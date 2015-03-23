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
public class ViewOverdraftTab {
    
    public SimpleStringProperty staffName = new SimpleStringProperty(); 
    public SimpleStringProperty amtIssued = new SimpleStringProperty(); 
    public SimpleStringProperty dateIssued = new SimpleStringProperty(); 
    public SimpleStringProperty expiryDate = new SimpleStringProperty();
    public SimpleStringProperty status = new SimpleStringProperty();
    
    public String getCode() {
        return staffName.get();
    }
    public void setCode(String col) {
        staffName.set(col);
    }
    
    public String getName() {
        return amtIssued.get();
    }
    public void setName(String col) {
        amtIssued.set(col);
    }
    
    public String getDesc() {
        return dateIssued.get();
    }
    public void setDesc(String col) {
        dateIssued.set(col);
    }
    
    public String getUsage() {
        return expiryDate.get();
    }
    public void setUsage(String col) {
        expiryDate.set(col);
    }
    
    public String getStatus() {
        return status.get();
    }
    public void setStatus(String col) {
        status.set(col);
    }
    
}
