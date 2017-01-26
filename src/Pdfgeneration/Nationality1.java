/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pdfgeneration;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import jdbcconnection.JDBCTemplate;
import windows.*;
import java.util.Date;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import javax.swing.JOptionPane;
import jdbcconnection.JDBCTemplate;
import Pdfgeneration.Border;
import mailsending.email1;
import windows.Login;
/**
 *
 * @author sharmeen
 */
public class Nationality1 extends PdfPageEventHelper {
    
    
    public String country="India";
    public String space="              ";
    public String appname="Junaid Khan Name Khan";
    public static String place="Dharangaon";
    public static String Taluka="Dharangaon";
    public String op="(";
    public String clo=")";
    public String bdate=op+"14-10-1995"+clo;
    public String day="Seventh";
    public String month="May";
    public String year="Nineteen Ninety Three";
    public String bdatewords=day+" Day of "+month+" of "+year;
    public static String dist="Jalgaon";
    public String state="Maharashtra";
   
    public int issyear=2010;
    public String docno="";
    public String issueyear=Integer.toString(issyear);
    
    
    public String imagepath="D:\\DDGUMPROJECT\\src\\images\\Sunset.png";
    public String imagepaths="D:\\DDGUMPROJECT\\src\\images\\stamp.jpg";
    //public String RESULT = "Nationality1.pdf";
    
                Date currDate=new Date();
             
	
    public Nationality1(String id)
    {
        System.out.println(id);
        String ss="njmh"+Integer.toString(Home.cno);
        Home.cno++;
        System.out.println(ss);
       try
		{
                           
		  java.sql.Connection c=null;
		  c=JDBCTemplate.getConnection();
		  java.sql.Statement str=c.createStatement();
                  //str.executeQuery("UPDATE nationality SET ce  WHERE userid='"+id+"'");
        	  java.sql.ResultSet rs3=str.executeQuery("SELECT * FROM nationality  WHERE userid='"+id+"'");
		    if(rs3.next())
		    {
		      	 appname=rs3.getString(2);
                         place=rs3.getString(3);
                         Taluka=rs3.getString(5);
                         state=rs3.getString(7);
                         Date bdates=rs3.getDate(9);
                         System.out.println(bdates);
                         bdate=bdates.toString();
                         System.out.println(bdate);
                         docno=rs3.getString(21);
                         System.out.println(docno);
                         Date issueyears=rs3.getDate(22);
                         issueyear=issueyears.toString();
                         
                         this.createPdf(id+".pdf");
                         
                         email1 eob=new email1(id);
                         
		     }
		    
		  }
		   catch(Exception e)
		   {
                       System.out.println(e);
		   }
            
    
    }
    public void onEndPage(PdfWriter writer, Document document) {
    PdfContentByte cb = writer.getDirectContent();
    Rectangle pageSize = writer.getPageSize();
    
    //System.out.println(pageSize.getLeft());
    //System.out.println(pageSize.getBottom());
    //System.out.println(pageSize.getWidth());
    //System.out.println(pageSize.getHeight());
    
    
    Rectangle rect=new Rectangle(pageSize.getLeft() + 10, pageSize.getBottom() + 10,
      pageSize.getWidth() - 20, pageSize.getHeight() - 20);
    
    rect.setBorder(Rectangle.BOX);
   
    rect.setBorderWidth(10);
    rect.setBorderColor(BaseColor.BLACK);
    
    //cb.rectangle(pageSize.getLeft() + 10, pageSize.getBottom() + 10,pageSize.getWidth() - 20, pageSize.getHeight() - 20);
    cb.rectangle(rect);
  cb.stroke();
  
  }
    
    /*public static void main(String args[]) throws IOException, DocumentException
    {
        
                new Nationality1().createPdf("Nationality.pdf");
    }*/

