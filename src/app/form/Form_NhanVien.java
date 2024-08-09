/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package app.form;

import app.model.NhanVien;
import app.service.NhanVienService;
import app.util.MsgBox;
import app.util.excel;
import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.regex.Pattern;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ADMIN
 */
public class Form_NhanVien extends javax.swing.JPanel implements Runnable, ThreadFactory {

    private WebcamPanel panel = null;
    private Webcam webcam = null;
    private Executor executor = Executors.newSingleThreadExecutor(this);

    private static final String alpha = "abcdefghijklmnopqrstuvwxyz"; // a-z
    private static final String alphaUpperCase = alpha.toUpperCase(); // A-Z
    private static final String digits = "0123456789"; // 0-9
    private static final String ALPHA_NUMERIC = alpha + alphaUpperCase + digits;

    List<NhanVien> listNV = new ArrayList<>();
    NhanVienService service = new NhanVienService();
    DefaultTableModel model = new DefaultTableModel();
    DefaultComboBoxModel boxModel = new DefaultComboBoxModel();
    private int i = -1;
//    int limit = 4;
//    int maxNV = service.getMaxPage(limit);
//    private int page = 1;

    /**
     * Creates new form Form_NhanVien
     */
    public Form_NhanVien() {
        initComponents();
        initWebcam();
        fillComBoGioiTinh();
        i = service.getAll().size();
        model = (DefaultTableModel) tblNhanVien.getModel();
        listNV = service.getAll();
//                paging(page, 3);
        showData(listNV);
        txtMa.setEnabled(false);
    }

    public void showData(List<NhanVien> list) {
        model.setRowCount(0);
        int stt = tblNhanVien.getSelectedRow();
        stt += 1;
        for (NhanVien nv : list) {
            model.addRow(new Object[]{
                stt += 1,
                nv.getId(),
                nv.getMaNV(),
                nv.getTenNV(),
                nv.getNgSinh(),
                nv.isGioiTinh() ? "Nam" : "Nữ",
                nv.getSdt(),
                nv.getDiaChi(),
                nv.getEmail()
            });
        }
    }

    void fillComBoGioiTinh() {
        boxModel = (DefaultComboBoxModel) cboLoc.getModel();
        boxModel.removeAllElements();
        boxModel.addElement("Tất cả");
        boxModel.addElement("Nam");
        boxModel.addElement("Nữ");

    }

    public void showInfor(List<NhanVien> list, int viTri) {
        NhanVien nv = list.get(viTri);
        txtMa.setText(nv.getMaNV());
        txtTen.setText(nv.getTenNV());
        DateNgsinh.setDate(nv.getNgSinh());
        if (nv.isGioiTinh()) {
            rdNam.setSelected(true);
        } else {
            rdNu.setSelected(true);
        }
        txtDiaChi.setText(nv.getDiaChi());
        txtSDT.setText(nv.getSdt());
        txtEmail.setText(nv.getEmail());
    }

    private NhanVien getFormData() {
        SimpleDateFormat sds = new SimpleDateFormat("yyy-MM-dd");
        String manv = randomAlphaNumeric(4);
        String tennv = txtTen.getText();
        Date ns = null;
        try {
            ns = sds.parse(sds.format(DateNgsinh.getDate()));
        } catch (Exception e) {
        }
        boolean gioiTinh = rdNam.isSelected();
        String sdt = txtSDT.getText();
        String diaChi = txtDiaChi.getText();
        String email = txtEmail.getText();
        return new NhanVien(i, manv, tennv, ns, gioiTinh, diaChi, sdt, email, alpha);
    }

    public void reset() {
        txtMa.setText("");
        txtTen.setText("");
        DateNgsinh.setDate(null);
        txtSDT.setText("");
        txtDiaChi.setText("");
        txtEmail.setText("");
        buttonGroup1.clearSelection();
    }

