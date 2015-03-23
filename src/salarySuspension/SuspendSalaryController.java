/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package salarySuspension;

import Tables.Printer;
import Tables.TabClass;
import framework.ControlledScreen;
import framework.ScreensController;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import njieaccount.DatabaseHelper;
import njieaccount.TableClass;
import njieaccount.UsefullStuff;
import org.controlsfx.dialog.Dialogs;

/**
 * FXML Controller class
 *
 * @author root
 */
public class SuspendSalaryController implements Initializable, ControlledScreen {
    @FXML
    private AnchorPane suspensionAnchorPane;
    @FXML
    private Pane defaultSuspensionPane;
    @FXML
    private Pane MakeSuspensionPane;
    @FXML
    private TextField staffIdToSuspensInpt;
    @FXML
    private TextField amtToSuspendInpt;
    @FXML
    private Pane alterSuspensionsPane;
    @FXML
    private TableView<TableClass> viewSuspensionsTable;
    @FXML
    private TableColumn<TableClass, String> viewSusTab_susIdCol;
    @FXML
    private TableColumn<TableClass, String> viewSusTab_staffNameCol;
    @FXML
    private TableColumn<TableClass, String> viewSusTab_AmountCol;
    @FXML
    private TableColumn<TableClass, String> viewSusTab_susDateCol;
    @FXML
    private TableColumn<TableClass, String> viewSusTab_susStatusCol;
    @FXML
    private TableColumn<TableClass, String> viewSusTab_ReasonCol;

    
    UsefullStuff usefulStuf;
    DatabaseHelper db;
    SimpleDateFormat dateOnly, tim;
    Date dNow;
    TabClass tab;
    Printer print;
    
    long totalSus;
    
    @FXML
    private TextField suspensionReason;
    