    public void createPdf(String dest) throws IOException, DocumentException {
    Document document = new Document();
    PdfWriter writer=PdfWriter.getInstance(document, new FileOutputStream(dest));
    
    writer.setPageEvent(new Border());
    
    document.open();
    
    /*Image image1=Image.getInstance("Sunset.png");
    image1.scaleAbsolute(50f, 30f);
    image1.setAlignment(Image.ALIGN_RIGHT); */
    
    
    Font font1=new Font(Font.FontFamily.TIMES_ROMAN,20,Font.BOLDITALIC,BaseColor.BLACK);
    Paragraph title=new Paragraph("Certificate of Nationality\n",font1);
    title.setAlignment(Paragraph.ALIGN_CENTER);
    
    Font font2=new Font(Font.FontFamily.UNDEFINED,18,Font.NORMAL,BaseColor.BLACK);
    Paragraph subtitle1=new Paragraph("(Issued By Authorities in "+country+")\n",font2);
    subtitle1.setAlignment(Paragraph.ALIGN_CENTER);
    
    Font font5=new Font(Font.FontFamily.UNDEFINED,16,Font.NORMAL,BaseColor.BLACK);
    Paragraph subtitle2=new Paragraph("On submission of proofs noted below",font5);
    subtitle2.setAlignment(Paragraph.ALIGN_CENTER);
    
    Font font3=new Font(Font.FontFamily.UNDEFINED,20,Font.UNDERLINE,BaseColor.BLACK);
    Paragraph head=new Paragraph("* IT IS HERE BY CERTIFIED THAT *",font3);
    head.setAlignment(Paragraph.ALIGN_CENTER);
    
    //Font font6=new Font(Font.FontFamily.UNDEFINED,16,Font.NORMAL,BaseColor.BLACK);
    //Paragraph content=new Paragraph(space+""+appname+" At. "+place+" Tal: "+Taluka+" Was Born "+bdate+" on the "+bdatewords+" Place of Birth At:- "+place+" Tal:- "+Taluka+" Dist:- "+dist+" in the State of "+state+" with the territory of "+country+".",font6);
    //content.setAlignment(Paragraph.ALIGN_JUSTIFIED);
    
    Font font9=new Font(Font.FontFamily.UNDEFINED,12,Font.BOLDITALIC,BaseColor.BLACK);
    
    Font font10=new Font(Font.FontFamily.UNDEFINED,12,Font.NORMAL,BaseColor.BLACK);
    
    
    Font font4=new Font(Font.FontFamily.UNDEFINED,20,Font.BOLD,BaseColor.BLACK);
    Paragraph proof=new Paragraph("Particulars of Proofs Submitted",font4);
    proof.setAlignment(Paragraph.ALIGN_CENTER);
    
    Font font7=new Font(Font.FontFamily.UNDEFINED,14,Font.ITALIC,BaseColor.BLACK);
    List list=new List(true,30);
        list.add(new ListItem("Aadhar Card",font7));
        list.add(new ListItem("Birth Certificate",font7));
        list.add(new ListItem("Affedavit",font7));
        
    Font font8=new Font(Font.FontFamily.UNDEFINED,16,Font.BOLD,BaseColor.BLACK);
    Paragraph p8=new Paragraph("No/MAG/SR/"+docno+"/"+issueyear,font8);
    p8.setAlignment(Paragraph.ALIGN_LEFT);
    
    PdfPTable table = new PdfPTable(3);
    table.setWidthPercentage(100);
    table.setWidths(new int[]{4, 2, 3});
    //table.setWidths(new float[]{2,2,2});
    
    
    table.addCell(createTextCell());
    table.addCell(createCell2());
    table.addCell(createImageCell(imagepath));
    table.addCell(createCell2());
    table.addCell(createImageCell1(imagepaths));
    table.addCell(createTextCell1());
    
    
    
    document.add(title);
    //document.add(Chunk.NEWLINE);
    document.add(subtitle1);
    //document.add(Chunk.NEWLINE);
    document.add(subtitle2);
    
    document.add(Chunk.NEWLINE);
    
    document.add(head);
    
    document.add(Chunk.NEWLINE);
    
    //document.add(content);
    document.add(new Chunk(space));
    document.add(new Chunk(appname,font9));
    document.add(new Chunk(" At. ",font10));
    document.add(new Chunk(place,font9));
    document.add(new Chunk(" Tal: ",font10));
    document.add(new Chunk(Taluka,font9));
    document.add(new Chunk(" was Born ",font10));
    document.add(new Chunk(bdate,font9));
    document.add(new Chunk(" on the ",font10));
    document.add(new Chunk(bdatewords,font9));
    document.add(new Chunk(" Place of Birth At. ",font10));
    document.add(new Chunk(place,font9));
    document.add(new Chunk(" Tal: ",font10));
    document.add(new Chunk(Taluka,font9));
    document.add(new Chunk(" Dist: ",font10));
    document.add(new Chunk(dist,font9));
    document.add(new Chunk(" in the state of ",font10));
    document.add(new Chunk(state,font9));
    document.add(new Chunk(" with the territory of ",font10));
    document.add(new Chunk(country,font9));
    document.add(new Chunk(".",font10));
    
    
    document.add(Chunk.NEWLINE);
     document.add(Chunk.NEWLINE);
    
    document.add(proof);
    
    document.add(Chunk.NEWLINE);
    
    document.add(list);
    
    document.add(Chunk.NEWLINE);
    document.add(Chunk.NEWLINE);
    
    document.add(p8);
    
    document.add(Chunk.NEWLINE);
    
    document.add(table);
    
    document.close();
}
    
