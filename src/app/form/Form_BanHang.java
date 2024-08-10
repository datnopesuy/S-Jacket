/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package app.form;

import app.model.ChatLieu;
import app.model.GioHang;
import app.model.HoaDon;
import app.model.HoaDonChiTiet;
import app.model.HoaDonChiTiet1;
import app.model.KhachHang;
import app.model.KieuDang;
import app.model.LopLot;
import app.model.MauSac;
import app.model.Mu;
import app.model.SanPham;
import app.model.SanPhamChiTiet;
import app.model.Size;
import app.model.Stringname;
import app.model.Voucher;
import app.service.BanHangService;
import app.service.GioHangService;
import app.service.KhachHangService;
import app.service.SanPhamChiTietService;
import app.service.VoucherService;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author dungn
 */
public class Form_BanHang extends javax.swing.JPanel {

    DefaultTableModel tblModelHD = new DefaultTableModel();
    DefaultTableModel tblModelSPCT = new DefaultTableModel();
    DefaultTableModel tblModelGH = new DefaultTableModel();
    private final BanHangService bhs = new BanHangService();
    private final SanPhamChiTietService spcts = new SanPhamChiTietService();
    private final GioHangService ghs = new GioHangService();
    private final KhachHangService khs = new KhachHangService();
    private final VoucherService voucherService = new VoucherService();
    int indexHDC = - 1;
    int indexDSSP = - 1;
    int soLuongMua = 0;
    double tongTien = 0;
    double tienKhachTra = 0;
    double tienThua = 0;

    /**
     * Creates new form Form_BanHang
     */
    public Form_BanHang() {
        initComponents();
        loadFormHDC();
        loadFormSPCT();
        txtLoai.setText("Tiền");
        Stringname name = new Stringname();
        txtNVBan.setText(name.toString());
        txtNVBan.setEnabled(false);
        txtTienHD.setText("0");

    }

    private void tinhTien() {
        tongTien = Double.valueOf(txtTienHD.getText());
        tienKhachTra = Double.valueOf(txtTienKhachTra.getText());
        tienThua = tongTien - tienKhachTra;
        txtTienThua.setText(String.valueOf(tienThua));
    }

    private void LoadDataGH(ArrayList<GioHang> list) {
        tblModelGH.setRowCount(0);
        for (GioHang gh : list) {
            tblModelGH.addRow(new Object[]{
                gh.getSanPhamChiTiet().getIdSPCT(),
                gh.getSanPham().getTenSP(),
                gh.getMauSac().getTenMauSac(),
                gh.getChatLieu().getMaChatLieu(),
                gh.getLopLot().getTenLopLot(),
                gh.getMu().getTenMu(),
                gh.getSize().getMaSize(),
                gh.getKieuDang().getTenKieuDang(),
                gh.getSoLuong(),
                gh.getThanhTien()
            });
        }
    }

    private void LoadFormGH() {
        tblGH.removeAll();
        tblGH.setModel(tblModelGH);
        String headerSP[] = {
            "IDSP", "Tên sản phẩm", "Màu sắc", "Chất liệu", "Lớp lót", "Mũ", "Size", "Kiểu dáng", "Số lượng", "Thành tiền"
        };
        tblModelGH.setColumnIdentifiers(headerSP);
        tblModelGH = (DefaultTableModel) tblGH.getModel();
        int index = tblHDC.getSelectedRow();
        String maHD = tblHDC.getValueAt(index, 0).toString();
        LoadDataGH(ghs.getGioHangByMaHD(maHD));
    }

    private void LoadDataHDC(ArrayList<HoaDon> list) {
        tblModelHD.setRowCount(0);

        for (HoaDon hd : bhs.getAllHDC()) {
            tblModelHD.addRow(new Object[]{
                hd.getMaHoaDon(),
                hd.getNgayTao(),
                hd.isTrangThai() ? "Đã thanh toán" : "Hóa đơn chờ"
            });
        }
    }

    private void loadFormHDC() {
        tblHDC.removeAll();
        tblHDC.setModel(tblModelHD);
        String headerSP[] = {
            "Mã hóa đơn", "Ngày tạo", "Trạng thái"
        };
        tblModelHD.setColumnIdentifiers(headerSP);
        tblModelHD = (DefaultTableModel) tblHDC.getModel();
        LoadDataHDC(bhs.getAllHDC());
    }

