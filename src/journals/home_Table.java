/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package journals;

import intreprisemanagementsystem.JavaDbConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Dosh
 */
public class home_Table extends javax.swing.JPanel {

   Connection con=null;
PreparedStatement pst=null;
ResultSet rs= null;
String DEVISE=null;
    public home_Table() {
        initComponents();
        con=JavaDbConnect.dbConnect();
         jTable2.setDefaultRenderer(Object.class,new home_PINTAR_TABELA()); 
         sahowtable2();
    }
   // if(){}
     SimpleDateFormat dateFormatsjour = new SimpleDateFormat("yyyy-MM-dd");
         String addDatejour = dateFormatsjour.format(journal1.jDateChooser1.getDate());
          SimpleDateFormat dateFormatsmois = new SimpleDateFormat("yyyy-MM");
         String addDatemois = dateFormatsmois.format(journal1.jDateChooser1.getDate());
          SimpleDateFormat dateFormatsyear = new SimpleDateFormat("yyyy");
         String addDateyear = dateFormatsyear.format(journal1.jDateChooser1.getDate());
     String UsDj="SELECT `NUM`, `DATES`,`LB`,`CODE1` AS CODE,`LIBELLE`, FORMAT(CREDIT,2) AS DEBIT, FORMAT(DEBIT,2) AS CREDIT,ARCHIVE AS PIECES FROM `ohada_trans` WHERE code='"+journal1.code1.getText()+"' and BUSS='"+journal1.buss.getText()+"' and DATES='"+addDatejour+"' ORDER BY BATCH_ECRITURE,DATES,NUM,ID";
      String CdFj="SELECT `NUM`, `DATES`,`LB`,`CODE1` AS CODE,`LIBELLE`, FORMAT(CREDIT_CDF,2) AS DEBIT, FORMAT(DEBIT_CDF,2) AS CREDIT,ARCHIVE AS PIECES FROM `ohada_trans` WHERE code='"+journal1.code1.getText()+"' and BUSS='"+journal1.buss.getText()+"' and DATES='"+addDatejour+"' ORDER BY BATCH_ECRITURE,DATES,NUM,ID";
       String UsDtj="SELECT `NUM`, `DATES`,`LB`, CODE,`LIBELLE`,FORMAT(DEBIT,2) AS DEBIT, FORMAT(CREDIT,2) AS CREDIT,ARCHIVE AS PIECES FROM `ohada_trans` WHERE JOURNAL='"+journal1.code1.getText()+"' and BUSS='"+journal1.buss.getText()+"' and DATES='"+addDatejour+"' ORDER BY BATCH_ECRITURE,DATES,NUM,ID";
    
       String UsDm="SELECT `NUM`, `DATES`,`LB`,`CODE1` AS CODE,`LIBELLE`, FORMAT(CREDIT,2) AS DEBIT, FORMAT(DEBIT,2) AS CREDIT,ARCHIVE AS PIECES FROM `ohada_trans` WHERE code='"+journal1.code1.getText()+"' and BUSS='"+journal1.buss.getText()+"' and SUBSTR(DATES,1,7)='"+addDatemois+"' ORDER BY BATCH_ECRITURE,DATES,NUM,ID";
      String CdFm="SELECT `NUM`, `DATES`,`LB`,`CODE1` AS CODE,`LIBELLE`, FORMAT(CREDIT_CDF,2) AS DEBIT, FORMAT(DEBIT_CDF,2) AS CREDIT,ARCHIVE AS PIECES FROM `ohada_trans` WHERE code='"+journal1.code1.getText()+"' and BUSS='"+journal1.buss.getText()+"' and SUBSTR(DATES,1,7)='"+addDatemois+"' ORDER BY BATCH_ECRITURE,DATES,NUM,ID";
       String UsDtm="SELECT `NUM`, `DATES`,`LB`, CODE,`LIBELLE`,FORMAT(DEBIT,2) AS DEBIT, FORMAT(CREDIT,2) AS CREDIT,ARCHIVE AS PIECES FROM `ohada_trans` WHERE JOURNAL='"+journal1.code1.getText()+"' and BUSS='"+journal1.buss.getText()+"' and SUBSTR(DATES,1,7)='"+addDatemois+"' ORDER BY BATCH_ECRITURE,DATES,NUM,ID";
    
