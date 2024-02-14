/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intreprisemanagementsystem;

import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author DOSHE
 */
public class salary_cal extends javax.swing.JInternalFrame {

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
    public salary_cal() {
        initComponents();
              con=JavaDbConnect.dbConnect();
         this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI bui= (BasicInternalFrameUI) this.getUI();
       bui.setNorthPane(null);
        
        jTable1.enable(false);
        webDateField1.setDate(new Date());
    }
      String  NAME,LNAME,TITLE,ROLL = null,Inss,CURRENCY;
       Double CHILD,Advance,DAYIN,DAYOUT,DAYM,EXTRADAY,RESTING,ADVANCE,SALAIRE,Ipr,Inpp,Onem,TRANSPORT,ALL_FAMILLE,ACCOMMODATION,Ipr_2pr,Cnsstous,Cnsstou13,total_net,total_brut,Salaire_Brut,Net_a_paye,Total_sal; 
       String Curent,Date_in,Date_Out,Child,Cnss,CONTRACT;
     public void call_in_table(){
     
      try{
           
            
             //String sql="SELECT `NAME`, `LNAME`, `ROLL`  FROM `contract` WHERE ACTIVE='Active' and SALAIRE is not null and TRIM(SALAIRE)<>''";
      String sql = "SELECT Distinct (EMP_ROLL) as 'ROLL No',EMP_NAME as 'Employees Name',EMP_LNAME as 'Employees L.Name' FROM  backupattendance WHERE MOIS = '"+jComboBox2.getSelectedItem()+"'";
       //  SELECT `ID`, `EMP_NAME`, `EMP_LNAME`, `EMP_POST`, `EMP_ROLL`, `DAYS`, `DATES`, `DATETART`, `DATEEND`, `MOIS`, `STATUT` FROM `backupattendance` WHERE 1
         
       pst = con.prepareStatement(sql);
      rs= pst.executeQuery();
      
       
       jTable1.setModel(DbUtils.resultSetToTableModel(rs));
       mode=new DefaultTableModel();
       
       
        TableColumn col0=jTable1.getColumnModel().getColumn(0);
        TableColumn col1=jTable1.getColumnModel().getColumn(1);
        TableColumn col2=jTable1.getColumnModel().getColumn(2);
        
       
       
      
       
       col0.setPreferredWidth(150);
       col1.setPreferredWidth(130);
       col2.setPreferredWidth(100);
       
      
      
       
       
      // jTable1.setModel(mode);
       
     
    }catch(SQLException ex ){
     JOptionPane.showMessageDialog(null, ex);
}
     
     }
    
    
      public void call_in_tablesearch(){
     
    try{
           
             String sql="SELECT   NOMS AS 'NAME',LNAME AS 'LAST NAME', ROLL FROM active_employee  WHERE "+jComboBox1.getSelectedItem()+" LIKE '"+jTextField1.getText()+"%' and Locks='Active'";
     //  String sql="SELECT ID, `NUM_FACTURE`, `DATE_FACTURE`, `DESCRIPTION`, `DEBIT_QTY`, `DEBIT_PU`, `DEBIT_PT`, `DEBIT_TVA`, `CREDIT_QTY`, `CREDIT_PU`, `CREDIT_PT`, `CREDIT_TVA`, `DATES`, `NUM` FROM facture_fournisseur where compte='"+jComboBox6.getSelectedItem()+"'";
       pst = con.prepareStatement(sql);
      rs= pst.executeQuery();
      
       
       jTable1.setModel(DbUtils.resultSetToTableModel(rs));
       mode=new DefaultTableModel();
       
       
        TableColumn col0=jTable1.getColumnModel().getColumn(0);
        TableColumn col1=jTable1.getColumnModel().getColumn(1);
        TableColumn col2=jTable1.getColumnModel().getColumn(2);
        
       
       
      
       
       col0.setPreferredWidth(150);
       col1.setPreferredWidth(130);
       col2.setPreferredWidth(100);
      
      
      
      
       
       
      // jTable1.setModel(mode);
       
     
    }catch(SQLException ex ){
     JOptionPane.showMessageDialog(null, ex);
}
     
      }
    Double DAYS,DAYS_IN,DAYS_OUT,DAYS_M,RESTING_DAY ;
    
       public void show_photo_from_db()
               
