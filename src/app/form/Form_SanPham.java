/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package app.form;

import app.FormTT.ClForm;
import app.FormTT.KieuDangForm;
import app.FormTT.LopLotForm;
import app.FormTT.MsForm;
import app.FormTT.MuForm;
import app.FormTT.SizeForm;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import app.model.ChatLieu;
import app.model.KieuDang;
import app.model.LopLot;
import app.model.MauSac;
import app.model.Mu;
import app.model.SanPham;
import app.model.SanPhamChiTiet;
import app.model.Size;
import app.service.ChatLieuService;
import app.service.KieuDangService;
import app.service.LopLotService;
import app.service.MauSacService;
import app.service.MuService;
import app.service.SanPhamChiTietService;
import app.service.SanPhamService;
import app.service.SizeService;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author dungn
 */
public class Form_SanPham extends javax.swing.JPanel {

    private final SanPhamService sps = new SanPhamService();
    private final SanPhamChiTietService spcts = new SanPhamChiTietService();
    private final ChatLieuService cls = new ChatLieuService();
    private final SizeService ss = new SizeService();
    private final MuService ms = new MuService();
    private final MauSacService mss = new MauSacService();
    private final KieuDangService kds = new KieuDangService();
    private final LopLotService lls = new LopLotService();
    DefaultTableModel tblModel = new DefaultTableModel();
    DefaultTableModel tblModelSP = new DefaultTableModel();
    DefaultTableModel tblModelSPCT = new DefaultTableModel();
    private DefaultComboBoxModel dcbmSize = new DefaultComboBoxModel();
    private DefaultComboBoxModel dcbmMS = new DefaultComboBoxModel();
    private DefaultComboBoxModel dcbmCL = new DefaultComboBoxModel();
    private DefaultComboBoxModel dcbmKD = new DefaultComboBoxModel();
    private DefaultComboBoxModel dcbmLL = new DefaultComboBoxModel();
    private DefaultComboBoxModel dcbmMu = new DefaultComboBoxModel();

    /**
     * Creates new form Form_SanPham
     */
    public Form_SanPham() {
        initComponents();
        lb.setText("Form sản phẩm ");
        loadFormSP();
        loadFormSPCT();
        rdoHoatDong3.setSelected(true);
        rdoSearchHD.setSelected(true);
        String text = txtTimKiem.getText();
        if (rdoSearchHD.isSelected()) {
            loadDataSanPham(sps.getAllSPHD(text));
        }
        showCBOSize();
        showCBOCL();
        showCBOKD();
        showCBOLL();
        showCBOMS();
        showCBOMu();
    }

    private void loadDataSanPham(ArrayList<SanPham> list) {
        tblModelSP.setRowCount(0);
        int stt = 1;
        for (SanPham sp : list) {
            tblModelSP.addRow(new Object[]{
                stt++,
                sp.getMaSP(),
                sp.getTenSP(),
                sp.isTrangThai() ? "Hoạt động" : "Ngưng hoạt động"
            });
        }
    }

    private void loadFormSP() {
        tblSanPham.removeAll();
        tblSanPham.setModel(tblModelSP);
        String headerSP[] = {
            "STT", "Mã sản phẩm", "Tên sản phẩm", "Trạng thái"
        };
        tblModelSP.setColumnIdentifiers(headerSP);
        tblModelSP = (DefaultTableModel) tblSanPham.getModel();
        loadDataSanPham(sps.getAllSanPham());
    }

    private void loadDataSPCT(ArrayList<SanPhamChiTiet> listSPCT) {
        tblModelSPCT.setRowCount(0);
        int stt = 1;
        for (SanPhamChiTiet spct : listSPCT) {
            tblModelSPCT.addRow(new Object[]{
                stt++,
                spct.getSanPham().getMaSP(),
                spct.getSanPham().getTenSP(),
                spct.getSize().getMaSize(),
                spct.getMauSac().getTenMauSac(),
                spct.getChatLieu().getMaChatLieu(),
                spct.getLopLot().getTenLopLot(),
                spct.getKieuDang().getTenKieuDang(),
                spct.getMu().getTenMu(),
                spct.getSoLuong(),
                spct.getGia(),
                spct.getMoTa()
            });
        }
    }

    private void loadFormSPCT1() {
        tblSanPhamChiTiet.removeAll();
        tblSanPhamChiTiet.setModel(tblModelSPCT);
        String headerSPCT[] = {
            "STT", "Mã sản phẩm", "Tên sản phẩm", "Size", "Màu sắc", "Chất liệu", "Lớp lót", "Kiểu dáng", "Mũ", "Số lượng", "Giá", "Mô tả"
        };
        tblModelSPCT.setColumnIdentifiers(headerSPCT);
        tblModelSPCT = (DefaultTableModel) tblSanPhamChiTiet.getModel();
        int row = tblSanPham.getSelectedRow();
        String maSp = tblSanPham.getValueAt(row, 1).toString();
        pnTong.setSelectedIndex(1);
        loadDataSPCT(spcts.getSPCTByIDSP(maSp));
    }

    private void loadFormSPCT() {
        tblSanPhamChiTiet.removeAll();
        tblSanPhamChiTiet.setModel(tblModelSPCT);
        String headerSPCT[] = {
            "STT", "Mã sản phẩm", "Tên sản phẩm", "Size", "Màu sắc", "Chất liệu", "Lớp lót", "Kiểu dáng", "Mũ", "Số lượng", "Giá", "Mô tả"
        };
        tblModelSPCT.setColumnIdentifiers(headerSPCT);
        tblModelSPCT = (DefaultTableModel) tblSanPhamChiTiet.getModel();
        loadDataSPCT(spcts.getAllSPCT());
    }

    private void loadDataChatLieu(ArrayList<ChatLieu> listCL) {
        tblModel.setRowCount(0);
        int stt = 1;
        for (ChatLieu cl : listCL) {
            tblModel.addRow(new Object[]{
                stt++,
                cl.getMaChatLieu(),
                cl.getTenChatLieu(),
                cl.isTrangThai() ? "Hoạt động" : "Ngưng hoạt động"
            });
        }
    }

    private void loadDataMauSac(ArrayList<MauSac> list) {
        tblModel.setRowCount(0);
        int stt = 1;
        for (MauSac ms : list) {
            tblModel.addRow(new Object[]{
                stt++,
                ms.getMaMauSac(),
                ms.getTenMauSac(),
                ms.isTrangThai() ? "Hoạt động" : "Ngưng hoạt động"
            });
        }
    }

    private void loadDataKieuDang(ArrayList<KieuDang> list) {
        tblModel.setRowCount(0);
        int stt = 1;
        for (KieuDang kd : list) {
            tblModel.addRow(new Object[]{
                stt++,
                kd.getMaKieuDang(),
                kd.getTenKieuDang(),
                kd.isTrangThai() ? "Hoạt động" : "Ngưng hoạt động"
            });
        }
    }

    private void loadDataLopLot(ArrayList<LopLot> list) {
        tblModel.setRowCount(0);
        int stt = 1;
        for (LopLot ll : list) {
            tblModel.addRow(new Object[]{
                stt++,
                ll.getMaLopLot(),
                ll.getTenLopLot(),
                ll.isTrangThai() ? "Hoạt động" : "Ngưng hoạt động"
            });
        }
    }

    private void loadDataMu(ArrayList<Mu> list) {
        tblModel.setRowCount(0);
        int stt = 1;
        for (Mu m : list) {
            tblModel.addRow(new Object[]{
                stt++,
                m.getMaMu(),
                m.getTenMu(),
                m.isTrangThai() ? "Hoạt động" : "Ngưng hoạt động"
            });
        }
    }

    private void loadDataSize(ArrayList<Size> list) {
        tblModel.setRowCount(0);
        int stt = 1;
        for (Size s : list) {
            tblModel.addRow(new Object[]{
                stt++,
                s.getMaSize(),
                s.getTenSize(),
                s.isTrangThai() ? "Hoạt động" : "Ngưng hoạt động"
            });
        }
    }

    private void showDetailSP() {
        int index = tblSanPham.getSelectedRow();
        txtMaSanPham3.setText(tblModelSP.getValueAt(index, 1).toString());
        txtTenSanPham3.setText(tblModelSP.getValueAt(index, 2).toString());
        boolean tt = tblModelSP.getValueAt(index, 3).toString().equalsIgnoreCase("Hoạt động") ? true : false;
        rdoHoatDong3.setSelected(tt);
        rdoNgungHoatDong3.setSelected(!tt);
    }

