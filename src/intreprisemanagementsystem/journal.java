/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intreprisemanagementsystem;

import com.alee.laf.WebLookAndFeel;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.print.PrinterException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import net.proteanit.sql.DbUtils;

/**
 *levi
 * @author Dosh
 */
public class journal extends javax.swing.JFrame {

     Connection con=null;
 Connection cononline=null;
PreparedStatement pst=null;
ResultSet rs= null;
 DefaultTableModel mode;
 String rolls;
 int x,y;
 String NAME,CODEM,COMPTEM,CLASSE,SUBSTRING,CODE; 
    public journal() {
        initComponents();
             setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("icons8_Raspberry_Pi_48px_1.png")));

         con=JavaDbConnect.dbConnect();
         code1.setEditable(false);
         projet.setEditable(false);
         lb.setEditable(false);
         jDateChooser1.setDate(new Date());
         Groupe1();
       jTable1.setDefaultRenderer(Object.class,new PINTAR_TABELA_journal()); 
    }
                  public void Groupe1(){
ButtonGroup bg1 = new ButtonGroup();
bg1.add(deux);
bg1.add(un);
ButtonGroup bg2 = new ButtonGroup();
bg2.add(pro);
bg2.add(stru);

 try{
          String sql="SELECT * FROM currency";
             
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                //String sum=rs.getString("nom");
                String sums=rs.getString("APPR");
                
                
               jComboBox3.addItem(sums);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
     }
public void change(){
    String tmp = null;
    code1.setEditable(false);
if(jComboBox1.getSelectedItem().equals("JRN-VENTE")){
title.setText("Journl de vente");
tmp="41";
}else if(jComboBox1.getSelectedItem().equals("JRN-VENTE-45")){
title.setText("Journal vente Subvention");
tmp="45";
}else if(jComboBox1.getSelectedItem().equals("JRN-ACHAT")){
title.setText("Journl achat");
tmp="40";
}else if(jComboBox1.getSelectedItem().equals("JRN-BANQUE")){
title.setText("Journl de banque");
tmp="52";
}else if(jComboBox1.getSelectedItem().equals("JRN-CAISSE")){
title.setText("Journl de caisse");
tmp="57";
}else if(jComboBox1.getSelectedItem().equals("JRN-OPD")){
title.setText("Journl des opretions diverses");
code1.setEditable(true);
}

  try{
            String sql="select * from  ohada WHERE SUBSTR='"+tmp+"'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
           if(rs.next()){
                String sum=rs.getString("SUBSTR");
                 try{  // String Table_click = (jList2.getModel().getSelectedItem(tmps,0). toString());
                    compte m = new compte();
                     m.show();
        m. setDefaultCloseOperation(DISPOSE_ON_CLOSE);  
       String sqls="SELECT  CODE as 'Numéro',`NAME` AS 'Intitulé du compte' FROM `ohada` where SUBSTR='"+sum+"' ";
       pst = con.prepareStatement(sqls);
      rs= pst.executeQuery();
     
      compte.etjTable3.setModel(DbUtils.resultSetToTableModel(rs));
       DefaultTableCellRenderer centre =new DefaultTableCellRenderer();
       TableColumn col0= compte.etjTable3.getColumnModel().getColumn(0);
        TableColumn col1= compte.etjTable3.getColumnModel().getColumn(1);
       col0.setPreferredWidth(50);
       col1.setPreferredWidth(300);
      centre.setHorizontalAlignment(JLabel.CENTER);
         compte.etjTable3.getColumnModel().getColumn(0).setCellRenderer(centre); 
    }catch(SQLException ex ){
     JOptionPane.showMessageDialog(null, ex);}
                 
                 
                
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex); }  
        
}

 public void compte()
    {
         try{
            String sql="select * from  ohada WHERE SUBSTR='"+journal.code1.getText()+"'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
           if(rs.next()){
                String sum=rs.getString("SUBSTR");
                 try{  // String Table_click = (jList2.getModel().getSelectedItem(tmps,0). toString());
                    compte m = new compte();
                     m.show();
        m. setDefaultCloseOperation(DISPOSE_ON_CLOSE);  
       String sqls="SELECT  CODE as 'Numéro',`NAME` AS 'Intitulé du compte' FROM `ohada` where SUBSTR='"+sum+"' ";
       pst = con.prepareStatement(sqls);
      rs= pst.executeQuery();
     
      compte.etjTable3.setModel(DbUtils.resultSetToTableModel(rs));
       DefaultTableCellRenderer centre =new DefaultTableCellRenderer();
       TableColumn col0= compte.etjTable3.getColumnModel().getColumn(0);
        TableColumn col1= compte.etjTable3.getColumnModel().getColumn(1);
       col0.setPreferredWidth(50);
       col1.setPreferredWidth(300);
      centre.setHorizontalAlignment(JLabel.CENTER);
         compte.etjTable3.getColumnModel().getColumn(0).setCellRenderer(centre); 
    }catch(SQLException ex ){
     JOptionPane.showMessageDialog(null, ex);}
                 
                 
                
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex); }  
        
       
}
 public void compte1()
    {
         try{
            String sql="select * from  ohada WHERE SUBSTR='"+journal.code2.getText()+"'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
           if(rs.next()){
                String sum=rs.getString("SUBSTR");
                 try{  // String Table_click = (jList2.getModel().getSelectedItem(tmps,0). toString());
                    compte1 m = new compte1();
                     m.show();
        m. setDefaultCloseOperation(DISPOSE_ON_CLOSE);  
       String sqls="SELECT  CODE as 'Numéro',`NAME` AS 'Intitulé du compte' FROM `ohada` where SUBSTR='"+sum+"' ";
       pst = con.prepareStatement(sqls);
      rs= pst.executeQuery();
     
      compte1.etjTable3.setModel(DbUtils.resultSetToTableModel(rs));
       DefaultTableCellRenderer centre =new DefaultTableCellRenderer();
       TableColumn col0=compte1.etjTable3.getColumnModel().getColumn(0);
        TableColumn col1=compte1.etjTable3.getColumnModel().getColumn(1);
       col0.setPreferredWidth(50);
       col1.setPreferredWidth(300);
      centre.setHorizontalAlignment(JLabel.CENTER);
       compte1.etjTable3.getColumnModel().getColumn(0).setCellRenderer(centre); 
    }catch(SQLException ex ){
     JOptionPane.showMessageDialog(null, ex);}
                 
                 
                
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex); }  
        
       
}
  public void rolls(){
          try{
                  SimpleDateFormat dateFormatsS = new SimpleDateFormat("yyyy-MM-dd");
         String addDateS = dateFormatsS.format(jDateChooser1.getDate());
         
         String sql="SELECT NUM FROM ohada_trans where SUBSTR(NUM,5,10)='"+addDateS+"' AND BUSS='"+projet.getText()+"'  ORDER BY NUM DESC LIMIT 1";
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
          roll.setText(rolls);
         }
  
  
           Double BUY,USD,CDF,buy,sale ;//,USDD;
           public void call_currency(){
               
      Double PR = Double.parseDouble(amount.getText());
 try{
String sql="select * from monais WHERE caisse='"+code1.getText()+"'";      
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            if(rs.next()){
           String DEVICE=rs.getString("DEVICE");  
            jComboBox3.setSelectedItem(DEVICE);
            }
        //  con.close();
            }
        
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex); }

 try{
String sql="SELECT * FROM  currency where APPR='"+jComboBox3.getSelectedItem()+"'";
           
            pst=con.prepareStatement(sql);
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
  if(jComboBox3.getSelectedItem().equals("USD")){
   CDF=PR*buy;
  USD=PR;
  
  }else{
     
  USD=PR/sale;
  CDF=PR;
  }
      
}
  public void saveohadatrans(){
       rolls();
     call_currency();
       if(jComboBox2.getSelectedItem().equals("Debit")){
       try {
        PreparedStatement pst = con.prepareStatement("INSERT INTO `ohada_trans`(`COMPTE_M`, `COMPTE`, `CODE_M`, `CODE`, `CLASSE`, `SUBSTR`, `DEBIT`, `CREDIT`, `NUM`, `DATES`, `BUSS`, `LIBELLE`, `JOURNAL`, `CODE1`,DEBIT_CDF,CREDIT_CDF,DEVICE)"
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        
        pst.setString(1,comptem.getText());
        pst.setString(2, nom.getText());
         pst.setString(3, codem.getText());
         pst.setString(4,code1.getText());
         pst.setString(5,classe.getText());
         pst.setString(6,substr.getText());
         pst.setDouble(7,USD);
         pst.setString(8,"0");
         pst.setString(9,rolls);
         
       SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(jDateChooser1.getDate());
         pst.setString(10, addDate);
         pst.setString(11,projet.getText());
         pst.setString(12,libele.getText());
         pst.setString(13,"[JC]");
          pst.setString(14,code2.getText());
           pst.setDouble(15,CDF);
            pst.setDouble(16,0);
            pst.setString(17,jComboBox3.getSelectedItem().toString());
       
          
         
          pst.executeUpdate();
        
               //  JOptionPane.showMessageDialog(null,"Caisse saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
      
            try {
        PreparedStatement pst = con.prepareStatement("INSERT INTO `ohada_trans`(`COMPTE_M`, `COMPTE`, `CODE_M`, `CODE`, `CLASSE`, `SUBSTR`, `DEBIT`, `CREDIT`, `NUM`, `DATES`, `BUSS`, `LIBELLE`, `JOURNAL`, `CODE1`,DEBIT_CDF,CREDIT_CDF,DEVICE)"
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        
        pst.setString(1,comptem1.getText());
        pst.setString(2, nom1.getText());
         pst.setString(3, codem1.getText());
         pst.setString(4,code2.getText());
         pst.setString(5,classe1.getText());
         pst.setString(6,substr1.getText());
         pst.setDouble(8,USD);
         pst.setString(7,"0");
         pst.setString(9,rolls);
         
       SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(jDateChooser1.getDate());
         pst.setString(10, addDate);
         pst.setString(11,projet.getText());
         pst.setString(12,libele.getText());
         pst.setString(13,"[JC]");
          pst.setString(14,code1.getText());
           pst.setString(15,"0");
            pst.setDouble(16,CDF);
            pst.setString(17,jComboBox3.getSelectedItem().toString());
       
          
         
          pst.executeUpdate();
        
                
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
  
       
       }else{
       try {
        PreparedStatement pst = con.prepareStatement("INSERT INTO `ohada_trans`(`COMPTE_M`, `COMPTE`, `CODE_M`, `CODE`, `CLASSE`, `SUBSTR`, `DEBIT`, `CREDIT`, `NUM`, `DATES`, `BUSS`, `LIBELLE`, `JOURNAL`, `CODE1`,DEBIT_CDF,CREDIT_CDF,DEVICE)"
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        
        pst.setString(1,comptem.getText());
        pst.setString(2, nom.getText());
         pst.setString(3, codem.getText());
         pst.setString(4,code1.getText());
         pst.setString(5,classe.getText());
         pst.setString(6,substr.getText());
         pst.setDouble(8,USD);
         pst.setString(7,"0");
         pst.setString(9,rolls);
         
       SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(jDateChooser1.getDate());
         pst.setString(10, addDate);
         pst.setString(11,projet.getText());
         pst.setString(12,libele.getText());
         pst.setString(13,"[JC]");
          pst.setString(14,code2.getText());
           pst.setString(15,"0");
            pst.setDouble(16,CDF);
            pst.setString(17,jComboBox3.getSelectedItem().toString());
       
          
         
          pst.executeUpdate();
        
               //  JOptionPane.showMessageDialog(null,"Caisse saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
      
            try {
        PreparedStatement pst = con.prepareStatement("INSERT INTO `ohada_trans`(`COMPTE_M`, `COMPTE`, `CODE_M`, `CODE`, `CLASSE`, `SUBSTR`, `DEBIT`, `CREDIT`, `NUM`, `DATES`, `BUSS`, `LIBELLE`, `JOURNAL`, `CODE1`,DEBIT_CDF,CREDIT_CDF,DEVICE)"
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        
        pst.setString(1,comptem1.getText());
        pst.setString(2, nom1.getText());
         pst.setString(3, codem1.getText());
         pst.setString(4,code2.getText());
         pst.setString(5,classe1.getText());
         pst.setString(6,substr1.getText());
         pst.setDouble(7,USD);
         pst.setString(8,"0");
         pst.setString(9,rolls);
         
       SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(jDateChooser1.getDate());
         pst.setString(10, addDate);
         pst.setString(11,projet.getText());
         pst.setString(12,libele.getText());
         pst.setString(13,"[JC]");
          pst.setString(14,code1.getText());
           pst.setDouble(15,CDF);
            pst.setDouble(16,0);
            pst.setString(17,jComboBox3.getSelectedItem().toString());
       
          
         
          pst.executeUpdate();
        
                
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
  
       }
   
   JOptionPane.showMessageDialog(null,"Caisse saved");
  if(jRadioButton1.isSelected()){
   show_in_table_concern();
  }else{
  show_in_table();
  }
  }
  
  
    public void saveohadatranspro(){
       rolls();
       call_currency();
       if(lb.getText().equals("LB")||lb.getText().equals("")||lbs.getText().equals("LB")||lbs.getText().equals("") ||projet.getText().equals("Projet")||projet.getText().equals("")){
      JOptionPane.showMessageDialog(null,"Wrong Data","Error",JOptionPane.ERROR_MESSAGE); 
       }else{
        if(jComboBox2.getSelectedItem().equals("Debit")){
       try {
        PreparedStatement pst = con.prepareStatement("INSERT INTO `ohada_trans`(`COMPTE_M`, `COMPTE`, `CODE_M`, `CODE`, `CLASSE`, `SUBSTR`, `DEBIT`, `CREDIT`, `NUM`, `DATES`, `BUSS`, `LIBELLE`, `JOURNAL`, `CODE1`,DEBIT_CDF,CREDIT_CDF,DEVICE,LB)"
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        
        pst.setString(1,comptem.getText());
        pst.setString(2, nom.getText());
         pst.setString(3, codem.getText());
         pst.setString(4,code1.getText());
         pst.setString(5,classe.getText());
         pst.setString(6,substr.getText());
         pst.setDouble(7,USD);
         pst.setString(8,"0");
         pst.setString(9,rolls);
         
       SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(jDateChooser1.getDate());
         pst.setString(10, addDate);
         pst.setString(11,projet.getText());
         pst.setString(12,libele.getText());
         pst.setString(13,"[JC]");
          pst.setString(14,code2.getText());
           pst.setDouble(15,CDF);
            pst.setDouble(16,0);
            pst.setString(17,jComboBox3.getSelectedItem().toString());
            pst.setString(18,lb.getText());
       
          
         
          pst.executeUpdate();
        
               //  JOptionPane.showMessageDialog(null,"Caisse saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
      
            try {
        PreparedStatement pst = con.prepareStatement("INSERT INTO `ohada_trans`(`COMPTE_M`, `COMPTE`, `CODE_M`, `CODE`, `CLASSE`, `SUBSTR`, `DEBIT`, `CREDIT`, `NUM`, `DATES`, `BUSS`, `LIBELLE`, `JOURNAL`, `CODE1`,DEBIT_CDF,CREDIT_CDF,DEVICE,LB)"
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        
        pst.setString(1,comptem1.getText());
        pst.setString(2, nom1.getText());
         pst.setString(3, codem1.getText());
         pst.setString(4,code2.getText());
         pst.setString(5,classe1.getText());
         pst.setString(6,substr1.getText());
         pst.setDouble(8,USD);
         pst.setString(7,"0");
         pst.setString(9,rolls);
         
       SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(jDateChooser1.getDate());
         pst.setString(10, addDate);
         pst.setString(11,projet.getText());
         pst.setString(12,libele.getText());
         pst.setString(13,"[JC]");
          pst.setString(14,code1.getText());
           pst.setString(15,"0");
            pst.setDouble(16,CDF);
            pst.setString(17,jComboBox3.getSelectedItem().toString());
            pst.setString(18,lb.getText());
       
          
         
          pst.executeUpdate();
        
                
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
  if(lb.getText().equals("LB")){
  }else{
  budget_trans();
  }
       
       }else{
       try {
        PreparedStatement pst = con.prepareStatement("INSERT INTO `ohada_trans`(`COMPTE_M`, `COMPTE`, `CODE_M`, `CODE`, `CLASSE`, `SUBSTR`, `DEBIT`, `CREDIT`, `NUM`, `DATES`, `BUSS`, `LIBELLE`, `JOURNAL`, `CODE1`,DEBIT_CDF,CREDIT_CDF,DEVICE,LB)"
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        
        pst.setString(1,comptem.getText());
        pst.setString(2, nom.getText());
         pst.setString(3, codem.getText());
         pst.setString(4,code1.getText());
         pst.setString(5,classe.getText());
         pst.setString(6,substr.getText());
         pst.setDouble(8,USD);
         pst.setString(7,"0");
         pst.setString(9,rolls);
         
       SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(jDateChooser1.getDate());
         pst.setString(10, addDate);
         pst.setString(11,projet.getText());
         pst.setString(12,libele.getText());
         pst.setString(13,"[JC]");
          pst.setString(14,code2.getText());
           pst.setString(15,"0");
            pst.setDouble(16,CDF);
            pst.setString(17,jComboBox3.getSelectedItem().toString());
            pst.setString(18,lb.getText());
       
          
         
          pst.executeUpdate();
        
               //  JOptionPane.showMessageDialog(null,"Caisse saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
      
            try {
        PreparedStatement pst = con.prepareStatement("INSERT INTO `ohada_trans`(`COMPTE_M`, `COMPTE`, `CODE_M`, `CODE`, `CLASSE`, `SUBSTR`, `DEBIT`, `CREDIT`, `NUM`, `DATES`, `BUSS`, `LIBELLE`, `JOURNAL`, `CODE1`,DEBIT_CDF,CREDIT_CDF,DEVICE,LB)"
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        
        pst.setString(1,comptem1.getText());
        pst.setString(2, nom1.getText());
         pst.setString(3, codem1.getText());
         pst.setString(4,code2.getText());
         pst.setString(5,classe1.getText());
         pst.setString(6,substr1.getText());
         pst.setDouble(7,USD);
         pst.setString(8,"0");
         pst.setString(9,rolls);
         
       SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(jDateChooser1.getDate());
         pst.setString(10, addDate);
         pst.setString(11,projet.getText());
         pst.setString(12,libele.getText());
         pst.setString(13,"[JC]");
          pst.setString(14,code1.getText());
           pst.setDouble(15,CDF);
            pst.setDouble(16,0);
            pst.setString(17,jComboBox3.getSelectedItem().toString());
            pst.setString(18,lb.getText());
       
          
         
          pst.executeUpdate();
        
                
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
   if(lb.getText().equals("LB")){
  }else{
  budget_trans();
  }
       }
   
   JOptionPane.showMessageDialog(null,"Caisse saved");
  if(jRadioButton1.isSelected()){
   show_in_table_concern();
  }else{
  show_in_table();
  }
       }
      
  }
  
  public void budget_trans(){
  call_currency();
   SimpleDateFormat dateFormatsS = new SimpleDateFormat("MMM");
         String mois = dateFormatsS.format(jDateChooser1.getDate());
         SimpleDateFormat dateFormatS = new SimpleDateFormat("yyyy-MM-dd");
         String dates = dateFormatS.format(jDateChooser1.getDate());
         SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
         String year = dateFormat.format(jDateChooser1.getDate());
       try{
    String sql="INSERT INTO `budget_trans`(`ITEM`, `DEBIT`, `CREDIT`, `SOLD`, `PROJET`, `CODE`, `NUM`, `CAT`, `DATES`, `MOIS`, `ANNEE`,SUB_CAT,CODE_CAT,CODE_SUB_CAT,`ITEMS`,BATCH) "+"values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    pst=con.prepareStatement(sql);
    pst.setString(1,libele.getText());
    pst.setString(2,"0");
    pst.setDouble(3,USD);
    pst.setDouble(4, 0.0);
    pst.setString(5,projet.getText());
    pst.setString(6,lb.getText());
    pst.setString(7,rolls);
    
    pst.setString(8,catb.getText());
   
    pst.setString(9,dates);
   
         pst.setString(10, mois);
 
         pst.setString(11,year);
    pst.setString (12,sub_catb.getText());
    
     pst.setString(13,code_cat.getText());
      pst.setString(14,code_subcat.getText());
      
      pst.setString(15,lbs.getText());
      pst.setString(16,jComboBox4.getSelectedItem().toString());
   
    
    
    pst.executeUpdate();
    // JOptionPane.showMessageDialog(null,"BUDGET TRANS");
    }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}
  
  }
  
     public void show_in_table(){
 //rolls();

 try{
    String sql="select `NUM`, `CODE1` as 'CODE',LB,`LIBELLE` AS 'DESCRIPTION OF TRANSACTION', FORMAT(`CREDIT`,2) AS DEBIT, FORMAT(`DEBIT`,2) as CREDIT, `DATES` from OHADA_TRANS WHERE  buss='"+projet.getText()+"' order by DATES";
    //`LIBELLE`, `ENTRE`, `SORTIE`, `DATE`, `BUSS`, `USED`, `NUM`
    pst = con.prepareStatement(sql);
      rs= pst.executeQuery();
     
       jTable1.setModel(DbUtils.resultSetToTableModel(rs));
    
             }catch(Exception ex){
                 JOptionPane.showMessageDialog(null, ex);
             }
                  DefaultTableCellRenderer centre =new DefaultTableCellRenderer();
                 DefaultTableCellRenderer rigth =new DefaultTableCellRenderer();
        centre.setHorizontalAlignment(JLabel.CENTER);
         TableColumn col0=jTable1.getColumnModel().getColumn(0);
        TableColumn col1=jTable1.getColumnModel().getColumn(1);
         jTable1.getColumnModel().getColumn(1).setCellRenderer(centre);
          TableColumn col2=jTable1.getColumnModel().getColumn(2);
         jTable1.getColumnModel().getColumn(2).setCellRenderer(centre);
        TableColumn col3=jTable1.getColumnModel().getColumn(3);
        TableColumn col4=jTable1.getColumnModel().getColumn(4);
          rigth.setHorizontalAlignment(JLabel.RIGHT);
            jTable1.getColumnModel().getColumn(4).setCellRenderer(rigth);
        TableColumn col5=jTable1.getColumnModel().getColumn(5);
          rigth.setHorizontalAlignment(JLabel.RIGHT);
            jTable1.getColumnModel().getColumn(5).setCellRenderer(rigth);
           TableColumn col6=jTable1.getColumnModel().getColumn(6);
           
      
       
       
       col0.setPreferredWidth(100);
       col1.setPreferredWidth(20);
       col2.setPreferredWidth(20);
       col3.setPreferredWidth(350);
       col4.setPreferredWidth(50);
       col5.setPreferredWidth(50);
       col6.setPreferredWidth(20);
             }
      public void show_in_table_concern(){
          jTable1.setDefaultRenderer(Object.class,new PINTAR_TABELA_journal()); 
  if(journal.jComboBox1.getSelectedItem().equals("JRN-CAISSE") ||journal.jComboBox1.getSelectedItem().equals("JRN-BANQUE")){
      if(jComboBox3.getSelectedItem().equals("USD")){
      try{
    String sql="select `NUM`, `CODE1` as 'CODE',LB,`LIBELLE` AS 'DESCRIPTION OF TRANSACTION', FORMAT(`CREDIT`,2) AS DEBIT, FORMAT(`DEBIT`,2) as CREDIT, `DATES` from OHADA_TRANS WHERE  buss='"+projet.getText()+"' and code='"+code1.getText()+"'order by DATES,num";
    //`LIBELLE`, `ENTRE`, `SORTIE`, `DATE`, `BUSS`, `USED`, `NUM`
    pst = con.prepareStatement(sql);
      rs= pst.executeQuery();
     
       jTable1.setModel(DbUtils.resultSetToTableModel(rs));
    
             }catch(Exception ex){
                 JOptionPane.showMessageDialog(null, ex);
             }
                 DefaultTableCellRenderer centre =new DefaultTableCellRenderer();
                 DefaultTableCellRenderer rigth =new DefaultTableCellRenderer();
        centre.setHorizontalAlignment(JLabel.CENTER);
         TableColumn col0=jTable1.getColumnModel().getColumn(0);
        TableColumn col1=jTable1.getColumnModel().getColumn(1);
         jTable1.getColumnModel().getColumn(1).setCellRenderer(centre);
          TableColumn col2=jTable1.getColumnModel().getColumn(2);
         jTable1.getColumnModel().getColumn(2).setCellRenderer(centre);
        TableColumn col3=jTable1.getColumnModel().getColumn(3);
        TableColumn col4=jTable1.getColumnModel().getColumn(4);
          rigth.setHorizontalAlignment(JLabel.RIGHT);
            jTable1.getColumnModel().getColumn(4).setCellRenderer(rigth);
        TableColumn col5=jTable1.getColumnModel().getColumn(5);
          rigth.setHorizontalAlignment(JLabel.RIGHT);
            jTable1.getColumnModel().getColumn(5).setCellRenderer(rigth);
           TableColumn col6=jTable1.getColumnModel().getColumn(6);
           
      
       
       col0.setPreferredWidth(80);
       col1.setPreferredWidth(10);
       col2.setPreferredWidth(10);
       col3.setPreferredWidth(500);
       col4.setPreferredWidth(20);
       col5.setPreferredWidth(20);
       col6.setPreferredWidth(20);
             
      }else{
        
      try{
    String sql="select `NUM`, `CODE1` as 'CODE',LB,`LIBELLE` AS 'DESCRIPTION OF TRANSACTION', FORMAT(`CREDIT_CDF`,2) AS DEBIT, FORMAT(`DEBIT_CDF`,2) as CREDIT, `DATES` from OHADA_TRANS WHERE  buss='"+projet.getText()+"' and code='"+code1.getText()+"'order by DATES,num";
    //`LIBELLE`, `ENTRE`, `SORTIE`, `DATE`, `BUSS`, `USED`, `NUM`
    pst = con.prepareStatement(sql);
      rs= pst.executeQuery();
     
       jTable1.setModel(DbUtils.resultSetToTableModel(rs));
    
             }catch(Exception ex){
                 JOptionPane.showMessageDialog(null, ex);
             }
                     DefaultTableCellRenderer centre =new DefaultTableCellRenderer();
                 DefaultTableCellRenderer rigth =new DefaultTableCellRenderer();
        centre.setHorizontalAlignment(JLabel.CENTER);
         TableColumn col0=jTable1.getColumnModel().getColumn(0);
        TableColumn col1=jTable1.getColumnModel().getColumn(1);
         jTable1.getColumnModel().getColumn(1).setCellRenderer(centre);
          TableColumn col2=jTable1.getColumnModel().getColumn(2);
         jTable1.getColumnModel().getColumn(2).setCellRenderer(centre);
        TableColumn col3=jTable1.getColumnModel().getColumn(3);
        TableColumn col4=jTable1.getColumnModel().getColumn(4);
          rigth.setHorizontalAlignment(JLabel.RIGHT);
            jTable1.getColumnModel().getColumn(4).setCellRenderer(rigth);
        TableColumn col5=jTable1.getColumnModel().getColumn(5);
          rigth.setHorizontalAlignment(JLabel.RIGHT);
            jTable1.getColumnModel().getColumn(5).setCellRenderer(rigth);
           TableColumn col6=jTable1.getColumnModel().getColumn(6);
           
           
      
       
       
 col0.setPreferredWidth(80);
       col1.setPreferredWidth(10);
       col2.setPreferredWidth(10);
       col3.setPreferredWidth(500);
       col4.setPreferredWidth(20);
       col5.setPreferredWidth(20);
       col6.setPreferredWidth(20);
      
      }
   
  
  }else{
      
  if(jComboBox3.getSelectedItem().equals("USD")){
     try{
    String sql="select `NUM`, `CODE1` as 'CODE',LB,`LIBELLE` AS 'DESCRIPTION OF TRANSACTION', FORMAT(`CREDIT`,2) AS DEBIT, FORMAT(`DEBIT`,2) as CREDIT, `DATES` from OHADA_TRANS WHERE  buss='"+projet.getText()+"' and code='"+code1.getText()+"'order by DATES,num";
    //`LIBELLE`, `ENTRE`, `SORTIE`, `DATE`, `BUSS`, `USED`, `NUM`
    pst = con.prepareStatement(sql);
      rs= pst.executeQuery();
     
       jTable1.setModel(DbUtils.resultSetToTableModel(rs));
    
             }catch(Exception ex){
                 JOptionPane.showMessageDialog(null, ex);
             }
                  DefaultTableCellRenderer centre =new DefaultTableCellRenderer();
                 DefaultTableCellRenderer rigth =new DefaultTableCellRenderer();
        centre.setHorizontalAlignment(JLabel.CENTER);
         TableColumn col0=jTable1.getColumnModel().getColumn(0);
        TableColumn col1=jTable1.getColumnModel().getColumn(1);
         jTable1.getColumnModel().getColumn(1).setCellRenderer(centre);
          TableColumn col2=jTable1.getColumnModel().getColumn(2);
         jTable1.getColumnModel().getColumn(2).setCellRenderer(centre);
        TableColumn col3=jTable1.getColumnModel().getColumn(3);
        TableColumn col4=jTable1.getColumnModel().getColumn(4);
          rigth.setHorizontalAlignment(JLabel.RIGHT);
            jTable1.getColumnModel().getColumn(4).setCellRenderer(rigth);
        TableColumn col5=jTable1.getColumnModel().getColumn(5);
          rigth.setHorizontalAlignment(JLabel.RIGHT);
            jTable1.getColumnModel().getColumn(5).setCellRenderer(rigth);
           TableColumn col6=jTable1.getColumnModel().getColumn(6);
           
      
       
       
       col0.setPreferredWidth(80);
       col1.setPreferredWidth(10);
       col2.setPreferredWidth(10);
       col3.setPreferredWidth(500);
       col4.setPreferredWidth(20);
       col5.setPreferredWidth(20);
       col6.setPreferredWidth(20);  
  }else{
  
   try{
    String sql="select `NUM`, `CODE1` as 'CODE',LB,`LIBELLE` AS 'DESCRIPTION OF TRANSACTION', FORMAT(`CREDIT_CDF`,2) AS DEBIT, FORMAT(`DEBIT_CDF`,2) as CREDIT, `DATES` from OHADA_TRANS WHERE  buss='"+projet.getText()+"' and code='"+code1.getText()+"'order by DATES,num";
    //`LIBELLE`, `ENTRE`, `SORTIE`, `DATE`, `BUSS`, `USED`, `NUM`
    pst = con.prepareStatement(sql);
      rs= pst.executeQuery();
     
       jTable1.setModel(DbUtils.resultSetToTableModel(rs));
    
             }catch(Exception ex){
                 JOptionPane.showMessageDialog(null, ex);
             }
                   DefaultTableCellRenderer centre =new DefaultTableCellRenderer();
                 DefaultTableCellRenderer rigth =new DefaultTableCellRenderer();
        centre.setHorizontalAlignment(JLabel.CENTER);
         TableColumn col0=jTable1.getColumnModel().getColumn(0);
        TableColumn col1=jTable1.getColumnModel().getColumn(1);
         jTable1.getColumnModel().getColumn(1).setCellRenderer(centre);
          TableColumn col2=jTable1.getColumnModel().getColumn(2);
         jTable1.getColumnModel().getColumn(2).setCellRenderer(centre);
        TableColumn col3=jTable1.getColumnModel().getColumn(3);
        TableColumn col4=jTable1.getColumnModel().getColumn(4);
          rigth.setHorizontalAlignment(JLabel.RIGHT);
            jTable1.getColumnModel().getColumn(4).setCellRenderer(rigth);
        TableColumn col5=jTable1.getColumnModel().getColumn(5);
          rigth.setHorizontalAlignment(JLabel.RIGHT);
            jTable1.getColumnModel().getColumn(5).setCellRenderer(rigth);
           TableColumn col6=jTable1.getColumnModel().getColumn(6);
           
      
       
       
       col0.setPreferredWidth(80);
       col1.setPreferredWidth(10);
       col2.setPreferredWidth(10);
       col3.setPreferredWidth(500);
       col4.setPreferredWidth(20);
       col5.setPreferredWidth(20);
       col6.setPreferredWidth(20);
  }
  
  }
      }
      
public void save_Contract(){
          int indexs[]=jTable1.getSelectedRows();
        String LB = null,PRO = null,num_fact=null;
        Double CRE = null,DE;
        for(int i=0; i < indexs.length;i++){
             String nums = (jTable1.getModel().getValueAt(indexs[i],0). toString());
              String buss = projet.getText();
            
         try{
            String sqls="select CODE,DEBIT,CREDIT,PROJET from budget_trans where NUM='"+nums+"' and projet='"+buss+"'";
            
           
             pst=con.prepareStatement(sqls);
          //  pst.setString(1, A);
            rs=pst.executeQuery();
            if(rs.next()){
             LB = rs.getString("CODE");
             CRE = rs.getDouble("CREDIT");
             DE = rs.getDouble("DEBIT");
             PRO = rs.getString("PROJET");
            }
            
            }
        catch(Exception ex){
       msg.setText(""+ex);   
        }
         
          try{
            String sqls="select NUM_FACTURE from ohada_trans where NUM='"+nums+"' and buss='"+buss+"'";
            
           
             pst=con.prepareStatement(sqls);
          //  pst.setString(1, A);
            rs=pst.executeQuery();
            if(rs.next()){
            num_fact = rs.getString("NUM_FACTURE");
            
            }
            
            }
        catch(Exception ex){
        msg.setText(""+ex);
        }
        
        
        
         try{
        String sql = "DELETE FROM ohada_trans WHERE NUM=? and buss='"+buss+"'";
        
         pst = con.prepareStatement(sql);
         pst.setString(1,nums);
         pst.executeUpdate();

     }catch(SQLException | HeadlessException ex ){
        msg.setText(""+ex);
     } 
          try{
        String sql = "DELETE FROM caisses WHERE NUM=? and buss='"+buss+"'";
        
         pst = con.prepareStatement(sql);
         pst.setString(1,nums);
         pst.executeUpdate();

     }catch(SQLException | HeadlessException ex ){
        msg.setText(""+ex);
     } 
            try{
        String sql = "DELETE FROM budget_trans WHERE NUM=? and projet='"+buss+"'";
        
         pst = con.prepareStatement(sql);
         pst.setString(1,nums);
         pst.executeUpdate();

     }catch(SQLException | HeadlessException ex ){
       msg.setText(""+ex); 
     } 
            
            
            
              
                  try{
         PreparedStatement pst = con.prepareStatement("UPDATE `ohada_trans` SET `Pay`='No' WHERE NUM_FACTURE='"+num_fact+"' and buss='"+buss+"'");
      pst.executeUpdate();

     }catch(SQLException | HeadlessException ex ){
       msg.setText(""+ex); 
     }
           
                     try{
         PreparedStatement pst = con.prepareStatement("UPDATE `etat_de_besoin` SET `ECRITURE`='' WHERE NUM='"+nums+"' and buss='"+buss+"'");
      pst.executeUpdate();

     }catch(SQLException | HeadlessException ex ){
       msg.setText(""+ex); 
     }      
        
        }
      msg.setText("Data Deleted"); 
       
          LB = null;
          PRO = null;
        CRE = null;
        DE=null;
     }
public void saveusd(){
          
       int rowS= jTable1.getSelectedRow();
     
        String LBSUB= null,CAT = null,SUB_CAT= null,ITEM= null,CODE_CAT= null,CODE_SUB_CAT= null,NAMEOHADA= null,CODEMEREOHADA= null,CODEOHADA= null,CLASSEOHDA= null,SUBSTRSOHADA= null,COMPTEMERESOHADA= null, code= null,lbss= null,libelle= null,debits= null,credit= null,dates= null ; 
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
        SimpleDateFormat dateFormats = new SimpleDateFormat("mm");
         String years = dateFormat.format(jDateChooser1.getDate());
         String mois = dateFormats.format(jDateChooser1.getDate());
        int indexs[]=jTable1.getSelectedRows();
       
        for(int i=0; i < indexs.length;i++){
            code = (jTable1.getModel().getValueAt(rowS,1). toString());
         lbss = (jTable1.getModel().getValueAt(indexs[i],2). toString());
         libelle = (jTable1.getModel().getValueAt(indexs[i],3). toString());
        debits = (jTable1.getModel().getValueAt(indexs[i],4). toString());
         credit = (jTable1.getModel().getValueAt(indexs[i],5). toString());
           dates = (jTable1.getModel().getValueAt(indexs[i],6). toString());
           
          String debit= debits.replace(",", "");
           try{
         
         String sql="SELECT NUM FROM ohada_trans where SUBSTR(NUM,5,10)='"+dates+"' AND BUSS='"+projet.getText()+"'  ORDER BY NUM DESC LIMIT 1";
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
               rolls= "No: "+dates+"/1001";
                
             }
         }catch(NumberFormatException | SQLException e){
             JOptionPane.showMessageDialog(null, e);  
         }
          roll.setText(rolls);
            
    String DEVICE = null;
        

 try{
          String sql="SELECT * FROM  budget_code WHERE code='"+lbss+"' and CAT='"+projet.getText()+"'";
          
            pst=con.prepareStatement(sql);
            //pst.setString(1, tmp);
            rs=pst.executeQuery();
            if(rs.next()){
                 
                ITEM = rs.getString("item");
        
          }
 }catch(Exception ex){
       JOptionPane.showMessageDialog(null, ex);;  
      }
  
      try{
          String sql="SELECT LB,LBSUB,CAT,SUB_CAT FROM  budget WHERE code='"+lbss+"' and PROJECT='"+projet.getText()+"'";
          
            pst=con.prepareStatement(sql);
            //pst.setString(1, tmp);
            rs=pst.executeQuery();
            if(rs.next()){
                CODE_CAT = rs.getString("LB");  
               CODE_SUB_CAT = rs.getString("LBSUB");
             CAT = rs.getString("CAT");
             SUB_CAT = rs.getString("SUB_CAT");
          }
  
      }catch(Exception ex){
       JOptionPane.showMessageDialog(null, ex);;  
      }
      
       try{
          String sql="SELECT * FROM ohada where CODE ='"+code+"'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                //String sum=rs.getString("nom");
                //String sum=rs.getString("nom");
                NAMEOHADA=rs.getString("NAME");
               CODEMEREOHADA=rs.getString("CODEMERE");
                CODEOHADA=rs.getString("CODE");
                COMPTEMERESOHADA=rs.getString("COMPTEMERE");
                  CLASSEOHDA=rs.getString("CLASS");
                  SUBSTRSOHADA=rs.getString("SUBSTR");
                
               
                
                
            }
            }
        catch(Exception ex){
        JOptionPane.showMessageDialog(null, ex);   
        }
      try {
         ////
       
         PreparedStatement pst = con.prepareStatement("INSERT INTO `budget_trans`(`ITEM`, `DEBIT`, `CREDIT`, `SOLD`,`PROJET`, `CODE`, `NUM`, `CAT`, `DATES`, `MOIS`, `ANNEE`,SUB_CAT,CODE_CAT,CODE_SUB_CAT,`ITEMS`,BATCH) "
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
       
        //budget,code_budget,cat_budget,ohada_code,ohada_account,
         pst.setString(1, libelle);
         pst.setString(2,"0");
          pst.setString(3, debit);
          pst.setDouble(4, 0.0);
          pst.setString(5, projet.getText());
        pst.setString(6, lbss);
         pst.setString(7,rolls);
         
         pst.setString(8,CAT);
          
         pst.setString(9, dates);
         pst.setString(10,mois);
          pst.setString(11,years);
            pst.setString(12,SUB_CAT);
             pst.setString(13,CODE_CAT);
              pst.setString(14,CODE_SUB_CAT);
              pst.setString(15,ITEM);
                pst.setString(16,jComboBox4.getSelectedItem().toString());
      
       
         
          pst.executeUpdate();
        
              //   JOptionPane.showMessageDialog(null,"data saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex);}
         
         
        
     
     
        //========>>>>DEBIT
        ///INSERT INTO `ohada_trans`(`ID`, `COMPTE_M`, `COMPTE`, `CODE_M`, `CODE`, `CLASSE`, `SUBSTR`, `DEBIT`, `CREDIT`, `NUM`, `DATES`, `BUSS`, `LIBELLE`, `JOURNAL`)
         try {
        // String tmp =jComboBox3.getSelectedItem().toString();
         PreparedStatement pst = con.prepareStatement("INSERT INTO `ohada_trans`(`COMPTE_M`, `COMPTE`, `CODE_M`, `CODE`, `CLASSE`, `SUBSTR`, `DEBIT`, `CREDIT`, `NUM`, `DATES`, `BUSS`, `LIBELLE`, `JOURNAL`, `CODE1`,DEBIT_CDF,CREDIT_CDF,DEVICE,LB)"
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        
     pst.setString(1,comptem.getText());
        pst.setString(2, nom.getText());
         pst.setString(3, codem.getText());
         pst.setString(4,code1.getText());
         pst.setString(5,classe.getText());
         pst.setString(6,substr.getText());
         pst.setString(8,debit);
         pst.setString(7,"0");
         pst.setString(9,rolls);
         
         pst.setString(10, dates);
         pst.setString(11,projet.getText());
         pst.setString(12,libelle);
         pst.setString(13,"[JC]");
          pst.setString(14,code);
           pst.setString(15,"0");
            pst.setString(16,"0");
             pst.setString(17,"USD");
              pst.setString(18,lbss);
       
          
         
          pst.executeUpdate();
        
           //      JOptionPane.showMessageDialog(null,"Caisse saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex);
    }
      
           try {
        // String tmp =jComboBox3.getSelectedItem().toString();
        PreparedStatement pst = con.prepareStatement("INSERT INTO `ohada_trans`(`COMPTE_M`, `COMPTE`, `CODE_M`, `CODE`, `CLASSE`, `SUBSTR`, `DEBIT`, `CREDIT`, `NUM`, `DATES`, `BUSS`, `LIBELLE`, `JOURNAL`, `CODE1`,DEBIT_CDF,CREDIT_CDF,DEVICE,LB)"
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
       // NAMEOHADA= null,CODEMEREOHADA= null,CODEOHADA= null,CLASSEOHDA= null,SUBSTRSOHADA= null,COMPTEMERESOHADA= null 
        pst.setString(1, COMPTEMERESOHADA);
        pst.setString(2, NAMEOHADA);
         pst.setString(3, CODEMEREOHADA);
         pst.setString(4, code);
         pst.setString(5,CLASSEOHDA);
         pst.setString(6, SUBSTRSOHADA);
         pst.setString(7,debit);
         pst.setString(8,"0");
         pst.setString(9,rolls);
         
         pst.setString(10, dates);
         pst.setString(11,projet.getText());
         pst.setString(12,libelle);
         pst.setString(13,"[JC]");
          pst.setString(14,code1.getText());
           pst.setString(15,"0");
            pst.setString(16,"0");
              pst.setString(17,"USD");
               pst.setString(18,lbss);
       
          
         
          pst.executeUpdate();
        
           //      JOptionPane.showMessageDialog(null,"Caisse saved");
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(null, ex);}
      
           
            // CAISSE

       try {
      //   String tmp =jComboBox3.getSelectedItem().toString();
       PreparedStatement pst = con.prepareStatement("INSERT INTO `caisses`(`CODE`,`CODEMERE`,`LIBELE`,`BUSS`,`DEBIT`,`CREDIT`,`DATE`,`COMPTE`,NUM,`MOIS`, `ANNEE`, `BUDGET`, `SOLDE`, `DEVICE`,PRINT)"
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        
        pst.setString(1, code);
         pst.setString(2, code1.getText());
         pst.setString(3, libelle);
         pst.setString(4, projet.getText());
         pst.setString(5, "0");
         pst.setString(6,debit);
         pst.setString(8,nom.getText());
         pst.setString(9,rolls);
         pst.setString(10, mois);
         pst.setString(11, years);;
         pst.setString(12,lbss);
         pst.setString(13,"OK");
         pst.setString(14,"USD");
         pst.setString(15,"Yes");
       
         pst.setString(7, dates);
         
          pst.executeUpdate();
        
               
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex);
    }
        
      
    //    }
         
        
        }    
         msg.setText("Transaction Saved");
       }

public void saveusdcdf(){
          
       int rowS= jTable1.getSelectedRow();
     
        String LBSUB= null,CAT = null,SUB_CAT= null,ITEM= null,CODE_CAT= null,CODE_SUB_CAT= null,NAMEOHADA= null,CODEMEREOHADA= null,CODEOHADA= null,CLASSEOHDA= null,SUBSTRSOHADA= null,COMPTEMERESOHADA= null ; 
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
        SimpleDateFormat dateFormats = new SimpleDateFormat("mm");
         String years = dateFormat.format(jDateChooser1.getDate());
         String mois = dateFormats.format(jDateChooser1.getDate());
         
          
        int indexs[]=jTable1.getSelectedRows();
       
        for(int i=0; i < indexs.length;i++){
             String code = (jTable1.getModel().getValueAt(rowS,1). toString());
        String lbss = (jTable1.getModel().getValueAt(indexs[i],2). toString());
        String libelle = (jTable1.getModel().getValueAt(indexs[i],3). toString());
        String debits = (jTable1.getModel().getValueAt(indexs[i],4). toString());
        String credit = (jTable1.getModel().getValueAt(indexs[i],5). toString());
          String dates = (jTable1.getModel().getValueAt(indexs[i],6). toString());
          String debit= debits.replace(",", "");
  
 // Double a = Double.parseDouble(debit);
           try{
         
         String sql="SELECT NUM FROM ohada_trans where SUBSTR(NUM,5,10)='"+dates+"' AND BUSS='"+projet.getText()+"'  ORDER BY NUM DESC LIMIT 1";
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
               rolls= "No: "+dates+"/1001";
                
             }
         }catch(NumberFormatException | SQLException e){
             JOptionPane.showMessageDialog(null, e);  
         }
          roll.setText(rolls);
            
      Double PR = Double.parseDouble(debit);
      String DEVICE = null;
 try{
String sql="select * from monais WHERE caisse='"+code1.getText()+"'";      
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            if(rs.next()){
            DEVICE=rs.getString("DEVICE");  
           // jComboBox3.setSelectedItem(DEVICE);
            }
        //  con.close();
            }
        
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex); }

 try{
String sql="SELECT * FROM  currency where APPR='"+DEVICE+"'";
           
            pst=con.prepareStatement(sql);
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
  if(jComboBox3.getSelectedItem().equals("USD")){
   CDF=PR*buy;
  USD=PR;
  
  }else{
     
  USD=PR/sale;
  CDF=PR;
  }
        
          try{
          String sql="SELECT * FROM  budget_code WHERE code='"+lbss+"' and CAT='"+projet.getText()+"'";
          
            pst=con.prepareStatement(sql);
            //pst.setString(1, tmp);
            rs=pst.executeQuery();
            if(rs.next()){
                 
                ITEM = rs.getString("item");
        
          }
  
      }catch(Exception ex){
     JOptionPane.showMessageDialog(null, ex);
      }
      
   
      try{
          String sql="SELECT LB,LBSUB,CAT,SUB_CAT FROM  budget WHERE code='"+lbss+"' and PROJECT='"+projet.getText()+"'";
          
            pst=con.prepareStatement(sql);
            //pst.setString(1, tmp);
            rs=pst.executeQuery();
            if(rs.next()){
                CODE_CAT = rs.getString("LB");  
               CODE_SUB_CAT = rs.getString("LBSUB");
             CAT = rs.getString("CAT");
             SUB_CAT = rs.getString("SUB_CAT");
          }
  
      }catch(Exception ex){
       JOptionPane.showMessageDialog(null, ex);;  
      }
      
       try{
          String sql="SELECT * FROM ohada where CODE ='"+code+"'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                //String sum=rs.getString("nom");
                //String sum=rs.getString("nom");
                NAMEOHADA=rs.getString("NAME");
               CODEMEREOHADA=rs.getString("CODEMERE");
                CODEOHADA=rs.getString("CODE");
                COMPTEMERESOHADA=rs.getString("COMPTEMERE");
                  CLASSEOHDA=rs.getString("CLASS");
                  SUBSTRSOHADA=rs.getString("SUBSTR");
                
               
                
                
            }
            }
        catch(Exception ex){
        JOptionPane.showMessageDialog(null, ex);   
        }
      try {
         ////
       
         PreparedStatement pst = con.prepareStatement("INSERT INTO `budget_trans`(`ITEM`, `DEBIT`, `CREDIT`, `SOLD`,`PROJET`, `CODE`, `NUM`, `CAT`, `DATES`, `MOIS`, `ANNEE`,SUB_CAT,CODE_CAT,CODE_SUB_CAT,`ITEMS`,BATCH) "
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
       
        //budget,code_budget,cat_budget,ohada_code,ohada_account,
         pst.setString(1, libelle);
         pst.setString(2,"0");
          pst.setDouble(3, USD);
          pst.setDouble(4, 0.0);
          pst.setString(5, projet.getText());
        pst.setString(6, lbss);
         pst.setString(7,rolls);
         
         pst.setString(8,CAT);
          
         pst.setString(9, dates);
         pst.setString(10,mois);
          pst.setString(11,years);
            pst.setString(12,SUB_CAT);
             pst.setString(13,CODE_CAT);
              pst.setString(14,CODE_SUB_CAT);
              pst.setString(15,ITEM);
               pst.setString(16,jComboBox4.getSelectedItem().toString());
      
       
         
          pst.executeUpdate();
        
              //   JOptionPane.showMessageDialog(null,"data saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex);}
         
         
        
     
     
        //========>>>>DEBIT
        ///INSERT INTO `ohada_trans`(`ID`, `COMPTE_M`, `COMPTE`, `CODE_M`, `CODE`, `CLASSE`, `SUBSTR`, `DEBIT`, `CREDIT`, `NUM`, `DATES`, `BUSS`, `LIBELLE`, `JOURNAL`)
         try {
        // String tmp =jComboBox3.getSelectedItem().toString();
         PreparedStatement pst = con.prepareStatement("INSERT INTO `ohada_trans`(`COMPTE_M`, `COMPTE`, `CODE_M`, `CODE`, `CLASSE`, `SUBSTR`, `DEBIT`, `CREDIT`, `NUM`, `DATES`, `BUSS`, `LIBELLE`, `JOURNAL`, `CODE1`,DEBIT_CDF,CREDIT_CDF,DEVICE,LB)"
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        
     pst.setString(1,comptem.getText());
        pst.setString(2, nom.getText());
         pst.setString(3, codem.getText());
         pst.setString(4,code1.getText());
         pst.setString(5,classe.getText());
         pst.setString(6,substr.getText());
         pst.setDouble(8,USD);
         pst.setString(7,"0");
         pst.setString(9,rolls);
         
         pst.setString(10, dates);
         pst.setString(11,projet.getText());
         pst.setString(12,libelle);
         pst.setString(13,"[JC]");
          pst.setString(14,code);
           pst.setString(15,"0");
            pst.setDouble(16,CDF);
             pst.setString(17,DEVICE);
              pst.setString(18,lbss);
       
          
         
          pst.executeUpdate();
        
           //      JOptionPane.showMessageDialog(null,"Caisse saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex);
    }
      
           try {
        // String tmp =jComboBox3.getSelectedItem().toString();
        PreparedStatement pst = con.prepareStatement("INSERT INTO `ohada_trans`(`COMPTE_M`, `COMPTE`, `CODE_M`, `CODE`, `CLASSE`, `SUBSTR`, `DEBIT`, `CREDIT`, `NUM`, `DATES`, `BUSS`, `LIBELLE`, `JOURNAL`, `CODE1`,DEBIT_CDF,CREDIT_CDF,DEVICE,LB)"
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
       // NAMEOHADA= null,CODEMEREOHADA= null,CODEOHADA= null,CLASSEOHDA= null,SUBSTRSOHADA= null,COMPTEMERESOHADA= null 
        pst.setString(1, COMPTEMERESOHADA);
        pst.setString(2, NAMEOHADA);
         pst.setString(3, CODEMEREOHADA);
         pst.setString(4, code);
         pst.setString(5,CLASSEOHDA);
         pst.setString(6, SUBSTRSOHADA);
         pst.setDouble(7,USD);
         pst.setString(8,"0");
         pst.setString(9,rolls);
         
         pst.setString(10, dates);
         pst.setString(11,projet.getText());
         pst.setString(12,libelle);
         pst.setString(13,"[JC]");
          pst.setString(14,code1.getText());
           pst.setDouble(15,CDF);
            pst.setString(16,"0");
              pst.setString(17,DEVICE);
               pst.setString(18,lbss);
       
          
         
          pst.executeUpdate();
        
           //      JOptionPane.showMessageDialog(null,"Caisse saved");
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(null, ex);}
      
           
            // CAISSE

       try {
      //   String tmp =jComboBox3.getSelectedItem().toString();
       PreparedStatement pst = con.prepareStatement("INSERT INTO `caisses`(`CODE`,`CODEMERE`,`LIBELE`,`BUSS`,`DEBIT`,`CREDIT`,`DATE`,`COMPTE`,NUM,`MOIS`, `ANNEE`, `BUDGET`, `SOLDE`, `DEVICE`,PRINT)"
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        
        pst.setString(1, code);
         pst.setString(2, code1.getText());
         pst.setString(3, libelle);
         pst.setString(4, projet.getText());
         pst.setString(5, "0");
         pst.setDouble(6,CDF);
         pst.setString(8,nom.getText());
         pst.setString(9,rolls);
         pst.setString(10, mois);
         pst.setString(11, years);;
         pst.setString(12,lbss);
         pst.setString(13,"OK");
         pst.setString(14,DEVICE);
         pst.setString(15,"Yes");
       
         pst.setString(7, dates);
         
          pst.executeUpdate();
        
               
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex);
    }
        
      
      
        
       
        
        }    
         msg.setText("Transaction Saved");
       }
public void transfertcaisse(){
int rowS= jTable1.getSelectedRow();
     
        String LBSUB= null,CAT = null,SUB_CAT= null,ITEM= null,CODE_CAT= null,CODE_SUB_CAT= null,NAMEOHADA= null,CODEMEREOHADA= null,CODEOHADA= null,CLASSEOHDA= null,SUBSTRSOHADA= null,COMPTEMERESOHADA= null, code= null,lbss= null,libelle= null,debit= null,credit= null,dates= null ; 
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
        SimpleDateFormat dateFormats = new SimpleDateFormat("mm");
         String years = dateFormat.format(jDateChooser1.getDate());
         String mois = dateFormats.format(jDateChooser1.getDate());
        int indexs[]=jTable1.getSelectedRows();
       
        for(int i=0; i < indexs.length;i++){
            code = (jTable1.getModel().getValueAt(rowS,1). toString());
         libelle = (jTable1.getModel().getValueAt(indexs[i],3). toString());
        debit = (jTable1.getModel().getValueAt(indexs[i],4). toString());
         credit = (jTable1.getModel().getValueAt(indexs[i],5). toString());
           dates = (jTable1.getModel().getValueAt(indexs[i],6). toString());
           try{
         
         String sql="SELECT NUM FROM ohada_trans where SUBSTR(NUM,5,10)='"+dates+"' AND BUSS='"+projet.getText()+"'  ORDER BY NUM DESC LIMIT 1";
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
               rolls= "No: "+dates+"/1001";
                
             }
         }catch(NumberFormatException | SQLException e){
             JOptionPane.showMessageDialog(null, e);  
         }

if(debit.equals("0.00")){
    try{
          String sql="SELECT * FROM ohada where CODE ='"+code+"'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                //String sum=rs.getString("nom");
                //String sum=rs.getString("nom");
                NAMEOHADA=rs.getString("NAME");
               CODEMEREOHADA=rs.getString("CODEMERE");
                CODEOHADA=rs.getString("CODE");
                COMPTEMERESOHADA=rs.getString("COMPTEMERE");
                  CLASSEOHDA=rs.getString("CLASS");
                  SUBSTRSOHADA=rs.getString("SUBSTR");
            }
            }
        catch(Exception ex){
        JOptionPane.showMessageDialog(null, ex);   
        }
try {
        // String tmp =jComboBox3.getSelectedItem().toString();
         PreparedStatement pst = con.prepareStatement("INSERT INTO `ohada_trans`(`COMPTE_M`, `COMPTE`, `CODE_M`, `CODE`, `CLASSE`, `SUBSTR`, `DEBIT`, `CREDIT`, `NUM`, `DATES`, `BUSS`, `LIBELLE`, `JOURNAL`, `CODE1`,DEBIT_CDF,CREDIT_CDF,DEVICE,LB)"
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        
     pst.setString(1,comptem.getText());
        pst.setString(2, nom.getText());
         pst.setString(3, codem.getText());
         pst.setString(4,code1.getText());
         pst.setString(5,classe.getText());
         pst.setString(6,substr.getText());
         pst.setString(7,credit);
         pst.setString(8,"0");
         pst.setString(9,rolls);
         
         pst.setString(10, dates);
         pst.setString(11,projet.getText());
         pst.setString(12,libelle);
         pst.setString(13,"OK");
          pst.setString(14,code);
           pst.setString(16,"0");
            pst.setString(15,"0");
             pst.setString(17,"USD");
              pst.setString(18,"");
       
          
         
          pst.executeUpdate();
        
           //      JOptionPane.showMessageDialog(null,"Caisse saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex);
    }
       Double PR = Double.parseDouble(credit);
      String DEVICE = null;
 try{
String sql="select * from monais WHERE caisse='"+code1.getText()+"'";      
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            if(rs.next()){
            DEVICE=rs.getString("DEVICE");  
           // jComboBox3.setSelectedItem(DEVICE);
            }
        //  con.close();
            }
        
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex); }

 try{
String sql="SELECT * FROM  currency where APPR='"+DEVICE+"'";
           
            pst=con.prepareStatement(sql);
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
  if(jComboBox3.getSelectedItem().equals("USD")){
   CDF=PR*buy;
  USD=PR;
  
  }else{
     
  USD=PR/sale;
  CDF=PR;
  }
           try {
        // String tmp =jComboBox3.getSelectedItem().toString();
        PreparedStatement pst = con.prepareStatement("INSERT INTO `ohada_trans`(`COMPTE_M`, `COMPTE`, `CODE_M`, `CODE`, `CLASSE`, `SUBSTR`, `DEBIT`, `CREDIT`, `NUM`, `DATES`, `BUSS`, `LIBELLE`, `JOURNAL`, `CODE1`,DEBIT_CDF,CREDIT_CDF,DEVICE,LB)"
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
       // NAMEOHADA= null,CODEMEREOHADA= null,CODEOHADA= null,CLASSEOHDA= null,SUBSTRSOHADA= null,COMPTEMERESOHADA= null 
        pst.setString(1, COMPTEMERESOHADA);
        pst.setString(2, NAMEOHADA);
         pst.setString(3, CODEMEREOHADA);
         pst.setString(4, code);
         pst.setString(5,CLASSEOHDA);
         pst.setString(6, SUBSTRSOHADA);
         pst.setDouble(8,USD);
         pst.setString(7,"0");
         pst.setString(9,rolls);
         
         pst.setString(10, dates);
         pst.setString(11,projet.getText());
         pst.setString(12,libelle);
         pst.setString(13,"[JC]");
          pst.setString(14,code1.getText());
           pst.setString(16,"0");
            pst.setDouble(15,CDF);
              pst.setString(17,DEVICE);
               pst.setString(18,"");
       
          
         
          pst.executeUpdate();
        
           //      JOptionPane.showMessageDialog(null,"Caisse saved");
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(null, ex);}
    

        
}else{
    try{
          String sql="SELECT * FROM ohada where CODE ='"+code+"'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                //String sum=rs.getString("nom");
                //String sum=rs.getString("nom");
                NAMEOHADA=rs.getString("NAME");
               CODEMEREOHADA=rs.getString("CODEMERE");
                CODEOHADA=rs.getString("CODE");
                COMPTEMERESOHADA=rs.getString("COMPTEMERE");
                  CLASSEOHDA=rs.getString("CLASS");
                  SUBSTRSOHADA=rs.getString("SUBSTR");
            }
            }
        catch(Exception ex){
        JOptionPane.showMessageDialog(null, ex);   
        }
try {
        // String tmp =jComboBox3.getSelectedItem().toString();
         PreparedStatement pst = con.prepareStatement("INSERT INTO `ohada_trans`(`COMPTE_M`, `COMPTE`, `CODE_M`, `CODE`, `CLASSE`, `SUBSTR`, `DEBIT`, `CREDIT`, `NUM`, `DATES`, `BUSS`, `LIBELLE`, `JOURNAL`, `CODE1`,DEBIT_CDF,CREDIT_CDF,DEVICE,LB)"
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        
     pst.setString(1,comptem.getText());
        pst.setString(2, nom.getText());
         pst.setString(3, codem.getText());
         pst.setString(4,code1.getText());
         pst.setString(5,classe.getText());
         pst.setString(6,substr.getText());
         pst.setString(8,debit);
         pst.setString(7,"0");
         pst.setString(9,rolls);
         
         pst.setString(10, dates);
         pst.setString(11,projet.getText());
         pst.setString(12,libelle);
         pst.setString(13,"[JC]");
          pst.setString(14,code);
           pst.setString(16,"0");
            pst.setString(15,"0");
             pst.setString(17,"USD");
              pst.setString(18,"");
       
          
         
          pst.executeUpdate();
        
           //      JOptionPane.showMessageDialog(null,"Caisse saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex);
    }
       Double PR = Double.parseDouble(debit);
      String DEVICE = null;
 try{
String sql="select * from monais WHERE caisse='"+code+"'";      
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            if(rs.next()){
            DEVICE=rs.getString("DEVICE");  
           // jComboBox3.setSelectedItem(DEVICE);
            }
        //  con.close();
            }
        
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex); }

 try{
String sql="SELECT * FROM  currency where APPR='"+DEVICE+"'";
           
            pst=con.prepareStatement(sql);
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
  if(jComboBox3.getSelectedItem().equals("USD")){
   CDF=PR*buy;
  USD=PR;
  
  }else{
     
  USD=PR/sale;
  CDF=PR;
  }
           try {
        // String tmp =jComboBox3.getSelectedItem().toString();
        PreparedStatement pst = con.prepareStatement("INSERT INTO `ohada_trans`(`COMPTE_M`, `COMPTE`, `CODE_M`, `CODE`, `CLASSE`, `SUBSTR`, `DEBIT`, `CREDIT`, `NUM`, `DATES`, `BUSS`, `LIBELLE`, `JOURNAL`, `CODE1`,DEBIT_CDF,CREDIT_CDF,DEVICE,LB)"
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
       // NAMEOHADA= null,CODEMEREOHADA= null,CODEOHADA= null,CLASSEOHDA= null,SUBSTRSOHADA= null,COMPTEMERESOHADA= null 
        pst.setString(1, COMPTEMERESOHADA);
        pst.setString(2, NAMEOHADA);
         pst.setString(3, CODEMEREOHADA);
         pst.setString(4, code);
         pst.setString(5,CLASSEOHDA);
         pst.setString(6, SUBSTRSOHADA);
         pst.setDouble(7,USD);
         pst.setString(8,"0");
         pst.setString(9,rolls);
         
         pst.setString(10, dates);
         pst.setString(11,projet.getText());
         pst.setString(12,libelle);
         pst.setString(13,"[JC]");
          pst.setString(14,code1.getText());
           pst.setString(16,"0");
            pst.setDouble(15,CDF);
              pst.setString(17,DEVICE);
               pst.setString(18,"");
       
          
         
          pst.executeUpdate();
        
           //      JOptionPane.showMessageDialog(null,"Caisse saved");
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(null, ex);}
    

        }

}
       
    msg.setText("Transaction Saved");

}

public void printjtable(){
 MessageFormat header = new MessageFormat("Projet: "+projet.getText()+"  "+comptem.getText()+": "+nom.getText());   
 MessageFormat footer = new MessageFormat("Page{0,number,integer}"); 
 try{
jTable1.print(JTable.PrintMode.FIT_WIDTH,header,footer);
 }catch(PrinterException ex){
          JOptionPane.showMessageDialog(null, ex);    
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

        jProgressBar1 = new javax.swing.JProgressBar();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        title = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel5 = new javax.swing.JPanel();
        jDateChooser1 = new com.alee.extended.date.WebDateField();
        libele = new javax.swing.JTextField();
        jComboBox2 = new javax.swing.JComboBox<>();
        amount = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        deux = new javax.swing.JRadioButton();
        un = new javax.swing.JRadioButton();
        stru = new javax.swing.JRadioButton();
        pro = new javax.swing.JRadioButton();
        jSeparator1 = new javax.swing.JSeparator();
        jRadioButton1 = new javax.swing.JRadioButton();
        jSeparator2 = new javax.swing.JSeparator();
        tranf_app = new javax.swing.JRadioButton();
        jSeparator4 = new javax.swing.JSeparator();
        msg = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));
        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 0, true));
        jPanel1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jPanel1MouseDragged(evt);
            }
        });
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel1MousePressed(evt);
            }
        });

        jPanel2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jComboBox1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "JRN-VENTE", "JRN-VENTE-45", "JRN-ACHAT", "JRN-BANQUE", "JRN-CAISSE", "JRN-OPD" }));
        jComboBox1.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                jComboBox1PopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        title.setEditable(false);
        title.setBackground(new java.awt.Color(240, 240, 241));
        title.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        title.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_Microsoft_Excel_30px.png"))); // NOI18N
        jLabel3.setText("Export");
        jLabel3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_Print_30px.png"))); // NOI18N
        jLabel4.setText("Print");
        jLabel4.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });

        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);

        roll.setFont(new java.awt.Font("Tahoma", 0, 3)); // NOI18N
        roll.setForeground(new java.awt.Color(240, 240, 240));
        roll.setText("jLabel1");

        nom1.setFont(new java.awt.Font("Tahoma", 0, 3)); // NOI18N
        nom1.setForeground(new java.awt.Color(240, 240, 240));
        nom1.setText("jLabel1");

        substr1.setFont(new java.awt.Font("Tahoma", 0, 3)); // NOI18N
        substr1.setForeground(new java.awt.Color(240, 240, 240));
        substr1.setText("jLabel4");

        classe1.setFont(new java.awt.Font("Tahoma", 0, 3)); // NOI18N
        classe1.setForeground(new java.awt.Color(240, 240, 240));
        classe1.setText("jLabel3");

        codem1.setFont(new java.awt.Font("Tahoma", 0, 3)); // NOI18N
        codem1.setForeground(new java.awt.Color(240, 240, 240));
        codem1.setText("jLabel2");

        comptem1.setFont(new java.awt.Font("Tahoma", 0, 3)); // NOI18N
        comptem1.setForeground(new java.awt.Color(240, 240, 240));
        comptem1.setText("jLabel1");

        nom.setFont(new java.awt.Font("Tahoma", 0, 3)); // NOI18N
        nom.setForeground(new java.awt.Color(240, 240, 240));
        nom.setText("jLabel1");

        substr.setFont(new java.awt.Font("Tahoma", 0, 3)); // NOI18N
        substr.setForeground(new java.awt.Color(240, 240, 240));
        substr.setText("jLabel4");

        classe.setFont(new java.awt.Font("Tahoma", 0, 3)); // NOI18N
        classe.setForeground(new java.awt.Color(240, 240, 240));
        classe.setText("jLabel3");

        codem.setFont(new java.awt.Font("Tahoma", 0, 3)); // NOI18N
        codem.setForeground(new java.awt.Color(240, 240, 240));
        codem.setText("jLabel2");

        comptem.setFont(new java.awt.Font("Tahoma", 0, 3)); // NOI18N
        comptem.setForeground(new java.awt.Color(240, 240, 240));
        comptem.setText("jLabel1");

        code_cat.setFont(new java.awt.Font("Tahoma", 0, 3)); // NOI18N
        code_cat.setForeground(new java.awt.Color(240, 240, 240));
        code_cat.setText("jLabel1");

        code_subcat.setFont(new java.awt.Font("Tahoma", 0, 3)); // NOI18N
        code_subcat.setForeground(new java.awt.Color(240, 240, 240));
        code_subcat.setText("jLabel1");

        catb.setFont(new java.awt.Font("Tahoma", 0, 3)); // NOI18N
        catb.setForeground(new java.awt.Color(240, 240, 240));
        catb.setText("jLabel1");

        sub_catb.setFont(new java.awt.Font("Tahoma", 0, 3)); // NOI18N
        sub_catb.setForeground(new java.awt.Color(240, 240, 240));
        sub_catb.setText("jLabel1");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_Refresh_30px.png"))); // NOI18N
        jLabel5.setText("Recherche");
        jLabel5.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_cancel_24px_1.png"))); // NOI18N
        jLabel6.setText("Delete");
        jLabel6.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/saves.jpg"))); // NOI18N
        jLabel7.setText("Save ");
        jLabel7.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(comptem)
                        .addGap(28, 28, 28)
                        .addComponent(codem)
                        .addGap(28, 28, 28)
                        .addComponent(classe)
                        .addGap(28, 28, 28)
                        .addComponent(substr)
                        .addGap(28, 28, 28)
                        .addComponent(nom)
                        .addGap(28, 28, 28)
                        .addComponent(comptem1)
                        .addGap(28, 28, 28)
                        .addComponent(codem1)
                        .addGap(28, 28, 28)
                        .addComponent(classe1)
                        .addGap(28, 28, 28)
                        .addComponent(substr1)
                        .addGap(28, 28, 28)
                        .addComponent(nom1)
                        .addGap(38, 38, 38)
                        .addComponent(roll)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(code_cat)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(code_subcat)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(catb)
                        .addGap(72, 72, 72)
                        .addComponent(sub_catb)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(title))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6))
                .addGap(0, 0, 0)
                .addComponent(jLabel5)
                .addGap(0, 0, 0)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(comptem, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(codem, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(classe, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(substr, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nom, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comptem1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(codem1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(classe1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(substr1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nom1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(roll, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(code_cat, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(code_subcat, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(catb, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(sub_catb, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))))
            .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel6)
                .addGap(0, 0, 0)
                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE))
        );

        jPanel4.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable1.setFocusable(false);
        jTable1.setIntercellSpacing(new java.awt.Dimension(0, 0));
        jTable1.setRowHeight(24);
        jTable1.setShowVerticalLines(false);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 489, Short.MAX_VALUE)
        );

        jPanel5.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jDateChooser1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jDateChooser1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jDateChooser1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jDateChooser1ActionPerformed(evt);
            }
        });
        jPanel5.add(jDateChooser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 109, 28));

        code1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        code1.setText("Compte principal");
        code1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                code1MouseClicked(evt);
            }
        });
        code1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                code1KeyReleased(evt);
            }
        });
        jPanel5.add(code1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 10, 100, 28));

        code2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        code2.setText("Compte secondaire");
        code2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                code2MouseClicked(evt);
            }
        });
        code2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                code2ActionPerformed(evt);
            }
        });
        code2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                code2KeyReleased(evt);
            }
        });
        jPanel5.add(code2, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 10, 110, 28));

        libele.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        libele.setText("Libelle");
        libele.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                libeleMouseClicked(evt);
            }
        });
        jPanel5.add(libele, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 10, 730, 28));

        jComboBox2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Debit", "Credit" }));
        jPanel5.add(jComboBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 10, 60, 28));

        jComboBox3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "USD", "CDF" }));
        jPanel5.add(jComboBox3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 10, 60, 28));

        amount.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        amount.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        amount.setText("0.00");
        jPanel5.add(amount, new org.netbeans.lib.awtextra.AbsoluteConstraints(1180, 10, 140, 28));

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton1.setText("Save");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1180, 40, 140, 30));

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton2.setText("New");
        jPanel5.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 40, 60, 30));

        lbs.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lbs.setText("LB");
        jPanel5.add(lbs, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 1110, 40));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Ecriture comptable");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("X");
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });

        jPanel3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        deux.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        deux.setSelected(true);
        deux.setText("Deux ecritures");

        un.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        un.setText("Une ecriture");

        projet.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        projet.setText("Structure/Projet");
        projet.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                projetMouseClicked(evt);
            }
        });
        projet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                projetActionPerformed(evt);
            }
        });
        projet.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                projetKeyReleased(evt);
            }
        });

        stru.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        stru.setText("Structure");
        stru.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                struMouseClicked(evt);
            }
        });

        pro.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        pro.setText("Projet");
        pro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                proMouseClicked(evt);
            }
        });
        pro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                proActionPerformed(evt);
            }
        });

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jRadioButton1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jRadioButton1.setSelected(true);
        jRadioButton1.setText("Concern");
        jRadioButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRadioButton1MouseClicked(evt);
            }
        });

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        lb.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lb.setText("LB");
        lb.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                lbKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                lbKeyTyped(evt);
            }
        });

        tranf_app.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        tranf_app.setText("Tranfers/App");

        jSeparator4.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jComboBox4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Batchs" }));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jRadioButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(tranf_app)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(deux, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(un)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(stru)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pro)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(projet, javax.swing.GroupLayout.PREFERRED_SIZE, 471, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jComboBox4, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(lb, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator2)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(projet, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jRadioButton1)
                        .addComponent(lb, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(deux)
                        .addComponent(un)
                        .addComponent(stru)
                        .addComponent(pro)
                        .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator1)
                    .addComponent(jSeparator4)
                    .addComponent(tranf_app, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        msg.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        msg.setForeground(new java.awt.Color(255, 255, 255));
        msg.setText("Message");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(msg, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 1340, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(msg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    private void jDateChooser1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jDateChooser1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jDateChooser1ActionPerformed

    private void jComboBox1PopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_jComboBox1PopupMenuWillBecomeInvisible
change(); 
  msg.setText("Message");// TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1PopupMenuWillBecomeInvisible

    private void code1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_code1KeyReleased
// compte m=new compte();
// m.attLIST_from_EMPLOYEView_used();
compte();
// TODO add your handling code here:
    }//GEN-LAST:event_code1KeyReleased

    private void code1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_code1MouseClicked
if(code1.getText().equals("Compte principal")){
code1.setText("");
}        // TODO add your handling code here:
    }//GEN-LAST:event_code1MouseClicked

    private void code2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_code2KeyReleased
compte1();        // TODO add your handling code here:
    }//GEN-LAST:event_code2KeyReleased

    private void code2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_code2MouseClicked
