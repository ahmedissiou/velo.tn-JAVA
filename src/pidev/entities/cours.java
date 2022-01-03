package pidev.entities;

import java.sql.Date;
import java.util.Collection;
import java.util.Objects;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

/**
 *
 * @author Ahmed
 */
public class cours {
    
    private Integer id;
    
    private Integer niveau;
   
    private String date_cours;
   
    private String lieu_cours;
 
    private Integer prixcours;
   
    private String nom_image;
   
    private Integer signale;
 
    private Integer nb;
     private Integer user_id;
   
  

    public cours() {
    }
      public cours( Integer niveau, String date_cours, String lieu_cours, Integer prixcours) {
        
        this.niveau = niveau;
        this.date_cours = date_cours;
        this.lieu_cours = lieu_cours;
        this.prixcours = prixcours;
        this.nom_image = nom_image;
       
       
       
    }
     
        public cours( Integer id,Integer niveau, String date_cours, String lieu_cours, Integer prixcours) {
        this.id = id;
        this.niveau = niveau;
        this.date_cours = date_cours;
        this.lieu_cours = lieu_cours;
        this.prixcours = prixcours;
          ;}

    public cours(Integer nb) {
        this.nb = nb;
    }

    public cours(Integer id, Integer nb) {
        this.id = id;
        this.nb = nb;
    }

    public cours(Integer id, Integer niveau, String date_cours, String lieu_cours, Integer prixcours, Integer nb) {
        this.id = id;
        this.niveau = niveau;
        this.date_cours = date_cours;
        this.lieu_cours = lieu_cours;
        this.prixcours = prixcours;
        this.nb = nb;
    }

    public cours(String nom_image) {
        this.nom_image = nom_image;
    }
    
       
       
    
         public cours( Integer id,Integer niveau, String date_cours, String lieu_cours, Integer prixcours,String nom_image) {
        this.id = id;
        this.niveau = niveau;
        this.date_cours = date_cours;
        this.lieu_cours = lieu_cours;
        this.prixcours = prixcours;
         this.nom_image= nom_image ;}
       public cours( Integer niveau, String date_cours, String lieu_cours, Integer prixcours , String nom_image) {
        
        this.niveau = niveau;
        this.date_cours = date_cours;
        this.lieu_cours = lieu_cours;
        this.prixcours = prixcours;
        this.nom_image = nom_image;
       
       
    }
    
    
    public cours( Integer id,Integer niveau, String date_cours, String lieu_cours, Integer prixcours, String nom_image,Integer signale, Integer nb,Integer user_id) {
        this.id=id;
        this.niveau = niveau;
        this.date_cours = date_cours;
        this.lieu_cours = lieu_cours;
        this.prixcours = prixcours;
        this.nom_image = nom_image;
        this.signale = signale;
        this.nb = nb;
        this.user_id=user_id;
       
    }

    public cours(Integer id, Integer niveau, String date_cours, String lieu_cours, Integer prixcours, String nom_image, Integer nb) {
        this.id = id;
        this.niveau = niveau;
        this.date_cours = date_cours;
        this.lieu_cours = lieu_cours;
        this.prixcours = prixcours;
        this.nom_image = nom_image;
        this.nb = nb;
    }

   

   

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNiveau() {
        return niveau;
    }

    public void setNiveau(Integer niveau) {
        this.niveau = niveau;
    }

    public String getDate_cours() {
        return date_cours;
    }

    public void setDate_cours(String date_cours) {
        this.date_cours = date_cours;
    }

   

    public String getLieu_cours() {
        return lieu_cours;
    }

    public void setLieu_cours(String lieu_cours) {
        this.lieu_cours = lieu_cours;
    }

    public Integer getPrixcours() {
        return prixcours;
    }

    public void setPrixcours(Integer prixcours) {
        this.prixcours = prixcours;
    }

    public String getNom_image() {
        return nom_image;
    }

    public void setNom_image(String nom_image) {
        this.nom_image = nom_image;
    }


  
    

    public Integer getSignale() {
        return signale;
    }

    public void setSignale(Integer signale) {
        this.signale = signale;
    }

    public Integer getNb() {
        return nb;
    }

    public void setNb(Integer nb) {
        this.nb = nb;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "cours{" + "id=" + id + ", niveau=" + niveau + ", date_cours=" + date_cours + ", lieu_cours=" + lieu_cours + ", prixcours=" + prixcours + ", nom_image=" + nom_image + ", signale=" + signale + ", nb=" + nb + ", user_id=" + user_id + '}';
    }
    
 


    @Override
    public int hashCode() {
        int hash = 8;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final cours other = (cours) obj;
        if (this.prixcours != other.prixcours) {
            return false;
        }
        if (this.signale != other.signale) {
            return false;
        }
        if (this.nb != other.nb) {
            return false;
        }
         if (this.niveau != other.niveau) {
            return false;
        }
        if (!Objects.equals(this.lieu_cours, other.lieu_cours)) {
            return false;
        }
        if (!Objects.equals(this.date_cours, other.date_cours)) {
            return false;
        }
        if (!Objects.equals(this.nom_image, other.nom_image)) {
            return false;
        }
       
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
      
        return true;
    }
    
    
    
    
    
}
