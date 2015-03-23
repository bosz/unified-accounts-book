/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Reports;

import Tables.TabClass;
import java.sql.SQLException;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import njieaccount.AutoCompleteComboBoxListener;
import njieaccount.ComboItem;
import njieaccount.DatabaseHelper;
import org.controlsfx.dialog.Dialogs;

/**
 *
 * @author r
 */
public class StaffOverdraft {
        TabClass tab;
        DatabaseHelper db;
        String Q_overdraft;
   public StaffOverdraft() throws SQLException, Exception{
       tab = new TabClass();
        db = new DatabaseHelper();
        Q_overdraft = "SELECT "  + sTab + ".NAME, "
                +   oTab + ".AMOUNT, " + oTab + ".DEDUCTION_PER_MONTH, " 
                +   oTab + ".DATE, " + oTab + ".DEDUCTION_START_DATE, " + oTab + ".DEDUCT_UNTIL, "
                +   oTab + ".PAY_STATUS, " + oTab + ".EXP_STATUS "
                + " FROM " + oTab + ", " + sTab
                + " WHERE "
                +   sTab + ".STAFF_ID = " + oTab + ".STAFF_ID ";
   }
    
    
    String sTab = "njieDB.STAFF";
    String oTab = "njieDB.OVERDRAFT";
    String pTab = "njieDB.PAYROLL";
    public void displayStaffOverdraftReport(TableView tabId, TableColumn c1, TableColumn c2, 
                                            TableColumn c3, TableColumn c4, TableColumn c5,
                                            TableColumn c6, TableColumn c7, TableColumn c8) throws SQLException{
        
        tab.makeOverdraftReportsTable(this.Q_overdraft, c1, c2, c3, c4, c5, c6, c7, c8, tabId);
        
    }
    
    /*------------------------------------------------------------------------------------------
    ------------------------------FILTER STARTS---------------------------------------------------
    */
    //  3   selected
    public void displayFilteredStaffOverdraftReport(int staffCode, String val1, String val2, TableView tabId, TableColumn c1, TableColumn c2, 
                                            TableColumn c3, TableColumn c4, TableColumn c5,
                                            TableColumn c6, TableColumn c7, TableColumn c8) throws SQLException{
        String Q_FilterOverdraft = this.Q_overdraft + " AND " + sTab + ".STAFF_ID = " + staffCode + " AND " 
                                   + oTab + ".DATE  BETWEEN '" + val1 + "' AND '" + val2 + "'";
        tab.makeOverdraftReportsTable(Q_FilterOverdraft, c1, c2, c3, c4, c5, c6, c7, c8, tabId);
    }
    
    //  2   selected
    public void displayFilteredStaffOverdraftReport(String val1, String val2, int filter, TableView tabId, TableColumn c1, TableColumn c2, 
                                            TableColumn c3, TableColumn c4, TableColumn c5,
                                            TableColumn c6, TableColumn c7, TableColumn c8) throws SQLException{
        String Q_FilterOverdraft = "";
        switch(filter){
            case 1:  // id and start
                Q_FilterOverdraft = this.Q_overdraft + " AND "  + sTab + ".STAFF_ID = " + Integer.parseInt(val1) 
                                    + " AND " + oTab + ".DATE  >= '" + val2 + "'";
                System.out.println(Q_FilterOverdraft);
                break;
            case 2:  // id and end
                Q_FilterOverdraft = this.Q_overdraft + " AND "   + sTab + ".STAFF_ID = " + Integer.parseInt(val1) 
                                    + " AND " + oTab + ".DATE  <= '" + val2 + "'";
                break;
            case 3:  // start and end
                Q_FilterOverdraft = this.Q_overdraft + " AND "  + oTab + ".DATE  BETWEEN '" + val1 + "' AND '" + val2 + "'";
                break;
            default:
               Dialogs.create().title("wrong option").showInformation();
                
        }
        tab.makeOverdraftReportsTable(Q_FilterOverdraft, c1, c2, c3, c4, c5, c6, c7, c8, tabId);
    }
    
    //  1   selected
    public void displayFilteredStaffOverdraftReport(String val1, int filter, TableView tabId, TableColumn c1, TableColumn c2, 
                                            TableColumn c3, TableColumn c4, TableColumn c5,
                                            TableColumn c6, TableColumn c7, TableColumn c8) throws SQLException{
        String Q_FilterOverdraft="";
        switch(filter){
            case 1:  // id
                Q_FilterOverdraft = this.Q_overdraft + " AND " + sTab + ".STAFF_ID = " + Integer.parseInt(val1);
                break;
            case 2:  // start
                Q_FilterOverdraft = this.Q_overdraft + " AND " + oTab + ".DATE  >= '" + val1 + "'";
                break;
            case 3:  // end
                Q_FilterOverdraft = this.Q_overdraft + " AND " + oTab + ".DATE  <= '" + val1 + "'";
                break;
            default:
               Dialogs.create().title("wrong option").showInformation();
        }
        tab.makeOverdraftReportsTable(Q_FilterOverdraft, c1, c2, c3, c4, c5, c6, c7, c8,tabId);
    }
    /*------------------------------------------------------------------------------------------
    ------------------------------FILTER ENDS---------------------------------------------------
    */
    
    
    
}