   {
            try{
          int row= jTable1.getSelectedRow();
          String Table_click = (jTable1.getModel().getValueAt(row,0). toString());
          String sql = "SELECT * FROM  backupattendance WHERE EMP_ROLL = '"+Table_click+"'";
          pst = con.prepareStatement(sql);
          rs=pst.executeQuery();
          if(rs.next()){
          //  SELECT `ID`, `EMP_NAME`, `EMP_LNAME`, `EMP_POST`, `EMP_ROLL`, `DAYS`, `DATES`, `DATETART`, `DATEEND`, `MOIS`, `STATUT` FROM `backupattendance` WHERE 1
            NAME=rs.getString("EMP_NAME");
                 nom.setText(NAME);
                 
                 LNAME=rs.getString("EMP_LNAME");
                lname.setText(LNAME);
                 
                 ROLL=rs.getString("EMP_ROLL");
                 roll.setText(ROLL);
                 
                // String sum22=rs.getString("STATUT");
                // contract.setText(sum22);
                 
                 TITLE=rs.getString("EMP_POST");
                 title.setText(TITLE);
                 
                 }
         
    }catch(SQLException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }
            
             try{
          int row= jTable1.getSelectedRow();
          String Table_click = (jTable1.getModel().getValueAt(row,0). toString());
          String sql = "SELECT * FROM  contract WHERE ROLL = '"+Table_click+"' and  ACTIVE='Active'";
          pst = con.prepareStatement(sql);
          rs=pst.executeQuery();
          if(rs.next()){
            
          //SALAIRE,Curent,Date_in,Date_Out
            Curent=rs.getString("DATE_IN");
                 curent.setText(Curent);
                 
                   Date_in=rs.getString("DATE_IN");
                 date_in.setText(Date_in);
                 
                 Date_Out=rs.getString("DATE_OUT");
                date_out.setText(Date_Out);
                
                 SALAIRE=rs.getDouble("SALAIRE");
               sal.setText(""+SALAIRE);
               
               
            CONTRACT=rs.getString("CONTRACT");
            contract.setText(CONTRACT);

                 
                 }
         
    }catch(SQLException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }
             
              try{
          int row= jTable1.getSelectedRow();
          String Table_click = (jTable1.getModel().getValueAt(row,0). toString());
          String sql = "SELECT * FROM  inss WHERE ROLL = '"+Table_click+"'";
          pst = con.prepareStatement(sql);
          rs=pst.executeQuery();
          if(rs.next()){
            
          //`ID`, `NOMS`, `LNAME`, `ROLL`, `ADDRESS`, `TEL`, `MAIL`, `GENDER`, `TITLE`, `DEPARTEMENT`, `PHOTOS`, `LOCKS`
             Inss=rs.getString("inss");
                inss.setText(Inss);
                 
                  
                 
                 }
         
    }catch(SQLException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }
              
              
                try{
          int row= jTable1.getSelectedRow();
          String Table_click = (jTable1.getModel().getValueAt(row,0). toString());
         String sql = "SELECT SUM(MOTANT_PR) FROM  programation_avance_sur_sal WHERE NUM = '"+Table_click+"' and MOIS='"+jComboBox2.getSelectedItem()+"'";
          pst = con.prepareStatement(sql);
          rs=pst.executeQuery();
          if(rs.next()){
            
          //`ID`, `NOMS`, `LNAME`, `ROLL`, `ADDRESS`, `TEL`, `MAIL`, `GENDER`, `TITLE`, `DEPARTEMENT`, `PHOTOS`, `LOCKS`
             Advance=rs.getDouble("SUM(MOTANT_PR)");
                advance.setText(""+Advance);
                 
                  if(advance.getText().isEmpty()){
                  advance.setText("0");
                  }
                 
                 }
         
    }catch(SQLException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }
              
               try{
          int row= jTable1.getSelectedRow();
          String Table_click = (jTable1.getModel().getValueAt(row,0). toString());
          String sql = "SELECT COUNT(NAMES) FROM  all_fam_listchild WHERE ROLL = '"+Table_click+"'";
          pst = con.prepareStatement(sql);
          rs=pst.executeQuery();
          if(rs.next()){
            
          //`ID`, `NOMS`, `LNAME`, `ROLL`, `ADDRESS`, `TEL`, `MAIL`, `GENDER`, `TITLE`, `DEPARTEMENT`, `PHOTOS`, `LOCKS`
             Child=rs.getString("COUNT(NAMES)");
                child.setText(Child);
                 
                   if(child.getText().isEmpty()){
                  child.setText("0");
                  }
                 
                 }
         
    }catch(SQLException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }
            
               
               
               try{
                   
                   
          int row= jTable1.getSelectedRow();
          String Table_click = (jTable1.getModel().getValueAt(row,0). toString());
          String sql = "SELECT sum(days) FROM  backupattendance WHERE EMP_ROLL = '"+Table_click+"' and MOIS='"+jComboBox2.getSelectedItem()+"'";
          pst = con.prepareStatement(sql);
          rs=pst.executeQuery();
          if(rs.next()){
            
          //`ID`, `NOMS`, `LNAME`, `ROLL`, `ADDRESS`, `TEL`, `MAIL`, `GENDER`, `TITLE`, `DEPARTEMENT`, `PHOTOS`, `LOCKS`
             DAYS=rs.getDouble("sum(DAYS)");
             
             DAYS_IN=DAYS/2;
             DAYS_OUT=26-DAYS_IN;
             DAYS_M=0.0;
             RESTING_DAY=0.0;
             
             
             days_in.setText(""+DAYS_IN);
             days_out.setText(""+DAYS_OUT);
             days_m.setText("0");
             resting_days.setText("0");
             currency.setText("USD");
               
                 
                  
                 
                 }
         
    }catch(SQLException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }
                   
   }
       Double Salaire_brute,Transport,Accommodation,All_Fam,Accouragement,Autre;
       Double Inss3pr, Oneme,Salairetot,AVD,Ret,Sal_Net;
       public void calculate(){
           call_taxe_et_avantage();
           call_currency();
       String d;
           //>>> Salire BRUTE
           
           Double a =Double.parseDouble(sal.getText());
           Double b =Double.parseDouble(days_in.getText());
           Salaire_brute=(a/26)*b;
           d= String.format("%.3f",Salaire_brute);
          sal_brut.setText(d);
           
           //>>> Transport
           
           Transport=((TRANS*4)*b)/AUSD;
            d= String.format("%.3f",Transport);
           trans.setText(d);
           
           //>>> Accommodation
         
           Accommodation=Salaire_brute/ACCOM;
           d= String.format("%.3f",Accommodation);
           accom.setText(d);
           
         
           
           //>>> Allocation Familial
       
           Double childs= Double.parseDouble(child.getText());
           All_Fam=(ALL_FAM/AUSD)*childs;
           d= String.format("%.3f",All_Fam);
           all_fam.setText(d);
           
           //>>> Accouragement
          
           Accouragement=ACCOUR;
           Autre=AUTRES;
            d= String.format("%.3f",Accouragement);
           jTextField21.setText(d);
            d= String.format("%.3f",Autre);
           jTextField22.setText(d);
           
           //>>>>>>>>>>>>>>>>les retenues<<<<<<<<<<<<<<<<<<<
           
            //>>> INSS
           
           Inss3pr=(Salaire_brute*INSS)/100;
            d= String.format("%.3f",Inss3pr);
           cnss.setText(d);
           
            Inss3pr=(Salaire_brute*CNSS)/100;
            d= String.format("%.3f",Inss3pr);
           cnsstou13.setText(d);
           
            Inss3pr=(Salaire_brute*(INSS+CNSS))/100;
            d= String.format("%.3f",Inss3pr);
          cnsstous.setText(d);
           
           //>>> INPP
            
           Inpp=(Salaire_brute*INPP)/100;
            d= String.format("%.3f",Inpp);
          inpps.setText(d);
          
          //>>> ONME
           
           Oneme=(Salaire_brute*ONEME)/100;
           d= String.format("%.3f",Oneme);
          onem.setText(d);
          
          //>>>IPR
        //  Double Ipr;
          if(Salaire_brute < TO1){
              Ipr=0.000;
               d= String.format("%.3f",Ipr);
          ipr.setText(d);
          
          }else if(Salaire_brute < TO2){
          Ipr=((Salaire_brute-DIFF1)*IPR2)/100;
          d= String.format("%.3f",Ipr);
          ipr.setText(d);
              
              
          }else if(Salaire_brute < TO3){
          Ipr=((Salaire_brute-DIFF2)*IPR3)/100;
          d= String.format("%.3f",Ipr);
          ipr.setText(d);
          }else if(Salaire_brute < TO4){
          Ipr=((Salaire_brute-DIFF3)*IPR4)/100;
          d= String.format("%.3f",Ipr);
          ipr.setText(d);
          }
          
          //>>>>>>> 2%IPR
          Double Iprp;
          if(!child.getText().equals("0")){
          Double ipr2;
          ipr2=(Ipr * 2)/100;
          d= String.format("%.3f",ipr2);
          ipr_2pr.setText(d);
          Iprp=Ipr-ipr2;
          
          d= String.format("%.3f",Iprp);
          ipr.setText(d);
          
          
          ///>>>>>>>>>SALAIRE<<<<<<<<<<<
        
           AVD=  (Transport + Accommodation+  All_Fam + Accouragement + Autre);
           
          
           Ret=Inss3pr+Iprp;
           
           Salairetot=Salaire_brute+AVD;
           d= String.format("%.3f",Salairetot);
          total_sal.setText(d);
          
           Sal_Net= Salairetot-Ret;
                   d= String.format("%.3f",Sal_Net);
          net_a_paye.setText(d);
           
           
          }else{
          
          ///>>>>>>>>>SALAIRE<<<<<<<<<<<
        
           AVD=  (Transport + Accommodation+  All_Fam + Accouragement + Autre);
           
          
           Ret=Inss3pr+Ipr;
           
           Salairetot=Salaire_brute+AVD;
           d= String.format("%.3f",Salairetot);
          total_sal.setText(d);
          
           Sal_Net= Salairetot-Ret;
                   d= String.format("%.3f",Sal_Net);
          net_a_paye.setText(d);
           
            ipr_2pr.setText("0.00");
          
          
          }
          
           
           
           
         
           /*
            IPR1 = rs.getDouble("POUR");
              FROM1 = rs.getDouble("FROM_USD");
              TO1 = rs.getDouble("TO_USD");
              DIFF1 = rs.getDouble("DIFFENCE");
           */
       
       }
       
