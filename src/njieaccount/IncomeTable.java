/*
 * This class will hold the siplle*properties to manage the income teble
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package njieaccount;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author root
 */
public class IncomeTable {
    
    public SimpleStringProperty code = new SimpleStringProperty(); 
    public SimpleStringProperty name = new SimpleStringProperty(); 
    public SimpleStringProperty memo = new SimpleStringProperty(); 
    public SimpleStringProperty amount = new SimpleStringProperty();
    public SimpleStringProperty time = new SimpleStringProperty();
    
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
    
    public String getMemo() {
        return memo.get();
    }
    public void setMemo(String fName) {
        memo.set(fName);
    }
    
    public String getAmount() {
        return amount.get();
    }
    public void setAmount(String fName) {
        time.set(fName);
    }
    
    public String getTime() {
        return time.get();
    }
    public void setTime(String fName) {
        time.set(fName);
    }
    
}
