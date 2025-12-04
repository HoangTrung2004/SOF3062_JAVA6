package org.example.lab5.Bai2;

// Dòng đúng
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.io.IOException;

public class StudentFrame extends JFrame {
    private JTextField txtId = new JTextField(10);
    private JTextField txtName = new JTextField(20);
    private JRadioButton rdMale = new JRadioButton("Male");
    private JRadioButton rdFemale = new JRadioButton("Female");
    private JTextField txtMark = new JTextField(5);
    private JTable table;
    private DefaultTableModel model;

    static String host = "https://demoproject-5a913-default-rtdb.firebaseio.com";

    public StudentFrame() {
        setTitle("Quản lý sinh viên");
        setSize(700, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Form nhập liệu
        JPanel form = new JPanel(new GridLayout(4, 2));
        ButtonGroup group = new ButtonGroup();
        group.add(rdMale);
        group.add(rdFemale);

        form.add(new JLabel("ID:")); form.add(txtId);
        form.add(new JLabel("Full Name:")); form.add(txtName);
        form.add(new JLabel("Gender:"));
        JPanel genderPanel = new JPanel();
        genderPanel.add(rdMale); genderPanel.add(rdFemale);
        form.add(genderPanel);
        form.add(new JLabel("Average Mark:")); form.add(txtMark);

        add(form, BorderLayout.NORTH);

        // Bảng dữ liệu (có cột Key ẩn)
        model = new DefaultTableModel(new String[]{"Key", "Id", "Full Name", "Gender", "Mark"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // tất cả các ô đều không chỉnh sửa được
            }
        };

        table = new JTable(model);
        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(0).setMinWidth(0);
        columnModel.getColumn(0).setMaxWidth(0);
        columnModel.getColumn(0).setPreferredWidth(0);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // Nút chức năng
        JPanel buttons = new JPanel();
        JButton btnCreate = new JButton("Create");
        JButton btnUpdate = new JButton("Update");
        JButton btnDelete = new JButton("Delete");
        JButton btnReset = new JButton("Reset");
        buttons.add(btnCreate); buttons.add(btnUpdate); buttons.add(btnDelete); buttons.add(btnReset);
        add(buttons, BorderLayout.SOUTH);

        // Sự kiện chọn dòng
        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int row = table.getSelectedRow();
                if (row >= 0) {
                    txtId.setText(model.getValueAt(row, 1).toString());
                    txtName.setText(model.getValueAt(row, 2).toString());
                    boolean gender = Boolean.parseBoolean(model.getValueAt(row, 3).toString());
                    if (gender) rdMale.setSelected(true); else rdFemale.setSelected(true);
                    txtMark.setText(model.getValueAt(row, 4).toString());
                }
            }
        });

        // Sự kiện nút
        btnCreate.addActionListener(e -> createStudent());
        btnUpdate.addActionListener(e -> updateStudent());
        btnDelete.addActionListener(e -> deleteStudent());
        btnReset.addActionListener(e -> resetForm());

        loadStudents();
    }

    private void loadStudents() {
        try {
            var url = host + "/students.json";
            var connection = HttpClient.openConnection("GET", url);
            var response = HttpClient.readData(connection);
            var mapper = new ObjectMapper();
            var studentMap = mapper.readValue(response, StudentMap.class);

            model.setRowCount(0);
            for (var entry : studentMap.entrySet()) {
                String key = entry.getKey();
                Student s = entry.getValue();
                model.addRow(new Object[]{key, s.getId(), s.getName(), s.isGender(), s.getMark()});
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }

    private void createStudent() {
        try {
            var student = new Student(txtId.getText(), txtName.getText(),
                    Double.parseDouble(txtMark.getText()), rdMale.isSelected());
            var mapper = new ObjectMapper();
            var data = mapper.writeValueAsBytes(student);

            var url = host + "/students.json";
            var connection = HttpClient.openConnection("POST", url);
            HttpClient.writeData(connection, data);

            loadStudents();
            resetForm();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }

    private void updateStudent() {
        int row = table.getSelectedRow();
        if (row >= 0) {
            String key = model.getValueAt(row, 0).toString();
            try {
                var student = new Student(txtId.getText(), txtName.getText(),
                        Double.parseDouble(txtMark.getText()), rdMale.isSelected());
                var mapper = new ObjectMapper();
                var data = mapper.writeValueAsBytes(student);

                var url = host + "/students/" + key + ".json";
                var connection = HttpClient.openConnection("PUT", url);
                HttpClient.writeData(connection, data);

                loadStudents();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(this, "Chọn một sinh viên để cập nhật!");
        }
    }

    private void deleteStudent() {
        int row = table.getSelectedRow();
        if (row >= 0) {
            String key = model.getValueAt(row, 0).toString();
            try {
                var url = host + "/students/" + key + ".json";
                var connection = HttpClient.openConnection("DELETE", url);
                HttpClient.readData(connection);

                loadStudents();
                resetForm();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(this, "Chọn một sinh viên để xóa!");
        }
    }

    private void resetForm() {
        txtId.setText("");
        txtName.setText("");
        txtMark.setText("");
        rdMale.setSelected(false);
        rdFemale.setSelected(false);
        table.clearSelection();
    }

}
