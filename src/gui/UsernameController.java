/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import pidev.entities.fos_user;
import pidev.services.fos_userService;
import pidev.utils.BCrypt;
import pidev.pidev;

/**
 * FXML Controller class
 *
 * @author ouertani
 */
public class UsernameController implements Initializable {

    @FXML
    private Button editButton;
    @FXML
    private TextField txtUsername;
    @FXML
    private Label txtetat;
    @FXML
    private TextField txtemail;
    @FXML
    private TextField txtmdp;
    @FXML
    private TextField txtmdp2;
    @FXML
    private TextField txtnumcarte;
    @FXML
    private AnchorPane arch;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fos_user user=new fos_user();
        fos_userService us=new fos_userService();
        try {
            user=us.getUserById(pidev.user_id);
        } catch (SQLException ex) {
            Logger.getLogger(UsernameController.class.getName()).log(Level.SEVERE, null, ex);
        }
        txtUsername.setText(user.getUsername());
        txtemail.setText(user.getEmail());
        txtnumcarte.setText(String.valueOf(user.getNumcarte()));
        
    }    

    @FXML
    private void edit(ActionEvent event) throws SQLException {
        if (validateInputs()) {
        fos_userService sr = new fos_userService();
        fos_user user=new fos_user(txtUsername.getText(), txtemail.getText(), BCrypt.hashpw(txtmdp.getText(), BCrypt.gensalt()),Integer.parseInt(txtnumcarte.getText()));
        sr.modifierClient(user,pidev.user_id);
        arch.setVisible(false);
                 
             }
                
    }
    
        private boolean validateInputs() {
        if ((txtUsername.getText().isEmpty()) || txtemail.getText().isEmpty()|| txtmdp.getText().isEmpty() ||txtmdp2.getText().isEmpty())  {
            Alert alert1 = new Alert(Alert.AlertType.WARNING);
            alert1.setTitle("Erreur");
            alert1.setContentText("Veillez remplir tout les champs");
            alert1.setHeaderText(null);
            alert1.show();
            return false;
    } 
        else if(!txtemail.getText().contains("@")){
            Alert alert2 = new Alert(Alert.AlertType.WARNING);
            alert2.setTitle("Erreur");
            alert2.setContentText("Veillez enter une address email valide");
            alert2.setHeaderText(null);
            alert2.show();
            return false;
            
    }
        else if (txtmdp.getText().trim().length() < 8) {
            Alert alert2 = new Alert(Alert.AlertType.WARNING);
            alert2.setTitle("Erreur");
            alert2.setContentText("Veillez entrer une mot de passe qui contient au moins 8 caractéres");
            alert2.setHeaderText(null);
            alert2.show();
            return false;
        }
        else if (!txtmdp.getText().equals(txtmdp2.getText())){
            Alert alert2 = new Alert(Alert.AlertType.WARNING);
            alert2.setTitle("Erreur");
            alert2.setContentText("Veillez enter la meme mot de passe");
            alert2.setHeaderText(null);
            alert2.show();
            return false;
        }
        else
            return true;
        }
}
