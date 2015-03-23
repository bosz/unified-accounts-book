/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package njieaccount;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author root
 */
public class Checks {
    
    DatabaseHelper db;
    
    Checks() throws SQLException, Exception
    {
        db = new DatabaseHelper();
    }
    
    public void checkIncome() throws SQLException
    {
        String checkIncome = "SELECT * FROM njieDB.INCOME";
        db.setQuery(checkIncome);
        System.out.println("Query: " + checkIncome );
        
        for(int i = 0; i<db.getRowCount(); i++){
            System.out.print("(((((INCOME)))))->");
            for(int j = 0; j<db.getColumnCount(); j++){
                System.out.println("" + db.getValueAt(i, j) + "\t");
            }
        }
    }
    public void checkExpenses() throws SQLException
    {
        String checkExpense = "SELECT * FROM njieDB.EXPENSES";
        db.setQuery(checkExpense);
        System.out.println("Query: " + checkExpense );
        
        for(int i = 0; i<db.getRowCount(); i++){
            System.out.print("(((((EXPENSES)))))->");
            for(int j = 0; j<db.getColumnCount(); j++){
                System.out.println("" + db.getValueAt(i, j) + "\t");
            }
        }
    }
    
    public void checkSupplier() throws SQLException, SQLException, SQLException, SQLException
    {
        String checkSupplier = "SELECT * FROM njieDB.SUPPLIER";
        db.setQuery(checkSupplier);
        System.out.println("Query: " + checkSupplier );
        
        for(int i = 0; i<db.getRowCount(); i++){
            System.out.println("(((((SUPPLIER)))))->");
            for(int j = 0; j<db.getColumnCount(); j++){
                System.out.println("" + db.getValueAt(i, j) + "\t");
            }
        }
    }
    
    
    
    public void checkPayOrder() throws SQLException
    {
        String checkPayOrdere = "SELECT * FROM njieDB.SUPPLIER_PAY_ORDER";
        db.setQuery(checkPayOrdere);
        System.out.println("Query: " + checkPayOrdere );
        
        for(int i = 0; i<db.getRowCount(); i++){
            System.out.println("(((((ISSUE PAY ORDER)))))->");
            for(int j = 0; j<db.getColumnCount(); j++){
                System.out.println("" + db.getValueAt(i, j) + "\t");
            }
        }
    }
    
    public void checkPayMade() throws SQLException
    {
        String checkPayMade = "SELECT * FROM njieDB.SUPPLIER_PAY";
        db.setQuery(checkPayMade);
        System.out.println("Query: " + checkPayMade );
        
        for(int i = 0; i<db.getRowCount(); i++){
            System.out.println("(((((paying )))))->");
            for(int j = 0; j<db.getColumnCount(); j++){
                System.out.println("" + db.getValueAt(i, j) + "\t");
            }
        }        
        
    }
    
    public void checkIncTransClass() throws SQLException
    {
        String checkIncTransClass = "SELECT * FROM njieDB.INC_TRANS_CLASS";
        db.setQuery(checkIncTransClass);
        System.out.println("Query: " + checkIncTransClass );
        
        for(int i = 0; i<db.getRowCount(); i++){
            System.out.println("(((((INCOME TRANSACTION CLASS)))))->");
            for(int j = 0; j<db.getColumnCount(); j++){
                System.out.println("" + db.getValueAt(i, j) + "\t");
            }
        }        
        
    }
    
    public void checkExpTransClass() throws SQLException
    {
        String checkExpTransClass = "SELECT * FROM njieDB.INC_TRANS_CLASS";
        db.setQuery(checkExpTransClass);
        System.out.println("Query: " + checkExpTransClass );
        
        for(int i = 0; i<db.getRowCount(); i++){
            System.out.println("(((((EXPENSE TRANSACTION CLASS)))))->");
            for(int j = 0; j<db.getColumnCount(); j++){
                System.out.println("" + db.getValueAt(i, j) + "\t");
            }
        }        
        
    }
    
     public void checkAccount() throws SQLException
    {
        String checkExpTransClass = "SELECT * FROM njieDB.ACCOUNT";
        db.setQuery(checkExpTransClass);
        System.out.println("Query: " + checkExpTransClass );
        
        for(int i = 0; i<db.getRowCount(); i++){
            System.out.println("(((((ACCOUNT)))))->");
            for(int j = 0; j<db.getColumnCount(); j++){
                System.out.println("" + db.getValueAt(i, j) + "\t");
            }
        }        
        
    }
     
    
    public long sumExporInc(String table, String column) throws SQLException
    {
        long sum=0;
        String query =" SELECT " + column + " from " + table + "";
        db.setQuery(query);
        if(db.getRowCount() > 0){
            for(int i = 0 ; i < db.getRowCount() ; i++){
                    sum += Long.parseLong(db.getValueAt(i, 0).toString());
            }
            return sum;
        }
        else{
            return 0;
        }
    }
    
    
    
    //calculate balance
    long totExp, totInc, incExpBal;
    public void expIncBalance() throws SQLException{
        this.totExp = sumExporInc("njieDB.EXPENSES", "njieDB.EXPENSES.AMOUNT");
        this.totInc = sumExporInc("njieDB.INCOME", "njieDB.INCOME.AMOUNT");
        this.incExpBal = totInc - totExp;
    }
    
    public long getBalafterInc(long lattestInc){
        this.totInc += lattestInc;
        this.incExpBal = totInc - totExp;
        return incExpBal;
    }
    
