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
import app.model.ChatLieu;
import app.model.KieuDang;
import app.model.MauSac;
import app.model.Mu;

/**
 *
 * @author Dat
 */
public class MuService {

    public ArrayList<Mu> getAllMu() {
        ArrayList<Mu> list = new ArrayList<>();
        String sql = "select * from MU";
        Connection con = DBConnect.getConnection();
        try {
            PreparedStatement pstm = con.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                Mu mu = new Mu();
                mu.setId(rs.getInt("IDMU"));
                mu.setMaMu(rs.getString("MAMU"));
                mu.setTenMu(rs.getString("KIEUMU"));
                mu.setTrangThai(rs.getBoolean("TRANGTHAI"));
                list.add(mu);
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return list;
    }

    public Mu getMSbyMa(String maMu) {
        String sql = "select * from MU\n"
                + "where KIEUMU = ?";
        Connection con = DBConnect.getConnection();
        try {
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, maMu);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                Mu m = new Mu();
                m.setId(rs.getInt("IDMU"));
                m.setMaMu(rs.getString("MAMU"));
                m.setTenMu(rs.getString("KIEUMU"));
                m.setTrangThai(rs.getBoolean("TRANGTHAI"));
                return m;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Integer addMu(Mu m) {
        Integer row = null;
        String sql = "insert into MU(MAMU, TENMU, TRANGTHAI)"
                + "values(?, ?, ?)";
        Connection con = DBConnect.getConnection();
        try {
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, m.getMaMu());
            pstm.setString(2, m.getTenMu());
            pstm.setBoolean(3, m.isTrangThai());
            row = pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return row;
    }

    public Integer updateMu(Mu m) {
        Integer row = null;
        String sql = "update MU\n"
                + "set TRANGTHAI = ?, KIEUMU = ?\n"
                + "where MAMU = ?";
        Connection con = DBConnect.getConnection();
        try {
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setBoolean(1, m.isTrangThai());
            pstm.setString(2, m.getTenMu());
            pstm.setString(3, m.getMaMu());
            row = pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return row;
    }
}
