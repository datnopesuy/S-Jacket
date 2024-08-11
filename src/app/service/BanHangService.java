/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.service;

import app.model.GioHang;
import app.model.HoaDon;
import app.model.HoaDonChiTiet;
import app.model.HoaDonChiTiet1;
import app.model.KhachHang;
import app.model.SanPham;
import app.model.SanPhamChiTiet;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
                hd.setTongTien(rs.getDouble("TONGTIEN"));
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

    public Integer getIDHD(String maHD) {
        String sql = "select IDHOADON from HOADON"
                + " where MAHOADON = ?";
        Connection con = DBConnect.getConnection();
        try {
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, maHD);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                return rs.getInt("IDHOADON");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Double getTongTienHD(String maHD) {
        String sql = "select TONGTIEN from HOADON"
                + " where MAHOADON = ?";
        Connection con = DBConnect.getConnection();
        try {
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, maHD);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                return rs.getDouble("TONGTIEN");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Integer updateSLDSSP1(int idSPCT, int soLuongCon) {
        Integer row = null;
        String sql = "update SANPHAMCHITIET \n"
                + " set SOLUONG = ?\n"
                + " where IDSPCT = ? ";
        Connection con = DBConnect.getConnection();
        try {
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setInt(2, idSPCT);
            pstm.setInt(1, soLuongCon);
            row = pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }

    public Integer getSlSPCT(int idSPCT) {
        String sql = "select SOLUONG \n"
                + "from SANPHAMCHITIET\n"
                + "where IDSPCT = ?";
        Connection con = DBConnect.getConnection();
        try {
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setInt(1, idSPCT);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                return rs.getInt("SOLUONG");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Integer thanhToan(int idHD, int idKH, int idVoucher, int idNV, int idHDCT) {
        Integer row = null;
        String sql = "update HOADON \n"
                + "set TRANGTHAI = 1, IDKHACHHANG = ?, IDNHANVIEN = ?, IDVOUCHER = ?, NGAYTHANHTOAN = GETDATE(), IDHDCT = ?\n"
                + "where IDHOADON = ?";
        Connection con = DBConnect.getConnection();
        try {
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setInt(1, idKH);
            pstm.setInt(2, idNV);
            pstm.setInt(3, idVoucher);
            pstm.setInt(4, idHDCT);
            pstm.setInt(5, idHD);
            row = pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }

    public Integer thanhToan1(int idHD, int idKH, int idNV, int idHDCT) {
        Integer row = null;
        String sql = "update HOADON \n"
                + "set TRANGTHAI = 1, IDKHACHHANG = ?, IDNHANVIEN = ?, NGAYTHANHTOAN = GETDATE(), IDHDCT = ?\n"
                + "where IDHOADON = ?";
        Connection con = DBConnect.getConnection();
        try {
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setInt(1, idKH);
            pstm.setInt(2, idNV);
            pstm.setInt(3, idHDCT);
            pstm.setInt(4, idHD);
            row = pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }

    public Integer thanhToan2(int idHD, int idNV, int idHDCT) {
        Integer row = null;
        String sql = "update HOADON \n"
                + "set TRANGTHAI = 1,IDKHACHHANG = 20, IDNHANVIEN = ?, NGAYTHANHTOAN = GETDATE(), IDHDCT = ?\n"
                + "where IDHOADON = ?";
        Connection con = DBConnect.getConnection();
        try {
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setInt(1, idNV);
            pstm.setInt(2, idHDCT);
            pstm.setInt(3, idHD);
            row = pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }

    public Integer getIDKHBySDT(String sdt) {
        String sql = "select IDKHACHHANG\n"
                + "from KHACHHANG\n"
                + "where SDT = ?";
        Connection con = DBConnect.getConnection();
        try {
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, sdt);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                return rs.getInt("IDKHACHHANG");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Integer getIDVoucherByMa(String maVoucher) {
        String sql = "select IDVOUCHER\n"
                + "from VOUCHER\n"
                + "where MAVOUCHER = ?";
        Connection con = DBConnect.getConnection();
        try {
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, maVoucher);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                return rs.getInt("IDVOUCHER");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Integer getIDNhanVienByMa(String maNV) {
        String sql = "select IDNHANVIEN\n"
                + "from NHANVIEN\n"
                + "where MANHANVIEN = ?";
        Connection con = DBConnect.getConnection();
        try {
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, maNV);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                return rs.getInt("IDNHANVIEN");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Integer getIDHDCTByIDHD(int idHD) {
        String sql = "select IDHDCT\n"
                + "from HOADONCHITIET\n"
                + "where IDHOADON = ?";
        Connection con = DBConnect.getConnection();
        try {
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setInt(1, idHD);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                return rs.getInt("IDHDCT");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public int UpdateHD(String maKH, String trangThai, String maHD) {
        int result = -1;
        try {
            String sql = "update HoaDon set idKhachHang=(select idKhachHang from KhachHang where MaKhachHang=?),TrangThai=? where MaHoaDon=?";
            Connection con = DBConnect.getConnection();
            PreparedStatement ps = con.prepareCall(sql);
            ps.setObject(1, maKH);
            ps.setObject(2, trangThai);
            ps.setObject(3, maHD);
            result = ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            result = 0;
        }
        return result;
    }

    public KhachHang getbySDTKhachHang(String SDT) {
        try {
            List<KhachHang> listKhachHang = new ArrayList();
            String sqlKH = "select * from KHACHHANG where SDT = ?";
            Connection con = DBConnect.getConnection();
            PreparedStatement ps = con.prepareCall(sqlKH);
            ps.setObject(1, SDT);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                KhachHang kh = new KhachHang();
                kh.setId(rs.getInt(1));
                kh.setMaKH(rs.getString(2));
                kh.setTenKH(rs.getString(3));
                kh.setGioiTinh(rs.getBoolean(4));
                kh.setSdt(rs.getString(5));
                kh.setNgaySinh(rs.getDate(6));
                kh.setEmail(rs.getString(7));
                kh.setNgayTao(rs.getDate(8));
                kh.setNgaySua(rs.getDate(9));
                kh.setNguoiTao(rs.getString(10));
                kh.setNguoiSua(rs.getString(11));
                kh.setTrangThai(rs.getBoolean(12));
                listKhachHang.add(kh);
            }
            if (listKhachHang.isEmpty()) {
                return null;
            }
            return listKhachHang.get(0);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Integer addKhachHang(KhachHang kh) {
        int result = 0;
        String sql = "insert into KHACHHANG(MAKHACHHANG, HOTEN, SDT, TRANGTHAI) values (?,?,?,1)";
        try {
            Connection con = DBConnect.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setObject(1, kh.getMaKH());
            ps.setObject(2, kh.getTenKH());
            ps.setObject(3, kh.getSdt());

            result = ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return result;
    }

    public Integer updateTongTien(int idHDC, double tongTien) {
        Integer row = null;
        String sql = "update HOADON\n"
                + "set TONGTIEN = ?\n"
                + "where IDHOADON = ? ";
        Connection con = DBConnect.getConnection();
        try {
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setDouble(1, tongTien);
            pstm.setInt(2, idHDC);
            row = pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }
}
