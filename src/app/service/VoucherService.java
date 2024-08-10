/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.service;

import app.model.Voucher;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class VoucherService {

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    String sql = null;

    public List<Voucher> getAll() {
        List<Voucher> listVoucher = new ArrayList<>();
        try {
            con = SQLServerConnection.getConnection("DUAN1");
            sql = "select* from Voucher";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Voucher voucher = new Voucher();
                voucher.setMaVocher(rs.getString(2));
                voucher.setTenVoucher(rs.getString(3));
                voucher.setDonToithieu(rs.getDouble(4));
                voucher.setMucGiamgia(rs.getDouble(5));
                voucher.setHinhThucgiam(rs.getString(6));
                voucher.setNgayBatdau(rs.getDate(7));
                voucher.setNgayKetthuc(rs.getDate(8));
                voucher.setTrangthai(rs.getBoolean(13));
                listVoucher.add(voucher);
            }
        } catch (Exception e) {

        }
        return listVoucher;
    }

    public void inSertvoucher(Voucher voucher) {
        try {
            con = SQLServerConnection.getConnection("DUAN1");
            sql = "INSERT INTO Voucher (MAVOUCHER, TENVOUCHER, DONTOITHIEU,MUCGIAMGIA,LOAIVC,NGAYBATDAU,NGAYKETTHUC)\n"
                    + "VALUES(?,?,?,?,?,?,?)";
            ps = con.prepareStatement(sql);
            ps.setString(1, voucher.getMaVocher());
            ps.setString(2, voucher.getTenVoucher());
            ps.setDouble(3, voucher.getDonToithieu());
            ps.setDouble(4, voucher.getMucGiamgia());
            ps.setString(5, voucher.getHinhThucgiam());
            ps.setString(6, dateFormat.format(voucher.getNgayBatdau()));
            ps.setString(7, dateFormat.format(voucher.getNgayKetthuc()));
            ps.executeUpdate();
            System.out.println(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteVoucher(String maVoucher) {

        try {
            con = SQLServerConnection.getConnection("DUAN1");
            sql = "DELETE FROM VOUCHER WHERE MAVOUCHER=?";
            ps = con.prepareStatement(sql);
            ps.setString(1, maVoucher);
            ps.executeUpdate();
            System.out.println(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public List<String> getNameVoucher() {
        List<String> list = new ArrayList<>();
        try {
            con = SQLServerConnection.getConnection("DUAN1");
            sql = "select MAVOUCHER  FROM VOUCHER";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(rs.getString(1));
            }
        } catch (Exception e) {

        }
        return list;
    }

    public void upateVoucher(Voucher voucher) {
        try {
            con = SQLServerConnection.getConnection("DUAN1");
            sql = "UPDATE VOUCHER SET MAVOUCHER=?,TENVOUCHER=?,DONTOITHIEU=?,MUCGIAMGIA=?,LOAIVC=?,NGAYBATDAU=?,NGAYKETTHUC=? WHERE MAVOUCHER=?";
            ps = con.prepareStatement(sql);
            ps.setString(1, voucher.getMaVocher());
            ps.setString(2, voucher.getTenVoucher());
            ps.setDouble(3, voucher.getDonToithieu());
            ps.setDouble(4, voucher.getMucGiamgia());
            ps.setString(5, voucher.getHinhThucgiam());
            ps.setString(6, dateFormat.format(voucher.getNgayBatdau()));
            ps.setString(7, dateFormat.format(voucher.getNgayKetthuc()));
            ps.setString(8, voucher.getMaVocher());
            ps.executeUpdate();
            System.out.println(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
public List<Voucher> searchBymaVoucher(String maVoucher) {
        List<Voucher> listVoucher = new ArrayList<>();
        try {
            con = SQLServerConnection.getConnection("DUAN1");
            sql = "select * from VOUCHER where mavoucher like ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, maVoucher);
            rs = ps.executeQuery();
            while (rs.next()) {
                Voucher voucher = new Voucher();
                voucher.setMaVocher(rs.getString(2));
                voucher.setTenVoucher(rs.getString(3));
                voucher.setDonToithieu(rs.getDouble(4));
                voucher.setMucGiamgia(rs.getDouble(5));
                voucher.setHinhThucgiam(rs.getString(6));
                voucher.setNgayBatdau(rs.getDate(7));
                voucher.setNgayKetthuc(rs.getDate(8));
                voucher.setTrangthai(rs.getBoolean(13));
                listVoucher.add(voucher);
            }
        } catch (Exception e) {

        }
        return listVoucher;
    }
   
    public Voucher getDetail(String maVoucher) {
        Voucher voucher = new Voucher();
        String sql = "select * from voucher where mavoucher like ?";
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, maVoucher);
            ResultSet rs = ps.executeQuery();
            List<Voucher> list = new ArrayList<>();
            while (rs.next()) {
                voucher.setMaVocher(rs.getString(2));
                voucher.setTenVoucher(rs.getString(3));
                voucher.setDonToithieu(rs.getDouble(4));
                voucher.setMucGiamgia(rs.getDouble(5));
                return voucher;
            }
            return voucher;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }
     public static void main(String[] args) {
          
        VoucherService vs = new VoucherService();
        for (Voucher voucher : vs.searchBymaVoucher("vou001")) {
            System.out.println(voucher);

        }

    }
}
