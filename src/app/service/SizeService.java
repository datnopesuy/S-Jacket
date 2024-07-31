/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.service;

import app.service.DBConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import app.model.Mu;
import app.model.SanPham;
import app.model.Size;

/**
 *
 * @author Dat
 */
public class SizeService {

    public ArrayList<Size> getAllSize() {
        ArrayList<Size> list = new ArrayList<>();
        String sql = "select IDSIZE,MASIZE, TENSIZE, TRANGTHAI from SIZE";
        Connection con = DBConnect.getConnection();
        try {
            PreparedStatement pstm = con.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                Size s = new Size();
                s.setId(rs.getInt("IDSIZE"));
                s.setMaSize(rs.getString("MASIZE"));
                s.setTenSize(rs.getString("TENSIZE"));
                s.setTrangThai(rs.getBoolean("TRANGTHAI"));
                list.add(s);
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return list;
    }

    public Size getSizebyMa(String maSize) {
        String sql = "select * from SIZE\n"
                + "where MASIZE = ?";
        Connection con = DBConnect.getConnection();
        try {
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, maSize);
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                Size s = new Size();
                s.setId(rs.getInt("IDSIZE"));
                s.setMaSize(rs.getString("MASIZE"));
                s.setTenSize(rs.getString("TENSIZE"));
                s.setTrangThai(rs.getBoolean("TRANGTHAI"));
                return s;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Integer addSize(Size s) {
        Integer row = null;
        String sql = "insert into SIZE(MASIZE, TENSIZE, TRANGTHAI)"
                + "values(?, ?, ?)";
        Connection con = DBConnect.getConnection();
        try {
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, s.getMaSize());
            pstm.setString(2, s.getTenSize());
            pstm.setBoolean(3, s.isTrangThai());
            row = pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return row;
    }

    public Integer updateSize(Size s) {
        Integer row = null;
        String sql = "update SIZE\n"
                + "set TRANGTHAI = ?, TENSIZE = ?\n"
                + "where MASIZE = ?";
        Connection con = DBConnect.getConnection();
        try {
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setBoolean(1, s.isTrangThai());
            pstm.setString(2, s.getTenSize());
            pstm.setString(3, s.getMaSize());
            row = pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return row;
    }

}
