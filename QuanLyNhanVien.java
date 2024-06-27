/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package baitapjava;

/**
 *
 * @author Lenovo
 */
public class QuanLyNhanVien {
    private String maNV;
    private String hotTen;
    private boolean gioiTinh;
    private String diaChi;

    public QuanLyNhanVien() {
    }

    public QuanLyNhanVien(String maNV, String hotTen, boolean gioiTinh, String diaChi) {
        this.maNV = maNV;
        this.hotTen = hotTen;
        this.gioiTinh = gioiTinh;
        this.diaChi = diaChi;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getHotTen() {
        return hotTen;
    }

    public void setHotTen(String hotTen) {
        this.hotTen = hotTen;
    }

    public boolean isGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }
    
    public Object[] row() {
        return new Object[] {
            MaKH,hocPhi,ThoiLuong,maNV
        };
    }
        



    
    
}
