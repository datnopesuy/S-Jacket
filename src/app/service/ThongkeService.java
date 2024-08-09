/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.service;

import app.fpt.utils.JDBCHelper;
import app.model.ChatLieu;
import app.model.KieuDang;
import app.model.LopLot;
import app.model.MauSac;
import app.model.Mu;
import app.model.SanPham;
import java.sql.*;
import app.model.SanPhamChiTiet;
import app.model.Size;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class ThongkeService {

    String getcoutSP = "select COUNT(*) as 'id' from SANPHAM";
    String getcoutNV = "select COUNT(*) as 'id' from NHANVIEN";
    String getcoutKH = "select COUNT(*) as 'id' from KHACHHANG";
    String getsumDT = "select SUM(TONGTIEN) as 'id' from HOADON where TRANGTHAI = 1";
    String getday = "select DAY(NGAYTHANHTOAN)\n"
            + "from HOADON join HOADONCHITIET on HOADON.IDHOADON = HOADONCHITIET.IDHOADON \n"
            + "where TRANGTHAI = 1 and MONTH(NGAYTHANHTOAN) = ? and YEAR(NGAYTHANHTOAN) = ?\n"
            + "group by DAY(NGAYTHANHTOAN)";
    String getmonth = "select MONTH(NGAYTHANHTOAN)\n"
            + "from HOADON join HOADONCHITIET on HOADON.IDHOADON = HOADONCHITIET.IDHOADON \n"
            + "where TRANGTHAI = 1 and YEAR(NGAYTHANHTOAN) = ?\n"
            + "group by MONTH(NGAYTHANHTOAN)";
    String getyear = "select YEAR(NGAYTHANHTOAN)\n"
            + "from HOADON join HOADONCHITIET on HOADON.IDHOADON = HOADONCHITIET.IDHOADON \n"
            + "where TRANGTHAI = 1\n"
            + "group by YEAR(NGAYTHANHTOAN)";
    String getSLandDTNgay = "select SUM(SOLUONG),SUM(TONGTIEN)\n"
            + "from HOADON join HOADONCHITIET on HOADON.IDHOADON = HOADONCHITIET.IDHOADON \n"
            + "where TRANGTHAI = 1 and MONTH(NGAYTHANHTOAN) = ? and YEAR(NGAYTHANHTOAN) = ? and DAY(NGAYTHANHTOAN) = ?";
    String getSLandDTThang = "select SUM(SOLUONG),SUM(TONGTIEN)\n"
            + "from HOADON join HOADONCHITIET on HOADON.IDHOADON = HOADONCHITIET.IDHOADON \n"
            + "where TRANGTHAI = 1 and MONTH(NGAYTHANHTOAN) = ? and YEAR(NGAYTHANHTOAN) = ?";
    String getSLandDTNam = "select SUM(SOLUONG),SUM(TONGTIEN)\n"
            + "from HOADON join HOADONCHITIET on HOADON.IDHOADON = HOADONCHITIET.IDHOADON \n"
            + "where TRANGTHAI = 1 and YEAR(NGAYTHANHTOAN) = ?";
    String getSLSPNgay = "select HOADON.IDHOADON\n"
            + "from HOADON join HOADONCHITIET on HOADON.IDHOADON = HOADONCHITIET.IDHOADON \n"
            + "where TRANGTHAI = 1 and MONTH(NGAYTHANHTOAN) = ? and YEAR(NGAYTHANHTOAN) = ? and DAY(NGAYTHANHTOAN) = ?\n"
            + "group by HOADON.IDHOADON";
    String getSLSPThang = "select HOADON.IDHOADON\n"
            + "from HOADON join HOADONCHITIET on HOADON.IDHOADON = HOADONCHITIET.IDHOADON \n"
            + "where TRANGTHAI = 1 and MONTH(NGAYTHANHTOAN) = ? and YEAR(NGAYTHANHTOAN) = ?\n"
            + "group by HOADON.IDHOADON";
    String getSLSPNam = "select HOADON.IDHOADON\n"
            + "from HOADON join HOADONCHITIET on HOADON.IDHOADON = HOADONCHITIET.IDHOADON \n"
            + "where TRANGTHAI = 1 and YEAR(NGAYTHANHTOAN) = ?\n"
            + "group by HOADON.IDHOADON";
    String topSP = "SELECT TOP 5 SANPHAMCHITIET.IDSANPHAM,\n"
            + "SANPHAMCHITIET.IDLOPLOT, \n"
            + "SANPHAMCHITIET.IDCHATLIEU, \n"
            + "SANPHAMCHITIET.IDKIEUDANG,\n"
            + "SANPHAMCHITIET.IDMAUSAC, \n"
            + "SANPHAMCHITIET.IDMU,\n"
            + "SANPHAMCHITIET.IDSIZE, SUM(HOADONCHITIET.SOLUONG) AS Tong\n"
            + "FROM HOADONCHITIET JOIN SANPHAMCHITIET ON HOADONCHITIET.IDSPCT = SANPHAMCHITIET.IDSPCT\n"
            + "GROUP BY SANPHAMCHITIET.IDSANPHAM, \n"
            + "SANPHAMCHITIET.IDCHATLIEU, \n"
            + "SANPHAMCHITIET.IDKIEUDANG, \n"
            + "SANPHAMCHITIET.IDLOPLOT,\n"
            + "SANPHAMCHITIET.IDMAUSAC,\n"
            + "SANPHAMCHITIET.IDMU,\n"
            + "SANPHAMCHITIET.IDSIZE\n"
            + "ORDER BY Tong DESC";
    String Find = "select * from HOADON where (MAHOADON like ? or TONGTIEN like ? "
            + "or NGAYTHANHTOAN like ? or NGAYTAO like ?)and TRANGTHAI = 1 order by NGAYTHANHTOAN desc";

    public ThongkeService() {
    }

    public List<SanPhamChiTiet> getday(int month, int year) {
        try {
            List<SanPhamChiTiet> listSanPhamChiTiet = new ArrayList<>();
            ResultSet rs = JDBCHelper.executeQuery(getday, month, year);
            while (rs.next()) {
                SanPhamChiTiet spct = new SanPhamChiTiet();
                spct.setIdSPCT(rs.getInt(1));
                listSanPhamChiTiet.add(spct);
            }
            return listSanPhamChiTiet;
//            return listHoaDon;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<SanPhamChiTiet> getmonth(int year) {
        try {
            List<SanPhamChiTiet> listSanPhamChiTiet = new ArrayList<>();
            ResultSet rs = JDBCHelper.executeQuery(getmonth, year);
            while (rs.next()) {
                SanPhamChiTiet spct = new SanPhamChiTiet();
                spct.setIdSPCT(rs.getInt(1));
                listSanPhamChiTiet.add(spct);
            }
            return listSanPhamChiTiet;
//            return listHoaDon;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<SanPhamChiTiet> getyear() {
        try {
            List<SanPhamChiTiet> listSanPhamChiTiet = new ArrayList<>();
            ResultSet rs = JDBCHelper.executeQuery(getyear);
            while (rs.next()) {
                SanPhamChiTiet spct = new SanPhamChiTiet();
                spct.setIdSPCT(rs.getInt(1));
                listSanPhamChiTiet.add(spct);
            }
            return listSanPhamChiTiet;
//            return listHoaDon;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public SanPhamChiTiet getSLandDTNgay(int month, int year, int day) {
        try {
            List<SanPhamChiTiet> listSanPhamChiTiet = new ArrayList<>();
            ResultSet rs = JDBCHelper.executeQuery(getSLandDTNgay, month, year, day);
            while (rs.next()) {
                SanPhamChiTiet spct = new SanPhamChiTiet();
                spct.setIdSPCT(rs.getInt(1));
                spct.setGia(rs.getDouble(2));
                listSanPhamChiTiet.add(spct);
            }
            return listSanPhamChiTiet.get(0);
//            return listHoaDon;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public SanPhamChiTiet getSLandDTThang(int month, int year) {
        try {
            List<SanPhamChiTiet> listSanPhamChiTiet = new ArrayList<>();
            ResultSet rs = JDBCHelper.executeQuery(getSLandDTThang, month, year);
            while (rs.next()) {
                SanPhamChiTiet spct = new SanPhamChiTiet();
                spct.setIdSPCT(rs.getInt(1));
                spct.setGia(rs.getDouble(2));
                listSanPhamChiTiet.add(spct);
            }
            return listSanPhamChiTiet.get(0);
//            return listHoaDon;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public SanPhamChiTiet getSLandDTNam(int year) {
        try {
            List<SanPhamChiTiet> listSanPhamChiTiet = new ArrayList<>();
            ResultSet rs = JDBCHelper.executeQuery(getSLandDTNam, year);
            while (rs.next()) {
                SanPhamChiTiet spct = new SanPhamChiTiet();
                spct.setIdSPCT(rs.getInt(1));
                spct.setGia(rs.getDouble(2));
                listSanPhamChiTiet.add(spct);
            }
            return listSanPhamChiTiet.get(0);
//            return listHoaDon;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int getSLSPNgay(int month, int year, int day) {
        try {
            int index = 0;
            ResultSet rs = JDBCHelper.executeQuery(getSLSPNgay, month, year, day);
            while (rs.next()) {
                index++;
            }
            return index;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int getSLSPThang(int month, int year) {
        try {
            int index = 0;
//            List<SanPhamChiTiet> listSanPhamChiTiet = new ArrayList<>();
            ResultSet rs = JDBCHelper.executeQuery(getSLSPThang, month, year);
            while (rs.next()) {
//                SanPhamChiTiet spct = new SanPhamChiTiet();
//                spct.setId(rs.getInt(1));
////                spct.setGia(rs.getFloat(2));
//                listSanPhamChiTiet.add(spct);
                index++;
            }
            return index;
//            return listHoaDon;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int getSLSPNam(int year) {
        try {
            int index = 0;
            ResultSet rs = JDBCHelper.executeQuery(getSLSPNam, year);
            while (rs.next()) {
                index++;
            }
            return index;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public double getcout(String sql) {
        try {
            List<SanPhamChiTiet> listSanPhamChiTiet = new ArrayList<>();
            ResultSet rs = JDBCHelper.executeQuery(sql);
            while (rs.next()) {
                SanPhamChiTiet spct = new SanPhamChiTiet();
                spct.setGia(rs.getDouble(1));
                listSanPhamChiTiet.add(spct);
            }
            return listSanPhamChiTiet.get(0).getGia();
//            return listHoaDon;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<SanPhamChiTiet> topSp() {
        List<SanPhamChiTiet> list = new ArrayList<>();
        String topSP = "SELECT TOP 5 SANPHAMCHITIET.IDSANPHAM, SANPHAM.TENSP, LOPLOT.TENLOPLOT, CHATLIEU.TENCHATLIEU, KIEUDANG.TENKIEUDANG, MAUSAC.TENMAU, MU.KIEUMU, SIZE.TENSIZE,\n"
                + "       SANPHAMCHITIET.IDLOPLOT, SANPHAMCHITIET.IDCHATLIEU, SANPHAMCHITIET.IDKIEUDANG,\n"
                + "       SANPHAMCHITIET.IDMAUSAC, SANPHAMCHITIET.IDMU, SANPHAMCHITIET.IDSIZE, \n"
                + "       SUM(HOADONCHITIET.SOLUONG) AS Tong\n"
                + "FROM HOADONCHITIET \n"
                + "JOIN SANPHAMCHITIET ON HOADONCHITIET.IDSPCT = SANPHAMCHITIET.IDSPCT\n"
                + "JOIN SANPHAM ON SANPHAMCHITIET.IDSANPHAM = SANPHAM.IDSANPHAM\n"
                + "JOIN LOPLOT ON SANPHAMCHITIET.IDLOPLOT = LOPLOT.IDLOPLOT\n"
                + "JOIN KIEUDANG ON SANPHAMCHITIET.IDKIEUDANG = KIEUDANG.IDKIEUDANG\n"
                + "JOIN CHATLIEU ON SANPHAMCHITIET.IDCHATLIEU = CHATLIEU.IDCHATLIEU\n"
                + "JOIN MAUSAC ON SANPHAMCHITIET.IDMAUSAC = MAUSAC.IDMAUSAC\n"
                + "JOIN MU ON SANPHAMCHITIET.IDMU = MU.IDMU\n"
                + "JOIN SIZE ON SANPHAMCHITIET.IDSIZE = SIZE.IDSIZE\n"
                + "GROUP BY SANPHAMCHITIET.IDSANPHAM, SANPHAM.TENSP, LOPLOT.TENLOPLOT, CHATLIEU.TENCHATLIEU, KIEUDANG.TENKIEUDANG, MAUSAC.TENMAU, MU.KIEUMU, SIZE.TENSIZE,\n"
                + "         SANPHAMCHITIET.IDLOPLOT, SANPHAMCHITIET.IDCHATLIEU, \n"
                + "         SANPHAMCHITIET.IDKIEUDANG, SANPHAMCHITIET.IDMAUSAC, \n"
                + "         SANPHAMCHITIET.IDMU, SANPHAMCHITIET.IDSIZE\n"
                + "ORDER BY Tong DESC";

        try (Connection conn = DBConnect.getConnection(); PreparedStatement stmt = conn.prepareStatement(topSP); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                SanPhamChiTiet spct = new SanPhamChiTiet();

                // Initialize and set SanPham
                SanPham sanPham = new SanPham();
                sanPham.setId(rs.getInt("IDSANPHAM"));
                sanPham.setTenSP(rs.getString("TENSP"));
                spct.setSanPham(sanPham);

                // Initialize and set LopLot
                LopLot lopLot = new LopLot();
                lopLot.setId(rs.getInt("IDLOPLOT"));
                lopLot.setTenLopLot(rs.getString("TENLOPLOT"));
                spct.setLopLot(lopLot);

                // Initialize and set ChatLieu
                ChatLieu chatLieu = new ChatLieu();
                chatLieu.setId(rs.getInt("IDCHATLIEU"));
                chatLieu.setTenChatLieu(rs.getString("TENCHATLIEU"));
                spct.setChatLieu(chatLieu);

                // Initialize and set KieuDang
                KieuDang kieuDang = new KieuDang();
                kieuDang.setId(rs.getInt("IDKIEUDANG"));
                kieuDang.setTenKieuDang(rs.getString("TENKIEUDANG"));
                spct.setKieuDang(kieuDang);

                // Initialize and set MauSac
                MauSac mauSac = new MauSac();
                mauSac.setId(rs.getInt("IDMAUSAC"));
                mauSac.setTenMauSac(rs.getString("TENMAU"));
                spct.setMauSac(mauSac);

                // Initialize and set Mu
                Mu mu = new Mu();
                mu.setId(rs.getInt("IDMU"));
                mu.setTenMu(rs.getString("KIEUMU"));
                spct.setMu(mu);

                // Initialize and set Size
                Size size = new Size();
                size.setId(rs.getInt("IDSIZE"));
                size.setTenSize(rs.getString("TENSIZE"));
                spct.setSize(size);

                // Set soLuong
                spct.setSoLuong(rs.getInt("Tong"));

                list.add(spct);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public int getcoutSP() {
        return (int) getcout(getcoutSP);
    }

    public int getcoutNV() {
        return (int) getcout(getcoutNV);
    }

    public int getcoutKH() {
        return (int) getcout(getcoutKH);
    }

    public double getsumDT() {
        return getcout(getsumDT);
    }

}