    private void showDetailSPCT() {
        int index = tblSanPhamChiTiet.getSelectedRow();
        int row = tblSanPham.getSelectedRow();
        String maSp = tblSanPham.getValueAt(row, 1).toString();
        SanPhamChiTiet spct = spcts.getSPCTByIDSP(maSp).get(index);
        txtMaSpct.setText(spct.getSanPham().getMaSP());
        txtTenSanPham.setText(spct.getSanPham().getTenSP());
        txtSoLuong.setText(tblSanPhamChiTiet.getValueAt(index, 9).toString());
        txtGia.setText(tblSanPhamChiTiet.getValueAt(index, 10).toString());
        taMoTa.setText(tblSanPhamChiTiet.getValueAt(index, 11).toString());
        cboSize.setSelectedItem(tblSanPhamChiTiet.getValueAt(index, 3).toString());
        cboMauSac.setSelectedItem(tblSanPhamChiTiet.getValueAt(index, 4).toString());
        cboChatLieu.setSelectedItem(tblSanPhamChiTiet.getValueAt(index, 5).toString());
        cboLopLot.setSelectedItem(tblSanPhamChiTiet.getValueAt(index, 6).toString());
        cboKieuDang.setSelectedItem(tblSanPhamChiTiet.getValueAt(index, 7).toString());
        cboMu.setSelectedItem(tblSanPhamChiTiet.getValueAt(index, 8).toString());
    }

    public void showCBOSize() {
        dcbmSize = (DefaultComboBoxModel) cboSize.getModel();
        DefaultComboBoxModel dcbmLocSize = (DefaultComboBoxModel) cboLocsize.getModel();
        dcbmSize.removeAllElements();
        dcbmLocSize.removeAllElements();
        dcbmLocSize.addElement("Tất cả");
        for (Size s : ss.getAllSize()) {
            dcbmSize.addElement(s.getMaSize());
            dcbmLocSize.addElement(s.getMaSize());

        }
    }

    public void showCBOMS() {
        dcbmMS = (DefaultComboBoxModel) cboMauSac.getModel();
        DefaultComboBoxModel dcbmLocMauSac = (DefaultComboBoxModel) cboLocMauSac.getModel();
        dcbmMS.removeAllElements();
        dcbmLocMauSac.removeAllElements();
        dcbmLocMauSac.addElement("Tất cả");
        for (MauSac ms : mss.getAllMauSac()) {
            dcbmMS.addElement(ms.getTenMauSac());
            dcbmLocMauSac.addElement(ms.getTenMauSac());
        }
    }

    public void showCBOKD() {
        dcbmKD = (DefaultComboBoxModel) cboKieuDang.getModel();
        DefaultComboBoxModel dcbmLocKD = (DefaultComboBoxModel) cboLocKieuDang.getModel();
        dcbmKD.removeAllElements();
        dcbmLocKD.removeAllElements();
        dcbmLocKD.addElement("Tất cả");
        for (KieuDang kd : kds.getAllKieuDang()) {
            dcbmLocKD.addElement(kd.getTenKieuDang());
            dcbmKD.addElement(kd.getTenKieuDang());
        }
    }

    public void showCBOLL() {
        dcbmLL = (DefaultComboBoxModel) cboLopLot.getModel();
        DefaultComboBoxModel dcbmLocLL = (DefaultComboBoxModel) cboLocLopLot.getModel();
        dcbmLL.removeAllElements();
        dcbmLocLL.removeAllElements();
        dcbmLocLL.addElement("Tất cả");
        for (LopLot ll : lls.getAllLopLot()) {
            dcbmLL.addElement(ll.getTenLopLot());
            dcbmLocLL.addElement(ll.getTenLopLot());
        }
    }

    public void showCBOMu() {
        dcbmMu = (DefaultComboBoxModel) cboMu.getModel();
        DefaultComboBoxModel dcbmLocMu = (DefaultComboBoxModel) cboLocMu.getModel();
        dcbmLocMu.removeAllElements();
        dcbmMu.removeAllElements();
        dcbmLocMu.addElement("Tất cả");
        for (Mu m : ms.getAllMu()) {
            dcbmMu.addElement(m.getTenMu());
            dcbmLocMu.addElement(m.getTenMu());
        }
    }

    public void showCBOCL() {
        dcbmCL = (DefaultComboBoxModel) cboChatLieu.getModel();
        DefaultComboBoxModel dcbmLocCL = (DefaultComboBoxModel) cboLocChatLieu.getModel();
        dcbmLocCL.removeAllElements();
        dcbmCL.removeAllElements();
        dcbmLocCL.addElement("Tất cả");
        for (ChatLieu cl : cls.getAllChatLieu()) {
            dcbmCL.addElement(cl.getMaChatLieu());
            dcbmLocCL.addElement(cl.getMaChatLieu());
        }
    }

    private void showDetailTT() {
        int index = tblThuocTinh.getSelectedRow();
        txtMaThuocTinh.setText(tblModel.getValueAt(index, 1).toString());
        txtTenThuocTinh.setText(tblModel.getValueAt(index, 2).toString());
        boolean tt = tblModel.getValueAt(index, 3).toString().equalsIgnoreCase("Hoạt động") ? true : false;
        rdoHDTT.setSelected(tt);
        rdoNHDTT.setSelected(!tt);
    }

    private SanPham getSPFormInput() {
        SanPham sp = new SanPham();
        sp.setMaSP(txtMaSanPham3.getText());
        sp.setTenSP(txtTenSanPham3.getText());
        sp.setTrangThai(rdoHoatDong3.isSelected() ? true : false);
        return sp;
    }

    private SanPhamChiTiet getSPCTFormInput() {

        SanPham sp = new SanPham();
        sp.setId(spcts.getIDSP(txtMaSpct.getText()));
        sp.setMaSP(txtMaSpct.getText());
        sp.setTenSP(txtTenSanPham.getText());
        MauSac msac = mss.getMSbyMa(cboMauSac.getSelectedItem().toString());
        Size s = ss.getSizebyMa(cboSize.getSelectedItem().toString());
        ChatLieu cl = cls.getCLbyMa(cboChatLieu.getSelectedItem().toString());
        LopLot ll = lls.getLLbyMa(cboLopLot.getSelectedItem().toString());
        KieuDang kd = kds.getKDbyMa(cboKieuDang.getSelectedItem().toString());
        Mu m = ms.getMSbyMa(cboMu.getSelectedItem().toString());

        SanPhamChiTiet spct = new SanPhamChiTiet();
        spct.setIdSPCT(spcts.getIDSPCTByIDSP(spcts.getIDSP(txtMaSpct.getText())));
        spct.setSanPham(sp);
        spct.setChatLieu(cl);
        spct.setMauSac(msac);
        spct.setKieuDang(kd);
        spct.setLopLot(ll);
        spct.setSize(s);
        spct.setMu(m);
        spct.setSoLuong(Integer.parseInt(txtSoLuong.getText()));
        spct.setGia(Double.parseDouble(txtGia.getText()));
        spct.setMoTa(taMoTa.getText());
        return spct;
    }

    private MauSac getMSFormInput() {
        MauSac ms = new MauSac();
        ms.setMaMauSac(txtMaThuocTinh.getText());
        ms.setTenMauSac(txtTenThuocTinh.getText());
        ms.setTrangThai(rdoHDTT.isSelected() ? true : false);
        return ms;
    }

    private Size getSizeFormInput() {
        Size s = new Size();
        s.setMaSize(txtMaThuocTinh.getText());
        s.setTenSize(txtTenThuocTinh.getText());
        s.setTrangThai(rdoHDTT.isSelected() ? true : false);
        return s;
    }

    private ChatLieu getCLFormInput() {
        ChatLieu cl = new ChatLieu();
        cl.setMaChatLieu(txtMaThuocTinh.getText());
        cl.setTenChatLieu(txtTenThuocTinh.getText());
        cl.setTrangThai(rdoHDTT.isSelected() ? true : false);
        return cl;
    }

    private Mu getMuFormInput() {
        Mu m = new Mu();
        m.setMaMu(txtMaThuocTinh.getText());
        m.setTenMu(txtTenThuocTinh.getText());
        m.setTrangThai(rdoHDTT.isSelected() ? true : false);
        return m;
    }

