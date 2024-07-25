/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.model;

/**
 *
 * @author Dat
 */
public class SanPham {
    
    private Integer id;
    private String maSP;
    private String tenSP;
    private boolean trangThai;

    public SanPham() {
    }

    public SanPham(Integer id, String maSP, String tenSP, boolean trangThai) {
        this.id = id;
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.trangThai = trangThai;
    }

    public Integer getId() {
        return id;
    }

    public String getMaSP() {
        return maSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    @Override
    public String toString() {
        return "SanPham{" + "id=" + id + ", maSP=" + maSP + ", tenSP=" + tenSP + ", trangThai=" + trangThai + '}';
    }
    
    
}
