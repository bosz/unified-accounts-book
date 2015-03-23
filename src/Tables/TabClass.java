/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Tables;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import njieaccount.DatabaseHelper;
import njieaccount.UsefullStuff;
/**
 *
 * @author root
 */
public class TabClass {
    DatabaseHelper db;
    DatabaseHelper dbrep;
    SimpleDateFormat dateOnly;
    Date dNow;
    
    public TabClass() throws SQLException, Exception{
        db = new DatabaseHelper();  
        dbrep = new DatabaseHelper();
        dateOnly = new SimpleDateFormat ("yyyy-MM-dd");
    }
    
    
    ObservableList<SimpleString> composeData;
    public void makeTable(String query, TableColumn C1, TableView tabId) throws SQLException{
        
        C1.setCellValueFactory(new PropertyValueFactory<>("col1"));
            
        composeData = FXCollections.observableArrayList();
        tabId.setItems(composeData);

        System.out.println("Query For Table");
        System.out.println("Query is \n" + query);
        db.setQuery(query);

         
        for (int i = 0; i < db.getRowCount(); i++) {
            SimpleString trans = new SimpleString();

            trans.col1.setValue(db.getValueAt(i, 0).toString());

            composeData.add(trans);
        }
        
    }
    
    public void makeTable(String query, TableColumn C1, TableColumn C2, TableView tabId) throws SQLException{
        
        C1.setCellValueFactory(new PropertyValueFactory<>("col1"));
        C2.setCellValueFactory(new PropertyValueFactory<>("col2"));
            
        composeData = FXCollections.observableArrayList();
        tabId.setItems(composeData);

        System.out.println("Query For Table");
        System.out.println("Query is \n" + query);
        db.setQuery(query);

         
        for (int i = 0; i < db.getRowCount(); i++) {
            SimpleString trans = new SimpleString();

            trans.col1.setValue(db.getValueAt(i, 0).toString());
            trans.col2.setValue(db.getValueAt(i, 1).toString());

            composeData.add(trans);
        }
        
    }
    
    public void makeTable(String query, TableColumn C1, TableColumn C2, TableColumn C3,  TableView tabId) throws SQLException{
        
        C1.setCellValueFactory(new PropertyValueFactory<>("col1"));
        C2.setCellValueFactory(new PropertyValueFactory<>("col2"));
        C3.setCellValueFactory(new PropertyValueFactory<>("col3"));
            
        composeData = FXCollections.observableArrayList();
        tabId.setItems(composeData);

        System.out.println("Query For Table");
        System.out.println("Query is \n" + query);
        db.setQuery(query);

         
        for (int i = 0; i < db.getRowCount(); i++) {
            SimpleString trans = new SimpleString();

            trans.col1.setValue(db.getValueAt(i, 0).toString());
            trans.col2.setValue(db.getValueAt(i, 1).toString());
            trans.col3.setValue(db.getValueAt(i, 2).toString());

            composeData.add(trans);
        }
        
    }
    
    public void makeTable(String query, TableColumn C1, TableColumn C2, TableColumn C3, 
                                        TableColumn C4, TableView tabId) throws SQLException{
        
        C1.setCellValueFactory(new PropertyValueFactory<>("col1"));
        C2.setCellValueFactory(new PropertyValueFactory<>("col2"));
        C3.setCellValueFactory(new PropertyValueFactory<>("col3"));
        C4.setCellValueFactory(new PropertyValueFactory<>("col4"));
            
        composeData = FXCollections.observableArrayList();
        tabId.setItems(composeData);

        System.out.println("Query For Table");
        System.out.println("Query is \n" + query);
        db.setQuery(query);

         
        for (int i = 0; i < db.getRowCount(); i++) {
            SimpleString trans = new SimpleString();

            trans.col1.setValue(db.getValueAt(i, 0).toString());
            trans.col2.setValue(db.getValueAt(i, 1).toString());
            trans.col3.setValue(db.getValueAt(i, 2).toString());
            trans.col4.setValue(db.getValueAt(i, 3).toString());

            composeData.add(trans);
        }
        
    }
    
