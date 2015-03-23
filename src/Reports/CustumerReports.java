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
import njieaccount.UsefullStuff;

/**
 *
 * @author root
 */
public class CustumerReports {
    
    TabClass tab;
    String Q_custumerDebt, Q_custumerPay;
    String grouping;
    DatabaseHelper db;
    UsefullStuff stuff;
    
    public long totalDebt, totalPay;
    public CustumerReports() throws SQLException, Exception{
        tab = new TabClass();
        db = new DatabaseHelper();
        stuff = new UsefullStuff();
        grouping = " group by " + dTab + ".CUST_ID";
        
        instantiate();
        Q_custumerDebt += grouping;
         
        
        Q_custumerPay = "select " + cTab + ".NAME, " 
                + dpTab + ".AMOUNT_PAID, " + dpTab + ".DATE "
                + " FROM " + cTab + ", " + dpTab 
                + " WHERE "
                + cTab + ".CUST_ID = " + dpTab + ".CUST_ID "; 
        System.out.println("-----------------------------------------------");
        System.out.println(" debt " + Q_custumerDebt);
        System.out.println(" debt " + Q_custumerPay);
        System.out.println("-----------------------------------------------");
     }
    
    private void instantiate(){
        Q_custumerDebt = "select " + dTab + ".CUST_ID, sum("
                +   dTab + ".AMOUNT) FROM " 
                +   dTab + " ";
    }
    
    String dTab = "njieDB.DEBTS";
    String cTab = "njieDB.CUSTUMER";
    String dpTab = "njieDB.DEBT_PAYMENT";
    long totalCustdebt, totalCustPayment;
    
    public void displayCustumerReports(TableView tabId, TableColumn c1, TableColumn c2) throws SQLException{
        instantiate();
        System.out.println("query is \n " + this.Q_custumerDebt + grouping);
        tab.makeCustDebtTable(this.Q_custumerDebt + grouping, c1, c2, tabId);
        this.totalCustdebt = stuff.getTotalAfterFilter(this.Q_custumerDebt, 1);
    }
    
     public void displayCustumerPayReport(TableView tabId, TableColumn c1, TableColumn c2, TableColumn c3) throws SQLException{
         System.out.println("query is \n" + this.Q_custumerPay);
        tab.makeTable(this.Q_custumerPay, c1, c2, c3, tabId);
         this.totalCustPayment = stuff.getTotalAfterFilter(this.Q_custumerPay, 1);
     }
    
    
    
    //three selected
    public void displayFilteredCustReport(String code, String startDate, String endDat,
                                          TableView debTab, TableColumn debt_c1, TableColumn debt_c2
                                         ,TableView payTab, TableColumn pay_c1, TableColumn pay_c2, TableColumn pay_c3) throws SQLException{
        instantiate();
        String Q_debt, Q_pay;
        startDate += " 00:00:00.0";
        endDat += " 00:00:00.0";
        Q_debt = this.Q_custumerDebt + " where " +  dTab + ".CUST_ID = '" + code 
                                 + "' AND " + dTab + ".DATE_ISSUED BETWEEN '"
                                 + startDate + "' AND '" + endDat + "'" + grouping;
        Q_pay = this.Q_custumerPay + " AND " +  cTab + ".CUST_ID = '" + code 
                                 + "' AND " + dpTab + ".DATE BETWEEN '"
                                 + startDate + "' AND '" + endDat + "'";
        
        tab.makeCustDebtTable(Q_debt, debt_c1, debt_c2, debTab);
        tab.makeTable(Q_pay, pay_c1, pay_c2, pay_c3, payTab);
        
        //update the total after the filtering.
        this.totalCustPayment = stuff.getTotalAfterFilter(Q_pay, 1);
        this.totalCustdebt = stuff.getTotalAfterFilter(Q_debt, 1);
        
    }
    
    //two selected
    public void displayFilteredCustReport(String val1, String val2, int filter,
                                          TableView debTab, TableColumn debt_c1, TableColumn debt_c2
                                         ,TableView payTab, TableColumn pay_c1, TableColumn pay_c2, TableColumn pay_c3) throws SQLException{
        instantiate();
        String Q_debt="", Q_pay="";
        String tim = " 00:00:00.0";
        switch(filter){
            case 1: //id and start
                Q_debt = this.Q_custumerDebt + " where " +  dTab + ".CUST_ID = '" + val1 
                                 + "' AND " + dTab + ".DATE_ISSUED >= '" + val2 + tim +  "'" + grouping;
                Q_pay = this.Q_custumerPay + " AND " +  cTab + ".CUST_ID = '" + val1 
                                 + "' AND " + dpTab + ".DATE >= '" + val2 +  tim + "'"; 
                break;
            case 2: //id and end
                Q_debt = this.Q_custumerDebt + " where " +  dTab + ".CUST_ID = '" + val1 
                                 + "' AND " + dTab + ".DATE_ISSUED <= '" + val2 + tim + "'" + grouping;
                Q_pay = this.Q_custumerPay + " AND " +  cTab + ".CUST_ID = '" + val1
                                 + "' AND " + dpTab + ".DATE <= '" + val2 + tim + "'";
                break;
            case 3: //start and end
                Q_debt = this.Q_custumerDebt + " where " + dTab + ".DATE_ISSUED BETWEEN '"
                                         + val1 + tim + "' AND '" + val2 + tim + "'" + grouping;                
                Q_pay = this.Q_custumerPay + " AND " + dpTab + ".DATE BETWEEN '"
                                           + val1 + tim + "' AND '" + val2 + tim + "'";
                break;
            default:
        }
        tab.makeCustDebtTable(Q_debt, debt_c1, debt_c2, debTab);
        tab.makeTable(Q_pay, pay_c1, pay_c2, pay_c3, payTab);
        
        //getting the total in place
        this.totalCustPayment = stuff.getTotalAfterFilter(Q_pay, 1);
        this.totalCustdebt = stuff.getTotalAfterFilter(Q_debt, 1);
    }
    
    //only one selected
    public void displayFilteredCustReport(String val1, int filter,
                                          TableView debTab, TableColumn debt_c1, TableColumn debt_c2
                                         ,TableView payTab, TableColumn pay_c1, TableColumn pay_c2, TableColumn pay_c3) throws SQLException{
        instantiate();
        String Q_debt="", Q_pay="";
        String date = val1 + " 00:00:00.0";
        switch(filter){
            case 1: //id 
                Q_debt = this.Q_custumerDebt + " where "+  dTab + ".CUST_ID = '" + val1 + "'" + grouping;
                Q_pay = this.Q_custumerPay + " AND "+  cTab + ".CUST_ID = '" + val1 + "'";
                break;
            case 2: //start
                Q_debt = this.Q_custumerDebt + " where " + dTab + ".DATE_ISSUED >= '" + date + "'" + grouping;
                Q_pay = this.Q_custumerPay + " AND " + dpTab + ".DATE >= '" + date + "'";
                break;
            case 3: //end
                Q_debt = this.Q_custumerDebt + " where " + dTab + ".DATE_ISSUED <= '" + date + "'" + grouping;
                Q_pay = this.Q_custumerPay + " AND " + dpTab + ".DATE <= '" + date + "'";
                break;
            default:
                
        }
        tab.makeCustDebtTable(Q_debt, debt_c1, debt_c2, debTab);
        tab.makeTable(Q_pay, pay_c1, pay_c2, pay_c3, payTab);
        
        //getting the total in place
        this.totalCustPayment = stuff.getTotalAfterFilter(Q_pay, 1);
        this.totalCustdebt = stuff.getTotalAfterFilter(Q_debt, 1);
    }
    
}
