/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intreprisemanagementsystem;

import static intreprisemanagementsystem.homme.user;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.showInputDialog;
import static javax.swing.JOptionPane.showMessageDialog;
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
 * @author Dosh
 */
public class etat_de_besoin extends javax.swing.JFrame {
 Connection con=null;
PreparedStatement pst=null;
ResultSet rs= null;
String rolls,CLASSE,CLASSE1,SUBSTRS,SUBSTR,CATB,SUB_CATB,APPROV,IDS,ECRI;
Double SUMDEBIT,SUMCREDIT,ECART,CHECK_BUDGET,CHECK_BUDGETP;
DefaultTableModel mode;
int x,y;
    public etat_de_besoin() {
        initComponents();
         con=JavaDbConnect.dbConnect();
         setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("icons8_Raspberry_Pi_48px_1.png")));
        //   date_now();
    sup.setEditable(false);
    chat.setEditable(false);
    Call_IN_LIST();
    
    showEBData();
       
    }
 
      public void if_nothing()
    {
        if(recherches.getText().equals("")){
            recherches.setText("yyyyy");
    //        search();
        }
    }
 
      public void date_now()
    {
       jDateChooser1.setDate(new Date());
      
    }
   
     
   
         
      public void increment_number()
      {
          String s = roll.getText();
          
        int billNumber = Integer.valueOf(s);
        s = Integer.toString(++billNumber);
        roll.setText(s); 
      }
      // AUTO ROLL NUMBER
     public void roll()
     {
             try{
                  SimpleDateFormat dateFormatsS = new SimpleDateFormat("yyyy-MM-dd");
         String addDateS = dateFormatsS.format(jDateChooser1.getDate());
         
         String sql="SELECT NUM FROM ohada_trans where SUBSTR(NUM,5,10)='"+addDateS+"' ORDER BY NUM DESC LIMIT 1";
             pst=con.prepareStatement(sql);
             rs=pst.executeQuery();
             if(rs.next()){
                 String rl=rs.getString("NUM");
                 int ln=rl.length();
                 String stxt=rl.substring(0,15);
                 String snum=rl.substring(15,ln);
                 int n = Integer.parseInt(snum);
                 n++;
                 snum=Integer.toString(n);
                 rolls=stxt+snum;
             }else{
               rolls= "No: "+addDateS+"/1001";
                
             }
         }catch(NumberFormatException | SQLException e){
             JOptionPane.showMessageDialog(null, e);  
         }
             
         }
     
  
         
        
    
      
      // DELETE
    public void delete()
    {
         try{
        String sql = "DELETE FROM etat_de_besoin WHERE NUM=?";
        
         pst = con.prepareStatement(sql);
         pst.setString(1,recherche.getText());
         pst.executeUpdate();

     }catch(SQLException | HeadlessException ex ){
         JOptionPane.showMessageDialog(null,ex);
     } 
    }
      //UPDATE
  

