/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.model;

/**
 *
 * @author Dat
 */
public class Mu {

    private Integer id;
    private String maMu;
    private String tenMu;
    private boolean trangThai;

    public Mu() {
    }

    public Mu(Integer id, String maMu, String tenMu, boolean trangThai) {
        this.id = id;
        this.maMu = maMu;
        this.tenMu = tenMu;
        this.trangThai = trangThai;
    }

    public Integer getId() {
        return id;
    }

    public String getMaMu() {
        return maMu;
    }

    public String getTenMu() {
        return tenMu;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setMaMu(String maMu) {
        this.maMu = maMu;
    }

    public void setTenMu(String tenMu) {
        this.tenMu = tenMu;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    @Override
    public String toString() {
        return tenMu;
    }
    
    
}