    public static PdfPCell createImageCell(String path) throws DocumentException, IOException {
    Image img = Image.getInstance(path);
    img.scaleAbsolute(10f, 10f);
    img.setAlignment(Image.ALIGN_LEFT);
    PdfPCell cell = new PdfPCell(img, true);
    
    cell.setVerticalAlignment(Element.ALIGN_CENTER);
    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    cell.setBorder(Rectangle.NO_BORDER);
    cell.setFixedHeight(70);
    
    cell.setColspan(1);
    return cell;
}
    
    
    public static PdfPCell createImageCell1(String path) throws DocumentException, IOException {
    Image img = Image.getInstance(path);
    img.scaleAbsolute(10f, 10f);
    img.setAlignment(Image.ALIGN_RIGHT);
    PdfPCell cell = new PdfPCell(img, true);
    
    cell.setVerticalAlignment(Element.ALIGN_CENTER);
    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    cell.setBorder(Rectangle.NO_BORDER);
    return cell;
}

    public static PdfPCell createTextCell() throws DocumentException, IOException {
    PdfPCell cell = new PdfPCell();
    Paragraph pc1 = new Paragraph("Place :- "+place,new Font(Font.FontFamily.UNDEFINED,14,Font.BOLD,BaseColor.BLACK));
    Paragraph pc2 = new Paragraph("Issue :- "+Taluka,new Font(Font.FontFamily.UNDEFINED,14,Font.BOLD,BaseColor.BLACK));
    Paragraph pc3 = new Paragraph("Date :- "+new Date().toString(),new Font(Font.FontFamily.UNDEFINED,12,Font.BOLD,BaseColor.BLACK));
    pc1.setAlignment(Element.ALIGN_LEFT);
    pc2.setAlignment(Element.ALIGN_LEFT);
    pc3.setAlignment(Element.ALIGN_LEFT);
    cell.addElement(pc1);
    cell.addElement(pc2);
    cell.addElement(pc3);
    cell.setVerticalAlignment(Element.ALIGN_LEFT);
    cell.setBorder(Rectangle.NO_BORDER);
    return cell;
}
    
    public static PdfPCell createTextCell1() throws DocumentException, IOException {
    PdfPCell cell = new PdfPCell();
    Paragraph pc1 = new Paragraph("Taluka Executive Magistrate",new Font(Font.FontFamily.UNDEFINED,12,Font.BOLD,BaseColor.BLACK));
    Paragraph pc2 = new Paragraph(""+place,new Font(Font.FontFamily.UNDEFINED,16,Font.BOLD,BaseColor.BLACK));
    
    pc1.setAlignment(Element.ALIGN_CENTER);
    pc2.setAlignment(Element.ALIGN_CENTER);
    
    cell.addElement(pc1);
    cell.addElement(pc2);
    
    cell.setVerticalAlignment(Element.ALIGN_CENTER);
    cell.setBorder(Rectangle.NO_BORDER);
    return cell;
}
    
    public static PdfPCell createCell2() throws DocumentException, IOException {
    PdfPCell cell = new PdfPCell();
    
    cell.setBorder(Rectangle.NO_BORDER);
    return cell;
}
    
}
