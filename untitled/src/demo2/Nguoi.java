package demo2;

public class Nguoi {
    private Integer ID;
    private String name;
    private String gioiTinh;
    private String ngaySinh;
    private String diaChi;
    public Nguoi() {
        System.out.println("Cha duoc tao");
    }

    public Nguoi(Integer ID, String diaChi, String ngaySinh, String gioiTinh, String name) {
        this.ID = ID;
        this.diaChi = diaChi;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
        this.name = name;
    }

}
