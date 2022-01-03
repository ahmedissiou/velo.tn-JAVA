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
import static javax.swing.UIManager.getString;
import pidev.entities.cours;
import pidev.util.Connexion;


/**
 *
 * @author ahmed
 */
public class coursService {

 
Connection connexion;
public coursService() {
       connexion=Connexion.getInstance().getConnexion();
    }

       

    
    public void AddCours(cours c) {

        String requete = "INSERT INTO cours(niveau,date_cours,lieu_cours,prixcours,nom_image,nb) VALUES (?,?,?,?,?,0)";

        try {

            PreparedStatement st = connexion.prepareStatement(requete);
            
            st.setInt(1, c.getNiveau());
            st.setString(2, c.getDate_cours());
            st.setString(3, c.getLieu_cours());
            st.setInt(4, c.getPrixcours());
            st.setString(5, c.getNom_image());
          

            st.executeUpdate();
            System.out.println("cours ajoutée");

        } catch (SQLException ex) {
            Logger.getLogger(coursService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    

    public void EditCours(cours c) {
   
     
        Statement st;
       
        try {
             st = connexion.createStatement();
             st.executeUpdate("UPDATE cours SET niveau='"+c.getNiveau()+"',date_cours='"+c.getDate_cours()+"',lieu_cours='"+c.getLieu_cours()+"',prixcours='"+c.getPrixcours()+
                        "' WHERE id= "+c.getId());
                    }   
        catch (SQLException ex) {
            Logger.getLogger(coursService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
  public void increment_nb(int id) throws SQLException{
        PreparedStatement preparedStatement = null ;
        String query="update cours c   set c.nb =c.nb+1 WHERE id="+id;
        Statement pstm = connexion.createStatement();
       pstm.executeUpdate(query);
    }

    public void DeleteCours(int id) throws SQLException {
        String req = "DELETE FROM cours WHERE id=?";
        PreparedStatement pre = connexion.prepareStatement(req);
        pre.setInt(1, id);
        pre.executeUpdate();

    }
     public cours affichecoursparid(int id) {
         cours  evt= new cours();
            try {
            
           
            PreparedStatement pt = connexion.prepareStatement("select c.niveau ,x.user_id  from cours c  join fos_user  join paiement x WHERE c.id = ?");
            pt.setInt(1, id);
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {
                evt.setId(rs.getInt(1));
                evt.setNiveau(rs.getInt(2));
                evt.setDate_cours(rs.getString(3));
                evt.setNom_image(rs.getString(4)); 
                evt.setLieu_cours(rs.getString(5));
                evt.setPrixcours(rs.getInt(6));
                evt.setNb(rs.getInt(8));
                evt.setSignale(rs.getInt(9));
                evt.setUser_id(rs.getInt(10));
                
                
                
              
           
                 

            }
        } catch (SQLException ex) {
            System.out.println("erreur " + ex.getMessage());
        }
        
        return evt;
    }
   
  

    public cours FindById(int id) throws SQLException {
        cours c = new cours();

        String req = "SELECT * FROM `cours` WHERE id = ?";
        PreparedStatement pre = connexion.prepareStatement(req);
        pre.setInt(1, id);
        ResultSet rs = pre.executeQuery();
        while (rs.next()) {
             c.setNiveau(rs.getInt("id"));
            
            c.setNiveau(rs.getInt("niveau"));
            c.setDate_cours(rs.getString("date_cours"));
            c.setLieu_cours(rs.getString("lieu_cours"));
            c.setPrixcours(rs.getInt("prixcours"));
            c.setNom_image(rs.getString("nom_image"));
          
           
          
      

        }

        return c;
    }
    
    public ObservableList<cours>recherche(String lieu_cours) {
        
        String requete = "SELECT * FROM  cours where lieu_cours = '"+lieu_cours+"' " ;
        PreparedStatement pst;
        ObservableList<cours> cours= FXCollections.observableArrayList();
            

        try {
            pst = connexion.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while(rs.next())
            {
                
            int id=rs.getInt(1);
               String date_cours =rs.getString("date_cours");
              Integer prixcours =rs.getInt("prixcours");
              Integer niveau =rs.getInt("niveau");
               cours c =new cours (niveau ,date_cours,lieu_cours,prixcours);
           cours.add(c);
            }
            
        
        } catch (SQLException ex) {
            Logger.getLogger(coursService.class.getName()).log(Level.SEVERE, null, ex);
        }
       return cours;
          
    }

     public ObservableList<cours> FindAllcours() throws SQLException {
         

        ObservableList<cours> cours = FXCollections.observableArrayList();

       

            String req = "SELECT * FROM cours ORDER BY date_cours DESC";
            Statement st = connexion.createStatement();
            ResultSet result = st.executeQuery(req);
            while (result.next()) {
                cours c =new cours(
                result.getInt("id"),
                result.getInt("niveau"),
                result.getString("date_cours"),
                result.getString("lieu_cours"),
                result.getInt("prixcours"),
                result.getString("nom_image")
               );
                
              
                cours.add(c);

            }


        return cours;
        }
     
     public ResultSet afficher() {
                ResultSet rs = null;
        try {
            PreparedStatement pt = connexion.prepareStatement("SELECT * FROM cours ORDER BY date_cours DESC");
            rs = pt.executeQuery();
    
        } catch (SQLException ex) {
            System.out.println("erreur " + ex.getMessage());
        }
            return rs;
    }

public static coursService su;
    
     public static coursService getInstance() {
        if(su == null )
            su = new coursService();
        return su;
            
    }
}

   
        
     

       
  



