/*
 *
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
 * @author root
 */
public class SupplierReports {
    TabClass tab;
    String supTab, supPayTab, supPayOrder; 
    String Q_searchSupplierPayOrder, Q_searchSupplierPayments;
    String supCodeQ, orderDateQ, payDateQ, supCodeInPayOrder;
    String grouping;
    
    public SupplierReports() throws SQLException, Exception{
        tab = new TabClass();
        supTab = "njieDB.SUPPLIER";
        supPayTab = "njieDB.SUPPLIER_PAY";
        supPayOrder = "njieDB.SUPPLIER_PAY_ORDER";
        grouping = " group by " + supPayOrder + ".SUPPLIER_CODE";
        
        instantiateQuery();
        Q_searchSupplierPayOrder += grouping;
        /*This query string is to get the payment made to suppliers*/
        Q_searchSupplierPayments = "select " + supTab + ".NAME, " 
                + supPayTab + ".AMOUNT_PAID, " + supPayTab + ".DATE "
                + " FROM " + supTab + ", " + supPayTab 
                + " WHERE "
                + supTab + ".SUP_CODE = " + supPayTab + ".SUP_ID ";
        
        
        
        
        supCodeQ = " njieDB.SUPPLIER.SUP_CODE";
        orderDateQ = "njieDB.SUPPLIER_PAY_ORDER.ORDER_DATE";
        payDateQ = "njieDB.SUPPLIER_PAY.DATE";
        supCodeInPayOrder = "njieDB.SUPPLIER_PAY_ORDER.SUPPLIER_CODE";
        
    }
    
    private void instantiateQuery(){
        /*This is the query string to get the pay orders issued for suppliers */
        Q_searchSupplierPayOrder = "select "
                +  supPayOrder + ".SUPPLIER_CODE, sum("
                + supPayOrder + ".AMOUNT) " +  " FROM " 
                + supPayOrder + " ";
    }
    
    public boolean displaySupplierOrderRreports(TableView tabId, TableColumn c1, TableColumn c2) throws SQLException{
       instantiateQuery();
        System.out.println("QueryQuery = \n" + Q_searchSupplierPayOrder + grouping);
        try {
            tab.makeSuppDebtTable(Q_searchSupplierPayOrder + grouping , c1, c2, tabId);
            System.out.println("returned true");
            return true;
        } catch (SQLException sQLException) {
            sQLException.printStackTrace();
            return false;
        }
    }
    
    public void displaySupplierPay(TableView tabId, TableColumn c1, TableColumn c2, TableColumn c3) throws SQLException{
        tab.makeTable(this.Q_searchSupplierPayments, c1, c2, c3, tabId);
    }
    //NOW, WE START DOING THE FILTERING.
    /*************8
     * 
     * @param filter : this indicates what measure u will like to filter the outputs.
     *                 1 for id and start date
     *                 2 for id and end date
     *                 3 for start date and end date
     * @param val1      this holds the first parameter for the search.
     *                  Should be either id, start or end date
     * @param val2      this hold the second parameter for the search
     *                  should be either start or end date and NOT ID. ID IS ONLY GOES FOR VAL1
     * 
     * @param tabId
     * @param c1
     * @param c2
     * @throws SQLException 
     */
    public void supplierReportsFiltered(String val1, String val2, int filter,
                                        TableView tabId, TableColumn c1, TableColumn c2
                                        ,TableColumn cPay1, TableColumn cPay2, TableColumn cPay3, TableView payTabId) throws SQLException{ 
        instantiateQuery();
        String filteredQuery = "", filterPayQuery = "";
        switch(filter){
            case 1: //id and start
                filteredQuery = this.Q_searchSupplierPayOrder + "where " + this.supCodeInPayOrder + " = " + Integer.parseInt(val1)
                        + " AND " + this.orderDateQ + " >= '" + val2 + "' " + grouping;
                filterPayQuery = this.Q_searchSupplierPayments + " AND " + this.supCodeQ + " = " + Integer.parseInt(val1)
                        + " AND " + this.orderDateQ + " >= '" + val2 + "' ";
                break;
            case 2: //id and end
                filteredQuery = this.Q_searchSupplierPayOrder + " where " + this.supCodeInPayOrder + " = " + Integer.parseInt(val1)
                        + " AND " + this.orderDateQ + " <= '" + val2 + "' " + grouping;
                filterPayQuery = this.Q_searchSupplierPayments + " AND " + this.supCodeQ + " = " + Integer.parseInt(val1)
                        + " AND " + this.orderDateQ + " <= '" + val2 + "' ";
                break;
            case 3: //start and end
                System.out.println("case 3 why");
                filteredQuery = this.Q_searchSupplierPayOrder + " where " + this.orderDateQ 
                        + " BETWEEN '" + val1 + "' AND '" + val2 + "' " + grouping;
                filterPayQuery = this.Q_searchSupplierPayments + " AND " + this.orderDateQ 
                        + " BETWEEN '" + val1 + "' AND '" + val2 + "' ";
                break;
            default:
                break;
        }
        //populate the table.
        tab.makeSuppDebtTable(filteredQuery, c1, c2, tabId);
        tab.makeTable(filterPayQuery , cPay1, cPay2, cPay3, payTabId);
        
    }
    
