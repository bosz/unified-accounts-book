/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Reports;

import Tables.SimpleString;
import Tables.TabClass;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import njieaccount.ComboItem;
import njieaccount.DatabaseHelper;
import njieaccount.UsefullStuff;
import org.controlsfx.control.action.Action;
import org.controlsfx.dialog.Dialogs;

/**
 *
 * @author root
 */
public class StaffReports {
    
    TabClass tab;
    DatabaseHelper db,dbase,base;
    UsefullStuff usefulstuff;
    public String popStaff_Q;
    public String Qstaff_salaryAndName;
    public String Qpay_totAmtPaid;
    public String qGetOverdraftAmtIfExists;
    public String testString, holdTestString;
    int staffId = 0, overdraftDeductionPerMonth = 0;

    
    //holding the database names
    
     String sTab = "njieDB.STAFF";
    String oTab = "njieDB.OVERDRAFT";
    String pTab = "njieDB.PAYROLL";
    String mTab = "njieDB.MONTHLY_SALARY_PAYMENT";
    public int id, idTrack = 0;

    /***********88888888888888888************/
    public String qGetTotaUnpaidSalary_and_totalSalary, qEndWithStaffId, qBranch;
    SimpleDateFormat dateOnly, endSalaryDate;
    Date dNow;
    /***********88888888888888888************/
    
    public StaffReports() throws SQLException, Exception{
        tab = new TabClass();
        db = new DatabaseHelper();
        dbase = new DatabaseHelper();
        base = new DatabaseHelper();
        usefulstuff = new UsefullStuff();

        /*FIXING FOTSO'S CODE ***************888888888888888888888888*******/
       
        dateOnly = new SimpleDateFormat("yyyy-MM-dd");
        endSalaryDate = new SimpleDateFormat("yyyy-MM-20");
        qEndWithStaffId = "";
        qBranch = "";

        //staffid, name, branch, total(salary), total(paid)
        qGetTotaUnpaidSalary_and_totalSalary = "SELECT SUM(njieDB.MONTHLY_SALARY_PAYMENT.STAFF_ID), "
                + " SUM(njieDB.MONTHLY_SALARY_PAYMENT.SALARY), "
                + " SUM(njieDB.MONTHLY_SALARY_PAYMENT.TOTAL_PAID) FROM "
                + " njieDB.MONTHLY_SALARY_PAYMENT, njieDB.STAFF WHERE "
                + " njieDB.MONTHLY_SALARY_PAYMENT.SALARY != njieDB.MONTHLY_SALARY_PAYMENT.TOTAL_PAID "
                + " AND njieDB.MONTHLY_SALARY_PAYMENT.STAFF_ID = njieDB.STAFF.STAFF_ID ";

        

        /**88888888888888888888888888888**********************/



         popStaff_Q = "SELECT " + sTab + ".STAFF_ID, " + sTab + ".NAME, " + sTab + ".SALARY, " + pTab + ".AMOUNT_PAID, " + pTab + ".PAY_DATE " 
              // + oTab + ".AMOUNT, " + oTab + ".DEDUCTION_PER_MONTH, " + oTab + ".DEDUCT_UNTIL FROM "
              //  + oTab + ", " 
                + " FROM " + sTab + ", " + pTab + " WHERE "
                //+ " WHERE " + sTab + ".STAFF_ID = " + oTab + ".STAFF_ID AND "
                + sTab+ ".STAFF_ID = " + pTab + ".STAFF_ID";
               // + " AND  (" +pTab + ".PAY_DATE BETWEEN " +oTab + ".DATE AND " + oTab + ".DEDUCT_UNTIL )" ;
         
         holdTestString = "SELECT " + sTab + ".STAFF_ID, " + sTab + ".NAME, " + sTab + ".SALARY, " + pTab + ".AMOUNT_PAID, " + pTab + ".PAY_DATE " 
                + " FROM " + sTab + ", " + pTab + " WHERE "
                + sTab+ ".STAFF_ID = " + pTab + ".STAFF_ID";
         
          Qstaff_salaryAndName = "SELECT njieDB.STAFF.SALARY, njieDB.STAFF.NAME, njieDB.STAFF.EMP_DATE,"
                +                     " njieDB.STAFF.STAFF_ID  , njieDB.STAFF.BRANCH FROM njieDB.STAFF ";
          testString = "SELECT njieDB.STAFF.SALARY, njieDB.STAFF.NAME, njieDB.STAFF.EMP_DATE,"
                +                     " njieDB.STAFF.STAFF_ID  , njieDB.STAFF.BRANCH FROM njieDB.STAFF ";
    }
    
   
    
