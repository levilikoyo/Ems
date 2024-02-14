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
public class JavaDbConnect {
    
private static Connection con;
static final String DB_URL="jdbc:mysql://localhost:3306/ems";
//static final String DB_URL="jdbc:mysql://192.168.100.25/ems";
static final String DB_DRV="com.mysql.jdbc.Driver";
static final String DB_USER="root";
static final String DB_PASS="";
//static final String DB_PASS="Doshe@1234";
  
      public static Connection dbConnect(){
          
         try{
             con = DriverManager.getConnection(DB_URL,DB_USER,DB_PASS);//.autoReconnect=true;
     
      return con;
              }catch(SQLException ex ){
             JOptionPane.showMessageDialog(null, ex);
         } 
         return null;

//
// try{
//        Class.forName("com.mysql.jdbc.Driver");
//        String unicode="useSSL=false&autoReconnect=true&useUnicode=yes&characterEncoding=UTF-8";
//        return DriverManager.getConnection("jdbc:mysql://localhost:3306/ems?"+unicode, DB_USER,DB_PASS);
//    }catch(Exception ex){
//        System.out.println(ex.getMessage());
//        System.out.println("couldn't connect!");
//        throw new RuntimeException(ex);
//    }
    }
    
      
      
    
}
