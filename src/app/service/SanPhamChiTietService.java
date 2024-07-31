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
import app.model.SanPham;
import app.model.SanPhamChiTiet;
import app.model.Size;
import java.util.ArrayList;
import java.sql.*;

/**
 *
 * @author Dat
 */
public class SanPhamChiTietService {
    public ArrayList<SanPhamChiTiet> getAllSPCT() {
        ArrayList<SanPhamChiTiet> list = new ArrayList<>();
        String sql = "SELECT spct.IDSANPHAM, sp.MASP, sp.TENSP, spct.IDMAUSAC, ms.MAMAU, ms.TENMAU\n"
                + "            , spct.IDCHATLIEU, cl.MACHATLIEU, cl.TENCHATLIEU\n"
                + "            , spct.IDLOPLOT, ll.TENLOPLOT, ll.MALOPLOT\n"
                + "            , spct.IDMU, m.MAMU, m.KIEUMU\n"
                + "            , spct.IDSIZE, s.MASIZE, s.TENSIZE\n"
                + "            , spct.IDKIEUDANG, kd.MAKIEUDANG, kd.TENKIEUDANG\n"
                + "            , spct.SOLUONG, spct.MOTA, spct.GIA, spct.TRANGTHAI \n"
                + "FROM SANPHAMCHITIET spct \n"
                + "LEFT JOIN SANPHAM sp ON sp.IDSANPHAM = spct.IDSANPHAM\n"
                + "LEFT JOIN MAUSAC ms ON spct.IDMAUSAC = ms.IDMAUSAC\n"
                + "LEFT JOIN CHATLIEU cl ON spct.IDCHATLIEU = cl.IDCHATLIEU\n"
                + "LEFT JOIN MU m ON spct.IDMU = m.IDMU\n"
                + "LEFT JOIN SIZE s ON spct.IDSIZE = s.IDSIZE\n"
                + "LEFT JOIN LOPLOT ll ON spct.IDLOPLOT = ll.IDLOPLOT\n"
                + "LEFT JOIN KIEUDANG kd ON spct.IDKIEUDANG = kd.IDKIEUDANG";
        Connection con = DBConnect.getConnection();
        try {
            PreparedStatement pstm = con.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {

                SanPham sp = new SanPham();
                sp.setId(rs.getInt("IDSANPHAM"));
                sp.setMaSP(rs.getString("MASP"));
                sp.setTenSP(rs.getString("TENSP"));

                MauSac ms = new MauSac();
                ms.setId(rs.getInt("IDMAUSAC"));
                //ms.setMaMauSac(rs.getString("MAMAU"));
                ms.setTenMauSac(rs.getString("TENMAU"));

                ChatLieu cl = new ChatLieu();
                cl.setId(rs.getInt("IDCHATLIEU"));
                cl.setMaChatLieu(rs.getString("MACHATLIEU"));
                //cl.setTenChatLieu(rs.getString("TENCHATLIEU"));

                LopLot ll = new LopLot();
                ll.setId(rs.getInt("IDLOPLOT"));
                //ll.setMaLopLot(rs.getString("MALOPLOT"));
                ll.setTenLopLot(rs.getString("TENLOPLOT"));

                Mu m = new Mu();
                m.setId(rs.getInt("IDMU"));
                //m.setMaMu(rs.getString("MAMU"));
                m.setTenMu(rs.getString("KIEUMU"));

                Size s = new Size();
                s.setId(rs.getInt("IDSIZE"));
                s.setMaSize(rs.getString("MASIZE"));
                //s.setTenSize(rs.getString("TENSIZE"));

                KieuDang kd = new KieuDang();
                kd.setId(rs.getInt("IDKIEUDANG"));
                //kd.setMaKieuDang(rs.getString("MAKIEUDANG"));
                kd.setTenKieuDang(rs.getString("TENKIEUDANG"));

                SanPhamChiTiet spct = new SanPhamChiTiet();
                spct.setIdSPCT(rs.getInt("IDSPCT")); // Ensure the column exists and the correct name is used
                spct.setSanPham(sp);
                spct.setMauSac(ms);
                spct.setChatLieu(cl);
                spct.setLopLot(ll);
                spct.setMu(m);
                spct.setSize(s);
                spct.setKieuDang(kd);
                spct.setSoLuong(rs.getInt("SOLUONG"));
                spct.setMoTa(rs.getString("MOTA"));
                spct.setGia(rs.getDouble("GIA"));
                spct.setTrangThai(rs.getBoolean("TRANGTHAI"));
                list.add(spct);
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                e.printStackTrace(System.out);
            }
        }
        return list;
    }

//    public boolean addSPCT(SanPhamChiTiet spct) {
//        int check = 0;
//        String sql = "insert into SANPHAMCHITIET(IDSANPHAM, IDMAUSAC, IDCHATLIEU, IDMU, IDSIZE, IDLOPLOT, IDKIEUDANG, MOTA, SOLUONG, GIA, TRANGTHAI)\n"
//                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
//        Connection con = DBConnect.getConnection();
//        try {
//            PreparedStatement pstm = con.prepareStatement(sql);
//            
//            pstm.setInt(1, spct.getSanPham().getId());
//            pstm.setInt(2, spct.getMauSac().getId());
//            pstm.setInt(3, spct.getChatLieu().getId());
//            pstm.setInt(4, spct.getMu().getId());
//            pstm.setInt(5, spct.getSize().getId());
//            pstm.setInt(6, spct.getLopLot().getId());
//            pstm.setInt(7, spct.getKieuDang().getId());
//            pstm.setString(8, spct.getMoTa());
//            pstm.setInt(9, spct.getSoLuong());
//            pstm.setDouble(10, spct.getGia());
//            pstm.setBoolean(11, spct.isTrangThai());
//                check = pstm.executeUpdate();
//        } catch (Exception e) {
//            e.printStackTrace(System.out);
//        } finally {
//            try {
//                if (con != null) {
//                    con.close();
//                }
//            } catch (Exception e) {
//                e.printStackTrace(System.out);
//            }
//        }
//        return check > 0;
//    }
    public ArrayList<SanPhamChiTiet> getAllSPCTHD() {
        ArrayList<SanPhamChiTiet> list = new ArrayList<>();
        String sql = "SELECT spct.IDSPCT, spct.IDSANPHAM, sp.MASP, sp.TENSP, spct.IDMAUSAC, ms.MAMAU, ms.TENMAU\n"
                + ", spct.IDCHATLIEU, cl.MACHATLIEU, cl.TENCHATLIEU\n"
                + ", spct.IDLOPLOT, ll.TENLOPLOT, ll.MALOPLOT\n"
                + ", spct.IDMU, m.MAMU, m.KIEUMU\n"
                + ", spct.IDSIZE, s.MASIZE, s.TENSIZE\n"
                + ", spct.IDKIEUDANG, kd.MAKIEUDANG, kd.TENKIEUDANG\n"
                + ", spct.SOLUONG, spct.MOTA, spct.GIA, spct.TRANGTHAI \n"
                + "FROM SANPHAMCHITIET spct \n"
                + "LEFT JOIN SANPHAM sp ON sp.IDSANPHAM = spct.IDSANPHAM\n"
                + "LEFT JOIN MAUSAC ms ON spct.IDMAUSAC = ms.IDMAUSAC\n"
                + "LEFT JOIN CHATLIEU cl ON spct.IDCHATLIEU = cl.IDCHATLIEU\n"
                + "LEFT JOIN MU m ON spct.IDMU = m.IDMU\n"
                + "LEFT JOIN SIZE s ON spct.IDSIZE = s.IDSIZE\n"
                + "LEFT JOIN LOPLOT ll ON spct.IDLOPLOT = ll.IDLOPLOT\n"
                + "LEFT JOIN KIEUDANG kd ON spct.IDKIEUDANG = kd.IDKIEUDANG\n"
                + "where spct.TRANGTHAI = 1";
        Connection con = DBConnect.getConnection();
        try {
            PreparedStatement pstm = con.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {

                SanPham sp = new SanPham();
                sp.setId(rs.getInt("IDSANPHAM"));
                sp.setMaSP(rs.getString("MASP"));
                sp.setTenSP(rs.getString("TENSP"));

                MauSac ms = new MauSac();
                ms.setId(rs.getInt("IDMAUSAC"));
                //ms.setMaMauSac(rs.getString("MAMAU"));
                ms.setTenMauSac(rs.getString("TENMAU"));

                ChatLieu cl = new ChatLieu();
                cl.setId(rs.getInt("IDCHATLIEU"));
                cl.setMaChatLieu(rs.getString("MACHATLIEU"));
                //cl.setTenChatLieu(rs.getString("TENCHATLIEU"));

                LopLot ll = new LopLot();
                ll.setId(rs.getInt("IDLOPLOT"));
                //ll.setMaLopLot(rs.getString("MALOPLOT"));
                ll.setTenLopLot(rs.getString("TENLOPLOT"));

                Mu m = new Mu();
                m.setId(rs.getInt("IDMU"));
                //m.setMaMu(rs.getString("MAMU"));
                m.setTenMu(rs.getString("KIEUMU"));

                Size s = new Size();
                s.setId(rs.getInt("IDSIZE"));
                s.setMaSize(rs.getString("MASIZE"));
                //s.setTenSize(rs.getString("TENSIZE"));

                KieuDang kd = new KieuDang();
                kd.setId(rs.getInt("IDKIEUDANG"));
                //kd.setMaKieuDang(rs.getString("MAKIEUDANG"));
                kd.setTenKieuDang(rs.getString("TENKIEUDANG"));

                SanPhamChiTiet spct = new SanPhamChiTiet();
                spct.setIdSPCT(rs.getInt("IDSPCT")); // Ensure the column exists and the correct name is used
                spct.setSanPham(sp);
                spct.setMauSac(ms);
                spct.setChatLieu(cl);
                spct.setLopLot(ll);
                spct.setMu(m);
                spct.setSize(s);
                spct.setKieuDang(kd);
                spct.setSoLuong(rs.getInt("SOLUONG"));
                spct.setMoTa(rs.getString("MOTA"));
                spct.setGia(rs.getDouble("GIA"));
//                spct.setTrangThai(rs.getBoolean("TRANGTHAI"));
                list.add(spct);
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                e.printStackTrace(System.out);
            }
        }
        return list;
    }

