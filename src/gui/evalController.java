/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import pidev.entities.Evaluations;
import pidev.Services.EvaluationService;
import pidev.entities.paiement;
import pidev.services.paiementService;



/**
 * FXML Controller class
 *
 * @author user
 */
public class evalController implements Initializable {

    @FXML
    private TableView<Evaluations> table;
    @FXML
    private TableColumn<Double, Evaluations> note;
    @FXML
    private TableColumn<String, Evaluations> comment;
    @FXML
    private TableColumn<Evaluations, ?> id;
    @FXML
    private TableColumn<String, Evaluations> lieu;
    @FXML
    private JFXTextField recherche;
    @FXML
    private JFXButton back;
     EvaluationService ps=new EvaluationService();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<Evaluations> Evaluations = FXCollections.observableArrayList();
       
         for(Evaluations p: ps.afficher())
             Evaluations.add(p);
         
        note.setCellValueFactory(new PropertyValueFactory<>("note"));
         comment.setCellValueFactory(new PropertyValueFactory<>("commentaire"));
        lieu.setCellValueFactory(new PropertyValueFactory<>("lieu_cours"));
      

       id.setCellValueFactory(new PropertyValueFactory<>("id")); 
       
  
       
      table.setItems(Evaluations);
        
     
     
        // TODO
    }    

    @FXML
    private void recherche(ActionEvent event) {
    }

  
            
            
        
        
      
    
        
    

    @FXML
    private void back(ActionEvent event) {
    
       
         try {
           
        javafx.scene.Parent view = FXMLLoader.load(getClass().getResource("Affichercours.fxml"));
        Scene sceneview = new Scene(view);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
