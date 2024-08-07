/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package app.form;

import app.model.HoaDon;
import app.model.KhachHang;
import app.service.KhachHangService;
import java.io.File;
import java.io.FileOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author dungn
 */
public class Form_KhachHang extends javax.swing.JPanel {

    /**
     * Creates new form Form_KhachHang
     */
    KhachHangService khService = new KhachHangService();
    DefaultTableModel tbl = new DefaultTableModel();
    DefaultComboBoxModel boxModel = new DefaultComboBoxModel();
    List<KhachHang> listKh = new ArrayList();
    SimpleDateFormat sds = new SimpleDateFormat("yyy-MM-dd");
    private int i = -1;

    public Form_KhachHang() {
        initComponents();
        tbl = (DefaultTableModel) tblKhachHang.getModel();
        this.fillTable(khService.getall());
        i = khService.getall().size();
        fillComBoGioiTinh();
        txtmaKH.setEnabled(false);

    }

    void fillComBoGioiTinh() {
        boxModel = (DefaultComboBoxModel) cboLoc.getModel();
        boxModel.removeAllElements();
        boxModel.addElement("Tất cả");
        boxModel.addElement("Nam");
        boxModel.addElement("Nữ");

    }

    private void fillTable(List<KhachHang> list) {
        tbl.setRowCount(0);
        int stt = tblKhachHang.getSelectedRow();
        stt += 1;
        for (KhachHang khachHang : list) {
            tbl.addRow(new Object[]{
                stt += 1,
                khachHang.getId(),
                khachHang.getMaKH(),
                khachHang.getTenKH(),
                khachHang.isGioiTinh() ? "Nam" : "Nữ",
                khachHang.getNgaySinh(),
                khachHang.getEmail(),
                khachHang.getSdt()
            }
            );
        }
    }

    void showTable(KhachHang kh) {
        txtmaKH.setText(kh.getMaKH());
        txtEmail.setText(kh.getEmail());
        txtNgaysinh.setDate(kh.getNgaySinh());
        txttenKH.setText(kh.getTenKH());
        txtSDt.setText(kh.getSdt());
        if (kh.isGioiTinh()) {
            rdnam.setSelected(true);
        } else {
            rdNu.setSelected(true);
        }

    }

    public void reset() {
        txtEmail.setText("");
        txtNgaysinh.setDate(null);
        txtSDt.setText("");
        txttenKH.setText("");
        txtmaKH.setText("");
        buttonGroup1.clearSelection();

    }