    private LopLot getLLFormInput() {
        LopLot ll = new LopLot();
        ll.setMaLopLot(txtMaThuocTinh.getText());
        ll.setTenLopLot(txtTenThuocTinh.getText());
        ll.setTrangThai(rdoHDTT.isSelected() ? true : false);
        return ll;
    }

    private KieuDang getKDFormInput() {
        KieuDang kd = new KieuDang();
        kd.setMaKieuDang(txtMaThuocTinh.getText());
        kd.setTenKieuDang(txtTenThuocTinh.getText());
        kd.setTrangThai(rdoHDTT.isSelected() ? true : false);
        return kd;
    }

    private void clearFormSP() {
        txtMaSanPham3.setText("");
        txtTenSanPham3.setText("");
        rdoHoatDong3.setSelected(true);
        rdoNgungHoatDong3.setSelected(false);
    }

    private void clearFormTT() {
        txtMaThuocTinh.setText("");
        txtTenThuocTinh.setText("");
        rdoHDTT.setSelected(true);
        rdoNHDTT.setSelected(false);
    }

    private boolean validateSP() {
        if (txtMaSanPham3.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Mã sản phẩm không được để trống");
            return false;
        }
        if (txtTenSanPham3.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Tên sản phẩm không được để trống");
            return false;
        }
        return true;
    }

    private boolean validateTT() {
        if (txtMaThuocTinh.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Mã thuộc tính không được để trống");
            return false;
        }
        if (txtTenThuocTinh.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Tên thuộc tính không được để trống");
            return false;
        }
        return true;
    }

    private boolean checkTrungMaSP(String maSP) {
        for (int i = 0; i < tblSanPham.getRowCount() - 1; i++) {
            if (tblSanPham.getValueAt(i, 1).toString().equalsIgnoreCase(maSP)) {
                return false;
            }
        }
        return true;
    }

