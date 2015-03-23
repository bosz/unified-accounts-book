/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Tables;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author root
 */
public class AccountsController implements Initializable {
    @FXML
    private Pane defaultManagerPane;
    @FXML
    private Pane addNewManagerPane;
    @FXML
    private Pane editManagerInfoPane;
    @FXML
    private Pane deleteManagerInfoPane;
    @FXML
    private Pane defaultCashierPane;
    @FXML
    private Pane addNewCashierPane;
    @FXML
    private Pane editCashierInfoPane;
    @FXML
    private Pane deleteCashierPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public AccountsController() {
    }

    
    /*************** MANAGER VIEW TOGGLING*********************
      These set of manager management display methods will toggle the 
      manager's add, edit and delete panes and the default will trigger a default 
      page when the tab is changed to the managers' account management
    */
    
    //this will clear all the panse in the manager section of accounts editing
    private void clearAllManagerPanes(){
        addNewManagerPane.setVisible(false);
        editManagerInfoPane.setVisible(false);
        deleteManagerInfoPane.setVisible(false);
        defaultManagerPane.setVisible(false);
    }
    
    
    @FXML
    private void displayAddNewManagerPane(MouseEvent event) {
        clearAllManagerPanes();
        addNewManagerPane.setVisible(true);
    }

    @FXML
    private void displayEditManagerPane(MouseEvent event) {
        clearAllManagerPanes();
        editManagerInfoPane.setVisible(true);
    }

    @FXML
    private void displayDeleteManagerPane(MouseEvent event) {
        clearAllManagerPanes();
        deleteManagerInfoPane.setVisible(true);
    }

    @FXML
    private void displayDefaultManagerPane(Event event) {
        clearAllManagerPanes();
        defaultManagerPane.setVisible(true);
    }
    
    

    /************* CASHIER VIEW TOGGLING*********************88
     The next 4 methods are going to be used to toggle the display of the various
     sections(panes) for the mangement of the cashiers' information.
     The displaycashierdefault method is attributed to the tab such that when it's clicke, 
     it displays a default page showing what that section(tab) is all about.*/
   
    //this method will clear all the panes in the cashier accounts management
    public void clearAllCashierPanes(){
        addNewCashierPane.setVisible(false);
        editCashierInfoPane.setVisible(false);
        deleteCashierPane.setVisible(false);
        defaultCashierPane.setVisible(false);
    }
    
    @FXML
    private void displayAddNewCashierPane(MouseEvent event) {
        clearAllCashierPanes();
        addNewCashierPane.setVisible(true);
    }

    @FXML
    private void displayEditCashierPane(MouseEvent event) {
        clearAllCashierPanes();
        editCashierInfoPane.setVisible(true);
    }

    @FXML
    private void displayDeleteCashierPane(MouseEvent event) {
        clearAllCashierPanes();
        deleteCashierPane.setVisible(true);
    }

    @FXML
    private void displayDefaultCashierPane(Event event) {
        clearAllCashierPanes();
        defaultCashierPane.setVisible(true);
    }
    
}