    public Integer getIDSP(String maSP) {
        String sql = "SELECT IDSANPHAM FROM SANPHAM WHERE MASP LIKE ?";
        Connection con = DBConnect.getConnection();
        try {
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, "%" + maSP + "%");  // Thêm ký tự đại diện nếu cần
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                return rs.getInt("IDSANPHAM");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<SanPhamChiTiet> getSPCTByIDSP(String maSP) {
        ArrayList<SanPhamChiTiet> list = new ArrayList<>();
        String sql = "SELECT spct.IDSANPHAM, sp.MASP, sp.TENSP, spct.IDMAUSAC, ms.MAMAU, ms.TENMAU\n"
                + ", spct.IDCHATLIEU, cl.MACHATLIEU, cl.TENCHATLIEU\n"
                + ", spct.IDLOPLOT, ll.TENLOPLOT, ll.MALOPLOT\n"
                + ", spct.IDMU, m.MAMU, m.KIEUMU\n"
                + ", spct.IDSIZE, s.MASIZE, s.TENSIZE\n"
                + ", spct.IDKIEUDANG, kd.MAKIEUDANG, kd.TENKIEUDANG\n"
                + ", spct.SOLUONG, spct.MOTA, spct.GIA, spct.TRANGTHAI \n"
                + "FROM SANPHAMCHITIET spct \n"
                + "LEFT JOIN SANPHAM sp ON sp.IDSANPHAM = spct.IDSANPHAM\n"
                + "LEFT JOIN MAUSAC ms ON spct.IDMAUSAC = ms.IDMAUSAC\n"
                + "LEFT JOIN CHATLIEU cl ON spct.IDCHATLIEU = cl.IDCHATLIEU\n"
                + "LEFT JOIN MU m ON spct.IDMU = m.IDMU\n"
                + "LEFT JOIN SIZE s ON spct.IDSIZE = s.IDSIZE\n"
                + "LEFT JOIN LOPLOT ll ON spct.IDLOPLOT = ll.IDLOPLOT\n"
                + "LEFT JOIN KIEUDANG kd ON spct.IDKIEUDANG = kd.IDKIEUDANG\n"
                + "where sp.MASP = ?";
        Connection con = DBConnect.getConnection();
        try {
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, maSP);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                SanPham sp = new SanPham();
                sp.setId(rs.getInt("IDSANPHAM"));
                sp.setMaSP(rs.getString("MASP"));
                sp.setTenSP(rs.getString("TENSP"));

                MauSac ms = new MauSac();
                ms.setId(rs.getInt("IDMAUSAC"));
                //ms.setMaMauSac(rs.getString("MAMAU"));
                ms.setTenMauSac(rs.getString("TENMAU"));

                ChatLieu cl = new ChatLieu();
                cl.setId(rs.getInt("IDCHATLIEU"));
                cl.setMaChatLieu(rs.getString("MACHATLIEU"));
                //cl.setTenChatLieu(rs.getString("TENCHATLIEU"));

                LopLot ll = new LopLot();
                ll.setId(rs.getInt("IDLOPLOT"));
                //ll.setMaLopLot(rs.getString("MALOPLOT"));
                ll.setTenLopLot(rs.getString("TENLOPLOT"));

                Mu m = new Mu();
                m.setId(rs.getInt("IDMU"));
                //m.setMaMu(rs.getString("MAMU"));
                m.setTenMu(rs.getString("KIEUMU"));

                Size s = new Size();
                s.setId(rs.getInt("IDSIZE"));
                s.setMaSize(rs.getString("MASIZE"));
                //s.setTenSize(rs.getString("TENSIZE"));

                KieuDang kd = new KieuDang();
                kd.setId(rs.getInt("IDKIEUDANG"));
                //kd.setMaKieuDang(rs.getString("MAKIEUDANG"));
                kd.setTenKieuDang(rs.getString("TENKIEUDANG"));

                SanPhamChiTiet spct = new SanPhamChiTiet();
                spct.setIdSPCT(rs.getInt("IDSPCT")); // Ensure the column exists and the correct name is used
                spct.setSanPham(sp);
                spct.setMauSac(ms);
                spct.setChatLieu(cl);
                spct.setLopLot(ll);
                spct.setMu(m);
                spct.setSize(s);
                spct.setKieuDang(kd);
                spct.setSoLuong(rs.getInt("SOLUONG"));
                spct.setMoTa(rs.getString("MOTA"));
                spct.setGia(rs.getDouble("GIA"));
                spct.setTrangThai(rs.getBoolean("TRANGTHAI"));

                list.add(spct);
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }

        return list;
    }