    private boolean checkTrungMaTT(String maTT) {
        for (int i = 0; i < tblThuocTinh.getRowCount() - 1; i++) {
            if (tblSanPham.getValueAt(i, 1).toString().equalsIgnoreCase(maTT)) {
                return false;
            }
        }
        return true;
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
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        buttonGroup4 = new javax.swing.ButtonGroup();
        buttonGroup5 = new javax.swing.ButtonGroup();
        lb = new javax.swing.JLabel();
        pnTong = new javax.swing.JTabbedPane();
        jPanel5 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtTenSanPham3 = new javax.swing.JTextField();
        txtMaSanPham3 = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        rdoHoatDong3 = new javax.swing.JRadioButton();
        rdoNgungHoatDong3 = new javax.swing.JRadioButton();
        jPanel12 = new javax.swing.JPanel();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnCTSP = new javax.swing.JButton();
        btnNew = new javax.swing.JButton();
        jPanel13 = new javax.swing.JPanel();
        txtTimKiem = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();
        rdoSearchHD = new javax.swing.JRadioButton();
        rdoSearchNHD = new javax.swing.JRadioButton();
        jPanel7 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        txtMaSpct = new javax.swing.JTextField();
        txtSoLuong = new javax.swing.JTextField();
        txtGia = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        taMoTa = new javax.swing.JTextArea();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        cboLopLot = new javax.swing.JComboBox<>();
        cboSize = new javax.swing.JComboBox<>();
        cboMauSac = new javax.swing.JComboBox<>();
        cboChatLieu = new javax.swing.JComboBox<>();
        jPanel15 = new javax.swing.JPanel();
        btnAddSPCT = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        addSize = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jLabel32 = new javax.swing.JLabel();
        cboKieuDang = new javax.swing.JComboBox<>();
        jButton19 = new javax.swing.JButton();
        jLabel33 = new javax.swing.JLabel();
        cboMu = new javax.swing.JComboBox<>();
        jButton20 = new javax.swing.JButton();
        txtTenSanPham = new javax.swing.JTextField();
        jPanel16 = new javax.swing.JPanel();
        tfTimKiemSpct = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblSanPhamChiTiet = new javax.swing.JTable();
        jButton15 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();
        jLabel34 = new javax.swing.JLabel();
        cboLocsize = new javax.swing.JComboBox<>();
        jLabel35 = new javax.swing.JLabel();
        cboLocChatLieu = new javax.swing.JComboBox<>();
        jLabel36 = new javax.swing.JLabel();
        cboLocLopLot = new javax.swing.JComboBox<>();
        jLabel37 = new javax.swing.JLabel();
        cboLocKieuDang = new javax.swing.JComboBox<>();
        cboLocMu = new javax.swing.JComboBox<>();
        jLabel38 = new javax.swing.JLabel();
        cboLocMauSac = new javax.swing.JComboBox<>();
        jLabel39 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanel17 = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        jPanel19 = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        rdoSize = new javax.swing.JRadioButton();
        rdoMauSac = new javax.swing.JRadioButton();
        rdoChatLieu = new javax.swing.JRadioButton();
        rdoMu = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        rdoLopLot = new javax.swing.JRadioButton();
        rdoKieuDang = new javax.swing.JRadioButton();
        jPanel20 = new javax.swing.JPanel();
        lblMaThuocTinh = new javax.swing.JLabel();
        lblTenThuocTinh = new javax.swing.JLabel();
        txtMaThuocTinh = new javax.swing.JTextField();
        txtTenThuocTinh = new javax.swing.JTextField();
        rdoHDTT = new javax.swing.JRadioButton();
        rdoNHDTT = new javax.swing.JRadioButton();
        lblTenThuocTinh1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        btnAddTT = new javax.swing.JButton();
        btnUpdateTT = new javax.swing.JButton();
        btnNewTT = new javax.swing.JButton();
        jPanel21 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblThuocTinh = new javax.swing.JTable();

        lb.setFont(new java.awt.Font("sansserif", 1, 48)); // NOI18N
        lb.setForeground(new java.awt.Color(125, 125, 125));
        lb.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb.setText("Form");

        pnTong.setToolTipText("");

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setPreferredSize(new java.awt.Dimension(1014, 600));

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông Tin Sản Phẩm"));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel11.setText("Mã Sản Phẩm");

        jLabel12.setText("Tên Sản Phẩm");

        jLabel20.setText("Trạng thái");

        buttonGroup1.add(rdoHoatDong3);
        rdoHoatDong3.setText("Hoạt động");

        buttonGroup1.add(rdoNgungHoatDong3);
        rdoNgungHoatDong3.setText("Ngưng hoạt động");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(18, 18, 18)
                        .addComponent(txtMaSanPham3, javax.swing.GroupLayout.PREFERRED_SIZE, 404, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(jLabel20))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(rdoHoatDong3)
                                .addGap(54, 54, 54)
                                .addComponent(rdoNgungHoatDong3))
                            .addComponent(txtTenSanPham3, javax.swing.GroupLayout.PREFERRED_SIZE, 402, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(74, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtMaSanPham3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtTenSanPham3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(rdoHoatDong3)
                    .addComponent(rdoNgungHoatDong3))
                .addGap(33, 33, 33))
        );

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));
        jPanel12.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnCTSP.setText("Chi Tiết Sản Phẩm");
        btnCTSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCTSPActionPerformed(evt);
            }
        });

        btnNew.setText("Làm mới");
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnCTSP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnThem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnNew, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(80, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSua)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCTSP)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnNew)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(109, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));
        jPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder("Dạnh Sách Sản Phẩm"));

        txtTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimKiemActionPerformed(evt);
            }
        });
        txtTimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTimKiemKeyTyped(evt);
            }
        });

        jLabel13.setText("Tìm Kiếm");

        tblSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã Sản Phẩm", "Tên Sản Phẩm", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPhamMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblSanPham);

        buttonGroup2.add(rdoSearchHD);
        rdoSearchHD.setText("Hoạt động");
        rdoSearchHD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rdoSearchHDMouseClicked(evt);
            }
        });
        rdoSearchHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoSearchHDActionPerformed(evt);
            }
        });

        buttonGroup2.add(rdoSearchNHD);
        rdoSearchNHD.setText("Ngừng hoạt động");
        rdoSearchNHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoSearchNHDActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 886, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addGap(18, 18, 18)
                        .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(155, 155, 155)
                        .addComponent(rdoSearchHD)
                        .addGap(65, 65, 65)
                        .addComponent(rdoSearchNHD)))
                .addContainerGap(74, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(rdoSearchHD)
                    .addComponent(rdoSearchNHD))
                .addGap(10, 10, 10)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(36, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(266, Short.MAX_VALUE))
        );

        pnTong.addTab("Sản Phẩm", jPanel5);

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));
        jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông Tin Sản Phẩm"));

        jLabel14.setText("Mã SP");

        jLabel15.setText("Tên SP");

        jLabel16.setText("Số Lượng");

        jLabel21.setText("Giá");

        jLabel22.setText("Mô Tả");

        txtMaSpct.setEnabled(false);

        taMoTa.setColumns(20);
        taMoTa.setRows(5);
        jScrollPane2.setViewportView(taMoTa);

        jLabel23.setText("Size");

        jLabel24.setText("Màu Sắc");

        jLabel25.setText("Chất Liệu");

        jLabel26.setText("Lớp lót");

        cboLopLot.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Lông thỏ", "Lông vũ", "Lông thường", "Lông cừu" }));
        cboLopLot.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboLopLotActionPerformed(evt);
            }
        });

        cboSize.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "S", "M", "L", "Xl", "XXL" }));
        cboSize.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cboSizeMouseClicked(evt);
            }
        });
        cboSize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboSizeActionPerformed(evt);
            }
        });

        cboMauSac.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Đen", "Be", "Trắng", "Xanh", "Đỏ", "Vàng", "Tím" }));
        cboMauSac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboMauSacActionPerformed(evt);
            }
        });

        cboChatLieu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cotton", "Kaki", "Jean", "Len", "Nilon" }));
        cboChatLieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboChatLieuActionPerformed(evt);
            }
        });

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));
        jPanel15.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnAddSPCT.setText("Thêm Sản Phẩm");
        btnAddSPCT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddSPCTActionPerformed(evt);
            }
        });

        btnUpdate.setText("Cập Nhật Sản Phẩm");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        jButton4.setText("Làm Mới");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Quay Lại Sản Phẩm");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnAddSPCT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnUpdate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(56, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnAddSPCT)
                .addGap(28, 28, 28)
                .addComponent(btnUpdate)
                .addGap(26, 26, 26)
                .addComponent(jButton4)
                .addGap(34, 34, 34)
                .addComponent(jButton5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        addSize.setText("+");
        addSize.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addSizeMouseClicked(evt);
            }
        });
        addSize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addSizeActionPerformed(evt);
            }
        });

        jButton7.setText("+");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton13.setText("+");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        jButton14.setText("+");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        jLabel32.setText("Kiểu dáng");

        cboKieuDang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Lông thỏ", "Lông vũ", "Lông thường", "Lông cừu" }));
        cboKieuDang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboKieuDangActionPerformed(evt);
            }
        });

        jButton19.setText("+");
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });

        jLabel33.setText("Mũ");

        cboMu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Lông thỏ", "Lông vũ", "Lông thường", "Lông cừu" }));
        cboMu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboMuActionPerformed(evt);
            }
        });

        jButton20.setText("+");
        jButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton20ActionPerformed(evt);
            }
        });

        txtTenSanPham.setEnabled(false);
        txtTenSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenSanPhamActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14)
                    .addComponent(jLabel15)
                    .addComponent(jLabel16)
                    .addComponent(jLabel21)
                    .addComponent(jLabel22))
                .addGap(26, 26, 26)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE)
                    .addComponent(txtGia)
                    .addComponent(txtSoLuong)
                    .addComponent(txtMaSpct)
                    .addComponent(txtTenSanPham))
                .addGap(54, 54, 54)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel26)
                    .addComponent(jLabel25)
                    .addComponent(jLabel32)
                    .addComponent(jLabel33)
                    .addComponent(jLabel23)
                    .addComponent(jLabel24))
                .addGap(18, 18, 18)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cboMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboSize, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboLopLot, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(addSize)
                            .addComponent(jButton7)
                            .addComponent(jButton13)
                            .addComponent(jButton19)))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(cboKieuDang, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton14))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(cboMu, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton20)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel23, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel14)
                                .addComponent(txtMaSpct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cboSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(addSize)))
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel15)
                                    .addComponent(txtTenSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel24)
                                    .addComponent(cboMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton7))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel25)
                            .addComponent(cboChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton13))
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel16)
                                    .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel21)
                                    .addComponent(txtGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel26)
                                    .addComponent(cboLopLot, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton19))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel32)
                                    .addComponent(cboKieuDang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton14))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel22)
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel33)
                                    .addComponent(cboMu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton20)))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(39, 39, 39))
                    .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));
        jPanel16.setBorder(javax.swing.BorderFactory.createTitledBorder("Dạnh Sách Sản Phẩm"));

        tfTimKiemSpct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfTimKiemSpctActionPerformed(evt);
            }
        });
        tfTimKiemSpct.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfTimKiemSpctKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfTimKiemSpctKeyTyped(evt);
            }
        });

        jLabel27.setText("Tìm Kiếm");

        tblSanPhamChiTiet.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "MASP", "TENSP", "MAUSAC", "CHATLIEU", "LOP LOT", "SOLUONG", "SIZE", "GIA", "MOTA", "TRANGTHAI"
            }
        ));
        tblSanPhamChiTiet.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPhamChiTietMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tblSanPhamChiTiet);

        jButton15.setText("<<");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        jButton16.setText("<");
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        jButton17.setText(">");
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });

        jButton18.setText(">>");
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });

        jLabel34.setText("Size");

        cboLocsize.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "S", "M", "L", "Xl", "XXL" }));
        cboLocsize.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cboLocsizeMouseClicked(evt);
            }
        });
        cboLocsize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboLocsizeActionPerformed(evt);
            }
        });

        jLabel35.setText("Chất Liệu");

        cboLocChatLieu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cotton", "Kaki", "Jean", "Len", "Nilon" }));
        cboLocChatLieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboLocChatLieuActionPerformed(evt);
            }
        });

        jLabel36.setText("Lớp lót");

        cboLocLopLot.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Lông thỏ", "Lông vũ", "Lông thường", "Lông cừu" }));
        cboLocLopLot.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboLocLopLotActionPerformed(evt);
            }
        });

        jLabel37.setText("Kiểu dáng");

        cboLocKieuDang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Lông thỏ", "Lông vũ", "Lông thường", "Lông cừu" }));
        cboLocKieuDang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboLocKieuDangActionPerformed(evt);
            }
        });

        cboLocMu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Lông thỏ", "Lông vũ", "Lông thường", "Lông cừu" }));
        cboLocMu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboLocMuActionPerformed(evt);
            }
        });

        jLabel38.setText("Mũ");

        cboLocMauSac.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Đen", "Be", "Trắng", "Xanh", "Đỏ", "Vàng", "Tím" }));
        cboLocMauSac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboLocMauSacActionPerformed(evt);
            }
        });

        jLabel39.setText("Màu Sắc");

        jButton1.setText("RESERT");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGap(208, 208, 208)
                        .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(56, 56, 56)
                        .addComponent(jButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton18, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jLabel27)
                        .addGap(18, 18, 18)
                        .addComponent(tfTimKiemSpct, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel34)
                                .addGap(98, 98, 98)
                                .addComponent(jLabel35))
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addGap(18, 18, 18)
                                .addComponent(cboLocsize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cboLocChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cboLocLopLot, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel36, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cboLocMu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel38, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cboLocMauSac, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel39, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(cboLocKieuDang, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                                .addGap(93, 93, 93)
                                .addComponent(jLabel37)))
                        .addGap(67, 67, 67))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                        .addComponent(jScrollPane5)
                        .addContainerGap())))
        );

        jPanel16Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {cboLocChatLieu, cboLocKieuDang, cboLocLopLot, cboLocMauSac, cboLocMu, cboLocsize});

        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfTimKiemSpct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel38)
                    .addComponent(jLabel39)
                    .addComponent(jLabel37)
                    .addComponent(jLabel34)
                    .addComponent(jLabel35)
                    .addComponent(jLabel36))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cboLocMu)
                        .addComponent(cboLocMauSac)
                        .addComponent(cboLocKieuDang)
                        .addComponent(cboLocsize)
                        .addComponent(cboLocChatLieu)
                        .addComponent(jButton1))
                    .addComponent(cboLocLopLot))
                .addGap(17, 17, 17)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton15)
                    .addComponent(jButton16)
                    .addComponent(jButton17)
                    .addComponent(jButton18))
                .addGap(36, 36, 36))
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(118, Short.MAX_VALUE))
            .addComponent(jPanel16, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(144, Short.MAX_VALUE))
        );

        pnTong.addTab("Sản Phẩm Chi Tiết", jPanel7);

        jPanel17.setBackground(new java.awt.Color(255, 255, 255));

        jPanel18.setBackground(new java.awt.Color(255, 255, 255));
        jPanel18.setBorder(javax.swing.BorderFactory.createTitledBorder("Thiết Lập Thuộc Tính"));

        jPanel19.setBackground(new java.awt.Color(255, 255, 255));
        jPanel19.setBorder(javax.swing.BorderFactory.createTitledBorder("Chọn thuộc tính "));

        jLabel28.setText("Size");

        jLabel29.setText("Màu Sắc");

        jLabel30.setText("Chất Liệu");
        jLabel30.setToolTipText("");

        jLabel31.setText("Mũ");

        buttonGroup3.add(rdoSize);
        rdoSize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoSizeActionPerformed(evt);
            }
        });

        buttonGroup3.add(rdoMauSac);
        rdoMauSac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoMauSacActionPerformed(evt);
            }
        });

        buttonGroup3.add(rdoChatLieu);
        rdoChatLieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoChatLieuActionPerformed(evt);
            }
        });

        buttonGroup3.add(rdoMu);
        rdoMu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoMuActionPerformed(evt);
            }
        });

        jLabel1.setText("Lớp lót");

        jLabel2.setText("Kiểu dáng");

        buttonGroup3.add(rdoLopLot);
        rdoLopLot.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoLopLotActionPerformed(evt);
            }
        });

        buttonGroup3.add(rdoKieuDang);
        rdoKieuDang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoKieuDangActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel28)
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel29)
                            .addComponent(jLabel30)
                            .addComponent(jLabel31))
                        .addGap(21, 21, 21)
                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rdoMu)
                            .addComponent(rdoChatLieu)
                            .addComponent(rdoMauSac)
                            .addComponent(rdoLopLot)
                            .addComponent(rdoSize)))
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(rdoKieuDang)))
                .addContainerGap(42, Short.MAX_VALUE))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel28)
                    .addComponent(rdoSize))
                .addGap(18, 18, 18)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel29)
                    .addComponent(rdoMauSac))
                .addGap(18, 18, 18)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(rdoLopLot))
                .addGap(18, 18, 18)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel30)
                    .addComponent(rdoChatLieu))
                .addGap(18, 18, 18)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel31)
                    .addComponent(rdoMu))
                .addGap(18, 18, 18)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(rdoKieuDang))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel20.setBackground(new java.awt.Color(255, 255, 255));
        jPanel20.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblMaThuocTinh.setText("Mã Thuộc Tính");

        lblTenThuocTinh.setText("Tên Thuộc Tính");

        txtTenThuocTinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenThuocTinhActionPerformed(evt);
            }
        });

        buttonGroup4.add(rdoHDTT);
        rdoHDTT.setSelected(true);
        rdoHDTT.setText("Hoạt động");

        buttonGroup4.add(rdoNHDTT);
        rdoNHDTT.setText("Ngừng hoạt động");

        lblTenThuocTinh1.setText("Trạng thái");

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblMaThuocTinh)
                    .addComponent(lblTenThuocTinh)
                    .addComponent(lblTenThuocTinh1))
                .addGap(42, 42, 42)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addComponent(rdoHDTT)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rdoNHDTT)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(txtMaThuocTinh)
                    .addComponent(txtTenThuocTinh))
                .addContainerGap())
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMaThuocTinh)
                    .addComponent(txtMaThuocTinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTenThuocTinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTenThuocTinh))
                .addGap(24, 24, 24)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTenThuocTinh1)
                    .addComponent(rdoHDTT)
                    .addComponent(rdoNHDTT))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnAddTT.setText("Thêm");
        btnAddTT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddTTActionPerformed(evt);
            }
        });

        btnUpdateTT.setText("Sửa");
        btnUpdateTT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateTTActionPerformed(evt);
            }
        });

        btnNewTT.setText("Làm mới");
        btnNewTT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewTTActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnAddTT, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 150, Short.MAX_VALUE)
                .addComponent(btnUpdateTT, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(128, 128, 128)
                .addComponent(btnNewTT)
                .addGap(40, 40, 40))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddTT)
                    .addComponent(btnUpdateTT)
                    .addComponent(btnNewTT))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(46, Short.MAX_VALUE))
        );

        jPanel21.setBackground(new java.awt.Color(255, 255, 255));
        jPanel21.setBorder(javax.swing.BorderFactory.createTitledBorder("Danh Sách Thuộc Tính"));

        tblThuocTinh.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "STT", "Mã Thuộc TÍnh", "Tên Thuộc Tính"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblThuocTinh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblThuocTinhMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblThuocTinh);

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 877, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 185, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(175, Short.MAX_VALUE))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pnTong.addTab("Thuộc Tính Sản Phẩm", jPanel17);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lb, javax.swing.GroupLayout.PREFERRED_SIZE, 1, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(1110, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(pnTong)
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lb, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 866, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(pnTong)
                    .addContainerGap()))
        );
    }// </editor-fold>//GEN-END:initComponents


    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        if (validateSP()) {
            try {
                SanPham sp = getSPFormInput();
                if (!checkTrungMaSP(sp.getMaSP())) {
                    JOptionPane.showMessageDialog(this, "Mã sản phẩm đã tồn tại");
                    return;
                } else {
                    int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn thêm sản phẩm này?", "Xác nhận", JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        if (sps.addSanPham(sp) != null) {
                            JOptionPane.showMessageDialog(this, "Thêm sản phẩm thành công");
                            System.out.println("Thêm thành công");
                            String text = txtTimKiem.getText();
                            ArrayList<SanPham> allSanPham = sps.getAllSPHD(text);
                            loadDataSanPham(allSanPham);
                            clearFormSP();
                        }
                    }
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Thêm sản phẩm không thành công");
                e.printStackTrace(System.out);
            }
        }

    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        if (validateSP()) {
            try {
                SanPham sp = getSPFormInput();
                if (sps.updateSanPham(sp) != null) {
                    JOptionPane.showMessageDialog(this, "Sửa sản phẩm thành công");
                    System.out.println("Sửa thành công");
                    loadDataSanPham(sps.getAllSanPham());
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Sửa sản phẩm không thành công");
                e.printStackTrace(System.out);
            }
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnCTSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCTSPActionPerformed
//        try {
//            int row = tblSanPham.getSelectedRow();
//            String maSp = tblSanPham.getValueAt(row, 1).toString();
//            pnTong.setSelectedIndex(1);
//            
//            loadDataSPCT(spcts.getSPCTByIDSP(maSp));
//
//        } catch (Exception e) {
//            e.printStackTrace(System.out);
//        }
        try {
            int indexSP = tblSanPham.getSelectedRow();
            txtMaSpct.setText(tblSanPham.getValueAt(indexSP, 1).toString());
            txtTenSanPham.setText(tblSanPham.getValueAt(indexSP, 2).toString());
            //showDetailSPCT();
            loadFormSPCT1();

        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnCTSPActionPerformed

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        clearFormSP();
    }//GEN-LAST:event_btnNewActionPerformed

    private void txtTimKiemKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyTyped

    }//GEN-LAST:event_txtTimKiemKeyTyped

    private void tblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMouseClicked
        try {
            showDetailSP();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }//GEN-LAST:event_tblSanPhamMouseClicked

    private void btnAddSPCTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddSPCTActionPerformed
        //BTN Thêm
        try {
            if (validateFormInputs()) {
                SanPhamChiTiet spct = getSPCTFormInput();
                ArrayList<SanPhamChiTiet> checktrung = spcts.filterSPCT1(spct.getSanPham().getMaSP(), spct.getMauSac().getTenMauSac(), spct.getChatLieu().getMaChatLieu(), spct.getSize().getMaSize(), spct.getKieuDang().getTenKieuDang(), spct.getMu().getTenMu(), spct.getLopLot().getTenLopLot());
                System.out.println(checktrung.size());
                if (checktrung.size() != 0) {
                    JOptionPane.showMessageDialog(this, "Sản phẩm chi tiết đã tồn tại");
                } else {
                    if (spcts.addSPCT(spct) != null) {
                        JOptionPane.showMessageDialog(this, "Thêm sản phẩm thành công");
                        loadDataSPCT(spcts.getSPCTByIDSP(spct.getSanPham().getMaSP()));

                    }
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Thêm sản phẩm không thành công");
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnAddSPCTActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // btn update
        try {
            if (validateFormInputs()) {
                SanPhamChiTiet spct = getSPCTFormInput();
                ArrayList<SanPhamChiTiet> checktrung = spcts.filterSPCT1(spct.getSanPham().getMaSP(), spct.getMauSac().getTenMauSac(), spct.getChatLieu().getTenChatLieu(), spct.getSize().getMaSize(), spct.getKieuDang().getTenKieuDang(), spct.getMu().getTenMu(), spct.getLopLot().getTenLopLot());

                if (checktrung.size() != 0) {
                    JOptionPane.showMessageDialog(this, "Sản phẩm chi tiết đã tồn tại");
                } else {
                    if (spcts.updateSPCT(spct) != null) {
                        JOptionPane.showMessageDialog(this, "Cập nhật sản phẩm thành công");
                        loadDataSPCT(spcts.getSPCTByIDSP(spct.getSanPham().getMaSP()));
                    }
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Cập nhật sản phẩm không thành công");
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // btn làm mới
        txtMaSpct.setText("");
        txtTenSanPham.setText("");
        txtSoLuong.setText("");
        txtGia.setText("");
        taMoTa.setText("");
        cboChatLieu.setSelectedItem(0);
        cboKieuDang.setSelectedItem(0);
        cboLopLot.setSelectedItem(0);
        cboMauSac.setSelectedItem(0);
        cboMu.setSelectedItem(0);
        cboSize.setSelectedItem(0);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        pnTong.setSelectedIndex(0);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void addSizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addSizeActionPerformed
        //btn addsize
        SizeForm sizeForm = new SizeForm(this);
        sizeForm.setVisible(true);
    }//GEN-LAST:event_addSizeActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        //btn addmausac
        MsForm msForm = new MsForm(this);
        msForm.setVisible(true);
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        //btn addchatlieu
        ClForm cf = new ClForm(this);
        cf.setVisible(true);
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        //btn addkieudang
        KieuDangForm kdf = new KieuDangForm(this);
        kdf.setVisible(true);
    }//GEN-LAST:event_jButton14ActionPerformed

    private void tfTimKiemSpctActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfTimKiemSpctActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfTimKiemSpctActionPerformed

    private void tfTimKiemSpctKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfTimKiemSpctKeyTyped

    }//GEN-LAST:event_tfTimKiemSpctKeyTyped

    private void tblSanPhamChiTietMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamChiTietMouseClicked
        try {
            showDetailSPCT();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_tblSanPhamChiTietMouseClicked

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed

    }//GEN-LAST:event_jButton15ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed

    }//GEN-LAST:event_jButton16ActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed

    }//GEN-LAST:event_jButton17ActionPerformed

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed

    }//GEN-LAST:event_jButton18ActionPerformed

    private void btnAddTTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddTTActionPerformed
        //Thêm size
        if (rdoSize.isSelected() == true) {
            if (validateTT()) {
                try {
                    Size s = getSizeFormInput();
                    if (checkTrungMaTT(s.getMaSize()) == false) {
                        JOptionPane.showMessageDialog(this, "Mã size đã tồn tại");
                        return;
                    } else {
                        if (ss.addSize(s) != null) {
                            JOptionPane.showMessageDialog(this, "Thêm sản phẩm thành công");
                            System.out.println("Thêm thành công");
                            loadDataSize(ss.getAllSize());
                            clearFormTT();
                        }
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Thêm màu sắc không thành công");
                    e.printStackTrace(System.out);
                }
            }
        }
        //Thêm Chất liệu
        if (rdoSize.isSelected() == true) {
            if (validateTT()) {
                try {
                    Size s = getSizeFormInput();
                    if (checkTrungMaTT(s.getMaSize()) == false) {
                        JOptionPane.showMessageDialog(this, "Mã size đã tồn tại");
                        return;
                    } else {
                        if (ss.addSize(s) != null) {
                            JOptionPane.showMessageDialog(this, "Thêm sản phẩm thành công");
                            System.out.println("Thêm thành công");
                            loadDataSize(ss.getAllSize());
                            clearFormTT();
                        }
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Thêm màu sắc không thành công");
                    e.printStackTrace(System.out);
                }
            }
        }
        //Thêm màu sắc
        if (rdoSize.isSelected() == true) {
            if (validateTT()) {
                try {
                    Size s = getSizeFormInput();
                    if (checkTrungMaTT(s.getMaSize()) == false) {
                        JOptionPane.showMessageDialog(this, "Mã size đã tồn tại");
                        return;
                    } else {
                        if (ss.addSize(s) != null) {
                            JOptionPane.showMessageDialog(this, "Thêm sản phẩm thành công");
                            System.out.println("Thêm thành công");
                            loadDataSize(ss.getAllSize());
                            clearFormTT();
                        }
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Thêm màu sắc không thành công");
                    e.printStackTrace(System.out);
                }
            }
        }
        //Thêm mũ
        if (rdoSize.isSelected() == true) {
            if (validateTT()) {
                try {
                    Size s = getSizeFormInput();
                    if (checkTrungMaTT(s.getMaSize()) == false) {
                        JOptionPane.showMessageDialog(this, "Mã size đã tồn tại");
                        return;
                    } else {
                        if (ss.addSize(s) != null) {
                            JOptionPane.showMessageDialog(this, "Thêm sản phẩm thành công");
                            System.out.println("Thêm thành công");
                            loadDataSize(ss.getAllSize());
                            clearFormTT();
                        }
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Thêm màu sắc không thành công");
                    e.printStackTrace(System.out);
                }
            }
        }
        //Thêm lớp lót
        if (rdoSize.isSelected() == true) {
            if (validateTT()) {
                try {
                    Size s = getSizeFormInput();
                    if (checkTrungMaTT(s.getMaSize()) == false) {
                        JOptionPane.showMessageDialog(this, "Mã size đã tồn tại");
                        return;
                    } else {
                        if (ss.addSize(s) != null) {
                            JOptionPane.showMessageDialog(this, "Thêm sản phẩm thành công");
                            System.out.println("Thêm thành công");
                            loadDataSize(ss.getAllSize());
                            clearFormTT();
                        }
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Thêm màu sắc không thành công");
                    e.printStackTrace(System.out);
                }
            }
        }
        //Thêm kiểu dáng
        if (rdoSize.isSelected() == true) {
            if (validateTT()) {
                try {
                    Size s = getSizeFormInput();
                    if (checkTrungMaTT(s.getMaSize()) == false) {
                        JOptionPane.showMessageDialog(this, "Mã size đã tồn tại");
                        return;
                    } else {
                        if (ss.addSize(s) != null) {
                            JOptionPane.showMessageDialog(this, "Thêm sản phẩm thành công");
                            System.out.println("Thêm thành công");
                            loadDataSize(ss.getAllSize());
                            clearFormTT();
                        }
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Thêm màu sắc không thành công");
                    e.printStackTrace(System.out);
                }
            }
        }
    }//GEN-LAST:event_btnAddTTActionPerformed

    private void btnUpdateTTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateTTActionPerformed
        if (rdoSize.isSelected()) {
            if (validateTT()) {
                try {
                    Size s = getSizeFormInput();
                    if (ss.updateSize(s) != null) {
                        JOptionPane.showMessageDialog(this, "Sửa thuộc tính thành công");
                        System.out.println("Sửa thành công");
                        loadDataSize(ss.getAllSize());
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Sửa thuộc tính không thành công");
                    e.printStackTrace(System.out);
                }
            }
        }

        if (rdoChatLieu.isSelected()) {
            if (validateTT()) {
                try {
                    ChatLieu cl = getCLFormInput();
                    if (cls.updateChatLieu(cl) != null) {
                        JOptionPane.showMessageDialog(this, "Sửa thuộc tính thành công");
                        System.out.println("Sửa thành công");
                        loadDataChatLieu(cls.getAllChatLieu());
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Sửa thuộc tính không thành công");
                    e.printStackTrace(System.out);
                }
            }
        }

        if (rdoMauSac.isSelected()) {
            if (validateTT()) {
                try {
                    MauSac ms = getMSFormInput();
                    if (mss.updateMauSac(ms) != null) {
                        JOptionPane.showMessageDialog(this, "Sửa thuộc tính thành công");
                        System.out.println("Sửa thành công");
                        loadDataMauSac(mss.getAllMauSac());
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Sửa thuộc tính không thành công");
                    e.printStackTrace(System.out);
                }
            }
        }

        if (rdoMu.isSelected()) {
            if (validateTT()) {
                try {
                    Mu m = getMuFormInput();
                    if (ms.updateMu(m) != null) {
                        JOptionPane.showMessageDialog(this, "Sửa thuộc tính thành công");
                        System.out.println("Sửa thành công");
                        loadDataMu(ms.getAllMu());
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Sửa thuộc tính không thành công");
                    e.printStackTrace(System.out);
                }
            }
        }

        if (rdoLopLot.isSelected()) {
            if (validateTT()) {
                try {
                    LopLot ll = getLLFormInput();
                    if (lls.updateLopLot(ll) != null) {
                        JOptionPane.showMessageDialog(this, "Sửa thuộc tính thành công");
                        System.out.println("Sửa thành công");
                        loadDataLopLot(lls.getAllLopLot());
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Sửa thuộc tính không thành công");
                    e.printStackTrace(System.out);
                }
            }
        }

        if (rdoKieuDang.isSelected()) {
            if (validateTT()) {
                try {
                    KieuDang kd = getKDFormInput();
                    if (kds.updateKieuDang(kd) != null) {
                        JOptionPane.showMessageDialog(this, "Sửa thuộc tính thành công");
                        System.out.println("Sửa thành công");
                        loadDataKieuDang(kds.getAllKieuDang());
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Sửa thuộc tính không thành công");
                    e.printStackTrace(System.out);
                }
            }
        }
    }//GEN-LAST:event_btnUpdateTTActionPerformed

    private void btnNewTTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewTTActionPerformed
        // TODO add your handling code here:
        clearFormTT();
    }//GEN-LAST:event_btnNewTTActionPerformed

    private void rdoSizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoSizeActionPerformed
        tblThuocTinh.removeAll();
        tblThuocTinh.setModel(tblModel);
        String header[] = {
            "STT", "Mã Size", "Tên size", "Trạng thái"
        };
        tblModel.setColumnIdentifiers(header);
        tblModel = (DefaultTableModel) tblThuocTinh.getModel();
        loadDataSize(ss.getAllSize());
    }//GEN-LAST:event_rdoSizeActionPerformed

    private void rdoMauSacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoMauSacActionPerformed
        tblThuocTinh.removeAll();
        tblThuocTinh.setModel(tblModel);
        String header[] = {
            "STT", "Mã màu sắc", "Tên màu sắc", "Trạng thái"
        };
        tblModel.setColumnIdentifiers(header);
        tblModel = (DefaultTableModel) tblThuocTinh.getModel();
        loadDataMauSac(mss.getAllMauSac());
    }//GEN-LAST:event_rdoMauSacActionPerformed

    private void rdoChatLieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoChatLieuActionPerformed
        // TODO add your handling code here:
        tblThuocTinh.removeAll();
        tblThuocTinh.setModel(tblModel);
        String headerCL[] = {
            "STT", "Mã chất liệu", "Tên chất liệu", "Trạng thái"
        };
        tblModel.setColumnIdentifiers(headerCL);
        tblModel = (DefaultTableModel) tblThuocTinh.getModel();
        loadDataChatLieu(cls.getAllChatLieu());
    }//GEN-LAST:event_rdoChatLieuActionPerformed

    private void rdoMuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoMuActionPerformed
        // TODO add your handling code here:
        tblThuocTinh.removeAll();
        tblThuocTinh.setModel(tblModel);
        String header[] = {
            "STT", "Mã mũ", "Tên mũ", "Trạng thái"
        };
        tblModel.setColumnIdentifiers(header);
        tblModel = (DefaultTableModel) tblThuocTinh.getModel();
        loadDataMu(ms.getAllMu());
    }//GEN-LAST:event_rdoMuActionPerformed

    private void tblThuocTinhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblThuocTinhMouseClicked
        // TODO add your handling code here:
        try {
            showDetailTT();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }

    }//GEN-LAST:event_tblThuocTinhMouseClicked

    private void txtTimKiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyReleased
        // TODO add your handling code here:
//        try {
//            String text = "%" + txtTimKiem.getText().trim() + "%";
//            if (txtTimKiem.getText().isEmpty()) {
//                loadDataSanPham(sps.getAllSanPham());
//            } else {
//                ArrayList<SanPham> list = sps.searchSP(text);
//                loadDataSanPham(list);
//            }
//        } catch (Exception e) {
//            e.printStackTrace(System.out);
//        }

        try {
            String text = "%" + txtTimKiem.getText().trim() + "%";
            if (rdoSearchHD.isSelected()) {
                loadDataSanPham(sps.getAllSPHD(text));
            } else {
                //ArrayList<SanPham> list = sps.getAllSPNHD(text);
                loadDataSanPham(sps.getAllSPNHD(text));
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }//GEN-LAST:event_txtTimKiemKeyReleased

    private void rdoSearchHDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdoSearchHDMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_rdoSearchHDMouseClicked

    private void rdoSearchHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoSearchHDActionPerformed
        // TODO add your handling code here:
        try {
            String text = "%" + txtTimKiem.getText().trim() + "%";
            if (txtTimKiem.getText().isEmpty()) {
                loadDataSanPham(sps.getAllSPHD(text));
            } else {
                ArrayList<SanPham> list = sps.getAllSPHD(text);
                loadDataSanPham(list);
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }//GEN-LAST:event_rdoSearchHDActionPerformed

    private void rdoSearchNHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoSearchNHDActionPerformed
        // TODO add your handling code here:
        try {
            String text = "%" + txtTimKiem.getText().trim() + "%";
            if (txtTimKiem.getText().isEmpty()) {
                loadDataSanPham(sps.getAllSPNHD(text));
            } else {
                ArrayList<SanPham> list = sps.getAllSPNHD(text);
                loadDataSanPham(list);
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }//GEN-LAST:event_rdoSearchNHDActionPerformed

    private void txtTenThuocTinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenThuocTinhActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenThuocTinhActionPerformed

    private void rdoLopLotActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoLopLotActionPerformed
        // TODO add your handling code here:
        tblThuocTinh.removeAll();
        tblThuocTinh.setModel(tblModel);
        String header[] = {
            "STT", "Mã lớp lót", "Tên lớp lót", "Trạng thái"
        };
        tblModel.setColumnIdentifiers(header);
        tblModel = (DefaultTableModel) tblThuocTinh.getModel();
        loadDataLopLot(lls.getAllLopLot());
    }//GEN-LAST:event_rdoLopLotActionPerformed

    private void rdoKieuDangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoKieuDangActionPerformed
        // TODO add your handling code here:
        tblThuocTinh.removeAll();
        tblThuocTinh.setModel(tblModel);
        String header[] = {
            "STT", "Mã kiểu dáng", "Tên kiểu dáng", "Trạng thái"
        };
        tblModel.setColumnIdentifiers(header);
        tblModel = (DefaultTableModel) tblThuocTinh.getModel();
        loadDataKieuDang(kds.getAllKieuDang());
    }//GEN-LAST:event_rdoKieuDangActionPerformed

    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
        // TODO add your handling code here:
        //btn addloplot
        LopLotForm lopLotForm = new LopLotForm(this);
        lopLotForm.setVisible(true);
    }//GEN-LAST:event_jButton19ActionPerformed

    private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton20ActionPerformed
        // TODO add your handling code here:
        //btn addmu
        MuForm mf = new MuForm(this);
        mf.setVisible(true);
    }//GEN-LAST:event_jButton20ActionPerformed

    private void txtTenSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenSanPhamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenSanPhamActionPerformed

    private void txtTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimKiemActionPerformed

    private void tfTimKiemSpctKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfTimKiemSpctKeyReleased
        // TODO add your handling code here:

        //Tìm kiếm sản phẩm chi tiết
        String keyword = tfTimKiemSpct.getText().trim();
        if (keyword != null) {
            tblModelSPCT = (DefaultTableModel) tblSanPhamChiTiet.getModel();
            tblModelSPCT.setRowCount(0);
            int stt = 1;
            ArrayList<SanPhamChiTiet> list = spcts.searchSPCT(keyword);
            for (SanPhamChiTiet spct : list) {
                tblModelSPCT.addRow(new Object[]{
                    stt++,
                    spct.getSanPham().getMaSP(),
                    spct.getSanPham().getTenSP(),
                    spct.getSize().getMaSize(),
                    spct.getMauSac().getTenMauSac(),
                    spct.getChatLieu().getMaChatLieu(),
                    spct.getLopLot().getTenLopLot(),
                    spct.getKieuDang().getTenKieuDang(),
                    spct.getMu().getTenMu(),
                    spct.getSoLuong(),
                    spct.getGia(),
                    spct.getMoTa(), //                spct.isTrangThai()
                });
            }
        }
    }//GEN-LAST:event_tfTimKiemSpctKeyReleased

    private void addSizeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addSizeMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_addSizeMouseClicked

    private void cboSizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboSizeActionPerformed

    }//GEN-LAST:event_cboSizeActionPerformed

    private void cboMauSacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboMauSacActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_cboMauSacActionPerformed

    private void cboSizeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboSizeMouseClicked
        // TODO add your handling code here:


    }//GEN-LAST:event_cboSizeMouseClicked

    private void cboChatLieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboChatLieuActionPerformed

    }//GEN-LAST:event_cboChatLieuActionPerformed

    private void cboLopLotActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboLopLotActionPerformed

    }//GEN-LAST:event_cboLopLotActionPerformed

    private void cboKieuDangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboKieuDangActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_cboKieuDangActionPerformed

    private void cboMuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboMuActionPerformed

    }//GEN-LAST:event_cboMuActionPerformed

    private void cboLocsizeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboLocsizeMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_cboLocsizeMouseClicked

    private void cboLocsizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboLocsizeActionPerformed
        //btn lọc size
        Filler();
    }//GEN-LAST:event_cboLocsizeActionPerformed