    public void displayStaffPayrollReports(TableView tabId, TableColumn c1, TableColumn c2, 
                                            TableColumn c3, TableColumn c4, TableColumn c5) throws SQLException{
        
        
//        String sTab = "njieDB.STAFF";
//        String oTab = "njieDB.OVERDRAFT";
//        String pTab = "njieDB.PAYROLL";
        
       // this is twhere is goes!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        
        System.out.println("Query is \n" + popStaff_Q);
        
        
        /*
        
        SELECT njieDB.STAFF.STAFF_ID, njieDB.STAFF.SALARY, njieDB.PAYROLL.AMOUNT_PAID, 
        njieDB.PAYROLL.PAY_DATE, njieDB.OVERDRAFT, njieDB.STAFF, njieDB.PAYROLL 
        WHERE njieDB.STAFF.STAFF_ID = njieDB.OVERDRAFT.STAFF_ID AND 
        njieDB.STAFF.STAFF_ID = njieDB.PAYROLL.STAFF_ID AND   
        (njieDB.PAYROLL.PAY_DATE BETWEEN njieDB.OVERDRAFT.DATE AND njieDB.OVERDRAFT.DEDUCT_UNTIL )
        
        */
        
        tab.makeTable(popStaff_Q, c1, c2, c3, c4, c5, tabId);
        popStaff_Q = holdTestString;
            /*------------------------------------------------------DONE BY FOTSO FONO KEVIN LARRY-----------------------------*/
        /* USED TO STORE THE ID FILTERED ON THE STAFF TABLE TO FILTER THE AMOUNT LEFT TO BE PAID TABLE */
        dbase.setQuery(popStaff_Q);
        if(dbase.getRowCount() > 0)
            for(int i=0; i<dbase.getRowCount(); i++){
                id = Integer.parseInt(dbase.getValueAt(0, 0).toString());
                System.out.println("-----------------------IT ENTERED HERE------------------------- " + id);
            }
        /* ENDS HERE */
        
    }
    ObservableList<SimpleString> composeData;
    long totalSalaryUnpaid, totalSalaryPaid;
    
    
    
    /*this method has a problem as it takes only the first staff and displays his.her balance payment for the month */
    
    
    public long displayBalancePayroll(TableView tableId, TableColumn c1, TableColumn c2, TableColumn c3) throws ParseException, SQLException{
        System.out.println("----------\n--------------\n--------------\n");
        //her goes the ballance calculator. seems tough, bt not impossoble with God. Yea
        System.out.println("BEGINNING THE BALANCE TABLE GENERATION");
        dNow = new Date();
        //1. get the staff name, branch and the tot(sal) and tot(paid)
        System.out.println("Getting the staff information \n " 
            + qGetTotaUnpaidSalary_and_totalSalary 
         //   + qBranch
            + qEndWithStaffId  
            + " AND njieDB.MONTHLY_SALARY_PAYMENT.DAT <= '" + endSalaryDate.format(dNow) + "'"
            + " group by njieDB.MONTHLY_SALARY_PAYMENT.staff_id  ");

        try {
            db.setQuery(qGetTotaUnpaidSalary_and_totalSalary 
         //   + qBranch
            + qEndWithStaffId  
            + " AND njieDB.MONTHLY_SALARY_PAYMENT.DAT <= '" + endSalaryDate.format(dNow) + "'"
            + " group by njieDB.MONTHLY_SALARY_PAYMENT.staff_id  ");
        } catch (SQLException | IllegalStateException sQLException) {
            Dialogs.create().message("Failed to Retrieve full staff balance financial record.").showError();
            sQLException.printStackTrace();
            return 0;
        }

        /************************88888888888888888888888************/
        int staffId;
        String kstaffName = null, branch = null, qGetStaffNameAndBranch;
        long totalSalaryUnpaid = (long)0, possiblePayableAmount = (long)0; 
        String todayDate = dateOnly.format(dNow);
        /************************88888888888888888888888************/
        
        c1.setCellValueFactory(new PropertyValueFactory<>("col1"));
        c2.setCellValueFactory(new PropertyValueFactory<>("col2"));
        c3.setCellValueFactory(new PropertyValueFactory<>("col3"));
            
        composeData = FXCollections.observableArrayList();
        tableId.setItems(composeData);
        
        for(int i = 0; i < db.getRowCount(); i++){
            SimpleString trans = new SimpleString();
            //staffid, name, branch, total(salary), total(paid)
            staffId = Integer.parseInt(db.getValueAt(i, 0).toString());
            possiblePayableAmount = Long.parseLong(db.getValueAt(i, 1).toString()) 
                                - Long.parseLong(db.getValueAt(i, 2).toString());
            System.out.println("Information is \n " +
                staffId + " -- " + kstaffName + " -- " + 
                branch + " -- " + possiblePayableAmount + "\n");

            //compose thd overdraft query 
            qGetOverdraftAmtIfExists = "SELECT njieDB.OVERDRAFT.DEDUCTION_PER_MONTH FROM njieDB.OVERDRAFT " 
                    + " WHERE njieDB.OVERDRAFT.EXP_STATUS = " + false + " AND "
                    + " njieDB.OVERDRAFT.DEDUCTION_START_DATE <= '" + todayDate + "'"
                    + " AND njieDB.OVERDRAFT.STAFF_ID = ";


            //execute the overdraft query
            System.out.println("getting the overdraft amount if possible \n" + qGetOverdraftAmtIfExists + staffId);
            dbase.setQuery(qGetOverdraftAmtIfExists + staffId);

            if(dbase.getRowCount() > 0){ //means that staff has an overdraft. 
                //so we deduct that overdraft from the staff's total possiblePayableAmount
                possiblePayableAmount -= Long.parseLong(dbase.getValueAt(0, 0).toString());
            }

            /*******-----------------compose query to get staffName and Branch */
            qGetStaffNameAndBranch = "select njieDB.STAFF.NAME, njieDB.STAFF.BRANCH "
                                      + " FROM njieDB.STAFF WHERE njieDB.STAFF.STAFF_ID = " + staffId;
            System.out.println("\nGetting the staffname and branch \n" + qGetStaffNameAndBranch);
            dbase.setQuery(qGetStaffNameAndBranch);
            kstaffName = dbase.getValueAt(0, 0).toString();
            branch = dbase.getValueAt(0, 1).toString();
            System.out.println("staff name = " + kstaffName + "\n");
            System.out.println("Branch = " + branch);
            
            possiblePayableAmount -= usefulstuff.getSuspensionForStaff(staffId);
            /****************-------------------------------------------------*/

            trans.col1.setValue(kstaffName);
            trans.col2.setValue(branch);
            trans.col3.setValue(String.valueOf(possiblePayableAmount));

            composeData.add(trans);
                    
            this.totalSalaryUnpaid += possiblePayableAmount;
        }
        
        qEndWithStaffId = "";
        qBranch = "";
        System.out.println("----------\n-------end of staff balance display-------\n--------------\n");
        return this.totalSalaryUnpaid;
       
    }
    
    
    public void displayStaffPayrollReportsFilteredById(){
        
    }
    
