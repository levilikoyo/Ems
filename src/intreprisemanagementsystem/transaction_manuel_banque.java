/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intreprisemanagementsystem;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Dosh
 */
public class transaction_manuel_banque extends javax.swing.JFrame {

       DefaultTableModel mode;
Connection con=null;
PreparedStatement pst=null;
ResultSet rs= null;
String rolls,SUB_CAT,CAT;
    public transaction_manuel_banque() {
        initComponents();
              con=JavaDbConnect.dbConnect();
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("icons8_Raspberry_Pi_48px_1.png")));
        autre.setEnabled(false);
       autre.setBackground(Color.LIGHT_GRAY) ;
      
      callbank();
       Groupe1();
       jDateChooser1.setDate(new Date());
        
    }
    
 
    
    public void attCall_IN_LIST()
    { 
        DefaultListModel list = new DefaultListModel();
 
        try{
          String sql="SELECT * FROM ohada where code like '"+jTextField1.getText()+"%' order by CODE";
             
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
    
    
    public void search(){
     DefaultListModel list = new DefaultListModel();
        try{
          String sql="SELECT * FROM budget_code where code like '"+jTextField2.getText()+"%'  and CAT='"+buss.getSelectedItem()+"'order by CODE";
             
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                //String sum=rs.getString("nom");
                String sums=rs.getString("code");
                 list.addElement(sums);
                
                 jList2.setModel(list);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
    
    }
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
                codem.setText(sums1);
                
                String sums2=rs.getString("CODE");
                codes.setText(sums2);
                
                 String sum3=rs.getString("COMPTEMERE");
                 comptemere.setText(sum3);
                 String sum4=rs.getString("CLASS");
                classs.setText(sum4);
                
                 
                
                
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
    }
    
     public void call_budget(){
  String tmp =(String)jList2.getSelectedValue();
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
               //String add = rs.getString("CAT");
              code_cat.setText("LB");
          }
  
      }catch(Exception ex){
        JOptionPane.showMessageDialog(null,ex);  
      }
        
          try{
          String sql="SELECT LB,LBSUB,SUB_CAT,CAT FROM  budget WHERE code='"+tmp+"' and PROJECT='"+buss.getSelectedItem()+"'";
          
            pst=con.prepareStatement(sql);
            //pst.setString(1, tmp);
            rs=pst.executeQuery();
            if(rs.next()){
                 
                String ad1 = rs.getString("LB");
             code_cat.setText(ad1);     
               String add1 = rs.getString("LBSUB");
             code_subcat.setText(add1);
             	SUB_CAT=rs.getString("SUB_CAT");
                CAT=rs.getString("CAT");
             // here now
          }
  
      }catch(Exception ex){
        JOptionPane.showMessageDialog(null,ex);  
      }
 }
    
     
     
     String NAME,CODE,COMPTEMERE,CODEMERE,CLASS;
      public void SELECT_buss()
    {
       
        
         //`NAME`, `CODE`, `COMPTEMERE`, `CODEMERE`, `CLASS`
        try{
             
            String sql="SELECT * FROM   ohada where NAME='"+bank.getSelectedItem()+"'";
          
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
     
     
     public void callbank(){
    
    try{
            String sql="select * from bank";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                String sum=rs.getString("REF");
                  bank.addItem(sum);
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
       DefaultListModel list = new DefaultListModel();
 
        try{
          String sql="SELECT * FROM ohada order by CODE";
             
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
    
  
    
   
      public void Groupe1(){
ButtonGroup bg1 = new ButtonGroup();
bg1.add(debit);
bg1.add(credit);
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
       
       
      
      // Double  = double.parseDouble(amount.getText());
     public void newsave(){
       //  etroll();
          Double aaaa= Double.parseDouble(amount.getText());
          Double caissesdebit = null;
         etroll();
     if (debit.isSelected()){
          try{  
             Double xxx;
Double yyy;


//call from journala de banque  
            String sql="select SUM(DEBIT),SUM(CREDIT) from ohada_trans where code='"+codes.getText()+"'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                xxx=rs.getDouble("SUM(DEBIT)");
               // entre.setText(""+xxx);
                yyy=rs.getDouble("SUM(CREDIT)");
                  
                   caissesdebit=(xxx-yyy);
                 
            }
            }catch(Exception ex){ JOptionPane.showMessageDialog(null, ex);}
            
            if(caissesdebit < aaaa){
            JOptionPane.showMessageDialog(null,"Wrong Data"+"\n"+"No Enough Resources","Error",JOptionPane.ERROR_MESSAGE);
            }else{
             try {
        // String tmp =jComboBox3.getSelectedItem().toString();
           
        PreparedStatement pst = con.prepareStatement("INSERT INTO `ohada_trans`(`COMPTE_M`, `COMPTE`, `CODE_M`, `CODE`, `CLASSE`, `SUBSTR`, `DEBIT`, `CREDIT`, `NUM`, `DATES`, `BUSS`, `LIBELLE`, `JOURNAL`,`CODE1`,NUM_FACTURE,device,LB)"
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        //NAME`, `CODE`, `COMPTEMERE`, `CODEMERE`, `CLASS`
        pst.setString(1, COMPTEMERE);
        pst.setString(2, NAME);
         pst.setString(3, CODEMERE);
         pst.setString(4, CODE);
         pst.setString(5, CLASS);
         pst.setString(6, "[JB]");
         pst.setString(7,amount.getText());
         pst.setString(8,"0");
         pst.setString(9,rolls);
     
       SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
       String tmps = dateFormats.format(jDateChooser1.getDate());
        pst.setString(10,tmps);
         pst.setString(11,buss.getSelectedItem().toString());
         pst.setString(12,desc.getText());
         pst.setString(13,"[JC]");
         pst.setString(14,codes.getText());
          pst.setString(15,num_fact.getSelectedItem().toString());
           pst.setString(16,jComboBox1.getSelectedItem().toString());
           pst.setString(17,codeb.getText());
       
          
         
          pst.executeUpdate();
        
           //      JOptionPane.showMessageDialog(null,"Caisse saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
      ///>>>>>52
        try {
        // String tmp =jComboBox3.getSelectedItem().toString();
         
        PreparedStatement pst = con.prepareStatement("INSERT INTO `ohada_trans`(`COMPTE_M`, `COMPTE`, `CODE_M`, `CODE`, `CLASSE`, `SUBSTR`, `DEBIT`, `CREDIT`, `NUM`, `DATES`, `BUSS`, `LIBELLE`, `JOURNAL`,`CODE1`,NUM_FACTURE,device,LB)"
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        
        pst.setString(1, comptemere.getText());
        pst.setString(2, compte.getText());
         pst.setString(3, codem.getText());
         pst.setString(4, codes.getText());
         pst.setString(5, classs.getText());
         pst.setString(6, "[JCB]");
         pst.setString(7,"0");
         pst.setString(8,amount.getText());
         pst.setString(9,rolls);
         
          SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
       String tmps = dateFormats.format(jDateChooser1.getDate());
        pst.setString(10,tmps);
         pst.setString(11,buss.getSelectedItem().toString());
         pst.setString(12,desc.getText());
         pst.setString(13,"[JB]");
         pst.setString(14,CODE);
          pst.setString(15,num_fact.getSelectedItem().toString());
           pst.setString(16,jComboBox1.getSelectedItem().toString());
           pst.setString(17,codeb.getText());
       
          
         
          pst.executeUpdate();
        
               //  JOptionPane.showMessageDialog(null,"Caisse saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
     
            }
     
//      try {
//        PreparedStatement pst = con.prepareStatement("UPDATE `ohada_trans` SET PAY=? WHERE NUM_FACTURE='"+num_fact.getSelectedItem()+"'");
//        pst.setString(1,"Pay");
//          pst.executeUpdate();
//    } catch (Exception ex) {
//         JOptionPane.showMessageDialog(null, ex.getMessage());
//    }
     
                //  int row= jTable1.getSelectedRow();
          String Table_click = num_fact.getSelectedItem(). toString();
          
         try{
          String sql = "SELECT SUM(DEBIT),SUM(`CREDIT`) FROM  ohada_trans WHERE `NUM_FACTURE` = '"+Table_click+"' AND BUSS='"+buss.getSelectedItem()+"' and CLASSE=4";
          pst = con.prepareStatement(sql);
          rs=pst.executeQuery();
          if(rs.next()){
              Double cc;
            Double  sum0=rs.getDouble("SUM(DEBIT)");
               // debit.setText(sum0);
              Double  sum1=rs.getDouble("SUM(`CREDIT`)");
                // credit.setText(sum);
                
                
                
                 cc=(sum0-sum1);
                 num_fact2.setText(""+cc);
                 
           if(num_fact2.getText().equals("0.0")){
             try {
        PreparedStatement pst = con.prepareStatement("UPDATE `ohada_trans` SET PAY=? WHERE NUM_FACTURE='"+Table_click+"'");
        pst.setString(1,"Pay");
          pst.executeUpdate();
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
           }else{
           
           }
                  
          }
         
    }catch(SQLException ex ){
         JOptionPane.showMessageDialog(null,ex);
     } 
     }else{
         if(btwn_cashbook.isSelected()){
       
               
           // OHADA ACCOUNT
        
        //========>>>>DEBIT
        ///INSERT INTO `ohada_trans`(`ID`, `COMPTE_M`, `COMPTE`, `CODE_M`, `CODE`, `CLASSE`, `SUBSTR`, `DEBIT`, `CREDIT`, `NUM`, `DATES`, `BUSS`, `LIBELLE`, `JOURNAL`)
       
        
        String DEVICEEs = null,DEVICEEss = null;
        Double sale,buy = null,BUY,USD = null,CDF = null;
        Double PR= Double.parseDouble(amount.getText());
    try{
            String sql="select * from monais WHERE caisse='"+codes.getText()+"'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            if(rs.next()){
          DEVICEEss=rs.getString("DEVICE");  
          //  jComboBox2.setSelectedItem(DEVICE);
            }
        //  con.close();
            }
        
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex); }
    
     try{
String sql="SELECT * FROM  currency where APPR='"+DEVICEEss+"'";
           
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
  if(jComboBox1.getSelectedItem().equals("USD")){
      //HERE IS THE CODE 
      try{
            String sql="select * from monais WHERE caisse='"+CODE+"'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            if(rs.next()){
          DEVICEEs=rs.getString("DEVICE");  
          //  jComboBox2.setSelectedItem(DEVICE);
            }
        //  con.close();
            }
        
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex); }
    
     try{
String sql="SELECT * FROM  currency where APPR='"+DEVICEEs+"'";
           
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
      
   CDF=PR*buy;
  USD=PR;
  
  }else{
     
//  USD=PR/sale;
//  CDF=PR;
  }
        
        try{  
             Double xxx;
Double yyy;


//call from journala de banque  
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
            
            if(caissesdebit < aaaa){
            JOptionPane.showMessageDialog(null,"Wrong Data"+"\n"+"No Enough Resources","Error",JOptionPane.ERROR_MESSAGE);
            }else{
     
        try {
      //   String tmp =jComboBox3.getSelectedItem().toString();
        PreparedStatement pst = con.prepareStatement("INSERT INTO `ohada_trans`(`COMPTE_M`, `COMPTE`, `CODE_M`, `CODE`, `CLASSE`, `SUBSTR`, `DEBIT`, `CREDIT`, `NUM`, `DATES`, `BUSS`, `LIBELLE`, `JOURNAL`, `CODE1`,DEBIT_CDF,CREDIT_CDF,DEVICE,LB)"
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        
       pst.setString(1, COMPTEMERE);
        pst.setString(2, NAME);
         pst.setString(3, CODEMERE);
         pst.setString(4, CODE);
         pst.setString(5, CLASS);
         pst.setString(6, "[JB]");
         pst.setDouble(8,USD);
         pst.setString(7,"0");
         pst.setString(9,rolls);

         
       SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(jDateChooser1.getDate());
         pst.setString(10, addDate);
         pst.setString(11,buss.getSelectedItem().toString());
         pst.setString(12,desc.getText());
         pst.setString(13,"[JC]");
          pst.setString(14,codes.getText());
           pst.setString(15,"0");
            pst.setDouble(16,CDF);
            pst.setString(17,DEVICEEs);
            pst.setString(18,codeb.getText());
       
          
         
          pst.executeUpdate();
        
           //      JOptionPane.showMessageDialog(null,"Caisse saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
      
           //========>>>>CREDIT
        ///INSERT INTO `ohada_trans`(`ID`, `COMPTE_M`, `COMPTE`, `CODE_M`, `CODE`, `CLASSE`, `SUBSTR`, `DEBIT`, `CREDIT`, `NUM`, `DATES`, `BUSS`, `LIBELLE`, `JOURNAL`)
        
//        
//         try{
//            String sql="select * from monais WHERE caisse='"+code.getText()+"'";
//           
//            pst=con.prepareStatement(sql);
//            rs=pst.executeQuery();
//            if(rs.next()){
//          DEVICEEs=rs.getString("DEVICE");  
//          //  jComboBox2.setSelectedItem(DEVICE);
//            }
//        //  con.close();
//            }
//        
//        catch(Exception ex){
//          JOptionPane.showMessageDialog(null, ex); }
//          try{
//String sql="SELECT * FROM  currency where APPR='"+DEVICEEs+"'";
//           
//            pst=con.prepareStatement(sql);
//            rs=pst.executeQuery();
//            while(rs.next()){
//            //device=rs.getString("APPR")
//             sale=rs.getDouble("SALE");
//             buy=rs.getDouble("BUY"); 
//            BUY=buy;
//            }
//          
//            }
//        catch(Exception ex){
//          JOptionPane.showMessageDialog(null, ex);    
//        }
//  if(DEVICEEs.equals("USD")){
//   CDF=PR*buy;
//  USD=PR;
//  
//  }else{
//     
//  USD=PR/sale;
//  CDF=PR*sale;
//  }
        
        try {
         //String tmp =jComboBox3.getSelectedItem().toString();
        PreparedStatement pst = con.prepareStatement("INSERT INTO `ohada_trans`(`COMPTE_M`, `COMPTE`, `CODE_M`, `CODE`, `CLASSE`, `SUBSTR`, `DEBIT`, `CREDIT`, `NUM`, `DATES`, `BUSS`, `LIBELLE`, `JOURNAL`, `CODE1`,DEBIT_CDF,CREDIT_CDF,DEVICE,LB)"
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        
            pst.setString(1, comptemere.getText());
        pst.setString(2, compte.getText());
         pst.setString(3, codem.getText());
         pst.setString(4, codes.getText());
         pst.setString(5, classs.getText());
         pst.setString(6, "[JCB]");
         pst.setString(8,"0");
         pst.setString(7,amount.getText());
         pst.setString(9,rolls);
         
       SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(jDateChooser1.getDate());
         pst.setString(10, addDate);
         pst.setString(11,buss.getSelectedItem().toString());
         pst.setString(12,desc.getText());
         pst.setString(13,"[JC]");
          pst.setString(14, CODE);
          pst.setDouble(15,CDF);
            pst.setString(16,"0");
     pst.setString(17,DEVICEEss); 
     pst.setString(18,codeb.getText());
          
         
          pst.executeUpdate();
        
               //  JOptionPane.showMessageDialog(null,"Caisse saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
      // CAISSE
       
       try{  
         Double xxx= null,yyy;   



//call from journala de banque  
            String sql="select SUM(DEBIT),SUM(CREDIT) from caisses where buss='"+buss.getSelectedItem()+"'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                xxx=rs.getDouble("SUM(DEBIT)");
               // entre.setText(""+xxx);
                yyy=rs.getDouble("SUM(CREDIT)");
                  
                   caissesdebit=(xxx-yyy)-USD;
                //   sorti.setText(""+yyy);
            }
            }catch(Exception ex){ JOptionPane.showMessageDialog(null, ex);} 
       
       ///CREDIT CAISSE
       try {
        // String tmp =jComboBox3.getSelectedItem().toString();
         PreparedStatement pst = con.prepareStatement("INSERT INTO `caisses`(`CODE`,`CODEMERE`,`LIBELE`,`BUSS`,`DEBIT`,`CREDIT`,`DATE`,`COMPTE`,NUM,`MOIS`, `ANNEE`, `BUDGET`, `SOLDE`, `DEVICE`,PRINT)"
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        
        pst.setString(1, CODE);
         pst.setString(2, codes.getText());
         pst.setString(3, desc.getText());
         pst.setString(4, buss.getSelectedItem().toString());
         pst.setString(6, "0");
         pst.setDouble(5,USD);
         pst.setString(8,NAME);
         pst.setString(9,rolls);
         
          SimpleDateFormat dateFormatsS = new SimpleDateFormat("MMM");
         String addDateS = dateFormatsS.format(jDateChooser1.getDate());
         pst.setString(10, addDateS);
 
         SimpleDateFormat dateFormatsSs = new SimpleDateFormat("yyyy");
         String addDateSs = dateFormatsSs.format(jDateChooser1.getDate());
         pst.setString(11,addDateSs);
         pst.setString(12,codeb.getText());
         pst.setString(13,"OK");
       
          SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(jDateChooser1.getDate());
         pst.setString(7, addDate);
         pst.setString(14,jComboBox1.getSelectedItem().toString());
          pst.setString(15,"No");
         
          pst.executeUpdate();
        
             //    JOptionPane.showMessageDialog(null,"Caisse saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
       ///DEBIT CAISSE
        try {
       //  String tmp =jComboBox3.getSelectedItem().toString();
          PreparedStatement pst = con.prepareStatement("INSERT INTO `caisses`(`CODE`,`CODEMERE`,`LIBELE`,`BUSS`,`DEBIT`,`CREDIT`,`DATE`,`COMPTE`,NUM,`MOIS`, `ANNEE`, `BUDGET`, `SOLDE`, `DEVICE`,PRINT)"
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        
        pst.setString(1, CODE);
         pst.setString(2, codes.getText());
         pst.setString(3, desc.getText());
         pst.setString(4, buss.getSelectedItem().toString());
         pst.setString(6, "0");
         pst.setDouble(5,CDF);
         pst.setString(8,NAME);
         pst.setString(9,rolls);
         
          SimpleDateFormat dateFormatsS = new SimpleDateFormat("MMM");
         String addDateS = dateFormatsS.format(jDateChooser1.getDate());
         pst.setString(10, addDateS);
 
          SimpleDateFormat dateFormatsSs = new SimpleDateFormat("yyyy");
         String addDateSs = dateFormatsSs.format(jDateChooser1.getDate());
         pst.setString(11,addDateSs);
         pst.setString(12,codeb.getText());
         pst.setString(13,"");
       
          SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(jDateChooser1.getDate());
         pst.setString(7, addDate);
         pst.setString(14,DEVICEEss);
           pst.setString(15,"Yes");
         
          pst.executeUpdate();
        
                 JOptionPane.showMessageDialog(null,"Caisse saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
     //  save_budget();
          // refresh();
            }
         }else{
         try{  
             Double xxx;
Double yyy;


//call from journala de banque  
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
            
            if(caissesdebit < aaaa ||codeb.getText().equals("")||jComboBox2.getSelectedItem().equals("Batchs")){
            JOptionPane.showMessageDialog(null,"Wrong Data  or"+"\n"+"No Enough Resources","Error",JOptionPane.ERROR_MESSAGE);
            }else{
      try {
        // String tmp =jComboBox3.getSelectedItem().toString();
           
        PreparedStatement pst = con.prepareStatement("INSERT INTO `ohada_trans`(`COMPTE_M`, `COMPTE`, `CODE_M`, `CODE`, `CLASSE`, `SUBSTR`, `DEBIT`, `CREDIT`, `NUM`, `DATES`, `BUSS`, `LIBELLE`, `JOURNAL`,`CODE1`,NUM_FACTURE,device,LB)"
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        //NAME`, `CODE`, `COMPTEMERE`, `CODEMERE`, `CLASS`
        pst.setString(1, COMPTEMERE);
        pst.setString(2, NAME);
         pst.setString(3, CODEMERE);
         pst.setString(4, CODE);
         pst.setString(5, CLASS);
         pst.setString(6, "[JB]");
         pst.setString(8,amount.getText());
         pst.setString(7,"0");
         pst.setString(9,rolls);
     
          SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
       String tmps = dateFormats.format(jDateChooser1.getDate());
        pst.setString(10,tmps);
         pst.setString(11,buss.getSelectedItem().toString());
         pst.setString(12,desc.getText());
         pst.setString(13,"[JC]");
         pst.setString(14,codes.getText());
          pst.setString(15,"");
           pst.setString(16,jComboBox1.getSelectedItem().toString());
           pst.setString(17,codeb.getText());
       
          
         
          pst.executeUpdate();
        
           //      JOptionPane.showMessageDialog(null,"Caisse saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
      ///>>>>>52
        try {
        // String tmp =jComboBox3.getSelectedItem().toString();
         
        PreparedStatement pst = con.prepareStatement("INSERT INTO `ohada_trans`(`COMPTE_M`, `COMPTE`, `CODE_M`, `CODE`, `CLASSE`, `SUBSTR`, `DEBIT`, `CREDIT`, `NUM`, `DATES`, `BUSS`, `LIBELLE`, `JOURNAL`,`CODE1`,NUM_FACTURE,device,LB)"
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        
        pst.setString(1, comptemere.getText());
        pst.setString(2, compte.getText());
         pst.setString(3, codem.getText());
         pst.setString(4, codes.getText());
         pst.setString(5, classs.getText());
         pst.setString(6, "[JCB]");
         pst.setString(8,"0");
         pst.setString(7,amount.getText());
         pst.setString(9,rolls);
         
         SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
       String tmps = dateFormats.format(jDateChooser1.getDate());
        pst.setString(10,tmps);
         pst.setString(11,buss.getSelectedItem().toString());
         pst.setString(12,desc.getText());
         pst.setString(13,"[JB]");
         pst.setString(14,CODE);
         pst.setString(15,"");
         pst.setString(16,jComboBox1.getSelectedItem().toString());
         pst.setString(17,codeb.getText());
       
          
         
          pst.executeUpdate();
        
              //   JOptionPane.showMessageDialog(null,"Caisse saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
     
     }
               SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(jDateChooser1.getDate());
          // call_currency();
         //  SELECT_buss();
             //call_sum_budget();
        Double aaa= Double.parseDouble(amount.getText());
        Double ccc;
      
          try{
    String sql="INSERT INTO `budget_trans`(`ITEM`, `DEBIT`, `CREDIT`, `SOLD`, `PROJET`, `CODE`, `NUM`, `CAT`, `DATES`, `MOIS`, `ANNEE`,SUB_CAT,CODE_CAT,CODE_SUB_CAT,`ITEMS`,BATCH) "+"values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    pst=con.prepareStatement(sql);
    pst.setString(1,desc.getText());
    pst.setString(2,"0");
    pst.setDouble(3,aaa);
    pst.setDouble(4,0.0);
    pst.setString(5,buss.getSelectedItem().toString());
    pst.setString(6,jList2.getSelectedValue());
    pst.setString(7,rolls);
    
    pst.setString(8,CAT);
    
    pst.setString(9,addDate);
    SimpleDateFormat dateFormatsS = new SimpleDateFormat("MMM");
         String addDateS = dateFormatsS.format(jDateChooser1.getDate());
         pst.setString(10, addDateS);
 
         
         SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
         String addDat = dateFormat.format(jDateChooser1.getDate());
        // pst.setString(10, addDat);
         pst.setString(11,addDat);
    pst.setString (12,SUB_CAT);
    
     pst.setString(13,code_cat.getText());
      pst.setString(14,code_subcat.getText());
      pst.setString(15,lb.getText());
         pst.setString(16,jComboBox2.getSelectedItem().toString());
   
    
    
    pst.executeUpdate();
    // JOptionPane.showMessageDialog(null,"BUDGET TRANS");
    }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}    
         
         }
      
     }
            
 //     JOptionPane.showMessageDialog(null,"Caisse saved");
     }
     
    
        //SEARCH AND ADDREADER
     
      public void etReadData(String sql)
    {
            Statement st;
            try{
                
                st=con.createStatement();
                ResultSet rs;
                rs=st.executeQuery(sql);
                if(rs.first()){
                    String Data[]=new String[9];
                    do{
                        for(int i=0;i<9;i++){
                            Data[i]=rs.getString(i+1);
                        }
                    
                    mode.addRow(Data);
                }while(rs.next());
            }
                rs.close();
            }catch(Exception ex){
                
            }
    }
    
    public void etAddModel(){
        mode=new DefaultTableModel();
        mode.addColumn("ID");    
        mode.addColumn("TRANS_NUM");
        mode.addColumn("PROVENANCE");
        mode.addColumn("MOTIF");
        mode.addColumn("projet");
        mode.addColumn("MOTANT_IN");
        mode.addColumn("MOTANT_OUT");
        mode.addColumn("DATES");
        mode.addColumn("BANQUE");
        NewJInternalFramejournal_de_banque.jTable1.setModel(mode);
        
        
        TableColumn col0=NewJInternalFramejournal_de_banque.jTable1.getColumnModel().getColumn(0);
        TableColumn col1=NewJInternalFramejournal_de_banque.jTable1.getColumnModel().getColumn(1);
        TableColumn col2=NewJInternalFramejournal_de_banque.jTable1.getColumnModel().getColumn(2);
        TableColumn col3=NewJInternalFramejournal_de_banque.jTable1.getColumnModel().getColumn(3);
        TableColumn col4=NewJInternalFramejournal_de_banque.jTable1.getColumnModel().getColumn(4);
        TableColumn col5=NewJInternalFramejournal_de_banque.jTable1.getColumnModel().getColumn(5);
        TableColumn col6=NewJInternalFramejournal_de_banque.jTable1.getColumnModel().getColumn(6);
        TableColumn col7=NewJInternalFramejournal_de_banque.jTable1.getColumnModel().getColumn(7);
        TableColumn col8=NewJInternalFramejournal_de_banque.jTable1.getColumnModel().getColumn(8);
       
       col0.setPreferredWidth(10);
       col1.setPreferredWidth(130);
       col2.setPreferredWidth(150);
       col3.setPreferredWidth(150);
       col4.setPreferredWidth(130);
       col5.setPreferredWidth(50);
       col6.setPreferredWidth(50);
       col7.setPreferredWidth(50);
       col8.setPreferredWidth(50);
    }
      
      public void etremove(){
          while(NewJInternalFramejournal_de_banque.jTable1.getRowCount()>0){
              mode.removeRow(0);
          }
      }
              
             public void etsearch()
             {
                  String st=bank.getSelectedItem().toString();
   etremove();
    etReadData("select * from  journal_de_bank where BANQUE like '"+st+"'");
   
             } 
             
             
             
                public void call_in_table2(){
        
         try{
       
      
       String sql="select ID,`TRANS_NUM`, `PROVENANCE`, `MOTIF`, `projet`, `MOTANT_IN`, `MOTANT_OUT`, `DATES`, `BANQUE`from journal_de_bank";
       pst = con.prepareStatement(sql);
      rs= pst.executeQuery();
      
       
       NewJInternalFramejournal_de_banque.jTable1.setModel(DbUtils.resultSetToTableModel(rs));
       mode=new DefaultTableModel();
       
       
       TableColumn col0=NewJInternalFramejournal_de_banque.jTable1.getColumnModel().getColumn(0);
        TableColumn col1=NewJInternalFramejournal_de_banque.jTable1.getColumnModel().getColumn(1);
        TableColumn col2=NewJInternalFramejournal_de_banque.jTable1.getColumnModel().getColumn(2);
        TableColumn col3=NewJInternalFramejournal_de_banque.jTable1.getColumnModel().getColumn(3);
        TableColumn col4=NewJInternalFramejournal_de_banque.jTable1.getColumnModel().getColumn(4);
        TableColumn col5=NewJInternalFramejournal_de_banque.jTable1.getColumnModel().getColumn(5);
        TableColumn col6=NewJInternalFramejournal_de_banque.jTable1.getColumnModel().getColumn(6);
        TableColumn col7=NewJInternalFramejournal_de_banque.jTable1.getColumnModel().getColumn(7);
        TableColumn col8=NewJInternalFramejournal_de_banque.jTable1.getColumnModel().getColumn(8);
       
       col0.setPreferredWidth(10);
       col1.setPreferredWidth(130);
       col2.setPreferredWidth(150);
       col3.setPreferredWidth(150);
       col4.setPreferredWidth(130);
       col5.setPreferredWidth(50);
       col6.setPreferredWidth(50);
       col7.setPreferredWidth(50);
       col8.setPreferredWidth(50);
       
       
      // jTable1.setModel(mode);
       
     
    }catch(SQLException ex ){
     JOptionPane.showMessageDialog(null, ex);
}
     }
                
                public void cal_num_fact(){
                num_fact.removeAllItems();
                
              
                
                 try{
           String sql="select distinct(NUM_FACTURE) AS 'No_INVOICE' from   ohada_trans where SUBSTR='41'  and BUSS='"+buss.getSelectedItem()+"' and PAY='No' group by NUM_FACTURE";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
         while(rs.next()){
                String sum2=rs.getString("No_INVOICE");
                 num_fact.addItem(sum2);
            }
            
          //  }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
                
                
                }
                
            ///  amount.setText(""+c);
         
                
                 public void call_num_fact(){
                
                  try{
           String sqls=" select SUM(DEBIT),SUM(CREDIT)  FROM ohada_trans where  NUM_FACTURE='"+num_fact.getSelectedItem()+"' and code_m=41";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
         while(rs.next()){
                Double sum=rs.getDouble("SUM(DEBIT)");
                Double sum1=rs.getDouble("SUM(CREDIT)");
                Double c= sum-sum1;
              amount.setText(""+c);
            }
            
          //  }
            }
        catch(Exception ex){
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

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        buss = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        Date = new javax.swing.JLabel();
        credit = new javax.swing.JRadioButton();
        debit = new javax.swing.JRadioButton();
        bank = new javax.swing.JComboBox<>();
        autre = new javax.swing.JTextField();
        jDateChooser1 = new com.alee.extended.date.WebDateField();
        amount = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        desc = new javax.swing.JEditorPane();
        jButton2 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        compte = new javax.swing.JTextField();
        codem = new javax.swing.JTextField();
        classs = new javax.swing.JTextField();
        comptemere = new javax.swing.JTextField();
        codes = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jTextField1 = new javax.swing.JTextField();
        num_fact = new javax.swing.JComboBox<>();
        num_fact2 = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList<>();
        jTextField2 = new javax.swing.JTextField();
        lb = new javax.swing.JTextField();
        codeb = new javax.swing.JTextField();
        code_cat = new javax.swing.JTextField();
        code_subcat = new javax.swing.JTextField();
        btwn_cashbook = new javax.swing.JRadioButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        buss.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        buss.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select One Project", "Other" }));
        buss.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                bussPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Description");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Amount_out");

        Date.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Date.setText("Transaction Date");

        credit.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        credit.setText("Credit");

        debit.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        debit.setText("Debit");

        bank.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
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

        autre.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jDateChooser1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        amount.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        amount.setText("0.00");
        amount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                amountKeyTyped(evt);
            }
        });

        jScrollPane1.setViewportView(desc);

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton2.setText("Save");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        compte.setEditable(false);
        compte.setBackground(new java.awt.Color(240, 240, 241));
        compte.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        compte.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        codem.setEditable(false);
        codem.setBackground(new java.awt.Color(240, 240, 241));
        codem.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        codem.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        classs.setEditable(false);
        classs.setBackground(new java.awt.Color(240, 240, 241));
        classs.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        classs.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        comptemere.setEditable(false);
        comptemere.setBackground(new java.awt.Color(240, 240, 241));
        comptemere.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        comptemere.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        codes.setEditable(false);
        codes.setBackground(new java.awt.Color(240, 240, 241));
        codes.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        codes.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(comptemere)
                    .addComponent(compte)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(codem, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(classs, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(codes, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(compte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(codem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(classs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(codes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(comptemere, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jList1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jList1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jList1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jList1);

        jTextField1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTextField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        num_fact.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        num_fact.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Num. Fact." }));
        num_fact.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                num_factPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        num_fact.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                num_factActionPerformed(evt);
            }
        });

        num_fact2.setEditable(false);
        num_fact2.setBackground(new java.awt.Color(240, 240, 241));
        num_fact2.setForeground(new java.awt.Color(255, 255, 255));

        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jList2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jList2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jList2MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jList2);

        jTextField2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTextField2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField2KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        lb.setEditable(false);
        lb.setBackground(new java.awt.Color(240, 240, 241));
        lb.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        codeb.setEditable(false);
        codeb.setBackground(new java.awt.Color(240, 240, 241));
        codeb.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        codeb.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        code_cat.setEditable(false);
        code_cat.setBackground(new java.awt.Color(240, 240, 241));
        code_cat.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        code_cat.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        code_subcat.setEditable(false);
        code_subcat.setBackground(new java.awt.Color(240, 240, 241));
        code_subcat.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        code_subcat.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        btwn_cashbook.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btwn_cashbook.setText("Between CashBook");
        btwn_cashbook.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        btwn_cashbook.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btwn_cashbookMouseClicked(evt);
            }
        });

        jComboBox1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "USD" }));

        jComboBox2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Batchs" }));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(credit)
                        .addGap(18, 18, 18)
                        .addComponent(debit)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btwn_cashbook, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(bank, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(autre, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(amount, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(num_fact2, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lb)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Date)
                            .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(codeb, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(code_cat, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(code_subcat, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 4, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(buss, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(num_fact, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(credit)
                    .addComponent(debit)
                    .addComponent(btwn_cashbook, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buss, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(num_fact, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(autre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bank, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Date)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(amount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(code_subcat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(code_cat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(codeb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(num_fact2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Manuel_Transaction");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(286, 286, 286))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void bussPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_bussPopupMenuWillBecomeInvisible
if(buss.getSelectedItem().equals("Other")) {

autre.setEnabled(true);
 autre.setBackground(Color.white) ;
}else{autre.setEnabled(false);
 autre.setBackground(Color.LIGHT_GRAY) ;
 cal_num_fact();
}
DefaultListModel list = new DefaultListModel();
jList2.setModel(list);
 try{
          String sql="SELECT * FROM budget_code  where CAT='"+buss.getSelectedItem()+"'order by CODE";
             
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                //String sum=rs.getString("nom");
                String sums=rs.getString("code");
                 list.addElement(sums);
                
                 jList2.setModel(list);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
jComboBox2.removeAllItems();
     jComboBox2.addItem("Batchs"); 
     try{
          String sql="SELECT * FROM batchs where projet='"+buss.getSelectedItem()+"'";
             
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                //String sum=rs.getString("nom");
                String sums=rs.getString("BATCH");
                
                
               jComboBox2.addItem(sums);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        } // TODO add your handling code here:
    }//GEN-LAST:event_bussPopupMenuWillBecomeInvisible

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
if(compte.getText().equals("") |codes.getText().equals("") |comptemere.getText().equals("") |desc.getText().equals("") |jDateChooser1.getText().equals("") |amount.getText().equals("") |amount.getText().equals("0.00")|bank.getSelectedItem().equals("Select One Bank") ){
JOptionPane.showMessageDialog(null,"Wrong Data","Error",JOptionPane.ERROR_MESSAGE);
}else{
    if(credit.isSelected()){
     newsave();
      this.dispose();
    }else{
    newsave();
    this.dispose();
    }

//call_in_table2();
//code.equals("");
compte.setText("");
codem.setText("");
classs.setText("");
codes.setText("");
comptemere.setText("");
amount.setText("0.00");
jDateChooser1.setText("");
bank.setSelectedItem("");
buss.setSelectedItem("Other");
//this.dispose();
}// TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void bankPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_bankPopupMenuWillBecomeInvisible
 SELECT_buss();// TODO add your handling code here:
    }//GEN-LAST:event_bankPopupMenuWillBecomeInvisible

    private void amountKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_amountKeyTyped
char c=evt.getKeyChar();
        if(!(Character.isDigit(c) || (c==KeyEvent.VK_BACK_SPACE)||c==KeyEvent.VK_DELETE ||c==KeyEvent.VK_PERIOD )){
            evt.consume();
           
        } 
                // TODO add your handling code here:
    }//GEN-LAST:event_amountKeyTyped

    private void jList1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList1MouseClicked
clic_attCall_IN_LIST7();        // TODO add your handling code here:
    }//GEN-LAST:event_jList1MouseClicked

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
attCall_IN_LIST();        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1KeyReleased

    private void num_factPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_num_factPopupMenuWillBecomeInvisible
call_num_fact();        // TODO add your handling code here:
    }//GEN-LAST:event_num_factPopupMenuWillBecomeInvisible

    private void num_factActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_num_factActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_num_factActionPerformed

    private void jList2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList2MouseClicked
call_budget();        // TODO add your handling code here:
    }//GEN-LAST:event_jList2MouseClicked

    private void jTextField2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyReleased
search();        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2KeyReleased

    private void btwn_cashbookMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btwn_cashbookMouseClicked
      //  refresh();        // TODO add your handling code here:
    }//GEN-LAST:event_btwn_cashbookMouseClicked

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
            java.util.logging.Logger.getLogger(transaction_manuel_banque.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(transaction_manuel_banque.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(transaction_manuel_banque.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(transaction_manuel_banque.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new transaction_manuel_banque().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Date;
    private javax.swing.JTextField amount;
    private javax.swing.JTextField autre;
    private javax.swing.JComboBox<String> bank;
    private javax.swing.JRadioButton btwn_cashbook;
    private javax.swing.JComboBox<String> buss;
    private javax.swing.JTextField classs;
    private javax.swing.JTextField code_cat;
    private javax.swing.JTextField code_subcat;
    private javax.swing.JTextField codeb;
    private javax.swing.JTextField codem;
    private javax.swing.JTextField codes;
    private javax.swing.JTextField compte;
    private javax.swing.JTextField comptemere;
    private javax.swing.JRadioButton credit;
    private javax.swing.JRadioButton debit;
    private javax.swing.JEditorPane desc;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private com.alee.extended.date.WebDateField jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JList<String> jList1;
    private javax.swing.JList<String> jList2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField lb;
    private javax.swing.JComboBox<String> num_fact;
    private javax.swing.JTextField num_fact2;
    // End of variables declaration//GEN-END:variables
}
