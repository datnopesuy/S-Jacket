/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.model;

import java.util.Date;

/**
 *
 * @author ADMIN
 */
public class NhanVien {
    private int id;
    private String maNV;
    private String tenNV;
    private Date ngSinh;
    private boolean gioiTinh;
    private String diaChi;
    private String sdt;
    private String email;
    private String matKhau;

    public NhanVien() {
    }

//    public NhanVien(int id, String maNV, String tenNV, Date ngSinh, boolean gioiTinh, String diaChi, String sdt, String email) {
//        this.id = id;
//        this.maNV = maNV;
//        this.tenNV = tenNV;
//        this.ngSinh = ngSinh;
//        this.gioiTinh = gioiTinh;
//        this.diaChi = diaChi;
//        this.sdt = sdt;
//        this.email = email;
//    }
    

    public NhanVien(int id, String maNV, String tenNV, Date ngSinh, boolean gioiTinh, String diaChi, String sdt, String email, String matKhau) {
        this.id = id;
        this.maNV = maNV;
        this.tenNV = tenNV;
        this.ngSinh = ngSinh;
        this.gioiTinh = gioiTinh;
        this.diaChi = diaChi;
        this.sdt = sdt;
        this.email = email;
        this.matKhau = matKhau;
    }

    public NhanVien(String maNV, String tenNV, Date ngSinh, boolean gioiTinh, String diaChi, String sdt, String email, String matKhau) {
        this.maNV = maNV;
        this.tenNV = tenNV;
        this.ngSinh = ngSinh;
        this.gioiTinh = gioiTinh;
        this.diaChi = diaChi;
        this.sdt = sdt;
        this.email = email;
        this.matKhau = matKhau;
    }

    
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getTenNV() {
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }

    public Date getNgSinh() {
        return ngSinh;
    }

    public void setNgSinh(Date ngSinh) {
        this.ngSinh = ngSinh;
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

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    @Override
    public String toString() {
        return "NhanVien{" + "id=" + id + ", maNV=" + maNV + ", tenNV=" + tenNV + ", ngSinh=" + ngSinh + ", gioiTinh=" + gioiTinh + ", diaChi=" + diaChi + ", sdt=" + sdt + ", email=" + email + ", matKhau=" + matKhau + '}';
    }
    
}
