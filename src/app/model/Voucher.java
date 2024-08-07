/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.model;

/**
 *
 * @author Dat
 */
public class Voucher {
    private String maVoucher ;
   private String tenvoucher;
   private Double dontToithieu;
   private Double mucGiamgia;

    public Voucher() {
    }
   

    public Voucher(String maVoucher, String tenvoucher, Double dontToithieu, Double mucGiamgia) {
        this.maVoucher = maVoucher;
        this.tenvoucher = tenvoucher;
        this.dontToithieu = dontToithieu;
        this.mucGiamgia = mucGiamgia;
    }

    public String getMaVoucher() {
        return maVoucher;
    }

    public String getTenvoucher() {
        return tenvoucher;
    }

    public Double getDontToithieu() {
        return dontToithieu;
    }

    public Double getMucGiamgia() {
        return mucGiamgia;
    }

    public void setMaVoucher(String maVoucher) {
        this.maVoucher = maVoucher;
    }

    public void setTenvoucher(String tenvoucher) {
        this.tenvoucher = tenvoucher;
    }

    public void setDontToithieu(Double dontToithieu) {
        this.dontToithieu = dontToithieu;
    }

    public void setMucGiamgia(Double mucGiamgia) {
        this.mucGiamgia = mucGiamgia;
    }

    @Override
    public String toString() {
        return "Voucher{" + "maVoucher=" + maVoucher + ", tenvoucher=" + tenvoucher + ", dontToithieu=" + dontToithieu + ", mucGiamgia=" + mucGiamgia + '}';
    }
    
}
