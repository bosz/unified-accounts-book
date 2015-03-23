/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Reports;

import Tables.TabClass;
import java.io.FileOutputStream;
import java.sql.SQLException;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import njieaccount.UsefullStuff;
import org.controlsfx.dialog.Dialogs;

/**
 *
 * @author root
 */
public class GenTransReports {
    //DatabaseHelper db;
    TabClass tab;
    UsefullStuff stuff;
     
    String gTab;
    String gTabClass;
    public long totalIncExpAfterFilter;
    /*****
     * @see  the transaction that is currently available, it can be either "INCOME" or "EXPENSES"
     * @
     */
    public String gType;
    private FileOutputStream fs;
    
    String Q_exp, Q_inc;
    String incTab, incTransTab;
    String expTab, expTransTab;
    
    public GenTransReports() throws SQLException, Exception{
        //db = new DatabaseHelper();
        tab = new TabClass();
        gTab = "njieDB.INCOME";
        gTabClass = " njieDB.INC_TRANS_CLASS ";
        gType = "INCOME";
        stuff = new UsefullStuff();
        
        incTab = "njieDB.INCOME";
        incTransTab = "njieDB.INC_TRANS_CLASS";
        expTab = "njieDB.EXPENSES";
        expTransTab = "njieDB.EXP_TRANS_CLASS";
 
        Q_inc = "SELECT " + incTransTab + ".NAME , " + incTab + ".MEMO , "
                       + " " + incTab + ".AMOUNT, " + incTab + ".DAT ," + incTab + ".TRANS_ID"
                       + " FROM " + incTransTab + " , " + incTab + " WHERE  "
                       + incTransTab + ".CODE = " + incTab + ".CODE";
        Q_exp = "SELECT " + expTransTab + ".NAME , " + expTab + ".MEMO , "
                       + expTab + ".AMOUNT, " + expTab + ".DAT ," + expTab + ".TRANS_ID"
                       + " FROM " + expTransTab + " , " + expTab + " WHERE  "
                       + expTransTab + ".CODE = " + expTab + ".CODE";
         
    }
    
    
    public boolean populateTableWithIncomeTransactions(TableView tableId, TableColumn name,
                                                    TableColumn desc, TableColumn amt, TableColumn time, TableColumn code) throws SQLException{
        
        String table = "njieDB.INCOME";
        String classTable = "njieDB.INC_TRANS_CLASS";
        
        this.gTab = table;
        this.gTabClass = classTable;
        this.gType = "INCOME";
        System.out.println( "((-- " + this.gTab + "--" + this.gTabClass + " --))" );
        
        String populatequery = "SELECT " + classTable + ".NAME, "
                + table + ".MEMO, " + table + ".AMOUNT, " + table + ".DAT, " + table + ".TRANS_ID "
                + " FROM " + table + ", " + classTable 
                + " WHERE " + table + ".CODE = " + classTable + ".CODE";
        
        System.out.println("---query INC is : " + populatequery);
         try {
            tab.makeTable(populatequery, name,  desc, amt, time, code, tableId);
            return true;
        } catch (SQLException sQLException) {
            Dialogs.create().message("there was an error populating the table").showError();
            return false;
        }
        
    }
    
    public boolean populateTableWithExpenseTransactions(TableView tableId, TableColumn name, 
                                                    TableColumn desc, TableColumn amt, TableColumn time, TableColumn code) throws SQLException {
        String table = "njieDB.EXPENSES";
        String classTable = "njieDB.EXP_TRANS_CLASS";
        
        this.gTab = table;
        this.gTabClass = classTable;
        this.gType = "EXPENSES";
        System.out.println( "((-- " + this.gTab + "--" + this.gTabClass + " --))" );
        
        String populatequery = "SELECT " + classTable + ".NAME, " 
                + table + ".MEMO, " + table + ".AMOUNT, " + table + ".DAT, " + table + ".TRANS_ID "
                + " FROM " + table + ", " + classTable 
                + " WHERE " + table + ".CODE = " + classTable + ".CODE";
        
        System.out.println("---query EXP is : " + populatequery);
        try {
            tab.makeTable(populatequery, name, desc, amt, time, code,  tableId);
            return true;
        } catch (SQLException sQLException) {
            Dialogs.create().message("there was an error populating the table").showError();
            return false;
        }
    }
    
    public String populateFilteTableWithTransactionsByDate(TableView tableId, TableColumn code,
                                                    TableColumn desc, TableColumn amt, TableColumn time, 
                                                    String startDate, String endDate) throws SQLException{
        
        String table = this.gTab;
        String classTable = this.gTabClass;
        
        String populatequery = "SELECT " + classTable + ".NAME, " 
                + table + ".MEMO, " + table + ".AMOUNT, " + table + ".DAT, " + table + ".TRANS_ID " 
                + " FROM " + table + ", " + classTable 
                + " WHERE " + table + ".CODE = " + classTable + ".CODE "
                + " AND (" + table + ".DAT BETWEEN '" + startDate + "' AND '" + endDate + "')" ;
        System.out.println("filterd query for trans is\n" + populatequery);
        try {
            tab.makeTable(populatequery, code, desc, amt, time, tableId);
            Dialogs.create().message("filter").showError();
        } catch (IllegalStateException sQLException) {
            Dialogs.create().message("there was an error populating the table").showError();
        }
        
        return this.gType;
    }
    
    
    /////////////////////  eugene' work /////////////////////////////
    
    
    
