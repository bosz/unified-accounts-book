/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Tables;

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
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import org.controlsfx.control.action.Action;
import org.controlsfx.dialog.Dialogs;



/**
 *
 * @author r
 */
public class Printer {
    /**/
    
    public static final String companyLogo = "img/companyLogo.png";
    Font companyNameFont, reportTitleFont;
    
    /**/
    
    
    private FileOutputStream fs;
    Font bigHeading, unpaid, smallFooter;
    SimpleDateFormat datTim, datOnly, timOnly, datForFile;
             
    Date dNow;
    public Printer(){
        this.companyNameFont = new Font(Font.FontFamily.TIMES_ROMAN, 17, Font.BOLD, new BaseColor(0, 0, 128));
        this.reportTitleFont = new Font(Font.FontFamily.TIMES_ROMAN, 15, Font.BOLD, new BaseColor(51, 102, 13));
        bigHeading = new Font(FontFamily.TIMES_ROMAN, 16, Font.BOLD, new BaseColor(34, 90, 148));
        smallFooter = new Font(FontFamily.TIMES_ROMAN, 12, Font.ITALIC, new BaseColor(0, 0, 0));
        unpaid = new Font(FontFamily.TIMES_ROMAN, 13, Font.BOLD, new BaseColor(98, 45, 46));
        
        datOnly = new SimpleDateFormat("yyyy-MM-dd");
        timOnly = new SimpleDateFormat("hh:mm:ss");
        datTim = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        datForFile = new SimpleDateFormat("yyyy-MM-dd __ hh mm ss");
        
    }
    
    public void genTrans(TableView gentab, TableColumn name, TableColumn desc, 
                              TableColumn amt, TableColumn date, long total, String incOrExp){
         try{
//            Image im = Image.getInstance("src/njeiaccount/njei_img/accountcreation-icon.png");
//            im.scaleAbsolute(150f, 150f);
//            im.setAbsolutePosition(25,50);
//            im.setBackgroundColor(BaseColor.PINK);
             
             dNow = new Date();
             String app = this.datForFile.format(dNow);
             String file = "\\Documents\\NjieAB_receipts\\general transactions\\GenTransRec"+ app + ".pdf";
             System.out.println("file is " + file);
             fs = new FileOutputStream(System.getProperties().getProperty("user.home")+ file ); //this is for windows. since am developing on linux, i will use the next line. But for production version, i will enable the first optionand comment the second option as most users will be on the windows system. Or, will detect the OS and decide where to keept the files. Better idea
             //fs = new FileOutputStream(System.getProperties().getProperty("user.home")+"root/Desktop/reciept.pdf");
            
            Document doc = new Document(PageSize.A4, 20, 20, 20, 20);
                PdfWriter writer = PdfWriter.getInstance(doc, fs);
            //specify column widths    
            float[] columnWidths = {4f, 5f, 1.5f, 2f};
            PdfPTable table = new PdfPTable(columnWidths);
            
             //special font sizes    
            Font bfBold12 = new Font(FontFamily.TIMES_ROMAN, 12, Font.BOLD, new BaseColor(0, 0, 0));     
            Font bf12 = new Font(FontFamily.TIMES_ROMAN, 10);  
            // set table width a percentage of the page width    
            table.setWidthPercentage(90f); 

            
            
            PdfPCell cell = new PdfPCell(new Phrase("\nRECIEPT FOR " ));
            cell.setColspan(6);
            cell.setHorizontalAlignment(2);
            doc.open();
            //doc.add(im);
            
            header(doc, "General "+ incOrExp + " Transaction Table");
            
            doc.add(cell);
            System.out.println(doc.leftMargin());
 
            //insert column headings    
            insertTableHeading(table, "Transaction Name ", Element.ALIGN_MIDDLE, 1, bfBold12);    
            insertTableHeading(table, "Description of Transaction", Element.ALIGN_MIDDLE, 1, bfBold12);    
            insertTableHeading(table, "Amount", Element.ALIGN_MIDDLE, 1, bfBold12);    
            insertTableHeading(table, "Date ", Element.ALIGN_MIDDLE, 1, bfBold12);    
            table.setHeaderRows(1); 
            
            
            
            /*Now i have to fill the table  */
            
            for (int i = 0; i < gentab.getItems().size(); i++) {
                insertCell(table, name.getCellData(i).toString(), Element.ALIGN_LEFT, 1, bf12); 
                insertCell(table, desc.getCellData(i).toString(), Element.ALIGN_LEFT, 1, bf12); 
                insertCell(table, amt.getCellData(i).toString(), Element.ALIGN_LEFT, 1, bf12); 
                insertCell(table, date.getCellData(i).toString(), Element.ALIGN_RIGHT, 1, bf12);
            }

            insertCell(table, "Total ", Element.ALIGN_RIGHT, 2, bfBold12);
            insertCell(table, String.valueOf(total), Element.ALIGN_LEFT, 3, bfBold12); 
            insertCell(table, "", Element.ALIGN_RIGHT, 4, bfBold12);
            doc.add(table);
            
            footer(doc);   // this is rgw footer of the page
            
            doc.addCreationDate();
            doc.close();
            System.out.println("finished the printing job");

            //Desktop.getDesktop().open(new File(System.getProperties().getProperty("user.home")+"root/Desktop/reciept.pdf"));
            try {
                Desktop.getDesktop().open(new File(System.getProperties().getProperty("user.home") + file));
            } catch (IOException iOException) {
                System.out.println("failed opening the file");
                iOException.printStackTrace();
            }
        }catch(Exception sd){
            sd.printStackTrace();
            Dialogs.create().title("Printer Error").masthead("Errro In Printing File").message(sd.getMessage()+" : "+sd.getCause()+" Caused By "+sd.getCause()+"--"+sd.toString()).showError();
        }finally{}
    }
    
   
    
