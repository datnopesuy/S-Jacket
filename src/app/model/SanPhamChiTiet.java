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
public class SanPhamChiTiet {

    private Integer idSPCT;
    private SanPham sanPham;
    private MauSac mauSac;
    private ChatLieu chatLieu;
    private Mu mu;
    private Size size;
    private LopLot lopLot;
    private KieuDang kieuDang;
    private String moTa;
    private int soLuong;
    private Double gia;
    private String nguoiTao;
    private String nguoiSua;
    private Date ngayTao;
    private Date ngaySua;
    private boolean trangThai;

    public SanPhamChiTiet() {
    }

    public SanPhamChiTiet(Integer idSPCT, SanPham sanPham, MauSac mauSac, ChatLieu chatLieu, Mu mu, Size size, LopLot lopLot, KieuDang kieuDang, String moTa, int soLuong, Double gia, String nguoiTao, String nguoiSua, Date ngayTao, Date ngaySua, boolean trangThai) {
        this.idSPCT = idSPCT;
        this.sanPham = sanPham;
        this.mauSac = mauSac;
        this.chatLieu = chatLieu;
        this.mu = mu;
        this.size = size;
        this.lopLot = lopLot;
        this.kieuDang = kieuDang;
        this.moTa = moTa;
        this.soLuong = soLuong;
        this.gia = gia;
        this.nguoiTao = nguoiTao;
        this.nguoiSua = nguoiSua;
        this.ngayTao = ngayTao;
        this.ngaySua = ngaySua;
        this.trangThai = trangThai;
    }

    public Integer getIdSPCT() {
        return idSPCT;
    }

    public SanPham getSanPham() {
        return sanPham;
    }

    public MauSac getMauSac() {
        return mauSac;
    }

    public ChatLieu getChatLieu() {
        return chatLieu;
    }

    public Mu getMu() {
        return mu;
    }

    public Size getSize() {
        return size;
    }

    public LopLot getLopLot() {
        return lopLot;
    }

    public KieuDang getKieuDang() {
        return kieuDang;
    }

    public String getMoTa() {
        return moTa;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public Double getGia() {
        return gia;
    }

    public String getNguoiTao() {
        return nguoiTao;
    }

    public String getNguoiSua() {
        return nguoiSua;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public Date getNgaySua() {
        return ngaySua;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setIdSPCT(Integer idSPCT) {
        this.idSPCT = idSPCT;
    }

    public void setSanPham(SanPham sanPham) {
        this.sanPham = sanPham;
    }

    public void setMauSac(MauSac mauSac) {
        this.mauSac = mauSac;
    }

    public void setChatLieu(ChatLieu chatLieu) {
        this.chatLieu = chatLieu;
    }

    public void setMu(Mu mu) {
        this.mu = mu;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public void setLopLot(LopLot lopLot) {
        this.lopLot = lopLot;
    }

    public void setKieuDang(KieuDang kieuDang) {
        this.kieuDang = kieuDang;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public void setGia(Double gia) {
        this.gia = gia;
    }

    public void setNguoiTao(String nguoiTao) {
        this.nguoiTao = nguoiTao;
    }

    public void setNguoiSua(String nguoiSua) {
        this.nguoiSua = nguoiSua;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public void setNgaySua(Date ngaySua) {
        this.ngaySua = ngaySua;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    @Override
    public String toString() {
        return "SanPhamChiTiet{" + "idSPCT=" + idSPCT + ", sanPham=" + sanPham + ", mauSac=" + mauSac + ", chatLieu=" + chatLieu + ", mu=" + mu + ", size=" + size + ", lopLot=" + lopLot + ", kieuDang=" + kieuDang + ", moTa=" + moTa + ", soLuong=" + soLuong + ", gia=" + gia + ", nguoiTao=" + nguoiTao + ", nguoiSua=" + nguoiSua + ", ngayTao=" + ngayTao + ", ngaySua=" + ngaySua + ", trangThai=" + trangThai + '}';
    }
    
}
