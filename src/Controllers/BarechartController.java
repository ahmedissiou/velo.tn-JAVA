/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

//import Utils.DataBase;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import pidev.util.Connexion;


public class BarechartController {

    @FXML
    private AnchorPane avr;
    @FXML
    private BarChart<String, Double> BarChart;
    @FXML
    private JFXButton LOAD;
    @FXML 
    private JFXButton back;
    @FXML
    private void LOAD(ActionEvent event) throws SQLException {
        String query = "SELECT nom, prixr FROM reparation ";
        XYChart.Series<String,Double> series = new XYChart.Series<>();
        try {
         Connection cx = Connexion.getInstance().getConnexion();
            ResultSet rs=cx.createStatement().executeQuery(query);
            while (rs.next()) {
             series.getData().add(new XYChart.Data<>(rs.getString(1), rs.getDouble(2)));
            }
            BarChart.getData().add(series);

        } catch (Exception e){

        }
    }
        @FXML
    private void back(ActionEvent event) throws IOException {
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
}