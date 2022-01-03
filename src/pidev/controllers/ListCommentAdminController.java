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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import pidev.entities.Comment;
import pidev.entities.Event;
import pidev.entities.User;
import pidev.services.CommentService;
import pidev.services.EventService;
import pidev.services.UserService;


/**
 * FXML Controller class
 *
 * @author Manel
 */
public class ListCommentAdminController implements Initializable {
     private ObservableList<Comment> data;

        @FXML

    
    private AnchorPane holderpane;
    @FXML
     private TableView<Comment> tableview;
    @FXML
    private TableColumn<Comment, Integer> id_event=new TableColumn<>();
    @FXML
    private TableColumn<Comment, Integer> id_user=new TableColumn<>();
    @FXML
    private TableColumn<Comment, Integer> commentaire=new TableColumn<>();
 

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
             Comment u = new Comment();
        id_event.setCellValueFactory(new PropertyValueFactory<Comment, Integer>("event_name"));
        id_user.setCellValueFactory(new PropertyValueFactory<Comment, Integer>("user_name"));
        commentaire.setCellValueFactory(new PropertyValueFactory<Comment, Integer>("commentaire"));
     
        buildData();
        // TODO
    }    

    
    private void buildData() {
         CommentService su = CommentService.getInstance();
        data=FXCollections.observableArrayList();
                      UserService usr = UserService.getInstance(); 
                      EventService evservice =EventService.getInstance();

        try {
             ResultSet rs = su.afficherComments();
            while(rs.next()){
                                User user = usr.afficheruserparid(rs.getInt(3));
                              Event event = evservice.affichereventparid(rs.getInt(4));
                Comment cmt = new Comment();
                cmt.setId(rs.getInt(1));
                cmt.setEvent_id(rs.getInt(4));
                cmt.setUser_id(rs.getInt(3));
                cmt.setEvent_name(event.getNomevet());
                cmt.setUser_name(user.getUsername());

                cmt.setCommentaire(rs.getString(2));
             
                System.out.println(cmt.getEvent_id());
               
              data.add(cmt);
            }
            
            tableview.setItems(data);

        } catch (SQLException ex) {
            Logger.getLogger(ListCommentAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    
    
    
    
  
    
}
