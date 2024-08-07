/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.form.FormTT;

import app.form.Form_SanPham;
import app.model.Size;
import app.service.DBConnect;
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
public class SizeForm extends JFrame {

    private JTextField txtMaSize;
    private JTextField txtTenSize;
    private JRadioButton rdoHDTT;
    private JRadioButton rdoKhongHDTT;
    private JButton btnConfirm;
    private JButton btnCancel;
    private final SizeService ss = new SizeService();
    private DefaultComboBoxModel dcbmSize = new DefaultComboBoxModel();
    private Form_SanPham form_SanPham;

    public SizeForm(Form_SanPham  form_SanPham) {
        this.form_SanPham = form_SanPham;
        setTitle("Thêm Size Mới");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5, 2, 5, 5));

        JLabel lblMaSize = new JLabel("Mã Size:");
        txtMaSize = new JTextField();

        JLabel lblTenSize = new JLabel("Tên Size:");
        txtTenSize = new JTextField();

        JLabel lblTrangThai = new JLabel("Trạng Thái:");

        rdoHDTT = new JRadioButton("Hoạt động", true);
        rdoKhongHDTT = new JRadioButton("Không hoạt động");

        ButtonGroup group = new ButtonGroup();
        group.add(rdoHDTT);
        group.add(rdoKhongHDTT);

        btnConfirm = new JButton("Xác nhận");
        btnCancel = new JButton("Hủy bỏ");

        add(lblMaSize);
        add(txtMaSize);
        add(lblTenSize);
        add(txtTenSize);
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
        String maSize = txtMaSize.getText().trim();
        String tenSize = txtTenSize.getText().trim();
        boolean trangThai = rdoHDTT.isSelected();

        // Kiểm tra thông tin nhập vào
        

        Size newSize = new Size();
        newSize.setMaSize(maSize);
        newSize.setTenSize(tenSize);
        newSize.setTrangThai(trangThai);

        try {
            if (maSize.isEmpty() || tenSize.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin");
            return;
        }
            // Kiểm tra xem mã size có bị trùng không
            if (checkTrungMaSize(newSize.getMaSize())) {
                JOptionPane.showMessageDialog(this, "Mã size đã tồn tại");
                return;
            }

            // Thêm size vào cơ sở dữ liệu
            if (ss.addSize(newSize) != null) {
                JOptionPane.showMessageDialog(this, "Thêm size thành công");
                System.out.println("Thêm thành công");
                if (form_SanPham != null) {
                    form_SanPham.showCBOSize(); // Gọi phương thức làm mới JComboBox trên form chính
                }
                // Cập nhật dữ liệu trong form cha nếu cần
                // loadDataSize(ss.getAllSize());
                 clearFormTT();
                 dispose();
            } 
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Thêm size không thành công");
            e.printStackTrace(System.out);
        } 
    }
    private void onCancel() {
    dispose(); 
}

    private boolean checkTrungMaSize(String maSize) {
        boolean exists = false;
    String sql = "SELECT COUNT(*) FROM SIZE WHERE MASIZE = ?";

    try (Connection conn = DBConnect.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

        pstmt.setString(1, maSize);

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

    
    private Size getSizeFormInput() {
        Size s = new Size();
        s.setMaSize(txtMaSize.getText());
        s.setTenSize(txtTenSize.getText());
        s.setTrangThai(rdoHDTT.isSelected() ? true : false);
        return s;
    }

//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                new SizeForm().setVisible(true);
//            }
//        });
//    }

    private void clearFormTT() {
        txtMaSize.setText("");
        txtTenSize.setText("");
        rdoHDTT.isSelected();
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