    public void makeTable(String query, TableColumn C1, TableColumn C2, TableColumn C3, 
                                        TableColumn C4, TableColumn C5, TableView tabId) throws SQLException{
        
        C1.setCellValueFactory(new PropertyValueFactory<>("col1"));
        C2.setCellValueFactory(new PropertyValueFactory<>("col2"));
        C3.setCellValueFactory(new PropertyValueFactory<>("col3"));
        C4.setCellValueFactory(new PropertyValueFactory<>("col4"));
        C5.setCellValueFactory(new PropertyValueFactory<>("col5"));
            
        composeData = FXCollections.observableArrayList();
        tabId.setItems(composeData);

        System.out.println("Query For Table");
        System.out.println("Query is \n" + query);
        db.setQuery(query);

         
        for (int i = 0; i < db.getRowCount(); i++) {
            SimpleString trans = new SimpleString();

            trans.col1.setValue(db.getValueAt(i, 0).toString());
            trans.col2.setValue(db.getValueAt(i, 1).toString());
            trans.col3.setValue(db.getValueAt(i, 2).toString());
            trans.col4.setValue(db.getValueAt(i, 3).toString());
            trans.col5.setValue(db.getValueAt(i, 4).toString());

            composeData.add(trans);
        }
        
    }
    
     public void makeTable(String query, TableColumn C1, TableColumn C2, TableColumn C3, 
                                        TableColumn C4, TableColumn C5,  TableColumn C6, TableView tabId) throws SQLException{
         
        C1.setCellValueFactory(new PropertyValueFactory<>("col1"));
        C2.setCellValueFactory(new PropertyValueFactory<>("col2"));
        C3.setCellValueFactory(new PropertyValueFactory<>("col3"));
        C4.setCellValueFactory(new PropertyValueFactory<>("col4"));
        C5.setCellValueFactory(new PropertyValueFactory<>("col5"));
        C6.setCellValueFactory(new PropertyValueFactory<>("col6"));
            
        composeData = FXCollections.observableArrayList();
        tabId.setItems(composeData);

        System.out.println("Query For Table");
        db.setQuery(query);

         System.out.println("rows = " + db.getRowCount());
        for (int i = 0; i < db.getRowCount(); i++) {
            SimpleString trans = new SimpleString();

            trans.col1.setValue(db.getValueAt(i, 0).toString());
            trans.col2.setValue(db.getValueAt(i, 1).toString());
            trans.col3.setValue(db.getValueAt(i, 2).toString());
            trans.col4.setValue(db.getValueAt(i, 3).toString());
            trans.col5.setValue(db.getValueAt(i, 4).toString());
            trans.col6.setValue(db.getValueAt(i, 5).toString());

            composeData.add(trans);
        }
        
    }
     
     public void makeOverdraftReportsTable(String query, TableColumn C1, TableColumn C2, TableColumn C3, 
                                        TableColumn C4, TableColumn C5,  TableColumn C6,
                                        TableColumn C7, TableColumn C8, TableView tabId) throws SQLException{
         
        C1.setCellValueFactory(new PropertyValueFactory<>("col1"));
        C2.setCellValueFactory(new PropertyValueFactory<>("col2"));
        C3.setCellValueFactory(new PropertyValueFactory<>("col3"));
        C4.setCellValueFactory(new PropertyValueFactory<>("col4"));
        C5.setCellValueFactory(new PropertyValueFactory<>("col5"));
        C6.setCellValueFactory(new PropertyValueFactory<>("col6"));
        C7.setCellValueFactory(new PropertyValueFactory<>("col7"));
        C8.setCellValueFactory(new PropertyValueFactory<>("col8"));
            
        composeData = FXCollections.observableArrayList();
        tabId.setItems(composeData);

        System.out.println("Query For Table");
        System.out.println("Query is \n" + query);
        db.setQuery(query);

         
        for (int i = 0; i < db.getRowCount(); i++) {
            SimpleString trans = new SimpleString();

            trans.col1.setValue(db.getValueAt(i, 0).toString());
            trans.col2.setValue(db.getValueAt(i, 1).toString());
            trans.col3.setValue(db.getValueAt(i, 2).toString());
            trans.col4.setValue(db.getValueAt(i, 3).toString());
            trans.col5.setValue(db.getValueAt(i, 4).toString());
            trans.col6.setValue(db.getValueAt(i, 5).toString());
            if(db.getValueAt(i, 6).toString() == "true"){
                //trans.col7.setValue(db.getValueAt(i, 6).toString());
                trans.col7.setValue("paid");
            }else{
                 trans.col7.setValue("unpaid");
            }
            if(db.getValueAt(i, 7).toString() == "true"){
                //trans.col8.setValue(db.getValueAt(i, 7).toString());
                 trans.col8.setValue("expired");
            }else{
                trans.col8.setValue("active");
            }
            composeData.add(trans);
        }
        
    }
     
