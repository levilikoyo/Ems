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
public class JavaDbConnect_id {
    
    private static Connection con;
   //static final String DB_URL="jdbc:mysql://remotemysql.com/Phc7qJMBCY";
   //static final String DB_DRV="com.mysql.jdbc.Driver";
   //static final String DB_USER="Phc7qJMBCY";
   //static final String DB_PASS="X7b8hQtjfb";
    
    //static final String DB_URL="jdbc:mysql://localhost:3306/ entreprhise_management_system";
   // static final String DB_URL="jdbc:mysql://emslcom:3306/emslcom_exam";
  //static final String DB_DRV="com.mysql.jdbc.Driver";
    //static final String DB_USER="emslcom_levi";
    //static final String DB_PASS="=j&N8Ko7#}@F";
    
   static final String DB_URL="jdbc:mysql://127.0.0.1/ems";
   //static final String DB_URL="jdbc:mysql://localhost:3306/UMCO";
  static final String DB_DRV="com.mysql.jdbc.Driver";
  static final String DB_USER="root";
 //static final String DB_PASS="Doshe@1234";
  static final String DB_PASS="";
    
    
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
