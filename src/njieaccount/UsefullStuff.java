/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package njieaccount;

import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import static java.lang.System.exit;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.controlsfx.dialog.Dialogs;

/**
 *
 * @author KLEXY
 */
public class UsefullStuff {

    DatabaseHelper db, db2;
    SimpleDateFormat dateOnly, dateForSalary;
    Date dNow;

    public UsefullStuff() throws Exception {

        try {
            db = new DatabaseHelper();
            db2 = new DatabaseHelper();
        } catch (SQLException sQLException) {
            Dialogs.create().title("Connection error").message("Error Connection with databse connection").showError();
        }
        dateOnly = new SimpleDateFormat("yyyy-MM-dd");
        dateForSalary = new SimpleDateFormat("yyyy-MM" + "-05");

    }

    public void restrictToNumbers(javafx.scene.input.KeyEvent event) {
        char c = event.getCharacter().charAt(0);
        System.out.println("character c: " + c);
        if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_TAB
                || c == KeyEvent.VK_ENTER || c == KeyEvent.VK_BACK_SPACE))) {
            Dialogs.create().title("Wrong Input").
                    message("You must enter only numbers here.").
                    showInformation();
            event.consume();
        }
    }
    
    public boolean enterWasPressed(javafx.scene.input.KeyEvent event){
        char c = event.getCharacter().charAt(0);
        if ( c == KeyEvent.VK_ENTER ) {
            System.out.println("character ENTER");
            event.consume();
            return true;
        }else{
            return false;
        }
    }

    public boolean escape(javafx.scene.input.KeyEvent event) {
        if (event.getCharacter().charAt(0) == KeyEvent.VK_ESCAPE) {
            event.consume();
            return true;
        }
        return false;
    }

    /**
     * *********@see selecting from two tables with joins. Natural joins**********
     */
    public ArrayList selectWithJoin(String data, String table, String jCol1, String jCol2) {
        ArrayList re = new ArrayList();
        try {

            String sql = "select " + data + " from " + table + " where  " + jCol1 + " = " + jCol2;
            sql = " SELECT njieDB.STAFF.NAME FROM njieDB.STAFF, njieDB.OVERDRAFT WHERE njieDB.STAFF.STAFF_ID = njieDB.OVERDRAFT.STAFF_ID ";
            //  DatabaseHelper db = new DatabaseHelper();
            System.out.println(sql);
            re = db.ExecuteQuery(sql);

        } catch (SQLException sh) {
            System.out.println(sh.getMessage());
            System.out.println("Stack trace");

        }
        return re;

    }

    public boolean isEmpty(String c_id, String c_name, String c_prefgood) {

        if ("".equals(c_id) || "".equals(c_name) || "".equals(c_prefgood)) {
            Dialogs.create().title("error").message("Some fields are empty.Pleas ensure they are filled").showError();
            return true;
        } else {
            return false;
        }

    }

    public static int differenceInMonths(Date d1, Date d2) {
        Calendar c1 = Calendar.getInstance();
        c1.setTime(d1);
        Calendar c2 = Calendar.getInstance();
        c2.setTime(d2);
        int diff = 0;
        if (c2.after(c1)) {
            while (c2.after(c1)) {
                c1.add(Calendar.MONTH, 1);
                if (c2.after(c1)) {
                    diff++;
                }
            }
        } else if (c2.before(c1)) {
            while (c2.before(c1)) {
                c1.add(Calendar.MONTH, -1);
                if (c1.before(c2)) {
                    diff--;
                }
            }
        }
        
        System.out.println("/\\/\\/\\/\\ dateDiff = " + diff);
        return diff;
    }

    public void checkforExpiryOverdraft() throws ParseException {
        String Q_search, deduct_until = "";
        int ov_id = 0, OV_staff_id = 0;

        dNow = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
        String td = ft.format(dNow);
        Q_search = "SELECT njieDB.OVERDRAFT.OV_ID, njieDB.OVERDRAFT.DEDUCT_UNTIL, "
                + " njieDB.OVERDRAFT.STAFF_ID FROM njieDB.OVERDRAFT WHERE "
                + " njieDB.OVERDRAFT.EXP_STATUS = " + false;
        System.out.println("overdraft exp qyuery " + Q_search);
        try {
            db.setQuery(Q_search);
            if (db.getRowCount() > 0) { //check if there is any overdraft in the system.
                for (int i = 0; i < db.getRowCount(); i++) {
                    ov_id = Integer.parseInt(db.getValueAt(i, 0).toString());
                    deduct_until = db.getValueAt(i, 1).toString();

                    OV_staff_id = Integer.parseInt(db.getValueAt(i, 2).toString());

                    /*composing the dates to be subtracted*/
                    Date d1 = ft.parse(td);
                    Date d2 = ft.parse(deduct_until);
                    int n = differenceInMonths(d1, d2);

                    if (n < 0) {//the expiry time has reached
                        String update_OV;
                        update_OV = "UPDATE njieDB.OVERDRAFT SET njieDB.OVERDRAFT.EXP_STATUS = " + true
                                + " WHERE njieDB.OVERDRAFT.OV_ID = " + ov_id;
                        db.setQuery(update_OV);
                        Dialogs.create().title("Expire overdraft").message("Overdraft deduction "
                                + " for user with ID " + OV_staff_id + " has come to its expiration ").showInformation();
                    } else {
                        System.out.println("no overdraft is expiring today");
                    }

                }
            } else {
                System.out.println("no overdraft in system");
            }
        } catch (SQLException | IllegalStateException sQLException) {
            sQLException.printStackTrace();
        }
    }

    public String addSubtractMonthFromDate(String givenDate, int shift) throws ParseException {
        //Date dNow = new Date();
        SimpleDateFormat dateOnly = new SimpleDateFormat("yyyy-MM-dd");
        Date dateToManipulate = dateOnly.parse(givenDate);
        Calendar c = Calendar.getInstance();
        c.setTime(dateToManipulate);
        c.add(Calendar.MONTH, shift);

        return dateOnly.format(c.getTime());
    }

    /**
     * **************
     *
     * @param table the table that we are retrieving the key from
     * @param valueColumn the column that has the value
     * @param keyColumn the column that has the key, which is the return value
     * @param value this is what is gotten from the combobox.
     * @return the key from the combobox
     */
    public String getKeyFromComboBoxValue(String table, String valueColumn, String keyColumn, String value) throws SQLException {
        String key = null;
        String query = "select njieDB." + table + "." + keyColumn + " from njieDB." + table + " where "
                + " njieDB." + table + "." + valueColumn + " = '" + value + "'";
        System.out.println("doing sum ------------------------- for custumer ---------- \n " + query);

        db.setQuery(query);
        if (db.getRowCount() > 0) {
            return db.getValueAt(0, 0).toString();
        } else {
            return key;
        }
    }

    public long getTotalAfterFilter(String query, int totCol) throws SQLException {
        try {
            long total = 0;
            db.setQuery(query);
            System.out.println("doing sum ------------------------- for custumer ---------- \n " + query);
            System.out.println("the index is + " + totCol);
            for (int i = 0; i < db.getRowCount(); i++) {
                total += Integer.parseInt(db.getValueAt(i, totCol).toString());
            }
            System.out.println("custumer totals = " + total);
            return total;
        } catch (SQLException | IllegalStateException | NumberFormatException sQLException) {
            return 0;
        }
    }

    public long getTotalFromDisplayedTable(TableView tabId, TableColumn colToSum) {
        long total = 0;
        for (int i = 0; i < tabId.getItems().size(); i++) {
            total += Integer.parseInt(colToSum.getCellData(i).toString());
        }
        System.out.println("total from table is " + total);
        return total;
    }

    //this method is to get the total salary left that a worker can take for that month
    //it takes only the staff id and uses it to get all the information it needs form the database
    public long getStaffSalaryForCurrentMonth(int staffId) throws ParseException, SQLException {
        int overdraftDeductionPerMonth = 0;
        long possiblePayableAmount = (long) 0;
        
        dNow = new Date();

        String qGetTotaUnpaidSalary_and_totalSalary;
        /*
         this method had an error. the error reads
         Caused by: java.sql.SQLException: ResultSet not open. 
         Operation 'absolute()' not permitted. Verify that autocommit is off.
         */
        SimpleDateFormat endSalaryDate = new SimpleDateFormat("yyyy-MM-20");
        /*get the total salary paid since employment*/
        qGetTotaUnpaidSalary_and_totalSalary = "SELECT SUM(njieDB.MONTHLY_SALARY_PAYMENT.SALARY), "
                + " SUM(njieDB.MONTHLY_SALARY_PAYMENT.TOTAL_PAID) FROM "
                + " njieDB.MONTHLY_SALARY_PAYMENT WHERE "
                + " njieDB.MONTHLY_SALARY_PAYMENT.STAFF_ID = " + staffId + " AND "
                + " njieDB.MONTHLY_SALARY_PAYMENT.SALARY != njieDB.MONTHLY_SALARY_PAYMENT.TOTAL_PAID "
                + " AND njieDB.MONTHLY_SALARY_PAYMENT.DAT <= '" + endSalaryDate.format(dNow) + "'";
        System.out.println("Total SalaryPaid : " + qGetTotaUnpaidSalary_and_totalSalary);
        long totalSalaryPaid = (long) 0, totalSalary = (long) 0;
        db.setQuery(qGetTotaUnpaidSalary_and_totalSalary);
        if (db.getValueAt(0, 0) != null) {
            totalSalary = Long.parseLong(db.getValueAt(0, 0).toString());
            totalSalaryPaid = Long.parseLong(db.getValueAt(0, 1).toString());
        }
        System.out.println("total paid salary = " + totalSalaryPaid + "\n");
        System.out.println("total salary = " + totalSalary);

        /*now i get the possible payable amount*/
        possiblePayableAmount = totalSalary - totalSalaryPaid;

        /*check if it is he can collect something under normal circumstances, ie with no overdraft */
        if (possiblePayableAmount == 0) {//he cannot take anything since he has 0 balance
            Dialogs.create().title("No Funds").message("Sorry, he has exhausted his pay ").showError();
            return 0;
        }
       //he has some money to collect.
        //But we need to know if he has an overdraft so that we deduct the appropriate amount
        // and see if he/she still has any valid payment.
        String Q_OD;

        /**
         * **
         * since the overdraft date is specified by the admin, also need to
         * check if the date of today is greater that or equal to the start date
         * of the overdraft along side that status being false.
         */
        System.out.println("today's date ois " + dateOnly);
        System.out.println("possible payable amount : " + possiblePayableAmount);
        Q_OD = "SELECT njieDB.OVERDRAFT.DEDUCTION_PER_MONTH FROM njieDB.OVERDRAFT "
                + " WHERE njieDB.OVERDRAFT.STAFF_ID = " + staffId
                + " AND njieDB.OVERDRAFT.EXP_STATUS = " + false + " AND "
                + " njieDB.OVERDRAFT.DEDUCTION_START_DATE <= '" + dateOnly.format(dNow) + "'";
        System.out.println("getting overdraft query ===+==========+= \n" + Q_OD);
        db.setQuery(Q_OD);
        if (db.getRowCount() > 0) {// has overdraft
            overdraftDeductionPerMonth = Integer.parseInt(db.getValueAt(0, 0).toString());
            System.out.println("overdraft = " + overdraftDeductionPerMonth);
        }
            //we now have an overdraft of either 0 or a certain value.
        //take off the overdraft deduction from the posible income to be collected.
        possiblePayableAmount -= overdraftDeductionPerMonth;

        //check if the staff with the deduction due to overdraft can still take home something.
        if (possiblePayableAmount <= 0) {//no possible payment
            Dialogs.create().title("Funds out").message("You cannot collect any more this month because"
                    + " of your overdraft has been deducted leaving you with a net sum of 0 frs"
                    + " for this month").showError();
            return 0;
        }
        
        /*we need also to check if there are other suspensions that will hinder this one.*/
        possiblePayableAmount -= getSuspensionForStaff(staffId);
        if(possiblePayableAmount <= 0){
            System.out.println("amount too small for payment caused by syspension");
                Dialogs.create().title("Suspension Cuts")
                        .masthead("Salary suspension cuts")
                        .message("This staff has some suspension and upon the "
                                + "reduction of the suspensions, the staff is left with a "
                                + "net of <= 0 possible amount to be collected for this month.")
                        .showInformation();
                return 0;
        }
        return possiblePayableAmount;
    }

    /**
     * *************************
     * This method will be used to extract the staff's id from the descriptionof
     * either the income or expense transaction. eg the desc might be like "
     * |223| suspension here" so my interest is to extract the 223 which is the
     * staff id. --ALGO-- This is done by firstly tokenising the string into a
     * character array. This char array will iterated from cell 2 checking if
     * the character at that cell is not equal to '|' then get the respective
     * decimal and appends to the original one iteratively up to the point where
     * the char '|' is reached.
     *
     * @param desc description from which you want to extract the id eg " |22|
     * suspension of salary " i will be extracting 22 from that string
     * @param start the cell it should start looking for the id
     * @param del what should cause it to stop the search.
     * @return integer value whose first digit started from start and ended at
     * the first encounter of del.
     */
    public int getIntValFromString(String desc, int start, char del) {
        char[] tmp = new char[100];
        int staffId = 0;
        int mult = 1;

        tmp = desc.toCharArray();

        for (int i = start; i < desc.length(); i++) {
            if (tmp[i] != del) {
                staffId += Integer.parseInt(String.valueOf(tmp[i])) * mult;
            }
            mult *= 10;
        }
        return staffId;
    }

    public void makingMonthlySalaryEntries() throws SQLException {
        dNow = new Date();

        SimpleDateFormat s1 = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat s2 = new SimpleDateFormat("yyyyMM05");
        SimpleDateFormat s3 = new SimpleDateFormat("yyyyMM10");

        System.out.println("the various date formats are \n");
        System.out.println(s1.format(dNow) + " == "
                + s2.format(dNow) + " == "
                + s3.format(dNow) + " == "
                + dateForSalary.format(dNow));
        if (Long.parseLong(s1.format(dNow)) < Long.parseLong(s2.format(dNow))) {
            System.out.println("still giving the admin time to make the neccesary changes");
            return;
        }
        if (Long.parseLong(s1.format(dNow)) > Long.parseLong(s3.format(dNow))) {
            System.out.println("Evaluation period ended");
            return;
        }

        String qGetStaffIdAndSalary, qTestPresenceOfStaffEntryForCurrentMonth, qInsertSatffEntryForCurrentMonth;
        qGetStaffIdAndSalary = "select njieDB.STAFF.STAFF_ID, njieDB.STAFF.SALARY from njieDB.STAFF ";

        //get the staff id and salary
        System.out.println("getting staff id and salary \n " + qGetStaffIdAndSalary);
        db.setQuery(qGetStaffIdAndSalary);
        if (db.getRowCount() == 0) {
            return;
        }
        //we are sure that there is at least one staff in the system
        for (int i = 0; i < db.getRowCount(); i++) {
            try {
                qTestPresenceOfStaffEntryForCurrentMonth = ""
                        + " select njieDB.MONTHLY_SALARY_PAYMENT.DAT FROM njieDB.MONTHLY_SALARY_PAYMENT "
                        + " WHERE njieDB.MONTHLY_SALARY_PAYMENT.STAFF_ID = " + Integer.parseInt(db.getValueAt(i, 0).toString())
                        + " and njieDB.MONTHLY_SALARY_PAYMENT.DAT = '" + dateForSalary.format(dNow) + "' ";
                System.out.println("cheking if staff has entry \n " + qTestPresenceOfStaffEntryForCurrentMonth);
                db2.setQuery(qTestPresenceOfStaffEntryForCurrentMonth);
                if (db2.getRowCount() == 0) { //the staff does not have an entry in the monthly salary payments table. 
                    //so insert a new entry for that staff.
                    qInsertSatffEntryForCurrentMonth = "insert into njieDB.MONTHLY_SALARY_PAYMENT(njieDB.MONTHLY_SALARY_PAYMENT.DAT, "
                            + " njieDB.MONTHLY_SALARY_PAYMENT.SALARY, njieDB.MONTHLY_SALARY_PAYMENT.STAFF_ID) VALUES ("
                            + " '" + dateForSalary.format(dNow) + "', " + Integer.parseInt(db.getValueAt(i, 1).toString()) + ", "
                            + " " + Integer.parseInt(db.getValueAt(i, 0).toString()) + ")";
                    System.out.println("since staff does not have entry, we insert a new entry for this month \n"
                            + qInsertSatffEntryForCurrentMonth);
                    db2.setQuery(qInsertSatffEntryForCurrentMonth);
                }
            } catch (Exception ex) {
                Dialogs.create().title("error").masthead("Evaluation of staff's monthly salary faild").message("The error is \n " + ex.getMessage()).showError();
            }
            System.out.println(" loop " + i + " ends -------------------------------------------\n");
        }

        System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&& "
                + "end of monthly salary payment testing "
                + "&&&&&&&&&&&&&&&&&&&&&&&&&&\n\n\n");
        try {
            clearMonthlyTableAfterFiveMonths();
        } catch (SQLException | ParseException ex) {
            ex.printStackTrace();
        }

    }

    public void clearMonthlyTableAfterFiveMonths() throws ParseException, SQLException {
        System.out.println("*********************CLEANING CLEANING MONTHLY PAYMENT TABLE *************\n\n");
        dNow = new Date();
        String fiveMonths;
        fiveMonths = this.dateOnly.format(dNow);
        fiveMonths = this.addSubtractMonthFromDate(fiveMonths, -5);
        System.out.println("five months ago was " + fiveMonths);

        String qToDeleteOldData = "delete from njieDB.MONTHLY_SALARY_PAYMENT "
                + " WHERE njieDB.MONTHLY_SALARY_PAYMENT.DAT <= '" + fiveMonths + "' "
                + " AND njieDB.MONTHLY_SALARY_PAYMENT.SALARY = njieDB.MONTHLY_SALARY_PAYMENT.TOTAL_PAID";
        System.out.println("query to delete entries less than five months old \n" + qToDeleteOldData);
        db.setQuery(qToDeleteOldData);
        System.out.println("\n\n**********end of cleaning monthly payment table. *******8\n\n");
    }

    public long getSuspensionForStaff(int staff_id) {
        long salarySuepension = 0;

        String Q_getSuspension = "select sum(njiedb.salary_suspension.sus_amt) from njiedb.salary_suspension "
                + " where njiedb.salary_suspension.staff_id = " + staff_id;
        try {
            db.setQuery(Q_getSuspension);
            if(db.getValueAt(0, 0) != null && db.getRowCount() > 0 )
                salarySuepension = Long.parseLong(db.getValueAt(0, 0).toString());
            else
                return (long)0;
        } catch (Exception e) {
            //Dialogs.create().title("Error").message("error getting staffs suspensions").showError();
            return (long) 0;
        }

        return salarySuepension;
    }
    
    public static String getPcName(){
        FileReader in = null;
        String dataFromFile = null ;
        int ipVal;
        
        String ipFileLocation;
        ipFileLocation = "config.txt";
        System.out.println("ip location = " + ipFileLocation);
        
        try {
            System.out.println("opeinig the input file");
            try {
                in = new FileReader(ipFileLocation);
                System.out.println("input file opened succesfully");
            } catch (FileNotFoundException ex) {
                System.out.println("error opening file \n " + ex.getMessage());
                exit(1);
            }
            dataFromFile = String.valueOf((char)in.read());
            while((ipVal = in.read()) != -1){
                dataFromFile += (char)ipVal;
            }
            System.out.println("File value is \n" + dataFromFile);   
        } catch (IOException e) {
        }   
        return dataFromFile;
    }

}
