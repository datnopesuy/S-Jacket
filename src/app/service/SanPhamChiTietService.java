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
import javax.swing.JOptionPane;

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
                spct.setIdSPCT(rs.getInt("IDSANPHAM")); // Ensure the column exists and the correct name is used
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

    public ArrayList<SanPhamChiTiet> getAllSPCTHD(String text) {
        ArrayList<SanPhamChiTiet> list = new ArrayList<>();
        String sql = "SELECT spct.IDSANPHAM, sp.MASP, sp.TENSP, spct.IDMAUSAC, ms.MAMAU, ms.TENMAU,\n"
                + "       spct.IDCHATLIEU, cl.MACHATLIEU, cl.TENCHATLIEU,\n"
                + "       spct.IDLOPLOT, ll.TENLOPLOT, ll.MALOPLOT,\n"
                + "       spct.IDMU, m.MAMU, m.KIEUMU,\n"
                + "       spct.IDSIZE, s.MASIZE, s.TENSIZE,\n"
                + "       spct.IDKIEUDANG, kd.MAKIEUDANG, kd.TENKIEUDANG,\n"
                + "       spct.SOLUONG, spct.MOTA, spct.GIA, spct.TRANGTHAI\n"
                + "FROM SANPHAMCHITIET spct\n"
                + "LEFT JOIN SANPHAM sp ON sp.IDSANPHAM = spct.IDSANPHAM\n"
                + "LEFT JOIN MAUSAC ms ON spct.IDMAUSAC = ms.IDMAUSAC\n"
                + "LEFT JOIN CHATLIEU cl ON spct.IDCHATLIEU = cl.IDCHATLIEU\n"
                + "LEFT JOIN MU m ON spct.IDMU = m.IDMU\n"
                + "LEFT JOIN SIZE s ON spct.IDSIZE = s.IDSIZE\n"
                + "LEFT JOIN LOPLOT ll ON spct.IDLOPLOT = ll.IDLOPLOT\n"
                + "LEFT JOIN KIEUDANG kd ON spct.IDKIEUDANG = kd.IDKIEUDANG\n"
                + "WHERE spct.TRANGTHAI = 1";
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
                spct.setIdSPCT(rs.getInt("IDSANPHAM")); // Ensure the column exists and the correct name is used
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

    public ArrayList<SanPhamChiTiet> getAllSPCTKHD(String text) {
        ArrayList<SanPhamChiTiet> list = new ArrayList<>();
        String sql = "SELECT spct.IDSANPHAM, sp.MASP, sp.TENSP, spct.IDMAUSAC, ms.MAMAU, ms.TENMAU,\n"
                + "       spct.IDCHATLIEU, cl.MACHATLIEU, cl.TENCHATLIEU,\n"
                + "       spct.IDLOPLOT, ll.TENLOPLOT, ll.MALOPLOT,\n"
                + "       spct.IDMU, m.MAMU, m.KIEUMU,\n"
                + "       spct.IDSIZE, s.MASIZE, s.TENSIZE,\n"
                + "       spct.IDKIEUDANG, kd.MAKIEUDANG, kd.TENKIEUDANG,\n"
                + "       spct.SOLUONG, spct.MOTA, spct.GIA, spct.TRANGTHAI\n"
                + "FROM SANPHAMCHITIET spct\n"
                + "LEFT JOIN SANPHAM sp ON sp.IDSANPHAM = spct.IDSANPHAM\n"
                + "LEFT JOIN MAUSAC ms ON spct.IDMAUSAC = ms.IDMAUSAC\n"
                + "LEFT JOIN CHATLIEU cl ON spct.IDCHATLIEU = cl.IDCHATLIEU\n"
                + "LEFT JOIN MU m ON spct.IDMU = m.IDMU\n"
                + "LEFT JOIN SIZE s ON spct.IDSIZE = s.IDSIZE\n"
                + "LEFT JOIN LOPLOT ll ON spct.IDLOPLOT = ll.IDLOPLOT\n"
                + "LEFT JOIN KIEUDANG kd ON spct.IDKIEUDANG = kd.IDKIEUDANG\n"
                + "WHERE spct.TRANGTHAI = 0";
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
                spct.setIdSPCT(rs.getInt("IDSANPHAM")); // Ensure the column exists and the correct name is used
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
    //Tìm kiếm sản phẩm chi tiết
    public ArrayList<SanPhamChiTiet> searchSPCT(String ma) {
        ArrayList<SanPhamChiTiet> spctList = new ArrayList<>();
        try {
            Connection coon = DBConnect.getConnection();
            String sql = "SELECT spct.IDSANPHAM, sp.MASP, sp.TENSP, spct.IDMAUSAC, ms.MAMAU, ms.TENMAU, "
                    + "spct.IDCHATLIEU, cl.MACHATLIEU, cl.TENCHATLIEU, spct.IDLOPLOT, ll.TENLOPLOT, ll.MALOPLOT, "
                    + "spct.IDMU, m.MAMU, m.KIEUMU, spct.IDSIZE, s.MASIZE, s.TENSIZE, "
                    + "spct.IDKIEUDANG, kd.MAKIEUDANG, kd.TENKIEUDANG, spct.SOLUONG, spct.MOTA, spct.GIA, spct.TRANGTHAI "
                    + "FROM SANPHAMCHITIET spct "
                    + "LEFT JOIN SANPHAM sp ON sp.IDSANPHAM = spct.IDSANPHAM "
                    + "LEFT JOIN MAUSAC ms ON spct.IDMAUSAC = ms.IDMAUSAC "
                    + "LEFT JOIN CHATLIEU cl ON spct.IDCHATLIEU = cl.IDCHATLIEU "
                    + "LEFT JOIN MU m ON spct.IDMU = m.IDMU "
                    + "LEFT JOIN SIZE s ON spct.IDSIZE = s.IDSIZE "
                    + "LEFT JOIN LOPLOT ll ON spct.IDLOPLOT = ll.IDLOPLOT "
                    + "LEFT JOIN KIEUDANG kd ON spct.IDKIEUDANG = kd.IDKIEUDANG "
                    + "WHERE sp.MASP LIKE ? "
                    + "OR sp.TENSP LIKE ? "
                    + "OR ms.TENMAU LIKE ? "
                    + "OR cl.MACHATLIEU LIKE ? "
                    + "OR ll.TENLOPLOT LIKE ? "
                    + "OR m.KIEUMU LIKE ? "
                    + "OR s.MASIZE LIKE ? "
                    + "OR kd.TENKIEUDANG LIKE ?";

            PreparedStatement prsm = coon.prepareStatement(sql);
            prsm.setString(1, "%" + ma + "%");
            prsm.setString(2, "%" + ma + "%");
            prsm.setString(3, "%" + ma + "%");
            prsm.setString(4, "%" + ma + "%");
            prsm.setString(5, "%" + ma + "%");
            prsm.setString(6, "%" + ma + "%");
            prsm.setString(7, "%" + ma + "%");
            prsm.setString(8, "%" + ma + "%");

            ResultSet rs = prsm.executeQuery();
            while (rs.next()) {
                SanPham sp = new SanPham();
                sp.setMaSP(rs.getString("MASP"));
                sp.setTenSP(rs.getString("TENSP"));

                MauSac ms = new MauSac();
                ms.setMaMauSac(rs.getString("MAMAU"));
                ms.setTenMauSac(rs.getString("TENMAU"));

                ChatLieu cl = new ChatLieu();
                cl.setMaChatLieu(rs.getString("MACHATLIEU"));
                cl.setTenChatLieu(rs.getString("TENCHATLIEU"));

                LopLot ll = new LopLot();
                ll.setMaLopLot(rs.getString("MALOPLOT"));
                ll.setTenLopLot(rs.getString("TENLOPLOT"));

                Mu m = new Mu();
                m.setMaMu(rs.getString("MAMU"));
                m.setTenMu(rs.getString("KIEUMU"));

                Size s = new Size();
                s.setMaSize(rs.getString("MASIZE"));
                s.setTenSize(rs.getString("TENSIZE"));

                KieuDang kd = new KieuDang();
                kd.setMaKieuDang(rs.getString("MAKIEUDANG"));
                kd.setTenKieuDang(rs.getString("TENKIEUDANG"));

                SanPhamChiTiet spct = new SanPhamChiTiet();
                spct.setIdSPCT(rs.getInt("IDSANPHAM")); // Đảm bảo rằng IDSANPHAM tồn tại và đúng kiểu dữ liệu
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
                spctList.add(spct);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return spctList;
    }
//Copy phần này nha. Phần này để lọc bên SPCT
    public ArrayList<SanPhamChiTiet> filterSPCT1(String maSanPham, String maMauSac, String maChatLieu, String maSize, String maKieuDang, String maMu, String maLopLot) {
        ArrayList<SanPhamChiTiet> list = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT spct.IDSANPHAM, sp.MASP, sp.TENSP, spct.IDMAUSAC, ms.MAMAU, ms.TENMAU, "
                + "spct.IDCHATLIEU, cl.MACHATLIEU, cl.TENCHATLIEU, spct.IDLOPLOT, ll.TENLOPLOT, ll.MALOPLOT, "
                + "spct.IDMU, m.MAMU, m.KIEUMU, spct.IDSIZE, s.MASIZE, s.TENSIZE, "
                + "spct.IDKIEUDANG, kd.MAKIEUDANG, kd.TENKIEUDANG, spct.SOLUONG, spct.MOTA, spct.GIA, spct.TRANGTHAI "
                + "FROM SANPHAMCHITIET spct "
                + "LEFT JOIN SANPHAM sp ON sp.IDSANPHAM = spct.IDSANPHAM "
                + "LEFT JOIN MAUSAC ms ON spct.IDMAUSAC = ms.IDMAUSAC "
                + "LEFT JOIN CHATLIEU cl ON spct.IDCHATLIEU = cl.IDCHATLIEU "
                + "LEFT JOIN MU m ON spct.IDMU = m.IDMU "
                + "LEFT JOIN SIZE s ON spct.IDSIZE = s.IDSIZE "
                + "LEFT JOIN LOPLOT ll ON spct.IDLOPLOT = ll.IDLOPLOT "
                + "LEFT JOIN KIEUDANG kd ON spct.IDKIEUDANG = kd.IDKIEUDANG "
                + "WHERE (sp.MASP LIKE ? OR ? = N'') "
                + "AND (ms.TENMAU LIKE ? OR ? = N'') "
                + "AND (cl.MACHATLIEU LIKE ? OR ? = N'') "
                + "AND (s.MASIZE LIKE ? OR ? = N'') "
                + "AND (kd.TENKIEUDANG LIKE ? OR ? = N'') "
                + "AND (m.KIEUMU LIKE ? OR ? = N'') "
                + "AND (ll.TENLOPLOT LIKE ? OR ? = N'')");

        Connection con = DBConnect.getConnection();
        try {
            PreparedStatement pstm = con.prepareStatement(sql.toString());

            int index = 1;
            if (maSanPham != null) {
                if (maSanPham.equalsIgnoreCase("Tất cả")) maSanPham = "";
            }
            if (maMauSac != null) {
                if (maMauSac.equalsIgnoreCase("Tất cả")) maMauSac = "";
            }
            if (maChatLieu != null) {
                if (maChatLieu.equalsIgnoreCase("Tất cả")) maChatLieu = "";
            }
            if (maSize != null) {
                if (maSize.equalsIgnoreCase("Tất cả")) maSize = "";
            }
            if (maKieuDang != null) {
                if (maKieuDang.equalsIgnoreCase("Tất cả")) maKieuDang = "";
            }
            if (maMu != null){
                if(maMu.equalsIgnoreCase("Tất cả")) maMu = "";
            }
            if(maLopLot != null){
                if(maLopLot.equalsIgnoreCase("Tất cả")) maLopLot = "";
            }
            // Sử dụng các giá trị từ JComboBox, với giá trị mặc định là chuỗi rỗng nếu không có giá trị được chọn
            pstm.setString(index++, maSanPham != null ? maSanPham : "");
            pstm.setString(index++, maSanPham != null ? maSanPham : "");

            pstm.setString(index++, maMauSac != null ? maMauSac : "");
            pstm.setString(index++, maMauSac != null ? maMauSac : "");

            pstm.setString(index++, maChatLieu != null ? maChatLieu : "");
            pstm.setString(index++, maChatLieu != null ? maChatLieu : "");
//maSanPham, String maMauSac, String maChatLieu, String maSize, String maKieuDang, String maMu, String maLopLot
            pstm.setString(index++, maSize != null ? maSize : "");
            pstm.setString(index++, maSize != null ? maSize : "");

            pstm.setString(index++, maKieuDang != null ? maKieuDang : "");
            pstm.setString(index++, maKieuDang != null ? maKieuDang : "");

            pstm.setString(index++, maMu != null ? maMu : "");
            pstm.setString(index++, maMu != null ? maMu : "");

            pstm.setString(index++, maLopLot != null ? maLopLot : "");
            pstm.setString(index++, maLopLot != null ? maLopLot : "");

            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                SanPham sp = new SanPham();
                sp.setId(rs.getInt("IDSANPHAM"));
                sp.setMaSP(rs.getString("MASP"));
                sp.setTenSP(rs.getString("TENSP"));

                MauSac ms = new MauSac();
                ms.setId(rs.getInt("IDMAUSAC"));
                ms.setMaMauSac(rs.getString("MAMAU"));
                ms.setTenMauSac(rs.getString("TENMAU"));

                ChatLieu cl = new ChatLieu();
                cl.setId(rs.getInt("IDCHATLIEU"));
                cl.setMaChatLieu(rs.getString("MACHATLIEU"));
                cl.setTenChatLieu(rs.getString("TENCHATLIEU"));

                LopLot ll = new LopLot();
                ll.setId(rs.getInt("IDLOPLOT"));
                ll.setMaLopLot(rs.getString("MALOPLOT"));
                ll.setTenLopLot(rs.getString("TENLOPLOT"));

                Mu m = new Mu();
                m.setId(rs.getInt("IDMU"));
                m.setMaMu(rs.getString("MAMU"));
                m.setTenMu(rs.getString("KIEUMU"));

                Size s = new Size();
                s.setId(rs.getInt("IDSIZE"));
                s.setMaSize(rs.getString("MASIZE"));
                s.setTenSize(rs.getString("TENSIZE"));

                KieuDang kd = new KieuDang();
                kd.setId(rs.getInt("IDKIEUDANG"));
                kd.setMaKieuDang(rs.getString("MAKIEUDANG"));
                kd.setTenKieuDang(rs.getString("TENKIEUDANG"));

                SanPhamChiTiet spct = new SanPhamChiTiet();
                spct.setIdSPCT(rs.getInt("IDSANPHAM"));
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
                spct.setIdSPCT(rs.getInt("IDSANPHAM")); // Ensure the column exists and the correct name is used
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
    //Lọc sản phẩm bên bảng bán hàng
    public ArrayList<SanPhamChiTiet> filterSPCT(String maMauSac, String maChatLieu, String maSize, String maLopLot, String maKieuDang, String maMu) {
        ArrayList<SanPhamChiTiet> list = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT spct.IDSANPHAM, sp.MASP, sp.TENSP, spct.IDMAUSAC, ms.MAMAU, ms.TENMAU, "
                + "spct.IDCHATLIEU, cl.MACHATLIEU, cl.TENCHATLIEU, spct.IDLOPLOT, ll.TENLOPLOT, ll.MALOPLOT, "
                + "spct.IDMU, m.MAMU, m.KIEUMU, spct.IDSIZE, s.MASIZE, s.TENSIZE, "
                + "spct.IDKIEUDANG, kd.MAKIEUDANG, kd.TENKIEUDANG, spct.SOLUONG, spct.MOTA, spct.GIA, spct.TRANGTHAI "
                + "FROM SANPHAMCHITIET spct "
                + "LEFT JOIN SANPHAM sp ON sp.IDSANPHAM = spct.IDSANPHAM "
                + "LEFT JOIN MAUSAC ms ON spct.IDMAUSAC = ms.IDMAUSAC "
                + "LEFT JOIN CHATLIEU cl ON spct.IDCHATLIEU = cl.IDCHATLIEU "
                + "LEFT JOIN MU m ON spct.IDMU = m.IDMU "
                + "LEFT JOIN SIZE s ON spct.IDSIZE = s.IDSIZE "
                + "LEFT JOIN LOPLOT ll ON spct.IDLOPLOT = ll.IDLOPLOT "
                + "LEFT JOIN KIEUDANG kd ON spct.IDKIEUDANG = kd.IDKIEUDANG "
                + "WHERE (ms.TENMAU LIKE ? OR ? = N'') "
                + "AND (cl.MACHATLIEU LIKE ? OR ? = N'') "
                + "AND (s.MASIZE LIKE ? OR ? = N'') "
                + "AND (ll.TENLOPLOT LIKE ? OR ? = N'') "
                + "AND (kd.TENKIEUDANG LIKE ? OR ? = N'') "
                + "AND (m.KIEUMU LIKE ? OR ? = N'')");

        Connection con = DBConnect.getConnection();
        try {
            PreparedStatement pstm = con.prepareStatement(sql.toString());

            int index = 1;
            
            if (maMauSac != null) {
                if (maMauSac.equalsIgnoreCase("Tất cả")) maMauSac = "";
            }
            if (maChatLieu != null) {
                if (maChatLieu.equalsIgnoreCase("Tất cả")) maChatLieu = "";
            }
            if (maSize != null) {
                if (maSize.equalsIgnoreCase("Tất cả")) maSize = "";
            }
            if (maKieuDang != null) {
                if (maKieuDang.equalsIgnoreCase("Tất cả")) maKieuDang = "";
            }
            if (maMu != null){
                if(maMu.equalsIgnoreCase("Tất cả")) maMu = "";
            }
            if(maLopLot != null){
                if(maLopLot.equalsIgnoreCase("Tất cả")) maLopLot = "";
            }
            // Sử dụng các giá trị từ JComboBox, với giá trị mặc định là chuỗi rỗng nếu không có giá trị được chọn
            pstm.setString(index++, maMauSac != null ? maMauSac : "%");
            pstm.setString(index++, maMauSac != null ? maMauSac : "%");
            pstm.setString(index++, maChatLieu != null ? maChatLieu : "%");
            pstm.setString(index++, maChatLieu != null ? maChatLieu : "%");
            pstm.setString(index++, maSize != null ? maSize : "%");
            pstm.setString(index++, maSize != null ? maSize : "%");
            pstm.setString(index++, maLopLot != null ? maLopLot : "%");
            pstm.setString(index++, maLopLot != null ? maLopLot : "%");
            pstm.setString(index++, maKieuDang != null ? maKieuDang : "%");
            pstm.setString(index++, maKieuDang != null ? maKieuDang : "%");
            pstm.setString(index++, maMu != null ? maMu : "%");
            pstm.setString(index++, maMu != null ? maMu : "%");

            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                SanPham sp = new SanPham();
                sp.setId(rs.getInt("IDSANPHAM"));
                sp.setMaSP(rs.getString("MASP"));
                sp.setTenSP(rs.getString("TENSP"));

                MauSac ms = new MauSac();
                ms.setId(rs.getInt("IDMAUSAC"));
                ms.setMaMauSac(rs.getString("MAMAU"));
                ms.setTenMauSac(rs.getString("TENMAU"));

                ChatLieu cl = new ChatLieu();
                cl.setId(rs.getInt("IDCHATLIEU"));
                cl.setMaChatLieu(rs.getString("MACHATLIEU"));
                cl.setTenChatLieu(rs.getString("TENCHATLIEU"));

                LopLot ll = new LopLot();
                ll.setId(rs.getInt("IDLOPLOT"));
                ll.setMaLopLot(rs.getString("MALOPLOT"));
                ll.setTenLopLot(rs.getString("TENLOPLOT"));

                Mu m = new Mu();
                m.setId(rs.getInt("IDMU"));
                m.setMaMu(rs.getString("MAMU"));
                m.setTenMu(rs.getString("KIEUMU"));

                Size s = new Size();
                s.setId(rs.getInt("IDSIZE"));
                s.setMaSize(rs.getString("MASIZE"));
                s.setTenSize(rs.getString("TENSIZE"));

                KieuDang kd = new KieuDang();
                kd.setId(rs.getInt("IDKIEUDANG"));
                kd.setMaKieuDang(rs.getString("MAKIEUDANG"));
                kd.setTenKieuDang(rs.getString("TENKIEUDANG"));

                SanPhamChiTiet spct = new SanPhamChiTiet();
                spct.setIdSPCT(rs.getInt("IDSANPHAM"));
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

    public static void main(String[] args) {
        for (SanPhamChiTiet spct : new SanPhamChiTietService().getSPCTByIDSP("SP001")) {
            System.out.println(spct);
        }
    }
}