//Lọc sản phẩm đa thuộc tính

    private void Filler() {
        String selectedSize = (String) cboLocsize.getSelectedItem();
        String selectedCl = (String) cboLocChatLieu.getSelectedItem();
        String selectedLL = (String) cboLocLopLot.getSelectedItem();
        String selectedKD = (String) cboLocKieuDang.getSelectedItem();
        String selectedMu = (String) cboLocMu.getSelectedItem();
        String selectedMau = (String) cboLocMauSac.getSelectedItem();
        String masp = txtMaSpct.getText();
        ArrayList<SanPhamChiTiet> listspct = spcts.filterSPCT1(masp, selectedMau, selectedCl, selectedSize, selectedKD, selectedMu, selectedLL);
//        System.out.println("List of SanPhamChiTiet: " + listspct);
        for (SanPhamChiTiet spct : listspct) {
            System.out.println(spct); // In ra từng phần tử trong danh sách
        }
        System.out.println(selectedKD);
        System.out.println(selectedMau);
        loadDataSPCT(listspct);
    }
    private void cboLocChatLieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboLocChatLieuActionPerformed
        //btn loc size
        Filler();
    }//GEN-LAST:event_cboLocChatLieuActionPerformed

    private void cboLocLopLotActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboLocLopLotActionPerformed
        //btn lọc lớp lót
        Filler();
    }//GEN-LAST:event_cboLocLopLotActionPerformed

    private void cboLocKieuDangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboLocKieuDangActionPerformed
        //btn lọc kiểu dáng
        Filler();
    }//GEN-LAST:event_cboLocKieuDangActionPerformed

    private void cboLocMuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboLocMuActionPerformed
        //btn lọc mũ
        Filler();
    }//GEN-LAST:event_cboLocMuActionPerformed

    private void cboLocMauSacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboLocMauSacActionPerformed
        //btn lọc Màu sắc
        Filler();
    }//GEN-LAST:event_cboLocMauSacActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
