/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intreprisemanagementsystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author DOSHE
 */
public class Numero_Trans {
      private static Connection con=null;
private static PreparedStatement pst=null;
private static ResultSet rs= null;
    // String rolls;
     public static String rolls;
    
   public static void numero_Trans(){
     
         Date dates= new Date();
        // con=JavaDbConnect.dbConnect();
         SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM");
         String addDate = dateFormats.format(dates);
         try{
            String sql="SELECT NUM FROM ohada_trans ORDER BY NUM DESC LIMIT 1";
             pst=con.prepareStatement(sql);
             rs=pst.executeQuery();
             if(rs.next()){
                 String rl=rs.getString("NUM");
                 int ln=rl.length();
                 String stxt=rl.substring(0,12);
                 String snum=rl.substring(12,ln);
                 int n = Integer.parseInt(snum);
                 n++;
                 snum=Integer.toString(n);
                 rolls=stxt+snum;
                 
                 
                 
             }else{
                rolls= "No: "+addDate+"/1";
                
             }
              // compte.setText(rolls);
             
         }catch(NumberFormatException | SQLException e){
             JOptionPane.showMessageDialog(null, e); 
             
         }
       //  return null;
     }
    
    
}
