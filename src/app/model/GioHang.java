/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.model;

import java.util.Date;

/**
 *
 * @author Dat
 */
public class GioHang {
    private HoaDonChiTiet hoaDonChiTiet;
    private HoaDon hoaDon;
    private SanPham sanPham;
    private SanPhamChiTiet sanPhamChiTiet;
    private MauSac mauSac;
    private ChatLieu chatLieu;
    private KieuDang kieuDang;
    private Size size;
    private LopLot lopLot;
    private Mu mu;
    private int SoLuong;
    private double Gia;
    private double ThanhTien;
    private String NguoiTao;
    private Date NgayTao;
    private Date NgaySua;

    public GioHang() {
    }

    public GioHang(HoaDonChiTiet hoaDonChiTiet, HoaDon hoaDon, SanPham sanPham, SanPhamChiTiet sanPhamChiTiet, MauSac mauSac, ChatLieu chatLieu, KieuDang kieuDang, Size size, LopLot lopLot, Mu mu, int SoLuong, double Gia, double ThanhTien, String NguoiTao, Date NgayTao, Date NgaySua) {
        this.hoaDonChiTiet = hoaDonChiTiet;
        this.hoaDon = hoaDon;
        this.sanPham = sanPham;
        this.sanPhamChiTiet = sanPhamChiTiet;
        this.mauSac = mauSac;
        this.chatLieu = chatLieu;
        this.kieuDang = kieuDang;
        this.size = size;
        this.lopLot = lopLot;
        this.mu = mu;
        this.SoLuong = SoLuong;
        this.Gia = Gia;
        this.ThanhTien = ThanhTien;
        this.NguoiTao = NguoiTao;
        this.NgayTao = NgayTao;
        this.NgaySua = NgaySua;
    }

    public HoaDonChiTiet getHoaDonChiTiet() {
        return hoaDonChiTiet;
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

    public MauSac getMauSac() {
        return mauSac;
    }

    public ChatLieu getChatLieu() {
        return chatLieu;
    }

    public KieuDang getKieuDang() {
        return kieuDang;
    }

    public Size getSize() {
        return size;
    }

    public LopLot getLopLot() {
        return lopLot;
    }

    public Mu getMu() {
        return mu;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public double getGia() {
        return Gia;
    }

    public double getThanhTien() {
        return ThanhTien;
    }

    public String getNguoiTao() {
        return NguoiTao;
    }

    public Date getNgayTao() {
        return NgayTao;
    }

    public Date getNgaySua() {
        return NgaySua;
    }

    public void setHoaDonChiTiet(HoaDonChiTiet hoaDonChiTiet) {
        this.hoaDonChiTiet = hoaDonChiTiet;
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

    public void setMauSac(MauSac mauSac) {
        this.mauSac = mauSac;
    }

    public void setChatLieu(ChatLieu chatLieu) {
        this.chatLieu = chatLieu;
    }

    public void setKieuDang(KieuDang kieuDang) {
        this.kieuDang = kieuDang;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public void setLopLot(LopLot lopLot) {
        this.lopLot = lopLot;
    }

    public void setMu(Mu mu) {
        this.mu = mu;
    }

    public void setSoLuong(int SoLuong) {
        this.SoLuong = SoLuong;
    }

    public void setGia(double Gia) {
        this.Gia = Gia;
    }

    public void setThanhTien(double ThanhTien) {
        this.ThanhTien = ThanhTien;
    }

    public void setNguoiTao(String NguoiTao) {
        this.NguoiTao = NguoiTao;
    }

    public void setNgayTao(Date NgayTao) {
        this.NgayTao = NgayTao;
    }

    public void setNgaySua(Date NgaySua) {
        this.NgaySua = NgaySua;
    }

    
    
    @Override
    public String toString() {
        return "GioHang{" + "hoaDonChiTiet=" + hoaDonChiTiet + ", hoaDon=" + hoaDon + ", sanPham=" + sanPham + ", sanPhamChiTiet=" + sanPhamChiTiet + ", SoLuong=" + SoLuong + ", Gia=" + Gia + ", ThanhTien=" + ThanhTien + ", NguoiTao=" + NguoiTao + ", NgayTao=" + NgayTao + ", NgaySua=" + NgaySua + '}';
    }
    
    
    
    
}
