/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package njieaccount;

import Reports.*;
import Tables.Printer;
import Tables.PrinterClass;
import Tables.SimpleString;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.DocumentException;
import framework.ControlledScreen;
import framework.ScreensController;
import java.io.IOException;
import static java.lang.System.exit;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Formatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import org.controlsfx.control.action.Action;
import org.controlsfx.dialog.Dialog;
import org.controlsfx.dialog.Dialogs;

/**
 * FXML Controller class
 *
 * @author root
 */
public class ReportsController implements Initializable, ControlledScreen {
    
    @FXML
    private DatePicker rep_genEndtDateSearch;
    @FXML
    private DatePicker rep_genStartDateSearch;
    @FXML
    private TableView<SimpleString> report_genTrans_table;
    private TableColumn<SimpleString, String> report_GT_code;
    
    @FXML
    private TableColumn<SimpleString, String> report_GT_desc;
    @FXML
    private TableColumn<SimpleString, String> report_GT_totamt;
    @FXML
    private TableColumn<SimpleString, String> report_GT_time;
    @FXML
    private Label repGTIncExp;
    @FXML
    private DatePicker rep_staffStartDateSearch;
    @FXML
    private DatePicker rep_staffEndDateSearch;
    @FXML
    private ComboBox<ComboItem> rep_staffstaffIdSearch;
    @FXML
    private DatePicker rep_custStartDateSearch;
    @FXML
    private DatePicker rep_custEndDateSearch;
    @FXML
    private ComboBox<ComboItem> rep_custCustIdSearch;
    @FXML
    private TableView<SimpleString> CustumerFinancialRecprdTable;
    @FXML
    private DatePicker rep_supStartDateSearch;
    @FXML
    private DatePicker rep_supEndDateSearch;
    @FXML
    private ComboBox<ComboItem> rep_supIdSearch;
    @FXML
    private TableView<SimpleString> SupplierFinancialRecprdTable;

        
        
    
     CustumerReports custR;
     StaffReports staffR,staffReportDefault,staff;
     SupplierReports supR;
     StaffOverdraft overdR;
     GenTransReports gentransR;
     Checks check;
     DatabaseHelper db,dba;
     UsefullStuff stuff;
     String val;
     Printer print;
     
    @FXML
    private Label genReportRangeDisplay;
    Formatter st;
    Formatter en;
    @FXML
    private TableView<SimpleString> report_staff_table;
    @FXML
    private TableColumn<SimpleString, String> reports_ST_StaffID;
    @FXML
    private TableColumn<SimpleString, String> reports_ST_monthlySalary;
    @FXML
    private TableColumn<SimpleString, String> reports_ST_salaryPaid;
    @FXML
    private TableColumn<SimpleString, String> reports_ST_salaryPayDate;
//    private TableColumn<SimpleString, String> reports_ST_overdraftAmt;
//    private TableColumn<SimpleString, String> reports_ST_overdraftMontlyDeduction;
//    private TableColumn<SimpleString, String> reports_ST_overdraftEndDate;
    @FXML
    private TableColumn<SimpleString, String> reports_ST_name;
    @FXML
    private TableColumn<SimpleString, String> reports_OV_name;
    @FXML
    private TableColumn<SimpleString, String> reports_OV_amt;
    @FXML
    private TableColumn<SimpleString, String> reports_OV_monthlyDeduction;
    @FXML
    private TableColumn<SimpleString, String> reports_OV_dateIssued;
    @FXML
    private TableColumn<SimpleString, String> reports_OV_ExpiryDate;
    @FXML
    private TableColumn<SimpleString, String> reports_OV_payStatus;
    @FXML
    private TableColumn<SimpleString, String> reports_OV_ExpStatus;
    @FXML
    private TableView<SimpleString> report_staffOverdraft_table;
    @FXML
    private TableColumn<SimpleString, String> reports_CU_name;
    @FXML
    private TableColumn<SimpleString, String> reports_CU_debtAmt;
    @FXML
    private TableColumn<SimpleString, String> reports_SU_supName;
    @FXML
    private TableColumn<SimpleString, String> reports_SU_amtOwed;
    private TableColumn<SimpleString, String> reports_SU_dateIssued;
    @FXML
    private TableColumn<SimpleString, String> reports_SU_debtPayment;
    @FXML
    private TableColumn<SimpleString, String> reports_SU_debtPaymentDate;
    private TableColumn<SimpleString, String> reports_SU_status;
    
    SimpleDateFormat formatter;
    Date dNow;
    @FXML
    private TableView<SimpleString> supplierDebtPaymentTable;
    @FXML
    private TableColumn<SimpleString, String> supCode_forPayment_col;
    @FXML
    private Label totIncExp;
    @FXML
    private Label IncExpbalance;
    @FXML
    private TableColumn<SimpleString, String> reports_OV_monthlyDeduction1;
    @FXML
    private TableView<SimpleString> staffBalSalaryTable;
    @FXML
    private TableColumn<SimpleString, String> staffBalSalary_Name_col;
    @FXML
    private TableColumn<SimpleString, String> staffBalSalary_Branch_col;
    @FXML
    private TableColumn<SimpleString, String> staffBalSalary_balSal_col;
    @FXML
    private Label totalUnpaidSalary;
    @FXML
    private TableView<SimpleString> repCustPayTable;
    @FXML
    private TableColumn<SimpleString, String> repCustPay_name_col;
    @FXML
    private TableColumn<SimpleString, String> repCustPay_debtPayment_col;
    @FXML
    private TableColumn<SimpleString, String> repCustPay_paymentDate_col;
    @FXML
    private DatePicker OverDStartDateFilter;
    @FXML
    private DatePicker OverDEndDateFilter;
    @FXML
    private ComboBox<?> OverDstaffNameFilter;
    @FXML
    private Button btnfilterGeneralTransactionTable;
    @FXML
    private TableColumn<?, ?> report_GT_id_Column;
    @FXML
    private TableColumn<?, ?> report_GT_Name;
    @FXML
    private ComboBox<ComboItem> transactionNameComboBox;
    @FXML
    private Pane editDescriptionAndAmountOnGeneralTransactionTablePane;
    @FXML
    private Button btnSaveUpdateGeneralTransactionEditInformation;
    @FXML
    private TextField generalTransactionDescriptionEditTextField;
    @FXML
    private TextField generalTransactionAmountEditableTextField;
    @FXML
    private Button btnCancleEditGeneralTransactionEditTextField;
    @FXML
    private Label lblTransNameSelectFromTable;
    @FXML
    private Button btneditDescriptionAndAmountOnGeneralTransactionTablePane;
     
    @FXML
    private ComboBox<ComboItem> staffBranchForFiltering;
    @FXML
    private Label totalOverdraftAmount;
    @FXML
    private Label totalMonthlyDeduction;
    @FXML
    private Label totalCustumerDebt;
    @FXML
    private Label totalCustumerDebtPayment;
    @FXML
    private Label totalSupplierAmoutOwing;
    @FXML
    private Label totalSupplierAmountPayment;
    @FXML
    private Label totalMonthlySalary;
    @FXML
    private Label totalSalaryPaid;
    @FXML
    private TableColumn<?, ?> reports_OV_startDate;
    
    ArrayList<String> ComboValue;
    ArrayList<String> ComboKey;
    
    ArrayList<String> issueId;
    ArrayList<String> issuedAmt;
    @FXML
    private Pane SpecialEditDescriptionAndAmountOnGeneralTransactionTablePane;
    @FXML
    private Button btnSaveSpecialUpdateGeneralTransactionEditInformation;
    @FXML
    private TextField specialTransEditDescInput;
    @FXML
    private TextField specialTransEditAmtInput;
    @FXML
    private Button btnCancleSpecialEditGeneralTransactionEditTextField;
    @FXML
    private Label specialTransEditTransClassInput;
    @FXML
    private ComboBox<ComboItem> specialEditTrans_idCmbxInput;
    @FXML
    private Label specialEditTransCodeLabelForInputCombo;
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        rep_supStartDateSearch.setEditable(false);
//        rep_supEndDateSearch.setEditable(false);
        rep_supStartDateSearch.setShowWeekNumbers(true);
        formatter = new SimpleDateFormat("yyyy-MM-dd");
      
        ComboValue = new ArrayList<>();
        ComboKey = new ArrayList<>();
        issueId = new ArrayList<>();
        issuedAmt = new ArrayList<>();
        
        
        print = new Printer();
        
//        rep_supStartDateSearch.set
        