    public long getBalafterExp(long lattestExp){
        this.totExp += lattestExp;
        this.incExpBal = totInc - totExp;
        return incExpBal;
    }
    
    public long getBal(){
        return this.incExpBal;
    }
    
     public void checkstaff() throws SQLException
    {
        String checkPayOrdere = "SELECT * FROM njieDB.STAFF";
        db.setQuery(checkPayOrdere);
        System.out.println("Query: " + checkPayOrdere );
        
        for(int i = 0; i<db.getRowCount(); i++){
            System.out.println("(((((  STAFF )))))->");
            for(int j = 0; j<db.getColumnCount(); j++){
                System.out.println("" + db.getValueAt(i, j) + "\t");
            }
        }
    }
     
     
      public void checkCust() throws SQLException
    {
        String checkPayOrdere = "SELECT * FROM njieDB.CUSTUMER";
        db.setQuery(checkPayOrdere);
        System.out.println("Query: " + checkPayOrdere );
        System.out.println("\n(((((  custumers )))))->");
        
         for(int i = 0; i<db.getRowCount(); i++){
           
            for(int k = 0 ; k < db.getColumnCount(); k++){
                    System.out.print("\nColumn " + k+1 + " is " + db.getColumnName(k) + "\t");
                    
                }
             System.out.println("");
            for(int j = 0; j<db.getColumnCount(); j++){
                System.out.print("" + db.getValueAt(i, j) + "\t");
            }
        }
    }
      
     public void checkDebtsIssued() throws SQLException
    {
        String checkPayOrdere = "SELECT * FROM njieDB.DEBTS";
        db.setQuery(checkPayOrdere);
        System.out.println("Query: " + checkPayOrdere );
        
        for(int i = 0; i<db.getRowCount(); i++){
            System.out.println("(((((  DEBTS )))))->");
            for(int j = 0; j<db.getColumnCount(); j++){
                System.out.print("" + db.getValueAt(i, j) + "\t");
            }
        }
    }
     
     
     
     
     /*-----------------getting information on todays transaction --------------------*/
     
     long todayTotExp, todayTotInc, todayBal;
    public void daysIncExpBal() throws SQLException, ParseException{
        this.todayTotExp = TodaySumExporInc("njieDB.EXPENSES", "njieDB.EXPENSES.AMOUNT");
        this.todayTotInc = TodaySumExporInc("njieDB.INCOME", "njieDB.INCOME.AMOUNT");
        this.todayBal = this.todayTotInc - this.todayTotExp;
    }
    
    public long TodaySumExporInc(String table, String column) throws SQLException, ParseException
    {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date dnow = new Date();
        String todayDate = format.format(dnow);
       
        Date dateToCompare = format.parse(todayDate);
        
        
        long sum=0;
        String newTest = format.format(dnow);
        System.out.println("testing date = " + newTest);
        
        
        NjieAccount hom = new NjieAccount();
        String query =" SELECT " + column  + ", " + table + ".DAT" + " from " + table;
                //+ " where " + table + ".TRANS_ID >= '" + dateToCompare + "'";
        System.out.println("income and expenditure for the day " + query);
        db.setQuery(query);
        if(db.getRowCount() > 0){
            for(int i = 0 ; i < db.getRowCount() ; i++){
                /*now filter only the transaction of today*/
                System.out.println("DatabaseDate = " + db.getValueAt(i, 1) + "--- today = " + todayDate + "....amt = " + db.getValueAt(i, 0));
                if(todayDate.equals(db.getValueAt(i, 1).toString())){
                    System.out.println("inside if");
                    sum += Long.parseLong(db.getValueAt(i, 0).toString());
                }
            }
            System.out.println("sum = " + sum);
            return sum;
        }
        else{
            return 0;
        }
    }
        
    
     public void checkOverdraft() throws SQLException
    {
        String checkExpTransClass = "SELECT * FROM njieDB.OVERDRAFT";
        db.setQuery(checkExpTransClass);
        System.out.println("Query: " + checkExpTransClass );
        
        System.out.println("(((((oVERDRAFT)))))->");
        for(int i = 0; i<db.getRowCount(); i++){
            for(int j = 0; j<db.getColumnCount(); j++){
                System.out.println("" + db.getValueAt(i, j) + "\t");
            }
        }        
        
    }
     
     
    public void checkPayroll() throws SQLException
    {
        String checkPayOrdere = "SELECT * FROM njieDB.PAYROLL";
        db.setQuery(checkPayOrdere);
        System.out.println("Query: " + checkPayOrdere );
        
        for(int i = 0; i<db.getRowCount(); i++){
            System.out.println("((((( PAYROLL )))))->");
            for(int j = 0; j<db.getColumnCount(); j++){
                System.out.println("" + db.getValueAt(i, j) + "\t");
            }
        }
    }
    
    public void checkXtraCust() throws SQLException
    {
        String checkPayOrdere = "SELECT * FROM njieDB.CUST_XTRA";
        db.setQuery(checkPayOrdere);
        System.out.println("Query: " + checkPayOrdere );
        
        System.out.println("((((( XTRA CUST )))))->");
        for(int i = 0; i<db.getRowCount(); i++){
            for(int j = 0; j<db.getColumnCount(); j++){
                System.out.println("" + db.getValueAt(i, j) + "\t");
            }
        }
    }
     
}
