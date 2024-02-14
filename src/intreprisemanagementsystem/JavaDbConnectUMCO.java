/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intreprisemanagementsystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author DOSHE
 */
public class JavaDbConnectUMCO {
    
    private static Connection con;
   //static final String DB_URL="jdbc:mysql://remotemysql.com/Phc7qJMBCY";
   //static final String DB_DRV="com.mysql.jdbc.Driver";
   //static final String DB_USER="Phc7qJMBCY";
   //static final String DB_PASS="X7b8hQtjfb";
   // String url="jdbc:sqlite:kavira.db";
    String a=null;
   // static final String DB_URL="jdbc:mysql://localhost:3306/ entreprise_management_system";
   static final String DB_URL="jdbc:sqlite:umco.db";
 // String DB_DRV="org.sqlite.JDBC";
   static final String DB_USER="";
   static final String DB_PASS="";
    
    
      public static Connection dbConnect(){
       
         try{
             
              Class.forName("org.sqlite.JDBC");
             con = DriverManager.getConnection(DB_URL);//.autoReconnect=true;
      
      return con;
            
          //con.close();
         }catch(ClassNotFoundException | SQLException ex ){
             JOptionPane.showMessageDialog(null, ex);
         } 
         return null;
    }
    
    
}
