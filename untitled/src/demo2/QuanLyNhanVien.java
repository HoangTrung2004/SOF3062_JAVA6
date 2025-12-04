package demo2;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QuanLyNhanVien {

    // Lớp NhanVien
    static class NhanVien {
        private String maNV;
        private String hoTen;
        private String gioiTinh;
        private String ngaySinh;
        private String diaChi;
        private String maSoThue;
        private String ngayKyHopDong;

        // Biến static (tĩnh) để theo dõi và tự động gán mã nhân viên
        private static int dem = 0;

        // --- Phương thức Hỗ trợ: Chuẩn hóa Ngày (dd/mm/yyyy) ---
        private String chuanHoaNgay(String ngay) {
            // Tách chuỗi ngày theo ký tự "/"
            String[] parts = ngay.split("/");
            if (parts.length != 3) return ngay;

            // Chuẩn hóa ngày (parts[0])
            if (parts[0].length() == 1) {
                parts[0] = "0" + parts[0];
            }
            // Chuẩn hóa tháng (parts[1])
            if (parts[1].length() == 1) {
                parts[1] = "0" + parts[1];
            }

            return parts[0] + "/" + parts[1] + "/" + parts[2];
        }

        // --- Phương thức Nhập thông tin ---
        public void nhap(Scanner sc) {
            // 1. Tự động gán mã nhân viên (00001, 00002,...)
            dem++;
            this.maNV = String.format("%05d", dem);

            // 2. Nhập 6 dòng thông tin còn lại
            this.hoTen = sc.nextLine();
            this.gioiTinh = sc.nextLine();
            this.ngaySinh = sc.nextLine();
            this.diaChi = sc.nextLine();
            this.maSoThue = sc.nextLine();
            this.ngayKyHopDong = sc.nextLine();

            // 3. Chuẩn hóa ngày sinh và ngày ký hợp đồng
            this.ngaySinh = chuanHoaNgay(this.ngaySinh);
            this.ngayKyHopDong = chuanHoaNgay(this.ngayKyHopDong);
        }

        // --- Ghi đè phương thức toString() để Xuất thông tin ---
        @Override
        public String toString() {
            // In tất cả thông tin trên một dòng, cách nhau đúng một khoảng trắng
            return maNV + " " + hoTen + " " + gioiTinh + " " + ngaySinh + " " + diaChi + " " + maSoThue + " " + ngayKyHopDong;
        }
    }

    // --- Hàm main để thực thi chương trình ---
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Đọc số lượng nhân viên N.
        // Dùng nextLine() kết hợp với parseInt() để tránh lỗi input mixing.
        int N = Integer.parseInt(sc.nextLine());

        List<NhanVien> danhSach = new ArrayList<>();

        // Vòng lặp nhập thông tin cho N nhân viên
        for (int i = 0; i < N; i++) {
            NhanVien nv = new NhanVien();
            nv.nhap(sc); // Gọi phương thức nhập
            danhSach.add(nv);
        }

        // In danh sách nhân viên ra màn hình
        for (NhanVien nv : danhSach) {
            // Khi in đối tượng, Java tự động gọi toString()
            System.out.println(nv);
        }

        sc.close();
    }
}