/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.controllers;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import pidev.entities.fos_user;
import pidev.services.fos_userService;

/**
 * FXML Controller class
 *
 * @author ouertani
 */
public class AdminUsersController implements Initializable {
    
    List<fos_user> listUsers;
    ObservableList<fos_user> listViewUsers;

    @FXML
    private TableView<fos_user> tableView;
    @FXML
    private TableColumn<?, ?> username;
    @FXML
    private TableColumn<?, ?> email;
    @FXML
    private JFXButton btnActiver;
    @FXML
    private JFXButton btnDesactiver;
    @FXML
    private JFXButton btnAjout;
    @FXML
    private JFXButton btnsupp;
    @FXML
    private TableColumn<?, ?> numcarte;
    @FXML
    private TableColumn<?, ?> etat;
    @FXML
    private Label infoact;
    @FXML
    private Label inforole;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        btnActiver.setVisible(false);
        btnDesactiver.setVisible(false);
        btnAjout.setVisible(false);
        btnsupp.setVisible(false);
        tableView.setOnMouseClicked((event) -> {
        if (tableView.getSelectionModel().getSelectedItem().getEnabled() == 0  ){
            btnActiver.setVisible(true);
            btnDesactiver.setVisible(false);
            infoact.setText("Ce compte est desactivé");
        }
        else {
        btnActiver.setVisible(false);
        btnDesactiver.setVisible(true);
        infoact.setText("Ce compte est activé");
        
                
        if(tableView.getSelectionModel().getSelectedItem().getRoles().equals("ROLE_USER") ){
        btnAjout.setVisible(true);
        btnsupp.setVisible(false);
        inforole.setText("Cette utilisater est un user");
        }
        else {
        btnAjout.setVisible(false);
        btnsupp.setVisible(true);
        inforole.setText("Cette utilisater est un Admin");
        }
        }
        
        }
        )
                ;
        try {
            initCol();
            loadData();
        } catch (Exception e) {
        }
    }
    
        private void loadData() {

        try {
            fos_userService sr = new fos_userService();
            listUsers = new ArrayList<>();
            listUsers = sr.getAllUsers();
            System.err.println(listUsers);
            listViewUsers = FXCollections.observableArrayList(listUsers);
            tableView.setItems(listViewUsers);
            initCol();
        } catch (Exception e) {
        }

    }


    private void initCol() {
    email.setCellValueFactory(new PropertyValueFactory<>("email"));
    numcarte.setCellValueFactory(new PropertyValueFactory<>("numcarte"));
    username.setCellValueFactory(new PropertyValueFactory<>("username"));
        

    }    

    @FXML
    private void ActiverUser(ActionEvent event) throws IOException {
        fos_userService us= new fos_userService();
        try {
            us.UserSetEnable(tableView.getSelectionModel().getSelectedItem().getId());
        } catch (SQLException ex) {
            Logger.getLogger(AdminUsersController.class.getName()).log(Level.SEVERE, null, ex);
        }
        loadData();
        
                         
        
    }

    @FXML
    private void DesactiverUser(ActionEvent event) throws IOException{
        System.out.println("desactiver");
        fos_userService us= new fos_userService();
        try {
            us.UserSetDisable(tableView.getSelectionModel().getSelectedItem().getId());
        } catch (SQLException ex) {
            Logger.getLogger(AdminUsersController.class.getName()).log(Level.SEVERE, null, ex);
        }
        loadData();
    }

    @FXML
    private void ajoutadmine(ActionEvent event) throws IOException {
                fos_userService us= new fos_userService();
        try {
            us.UserSetadmin(tableView.getSelectionModel().getSelectedItem().getId());
        } catch (SQLException ex) {
            Logger.getLogger(AdminUsersController.class.getName()).log(Level.SEVERE, null, ex);
        }
           loadData();
              
    }

    @FXML
    private void suppAdmine(ActionEvent event) throws IOException {
                fos_userService us= new fos_userService();
        try {
            us.adminSetuser(tableView.getSelectionModel().getSelectedItem().getId());
        } catch (SQLException ex) {
            Logger.getLogger(AdminUsersController.class.getName()).log(Level.SEVERE, null, ex);
        }
        loadData();
         
    }
    
}