    @FXML
    private Button editSalarySuspensionBtn;
    @FXML
    private Button deleteSalarySuspensionBtn;
    @FXML
    private Button printSuspensionButton;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            db = new DatabaseHelper();
        } catch (Exception ex) {
            Logger.getLogger(SuspendSalaryController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            usefulStuf = new UsefullStuff();
        } catch (Exception ex) {
            Logger.getLogger(SuspendSalaryController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            tab = new TabClass();
        } catch (Exception ex) {
            Logger.getLogger(SuspendSalaryController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        tim = new SimpleDateFormat("hh:mm:ss");
        dateOnly = new SimpleDateFormat ("yyyy-MM-dd");
        print = new Printer();
        
        totalSus = 0;
    }    

    public void clearPanes(){
        MakeSuspensionPane.setVisible(false);
        alterSuspensionsPane.setVisible(false);
        defaultSuspensionPane.setVisible(false);
    }
    
    @FXML
    private void displaySuspendSalaryPane(MouseEvent event) {
        clearPanes();
        MakeSuspensionPane.setVisible(true);
    }

    @FXML
    private void displayViewEditDeleteSuspensionPane(MouseEvent event) throws SQLException, ParseException {
        String qViewSuspensions;
        
        clearPanes();
        alterSuspensionsPane.setVisible(true);
        
        qViewSuspensions = "select njieDB.SALARY_SUSPENSION.ID, njieDB.STAFF.NAME, "
                + "njieDB.SALARY_SUSPENSION.SUS_AMT, njieDB.SALARY_SUSPENSION.REASON, njieDB.SALARY_SUSPENSION.DAT "
                + " FROM njieDB.SALARY_SUSPENSION, njieDB.STAFF "
                + " WHERE njieDB.SALARY_SUSPENSION.STAFF_ID = njieDB.STAFF.STAFF_ID";
        
        tab.makeSuspensionTable(qViewSuspensions, viewSusTab_susIdCol, viewSusTab_staffNameCol, 
                viewSusTab_AmountCol, viewSusTab_ReasonCol, viewSusTab_susDateCol, viewSusTab_susStatusCol, viewSuspensionsTable);
        
    }

    @FXML
    private void suspendSalaryAddToDatabase(MouseEvent event) throws ParseException, SQLException {
        //variables to use
        String Qstaff_Name, qAddSuspensionEntry;    //query for salary and staffName
        long possiblePayableSalary; //possible payable amount
        
        //get the data from the form
        int staffIdToSuspendSalary = Integer.parseInt(staffIdToSuspensInpt.getText());
        int amtToSuspend = Integer.parseInt(amtToSuspendInpt.getText());
        String reason = suspensionReason.getText();
        //i will get the staff name first.
        Qstaff_Name = "SELECT njieDB.STAFF.NAME"
                + " FROM njieDB.STAFF WHERE njieDB.STAFF.STAFF_ID = " + staffIdToSuspendSalary + "";
        long monthlySalary = (long)0;
        String staffName = "";
        try {
            db.setQuery(Qstaff_Name);
            staffName = db.getValueAt(0, 0).toString();
        } catch (SQLException sQLException) {
            Dialogs.create().title("Error")
                    .message("Error fetching staff information on payroll\n\n" + sQLException.getMessage())
                    .showError();
            return ;
        }
        
        //call the method to calculate the staffs max payable salary for this month and return it.
        possiblePayableSalary = usefulStuf.getStaffSalaryForCurrentMonth(staffIdToSuspendSalary);
        if(possiblePayableSalary != 0){ //correct:: the staff still has some money to be collected. 
                                        //Bt the qnt goes, is that amt up to the amount to be suspended?
            //check if the possible payable amount is up to the amount to be suspended.
            
            if(possiblePayableSalary >= (long)amtToSuspend){//all is ok.  now go to the deduction
                dNow = new Date();
               
               
                //add new entry in the suspension table tihe the inputed information
                qAddSuspensionEntry = "INSERT INTO njieDB.SALARY_SUSPENSION("
                        + " njieDB.SALARY_SUSPENSION.STAFF_ID, njieDB.SALARY_SUSPENSION.SUS_AMT, "
                        + "njieDB.SALARY_SUSPENSION.REASON, njieDB.SALARY_SUSPENSION.DAT) VALUES ("
                        + staffIdToSuspendSalary + ", " + amtToSuspend + ", '" + reason +"', '" + dateOnly.format(dNow) + "')";
               
                /*The idea is money is being transfered fromt he staff's payroll to back to the company's income accounts*/
                try {
                    System.out.println("commiting changes");
                   
                    System.out.println(qAddSuspensionEntry + "\n");
                    db.setQuery(qAddSuspensionEntry);
                   
                    
                    staffIdToSuspensInpt.setText(null);
                    amtToSuspendInpt.setText(null);
                    suspensionReason.setText(null);
                    System.out.println("end of commit.");
                    Dialogs.create().title("Success").message("Suspension issued succesfully").showInformation();
                    
                } catch (Exception e) {
                    Dialogs.create().title("write error").message("Sorry, there was an error suspending staff salary \n\n" + e.getMessage()).showError();
                }
                
                
            }else{ //the amount to be suspended is more than the staff's possible amount to be collected for that month
                Dialogs.create().title("Error")
                    .masthead("Staff Name " + staffName + "\nPossible Payable Amount = " + possiblePayableSalary + "\nSuspension Amount = " + amtToSuspend)
                    .message("Seemingly, this can't work. "
                            + "The amount to be suspended is more that the amount "
                            + "the staff can collect for this month. Just try reducing "
                            + "the amount to less than or equal to the staff's possible payable amount")
                    .showError();
            }
            
        }
    }

    @FXML
    private void resetSuspensionInputs(MouseEvent event) {
        staffIdToSuspensInpt.setText(null);
        amtToSuspendInpt.setText(null);
    }

    @Override
    public void setScreenParent(ScreensController pane) {
        //nothing goes here yet
    }

    @FXML
    private void suspendSalaryOnPressingEnter(KeyEvent event) throws ParseException, SQLException {
        if(usefulStuf.enterWasPressed(event)){
            suspendSalaryAddToDatabase(null);
        }
    }

    @FXML
    private void editSalarySuspensionDetails(ContextMenuEvent event) {
    }

    @FXML
    private void deleteSalarySuspensionDetails(ContextMenuEvent event) {
    }

    @FXML
    private void printSalarySuspension(MouseEvent event) {
        print.salarySuspension(viewSuspensionsTable, viewSusTab_staffNameCol, viewSusTab_AmountCol, viewSusTab_ReasonCol, viewSusTab_susDateCol, viewSusTab_susStatusCol, this.totalSus);
    }
    
}
