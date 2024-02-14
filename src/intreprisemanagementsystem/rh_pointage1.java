/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intreprisemanagementsystem;

import com.alee.laf.WebLookAndFeel;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Dosh
 */
public class rh_pointage1 extends javax.swing.JFrame {

   private Connection con=null;
PreparedStatement pst=null;
ResultSet rs= null;
          DefaultTableModel mode;
    public rh_pointage1() {
        con=JavaDbConnect.dbConnect();
        initComponents();
          setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("icons8_Raspberry_Pi_48px_1.png")));
        call();
        
    }
    
    public void call(){
    
    try{
            String sql="select distinct(CONTRACT) from CONTRACT";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                String sum=rs.getString("CONTRACT");
                 buss.addItem(sum);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex); }  
    
    }
     public void call_in_table2(){
     
      try{
           
             String sql="SELECT Distinct(ROLL) as MATRICULE,NAME,LNAME FROM contract where  CONTRACT='"+buss.getSelectedItem()+"'";
     //  String sql="SELECT ID, `NUM_FACTURE`, `DATE_FACTURE`, `DESCRIPTION`, `DEBIT_QTY`, `DEBIT_PU`, `DEBIT_PT`, `DEBIT_TVA`, `CREDIT_QTY`, `CREDIT_PU`, `CREDIT_PT`, `CREDIT_TVA`, `DATES`, `NUM` FROM facture_fournisseur where compte='"+jComboBox6.getSelectedItem()+"'";
       pst = con.prepareStatement(sql);
      rs= pst.executeQuery();
      
       
       jTable1.setModel(DbUtils.resultSetToTableModel(rs));
       mode=new DefaultTableModel();
       
       
     TableColumn col0=jTable1.getColumnModel().getColumn(0);
        TableColumn col1=jTable1.getColumnModel().getColumn(1);
        TableColumn col2=jTable1.getColumnModel().getColumn(2);
      
        
       
       
      
       
       col0.setPreferredWidth(100);
       col1.setPreferredWidth(250);
       col2.setPreferredWidth(250);
      
      
      
       
       
      // jTable1.setModel(mode);
      
     
    }catch(SQLException ex ){
     JOptionPane.showMessageDialog(null, ex);
}
      try{
    String sql="select * from dates where id=1";
   
    pst=con.prepareStatement(sql);
    rs=pst.executeQuery();
            if(rs.next()){
                String sum=rs.getString("date1");
                String sums=rs.getString("date2");
                String sumss=rs.getString("mois");
              //  String sumS=rs.getString("STATUT");
                
               
                
                 date1.setText(sum);
                 date2.setText(sums);
                 mois.setText(sumss);
                // statut.setText(sumS);
                 
               
                 
              
    
            }
           
            
                
    }catch(Exception ex){JOptionPane.showMessageDialog(null,ex);}
  
     }
       public void call_in_table(){
     
      try{
           
             String sql="SELECT ROLLNUM,NAME,LNAME FROM employee where NAME LIKE '"+jTextField1.getText()+"%' AND ACTIVE='Active'";
     //  String sql="SELECT ID, `NUM_FACTURE`, `DATE_FACTURE`, `DESCRIPTION`, `DEBIT_QTY`, `DEBIT_PU`, `DEBIT_PT`, `DEBIT_TVA`, `CREDIT_QTY`, `CREDIT_PU`, `CREDIT_PT`, `CREDIT_TVA`, `DATES`, `NUM` FROM facture_fournisseur where compte='"+jComboBox6.getSelectedItem()+"'";
       pst = con.prepareStatement(sql);
      rs= pst.executeQuery();
      
       
       jTable1.setModel(DbUtils.resultSetToTableModel(rs));
       mode=new DefaultTableModel();
       
       
     TableColumn col0=jTable1.getColumnModel().getColumn(0);
        TableColumn col1=jTable1.getColumnModel().getColumn(1);
        TableColumn col2=jTable1.getColumnModel().getColumn(2);
      
        
       
       
      
       
       col0.setPreferredWidth(100);
       col1.setPreferredWidth(250);
       col2.setPreferredWidth(250);
      
      
      
       
       
      // jTable1.setModel(mode);
      
     
    }catch(SQLException ex ){
     JOptionPane.showMessageDialog(null, ex);
}
      try{
    String sql="select * from dates where id=1";
   
    pst=con.prepareStatement(sql);
    rs=pst.executeQuery();
            if(rs.next()){
                String sum=rs.getString("date1");
                String sums=rs.getString("date2");
                String sumss=rs.getString("mois");
              //  String sumS=rs.getString("STATUT");
                
               
                
                 date1.setText(sum);
                 date2.setText(sums);
                 mois.setText(sumss);
                // statut.setText(sumS);
                 
               
                 
              
    
            }
           
            
                
    }catch(Exception ex){JOptionPane.showMessageDialog(null,ex);}
  
     }
       

    
    public void shawdates_between(){
        
        SimpleDateFormat dateFormatsSs = new SimpleDateFormat("yyyy");
         String annee = dateFormatsSs.format(jDateChooser1.getDate());
        String ROLL=null,NAME=null,LNAME=null,TITRE=null;
        int indexs[]=jTable1.getSelectedRows();
       
        for(int i=0; i < indexs.length;i++){
          ROLL = (jTable1.getModel().getValueAt(indexs[i],0). toString());
           NAME = (jTable1.getModel().getValueAt(indexs[i],1). toString());
           LNAME = (jTable1.getModel().getValueAt(indexs[i],2). toString());
           SimpleDateFormat dateFormatsS = new SimpleDateFormat("yyyy-MM-dd");
         String from = dateFormatsS.format(jDateChooser1.getDate());
         String to = dateFormatsS.format(jDateChooser2.getDate());
         
         
          try{
    String sqls="select * from CONTRACT where ROLL='"+ROLL+"'";
   
    pst=con.prepareStatement(sqls);
    rs=pst.executeQuery();
           if(rs.next()){
            String    TITREs=rs.getString("POSTE");
              TITRE=TITREs.replace("'", "");
                }       
    }catch(Exception ex){JOptionPane.showMessageDialog(null,ex);}
  
         
                
         
         try{
    String sql="select * from auttendace_sheet where ROLL='"+ROLL+"' and MOIS='"+mois.getText()+"' AND DATE_IN='"+date1.getText()+"' AND DATE_OUT='"+date2.getText()+"' AND CONTRACT='"+buss.getSelectedItem()+"'";
   
    pst=con.prepareStatement(sql);
    rs=pst.executeQuery();
            if(rs.next()){
               
            }else{
            
            
                
           try {
        
        PreparedStatement pst = con.prepareStatement("INSERT INTO `auttendace_sheet`( `NAME`, `LNAME`, `FUNCTION`, `ROLL`, `MI1`, `MI2`, `MI3`, `MI4`, `MI5`, `MI6`, `MI7`, `MI8`, `MI9`, `MI10`, `MI11`, `MI12`, `MI13`, `MI14`, `MI15`, `MI16`, `MI17`, `MI18`, `MI19`, `MI20`, `MI21`, `MI22`, `MI23`, `MI24`, `MI25`, `MI26`, `MI27`, `MI28`, `MI29`, `MI30`, `MI31`, `MO1`, `MO2`, `MO3`, `MO4`, `MO5`, `MO6`, `MO7`, `MO8`, `MO9`, `MO10`, `MO11`, `MO12`, `MO13`, `MO14`, `MO15`, `MO16`, `MO17`, `MO18`, `MO19`, `MO20`, `MO21`, `MO22`, `MO23`, `MO24`, `MO25`, `MO26`, `MO27`, `MO28`, `MO29`, `MO30`, `MO31`, `A1`, `A2`, `A3`, `A4`, `A5`, `A6`, `A7`, `A8`, `A9`, `A10`, `A11`, `A12`, `A13`, `A14`, `A15`, `A16`, `A17`, `A18`, `A19`, `A20`, `A21`, `A22`, `A23`, `A24`, `A25`, `A26`, `A27`, `A28`, `A29`, `A30`, `A31`,`S1`, `S2`, `S3`, `S4`, `S5`, `S6`, `S7`, `S8`, `S9`, `S10`, `S11`, `S12`, `S13`, `S14`, `S15`, `S16`, `S17`, `S18`, `S19`, `S20`, `S21`, `S22`, `S23`, `S24`, `S25`, `S26`, `S27`, `S28`, `S29`, `S30`, `S31`,`MOIS`, `Date_in`, `Date_out`,CONTRACT) "+
                " VALUES ('"+NAME+"','"+LNAME+"','"+TITRE+"','"+ROLL+"','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X64','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','"+mois.getText()+"','"+date1.getText()+"','"+date2.getText()+"','"+buss.getSelectedItem()+"')");
       
         
          pst.executeUpdate();
        
         //        JOptionPane.showMessageDialog(null,"Data Saved");
    } catch (SQLException ex) {
         JOptionPane.showMessageDialog(null, ex);
    }
            
            }
           
            
                
    }catch(HeadlessException | SQLException ex){JOptionPane.showMessageDialog(null,ex);}
  
///////////////////////////////////////////////////////////////////////////    
 
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
        // SimpleDateFormat annee=new SimpleDateFormat("yyyy");
    //   String a1=null,a2=null,a3=null,a4=null,a5=null,a6=null,a7=null,a8=null,a9=null,a10=null,a11=null,a12=null,a13=null,a14=null,a15=null,a16=null,a17=null,a18=null,a19=null,a20=null,a21=null,a22=null,a23=null,a24=null,a25=null,a26=null,a27=null,a28=null,a29=null,a30=null,a31=null;
        if(t1<t2)
        {
            //1 = 1000
            for(long ii=t1;ii<=t2;ii+=86400000)
            {
         
                String dates=date.format(ii);
                String datess=jrs.format(ii);
                int aa= Integer.parseInt(datess);
              //  String week=fff.format(ii);
                String weeks=sem.format(ii);
                String Mois=moiss.format(ii);
                
                String A=null;



                
                String Datess ="A"+datess;
                
                String Week ="MI"+datess;
                String WeekO ="MO"+datess;
                String WeekS ="S"+datess;
         
           //     JOptionPane.showMessageDialog(null,A);
                
                
                
               // JOptionPane.showMessageDialog(null,Week+" AND "+Datess);
                
     try{
    String sql="select * from auttendace_sheet where ROLL='"+ROLL+"' and MOIS='"+mois.getText()+"' AND DATE_IN='"+date1.getText()+"' AND DATE_OUT='"+date2.getText()+"' and CONTRACT='"+buss.getSelectedItem()+"'";
   
    pst=con.prepareStatement(sql);
    rs=pst.executeQuery();
            if(rs.next()){
         try {
        PreparedStatement pst = con.prepareStatement("UPDATE `auttendace_sheet` SET `"+Datess+"`=? WHERE ROLL='"+ROLL+"' and MOIS='"+mois.getText()+"' AND DATE_IN='"+date1.getText()+"' AND DATE_OUT='"+date2.getText()+"' and CONTRACT='"+buss.getSelectedItem()+"'");
        pst.setString(1,datess+"-"+Mois );
        pst.executeUpdate();
    } catch (SQLException ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
     try {
        PreparedStatement pst = con.prepareStatement("UPDATE `auttendace_sheet` SET `"+Week+"`=?,`"+WeekO+"`=? ,`"+WeekS+"`=? WHERE ROLL='"+ROLL+"' and MOIS='"+mois.getText()+"' AND DATE_IN='"+date1.getText()+"' AND DATE_OUT='"+date2.getText()+"' and CONTRACT='"+buss.getSelectedItem()+"'");
        pst.setString(1,"1");
        pst.setString(2,"1");
         pst.setString(3,weeks);
        pst.executeUpdate();
    } catch (SQLException ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
                
            }
           
            
                
    }catch(Exception ex){JOptionPane.showMessageDialog(null,ex);}          
                

    

    
        try {
         
        PreparedStatement pst = con.prepareStatement("INSERT INTO attendance(EMP_NAME,EMP_LNAME,EMP_POST,EMP_ROLL,DAYS,DATES,DATETART,DATEEND,MOIS,STATUT,OURS,ANNEE,CONTRACT)"
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?)");
       
        pst.setString(1, NAME);
         pst.setString(2, LNAME);
         pst.setString(3, TITRE);
         pst.setString(4, ROLL);
         pst.setString(5, "1");
         pst.setString(6, dates);
          pst.setString(7, date1.getText());
         pst.setString(8, date2.getText());
         pst.setString(9, mois.getText());
         pst.setString(10,"IN");
         pst.setString(11,"08:00:00  AM");
         pst.setString(12,annee);
        pst.setString(13,buss.getSelectedItem().toString());
        
         
          pst.executeUpdate();
        
         //        JOptionPane.showMessageDialog(null,"Data Saved");
    } catch (SQLException ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
        try {
         
        PreparedStatement pst = con.prepareStatement("INSERT INTO attendance(EMP_NAME,EMP_LNAME,EMP_POST,EMP_ROLL,DAYS,DATES,DATETART,DATEEND,MOIS,STATUT,OURS,ANNEE,CONTRACT)"
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?)");
       
        pst.setString(1, NAME);
         pst.setString(2, LNAME);
         pst.setString(3, TITRE);
         pst.setString(4, ROLL);
         pst.setString(5, "1");
         pst.setString(6, dates);
          pst.setString(7, date1.getText());
         pst.setString(8, date2.getText());
         pst.setString(9, mois.getText());
         pst.setString(10,"OUT");
         pst.setString(11,"16:00:00  PM");
        pst.setString(12,annee);
       pst.setString(13,buss.getSelectedItem().toString());
        
         
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
         pst.setString(3, TITRE);
         pst.setString(4, ROLL);
         pst.setString(5, "1");
         pst.setString(6, dates);
          pst.setString(7, date1.getText());
         pst.setString(8, date2.getText());
         pst.setString(9, mois.getText());
         pst.setString(10,"IN");
         pst.setString(11,"08:00:00  AM");
         pst.setString(12,annee);
          pst.setString(13,buss.getSelectedItem().toString());
        
         
          pst.executeUpdate();
        
               //  JOptionPane.showMessageDialog(null,"Good Morning"+" "+NAME);
    } catch (  HeadlessException | SQLException ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
          try {
         
        PreparedStatement pst = con.prepareStatement("INSERT INTO backupattendance(EMP_NAME,EMP_LNAME,EMP_POST,EMP_ROLL,DAYS,DATES,DATETART,DATEEND,MOIS,STATUT,OURS,ANNEE,CONTRACT)"
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?)");
       
         pst.setString(1, NAME);
         pst.setString(2, LNAME);
         pst.setString(3, TITRE);
         pst.setString(4, ROLL);
         pst.setString(5, "1");
         pst.setString(6, dates);
          pst.setString(7, date1.getText());
         pst.setString(8, date2.getText());
         pst.setString(9, mois.getText());
         pst.setString(10,"OUT");
         pst.setString(11,"16:00:00  PM");
         pst.setString(12,annee);
        pst.setString(13,buss.getSelectedItem().toString());
        
         
          pst.executeUpdate();
        
              //   JOptionPane.showMessageDialog(null,"Good Morning"+" "+NAME);
    } catch (  HeadlessException | SQLException ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
               
            
            }
        }else if (t1==t2){
        for(long ii=t1;ii<=t2;ii+=86400000)
            {
         
                String dates=date.format(ii);
                String datess=jrs.format(ii);
                int aa= Integer.parseInt(datess);
              //  String week=fff.format(ii);
                String weeks=sem.format(ii);
                String Mois=moiss.format(ii);
                
                String A=null;



                
                String Datess ="A"+datess;
                
                String Week ="MI"+datess;
                String WeekO ="MO"+datess;
                String WeekS ="S"+datess;
         
           //     JOptionPane.showMessageDialog(null,A);
                
                
                
               // JOptionPane.showMessageDialog(null,Week+" AND "+Datess);
                
     try{
    String sql="select * from auttendace_sheet where ROLL='"+ROLL+"' and MOIS='"+mois.getText()+"' AND DATE_IN='"+date1.getText()+"' AND DATE_OUT='"+date2.getText()+"' and CONTRACT='"+buss.getSelectedItem()+"'";
   
    pst=con.prepareStatement(sql);
    rs=pst.executeQuery();
            if(rs.next()){
         try {
        PreparedStatement pst = con.prepareStatement("UPDATE `auttendace_sheet` SET `"+Datess+"`=? WHERE ROLL='"+ROLL+"' and MOIS='"+mois.getText()+"' AND DATE_IN='"+date1.getText()+"' AND DATE_OUT='"+date2.getText()+"' and CONTRACT='"+buss.getSelectedItem()+"'");
        pst.setString(1,datess+"-"+Mois );
        pst.executeUpdate();
    } catch (SQLException ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
     try {
        PreparedStatement pst = con.prepareStatement("UPDATE `auttendace_sheet` SET `"+Week+"`=?,`"+WeekO+"`=? ,`"+WeekS+"`=? WHERE ROLL='"+ROLL+"' and MOIS='"+mois.getText()+"' AND DATE_IN='"+date1.getText()+"' AND DATE_OUT='"+date2.getText()+"' and CONTRACT='"+buss.getSelectedItem()+"'");
        pst.setString(1,"1");
        pst.setString(2,"1");
         pst.setString(3,weeks);
        pst.executeUpdate();
    } catch (SQLException ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
                
            }
           
            
                
    }catch(Exception ex){JOptionPane.showMessageDialog(null,ex);}          
                

    

    
        try {
         
        PreparedStatement pst = con.prepareStatement("INSERT INTO attendance(EMP_NAME,EMP_LNAME,EMP_POST,EMP_ROLL,DAYS,DATES,DATETART,DATEEND,MOIS,STATUT,OURS,ANNEE,CONTRACT)"
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?)");
       
        pst.setString(1, NAME);
         pst.setString(2, LNAME);
         pst.setString(3, TITRE);
         pst.setString(4, ROLL);
         pst.setString(5, "1");
         pst.setString(6, dates);
          pst.setString(7, date1.getText());
         pst.setString(8, date2.getText());
         pst.setString(9, mois.getText());
         pst.setString(10,"IN");
         pst.setString(11,"08:00:00  AM");
         pst.setString(12,annee);
        pst.setString(13,buss.getSelectedItem().toString());
        
         
          pst.executeUpdate();
        
         //        JOptionPane.showMessageDialog(null,"Data Saved");
    } catch (SQLException ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
        try {
         
        PreparedStatement pst = con.prepareStatement("INSERT INTO attendance(EMP_NAME,EMP_LNAME,EMP_POST,EMP_ROLL,DAYS,DATES,DATETART,DATEEND,MOIS,STATUT,OURS,ANNEE,CONTRACT)"
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?)");
       
        pst.setString(1, NAME);
         pst.setString(2, LNAME);
         pst.setString(3, TITRE);
         pst.setString(4, ROLL);
         pst.setString(5, "1");
         pst.setString(6, dates);
          pst.setString(7, date1.getText());
         pst.setString(8, date2.getText());
         pst.setString(9, mois.getText());
         pst.setString(10,"OUT");
         pst.setString(11,"16:00:00  PM");
        pst.setString(12,annee);
       pst.setString(13,buss.getSelectedItem().toString());
        
         
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
         pst.setString(3, TITRE);
         pst.setString(4, ROLL);
         pst.setString(5, "1");
         pst.setString(6, dates);
          pst.setString(7, date1.getText());
         pst.setString(8, date2.getText());
         pst.setString(9, mois.getText());
         pst.setString(10,"IN");
         pst.setString(11,"08:00:00  AM");
         pst.setString(12,annee);
          pst.setString(13,buss.getSelectedItem().toString());
        
         
          pst.executeUpdate();
        
               //  JOptionPane.showMessageDialog(null,"Good Morning"+" "+NAME);
    } catch (  HeadlessException | SQLException ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
          try {
         
        PreparedStatement pst = con.prepareStatement("INSERT INTO backupattendance(EMP_NAME,EMP_LNAME,EMP_POST,EMP_ROLL,DAYS,DATES,DATETART,DATEEND,MOIS,STATUT,OURS,ANNEE,CONTRACT)"
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?)");
       
         pst.setString(1, NAME);
         pst.setString(2, LNAME);
         pst.setString(3, TITRE);
         pst.setString(4, ROLL);
         pst.setString(5, "1");
         pst.setString(6, dates);
          pst.setString(7, date1.getText());
         pst.setString(8, date2.getText());
         pst.setString(9, mois.getText());
         pst.setString(10,"OUT");
         pst.setString(11,"16:00:00  PM");
         pst.setString(12,annee);
        pst.setString(13,buss.getSelectedItem().toString());
        
         
          pst.executeUpdate();
        
              //   JOptionPane.showMessageDialog(null,"Good Morning"+" "+NAME);
    } catch (  HeadlessException | SQLException ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
               
            
            } 
        
        }
       
     }
  
        JOptionPane.showMessageDialog(null,"Transaction Saved");
    }
     public void call_in_table1(){
         int indexs[]=jTable1.getSelectedRows();
       
        for(int i=0; i < indexs.length;i++){
       //  String ROLL = (jTable1.getModel().getValueAt(indexs[i],0). toString());
        
      try{
           
             String sql="SELECT `EMP_ROLL`, `DAYS`, `DATES`, `MOIS`, `STATUT`, `OURS` FROM `backupattendance` WHERE CONTRACT='"+buss.getSelectedItem()+"' AND DATETART='"+date1.getText()+"' AND DATEEND='"+date2.getText()+"' and mois='"+mois.getText()+"'";
     //  String sql="SELECT ID, `NUM_FACTURE`, `DATE_FACTURE`, `DESCRIPTION`, `DEBIT_QTY`, `DEBIT_PU`, `DEBIT_PT`, `DEBIT_TVA`, `CREDIT_QTY`, `CREDIT_PU`, `CREDIT_PT`, `CREDIT_TVA`, `DATES`, `NUM` FROM facture_fournisseur where compte='"+jComboBox6.getSelectedItem()+"'";
       pst = con.prepareStatement(sql);
      rs= pst.executeQuery();
      
       
       jTable4.setModel(DbUtils.resultSetToTableModel(rs));
       mode=new DefaultTableModel();
       
       
     TableColumn col0=jTable4.getColumnModel().getColumn(0);
        TableColumn col1=jTable4.getColumnModel().getColumn(1);
        TableColumn col2=jTable4.getColumnModel().getColumn(2);
        TableColumn col3=jTable4.getColumnModel().getColumn(3);
        TableColumn col4=jTable4.getColumnModel().getColumn(4);
        TableColumn col5=jTable4.getColumnModel().getColumn(5);
      
        
       
       
      
       
       col0.setPreferredWidth(50);
       col1.setPreferredWidth(50);
       col2.setPreferredWidth(50);
           col3.setPreferredWidth(50);
       col4.setPreferredWidth(50);
       col5.setPreferredWidth(50);
      
      
      
       
       
      // jTable1.setModel(mode);
      
     
    }catch(SQLException ex ){
     JOptionPane.showMessageDialog(null, ex);
}
     }
     }
 //    String a1,a2,a3,a4,a5,a6,a7,a8,a9,a10,a11,a12,a13,a14,a15,a16,a17,a18,a19,a20,a21,a22,a23,a24,a25,a26,a27,a28,a29,a30,a31;
     /*
     public void save_attendance(){
          String ROLL=null,NAME=null,LNAME=null;
        int indexs[]=jTable1.getSelectedRows();
       
        for(int i=0; i < indexs.length;i++){
          ROLL = (jTable1.getModel().getValueAt(indexs[i],0). toString());
           NAME = (jTable1.getModel().getValueAt(indexs[i],1). toString());
           LNAME = (jTable1.getModel().getValueAt(indexs[i],2). toString());
           
            try{
    String sql="select * from auttendace_sheet where ROLL='"+ROLL+"'";
   
    pst=con.prepareStatement(sql);
    rs=pst.executeQuery();
            if(rs.next()){
                String sum=rs.getString("date1");
                String sums=rs.getString("date2");
                String sumss=rs.getString("mois");
              //  String sumS=rs.getString("STATUT");
                
               
                
                 date1.setText(sum);
                 date2.setText(sums);
                 mois.setText(sumss);
                // statut.setText(sumS);
                 
               
                 
              
    
            }
           
            
                
    }catch(Exception ex){JOptionPane.showMessageDialog(null,ex);}
  
     
  try {
         
        PreparedStatement pst = con.prepareStatement("INSERT INTO `auttendace_sheet`(`1`, `NAME`, `LNAME`, `FUNCTION`, `ROLL`, `2`, `3`, `4`, `5`, `6`, `7`, `8`, `9`, `10`, `11`, `12`, `13`, `14`, `15`, `16`, `17`, `18`, `19`, `20`, `21`, `22`, `23`, `24`, `25`, `26`, `27`, `28`, `29`, `30`, `31`)"
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
       
        pst.setString(1,a1);
         pst.setString(2,NAME);
         pst.setString(3,LNAME);
         pst.setString(4,"");
         pst.setString(5,ROLL);
         pst.setString(6, a2);
         pst.setString(7, a3);
          pst.setString(8,a4);
         pst.setString(9, a5);
         pst.setString(10, a6);
         pst.setString(11,a7);
         
         pst.setString(12,a8);
         pst.setString(13, a9);
         pst.setString(14,a10);
         pst.setString(15,a11);
         pst.setString(16, a12);
         pst.setString(17, a13);
          pst.setString(18,a14);
         pst.setString(19, a15);
         pst.setString(20, a16);
         pst.setString(21,a17);
         
         pst.setString(22,a18);
         pst.setString(23, a19);
         pst.setString(24,a20);
         pst.setString(25,a21);
         pst.setString(26, a22);
         pst.setString(27, a23);
          pst.setString(28,a24);
         pst.setString(29, a25);
         pst.setString(30, a26);
         pst.setString(31,a27);
         
         pst.setString(32,a28);
          pst.setString(33,a29);
         pst.setString(34, a30);
         pst.setString(35, a31);
        
        
         
          pst.executeUpdate();
        
         //        JOptionPane.showMessageDialog(null,"Data Saved");
    } catch (SQLException ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
        }
             JOptionPane.showMessageDialog(null,"Data Saved");
}
*/
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
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        buss = new Palette.Combobox();
        jTextField1 = new Palette.TextField();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jDateChooser1 = new com.alee.extended.date.WebDateField();
        jDateChooser2 = new com.alee.extended.date.WebDateField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        date1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        date2 = new javax.swing.JLabel();
        mois = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jTable1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable1);

        buss.setLabeText("Select one Project");
        buss.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                bussPopupMenuWillBecomeInvisible(evt);
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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 303, Short.MAX_VALUE)
                    .addComponent(buss, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(buss, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jTable4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable4MouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(jTable4);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jDateChooser1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jDateChooser1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jDateChooser1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jDateChooser1ActionPerformed(evt);
            }
        });

        jDateChooser2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jDateChooser2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jDateChooser2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jDateChooser2ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("From");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("To");

        jButton3.setText("Ok");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        date1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        date1.setText("Date1");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("To");

        date2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        date2.setText("Date2");

        mois.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        mois.setText("Mois");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(date1, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(date2, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(mois, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton3)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(date1)
                            .addComponent(jLabel4)
                            .addComponent(date2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(mois)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 401, Short.MAX_VALUE)
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
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

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
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
//        int row= jTable1.getSelectedRow();
  //      String date = (jTable1.getModel().getValueAt(row,0). toString());
//        ID=date;// TODO add your handling code here:
    }//GEN-LAST:event_jTable1MouseClicked

    private void jTable4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable4MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable4MouseClicked

    private void jDateChooser1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jDateChooser1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jDateChooser1ActionPerformed

    private void jDateChooser2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jDateChooser2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jDateChooser2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed


try{
 con=JavaDbConnect.dbConnect(); 
if(date1.getText().equals("")||date2.getText().equals("")){
 JOptionPane.showMessageDialog(null,"Wrong Data","Error",JOptionPane.ERROR_MESSAGE);
}else{
        shawdates_between();  
call_in_table1();
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
 
            

    }//GEN-LAST:event_jButton3ActionPerformed

    private void bussPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_bussPopupMenuWillBecomeInvisible
try{
 con=JavaDbConnect.dbConnect(); 
call_in_table2(); 
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
    }//GEN-LAST:event_bussPopupMenuWillBecomeInvisible

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
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
    }//GEN-LAST:event_jTextField1KeyReleased

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
            java.util.logging.Logger.getLogger(rh_pointage1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(rh_pointage1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(rh_pointage1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(rh_pointage1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                  WebLookAndFeel.install(true);
                new rh_pointage1().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Palette.Combobox buss;
    private javax.swing.JLabel date1;
    private javax.swing.JLabel date2;
    private javax.swing.JButton jButton3;
    private com.alee.extended.date.WebDateField jDateChooser1;
    private com.alee.extended.date.WebDateField jDateChooser2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable4;
    private Palette.TextField jTextField1;
    private javax.swing.JLabel mois;
    // End of variables declaration//GEN-END:variables
}
