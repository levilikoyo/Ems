/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intreprisemanagementsystem;
import java.awt.event.KeyEvent;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.table.DefaultTableCellRenderer;
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
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author DOSHE
 */
public class journal_caisse extends javax.swing.JInternalFrame {

  Connection con=null;
  Connection cononline=null;
PreparedStatement pst=null;
ResultSet rs= null;
 DefaultTableModel mode;
 String roll,rolls;
 String CLASSS;
 String NAME,CODE,COMPTEMERE,CODEMERE,CLASS,SUBSTRS,SUBSTR,CATB,SUB_CATB;
 Double CHECK_BUDGET,CHECK_OHADA,CHECK_BUDGETP,ECART;
 
//Double bbb=Double.parseDouble(creditbudget.getText());
 Double SUMDEBIT,SUMCREDIT;
    public journal_caisse() {
        initComponents();
         /*this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI bui= (BasicInternalFrameUI) this.getUI();
       bui.setNorthPane(null);*/
        con=JavaDbConnect.dbConnect();
       
        call();
        show_in_table();
        jDateChooser1.setDate(new Date());
    }
    

public void call(){
    DefaultListModel list = new DefaultListModel();
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
               try{
          String sql="SELECT * FROM ohada  order by CODE";
             
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
      try{
          String sql="SELECT * FROM ohada where CODEMERE=57";
             
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                //String sum=rs.getString("nom");
                String sums=rs.getString("CODE");
                
                
               jComboBox1.addItem(sums);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
      
         try{
          String sql="SELECT * FROM currency";
             
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                //String sum=rs.getString("nom");
                String sums=rs.getString("APPR");
                
                
               jComboBox2.addItem(sums);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
         
     }
 public void budget_LIST1()
    {
        DefaultListModel list = new DefaultListModel();
        
         
        try{
            String sql="SELECT * FROM `budget_code` where CAT='"+buss.getSelectedItem()+"' order by CODE";
           
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
    try{
         titre.setText("");
            String sql="SELECT * FROM `projet` where PROJET_NUM='"+buss.getSelectedItem()+"'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                //String sum=rs.getString("nom");
                String sums=rs.getString("PROJET");
              //   list.addElement(sums);
                
                 titre.setText(sums);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
     jComboBox5.removeAllItems();
     jComboBox5.addItem("Batchs"); 
     try{
          String sql="SELECT * FROM batchs where projet='"+buss.getSelectedItem()+"'";
             
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                //String sum=rs.getString("nom");
                String sums=rs.getString("BATCH");
                
                
               jComboBox5.addItem(sums);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
    }
 public void call_budget(){
     if(jComboBox3.getSelectedItem().equals("Credit")){
     String tmp =(String)jList1.getSelectedValue();
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
          String sql="SELECT CAT,LB,LBSUB,SUB_CAT FROM  budget WHERE code='"+tmp+"' and PROJECT='"+buss.getSelectedItem()+"'";
          
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
              
          }
  
      }catch(Exception ex){
        JOptionPane.showMessageDialog(null,ex);  
      }
     }else{
      lb.setText("Remboursement Pref-01");     
              codeb.setText("Pref-01");
              code_cat.setText("Pref-01");
             code_cat.setText("Pref-01");  
             code_subcat.setText("Pref-01");
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
                
                 jList2.setModel(list);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
    }
        
        
           public void attCall_IN_LIST_serach_LB()
    { 
        DefaultListModel list = new DefaultListModel();
 
        try{
          String sql="SELECT * FROM  budget_code where CODE like '"+recherche1.getText()+"%'  AND CAT='"+buss.getSelectedItem()+"' order by CODE";
             
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
        
        public void clic_attCall_IN_LIST7()
    { 
        DefaultListModel list = new DefaultListModel();
 
        try{
          String sql="SELECT * FROM ohada where CODE ='"+jList2.getSelectedValue()+"'";
           
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
                compte1.setText(COMPTEMERES);
                 
                  CLASSS=rs.getString("CLASS");
                  SUBSTR=rs.getString("SUBSTR");
                
                 
                
                
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
    }
     Double BUY,USD,CDF,buy,sale ;//,USDD;
           public void call_currency(){
               
      Double PR = Double.parseDouble(amount.getText());
 try{
String sql="select * from monais WHERE caisse='"+jComboBox1.getSelectedItem()+"'";      
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            if(rs.next()){
           String DEVICE=rs.getString("DEVICE");  
            jComboBox2.setSelectedItem(DEVICE);
            }
        //  con.close();
            }
        
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex); }

 try{
String sql="SELECT * FROM  currency where APPR='"+jComboBox2.getSelectedItem()+"'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                //device=rs.getString("APPR")
             sale=rs.getDouble("SALE");
             buy=rs.getDouble("BUY"); 
            BUY=sale;
            }
          
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
  if(jComboBox2.getSelectedItem().equals("USD")){
   CDF=PR*buy;
  USD=PR;
  
  }else{
     
  USD=PR/sale;
  CDF=PR;
  }
      
}

 
 
  
    
         public void callcaisebuss()
    {
       call_currency();
        
         Double Debit,Credit,c,cc;
      //   String e;
         
         
          NumberFormat nf = new DecimalFormat("#,###.##");
         
         
        String DEVICE = null; 
                 try{
            String sql="select * from monais WHERE caisse='"+jComboBox1.getSelectedItem()+"'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            if(rs.next()){
            DEVICE=rs.getString("DEVICE");  
          //  jComboBox2.setSelectedItem(DEVICE);
            }
            if(DEVICE.equals("USD")){
                try{
            String sqlS="SELECT sum(debit),sum(credit) FROM   ohada_trans WHERE CODE='"+jComboBox1.getSelectedItem()+"' AND SOLDE='No'";
          
            pst=con.prepareStatement(sqlS);
            rs=pst.executeQuery();
            while(rs.next()){
               Debit=rs.getDouble("sum(debit)");
               Credit=rs.getDouble("sum(credit)");
               
              // cc=Debitcdf-Creditcdf;
                c=Debit-Credit;
              cc=c*BUY; 
             // jTextField3.setText(""+USDD);
               
               
String fn = nf.format(c);
String fn1 = nf.format(cc);
           usd.setText(fn);
         cdf.setText(fn1);
         jLabel7.setText("USD");
         jLabel8.setText("CDF");
        
           
               
               
               
            }
         //   con.close();
            }
        
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
            }else{
                 try{
            String sqls="SELECT sum(debit_cdf),sum(credit_cdf) FROM   ohada_trans WHERE CODE='"+jComboBox1.getSelectedItem()+"' AND SOLDE='No'";
          
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
            while(rs.next()){
               Debit=rs.getDouble("sum(debit_cdf)");
               Credit=rs.getDouble("sum(credit_cdf)");
               
              // cc=Debitcdf-Creditcdf;
                c=Debit-Credit;
              cc=c/BUY; 
             // jTextField3.setText(""+USDD);
               
String fn = nf.format(c);
String fn1 = nf.format(cc);
           cdf.setText(fn);
         usd.setText(fn1);
        
           
         jLabel7.setText("USD");
         jLabel8.setText(DEVICE);       
               
               
            }
         //   con.close();
            }
        
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
            }
            
            
            } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());}   
        
                 
    
    
    }
      public void call_caisse(){
          
          
               NumberFormat nf = new DecimalFormat("#,###.##");
                Double debit = null,credit = null,sold;

                  try{
            String sqls="select sum(COUTS) from projet WHERE PROJET_NUM='"+buss.getSelectedItem()+"'";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
            while(rs.next()){
                Double add1 = rs.getDouble("sum(COUTS)");
                String fn = nf.format(add1 );

              budget.setText(fn);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
      try{
            String sqls="select SUM(DEBIT) from OHADA_TRANS  WHERE BUSS='"+buss.getSelectedItem()+"' AND SUBSTR=57 AND LB='' and journal='OK'";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
            while(rs.next()){
                 debit=rs.getDouble("SUM(DEBIT)");
                 
                  String fnnn = nf.format(debit);
debitbudget.setText(fnnn);


            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);}
         try{
            String sqls="select SUM(CREDIT)  from OHADA_TRANS WHERE BUSS='"+buss.getSelectedItem()+"' AND LB<> '' AND SUBSTR=57";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
            while(rs.next()){
               credit =rs.getDouble("SUM(CREDIT)");
                
                String fnn = nf.format(credit);
                 
reste.setText(fnn);


            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
         sold=debit-credit;
          String fnn = nf.format(sold);
          creditbudget.setText(fnn);
      }
      
      public void SELECT_buss()
    {
       
        
         //`NAME`, `CODE`, `COMPTEMERE`, `CODEMERE`, `CLASS`
        try{
             
            String sql="SELECT * FROM   OHADA where CODE='"+jComboBox1.getSelectedItem()+"' and CLASS=5";
          
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                //String sum=rs.getString("nom");
               COMPTEMERE=rs.getString("COMPTEMERE");
               
                
                CODEMERE=rs.getString("CODEMERE");
              
                
                CODE=rs.getString("CODE");
              
                
                 NAME=rs.getString("NAME");
                 caisse.setText(NAME);
                  CLASS=rs.getString("CLASS");
                  SUBSTRS =rs.getString("SUBSTR");
                  //COMPTEMERE,CODEMERE,CODE,NAME, CLASS,SUBSTRS
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
    
    }
    
      
      
      public void rolls(){
          try{
                  SimpleDateFormat dateFormatsS = new SimpleDateFormat("yyyy-MM-dd");
         String addDateS = dateFormatsS.format(jDateChooser1.getDate());
         
         String sql="SELECT NUM FROM ohada_trans where SUBSTR(NUM,5,10)='"+addDateS+"' AND BUSS='"+buss.getSelectedItem()+"'  ORDER BY NUM DESC LIMIT 1";
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
      
      
      
          // />>here
      public void save(){
        rolls();
          SELECT_buss();
          clic_attCall_IN_LIST7();
         // call_currency();
         Double PR = Double.parseDouble(amount.getText());
        
        if(btwn_cashbook.isSelected()){
        
         if(jComboBox3.getSelectedItem().equals("Credit")){
              try{
       
 
            String sql="SELECT   sum(DEBIT),sum(CREDIT) FROM ohada_trans WHERE code='"+jComboBox1.getSelectedItem()+"' AND BUSS='"+buss.getSelectedItem()+"'";
           
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
 
   
   if(CHECK_OHADA <USD){ 
      
   JOptionPane.showMessageDialog(null,"Wrong Data"+"\n"+"No Enough Resources  ","Error",JOptionPane.ERROR_MESSAGE);
  }else if(cdf.getText().isEmpty()||libele.getText().isEmpty()||code_m.getText().isEmpty()||jComboBox2.getSelectedItem().equals("....")||jComboBox3.getSelectedItem().equals("....")){ 
   JOptionPane.showMessageDialog(null,"Wrong Data"+"\n"+"Wrong Data ","Error",JOptionPane.ERROR_MESSAGE);
  }else if(jComboBox1.getSelectedItem().equals("Select one Caisse")){ 
   JOptionPane.showMessageDialog(null,"Wrong Data"+"\n"+"Select one Caisse","Error",JOptionPane.ERROR_MESSAGE);
  }else{ 
               
           // OHADA ACCOUNT
        
        //========>>>>DEBIT
        ///INSERT INTO `ohada_trans`(`ID`, `COMPTE_M`, `COMPTE`, `CODE_M`, `CODE`, `CLASSE`, `SUBSTR`, `DEBIT`, `CREDIT`, `NUM`, `DATES`, `BUSS`, `LIBELLE`, `JOURNAL`)
       
        
        String DEVICEEs = null,DEVICEEss = null;
    try{
            String sql="select * from monais WHERE caisse='"+jComboBox1.getSelectedItem()+"'";
           
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
  if(jComboBox2.getSelectedItem().equals("USD")){
      //HERE IS THE CODE 
      try{
            String sql="select * from monais WHERE caisse='"+code.getText()+"'";
           
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
        
        try {
         String tmp =jComboBox3.getSelectedItem().toString();
        PreparedStatement pst = con.prepareStatement("INSERT INTO `ohada_trans`(`COMPTE_M`, `COMPTE`, `CODE_M`, `CODE`, `CLASSE`, `SUBSTR`, `DEBIT`, `CREDIT`, `NUM`, `DATES`, `BUSS`, `LIBELLE`, `JOURNAL`, `CODE1`,DEBIT_CDF,CREDIT_CDF,DEVICE,LB)"
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        
        pst.setString(1, COMPTEMERE);
        pst.setString(2, NAME);
         pst.setString(3, CODEMERE);
         pst.setString(4, CODE);
         pst.setString(5,CLASS);
         pst.setString(6, SUBSTRS);
         pst.setDouble(8,USD);
         pst.setString(7,"0");
         pst.setString(9,rolls);
         
       SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(jDateChooser1.getDate());
         pst.setString(10, addDate);
         pst.setString(11,buss.getSelectedItem().toString());
         pst.setString(12,libele.getText());
         pst.setString(13,"[JC]");
          pst.setString(14,code.getText());
           pst.setString(15,"0");
            pst.setDouble(16,CDF);
            pst.setString(17,DEVICEEss);
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
         String tmp =jComboBox3.getSelectedItem().toString();
        PreparedStatement pst = con.prepareStatement("INSERT INTO `ohada_trans`(`COMPTE_M`, `COMPTE`, `CODE_M`, `CODE`, `CLASSE`, `SUBSTR`, `DEBIT`, `CREDIT`, `NUM`, `DATES`, `BUSS`, `LIBELLE`, `JOURNAL`, `CODE1`,DEBIT_CDF,CREDIT_CDF,DEVICE,LB)"
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        
        pst.setString(1, compte1.getText());
        pst.setString(2, compte.getText());
         pst.setString(3, code_m.getText());
         pst.setString(4, code.getText());
         pst.setString(5,CLASSS);
         pst.setString(6, SUBSTR);
         pst.setString(8,"0");
         pst.setDouble(7,USD);
         pst.setString(9,rolls);
         
       SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(jDateChooser1.getDate());
         pst.setString(10, addDate);
         pst.setString(11,buss.getSelectedItem().toString());
         pst.setString(12,libele.getText());
         pst.setString(13,"[JC]");
          pst.setString(14, CODE);
          pst.setDouble(15,CDF);
            pst.setString(16,"0");
     pst.setString(17,DEVICEEs);  
     pst.setString(18,codeb.getText());
          
         
          pst.executeUpdate();
        
               //  JOptionPane.showMessageDialog(null,"Caisse saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
      // CAISSE
       Double xxx,caissesdebit = null,yyy;
       try{  
            



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
         String tmp =jComboBox3.getSelectedItem().toString();
         PreparedStatement pst = con.prepareStatement("INSERT INTO `caisses`(`CODE`,`CODEMERE`,`LIBELE`,`BUSS`,`DEBIT`,`CREDIT`,`DATE`,`COMPTE`,NUM,`MOIS`, `ANNEE`, `BUDGET`, `SOLDE`, `DEVICE`,PRINT)"
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        
        pst.setString(1, code.getText());
         pst.setString(2, jComboBox1.getSelectedItem().toString());
         pst.setString(3, libele.getText());
         pst.setString(4, buss.getSelectedItem().toString());
         pst.setString(5, "0");
         pst.setDouble(6,USD);
         pst.setString(8,NAME);
         pst.setString(9,rolls);
         
          SimpleDateFormat dateFormatsS = new SimpleDateFormat("MMM");
         String addDateS = dateFormatsS.format(jDateChooser1.getDate());
         pst.setString(10, addDateS);
 
         pst.setInt(11,jYearChooser1.getValue());
         pst.setString(12,codeb.getText());
         pst.setString(13,"");
       
          SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(jDateChooser1.getDate());
         pst.setString(7, addDate);
         pst.setString(14,jComboBox2.getSelectedItem().toString());
          pst.setString(15,"Yes");
         
          pst.executeUpdate();
        
             //    JOptionPane.showMessageDialog(null,"Caisse saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
       ///DEBIT CAISSE
        try {
         String tmp =jComboBox3.getSelectedItem().toString();
          PreparedStatement pst = con.prepareStatement("INSERT INTO `caisses`(`CODE`,`CODEMERE`,`LIBELE`,`BUSS`,`DEBIT`,`CREDIT`,`DATE`,`COMPTE`,NUM,`MOIS`, `ANNEE`, `BUDGET`, `SOLDE`, `DEVICE`,PRINT)"
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        
        pst.setString(1, code.getText());
         pst.setString(2, code.getText());
         pst.setString(3, libele.getText());
         pst.setString(4, buss.getSelectedItem().toString());
         pst.setString(6, "0");
         pst.setDouble(5,CDF);
         pst.setString(8,NAME);
         pst.setString(9,rolls);
         
          SimpleDateFormat dateFormatsS = new SimpleDateFormat("MMM");
         String addDateS = dateFormatsS.format(jDateChooser1.getDate());
         pst.setString(10, addDateS);
 
         pst.setInt(11,jYearChooser1.getValue());
         pst.setString(12,codeb.getText());
         pst.setString(13,"");
       
          SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(jDateChooser1.getDate());
         pst.setString(7, addDate);
         pst.setString(14,DEVICEEs);
           pst.setString(15,"Yes");
         
          pst.executeUpdate();
        
                 JOptionPane.showMessageDialog(null,"Caisse saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
     //  save_budget();
           refresh();
           }
       
           }else if(jComboBox3.getSelectedItem().equals("Debit")){
               
   //>>>>>>>NOT YET IMPLIMATATED
     
//            try{  
//             Double xxx;
//Double yyy;
//
//
////call from journala de banque  
//            String sql="select SUM(DEBIT),SUM(CREDIT) from ohada_trans where code='"+code.getText()+"' and BUSS='"+buss.getSelectedItem()+"'";
//           
//            pst=con.prepareStatement(sql);
//            rs=pst.executeQuery();
//            while(rs.next()){
//                xxx=rs.getDouble("SUM(DEBIT)");
//               // entre.setText(""+xxx);
//                yyy=rs.getDouble("SUM(CREDIT)");
//                  
//                   CHECK_OHADA=(xxx-yyy);
//                 
//            }
//            }catch(Exception ex){ JOptionPane.showMessageDialog(null, ex);}
//            
//       
//       if(CHECK_OHADA <USD){ 
//      
//   JOptionPane.showMessageDialog(null,"Wrong Data"+"\n"+"No Enough Resources   ","Error",JOptionPane.ERROR_MESSAGE);
//  }/*else if(CHECK_BUDGET <PR){ 
//      
//   JOptionPane.showMessageDialog(null,"Wrong Data"+"\n"+"No Enough Budget ","Error",JOptionPane.ERROR_MESSAGE);
//  }*/else if(cdf.getText().isEmpty()||libele.getText().isEmpty()||code_m.getText().isEmpty()||jComboBox2.getSelectedItem().equals("....")||jComboBox3.getSelectedItem().equals("....")){ 
//   JOptionPane.showMessageDialog(null,"Wrong Data"+"\n"+"Wrong Data ","Error",JOptionPane.ERROR_MESSAGE);
//  }else if(jComboBox1.getSelectedItem().equals("Select one Caisse")){ 
//   JOptionPane.showMessageDialog(null,"Wrong Data"+"\n"+"Select one Caisse","Error",JOptionPane.ERROR_MESSAGE);
//  }else{ 
//               
//     
//      // OHADA ACCOUNT 
//        
//        //========>>>>DEBIT
//        ///INSERT INTO `ohada_trans`(`ID`, `COMPTE_M`, `COMPTE`, `CODE_M`, `CODE`, `CLASSE`, `SUBSTR`, `DEBIT`, `CREDIT`, `NUM`, `DATES`, `BUSS`, `LIBELLE`, `JOURNAL`)
//          String DEVICEE = null;
//    try{
//            String sql="select * from monais WHERE caisse='"+jComboBox1.getSelectedItem()+"'";
//           
//            pst=con.prepareStatement(sql);
//            rs=pst.executeQuery();
//            if(rs.next()){
//          DEVICEE=rs.getString("DEVICE");  
//          //  jComboBox2.setSelectedItem(DEVICE);
//            }
//        //  con.close();
//            }
//        
//        catch(Exception ex){
//          JOptionPane.showMessageDialog(null, ex); }
// 
//        
//        
//        try {
//        // String tmp =jComboBox3.getSelectedItem().toString();
//         PreparedStatement pst = con.prepareStatement("INSERT INTO `ohada_trans`(`COMPTE_M`, `COMPTE`, `CODE_M`, `CODE`, `CLASSE`, `SUBSTR`, `DEBIT`, `CREDIT`, `NUM`, `DATES`, `BUSS`, `LIBELLE`, `JOURNAL`, `CODE1`,DEBIT_CDF,CREDIT_CDF,DEVICE)"
//        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
//        
//        pst.setString(1, COMPTEMERE);
//        pst.setString(2, NAME);
//         pst.setString(3, CODEMERE);
//         pst.setString(4, CODE);
//         pst.setString(5,CLASS);
//         pst.setString(6, SUBSTRS);
//         pst.setDouble(7,USD);
//         pst.setString(8,"0");
//         pst.setString(9,rolls);
//         
//       SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
//         String addDate = dateFormats.format(jDateChooser1.getDate());
//         pst.setString(10, addDate);
//         pst.setString(11,buss.getSelectedItem().toString());
//         pst.setString(12,libele.getText());
//         pst.setString(13,"[JC]");
//          pst.setString(14,code.getText());
//           pst.setString(16,"0");
//            pst.setDouble(15,CDF);
//       pst.setString(17, DEVICEE);
//          
//         
//          pst.executeUpdate();
//        
//           //      JOptionPane.showMessageDialog(null,"Caisse saved");
//    } catch (Exception ex) {
//         JOptionPane.showMessageDialog(null, ex.getMessage());
//    }
//      
//           //========>>>>CREDIT
//        ///INSERT INTO `ohada_trans`(`ID`, `COMPTE_M`, `COMPTE`, `CODE_M`, `CODE`, `CLASSE`, `SUBSTR`, `DEBIT`, `CREDIT`, `NUM`, `DATES`, `BUSS`, `LIBELLE`, `JOURNAL`)
//        
//        
//        //>>>>>DEVICE HERE
//        String DEVICEEs = null;
//    try{
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
//
//        
//        try {
//         String tmp =jComboBox3.getSelectedItem().toString();
//         PreparedStatement pst = con.prepareStatement("INSERT INTO `ohada_trans`(`COMPTE_M`, `COMPTE`, `CODE_M`, `CODE`, `CLASSE`, `SUBSTR`, `DEBIT`, `CREDIT`, `NUM`, `DATES`, `BUSS`, `LIBELLE`, `JOURNAL`, `CODE1`,DEBIT_CDF,CREDIT_CDF,DEVICE)"
//        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
//        
//        pst.setString(1, compte1.getText());
//        pst.setString(2, compte.getText());
//         pst.setString(3, code_m.getText());
//         pst.setString(4, code.getText());
//         pst.setString(5,CLASSS);
//         pst.setString(6, SUBSTR);
//         pst.setString(7,"0");
//         pst.setDouble(8,USD);
//         pst.setString(9,rolls);
//         
//       SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
//         String addDate = dateFormats.format(jDateChooser1.getDate());
//         pst.setString(10, addDate);
//         pst.setString(11,buss.getSelectedItem().toString());
//         pst.setString(12,libele.getText());
//         pst.setString(13,"[JC]");
//          pst.setString(14, CODE);
//          pst.setDouble(16,CDF);
//            pst.setString(15,"0");
//       
//    pst.setString(17,DEVICEEs);      
//         
//          pst.executeUpdate();
//        
//               //  JOptionPane.showMessageDialog(null,"Caisse saved");
//    } catch (Exception ex) {
//         JOptionPane.showMessageDialog(null, ex.getMessage());
//    }
//      // CAISSE
//       Double xxx,caissesdebit = null,yyy;
//       try{  
//            
//
//
//
////call from journala de banque  
//            String sql="select SUM(DEBIT),SUM(CREDIT) from caisses where buss='"+buss.getSelectedItem()+"'";
//           
//            pst=con.prepareStatement(sql);
//            rs=pst.executeQuery();
//            while(rs.next()){
//                xxx=rs.getDouble("SUM(DEBIT)");
//               // entre.setText(""+xxx);
//                yyy=rs.getDouble("SUM(CREDIT)");
//                  
//                   caissesdebit=(xxx-yyy)+USD;
//                //   sorti.setText(""+yyy);
//            }
//            }catch(Exception ex){ JOptionPane.showMessageDialog(null, ex);} 
//      
//      
//            try {
//         String tmp =jComboBox3.getSelectedItem().toString();
//        PreparedStatement pst = con.prepareStatement("INSERT INTO `caisses`(`CODE`,`CODEMERE`,`LIBELE`,`BUSS`,`DEBIT`,`CREDIT`,`DATE`,`COMPTE`,NUM,`MOIS`, `ANNEE`, `BUDGET`, `SOLDE`,DEVICE)"
//        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
//        
//         pst.setString(1, code.getText());
//         pst.setString(2, jComboBox1.getSelectedItem().toString());
//         pst.setString(3, libele.getText());
//         pst.setString(4, buss.getSelectedItem().toString());
//         pst.setString(6, "0");
//         pst.setDouble(5,USD);
//         pst.setString(8,NAME);
//         pst.setString(9,rolls);
//         
//          SimpleDateFormat dateFormatsS = new SimpleDateFormat("MMM");
//         String addDateS = dateFormatsS.format(jDateChooser1.getDate());
//         pst.setString(10, addDateS);
// 
//         pst.setInt(11,jYearChooser1.getValue());
//         pst.setString(12,codeb.getText());
//         pst.setString(13,"OK");
//       
//          SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
//         String addDate = dateFormats.format(jDateChooser1.getDate());
//         pst.setString(7, addDate);
//         pst.setString(14,jComboBox2.getSelectedItem().toString());
//         
//          pst.executeUpdate();
//        
//                 JOptionPane.showMessageDialog(null,"Caisse saved");
//    } catch (Exception ex) {
//         JOptionPane.showMessageDialog(null, ex.getMessage());
//    }
//         //  save_budgetdebit();
//       }
//       refresh();
//      
//             
           
           }
             
            
        }else{
            //fin Between Cashbook
            // Debut Normal
            call_currency();
//            here
       if(jComboBox3.getSelectedItem().equals("Credit")){
              try{
       
 
            String sql="SELECT   sum(DEBIT),sum(CREDIT) FROM ohada_trans WHERE code='"+jComboBox1.getSelectedItem()+"' AND BUSS='"+buss.getSelectedItem()+"'";
           
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
       
            String sql="SELECT   sum(DEBIT),sum(CREDIT) FROM budget_trans WHERE code='"+codeb.getText()+"' AND PROJET='"+buss.getSelectedItem()+"'";
           
            pst=con.prepareStatement(sql);
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
            jMenu7.setText(""+ECART);
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
   if(CHECK_OHADA <USD){ 
      
   JOptionPane.showMessageDialog(null,"Wrong Data"+"\n"+"No Enough Resources  ","Error",JOptionPane.ERROR_MESSAGE);
  }else if(CHECK_BUDGET >=ECART){ 
      
   JOptionPane.showMessageDialog(null,"Wrong Data"+"\n"+"No Enough Budget ","Error",JOptionPane.ERROR_MESSAGE);
  }else if(cdf.getText().isEmpty()||libele.getText().isEmpty()||code_m.getText().isEmpty()||jComboBox2.getSelectedItem().equals("....")||jComboBox3.getSelectedItem().equals("....")){ 
   JOptionPane.showMessageDialog(null,"Wrong Data"+"\n"+"Wrong Data ","Error",JOptionPane.ERROR_MESSAGE);
  }else if(jComboBox1.getSelectedItem().equals("Select one Caisse")){ 
   JOptionPane.showMessageDialog(null,"Wrong Data"+"\n"+"Select one Caisse","Error",JOptionPane.ERROR_MESSAGE);
  }else if(jComboBox5.getSelectedItem().equals("Batchs")){ 
   JOptionPane.showMessageDialog(null,"Wrong Data"+"\n"+"Select one Batch","Error",JOptionPane.ERROR_MESSAGE);
  }else{ 
               
           // OHADA ACCOUNT
        
        //========>>>>DEBIT
        ///INSERT INTO `ohada_trans`(`ID`, `COMPTE_M`, `COMPTE`, `CODE_M`, `CODE`, `CLASSE`, `SUBSTR`, `DEBIT`, `CREDIT`, `NUM`, `DATES`, `BUSS`, `LIBELLE`, `JOURNAL`)
         try {
         String tmp =jComboBox3.getSelectedItem().toString();
        PreparedStatement pst = con.prepareStatement("INSERT INTO `ohada_trans`(`COMPTE_M`, `COMPTE`, `CODE_M`, `CODE`, `CLASSE`, `SUBSTR`, `DEBIT`, `CREDIT`, `NUM`, `DATES`, `BUSS`, `LIBELLE`, `JOURNAL`, `CODE1`,DEBIT_CDF,CREDIT_CDF,DEVICE,LB)"
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        
        pst.setString(1, COMPTEMERE);
        pst.setString(2, NAME);
         pst.setString(3, CODEMERE);
         pst.setString(4, CODE);
         pst.setString(5,CLASS);
         pst.setString(6, SUBSTRS);
         pst.setDouble(8,USD);
         pst.setString(7,"0");
         pst.setString(9,rolls);
         
       SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(jDateChooser1.getDate());
         pst.setString(10, addDate);
         pst.setString(11,buss.getSelectedItem().toString());
         pst.setString(12,libele.getText());
         pst.setString(13,"[JC]");
          pst.setString(14,code.getText());
           pst.setString(15,"0");
            pst.setDouble(16,CDF);
            pst.setString(17,jComboBox2.getSelectedItem().toString());
            pst.setString(18,codeb.getText());
       
          
         
          pst.executeUpdate();
        
           //      JOptionPane.showMessageDialog(null,"Caisse saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
      
           //========>>>>CREDIT
        ///INSERT INTO `ohada_trans`(`ID`, `COMPTE_M`, `COMPTE`, `CODE_M`, `CODE`, `CLASSE`, `SUBSTR`, `DEBIT`, `CREDIT`, `NUM`, `DATES`, `BUSS`, `LIBELLE`, `JOURNAL`)
         try {
         String tmp =jComboBox3.getSelectedItem().toString();
        PreparedStatement pst = con.prepareStatement("INSERT INTO `ohada_trans`(`COMPTE_M`, `COMPTE`, `CODE_M`, `CODE`, `CLASSE`, `SUBSTR`, `DEBIT`, `CREDIT`, `NUM`, `DATES`, `BUSS`, `LIBELLE`, `JOURNAL`, `CODE1`,DEBIT_CDF,CREDIT_CDF,DEVICE,LB)"
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        
        pst.setString(1, compte1.getText());
        pst.setString(2, compte.getText());
         pst.setString(3, code_m.getText());
         pst.setString(4, code.getText());
         pst.setString(5,CLASSS);
         pst.setString(6, SUBSTR);
         pst.setString(8,"0");
         pst.setDouble(7,USD);
         pst.setString(9,rolls);
         
       SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(jDateChooser1.getDate());
         pst.setString(10, addDate);
         pst.setString(11,buss.getSelectedItem().toString());
         pst.setString(12,libele.getText());
         pst.setString(13,"[JC]");
          pst.setString(14, CODE);
          pst.setDouble(15,CDF);
            pst.setString(16,"0");
     pst.setString(17,jComboBox2.getSelectedItem().toString());  
     pst.setString(18,codeb.getText());
          
         
          pst.executeUpdate();
        
               //  JOptionPane.showMessageDialog(null,"Caisse saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
      // CAISSE
       Double xxx,caissesdebit = null,yyy;
       try{  
            



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
       
       ////Caisse comptable
       try {
         String tmp =jComboBox3.getSelectedItem().toString();
        PreparedStatement pst = con.prepareStatement("INSERT INTO `caisses`(`CODE`,`CODEMERE`,`LIBELE`,`BUSS`,`DEBIT`,`CREDIT`,`DATE`,`COMPTE`,NUM,`MOIS`, `ANNEE`, `BUDGET`, `SOLDE`, `DEVICE`,PRINT)"
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        
        pst.setString(1, code.getText());
         pst.setString(2, jComboBox1.getSelectedItem().toString());
         pst.setString(3, libele.getText());
         pst.setString(4, buss.getSelectedItem().toString());
         pst.setString(5, "0");
         pst.setDouble(6,USD);
         pst.setString(8,NAME);
         pst.setString(9,rolls);
         
          SimpleDateFormat dateFormatsS = new SimpleDateFormat("MMM");
         String addDateS = dateFormatsS.format(jDateChooser1.getDate());
         pst.setString(10, addDateS);
 
         pst.setInt(11,jYearChooser1.getValue());
         pst.setString(12,codeb.getText());
         pst.setString(13,"OK");
       
          SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(jDateChooser1.getDate());
         pst.setString(7, addDate);
         pst.setString(14,jComboBox2.getSelectedItem().toString());
           pst.setString(15,"No");
         
          pst.executeUpdate();
        
                 JOptionPane.showMessageDialog(null,"Caisse saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
        ////Caisse Print
       try {
         String tmp =jComboBox3.getSelectedItem().toString();
         PreparedStatement pst = con.prepareStatement("INSERT INTO `caisses`(`CODE`,`CODEMERE`,`LIBELE`,`BUSS`,`DEBIT`,`CREDIT`,`DATE`,`COMPTE`,NUM,`MOIS`, `ANNEE`, `BUDGET`, `SOLDE`, `DEVICE`,PRINT)"
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        
        pst.setString(1, code.getText());
         pst.setString(2, jComboBox1.getSelectedItem().toString());
         pst.setString(3, libele.getText());
         pst.setString(4, buss.getSelectedItem().toString());
         pst.setString(5, "0");
         pst.setDouble(6,PR);
         pst.setString(8,NAME);
         pst.setString(9,rolls);
         
          SimpleDateFormat dateFormatsS = new SimpleDateFormat("MMM");
         String addDateS = dateFormatsS.format(jDateChooser1.getDate());
         pst.setString(10, addDateS);
 
         pst.setInt(11,jYearChooser1.getValue());
         pst.setString(12,codeb.getText());
         pst.setString(13,"");
       
          SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(jDateChooser1.getDate());
         pst.setString(7, addDate);
         pst.setString(14,jComboBox2.getSelectedItem().toString());
           pst.setString(15,"Yes");
         
          pst.executeUpdate();
        
       //          JOptionPane.showMessageDialog(null,"Caisse saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
       save_budget();
           refresh();
           }
       
           }else if(jComboBox3.getSelectedItem().equals("Debit")){
               
             /*   try{
            String sql="SELECT   sum(DEBIT),sum(CREDIT) FROM budget_trans WHERE code='"+codeb.getText()+"' AND PROJET='"+buss.getSelectedItem()+"'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
              SUMDEBIT=rs.getDouble("sum(DEBIT)");
              SUMCREDIT=rs.getDouble("sum(CREDIT)");
             
               CHECK_BUDGET=SUMCREDIT-SUMDEBIT;
                
                
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
     */
     
            try{  
             Double xxx;
Double yyy;


//call from journala de banque  
            String sql="select SUM(DEBIT),SUM(CREDIT) from ohada_trans where code='"+code.getText()+"' and BUSS='"+buss.getSelectedItem()+"'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                xxx=rs.getDouble("SUM(DEBIT)");
               // entre.setText(""+xxx);
                yyy=rs.getDouble("SUM(CREDIT)");
                  
                   CHECK_OHADA=(xxx-yyy);
                 
            }
            }catch(Exception ex){ JOptionPane.showMessageDialog(null, ex);}
            
       
       if(CHECK_OHADA < USD){ 
      
   JOptionPane.showMessageDialog(null,"Wrong Data"+"\n"+"No Enough Resources   ","Error",JOptionPane.ERROR_MESSAGE);
  }else if(cdf.getText().isEmpty()||libele.getText().isEmpty()||code_m.getText().isEmpty()||jComboBox2.getSelectedItem().equals("....")||jComboBox3.getSelectedItem().equals("....")){ 
   JOptionPane.showMessageDialog(null,"Wrong Data"+"\n"+"Wrong Data ","Error",JOptionPane.ERROR_MESSAGE);
  }else if(jComboBox1.getSelectedItem().equals("Select one Caisse")){ 
   JOptionPane.showMessageDialog(null,"Wrong Data"+"\n"+"Select one Caisse","Error",JOptionPane.ERROR_MESSAGE);
  }else{ 
    
      // OHADA ACCOUNT 
        
        //========>>>>DEBIT
        ///INSERT INTO `ohada_trans`(`ID`, `COMPTE_M`, `COMPTE`, `CODE_M`, `CODE`, `CLASSE`, `SUBSTR`, `DEBIT`, `CREDIT`, `NUM`, `DATES`, `BUSS`, `LIBELLE`, `JOURNAL`)
          String DEVICEE = null;
    try{
            String sql="select * from monais WHERE caisse='"+jComboBox1.getSelectedItem()+"'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            if(rs.next()){
          DEVICEE=rs.getString("DEVICE");  
          //  jComboBox2.setSelectedItem(DEVICE);
            }
        //  con.close();
            }
        
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex); }
 
        
        
        try {
        // String tmp =jComboBox3.getSelectedItem().toString();
         PreparedStatement pst = con.prepareStatement("INSERT INTO `ohada_trans`(`COMPTE_M`, `COMPTE`, `CODE_M`, `CODE`, `CLASSE`, `SUBSTR`, `DEBIT`, `CREDIT`, `NUM`, `DATES`, `BUSS`, `LIBELLE`, `JOURNAL`, `CODE1`,DEBIT_CDF,CREDIT_CDF,DEVICE,LB)"
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        
        pst.setString(1, COMPTEMERE);
        pst.setString(2, NAME);
         pst.setString(3, CODEMERE);
         pst.setString(4, CODE);
         pst.setString(5,CLASS);
         pst.setString(6, SUBSTRS);
         pst.setDouble(7,USD);
         pst.setString(8,"0");
         pst.setString(9,rolls);
         
       SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(jDateChooser1.getDate());
         pst.setString(10, addDate);
         pst.setString(11,buss.getSelectedItem().toString());
         pst.setString(12,libele.getText());
         pst.setString(13,"OK");
          pst.setString(14,code.getText());
           pst.setString(16,"0");
            pst.setDouble(15,CDF);
       pst.setString(17, DEVICEE);
       pst.setString(18,codeb.getText());
          
         
          pst.executeUpdate();
        
           //      JOptionPane.showMessageDialog(null,"Caisse saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
      
           //========>>>>CREDIT
        ///INSERT INTO `ohada_trans`(`ID`, `COMPTE_M`, `COMPTE`, `CODE_M`, `CODE`, `CLASSE`, `SUBSTR`, `DEBIT`, `CREDIT`, `NUM`, `DATES`, `BUSS`, `LIBELLE`, `JOURNAL`)
        
        
        //>>>>>DEVICE HERE
        String DEVICEEs = null;
    try{
            String sql="select * from monais WHERE caisse='"+code.getText()+"'";
           
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

        
        try {
         String tmp =jComboBox3.getSelectedItem().toString();
         PreparedStatement pst = con.prepareStatement("INSERT INTO `ohada_trans`(`COMPTE_M`, `COMPTE`, `CODE_M`, `CODE`, `CLASSE`, `SUBSTR`, `DEBIT`, `CREDIT`, `NUM`, `DATES`, `BUSS`, `LIBELLE`, `JOURNAL`, `CODE1`,DEBIT_CDF,CREDIT_CDF,DEVICE,LB)"
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        
        pst.setString(1, compte1.getText());
        pst.setString(2, compte.getText());
         pst.setString(3, code_m.getText());
         pst.setString(4, code.getText());
         pst.setString(5,CLASSS);
         pst.setString(6, SUBSTR);
         pst.setString(7,"0");
         pst.setDouble(8,USD);
         pst.setString(9,rolls);
         
       SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(jDateChooser1.getDate());
         pst.setString(10, addDate);
         pst.setString(11,buss.getSelectedItem().toString());
         pst.setString(12,libele.getText());
         pst.setString(13,"[JC]");
          pst.setString(14, CODE);
          pst.setDouble(16,CDF);
            pst.setString(15,"0");
       
    pst.setString(17,DEVICEEs);   
    pst.setString(18,codeb.getText());
         
          pst.executeUpdate();
        
               //  JOptionPane.showMessageDialog(null,"Caisse saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
      // CAISSE
       Double xxx,caissesdebit = null,yyy;
       try{  
            



//call from journala de banque  
            String sql="select SUM(DEBIT),SUM(CREDIT) from caisses where buss='"+buss.getSelectedItem()+"'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                xxx=rs.getDouble("SUM(DEBIT)");
               // entre.setText(""+xxx);
                yyy=rs.getDouble("SUM(CREDIT)");
                  
                   caissesdebit=(xxx-yyy)+USD;
                //   sorti.setText(""+yyy);
            }
            }catch(Exception ex){ JOptionPane.showMessageDialog(null, ex);} 
      
       if(lb.getText().equals("Remboursement Pref-01")){
           try {
         String tmp =jComboBox3.getSelectedItem().toString();
        PreparedStatement pst = con.prepareStatement("INSERT INTO `caisses`(`CODE`,`CODEMERE`,`LIBELE`,`BUSS`,`DEBIT`,`CREDIT`,`DATE`,`COMPTE`,NUM,`MOIS`, `ANNEE`, `BUDGET`, `SOLDE`, `DEVICE`,PRINT)"
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        
        pst.setString(1, code.getText());
         pst.setString(2, jComboBox1.getSelectedItem().toString());
         pst.setString(3, libele.getText());
         pst.setString(4, buss.getSelectedItem().toString());
         pst.setString(5, "0");
         pst.setDouble(6,USD);
         pst.setString(8,NAME);
         pst.setString(9,rolls);
         
          SimpleDateFormat dateFormatsS = new SimpleDateFormat("MMM");
         String addDateS = dateFormatsS.format(jDateChooser1.getDate());
         pst.setString(10, addDateS);
 
         pst.setInt(11,jYearChooser1.getValue());
         pst.setString(12,codeb.getText());
         pst.setString(13,"OK");
       
          SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(jDateChooser1.getDate());
         pst.setString(7, addDate);
         pst.setString(14,jComboBox2.getSelectedItem().toString());
           pst.setString(15,"Yes");
         
          pst.executeUpdate();
        
                 JOptionPane.showMessageDialog(null,"Caisse saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
  // juste pour essayer // save_budget_Pref();
     refresh();
       }else{
            try {
         String tmp =jComboBox3.getSelectedItem().toString();
        PreparedStatement pst = con.prepareStatement("INSERT INTO `caisses`(`CODE`,`CODEMERE`,`LIBELE`,`BUSS`,`DEBIT`,`CREDIT`,`DATE`,`COMPTE`,NUM,`MOIS`, `ANNEE`, `BUDGET`, `SOLDE`,DEVICE,PRINT)"
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        
         pst.setString(1, code.getText());
         pst.setString(2, jComboBox1.getSelectedItem().toString());
         pst.setString(3, libele.getText());
         pst.setString(4, buss.getSelectedItem().toString());
         pst.setString(6, "0");
         pst.setDouble(5,USD);
         pst.setString(8,NAME);
         pst.setString(9,rolls);
         
          SimpleDateFormat dateFormatsS = new SimpleDateFormat("MMM");
         String addDateS = dateFormatsS.format(jDateChooser1.getDate());
         pst.setString(10, addDateS);
 
         pst.setInt(11,jYearChooser1.getValue());
         pst.setString(12,codeb.getText());
         pst.setString(13,"OK");
       
          SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(jDateChooser1.getDate());
         pst.setString(7, addDate);
         pst.setString(14,jComboBox2.getSelectedItem().toString());
           pst.setString(15,"Yes");
         
          pst.executeUpdate();
        
                 JOptionPane.showMessageDialog(null,"Caisse saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
         //  save_budgetdebit();
       }
       refresh();
      
      }         
           
           
           }
      }
              
          
      
      }
      Double sumcredit;  
   Double sumcredits;  
   
   // Double caissesdebit; 
    // Double soldecaisses; 
public void call_sum_budget(){
Double a;
Double b;
Double aa;
Double ba;
Double c= Double.parseDouble(amount.getText());

 try{              //call from journala de banque  
            String sql="select SUM(DEBIT),SUM(CREDIT) from  budget_trans where CODE= '"+codeb.getText()+"' and projet='"+buss.getSelectedItem()+"'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                a=rs.getDouble("SUM(DEBIT)");
                b=rs.getDouble("SUM(CREDIT)");
                  
                   sumcredit=a-b;
                   sumcredits=c+b;
                    sumcredit=a-sumcredits;
                 
            //jTextField4.setText(d);
            }
            }catch(Exception ex){ JOptionPane.showMessageDialog(null, ex);} 
            

}


       public void save_budget(){
                 SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(jDateChooser1.getDate());
           call_currency();
           SELECT_buss();
             
 call_sum_budget();
        Double aaa= Double.parseDouble(amount.getText());
        Double ccc;
        ccc= sumcredit;
     
          try{
    String sql="INSERT INTO `budget_trans`(`ITEM`, `DEBIT`, `CREDIT`, `SOLD`, `PROJET`, `CODE`, `NUM`, `CAT`, `DATES`, `MOIS`, `ANNEE`,SUB_CAT,CODE_CAT,CODE_SUB_CAT,ITEMS,BATCH) "+"values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    pst=con.prepareStatement(sql);
    pst.setString(1,libele.getText());
    pst.setString(2,"0");
    pst.setDouble(3,USD);
    pst.setDouble(4, ccc);
    pst.setString(5,buss.getSelectedItem().toString());
    pst.setString(6,jList1.getSelectedValue());
    pst.setString(7,rolls);
    
    pst.setString(8,CATB);
    
    pst.setString(9,addDate);
    SimpleDateFormat dateFormatsS = new SimpleDateFormat("MMM");
         String addDateS = dateFormatsS.format(jDateChooser1.getDate());
         pst.setString(10, addDateS);
 
         pst.setInt(11,jYearChooser1.getValue());
    pst.setString (12,SUB_CATB);
    
     pst.setString(13,code_cat.getText());
      pst.setString(14,code_subcat.getText());
      
        pst.setString(15,lb.getText());
        pst.setString(16,jComboBox5.getSelectedItem().toString());
   
    
    
    pst.executeUpdate();
    // JOptionPane.showMessageDialog(null,"BUDGET TRANS");
    }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}
    
            try {
         Double CREDIT = null,SOLD = null;
         try{              //call from journala de banque  
            String sql="select SUM(DEBIT),SUM(CREDIT) from  budget_show where CODE= '"+codeb.getText()+"' and projet='"+buss.getSelectedItem()+"'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
               Double a=rs.getDouble("SUM(DEBIT)");
               Double b=rs.getDouble("SUM(CREDIT)");
               // Double c=Double.parseDouble(amount.getText());
                   CREDIT=b+USD;
                 SOLD=a-CREDIT;
            //jTextField4.setText(d);
            }
            }catch(Exception ex){ JOptionPane.showMessageDialog(null, ex);} 
        PreparedStatement pst = con.prepareStatement("UPDATE `budget_show` SET `CREDIT`='"+CREDIT+"',`SOLD`='"+SOLD+"' WHERE code='"+codeb.getText()+"' and projet='"+buss.getSelectedItem()+"'");
      pst.executeUpdate();
      
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());}
           
           }
       
        public void save_budgetdebit(){
             SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(jDateChooser1.getDate());
           call_currency();
           SELECT_buss();
             Double debit,credit = null,cccc = null;
  try{              //call from journala de banque  
            String sql="select SUM(DEBIT),SUM(CREDIT) from  budget_trans where CODE= '"+codeb.getText()+"' and projet='"+buss.getSelectedItem()+"'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
               Double a=rs.getDouble("SUM(DEBIT)");
               Double b=rs.getDouble("SUM(CREDIT)");
                Double c=Double.parseDouble(amount.getText());
                   credit=b-c;
                 cccc=a-credit;
            //jTextField4.setText(d);
            }
            }catch(Exception ex){ JOptionPane.showMessageDialog(null, ex);} 
     
          try{
    String sql="INSERT INTO `budget_trans`(`ITEM`, `DEBIT`, `CREDIT`, `SOLD`, `PROJET`, `CODE`, `NUM`, `CAT`, `DATES`, `MOIS`, `ANNEE`,SUB_CAT,CODE_CAT,CODE_SUB_CAT,`ITEMS`) "+"values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    pst=con.prepareStatement(sql);
    pst.setString(1,libele.getText());
    pst.setString(3,"0");
    pst.setDouble(2,USD);
    pst.setDouble(4, cccc);
    pst.setString(5,buss.getSelectedItem().toString());
    pst.setString(6,jList1.getSelectedValue());
    pst.setString(7,rolls);
    
    pst.setString(8,code.getText());
   
    pst.setString(9,addDate);
    SimpleDateFormat dateFormatsS = new SimpleDateFormat("MMM");
         String addDateS = dateFormatsS.format(jDateChooser1.getDate());
         pst.setString(10, addDateS);
 
         pst.setInt(11,jYearChooser1.getValue());
    pst.setString (12,COMPTEMERE);
    
     pst.setString(13,code_cat.getText());
      pst.setString(14,code_subcat.getText());
      
      pst.setString(15,lb.getText());
   
    
    
    pst.executeUpdate();
    // JOptionPane.showMessageDialog(null,"BUDGET TRANS");
    }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}
    
            try {
                Double CREDIT = null,SOLD = null;
         try{              //call from journala de banque  
            String sql="select SUM(DEBIT),SUM(CREDIT) from  budget_show where CODE= '"+codeb.getText()+"' and projet='"+buss.getSelectedItem()+"'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
               Double a=rs.getDouble("SUM(DEBIT)");
               Double b=rs.getDouble("SUM(CREDIT)");
                Double c=Double.parseDouble(amount.getText());
                   CREDIT=b-c;
                 SOLD=a-CREDIT;
            //jTextField4.setText(d);
            }
            }catch(Exception ex){ JOptionPane.showMessageDialog(null, ex);} 
        PreparedStatement pst = con.prepareStatement("UPDATE `budget_show` SET `CREDIT`='"+CREDIT+"',`SOLD`='"+SOLD+"' WHERE code='"+codeb.getText()+"' and projet='"+buss.getSelectedItem()+"'");
      pst.executeUpdate();
      
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());}
           
           }
        
        public void save_budgetdebit_usd_import(){
             SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(jDateChooser1.getDate());
           call_currency();
           SELECT_buss();
             Double debit,credit = null,cccc = null;
  try{              //call from journala de banque  
            String sql="select SUM(DEBIT),SUM(CREDIT) from  budget_trans where CODE= '"+codeb.getText()+"' and projet='"+buss.getSelectedItem()+"'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
               Double a=rs.getDouble("SUM(DEBIT)");
               Double b=rs.getDouble("SUM(CREDIT)");
                Double c=Double.parseDouble(amount.getText());
                   credit=b-c;
                 cccc=a-credit;
            //jTextField4.setText(d);
            }
            }catch(Exception ex){ JOptionPane.showMessageDialog(null, ex);} 
     
          try{
    String sql="INSERT INTO `budget_trans`(`ITEM`, `DEBIT`, `CREDIT`, `SOLD`, `PROJET`, `CODE`, `NUM`, `CAT`, `DATES`, `MOIS`, `ANNEE`,SUB_CAT,CODE_CAT,CODE_SUB_CAT,`ITEMS`) "+"values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    pst=con.prepareStatement(sql);
    pst.setString(1,libele.getText());
    pst.setString(3,"0");
    pst.setDouble(2,USD);
    pst.setDouble(4, cccc);
    pst.setString(5,buss.getSelectedItem().toString());
    pst.setString(6,jList1.getSelectedValue());
    pst.setString(7,rolls);
    
    pst.setString(8,code.getText());
   
    pst.setString(9,addDate);
    SimpleDateFormat dateFormatsS = new SimpleDateFormat("MMM");
         String addDateS = dateFormatsS.format(jDateChooser1.getDate());
         pst.setString(10, addDateS);
 
         pst.setInt(11,jYearChooser1.getValue());
    pst.setString (12,COMPTEMERE);
    
     pst.setString(13,code_cat.getText());
      pst.setString(14,code_subcat.getText());
      
      pst.setString(15,lb.getText());
   
    
    
    pst.executeUpdate();
    // JOptionPane.showMessageDialog(null,"BUDGET TRANS");
    }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}
    
           
           }
        
          public void save_budget_Pref(){
                 SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(jDateChooser1.getDate());
           call_currency();
           SELECT_buss();
             
 call_sum_budget();
        Double aaa= Double.parseDouble(amount.getText());
        Double ccc;
        ccc= sumcredit;
     
          try{
    String sql="INSERT INTO `budget_trans`(`ITEM`, `DEBIT`, `CREDIT`, `SOLD`, `PROJET`, `CODE`, `NUM`, `CAT`, `DATES`, `MOIS`, `ANNEE`,SUB_CAT,CODE_CAT,CODE_SUB_CAT,`ITEMS`) "+"values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    pst=con.prepareStatement(sql);
    pst.setString(1,libele.getText());
    pst.setString(2,"0");
    pst.setDouble(3,USD);
    pst.setDouble(4, ccc);
    pst.setString(5,buss.getSelectedItem().toString());
    pst.setString(6,jList1.getSelectedValue());
    pst.setString(7,rolls);
    
    pst.setString(8,code_cat.getText());
    
    pst.setString(9,addDate);
    SimpleDateFormat dateFormatsS = new SimpleDateFormat("MMM");
         String addDateS = dateFormatsS.format(jDateChooser1.getDate());
         pst.setString(10, addDateS);
 
         pst.setInt(11,jYearChooser1.getValue());
    pst.setString (12,code_cat.getText());
    
     pst.setString(13,code_cat.getText());
      pst.setString(14,code_subcat.getText());
      
        pst.setString(15,lb.getText());
   
    
    
    pst.executeUpdate();
    // JOptionPane.showMessageDialog(null,"BUDGET TRANS");
    }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}
  
           
           }
          
        
              public void search2BUS()
             {
                // dddd
                  SimpleDateFormat days = new SimpleDateFormat("yyyy-MM-dd");
         String day = days.format(jDateChooser1.getDate());
         
         SimpleDateFormat moiss = new SimpleDateFormat("YYYY-MM");
         String mois = moiss.format(jDateChooser1.getDate());
         
         SimpleDateFormat years = new SimpleDateFormat("YYYY");
         String year = years.format(jDateChooser1.getDate());
         
                String DEVICE = null; 
                 try{
            String sql="select * from monais WHERE caisse='"+jComboBox1.getSelectedItem()+"'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            if(rs.next()){
            DEVICE=rs.getString("DEVICE");      
            }
           if(jComboBox4.getSelectedItem().equals("Day")){
              //  JOptionPane.showMessageDialog(null, day);
       if(DEVICE.equals("USD")){
                try{
            // `COMPTE_M`, `COMPTE`, `CODE_M`, `CODE`, `CLASSE`, `SUBSTR`, `DEBIT`, `CREDIT`, `NUM`, `DATES`, `BUSS`, `LIBELLE`, `JOURNAL`, `CODE1`, `NUM_FACTURE`, `DEBIT_CDF`, `CREDIT_CDF`    
             String tmp=buss.getSelectedItem().toString();
    String sqlS="select `NUM`, `CODE1` as 'CODE',`LIBELLE` AS 'DESCRIPTION OF TRANSACTION',CREDIT AS DEBIT,DEBIT AS CREDIT, `DATES` from OHADA_TRANS where BUSS = '"+tmp+"' AND CODE='"+jComboBox1.getSelectedItem()+"' AND DEVICE='"+jComboBox2.getSelectedItem()+"' and DATES='"+day+"' order by DATES,NUM";
    //`LIBELLE`, `ENTRE`, `SORTIE`, `DATE`, `BUSS`, `USED`, `NUM`
    pst = con.prepareStatement(sqlS);
      rs= pst.executeQuery();
     
       jTable1.setModel(DbUtils.resultSetToTableModel(rs));
    
             }catch(Exception ex){
                 JOptionPane.showMessageDialog(null, ex);
             }
                 DefaultTableCellRenderer centre =new DefaultTableCellRenderer();
        centre.setHorizontalAlignment(JLabel.CENTER);
         TableColumn col0=jTable1.getColumnModel().getColumn(0);
        TableColumn col1=jTable1.getColumnModel().getColumn(1);
          jTable1.getColumnModel().getColumn(1).setCellRenderer(centre);
        TableColumn col2=jTable1.getColumnModel().getColumn(2);
        TableColumn col3=jTable1.getColumnModel().getColumn(3);
          //centre.setHorizontalAlignment(JLabel.CENTER);
            jTable1.getColumnModel().getColumn(3).setCellRenderer(centre);
        TableColumn col4=jTable1.getColumnModel().getColumn(4);
         // centre.setHorizontalAlignment(JLabel.CENTER);
         
            jTable1.getColumnModel().getColumn(4).setCellRenderer(centre);
            //jTable1.getColumnModel().getColumn(4).;
           TableColumn col5=jTable1.getColumnModel().getColumn(5);
           // TableColumn col6=jTable1.getColumnModel().getColumn(6);
      
       
       col0.setPreferredWidth(60);
       col1.setPreferredWidth(20);
       col2.setPreferredWidth(370);
       col3.setPreferredWidth(20);
       col4.setPreferredWidth(20);
       col5.setPreferredWidth(20);
            
            }else{
                 try{
            // `COMPTE_M`, `COMPTE`, `CODE_M`, `CODE`, `CLASSE`, `SUBSTR`, `DEBIT`, `CREDIT`, `NUM`, `DATES`, `BUSS`, `LIBELLE`, `JOURNAL`, `CODE1`, `NUM_FACTURE`, `DEBIT_CDF`, `CREDIT_CDF`    
             String tmp=buss.getSelectedItem().toString();
    String sqls="select `NUM`, `CODE1` as 'CODE',`LIBELLE` AS 'DESCRIPTION OF TRANSACTION',CREDIT_CDF AS DEBIT,DEBIT_CDF AS CREDIT, `DATES` from OHADA_TRANS where BUSS = '"+tmp+"' AND CODE='"+jComboBox1.getSelectedItem()+"' AND DEVICE='"+jComboBox2.getSelectedItem()+"' and DATES='"+day+"' order by DATES,NUM";
    //`LIBELLE`, `ENTRE`, `SORTIE`, `DATE`, `BUSS`, `USED`, `NUM`
    pst = con.prepareStatement(sqls);
      rs= pst.executeQuery();
     
       jTable1.setModel(DbUtils.resultSetToTableModel(rs));
    
             }catch(Exception ex){
                 JOptionPane.showMessageDialog(null, ex);
             }
                 DefaultTableCellRenderer centre =new DefaultTableCellRenderer();
        centre.setHorizontalAlignment(JLabel.CENTER);
         TableColumn col0=jTable1.getColumnModel().getColumn(0);
        TableColumn col1=jTable1.getColumnModel().getColumn(1);
          jTable1.getColumnModel().getColumn(1).setCellRenderer(centre);
        TableColumn col2=jTable1.getColumnModel().getColumn(2);
        TableColumn col3=jTable1.getColumnModel().getColumn(3);
          //centre.setHorizontalAlignment(JLabel.CENTER);
            jTable1.getColumnModel().getColumn(3).setCellRenderer(centre);
        TableColumn col4=jTable1.getColumnModel().getColumn(4);
         // centre.setHorizontalAlignment(JLabel.CENTER);
            jTable1.getColumnModel().getColumn(4).setCellRenderer(centre);
           TableColumn col5=jTable1.getColumnModel().getColumn(5);
           // TableColumn col6=jTable1.getColumnModel().getColumn(6);
      
       
       col0.setPreferredWidth(60);
       col1.setPreferredWidth(20);
       col2.setPreferredWidth(370);
       col3.setPreferredWidth(20);
       col4.setPreferredWidth(20);
       col5.setPreferredWidth(20);    
            }
            
            
            }else if(jComboBox4.getSelectedItem().equals("Month")){
            //    JOptionPane.showMessageDialog(null, mois);
            
                   if(DEVICE.equals("USD")){
                try{
            // `COMPTE_M`, `COMPTE`, `CODE_M`, `CODE`, `CLASSE`, `SUBSTR`, `DEBIT`, `CREDIT`, `NUM`, `DATES`, `BUSS`, `LIBELLE`, `JOURNAL`, `CODE1`, `NUM_FACTURE`, `DEBIT_CDF`, `CREDIT_CDF`    
             String tmp=buss.getSelectedItem().toString();
    String sqlS="select `NUM`, `CODE1` as 'CODE',`LIBELLE` AS 'DESCRIPTION OF TRANSACTION',CREDIT AS DEBIT,DEBIT AS CREDIT, `DATES` from OHADA_TRANS where BUSS = '"+tmp+"' AND CODE='"+jComboBox1.getSelectedItem()+"' AND DEVICE='"+jComboBox2.getSelectedItem()+"' and  SUBSTR(DATES,1,7)='"+mois+"' order by DATES,NUM";
    //`LIBELLE`, `ENTRE`, `SORTIE`, `DATE`, `BUSS`, `USED`, `NUM`
    pst = con.prepareStatement(sqlS);
      rs= pst.executeQuery();
     
       jTable1.setModel(DbUtils.resultSetToTableModel(rs));
    
             }catch(Exception ex){
                 JOptionPane.showMessageDialog(null, ex);
             }
                 DefaultTableCellRenderer centre =new DefaultTableCellRenderer();
        centre.setHorizontalAlignment(JLabel.CENTER);
         TableColumn col0=jTable1.getColumnModel().getColumn(0);
        TableColumn col1=jTable1.getColumnModel().getColumn(1);
          jTable1.getColumnModel().getColumn(1).setCellRenderer(centre);
        TableColumn col2=jTable1.getColumnModel().getColumn(2);
        TableColumn col3=jTable1.getColumnModel().getColumn(3);
          //centre.setHorizontalAlignment(JLabel.CENTER);
            jTable1.getColumnModel().getColumn(3).setCellRenderer(centre);
        TableColumn col4=jTable1.getColumnModel().getColumn(4);
         // centre.setHorizontalAlignment(JLabel.CENTER);
         
            jTable1.getColumnModel().getColumn(4).setCellRenderer(centre);
            //jTable1.getColumnModel().getColumn(4).;
           TableColumn col5=jTable1.getColumnModel().getColumn(5);
           // TableColumn col6=jTable1.getColumnModel().getColumn(6);
      
       
       col0.setPreferredWidth(60);
       col1.setPreferredWidth(20);
       col2.setPreferredWidth(370);
       col3.setPreferredWidth(20);
       col4.setPreferredWidth(20);
       col5.setPreferredWidth(20);
            
            }else{
                 try{
            // `COMPTE_M`, `COMPTE`, `CODE_M`, `CODE`, `CLASSE`, `SUBSTR`, `DEBIT`, `CREDIT`, `NUM`, `DATES`, `BUSS`, `LIBELLE`, `JOURNAL`, `CODE1`, `NUM_FACTURE`, `DEBIT_CDF`, `CREDIT_CDF`    
             String tmp=buss.getSelectedItem().toString();
    String sqls="select `NUM`, `CODE1` as 'CODE',`LIBELLE` AS 'DESCRIPTION OF TRANSACTION',CREDIT_CDF AS DEBIT,DEBIT_CDF AS CREDIT, `DATES` from OHADA_TRANS where BUSS = '"+tmp+"' AND CODE='"+jComboBox1.getSelectedItem()+"' AND DEVICE='"+jComboBox2.getSelectedItem()+"' and  SUBSTR(DATES,1,7)='"+mois+"' order by DATES,NUM";
    //`LIBELLE`, `ENTRE`, `SORTIE`, `DATE`, `BUSS`, `USED`, `NUM`
    pst = con.prepareStatement(sqls);
      rs= pst.executeQuery();
     
       jTable1.setModel(DbUtils.resultSetToTableModel(rs));
    
             }catch(Exception ex){
                 JOptionPane.showMessageDialog(null, ex);
             }
                 DefaultTableCellRenderer centre =new DefaultTableCellRenderer();
        centre.setHorizontalAlignment(JLabel.CENTER);
         TableColumn col0=jTable1.getColumnModel().getColumn(0);
        TableColumn col1=jTable1.getColumnModel().getColumn(1);
          jTable1.getColumnModel().getColumn(1).setCellRenderer(centre);
        TableColumn col2=jTable1.getColumnModel().getColumn(2);
        TableColumn col3=jTable1.getColumnModel().getColumn(3);
          //centre.setHorizontalAlignment(JLabel.CENTER);
            jTable1.getColumnModel().getColumn(3).setCellRenderer(centre);
        TableColumn col4=jTable1.getColumnModel().getColumn(4);
         // centre.setHorizontalAlignment(JLabel.CENTER);
            jTable1.getColumnModel().getColumn(4).setCellRenderer(centre);
           TableColumn col5=jTable1.getColumnModel().getColumn(5);
           // TableColumn col6=jTable1.getColumnModel().getColumn(6);
      
       
       col0.setPreferredWidth(60);
       col1.setPreferredWidth(20);
       col2.setPreferredWidth(370);
       col3.setPreferredWidth(20);
       col4.setPreferredWidth(20);
       col5.setPreferredWidth(20);    
            }
            
            
            }else{
          //      JOptionPane.showMessageDialog(null, year);
              if(DEVICE.equals("USD")){
                try{
            // `COMPTE_M`, `COMPTE`, `CODE_M`, `CODE`, `CLASSE`, `SUBSTR`, `DEBIT`, `CREDIT`, `NUM`, `DATES`, `BUSS`, `LIBELLE`, `JOURNAL`, `CODE1`, `NUM_FACTURE`, `DEBIT_CDF`, `CREDIT_CDF`    
             String tmp=buss.getSelectedItem().toString();
    String sqlS="select `NUM`, `CODE1` as 'CODE',`LIBELLE` AS 'DESCRIPTION OF TRANSACTION',CREDIT AS DEBIT,DEBIT AS CREDIT, `DATES` from OHADA_TRANS where BUSS = '"+tmp+"' AND CODE='"+jComboBox1.getSelectedItem()+"' AND DEVICE='"+jComboBox2.getSelectedItem()+"' and  SUBSTR(DATES,1,4)='"+year+"' order by DATES,NUM";
    //`LIBELLE`, `ENTRE`, `SORTIE`, `DATE`, `BUSS`, `USED`, `NUM`
    pst = con.prepareStatement(sqlS);
      rs= pst.executeQuery();
     
       jTable1.setModel(DbUtils.resultSetToTableModel(rs));
    
             }catch(Exception ex){
                 JOptionPane.showMessageDialog(null, ex);
             }
                 DefaultTableCellRenderer centre =new DefaultTableCellRenderer();
        centre.setHorizontalAlignment(JLabel.CENTER);
         TableColumn col0=jTable1.getColumnModel().getColumn(0);
        TableColumn col1=jTable1.getColumnModel().getColumn(1);
          jTable1.getColumnModel().getColumn(1).setCellRenderer(centre);
        TableColumn col2=jTable1.getColumnModel().getColumn(2);
        TableColumn col3=jTable1.getColumnModel().getColumn(3);
          //centre.setHorizontalAlignment(JLabel.CENTER);
            jTable1.getColumnModel().getColumn(3).setCellRenderer(centre);
        TableColumn col4=jTable1.getColumnModel().getColumn(4);
         // centre.setHorizontalAlignment(JLabel.CENTER);
         
            jTable1.getColumnModel().getColumn(4).setCellRenderer(centre);
            //jTable1.getColumnModel().getColumn(4).;
           TableColumn col5=jTable1.getColumnModel().getColumn(5);
           // TableColumn col6=jTable1.getColumnModel().getColumn(6);
      
       
       col0.setPreferredWidth(60);
       col1.setPreferredWidth(20);
       col2.setPreferredWidth(370);
       col3.setPreferredWidth(20);
       col4.setPreferredWidth(20);
       col5.setPreferredWidth(20);
            
            }else{
                 try{
            // `COMPTE_M`, `COMPTE`, `CODE_M`, `CODE`, `CLASSE`, `SUBSTR`, `DEBIT`, `CREDIT`, `NUM`, `DATES`, `BUSS`, `LIBELLE`, `JOURNAL`, `CODE1`, `NUM_FACTURE`, `DEBIT_CDF`, `CREDIT_CDF`    
             String tmp=buss.getSelectedItem().toString();
    String sqls="select `NUM`, `CODE1` as 'CODE',`LIBELLE` AS 'DESCRIPTION OF TRANSACTION',CREDIT_CDF AS DEBIT,DEBIT_CDF AS CREDIT, `DATES` from OHADA_TRANS where BUSS = '"+tmp+"' AND CODE='"+jComboBox1.getSelectedItem()+"' AND DEVICE='"+jComboBox2.getSelectedItem()+"' and SUBSTR(DATES,1,4)='"+year+"' order by DATES,NUM";
    //`LIBELLE`, `ENTRE`, `SORTIE`, `DATE`, `BUSS`, `USED`, `NUM`
    pst = con.prepareStatement(sqls);
      rs= pst.executeQuery();
     
       jTable1.setModel(DbUtils.resultSetToTableModel(rs));
    
             }catch(Exception ex){
                 JOptionPane.showMessageDialog(null, ex);
             }
                 DefaultTableCellRenderer centre =new DefaultTableCellRenderer();
        centre.setHorizontalAlignment(JLabel.CENTER);
         TableColumn col0=jTable1.getColumnModel().getColumn(0);
        TableColumn col1=jTable1.getColumnModel().getColumn(1);
          jTable1.getColumnModel().getColumn(1).setCellRenderer(centre);
        TableColumn col2=jTable1.getColumnModel().getColumn(2);
        TableColumn col3=jTable1.getColumnModel().getColumn(3);
          //centre.setHorizontalAlignment(JLabel.CENTER);
            jTable1.getColumnModel().getColumn(3).setCellRenderer(centre);
        TableColumn col4=jTable1.getColumnModel().getColumn(4);
         // centre.setHorizontalAlignment(JLabel.CENTER);
            jTable1.getColumnModel().getColumn(4).setCellRenderer(centre);
           TableColumn col5=jTable1.getColumnModel().getColumn(5);
           // TableColumn col6=jTable1.getColumnModel().getColumn(6);
      
       
       col0.setPreferredWidth(60);
       col1.setPreferredWidth(20);
       col2.setPreferredWidth(370);
       col3.setPreferredWidth(20);
       col4.setPreferredWidth(20);
       col5.setPreferredWidth(20);    
            }
             
            
            }
            
         
            
            } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());}   
        
                 
                // if(){}
             
    //   col6.setPreferredWidth(200);
             }
             public void show_in_table(){


 try{
    String sql="select `NUM`, `CODE1` as 'CODE',`LIBELLE` AS 'DESCRIPTION OF TRANSACTION',CREDIT AS DEBIT,DEBIT AS CREDIT, `DATES` from OHADA_TRANS WHERE CODE_M=57 order by DATES";
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
           
      
       
       
       col0.setPreferredWidth(20);
       col1.setPreferredWidth(370);
       col2.setPreferredWidth(50);
       col3.setPreferredWidth(50);
       col4.setPreferredWidth(50);
       col5.setPreferredWidth(200);
             }
    public static void cccccc (){
    
  //  show_in_table();
    }
     
      public void BigSave(){

 Double aaa = null;
 if(jComboBox3.getSelectedItem().equals("Credit")){
 
  try{
       
 
            String sql="SELECT   sum(DEBIT),sum(CREDIT) FROM ohada_trans WHERE code='"+jComboBox1.getSelectedItem()+"' AND BUSS='"+buss.getSelectedItem()+"'";
           
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
       
            String sql="SELECT   sum(DEBIT),sum(CREDIT) FROM budget_trans WHERE code='"+codeb.getText()+"' AND PROJET='"+buss.getSelectedItem()+"'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
              SUMDEBIT=rs.getDouble("sum(DEBIT)");
              SUMCREDIT=rs.getDouble("sum(CREDIT)");
             
               CHECK_BUDGET=SUMDEBIT-SUMCREDIT;
                
                
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
   
   
   
  
   if(CHECK_OHADA <aaa){ 
      
   JOptionPane.showMessageDialog(null,"Wrong Data"+"\n"+"No Enough Resources ","Error",JOptionPane.ERROR_MESSAGE);
  }else if(CHECK_BUDGET <aaa){ 
      
   JOptionPane.showMessageDialog(null,"Wrong Data"+"\n"+"No Enough Resources ","Error",JOptionPane.ERROR_MESSAGE);
  }else if(cdf.getText().isEmpty()||libele.getText().isEmpty()||code_m.getText().isEmpty()){ 
   JOptionPane.showMessageDialog(null,"Wrong Data"+"\n"+"Wrong Data ","Error",JOptionPane.ERROR_MESSAGE);
  }else{ 
  save();
callcaisebuss();
call_caisse();
search2BUS();
  }
   
   
   
 }else{
     if(codeb.getText().equals("")){
     
      CHECK_BUDGET=aaa;
     }else{
      try{
            String sql="SELECT   sum(DEBIT),sum(CREDIT) FROM budget_trans WHERE code='"+codeb.getText()+"' AND PROJET='"+buss.getSelectedItem()+"'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
              SUMDEBIT=rs.getDouble("sum(DEBIT)");
              SUMCREDIT=rs.getDouble("sum(CREDIT)");
             
               CHECK_BUDGET=SUMCREDIT-SUMDEBIT;
                
                
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
     
     }
            try{  
             Double xxx;
Double yyy;


//call from journala de banque  
            String sql="select SUM(DEBIT),SUM(CREDIT) from ohada_trans where code='"+code.getText()+"' and BUSS='"+buss.getSelectedItem()+"'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                xxx=rs.getDouble("SUM(DEBIT)");
               // entre.setText(""+xxx);
                yyy=rs.getDouble("SUM(CREDIT)");
                  
                   CHECK_OHADA=(xxx-yyy);
                 
            }
            }catch(Exception ex){ JOptionPane.showMessageDialog(null, ex);}
            
       
      
     
   if(CHECK_OHADA <aaa){ 
      
   JOptionPane.showMessageDialog(null,"Wrong Data"+"\n"+"No Enough Resources ","Error",JOptionPane.ERROR_MESSAGE);
  }else if(CHECK_BUDGET <aaa){ 
      
   JOptionPane.showMessageDialog(null,"Wrong Data"+"\n"+"No Enough Resources ","Error",JOptionPane.ERROR_MESSAGE);
  }else if(cdf.getText().isEmpty()||libele.getText().isEmpty()||code_m.getText().isEmpty()){ 
   JOptionPane.showMessageDialog(null,"Wrong Data"+"\n"+"Wrong Data ","Error",JOptionPane.ERROR_MESSAGE);
  }else{ 
  save();
callcaisebuss();
call_caisse();
search2BUS();
  }  
 
 }
   
 }
           public void report()
                           
     {
         
         
             
         
             
           String tmp =(String)buss.getSelectedItem();
          // this.setAlwaysOnTop (false);
     
             try{
                 
                 
                   String sqls="Select path from pathn";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
            if(rs.next()){
             
                String sum=rs.getString("path");
                  
                 String NameFiles=sum;
            
            String NameFile=""+NameFiles+"journaldecaise.jrxml";
                 
                 
                 
                JasperDesign jd=JRXmlLoader.load(NameFile);
                 
               //JasperDesign jd=JRXmlLoader.load("C:\\Users\\Dosh\\Documents\\NetBeansProjects\\intreprisemanagementsystem\\src\\intreprisemanagementsystem\\journaldecaise.jrxml");
              String sql="Select * from CAISSES INNER JOIN projet ON CAISSES.BUSS=projet.PROJET_NUM where buss='"+tmp+" '";
             // String sql="Select * from OHADA_TRANS INNER JOIN projet ON OHADA_TRANS.BUSS=projet.PROJET_NUM where buss='"+tmp+" and code_m=57'";
    
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
 
      public void reports()
     {
             try{
                 
                 
                   String sqls="Select path from pathn";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
            if(rs.next()){
             
                String sum=rs.getString("path");
                  
                 String NameFiles=sum;
            
            String NameFile=""+NameFiles+"journaldecaise.jrxml";
                 
                 
                 
                JasperDesign jd=JRXmlLoader.load(NameFile);
                 
                 
                 
               //String report ="C:\\Users\\Dosh\\Documents\\NetBeansProjects\\intreprisemanagementsystem\\src\\intreprisemanagementsystem\\journaldecaise.jrxml";
               // String report ="C:\\Users\\Doshe\\Documents\\NetBeansProjects\\intreprisemanagementsystem\\src\\report\\newReportmateriau.jrxml";
                
               
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
public void refresh(){
lb.setText("");
code_subcat.setText("");
codeb.setText("");
code_cat.setText("");
libele.setText("");
compte.setText("");
code_m.setText("");
code.setText("");
compte1.setText("");
jComboBox2.setSelectedItem("....");
amount.setText("0.00");
}
///BON





 public void imports(){
        CAISSE_Model();
       DefaultTableModel excels= (DefaultTableModel)jTable1.getModel(); 
        
        FileInputStream excelFILS=null;
        BufferedInputStream excelBIS=null;
        XSSFWorkbook excelImportWorkbook;
        
        
        String curentDirectiryPath="C:\\Users\\Dosh\\Desktop";
        JFileChooser excelFileChooserImport = new JFileChooser(curentDirectiryPath);
     // excelFileChooserImport.showOpenDialog(null);
      
      int excelchooser=excelFileChooserImport.showOpenDialog(null);
      
      if(excelchooser== JFileChooser.APPROVE_OPTION){
      try{
      File exceleFile =excelFileChooserImport.getSelectedFile();
      excelFILS=new FileInputStream(exceleFile);
      excelBIS= new BufferedInputStream(excelFILS);
       excelImportWorkbook = new XSSFWorkbook(excelBIS);
      XSSFSheet excelSheet = excelImportWorkbook.getSheetAt(0);
      
     
      for(int i=3;i<excelSheet.getLastRowNum(); i++){
      
      XSSFRow excelRow = excelSheet.getRow(i);
      XSSFCell cell = excelRow.getCell(0);
      XSSFCell cell1 = excelRow.getCell(1);
      XSSFCell cell2 = excelRow.getCell(2);
      XSSFCell cell3 = excelRow.getCell(3);
      XSSFCell cell4 = excelRow.getCell(4);
      XSSFCell cell5 = excelRow.getCell(5);
      XSSFCell cell6 = excelRow.getCell(6);
      XSSFCell cell7 = excelRow.getCell(7);
       XSSFCell cell8 = excelRow.getCell(8);
     
     excels.addRow(new Object[]{cell,cell1,cell2,cell3,cell4,cell5,cell6,cell7,cell8}); 
      }
      
      }catch(FileNotFoundException ex){
      ex.printStackTrace();
      }     catch (IOException ex) {
               ex.printStackTrace();
            }
      
      }

}
  public void CAISSE_Model(){
        mode=new DefaultTableModel();
        mode.addColumn("DATES");    
        mode.addColumn("CODE");
        mode.addColumn("LB");
        mode.addColumn("LIBELLE");
        mode.addColumn("DEBIT USD");
        mode.addColumn("CREDIT USD");
        mode.addColumn("DEBIT CDF");
        mode.addColumn("CREDIT CDF");   
       mode.addColumn("BATCH");
      //  mode.addColumn("PROJET");
       
       
        
        
        
        jTable1.setModel(mode);

  }
     public void Save_CAISSE_usd(){
       String COMPTEMEREEXCEL = null,CODEMEREEXCEL = null,CODEEXCEL,NAMEEXCEL = null, CLASSEXCEL = null,SUBSTRSEXCEL = null,BATCH=null;
   DefaultTableModel excels= (DefaultTableModel)jTable1.getModel(); 
   String DATES= null,LB,LIBELLE = null, DEBIT_USD = null,DEBIT_CDF= null ,CREDIT_USD= null ,CREDIT_CDF= null,CODE2= null,PROJET= null,COMPTEMERE = null,CODEMERE = null,OHADACODE = null,NAME = null,CLASS = null,SUBSTRS  = null,CCOMPTEMERE = null,CCODEMERE = null,COHADACODE,CNAME = null,CCLASS = null,CSUBSTRS  = null,CCODE = null;
   
    for(int i=0; i < excels.getRowCount();i++){
      DATES = excels.getValueAt(i,0).toString();
      CODEEXCEL= excels.getValueAt(i,1). toString();
      LB = excels.getValueAt(i,2). toString();
      LIBELLE = excels.getValueAt(i,3). toString();
      DEBIT_USD = excels.getValueAt(i,4). toString();
      CREDIT_USD= excels.getValueAt(i,5). toString();
      DEBIT_CDF = excels.getValueAt(i,6). toString();
     CREDIT_CDF = excels.getValueAt(i,7). toString();
     BATCH = excels.getValueAt(i,8). toString();
  
     
   
  
      try{
                 // SimpleDateFormat dateFormatsS = new SimpleDateFormat("yyyy-MM-dd");
        // String addDateS = dateFormatsS.format(jDateChooser1.getDate());
         
         String sql="SELECT NUM FROM ohada_trans where SUBSTR(NUM,5,10)='"+DATES+"' ORDER BY NUM DESC LIMIT 1";
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
               rolls= "No: "+DATES+"/1001";
                
             }
         }catch(NumberFormatException | SQLException e){
             JOptionPane.showMessageDialog(null, e);  
         }
     
     
     
    if(CODEEXCEL.equals("") && LB.equals("") && CREDIT_USD.equals("0.0")){
     //SAVE SOLD   
  
             try {
        PreparedStatement pst = con.prepareStatement("INSERT INTO `ohada_trans`(`COMPTE_M`, `COMPTE`, `CODE_M`, `CODE`, `CLASSE`, `SUBSTR`, `DEBIT`, `CREDIT`, `NUM`, `DATES`, `BUSS`, `LIBELLE`, `JOURNAL`, `CODE1`,DEBIT_CDF,CREDIT_CDF,DEVICE)"
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        
        pst.setString(1, "");
        pst.setString(2, "Caisse");
         pst.setString(3, "");
         pst.setString(4, jComboBox1.getSelectedItem().toString());
         pst.setString(5,"5");
         pst.setString(6, "57");
         pst.setString(7,DEBIT_USD);
         pst.setString(8,"0");
         pst.setString(9,rolls);
         
       SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(jDateChooser1.getDate());
         pst.setString(10,DATES);
         pst.setString(11,buss.getSelectedItem().toString());
         pst.setString(12,LIBELLE);
         pst.setString(13,"[JC]");
          pst.setString(14,"");
           pst.setString(15,amount.getText());
            pst.setDouble(16,0.0);
            pst.setString(17,jComboBox2.getSelectedItem().toString());
       
          
         
          pst.executeUpdate();
        
           //      JOptionPane.showMessageDialog(null,"Caisse saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
try {
     // CAISSE
       Double xxx,caissesdebit = null,yyy;
       try{  
            



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
        PreparedStatement pst = con.prepareStatement("INSERT INTO `caisses`(`CODE`,`CODEMERE`,`LIBELE`,`BUSS`,`DEBIT`,`CREDIT`,`DATE`,`COMPTE`,NUM,`MOIS`, `ANNEE`, `BUDGET`, `SOLDE`,DEVICE,PRINT)"
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        
        pst.setString(1, "");
         pst.setString(2, journal_caisse.jComboBox1.getSelectedItem().toString());
         pst.setString(3, LIBELLE);
         pst.setString(4, journal_caisse.buss.getSelectedItem().toString());
         pst.setString(5,DEBIT_USD);
         pst.setDouble(6,0.0);
         pst.setString(8,"");
         pst.setString(9,rolls);
         
          SimpleDateFormat dateFormatsS = new SimpleDateFormat("MMM");
         String addDateS = dateFormatsS.format(jDateChooser1.getDate());
         pst.setString(10,addDateS);
 
         pst.setInt(11,journal_caisse.jYearChooser1.getValue());
         pst.setString(12,LB);
         pst.setDouble(13,caissesdebit);
       
         pst.setString(14,journal_caisse.jComboBox2.getSelectedItem().toString());
         pst.setString(15,"Yes");
         
          SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(jDateChooser1.getDate());
         pst.setString(7, DATES);
         
          pst.executeUpdate();
        
                // JOptionPane.showMessageDialog(null,"Caisse saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
 
        
        
        
        
      }else if((LB.equals("") && CREDIT_USD.equals("0.0"))){
          // APPROVISIONEMENT
       // JOptionPane.showMessageDialog(null,CODEEXCEL);
        //JOptionPane.showMessageDialog(null,LIBELLE);
        //SELECT_buss();
         try{
             
            String sql="SELECT * FROM   OHADA where CODE='"+CODEEXCEL+"'";
          
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                //COMPTEMERE,CODEMERE,OHADACODE,NAME,CLASS,SUBSTRS
               COMPTEMEREEXCEL=rs.getString("COMPTEMERE");
               
                
                CODEMEREEXCEL=rs.getString("CODEMERE");
              
                
                CODEEXCEL=rs.getString("CODE");
              
                
                 NAMEEXCEL=rs.getString("NAME");
                  CLASSEXCEL=rs.getString("CLASS");
                  SUBSTRSEXCEL =rs.getString("SUBSTR");
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
 try{
             
            String sql="SELECT * FROM   OHADA where CODE='"+jComboBox1.getSelectedItem()+"'";
          
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                //COMPTEMERE,CODEMERE,OHADACODE,NAME,CLASS,SUBSTRS
               COMPTEMERE=rs.getString("COMPTEMERE");
               
                
                CODEMERE=rs.getString("CODEMERE");
              
                
                CODE=rs.getString("CODE");
              
                
                 NAME=rs.getString("NAME");
                  CLASS=rs.getString("CLASS");
                  SUBSTRS =rs.getString("SUBSTR");
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }

        try {
        // String tmp =jComboBox3.getSelectedItem().toString();
         PreparedStatement pst = con.prepareStatement("INSERT INTO `ohada_trans`(`COMPTE_M`, `COMPTE`, `CODE_M`, `CODE`, `CLASSE`, `SUBSTR`, `DEBIT`, `CREDIT`, `NUM`, `DATES`, `BUSS`, `LIBELLE`, `JOURNAL`, `CODE1`,DEBIT_CDF,CREDIT_CDF,DEVICE)"
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        
        pst.setString(1, COMPTEMEREEXCEL);
        pst.setString(2, NAMEEXCEL);
         pst.setString(3, CODEMEREEXCEL);
         pst.setString(4, CODEEXCEL);
         pst.setString(5,CLASSEXCEL);
         pst.setString(6, SUBSTRSEXCEL);
         pst.setString(8,CREDIT_USD);
         pst.setString(7,"0");
         pst.setString(9,rolls);
         
       SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(jDateChooser1.getDate());
         pst.setString(10,DATES);
         pst.setString(11,buss.getSelectedItem().toString());
         pst.setString(12,LIBELLE);
         pst.setString(13,"[JC]");
          pst.setString(14,CODE);
           pst.setString(16,"0");
            pst.setDouble(15,CDF);
       pst.setString(17, jComboBox2.getSelectedItem().toString());
          
         
          pst.executeUpdate();
        
           //      JOptionPane.showMessageDialog(null,"Caisse saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
        
        try {
         String tmp =jComboBox3.getSelectedItem().toString();
         PreparedStatement pst = con.prepareStatement("INSERT INTO `ohada_trans`(`COMPTE_M`, `COMPTE`, `CODE_M`, `CODE`, `CLASSE`, `SUBSTR`, `DEBIT`, `CREDIT`, `NUM`, `DATES`, `BUSS`, `LIBELLE`, `JOURNAL`, `CODE1`,DEBIT_CDF,CREDIT_CDF,DEVICE,LB)"
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        //COMPTEMERE,CODEMERE,CODE,NAME, CLASS,SUBSTRS
        pst.setString(1,COMPTEMERE);
        pst.setString(2, NAME);
         pst.setString(3, CODEMERE);
         pst.setString(4, CODE);
         pst.setString(5,CLASS);
         pst.setString(6, SUBSTRS);
         pst.setString(8,"0");
         pst.setString(7,DEBIT_USD);
         pst.setString(9,rolls);
         
       SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(jDateChooser1.getDate());
         pst.setString(10,DATES);
         pst.setString(11,buss.getSelectedItem().toString());
         pst.setString(12,LIBELLE);
         pst.setString(13,"OK");
          pst.setString(14, CODEEXCEL);
          pst.setDouble(16,CDF);
            pst.setString(15,"0");
       
    pst.setString(17,jComboBox2.getSelectedItem().toString());  
pst.setString(18,"");      
         
          pst.executeUpdate();
        
               //  JOptionPane.showMessageDialog(null,"Caisse saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
      try {
         String tmp =jComboBox3.getSelectedItem().toString();
        PreparedStatement pst = con.prepareStatement("INSERT INTO `caisses`(`CODE`,`CODEMERE`,`LIBELE`,`BUSS`,`DEBIT`,`CREDIT`,`DATE`,`COMPTE`,NUM,`MOIS`, `ANNEE`, `BUDGET`, `SOLDE`,DEVICE,PRINT)"
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        
         pst.setString(1, CODEEXCEL);
         pst.setString(2, jComboBox1.getSelectedItem().toString());
         pst.setString(3, LIBELLE);
         pst.setString(4, buss.getSelectedItem().toString());
         pst.setString(6, "0");
         pst.setString(5,DEBIT_USD);
         pst.setString(8,NAME);
         pst.setString(9,rolls);
         
          SimpleDateFormat dateFormatsS = new SimpleDateFormat("MMM");
         String addDateS = dateFormatsS.format(jDateChooser1.getDate());
         pst.setString(10, addDateS);
 
         pst.setInt(11,jYearChooser1.getValue());
         pst.setString(12,LB);
         pst.setString(13,"OK");
       
          SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(jDateChooser1.getDate());
         pst.setString(7, DATES);
         pst.setString(14,jComboBox2.getSelectedItem().toString());
           pst.setString(15,"Yes");
         
          pst.executeUpdate();
        
               //  JOptionPane.showMessageDialog(null,"Caisse saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
     
        
   }else if ((LB.equals("") && DEBIT_USD.equals("0.0"))){
////   TRANSFERT CAISSE
 try{
             
            String sql="SELECT * FROM   OHADA where CODE='"+CODEEXCEL+"'";
          
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                //COMPTEMERE,CODEMERE,OHADACODE,NAME,CLASS,SUBSTRS
               COMPTEMEREEXCEL=rs.getString("COMPTEMERE");
               
                
                CODEMEREEXCEL=rs.getString("CODEMERE");
              
                
                CODEEXCEL=rs.getString("CODE");
              
                
                 NAMEEXCEL=rs.getString("NAME");
                  CLASSEXCEL=rs.getString("CLASS");
                  SUBSTRSEXCEL =rs.getString("SUBSTR");
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
 try{
             
            String sql="SELECT * FROM   OHADA where CODE='"+jComboBox1.getSelectedItem()+"'";
          
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                //COMPTEMERE,CODEMERE,OHADACODE,NAME,CLASS,SUBSTRS
               COMPTEMERE=rs.getString("COMPTEMERE");
               
                
                CODEMERE=rs.getString("CODEMERE");
              
                
                CODE=rs.getString("CODE");
              
                
                 NAME=rs.getString("NAME");
                  CLASS=rs.getString("CLASS");
                  SUBSTRS =rs.getString("SUBSTR");
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }

        try {
        // String tmp =jComboBox3.getSelectedItem().toString();
         PreparedStatement pst = con.prepareStatement("INSERT INTO `ohada_trans`(`COMPTE_M`, `COMPTE`, `CODE_M`, `CODE`, `CLASSE`, `SUBSTR`, `DEBIT`, `CREDIT`, `NUM`, `DATES`, `BUSS`, `LIBELLE`, `JOURNAL`, `CODE1`,DEBIT_CDF,CREDIT_CDF,DEVICE)"
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        
        pst.setString(1, COMPTEMEREEXCEL);
        pst.setString(2, NAMEEXCEL);
         pst.setString(3, CODEMEREEXCEL);
         pst.setString(4, CODEEXCEL);
         pst.setString(5,CLASSEXCEL);
         pst.setString(6, SUBSTRSEXCEL);
         pst.setString(7,CREDIT_USD);
         pst.setString(8,"0");
         pst.setString(9,rolls);
         
       SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(jDateChooser1.getDate());
         pst.setString(10,DATES);
         pst.setString(11,buss.getSelectedItem().toString());
         pst.setString(12,LIBELLE);
         pst.setString(13,"[JC]");
          pst.setString(14,CODE);
           pst.setString(16,"0");
            pst.setDouble(15,CDF);
       pst.setString(17, jComboBox2.getSelectedItem().toString());
          
         
          pst.executeUpdate();
        
           //      JOptionPane.showMessageDialog(null,"Caisse saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
        
        try {
         String tmp =jComboBox3.getSelectedItem().toString();
         PreparedStatement pst = con.prepareStatement("INSERT INTO `ohada_trans`(`COMPTE_M`, `COMPTE`, `CODE_M`, `CODE`, `CLASSE`, `SUBSTR`, `DEBIT`, `CREDIT`, `NUM`, `DATES`, `BUSS`, `LIBELLE`, `JOURNAL`, `CODE1`,DEBIT_CDF,CREDIT_CDF,DEVICE)"
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        //COMPTEMERE,CODEMERE,CODE,NAME, CLASS,SUBSTRS
        pst.setString(1,COMPTEMERE);
        pst.setString(2, NAME);
         pst.setString(3, CODEMERE);
         pst.setString(4, CODE);
         pst.setString(5,CLASS);
         pst.setString(6, SUBSTRS);
         pst.setString(7,"0");
         pst.setString(8,CREDIT_USD);
         pst.setString(9,rolls);
         
       SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(jDateChooser1.getDate());
         pst.setString(10, DATES);
         pst.setString(11,buss.getSelectedItem().toString());
         pst.setString(12,LIBELLE);
         pst.setString(13,"[JC]");
          pst.setString(14, CODEEXCEL);
          pst.setDouble(16,CDF);
            pst.setString(15,"0");
       
    pst.setString(17,jComboBox2.getSelectedItem().toString());      
         
          pst.executeUpdate();
        
               //  JOptionPane.showMessageDialog(null,"Caisse saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
      try {
         String tmp =jComboBox3.getSelectedItem().toString();
        PreparedStatement pst = con.prepareStatement("INSERT INTO `caisses`(`CODE`,`CODEMERE`,`LIBELE`,`BUSS`,`DEBIT`,`CREDIT`,`DATE`,`COMPTE`,NUM,`MOIS`, `ANNEE`, `BUDGET`, `SOLDE`,DEVICE,PRINT)"
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        
         pst.setString(1, CODEEXCEL);
         pst.setString(2, jComboBox1.getSelectedItem().toString());
         pst.setString(3, LIBELLE);
         pst.setString(4, buss.getSelectedItem().toString());
         pst.setString(5, "0");
         pst.setString(6,CREDIT_USD);
         pst.setString(8,NAME);
         pst.setString(9,rolls);
         
          SimpleDateFormat dateFormatsS = new SimpleDateFormat("MMM");
         String addDateS = dateFormatsS.format(jDateChooser1.getDate());
         pst.setString(10, addDateS);
 
         pst.setInt(11,jYearChooser1.getValue());
         pst.setString(12,LB);
         pst.setString(13,"OK");
       
          SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(jDateChooser1.getDate());
         pst.setString(7, DATES);
         pst.setString(14,jComboBox2.getSelectedItem().toString());
           pst.setString(15,"Yes");
         
          pst.executeUpdate();
        
               //  JOptionPane.showMessageDialog(null,"Caisse saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }


   
   }else if (DEBIT_USD.equals("0.0")){
////// CREDIT
try{
             
            String sql="SELECT * FROM   OHADA where CODE='"+CODEEXCEL+"'";
          
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                //COMPTEMERE,CODEMERE,OHADACODE,NAME,CLASS,SUBSTRS
               COMPTEMEREEXCEL=rs.getString("COMPTEMERE");
               
                
                CODEMEREEXCEL=rs.getString("CODEMERE");
              
                
                CODEEXCEL=rs.getString("CODE");
              
                
                 NAMEEXCEL=rs.getString("NAME");
                  CLASSEXCEL=rs.getString("CLASS");
                  SUBSTRSEXCEL =rs.getString("SUBSTR");
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
 try{
             
            String sql="SELECT * FROM   OHADA where CODE='"+jComboBox1.getSelectedItem()+"'";
          
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                //COMPTEMERE,CODEMERE,OHADACODE,NAME,CLASS,SUBSTRS
               COMPTEMERE=rs.getString("COMPTEMERE");
               
                
                CODEMERE=rs.getString("CODEMERE");
              
                
                CODE=rs.getString("CODE");
              
                
                 NAME=rs.getString("NAME");
                  CLASS=rs.getString("CLASS");
                  SUBSTRS =rs.getString("SUBSTR");
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }

        try {
        // String tmp =jComboBox3.getSelectedItem().toString();
         PreparedStatement pst = con.prepareStatement("INSERT INTO `ohada_trans`(`COMPTE_M`, `COMPTE`, `CODE_M`, `CODE`, `CLASSE`, `SUBSTR`, `DEBIT`, `CREDIT`, `NUM`, `DATES`, `BUSS`, `LIBELLE`, `JOURNAL`, `CODE1`,DEBIT_CDF,CREDIT_CDF,DEVICE,LB)"
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        
        pst.setString(1, COMPTEMEREEXCEL);
        pst.setString(2, NAMEEXCEL);
         pst.setString(3, CODEMEREEXCEL);
         pst.setString(4, CODEEXCEL);
         pst.setString(5,CLASSEXCEL);
         pst.setString(6, SUBSTRSEXCEL);
         pst.setString(7,CREDIT_USD);
         pst.setString(8,"0");
         pst.setString(9,rolls);
         
       SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(jDateChooser1.getDate());
         pst.setString(10, DATES);
         pst.setString(11,buss.getSelectedItem().toString());
         pst.setString(12,LIBELLE);
         pst.setString(13,"[JC]");
          pst.setString(14,CODE);
           pst.setString(16,"0");
            pst.setDouble(15,CDF);
       pst.setString(17, jComboBox2.getSelectedItem().toString());
       pst.setString(18,LB);
          
         
          pst.executeUpdate();
        
           //      JOptionPane.showMessageDialog(null,"Caisse saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
        
        try {
         String tmp =jComboBox3.getSelectedItem().toString();
         PreparedStatement pst = con.prepareStatement("INSERT INTO `ohada_trans`(`COMPTE_M`, `COMPTE`, `CODE_M`, `CODE`, `CLASSE`, `SUBSTR`, `DEBIT`, `CREDIT`, `NUM`, `DATES`, `BUSS`, `LIBELLE`, `JOURNAL`, `CODE1`,DEBIT_CDF,CREDIT_CDF,DEVICE,LB)"
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        //COMPTEMERE,CODEMERE,CODE,NAME, CLASS,SUBSTRS
        pst.setString(1,COMPTEMERE);
        pst.setString(2, NAME);
         pst.setString(3, CODEMERE);
         pst.setString(4, CODE);
         pst.setString(5,CLASS);
         pst.setString(6, SUBSTRS);
         pst.setString(7,"0");
         pst.setString(8,CREDIT_USD);
         pst.setString(9,rolls);
         
       SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(jDateChooser1.getDate());
         pst.setString(10,DATES);
         pst.setString(11,buss.getSelectedItem().toString());
         pst.setString(12,LIBELLE);
         pst.setString(13,"[JC]");
          pst.setString(14, CODEEXCEL);
          pst.setDouble(16,CDF);
            pst.setString(15,"0");
       
    pst.setString(17,jComboBox2.getSelectedItem().toString()); 
     pst.setString(18,LB);
         
          pst.executeUpdate();
        
               //  JOptionPane.showMessageDialog(null,"Caisse saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
      try {
         String tmp =jComboBox3.getSelectedItem().toString();
        PreparedStatement pst = con.prepareStatement("INSERT INTO `caisses`(`CODE`,`CODEMERE`,`LIBELE`,`BUSS`,`DEBIT`,`CREDIT`,`DATE`,`COMPTE`,NUM,`MOIS`, `ANNEE`, `BUDGET`, `SOLDE`,DEVICE,PRINT)"
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        
         pst.setString(1, CODEEXCEL);
         pst.setString(2, jComboBox1.getSelectedItem().toString());
         pst.setString(3, LIBELLE);
         pst.setString(4, buss.getSelectedItem().toString());
         pst.setString(5, "0");
         pst.setString(6,CREDIT_USD);
         pst.setString(8,NAME);
         pst.setString(9,rolls);
         
          SimpleDateFormat dateFormatsS = new SimpleDateFormat("MMM");
         String addDateS = dateFormatsS.format(jDateChooser1.getDate());
         pst.setString(10,addDateS);
 
         pst.setInt(11,jYearChooser1.getValue());
         pst.setString(12,LB);
         pst.setString(13,"OK");
       
          SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(jDateChooser1.getDate());
         pst.setString(7, DATES);
         pst.setString(14,jComboBox2.getSelectedItem().toString());
           pst.setString(15,"Yes");
         
          pst.executeUpdate();
        
               //  JOptionPane.showMessageDialog(null,"Caisse saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }


 //BUDGET CREDIT
      String ITEM = null,CODECAT = null,CODESUBCAT = null,CAT = null,SUBCAT = null;
       try{
          String sql="SELECT item,code FROM  budget_code WHERE code='"+LB+"' and CAT='"+buss.getSelectedItem()+"'";
          
            pst=con.prepareStatement(sql);
            //pst.setString(1, tmp);
            rs=pst.executeQuery();
            if(rs.next()){
                 
                ITEM = rs.getString("item"); 
          }
  
      }catch(Exception ex){
        JOptionPane.showMessageDialog(null,ex);  
      }
        
          try{
          String sql="SELECT CAT,LB,LBSUB,SUB_CAT FROM  budget WHERE code='"+LB+"' and PROJECT='"+buss.getSelectedItem()+"'";
          
            pst=con.prepareStatement(sql);
            //pst.setString(1, tmp);
            rs=pst.executeQuery();
            if(rs.next()){
                 
               CODECAT = rs.getString("LB");     
              CODESUBCAT = rs.getString("LBSUB");
             CAT = rs.getString("CAT");
             SUBCAT = rs.getString("SUB_CAT");
              
          }
  
      }catch(Exception ex){
        JOptionPane.showMessageDialog(null,ex);  
      }
         //  JOptionPane.showMessageDialog(null,CAT+""+SUBCAT);  
          try{
    String sql="INSERT INTO `budget_trans`(`ITEM`, `DEBIT`, `CREDIT`, `SOLD`, `PROJET`, `CODE`, `NUM`, `CAT`, `DATES`, `MOIS`, `ANNEE`,SUB_CAT,CODE_CAT,CODE_SUB_CAT,ITEMS,BATCH) "+"values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    pst=con.prepareStatement(sql);
    pst.setString(1,LIBELLE);
    pst.setString(2,"0");
    pst.setString(3,CREDIT_USD);
    pst.setDouble(4, 0);
    pst.setString(5,buss.getSelectedItem().toString());
    pst.setString(6,LB);
    pst.setString(7,rolls);
    
    pst.setString(8,CAT);
   
    pst.setString(9,DATES);
    SimpleDateFormat dateFormatsS = new SimpleDateFormat("MMM");
         String addDateS = dateFormatsS.format(jDateChooser1.getDate());
      pst.setString(10,DATES.substring(5,7));
         pst.setString(11,DATES.substring(0,4));
    pst.setString (12,SUBCAT);
    
     pst.setString(13,CODECAT);
      pst.setString(14,CODESUBCAT);
      
      pst.setString(15,ITEM);
      pst.setString(16,BATCH);
   
    
    
    pst.executeUpdate();
    // JOptionPane.showMessageDialog(null,"BUDGET TRANS");
    }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}


   }// END OF BIG ELSE
     
      
     
   
  
    } //END FOR
     JOptionPane.showMessageDialog(null,"Tranction Saved");
    } 
   //  HERE
     
      public void Save_CAISSE(){
       String COMPTEMEREEXCEL = null,CODEMEREEXCEL = null,CODEEXCEL,NAMEEXCEL = null, CLASSEXCEL = null,SUBSTRSEXCEL = null,BATCH=null;
   DefaultTableModel excels= (DefaultTableModel)jTable1.getModel(); 
   String DATES= null,LB,LIBELLE = null, DEBIT_USD = null,DEBIT_CDF= null ,CREDIT_USD= null ,CREDIT_CDF= null,CODE2= null,PROJET= null,COMPTEMERE = null,CODEMERE = null,OHADACODE = null,NAME = null,CLASS = null,SUBSTRS  = null,CCOMPTEMERE = null,CCODEMERE = null,COHADACODE,CNAME = null,CCLASS = null,CSUBSTRS  = null,CCODE = null;
   
    for(int i=0; i < excels.getRowCount();i++){
      DATES = excels.getValueAt(i,0).toString();
      CODEEXCEL= excels.getValueAt(i,1). toString();
      LB = excels.getValueAt(i,2). toString();
      LIBELLE = excels.getValueAt(i,3). toString();
      DEBIT_USD = excels.getValueAt(i,4). toString();
      CREDIT_USD= excels.getValueAt(i,5). toString();
      DEBIT_CDF = excels.getValueAt(i,6). toString();
     CREDIT_CDF = excels.getValueAt(i,7). toString();
   BATCH = excels.getValueAt(i,8). toString();
  
        
   
  
      try{
                 // SimpleDateFormat dateFormatsS = new SimpleDateFormat("yyyy-MM-dd");
        // String addDateS = dateFormatsS.format(jDateChooser1.getDate());
         
         String sql="SELECT NUM FROM ohada_trans where SUBSTR(NUM,5,10)='"+DATES+"' ORDER BY NUM DESC LIMIT 1";
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
               rolls= "No: "+DATES+"/1001";
                
             }
         }catch(NumberFormatException | SQLException e){
             JOptionPane.showMessageDialog(null, e);  
         }
     
     
     
    if(CODEEXCEL.equals("") && LB.equals("") && CREDIT_USD.equals("0.0")){
     //SAVE SOLD   
  String DEVICEEs = null,DEVICEEss = null;
        Double PR= Double.parseDouble(DEBIT_USD);
    try{
            String sql="select * from monais WHERE caisse='"+jComboBox1.getSelectedItem()+"'";
           
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
  if(jComboBox2.getSelectedItem().equals("USD")){
      //HERE IS THE CODE 
      try{
            String sql="select * from monais WHERE caisse='"+CODEEXCEL+"'";
           
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
     
  USD=PR/sale;
  CDF=PR;
  }

             try {
        PreparedStatement pst = con.prepareStatement("INSERT INTO `ohada_trans`(`COMPTE_M`, `COMPTE`, `CODE_M`, `CODE`, `CLASSE`, `SUBSTR`, `DEBIT`, `CREDIT`, `NUM`, `DATES`, `BUSS`, `LIBELLE`, `JOURNAL`, `CODE1`,DEBIT_CDF,CREDIT_CDF,DEVICE)"
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        
        pst.setString(1, "");
        pst.setString(2, "Caisse");
         pst.setString(3, "");
         pst.setString(4, jComboBox1.getSelectedItem().toString());
         pst.setString(5,"5");
         pst.setString(6, "57");
         pst.setDouble(7,USD);
         pst.setString(8,"0");
         pst.setString(9,rolls);
         
       SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(jDateChooser1.getDate());
         pst.setString(10,DATES);
         pst.setString(11,buss.getSelectedItem().toString());
         pst.setString(12,LIBELLE);
         pst.setString(13,"[JC]");
          pst.setString(14,"");
           pst.setDouble(15,CDF);
            pst.setDouble(16,0.0);
            pst.setString(17,jComboBox2.getSelectedItem().toString());
       
          
         
          pst.executeUpdate();
        
           //      JOptionPane.showMessageDialog(null,"Caisse saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }

        
        
      }else if((LB.equals("") && CREDIT_USD.equals("0.0"))){
          // APPROVISIONEMENT
       // JOptionPane.showMessageDialog(null,CODEEXCEL);
        //JOptionPane.showMessageDialog(null,LIBELLE);
        //SELECT_buss();
        
        String DEVICEEs = null,DEVICEEss = null;
        Double PR= Double.parseDouble(DEBIT_USD);
    try{
            String sql="select * from monais WHERE caisse='"+jComboBox1.getSelectedItem()+"'";
           
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
  if(jComboBox2.getSelectedItem().equals("USD")){
      //HERE IS THE CODE 
      try{
            String sql="select * from monais WHERE caisse='"+CODEEXCEL+"'";
           
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
     
  USD=PR/sale;
  CDF=PR;
  }

         try{
             
            String sql="SELECT * FROM   OHADA where CODE='"+CODEEXCEL+"'";
          
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                //COMPTEMERE,CODEMERE,OHADACODE,NAME,CLASS,SUBSTRS
               COMPTEMEREEXCEL=rs.getString("COMPTEMERE");
               
                
                CODEMEREEXCEL=rs.getString("CODEMERE");
              
                
                CODEEXCEL=rs.getString("CODE");
              
                
                 NAMEEXCEL=rs.getString("NAME");
                  CLASSEXCEL=rs.getString("CLASS");
                  SUBSTRSEXCEL =rs.getString("SUBSTR");
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
 try{
             
            String sql="SELECT * FROM   OHADA where CODE='"+jComboBox1.getSelectedItem()+"'";
          
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                //COMPTEMERE,CODEMERE,OHADACODE,NAME,CLASS,SUBSTRS
               COMPTEMERE=rs.getString("COMPTEMERE");
               
                
                CODEMERE=rs.getString("CODEMERE");
              
                
                CODE=rs.getString("CODE");
              
                
                 NAME=rs.getString("NAME");
                  CLASS=rs.getString("CLASS");
                  SUBSTRS =rs.getString("SUBSTR");
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }

        try {
        // String tmp =jComboBox3.getSelectedItem().toString();
         PreparedStatement pst = con.prepareStatement("INSERT INTO `ohada_trans`(`COMPTE_M`, `COMPTE`, `CODE_M`, `CODE`, `CLASSE`, `SUBSTR`, `DEBIT`, `CREDIT`, `NUM`, `DATES`, `BUSS`, `LIBELLE`, `JOURNAL`, `CODE1`,DEBIT_CDF,CREDIT_CDF,DEVICE)"
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        
        pst.setString(1, COMPTEMEREEXCEL);
        pst.setString(2, NAMEEXCEL);
         pst.setString(3, CODEMEREEXCEL);
         pst.setString(4, CODEEXCEL);
         pst.setString(5,CLASSEXCEL);
         pst.setString(6, SUBSTRSEXCEL);
         pst.setDouble(8,USD);
         pst.setString(7,"0");
         pst.setString(9,rolls);
         
       SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(jDateChooser1.getDate());
         pst.setString(10,DATES);
         pst.setString(11,buss.getSelectedItem().toString());
         pst.setString(12,LIBELLE);
         pst.setString(13,"[JC]");
          pst.setString(14,CODE);
           pst.setDouble(16,CDF);
            pst.setString(15,"0");
       pst.setString(17, jComboBox2.getSelectedItem().toString());
          
         
          pst.executeUpdate();
        
           //      JOptionPane.showMessageDialog(null,"Caisse saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
        
        try {
         String tmp =jComboBox3.getSelectedItem().toString();
         PreparedStatement pst = con.prepareStatement("INSERT INTO `ohada_trans`(`COMPTE_M`, `COMPTE`, `CODE_M`, `CODE`, `CLASSE`, `SUBSTR`, `DEBIT`, `CREDIT`, `NUM`, `DATES`, `BUSS`, `LIBELLE`, `JOURNAL`, `CODE1`,DEBIT_CDF,CREDIT_CDF,DEVICE)"
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        //COMPTEMERE,CODEMERE,CODE,NAME, CLASS,SUBSTRS
        pst.setString(1,COMPTEMERE);
        pst.setString(2, NAME);
         pst.setString(3, CODEMERE);
         pst.setString(4, CODE);
         pst.setString(5,CLASS);
         pst.setString(6, SUBSTRS);
         pst.setString(8,"0");
         pst.setDouble(7,USD);
         pst.setString(9,rolls);
         
       SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(jDateChooser1.getDate());
         pst.setString(10,DATES);
         pst.setString(11,buss.getSelectedItem().toString());
         pst.setString(12,LIBELLE);
         pst.setString(13,"[JC]");
          pst.setString(14, CODEEXCEL);
          pst.setString(16,"0");
            pst.setDouble(15,CDF);
       
    pst.setString(17,jComboBox2.getSelectedItem().toString());      
         
          pst.executeUpdate();
        
               //  JOptionPane.showMessageDialog(null,"Caisse saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
       
      //caisse comptable
    
        
   }else if ((LB.equals("") && DEBIT_USD.equals("0.0"))){
////   TRANSFERT CAISSE

String DEVICEEs = null,DEVICEEss = null;
        Double PR= Double.parseDouble(CREDIT_USD);
    try{
            String sql="select * from monais WHERE caisse='"+jComboBox1.getSelectedItem()+"'";
           
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
  if(jComboBox2.getSelectedItem().equals("USD")){
      //HERE IS THE CODE 
      try{
            String sql="select * from monais WHERE caisse='"+CODEEXCEL+"'";
           
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
     
  USD=PR/sale;
  CDF=PR;
  }

 try{
             
            String sql="SELECT * FROM   OHADA where CODE='"+CODEEXCEL+"'";
          
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                //COMPTEMERE,CODEMERE,OHADACODE,NAME,CLASS,SUBSTRS
               COMPTEMEREEXCEL=rs.getString("COMPTEMERE");
               
                
                CODEMEREEXCEL=rs.getString("CODEMERE");
              
                
                CODEEXCEL=rs.getString("CODE");
              
                
                 NAMEEXCEL=rs.getString("NAME");
                  CLASSEXCEL=rs.getString("CLASS");
                  SUBSTRSEXCEL =rs.getString("SUBSTR");
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
 try{
             
            String sql="SELECT * FROM   OHADA where CODE='"+jComboBox1.getSelectedItem()+"'";
          
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                //COMPTEMERE,CODEMERE,OHADACODE,NAME,CLASS,SUBSTRS
               COMPTEMERE=rs.getString("COMPTEMERE");
               
                
                CODEMERE=rs.getString("CODEMERE");
              
                
                CODE=rs.getString("CODE");
              
                
                 NAME=rs.getString("NAME");
                  CLASS=rs.getString("CLASS");
                  SUBSTRS =rs.getString("SUBSTR");
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }

        try {
        // String tmp =jComboBox3.getSelectedItem().toString();
         PreparedStatement pst = con.prepareStatement("INSERT INTO `ohada_trans`(`COMPTE_M`, `COMPTE`, `CODE_M`, `CODE`, `CLASSE`, `SUBSTR`, `DEBIT`, `CREDIT`, `NUM`, `DATES`, `BUSS`, `LIBELLE`, `JOURNAL`, `CODE1`,DEBIT_CDF,CREDIT_CDF,DEVICE)"
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        
        pst.setString(1, COMPTEMEREEXCEL);
        pst.setString(2, NAMEEXCEL);
         pst.setString(3, CODEMEREEXCEL);
         pst.setString(4, CODEEXCEL);
         pst.setString(5,CLASSEXCEL);
         pst.setString(6, SUBSTRSEXCEL);
         pst.setDouble(7,USD);
         pst.setString(8,"0");
         pst.setString(9,rolls);
         
       SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(jDateChooser1.getDate());
         pst.setString(10,DATES);
         pst.setString(11,buss.getSelectedItem().toString());
         pst.setString(12,LIBELLE);
         pst.setString(13,"[JC]");
          pst.setString(14,CODE);
           pst.setString(16,"0");
            pst.setDouble(15,CDF);
       pst.setString(17, jComboBox2.getSelectedItem().toString());
          
         
          pst.executeUpdate();
        
           //      JOptionPane.showMessageDialog(null,"Caisse saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
        
        try {
         String tmp =jComboBox3.getSelectedItem().toString();
         PreparedStatement pst = con.prepareStatement("INSERT INTO `ohada_trans`(`COMPTE_M`, `COMPTE`, `CODE_M`, `CODE`, `CLASSE`, `SUBSTR`, `DEBIT`, `CREDIT`, `NUM`, `DATES`, `BUSS`, `LIBELLE`, `JOURNAL`, `CODE1`,DEBIT_CDF,CREDIT_CDF,DEVICE)"
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        //COMPTEMERE,CODEMERE,CODE,NAME, CLASS,SUBSTRS
        pst.setString(1,COMPTEMERE);
        pst.setString(2, NAME);
         pst.setString(3, CODEMERE);
         pst.setString(4, CODE);
         pst.setString(5,CLASS);
         pst.setString(6, SUBSTRS);
         pst.setString(7,"0");
         pst.setDouble(8,USD);
         pst.setString(9,rolls);
         
       SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(jDateChooser1.getDate());
         pst.setString(10, DATES);
         pst.setString(11,buss.getSelectedItem().toString());
         pst.setString(12,LIBELLE);
         pst.setString(13,"[JC]");
          pst.setString(14, CODEEXCEL);
          pst.setDouble(16,CDF);
            pst.setString(15,"0");
       
    pst.setString(17,jComboBox2.getSelectedItem().toString());      
         
          pst.executeUpdate();
        
               //  JOptionPane.showMessageDialog(null,"Caisse saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
      
   
   }else if (DEBIT_USD.equals("0.0")){
////// CREDIT
try{
             
            String sql="SELECT * FROM   OHADA where CODE='"+CODEEXCEL+"'";
          
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                //COMPTEMERE,CODEMERE,OHADACODE,NAME,CLASS,SUBSTRS
               COMPTEMEREEXCEL=rs.getString("COMPTEMERE");
               
                
                CODEMEREEXCEL=rs.getString("CODEMERE");
              
                
                CODEEXCEL=rs.getString("CODE");
              
                
                 NAMEEXCEL=rs.getString("NAME");
                  CLASSEXCEL=rs.getString("CLASS");
                  SUBSTRSEXCEL =rs.getString("SUBSTR");
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
 try{
             
            String sql="SELECT * FROM   OHADA where CODE='"+jComboBox1.getSelectedItem()+"'";
          
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                //COMPTEMERE,CODEMERE,OHADACODE,NAME,CLASS,SUBSTRS
               COMPTEMERE=rs.getString("COMPTEMERE");
               
                
                CODEMERE=rs.getString("CODEMERE");
              
                
                CODE=rs.getString("CODE");
              
                
                 NAME=rs.getString("NAME");
                  CLASS=rs.getString("CLASS");
                  SUBSTRS =rs.getString("SUBSTR");
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
 String DEVICEEs = null,DEVICEEss = null;
        Double PR= Double.parseDouble(CREDIT_USD);
    try{
            String sql="select * from monais WHERE caisse='"+jComboBox1.getSelectedItem()+"'";
           
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
  if(jComboBox2.getSelectedItem().equals("USD")){
      //HERE IS THE CODE 
      try{
            String sql="select * from monais WHERE caisse='"+CODEEXCEL+"'";
           
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
     
  USD=PR/sale;
  CDF=PR;
  }

        try {
        // String tmp =jComboBox3.getSelectedItem().toString();
         PreparedStatement pst = con.prepareStatement("INSERT INTO `ohada_trans`(`COMPTE_M`, `COMPTE`, `CODE_M`, `CODE`, `CLASSE`, `SUBSTR`, `DEBIT`, `CREDIT`, `NUM`, `DATES`, `BUSS`, `LIBELLE`, `JOURNAL`, `CODE1`,DEBIT_CDF,CREDIT_CDF,DEVICE,LB)"
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
       // here
        pst.setString(1, COMPTEMEREEXCEL);
        pst.setString(2, NAMEEXCEL);
         pst.setString(3, CODEMEREEXCEL);
         pst.setString(4, CODEEXCEL);
         pst.setString(5,CLASSEXCEL);
         pst.setString(6, SUBSTRSEXCEL);
         pst.setDouble(7,USD);
         pst.setString(8,"0");
         pst.setString(9,rolls);
         
       SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(jDateChooser1.getDate());
         pst.setString(10, DATES);
         pst.setString(11,buss.getSelectedItem().toString());
         pst.setString(12,LIBELLE);
         pst.setString(13,"[JC]");
          pst.setString(14,CODE);
           pst.setString(16,"0");
            pst.setDouble(15,CDF);
       pst.setString(17, jComboBox2.getSelectedItem().toString());
        pst.setString(18,LB);
          
         
          pst.executeUpdate();
        
           //      JOptionPane.showMessageDialog(null,"Caisse saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
        
        try {
         String tmp =jComboBox3.getSelectedItem().toString();
         PreparedStatement pst = con.prepareStatement("INSERT INTO `ohada_trans`(`COMPTE_M`, `COMPTE`, `CODE_M`, `CODE`, `CLASSE`, `SUBSTR`, `DEBIT`, `CREDIT`, `NUM`, `DATES`, `BUSS`, `LIBELLE`, `JOURNAL`, `CODE1`,DEBIT_CDF,CREDIT_CDF,DEVICE,LB)"
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        //COMPTEMERE,CODEMERE,CODE,NAME, CLASS,SUBSTRS
        pst.setString(1,COMPTEMERE);
        pst.setString(2, NAME);
         pst.setString(3, CODEMERE);
         pst.setString(4, CODE);
         pst.setString(5,CLASS);
         pst.setString(6, SUBSTRS);
         pst.setString(7,"0");
         pst.setDouble(8,USD);
         pst.setString(9,rolls);
         
       SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(jDateChooser1.getDate());
         pst.setString(10,DATES);
         pst.setString(11,buss.getSelectedItem().toString());
         pst.setString(12,LIBELLE);
         pst.setString(13,"[JC]");
          pst.setString(14, CODEEXCEL);
          pst.setDouble(16,CDF);
            pst.setString(15,"0");
       
    pst.setString(17,jComboBox2.getSelectedItem().toString());  
 pst.setString(18,LB);    
         
          pst.executeUpdate();
        
               //  JOptionPane.showMessageDialog(null,"Caisse saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
      

//BUDGET CREDIT
      String ITEM = null,CODECAT = null,CODESUBCAT = null,CAT = null,SUBCAT = null;
       try{
          String sql="SELECT item,code FROM  budget_code WHERE code='"+LB+"' and CAT='"+buss.getSelectedItem()+"'";
          
            pst=con.prepareStatement(sql);
            //pst.setString(1, tmp);
            rs=pst.executeQuery();
            if(rs.next()){
                 
                ITEM = rs.getString("item"); 
          }
  
      }catch(Exception ex){
        JOptionPane.showMessageDialog(null,ex);  
      }
        
          try{
          String sql="SELECT CAT,LB,LBSUB,SUB_CAT FROM  budget WHERE code='"+LB+"' and PROJECT='"+buss.getSelectedItem()+"'";
          
            pst=con.prepareStatement(sql);
            //pst.setString(1, tmp);
            rs=pst.executeQuery();
            if(rs.next()){
                 
               CODECAT = rs.getString("LB");     
              CODESUBCAT = rs.getString("LBSUB");
             CAT = rs.getString("CAT");
             SUBCAT = rs.getString("SUB_CAT");
              
          }
  
      }catch(Exception ex){
        JOptionPane.showMessageDialog(null,ex);  
      }
         //  JOptionPane.showMessageDialog(null,CAT+""+SUBCAT);  
          try{
    String sql="INSERT INTO `budget_trans`(`ITEM`, `DEBIT`, `CREDIT`, `SOLD`, `PROJET`, `CODE`, `NUM`, `CAT`, `DATES`, `MOIS`, `ANNEE`,SUB_CAT,CODE_CAT,CODE_SUB_CAT,ITEMS,BATCH) "+"values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    pst=con.prepareStatement(sql);
    pst.setString(1,LIBELLE);
    pst.setString(2,"0");
    pst.setDouble(3,USD);
    pst.setDouble(4, 0);
    pst.setString(5,buss.getSelectedItem().toString());
    pst.setString(6,LB);
    pst.setString(7,rolls);
    
    pst.setString(8,CAT);
   
    pst.setString(9,DATES);
         pst.setString(10,DATES.substring(5,7));
         pst.setString(11,DATES.substring(0,4));
    pst.setString (12,SUBCAT);
    
     pst.setString(13,CODECAT);
      pst.setString(14,CODESUBCAT);
      
      pst.setString(15,ITEM);
      pst.setString(16,BATCH);
   
    
    
    pst.executeUpdate();
    // JOptionPane.showMessageDialog(null,"BUDGET TRANS");
    }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}



   }// END OF BIG ELSE
     
      
     
   
  
    } //END FOR
     JOptionPane.showMessageDialog(null,"Tranction Saved");
    } 
         public void Save_CAISSEwithcaise(){
       String COMPTEMEREEXCEL = null,CODEMEREEXCEL = null,CODEEXCEL,NAMEEXCEL = null, CLASSEXCEL = null,SUBSTRSEXCEL = null;
   DefaultTableModel excels= (DefaultTableModel)jTable1.getModel(); 
   String DATES= null,LB,LIBELLE = null, DEBIT_USD = null,DEBIT_CDF= null ,CREDIT_USD= null ,CREDIT_CDF= null,CODE2= null,PROJET= null,COMPTEMERE = null,CODEMERE = null,OHADACODE = null,NAME = null,CLASS = null,SUBSTRS  = null,CCOMPTEMERE = null,CCODEMERE = null,COHADACODE,CNAME = null,CCLASS = null,CSUBSTRS  = null,CCODE = null;
   
    for(int i=0; i < excels.getRowCount();i++){
      DATES = excels.getValueAt(i,0).toString();
      CODEEXCEL= excels.getValueAt(i,1). toString();
      LB = excels.getValueAt(i,2). toString();
      LIBELLE = excels.getValueAt(i,3). toString();
      DEBIT_USD = excels.getValueAt(i,4). toString();
      CREDIT_USD= excels.getValueAt(i,5). toString();
      DEBIT_CDF = excels.getValueAt(i,6). toString();
     CREDIT_CDF = excels.getValueAt(i,7). toString();
   //  CODE2 = excels.getValueAt(i,8). toString();
  
     
   
  
      try{
                 // SimpleDateFormat dateFormatsS = new SimpleDateFormat("yyyy-MM-dd");
        // String addDateS = dateFormatsS.format(jDateChooser1.getDate());
         
         String sql="SELECT NUM FROM ohada_trans where SUBSTR(NUM,5,10)='"+DATES+"' ORDER BY NUM DESC LIMIT 1";
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
               rolls= "No: "+DATES+"/1001";
                
             }
         }catch(NumberFormatException | SQLException e){
             JOptionPane.showMessageDialog(null, e);  
         }
     
     
     
    if(CODEEXCEL.equals("") && LB.equals("") && CREDIT_USD.equals("0.0")){
     //SAVE SOLD   
  
             try {
        PreparedStatement pst = con.prepareStatement("INSERT INTO `ohada_trans`(`COMPTE_M`, `COMPTE`, `CODE_M`, `CODE`, `CLASSE`, `SUBSTR`, `DEBIT`, `CREDIT`, `NUM`, `DATES`, `BUSS`, `LIBELLE`, `JOURNAL`, `CODE1`,DEBIT_CDF,CREDIT_CDF,DEVICE)"
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        
        pst.setString(1, "");
        pst.setString(2, "Caisse");
         pst.setString(3, "");
         pst.setString(4, jComboBox1.getSelectedItem().toString());
         pst.setString(5,"5");
         pst.setString(6, "57");
         pst.setString(7,DEBIT_USD);
         pst.setString(8,"0");
         pst.setString(9,rolls);
         
       SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(jDateChooser1.getDate());
         pst.setString(10,DATES);
         pst.setString(11,buss.getSelectedItem().toString());
         pst.setString(12,LIBELLE);
         pst.setString(13,"[JC]");
          pst.setString(14,"");
           pst.setString(15,DEBIT_USD);
            pst.setDouble(16,0.0);
            pst.setString(17,jComboBox2.getSelectedItem().toString());
       
          
         
          pst.executeUpdate();
        
           //      JOptionPane.showMessageDialog(null,"Caisse saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
try {
     // CAISSE
       Double xxx,caissesdebit = null,yyy;
       try{  
            



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
        PreparedStatement pst = con.prepareStatement("INSERT INTO `caisses`(`CODE`,`CODEMERE`,`LIBELE`,`BUSS`,`DEBIT`,`CREDIT`,`DATE`,`COMPTE`,NUM,`MOIS`, `ANNEE`, `BUDGET`, `SOLDE`,DEVICE,PRINT)"
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        
        pst.setString(1, "");
         pst.setString(2, journal_caisse.jComboBox1.getSelectedItem().toString());
         pst.setString(3, LIBELLE);
         pst.setString(4, journal_caisse.buss.getSelectedItem().toString());
         pst.setString(5,DEBIT_USD);
         pst.setDouble(6,0.0);
         pst.setString(8,"");
         pst.setString(9,rolls);
         
          SimpleDateFormat dateFormatsS = new SimpleDateFormat("MMM");
         String addDateS = dateFormatsS.format(jDateChooser1.getDate());
         pst.setString(10,addDateS);
 
         pst.setInt(11,journal_caisse.jYearChooser1.getValue());
         pst.setString(12,LB);
         pst.setString(13,"");
       
         pst.setString(14,journal_caisse.jComboBox2.getSelectedItem().toString());
         pst.setString(15,"Yes");
         
          SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(jDateChooser1.getDate());
         pst.setString(7, DATES);
         
          pst.executeUpdate();
        
                // JOptionPane.showMessageDialog(null,"Caisse saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
 
        
        
        
        
      }else if((LB.equals("") && CREDIT_USD.equals("0.0"))){
          // APPROVISIONEMENT
       // JOptionPane.showMessageDialog(null,CODEEXCEL);
        //JOptionPane.showMessageDialog(null,LIBELLE);
        //SELECT_buss();
         try{
             
            String sql="SELECT * FROM   OHADA where CODE='"+CODEEXCEL+"'";
          
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                //COMPTEMERE,CODEMERE,OHADACODE,NAME,CLASS,SUBSTRS
               COMPTEMEREEXCEL=rs.getString("COMPTEMERE");
               
                
                CODEMEREEXCEL=rs.getString("CODEMERE");
              
                
                CODEEXCEL=rs.getString("CODE");
              
                
                 NAMEEXCEL=rs.getString("NAME");
                  CLASSEXCEL=rs.getString("CLASS");
                  SUBSTRSEXCEL =rs.getString("SUBSTR");
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
 try{
             
            String sql="SELECT * FROM   OHADA where CODE='"+jComboBox1.getSelectedItem()+"'";
          
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                //COMPTEMERE,CODEMERE,OHADACODE,NAME,CLASS,SUBSTRS
               COMPTEMERE=rs.getString("COMPTEMERE");
               
                
                CODEMERE=rs.getString("CODEMERE");
              
                
                CODE=rs.getString("CODE");
              
                
                 NAME=rs.getString("NAME");
                  CLASS=rs.getString("CLASS");
                  SUBSTRS =rs.getString("SUBSTR");
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }

        try {
        // String tmp =jComboBox3.getSelectedItem().toString();
         PreparedStatement pst = con.prepareStatement("INSERT INTO `ohada_trans`(`COMPTE_M`, `COMPTE`, `CODE_M`, `CODE`, `CLASSE`, `SUBSTR`, `DEBIT`, `CREDIT`, `NUM`, `DATES`, `BUSS`, `LIBELLE`, `JOURNAL`, `CODE1`,DEBIT_CDF,CREDIT_CDF,DEVICE)"
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        
        pst.setString(1, COMPTEMEREEXCEL);
        pst.setString(2, NAMEEXCEL);
         pst.setString(3, CODEMEREEXCEL);
         pst.setString(4, CODEEXCEL);
         pst.setString(5,CLASSEXCEL);
         pst.setString(6, SUBSTRSEXCEL);
         pst.setString(8,CREDIT_USD);
         pst.setString(7,"0");
         pst.setString(9,rolls);
         
       SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(jDateChooser1.getDate());
         pst.setString(10,DATES);
         pst.setString(11,buss.getSelectedItem().toString());
         pst.setString(12,LIBELLE);
         pst.setString(13,"[JC]");
          pst.setString(14,CODE);
           pst.setString(16,CREDIT_USD);
            pst.setString(15,"0");
       pst.setString(17, jComboBox2.getSelectedItem().toString());
          
         
          pst.executeUpdate();
        
           //      JOptionPane.showMessageDialog(null,"Caisse saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
        
        try {
         String tmp =jComboBox3.getSelectedItem().toString();
         PreparedStatement pst = con.prepareStatement("INSERT INTO `ohada_trans`(`COMPTE_M`, `COMPTE`, `CODE_M`, `CODE`, `CLASSE`, `SUBSTR`, `DEBIT`, `CREDIT`, `NUM`, `DATES`, `BUSS`, `LIBELLE`, `JOURNAL`, `CODE1`,DEBIT_CDF,CREDIT_CDF,DEVICE)"
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        //COMPTEMERE,CODEMERE,CODE,NAME, CLASS,SUBSTRS
        pst.setString(1,COMPTEMERE);
        pst.setString(2, NAME);
         pst.setString(3, CODEMERE);
         pst.setString(4, CODE);
         pst.setString(5,CLASS);
         pst.setString(6, SUBSTRS);
         pst.setString(8,"0");
         pst.setString(7,DEBIT_USD);
         pst.setString(9,rolls);
         
       SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(jDateChooser1.getDate());
         pst.setString(10,DATES);
         pst.setString(11,buss.getSelectedItem().toString());
         pst.setString(12,LIBELLE);
         pst.setString(13,"[JC]");
          pst.setString(14, CODEEXCEL);
          pst.setString(16,"0");
            pst.setString(15,DEBIT_USD);
       
    pst.setString(17,jComboBox2.getSelectedItem().toString());      
         
          pst.executeUpdate();
        
               //  JOptionPane.showMessageDialog(null,"Caisse saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
        //print
      try {
         String tmp =jComboBox3.getSelectedItem().toString();
        PreparedStatement pst = con.prepareStatement("INSERT INTO `caisses`(`CODE`,`CODEMERE`,`LIBELE`,`BUSS`,`DEBIT`,`CREDIT`,`DATE`,`COMPTE`,NUM,`MOIS`, `ANNEE`, `BUDGET`, `SOLDE`,DEVICE,PRINT)"
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        
         pst.setString(1, CODEEXCEL);
         pst.setString(2, jComboBox1.getSelectedItem().toString());
         pst.setString(3, LIBELLE);
         pst.setString(4, buss.getSelectedItem().toString());
         pst.setString(6, "0");
         pst.setString(5,DEBIT_USD);
         pst.setString(8,NAME);
         pst.setString(9,rolls);
         
          SimpleDateFormat dateFormatsS = new SimpleDateFormat("MMM");
         String addDateS = dateFormatsS.format(jDateChooser1.getDate());
         pst.setString(10, addDateS);
 
         pst.setInt(11,jYearChooser1.getValue());
         pst.setString(12,LB);
         pst.setString(13,"");
       
          SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(jDateChooser1.getDate());
         pst.setString(7, DATES);
         pst.setString(14,jComboBox2.getSelectedItem().toString());
           pst.setString(15,"Yes");
         
          pst.executeUpdate();
        
               //  JOptionPane.showMessageDialog(null,"Caisse saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
      //caisse comptable
        String DEVICEEs = null,DEVICEEss = null;
        Double PR= Double.parseDouble(DEBIT_USD);
    try{
            String sql="select * from monais WHERE caisse='"+jComboBox1.getSelectedItem()+"'";
           
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
  if(jComboBox2.getSelectedItem().equals("USD")){
      //HERE IS THE CODE 
      try{
            String sql="select * from monais WHERE caisse='"+CODEEXCEL+"'";
           
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
     
  USD=PR/sale;
  CDF=PR;
  }
//  HERE
 try {
         String tmp =jComboBox3.getSelectedItem().toString();
        PreparedStatement pst = con.prepareStatement("INSERT INTO `caisses`(`CODE`,`CODEMERE`,`LIBELE`,`BUSS`,`DEBIT`,`CREDIT`,`DATE`,`COMPTE`,NUM,`MOIS`, `ANNEE`, `BUDGET`, `SOLDE`, `DEVICE`,PRINT)"
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        pst.setString(1, CODEEXCEL);
         pst.setString(2, jComboBox1.getSelectedItem().toString());
         pst.setString(3, LIBELLE);
         pst.setString(4, buss.getSelectedItem().toString());
         pst.setDouble(5, USD);
         pst.setString(6,"0");
         pst.setString(8,NAME);
         pst.setString(9,rolls);
         
          SimpleDateFormat dateFormatsS = new SimpleDateFormat("MMM");
         String addDateS = dateFormatsS.format(jDateChooser1.getDate());
         pst.setString(10, addDateS);
 
         pst.setInt(11,jYearChooser1.getValue());
         pst.setString(12,LB);
         pst.setString(13,"OK");
       
          SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(jDateChooser1.getDate());
         pst.setString(7, addDate);
         pst.setString(14,jComboBox2.getSelectedItem().toString());
           pst.setString(15,"No");
         
          pst.executeUpdate();
        
           //      JOptionPane.showMessageDialog(null,"Caisse saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
        
   }else if ((LB.equals("") && DEBIT_USD.equals("0.0"))){
////   TRANSFERT CAISSE
 try{
             
            String sql="SELECT * FROM   OHADA where CODE='"+CODEEXCEL+"'";
          
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                //COMPTEMERE,CODEMERE,OHADACODE,NAME,CLASS,SUBSTRS
               COMPTEMEREEXCEL=rs.getString("COMPTEMERE");
               
                
                CODEMEREEXCEL=rs.getString("CODEMERE");
              
                
                CODEEXCEL=rs.getString("CODE");
              
                
                 NAMEEXCEL=rs.getString("NAME");
                  CLASSEXCEL=rs.getString("CLASS");
                  SUBSTRSEXCEL =rs.getString("SUBSTR");
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
 try{
             
            String sql="SELECT * FROM   OHADA where CODE='"+jComboBox1.getSelectedItem()+"'";
          
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                //COMPTEMERE,CODEMERE,OHADACODE,NAME,CLASS,SUBSTRS
               COMPTEMERE=rs.getString("COMPTEMERE");
               
                
                CODEMERE=rs.getString("CODEMERE");
              
                
                CODE=rs.getString("CODE");
              
                
                 NAME=rs.getString("NAME");
                  CLASS=rs.getString("CLASS");
                  SUBSTRS =rs.getString("SUBSTR");
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }

        try {
        // String tmp =jComboBox3.getSelectedItem().toString();
         PreparedStatement pst = con.prepareStatement("INSERT INTO `ohada_trans`(`COMPTE_M`, `COMPTE`, `CODE_M`, `CODE`, `CLASSE`, `SUBSTR`, `DEBIT`, `CREDIT`, `NUM`, `DATES`, `BUSS`, `LIBELLE`, `JOURNAL`, `CODE1`,DEBIT_CDF,CREDIT_CDF,DEVICE)"
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        
        pst.setString(1, COMPTEMEREEXCEL);
        pst.setString(2, NAMEEXCEL);
         pst.setString(3, CODEMEREEXCEL);
         pst.setString(4, CODEEXCEL);
         pst.setString(5,CLASSEXCEL);
         pst.setString(6, SUBSTRSEXCEL);
         pst.setString(7,CREDIT_USD);
         pst.setString(8,"0");
         pst.setString(9,rolls);
         
       SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(jDateChooser1.getDate());
         pst.setString(10,DATES);
         pst.setString(11,buss.getSelectedItem().toString());
         pst.setString(12,LIBELLE);
         pst.setString(13,"[JC]");
          pst.setString(14,CODE);
           pst.setString(16,"0");
            pst.setString(15,CREDIT_USD);
       pst.setString(17, jComboBox2.getSelectedItem().toString());
          
         
          pst.executeUpdate();
        
           //      JOptionPane.showMessageDialog(null,"Caisse saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
        
        try {
         String tmp =jComboBox3.getSelectedItem().toString();
         PreparedStatement pst = con.prepareStatement("INSERT INTO `ohada_trans`(`COMPTE_M`, `COMPTE`, `CODE_M`, `CODE`, `CLASSE`, `SUBSTR`, `DEBIT`, `CREDIT`, `NUM`, `DATES`, `BUSS`, `LIBELLE`, `JOURNAL`, `CODE1`,DEBIT_CDF,CREDIT_CDF,DEVICE)"
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        //COMPTEMERE,CODEMERE,CODE,NAME, CLASS,SUBSTRS
        pst.setString(1,COMPTEMERE);
        pst.setString(2, NAME);
         pst.setString(3, CODEMERE);
         pst.setString(4, CODE);
         pst.setString(5,CLASS);
         pst.setString(6, SUBSTRS);
         pst.setString(7,"0");
         pst.setString(8,CREDIT_USD);
         pst.setString(9,rolls);
         
       SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(jDateChooser1.getDate());
         pst.setString(10, DATES);
         pst.setString(11,buss.getSelectedItem().toString());
         pst.setString(12,LIBELLE);
         pst.setString(13,"[JC]");
          pst.setString(14, CODEEXCEL);
          pst.setString(16,CREDIT_USD);
            pst.setString(15,"0");
       
    pst.setString(17,jComboBox2.getSelectedItem().toString());      
         
          pst.executeUpdate();
        
               //  JOptionPane.showMessageDialog(null,"Caisse saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
      try {
         String tmp =jComboBox3.getSelectedItem().toString();
        PreparedStatement pst = con.prepareStatement("INSERT INTO `caisses`(`CODE`,`CODEMERE`,`LIBELE`,`BUSS`,`DEBIT`,`CREDIT`,`DATE`,`COMPTE`,NUM,`MOIS`, `ANNEE`, `BUDGET`, `SOLDE`,DEVICE,PRINT)"
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        
         pst.setString(1, CODEEXCEL);
         pst.setString(2, jComboBox1.getSelectedItem().toString());
         pst.setString(3, LIBELLE);
         pst.setString(4, buss.getSelectedItem().toString());
         pst.setString(5, "0");
         pst.setString(6,CREDIT_USD);
         pst.setString(8,NAME);
         pst.setString(9,rolls);
         
          SimpleDateFormat dateFormatsS = new SimpleDateFormat("MMM");
         String addDateS = dateFormatsS.format(jDateChooser1.getDate());
         pst.setString(10, addDateS);
 
         pst.setInt(11,jYearChooser1.getValue());
         pst.setString(12,LB);
         pst.setString(13,"");
       
          SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(jDateChooser1.getDate());
         pst.setString(7, DATES);
         pst.setString(14,jComboBox2.getSelectedItem().toString());
           pst.setString(15,"Yes");
         
          pst.executeUpdate();
        
               //  JOptionPane.showMessageDialog(null,"Caisse saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }


   
   }else if (DEBIT_USD.equals("0.0")){
////// CREDIT
try{
             
            String sql="SELECT * FROM   OHADA where CODE='"+CODEEXCEL+"'";
          
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                //COMPTEMERE,CODEMERE,OHADACODE,NAME,CLASS,SUBSTRS
               COMPTEMEREEXCEL=rs.getString("COMPTEMERE");
               
                
                CODEMEREEXCEL=rs.getString("CODEMERE");
              
                
                CODEEXCEL=rs.getString("CODE");
              
                
                 NAMEEXCEL=rs.getString("NAME");
                  CLASSEXCEL=rs.getString("CLASS");
                  SUBSTRSEXCEL =rs.getString("SUBSTR");
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
 try{
             
            String sql="SELECT * FROM   OHADA where CODE='"+jComboBox1.getSelectedItem()+"'";
          
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                //COMPTEMERE,CODEMERE,OHADACODE,NAME,CLASS,SUBSTRS
               COMPTEMERE=rs.getString("COMPTEMERE");
               
                
                CODEMERE=rs.getString("CODEMERE");
              
                
                CODE=rs.getString("CODE");
              
                
                 NAME=rs.getString("NAME");
                  CLASS=rs.getString("CLASS");
                  SUBSTRS =rs.getString("SUBSTR");
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }

        try {
        // String tmp =jComboBox3.getSelectedItem().toString();
         PreparedStatement pst = con.prepareStatement("INSERT INTO `ohada_trans`(`COMPTE_M`, `COMPTE`, `CODE_M`, `CODE`, `CLASSE`, `SUBSTR`, `DEBIT`, `CREDIT`, `NUM`, `DATES`, `BUSS`, `LIBELLE`, `JOURNAL`, `CODE1`,DEBIT_CDF,CREDIT_CDF,DEVICE,LB)"
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        
        pst.setString(1, COMPTEMEREEXCEL);
        pst.setString(2, NAMEEXCEL);
         pst.setString(3, CODEMEREEXCEL);
         pst.setString(4, CODEEXCEL);
         pst.setString(5,CLASSEXCEL);
         pst.setString(6, SUBSTRSEXCEL);
         pst.setString(7,CREDIT_USD);
         pst.setString(8,"0");
         pst.setString(9,rolls);
         
       SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(jDateChooser1.getDate());
         pst.setString(10, DATES);
         pst.setString(11,buss.getSelectedItem().toString());
         pst.setString(12,LIBELLE);
         pst.setString(13,"[JC]");
          pst.setString(14,CODE);
           pst.setString(16,"0");
            pst.setString(15,CREDIT_USD);
       pst.setString(17, jComboBox2.getSelectedItem().toString());
        pst.setString(18,LB);
          
         
          pst.executeUpdate();
        
           //      JOptionPane.showMessageDialog(null,"Caisse saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
        
        try {
         String tmp =jComboBox3.getSelectedItem().toString();
         PreparedStatement pst = con.prepareStatement("INSERT INTO `ohada_trans`(`COMPTE_M`, `COMPTE`, `CODE_M`, `CODE`, `CLASSE`, `SUBSTR`, `DEBIT`, `CREDIT`, `NUM`, `DATES`, `BUSS`, `LIBELLE`, `JOURNAL`, `CODE1`,DEBIT_CDF,CREDIT_CDF,DEVICE,LB)"
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        pst.setString(1,COMPTEMERE);
        pst.setString(2, NAME);
         pst.setString(3, CODEMERE);
         pst.setString(4, CODE);
         pst.setString(5,CLASS);
         pst.setString(6, SUBSTRS);
         pst.setString(7,"0");
         pst.setString(8,CREDIT_USD);
         pst.setString(9,rolls);
         
       SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(jDateChooser1.getDate());
         pst.setString(10,DATES);
         pst.setString(11,buss.getSelectedItem().toString());
         pst.setString(12,LIBELLE);
         pst.setString(13,"[JC]");
          pst.setString(14, CODEEXCEL);
          pst.setString(16,CREDIT_USD);
            pst.setString(15,"0");
       
    pst.setString(17,jComboBox2.getSelectedItem().toString());  
 pst.setString(18,LB);    
         
          pst.executeUpdate();
        
               //  JOptionPane.showMessageDialog(null,"Caisse saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
      try {
         String tmp =jComboBox3.getSelectedItem().toString();
        PreparedStatement pst = con.prepareStatement("INSERT INTO `caisses`(`CODE`,`CODEMERE`,`LIBELE`,`BUSS`,`DEBIT`,`CREDIT`,`DATE`,`COMPTE`,NUM,`MOIS`, `ANNEE`, `BUDGET`, `SOLDE`,DEVICE,PRINT)"
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        
         pst.setString(1, CODEEXCEL);
         pst.setString(2, jComboBox1.getSelectedItem().toString());
         pst.setString(3, LIBELLE);
         pst.setString(4, buss.getSelectedItem().toString());
         pst.setString(5, "0");
         pst.setString(6,CREDIT_USD);
         pst.setString(8,NAME);
         pst.setString(9,rolls);
         
          SimpleDateFormat dateFormatsS = new SimpleDateFormat("MMM");
         String addDateS = dateFormatsS.format(jDateChooser1.getDate());
         pst.setString(10,addDateS);
 
         pst.setInt(11,jYearChooser1.getValue());
         pst.setString(12,LB);
         pst.setString(13,"");
       
          SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(jDateChooser1.getDate());
         pst.setString(7, DATES);
         pst.setString(14,jComboBox2.getSelectedItem().toString());
           pst.setString(15,"Yes");
         
          pst.executeUpdate();
        
               //  JOptionPane.showMessageDialog(null,"Caisse saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }

//caisse comptable
        String DEVICEEs = null,DEVICEEss = null;
        Double PR= Double.parseDouble(CREDIT_USD),USDB;
    try{
            String sql="select * from monais WHERE caisse='"+jComboBox1.getSelectedItem()+"'";
           
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
  if(jComboBox2.getSelectedItem().equals("USD")){
      //HERE IS THE CODE 
      try{
            String sql="select * from monais WHERE caisse='"+CODEEXCEL+"'";
           
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
     
  USD=PR/sale;
  CDF=PR;
 // USDB=
  }
//  HERE
 try {
         String tmp =jComboBox3.getSelectedItem().toString();
        PreparedStatement pst = con.prepareStatement("INSERT INTO `caisses`(`CODE`,`CODEMERE`,`LIBELE`,`BUSS`,`DEBIT`,`CREDIT`,`DATE`,`COMPTE`,NUM,`MOIS`, `ANNEE`, `BUDGET`, `SOLDE`, `DEVICE`,PRINT)"
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        pst.setString(1, CODEEXCEL);
         pst.setString(2, jComboBox1.getSelectedItem().toString());
         pst.setString(3, LIBELLE);
         pst.setString(4, buss.getSelectedItem().toString());
         pst.setDouble(6, USD);
         pst.setString(5,"0");
         pst.setString(8,NAME);
         pst.setString(9,rolls);
         
          SimpleDateFormat dateFormatsS = new SimpleDateFormat("MMM");
         String addDateS = dateFormatsS.format(jDateChooser1.getDate());
         pst.setString(10, addDateS);
 
         pst.setInt(11,jYearChooser1.getValue());
         pst.setString(12,LB);
         pst.setString(13,"OK");
       
          SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(jDateChooser1.getDate());
         pst.setString(7, addDate);
         pst.setString(14,jComboBox2.getSelectedItem().toString());
           pst.setString(15,"No");
         
          pst.executeUpdate();
        
           //      JOptionPane.showMessageDialog(null,"Caisse saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
//BUDGET CREDIT
      String ITEM = null,CODECAT = null,CODESUBCAT = null,CAT = null,SUBCAT = null;
       try{
          String sql="SELECT item,code FROM  budget_code WHERE code='"+LB+"' and CAT='"+buss.getSelectedItem()+"'";
          
            pst=con.prepareStatement(sql);
            //pst.setString(1, tmp);
            rs=pst.executeQuery();
            if(rs.next()){
                 
                ITEM = rs.getString("item"); 
          }
  
      }catch(Exception ex){
        JOptionPane.showMessageDialog(null,ex);  
      }
        
          try{
          String sql="SELECT CAT,LB,LBSUB,SUB_CAT FROM  budget WHERE code='"+LB+"' and PROJECT='"+buss.getSelectedItem()+"'";
          
            pst=con.prepareStatement(sql);
            //pst.setString(1, tmp);
            rs=pst.executeQuery();
            if(rs.next()){
                 
               CODECAT = rs.getString("LB");     
              CODESUBCAT = rs.getString("LBSUB");
             CAT = rs.getString("CAT");
             SUBCAT = rs.getString("SUB_CAT");
              
          }
  
      }catch(Exception ex){
        JOptionPane.showMessageDialog(null,ex);  
      }
         //  JOptionPane.showMessageDialog(null,CAT+""+SUBCAT);  
          try{
    String sql="INSERT INTO `budget_trans`(`ITEM`, `DEBIT`, `CREDIT`, `SOLD`, `PROJET`, `CODE`, `NUM`, `CAT`, `DATES`, `MOIS`, `ANNEE`,SUB_CAT,CODE_CAT,CODE_SUB_CAT,`ITEMS`) "+"values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    pst=con.prepareStatement(sql);
    pst.setString(1,LIBELLE);
    pst.setString(2,"0");
    pst.setDouble(3,USD);
    pst.setDouble(4, 0);
    pst.setString(5,buss.getSelectedItem().toString());
    pst.setString(6,LB);
    pst.setString(7,rolls);
    
    pst.setString(8,CAT);
   
    pst.setString(9,DATES);
         pst.setString(10,DATES.substring(5,7));
         pst.setString(11,DATES.substring(0,4));
    pst.setString (12,SUBCAT);
    
     pst.setString(13,CODECAT);
      pst.setString(14,CODESUBCAT);
      
      pst.setString(15,ITEM);
   
    
    
    pst.executeUpdate();
    // JOptionPane.showMessageDialog(null,"BUDGET TRANS");
    }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}



   }// END OF BIG ELSE
     
      
     
   
  
    } //END FOR
     JOptionPane.showMessageDialog(null,"Tranction Saved");
    } 
    public void upload(){
         cononline=JavaDbConnectonline.dbConnect();
        String COMPTE_M,COMPTE,CODE_M,CODEs,CLASSE,SUBSTRs,DEBIT,CREDIT,NUM,DATES,BUSS,LIBELLE,JOURNAL,CODE1,NUM_FACTURE,DEBIT_CDF,CREDIT_CDF,LB,PAY,DEVICE;
       try{
            String sql="SELECT COMPTE_M,COMPTE,CODE_M,CODE,CLASSE,SUBSTR,DEBIT,CREDIT,NUM,DATES,BUSS,LIBELLE,JOURNAL,CODE1,NUM_FACTURE,DEBIT_CDF,CREDIT_CDF,LB,PAY,DEVICE FROM ohada_trans WHERE BUSS='"+buss.getSelectedItem()+"'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
               COMPTE_M=rs.getString("COMPTE_M");
               COMPTE=rs.getString("COMPTE");
               CODE_M=rs.getString("CODE_M");
               CODEs=rs.getString("CODE");
               CLASSE=rs.getString("CLASSE");
               SUBSTRs=rs.getString("SUBSTR");
               DEBIT=rs.getString("DEBIT");
               CREDIT=rs.getString("CREDIT");
               NUM=rs.getString("NUM");
               DATES=rs.getString("DATES");
               LIBELLE=rs.getString("LIBELLE");
               JOURNAL=rs.getString("JOURNAL");
               CODE1=rs.getString("CODE1");
               NUM_FACTURE=rs.getString("NUM_FACTURE");
               DEBIT_CDF=rs.getString("DEBIT_CDF");
               CREDIT_CDF=rs.getString("CREDIT_CDF");
               LB=rs.getString("LB");
               PAY=rs.getString("PAY");
               DEVICE=rs.getString("DEVICE");
              
         
          try{
    String sqls="INSERT INTO ohada_trans_ems (COMPTE_M,COMPTE,CODE_M,CODE,CLASSE,SUBSTR,DEBIT,CREDIT,NUM,DATES,BUSS,LIBELLE,JOURNAL,CODE1,NUM_FACTURE,DEBIT_CDF,CREDIT_CDF,LB,PAY,DEVICE) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     
    pst=cononline.prepareStatement(sqls);
     pst.setString(1,COMPTE_M);
    pst.setString(2,COMPTE);
    pst.setString(3,CODE_M);
    pst.setString(4, CODEs);
    pst.setString(5,CLASSE);
    pst.setString(6,SUBSTRs);
    pst.setString(7,DEBIT);
    
    pst.setString(8,CREDIT);
   
    pst.setString(9,NUM);
         pst.setString(10,DATES);
         pst.setString(11,buss.getSelectedItem().toString());
    pst.setString (12,LIBELLE);
    
     pst.setString(13,JOURNAL);
      pst.setString(14,CODE1);
      pst.setString(15,NUM_FACTURE);
      pst.setString(16,DEBIT_CDF);
      pst.setString(17,CREDIT_CDF);
      pst.setString(18,LB);
      pst.setString(19,PAY);
      pst.setString(20,DEVICE);
   
    
    pst.executeUpdate();
     // JOptionPane.showMessageDialog(null,"Datas Saved");
    }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}       
//                INSERT INTO Customers (CustomerName, ContactName, Address, City, PostalCode, Country)
//SELECT SupplierName, ContactName, Address, City, PostalCode, Country FROM Suppliers;
//                
            }
        //  con.close();
            }
        
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex); }   
         
         
//     INSERT INTO table2 (column1, column2, column3, ...)
//SELECT column1, column2, column3, ...
//FROM table1
//WHERE condition;
 JOptionPane.showMessageDialog(null,"Transaction Saved");
     
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
        lb = new javax.swing.JTextField();
        codeb = new javax.swing.JTextField();
        code_cat = new javax.swing.JTextField();
        code_subcat = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        titre = new javax.swing.JEditorPane();
        jPanel5 = new javax.swing.JPanel();
        jDateChooser1 = new com.alee.extended.date.WebDateField();
        jComboBox4 = new javax.swing.JComboBox<>();
        jPanel11 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        cdf = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        caisse = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        budget = new javax.swing.JTextField();
        debitbudget = new javax.swing.JTextField();
        reste = new javax.swing.JTextField();
        jPanel14 = new javax.swing.JPanel();
        compte = new javax.swing.JTextField();
        code_m = new javax.swing.JTextField();
        compte1 = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        libele = new javax.swing.JEditorPane();
        jPanel17 = new javax.swing.JPanel();
        jComboBox3 = new javax.swing.JComboBox<>();
        amount = new javax.swing.JTextField();
        jPanel18 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList<>();
        recherche = new javax.swing.JTextField();
        jPanel20 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel19 = new javax.swing.JPanel();
        recherche1 = new javax.swing.JTextField();
        jScrollPane5 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jPanel8 = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        btwn_cashbook = new javax.swing.JRadioButton();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu4 = new javax.swing.JMenu();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu7 = new javax.swing.JMenu();

        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_Spiral_Bound_Booklet_16px.png"))); // NOI18N
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

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)), "LB", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        lb.setEditable(false);
        lb.setBackground(new java.awt.Color(240, 240, 241));
        lb.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lb.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        codeb.setEditable(false);
        codeb.setBackground(new java.awt.Color(240, 240, 241));
        codeb.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        codeb.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        code_cat.setEditable(false);
        code_cat.setBackground(new java.awt.Color(240, 240, 241));
        code_cat.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        code_cat.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        code_subcat.setEditable(false);
        code_subcat.setBackground(new java.awt.Color(240, 240, 241));
        code_subcat.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        code_subcat.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lb)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(codeb, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(code_cat, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(code_subcat, javax.swing.GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(lb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(codeb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(code_cat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(code_subcat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)), "Project Title", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        titre.setEditable(false);
        titre.setBackground(new java.awt.Color(240, 240, 241));
        titre.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jScrollPane1.setViewportView(titre);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 344, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)), "Cash Book Account (Compte Caisse)", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jDateChooser1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jDateChooser1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jDateChooser1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jDateChooser1ActionPerformed(evt);
            }
        });

        jComboBox1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select one Caisse" }));
        jComboBox1.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                jComboBox1PopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        jComboBox4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Day", "Month", "Year" }));

        jComboBox5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Batchs" }));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBox4, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBox5, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );

        jPanel11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)), "Sold", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        usd.setEditable(false);
        usd.setBackground(new java.awt.Color(204, 204, 204));
        usd.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        usd.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        usd.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        usd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usdActionPerformed(evt);
            }
        });

        cdf.setEditable(false);
        cdf.setBackground(new java.awt.Color(204, 204, 204));
        cdf.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cdf.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        cdf.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("USD");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("CDF");

        caisse.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        caisse.setText("C");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(usd, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
                            .addComponent(cdf)))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(caisse)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addComponent(caisse)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(usd, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cdf, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)))
        );

        jPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)), "Sold Project", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

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

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setText("Debit:");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setText("Budget:");

        budget.setBackground(new java.awt.Color(0, 0, 153));
        budget.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        budget.setForeground(new java.awt.Color(255, 255, 255));
        budget.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        debitbudget.setEditable(false);
        debitbudget.setBackground(new java.awt.Color(0, 0, 153));
        debitbudget.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        debitbudget.setForeground(new java.awt.Color(255, 255, 255));
        debitbudget.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        reste.setEditable(false);
        reste.setBackground(new java.awt.Color(204, 0, 0));
        reste.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        reste.setForeground(new java.awt.Color(255, 255, 255));
        reste.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        creditbudget.setEditable(false);
        creditbudget.setBackground(new java.awt.Color(51, 153, 0));
        creditbudget.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        creditbudget.setForeground(new java.awt.Color(255, 255, 255));
        creditbudget.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        creditbudget.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                creditbudgetActionPerformed(evt);
            }
        });

        jYearChooser1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buss, 0, 324, Short.MAX_VALUE)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(budget)
                            .addComponent(debitbudget))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(reste, javax.swing.GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)
                            .addComponent(creditbudget))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jYearChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addComponent(buss, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(budget, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(reste, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(debitbudget, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(creditbudget, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jYearChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)), "Comptes", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

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

        compte1.setEditable(false);
        compte1.setBackground(new java.awt.Color(204, 204, 204));
        compte1.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        compte1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        compte1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                compte1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(compte)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(code_m, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(code, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(compte1, javax.swing.GroupLayout.Alignment.TRAILING))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(compte1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
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
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(3, 3, 3))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jPanel16.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)), "Wording", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        libele.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        libele.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jScrollPane2.setViewportView(libele);

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
        );

        jPanel17.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)), "Action 1", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jComboBox2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "...." }));

        jComboBox3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Credit", "Debit" }));
        jComboBox3.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                jComboBox3PopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        amount.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        amount.setText("0.00");
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
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(amount)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(amount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel18.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)), "Ohada", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jList2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jList2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jList2MouseEntered(evt);
            }
        });
        jScrollPane4.setViewportView(jList2);

        recherche.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        recherche.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                rechercheKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addComponent(recherche, javax.swing.GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                .addComponent(recherche, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
        );

        jPanel20.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)), "Action 3", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton1.setText("Save");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton2.setText("New");
        jButton2.setOpaque(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)
            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2))
        );

        jPanel19.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)), "LB Code", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        recherche1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        recherche1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                recherche1KeyReleased(evt);
            }
        });

        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Pref-01" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jList1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jList1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jList1MouseEntered(evt);
            }
        });
        jScrollPane5.setViewportView(jList1);

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(recherche1, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                .addComponent(recherche1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
        );

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Action 2", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jButton4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton4.setText("Advance");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        btwn_cashbook.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btwn_cashbook.setText("Between CashBook");
        btwn_cashbook.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        btwn_cashbook.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btwn_cashbookMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btwn_cashbook, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(btwn_cashbook, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4))
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jTable1.setBackground(new java.awt.Color(240, 240, 240));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTable1MousePressed(evt);
            }
        });
        jScrollPane3.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(32, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jMenu4.setText("X");
        jMenu4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jMenu4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu4MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu4);

        jMenu1.setText("File");

        jMenuItem3.setText("Sold");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuItem6.setText("Pref-01");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem6);

        jMenu3.setText("Import");

        jMenuItem7.setText("Excel");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem7);

        jMenuItem8.setText("Save Excel");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem8);

        jMenu1.add(jMenu3);

        jMenu5.setText("Online");

        jMenuItem9.setText("Upload");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem9);

        jMenuItem10.setText("Download");
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem10);

        jMenu1.add(jMenu5);

        jMenuItem11.setText("Search");
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem11);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");

        jMenuItem2.setText("Currency");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem2);

        jMenuItem5.setText("Principal Currency");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem5);

        jMenuBar1.add(jMenu2);

        jMenu6.setText("Reports");

        jMenuItem1.setText("Cash Book Report");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem1);

        jMenuItem4.setText("Receipt Out");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem4);

        jMenuBar1.add(jMenu6);

        jMenu7.setText("Ecart B");
        jMenuBar1.add(jMenu7);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setBounds(50, 30, 1006, 598);
    }// </editor-fold>//GEN-END:initComponents

    private void bussPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_bussPopupMenuWillBecomeInvisible
if(jComboBox1.getSelectedItem().equals("Select one Caisse")){
JOptionPane.showMessageDialog(null,"Wrong Data","Error",JOptionPane.ERROR_MESSAGE);
}else{
   budget_LIST1();
call_caisse();
search2BUS();}// TODO add your handling code here:
    }//GEN-LAST:event_bussPopupMenuWillBecomeInvisible

    private void compteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_compteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_compteActionPerformed

    private void jDateChooser1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jDateChooser1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jDateChooser1ActionPerformed

    private void compte1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_compte1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_compte1ActionPerformed

    private void jComboBox3PopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_jComboBox3PopupMenuWillBecomeInvisible
 DefaultListModel list = new DefaultListModel();
        if(jComboBox3.getSelectedItem().equals("Credit")){
  try{
            String sql="SELECT * FROM `budget_code` where CAT='"+buss.getSelectedItem()+"' order by CODE";
           
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
}else{
    list.addElement("Pref-01");
 jList1.setModel(list);
} // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox3PopupMenuWillBecomeInvisible

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

    private void jList2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList2MouseClicked
clic_attCall_IN_LIST7();                    // TODO add your handling code here:
    }//GEN-LAST:event_jList2MouseClicked

    private void jList2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList2MouseEntered
        //JOptionPane.showMessageDialog(null, jList1.getSelectedValue());     // TODO add your handling code here:
    }//GEN-LAST:event_jList2MouseEntered

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
//BigSave();
if(amount.getText().equals("")||amount.getText().equals("0.00")){
JOptionPane.showMessageDialog(null,"Wrong Data"+"\n"+"Amount = '"+amount.getText()+"' ","Error",JOptionPane.ERROR_MESSAGE);
}else{
    save();
callcaisebuss();
call_caisse();
search2BUS();
}




// TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void recherche1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_recherche1KeyReleased
 attCall_IN_LIST_serach_LB();    // TODO add your handling code here:
    }//GEN-LAST:event_recherche1KeyReleased

    private void jList1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList1MouseClicked
call_budget();              // TODO add your handling code here:
    }//GEN-LAST:event_jList1MouseClicked

    private void jList1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList1MouseEntered
        //JOptionPane.showMessageDialog(null, jList1.getSelectedValue());     // TODO add your handling code here:
    }//GEN-LAST:event_jList1MouseEntered

    private void jComboBox1PopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_jComboBox1PopupMenuWillBecomeInvisible
if(buss.getSelectedItem().equals("Select One Project")){
callcaisebuss();    
SELECT_buss();
}else{
   callcaisebuss();    
SELECT_buss();  
call_caisse();
search2BUS();
}// TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1PopupMenuWillBecomeInvisible

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
  avance_sur_salaire m = new avance_sur_salaire();
m.show();
 m. setDefaultCloseOperation(DISPOSE_ON_CLOSE);        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jMenu4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu4MouseClicked
this.dispose(); 
newdesingn.title.setText("Windows");// TODO add your handling code here:
    }//GEN-LAST:event_jMenu4MouseClicked

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
caisse_dates m= new  caisse_dates();
        m.show();
        m. setDefaultCloseOperation(DISPOSE_ON_CLOSE);       // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
monaiss m =new monaiss();

        m.show();
        m.setAlwaysOnTop(true);
        //m.setType(Type.UTILITY);
        //calculater f = new calculater(" frame not displayable in the task bar ");
        m. setDefaultCloseOperation(DISPOSE_ON_CLOSE);         // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM");
         String addDate = dateFormats.format(jDateChooser1.getDate());
           String sums=null;
        try{
          String sql="SELECT NUM FROM ohada_trans WHERE SUBSTR(NUM,5,7)='"+addDate+"'  ORDER BY NUM  ASC|DESC LIMIT 1";
             
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
           if(rs.next()){
            sums =rs.getString("num");
                // list.addElement(sums);
           }
            JOptionPane.showMessageDialog(null, sums);   
            }catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        } 
         // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void formInternalFrameActivated(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameActivated
newdesingn.title.setText("Cash Journal / Journal Caisse");        // TODO add your handling code here:
    }//GEN-LAST:event_formInternalFrameActivated

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
if(buss.getSelectedItem().equals("Select One Project")||jComboBox1.getSelectedItem().equals("Select one Caisse")){
JOptionPane.showMessageDialog(null,"Wrong Data","Error",JOptionPane.ERROR_MESSAGE);
}else{
        sold_caisse m= new sold_caisse();
        newdesingn.jDesktopPane1.add(m);
        m.show();
        m. setDefaultCloseOperation(DISPOSE_ON_CLOSE); 
}// TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jTable1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MousePressed
//doubleclick();        // TODO add your handling code here:
    }//GEN-LAST:event_jTable1MousePressed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
// TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
 monais m =new monais();

        m.show();
        m.setAlwaysOnTop(true);
        //m.setType(Type.UTILITY);
        //calculater f = new calculater(" frame not displayable in the task bar ");
        m. setDefaultCloseOperation(DISPOSE_ON_CLOSE);         // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
  
              lb.setText("Remboursement Pref-01");     
              codeb.setText("Pref-01");
              code_cat.setText("Pref-01");
             code_cat.setText("Pref-01");  
             code_subcat.setText("Pref-01");
            // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void btwn_cashbookMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btwn_cashbookMouseClicked
refresh();        // TODO add your handling code here:
    }//GEN-LAST:event_btwn_cashbookMouseClicked

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
imports();        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
if(jComboBox2.getSelectedItem().equals("USD")){
  Save_CAISSE_usd(); 
}else{
  Save_CAISSE(); 
}
             // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void usdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usdActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
//upload();        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
//        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem10ActionPerformed

    private void creditbudgetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_creditbudgetActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_creditbudgetActionPerformed

    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
search_caisse m = new search_caisse();
m.show();
m. setDefaultCloseOperation(DISPOSE_ON_CLOSE);        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem11ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField amount;
    private javax.swing.JRadioButton btwn_cashbook;
    public static javax.swing.JTextField budget;
    public static final javax.swing.JComboBox<String> buss = new javax.swing.JComboBox<>();
    private javax.swing.JLabel caisse;
    private javax.swing.JTextField cdf;
    public static final javax.swing.JTextField code = new javax.swing.JTextField();
    private javax.swing.JTextField code_cat;
    private javax.swing.JTextField code_m;
    private javax.swing.JTextField code_subcat;
    private javax.swing.JTextField codeb;
    private javax.swing.JTextField compte;
    private javax.swing.JTextField compte1;
    public static final javax.swing.JTextField creditbudget = new javax.swing.JTextField();
    public static javax.swing.JTextField debitbudget;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    public static final javax.swing.JComboBox<String> jComboBox1 = new javax.swing.JComboBox<>();
    public static final javax.swing.JComboBox<String> jComboBox2 = new javax.swing.JComboBox<>();
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    public static final javax.swing.JComboBox<String> jComboBox5 = new javax.swing.JComboBox<>();
    private com.alee.extended.date.WebDateField jDateChooser1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList<String> jList1;
    private javax.swing.JList<String> jList2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    public static final javax.swing.JTable jTable1 = new javax.swing.JTable();
    public static final com.toedter.calendar.JYearChooser jYearChooser1 = new com.toedter.calendar.JYearChooser();
    private javax.swing.JTextField lb;
    private javax.swing.JEditorPane libele;
    private javax.swing.JTextField recherche;
    private javax.swing.JTextField recherche1;
    public static javax.swing.JTextField reste;
    private javax.swing.JEditorPane titre;
    public static final javax.swing.JTextField usd = new javax.swing.JTextField();
    // End of variables declaration//GEN-END:variables
}