       String UsDy="SELECT `NUM`, `DATES`,`LB`,`CODE1` AS CODE,`LIBELLE`, FORMAT(CREDIT,2) AS DEBIT, FORMAT(DEBIT,2) AS CREDIT,ARCHIVE AS PIECES FROM `ohada_trans` WHERE code='"+journal1.code1.getText()+"' and BUSS='"+journal1.buss.getText()+"' and SUBSTR(DATES,1,4)='"+addDateyear+"' ORDER BY BATCH_ECRITURE,DATES,NUM,ID";
      String CdFy="SELECT `NUM`, `DATES`,`LB`,`CODE1` AS CODE,`LIBELLE`, FORMAT(CREDIT_CDF,2) AS DEBIT, FORMAT(DEBIT_CDF,2) AS CREDIT,ARCHIVE AS PIECES FROM `ohada_trans` WHERE code='"+journal1.code1.getText()+"' and BUSS='"+journal1.buss.getText()+"' and SUBSTR(DATES,1,4)='"+addDateyear+"' ORDER BY BATCH_ECRITURE,DATES,NUM,ID";
       String UsDty="SELECT `NUM`, `DATES`,`LB`, CODE,`LIBELLE`,FORMAT(DEBIT,2) AS DEBIT, FORMAT(CREDIT,2) AS CREDIT,ARCHIVE AS PIECES FROM `ohada_trans` WHERE JOURNAL='"+journal1.code1.getText()+"' and BUSS='"+journal1.buss.getText()+"' and SUBSTR(DATES,1,4)='"+addDateyear+"' ORDER BY BATCH_ECRITURE,DATES,NUM,ID";
    
 
          