     public void makeTableCheckLastBooleanIfPaid(String query, TableColumn C1, TableColumn C2, TableColumn C3, 
                                        TableColumn C4, TableView tabId) throws SQLException{
         
        C1.setCellValueFactory(new PropertyValueFactory<>("col1"));
        C2.setCellValueFactory(new PropertyValueFactory<>("col2"));
        C3.setCellValueFactory(new PropertyValueFactory<>("col3"));
        C4.setCellValueFactory(new PropertyValueFactory<>("col4"));
            
        composeData = FXCollections.observableArrayList();
        tabId.setItems(composeData);

        System.out.println("Query For Table");
        System.out.println("Query is \n" + query);
        db.setQuery(query);

         
        for (int i = 0; i < db.getRowCount(); i++) {
            SimpleString trans = new SimpleString();

            trans.col1.setValue(db.getValueAt(i, 0).toString());
            trans.col2.setValue(db.getValueAt(i, 1).toString());
            trans.col3.setValue(db.getValueAt(i, 2).toString());
            
              
            if("true".equals(db.getValueAt(i, 3).toString())){
                //trans.col8.setValue(db.getValueAt(i, 6).toString());
                 trans.col4.setValue("paid");
            }else{
                trans.col4.setValue("unpaid");
            }
            composeData.add(trans);
        }
        
    }
     
     public void makeSupplierTableCheckLastBooleanIfPaid(String query, TableColumn C1, TableColumn C2, TableColumn C3, 
                                        TableColumn C4, TableView tabId) throws SQLException{
         
        C1.setCellValueFactory(new PropertyValueFactory<>("col1"));
        C2.setCellValueFactory(new PropertyValueFactory<>("col2"));
        C3.setCellValueFactory(new PropertyValueFactory<>("col3"));
        C4.setCellValueFactory(new PropertyValueFactory<>("col4"));
            
        composeData = FXCollections.observableArrayList();
        tabId.setItems(composeData);

        System.out.println("Query For Table");
        System.out.println("Query is \n" + query);
        db.setQuery(query);

         
        for (int i = 0; i < db.getRowCount(); i++) {
            SimpleString trans = new SimpleString();

            trans.col1.setValue(db.getValueAt(i, 0).toString());
            trans.col2.setValue(db.getValueAt(i, 1).toString());
            trans.col3.setValue(db.getValueAt(i, 2).toString());
            
            
              
            if("true".equals(db.getValueAt(i, 3).toString())){
                //trans.col8.setValue(db.getValueAt(i, 6).toString());
                 trans.col4.setValue("paid");
            }else{
                trans.col4.setValue("unpaid");
            }
            composeData.add(trans);
        }
        
    }
     
