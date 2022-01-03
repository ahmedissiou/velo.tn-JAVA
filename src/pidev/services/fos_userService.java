/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.services;

import pidev.util.BCrypt;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Alert;
import pidev.entities.fos_user;
import pidev.util.Connexion;

/**
 *
 * @author ouertani
 */
public class fos_userService {
    
    Connection con = Connexion.getInstance().getConnexion();
    private Statement stmt;

    public fos_userService() {
        try {
            if (con != null) {
                stmt = con.createStatement();
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
        public void changePassword(String password, String email) throws SQLException
    {
        String req = "UPDATE fos_user SET password = ?  WHERE email = ?";
        PreparedStatement ste = con.prepareStatement(req);
        ste.setString(1,password);
        
        
        ste.setString(2, email);
        ste.executeUpdate();
    }
        
    
    public fos_user getUserById(int id) throws SQLException {
        fos_user u = null;
        String req = "SELECT * FROM fos_user WHERE id = ?";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setInt(1, id);
        ResultSet rs = pre.executeQuery();
        while (rs.next()) {
            u = new fos_user();

            u.setId(rs.getInt("id"));
            u.setUsername(rs.getString("username"));
            u.setEmail(rs.getString("email"));
            u.setRoles(rs.getString("roles"));
            u.setPassword(rs.getString("password"));
            u.setEnabled(rs.getInt("enabled"));
//            u.setNumcarte(rs.getInt("numcarte"));
            u.setSolde(rs.getInt("solde"));

            System.out.println("Utilisateur trouvé !");
        }
        return u;
    }
        public fos_user searchByPseudoPass(String pseudo, String password) throws SQLException {
            fos_user u = null;
        String req = "SELECT * FROM fos_user WHERE ((username = ? OR email = ?))";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setString(1, pseudo);
        pre.setString(2, pseudo);
        //pre.setString(3, password);
        ResultSet rs = pre.executeQuery();
        while (rs.next()) {
            u = new fos_user();
            u = fos_userService.this.getUserById(rs.getInt("id"));
            if (BCrypt.checkpw(u.getPassword(), BCrypt.hashpw(password, BCrypt.gensalt())) == true) {
                return u;

            }
        }
        return u;
    }
        public fos_user getUserByEmail(String email) throws SQLException {
        fos_user u = null;
        String req = "SELECT * FROM fos_user WHERE email = ?";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setString(1, email);
        ResultSet rs = pre.executeQuery();
        while (rs.next()) {
            u = new fos_user();
            
            u.setId(rs.getInt("id"));
            u.setUsername(rs.getString("username"));
            u.setEmail(rs.getString("email"));
            u.setRoles(rs.getString("roles"));
            u.setPassword(rs.getString("password"));
            u.setEnabled(rs.getInt("enabled"));

            System.out.println("Utilisateur trouvé !");
            System.out.println(u);
  
            
        }
        return u;
    }
            
        public void ajouterClient(fos_user fos_user) throws SQLException {
        String req = "INSERT INTO fos_user(username,username_canonical,email,email_canonical,password,roles,enabled,numcarte,solde) values(?,?,?,?,?,'ROLE_USER',1,?,1000)";
        PreparedStatement pre = con.prepareStatement(req);
        //   pre.setLong(1, user.getId());
        String password = BCrypt.hashpw(fos_user.getPassword(), BCrypt.gensalt());

        pre.setString(1, fos_user.getUsername());
        pre.setString(2, fos_user.getUsername());
        pre.setString(3, fos_user.getEmail());
        pre.setString(4, fos_user.getEmail());
        pre.setString(5, password);
        pre.setInt(6, fos_user.getNumcarte());

        pre.executeUpdate();
    }
        
        public void modifierClient(fos_user client, int id) throws SQLException {
        String req = "UPDATE fos_user SET username=?,email = ? ,password = ?, numcarte=? WHERE id = ?";
        PreparedStatement ste = con.prepareStatement(req);
        ste.setString(1,client.getUsername());
        ste.setString(2, client.getEmail());
        ste.setString(3, client.getPassword());
        ste.setInt(4,client.getNumcarte());
        ste.setInt(5, id);
        ste.executeUpdate();
        Alert alertSucc = new Alert(Alert.AlertType.CONFIRMATION);
        alertSucc.setTitle("Succés");
        alertSucc.setContentText("Opération effectuer avec succés");
        alertSucc.setHeaderText(null);
        alertSucc.show();
}
        public List<fos_user> getAllUsers() throws SQLException {
        List<fos_user> myList = new ArrayList<fos_user>();

          
        String req = "SELECT * FROM fos_user";
        PreparedStatement pre = con.prepareStatement(req);
      
        ResultSet rs = pre.executeQuery();
        while (rs.next()) {
        fos_user u = new fos_user();

            u.setId(rs.getInt("id"));
            u.setUsername(rs.getString("username"));
            u.setEmail(rs.getString("email"));
            u.setRoles(rs.getString("roles"));
            u.setPassword(rs.getString("password"));
            u.setEnabled(rs.getInt("enabled"));
            u.setNumcarte(rs.getInt("numcarte"));

            myList.add(u);

            
        }
        return myList;
    }
        public void UserSetEnable(int id) throws SQLException {
        
        
        String req = "UPDATE fos_user SET enabled = ? WHERE id = ?";
        PreparedStatement st = con.prepareStatement(req);
        
        st.setInt(1,1);
        st.setInt(2,id);
 
        st.executeUpdate();

    }
       public void UserSetDisable(int id) throws SQLException {
        
        
        String req = "UPDATE fos_user SET enabled = ? WHERE id = ?";
        PreparedStatement st = con.prepareStatement(req);
        
        st.setInt(1,0);
        st.setInt(2,id);
 
        st.executeUpdate();

    }
        
       public void UserSetadmin(int id) throws SQLException {
        
        
        String req = "UPDATE fos_user SET roles = ? WHERE id = ?";
        PreparedStatement st = con.prepareStatement(req);
        
        st.setString(1,"ROLE_ADMIN");
        st.setInt(2,id);
 
        st.executeUpdate();

    }
       
    public void adminSetuser(int id) throws SQLException {
        
        
        String req = "UPDATE fos_user SET roles = ? WHERE id = ?";
        PreparedStatement st = con.prepareStatement(req);
        
        st.setString(1,"ROLE_USER");
        st.setInt(2,id);
 
        st.executeUpdate();

    }
}
