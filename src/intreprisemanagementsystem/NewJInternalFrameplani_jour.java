/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package intreprisemanagementsystem;

import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;


/**
 *
 * @author Dosh
 */
public class NewJInternalFrameplani_jour extends javax.swing.JInternalFrame {
DefaultTableModel mode;
    
   Connection con=null;
    PreparedStatement pst=null;
    ResultSet rs= null;
    public NewJInternalFrameplani_jour() {
        initComponents();
              con=JavaDbConnect.dbConnect();
       //  setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("icons8_Raspberry_Pi_48px_1.png")));
        jComboBox4.addItem("SELECTIONNE LE NOM DE L'EMPLOYE");
        jComboBox5.addItem("HEURS");
        jComboBox5.addItem("PLANIFIER LA JOURNEE");
        jComboBox5.addItem("AVANT MIDI");
        jComboBox5.addItem("APRES MIDI");
        jComboBox5.addItem("6H-6H30");
        jComboBox5.addItem("6H30-7H");
        jComboBox5.addItem("07H-08H");
        jComboBox5.addItem("08H-09H");
        jComboBox5.addItem("09H-10H");
        jComboBox5.addItem("10H-11H");
        jComboBox5.addItem("11H-12H");
        jComboBox5.addItem("12H-13H");
        jComboBox5.addItem("13H-14H");
        jComboBox5.addItem("14H-15H");
        jComboBox5.addItem("15H-16H");
        jComboBox5.addItem("16H-17H");
        jComboBox5.addItem("17H-18H");
        jComboBox5.addItem("18H-19H");
        jComboBox5.addItem("19H-20H");
        Call_ID_TO_BOMBOBOX2();
    }
    //CONNECTION
     public static Connection getConnection()
    {
        Connection con = null;
        try{
             con= DriverManager.getConnection("jdbc:mysql://localhost/entreprise_management_system","root","");
              // JOptionPane.showMessageDialog(null,"connected");
             return con;
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,"not connected");
           
        }
         return null;
    }
     
     public void add()
     {
         desc3.setText("6H-6H30");
         un.setText("6H30-7H");
         deux.setText("07H-08H");
         trois.setText("08H-09H");
         quatre.setText("09H-10H");
         cinq.setText("10H-11H");
         six.setText("11H-12H");
         sept.setText("13H-14H");
         huit.setText("14H-15H");
         neuf.setText("15H-16H");
         dix.setText("16H-17H");
         onze.setText("17H-18H");
         douze.setText("18H-19H");
         treize.setText("19H-20H");
         desc2.setText("Presentation, Recuperation de Fiche de Presence, Fiche de Rapport et ClackIn");
         sortie.setText("Remisse de Rapport des activites journaliere,Liste de presnce et Etats de Besoins");
     }
     
     
      public void Call_ID_TO_BOMBOBOX2()
    {
         {
        try{
            String sql="select * from employee";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                String sum=rs.getString("NAME");
                  jComboBox4.addItem(sum);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
    }
    }
       public void combobox_from_materiel()
    {
        
     String tmp =(String)jComboBox4.getSelectedItem();
     String sql="select * from employee where NAME=?";
     
        try{
         
          
            pst=con.prepareStatement(sql);
            pst.setString(1, tmp);
            rs=pst.executeQuery();
            if(rs.next()){
              
              String add1 = rs.getString("NAME");
              nom.setText(add1);
               String add2 = rs.getString("LNAME");
              pnom.setText(add2);
              
               String add7 = rs.getString("TITRE");
             poste.setText(add7);
               String add8 = rs.getString("ROLLNUM");
              roll.setText(add8);
            
          }
         
           
            
            
            
            
      }catch(Exception ex){
        JOptionPane.showMessageDialog(null,ex);  
      }
          
    }     
 
     public void Call_ID_TO_BOMBOBOX()
    {
         {
        try{
            String sql="select * from employee";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                String sum=rs.getString("NAME");
                  jComboBox4.addItem(sum);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
    }
    }
      //SAVE
   
      public void savemateriaux()
    {
        if(jComboBox5.getSelectedItem().equals("PLANIFIER LA JOURNEE")){
                    try {
         Connection con = getConnection();
        PreparedStatement pst = con.prepareStatement("INSERT INTO hebdo(HEURE,LUNDI,ROLL,NOM,POSTNOM,POST,DATES) "
        +"value(?,?,?,?,?,?,?)");
         pst.setString(2, desc2.getText());
         pst.setString(1, desc3.getText());
         pst.setString(4, nom.getText());
          pst.setString(5, pnom.getText());
          pst.setString(6, poste.getText());
         pst.setString(3, roll.getText());
          SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(jDateChooser3.getDate());
         pst.setString(7, addDate);
         

         
          pst.executeUpdate();
        
             //    JOptionPane.showMessageDialog(null,"data saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
                    
               try {
         Connection con = getConnection();
        PreparedStatement pst = con.prepareStatement("INSERT INTO hebdo(HEURE,LUNDI,ROLL,NOM,POSTNOM,POST,DATES) "
        +"value(?,?,?,?,?,?,?)");
         pst.setString(2, desc1.getText());
         pst.setString(1, un.getText());
         pst.setString(4, nom.getText());
          pst.setString(5, pnom.getText());
          pst.setString(6, poste.getText());
         pst.setString(3, roll.getText());
          SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(jDateChooser3.getDate());
         pst.setString(7, addDate);
         

         
          pst.executeUpdate();
        
             //    JOptionPane.showMessageDialog(null,"data saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
                  try {
         Connection con = getConnection();
        PreparedStatement pst = con.prepareStatement("INSERT INTO hebdo(HEURE,LUNDI,ROLL,NOM,POSTNOM,POST,DATES) "
        +"value(?,?,?,?,?,?,?)");
         pst.setString(2, desc1.getText());
         pst.setString(1, deux.getText());
         pst.setString(4, nom.getText());
          pst.setString(5, pnom.getText());
          pst.setString(6, poste.getText());
         pst.setString(3, roll.getText());
          SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(jDateChooser3.getDate());
         pst.setString(7, addDate);
         

         
          pst.executeUpdate();
        
            //     JOptionPane.showMessageDialog(null,"data saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
                     try {
         Connection con = getConnection();
        PreparedStatement pst = con.prepareStatement("INSERT INTO hebdo(HEURE,LUNDI,ROLL,NOM,POSTNOM,POST,DATES) "
        +"value(?,?,?,?,?,?,?)");
         pst.setString(2, desc1.getText());
         pst.setString(1, trois.getText());
         pst.setString(4, nom.getText());
          pst.setString(5, pnom.getText());
          pst.setString(6, poste.getText());
         pst.setString(3, roll.getText());
          SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(jDateChooser3.getDate());
         pst.setString(7, addDate);
         

         
          pst.executeUpdate();
        
              //   JOptionPane.showMessageDialog(null,"data saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
                        try {
         Connection con = getConnection();
        PreparedStatement pst = con.prepareStatement("INSERT INTO hebdo(HEURE,LUNDI,ROLL,NOM,POSTNOM,POST,DATES) "
        +"value(?,?,?,?,?,?,?)");
         pst.setString(2, desc1.getText());
         pst.setString(1, quatre.getText());
         pst.setString(4, nom.getText());
          pst.setString(5, pnom.getText());
          pst.setString(6, poste.getText());
         pst.setString(3, roll.getText());
          SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(jDateChooser3.getDate());
         pst.setString(7, addDate);
         

         
          pst.executeUpdate();
        
             //    JOptionPane.showMessageDialog(null,"data saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
                           try {
         Connection con = getConnection();
        PreparedStatement pst = con.prepareStatement("INSERT INTO hebdo(HEURE,LUNDI,ROLL,NOM,POSTNOM,POST,DATES) "
        +"value(?,?,?,?,?,?,?)");
         pst.setString(2, desc1.getText());
         pst.setString(1, cinq.getText());
         pst.setString(4, nom.getText());
          pst.setString(5, pnom.getText());
          pst.setString(6, poste.getText());
         pst.setString(3, roll.getText());
          SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(jDateChooser3.getDate());
         pst.setString(7, addDate);
         

         
          pst.executeUpdate();
        
               //  JOptionPane.showMessageDialog(null,"data saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
                              try {
         Connection con = getConnection();
        PreparedStatement pst = con.prepareStatement("INSERT INTO hebdo(HEURE,LUNDI,ROLL,NOM,POSTNOM,POST,DATES) "
        +"value(?,?,?,?,?,?,?)");
         pst.setString(2, desc1.getText());
         pst.setString(1, six.getText());
         pst.setString(4, nom.getText());
          pst.setString(5, pnom.getText());
          pst.setString(6, poste.getText());
         pst.setString(3, roll.getText());
          SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(jDateChooser3.getDate());
         pst.setString(7, addDate);
         

         
          pst.executeUpdate();
        
             //    JOptionPane.showMessageDialog(null,"data saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
               try {
         Connection con = getConnection();
        PreparedStatement pst = con.prepareStatement("INSERT INTO hebdo(HEURE,LUNDI,ROLL,NOM,POSTNOM,POST,DATES) "
        +"value(?,?,?,?,?,?,?)");
         pst.setString(2, desc1.getText());
         pst.setString(1, sept.getText());
         pst.setString(4, nom.getText());
          pst.setString(5, pnom.getText());
          pst.setString(6, poste.getText());
         pst.setString(3, roll.getText());
          SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(jDateChooser3.getDate());
         pst.setString(7, addDate);
         

         
          pst.executeUpdate();
        
               //  JOptionPane.showMessageDialog(null,"data saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
                  try {
          Connection con = getConnection();
        PreparedStatement pst = con.prepareStatement("INSERT INTO hebdo(HEURE,LUNDI,ROLL,NOM,POSTNOM,POST,DATES) "
        +"value(?,?,?,?,?,?,?)");
         pst.setString(2, desc1.getText());
         pst.setString(1, huit.getText());
         pst.setString(4, nom.getText());
          pst.setString(5, pnom.getText());
          pst.setString(6, poste.getText());
         pst.setString(3, roll.getText());
          SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(jDateChooser3.getDate());
         pst.setString(7, addDate);
         

         
          pst.executeUpdate();
        
                // JOptionPane.showMessageDialog(null,"data saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
                     try {
          Connection con = getConnection();
        PreparedStatement pst = con.prepareStatement("INSERT INTO hebdo(HEURE,LUNDI,ROLL,NOM,POSTNOM,POST,DATES) "
        +"value(?,?,?,?,?,?,?)");
         pst.setString(2, desc1.getText());
         pst.setString(1, neuf.getText());
         pst.setString(4, nom.getText());
          pst.setString(5, pnom.getText());
          pst.setString(6, poste.getText());
         pst.setString(3, roll.getText());
          SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(jDateChooser3.getDate());
         pst.setString(7, addDate);
         

         
          pst.executeUpdate();
        
           //      JOptionPane.showMessageDialog(null,"data saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
                        try {
         Connection con = getConnection();
        PreparedStatement pst = con.prepareStatement("INSERT INTO hebdo(HEURE,LUNDI,ROLL,NOM,POSTNOM,POST,DATES) "
        +"value(?,?,?,?,?,?,?)");
         pst.setString(2, desc1.getText());
         pst.setString(1, dix.getText());
         pst.setString(4, nom.getText());
          pst.setString(5, pnom.getText());
          pst.setString(6, poste.getText());
         pst.setString(3, roll.getText());
          SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(jDateChooser3.getDate());
         pst.setString(7, addDate);
         

         
          pst.executeUpdate();
        
            //     JOptionPane.showMessageDialog(null,"data saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
                           try {
          Connection con = getConnection();
        PreparedStatement pst = con.prepareStatement("INSERT INTO hebdo(HEURE,LUNDI,ROLL,NOM,POSTNOM,POST,DATES) "
        +"value(?,?,?,?,?,?,?)");
         pst.setString(2, desc1.getText());
         pst.setString(1, onze.getText());
         pst.setString(4, nom.getText());
          pst.setString(5, pnom.getText());
          pst.setString(6, poste.getText());
         pst.setString(3, roll.getText());
          SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(jDateChooser3.getDate());
         pst.setString(7, addDate);
         

         
          pst.executeUpdate();
        
             //    JOptionPane.showMessageDialog(null,"data saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
                              try {
         Connection con = getConnection();
        PreparedStatement pst = con.prepareStatement("INSERT INTO hebdo(HEURE,LUNDI,ROLL,NOM,POSTNOM,POST,DATES) "
        +"value(?,?,?,?,?,?,?)");
         pst.setString(2, desc1.getText());
         pst.setString(1, douze.getText());
         pst.setString(4, nom.getText());
          pst.setString(5, pnom.getText());
          pst.setString(6, poste.getText());
         pst.setString(3, roll.getText());
          SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(jDateChooser3.getDate());
         pst.setString(7, addDate);
         

         
          pst.executeUpdate();
        
              //   JOptionPane.showMessageDialog(null,"data saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
         
                                                            try {
          Connection con = getConnection();
        PreparedStatement pst = con.prepareStatement("INSERT INTO hebdo(HEURE,LUNDI,ROLL,NOM,POSTNOM,POST,DATES) "
        +"value(?,?,?,?,?,?,?)");
         pst.setString(2, desc1.getText());
         pst.setString(1, treize.getText());
         pst.setString(4, nom.getText());
          pst.setString(5, pnom.getText());
          pst.setString(6, poste.getText());
         pst.setString(3, roll.getText());
          SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(jDateChooser3.getDate());
         pst.setString(7, addDate);
         

         
          pst.executeUpdate();
        
               //  JOptionPane.showMessageDialog(null,"data saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
        }
         else if(jComboBox5.getSelectedItem().equals("AVANT MIDI")){
              
          
          try{
           String sql = "UPDATE  hebdo SET LUNDI =?  WHERE DATES=? AND HEURE=?  AND ROLL=?";
        
         pst = con.prepareStatement(sql);
          pst.setString(1,descs.getText());
        pst.setString(3,un.getText());
         pst.setString(4,roll.getText());
         SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
         String addDate1 = dateFormat1.format(jDateChooser3.getDate());
         pst.setString(2, addDate1);
         pst.executeUpdate();
         //JOptionPane.showMessageDialog(null,"updaded");    
     }catch(SQLException | HeadlessException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }
             try{
          
               String sql = "UPDATE  hebdo SET LUNDI =?  WHERE DATES=? AND HEURE=?  AND ROLL=?";
        
         pst = con.prepareStatement(sql);
          pst.setString(1,descs.getText());
        pst.setString(3,deux.getText());
         pst.setString(4,roll.getText());
         SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
         String addDate1 = dateFormat1.format(jDateChooser3.getDate());
         pst.setString(2, addDate1);
         pst.executeUpdate();
         //JOptionPane.showMessageDialog(null,"updaded");    
     }catch(SQLException | HeadlessException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }
           try{
            String sql = "UPDATE  hebdo SET LUNDI =?  WHERE DATES=? AND HEURE=?  AND ROLL=?";
        
         pst = con.prepareStatement(sql);
          pst.setString(1,descs.getText());
        pst.setString(3,trois.getText());
         pst.setString(4,roll.getText());
         SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
         String addDate1 = dateFormat1.format(jDateChooser3.getDate());
         pst.setString(2, addDate1);
         pst.executeUpdate();
         //JOptionPane.showMessageDialog(null,"updaded");    
     }catch(SQLException | HeadlessException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }
              try{
            String sql = "UPDATE  hebdo SET LUNDI =?  WHERE DATES=? AND HEURE=?  AND ROLL=?";
        
         pst = con.prepareStatement(sql);
          pst.setString(1,descs.getText());
        pst.setString(3,quatre.getText());
         pst.setString(4,roll.getText());
         SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
         String addDate1 = dateFormat1.format(jDateChooser3.getDate());
         pst.setString(2, addDate1);
         pst.executeUpdate();
         //JOptionPane.showMessageDialog(null,"updaded");    
     }catch(SQLException | HeadlessException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }
                 try{
            String sql = "UPDATE  hebdo SET LUNDI =?  WHERE DATES=? AND HEURE=?  AND ROLL=?";
        
         pst = con.prepareStatement(sql);
          pst.setString(1,descs.getText());
        pst.setString(3,cinq.getText());
         pst.setString(4,roll.getText());
         SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
         String addDate1 = dateFormat1.format(jDateChooser3.getDate());
         pst.setString(2, addDate1);
         pst.executeUpdate();
         //JOptionPane.showMessageDialog(null,"updaded");    
     }catch(SQLException | HeadlessException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }
                    try{
          
         String sql = "UPDATE  hebdo SET LUNDI =?  WHERE DATES=? AND HEURE=?  AND ROLL=?";
        
         pst = con.prepareStatement(sql);
          pst.setString(1,descs.getText());
        pst.setString(3,six.getText());
         pst.setString(4,roll.getText());
         SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
         String addDate1 = dateFormat1.format(jDateChooser3.getDate());
         pst.setString(2, addDate1);
         pst.executeUpdate();
         //JOptionPane.showMessageDialog(null,"updaded");    
     }catch(SQLException | HeadlessException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }
      }else if(jComboBox5.getSelectedItem().equals("APRES MIDI")){
      

             try{
         String sql = "UPDATE  hebdo SET LUNDI =?  WHERE DATES=? AND HEURE=?  AND ROLL=?";
        
         pst = con.prepareStatement(sql);
          pst.setString(1,descs.getText());
        pst.setString(3,sept.getText());
         pst.setString(4,roll.getText());
         SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
         String addDate1 = dateFormat1.format(jDateChooser3.getDate());
         pst.setString(2, addDate1);
         pst.executeUpdate();
         //JOptionPane.showMessageDialog(null,"updaded");    
     }catch(SQLException | HeadlessException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }
             try{
         String sql = "UPDATE  hebdo SET LUNDI =?  WHERE DATES=? AND HEURE=?  AND ROLL=?";
        
         pst = con.prepareStatement(sql);
          pst.setString(1,descs.getText());
        pst.setString(3,huit.getText());
         pst.setString(4,roll.getText());
         SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
         String addDate1 = dateFormat1.format(jDateChooser3.getDate());
         pst.setString(2, addDate1);
         pst.executeUpdate();
         //JOptionPane.showMessageDialog(null,"updaded");    
     }catch(SQLException | HeadlessException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }
              try{
         String sql = "UPDATE  hebdo SET LUNDI =?  WHERE DATES=? AND HEURE=?  AND ROLL=?";
        
         pst = con.prepareStatement(sql);
          pst.setString(1,descs.getText());
        pst.setString(3,neuf.getText());
         pst.setString(4,roll.getText());
         SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
         String addDate1 = dateFormat1.format(jDateChooser3.getDate());
         pst.setString(2, addDate1);
         pst.executeUpdate();
         //JOptionPane.showMessageDialog(null,"updaded");    
     }catch(SQLException | HeadlessException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }
                 try{
         String sql = "UPDATE  hebdo SET LUNDI =?  WHERE DATES=? AND HEURE=?  AND ROLL=?";
        
         pst = con.prepareStatement(sql);
          pst.setString(1,descs.getText());
        pst.setString(3,dix.getText());
         pst.setString(4,roll.getText());
         SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
         String addDate1 = dateFormat1.format(jDateChooser3.getDate());
         pst.setString(2, addDate1);
         pst.executeUpdate();
         //JOptionPane.showMessageDialog(null,"updaded");    
     }catch(SQLException | HeadlessException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }
                         try{
          
            //  String tmp =(String)jComboBox2.getSelectedItem();
              //UPDATE  hebdo SET LUNDI= "supervission clinic" WHERE HEURE="10H-11H" AND ROLL="0001/IT"
         String sql = "UPDATE  hebdo SET LUNDI =?  WHERE DATES=? AND HEURE=?  AND ROLL=?";
        
         pst = con.prepareStatement(sql);
          pst.setString(1,sortie.getText());
        pst.setString(3,onze.getText());
         pst.setString(4,roll.getText());
         SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
         String addDate1 = dateFormat1.format(jDateChooser3.getDate());
         pst.setString(2, addDate1);
         pst.executeUpdate();
         //JOptionPane.showMessageDialog(null,"updaded");    
     }catch(SQLException | HeadlessException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }
          
      } 
    
        
        else{
         try{
             // String tmp =(String)jComboBox2.getSelectedItem();
              //UPDATE  hebdo SET LUNDI= "supervission clinic" WHERE HEURE="10H-11H" AND ROLL="0001/IT"
        String sql = "UPDATE  hebdo SET LUNDI =?  WHERE DATES=? AND HEURE=?  AND ROLL=?";
        
         pst = con.prepareStatement(sql);
          pst.setString(1,descs.getText());
        String value=jComboBox5.getSelectedItem().toString();
         pst.setString(3, value); 
         pst.setString(4,roll.getText());
          SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
         String addDate1 = dateFormat1.format(jDateChooser3.getDate());
         pst.setString(2, addDate1);
         pst.executeUpdate();
         //JOptionPane.showMessageDialog(null,"updaded");    
     }catch(SQLException | HeadlessException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }
    } 
    
    } 
      
          // DELETE
    public void delete()
    {
         try{
        String sql = "DELETE FROM hebdo WHERE DATES=?";
        
         pst = con.prepareStatement(sql);
          SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
         String addDate1 = dateFormat1.format(jDateChooser3.getDate());
         pst.setString(1, addDate1);
         pst.executeUpdate();

     }catch(SQLException | HeadlessException ex ){
         JOptionPane.showMessageDialog(null,ex);
     } 
    }
      
      public void update(){
         
}
      
      
//SEARCH AND ADDREADER
     
      public void ReadData(String sql)
    {
            Statement st;
            try{
                
                st=con.createStatement();
                ResultSet rs;
                rs=st.executeQuery(sql);
                if(rs.first()){
                    String Data[]=new String[7];
                    do{
                        for(int i=0;i<7;i++){
                            Data[i]=rs.getString(i+1);
                        }
                    
                    mode.addRow(Data);
                }while(rs.next());
            }
                rs.close();
            }catch(Exception ex){
                
            }
    }
 public void AddModel(){
        mode=new DefaultTableModel();
        mode.addColumn("HEURE");    
        mode.addColumn("LUNDI");
        mode.addColumn("ROLL");
        mode.addColumn("NOM");
        mode.addColumn("PNOM");
        mode.addColumn("POST");
        mode.addColumn("DATE");
        
        jTable1.setModel(mode);
        
          TableColumn col0=jTable1.getColumnModel().getColumn(0);
        TableColumn col1=jTable1.getColumnModel().getColumn(1);
        TableColumn col2=jTable1.getColumnModel().getColumn(2);
        TableColumn col3=jTable1.getColumnModel().getColumn(3);
        TableColumn col4=jTable1.getColumnModel().getColumn(4);
        TableColumn col5=jTable1.getColumnModel().getColumn(5);
        TableColumn col6=jTable1.getColumnModel().getColumn(6);
        
       
       col0.setPreferredWidth(10);
       col1.setPreferredWidth(300);
       col2.setPreferredWidth(20);
       col3.setPreferredWidth(20);
       col4.setPreferredWidth(20);
       col5.setPreferredWidth(20);
       col6.setPreferredWidth(20);
       
        
    }
   public void remove(){
          while(jTable1.getRowCount()>0){
              mode.removeRow(0);
          }
      }
              
             public void search()
             {
                  String st=roll.getText().trim();
    remove();
    ReadData("select * from hebdo where ROLL like '%"+st+"%'");
   // ReadData("select * from journal where PROJET like '%"+st+"%'");
     
             }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jComboBox4 = new javax.swing.JComboBox<>();
        nom = new javax.swing.JTextField();
        pnom = new javax.swing.JTextField();
        poste = new javax.swing.JTextField();
        jComboBox5 = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jDateChooser3 = new com.toedter.calendar.JDateChooser();
        roll = new javax.swing.JTextField();
        un = new javax.swing.JTextField();
        deux = new javax.swing.JTextField();
        trois = new javax.swing.JTextField();
        quatre = new javax.swing.JTextField();
        cinq = new javax.swing.JTextField();
        six = new javax.swing.JTextField();
        onze = new javax.swing.JTextField();
        dix = new javax.swing.JTextField();
        neuf = new javax.swing.JTextField();
        huit = new javax.swing.JTextField();
        sept = new javax.swing.JTextField();
        douze = new javax.swing.JTextField();
        treize = new javax.swing.JTextField();
        desc2 = new javax.swing.JTextField();
        jScrollPane5 = new javax.swing.JScrollPane();
        descs = new javax.swing.JEditorPane();
        desc1 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        desc3 = new javax.swing.JTextField();
        sortie = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();

        setClosable(true);
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameOpened(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(204, 255, 204));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jComboBox4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jComboBox4.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                jComboBox4PopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        nom.setEditable(false);
        nom.setBackground(new java.awt.Color(204, 204, 255));
        nom.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        pnom.setEditable(false);
        pnom.setBackground(new java.awt.Color(204, 204, 255));
        pnom.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        poste.setEditable(false);
        poste.setBackground(new java.awt.Color(204, 204, 255));
        poste.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jComboBox5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jComboBox5.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                jComboBox5PopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        jComboBox5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox5ActionPerformed(evt);
            }
        });

        jTable1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable1);

        jButton1.setBackground(new java.awt.Color(255, 51, 51));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton1.setText("DELETE");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton4.setText("PLANNIFICATION");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Nom de l'employe");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Post_Nom");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Poste");

        jDateChooser3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        roll.setEditable(false);
        roll.setBackground(new java.awt.Color(204, 204, 255));
        roll.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        roll.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                rollKeyReleased(evt);
            }
        });

        un.setEditable(false);
        un.setFont(new java.awt.Font("Tahoma", 0, 5)); // NOI18N

        deux.setEditable(false);
        deux.setFont(new java.awt.Font("Tahoma", 0, 5)); // NOI18N

        trois.setEditable(false);
        trois.setFont(new java.awt.Font("Tahoma", 0, 5)); // NOI18N
        trois.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                troisActionPerformed(evt);
            }
        });

        quatre.setEditable(false);
        quatre.setFont(new java.awt.Font("Tahoma", 0, 5)); // NOI18N

        cinq.setEditable(false);
        cinq.setFont(new java.awt.Font("Tahoma", 0, 5)); // NOI18N

        six.setEditable(false);
        six.setFont(new java.awt.Font("Tahoma", 0, 5)); // NOI18N

        onze.setEditable(false);
        onze.setFont(new java.awt.Font("Tahoma", 0, 5)); // NOI18N

        dix.setEditable(false);
        dix.setFont(new java.awt.Font("Tahoma", 0, 5)); // NOI18N

        neuf.setEditable(false);
        neuf.setFont(new java.awt.Font("Tahoma", 0, 5)); // NOI18N

        huit.setEditable(false);
        huit.setFont(new java.awt.Font("Tahoma", 0, 5)); // NOI18N
        huit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                huitActionPerformed(evt);
            }
        });

        sept.setEditable(false);
        sept.setFont(new java.awt.Font("Tahoma", 0, 5)); // NOI18N

        douze.setEditable(false);
        douze.setFont(new java.awt.Font("Tahoma", 0, 5)); // NOI18N

        treize.setEditable(false);
        treize.setFont(new java.awt.Font("Tahoma", 0, 5)); // NOI18N

        desc2.setEditable(false);
        desc2.setBackground(new java.awt.Color(204, 255, 204));
        desc2.setForeground(new java.awt.Color(204, 255, 204));
        desc2.setBorder(null);
        desc2.setCaretColor(new java.awt.Color(204, 255, 204));

        jScrollPane5.setViewportView(descs);

        desc3.setEditable(false);
        desc3.setBackground(new java.awt.Color(204, 255, 204));
        desc3.setForeground(new java.awt.Color(204, 255, 204));
        desc3.setBorder(null);
        desc3.setCaretColor(new java.awt.Color(204, 255, 204));

        sortie.setEditable(false);
        sortie.setBackground(new java.awt.Color(204, 255, 204));
        sortie.setForeground(new java.awt.Color(204, 255, 204));
        sortie.setBorder(null);
        sortie.setCaretColor(new java.awt.Color(204, 255, 204));

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 92, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField3)
                    .addComponent(desc1)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBox4, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(desc3, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(sortie, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(219, 219, 219))
                    .addComponent(jComboBox5, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jDateChooser3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(un, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(deux, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(trois, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(quatre, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cinq, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(six, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(sept, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(huit, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(neuf, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dix, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(onze, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(douze, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(2, 2, 2)
                                .addComponent(treize, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addGap(31, 31, 31)
                                        .addComponent(desc2, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(roll, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(nom, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(pnom, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(poste, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 868, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(nom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)
                            .addComponent(roll, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(pnom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(poste, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3)
                                    .addComponent(desc2, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(desc3, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(sortie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jDateChooser3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(un, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(deux, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(trois, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(quatre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cinq, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(six, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sept, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(huit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(neuf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dix, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(onze, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(douze, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(treize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(desc1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane2))
                .addContainerGap())
        );

        jTextField1.setEditable(false);
        jTextField1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jTextField1.setText("                                          PLANNIFICATION DE TACHE JOURNALIERE DU PERSONNEL");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jTextField1))
                .addGap(2, 2, 2))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox4PopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_jComboBox4PopupMenuWillBecomeInvisible
        combobox_from_materiel();
        search();// TODO add your handling code here:
    }//GEN-LAST:event_jComboBox4PopupMenuWillBecomeInvisible

    private void jComboBox5PopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_jComboBox5PopupMenuWillBecomeInvisible
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox5PopupMenuWillBecomeInvisible

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        delete();
        search();// TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try{
            String report ="C:\\Users\\Doshe\\Documents\\NetBeansProjects\\meproba\\src\\meproba\\palmarese.jrxml";
            JasperReport jrs =JasperCompileManager.compileReport(report);
            JasperPrint jps=JasperFillManager.fillReport(jrs,null,con);
            JasperViewer.viewReport(jps,false);

            JasperViewer m= new JasperViewer(jps);
            m.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        }catch(JRException ex){
            JOptionPane.showMessageDialog(null, ex);
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        //
        add();
        savemateriaux();
        jComboBox5.setSelectedItem("HEURS");
        search();// TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void rollKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_rollKeyReleased
        search();        // TODO add your handling code here:
    }//GEN-LAST:event_rollKeyReleased

    private void troisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_troisActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_troisActionPerformed

    private void huitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_huitActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_huitActionPerformed

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
AddModel(); 
ReadData("select * from hebdo");        // TODO add your handling code here:
    }//GEN-LAST:event_formInternalFrameOpened

    private void jComboBox5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox5ActionPerformed
//savemateriaux();        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox5ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField cinq;
    private javax.swing.JTextField desc1;
    private javax.swing.JTextField desc2;
    private javax.swing.JTextField desc3;
    private javax.swing.JEditorPane descs;
    private javax.swing.JTextField deux;
    private javax.swing.JTextField dix;
    private javax.swing.JTextField douze;
    private javax.swing.JTextField huit;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JComboBox<String> jComboBox5;
    private com.toedter.calendar.JDateChooser jDateChooser3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField neuf;
    private javax.swing.JTextField nom;
    private javax.swing.JTextField onze;
    private javax.swing.JTextField pnom;
    private javax.swing.JTextField poste;
    private javax.swing.JTextField quatre;
    private javax.swing.JTextField roll;
    private javax.swing.JTextField sept;
    private javax.swing.JTextField six;
    private javax.swing.JTextField sortie;
    private javax.swing.JTextField treize;
    private javax.swing.JTextField trois;
    private javax.swing.JTextField un;
    // End of variables declaration//GEN-END:variables

}
