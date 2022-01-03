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
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import pidev.pidev;

/**
 * FXML Controller class
 *
 * @author user
 */
public class homeAdmin2Controller implements Initializable {

    @FXML
    private AnchorPane content;
    @FXML
    private ImageView image;
    @FXML
    private Button notif;
    @FXML
    private Button profile;
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void notif(ActionEvent event) {
        Image img = new Image ("/gui/stick.png");
     Notifications notificationBuilder = Notifications.create()
             .title("Notifications")
             .text("vous avez des nouvelles reclamations ..!")
             .graphic(new ImageView(img))
             .hideAfter(Duration.seconds(5))
             .position(Pos.TOP_RIGHT)
             .onAction(new EventHandler<ActionEvent>() {
               
                 public void hanle (ActionEvent event){
               System.out.println("Clicked on");
                 
                  }

         @Override
         public void handle(ActionEvent event) {
             throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
         }
             });
     notificationBuilder.darkStyle();
     
     notificationBuilder.show();
             }
@FXML
    private void profile(ActionEvent event) throws IOException {
         content.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("/gui/profile.fxml"));
        content.getChildren().add(parent);
        content.toFront();}

   

        
    
    
}
