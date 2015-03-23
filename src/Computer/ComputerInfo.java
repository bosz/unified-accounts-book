/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Computer;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import static java.lang.System.exit;
import org.controlsfx.dialog.Dialogs;

/**
 *
 * @author root
 */
public class ComputerInfo {
    
    FileReader in = null;
    private String computerName;

    public ComputerInfo(){
        this.setComputerName();
    }
    
    public void setComputerName(){
        String compName = null;
        int tmp;
        String ipFileLocation;
        ipFileLocation = "config.txt";
        System.out.println("config location = " + ipFileLocation);
        
        try {
            System.out.println("opeinig the input file");
            try {
                in = new FileReader(ipFileLocation);
                System.out.println("input file opened succesfully");
            } catch (FileNotFoundException ex) {
                System.out.println("error opening file \n " + ex.getMessage());
                Dialogs.create()
                    .title("File IO Error")
                    .masthead("There was an error reading the name of the computer to connect to")
                    .message("Please, ensure that the file \n\n c:\\Program Files(x86) \\ Pier \\ UAB \\ config.txt \n\n "
                            + "is available.\nIt should also have the computer name hosting the database").showError();
                exit(1);
            }
            compName = String.valueOf((char)in.read());
            while((tmp = in.read()) != -1){
                compName += (char)tmp;
            }
            this.computerName = compName;
        } catch (IOException e) {
            Dialogs.create()
                    .title("File IO Error")
                    .masthead("There was an error reading the name of the computer to connect to")
                    .message("Please, ensure that the file \n\n c:\\Program Files(x86) \\ Pier \\ UAB \\ config.txt \n\n "
                            + "is available.\nIt should also have the computer name hosting the database").showError();
            this.computerName = null;
        } 
        
    }
    
    public String getCompupterName(){
        return this.computerName;
    }
    
}