       //>>> Select all
      
       
       
        Double AUSD;
       
    public void call_currency(){
    
     try{
            String sqls="SELECT `VUSD` FROM `currency`";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
            while(rs.next()){
              AUSD = rs.getDouble("VUSD");
              
              
             
              }
            }
        catch(Exception ex){
         JOptionPane.showMessageDialog(null, ex);}
        
    } 
       
       
       Double INSS,IPR1,IPR2,IPR3,IPR4,ONEME,ADV,INPP,CNSS;
       Double TRANS,ACCOM,ALL_FAM,ACCOUR,AUTRES;
       Double FROM1,TO1,FROM2,TO2,FROM3,TO3,FROM4,TO4;
       Double DIFF1,DIFF2,DIFF3,DIFF4;
      
       
       public void call_taxe_et_avantage(){
       
            try{
            String sqls="SELECT `INSS`, `INPP`, `ONME`, `CNSS` FROM `retenue` WHERE ID='1'";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
            while(rs.next()){
             
              
              INSS = rs.getDouble("INSS");
              INPP = rs.getDouble("INPP");
              ONEME = rs.getDouble("ONME"); 
            CNSS = rs.getDouble("CNSS");}
            }
        catch(SQLException ex){
         JOptionPane.showMessageDialog(null, ex);}
            
            
            //IPR
             try{
            String sqls="SELECT `FROM_USD`, `TO_USD`, `DIFFENCE`, `POUR` FROM `ipr` WHERE ID='1'";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
            while(rs.next()){ 
              IPR1 = rs.getDouble("POUR");
              FROM1 = rs.getDouble("FROM_USD");
              TO1 = rs.getDouble("TO_USD");
              DIFF1 = rs.getDouble("DIFFENCE");
               }
            }
        catch(SQLException ex){
         JOptionPane.showMessageDialog(null, ex);}
             
              try{
            String sqls="SELECT `FROM_USD`, `TO_USD`,`DIFFENCE`, `POUR` FROM `ipr` WHERE ID='2'";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
            while(rs.next()){
             
              
              IPR2 = rs.getDouble("POUR");
               FROM2 = rs.getDouble("FROM_USD");
              TO2 = rs.getDouble("TO_USD");
              DIFF2 = rs.getDouble("DIFFENCE");
               }
            }
        catch(SQLException ex){
         JOptionPane.showMessageDialog(null, ex);}
    
               try{
            String sqls="SELECT `FROM_USD`, `TO_USD`,`DIFFENCE`,`POUR` FROM `ipr` WHERE ID='3'";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
            while(rs.next()){
             
              
              IPR3 = rs.getDouble("POUR");
               FROM3 = rs.getDouble("FROM_USD");
              TO3 = rs.getDouble("TO_USD");
              DIFF3 = rs.getDouble("DIFFENCE");
               }
            }
        catch(SQLException ex){
         JOptionPane.showMessageDialog(null, ex);}
               
                try{
            String sqls="SELECT `FROM_USD`, `TO_USD`,`DIFFENCE`,`POUR` FROM `ipr` WHERE ID='4'";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
            while(rs.next()){
             
              
              IPR4 = rs.getDouble("POUR");
               FROM4 = rs.getDouble("FROM_USD");
              //TO4 = rs.getDouble("TO_USD");
              DIFF4 = rs.getDouble("DIFFENCE");
               }
            }
        catch(SQLException ex){
         JOptionPane.showMessageDialog(null, ex);}
                
                 try{
            String sqls="SELECT `TRANSPORT`, `ACCOMMODATION`, `ALL_FAM`, `ACCOURAGEMENT`, `AUTRES` FROM `avantage` WHERE ID='1'";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
            while(rs.next()){
             
              
              TRANS = rs.getDouble("TRANSPORT");
              ACCOM = rs.getDouble("ACCOMMODATION");
              ALL_FAM = rs.getDouble("ALL_FAM"); 
               ACCOUR = rs.getDouble("ACCOURAGEMENT");
              AUTRES = rs.getDouble("AUTRES");
            
            }
            }
        catch(SQLException ex){
         JOptionPane.showMessageDialog(null, ex);}
       
       }
    
