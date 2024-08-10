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
 *
 * @author ADMIN
 */
public class NhanVienService {
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    String sql = null;
    List<NhanVien> list;
    
    public List<NhanVien> getAll(){
        String sql = "SELECT [IDNHANVIEN]\n" + "      ,[MANHANVIEN]\n" + "      ,[HOTEN]\n" + "      ,[NGAYSINH]\n" + "      ,[GIOITINH]\n" + "      ,[DIACHI]\n" + "      ,[SDT]\n" + "      ,[EMAIL]\n" + "      ,[MATKHAU]\n" + "  FROM [dbo].[NHANVIEN]\n";
        try(Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)){
            ResultSet rs = ps.executeQuery();
            List<NhanVien> list = new ArrayList<>();
            while (rs.next()) {                
                NhanVien nv = new NhanVien();
                nv.setId(rs.getInt(1));
                nv.setMaNV(rs.getString(2));
                nv.setTenNV(rs.getString(3));
                nv.setNgSinh(rs.getDate(4));
                nv.setGioiTinh(rs.getBoolean(5));
                nv.setDiaChi(rs.getString(6));
                nv.setSdt(rs.getString(7));
                nv.setEmail(rs.getString(8));
                nv.setMatKhau(rs.getString(9));
                list.add(nv);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
             return null;
    }
    public List<NhanVien> paging(int page, int limit){
        String sql = " SELECT\n" + "        [MANHANVIEN]\n" + "       ,[HOTEN]\n" + "       ,[NGAYSINH]\n" + "       ,[GIOITINH]\n" + "       ,[DIACHI]\n" + "       ,[SDT]\n" + "       ,[EMAIL]\n" + "   FROM [dbo].[NHANVIEN] ORDER BY MANHANVIEN\n" + " OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
         try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, (page - 1) * limit);
            ps.setInt(2, limit);
            ResultSet rs = ps.executeQuery();
            List<NhanVien> list = new ArrayList<>();
            while (rs.next()) {
                NhanVien nv = new NhanVien();
                nv.setMaNV(rs.getString(1));
                nv.setTenNV(rs.getString(2));
                nv.setNgSinh(rs.getDate(3));
                nv.setGioiTinh(rs.getBoolean(4));
                nv.setDiaChi(rs.getString(5));
                nv.setSdt(rs.getString(6));
                nv.setEmail(rs.getString(7));
                list.add(nv);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
 
    public Integer addNV(NhanVien nv){
        int result = 0;
        String sql = "INSERT INTO [dbo].[NHANVIEN]\n" + "           ([MANHANVIEN]\n" + "           ,[HOTEN]\n" + "           ,[NGAYSINH]\n" + "           ,[GIOITINH]\n" + "           ,[DIACHI]\n" + "           ,[SDT]\n" + "           ,[EMAIL])\n" + "     VALUES\n" + "           (?,?,?,?,?,?,?)\n";
        try(Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, nv.getMaNV());
            ps.setObject(2, nv.getTenNV());
            ps.setObject(3, nv.getNgSinh());
            ps.setObject(4, nv.isGioiTinh());
            ps.setObject(5, nv.getDiaChi());
            ps.setObject(6, nv.getSdt());
            ps.setObject(7, nv.getEmail());
            
            result = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return result;
    }
    public int delete(NhanVien nv, String ma){
        int result = 0;
        String sql = "DELETE FROM [dbo].[NHANVIEN]\n" + "      WHERE MANHANVIEN = ?\n";
        try(Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, ma);
            result = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    
    public int countNhanVien(){
        String sql = "select count (*) from NHANVIEN\n";
        int count = 0;
        Connection c = DBConnect.getConnection();
        try {
            PreparedStatement ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                count = rs.getInt(1);
            }
            return count;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
       public int getMaxPage(int itemsPerPage){
        int totalCount = countNhanVien();
        int maxPage = totalCount / itemsPerPage;
        if (totalCount % itemsPerPage !=0) {
            maxPage++;
        }
        return maxPage;
    }
    public int update(NhanVien nv, Integer id){
        int result = 0;
        String sql = " UPDATE [dbo].[NHANVIEN]\n" + "       SET\n" + "           [HOTEN] = ?\n" + "          ,[NGAYSINH] = ?\n" + "          ,[GIOITINH] = ?\n" + "          ,[DIACHI] = ?\n" + "          ,[SDT] = ?\n" + "          ,[EMAIL] = ?\n" + "                       WHERE IDNHANVIEN = ?\n";
        try(Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, nv.getTenNV());
            ps.setObject(2, nv.getNgSinh());
            ps.setObject(3, nv.isGioiTinh());
            ps.setObject(4, nv.getDiaChi());
            ps.setObject(5, nv.getSdt());
            ps.setObject(6, nv.getEmail());
            ps.setObject(7, id);
            result = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
   public List<NhanVien> searchAllField(String keyword) {
        List<NhanVien> list = new ArrayList<>();
        String sql = "SELECT\n" + "      [MANHANVIEN]\n" + "      ,[HOTEN]\n" + "      ,[NGAYSINH]\n" + "      ,[GIOITINH]\n" + "      ,[DIACHI]\n" + "      ,[SDT]\n" + "      ,[EMAIL]\n" + "  FROM [dbo].[NHANVIEN]\n" + "  where MANHANVIEN LIKE  ? OR HOTEN LIKE  ? OR SDT LIKE ?\n";
        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            for (int i = 1; i <= 3; i++) {
                ps.setObject(i, "%" + keyword + "%");
            }
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new NhanVien(rs.getString(1),rs.getString(2),rs.getDate(3),rs.getBoolean(4),rs.getString(5),rs.getString(6),rs.getString(7), rs.getString(8)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
   public List<NhanVien> loc(String gender) {
        List<NhanVien> list = new ArrayList<>();
        String sql = "SELECT\n" + "      [MANHANVIEN]\n" + "      ,[HOTEN]\n" + "      ,[NGAYSINH]\n" + "      ,[GIOITINH]\n" + "      ,[DIACHI]\n" + "      ,[SDT]\n" + "      ,[EMAIL]\n" + "  FROM [dbo].[NHANVIEN]\n" + "  where GIOITINH = ?\n";
        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            int genderBit = "Nam".equals(gender) ? 1 : 0;

            ps.setInt(1, genderBit);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new NhanVien(rs.getString(1),rs.getString(2),rs.getDate(3),rs.getBoolean(4),rs.getString(5),rs.getString(6),rs.getString(7), rs.getString(8)));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return list;
    }

   
   public NhanVien searchByma(String maNhanvien){
   NhanVien nv = new NhanVien();
    String sql = " select * from NhanVien where MANHANVIEN LIKE ? ";
        try(Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)){
            ps.setString(1, maNhanvien);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {                
                nv.setMaNV(rs.getString(2));
                nv.setMatKhau(rs.getString(10));
                return nv;
            }
            return nv;
        } catch (Exception e) {
            e.printStackTrace();
        }
   return null;
   }

    
    public static void main(String[] args) {
        NhanVien nv = new NhanVienService().searchByma("NV001");
        System.out.println(nv);
    }
}
