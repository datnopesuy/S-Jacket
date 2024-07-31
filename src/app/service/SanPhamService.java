/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.service;

import app.model.ChatLieu;
import app.model.KieuDang;
import app.model.LopLot;
import app.model.MauSac;
import app.model.Mu;
import app.service.DBConnect;
import java.util.ArrayList;
import app.model.SanPham;
import app.model.SanPhamChiTiet;
import app.model.Size;
import java.sql.*;

/**
 *
 * @author Dat
 */
public class SanPhamService {

    public ArrayList<SanPham> getAllSanPham() {
        ArrayList<SanPham> list = new ArrayList<>();
        String sql = "select IDSANPHAM ,MASP, TENSP, TRANGTHAI \n"
                + "from SANPHAM";
        Connection con = DBConnect.getConnection();
        try {
            PreparedStatement pstm = con.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                SanPham sp = new SanPham();
                sp.setId(rs.getInt("IDSANPHAM"));
                sp.setMaSP(rs.getString("MASP"));
                sp.setTenSP(rs.getString("TENSP"));
                sp.setTrangThai(rs.getBoolean("TRANGTHAI"));
                list.add(sp);
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return list;
    }
    
        public SanPham getSanPhambyID(Integer id) {
        String sql = "select IDSANPHAM ,MASP, TENSP, TRANGTHAI \n"
                + "from SANPHAM"
                + "where IDSANPHAM = ?";
        Connection con = DBConnect.getConnection();
        try {
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                SanPham sp = new SanPham();
                sp.setId(rs.getInt("IDSANPHAM"));
                sp.setMaSP(rs.getString("MASP"));
                sp.setTenSP(rs.getString("TENSP"));
                sp.setTrangThai(rs.getBoolean("TRANGTHAI"));
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public Integer addSanPham(SanPham sp) {
        Integer row = null;
        String sql = "insert into SANPHAM(MASP, TENSP, TRANGTHAI)"
                + "values(?, ?, ?)";
        Connection con = DBConnect.getConnection();
        try {
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, sp.getMaSP());
            pstm.setString(2, sp.getTenSP());
            pstm.setBoolean(3, sp.isTrangThai());
            row = pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return row;
    }

    public Integer updateSanPham(SanPham sp) {
        Integer row = null;
        String sql = "update SANPHAM\n"
                + "set TRANGTHAI = ?, TENSP = ?\n"
                + "where MASP = ?";
        Connection con = DBConnect.getConnection();
        try {
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setBoolean(1, sp.isTrangThai());
            pstm.setString(2, sp.getTenSP());
            pstm.setString(3, sp.getMaSP());
            row = pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return row;
    }

    

    public ArrayList<SanPham> searchSP(String texttk) {
        ArrayList<SanPham> list = new ArrayList<>();
        String sql = "select * \n"
                + "from SANPHAM\n"
                + "where MASP like ? or TENSP like ?";
        Connection con = DBConnect.getConnection();
        try {
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, texttk);
            pstm.setString(2, texttk);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                SanPham sp = new SanPham();
                sp.setMaSP(rs.getString("MASP"));
                sp.setTenSP(rs.getString("TENSP"));
                sp.setTrangThai(rs.getBoolean("TRANGTHAI"));
                list.add(sp);
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return list;
    }

    public ArrayList<SanPham> getAllSPHD(String texttk) {
        ArrayList<SanPham> list = new ArrayList<>();
        String sql = "select * \n"
                + "from SANPHAM\n"
                + "where TRANGTHAI = 'true'";
        if (texttk.length() != 0) {
            sql += "AND (MASP like ? or TENSP like ?)";
        }
        Connection con = DBConnect.getConnection();
        try {
            PreparedStatement pstm = con.prepareStatement(sql);
            //pstm.setBoolean(1, trangThai);
            if (texttk.length() != 0) {
                pstm.setString(1, "%" + texttk + "%");
                pstm.setString(2, "%" + texttk + "%");
            }
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                SanPham sp = new SanPham();
                sp.setMaSP(rs.getString("MASP"));
                sp.setTenSP(rs.getString("TENSP"));
                sp.setTrangThai(rs.getBoolean("TRANGTHAI"));
                list.add(sp);
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return list;
    }

    public ArrayList<SanPham> getAllSPNHD(String texttk) {
        ArrayList<SanPham> list = new ArrayList<>();
        String sql = "select * \n"
                + "from SANPHAM\n"
                + "where TRANGTHAI = 'false'";
        if (texttk.length() != 0) {
            sql += "AND (MASP like ? or TENSP like ?)";
        }
        Connection con = DBConnect.getConnection();
        try {
            PreparedStatement pstm = con.prepareStatement(sql);
            //pstm.setBoolean(1, trangThai);
            if (texttk.length() != 0) {
                pstm.setString(1, "%" + texttk + "%");
                pstm.setString(2, "%" + texttk + "%");
            }
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                SanPham sp = new SanPham();
                sp.setMaSP(rs.getString("MASP"));
                sp.setTenSP(rs.getString("TENSP"));
                sp.setTrangThai(rs.getBoolean("TRANGTHAI"));
                list.add(sp);
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return list;
    }

    public static void main(String[] args) {
        for (SanPham sp : new SanPhamService().getAllSanPham()) {
            System.out.println(sp);
        }
    }
}
