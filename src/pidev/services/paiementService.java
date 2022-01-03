package pidev.services;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pidev.entities.cours;
import pidev.entities.paiement;
import pidev.pidev;
import pidev.util.Connexion;


/**
 *
 * @author ahmed
 */
public class paiementService {
Connection connexion;
 public paiementService() {
       connexion=Connexion.getInstance().getConnexion();
    }

       

    
 public void Addpaiement(paiement p) {

        String requete = "INSERT INTO paiement(nom,prénom,age,cour_id,user_id) VALUES (?,?,?,?,?)";

        try {

            PreparedStatement st = connexion.prepareStatement(requete);
            st.setString(1, p.getNom());
            st.setString(2, p.getPrénom());
            st.setInt(3, p.getAge());
            st.setInt(4, p.getCour_id());
            st.setInt(5, p.getUser_id());
            st.executeUpdate();
            System.out.println("Felicitation");

        } catch (SQLException ex) {
            Logger.getLogger(paiementService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
 public void increment(int cour_id) throws SQLException{
        PreparedStatement preparedStatement = null ;
        String query="update cours set nb=nb+1 WHERE cour_id=?";
        Statement pstm = connexion.createStatement();
       pstm.executeUpdate(query);
    }
    

    public void Editpaiement(paiement p, int id) throws SQLException {

        String req = "UPDATE paiement SET nom=?,prénom=?,age=?";
        PreparedStatement st = connexion.prepareStatement(req);
             st.setString(1, p.getNom());
            st.setString(2, p.getPrénom());
            st.setInt(3, p.getAge());
           
     
        st.setInt(10, id);
        st.executeUpdate();

    }
    public void eval(paiement p) {
   
     
        Statement st;
       
        try {
             st = connexion.createStatement();
             st.executeUpdate("UPDATE paiement SET niveau='"+p.getNote()+
                        "' WHERE id= "+p.getId());
                    }   
        catch (SQLException ex) {
            Logger.getLogger(coursService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

  

    public paiement FindById(int id) throws SQLException {
        paiement p  = new paiement();

        String req = "SELECT * FROM `paiement` WHERE id = ?";
        PreparedStatement pre = connexion.prepareStatement(req);
        pre.setInt(1, id);
        ResultSet rs = pre.executeQuery();
        while (rs.next()) {

            p.setId(rs.getInt("id"));
            p.setNom(rs.getString("nom"));
            p.setPrénom(rs.getString("prénom"));
            p.setAge(rs.getInt("age"));
            
           
          
      

        }

        return p;
    }
    public paiement getpaiementbycoursId(Integer id) {
        paiement p = null;
        String req = "select * paiement where cour_id=?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connexion.prepareStatement(req);
            preparedStatement.setInt(1, id);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                p = new paiement();
             
                p.setNom(rs.getString(3));
                p.setPrénom(rs.getString(4));
                p.setAge(rs.getInt(5));
              
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return p;

    }
      public void Deletepaiement(int id) throws SQLException {
        String req = "DELETE FROM paiement WHERE id=?";
        PreparedStatement pre = connexion.prepareStatement(req);
        pre.setInt(1, id);
        pre.executeUpdate();

    }
    

   

       
    public ObservableList<paiement> FindAllpaiement() throws SQLException {
         

        ObservableList<paiement> paiement = FXCollections.observableArrayList();
            String req = "SELECT * FROM paiement";
            Statement st = connexion.createStatement();
            ResultSet result = st.executeQuery(req);
            while (result.next()) {
                paiement p  =new paiement(
              
              
               
                result.getString("nom"),
               
                result.getString("prénom"),
                result.getInt("age"),
               
                result.getInt("id"));
                
               
                
              
                paiement.add(p);

            }


        return paiement;
          }


 public ObservableList<paiement> FindAllpay() throws SQLException {
         

        ObservableList<paiement> paiement = FXCollections.observableArrayList();
            String req = "select p.id, p.nom,p.prénom,p.age,c.niveau,c.prixcours,c.id from paiement p join cours c on p.cour_id=c.id";
            Statement st = connexion.createStatement();
            ResultSet result = st.executeQuery(req);
            while (result.next()) {
                paiement p  =new paiement(
              
              
               
                result.getString("nom"),
               
                result.getString("prénom"),
                result.getInt("age"),
                        result.getInt("id"),
               
               
                 result.getInt("niveau"),
                result.getInt("prixcours"));
               
                
               
                
              
                paiement.add(p);

            }


        return paiement;
 }

public double total() throws SQLException{
            String query="select sum(c.prixcours) as total from paiement p join cours c on p.cour_id=c.id" ;
            ResultSet rs =connexion.createStatement().executeQuery(query);
            while(rs.next()){
            int totalamount=rs.getInt("total");
                return totalamount;
                
            }
       return 0;
    }
}