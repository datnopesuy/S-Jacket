/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.service;

import app.model.ChatLieu;
import app.model.GioHang;
import app.model.HoaDon;
import app.model.HoaDonChiTiet;
import app.model.HoaDonChiTiet1;
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
public class GioHangService {

    public ArrayList<GioHang> getGioHangByMaHD(String maHD) {
        ArrayList<GioHang> list = new ArrayList<>();
        String sql = "SELECT hdct.IDSPCT,\n"
                + "       hdct.IDHDCT, hd.IDHOADON, hd.MAHOADON,\n"
                + "       sp.TENSP, sp.IDSANPHAM, sp.MASP,\n"
                + "       ms.TENMAU, ms.IDMAUSAC, ms.MAMAU,\n"
                + "       cl.MACHATLIEU, cl.IDCHATLIEU, cl.TENCHATLIEU,\n"
                + "       ll.TENLOPLOT, ll.IDLOPLOT, ll.MALOPLOT,\n"
                + "       m.KIEUMU, m.IDMU, m.MAMU,\n"
                + "       s.MASIZE, s.IDSIZE, s.TENSIZE,\n"
                + "       kd.TENKIEUDANG, kd.IDKIEUDANG, kd.MAKIEUDANG,\n"
                + "       hdct.SOLUONG, hdct.SOLUONG * hdct.GIA AS THANHTIEN\n"
                + "FROM HOADONCHITIET hdct\n"
                + "JOIN HOADON hd ON hd.IDHOADON = hdct.IDHOADON\n"
                + "JOIN SANPHAMCHITIET spct ON spct.IDSPCT = hdct.IDSPCT\n"
                + "JOIN SANPHAM sp ON sp.IDSANPHAM = spct.IDSANPHAM\n"
                + "JOIN MAUSAC ms ON ms.IDMAUSAC = spct.IDMAUSAC\n"
                + "JOIN CHATLIEU cl ON cl.IDCHATLIEU = spct.IDCHATLIEU\n"
                + "JOIN LOPLOT ll ON ll.IDLOPLOT = spct.IDLOPLOT\n"
                + "JOIN MU m ON m.IDMU = spct.IDMU\n"
                + "JOIN SIZE s ON s.IDSIZE = spct.IDSIZE\n"
                + "JOIN KIEUDANG kd ON kd.IDKIEUDANG = spct.IDKIEUDANG\n"
                + "where hd.MAHOADON = ?";
        Connection con = DBConnect.getConnection();
        try {
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, maHD);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                HoaDon hd = new HoaDon();
                hd.setIdHoaDon(rs.getInt("IDHOADON"));
                hd.setMaHoaDon(rs.getString("MAHOADON"));

                HoaDonChiTiet hdct = new HoaDonChiTiet();
                hdct.setIDHSanPhamChiTiet(rs.getInt("IDHDCT"));

                SanPham sp = new SanPham();
                sp.setId(rs.getInt("IDSANPHAM"));
                sp.setMaSP(rs.getString("MASP"));
                sp.setTenSP(rs.getString("TENSP"));

                SanPhamChiTiet spct = new SanPhamChiTiet();
                spct.setIdSPCT(rs.getInt("IDSPCT"));

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

                GioHang gh = new GioHang();
                gh.setChatLieu(cl);
                gh.setMauSac(ms);
                gh.setSize(s);
                gh.setLopLot(ll);
                gh.setKieuDang(kd);
                gh.setMu(m);
                gh.setHoaDon(hd);
                gh.setHoaDonChiTiet(hdct);
                gh.setSanPham(sp);
                gh.setSanPhamChiTiet(spct);
                gh.setSoLuong(rs.getInt("SOLUONG"));
                //gh.setGia(rs.getDouble("GIA"));
                gh.setThanhTien(rs.getDouble("THANHTIEN"));
                list.add(gh);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public Integer addGH(HoaDonChiTiet1 hdct, int soLuongMua, int idHoaDon) {
        Integer row = null;
        String sql = "insert into HOADONCHITIET(IDHOADON, IDSPCT, SOLUONG, GIA, THANHTIEN)\n"
                + "values(?, ?, ?, ?, ?)";
        Connection con = DBConnect.getConnection();
        try {
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setInt(1, idHoaDon);
            pstm.setInt(2, hdct.getSanPhamChiTiet().getIdSPCT());
            pstm.setInt(3, hdct.getSoLuong());
            pstm.setDouble(4, hdct.getGia());
            pstm.setDouble(5, hdct.getThanhTien());
            row = pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }

    public Integer updateSLGH(HoaDonChiTiet1 hdct, int soLuongSua, int idHoaDon) {
        Integer row = null;
        String sql = "update HOADONCHITIET \n"
                + "set SOLUONG = ?\n"
                + "where IDSPCT = ? and IDHOADON = ?";
        Connection con = DBConnect.getConnection();
        try {
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setInt(1, soLuongSua);
            pstm.setInt(2, hdct.getSanPhamChiTiet().getIdSPCT());
            pstm.setInt(3, idHoaDon);
            row = pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }

    public Integer deleteGH(HoaDonChiTiet1 hdct, int idHoaDon, int idSPCT) {
        Integer row = null;
        String sql = "delete from HOADONCHITIET\n"
                + " where IDHOADON = ? and IDSPCT = ?";
        Connection con = DBConnect.getConnection();
        try {
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setInt(1, idHoaDon);
            pstm.setInt(2, idSPCT);
            row = pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }

}
