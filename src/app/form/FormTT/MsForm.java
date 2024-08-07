/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.form.FormTT;

import app.form.Form_SanPham;
import app.model.MauSac;
import app.model.Size;
import app.service.DBConnect;
import app.service.KieuDangService;
import app.service.LopLotService;
import app.service.MauSacService;
import app.service.MuService;
import app.service.SizeService;
import javax.swing.JFrame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

/**
 *
 * @author admin
 */
public class MsForm extends JFrame {

    private JTextField txtMaMau;
    private JTextField txtTenMau;
    private JRadioButton rdoHDTT;
    private JRadioButton rdoKhongHDTT;
    private JButton btnConfirm;
    private JButton btnCancel;
    private final SizeService ss = new SizeService();
    private final MuService ms = new MuService();
    private final MauSacService mss = new MauSacService();
    private final KieuDangService kds = new KieuDangService();
    private final LopLotService lls = new LopLotService();
    private DefaultComboBoxModel dcbmSize = new DefaultComboBoxModel();
    private DefaultComboBoxModel dcbmMS = new DefaultComboBoxModel();
    private DefaultComboBoxModel dcbmCL = new DefaultComboBoxModel();
    private DefaultComboBoxModel dcbmKD = new DefaultComboBoxModel();
    private DefaultComboBoxModel dcbmLL = new DefaultComboBoxModel();
    private DefaultComboBoxModel dcbmMu = new DefaultComboBoxModel();
    private Form_SanPham form_SanPham;

    public MsForm(Form_SanPham form_SanPham) {
        this.form_SanPham = form_SanPham;
        setTitle("Thêm Màu Sắc Mới");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5, 2, 5, 5));

        JLabel lblMaMs = new JLabel("Mã Màu:");
        txtMaMau = new JTextField();

        JLabel lblTenMau = new JLabel("Tên Màu:");
        txtTenMau = new JTextField();

        JLabel lblTrangThai = new JLabel("Trạng Thái:");

        rdoHDTT = new JRadioButton("Hoạt động", true);
        rdoKhongHDTT = new JRadioButton("Không hoạt động");

        ButtonGroup group = new ButtonGroup();
        group.add(rdoHDTT);
        group.add(rdoKhongHDTT);

        btnConfirm = new JButton("Xác nhận");
        btnCancel = new JButton("Hủy bỏ");

        add(lblMaMs);
        add(txtMaMau);
        add(lblTenMau);
        add(txtTenMau);
        add(lblTrangThai);
        add(rdoHDTT);
        add(new JLabel()); // Empty cell for alignment
        add(rdoKhongHDTT);
        add(btnConfirm);
        add(btnCancel);

        btnConfirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onConfirm();
            }
        });

        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });
    }

    private void onConfirm() {
        // Lấy dữ liệu từ form
        String maMau = txtMaMau.getText().trim();
        String tenMau = txtTenMau.getText().trim();
        boolean trangThai = rdoHDTT.isSelected();

        // Kiểm tra thông tin nhập vào
        

        MauSac newMau = new MauSac();
        newMau.setMaMauSac(maMau);
        newMau.setTenMauSac(tenMau);
        newMau.setTrangThai(trangThai);

        try {
            if (maMau.isEmpty() || tenMau.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin");
            return;
        }
            // Kiểm tra xem mã size có bị trùng không
            if (checkTrungMaSize(newMau.getMaMauSac())) {
                JOptionPane.showMessageDialog(this, "Mã Màu đã tồn tại");
                return;
            }

            // Thêm size vào cơ sở dữ liệu
            if (mss.addMauSac(newMau) != null) {
                JOptionPane.showMessageDialog(this, "Thêm Màu thành công");
                System.out.println("Thêm thành công");
                if (form_SanPham != null) {
                    form_SanPham.showCBOMS(); // Gọi phương thức làm mới JComboBox trên form chính
                }
                // Cập nhật dữ liệu trong form cha nếu cần
                // loadDataSize(ss.getAllSize());
                 clearFormTT();
                 dispose();
            } 
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Thêm Màu không thành công");
            e.printStackTrace(System.out);
        } 
    }
    private void onCancel() {
    dispose(); 
}

    private boolean checkTrungMaSize(String maMau) {
        boolean exists = false;
    String sql = "SELECT COUNT(*) FROM MAUSAC WHERE MAMAU = ?";

    try (Connection conn = DBConnect.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

        pstmt.setString(1, maMau);

        try (ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                int count = rs.getInt(1);
                exists = (count > 0); // Nếu có ít nhất 1 bản ghi, mã size đã tồn tại
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return exists;
      
    }

    private void clearFormTT() {
        txtMaMau.setText("");
        txtTenMau.setText("");
        rdoHDTT.isSelected();
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