    public void makeSuspensionTable(String query, TableColumn C1, TableColumn C2, TableColumn C3, 
                                        TableColumn C4, TableColumn C5, TableColumn C6, TableView tabId) throws SQLException, ParseException{
        dNow = new Date();
        int datediff;
        Date today;
        today = dateOnly.parse(dateOnly.format(dNow));
        
        C1.setCellValueFactory(new PropertyValueFactory<>("col1"));
        C2.setCellValueFactory(new PropertyValueFactory<>("col2"));
        C3.setCellValueFactory(new PropertyValueFactory<>("col3"));
        C4.setCellValueFactory(new PropertyValueFactory<>("col4"));
        C5.setCellValueFactory(new PropertyValueFactory<>("col5"));
        C6.setCellValueFactory(new PropertyValueFactory<>("col6"));
            
        composeData = FXCollections.observableArrayList();
        tabId.setItems(composeData);

        System.out.println("Query For Table");
        System.out.println("Query is \n" + query);
        db.setQuery(query);

         
        for (int i = 0; i < db.getRowCount(); i++) {
            SimpleString trans = new SimpleString();

            trans.col1.setValue(db.getValueAt(i, 0).toString());
            trans.col2.setValue(db.getValueAt(i, 1).toString());
            trans.col3.setValue(db.getValueAt(i, 2).toString());
            trans.col4.setValue(db.getValueAt(i, 3).toString());
            trans.col5.setValue(db.getValueAt(i, 4).toString());
            datediff = UsefullStuff.differenceInMonths(dNow, dateOnly.parse(db.getValueAt(i, 4).toString()));
            System.out.println("difference in months is " + datediff);
            if(datediff >= 0){
                trans.col6.setValue("EXPIRED");
            }else{
                trans.col6.setValue("VALID");
            }
            
            

            composeData.add(trans);
        }
        
    }
    
    
    
    
    
    //this method is comming as a reason because when using the sum aggregation, i cannot add the name of the custumer to the query.
    //so this means of separately first pulling out the custumer id and then the cusutumer name came to remedy the situation until a better approach is found.
    public void makeCustDebtTable(String query, TableColumn C1, TableColumn C2,  TableView tabId) throws SQLException{
        
        C1.setCellValueFactory(new PropertyValueFactory<>("col1"));
        C2.setCellValueFactory(new PropertyValueFactory<>("col2"));
            
        composeData = FXCollections.observableArrayList();
        tabId.setItems(composeData);

        System.out.println("Query For Table");
        System.out.println("Query is \n" + query);
        db.setQuery(query);

         
        for (int i = 0; i < db.getRowCount(); i++) {
            SimpleString trans = new SimpleString();

            String cust_id = db.getValueAt(i, 0).toString();
            long amt = Long.parseLong(db.getValueAt(i, 1).toString());
            System.out.println("total for " + cust_id + " = " + amt + "\n");
            
            dbrep.setQuery("select njieDB.CUSTUMER.NAME FROM njieDB.CUSTUMER WHERE njieDB.CUSTUMER.CUST_ID = '" + cust_id + "' ");
            
            trans.col1.setValue(dbrep.getValueAt(0, 0).toString());
            trans.col2.setValue(String.valueOf(amt));            

            composeData.add(trans);
        }
        
    }
    
    public void makeSuppDebtTable(String query, TableColumn C1, TableColumn C2,  TableView tabId) throws SQLException{
        
        C1.setCellValueFactory(new PropertyValueFactory<>("col1"));
        C2.setCellValueFactory(new PropertyValueFactory<>("col2"));
            
        composeData = FXCollections.observableArrayList();
        tabId.setItems(composeData);

        System.out.println("Query For Table");
        System.out.println("Query is \n" + query);
        db.setQuery(query);

         
        for (int i = 0; i < db.getRowCount(); i++) {
            SimpleString trans = new SimpleString();

            int supp_id = Integer.parseInt(db.getValueAt(i, 0).toString());
            long totalAmt = Long.parseLong(db.getValueAt(i, 1).toString());
            System.out.println("total for " + supp_id + " = " + totalAmt + "\n");
            
            dbrep.setQuery("select njieDB.SUPPLIER.NAME FROM njieDB.SUPPLIER WHERE njieDB.SUPPLIER.SUP_CODE = " + supp_id + " ");
            
            trans.col1.setValue(dbrep.getValueAt(0, 0).toString());
            trans.col2.setValue(String.valueOf(totalAmt));            

            composeData.add(trans);
        }
        
    }
    
    
    
    
}