    public void displayStaffPayrollReportsFilteredByDate(){
        
    }
    
    public void displayStaffBranchCombo(ComboBox elt_id){
        //combo box for branch of staff. its statis for the time being.
        elt_id.setEditable(false);
        elt_id.getItems().clear();
        elt_id.getItems().add(new ComboItem("BONGO SQUARE", "bongo_square"));
        elt_id.getItems().add(new ComboItem("MOLYKO SHOW ROOM", "molyko_show_room"));
        elt_id.getItems().add(new ComboItem("MILE 17", "mile_17"));
        elt_id.getItems().add(new ComboItem("TIKO BRANCH", "tiko"));
        elt_id.getItems().add(new ComboItem("BAMENDA", "bamenda"));
        elt_id.getItems().add(new ComboItem("PRODUCTION CENTER", "production_center"));
        elt_id.getItems().add(new ComboItem("LAS VEGAS", "las_vegas"));
        elt_id.getItems().add(new ComboItem("NEW JERUSALEM", "new_jerusalem"));
        elt_id.getItems().add(new ComboItem("OFFICE", "office"));
        //elt_id.getSelectionModel().selectFirst();
    }
    
}

/*
"CREATE TABLE njieDB.STAFF ("
                    + "STAFF_ID INTEGER NOT NULL,"
                    + "NAME VARCHAR(30),"
                    + "PHONE_NUMBER BIGINT,"
                    + "SALARY BIGINT, "
                    + "BRANCH VARCHAR(10), "
                    + "EMP_DATE DATE NOT NULL, "
                    + "PRIMARY KEY (STAFF_ID)"
                    + ")";
EATE TABLE njieDB.OVERDRAFT ("
                    + "OV_ID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY "
                    + "(START WITH 1, INCREMENT BY 1), "
                    + "STAFF_ID INTEGER,"
                    + "AMOUNT INTEGER,"
                    + "DATE DATE,"
                    + "TIM_ISSUED TIME, "
                    + "DEDUCTION_PER_MONTH INTEGER,"
                    + "EXP_STATUS BOOLEAN DEFAULT false, "
                    + "DEDUCT_UNTIL DATE, "
                    + "PAY_STATUS BOOLEAN DEFAULT false , "
                    + "PRIMARY KEY (OV_ID)"
                    + ")";
"CREATE TABLE njieDB.PAYROLL ("
                    + " PAYMT_ID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY "
                    + " (START WITH 1, INCREMENT BY 1), "
                    + "	STAFF_ID INTEGER,"
                    + "	AMOUNT_PAID BIGINT,"
                    + "	PAY_DATE DATE,"
                    + " EXP_ID INTEGER, "//THIS IS FOREIGN KEY FROM EXPENSES TABLE
                    + " PRIMARY KEY( PAYMT_ID )"
                    + ")";


*/