        Double iprbrute;
//       public void big_salary_calculation()
       
       
       
       
       //>>>>>>save All
       
       //  public void big_salary_calculationAll()
       
       public void numid()
     {
         try{
            String sql="SELECT NUM FROM salaire ORDER BY NUM DESC LIMIT 1";
             pst=con.prepareStatement(sql);
             rs=pst.executeQuery();
             if(rs.next()){
                 String rl=rs.getString("NUM");
                 int ln=rl.length();
                 String stxt=rl.substring(0,10);
                 String snum=rl.substring(10,ln);
                 int n = Integer.parseInt(snum);
                 n++;
                 snum=Integer.toString(n);
                rolls=stxt+snum;
                 
                 
                 
             }else{
                 //rolls="FICHE/EB/2018/1";
                rolls="SAL_SHEET_1001";
                
             }
              }catch(Exception e){
             JOptionPane.showMessageDialog(null, e); 
             
         }
         }
  String CHECK;
      public void check(){
      
        try{
            String sqls="SELECT count(roll) FROM `salaire` WHERE ROLL='"+roll.getText()+"' and 	MONTH='"+jComboBox2.getSelectedItem()+"'";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
            while(rs.next()){
             
              
             CHECK = rs.getString("count(roll)");
              
               }
            }
        catch(SQLException ex){
         JOptionPane.showMessageDialog(null, ex);}
      
      }
       
public void saves(){
             check();
             numid();
             
             if(CHECK.equals("0")){
 
 try{
    String sql="INSERT INTO `salaire`(`NAME`, `LNAME`, `TITLE`, `CONTRACT`, `ROLL`, `DATE_IN`, `CURRENT_DATES`, `DATE_OUT`, `INSS`, `DAYS_IN`, `DAYS_OFF`, `DAYS_SICK`, `RESTING_DAYS`, `SAILAIR_BASE`, `CHILD`, `CURENCY`, `TRANSPORT`, `ACCOMODATION`, `ALL_FAM`, `EXTRA_HOUR`, `MOTIVATION`, `OTHER`, `CNSS`, `IPR`, `INPP`, `ONEM`, `ADV`, `SALAIRE_BRUTE`, `SALAIRE_NET_BASE`, `SALAIRE_NET`,`MONTH`,`NUM`,`PRINT`,`CNSS_13`,`CNSS_TOUS`,`DATES`,`ANNEE`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"; 
    pst=con.prepareStatement(sql);
    
    pst.setString(1,NAME);
    pst.setString(2,LNAME);
    pst.setString(3,TITLE);
    pst.setString(4,contract.getText());
    pst.setString(5,ROLL);
    pst.setString(6,Date_in);
     pst.setString(7,Curent);
     pst.setString(8,Date_Out);
    
        
      pst.setString(9,Inss);
       pst.setDouble(10,DAYS_IN);
       pst.setDouble(11,DAYS_OUT);
       pst.setDouble(12,DAYS_M);
       pst.setDouble(13,RESTING_DAY);
        pst.setDouble(14,SALAIRE);
        pst.setString(15,Child);
        
        pst.setString(16,"USD");
        
        // Salaire_brute,Transport,Accommodation,All_Fam,Accouragement,Autre;

        
    pst.setDouble(17,Transport);
    pst.setDouble(18,Accommodation);
    pst.setDouble(19,All_Fam);
    
    
    pst.setString(20,"0");
    pst.setDouble(21,Accouragement);
     pst.setDouble(22,Autre);
     
    //Double Inss3pr, Oneme,Salairetot,AVD,Ret,Sal_Net; 
     pst.setDouble(23,Inss3pr);
     
      pst.setDouble(24,Ipr);
       pst.setDouble(25,Inpp);
        pst.setDouble(26,Oneme);
        pst.setDouble(27,Advance);
        pst.setDouble(28,Salaire_brute);
        
        
        pst.setDouble(29,Salairetot);
        pst.setDouble(30,Sal_Net);
         pst.setString(31,jComboBox2.getSelectedItem().toString());
         pst.setString(32,rolls);
          pst.setString(33,"Print");
         
         pst.setString(34,cnsstou13.getText());
        pst.setString(35,cnsstous.getText());
        
         pst.setString(36,webDateField1.getText());
         
         SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy");
         String Anne = dateFormat2.format(webDateField1.getDate());
        pst.setString(37,Anne);
    
    pst.executeUpdate();
     // JOptionPane.showMessageDialog(null,"Datas Saved");
    }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}
 
 
 
 
 
         }else{
            JOptionPane.showMessageDialog(null,"Please you can not Pay "+nom.getText()+"  Again"); 
             
             }
         }
         
         
        
      public void foreach(){
       
                    try{
           rs = pst.executeQuery("SELECT distinct(EMP_ROLL),EMP_NAME,EMP_LNAME,EMP_POST FROM   backupattendance where MOIS='"+jComboBox2.getSelectedItem()+"'");
           
            while(rs.next()){ 
               NAME=rs.getString("EMP_NAME");
               LNAME=rs.getString("EMP_LNAME");
               ROLL=rs.getString("EMP_ROLL");
               TITLE=rs.getString("EMP_POST");
               
           //  JOptionPane.showMessageDialog(null, ROLL);  
             
            
               
                 /*
          String sql = "SELECT * FROM  contract WHERE ROLL='"+ROLL+"' and  ACTIVE='Active'";
          pst = con.prepareStatement(sql);
          rs=pst.executeQuery();
          if(rs.next()){
            
          //`ID`, `NOMS`, `LNAME`, `ROLL`, `ADDRESS`, `TEL`, `MAIL`, `GENDER`, `TITLE`, `DEPARTEMENT`, `PHOTOS`, `LOCKS`
             Curent=rs.getDouble("DATE_IN");
                
             Date_in=rs.getDouble("DATE_IN");
                
                 
             Date_Out=rs.getDouble("DATE_OUT");
               
                
           SALAIRE=rs.getDouble("SALAIRE");
               
                
                 }
         
    }catch(SQLException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }
                    
            /* 
              try{
          String sql = "SELECT * FROM  inss WHERE ROLL='"+ROLL+"' ";
          pst = con.prepareStatement(sql);
          rs=pst.executeQuery();
          if(rs.next()){
            
          //`ID`, `NOMS`, `LNAME`, `ROLL`, `ADDRESS`, `TEL`, `MAIL`, `GENDER`, `TITLE`, `DEPARTEMENT`, `PHOTOS`, `LOCKS`
            Inss=rs.getString("inss");
               
                 
                  
                 
                 }
         
    }catch(SQLException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }
              
              
                try{
         String sql = "SELECT SUM(MOTANT_PR) FROM  programation_avance_sur_sal WHERE NUM = '"+ROLL+"' and MOIS='"+jComboBox2.getSelectedItem()+"'";
          pst = con.prepareStatement(sql);
          rs=pst.executeQuery();
          if(rs.next()){
            
          //`ID`, `NOMS`, `LNAME`, `ROLL`, `ADDRESS`, `TEL`, `MAIL`, `GENDER`, `TITLE`, `DEPARTEMENT`, `PHOTOS`, `LOCKS`
             ADVANCE=rs.getDouble("SUM(MOTANT_PR)");
               
                 
                 }
         
    }catch(SQLException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }
              
               try{
          String sql = "SELECT COUNT(NAMES) FROM  all_fam_listchild WHERE ROLL = '"+ROLL+"'";
          pst = con.prepareStatement(sql);
          rs=pst.executeQuery();
          if(rs.next()){
            
          //`ID`, `NOMS`, `LNAME`, `ROLL`, `ADDRESS`, `TEL`, `MAIL`, `GENDER`, `TITLE`, `DEPARTEMENT`, `PHOTOS`, `LOCKS`
           CHILD=rs.getDouble("COUNT(NAMES)");        
                 }
         
    }catch(SQLException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }
            
               
               
               try{
                   Double DAYS;
                   Double DAYS_IN;
                   Double DAYS_OUT;
                   
        
          String sql = "SELECT sum(days) FROM  backupattendance WHERE EMP_ROLL = '"+ROLL+"' and MOIS='"+jComboBox2.getSelectedItem()+"'";
          pst = con.prepareStatement(sql);
          rs=pst.executeQuery();
          if(rs.next()){
            
          //`ID`, `NOMS`, `LNAME`, `ROLL`, `ADDRESS`, `TEL`, `MAIL`, `GENDER`, `TITLE`, `DEPARTEMENT`, `PHOTOS`, `LOCKS`
             DAYS=rs.getDouble("sum(DAYS)");
             
             DAYS_IN=DAYS/2;
             DAYS_OUT=26-DAYS_IN;
             
             DAYIN=(DAYS_IN);
             DAYOUT=(DAYS_OUT);
             DAYM=0.0;
            RESTING=0.0;
             CURRENCY=("USD");
               
                 
                  
                 
                 }
         
    }catch(SQLException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }
                 
               
             
             */  
               
               }
            
            }
        catch(SQLException ex){
         JOptionPane.showMessageDialog(null, ex);}
       
                    
          /*          
                    
                    
                    ResultSet rs = stmt.executeQuery(
    "SELECT region_name, zips FROM REGIONS");

while (rs.next()) {
    Array z = rs.getArray("ZIPS");
    String[] zips = (String[])z.getArray();
    for (int i = 0; i < zips.length; i++) {
        if (!ZipCode.isValid(zips[i])) {
            // ...
            // Code to display warning
        }
    }
}
                    */
       }
