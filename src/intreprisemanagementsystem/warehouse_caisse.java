 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intreprisemanagementsystem;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import net.proteanit.sql.DbUtils;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

/**
 *
 * @author DOSHE
 */
public class warehouse_caisse extends javax.swing.JPanel {

    DefaultTableModel mode;
Connection con=null;
PreparedStatement pst=null;
ResultSet rs= null;
String ID=null;
String check=null;
String check2=null;
String rolls;
String annee;
String mois;
Double USD,USH,CDF;
Double caissesdebit = null;
 SimpleDateFormat dateFormats = new SimpleDateFormat("dd-MM-yyyy");

    public warehouse_caisse() {
        initComponents();
              con=JavaDbConnectUMCO.dbConnect();
         buss();
         attCall_IN_LIST1();
        // 
         callcaise();
         webDateField1.setDate(new Date());
         Groupe1();
         cdfb.setSelected(true);
         search2BUS();
    }
    public void Groupe1(){
ButtonGroup bg1 = new ButtonGroup();
bg1.add(usdb);
bg1.add(cdfb);
    }

    public void dateannee(){
    SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy");
    SimpleDateFormat dateFormats1 = new SimpleDateFormat("MMM");
         annee= dateFormats.format(webDateField1.getDate());
         mois= dateFormats1.format(webDateField1.getDate());
    }
 public void buss()
    {
       
        
         
        try{
            String sql="SELECT NAME FROM   warehouse";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                //String sum=rs.getString("nom");
                String sums=rs.getString("NAME");
                buss.addItem(sums);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
    
    }
 
  public void callcaise()
    {
       
        
         Double Debit,DebitCDF,CreditCDF;
         Double Credit;
         Double c,e;
        try{
            String sql="SELECT sum(debit),sum(credit),SUM(DEBIT_CDF),SUM(CREDIT_CDF) FROM   OHADA_TRANS WHERE CODE='575' AND SUBSTR='[JCB]'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
               Debit=rs.getDouble("sum(debit)");
               Credit=rs.getDouble("sum(credit)");
               
            
               
                 DebitCDF=rs.getDouble("SUM(DEBIT_CDF)");
               // entre.setText(""+xxx);
               CreditCDF=rs.getDouble("SUM(CREDIT_CDF)");
               
               c=DebitCDF-CreditCDF;
                e=Debit-Credit;
               
               NumberFormat nf = new DecimalFormat("#,###,###");
String fn = nf.format(c);

 NumberFormat nf1 = new DecimalFormat("#.##");//##.##
String fn1 = nf1.format(e);
               
           cdf.setText(fn);
           usd3.setText(fn1);
               
               
               
            }
          //  con.close();
            }
        
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
    
    }
     
 
  String NAME,CODE,COMPTEMERE,CODEMERE,CLASS;
      public void SELECT_buss()
    {
       
        
         //`NAME`, `CODE`, `COMPTEMERE`, `CODEMERE`, `CLASS`
        try{
             
            String sql="SELECT * FROM   OHADA where NAME='"+buss.getSelectedItem()+"' and CLASS=5";
          
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                //String sum=rs.getString("nom");
               COMPTEMERE=rs.getString("COMPTEMERE");
               
                
                CODEMERE=rs.getString("CODEMERE");
              
                
                CODE=rs.getString("CODE");
              
                
                 NAME=rs.getString("NAME");
                  CLASS=rs.getString("CLASS");
                 //  SUBSTRS =rs.getString("SUBSTR");
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
    
    }
      
       public void callcaisebuss()
    {
       
        
         Double Debit;
         Double Credit;
         Double c;
         String e;
        try{
            String sql="SELECT sum(debit),sum(credit) FROM   ohada_trans where compte='"+buss.getSelectedItem()+"' and CLASSE=5";
          
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
               Debit=rs.getDouble("sum(debit)");
               Credit=rs.getDouble("sum(credit)");
               c=Debit-Credit;
                 NumberFormat nf = new DecimalFormat("#.##");//##.##
String fn = nf.format(c);
           usdbuss.setText(fn);
               
               
               
            }
         //   con.close();
            }
        
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
     try{
            String sql="SELECT sum(DEBIT_CDF),sum(CREDIT_CDF) FROM   ohada_trans where compte='"+buss.getSelectedItem()+"' and CLASSE=5";
          
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
               Debit=rs.getDouble("sum(DEBIT_CDF)");
               Credit=rs.getDouble("sum(CREDIT_CDF)");
               c=Debit-Credit;
                NumberFormat nf = new DecimalFormat("#,###,###");
String fn = nf.format(c);
           cdfbuss.setText(fn);
               
               
               
            }
         //   con.close();
            }
        
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
    }
        public void attCall_IN_LIST1()
    { 
        DefaultListModel list = new DefaultListModel();
 
        try{
          String sql="SELECT * FROM ohada  order by CODE";
             
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                //String sum=rs.getString("nom");
                String sums=rs.getString("code");
                 list.addElement(sums);
                
                 jList1.setModel(list);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
    }
        public void attCall_IN_LIST_serach()
    { 
        DefaultListModel list = new DefaultListModel();
 
        try{
          String sql="SELECT * FROM ohada where CODE like '"+recherche.getText()+"%' order by CODE";
             
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                //String sum=rs.getString("nom");
                String sums=rs.getString("code");
                 list.addElement(sums);
                
                 jList1.setModel(list);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
    }
        String CLASSS,COMPTEMERES;
        
         public void clic_attCall_IN_LIST7()
    { 
        DefaultListModel list = new DefaultListModel();
 
        try{
          String sql="SELECT * FROM ohada where CODE ='"+jList1.getSelectedValue()+"'";
           
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
                
                 COMPTEMERES=rs.getString("COMPTEMERE");
                  CLASSS=rs.getString("CLASS");
                
                 
                
                
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
    }
           public void etroll()
     {
        SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM");
         String addDate = dateFormats.format(webDateField1.getDate());
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
                rolls= "No: "+addDate+"/1001";
                
             }
              // compte.setText(rolls);
             
         }catch(Exception e){
             JOptionPane.showMessageDialog(null, e); }
     }
           
           Double PV,PVV;
     public void call_currency(){
      Double PR = Double.parseDouble(amount.getText());
      Double cdfd,usdd;
 
  try{
                         String sql="SELECT * FROM  currency";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
             CDF=rs.getDouble("ACDF");
             USD=rs.getDouble("VUSD"); 
            }
//String         CDF=(new DecimalFormat("##.##").format(cdfd));
//          USD=(new DecimalFormat("##.##").format(usdd));
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
 } 
      
         public void save(){
             
               SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy/MM/dd");
         String addDate = dateFormats.format(webDateField1.getDate());
       //  String addDate2 = dateFormats.format(webDateField2.getDate()); 
             
           etroll();
           dateannee();
           SELECT_buss();
           callcaise();
           callcaisebuss();
           
       call_currency();
           Double PV=Double.parseDouble(amount.getText());
           
         
            
           
            if(jComboBox3.getSelectedItem().equals("Debit")){
                      Double aaa=Double.parseDouble(amount.getText());
               
            try{  
             Double xxx;
Double yyy;


//call from journala de banque  
            String sql="select SUM(DEBIT),SUM(CREDIT),SUM(DEBIT_CDF),SUM(CREDIT_CDF) from ohada_trans where code='"+code.getText()+"'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                if(jComboBox2.getSelectedItem().equals("USD")){
                xxx=rs.getDouble("SUM(DEBIT)");
               // entre.setText(""+xxx);
                yyy=rs.getDouble("SUM(CREDIT)");
                // caissesdebit=(xxx-yyy);
                }else{
                    //,
                xxx=rs.getDouble("SUM(DEBIT_CDF)");
               // entre.setText(""+xxx);
                yyy=rs.getDouble("SUM(CREDIT_CDF)");
                // 
                }
               caissesdebit=(xxx-yyy); 
                  
                  
                 
            }
            }catch(Exception ex){ JOptionPane.showMessageDialog(null, ex);}
                  
            if(caissesdebit < aaa){
            JOptionPane.showMessageDialog(null,"Wrong Data"+"\n"+"No Enough Resources","Error",JOptionPane.ERROR_MESSAGE);
            }else{
                     // OHADA ACCOUNT
                //>>>>57
                if(jComboBox2.getSelectedItem().equals("USD")){
                 try {
         String tmp =jComboBox3.getSelectedItem().toString();
           
        PreparedStatement pst = con.prepareStatement("INSERT INTO `ohada_trans`(`COMPTE_M`, `COMPTE`, `CODE_M`, `CODE`, `CLASSE`, `SUBSTR`, `DEBIT`, `CREDIT`, `NUM`, `DATES`, `BUSS`, `LIBELLE`, `JOURNAL`,`CODE1`,DEBIT_CDF,CREDIT_CDF)"
        +"values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        //NAME`, `CODE`, `COMPTEMERE`, `CODEMERE`, `CLASS`
        pst.setString(1, COMPTEMERE);
        pst.setString(2, NAME);
         pst.setString(3, CODEMERE);
         pst.setString(4, CODE);
         pst.setString(5, CLASS);
         pst.setString(6, "[JCB]");
         pst.setDouble(7,PV);
         pst.setString(8,"");
         pst.setString(9,rolls);
         
      
         //String addDate = dateFormats.format(webDateField1.getDate());
         pst.setString(10, addDate);
         pst.setString(11,buss.getSelectedItem().toString());
         pst.setString(12,libele.getText());
         pst.setString(13,"[JC]");
         pst.setString(14,code.getText());
         pst.setDouble(15,PV*USD);
         pst.setString(16,"");
       
          
         
          pst.executeUpdate();
        
           //      JOptionPane.showMessageDialog(null,"Caisse saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
      ///>>>>>52
        try {
         String tmp =jComboBox3.getSelectedItem().toString();
         
        PreparedStatement pst = con.prepareStatement("INSERT INTO `ohada_trans`(`COMPTE_M`, `COMPTE`, `CODE_M`, `CODE`, `CLASSE`, `SUBSTR`, `DEBIT`, `CREDIT`, `NUM`, `DATES`, `BUSS`, `LIBELLE`, `JOURNAL`,`CODE1`,DEBIT_CDF,CREDIT_CDF)"
        +"values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        
        pst.setString(1, COMPTEMERES);
        pst.setString(2, compte.getText());
         pst.setString(3, code_m.getText());
         pst.setString(4, code.getText());
         pst.setString(5, CLASSS);
         pst.setString(6, "[JCB]");
         pst.setString(7,"");
         pst.setDouble(8,PV);
         pst.setString(9,rolls);
         
 
        // String addDate = dateFormats.format(webDateField1.getDate());
         pst.setString(10, addDate);
         pst.setString(11,buss.getSelectedItem().toString());
         pst.setString(12,libele.getText());
         pst.setString(13,"[JC]");
         pst.setString(14,CODE);
         pst.setString(15,"");
         pst.setDouble(16,PV*USD);
       
          
         
          pst.executeUpdate();
        
                 JOptionPane.showMessageDialog(null,"Caisse saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
                }else{
                
                 try {
         String tmp =jComboBox3.getSelectedItem().toString();
           
        PreparedStatement pst = con.prepareStatement("INSERT INTO `ohada_trans`(`COMPTE_M`, `COMPTE`, `CODE_M`, `CODE`, `CLASSE`, `SUBSTR`, `DEBIT`, `CREDIT`, `NUM`, `DATES`, `BUSS`, `LIBELLE`, `JOURNAL`,`CODE1`,DEBIT_CDF,CREDIT_CDF)"
        +"values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        //NAME`, `CODE`, `COMPTEMERE`, `CODEMERE`, `CLASS`
        pst.setString(1, COMPTEMERE);
        pst.setString(2, NAME);
         pst.setString(3, CODEMERE);
         pst.setString(4, CODE);
         pst.setString(5, CLASS);
         pst.setString(6, "[JCB]");
        
          Double q=PV/CDF;
        String usdd=(new DecimalFormat("##.##").format(q));
         pst.setString(7,usdd);
         pst.setString(8,"");
         pst.setString(9,rolls);
         
       
//         String addDate = dateFormats.format(webDateField1.getDate());
         pst.setString(10, addDate);
         pst.setString(11,buss.getSelectedItem().toString());
         pst.setString(12,libele.getText());
         pst.setString(13,"[JC]");
         pst.setString(14,code.getText());
         pst.setString(15,amount.getText());
         pst.setString(16,"");
       
          
         
          pst.executeUpdate();
        
           //      JOptionPane.showMessageDialog(null,"Caisse saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
      ///>>>>>52
        try {
         String tmp =jComboBox3.getSelectedItem().toString();
         
        PreparedStatement pst = con.prepareStatement("INSERT INTO `ohada_trans`(`COMPTE_M`, `COMPTE`, `CODE_M`, `CODE`, `CLASSE`, `SUBSTR`, `DEBIT`, `CREDIT`, `NUM`, `DATES`, `BUSS`, `LIBELLE`, `JOURNAL`,`CODE1`,DEBIT_CDF,CREDIT_CDF)"
        +"values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        
        pst.setString(1, COMPTEMERES);
        pst.setString(2, compte.getText());
         pst.setString(3, code_m.getText());
         pst.setString(4, code.getText());
         pst.setString(5, CLASSS);
         pst.setString(6, "[JCB]");
         pst.setString(7,"");
         Double q=PV/CDF;
        String usdd=(new DecimalFormat("##.##").format(q));
         pst.setString(8,usdd);
         pst.setString(9,rolls);
         
      
//         String addDate = dateFormats.format(webDateField1.getDate());
         pst.setString(10, addDate);
         pst.setString(11,buss.getSelectedItem().toString());
         pst.setString(12,libele.getText());
         pst.setString(13,"[JC]");
         pst.setString(14,CODE);
         pst.setString(15,"");
         pst.setString(16,amount.getText());
       
          
         
          pst.executeUpdate();
        
                 JOptionPane.showMessageDialog(null,"Caisse saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
                }
      
            }
                 

        
                
           }else{
               
        Double aaa=Double.parseDouble(amount.getText());
               Double caissesdebit = null;
            try{  
             Double xxx;
Double yyy;


 String sql="select SUM(DEBIT),SUM(CREDIT),SUM(DEBIT_CDF),SUM(CREDIT_CDF) from ohada_trans where code='"+CODE+"'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                if(jComboBox2.getSelectedItem().equals("USD")){
                xxx=rs.getDouble("SUM(DEBIT)");
               // entre.setText(""+xxx);
                yyy=rs.getDouble("SUM(CREDIT)");
                // caissesdebit=(xxx-yyy);
                }else{
                    //,
                xxx=rs.getDouble("SUM(DEBIT_CDF)");
               // entre.setText(""+xxx);
                yyy=rs.getDouble("SUM(CREDIT_CDF)");
               //  
                }
                caissesdebit=(xxx-yyy);
                  
                  
                 
            }
            }catch(Exception ex){ JOptionPane.showMessageDialog(null, ex);}
 /*
            String sql="select SUM(DEBIT),SUM(CREDIT) from ohada_trans where code='"+CODE+"'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                xxx=rs.getDouble("SUM(DEBIT)");
               // entre.setText(""+xxx);
                yyy=rs.getDouble("SUM(CREDIT)");
                  
                   caissesdebit=(xxx-yyy);
                 
            }
            }catch(Exception ex){ JOptionPane.showMessageDialog(null, ex);}
            */
            if(caissesdebit < aaa){
            JOptionPane.showMessageDialog(null,"Wrong Data"+"\n"+"No Enough Resources","Error",JOptionPane.ERROR_MESSAGE);
            }else{
                   
         // OHADA ACCOUNT
                //>>>>57
                
                if(jComboBox2.getSelectedItem().equals("USD")){
                try {
         String tmp =jComboBox3.getSelectedItem().toString();
           
        PreparedStatement pst = con.prepareStatement("INSERT INTO `ohada_trans`(`COMPTE_M`, `COMPTE`, `CODE_M`, `CODE`, `CLASSE`, `SUBSTR`, `DEBIT`, `CREDIT`, `NUM`, `DATES`, `BUSS`, `LIBELLE`, `JOURNAL`,`CODE1`,DEBIT_CDF,CREDIT_CDF)"
        +"values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        //NAME`, `CODE`, `COMPTEMERE`, `CODEMERE`, `CLASS`
        pst.setString(1, COMPTEMERE);
        pst.setString(2, NAME);
         pst.setString(3, CODEMERE);
         pst.setString(4, CODE);
         pst.setString(5, CLASS);
         pst.setString(6, "[JCB]");
         pst.setDouble(8,PV);
         pst.setString(7,"");
         pst.setString(9,rolls);
         
     
//         String addDate = dateFormats.format(webDateField1.getDate());
         pst.setString(10, addDate);
         pst.setString(11,buss.getSelectedItem().toString());
         pst.setString(12,libele.getText());
         pst.setString(13,"[JC]");
         pst.setString(14,code.getText());
          pst.setString(15,"");
         pst.setDouble(16,PV*USD);
       
          
         
          pst.executeUpdate();
        
           //      JOptionPane.showMessageDialog(null,"Caisse saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
      ///>>>>>52
        try {
         String tmp =jComboBox3.getSelectedItem().toString();
         
        PreparedStatement pst = con.prepareStatement("INSERT INTO `ohada_trans`(`COMPTE_M`, `COMPTE`, `CODE_M`, `CODE`, `CLASSE`, `SUBSTR`, `DEBIT`, `CREDIT`, `NUM`, `DATES`, `BUSS`, `LIBELLE`, `JOURNAL`,`CODE1`,DEBIT_CDF,CREDIT_CDF)"
        +"values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        
        pst.setString(1, COMPTEMERES);
        pst.setString(2, compte.getText());
         pst.setString(3, code_m.getText());
         pst.setString(4, code.getText());
         pst.setString(5, CLASSS);
         pst.setString(6, "[JCB]");
         pst.setString(8,"");
         pst.setDouble(7,PV);
         pst.setString(9,rolls);
         
     
//         String addDate = dateFormats.format(webDateField1.getDate());
         pst.setString(10, addDate);
         pst.setString(11,buss.getSelectedItem().toString());
         pst.setString(12,libele.getText());
         pst.setString(13,"[JC]");
         pst.setString(14,CODE);
          pst.setString(13,"[JC]");
         pst.setString(14,CODE);
         pst.setDouble(15,PV*USD);
         pst.setString(16,"");
       
          
         
          pst.executeUpdate();
        
                 JOptionPane.showMessageDialog(null,"Caisse saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
                }else{
                try {
         String tmp =jComboBox3.getSelectedItem().toString();
           
        PreparedStatement pst = con.prepareStatement("INSERT INTO `ohada_trans`(`COMPTE_M`, `COMPTE`, `CODE_M`, `CODE`, `CLASSE`, `SUBSTR`, `DEBIT`, `CREDIT`, `NUM`, `DATES`, `BUSS`, `LIBELLE`, `JOURNAL`,`CODE1`,DEBIT_CDF,CREDIT_CDF)"
        +"values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        //NAME`, `CODE`, `COMPTEMERE`, `CODEMERE`, `CLASS`
        pst.setString(1, COMPTEMERE);
        pst.setString(2, NAME);
         pst.setString(3, CODEMERE);
         pst.setString(4, CODE);
         pst.setString(5, CLASS);
         pst.setString(6, "[JCB]");
       
         Double q=PV/CDF;
        String usdd=(new DecimalFormat("##.##").format(q));
         pst.setString(8,usdd);
         pst.setString(7,"");
         pst.setString(9,rolls);
         
      
//         String addDate = dateFormats.format(webDateField1.getDate());
         pst.setString(10, addDate);
         pst.setString(11,buss.getSelectedItem().toString());
         pst.setString(12,libele.getText());
         pst.setString(13,"[JC]");
         pst.setString(14,code.getText());
          pst.setString(15,"");
         pst.setString(16,amount.getText());
       
          
         
          pst.executeUpdate();
        
           //      JOptionPane.showMessageDialog(null,"Caisse saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
      ///>>>>>52
        try {
         String tmp =jComboBox3.getSelectedItem().toString();
         
        PreparedStatement pst = con.prepareStatement("INSERT INTO `ohada_trans`(`COMPTE_M`, `COMPTE`, `CODE_M`, `CODE`, `CLASSE`, `SUBSTR`, `DEBIT`, `CREDIT`, `NUM`, `DATES`, `BUSS`, `LIBELLE`, `JOURNAL`,`CODE1`,DEBIT_CDF,CREDIT_CDF)"
        +"values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        
        pst.setString(1, COMPTEMERES);
        pst.setString(2, compte.getText());
         pst.setString(3, code_m.getText());
         pst.setString(4, code.getText());
         pst.setString(5, CLASSS);
         pst.setString(6, "[JCB]");
         pst.setString(8,"");
        
          Double q=PV/CDF;
        String usdd=(new DecimalFormat("##.##").format(q));
         pst.setString(7,usdd);
         pst.setString(9,rolls);
         
//       SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
   //      String addDate = dateFormats.format(webDateField1.getDate());
         pst.setString(10, addDate);
         pst.setString(11,buss.getSelectedItem().toString());
         pst.setString(12,libele.getText());
         pst.setString(13,"[JC]");
         pst.setString(14,CODE);
          pst.setString(13,"[JC]");
         pst.setString(14,CODE);
         pst.setString(15,amount.getText());
         pst.setString(16,"");
       
          
         
          pst.executeUpdate();
        
                 JOptionPane.showMessageDialog(null,"Caisse saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
                }
       
             
         }
            
            }
         
         }
       
           public void search2BUS()
             {
                 if(usdb.isSelected()){
                 try{
               //   String st=projetid.getText().trim();
             String tmp=buss.getSelectedItem().toString();
    String sql="select `NUM`, `CODE1` AS 'CODE',`LIBELLE`, `DEBIT`, `CREDIT`, `DATES` from OHADA_TRANS where COMPTE = '"+tmp+"'ORDER BY DATES";
    //`LIBELLE`, `ENTRE`, `SORTIE`, `DATE`, `BUSS`, `USED`, `NUM`
    pst = con.prepareStatement(sql);
      rs= pst.executeQuery();
     
       jTable1.setModel(DbUtils.resultSetToTableModel(rs));
    
             }catch(Exception ex){
                 JOptionPane.showMessageDialog(null, ex);
             }
                 }else if(cdfb.isSelected()){
                 
                 try{
               //   String st=projetid.getText().trim();
             String tmp=buss.getSelectedItem().toString();
    String sql="select `NUM`, `CODE1` AS 'CODE',`LIBELLE`, DEBIT_CDF as 'DEBIT',CREDIT_CDF AS 'CREDIT', `DATES` from OHADA_TRANS where COMPTE = '"+tmp+"' ORDER BY DATES";
    //`LIBELLE`, `ENTRE`, `SORTIE`, `DATE`, `BUSS`, `USED`, `NUM`
    pst = con.prepareStatement(sql);
      rs= pst.executeQuery();
     
       jTable1.setModel(DbUtils.resultSetToTableModel(rs));
    
             }catch(Exception ex){
                 JOptionPane.showMessageDialog(null, ex);
             }
                 }
             //COMPTE_M`, `COMPTE`, `CODE_M`, `CODE`, `CLASSE`, `SUBSTR`, `DEBIT`, `CREDIT`, `NUM`, `DATES`, `BUSS`, `LIBELLE`, `JOURNAL`, `CODE1` 
                 
                 DefaultTableCellRenderer centre =new DefaultTableCellRenderer();
        centre.setHorizontalAlignment(JLabel.CENTER);
         TableColumn col0=jTable1.getColumnModel().getColumn(0);
        TableColumn col1=jTable1.getColumnModel().getColumn(1);
        TableColumn col2=jTable1.getColumnModel().getColumn(2);
        TableColumn col3=jTable1.getColumnModel().getColumn(3);
          centre.setHorizontalAlignment(JLabel.CENTER);
            jTable1.getColumnModel().getColumn(3).setCellRenderer(centre);
        TableColumn col4=jTable1.getColumnModel().getColumn(4);
          centre.setHorizontalAlignment(JLabel.CENTER);
            jTable1.getColumnModel().getColumn(4).setCellRenderer(centre);
           TableColumn col5=jTable1.getColumnModel().getColumn(5);
           // TableColumn col6=jTable1.getColumnModel().getColumn(6);
      
       
       col0.setPreferredWidth(80);
       col1.setPreferredWidth(30);
       col2.setPreferredWidth(270);
       col3.setPreferredWidth(20);
       col4.setPreferredWidth(20);
       col5.setPreferredWidth(30);
    //   col6.setPreferredWidth(200);
             
             }
           
           public void currency(){
               Double c;
           if(jComboBox2.getSelectedItem().equals("USD")){
               try{
            String sql="select AUSD from currency WHERE ID='1'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                USD=rs.getDouble("AUSD");          
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
           
               Double a =Double.parseDouble(amount.getText());
              
               
           }else if(jComboBox2.getSelectedItem().equals("CDF")){
           try{
            String sql="select ACDF from currency WHERE ID='1'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                CDF=rs.getDouble("ACDF");          
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
           }else{
           try{
            String sql="select AUSH from currency WHERE ID='1'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                USH=rs.getDouble("AUSH");          
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
           }
           
           }
             public void report(){
                   SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy/MM/dd");
         String addDate = dateFormats.format(webDateField1.getDate());
         String addDate2 = dateFormats.format(webDateField2.getDate()); 
                String tmp=buss.getSelectedItem().toString();
             try{
             
          String sqls="Select path from pathn";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
            if(rs.next()){
             
                String sum=rs.getString("path");
                  
                 String NameFiles=sum;
            
            String NameFile=""+NameFiles+"ware_house_caisse.jrxml";//journal_de_banque
           // String NameFile=""+NameFiles+"journal_de_banque.jrxml";//journal_de_banque
                 
                 //SELECT `CAT` AS budget_CAT, `ITEM` AS budget_ITEM, `QTY` AS budget_QTY, `UNITY` AS budget_UNITY, `PU` AS budget_PU, `PT` AS budget_PT, `PROJECT` AS budget_POJECT, `CODE`  AS budget_CODE, `LASTING` AS budget_LASTING FROM `budget` WHERE 1
                 
                JasperDesign jd=JRXmlLoader.load(NameFile);
        
        
             String sql;
        if(webDateField2.getText().isEmpty()){
         sql="SELECT * FROM  ohada_trans  where compte='"+tmp+"' and classe=5 AND DATES='"+addDate+"' ORDER BY DATES";
        }else{
         sql="SELECT * FROM  ohada_trans  where compte='"+tmp+"' and classe=5 AND DATES BETWEEN '"+addDate2+"' AND '"+addDate+"' ORDER BY DATES";
        }
              
    
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
             
             public void reportcdf(){
                   SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy/MM/dd");
         String addDate = dateFormats.format(webDateField1.getDate());
         String addDate2 = dateFormats.format(webDateField2.getDate()); 
                String tmp=buss.getSelectedItem().toString();
             try{
             
          String sqls="Select path from pathn";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
            if(rs.next()){
             
                String sum=rs.getString("path");
                  
                 String NameFiles=sum;
            
            String NameFile=""+NameFiles+"ware_house_caissecdf.jrxml";//journal_de_banque
            HashMap param= new HashMap();
    param.put("date2", webDateField1.getText());
     param.put("date1", webDateField2.getText());
                JasperDesign jd=JRXmlLoader.load(NameFile);
        
        String sql;
        if(webDateField2.getText().isEmpty()){
         sql="SELECT * FROM  ohada_trans  where compte='"+tmp+"' and classe=5 AND DATES='"+addDate+"' ORDER BY DATES";
        }else{
         sql="SELECT * FROM  ohada_trans  where compte='"+tmp+"' and classe=5 AND DATES BETWEEN '"+addDate2+"' AND '"+addDate+"' ORDER BY DATES";
        }
              // JasperDesign jd=JRXmlLoader.load("C:\\Users\\Dosh\\Documents\\NetBeansProjects\\intreprisemanagementsystem\\src\\intreprisemanagementsystem\\journaldecaise.jrxml");
        
              
    
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
             webDateField2.setText("");
             }
                public void reportcdfexl(){
                      String tmp=buss.getSelectedItem().toString();
               JasperPrint report = null;
               boolean isExcel;
               String saveTo = "C:\\Users\\DOSHE\\Desktop";
               JRExporter exporter=null;
               
            try{
            
            JasperDesign jd=JRXmlLoader.load("C:\\Users\\DOSHE\\Documents\\NetBeansProjects\\intreprisemanagementsystem\\src\\intreprisemanagementsystem\\ware_house_caisse.jrxml");
              String sql="SELECT * FROM  ohada_trans  where compte='"+tmp+"' and classe=5";
              
    
                JRDesignQuery nq=new JRDesignQuery();
                nq.setText(sql);
                jd.setQuery(nq);
               
               
                JasperReport jr =JasperCompileManager.compileReport(jd);
                JasperPrint jp=JasperFillManager.fillReport(jr,null,con);
               exporter=new JRXlsExporter();
               exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,Boolean.FALSE);
                exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND,Boolean.FALSE);
                 exporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE,Boolean.TRUE);
                  exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET,Boolean.TRUE);
                  
                   exporter.setParameter(JRExporterParameter.JASPER_PRINT,jp);
                    exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME,"Desktop");
                    exporter.exportReport();
            
            }catch(Exception ex){
                  JOptionPane.showMessageDialog(null, ex);   
             }
                }
                
                public void update(){
               String sql="UPDATE  `ohada_trans` SET `PCS`=(DEBIT_CDF/9000)  WHERE class=5";
              // UPDATE `` SET `PCS`= WHERE 1
 try{
        
        pst=con.prepareStatement(sql);
        pst.setString(1,"1");
      //  pst.setString(2,"1");
      //  pst.setString(3,"1");


    pst.executeUpdate();
   // JOptionPane.showMessageDialog(null, "DATA SAVED");
        
        }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);} 
                
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
        jPanel11 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        usd3 = new javax.swing.JTextField();
        cdf = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        usdbuss = new javax.swing.JTextField();
        cdfbuss = new javax.swing.JTextField();
        jTextField13 = new javax.swing.JTextField();
        jTextField14 = new javax.swing.JTextField();
        jPanel14 = new javax.swing.JPanel();
        compte = new javax.swing.JTextField();
        code_m = new javax.swing.JTextField();
        code = new javax.swing.JTextField();
        webDateField1 = new com.alee.extended.date.WebDateField();
        jPanel15 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        libele = new javax.swing.JEditorPane();
        jPanel17 = new javax.swing.JPanel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jComboBox3 = new javax.swing.JComboBox<>();
        bank = new javax.swing.JComboBox<>();
        amount = new javax.swing.JTextField();
        jPanel18 = new javax.swing.JPanel();
        recherche = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jPanel20 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel19 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        usdb = new javax.swing.JRadioButton();
        cdfb = new javax.swing.JRadioButton();
        jLabel2 = new javax.swing.JLabel();
        webDateField2 = new com.alee.extended.date.WebDateField();

        jPanel1.setBackground(new java.awt.Color(244, 244, 245));

        jPanel11.setBackground(new java.awt.Color(244, 244, 245));
        jPanel11.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Sold", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        usd3.setEditable(false);
        usd3.setBackground(new java.awt.Color(204, 204, 204));
        usd3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        usd3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        usd3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        cdf.setEditable(false);
        cdf.setBackground(new java.awt.Color(204, 204, 204));
        cdf.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        cdf.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        cdf.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("USD");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("CDF");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cdf, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
                    .addComponent(usd3))
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(usd3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cdf, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Busness", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        buss.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        buss.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select One Busness" }));
        buss.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                bussPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setText("CDF");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setText("USD");

        usdbuss.setEditable(false);
        usdbuss.setBackground(new java.awt.Color(51, 255, 51));
        usdbuss.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        usdbuss.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        cdfbuss.setEditable(false);
        cdfbuss.setBackground(new java.awt.Color(51, 255, 51));
        cdfbuss.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        cdfbuss.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jTextField13.setEditable(false);
        jTextField13.setBackground(new java.awt.Color(255, 51, 51));
        jTextField13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTextField13.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jTextField14.setEditable(false);
        jTextField14.setBackground(new java.awt.Color(255, 51, 51));
        jTextField14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTextField14.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buss, 0, 255, Short.MAX_VALUE)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addGap(2, 2, 2)))
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(usdbuss, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
                            .addComponent(cdfbuss))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField13)
                            .addComponent(jTextField14))))
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addComponent(buss, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(usdbuss, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(cdfbuss, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4))
        );

        jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ohada Account", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        compte.setEditable(false);
        compte.setBackground(new java.awt.Color(204, 204, 204));
        compte.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        compte.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        compte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                compteActionPerformed(evt);
            }
        });

        code_m.setEditable(false);
        code_m.setBackground(new java.awt.Color(204, 204, 204));
        code_m.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        code_m.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        code.setEditable(false);
        code.setBackground(new java.awt.Color(204, 204, 204));
        code.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        code.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        webDateField1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(webDateField1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(compte)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(code_m, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(code, javax.swing.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addComponent(compte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(code_m, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(code, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(webDateField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel15.setBackground(new java.awt.Color(244, 244, 245));
        jPanel15.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel16.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Wording", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        libele.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        libele.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jScrollPane2.setViewportView(libele);

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 298, Short.MAX_VALUE)
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
        );

        jPanel17.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Action", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jComboBox2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "CDF", "USD" }));

        jComboBox3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Debit", "Credit" }));
        jComboBox3.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                jComboBox3PopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        bank.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        bank.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select One Bank" }));
        bank.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                bankPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        amount.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        amount.setText("0.00");
        amount.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                amountMouseClicked(evt);
            }
        });
        amount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                amountKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bank, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(amount, javax.swing.GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(amount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addComponent(bank, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel18.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ohada Acc", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        recherche.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        recherche.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                rechercheKeyReleased(evt);
            }
        });

        jList1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jList1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jList1MouseEntered(evt);
            }
        });
        jScrollPane4.setViewportView(jList1);

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(recherche, javax.swing.GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                .addComponent(recherche, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel20.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Actions", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton1.setText("Save");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton2.setText("New");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE)
            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2))
        );

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(11, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(6, 6, 6))))
        );

        jTable1.setBackground(new java.awt.Color(240, 240, 240));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jPanel19.setBackground(new java.awt.Color(204, 204, 204));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("X");
        jLabel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel11MouseClicked(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("Warning: Caisse Principale Must Be: 575");

        usdb.setBackground(new java.awt.Color(204, 204, 204));
        usdb.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        usdb.setText("USD");
        usdb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usdbActionPerformed(evt);
            }
        });

        cdfb.setBackground(new java.awt.Color(204, 204, 204));
        cdfb.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        cdfb.setText("CDF");
        cdfb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cdfbActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_print_16px_1.png"))); // NOI18N
        jLabel2.setText("Print");
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel2MousePressed(evt);
            }
        });

        webDateField2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel1)
                .addGap(135, 135, 135)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(webDateField2, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(usdb)
                .addGap(18, 18, 18)
                .addComponent(cdfb)
                .addGap(36, 36, 36)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1))
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(usdb)
                    .addComponent(cdfb)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(webDateField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(113, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(113, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void bussPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_bussPopupMenuWillBecomeInvisible
SELECT_buss(); 
callcaisebuss();
search2BUS();// TODO add your handling code here:
    }//GEN-LAST:event_bussPopupMenuWillBecomeInvisible

    private void jComboBox3PopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_jComboBox3PopupMenuWillBecomeInvisible
caissesdebit = null ; 

        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox3PopupMenuWillBecomeInvisible

    private void bankPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_bankPopupMenuWillBecomeInvisible
           // TODO add your handling code here:
    }//GEN-LAST:event_bankPopupMenuWillBecomeInvisible

    private void amountKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_amountKeyTyped
        char c=evt.getKeyChar();
        if(!(Character.isDigit(c) || (c==KeyEvent.VK_BACK_SPACE)||c==KeyEvent.VK_DELETE ||c==KeyEvent.VK_PERIOD )){
            evt.consume();

        }          // TODO add your handling code here:
    }//GEN-LAST:event_amountKeyTyped

    private void rechercheKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_rechercheKeyReleased
