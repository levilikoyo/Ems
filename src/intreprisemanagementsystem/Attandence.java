/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intreprisemanagementsystem;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import net.proteanit.sql.DbUtils;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author DOSHE
 */
public class Attandence extends javax.swing.JInternalFrame {

  DefaultTableModel modes;
  DefaultTableModel mode;
  DefaultTableModel etmode;
  DefaultTableModel attmode;
 DefaultTableModel modetable3;
Connection con=null;
PreparedStatement pst=null;
ResultSet rs= null;
String rolls;
String etrolls;
String salaryrolls;
    public Attandence() {
        initComponents();
              con=JavaDbConnect.dbConnect();
//         this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
//        BasicInternalFrameUI bui= (BasicInternalFrameUI) this.getUI();
//       bui.setNorthPane(null);
       Call_ID_TO_BOMBOBOX();
       callcontract(); 
        Groupe1();
         SimpleDateFormat dateFormatsS = new SimpleDateFormat("dd-MMM-yyyy");
         String addDateS = dateFormatsS.format(new Date());
        sick.setText(addDateS);
        
    }
    String ROOL;
    public void selectrool(){
     try{
            String sqls="select roll from  active_employee  where LOCKS='Active'";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
            while(rs.next()){
                ROOL = rs.getString("roll");
                
                 try{
           
             String sql="SELECT  NAME,LNAME,ROLLNUM,TITRE,DEPARTMENT FROM employee WHERE ROLLNUM='"+ROOL+"'";
     //  String sql="SELECT ID, `NUM_FACTURE`, `DATE_FACTURE`, `DESCRIPTION`, `DEBIT_QTY`, `DEBIT_PU`, `DEBIT_PT`, `DEBIT_TVA`, `CREDIT_QTY`, `CREDIT_PU`, `CREDIT_PT`, `CREDIT_TVA`, `DATES`, `NUM` FROM facture_fournisseur where compte='"+jComboBox6.getSelectedItem()+"'";
       pst = con.prepareStatement(sql);
      rs= pst.executeQuery();
      
       
       jTable1.setModel(DbUtils.resultSetToTableModel(rs));
       mode=new DefaultTableModel();
       
       
        TableColumn col0=jTable1.getColumnModel().getColumn(0);
        TableColumn col1=jTable1.getColumnModel().getColumn(1);
        TableColumn col2=jTable1.getColumnModel().getColumn(2);
        TableColumn col3=jTable1.getColumnModel().getColumn(3);
        TableColumn col4=jTable1.getColumnModel().getColumn(4);
       
       
      
       
       col0.setPreferredWidth(150);
       col1.setPreferredWidth(150);
       col2.setPreferredWidth(80);
       col3.setPreferredWidth(100);
       col4.setPreferredWidth(50);
       
      
      
      
       
       
      // jTable1.setModel(mode);
       
     
    }catch(SQLException ex ){
     JOptionPane.showMessageDialog(null, ex);
}
                
                
              
            }
            }
        catch(Exception ex){
         JOptionPane.showMessageDialog(null, ex);}
    
    }
    public void callcontract(){
    try{
            String sql="select distinct(contract) from contract";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                String sum=rs.getString("contract");
                contr.addItem(sum);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex); }   
    
    }
     public void call_in_table(){
     
      try{
           
             String sql="SELECT  DISTINCT(EMP_NAME),EMP_LNAME,EMP_ROLL,EMP_POST FROM backupattendance  WHERE  DATETART='"+date1.getText()+"' and DATEEND='"+date2.getText()+"' and MOIS='"+mois.getSelectedItem()+"' and contract='"+contr.getSelectedItem()+"'";
     //  String sql="SELECT ID, `NUM_FACTURE`, `DATE_FACTURE`, `DESCRIPTION`, `DEBIT_QTY`, `DEBIT_PU`, `DEBIT_PT`, `DEBIT_TVA`, `CREDIT_QTY`, `CREDIT_PU`, `CREDIT_PT`, `CREDIT_TVA`, `DATES`, `NUM` FROM facture_fournisseur where compte='"+jComboBox6.getSelectedItem()+"'";
       pst = con.prepareStatement(sql);
      rs= pst.executeQuery();
      
       
       jTable1.setModel(DbUtils.resultSetToTableModel(rs));
       mode=new DefaultTableModel();
       
       
        TableColumn col0=jTable1.getColumnModel().getColumn(0);
        TableColumn col1=jTable1.getColumnModel().getColumn(1);
        TableColumn col2=jTable1.getColumnModel().getColumn(2);
        TableColumn col3=jTable1.getColumnModel().getColumn(3);
        //TableColumn col4=jTable1.getColumnModel().getColumn(4);
       
       
       
      
       
       col0.setPreferredWidth(150);
       col1.setPreferredWidth(80);
       col2.setPreferredWidth(80);
       col3.setPreferredWidth(100);
       //col4.setPreferredWidth(50);
       
      
       
       
      // jTable1.setModel(mode);
       
     
    }catch(SQLException ex ){
     JOptionPane.showMessageDialog(null, ex);
}
       
       
     }
     
        public void call_in_tableSEARCH(){
     
      try{
           
             String sql="SELECT  DISTINCT(EMP_NAME),EMP_LNAME,EMP_ROLL,EMP_POST FROM backupattendance  WHERE CONTRACT='"+contr.getSelectedItem()+"' and DATETART='"+date1.getText()+"' and DATEEND='"+date2.getText()+"' and  MOIS='"+mois.getSelectedItem()+"' AND EMP_NAME LIKE '"+jTextField1.getText()+"%'";
     //  String sql="SELECT ID, `NUM_FACTURE`, `DATE_FACTURE`, `DESCRIPTION`, `DEBIT_QTY`, `DEBIT_PU`, `DEBIT_PT`, `DEBIT_TVA`, `CREDIT_QTY`, `CREDIT_PU`, `CREDIT_PT`, `CREDIT_TVA`, `DATES`, `NUM` FROM facture_fournisseur where compte='"+jComboBox6.getSelectedItem()+"'";
       pst = con.prepareStatement(sql);
      rs= pst.executeQuery();
      
       
       jTable1.setModel(DbUtils.resultSetToTableModel(rs));
       mode=new DefaultTableModel();
       
       
        TableColumn col0=jTable1.getColumnModel().getColumn(0);
        TableColumn col1=jTable1.getColumnModel().getColumn(1);
        TableColumn col2=jTable1.getColumnModel().getColumn(2);
        TableColumn col3=jTable1.getColumnModel().getColumn(3);
        //TableColumn col4=jTable1.getColumnModel().getColumn(4);
       
       
       
      
       
       col0.setPreferredWidth(150);
       col1.setPreferredWidth(80);
       col2.setPreferredWidth(80);
       col3.setPreferredWidth(100);
       //col4.setPreferredWidth(50);
       
      
       
       
      // jTable1.setModel(mode);
       
     
    }catch(SQLException ex ){
     JOptionPane.showMessageDialog(null, ex);
}
     
     }
    
      public void show_photo_from_db()
               
   {
            try{
          int row= jTable1.getSelectedRow();
          String Table_click = (jTable1.getModel().getValueAt(row,2). toString());
          String sql = "SELECT * FROM  employee WHERE ROLLNUM = '"+Table_click+"' and ACTIVE='Active'";
          pst = con.prepareStatement(sql);
          rs=pst.executeQuery();
          if(rs.next()){
            
          //`ID`, `NOMS`, `LNAME`, `ROLL`, `ADDRESS`, `TEL`, `MAIL`, `GENDER`, `TITLE`, `DEPARTEMENT`, `PHOTOS`, `LOCKS`
             String sum=rs.getString("NAME");
                 name.setText(sum);
                 
                 String sum1=rs.getString("LNAME");
                lname.setText(sum1);
                 
                 String sum2=rs.getString("ROLLNUM");
                 roll.setText(sum2);
                 
                 }
         
    }catch(SQLException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }
           
            
              try{
      // ;
       String sql="SELECT  STATUT,DAYS,DATES,OURS FROM backupattendance where EMP_ROLL='"+roll.getText()+"' and MOIS='"+mois.getSelectedItem()+"' and contract='"+contr.getSelectedItem()+"' ORDER BY ID";
       pst = con.prepareStatement(sql);
      rs= pst.executeQuery();
      jTable2.setModel(DbUtils.resultSetToTableModel(rs));
        
    }catch(SQLException ex ){
     JOptionPane.showMessageDialog(null, ex);
}
             
               
               try{
            String sql="SELECT sum(days) FROM  backupattendance WHERE EMP_ROLL='"+roll.getText()+"' and MOIS='"+mois.getSelectedItem()+"' and contract='"+contr.getSelectedItem()+"'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            if(rs.next()){
                String sums=rs.getString("sum(days)");
                 clerck.setText(sums);
                Double a = Double.parseDouble(clerck.getText());
                
                days.setText(""+a/2);
                
            }
            }
        catch(Exception ex){
      //    JOptionPane.showMessageDialog(null, ex);    
        }
          
            
   }
      
      
      public void update(){
          String CHECK = null;
       try{
            String sql="SELECT * FROM  dates WHERE ID=1";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            if(rs.next()){
                
                
                   try {
         
        PreparedStatement pst = con.prepareStatement("UPDATE `dates` SET `DATE1`=?,`DATE2`=?,`MOIS`=? WHERE ID='1'");
       
        pst.setString(1, date1.getText());
         pst.setString(2, date2.getText());
         pst.setString(3, mois.getSelectedItem().toString());
        
        
         
          pst.executeUpdate();
        
                JOptionPane.showMessageDialog(null,"New Batch Set Succefully");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());}  
                  
                
              
            }else{
                
                    try {
         
        PreparedStatement pst = con.prepareStatement("INSERT INTO `dates`(`DATE1`, `DATE2`, `MOIS`, `STATUT`) VALUES (?,?,?,?)");
       
        pst.setString(1, date1.getText());
         pst.setString(2, date2.getText());
         pst.setString(3, mois.getSelectedItem().toString());
          pst.setString(4, "");
        
        
         
          pst.executeUpdate();
        
                JOptionPane.showMessageDialog(null,"New Batch Set Succefully");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage()); }
                
                
                
                
             
        }
             } catch(Exception ex){
      //    JOptionPane.showMessageDialog(null, ex);    
        }
            
         }    
       
      
      
        public void Call_ID_TO_BOMBOBOX()
    {
         {
             String Statut;
        try{
            String sql="select * from dates where ID=1";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                String sum=rs.getString("DATE1");
                  date1.setText(sum);
                  String sum1=rs.getString("DATE2");
                  date2.setText(sum1);
                  String sum2=rs.getString("MOIS");
                 mois.setSelectedItem(sum2);
               //  mois1.setSelectedItem(sum2);
                 Statut=rs.getString("STATUT");
                 if(Statut.equals("IN")){
                 in.isSelected();
                 }else{
                  out.isSelected();
                 }
                 
                 
                 
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
    }
    }
       public void truncate()
    {
         try{
        String sql = "truncate  attendance";
        
         pst = con.prepareStatement(sql);
        // pst.setString(1,num.getText());
         pst.executeUpdate();
         JOptionPane.showMessageDialog(null,"Table Truncated");
              
     }catch(SQLException | HeadlessException ex ){
         JOptionPane.showMessageDialog(null,ex);
     } 
    }
      
       public void Groupe1(){
ButtonGroup bg1 = new ButtonGroup();
bg1.add(in);
bg1.add(out);
bg1.add(lock);

}
      
       public void OnOff(){
           String avant="";
if(in.isSelected()){
avant=in.getText();
}
if(out.isSelected()){
avant=out.getText();
}
if(lock.isSelected()){
avant=lock.getText();
}
      
       try {
         
        PreparedStatement pst = con.prepareStatement("UPDATE `dates` SET STATUT=? WHERE ID='1'");
       
        pst.setString(1, avant);
        
        
        
         
          pst.executeUpdate();
        
                JOptionPane.showMessageDialog(null,"Now People are Authorized to Sign");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
      
      }
       
       
         public void report(){
              //   String tmp=project.getSelectedItem().toString();
             try{
             
          String sqls="Select path from pathn";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
            if(rs.next()){
             
                String sum=rs.getString("path");
                  
                 String NameFiles=sum;
            
            String NameFile=""+NameFiles+"attendancerepport.jrxml";//journal_de_banque
           // String NameFile=""+NameFiles+"journal_de_banque.jrxml";//journal_de_banque
                 
                 //SELECT `CAT` AS budget_CAT, `ITEM` AS budget_ITEM, `QTY` AS budget_QTY, `UNITY` AS budget_UNITY, `PU` AS budget_PU, `PT` AS budget_PT, `PROJECT` AS budget_POJECT, `CODE`  AS budget_CODE, `LASTING` AS budget_LASTING FROM `budget` WHERE 1
                 
                JasperDesign jd=JRXmlLoader.load(NameFile);
        
        
              // JasperDesign jd=JRXmlLoader.load("C:\\Users\\Dosh\\Documents\\NetBeansProjects\\intreprisemanagementsystem\\src\\intreprisemanagementsystem\\journaldecaise.jrxml");
              String sql="SELECT * FROM  backupattendance  where EMP_ROLL='"+roll.getText()+"' and MOIS='"+mois.getSelectedItem()+"' and contract='"+contr.getSelectedItem()+"'";
              
    
                JRDesignQuery nq=new JRDesignQuery();
                nq.setText(sql);
                jd.setQuery(nq);
               
                 JasperReport jr =JasperCompileManager.compileReport(jd);
                 JasperPrint jp=JasperFillManager.fillReport(jr,null,con);
                 JasperViewer.viewReport(jp,false);
                 
                 
                 JasperViewer m= new JasperViewer(jp);
                 m.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            }
             }catch(Exception ex){
                  JOptionPane.showMessageDialog(null, ex);   
             }
             
             }
             
         
           public void report_sheet(){
              //   String tmp=project.getSelectedItem().toString();
             try{
             
          String sqls="Select path from pathn";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
            if(rs.next()){
             
                String sum=rs.getString("path");
                  
                 String NameFiles=sum;
            
            String NameFile=""+NameFiles+"timing_sheet.jrxml";//journal_de_banque
           JasperDesign jd=JRXmlLoader.load(NameFile);
         String sql="SELECT * FROM  auttendace_sheet where  MOIS='"+mois.getSelectedItem()+"' AND DATE_IN='"+date1.getText()+"' AND DATE_OUT='"+date2.getText()+"' and CONTRACT='"+contr.getSelectedItem()+"'";//  where EMP_ROLL='"+roll.getText()+"' and MOIS='"+mois.getSelectedItem()+"'";
              
    
                JRDesignQuery nq=new JRDesignQuery();
                nq.setText(sql);
                jd.setQuery(nq);
               
                 JasperReport jr =JasperCompileManager.compileReport(jd);
                 JasperPrint jp=JasperFillManager.fillReport(jr,null,con);
                 JasperViewer.viewReport(jp,false);
                 
                 
                 JasperViewer m= new JasperViewer(jp);
                 m.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            }
             }catch(Exception ex){
                  JOptionPane.showMessageDialog(null, ex);   
             }
             
             }
              public void report_sheet_roll(){
              //   String tmp=project.getSelectedItem().toString();
             try{
             
          String sqls="Select path from pathn";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
            if(rs.next()){
             
                String sum=rs.getString("path");
                  
                 String NameFiles=sum;
            
            String NameFile=""+NameFiles+"timing_sheet.jrxml";//journal_de_banque
       JasperDesign jd=JRXmlLoader.load(NameFile);
        String sql="SELECT * FROM  auttendace_sheet where  MOIS='"+mois.getSelectedItem()+"' AND DATE_IN='"+date1.getText()+"' AND DATE_OUT='"+date2.getText()+"' and CONTRACT='"+contr.getSelectedItem()+"' and  ROLL='"+roll.getText()+"'";/// and MOIS='"+mois.getSelectedItem()+"'";
              
    
                JRDesignQuery nq=new JRDesignQuery();
                nq.setText(sql);
                jd.setQuery(nq);
               
                 JasperReport jr =JasperCompileManager.compileReport(jd);
                 JasperPrint jp=JasperFillManager.fillReport(jr,null,con);
                 JasperViewer.viewReport(jp,false);
                 
                 
                 JasperViewer m= new JasperViewer(jp);
                 m.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            }
             }catch(Exception ex){
                  JOptionPane.showMessageDialog(null, ex);   
             }
             
             }
             
         
   public void delete()
    {
     
         try{
        String sql = "DELETE FROM attendance WHERE MOIS=? and DATETART='"+date1.getText()+"' and DATEEND='"+date2.getText()+"'";
        
         pst = con.prepareStatement(sql);
         pst.setString(1,mois.getSelectedItem().toString());
         pst.executeUpdate();
        // JOptionPane.showMessageDialog(null,"delete");
              
     }catch(SQLException | HeadlessException ex ){
         JOptionPane.showMessageDialog(null,ex);
     
     }
          try{
        String sql = "DELETE FROM backupattendance WHERE MOIS=? and DATETART='"+date1.getText()+"' and DATEEND='"+date2.getText()+"' and contract='"+contr.getSelectedItem()+"'";
        
         pst = con.prepareStatement(sql);
         pst.setString(1,mois.getSelectedItem().toString());
         pst.executeUpdate();
        // JOptionPane.showMessageDialog(null,"delete");
              
     }catch(SQLException | HeadlessException ex ){
         JOptionPane.showMessageDialog(null,ex);
     
     
     }
           try{
        String sql = "DELETE FROM auttendace_sheet WHERE MOIS=? and DATE_in='"+date1.getText()+"' and DATE_out='"+date2.getText()+"' and contract='"+contr.getSelectedItem()+"'";
        
         pst = con.prepareStatement(sql);
         pst.setString(1,mois.getSelectedItem().toString());
         pst.executeUpdate();
        // JOptionPane.showMessageDialog(null,"delete");
              
     }catch(SQLException | HeadlessException ex ){
         JOptionPane.showMessageDialog(null,ex);
     
     }
    } 
         

public void savemaladi(){
    SimpleDateFormat dateFormat1 = new SimpleDateFormat("EEEE, MMMM d, yyyy");
         String addDate1 = dateFormat1.format(sick.getDate());
         
          SimpleDateFormat dateFormat1S = new SimpleDateFormat("yyyy");
         String Annee = dateFormat1S.format(sick.getDate());
    
               try{
            String sql="SELECT * FROM  backupattendance WHERE EMP_ROLL='"+roll.getText()+"' and MOIS='"+mois.getSelectedItem()+"' and dates='"+addDate1+"' and contract='"+contr.getSelectedItem()+"'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            if(rs.next()){
            try {
         
        PreparedStatement pst = con.prepareStatement("UPDATE `attendance` SET `STATUT`=? WHERE EMP_ROLL='"+roll.getText()+"' and MOIS='"+mois.getSelectedItem()+"' and dates='"+addDate1+"' and contract='"+contr.getSelectedItem()+"'");
       
        pst.setString(1,"M");
        
         
          pst.executeUpdate();
        
         //        JOptionPane.showMessageDialog(null,"Data Saved");
    } catch (SQLException ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
         
         try {
        PreparedStatement pst = con.prepareStatement("UPDATE `backupattendance` SET `STATUT`=? WHERE EMP_ROLL='"+roll.getText()+"' and MOIS='"+mois.getSelectedItem()+"' and dates='"+addDate1+"' and contract='"+contr.getSelectedItem()+"'");
       
        pst.setString(1,"M");
        
         
          pst.executeUpdate();
        
            
    } catch (  HeadlessException | SQLException ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }   
                
            }else{
            try {
         
        PreparedStatement pst = con.prepareStatement("INSERT INTO attendance(EMP_NAME,EMP_LNAME,EMP_POST,EMP_ROLL,DAYS,DATES,DATETART,DATEEND,MOIS,STATUT,OURS,ANNEE,CONTRACT)"
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?)");
       
        pst.setString(1, name.getText());
         pst.setString(2, lname.getText());
         pst.setString(3, "Tittle");
         pst.setString(4, roll.getText());
         pst.setString(5, "1");
         pst.setString(6, sick.getText());
          pst.setString(7, date1.getText());
         pst.setString(8, date2.getText());
         pst.setString(9, mois.getSelectedItem().toString());
         pst.setString(10,"M");
         pst.setString(11,"M");
         
         pst.setString(12,Annee);
         pst.setString(13,contr.getSelectedItem().toString());
        
         
          pst.executeUpdate();
        
         //        JOptionPane.showMessageDialog(null,"Data Saved");
    } catch (SQLException ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
         
         try {
         
        PreparedStatement pst = con.prepareStatement("INSERT INTO backupattendance(EMP_NAME,EMP_LNAME,EMP_POST,EMP_ROLL,DAYS,DATES,DATETART,DATEEND,MOIS,STATUT,OURS,ANNEE,CONTRACT)"
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?)");
       
         pst.setString(1, name.getText());
         pst.setString(2, lname.getText());
         pst.setString(3, "Tittle");
         pst.setString(4, roll.getText());
         pst.setString(5, "1");
         pst.setString(6, sick.getText());
          pst.setString(7, date1.getText());
         pst.setString(8, date2.getText());
         pst.setString(9, mois.getSelectedItem().toString());
         pst.setString(10,"M");
         pst.setString(11,"M");
         
          pst.setString(12,Annee);
        pst.setString(13,contr.getSelectedItem().toString());
        
         
          pst.executeUpdate();
        
            
    } catch (  HeadlessException | SQLException ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
            }
            }catch(Exception ex){
        JOptionPane.showMessageDialog(null, ex);    
        }

  
JOptionPane.showMessageDialog(null,"Good Recovery to"+" "+name.getText());
}

 public void deleteS(){
     SimpleDateFormat dateFormat1 = new SimpleDateFormat("EEEE, MMMM d, yyyy");
         String addDate1 = dateFormat1.format(sick.getDate());
 
 try{
        String sql = "DELETE FROM attendance WHERE EMP_ROLL=? and DATES='"+addDate1+"' and contract='"+contr.getSelectedItem()+"'";
        
         pst = con.prepareStatement(sql);
         pst.setString(1,roll.getText());
         pst.executeUpdate();
        // JOptionPane.showMessageDialog(null,"delete");
              
     }catch(SQLException | HeadlessException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }
         
          try{
        String sql = "DELETE FROM backupattendance WHERE EMP_ROLL=? and DATES='"+addDate1+"' and contract='"+contr.getSelectedItem()+"'";
        
         pst = con.prepareStatement(sql);
         pst.setString(1,roll.getText());
         pst.executeUpdate();
        // JOptionPane.showMessageDialog(null,"delete");
              
     }catch(SQLException | HeadlessException ex ){
         JOptionPane.showMessageDialog(null,ex);
}
          JOptionPane.showMessageDialog(null,"Transaction removed");
 }
 
  public void deleteAnuller(){
 try{
        String sql = "DELETE FROM attendance WHERE DATETART='"+date1.getText()+"' and DATEEND='"+date2.getText()+"' and MOIS='"+mois.getSelectedItem()+"' and  contract='"+contr.getSelectedItem()+"'";
        
         pst = con.prepareStatement(sql);
         pst.executeUpdate();
        // JOptionPane.showMessageDialog(null,"delete");
              
     }catch(SQLException | HeadlessException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }
         
          try{
        String sql = "DELETE FROM backupattendance WHERE DATETART='"+date1.getText()+"' and DATEEND='"+date2.getText()+"' and MOIS='"+mois.getSelectedItem()+"' and  contract='"+contr.getSelectedItem()+"'";
        
         pst = con.prepareStatement(sql);
         pst.executeUpdate();
        // JOptionPane.showMessageDialog(null,"delete");
              
     }catch(SQLException | HeadlessException ex ){
         JOptionPane.showMessageDialog(null,ex);
}
            try{
        String sql = "DELETE FROM auttendace_sheet WHERE MOIS=? and DATE_in='"+date1.getText()+"' and DATE_out='"+date2.getText()+"' and contract='"+contr.getSelectedItem()+"'";
        
         pst = con.prepareStatement(sql);
         pst.setString(1,mois.getSelectedItem().toString());
         pst.executeUpdate();
        // JOptionPane.showMessageDialog(null,"delete");
              
     }catch(SQLException | HeadlessException ex ){
         JOptionPane.showMessageDialog(null,ex);
     
     }
          JOptionPane.showMessageDialog(null,"Transaction removed");
 }
  
    public void deleteAnullerParpersonne(){
 try{
        String sql = "DELETE FROM attendance WHERE EMP_ROLL='"+roll.getText()+"' and DATETART='"+date1.getText()+"' and DATEEND='"+date2.getText()+"' and MOIS='"+mois.getSelectedItem()+"' and  contract='"+contr.getSelectedItem()+"'";
        
         pst = con.prepareStatement(sql);
         pst.executeUpdate();
        // JOptionPane.showMessageDialog(null,"delete");
              
     }catch(SQLException | HeadlessException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }
         
          try{
        String sql = "DELETE FROM backupattendance WHERE EMP_ROLL='"+roll.getText()+"' and DATETART='"+date1.getText()+"' and DATEEND='"+date2.getText()+"' and MOIS='"+mois.getSelectedItem()+"' and  contract='"+contr.getSelectedItem()+"'";
        
         pst = con.prepareStatement(sql);
         pst.executeUpdate();
        // JOptionPane.showMessageDialog(null,"delete");
              
     }catch(SQLException | HeadlessException ex ){
         JOptionPane.showMessageDialog(null,ex);
}
       try{
        String sql = "DELETE FROM auttendace_sheet WHERE ROLL='"+roll.getText()+"' and MOIS=? and DATE_in='"+date1.getText()+"' and DATE_out='"+date2.getText()+"' and contract='"+contr.getSelectedItem()+"'";
        
         pst = con.prepareStatement(sql);
         pst.setString(1,mois.getSelectedItem().toString());
         pst.executeUpdate();
        // JOptionPane.showMessageDialog(null,"delete");
              
     }catch(SQLException | HeadlessException ex ){JOptionPane.showMessageDialog(null,ex); }     
          JOptionPane.showMessageDialog(null,"Transaction removed");
 }
 
 public void Pointage_demi_journee(){
  int row= jTable2.getSelectedRow();
          String Dates = (jTable2.getModel().getValueAt(row,2). toString());
           String status = (jTable2.getModel().getValueAt(row,0). toString());
           SimpleDateFormat dateFormatsS = new SimpleDateFormat("yyyy-MM-dd");
            long a = Date.parse(Dates);
       String from = dateFormatsS.format(a);
       // SimpleDateFormat date=new SimpleDateFormat("EEEE, MMMM d, yyyy");
        SimpleDateFormat jrs=new SimpleDateFormat("d");
        SimpleDateFormat sem=new SimpleDateFormat("EE");
         SimpleDateFormat moiss=new SimpleDateFormat("MMM");
         
        String jour= jrs.format(a);
        String semaine= sem.format(a);
     //   String Mois= moiss.format(a);
        
        String Week ="MI"+jour;
        String WeekO ="MO"+jour;
        String WeekS ="S"+jour;
       
      if(status.equals("IN")){
           try {
        PreparedStatement pst = con.prepareStatement("UPDATE `auttendace_sheet` SET `"+Week+"`=?,`"+WeekS+"`=? WHERE ROLL='"+roll.getText()+"' and MOIS='"+mois.getSelectedItem()+"' AND DATE_IN='"+date1.getText()+"' AND DATE_OUT='"+date2.getText()+"' and CONTRACT='"+contr.getSelectedItem()+"'");
        pst.setString(1,"A");
         pst.setString(2,semaine);
        pst.executeUpdate();
    } catch (SQLException ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());}
        }else if(status.equals("OUT")){
          try {
        PreparedStatement pst = con.prepareStatement("UPDATE `auttendace_sheet` SET `"+WeekO+"`=? ,`"+WeekS+"`=? WHERE ROLL='"+roll.getText()+"' and MOIS='"+mois.getSelectedItem()+"' AND DATE_IN='"+date1.getText()+"' AND DATE_OUT='"+date2.getText()+"' and CONTRACT='"+contr.getSelectedItem()+"'");
        pst.setString(1,"A");
         pst.setString(2,semaine);
        pst.executeUpdate();
    } catch (SQLException ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());} 
        }
       try{
        String sql = "DELETE FROM attendance WHERE EMP_ROLL=? and DATES='"+Dates+"' and contract='"+contr.getSelectedItem()+"' and STATUT='"+status+"'";
        
         pst = con.prepareStatement(sql);
         pst.setString(1,roll.getText());
         pst.executeUpdate();
        // JOptionPane.showMessageDialog(null,"delete");
              
     }catch(SQLException | HeadlessException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }
         
          try{
        String sql = "DELETE FROM backupattendance WHERE EMP_ROLL=? and DATES='"+Dates+"' and contract='"+contr.getSelectedItem()+"' and STATUT='"+status+"'";
        
         pst = con.prepareStatement(sql);
         pst.setString(1,roll.getText());
         pst.executeUpdate();
        // JOptionPane.showMessageDialog(null,"delete");
              
     }catch(SQLException | HeadlessException ex ){
         JOptionPane.showMessageDialog(null,ex);
}
      JOptionPane.showMessageDialog(null,"Transaction removed"); 
 }
 
  public void Pointage_maladie(){
   SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
         String addDate1 = dateFormat1.format(sick.getDate());
   
        SimpleDateFormat jrs=new SimpleDateFormat("d");
        SimpleDateFormat sem=new SimpleDateFormat("EE");
         
        String jour= jrs.format(sick.getDate());
        String semaine= sem.format(sick.getDate());
     //   String Mois= moiss.format(a);
        
        String Week ="MI"+jour;
        String WeekO ="MO"+jour;
        String WeekS ="S"+jour;
       
      
           try {
        PreparedStatement pst = con.prepareStatement("UPDATE `auttendace_sheet` SET `"+Week+"`=?,`"+WeekO+"`=?,`"+WeekS+"`=? WHERE ROLL='"+roll.getText()+"' and MOIS='"+mois.getSelectedItem()+"' AND DATE_IN='"+date1.getText()+"' AND DATE_OUT='"+date2.getText()+"' and CONTRACT='"+contr.getSelectedItem()+"'");
        pst.setString(1,"M");
        pst.setString(2,"M");
         pst.setString(3,semaine);
        pst.executeUpdate();
    } catch (SQLException ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());}
    
 }
  
    public void Pointage_Absence(){
   SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
         String addDate1 = dateFormat1.format(sick.getDate());
   
        SimpleDateFormat jrs=new SimpleDateFormat("d");
        SimpleDateFormat sem=new SimpleDateFormat("EE");
         
        String jour= jrs.format(sick.getDate());
        String semaine= sem.format(sick.getDate());
     //   String Mois= moiss.format(a);
        
        String Week ="MI"+jour;
        String WeekO ="MO"+jour;
        String WeekS ="S"+jour;
       
      
           try {
        PreparedStatement pst = con.prepareStatement("UPDATE `auttendace_sheet` SET `"+Week+"`=?,`"+WeekO+"`=?,`"+WeekS+"`=? WHERE ROLL='"+roll.getText()+"' and MOIS='"+mois.getSelectedItem()+"' AND DATE_IN='"+date1.getText()+"' AND DATE_OUT='"+date2.getText()+"' and CONTRACT='"+contr.getSelectedItem()+"'");
        pst.setString(1,"A");
        pst.setString(2,"A");
         pst.setString(3,semaine);
        pst.executeUpdate();
    } catch (SQLException ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());}
    
 }
 public void delete0_5(){
      int row= jTable2.getSelectedRow();
          String status = (jTable2.getModel().getValueAt(row,0). toString());
          String datEs = (jTable2.getModel().getValueAt(row,2). toString());
          String ours= (jTable2.getModel().getValueAt(row,3). toString());
 
     SimpleDateFormat dateFormatsS = new SimpleDateFormat("yyyy-MM-dd");
       String from = dateFormatsS.format(date1.getDate());
         String to = dateFormatsS.format(date2.getDate());
           Date d_from = null,d_to=null;
       try {
           d_from = new SimpleDateFormat("yyyy-MM-dd").parse(from);
           d_to = new SimpleDateFormat("yyyy-MM-dd").parse(to);
           
       } catch (ParseException ex) {
           Logger.getLogger(rh_pointage1.class.getName()).log(Level.SEVERE, null, ex);
       }
        long t1=d_from.getTime();
        long t2=d_to.getTime();
       
        SimpleDateFormat date=new SimpleDateFormat("EEEE, MMMM d, yyyy");//EEEE, d MMMM yyyy
       SimpleDateFormat jrs=new SimpleDateFormat("d");
        SimpleDateFormat sem=new SimpleDateFormat("EE");
         SimpleDateFormat moiss=new SimpleDateFormat("MMM");
      if(t1<t2)
        {
            //1 = 1000
            for(long ii=t1;ii<=t2;ii+=86400000)
            {
         
                String dates=date.format(ii);
                String datess=jrs.format(ii);
                int aa= Integer.parseInt(datess);
                String weeks=sem.format(ii);
                String Mois=moiss.format(ii);
                
                String A=null;



                
                String Datess ="A"+datess;
                
                String Week ="MI"+datess;
                String WeekO ="MO"+datess;
                String WeekS ="S"+datess;
    if(status.equals("IN")){
           try {
        PreparedStatement pst = con.prepareStatement("UPDATE `auttendace_sheet` SET `"+Week+"`=?,`"+WeekS+"`=? WHERE ROLL='"+roll.getText()+"' and MOIS='"+mois.getSelectedItem()+"' AND DATE_IN='"+date1.getText()+"' AND DATE_OUT='"+date2.getText()+"' and CONTRACT='"+contr.getSelectedItem()+"'");
        pst.setString(1,"X");
         pst.setString(2,weeks);
        pst.executeUpdate();
    } catch (SQLException ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());}
        }else if(status.equals("OUT")){
          try {
        PreparedStatement pst = con.prepareStatement("UPDATE `auttendace_sheet` SET `"+WeekO+"`=? ,`"+WeekS+"`=? WHERE ROLL='"+roll.getText()+"' and MOIS='"+mois.getSelectedItem()+"' AND DATE_IN='"+date1.getText()+"' AND DATE_OUT='"+date2.getText()+"' and CONTRACT='"+contr.getSelectedItem()+"'");
        pst.setString(1,"X");
         pst.setString(2,weeks);
        pst.executeUpdate();
    } catch (SQLException ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());} 
        }
            }
  
        }
     
 
 try{
        String sql = "DELETE FROM attendance WHERE EMP_ROLL=? and DATES='"+datEs+"' and STATUT='"+status+"'and OURS='"+ours+"' and contract='"+contr.getSelectedItem()+"'";
        
         pst = con.prepareStatement(sql);
         pst.setString(1,roll.getText());
         pst.executeUpdate();
        // JOptionPane.showMessageDialog(null,"delete");
              
     }catch(SQLException | HeadlessException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }
         
          try{
        String sql = "DELETE FROM backupattendance WHERE EMP_ROLL=? and DATES='"+datEs+"' and STATUT='"+status+"'and OURS='"+ours+"' and contract='"+contr.getSelectedItem()+"'";
        
         pst = con.prepareStatement(sql);
         pst.setString(1,roll.getText());
         pst.executeUpdate();
        // JOptionPane.showMessageDialog(null,"delete");
              
     }catch(SQLException | HeadlessException ex ){
         JOptionPane.showMessageDialog(null,ex);
}
       
          
       
          JOptionPane.showMessageDialog(null,"Transaction removed");
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
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        contr = new Palette.Combobox();
        jTextField1 = new Palette.TextField();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        date1 = new com.alee.extended.date.WebDateField();
        date2 = new com.alee.extended.date.WebDateField();
        in = new javax.swing.JRadioButton();
        out = new javax.swing.JRadioButton();
        jButton3 = new javax.swing.JButton();
        lock = new javax.swing.JRadioButton();
        jButton7 = new javax.swing.JButton();
        mois = new Palette.Combobox();
        jPanel6 = new javax.swing.JPanel();
        name = new Palette.TextField();
        lname = new Palette.TextField();
        roll = new Palette.TextField();
        clerck = new Palette.TextField();
        days = new Palette.TextField();
        jButton2 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        sick = new com.alee.extended.date.WebDateField();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu2 = new javax.swing.JMenu();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();

        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_Bill_16px.png"))); // NOI18N

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTable1.setRowHeight(24);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Search By:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N

        contr.setLabeText("Select On Contract");
        contr.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                contrPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        jTextField1.setLabelText("Recherche");
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(contr, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(contr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 502, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 374, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Batch", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("From");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("To");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText(">>>");

        jButton1.setText("OK");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        date2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        in.setBackground(new java.awt.Color(255, 255, 255));
        in.setText("IN");
        in.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inActionPerformed(evt);
            }
        });

        out.setBackground(new java.awt.Color(255, 255, 255));
        out.setText("OUT");
        out.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                outActionPerformed(evt);
            }
        });

        jButton3.setText("Next Month");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        lock.setBackground(new java.awt.Color(255, 255, 255));
        lock.setText("LOCK");
        lock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lockActionPerformed(evt);
            }
        });

        jButton7.setText("Annuler");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        mois.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December", "13-ième mois" }));
        mois.setLabeText("Mois");
        mois.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                moisActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(in, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lock, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(out))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(date1, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2)
                    .addComponent(date2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(mois, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(5, 5, 5)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(date1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(date2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(mois, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(in)
                    .addComponent(out)
                    .addComponent(jButton3)
                    .addComponent(lock)
                    .addComponent(jButton7))
                .addContainerGap())
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Personel Information", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N

        name.setEditable(false);
        name.setLabelText("Nom");

        lname.setEditable(false);
        lname.setLabelText("Poste nom/Prénom");

        roll.setEditable(false);
        roll.setLabelText("Matricule");

        clerck.setEditable(false);
        clerck.setLabelText("Clerck");

        days.setEditable(false);
        days.setLabelText("Jours");

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton2.setText("Print");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(roll, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(clerck, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(days, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(name, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lname, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(roll, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(clerck, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(days, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2))
                .addContainerGap())
        );

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(jTable2);

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Sick Days"));

        jButton4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton4.setText("OK");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        sick.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jButton5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton5.setText("DEL");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton6.setText("0.5Jrs");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sick, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sick, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jMenu2.setText("X");
        jMenu2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jMenu2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu2MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu2);

        jMenu1.setText("File");

        jMenuItem1.setText("HR_Pointage");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("New Pointage");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem3.setText("Time Sheet");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

try{
 con=JavaDbConnect.dbConnect(); 
report_sheet_roll();  

}catch(Exception ex){JOptionPane.showMessageDialog(null,ex);
} finally {
    if (rs != null) {
        try {
            rs.close();
        } catch (SQLException ex) { /* Ignored */}
    }
    if (pst != null) {
        try {
            pst.close();
        } catch (SQLException ex) { /* Ignored */}
    }
    if (con != null) {
        try {
            con.close();
        } catch (SQLException ex) { /* Ignored */}
    }
}    // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jMenu2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu2MouseClicked
this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu2MouseClicked

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
try{
 con=JavaDbConnect.dbConnect(); 
show_photo_from_db();
}catch(Exception ex){JOptionPane.showMessageDialog(null,ex);
} finally {
    if (rs != null) {
        try {
            rs.close();
        } catch (SQLException ex) { /* Ignored */}
    }
    if (pst != null) {
        try {
            pst.close();
        } catch (SQLException ex) { /* Ignored */}
    }
    if (con != null) {
        try {
            con.close();
        } catch (SQLException ex) { /* Ignored */}
    }
}// TODO add your handling code here:
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

try{
 con=JavaDbConnect.dbConnect(); 
update(); 

}catch(Exception ex){JOptionPane.showMessageDialog(null,ex);
} finally {
    if (rs != null) {
        try {
            rs.close();
        } catch (SQLException ex) { /* Ignored */}
    }
    if (pst != null) {
        try {
            pst.close();
        } catch (SQLException ex) { /* Ignored */}
    }
    if (con != null) {
        try {
            con.close();
        } catch (SQLException ex) { /* Ignored */}
    }
}         // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void inActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inActionPerformed

try{
 con=JavaDbConnect.dbConnect(); 
OnOff(); 

}catch(Exception ex){JOptionPane.showMessageDialog(null,ex);
} finally {
    if (rs != null) {
        try {
            rs.close();
        } catch (SQLException ex) { /* Ignored */}
    }
    if (pst != null) {
        try {
            pst.close();
        } catch (SQLException ex) { /* Ignored */}
    }
    if (con != null) {
        try {
            con.close();
        } catch (SQLException ex) { /* Ignored */}
    }
}// TODO add your handling code here:
    }//GEN-LAST:event_inActionPerformed

    private void outActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_outActionPerformed

try{
 con=JavaDbConnect.dbConnect(); 
OnOff(); 
}catch(Exception ex){JOptionPane.showMessageDialog(null,ex);
} finally {
    if (rs != null) {
        try {
            rs.close();
        } catch (SQLException ex) { /* Ignored */}
    }
    if (pst != null) {
        try {
            pst.close();
        } catch (SQLException ex) { /* Ignored */}
    }
    if (con != null) {
        try {
            con.close();
        } catch (SQLException ex) { /* Ignored */}
    }
}
// TODO add your handling code here:
    }//GEN-LAST:event_outActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
try{
 con=JavaDbConnect.dbConnect(); 
int response = JOptionPane.showConfirmDialog(null, "ARE YOU SURE WITH THIS PROCESS BECAUSE ALL THE DATAS WILL BE DELETED", "Confirm",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (response == JOptionPane.YES_OPTION) {
                
                int responseS = JOptionPane.showConfirmDialog(null, "Make Sure You Know What You are doing. Are you sure To Continuous?", "Confirm",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (responseS == JOptionPane.YES_OPTION) {

truncate();
               
            } else if (responseS == JOptionPane.NO_OPTION) {


               
            } else if (response == JOptionPane.NO_OPTION) {
            }
            }
}catch(Exception ex){JOptionPane.showMessageDialog(null,ex);
} finally {
    if (rs != null) {
        try {
            rs.close();
        } catch (SQLException ex) { /* Ignored */}
    }
    if (pst != null) {
        try {
            pst.close();
        } catch (SQLException ex) { /* Ignored */}
    }
    if (con != null) {
        try {
            con.close();
        } catch (SQLException ex) { /* Ignored */}
    }
}
 // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void lockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lockActionPerformed

try{
 con=JavaDbConnect.dbConnect(); 
OnOff();
}catch(Exception ex){JOptionPane.showMessageDialog(null,ex);
} finally {
    if (rs != null) {
        try {
            rs.close();
        } catch (SQLException ex) { /* Ignored */}
    }
    if (pst != null) {
        try {
            pst.close();
        } catch (SQLException ex) { /* Ignored */}
    }
    if (con != null) {
        try {
            con.close();
        } catch (SQLException ex) { /* Ignored */}
    }
}// TODO add your handling code here:
    }//GEN-LAST:event_lockActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
    rh_pointage1 m= new rh_pointage1();
        m.show();

        m.setDefaultCloseOperation(DISPOSE_ON_CLOSE);         // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
try{
 con=JavaDbConnect.dbConnect(); 
delete();
jTable2.setModel(new DefaultTableModel());
}catch(Exception ex){JOptionPane.showMessageDialog(null,ex);
} finally {
    if (rs != null) {
        try {
            rs.close();
        } catch (SQLException ex) { /* Ignored */}
    }
    if (pst != null) {
        try {
            pst.close();
        } catch (SQLException ex) { /* Ignored */}
    }
    if (con != null) {
        try {
            con.close();
        } catch (SQLException ex) { /* Ignored */}
    }
}// TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed

try{
 con=JavaDbConnect.dbConnect(); 
report_sheet();
}catch(Exception ex){JOptionPane.showMessageDialog(null,ex);
} finally {
    if (rs != null) {
        try {
            rs.close();
        } catch (SQLException ex) { /* Ignored */}
    }
    if (pst != null) {
        try {
            pst.close();
        } catch (SQLException ex) { /* Ignored */}
    }
    if (con != null) {
        try {
            con.close();
        } catch (SQLException ex) { /* Ignored */}
    }
}// TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
try{
 con=JavaDbConnect.dbConnect(); 
savemaladi();  
Pointage_maladie();

}catch(Exception ex){JOptionPane.showMessageDialog(null,ex);
} finally {
    if (rs != null) {
        try {
            rs.close();
        } catch (SQLException ex) { /* Ignored */}
    }
    if (pst != null) {
        try {
            pst.close();
        } catch (SQLException ex) { /* Ignored */}
    }
    if (con != null) {
        try {
            con.close();
        } catch (SQLException ex) { /* Ignored */}
    }
}// TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed

try{
 con=JavaDbConnect.dbConnect(); 
deleteS();
Pointage_Absence(); 

}catch(Exception ex){JOptionPane.showMessageDialog(null,ex);
} finally {
    if (rs != null) {
        try {
            rs.close();
        } catch (SQLException ex) { /* Ignored */}
    }
    if (pst != null) {
        try {
            pst.close();
        } catch (SQLException ex) { /* Ignored */}
    }
    if (con != null) {
        try {
            con.close();
        } catch (SQLException ex) { /* Ignored */}
    }
} // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed

try{
 con=JavaDbConnect.dbConnect(); 
Pointage_demi_journee();

}catch(Exception ex){JOptionPane.showMessageDialog(null,ex);
} finally {
    if (rs != null) {
        try {
            rs.close();
        } catch (SQLException ex) { /* Ignored */}
    }
    if (pst != null) {
        try {
            pst.close();
        } catch (SQLException ex) { /* Ignored */}
    }
    if (con != null) {
        try {
            con.close();
        } catch (SQLException ex) { /* Ignored */}
    }
}// TODO add your handling code here:
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
try{
 con=JavaDbConnect.dbConnect(); 
int res = JOptionPane.showOptionDialog(null, "Que voulez-vous faire?","EMS-L",
         JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
         new Object[] { "Annuler les pointages mensuel?", "Annuler un pointage individuel" }, JOptionPane.YES_OPTION);
      if (res == JOptionPane.YES_OPTION){
      deleteAnuller();
      } else if (res == JOptionPane.NO_OPTION) {
       deleteAnullerParpersonne();}   
      call_in_table();

}catch(Exception ex){JOptionPane.showMessageDialog(null,ex);
} finally {
    if (rs != null) {
        try {
            rs.close();
        } catch (SQLException ex) { /* Ignored */}
    }
    if (pst != null) {
        try {
            pst.close();
        } catch (SQLException ex) { /* Ignored */}
    }
    if (con != null) {
        try {
            con.close();
        } catch (SQLException ex) { /* Ignored */}
    }
}
        
        
        
        


    }//GEN-LAST:event_jButton7ActionPerformed

    private void contrPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_contrPopupMenuWillBecomeInvisible
try{
 con=JavaDbConnect.dbConnect(); 
call_in_table(); 
}catch(Exception ex){JOptionPane.showMessageDialog(null,ex);
} finally {
    if (rs != null) {
        try {
            rs.close();
        } catch (SQLException ex) { /* Ignored */}
    }
    if (pst != null) {
        try {
            pst.close();
        } catch (SQLException ex) { /* Ignored */}
    }
    if (con != null) {
        try {
            con.close();
        } catch (SQLException ex) { /* Ignored */}
    }
}        // TODO add your handling code here:
    }//GEN-LAST:event_contrPopupMenuWillBecomeInvisible

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased

try{
 con=JavaDbConnect.dbConnect(); 
call_in_tableSEARCH(); 
}catch(Exception ex){JOptionPane.showMessageDialog(null,ex);
} finally {
    if (rs != null) {
        try {
            rs.close();
        } catch (SQLException ex) { /* Ignored */}
    }
    if (pst != null) {
        try {
            pst.close();
        } catch (SQLException ex) { /* Ignored */}
    }
    if (con != null) {
        try {
            con.close();
        } catch (SQLException ex) { /* Ignored */}
    }
}        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1KeyReleased

    private void moisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moisActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_moisActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Palette.TextField clerck;
    private Palette.Combobox contr;
    private com.alee.extended.date.WebDateField date1;
    private com.alee.extended.date.WebDateField date2;
    private Palette.TextField days;
    private javax.swing.JRadioButton in;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private Palette.TextField jTextField1;
    private Palette.TextField lname;
    private javax.swing.JRadioButton lock;
    private Palette.Combobox mois;
    private Palette.TextField name;
    private javax.swing.JRadioButton out;
    private Palette.TextField roll;
    private com.alee.extended.date.WebDateField sick;
    // End of variables declaration//GEN-END:variables
}
