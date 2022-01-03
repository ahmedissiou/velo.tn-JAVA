/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pitry;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pidev.entities.fos_user;
import pidev.services.fos_userService;

/**
 *
 * @author ouertani
 */
public class PiTry extends Application {
    
    public static fos_user fos_user;
    public static int user_id;
    

       
    
    @Override
    public void start(Stage primaryStage) {
        try {
            /*   Button btn = new Button();
            btn.setText("Say 'Hello World'");
            btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
            System.out.println("Hello World!");
            }
            });
            
            StackPane root = new StackPane();
            root.getChildren().add(btn);
            */
            
            Parent root = FXMLLoader.load(getClass().getResource("/gui/Login.fxml"));
            
            Scene scene = new Scene(root);
            
            primaryStage.setTitle("3a20!");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            
            
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        System.out.println(fos_user);
        System.out.println(user_id);
    }
    
    }
    
