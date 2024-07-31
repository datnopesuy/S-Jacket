/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.service;

import app.service.DBConnect;
import java.util.ArrayList;
import app.model.ChatLieu;
import java.sql.*;
import app.model.Size;

/**
 *
 * @author Dat
 */
public class ChatLieuService {

    public ArrayList<ChatLieu> getAllChatLieu() {
        ArrayList<ChatLieu> list = new ArrayList<>();
        String sql = "select * from CHATLIEU";
        Connection con = DBConnect.getConnection();
        try {
            PreparedStatement pstm = con.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                ChatLieu cl = new ChatLieu();
                cl.setId(rs.getInt("IDCHATLIEU"));
                cl.setMaChatLieu(rs.getString("MACHATLIEU"));
                cl.setTenChatLieu(rs.getString("TENCHATLIEU"));
                cl.setTrangThai(rs.getBoolean("TRANGTHAI"));
                list.add(cl);
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return list;
    }
    
        public ChatLieu getCLbyMa(String maCL) {
        String sql = "select * from CHATLIEU\n"
                + "where MACHATLIEU = ?";
        Connection con = DBConnect.getConnection();
        try {
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, maCL);
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                ChatLieu cl = new ChatLieu();
                cl.setId(rs.getInt("IDCHATLIEU"));
                cl.setMaChatLieu(rs.getString("MACHATLIEU"));
                cl.setTenChatLieu(rs.getString("TENCHATLIEU"));
                cl.setTrangThai(rs.getBoolean("TRANGTHAI"));
                return cl;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Integer addChatLieu(ChatLieu cl) {
        Integer row = null;
        String sql = "insert into CHATLIEU(MACHATLIEU, TENCHATLIEU, TRANGTHAI)"
                + "values(?, ?, ?)";
        Connection con = DBConnect.getConnection();
        try {
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, cl.getMaChatLieu());
            pstm.setString(2, cl.getTenChatLieu());
            pstm.setBoolean(3, cl.isTrangThai());
            row = pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return row;
    }

    public Integer updateChatLieu(ChatLieu cl) {
        Integer row = null;
        String sql = "update CHATLIEU\n"
                + "set TRANGTHAI = ?, TENCHATLIEU = ?\n"
                + "where MACHATLIEU = ?";
        Connection con = DBConnect.getConnection();
        try {
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setBoolean(1, cl.isTrangThai());
            pstm.setString(2, cl.getTenChatLieu());
            pstm.setString(3, cl.getMaChatLieu());
            row = pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return row;
    }

}