if(code2.getText().equals("Compte secondaire")){
code2.setText("");
}         // TODO add your handling code here:
    }//GEN-LAST:event_code2MouseClicked

    private void code2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_code2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_code2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
if(projet.getText().equals("Structure/Projet")){
JOptionPane.showMessageDialog(null,"Wrong Data","Error",JOptionPane.ERROR_MESSAGE);
}else{
 
        if(stru.isSelected()){
saveohadatrans();
}else{  
saveohadatranspro();
}
        lb.setText("LB");
        lbs.setText("LB");
        code2.setText("Compte secondaire");
        libele.setText("Libelle");
        amount.setText("0.00");
       
}// TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void libeleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_libeleMouseClicked

if(libele.getText().equals("Libelle")){
libele.setText("");   }     // TODO add your handling code here:
    }//GEN-LAST:event_libeleMouseClicked

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
     //   tableselectedcompte();
     //   this.dispose();//   select_jTable();       // TODO add your handling code here:
    }//GEN-LAST:event_jTable1MouseClicked

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel2MouseClicked

    private void projetMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_projetMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_projetMouseClicked

    private void projetKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_projetKeyReleased
compte_pro m = new compte_pro();
                     m.show();
        m. setDefaultCloseOperation(DISPOSE_ON_CLOSE);         // TODO add your handling code here:
    }//GEN-LAST:event_projetKeyReleased

    private void struMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_struMouseClicked
 projet.setEditable(false);
 projet.setText("Structure");
 lb.setEditable(false);// TODO add your handling code here:
    }//GEN-LAST:event_struMouseClicked

    private void proActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_proActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_proActionPerformed

    private void proMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_proMouseClicked
