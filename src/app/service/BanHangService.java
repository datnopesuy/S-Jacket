/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.service;

import app.model.GioHang;
import app.model.HoaDon;
import app.model.HoaDonChiTiet;
import app.model.HoaDonChiTiet1;
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
                hd.setIdKhachHang(rs.getInt("IDKHACHHANG"));
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

    public Integer addHDCT(HoaDonChiTiet1 hdct, int soLuong) {
        Integer row = null;
        String sql = "insert into HOADONCHITIET(IDHOADON, IDSPCT, SOLUONG, GIA, THANHTIEN)\n"
                + "values(?, ?, ?, ?, ?)";
        Connection con = DBConnect.getConnection();
        try {
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setInt(1, hdct.getHoaDon().getIdHoaDon());
            pstm.setInt(2, hdct.getSanPhamChiTiet().getIdSPCT());
            pstm.setInt(3, soLuong);
            pstm.setDouble(4, hdct.getGia());
            pstm.setDouble(5, hdct.getThanhTien());
            row = pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }

//    public ArrayList<HoaDonChiTiet> getHDCTByMaHD(String maHD) {
//        ArrayList<HoaDonChiTiet> list = new ArrayList<>();
//        String sql = "select hdct.IDHDCT, hd.IDHOADON, hdct.IDSPCT, hdct.SOLUONG, hdct.SOLUONG * hdct.GIA as THANHTIEN\n"
//                + "from HOADONCHITIET hdct \n"
//                + "join HOADON hd on hd.IDHOADON = hdct.IDHOADON\n"
//                + "join SANPHAMCHITIET spct on  spct.IDSPCT = hdct.IDSPCT \n"
//                + "join SANPHAM sp on sp.IDSANPHAM = spct.IDSANPHAM\n"
//                + "join MAUSAC ms on ms.IDMAUSAC = spct.IDMAUSAC\n"
//                + "join CHATLIEU cl on cl.IDCHATLIEU = spct.IDCHATLIEU\n"
//                + "join LOPLOT ll on ll.IDLOPLOT = spct.IDLOPLOT\n"
//                + "join MU m on m.IDMU = spct.IDMU\n"
//                + "join SIZE s on s.IDSIZE = spct.IDSIZE\n"
//                + "join KIEUDANG kd on kd.IDKIEUDANG = spct.IDKIEUDANG\n"
//                + "where hd.MAHOADON = ?";
//        Connection con = DBConnect.getConnection();
//        try {
//            PreparedStatement pstm = con.prepareStatement(sql);
//            pstm.setString(1, maHD);
//            ResultSet rs = pstm.executeQuery();
//            while (rs.next()) {
//                HoaDonChiTiet hdct = new HoaDonChiTiet();
//                hdct.setIDHoaDonChiTiet(rs.getInt("IDHDCT"));
//                hdct.setIDHoaDon(rs.getInt("IDHOADON"));
//                hdct.setIDHSanPhamChiTiet(rs.getInt("IDSPCT"));
//                hdct.setSoLuong(rs.getInt("SOLUONG"));
//                hdct.setGia(rs.getDouble("GIA"));
//                hdct.setThanhTien(rs.getDouble("THANHTIEN"));
//                list.add(hdct);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return list;
//    }
//    public Integer addHDCTBySL(HoaDonChiTiet hdct, int soLuong) {
//        Integer row = null;
//        String sql = "insert into HOADONCHITIET(IDHOADON, IDSPCT, SOLUONG, GIA, THANHTIEN)\n"
//                + "values(?, ?, ?, ?, ?)";
//        Connection con = DBConnect.getConnection();
//        try {
//            PreparedStatement pstm = con.prepareStatement(sql);
//            pstm.setInt(1, hdct.getIDHoaDon());
//            pstm.setInt(2, hdct.getIDHSanPhamChiTiet());
//            pstm.setInt(3, soLuong);
//            pstm.setDouble(4, hdct.getGia());
//            pstm.setDouble(5, hdct.getThanhTien());
//            row = pstm.executeUpdate();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//    
//    public HoaDon getHoaDonByMaHD(String maHD){
//        String sql = "select * from HOADON"
//                + "where MAHOADON = ?";
//        Connection con = DBConnect.getConnection();
//        try {
//            PreparedStatement pstm = con.prepareStatement(sql);
//            pstm.setString(1, maHD);
//            ResultSet rs = pstm.executeQuery();
//            while(rs.next()){
//                HoaDon hd = new HoaDon();
//                hd.setIdHoaDon(rs.getInt("IDHOADON"));
//                hd.setMaHoaDon(maHD);
//                hd.setTongTien(rs.getDouble("TONGTIEN"));
//                hd.setTrangThai(rs.getBoolean("TRANGTHAI"));
//                return hd;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
}