//        // TODO add your handling code here:
//        int indexSP = tblSanPham.getSelectedRow();
//            txtMaSpct.setText(tblSanPham.getValueAt(indexSP, 1).toString());
//            txtTenSanPham.setText(tblSanPham.getValueAt(indexSP, 2).toString());
//            //showDetailSPCT();
//            loadFormSPCT1();

        // btn reset các trường dữ liệu Lọc
        cboLocChatLieu.setSelectedIndex(0);
        cboLocKieuDang.setSelectedIndex(0);
        cboLocLopLot.setSelectedIndex(0);
        cboLocMauSac.setSelectedIndex(0);
        cboLocMu.setSelectedIndex(0);
        cboLocsize.setSelectedIndex(0);
    }//GEN-LAST:event_jButton1ActionPerformed

    //VALIDETE 
    private boolean validateFormInputs() {
        // Giả sử bạn có các phương thức để lấy giá trị từ form

        String giaStr = txtGia.getText();
        String soLuongStr = txtSoLuong.getText();
        // Các trường khác cũng có thể được xác thực tương tự

        // Kiểm tra nếu các trường không được để trống
        // Kiểm tra giá hợp lệ
        float gia;
        try {
            gia = Float.parseFloat(giaStr);
            if (gia <= 0) {
                JOptionPane.showMessageDialog(this, "Giá phải là số dương.");
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Giá phải là số hợp lệ.");
            return false;
        }

        // Kiểm tra số lượng hợp lệ
        int soLuong;
        try {
            soLuong = Integer.parseInt(soLuongStr);
            if (soLuong < 0) {
                JOptionPane.showMessageDialog(this, "Số lượng không được âm.");
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Số lượng phải là số hợp lệ.");
            return false;
        }

        // Xác thực các trường khác nếu cần
        return true;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addSize;
    private javax.swing.JButton btnAddSPCT;
    private javax.swing.JButton btnAddTT;
    private javax.swing.JButton btnCTSP;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnNewTT;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JButton btnUpdateTT;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.ButtonGroup buttonGroup4;
    private javax.swing.ButtonGroup buttonGroup5;
    private javax.swing.JComboBox<String> cboChatLieu;
    private javax.swing.JComboBox<String> cboKieuDang;
    private javax.swing.JComboBox<String> cboLocChatLieu;
    private javax.swing.JComboBox<String> cboLocKieuDang;
    private javax.swing.JComboBox<String> cboLocLopLot;
    private javax.swing.JComboBox<String> cboLocMauSac;
    private javax.swing.JComboBox<String> cboLocMu;
    private javax.swing.JComboBox<String> cboLocsize;
    private javax.swing.JComboBox<String> cboLopLot;
    private javax.swing.JComboBox<String> cboMauSac;
    private javax.swing.JComboBox<String> cboMu;
    private javax.swing.JComboBox<String> cboSize;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JLabel lb;
    private javax.swing.JLabel lblMaThuocTinh;
    private javax.swing.JLabel lblTenThuocTinh;
    private javax.swing.JLabel lblTenThuocTinh1;
    private javax.swing.JTabbedPane pnTong;
    private javax.swing.JRadioButton rdoChatLieu;
    private javax.swing.JRadioButton rdoHDTT;
    private javax.swing.JRadioButton rdoHoatDong3;
    private javax.swing.JRadioButton rdoKieuDang;
    private javax.swing.JRadioButton rdoLopLot;
    private javax.swing.JRadioButton rdoMauSac;
    private javax.swing.JRadioButton rdoMu;
    private javax.swing.JRadioButton rdoNHDTT;
    private javax.swing.JRadioButton rdoNgungHoatDong3;
    private javax.swing.JRadioButton rdoSearchHD;
    private javax.swing.JRadioButton rdoSearchNHD;
    private javax.swing.JRadioButton rdoSize;
    private javax.swing.JTextArea taMoTa;
    private javax.swing.JTable tblSanPham;
    private javax.swing.JTable tblSanPhamChiTiet;
    private javax.swing.JTable tblThuocTinh;
    private javax.swing.JTextField tfTimKiemSpct;
    private javax.swing.JTextField txtGia;
    private javax.swing.JTextField txtMaSanPham3;
    private javax.swing.JTextField txtMaSpct;
    private javax.swing.JTextField txtMaThuocTinh;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtTenSanPham;
    private javax.swing.JTextField txtTenSanPham3;
    private javax.swing.JTextField txtTenThuocTinh;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