projet.setEditable(true);
 lb.setEditable(true);
 projet.setText("Projet");        // TODO add your handling code here:
    }//GEN-LAST:event_proMouseClicked

    private void jRadioButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRadioButton1MouseClicked
  if(jRadioButton1.isSelected()){
   show_in_table_concern();
  }else{
  show_in_table();
  }
  msg.setText("Message");
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton1MouseClicked

    private void lbKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lbKeyReleased
compte_lb m = new compte_lb();
                     m.show();
        m. setDefaultCloseOperation(DISPOSE_ON_CLOSE);         // TODO add your handling code here:
    }//GEN-LAST:event_lbKeyReleased

    private void jPanel1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MousePressed
        x= evt.getX();
        y= evt.getY();//        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel1MousePressed

    private void jPanel1MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseDragged
  int xx= evt.getXOnScreen();
        int yy= evt.getYOnScreen();
        this.setLocation(xx-x,yy-y);        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel1MouseDragged

    private void projetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_projetActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_projetActionPerformed

    private void lbKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lbKeyTyped
       char c=evt.getKeyChar();
        if(!(Character.isDigit(c) || (c==KeyEvent.VK_BACK_SPACE)||c==KeyEvent.VK_DELETE ||c==KeyEvent.VK_PERIOD )){
            evt.consume();

        }           // TODO add your handling code here:
    }//GEN-LAST:event_lbKeyTyped

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
search m = new search();
m.show();
m. setDefaultCloseOperation(DISPOSE_ON_CLOSE);         // TODO add your handling code here:
    }//GEN-LAST:event_jLabel5MouseClicked

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
msg.setText("Message");
        save_Contract();        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel6MouseClicked

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
msg.setText("Message");
        if(tranf_app.isSelected()){
    transfertcaisse();
        
    }else{
        if(jComboBox3.getSelectedItem().equals("USD")){
saveusd();
}else{
        saveusdcdf();
        }
   
   }
