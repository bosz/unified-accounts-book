/*
 * To change this license header, choose License Headers in Project Properties.
 * By Fongoh Martin Tayong
fonoghmartin@gmail.com
 * and open the template in the editor.
 */

package njieaccount;

import Reports.StaffReports;
import Tables.SimpleString;
import Tables.TabClass;
import framework.ScreensController;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Formatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import org.controlsfx.control.action.Action;
import org.controlsfx.dialog.Dialog;
import org.controlsfx.dialog.Dialogs;

/**
 * FXML Controller class
 *
 * @author root
 */
public class recentNjieController implements Initializable {
    
    
    /*creating observation list to be used to formulate the table data*/
    /*ObservableList<IncomeTable> incTableData = null;
    ObservableList<ExpensesTable> expTableData = null;
    ObservableList<TransClass> transClass;*/
    
    ObservableList OLInccode, OLIncname, OLIncmemo, OLIncamount;
    
    ObservableList OLExpcode, OLExpname, OLExpmemo, OLExpamount;
    
    ObservableList OLcodeTC, OLnameTC, OLdescTC, OLusageTC;
    
    ObservableList<TableClass> composeIncome;
    ObservableList<TableClass> composeExpenseIncome;
    ObservableList<TransClass> composeTransClass;
    
    ArrayList ArrayLInccode = new ArrayList();
    ArrayList ArrayLIncname = new ArrayList();
    ArrayList ArrayLIncmemo = new ArrayList();
    ArrayList ArrayLIncamount = new ArrayList();
    
    ArrayList ArrayLExpcode = new ArrayList();
    ArrayList ArrayLExpname = new ArrayList();
    ArrayList ArrayLExpmemo = new ArrayList();
    ArrayList ArrayLExpamount = new ArrayList();
    
    ArrayList ArrayLcodeTC = new ArrayList();
    ArrayList ArrayLnameTC = new ArrayList();
    ArrayList ArrayLdescTC = new ArrayList();
    ArrayList ArrayLusageTC = new ArrayList();
    
    
    
    
    @FXML
    private Pane pane_add_income_transaction;
    @FXML
    private Pane pane_add_expendure_transaction;
    @FXML
    private Pane welcome_pane;
    @FXML
    private Pane work_space;
    
    @FXML
    private Button income_add_transaction_main_button;
    @FXML
    private Button expenditure_add_transaction_main_button;
    @FXML
    private TableView<TableClass> income_table;
    @FXML
    private TableView<TableClass> expenditure_table;
    @FXML
    private Button close_daily_transaction;
    @FXML
    private Hyperlink quit;
    private Label status_income;
    private Label status_expenditure;

    
    
    @FXML
    private TextField input_income_memo;
    @FXML
    private TextField input_income_amount;
    @FXML
    private ComboBox<ComboItem> input_income_code;
    
    
    @FXML
    private TextField input_expenditure_memo;
    @FXML
    private TextField input_expenditure_amount;
    @FXML
    private ComboBox<ComboItem> input_expenditure_code;
    
    @FXML
    private TableColumn<TableClass, String> col_inc_code;
//    @FXML
//    private TableColumn<TableClass, String> col_inc_name;
    @FXML
    private TableColumn<TableClass, String> col_inc_memo;
    @FXML
    private TableColumn<TableClass, String> col_inc_amount;
    
    @FXML
    private TableColumn<TableClass, String> col_exp_code;
//    @FXML
//    private TableColumn<TableClass, String> col_exp_name;
    @FXML
    private TableColumn<TableClass, String> col_exp_memo;
    @FXML
    private TableColumn<TableClass, String> col_exp_amount;
    
    @FXML
    private Label total_income_up;
    @FXML
    private Label total_income_down;
    @FXML
    private Label total_expenditure_down;
    @FXML
    private Label balance;
    @FXML
    private Button purchases_add_transaction_main_button;
    @FXML
    private Button payroll_add_transaction_main_button;
    @FXML
    private Button overdraft_add_transaction_main_button1;
    @FXML
    private Pane pane_add_purchase_expense_Transaction;
    @FXML
    private ComboBox<ComboItem> input_supplierID_supplierPartPayment;
    @FXML
    private Pane pane_toMake_supplierPartPayment;
    @FXML
    private Pane pane_add_payroll_expense_Transaction;
    @FXML
    private Pane pane_payrollFor_aStaff;
    @FXML
    private TextField input_payrollAmtToCollect;
    @FXML
    private Label label_payroll_balance;
    @FXML
    private TextField input_staffID_payroll;
    @FXML
    private Pane pane_add_overdraft_expense_Transaction;
    @FXML
    private Pane valid_overdraft_pane;
    @FXML
    private TextField input_staffName_overdraft;
    @FXML
    private Pane admin_space;
    @FXML
    private Pane manager_addTransaction;
    private Pane manager_editTransaction;
    private Pane manager_deleteTransaction;
    @FXML
    private Pane manager_viewTransactions;
    @FXML
    private Pane manager_default_display;
    @FXML
    private Pane manager_addSupplier;
    private Pane manager_editSupplier;
    private Pane manager_deleteSupplier;
    @FXML
    private Pane manager_viewSupplier;
    private Pane manager_defaultSupplier;
    @FXML
    private Pane manager_Supplier_issuePayment;
    @FXML
    private TextField usernameTxtFld;
    @FXML
    private PasswordField passwordFld;
    
    DatabaseHelper db, db2;
    Checks chek;
    SimpleDateFormat ft, dateOnly, tim;
    Date dNow;
    ManagerReport mreport;
    UsefullStuff usefullstuff;
    TabClass tab;
    
    
    
    //ManageComboBox combo;
    @FXML
    private TextField add_supplierCodeTxtFld;
    @FXML
    private TextField add_supplierNameTxtFld;
    @FXML
    private Label addSupplier_response;
    @FXML
    private ComboBox<ComboItem> supplierCode_issuePayCmbox;
    @FXML
    private TextField issuePay_amtTxtFld;
    @FXML
    private ComboBox<ComboItem> input_supplierName_supplierPayment;
    @FXML
    private Label amt_alreadyPait_toSupplier;
    @FXML
    private Label maxAmt_toPay_supplier;
    @FXML
    private TextField input_paying_supplierPayment;
    @FXML
    private TextField addTransClassCodeTxtFld;
    @FXML
    private TextField addTransClassNameTxtFld;
    @FXML
    private TextArea addTransClassDescTxtA;
    @FXML
    private ComboBox<ComboItem> addTransClassTypeCombx;
    @FXML
    private Label addTransClassResponse;
    @FXML
    private TableView<TransClass> viewTransClass_table;
    @FXML
    private TableColumn viewTransClass_Tcode;
    @FXML
    private TableColumn viewTransClass_Tname;
    @FXML
    private TableColumn viewTransClass_Tdesc;
    @FXML
    private TextArea add_supplierServiceTxtFld;
    @FXML
    private TextField addStaff_codeTxtFld;
    @FXML
    private TextField addStaff_nameTxtFlx;
    @FXML
    private ComboBox<ComboItem> addStaff_branchCmbBx;
    @FXML
    private Label addStaff_response;
    @FXML
    private TextField addStaff_salaryTxtFlx;
    @FXML
    private TextField addStaff_phoneTxtFlx1;
    @FXML
    private Pane manager_addStaff;
    @FXML
    private Pane manager_defaultStaff;
    @FXML
    private Pane manager_viewStaff;
    @FXML
    private TableView<SimpleString> viewStaff_table;
    @FXML
    private TableColumn<SimpleString, String> viewStaff_code_col;
    @FXML
    private TableColumn<SimpleString, String> viewStaff_name_col;
    @FXML
    private TableColumn<SimpleString, String> viewStaff_phone_col;
    @FXML
    private TableColumn<SimpleString, String> viewStaff_salary_col;
    @FXML
    private TableColumn<SimpleString, String> viewStaff_branch_col;
    private Pane report_genTrans_D;
    @FXML
    private Label report_staff_D;
    @FXML
    private Pane report_supplier_D;
//    
    @FXML
    private Pane report_custumer_D;
    @FXML
    private Label vInvExp;
    private Label repGTIncExp;
    @FXML
    private Pane pane_custumer_pay_expense_Transaction;
    @FXML
    private Pane pane_toReceive_debtPayment;
    @FXML
    private Label debt_output_debtAmtPaid;
    @FXML
    private Label debt_output_balanceDebtAmt;
    @FXML
    private TextField debt_ouIn_balanceDebt;
    @FXML
    private ComboBox<ComboItem> input_supplierName_supplierPayment1;
    @FXML
    private Button income_custumer_transaction_main_button;
    @FXML
    private Label pay_suplierID;
    @FXML
    private Button payPrintSupplier;
    @FXML
    private Pane manager_IssueOverdraft_Pane;
    @FXML
    private TextField input_OD_staffName;
    @FXML
    private TextField input_OD_Amount_TxtFld;
    @FXML
    private TextField input_OD_monthlyDeduction_TxtFld;
    @FXML
    private Pane manager_viewOverdraft_Pane;
    @FXML
    private TableView<TableClass> viewOverdraft_table;
    @FXML
    private TableColumn<TableClass, String> viewOverdraft_StaffCode_col;
    @FXML
    private TableColumn<TableClass, String> viewOverdraft_AmountIssued_col;
    @FXML
    private TableColumn<TableClass, String> viewOverdraft_DateIssued_col;
    @FXML
    private TableColumn<TableClass, String> viewOverdraft_ExpiryDate_col;
    @FXML
    private TableColumn<TableClass, String> viewOverdraft_Status_col;
    @FXML
    private Pane manager_defaultOverdraft;
    @FXML
    private Label OD_output_staffID;
    @FXML
    private Label OD_output_staffName;
    @FXML
    private Label OD_output_AmtToPay;
    @FXML
    private Label OD_output_monthlyDeduction;
    @FXML
    private Label OD_output_deductUntil;
    @FXML
    private Label PayRoll_output_staffID;
    @FXML
    private Label PayRoll_output_staffName;
    @FXML
    private Label PayRoll_output_monthlySalary;
    @FXML
    private Label PayRoll_output_ovMontlyDeduction;
    @FXML
    private Pane manager_addCustumer;
    @FXML
    private TextField add_CustumerCodeTxtFld;
    @FXML
    private TextField add_CustumerNameTxtFld;
    @FXML
    private TextArea add_CustumerServiceTxtFld;
    @FXML
    private Pane manager_editCustumer;
    private Pane manager_deleteCustumer;
    @FXML
    private Pane manager_viewCustumer;
    @FXML
    private Pane manager_defaultCustumer;
    @FXML
    private Pane manager_Custumer_issueDebt;
    @FXML
    private ComboBox<ComboItem> CustumerCode_issueDebtCmbox;
    @FXML
    private TextField issueDebt_amtTxtFld;
    @FXML
    private Label debt_output_custId;
    @FXML
    private Label debt_output_TotalDebtAmt;
    @FXML
    private Label pay_and_getBal_debt;
    @FXML
    private TextField input_EDIT_staff_staffID;
    @FXML
    private TextField input_EDIT_staff_staffName;
    @FXML
    private ComboBox<?> input_EDIT_staff_branch;
    @FXML
    private TextField input_EDIT_staff_salary;
    @FXML
    private TextField input_EDIT_staff_phoneNumber;
    @FXML
    private Pane miniPaneForStaffEdit;
    @FXML
    private Pane miniPaneForTransClass;
    @FXML
    private TextField input_EDIT_transClass_Desc;
    @FXML
    private TextField input_EDIT_transClass_transName;
    @FXML
    private TextField input_EDIT_transClass_transID;
    @FXML
    private ImageView cancel_add_income_button;
    @FXML
    private Button add_income_transaction_button;
    @FXML
    private ComboBox<?> input_supplierID_supplierPartPayment1;
    @FXML
    private ImageView cancel_add_expenditure_button12;
    @FXML
    private ImageView cancel_add_expenditure_button;
    @FXML
    private Button add_expenditure_transaction_button;
    @FXML
    private Button searchSup;
    @FXML
    private Label supplierPayment_balace;
    @FXML
    private ImageView cancel_add_expenditure_button1;
    @FXML
    private ImageView cancel_add_expenditure_button11;
    @FXML
    private Label PayRoll_output_salaryAmtCollected;
    @FXML
    private Button pay_employee_button;
    @FXML
    private Button search_payroll_button;
    @FXML
    private ComboBox<?> input_staffName_payroll;
    @FXML
    private ImageView cancel_add_expenditure_button111;
    @FXML
    private TextField input_staffID_overdraft;
    @FXML
    private Button payPrint_overdraft;
    @FXML
    private Tab activate_defaultManager_transaction;
    @FXML
    private Group infoEditingGroup1;
    @FXML
    private Button close_daily_transaction1;
    @FXML
    private Button close_daily_transaction13;
    @FXML
    private Group infoEditingGroup;
    @FXML
    private Tab activate_defaultManager_transaction1;
    @FXML
    private TableColumn<SupTable, String> col_sup_code;
    @FXML
    private TableColumn<SupTable, String> col_supName;
    @FXML
    private Button close_daily_transaction11;
    @FXML
    private Button close_daily_transaction131;
    @FXML
    private Label addStaff_response1;
    @FXML
    private Tab activate_defaultManager_transaction11;
    @FXML
    private Label addSupplier_response1;
    @FXML
    private Pane report_genTrans_D1;
    @FXML
    private TableColumn<?, ?> viewStaff_code_col11;
    @FXML
    private TableColumn<?, ?> viewStaff_name_col11;
    @FXML
    private TableColumn<?, ?> viewStaff_phone_col11;
    @FXML
    private TableColumn<?, ?> viewStaff_salary_col11;
    @FXML
    private TableColumn<?, ?> viewStaff_branch_col11;
    @FXML
    private TableView<?> viewStaff_table12;
    @FXML
    private TableColumn<?, ?> viewStaff_code_col12;
    @FXML
    private TableColumn<?, ?> viewStaff_name_col12;
    @FXML
    private TableColumn<?, ?> viewStaff_phone_col12;
    @FXML
    private TableColumn<?, ?> viewStaff_salary_col12;
    @FXML
    private TableColumn<?, ?> viewStaff_branch_col12;
    @FXML
    private TableView<?> viewStaff_table13;
    @FXML
    private TableColumn<?, ?> viewStaff_code_col13;
    @FXML
    private TableColumn<?, ?> viewStaff_name_col13;
    @FXML
    private TableColumn<?, ?> viewStaff_phone_col13;
    @FXML
    private TableColumn<?, ?> viewStaff_salary_col13;
    @FXML
    private TableColumn<?, ?> viewStaff_branch_col13;
    @FXML
    private TableView<SupTable> viewSuppierInfo;
    @FXML
    private TableColumn<SupTable, String> col_supServices;
    @FXML
    private Group infoEditingGroup11;
    @FXML
    private TextField input_EDIT_supplier_service;
    @FXML
    private TextField input_EDIT_supplier_Name;
    @FXML
    private TextField input_EDIT_supplier_ID;
    @FXML
    private Pane miniPaneForSupplier;
    @FXML
    private TableView<TableClass> view_custumerTable;
    @FXML
    private TableColumn<TableClass, String> view_custumerTable_codeCol;
    @FXML
    private TableColumn<TableClass, String> view_custumerTable_nameCol;
    @FXML
    private TableColumn<TableClass, String> view_custumerTable_prefServCol;
    @FXML
    private Group infoEditingGroup111;
    @FXML
    private TextField input_EDIT_custumer_prefServices;
    @FXML
    private TextField input_EDIT_custumer_Name;
    @FXML
    private TextField input_EDIT_custumer_ID;
    @FXML
    private Pane miniPaneForCustumer;
    @FXML
    private Button close_daily_transaction121;
    @FXML
    private TableView<?> payrollReportTable;
    @FXML
    private TableColumn<?, ?> viewStaff_branch_col111;
    @FXML
    private TableColumn<?, ?> viewStaff_salary_col111;
    @FXML
    private ComboBox<?> report_payrollStaffIdCmBx;
    
    public static ScreensController reportsContainer;
    public static String REPORTS_ID = "reports";
    public static String REPORTS_FXML = "/njieaccount/reports.fxml";
    
//    public static ScreensController accountsContainer;
//    public static String ACCOUNTS_ID = "accounts";
//    public static String ACCOUNTS_FXML = "/njieaccount/accounts.fxml";
    
    public static ScreensController suspensionContainer;
    public static String SUSPENSION_ID = "salarySuspension";
    public static String SUSPENSION_FXML = "/salarySuspension/suspendSalary.fxml";
    
    @FXML
    private AnchorPane reportsAnchor;
    @FXML
    private DatePicker startDeductionDate;
    @FXML
    private TableColumn<?, ?> viewOverdraft_deductionStartDate_col;
    StaffReports staffRep;
    
    //array lists to hold the value and key inputed from the combobox.
    ArrayList<String> ComboValue;
    ArrayList<String> ComboKey;
    @FXML
    private Button btnDiplayViewSupplierIssuedPaymentsPane;
    private Pane view_issued_payments_pane;
    @FXML
    private Pane manaageSupplierEditPane;
    @FXML
    private TableView<TableClass> manageSuppliertableView;
    @FXML
    private TableColumn<TableClass, String> manageSupplierIDColumn;
    @FXML
    private TableColumn<TableClass, String> manageSupplierAmountColumn;
    @FXML
    private TableColumn<TableClass, String> manageSupplierDateColumn;
    @FXML
    private TableColumn<TableClass, String> manageSupplierNameColumn;
    @FXML
    private Button btnEditmanageSupplierInfo;
    @FXML
    private Pane editSupplierAmountAndName;
    @FXML
    private TextField editmanageSupplierAmountTextField;
    @FXML
    private ComboBox<ComboItem> editmanageSupplierAmountComboBox;
    @FXML
    private Button btnSaveSupplierUpdateInfo;
    @FXML
    private Button btnCancelSupplierUpdate;
    @FXML
    private Label choosenSupplierLabel;
    @FXML
    private TableColumn<?, ?> viewOverdraft_OVCODE_col;
    @FXML
    private Button btneditOverdraftInfoFromTable;
    @FXML
    private Button btnDeleteOcerdraftInfoFromTable;
    @FXML
    private ComboBox<ComboItem> overdraftStaffNameComboBox;
    @FXML
    private TextField overdraftAmountTextField;
    @FXML
    private TextField overdraftMonthlydeductionTextField;
    @FXML
    private DatePicker overdraftdeductionStartDatePicker;
    @FXML
    private Button btnSaveOverdraftUpdateInfo;
    @FXML
    private Button btnCancelOverdraftUpdate;
    @FXML
    private Pane overDraftEditPane;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    SimpleDateFormat sdf;
    @FXML
    private Pane manager_EditCustumerIssuedDebtDisplayPane;
    @FXML
    private TableView<SimpleString> editCustumerDebtsTable;
    @FXML
    private TableColumn<SimpleString, String> editCustDebt_code;
    @FXML
    private TableColumn<SimpleString, String> editCustDebt_name;
    @FXML
    private TableColumn<SimpleString, String> editCustDebt_amt;
    @FXML
    private Pane editCustDebtMiniPane;
    @FXML
    private ComboBox<ComboItem> custNameForDebtEditingCmBx;
    @FXML
    private TextField debtAmtForCustDebtEdit;
    @FXML
    private AnchorPane suspensionAnchor;
    @FXML
    private TableColumn<?, ?> editCustDebt_date;
    @FXML
    private Label pay_SupplierName;
    @FXML
    private Label totalamtIssuedToSupplier;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        sdf = new SimpleDateFormat("yyyy-MM-dd");
       
        try {
            reportsContainer = new ScreensController(reportsAnchor);
            reportsContainer.loadScreen(REPORTS_ID, REPORTS_FXML);
            reportsContainer.setScreen(REPORTS_ID);
        } catch (Exception e) {
            Dialogs.create().message("error loading reports. U wont be able to see the reports").showInformation();
        }
        
