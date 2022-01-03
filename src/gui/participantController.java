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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import pidev.entities.Comment;
import pidev.entities.cours;
import pidev.entities.paiement;
import pidev.entities.paiement;
import pidev.services.paiementService;


/**
 * FXML Controller class
 *
 * @author user
 */
public class participantController implements Initializable {

    @FXML
    private TableView<paiement> table;
     @FXML
    private TableColumn<paiement, String> nom;
      @FXML
    
    private TableColumn<paiement, String> pnom;
       @FXML
  
    private TableColumn<paiement, Integer> age;
        @FXML
    
    private TableColumn<paiement, Integer> niveau;
         @FXML
    private TableColumn<paiement, Integer> prix;
     @FXML
    private TableColumn<paiement, ?> id;
    @FXML
    private JFXTextField recherche;
    @FXML
    private JFXButton back;
     @FXML
    private JFXButton delete;
     @FXML
    private Label total;
         paiementService ps=new paiementService();

        private paiement selectedid;
  

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                          
   ObservableList<paiement> paiement = FXCollections.observableArrayList();
     try {       
         for(paiement p: ps.FindAllpay())
             paiement.add(p);
         
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
         pnom.setCellValueFactory(new PropertyValueFactory<>("prénom"));
        age.setCellValueFactory(new PropertyValueFactory<>("age"));
        prix.setCellValueFactory(new PropertyValueFactory<>("prixcours"));
        niveau.setCellValueFactory(new PropertyValueFactory<>("niveau"));

       id.setCellValueFactory(new PropertyValueFactory<>("id")); 
       
  
       
      table.setItems(paiement);
        
     
     } catch (SQLException ex) {
          System.out.println(ex);
     } 
       
        try {
            total.setText(String.valueOf(ps.total()));
            // TODO
        } catch (SQLException ex) {
            Logger.getLogger(participantController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

  

   

    @FXML
    private void recherche(ActionEvent event) {
    }

  
@FXML
    private void delete(ActionEvent event) throws SQLException {
        
        
        if(table.getSelectionModel().getSelectedItems().size()!=0) {
           
    
        paiementService cmt = new paiementService();
      paiement pmt =(paiement)table.getSelectionModel().getSelectedItem();  
        cmt.Deletepaiement(pmt.getId());
         try {
        javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("/gui/participants.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    
    }
            
            
        
        
      
    
        
        
       else{
           
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("aucun élément 'a ètè séléctionné");
        alert.showAndWait();

           
        
       }
    }
    

    @FXML
    private void back(ActionEvent event) {
         try {
        javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("/gui/homeAdm.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    
    }

    @FXML
    private void recherche(KeyEvent event) {
    }
    
}
