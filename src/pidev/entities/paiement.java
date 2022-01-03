/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author DELL
 */
public class paiement{
       private String nom;
     private String prénom;
     private Integer age;
    
    private Integer id;
    
  
  
   
    private Integer note;
    public Integer user_id;
    
    private Integer cour_id;
    private Integer niveau;
    private Integer prixcours;
    private String lieu_cours;
    private String date_cours;

   
      public paiement() {
    }

    public paiement(String nom, String prénom, Integer age) {
        this.nom = nom;
        this.prénom = prénom;
        this.age = age;
    }

    public paiement(String nom, String prénom, Integer age, Integer cour_id) {
        this.nom = nom;
        this.prénom = prénom;
        this.age = age;
        this.cour_id = cour_id;
    }

   

    public paiement(String nom, String prénom, Integer age, Integer cour_id,Integer user_id) {
        this.nom = nom;
        this.prénom = prénom;
        this.age = age;
        this.user_id = user_id;
        this.cour_id = cour_id;
    }

    public paiement(String nom, String prénom, Integer age, Integer id, Integer niveau, Integer prixcours) {
        this.nom = nom;
        this.prénom = prénom;
        this.age = age;
        this.id = id;
        this.niveau = niveau;
        this.prixcours = prixcours;
    }


      
    
     
  

   
    
 
    public paiement(Integer id, Integer num_carte, Integer cvc, String type_car, String nom, String prénom, Integer age, Integer note, Integer user_id, Integer cour_id) {
        this.id = id;
     
        this.nom = nom;
        this.prénom = prénom;
        this.age = age;
        this.note = note;
        this.user_id = user_id;
        this.cour_id = cour_id;
    }

    public paiement(Integer id, String nom, String prénom, Integer age, Integer user_id, Integer cour_id) {
        this.id = id;
        this.nom = nom;
        this.prénom = prénom;
        this.age = age;
        this.user_id = user_id;
        this.cour_id = cour_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
      
         
    
  
    

  

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

       public String getPrénom() {
        return prénom;
    }

    public void setPrénom(String prénom) {
        this.prénom = prénom;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getNote() {
        return note;
    }

    public void setNote(Integer note) {
        this.note = note;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getCour_id() {
        return cour_id;
    }

    public void setCour_id(Integer cour_id) {
        this.cour_id = cour_id;
    }

    public Integer getNiveau() {
        return niveau;
    }

    public void setNiveau(Integer niveau) {
        this.niveau = niveau;
    }

    public Integer getPrixcours() {
        return prixcours;
    }

    public void setPrixcours(Integer prixcours) {
        this.prixcours = prixcours;
    }

    public String getLieu_cours() {
        return lieu_cours;
    }

    public void setLieu_cours(String lieu_cours) {
        this.lieu_cours = lieu_cours;
    }

    public String getDate_cours() {
        return date_cours;
    }

    public void setDate_cours(String date_cours) {
        this.date_cours = date_cours;
    }
    

    @Override
    public String toString() {
        return "paiement{" + "nom=" + nom + ", prénom=" + prénom + ", age=" + age + ", id=" + id + ", note=" + note + ", user_id=" + user_id + ", cour_id=" + cour_id + ", niveau=" + niveau + ", prixcours=" + prixcours + ", lieu_cours=" + lieu_cours + ", date_cours=" + date_cours + '}';
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
        final paiement other = (paiement) obj;
       
        if (this.age != other.age) {
            return false;
        }
        if (this.note != other.note) {
            return false;
        }
           if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
              if (!Objects.equals(this.prénom, other.nom)) {
            return false;
        }
              
        
       
       if (!Objects.equals(this.cour_id, other.cour_id)) {
            return false;
        }
       
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
      
        return true;
    }
    
    
    
    
    
}
