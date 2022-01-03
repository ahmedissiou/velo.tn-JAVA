/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import pidev.entities.fos_user;
import pidev.services.fos_userService;
import pidev.pidev;

/**
 * FXML Controller class
 *
 * @author ouertani
 */
 public class ProfileController implements Initializable {

    @FXML
    private AnchorPane profilecontent;
      @FXML
    private AnchorPane content;
    @FXML
    private Text txtusername;
    @FXML
    private Text txtemail;
    @FXML
    private Button gochangeUsername;
    @FXML
    private AnchorPane profilecontent1;
    @FXML
    private Text txtnumcarte;
    @FXML
    private Text txtsolde;
    @FXML
    private JFXButton btnlogout;
    @FXML
    private JFXButton back;
    @FXML
    private JFXButton btnmesevent;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        fos_user user=new fos_user();
        fos_userService us=new fos_userService();
        try {
            user=us.getUserById(pidev.user_id);
            txtusername.setText(user.getUsername());
            txtemail.setText(user.getEmail());
            txtnumcarte.setText(String.valueOf(user.getNumcarte()));
            txtsolde.setText(String.valueOf(user.getSolde()));
            System.out.print(user.getSolde());
            System.out.print(user.getNumcarte());
        } catch (SQLException ex) {
            Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }    
       @FXML
    private void back(ActionEvent event) throws IOException {
         
         try {
        javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("homeClt.fxml"));
        
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }


    @FXML
    private void gochangeUsername(ActionEvent event) throws IOException {
        profilecontent1.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("/gui/username.fxml"));
        profilecontent1.getChildren().add(parent);
        profilecontent1.toFront();
    }

    @FXML
    private void logout(ActionEvent event) throws IOException {
        pidev.fos_user=null;
        Stage stage= (Stage)btnlogout.getScene().getWindow();
       try {
        javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("login.fxml"));
        
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        
    }

    @FXML
    private void mesevent(ActionEvent event) throws IOException  {
        content.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("/pidev/gui/AllEvents.fxml"));
        content.getChildren().add(parent);
        content.toFront();
    }

    
}
