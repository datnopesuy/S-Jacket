/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.model;

/**
 *
 * @author Dat
 */
public class ChatLieu {

    private Integer id;
    private String maChatLieu;
    private String tenChatLieu;
    private boolean trangThai;

    public ChatLieu() {
    }

    public ChatLieu(Integer id, String maChatLieu, String tenChatLieu, boolean trangThai) {
        this.id = id;
        this.maChatLieu = maChatLieu;
        this.tenChatLieu = tenChatLieu;
        this.trangThai = trangThai;
    }

    public Integer getId() {
        return id;
    }

    public String getMaChatLieu() {
        return maChatLieu;
    }

    public String getTenChatLieu() {
        return tenChatLieu;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setMaChatLieu(String maChatLieu) {
        this.maChatLieu = maChatLieu;
    }

    public void setTenChatLieu(String tenChatLieu) {
        this.tenChatLieu = tenChatLieu;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    @Override
    public String toString() {
        return tenChatLieu;
    }
    
    
    
}