    public boolean check() {
        String tenNV = txtTen.getText().trim();
        if (tenNV.trim().isEmpty()) {
            JOptionPane.showConfirmDialog(this, "Vui lòng nhập tên", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        String ngaySinh = DateNgsinh.getDateFormatString().trim();
        if (ngaySinh.trim().isEmpty()) {
            JOptionPane.showConfirmDialog(this, "Vui lòng nhập ngày sinh", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date ngaySinhDate;

        try {
            ngaySinhDate = format.parse(format.format(DateNgsinh.getDate())); // Get the date from the date picker directly

            // Check if the date is in the future
            Date currentDate = new Date();
            if (ngaySinhDate.after(currentDate)) {
                JOptionPane.showMessageDialog(this, "Ngày sinh không thể lớn hơn ngày hiện tại!");
                DateNgsinh.requestFocus();
                return false;
            }

        } catch (ParseException e) {
            JOptionPane.showMessageDialog(this, "Ngày sinh không đúng định dạng yyyy-MM-dd !");
            DateNgsinh.requestFocus();
            return false;
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, "Lỗi trong việc đặt định dạng ngày!");
            DateNgsinh.requestFocus();
            return false;
        }

        String sdt = txtSDT.getText().trim();
        if (sdt.trim().isEmpty()) {
            JOptionPane.showConfirmDialog(this, "Vui lòng nhập sđt", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        String email = txtEmail.getText().trim();
        if (email.trim().isEmpty()) {
            JOptionPane.showConfirmDialog(this, "Vui lòng nhập email", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        Pattern ptt1 = Pattern.compile("^[aAàÀảẢãÃáÁạẠăĂằẰẳẲẵẴắẮặẶâÂầẦẩẨẫẪấẤậẬbBcCdDđĐeEèÈẻẺẽẼéÉẹẸêÊềỀểỂễỄếẾệỆ\n"
                + "fFgGhHiIìÌỉỈĩĨíÍịỊjJkKlLmMnNoOòÒỏỎõÕóÓọỌôÔồỒổỔỗỖốỐộỘơƠờỜởỞỡỠớỚợỢpPqQrRsStTu\n"
                + "UùÙủỦũŨúÚụỤưƯừỪửỬữỮứỨựỰvVwWxXyYỳỲỷỶỹỸýÝỵỴzZ ]+$");
        if (ptt1.matcher(txtTen.getText()).find() == false || txtTen.getText().length() <= 2) {
            JOptionPane.showMessageDialog(this, "Tên nhân viên không hợp lệ(Tên không được phép có ký tự đặc biệt và số)");
            return false;
        }
        Pattern ptt2 = Pattern.compile("^0\\d{9,10}$");
        if (ptt2.matcher(sdt).find() == false) {
            JOptionPane.showMessageDialog(this, "Số điện thoại không hợp lệ");
            return false;
        }
        return true;
    }

    boolean checkTrungSDT() {
        for (NhanVien nv : service.getAll()) {
            if (nv.getSdt().equalsIgnoreCase(txtSDT.getText())) {
                JOptionPane.showConfirmDialog(this, "Số điện thoại nhân viên đã tồn tại");
                return false;
            }
        }
        return true;
    }
    private static Random generator = new Random();

    public static int randomNumber(int min, int max) {
        return generator.nextInt((max - min) + 1) + min;
    }

    public String randomAlphaNumeric(int numberOfCharactor) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numberOfCharactor; i++) {
            int number = randomNumber(0, ALPHA_NUMERIC.length() - 1);
            char ch = ALPHA_NUMERIC.charAt(number);
            sb.append(ch);
        }
        return sb.toString();
    }

    public void excelNV() throws IOException {
        excel.outExcel((DefaultTableModel) tblNhanVien.getModel());
        MsgBox.alert(this, "Xuất file thành công");
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        DateNgsinh = new com.toedter.calendar.JDateChooser();
        txtTen = new javax.swing.JTextField();
        txtSDT = new javax.swing.JTextField();
        txtDiaChi = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        rdNam = new javax.swing.JRadioButton();
        rdNu = new javax.swing.JRadioButton();
        btnClear = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnXuat = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblNhanVien = new javax.swing.JTable();
        btnPrev = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        txtTimKiem = new javax.swing.JTextField();
        lblSoTrang = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtMa = new javax.swing.JTextField();
        cam = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        result_field = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        cboLoc = new javax.swing.JComboBox<>();
        btnLoc = new javax.swing.JButton();

        jLabel1.setText("Họ và tên");

        jLabel2.setText("SĐT");

        jLabel3.setText("Giới tính");

        jLabel4.setText("Ngày sinh");

        jLabel5.setText("Địa chỉ");

        jLabel6.setText("Email");

        txtTen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdNam);
        rdNam.setSelected(true);
        rdNam.setText("Nam");

        buttonGroup1.add(rdNu);
        rdNu.setText("Nữ");

        btnClear.setText("Clear");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        btnAdd.setText("Thêm ");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnUpdate.setText("Sửa");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnXuat.setText("Xuất Excel");
        btnXuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatActionPerformed(evt);
            }
        });

        tblNhanVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "ID", "Mã NV", "Họ tên", "Ngày sinh", "Giới tính", "SDT", "Địa chỉ", "Email"
            }
        ));
        tblNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNhanVienMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblNhanVien);
        if (tblNhanVien.getColumnModel().getColumnCount() > 0) {
            tblNhanVien.getColumnModel().getColumn(1).setMinWidth(0);
            tblNhanVien.getColumnModel().getColumn(1).setPreferredWidth(0);
            tblNhanVien.getColumnModel().getColumn(1).setMaxWidth(0);
        }

        btnPrev.setText("<<<");
        btnPrev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrevActionPerformed(evt);
            }
        });

        btnNext.setText(">>>");
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });

        txtTimKiem.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtTimKiemFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtTimKiemFocusLost(evt);
            }
        });
        txtTimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemKeyReleased(evt);
            }
        });

        jLabel7.setText("Mã NV");

        cam.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new java.awt.BorderLayout());

        jLabel8.setText("Result");

        jLabel9.setText("Tìm kiếm");

        cboLoc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnLoc.setText("Lọc");
        btnLoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLocActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel7)))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtTen, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
                                    .addComponent(txtSDT)
                                    .addComponent(txtMa)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(rdNam)
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(btnUpdate)
                                            .addComponent(rdNu)))))
                            .addComponent(btnAdd))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(92, 92, 92)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel4)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnXoa)
                                .addGap(53, 53, 53)))
                        .addGap(59, 59, 59)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(DateNgsinh, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(241, 241, 241)
                        .addComponent(btnPrev)
                        .addGap(37, 37, 37)
                        .addComponent(lblSoTrang)
                        .addGap(40, 40, 40)
                        .addComponent(btnNext))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 686, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(jLabel9)
                        .addGap(33, 33, 33)
                        .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnXuat)
                                .addGap(55, 55, 55)
                                .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(62, 62, 62))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(84, 84, 84)
                                .addComponent(cboLoc, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnLoc)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(result_field, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(cam, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(38, 38, 38))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(33, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(DateNgsinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel4))
                                .addGap(31, 31, 31)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel5)
                                    .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(14, 14, 14)))
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rdNu)
                            .addComponent(rdNam)
                            .addComponent(jLabel3)))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cam, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnUpdate)
                    .addComponent(btnAdd)
                    .addComponent(btnXoa)
                    .addComponent(btnXuat)
                    .addComponent(btnClear)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(result_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(cboLoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLoc))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPrev)
                    .addComponent(btnNext)
                    .addComponent(lblSoTrang))
                .addContainerGap(131, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtTenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        int result = 0;
        List<NhanVien> list = service.getAll();
        int choose = tblNhanVien.getSelectedRow();
        JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa nhân viên này không ?", "", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            if (service.delete(getFormData(), txtMa.getText()) > 0) {
                JOptionPane.showMessageDialog(this, "Xóa thành công");
                model.removeRow(choose);
                reset();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Xóa thất bại");
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnPrevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevActionPerformed
        // TODO add your handling code here:
//        this.page--;
//        btnNext.setEnabled(true);
//        if (page < limit) {
//            btnPrev.setEnabled(false);
//        }
//        listNV = service.paging(page, limit);
//        showData(listNV);
//        lblSoTrang.setText(String.valueOf(page) + "/" + maxNV);
    }//GEN-LAST:event_btnPrevActionPerformed

    private void tblNhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNhanVienMouseClicked
        // TODO add your handling code here:
        showInfor(listNV, tblNhanVien.getSelectedRow());
    }//GEN-LAST:event_tblNhanVienMouseClicked

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        // TODO add your handling code here:
        txtMa.setText("");
        txtTen.setText("");
        DateNgsinh.setDateFormatString("");
        buttonGroup1.clearSelection();
        rdNam.setSelected(false);
        txtDiaChi.setText("");
        txtSDT.setText("");
        txtEmail.setText("");
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
        int result
                = JOptionPane.showConfirmDialog(this, "Bạn có muốn thêm nhân viên này không ?", "", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            if (check() && checkTrungSDT()) {
                if (service.addNV(getFormData()) > 0) {
                    JOptionPane.showMessageDialog(this, "Thêm thành công");
                    showData(service.getAll());
                    reset();
                } else {
                    JOptionPane.showMessageDialog(this, "Thêm không thành công");
                }

            }
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        // TODO add your handling code here:
//        this.page++;
//        btnPrev.setEnabled(true);
//        if (page >= maxNV) {
//            btnNext.setEnabled(false);
//        }
//        listNV = service.paging(page, limit);
//        showData(listNV);
//        lblSoTrang.setText(String.valueOf(page) + "/" + maxNV);
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
        int result
                = JOptionPane.showConfirmDialog(null, "Bạn có muốn sửa nhân viên này không ?", "", JOptionPane.YES_NO_OPTION);
        NhanVien nv = service.getAll().get(tblNhanVien.getSelectedRow());
        if (result == JOptionPane.YES_OPTION) {
            if (check()) {
                if (service.update(getFormData(), nv.getId()) > 0) {
                    System.out.println("id" + nv.getId());
                    JOptionPane.showMessageDialog(this, "Sửa thành công");
                    showData(service.getAll());
                    reset();
                } else {
                    System.out.println("1");
                    JOptionPane.showMessageDialog(this, "Sửa không thành công");
                }
            }
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnXuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatActionPerformed
        // TODO add your handling code here:
        try {
            excelNV();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnXuatActionPerformed

    private void txtTimKiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyReleased
        // TODO add your handling code here:
        String timKiem = txtTimKiem.getText().trim();
        showData(service.searchAllField(timKiem));
        if (timKiem.trim().isEmpty()) {
            showData(service.getAll());
        }
    }//GEN-LAST:event_txtTimKiemKeyReleased

    private void txtTimKiemFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTimKiemFocusGained
        // TODO add your handling code here:
        if (txtTimKiem.getText().equals("Mã nhân viên - Tên nhân viên - Số điện thoại")) {
            txtTimKiem.setText("");
            setForeground(Color.BLACK);
        }
    }//GEN-LAST:event_txtTimKiemFocusGained

    private void txtTimKiemFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTimKiemFocusLost
        // TODO add your handling code here:
        if (txtTimKiem.getText().equals("")) {
            txtTimKiem.setText("Mã nhân viên - Tên nhân viên - Số điện thoại");
            setForeground(new Color(53, 153, 153));
        }
    }//GEN-LAST:event_txtTimKiemFocusLost

    private void btnLocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLocActionPerformed
        // TODO add your handling code here:
