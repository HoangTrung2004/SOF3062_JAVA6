package demo2;

public class sinhVien extends Nguoi {
    private Long GPA;
    private String nganh;

    public sinhVien(Integer ID, String diaChi, String ngaySinh, String gioiTinh, String name,String nganh, Long GPA) {
        super(ID, diaChi, ngaySinh, gioiTinh, name);
        this.nganh = nganh;
        this.GPA = GPA;
    }




}

