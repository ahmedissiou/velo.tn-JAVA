/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import pidev.entities.fos_user;
import pidev.services.fos_userService;

/**
 * FXML Controller class
 *
 * @author ouertani
 */
public class RegistrationController implements Initializable {

    @FXML
    private VBox VBoxMdp;
    @FXML
    private TextField txtPseudo;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private PasswordField txtCfPassword;
    @FXML
    private Button back;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtnumcarte;
    @FXML
    private AnchorPane mainp;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Back(ActionEvent event) throws IOException {
        mainp.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("/gui/Login.fxml"));
        mainp.getChildren().add(parent);
        mainp.toFront();
     
    }

    @FXML
    private void ajouterClient(ActionEvent event) throws SQLException {
        
        fos_userService sr = new fos_userService();

        if (validateInputs()) {
            fos_user fos_user = new fos_user(txtPseudo.getText(), txtEmail.getText(), txtPassword.getText(),Integer.parseInt(txtnumcarte.getText()));
            fos_userService us = new fos_userService();

            sr.ajouterClient(fos_user);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);
            alert.setContentText("Operation effectuée avec succée !");
            alert.show();
            alert.setOnHidden(e -> {
                if (alert.getResult() == ButtonType.YES) {
                    System.out.println("good");
                     mainp.getChildren().clear();
                     Parent parent;
                    try {
                        parent = FXMLLoader.load(getClass().getResource("/gui/Login.fxml"));
                        mainp.getChildren().add(parent);
                        mainp.toFront();
                        sendEmail(fos_user);
                                } 
                    catch (IOException ex) {
                        Logger.getLogger(RegistrationController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                 
                } else {
                    System.out.println("canceled");
                }
            });
            
        }
    }
        
        private boolean validateInputs() {
        if ((txtPseudo.getText().isEmpty()) || (txtEmail.getText().isEmpty()) || 
                (txtPassword.getText().isEmpty())) 
        {
            Alert alert1 = new Alert(Alert.AlertType.WARNING);
            alert1.setTitle("Erreur");
            alert1.setContentText("Veillez remplir tout les champs");
            alert1.setHeaderText(null);
            alert1.show();
            return false;
        } else if (!(txtCfPassword.getText().equals(txtPassword.getText()))) {
            Alert alert2 = new Alert(Alert.AlertType.WARNING);
            alert2.setTitle("Erreur");
            alert2.setContentText("Veillez vérifier votre mot de passe");
            alert2.setHeaderText(null);
            alert2.show();
            return false;
        } else if (!(validate(txtEmail.getText()))) {
            Alert alert2 = new Alert(Alert.AlertType.WARNING);
            alert2.setTitle("Erreur");
            alert2.setContentText("Veillez vérifier votre email");
            alert2.setHeaderText(null);
            alert2.show();
            return false;
        } else if ((txtnumcarte.getText().trim().length() > 8) || ((txtnumcarte.getText().trim().length() < 8))) {
            Alert alert2 = new Alert(Alert.AlertType.WARNING);
            alert2.setTitle("Erreur");
            alert2.setContentText("Veillez vérifier votre numéro de votre carte boncaire");
            alert2.setHeaderText(null);
            alert2.show();
            return false;
        }
        return true;
    }
        
        
        public static final Pattern VALID_EMAIL_ADDRESS_REGEX
            = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static boolean validate(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }
    
        public void sendEmail(fos_user compte) {
                try {
            String host = "smtp.gmail.com";
            String user = "aymen.ouertani@esprit.tn";
            String pass = "193JMT2693";
            String to = compte.getEmail();
            String from = "aymen.ouertani@esprit.tn";
            String subject = " Bienvenu!";
            boolean sessionDebug = false;

            Properties props = System.getProperties();

            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.required", "true");
            props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

            java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
            Session mailSession = Session.getDefaultInstance(props, null);
            mailSession.setDebug(sessionDebug);
            Message msg = new MimeMessage(mailSession);
            msg.setFrom(new InternetAddress(from));
            InternetAddress[] address = {new InternetAddress(to)};

            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject(subject);
            //msg.setSentDate(new Date());

            MimeBodyPart messageBodyPart = new MimeBodyPart();
            Multipart multipart = new MimeMultipart();

            messageBodyPart = new MimeBodyPart();

            messageBodyPart.setText("Bonjour " + compte.getUsername() + ",Bienvenu dans notre application Velo.Tn");
            multipart.addBodyPart(messageBodyPart);

            msg.setContent(multipart);

            Transport transport = mailSession.getTransport("smtp");
            transport.connect(host, user, pass);
            transport.sendMessage(msg, msg.getAllRecipients());
            transport.close();
            System.out.println("Email envoyé");

        } catch (Exception ex) {
            System.out.println(ex);
        }
            }
    

}
    
