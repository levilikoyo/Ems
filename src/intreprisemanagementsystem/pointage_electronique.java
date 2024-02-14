/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intreprisemanagementsystem;

import com.alee.laf.WebLookAndFeel;

import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author DOSHE
 */
public class pointage_electronique extends javax.swing.JFrame {
DefaultTableModel mode;
    
   Connection con=null;
    PreparedStatement pst=null;
    ResultSet rs= null;
      Timer timer;
     // Date a;
    public pointage_electronique() {
        initComponents();
             con=JavaDbConnect.dbConnect();
         this.setExtendedState(JFrame.MAXIMIZED_BOTH);
            setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("icons8_Raspberry_Pi_48px_1.png")));
       
            times();
        lock_employee();
        datess();
        date3.setDate(new Date());
    // jPanel8.setVisible(false);
     jPanel3.setVisible(false);
     
     
       
    }
    
      public void lock_employee(){
         ActionListener actionListener = new  ActionListener(){
             @Override
             public void actionPerformed(ActionEvent e) {
         dates();
             }
             
         };
         timer = new Timer(100,actionListener); 
    
timer.start();

//To change body of generated methods, choose Tools | Templates.
}
  
    public void datess(){
    
         Date date = new Date();
    SimpleDateFormat dateFormat1 = new SimpleDateFormat("EEEE, MMMM d, yyyy");
         String addDate1 = dateFormat1.format(date);
       dates.setText(addDate1);
    
    }
    
