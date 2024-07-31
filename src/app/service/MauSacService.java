/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.service;

import app.model.LopLot;
import app.service.DBConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import app.model.MauSac;
import app.model.Mu;
import app.model.Size;

/**
 *
 * @author Dat
 */
public class MauSacService {

    public ArrayList<MauSac> getAllMauSac() {
        ArrayList<MauSac> list = new ArrayList<>();
        String sql = "select * from MAUSAC";
        Connection con = DBConnect.getConnection();
        try {
            PreparedStatement pstm = con.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                MauSac ms = new MauSac();
                ms.setId(rs.getInt("IDMAUSAC"));
                ms.setMaMauSac(rs.getString("MAMAU"));
                ms.setTenMauSac(rs.getString("TENMAU"));
                ms.setTrangThai(rs.getBoolean("TRANGTHAI"));
                list.add(ms);
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return list;
    }
    
        public MauSac getMSbyMa(String maMS) {
        String sql = "select * from MAUSAC\n"
                + "where TENMAU = ?";
        Connection con = DBConnect.getConnection();
        try {
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, maMS);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                MauSac ms = new MauSac();
                ms.setId(rs.getInt("IDMAUSAC"));
                ms.setMaMauSac(rs.getString("MAMAU"));
                ms.setTenMauSac(rs.getString("TENMAU"));
                ms.setTrangThai(rs.getBoolean("TRANGTHAI"));
                return ms;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Integer addMauSac(MauSac ms) {
        Integer row = null;
        String sql = "insert into MAUSAC(MAMAU, TENMAU, TRANGTHAI)"
                + "values(?, ?, ?)";
        Connection con = DBConnect.getConnection();
        try {
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, ms.getMaMauSac());
            pstm.setString(2, ms.getTenMauSac());
            pstm.setBoolean(3, ms.isTrangThai());
            row = pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return row;
    }
    
        public Integer updateMauSac(MauSac ms) {
        Integer row = null;
        String sql = "update MAUSAC\n"
                + "set TRANGTHAI = ?, TENMAU = ?\n"
                + "where MAMAU = ?";
        Connection con = DBConnect.getConnection();
        try {
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setBoolean(1, ms.isTrangThai());
            pstm.setString(2, ms.getTenMauSac());
            pstm.setString(3, ms.getMaMauSac());
            row = pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return row;
    }
}
