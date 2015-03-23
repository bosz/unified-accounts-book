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
public class ViewStaffTable {
    
     
    public SimpleStringProperty code = new SimpleStringProperty();
    public SimpleStringProperty name = new SimpleStringProperty();
    public SimpleStringProperty phone = new SimpleStringProperty();
    public SimpleStringProperty salary = new SimpleStringProperty();
    public SimpleStringProperty branch = new SimpleStringProperty();
 
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
    
    public String getPhone() {
        return phone.get();
    }
    public void setPhone(String fName) {
        phone.set(fName);
    }
    
    public String getSalary() {
        return salary.get();
    }
    public void setSalary(String fName) {
        salary.set(fName);
    }
    public String getBranch() {
        return branch.get();
    }
    public void setBranch(String fName) {
        branch.set(fName);
    }
}
