/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package njieaccount;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author root
 */
public class Liscence {
    
    public Liscence(){
        
    }
    
    public boolean checkExpiry(){
        Calendar expiry = Calendar.getInstance();
        String expire = "2014-09-15";
        
        expiry.set(2014, 9, 14);
        Calendar now = Calendar.getInstance();

        if(now.after(expiry)){
            Runtime.getRuntime().halt(0);
            System.out.println("Your trial period is over, please contact Unity Systems for a full liceinced version");
            return true;
        }

        else{
            System.out.println("Your trial period is now left");
            return false;
        }
    }
}
