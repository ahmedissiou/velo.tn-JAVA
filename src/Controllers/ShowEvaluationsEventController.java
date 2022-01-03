/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import pidev.Services.EvaluationService;
import pidev.entities.Evaluations;
import pidev.entities.cours;
import pidev.services.coursService;

/**
 * FXML Controller class
 *
 * @author user
 */
public class ShowEvaluationsEventController implements Initializable {

 
 
    @FXML
    private TableColumn<Evaluations, String> C2;
    @FXML
    private TableColumn<Evaluations, String> C3;
    coursService evt = new coursService();
    cours e = evt.affichecoursparid(id);
    public static int id;
    private static int cour_id;
    EvaluationService evs= new EvaluationService();
    @FXML
    private TableView<Evaluations> table;
    @FXML
    private ImageView image;
    @FXML
    private Label titre;
    /**
     * Initializes the controller class.
     */
  @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<Evaluations> evaluation = FXCollections.observableArrayList();
        for (Evaluations ev : evs.afficherEvaluationsparidev(id)) {
            evaluation.add(ev);
          
            C2.setCellValueFactory(new PropertyValueFactory<>("note"));
            C3.setCellValueFactory(new PropertyValueFactory<>("commentaire"));

            table.setItems(evaluation);
           


        }
          String img = "http://localhost//pifinal/pidevfinal/web/images/" + e.getNom_image();
            image.setImage(new Image(img));
            titre.setText(e.getLieu_cours());
    }    
    
}