    public void overdraft(TableView ovTab, TableColumn name, TableColumn amtIssued, TableColumn monthlyDeduction,
                                            TableColumn dateIssued, TableColumn startDeduction, TableColumn expDate, 
                                            TableColumn payStatus, TableColumn expStatus, long totIssue, long totDeduction ){
        
        try{
//            Image im = Image.getInstance("src/njeiaccount/njei_img/accountcreation-icon.png");
//            im.scaleAbsolute(150f, 150f);
//            im.setAbsolutePosition(25,50);
//            im.setBackgroundColor(BaseColor.PINK);
             
             dNow = new Date();
             String app = this.datForFile.format(dNow);
             String file = "\\Documents\\NjieAB_receipts\\Overdraft\\GenTransRec"+ app + ".pdf";
             System.out.println("file is " + file);
             fs = new FileOutputStream(System.getProperties().getProperty("user.home")+ file ); //this is for windows. since am developing on linux, i will use the next line. But for production version, i will enable the first optionand comment the second option as most users will be on the windows system. Or, will detect the OS and decide where to keept the files. Better idea
             //fs = new FileOutputStream(System.getProperties().getProperty("user.home")+"root/Desktop/reciept.pdf");
          
            Document doc = new Document();
                PdfWriter writer = PdfWriter.getInstance(doc, fs);
            //specify column widths    
            float[] columnWidths = {2f, 2f, 2f, 3f, 3f, 2f, 1.5f, 1.5f};
            PdfPTable table = new PdfPTable(columnWidths);
            
             //special font sizes    
            Font bfBold12 = new Font(FontFamily.TIMES_ROMAN, 12, Font.BOLD, new BaseColor(0, 0, 0));     
            Font bf12 = new Font(FontFamily.TIMES_ROMAN, 10);  
            // set table width a percentage of the page width    
            table.setWidthPercentage(90f); 

            
            
            PdfPCell cell = new PdfPCell(new Phrase("\nRECIEPT FOR " ));
            cell.setColspan(6);
            cell.setHorizontalAlignment(2);
            doc.open();
            //doc.add(im);
            
            header(doc, "Staff Overdraft Reports");
            
            doc.add(cell);
            System.out.println(doc.leftMargin());
 
            //insert column headings    
            insertTableHeading(table, "Staff Name ", Element.ALIGN_MIDDLE, 1, bfBold12);    
            insertTableHeading(table, "Amount Issued", Element.ALIGN_MIDDLE, 1, bfBold12);    
            insertTableHeading(table, "Monthly Deduction", Element.ALIGN_MIDDLE, 1, bfBold12);    
            insertTableHeading(table, "Date Issued ", Element.ALIGN_MIDDLE, 1, bfBold12);  
            insertTableHeading(table, "Start Deduction", Element.ALIGN_MIDDLE, 1, bfBold12);    
            insertTableHeading(table, "Expiry Date", Element.ALIGN_MIDDLE, 1, bfBold12);    
            insertTableHeading(table, "Pay Status", Element.ALIGN_MIDDLE, 1, bfBold12);    
            insertTableHeading(table, "Expiry Status", Element.ALIGN_MIDDLE, 1, bfBold12);  
            table.setHeaderRows(1); 
            
            
            
            /*Now i have to fill the table  */
            
            for (int i = 0; i < ovTab.getItems().size(); i++) {
                insertCell(table, name.getCellData(i).toString(), Element.ALIGN_LEFT, 1, bf12); 
                insertCell(table, amtIssued.getCellData(i).toString(), Element.ALIGN_LEFT, 1, bf12); 
                insertCell(table, monthlyDeduction.getCellData(i).toString(), Element.ALIGN_LEFT, 1, bf12); 
                insertCell(table, dateIssued.getCellData(i).toString(), Element.ALIGN_RIGHT, 1, bf12);
                insertCell(table, startDeduction.getCellData(i).toString(), Element.ALIGN_LEFT, 1, bf12); 
                insertCell(table, expDate.getCellData(i).toString(), Element.ALIGN_LEFT, 1, bf12); 
                insertCell(table, payStatus.getCellData(i).toString(), Element.ALIGN_LEFT, 1, bf12); 
                insertCell(table, expStatus.getCellData(i).toString(), Element.ALIGN_RIGHT, 1, bf12);
            }

            insertCell(table, "Total ", Element.ALIGN_RIGHT, 1, bfBold12);
            insertCell(table, String.valueOf(totIssue), Element.ALIGN_LEFT, 1, bfBold12); 
            insertCell(table, String.valueOf(totDeduction), Element.ALIGN_LEFT, 1, bfBold12); 
            insertCell(table, "", Element.ALIGN_LEFT, 1, bfBold12);
            insertCell(table, "", Element.ALIGN_LEFT, 1, bfBold12);
            insertCell(table, "", Element.ALIGN_LEFT, 1, bfBold12);
            insertCell(table, "", Element.ALIGN_LEFT, 1, bfBold12);
            insertCell(table, "", Element.ALIGN_LEFT, 1, bfBold12);
            doc.add(table);
            
            footer(doc);   // this is rgw footer of the page
            
            doc.addCreationDate();
            doc.close();
            System.out.println("finished the printing job");

            //Desktop.getDesktop().open(new File(System.getProperties().getProperty("user.home")+"root/Desktop/reciept.pdf"));
            try {
                Desktop.getDesktop().open(new File(System.getProperties().getProperty("user.home") + file));
            } catch (IOException iOException) {
                System.out.println("failed opening the file");
            }
        }catch(DocumentException | FileNotFoundException sd){
            Action showError = Dialogs.create().title("Printer Error").masthead("Errro In Printing File").message(sd.getMessage()+" : "+sd.getCause()+" Caused By "+sd.getCause()+"--"+sd.toString()).showError();
        }finally{}
        
    }
    
