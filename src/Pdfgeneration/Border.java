/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pdfgeneration;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;

/**
 *
 * @author sharmeen
 */
public class Border extends PdfPageEventHelper {
    
    //public class PageWithRectangle extends PdfPageEventHelper {
  public void onEndPage(PdfWriter writer, Document document) {
    PdfContentByte cb = writer.getDirectContent();
    Rectangle pageSize = writer.getPageSize();
    
    //System.out.println(pageSize.getLeft());
    //System.out.println(pageSize.getBottom());
    //System.out.println(pageSize.getWidth());
    //System.out.println(pageSize.getHeight());
    
    cb.rectangle(pageSize.getLeft() + 10, pageSize.getBottom() + 10,
      pageSize.getWidth() - 20, pageSize.getHeight() - 20);
  cb.stroke();
  }
  public static void main(String[] args) {
    Document document = new Document();
    try {
      PdfWriter writer =
        PdfWriter.getInstance(document, new FileOutputStream("test.pdf"));
        writer.setPageEvent(new Border());
      document.open();
      document.add(new Paragraph("test1"));
      document.newPage();
      document.add(new Paragraph("test2"));
      document.newPage();
      document.add(new Paragraph("test3"));
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
    document.close();
  }
//} 
    
}
