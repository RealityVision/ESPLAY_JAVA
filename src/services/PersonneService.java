/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Personne;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javaapplication8.MaConnexion;

/**
 *
 * @author khaled
 */
public class PersonneService {
    Connection mc;
 PreparedStatement ste;
    public PersonneService() {
        mc=MaConnexion.instconn().getcnx();
    }
    public void ajouterPersonne(Personne P){
        String sql = "insert into personne(nom,prenom) values(?,?) "; 
        try {
             ste= mc.prepareStatement(sql);
            ste.setString(1, P.getNom());
            ste.setString(2, P.getPrenom());
          
            ste.executeUpdate();
            System.out.println("personne ajouté");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public List<Personne> afficherPersonne(){
        List<Personne> personnes = new ArrayList<>();
        String sql = "select * from personne";
        try {

            ste =mc.prepareStatement(sql);
            ResultSet rs=ste.executeQuery();
            
            while(rs.next()){
            Personne p = new Personne();
            
            p.setId(rs.getInt("id"));
            p.setNom(rs.getString(2));
            p.setPrenom(rs.getString("prenom"));
            
            personnes.add(p);
            
            }
        } catch (SQLException ex) {
           System.out.println(ex.getMessage());
        }
        
         return personnes;
    }
    
    
    
}