    public void supCust_DebtIssue(TableView supIssTab, TableColumn name, TableColumn amtOwed,
                                 long total, String title, String fileName){
        
        try{
//            Image im = Image.getInstance("src/njeiaccount/njei_img/accountcreation-icon.png");
//            im.scaleAbsolute(150f, 150f);
//            im.setAbsolutePosition(25,50);
//            im.setBackgroundColor(BaseColor.PINK);
             
             dNow = new Date();
             String app = this.datForFile.format(dNow);
             String file = "\\Documents\\NjieAB_receipts\\default"+ app + ".pdf";
             if(fileName == "SupplierDebt"){
                 file = "\\Documents\\NjieAB_receipts\\supplier\\debt "+ app + ".pdf";
             }else if(fileName == "CustumerDebt"){
                  file = "\\Documents\\NjieAB_receipts\\custumer\\debt "+ app + ".pdf";
             }
             
             System.out.println("file is " + file);
             fs = new FileOutputStream(System.getProperties().getProperty("user.home")+ file ); //this is for windows. since am developing on linux, i will use the next line. But for production version, i will enable the first optionand comment the second option as most users will be on the windows system. Or, will detect the OS and decide where to keept the files. Better idea
             //fs = new FileOutputStream(System.getProperties().getProperty("user.home")+"root/Desktop/reciept.pdf");
          
            Document doc = new Document();
                PdfWriter writer = PdfWriter.getInstance(doc, fs);
            //specify column widths    
            float[] columnWidths = {4f, 5f};
            PdfPTable table = new PdfPTable(columnWidths);
            
             //special font sizes    
            Font bfBold12 = new Font(FontFamily.TIMES_ROMAN, 12, Font.BOLD, new BaseColor(0, 0, 0));     
            Font bf12 = new Font(FontFamily.TIMES_ROMAN, 10);  
            // set table width a percentage of the page width    
            table.setWidthPercentage(90f); 

            
            
            PdfPCell cell = new PdfPCell(new Phrase("\nRECIEPT FOR " ));
            cell.setColspan(6);
            cell.setHorizontalAlignment(2);
            doc.open();
            //doc.add(im);
            
            header(doc, title);
            
            doc.add(cell);
            System.out.println(doc.leftMargin());
 
            //insert column headings    
            if(fileName == "SupplierDebt"){
                insertTableHeading(table, "Supplier Name ", Element.ALIGN_MIDDLE, 1, bfBold12);    
                insertTableHeading(table, "Total Amount Owing", Element.ALIGN_MIDDLE, 1, bfBold12);
            }else if(fileName == "CustumerDebt"){
                insertTableHeading(table, "Custumer Name ", Element.ALIGN_MIDDLE, 1, bfBold12);    
                insertTableHeading(table, "Total Amount Owing", Element.ALIGN_MIDDLE, 1, bfBold12);
            }
            table.setHeaderRows(1); 
            
            
            
            /*Now i have to fill the table  */
            
            for (int i = 0; i < supIssTab.getItems().size(); i++) {
                insertCell(table, name.getCellData(i).toString(), Element.ALIGN_LEFT, 1, bf12); 
                insertCell(table, amtOwed.getCellData(i).toString(), Element.ALIGN_LEFT, 1, bf12);
            }

            insertCell(table, "Total ", Element.ALIGN_RIGHT, 1, bfBold12);
            insertCell(table, String.valueOf(total), Element.ALIGN_LEFT, 2, bfBold12); 
            doc.add(table);
            
            footer(doc);   // this is rgw footer of the page
            
            doc.addCreationDate();
            doc.close();
            System.out.println("finished the printing job");

            try {
                Desktop.getDesktop().open(new File(System.getProperties().getProperty("user.home") + file));
            } catch (IOException iOException) {
                System.out.println("failed opening the file");
            }
        }catch(Exception sd){
            Dialogs.create().title("Printer Error").masthead("Errro In Printing File").message(sd.getMessage()+" : "+sd.getCause()+" Caused By "+sd.getCause()+"--"+sd.toString()).showError();
        }finally{}
        
    }
    
