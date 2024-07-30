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
public class HoaDon {
    private int idHoaDon;
    private int idKhachHang;
    private int idNhanVien;
    private int idVouCher;
    private String MaHoaDon;
    private double TongTien;
    private Date NgayTao;
    private Date NgayThanhToan;
    private String NguoiTao;
    private boolean TrangThai;

    public HoaDon(int idHoaDon, int idKhachHang, int idNhanVien, int idVouCher, String MaHoaDon, double TongTien, Date NgayTao, Date NgayThanhToan, String NguoiTao, boolean TrangThai) {
        this.idHoaDon = idHoaDon;
        this.idKhachHang = idKhachHang;
        this.idNhanVien = idNhanVien;
        this.idVouCher = idVouCher;
        this.MaHoaDon = MaHoaDon;
        this.TongTien = TongTien;
        this.NgayTao = NgayTao;
        this.NgayThanhToan = NgayThanhToan;
        this.NguoiTao = NguoiTao;
        this.TrangThai = TrangThai;
    }

    public HoaDon() {
    }
    
     public HoaDon(int idKhachHang, int idNhanVien, String MaHoaDon, double TongTien) {
        this.idKhachHang = idKhachHang;
        this.idNhanVien = idNhanVien;
        this.MaHoaDon = MaHoaDon;
        this.TongTien = TongTien;
    }

    public int getIdHoaDon() {
        return idHoaDon;
    }

    public void setIdHoaDon(int idHoaDon) {
        this.idHoaDon = idHoaDon;
    }

    public int getIdKhachHang() {
        return idKhachHang;
    }

    public void setIdKhachHang(int idKhachHang) {
        this.idKhachHang = idKhachHang;
    }

    public int getIdNhanVien() {
        return idNhanVien;
    }

    public void setIdNhanVien(int idNhanVien) {
        this.idNhanVien = idNhanVien;
    }

    public int getIdVouCher() {
        return idVouCher;
    }

    public void setIdVouCher(int idVouCher) {
        this.idVouCher = idVouCher;
    }

    public String getMaHoaDon() {
        return MaHoaDon;
    }

    public void setMaHoaDon(String MaHoaDon) {
        this.MaHoaDon = MaHoaDon;
    }

    public double getTongTien() {
        return TongTien;
    }

    public void setTongTien(double TongTien) {
        this.TongTien = TongTien;
    }

    public Date getNgayTao() {
        return NgayTao;
    }

    public void setNgayTao(Date NgayTao) {
        this.NgayTao = NgayTao;
    }

    public Date getNgayThanhToan() {
        return NgayThanhToan;
    }

    public void setNgayThanhToan(Date NgayThanhToan) {
        this.NgayThanhToan = NgayThanhToan;
    }

    public String getNguoiTao() {
        return NguoiTao;
    }

    public void setNguoiTao(String NguoiTao) {
        this.NguoiTao = NguoiTao;
    }

    public boolean isTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(boolean TrangThai) {
        this.TrangThai = TrangThai;
    }
     
     
     
    
}
