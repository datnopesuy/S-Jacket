/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.service;
import app.model.NhanVien;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dat
 */
public class NhanVienService {
    
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    String sql = null;
    List<NhanVien> list;
    
    public List<NhanVien> getAll(){
        String sql = "select * from NHANVIEN";
        try(Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)){
            ResultSet rs = ps.executeQuery();
            List<NhanVien> list = new ArrayList<>();
            while (rs.next()) {                
                NhanVien nv = new NhanVien();
                nv.setId(rs.getInt(1));
                nv.setMaNV(rs.getString(2));
                nv.setTenNV(rs.getString(4));
                //nv.setNgSinh(rs.getDate(4));
                //nv.setGioiTinh(rs.getBoolean(5));
                nv.setDiaChi(rs.getString(6));
                nv.setSdt(rs.getString(7));
                nv.setEmail(rs.getString(8));
                nv.setMatkhau(rs.getString(10));
                list.add(nv);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
             return null;
    }
    
}
