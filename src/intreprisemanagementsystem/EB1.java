/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intreprisemanagementsystem;

import java.awt.HeadlessException;
import java.awt.Toolkit;
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
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import net.proteanit.sql.DbUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Administrator
 */
public class EB1 extends javax.swing.JFrame {

   Connection con=null;
PreparedStatement pst=null;
ResultSet rs= null;
String etrolls,roll_id,etrolls_log;
DefaultTableModel mode;
int xx=0;
int yy=0;
    public EB1() {
        initComponents();
            this.setExtendedState(JFrame.MAXIMIZED_BOTH);
         setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("icons8_Raspberry_Pi_48px_1.png")));
         con=JavaDbConnect.dbConnect();
         etjComboBox6.addItem("EMPLOYEE");
        etjComboBox6.addItem("CONSULTANT");
        
         etsup1.setEditable(false);
            etchat.setEditable(false);
            etpu.setEditable(false);
            jDateChooser1.setDate(new Date());
            call();
      
    }
 // AUTO ROLL NUMBER
    
    public void call(){
    
     try{
            String sql="select CAT from caisse_dispacting WHERE NAME='"+Home_page.home.user.getText()+"' and ACCESS='Yes'";
           
           
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
          String sql="SELECT * FROM currency";
             
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                //String sum=rs.getString("nom");
                String sums=rs.getString("APPR");
                
                
               jComboBox1.addItem(sums);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
        
    }
    
     public void etroll()
     {
        String NUMS = null;
        int SUSTRING = 0;
           try{
          String sql="SELECT * FROM  transaction_number WHERE PROJET='"+buss.getSelectedItem()+"' AND NAME='EB'";
          
             
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
           if(rs.next()){
                //String sum=rs.getString("nom");
                NUMS=rs.getString("NUM");
                SUSTRING=rs.getInt("SUBSTR");
                
                
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
          // JOptionPane.showMessageDialog(null, NUMS);
            try{
          String sql="SELECT * FROM  etat_de_besoin WHERE buss='"+buss.getSelectedItem()+"' AND NUM='"+NUMS+"' ";
          
             
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
           if(rs.next()){
           try{
             String sqls="SELECT NUM FROM etat_de_besoin  WHERE ID=(SELECT MAX(ID) FROM etat_de_besoin WHERE ORIENTATION ='FINANCE' AND BUSS='"+buss.getSelectedItem()+"')  ORDER BY NUM DESC LIMIT 1";
             pst=con.prepareStatement(sqls);
             rs=pst.executeQuery();
             if(rs.next()){
                 String rl=rs.getString("NUM");
                 int ln=rl.length();
                 String stxt=rl.substring(0,SUSTRING);
                 String snum=rl.substring(SUSTRING,ln);
                 //sum=001
                 int n = Integer.parseInt(snum);
                 //n=1
                 //001,010,100,101,167,1799
                 n++;
                 snum=Integer.toString(n);
                 if(n < 10){
                 etrolls=stxt+"00"+snum;//001 ou 009
                 }else if(n < 100){
                 etrolls=stxt+"0"+snum;//010 ou 099
                 }else if(n > 100){
                 etrolls=stxt+snum;// 100 ou 199
                 }  else if(n > 1000){
                 etrolls=stxt+snum;// 1000 ou 1999
                 }     
             }else{
                 //rolls="FICHE/EB/2018/1";
               //  etrolls="EB1001";
                 etrolls=NUMS;
             }
                 
             
         }catch(Exception e){
             JOptionPane.showMessageDialog(null, e); 
             
         }     
                
            }else{
           etrolls=NUMS;
           }
         //  JOptionPane.showMessageDialog(null,etrolls);
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
         
            
     }
     
     
     
     public void rollid(){
     
     try{
             String sql="SELECT NUM_ID FROM etat_de_besoin where ORIENTATION='FINANCE' AND NUM='"+num.getText()+"' ORDER BY NUM_ID DESC LIMIT 1";
             pst=con.prepareStatement(sql);
             rs=pst.executeQuery();
             if(rs.next()){
                 String rl=rs.getString("NUM_ID");
                 int n = Integer.parseInt(rl);
                
                 n++;
               String snum=Integer.toString(n);
                 roll_id=snum;
             }else{
                 roll_id="1";
             }
                 
             
         }catch(Exception e){
             JOptionPane.showMessageDialog(null, e);}
     
     }
     
     public void rollid_log(){
     
     try{
             String sql="SELECT NUM_ID FROM etat_de_besoin where ORIENTATION='LOGISTIQUE' AND NUM='"+num.getText()+"' ORDER BY NUM_ID DESC LIMIT 1";
             pst=con.prepareStatement(sql);
             rs=pst.executeQuery();
             if(rs.next()){
                 String rl=rs.getString("NUM_ID");
                 int n = Integer.parseInt(rl);
                
                 n++;
               String snum=Integer.toString(n);
                 roll_id=snum;
             }else{
                 roll_id="1";
             }
                 
             
         }catch(Exception e){
             JOptionPane.showMessageDialog(null, e);}
     
     }
     
     public void etroll_log()
     {
         try{
             String sql="SELECT NUM FROM etat_de_besoin  where ORIENTATION='LOGISTIQUE'ORDER BY NUM DESC LIMIT 1";
             pst=con.prepareStatement(sql);
             rs=pst.executeQuery();
             if(rs.next()){
                 String rl=rs.getString("NUM");
                 int ln=rl.length();
                 String stxt=rl.substring(0,2);
                 String snum=rl.substring(2,ln);
                 int n = Integer.parseInt(snum);
                 n++;
                 snum=Integer.toString(n);
                 etrolls_log=stxt+snum;
                 
                 
             }else{
                 //rolls="FICHE/EB/2018/1";
                 etrolls_log="EB1001";
             }
                 
             
         }catch(Exception e){
             JOptionPane.showMessageDialog(null, e); 
             
         }
     }
     
  public void attCall_roll()
    {
      
         String A =JOptionPane.showInputDialog("Please Entre Your Roll Number!!!"); 
        try{
            String sql="select * from employee where ROLLNUM= ? and ACTIVE='Active'";
            
           
             pst=con.prepareStatement(sql);
            pst.setString(1, A);
            rs=pst.executeQuery();
            if(rs.next()){
             String add1 = rs.getString("NAME");
             String add2 = rs.getString("LNAME");
              etsup1.setText(add1+" "+add2);
             
             String adde = rs.getString("rollNUM");
             etroll1.setText(adde);
              
              
              
               try{
            String sqls="select * from employee where ROLLNUM= ?";
            
           
             pst=con.prepareStatement(sqls);
            pst.setString(1, A);
            rs=pst.executeQuery();
            if(rs.next()){
             String add11 = rs.getString("TITRE");
           etchat.setText(add11);
             
             
              // String add4 = rs.getString("ROLL");
              // etroll1.setText(add4);
            }
            
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
              
              
              
              
              
              
              
              // String add4 = rs.getString("ROLL");
              // etroll1.setText(add4);
            }else{JOptionPane.showMessageDialog(null, "Invalid Roll No. or Does not Have Info");  
            
              attCall_roll();
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
   
    } 

public void save(){
    Double qty = Double.parseDouble(etqty.getText());
    Double up = Double.parseDouble(etpu.getText());
    Double pt= qty*up;
    String tmp=jComboBox2.getSelectedItem().toString();
    String orientation;
    if(tmp.equals("Log")){
        orientation="LOGISTIQUE";
        
     etroll_log();
  rollid_log();
if(num.getText().equals("")){
       try {
            
        PreparedStatement pst = con.prepareStatement("INSERT INTO etat_de_besoin(SUP,CHANT,NUM,DET,QTY,PU,PT,DATES,APPROUVATION,EXCECUTE,PRINT,PAY,ORIENTATION,ECRITURE,NUM_TRANS,ETUDE,BUSS,DEVICE,UNITE,NUM_ID) "
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        
        pst.setString(1, etsup1.getText());
         pst.setString(2, etchat.getText());
        pst.setString(3, etrolls_log);
         pst.setString(4, etdet.getText());
         pst.setString(5, etqty.getText());
         pst.setString(6, etpu.getText());
         pst.setDouble(7, pt);
         pst.setString(9, "");
          pst.setString(10,"");
          pst.setString(11,"");
          pst.setString(12,"");
            pst.setString(13,orientation);
              pst.setString(14,"");
                pst.setString(15,"");
                  pst.setString(16,"");
                  pst.setString(17,buss.getSelectedItem().toString());
                  pst.setString(18,jComboBox1.getSelectedItem().toString());
                   pst.setString(19,jComboBox3.getSelectedItem().toString());
                    pst.setString(20,roll_id);
       
          SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(jDateChooser1.getDate());
         pst.setString(8, addDate);
         
          pst.executeUpdate();
        
              //   JOptionPane.showMessageDialog(null,"CLERCK_IN  SUCCESSFUL BONNE JOURNEE!!!        ");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
         try{
            String sqls="select MAX(NUM) from etat_de_besoin WHERE ORIENTATION='LOGISTIQUE'";
            
           
             pst=con.prepareStatement(sqls);
          //  pst.setString(1, A);
            rs=pst.executeQuery();
            if(rs.next()){
             String add11 = rs.getString("MAX(NUM)");
           num.setText(add11);
             
             
              // String add4 = rs.getString("ROLL");
              // etroll1.setText(add4);
            }
            
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
          Table();
    }else{
    
    int aa=0;     
           try{
            String sqls="select NUM_ID from etat_de_besoin WHERE ID=(SELECT MAX(ID) FROM etat_de_besoin WHERE NUM='"+num.getText()+"')";
            
           
             pst=con.prepareStatement(sqls);
          //  pst.setString(1, A);
            rs=pst.executeQuery();
            if(rs.next()){
             int add11 = rs.getInt("NUM_ID");
           aa=add11+1;
            }
            
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
           
          try {
        // int ids=0;
       PreparedStatement pst = con.prepareStatement("INSERT INTO etat_de_besoin(SUP,CHANT,NUM,DET,QTY,PU,PT,DATES,APPROUVATION,EXCECUTE,PRINT,PAY,ORIENTATION,ECRITURE,NUM_TRANS,ETUDE,BUSS,DEVICE,UNITE,NUM_ID) "
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        
        pst.setString(1, etsup1.getText());
         pst.setString(2, etchat.getText());
        pst.setString(3, num.getText());
         pst.setString(4, etdet.getText());
         pst.setString(5, etqty.getText());
         pst.setString(6, etpu.getText());
         pst.setDouble(7, pt);
         pst.setString(9, "");
          pst.setString(10,"");
          pst.setString(11,"");
          pst.setString(12,"");
           pst.setString(13,orientation);
           pst.setString(14,"");
                pst.setString(15,"");
                  pst.setString(16,"");
                  pst.setString(17,buss.getSelectedItem().toString());
                  pst.setString(18,jComboBox1.getSelectedItem().toString());
                   pst.setString(19,jComboBox3.getSelectedItem().toString());
                    pst.setInt(20,aa);
                
       
          SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(jDateChooser1.getDate());
         pst.setString(8, addDate);
         
          pst.executeUpdate();
        
              //   JOptionPane.showMessageDialog(null,"CLERCK_IN  SUCCESSFUL BONNE JOURNEE!!!        ");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    } 
            Table();
          //  Table();
    etqty.setText("Qty");
etpu.setText("up");
etdet.setText("Wording"); 
        }    
        
    
    }else{
        orientation="FINANCE";
         etroll();
  rollid();
       if(num.getText().equals("")){
       try {
            
        PreparedStatement pst = con.prepareStatement("INSERT INTO etat_de_besoin(SUP,CHANT,NUM,DET,QTY,PU,PT,DATES,APPROUVATION,EXCECUTE,PRINT,PAY,ORIENTATION,ECRITURE,NUM_TRANS,ETUDE,BUSS,DEVICE,UNITE,NUM_ID) "
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        
        pst.setString(1, etsup1.getText());
         pst.setString(2, etchat.getText());
        pst.setString(3, etrolls);
         pst.setString(4, etdet.getText());
         pst.setString(5, etqty.getText());
         pst.setString(6, etpu.getText());
         pst.setDouble(7, pt);
         pst.setString(9, "");
          pst.setString(10,"");
          pst.setString(11,"");
          pst.setString(12,"");
            pst.setString(13,orientation);
              pst.setString(14,"");
                pst.setString(15,"");
                  pst.setString(16,"");
                  pst.setString(17,buss.getSelectedItem().toString());
                  pst.setString(18,jComboBox1.getSelectedItem().toString());
                   pst.setString(19,jComboBox3.getSelectedItem().toString());
                    pst.setString(20,roll_id);
       
          SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(jDateChooser1.getDate());
         pst.setString(8, addDate);
         
          pst.executeUpdate();
        
              //   JOptionPane.showMessageDialog(null,"CLERCK_IN  SUCCESSFUL BONNE JOURNEE!!!        ");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
         try{
           // String sqls="select MAX(NUM) from etat_de_besoin WHERE ORIENTATION='FINANCE' and BUSS='"+buss.getSelectedItem()+"'";
            
          String sqls="SELECT NUM FROM etat_de_besoin  WHERE ID=(SELECT MAX(ID) FROM etat_de_besoin WHERE ORIENTATION ='FINANCE' AND BUSS='"+buss.getSelectedItem()+"')  ORDER BY NUM DESC LIMIT 1";
            
             pst=con.prepareStatement(sqls);
          //  pst.setString(1, A);
            rs=pst.executeQuery();
            if(rs.next()){
             String add11 = rs.getString("NUM");
           num.setText(add11);
             
             
              // String add4 = rs.getString("ROLL");
              // etroll1.setText(add4);
            }
            
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
    }else{
      int aa=0;     
           try{
            String sqls="select NUM_ID from etat_de_besoin WHERE ID=(SELECT MAX(ID) FROM etat_de_besoin WHERE NUM='"+num.getText()+"')";
            
           
             pst=con.prepareStatement(sqls);
          //  pst.setString(1, A);
            rs=pst.executeQuery();
            if(rs.next()){
             int add11 = rs.getInt("NUM_ID");
           aa=add11+1;
             
             
              // String add4 = rs.getString("ROLL");
              // etroll1.setText(add4);
            }
            
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
           
          try {
           
           
        PreparedStatement pst = con.prepareStatement("INSERT INTO etat_de_besoin(SUP,CHANT,NUM,DET,QTY,PU,PT,DATES,APPROUVATION,EXCECUTE,PRINT,PAY,ORIENTATION,ECRITURE,NUM_TRANS,ETUDE,BUSS,DEVICE,UNITE,NUM_ID) "
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        
        pst.setString(1, etsup1.getText());
         pst.setString(2, etchat.getText());
        pst.setString(3, num.getText());
         pst.setString(4, etdet.getText());
         pst.setString(5, etqty.getText());
         pst.setString(6, etpu.getText());
         pst.setDouble(7, pt);
         pst.setString(9, "");
          pst.setString(10,"");
          pst.setString(11,"");
          pst.setString(12,"");
           pst.setString(13,orientation);
           pst.setString(14,"");
                pst.setString(15,"");
                  pst.setString(16,"");
                  pst.setString(17,buss.getSelectedItem().toString());
                  pst.setString(18,jComboBox1.getSelectedItem().toString());
                   pst.setString(19,jComboBox3.getSelectedItem().toString());
                    pst.setInt(20,aa);
                
       
          SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(jDateChooser1.getDate());
         pst.setString(8, addDate);
         
          pst.executeUpdate();
        
              //   JOptionPane.showMessageDialog(null,"CLERCK_IN  SUCCESSFUL BONNE JOURNEE!!!        ");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    } 
        }

   NumberFormat nf = new DecimalFormat("#,###,###");
   try{
            String sqls="select SUM(PT) from etat_de_besoin WHERE NUM='"+num.getText()+"' ";
            
           
             pst=con.prepareStatement(sqls);
          //  pst.setString(1, A);
            rs=pst.executeQuery();
            if(rs.next()){
             Double add11 = rs.getDouble("SUM(PT)");
              String fnn = nf.format(add11);
                  num1.setText(fnn);
            }
            
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
  Table();
  //refreshe(); 
    etqty.setText("Qty");
etpu.setText("up");
etdet.setText("Wording");
        
    }
   

} 
  // DELETE
    public void etdelete()
    {
        int row= etjTable3.getSelectedRow();
         // String rows =jTable1.getName()
        String  COMPTES = (etjTable3.getModel().getValueAt(row,0). toString());
         try{
        String sql = "DELETE FROM etat_de_besoin WHERE NUM_ID=? AND NUM='"+num.getText()+"'";
        
         pst = con.prepareStatement(sql);
         pst.setString(1,COMPTES);
         pst.executeUpdate();

     }catch(SQLException | HeadlessException ex ){
         JOptionPane.showMessageDialog(null,ex);
     } 
          NumberFormat nf = new DecimalFormat("#,###,###");
          try{
            String sqls="select SUM(PT) from etat_de_besoin WHERE NUM='"+num.getText()+"' ";
            
           
             pst=con.prepareStatement(sqls);
          //  pst.setString(1, A);
            rs=pst.executeQuery();
            if(rs.next()){
            Double add11 = rs.getDouble("SUM(PT)");
          
            String fnn = nf.format(add11);
                  num1.setText(fnn);
             
             
              // String add4 = rs.getString("ROLL");
              // etroll1.setText(add4);
            }
            
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
         Table();
    }
    
    public void etdeletee()
    {
         try{
        String sql = "DELETE FROM etat_de_besoin WHERE NUM=?";
        
         pst = con.prepareStatement(sql);
         pst.setString(1,num.getText());
         pst.executeUpdate();

     }catch(SQLException | HeadlessException ex ){
         JOptionPane.showMessageDialog(null,ex);
     } 
         Table();
    }
    
    
public void Table()
    {
         try{
           
             String sql="SELECT NUM_ID AS NUM,`DET` as 'Description', `QTY` AS 'Quantity', `PU` AS 'Unity By Price', `PT` as 'Total Price', `DATES` as 'Delivery Date' FROM `etat_de_besoin` WHERE `NUM`='"+num.getText()+"'";
     //  String sql="SELECT ID, `NUM_FACTURE`, `DATE_FACTURE`, `DESCRIPTION`, `DEBIT_QTY`, `DEBIT_PU`, `DEBIT_PT`, `DEBIT_TVA`, `CREDIT_QTY`, `CREDIT_PU`, `CREDIT_PT`, `CREDIT_TVA`, `DATES`, `NUM` FROM facture_fournisseur where compte='"+jComboBox6.getSelectedItem()+"'";
       pst = con.prepareStatement(sql);
      rs= pst.executeQuery();
      
       
      etjTable3.setModel(DbUtils.resultSetToTableModel(rs));
       mode=new DefaultTableModel();
       
       
        TableColumn col0=etjTable3.getColumnModel().getColumn(0);
        TableColumn col1=etjTable3.getColumnModel().getColumn(1);
        TableColumn col2=etjTable3.getColumnModel().getColumn(2);
        TableColumn col3=etjTable3.getColumnModel().getColumn(3);
        TableColumn col4=etjTable3.getColumnModel().getColumn(4);
        TableColumn col5=etjTable3.getColumnModel().getColumn(5);
      //  TableColumn col5=etjTable3.getColumnModel().getColumn(5);

       
      
       col0.setPreferredWidth(50);
       col1.setPreferredWidth(650);
       col2.setPreferredWidth(50);
       col3.setPreferredWidth(50);
       col4.setPreferredWidth(50);
       col5.setPreferredWidth(50);
      // col5.setPreferredWidth(50);
      
      
      
       
       
      // jTable1.setModel(mode);
       
     
    }catch(SQLException ex ){
     JOptionPane.showMessageDialog(null, ex);
}
    } 



public void Tables()
    {
         try{
           
             String sql="SELECT NUM_ID AS NUM,`DET` as 'Description', `QTY` AS 'Quantity', `PU` AS 'Unity By Price', `PT` as 'Total Price', `DATES` as 'Delivery Date' FROM `etat_de_besoin` WHERE `NUM`='"+num.getText()+"' and SUP='"+etsup1.getText()+"'";
     //  String sql="SELECT ID, `NUM_FACTURE`, `DATE_FACTURE`, `DESCRIPTION`, `DEBIT_QTY`, `DEBIT_PU`, `DEBIT_PT`, `DEBIT_TVA`, `CREDIT_QTY`, `CREDIT_PU`, `CREDIT_PT`, `CREDIT_TVA`, `DATES`, `NUM` FROM facture_fournisseur where compte='"+jComboBox6.getSelectedItem()+"'";
       pst = con.prepareStatement(sql);
      rs= pst.executeQuery();
      
       
      etjTable3.setModel(DbUtils.resultSetToTableModel(rs));
       mode=new DefaultTableModel();
       
       
        TableColumn col0=etjTable3.getColumnModel().getColumn(0);
        TableColumn col1=etjTable3.getColumnModel().getColumn(1);
        TableColumn col2=etjTable3.getColumnModel().getColumn(2);
        TableColumn col3=etjTable3.getColumnModel().getColumn(3);
        TableColumn col4=etjTable3.getColumnModel().getColumn(4);
        TableColumn col5=etjTable3.getColumnModel().getColumn(5);
      //  TableColumn col5=etjTable3.getColumnModel().getColumn(5);

       
      
       col0.setPreferredWidth(50);
       col1.setPreferredWidth(650);
       col2.setPreferredWidth(50);
       col3.setPreferredWidth(50);
       col4.setPreferredWidth(50);
       col5.setPreferredWidth(50);
      // col5.setPreferredWidth(50);
      
      
      
       
       
      // jTable1.setModel(mode);
       
     
    }catch(SQLException ex ){
     JOptionPane.showMessageDialog(null, ex);
}
    } 


public void refreshe(){
etqty.setText("Qty");
etpu.setText("up");
etdet.setText("Wording");
buss.setSelectedItem("Select One Project");

}

public void importexcel(){

    etjTable3.setModel(new DefaultTableModel());
  mode=new DefaultTableModel();
        mode.addColumn("No");
        mode.addColumn("Libelle"); 
        mode.addColumn("Qtes");
        mode.addColumn("Utes");
        mode.addColumn("UP");
        mode.addColumn("PT");
        mode.addColumn("DEVISE");
       
       etjTable3.setModel(mode);
        
   DefaultTableModel excels= (DefaultTableModel)etjTable3.getModel(); 
  //  DefaultTableModel excels= (DefaultTableModel)jTable3.getModel(); 
        
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
      
     
      for(int i=4;i<excelSheet.getLastRowNum(); i++){
      
      XSSFRow excelRow = excelSheet.getRow(i);
      XSSFCell cell = excelRow.getCell(0);
      XSSFCell cell1 = excelRow.getCell(1);
      XSSFCell cell2 = excelRow.getCell(2);
      XSSFCell cell3 = excelRow.getCell(3);
      XSSFCell cell4 = excelRow.getCell(4);
      XSSFCell cell5 = excelRow.getCell(5);
      XSSFCell cell6 = excelRow.getCell(6);
     
     
     excels.addRow(new Object[]{cell,cell1,cell2,cell3,cell4,cell5,cell6}); 
      }
      
      }catch(FileNotFoundException ex){
      ex.printStackTrace();
      }     catch (IOException ex) {
               ex.printStackTrace();
            }
      etdet.setText("File Imported");
      //budget.setText("Load");
      } 

}
public void SaveBudget(){
     etroll();
   
   DefaultTableModel excels= (DefaultTableModel)etjTable3.getModel(); 
   String NO= null,LIBELLE = null,QTY,UNITY,PU,PT = null;
    for(int i=0; i < excels.getRowCount();i++){
   NO  = excels.getValueAt(i,0). toString();
   LIBELLE  = excels.getValueAt(i,1). toString();
   QTY = excels.getValueAt(i,2). toString();
   UNITY = excels.getValueAt(i,3). toString();
   PU = excels.getValueAt(i,4). toString();
   PT = excels.getValueAt(i,5). toString();
   
         
         
          
           try {
     
         PreparedStatement pst = con.prepareStatement("INSERT INTO etat_de_besoin(SUP,CHANT,NUM,DET,QTY,PU,PT,DATES,APPROUVATION,EXCECUTE,PRINT,PAY,ORIENTATION,ECRITURE,NUM_TRANS,ETUDE,BUSS,DEVICE,UNITE,NUM_ID) "
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        
        pst.setString(1, etsup1.getText());
         pst.setString(2, etchat.getText());
        pst.setString(3, etrolls);
         pst.setString(4, LIBELLE);
         pst.setString(5, QTY);
         pst.setString(6, PU);
         pst.setString(7, PT);
         pst.setString(9, "");
          pst.setString(10,"");
          pst.setString(11,"");
          pst.setString(12,"");
           pst.setString(13,"FINANCE");
           pst.setString(14,"");
                pst.setString(15,"");
                  pst.setString(16,"");
                  pst.setString(17,buss.getSelectedItem().toString());
                  pst.setString(18,jComboBox1.getSelectedItem().toString());
                   pst.setString(19,jComboBox3.getSelectedItem().toString());
                    pst.setString(20,NO);
                
       
          SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(jDateChooser1.getDate());
         pst.setString(8, addDate);
         
          pst.executeUpdate();
         
               //  JOptionPane.showMessageDialog(null,"data saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }   
        }
 
            
          
   
   try{
            String sqls="select MAX(NUM) from etat_de_besoin WHERE ORIENTATION='FINANCE' AND BUSS='"+buss.getSelectedItem()+"'";
            
           
             pst=con.prepareStatement(sqls);
          //  pst.setString(1, A);
            rs=pst.executeQuery();
            if(rs.next()){
             String add11 = rs.getString("MAX(NUM)");
           num.setText(add11);
             
             
              // String add4 = rs.getString("ROLL");
              // etroll1.setText(add4);
            }
            
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
      NumberFormat nf = new DecimalFormat("#,###,###");
   try{
            String sqls="select SUM(PT) from etat_de_besoin WHERE NUM='"+num.getText()+"' ";
            
           
             pst=con.prepareStatement(sqls);
          //  pst.setString(1, A);
            rs=pst.executeQuery();
            if(rs.next()){
             Double add11 = rs.getDouble("SUM(PT)");
              String fnn = nf.format(add11);
                  num1.setText(fnn);
            }
            
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
   Table();
    JOptionPane.showMessageDialog(null,"Tranction Saved");
  }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator3 = new javax.swing.JSeparator();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        num = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        num1 = new javax.swing.JTextField();
        jDateChooser1 = new com.alee.extended.date.WebDateField();
        buss = new javax.swing.JComboBox<>();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        etjComboBox6 = new javax.swing.JComboBox<>();
        etsup1 = new javax.swing.JTextField();
        etchat = new javax.swing.JTextField();
        etroll1 = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        textAreaScroll3 = new textarea.TextAreaScroll();
        etqty = new Palette.TextField();
        etpu = new Palette.TextField();
        jComboBox3 = new Palette.Combobox();
        jComboBox1 = new Palette.Combobox();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        etjTable3 = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setType(java.awt.Window.Type.UTILITY);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel2.setBackground(java.awt.SystemColor.activeCaption);
        jPanel2.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jPanel2MouseDragged(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(255, 0, 0));
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("X");
        jLabel1.setOpaque(true);
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        num.setBackground(new java.awt.Color(240, 240, 241));
        num.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        num.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        num.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        num.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                numKeyReleased(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Etats de Besoin");
        jLabel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        num1.setEditable(false);
        num1.setBackground(new java.awt.Color(240, 240, 241));
        num1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        num1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        num1.setText("0.00");
        num1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jDateChooser1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jDateChooser1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jDateChooser1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jDateChooser1ActionPerformed(evt);
            }
        });

        buss.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        buss.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select One Project" }));
        buss.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jComboBox2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Fin", "Log" }));
        jComboBox2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jComboBox2.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                jComboBox2PopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_Microsoft_Excel_30px.png"))); // NOI18N
        jLabel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });

        jLabel4.setBackground(new java.awt.Color(51, 255, 51));
        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_Safe_Out_16px.png"))); // NOI18N
        jLabel4.setText("Clerck");
        jLabel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });

        jSeparator4.setOrientation(javax.swing.SwingConstants.VERTICAL);

        etjComboBox6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        etjComboBox6.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        etjComboBox6.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                etjComboBox6PopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        etsup1.setBackground(new java.awt.Color(240, 240, 241));
        etsup1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        etsup1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        etsup1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                etsup1KeyReleased(evt);
            }
        });

        etchat.setBackground(new java.awt.Color(240, 240, 241));
        etchat.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        etchat.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        etchat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                etchatActionPerformed(evt);
            }
        });
        etchat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                etchatKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                etchatKeyTyped(evt);
            }
        });

        etroll1.setEditable(false);
        etroll1.setBackground(new java.awt.Color(240, 240, 241));
        etroll1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        etroll1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        etroll1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(etjComboBox6, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(buss, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(etsup1))
                .addGap(0, 0, 0)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(num1, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(num, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(etchat)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(etroll1, javax.swing.GroupLayout.PREFERRED_SIZE, 510, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(num)
                            .addComponent(num1)
                            .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(etchat, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                            .addComponent(etroll1)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(etjComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(buss, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                            .addComponent(jComboBox2, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(etsup1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))))
            .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jButton3.setText("Rmv");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton2.setText("Add");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton4.setText("Send");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        textAreaScroll3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        textAreaScroll3.setLabelText("Libelle");

        etdet.setColumns(20);
        etdet.setRows(5);
        textAreaScroll3.setViewportView(etdet);

        etqty.setText("0.00");
        etqty.setLabelText("Quantités");
        etqty.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                etqtyMouseClicked(evt);
            }
        });
        etqty.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                etqtyKeyTyped(evt);
            }
        });

        etpu.setText("0.00");
        etpu.setLabelText("Prix unitaire");
        etpu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                etpuMouseClicked(evt);
            }
        });
        etpu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                etpuKeyTyped(evt);
            }
        });

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "", "Pcs", "Kg", "M", "Sac", "Ltr", "g", "Ram", "Autre" }));
        jComboBox3.setLabeText("Unités");

        jComboBox1.setLabeText("Monnaies");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(textAreaScroll3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(etqty, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(etpu, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(textAreaScroll3, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(etqty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, 0)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(etpu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4)
                .addContainerGap())
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        etjTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        etjTable3.setFocusable(false);
        etjTable3.setRowHeight(30);
        jScrollPane1.setViewportView(etjTable3);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 409, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel5.setBackground(new java.awt.Color(51, 51, 255));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 21, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel1MouseClicked

    private void etjComboBox6PopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_etjComboBox6PopupMenuWillBecomeInvisible
        if(etjComboBox6.getSelectedItem().equals("CONSULTANT")){//&&etjComboBox6.getSelectedItem().equals("EMPLOYE")){
            etsup1.setEditable(true);
            etchat.setEditable(true);
        }else{
            etsup1.setEditable(false);
            etchat.setEditable(false);
        }// TODO add your handling code here:
    }//GEN-LAST:event_etjComboBox6PopupMenuWillBecomeInvisible

    private void etsup1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_etsup1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_etsup1KeyReleased

    private void etchatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_etchatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_etchatActionPerformed

    private void etchatKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_etchatKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_etchatKeyReleased

    private void etchatKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_etchatKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_etchatKeyTyped

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
try{
 con=JavaDbConnect.dbConnect(); 
  etdelete(); 

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

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
try{
 con=JavaDbConnect.dbConnect(); 
if(etdet.getText().equals("File Imported")){
      if(etsup1.getText().equals("")||buss.getSelectedItem().equals("Select One Project")||jComboBox1.getSelectedItem().equals(".......$")){
        JOptionPane.showMessageDialog(null,"Wrong Data","Error",JOptionPane.ERROR_MESSAGE);
      }else{
 SaveBudget();}
 }else{
        if(etsup1.getText().equals("")||buss.getSelectedItem().equals("Select One Project")||jComboBox1.getSelectedItem().equals(".......$")){
        JOptionPane.showMessageDialog(null,"Wrong Data","Error",JOptionPane.ERROR_MESSAGE);
 }else{
        save(); 
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
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed

        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
try{
 con=JavaDbConnect.dbConnect(); 
 int response = JOptionPane.showConfirmDialog(null, "DO YOU WANT TO SEND THIS FOR APPROUVEMENT?", "Confirm",
            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (response == JOptionPane.YES_OPTION) {
            etsup1.setText("");
            etchat.setText("");
            etroll1.setText("");
            num.setText("");
            num1.setText("0.00");
             etpu.setText("0.00");
             etpu.setEditable(false);
            etjTable3.setModel(new DefaultTableModel());
            refreshe();
            this.dispose();
        }else{
            etdeletee();
            etsup1.setText("");
            etchat.setText("");
            etroll1.setText("");
            num.setText("");
            num1.setText("0.00");
            etjTable3.setModel(new DefaultTableModel());
            refreshe();
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
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jPanel2MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseDragged
int x=evt.getXOnScreen();
       int y=evt.getYOnScreen();
       this.setLocation(x-xx, y-yy);        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel2MouseDragged

    private void jComboBox2PopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_jComboBox2PopupMenuWillBecomeInvisible
if(jComboBox2.getSelectedItem().equals("Fin")){
etpu.setEditable(true);
jComboBox1.setSelectedItem(".......$");
}else{
etpu.setEditable(false);
jComboBox1.setSelectedItem("USD");
}        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox2PopupMenuWillBecomeInvisible

    private void jDateChooser1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jDateChooser1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jDateChooser1ActionPerformed

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
try{
 con=JavaDbConnect.dbConnect(); 
 etsup1.setText("");
        etchat.setText("");
        etroll1.setText("");
        num.setText("");
        etjTable3.setModel(new DefaultTableModel());
       etqty.setText("Qty");
etpu.setText("up");
etdet.setText("Wording");
//buss.setSelectedItem("Select One Project");
        attCall_roll();   

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
    }//GEN-LAST:event_jLabel4MouseClicked

    private void numKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_numKeyReleased
try{
 con=JavaDbConnect.dbConnect(); 
     Tables(); 

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
    }//GEN-LAST:event_numKeyReleased

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
importexcel();        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel3MouseClicked

    private void etqtyKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_etqtyKeyTyped
      char c=evt.getKeyChar();
        if(!(Character.isDigit(c) || (c==KeyEvent.VK_BACK_SPACE)||c==KeyEvent.VK_DELETE ||c==KeyEvent.VK_PERIOD )){
            evt.consume();
        }         // TODO add your handling code here:
    }//GEN-LAST:event_etqtyKeyTyped

    private void etqtyMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_etqtyMouseClicked
   etqty.setText("");
        etpu.setText("0.0");        // TODO add your handling code here:
    }//GEN-LAST:event_etqtyMouseClicked

    private void etpuKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_etpuKeyTyped
    char c=evt.getKeyChar();
        if(!(Character.isDigit(c) || (c==KeyEvent.VK_BACK_SPACE)||c==KeyEvent.VK_DELETE ||c==KeyEvent.VK_PERIOD )){
            evt.consume();
        }          // TODO add your handling code here:
    }//GEN-LAST:event_etpuKeyTyped

    private void etpuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_etpuMouseClicked

        etpu.setText("");         // TODO add your handling code here:
    }//GEN-LAST:event_etpuMouseClicked

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
            java.util.logging.Logger.getLogger(EB1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EB1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EB1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EB1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EB1().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> buss;
    private javax.swing.JTextField etchat;
    public static final textarea.TextArea etdet = new textarea.TextArea();
    private javax.swing.JComboBox<String> etjComboBox6;
    private javax.swing.JTable etjTable3;
    private Palette.TextField etpu;
    private Palette.TextField etqty;
    private javax.swing.JTextField etroll1;
    private javax.swing.JTextField etsup1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private Palette.Combobox jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private Palette.Combobox jComboBox3;
    private com.alee.extended.date.WebDateField jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JTextField num;
    private javax.swing.JTextField num1;
    private textarea.TextAreaScroll textAreaScroll3;
    // End of variables declaration//GEN-END:variables
}
