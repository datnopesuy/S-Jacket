/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.service;

import app.model.KieuDang;
import app.service.DBConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import app.model.LopLot;
import app.model.MauSac;

/**
 *
 * @author Dat
 */
public class LopLotService {

    public ArrayList<LopLot> getAllLopLot() {
        ArrayList<LopLot> list = new ArrayList<>();
        String sql = "select * from LOPLOT";
        Connection con = DBConnect.getConnection();
        try {
            PreparedStatement pstm = con.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                LopLot ll = new LopLot();
                ll.setId(rs.getInt("IDLOPLOT"));
                ll.setMaLopLot(rs.getString("MALOPLOT"));
                ll.setTenLopLot(rs.getString("TENLOPLOT"));
                ll.setTrangThai(rs.getBoolean("TRANGTHAI"));
                list.add(ll);
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return list;
    }
    
        public LopLot getLLbyMa(String maLL) {
        String sql = "select * from LOPLOT\n"
                + "where TENLOPLOT = ?";
        Connection con = DBConnect.getConnection();
        try {
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, maLL);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                LopLot ll = new LopLot();
                ll.setId(rs.getInt("IDLOPLOT"));
                ll.setMaLopLot(rs.getString("MALOPLOT"));
                ll.setTenLopLot(rs.getString("TENLOPLOT"));
                ll.setTrangThai(rs.getBoolean("TRANGTHAI"));
                return ll;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Integer addLopLot(LopLot ll) {
        Integer row = null;
        String sql = "insert into LOPLOT(MALOPLOT, TENLOPLOT, TRANGTHAI)"
                + "values(?, ?, ?)";
        Connection con = DBConnect.getConnection();
        try {
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, ll.getMaLopLot());
            pstm.setString(2, ll.getTenLopLot());
            pstm.setBoolean(3, ll.isTrangThai());
            row = pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return row;
    }

    public Integer updateLopLot(LopLot ll) {
        Integer row = null;
        String sql = "update LOPLOT\n"
                + "set TRANGTHAI = ?, TENLOPLOT = ?\n"
                + "where MALOPLOT = ?";
        Connection con = DBConnect.getConnection();
        try {
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setBoolean(1, ll.isTrangThai());
            pstm.setString(2, ll.getTenLopLot());
            pstm.setString(3, ll.getMaLopLot());
            row = pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return row;
    }
}
