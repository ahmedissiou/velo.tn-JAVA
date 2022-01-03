/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.entities;

/**
 *
 * @author fedi
 */
public class Evaluations {

    private int id;
    private double note;
    private String commentaire;
    private int cour_id;
    private int user_id;
    private String lieu_cours;
    private String username;

    public Evaluations() {
    }

    public Evaluations(double note) {
        this.note = note;
    }
    
    

    public Evaluations(int id, double note, String commentaire, int cour_id, int user_id) {
        this.id = id;
        this.note = note;
        this.commentaire = commentaire;
        this.cour_id = cour_id;
        this.user_id = user_id;
    }

    public Evaluations( int cour_id, double note, String commentaire) {
        this.note = note;
        this.commentaire = commentaire;
        this.cour_id = cour_id;
    }

    public Evaluations(int cour_id,double note, String commentaire,  int user_id) {
        this.note = note;
        this.commentaire = commentaire;
        this.cour_id = cour_id;
        this.user_id = user_id;
    }

    public Evaluations(int id, double note, String commentaire, String username) {
        this.id = id;
        this.note = note;
        this.commentaire = commentaire;
        this.username = username;
    }
    
    
    
    

    public Evaluations(double note, String commentaire, String lieu_cours) {
        this.note = note;
        this.commentaire = commentaire;
        this.lieu_cours = lieu_cours;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getNote() {
        return note;
    }

    public void setNote(double note) {
        this.note = note;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public int getCour_id() {
        return cour_id;
    }

    public void setCour_id(int cour_id) {
        this.cour_id = cour_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    
     @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + this.id;
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
        final Evaluations other = (Evaluations) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    public String getLieu_cours() {
        return lieu_cours;
    }

    public void setLieu_cours(String lieu_cours) {
        this.lieu_cours = lieu_cours;
    }

    @Override
    public String toString() {
        return "Evaluations{" + "note=" + note + ", commentaire=" + commentaire + ", lieu_cours=" + lieu_cours +",username="+username + '}';
    }

   

   

   

}
