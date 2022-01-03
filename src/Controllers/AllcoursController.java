/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import Controllers.DetailcoursController;

import pidev.entities.cours;
import pidev.services.coursService;

/**
 * FXML Controller class
 *
 * @author Fedi
 */
public class AllcoursController implements Initializable {

    @FXML
    private AnchorPane holderPane;
    private VBox vbox2;
    @FXML
    private ImageView image2;
    @FXML
    private Label label2;
    @FXML
    private Button btn2_event;
    private VBox vbox1;
    @FXML
    private ImageView image1;
    @FXML
    private Label label1;
    @FXML
    private Button btn1_event;
    private VBox vbox3;
    @FXML
    private ImageView image3;
    @FXML
    private Label label3;
    @FXML
    private Button btn3_event;
    private VBox vbox4;
    @FXML
    private ImageView image4;
    @FXML
    private Label label4;
    @FXML
    private Button btn4_event;
    @FXML
    private ImageView courssuivant;
    @FXML
    private ImageView coursprecedent;
    private static int a, b, c, d, i, idcours1, idcours2, idcours3, idcours4;
    private ObservableList<cours> data;
    public static String value;
    @FXML
    private JFXTextField rechercheBar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        a = 0;
        b = 1;
        c = 2;
        d = 3;
        coursprecedent.setVisible(false);
        buildData();
        rechercheBar.textProperty().addListener((observable, oldValue, newValue) -> {
            value = rechercheBar.getText();
            buildData();
        });

    }

    private void buildData() {
        data = FXCollections.observableArrayList();
        coursService su = coursService.getInstance();
        btn1_event.setVisible(false);
        label1.setVisible(false);
        image1.setVisible(false);
        btn2_event.setVisible(false);
        label2.setVisible(false);
        image2.setVisible(false);
        btn3_event.setVisible(false);
        label3.setVisible(false);
        image3.setVisible(false);
        btn4_event.setVisible(false);
        label4.setVisible(false);
        image4.setVisible(false);
        i = 0;
        int j = 0;
        try {
            ResultSet rs = su.afficher();
            while (rs.next()) {

                cours cr= new cours();
              
		    cr.setId(rs.getInt(1));
            
            cr.setNiveau(rs.getInt(2));
            cr.setDate_cours(rs.getString(3));
            cr.setNom_image(rs.getString(4));
            cr.setLieu_cours(rs.getString(5));
            cr.setPrixcours(rs.getInt(6));
            

                if (value == null) {
                    data.add(cr);
                    j++;
                } ////////recherche ///////// 
                else if (cr.getLieu_cours().toLowerCase().contains(value.toLowerCase()) ) {
                    data.add(cr);
                    j++;

                }

                while (i < j) {
                    if (i == a) { 
                        btn1_event.setVisible(true);
                        label1.setVisible(true);
                        image1.setVisible(true);
                        label1.setText(data.get(i).getLieu_cours());
                        String img = "http://localhost//pifinal/pidevfinal/web/images/" + data.get(i).getNom_image();
                        idcours1 = data.get(i).getId();
                        image1.setImage(new Image(img));
                    } else if (i == b) {
                        btn2_event.setVisible(true);
                        label2.setVisible(true);
                        image2.setVisible(true);
                        label2.setText(data.get(i).getLieu_cours());
                        String img = "http://localhost//pifinal/pidevfinal/web/images/" + data.get(i).getNom_image();
                        idcours2 = data.get(i).getId();
                        image2.setImage(new Image(img));
                    } else if (i == c) {
                        btn3_event.setVisible(true);
                        label3.setVisible(true);
                        image3.setVisible(true);
                        label3.setText(data.get(i).getLieu_cours());
                        String img = "http://localhost//pifinal/pidevfinal/web/images/" + data.get(i).getNom_image();
                        idcours3 = data.get(i).getId();
                        image3.setImage(new Image(img));
                    } else if (i == d) {
                        btn4_event.setVisible(true);
                        label4.setVisible(true);
                        image4.setVisible(true);
                        label4.setText(data.get(i).getLieu_cours());
                       String img = "http://localhost//pifinal/pidevfinal/web/images/" + data.get(i).getNom_image();
                        idcours4 = data.get(i).getId();
                        image4.setImage(new Image(img));
                    }

                    i++;
                }

            }

        } catch (SQLException ex) {
            Logger.getLogger(AllcoursController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   @FXML
   private void affichage_2(ActionEvent event) throws IOException {
        DetailcoursController.id = idcours2;
        AnchorPane page1 = FXMLLoader.load(getClass().getResource("/gui/détailscours.fxml"));
        setNode(page1);
        

    }

   @FXML
    private void affichage_1(ActionEvent event) throws IOException {
       DetailcoursController.id = idcours1;
        AnchorPane page1 = FXMLLoader.load(getClass().getResource("/gui/détailscours.fxml"));
        setNode(page1);
    }

   @FXML
    private void affichage_3(ActionEvent event) throws IOException {
       DetailcoursController.id = idcours3;
        AnchorPane page1 = FXMLLoader.load(getClass().getResource("/gui/détailscours.fxml"));
        setNode(page1);
  }

   @FXML
    private void affichage_4(ActionEvent event) throws IOException {
        DetailcoursController.id = idcours4;
        AnchorPane page1 = FXMLLoader.load(getClass().getResource("/gui/détailscours.fxml"));
       setNode(page1);
   }

    @FXML
    private void suivant_event(MouseEvent event) {
        a = a + 4;
        b = b + 4;
        c = c + 4;
        d = d + 4;
        coursprecedent.setVisible(true);
        buildData();
        if (b == i || a == i || c == i || d == i) {
            courssuivant.setVisible(false);
        }
    }

    @FXML
    private void event_precedent(MouseEvent event) {
        a = a - 4;
        b = b - 4;
        c = c - 4;
        d = d - 4;
        courssuivant.setVisible(true);

        if (a == 0) {
            coursprecedent.setVisible(false);
        }
        buildData();
    }

    public void setNode(Node node) {
        holderPane.getChildren().clear();
        holderPane.getChildren().add((Node) node);

        FadeTransition ft = new FadeTransition(Duration.millis(1000));
        ft.setNode(node);
        ft.setFromValue(0.1);
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(false);
        ft.play();
    }

    /*private void ajouter(ActionEvent event) throws IOException {
        
        
                AnchorPane page1 = FXMLLoader.load(getClass().getResource("/pidev/gui/ajouterevent.fxml"));
                               setNode(page1);

        
    }*/
}
