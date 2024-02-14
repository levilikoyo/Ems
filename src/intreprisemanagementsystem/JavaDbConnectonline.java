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


public class JavaDbConnectonline {
    
    
    
   
    
    private static Connection con;
  /*     static final String DB_URL="jdbc:mysql://50.87.176.132/emslnet_ems";
   static final String DB_DRV="com.mysql.jdbc.Driver";
   static final String DB_USER="emslnet_levilikoyo";
   static final String DB_PASS="Doshe@1234";
  */ //41.243.54.161
    //162.241.244.79
     static final String DB_URL="jdbc:mysql://162.241.244.79/ecolelej_ems_l";
    //static final String DB_URL="jdbc:mysql://41.243.54.161/ecolelej_ems_l";
   static final String DB_DRV="com.mysql.jdbc.Driver";
   static final String DB_USER="ecolelej_emsl";
   static final String DB_PASS="Doshe@0611";
 
    
//   static final String DB_URL="jdbc:mysql://localhost:3306/hand_book";
//  static final String DB_DRV="com.mysql.jdbc.Driver";
//  static final String DB_USER="root";
//  static final String DB_PASS="Doshe@1234";
    
    
      public static Connection dbConnect(){
          
         try{
             con = DriverManager.getConnection(DB_URL,DB_USER,DB_PASS);//.autoReconnect=true;
     
      return con;
              }catch(SQLException ex ){
        // }catch(ClassNotFoundException | SQLException ex ){
             JOptionPane.showMessageDialog(null, ex);
         } 
         return null;
    }
    
    
    
    
}