    public Integer addSPCT(SanPhamChiTiet spct) {
        Integer row = null;
        String sql = "insert into SANPHAMCHITIET(IDSANPHAM, IDMAUSAC, IDCHATLIEU, IDMU, IDSIZE, IDLOPLOT, IDKIEUDANG, SOLUONG, GIA, MOTA, TRANGTHAI)\n"
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Connection con = DBConnect.getConnection();
        try {
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setInt(1, spct.getSanPham().getId());
            pstm.setInt(2, spct.getMauSac().getId());
            pstm.setInt(3, spct.getChatLieu().getId());
            pstm.setInt(4, spct.getMu().getId());
            pstm.setInt(5, spct.getSize().getId());
            pstm.setInt(6, spct.getLopLot().getId());
            pstm.setInt(7, spct.getKieuDang().getId());
            pstm.setInt(8, spct.getSoLuong());
            pstm.setDouble(9, spct.getGia());
            pstm.setString(10, spct.getMoTa());
            pstm.setBoolean(11, spct.isTrangThai());
            row = pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }

    public Integer updateSPCT(SanPhamChiTiet spct) {
        Integer row = null;
        String sql = "update SANPHAMCHITIET\n"
                + "set IDMAUSAC = ?, IDCHATLIEU = ?, IDMU = ?, IDSIZE = ?,  IDLOPLOT = ?, IDKIEUDANG = ?, SOLUONG = ?,\n"
                + "GIA = ?, MOTA = ?, TRANGTHAI = ?\n"
                + "where IDSPCT = ?";
        Connection con = DBConnect.getConnection();
        try {
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setInt(11, spct.getIdSPCT());
            pstm.setInt(1, spct.getMauSac().getId());
            pstm.setInt(2, spct.getChatLieu().getId());
            pstm.setInt(3, spct.getMu().getId());
            pstm.setInt(4, spct.getSize().getId());
            pstm.setInt(5, spct.getLopLot().getId());
            pstm.setInt(6, spct.getKieuDang().getId());
            pstm.setInt(7, spct.getSoLuong());
            pstm.setDouble(8, spct.getGia());
            pstm.setString(9, spct.getMoTa());
            pstm.setBoolean(10, spct.isTrangThai());
            row = pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }

    public Integer getIDSPCTByIDSP(Integer idSP) {
        String sql = "select IDSPCT\n"
                + "from SANPHAMCHITIET\n"
                + "where IDSANPHAM = ?";
        Connection con = DBConnect.getConnection();
        try {
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setInt(1, idSP);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                SanPhamChiTiet spct = new SanPhamChiTiet();
                return rs.getInt("IDSPCT");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        for (SanPhamChiTiet spct : new SanPhamChiTietService().getSPCTByIDSP("SP001")) {
            System.out.println(spct);
        }
    }
}
