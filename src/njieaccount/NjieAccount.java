/*
 * By Fongoh Martin Tayong
fonoghmartin@gmail.com
 * and open the template in the editor.
 */

package njieaccount;

import java.text.SimpleDateFormat;
import java.util.Date;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.controlsfx.dialog.Dialogs;

/**
 *
 * @author root
 */
public class NjieAccount extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        /*   this is the file that is suppose to be loaded. the one presently being loaded is used to 
             work on the reports 
                                    njie.fxml          */
//        Liscence expire = new Liscence();
//        boolean ad = expire.checkExpiry();
        
            /*      LISCENCE            */
        
            
            SimpleDateFormat aa = new SimpleDateFormat("yyyyMMdd");
            Date dNow = new Date();
            
            String exp = "20151231";//i have changed this expiry date to test for overdraft
            String today = aa.format(dNow);
            
            int intXp = Integer.valueOf(exp);
            int intNw = Integer.valueOf(today);
            int daysLeft = intNw - intXp;
            System.out.println("expiration \n today:" + intNw + " exp: " + intXp);
            if(intNw > intXp)
            {
                System.out.println("Your trial period is over, please contact Unity Systems for a full liceinced version");
                Dialogs.create().title("Expire").masthead("Renew your one year liscence")
                        .message("Your copy has expired. Please contact Unity systems for the "
                                + "liscence of another year.").showInformation();
                Runtime.getRuntime().halt(0);
            }

            else{
                //Dialogs.create().title("trial").message("Operating under trial mode : \n\n\t\t30days" ).showInformation();
                System.out.println("Your trial period is now left");
            }
        
            /*      LISCENCE            */
        
        
            Parent root;
            root = FXMLLoader.load(getClass().getResource("njie.fxml"));

            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.setTitle("Njeiforbi Accounts Books 2.0.1");
            stage.setResizable(false);
            //Image testIcon = new Image(getClass().getResourceAsStream("/img/app_icon.ico"));

            //stage.getIcons().add(testIcon);
            stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