tranf_app.setSelected(false);// TODO add your handling code here:
    }//GEN-LAST:event_jLabel7MouseClicked

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
printjtable();        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel4MouseClicked

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
            java.util.logging.Logger.getLogger(journal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(journal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(journal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(journal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                 WebLookAndFeel.install(true);
                new journal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField amount;
    public static final javax.swing.JLabel catb = new javax.swing.JLabel();
    public static final javax.swing.JLabel classe = new javax.swing.JLabel();
    public static final javax.swing.JLabel classe1 = new javax.swing.JLabel();
    public static final javax.swing.JTextField code1 = new javax.swing.JTextField();
    public static final javax.swing.JTextField code2 = new javax.swing.JTextField();
    public static final javax.swing.JLabel code_cat = new javax.swing.JLabel();
    public static final javax.swing.JLabel code_subcat = new javax.swing.JLabel();
    public static final javax.swing.JLabel codem = new javax.swing.JLabel();
    public static final javax.swing.JLabel codem1 = new javax.swing.JLabel();
    public static final javax.swing.JLabel comptem = new javax.swing.JLabel();
    public static final javax.swing.JLabel comptem1 = new javax.swing.JLabel();
    private javax.swing.JRadioButton deux;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    public static final javax.swing.JComboBox<String> jComboBox1 = new javax.swing.JComboBox<>();
    private javax.swing.JComboBox<String> jComboBox2;
    public static final javax.swing.JComboBox<String> jComboBox3 = new javax.swing.JComboBox<>();
    public static final javax.swing.JComboBox<String> jComboBox4 = new javax.swing.JComboBox<>();
    private com.alee.extended.date.WebDateField jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    public static final javax.swing.JTable jTable1 = new javax.swing.JTable();
    public static final javax.swing.JTextField lb = new javax.swing.JTextField();
    public static final javax.swing.JLabel lbs = new javax.swing.JLabel();
    private javax.swing.JTextField libele;
    private javax.swing.JLabel msg;
    public static final javax.swing.JLabel nom = new javax.swing.JLabel();
    public static final javax.swing.JLabel nom1 = new javax.swing.JLabel();
    private javax.swing.JRadioButton pro;
    public static final javax.swing.JTextField projet = new javax.swing.JTextField();
    public static final javax.swing.JLabel roll = new javax.swing.JLabel();
    private javax.swing.JRadioButton stru;
    public static final javax.swing.JLabel sub_catb = new javax.swing.JLabel();
    public static final javax.swing.JLabel substr = new javax.swing.JLabel();
    public static final javax.swing.JLabel substr1 = new javax.swing.JLabel();
    private javax.swing.JTextField title;
    private javax.swing.JRadioButton tranf_app;
    private javax.swing.JRadioButton un;
    // End of variables declaration//GEN-END:variables
}