    /****
     * 
     * @param filter    This filter identifies that value to be filtered by.
     *                  1 ->= id only
     *                  2 ->= start date only
     *                  3 ->= end date only
     * @param val1      This is the filtering value.
     *                  Its either id, start date or end date
     * @param tabId
     * @param c1
     * @param c2
     * @throws SQLException 
     */
    public void supplierReportsFiltered(String val1, int filter,
                                        TableView tabId, TableColumn c1, TableColumn c2
                                        ,TableColumn cPay1, TableColumn cPay2, TableColumn cPay3, TableView payTabId) throws SQLException{ 
        instantiateQuery();
        String filteredQuery = "", filterPayQuery = "";
        switch(filter){
            case 1: //id 
                filteredQuery = this.Q_searchSupplierPayOrder + " where " + this.supCodeInPayOrder + " = " + Integer.parseInt(val1) + grouping;
                filterPayQuery = this.Q_searchSupplierPayments + " AND " + this.supCodeQ + " = " + Integer.parseInt(val1);
                break;
            case 2: //start
                filteredQuery = this.Q_searchSupplierPayOrder + " where " + this.orderDateQ + " >= '" + val1 + "' " + grouping;
                filterPayQuery = this.Q_searchSupplierPayments + " AND " + this.orderDateQ + " >= '" + val1 + "' ";
                break;
            case 3: //end
                filteredQuery = this.Q_searchSupplierPayOrder + " where " + this.orderDateQ + " <= '" + val1 + "' " + grouping;
                filterPayQuery = this.Q_searchSupplierPayments + " AND " + this.orderDateQ + " <= '" + val1 + "' ";
                break;
            default:
                Dialogs.create().message("wrong filter").showInformation();
                break;
        }
        //populate the table.
        tab.makeSuppDebtTable(filteredQuery, c1, c2, tabId);
        tab.makeTable(filterPayQuery , cPay1, cPay2, cPay3, payTabId);
        
    }
    
    
    public void supplierReportsFiltered(int code, String startDate, String endDate,  
                                        TableView tabId, TableColumn c1, TableColumn c2
                                        ,TableColumn cPay1, TableColumn cPay2, TableColumn cPay3, TableView payTabId) throws SQLException{ 
        String filteredQuery = "", filterPayQuery="";
        instantiateQuery();
        filteredQuery = this.Q_searchSupplierPayOrder + " where " + this.supCodeInPayOrder + " = " + code + " AND " 
                + this.orderDateQ + " BETWEEN '" + startDate + "' AND '" + endDate + "' " + grouping;
        filterPayQuery = this.Q_searchSupplierPayments + " AND " + this.supCodeQ + " = " + code + " AND " 
                + this.orderDateQ + " BETWEEN '" + startDate + "' AND '" + endDate + "' ";
        //populate the table.
        tab.makeSuppDebtTable(filteredQuery, c1, c2, tabId);
        tab.makeTable(filterPayQuery , cPay1, cPay2, cPay3, payTabId);
        
    }
    

    
}
/*            
SUPPLIER ->= table
    SUP_CODE, NAME
SUPPLIER_PAY ->= table
    AMOUNT, DATE
SUPPLIER_PAY_ORDER ->= table
    AMOUNT, STATUS

where
sT.SUP_CODE = oT.SUPPLIER_CODE AND

pT.SUPPLY_ORDER_CODE = oT.ID

*/
