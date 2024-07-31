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

/**
 *
 * @author Dat
 */
public class KieuDangService {

    public ArrayList<KieuDang> getAllKieuDang() {
        ArrayList<KieuDang> list = new ArrayList<>();
        String sql = "select * from KIEUDANG";
        Connection con = DBConnect.getConnection();
        try {
            PreparedStatement pstm = con.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                KieuDang kd = new KieuDang();
                kd.setId(rs.getInt("IDKIEUDANG"));
                kd.setMaKieuDang(rs.getString("MAKIEUDANG"));
                kd.setTenKieuDang(rs.getString("TENKIEUDANG"));
                kd.setTrangThai(rs.getBoolean("TRANGTHAI"));
                list.add(kd);
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return list;
    }

    public KieuDang getKDbyMa(String maKD) {
        String sql = "select * from KIEUDANG\n"
                + "where TENKIEUDANG = ?";
        Connection con = DBConnect.getConnection();
        try {
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, maKD);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                KieuDang kd = new KieuDang();
                kd.setId(rs.getInt("IDKIEUDANG"));
                kd.setMaKieuDang(rs.getString("MAKIEUDANG"));
                kd.setTenKieuDang(rs.getString("TENKIEUDANG"));
                kd.setTrangThai(rs.getBoolean("TRANGTHAI"));
                return kd;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Integer addKieuDang(KieuDang kd) {
        Integer row = null;
        String sql = "insert into KIEUDANG(MAKIEUDANG, TENKIEUDANG, TRANGTHAI)"
                + "values(?, ?, ?)";
        Connection con = DBConnect.getConnection();
        try {
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, kd.getMaKieuDang());
            pstm.setString(2, kd.getTenKieuDang());
            pstm.setBoolean(3, kd.isTrangThai());
            row = pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return row;
    }

    public Integer updateKieuDang(KieuDang kd) {
        Integer row = null;
        String sql = "update KIEUDANG\n"
                + "set TRANGTHAI = ?, TENKIEUDANG = ?\n"
                + "where MAKIEUDANG = ?";
        Connection con = DBConnect.getConnection();
        try {
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setBoolean(1, kd.isTrangThai());
            pstm.setString(2, kd.getTenKieuDang());
            pstm.setString(3, kd.getMaKieuDang());
            row = pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return row;
    }
}