    public boolean check() {

        String tenKhachHang = txttenKH.getText().trim();
        if (tenKhachHang.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập tên!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        Date ngaySinh = txtNgaysinh.getDate();
        if (ngaySinh == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập ngày sinh!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        Date today = new Date();
        if (ngaySinh.after(today)) {
            JOptionPane.showMessageDialog(this, "Ngày sinh không được sau ngày hôm nay!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        String email = txtEmail.getText().trim();
        if (tenKhachHang.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập tên!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        String sdt = txtSDt.getText().trim();
        if (sdt.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập tên!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
            // Kiểm tra độ dài tên
    if (tenKhachHang.length() < 2) {
        JOptionPane.showMessageDialog(this, "Tên khách hàng phải có ít nhất 2 ký tự");
        return false;
    }

        Pattern ptt1 = Pattern.compile("^[aAàÀảẢãÃáÁạẠăĂằẰẳẲẵẴắẮặẶâÂầẦẩẨẫẪấẤậẬbBcCdDđĐeEèÈẻẺẽẼéÉẹẸêÊềỀểỂễỄếẾệỆ\n"
                + "fFgGhHiIìÌỉỈĩĨíÍịỊjJkKlLmMnNoOòÒỏỎõÕóÓọỌôÔồỒổỔỗỖốỐộỘơƠờỜởỞỡỠớỚợỢpPqQrRsStTu\n"
                + "UùÙủỦũŨúÚụỤưƯừỪửỬữỮứỨựỰvVwWxXyYỳỲỷỶỹỸýÝỵỴzZ ]+$");

// Kiểm tra nếu tên không hợp lệ hoặc độ dài dưới 2 ký tự
        if (ptt1.matcher(txttenKH.getText()).find() == false ) {
            JOptionPane.showMessageDialog(this, "Tên khách hàng không hợp lệ(Tên không được phép có ký tự đặc biệt và số)");
            return false;
        }

        Pattern ptt2 = Pattern.compile("^0\\d{9,10}$");
        if (ptt2.matcher(sdt).find() == false) {
            JOptionPane.showMessageDialog(this, "Số điện thoại bắt đầu bằng 0 và có 10 số");
            return false;
        }

        Pattern emailPattern = Pattern.compile("^[\\w\\.-]+@[\\w\\.-]+\\.[a-zA-Z]{2,6}$");
        if (!emailPattern.matcher(email).matches()) {
            JOptionPane.showMessageDialog(this, "Email không hợp lệ");
            return false;
        }

        return true;

    }

    boolean checkTrungSDT() {
        for (KhachHang kh : khService.getall()) {
            if (kh.getSdt().equalsIgnoreCase(txtSDt.getText())) {
                JOptionPane.showMessageDialog(this, "Số điện thoại khách hàng đã tồn tại");
                return false;
            }
        }
        return true;
    }

    private KhachHang getForm() {
        SimpleDateFormat sds = new SimpleDateFormat("yyy-MM-dd");
        String maKH;
        String ten;
        String sdt;
        Date ns = null;
        try {
            ns = sds.parse(sds.format(txtNgaysinh.getDate()));
        } catch (Exception e) {

        }
        String email;

        Date ngayTao;

        boolean gioiTinh;
        maKH = "KH" + new Random().nextInt(10000);
        for (KhachHang kh : khService.getall()) {
            if (kh.getMaKH().equalsIgnoreCase(maKH)) {
                maKH = "KH" + new Random().nextInt(10000);
            }
        }

        ten = txttenKH.getText();
        sdt = txtSDt.getText();

        email = txtEmail.getText();
        ngayTao = new java.util.Date();
        gioiTinh = rdnam.isSelected();

        return new KhachHang(maKH, ten, sdt, ns, email, ngayTao, gioiTinh, true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtmaKH = new javax.swing.JTextField();
        txttenKH = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        txtSDt = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        rdnam = new javax.swing.JRadioButton();
        rdNu = new javax.swing.JRadioButton();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        txtTimkiem = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        cboLoc = new javax.swing.JComboBox<>();
        btnLoc = new javax.swing.JButton();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanelKhachHang = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblKhachHang = new javax.swing.JTable();
        jPanelLichSuKhachHang = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblLichSuMuaHang = new javax.swing.JTable();
        btnxoa1 = new javax.swing.JButton();
        txtNgaysinh = new com.toedter.calendar.JDateChooser();
        btnXuat = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setText("Mã khách hàng:");

        txtmaKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtmaKHActionPerformed(evt);
            }
        });

        txttenKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txttenKHActionPerformed(evt);
            }
        });

        jLabel2.setText("Giới tính:");

        txtEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmailActionPerformed(evt);
            }
        });

        txtSDt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSDtActionPerformed(evt);
            }
        });

        jLabel4.setText("Tên khách hàng:");

        jLabel5.setText("Ngày sinh:");

        jLabel6.setText("Email:");

        jLabel7.setText("Số điện thoại:");

        buttonGroup1.add(rdnam);
        rdnam.setSelected(true);
        rdnam.setText("Nam");

        buttonGroup1.add(rdNu);
        rdNu.setText("Nữ");
        rdNu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdNuActionPerformed(evt);
            }
        });

        btnThem.setBackground(new java.awt.Color(238, 250, 250));
        btnThem.setText("Thêm");
        btnThem.setMaximumSize(new java.awt.Dimension(72, 31));
        btnThem.setMinimumSize(new java.awt.Dimension(72, 31));
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setBackground(new java.awt.Color(238, 250, 250));
        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnClear.setBackground(new java.awt.Color(238, 250, 250));
        btnClear.setText("Làm mới");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        txtTimkiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimkiemKeyReleased(evt);
            }
        });

        jLabel8.setText(" Tìm kiếm:");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Lọc"));

        cboLoc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboLoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboLocActionPerformed(evt);
            }
        });

        btnLoc.setBackground(new java.awt.Color(238, 250, 250));
        btnLoc.setText("Lọc");
        btnLoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLocActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cboLoc, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addComponent(btnLoc)
                .addGap(22, 22, 22))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboLoc, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLoc, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jTabbedPane2.setBackground(new java.awt.Color(255, 255, 255));

        jPanelKhachHang.setBackground(new java.awt.Color(255, 255, 255));

        tblKhachHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "ID", "Mã khách hàng", "Tên khách hàng", "Giới tính", "Ngày sinh", "Email", "SDT"
            }
        ));
        tblKhachHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblKhachHangMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblKhachHang);
        if (tblKhachHang.getColumnModel().getColumnCount() > 0) {
            tblKhachHang.getColumnModel().getColumn(1).setMinWidth(0);
            tblKhachHang.getColumnModel().getColumn(1).setPreferredWidth(0);
            tblKhachHang.getColumnModel().getColumn(1).setMaxWidth(0);
        }

        javax.swing.GroupLayout jPanelKhachHangLayout = new javax.swing.GroupLayout(jPanelKhachHang);
        jPanelKhachHang.setLayout(jPanelKhachHangLayout);
        jPanelKhachHangLayout.setHorizontalGroup(
            jPanelKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelKhachHangLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 821, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
        );
        jPanelKhachHangLayout.setVerticalGroup(
            jPanelKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelKhachHangLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(44, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Thông tin khách hàng", jPanelKhachHang);

        jPanelLichSuKhachHang.setBackground(new java.awt.Color(255, 255, 255));

        tblLichSuMuaHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã hóa đơn", "Ngày tạo", "Tổng tiền", "Trạng thái"
            }
        ));
        tblLichSuMuaHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblLichSuMuaHangMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblLichSuMuaHang);

        javax.swing.GroupLayout jPanelLichSuKhachHangLayout = new javax.swing.GroupLayout(jPanelLichSuKhachHang);
        jPanelLichSuKhachHang.setLayout(jPanelLichSuKhachHangLayout);
        jPanelLichSuKhachHangLayout.setHorizontalGroup(
            jPanelLichSuKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLichSuKhachHangLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 818, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );
        jPanelLichSuKhachHangLayout.setVerticalGroup(
            jPanelLichSuKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLichSuKhachHangLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(52, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Lịch sử khách hàng", jPanelLichSuKhachHang);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jTabbedPane2)
                        .addGap(40, 40, 40))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtTimkiem, javax.swing.GroupLayout.PREFERRED_SIZE, 484, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTimkiem, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnxoa1.setBackground(new java.awt.Color(238, 250, 250));
        btnxoa1.setText("Xóa");
        btnxoa1.setToolTipText("");
        btnxoa1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnxoa1ActionPerformed(evt);
            }
        });

        txtNgaysinh.setDateFormatString("yyyy-MM-dd");

        btnXuat.setBackground(new java.awt.Color(238, 250, 250));
        btnXuat.setText("Xuất excel");
        btnXuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addContainerGap())
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGap(31, 31, 31)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGap(17, 17, 17)
                                        .addComponent(rdnam)
                                        .addGap(47, 47, 47)
                                        .addComponent(rdNu))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtmaKH, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txttenKH, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                                .addGap(66, 66, 66)
                                .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(72, 72, 72)
                                .addComponent(btnSua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel4Layout.createSequentialGroup()
                                                .addComponent(jLabel7)
                                                .addGap(39, 39, 39))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                                .addComponent(jLabel5)
                                                .addGap(57, 57, 57)))
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtNgaysinh, javax.swing.GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)
                                            .addComponent(txtEmail)
                                            .addComponent(txtSDt))))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(82, 82, 82)
                                .addComponent(btnxoa1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(86, 86, 86)
                                .addComponent(btnXuat)
                                .addGap(67, 67, 67))))))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 50, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(txtmaKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3))
                    .addComponent(txtNgaysinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdNu)
                    .addComponent(jLabel2)
                    .addComponent(rdnam))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtSDt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4)
                        .addComponent(jLabel7))
                    .addComponent(txttenKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnSua)
                        .addComponent(btnxoa1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnXuat)
                        .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel4Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnClear, btnSua, btnThem, btnXuat});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtmaKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtmaKHActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtmaKHActionPerformed

    private void txttenKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txttenKHActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txttenKHActionPerformed

    private void txtEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmailActionPerformed

    private void txtSDtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSDtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSDtActionPerformed

    private void rdNuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdNuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdNuActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        int result
                = JOptionPane.showConfirmDialog(null, "Bạn có muốn thêm khách hàng này không ?", "", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            if (check() && checkTrungSDT()) {
                if (khService.addKh(getForm()) > 0) {
                    JOptionPane.showMessageDialog(this, "Thêm thành công!");
                    fillTable(khService.getall());
                    reset();
                } else {
                    JOptionPane.showMessageDialog(this, "Thêm thất bại!");
                }
            }
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        int result
                = JOptionPane.showConfirmDialog(null, "Bạn có muốn sửa Khách Hàng này không ?", "", JOptionPane.YES_NO_OPTION);
        KhachHang kh = khService.getall().get(tblKhachHang.getSelectedRow());

        if (result == JOptionPane.YES_OPTION) {
            if (check()) {

                if (khService.updateKh(getForm(), kh.getId()) > 0) {
                    System.out.println("id" + kh.getId());
                    JOptionPane.showMessageDialog(this, "Sửa thành công!");
                    fillTable(khService.getall());
                    reset();

                } else {
                    System.out.println("1");
                    JOptionPane.showMessageDialog(this, "Sửa thất bại!");
                }
            }
        }

    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        reset();
    }//GEN-LAST:event_btnClearActionPerformed

    private void txtTimkiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimkiemKeyReleased
        String timkiem = txtTimkiem.getText();
        fillTable(khService.search(timkiem));
        if (timkiem.trim().isEmpty()) {
            fillTable(khService.getall());

        }
    }//GEN-LAST:event_txtTimkiemKeyReleased

    private void btnxoa1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnxoa1ActionPerformed
        // TODO add your handling code here:
        int result = 0;
        List<KhachHang> list = khService.getall();
        int choose = tblKhachHang.getSelectedRow();
        JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa khách hàng này không ?", "", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            if (khService.deleteKH(getForm(), txtmaKH.getText()) > 0) {
                JOptionPane.showMessageDialog(this, "Xóa thành công!");
                tbl.removeRow(choose);
                reset();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Xóa thất bại");
        }
    }//GEN-LAST:event_btnxoa1ActionPerformed

    private void tblKhachHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKhachHangMouseClicked
        // TODO add your handling code here:
    int index = tblKhachHang.getSelectedRow();