    //this method will manage staff balance salary
    public void staffBalanceSalary(TableView supIssTab, TableColumn name, TableColumn branch, 
                              TableColumn amountLeft, long total){
        String fileName = "StaffBalSalaryt";
        String title = "Staff Balance Salary";
        
        try{
//            Image im = Image.getInstance("src/njeiaccount/njei_img/accountcreation-icon.png");
//            im.scaleAbsolute(150f, 150f);
//            im.setAbsolutePosition(25,50);
//            im.setBackgroundColor(BaseColor.PINK);
             
             dNow = new Date();
             String app = this.datForFile.format(dNow);
             String file = "\\Documents\\NjieAB_receipts\\staff\\bal "+ app + ".pdf";
             
             System.out.println("file is " + file);
             fs = new FileOutputStream(System.getProperties().getProperty("user.home")+ file ); //this is for windows. since am developing on linux, i will use the next line. But for production version, i will enable the first optionand comment the second option as most users will be on the windows system. Or, will detect the OS and decide where to keept the files. Better idea
             //fs = new FileOutputStream(System.getProperties().getProperty("user.home")+"root/Desktop/reciept.pdf");
          
            Document doc = new Document();
                PdfWriter writer = PdfWriter.getInstance(doc, fs);
            //specify column widths    
            float[] columnWidths = {4f, 5f, 4f};
            PdfPTable table = new PdfPTable(columnWidths);
            
             //special font sizes    
            Font bfBold12 = new Font(FontFamily.TIMES_ROMAN, 12, Font.BOLD, new BaseColor(0, 0, 0));     
            Font bf12 = new Font(FontFamily.TIMES_ROMAN, 10);  
            // set table width a percentage of the page width    
            table.setWidthPercentage(90f); 

            
            
            PdfPCell cell = new PdfPCell(new Phrase("\nRECIEPT FOR " ));
            cell.setColspan(6);
            cell.setHorizontalAlignment(2);
            doc.open();
            //doc.add(im);
            
            header(doc, title);
            
            doc.add(cell);
            System.out.println(doc.leftMargin());
 
            //insert column headings    
            
            insertTableHeading(table, "Staff Name ", Element.ALIGN_MIDDLE, 1, bfBold12);    
            insertTableHeading(table, "Branch ", Element.ALIGN_MIDDLE, 1, bfBold12);    
            insertTableHeading(table, "Salary Left", Element.ALIGN_MIDDLE, 1, bfBold12);    
            
            table.setHeaderRows(1); 
            
            
            
            /*Now i have to fill the table  */
            
            for (int i = 0; i < supIssTab.getItems().size(); i++) {
                insertCell(table, name.getCellData(i).toString(), Element.ALIGN_LEFT, 1, bf12); 
                insertCell(table, branch.getCellData(i).toString(), Element.ALIGN_LEFT, 1, bf12); 
                insertCell(table, amountLeft.getCellData(i).toString(), Element.ALIGN_LEFT, 1, bf12); 
               
            }

            insertCell(table, "", Element.ALIGN_RIGHT, 1, bfBold12);
            insertCell(table, "Total ", Element.ALIGN_RIGHT, 1, bfBold12);
            insertCell(table, String.valueOf(total), Element.ALIGN_LEFT, 1, bfBold12); 
            doc.add(table);
            
            footer(doc);   // this is rgw footer of the page
            
            doc.addCreationDate();
            doc.close();
            System.out.println("finished the printing job");

            try {
                Desktop.getDesktop().open(new File(System.getProperties().getProperty("user.home") + file));
            } catch (IOException iOException) {
                System.out.println("failed opening the file");
            }
        }catch(Exception sd){
            Dialogs.create().title("Printer Error").masthead("Errro In Printing File").message(sd.getMessage()+" : "+sd.getCause()+" Caused By "+sd.getCause()+"--"+sd.toString()).showError();
        }finally{}
        
    }
    
    
    public void salarySuspension(TableView supIssTab, 
                                 TableColumn name, TableColumn amount, TableColumn reason, 
                                 TableColumn susDate, TableColumn status, long total){
        String title = "Salary Suspension";
        
        try{
//            Image im = Image.getInstance("src/njeiaccount/njei_img/accountcreation-icon.png");
//            im.scaleAbsolute(150f, 150f);
//            im.setAbsolutePosition(25,50);
//            im.setBackgroundColor(BaseColor.PINK);
             
             dNow = new Date();
             String app = this.datForFile.format(dNow);
             String file = "\\Documents\\NjieAB_receipts\\staff\\suspension "+ app + ".pdf";
             
             System.out.println("file is " + file);
             fs = new FileOutputStream(System.getProperties().getProperty("user.home")+ file ); //this is for windows. since am developing on linux, i will use the next line. But for production version, i will enable the first optionand comment the second option as most users will be on the windows system. Or, will detect the OS and decide where to keept the files. Better idea
             //fs = new FileOutputStream(System.getProperties().getProperty("user.home")+"root/Desktop/reciept.pdf");
          
            Document doc = new Document(PageSize.A4, 10, 10, 5, 10);
                PdfWriter writer = PdfWriter.getInstance(doc, fs);
            //specify column widths    
            float[] columnWidths = {6f, 3f, 8f, 3f, 2f};
            PdfPTable table = new PdfPTable(columnWidths);
            
             //special font sizes    
            Font bfBold12 = new Font(FontFamily.TIMES_ROMAN, 12, Font.BOLD, new BaseColor(0, 0, 0));     
            Font bf12 = new Font(FontFamily.TIMES_ROMAN, 10);  
            // set table width a percentage of the page width    
            table.setWidthPercentage(90f); 

            
            
            PdfPCell cell = new PdfPCell(new Phrase("\nRECIEPT FOR " ));
            cell.setColspan(6);
            cell.setHorizontalAlignment(2);
            doc.open();
            //doc.add(im);
            
            header(doc, title);
            
            doc.add(cell);
            System.out.println(doc.leftMargin());
 
            //insert column headings    
            
            insertTableHeading(table, "Staff Name ", Element.ALIGN_MIDDLE, 1, bfBold12);    
            insertTableHeading(table, "Amount ", Element.ALIGN_MIDDLE, 1, bfBold12);    
            insertTableHeading(table, "Reason", Element.ALIGN_MIDDLE, 1, bfBold12);  
            insertTableHeading(table, "Date ", Element.ALIGN_MIDDLE, 1, bfBold12);    
            insertTableHeading(table, "Status", Element.ALIGN_MIDDLE, 1, bfBold12);  
            
            table.setHeaderRows(1); 
            
            
            
            /*Now i have to fill the table  */
            
            for (int i = 0; i < supIssTab.getItems().size(); i++) {
                insertCell(table, name.getCellData(i).toString(), Element.ALIGN_LEFT, 1, bf12); 
                insertCell(table, amount.getCellData(i).toString(), Element.ALIGN_LEFT, 1, bf12); 
                insertCell(table, reason.getCellData(i).toString(), Element.ALIGN_LEFT, 1, bf12); 
                insertCell(table, susDate.getCellData(i).toString(), Element.ALIGN_LEFT, 1, bf12); 
                insertCell(table, status.getCellData(i).toString(), Element.ALIGN_LEFT, 1, bf12); 
               
            }

            insertCell(table, "", Element.ALIGN_RIGHT, 1, bfBold12);
            insertCell(table, "Total ", Element.ALIGN_RIGHT, 1, bfBold12);
            insertCell(table, String.valueOf(total), Element.ALIGN_LEFT, 1, bfBold12); 
            doc.add(table);
            
            footer(doc);   // this is rgw footer of the page
            
            doc.addCreationDate();
            doc.close();
            System.out.println("finished the printing job");

            try {
                Desktop.getDesktop().open(new File(System.getProperties().getProperty("user.home") + file));
            } catch (IOException iOException) {
                System.out.println("failed opening the file");
            }
        }catch(Exception sd){
            Dialogs.create().title("Printer Error").masthead("Errro In Printing File").message(sd.getMessage()+" : "+sd.getCause()+" Caused By "+sd.getCause()+"--"+sd.toString()).showError();
        }finally{}
        
    }
    