     public void sahowtablejour(){
        //jTable2.setDefaultRenderer(Object.class,new PINTAR_TABELA());  
         SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(journal1.jDateChooser1.getDate());
        
       if(journal1.jrn.getText().equals("JRN-57")||journal1.jrn.getText().equals("JRN-52")){
            String  PRINCIPAL=null;
            try{
          String sql="SELECT * FROM currency where APPR ='"+journal1.jComboBox2.getSelectedItem()+"'";
         pst = con.prepareStatement(sql);          rs=pst.executeQuery();
            while(rs.next()){
               PRINCIPAL=rs.getString("PRINCIPAL");
               journal1.principal.setText(PRINCIPAL);
           }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
      //  JOptionPane.showMessageDialog(null, PRINCIPAL); 
           
           if( journal1.principal.getText().equals("Yes")){
          try{
           
    String sqls=UsDj;
  pst = con.prepareStatement(sqls);          rs=pst.executeQuery();
     
       jTable2.setModel(DbUtils.resultSetToTableModel(rs));
    
             }catch(Exception ex){
                 JOptionPane.showMessageDialog(null, ex);
             }
                 DefaultTableCellRenderer centre =new DefaultTableCellRenderer();
                 
        centre.setHorizontalAlignment(JLabel.CENTER);
         TableColumn col0=jTable2.getColumnModel().getColumn(0);
        TableColumn col1=jTable2.getColumnModel().getColumn(1);
         jTable2.getColumnModel().getColumn(1).setCellRenderer(centre);
        TableColumn col2=jTable2.getColumnModel().getColumn(2);
         jTable2.getColumnModel().getColumn(2).setCellRenderer(centre);
         
         TableColumn col3=jTable2.getColumnModel().getColumn(3);
          jTable2.getColumnModel().getColumn(3).setCellRenderer(centre);
         
         TableColumn col4=jTable2.getColumnModel().getColumn(4);
        // jTable2.getColumnModel().getColumn(4).setCellRenderer(centre);
         TableColumn col5=jTable2.getColumnModel().getColumn(5);
         jTable2.getColumnModel().getColumn(5).setCellRenderer(centre);
         
          TableColumn col6=jTable2.getColumnModel().getColumn(6);
         jTable2.getColumnModel().getColumn(6).setCellRenderer(centre);
//        
      
     TableColumn col7=jTable2.getColumnModel().getColumn(7);
         jTable2.getColumnModel().getColumn(7).setCellRenderer(centre);
//        
      
       
     col0.setPreferredWidth(80);
       col1.setPreferredWidth(30);
       col2.setPreferredWidth(20);
       col3.setPreferredWidth(20);
       col4.setPreferredWidth(400);
       col5.setPreferredWidth(50);
       col6.setPreferredWidth(50);
        col7.setPreferredWidth(20);
         }else{
          try{
           
    String sqls=CdFj;
		   pst = con.prepareStatement(sqls);          rs=pst.executeQuery();
     
       jTable2.setModel(DbUtils.resultSetToTableModel(rs));
    
             }catch(Exception ex){
                 JOptionPane.showMessageDialog(null, ex);
             }
                  DefaultTableCellRenderer centre =new DefaultTableCellRenderer();
                 
        centre.setHorizontalAlignment(JLabel.CENTER);
         TableColumn col0=jTable2.getColumnModel().getColumn(0);
        TableColumn col1=jTable2.getColumnModel().getColumn(1);
         jTable2.getColumnModel().getColumn(1).setCellRenderer(centre);
        TableColumn col2=jTable2.getColumnModel().getColumn(2);
         jTable2.getColumnModel().getColumn(2).setCellRenderer(centre);
         
         TableColumn col3=jTable2.getColumnModel().getColumn(3);
          jTable2.getColumnModel().getColumn(3).setCellRenderer(centre);
         
         TableColumn col4=jTable2.getColumnModel().getColumn(4);
        // jTable2.getColumnModel().getColumn(4).setCellRenderer(centre);
         TableColumn col5=jTable2.getColumnModel().getColumn(5);
         jTable2.getColumnModel().getColumn(5).setCellRenderer(centre);
         
          TableColumn col6=jTable2.getColumnModel().getColumn(6);
         jTable2.getColumnModel().getColumn(6).setCellRenderer(centre);
//        
      
     TableColumn col7=jTable2.getColumnModel().getColumn(7);
         jTable2.getColumnModel().getColumn(7).setCellRenderer(centre);
//        
      
       
     col0.setPreferredWidth(80);
       col1.setPreferredWidth(30);
       col2.setPreferredWidth(20);
       col3.setPreferredWidth(20);
       col4.setPreferredWidth(400);
       col5.setPreferredWidth(50);
       col6.setPreferredWidth(50);
        col7.setPreferredWidth(20);
         
         }
      
       }else if(journal1.classe1.getText().equals("4") && !journal1.substr1.getText().equals("44")){
       try{
           
    String sqls=UsDtj;
		   pst = con.prepareStatement(sqls);          rs=pst.executeQuery();
     
       jTable2.setModel(DbUtils.resultSetToTableModel(rs));
    
             }catch(Exception ex){
                 JOptionPane.showMessageDialog(null, ex);
             }
                              DefaultTableCellRenderer centre =new DefaultTableCellRenderer();
                 
        centre.setHorizontalAlignment(JLabel.CENTER);
         TableColumn col0=jTable2.getColumnModel().getColumn(0);
        TableColumn col1=jTable2.getColumnModel().getColumn(1);
         jTable2.getColumnModel().getColumn(1).setCellRenderer(centre);
        TableColumn col2=jTable2.getColumnModel().getColumn(2);
         jTable2.getColumnModel().getColumn(2).setCellRenderer(centre);
         
         TableColumn col3=jTable2.getColumnModel().getColumn(3);
          jTable2.getColumnModel().getColumn(3).setCellRenderer(centre);
         
         TableColumn col4=jTable2.getColumnModel().getColumn(4);
        // jTable2.getColumnModel().getColumn(4).setCellRenderer(centre);
         TableColumn col5=jTable2.getColumnModel().getColumn(5);
         jTable2.getColumnModel().getColumn(5).setCellRenderer(centre);
         
          TableColumn col6=jTable2.getColumnModel().getColumn(6);
         jTable2.getColumnModel().getColumn(6).setCellRenderer(centre);
//        
      
      TableColumn col7=jTable2.getColumnModel().getColumn(7);
         jTable2.getColumnModel().getColumn(7).setCellRenderer(centre);
//        
      
       
     col0.setPreferredWidth(80);
       col1.setPreferredWidth(30);
       col2.setPreferredWidth(20);
       col3.setPreferredWidth(20);
       col4.setPreferredWidth(400);
       col5.setPreferredWidth(50);
       col6.setPreferredWidth(50);
        col7.setPreferredWidth(20);
       
       }else{
       try{
           
    String sqls=UsDj;
		   pst = con.prepareStatement(sqls);          rs=pst.executeQuery();
     
       jTable2.setModel(DbUtils.resultSetToTableModel(rs));
    
             }catch(Exception ex){
                 JOptionPane.showMessageDialog(null, ex);
             }
                              DefaultTableCellRenderer centre =new DefaultTableCellRenderer();
                 
        centre.setHorizontalAlignment(JLabel.CENTER);
         TableColumn col0=jTable2.getColumnModel().getColumn(0);
        TableColumn col1=jTable2.getColumnModel().getColumn(1);
         jTable2.getColumnModel().getColumn(1).setCellRenderer(centre);
        TableColumn col2=jTable2.getColumnModel().getColumn(2);
         jTable2.getColumnModel().getColumn(2).setCellRenderer(centre);
         
         TableColumn col3=jTable2.getColumnModel().getColumn(3);
          jTable2.getColumnModel().getColumn(3).setCellRenderer(centre);
         
         TableColumn col4=jTable2.getColumnModel().getColumn(4);
        // jTable2.getColumnModel().getColumn(4).setCellRenderer(centre);
         TableColumn col5=jTable2.getColumnModel().getColumn(5);
         jTable2.getColumnModel().getColumn(5).setCellRenderer(centre);
         
          TableColumn col6=jTable2.getColumnModel().getColumn(6);
         jTable2.getColumnModel().getColumn(6).setCellRenderer(centre);
//        
      
     TableColumn col7=jTable2.getColumnModel().getColumn(7);
         jTable2.getColumnModel().getColumn(7).setCellRenderer(centre);
//        
      
       
     col0.setPreferredWidth(80);
       col1.setPreferredWidth(30);
       col2.setPreferredWidth(20);
       col3.setPreferredWidth(20);
       col4.setPreferredWidth(400);
       col5.setPreferredWidth(50);
       col6.setPreferredWidth(50);
        col7.setPreferredWidth(20);
       
       }
     
           }  
     //  String sqls="SELECT `NUM`, `DATES`,`LB`,`CODE1` AS CODE,`LIBELLE`, FORMAT(CREDIT,2) AS DEBIT, FORMAT(DEBIT,2) AS CREDIT FROM `ohada_trans` WHERE code='"+journal1.code1.getText()+"' and BUSS='"+journal1.buss.getText()+"' and SUBSTR(DATES,1,7)='"+addDate+"' ORDER BY BATCH_ECRITURE,NUM,DATES";
     public void sahowtablemois(){
    //   jTable2.setDefaultRenderer(Object.class,new PINTAR_TABELA());   
        
        
       if(journal1.jrn.getText().equals("JRN-57")||journal1.jrn.getText().equals("JRN-52")){
            String  PRINCIPAL=null;
            try{
          String sql="SELECT * FROM currency where APPR ='"+journal1.jComboBox2.getSelectedItem()+"'";
         pst = con.prepareStatement(sql);          rs=pst.executeQuery();
            while(rs.next()){
               PRINCIPAL=rs.getString("PRINCIPAL");
               journal1.principal.setText(PRINCIPAL);
           }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
      //  JOptionPane.showMessageDialog(null, PRINCIPAL); 
           
             if(journal1.principal.getText().equals("Yes")){
          try{
           
    String sqls=UsDm;
    pst = con.prepareStatement(sqls);          rs=pst.executeQuery();
     
       jTable2.setModel(DbUtils.resultSetToTableModel(rs));
    
             }catch(Exception ex){
                 JOptionPane.showMessageDialog(null, ex);
             }
                 DefaultTableCellRenderer centre =new DefaultTableCellRenderer();
                 
        centre.setHorizontalAlignment(JLabel.CENTER);
         TableColumn col0=jTable2.getColumnModel().getColumn(0);
        TableColumn col1=jTable2.getColumnModel().getColumn(1);
         jTable2.getColumnModel().getColumn(1).setCellRenderer(centre);
        TableColumn col2=jTable2.getColumnModel().getColumn(2);
         jTable2.getColumnModel().getColumn(2).setCellRenderer(centre);
         
         TableColumn col3=jTable2.getColumnModel().getColumn(3);
          jTable2.getColumnModel().getColumn(3).setCellRenderer(centre);
         
         TableColumn col4=jTable2.getColumnModel().getColumn(4);
        // jTable2.getColumnModel().getColumn(4).setCellRenderer(centre);
         TableColumn col5=jTable2.getColumnModel().getColumn(5);
         jTable2.getColumnModel().getColumn(5).setCellRenderer(centre);
         
          TableColumn col6=jTable2.getColumnModel().getColumn(6);
         jTable2.getColumnModel().getColumn(6).setCellRenderer(centre);
//        
      
       
     TableColumn col7=jTable2.getColumnModel().getColumn(7);
         jTable2.getColumnModel().getColumn(7).setCellRenderer(centre);
//        
      
       
     col0.setPreferredWidth(80);
       col1.setPreferredWidth(30);
       col2.setPreferredWidth(20);
       col3.setPreferredWidth(20);
       col4.setPreferredWidth(400);
       col5.setPreferredWidth(50);
       col6.setPreferredWidth(50);
        col7.setPreferredWidth(20);
         }else{
          try{
           
    String sqls=CdFm;
		   pst = con.prepareStatement(sqls);          rs=pst.executeQuery();
     
       jTable2.setModel(DbUtils.resultSetToTableModel(rs));
    
             }catch(Exception ex){
                 JOptionPane.showMessageDialog(null, ex);
             }
                  DefaultTableCellRenderer centre =new DefaultTableCellRenderer();
                 
        centre.setHorizontalAlignment(JLabel.CENTER);
         TableColumn col0=jTable2.getColumnModel().getColumn(0);
        TableColumn col1=jTable2.getColumnModel().getColumn(1);
         jTable2.getColumnModel().getColumn(1).setCellRenderer(centre);
        TableColumn col2=jTable2.getColumnModel().getColumn(2);
         jTable2.getColumnModel().getColumn(2).setCellRenderer(centre);
         
         TableColumn col3=jTable2.getColumnModel().getColumn(3);
          jTable2.getColumnModel().getColumn(3).setCellRenderer(centre);
         
         TableColumn col4=jTable2.getColumnModel().getColumn(4);
        // jTable2.getColumnModel().getColumn(4).setCellRenderer(centre);
         TableColumn col5=jTable2.getColumnModel().getColumn(5);
         jTable2.getColumnModel().getColumn(5).setCellRenderer(centre);
         
          TableColumn col6=jTable2.getColumnModel().getColumn(6);
         jTable2.getColumnModel().getColumn(6).setCellRenderer(centre);
//        
      
       
      TableColumn col7=jTable2.getColumnModel().getColumn(7);
         jTable2.getColumnModel().getColumn(7).setCellRenderer(centre);
//        
      
       
     col0.setPreferredWidth(80);
       col1.setPreferredWidth(30);
       col2.setPreferredWidth(20);
       col3.setPreferredWidth(20);
       col4.setPreferredWidth(400);
       col5.setPreferredWidth(50);
       col6.setPreferredWidth(50);
        col7.setPreferredWidth(20);
         
         }
      
       }else if(journal1.classe1.getText().equals("4") && !journal1.substr1.getText().equals("44")){
       try{
           
    String sqls=UsDtm;
		   pst = con.prepareStatement(sqls);          rs=pst.executeQuery();
     
       jTable2.setModel(DbUtils.resultSetToTableModel(rs));
    
             }catch(Exception ex){
                 JOptionPane.showMessageDialog(null, ex);
             }
                              DefaultTableCellRenderer centre =new DefaultTableCellRenderer();
                 
        centre.setHorizontalAlignment(JLabel.CENTER);
         TableColumn col0=jTable2.getColumnModel().getColumn(0);
        TableColumn col1=jTable2.getColumnModel().getColumn(1);
         jTable2.getColumnModel().getColumn(1).setCellRenderer(centre);
        TableColumn col2=jTable2.getColumnModel().getColumn(2);
         jTable2.getColumnModel().getColumn(2).setCellRenderer(centre);
         
         TableColumn col3=jTable2.getColumnModel().getColumn(3);
          jTable2.getColumnModel().getColumn(3).setCellRenderer(centre);
         
         TableColumn col4=jTable2.getColumnModel().getColumn(4);
        // jTable2.getColumnModel().getColumn(4).setCellRenderer(centre);
         TableColumn col5=jTable2.getColumnModel().getColumn(5);
         jTable2.getColumnModel().getColumn(5).setCellRenderer(centre);
         
          TableColumn col6=jTable2.getColumnModel().getColumn(6);
         jTable2.getColumnModel().getColumn(6).setCellRenderer(centre);
//        
      
       
     TableColumn col7=jTable2.getColumnModel().getColumn(7);
         jTable2.getColumnModel().getColumn(7).setCellRenderer(centre);
//        
      
       
     col0.setPreferredWidth(80);
       col1.setPreferredWidth(30);
       col2.setPreferredWidth(20);
       col3.setPreferredWidth(20);
       col4.setPreferredWidth(400);
       col5.setPreferredWidth(50);
       col6.setPreferredWidth(50);
        col7.setPreferredWidth(20);
       
       }else{
       try{
           
    String sqls=UsDm;
		   pst = con.prepareStatement(sqls);          rs=pst.executeQuery();
     
       jTable2.setModel(DbUtils.resultSetToTableModel(rs));
    
             }catch(Exception ex){
                 JOptionPane.showMessageDialog(null, ex);
             }
                              DefaultTableCellRenderer centre =new DefaultTableCellRenderer();
                 
        centre.setHorizontalAlignment(JLabel.CENTER);
         TableColumn col0=jTable2.getColumnModel().getColumn(0);
        TableColumn col1=jTable2.getColumnModel().getColumn(1);
         jTable2.getColumnModel().getColumn(1).setCellRenderer(centre);
        TableColumn col2=jTable2.getColumnModel().getColumn(2);
         jTable2.getColumnModel().getColumn(2).setCellRenderer(centre);
         
         TableColumn col3=jTable2.getColumnModel().getColumn(3);
          jTable2.getColumnModel().getColumn(3).setCellRenderer(centre);
         
         TableColumn col4=jTable2.getColumnModel().getColumn(4);
        // jTable2.getColumnModel().getColumn(4).setCellRenderer(centre);
         TableColumn col5=jTable2.getColumnModel().getColumn(5);
         jTable2.getColumnModel().getColumn(5).setCellRenderer(centre);
         
          TableColumn col6=jTable2.getColumnModel().getColumn(6);
         jTable2.getColumnModel().getColumn(6).setCellRenderer(centre);
//        
      
       
     TableColumn col7=jTable2.getColumnModel().getColumn(7);
         jTable2.getColumnModel().getColumn(7).setCellRenderer(centre);
//        
      
       
     col0.setPreferredWidth(80);
       col1.setPreferredWidth(30);
       col2.setPreferredWidth(20);
       col3.setPreferredWidth(20);
       col4.setPreferredWidth(400);
       col5.setPreferredWidth(50);
       col6.setPreferredWidth(50);
        col7.setPreferredWidth(20);
       
       }
     
           }
     //    String sqls="SELECT `NUM`, `DATES`,`LB`,`CODE1` AS CODE,`LIBELLE`, FORMAT(CREDIT,2) AS DEBIT, FORMAT(DEBIT,2) AS CREDIT FROM `ohada_trans` WHERE code='"+journal1.code1.getText()+"' and BUSS='"+journal1.buss.getText()+"' and  SUBSTR(DATES,1,4)='"+addDate+"' ORDER BY BATCH_ECRITURE,NUM,DATES";
       public void sahowtableyear(){
         
         SimpleDateFormat dateFormats = new SimpleDateFormat("YYYY");
         String addDate = dateFormats.format(journal1.jDateChooser1.getDate());
       
       if(journal1.jrn.getText().equals("JRN-57")||journal1.jrn.getText().equals("JRN-52")){
            String  PRINCIPAL=null;
            try{
          String sql="SELECT * FROM currency where APPR ='"+journal1.jComboBox2.getSelectedItem()+"'";
         pst = con.prepareStatement(sql);          rs=pst.executeQuery();
            while(rs.next()){
               PRINCIPAL=rs.getString("PRINCIPAL");
               journal1.principal.setText(PRINCIPAL);
           }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
       // JOptionPane.showMessageDialog(null, PRINCIPAL); 
           
             if( journal1.principal.getText().equals("Yes")){
              // JOptionPane.showMessageDialog(null, addDate);      
          try{
           
    String sqls=UsDy;
    pst = con.prepareStatement(sqls);          rs=pst.executeQuery();
     
       jTable2.setModel(DbUtils.resultSetToTableModel(rs));
    
             }catch(Exception ex){
                 JOptionPane.showMessageDialog(null, ex);
             }
                 DefaultTableCellRenderer centre =new DefaultTableCellRenderer();
                 
        centre.setHorizontalAlignment(JLabel.CENTER);
         TableColumn col0=jTable2.getColumnModel().getColumn(0);
        TableColumn col1=jTable2.getColumnModel().getColumn(1);
         jTable2.getColumnModel().getColumn(1).setCellRenderer(centre);
        TableColumn col2=jTable2.getColumnModel().getColumn(2);
         jTable2.getColumnModel().getColumn(2).setCellRenderer(centre);
         
         TableColumn col3=jTable2.getColumnModel().getColumn(3);
          jTable2.getColumnModel().getColumn(3).setCellRenderer(centre);
         
         TableColumn col4=jTable2.getColumnModel().getColumn(4);
        // jTable2.getColumnModel().getColumn(4).setCellRenderer(centre);
         TableColumn col5=jTable2.getColumnModel().getColumn(5);
         jTable2.getColumnModel().getColumn(5).setCellRenderer(centre);
         
          TableColumn col6=jTable2.getColumnModel().getColumn(6);
         jTable2.getColumnModel().getColumn(6).setCellRenderer(centre);
//        
      
       
     TableColumn col7=jTable2.getColumnModel().getColumn(7);
         jTable2.getColumnModel().getColumn(7).setCellRenderer(centre);
//        
      
       
     col0.setPreferredWidth(80);
       col1.setPreferredWidth(30);
       col2.setPreferredWidth(20);
       col3.setPreferredWidth(20);
       col4.setPreferredWidth(400);
       col5.setPreferredWidth(50);
       col6.setPreferredWidth(50);
        col7.setPreferredWidth(20);
         
         }else{
          try{
           
    String sqls=CdFy;
		   pst = con.prepareStatement(sqls);          rs=pst.executeQuery();
     
       jTable2.setModel(DbUtils.resultSetToTableModel(rs));
    
             }catch(Exception ex){
                 JOptionPane.showMessageDialog(null, ex);
             }
                  DefaultTableCellRenderer centre =new DefaultTableCellRenderer();
                 
        centre.setHorizontalAlignment(JLabel.CENTER);
         TableColumn col0=jTable2.getColumnModel().getColumn(0);
        TableColumn col1=jTable2.getColumnModel().getColumn(1);
         jTable2.getColumnModel().getColumn(1).setCellRenderer(centre);
        TableColumn col2=jTable2.getColumnModel().getColumn(2);
         jTable2.getColumnModel().getColumn(2).setCellRenderer(centre);
         
         TableColumn col3=jTable2.getColumnModel().getColumn(3);
          jTable2.getColumnModel().getColumn(3).setCellRenderer(centre);
         
         TableColumn col4=jTable2.getColumnModel().getColumn(4);
        // jTable2.getColumnModel().getColumn(4).setCellRenderer(centre);
         TableColumn col5=jTable2.getColumnModel().getColumn(5);
         jTable2.getColumnModel().getColumn(5).setCellRenderer(centre);
         
          TableColumn col6=jTable2.getColumnModel().getColumn(6);
         jTable2.getColumnModel().getColumn(6).setCellRenderer(centre);
//        
      
       
     TableColumn col7=jTable2.getColumnModel().getColumn(7);
         jTable2.getColumnModel().getColumn(7).setCellRenderer(centre);
//        
      
       
     col0.setPreferredWidth(80);
       col1.setPreferredWidth(30);
       col2.setPreferredWidth(20);
       col3.setPreferredWidth(20);
       col4.setPreferredWidth(400);
       col5.setPreferredWidth(50);
       col6.setPreferredWidth(50);
        col7.setPreferredWidth(20);
         
         }
      
       }else if(journal1.classe1.getText().equals("4") && !journal1.substr1.getText().equals("44")){
       try{
           
    String sqls=UsDty;
		   pst = con.prepareStatement(sqls);          rs=pst.executeQuery();
     
       jTable2.setModel(DbUtils.resultSetToTableModel(rs));
    
             }catch(Exception ex){
                 JOptionPane.showMessageDialog(null, ex);
             }
                              DefaultTableCellRenderer centre =new DefaultTableCellRenderer();
                 
        centre.setHorizontalAlignment(JLabel.CENTER);
         TableColumn col0=jTable2.getColumnModel().getColumn(0);
        TableColumn col1=jTable2.getColumnModel().getColumn(1);
         jTable2.getColumnModel().getColumn(1).setCellRenderer(centre);
        TableColumn col2=jTable2.getColumnModel().getColumn(2);
         jTable2.getColumnModel().getColumn(2).setCellRenderer(centre);
         
         TableColumn col3=jTable2.getColumnModel().getColumn(3);
          jTable2.getColumnModel().getColumn(3).setCellRenderer(centre);
         
         TableColumn col4=jTable2.getColumnModel().getColumn(4);
        // jTable2.getColumnModel().getColumn(4).setCellRenderer(centre);
         TableColumn col5=jTable2.getColumnModel().getColumn(5);
         jTable2.getColumnModel().getColumn(5).setCellRenderer(centre);
         
          TableColumn col6=jTable2.getColumnModel().getColumn(6);
         jTable2.getColumnModel().getColumn(6).setCellRenderer(centre);
//        
      
       
    TableColumn col7=jTable2.getColumnModel().getColumn(7);
         jTable2.getColumnModel().getColumn(7).setCellRenderer(centre);
//        
      
       
     col0.setPreferredWidth(80);
       col1.setPreferredWidth(30);
       col2.setPreferredWidth(20);
       col3.setPreferredWidth(20);
       col4.setPreferredWidth(400);
       col5.setPreferredWidth(50);
       col6.setPreferredWidth(50);
        col7.setPreferredWidth(20);
       
       }else{
       try{
           
    String sqls=UsDy;
		   pst = con.prepareStatement(sqls);          rs=pst.executeQuery();
     
       jTable2.setModel(DbUtils.resultSetToTableModel(rs));
    
             }catch(Exception ex){
                 JOptionPane.showMessageDialog(null, ex);
             }
                              DefaultTableCellRenderer centre =new DefaultTableCellRenderer();
                 
        centre.setHorizontalAlignment(JLabel.CENTER);
         TableColumn col0=jTable2.getColumnModel().getColumn(0);
        TableColumn col1=jTable2.getColumnModel().getColumn(1);
         jTable2.getColumnModel().getColumn(1).setCellRenderer(centre);
        TableColumn col2=jTable2.getColumnModel().getColumn(2);
         jTable2.getColumnModel().getColumn(2).setCellRenderer(centre);
         
         TableColumn col3=jTable2.getColumnModel().getColumn(3);
          jTable2.getColumnModel().getColumn(3).setCellRenderer(centre);
         
         TableColumn col4=jTable2.getColumnModel().getColumn(4);
        // jTable2.getColumnModel().getColumn(4).setCellRenderer(centre);
         TableColumn col5=jTable2.getColumnModel().getColumn(5);
         jTable2.getColumnModel().getColumn(5).setCellRenderer(centre);
         
          TableColumn col6=jTable2.getColumnModel().getColumn(6);
         jTable2.getColumnModel().getColumn(6).setCellRenderer(centre);
         
          TableColumn col7=jTable2.getColumnModel().getColumn(7);
         jTable2.getColumnModel().getColumn(7).setCellRenderer(centre);
//        
      
       
     col0.setPreferredWidth(80);
       col1.setPreferredWidth(30);
       col2.setPreferredWidth(20);
       col3.setPreferredWidth(20);
       col4.setPreferredWidth(400);
       col5.setPreferredWidth(50);
       col6.setPreferredWidth(50);
        col7.setPreferredWidth(20);
       
       }
     
           }
       
       public void  sahowtable2(){
       if(journal1.batchs1.getSelectedItem().equals("Day")){
       sahowtablejour();
       }else if(journal1.batchs1.getSelectedItem().equals("Month")){
       sahowtablemois();
       }else if(journal1.batchs1.getSelectedItem().equals("Year")){
       sahowtableyear();
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

        jScrollPane2 = new javax.swing.JScrollPane();

        jTable2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable2.setFocusTraversalPolicyProvider(true);
        jTable2.setGridColor(new java.awt.Color(204, 204, 204));
        jTable2.setInheritsPopupMenu(true);
        jTable2.setRowHeight(30);
        jTable2.setSelectionForeground(new java.awt.Color(204, 204, 204));
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 708, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 341, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
journal1.check_select_Table.setText("Yes");
 int row= jTable2.getSelectedRow();
          String Table_click = (jTable2.getModel().getValueAt(row,0). toString());
try{
          String sql="SELECT * FROM ohada_trans where buss='"+journal1.buss.getText()+"' and num ='"+Table_click+"'";
         pst = con.prepareStatement(sql);          rs=pst.executeQuery();
           while(rs.next()){
          journal1.jTextField1.setText(rs.getString("BATCH_ECRITURE"));
        }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex); 
         
        }// TODO add your handling jrn here:
    }//GEN-LAST:event_jTable2MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane2;
    public static final javax.swing.JTable jTable2 = new javax.swing.JTable();
    // End of variables declaration//GEN-END:variables
}
