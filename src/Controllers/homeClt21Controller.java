/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import pidev.pidev;

/**
 * FXML Controller class
 *
 * @author user
 */
public class homeClt21Controller implements Initializable {

    @FXML
    private AnchorPane content;
    @FXML
    private ImageView image;
    @FXML
    private Button profile;
    @FXML
    private Button logout;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void profile(ActionEvent event) throws IOException
    {     content.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("/gui/profile.fxml"));
        content.getChildren().add(parent);
        content.toFront();
    }
    

   @FXML
    private void logout(ActionEvent event) throws IOException {
        pidev.fos_user=null;
        Stage stage= (Stage)logout.getScene().getWindow();
       try {
        javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("login.fxml"));
        
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        
    }}
