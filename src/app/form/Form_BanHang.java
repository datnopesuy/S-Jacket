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
import app.model.KieuDang;
import app.model.LopLot;
import app.model.MauSac;
import app.model.Mu;
import app.model.SanPham;
import app.model.SanPhamChiTiet;
import app.model.Size;
import app.service.BanHangService;
import app.service.GioHangService;
import app.service.SanPhamChiTietService;
import java.util.ArrayList;
import javax.swing.JOptionPane;
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
    int indexHDC = - 1;
    int indexDSSP = - 1;

    /**
     * Creates new form Form_BanHang
     */
    public Form_BanHang() {
        initComponents();
        loadFormHDC();
        loadFormSPCT();
    }

    private void LoadDataGH(ArrayList<GioHang> list) {
        tblModelGH.setRowCount(0);
        for (GioHang gh : list) {
            tblModelGH.addRow(new Object[]{
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
            "Tên sản phẩm", "Màu sắc", "Chất liệu", "Lớp lót", "Mũ", "Size", "Kiểu dáng", "Số lượng", "Thành tiền"
        };
        tblModelGH.setColumnIdentifiers(headerSP);
        tblModelGH = (DefaultTableModel) tblGH.getModel();
        int index = tblHDC.getSelectedRow();
        String maHD = tblHDC.getValueAt(index, 0).toString();
        LoadDataGH(ghs.getGioHangByMaHD(maHD));
    }

    private void LoadDataHDC(ArrayList<HoaDon> list) {
        tblModelHD.setRowCount(0);
        for (HoaDon hd : list) {
            tblModelHD.addRow(new Object[]{
                hd.getMaHoaDon(),
                hd.getTongTien(),
                hd.getNgayTao(),
                hd.isTrangThai() ? "Đã thanh toán" : "Hóa đơn chờ"
            });
        }
    }

    private void loadFormHDC() {
        tblHDC.removeAll();
        tblHDC.setModel(tblModelHD);
        String headerSP[] = {
            "Mã hóa đơn", "Tổng tiền", "Ngày tạo", "Trạng thái"
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
        hdct1.getSoLuong();
        hdct1.setGia(Double.parseDouble(tblDSSP.getValueAt(indexDSSP, 10).toString()));
        return hdct1;
    }

    private void loadFormSPCT() {
        tblDSSP.removeAll();
        tblDSSP.setModel(tblModelSPCT);
        String headerSPCT[] = {
            "IDSPCT", "Mã sản phẩm", "Tên sản phẩm", "Màu sắc", "Chất liệu", "Lớp lót", "Mũ", "Size", "Kiểu dáng", "Số lượng", "Giá"
        };
        tblModelSPCT.setColumnIdentifiers(headerSPCT);
        tblModelSPCT = (DefaultTableModel) tblDSSP.getModel();
        loadDataSPCT(spcts.getAllSPCTHD());
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
        jButton2 = new javax.swing.JButton();
        btnDeleteHDC = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblGH = new javax.swing.JTable();
        jButton5 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblDSSP = new javax.swing.JTable();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jButton8 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
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

        jButton2.setText("Làm mới");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
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
                .addGap(18, 18, 18)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnDeleteHDC)
                .addGap(18, 18, 18)
                .addComponent(jButton4)
                .addGap(14, 14, 14))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(btnDeleteHDC)
                    .addComponent(jButton4)
                    .addComponent(btnAddHDC))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Barcode"));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 221, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
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
        jScrollPane2.setViewportView(tblGH);

        jButton5.setText("Xóa sản phẩm");

        jButton9.setText("Sửa số lượng");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(395, Short.MAX_VALUE)
                .addComponent(jButton9)
                .addGap(50, 50, 50)
                .addComponent(jButton5)
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
                    .addComponent(jButton5)
                    .addComponent(jButton9))
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

        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/app/icon/icon/user1.png"))); // NOI18N
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/app/icon/icon/sale.png"))); // NOI18N

        jButton11.setText("Thanh toán");

        txtNhapSDT.setText("Nhập số điện thoại");
        txtNhapSDT.setBorder(null);
        txtNhapSDT.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtNhapSDTFocusGained(evt);
            }
        });

        txtTenKH.setText("Tên khách hàng");
        txtTenKH.setBorder(null);
        txtTenKH.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtTenKHFocusGained(evt);
            }
        });

        txtNVBan.setText("Nhân viên bán hàng");
        txtNVBan.setBorder(null);
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
        txtMaAP.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtMaAPFocusGained(evt);
            }
        });

        txtLoai.setText("Loại giảm giá");
        txtLoai.setBorder(null);
        txtLoai.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtLoaiFocusGained(evt);
            }
        });

        txtTienGiam.setText("Số tiền được giảm");
        txtTienGiam.setBorder(null);
        txtTienGiam.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtTienGiamFocusGained(evt);
            }
        });

        txtTienHD.setText("Tổng tiền hóa đơn");
        txtTienHD.setBorder(null);
        txtTienHD.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtTienHDFocusGained(evt);
            }
        });

        txtTienKhachTra.setText("Tiền khách trả");
        txtTienKhachTra.setBorder(null);
        txtTienKhachTra.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtTienKhachTraFocusGained(evt);
            }
        });

        txtTienThua.setText("Tiền thừa");
        txtTienThua.setBorder(null);
        txtTienThua.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtTienThuaFocusGained(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButton11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                        .addComponent(jButton8))
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
                        .addComponent(jButton10)))
                .addGap(22, 22, 22))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                    .addComponent(jButton10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(10, 10, 10)
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
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnDeleteHDCActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton8ActionPerformed

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

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void tblHDCMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHDCMouseClicked
        // TODO add your handling code here:
        try {
            int indexHDC = tblHDC.getSelectedRow();
            if (indexHDC >= 0) {
                LoadFormGH();
                String maHD = tblHDC.getValueAt(indexHDC, 0).toString();
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
        // TODO add your handling code here:
        indexHDC = tblHDC.getSelectedRow();
        indexDSSP = tblDSSP.getSelectedRow();
        String maHD = tblHDC.getValueAt(indexHDC, 0).toString();
        int idHDC = bhs.getIDHD(maHD);
        System.out.println(maHD);
        System.out.println(idHDC);
        double thanhTien = 0;
        if (indexHDC >= 0) {
            if (indexDSSP >= 0) {
                int soLuongMua = Integer.parseInt(JOptionPane.showInputDialog(this, "Mua nhiu cu:", "Số lượng mua", JOptionPane.QUESTION_MESSAGE));
                HoaDonChiTiet1 hdct = getSPCTFROMDSSP();
                ghs.addGH(hdct, soLuongMua, idHDC);
                LoadDataGH(ghs.getGioHangByMaHD(maHD));
            }
        }
    }//GEN-LAST:event_tblDSSPMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddHDC;
    private javax.swing.JButton btnDeleteHDC;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
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
}
