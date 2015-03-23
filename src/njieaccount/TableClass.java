/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package njieaccount;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author root
 */
public class TableClass {
    
     public  final SimpleStringProperty col1 = new SimpleStringProperty(); 
     public  final SimpleStringProperty col2 = new SimpleStringProperty();      
     public  final SimpleStringProperty col3 = new SimpleStringProperty();    
     public  final SimpleStringProperty col4 = new SimpleStringProperty(); 
     public  final SimpleStringProperty col5 = new SimpleStringProperty();      
     public  final SimpleStringProperty col6 = new SimpleStringProperty(); 
     public  final SimpleStringProperty col7 = new SimpleStringProperty();
    
     public String getCol1(){
     return col1.get();
     
     }
     
     public String getCol2(){
     return col2.get();
     
     }
     
     public String getCol3(){
     return col3.get();
     }
     
     public String getCol4(){
     return col4.get();
     
     }
     
     public String getCol5(){
     return col5.get();
     
     }
     
     public String getCol6(){
     return col6.get();
     
     }

    public void makeTable() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