        try {
            db = new DatabaseHelper();
            custR = new CustumerReports();
            staffR = new StaffReports();
            supR = new SupplierReports();
            overdR = new StaffOverdraft();
            st = new Formatter();
            en = new Formatter();
            check = new Checks();
            check.expIncBalance();
            gentransR = new GenTransReports();
            staffReportDefault = new StaffReports();
            staffR.displayStaffBranchCombo(staffBranchForFiltering);
            staff = new StaffReports();
            dba = new DatabaseHelper();
            stuff = new UsefullStuff();
        } catch (SQLException ex) {
            Logger.getLogger(ReportsController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ReportsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
      // rep_supIdSearch.setEditable(false);
        loadTransactionNameCombo();

    }    

    
     /*******GENERAL TRANSACTION REPORTING 
     * @param generating and displaying all the transactions under the income and expenses class
     * @throws SQLException 
     */
//    @FXML
//    private void generalReportFilter(MouseEvent event) throws SQLException, ParseException {
//        
//        String startDate = formatter.format(formatter.parse(this.rep_genStartDateSearch.getValue().toString()));
//        String endDate = formatter.format(formatter.parse(this.rep_genEndtDateSearch.getValue().toString()));
//        
//        rep_genStartDateSearch.getEditor().clear();
//        rep_genEndtDateSearch.getEditor().clear();
//        
//        
//        rep_genEndtDateSearch.setValue(LocalDate.now());
//        rep_genEndtDateSearch.setDayCellFactory(null);
//        System.out.println("start = " + startDate + "\nend = " + endDate);
//        if(startDate == null || endDate == null){
//            Dialogs.create().title("error").message("fill the datePickers before searching by date").showInformation();
//            return ;
//        }
//        String ret = gentransR.populateFilteTableWithTransactionsByDate(report_genTrans_table, report_GT_code,  report_GT_desc, report_GT_totamt, report_GT_time, startDate, endDate);
//        genReportRangeDisplay.setText(ret + " for " + startDate + " - " + endDate);
//    }

   
    @FXML
    private void manViewExpTrans(MouseEvent event) throws SQLException {
        boolean ret = gentransR.populateTableWithExpenseTransactions(report_genTrans_table, report_GT_Name,  report_GT_desc, report_GT_totamt, report_GT_time, report_GT_id_Column);
        if(ret){
            repGTIncExp.setText("EXPENSES");
            totIncExp.setText(String.valueOf(check.totExp));
            IncExpbalance.setText(String.valueOf(check.incExpBal));
        }
        genReportRangeDisplay.setText("");
        loadTransactionNameCombo();
    }

    @FXML
    private void manViewIncTrans(MouseEvent event) throws SQLException {
        boolean ret = gentransR.populateTableWithIncomeTransactions(report_genTrans_table, report_GT_Name,  report_GT_desc, report_GT_totamt, report_GT_time, report_GT_id_Column);
        if(ret){
            check.expIncBalance();
            repGTIncExp.setText("INCOME");
            totIncExp.setText(String.valueOf(check.totInc));
            IncExpbalance.setText(String.valueOf(check.incExpBal));
        }
        genReportRangeDisplay.setText("");
        
        loadTransactionNameCombo();
        
    }
    
    @FXML
    private void report_genTrans(Event event) throws SQLException {
        //somethins is not right with this guy. when i uncomment the commented line, there is a null pointer exception at the start of the application thereby causing the app to stop working.;
        
            boolean ret = false;
            //ret = gentransR.populateTableWithIncomeTransactions(report_genTrans_table, report_GT_code, report_GT_name, report_GT_desc, report_GT_totamt, report_GT_time);
            if (ret) {
                check.expIncBalance();
                repGTIncExp.setText("INCOME");
                /*      FILL THE TOTAL INCOME AND THE BALANCE   */
                totIncExp.setText(String.valueOf(check.totInc));
                IncExpbalance.setText(String.valueOf(check.incExpBal));
                
            }
            genReportRangeDisplay.setText("");
      
        
    }
    
    
    /******8as
     * asdkf
     * 
     * @param event 
     */
    @FXML
    private void StaffOverdraftFilter(MouseEvent event) throws SQLException {
        
        
        String startDate=null , endDate=null;
        int staffCode = 0;
        try {
            startDate = formatter.format(formatter.parse(this.OverDStartDateFilter.getValue().toString()));
        } catch (Exception e) {}
        try {
            endDate = formatter.format(formatter.parse(this.OverDEndDateFilter.getValue().toString()));
        } catch (Exception e) {}
        
        OverDStartDateFilter.getEditor().clear();        OverDStartDateFilter.setValue(null);
        OverDEndDateFilter.getEditor().clear();          OverDEndDateFilter.setValue(null);
        
        if(OverDstaffNameFilter.getSelectionModel().getSelectedIndex()!=-1){
            String cCode =  this.OverDstaffNameFilter.getEditor().getText();
            staffCode = Integer.parseInt(getKey(cCode));
            
            //String value = (String) this.OverDstaffNameFilter.getValue();
        }
        
        //CHECK IF THE GETKEY RETURNED A NULL
        
        
        //now  doing the testing to see category to filter
        //      3   seected
        if(staffCode != 0 && startDate != null && endDate != null){
            overdR.displayFilteredStaffOverdraftReport(staffCode, startDate, endDate, report_staffOverdraft_table,  reports_OV_name, 
                                            reports_OV_amt, reports_OV_monthlyDeduction, reports_OV_dateIssued, reports_OV_startDate,
                                            reports_OV_ExpiryDate, reports_OV_payStatus, reports_OV_ExpStatus);
            loadStaffCodeCombox(OverDstaffNameFilter);
        }
        /*      2 selected*/
        else if(startDate != null && endDate != null){
            
            overdR.displayFilteredStaffOverdraftReport(startDate, endDate, 3, report_staffOverdraft_table,  reports_OV_name, 
                                            reports_OV_amt, reports_OV_monthlyDeduction, reports_OV_dateIssued, reports_OV_startDate,
                                            reports_OV_ExpiryDate, reports_OV_payStatus, reports_OV_ExpStatus);
            
        }else if(staffCode != 0 && endDate != null){
            
            overdR.displayFilteredStaffOverdraftReport(String.valueOf(staffCode), endDate, 2, report_staffOverdraft_table,  reports_OV_name, 
                                            reports_OV_amt, reports_OV_monthlyDeduction, reports_OV_dateIssued, reports_OV_startDate,
                                            reports_OV_ExpiryDate, reports_OV_payStatus, reports_OV_ExpStatus);
            loadStaffCodeCombox(OverDstaffNameFilter);
        }else if(staffCode != 0 && startDate != null){
            
            overdR.displayFilteredStaffOverdraftReport(String.valueOf(staffCode), startDate, 1, report_staffOverdraft_table,  reports_OV_name, 
                                            reports_OV_amt, reports_OV_monthlyDeduction, reports_OV_dateIssued, reports_OV_startDate,
                                            reports_OV_ExpiryDate, reports_OV_payStatus, reports_OV_ExpStatus);
            loadStaffCodeCombox(OverDstaffNameFilter);
        }
        
        /*one, only one is selected*/
        else if(staffCode != 0){
            
            overdR.displayFilteredStaffOverdraftReport(String.valueOf(staffCode), 1, report_staffOverdraft_table,  reports_OV_name, 
                                            reports_OV_amt, reports_OV_monthlyDeduction, reports_OV_dateIssued, reports_OV_startDate,
                                            reports_OV_ExpiryDate, reports_OV_payStatus, reports_OV_ExpStatus);
            loadStaffCodeCombox(OverDstaffNameFilter);
        }else if(startDate != null){
            
            overdR.displayFilteredStaffOverdraftReport(startDate, 2, report_staffOverdraft_table,  reports_OV_name, 
                                            reports_OV_amt, reports_OV_monthlyDeduction, reports_OV_dateIssued, reports_OV_startDate,
                                            reports_OV_ExpiryDate, reports_OV_payStatus, reports_OV_ExpStatus);
            
        }else if(endDate != null){
            
            overdR.displayFilteredStaffOverdraftReport(endDate, 3, report_staffOverdraft_table,  reports_OV_name, 
                                            reports_OV_amt, reports_OV_monthlyDeduction, reports_OV_dateIssued, reports_OV_startDate,
                                            reports_OV_ExpiryDate, reports_OV_payStatus, reports_OV_ExpStatus);
            
        }
        else{
            System.err.println("Nothing has been selected to filter by. Please, select atleast one attribute to filter by");
            Dialogs.create().masthead("Invalid attribute selection for filering").message("Nothing has been selected to filter by. Please, select atleast one attribute to filter by").showError();
            
            
        }
        
        //fill the totals with the sum from the table with respect to the specified column
        totalOverdraftAmount.setText(String.valueOf(stuff.getTotalFromDisplayedTable(report_staffOverdraft_table, reports_OV_amt)));
        totalMonthlyDeduction.setText(String.valueOf(stuff.getTotalFromDisplayedTable(report_staffOverdraft_table, reports_OV_monthlyDeduction)));
        
    }

    @FXML
    private void report_staffPayroll(Event event) throws SQLException, ParseException {
        System.out.println("In the staff reports section of report generation");
        //this ives how the worker has been paid i the past
        this.staffReportDefault.displayStaffPayrollReports(report_staff_table, reports_ST_StaffID, reports_ST_name, 
                                                reports_ST_monthlySalary, reports_ST_salaryPaid, 
                                                reports_ST_salaryPayDate);
        
        //this gies the workers ctual balance as at the current month.
        long totBal = this.staffReportDefault.displayBalancePayroll(staffBalSalaryTable, staffBalSalary_Name_col, staffBalSalary_Branch_col, staffBalSalary_balSal_col);
        totalUnpaidSalary.setText(String.valueOf(totBal));
        
        this.totalMonthlyDeduction.setText(String.valueOf(stuff.getTotalFromDisplayedTable(report_staff_table, reports_ST_monthlySalary)));
        this.totalSalaryPaid.setText(String.valueOf(stuff.getTotalFromDisplayedTable(report_staff_table, reports_ST_salaryPaid)));
        
        loadStaffCodeCombox(rep_staffstaffIdSearch);
    }

    @FXML
    private void CustumerReportFilter(MouseEvent event) throws ParseException, SQLException {
        String startDate = null, endDate = null;
        int supCode = 0;
        if(rep_custStartDateSearch.getValue() != null){
            startDate = formatter.format(formatter.parse(this.rep_custStartDateSearch.getValue().toString()));
        }
        if(rep_custEndDateSearch.getValue() != null){
            endDate = formatter.format(formatter.parse(this.rep_custEndDateSearch.getValue().toString()));
        }
        if(rep_custCustIdSearch.getSelectionModel().getSelectedIndex()!=-1){
            val = rep_custCustIdSearch.getEditor().getText();
            System.out.println("the value gotten is " + val);
            supCode = Integer.parseInt(stuff.getKeyFromComboBoxValue("custumer", "NAME", "CUST_ID", val));
            rep_custCustIdSearch.getEditor().clear();
            rep_custCustIdSearch.getEditor().setText("");
            rep_custCustIdSearch.setValue(null);
        }
        
        //clear the datepickers
        rep_custStartDateSearch.getEditor().clear();        rep_custStartDateSearch.setValue(null);
        rep_custEndDateSearch.getEditor().clear();          rep_custEndDateSearch.setValue(null);
        //start searching for those entries that have been filled
        if(supCode != 0 && startDate != null && endDate != null){
            custR.displayFilteredCustReport(String.valueOf(supCode), startDate, endDate, CustumerFinancialRecprdTable, reports_CU_name, reports_CU_debtAmt,  
                                    repCustPayTable, repCustPay_name_col, repCustPay_debtPayment_col, repCustPay_paymentDate_col);
            custR.displayFilteredCustReport(String.valueOf(supCode), startDate, 2, CustumerFinancialRecprdTable, reports_CU_name, reports_CU_debtAmt,   
                                    repCustPayTable, repCustPay_name_col, repCustPay_debtPayment_col, repCustPay_paymentDate_col);
        }
        /*2 selected*/
        else if(startDate != null && endDate != null){
            custR.displayFilteredCustReport(startDate, endDate, 3, CustumerFinancialRecprdTable, reports_CU_name, reports_CU_debtAmt,   
                                    repCustPayTable, repCustPay_name_col, repCustPay_debtPayment_col, repCustPay_paymentDate_col);
        }else if(supCode != 0 && startDate != null){
            custR.displayFilteredCustReport(String.valueOf(supCode), startDate, 1, CustumerFinancialRecprdTable, reports_CU_name, reports_CU_debtAmt,   
                                    repCustPayTable, repCustPay_name_col, repCustPay_debtPayment_col, repCustPay_paymentDate_col);
        }else if(supCode != 0 && endDate != null){
            custR.displayFilteredCustReport(String.valueOf(supCode), endDate, 2, CustumerFinancialRecprdTable, reports_CU_name, reports_CU_debtAmt,   
                                    repCustPayTable, repCustPay_name_col, repCustPay_debtPayment_col, repCustPay_paymentDate_col);
        }
        
        /*noe, only one is selected*/
        else if(supCode != 0){
            custR.displayFilteredCustReport(String.valueOf(supCode), 1,CustumerFinancialRecprdTable, reports_CU_name, reports_CU_debtAmt,   
                                    repCustPayTable, repCustPay_name_col, repCustPay_debtPayment_col, repCustPay_paymentDate_col);
        }else if(startDate != null){
            custR.displayFilteredCustReport(startDate, 2,CustumerFinancialRecprdTable, reports_CU_name, reports_CU_debtAmt,   
                                    repCustPayTable, repCustPay_name_col, repCustPay_debtPayment_col, repCustPay_paymentDate_col);
        }else if(endDate != null){
            custR.displayFilteredCustReport(endDate, 3, CustumerFinancialRecprdTable, reports_CU_name, reports_CU_debtAmt,   
                                    repCustPayTable, repCustPay_name_col, repCustPay_debtPayment_col, repCustPay_paymentDate_col);
            
        }
        else{
            System.err.println("Nothing has been selected to filter by. Please, select atleast one attribute to filter by");
            Dialogs.create().masthead("Invalid attribute selection for filering").message("Nothing has been selected to filter by. Please, select atleast one attribute to filter by").showError();
            
            
        }
        
        //update the stuff
        System.out.println("custumer details to be printed on the screen");
        System.out.println("debt = " + custR.totalDebt);
        System.out.println("payment = " + custR.totalPay);
        
        
//        totalCustumerDebt.setText(String.valueOf(custR.totalDebt));
//        totalCustumerDebtPayment.setText(String.valueOf(custR.totalPay));
        
        
        totalCustumerDebt.setText(String.valueOf(stuff.getTotalFromDisplayedTable(CustumerFinancialRecprdTable, reports_CU_debtAmt)));
        totalCustumerDebtPayment.setText(String.valueOf(stuff.getTotalFromDisplayedTable(repCustPayTable, repCustPay_debtPayment_col)));
       
    
    }

    @FXML
    private void report_custumer(Event event) throws SQLException {
        System.out.println("filling custumer transaction tables");
        custR.displayCustumerReports(CustumerFinancialRecprdTable, reports_CU_name, reports_CU_debtAmt);
        //call the other method to fill the pay table
        
        System.out.println("filling custumer paytable");
        custR.displayCustumerPayReport(repCustPayTable, repCustPay_name_col, repCustPay_debtPayment_col, repCustPay_paymentDate_col);
        
        //////////////////////////////////
        try {
            loadCustumerCodeCombox(rep_custCustIdSearch);
        } catch (SQLException sQLException) {
        }
        System.out.println("in the reports controller \n Debttotal = " + custR.totalDebt + " debtpayment = " + custR.totalPay);
//        totalCustumerDebt.setText(String.valueOf(custR.totalDebt));
//        totalCustumerDebtPayment.setText(String.valueOf(custR.totalPay));
        
        //call the methods to find the sum of debts and payments from the displayed table. the method is in the usefull stuffs class
        totalCustumerDebt.setText(String.valueOf(stuff.getTotalFromDisplayedTable(CustumerFinancialRecprdTable, reports_CU_debtAmt)));
        totalCustumerDebtPayment.setText(String.valueOf(stuff.getTotalFromDisplayedTable(repCustPayTable, repCustPay_debtPayment_col)));
       }

    @FXML
    private void SupplierReportFilter(MouseEvent event) throws SQLException, ParseException {
        //do some filtering. First of all, get the stuffs from the form.
        String startDate=null , endDate=null;
        try {
            startDate = formatter.format(formatter.parse(this.rep_supStartDateSearch.getValue().toString()));
        } catch (Exception e) {}
        try {
            endDate = formatter.format(formatter.parse(this.rep_supEndDateSearch.getValue().toString()));
        } catch (Exception e) {}
//        LocalDate ld = rep_supEndDateSearch.getValue();
//        System.out.println("new formatted date = " + formatter.format(ld));
        
        rep_supStartDateSearch.getEditor().clear();         rep_supStartDateSearch.setValue(null);
        rep_supEndDateSearch.getEditor().clear();           rep_supEndDateSearch.setValue(null);
        
        
        //RESOLVED: ther is a problem here. I cannot the datepicker keeps on appending old values to this new one thus making the search to run the firts time and fail sumsequently
        //rep_supEndDateSearch.setEditable(false);
        int code = 0;
        
        if(rep_supIdSearch.getSelectionModel().getSelectedIndex()!=-1){
            String cCode = this.rep_supIdSearch.getEditor().getText();
            code = Integer.parseInt(getKey(cCode));
        }
       
        
        System.out.println("code - " + code + " start - " + startDate + " end - " + endDate);
        
        //THIS IS OK. THE ONLY PROBLEM IS THAT THE DATEPICKER CONTINOUSELY APPENDS THE DATE TO THE LAST DATE CHOOSEN.
        //supR.supplierReportsFiltered(endDate, endDate, 3, SupplierFinancialRecprdTable, reports_SU_supCode, reports_SU_supName, reports_SU_amtOwed, reports_SU_dateIssued, reports_SU_debtPayment, reports_SU_debtPaymentDate, reports_SU_status);
        //now the logic of which method to call goes here.
        if( startDate != null && endDate != null && code != 0){
            supR.supplierReportsFiltered(code, startDate, endDate, SupplierFinancialRecprdTable, reports_SU_supName, reports_SU_amtOwed
                                        //the next line gives the informaion of he pay table yo be filtered.
                                        ,supCode_forPayment_col, reports_SU_debtPayment, reports_SU_debtPaymentDate, supplierDebtPaymentTable);
            loadSupplierCodeCombox(rep_supIdSearch);
        }
        /*two are not empty.*/
        else if(startDate != null && endDate !=null){
            System.out.println("start and stop");
            supR.supplierReportsFiltered(startDate, endDate, 3, SupplierFinancialRecprdTable,  reports_SU_supName, reports_SU_amtOwed
                                        //the next line gives the informaion of he pay table yo be filtered.
                                        ,supCode_forPayment_col, reports_SU_debtPayment, reports_SU_debtPaymentDate, supplierDebtPaymentTable);
            
        }else if(code != 0 && endDate != null){
            supR.supplierReportsFiltered(String.valueOf(code), endDate, 2, SupplierFinancialRecprdTable,  reports_SU_supName, reports_SU_amtOwed
                                        //the next line gives the informaion of he pay table yo be filtered.
                                        ,supCode_forPayment_col, reports_SU_debtPayment, reports_SU_debtPaymentDate, supplierDebtPaymentTable);
            loadSupplierCodeCombox(rep_supIdSearch);
        }else if(code !=0 &&  startDate != null){
            supR.supplierReportsFiltered(String.valueOf(code), startDate, 1, SupplierFinancialRecprdTable, reports_SU_supName, reports_SU_amtOwed
                                        //the next line gives the informaion of he pay table yo be filtered.
                                        ,supCode_forPayment_col, reports_SU_debtPayment, reports_SU_debtPaymentDate, supplierDebtPaymentTable);
            loadSupplierCodeCombox(rep_supIdSearch);
        }
        /*one is not empty*/
        else if(code != 0){
            supR.supplierReportsFiltered(String.valueOf(code), 1,  SupplierFinancialRecprdTable, reports_SU_supName, reports_SU_amtOwed
                                        //the next line gives the informaion of he pay table yo be filtered.
                                        ,supCode_forPayment_col, reports_SU_debtPayment, reports_SU_debtPaymentDate, supplierDebtPaymentTable);
            loadSupplierCodeCombox(rep_supIdSearch);
        }else if(startDate != null){
            supR.supplierReportsFiltered(startDate, 2, SupplierFinancialRecprdTable, reports_SU_supName, reports_SU_amtOwed
                                        //the next line gives the informaion of he pay table yo be filtered.
                                        ,supCode_forPayment_col, reports_SU_debtPayment, reports_SU_debtPaymentDate, supplierDebtPaymentTable);
        }else if(endDate != null){
            supR.supplierReportsFiltered(endDate, 3, SupplierFinancialRecprdTable, reports_SU_supName, reports_SU_amtOwed
                                        //the next line gives the informaion of he pay table yo be filtered.
                                        ,supCode_forPayment_col, reports_SU_debtPayment, reports_SU_debtPaymentDate, supplierDebtPaymentTable);
        }
        /*all of them are empty so kill the user with an error message.*/
        else{
            Action showError = Dialogs.create().title("empty").message("At least one search filter must be entered before we can do a search").showError();

        }
        
        //get the total from method in the usefullstuffs class and display it on the ui.
        totalSupplierAmoutOwing.setText(String.valueOf(stuff.getTotalFromDisplayedTable(SupplierFinancialRecprdTable, reports_SU_amtOwed)));
        totalSupplierAmountPayment.setText(String.valueOf(stuff.getTotalFromDisplayedTable(supplierDebtPaymentTable, reports_SU_debtPayment)));
        
    }

    @FXML
    private void report_supplier(Event event) throws SQLException {
        //we have to load supplier idToEditTransactions to the combobox here.
        
        try {
            loadSupplierCodeCombox(rep_supIdSearch);
        } catch (SQLException sQLException) {
        }
        
        if(supR.displaySupplierOrderRreports(SupplierFinancialRecprdTable,  
                                        reports_SU_supName, reports_SU_amtOwed)){
        }
        supR.displaySupplierPay(supplierDebtPaymentTable, supCode_forPayment_col, reports_SU_debtPayment, reports_SU_debtPaymentDate);
//        reports_SU_debtPayment, reports_SU_debtPaymentDate
        
        //get the total from method in the usefullstuffs class and display it on the ui.
        totalSupplierAmoutOwing.setText(String.valueOf(stuff.getTotalFromDisplayedTable(SupplierFinancialRecprdTable, reports_SU_amtOwed)));
        totalSupplierAmountPayment.setText(String.valueOf(stuff.getTotalFromDisplayedTable(supplierDebtPaymentTable, reports_SU_debtPayment)));
        
    }

    @Override
    public void setScreenParent(ScreensController pane) {
        //nothing here yet
    }

    @FXML
    private void report_staffOverdraft(Event event) throws SQLException {
        System.out.println("in the overdraft generation.");
        overdR.displayStaffOverdraftReport(report_staffOverdraft_table,  reports_OV_name, 
                                            reports_OV_amt, reports_OV_monthlyDeduction, reports_OV_dateIssued, reports_OV_startDate,
                                            reports_OV_ExpiryDate, reports_OV_payStatus, reports_OV_ExpStatus);
        
        loadStaffCodeCombox(OverDstaffNameFilter);
        
        //fill the totals with the sum from the table with respect to the specified column
        totalOverdraftAmount.setText(String.valueOf(stuff.getTotalFromDisplayedTable(report_staffOverdraft_table, reports_OV_amt)));
        totalMonthlyDeduction.setText(String.valueOf(stuff.getTotalFromDisplayedTable(report_staffOverdraft_table, reports_OV_monthlyDeduction)));
        
    }

    @FXML
    private void printReceipt(MouseEvent event) throws DocumentException, BadElementException, IOException {
        /////////////////
        if(gentransR.gType == "INCOME")
            print.genTrans(report_genTrans_table, report_GT_Name, report_GT_desc, report_GT_totamt, report_GT_time, check.totInc, gentransR.gType);
        else{
            print.genTrans(report_genTrans_table, report_GT_Name, report_GT_desc, report_GT_totamt, report_GT_time, check.totExp, gentransR.gType);
        }
    }

    
    ///////////////////////eugene's work /////////////////////////////////
    
    
    
  /* this method is used to fill the transaction name 
    
    
    */
       public void loadTransactionNameCombo(){
            try {
           
          transactionNameComboBox.getItems().clear();
           
       } catch (Exception e) {
           
       }
      String getData;
     //this is the querry to retrieve information from the income class table ie the 
     // name and code name for front end and code for backend 
        if( "INCOME".equals(gentransR.gType)){
            getData = "SELECT njieDB.INC_TRANS_CLASS.NAME, njieDB.INC_TRANS_CLASS.CODE FROM njieDB.INC_TRANS_CLASS";
        }else{
            getData = "SELECT njieDB.EXP_TRANS_CLASS.NAME, njieDB.EXP_TRANS_CLASS.CODE FROM njieDB.EXP_TRANS_CLASS";
        }
          
           
           try {
              System.out.println(getData);
           
              db.setQuery(getData);
              
           } catch (Exception e) {
           
           }
           if (db.getRowCount() > 0) {
               clearArrayList();
      for(int i = 0 ; i < db.getRowCount() ; i++){

             String name = db.getValueAt(i, 0).toString();
             String code = db.getValueAt(i, 1).toString();
     
            System.out.println("COMBO BOX IS FILLED WITH "+name+" AND "+ code );

             System.out.println("---------------- FILLING THE COMBO BOXES -----------------"); 

            this.transactionNameComboBox.getItems().add(new ComboItem(name, code));
            ComboValue.add(i, name);
            ComboKey.add(i, code);
  
        }         
//           this. transactionNameComboBox.getSelectionModel().selectFirst();
        
        }//end if 

        else{
          
        System.out.println(" there is no information in the database to fill the transaction Name combo box");
            
        }
           AutoCompleteComboBoxListener autocom = new AutoCompleteComboBoxListener<>(this.transactionNameComboBox);
    }

   /*  THIS METHOD FILTERS THE GENERAL TRANSACTION TABLE WHEN THE VARIOUS CRITERIA 
       FOR PERFORMING THE TRANSACTION ARE CHOOSEN IE
       
       @2 param
       - TRANSACTION NAME 1
       - START DATE 2
       - END DATE 3
       
       @3param
       - TRANSACTION NAME  AND START DATE 11
       - TRANSACTION NAME  AND END DATE 12
       - START DATE AND END DATE 13
       
       @3param(string)
       - TRANSACTION NAME START DATE AND END DATE 
       
     */
    @FXML
    private void filterGeneralTransactionTable(ActionEvent event) throws ParseException, SQLException {
        
          String startDate = null , endDate = null ;
          int code= 0;
      
        try {
            startDate = formatter.format(formatter.parse(rep_genStartDateSearch.getValue().toString()));
            
        } catch (Exception e) {
        
             
        }
  
        try {
              endDate = formatter.format(formatter.parse(rep_genEndtDateSearch.getValue().toString()));
              
        } catch (Exception e) {
        
        }
        
     
      
        
               if(transactionNameComboBox.getSelectionModel().getSelectedIndex()!=-1){
                 String cCode = this.transactionNameComboBox.getEditor().getText();
                code = Integer.parseInt(getKey(cCode));
             
        }
        
        //here we find out the search filter option and load the tab les particular filter 
        // table 
               
       if( startDate != null && endDate != null && code != 0){
       
        // all the search params have been chosen 
           
           gentransR.filterGeneralTransactionOptionThree(code, startDate, endDate, report_genTrans_table,report_GT_Name, report_GT_desc, report_GT_totamt, report_GT_time , report_GT_id_Column);
            editDescriptionAndAmountOnGeneralTransactionTablePane.setVisible(false);
            report_genTrans_table.setDisable(false);
       }
       /*two are not empty.*/
        else if(startDate != null && endDate !=null){
           
          //code for  start date and end date selected   
         
          gentransR.filterGeneralTransactionOptionTwo(startDate, endDate, 13, report_genTrans_table, report_GT_Name, report_GT_desc, report_GT_totamt, report_GT_time ,report_GT_id_Column);
           editDescriptionAndAmountOnGeneralTransactionTablePane.setVisible(false);
          report_genTrans_table.setDisable(false);
          
        }else if(code != 0 && endDate != null){
           
          
            // code for transaction name  and end date 
           
        gentransR.filterGeneralTransactionOptionTwo(String.valueOf(code), endDate, 12, report_genTrans_table, report_GT_Name, report_GT_desc, report_GT_totamt, report_GT_time ,report_GT_id_Column);
        editDescriptionAndAmountOnGeneralTransactionTablePane.setVisible(false);
        report_genTrans_table.setDisable(false);
   
        }else if(code !=0 &&  startDate != null){
            
            
            //code for transaction name and start date
            
           gentransR.filterGeneralTransactionOptionTwo(String.valueOf(code), startDate, 11, report_genTrans_table, report_GT_Name, report_GT_desc, report_GT_totamt, report_GT_time ,report_GT_id_Column);
         editDescriptionAndAmountOnGeneralTransactionTablePane.setVisible(false);
         report_genTrans_table.setDisable(false);
        }  
               
          /*one is not empty*/
        else if(code != 0 ){
           
            // the transaction name has been choosen only 
         
         gentransR.filterGeneralTransactionOptionOne(String.valueOf(code), 1, report_genTrans_table, report_GT_Name, report_GT_desc, report_GT_totamt, report_GT_time ,report_GT_id_Column);
         editDescriptionAndAmountOnGeneralTransactionTablePane.setVisible(false);
         report_genTrans_table.setDisable(false);
         
           System.out.println("THE SEARCH USING TRANSACTION NAME ONLY SUCCESSFUL");  
        
        }else if(startDate != null ){
      
          // only start date has been choosen 
    
         gentransR.filterGeneralTransactionOptionOne( startDate, 2, report_genTrans_table, report_GT_Name, report_GT_desc, report_GT_totamt, report_GT_time ,report_GT_id_Column);
         editDescriptionAndAmountOnGeneralTransactionTablePane.setVisible(false);
         report_genTrans_table.setDisable(false);
         
          System.out.println("THE SEARCH USING TRANSACTION NAME ONLY SUCCESSFUL"); 
        
        }else if(endDate != null ){
        
            //only end date has been choosen           
         gentransR.filterGeneralTransactionOptionOne( endDate, 3, report_genTrans_table, report_GT_Name, report_GT_desc, report_GT_totamt, report_GT_time ,report_GT_id_Column);
         editDescriptionAndAmountOnGeneralTransactionTablePane.setVisible(false);
         report_genTrans_table.setDisable(false);
         
        }
        
        /*all of them are empty so kill the user with an error message.*/
       
        else{
        
            Action showError = Dialogs.create().title("empty").message("At least one search filter must be entered before we can do a search").showError();

        }      
               
    //clear the date pickers and set the values to null
       
        rep_genStartDateSearch.getEditor().clear();
        rep_genStartDateSearch.setValue(null);
    
        rep_genEndtDateSearch.getEditor().clear();
        rep_genEndtDateSearch.setValue(null);
    
        //reload the combo box in this section 
        loadTransactionNameCombo();
        this.totIncExp.setText(String.valueOf(gentransR.totalIncExpAfterFilter));
    }

 /*  THIS METHOD DISPLAYS THE PANE WHERE THE DESCRIPTION AND AMOUNT OF A 
    GENERAL TRANSACTION CAN BE CHANGED WHEN  A PARTICulAR RECORD IS SELECTED
    
  */
    long oldAmt;
    @FXML
    private void displayEditDescriptionAndAmountOnGeneralTransactionTablePane(ActionEvent event) throws SQLException {
        //here we have to get the transname from the table and display the particular record 
        //on the label 
        String col1 = this.report_genTrans_table.getSelectionModel().getSelectedItem().col1.get();
        String col2 = this.report_genTrans_table.getSelectionModel().getSelectedItem().col2.get();
        String col3 = this.report_genTrans_table.getSelectionModel().getSelectedItem().col3.get();
        String col5 = this.report_genTrans_table.getSelectionModel().getSelectedItem().col5.get();
        this.oldAmt = Long.parseLong(col3); //this will be the previous amount before updating the information in the database.
        
        this.idToEditTransactions = Integer.parseInt(col5);
        System.out.println("the editing id is " + this.idToEditTransactions);

        //check to make sure this is a normal transaction
        if(!"CUSTUMER PAYBACK".equalsIgnoreCase(col1)  && !"SUPPLIER PAYMENT".equalsIgnoreCase(col1) && 
           !"PAYROLL".equalsIgnoreCase(col1)    && !"OVERDRAFT".equalsIgnoreCase(col1)      ){
            //HERE WE DISABLE THE TABLE VIEW AND THEN SET THE EDITPANE VISIBLE 

            report_genTrans_table.setDisable(true);
            editDescriptionAndAmountOnGeneralTransactionTablePane.setVisible(true);

            generalTransactionDescriptionEditTextField.setText(col2);
            generalTransactionAmountEditableTextField.setText(col3);
            lblTransNameSelectFromTable.setText(col1);
            
            specialTransEditAmtInput.setDisable(false);
            specialTransEditDescInput.setDisable(false);
            
        }   //this is now working with the custumer payback
        else{ 
            SpecialEditDescriptionAndAmountOnGeneralTransactionTablePane.setVisible(true);
            disableBeforeSpecialEdit();
            
            //fill desc and amt fields
            specialTransEditDescInput.setText(col2);
            specialTransEditAmtInput.setText(col3);
            
            if("CUSTUMER PAYBACK".equalsIgnoreCase(col1)){
                    specialEditTransCodeLabelForInputCombo.setText("Cutumer's Name");
                    loadCustumerCodeCombox(specialEditTrans_idCmbxInput);
                    specialTransEditTransClassInput.setText("CUSTUMER PAYBACK");
            }else //this is working with supplier payment 
                if("SUPPLIER PAYMENT".equalsIgnoreCase(col1)){
                    specialEditTransCodeLabelForInputCombo.setText("Supplier's Name");
                    loadSupplierCodeCombox(specialEditTrans_idCmbxInput);
                    specialTransEditTransClassInput.setText("SUPPLIER PAYMENT");
            }else //this got to do with payroll 
                if("PAYROLL".equalsIgnoreCase(col1)){
                    specialEditTransCodeLabelForInputCombo.setText("Staff's Name");
                    loadStaffCodeCombox(specialEditTrans_idCmbxInput);
                    specialTransEditTransClassInput.setText("STAFF PAYROLL");
                    specialTransEditDescInput.setDisable(true);
            }else //this has to do with overdraft editing
                if("OVERDRAFT".equalsIgnoreCase(col1)){
                    specialEditTransCodeLabelForInputCombo.setText("Staff's Name \n Corresponding to\n the Overdraft");
                    loadStaffCodeCombox(specialEditTrans_idCmbxInput);
                    specialTransEditTransClassInput.setText("STAFF OVERDRAFT");
                    specialTransEditAmtInput.setDisable(true);
                    specialTransEditDescInput.setDisable(true);
            }
        }
}

    int idToEditTransactions ;
    long editAmt;
    @FXML
    private void editDescriptionAndAmountOnGeneralTransactionTablePane(ActionEvent event) {
    
        //THIS METHOD GETS THE VALUE FROM THE EDIT FORM ON THE TABLE EDIT 
        //GENERAL TRANSACTION PANE AND UPDATES THE RECORD IN THE DATABASE 
     
        String desc = generalTransactionDescriptionEditTextField.getText();
        int amount = Integer.parseInt(generalTransactionAmountEditableTextField.getText());
              
        //we need to select the data from the income table to be used for the comparison
        String database_update;
        System.out.println("we are updating with id " + this.idToEditTransactions);
        if(  "INCOME".equals(gentransR.gType)){
            database_update = "UPDATE njieDB.INCOME SET njieDB.INCOME.MEMO = '"+ desc + "' , njieDB.INCOME.AMOUNT = " + amount +" WHERE "
                                  + "njieDB.INCOME.TRANS_ID = "+ this.idToEditTransactions +" " ;
        }else{
            database_update = "UPDATE njieDB.EXPENSES SET njieDB.EXPENSES.MEMO = '"+ desc + "' , njieDB.EXPENSES.AMOUNT = " + amount +" WHERE "
                                   + "njieDB.EXPENSES.TRANS_ID = "+ this.idToEditTransactions +" " ;
        }
        System.out.println("Query to update the transaction \n "+ database_update);
              
       
        try {
            db.setQuery(database_update);
            Dialogs.create().title("confirm").message("update was successfull "
                                                        +  " do the search again to get the updated data").showInformation();
            report_genTrans_table.setDisable(false);
            editDescriptionAndAmountOnGeneralTransactionTablePane.setVisible(false);
                 
        } catch (SQLException | IllegalStateException e) {       
            Dialogs.create().title("Error").message("update Failed").showError();
            editDescriptionAndAmountOnGeneralTransactionTablePane.setVisible(false);
   
        }
 
        editDescriptionAndAmountOnGeneralTransactionTablePane.setVisible(false);
        
    }

    @FXML
    private void cancleEditGeneralTransactionEditTextField(ActionEvent event) {
   
        report_genTrans_table.setDisable(false);
        editDescriptionAndAmountOnGeneralTransactionTablePane.setVisible(false);
        
         generalTransactionDescriptionEditTextField.setText("");
         generalTransactionAmountEditableTextField.setText("");
         lblTransNameSelectFromTable.setText("no transaction choosen");
         
    }

    public void enableAfterSpecialEdit(){
        report_genTrans_table.setDisable(false);
        btneditDescriptionAndAmountOnGeneralTransactionTablePane.setDisable(false);  
        SpecialEditDescriptionAndAmountOnGeneralTransactionTablePane.setVisible(false);
    }
    
    public void disableBeforeSpecialEdit(){
        report_genTrans_table.setDisable(true);
        btneditDescriptionAndAmountOnGeneralTransactionTablePane.setDisable(true);  
        SpecialEditDescriptionAndAmountOnGeneralTransactionTablePane.setVisible(true);
    }
    
    long possiblePayableAmount;
    public boolean getPossibleStaffPaymentAmount(int _staffID) throws SQLException, ParseException{
        this.possiblePayableAmount = (long)0;
        int payroll_staff_id;
        int overdraftDeductionPerMonth = 0;
        /*
        this method had an error. the error reads
        Caused by: java.sql.SQLException: ResultSet not open. 
        Operation 'absolute()' not permitted. Verify that autocommit is off.
        */
        
        
        System.out.println("Searching for employee for payment section start");
        payroll_staff_id = _staffID;
        System.out.println("staff ID : " + payroll_staff_id);
       /* input_staffName_payroll*/
        String Qstaff_salaryAndName, Qpay_totAmtPaid; 
        /*get the monthly salary and name of the staff
        * Also, get the date of work start 
        * This is in order to calculate the cumulative salary
        * Maybe for some reason, the staff might not have collected the salary completely
        * for some months or did not collect at all, the difference between the 
        * the his total salary collected from date 1 and the product of the salary and the 
        * total number of months worked can give the salary the worker can collect even in 
        * in the case of the months which he did not collect any salary.
        *        --  monthlysalary, staffName, employmentDate  --
        */
        //chek.checkstaff();
        Qstaff_salaryAndName = "SELECT njieDB.STAFF.SALARY, njieDB.STAFF.NAME, njieDB.STAFF.EMP_DATE "
                + " FROM njieDB.STAFF WHERE njieDB.STAFF.STAFF_ID = " + payroll_staff_id + "";
        System.out.println("Gettins sal, sNam and empDate " + Qstaff_salaryAndName);
        long monthlySalary = (long)0;
        String startEmpDate = "", kstaffName = "";
        try {
            db.setQuery(Qstaff_salaryAndName);
            monthlySalary = Integer.parseInt(db.getValueAt(0, 0).toString());
            kstaffName = db.getValueAt(0, 1).toString();
            startEmpDate = db.getValueAt(0, 2).toString();
        } catch (SQLException sQLException) {
            System.out.println("sql excemtion");
            sQLException.printStackTrace();
            Dialogs.create().title("Error").message("Error fetching staff information on payroll").showError();
        } catch (IllegalStateException illegalStateException) {
            System.out.println("illegal expression ");
        }
        
        
      
        Formatter cc = new Formatter();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        dNow = new Date();
        String todayDate = format.format(dNow);
       
        Date d1 = format.parse(startEmpDate);
        Date d2 = format.parse(todayDate);
        int totalWorkMonths = UsefullStuff.differenceInMonths(d1, d2);
        totalWorkMonths += 1;
        
        String empDate = cc.format("%tF", db.getValueAt(0, 2)).toString();
        System.out.println("Information gathered: " + kstaffName + " -- " + monthlySalary + " --- " + empDate );
        
        System.out.println("staffName = " + kstaffName + "empDate = " + empDate + "monthly salary " + monthlySalary);
        
         /*get the total salary paid since employment*/
        Qpay_totAmtPaid = "SELECT SUM(njieDB.PAYROLL.AMOUNT_PAID) FROM njieDB.PAYROLL "
                + " WHERE njieDB.PAYROLL.STAFF_ID = " + payroll_staff_id ;
        System.out.println("Total SalaryPaid : " + Qpay_totAmtPaid);
        long totalSalaryPaid = (long)0;
        db.setQuery(Qpay_totAmtPaid);
        if(db.getValueAt(0, 0) != null){
            totalSalaryPaid = Long.parseLong(db.getValueAt(0, 0).toString());
        }
        System.out.println("total paid salary = " + totalSalaryPaid);
        
        /*This is the total salary the staff could have obtained from emp date till date*/
        long totalSalaryFromEmpDate = (long)totalWorkMonths * monthlySalary;
        System.out.println("total months worked = " + totalWorkMonths);
        System.out.println("total salary from emp date = " + totalSalaryFromEmpDate);
        
        /*now i get the possible payable amount*/
        possiblePayableAmount = totalSalaryFromEmpDate - totalSalaryPaid;
        
        /*check if it is he can collect something under normal circumstances, ie with no overdraft */
        if(possiblePayableAmount == 0){//he cannot take anything since he has 0 balance
            return false;
        }
        else{//he has some money to collect.
            //But we need to know if he has an overdraft so that we deduct the appropriate amount
            // and see if he/she still has any valid payment.
            String Q_OD;
            
            /****
             * since the overdraft date is specified by the admin, also need to check if the date of today
             * is greater that or equal to the start date of the overdraft along side that status being false.
             */
            System.out.println("today's date ois " + todayDate);
            System.out.println("possible payable amount : " + possiblePayableAmount);
            Q_OD = "SELECT njieDB.OVERDRAFT.DEDUCTION_PER_MONTH FROM njieDB.OVERDRAFT " 
                    + " WHERE njieDB.OVERDRAFT.STAFF_ID = " + payroll_staff_id
                    + " AND njieDB.OVERDRAFT.EXP_STATUS = " + false + " AND "
                    + " njieDB.OVERDRAFT.DEDUCTION_START_DATE <= '" + todayDate + "'";
            System.out.println("getting overdraft query ===+==========+= \n" + Q_OD);
            db.setQuery(Q_OD);
            if(db.getRowCount() > 0){// has overdraft
                overdraftDeductionPerMonth = Integer.parseInt(db.getValueAt(0, 0).toString()); 
                System.out.println("overdraft = " + overdraftDeductionPerMonth);
            }
            //we now have an overdraft of either 0 or a certain value.
            //take off the overdraft deduction from the posible income to be collected.
            possiblePayableAmount -= overdraftDeductionPerMonth;
            
            //check if the staff with the deduction due to overdraft can still take home something.
            if(possiblePayableAmount <= 0){//no possible payment

                return false;
            }
        }
        return true;
    }
    
    
//  ------------------  Not Usefull. I think i will have to remove it beefore soon when i see its totally useless.
    
//    public void updateSuppCustDebt(String custOrSup, String _id, long editAmtDiff) throws SQLException{
//        long allPayments, totalAmtForUpdate;
//        String Q_allPayments = null;
//        String Q_getIssueIdandAmt = null;
//        switch (custOrSup) {
//            case "cust":
//                Q_allPayments = "select sum(njieDB.DEBT_PAYMENT.amount_paid) from njieDB.DEBT_PAYMENT"
//                        + " where njieDB.DEBT_PAYMENT.cust_id = '" + _id + "'";
//                ;
//                Q_getIssueIdandAmt = " select njieDB.DEBTS.DEBT_ID, njieDB.DEBTS.AMOUNT "
//                        + " FROM njieDB.DEBTS WHERE njieDB.DEBTS.CUST_ID = '" + _id 
//                        + "' order by njieDB.DEBTS.AMOUNT ";
//                          
//                break;
//            case "supp":
//                Q_allPayments = "select sum(njiedb.SUPPLIER_PAY.amount_paid) from njiedb.SUPPLIER_PAY "
//                        + " where njiedb.SUPPLIER_PAY.sup_id = " + Integer.parseInt(_id) ;
//                Q_getIssueIdandAmt = " select njieDB.SUPPLIER_PAY_ORDER.ID, njieDB.SUPPLIER_PAY_ORDER.AMOUNT "
//                        + " FROM njieDB.SUPPLIER_PAY_ORDER WHERE "
//                        + " njieDB.SUPPLIER_PAY_ORDER.SUPPLIER_CODE = " + Integer.parseInt(_id) + " order by njieDB.SUPPLIER_PAY_ORDER.AMOUNT ";
//               
//                break;                
//        }
//        
////        //get the extra amt from the table
////        System.out.println("xtra : " + Q_xtra);
////        db.setQuery(Q_xtra);
////        xtraAmt = Integer.parseInt(db.getValueAt(0, 0).toString());//done
//        
//        //get all the payments to that supplier or by that custumer
//        System.out.println("total already paid " + Q_allPayments);
//        db.setQuery(Q_allPayments);
//        allPayments = Long.parseLong(db.getValueAt(0, 0).toString());
//        System.out.println("all money paid for id " + _id + "is " + allPayments);
//        
//        //make total payable
//        System.out.println("amt diff = " + editAmtDiff );
//        totalAmtForUpdate = allPayments + editAmtDiff;  // - xtraAmt ;
//        System.out.println("we are going to do a regorous update with "+ totalAmtForUpdate);
//        
//        //populate the arrayList
//        this.getInfoOnSupPayIssueOrCustDebt(Q_getIssueIdandAmt);
//        long issueDiff = this.getTotalSupCustPayIssueAmt() - totalAmtForUpdate;
////        if(issueDiff - editAmtDiff > 0 && editAmtDiff != 0){
//            /***********now the whole show starts. ***********************/
//            try {
//
//
//                //iterate the debts reducing the updateAmt updating the debts and updating the xtra with the amt left that cannot
//                //make a debt paid.
//                for(int i = 0; i < this.totalNumOfIssuesUnpaid; i++){
//                    System.out.println("inside loop to update bals");
//
//                    if(totalAmtForUpdate >= Long.parseLong(this.issuedAmt.get(i))){ // the amt inputed is suffecient to update this issue id
//                            System.out.println("we are updating amt " + this.issuedAmt.get(i));
//                            
//                            
//                            totalAmtForUpdate -= Long.parseLong(this.issuedAmt.get(i));
//                            System.out.println("balance = " + totalAmtForUpdate);
//                        }else{ //the amount cannot update the status. som push that value tothe xtra table
//                            System.out.println("extra " + totalAmtForUpdate);
//                            switch (custOrSup) {
//                                case "cust":
//                                    
//                                    break;
//                                case "supp":
//                                   
//                                    break;
//                                default:
//                                    Dialogs.create().message("wrong option").showError();
//                                    exit(1);
//                            }
//                            break;
//                        }
//
//                }
//
//
//            } catch (Exception e) {
//                e.printStackTrace();
//                Dialogs.create().message("There was an error whild effecting the change. Please try again.").showError();
//            }
////        }else{
////            Dialogs.create().message("inputed amount will cause an excess of " + Math.abs(issueDiff - editAmtDiff)).showInformation();
////        }
//        
//        
//    }
//    
    @FXML
    private void savaSpecialEditedTransaction(ActionEvent event) throws SQLException, ParseException {
       
        System.out.println("checking payroll");
        check.checkPayroll();
        
        
        String _newId = getKey((specialEditTrans_idCmbxInput.getEditor().getText()));
        long newAmt = Long.parseLong(specialTransEditAmtInput.getText());
        String newDescFromUser = specialTransEditDescInput.getText();
        
        int diffAmt = (int)(newAmt - this.oldAmt)  ;
        long TotAmtLeftToBePaid;
        int paymentIdForPaymentUpdate;
        String Q_updateExp = "update njieDB.EXPENSES set "
                                + " njieDB.EXPENSES.AMOUNT = " + newAmt 
                                + " where njieDB.EXPENSES.TRANS_ID = " + this.idToEditTransactions;
        
        Action confirmer = Dialogs.create().
                    title("confirm Edit").
                    masthead( "\nname   : " + (specialEditTrans_idCmbxInput.getEditor().getText())
                            + "\namt   : " + String.valueOf(newAmt)
                            + "\ndesc  : " + newDescFromUser ).
                    message("Commit the Change?").showConfirm();
                   
        if (confirmer == Dialog.Actions.YES) {
            String type = specialTransEditTransClassInput.getText();
            switch(type){
                case "CUSTUMER PAYBACK": //we are here now
                    try{
                        System.out.println("begin the updating of the custumer payback.");
                        String qTotAmtLeftToBePaid = "select sum(njieDB.DEBTS.AMOUNT) - sum(njieDB.DEBT_PAYMENT.AMOUNT_PAID) "
                                + " FROM njieDB.DEBTS, njieDB.DEBT_PAYMENT WHERE njieDB.DEBTS.CUST_ID = '" + _newId + "' ";
                        db.setQuery(qTotAmtLeftToBePaid);
                        TotAmtLeftToBePaid = Long.parseLong(db.getValueAt(0, 0).toString());
                        
                        //test to see if the custumer's new inputed amount will create a negative value in the system
                        if ( diffAmt > TotAmtLeftToBePaid ){
                            Dialogs.create().title("Excess").masthead("New inputed Amount causes an negative value").showInformation();
                            return ;
                        }
                        
                        //at this point, we know that the new amount enterd stays within the range of the possible payable amount dued for the custumer to pay the company.
                        
                        /*see if actually there was a modification in the amount inputed.
                        * If no modification was done, then there is no need for us to update with the same information*/
                        if(diffAmt == 0){
                            Dialogs.create()
                                    .title("No Change")
                                    .masthead("No change in the information.")
                                    .message("The old amount and the new one inputed are the same so no change has been effected.")
                                    .showInformation();
                            return ;
                        }
                        
                        /*we know now that the old and new amounts are different. So prepare to make  the updates.
                        * We shall be updating only the amont to the new amount in both the custumer table and the income table*/
                        String qUpdateCustAmt, qUpdateIncomeAmt, qGetPaymentIdForUpdatingPayment;
                        qGetPaymentIdForUpdatingPayment = "select njieDB.DEBT_PAYMENT.PAY_ID from njieDB.DEBT_PAYMENT where"
                                + " njieDB.DEBT_PAYMENT.AMOUNT_PAID = " + this.oldAmt +  " AND "
                                + " njieDB.DEBT_PAYMENT.CUST_ID = '" + _newId + "' ORDER BY njieDB.DEBT_PAYMENT.DATE DESC";
                        db.setQuery(qGetPaymentIdForUpdatingPayment);
                        paymentIdForPaymentUpdate = Integer.parseInt(db.getValueAt(0, 0).toString());
                        qUpdateCustAmt = "update njieDB.DEBT_PAYMENT set njieDB.DEBT_PAYMENT.AMOUNT_PAID = " + newAmt + " "
                                + " where njieDB.DEBT_PAYMENT.PAY_ID = " + paymentIdForPaymentUpdate;
                        qUpdateIncomeAmt = "update njieDB.INCOME set njieDB.INCOME.AMOUNT = " + newAmt + " where "
                                + " njieDB.INCOME.TRANS_ID = " + this.idToEditTransactions;
                        try{
                            db.setQuery(qUpdateCustAmt);
                            db.setQuery(qUpdateIncomeAmt);
                            manViewIncTrans(null);
                            Dialogs.create().title("success").message("Update completed succesfully.").showInformation();
                        }catch(Exception ex){
                            Dialogs.create().title("Update Error")
                                    .masthead("There was an error updating with the new information")
                                    .message("error message \n\n" + ex.getMessage())
                                    .showError();
                        }
                    }catch(Exception e){
                        Dialogs.create().title("Error").masthead("There was an error updating the information").message("error code \n").showInformation();
                        e.printStackTrace();
                    }
                    break;
                case "SUPPLIER PAYMENT": //done

                    try{
                        System.out.println("begin the updating of the supplier payment.");
                        String qTotAmtLeftToBePaid = "select sum(njieDB.SUPPLIER_PAY_ORDER.AMOUNT) - sum(njieDB.SUPPLIER_PAY.AMOUNT_PAID) "
                                + " FROM njieDB.SUPPLIER_PAY_ORDER, njieDB.SUPPLIER_PAY WHERE njieDB.SUPPLIER_PAY_ORDER.SUPPLIER_CODE = " + Integer.parseInt(_newId) + " ";
                        db.setQuery(qTotAmtLeftToBePaid);
                        TotAmtLeftToBePaid = Long.parseLong(db.getValueAt(0, 0).toString());
                        
                        //test to see if the custumer's new inputed amount will create a negative value in the system
                        if ( diffAmt > TotAmtLeftToBePaid ){
                            Dialogs.create().title("Excess").masthead("New inputed Amount causes an negative value").showInformation();
                            return ;
                        }
                        
                        //at this point, we know that the new amount enterd stays within the range of the possible payable amount dued for the custumer to pay the company.
                        
                        /*see if actually there was a modification in the amount inputed.
                        * If no modification was done, then there is no need for us to update with the same information*/
                        if(diffAmt == 0){
                            Dialogs.create()
                                    .title("No Change")
                                    .masthead("No change in the information.")
                                    .message("The old amount and the new one inputed are the same so no change has been effected.")
                                    .showInformation();
                            return ;
                        }
                        
                        /*we know now that the old and new amounts are different. So prepare to make  the updates.
                        * We shall be updating only the amont to the new amount in both the supplier table and the income table*/
                        String qUpdateCustAmt, qUpdateExpenseAmt, qGetPaymentIdForUpdatingPayment;
                        qGetPaymentIdForUpdatingPayment = "select njieDB.SUPPLIER_PAY.PAY_ID from njieDB.SUPPLIER_PAY where"
                                + " njieDB.SUPPLIER_PAY.AMOUNT_PAID = " + this.oldAmt +  " AND "
                                + " njieDB.SUPPLIER_PAY.SUP_ID = " + Integer.parseInt(_newId) + " ORDER BY njieDB.SUPPLIER_PAY.DATE DESC";
                        db.setQuery(qGetPaymentIdForUpdatingPayment);
                        paymentIdForPaymentUpdate = Integer.parseInt(db.getValueAt(0, 0).toString());
                        qUpdateCustAmt = "update njieDB.SUPPLIER_PAY set njieDB.SUPPLIER_PAY.AMOUNT_PAID = " + newAmt + " "
                                + " where njieDB.SUPPLIER_PAY.PAY_ID = " + paymentIdForPaymentUpdate;
                        qUpdateExpenseAmt = "update njieDB.EXPENSES set njieDB.EXPENSES.AMOUNT = " + newAmt + " where "
                                + " njieDB.EXPENSES.TRANS_ID = " + this.idToEditTransactions;
                        try{
                            db.setQuery(qUpdateCustAmt);
                            db.setQuery(qUpdateExpenseAmt);
                            manViewExpTrans(null);
                            Dialogs.create().title("success").message("Update completed succesfully.").showInformation();
                        }catch(Exception ex){
                            Dialogs.create().title("Update Error")
                                    .masthead("There was an error updating with the new information")
                                    .message("error message \n\n" + ex.getMessage())
                                    .showError();
                        }
                    }catch(Exception e){
                        Dialogs.create().title("Error").masthead("There was an error updating the information").message("error is \n ").showInformation();
                        e.printStackTrace();
                    }
                    
                    break;
                    
                case "STAFF PAYROLL": //done. only some few stuffs to perfect
                        Dialogs.create().title("Inactive")
                                .masthead("This section is under development")
                                .message("For now, we are still developing this section so just hang on for the next release")
                                .showInformation();
                    break;
                    
                    
                case "STAFF OVERDRAFT": //working well. tweaks might be needed in the future.
                    String checkValidODForStaff = "select njiedb.overdraft.ov_id from njiedb.overdraft "
                            + " where njiedb.overdraft.amount = " + newAmt + " and njiedb.overdraft.EXP_STATUS = " + false 
                            + " and njiedb.overdraft.staff_id = " + Integer.parseInt(_newId);
                    System.out.println("checking for valid OV : \n" + checkValidODForStaff);
                    db.setQuery(checkValidODForStaff);
                    if(db.getRowCount() > 0){ //this means the row to be edited is for that staff and the overdraft is still valid
                        String makeOVunpaid = "update njiedb.overdraft set njiedb.overdraft.pay_status = " + false 
                                + " where njiedb.overdraft.ov_id = " + db.getValueAt(0, 0);
                        String deleteOVExp = "delete from njiedb.expenses where njiedb.expenses.TRANS_ID = " + this.idToEditTransactions;
                        System.out.println("valid edit: \n" + makeOVunpaid + "\n" + deleteOVExp);
                        try {
                            db.setQuery(makeOVunpaid);
                            db.setQuery(deleteOVExp);
                            manViewExpTrans(null);
                        } catch (SQLException | IllegalStateException ex) {
                            Dialogs.create().masthead(ex.getMessage()).message("there was an error while updating the status of the overdraft. Please try again" ).showError();
                        }
                    }else{//maybe the staff idToEditTransactions is wrong or the overdraft has expired
                       Dialogs.create().masthead("wrong details").message("Either the name is wrong or the overdraft has expired.").showInformation();
                    }
                    
                    break;
                default:
                    System.out.println("Wrong option. trying to hack. sorry");
            }
            
            
        }else{
            Dialogs.create().message("Operation Canceled").showWarning();
        }
        
        enableAfterSpecialEdit();
    }

    @FXML
    private void cancelSpecialTransEditProcess(ActionEvent event) {
        enableAfterSpecialEdit();
    }
    

    @FXML
    private void printSupDebtPaymntReport(MouseEvent event) {
        print.supCust_PayDebt(supplierDebtPaymentTable, supCode_forPayment_col, reports_SU_debtPayment, 
                              reports_SU_debtPaymentDate, Long.parseLong(totalSupplierAmountPayment.getText()), "Supplier Debt Payment Report", "SupplierDebtPayment");
    }

    @FXML
    private void printCustDebt(MouseEvent event) {
        print.supCust_DebtIssue(CustumerFinancialRecprdTable, reports_CU_name, reports_CU_debtAmt,  
                                 Long.parseLong(totalCustumerDebt.getText()), "Custumer Debts Report", "CustumerDebt");
    
        //want to test the printer class.
//        String[] tHeading = {"Custumer Name", "Total Amount Owed"};
//        PrinterClass pr;
//        pr = new PrinterClass();
//        pr.print(CustumerFinancialRecprdTable, reports_CU_name, 
//                reports_CU_debtAmt, "test report file name", "test report title", tHeading);
    
    
    }
    
    
    @FXML
    private void printSupIssueDebtReport(MouseEvent event) {
        print.supCust_DebtIssue(SupplierFinancialRecprdTable, reports_SU_supName, reports_SU_amtOwed, 
                                 Long.parseLong(totalSupplierAmoutOwing.getText()), "Supplier Debts Reports", "SupplierDebt");
    } 

    @FXML
    private void printCustPay(MouseEvent event) {
        print.supCust_PayDebt(repCustPayTable, repCustPay_name_col, repCustPay_debtPayment_col, 
                              repCustPay_paymentDate_col, Long.parseLong(totalCustumerDebtPayment.getText()), "Custumer Debt payment Report", "CustumerDebtPayment");
     }

    

    @FXML
    private void printStaffBalSalary(MouseEvent event) {
        print.staffBalanceSalary(staffBalSalaryTable, staffBalSalary_Name_col, staffBalSalary_Branch_col, 
                              staffBalSalary_balSal_col, Long.parseLong(totalUnpaidSalary.getText()));
    }

    @FXML
    private void printStaffPayment(MouseEvent event) {
        print.staffSalaryPayments(report_staff_table, reports_ST_name, reports_ST_monthlySalary, reports_ST_salaryPaid, 
                                reports_ST_salaryPayDate, Long.parseLong(totalSalaryPaid.getText()));
    }
    
    @FXML
    public void printOverdraft(MouseEvent event){
        print.overdraft(report_staffOverdraft_table, reports_OV_name, reports_OV_amt, reports_OV_monthlyDeduction, reports_OV_dateIssued, reports_OV_startDate, reports_OV_ExpiryDate, reports_OV_payStatus, reports_OV_ExpStatus, Long.parseLong(totalOverdraftAmount.getText()), Long.parseLong(totalMonthlyDeduction.getText()));
    }
    
    /**** small receipt printing start here. *****/
    
    
    ////////////////    fotso's work ///////////////////////////////
    
    
    /* this is the Filtering of  the STAFF PAYROLL
        FINANCIAL RECORD USED TO SELECT A SPECIFIC
        NUMBER OF ITEMS ON THE TABLE!!!!
    
        ** THE FUNCTION STARTS HERE **
    */
   /* ------------------------------------------------------------------------------- FOTSO FONO KEVIN LARRY -----------------------------------------------------------*/
   /* ===========================================================================CODE STARTS HERE ======================================================================*/
     @FXML
    private void StaffReportFilter(MouseEvent event) throws ParseException, SQLException {
     /*   try {
            staffR = new StaffReports();
        } catch (SQLException ex) {
            Logger.getLogger(ReportsController.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        String st = null;
        String en = null;
        String query;
        int    id = 0;
        
        if(rep_staffstaffIdSearch.getValue()!= null){
             /*ComboItem staffNameFilter = (ComboItem) rep_staffstaffIdSearch.getSelectionModel().getSelectedItem();
            idToEditTransactions = Integer.parseInt(staffNameFilter.getValue());*/
            dba.setQuery((query ="select staff_id from njiedb.staff where name = '" + rep_staffstaffIdSearch.getValue() + "'"));
            System.out.println("****************888THIS IS THE QUERY ***********************8 " + query);
            if(dba.getRowCount()>0){
                id = Integer.parseInt(dba.getValueAt(0, 0).toString());
                System.out.println("---------------------********____________" + id);
            }
        }
        try {
            st = formatter.format(formatter.parse(this.rep_staffStartDateSearch.getValue().toString()));
        } catch (Exception exception) {
        }
         
        try {
            en = formatter.format(formatter.parse(this.rep_staffEndDateSearch.getValue().toString()));
        } catch (Exception exception) {
        }
   
        
        if(st != null && en != null && id != 0)
        {
         staffR.popStaff_Q = staffR.popStaff_Q + " And njieDB.STAFF.STAFF_ID = " + id 
                                                      + " And njieDB.PAYROLL.PAY_DATE >= '" + st 
                                                + "' And njieDB.PAYROLL.PAY_DATE <= '" + en + "'"  ;
         staffR.qEndWithStaffId = " And njieDB.MONTHLY_SALARY_PAYMENT.STAFF_ID = " + id + " ";
        }
        else if(id != 0 && st != null)
        {
           staffR.popStaff_Q = staffR.popStaff_Q + " And njieDB.STAFF.STAFF_ID = " + id 
                                                      + " And njieDB.PAYROLL.PAY_DATE >= '" + st + "'"  ;
           staffR.qEndWithStaffId = " And njieDB.MONTHLY_SALARY_PAYMENT.STAFF_ID = " + id + " " ;
        }
        else if(id != 0 && en != null)
        {
            staffR.popStaff_Q = staffR.popStaff_Q + " And njieDB.STAFF.STAFF_ID = " + id 
                                                + "' And njieDB.PAYROLL.PAY_DATE <= '" + en + "'"  ;
            staffR.qEndWithStaffId = " And njieDB.MONTHLY_SALARY_PAYMENT.STAFF_ID = " + id + " ";
        }
        else if(st != null && en != null)
        {
             staffR.popStaff_Q = staffR.popStaff_Q + " And njieDB.PAYROLL.PAY_DATE >= '" + st 
                                                      + "' And njieDB.PAYROLL.PAY_DATE <= '" + en + "'"  ;
        }
        else if(id != 0)
        {
           
            staffR.popStaff_Q = staffR.popStaff_Q  + " And njieDB.STAFF.STAFF_ID = " + id ;
            staffR.qEndWithStaffId = " And njieDB.MONTHLY_SALARY_PAYMENT.STAFF_ID = " + id + " " ;
        }
        else if(st != null)
        {
             staffR.popStaff_Q = staffR.popStaff_Q + " And njieDB.PAYROLL.PAY_DATE >= '" + st + "'";
        }
        else if(en != null)
        {
            staffR.popStaff_Q = staffR.popStaff_Q + " And njieDB.PAYROLL.PAY_DATE <= '" + en + "'";
        }
        else{
            report_staffPayroll(event);
            return;
        }
        
        filterStaffBranches(id);
            // printing the filtered information on the table
        try {
                this.staffR.displayStaffPayrollReports(report_staff_table, reports_ST_StaffID, reports_ST_name, 
                                                reports_ST_monthlySalary, reports_ST_salaryPaid, 
                                                reports_ST_salaryPayDate);
                // filterAmountLeft(st,en, idToEditTransactions);
            } catch (SQLException ex) {
                Logger.getLogger(ReportsController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
            //clear the name combo box
            rep_staffstaffIdSearch.getSelectionModel().clearSelection();
            rep_staffstaffIdSearch.setValue(null);
            
            //clear the start date picker
            rep_staffStartDateSearch.getEditor().clear();
            rep_staffStartDateSearch.setValue(null);
            
            //clear the end date picker
            rep_staffEndDateSearch.getEditor().clear();
            rep_staffEndDateSearch.setValue(null);
            
            this.totalMonthlyDeduction.setText(String.valueOf(stuff.getTotalFromDisplayedTable(report_staff_table, reports_ST_monthlySalary)));
            this.totalSalaryPaid.setText(String.valueOf(stuff.getTotalFromDisplayedTable(report_staff_table, reports_ST_salaryPaid)));
        
            
    }
    
    public void loadStaffCodeCombox(ComboBox comboId) throws SQLException
    {
        try {
            comboId.getItems().clear();//remove all elements from the list
        } catch (Exception e) {}
        String getData = "SELECT njieDB.STAFF.STAFF_ID, njieDB.STAFF.NAME FROM njieDB.STAFF";
        try {
            db.setQuery(getData);
            clearArrayList();
            if (db.getRowCount() > 0) {
                for (int i = 0; i <= db.getRowCount(); i++) {
                    String code = db.getValueAt(i, 0).toString();
                    String name = db.getValueAt(i, 1).toString();
                    //fill it to the combo box
                    comboId.getItems().add(new ComboItem(name, code));
                    System.out.printf(" %s\n", name);
                    ComboValue.add(i, name);
                    ComboKey.add(i, code);
                }
                //comboId.getSelectionModel().selectFirst();
                System.out.println("Finished loading the combo box for the supplier's code");
                AutoCompleteComboBoxListener autocom = new AutoCompleteComboBoxListener<>(comboId);
            }
        } catch (SQLException | IllegalStateException sQLException) {
        }
    }
    
    public void loadSupplierCodeCombox(ComboBox comboId) throws SQLException
    {
        try {
            comboId.getItems().clear();//remove all elements from the list
        } catch (Exception e) {}
        String getData = "SELECT njieDB.SUPPLIER.SUP_CODE, njieDB.SUPPLIER.NAME FROM njieDB.SUPPLIER";
        db.setQuery(getData);
        clearArrayList();
        if(db.getRowCount() > 0){
            for(int i = 0 ; i <= db.getRowCount() ; i++){
                String code = db.getValueAt(i, 0).toString();
                String name = db.getValueAt(i, 1).toString();
                //fill it to the combo box
                comboId.getItems().add(new ComboItem(name, code));
                System.out.printf(" %s\n", name);
                ComboValue.add(i, name);
                ComboKey.add(i, code);
            }
            //comboId.getSelectionModel().selectFirst();
            
            System.out.println("Finished loading the combo box for the supplier's code");
        }
    AutoCompleteComboBoxListener autocom = new AutoCompleteComboBoxListener<>(comboId);
    }
    
    public void loadCustumerCodeCombox(ComboBox comboId) throws SQLException
    {
        try {
            comboId.getItems().clear();//remove all elements from the list
        } catch (Exception e) {}
        String getData = "SELECT njieDB.CUSTUMER.CUST_ID, njieDB.CUSTUMER.NAME FROM njieDB.CUSTUMER";
        try {
            db.setQuery(getData);
            clearArrayList();
            if (db.getRowCount() > 0) {
                for (int i = 0; i <= db.getRowCount(); i++) {
                    String code = db.getValueAt(i, 0).toString();
                    String name = db.getValueAt(i, 1).toString();
                    //fill it to the combo box
                    comboId.getItems().add(new ComboItem(name, code));
                    System.out.printf(" %s\n", name);
                    ComboValue.add(i, name);
                    ComboKey.add(i, code);
                }
                //comboId.getSelectionModel().selectFirst();
                System.out.println("Finished loading the combo box for the CUSTUMER's code");
            }            
        } catch (SQLException | IllegalStateException sQLException) {
        }
        AutoCompleteComboBoxListener autocom = new AutoCompleteComboBoxListener<>(comboId);
    }
    
    public void filterStaffBranches(int id){
        
        if(id != 0){
        staffR.Qstaff_salaryAndName = staffR.Qstaff_salaryAndName + " where njieDB.STAFF.STAFF_ID = " + id ;
            System.out.println("========================This is the statement=================== " + staffR.Qstaff_salaryAndName);
             long totBal;
    
        try {
            this.staffR.qBranch = "and njieDB.STAFF.BRANCH = ";
            totBal = this.staffR.displayBalancePayroll(staffBalSalaryTable, staffBalSalary_Name_col, staffBalSalary_Branch_col, staffBalSalary_balSal_col);
            totalUnpaidSalary.setText(String.valueOf(totBal));
            this.staffR.qBranch = "";
        } catch (ParseException parseException) {
        } catch (SQLException sQLException) {
        }
       }
    }
        
    @FXML
    public void filterStaffBranch() {
        System.out.println("filtering staff balance by branch.");
        String branch = null;
        if (staffBranchForFiltering.getValue() != null) {
            ComboItem staffNameFilter = (ComboItem) staffBranchForFiltering.getSelectionModel().getSelectedItem();
            branch = staffNameFilter.getValue();
            staff.Qstaff_salaryAndName = staff.Qstaff_salaryAndName + " where njieDB.STAFF.BRANCH = '" + branch + "'";
            long totBal;
            System.out.println("the branch to filter is = " + branch);
            try {
                this.staffR.qBranch = " and njieDB.STAFF.BRANCH = '" + branch + "' ";
                totBal = this.staff.displayBalancePayroll(staffBalSalaryTable, staffBalSalary_Name_col, staffBalSalary_Branch_col, staffBalSalary_balSal_col);
                totalUnpaidSalary.setText(String.valueOf(totBal));
            } catch (ParseException parseException) {
            } catch (SQLException sQLException) {
            }
        }
        this.totalMonthlyDeduction.setText(String.valueOf(stuff.getTotalFromDisplayedTable(report_staff_table, reports_ST_monthlySalary)));
        this.totalSalaryPaid.setText(String.valueOf(stuff.getTotalFromDisplayedTable(report_staff_table, reports_ST_salaryPaid)));
        this.staffR.qBranch = "";
    }
     
    
  /* ================================================================================== CODE ENDS HERE =====================================================================*/

     //managing array list in this section
    
    public void clearArrayList(){
        for(int i = 0; i < ComboValue.size(); i++){
            ComboKey.remove(i);
            ComboValue.remove(i);
        }
    }
    
    public String getKey(String value){
        displayList();
        for(int i = 0; i < ComboValue.size(); i++){
            if(ComboValue.get(i).equals(value)){
                return ComboKey.get(i);
            }
        }
        Dialogs.create().title("invalid").masthead(value).message("Invalid entry. Try again.").showError();
        return null;
    }
    
    public void displayList(){
        for(int i = 0 ; i < ComboValue.size(); i++){
            System.out.println("value =" + ComboValue.get(i));
            System.out.println("key ===" + ComboKey.get(i));
        }
    }

    @FXML
    private void EscapeFromSpecialTransEdit(KeyEvent event) {
        if(stuff.escape(event)){
            enableAfterSpecialEdit();
        }
    }

    @FXML
    private void cancleEditGeneralTransactionEditTextField(KeyEvent event) {
        if(stuff.escape(event)){
            report_genTrans_table.setDisable(false);
            editDescriptionAndAmountOnGeneralTransactionTablePane.setVisible(false);
        }
    }

    
    
    int totalNumOfIssuesUnpaid;
    public void getInfoOnSupPayIssueOrCustDebt(String query) throws SQLException{
        //attempt to remove everything.
        for(int i = 0; i < issueId.size(); i++){
            this.issueId.remove(i);
            this.issuedAmt.remove(i);
        }    
        
        System.out.println("populating the arraylist \n" + query );
        db.setQuery(query);
        this.totalNumOfIssuesUnpaid = db.getRowCount();
        for(int i = 0; i < db.getRowCount(); i++){
            this.issueId.add(i, db.getValueAt(i, 0).toString());
            this.issuedAmt.add(i, db.getValueAt(i, 1).toString());
        }
    }
    
    public long getTotalSupCustPayIssueAmt(){
        long sum = (long)0;
        for(int i = 0; i < this.totalNumOfIssuesUnpaid; i++){
            sum += Long.parseLong(this.issuedAmt.get(i));
        }
        return sum;
    }
}
