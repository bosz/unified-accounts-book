/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Tables;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import static org.apache.derby.impl.tools.ij.ijConstants.RESOURCE;
import org.controlsfx.dialog.Dialogs;

/**
 *
 * @author root
 */
public class PrinterClass {
    
    private FileOutputStream fs;
    Font tHeadingFont, tBodyFont, companyNameFont, companySubInfoFont, smallFooter;
    Date dNow;
    SimpleDateFormat dateTime, dateOnly, timeOnly, datForFile;
    
    private String saveDir;
    
    private Paragraph companyHeader;
    
    public static final String companyLogo = "img/companyLogo.png";
    
    private String companyName, companyLocation, companyTel, companyPoBox;
    
    public PrinterClass(){
        /****default values for the printer */
        System.out.println("instantiating the printer class");
        
        companyName = "Unity Systems";
        companyLocation = "Malingo, HIAMS building, Buea";
        companyTel = "+237 679574561";
        companyPoBox = "PB 1001, Buea";
        
        smallFooter = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.ITALIC, new BaseColor(157, 175, 157));
        this.companyNameFont = new Font(Font.FontFamily.TIMES_ROMAN, 22, Font.BOLD, new BaseColor(0, 0, 128));
        this.companySubInfoFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD, new BaseColor(51, 102, 13));
        this.tHeadingFont = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD, new BaseColor(51, 51, 0));
        this.tBodyFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD, new BaseColor(51, 51, 51));
        
        this.saveDir = "\\"; 
        
        dateOnly = new SimpleDateFormat("yyyy-MM-dd");
        timeOnly = new SimpleDateFormat("hh:mm:ss");
        dateTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        datForFile = new SimpleDateFormat("yyyy-MM-dd __ hh mm ss");
    }
    
    public void setCompanyDetails(String name, String location, String poBox, String tel){
        this.companyName = name;
        this.companyLocation = location;
        this.companyTel = tel;
        this.companyPoBox = poBox;
        
    }
    
    /**
     * @param fontFamily the font family u want to use eg, TIMES_ROMAN
     * @param style the font style eg. italics, bold etc
     * @param baseColor the base color eg BaseColor(23, 22, 53)
     * @param size the font size eg 22.3, 53
     */
    public void setCompanyNameFont(String fontFamily, String style, BaseColor baseColor, float size){
        if(!"".equals(fontFamily))
            this.companyNameFont.setFamily(fontFamily);
        if(size != 0)
            this.companyNameFont.setSize(size);
        if(!"".equals(style))
            this.companyNameFont.setStyle(style); 
        if(baseColor != null)
            this.companyNameFont.setColor(baseColor);
    }
    
    /**
     * @param fontFamily the font family u want to use eg, TIMES_ROMAN
     * @param style the font style eg. italics, bold etc
     * @param baseColor the base color eg BaseColor(23, 22, 53)
     * @param size the font size eg 22.3, 53
     */
    public void setCompanySubInfoFont(String fontFamily, String style, BaseColor baseColor, float size){
        if(!"".equals(fontFamily))
            this.companySubInfoFont.setFamily(fontFamily);
        if(size != 0)
            this.companySubInfoFont.setSize(size);
        if(!"".equals(style))
            this.companySubInfoFont.setStyle(style); 
        if(baseColor != null)
            this.companySubInfoFont.setColor(baseColor);
    }
    
    /**
     * @param fontFamily the font family u want to use eg, TIMES_ROMAN
     * @param style the font style eg. italics, bold etc
     * @param baseColor the base color eg BaseColor(23, 22, 53)
     * @param size the font size eg 22.3, 53
     */
    public void setTHeadingFont(String fontFamily, String style, BaseColor baseColor, float size){       
        if(!"".equals(fontFamily))
            this.tHeadingFont.setFamily(fontFamily);
        if(size != 0)
            this.tHeadingFont.setSize(size);
        if(!"".equals(style))
            this.tHeadingFont.setStyle(style); 
        if(baseColor != null)
            this.tHeadingFont.setColor(baseColor);
    }
    
    /**
     * @param fontFamily the font family u want to use eg, TIMES_ROMAN
     * @param style the font style eg. italics, bold etc
     * @param baseColor the base color eg BaseColor(23, 22, 53)
     * @param size the font size eg 22.3, 53
     */
    public void setTBodyFont(String fontFamily, String style, BaseColor baseColor, float size){        
        if(!"".equals(fontFamily))
            this.tBodyFont.setFamily(fontFamily);
        if(size != 0)
            this.tBodyFont.setSize(size);
        if(!"".equals(style))
            this.tBodyFont.setStyle(style); 
        if(baseColor != null)
            this.tBodyFont.setColor(baseColor);
    }
    
    
    /**
     * @param saveD the folder inside your system directory you will like to save the files.
     */
    public void setSavingDir(String saveD){
        if(!"".equals(saveD)){
            this.saveDir = saveD;
        }
        else{
            Dialogs.create().title("Empty String").message("No saving directory. The string has no value").showError();
        }
    }
    
    
    /***************HEADINGS HEADINGS HEADINGS */
    
    private void makeCompanyHeading(Document doc) throws DocumentException, BadElementException, IOException{
        
        //adding the company logo.
        try{
            Image img = Image.getInstance(this.companyLogo);
            img.scaleAbsolute(80f, 80f);
            img.setAlt("Unity Systems");
            img.setAlignment(Element.ALIGN_CENTER);
            
//            Image img = Image.getInstance(this.companyLogo);
            
            /*with the next 3 lines oc code, the image will align at the center of the page.*/
//            img.setAbsolutePosition(
//                (PageSize.A4.getWidth() - img.getScaledWidth()) / 2,
//                (PageSize.A4.getHeight() - img.getScaledHeight()) / 2);

            doc.add(img);
        }catch(IOException | DocumentException ex){
            Dialogs.create().title("Logo Not found").message("The Company's logo has not been located. So the printout will be without the logo of the company").showError();
            ex.printStackTrace();
        }
        
        Paragraph companyName = new Paragraph(this.companyName, this.companyNameFont);
        Paragraph newLine = new Paragraph("\n");
        Paragraph companyLocation = new Paragraph(this.companyLocation + "\n" + 
                                                  this.companyPoBox + "\n "+ 
                                                  this.companyTel, 
                                                  this.companySubInfoFont);
        
        companyName.setAlignment(Element.ALIGN_CENTER);
        companyLocation.setAlignment(Element.ALIGN_CENTER);
        newLine.setAlignment(Element.ALIGN_CENTER);
        
        doc.add(companyName);
        doc.add(companyLocation);
        doc.add(newLine);
        
    }
    /****************************************
     * heading ends
     */
    
    private void insertTableHeading(PdfPTable table, String text, int align, int colspan, Font font){      
         //create a new cell with the specified Text and Font   
         PdfPCell cell = new PdfPCell(new Phrase(text.trim(), font));   
        //set the cell alignment   
         cell.setHorizontalAlignment(align);   
        //set the cell column span in case you want to merge two or more cells   
         cell.setColspan(colspan);   
        //in case there is no text and you wan to create an empty row   
         if(text.trim().equalsIgnoreCase("")){    
             cell.setMinimumHeight(10f);
         }    
        //add the call to the table  
        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        table.addCell(cell);      
    }
    
    private void insertCell(PdfPTable table, String text, int align, int colspan, Font font){      
         //create a new cell with the specified Text and Font   
         PdfPCell cell = new PdfPCell(new Phrase(text.trim(), font));   
        //set the cell alignment   
         cell.setHorizontalAlignment(align);   
        //set the cell column span in case you want to merge two or more cells   
         cell.setColspan(colspan);   
        //in case there is no text and you wan to create an empty row   
         if(text.trim().equalsIgnoreCase("")){    
             cell.setMinimumHeight(10f);
         }    
        //add the call to the table  
        table.addCell(cell);      
    } 
    
    
    
    public void footer(Document doc) throws DocumentException{
        Paragraph paragraph = new Paragraph("@" + this.companyName);    
        doc.add(paragraph);
        dNow = new Date();
        

        String prodDate;
        prodDate = "Created on the " + dateOnly.format(dNow) + " at " + timeOnly.format(dNow);
       
        
        //////////////////
        
        PdfPTable timeOfProduction = new PdfPTable(1);
        
        PdfPCell cell = new PdfPCell(new Phrase(prodDate.trim(), smallFooter));   
          cell.setHorizontalAlignment(Element.ALIGN_RIGHT);   
          cell.setColspan(3); 
          cell.setBorder(0);
          if(prodDate.trim().equalsIgnoreCase("")){    
             cell.setMinimumHeight(10f);
         }
          
         timeOfProduction.addCell(cell);
         doc.add(timeOfProduction);
         
         try{
             Image img = Image.getInstance(this.companyLogo);
             img.scaleAbsolute(20f, 20f);
             
             img.setAbsolutePosition(
                (PageSize.A4.getWidth() - img.getScaledWidth()) ,
                (PageSize.A4.getHeight() - img.getScaledHeight()) ); //place the image at the bottom right position
             
             img.setAlt("Unity Systems");
         }catch(Exception ex){
         
         }
         
        
    }
    
    
    
    /********************************************************************
     *    ___    ___    _ _  _____                _______      ___      *
     *   |   \  |   \    |     |      |\      |  |            |   \     *
     *   |    | |    |   |     |      | \     |  |            |    |    *   C
     *   |    | |    |   |     |      |  \    |   \______     |    |    *    L
     *   |____/ |___/    |     |      |   \   |   /           |___/     *     A
     *   |      |   \    |     |      |    \  |  |            |   \     *      S
     *   |      |    |   |     |      |     \ |  |            |    |    *       S
     *   |      |    |  _|_    |      |      \|  `-------     |    |    *
     *******************************************************************`
     * By Fongoh Martin Tayong, fongohmartin@gmail.com 
     */
    
    /*******************************************************************************************
     * printer for two cols
     * *****************************************************************************************/
    
    /**
     * heading ends
     * @param tabId
     * @param col1
     * @param col2
     * @param tHeading
     * @param fileName
     * @param tTitle
     */
    public void print(TableView tabId, TableColumn col1, TableColumn col2, String fileName, String tTitle, String[] tHeading){
        dNow = new Date();
        System.out.println("printing with 2 cols");
        try{
            String completeFileName = this.saveDir + fileName + this.datForFile.format(dNow) + ".pdf";
            completeFileName = System.getProperties().getProperty("user.home")+ completeFileName;
            fs = new FileOutputStream(completeFileName ); //this is for windows. since am developing on linux, i will use the next line. But for production version, i will enable the first optionand comment the second option as most users will be on the windows system. Or, will detect the OS and decide where to keept the files. Better idea
            
            Document doc = new Document(PageSize.A4, 20, 20, 20, 20);
            PdfWriter writer = PdfWriter.getInstance(doc, fs);
            
            //specify column widths    
            float[] columnWidths = {5f, 5f};
            PdfPTable table = new PdfPTable(columnWidths);
            
            table.setWidthPercentage(90f);
            
            PdfPCell cell = new PdfPCell(new Phrase("\nRECIEPT FOR " ));
            cell.setColspan(6);
            cell.setHorizontalAlignment(2);
            doc.open();
            //doc.add(im);
            
            makeCompanyHeading(doc);
            
            doc.add(cell);
            System.out.println(doc.leftMargin());
 
            for (String tHeading1 : tHeading) {
                insertTableHeading(table, tHeading1, Element.ALIGN_CENTER, 1, this.tHeadingFont);    
            }
            table.setHeaderRows(1); 
            
            
            
            /*Now i have to fill the table  */
            for (int i = 0; i < tabId.getItems().size(); i++) {
                insertCell(table, col1.getCellData(i).toString(), Element.ALIGN_LEFT, 1, this.tBodyFont); 
                insertCell(table, col2.getCellData(i).toString(), Element.ALIGN_LEFT, 1, this.tBodyFont); 
            }
            
            doc.add(table);
            
            footer(doc);   // this is rgw footer of the page
            
            doc.addCreationDate();
            doc.close();
            System.out.println("finished the printing job");
            
            Dialogs.create()
                    .title("Printing complete")
                    .masthead("The printing process was completed succesfully")
                    .message("See : " + completeFileName ).showInformation();
        }catch(Exception sd){
            sd.printStackTrace();
            Dialogs.create().title("Printer Error").masthead("Errro In Printing File").message("There was an error while trying to print. ").showError();
        }finally{}
    }
    
    
    
    /*******************************************************************************************
     * printer for 3 cols
     * *****************************************************************************************/
    
    /**
     * heading ends
     * @param tabId
     * @param col1
     * @param col2
     * @param tHeading
     * @param fileName
     * @param tTitle
     */
    public void print(TableView tabId, 
                      TableColumn col1, TableColumn col2, TableColumn col3, 
                      String fileName, String tTitle, String[] tHeading){
        dNow = new Date();
        System.out.println("printing with 2 cols");
        try{
            String completeFileName = this.saveDir + fileName + this.datForFile.format(dNow) + ".pdf";
            completeFileName = System.getProperties().getProperty("user.home")+ completeFileName;
            fs = new FileOutputStream(completeFileName ); //this is for windows. since am developing on linux, i will use the next line. But for production version, i will enable the first optionand comment the second option as most users will be on the windows system. Or, will detect the OS and decide where to keept the files. Better idea
            
            Document doc = new Document(PageSize.A4, 20, 20, 20, 20);
            PdfWriter writer = PdfWriter.getInstance(doc, fs);
            
            //specify column widths    
            float[] columnWidths = {4f, 4f, 4f};
            PdfPTable table = new PdfPTable(columnWidths);
            
            table.setWidthPercentage(90f);
            
            PdfPCell cell = new PdfPCell(new Phrase("\nRECIEPT FOR " ));
            cell.setColspan(6);
            cell.setHorizontalAlignment(2);
            doc.open();
            //doc.add(im);
            
            makeCompanyHeading(doc);
            
            doc.add(cell);
            System.out.println(doc.leftMargin());
 
            for (String tHeading1 : tHeading) {
                insertTableHeading(table, tHeading1, Element.ALIGN_CENTER, 1, this.tHeadingFont);    
            }
            table.setHeaderRows(1); 
            
            
            
            /*Now i have to fill the table  */
            for (int i = 0; i < tabId.getItems().size(); i++) {
                insertCell(table, col1.getCellData(i).toString(), Element.ALIGN_LEFT, 1, this.tBodyFont); 
                insertCell(table, col2.getCellData(i).toString(), Element.ALIGN_LEFT, 1, this.tBodyFont); 
                insertCell(table, col3.getCellData(i).toString(), Element.ALIGN_LEFT, 1, this.tBodyFont); 
            }
            
            doc.add(table);
            
            footer(doc);   // this is rgw footer of the page
            
            doc.addCreationDate();
            doc.close();
            System.out.println("finished the printing job");
            
            Dialogs.create()
                    .title("Printing complete")
                    .masthead("The printing process was completed succesfully")
                    .message("See : " + completeFileName ).showInformation();
        }catch(Exception sd){
            sd.printStackTrace();
            Dialogs.create().title("Printer Error").masthead("Errro In Printing File").message("There was an error while trying to print. ").showError();
        }finally{}
    }
    
    
    
    
}