    public void filterGeneralTransactionOptionThree(int code, String date1, String date2, 
                                        TableView tabId, TableColumn c1, TableColumn c2, 
                                        TableColumn c3, TableColumn c4, TableColumn c5) throws SQLException{ 
   
  
        //HERE WE DO THE FILETER BASED ON ALL THE DATA THAT HAVE BEEN CHOOSEN 
        //INORDER TO MAKE A SEARCH
         String query;
        if("INCOME".equals(gType)){
            query = Q_inc
                    + "     AND  " + incTab + ".CODE = " + code + " AND " + incTab + ".DAT BETWEEN '" + date1 + "' AND '" + date2 + "' ";
        }else{
            query = Q_exp
                    + "     AND  " + expTab + ".CODE = " + code + " AND " + expTab + ".DAT BETWEEN '" + date1 + "' AND '" + date2 + "' "; 
        }
        
        //populate the table.
     
          
       tab.makeTable(query, c1, c2, c3, c4,c5, tabId);
       this.totalIncExpAfterFilter = stuff.getTotalAfterFilter(query, 2);
    } 
 
 
 public void filterGeneralTransactionOptionTwo(String val1, String val2, int filter, 
                                        TableView tabId, TableColumn c1, TableColumn c2, 
                                        TableColumn c3, TableColumn c4, TableColumn c5) throws SQLException{
 
     String query = "";
     
      switch(filter){
            case 11: //trans_name and start date 
                if("INCOME".equals(gType)){
                    query = Q_inc
                    + "     AND  " + incTab + ".CODE = " +Integer.parseInt(val1) + " AND " + incTab + ".DAT >  '" + val2 + "' ";
                }else{
                    query = Q_exp
                    + "     AND  " + expTab + ".CODE = " +Integer.parseInt(val1) + " AND " + expTab + ".DAT >  '" + val2 + "' ";
                }
                    
                break;
            case 12: //trans_name and end date 
                if("INCOME".equals(gType)){
                    query = Q_inc
                   + "     AND  " + incTab + ".CODE = " +Integer.parseInt(val1)  + " AND " + incTab + ".DAT <  '" + val2 + "' ";
                }else{
                    query = Q_exp
                   + "     AND  " + expTab + ".CODE = " +Integer.parseInt(val1)  + " AND " + expTab + ".DAT <  '" + val2 + "' ";
                }
                    break;
           
            case 13: //start date and end date
                if("INCOME".equals(gType)){
                    query = Q_inc
                           + "     AND " + incTab + ".DAT BETWEEN '" + val1 + "'  AND '"+ val2 +"' ";
                          
                }else{
                    query = Q_exp
                           + "     AND " + expTab + ".DAT BETWEEN '" + val1 + "'  AND '"+ val2 +"' ";
                   }
                 System.out.println("case 3 why");
                break;
             default:
             
                 break;
        }
        //populate the table.
        tab.makeTable(query, c1, c2, c3, c4, c5, tabId);
        this.totalIncExpAfterFilter = stuff.getTotalAfterFilter(query, 2);

 }
 
 
  public void filterGeneralTransactionOptionOne(String val1,int filter ,  TableView tabId, TableColumn c1, TableColumn c2, 
                                                TableColumn c3, TableColumn c4 ,TableColumn c5 ) throws SQLException{

      String query = ""; 
      
      
        switch(filter){
            case 1: //only the trans name has been choosen
                if("INCOME".equals(gType)){
                    query = Q_inc
                    + "     AND  " + incTab + ".CODE = " +Integer.parseInt(val1) + "";
                }else{
                    query = Q_exp
                    + "     AND  " + expTab + ".CODE = " +Integer.parseInt(val1) + "";
                }
                
        
                break;
            case 2: //only the start date has been selected 
                if("INCOME".equals(gType)){
                    query = Q_inc
                    + "     AND  " + incTab + ".DAT > '" +val1 + "'";
                }else{
                    query = Q_exp
                    + "     AND  " + expTab + ".DAT > '" +val1 + "'";
                }
                break;
            case 3: //only the end date has been choosen
                if("INCOME".equals(gType)){
                    query = Q_inc
                    + "     AND  " + incTab + ".DAT < '" +val1 + "'";
                }else{
                    query = Q_exp
                    + "     AND  " + expTab + ".DAT < '" +val1 + "'";
                }
                break;
                
            default:
             
                Dialogs.create().message("wrong filter choosen").showInformation();
               
                break;
        }
      
        //populate the table.
      
         tab.makeTable(query, c1, c2, c3, c4,c5, tabId); 
         this.totalIncExpAfterFilter = stuff.getTotalAfterFilter(query, 2);
      
  }
  
  
}
