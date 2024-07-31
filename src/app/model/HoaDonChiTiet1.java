/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.model;

/**
 *
 * @author Dat
 */
public class HoaDonChiTiet1 {
    
    private HoaDon hoaDon;
    private SanPham sanPham;
    private SanPhamChiTiet sanPhamChiTiet;
    private int soLuong;
    private double gia;
    private double thanhTien;

    public HoaDonChiTiet1() {
    }

    public HoaDonChiTiet1(HoaDon hoaDon, SanPham sanPham, SanPhamChiTiet sanPhamChiTiet, int soLuong, double gia, double thanhTien) {
        this.hoaDon = hoaDon;
        this.sanPham = sanPham;
        this.sanPhamChiTiet = sanPhamChiTiet;
        this.soLuong = soLuong;
        this.gia = gia;
        this.thanhTien = thanhTien;
    }

    public HoaDon getHoaDon() {
        return hoaDon;
    }

    public SanPham getSanPham() {
        return sanPham;
    }

    public SanPhamChiTiet getSanPhamChiTiet() {
        return sanPhamChiTiet;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public double getGia() {
        return gia;
    }

    public double getThanhTien() {
        return thanhTien;
    }

    public void setHoaDon(HoaDon hoaDon) {
        this.hoaDon = hoaDon;
    }

    public void setSanPham(SanPham sanPham) {
        this.sanPham = sanPham;
    }

    public void setSanPhamChiTiet(SanPhamChiTiet sanPhamChiTiet) {
        this.sanPhamChiTiet = sanPhamChiTiet;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public void setGia(double gia) {
        this.gia = gia;
    }

    public void setThanhTien(double thanhTien) {
        this.thanhTien = thanhTien;
    }
    
    
    
    
}
