/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.model;

/**
 *
 * @author Dat
 */
public class MauSac {

    private Integer id;
    private String maMauSac;
    private String tenMauSac;
    private boolean trangThai;

    public MauSac() {
    }

    public MauSac(Integer id, String maMauSac, String tenMauSac, boolean trangThai) {
        this.id = id;
        this.maMauSac = maMauSac;
        this.tenMauSac = tenMauSac;
        this.trangThai = trangThai;
    }

    public Integer getId() {
        return id;
    }

    public String getMaMauSac() {
        return maMauSac;
    }

    public String getTenMauSac() {
        return tenMauSac;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setMaMauSac(String maMauSac) {
        this.maMauSac = maMauSac;
    }

    public void setTenMauSac(String tenMauSac) {
        this.tenMauSac = tenMauSac;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    @Override
    public String toString() {
        return tenMauSac;
    }
    
    
}
