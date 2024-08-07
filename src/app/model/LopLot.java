/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.model;

/**
 *
 * @author Dat
 */
public class LopLot {

    private Integer id;
    private String maLopLot;
    private String tenLopLot;
    private boolean trangThai;

    public LopLot() {
    }

    public LopLot(Integer id, String maLopLot, String tenLopLot, boolean trangThai) {
        this.id = id;
        this.maLopLot = maLopLot;
        this.tenLopLot = tenLopLot;
        this.trangThai = trangThai;
    }

    public Integer getId() {
        return id;
    }

    public String getMaLopLot() {
        return maLopLot;
    }

    public String getTenLopLot() {
        return tenLopLot;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setMaLopLot(String maLopLot) {
        this.maLopLot = maLopLot;
    }

    public void setTenLopLot(String tenLopLot) {
        this.tenLopLot = tenLopLot;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    @Override
    public String toString() {
        return tenLopLot;
    }
    
    
}
