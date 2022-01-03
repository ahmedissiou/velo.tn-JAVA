/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import pidev.Services.EvaluationService;
import pidev.entities.Evaluations;
import pidev.entities.cours;
import pidev.services.coursService;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author user
 */
public class NewEvaluationController implements Initializable {

    @FXML
    private AnchorPane avr;
    @FXML
    private ImageView ImgEvent;
    @FXML
    private AnchorPane btnAjouter;
    @FXML
    private JFXSlider Notech;
    @FXML
    private JFXTextField Commentch;
    @FXML
    private Button btnAjout;
    @FXML
    private Label Titre;
    @FXML
    private Button consulter;
     public static int  id;
        coursService es = new coursService();
        cours e = es.affichecoursparid(id);

    /**
     * Initializes the controller class.
     */
     @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        Titre.setText(e.getLieu_cours());
        Image image = new Image("http://localhost//pifinal/pidevfinal/web/images/" + e.getNom_image());
        ImgEvent.setImage(image);
       
    }

    @FXML
    private void ajouter(ActionEvent event) {
        if (validateInputs()) {

            
            int id = e.getId();
            String commentaire = Commentch.getText();
            double note = Notech.getValue();
            int user_id = pidev.pidev.user_id;

            
            EvaluationService evs = new EvaluationService();
            Evaluations ev = new Evaluations(id, note, commentaire, user_id);
            evs.ajouter(ev);
            System.out.println("Evaluation ajouté");
           /* Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Evaluation bien ajouté");

            alert.showAndWait();*/
           
           
            String tit = "Evaluation réussi";
            String message = "Evaluation bien ajouté.\n note :"+ev.getNote();
            NotificationType notification = NotificationType.SUCCESS;
    
            TrayNotification tray = new TrayNotification(tit, message, notification);          
            tray.setAnimationType(AnimationType.POPUP);
            tray.showAndDismiss(javafx.util.Duration.seconds(10));
       

        }
    }


   @FXML
    private void consulter(ActionEvent event) {
        {
            try {
                avr.getChildren().clear();
                Parent parent = FXMLLoader.load(getClass().getResource("/gui/Allcours.fxml"));
                avr.getChildren().add(parent);
                avr.toFront();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
     private boolean validateInputs() {
        if (Commentch.getText().length() == 0) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("tous les champs doivent etre remplis");
            alert.showAndWait();
            return false;
        }

        return true;
    }
    
}
