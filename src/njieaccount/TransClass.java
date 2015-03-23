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
public class TransClass {
    
    public SimpleStringProperty code = new SimpleStringProperty(); 
    public SimpleStringProperty name = new SimpleStringProperty(); 
    public SimpleStringProperty desc = new SimpleStringProperty(); 
    public SimpleStringProperty usage = new SimpleStringProperty();
    
    public String getCode() {
        return code.get();
    }
    public void setCode(String fName) {
        code.set(fName);
    }
    
    public String getName() {
        return name.get();
    }
    public void setName(String fName) {
        name.set(fName);
    }
    
    public String getDesc() {
        return desc.get();
    }
    public void setDesc(String fName) {
        desc.set(fName);
    }
    
    public String getUsage() {
        return usage.get();
    }
    public void setUsage(String fName) {
        usage.set(fName);
    }
    
}
