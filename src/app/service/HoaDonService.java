/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.service;

import app.model.ChatLieu;
import app.model.HoaDonChiTiet;
import app.model.KieuDang;
import app.model.LopLot;
import app.model.MauSac;
import app.model.Mu;
import app.model.QuanLyHoaDon;
import app.model.SanPham;
import app.model.SanPhamChiTiet;
import app.model.Size;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PC
 */
public class HoaDonService {

    private Connection con = null;
    private PreparedStatement ps = null;
    private CallableStatement cs = null;
    private ResultSet rs = null;
    private ResultSet rs2 = null;
    private String sql = "";

    List<QuanLyHoaDon> listQLHD = null;
    List<SanPhamChiTiet> listSPCT = null;

    public List<QuanLyHoaDon> getAllHD() {
        listQLHD = new ArrayList<>();
        try {
            String sql = "SELECT \n"
                    + "    HOADON.MAHOADON,\n"
                    + "    HOADON.NGAYTAO,\n"
                    + "    HOADON.NGAYTHANHTOAN,\n"
                    + "    HOADON.TONGTIEN,\n"
                    + "    NHANVIEN.HOTEN,\n"
                    + "    KHACHHANG.HOTEN AS TenKhachHang,\n"
                    + "    KHACHHANG.SDT AS SoDienThoaiKhachHang,\n"
                    + "    HOADON.TRANGTHAI\n"
                    + "FROM \n"
                    + "    HOADON\n"
                    + "JOIN \n"
                    + "    KHACHHANG ON HOADON.IDKHACHHANG = KHACHHANG.IDKHACHHANG\n"
                    + "JOIN \n"
                    + "    NHANVIEN ON HOADON.IDNHANVIEN = NHANVIEN.IDNHANVIEN;";
            con = DBConnect.getConnection();
            ps = con.prepareCall(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                QuanLyHoaDon qlhd = new QuanLyHoaDon(rs.getString(1), rs.getDate(2), rs.getDate(3), rs.getDouble(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getBoolean(8));
                listQLHD.add(qlhd);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return listQLHD;
    }

    public List<SanPhamChiTiet> getSPCT(String mahoadon) {
        listSPCT = new ArrayList<>();
        try {
            sql = "SELECT hdct.IDSPCT,\n"
                    + "       hdct.IDHDCT, hd.IDHOADON, hd.MAHOADON,\n"
                    + "       sp.TENSP, sp.IDSANPHAM,\n"
                    + "       ms.TENMAU, ms.IDMAUSAC,\n"
                    + "       cl.MACHATLIEU, cl.IDCHATLIEU,\n"
                    + "       ll.TENLOPLOT, ll.IDLOPLOT,\n"
                    + "       m.KIEUMU, m.IDMU,\n"
                    + "       s.MASIZE, s.IDSIZE,\n"
                    + "       kd.TENKIEUDANG, kd.IDKIEUDANG,\n"
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
                    + "where hd.MAHOADON = ?;";
            con = DBConnect.getConnection();
            ps = con.prepareCall(sql);
            ps.setObject(1, mahoadon);
            rs = ps.executeQuery();
            while (rs.next()) {
//                SanPhamChiTiet sp = new SanPhamChiTiet(Integer.SIZE, sanPham, mauSac, chatLieu, mu, size, lopLot, kieuDang, sql, 0, Double.NaN, maHoaDon, maHoaDon, ngayTao, ngaySua, true);
                SanPhamChiTiet spct = new SanPhamChiTiet();

                // Tạo và thiết lập các đối tượng thành phần
                SanPham sanPham = new SanPham();
                sanPham.setTenSP(rs.getString("TenSP"));
                spct.setSanPham(sanPham);

                ChatLieu chatLieu = new ChatLieu();
                chatLieu.setTenChatLieu(rs.getString("MaChatLieu"));
                spct.setChatLieu(chatLieu);

                Size size = new Size();
                size.setTenSize(rs.getString("MaSize"));
                spct.setSize(size);

                LopLot lopLot = new LopLot();
                lopLot.setTenLopLot(rs.getString("TenLopLot"));
                spct.setLopLot(lopLot);

                KieuDang kieuDang = new KieuDang();
                kieuDang.setTenKieuDang(rs.getString("TenKieuDang"));
                spct.setKieuDang(kieuDang);

                Mu mu = new Mu();
                mu.setTenMu(rs.getString("KIEUMU"));
                spct.setMu(mu);

                MauSac mauSac = new MauSac();
                mauSac.setTenMauSac(rs.getString("TenMau"));
                spct.setMauSac(mauSac);

                // Thiết lập các thuộc tính còn lại
                spct.setSoLuong(rs.getInt("SoLuong"));
                spct.setGia(rs.getDouble("THANHTIEN"));

                // Thêm đối tượng vào danh sách
                listSPCT.add(spct);

            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return listSPCT;
    }

    public List<QuanLyHoaDon> TimHD(String maHD) {
        listQLHD = new ArrayList<>();
        try {
            String sql = "SELECT \n"
                    + "    HOADON.MAHOADON,\n"
                    + "    HOADON.NGAYTAO,\n"
                    + "    HOADON.NGAYTHANHTOAN,\n"
                    + "    HOADON.TONGTIEN,\n"
                    + "    NHANVIEN.MANHANVIEN,\n"
                    + "    KHACHHANG.HOTEN AS TenKhachHang,\n"
                    + "    KHACHHANG.SDT AS SoDienThoaiKhachHang,\n"
                    + "    HOADON.TRANGTHAI\n"
                    + "FROM \n"
                    + "    HOADON\n"
                    + "JOIN \n"
                    + "    KHACHHANG ON HOADON.IDKHACHHANG = KHACHHANG.IDKHACHHANG\n"
                    + "JOIN \n"
                    + "    NHANVIEN ON HOADON.IDNHANVIEN = NHANVIEN.IDNHANVIEN where HoaDon.MaHoaDon like '%'+?+'%';";
            con = DBConnect.getConnection();
            ps = con.prepareCall(sql);
            ps.setObject(1, maHD);
            rs = ps.executeQuery();
            while (rs.next()) {
                QuanLyHoaDon qlhd = new QuanLyHoaDon(rs.getString(1), rs.getDate(2), rs.getDate(3), rs.getDouble(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getBoolean(8));
                listQLHD.add(qlhd);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return listQLHD;
    }

    public List<QuanLyHoaDon> loc(String trangthai, String ngaybd, String ngaykt) {
        listQLHD = new ArrayList<>();
        try {
            String sql = "SELECT \n"
                    + "    HOADON.MAHOADON,\n"
                    + "    HOADON.NGAYTAO,\n"
                    + "    HOADON.NGAYTHANHTOAN,\n"
                    + "    HOADON.TONGTIEN,\n"
                    + "    NHANVIEN.MANHANVIEN,\n"
                    + "    KHACHHANG.HOTEN AS TenKhachHang,\n"
                    + "    KHACHHANG.SDT AS SoDienThoaiKhachHang,\n"
                    + "    HOADON.TRANGTHAI\n"
                    + "FROM \n"
                    + "    HOADON\n"
                    + "JOIN \n"
                    + "    KHACHHANG ON HOADON.IDKHACHHANG = KHACHHANG.IDKHACHHANG\n"
                    + "JOIN \n"
                    + "    NHANVIEN ON HOADON.IDNHANVIEN = NHANVIEN.IDNHANVIEN where HoaDon.TrangThai like '%'+?+'%' and (HoaDon.NgayTao between ? and ?);";
            con = DBConnect.getConnection();
            ps = con.prepareCall(sql);
            ps.setObject(1, trangthai);
            ps.setObject(2, ngaybd);
            ps.setObject(3, ngaykt);
            rs = ps.executeQuery();
            while (rs.next()) {
                QuanLyHoaDon qlhd = new QuanLyHoaDon(rs.getString(1), rs.getDate(2), rs.getDate(3), rs.getDouble(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getBoolean(8));
                listQLHD.add(qlhd);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return listQLHD;
    }

    public List<QuanLyHoaDon> locTrangThai(String trangthai) {
        listQLHD = new ArrayList<>();
        try {
            String sql = "SELECT \n"
                    + "    HOADON.MAHOADON,\n"
                    + "    HOADON.NGAYTAO,\n"
                    + "    HOADON.NGAYTHANHTOAN,\n"
                    + "    HOADON.TONGTIEN,\n"
                    + "    NHANVIEN.MANHANVIEN,\n"
                    + "    KHACHHANG.HOTEN AS TenKhachHang,\n"
                    + "    KHACHHANG.SDT AS SoDienThoaiKhachHang,\n"
                    + "    HOADON.TRANGTHAI\n"
                    + "FROM \n"
                    + "    HOADON\n"
                    + "JOIN \n"
                    + "    KHACHHANG ON HOADON.IDKHACHHANG = KHACHHANG.IDKHACHHANG\n"
                    + "JOIN \n"
                    + "    NHANVIEN ON HOADON.IDNHANVIEN = NHANVIEN.IDNHANVIEN where HoaDon.TrangThai like '%'+?+'%';";
            con = DBConnect.getConnection();
            ps = con.prepareCall(sql);
            ps.setObject(1, trangthai);
            rs = ps.executeQuery();
            while (rs.next()) {
                QuanLyHoaDon qlhd = new QuanLyHoaDon(rs.getString(1), rs.getDate(2), rs.getDate(3), rs.getDouble(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getBoolean(8));
                listQLHD.add(qlhd);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return listQLHD;
    }

//    public List<SanPhamChiTiet> getSPCT(String maHD) {
//        listSPCT = new ArrayList<>();
//        try {
//            String sql = "SELECT \n"
//                    + "    HD.MAHOADON,\n"
//                    + "    SP.TENSP AS TenSanPham,\n"
//                    + "    CL.TENCHATLIEU AS TenChatLieu,\n"
//                    + "    S.TENSIZE AS TenSize,\n"
//                    + "    LL.TENLOPLOT AS TenLopLot,\n"
//                    + "    KD.TENKIEUDANG AS TenKieuDang,\n"
//                    + "    M.KIEUMU AS TenMu,\n"
//                    + "    MS.TENMAU AS TenMau,\n"
//                    + "    HDCT.SOLUONG AS SoLuong,\n"
//                    + "    HDCT.GIA AS Gia,\n"
//                    + "    HDCT.THANHTIEN AS ThanhTien\n"
//                    + "FROM \n"
//                    + "    HOADON HD\n"
//                    + "JOIN \n"
//                    + "    HOADONCHITIET HDCT ON HD.IDHOADON = HDCT.IDHOADON\n"
//                    + "JOIN \n"
//                    + "    SANPHAMCHITIET SPCT ON HDCT.IDSPCT = SPCT.IDSPCT\n"
//                    + "JOIN \n"
//                    + "    SANPHAM SP ON SPCT.IDSANPHAM = SP.IDSANPHAM\n"
//                    + "JOIN \n"
//                    + "    CHATLIEU CL ON SPCT.IDCHATLIEU = CL.IDCHATLIEU\n"
//                    + "JOIN \n"
//                    + "    SIZE S ON SPCT.IDSIZE = S.IDSIZE\n"
//                    + "JOIN \n"
//                    + "    LOPLOT LL ON SPCT.IDLOPLOT = LL.IDLOPLOT\n"
//                    + "JOIN \n"
//                    + "    KIEUDANG KD ON SPCT.IDKIEUDANG = KD.IDKIEUDANG\n"
//                    + "JOIN \n"
//                    + "    MU M ON SPCT.IDMU = M.IDMU\n"
//                    + "JOIN \n"
//                    + "    MAUSAC MS ON SPCT.IDMAUSAC = MS.IDMAUSAC\n"
//                    + "WHERE \n"
//                    + "    HD.MAHOADON = ?;";
//            con = DBConnect.getConnection();
//            ps = con.prepareCall(sql);
//            ps.setObject(1, maHD);
//            rs = ps.executeQuery();
//            while (rs.next()) {                
//                SanPhamChiTiet spct = new SanPhamChiTiet
//            }
//        } catch (Exception e) {
//        }
//
//    }
    public List<HoaDonChiTiet> selectbySQL(int idHd) {
        try {
            List<HoaDonChiTiet> listHoaDonChiTiet = new ArrayList<>();
            sql = "select * from HOADONCHITIET where IDHOADON = ?";
            con = DBConnect.getConnection();
            ps = con.prepareCall(sql);
            ps.setObject(1, idHd);
            rs = ps.executeQuery();
            while (rs.next()) {
                HoaDonChiTiet hdct = new HoaDonChiTiet();
                hdct.setIDHoaDonChiTiet(rs.getInt(1));
                hdct.setIDHoaDon(rs.getInt(2));
                hdct.setIDHSanPhamChiTiet(rs.getInt(3));
                hdct.setSoLuong(rs.getInt(4));
                hdct.setGia(rs.getDouble(5));
                hdct.setThanhTien(rs.getDouble(6));
                hdct.setNguoiTao(rs.getString(7));
                hdct.setNguoiSua(rs.getString(8));
                hdct.setNgayTao(rs.getDate(9));
                hdct.setNgaySua(rs.getDate(10));
                listHoaDonChiTiet.add(hdct);
            }
            return listHoaDonChiTiet;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
      public QuanLyHoaDon TimThongTinHd(String maHD) {
        listQLHD = new ArrayList<>();
        try {
            String sql = "SELECT \n"
                    + "    HOADON.MAHOADON,\n"
                    + "    HOADON.NGAYTAO,\n"
                    + "    HOADON.NGAYTHANHTOAN,\n"
                    + "    HOADON.TONGTIEN,\n"
                    + "    NHANVIEN.MANHANVIEN,\n"
                    + "    KHACHHANG.HOTEN AS TenKhachHang,\n"
                    + "    KHACHHANG.SDT AS SoDienThoaiKhachHang,\n"
                    + "    HOADON.TRANGTHAI\n"
                    + "FROM \n"
                    + "    HOADON\n"
                    + "JOIN \n"
                    + "    KHACHHANG ON HOADON.IDKHACHHANG = KHACHHANG.IDKHACHHANG\n"
                    + "JOIN \n"
                    + "    NHANVIEN ON HOADON.IDNHANVIEN = NHANVIEN.IDNHANVIEN where HoaDon.MaHoaDon like '%'+?+'%';";
            con = DBConnect.getConnection();
            ps = con.prepareCall(sql);
            ps.setObject(1, maHD);
            rs = ps.executeQuery();
            while (rs.next()) {
                QuanLyHoaDon qlhd = new QuanLyHoaDon(rs.getString(1), rs.getDate(2), rs.getDate(3), rs.getDouble(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getBoolean(8));
                listQLHD.add(qlhd);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return listQLHD.get(0);
    }

}
