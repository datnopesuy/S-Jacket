/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.service;
import app.model.NhanVien;
import app.model.Voucher;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dat
 */
public class VoucherService {

    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    String sql = null;
    List<NhanVien> list;

    public List<Voucher> getAll() {
        String sql = "select * from voucher";
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            List<Voucher> list = new ArrayList<>();
            while (rs.next()) {
                Voucher voucher = new Voucher();
                voucher.setMaVoucher(rs.getString(2));
                voucher.setTenvoucher(rs.getString(3));
                voucher.setDontToithieu(rs.getDouble(4));
                voucher.setMucGiamgia(rs.getDouble(5));
                list.add(voucher);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Voucher getDetail(String maVoucher) {
        Voucher voucher = new Voucher();
        String sql = "select * from voucher where mavoucher like ?";
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, maVoucher);
            ResultSet rs = ps.executeQuery();
            List<Voucher> list = new ArrayList<>();
            while (rs.next()) {
                voucher.setMaVoucher(rs.getString(2));
                voucher.setTenvoucher(rs.getString(3));
                voucher.setDontToithieu(rs.getDouble(4));
                voucher.setMucGiamgia(rs.getDouble(5));
                return voucher;
            }
            return voucher;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }
    
}
