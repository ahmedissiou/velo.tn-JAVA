/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.Services;

import pidev.entities.Evaluations;
import pidev.util.Connexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pidev.entities.cours;

/**
 *
 * @author fedi
 */
public class EvaluationService {

    Connection cx = Connexion.getInstance().getConnexion();

    public void ajouter(Evaluations  ev) {
        String req = "INSERT INTO evaluations (note, commentaire, cour_id, user_id) VALUES ( '"
                + ev.getNote() + "', '" + ev.getCommentaire() + "','"+ev.getCour_id()+ "','" + ev.getUser_id()+ "')";
        Statement st;
        try {
            st = cx.createStatement();
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(EvaluationService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void supprimer(int id){
        String req="delete from evaluations where Id= '"+id+"'";
        Statement st;
         try {
            st = cx.createStatement();
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(EvaluationService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void maj(Evaluations e){
        String req= " UPDATE evaluations SET (note, commentaire)='"+ e.getNote() + "', '" + e.getCommentaire() + "' where id= '"+e.getId()+"' " ;
        Statement st;
        try {
             st = cx.createStatement();
             st.executeUpdate(req);
                    }   
        catch (SQLException ex) {
            Logger.getLogger(EvaluationService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
      public void rechercheEvaluation(int id) throws SQLException {
        String req = "select * FROM evaluations where id= '"+id+"'";
        Statement pstm = cx.createStatement();
       ResultSet rst = pstm.executeQuery(req);
       rst.last();
       int nbrRow=rst.getRow();
       if (nbrRow != 0 )
       {System.out.println("Evaluation trouver");}
       else{ System.out.println("Evaluation non trouver");
    }
    }
    
    public ObservableList<Evaluations> afficher()
             
     {  
        ObservableList<Evaluations> mylist=FXCollections.observableArrayList();
        String req= " SELECT ev.id,ev.note,ev.commentaire,e.lieu_cours FROM evaluations ev INNER JOIN cours e ON ev.cour_id=e.id ";
        Statement st;
        try {
            st=cx.createStatement();
            ResultSet resultat=st.executeQuery(req);
            while(resultat.next())
            {    Evaluations c = new Evaluations(resultat.getInt("note"), resultat.getString("commentaire"), resultat.getString("lieu_cours"));
               /*  c.setTitre(resultat.getString(2));
                 c.setDescription(resultat.getString(3));*/
                 mylist.add(c);
                    }
            
        } catch (SQLException ex) {
            Logger.getLogger(EvaluationService.class.getName()).log(Level.SEVERE, null, ex);
        }
          return mylist;
     }
    
    public ObservableList<Evaluations> afficherUsers(){
                ObservableList<Evaluations> mylist=FXCollections.observableArrayList();
                String req="Select ev.note from evaluations ev INNER JOIN fos_user u ON ev.user_id=u.id";
                Statement st;
        try {
            st=cx.createStatement();
            ResultSet resultat=st.executeQuery(req);
            while(resultat.next())
            {    Evaluations c = new Evaluations(resultat.getDouble("note"));
               /*  c.setTitre(resultat.getString(2));
                 c.setDescription(resultat.getString(3));*/
                 mylist.add(c);
                    }
            
        } catch (SQLException ex) {
            Logger.getLogger(EvaluationService.class.getName()).log(Level.SEVERE, null, ex);
        }
          return mylist;
     }
    
public double eval(int cour_id) throws SQLException{
      
          
          
        
             PreparedStatement pt = cx.prepareStatement("select avg(note)as eval from evaluations where cour_id=?  ");
            pt.setInt(1, cour_id);
           ResultSet rs = pt.executeQuery();
            while(rs.next()){
            int notecours=rs.getInt("eval");
                return notecours;
                
            }
       return 0;
    }
          public ObservableList<Evaluations> afficherEvaluationsparidev(int id) {
        ObservableList<Evaluations> mylist=FXCollections.observableArrayList();
            try {
            
           
            PreparedStatement pt = cx.prepareStatement("SELECT ev.note,ev.commentaire,u.username FROM evaluations ev INNER JOIN fos_user u ON ev.user_id=u.id  WHERE ev.cour_id = ?");
            pt.setInt(1, id);
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {
                Evaluations c = new Evaluations(rs.getDouble("note"), rs.getString("commentaire"), rs.getString("username"));
              //  evt.setIdPro(rs.getInt(10));   

             mylist.add(c);
                    }
        } catch (SQLException ex) {
            System.out.println("erreur " + ex.getMessage());
        }
        
        return mylist;
    }
}         



//SELECT ev.id,ev.note,ev.commentaire,e.titre FROM evaluations ev INNER JOIN events e ON ev.event_id=e.id

