/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.model;

import java.util.Date;

/**
 *
 * @author PC
 */
public class QuanLyHoaDon {
    private String MaHoaDon;
    private Date ngayTaoHD;
    private Date ngayThanhToan;
    private Double tongtientt;
    private String maKH;
    private String maNV;
    private String tenKH;
    private String sdt;
    private boolean trangThai;
    private String tenNV;
    private String diaChi;
    private int mucgiam;
    private String hinhThucTT;
    private Double thanhtien;

    public QuanLyHoaDon() {
    }

    public QuanLyHoaDon(String MaHoaDon, Date ngayTaoHD, Date ngayThanhToan, Double tongtientt, String maNV, String tenKH, String sdt, boolean trangThai) {
        this.MaHoaDon = MaHoaDon;
        this.ngayTaoHD = ngayTaoHD;
        this.ngayThanhToan = ngayThanhToan;
        this.tongtientt = tongtientt;
        this.maNV = maNV;
        this.tenKH = tenKH;
        this.sdt = sdt;
        this.trangThai = trangThai;
    }

    

    public QuanLyHoaDon(String MaHoaDon, Date ngayTaoHD, Date ngayThanhToan, Double tongtientt, String maKH, String maNV, String tenKH, String sdt, boolean trangThai, String tenNV, String diaChi, int mucgiam, String hinhThucTT, Double thanhtien) {
        this.MaHoaDon = MaHoaDon;
        this.ngayTaoHD = ngayTaoHD;
        this.ngayThanhToan = ngayThanhToan;
        this.tongtientt = tongtientt;
        this.maKH = maKH;
        this.maNV = maNV;
        this.tenKH = tenKH;
        this.sdt = sdt;
        this.trangThai = trangThai;
        this.tenNV = tenNV;
        this.diaChi = diaChi;
        this.mucgiam = mucgiam;
        this.hinhThucTT = hinhThucTT;
        this.thanhtien = thanhtien;
    }

    public String getMaHoaDon() {
        return MaHoaDon;
    }

    public void setMaHoaDon(String MaHoaDon) {
        this.MaHoaDon = MaHoaDon;
    }

    public Date getNgayTaoHD() {
        return ngayTaoHD;
    }

    public void setNgayTaoHD(Date ngayTaoHD) {
        this.ngayTaoHD = ngayTaoHD;
    }

    public Date getNgayThanhToan() {
        return ngayThanhToan;
    }

    public void setNgayThanhToan(Date ngayThanhToan) {
        this.ngayThanhToan = ngayThanhToan;
    }

    public Double getTongtientt() {
        return tongtientt;
    }

    public void setTongtientt(Double tongtientt) {
        this.tongtientt = tongtientt;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    public String getTenNV() {
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public int getMucgiam() {
        return mucgiam;
    }

    public void setMucgiam(int mucgiam) {
        this.mucgiam = mucgiam;
    }

    public String getHinhThucTT() {
        return hinhThucTT;
    }

    public void setHinhThucTT(String hinhThucTT) {
        this.hinhThucTT = hinhThucTT;
    }

    public Double getThanhtien() {
        return thanhtien;
    }

    public void setThanhtien(Double thanhtien) {
        this.thanhtien = thanhtien;
    }
    
    
    
}