    public void staffSalaryPayments(TableView supIssTab, TableColumn name, TableColumn monthlySalary, 
                              TableColumn salaryPaid, TableColumn payDate, long total){
        String fileName = "staffPayment";
        String title = "Staff Payment History";
        
        try{
//            Image im = Image.getInstance("src/njeiaccount/njei_img/accountcreation-icon.png");
//            im.scaleAbsolute(150f, 150f);
//            im.setAbsolutePosition(25,50);
//            im.setBackgroundColor(BaseColor.PINK);
             
             dNow = new Date();
             String app = this.datForFile.format(dNow);
             String file = "\\Documents\\NjieAB_receipts\\staff\\pay "+ app + ".pdf";
             
             System.out.println("file is " + file);
             fs = new FileOutputStream(System.getProperties().getProperty("user.home")+ file ); //this is for windows. since am developing on linux, i will use the next line. But for production version, i will enable the first optionand comment the second option as most users will be on the windows system. Or, will detect the OS and decide where to keept the files. Better idea
             //fs = new FileOutputStream(System.getProperties().getProperty("user.home")+"root/Desktop/reciept.pdf");
          
            Document doc = new Document();
                PdfWriter writer = PdfWriter.getInstance(doc, fs);
            //specify column widths    
            float[] columnWidths = {6f, 4f, 4f, 4f};
            PdfPTable table = new PdfPTable(columnWidths);
            
             //special font sizes    
            Font bfBold12 = new Font(FontFamily.TIMES_ROMAN, 12, Font.BOLD, new BaseColor(0, 0, 0));     
            Font bf12 = new Font(FontFamily.TIMES_ROMAN, 10);  
            // set table width a percentage of the page width    
            table.setWidthPercentage(90f); 

            
            
            PdfPCell cell = new PdfPCell(new Phrase("\nRECIEPT FOR " ));
            cell.setColspan(6);
            cell.setHorizontalAlignment(2);
            doc.open();
            //doc.add(im);
            
            header(doc, title);
            
            doc.add(cell);
            System.out.println(doc.leftMargin());
 
            //insert column headings    
            
            insertTableHeading(table, "Staff Name ", Element.ALIGN_MIDDLE, 1, bfBold12);    
            insertTableHeading(table, "Monthly Salary ", Element.ALIGN_MIDDLE, 1, bfBold12);    
            insertTableHeading(table, "Salary Paid", Element.ALIGN_MIDDLE, 1, bfBold12);    
            insertTableHeading(table, "Payment Date", Element.ALIGN_MIDDLE, 1, bfBold12);    
            
            table.setHeaderRows(1); 
            
            
            
            /*Now i have to fill the table  */
            
            for (int i = 0; i < supIssTab.getItems().size(); i++) {
                insertCell(table, name.getCellData(i).toString(), Element.ALIGN_LEFT, 1, bf12); 
                insertCell(table, monthlySalary.getCellData(i).toString(), Element.ALIGN_LEFT, 1, bf12); 
                insertCell(table, salaryPaid.getCellData(i).toString(), Element.ALIGN_LEFT, 1, bf12); 
                insertCell(table, payDate.getCellData(i).toString(), Element.ALIGN_LEFT, 1, bf12); 
               
            }

            insertCell(table, "", Element.ALIGN_RIGHT, 1, bfBold12);
            insertCell(table, "Total ", Element.ALIGN_RIGHT, 1, bfBold12);
            insertCell(table, String.valueOf(total), Element.ALIGN_LEFT, 1, bfBold12); 
            doc.add(table);
            
            footer(doc);   // this is rgw footer of the page
            
            doc.addCreationDate();
            doc.close();
            System.out.println("finished the printing job");

            try {
                Desktop.getDesktop().open(new File(System.getProperties().getProperty("user.home") + file));
            } catch (IOException iOException) {
                System.out.println("failed opening the file");
            }
        }catch(Exception sd){
            Dialogs.create().title("Printer Error").masthead("Errro In Printing File").message(sd.getMessage()+" : "+sd.getCause()+" Caused By "+sd.getCause()+"--"+sd.toString()).showError();
        }finally{}
        
    }
    
    
    
