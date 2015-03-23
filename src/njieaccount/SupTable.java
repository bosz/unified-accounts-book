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
public class SupTable {
    
     public  final SimpleStringProperty code = new SimpleStringProperty(); 
     public  final SimpleStringProperty name = new SimpleStringProperty();      
     public  final SimpleStringProperty service = new SimpleStringProperty();      
    
       public String getCode(){
     return code.get();
     
     }
     
     public String getName(){
     return name.get();
     
     }
     
     public String getService(){
     return service.get();
     
     }
    
}