        try {
            suspensionContainer = new ScreensController(suspensionAnchor);
            suspensionContainer.loadScreen(SUSPENSION_ID, SUSPENSION_FXML);
            suspensionContainer.setScreen(SUSPENSION_ID);
        } catch (Exception e) {
            Dialogs.create().message("error loading suspension page. U wont be able to make or alter any salary suspension\n" + e.getMessage()).showInformation();
        }
        
//        try {
//            accountsContainer = new ScreensController(accountsManagementAnchor);
//            accountsContainer.loadScreen(ACCOUNTS_ID, ACCOUNTS_FXML);
//            accountsContainer.setScreen(ACCOUNTS_ID);
//        } catch (Exception e) {
//            Dialogs.create().message("Error occured while loading the accounts management page").masthead("LOADING ERROR").showError();
//        }
       
        tim = new SimpleDateFormat("hh:mm:ss");
        try {
            usefullstuff = new UsefullStuff();
        } catch (Exception ex) {
            Logger.getLogger(NjieController.class.getName()).log(Level.SEVERE, null, ex);
        }
        /*initialise the current timestamp to be used*/
        ft = new SimpleDateFormat ("yyyy-MM-dd' 'hh:mm:ss'.000'");
        dateOnly = new SimpleDateFormat ("yyyy-MM-dd");
        
