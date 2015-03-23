/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package njieaccount;

import java.sql.SQLException;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author root
 */
public class ManagerReport {
    
    
     
    DatabaseHelper db;
    
    public ManagerReport() throws SQLException, Exception{
        db = new DatabaseHelper();   
    }
    
    
    /*******************************@generalTransactionReports ************/
    @FXML
    private TableView<IncomeTable> report_genTrans_table;
    @FXML
    private TableColumn report_GT_code;
    @FXML
    private TableColumn report_GT_name;
    @FXML
    private TableColumn  report_GT_desc;
    @FXML
    private TableColumn report_GT_totamt;
    
    ArrayList ArrayLGTcode = new ArrayList();
    ArrayList ArrayLGTname = new ArrayList();
    ArrayList ArrayLGTdesc = new ArrayList();
    ArrayList ArrayLGTtotamt = new ArrayList();  
    
    ObservableList OLGTcode;
    ObservableList OLGTname;
    ObservableList OLGTdesc;
    ObservableList OLGTtotamt;
    
    ObservableList<IncomeTable> composeGenTrans;
   
    
    
    
    public void genTrans(String table) throws SQLException {
        report_GT_code.setCellValueFactory(new PropertyValueFactory("code"));
        report_GT_name.setCellValueFactory(new PropertyValueFactory("name"));
        report_GT_desc.setCellValueFactory(new PropertyValueFactory("memo"));
        report_GT_totamt.setCellValueFactory(new PropertyValueFactory("amount"));
        
        report_genTrans_table.getColumns().clear();
        report_genTrans_table.getColumns().addAll(report_GT_code, report_GT_name, report_GT_desc, report_GT_totamt);
        
        ArrayLGTcode = getAllDataWhere(table + ".CODE", "njieDB.INCOME");
        ArrayLGTname = getAllDataWhere("njieDB.INCOME.NAME", "njieDB.INCOME");
        ArrayLGTdesc = getAllDataWhere("njieDB.INCOME.MEMO", "njieDB.INCOME");
        ArrayLGTtotamt = getAllDataWhere("njieDB.INCOME.AMOUNT", "njieDB.INCOME");
        
        
        OLGTcode =  FXCollections.observableArrayList(ArrayLGTcode);
        OLGTname =  FXCollections.observableArrayList(ArrayLGTname);
        OLGTdesc =  FXCollections.observableArrayList(ArrayLGTdesc);
        OLGTtotamt =  FXCollections.observableArrayList(ArrayLGTtotamt);
        composeGenTrans = FXCollections.observableArrayList();
        
        String tmp;
        for(int i = 0 ;  i < ArrayLGTdesc.size(); i++){
           IncomeTable GTdata = new IncomeTable();
           GTdata.code.setValue(ArrayLGTcode.get(i).toString());
           GTdata.name.setValue(ArrayLGTname.get(i).toString());
           GTdata.memo.setValue(ArrayLGTdesc.get(i).toString());
           GTdata.amount.setValue(ArrayLGTtotamt.get(i).toString());
           composeGenTrans.add(GTdata);
        }
        
        try {
            report_genTrans_table.getItems().setAll(composeGenTrans);
            report_genTrans_table.setItems(composeGenTrans);

        } catch (Exception nn) {
            System.out.println(nn.getMessage());
        }
    }
    
    public void staff(){
        
    }
    
    public void supplier(){
        
    }
    
    public void custumer(){
        
    }
    
    public ArrayList getAllDataWhere(String data, String table) {
        ArrayList re = new ArrayList();
        try {

            String sql = "select " + data + " from " + table + "GROUP BY " + data + ".CODE";
            //  DatabaseHelper db = new DatabaseHelper();
            System.out.println(sql);
            re = db.ExecuteQuery(sql);

        } catch (Exception sh) {
            System.out.println(sh.getMessage());

        }
        return re;

    }
    
}
