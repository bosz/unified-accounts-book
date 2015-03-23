///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//
//package njieaccount;
//
//import javafx.event.Event;
//import javafx.fxml.FXML;
//import javafx.scene.input.MouseEvent;
//
///**
// *
// * @author root
// */
//public class Overdraft {
//    
//    NjieController con;
//    
//    public Overdraft()
//    {
//        con = new NjieController();
//    }
//    
//    @FXML
//    private void manager_issue_OD_to_database(MouseEvent event) {
//    }
//    
//    private void clearAllOverdraft(){
//        con.manager_IssueOverdraft_Pane.setVisible(false);
//        con.manager_viewOverdraft_Pane.setVisible(false);
//        con.manager_defaultOverdraft.setVisible(false);
//    }
//
//    @FXML
//    private void manager_IssueOverdraft(MouseEvent event) {
//        clearAllOverdraft();
//        con.manager_IssueOverdraft_Pane.setVisible(true);
//    }
//
//    @FXML
//    private void manager_editOverdraft(MouseEvent event) {//NOT YET IMPLEMENTED
//    }
//    
//    @FXML
//    private void manager_deleteOverdraft(MouseEvent event) {//NOT YET IMPLEMENTED
//    }
//
//    @FXML
//    public void manager_viewOverdraft(MouseEvent event) {
//        clearAllOverdraft();
//        con.manager_viewOverdraft_Pane.setVisible(true);
//    }
//
//    @FXML
//    private void activate_defaultManager_Overdraft(Event event) {
//        clearAllOverdraft();
//        con.manager_defaultOverdraft.setVisible(true);
//    }
//    
//}