public void showEBData(){ 
   // SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
    // String add = dateFormats.format (jDateChooser1.setDate(new Date()));
     try{
       
         String sql="SELECT distinct NUM FROM etat_de_besoin WHERE ORIENTATION='FINANCE' and ORIENTATION2='Fin' and PRINT='' and buss='"+buss.getSelectedItem()+"'";
          pst = con.prepareStatement(sql);
        rs= pst.executeQuery();
        jTable2.setModel(DbUtils.resultSetToTableModel(rs));
        
      }catch(SQLException ex ){
      JOptionPane.showMessageDialog(null, ex);
}    
}
     
      public void showTableData(){
     try{
       
         String sql="SELECT * FROM etat_de_besoin";
          pst = con.prepareStatement(sql);
        rs= pst.executeQuery();
        jTable1.setModel(DbUtils.resultSetToTableModel(rs));
        
      }catch(SQLException ex ){
      JOptionPane.showMessageDialog(null, ex);
}
}
        public void Call_ID_MAX()
    {
         {
        try{
            String sql="select MAX(NUM) from etat_de_besoin ";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                String sum=rs.getString("MAX(NUM)");
                  roll.setText(sum);
                  recherches.setText(sum);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
    }
    }
        
        
        //SELECT IN JTABLE
        
         public void select_jTable()
   {
       if(jRadioButton1.isSelected()){
         try{
          int row= jTable1.getSelectedRow();
          String Table_click = (jTable1.getModel().getValueAt(row,0). toString());
           String libelle = (jTable1.getModel().getValueAt(row,1). toString());
            String dates = (jTable1.getModel().getValueAt(row,5). toString());
            String replaceString=libelle.replace("'", "''");
           //  String Table_click = (jTable1.getModel().getValueAt(row,0). toString());
          String sql = "SELECT * FROM etat_de_besoin_b WHERE ID= '"+Table_click+"' AND DET='"+replaceString+"' and DATES='"+dates+"'";
           //  String sql = "SELECT * FROM etat_de_besoin WHERE NUM_ID= '"+Table_click+"' AND NUM='"+recherche.getText()+"'";
          
          pst = con.prepareStatement(sql);
          rs=pst.executeQuery();
          if(rs.next()){
              IDS = rs.getString("NUM_ID");
                String add2 = rs.getString("SUP");
              sup.setText(add2);
                String add3 = rs.getString("CHANT");
              chat.setText(add3);
                String add4 = rs.getString("NUM");
              roll.setText(add4);
               
              
               String add9 = rs.getString("NUM");
              recherche.setText(add9);
              
                  Date add92 = rs.getDate("DATES");
             // jDateChooser1.setText(add92)
                      ;
              jDateChooser1.setDate(add92);
              
                 APPROV = rs.getString("APPROUVATION");//SUP,CHANT,NUM,DET,QTY,PU,PT,DATES
             
              
              ECRI = rs.getString("ECRITURE");//SUP,CHANT,NUM,DET,QTY,PU,PT,DATES
             
              
              String addss = rs.getString("BUSS");//SUP,CHANT,NUM,DET,QTY,PU,PT,DATES
              buss.setSelectedItem(addss);
              
              String addsss = rs.getString("DEVICE");//SUP,CHANT,NUM,DET,QTY,PU,PT,DATES
              devise.setText(addsss);
          }
         
    }catch(SQLException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }
       }else{
         try{
          int row= jTable1.getSelectedRow();
          String Table_click = (jTable1.getModel().getValueAt(row,0). toString());
           String libelle = (jTable1.getModel().getValueAt(row,1). toString());
            String dates = (jTable1.getModel().getValueAt(row,5). toString());
            String replaceString=libelle.replace("'", "''");
           //  String Table_click = (jTable1.getModel().getValueAt(row,0). toString());
          String sql = "SELECT * FROM etat_de_besoin WHERE ID= '"+Table_click+"' AND DET='"+replaceString+"' and DATES='"+dates+"'";
           //  String sql = "SELECT * FROM etat_de_besoin WHERE NUM_ID= '"+Table_click+"' AND NUM='"+recherche.getText()+"'";
          
          pst = con.prepareStatement(sql);
          rs=pst.executeQuery();
          if(rs.next()){
              IDS = rs.getString("NUM_ID");
                String add2 = rs.getString("SUP");
              sup.setText(add2);
                String add3 = rs.getString("CHANT");
              chat.setText(add3);
                String add4 = rs.getString("NUM");
              roll.setText(add4);
               
              
               String add9 = rs.getString("NUM");
              recherche.setText(add9);
              
                  Date add92 = rs.getDate("DATES");
             // jDateChooser1.setText(add92)
                      ;
              jDateChooser1.setDate(add92);
              
                 APPROV = rs.getString("APPROUVATION");//SUP,CHANT,NUM,DET,QTY,PU,PT,DATES
             
              
              ECRI = rs.getString("ECRITURE");//SUP,CHANT,NUM,DET,QTY,PU,PT,DATES
             
              
              String addss = rs.getString("BUSS");//SUP,CHANT,NUM,DET,QTY,PU,PT,DATES
              buss.setSelectedItem(addss);
              
              String addsss = rs.getString("DEVICE");//SUP,CHANT,NUM,DET,QTY,PU,PT,DATES
              devise.setText(addsss);
          }
         
    }catch(SQLException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }
       }
     
         try{
      DefaultListModel listSS = new DefaultListModel();
      listSS.clear();
jList4.setModel(listSS);
            String sql="SELECT * FROM  budget_code  WHERE  CAT='"+buss.getSelectedItem()+"'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
               // String sum=rs.getString("intitule_compte");
                String sums=rs.getString("CODE");
               listSS.addElement(sums);
                 jList4.setModel(listSS);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex); } 
       
        
        
   }
                public void select_jTableb()
   {
     //  if(recherche.getText().isEmpty()){
       try{
          int row= jTable1.getSelectedRow();
          String Table_click = (jTable1.getModel().getValueAt(row,0). toString());
           String libelle = (jTable1.getModel().getValueAt(row,1). toString());
            String dates = (jTable1.getModel().getValueAt(row,5). toString());
            String replaceString=libelle.replace("'", "''");
           //  String Table_click = (jTable1.getModel().getValueAt(row,0). toString());
          String sql = "SELECT * FROM etat_de_besoin_b WHERE NUM_ID= '"+Table_click+"' AND DET='"+replaceString+"' and DATES='"+dates+"'";
           //  String sql = "SELECT * FROM etat_de_besoin WHERE NUM_ID= '"+Table_click+"' AND NUM='"+recherche.getText()+"'";
          
          pst = con.prepareStatement(sql);
          rs=pst.executeQuery();
          if(rs.next()){
              IDS = rs.getString("NUM_ID");
                String add2 = rs.getString("SUP");
              sup.setText(add2);
                String add3 = rs.getString("CHANT");
              chat.setText(add3);
                String add4 = rs.getString("NUM");
              roll.setText(add4);
               
              
               String add9 = rs.getString("NUM");
              recherche.setText(add9);
              
                 APPROV = rs.getString("APPROUVATION");//SUP,CHANT,NUM,DET,QTY,PU,PT,DATES
             
              
              ECRI = rs.getString("ECRITURE");//SUP,CHANT,NUM,DET,QTY,PU,PT,DATES
             
              
              String addss = rs.getString("BUSS");//SUP,CHANT,NUM,DET,QTY,PU,PT,DATES
              buss.setSelectedItem(addss);
              
              String addsss = rs.getString("DEVICE");//SUP,CHANT,NUM,DET,QTY,PU,PT,DATES
              devise.setText(addsss);
          }
         
    }catch(SQLException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }
         try{
      DefaultListModel listSS = new DefaultListModel();
      listSS.clear();
jList4.setModel(listSS);
            String sql="SELECT * FROM  budget_code  WHERE  CAT='"+buss.getSelectedItem()+"'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
               // String sum=rs.getString("intitule_compte");
                String sums=rs.getString("CODE");
               listSS.addElement(sums);
                 jList4.setModel(listSS);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex); } 
       
        
        
   }
          public void select_jTableNUM()
   {
        int row= jTable2.getSelectedRow();
          String Table_click = (jTable2.getModel().getValueAt(row,0). toString());
        try{
         
          String sql = "SELECT * FROM etat_de_besoin WHERE NUM= '"+Table_click+"' and buss='"+buss.getSelectedItem()+"'";
          pst = con.prepareStatement(sql);
          rs=pst.executeQuery();
          if(rs.next()){
              String add1 = rs.getString("NUM");//SUP,CHANT,NUM,DET,QTY,PU,PT,DATES
              recherche.setText(add1);
              APPROV= rs.getString("APPROUVATION");//SUP,CHANT,NUM,DET,QTY,PU,PT,DATES
              
              String add11 = rs.getString("DEVICE");//SUP,CHANT,NUM,DET,QTY,PU,PT,DATES
             devise.setText(add11);
              
                
          }
         
    }catch(SQLException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }
        
         try{
           
             String sql="SELECT `ID` AS NUM,`DET`, `QTY`, `PU`, `PT`, `DATES`, `APPROUVATION` FROM `etat_de_besoin` WHERE NUM= '"+Table_click+"'";
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
        TableColumn col5=jTable1.getColumnModel().getColumn(5);
        TableColumn col6=jTable1.getColumnModel().getColumn(6);
//        TableColumn col7=jTable1.getColumnModel().getColumn(7);
//        TableColumn col8=jTable1.getColumnModel().getColumn(8);
//        TableColumn col9=jTable1.getColumnModel().getColumn(9);
        
       
       
      
//`ID`,`SUP`, `CHANT`, `NUM`, `DET`, `QTY`, `PU`, `PT`, `DATES`, `APPROUVATION`       
       col0.setPreferredWidth(5);
       col1.setPreferredWidth(250);
       col2.setPreferredWidth(30);
       col3.setPreferredWidth(50);
       col4.setPreferredWidth(50);
       col5.setPreferredWidth(80);
       col6.setPreferredWidth(100);
//       col7.setPreferredWidth(20);
//       col8.setPreferredWidth(30);
//       col9.setPreferredWidth(50);
      
      
       
       
      // jTable1.setModel(mode);
       
     
    }catch(SQLException ex ){
     JOptionPane.showMessageDialog(null, ex);
}
   }
        
     //SEARCH AND ADDREADER
     
     
      
       public void call_in_tablesearch(){
     
    try{
           
             String sql="SELECT `ID` AS NUM,`DET`, `QTY`, `PU`, `PT`, `DATES`, `APPROUVATION` FROM `etat_de_besoin` WHERE `ORIENTATION`='FINANCE'";
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
        TableColumn col5=jTable1.getColumnModel().getColumn(5);
        TableColumn col6=jTable1.getColumnModel().getColumn(6);
//        TableColumn col7=jTable1.getColumnModel().getColumn(7);
//        TableColumn col8=jTable1.getColumnModel().getColumn(8);
//        TableColumn col9=jTable1.getColumnModel().getColumn(9);
        
       
       
      
//`ID`,`SUP`, `CHANT`, `NUM`, `DET`, `QTY`, `PU`, `PT`, `DATES`, `APPROUVATION`       
       col0.setPreferredWidth(5);
       col1.setPreferredWidth(250);
       col2.setPreferredWidth(30);
       col3.setPreferredWidth(50);
       col4.setPreferredWidth(50);
       col5.setPreferredWidth(80);
       col6.setPreferredWidth(100);
//       col7.setPreferredWidth(20);
//       col8.setPreferredWidth(30);
//       col9.setPreferredWidth(50);
      
      
      
      
       
       
      // jTable1.setModel(mode);
       
     
    }catch(SQLException ex ){
     JOptionPane.showMessageDialog(null, ex);
}
     
      }
    
     
           
             
              public void searchS()
             {
                
    try{
           
             String sql="SELECT `ID` AS NUM,`DET`, `QTY`, `PU`, `PT`, `DATES`, `APPROUVATION` FROM `etat_de_besoin` WHERE NUM like '"+recherche.getText()+"%'  AND `ORIENTATION`='FINANCE' and buss='"+buss.getSelectedItem()+"'";
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
        TableColumn col5=jTable1.getColumnModel().getColumn(5);
        TableColumn col6=jTable1.getColumnModel().getColumn(6);
//        TableColumn col7=jTable1.getColumnModel().getColumn(7);
//        TableColumn col8=jTable1.getColumnModel().getColumn(8);
//        TableColumn col9=jTable1.getColumnModel().getColumn(9);
        
       
       
      
//`ID`,`SUP`, `CHANT`, `NUM`, `DET`, `QTY`, `PU`, `PT`, `DATES`, `APPROUVATION`       
       col0.setPreferredWidth(5);
       col1.setPreferredWidth(250);
       col2.setPreferredWidth(30);
       col3.setPreferredWidth(50);
       col4.setPreferredWidth(50);
       col5.setPreferredWidth(80);
       col6.setPreferredWidth(100);
//       col7.setPreferredWidth(20);
//       col8.setPreferredWidth(30);
//       col9.setPreferredWidth(50);
       
     
    }catch(SQLException ex ){
     JOptionPane.showMessageDialog(null, ex);
}
     
      }
   
               public void searchSB()
             {
                
    try{
           
             String sql="SELECT `ID` AS NUM,`DET`, `QTY`, `PU`, `PT`, `DATES`, `APPROUVATION` FROM `etat_de_besoin_B` WHERE NUM like '"+recherche.getText()+"%'  AND `ORIENTATION`='FINANCE' and buss='"+buss.getSelectedItem()+"'";
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
        TableColumn col5=jTable1.getColumnModel().getColumn(5);
        TableColumn col6=jTable1.getColumnModel().getColumn(6);
//        TableColumn col7=jTable1.getColumnModel().getColumn(7);
//        TableColumn col8=jTable1.getColumnModel().getColumn(8);
//        TableColumn col9=jTable1.getColumnModel().getColumn(9);
        
       
       
      
//`ID`,`SUP`, `CHANT`, `NUM`, `DET`, `QTY`, `PU`, `PT`, `DATES`, `APPROUVATION`       
       col0.setPreferredWidth(5);
       col1.setPreferredWidth(250);
       col2.setPreferredWidth(30);
       col3.setPreferredWidth(50);
       col4.setPreferredWidth(50);
       col5.setPreferredWidth(80);
       col6.setPreferredWidth(100);
//       col7.setPreferredWidth(20);
//       col8.setPreferredWidth(30);
//       col9.setPreferredWidth(50);
       
     
    }catch(SQLException ex ){
     JOptionPane.showMessageDialog(null, ex);
}
     
      }
   
              
              
              //REPORT
               public void report()
     {
       
         
      /// 
          
    
     String A =showInputDialog("ENTREZ LE ROLL_No!!!");
          if(A.equals("1234")){
              
          if(recherche.getText().equals("")){
              String tmp =(String) recherches.getText();
     
             try{
                 
                   String sqls="Select path from pathn";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
            if(rs.next()){
             
                String sum=rs.getString("path");
                  
                 String NameFiles=sum;
            
            String NameFile=""+NameFiles+"etatdebesoin.jrxml";
                 
                 
                 
                JasperDesign jd=JRXmlLoader.load(NameFile);
                 
                // String report ="C:\\Users\\Doshe\\Documents\\NetBeansProjects\\intreprisemanagementsystem\\src\\report\\newReportmateriau.jrxml";
           //     JasperDesign jd=JRXmlLoader.load("C:\\Users\\Dosh\\Documents\\NetBeansProjects\\intreprisemanagementsystem\\src\\intreprisemanagementsystem\\etatdebesoin.jrxml");
                String sql="select * from etat_de_besoin INNER JOIN projet ON etat_de_besoin.BUSS=projet.PROJET_NUM  ";// where NUM='"+tmp+"' and BUSS='"+buss.getSelectedItem()+"'";
                 HashMap param= new HashMap();
                 String User=homme.user.getText();
    param.put("nom",User);
                JRDesignQuery nq=new JRDesignQuery();
                nq.setText(sql);
                jd.setQuery(nq);
               
                 JasperReport jr =JasperCompileManager.compileReport(jd);
                 JasperPrint jp=JasperFillManager.fillReport(jr,param,con);
                 JasperViewer.viewReport(jp,false);
                 
                 
                 JasperViewer m= new JasperViewer(jp);
                 m.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            }
             }catch(Exception ex){
                  JOptionPane.showMessageDialog(null, ex);   
             }
          }else{
              String tmp =(String) recherche.getText();
               try{
                   
                     String sqls="Select path from pathn";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
            if(rs.next()){
             
                String sum=rs.getString("path");
                  
                 String NameFiles=sum;
            
            String NameFile=""+NameFiles+"etatdebesoin.jrxml";
                 
                 
                 
                JasperDesign jd=JRXmlLoader.load(NameFile);
                // String report ="C:\\Users\\Doshe\\Documents\\NetBeansProjects\\intreprisemanagementsystem\\src\\report\\newReportmateriau.jrxml";
               // JasperDesign jd=JRXmlLoader.load("C:\\Users\\Dosh\\Documents\\NetBeansProjects\\intreprisemanagementsystem\\src\\intreprisemanagementsystem\\etatdebesoin.jrxml");
                String sql="select * from etat_de_besoin INNER JOIN projet ON etat_de_besoin.BUSS=projet.PROJET_NUM  where etat_de_besoin.NUM='"+tmp+"' and buss='"+buss.getSelectedItem()+"'";
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
          }else{
              showMessageDialog(null,"VOUS N'ETES PAS AUTORISE A IMPRIMER CE DOCUMENT");
          }
     }
               
         //REPORT
               public void reportss()
     {
       
         
      /// 
          
    
     String A =showInputDialog("ENTREZ LE ROLL_No!!!");
          if(A.equals("1234")){
              
          if(recherche.getText().equals("")){
              String tmp =(String) recherches.getText();
     
             try{
                 
                   String sqls="Select path from pathn";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
            if(rs.next()){
             
                String sum=rs.getString("path");
                  
                 String NameFiles=sum;
            
            String NameFile=""+NameFiles+"etatdebesoin.jrxml";
                 
                 
                 
                JasperDesign jd=JRXmlLoader.load(NameFile);
                 
                // String report ="C:\\Users\\Doshe\\Documents\\NetBeansProjects\\intreprisemanagementsystem\\src\\report\\newReportmateriau.jrxml";
           //     JasperDesign jd=JRXmlLoader.load("C:\\Users\\Dosh\\Documents\\NetBeansProjects\\intreprisemanagementsystem\\src\\intreprisemanagementsystem\\etatdebesoin.jrxml");
                String sql="select * from etat_de_besoin INNER JOIN projet ON etat_de_besoin.BUSS=projet.PROJET_NUM  ";// where NUM='"+tmp+"' and BUSS='"+buss.getSelectedItem()+"'";
                 HashMap param= new HashMap();
                 String User=homme.user.getText();
    param.put("nom",User);
                JRDesignQuery nq=new JRDesignQuery();
                nq.setText(sql);
                jd.setQuery(nq);
               
                 JasperReport jr =JasperCompileManager.compileReport(jd);
                 JasperPrint jp=JasperFillManager.fillReport(jr,param,con);
                 JasperViewer.viewReport(jp,false);
                 
                 
                 JasperViewer m= new JasperViewer(jp);
                 m.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            }
             }catch(Exception ex){
                  JOptionPane.showMessageDialog(null, ex);   
             }
          }else{
              String tmp =(String) recherche.getText();
               try{
                   
                     String sqls="Select path from pathn";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
            if(rs.next()){
             
                String sum=rs.getString("path");
                  
                 String NameFiles=sum;
            
            String NameFile=""+NameFiles+"etatdebesoin.jrxml";
                 
                 
                 
                JasperDesign jd=JRXmlLoader.load(NameFile);
                // String report ="C:\\Users\\Doshe\\Documents\\NetBeansProjects\\intreprisemanagementsystem\\src\\report\\newReportmateriau.jrxml";
               // JasperDesign jd=JRXmlLoader.load("C:\\Users\\Dosh\\Documents\\NetBeansProjects\\intreprisemanagementsystem\\src\\intreprisemanagementsystem\\etatdebesoin.jrxml");
                String sql="select * from etat_de_besoin INNER JOIN projet ON etat_de_besoin.BUSS=projet.PROJET_NUM  where etat_de_besoin.NUM='"+tmp+"' and buss='"+buss.getSelectedItem()+"'";
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
          }else{
              showMessageDialog(null,"VOUS N'ETES PAS AUTORISE A IMPRIMER CE DOCUMENT");
          }
     }
                          
         
                
    public void report2(){
         String tmp =(String) recherche.getText();
       try{
                   
                     String sqls="Select path from pathn";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
            if(rs.next()){
             
                String sum=rs.getString("path");
                  
                 String NameFiles=sum;
            
            String NameFile=""+NameFiles+"afpde.jrxml";
                 
                 
                 
                JasperDesign jd=JRXmlLoader.load(NameFile);
                // String report ="C:\\Users\\Doshe\\Documents\\NetBeansProjects\\intreprisemanagementsystem\\src\\report\\newReportmateriau.jrxml";
               // JasperDesign jd=JRXmlLoader.load("C:\\Users\\Dosh\\Documents\\NetBeansProjects\\intreprisemanagementsystem\\src\\intreprisemanagementsystem\\etatdebesoin.jrxml");
                String sql="select * from etat_de_besoin INNER JOIN projet ON etat_de_besoin.BUSS=projet.PROJET_NUM  where etat_de_besoin.NUM='"+tmp+"' and buss='"+buss.getSelectedItem()+"'";
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
                
        public void reportDMD(){
         String tmp =(String) recherche.getText();
       try{
                   
                     String sqls="Select path from pathn";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
            if(rs.next()){
             
                String sum=rs.getString("path");
                  
                 String NameFiles=sum;
            
            String NameFile=""+NameFiles+"afpde.jrxml";
                 
                 
                 
                JasperDesign jd=JRXmlLoader.load(NameFile);
                // String report ="C:\\Users\\Doshe\\Documents\\NetBeansProjects\\intreprisemanagementsystem\\src\\report\\newReportmateriau.jrxml";
               // JasperDesign jd=JRXmlLoader.load("C:\\Users\\Dosh\\Documents\\NetBeansProjects\\intreprisemanagementsystem\\src\\intreprisemanagementsystem\\etatdebesoin.jrxml");
               String sql="select*from etat_de_besoiN  INNER JOIN projet ON etat_de_besoiN.BUSS=projet.PROJET_NUm INNER JOIN  employee ON etat_de_besoiN.SUP= CONCAT(employee.NAME,' ',employee.LNAME) where etat_de_besoin.NUM='"+tmp+"' and buss='"+buss.getSelectedItem()+"'";
             //   String sql="select * from etat_de_besoin INNER JOIN projet ON etat_de_besoin.BUSS=projet.PROJET_NUM  where etat_de_besoin.NUM='"+tmp+"' and buss='"+buss.getSelectedItem()+"'";
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
        
           public void reportDMD_B(){
         String tmp =(String) recherche.getText();
       try{
                   
                     String sqls="Select path from pathn";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
            if(rs.next()){
             
                String sum=rs.getString("path");
                  
                 String NameFiles=sum;
            
            String NameFile=""+NameFiles+"afpde_1.jrxml";
                 
                 
                 
                JasperDesign jd=JRXmlLoader.load(NameFile);
                // String report ="C:\\Users\\Doshe\\Documents\\NetBeansProjects\\intreprisemanagementsystem\\src\\report\\newReportmateriau.jrxml";
               // JasperDesign jd=JRXmlLoader.load("C:\\Users\\Dosh\\Documents\\NetBeansProjects\\intreprisemanagementsystem\\src\\intreprisemanagementsystem\\etatdebesoin.jrxml");
               String sql="select * from etat_de_besoin_b INNER JOIN projet ON etat_de_besoin_b.BUSS=projet.PROJET_NUM  where etat_de_besoin_b.NUM='"+tmp+"' and buss='"+buss.getSelectedItem()+"'";
               // String sql="select*from etat_de_besoin_b  INNER JOIN projet ON etat_de_besoin_b.BUSS=projet.PROJET_NUm INNER JOIN  employee ON etat_de_besoin_b.SUP= employee.NAME  where etat_de_besoin_B.NUM='"+tmp+"' and buss='"+buss.getSelectedItem()+"'";
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
         
               
        
         public void call_sum(){
             NumberFormat nf = new DecimalFormat("#,###.##");
         
          try{
            String sql="select sum(pt) from etat_de_besoin  WHERE num= '"+recherche.getText()+"' and buss='"+buss.getSelectedItem()+"'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                
                Double sums=rs.getDouble("sum(pt)");
                //sum.setText(""+sums);
            String fn1 = nf.format(sums);
           sum.setText(fn1);
         //  compte.setText(fn1);
          
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
         
         }


          public void call_sumb(){
             NumberFormat nf = new DecimalFormat("#,###.##");
         
          try{
            String sql="select sum(pt) from etat_de_besoin_b  WHERE num= '"+recherche.getText()+"' and buss='"+buss.getSelectedItem()+"'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                
                Double sums=rs.getDouble("sum(pt)");
                //sum.setText(""+sums);
            String fn1 = nf.format(sums);
           sum.setText(fn1);
         //  compte.setText(fn1);
          
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
         
         }

         
         
          public void Call_IN_LIST()
    {DefaultListModel list = new DefaultListModel();
       
        DefaultListModel listS = new DefaultListModel();
         
        
         
        try{
            String sql="SELECT * FROM  ohada WHERE CLASS BETWEEN 6 AND 7";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
               // String sum=rs.getString("intitule_compte");
                String sums=rs.getString("code");
                 list.addElement(sums);
                
                 jList5.setModel(list);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
        
         try{
            String sql="SELECT * FROM  ohada  WHERE  class='5'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
               // String sum=rs.getString("intitule_compte");
                String sums=rs.getString("CODE");
               listS.addElement(sums);
                 jList3.setModel(listS);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
         
            try{
            String sql="select * from caisse_dispacting WHERE NAME='"+homme.user.getText()+"' and ACCESS='Yes'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                String sum=rs.getString("CAT");
                 buss.addItem(sum);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex); }
    }
    
    public void call_budget(){
        
  String tmp =(String)jList4.getSelectedValue();
    
     
        try{
          String sql="SELECT item,code FROM  budget_code WHERE code='"+tmp+"' and CAT='"+buss.getSelectedItem()+"'";
          
            pst=con.prepareStatement(sql);
            //pst.setString(1, tmp);
            rs=pst.executeQuery();
            if(rs.next()){
                 
                String ad1 = rs.getString("item");
              lb.setText(ad1);     
               String add1 = rs.getString("code");
              codeb.setText(add1);
               
               
          
          }
  
      }catch(Exception ex){
        JOptionPane.showMessageDialog(null,ex);  
      }
        
        
      try{
          String sql="SELECT LB,LBSUB,CAT,SUB_CAT FROM  budget WHERE code='"+tmp+"' and PROJECT='"+buss.getSelectedItem()+"'";
          
            pst=con.prepareStatement(sql);
            //pst.setString(1, tmp);
            rs=pst.executeQuery();
            if(rs.next()){
                 
                String ad1 = rs.getString("LB");
             code_cat.setText(ad1);     
               String add1 = rs.getString("LBSUB");
             code_subcat.setText(add1);
             CATB = rs.getString("CAT");
             SUB_CATB = rs.getString("SUB_CAT");
           //  ITMES=rs.getString("ITME");
          }
  
      }catch(Exception ex){
        JOptionPane.showMessageDialog(null,ex);  
      }
 }      
      public void clic_attCall_IN_LIST7()
    { 
        DefaultListModel list = new DefaultListModel();
 
        try{
          String sql="SELECT * FROM ohada where CODE ='"+jList3.getSelectedValue()+"'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                //String sum=rs.getString("nom");
                //String sum=rs.getString("nom");
                String sums=rs.getString("NAME");
                compte.setText(sums);
                
                String sums1=rs.getString("CODEMERE");
                code_m.setText(sums1);
                
                String sums2=rs.getString("CODE");
                code.setText(sums2);
                
                String  COMPTEMERES=rs.getString("COMPTEMERE");
                compte_m.setText(COMPTEMERES);
                 
                  CLASSE=rs.getString("CLASS");
                  SUBSTRS=rs.getString("SUBSTR");
                
                 
                
                
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
    }
      
       public void clic_attCall_IN_LIST8()
    { 
        DefaultListModel list = new DefaultListModel();
 
        try{
          String sql="SELECT * FROM ohada where CODE ='"+jList5.getSelectedValue()+"'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                //String sum=rs.getString("nom");
                //String sum=rs.getString("nom");
                String sums=rs.getString("NAME");
                compte1.setText(sums);
                
                String sums1=rs.getString("CODEMERE");
                code_m1.setText(sums1);
                
                String sums2=rs.getString("CODE");
                code1.setText(sums2);
                
                String  COMPTEMERES=rs.getString("COMPTEMERE");
                compte_m1.setText(COMPTEMERES);
                 
                  CLASSE1=rs.getString("CLASS");
                    SUBSTR=rs.getString("SUBSTR");
                
                 
                
                
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
    }   
        public void etroll()
     {
      try{
                  SimpleDateFormat dateFormatsS = new SimpleDateFormat("yyyy-MM-dd");
         String addDateS = dateFormatsS.format(jDateChooser1.getDate());
         
         String sql="SELECT NUM FROM ohada_trans where SUBSTR(NUM,5,10)='"+addDateS+"' ORDER BY NUM DESC LIMIT 1";
             pst=con.prepareStatement(sql);
             rs=pst.executeQuery();
             if(rs.next()){
                 String rl=rs.getString("NUM");
                 int ln=rl.length();
                 String stxt=rl.substring(0,15);
                 String snum=rl.substring(15,ln);
                 int n = Integer.parseInt(snum);
                 n++;
                 snum=Integer.toString(n);
                 rolls=stxt+snum;
             }else{
               rolls= "No: "+addDateS+"/1001";
                
             }
         }catch(NumberFormatException | SQLException e){
             JOptionPane.showMessageDialog(null, e);  
         }
             
             
         }
         
  
          
       
       public void save(){
          
       int rowS= jTable1.getSelectedRow();
        
        int indexs[]=jTable1.getSelectedRows();
       
        for(int i=0; i < indexs.length;i++){
            
          String dets = (jTable1.getModel().getValueAt(indexs[i],1). toString());
          String pts = (jTable1.getModel().getValueAt(indexs[i],4). toString());
          
          //String  addDate= (jTable1.getModel().getValueAt(indexs[i],5). toString());
                    SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(jDateChooser1.getDate());//jDateChooser1
        
        
         SimpleDateFormat dateFormat1 = new SimpleDateFormat("MMMM");
         SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy");
         String mois = dateFormat1.format(jDateChooser1.getDate());
         String annee = dateFormat2.format(jDateChooser1.getDate());
      //Double sumcredit = null;
     // Double sumcredits = null;
         etroll(); 
         Double c = Double.parseDouble(pts);
         if(roll.getText().isEmpty()){
         JOptionPane.showMessageDialog(null,"UnKnown Personnel");
         }else if(pts.isEmpty()){
         JOptionPane.showMessageDialog(null,"Wrong Data"+"\n"+"Wrong Data ","Error",JOptionPane.ERROR_MESSAGE);
        
         }else if(dets.isEmpty()){
       JOptionPane.showMessageDialog(null,"Wrong Data"+"\n"+"Wrong Data ","Error",JOptionPane.ERROR_MESSAGE);
        
         }else if(compte.getText().isEmpty()){
         JOptionPane.showMessageDialog(null,"Wrong Data"+"\n"+"Wrong Data ","Error",JOptionPane.ERROR_MESSAGE);
        
         }else if(compte1.getText().isEmpty()){
         JOptionPane.showMessageDialog(null,"Wrong Data"+"\n"+"Wrong Data ","Error",JOptionPane.ERROR_MESSAGE);
        
         }else if(lb.getText().isEmpty()){
        JOptionPane.showMessageDialog(null,"Wrong Data"+"\n"+"Wrong Data ","Error",JOptionPane.ERROR_MESSAGE);
        
         }else if(ECRI.equals("Yes")){
         JOptionPane.showMessageDialog(null,"Process already Done","Information",JOptionPane.WARNING_MESSAGE);//null,"Wrong Data"+"\n"+"Select one Caisse","Error",JOptionPane.ERROR_MESSAGE
        
         }else if(APPROV.equals("")){
         JOptionPane.showMessageDialog(null,"EB Not Yet Approuved","Information",JOptionPane.WARNING_MESSAGE);//null,"Wrong Data"+"\n"+"Select one Caisse","Error",JOptionPane.ERROR_MESSAGE
        
         }else if(jDateChooser1.getText().equals("")){
         JOptionPane.showMessageDialog(null,"Select Date ","Error",JOptionPane.ERROR_MESSAGE);//null,"Wrong Data"+"\n"+"Select one Caisse","Error",JOptionPane.ERROR_MESSAGE
        
         }else{
      Double aa,bb;
      
       String DEVICE = null; 
                 try{
            String sql="select * from monais WHERE caisse='"+jList3.getSelectedValue()+"'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            if(rs.next()){
            DEVICE=rs.getString("DEVICE");  
          //  jComboBox2.setSelectedItem(DEVICE);
            }
            
            if(DEVICE.equals("USD")){
         
         if(!devise.getText().equals("USD")){
             
               Double sale = null,buy,BUY,CDF,USD;
   
    Double PR= Double.parseDouble(pts);
     try{
String sqls="SELECT * FROM  currency where APPR='"+devise.getText()+"'";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
            while(rs.next()){
            //device=rs.getString("APPR")
             sale=rs.getDouble("SALE");
             buy=rs.getDouble("BUY"); 
            BUY=buy;
            }
          
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
     
   
     
  USD=PR/sale;
  CDF=PR;
  try { 
      PreparedStatement pst = con.prepareStatement("INSERT INTO `budget_trans`(`ITEM`, `DEBIT`, `CREDIT`, `SOLD`,`PROJET`, `CODE`, `NUM`, `CAT`, `DATES`, `MOIS`, `ANNEE`,SUB_CAT,CODE_CAT,CODE_SUB_CAT,`ITEMS`,BATCH) "
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
         pst.setString(1, dets+" Cfr "+recherche.getText());
         pst.setString(2,"0");
          pst.setDouble(3, USD);
          pst.setDouble(4, 0.0);
          pst.setString(5, buss.getSelectedItem().toString());
        pst.setString(6, codeb.getText());
         pst.setString(7,rolls);
         pst.setString(8,CATB);
         pst.setString(9, addDate);
         pst.setString(10,mois);
          pst.setString(11,annee);
            pst.setString(12,SUB_CATB);
             pst.setString(13,code_cat.getText());
              pst.setString(14,code_subcat.getText());
              pst.setString(15,lb.getText());
               pst.setString(16,jComboBox1.getSelectedItem().toString());
       pst.executeUpdate();
        
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());}
         
     try {
         PreparedStatement pst = con.prepareStatement("INSERT INTO `ohada_trans`(`COMPTE_M`, `COMPTE`, `CODE_M`, `CODE`, `CLASSE`, `SUBSTR`, `DEBIT`, `CREDIT`, `NUM`, `DATES`, `BUSS`, `LIBELLE`, `JOURNAL`, `CODE1`,DEBIT_CDF,CREDIT_CDF,DEVICE,LB)"
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        
        pst.setString(1, compte_m.getText());
        pst.setString(2, compte.getText());
         pst.setString(3, code_m.getText());
         pst.setString(4, code.getText());
         pst.setString(5,CLASSE);
         pst.setString(6,SUBSTRS);
         pst.setDouble(8,USD);
         pst.setString(7,"0");
         pst.setString(9,rolls);
         pst.setString(10, addDate);
         pst.setString(11,buss.getSelectedItem().toString());
         pst.setString(12,dets+" Cfr "+recherche.getText());
         pst.setString(13,"[JC]");
          pst.setString(14,code1.getText());
           pst.setString(15,"0");
            pst.setDouble(16,CDF);
             pst.setString(17,DEVICE);
              pst.setString(18,codeb.getText());
       pst.executeUpdate();
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
           try {
        PreparedStatement pst = con.prepareStatement("INSERT INTO `ohada_trans`(`COMPTE_M`, `COMPTE`, `CODE_M`, `CODE`, `CLASSE`, `SUBSTR`, `DEBIT`, `CREDIT`, `NUM`, `DATES`, `BUSS`, `LIBELLE`, `JOURNAL`, `CODE1`,DEBIT_CDF,CREDIT_CDF,DEVICE,LB)"
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        
        pst.setString(1, compte_m1.getText());
        pst.setString(2, compte1.getText());
         pst.setString(3, code_m1.getText());
         pst.setString(4, code1.getText());
         pst.setString(5,CLASSE1);
         pst.setString(6, SUBSTR);
         pst.setDouble(7,USD);
         pst.setString(8,"0");
         pst.setString(9,rolls);
         pst.setString(10, addDate);
         pst.setString(11,buss.getSelectedItem().toString());
         pst.setString(12,dets+" Cfr "+recherche.getText());
         pst.setString(13,"[JC]");
          pst.setString(14,code.getText());
           pst.setDouble(15,CDF);
            pst.setString(16,"0");
              pst.setString(17,DEVICE);
               pst.setString(18,codeb.getText());
       
          
         
          pst.executeUpdate();
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());}
      
           APPROV ="Yes";
            if(jRadioButton1.isSelected()){
             try{
 String sqls = "UPDATE etat_de_besoin_b SET ECRITURE=?,NUM_TRANS=? WHERE NUM =? and NUM_ID='"+IDS+"'";
        
         pst = con.prepareStatement(sqls);
         pst.setString(3,roll.getText());
          pst.setString(1,APPROV);
           pst.setString(2,rolls);
                     
         pst.executeUpdate();


}catch(Exception ex){JOptionPane.showMessageDialog(null,ex);}  
            }else{
             try{
 String sqls = "UPDATE etat_de_besoin SET ECRITURE=?,NUM_TRANS=? WHERE NUM =? and NUM_ID='"+IDS+"'";
        
         pst = con.prepareStatement(sqls);
         pst.setString(3,roll.getText());
          pst.setString(1,APPROV);
           pst.setString(2,rolls);
                     
         pst.executeUpdate();


}catch(Exception ex){JOptionPane.showMessageDialog(null,ex);}  
            }  
        
        
         }else{
         
            try {
         PreparedStatement pst = con.prepareStatement("INSERT INTO `budget_trans`(`ITEM`, `DEBIT`, `CREDIT`, `SOLD`,`PROJET`, `CODE`, `NUM`, `CAT`, `DATES`, `MOIS`, `ANNEE`,SUB_CAT,CODE_CAT,CODE_SUB_CAT,`ITEMS`,BATCH) "
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
         pst.setString(1, dets+" Cfr "+recherche.getText());
         pst.setString(2,"0");
          pst.setString(3, pts);
          pst.setDouble(4, 0.0);
          pst.setString(5, buss.getSelectedItem().toString());
        pst.setString(6, codeb.getText());
         pst.setString(7,rolls);
         pst.setString(8,CATB);
         pst.setString(9, addDate);
         pst.setString(10,mois);
          pst.setString(11,annee);
            pst.setString(12,SUB_CATB);
             pst.setString(13,code_cat.getText());
              pst.setString(14,code_subcat.getText());
              pst.setString(15,lb.getText());
               pst.setString(16,jComboBox1.getSelectedItem().toString());
      
          pst.executeUpdate();
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());}
    //--------------------------------------------------------------------------        
      //========>>>>DEBIT
   try {
         PreparedStatement pst = con.prepareStatement("INSERT INTO `ohada_trans`(`COMPTE_M`, `COMPTE`, `CODE_M`, `CODE`, `CLASSE`, `SUBSTR`, `DEBIT`, `CREDIT`, `NUM`, `DATES`, `BUSS`, `LIBELLE`, `JOURNAL`, `CODE1`,DEBIT_CDF,CREDIT_CDF,DEVICE,LB)"
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        
        pst.setString(1, compte_m.getText());
        pst.setString(2, compte.getText());
         pst.setString(3, code_m.getText());
         pst.setString(4, code.getText());
         pst.setString(5,CLASSE);
         pst.setString(6,SUBSTRS);
         pst.setString(8,pts);
         pst.setString(7,"0");
         pst.setString(9,rolls);
         pst.setString(10, addDate);
         pst.setString(11,buss.getSelectedItem().toString());
         pst.setString(12,dets+" Cfr "+recherche.getText());
         pst.setString(13,"[JC]");
          pst.setString(14,code1.getText());
           pst.setString(15,"0");
            pst.setString(16,"0");
             pst.setString(17,"USD");
              pst.setString(18,codeb.getText());
          pst.executeUpdate();
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
      
           try {
        PreparedStatement pst = con.prepareStatement("INSERT INTO `ohada_trans`(`COMPTE_M`, `COMPTE`, `CODE_M`, `CODE`, `CLASSE`, `SUBSTR`, `DEBIT`, `CREDIT`, `NUM`, `DATES`, `BUSS`, `LIBELLE`, `JOURNAL`, `CODE1`,DEBIT_CDF,CREDIT_CDF,DEVICE,LB)"
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        
        pst.setString(1, compte_m1.getText());
        pst.setString(2, compte1.getText());
         pst.setString(3, code_m1.getText());
         pst.setString(4, code1.getText());
         pst.setString(5,CLASSE1);
         pst.setString(6, SUBSTR);
         pst.setString(7,pts);
         pst.setString(8,"0");
         pst.setString(9,rolls);
         pst.setString(10, addDate);
         pst.setString(11,buss.getSelectedItem().toString());
         pst.setString(12,dets+" Cfr "+recherche.getText());
         pst.setString(13,"[JC]");
          pst.setString(14,code.getText());
           pst.setString(15,"0");
            pst.setString(16,"0");
              pst.setString(17,"USD");
               pst.setString(18,codeb.getText());
      pst.executeUpdate();
        
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());}
      
          
         APPROV ="Yes";
            if(jRadioButton1.isSelected()){
             try{
 String sqls = "UPDATE etat_de_besoin_b SET ECRITURE=?,NUM_TRANS=? WHERE NUM =? and NUM_ID='"+IDS+"'";
        
         pst = con.prepareStatement(sqls);
         pst.setString(3,roll.getText());
          pst.setString(1,APPROV);
           pst.setString(2,rolls);
                     
         pst.executeUpdate();


}catch(Exception ex){JOptionPane.showMessageDialog(null,ex);}  
            }else{
             try{
 String sqls = "UPDATE etat_de_besoin SET ECRITURE=?,NUM_TRANS=? WHERE NUM =? and NUM_ID='"+IDS+"'";
        
         pst = con.prepareStatement(sqls);
         pst.setString(3,roll.getText());
          pst.setString(1,APPROV);
           pst.setString(2,rolls);
                     
         pst.executeUpdate();


}catch(Exception ex){JOptionPane.showMessageDialog(null,ex);}  
            }  
         
     }
        
            }else{
               // currcy
    Double sale = null,buy,BUY,CDF,USD;
   
    Double PR= Double.parseDouble(pts);
     try{
String sqls="SELECT * FROM  currency where APPR='"+DEVICE+"'";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
            while(rs.next()){
            //device=rs.getString("APPR")
             sale=rs.getDouble("SALE");
             buy=rs.getDouble("BUY"); 
            BUY=buy;
            }
          
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }  
  USD=PR/sale;
  CDF=PR;
      try {
         PreparedStatement pst = con.prepareStatement("INSERT INTO `budget_trans`(`ITEM`, `DEBIT`, `CREDIT`, `SOLD`,`PROJET`, `CODE`, `NUM`, `CAT`, `DATES`, `MOIS`, `ANNEE`,SUB_CAT,CODE_CAT,CODE_SUB_CAT,`ITEMS`,BATCH) "
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
         pst.setString(1, dets+" Cfr "+recherche.getText());
         pst.setString(2,"0");
          pst.setDouble(3, USD);
          pst.setDouble(4, 0.0);
          pst.setString(5, buss.getSelectedItem().toString());
        pst.setString(6, codeb.getText());
         pst.setString(7,rolls);
         pst.setString(8,CATB);
         pst.setString(9, addDate);
         pst.setString(10,mois);
          pst.setString(11,annee);
            pst.setString(12,SUB_CATB);
             pst.setString(13,code_cat.getText());
              pst.setString(14,code_subcat.getText());
              pst.setString(15,lb.getText());
              pst.setString(16,jComboBox1.getSelectedItem().toString());
       pst.executeUpdate();
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());}
      
      //-------------------------------------------------------------------------------
       //========>>>>DEBIT
         try {
         PreparedStatement pst = con.prepareStatement("INSERT INTO `ohada_trans`(`COMPTE_M`, `COMPTE`, `CODE_M`, `CODE`, `CLASSE`, `SUBSTR`, `DEBIT`, `CREDIT`, `NUM`, `DATES`, `BUSS`, `LIBELLE`, `JOURNAL`, `CODE1`,DEBIT_CDF,CREDIT_CDF,DEVICE,LB)"
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        
        pst.setString(1, compte_m.getText());
        pst.setString(2, compte.getText());
         pst.setString(3, code_m.getText());
         pst.setString(4, code.getText());
         pst.setString(5,CLASSE);
         pst.setString(6,SUBSTRS);
         pst.setDouble(8,USD);
         pst.setString(7,"0");
         pst.setString(9,rolls);
         pst.setString(10, addDate);
         pst.setString(11,buss.getSelectedItem().toString());
         pst.setString(12,dets+" Cfr "+recherche.getText());
         pst.setString(13,"[JC]");
          pst.setString(14,code1.getText());
           pst.setString(15,"0");
            pst.setDouble(16,CDF);
             pst.setString(17,DEVICE);
              pst.setString(18,codeb.getText());
          pst.executeUpdate();
        
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
      
           try {
        PreparedStatement pst = con.prepareStatement("INSERT INTO `ohada_trans`(`COMPTE_M`, `COMPTE`, `CODE_M`, `CODE`, `CLASSE`, `SUBSTR`, `DEBIT`, `CREDIT`, `NUM`, `DATES`, `BUSS`, `LIBELLE`, `JOURNAL`, `CODE1`,DEBIT_CDF,CREDIT_CDF,DEVICE,LB)"
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        
        pst.setString(1, compte_m1.getText());
        pst.setString(2, compte1.getText());
         pst.setString(3, code_m1.getText());
         pst.setString(4, code1.getText());
         pst.setString(5,CLASSE1);
         pst.setString(6, SUBSTR);
         pst.setDouble(7,USD);
         pst.setString(8,"0");
         pst.setString(9,rolls);
         pst.setString(10, addDate);
         pst.setString(11,buss.getSelectedItem().toString());
         pst.setString(12,dets+" Cfr "+recherche.getText());
         pst.setString(13,"[JC]");
          pst.setString(14,code.getText());
           pst.setDouble(15,CDF);
            pst.setString(16,"0");
              pst.setString(17,DEVICE);
               pst.setString(18,codeb.getText());
          pst.executeUpdate();
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());}
      
           
         
         APPROV ="Yes";
            if(jRadioButton1.isSelected()){
             try{
 String sqls = "UPDATE etat_de_besoin_b SET ECRITURE=?,NUM_TRANS=? WHERE NUM =? and NUM_ID='"+IDS+"'";
        
         pst = con.prepareStatement(sqls);
         pst.setString(3,roll.getText());
          pst.setString(1,APPROV);
           pst.setString(2,rolls);
                     
         pst.executeUpdate();


}catch(Exception ex){JOptionPane.showMessageDialog(null,ex);}  
            }else{
             try{
 String sqls = "UPDATE etat_de_besoin SET ECRITURE=?,NUM_TRANS=? WHERE NUM =? and NUM_ID='"+IDS+"'";
        
         pst = con.prepareStatement(sqls);
         pst.setString(3,roll.getText());
          pst.setString(1,APPROV);
           pst.setString(2,rolls);
                     
         pst.executeUpdate();


}catch(Exception ex){JOptionPane.showMessageDialog(null,ex);}  
            }  
        
            } 
            } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());} 
         }
        }
        JOptionPane.showMessageDialog(null,"Transaction Saved");
       }
       
        public void refreh(){
     lb.setText("LB");
     codeb.setText("");
     
     compte.setText("");
     code.setText("");
     code_m.setText("");
     compte_m.setText("");
      compte1.setText("");
     code1.setText("");
     code_m1.setText("");
     compte_m1.setText("");
     
     
      sup.setText("");
     chat.setText("");
     
     roll.setText("");
        }
       
         public void selectontable(){
        int indexs[]=jTable1.getSelectedRows();
       
        for(int i=0; i < indexs.length;i++){
          String Table_click = (jTable1.getModel().getValueAt(indexs[i],0). toString());
           try{
    String appr ="LOGISTIQUE";
 String sql = "UPDATE etat_de_besoin SET ORIENTATION=? WHERE ID ='"+Table_click+"'";
        
         pst = con.prepareStatement(sql);
        //  pst.setString(2,recherche.getText());
       //  pst.setString(2,homme.user.getText());
          pst.setString(1,appr);
                     
         pst.executeUpdate();


}catch(Exception ex){JOptionPane.showMessageDialog(null,ex);}
         
   }
                }
         
         public void callcode(){
         
          try{
      DefaultListModel listSS = new DefaultListModel();
     
            String sql="SELECT * FROM  budget_code  WHERE code like '"+jTextField1.getText()+"%' and CAT='"+buss.getSelectedItem()+"'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
               // String sum=rs.getString("intitule_compte");
                String sums=rs.getString("CODE");
               listSS.addElement(sums);
                 jList4.setModel(listSS);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex); } 
         
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
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        sum = new javax.swing.JTextField();
        buss = new javax.swing.JComboBox<>();
        devise = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        sup = new javax.swing.JTextField();
        roll = new javax.swing.JTextField();
        chat = new javax.swing.JTextField();
        jDateChooser1 = new com.alee.extended.date.WebDateField();
        jRadioButton1 = new javax.swing.JRadioButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        recherche = new javax.swing.JTextField();
        recherches = new javax.swing.JTextField();
        date1 = new com.alee.extended.date.WebDateField();
        date2 = new com.alee.extended.date.WebDateField();
        jPanel9 = new javax.swing.JPanel();
        jButton5 = new javax.swing.JButton();
        jComboBox2 = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jList4 = new javax.swing.JList<>();
        jTextField1 = new javax.swing.JTextField();
        jPanel11 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jList3 = new javax.swing.JList<>();
        jTextField4 = new javax.swing.JTextField();
        jPanel12 = new javax.swing.JPanel();
        compte = new javax.swing.JTextField();
        code_m = new javax.swing.JTextField();
        code = new javax.swing.JTextField();
        compte_m = new javax.swing.JTextField();
        jPanel14 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jList5 = new javax.swing.JList<>();
        jTextField5 = new javax.swing.JTextField();
        jPanel13 = new javax.swing.JPanel();
        compte1 = new javax.swing.JTextField();
        code_m1 = new javax.swing.JTextField();
        code1 = new javax.swing.JTextField();
        compte_m1 = new javax.swing.JTextField();
        jPanel15 = new javax.swing.JPanel();
        codeb = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        lb = new javax.swing.JTextField();
        code_cat = new javax.swing.JTextField();
        code_subcat = new javax.swing.JTextField();
        jPanel16 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel17 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jPanel2MouseDragged(evt);
            }
        });
        jPanel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel2MousePressed(evt);
            }
        });
        jPanel2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jPanel2KeyPressed(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel3.setForeground(new java.awt.Color(153, 255, 153));

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        sum.setEditable(false);
        sum.setBackground(new java.awt.Color(0, 0, 0));
        sum.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        sum.setForeground(new java.awt.Color(255, 255, 255));
        sum.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        buss.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        buss.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select One Project" }));
        buss.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                bussPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        devise.setEditable(false);
        devise.setBackground(new java.awt.Color(0, 0, 0));
        devise.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        devise.setForeground(new java.awt.Color(255, 255, 255));
        devise.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buss, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(sum, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(devise, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(sum)
                    .addComponent(devise))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buss, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "StateOf Need Infos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        sup.setBackground(new java.awt.Color(204, 204, 255));
        sup.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        roll.setEditable(false);
        roll.setBackground(new java.awt.Color(204, 204, 255));
        roll.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        roll.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                rollKeyTyped(evt);
            }
        });

        chat.setBackground(new java.awt.Color(204, 204, 255));
        chat.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        jDateChooser1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jDateChooser1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("yyyy-MM-dd"))));

        jRadioButton1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jRadioButton1.setText("Back Up EB");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sup, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)
            .addComponent(chat)
            .addComponent(roll)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRadioButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jRadioButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sup, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(roll, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTable1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
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

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Search", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        recherche.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        recherche.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                rechercheKeyReleased(evt);
            }
        });

        recherches.setEditable(false);
        recherches.setForeground(new java.awt.Color(240, 240, 240));
        recherches.setBorder(null);
        recherches.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                recherchesActionPerformed(evt);
            }
        });
        recherches.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                recherchesKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                recherchesKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                recherchesKeyTyped(evt);
            }
        });

        date1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        date2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(recherche, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(date1, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(date2, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(recherches, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(recherche, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(recherches, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(date1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(date2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Command", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jButton5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton5.setText("Search");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jComboBox2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Send For Approuval", "Send To Log" }));
        jComboBox2.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                jComboBox2PopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(jButton5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton5)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 441, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTable2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1"
            }
        ));
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jTable2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTable2KeyPressed(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Accounting Area/ Comptabilite generale", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "LB", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jList4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jList4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jList4MouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(jList4);

        jTextField1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE))
        );

        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ohada1", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jList3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jList3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jList3MouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(jList3);

        jTextField4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jTextField4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField4KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addComponent(jTextField4, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
        );

        jPanel12.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        compte.setEditable(false);
        compte.setBackground(new java.awt.Color(240, 240, 241));
        compte.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        compte.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        code_m.setEditable(false);
        code_m.setBackground(new java.awt.Color(240, 240, 241));
        code_m.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        code_m.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        code.setEditable(false);
        code.setBackground(new java.awt.Color(240, 240, 241));
        code.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        code.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        compte_m.setEditable(false);
        compte_m.setBackground(new java.awt.Color(240, 240, 241));
        compte_m.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        compte_m.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(compte_m)
                    .addComponent(compte)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(code_m, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(code, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(compte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(code_m, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(code, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(compte_m, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ohada2", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jList5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jList5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jList5MouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(jList5);

        jTextField5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jTextField5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField5KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addComponent(jTextField5, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE))
        );

        jPanel13.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        compte1.setEditable(false);
        compte1.setBackground(new java.awt.Color(240, 240, 241));
        compte1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        compte1.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        code_m1.setEditable(false);
        code_m1.setBackground(new java.awt.Color(240, 240, 241));
        code_m1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        code_m1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        code_m1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                code_m1ActionPerformed(evt);
            }
        });

        code1.setEditable(false);
        code1.setBackground(new java.awt.Color(240, 240, 241));
        code1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        code1.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        compte_m1.setEditable(false);
        compte_m1.setBackground(new java.awt.Color(240, 240, 241));
        compte_m1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        compte_m1.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(compte_m1)
                    .addComponent(compte1)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(code_m1, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(code1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(compte1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(code_m1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(code1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(compte_m1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel15.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        codeb.setEditable(false);
        codeb.setBackground(new java.awt.Color(240, 240, 241));
        codeb.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        codeb.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jButton4.setText("Ok");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        lb.setEditable(false);
        lb.setBackground(new java.awt.Color(240, 240, 241));
        lb.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        code_cat.setEditable(false);
        code_cat.setBackground(new java.awt.Color(240, 240, 241));
        code_cat.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        code_cat.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        code_subcat.setEditable(false);
        code_subcat.setBackground(new java.awt.Color(240, 240, 241));
        code_subcat.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        code_subcat.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lb)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(codeb)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton4))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(code_cat, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(code_subcat, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(code_cat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(code_subcat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(codeb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jPanel13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jPanel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel16.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Command", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jButton3.setBackground(new java.awt.Color(204, 204, 255));
        jButton3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton3.setText("Print(Ctrl+P)");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(51, 255, 51));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton1.setText("Print DMD ACHAT");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(255, 51, 51));
        jButton2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton2.setText("Remove");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel17.setBackground(new java.awt.Color(204, 204, 204));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_Refresh_16px.png"))); // NOI18N
        jLabel7.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/cancel.png"))); // NOI18N
        jLabel6.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jComboBox1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Batchs" }));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
                    .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
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

    private void bussPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_bussPopupMenuWillBecomeInvisible
        try{
            DefaultListModel listSS = new DefaultListModel();
            String sql="SELECT * FROM  budget_code  WHERE  CAT='"+buss.getSelectedItem()+"'";

            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                // String sum=rs.getString("intitule_compte");
                String sums=rs.getString("CODE");
                listSS.addElement(sums);
                jList4.setModel(listSS);
            }
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex); }
        try{

            String sql="SELECT distinct NUM FROM etat_de_besoin WHERE BUSS='"+buss.getSelectedItem()+"' AND ORIENTATION='FINANCE' AND ORIENTATION2='Fin' and PRINT=''";
            pst = con.prepareStatement(sql);
            rs= pst.executeQuery();
            jTable2.setModel(DbUtils.resultSetToTableModel(rs));

        }catch(SQLException ex ){
            JOptionPane.showMessageDialog(null, ex);
        }

        try{

            String sql="SELECT `NUM_ID` AS NUM,`DET`, `QTY`, `PU`, `PT`, `DATES`, `APPROUVATION` FROM `etat_de_besoin` WHERE  `ORIENTATION`='FINANCE' AND ORIENTATION2='Fin' and buss='"+buss.getSelectedItem()+"'";
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
            TableColumn col5=jTable1.getColumnModel().getColumn(5);
            TableColumn col6=jTable1.getColumnModel().getColumn(6);
            //        TableColumn col7=jTable1.getColumnModel().getColumn(7);
            //        TableColumn col8=jTable1.getColumnModel().getColumn(8);
            //        TableColumn col9=jTable1.getColumnModel().getColumn(9);

            //`ID`,`SUP`, `CHANT`, `NUM`, `DET`, `QTY`, `PU`, `PT`, `DATES`, `APPROUVATION`
            col0.setPreferredWidth(5);
            col1.setPreferredWidth(250);
            col2.setPreferredWidth(30);
            col3.setPreferredWidth(50);
            col4.setPreferredWidth(50);
            col5.setPreferredWidth(80);
            col6.setPreferredWidth(100);
            //       col7.setPreferredWidth(20);
            //       col8.setPreferredWidth(30);
            //       col9.setPreferredWidth(50);

        }catch(SQLException ex ){
            JOptionPane.showMessageDialog(null, ex);
        }
        
        jComboBox1.removeAllItems();
     jComboBox1.addItem("Batchs"); 
     try{
          String sql="SELECT * FROM batchs where projet='"+buss.getSelectedItem()+"'";
             
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                //String sum=rs.getString("nom");
                String sums=rs.getString("BATCH");
                
                
               jComboBox1.addItem(sums);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }

        // HERE// TODO add your handling code here:
    }//GEN-LAST:event_bussPopupMenuWillBecomeInvisible

    private void rollKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_rollKeyTyped
        //search();         // TODO add your handling code here:
    }//GEN-LAST:event_rollKeyTyped

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
    if(jRadioButton1.isSelected()){
        //select_jTableb(); before
        select_jTable(); //after
       call_sumb();
       }else{
       select_jTable();
        call_sum();
       }// TODO add your handling code here:
    }//GEN-LAST:event_jTable1MouseClicked

    private void rechercheKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_rechercheKeyReleased
       if(jRadioButton1.isSelected()){
       searchSB();
       call_sumb();
       }else{
       searchS();
        call_sum();
       }
        
        // TODO add your handling code here:
    }//GEN-LAST:event_rechercheKeyReleased

    private void recherchesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_recherchesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_recherchesActionPerformed

    private void recherchesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_recherchesKeyPressed
        //search();         // TODO add your handling code here:
    }//GEN-LAST:event_recherchesKeyPressed

    private void recherchesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_recherchesKeyReleased
        // search();     // TODO add your handling code here:
    }//GEN-LAST:event_recherchesKeyReleased

    private void recherchesKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_recherchesKeyTyped
        //        search();         // TODO add your handling code here:
    }//GEN-LAST:event_recherchesKeyTyped

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String tmp = dateFormat.format(date1.getDate());

        SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
        String tmps = dateFormats.format(date2.getDate());
         JOptionPane.showMessageDialog(null,tmp+"  and "+tmps);

//        try{
//            String sql="SELECT * FROM  etat_de_besoin WHERE DATES BETWEEN '"+tmp+"' and '"+tmps+"' ";//'"+tmp+"'and'"+tmps+"'";
//
//            pst=con.prepareStatement(sql);
//            rs= pst.executeQuery();
//            jTable1.setModel(DbUtils.resultSetToTableModel(rs));
//            TableColumn col1=jTable1.getColumnModel().getColumn(1);
//            col1.setPreferredWidth(200);
//
//        }catch(SQLException | HeadlessException ex ){
//            JOptionPane.showMessageDialog(null,ex);
//            
//
//        }
        if(jRadioButton1.isSelected()){
            try{
             String sql="SELECT `NUM_ID` AS NUM,`DET`, `QTY`, `PU`, `PT`, `DATES`, `APPROUVATION` FROM `etat_de_besoin_B` WHERE   buss='"+buss.getSelectedItem()+"' AND `ORIENTATION`='FINANCE' AND DATES BETWEEN '"+tmp+"' and '"+tmps+"'";
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
        TableColumn col5=jTable1.getColumnModel().getColumn(5);
        TableColumn col6=jTable1.getColumnModel().getColumn(6);   
        
       col0.setPreferredWidth(5);
       col1.setPreferredWidth(250);
       col2.setPreferredWidth(30);
       col3.setPreferredWidth(50);
       col4.setPreferredWidth(50);
       col5.setPreferredWidth(80);
       col6.setPreferredWidth(100);
       
     
    }catch(SQLException ex ){
     JOptionPane.showMessageDialog(null, ex);
}
        }else{
            try{
           
             String sql="SELECT `NUM_ID` AS NUM,`DET`, `QTY`, `PU`, `PT`, `DATES`, `APPROUVATION` FROM `etat_de_besoin` WHERE DATES BETWEEN '"+tmp+"' and '"+tmps+"'  AND `ORIENTATION`='FINANCE' and buss='"+buss.getSelectedItem()+"'";
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
        TableColumn col5=jTable1.getColumnModel().getColumn(5);
        TableColumn col6=jTable1.getColumnModel().getColumn(6);       
       col0.setPreferredWidth(5);
       col1.setPreferredWidth(250);
       col2.setPreferredWidth(30);
       col3.setPreferredWidth(50);
       col4.setPreferredWidth(50);
       col5.setPreferredWidth(80);
       col6.setPreferredWidth(100);
       
     
    }catch(SQLException ex ){
     JOptionPane.showMessageDialog(null, ex);
}
        
        }
      
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jComboBox2PopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_jComboBox2PopupMenuWillBecomeInvisible
  try{
            String sql="select ACESS from user_write where NAME='"+user.getText()+"' and CHAMP='Send EB for Approval'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            if(rs.next()){
          String  caisse= rs.getString("ACESS");
          if(caisse.equals("Yes")){
      if(jComboBox2.getSelectedItem().equals("Send For Approuval")){

            int response = JOptionPane.showConfirmDialog(null, "DO YOU WANT TO SEND THIS FOR APPROUVAL?", "Confirm",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (response == JOptionPane.YES_OPTION) {
             if(jRadioButton1.isSelected()){
              try{
                    String appr =homme.user.getText();
                    String sqls = "UPDATE etat_de_besoin_b SET EXCECUTE=?,ETUDE=? WHERE NUM =?";

                    pst = con.prepareStatement(sqls);
                    pst.setString(3,recherche.getText());
                    pst.setString(1,appr);
                    pst.setString(2,appr);

                    pst.executeUpdate();

                }catch(Exception ex){JOptionPane.showMessageDialog(null,ex);}
             }else{
              try{
                    String appr =homme.user.getText();
                    String sqls = "UPDATE etat_de_besoin SET EXCECUTE=?,ETUDE=? WHERE NUM =?";

                    pst = con.prepareStatement(sqls);
                    pst.setString(3,recherche.getText());
                    pst.setString(1,appr);
                    pst.setString(2,appr);

                    pst.executeUpdate();

                }catch(Exception ex){JOptionPane.showMessageDialog(null,ex);}
             }
               
                //searchS();
            } else if (response == JOptionPane.NO_OPTION) {
                searchS();
            }
        }else{
            int response = JOptionPane.showConfirmDialog(null, "DO YOU WANT TO SEND THIS TO LOGISTIQUE?", "Confirm",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (response == JOptionPane.YES_OPTION) {
                selectontable();
                searchS();
            }else if (response == JOptionPane.NO_OPTION) {
                searchS();
            }
        }
          }else{
       JOptionPane.showMessageDialog(null,"Seul le financier(e) est autorisé(e)","Avertissement",JOptionPane.WARNING_MESSAGE);
          }
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex); }


             // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox2PopupMenuWillBecomeInvisible

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel6MouseClicked

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
        showEBData() ;       // TODO add your handling code here:
    }//GEN-LAST:event_jLabel7MouseClicked

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        select_jTableNUM();
        searchS();
        call_sum();
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable2MouseClicked

    private void jTable2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable2KeyPressed

    private void jList4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList4MouseClicked
        call_budget();        // TODO add your handling code here:
    }//GEN-LAST:event_jList4MouseClicked

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
        callcode();        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1KeyReleased

    private void jList3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList3MouseClicked
        clic_attCall_IN_LIST7();        // TODO add your handling code here:
    }//GEN-LAST:event_jList3MouseClicked

    private void jList5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList5MouseClicked
        clic_attCall_IN_LIST8();        // TODO add your handling code here:
    }//GEN-LAST:event_jList5MouseClicked

    private void code_m1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_code_m1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_code_m1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        Double SUMDEBIT = null,SUMCREDIT = null,CHECK_OHADA = null,CHECK_BUDGET = null;
        int row= jTable1.getSelectedRow();
        String Table_click = (jTable1.getModel().getValueAt(row,4). toString());
        Double PR= Double.parseDouble(Table_click );
        String DEVICE=devise.getText();

        Double sale = null,buy,BUY,CDF,USD;

        try{
            String sqls="SELECT * FROM  currency where APPR='"+DEVICE+"'";

            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
            while(rs.next()){
                //device=rs.getString("APPR")
                sale=rs.getDouble("SALE");
                buy=rs.getDouble("BUY");
                BUY=buy;
            }

        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex);
        }
        if(devise.getText().equals("USD")){

            USD=PR;
        }else{
            USD=PR/sale;
            CDF=PR;
        }

        try{

            String sql="SELECT   sum(DEBIT),sum(CREDIT) FROM ohada_trans WHERE code='"+code.getText()+"' AND BUSS='"+buss.getSelectedItem()+"'";

            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                SUMDEBIT=rs.getDouble("sum(DEBIT)");
                SUMCREDIT=rs.getDouble("sum(CREDIT)");

                CHECK_OHADA=SUMDEBIT-SUMCREDIT;

            }
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex);
        }

        try{

            String sqls="SELECT   sum(DEBIT),sum(CREDIT) FROM budget_trans WHERE code='"+codeb.getText()+"' AND PROJET='"+buss.getSelectedItem()+"'";

            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
            while(rs.next()){
                SUMDEBIT=rs.getDouble("sum(DEBIT)");
                SUMCREDIT=rs.getDouble("sum(CREDIT)");

                try{
                    String sqlS="SELECT   NUM FROM PROJET WHERE PROJET_NUM='"+buss.getSelectedItem()+"'";

                    pst=con.prepareStatement(sqlS);
                    rs=pst.executeQuery();
                    while(rs.next()){
                        ECART=rs.getDouble("NUM");

                    }
                }
                catch(Exception ex){
                    JOptionPane.showMessageDialog(null, ex);
                }

            }
            CHECK_BUDGETP=SUMCREDIT+USD;
            CHECK_BUDGET=(CHECK_BUDGETP*100)/SUMDEBIT;

        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex);
        }

        if(CHECK_OHADA <USD){

            JOptionPane.showMessageDialog(null,"Wrong Data"+"\n"+"No Enough Resources  ","Error",JOptionPane.ERROR_MESSAGE);
        }else if(CHECK_BUDGET >ECART){
            JOptionPane.showMessageDialog(null,"Wrong Data"+"\n"+"No Budget  ","Error",JOptionPane.ERROR_MESSAGE);
        }else if(jComboBox1.getSelectedItem().equals("Batchs")){
            JOptionPane.showMessageDialog(null,"Wrong Data"+"\n"+"No Batchs  ","Error",JOptionPane.ERROR_MESSAGE);
        }else{
            save();
            refreh();}// TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        //  selectproject m = new selectproject();
        if(APPROV.isEmpty()){
            JOptionPane.showMessageDialog(null,"YOU ARE NOT AUTHORISED TO PRINT A EB WHICH IS NOT YET APPOUVED !!!");
        }
        else if(recherche.getText().isEmpty()){
            JOptionPane.showMessageDialog(null,"PLAISE SELECT ONE DOCUMENT FOR PRINTING");
        }
            else{
            if(jRadioButton1.isSelected()){
                 try{
                    String appr ="Print";
                    String sql = "UPDATE etat_de_besoin_b SET PRINT=? WHERE NUM =?";

                    pst = con.prepareStatement(sql);
                    pst.setString(2,recherche.getText());
                    pst.setString(1,appr);

                    pst.executeUpdate();

                }catch(Exception ex){JOptionPane.showMessageDialog(null,ex);}
                showEBData();
                report();
            
            }else{
                 try{
                    String appr ="Print";
                    String sql = "UPDATE etat_de_besoin SET PRINT=? WHERE NUM =?";

                    pst = con.prepareStatement(sql);
                    pst.setString(2,recherche.getText());
                    pst.setString(1,appr);

                    pst.executeUpdate();

                }catch(Exception ex){JOptionPane.showMessageDialog(null,ex);}
                showEBData();
                report();
            }
           
            }

            // m. setDefaultCloseOperation(DISPOSE_ON_CLOSE);//          // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