//        fillTable(khService.getall());
        if (i >= 0) {
            Object value = tblKhachHang.getValueAt(index, 1);
            String maKH = value != null ? value.toString() : "";
            showTable(khService.getAt(index));
            fillTableLSHD(khService.timLichSuHD(maKH));
        }

    }//GEN-LAST:event_tblKhachHangMouseClicked
 
    private void fillTableLSHD(List<HoaDon> list) {
        tbl = (DefaultTableModel) tblLichSuMuaHang.getModel();
        tbl.setRowCount(0);
        int stt = 1;
        for (HoaDon hoaDon : list) {
            Object[] row = new Object[]{
                stt, hoaDon.getMaHoaDon(), hoaDon.getNgayTao(), hoaDon.getTongTien(), hoaDon.isTrangThai() ? "Đã thanh toán" : "Chưa thanh toán"
            };
            stt += 1;
            tbl.addRow(row);
        }
    }
 
    private void btnXuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatActionPerformed
        // TODO add your handling code here:
        // TODO add your handling code here:
        try {
            XSSFWorkbook workBook = new XSSFWorkbook();
            XSSFSheet sheet = workBook.createSheet("Khach Hang");
            XSSFRow row = null;
            Cell cell = null;
            row = sheet.createRow(0);
            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue("STT");
            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue("Mã khách hàng");
            cell = row.createCell(2, CellType.STRING);
            cell.setCellValue("Tên khách hàng");
            cell = row.createCell(3, CellType.STRING);
            cell.setCellValue("Giới tính");
            cell = row.createCell(4, CellType.STRING);
            cell.setCellValue("Ngày sinh");
            cell = row.createCell(5, CellType.STRING);
            cell.setCellValue("Email");
            cell = row.createCell(6, CellType.STRING);
            cell.setCellValue("SDT");
            List<KhachHang> list = khService.getall();
            if (list != null) {
                int s = list.size();
                for (int i = 0; i < s; i++) {
                    KhachHang kh = list.get(i);
                    row = sheet.createRow(i + 1);
                    cell = row.createCell(0, CellType.NUMERIC);
                    cell.setCellValue(i + 1);
                    cell = row.createCell(1, CellType.STRING);
                    cell.setCellValue(list.get(i).getMaKH());
                    cell = row.createCell(2, CellType.STRING);
                    cell.setCellValue(list.get(i).getTenKH());
                    cell = row.createCell(3, CellType.STRING);
                    boolean gioiTinh = list.get(i).isGioiTinh(); // giả sử getGioiTinh() trả về boolean
                    if (gioiTinh) {
                        cell.setCellValue("Nam");
                    } else {
                        cell.setCellValue("Nữ");
                    }
                    cell = row.createCell(4, CellType.STRING);
                    cell.setCellValue(sds.format(list.get(i).getNgaySinh()).toString());
                    cell = row.createCell(5, CellType.STRING);
                    cell.setCellValue(list.get(i).getEmail());
                    cell = row.createCell(6, CellType.STRING);
                    cell.setCellValue(list.get(i).getSdt());

                }
            }
            // save file
            JFileChooser jfileChooser = new JFileChooser("D:\\");
            jfileChooser.showSaveDialog(this);
            File file = jfileChooser.getSelectedFile();
            if (file != null) {
                try {
                    file = new File(file.toString() + ".xlsx");
                    FileOutputStream fiO = new FileOutputStream(file);
                    workBook.write(fiO);
                    fiO.close();
                    JOptionPane.showMessageDialog(this, "Xuất thành Công");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Xuất thất Bại");
        }
    }//GEN-LAST:event_btnXuatActionPerformed

    private void cboLocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboLocActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_cboLocActionPerformed

    private void btnLocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLocActionPerformed
        // TODO add your handling code here:
        String gender = cboLoc.getSelectedItem().toString();
        List<KhachHang> listKh;

        if ("Tất cả".equals(gender)) {
            // Nếu chọn "Tất cả", không lọc theo giới tính
            listKh = khService.getall();
        } else {
            // Nếu chọn giới tính cụ thể, lọc theo giới tính đó
            listKh = khService.loc(gender);
        }

        System.out.println("btnLocActionPerformed() loc() returned " + listKh.size() + " items.");  // Debug output
        fillTable(listKh);
    }//GEN-LAST:event_btnLocActionPerformed

    private void tblLichSuMuaHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblLichSuMuaHangMouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_tblLichSuMuaHangMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnLoc;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXuat;
    private javax.swing.JButton btnxoa1;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cboLoc;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanelKhachHang;
    private javax.swing.JPanel jPanelLichSuKhachHang;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JRadioButton rdNu;
    private javax.swing.JRadioButton rdnam;
    private javax.swing.JTable tblKhachHang;
    private javax.swing.JTable tblLichSuMuaHang;
    private javax.swing.JTextField txtEmail;
    private com.toedter.calendar.JDateChooser txtNgaysinh;
    private javax.swing.JTextField txtSDt;
    private javax.swing.JTextField txtTimkiem;
    private javax.swing.JTextField txtmaKH;
    private javax.swing.JTextField txttenKH;
    // End of variables declaration//GEN-END:variables

}