    public void supCust_PayDebt(TableView supPayTab, TableColumn name, TableColumn debtPayment, 
                              TableColumn paymtDate, long total, String title, String fileName){
        
        try{
//            Image im = Image.getInstance("src/njeiaccount/njei_img/accountcreation-icon.png");
//            im.scaleAbsolute(150f, 150f);
//            im.setAbsolutePosition(25,50);
//            im.setBackgroundColor(BaseColor.PINK);
             
             dNow = new Date();
             String app = this.datForFile.format(dNow);
              
              String file = "\\Documents\\NjieAB_receipts\\default"+ app + ".pdf";
             if(fileName == "SupplierDebtPayment"){
                 file = "\\Documents\\NjieAB_receipts\\supplier\\pay "+ app + ".pdf";
             }else if(fileName == "CustumerDebtPayment"){
                  file = "\\Documents\\NjieAB_receipts\\custumer\\pay "+ app + ".pdf";
             }else if(fileName == "StaffBalSalaryt"){
                  file = "\\Documents\\NjieAB_receipts\\staff\\balance "+ app + ".pdf";
             } 
             
             System.out.println("file is " + file);
             fs = new FileOutputStream(System.getProperties().getProperty("user.home")+ file ); //this is for windows. since am developing on linux, i will use the next line. But for production version, i will enable the first optionand comment the second option as most users will be on the windows system. Or, will detect the OS and decide where to keept the files. Better idea
             //fs = new FileOutputStream(System.getProperties().getProperty("user.home")+"root/Desktop/reciept.pdf");
          
            Document doc = new Document();
                PdfWriter writer = PdfWriter.getInstance(doc, fs);
            //specify column widths    
            float[] columnWidths = {4f, 5f, 3f};
            PdfPTable table = new PdfPTable(columnWidths);
            
             //special font sizes    
            Font bfBold12 = new Font(FontFamily.TIMES_ROMAN, 12, Font.BOLD, new BaseColor(0, 0, 0));     
            Font bf12 = new Font(FontFamily.TIMES_ROMAN, 12);  
            // set table width a percentage of the page width    
            table.setWidthPercentage(90f); 

            
            
            PdfPCell cell = new PdfPCell(new Phrase("\nRECIEPT FOR " ));
            cell.setColspan(6);
            cell.setHorizontalAlignment(2);
            doc.open();
            //doc.add(im);
            
            header(doc, title);
            
            doc.add(cell);
            System.out.println(doc.leftMargin());
 
            //insert column headings
            if(fileName == "SupplierDebtPayment"){
                insertTableHeading(table, "Supplier Name ", Element.ALIGN_MIDDLE, 1, bfBold12);    
                insertTableHeading(table, "Amount Owing", Element.ALIGN_MIDDLE, 1, bfBold12);    
                insertTableHeading(table, "Date Issued", Element.ALIGN_MIDDLE, 1, bfBold12);    
            }else if( fileName == "CustumerDebtPayment"){
                insertTableHeading(table, "Custumer Name ", Element.ALIGN_MIDDLE, 1, bfBold12);    
                insertTableHeading(table, "Amount Owing", Element.ALIGN_MIDDLE, 1, bfBold12);    
                insertTableHeading(table, "Date Issued", Element.ALIGN_MIDDLE, 1, bfBold12); 
            }else if(fileName == "StaffBalSalaryt"){
                insertTableHeading(table, "Staff Name ", Element.ALIGN_MIDDLE, 1, bfBold12);    
                insertTableHeading(table, "Branch", Element.ALIGN_MIDDLE, 1, bfBold12);    
                insertTableHeading(table, "Balance Amount", Element.ALIGN_MIDDLE, 1, bfBold12); 
            }
            table.setHeaderRows(1); 
            
            
            
            /*Now i have to fill the table  */
            
            for (int i = 0; i < supPayTab.getItems().size(); i++) {
                insertCell(table, name.getCellData(i).toString(), Element.ALIGN_LEFT, 1, bf12); 
                insertCell(table, debtPayment.getCellData(i).toString(), Element.ALIGN_LEFT, 1, bf12); 
                insertCell(table, paymtDate.getCellData(i).toString(), Element.ALIGN_LEFT, 1, bf12);
            }

            insertCell(table, "Total ", Element.ALIGN_RIGHT, 1, bfBold12);
            insertCell(table, String.valueOf(total), Element.ALIGN_LEFT, 2, bfBold12); 
            insertCell(table, "", Element.ALIGN_RIGHT, 3, bfBold12);
            doc.add(table);
            
            footer(doc);   // this is rgw footer of the page
            
            doc.addCreationDate();
            doc.close();
            System.out.println("finished the printing job");

            //Desktop.getDesktop().open(new File(System.getProperties().getProperty("user.home")+"root/Desktop/reciept.pdf"));
            try {
                Desktop.getDesktop().open(new File(System.getProperties().getProperty("user.home") + file));
            } catch (IOException iOException) {
                System.out.println("failed opening the file");
            }
        }catch(Exception sd){
            Dialogs.create().title("Printer Error").masthead("Errro In Printing File").message(sd.getMessage()+" : "+sd.getCause()+" Caused By "+sd.getCause()+"--"+sd.toString()).showError();
        }finally{}
        
    }
    ////////////////////////////////////////////////////
    public void header(Document doc, String reportType) throws DocumentException{
       String companyInfo = "NJEIFORBI ACCOUNTS BOOK \nFINANCIAL REPORTS";
        
        Paragraph newLine = new Paragraph("\n");        doc.add(newLine); 
        
        // add the paragraph to the document            
        
        /*new heading*/
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
        
        Paragraph companyName = new Paragraph(companyInfo, this.companyNameFont);
        Paragraph reportTitle = new Paragraph(reportType, this.reportTitleFont);
        
        companyName.setAlignment(Element.ALIGN_CENTER);
        reportTitle.setAlignment(Element.ALIGN_CENTER);
        newLine.setAlignment(Element.ALIGN_CENTER);
        
        doc.add(companyName);
        doc.add(reportTitle);
        doc.add(newLine);
        
        /*new heading ends*/
        
        
    }
    
