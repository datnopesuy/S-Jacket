/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.model;

import java.util.Date;

/**
 *
 * @author acer
 */
public class Voucher {
    private String maVocher;
    private String tenVoucher ;
    private Double donToithieu;
    private Double mucGiamgia;
    private String hinhThucgiam;
    private Date ngayBatdau ;
    private Date ngayKetthuc;
    Boolean trangthai;
   // private

    public Voucher(String maVocher, String tenVoucher, Double donToithieu, double mucGiamgia, String hinhThucgiam, Date ngayBatdau, Date ngayKetthuc,Boolean trangthai) {
        this.maVocher = maVocher;
        this.tenVoucher = tenVoucher;
        this.donToithieu = donToithieu;
        this.mucGiamgia = mucGiamgia;
        this.hinhThucgiam = hinhThucgiam;
        this.ngayBatdau = ngayBatdau;
        this.ngayKetthuc = ngayKetthuc;
        this.trangthai=trangthai;
    }

    public Voucher(String maVocher, String tenVoucher, Double donToithieu, Double mucGiamgia, String hinhThucgiam, Date ngayBatdau, Date ngayKetthuc) {
        this.maVocher = maVocher;
        this.tenVoucher = tenVoucher;
        this.donToithieu = donToithieu;
        this.mucGiamgia = mucGiamgia;
        this.hinhThucgiam = hinhThucgiam;
        this.ngayBatdau = ngayBatdau;
        this.ngayKetthuc = ngayKetthuc;
    }

    public Voucher() {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public String getMaVocher() {
        return maVocher;
    }

    public String getTenVoucher() {
        return tenVoucher;
    }

    public Double getDonToithieu() {
        return donToithieu;
    }

    public double getMucGiamgia() {
        return mucGiamgia;
    }

    public String getHinhThucgiam() {
        return hinhThucgiam;
    }

    public Date getNgayBatdau() {
        return ngayBatdau;
    }

    public Date getNgayKetthuc() {
        return ngayKetthuc;
    }

    public void setMaVocher(String maVocher) {
        this.maVocher = maVocher;
    }

    public void setTenVoucher(String tenVoucher) {
        this.tenVoucher = tenVoucher;
    }

    public void setDonToithieu(Double donToithieu) {
        this.donToithieu = donToithieu;
    }

    public void setMucGiamgia(double mucGiamgia) {
        this.mucGiamgia = mucGiamgia;
    }

    public void setHinhThucgiam(String hinhThucgiam) {
        this.hinhThucgiam = hinhThucgiam;
    }

    public void setNgayBatdau(Date ngayBatdau) {
        this.ngayBatdau = ngayBatdau;
    }

    public void setNgayKetthuc(Date ngayKetthuc) {
        this.ngayKetthuc = ngayKetthuc;
    }

    public Boolean getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(Boolean trangthai) {
       if(this.ngayKetthuc.before(new Date())){
       this.trangthai=false;
       }
       else this.trangthai = true;
    }



    @Override
    public String toString() {
        return "Voucher{" + "maVocher=" + maVocher + ", tenVoucher=" + tenVoucher + ", donToithieu=" + donToithieu + ", mucGiamgia=" + mucGiamgia + ", hinhThucgiam=" + hinhThucgiam + ", ngayBatdau=" + ngayBatdau + ", ngayKetthuc=" + ngayKetthuc + ", trangthai=" + trangthai + '}';
    }

    

   
    
    
}