if(jRadioButton1.isSelected()){
 reportDMD_B();
}else{
 reportDMD();
}
       

        //roll.setText(null);// TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
//SimpleDateFormat dateFormatsS = new SimpleDateFormat("yyyy-MM-dd");
//         String addDateS = dateFormatsS.format(jDateChooser1.getDate());
//         JOptionPane.showMessageDialog(null,addDateS);

        delete();
        searchS();
        call_sum();// TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jPanel2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPanel2KeyPressed
           // TODO add your handling code here:
    }//GEN-LAST:event_jPanel2KeyPressed

    private void jPanel2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MousePressed
x= evt.getX();
        y= evt.getY();//         // TODO add your handling code here:
    }//GEN-LAST:event_jPanel2MousePressed

    private void jPanel2MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseDragged
 int xx= evt.getXOnScreen();
        int yy= evt.getYOnScreen();
        this.setLocation(xx-x,yy-y);        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel2MouseDragged

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void jTextField5KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField5KeyReleased
 DefaultListModel list = new DefaultListModel();
        try{
            String sql="SELECT * FROM  ohada WHERE CLASS BETWEEN 6 AND 7 and code like'"+jTextField5.getText()+"%'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
               // String sum=rs.getString("intitule_compte");
                String sums=rs.getString("code");
                 list.addElement(sums);
                
                 jList5.setModel(list);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField5KeyReleased

    private void jTextField4KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField4KeyReleased
 DefaultListModel list = new DefaultListModel();
        try{
            String sql="SELECT * FROM  ohada  WHERE  class='5' and code like '"+jTextField4.getText()+"%'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
               // String sum=rs.getString("intitule_compte");
                String sums=rs.getString("CODE");
               list.addElement(sums);
                 jList3.setModel(list);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField4KeyReleased

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
            java.util.logging.Logger.getLogger(etat_de_besoin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(etat_de_besoin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(etat_de_besoin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(etat_de_besoin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new etat_de_besoin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> buss;
    private javax.swing.JTextField chat;
    private javax.swing.JTextField code;
    private javax.swing.JTextField code1;
    private javax.swing.JTextField code_cat;
    private javax.swing.JTextField code_m;
    private javax.swing.JTextField code_m1;
    private javax.swing.JTextField code_subcat;
    private javax.swing.JTextField codeb;
    private javax.swing.JTextField compte;
    private javax.swing.JTextField compte1;
    private javax.swing.JTextField compte_m;
    private javax.swing.JTextField compte_m1;
    private com.alee.extended.date.WebDateField date1;
    private com.alee.extended.date.WebDateField date2;
    private javax.swing.JTextField devise;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    public static javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    public static javax.swing.JButton jButton5;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private com.alee.extended.date.WebDateField jDateChooser1;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JList<String> jList3;
    private javax.swing.JList<String> jList4;
    private javax.swing.JList<String> jList5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTable jTable1;
    public static javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField lb;
    public static javax.swing.JTextField recherche;
    public static javax.swing.JTextField recherches;
    private javax.swing.JTextField roll;
    private javax.swing.JTextField sum;
    private javax.swing.JTextField sup;
    // End of variables declaration//GEN-END:variables
}