    private void loadDataSPCT(ArrayList<SanPhamChiTiet> listSPCT) {
        tblModelSPCT.setRowCount(0);
        for (SanPhamChiTiet spct : listSPCT) {
            tblModelSPCT.addRow(new Object[]{
                spct.getIdSPCT(),
                spct.getSanPham().getMaSP(),
                spct.getSanPham().getTenSP(),
                spct.getMauSac().getTenMauSac(),
                spct.getChatLieu().getMaChatLieu(),
                spct.getLopLot().getTenLopLot(),
                spct.getMu().getTenMu(),
                spct.getSize().getMaSize(),
                spct.getKieuDang().getTenKieuDang(),
                spct.getSoLuong(),
                spct.getGia()
            });
        }
    }

    private void getDSSPTable(int soLuongMua) {
        SanPhamChiTiet spct = new SanPhamChiTiet();
        int indexDSSP = tblDSSP.getSelectedRow();
        SanPham sp = new SanPham();
        sp.setMaSP(tblDSSP.getValueAt(indexDSSP, 0).toString());

        MauSac ms = new MauSac();
        ms.setTenMauSac(tblDSSP.getValueAt(indexDSSP, 1).toString());

        ChatLieu cl = new ChatLieu();
        cl.setTenChatLieu(tblDSSP.getValueAt(indexDSSP, 2).toString());

        KieuDang kd = new KieuDang();
        kd.setTenKieuDang(tblDSSP.getValueAt(indexDSSP, 3).toString());

        Size s = new Size();
        s.setMaSize(tblDSSP.getValueAt(indexDSSP, 4).toString());

        LopLot ll = new LopLot();
        ll.setTenLopLot(tblDSSP.getValueAt(indexDSSP, 5).toString());

        Mu m = new Mu();
        m.setTenMu(tblDSSP.getValueAt(indexDSSP, 6).toString());

        spct.setSanPham(sp);
        spct.setChatLieu(cl);
        spct.setMauSac(ms);
        spct.setKieuDang(kd);
        spct.setLopLot(ll);
        spct.setSize(s);
        spct.setMu(m);
        spct.setSoLuong(soLuongMua);
        spct.setGia(Double.valueOf(tblDSSP.getValueAt(indexDSSP, 9).toString()));
    }

    private HoaDonChiTiet1 getSPCTFROMDSSP() {
        indexDSSP = tblDSSP.getSelectedRow();
        indexHDC = tblHDC.getSelectedRow();
        SanPhamChiTiet spct = new SanPhamChiTiet();
        spct.setIdSPCT(Integer.valueOf(tblDSSP.getValueAt(indexDSSP, 0).toString()));
        HoaDonChiTiet1 hdct1 = new HoaDonChiTiet1();
        hdct1.setSanPhamChiTiet(spct);
        hdct1.setSoLuong(soLuongMua);
        hdct1.setGia(Double.parseDouble(tblDSSP.getValueAt(indexDSSP, 10).toString()));
        return hdct1;
    }

    private HoaDonChiTiet1 getSPCTFromGH() {
        indexDSSP = tblDSSP.getSelectedRow();
        indexHDC = tblHDC.getSelectedRow();
        int indexGH = tblGH.getSelectedRow();
        SanPhamChiTiet spct = new SanPhamChiTiet();
        spct.setIdSPCT(Integer.valueOf(tblGH.getValueAt(indexGH, 0).toString()));
        HoaDonChiTiet1 hdct1 = new HoaDonChiTiet1();
        hdct1.setSanPhamChiTiet(spct);
        hdct1.setSoLuong(soLuongMua);
        hdct1.setGia(Double.parseDouble(tblGH.getValueAt(indexGH, 9).toString()));
        return hdct1;
    }

    private void loadFormSPCT() {
        tblDSSP.removeAll();
        tblDSSP.setModel(tblModelSPCT);
        String headerSPCT[] = {
            "IDSP", "Mã sản phẩm", "Tên sản phẩm", "Màu sắc", "Chất liệu", "Lớp lót", "Mũ", "Size", "Kiểu dáng", "Số lượng", "Giá"
        };
        tblModelSPCT.setColumnIdentifiers(headerSPCT);
        tblModelSPCT = (DefaultTableModel) tblDSSP.getModel();
        loadDataSPCT(spcts.getAllSPCTHD());
    }