    public void footer(Document doc) throws DocumentException{
        Paragraph paragraph = new Paragraph("Njieforbi Bakery,   Cameroon Buea");    
        doc.add(paragraph);
        dNow = new Date();

        String prodDate = "Created on the " + datOnly.format(dNow) + " at " + timOnly.format(dNow);
       
        
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
         
         Dialogs.create().title("Printing Complete")
                 .masthead("Printing process complete.")
                 .message("Patient while the file open to see your printout.")
                 .showInformation();
         
        
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
    
    /**** small receipts. *****/
    
    public void smlRecPayStaff(Pane outPane) throws FileNotFoundException, DocumentException{
        
//        dNow = new Date();
//             String app = this.datForFile.format(dNow);
//             String file = "\\Documents\\NjieAB_receipts\\smallReceipts\\testRec"+ app + ".pdf";
//             
//        
//        System.out.println("file is " + file);
//             fs = new FileOutputStream(System.getProperties().getProperty("user.home")+ file ); //this is for windows. since am developing on linux, i will use the next line. But for production version, i will enable the first optionand comment the second option as most users will be on the windows system. Or, will detect the OS and decide where to keept the files. Better idea
//
//        Document doc = new Document(  PageSize.A5, (float)3.2, (float)3.2, (float)3.2, (float)3.2  );
//        PdfWriter writer = PdfWriter.getInstance(doc, fs);
//        Dialogs.create().message("trying to print small receipt").showInformation();
//        header(doc, "Test Small Receipts");
//        
//        Paragraph pane = new Paragraph("hello");
//        boolean add = pane.add((Element) outPane);
//        
//        
//        footer(doc);
//        doc.close();
        
    }
    
    public void smlRecPaySupp(){
        
    }
    
    public void smlRecCustPay(){
        
    }
    
    public void smlRecOverdraftPay(){
        
    }
    
    /***** small receipts end. ****/
    
    
}
