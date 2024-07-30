/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.service;

import app.model.HoaDon;
import app.model.SanPham;
import app.model.SanPhamChiTiet;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Dat
 */
public class BanHangService {

    public ArrayList<HoaDon> getAllHDC() {
        ArrayList<HoaDon> list = new ArrayList<>();
        String sql = "select * from HOADON\n"
                + "where TRANGTHAI = 0";
        Connection con = DBConnect.getConnection();
        try {
            PreparedStatement pstm = con.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                HoaDon hd = new HoaDon();
                hd.setMaHoaDon(rs.getString("MAHOADON"));
                hd.setTongTien(rs.getDouble("TONGTIEN"));
                hd.setNgayTao(rs.getDate("NGAYTAO"));
                hd.setTrangThai(rs.getBoolean("TRANGTHAI"));
                list.add(hd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public Integer addHDC(HoaDon hd) {
        Integer row = null;
        String sql = "INSERT INTO HOADON (MAHOADON, TONGTIEN, NGAYTAO, TRANGTHAI)\n"
                + "VALUES (LEFT(CONVERT(varchar(50), NEWID()), 6), 0, GETDATE(), 0)";
        Connection con = DBConnect.getConnection();
        try {
            PreparedStatement pstm = con.prepareStatement(sql);
            row = pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }

    public Integer deleteHDC(String maHDC) {
        Integer row = null;
        String sql = "DELETE FROM HOADON\n"
                + "where TRANGTHAI = 0 AND MAHOADON = ?";
        Connection con = DBConnect.getConnection();
        try {
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, maHDC);
            row = pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return row;
    }
   

}
