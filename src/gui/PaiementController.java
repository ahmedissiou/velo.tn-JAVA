/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.stripe.model.Charge;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import pidev.entities.PaiementStripe;
import java.util.Date;
import java.util.Properties;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import pidev.controllers.LoginController;
import java.io.IOException;
import java.sql.SQLException;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import tray.notification.TrayNotification;
import tray.notification.NotificationType;
import tray.animations.AnimationType;  


/**
 * FXML Controller class
 *
 * @author abder
 */
public class PaiementController implements Initializable {

    @FXML
    private JFXTextField email;
    @FXML
    private JFXTextField numeroCarte;
    @FXML
    private JFXTextField MoisValidite;
    @FXML
    private JFXTextField AnneeValidite;
    @FXML
    private JFXPasswordField ccvTextField;
    @FXML
    private JFXButton btnValider;
    @FXML
    private JFXButton back;
    @FXML
    private JFXTextField montant;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void validerFunction(ActionEvent event) throws IOException {
        
        
            int mois = Integer.parseInt(MoisValidite.getText());
        int annee = Integer.parseInt(AnneeValidite.getText());
        
        
        
        
        com.stripe.model.Token token = PaiementStripe.getToken("pk_test_AuAMdXwE57NnBcd4Xld65Ez4", numeroCarte.getText(), mois, annee, ccvTextField.getText(), email.getText());
          if (validateInputs()) {
            if(token !=null){
                
                 Charge ch= PaiementStripe.ChargePayement("rk_test_oGfrFNOjpnRPklUVzjelPHgf", "usd", "tok_visa",400 ,"sk_test_yIqEVjLUzA1vwKhr1PjhnS9I", numeroCarte.getText(), mois, annee, ccvTextField.getText(), email.getText());
                 
                 
                 
                 
            String tit = "Paiement réussi";
            String message = "Votre paiement a été traité avec succès";
            NotificationType notification = NotificationType.SUCCESS;
    
            TrayNotification tray = new TrayNotification(tit, message, notification);          
            tray.setAnimationType(AnimationType.POPUP);
            tray.showAndDismiss(javafx.util.Duration.seconds(10));
       
     
       {
       
      Alert alert = new Alert(AlertType.INFORMATION);
alert.setTitle("Félicitations");
alert.setHeaderText(null);
alert.setContentText("vous avez participé au cours !");

alert.showAndWait();
  sendEmail();
      // email.getScene().setRoot(root);
      
    
            }
            }}}
    private boolean validateInputs() {
     if (ccvTextField.getText().length() == 0 || numeroCarte.getText().length() == 0 ) {

           Alert alert = new Alert(Alert.AlertType.ERROR);
          alert.setTitle("Information Dialog");
           alert.setHeaderText(null);
           alert.setContentText("tous les champs doivent etre remplis");
           alert.showAndWait();
          return false;
      } 
       
            

         else
         if 
                  (numeroCarte.getText().length() != 16) {
               Alert alert1 = new Alert(Alert.AlertType.WARNING);
            alert1.setTitle("Erreur");
            alert1.setContentText("Le numéro de carte doit comporter 16 chiffres");
            alert1.setHeaderText(null);
            alert1.show();
            return false;
          }
       
          else
         if 
                  (ccvTextField.getText().length() != 3) {
               Alert alert1 = new Alert(Alert.AlertType.WARNING);
            alert1.setTitle("Erreur");
            alert1.setContentText("Le CVC doit comporter 3 chiffres");
            alert1.setHeaderText(null);
            alert1.show();
            return false;
          }
          else if ((!email.getText().contains("@")) || (!email.getText().contains("."))) {
                   Alert alert1 = new Alert(Alert.AlertType.WARNING);
            alert1.setTitle("Erreur");
            alert1.setContentText(" Email invalide");
            alert1.setHeaderText(null);
            alert1.show();
            return false;
          }
       
        

        return true;
    }
    
            

         public void sendEmail(){
     try{
            String host ="smtp.gmail.com" ;
            String user = "velotncours@gmail.com";
            String pass = "unique509428";
            String to = email.getText();
            String from = "velotncours@gmail.com";
            String subject = "Velo.tn - Paiement effectué";
            boolean sessionDebug = false;

            Properties props = System.getProperties();

            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.required", "true");

            java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
            Session mailSession = Session.getDefaultInstance(props, null);
            mailSession.setDebug(sessionDebug);
            Message msg = new MimeMessage(mailSession);
            msg.setFrom(new InternetAddress(from));
            InternetAddress[] address = {new InternetAddress(to)};
           
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject(subject); msg.setSentDate(new Date());
                
            
            MimeBodyPart messageBodyPart = new MimeBodyPart();
        Multipart multipart = new MimeMultipart();
        
       //  String file = "/home/berrahal/NetBeansProjects/" + "facture.pdf";
        //String fileName = "commande.pdf";
    messageBodyPart = new MimeBodyPart();   
    //DataSource source = new FileDataSource(file);      
    //messageBodyPart.setDataHandler(new DataHandler(source));
    //messageBodyPart.setFileName(fileName);
    messageBodyPart.setText("Bonjour "+" "+", vous avez participé au cours ! Soyez au rendez vous ! Vos cordonnées :       Votre numéro  de carte : **************60 cvc **6 ");
    multipart.addBodyPart(messageBodyPart);
    
        
     msg.setContent(multipart);

       
        
        
        
           Transport transport=mailSession.getTransport("smtp");
           transport.connect(host, user, pass);
           transport.sendMessage(msg, msg.getAllRecipients());
           transport.close();
                   System.out.println("Email envoyé");

        }catch(Exception ex)
        {
            System.out.println(ex);
        }
}
     
      @FXML
    private void back(ActionEvent event) throws IOException {
         
         try {
        javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("homeClt.fxml"));
        
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
   
}

