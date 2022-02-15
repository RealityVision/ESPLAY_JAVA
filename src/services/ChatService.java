/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Chat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javaapplication8.MaConnexion;

/**
 *
 * @author khaled
 */
public class ChatService {
    Connection cn;
    PreparedStatement ps;

    public ChatService() {
        cn=MaConnexion.instconn().getcnx();
    }
   
    public void SendMessage(Chat c){
        String sql = "INSERT INTO `chat` (`id_user`, `message`, `picture`, `username`) VALUES (?,?,?,?);";
        try {
            ps=cn.prepareStatement(sql);
            
            ps.setInt(1, c.getId_user());
            ps.setString(2,c.getMessage());
            ps.setString(3, c.getPicture());
            ps.setString(4, c.getUsername());
            ps.executeUpdate();
            
            System.out.println("message envoyé avec succée");
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    
        }
    
    public void DeleteMessage(int id_message){
         String sql = "DELETE FROM `chat` WHERE `chat`.`id_message` = ?;";
        try {
            ps=cn.prepareStatement(sql);
            ps.setInt(1, id_message );
          
            ps.executeUpdate();
            
            System.out.println("message supprimé avec succée");
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public List<Chat> ReadChat(){
             List<Chat> chat = new ArrayList<>();
            String sql = "SELECT * FROM `chat`;";
            try{
                ps =cn.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            
            while(rs.next()){
            Chat c = new Chat();
                
             c.setId_message(rs.getInt("id_message"));
             c.setId_user(rs.getInt("id_user"));
             c.setUsername(rs.getString("username"));
             c.setMessage(rs.getString("message"));
             c.setDate_message(rs.getTimestamp("date_message"));
             c.setPicture(rs.getString("picture"));
             
            chat.add(c);
            
            }
                
            } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
            
            
        return chat;
    }
    
    
}