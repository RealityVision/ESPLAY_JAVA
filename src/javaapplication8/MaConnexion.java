
package javaapplication8;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MaConnexion {
    final String url="jdbc:mysql://localhost:3306/esplay";
    final String user="root";
    final String pwd="";
    
    private Connection  cnx; 
    
    public static MaConnexion MCN;
    
    private MaConnexion(){
        try {
            cnx= DriverManager.getConnection(url, user, pwd);
            System.out.println("----------connection etablie--------------");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public static MaConnexion instconn(){
        if (MCN==null)
            MCN=new MaConnexion();
        return MCN;
    }
    
public Connection getcnx(){
    return cnx;
}
}