public void pay_all(){
       
        int indexs[]=jTable1.getSelectedRows();
       
        for(int i=0; i < indexs.length;i++){
          String Table_click = (jTable1.getModel().getValueAt(indexs[i],0). toString());
           JOptionPane.showMessageDialog(null,Table_click);
        }
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
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        nom = new javax.swing.JTextField();
        lname = new javax.swing.JTextField();
        title = new javax.swing.JTextField();
        contract = new javax.swing.JTextField();
        roll = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        date_in = new javax.swing.JTextField();
        curent = new javax.swing.JTextField();
        date_out = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        inss = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        days_in = new javax.swing.JTextField();
        days_out = new javax.swing.JTextField();
        days_m = new javax.swing.JTextField();
        resting_days = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        sal = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        currency = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        child = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jComboBox1 = new javax.swing.JComboBox<>();
        jTextField1 = new javax.swing.JTextField();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel30 = new javax.swing.JLabel();
        webDateField1 = new com.alee.extended.date.WebDateField();
        jButton1 = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        trans = new javax.swing.JTextField();
        accom = new javax.swing.JTextField();
        all_fam = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jTextField20 = new javax.swing.JTextField();
        jTextField21 = new javax.swing.JTextField();
        jTextField22 = new javax.swing.JTextField();
        jPanel10 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        cnss = new javax.swing.JTextField();
        ipr = new javax.swing.JTextField();
        inpps = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jTextField26 = new javax.swing.JTextField();
        advance = new javax.swing.JTextField();
        onem = new javax.swing.JTextField();
        jPanel11 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        sal_brut = new javax.swing.JTextField();
        total_sal = new javax.swing.JTextField();
        net_a_paye = new javax.swing.JTextField();
        jPanel12 = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        cnsstou13 = new javax.swing.JTextField();
        cnsstous = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        ipr_2pr = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Personel Information", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("Name:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("L Name:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("Title:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("Contract:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setText("Roll:");

        nom.setEditable(false);
        nom.setBackground(new java.awt.Color(240, 240, 241));
        nom.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        lname.setEditable(false);
        lname.setBackground(new java.awt.Color(240, 240, 241));
        lname.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        title.setEditable(false);
        title.setBackground(new java.awt.Color(240, 240, 241));
        title.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        contract.setEditable(false);
        contract.setBackground(new java.awt.Color(240, 240, 241));
        contract.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        roll.setEditable(false);
        roll.setBackground(new java.awt.Color(240, 240, 241));
        roll.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nom, javax.swing.GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)
                    .addComponent(lname)
                    .addComponent(title)
                    .addComponent(contract)
                    .addComponent(roll))
                .addGap(10, 10, 10))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(nom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(contract, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(roll, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Contract", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setText("Date In:");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel7.setText("Current C:");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel8.setText("Date Out:");

        date_in.setEditable(false);
        date_in.setBackground(new java.awt.Color(240, 240, 241));
        date_in.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        curent.setEditable(false);
        curent.setBackground(new java.awt.Color(240, 240, 241));
        curent.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        date_out.setEditable(false);
        date_out.setBackground(new java.awt.Color(240, 240, 241));
        date_out.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(date_in)
                    .addComponent(curent)
                    .addComponent(date_out))
                .addGap(10, 10, 10))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(date_in, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(curent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(date_out, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "CNSS Affiliation", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N

        inss.setEditable(false);
        inss.setBackground(new java.awt.Color(240, 240, 241));
        inss.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(inss)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(inss, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Salary/ Attendance Info", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel9.setText("Days In:");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel10.setText("Days Off:");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel11.setText("Days Sick:");

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel12.setText("Resting Days:");

        days_in.setBackground(new java.awt.Color(0, 204, 0));
        days_in.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        days_in.setForeground(new java.awt.Color(255, 255, 255));

        days_out.setEditable(false);
        days_out.setBackground(new java.awt.Color(204, 0, 0));
        days_out.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        days_out.setForeground(new java.awt.Color(255, 255, 255));

        days_m.setEditable(false);
        days_m.setBackground(new java.awt.Color(240, 240, 241));
        days_m.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        resting_days.setEditable(false);
        resting_days.setBackground(new java.awt.Color(240, 240, 241));
        resting_days.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel13.setText("Base Salary:");

        sal.setEditable(false);
        sal.setBackground(new java.awt.Color(240, 240, 241));
        sal.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel14.setText("Currency:");

        currency.setEditable(false);
        currency.setBackground(new java.awt.Color(240, 240, 241));
        currency.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jLabel31.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel31.setText("Child:");

        child.setEditable(false);
        child.setBackground(new java.awt.Color(240, 240, 241));
        child.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(days_in)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(days_out, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(sal, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel11)
                    .addComponent(jLabel31))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(days_m, javax.swing.GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE)
                    .addComponent(child))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel14)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(resting_days)
                    .addComponent(currency, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(days_out, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(days_in, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(days_m, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(resting_days, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(sal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(currency, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel31)
                    .addComponent(child, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTable1.setBackground(new java.awt.Color(240, 240, 240));
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
        jScrollPane1.setViewportView(jTable1);

        jComboBox1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "NOMS", "LNAME", "ROLL" }));

        jTextField1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });

        jComboBox2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "January", "February", "March", "April", "May", "June", "July", "August", "September", "Octomber", "November", "December", "13-ième mois" }));
        jComboBox2.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                jComboBox2PopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        jLabel30.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel30.setText("Payment For");

        webDateField1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 465, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel30))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField1)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(webDateField1, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton1)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel30)
                    .addComponent(webDateField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addGap(10, 10, 10))
        );

        jPanel8.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Advantage", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel15.setText("Transport:");

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel16.setText("Accommodation:");

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel17.setText("Family Allocation:");

        trans.setEditable(false);
        trans.setBackground(new java.awt.Color(240, 240, 241));
        trans.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        trans.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                transActionPerformed(evt);
            }
        });

        accom.setEditable(false);
        accom.setBackground(new java.awt.Color(240, 240, 241));
        accom.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        all_fam.setEditable(false);
        all_fam.setBackground(new java.awt.Color(240, 240, 241));
        all_fam.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        all_fam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                all_famActionPerformed(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel18.setText("Extra Hours:");

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel19.setText("Motivation:");

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel20.setText("Others:");

        jTextField20.setEditable(false);
        jTextField20.setBackground(new java.awt.Color(240, 240, 241));
        jTextField20.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jTextField21.setEditable(false);
        jTextField21.setBackground(new java.awt.Color(240, 240, 241));
        jTextField21.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jTextField22.setEditable(false);
        jTextField22.setBackground(new java.awt.Color(240, 240, 241));
        jTextField22.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16)
                    .addComponent(jLabel17)
                    .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(all_fam, javax.swing.GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)
                    .addComponent(accom)
                    .addComponent(trans))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18)
                    .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField20, javax.swing.GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
                    .addComponent(jTextField21)
                    .addComponent(jTextField22))
                .addGap(11, 11, 11))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(trans, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18)
                    .addComponent(jTextField20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(accom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19)
                    .addComponent(jTextField21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(all_fam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20)
                    .addComponent(jTextField22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Taxes", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel21.setText("CNSS:");

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel22.setText("IPR:");

        jLabel23.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel23.setText("INPP:");

        cnss.setEditable(false);
        cnss.setBackground(new java.awt.Color(240, 240, 241));
        cnss.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        ipr.setEditable(false);
        ipr.setBackground(new java.awt.Color(240, 240, 241));
        ipr.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        inpps.setEditable(false);
        inpps.setBackground(new java.awt.Color(240, 240, 241));
        inpps.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jLabel24.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel24.setText("ONME:");

        jLabel25.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel25.setText("ADV:");

        jLabel26.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel26.setText("Otrs:");

        jTextField26.setEditable(false);
        jTextField26.setBackground(new java.awt.Color(240, 240, 241));
        jTextField26.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        advance.setEditable(false);
        advance.setBackground(new java.awt.Color(240, 240, 241));
        advance.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        advance.setText("0");

        onem.setEditable(false);
        onem.setBackground(new java.awt.Color(240, 240, 241));
        onem.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cnss, javax.swing.GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE)
                    .addComponent(ipr)
                    .addComponent(inpps))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel24, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel26, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(onem)
                    .addComponent(advance)
                    .addComponent(jTextField26, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel24)
                            .addComponent(onem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, 0)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel25)
                            .addComponent(advance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, 0)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel26)
                            .addComponent(jTextField26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel21)
                            .addComponent(cnss, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, 0)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel22)
                            .addComponent(ipr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, 0)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel23)
                            .addComponent(inpps, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Salary Payment", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N

        jLabel27.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel27.setText("Brut Salary:");

        jLabel28.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel28.setText("Total  Salary:");

        jLabel29.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel29.setText("Net Salary:");

        sal_brut.setEditable(false);
        sal_brut.setBackground(new java.awt.Color(240, 240, 241));
        sal_brut.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        total_sal.setEditable(false);
        total_sal.setBackground(new java.awt.Color(240, 240, 241));
        total_sal.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        net_a_paye.setEditable(false);
        net_a_paye.setBackground(new java.awt.Color(0, 204, 0));
        net_a_paye.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        net_a_paye.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel28)
                    .addComponent(jLabel27, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel29)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(net_a_paye, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
                    .addComponent(total_sal)
                    .addComponent(sal_brut))
                .addGap(11, 11, 11))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(sal_brut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(total_sal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(net_a_paye, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Personel Payed", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N

        jLabel32.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel32.setText("13% CNSS");

        cnsstou13.setEditable(false);
        cnsstou13.setBackground(new java.awt.Color(240, 240, 241));
        cnsstou13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        cnsstou13.setText("0.00");

        cnsstous.setEditable(false);
        cnsstous.setBackground(new java.awt.Color(240, 240, 241));
        cnsstous.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        cnsstous.setText("0.00");

        jLabel33.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel33.setText("2%_IPR:");

        ipr_2pr.setEditable(false);
        ipr_2pr.setBackground(new java.awt.Color(240, 240, 241));
        ipr_2pr.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        ipr_2pr.setText("0.00");

        jLabel34.setText("18% CNSS");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel33)
                    .addComponent(jLabel32)
                    .addComponent(jLabel34))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cnsstous)
                    .addComponent(cnsstou13)
                    .addComponent(ipr_2pr, javax.swing.GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32)
                    .addComponent(cnsstou13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cnsstous, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel34))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33)
                    .addComponent(ipr_2pr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jMenu3.setText("X");
        jMenu3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jMenu3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu3MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu3);

        jMenu1.setText("File");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setText("New");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem4.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem4.setText("Calculate");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem4);

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem2.setText("Save");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem3.setText("Delete");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuItem5.setText("Calculate All");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem5);

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
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 11, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jMenu3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu3MouseClicked
       // TODO add your handling code here:
    }//GEN-LAST:event_jMenu3MouseClicked

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
call_in_tablesearch();        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1KeyReleased

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
show_photo_from_db();        // TODO add your handling code here:
    }//GEN-LAST:event_jTable1MouseClicked

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
calculate();        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void all_famActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_all_famActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_all_famActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
//saves();
pay_all();// TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jComboBox2PopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_jComboBox2PopupMenuWillBecomeInvisible
jTable1.enable(true);  
call_in_table();// TODO add your handling code here:
    }//GEN-LAST:event_jComboBox2PopupMenuWillBecomeInvisible

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
pay_all();      // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
foreach();        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem1ActionPerformed

     @SuppressWarnings("empty-statement")
    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
