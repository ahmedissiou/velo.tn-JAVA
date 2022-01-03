/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;
import static com.itextpdf.text.pdf.PdfName.AND;
import pidev.entities.cours;
import pidev.services.coursService;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import static javax.persistence.criteria.Predicate.BooleanOperator.AND;
import pidev.Services.EvaluationService;
import pidev.entities.Evaluations;
import pidev.entities.paiement;
import pidev.pidev;


/**
 * FXML Controller class
 *
 * @author user
 */
public class DetailcoursController implements Initializable {

    @FXML
    private AnchorPane holderPane;
    @FXML
    private Label nomevent;
 
    @FXML
    private Label datedebe;
    @FXML
    private ImageView image;
    @FXML
    private Label prix;
     @FXML
    private Label note;
    @FXML
    private Label nb;
    public static int  id;

    @FXML
    private JFXButton signaler;
    @FXML
    private JFXButton eval;
    @FXML
    private JFXButton btnreser;
    @FXML
    private Button Back;
    
  
      DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        coursService cs = new coursService();
         EvaluationService es = new EvaluationService();
        cours e = cs.affichecoursparid(id);
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      paiement p=new paiement();
        
      DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        coursService cs = new coursService();
         EvaluationService es = new EvaluationService();
        cours e = cs.affichecoursparid(id);
         Date d1 = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String d2= dateFormat.format(d1);
        System.out.println("nous somme le : " +d2);
          if (e.getNb()>10){
            btnreser.setVisible(false);
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Il n'ya plus de places dans ce cours");
            alert.showAndWait();
        }
            if ((pidev.user_id==p.getUser_id()) ){
                btnreser.setVisible(false);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Vous ne pouvez pas ");
                alert.showAndWait();
    }
        
        if (e.getDate_cours().compareTo(d2)<0){
            btnreser.setVisible(false);
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Vous ne pouvez pas réserver à ce courscar sa date a expirée. En cours de suppression");
            alert.showAndWait();
        }
        
        
        if (e.getDate_cours().compareTo(d2)>0){
            eval.setVisible(false);
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Vous pouvez evaluer le cours seulement si sa date a achevée");
            alert.showAndWait();
        }
        
        nomevent.setText(e.getLieu_cours());
        
     prix.setText(Integer.toString(e.getPrixcours()));
    nb.setText(Integer.toString(e.getNiveau()));
        datedebe.setText(e.getDate_cours());
        //date_fine.setValue(e.getDate_fine().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
      
        String img = "http://localhost//pifinal/pidevfinal/web/images/" + e.getNom_image();
        image.setImage(new Image(img));
        
        
        try {
            note.setText(String.valueOf(es.eval(e.getId())));
        } catch (SQLException ex) {
            Logger.getLogger(DetailcoursController.class.getName()).log(Level.SEVERE, null, ex);
        }
    

    }    
     @FXML
    private void signaler(ActionEvent event) throws IOException {
      
         ShowEvaluationsEventController.id = e.getId();
        holderPane.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("/gui/ShowEvaluations.fxml"));
        holderPane.getChildren().add(parent);
        holderPane.toFront();}
   

  @FXML
    private void btnevalua(ActionEvent event) throws IOException {
       
        NewEvaluationController.id = id;

        holderPane.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("/gui/NewEvaluation.fxml"));
        holderPane.getChildren().add(parent);
        holderPane.toFront();
    }       
        
    

    @FXML
    private void btnreser(ActionEvent event) {
           try {
           
        javafx.scene.Parent view = FXMLLoader.load(getClass().getResource("/gui/payercourse.fxml"));
        Scene sceneview = new Scene(view);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
    }
    
    }

      

    @FXML
    private void retourner(ActionEvent event) {
         try {
        javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("/gui/Allcours.fxml"));
        
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    }
    
