/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.model;

/**
 *
 * @author Dat
 */
public class KieuDang {

    private Integer id;
    private String maKieuDang;
    private String tenKieuDang;
    private boolean trangThai;

    public KieuDang() {
    }

    public KieuDang(Integer id, String maKieuDang, String tenKieuDang, boolean trangThai) {
        this.id = id;
        this.maKieuDang = maKieuDang;
        this.tenKieuDang = tenKieuDang;
        this.trangThai = trangThai;
    }

    public Integer getId() {
        return id;
    }

    public String getMaKieuDang() {
        return maKieuDang;
    }

    public String getTenKieuDang() {
        return tenKieuDang;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setMaKieuDang(String maKieuDang) {
        this.maKieuDang = maKieuDang;
    }

    public void setTenKieuDang(String tenKieuDang) {
        this.tenKieuDang = tenKieuDang;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    @Override
    public String toString() {
        return tenKieuDang;
    }
    
    
}