        ComboValue = new ArrayList<>();
        ComboKey = new ArrayList<>();
        try {
            // TODO
            mreport = new ManagerReport();
            //combo = new ManageComboBox();
            db = new DatabaseHelper();
            db2 = new DatabaseHelper();
            chek = new Checks();
            tab = new TabClass();
            staffRep = new StaffReports();
        } catch (SQLException ex) {
            Logger.getLogger(NjieController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(NjieController.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        
       
        
        //combo box for adding a new transaction class.
        addTransClassTypeCombx.setEditable(false);
        addTransClassTypeCombx.getItems().clear();
        addTransClassTypeCombx.getItems().add(new ComboItem("INCOME", "income"));
        addTransClassTypeCombx.getItems().add(new ComboItem("EXPENSE", "expense"));
        this.addTransClassTypeCombx.getSelectionModel().selectFirst();
        
        this.staffRep.displayStaffBranchCombo(addStaff_branchCmbBx);
        
        try {
            usefullstuff.checkforExpiryOverdraft();
        } catch (ParseException ex) {
            Logger.getLogger(NjieController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            chek.daysIncExpBal();
        } catch (SQLException | ParseException ex) {
            Logger.getLogger(NjieController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        //addition of suspension table
        /* String qAddSusTable = "create table njiedb.salary_suspension("
        + "id int not null GENERATED ALWAYS AS IDENTITY "
        + " (START WITH 1, INCREMENT BY 1), "
        + "sus_amt int not null, "
        + "staff_id int not null, "
        + "dat date not null, "
        + "primary key (id), "
        + "foreign key ( staff_id ) "
        + "references njiedb.staff ( staff_id ) "
        + "on delete no action "
        + "on update no action "
        + ")";
        try {
        db.setQuery("drop table njiedb.salary_suspension");
        db.setQuery(qAddSusTable);
        Dialogs.create().message("salary suspension table created succesfully ").showInformation();
        } catch (Exception e) {
        Dialogs.create().message("error adding salary suspension table " + e.getMessage()).showError();
        }*/
        
    }    

    
    /*--------------@validating the integer inputs----------------*/
  
    
    @FXML
    private void display_add_income_transaction_form(MouseEvent event) throws SQLException {
        pane_add_income_transaction.setVisible(true);
        System.out.println("checking :");
        chek.checkIncTransClass();
        System.out.println("loading inc trans class combo box");
        loadInctransCode();
        this.disableToMakeTransaction();
        expenditure_add_transaction_main_button.setDisable(true);
        
    }

    @FXML
    private void display_add_expenditure_transaction_form(MouseEvent event) throws SQLException {
        pane_add_expendure_transaction.setVisible(true);
        System.out.println("loading exp trans class combo box");
        loadExptransCode();
        this.disableToMakeTransaction();
        income_add_transaction_main_button.setDisable(true);
    }
    @FXML
    private void add_income_transaction_to_database(MouseEvent event) throws SQLException, ParseException {
        pane_add_income_transaction.setVisible(false);
        this.enableFromMakxTransaction();
        expenditure_add_transaction_main_button.setDisable(false);
        try {
            this.getInputIncomeTransaction();
            fillCashierIncomeExpenseTable("INCOME", col_inc_code, 
                                    col_inc_memo, col_inc_amount, income_table);
        } catch (SQLException sQLException) {
            Dialogs.create().title("Error").message("Problem occured while trying to adding tranaction. Please try again").showError();
        }
        
        
    }
    @FXML
    private void add_expenditure_transaction_to_database(MouseEvent event) throws SQLException, ParseException {
        pane_add_expendure_transaction.setVisible(false);
        this.enableFromMakxTransaction();
        income_add_transaction_main_button.setDisable(false);
        //status_expenditure.setText("Transaction done successfully");
        this.getInputExpenditureTransaction();
        System.out.println("Succesfull income transaction");
        try {
            fillCashierIncomeExpenseTable("EXPENSES", col_exp_code, 
                                    col_exp_memo, col_exp_amount, expenditure_table);
        } catch (SQLException sQLException) {
        }
    }

    private void close_daily_transaction(MouseEvent event) {
        System.out.println("ending the daily transaction");
    }

    @FXML
    private void quit_to_home(MouseEvent event) {
        welcome_pane.setVisible(true);
        work_space.setVisible(false);
    }
    
    
    private void disableToMakeTransaction()
    {
        income_table.setDisable(true);
        expenditure_table.setDisable(true);
        close_daily_transaction.setDisable(true);
        
        quit.setDisable(true);
        income_add_transaction_main_button.setDisable(true);
        expenditure_add_transaction_main_button.setDisable(true);
        purchases_add_transaction_main_button.setDisable(true);
        payroll_add_transaction_main_button.setDisable(true);
        overdraft_add_transaction_main_button1.setDisable(true); 
        income_custumer_transaction_main_button.setDisable(true);
    }
    
    private void enableFromMakxTransaction()
    {
        income_table.setDisable(false);
        expenditure_table.setDisable(false);
        close_daily_transaction.setDisable(false);
        
        quit.setDisable(false);
        income_add_transaction_main_button.setDisable(false);
        expenditure_add_transaction_main_button.setDisable(false);
        purchases_add_transaction_main_button.setDisable(false);
        payroll_add_transaction_main_button.setDisable(false);
        overdraft_add_transaction_main_button1.setDisable(false);
        income_custumer_transaction_main_button.setDisable(false);
    }
    
    private int debt_id ;
    private long balanceDebt, totalDebtamt, custumerBal;
    String custId;
    @FXML
    private void search_customerDebt(MouseEvent event) throws SQLException {
    /*working with the custumer debts taking sample 
        code from search_supplierPartPartPayment();*/
        long totalPaid = (long)0;
        String value = input_supplierName_supplierPayment1.getEditor().getText();
        custId = getKey(value);
        
        
        /*get the debtID and the amount form the debts table if the user has a debt*/
        String qGetTotalDebtAmount, qGetTotalPaidDebt;
        qGetTotalDebtAmount = "SELECT SUM(njieDB.DEBTS.AMOUNT) "
                + " FROM njieDB.DEBTS "
                + " where njieDB.DEBTS.CUST_ID = '" + custId + "'";
        System.out.println("getting total amout of debts issued \n " + qGetTotalDebtAmount);
        db.setQuery(qGetTotalDebtAmount);
        if(Integer.parseInt(db.getValueAt(0, 0).toString()) > 0 ){
            long debtAmount = Integer.parseInt(db.getValueAt(0, 0).toString());
            this.totalDebtamt = debtAmount;
            System.out.println("custumer has debt " + this.totalDebtamt);
            /*Now, get the amount already paid from njieDB.DEBT_PAYMENT TABLE*/
            
            qGetTotalPaidDebt = "select sum(njieDB.DEBT_PAYMENT.AMOUNT_PAID) "
                    + "FROM njieDB.DEBT_PAYMENT "
                    + "WHERE njieDB.DEBT_PAYMENT.CUST_ID = '" + custId + "' ";
            System.out.println("getting total amts paid on debts \n " + qGetTotalPaidDebt);
            db.setQuery(qGetTotalPaidDebt);
            
            try {
                if (db.getRowCount() > 0) {
                        if(db.getValueAt(0, 0) != null){
                             totalPaid = Long.parseLong(db.getValueAt(0, 0).toString());
                        }
                }
            } catch (IllegalStateException | NumberFormatException illegalStateException) {
                Dialogs.create().message(illegalStateException.getMessage()).showError();
            }
            
            System.out.println("total amt paid by custumer = " + totalPaid + "\n");
            
            this.custumerBal =totalDebtamt - totalPaid;
            if(custumerBal <= 0){
                //error
                Dialogs.create()
                        .title("0 frs to pay")
                        .message("custumer has already been paid full debts so no amount is dued to supplier")
                        .showInformation();
                return ;
            }
            
            /*bring pane and display information*/
            pane_toReceive_debtPayment.setVisible(true);
            debt_output_debtAmtPaid.setText(String.valueOf(totalPaid));
            debt_output_TotalDebtAmt.setText(String.valueOf(debtAmount));
            debt_output_balanceDebtAmt.setText(String.valueOf(this.custumerBal));
            debt_ouIn_balanceDebt.setText(String.valueOf(this.custumerBal));
            debt_output_custId.setText(custId);
            //pay_and_getBal_debt.setText("0");
            debt_ouIn_balanceDebt.setEditable(true);
        }
        else{
            Dialogs.create().title("No Debts").message("Custumer has no unsettled debt").showInformation();
        }
        
        
    }
    
//    @FXML
//    private void updateDebtPayBalance(KeyEvent event) {
//        long newBal = this.balanceDebt - Long.parseLong(debt_ouIn_balanceDebt.getText());
//        pay_and_getBal_debt.setText(String.valueOf(newBal));
//    }

    @FXML
    private void display_customer_income_transaction_form(MouseEvent event) throws SQLException {
        this.disableToMakeTransaction();
        expenditure_add_transaction_main_button.setDisable(true);
        pane_custumer_pay_expense_Transaction.setVisible(true);
        pane_toReceive_debtPayment.setVisible(false);
        /*Now, dispay the combo box staff name*/
        loadCustumerId(input_supplierName_supplierPayment1);
        
    }

    @FXML
    private void cancel_add_income(MouseEvent event) {
        pane_add_income_transaction.setVisible(false);
        this.enableFromMakxTransaction();
        expenditure_add_transaction_main_button.setDisable(false);
//        status_income.setText("Insertion Operation Cancelled");
    }

    @FXML
    private void cancel_add_expenditure(MouseEvent event) {
        //pane_add_expendure_transaction.setVisible(false);
        this.invisibleSubExpenses();
        this.enableFromMakxTransaction();
        income_add_transaction_main_button.setDisable(false);
//        status_expenditure.setText("Insertion Operation Cancelled");
        pane_payrollFor_aStaff.setVisible(false);
        this.searchPanesGoInvisible();
        
    }
    
    private void clearMessages()
    {
        status_expenditure.setText("");
        status_income.setText("");
    }

    @FXML
    private void display_standby_window(MouseEvent event) {
        //work_space.setDisable(true);
        work_space.setVisible(false);
    }
    
    private void loginFromStandby()
    {
        work_space.setDisable(true);
    }
    long totExp, totInc, incExpBal;
    
    
    private void getInputIncomeTransaction() throws SQLException, ParseException
    {
        String memo = input_income_memo.getText();  input_income_memo.setText("");
        int amount = Integer.parseInt(input_income_amount.getText());   input_income_amount.setText("");

        String value = input_income_code.getEditor().getText();
        int codeCmbx = Integer.parseInt(getKey(value));

        String name = input_income_memo.getText();  input_income_memo.setText("");
        
        dNow = new Date( );
        
        System.out.println(memo + amount + name);
        System.out.println("Get income : ");
        chek.checkIncome();
        String toDatabase;
        toDatabase = "INSERT INTO njieDB.INCOME (njieDB.INCOME.CODE,  "
                    +"njieDB.INCOME.MEMO, njieDB.INCOME.AMOUNT, njieDB.INCOME.DAT, njieDB.INCOME.TIM) VALUES ("
                    +" " + codeCmbx + ", '" + memo + "', " 
                    + amount + ", '" + dateOnly.format(dNow) +"', '" + tim.format(dNow) + "' )";
        System.out.println("Query: " + toDatabase);
        
        Action confirmer = Dialogs.create().
                    title("confirm transaction").
                    masthead("amount : " + amount + "\nmemo   : " + memo).
                    message("Add transaction to database?").showConfirm();
                   
        if (confirmer == Dialog.Actions.YES) {
        db.setQuery(toDatabase);
        //update displayed total income, expenditure and balance
        updateExpIncBal();
        Dialogs.create().title("success").message("transaction completed succesfully").showInformation();
        }else{
            Dialogs.create().message("Operation Canceled").showWarning();
        }
    }
    
    private void getInputExpenditureTransaction() throws SQLException, ParseException
    {
        String memo = input_expenditure_memo.getText(); input_expenditure_memo.setText("");
        int amount = Integer.parseInt(input_expenditure_amount.getText());  input_expenditure_amount.setText("");
        
        String CcodeCmbx = this.input_expenditure_code.getEditor().getText();
        int codeCmbx = Integer.parseInt(getKey(CcodeCmbx));
        
        //String name = input_expenditure_name.getText(); input_expenditure_name.setText("");
        System.out.println(memo + amount );
        
        //updateExpIncBal();
        if((chek.totExp + amount ) <= chek.totInc){
            dNow = new Date( );
            String toDatabase;
            toDatabase = "INSERT INTO njieDB.EXPENSES (njieDB.EXPENSES.CODE, "
                        +"njieDB.EXPENSES.MEMO, njieDB.EXPENSES.AMOUNT, njieDB.EXPENSES.DAT, njieDB.EXPENSES.TIM) VALUES ("
                        +" " + codeCmbx + ", '" + memo + "', " 
                        + amount + ", '" +  dateOnly.format(dNow)  +"', '" + tim.format(dNow) + "' )";
            System.out.println("Query: " + toDatabase);
            Action confirmer = Dialogs.create().
                    title("confirm transaction").
                    masthead("amount : " + amount + "\nmemo   : " + memo).
                    message("Add transaction to database?").showConfirm();
                   
            if (confirmer == Dialog.Actions.YES) {
                db.setQuery(toDatabase);
                //update displayed total income, expenditure and balance
                updateExpIncBal();
                Dialogs.create().message("Transaction Succesfull").showInformation();
            }else{
            Dialogs.create().message("Deletion Canceled").showWarning();
            }
        }
        else{
             Dialogs.create().title("Income too small").message("The income at hand cannot be used for this transaction."
                       + "").showWarning();
        }
        
        
    }
    
    private void updateExpIncBal() throws SQLException, ParseException
    {
        chek.expIncBalance();
        chek.daysIncExpBal();
        total_income_down.setText(Long.toString(chek.todayTotInc));
        total_income_up.setText(Long.toString(chek.todayTotInc));
        total_expenditure_down.setText(Long.toString(chek.todayTotExp));
        balance.setText(Long.toString(chek.incExpBal));
        System.out.println("today bal = " + chek.todayBal 
                            + "total exp = " + chek.incExpBal);
    }
    
    private void invisibleSubExpenses()
    {
        pane_add_expendure_transaction.setVisible(false);
        pane_add_purchase_expense_Transaction.setVisible(false);
        pane_add_payroll_expense_Transaction.setVisible(false);
        pane_add_overdraft_expense_Transaction.setVisible(false);
    }
    
    @FXML
    private void display_paySupplier_expenditure_transaction_form(MouseEvent event) throws SQLException {
        try {
            loadSupplierName_forPayment();
            loadSupplierId_forPayment();    
        } catch (SQLException e) {
        }
        
        this.invisibleSubExpenses();
        this.disableToMakeTransaction();
        pane_add_purchase_expense_Transaction.setVisible(true);
        
    }

    @FXML
    private void display_add_payroll_expenditure_transaction_form(MouseEvent event) throws SQLException {
        this.invisibleSubExpenses();
        this.disableToMakeTransaction();
        pane_add_payroll_expense_Transaction.setVisible(true);
        /*load the combo box*/
        //loadStaffID_forPayroll(input_staffID_payroll);
    }

    @FXML
    private void display_add_overdraft_expenditure_transaction_form(MouseEvent event) throws SQLException {
        this.invisibleSubExpenses();
        this.disableToMakeTransaction();
        pane_add_overdraft_expense_Transaction.setVisible(true);
        valid_overdraft_pane.setVisible(false);
        
     }

    /*private int amountIssued, amountPaid, amountBalance;*/
    Long supplierBalance, supplierAmtToPay;
    int supply_transac_id, sup_id;
    String supplier_name;
    boolean updateOverdraft;
    
    @FXML   
    private void search_supplierPartPayment(MouseEvent event) throws SQLException {
        long totalPaid = (long)0;
        updateOverdraft=false;
        //chek.checkPayOrder();
        String cCode = this.input_supplierName_supplierPayment.getEditor().getText();
        sup_id = Integer.parseInt(getKey(cCode));
        
       
        System.out.println("supplier id : " + sup_id);
        
        String qGetSumOfAllOrders; //getting the pay_order and the amount(TOTAL)
        qGetSumOfAllOrders = "SELECT SUM(njieDB.SUPPLIER_PAY_ORDER.AMOUNT) "
                + " FROM njieDB.SUPPLIER_PAY_ORDER "
                + " WHERE njieDB.SUPPLIER_PAY_ORDER.SUPPLIER_CODE  = " + sup_id + " ";
//                + " AND njieDB.SUPPLIER_PAY_ORDER.STATUS = " + false;
        db.setQuery(qGetSumOfAllOrders);
        System.out.println("enquiries \n " + qGetSumOfAllOrders );
        //if the sum is more than 0, ie the supplier has a valid debs(s)
        if(Integer.parseInt(db.getValueAt(0, 0).toString()) > 0 ){//supplier has a valid payment in the system.
            //supply_transac_id = Integer.parseInt(db.getValueAt(0, 0).toString());
            long totalAmtIssued = Long.parseLong(db.getValueAt(0, 0).toString());
            System.out.println("total debt issued : " + totalAmtIssued);
            
            String qSumAllSuppPayments;
            qSumAllSuppPayments = "select sum(njieDB.SUPPLIER_PAY.AMOUNT_PAID) "
                    + "FROM njieDB.SUPPLIER_PAY "
                    + "WHERE njieDB.SUPPLIER_PAY.SUP_ID = " + sup_id + " ";
            db.setQuery(qSumAllSuppPayments);
            
            try {
                if (db.getRowCount() > 0) {
                        if(db.getValueAt(0, 0) != null){
                             totalPaid = Long.parseLong(db.getValueAt(0, 0).toString());
                        }
                }
            } catch (IllegalStateException | NumberFormatException illegalStateException) {
                Dialogs.create().message(illegalStateException.getMessage()).showError();
            }
            System.out.println("total amount paid = " + totalPaid + "\n");
            
            supplierAmtToPay =totalAmtIssued - totalPaid;
            if(supplierAmtToPay <= 0){
                //error
                Dialogs.create()
                        .title("0 frs to pay")
                        .message("supplier has already been paid full debts so no amount is dued to supplier")
                        .showInformation();
                return ;
            }
            
            /*get the supplier name to make the make of expense and the memo.*/
            String getSupplierName;
            getSupplierName = "SELECT njieDB.SUPPLIER.NAME FROM njieDB.SUPPLIER "
                    + " WHERE njieDB.SUPPLIER.SUP_CODE = " + sup_id ;
            System.out.println("getting the supplier name : " + getSupplierName);
            db.setQuery(getSupplierName);
            supplier_name = db.getValueAt(0, 0).toString();
           
            pane_toMake_supplierPartPayment.setVisible(true);
            pay_suplierID.setText(String.valueOf(sup_id));
            pay_SupplierName.setText(supplier_name);
            amt_alreadyPait_toSupplier.setText(String.valueOf(totalPaid));
            maxAmt_toPay_supplier.setText(String.valueOf(supplierAmtToPay));
            
            totalamtIssuedToSupplier.setText(String.valueOf(totalAmtIssued));
            payPrintSupplier.setDisable(false);
            input_paying_supplierPayment.setText(supplierAmtToPay.toString());
        }
        else{
            pane_toMake_supplierPartPayment.setVisible(false);
            Dialogs.create().title("No issued payment ").message("Supplier does not have any pending payment"
                       + "").showWarning();
        }
            
    }
    
    

    @FXML
    private void update_supplierPartPayment_balance() {/*
        input_paying_supplierPartPayment.setEditable(true);
        String theString = input_paying_supplierPartPayment.getText();
        supplierPartPayment_balace.setText(theString);
        Double supAmt = Double.parseDouble(theString);
        System.out.println("amount is " + supAmt + "frs\n\n");
        supAmt-=fixed;
        
        if(supAmt>=0) {
        // String stsupAmt = String.valueOf(supAmt);
        //supplierPartPayment_balace.setText(stsupAmt);
        supplierPartPayment_balace.setText(theString);
        } else {
        //supplierPartPayment_balace.setText("cannot take more than " + String.valueOf(fixed) );
        supplierPartPayment_balace.setText("out of range");
        }
        */
    }

    
      private void searchPanesGoInvisible()
      {
          pane_toMake_supplierPartPayment.setVisible(false);
          pane_toReceive_debtPayment.setVisible(false);
          pane_custumer_pay_expense_Transaction.setVisible(false);
      }
    
      
      int[] issueId = new int[50];
      long [] issuedAmt = new long[50];
      int numOfUpaid;
    @FXML
    private void pay_supplierPartPayment(MouseEvent event) throws SQLException, ParseException {    
        long bal = chek.getBal();
        String makePay;
        System.out.println("i want to pay");
        dNow = new Date();
        long amtSpecified = Long.parseLong(input_paying_supplierPayment.getText());
        if(amtSpecified > this.supplierAmtToPay){
            Dialogs.create().title("error").message("The amount inputed is more than the possible amount to be paid to the supplier").showError();
            return ;
        }else if(amtSpecified > bal ){ //test if this expenditure will cause a negative balance :: still have to put he condition
            Dialogs.create().title("Insuffecient funds").message("Not enough funds in the system").showError();
            return ;
        }
        else{
            Action confirmer = Dialogs.create().
                        title("confirm transaction").
                        masthead("amount : " + amtSpecified + "\nsupplier : " + this.supplier_name).
                        message("Add transaction to database?").showConfirm();

            if (confirmer == Dialog.Actions.YES) {
                //insrt the pay to the pay table
                makePay = "INSERT INTO njieDB.SUPPLIER_PAY( "
                        + " njieDB.SUPPLIER_PAY.EXP_ID, njieDB.SUPPLIER_PAY.SUP_ID,  "
                        + " njieDB.SUPPLIER_PAY.AMOUNT_PAID, njieDB.SUPPLIER_PAY.DATE) VALUES "
                        + " (1, " + this.sup_id + ", " + amtSpecified + ", '"
                        + ft.format(dNow) + "')";
                         db.setQuery(makePay);

        //        adding the expenditure of paying supplier to the expenditure's table
        //        this will take values 1, pay supplier, pay supplier at time timestamp, amt paid
                String addExp, expMemo;
                SimpleDateFormat month;
                month = new SimpleDateFormat("MM");
                expMemo = "payment to supplier | " + supplier_name + "";



                addExp = "INSERT INTO njieDB.EXPENSES (njieDB.EXPENSES.CODE, "
                                +"njieDB.EXPENSES.MEMO, njieDB.EXPENSES.AMOUNT, njieDB.EXPENSES.DAT, njieDB.EXPENSES.TIM ) VALUES ( "
                                + 1 + ", '" + expMemo + "', " 
                                + amtSpecified + ", '" +  dateOnly.format(dNow)  +"', '" + tim.format(dNow) + "' )";
                System.out.println("Query: " + addExp);
                    db.setQuery(addExp);
                    updateExpIncBal();
                    fillCashierIncomeExpenseTable("EXPENSES", col_exp_code,
                                                col_exp_memo, col_exp_amount, expenditure_table);
                    Dialogs.create().masthead("Transaction Completed").showInformation();
            }else{
            Dialogs.create().message("Operation Canceled").showWarning();
            }
        }
        
        pane_toMake_supplierPartPayment.setVisible(false);
        pane_add_purchase_expense_Transaction.setVisible(false);
        this.enableFromMakxTransaction();
        chek.checkPayMade();
    }


    @FXML
    private void payandPrint_payroll(MouseEvent event) throws SQLException, ParseException {
        Date dnow = new Date();
        SimpleDateFormat month = new SimpleDateFormat("yyyy-MM-dd");
        chek.expIncBalance();
        long bal = chek.getBal(), amtDiff;
        /*adding the expenditure */
        String qAddExp, expMemo, qAddpayroll, qGetSalaryAndTotalPay;
        expMemo = "salary payment to " + this.staffName ;
        

        long paymentAmt = Integer.parseInt(input_payrollAmtToCollect.getText());
        amtDiff = paymentAmt;
        qAddpayroll = "INSERT INTO njieDB.PAYROLL( njieDB.PAYROLL.STAFF_ID, "
                + " njieDB.PAYROLL.AMOUNT_PAID, njieDB.PAYROLL.PAY_DATE "
                + " ) VALUES ( " + payroll_staff_id + ", " + paymentAmt
                + ", '" + dateOnly.format(dnow) + "' " 
                + " )";
        qAddExp = "INSERT INTO njieDB.EXPENSES (njieDB.EXPENSES.CODE, "
                            +" njieDB.EXPENSES.MEMO, njieDB.EXPENSES.AMOUNT, njieDB.EXPENSES.DAT, njieDB.EXPENSES.TIM) VALUES ( "
                            + 2 + ", '" + expMemo + "', " 
                            + paymentAmt + ", '" + dateOnly.format(dNow)  +"', '" + tim.format(dNow) + "' )";
        qGetSalaryAndTotalPay = "select njieDB.MONTHLY_SALARY_PAYMENT.DAT, "
                + "                     njieDB.MONTHLY_SALARY_PAYMENT.SALARY, "
                + "                     njieDB.MONTHLY_SALARY_PAYMENT.TOTAL_PAID FROM "
                            + " njieDB.MONTHLY_SALARY_PAYMENT WHERE njieDB.MONTHLY_SALARY_PAYMENT.SALARY != njieDB.MONTHLY_SALARY_PAYMENT.TOTAL_PAID AND "
                            + " njieDB.MONTHLY_SALARY_PAYMENT.STAFF_ID = " + payroll_staff_id;
        
        if(paymentAmt > possiblePayableAmount ){
            Dialogs.create().title("Fail").message("amount requested is more than possible payment").showError();
        }else if(bal < paymentAmt){
            Dialogs.create().title("Error").message("Not enough funds in the system").showError();
        }
        else{//the amount entered is suffecient to pay the staff with no excesses.
            Action confirmer = Dialogs.create().
                title("confirm transaction").
                masthead("amount : " + paymentAmt + "\nmemo   : " + expMemo).
                message("Add transaction to database?").showConfirm();

            if (confirmer == Dialog.Actions.YES) {
                try {
                    /*---- doing the updating in the monthly salary payment table ---*/
                    System.out.println("query to get montly salary and payments from the monthly salary payments table \n " + qGetSalaryAndTotalPay );
                    db.setQuery(qGetSalaryAndTotalPay);
                    System.out.println("number of rows returned = " + db.getRowCount());
                    String payrollDate, qUpdateMontlySalaryPaymentTable;
                    long payrollTotal, payrollSalary, possibleMonthsPayableAmount;
                    int rowCount = db.getRowCount();
                    for(int i = 0; i < rowCount; i++){
                        System.out.println("*****loop " + i);
                        payrollDate = db.getValueAt(i, 0).toString();
                        payrollSalary = Long.parseLong(db.getValueAt(i, 1).toString());
                        payrollTotal = Long.parseLong(db.getValueAt(i, 2).toString());
                        possibleMonthsPayableAmount = payrollSalary - payrollTotal;
                        
                        
                        System.out.println("diff amt = " + amtDiff + "  possibleMonthsPayableAmt = " + possibleMonthsPayableAmount);
                        
                        amtDiff -= possibleMonthsPayableAmount;
                        if(amtDiff < 0){
                            System.out.println("\namounts \n " + payrollDate + " -- " + payrollSalary + " -- " + payrollTotal + "");
                                    
                            System.out.println("\n Amt diff = " + amtDiff);
                            //the amount will pay this month incompletely having a nagative remains
                            long amtForLessThan = payrollTotal - amtDiff;
                            qUpdateMontlySalaryPaymentTable = 
                                    "   update njieDB.MONTHLY_SALARY_PAYMENT "
                                    + " set njieDB.MONTHLY_SALARY_PAYMENT.TOTAL_PAID = " + amtForLessThan 
                                    + " where njieDB.MONTHLY_SALARY_PAYMENT.DAT = '" + payrollDate + "' ";
                            System.out.println("query to update when < 0 \n" + qUpdateMontlySalaryPaymentTable);
                            db2.setQuery(qUpdateMontlySalaryPaymentTable);
                            break ;
                        }else if(amtDiff == 0){
                            qUpdateMontlySalaryPaymentTable = 
                                    "   update njieDB.MONTHLY_SALARY_PAYMENT "
                                    + " set njieDB.MONTHLY_SALARY_PAYMENT.TOTAL_PAID = " + payrollSalary
                                    + " where njieDB.MONTHLY_SALARY_PAYMENT.DAT = '" + payrollDate + "' ";
                            System.out.println("query to update when == 0 \n" + qUpdateMontlySalaryPaymentTable);
                            db2.setQuery(qUpdateMontlySalaryPaymentTable);
                            break;
                        }else {
                            //the amount will pay the staff and more will still be left for paying the subsequent months
                            qUpdateMontlySalaryPaymentTable = 
                                    "   update njieDB.MONTHLY_SALARY_PAYMENT "
                                    + " set njieDB.MONTHLY_SALARY_PAYMENT.TOTAL_PAID = " + payrollSalary
                                    + " where njieDB.MONTHLY_SALARY_PAYMENT.DAT = '" + payrollDate + "' ";
                            System.out.println("query to update when > 0 \n" + qUpdateMontlySalaryPaymentTable);
                            db2.setQuery(qUpdateMontlySalaryPaymentTable);
                        }
                    }
                    
                    
                    
                    /*insert into payroll and expenditure tables*/
                    System.out.println("query to add to payroll \n " + qAddpayroll);
                    db.setQuery(qAddpayroll);
                    System.out.println("query to addExp \n " + qAddExp);
                    db.setQuery(qAddExp);
                    
                    System.out.println("end \n\n\n\n\n\n\n");
                    
                    updateExpIncBal();
                    fillCashierIncomeExpenseTable("EXPENSES", col_exp_code,
                                        col_exp_memo, col_exp_amount, expenditure_table);
                    Dialogs.create().title("paid succesfully")
                            .message("Salary paid to staff succesfully")
                            .showInformation();
                   
                
                } catch (SQLException | IllegalStateException ex) {
                    ex.printStackTrace();
                }
            }else{
                Dialogs.create().message("Operation Canceled").showWarning();
            }
            pane_payrollFor_aStaff.setVisible(false);
            pane_add_payroll_expense_Transaction.setVisible(false);
            enableFromMakxTransaction();
        }
        input_payrollAmtToCollect.setText(null);
       
    }
    
    int payroll_staff_id;
    long possiblePayableAmount;
    @FXML
    private void search_employeeFor_payroll(MouseEvent event) throws SQLException, ParseException {
        pane_payrollFor_aStaff.setVisible(true);
        payroll_staff_id = 0;
         int overdraftDeductionPerMonth = 0;
        /*
        this method had an error. the error reads
        Caused by: java.sql.SQLException: ResultSet not open. 
        Operation 'absolute()' not permitted. Verify that autocommit is off.
        */
        
        
        System.out.println("Searching for employee for payment section start");
        payroll_staff_id = Integer.parseInt(input_staffID_payroll.getText());
        System.out.println("staff ID : " + payroll_staff_id);
       /* input_staffName_payroll*/
        String Qstaff_salaryAndName, qGetTotaUnpaidSalary_and_totalSalary; 
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
        Qstaff_salaryAndName = "SELECT njieDB.MONTHLY_SALARY_PAYMENT.SALARY, njieDB.STAFF.NAME "
                + " FROM njieDB.STAFF, njieDB.MONTHLY_SALARY_PAYMENT WHERE "
                + " njieDB.STAFF.STAFF_ID = " + payroll_staff_id + " AND "
                + " njieDB.MONTHLY_SALARY_PAYMENT.STAFF_ID = " + payroll_staff_id;
        System.out.println("Gettins sal, sNam and empDate " + Qstaff_salaryAndName);
        long thisMonthsSalary = (long)0;
        try {
            db.setQuery(Qstaff_salaryAndName);
            thisMonthsSalary = Integer.parseInt(db.getValueAt(0, 0).toString());
            this.staffName = db.getValueAt(0, 1).toString();
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
       
        
        
        
        System.out.println("staffName = " + staffName + "   monthly salary " + thisMonthsSalary);
        
         /*get the total salary paid since employment*/
        qGetTotaUnpaidSalary_and_totalSalary = "SELECT SUM(njieDB.MONTHLY_SALARY_PAYMENT.SALARY), "
                + " SUM(njieDB.MONTHLY_SALARY_PAYMENT.TOTAL_PAID) FROM "
                + " njieDB.MONTHLY_SALARY_PAYMENT WHERE "
                + "  njieDB.MONTHLY_SALARY_PAYMENT.STAFF_ID = " + payroll_staff_id + " AND "
                + "  njieDB.MONTHLY_SALARY_PAYMENT.SALARY != njieDB.MONTHLY_SALARY_PAYMENT.TOTAL_PAID";
        System.out.println("Total SalaryPaid : " + qGetTotaUnpaidSalary_and_totalSalary);
        long totalSalaryPaid = (long)0, totalSalary = (long)0;
        db.setQuery(qGetTotaUnpaidSalary_and_totalSalary);
        if(db.getValueAt(0, 0) != null){
            totalSalary = Long.parseLong(db.getValueAt(0, 0).toString());
            totalSalaryPaid = Long.parseLong(db.getValueAt(0, 1).toString());
        }
        System.out.println("total paid salary = " + totalSalaryPaid + "\n");
        System.out.println("total salary = " + totalSalary);
                
        /*now i get the possible payable amount*/
        possiblePayableAmount = totalSalary - totalSalaryPaid;
        
        
        /*check if it is he can collect something under normal circumstances, ie with no overdraft */
        if(possiblePayableAmount == 0){//he cannot take anything since he has 0 balance
            Dialogs.create().title("No Funds").message("Sorry, he has exhausted his pay ").showError();
            pane_payrollFor_aStaff.setVisible(false);
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
                Dialogs.create().title("Funds out").message("You cannot collect any more this month because"
                        + " of your overdraft has been deducted leaving you with a net sum of 0 frs"
                        + " for this month").showError();
                pane_payrollFor_aStaff.setVisible(false);
                return ;
            }
            
            /*At this point in time, we clearly know that the staff has some funds 
            * that he can take with the overdraft deducted if he had an overdraft*/
            /*so at this point, we shall display the poissible amount the staff can collect
            * for that month */
           
            
            pane_payrollFor_aStaff.setVisible(true);
            PayRoll_output_staffID.setText(String.valueOf(payroll_staff_id));
            PayRoll_output_staffName.setText(String.valueOf(this.staffName));
            PayRoll_output_monthlySalary.setText(String.valueOf(thisMonthsSalary));
           // PayRoll_output_salaryAmtCollected.setText(String.valueOf(salaryColectedForCurrentMonth));
            PayRoll_output_ovMontlyDeduction.setText(String.valueOf(overdraftDeductionPerMonth));
            label_payroll_balance.setText(String.valueOf(possiblePayableAmount));
            input_payrollAmtToCollect.setText(String.valueOf(possiblePayableAmount));
            System.out.println("\n\n\n\n\n\n");
                    
            
        }
        
    }
    
    int staffId_payOD,  overdraft_ID_for_PaymentValidation;
    long OV_amt;
    String staffName, deductUntil;
    long amt;   
    @FXML
    private void searchFor_valid_overdraft_button(MouseEvent event) throws SQLException {
    /****clear****/
       // input_staff_ID.setText("");
        chek.checkOverdraft();
        int staffId = Integer.parseInt(input_staffName_overdraft.getText()); 
        String testForOverdraft, getOverdraftDetails;
        System.out.println("OV staffID = " + staffId);
        testForOverdraft = "SELECT njieDB.OVERDRAFT.OV_ID, njieDB.OVERDRAFT.AMOUNT, njieDB.STAFF.NAME, "
                + " njieDB.OVERDRAFT.DEDUCTION_PER_MONTH, njieDB.OVERDRAFT.DEDUCT_UNTIL "
                + " FROM njieDB.STAFF, njieDB.OVERDRAFT "
                + " WHERE njieDB.STAFF.STAFF_ID = " + staffId
                + " AND njieDB.STAFF.STAFF_ID = njieDB.OVERDRAFT.STAFF_ID "
                + " AND njieDB.OVERDRAFT.PAY_STATUS = " + false ;
        System.out.println("test for overdraft : \n" + testForOverdraft);
        db.setQuery(testForOverdraft);
        /*Here, i will check if the staff has an
        * overdraft issued by the manager.
        */
        /*assign staff name for error/succes message. */
        
        if(db.getRowCount() > 0){//staff has an overdraft issued so
            staffName = db.getValueAt(0, 2).toString();
            /*Since the user has we shall display the the amount to be issued and 
            also the staff name on the screen waiting for validation. */
            overdraft_ID_for_PaymentValidation = Integer.parseInt(db.getValueAt(0, 0).toString());
            this.OV_amt = Long.parseLong(db.getValueAt(0, 1).toString());
            /*Display the information to the user and wait for the payment instruction*/
            //to be inplemented: display sub form, set the values of the amount and staff name
            valid_overdraft_pane.setVisible(true);
            
            /*filling the labels for the cashier to see the required information and 
            * also for printing of the receipt. Receipt is still to be implemented.*/
            this.amt = Integer.parseInt(db.getValueAt(0, 1).toString());
            OD_output_AmtToPay.setText(String.valueOf(amt));
            OD_output_staffName.setText(staffName);
            OD_output_staffID.setText(String.valueOf(staffId));
            deductUntil = db.getValueAt(0, 4).toString();
            OD_output_deductUntil.setText(deductUntil);
            OD_output_monthlyDeduction.setText(db.getValueAt(0, 3).toString());
        }
        else{
            Dialogs.create().title("No overdraft").message("Staff does not has no valid overdraft payment issued ").showError();
            valid_overdraft_pane.setVisible(false);
        }
    }

    @FXML
    private void payPrint_overdraft(MouseEvent event) throws SQLException, ParseException {
       String updateOD, addExp;
       if(chek.getBal() < amt ){
           Dialogs.create().title("Not enough funds").message("The funds in the coffers are not enough to pay the overdraft.").showError();
            pane_add_overdraft_expense_Transaction.setVisible(false);
            enableFromMakxTransaction();
           return ;
       }
       updateOD = "UPDATE njieDB.OVERDRAFT SET njieDB.OVERDRAFT.PAY_STATUS = " + true
               + " WHERE njieDB.OVERDRAFT.OV_ID = " + overdraft_ID_for_PaymentValidation ;
       /*add the expenditure to the expenditure table
        * the information added here will be a concartinationof the various segnents about
        * the overdraft issued by the manager, ie, like the staffName, amt deducted and 
        * also the duration to be deducted.*/
        ft = new SimpleDateFormat ("yyyy-MM-dd' 'hh:mm:ss'.000'");
        dNow = new Date();
        addExp = "INSERT INTO njieDB.EXPENSES (njieDB.EXPENSES.CODE,  "
                        +"njieDB.EXPENSES.MEMO, njieDB.EXPENSES.AMOUNT, njieDB.EXPENSES.DAT, njieDB.EXPENSES.TIM) VALUES ( "
                        + 3 + ", 'Payment of overdraft to " 
                        + staffName + " dued to be deducted until " + deductUntil + " ', " + this.OV_amt + ", '"
                        + dateOnly.format(dNow)  +"', '" + tim.format(dNow) + "' )";
       System.out.println("Update query");
        System.out.println("Update : " + updateOD);
        System.out.println("addExp : " + addExp);
        
       /*executing the queries. I will put them in a try and catch so that if one fails, the
       * other should also do fail. This is in order to make sure that the statue of the 
       * overdraft is changed and the transaction recorded in the expense book.*/
       
        Action confirmer = Dialogs.create().
                    title("confirm transaction").
                    masthead("amount : " + OV_amt + "\n Overdraft").
                    message("Add transaction to database?").showConfirm();
                   
        if (confirmer == Dialog.Actions.YES) {
            try {
                db.setQuery(updateOD);
                db.setQuery(addExp);
                updateExpIncBal();
                Dialogs.create().title("Success").message("Paid succesfully").showInformation();
                valid_overdraft_pane.setVisible(false);
                fillCashierIncomeExpenseTable("EXPENSES", col_exp_code,
                                        col_exp_memo, col_exp_amount, expenditure_table);
                /*update the total expenses and balance*/
    //            balance.setText(Long.toString(chek.getBalafterExp(this.OV_amt)));
    //            total_expenditure_down.setText(Long.toString(chek.totExp));
            } catch (SQLException | IllegalStateException sQLException) {
                Dialogs.create().title("Error").message("error paying. Please try again.").showError();
                sQLException.printStackTrace();
            }
        }else{
        Dialogs.create().message("Operation Canceled").showWarning();
        }
            
        pane_add_overdraft_expense_Transaction.setVisible(false);
        enableFromMakxTransaction();
    }

    
    
    /*****
    ********  MANAGER ********
    * *****/
    private void managerAllTransactionsGoInvisible()
    {
        manager_addTransaction.setVisible(false);
        manager_viewTransactions.setVisible(false);
        manager_default_display.setVisible(false);
    }
        @FXML
        private void manager_addTransaction(MouseEvent event) {
            this.managerAllTransactionsGoInvisible();
            manager_addTransaction.setVisible(true);

        }
        
        
        private void manager_editTransaction(MouseEvent event) {
            this.managerAllTransactionsGoInvisible();
            manager_editTransaction.setVisible(true);
        }

        private void manager_deleteTransaction(MouseEvent event) {
            this.managerAllTransactionsGoInvisible();
            manager_deleteTransaction.setVisible(true);
        }

        @FXML
        private void manager_viewTransaction(MouseEvent event) {
            this.managerAllTransactionsGoInvisible();
            manager_viewTransactions.setVisible(true);
            miniPaneForTransClass.setDisable(false);
            displayTransactionClassTable("njieDB.INC_TRANS_CLASS");//DEFAULT WILL BE INCOME
            this.transClassType = "income";
            vInvExp.setText("INCOME");
            miniPaneForTransClass.setDisable(true);
        }

        @FXML
        private void activate_defaultManager_transaction(Event event) {
            this.managerAllTransactionsGoInvisible();
            manager_default_display.setVisible(true);
        }

        
        
        
        
        
        
        /********** SUPPLIER    *********
         * Management of the panes that come visible on clicking the buttons to show
         * the various options under the supplier
         * Here the manager will be able to add, delete, edit and view supplier information
         * Additionally, he/she will be able to issue a payment order which will be carried 
         * to the cashier to make the payment.
         * Without the manager issuing this payment, the cashier will not be able to input a 
         * and expenses transaction under the code of payment to suppliers.
         */
        
    private void clearSupplierScreen()
    {
        manager_addSupplier.setVisible(false);
        manager_viewSupplier.setVisible(false);
        manager_Supplier_issuePayment.setVisible(false);
        manaageSupplierEditPane.setVisible(false);
       
        //need to add this to the fxml
        //manager_suplier_displayIssuePaymentPane.setVisible(true);
        //manager_defaultSupplier.setVisible(false);
    }
        
        @FXML
        private void manager_addSupplier(MouseEvent event) {
            clearSupplierScreen();
            manager_addSupplier.setVisible(true);

        }

        private void manager_deleteSupplier(MouseEvent event) {
            clearSupplierScreen();
            manager_deleteSupplier.setVisible(true);
        }

        @FXML
        private void manager_viewSupplier(MouseEvent event) throws SQLException {
            clearSupplierScreen();
            manager_viewSupplier.setVisible(true);
            miniPaneForStaffEdit.setDisable(true);
            populateSupplierTable();
            
        }
        
        private ObservableList<SupTable> composeSupplier;
         public void populateSupplierTable () throws SQLException  {
            
            String query = "SELECT njieDB.SUPPLIER.SUP_CODE, njieDB.SUPPLIER.NAME, njieDB.SUPPLIER.SERVICES "
                           + " FROM njieDB.SUPPLIER ";
            
            this.col_sup_code.setCellValueFactory(new PropertyValueFactory<>("code"));
            this.col_supName.setCellValueFactory(new PropertyValueFactory<>("name"));
            this.col_supServices.setCellValueFactory(new PropertyValueFactory<>("service"));
            
            composeSupplier = FXCollections.observableArrayList();
            this.viewSuppierInfo.setItems(composeSupplier);
            
            System.out.println("Printing the Users History");
           
           
		    db.setQuery(query);
		    String tempName;
          
          
          	for (int i = 0; i < db.getRowCount(); i++) {
            	SupTable sup = new SupTable();
            
            sup.code.setValue(db.getValueAt(i, 0).toString());
            sup.name.setValue(db.getValueAt(i, 1).toString());
            sup.service.setValue(db.getValueAt(i, 2).toString());
         
            composeSupplier.add(sup);
      	}
            enableSelectForUpdateAndDelete(viewSuppierInfo);
      }

        @FXML
        private void activate_defaultManager_Supplier(Event event) {
            clearSupplierScreen();
            //manager_defaultSupplier.setVisible(true);
        }

        @FXML
        private void manager_Supplier_issuePayment(MouseEvent event) throws SQLException {
            clearSupplierScreen();
            manager_Supplier_issuePayment.setVisible(true);
            loadSupplierCodeCombox();
        }
        
        private void manager_Supplier_DisplayIssuedPayment(MouseEvent event) {
            clearSupplierScreen();
//            this is what is lacking in the fxml
//            manager_suplier_displayIssuePaymentPane.setVisible(true);
//            makeTableCheckLastBooleanIfPaid(viewSupIssueTab, viewSupIssue_id_col, viewSupIssue_name_col, viewSupIssue_amt_col, viewSupIssue_status_col);
        }
        
        /*************STAFF MANAGEMENT*************/
   
    
    private void clearAllAddStaff(){
        manager_addStaff.setVisible(false);
        manager_viewStaff.setVisible(false);
        manager_defaultStaff.setVisible(false);
    }
        @FXML
        private void manager_editStaff(MouseEvent event) {//TO BE ADDED
            clearAllAddStaff();
            //manager_editStaff.setVisible(true);
        }

        @FXML
        private void manager_addStaff(MouseEvent event) {
            clearAllAddStaff();
            manager_addStaff.setVisible(true);
        }

        @FXML
        private void manager_deleteStaff(MouseEvent event) {//TO BE ADDED
            clearAllAddStaff();
            //manager_deleteStaff.setVisible(true);
        }

        @FXML
        private void manager_viewStaff(MouseEvent event) throws SQLException {
            clearAllAddStaff();
            manager_viewStaff.setVisible(true);
            miniPaneForStaffEdit.setDisable(true);
            populateViewStaffTable();
        }
        
        @FXML
        private void activate_defaultManager_addStaff(Event event) {
            clearAllAddStaff();
            manager_defaultStaff.setVisible(true);
        }

    /*    
        *****************TOGGLING BETWEEN MANAGER AND CASHIE R    
         * The work_space is the pane holding the cashier's work space
         * The admin_space is the pane holding the manager's workspace**********************/
        
        private void clearCashierManager()
        {
            work_space.setVisible(false);
            admin_space.setVisible(false);
        }
        
        private void displayRootCashier()
        {
            this.clearCashierManager();
            work_space.setVisible(true);
        }
        
        @FXML/******************@managerLogin**********************/
        private void loginAsManager() throws SQLException
        {
            /**do data verification from database first */
            
            String username = this.usernameTxtFld.getText();
            String password = this.passwordFld.getText();
            
            usernameTxtFld.setText("");     passwordFld.setText("");

            String loginQuery;
            loginQuery = "SELECT njieDB.ACCOUNT.ACC_ID FROM njieDB.ACCOUNT WHERE"
                        +" njieDB.ACCOUNT.USERNAME = '" + username + "' AND "
                        +" njieDB.ACCOUNT.PASSWORD = '" + password + "' AND "
                        +" njieDB.ACCOUNT.ROLE = 'M'";
            System.out.println("The query is " + loginQuery);
          
            db.setQuery(loginQuery);
            
            if(db.getRowCount() > 0)
            {
                this.clearCashierManager();
                admin_space.setVisible(true);
            }
            
            else{
                   Dialogs.create().title("Login ERROR").message("Username or Password incorrect for manager"
                           + "").showWarning();
                System.out.println("Wrong login details for manager");
                return ;
            }
        } 
        
    @FXML/******************@cashierLogin*******************/
    private void loginAsCashier(MouseEvent event) throws SQLException, ParseException {
        //check user credentials
        String username = this.usernameTxtFld.getText();
        String password = this.passwordFld.getText();
        //clear the fields.
        usernameTxtFld.setText("");     passwordFld.setText("");
        
        String loginQuery;
        loginQuery = "SELECT njieDB.ACCOUNT.ACC_ID FROM njieDB.ACCOUNT WHERE"
                    +" njieDB.ACCOUNT.USERNAME = '" + username + "' AND "
                    +" njieDB.ACCOUNT.PASSWORD = '" + password + "' AND "
                    +" njieDB.ACCOUNT.ROLE = 'C'";
        
        //System.out.println("The query is " + loginQuery);
        
        try {
            db.setQuery(loginQuery);
            if (db.getRowCount() > 0) {
                welcome_pane.setVisible(false);
                work_space.setVisible(true);
                //this.clearMessages();
                //load combo boxes for income and expenses
                loadInctransCode();
                loadExptransCode();
                //fill income and expenses table
                fillCashierIncomeExpenseTable("INCOME", col_inc_code,
                                    col_inc_memo, col_inc_amount, income_table);
                fillCashierIncomeExpenseTable("EXPENSES", col_exp_code,
                                    col_exp_memo, col_exp_amount, expenditure_table);
                updateExpIncBal();
            } else {
                Dialogs.create().title("Login ERROR").message("Username or Password incorrect for cashier"
                        + "").showWarning();
                System.out.println("Wrong login details.");
            }
        } catch (SQLException sQLException) {
           sQLException.printStackTrace();
        } catch (IllegalStateException illegalStateException) {
        }
        
    } 
    
    /*******************************************
     *******************************************  POPULATING THE Incomes table.
     * *****************************************
     * *******************************************
     */
    @SuppressWarnings("Convert2Diamond")
    private void fillCashierIncomeExpenseTable(String dbTable, TableColumn t1, TableColumn t2,
            TableColumn t3, TableView tableElement) throws SQLException
    {   
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date dnow = new Date();
        String todayDate = format.format(dnow);
        
        String dbase = "njieDB.";
        String table = dbase + dbTable;
//njieDB.INC_TRANS_CLASS
        String query = "";
        /*if ststement to add the class and make the display have class name not code.*/
        if("njieDB.INCOME".equals(table)){
            query = "SELECT njieDB.INC_TRANS_CLASS.NAME, " //+ table + ".NAME, "  //have to remove name and also
                        + table + ".MEMO, " + table + ".AMOUNT, "   //need to display trans class name with join(class and actual table)
                        + table + ".DAT FROM " + table + ", njieDB.INC_TRANS_CLASS"
                        + " WHERE njieDB.INC_TRANS_CLASS.CODE = " + table + ".CODE";
        }   else{
            query = "SELECT njieDB.EXP_TRANS_CLASS.NAME, " //+ table + ".NAME, "  //have to remove name and also
                        + table + ".MEMO, " + table + ".AMOUNT, "   //need to display trans class name with join(class and actual table)
                        + table + ".DAT FROM " + table + ", njieDB.EXP_TRANS_CLASS"
                        + " WHERE njieDB.EXP_TRANS_CLASS.CODE = " + table + ".CODE";
        }
         System.out.println("query = \n" + query);
        t1.setCellValueFactory(new PropertyValueFactory<>("col1"));
        t2.setCellValueFactory(new PropertyValueFactory<>("col2"));  
        t3.setCellValueFactory(new PropertyValueFactory<>("col3"));
       // t4.setCellValueFactory(new PropertyValueFactory<>("col4")); //has to be removed


        composeExpenseIncome = FXCollections.observableArrayList();
        tableElement.setItems(composeExpenseIncome);

        System.out.println("Printing the Users History");

             db.setQuery(query);

         System.out.println("before loop");
        for (int i = 0; i < db.getRowCount(); i++) {
            if(todayDate.equals(db.getValueAt(i, 3).toString())){
                TableClass trans = new TableClass();
                trans.col1.setValue(db.getValueAt(i, 0).toString());
                trans.col2.setValue(db.getValueAt(i, 1).toString());
                trans.col3.setValue(db.getValueAt(i, 2).toString());
               // trans.col4.setValue(db.getValueAt(i, 3).toString());    //has to be removed
                composeExpenseIncome.add(trans);
            }
        }
        
    }
    
    
    public ArrayList getAllData(String data, String table) {
        ArrayList re = new ArrayList();
        try {

            String sql = "select " + data + " from " + table ;
            //  DatabaseHelper db = new DatabaseHelper();
            System.out.println(sql);
            re = db.ExecuteQuery(sql);

        } catch (Exception sh) {
            System.out.println(sh.getMessage());

        }
        return re;

    }
    private void loadSupplierName_forPayment() throws SQLException
    {
        input_supplierName_supplierPayment.getItems().clear();//remove all elements from the list
        String getData = "SELECT njieDB.SUPPLIER.SUP_CODE, njieDB.SUPPLIER.NAME FROM njieDB.SUPPLIER";
        db.setQuery(getData);
        clearArrayList();
        for(int i = 0 ; i < db.getRowCount() ; i++){
            String code = db.getValueAt(i, 0).toString();
            String name = db.getValueAt(i, 1).toString();
            //fill it to the combo box
            this.input_supplierName_supplierPayment.getItems().add(new ComboItem(name, code));
            System.out.printf(" %s\n", name);
            //fill the array list
           
            ComboValue.add(i, name);
            ComboKey.add(i, code);
        }
        //this.input_supplierName_supplierPayment.getSelectionModel().selectFirst();
        System.out.println("Finished loading the combo box for the supplier's code");
        AutoCompleteComboBoxListener autocom = new AutoCompleteComboBoxListener<>(input_supplierName_supplierPayment);
    }
    
    @FXML
    private void deactivateToSearch(){
        //searchSup.setDisable(true);
        payPrintSupplier.setDisable(true);
    }
    
    
    private void loadStaffID_forPayroll(ComboBox box) throws SQLException
    {
        box.getItems().clear();//remove all elements from the list
        String getData = "SELECT njieDB.STAFF.STAFF_ID, njieDB.STAFF.NAME FROM njieDB.STAFF";
       
            db.setQuery(getData);
        for(int i = 0 ; i < db.getRowCount() ; i++){
            String code = db.getValueAt(i, 0).toString();
            String name = db.getValueAt(i, 1).toString();
            //fill it to the combo box
            box.getItems().add(new ComboItem(name, code));
            System.out.printf(" %s\n", name);
        }
        //box.getSelectionModel().selectFirst();
        System.out.println("Finished loading the combo box for the staff id");
        AutoCompleteComboBoxListener autocom = new AutoCompleteComboBoxListener<>(box);
    }
    
    
    
    private void loadSupplierId_forPayment() throws SQLException
    {
        input_supplierID_supplierPartPayment.getItems().clear();//remove all elements from the list
        String getData = "SELECT njieDB.SUPPLIER.SUP_CODE, njieDB.SUPPLIER.NAME FROM njieDB.SUPPLIER";
        db.setQuery(getData);
        for(int i = 0 ; i < db.getRowCount() ; i++){
            String code = db.getValueAt(i, 0).toString();
            String name = db.getValueAt(i, 1).toString();
            //fill it to the combo box
            this.input_supplierID_supplierPartPayment.getItems().add(new ComboItem(code, code));
            System.out.printf(" %s\n", name);
        }
        //this.input_supplierID_supplierPartPayment.getSelectionModel().selectFirst();
        System.out.println("Finished loading the combo box for the supplier's code");
        AutoCompleteComboBoxListener autocom = new AutoCompleteComboBoxListener<>(input_supplierID_supplierPartPayment);
    }
    
    private void loadInctransCode() throws SQLException
    {
        input_income_code.getItems().clear();//remove all elements from the list
        String getData = "SELECT njieDB.INC_TRANS_CLASS.CODE, njieDB.INC_TRANS_CLASS.NAME FROM njieDB.INC_TRANS_CLASS"
                + " WHERE njieDB.INC_TRANS_CLASS.CODE != " + 1;
        db.setQuery(getData);
        clearArrayList();
        for(int i = 0 ; i < db.getRowCount() ; i++){
            String code = db.getValueAt(i, 0).toString();
            String trans_class_name = db.getValueAt(i, 1).toString();
            //fill it to the combo box
            this.input_income_code.getItems().add(new ComboItem(trans_class_name, code));
            System.out.printf(" %s\n", trans_class_name);
            //fill the array list
            
            ComboValue.add(i, trans_class_name);
            ComboKey.add(i, code);
            
        }
        //this.input_income_code.getSelectionModel().selectFirst();
        System.out.println("Finished loading the combo box for the supplier's code");
        AutoCompleteComboBoxListener autocom = new AutoCompleteComboBoxListener<>(input_income_code);
    }
    
    private void loadExptransCode() throws SQLException
    {
        try {
            input_expenditure_code.getItems().clear();//remove all elements from the list
        } catch (Exception e) {}
        String getData = "SELECT njieDB.EXP_TRANS_CLASS.CODE, njieDB.EXP_TRANS_CLASS.NAME "
                       + " FROM njieDB.EXP_TRANS_CLASS WHERE "
                + " njieDB.EXP_TRANS_CLASS.CODE != " + 1 
                + "AND njieDB.EXP_TRANS_CLASS.CODE != " + 2 
                + "AND njieDB.EXP_TRANS_CLASS.CODE != " + 3;
        db.setQuery(getData);
        clearArrayList();
        if(db.getRowCount() > 0){
            for(int i = 0 ; i < db.getRowCount() ; i++){
            String code = db.getValueAt(i, 0).toString();
            String trans_class_name = db.getValueAt(i, 1).toString();
            //fill it to the combo box
            this.input_expenditure_code.getItems().add(new ComboItem(trans_class_name, code));
            System.out.printf(" %s\n", trans_class_name);
            //fill the arraylist
            
            ComboValue.add(i, trans_class_name);
            ComboKey.add(i, code);
        }
        //this.input_expenditure_code.getSelectionModel().selectFirst();
        System.out.println("Finished loading the combo box for the supplier's code");
        }
        else{
           input_expenditure_code.setDisable(false);
        }
        AutoCompleteComboBoxListener autocom = new AutoCompleteComboBoxListener<>(input_expenditure_code);
        
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    private void clearAllResponseLabels()
    {
        addStaff_response.setText(".");
    }
    
    
    
    
    
    
    
    /*manager*/
    @FXML
    private void addSupplier_to_database(MouseEvent event) throws SQLException {
        int supplierCode = Integer.parseInt(add_supplierCodeTxtFld.getText()); add_supplierCodeTxtFld.setText("");
        String supplierName = add_supplierNameTxtFld.getText(); add_supplierNameTxtFld.setText("");
        String services = add_supplierServiceTxtFld.getText(); add_supplierServiceTxtFld.setText("");
        String isCodeAlreadyPresent = "SELECT njieDB.SUPPLIER.SUP_CODE FROM njieDB.SUPPLIER WHERE "
                + "njieDB.SUPPLIER.SUP_CODE = " + supplierCode + "";
        if(supplierCode == 0 || supplierName.length() == 0 )
        {
            addSupplier_response.setText("Some fields are empty");
            return ;
        }
        if(supplierName.length() > 20){
            addSupplier_response.setText("Name too long");
            return ;
        }
        System.out.println("isPresentQuery: " + isCodeAlreadyPresent);
            db.setQuery(isCodeAlreadyPresent);
//check if that code already exists in the database
        if(db.getRowCount()>0){
            addSupplier_response.setText("Code Already Exists. Choose another code");
        }
        else{//code has not been used to can be inserted to the database
            String addSupplier = "INSERT INTO njieDB.SUPPLIER "
                    + " (njieDB.SUPPLIER.SUP_CODE, njieDB.SUPPLIER.NAME, njieDB.SUPPLIER.SERVICES) "
                    + " VALUES ("+ supplierCode + ", '" + supplierName + "', '" + services + "' )";
            //add supplier's defalt value
            String supXtraDefaltAmt_Q = "insert into njieDB.SUPP_XTRA(njieDB.SUPP_XTRA.SUP_CODE) VALUES "
                    + "(" + supplierCode + ")"; 
            System.out.println("addSupplierQuery: " + addSupplier);
            System.out.println("add default exra amt " + supXtraDefaltAmt_Q);
            db.setQuery(addSupplier);
            db.setQuery(supXtraDefaltAmt_Q);
            Dialogs.create().message("supplier added succesfully").showInformation();
//            addSupplier_response.setText("Supplier added succesfully.");
        }
        
        add_supplierCodeTxtFld.setText("");
        add_supplierNameTxtFld.setText("");
        add_supplierServiceTxtFld.setText("");
        chek.checkSupplier();
        //loadSupplierCodeCombox();
    }
        
    
    private void loadSupplierCodeCombox() throws SQLException
    {
        supplierCode_issuePayCmbox.getItems().clear();//remove all elements from the list
        String getData = "SELECT njieDB.SUPPLIER.SUP_CODE, njieDB.SUPPLIER.NAME FROM njieDB.SUPPLIER";
        db.setQuery(getData);
        clearArrayList();
        for(int i = 0 ; i < db.getRowCount() ; i++){
            String code = db.getValueAt(i, 0).toString();
            String name = db.getValueAt(i, 1).toString();
            //fill it to the combo box
            this.supplierCode_issuePayCmbox.getItems().add(new ComboItem(name, code));
            System.out.printf(" %s\n", name);
            //fill the array list
            
            ComboValue.add(i, name);
            ComboKey.add(i, code);
        }
        //this.supplierCode_issuePayCmbox.getSelectionModel().selectFirst();
        System.out.println("Finished loading the combo box for the supplier's code");
        AutoCompleteComboBoxListener autocom = new AutoCompleteComboBoxListener<>(supplierCode_issuePayCmbox);
    }
    
       private void loadSupplierCodeCombox(ComboBox id) throws SQLException
    {
        id.getItems().clear();//remove all elements from the list
        String getData = "SELECT njieDB.SUPPLIER.SUP_CODE, njieDB.SUPPLIER.NAME FROM njieDB.SUPPLIER";
        db.setQuery(getData);
        clearArrayList();
        for(int i = 0 ; i < db.getRowCount() ; i++){
            String code = db.getValueAt(i, 0).toString();
            String name = db.getValueAt(i, 1).toString();
            //fill it to the combo box
            id.getItems().add(new ComboItem(name, code));
            System.out.printf(" %s\n", name);
            //fill the array list
            
            ComboValue.add(i, name);
            ComboKey.add(i, code);
        }
        //this.supplierCode_issuePayCmbox.getSelectionModel().selectFirst();
        System.out.println("Finished loading the combo box for the supplier's code");
        AutoCompleteComboBoxListener autocom = new AutoCompleteComboBoxListener<>(id);
    }

    @FXML
    private void manager_issue_suppPay(MouseEvent event) throws SQLException {
        String cCode = this.supplierCode_issuePayCmbox.getEditor().getText();
        int code = Integer.parseInt(getKey(cCode));
        int amount = Integer.parseInt(issuePay_amtTxtFld.getText());
        dNow = new Date();
        /*check if that supplier still has an unpaid entry on the database
          If he/she does have an unpaid(partially paid), we should not be able to assign another pay order
          
            VERSION 1.1 WILL OVERIDE THIS OPTION MAKING THE SUPPLIER TO HAVE MULTIPLE PAYMENT ISSUES but for version 1.0, we leave it at only 1 active payment issue*/

        //this is not used again. so i commented it
        /*
        String Q_check = "";
        Q_check = "select njieDB.SUPPLIER_PAY_ORDER.ID from njieDB.SUPPLIER_PAY_ORDER "
        + " where njieDB.SUPPLIER_PAY_ORDER.STATUS = " + false
        + " and njieDB.SUPPLIER_PAY_ORDER.SUPPLIER_CODE = " + code;
        db.setQuery(Q_check);
        System.out.println("row count = " + db.getRowCount());
        */
        //since the supplier can have more than one pending debt, then we don;t need the check again
        /*if(db.getRowCount() > 0){
        Dialogs.create().title("Error").message("The Supplier already has a pending payment or partially paid.").showInformation();
        return ;
        }*/
        
        /*Issue the payment for the cashier to be able to see it and pay.*/
        try {
            String issuePayment = "INSERT INTO njieDB.SUPPLIER_PAY_ORDER ( njieDB.SUPPLIER_PAY_ORDER.AMOUNT, "
                    + " njieDB.SUPPLIER_PAY_ORDER.SUPPLIER_CODE, njieDB.SUPPLIER_PAY_ORDER.ORDER_DATE )"
                    + " VALUES ( " + amount + ", " + code + ", '" + dateOnly.format(dNow) + "' )";
            System.out.println("PayOrderQuery: " + issuePayment);
            
            db.setQuery(issuePayment);
            issuePay_amtTxtFld.setText("");
            Dialogs.create().title("Succesfull pay Issue").message("Supplier payment issued succesfully").showInformation();
            loadSupplierCodeCombox();
        } catch (SQLException | IllegalStateException sQLException) {
            Dialogs.create().title("Issue Error").message("There was an error while issueing the debt").showError();
        }
    }

    @FXML
    private void addTransClass(MouseEvent event) throws SQLException {
        dNow = new Date();
        ComboItem cType = (ComboItem) this.addTransClassTypeCombx.getSelectionModel().getSelectedItem();
        String transType = cType.getValue();
        String name = addTransClassNameTxtFld.getText(); addTransClassNameTxtFld.setText("");
        String desc = addTransClassDescTxtA.getText(); addTransClassDescTxtA.setText("");
        int code = Integer.parseInt(addTransClassCodeTxtFld.getText()); addTransClassCodeTxtFld.setText("");
        String verifyNoDuplicate, addTransClass;
        if(transType == "income"){//we have the addition of an expense trans class
            verifyNoDuplicate = "SELECT njieDB.INC_TRANS_CLASS.CODE FROM njieDB.INC_TRANS_CLASS WHERE "
                    + "njieDB.INC_TRANS_CLASS.CODE = " + code + "";
            System.out.println("INC VEQ: " + verifyNoDuplicate);
            try {
                db.setQuery(verifyNoDuplicate);
                if (db.getRowCount() > 0) {
                    Dialogs.create().title("Transaction class exists").message("Transaction class already with code"
                            + code + "already exists. Change it and try again").showWarning();
                    addTransClassResponse.setText("Transaction class already exists with that code");
                    return;
                } else {
                    addTransClass = "INSERT INTO njieDB.INC_TRANS_CLASS ( njieDB.INC_TRANS_CLASS.CODE, "
                            + " njieDB.INC_TRANS_CLASS.NAME, njieDB.INC_TRANS_CLASS.INFO,  "
                            + " njieDB.INC_TRANS_CLASS.C_DATE )"
                            + " VALUES (" + code + ", '" + name + "', '" + desc + "', '" + ft.format(dNow) + "' )";
                    System.out.println("INC :" + addTransClass);
                    db.setQuery(addTransClass);
                    Dialogs.create().title("Successful creating").message("Creation done succesfull").showInformation();
                    addTransClassResponse.setText("Transaction class inserted succesfully");
                }
            } catch (SQLException sQLException) {
                sQLException.printStackTrace();
            } catch (IllegalStateException illegalStateException) {
                System.out.println("illegal inc trans class");
            }
        }
        
        else{//we have the addition of an expense trans class
            
            verifyNoDuplicate = "SELECT njieDB.EXP_TRANS_CLASS.CODE FROM njieDB.EXP_TRANS_CLASS WHERE "
                    + "njieDB.EXP_TRANS_CLASS.CODE = " + code + "";
            System.out.println("EXP VEQ: " + verifyNoDuplicate);
            db.setQuery(verifyNoDuplicate);
            if(db.getRowCount()>0){
                Dialogs.create().title("Transaction class exists").message("Transaction class already with code"
                       + code + "already exists. Change it and try again").showWarning();
                addTransClassResponse.setText("Transaction class already exists with that code");
            }
            else{
                addTransClass = "INSERT INTO njieDB.EXP_TRANS_CLASS (njieDB.EXP_TRANS_CLASS.CODE, "
                        + " njieDB.EXP_TRANS_CLASS.NAME, njieDB.EXP_TRANS_CLASS.INFO, "
                        + " njieDB.EXP_TRANS_CLASS.C_DATE ) "
                        + " VALUES (" + code + ", '" + name +  "', '"  +  desc +  "', '" + ft.format(dNow) + "' )";
                System.out.println("EXP :" + addTransClass);
                db.setQuery(addTransClass);
                Dialogs.create().title("Successful creating").message("Creation done succesfull").showInformation();
                addTransClassResponse.setText("Transaction class inserted succesfully");
            }
            /*chek.checkExpTransClass();
            chek.checkIncTransClass();*/
        }   
    }
    
    private void displayTransactionClassTable(String table)
    {        
        viewTransClass_Tcode.setCellValueFactory(new PropertyValueFactory("code"));
        viewTransClass_Tname.setCellValueFactory(new PropertyValueFactory("name"));
        viewTransClass_Tdesc.setCellValueFactory(new PropertyValueFactory("desc"));
        //viewTransClass_Tusage.setCellValueFactory(new PropertyValueFactory("usage"));
        
        viewTransClass_table.getColumns().clear();
        viewTransClass_table.getColumns().addAll(viewTransClass_Tcode, viewTransClass_Tname, viewTransClass_Tdesc);
        System.out.println("transClassGen ");
        ArrayLcodeTC = getAllData(table + ".CODE", table );
        ArrayLnameTC = getAllData(table + ".NAME", table );
        ArrayLdescTC = getAllData(table + ".INFO", table );
        //ArrayLusageTC = getAllData("njieDB.INC_TRANS_CLASS.USAGE", "njieDB.INC_TRANS_CLASS");
        
        
        OLcodeTC =  FXCollections.observableArrayList(ArrayLcodeTC);
        OLnameTC =  FXCollections.observableArrayList(ArrayLnameTC);
        OLdescTC =  FXCollections.observableArrayList(ArrayLdescTC);
        //OLusageTC =  FXCollections.observableArrayList(ArrayLIncamount);
        composeTransClass = FXCollections.observableArrayList();
        
        String tmp;
        for(int i = 0 ;  i < ArrayLcodeTC.size(); i++){
           TransClass Tdata = new TransClass();
           Tdata.code.setValue(ArrayLcodeTC.get(i).toString());
           Tdata.name.setValue(ArrayLnameTC.get(i).toString());
           Tdata.desc.setValue(ArrayLdescTC.get(i).toString());
           composeTransClass.add(Tdata);
        }
        try {
            viewTransClass_table.getItems().setAll(composeTransClass);
            viewTransClass_table.setItems(composeTransClass);

        } catch (Exception nn) {
            System.out.println(nn.getMessage());
        }
        enableSelectForUpdateAndDelete(viewTransClass_table);
        miniPaneForTransClass.setDisable(true);
        
    }

    @FXML
    private void addStaff(MouseEvent event) throws SQLException {
        ComboItem cBranch = (ComboItem) this.addStaff_branchCmbBx.getSelectionModel().getSelectedItem();
        String name = addStaff_nameTxtFlx.getText();
        int staff_id = Integer.parseInt(addStaff_codeTxtFld.getText());
        String branch = cBranch.getValue();
        int salary = Integer.parseInt(addStaff_salaryTxtFlx.getText());
        int phoneN = Integer.parseInt(addStaff_phoneTxtFlx1.getText());
        //clear every field
        addStaff_codeTxtFld.setText("");
        addStaff_nameTxtFlx.setText("");
        addStaff_salaryTxtFlx.setText("");
        addStaff_phoneTxtFlx1.setText("");
        
        //check for empty fields.
        //if()
        
        String testCode;
        testCode = "SELECT njieDB.STAFF.STAFF_ID FROM njieDB.STAFF WHERE "
                + " njieDB.STAFF.STAFF_ID = " + staff_id + " ";
        System.out.println("addStaffTestCode :" + testCode);
        db.setQuery(testCode);
        if(db.getRowCount() > 0)
        {
             Dialogs.create().title("Duplicate Staff Code ").message("Staff already exists with id "
                       + staff_id +"").showWarning();
             addStaff_response.setText("Failed to add staff");
        }
        else{
            Date dnow = new Date();
            SimpleDateFormat dat = new SimpleDateFormat("yyyy-MM-dd");
            String addStaff;
            addStaff = "INSERT INTO njieDB.STAFF ( njieDB.STAFF.STAFF_ID, njieDB.STAFF.NAME, "
                    + " njieDB.STAFF.PHONE_NUMBER, njieDB.STAFF.SALARY, njieDB.STAFF.BRANCH, njieDB.STAFF.EMP_DATE ) VALUES "
                    + "( " + staff_id + ", '" + name + "', " + phoneN + ", " + salary + ", '" + branch + "', '" + dat.format(dnow) + "' ) ";
            System.out.println("addStaff : " + addStaff );
            try {
                db.setQuery(addStaff);
                Dialogs.create().title("succes").message("Staff added succesfully").showInformation();
            } catch (SQLException | IllegalStateException sQLException) {
                sQLException.printStackTrace();
            }
        }
    }
    
    ArrayList ArrayLViewStaffcode;
    ArrayList ArrayLViewStaffname;
    ArrayList ArrayLViewStaffphone;
    ArrayList ArrayLViewStaffsalary;
    ArrayList ArrayLViewStaffbranch;
    
    ObservableList OLViewStaffcode;
    ObservableList OLViewStaffname;
    ObservableList OLViewStaffphone;
    ObservableList OLViewStaffsalary;
    ObservableList OLViewStaffbranch;
    ObservableList<ViewStaffTable> composeViewStaff;
    
    /*variables to get the the index of the selected cells from a clicked table column*/
    int[] EDIT_staff = new int[2];
    int[] EDIT_information = new int[2];
    
    /*******************populating the view staff table***************/
    private void populateViewStaffTable() throws SQLException
    {   
        String Q_staf = "select njieDB.STAFF.STAFF_ID, njieDB.STAFF.NAME, njieDB.STAFF.PHONE_NUMBER, "
                + " njieDB.STAFF.SALARY, njieDB.STAFF.BRANCH FROM njieDB.STAFF";
        tab.makeTable(Q_staf, viewStaff_code_col, viewStaff_name_col, 
                viewStaff_phone_col, viewStaff_salary_col, 
                viewStaff_branch_col, viewStaff_table);
         
        enableSelectForUpdateAndDelete(viewStaff_table);
        
    }
    
    public void enableSelectForUpdateAndDelete(TableView  taView){
        /*TRYING TO GET SELECTED ITEM'S ID TO BE ABLE TO MAKE CHANGES(EDIT/DELETE)*/
        taView.getSelectionModel().setCellSelectionEnabled(true);
        final ObservableList<TablePosition> selectedCells = taView.getSelectionModel().getSelectedCells();
            selectedCells.addListener(new ListChangeListener<TablePosition>() {

            @Override
            public void onChanged(ListChangeListener.Change changed) {
                for ( TablePosition pos : selectedCells ){
                    System.out.println("Cell selected in row " + pos.getRow() + 
                            "and column  " + pos.getColumn());
                    /*set the variables used for editing and deleting.*/
                    EDIT_staff[0] = pos.getRow();
                    EDIT_staff[1] = pos.getColumn();
                    
                    /*keepind the data so that the update/delete method can use it in doing its respective function of update and delete respectively*/
                    EDIT_information[0] = pos.getRow();
                    EDIT_information[1] = pos.getColumn();
                    
                    miniPaneForStaffEdit.setDisable(true);
                }
            }
        });
    }
    
    int s_id;
    private void getAndDisplayClickedStaffInfo(){
        String query;
        query = "SELECT njieDB.STAFF.STAFF_ID, njieDB.STAFF.NAME, njieDB.STAFF.PHONE_NUMBER, "
                + " njieDB.STAFF.SALARY, njieDB.STAFF.BRANCH, njieDB.STAFF.EMP_DATE FROM "
                + " njieDB.STAFF " ;
        System.out.println("query = " + query);
        try {
            db.setQuery(query);
            int r=EDIT_staff[0], c=EDIT_staff[1];
            
            String staff_id = db.getValueAt(r, 0).toString();
            this.s_id = Integer.parseInt(staff_id);
            String staff_name = db.getValueAt(r, 1).toString();
            String staff_phone = db.getValueAt(r, 2).toString();
            String staff_salary = db.getValueAt(r, 3).toString();
            String staff_branch = db.getValueAt(r, 4).toString();
            String staff_empDate = db.getValueAt(r, 5).toString();
            
            /*Fill the elements now*/
            
            input_EDIT_staff_staffID.setText(staff_id);
            input_EDIT_staff_staffID.setDisable(true);
            input_EDIT_staff_staffName.setText(staff_name);
            input_EDIT_staff_phoneNumber.setText(staff_phone);
            input_EDIT_staff_salary.setText(staff_salary);
            //input_EDIT_staff_branch.setText(staff_branch);
            this.staffRep.displayStaffBranchCombo(input_EDIT_staff_branch);
            
        } catch (SQLException | IllegalStateException sQLException) {
            sQLException.printStackTrace();
        }
    }

    @FXML
    private void leave_manager(MouseEvent event) {
        admin_space.setVisible(false);
        welcome_pane.setVisible(true);
    }

    @FXML
    private void viewExpTransClass(MouseEvent event) {
        displayTransactionClassTable("njieDB.EXP_TRANS_CLASS");
        vInvExp.setText("EXPENSES");
        transClassType = "expenditure";
        miniPaneForTransClass.setDisable(true);
    }

    @FXML
    private void viewIncTransClass(MouseEvent event) {
        displayTransactionClassTable("njieDB.INC_TRANS_CLASS");
        vInvExp.setText("INCOME");
        transClassType = "income";
        miniPaneForTransClass.setDisable(true);
    }

    private void clearAllManagerReports(){
        report_custumer_D.setVisible(false);
        report_supplier_D.setVisible(false);
        report_staff_D.setVisible(false);
        report_supplier_D.setVisible(false);
    }
    private void report_staff(MouseEvent event) {
        clearAllManagerReports();
        report_staff_D.setVisible(true);
        mreport.staff();
    }

    private void report_genTrans(MouseEvent event) throws SQLException {
        clearAllManagerReports();
        report_genTrans_D.setVisible(true);
        report_genTransactions("njieDB.INCOME", "njieDB.INC_TRANS_CLASS");
        //mreport.genTrans("njieDB.EXPENSE");
    }

    private void report_supplier(MouseEvent event) {
        clearAllManagerReports();
        report_supplier_D.setVisible(true);
        mreport.supplier();
    }

    private void report_custumer(MouseEvent event) {
        clearAllManagerReports();
        report_custumer_D.setVisible(true);
        mreport.custumer();
    }
    
    
    
    
    
    
    
    
    
    
    
    /*******************************@generalTransactionReports ************/
    private TableView<TableClass> report_genTrans_table;
    private TableColumn<TableClass, String> report_GT_code;
    private TableColumn<TableClass, String> report_GT_name;
    private TableColumn<TableClass, String>  report_GT_desc;
    private TableColumn<TableClass, String> report_GT_totamt;
    private TableColumn<TableClass, String> report_GT_time;
    
    /* ArrayList ArrayLGTcode = new ArrayList();
    ArrayList ArrayLGTname = new ArrayList();
    ArrayList ArrayLGTdesc = new ArrayList();
    ArrayList ArrayLGTtotamt = new ArrayList();
    ArrayList ArrayLGTtime = new ArrayList();
    
    ObservableList OLGTcode;
    ObservableList OLGTname;
    ObservableList OLGTdesc;
    ObservableList OLGTtotamt;
    ObservableList OLGTtime;
    
    ObservableList<IncomeTable> composeGenTrans;
    
    
    
    
    public void genTrans(String table) throws SQLException {
    report_GT_code.setCellValueFactory(new PropertyValueFactory("code"));
    report_GT_name.setCellValueFactory(new PropertyValueFactory("name"));
    report_GT_desc.setCellValueFactory(new PropertyValueFactory("memo"));
    report_GT_totamt.setCellValueFactory(new PropertyValueFactory("amount"));
    report_GT_time.setCellValueFactory(new PropertyValueFactory("time"));
    
    report_genTrans_table.getColumns().clear();
    report_genTrans_table.getColumns().addAll(report_GT_code, report_GT_name, report_GT_desc, report_GT_totamt);
    
    ArrayLGTcode = getAllDataWhere(table + ".CODE", table);
    ArrayLGTname = getAllDataWhere(table + ".NAME", table );
    ArrayLGTdesc = getAllDataWhere(table + ".MEMO", table);
    ArrayLGTtotamt = getAllDataWhere(table + ".AMOUNT", table);
    ArrayLGTtotamt = getAllDataWhere(table + ".TRANS_ID", table);
    
    
    OLGTcode =  FXCollections.observableArrayList(ArrayLGTcode);
    OLGTname =  FXCollections.observableArrayList(ArrayLGTname);
    OLGTdesc =  FXCollections.observableArrayList(ArrayLGTdesc);
    OLGTtotamt =  FXCollections.observableArrayList(ArrayLGTtotamt);
    OLGTtime =  FXCollections.observableArrayList(ArrayLGTtime);
    composeGenTrans = FXCollections.observableArrayList();
    
    String tmp;
    for(int i = 0 ;  i < ArrayLGTdesc.size(); i++){
    IncomeTable GTdata = new IncomeTable();
    GTdata.code.setValue(ArrayLGTcode.get(i).toString());
    GTdata.name.setValue(ArrayLGTname.get(i).toString());
    GTdata.memo.setValue(ArrayLGTdesc.get(i).toString());
    GTdata.amount.setValue(ArrayLGTtotamt.get(i).toString());
    GTdata.time.setValue(ArrayLGTtime.get(i).toString());
    composeGenTrans.add(GTdata);
    }
    
    try {
    report_genTrans_table.getItems().setAll(composeGenTrans);
    report_genTrans_table.setItems(composeGenTrans);
    
    } catch (Exception nn) {
    System.out.println(nn.getMessage());
    }
    }*/
    
    public void staff(){//STILL TO BE WRITTEN
        
    }
    
    public void supplier(){//STILL TO BE IMPLEMENETED
        
    }
    
    public void custumer(){//STILL TO BE IMPLEMENTED
        
    }
    
    public ArrayList getAllDataWhere(String data, String table) {
        ArrayList re = new ArrayList();
        try {

            String sql = "select " + data + " from " + table;
            //  DatabaseHelper db = new DatabaseHelper();
            System.out.println(sql);
            re = db.ExecuteQuery(sql);

        } catch (Exception sh) {
            System.out.println(sh.getMessage());

        }
        return re;

    }

    @FXML
    private void manViewIncTrans() throws SQLException {
//        repGTIncExp.setText("INCOME");
//        report_genTransactions("njieDB.INCOME", "njieDB.INC_TRANS_CLASS");
        //reportsContainer.setScreen(REPORTS_ID);
        
    }

    private void manViewExpTrans(MouseEvent event) throws SQLException {
        repGTIncExp.setText("EXPENSES");
        report_genTransactions("njieDB.EXPENSES", "njieDB.EXP_TRANS_CLASS");
        
    }

    /*@FXML
    private void manViewIncTrans(Event event) throws SQLException {
    genTrans("njieDB.INCOME");
    }*/

    

    @FXML
    private void validateInput(KeyEvent event) {
        usefullstuff.restrictToNumbers(event);
    }

    @FXML
    private void manager_issue_OD_to_database(MouseEvent event) throws SQLException, ParseException {
        
        System.out.println("Inserting date into OVERDRAFT TABLE.");
        /*take all inputs from the form and store them in the variables*/
        int staffId = Integer.parseInt(input_OD_staffName.getText());
        long amount = Long.parseLong(input_OD_Amount_TxtFld.getText());
        long monthlyDeduction = Long.parseLong(input_OD_monthlyDeduction_TxtFld.getText());
        int numOfMonthsForDEduction;
        if(amount % monthlyDeduction == 0){
            numOfMonthsForDEduction = (int)(amount / monthlyDeduction);
            dNow = new Date( );
            String StartDeductionFromDate = null;
            try {
                StartDeductionFromDate = dateOnly.format(dateOnly.parse(this.startDeductionDate.getValue().toString()));
                System.out.println("we are starting deduction from " + StartDeductionFromDate);
            } catch (Exception ex) {
                Logger.getLogger(NjieController.class.getName()).log(Level.SEVERE, null, ex);
                Dialogs.create().message("please input the start date of deduction");
                return ;
            }
            String xpDate = usefullstuff.addSubtractMonthFromDate(StartDeductionFromDate, numOfMonthsForDEduction);
            System.out.println("expiry date immediately after computation is " + xpDate);
            


            //composing the query
            String addOD;
            addOD = "INSERT INTO njieDB.OVERDRAFT( njieDB.OVERDRAFT.STAFF_ID, "
                 + " njieDB.OVERDRAFT.AMOUNT, njieDB.OVERDRAFT.DEDUCTION_PER_MONTH, njieDB.OVERDRAFT.DEDUCTION_START_DATE, "
                 + " njieDB.OVERDRAFT.DEDUCT_UNTIL, njieDB.OVERDRAFT.DATE, njieDB.OVERDRAFT.TIM_ISSUED )"
                 + " VALUES (" + staffId + ", " + amount + ", " + monthlyDeduction + ", '" + StartDeductionFromDate 
                 + "', '" + xpDate + "', '" + dateOnly.format(dNow) + "', '" + tim.format(dNow) + "')";
            System.out.println("Overdraft query : \n" + addOD);


            /*Executing the query for adding the overdraft to the database*/
            try {
                db.setQuery(addOD);
                Dialogs.create().title("Success").message("Overdraft created succesfully").showInformation();
            } catch (SQLException | IllegalStateException sQLException) {
                sQLException.printStackTrace();
                Dialogs.create().title("Error").message("There was an error issueing the overdraft").showError();
            }
            /*Clearing all fields*/
            input_OD_Amount_TxtFld.setText("");
            input_OD_monthlyDeduction_TxtFld.setText("");
            startDeductionDate.setValue(LocalDate.now());
            chek.checkOverdraft();
        }else{
            //the amount deduton is not a factor of tge amount issued
            Dialogs.create().masthead("error").title("error").message("the monthlydeduction is not a multiple of the amount issed.");
        }
    }
    
    private void clearAllOverdraft(){
        manager_IssueOverdraft_Pane.setVisible(false);
        manager_viewOverdraft_Pane.setVisible(false);
        manager_defaultOverdraft.setVisible(false);
        manager_viewOverdraft_Pane.setVisible(false);
        overDraftEditPane.setVisible(false);
  //  -----------------------    overDraftEditPane.setVisible(false);  ------------------------
        
    }

    @FXML
    private void manager_IssueOverdraft(MouseEvent event) throws SQLException {
        clearAllOverdraft();
        manager_IssueOverdraft_Pane.setVisible(true);
        //loadStaffID_forPayroll(input_OD_staffName_Cmbx);
    }

    @FXML
    private void manager_editOverdraft(MouseEvent event) {//NOT YET IMPLEMENTED
    }
    
    @FXML
    private void manager_deleteOverdraft(MouseEvent event) {//NOT YET IMPLEMENTED
    }

    @FXML
    private void manager_viewOverdraft(MouseEvent event) throws SQLException {
        clearAllOverdraft();
        manager_viewOverdraft_Pane.setVisible(true);
        displayOverdraftTable();
        Dialogs.create().masthead("Edit Tip").message("Select a record from the table and click the "
                                              +  " edit button below to edit the record OR DELETE  to delete the record").showWarning();

        System.out.println("after populating table");
   
    }

    @FXML
    private void activate_defaultManager_Overdraft(Event event) {
        clearAllOverdraft();
        manager_defaultOverdraft.setVisible(true);
    }
    
    /*FOR THE OVERDRAFT TABLE GENERATION*/
    private ObservableList<TableClass> composeOverdraft;
    private void displayOverdraftTable() throws SQLException{
        //sddf
         viewOverdraft_table.setDisable(false);
        viewOverdraft_table.setVisible(true);
        
      String query= "SELECT njieDB.STAFF.NAME , njieDB.OVERDRAFT.AMOUNT ,"
                  + "njieDB.OVERDRAFT.DATE , njieDB.OVERDRAFT.DEDUCTION_START_DATE,"
                  + "njieDB.OVERDRAFT.DEDUCT_UNTIL DATE , njieDB.OVERDRAFT.PAY_STATUS,"
                  + "njieDB.OVERDRAFT.OV_ID FROM  njieDB.STAFF ,njieDB.OVERDRAFT"
                  + " WHERE njieDB.OVERDRAFT.STAFF_ID = njieDB.STAFF.STAFF_ID ";
        
        System.out.println("info for table : " + query);
        
        viewOverdraft_StaffCode_col.setCellValueFactory(new PropertyValueFactory("col1"));
        viewOverdraft_AmountIssued_col.setCellValueFactory(new PropertyValueFactory("col2"));
        viewOverdraft_DateIssued_col.setCellValueFactory(new PropertyValueFactory("col3"));
        viewOverdraft_deductionStartDate_col.setCellValueFactory(new PropertyValueFactory("col4"));
        viewOverdraft_ExpiryDate_col.setCellValueFactory(new PropertyValueFactory("col5"));
        viewOverdraft_Status_col.setCellValueFactory(new PropertyValueFactory("col6"));
        viewOverdraft_OVCODE_col.setCellValueFactory(new PropertyValueFactory("col7"));
        
        composeOverdraft = FXCollections.observableArrayList();
        this.viewOverdraft_table.setItems(composeOverdraft);
        
       
          db.setQuery(query);
             System.out.println("before loop");
          	for (int i = 0; i < db.getRowCount(); i++) {
            	TableClass trans = new TableClass();
            
            trans.col1.setValue(db.getValueAt(i, 0).toString());
            trans.col2.setValue(db.getValueAt(i, 1).toString());
            trans.col3.setValue(db.getValueAt(i, 2).toString());
            trans.col4.setValue(db.getValueAt(i, 3).toString());
            trans.col5.setValue(db.getValueAt(i, 4).toString());
            //trans.col6.setValue(db.getValueAt(i, 5).toString()); 
            trans.col7.setValue(db.getValueAt(i, 6).toString());
            
            if( "true".equals(db.getValueAt(i, 5).toString())){
                trans.col6.setValue("paid to worker");
            }else{
                trans.col6.setValue("unpaid");
            }
         
            composeOverdraft.add(trans);
      	}
      
        
       
    }
    
    
    
    
    
    /**************************************************
     * **************************************************
     * **************************************************
     * *************************************************************************************      CUSTUMER
     * **************************************************
     * **************************************************
     * 
     */
    
    
    private void clearAllCustSubPanes(){
        manager_Custumer_issueDebt.setVisible(false);
        //manager_editCustumer.setVisible(false);
        //manager_deleteCustumer.setVisible(false);
        manager_viewCustumer.setVisible(false);
        manager_defaultCustumer.setVisible(false);
        manager_addCustumer.setVisible(false);
        manager_EditCustumerIssuedDebtDisplayPane.setVisible(false);
    }
    
        @FXML
        private void manager_addCustumer(MouseEvent event) {
            clearAllCustSubPanes();
            manager_addCustumer.setVisible(true);
        }

        @FXML
        private void manager_editCustumer(MouseEvent event) {
            clearAllCustSubPanes();
            manager_editCustumer.setVisible(true);
        }

        @FXML
        private void manager_deleteCustumer(MouseEvent event) {
            clearAllCustSubPanes();
            manager_deleteCustumer.setVisible(true);
        }

        @FXML
        private void manager_viewCustumer(MouseEvent event) throws SQLException {
            clearAllCustSubPanes();
            manager_viewCustumer.setVisible(true);
            miniPaneForCustumer.setDisable(true);
            displayCustumerInformationInTable();
        }
        
        @FXML
        private void displayCustomerManagementPane(ActionEvent event) throws SQLException {
            clearAllCustSubPanes();
            manager_EditCustumerIssuedDebtDisplayPane.setVisible(true);
            populateCustDebtForEdit();
        }
        
        
        
        public void populateCustDebtForEdit() throws SQLException{
            String query = "select njieDB.DEBTS.DEBT_ID, njieDB.CUSTUMER.NAME,"
                    + " njieDB.DEBTS.AMOUNT, njieDB.DEBTS.DATE_ISSUED"
                    + " FROM njieDB.CUSTUMER, njieDB.DEBTS "
                    + " where njieDB.DEBTS.CUST_ID = njieDB.CUSTUMER.CUST_ID "
                    + "ORDER BY njieDB.DEBTS.DATE_ISSUED DESC";
            System.out.println("query to populate custumer debts table for editing is \n" + query);
            tab.makeTable(query, editCustDebt_code, 
                    editCustDebt_name, editCustDebt_amt, 
                    editCustDebt_date, editCustumerDebtsTable);
        }



        @FXML
        private void manager_custumer_issueDebt(MouseEvent event) throws SQLException {
            clearAllCustSubPanes();
            manager_Custumer_issueDebt.setVisible(true);
            loadCustumerId(CustumerCode_issueDebtCmbox);
        }

        @FXML
        private void activate_defaultManager_Custumer(Event event) {
            clearAllCustSubPanes();
            manager_defaultCustumer.setVisible(true);
        }
        
        @FXML
        private void manager_editIssuedCustDebt(MouseEvent event) throws SQLException {
            
//fill table
        }

        
        
        /*logic*/
    /*---------------------------adding a custumer to to the database------------------------*/
    @FXML
    private void manager_issue_CustumerDebt(MouseEvent event) throws SQLException {
       // chek.checkDebtsIssued();
        /*getting custumer id */
        dNow = new Date();
        String value =  this.CustumerCode_issueDebtCmbox.getEditor().getText();
        String custID = getKey(value);
        long amount = Integer.parseInt(issueDebt_amtTxtFld.getText());
            String QaddCustdebt;
            QaddCustdebt =" INSERT INTO njieDB.DEBTS( "
                    + " njieDB.DEBTS.CUST_ID, njieDB.DEBTS.AMOUNT, njieDB.DEBTS.DATE_ISSUED ) "
                    + " VALUES ( '" + custID + "', " + amount + ", '" + ft.format(dNow) + "' )";
            System.out.println("Q issue debt : " + QaddCustdebt);
            
            try {
                db.setQuery(QaddCustdebt);
                Dialogs.create().title("Success").message("Debt added succesfully").showInformation();
            } catch (SQLException | IllegalStateException ex) {
                Dialogs.create().title("error").message("There was an error adding the debt").showError();
                Logger.getLogger(NjieController.class.getName()).log(Level.SEVERE, null, ex);
            }
        issueDebt_amtTxtFld.setText("");
        this.loadCustumerId(CustumerCode_issueDebtCmbox);
        
    }
    
    @FXML
    private void addCustumer_to_database(MouseEvent event) throws SQLException {
        String custID = add_CustumerCodeTxtFld.getText();
        String custName = add_CustumerNameTxtFld.getText();
        String prefGoods = add_CustumerServiceTxtFld.getText();
        
        chek.checkCust();
        if(usefullstuff.isEmpty(custID, custName, prefGoods) || causesDuplicate("njieDB.CUSTUMER", "njieDB.CUSTUMER.CUST_ID", custID))
        {}
        else{
            if(addCustumer(custID, custName, prefGoods)){
                Dialogs.create().title("Success").message("Custumer added succesfully").showInformation();
            }
        }
        add_CustumerCodeTxtFld.setText("");
        add_CustumerNameTxtFld.setText("");
        add_CustumerServiceTxtFld.setText("");
      
    }

    
    /*
    model
    */
    
     /*Creating a method that will insert data into the custumer's table */
    private boolean addCustumer(String custId, String custName, String prefGoods) throws SQLException{
        
        SimpleDateFormat timestamp, yearMonthDay;
        yearMonthDay = new SimpleDateFormat("yyyy-MM-dd");
        //take today's date
        dNow = new Date( );
        
        String addCust = "INSERT INTO njieDB.CUSTUMER "
                + " (njieDB.CUSTUMER.CUST_ID, njieDB.CUSTUMER.NAME, njieDB.CUSTUMER.PREFERED_GOODS, njieDB.CUSTUMER.C_DATE ) VALUES "
                + "( '" + custId  +  "', '"  + custName  +  "', '" + prefGoods + "', '" + yearMonthDay.format(dNow) + "' )";
        
        System.out.println("Q add custumre : " + addCust );
        //test if the query will be executed succesfully and determines the appropriate return value
        
            db.setQuery(addCust);
        
        
        return true;//query executed succesfully
    
    }
    
    private boolean causesDuplicate(String table, String column, String value){
        String getKey;
        getKey = "SELECT " + column + " from " + table + " where " + column + " = '" + value + "'" ;
        System.out.println("Q Duplicate check : " + getKey);
        
        if((db.getRowCount() < 0)){
            Dialogs.create().title("Error").message("There is another custumer with the same name").showError();
            return true;
        }
        return false;
    }
    
    private void loadCustumerId(ComboBox custBox) throws SQLException
    {
        custBox.getItems().clear();//remove all elements from the list
        String getData = "SELECT njieDB.CUSTUMER.CUST_ID, njieDB.CUSTUMER.NAME FROM njieDB.CUSTUMER";
        db.setQuery(getData);
        clearArrayList();
        for(int i = 0 ; i < db.getRowCount() ; i++){
            String code = db.getValueAt(i, 0).toString();
            String name = db.getValueAt(i, 1).toString();
            //fill it to the combo box
            custBox.getItems().add(new ComboItem(name, code));
            System.out.printf(" %s\n", name);
            //fill the array list
            
            ComboValue.add(i, name);
            ComboKey.add(i, code);
        }
        //custBox.getSelectionModel().selectFirst();
        System.out.println("Finished loading the combo box for the supplier's code");
        AutoCompleteComboBoxListener autocom = new AutoCompleteComboBoxListener<>(custBox);
    }

    @FXML
    private void pay_debt_writeToDatabase(MouseEvent event) throws ParseException, SQLException {
        String Custname = "";
        //get the custumer's name to display on the confirmation box.
        try { 
            db.setQuery("SELECT njieDB.CUSTUMER.NAME FROM njieDB.CUSTUMER WHERE njieDB.CUSTUMER.CUST_ID = '" + this.custId + "'" );
            Custname = db.getValueAt(0, 0).toString();
        } catch (SQLException | IllegalStateException ex) {
            Dialogs.create()
                    .title("failed getx cust name\n")
                    .message("Seems custumer with id = " + this.custId + " is not in the system")
                    .showError();
            Logger.getLogger(NjieController.class.getName()).log(Level.SEVERE, null, ex);
            return ;
        }
        dNow = new Date();
        
        //get the inputed amount
        int inputamtToPay = Integer.parseInt(debt_ouIn_balanceDebt.getText());
        
        //display confirmation so that the user can be sure of what he/she is about to commit.
        if(inputamtToPay <= this.custumerBal){ //som the amount inputed is not more than required
            
            Action confirmer = Dialogs.create().
                    title("confirm transaction")
                    .masthead("amount : " + inputamtToPay + "custName   : " + Custname)
                    .message("Add transaction to database?")
                    .showConfirm();
                   
            //user accepts to commit the inputed amount
            if (confirmer == Dialog.Actions.YES) { 
                //insert the pay to the pay table
                String qMakeDebtPaymentEntry;//query to add entry to the debt_payment table
                String qAddIncomeEntry;//add the corresponding entry in the income table
                String memo = "Debt Payment by | " + Custname + " ";
                
                qMakeDebtPaymentEntry = "INSERT INTO njieDB.DEBT_PAYMENT( "
                        + " njieDB.DEBT_PAYMENT.EXP_ID, njieDB.DEBT_PAYMENT.CUST_ID,  "
                        + " njieDB.DEBT_PAYMENT.AMOUNT_PAID, njieDB.DEBT_PAYMENT.DATE) VALUES "
                        + " (1, '" + this.custId + "', " + inputamtToPay + ", '"
                        + ft.format(dNow) + "')";
                qAddIncomeEntry = "INSERT INTO njieDB.INCOME (njieDB.INCOME.CODE,  "
                            +"njieDB.INCOME.MEMO, njieDB.INCOME.AMOUNT, njieDB.INCOME.DAT, njieDB.INCOME.TIM) VALUES ("
                            +" " + 1 + ", '" + memo + "', " 
                            + inputamtToPay + ", '" + dateOnly.format(dNow) 
                            + "', '" + tim.format(dNow) + "' )";
                
                //commit
                db.setQuery(qMakeDebtPaymentEntry);
                db.setQuery(qAddIncomeEntry);/* Add income */

                pane_toReceive_debtPayment.setVisible(false);
                pane_custumer_pay_expense_Transaction.setVisible(false);
                
                updateExpIncBal();
                fillCashierIncomeExpenseTable("INCOME", col_inc_code, 
                                            col_inc_memo, col_inc_amount, income_table);
                Dialogs.create().title("success").message("Debt paid partily with success").showInformation();
                /*Update the total income and balance displayed on the UI.*/
                balance.setText(Long.toString(chek.getBalafterInc(inputamtToPay)));
            }else{
            Dialogs.create().message("Deletion Canceled").showWarning();
            }
        }else{
             Dialogs.create().title("error").message("The amount entered is more than the total payable amount.").showError();
        }
        this.enableFromMakxTransaction();
        
    }

    @FXML
    private void updateStaffInformation(MouseEvent event) {
        
        ComboItem cBranch = (ComboItem) this.input_EDIT_staff_branch.getSelectionModel().getSelectedItem();
        String branch = cBranch.getValue();
        String Q_updateStaffInfo;
        
        Q_updateStaffInfo = "UPDATE njieDB.STAFF SET "
                + " njieDB.STAFF.NAME = '" + input_EDIT_staff_staffName.getText()
                + "', njieDB.STAFF.PHONE_NUMBER = " + input_EDIT_staff_phoneNumber.getText()
                + ", njieDB.STAFF.SALARY = " + input_EDIT_staff_salary.getText()
                + ", njieDB.STAFF.BRANCH = '" + branch 
                + "' WHERE njieDB.STAFF.STAFF_ID = " + s_id ;
        System.out.println("Query is : \n" + Q_updateStaffInfo);
        try {
            db.setQuery(Q_updateStaffInfo);
            Dialogs.create().title("succesfull update").message("Details of staff with id " + s_id + " has been modified succesfully").showInformation();
            miniPaneForStaffEdit.setDisable(true);
            populateViewStaffTable();
        } catch (SQLException | IllegalStateException sQLException) {
            Dialogs.create().title("error").message("There was an error updating staff information").showError();
            sQLException.printStackTrace();
        }
    }

    @FXML
    private void mini_editStaff(MouseEvent event) {
        miniPaneForStaffEdit.setDisable(false);
        getAndDisplayClickedStaffInfo();
    }

    @FXML
    private void mini_deleteStaff(MouseEvent event) {
        
       miniPaneForStaffEdit.setDisable(true);
       getAndDisplayClickedStaffInfo();
        
        String Q_deleteStaff;
        Q_deleteStaff = "DELETE FROM njieDB.STAFF WHERE njieDB.STAFF.STAFF_ID = " + s_id ;
        Action confirmer = Dialogs.create().
                    title("Delete confirmation").
                    masthead("You are about to delete user " + s_id).
                    message("Are you sure you want to continue?").showConfirm();
                   
        if (confirmer == Dialog.Actions.YES) {
            try {
                db.setQuery(Q_deleteStaff);
                Dialogs.create().title("Confirm").message("Staff with id =  " + s_id + " deleted from the system").showInformation();
                populateViewStaffTable();
            } catch (SQLException | IllegalStateException sQLException) {
                Dialogs.create().title("error").message("An error arose while trying to delete staff. Please try again").showError();
            } 
        }else {
                 Dialogs.create().message("Deletion Canceled").showWarning();
        }
   }
    
    int incExpCode;
    String transClassType ;
    public void getAndDisplayTransClass(){
        String queryInc, queryExp = "";
        queryInc = "SELECT njieDB.INC_TRANS_CLASS.CODE, njieDB.INC_TRANS_CLASS.NAME, "
                    + " njieDB.INC_TRANS_CLASS.INFO FROM njieDB.INC_TRANS_CLASS ";
        queryExp = "SELECT njieDB.EXP_TRANS_CLASS.CODE, njieDB.EXP_TRANS_CLASS.NAME, "
                    + " njieDB.EXP_TRANS_CLASS.INFO FROM njieDB.EXP_TRANS_CLASS ";
         int r = EDIT_information[0];
        try {
            if ("income".equals(transClassType)) {//information here has to deal with the income transactionc class
                db.setQuery(queryInc);
            } else {                
                db.setQuery(queryExp);
            }
            /*information here has to deal with the expenditure transaction class*/
            /*Get values and store */
            incExpCode = Integer.parseInt(db.getValueAt(r, 0).toString());
            String transClassName = db.getValueAt(r, 1).toString();
            String transClassDesc = db.getValueAt(r, 2).toString();

            /*fill the fields to be edited in the UI*/
            input_EDIT_transClass_transID.setText(String.valueOf(incExpCode));
            input_EDIT_transClass_transID.setDisable(true);
            input_EDIT_transClass_transName.setText(transClassName);
            input_EDIT_transClass_Desc.setText(transClassDesc);
            
        } catch (SQLException sQLException) {
        } catch (IllegalStateException illegalStateException) {
        } catch (NumberFormatException numberFormatException) {
        }
        
    }
    
    @FXML
    private void mini_deleteTransClass(MouseEvent event) {
        getAndDisplayTransClass();
        try {
            if (transClassType == "income") {
                Action confirmer = Dialogs.create().
                    title("Delete confirmation").
                    masthead("You are about to delete the transaaction " + transClassType ).
                    message("Are you sure you want to continue?").showConfirm();
                if (confirmer == Dialog.Actions.YES) {
                    db.setQuery("DELETE FROM njieDB.INC_TRANS_CLASS WHERE njieDB.INC_TRANS_CLASS.CODE = " + incExpCode);
                    Dialogs.create().title("success").message("Transaction class value deleted succesfully").showInformation();
                    displayTransactionClassTable("njieDB.INC_TRANS_CLASS");
              }else {
                    Dialogs.create().title("Cancellation").message("Deletion Cancel").showError();
                }
            }
                else {
                Action confirmer = Dialogs.create().
                    title("Delete confirmation").
                    masthead("You are about to delete the transaaction " + transClassType ).
                    message("Are you sure you want to continue?").showConfirm();
                if (confirmer == Dialog.Actions.YES) {
                       db.setQuery("DELETE FROM njieDB.EXP_TRANS_CLASS WHERE njieDB.EXP_TRANS_CLASS.CODE = " + incExpCode);
                       Dialogs.create().title("success").message("Transaction class value deleted succesfully").showInformation();
                       displayTransactionClassTable("njieDB.EXP_TRANS_CLASS");
            } else {
                   Dialogs.create().title("Cancellation").message("Deletion Cancel").showError(); 
                }}
            //Dialogs.create().title("success").message("Transaction class value deleted succesfully").showInformation();
        } catch (SQLException | IllegalStateException sQLException) {
            Dialogs.create().title("error").message("There was an error deleting the transaction class with id = " + incExpCode).showError();
        }
    }

    @FXML
    private void mini_editTransClass(MouseEvent event) {
        miniPaneForTransClass.setDisable(false);
        getAndDisplayTransClass();
    }
    
    @FXML
    private void updateTransClassInformation(MouseEvent event) throws SQLException {
         miniPaneForTransClass.setDisable(false);
        //check first which type of account is involved in this deleting.
         String updateInc, updateExp;
         updateInc = "UPDATE njieDB.INC_TRANS_CLASS SET "
                 + " njieDB.INC_TRANS_CLASS.NAME = '" + input_EDIT_transClass_transName.getText() 
                 + "', njieDB.INC_TRANS_CLASS.INFO = '" + input_EDIT_transClass_Desc.getText() + "' "
                 + " WHERE njieDB.INC_TRANS_CLASS.CODE = " + incExpCode ;
         
         updateExp = "UPDATE njieDB.EXP_TRANS_CLASS SET "
                 + " njieDB.EXP_TRANS_CLASS.NAME = '" + input_EDIT_transClass_transName.getText() 
                 + "', njieDB.EXP_TRANS_CLASS.INFO = '" + input_EDIT_transClass_Desc.getText() + "' "
                 + " WHERE njieDB.EXP_TRANS_CLASS.CODE = " + incExpCode ;
        System.out.println("updating inc class : " + updateInc);
        System.out.println("updating exp class : " + updateExp);
         try {
            if ("income".equals(transClassType)) {
                db.setQuery(updateInc);
                displayTransactionClassTable("njieDB.INC_TRANS_CLASS");
                System.out.println("in inc");
            } else {
                db.setQuery(updateExp);
                displayTransactionClassTable("njieDB.EXP_TRANS_CLASS");
                System.out.println("in exp");
            }
            Dialogs.create().title("ok").message("Updating of transaction class done succesfully").showInformation();
        } catch (SQLException | IllegalStateException sQLException) {
            sQLException.printStackTrace();
            Dialogs.create().title("error").message("There was an error updating the transaction class information").showError();
        }
            
    }

    int supplier_code;
    private void getAndDisplayClickedSupplierInfo(){
        String query;
        query = "SELECT njieDB.SUPPLIER.SUP_CODE, njieDB.SUPPLIER.NAME, njieDB.SUPPLIER.SERVICES "
                + " FROM  njieDB.SUPPLIER " ;
        System.out.println("query = " + query);
        try {
            db.setQuery(query);
            int r=EDIT_staff[0], c=EDIT_staff[1];
            
            String supp_id = db.getValueAt(r, 0).toString();
            this.supplier_code = Integer.parseInt(supp_id);
            String supplier_name = db.getValueAt(r, 1).toString();
            String supplier_service = db.getValueAt(r, 2).toString();
            
            
            /*Fill the elements now*/
            
            input_EDIT_supplier_ID.setText(supp_id);
            input_EDIT_supplier_ID.setDisable(true);
            input_EDIT_supplier_Name.setText(supplier_name);
            input_EDIT_supplier_service.setText(supplier_service);
            
        } catch (SQLException | IllegalStateException sQLException) {
            sQLException.printStackTrace();
        }
    }
    
    @FXML
    private void mini_deleteSupplier(MouseEvent event) throws SQLException {
        
        miniPaneForSupplier.setDisable(true);
        getAndDisplayClickedSupplierInfo();
        String Query = "DELETE FROM njieDB.SUPPLIER WHERE njieDB.SUPPLIER.SUP_CODE = " + supplier_code;
        Action confirmer = Dialogs.create().
                           title("Delete confirmation").
                           masthead("You are about to delete the supplier " + supplier_code).
                           message("Are you sure you want to continue?").showConfirm();
        if ( confirmer == Dialog.Actions.YES) {
        try {
            
            db.setQuery(Query);
            Dialogs.create().title("success").message("Supplier deleted succesfully").showInformation();
            populateSupplierTable();
        } catch (SQLException | IllegalStateException sQLException) {
            Dialogs.create().title("error").message("System encounterd an error while deleting the record. Pleas try again").showError();
            sQLException.printStackTrace();
         }
        }else {
             Dialogs.create().message("Delete Failed").showWarning();
        }
    }

    @FXML
    private void mini_editSupplier(MouseEvent event) {
        miniPaneForSupplier.setDisable(false);
        getAndDisplayClickedSupplierInfo();
    }

    @FXML
    private void updateSupplierInformation(MouseEvent event) throws SQLException {
        String Q_updateSupplierInfo;
        Q_updateSupplierInfo = "UPDATE njieDB.SUPPLIER SET "
                + " njieDB.SUPPLIER.NAME = '" + input_EDIT_supplier_Name.getText() + "', "
                + " njieDB.SUPPLIER.SERVICES = '" + input_EDIT_supplier_service.getText() + "' "
                + " WHERE njieDB.SUPPLIER.SUP_CODE = " + supplier_code;
        System.out.println("update supplier query = \n" + Q_updateSupplierInfo);
        try {
            db.setQuery(Q_updateSupplierInfo);
            Dialogs.create().title("Succesful update").message("Update completed succesfully").showInformation();
            populateSupplierTable();
        } catch (SQLException | IllegalStateException sQLException) {
            sQLException.printStackTrace();
            Dialogs.create().title("error").message("the system encountered and error while "
                    + "trying to update the supplier information. Please make sure"
                    + " that the fields are correctly filled.").showError();
        }
        
    }
    /*
    
    
    
    */
    
    private ObservableList<TableClass> report_composeIncExp;
         public void report_genTransactions(String table, String classTable) throws SQLException  {
            
            String query = "SELECT " + classTable + ".NAME, " + table + ".NAME, "
                    + table + ".MEMO, " + table + ".AMOUNT, " + table + ".DAT "  
                    + " FROM " + table + ", " + classTable 
                    + " WHERE " + table + ".CODE = " + classTable + ".CODE";
            System.out.println("query = \n" + query);
            this.report_GT_code.setCellValueFactory(new PropertyValueFactory<>("col1"));
            this.report_GT_name.setCellValueFactory(new PropertyValueFactory<>("col2"));
            this.report_GT_desc.setCellValueFactory(new PropertyValueFactory<>("col3"));
            this.report_GT_totamt.setCellValueFactory(new PropertyValueFactory<>("col4"));
            this.report_GT_time.setCellValueFactory(new PropertyValueFactory<>("col5"));
            
            report_composeIncExp = FXCollections.observableArrayList();
            this.report_genTrans_table.setItems(report_composeIncExp);
            
            System.out.println("Printing the Users History");
            
                 db.setQuery(query);
          
             System.out.println("before loop");
          	for (int i = 0; i < db.getRowCount(); i++) {
            	TableClass trans = new TableClass();
            
            trans.col1.setValue(db.getValueAt(i, 0).toString());
            trans.col2.setValue(db.getValueAt(i, 1).toString());
            trans.col3.setValue(db.getValueAt(i, 2).toString());
            trans.col4.setValue(db.getValueAt(i, 3).toString());
            trans.col5.setValue(db.getValueAt(i, 4).toString());
         
            report_composeIncExp.add(trans);
      	}
            enableSelectForUpdateAndDelete(report_genTrans_table);
      }

    private ObservableList<TableClass> report_composeCustumer;
    public void displayCustumerInformationInTable() throws SQLException{
        
            
            String query = "SELECT njieDB.CUSTUMER.CUST_ID, njieDB.CUSTUMER.NAME, njieDB.CUSTUMER.PREFERED_GOODS "
                    + " FROM njieDB.CUSTUMER";
             System.out.println("query = \n" + query);
            this.view_custumerTable_codeCol.setCellValueFactory(new PropertyValueFactory<>("col1"));
            this.view_custumerTable_nameCol.setCellValueFactory(new PropertyValueFactory<>("col2"));
            this.view_custumerTable_prefServCol.setCellValueFactory(new PropertyValueFactory<>("col3"));
            
            
            report_composeCustumer = FXCollections.observableArrayList();
            this.view_custumerTable.setItems(report_composeCustumer);
            
            System.out.println("Printing the Users History");
            
                 db.setQuery(query);
          
             System.out.println("before loop");
          	for (int i = 0; i < db.getRowCount(); i++) {
            	TableClass trans = new TableClass();
            
            trans.col1.setValue(db.getValueAt(i, 0).toString());
            trans.col2.setValue(db.getValueAt(i, 1).toString());
            trans.col3.setValue(db.getValueAt(i, 2).toString());
         
            report_composeCustumer.add(trans);
      	}
            enableSelectForUpdateAndDelete(view_custumerTable);
      
    }

    @FXML
    private void mini_deleteCustumer(MouseEvent event) {
        miniPaneForCustumer.setDisable(true);
        getSupplierClickedItem();
        String deleteCustumer;
        deleteCustumer = "DELETE FROM njieDB.CUSTUMER "
                       + " WHERE njieDB.CUSTUMER.CUST_ID = '" + custumer_id + "'";
        System.out.println("deleting custumer \n " + deleteCustumer);
        Action confirmer = Dialogs.create().
                           title("Delete confirmation").
                           masthead("You are about to delete the user " + custumer_id).
                           message("Are you sure you want to continue?").showConfirm();
        if ( confirmer == Dialog.Actions.YES) {
        
        try {
            db.setQuery(deleteCustumer);
            Dialogs.create().title("succes").message("custumer deleted from the system succesfully").showInformation();
            displayCustumerInformationInTable();
        } catch (SQLException | IllegalStateException sQLException) {
            sQLException.printStackTrace();
            Dialogs.create().title("succes").message("There was an error while deleting custumer from the system").showError();
        }
        }
        else {
             Dialogs.create().message("Delete Failed").showWarning();
        }
    }


    @FXML
    private void mini_editCustumer(MouseEvent event) {
        miniPaneForCustumer.setDisable(false);
        getSupplierClickedItem();
    }
    
    String custumer_id;
    public void getSupplierClickedItem(){
        
        
        String Q_custGet;
        Q_custGet = " SELECT njieDB.CUSTUMER.CUST_ID, njieDB.CUSTUMER.NAME, "
                + " njieDB.CUSTUMER.PREFERED_GOODS FROM njieDB.CUSTUMER ";
        System.out.println("Q load supplier : \n" + Q_custGet);
         int r = EDIT_information[0];
         System.out.println("clicked on cell " + r);
        try {
                        
                db.setQuery(Q_custGet);
                
            /*GET INFORMATION PARTAINING TO THE CLICKED CELL.*/
            /*Get values and store */
            custumer_id = db.getValueAt(r, 0).toString();
            String name = db.getValueAt(r, 1).toString();
            String service = db.getValueAt(r, 2).toString();
            System.out.println("information gathered :" +  String.valueOf(custumer_id) + name + service);

            miniPaneForCustumer.setDisable(false);
            /*fill the fields to be edited in the UI*/
            input_EDIT_custumer_ID.setText(String.valueOf(custumer_id));
            input_EDIT_custumer_ID.setDisable(true);
            input_EDIT_custumer_Name.setText(name);
            input_EDIT_custumer_prefServices.setText(service);
        } catch (SQLException | IllegalStateException | NumberFormatException sQLException) {
        Dialogs.create().message("system encounterd an error while loading supplier information for editing").showInformation();
        sQLException.printStackTrace();
        }
        
    
    }

    @FXML
    private void updateCustumerInformation(MouseEvent event) {
        String update;
        update = "UPDATE njieDB.CUSTUMER SET "
                + " njieDB.CUSTUMER.NAME = '" + input_EDIT_custumer_Name.getText() + "', "
                + " njieDB.CUSTUMER.PREFERED_GOODS = '" + input_EDIT_custumer_prefServices.getText() + "' "
                + " WHERE njieDB.CUSTUMER.CUST_ID = '" + custumer_id  + "'";
        System.out.println("updatx staff : \n" + update);
        try {
            db.setQuery(update);
            displayCustumerInformationInTable();
            Dialogs.create().title("succesfull update").message("Custumer details has been updated succesfully").showInformation();
            miniPaneForCustumer.setDisable(true);
        } catch (SQLException | IllegalStateException sQLException) {
            sQLException.printStackTrace();
            Dialogs.create().title(" Update error").message("There was a problem saving the staff's modified details").showError();
        }
    }

    @FXML
    private void report_searchButtonByStaffIdForPayroll(MouseEvent event) {
         Dialogs.create().message("U are trying to search for payroll by worker namd/id. Sorry, this feature is still under implementation").showInformation();
    }

    @FXML
    private void searchAllPayroll_HpLk(MouseEvent event) {
        Dialogs.create().message("U are trying to search for all payroll. Sorry, this feature is still under implementation").showInformation();
    }
    
    
    /*public void getInfoOnSupPayIsueOrCustDebt(String query) throws SQLException{
    db.setQuery(query);
    this.numOfUpaid = db.getRowCount();
    for(int i = 0; i < db.getRowCount(); i++){
    this.issueId[i] = Integer.parseInt(db.getValueAt(i, 0).toString());
    this.issuedAmt[i] = Integer.parseInt(db.getValueAt(i, 1).toString());
    }
    }*/
    
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

  
    
    //_----------------------------------------------------------------------------------------------------
    //  From here and below are the method that manage the the supplier issued      |    |
   // payments and the overdraft //-------------------------------------------   ___|    |___
                      //                                                         \         /
                      //                                                          \       /
                     //                                                            \     /
                    //                                                              \   / 
   //------------------------------------------------------------------------------------------------------ 
    
      @FXML
    private void diplayViewSupplierIssuedPaymentsPane(ActionEvent event) {
       
         clearSupplierScreen();
         manaageSupplierEditPane.setVisible(true);

       
        try {
            
            //here we display the table that shows the supplier info
          
            loadsuplierTable();
             Dialogs.create().masthead("Edit Tip").message("Select a record from the table and click the "
                                              +  " edit button below to edit the record").showWarning();
        } catch (Exception ex) {
            Logger.getLogger(NjieController.class.getName()).log(Level.SEVERE, null, ex);
        
            System.out.println("supplier table could not be filled");
        }
         
         
        
         
    }
    
    //   this method evlauates the update on the edit of the supplier 
    //   information
    
    
      @FXML
    private void saveSupplierUpdateInformation(ActionEvent event) throws SQLException {
    
                evaluateSupplierInfoUpdate();
                clearSupplierScreen();
                manaageSupplierEditPane.setVisible(true);
                editSupplierAmountAndName.setVisible(false);
                loadsuplierTable();

    }
    
//    when the cancel button is pressed this method executes
    
    @FXML
    private void cancelSupplierUpdate(ActionEvent event) throws SQLException {

               
        Dialogs.create().message("edit has been canceled").showInformation();

                clearSupplierScreen();
                manaageSupplierEditPane.setVisible(true);
                editSupplierAmountAndName.setVisible(false);
                loadsuplierTable();
    
    }

    
    
    /*  THIS METHOD HANDLES THE EDIT UPDATE OF  THE 
    **  SUPPLIER INFORMATION INTO THE DATABASE
    */
     
     @FXML
    private void updateSupplierAmountAndNameInfo(ActionEvent event) throws SQLException {
    
//        first set the table invisible first
//          then display the pane and load the supplier name combo box
        String staffName = this.manageSuppliertableView.getSelectionModel().getSelectedItem().col5.get();
            manageSuppliertableView.setDisable(true);
            editSupplierAmountAndName.setVisible(true);
            loadSupplierCodeCombox(editmanageSupplierAmountComboBox);
            editmanageSupplierAmountComboBox.getEditor().setText(staffName);
            editmanageSupplierAmountTextField.setText(this.manageSuppliertableView.getSelectionModel().getSelectedItem().col2.get());
        
    }
    


/**  this is the method that fills the supplier table 
 *   in the manage supplier section 
 */

    public void loadsuplierTable() throws SQLException{
    
        //sddf
        manageSuppliertableView.setDisable(false);
        manageSuppliertableView.setVisible(true);
        
        String query =" SELECT njieDB.SUPPLIER_PAY_ORDER.ID , "
                       + "       njieDB.SUPPLIER_PAY_ORDER.AMOUNT ,"
                       + "       njieDB.SUPPLIER_PAY_ORDER.ORDER_DATE , "
                  
                       + "       njieDB.SUPPLIER.NAME FROM njieDB.SUPPLIER_PAY_ORDER , njieDB.SUPPLIER "
                      
                       + " WHERE njieDB.SUPPLIER_PAY_ORDER.SUPPLIER_CODE =  njieDB.SUPPLIER.SUP_CODE ";
        
        System.out.println("info for table : " + query);
        manageSupplierIDColumn.setCellValueFactory(new PropertyValueFactory("col1"));
        manageSupplierAmountColumn.setCellValueFactory(new PropertyValueFactory("col2"));
        manageSupplierDateColumn.setCellValueFactory(new PropertyValueFactory("col3"));
        manageSupplierNameColumn.setCellValueFactory(new PropertyValueFactory("col4"));
        
        composeOverdraft = FXCollections.observableArrayList();
        this.manageSuppliertableView.setItems(composeOverdraft);
        
       
          db.setQuery(query);
             System.out.println("before loop");
          	for (int i = 0; i < db.getRowCount(); i++) {
            	TableClass trans = new TableClass();
            
            trans.col1.setValue(db.getValueAt(i, 0).toString());
            trans.col2.setValue(db.getValueAt(i, 1).toString());
            trans.col3.setValue(db.getValueAt(i, 2).toString());
            trans.col4.setValue(db.getValueAt(i, 3).toString());
         
            composeOverdraft.add(trans);
      	}
    
    }

 
    
    /**  This method evaluates the edit functio_naility when the 
     *   a record is chosen from the suplier table and the edit 
     *   button is clicked 
     */
    
    public void evaluateSupplierInfoUpdate() throws SQLException{
        String col1 = this.manageSuppliertableView.getSelectionModel().getSelectedItem().col1.get();
        String col2 = this.manageSuppliertableView.getSelectionModel().getSelectedItem().col2.get();
        String col3 = this.manageSuppliertableView.getSelectionModel().getSelectedItem().col3.get();

        choosenSupplierLabel.setText("Edit supplier Information");

        int id = Integer.parseInt(col1);
        int price = Integer.parseInt(this.editmanageSupplierAmountTextField.getText());

        //OBTAIN COMBO BOX VALUE 

        int supCode = Integer.parseInt(getKey(editmanageSupplierAmountComboBox.getEditor().getText()));


        Action confirmer = Dialogs.create().
        title("Delete confirmation").
        masthead("you want to edit Record on " + col2 ).
        message("Are you sure you want to continue?").showConfirm();

        if (confirmer == Dialog.Actions.YES){
            //update the day here 
            try {
                String q = "UPDATE njieDB.SUPPLIER_PAY_ORDER SET njieDB.SUPPLIER_PAY_ORDER.SUPPLIER_CODE = " + supCode 
                        + " , njieDB.SUPPLIER_PAY_ORDER.AMOUNT = " + price
                        + " WHERE njieDB.SUPPLIER_PAY_ORDER.ID = " + id ;
                System.out.println(q);
                db.setQuery(q);
                
                Dialogs.create().message("THE UPDATE WAS SUCCESFULLY DONE ").showInformation();
                loadsuplierTable();
            } catch (Exception e) {
                System.out.println("There was an error performing the update. Please verify all the fields are filled appropriately");
                Dialogs.create().message("There was an error performing the update. Please verify all the fields are filled appropriately").showInformation();
                e.printStackTrace();
            }
            
        }else{
           Dialogs.create().message("EDIT CANCELED ").showInformation();
       }

    }   

    
    
    int overdIdForEdit;
    String oldStaffNameInEditOverdraft;
    @FXML
    private void editOverdraftInfoFromTable(ActionEvent event) throws SQLException {
   
        //choose from the table and this method takes u to the small pane 
        //then sets the table dispalbled 
  
         String col1 = this.viewOverdraft_table.getSelectionModel().getSelectedItem().col1.get();
         String col2 = this.viewOverdraft_table.getSelectionModel().getSelectedItem().col2.get();
         String col3 = this.viewOverdraft_table.getSelectionModel().getSelectedItem().col3.get();
         String col4 = this.viewOverdraft_table.getSelectionModel().getSelectedItem().col4.get();
         String col6 = this.viewOverdraft_table.getSelectionModel().getSelectedItem().col6.get();
         if(col6 == "unpaid"){
            Dialogs.create().message("you want to edit the Overdraft Record of  "+ col1).showInformation();
   //         clearAllOverdraft();
            viewOverdraft_table.setDisable(true);
            overDraftEditPane.setVisible(true);
            loadOverdraftNameComboBox();
            overdraftAmountTextField.setText(col2);
            overdraftdeductionStartDatePicker.getEditor().setText(col4);
            
            this.overdIdForEdit = Integer.parseInt(this.viewOverdraft_table.getSelectionModel().getSelectedItem().col7.get());
            this.oldStaffNameInEditOverdraft = col1;
            
         }else{
             Dialogs.create().message("Cannot edit an overdraft that has already been paid.").showError();
         }
    }

    @FXML
    private void deleteOcerdraftInfoFromTable(ActionEvent event) throws ParseException {
    
       
         //this is when the record is selected on the table to be 
        //deleted using the delete button below the table 
         String col1 = this.viewOverdraft_table.getSelectionModel().getSelectedItem().col1.get();
         String col7 = this.viewOverdraft_table.getSelectionModel().getSelectedItem().col7.get();

        // int amount = Integer.parseInt( overdraftAmountTextField.getText());
         
          Action confirmer = Dialogs.create().
                    title("Delete confirmation").
                    masthead(" you want to delete " +col1 + "'s Record ").
                    message("Are you sure you want to continue?").showConfirm();
               
                 
          String query ="DELETE FROM  njieDB.OVERDRAFT WHERE  njieDB.OVERDRAFT.OV_ID  ="+Integer.parseInt(col7);
          
           if (confirmer == Dialog.Actions.YES){
          
                 try {
                     
                     db.setQuery(query);
                     
                  Dialogs.create().message("RECORD DELETED SUCCESSFULLY ").showInformation();
                  displayOverdraftTable();
                 } catch (SQLException  ex) {
                     
                     System.out.println("COULD NOT EVALUATE CONFIRMATION----->");      
                      ex.printStackTrace();
               Dialogs.create().message("PLEASE SELECT A RECORD FORM THE TABLE ").showInformation();
   
                 
                 }
           }else{
                              
                    Dialogs.create().message("DELETE CANCELED ").showWarning();
                }
    }

    
    @FXML
    private void saveOverdraftUpdateInfo(ActionEvent event) throws SQLException, ParseException {
        //this method updates the data into the database from 
        //the small editable pane 
        String col7 = this.viewOverdraft_table.getSelectionModel().getSelectedItem().col7.get();
        String col2 = this.viewOverdraft_table.getSelectionModel().getSelectedItem().col2.get();
        String col6 = this.viewOverdraft_table.getSelectionModel().getSelectedItem().col6.get();
        String col1 = this.viewOverdraft_table.getSelectionModel().getSelectedItem().col1.get();

        int staffId  = Integer.parseInt(getKey(overdraftStaffNameComboBox.getEditor().getText()));
        int amount =  Integer.parseInt(overdraftAmountTextField.getText());
        int overDraftDedPerMonth = Integer.parseInt(overdraftMonthlydeductionTextField.getText());
        String deduction_start_date = sdf.format(sdf.parse(overdraftdeductionStartDatePicker.getValue().toString()));
        
        Action confirmer = Dialogs.create().
        title("Delete confirmation").
        masthead("you want to edit Record of "+col1).
        message("Are you sure you want to continue?").showConfirm();
        
        /**** i have to do some checks like
         * 1) Monthly deduction is a multiple of overdraft amount
         * 2) generate end date of the overdraft
         * 3) Check if we are still dealing with same staff, if so, then no problem else
         *    check if there is any valid overdraft issued for the new staff that has been issued.
         */
        int ratio = amount % overDraftDedPerMonth; /**** 1. **/
        int numOfMonths = amount / overDraftDedPerMonth;
        String xpDate = usefullstuff.addSubtractMonthFromDate(deduction_start_date, numOfMonths);
        System.out.println("Deduction ends on the " + xpDate);
        
        String q = "UPDATE njieDB.OVERDRAFT SET njieDB.OVERDRAFT.STAFF_ID = " + staffId
                        + " , njieDB.OVERDRAFT.DEDUCTION_START_DATE = '" + deduction_start_date + "'"
                        + " , njieDB.OVERDRAFT.AMOUNT = " + amount
                        + " , njieDB.OVERDRAFT.DEDUCTION_PER_MONTH = " + overDraftDedPerMonth
                        + " , njieDB.OVERDRAFT.DEDUCT_UNTIL = '" + xpDate + "'"
                        + " WHERE njieDB.OVERDRAFT.OV_ID=" + this.overdIdForEdit;
                
        
        if (confirmer == Dialog.Actions.YES ){
               //update the day here 
            System.out.println("old = " + oldStaffNameInEditOverdraft);
            System.out.println("new = " + overdraftStaffNameComboBox.getEditor().getText());
            if(this.oldStaffNameInEditOverdraft.equalsIgnoreCase(overdraftStaffNameComboBox.getEditor().getText())){/***********Its the same staff  **/
                if (ratio == 0) {
                    System.out.println(q);

                    try {
                        db.setQuery(q);                    
                        Dialogs.create().message("THE UPDATE WAS SUCCESFULLY DONE ").showInformation();
                        displayOverdraftTable();
                    } catch (IllegalStateException | SQLException e) {
                        System.out.println("Error Updating overdraft. ");                    
                        e.printStackTrace();
                    }
                }else{
                    Dialogs.create().message("The monthly deduction will cause an inbalance of " + ratio + " at the end of deduction").showError();
                }
            }else{
                System.out.println("the user wants to change the staff that the overdraft was issued to");
                System.out.println("check if that user has any valid overdraft");
                db.setQuery("select count(njieDB.OVERDRAFT.ov_id) "
                        + " from  njieDB.OVERDRAFT "
                        + " where njieDB.OVERDRAFT.staff_id = " + staffId 
                        + " and   njieDB.OVERDRAFT.EXP_STATUS = " + true);
                if(db.getRowCount() >= 1){//different staff has an active overdraft
                    Dialogs.create().message("The new staff, " + overdraftStaffNameComboBox.getEditor().getText() 
                     + "you inputed has a pending overdraft in the system").showInformation();
                }else{//the different staff has no active overdraft
                    
                    if (ratio == 0) {
                    System.out.println(q);

                    try {
                        db.setQuery(q);                    
                        Dialogs.create().message("THE UPDATE WAS SUCCESFULLY DONE ").showInformation();
                        displayOverdraftTable();
                    } catch (IllegalStateException | SQLException e) {
                        System.out.println("Error Updating overdraft. ");                    
                        e.printStackTrace();
                    }
                    }else{
                        Dialogs.create().message("The monthly deduction will cause an inbalance of " + ratio + " at the end of deduction").showError();
                    }   
                }
                
            }
        }
        clearAllOverdraft();
        overDraftEditPane.setVisible(false);
        manager_viewOverdraft_Pane.setVisible(true);
        viewOverdraft_table.setDisable(false);
        overdraftAmountTextField.setText("");
        overdraftMonthlydeductionTextField.setText(null);
                 
                
    }

    @FXML
    private void cancelOverdraftUpdate(ActionEvent event) throws SQLException {
             Dialogs.create().message("update was canceled").showInformation();
             clearAllOverdraft();
             manager_viewOverdraft_Pane.setVisible(true); 
             displayOverdraftTable();
    }

    
   // this method loads the overdraft combo box
    
    public void loadOverdraftNameComboBox() throws SQLException{
    
    
     overdraftStaffNameComboBox.getItems().clear();//remove all elements from the list
        String getData = "SELECT njieDB.STAFF.STAFF_ID, njieDB.STAFF.NAME FROM njieDB.STAFF";
        db.setQuery(getData);
        clearArrayList();
        for(int i = 0 ; i < db.getRowCount() ; i++){
            String code = db.getValueAt(i, 0).toString();
            String name = db.getValueAt(i, 1).toString();
            //fill it to the combo box
            overdraftStaffNameComboBox.getItems().add(new ComboItem(name, code));
            System.out.printf(" %s\n", name);
            //fill the array list
            
            ComboValue.add(i, name);
            ComboKey.add(i, code);
        }
        //this.supplierCode_issuePayCmbox.getSelectionModel().selectFirst();
        System.out.println("Finished loading the combo box for the supplier's code");
        AutoCompleteComboBoxListener autocom = new AutoCompleteComboBoxListener<>(overdraftStaffNameComboBox);
    
    
    }

    

    //////
    @FXML/****The user has selected a row and wants to edit. **/
    private void displayMiniPaneToEditCustDebtIssued(MouseEvent event) throws SQLException {
         String custName = this.editCustumerDebtsTable.getSelectionModel().getSelectedItem().col2.get();
         String _amt = this.editCustumerDebtsTable.getSelectionModel().getSelectedItem().col3.get();
            editCustumerDebtsTable.setDisable(true);
            editCustDebtMiniPane.setVisible(true);
            
            this.loadCustumerId(custNameForDebtEditingCmBx);
            custNameForDebtEditingCmBx.getEditor().setText(custName);
            debtAmtForCustDebtEdit.setText(_amt);
        
    }

    @FXML/****user wants to save the change made. ***/
    private void saveTodatabaseChangesOnEditedCustumerDebt(MouseEvent event) throws SQLException {
        String debtId = this.editCustumerDebtsTable.getSelectionModel().getSelectedItem().col1.get();
        String editCustId = getKey(custNameForDebtEditingCmBx.getEditor().getText());
        int editAmt = Integer.parseInt(debtAmtForCustDebtEdit.getText());
        String update = "update njieDB.DEBTS set njieDB.DEBTS.AMOUNT = " + editAmt 
                + " , njieDB.DEBTS.CUST_ID = '" + editCustId + "' "
                + " where njieDB.DEBTS.DEBT_ID = " + debtId ;
        Action confirmer = Dialogs.create().
        title("Delete confirmation").
        masthead("Custumer Name = " + custNameForDebtEditingCmBx.getEditor().getText() 
        + "amount = " + editAmt).
        message("Are you sure you want to continue?").showConfirm();
        if (confirmer == Dialog.Actions.YES ){
            System.out.println("update Query is \n " + update);
            try {
                db.setQuery(update);
                Dialogs.create().message("operation completed succesfully").showInformation();
                populateCustDebtForEdit();
            } catch (SQLException | IllegalStateException sQLException) {
                Dialogs.create().message("There was an error during the update.").showError();
            }
        }
        
        editCustumerDebtsTable.setDisable(false);
        editCustDebtMiniPane.setVisible(false);
    }

    @FXML/****user decides to cancel the changes effected on a debt. **/
    private void cancelProcessOfCustumerDebtEditing(MouseEvent event) {
        editCustumerDebtsTable.setDisable(false);
        editCustDebtMiniPane.setVisible(false);
    }

    @FXML
    private void loadSuspensionAnchor(Event event) {
    }


    

  }

     
     
    

    
