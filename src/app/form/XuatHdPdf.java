/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.form;

import app.service.HoaDonService;
import app.util.GetMaSanPham;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import java.awt.Color;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PC
 */
public class XuatHdPdf {
    public static void main(String[] args) throws FileNotFoundException{
         HoaDonService qlservice = new HoaDonService();
        String path = "Hoadon.pdf";
        PdfWriter pdfWriter = new PdfWriter(path);
        PdfDocument pdfdocument = new PdfDocument(pdfWriter);
        pdfdocument.setDefaultPageSize(PageSize.A4);
        Document document = new Document(pdfdocument);
        float threecol = 190f;
        float twocol = 280f;
        float twocol150 = twocol + 150f;
        float twocolumWidth[] = {twocol150, twocol};
        float fullwidth[] = {threecol * 3};
        float collWidth[] = {150, 250, 150, 250};
        float iteminfoColWidth[] = {140, 140, 140, 140};
        float totalAmount[] = {500, 150, 200};
        float columfooter[] = {450, 450};
        Table table = new Table(twocolumWidth);
        
         long millis = System.currentTimeMillis();
        java.util.Date datenow = new java.util.Date(millis);
        table.addCell(new Cell().add(new Paragraph("Hoa don").setBold().setFontSize(30f)).setBorder(Border.NO_BORDER));
        Table nestedtable = new Table(new float[]{twocol / 2, twocol / 2});
        nestedtable.addCell(new Cell().add(new Paragraph("Ma hoa don:").setBold()).setBorder(Border.NO_BORDER));
        nestedtable.addCell(new Cell().add(new Paragraph(qlservice.TimThongTinHd(GetMaSanPham.maHDpdf).getMaHoaDon())).setBorder(Border.NO_BORDER));
        nestedtable.addCell(new Cell().add(new Paragraph("Ngay lap:").setBold()).setBorder(Border.NO_BORDER));
        nestedtable.addCell(new Cell().add(new Paragraph(String.valueOf(datenow))).setBorder(Border.NO_BORDER));
        table.addCell(new Cell().add(nestedtable).setBorder(Border.NO_BORDER));
        //line phân cách
        Border bd = new SolidBorder(1f / 2f);
        Table divider = new Table(fullwidth);
        divider.setBorder(bd);
        
         Table customerInfoTable = new Table(collWidth);
        customerInfoTable.addCell(new Cell(0, 3).add(new Paragraph("Thong tin khach hang")).setBold().setBorder(Border.NO_BORDER));
        customerInfoTable.addCell(new Cell().add(new Paragraph("Ten khach hang:")).setBorder(Border.NO_BORDER));
        customerInfoTable.addCell(new Cell().add(new Paragraph(qlservice.TimThongTinHd(GetMaSanPham.maHDpdf).getTenKH())).setBorder(Border.NO_BORDER));
        customerInfoTable.addCell(new Cell().add(new Paragraph("So dien thoai:")).setBorder(Border.NO_BORDER));
        customerInfoTable.addCell(new Cell().add(new Paragraph(qlservice.TimThongTinHd(GetMaSanPham.maHDpdf).getSdt())).setBorder(Border.NO_BORDER));
    
        customerInfoTable.addCell(new Cell().add(new Paragraph("Ngay thanh toan:")).setBorder(Border.NO_BORDER));
        customerInfoTable.addCell(new Cell().add(new Paragraph(qlservice.TimThongTinHd(GetMaSanPham.maHDpdf).getNgayThanhToan().toString())).setBorder(Border.NO_BORDER));
        //body.sanpham
        
         Table itemInfoTable = new Table(iteminfoColWidth);
        itemInfoTable.addCell(new Cell().add(new Paragraph("Ten san pham")).setTextAlignment(TextAlignment.CENTER).setBackgroundColor(new DeviceRgb(Color.WHITE)).setFontColor(new DeviceRgb(Color.BLACK)));
        itemInfoTable.addCell(new Cell().add(new Paragraph("So luong")).setTextAlignment(TextAlignment.CENTER).setBackgroundColor(new DeviceRgb(Color.BLACK)).setFontColor(new DeviceRgb(Color.WHITE)));
        itemInfoTable.addCell(new Cell().add(new Paragraph("Don gia")).setTextAlignment(TextAlignment.CENTER).setBackgroundColor(new DeviceRgb(Color.BLACK)).setFontColor(new DeviceRgb(Color.WHITE)));
        itemInfoTable.addCell(new Cell().add(new Paragraph("Thanh tien")).setTextAlignment(TextAlignment.CENTER).setBackgroundColor(new DeviceRgb(Color.BLACK)).setFontColor(new DeviceRgb(Color.WHITE)));
        
          for (int i = 0; i < qlservice.getSPCT(GetMaSanPham.maHDpdf).size(); i++) {
            itemInfoTable.addCell(new Cell().add(new Paragraph(qlservice.getSPCT(GetMaSanPham.maHDpdf).get(i).getSanPham().getTenSP())).setTextAlignment(TextAlignment.CENTER));
            itemInfoTable.addCell(new Cell().add(new Paragraph(String.valueOf(qlservice.getSPCT(GetMaSanPham.maHDpdf).get(i).getSoLuong()))).setTextAlignment(TextAlignment.CENTER));
            itemInfoTable.addCell(new Cell().add(new Paragraph(String.valueOf(qlservice.getSPCT(GetMaSanPham.maHDpdf).get(i).getGia()))).setTextAlignment(TextAlignment.CENTER));
            itemInfoTable.addCell(new Cell().add(new Paragraph(String.valueOf(qlservice.getSPCT(GetMaSanPham.maHDpdf).get(i).getGia() * qlservice.getSPCT(GetMaSanPham.maHDpdf).get(i).getSoLuong()))).setTextAlignment(TextAlignment.CENTER));
        }
          
          
          Table totalAmountTable = new Table(totalAmount);
        totalAmountTable.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        totalAmountTable.addCell(new Cell().add(new Paragraph("Tong tien hang :")).setTextAlignment(TextAlignment.CENTER).setBorder(Border.NO_BORDER));
        totalAmountTable.addCell(new Cell().add(new Paragraph(String.valueOf(qlservice.TimThongTinHd(GetMaSanPham.maHDpdf).getTongtientt()))).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.CENTER));
        totalAmountTable.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        totalAmountTable.addCell(new Cell().add(new Paragraph("Giam gia :")).setTextAlignment(TextAlignment.CENTER).setBorder(Border.NO_BORDER));
        totalAmountTable.addCell(new Cell().add(new Paragraph(String.valueOf(String.format("%.3f", qlservice.TimThongTinHd(GetMaSanPham.maHDpdf).getTongtientt()- qlservice.TimThongTinHd(GetMaSanPham.maHDpdf).getTongtientt())))).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.CENTER));
        totalAmountTable.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        totalAmountTable.addCell(new Cell().add(new Paragraph("Thanh toan :")).setTextAlignment(TextAlignment.CENTER).setBorder(Border.NO_BORDER));
        totalAmountTable.addCell(new Cell().add(new Paragraph(String.valueOf(qlservice.TimThongTinHd(GetMaSanPham.maHDpdf).getTongtientt()))).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.CENTER));

  Table footer = new Table(columfooter);
        footer.addCell(new Cell().add(new Paragraph("Cam on và hen gap lai quy khach!")).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.CENTER));
        footer.addCell(new Cell().add(new Paragraph("JACKET STORE")).setFontSize(20f).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.CENTER));
        footer.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        footer.addCell(new Cell().add(new Paragraph("Dc: FPT POLYTECHNIC, P. KIEUMAI, Nam Tu Liem, Ha Noi")).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.CENTER));

        document.add(table);
        document.add(divider);
        document.add(new Paragraph("\n"));
        document.add(customerInfoTable);
        document.add(new Paragraph("\n"));
        document.add(itemInfoTable);
        document.add(totalAmountTable);
        document.add(new Paragraph("\n"));
        document.add(divider);
        document.add(new Paragraph("\n"));
        document.add(footer);
        document.close();

        String pathhoadon = "D:\\";
        File file = new File(path);
        try {
            Desktop.getDesktop().open(file);

        } catch (IOException ex) {
            Logger.getLogger(XuatHdPdf.class.getName()).log(Level.SEVERE, null, ex);
        }

    
        
    }
}