//        String gender = cboLoc.getSelectedItem().toString();
//        List<NhanVien> listNV = new ArrayList<>(); 
//
//        if ("Tất cả".equals(gender)) {
//            // Nếu chọn "Tất cả", không lọc theo giới tính
//            listNV = service.getAll();
//        } else {
//            // Nếu chọn giới tính cụ thể, lọc theo giới tính đó
//            service.loc(gender);
//        }
//
//        showData(listNV);
        String gender = cboLoc.getSelectedItem().toString();
        List<NhanVien> listNV = new ArrayList<>(); // Khởi tạo danh sách trống để tránh lỗi

        if ("Tất cả".equals(gender)) {
            // Nếu chọn "Tất cả", không lọc theo giới tính
            listNV = service.getAll();
        } else {
            // Nếu chọn giới tính cụ thể, lọc theo giới tính đó
            listNV = service.loc(gender);  // Gán kết quả lọc cho listNV
        }

        showData(listNV);

    }//GEN-LAST:event_btnLocActionPerformed

    private void initWebcam() {
        Dimension size = WebcamResolution.QVGA.getSize();
        webcam = webcam.getWebcams().get(0);
        webcam.setViewSize(size);

        panel = new WebcamPanel(webcam);
        panel.setPreferredSize(size);
        panel.setFPSDisplayed(true);

        cam.add(panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 470, 300));
        executor.execute(this);
    }

    @Override
    public void run() {
        do {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }

            Result result = null;
            BufferedImage image = null;
            if (webcam.isOpen()) {
                if ((image = webcam.getImage()) == null) {
                    continue;
                }
            }

            LuminanceSource source = new BufferedImageLuminanceSource(image);
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

            try {
                result = new MultiFormatReader().decode(bitmap);
            } catch (NotFoundException ex) {
                ex.printStackTrace();
            }

            if (result != null) {
                result_field.setText(result.getText());
            } else {
                System.out.println(result);
            }

        } while (true);
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r, "My Thread");
        t.setDaemon(true);
        return t;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser DateNgsinh;
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnLoc;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPrev;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JButton btnXoa;
    private javax.swing.JButton btnXuat;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JPanel cam;
    private javax.swing.JComboBox<String> cboLoc;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblSoTrang;
    private javax.swing.JRadioButton rdNam;
    private javax.swing.JRadioButton rdNu;
    private javax.swing.JTextField result_field;
    private javax.swing.JTable tblNhanVien;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtMa;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTen;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