public void times(){
         ActionListener actionListener = new  ActionListener(){
             @Override
             public void actionPerformed(ActionEvent e) {
                 Date date = new Date();
         DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
         String time = timeFormat.format(date);
         
         DateFormat timeFormat1 = new SimpleDateFormat("HH");
         String time1 = timeFormat1.format(date);
        // Double a = Double.parseDouble(time1);
         Integer a = Integer.parseInt(time1);
        
        if(a>=12){
          clock.setText(time +"  PM");
         }else{
          clock.setText(time +"  AM");
         }
        
             }
             
         };
         
         timer = new Timer(1000,actionListener); 
timer.setInitialDelay(0);
timer.start();//To change body of generated methods, choose Tools | Templates.
             }

 public void dates(){
         DefaultListModel list = new DefaultListModel();
    
    try{
    String sql="select * from dates where id=1";
   
    pst=con.prepareStatement(sql);
    rs=pst.executeQuery();
            if(rs.next()){
                String sum=rs.getString("date1");
                String sums=rs.getString("date2");
                String sumss=rs.getString("mois");
                String sumS=rs.getString("STATUT");
                
               
                
                 date1.setText(sum);
                 date2.setText(sums);
                 mois.setText(sumss);
                 statut.setText(sumS);
                 
               
                 
              
    
            }
           
            
                
    }catch(Exception ex){JOptionPane.showMessageDialog(null,ex);}
  
    }
    
 public void datesf(){
     SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd.MM.yyyy");
    
         String addDate1 = dateFormat1.format(new Date());
        dates.setText(addDate1);
    }
 String NAME,LNAME,TITLE,ROLL;
 public void attCall_roll()
    { 
        try{  
     String sql="select * from employee where ROLLNUM='"+roll.getText()+"' and ACTIVE='Active'";
            
           
             pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            if(rs.next()){
            NAME = rs.getString("NAME");
         noms.setText(NAME);
              LNAME = rs.getString("LNAME");
              ROLL = rs.getString("ROLLNUM");
              rolls.setText(ROLL);
               TITLE = rs.getString("TITRE");
               posts.setText(TITLE);
            
          Icon icon = null;
   
         try{
 String sqls="select * from employee where ROLLNUM='"+roll.getText()+"' and ACTIVE='Active'";
     
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
          while(rs.next()){
                byte[] img = rs.getBytes("PHOTOS");
                  icon=new ImageIcon(img);
                 String Nom = rs.getString("NAME");
                String Lnom = rs.getString("LNAME");
                String Ldate = rs.getString("ROLLNUM");
                String Lpost = rs.getString("TITRE");
               
               pics.setIcon(icon); 
      home_call.add(new NewJPanel1());
  NewJPanel1.nom.setText(Nom);
  NewJPanel1.lnom.setText(Lnom);
  NewJPanel1.roll.setText(Ldate);
  NewJPanel1.poste.setText(Lpost);     
home_call.repaint();
home_call.revalidate();
            }
        }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}
               
            }else{
     home_call.add(new NewJPanel1());
  NewJPanel1.nom.setText("Inconnue");
  NewJPanel1.lnom.setText("Inconnue");
  NewJPanel1.roll.setText("Inconnue");
  NewJPanel1.poste.setText("Inconnue");     
home_call.repaint();
home_call.revalidate();
            
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
    }    
       

 
 String STATUT_CHECK=null;
     public void check_statut(){
     
     try{
         
          String sqls="select statut from  attendance where EMP_ROLL ='"+roll.getText()+"' and Dates='"+dates.getText()+"'";
          
            pst=con.prepareStatement(sqls);
           // pst.setString(1, tmp);
            rs=pst.executeQuery();
           while(rs.next()){
                  
                 
               STATUT_CHECK = rs.getString("statut");
            check.setText(STATUT_CHECK);
             
            
          
          }
  
      }catch(Exception ex){
        JOptionPane.showMessageDialog(null,ex);  
      }
     }
   public void saveS(){
        try{
         
          String sqls="select * from  contract where ROLL ='"+roll.getText()+"'";
          
            pst=con.prepareStatement(sqls);
           // pst.setString(1, tmp);
            rs=pst.executeQuery();
           while(rs.next()){
          
            
          
          }
  
      }catch(Exception ex){
        JOptionPane.showMessageDialog(null,ex);  
      }
          check_statut();
         if(statut.getText().equals("IN")){
          if (STATUT_CHECK==null){
              
               try {
         
        PreparedStatement pst = con.prepareStatement("INSERT INTO attendance(EMP_NAME,EMP_LNAME,EMP_POST,EMP_ROLL,DAYS,DATES,DATETART,DATEEND,MOIS,STATUT,OURS)"
        +"value(?,?,?,?,?,?,?,?,?,?,?)");
       
        pst.setString(1, NAME);
         pst.setString(2, LNAME);
         pst.setString(3, TITLE);
         pst.setString(4, roll.getText());
         pst.setString(5, "1");
         pst.setString(6, dates.getText());
          pst.setString(7, date1.getText());
         pst.setString(8, date2.getText());
         pst.setString(9, mois.getText());
         pst.setString(10,statut.getText());
         pst.setString(11,clock.getText());
        
         
          pst.executeUpdate();
        
         //        JOptionPane.showMessageDialog(null,"Data Saved");
    } catch (SQLException ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
         
         try {
         
        PreparedStatement pst = con.prepareStatement("INSERT INTO backupattendance(EMP_NAME,EMP_LNAME,EMP_POST,EMP_ROLL,DAYS,DATES,DATETART,DATEEND,MOIS,STATUT,OURS)"
        +"value(?,?,?,?,?,?,?,?,?,?,?)");
       
         pst.setString(1, NAME);
         pst.setString(2, LNAME);
         pst.setString(3, TITLE);
         pst.setString(4, roll.getText());
         pst.setString(5, "1");
         pst.setString(6, dates.getText());
          pst.setString(7, date1.getText());
         pst.setString(8, date2.getText());
         pst.setString(9, mois.getText());
         pst.setString(10,statut.getText());
         pst.setString(11,clock.getText());
        
         
          pst.executeUpdate();
        
                 JOptionPane.showMessageDialog(null,"Good Morning"+" "+NAME);
    } catch (  HeadlessException | SQLException ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
          
          }else if(!STATUT_CHECK.equals(statut.getText())){
          
                try {
         
        PreparedStatement pst = con.prepareStatement("INSERT INTO attendance(EMP_NAME,EMP_LNAME,EMP_POST,EMP_ROLL,DAYS,DATES,DATETART,DATEEND,MOIS,STATUT,OURS)"
        +"value(?,?,?,?,?,?,?,?,?,?,?)");
       
        pst.setString(1, NAME);
         pst.setString(2, LNAME);
         pst.setString(3, TITLE);
         pst.setString(4, roll.getText());
         pst.setString(5, "1");
         pst.setString(6, dates.getText());
          pst.setString(7, date1.getText());
         pst.setString(8, date2.getText());
         pst.setString(9, mois.getText());
         pst.setString(10,statut.getText());
             pst.setString(11,clock.getText());
        
         
          pst.executeUpdate();
        
         //        JOptionPane.showMessageDialog(null,"Data Saved");
    } catch (SQLException ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
         
         try {
         
        PreparedStatement pst = con.prepareStatement("INSERT INTO backupattendance(EMP_NAME,EMP_LNAME,EMP_POST,EMP_ROLL,DAYS,DATES,DATETART,DATEEND,MOIS,STATUT,OURS)"
        +"value(?,?,?,?,?,?,?,?,?,?,?)");
       
         pst.setString(1, NAME);
         pst.setString(2, LNAME);
         pst.setString(3, TITLE);
         pst.setString(4, roll.getText());
         pst.setString(5, "1");
         pst.setString(6, dates.getText());
          pst.setString(7, date1.getText());
         pst.setString(8, date2.getText());
         pst.setString(9, mois.getText());
         pst.setString(10,statut.getText());
             pst.setString(11,clock.getText());
        
         
          pst.executeUpdate();
        
                 JOptionPane.showMessageDialog(null,"Good Morning  "+""+NAME);
    } catch (   HeadlessException | SQLException ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
             
          }
          else{
           JOptionPane.showMessageDialog(null,"Please You Can Not Clerck Again");
          }
       
         }else if (statut.getText().equals("OUT")){
          if (STATUT_CHECK==null){
              
               try {
         
        PreparedStatement pst = con.prepareStatement("INSERT INTO attendance(EMP_NAME,EMP_LNAME,EMP_POST,EMP_ROLL,DAYS,DATES,DATETART,DATEEND,MOIS,STATUT)"
        +"value(?,?,?,?,?,?,?,?,?,?)");
       
        pst.setString(1, NAME);
         pst.setString(2, LNAME);
         pst.setString(3, TITLE);
         pst.setString(4, roll.getText());
         pst.setString(5, "1");
         pst.setString(6, dates.getText());
          pst.setString(7, date1.getText());
         pst.setString(8, date2.getText());
         pst.setString(9, mois.getText());
         pst.setString(10,statut.getText());
        
         
          pst.executeUpdate();
        
         //        JOptionPane.showMessageDialog(null,"Data Saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
         
         try {
         
        PreparedStatement pst = con.prepareStatement("INSERT INTO backupattendance(EMP_NAME,EMP_LNAME,EMP_POST,EMP_ROLL,DAYS,DATES,DATETART,DATEEND,MOIS,STATUT)"
        +"value(?,?,?,?,?,?,?,?,?,?)");
       
         pst.setString(1, NAME);
         pst.setString(2, LNAME);
         pst.setString(3, TITLE);
         pst.setString(4, roll.getText());
         pst.setString(5, "1");
         pst.setString(6, dates.getText());
          pst.setString(7, date1.getText());
         pst.setString(8, date2.getText());
         pst.setString(9, mois.getText());
         pst.setString(10,statut.getText());
        
         
          pst.executeUpdate();
        
                 JOptionPane.showMessageDialog(null,"Good Bye"+" "+NAME);
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
          
          }else if(!STATUT_CHECK.equals(statut.getText())){
          
                try {
         
        PreparedStatement pst = con.prepareStatement("INSERT INTO attendance(EMP_NAME,EMP_LNAME,EMP_POST,EMP_ROLL,DAYS,DATES,DATETART,DATEEND,MOIS,STATUT)"
        +"value(?,?,?,?,?,?,?,?,?,?)");
       
        pst.setString(1, NAME);
         pst.setString(2, LNAME);
         pst.setString(3, TITLE);
         pst.setString(4, roll.getText());
         pst.setString(5, "1");
         pst.setString(6, dates.getText());
          pst.setString(7, date1.getText());
         pst.setString(8, date2.getText());
         pst.setString(9, mois.getText());
         pst.setString(10,statut.getText());
        
         
          pst.executeUpdate();
        
         //        JOptionPane.showMessageDialog(null,"Data Saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
         
         try {
         
        PreparedStatement pst = con.prepareStatement("INSERT INTO backupattendance(EMP_NAME,EMP_LNAME,EMP_POST,EMP_ROLL,DAYS,DATES,DATETART,DATEEND,MOIS,STATUT)"
        +"value(?,?,?,?,?,?,?,?,?,?)");
       
         pst.setString(1, NAME);
         pst.setString(2, LNAME);
         pst.setString(3, TITLE);
         pst.setString(4, roll.getText());
         pst.setString(5, "1");
         pst.setString(6, dates.getText());
          pst.setString(7, date1.getText());
         pst.setString(8, date2.getText());
         pst.setString(9, mois.getText());
         pst.setString(10,statut.getText());
        
         
          pst.executeUpdate();
        
                 JOptionPane.showMessageDialog(null,"Good Bye "+""+NAME);
    } catch ( Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
             
          }
          else{
           JOptionPane.showMessageDialog(null,"Please You Can Not Clerck Again");
          }
       
         
         }
         
        
      }
public void save_Good(){
    
    
        check_statut();
         Date date = new Date();
    SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy");
         String annee = dateFormat1.format(date);
    String Contract =null;
         
         
         if(statut.getText().equals("IN")){
          if (check.getText().equals("")){
    
         try {
         
        PreparedStatement pst = con.prepareStatement("INSERT INTO attendance(EMP_NAME,EMP_LNAME,EMP_POST,EMP_ROLL,DAYS,DATES,DATETART,DATEEND,MOIS,STATUT,OURS,ANNEE,CONTRACT)"
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?)");
       
        pst.setString(1, NAME);
         pst.setString(2, LNAME);
         pst.setString(3, TITLE);
         pst.setString(4, roll.getText());
         pst.setString(5, "1");
         pst.setString(6, dates.getText());
          pst.setString(7, date1.getText());
         pst.setString(8, date2.getText());
         pst.setString(9, mois.getText());
         pst.setString(10,statut.getText());
             pst.setString(11,clock.getText());
             pst.setString(12, annee);
              pst.setString(13, Contract);
        
         
          pst.executeUpdate();
        
         //        JOptionPane.showMessageDialog(null,"Data Saved");
    } catch (SQLException ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
         
         try {
         
        PreparedStatement pst = con.prepareStatement("INSERT INTO backupattendance(EMP_NAME,EMP_LNAME,EMP_POST,EMP_ROLL,DAYS,DATES,DATETART,DATEEND,MOIS,STATUT,OURS,ANNEE,CONTRACT)"
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?)");
       
         pst.setString(1, NAME);
         pst.setString(2, LNAME);
         pst.setString(3, TITLE);
         pst.setString(4, roll.getText());
         pst.setString(5, "1");
         pst.setString(6, dates.getText());
          pst.setString(7, date1.getText());
         pst.setString(8, date2.getText());
         pst.setString(9, mois.getText());
         pst.setString(10,statut.getText());
             pst.setString(11,clock.getText());
             pst.setString(12, annee);
              pst.setString(13, Contract);
        
         
          pst.executeUpdate();
        
                 JOptionPane.showMessageDialog(null,"Good Morning"+" "+NAME);
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
        
        
              
         Programme_daily  m  = new Programme_daily();
        //jDesktopPane1.add(m);
        m.show();
        m.setDefaultCloseOperation(DISPOSE_ON_CLOSE);  
         
          }else{
            JOptionPane.showMessageDialog(null,"Please You Can Not Clerck Again");
          }
          //>>>>>>>>OUT
         }else if(statut.getText().equals("OUT")){
         Programme_daily  m  = new Programme_daily();
        //jDesktopPane1.add(m);
        m.show();
        m.setDefaultCloseOperation(DISPOSE_ON_CLOSE); 
           if (check.getText().equals("IN")){
               
               
          
            try {
         
        PreparedStatement pst = con.prepareStatement("INSERT INTO attendance(EMP_NAME,EMP_LNAME,EMP_POST,EMP_ROLL,DAYS,DATES,DATETART,DATEEND,MOIS,STATUT,OURS,ANNEE,CONTRACT)"
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?)");
       
        pst.setString(1, NAME);
         pst.setString(2, LNAME);
         pst.setString(3, TITLE);
         pst.setString(4, roll.getText());
         pst.setString(5, "1");
         pst.setString(6, dates.getText());
          pst.setString(7, date1.getText());
         pst.setString(8, date2.getText());
         pst.setString(9, mois.getText());
         pst.setString(10,statut.getText());
             pst.setString(11,clock.getText());
             pst.setString(12, annee);
              pst.setString(13, Contract);
        
         
          pst.executeUpdate();
        
         //        JOptionPane.showMessageDialog(null,"Data Saved");
    } catch (SQLException ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
         
         try {
         
        PreparedStatement pst = con.prepareStatement("INSERT INTO backupattendance(EMP_NAME,EMP_LNAME,EMP_POST,EMP_ROLL,DAYS,DATES,DATETART,DATEEND,MOIS,STATUT,OURS,ANNEE,CONTRACT)"
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?)");
       
         pst.setString(1, NAME);
         pst.setString(2, LNAME);
         pst.setString(3, TITLE);
         pst.setString(4, roll.getText());
         pst.setString(5, "1");
         pst.setString(6, dates.getText());
          pst.setString(7, date1.getText());
         pst.setString(8, date2.getText());
         pst.setString(9, mois.getText());
         pst.setString(10,statut.getText());
             pst.setString(11,clock.getText());
             pst.setString(12, annee);
              pst.setString(13, Contract);
        
         
          pst.executeUpdate();
        
                 JOptionPane.showMessageDialog(null,"Good Bye"+" "+NAME);
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }  
          
        
              
             
          }else if(check.getText().equals("")){
           JOptionPane.showMessageDialog(null,"You'v Not Clerck In Morning Please Contact Your HR.");
          }else{
            JOptionPane.showMessageDialog(null,"Please You Can Not Clerck Again");
          }   
         
         }else{
          JOptionPane.showMessageDialog(null,"Please You Can Not Clerck");
         
         }
         check.setText("");     
          
       
//
//         check_statut();
//         Date date = new Date();
//    SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy");
//         String annee = dateFormat1.format(date);
//    
//         
//         
//         if(statut.getText().equals("IN")){
//          if (check.getText().equals("")){
//              
//               try {
//         
//        PreparedStatement pst = con.prepareStatement("INSERT INTO attendance(EMP_NAME,EMP_LNAME,EMP_POST,EMP_ROLL,DAYS,DATES,DATETART,DATEEND,MOIS,STATUT,OURS,ANNEE)"
//        +"value(?,?,?,?,?,?,?,?,?,?,?,?)");
//       
//        pst.setString(1, NAME);
//         pst.setString(2, LNAME);
//         pst.setString(3, TITLE);
//         pst.setString(4, roll.getText());
//         pst.setString(5, "1");
//         pst.setString(6, dates.getText());
//          pst.setString(7, date1.getText());
//         pst.setString(8, date2.getText());
//         pst.setString(9, mois.getText());
//         pst.setString(10,statut.getText());
//             pst.setString(11,clock.getText());
//             pst.setString(12, annee);
//             
//        
//         
//          pst.executeUpdate();
//        
//         //        JOptionPane.showMessageDialog(null,"Data Saved");
//    } catch (SQLException ex) {
//         JOptionPane.showMessageDialog(null, ex.getMessage());
//    }
//         
//         try {
//         
//        PreparedStatement pst = con.prepareStatement("INSERT INTO backupattendance(EMP_NAME,EMP_LNAME,EMP_POST,EMP_ROLL,DAYS,DATES,DATETART,DATEEND,MOIS,STATUT,OURS,ANNEE)"
//        +"value(?,?,?,?,?,?,?,?,?,?,?,?)");
//       
//         pst.setString(1, NAME);
//         pst.setString(2, LNAME);
//         pst.setString(3, TITLE);
//         pst.setString(4, roll.getText());
//         pst.setString(5, "1");
//         pst.setString(6, dates.getText());
//          pst.setString(7, date1.getText());
//         pst.setString(8, date2.getText());
//         pst.setString(9, mois.getText());
//         pst.setString(10,statut.getText());
//             pst.setString(11,clock.getText());
//             pst.setString(12, annee);
//        
//         
//          pst.executeUpdate();
//        
//                 JOptionPane.showMessageDialog(null,"Good Morning"+" "+NAME);
//    } catch (Exception ex) {
//         JOptionPane.showMessageDialog(null, ex.getMessage());
//    }
//         Programme_daily  m  = new Programme_daily();
//        //jDesktopPane1.add(m);
//        m.show();
//        m.setDefaultCloseOperation(DISPOSE_ON_CLOSE);  
//         
//          }else{
//            JOptionPane.showMessageDialog(null,"Please You Can Not Clerck Again");
//          }
//          //>>>>>>>>OUT
//         }else if(statut.getText().equals("OUT")){
//         Programme_daily  m  = new Programme_daily();
//        //jDesktopPane1.add(m);
//        m.show();
//        m.setDefaultCloseOperation(DISPOSE_ON_CLOSE); 
//           if (check.getText().equals("IN")){
//              
//               try {
//         
//        PreparedStatement pst = con.prepareStatement("INSERT INTO attendance(EMP_NAME,EMP_LNAME,EMP_POST,EMP_ROLL,DAYS,DATES,DATETART,DATEEND,MOIS,STATUT,OURS,ANNEE)"
//        +"value(?,?,?,?,?,?,?,?,?,?,?,?)");
//       
//        pst.setString(1, NAME);
//         pst.setString(2, LNAME);
//         pst.setString(3, TITLE);
//         pst.setString(4, roll.getText());
//         pst.setString(5, "1");
//         pst.setString(6, dates.getText());
//          pst.setString(7, date1.getText());
//         pst.setString(8, date2.getText());
//         pst.setString(9, mois.getText());
//         pst.setString(10,statut.getText());
//             pst.setString(11,clock.getText());
//             pst.setString(12, annee);
//        
//         
//          pst.executeUpdate();
//        
//         //        JOptionPane.showMessageDialog(null,"Data Saved");
//    } catch (SQLException ex) {
//         JOptionPane.showMessageDialog(null, ex.getMessage());
//    }
//         
//         try {
//         
//        PreparedStatement pst = con.prepareStatement("INSERT INTO backupattendance(EMP_NAME,EMP_LNAME,EMP_POST,EMP_ROLL,DAYS,DATES,DATETART,DATEEND,MOIS,STATUT,OURS,ANNEE)"
//        +"value(?,?,?,?,?,?,?,?,?,?,?,?)");
//       
//         pst.setString(1, NAME);
//         pst.setString(2, LNAME);
//         pst.setString(3, TITLE);
//         pst.setString(4, roll.getText());
//         pst.setString(5, "1");
//         pst.setString(6, dates.getText());
//          pst.setString(7, date1.getText());
//         pst.setString(8, date2.getText());
//         pst.setString(9, mois.getText());
//         pst.setString(10,statut.getText());
//             pst.setString(11,clock.getText());
//             pst.setString(12, annee);
//        
//         
//          pst.executeUpdate();
//        
//                 JOptionPane.showMessageDialog(null,"Good Bye"+" "+NAME);
//    } catch (Exception ex) {
//         JOptionPane.showMessageDialog(null, ex.getMessage());
//    }
//          }else if(check.getText().equals("")){
//           JOptionPane.showMessageDialog(null,"You'v Not Clerck In Morning Please Contact Your HR.");
//          }else{
//            JOptionPane.showMessageDialog(null,"Please You Can Not Clerck Again");
//          }   
//         
//         }else{
//          JOptionPane.showMessageDialog(null,"Please You Can Not Clerck");
//         
//         }
//         check.setText("");
}

 public void sahowtableS(){
    
     try{
           
    String sqls="select CONTRACT  from contract where roll='"+roll.getText()+"'";
    //`LIBELLE`, `ENTRE`, `SORTIE`, `DATE`, `BUSS`, `USED`, `NUM`
    pst = con.prepareStatement(sqls);
      rs= pst.executeQuery();
     
       jTable3.setModel(DbUtils.resultSetToTableModel(rs));
    
             }catch(Exception ex){
                 JOptionPane.showMessageDialog(null, ex);
             }}
    public void selectontableSAVE(){
      //  sahowtableS();
        attCall_roll();
         
          int rows=jTable3.getRowCount();

  for(int row = 0; row<rows; row++)
  {   
      String Contract = (String)jTable3.getValueAt(row, 0);
 
           
                    Date date = new Date();
    SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy");
         String annee = dateFormat1.format(date);
    
         
         
         if(statut.getText().equals("IN")){
        //  if (check.getText().equals("")){
    
         try {
         
        PreparedStatement pst = con.prepareStatement("INSERT INTO attendance(EMP_NAME,EMP_LNAME,EMP_POST,EMP_ROLL,DAYS,DATES,DATETART,DATEEND,MOIS,STATUT,OURS,ANNEE,CONTRACT)"
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?)");
       
        pst.setString(1, NAME);
         pst.setString(2, LNAME);
         pst.setString(3, TITLE);
         pst.setString(4, roll.getText());
         pst.setString(5, "1");
         pst.setString(6, dates.getText());
          pst.setString(7, date1.getText());
         pst.setString(8, date2.getText());
         pst.setString(9, mois.getText());
         pst.setString(10,statut.getText());
             pst.setString(11,clock.getText());
             pst.setString(12, annee);
              pst.setString(13, Contract);
        
         
          pst.executeUpdate();
        
         //        JOptionPane.showMessageDialog(null,"Data Saved");
    } catch (SQLException ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
         
         try {
         
        PreparedStatement pst = con.prepareStatement("INSERT INTO backupattendance(EMP_NAME,EMP_LNAME,EMP_POST,EMP_ROLL,DAYS,DATES,DATETART,DATEEND,MOIS,STATUT,OURS,ANNEE,CONTRACT)"
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?)");
       
         pst.setString(1, NAME);
         pst.setString(2, LNAME);
         pst.setString(3, TITLE);
         pst.setString(4, roll.getText());
         pst.setString(5, "1");
         pst.setString(6, dates.getText());
          pst.setString(7, date1.getText());
         pst.setString(8, date2.getText());
         pst.setString(9, mois.getText());
         pst.setString(10,statut.getText());
             pst.setString(11,clock.getText());
             pst.setString(12, annee);
              pst.setString(13, Contract);
        
         
          pst.executeUpdate();
          
          
        
                // JOptionPane.showMessageDialog(null,"Good Morning"+" "+NAME);
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
              try{
    String sql="select * from auttendace_sheet where ROLL='"+ROLL+"' and MOIS='"+mois.getText()+"' AND DATE_IN='"+date1.getText()+"' AND DATE_OUT='"+date2.getText()+"' AND CONTRACT='"+Contract+"'";
   
    pst=con.prepareStatement(sql);
    rs=pst.executeQuery();
            if(rs.next()){
               
            }else{
       
                
           try {
        
        PreparedStatement pst = con.prepareStatement("INSERT INTO `auttendace_sheet`( `NAME`, `LNAME`, `FUNCTION`, `ROLL`, `MI1`, `MI2`, `MI3`, `MI4`, `MI5`, `MI6`, `MI7`, `MI8`, `MI9`, `MI10`, `MI11`, `MI12`, `MI13`, `MI14`, `MI15`, `MI16`, `MI17`, `MI18`, `MI19`, `MI20`, `MI21`, `MI22`, `MI23`, `MI24`, `MI25`, `MI26`, `MI27`, `MI28`, `MI29`, `MI30`, `MI31`, `MO1`, `MO2`, `MO3`, `MO4`, `MO5`, `MO6`, `MO7`, `MO8`, `MO9`, `MO10`, `MO11`, `MO12`, `MO13`, `MO14`, `MO15`, `MO16`, `MO17`, `MO18`, `MO19`, `MO20`, `MO21`, `MO22`, `MO23`, `MO24`, `MO25`, `MO26`, `MO27`, `MO28`, `MO29`, `MO30`, `MO31`, `A1`, `A2`, `A3`, `A4`, `A5`, `A6`, `A7`, `A8`, `A9`, `A10`, `A11`, `A12`, `A13`, `A14`, `A15`, `A16`, `A17`, `A18`, `A19`, `A20`, `A21`, `A22`, `A23`, `A24`, `A25`, `A26`, `A27`, `A28`, `A29`, `A30`, `A31`,`S1`, `S2`, `S3`, `S4`, `S5`, `S6`, `S7`, `S8`, `S9`, `S10`, `S11`, `S12`, `S13`, `S14`, `S15`, `S16`, `S17`, `S18`, `S19`, `S20`, `S21`, `S22`, `S23`, `S24`, `S25`, `S26`, `S27`, `S28`, `S29`, `S30`, `S31`,`MOIS`, `Date_in`, `Date_out`,CONTRACT) "+
                " VALUES ('"+NAME+"','"+LNAME+"','"+TITLE+"','"+ROLL+"','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X64','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','"+mois.getText()+"','"+date1.getText()+"','"+date2.getText()+"','"+Contract+"')");
       
         
          pst.executeUpdate();
        
         //        JOptionPane.showMessageDialog(null,"Data Saved");
    } catch (SQLException ex) {
         JOptionPane.showMessageDialog(null, ex);
    }
            
            }
           
            
                
    }catch(HeadlessException | SQLException ex){JOptionPane.showMessageDialog(null,ex);}   
         SimpleDateFormat jrs=new SimpleDateFormat("d");
        SimpleDateFormat sem=new SimpleDateFormat("EE");
         
        String jour= jrs.format(date3.getDate());
        String semaine= sem.format(date3.getDate());
     //   String Mois= moiss.format(a);
        
        String Week ="MI"+jour;
        String WeekO ="MO"+jour;
        String WeekS ="S"+jour;
       
      
           try {
        PreparedStatement pst = con.prepareStatement("UPDATE `auttendace_sheet` SET `"+Week+"`=?,`"+WeekS+"`=? WHERE ROLL='"+roll.getText()+"' and MOIS='"+mois.getText()+"' AND DATE_IN='"+date1.getText()+"' AND DATE_OUT='"+date2.getText()+"' and CONTRACT='"+Contract+"'");
        pst.setString(1,"1");
     
         pst.setString(2,semaine);
        pst.executeUpdate();
    } catch (SQLException ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());}
              
         Programme_daily  m  = new Programme_daily();
        //jDesktopPane1.add(m);
        m.show();
        m.setDefaultCloseOperation(DISPOSE_ON_CLOSE);  
         
//          }else{
//            JOptionPane.showMessageDialog(null,"Please You Can Not Clerck Again");
//          }
          //>>>>>>>>OUT
         }else if(statut.getText().equals("OUT")){
         Programme_daily  m  = new Programme_daily();
        //jDesktopPane1.add(m);
        m.show();
        m.setDefaultCloseOperation(DISPOSE_ON_CLOSE); 
         //  if (check.getText().equals("IN")){
               
               
          
            try {
         
        PreparedStatement pst = con.prepareStatement("INSERT INTO attendance(EMP_NAME,EMP_LNAME,EMP_POST,EMP_ROLL,DAYS,DATES,DATETART,DATEEND,MOIS,STATUT,OURS,ANNEE,CONTRACT)"
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?)");
       
        pst.setString(1, NAME);
         pst.setString(2, LNAME);
         pst.setString(3, TITLE);
         pst.setString(4, roll.getText());
         pst.setString(5, "1");
         pst.setString(6, dates.getText());
          pst.setString(7, date1.getText());
         pst.setString(8, date2.getText());
         pst.setString(9, mois.getText());
         pst.setString(10,statut.getText());
             pst.setString(11,clock.getText());
             pst.setString(12, annee);
              pst.setString(13, Contract);
        
         
          pst.executeUpdate();
        
         //        JOptionPane.showMessageDialog(null,"Data Saved");
    } catch (SQLException ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
         
         try {
         
        PreparedStatement pst = con.prepareStatement("INSERT INTO backupattendance(EMP_NAME,EMP_LNAME,EMP_POST,EMP_ROLL,DAYS,DATES,DATETART,DATEEND,MOIS,STATUT,OURS,ANNEE,CONTRACT)"
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?)");
       
         pst.setString(1, NAME);
         pst.setString(2, LNAME);
         pst.setString(3, TITLE);
         pst.setString(4, roll.getText());
         pst.setString(5, "1");
         pst.setString(6, dates.getText());
          pst.setString(7, date1.getText());
         pst.setString(8, date2.getText());
         pst.setString(9, mois.getText());
         pst.setString(10,statut.getText());
             pst.setString(11,clock.getText());
             pst.setString(12, annee);
              pst.setString(13, Contract);
        
         
          pst.executeUpdate();
        
             //    JOptionPane.showMessageDialog(null,"Good Bye"+" "+NAME);
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }  
                    try{
    String sql="select * from auttendace_sheet where ROLL='"+ROLL+"' and MOIS='"+mois.getText()+"' AND DATE_IN='"+date1.getText()+"' AND DATE_OUT='"+date2.getText()+"' AND CONTRACT='"+Contract+"'";
   
    pst=con.prepareStatement(sql);
    rs=pst.executeQuery();
            if(rs.next()){
               
            }else{
       
                
           try {
        
        PreparedStatement pst = con.prepareStatement("INSERT INTO `auttendace_sheet`( `NAME`, `LNAME`, `FUNCTION`, `ROLL`, `MI1`, `MI2`, `MI3`, `MI4`, `MI5`, `MI6`, `MI7`, `MI8`, `MI9`, `MI10`, `MI11`, `MI12`, `MI13`, `MI14`, `MI15`, `MI16`, `MI17`, `MI18`, `MI19`, `MI20`, `MI21`, `MI22`, `MI23`, `MI24`, `MI25`, `MI26`, `MI27`, `MI28`, `MI29`, `MI30`, `MI31`, `MO1`, `MO2`, `MO3`, `MO4`, `MO5`, `MO6`, `MO7`, `MO8`, `MO9`, `MO10`, `MO11`, `MO12`, `MO13`, `MO14`, `MO15`, `MO16`, `MO17`, `MO18`, `MO19`, `MO20`, `MO21`, `MO22`, `MO23`, `MO24`, `MO25`, `MO26`, `MO27`, `MO28`, `MO29`, `MO30`, `MO31`, `A1`, `A2`, `A3`, `A4`, `A5`, `A6`, `A7`, `A8`, `A9`, `A10`, `A11`, `A12`, `A13`, `A14`, `A15`, `A16`, `A17`, `A18`, `A19`, `A20`, `A21`, `A22`, `A23`, `A24`, `A25`, `A26`, `A27`, `A28`, `A29`, `A30`, `A31`,`S1`, `S2`, `S3`, `S4`, `S5`, `S6`, `S7`, `S8`, `S9`, `S10`, `S11`, `S12`, `S13`, `S14`, `S15`, `S16`, `S17`, `S18`, `S19`, `S20`, `S21`, `S22`, `S23`, `S24`, `S25`, `S26`, `S27`, `S28`, `S29`, `S30`, `S31`,`MOIS`, `Date_in`, `Date_out`,CONTRACT) "+
                " VALUES ('"+NAME+"','"+LNAME+"','"+TITLE+"','"+ROLL+"','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X64','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','"+mois.getText()+"','"+date1.getText()+"','"+date2.getText()+"','"+Contract+"')");
       
         
          pst.executeUpdate();
        
         //        JOptionPane.showMessageDialog(null,"Data Saved");
    } catch (SQLException ex) {
         JOptionPane.showMessageDialog(null, ex);
    }
            
            }
           
            
                
    }catch(HeadlessException | SQLException ex){JOptionPane.showMessageDialog(null,ex);}   
         SimpleDateFormat jrs=new SimpleDateFormat("d");
        SimpleDateFormat sem=new SimpleDateFormat("EE");
         
        String jour= jrs.format(date3.getDate());
        String semaine= sem.format(date3.getDate());
     //   String Mois= moiss.format(a);
        
        String Week ="MI"+jour;
        String WeekO ="MO"+jour;
        String WeekS ="S"+jour;
       
      
           try {
        PreparedStatement pst = con.prepareStatement("UPDATE `auttendace_sheet` SET `"+WeekO+"`=?,`"+WeekS+"`=? WHERE ROLL='"+roll.getText()+"' and MOIS='"+mois.getText()+"' AND DATE_IN='"+date1.getText()+"' AND DATE_OUT='"+date2.getText()+"' and CONTRACT='"+Contract+"'");
        pst.setString(1,"1");
     
         pst.setString(2,semaine);
        pst.executeUpdate();
    } catch (SQLException ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());}  
         
              
             
         // }else if(check.getText().equals("")){
//           JOptionPane.showMessageDialog(null,"You'v Not Clerck In Morning Please Contact Your HR.");
//          }
//           else{
//            JOptionPane.showMessageDialog(null,"Please You Can Not Clerck Again");
//          }   
         
         }else{
          JOptionPane.showMessageDialog(null,"Please You Can Not Clerck");
         
         }
         check.setText("");  
         //  }
  
//      }catch(Exception ex){
//        JOptionPane.showMessageDialog(null,ex); }
   
          
  
  }
  if(statut.getText().equals("IN")){
   JOptionPane.showMessageDialog(null,"Good Morning"+" "+NAME);
  }else if(statut.getText().equals("IN")){
  JOptionPane.showMessageDialog(null,"Good Bye"+" "+NAME);
  }
  
   
      //  JOptionPane.showMessageDialog(null,"Tranction Saved");
   }
  
   
     
   

       
    public ImageIcon ResizeImage(String ImagePath, byte[] pic)
    {
        ImageIcon MyImage = null;
        if(ImagePath !=null)
        {
            MyImage = new ImageIcon(ImagePath);
        }else
        {
            MyImage = new ImageIcon (ImagePath);
        }
        Image img =MyImage.getImage();
        Image newImg= img.getScaledInstance(pics.getWidth(),pics.getHeight(),Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(newImg);
        return image;
    }    
        
 
  

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        dates = new javax.swing.JLabel();
        clock = new javax.swing.JLabel();
        statut = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        date2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        date1 = new javax.swing.JLabel();
        mois = new javax.swing.JLabel();
        pics = new sample.swing.ImageAvatar();
        home_call = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        date3 = new com.alee.extended.date.WebDateField();
        check = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel3.setBackground(java.awt.SystemColor.activeCaption);
        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("DAILY ELECTRONIC ATTENDANCE MANAGEMENT");
        jLabel3.setOpaque(true);

        jPanel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        dates.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        dates.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        dates.setText("TUE-June 25,2019");

        clock.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        clock.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        clock.setText("00:00:00   PM");

        statut.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        statut.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        statut.setText("statut");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dates, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(clock, javax.swing.GroupLayout.DEFAULT_SIZE, 296, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statut, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(dates)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(clock)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(statut, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(1, 1, 1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        roll.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        roll.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        roll.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                rollKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(roll, javax.swing.GroupLayout.DEFAULT_SIZE, 373, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(roll, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel5.setBackground(new java.awt.Color(51, 255, 0));
        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("OK");
        jLabel5.setOpaque(true);
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });

        date2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        date2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        date2.setText("Date2");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("To");

        date1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        date1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        date1.setText("Date1");

        mois.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        mois.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        mois.setText("mois");

        pics.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        pics.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/tetes.JPG"))); // NOI18N
        pics.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                picsMouseClicked(evt);
            }
        });

        home_call.setLayout(new javax.swing.BoxLayout(home_call, javax.swing.BoxLayout.LINE_AXIS));

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(mois, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(date1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(jLabel4)
                                .addGap(6, 6, 6)
                                .addComponent(date2, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGap(306, 306, 306)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(home_call, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pics, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pics, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(home_call, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(mois)
                    .addComponent(date1)
                    .addComponent(jLabel4)
                    .addComponent(date2))
                .addGap(6, 6, 6)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/cancel.png"))); // NOI18N
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        noms.setBackground(new java.awt.Color(255, 255, 255));
        noms.setForeground(new java.awt.Color(240, 240, 240));
        noms.setOpaque(true);

        rolls.setBackground(new java.awt.Color(255, 255, 255));
        rolls.setForeground(new java.awt.Color(240, 240, 240));
        rolls.setOpaque(true);

        posts.setBackground(new java.awt.Color(255, 255, 255));
        posts.setForeground(new java.awt.Color(240, 240, 240));
        posts.setOpaque(true);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(posts, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(rolls, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(noms, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(noms, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rolls, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(posts, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable3.setGridColor(new java.awt.Color(204, 204, 204));
        jTable3.setRowHeight(22);
        jTable3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable3MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable3);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(date3, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
                    .addComponent(check, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(date3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(check, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(85, 85, 85)
                .addComponent(jLabel1))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(506, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(500, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(119, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked

selectontableSAVE();
 home_call.removeAll();
 home_call.repaint();
 home_call.revalidate();
 roll.setText("");
 pics.setIcon(ResizeImage("D:\\logos\\tetes.JPG",null));

//roll.setText("");

// TODO add your handling code here:
    }//GEN-LAST:event_jLabel5MouseClicked

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
//photophatom();        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowOpened

    private void rollKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_rollKeyPressed
 if (evt.getKeyCode()==KeyEvent.VK_ENTER){
//       call_fiche();
   
      try{
         
    sahowtableS();
  attCall_roll(); 
    }catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex); }       
}         // TODO add your handling code here:
    }//GEN-LAST:event_rollKeyPressed

    private void picsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_picsMouseClicked
//        select();
        // TODO add your handling code here:
    }//GEN-LAST:event_picsMouseClicked

    private void jTable3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable3MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable3MouseClicked

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel1MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(pointage_electronique.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(pointage_electronique.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(pointage_electronique.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(pointage_electronique.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                 WebLookAndFeel.install(true);
                new pointage_electronique().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel check;
    private javax.swing.JLabel clock;
    private javax.swing.JLabel date1;
    private javax.swing.JLabel date2;
    private com.alee.extended.date.WebDateField date3;
    private javax.swing.JLabel dates;
    private javax.swing.JPanel home_call;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable3;
    private javax.swing.JLabel mois;
    public static final javax.swing.JLabel noms = new javax.swing.JLabel();
    private sample.swing.ImageAvatar pics;
    public static final javax.swing.JLabel posts = new javax.swing.JLabel();
    public static final Palette.TextFieldSuggestion roll = new Palette.TextFieldSuggestion();
    public static final javax.swing.JLabel rolls = new javax.swing.JLabel();
    private javax.swing.JLabel statut;
    // End of variables declaration//GEN-END:variables

    
}