attCall_IN_LIST_serach();     

        // TODO add your handling code here:
    }//GEN-LAST:event_rechercheKeyReleased

    private void jList1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList1MouseClicked
clic_attCall_IN_LIST7();             // TODO add your handling code here:
    }//GEN-LAST:event_jList1MouseClicked

    private void jList1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList1MouseEntered
        //JOptionPane.showMessageDialog(null, jList1.getSelectedValue());     // TODO add your handling code here:
    }//GEN-LAST:event_jList1MouseEntered

    private void jLabel11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseClicked
this.show(false);        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel11MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
if(libele.getText().equals("") ||amount.getText().equals("0.00") ||amount.getText().equals("") ||code_m.getText().equals("")){
JOptionPane.showMessageDialog(null,"Wrong Data","Error",JOptionPane.ERROR_MESSAGE);
}else{
        save();
callcaisebuss();
callcaise();
search2BUS(); 
}// TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void compteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_compteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_compteActionPerformed

    private void usdbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usdbActionPerformed
search2BUS();        // TODO add your handling code here:
    }//GEN-LAST:event_usdbActionPerformed

    private void cdfbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cdfbActionPerformed
search2BUS();        // TODO add your handling code here:
    }//GEN-LAST:event_cdfbActionPerformed

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
if(usdb.isSelected()){
 report(); 
}else{
reportcdf();
}
        
// TODO add your handling code here:
    }//GEN-LAST:event_jLabel2MouseClicked

    private void amountMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_amountMouseClicked
amount.setText("");        // TODO add your handling code here:
    }//GEN-LAST:event_amountMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jLabel2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MousePressed
//doubleclick();        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel2MousePressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField amount;
    private javax.swing.JComboBox<String> bank;
    public static final javax.swing.JComboBox<String> buss = new javax.swing.JComboBox<>();
    private javax.swing.JTextField cdf;
    private javax.swing.JRadioButton cdfb;
    private javax.swing.JTextField cdfbuss;
    private javax.swing.JTextField code;
    private javax.swing.JTextField code_m;
    private javax.swing.JTextField compte;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList<String> jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    public static final javax.swing.JTable jTable1 = new javax.swing.JTable();
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField14;
    private javax.swing.JEditorPane libele;
    private javax.swing.JTextField recherche;
    private javax.swing.JTextField usd3;
    private javax.swing.JRadioButton usdb;
    private javax.swing.JTextField usdbuss;
    private com.alee.extended.date.WebDateField webDateField1;
    private com.alee.extended.date.WebDateField webDateField2;
    // End of variables declaration//GEN-END:variables
}
