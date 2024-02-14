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
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author DOSHE
 */
public class journal_achat extends javax.swing.JInternalFrame {
 Connection con=null;
PreparedStatement pst=null;
ResultSet rs= null;
 DefaultTableModel mode;
 String rolls,rollss;
 String CLASSE,CLASSES,SUBSTR,substr;
    public journal_achat() {
        initComponents();
              con=JavaDbConnect.dbConnect();
        call_combo();
        combobusness();
         Groupe1();
         attCall_IN_LIST();
         search2BUSs();
         jDateChooser5.setDate(new Date());
    }
   
                
              public void Groupe1(){
ButtonGroup bg1 = new ButtonGroup();
bg1.add(project);
bg1.add(business);
     }
 public void call_combo(){
    String tmp="Fournisseur";
     try{
            String sqls="select NAME from  ohada where  SUBSTR='40' order by NAME";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
            while(rs.next()){
                String add1 = rs.getString("NAME");
              jComboBox6.addItem(add1);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
    
    }
    
     public void call_combo_compte_client(){
         
    String tmp=jComboBox6.getSelectedItem().toString();
     try{
         String sqls="select * from  ohada where NAME= '"+tmp+"' and SUBSTR='40'";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
            while(rs.next()){
               String ad1 = rs.getString("NAME");
              cnom1.setText(ad1);     
               String add1 = rs.getString("CODE");
              code1.setText(add1);
              // compte.setText(add1);
               String add2 = rs.getString("COMPTEMERE");
               comptemere1.setText(add2);
               String add3 = rs.getString("SUBSTR");
              codemere1.setText(add3);
              CLASSE = rs.getString("CLASS");
              SUBSTR = rs.getString("SUBSTR");
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
    jTable1.setModel(new DefaultTableModel());
     }
      public void combobusness(){
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
    
      public void attCall_IN_LIST()
    {
        DefaultListModel list = new DefaultListModel();
        
         
        try{
            String sql="select * from ohada";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
               // String sum=rs.getString("NAME");
                String sums=rs.getString("CODE");
                 list.addElement(sums);
                
                 jList4.setModel(list);
               //  jList3.setModel(list);
                // jList5.setModel(list);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
    
    }
       
          public void JLIST_from_COMPTE()
    {
    
     {
    
      String tmp =(String)jList4.getSelectedValue();
  String sql="select * from ohada where CODE='"+tmp+"'";
     
        try{
         
          
            pst=con.prepareStatement(sql);
            //pst.setString(1, tmp);
            rs=pst.executeQuery();
            if(rs.next()){
                 
                String ad1 = rs.getString("NAME");
              cnom.setText(ad1);     
               String add1 = rs.getString("CODE");
              code.setText(add1);
              String add2 = rs.getString("COMPTEMERE");
               comptemere.setText(add2);
               String add3 = rs.getString("CODEMERE");
              codemere.setText(add3);
              
             CLASSES = rs.getString("CLASS");
               
           substr= rs.getString("SUBSTR");
             // codemere.setText(add3);
          }
  
      }catch(Exception ex){
        JOptionPane.showMessageDialog(null,ex);  
      }
          
          
  
       
      }
        
       
       
       
    } 
           
      
            
            
      
         public void attCall_LIST()
    {
        DefaultListModel list = new DefaultListModel();
        
         
        try{
            String sql="select * from ohada where CODE LIKE '"+chearch_ohada1.getText()+"%'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
               // String sum=rs.getString("NAME");
                String sums=rs.getString("CODE");
                 list.addElement(sums);
                
                 jList4.setModel(list);
               //  jList3.setModel(list);
                // jList5.setModel(list);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
    }
         
          public void moiss(){
 
}
          public void etroll()
     {
         try{
                  SimpleDateFormat dateFormatsS = new SimpleDateFormat("yyyy-MM-dd");
         String addDateS = dateFormatsS.format(jDateChooser4.getDate());
         
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
     public void call_num(){
   
        try{
            String sqls="select NUM_FACTURE,NUM from  ohada_trans WHERE NUM_FACTURE='"+num_fact.getText()+"'";////where NUM_FACTURE = '"+num_fact.getText()+"'";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
            while(rs.next()){
                String add1 = rs.getString("NUM_FACTURE");
             compte.setText(add1);
             String add2 = rs.getString("NUM");
             num.setText(add2);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
         
     }
     
     String Check=null;
       public void check(){
           Check=null;   
               
          try{
          
          String sql="select NUM_FACTURE from  ohada_trans WHERE NUM_FACTURE='"+num_fact.getText()+"'";//,MATR_NAME='"+b+"'";
          pst=con.prepareStatement(sql);
           rs=pst.executeQuery();
          while(rs.next()){
             Check = rs.getString("NUM_FACTURE");
            
           
          }
      }catch(Exception ex){
      JOptionPane.showMessageDialog(null,ex);
      }  }
       
       
       
        Double USD,CDF,USDD;
           public void call_currency(){
      Double PR = Double.parseDouble(ptf.getText());
Double cdf = null,usd = null;
  try{
                         String sql="SELECT * FROM  currency";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
             cdf=rs.getDouble("ACDF");
             usd=rs.getDouble("VUSD"); 
             USDD=usd;
            }
          
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
  if(jComboBox3.getSelectedItem().equals("CDF")){
  USD=PR/cdf;
  CDF=PR;
  
  }else if(jComboBox3.getSelectedItem().equals("USD")){
      CDF=PR*usd;
  USD=PR;
  }
      
  
 }     
     public void save_facture_fournisseur(){
        etroll(); 
        if(substr.equals("44")){
         if(jComboBox2.getSelectedItem().equals("CREDIT")){
            try {
        PreparedStatement pst = con.prepareStatement("INSERT INTO `ohada_trans`(`COMPTE_M`, `COMPTE`, `CODE_M`, `CODE`, `CLASSE`, `SUBSTR`, `DEBIT`, `CREDIT`, `NUM`, `DATES`, `BUSS`, `LIBELLE`, `JOURNAL`,`CODE1`,`NUM_FACTURE`,`DEVICE`)"
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        
        pst.setString(1, comptemere1.getText());
        pst.setString(2, cnom1.getText());
         pst.setString(3, codemere1.getText());
         pst.setString(4, code1.getText());
         pst.setString(5, CLASSE);
         pst.setString(6, SUBSTR);
         pst.setString(7,ptf.getText());
         pst.setString(8,"0");
         pst.setString(9,rolls);
         
       SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(jDateChooser5.getDate());
         pst.setString(10, addDate);
         pst.setString(11,buss.getSelectedItem().toString());
         pst.setString(12,desc.getText());
         pst.setString(13,"[JV]");
         pst.setString(14,code.getText());
         pst.setString(15,num_fact.getText());
         pst.setString(16,jComboBox3.getSelectedItem().toString());
          pst.executeUpdate();
           //   JOptionPane.showMessageDialog(null,"Caisse saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
         ///=====>>>>CREDIT 40
          try {
         String tmp =jComboBox3.getSelectedItem().toString();
         //Connection con = getConnection();
           PreparedStatement pst = con.prepareStatement("INSERT INTO `ohada_trans`(`COMPTE_M`, `COMPTE`, `CODE_M`, `CODE`, `CLASSE`, `SUBSTR`, `DEBIT`, `CREDIT`, `NUM`, `DATES`, `BUSS`, `LIBELLE`, `JOURNAL`,`CODE1`,`NUM_FACTURE`,`DEVICE`)"
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        
        pst.setString(1, comptemere.getText());
        pst.setString(2, cnom.getText());
         pst.setString(3, codemere.getText());
         pst.setString(4, code.getText());
         pst.setString(5, CLASSES);
         pst.setString(6, substr);
         pst.setString(8,ptf.getText());
         pst.setString(7,"0");
         pst.setString(9,rolls);
         
       SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(jDateChooser5.getDate());
         pst.setString(10, addDate);
         pst.setString(11,buss.getSelectedItem().toString());
         pst.setString(12,desc.getText());
         pst.setString(13,"[JV]");
         pst.setString(14,code1.getText());
         pst.setString(15,num_fact.getText());
          pst.setString(16,jComboBox3.getSelectedItem().toString());
       
          
         
          pst.executeUpdate();
        
               JOptionPane.showMessageDialog(null,"Transaction saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
      call_num(); 
     }else{
           
   try {
         String tmp =jComboBox3.getSelectedItem().toString();
       //  Connection con = getConnection();
           PreparedStatement pst = con.prepareStatement("INSERT INTO `ohada_trans`(`COMPTE_M`, `COMPTE`, `CODE_M`, `CODE`, `CLASSE`, `SUBSTR`, `DEBIT`, `CREDIT`, `NUM`, `DATES`, `BUSS`, `LIBELLE`, `JOURNAL`,`CODE1`,`NUM_FACTURE`,`DEVICE`)"
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        
        pst.setString(1, comptemere1.getText());
        pst.setString(2, cnom1.getText());
         pst.setString(3, codemere1.getText());
         pst.setString(4, code1.getText());
         pst.setString(5, CLASSE);
         pst.setString(6, SUBSTR);
         pst.setString(8,ptf.getText());
         pst.setString(7,"0");
         pst.setString(9,rolls);
         
       SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(jDateChooser5.getDate());
         pst.setString(10, addDate);
         pst.setString(11,buss.getSelectedItem().toString());
         pst.setString(12,desc.getText());
         pst.setString(13,"[JV]");
      pst.setString(14,code.getText());
         pst.setString(15,num_fact.getText());
          pst.setString(16,jComboBox3.getSelectedItem().toString());
       
          
         
          pst.executeUpdate();
        
           //      JOptionPane.showMessageDialog(null,"Caisse saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
         
          try {
         String tmp =jComboBox3.getSelectedItem().toString();
     //    Connection con = getConnection();
         PreparedStatement pst = con.prepareStatement("INSERT INTO `ohada_trans`(`COMPTE_M`, `COMPTE`, `CODE_M`, `CODE`, `CLASSE`, `SUBSTR`, `DEBIT`, `CREDIT`, `NUM`, `DATES`, `BUSS`, `LIBELLE`, `JOURNAL`,`CODE1`,`NUM_FACTURE`,`DEVICE`)"
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        
        pst.setString(1, comptemere.getText());
        pst.setString(2, cnom.getText());
         pst.setString(3, codemere.getText());
         pst.setString(4, code.getText());
         pst.setString(5, CLASSES);
         pst.setString(6, substr);
         pst.setString(7,ptf.getText());
         pst.setString(8,"0");
         pst.setString(9,rolls);
         
       SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(jDateChooser5.getDate());
         pst.setString(10, addDate);
         pst.setString(11,buss.getSelectedItem().toString());
         pst.setString(12,desc.getText());
         pst.setString(13,"[JV]");
         pst.setString(14,code1.getText());
         pst.setString(15,num_fact.getText());
          pst.setString(16,jComboBox3.getSelectedItem().toString());
       
          
         
          pst.executeUpdate();
        
                JOptionPane.showMessageDialog(null,"Transaction saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
          call_num(); 
        }
        }else{
         if(jComboBox2.getSelectedItem().equals("CREDIT")){
            try {
       PreparedStatement pst = con.prepareStatement("INSERT INTO `ohada_trans`(`COMPTE_M`, `COMPTE`, `CODE_M`, `CODE`, `CLASSE`, `SUBSTR`, `DEBIT`, `CREDIT`, `NUM`, `DATES`, `BUSS`, `LIBELLE`, `JOURNAL`,`CODE1`,`NUM_FACTURE`,`DEVICE`)"
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        
        pst.setString(1, comptemere1.getText());
        pst.setString(2, cnom1.getText());
         pst.setString(3, codemere1.getText());
         pst.setString(4, code1.getText());
         pst.setString(5, CLASSE);
         pst.setString(6, SUBSTR);
         pst.setString(7,ptf.getText());
         pst.setString(8,"0");
         pst.setString(9,rolls);
         
       SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(jDateChooser5.getDate());
         pst.setString(10, addDate);
         pst.setString(11,buss.getSelectedItem().toString());
         pst.setString(12,desc.getText());
         pst.setString(13,"[JV]");
         pst.setString(14,code.getText());
         pst.setString(15,num_fact.getText());
          pst.setString(16,jComboBox3.getSelectedItem().toString());
          pst.executeUpdate();
              JOptionPane.showMessageDialog(null,"Caisse saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
         ///=====>>>>CREDIT 40
          try {
         String tmp =jComboBox3.getSelectedItem().toString();
         //Connection con = getConnection();
        PreparedStatement pst = con.prepareStatement("INSERT INTO `ohada_trans`(`COMPTE_M`, `COMPTE`, `CODE_M`, `CODE`, `CLASSE`, `SUBSTR`, `DEBIT`, `CREDIT`, `NUM`, `DATES`, `BUSS`, `LIBELLE`, `JOURNAL`,`CODE1`,`NUM_FACTURE`,`DEVICE`)"
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        
        pst.setString(1, comptemere.getText());
        pst.setString(2, cnom.getText());
         pst.setString(3, codemere.getText());
         pst.setString(4, code.getText());
         pst.setString(5, CLASSES);
         pst.setString(6, substr);
         pst.setString(8,ptf.getText());
         pst.setString(7,"0");
         pst.setString(9,rolls);
         
       SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(jDateChooser5.getDate());
         pst.setString(10, addDate);
         pst.setString(11,buss.getSelectedItem().toString());
         pst.setString(12,desc.getText());
         pst.setString(13,"[JV]");
         pst.setString(14,code1.getText());
         pst.setString(15,num_fact.getText());
          pst.setString(16,jComboBox3.getSelectedItem().toString());
       
          
         
          pst.executeUpdate();
        
               JOptionPane.showMessageDialog(null,"Transaction saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
      call_num(); 
     }else{
           
   try {
         String tmp =jComboBox3.getSelectedItem().toString();
       //  Connection con = getConnection();
    PreparedStatement pst = con.prepareStatement("INSERT INTO `ohada_trans`(`COMPTE_M`, `COMPTE`, `CODE_M`, `CODE`, `CLASSE`, `SUBSTR`, `DEBIT`, `CREDIT`, `NUM`, `DATES`, `BUSS`, `LIBELLE`, `JOURNAL`,`CODE1`,`NUM_FACTURE`,`DEVICE`)"
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        
        pst.setString(1, comptemere1.getText());
        pst.setString(2, cnom1.getText());
         pst.setString(3, codemere1.getText());
         pst.setString(4, code1.getText());
         pst.setString(5, CLASSE);
         pst.setString(6, SUBSTR);
         pst.setString(8,ptf.getText());
         pst.setString(7,"0");
         pst.setString(9,rolls);
         
       SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(jDateChooser5.getDate());
         pst.setString(10, addDate);
         pst.setString(11,buss.getSelectedItem().toString());
         pst.setString(12,desc.getText());
         pst.setString(13,"[JV]");
      pst.setString(14,code.getText());
         pst.setString(15,num_fact.getText());
          pst.setString(16,jComboBox3.getSelectedItem().toString());
       
          
         
          pst.executeUpdate();
        
           //      JOptionPane.showMessageDialog(null,"Caisse saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
         
          try {
         String tmp =jComboBox3.getSelectedItem().toString();
     //    Connection con = getConnection();
         PreparedStatement pst = con.prepareStatement("INSERT INTO `ohada_trans`(`COMPTE_M`, `COMPTE`, `CODE_M`, `CODE`, `CLASSE`, `SUBSTR`, `DEBIT`, `CREDIT`, `NUM`, `DATES`, `BUSS`, `LIBELLE`, `JOURNAL`,`CODE1`,`NUM_FACTURE`,`DEVICE`)"
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        
        pst.setString(1, comptemere.getText());
        pst.setString(2, cnom.getText());
         pst.setString(3, codemere.getText());
         pst.setString(4, code.getText());
         pst.setString(5, CLASSES);
         pst.setString(6, substr);
         pst.setString(7,ptf.getText());
         pst.setString(8,"0");
         pst.setString(9,rolls);
         
       SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(jDateChooser5.getDate());
         pst.setString(10, addDate);
         pst.setString(11,buss.getSelectedItem().toString());
         pst.setString(12,desc.getText());
         pst.setString(13,"[JV]");
         pst.setString(14,code1.getText());
         pst.setString(15,num_fact.getText());
          pst.setString(16,jComboBox3.getSelectedItem().toString());
       
          
         
          pst.executeUpdate();
        
                JOptionPane.showMessageDialog(null,"Transaction saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
          call_num(); 
        }
        
        }
       
     }
      
      
          public void search2BUS()
             {
             //COMPTE_M`, `COMPTE`, `CODE_M`, `CODE`, `CLASSE`, `SUBSTR`, `DEBIT`, `CREDIT`, `NUM`, `DATES`, `BUSS`, `LIBELLE`, `JOURNAL`, `CODE1` 
                 try{
               //   String st=projetid.getText().trim();
             String tmp=buss.getSelectedItem().toString();
    String sql="select NUM, `CODE` AS 'CODE',`LIBELLE`, `DEBIT`, `CREDIT`, `DATES` from ohada_trans where NUM_FACTURE= '"+compte.getText()+"'";
    //`LIBELLE`, `ENTRE`, `SORTIE`, `DATE`, `BUSS`, `USED`, `NUM`
    pst = con.prepareStatement(sql);
      rs= pst.executeQuery();
     
       jTable1.setModel(DbUtils.resultSetToTableModel(rs));
    
             }catch(Exception ex){
                 JOptionPane.showMessageDialog(null, ex);
             }
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
      
       
       col0.setPreferredWidth(100);
       col1.setPreferredWidth(20);
       col2.setPreferredWidth(370);
       col3.setPreferredWidth(20);
       col4.setPreferredWidth(20);
       col5.setPreferredWidth(50);
    //   col6.setPreferredWidth(200);
             
             }
          
          public void search2BUSs()
             {
             //COMPTE_M`, `COMPTE`, `CODE_M`, `CODE`, `CLASSE`, `SUBSTR`, `DEBIT`, `CREDIT`, `NUM`, `DATES`, `BUSS`, `LIBELLE`, `JOURNAL`, `CODE1` 
                 try{
               //   String st=projetid.getText().trim();
             String tmp=buss.getSelectedItem().toString();
    String sql="select `NUM_FACTURE` AS 'NUM', `CODE` AS 'CODE',`LIBELLE`, `DEBIT`, `CREDIT`, `DATES` from ohada_trans where JOURNAL= '[JA]' and CLASSE='6'";
    //`LIBELLE`, `ENTRE`, `SORTIE`, `DATE`, `BUSS`, `USED`, `NUM`
    pst = con.prepareStatement(sql);
      rs= pst.executeQuery();
     
       jTable1.setModel(DbUtils.resultSetToTableModel(rs));
    
             }catch(Exception ex){
                 JOptionPane.showMessageDialog(null, ex);
             }
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
      
       
       col0.setPreferredWidth(100);
       col1.setPreferredWidth(20);
       col2.setPreferredWidth(370);
       col3.setPreferredWidth(20);
       col4.setPreferredWidth(20);
       col5.setPreferredWidth(50);
    //   col6.setPreferredWidth(200);
             
             }
          
          
            public void Refresh(){
      cnom1.setText(null);
      code1.setText(null);
      codemere1.setText(null);
      comptemere1.setText(null);
      cnom.setText(null);
      code.setText(null);
      codemere.setText(null);
      comptemere.setText(null);
      
      
       num_fact.setText(null);
      jDateChooser4.setText(null);
      chearch_ohada1.setText(null);
      compte.setText(null);
      num.setText(null);
      buss.getSelectedItem().equals("Select One Project");
      
     // num_fact.setText(null);
      desc.setText(null);
     // num_fact.setText(null);
      desc.setText(null);
      
//     
      ptf.setText("0.00");
      
     // jDateChooser4.setDate(null);
     // compte.setText(null);
       jTable1.setModel(new DefaultTableModel());
      
      
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
        jPanel7 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jComboBox6 = new javax.swing.JComboBox<>();
        compte = new javax.swing.JTextField();
        num = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        cnom = new javax.swing.JTextField();
        code = new javax.swing.JTextField();
        codemere = new javax.swing.JTextField();
        comptemere = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        cnom1 = new javax.swing.JTextField();
        code1 = new javax.swing.JTextField();
        codemere1 = new javax.swing.JTextField();
        comptemere1 = new javax.swing.JTextField();
        buss = new javax.swing.JComboBox<>();
        project = new javax.swing.JRadioButton();
        business = new javax.swing.JRadioButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jDateChooser5 = new com.alee.extended.date.WebDateField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        num_fact = new javax.swing.JTextField();
        jDateChooser4 = new com.alee.extended.date.WebDateField();
        jPanel13 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jScrollPane5 = new javax.swing.JScrollPane();
        desc = new javax.swing.JEditorPane();
        Ohada1 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jList4 = new javax.swing.JList<>();
        chearch_ohada1 = new javax.swing.JTextField();
        jPanel14 = new javax.swing.JPanel();
        jComboBox3 = new javax.swing.JComboBox<>();
        ptf = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_Bulleted_List_16px.png"))); // NOI18N
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameActivated(evt);
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
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Supply", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jComboBox6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jComboBox6.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                jComboBox6PopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        compte.setEditable(false);
        compte.setBackground(new java.awt.Color(240, 240, 241));
        compte.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        compte.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        compte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                compteActionPerformed(evt);
            }
        });

        num.setEditable(false);
        num.setBackground(new java.awt.Color(240, 240, 241));
        num.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        num.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jComboBox6, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(num)
            .addComponent(compte)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(compte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(num, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ohada 2", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        cnom.setEditable(false);
        cnom.setBackground(new java.awt.Color(240, 240, 241));
        cnom.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        cnom.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        code.setEditable(false);
        code.setBackground(new java.awt.Color(240, 240, 241));
        code.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        code.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        codemere.setEditable(false);
        codemere.setBackground(new java.awt.Color(240, 240, 241));
        codemere.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        codemere.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        comptemere.setEditable(false);
        comptemere.setBackground(new java.awt.Color(240, 240, 241));
        comptemere.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        comptemere.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(cnom)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(code, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(codemere, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(comptemere)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(cnom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(code, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(codemere, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(comptemere, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ohada 1", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        cnom1.setEditable(false);
        cnom1.setBackground(new java.awt.Color(240, 240, 241));
        cnom1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        cnom1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        cnom1.setToolTipText("");

        code1.setEditable(false);
        code1.setBackground(new java.awt.Color(240, 240, 241));
        code1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        code1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        code1.setToolTipText("");

        codemere1.setEditable(false);
        codemere1.setBackground(new java.awt.Color(240, 240, 241));
        codemere1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        codemere1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        codemere1.setToolTipText("");

        comptemere1.setEditable(false);
        comptemere1.setBackground(new java.awt.Color(240, 240, 241));
        comptemere1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        comptemere1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        comptemere1.setToolTipText("");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(cnom1)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(code1, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(codemere1, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(comptemere1)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(cnom1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(code1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(codemere1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(comptemere1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

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

        project.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        project.setText("Project");
        project.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                projectActionPerformed(evt);
            }
        });

        business.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        business.setText("Business");
        business.setEnabled(false);
        business.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                businessActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(business)
                        .addGap(114, 114, 114)
                        .addComponent(project))
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(buss, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(business, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(project, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(buss, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6))
        );

        jPanel4.setBackground(new java.awt.Color(0, 0, 0));
        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("PURCHASE JOURNAL");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jDateChooser5.setEnabled(false);
        jDateChooser5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel13.setText("Receipt_No");

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel14.setText("Receipt_Date");

        num_fact.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        num_fact.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                num_factKeyReleased(evt);
            }
        });

        jDateChooser4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(num_fact)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jDateChooser4, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jDateChooser5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jDateChooser5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(num_fact, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jDateChooser4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel15.setText("Description");

        jComboBox2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Credit", "Debit" }));
        jComboBox2.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                jComboBox2PopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        jComboBox2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jComboBox2MouseClicked(evt);
            }
        });
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        desc.setBorder(null);
        desc.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jScrollPane5.setViewportView(desc);

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5)
                .addContainerGap())
        );

        Ohada1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ohada2", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jList4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jList4MouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(jList4);

        chearch_ohada1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        chearch_ohada1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        chearch_ohada1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                chearch_ohada1KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout Ohada1Layout = new javax.swing.GroupLayout(Ohada1);
        Ohada1.setLayout(Ohada1Layout);
        Ohada1Layout.setHorizontalGroup(
            Ohada1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Ohada1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Ohada1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chearch_ohada1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        Ohada1Layout.setVerticalGroup(
            Ohada1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Ohada1Layout.createSequentialGroup()
                .addComponent(chearch_ohada1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE))
        );

        jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Amount", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jComboBox3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "USD", "CDF", "USH" }));

        ptf.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ptf.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        ptf.setText("0.00");
        ptf.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ptfMouseClicked(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton2.setText("OK");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ptf)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ptf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Ohada1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Ohada1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(6, 6, 6))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTable1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
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
        jTable1.setRowHeight(24);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jMenu1.setText("X");
        jMenu1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jMenu1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu1MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu1);

        jMenu2.setText("File");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setText("New");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem1);

        jMenuBar1.add(jMenu2);

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

    private void jComboBox6PopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_jComboBox6PopupMenuWillBecomeInvisible
call_combo_compte_client();
    }//GEN-LAST:event_jComboBox6PopupMenuWillBecomeInvisible

    private void compteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_compteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_compteActionPerformed

    private void bussPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_bussPopupMenuWillBecomeInvisible
        // TODO add your handling code here:
    }//GEN-LAST:event_bussPopupMenuWillBecomeInvisible

    private void projectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_projectActionPerformed
       
    }//GEN-LAST:event_projectActionPerformed

    private void businessActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_businessActionPerformed
     
    }//GEN-LAST:event_businessActionPerformed

    private void jComboBox2PopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_jComboBox2PopupMenuWillBecomeInvisible
        //        debit_credit();        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox2PopupMenuWillBecomeInvisible

    private void jComboBox2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBox2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox2MouseClicked

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void jList4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList4MouseClicked
JLIST_from_COMPTE();     // TODO add your handling code here:
    }//GEN-LAST:event_jList4MouseClicked

    private void chearch_ohada1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_chearch_ohada1KeyReleased
attCall_LIST();         // TODO add your handling code here:
    }//GEN-LAST:event_chearch_ohada1KeyReleased

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
if(code.getText().equals("") ||code1.getText().equals("")||num_fact.getText().equals("")||desc.getText().equals("")||jDateChooser4.getText().equals("")||ptf.getText().equals("")||ptf.getText().equals("0.00")){
JOptionPane.showMessageDialog(null,"Wrong Data","Error",JOptionPane.ERROR_MESSAGE);
}else{
       save_facture_fournisseur();
        ptf.setText("0.00");
  search2BUS();
}
    
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
            // TODO add your handling code here:
    }//GEN-LAST:event_jTable1MouseClicked

    private void jMenu1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu1MouseClicked
this.dispose(); 
newdesingn.title.setText("Windows");// TODO add your handling code here:
    }//GEN-LAST:event_jMenu1MouseClicked

    private void num_factKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_num_factKeyReleased
  check();
          if(Check==null){
    }else{
          
          JOptionPane.showMessageDialog(null,"Invoice Number Exist","Error",JOptionPane.ERROR_MESSAGE);
          num_fact.setText("");
          }        // TODO add your handling code here:
    }//GEN-LAST:event_num_factKeyReleased

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
Refresh();        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void formInternalFrameActivated(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameActivated
newdesingn.title.setText("Purshase Journal / Journal d'Achat");        // TODO add your handling code here:
    }//GEN-LAST:event_formInternalFrameActivated

    private void ptfMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ptfMouseClicked
ptf.setText("");        // TODO add your handling code here:
    }//GEN-LAST:event_ptfMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Ohada1;
    private javax.swing.JRadioButton business;
    private javax.swing.JComboBox<String> buss;
    private javax.swing.JTextField chearch_ohada1;
    private javax.swing.JTextField cnom;
    private javax.swing.JTextField cnom1;
    private javax.swing.JTextField code;
    private javax.swing.JTextField code1;
    private javax.swing.JTextField codemere;
    private javax.swing.JTextField codemere1;
    private javax.swing.JTextField compte;
    private javax.swing.JTextField comptemere;
    private javax.swing.JTextField comptemere1;
    private javax.swing.JEditorPane desc;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox6;
    private com.alee.extended.date.WebDateField jDateChooser4;
    private com.alee.extended.date.WebDateField jDateChooser5;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JList<String> jList4;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField num;
    private javax.swing.JTextField num_fact;
    private javax.swing.JRadioButton project;
    private javax.swing.JTextField ptf;
    // End of variables declaration//GEN-END:variables
}