    public GioHang getSPCTInGH(int idHDC, int idSPCT) {
        indexHDC = tblHDC.getSelectedRow();
        String maHDC = tblHDC.getValueAt(indexHDC, 0).toString();
        for (GioHang gh : ghs.getGioHangByMaHD(maHDC)) {
            if (gh.getHoaDon().getIdHoaDon() == idHDC && gh.getSanPhamChiTiet().getIdSPCT() == idSPCT) {
                return gh;
            }
        }
        return null; // Trả về null nếu không tìm thấy
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblHDC = new javax.swing.JTable();
        btnAddHDC = new javax.swing.JButton();
        btnDeleteHDC = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblGH = new javax.swing.JTable();
        btnXoaSanPhamCT = new javax.swing.JButton();
        btnSuaSL = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblDSSP = new javax.swing.JTable();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        btnKhachHang = new javax.swing.JButton();
        btnVoucher = new javax.swing.JButton();
        btnThanhToan = new javax.swing.JButton();
        txtNhapSDT = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        txtTenKH = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        txtNVBan = new javax.swing.JTextField();
        jSeparator3 = new javax.swing.JSeparator();
        txtMaVou = new javax.swing.JTextField();
        jSeparator4 = new javax.swing.JSeparator();
        txtMaAP = new javax.swing.JTextField();
        jSeparator5 = new javax.swing.JSeparator();
        txtLoai = new javax.swing.JTextField();
        jSeparator6 = new javax.swing.JSeparator();
        txtTienGiam = new javax.swing.JTextField();
        jSeparator7 = new javax.swing.JSeparator();
        txtTienHD = new javax.swing.JTextField();
        txtTienKhachTra = new javax.swing.JTextField();
        jSeparator9 = new javax.swing.JSeparator();
        jSeparator10 = new javax.swing.JSeparator();
        txtTienThua = new javax.swing.JTextField();
        jSeparator11 = new javax.swing.JSeparator();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Hóa đơn"));

        tblHDC.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã HD", "Tổng tiền", "Ngày tạo", "Trạng thái"
            }
        ));
        tblHDC.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHDCMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tblHDCMouseEntered(evt);
            }
        });
        jScrollPane1.setViewportView(tblHDC);

        btnAddHDC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/app/icon/icon/Add.png"))); // NOI18N
        btnAddHDC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddHDCActionPerformed(evt);
            }
        });

        btnDeleteHDC.setText("Hủy hóa đơn");
        btnDeleteHDC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteHDCActionPerformed(evt);
            }
        });

        jButton4.setText("Chọn Voucher");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(btnAddHDC)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnDeleteHDC)
                .addGap(18, 18, 18)
                .addComponent(jButton4)
                .addGap(14, 14, 14))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 687, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnDeleteHDC)
                        .addComponent(jButton4))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnAddHDC)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Giỏ hàng"));

        tblGH.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Tên SP", "Màu sắc", "Chất liệu", "Kiểu dáng", "Size", "Số lượng ", "Thành tiền"
            }
        ));
        tblGH.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblGHMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblGH);

        btnXoaSanPhamCT.setText("Xóa sản phẩm");
        btnXoaSanPhamCT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaSanPhamCTActionPerformed(evt);
            }
        });

        btnSuaSL.setText("Sửa số lượng");
        btnSuaSL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaSLActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(395, Short.MAX_VALUE)
                .addComponent(btnSuaSL)
                .addGap(50, 50, 50)
                .addComponent(btnXoaSanPhamCT)
                .addGap(54, 54, 54))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnXoaSanPhamCT)
                    .addComponent(btnSuaSL))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Danh sách sản phẩm"));

        tblDSSP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Tên SP", "Màu sắc", "Chất liệu", "Kiểu dáng", "Size", "Số lượng ", "Thành tiền"
            }
        ));
        tblDSSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDSSPMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblDSSP);

        jButton6.setText("<<<");

        jButton7.setText(">>>");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jScrollPane3)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGap(0, 223, Short.MAX_VALUE)
                        .addComponent(jButton6)
                        .addGap(62, 62, 62)
                        .addComponent(jButton7)
                        .addGap(269, 269, 269))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton7)
                    .addComponent(jButton6))
                .addContainerGap())
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin"));

        btnKhachHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/app/icon/icon/user1.png"))); // NOI18N
        btnKhachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKhachHangActionPerformed(evt);
            }
        });

        btnVoucher.setIcon(new javax.swing.ImageIcon(getClass().getResource("/app/icon/icon/sale.png"))); // NOI18N
        btnVoucher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVoucherActionPerformed(evt);
            }
        });

        btnThanhToan.setText("Thanh toán");
        btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanActionPerformed(evt);
            }
        });

        txtNhapSDT.setText("Nhập số điện thoại");
        txtNhapSDT.setBorder(null);
        txtNhapSDT.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtNhapSDTFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtNhapSDTFocusLost(evt);
            }
        });

        txtTenKH.setText("Tên khách hàng");
        txtTenKH.setBorder(null);
        txtTenKH.setEnabled(false);
        txtTenKH.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtTenKHFocusGained(evt);
            }
        });

        txtNVBan.setText("Nhân viên bán hàng");
        txtNVBan.setBorder(null);
        txtNVBan.setEnabled(false);
        txtNVBan.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtNVBanFocusGained(evt);
            }
        });

        txtMaVou.setText("Nhập mã voucher");
        txtMaVou.setBorder(null);
        txtMaVou.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtMaVouFocusGained(evt);
            }
        });

        txtMaAP.setText("Mã Voucher áp dụng");
        txtMaAP.setBorder(null);
        txtMaAP.setEnabled(false);
        txtMaAP.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtMaAPFocusGained(evt);
            }
        });

        txtLoai.setText("Loại giảm giá");
        txtLoai.setBorder(null);
        txtLoai.setEnabled(false);
        txtLoai.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtLoaiFocusGained(evt);
            }
        });

        txtTienGiam.setText("Số tiền được giảm");
        txtTienGiam.setBorder(null);
        txtTienGiam.setEnabled(false);
        txtTienGiam.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtTienGiamFocusGained(evt);
            }
        });

        txtTienHD.setText("Tổng tiền hóa đơn");
        txtTienHD.setBorder(null);
        txtTienHD.setEnabled(false);
        txtTienHD.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtTienHDFocusGained(evt);
            }
        });
        txtTienHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTienHDActionPerformed(evt);
            }
        });

        txtTienKhachTra.setText("Tiền khách trả");
        txtTienKhachTra.setBorder(null);
        txtTienKhachTra.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtTienKhachTraFocusGained(evt);
            }
        });
        txtTienKhachTra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTienKhachTraActionPerformed(evt);
            }
        });
        txtTienKhachTra.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTienKhachTraKeyPressed(evt);
            }
        });

        txtTienThua.setText("Tiền thừa");
        txtTienThua.setBorder(null);
        txtTienThua.setEnabled(false);
        txtTienThua.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtTienThuaFocusGained(evt);
            }
        });
        txtTienThua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTienThuaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnThanhToan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jSeparator3)
                            .addComponent(jSeparator2)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtNhapSDT, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtTenKH, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtNVBan, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(btnKhachHang))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(0, 22, Short.MAX_VALUE)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtTienKhachTra, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jSeparator9, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtTienHD)
                                .addComponent(jSeparator7)
                                .addComponent(txtTienGiam)
                                .addComponent(jSeparator6)
                                .addComponent(txtLoai)
                                .addComponent(jSeparator5)
                                .addComponent(txtMaAP, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                .addComponent(jSeparator4, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtMaVou, javax.swing.GroupLayout.Alignment.LEADING))
                            .addComponent(jSeparator10, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTienThua, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSeparator11, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(btnVoucher)))
                .addGap(22, 22, 22))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnKhachHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtNhapSDT))
                .addGap(1, 1, 1)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtNVBan, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtMaVou)
                    .addComponent(btnVoucher, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtMaAP, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtTienGiam, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtTienHD, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtTienKhachTra, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtTienThua, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(10, 10, 10))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1038, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 676, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnDeleteHDCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteHDCActionPerformed
        // TODO add your handling code here:
        try {
            int index = tblHDC.getSelectedRow();
            bhs.deleteHDC(tblHDC.getValueAt(index, 0).toString());
            loadFormHDC();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Hãy xóa hết sản phẩm đã thêm");
        }
    }//GEN-LAST:event_btnDeleteHDCActionPerformed

    private void btnKhachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKhachHangActionPerformed
        // TODO add your handling code here:
        try {
            String SDT = txtNhapSDT.getText();
            KhachHang kh = bhs.getbySDTKhachHang(SDT);
            showKH(kh);

            if (kh == null) {
                if (JOptionPane.showConfirmDialog(this, "Khach hang khong ton tai") == 0) {
                    bhs.addKhachHang(getForm());
                    KhachHang khNew = bhs.getbySDTKhachHang(SDT);
                    showKH(khNew);
                } else {
                    KhachHang khNew = bhs.getbySDTKhachHang(SDT);
                    showKH(khNew);
                }
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnKhachHangActionPerformed

    private void txtNhapSDTFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNhapSDTFocusGained
        txtNhapSDT.setText("");
    }//GEN-LAST:event_txtNhapSDTFocusGained

    private void txtTenKHFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTenKHFocusGained
        txtTenKH.setText("");
    }//GEN-LAST:event_txtTenKHFocusGained

    private void txtNVBanFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNVBanFocusGained
        txtNVBan.setText("");
    }//GEN-LAST:event_txtNVBanFocusGained

    private void txtMaVouFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtMaVouFocusGained
        // TODO add your handling code here:
        txtMaVou.setText("");
    }//GEN-LAST:event_txtMaVouFocusGained

    private void txtMaAPFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtMaAPFocusGained
        // TODO add your handling code here:
        txtMaAP.setText("");
    }//GEN-LAST:event_txtMaAPFocusGained

    private void txtLoaiFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtLoaiFocusGained
        // TODO add your handling code here:
        txtLoai.setText("");
    }//GEN-LAST:event_txtLoaiFocusGained

    private void txtTienGiamFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTienGiamFocusGained
        // TODO add your handling code here:
        txtTienGiam.setText("");
    }//GEN-LAST:event_txtTienGiamFocusGained

    private void txtTienHDFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTienHDFocusGained
        // TODO add your handling code here:
        txtTienHD.setText("");
    }//GEN-LAST:event_txtTienHDFocusGained

    private void txtTienKhachTraFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTienKhachTraFocusGained
        // TODO add your handling code here:
        txtTienKhachTra.setText("");
        if (!txtTienKhachTra.equals("")) {
            if (txtTienKhachTra.equals((""))) {
                tongTien = Double.valueOf(txtTienHD.getText());
                tienKhachTra = Double.valueOf(txtTienKhachTra.getText());
                tienThua = tongTien - tienKhachTra;
                txtTienThua.setText(String.valueOf(tienThua));
            }
        }
    }//GEN-LAST:event_txtTienKhachTraFocusGained

    private void txtTienThuaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTienThuaFocusGained
        // TODO add your handling code here:
        txtTienThua.setText("");
    }//GEN-LAST:event_txtTienThuaFocusGained

    private void btnAddHDCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddHDCActionPerformed
        // TODO add your handling code here:
        try {
            HoaDon hd = new HoaDon();
            bhs.addHDC(hd);
            loadFormHDC();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnAddHDCActionPerformed

    private void tblHDCMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHDCMouseClicked
        // TODO add your handling code here:
        try {
            int indexHDC = tblHDC.getSelectedRow();
            if (indexHDC >= 0) {
                String maHD = tblHDC.getValueAt(indexHDC, 0).toString();
                double tongTien = bhs.getTongTienHD(maHD); // Lấy tổng tiền ban đầu cho hóa đơn hiện tại

                // Hiển thị tổng tiền hóa đơn trong txtTienHD trước khi cộng thêm thành tiền của giỏ hàng
                //displayFormattedMoney(tongTien, txtTienHD);
                txtTienHD.setText(String.valueOf(tongTien));
                // Cộng thêm thành tiền của giỏ hàng
                tongTien = 0; // Đặt lại tongTien về 0 trước khi tính lại
                for (GioHang gh : ghs.getGioHangByMaHD(maHD)) {
                    tongTien += gh.getThanhTien();
                }

                // Hiển thị tổng tiền đã được định dạng
                txtTienHD.setText(String.valueOf(tongTien));
                //displayFormattedMoney(tongTien, txtTienHD);
                LoadFormGH();
                LoadDataGH(ghs.getGioHangByMaHD(maHD));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }//GEN-LAST:event_tblHDCMouseClicked

    private void tblHDCMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHDCMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tblHDCMouseEntered

    private void tblDSSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDSSPMouseClicked

        try {
            indexHDC = tblHDC.getSelectedRow();
            indexDSSP = tblDSSP.getSelectedRow();
            int indexGH = tblGH.getSelectedRow();
            String maHD = tblHDC.getValueAt(indexHDC, 0).toString();
            int idHDC = bhs.getIDHD(maHD);
            int idSPCT = Integer.parseInt(tblDSSP.getValueAt(indexDSSP, 0).toString());
            for (GioHang gh : ghs.getGioHangByMaHD(maHD)) {
                tongTien += gh.getThanhTien();
            }
            try {
                int soLuong = Integer.parseInt(tblDSSP.getValueAt(indexDSSP, 9).toString());
                if (indexHDC >= 0) {
                    if (indexDSSP >= 0) {
                        GioHang gh = getSPCTInGH(idHDC, idSPCT);
                        soLuongMua = Integer.valueOf(JOptionPane.showInputDialog(this, "Nhập số lượng mua:", "Số lượng mua", JOptionPane.QUESTION_MESSAGE));
                        if (soLuongMua > soLuong) {
                            JOptionPane.showMessageDialog(this, "Số lượng sản phẩm không đủ");
                            return;
                        } else if (soLuongMua < 0) {
                            JOptionPane.showMessageDialog(this, "Số lượng không phải là số âm");
                            return;
                        }
                        if (gh != null) {
                            HoaDonChiTiet1 hdct = getSPCTFROMDSSP();
                            int soLuongGH = gh.getSoLuong();
                            int soLuongMuaThem = soLuongMua + soLuongGH;
                            int soLuongCon = soLuong - soLuongMua;
                            ghs.updateSLGH(hdct, soLuongMuaThem, idHDC);
                            bhs.updateSLDSSP1(idSPCT, soLuongCon);

                            // Tính lại tổng tiền sau khi cập nhật số lượng giỏ hàng
                            tongTien = 0;
                            for (GioHang ghUpdated : ghs.getGioHangByMaHD(maHD)) {
                                tongTien += ghUpdated.getThanhTien();
                                bhs.updateTongTien(idHDC, tongTien);
                            }
                            loadDataSPCT(spcts.getAllSPCTHD());
                            LoadDataGH(ghs.getGioHangByMaHD(maHD));
                            txtTienHD.setText(String.valueOf(tongTien));
                            //displayFormattedMoney(tongTien, txtTienHD);
                            System.out.println(tongTien);
                        } else {
                            int soLuongCon = soLuong - soLuongMua;
                            HoaDonChiTiet1 hdct = getSPCTFROMDSSP();
                            ghs.addGH(hdct, soLuongMua, idHDC);
                            bhs.updateSLDSSP1(idSPCT, soLuongCon);
                            // Tính lại tổng tiền sau khi thêm sản phẩm vào giỏ hàng
                            tongTien = 0;
                            for (GioHang ghUpdated : ghs.getGioHangByMaHD(maHD)) {
                                tongTien += ghUpdated.getThanhTien();
                                bhs.updateTongTien(idHDC, tongTien);
                            }
                            loadDataSPCT(spcts.getAllSPCTHD());
                            LoadDataGH(ghs.getGioHangByMaHD(maHD));
                            txtTienHD.setText(String.valueOf(tongTien));
                            //displayFormattedMoney(tongTien, txtTienHD);
                            System.out.println(tongTien);
                        }
                    }
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Số lượng phải là số");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn hóa đơn");
        }

    }//GEN-LAST:event_tblDSSPMouseClicked

    private void btnXoaSanPhamCTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaSanPhamCTActionPerformed
        // TODO add your handling code here:
        int indexGH = tblGH.getSelectedRow();
        if (indexGH < 0) {
            JOptionPane.showMessageDialog(this, "Chọn sản phẩm cần xóa!!!");
            return;
        }
        indexHDC = tblHDC.getSelectedRow();
        String maHD = tblHDC.getValueAt(indexHDC, 0).toString();
        int idHDC = bhs.getIDHD(maHD);
        int idSPCT = Integer.parseInt(tblGH.getValueAt(indexGH, 0).toString());
        int soLuongXoa = Integer.parseInt(tblGH.getValueAt(indexGH, 8).toString());
        int soLuongSP = bhs.getSlSPCT(idSPCT);
        HoaDonChiTiet1 hdct = getSPCTFromGH();
        int soLuongCon = soLuongSP + soLuongXoa;
        bhs.updateSLDSSP1(idSPCT, soLuongCon);
        ghs.deleteGH(hdct, idHDC, idSPCT);
        loadDataSPCT(spcts.getAllSPCTHD());
        LoadFormGH();

    }//GEN-LAST:event_btnXoaSanPhamCTActionPerformed

    private void btnSuaSLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaSLActionPerformed
        // TODO add your handling code here:
        try {
            HoaDonChiTiet1 hdct = getSPCTFromGH();
            indexHDC = tblHDC.getSelectedRow();
            int indexGH = tblGH.getSelectedRow();
            int idSPCT = Integer.valueOf(tblGH.getValueAt(indexGH, 0).toString());
            String maHD = tblHDC.getValueAt(indexHDC, 0).toString();
            int idHDC = bhs.getIDHD(maHD);
            int soLuong = bhs.getSlSPCT(idSPCT);
            int soLuongGH = Integer.valueOf(tblGH.getValueAt(indexGH, 8).toString());

            if (indexGH < 0) {
                JOptionPane.showMessageDialog(this, "Bạn chưa chọn sản phẩm cần sửa");
                return;
            }
            int soLuongSua = Integer.valueOf(JOptionPane.showInputDialog(this, "Sửa số lượng:", "Số lượng sửa", JOptionPane.QUESTION_MESSAGE));
            int soLuongCon = soLuong - (soLuongSua - soLuongGH);
            if (soLuongSua == 0) {
                ghs.deleteGH(hdct, idHDC, idSPCT);
                bhs.updateSLDSSP1(idSPCT, soLuongCon);
                LoadFormGH();
                loadDataSPCT(spcts.getAllSPCTHD());
            }
            ghs.updateSLGH(hdct, soLuongSua, idHDC);
            bhs.updateSLDSSP1(idSPCT, soLuongCon);
            tongTien = 0;
            for (GioHang ghUpdated : ghs.getGioHangByMaHD(maHD)) {
                tongTien += ghUpdated.getThanhTien();
                bhs.updateTongTien(idHDC, tongTien);
            }
            txtTienHD.setText(String.valueOf(tongTien));
            //displayFormattedMoney(tongTien, txtTienHD);
            LoadFormGH();
            loadDataSPCT(spcts.getAllSPCTHD());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Số lượng phải là số");
        }
    }//GEN-LAST:event_btnSuaSLActionPerformed

    private void tblGHMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblGHMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_tblGHMouseClicked

    private void txtTienHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTienHDActionPerformed
        // TODO add your handling code here:


    }//GEN-LAST:event_txtTienHDActionPerformed

    private void txtTienKhachTraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTienKhachTraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTienKhachTraActionPerformed

    private void txtTienThuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTienThuaActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_txtTienThuaActionPerformed

    private void txtTienKhachTraKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTienKhachTraKeyPressed
        // TODO add your handling code here

        tongTien = Double.parseDouble(txtTienHD.getText());
        tienKhachTra = Double.parseDouble(txtTienKhachTra.getText() + "0");
        double tienKhuyenMai = Double.valueOf(txtTienGiam.getText());
        //displayFormattedMoney(tienKhachTra, txtTienKhachTra);
        txtTienHD.setText(String.valueOf(tongTien));
        if (txtTienGiam.getText().isEmpty()) {
            tienThua = tienKhachTra - tongTien;
            txtTienThua.setText(String.valueOf(tienThua));
            txtTienHD.setText(String.valueOf(tongTien));
        }
        tienThua = tienKhachTra - (tongTien - tienKhuyenMai);
        txtTienThua.setText(String.valueOf(tienThua));
        txtTienHD.setText(String.valueOf(tongTien));
        //displayFormattedMoney(tienThua, txtTienThua);

    }//GEN-LAST:event_txtTienKhachTraKeyPressed

    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed
        // TODO add your handling code here:
        if (Double.valueOf(txtTienThua.getText()) < 0) {
            JOptionPane.showMessageDialog(this, "Tiền khách đưa không được nhỏ hơn tiền thanh toán");
            return;
        }
        if (txtTienKhachTra.getText().equalsIgnoreCase("")) {
            JOptionPane.showMessageDialog(this, "Bạn chưa nhập tiền khách trả");
            return;
        }
        indexHDC = tblHDC.getSelectedRow();
        String maHD = tblHDC.getValueAt(indexHDC, 0).toString();
        int idHD = bhs.getIDHD(maHD);
        int idHDCT = bhs.getIDHDCTByIDHD(idHD);
        int idKH = bhs.getIDKHBySDT(txtNhapSDT.getText());
        int idNV = bhs.getIDNhanVienByMa(txtNVBan.getText());

        if (txtMaAP.getText().isEmpty()) {
            bhs.thanhToan1(idHD, idKH, idNV, idHDCT);
            loadFormHDC();
            LoadFormGH();
        } else {
            int idVoucher = bhs.getIDVoucherByMa(txtMaAP.getText());
            bhs.thanhToan(idHD, idKH, idVoucher, idNV, idHDCT);
            loadFormHDC();
            LoadFormGH();
        }

    }//GEN-LAST:event_btnThanhToanActionPerformed

    private void btnVoucherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoucherActionPerformed
        List<Voucher> list = new ArrayList<>();
        for (Voucher voucher : voucherService.getAll()) {
            if (voucher.getTrangthai() == true) {
                list.add(voucher);
            }
        }
        JFrame frame = new JFrame("Voucher");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500); // Kích thước của JFrame
        JPanel panel = new JPanel(new GridLayout(list.size(), 3));
        
       
        for (Voucher voucher : list) {
            JTextField text1 = new JTextField(voucher.getMaVocher());
            JTextField text2 = new JTextField(voucher.getTenVoucher());
            text2.setSize(50, 20);
            JButton  apdung = new  JButton("Ap dung");
            apdung.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Voucher voucher = voucherService.getDetail(text1.getText());
                    if (Double.parseDouble(txtTienHD.getText()) >= voucher.getDonToithieu()) {
                        txtMaAP.setText(voucher.getMaVocher());
                        txtTienGiam.setText(String.valueOf(voucher.getMucGiamgia()));
                        frame.setVisible(false);

                    } else {
                        JOptionPane.showMessageDialog(frame, "không đủ điều kiện");
                    }

                }
            });
            panel.add(text1);
            panel.add(text2);
            panel.add(apdung);

        }

        frame.add(panel);

        // Hiển thị JFrame
        frame.setVisible(true);
    }//GEN-LAST:event_btnVoucherActionPerformed

    private void txtNhapSDTFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNhapSDTFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNhapSDTFocusLost


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddHDC;
    private javax.swing.JButton btnDeleteHDC;
    private javax.swing.JButton btnKhachHang;
    private javax.swing.JButton btnSuaSL;
    private javax.swing.JButton btnThanhToan;
    private javax.swing.JButton btnVoucher;
    private javax.swing.JButton btnXoaSanPhamCT;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JTable tblDSSP;
    private javax.swing.JTable tblGH;
    private javax.swing.JTable tblHDC;
    private javax.swing.JTextField txtLoai;
    private javax.swing.JTextField txtMaAP;
    private javax.swing.JTextField txtMaVou;
    private javax.swing.JTextField txtNVBan;
    private javax.swing.JTextField txtNhapSDT;
    private javax.swing.JTextField txtTenKH;
    private javax.swing.JTextField txtTienGiam;
    private javax.swing.JTextField txtTienHD;
    private javax.swing.JTextField txtTienKhachTra;
    private javax.swing.JTextField txtTienThua;
    // End of variables declaration//GEN-END:variables
    void showKH(KhachHang kh) {
        if (kh == null) {
            txtTenKH.setText("Khách lẻ");
        } else {
            txtNhapSDT.setText(kh.getSdt());
            txtTenKH.setText(kh.getTenKH());
        }
    }

    private KhachHang getForm() {
//        SimpleDateFormat sds = new SimpleDateFormat("yyy-MM-dd");
        String maKH;

        String sdt;
        Date ns = null;
//        try {
//            ns = sds.parse(sds.format(txtNgaysinh.getDate()));
//        } catch (Exception e) {
//            
//        }
        String email;

        Date ngayTao;

        boolean gioiTinh;
        maKH = "KH" + new Random().nextInt(10000);
        for (KhachHang kh : khs.getall()) {
            if (kh.getMaKH().equalsIgnoreCase(maKH)) {
                maKH = "KH" + new Random().nextInt(10000);
            }
        }

//        String maKH = JOptionPane.showInputDialog("vui long nhap ma khach hang");
        String tenKH = JOptionPane.showInputDialog("vui long nhap ten khach hang");
        sdt = txtNhapSDT.getText();

        ngayTao = new java.util.Date();

        return new KhachHang(maKH, tenKH, sdt);
    }
}
