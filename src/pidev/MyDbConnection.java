/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author asus
 */
public class MyDbConnection {
    private static MyDbConnection instance;
    private Connection connexion;
    private String url = "JDBC:mysql://localhost:3306/pivelo";
    private String user = "root";
    private String password = "";

   MyDbConnection() {
        try {
            connexion = DriverManager.getConnection(url, user, password);
            System.out.println("connexion etablie");
        } catch (SQLException ex) {
            System.out.println("Probleme de connexion");
        }
    }

    public static MyDbConnection getInstance() {
        if (MyDbConnection.instance == null) {
            MyDbConnection.instance = new MyDbConnection();
        }
        return MyDbConnection.instance;
    }

    public Connection getConnexion() {
        return this.connexion;
    }
    
}