show_photo_from_db();      
       
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void transActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_transActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_transActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
pay_all();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField accom;
    private javax.swing.JTextField advance;
    private javax.swing.JTextField all_fam;
    private javax.swing.JTextField child;
    private javax.swing.JTextField cnss;
    private javax.swing.JTextField cnsstou13;
    private javax.swing.JTextField cnsstous;
    private javax.swing.JTextField contract;
    private javax.swing.JTextField curent;
    private javax.swing.JTextField currency;
    private javax.swing.JTextField date_in;
    private javax.swing.JTextField date_out;
    private javax.swing.JTextField days_in;
    private javax.swing.JTextField days_m;
    private javax.swing.JTextField days_out;
    private javax.swing.JTextField inpps;
    private javax.swing.JTextField inss;
    private javax.swing.JTextField ipr;
    private javax.swing.JTextField ipr_2pr;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField20;
    private javax.swing.JTextField jTextField21;
    private javax.swing.JTextField jTextField22;
    private javax.swing.JTextField jTextField26;
    private javax.swing.JTextField lname;
    private javax.swing.JTextField net_a_paye;
    private javax.swing.JTextField nom;
    private javax.swing.JTextField onem;
    private javax.swing.JTextField resting_days;
    private javax.swing.JTextField roll;
    private javax.swing.JTextField sal;
    private javax.swing.JTextField sal_brut;
    private javax.swing.JTextField title;
    private javax.swing.JTextField total_sal;
    private javax.swing.JTextField trans;
    private com.alee.extended.date.WebDateField webDateField1;
    // End of variables declaration//GEN-END:variables
}
