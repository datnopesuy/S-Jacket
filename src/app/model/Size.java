/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.model;

/**
 *
 * @author Dat
 */
public class Size {
    private Integer id;
    private String maSize;
    private String tenSize;
    private boolean trangThai;

    public Size() {
    }

    public Size(Integer id, String maSize, String tenSize, boolean trangThai) {
        this.id = id;
        this.maSize = maSize;
        this.tenSize = tenSize;
        this.trangThai = trangThai;
    }

    public Integer getId() {
        return id;
    }

    public String getMaSize() {
        return maSize;
    }

    public String getTenSize() {
        return tenSize;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setMaSize(String maSize) {
        this.maSize = maSize;
    }

    public void setTenSize(String tenSize) {
        this.tenSize = tenSize;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    @Override
    public String toString() {
        return tenSize;
    }
    
}
