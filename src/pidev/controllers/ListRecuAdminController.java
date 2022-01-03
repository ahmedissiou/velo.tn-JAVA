/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.controllers;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import pidev.entities.Event;
import pidev.entities.Recu;
import pidev.entities.User;
import pidev.services.EventService;
import pidev.services.RecuService;
import pidev.services.UserService;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class ListRecuAdminController implements Initializable {
 private ObservableList<Recu> data;
    @FXML
    
    private AnchorPane holderPane;
    @FXML
    private TableView<Recu> tableview;
    @FXML
    private TableColumn<Recu, Integer> idevent=new TableColumn<>();
    @FXML
    private TableColumn<Recu, Integer> prixrecu=new TableColumn<>();
     @FXML
    private TableColumn<Recu, Integer> iduser=new TableColumn<>();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         Recu u = new Recu();
     idevent.setCellValueFactory(new PropertyValueFactory<Recu, Integer>("event_name"));
        prixrecu.setCellValueFactory(new PropertyValueFactory<Recu, Integer>("prixrecu"));
        iduser.setCellValueFactory(new PropertyValueFactory<Recu, Integer>("user_name"));
     
        buildData();
        // TODO
    }    
    
     private void buildData() {
         RecuService su = RecuService.getInstance();
        data=FXCollections.observableArrayList();
                             UserService usr = UserService.getInstance(); 
                      EventService evservice =EventService.getInstance();
        try {
             ResultSet rs = su.afficherAllRecu();
            while(rs.next()){
                                        User user = usr.afficheruserparid(rs.getInt(3));
                              Event event = evservice.affichereventparid(rs.getInt(4));
                Recu rec = new Recu();
                rec.setPrixrecu(rs.getInt(2));
                  rec.setEvent_name(event.getNomevet());
                rec.setUser_name(user.getUsername());
                rec.setUser_id(rs.getInt(3));
                rec.setEvent_id(rs.getInt(4));
              
              data.add(rec);
            }
            
            tableview.setItems(data);

        } catch (SQLException ex) {
            Logger.getLogger(ListerEventAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    
}
