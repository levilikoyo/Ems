/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package journals;

import Home_page.home;
import static Home_page.menu_finance.jc;
import intreprisemanagementsystem.JavaDbConnect;
import intreprisemanagementsystem.avance_sur_salaire;
import java.awt.Component;
import java.awt.Container;
import java.awt.Desktop;
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import net.proteanit.sql.DbUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Doshe PC
 */
public class journal1 extends javax.swing.JInternalFrame {

 Connection con=null;
PreparedStatement pst=null;
ResultSet rs= null;
 DefaultTableModel mode,model;
 String roll,rolls,rolls_batch;
  String CLASSS;
 String NAME,CODE,COMPTEMERE,CODEMERE,EXIST,CLASS,SUBSTRS,SUBSTR,CATB,SUB_CATB,LBSUB, CAT,ITEM,LB,SUB_CAT,DEVISE=null,PRINCIPAL=null;
 Double CHECK_BUDGET,CHECK_OHADA;
 Double SUMDEBIT,SUMCREDIT, ECART;
 String ID_TOKEN=null,ORGANIZATION=null;
    public journal1() {
        initComponents();
         con=JavaDbConnect.dbConnect();
          this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI bui= (BasicInternalFrameUI) this.getUI();
       bui.setNorthPane(null);
          call();  
     update();
    }
 public void update(){
         try{
 String sqls = "UPDATE ohada_trans SET CODE=? ,CODE1=? where CODE='' OR CODE1=''";
        
         pst = con.prepareStatement(sqls);
          pst.setString(1,"-");
          pst.setString(2,"-");            
         pst.executeUpdate();
}catch(Exception ex){JOptionPane.showMessageDialog(null,ex);}   
      try{
 String sqls = "UPDATE ohada_trans SET LB=?  where LB='' OR LB IS NULL";
        
         pst = con.prepareStatement(sqls);
          pst.setString(1,"-");           
         pst.executeUpdate();
}catch(Exception ex){JOptionPane.showMessageDialog(null,ex);}
    }
public void call(){
    DefaultListModel list = new DefaultListModel();
    batchs.removeAllItems();
    batchs.addItem("Batches");
     jComboBox2.removeAllItems();
     jComboBox2.addItem("....");
try{
          String sql="SELECT * FROM batchs where projet='"+buss.getText()+"'";
             pst = con.prepareStatement(sql);rs=pst.executeQuery();
            while(rs.next()){
                //String sum=rs.getString("nom");
                String sums=rs.getString("batch");
             
               batchs.addItem(sums);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
     
      try{
		String sql=("SELECT Code, name as 'Compte' FROM ohada");
                pst = con.prepareStatement(sql);
      rs= pst.executeQuery();
                  jTable3.setModel(DbUtils.resultSetToTableModel(rs));
	 DefaultTableCellRenderer centre =new DefaultTableCellRenderer();
        centre.setHorizontalAlignment(JLabel.CENTER);
         TableColumn col0=jTable3.getColumnModel().getColumn(0);
        TableColumn col1=jTable3.getColumnModel().getColumn(1);
          jTable3.getColumnModel().getColumn(0).setCellRenderer(centre);
       
        
      
       
     col0.setPreferredWidth(50);
       col1.setPreferredWidth(150);
      // sahowtable();
   
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
        try{
          String sql="SELECT * FROM currency";
             pst = con.prepareStatement(sql);rs=pst.executeQuery();
            while(rs.next()){
                //String sum=rs.getString("nom");
                String sums=rs.getString("APPR");
             
               jComboBox2.addItem(sums);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }

jDateChooser1.setDate(new Date());
    }

public void call_journal(){
    String Code,Name = null;
   
 journals.setText(""); 

 
   try{        
   String sqls="SELECT Code, name as 'Compte' FROM ohada where SUBSTR= '"+jrn.getText().substring(4)+"' order by name"; //`LIBELLE`, `ENTRE`, `SORTIE`, `DATE`, `BUSS`, `USED`, `NUM`
    pst = con.prepareStatement(sqls);          rs=pst.executeQuery();

     
       jTable3.setModel(DbUtils.resultSetToTableModel(rs));
    
             }catch(Exception ex){
                 JOptionPane.showMessageDialog(null, ex);
             }
                 DefaultTableCellRenderer centre =new DefaultTableCellRenderer();
        centre.setHorizontalAlignment(JLabel.CENTER);
         TableColumn col0=jTable3.getColumnModel().getColumn(0);
        TableColumn col1=jTable3.getColumnModel().getColumn(1);
          jTable3.getColumnModel().getColumn(0).setCellRenderer(centre);
       
        
      
       
     col0.setPreferredWidth(50);
       col1.setPreferredWidth(150);
     
      
}
  public void compte()
    {
         try{
            String sql="select * from  ohada WHERE SUBSTR='"+ohada.getText()+"'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
           if(rs.next()){
                String sum=rs.getString("SUBSTR");
                 try{  // String Table_click = (jList2.getModel().getSelectedItem(tmps,0). toString());
                    home_compte m = new home_compte();
                     m.show();
        m. setDefaultCloseOperation(DISPOSE_ON_CLOSE);  
       String sqls="SELECT  CODE as 'Numéro',`NAME` AS 'Intitulé du compte' FROM `ohada` where SUBSTR='"+sum+"' ";
       pst = con.prepareStatement(sqls);
      rs= pst.executeQuery();
     
    home_compte.etjTable3.setModel(DbUtils.resultSetToTableModel(rs));
       DefaultTableCellRenderer centre =new DefaultTableCellRenderer();
       TableColumn col0=  home_compte.etjTable3.getColumnModel().getColumn(0);
        TableColumn col1=  home_compte.etjTable3.getColumnModel().getColumn(1);
       col0.setPreferredWidth(50);
       col1.setPreferredWidth(300);
      centre.setHorizontalAlignment(JLabel.CENTER);
         home_compte.etjTable3.getColumnModel().getColumn(0).setCellRenderer(centre); 
    }catch(SQLException ex ){
     JOptionPane.showMessageDialog(null, ex);}
                 
                 
                
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex); }  
        
       
} 
  
   public void clic_attCall_IN_LIST()
    { 
   int row= jTable3.getSelectedRow();
          String Table_click = (jTable3.getModel().getValueAt(row,0). toString());
 
        try{
          String sql="SELECT * FROM ohada where CODE ='"+Table_click+"'";
       pst = con.prepareStatement(sql);          rs=pst.executeQuery();
            while(rs.next()){
                //String sum=rs.getString("nom");
                //String sum=rs.getString("nom");
                String sums=rs.getString("NAME");
                compte1.setText(sums);
                journals.setText("Journa - "+sums);
                String sums1=rs.getString("CODEMERE");
                code_m1.setText(sums1);
                
                String sums2=rs.getString("CODE");
                code1.setText(sums2);
                
                String  COMPTEMERES=rs.getString("COMPTEMERE");
                comptemere1.setText(COMPTEMERES);
                 
                  CLASSS=rs.getString("CLASS");
                  classe1.setText(CLASSS);
                  SUBSTR=rs.getString("SUBSTR");
                  substr1.setText(SUBSTR);
           }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
                try{
          String sql="SELECT * FROM monais where caisse ='"+Table_click+"'";
        pst = con.prepareStatement(sql);          rs=pst.executeQuery();
            while(rs.next()){
                //String sum=rs.getString("nom");
                //String sum=rs.getString("nom");
               DEVISE=rs.getString("DEVICE");
               jComboBox2.setSelectedItem(DEVISE);
               
          }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
                 try{
          String sql="SELECT * FROM currency where APPR ='"+DEVISE+"'";
         pst = con.prepareStatement(sql);          rs=pst.executeQuery();
            while(rs.next()){
                String sale=rs.getString("SALE");
                String buy=rs.getString("BUY");
                PRINCIPAL=rs.getString("PRINCIPAL");
                jTextField4.setText(sale);
                jTextField5.setText(buy);
             
               
          }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
        
            boody.removeAll();
boody.add(new home_Table());
boody.repaint();
boody.revalidate();
    } 
   
    public void callcaisebuss()
    { 
        Double Debit,Credit,c,cc;
 if(jrn.getText().equals("JRN-57")||jrn.getText().equals("JRN-52")){
    if(PRINCIPAL.equals("Yes")){
    
       try{
String sqlS="SELECT sum(debit),sum(credit) FROM   ohada_trans WHERE CODE='"+code1.getText()+"' and  BUSS='"+buss.getText()+"' and solde='No'";
 pst = con.prepareStatement(sqlS);          rs=pst.executeQuery();
while(rs.next()){
Debit=rs.getDouble("sum(debit)");
 Credit=rs.getDouble("sum(credit)");
 c=Debit-Credit;
 NumberFormat nf = new DecimalFormat("#,###.000");
String fn = nf.format(c);
usd.setText(fn);
 devise.setText(DEVISE);}    
 } catch (Exception ex) {JOptionPane.showMessageDialog(null, ex.getMessage());}  
       
       
 if(journal1.batchs1.getSelectedItem().equals("Day")){
       SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
      String Dates = dateFormats.format(jDateChooser1.getDate());
      try{
String sqlS="SELECT sum(debit),sum(credit) FROM   ohada_trans WHERE CODE='"+code1.getText()+"' and  BUSS='"+buss.getText()+"' and DATES='"+Dates+"'";
 pst = con.prepareStatement(sqlS);          rs=pst.executeQuery();
while(rs.next()){
Debit=rs.getDouble("sum(debit)");
 Credit=rs.getDouble("sum(credit)");
 c=Debit-Credit;
 NumberFormat nf = new DecimalFormat("#,###.000");
String fn = nf.format(c);
usd_sold.setText(fn);}    
 } catch (Exception ex) {JOptionPane.showMessageDialog(null, ex.getMessage());} 
}else if(journal1.batchs1.getSelectedItem().equals("Month")){
       SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM");
       String Dates = dateFormats.format(jDateChooser1.getDate());
       try{
String sqlS="SELECT sum(debit),sum(credit) FROM   ohada_trans WHERE CODE='"+code1.getText()+"' and  BUSS='"+buss.getText()+"' and SUBSTR(DATES,1,7)='"+Dates+"' ";
 pst = con.prepareStatement(sqlS);          rs=pst.executeQuery();
while(rs.next()){
Debit=rs.getDouble("sum(debit)");
 Credit=rs.getDouble("sum(credit)");
 c=Debit-Credit;
 NumberFormat nf = new DecimalFormat("#,###.000");
String fn = nf.format(c);
usd_sold.setText(fn);}    
 } catch (Exception ex) {JOptionPane.showMessageDialog(null, ex.getMessage());} 
 }else if(journal1.batchs1.getSelectedItem().equals("Year")){
       SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy");
      String Dates = dateFormats.format(jDateChooser1.getDate());
      try{
String sqlS="SELECT sum(debit),sum(credit) FROM   ohada_trans WHERE CODE='"+code1.getText()+"' and  BUSS='"+buss.getText()+"' and SUBSTR(DATES,1,4)='"+Dates+"' and solde='No' ";
 pst = con.prepareStatement(sqlS);          rs=pst.executeQuery();
while(rs.next()){
Debit=rs.getDouble("sum(debit)");
 Credit=rs.getDouble("sum(credit)");
 c=Debit-Credit;
 NumberFormat nf = new DecimalFormat("#,###.000");
String fn = nf.format(c);
usd_sold.setText(fn);
 }    
 } catch (Exception ex) {JOptionPane.showMessageDialog(null, ex.getMessage());} 
       }       
   
        
    }else{
    
       try{
            String sqlS="SELECT sum(DEBIT_CDF),sum(CREDIT_CDF) FROM   ohada_trans WHERE CODE='"+code1.getText()+"' and  BUSS='"+buss.getText()+"' and solde='No' ";
       
		 pst = con.prepareStatement(sqlS);          rs=pst.executeQuery();
            while(rs.next()){
               Debit=rs.getDouble("sum(DEBIT_CDF)");
               Credit=rs.getDouble("sum(CREDIT_CDF)");
                c=Debit-Credit;
                NumberFormat nf = new DecimalFormat("#,###.000");
String fn = nf.format(c);
           usd.setText(fn);
         devise.setText(DEVISE);  
            }    
           } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());}   
       
     if(journal1.batchs1.getSelectedItem().equals("Day")){
       SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
      String Dates = dateFormats.format(jDateChooser1.getDate());
      try{
String sqlS="SELECT sum(DEBIT_CDF),sum(CREDIT_CDF) FROM   ohada_trans WHERE CODE='"+code1.getText()+"' and  BUSS='"+buss.getText()+"'  and DATES='"+Dates+"'";
 pst = con.prepareStatement(sqlS);          rs=pst.executeQuery();
while(rs.next()){
 Debit=rs.getDouble("sum(DEBIT_CDF)");
 Credit=rs.getDouble("sum(CREDIT_CDF)");
 c=Debit-Credit;
 NumberFormat nf = new DecimalFormat("#,###.000");
String fn = nf.format(c);
usd_sold.setText(fn);}    
 } catch (Exception ex) {JOptionPane.showMessageDialog(null, ex.getMessage());} 
}else if(journal1.batchs1.getSelectedItem().equals("Month")){
       SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM");
       String Dates = dateFormats.format(jDateChooser1.getDate());
       try{
String sqlS="SELECT sum(DEBIT_CDF),sum(CREDIT_CDF) FROM   ohada_trans WHERE CODE='"+code1.getText()+"' and  BUSS='"+buss.getText()+"'  and SUBSTR(DATES,1,7)='"+Dates+"' ";
 pst = con.prepareStatement(sqlS);          rs=pst.executeQuery();
while(rs.next()){
Debit=rs.getDouble("sum(DEBIT_CDF)");
Credit=rs.getDouble("sum(CREDIT_CDF)");
 c=Debit-Credit;
 NumberFormat nf = new DecimalFormat("#,###.000");
String fn = nf.format(c);
usd_sold.setText(fn);}    
 } catch (Exception ex) {JOptionPane.showMessageDialog(null, ex.getMessage());} 
 }else if(journal1.batchs1.getSelectedItem().equals("Year")){
       SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy");
      String Dates = dateFormats.format(jDateChooser1.getDate());
      try{
String sqlS="SELECT sum(DEBIT_CDF),sum(CREDIT_CDF) FROM   ohada_trans WHERE CODE='"+code1.getText()+"' and  BUSS='"+buss.getText()+"'  and SUBSTR(DATES,1,4)='"+Dates+"' and solde='No'";
 pst = con.prepareStatement(sqlS);          rs=pst.executeQuery();
while(rs.next()){
Debit=rs.getDouble("sum(DEBIT_CDF)");
Credit=rs.getDouble("sum(CREDIT_CDF)");
 c=Debit-Credit;
 NumberFormat nf = new DecimalFormat("#,###.000");
String fn = nf.format(c);
usd_sold.setText(fn);
 }    
 } catch (Exception ex) {JOptionPane.showMessageDialog(null, ex.getMessage());} 
       }      
       
    }
             
 }else{
 
  try{
            String sqlS="SELECT sum(debit),sum(credit) FROM   ohada_trans WHERE CODE='"+code1.getText()+"' and BUSS='"+buss.getText()+"' and solde='No'";
 pst = con.prepareStatement(sqlS);          rs=pst.executeQuery();
            while(rs.next()){
               Debit=rs.getDouble("sum(debit)");
               Credit=rs.getDouble("sum(credit)");
               
              // cc=Debit0.0-Credit0.0;
                c=Debit-Credit;
             // cc=c*BUY; 
             // jTextField3.setText(""+USDD);
               
                NumberFormat nf = new DecimalFormat("#,###.000");
String fn = nf.format(c);
//String fn1 = nf.format(cc);
           usd.setText(fn);
      //   0.0.setText(fn1);
         devise.setText(DEVISE);
      }  
            } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());} 
  if(journal1.batchs1.getSelectedItem().equals("Year")){
       SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy");
      String Dates = dateFormats.format(jDateChooser1.getDate());
      try{
String sqlS="SELECT sum(DEBIT),sum(CREDIT) FROM   ohada_trans WHERE CODE='"+code1.getText()+"' and  BUSS='"+buss.getText()+"'  and SUBSTR(DATES,1,4)='"+Dates+"' and solde='Yes'";
 pst = con.prepareStatement(sqlS);          rs=pst.executeQuery();
while(rs.next()){
Debit=rs.getDouble("sum(DEBIT)");
Credit=rs.getDouble("sum(CREDIT)");
 c=Debit-Credit;
 NumberFormat nf = new DecimalFormat("#,###.000");
String fn = nf.format(c);
usd_sold.setText(fn);
 }    
 } catch (Exception ex) {JOptionPane.showMessageDialog(null, ex.getMessage());} 
       }
 
 }             
    
    
    }
    
    
    //--------->>>ECRITURE
     public void Rolls(){
          try{
                  try{
         
         String sql="SELECT * FROM  ID_TOKEN  where id=1";
          pst = con.prepareStatement(sql);
          rs=pst.executeQuery();
          if(rs.next()){
         ID_TOKEN = rs.getString("ID_USER"); }
            }catch(SQLException ex ){JOptionPane.showMessageDialog(null,ex);}
                  
                  SimpleDateFormat dateFormatsS = new SimpleDateFormat("yyyyMMdd");
         String addDateS = dateFormatsS.format(jDateChooser1.getDate());
         
         String sql="SELECT NUM FROM ohada_trans where SUBSTR(NUM,5,8)='"+addDateS+"' ORDER BY NUM DESC LIMIT 1";
        
		   pst = con.prepareStatement(sql);          rs=pst.executeQuery();
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
                 //101-202401041006
                 //101-2024-01-04/1006
              // rolls= iduser+"-"+addDateS+"1001";
              rolls= ID_TOKEN+"-"+addDateS+"1001";
                
             }
         }catch(NumberFormatException | SQLException e){
             JOptionPane.showMessageDialog(null, e);  
         }
         }
     
      public void RollBATCH(){//No: 2022-01-31/1001
               try{
         
         String sql="SELECT * FROM  ID_TOKEN  where id=1";
          pst = con.prepareStatement(sql);
          rs=pst.executeQuery();
          if(rs.next()){
         ID_TOKEN = rs.getString("ID_USER"); }
            }catch(SQLException ex ){JOptionPane.showMessageDialog(null,ex);}
               
      String Batch_ecri=null;
       SimpleDateFormat dateFormatsS = new SimpleDateFormat("yyyyMMdd");
         String addDateS = dateFormatsS.format(jDateChooser1.getDate());
        String sql="SELECT BATCH_ECRITURE FROM ohada_trans where SUBSTR(BATCH_ECRITURE,5,8)='"+addDateS+"' ORDER BY BATCH_ECRITURE DESC LIMIT 1";
       if(jTextField1.getText().equals("")){
        try{
          String sqls="SELECT MAX(BATCH_ECRITURE) FROM ohada_trans where buss ='"+buss.getText()+"'";
        pst = con.prepareStatement(sqls);          rs=pst.executeQuery();
           if(rs.next()){
              Batch_ecri=rs.getString("MAX(BATCH_ECRITURE)");
             // jTextField1.setText(Batch_ecri);
              
           try{
		   pst = con.prepareStatement(sql);          rs=pst.executeQuery();
             if(rs.next()){
                 String rl=Batch_ecri;
                 int ln=rl.length();
                 String stxt=rl.substring(0,13);
                 String snum=rl.substring(13,ln);
                 int n = Integer.parseInt(snum);
                 n++;
                 snum=Integer.toString(n);
                rolls_batch=stxt+snum;
                jTextField1.setText(rolls_batch);
             }else{
            rolls_batch= ID_TOKEN+"-"+addDateS+"1001";
                jTextField1.setText(rolls_batch);  
             }
         }catch(NumberFormatException | SQLException e){
             JOptionPane.showMessageDialog(null, e);  
         }  
               
          }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        } 
       }else{
       rolls_batch=jTextField1.getText();
       }
         
          
         }
     
      public void delete_transction(){
          int indexs[]=home_Table.jTable2.getSelectedRows();
        for(int i=0; i < indexs.length;i++){
             String nums = (home_Table.jTable2.getModel().getValueAt(indexs[i],0). toString());
              String bus = buss.getText();
            
        
         try{
        String sql = "DELETE FROM ohada_trans WHERE NUM=? and buss='"+bus+"'";
        
         pst = con.prepareStatement(sql);
         pst.setString(1,nums);
         pst.executeUpdate();

     }catch(SQLException | HeadlessException ex ){
     // JOptionPane.showMessageDialog(null, ex.getMessage());
     } 
            try{
        String sql = "DELETE FROM avance_sur_sal WHERE NUMS=? and buss='"+bus+"'";
        
         pst = con.prepareStatement(sql);
         pst.setString(1,nums);
         pst.executeUpdate();

     }catch(SQLException | HeadlessException ex ){
     // JOptionPane.showMessageDialog(null, ex.getMessage());
     } 
            try{
        String sql = "DELETE FROM budget_trans WHERE NUM=? and projet='"+bus+"'";
        
         pst = con.prepareStatement(sql);
         pst.setString(1,nums);
         pst.executeUpdate();

     }catch(SQLException | HeadlessException ex ){
      // JOptionPane.showMessageDialog(null, ex.getMessage());
     } 
         try{
         PreparedStatement pst = con.prepareStatement("UPDATE `etat_de_besoin` SET `ECRITURE`='' WHERE NUM_TRANS='"+nums+"'");
      pst.executeUpdate();

     }catch(SQLException | HeadlessException ex ){
         JOptionPane.showMessageDialog(null,ex); }   
        }
        JOptionPane.showMessageDialog(null,"Transactrion Done");
     }
      
      public void update_transction(){
          int indexs[]=home_Table.jTable2.getSelectedRows();
        for(int i=0; i < indexs.length;i++){
             String num = (home_Table.jTable2.getModel().getValueAt(indexs[i],0). toString());
              String dates = (home_Table.jTable2.getModel().getValueAt(indexs[i],1). toString());
               String lb = (home_Table.jTable2.getModel().getValueAt(indexs[i],2). toString());
                String code = (home_Table.jTable2.getModel().getValueAt(indexs[i],3). toString());
                 String libelle = (home_Table.jTable2.getModel().getValueAt(indexs[i],4). toString());
                  String credits = (home_Table.jTable2.getModel().getValueAt(indexs[i],5). toString());
                  String credit=credits.replace(",", "");
               
              String bus = buss.getText();
            
       
         try{
        String sql = " UPDATE ohada_trans SET CODE='"+code1.getText()+"',CODE1='"+code+"',CREDIT='"+credit+"',DATES='"+dates+"',LIBELLE='"+libelle+"',LB='"+lb+"',BATCH_ECRITURE='"+jTextField1.getText()+"'  WHERE BUSS='"+buss.getText()+"' AND NUM='"+num+"' and DEBIT=0 ";
        
         pst = con.prepareStatement(sql);
         pst.executeUpdate();

     }catch(SQLException | HeadlessException ex ){
     } 
         
         try{
        String sql = " UPDATE ohada_trans SET CODE='"+code+"',CODE1='"+code1.getText()+"',DEBIT='"+credit+"',DATES='"+dates+"',LIBELLE='"+libelle+"',LB='"+lb+"',BATCH_ECRITURE='"+jTextField1.getText()+"' WHERE BUSS='"+buss.getText()+"' AND NUM='"+num+"' and CREDIT=0 ";
        
         pst = con.prepareStatement(sql);
         pst.executeUpdate();

     }catch(SQLException | HeadlessException ex ){
     } 
         
         
            try{
          String sql = " UPDATE avance_sur_sal SET MOTANT='"+credit+"',DATES='"+dates+"' WHERE BUSS='"+buss.getText()+"' AND NUMS='"+num+"' ";
       
         pst = con.prepareStatement(sql);
        // pst.setString(1,nums);
         pst.executeUpdate();

     }catch(SQLException | HeadlessException ex ){
     // JOptionPane.showMessageDialog(null, ex.getMessage());
     } 
           
            try{
        String sql = " UPDATE budget_trans SET ITEM='"+libelle+"',CREDIT='"+credit+"',CODE='"+lb+"',DATES='"+dates+"' WHERE projet='"+buss.getText()+"' AND NUM='"+num+"' ";
        
         pst = con.prepareStatement(sql);
         pst.executeUpdate();

     }catch(SQLException | HeadlessException ex ){
      // JOptionPane.showMessageDialog(null, ex.getMessage());
     } 
          
        }
        JOptionPane.showMessageDialog(null,"Transactrion Done");
     }
    
  String queries= "INSERT INTO `ohada_trans`(`COMPTE_M`, `COMPTE`, `CODE_M`, `CODE`, `CLASSE`, `SUBSTR`, `DEBIT`, `CREDIT`, `NUM`, `DATES`, `BUSS`, `LIBELLE`, `JOURNAL`, `CODE1`,DEBIT_CDF,CREDIT_CDF,DEVICE,LB,BATCH_ECRITURE,ORGANIZATION)"
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
           
       public void savecaisse(){
   double USD = 0,sale = 0,buy = 0,BUY,CDF = 0,SALE,USDC,CDFC;
   String Table_click1  = amount.getText();
String replaceString=Table_click1.replace(",", "");
 Double PR= Double.parseDouble(replaceString);
  
  String DEVICEEssC = null,DEVICEEss = null;
 try{
         
         String sql="SELECT * FROM  ID_TOKEN  where id=1";
          pst = con.prepareStatement(sql);
          rs=pst.executeQuery();
          if(rs.next()){
         ORGANIZATION = rs.getString("ORGANIZATION"); }
            }catch(SQLException ex ){JOptionPane.showMessageDialog(null,ex);}
 
   Rolls();
   RollBATCH();
   
//---------------------------------------------------------------------------------------------------------------   
     //----------> BETWEEN CASH BOOK
   if(classe1.getText().equals("5") && classe2.getText().equals("5")){

try{
    String sql="select * from monais WHERE caisse='"+code1.getText()+"'";
	   pst = con.prepareStatement(sql);          rs=pst.executeQuery();
            if(rs.next()){
          DEVICEEss=rs.getString("DEVICE");  
            }
            }
        
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex); }
    
     try{
String sql="SELECT * FROM  currency where APPR='"+DEVICEEss+"'";
pst = con.prepareStatement(sql);          rs=pst.executeQuery();
   while(rs.next()){
            //device=rs.getString("APPR")
             sale=rs.getDouble("SALE");
             buy=rs.getDouble("BUY"); 
               PRINCIPAL=rs.getString("PRINCIPAL"); 
            BUY=buy;//Achete le principal currency
            SALE=sale;//Ventre le principal currency
            }
          
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }

   //  JOptionPane.showMessageDialog(null, PRINCIPAL);  
       if(PRINCIPAL.equals("Yes")){
         try{
            String sql="select * from monais WHERE caisse='"+code2.getText()+"'";
	   pst = con.prepareStatement(sql);          rs=pst.executeQuery();
            if(rs.next()){
          DEVICEEss=rs.getString("DEVICE");  
            }
            }
        
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex); }
    
     try{
String sql="SELECT * FROM  currency where APPR='"+DEVICEEss+"'";
pst = con.prepareStatement(sql);          rs=pst.executeQuery();
   while(rs.next()){
            //device=rs.getString("APPR")
             sale=rs.getDouble("SALE");
             buy=rs.getDouble("BUY"); 
               PRINCIPAL=rs.getString("PRINCIPAL"); 
            BUY=buy;//Achete le principal currency
            SALE=sale;//Ventre le principal currency
            }
          
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }   
           
   CDF=PR*sale;
  USD=PR;
  
  }else{
     
  USD=PR/sale;
  CDF=PR;
  }
     try{
            String sql="select * from monais WHERE caisse='"+code2.getText()+"'";
	   pst = con.prepareStatement(sql);          rs=pst.executeQuery();
            if(rs.next()){
          DEVICEEssC=rs.getString("DEVICE");  
            }
            }
        
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex); }
    
     try{
String sql="SELECT * FROM  currency where APPR='"+DEVICEEssC+"'";
pst = con.prepareStatement(sql);          rs=pst.executeQuery();
   while(rs.next()){
            //device=rs.getString("APPR")
             sale=rs.getDouble("SALE");
             buy=rs.getDouble("BUY"); 
               PRINCIPAL=rs.getString("PRINCIPAL"); 
//            BUYC=buy;
           // BUY=buy;
            }
          
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }

   //  JOptionPane.showMessageDialog(null, PRINCIPAL);  
       if(PRINCIPAL.equals("Yes")){ 
   CDFC=PR*buy;
  USDC=PR;
  
  }else{
     
  USDC=PR/sale;
  CDFC=PR;
  }        
       
    if(jComboBox3.getSelectedItem().equals("Debit")){
       
        
           try{
       
 
            String sql="SELECT   sum(DEBIT),sum(CREDIT) FROM ohada_trans WHERE code='"+code2.getText()+"' AND BUSS='"+buss.getText()+"'";
   pst = con.prepareStatement(sql);          rs=pst.executeQuery();
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
  }else if(libele.getText().isEmpty()||code2.getText().isEmpty()||code1.getText().isEmpty()||amount.getText().isEmpty()||amount.getText().equals("0.00")){ 
   JOptionPane.showMessageDialog(null,"Wrong Data"+"\n"+"Wrong Data ","Error",JOptionPane.ERROR_MESSAGE);
  
  }else{ 
     try {
         pst = con.prepareStatement(queries);
    pst.setString(1, comptemere1.getText());
        pst.setString(2,compte1.getText());
         pst.setString(3,code_m1.getText());
         pst.setString(4,code1.getText());
         pst.setString(5,classe1.getText());
         pst.setString(6,substr1.getText());
         pst.setDouble(7,USD);
         pst.setString(8,"0");
         pst.setString(9,rolls);
         
       SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(jDateChooser1.getDate());
         pst.setString(10, addDate);
         pst.setString(11,buss.getText());
         pst.setString(12,libele.getText());
         pst.setString(13,"[JC]");
          pst.setString(14,code2.getText());
           pst.setString(16,"0");
            pst.setDouble(15,CDF);
            pst.setString(17,DEVICEEss);
            pst.setString(18,budget.getText());
            pst.setString(19,rolls_batch);
            pst.setString(20,ORGANIZATION);
          
          
         
          pst.executeUpdate();
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
      
        try {
         pst = con.prepareStatement(queries);
    pst.setString(1, comptemere2.getText());
        pst.setString(2,compte2.getText());
         pst.setString(3,code_m2.getText());
         pst.setString(4,code2.getText());
         pst.setString(5,classe2.getText());
         pst.setString(6,substr2.getText());
         pst.setDouble(8,USD);
         pst.setString(7,"0");
         pst.setString(9,rolls);
         
       SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(jDateChooser1.getDate());
         pst.setString(10, addDate);
         pst.setString(11,buss.getText());
         pst.setString(12,libele.getText());
         pst.setString(13,"[JC]");
          pst.setString(14,code1.getText());
           pst.setString(15,"0");
            pst.setDouble(16,CDF);
            pst.setString(17,DEVICEEss);
            pst.setString(18,budget.getText());
       pst.setString(19,rolls_batch); 
       pst.setString(20,ORGANIZATION);
          pst.executeUpdate();
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
      
        JOptionPane.showMessageDialog(null,"Transaction Done");}
    }else{    
    
             try{ String sql="SELECT   sum(DEBIT),sum(CREDIT) FROM ohada_trans WHERE code='"+code1.getText()+"' AND BUSS='"+buss.getText()+"'";
   pst = con.prepareStatement(sql);          rs=pst.executeQuery();
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
  }else if(libele.getText().isEmpty()||code2.getText().isEmpty()||code1.getText().isEmpty()||amount.getText().isEmpty()||amount.getText().equals("0.00")){ 
   JOptionPane.showMessageDialog(null,"Wrong Data"+"\n"+"Wrong Data ","Error",JOptionPane.ERROR_MESSAGE);
  
  }else{ 
    try {
         pst = con.prepareStatement(queries);
    pst.setString(1, comptemere1.getText());
        pst.setString(2,compte1.getText());
         pst.setString(3,code_m1.getText());
         pst.setString(4,code1.getText());
         pst.setString(5,classe1.getText());
         pst.setString(6,substr1.getText());
         pst.setDouble(8,USD);
         pst.setString(7,"0");
         pst.setString(9,rolls);
         
       SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(jDateChooser1.getDate());
         pst.setString(10, addDate);
         pst.setString(11,buss.getText());
         pst.setString(12,libele.getText());
         pst.setString(13,"[JC]");
          pst.setString(14,code2.getText());
           pst.setString(15,"0");
            pst.setDouble(16,CDF);
            pst.setString(17,DEVICEEss);
            pst.setString(18,budget.getText());
           pst.setString(19,rolls_batch);
           pst.setString(20,ORGANIZATION);
          
         
          pst.executeUpdate();
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
      
           //========>>>>CREDIT
        ///INSERT INTO `ohada_trans`(`ID`, `COMPTE_M`, `COMPTE`, `CODE_M`, `CODE`, `CLASSE`, `SUBSTR`, `DEBIT`, `CREDIT`, `NUM`, `DATES`, `BUSS`, `LIBELLE`, `JOURNAL`)
        try {
         pst = con.prepareStatement(queries);
    pst.setString(1, comptemere2.getText());
        pst.setString(2,compte2.getText());
         pst.setString(3,code_m2.getText());
         pst.setString(4,code2.getText());
         pst.setString(5,classe2.getText());
         pst.setString(6,substr2.getText());
         pst.setDouble(7,USD);
         pst.setString(8,"0");
         pst.setString(9,rolls);
         
       SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(jDateChooser1.getDate());
         pst.setString(10, addDate);
         pst.setString(11,buss.getText());
         pst.setString(12,libele.getText());
         pst.setString(13,"[JC]");
          pst.setString(14,code1.getText());
           pst.setString(16,"0");
            pst.setDouble(15,CDF);
            pst.setString(17,DEVICEEss);
            pst.setString(18,budget.getText());
           pst.setString(19,rolls_batch);
           pst.setString(20,ORGANIZATION);
          
         
          pst.executeUpdate();
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());}
        
        //budget
        
          
 JOptionPane.showMessageDialog(null,"Transaction Done");
    
    }
    }
minirefrsh();
//---------------------------------------------------------------------------------------------------------------   
     //----------> DECAISSEMENT /   
   }else if(classe1.getText().equals("5") && !classe2.getText().equals("5")){ 
 try{
            String sql="select * from monais WHERE caisse='"+code1.getText()+"'";
	   pst = con.prepareStatement(sql);          rs=pst.executeQuery();
            if(rs.next()){
          DEVICEEss=rs.getString("DEVICE");  
            }
            }
        
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex); }
    
     try{
String sql="SELECT * FROM  currency where APPR='"+DEVICEEss+"'";
pst = con.prepareStatement(sql);          rs=pst.executeQuery();
   while(rs.next()){
            //device=rs.getString("APPR")
             sale=rs.getDouble("SALE");
             buy=rs.getDouble("BUY"); 
               PRINCIPAL=rs.getString("PRINCIPAL"); 
            BUY=buy;//Achete le principal currency
            SALE=sale;//Ventre le principal currency
            }
          
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }

   //  JOptionPane.showMessageDialog(null, PRINCIPAL);  
       if(PRINCIPAL.equals("Yes")){
         try{
            String sql="select * from monais WHERE caisse='"+code2.getText()+"'";
	   pst = con.prepareStatement(sql);          rs=pst.executeQuery();
            if(rs.next()){
          DEVICEEss=rs.getString("DEVICE");  
            }
            }
        
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex); }
    
     try{
String sql="SELECT * FROM  currency where APPR='"+DEVICEEss+"'";
pst = con.prepareStatement(sql);          rs=pst.executeQuery();
   while(rs.next()){
            //device=rs.getString("APPR")
             sale=rs.getDouble("SALE");
             buy=rs.getDouble("BUY"); 
               PRINCIPAL=rs.getString("PRINCIPAL"); 
            BUY=buy;//Achete le principal currency
            SALE=sale;//Ventre le principal currency
            }
          
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }   
           
   CDF=PR*sale;
  USD=PR;
  
  }else{
     
  USD=PR/sale;
  CDF=PR;
  }
     try{
            String sql="select * from monais WHERE caisse='"+code2.getText()+"'";
	   pst = con.prepareStatement(sql);          rs=pst.executeQuery();
            if(rs.next()){
          DEVICEEssC=rs.getString("DEVICE");  
            }
            }
        
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex); }
    
     try{
String sql="SELECT * FROM  currency where APPR='"+DEVICEEssC+"'";
pst = con.prepareStatement(sql);          rs=pst.executeQuery();
   while(rs.next()){
            //device=rs.getString("APPR")
             sale=rs.getDouble("SALE");
             buy=rs.getDouble("BUY"); 
               PRINCIPAL=rs.getString("PRINCIPAL"); 
//            BUYC=buy;
           // BUY=buy;
            }
          
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }

   //  JOptionPane.showMessageDialog(null, PRINCIPAL);  
       if(PRINCIPAL.equals("Yes")){ 
   CDFC=PR*buy;
  USDC=PR;
  
  }else{
     
  USDC=PR/sale;
  CDFC=PR;
  } 
   
       if(jComboBox3.getSelectedItem().equals("Credit")){
             try{ String sql="SELECT   sum(DEBIT),sum(CREDIT) FROM ohada_trans WHERE code='"+code1.getText()+"' AND BUSS='"+buss.getText()+"'";
   pst = con.prepareStatement(sql);          rs=pst.executeQuery();
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
       
            String sql="SELECT   sum(DEBIT),sum(CREDIT) FROM budget_trans WHERE code='"+budget.getText()+"' AND PROJET='"+buss.getText()+"' AND BATCH='"+batchs.getSelectedItem()+"'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
              SUMDEBIT=rs.getDouble("sum(DEBIT)");
              SUMCREDIT=rs.getDouble("sum(CREDIT)");
            
               try{
            String sqlS="SELECT   NUM FROM PROJET WHERE PROJET_NUM='"+buss.getText()+"'";
           
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
            Double CHECK_BUDGETP=SUMCREDIT+USD;
            CHECK_BUDGET=(CHECK_BUDGETP*100)/SUMDEBIT;
           // jMenu7.setText(""+ECART);
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
   if(CHECK_OHADA <USD){  
   JOptionPane.showMessageDialog(null,"Wrong Data"+"\n"+"No Enough Resources  ","Error",JOptionPane.ERROR_MESSAGE);
  }else if(CHECK_BUDGET >=ECART){ 
   JOptionPane.showMessageDialog(null,"Wrong Data"+"\n"+"No Enough Budget"+"\n"+"You are at: "+CHECK_BUDGET+" %","Error",JOptionPane.ERROR_MESSAGE);
  }else if(libele.getText().isEmpty()||batchs.getSelectedItem().equals("Batches")||budget.getText().isEmpty()||code2.getText().isEmpty()||code1.getText().isEmpty()||amount.getText().isEmpty()||amount.getText().equals("0.00")){ 
   JOptionPane.showMessageDialog(null,"Wrong Data"+"\n"+"Wrong Data ","Error",JOptionPane.ERROR_MESSAGE);
  
  }else{ 
    try {
         pst = con.prepareStatement(queries);
    pst.setString(1, comptemere1.getText());
        pst.setString(2,compte1.getText());
         pst.setString(3,code_m1.getText());
         pst.setString(4,code1.getText());
         pst.setString(5,classe1.getText());
         pst.setString(6,substr1.getText());
         pst.setDouble(8,USD);
         pst.setString(7,"0");
         pst.setString(9,rolls);
         
       SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(jDateChooser1.getDate());
         pst.setString(10, addDate);
         pst.setString(11,buss.getText());
         pst.setString(12,libele.getText());
         pst.setString(13,code2.getText());
          pst.setString(14,code2.getText());
           pst.setString(15,"0");
            pst.setDouble(16,CDF);
            pst.setString(17,DEVICEEss);
            pst.setString(18,budget.getText());
           pst.setString(19,rolls_batch);
           pst.setString(20,ORGANIZATION);
          
         
          pst.executeUpdate();
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
    try {
         pst = con.prepareStatement(queries);
    pst.setString(1, comptemere2.getText());
        pst.setString(2,compte2.getText());
         pst.setString(3,code_m2.getText());
         pst.setString(4,code2.getText());
         pst.setString(5,classe2.getText());
         pst.setString(6,substr2.getText());
         pst.setDouble(7,USD);
         pst.setString(8,"0");
         pst.setString(9,rolls);
         
       SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(jDateChooser1.getDate());
         pst.setString(10, addDate);
         pst.setString(11,buss.getText());
         pst.setString(12,libele.getText());
         pst.setString(13,code2.getText());
          pst.setString(14,code1.getText());
           pst.setString(16,"0");
            pst.setDouble(15,CDF);
            pst.setString(17,DEVICEEss);
            pst.setString(18,budget.getText());
           pst.setString(19,rolls_batch);
           pst.setString(20,ORGANIZATION);
          
         
          pst.executeUpdate();
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());}
        
        //--------->>>>budget
       
       try{
          String sql="SELECT * FROM budget where CODE ='"+budget.getText()+"' and PROJECT='"+buss.getText()+"'";
       pst = con.prepareStatement(sql);          rs=pst.executeQuery();
            while(rs.next()){
               CAT=rs.getString("CAT");
              ITEM=rs.getString("ITEM");
               LB=rs.getString("LB");
               SUB_CAT=rs.getString("SUB_CAT");
               LBSUB=rs.getString("LBSUB");
           }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
             
            try{
    String sql="INSERT INTO `budget_trans`(`ITEM`, `DEBIT`, `CREDIT`, `SOLD`, `PROJET`, `CODE`, `NUM`, `CAT`, `DATES`, `MOIS`, `ANNEE`,SUB_CAT,CODE_CAT,CODE_SUB_CAT,ITEMS,BATCH) "+"values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    pst=con.prepareStatement(sql);
    pst.setString(1,libele.getText());
    pst.setString(2,"0");
    pst.setDouble(3,USD);
    pst.setString(4,"0.0");
    pst.setString(5,buss.getText());
    pst.setString(6,budget.getText());
    pst.setString(7,rolls);
    
    pst.setString(8,CAT);
    
     SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(jDateChooser1.getDate());
         pst.setString(9, addDate);
    SimpleDateFormat dateFormatsS = new SimpleDateFormat("MMM");
         String addDateS = dateFormatsS.format(jDateChooser1.getDate());
         pst.setString(10, addDateS);
         
          SimpleDateFormat dateFormatsSs = new SimpleDateFormat("yyyy");
         String addDateSs = dateFormatsSs.format(jDateChooser1.getDate());
         pst.setString(11, addDateSs);
    pst.setString (12,SUB_CAT);
    
     pst.setString(13,LB);
      pst.setString(14,LBSUB);
      
        pst.setString(15,ITEM);
        pst.setString(16,batchs.getSelectedItem().toString());
        
    //pst.setString(19,rolls_batch);
    
    
    pst.executeUpdate();
    // JOptionPane.showMessageDialog(null,"BUDGET TRANS");
    }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}
        
          
 JOptionPane.showMessageDialog(null,"Transaction Done");
       }
       }else{
         
    if(substr2.getText().equals("41")){
   // String Table_click;
           try{
       
 
            String sql="SELECT   sum(DEBIT),sum(CREDIT) FROM ohada_trans WHERE code='"+code2.getText()+"' AND BUSS='"+buss.getText()+"'";
   pst = con.prepareStatement(sql);          rs=pst.executeQuery();
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
  }else if(libele.getText().isEmpty()||code2.getText().isEmpty()||code1.getText().isEmpty()||amount.getText().isEmpty()||amount.getText().equals("0.00")){ 
   JOptionPane.showMessageDialog(null,"Wrong Data"+"\n"+"Wrong Data ","Error",JOptionPane.ERROR_MESSAGE);
  }else{ 
     try {
         pst = con.prepareStatement(queries);
    pst.setString(1, comptemere1.getText());
        pst.setString(2,compte1.getText());
         pst.setString(3,code_m1.getText());
         pst.setString(4,code1.getText());
         pst.setString(5,classe1.getText());
         pst.setString(6,substr1.getText());
         pst.setDouble(7,USD);
         pst.setString(8,"0");
         pst.setString(9,rolls);
         
       SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(jDateChooser1.getDate());
         pst.setString(10, addDate);
         pst.setString(11,buss.getText());
         pst.setString(12,libele.getText());
         pst.setString(13,code2.getText());
          pst.setString(14,code2.getText());
           pst.setString(16,"0");
            pst.setDouble(15,CDF);
            pst.setString(17,DEVICEEss);
            pst.setString(18,budget.getText());
           pst.setString(19,rolls_batch);
           pst.setString(20,ORGANIZATION);
          
         
          pst.executeUpdate();
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
      
        try {
         pst = con.prepareStatement(queries);
    pst.setString(1, comptemere2.getText());
        pst.setString(2,compte2.getText());
         pst.setString(3,code_m2.getText());
         pst.setString(4,code2.getText());
         pst.setString(5,classe2.getText());
         pst.setString(6,substr2.getText());
         pst.setDouble(8,USD);
         pst.setString(7,"0");
         pst.setString(9,rolls);
         
       SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(jDateChooser1.getDate());
         pst.setString(10, addDate);
         pst.setString(11,buss.getText());
         pst.setString(12,libele.getText());
         pst.setString(13,code2.getText());
          pst.setString(14,code1.getText());
           pst.setString(15,"0");
            pst.setDouble(16,CDF);
            pst.setString(17,DEVICEEss);
            pst.setString(18,budget.getText());
           pst.setString(19,rolls_batch);
           pst.setString(20,ORGANIZATION);
          
         
          pst.executeUpdate();
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
     
        JOptionPane.showMessageDialog(null,"Transaction Done");}
    
    }else{
    
    String Table_click;
           try{
       
 
            String sql="SELECT   sum(DEBIT),sum(CREDIT) FROM ohada_trans WHERE code='"+code2.getText()+"' AND BUSS='"+buss.getText()+"'";
   pst = con.prepareStatement(sql);          rs=pst.executeQuery();
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
  }else if(libele.getText().isEmpty()||code2.getText().isEmpty()||code1.getText().isEmpty()||amount.getText().isEmpty()||amount.getText().equals("0.00")){ 
   JOptionPane.showMessageDialog(null,"Wrong Data"+"\n"+"Wrong Data ","Error",JOptionPane.ERROR_MESSAGE);
  }else if(check_select_Table.getText().equals("No")){
  JOptionPane.showMessageDialog(null,"Wrong Data"+"\n"+"Sélectionnez la ligne à annuler","Error",JOptionPane.ERROR_MESSAGE);
 }else{ 
          int row= home_Table.jTable2.getSelectedRow();
           Table_click = (home_Table.jTable2.getModel().getValueAt(row,0). toString());
     try {
         pst = con.prepareStatement(queries);
    pst.setString(1, comptemere1.getText());
        pst.setString(2,compte1.getText());
         pst.setString(3,code_m1.getText());
         pst.setString(4,code1.getText());
         pst.setString(5,classe1.getText());
         pst.setString(6,substr1.getText());
         pst.setDouble(7,USD);
         pst.setString(8,"0");
         pst.setString(9,rolls);
         
       SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(jDateChooser1.getDate());
         pst.setString(10, addDate);
         pst.setString(11,buss.getText());
         pst.setString(12,libele.getText());
         pst.setString(13,code2.getText());
          pst.setString(14,code2.getText());
           pst.setString(16,"0");
            pst.setDouble(15,CDF);
            pst.setString(17,DEVICEEss);
            pst.setString(18,budget.getText());
           pst.setString(19,rolls_batch);
           pst.setString(20,ORGANIZATION);
          
         
          pst.executeUpdate();
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
      
        try {
         pst = con.prepareStatement(queries);
    pst.setString(1, comptemere2.getText());
        pst.setString(2,compte2.getText());
         pst.setString(3,code_m2.getText());
         pst.setString(4,code2.getText());
         pst.setString(5,classe2.getText());
         pst.setString(6,substr2.getText());
         pst.setDouble(8,USD);
         pst.setString(7,"0");
         pst.setString(9,rolls);
         
       SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(jDateChooser1.getDate());
         pst.setString(10, addDate);
         pst.setString(11,buss.getText());
         pst.setString(12,libele.getText());
         pst.setString(13,code2.getText());
          pst.setString(14,code1.getText());
           pst.setString(15,"0");
            pst.setDouble(16,CDF);
            pst.setString(17,DEVICEEss);
            pst.setString(18,budget.getText());
           pst.setString(19,rolls_batch);
           pst.setString(20,ORGANIZATION);
          
         
          pst.executeUpdate();
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
      //---------->Delete budget
    
       try{
        String sql = "DELETE FROM budget_trans WHERE NUM=? and projet='"+buss.getText()+"'";
        
         pst = con.prepareStatement(sql);
         pst.setString(1,Table_click);
         pst.executeUpdate();

     }catch(SQLException | HeadlessException ex ){
      // JOptionPane.showMessageDialog(null, ex.getMessage());
     } 
       try{
        String sql = "DELETE FROM avance_sur_sal WHERE NUMS=? and BUSS='"+buss.getText()+"'";
        
         pst = con.prepareStatement(sql);
         pst.setString(1,Table_click);
         pst.executeUpdate();

     }catch(SQLException | HeadlessException ex ){
      // JOptionPane.showMessageDialog(null, ex.getMessage());
     } 
      // Here
 try{
         PreparedStatement pst = con.prepareStatement("UPDATE `etat_de_besoin` SET `ECRITURE`='' WHERE NUM_TRANS='"+Table_click+"'");
      pst.executeUpdate();

     }catch(SQLException | HeadlessException ex ){
         JOptionPane.showMessageDialog(null,ex); }
        JOptionPane.showMessageDialog(null,"Transaction Done");}
    }       
           
    
       
       }
       minirefrsh();
//---------------------------------------------------------------------------------------------------------------   
     //----------> OPERATION DIVERS
   }else if(!classe1.getText().equals("5") && !classe2.getText().equals("5")){// ||classe2.getText().equals("4")){
       DEVICEEss=jComboBox2.getSelectedItem().toString();
      // JOptionPane.showMessageDialog(null, "is op divers"); 
   try{
String sql="SELECT * FROM  currency where APPR='"+DEVICEEss+"'";
pst = con.prepareStatement(sql);          rs=pst.executeQuery();
   while(rs.next()){
            //device=rs.getString("APPR")
             sale=rs.getDouble("SALE");
             buy=rs.getDouble("BUY"); 
               PRINCIPAL=rs.getString("PRINCIPAL"); 
            BUY=buy;//Achete le principal currency
            SALE=sale;//Ventre le principal currency
            }
          
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }

    // JOptionPane.showMessageDialog(null, "is principal: "+PRINCIPAL);  
       if(PRINCIPAL.equals("Yes")){
  CDF=PR*sale;
  USD=PR;
  
  }else{
  USD=PR/sale;
  CDF=PR;
  } 
       
  
     // if(substr1.getText().equals("41")){
             if(jComboBox3.getSelectedItem().equals("Debit")){
        if(libele.getText().isEmpty()||code2.getText().isEmpty()||code1.getText().isEmpty()||amount.getText().isEmpty()||amount.getText().equals("0.00")){ 
   JOptionPane.showMessageDialog(null,"Wrong Data"+"\n"+"Wrong Data ","Error",JOptionPane.ERROR_MESSAGE);
  
  }else{
             if(substr2.getText().equals("44")){
                 double a = 0;String b = null;
                  try{
String sql="SELECT DEBIT,LIBELLE FROM  ohada_trans where BATCH_ECRITURE='"+jTextField1.getText()+"' AND BUSS='"+buss.getText()+"' and CODE1='"+code1.getText()+"'";
pst = con.prepareStatement(sql);          rs=pst.executeQuery();
   while(rs.next()){
            a=rs.getDouble("DEBIT");
            b=rs.getString("LIBELLE");
            }
          
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
                //  JOptionPane.showMessageDialog(null, a);
     try{
 String sqls = "UPDATE ohada_trans SET DEBIT=?,LIBELLE=? where BATCH_ECRITURE='"+jTextField1.getText()+"' AND BUSS='"+buss.getText()+"' and CODE1='"+code1.getText()+"'";
        
         pst = con.prepareStatement(sqls);
          pst.setDouble(1,a-PR);
          pst.setString(2,b+"  - HT");
                     
         pst.executeUpdate();


}catch(Exception ex){JOptionPane.showMessageDialog(null,ex);}          
       try {
         pst = con.prepareStatement(queries);
    pst.setString(1, comptemere2.getText());
        pst.setString(2,compte2.getText());
         pst.setString(3,code_m2.getText());
         pst.setString(4,code2.getText());
         pst.setString(5,classe2.getText());
         pst.setString(6,substr2.getText());
         pst.setDouble(7,USD);
         pst.setString(8,"0");
         pst.setString(9,rolls);
         
       SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(jDateChooser1.getDate());
         pst.setString(10, addDate);
         pst.setString(11,buss.getText());
         pst.setString(12,libele.getText());
         pst.setString(13,code1.getText());
          pst.setString(14,code1.getText());
           pst.setString(16,"0");
            pst.setDouble(15,CDF);
            pst.setString(17,DEVICEEss);
            pst.setString(18,budget.getText());
           pst.setString(19,rolls_batch);
           pst.setString(20,ORGANIZATION);
          
         
          pst.executeUpdate();
    } catch (SQLException ex) {
         JOptionPane.showMessageDialog(null, ex);
    }
       }else{
     try {
         pst = con.prepareStatement(queries);
    pst.setString(1, comptemere1.getText());
        pst.setString(2,compte1.getText());
         pst.setString(3,code_m1.getText());
         pst.setString(4,code1.getText());
         pst.setString(5,classe1.getText());
         pst.setString(6,substr1.getText());
         pst.setDouble(7,USD);
         pst.setString(8,"0");
         pst.setString(9,rolls);
         
       SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(jDateChooser1.getDate());
         pst.setString(10, addDate);
         pst.setString(11,buss.getText());
         pst.setString(12,libele.getText());
         pst.setString(13,code1.getText());
          pst.setString(14,code2.getText());
           pst.setString(16,"0");
            pst.setDouble(15,CDF);
            pst.setString(17,DEVICEEss);
            pst.setString(18,budget.getText());
           pst.setString(19,rolls_batch);
           pst.setString(20,ORGANIZATION);
          
         
          pst.executeUpdate();
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
      
        try {
         pst = con.prepareStatement(queries);
    pst.setString(1, comptemere2.getText());
        pst.setString(2,compte2.getText());
         pst.setString(3,code_m2.getText());
         pst.setString(4,code2.getText());
         pst.setString(5,classe2.getText());
         pst.setString(6,substr2.getText());
         pst.setDouble(8,USD);
         pst.setString(7,"0");
         pst.setString(9,rolls);
         
       SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(jDateChooser1.getDate());
         pst.setString(10, addDate);
         pst.setString(11,buss.getText());
         pst.setString(12,libele.getText());
         pst.setString(13,code1.getText());
          pst.setString(14,code1.getText());
           pst.setString(15,"0");
            pst.setDouble(16,CDF);
            pst.setString(17,DEVICEEss);
            pst.setString(18,budget.getText());
           pst.setString(19,rolls_batch);
           pst.setString(20,ORGANIZATION);
          
         
          pst.executeUpdate();
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
             }
        JOptionPane.showMessageDialog(null,"Transaction Done");}
       }else{
                 
    if(libele.getText().isEmpty()||code2.getText().isEmpty()||code1.getText().isEmpty()||amount.getText().isEmpty()||amount.getText().equals("0.00")){ 
   JOptionPane.showMessageDialog(null,"Wrong Data"+"\n"+"Wrong Data ","Error",JOptionPane.ERROR_MESSAGE);
  
  }else{ 
         if(substr2.getText().equals("44") ){
              double a = 0; String b=null;
                  try{
String sql="SELECT CREDIT,LIBELLE FROM  ohada_trans where BATCH_ECRITURE='"+jTextField1.getText()+"' AND BUSS='"+buss.getText()+"' and CODE1='"+code1.getText()+"'";
pst = con.prepareStatement(sql);          rs=pst.executeQuery();
   while(rs.next()){
            a=rs.getDouble("CREDIT");
            b=rs.getString("LIBELLE");
            }
          
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
                 // JOptionPane.showMessageDialog(null, a);
     try{
 String sqls = "UPDATE ohada_trans SET CREDIT=?,LIBELLE=? where BATCH_ECRITURE='"+jTextField1.getText()+"' AND BUSS='"+buss.getText()+"' and CODE1='"+code1.getText()+"'";
        
         pst = con.prepareStatement(sqls);
          pst.setDouble(1,a-PR);
          pst.setString(2,b+"  - HT");
                     
         pst.executeUpdate();


}catch(Exception ex){JOptionPane.showMessageDialog(null,ex);}      
       try {
         pst = con.prepareStatement(queries);
    pst.setString(1, comptemere2.getText());
        pst.setString(2,compte2.getText());
         pst.setString(3,code_m2.getText());
         pst.setString(4,code2.getText());
         pst.setString(5,classe2.getText());
         pst.setString(6,substr2.getText());
         pst.setDouble(8,USD);
         pst.setString(7,"0");
         pst.setString(9,rolls);
         
       SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(jDateChooser1.getDate());
         pst.setString(10, addDate);
         pst.setString(11,buss.getText());
         pst.setString(12,libele.getText());
         pst.setString(13,code1.getText());
          pst.setString(14,code1.getText());
           pst.setString(15,"0");
            pst.setDouble(16,CDF);
            pst.setString(17,DEVICEEss);
            pst.setString(18,budget.getText());
           pst.setString(19,rolls_batch);
           pst.setString(20,ORGANIZATION);
          
         
          pst.executeUpdate();
    } catch (SQLException ex) {
         JOptionPane.showMessageDialog(null, ex);
    }
       }else{
    try {
         pst = con.prepareStatement(queries);
    pst.setString(1, comptemere1.getText());
        pst.setString(2,compte1.getText());
         pst.setString(3,code_m1.getText());
         pst.setString(4,code1.getText());
         pst.setString(5,classe1.getText());
         pst.setString(6,substr1.getText());
         pst.setDouble(8,USD);
         pst.setString(7,"0");
         pst.setString(9,rolls);
         
       SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(jDateChooser1.getDate());
         pst.setString(10, addDate);
         pst.setString(11,buss.getText());
         pst.setString(12,libele.getText());
         pst.setString(13,code1.getText());
          pst.setString(14,code2.getText());
           pst.setString(15,"0");
            pst.setDouble(16,CDF);
            pst.setString(17,DEVICEEss);
            pst.setString(18,budget.getText());
           pst.setString(19,rolls_batch);
           pst.setString(20,ORGANIZATION);
          
         
          pst.executeUpdate();
    } catch (SQLException ex) {
         JOptionPane.showMessageDialog(null, ex);
    }
    try {
         try{
            String sql="select * from monais WHERE caisse='"+code2.getText()+"'";
	   pst = con.prepareStatement(sql);          rs=pst.executeQuery();
            if(rs.next()){
          DEVICEEss=rs.getString("DEVICE");  
            }
            }
        
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex); }
    
     try{
String sql="SELECT * FROM  currency where APPR='"+DEVICEEss+"'";
pst = con.prepareStatement(sql);          rs=pst.executeQuery();
   while(rs.next()){
            //device=rs.getString("APPR")
             sale=rs.getDouble("SALE");
             buy=rs.getDouble("BUY"); 
               PRINCIPAL=rs.getString("PRINCIPAL"); 
            BUY=buy;//Achete le principal currency
            SALE=sale;//Ventre le principal currency
            }
          
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }

    // JOptionPane.showMessageDialog(null, PRINCIPAL);  
       if(PRINCIPAL.equals("Yes")){
        
   CDF=PR*buy;
  USD=PR;
  
  }else{
     
  USD=PR/sale;
  CDF=PR;
  } 
         pst = con.prepareStatement(queries);
    pst.setString(1, comptemere2.getText());
        pst.setString(2,compte2.getText());
         pst.setString(3,code_m2.getText());
         pst.setString(4,code2.getText());
         pst.setString(5,classe2.getText());
         pst.setString(6,substr2.getText());
         pst.setDouble(7,USD);
         pst.setString(8,"0");
         pst.setString(9,rolls);
         
       SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(jDateChooser1.getDate());
         pst.setString(10, addDate);
         pst.setString(11,buss.getText());
         pst.setString(12,libele.getText());
         pst.setString(13,code1.getText());
          pst.setString(14,code1.getText());
           pst.setString(16,"0");
            pst.setDouble(15,CDF);
            pst.setString(17,DEVICEEss);
            pst.setString(18,budget.getText());
           pst.setString(19,rolls_batch);
           pst.setString(20,ORGANIZATION);
          
         
          pst.executeUpdate();
    } catch (SQLException ex) {
         JOptionPane.showMessageDialog(null, ex);}
         }
    JOptionPane.showMessageDialog(null,"Transaction Done");}
       }
       minirefrsh();
    
      //---------------------------------------------------------------------------------------------------------------   
     //----------> OPERATION DIVERS A TRESORERIE

     }else if(!classe1.getText().equals("5") && classe2.getText().equals("5")){
//   here
     DEVICEEss=jComboBox2.getSelectedItem().toString();
     //  JOptionPane.showMessageDialog(null, "is op divers"); 
   try{
String sql="SELECT * FROM  currency where APPR='"+DEVICEEss+"'";
pst = con.prepareStatement(sql);          rs=pst.executeQuery();
   while(rs.next()){
            //device=rs.getString("APPR")
             sale=rs.getDouble("SALE");
             buy=rs.getDouble("BUY"); 
               PRINCIPAL=rs.getString("PRINCIPAL"); 
            BUY=buy;//Achete le principal currency
            SALE=sale;//Ventre le principal currency
            }
          
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }

    // JOptionPane.showMessageDialog(null, "is principal: "+PRINCIPAL);  
       if(PRINCIPAL.equals("Yes")){
  CDF=PR*sale;
  USD=PR;
  
  }else{
  USD=PR/sale;
  CDF=PR;
  } 
       
  
     // if(substr1.getText().equals("41")){
             if(jComboBox3.getSelectedItem().equals("Debit")){
                    try{ String sql="SELECT   sum(DEBIT),sum(CREDIT) FROM ohada_trans WHERE code='"+code2.getText()+"' AND BUSS='"+buss.getText()+"'";
   pst = con.prepareStatement(sql);          rs=pst.executeQuery();
            while(rs.next()){
              SUMDEBIT=rs.getDouble("sum(DEBIT)");
              SUMCREDIT=rs.getDouble("sum(CREDIT)");
             
               CHECK_OHADA=SUMDEBIT-SUMCREDIT;
                
                
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
              if(substr1.getText().equals("40")){
             try{
       
            String sql="SELECT   sum(DEBIT),sum(CREDIT) FROM budget_trans WHERE code='"+budget.getText()+"' AND PROJET='"+buss.getText()+"' AND BATCH='"+batchs.getSelectedItem()+"'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
              SUMDEBIT=rs.getDouble("sum(DEBIT)");
              SUMCREDIT=rs.getDouble("sum(CREDIT)");
            
               try{
            String sqlS="SELECT   NUM FROM PROJET WHERE PROJET_NUM='"+buss.getText()+"'";
           
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
           
}catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
             Double CHECK_BUDGETP=SUMCREDIT+USD;
            CHECK_BUDGET=(CHECK_BUDGETP*100)/SUMDEBIT;
            }else{
             CHECK_BUDGET=1.0;
             ECART=2.0;
            }
   if(CHECK_OHADA <USD){  
   JOptionPane.showMessageDialog(null,"Wrong Data"+"\n"+"No Enough Resources  ","Error",JOptionPane.ERROR_MESSAGE);
  }else if(CHECK_BUDGET >=ECART){ 
   JOptionPane.showMessageDialog(null,"Wrong Data"+"\n"+"No Enough Budget"+"\n"+"You are at: "+CHECK_BUDGET+" %","Error",JOptionPane.ERROR_MESSAGE);
  }else if(libele.getText().isEmpty()||batchs.getSelectedItem().equals("Batches")||budget.getText().isEmpty()||code2.getText().isEmpty()||code1.getText().isEmpty()||amount.getText().isEmpty()||amount.getText().equals("0.00")){ 
   JOptionPane.showMessageDialog(null,"Wrong Data"+"\n"+"Wrong Data ","Error",JOptionPane.ERROR_MESSAGE);
  
  }else if(libele.getText().isEmpty()||code2.getText().isEmpty()||code1.getText().isEmpty()||amount.getText().isEmpty()||amount.getText().equals("0.00")){ 
   JOptionPane.showMessageDialog(null,"Wrong Data"+"\n"+"Wrong Data ","Error",JOptionPane.ERROR_MESSAGE);
  
  }else{ 
     try {
         pst = con.prepareStatement(queries);
    pst.setString(1, comptemere1.getText());
        pst.setString(2,compte1.getText());
         pst.setString(3,code_m1.getText());
         pst.setString(4,code1.getText());
         pst.setString(5,classe1.getText());
         pst.setString(6,substr1.getText());
         pst.setDouble(7,USD);
         pst.setString(8,"0");
         pst.setString(9,rolls);
         
       SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(jDateChooser1.getDate());
         pst.setString(10, addDate);
         pst.setString(11,buss.getText());
         pst.setString(12,libele.getText());
         pst.setString(13,code1.getText());
          pst.setString(14,code2.getText());
           pst.setString(16,"0");
            pst.setDouble(15,CDF);
            pst.setString(17,DEVICEEss);
            pst.setString(18,budget.getText());
           pst.setString(19,rolls_batch);
           pst.setString(20,ORGANIZATION);
          
         
          pst.executeUpdate();
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
      
        try {
         pst = con.prepareStatement(queries);
    pst.setString(1, comptemere2.getText());
        pst.setString(2,compte2.getText());
         pst.setString(3,code_m2.getText());
         pst.setString(4,code2.getText());
         pst.setString(5,classe2.getText());
         pst.setString(6,substr2.getText());
         pst.setDouble(8,USD);
         pst.setString(7,"0");
         pst.setString(9,rolls);
         
       SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(jDateChooser1.getDate());
         pst.setString(10, addDate);
         pst.setString(11,buss.getText());
         pst.setString(12,libele.getText());
         pst.setString(13,code1.getText());
          pst.setString(14,code1.getText());
           pst.setString(15,"0");
            pst.setDouble(16,CDF);
            pst.setString(17,DEVICEEss);
            pst.setString(18,budget.getText());
           pst.setString(19,rolls_batch);
           pst.setString(20,ORGANIZATION);
          
         
          pst.executeUpdate();
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
      //--------->>>>budget
      if(substr1.getText().equals("40")){
     
       try{
          String sql="SELECT * FROM budget where CODE ='"+budget.getText()+"' and PROJECT='"+buss.getText()+"'";
       pst = con.prepareStatement(sql);          rs=pst.executeQuery();
            while(rs.next()){
               CAT=rs.getString("CAT");
              ITEM=rs.getString("ITEM");
               LB=rs.getString("LB");
               SUB_CAT=rs.getString("SUB_CAT");
               LBSUB=rs.getString("LBSUB");
           }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
             
            try{
    String sql="INSERT INTO `budget_trans`(`ITEM`, `DEBIT`, `CREDIT`, `SOLD`, `PROJET`, `CODE`, `NUM`, `CAT`, `DATES`, `MOIS`, `ANNEE`,SUB_CAT,CODE_CAT,CODE_SUB_CAT,ITEMS,BATCH) "+"values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    pst=con.prepareStatement(sql);
    pst.setString(1,libele.getText());
    pst.setString(2,"0");
    pst.setDouble(3,USD);
    pst.setString(4,"0.0");
    pst.setString(5,buss.getText());
    pst.setString(6,budget.getText());
    pst.setString(7,rolls);
    
    pst.setString(8,CAT);
    
     SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(jDateChooser1.getDate());
         pst.setString(9, addDate);
    SimpleDateFormat dateFormatsS = new SimpleDateFormat("MMM");
         String addDateS = dateFormatsS.format(jDateChooser1.getDate());
         pst.setString(10, addDateS);
         
          SimpleDateFormat dateFormatsSs = new SimpleDateFormat("yyyy");
         String addDateSs = dateFormatsSs.format(jDateChooser1.getDate());
         pst.setString(11, addDateSs);
    pst.setString (12,SUB_CAT);
    
     pst.setString(13,LB);
      pst.setString(14,LBSUB);
      
        pst.setString(15,ITEM);
        pst.setString(16,batchs.getSelectedItem().toString());
   
    
    
    pst.executeUpdate();
    // JOptionPane.showMessageDialog(null,"BUDGET TRANS");
    }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}
            
      }
        
        JOptionPane.showMessageDialog(null,"Transaction Done");}
       }else{
   try{ String sql="SELECT   sum(DEBIT),sum(CREDIT) FROM ohada_trans WHERE code='"+code1.getText()+"' AND BUSS='"+buss.getText()+"'";
   pst = con.prepareStatement(sql);          rs=pst.executeQuery();
            while(rs.next()){
              SUMDEBIT=rs.getDouble("sum(DEBIT)");
              SUMCREDIT=rs.getDouble("sum(CREDIT)");
             
               CHECK_OHADA=SUMDEBIT-SUMCREDIT;
                
                
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
    if(substr1.getText().equals("40")){
             try{
       
            String sql="SELECT   sum(DEBIT),sum(CREDIT) FROM budget_trans WHERE code='"+budget.getText()+"' AND PROJET='"+buss.getText()+"' AND BATCH='"+batchs.getSelectedItem()+"'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
              SUMDEBIT=rs.getDouble("sum(DEBIT)");
              SUMCREDIT=rs.getDouble("sum(CREDIT)");
            
               try{
            String sqlS="SELECT   NUM FROM PROJET WHERE PROJET_NUM='"+buss.getText()+"'";
           
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
           
}catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
             Double CHECK_BUDGETP=SUMCREDIT+USD;
            CHECK_BUDGET=(CHECK_BUDGETP*100)/SUMDEBIT;
            }else{
             CHECK_BUDGET=1.0;
             ECART=2.0;
            }
    //  JOptionPane.showMessageDialog(null,"Check 1");     
   if(CHECK_OHADA <USD){  
   JOptionPane.showMessageDialog(null,"Wrong Data"+"\n"+"No Enough Resources  ","Error",JOptionPane.ERROR_MESSAGE);
  }else if(CHECK_BUDGET >=ECART){ 
   JOptionPane.showMessageDialog(null,"Wrong Data"+"\n"+"No Enough Budget"+"\n"+"You are at: "+CHECK_BUDGET+" %","Error",JOptionPane.ERROR_MESSAGE);
  }else if(libele.getText().isEmpty()||batchs.getSelectedItem().equals("Batches")||budget.getText().isEmpty()||code2.getText().isEmpty()||code1.getText().isEmpty()||amount.getText().isEmpty()||amount.getText().equals("0.00")){ 
   JOptionPane.showMessageDialog(null,"Wrong Data"+"\n"+"Wrong Data ","Error",JOptionPane.ERROR_MESSAGE);
  
  }else if(libele.getText().isEmpty()||code2.getText().isEmpty()||code1.getText().isEmpty()||amount.getText().isEmpty()||amount.getText().equals("0.00")){ 
   JOptionPane.showMessageDialog(null,"Wrong Data"+"\n"+"Wrong Data ","Error",JOptionPane.ERROR_MESSAGE);
  
  }else{ 
   //    JOptionPane.showMessageDialog(null,"Check 2");   
    try {
         pst = con.prepareStatement(queries);
    pst.setString(1, comptemere1.getText());
        pst.setString(2,compte1.getText());
         pst.setString(3,code_m1.getText());
         pst.setString(4,code1.getText());
         pst.setString(5,classe1.getText());
         pst.setString(6,substr1.getText());
         pst.setDouble(8,USD);
         pst.setString(7,"0");
         pst.setString(9,rolls);
         
       SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(jDateChooser1.getDate());
         pst.setString(10, addDate);
         pst.setString(11,buss.getText());
         pst.setString(12,libele.getText());
         pst.setString(13,code1.getText());
          pst.setString(14,code2.getText());
           pst.setString(15,"0");
            pst.setDouble(16,CDF);
            pst.setString(17,DEVICEEss);
            pst.setString(18,budget.getText());
           pst.setString(19,rolls_batch);
           pst.setString(20,ORGANIZATION);
          
         
          pst.executeUpdate();
    } catch (SQLException ex) {
         JOptionPane.showMessageDialog(null, ex);
    }
    try {
         try{
            String sql="select * from monais WHERE caisse='"+code2.getText()+"'";
	   pst = con.prepareStatement(sql);          rs=pst.executeQuery();
            if(rs.next()){
          DEVICEEss=rs.getString("DEVICE");  
            }
            }
        
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex); }
    
     try{
String sql="SELECT * FROM  currency where APPR='"+DEVICEEss+"'";
pst = con.prepareStatement(sql);          rs=pst.executeQuery();
   while(rs.next()){
            //device=rs.getString("APPR")
             sale=rs.getDouble("SALE");
             buy=rs.getDouble("BUY"); 
               PRINCIPAL=rs.getString("PRINCIPAL"); 
            BUY=buy;//Achete le principal currency
            SALE=sale;//Ventre le principal currency
            }
          
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }

 //    JOptionPane.showMessageDialog(null, PRINCIPAL);  
       if(PRINCIPAL.equals("Yes")){
        
   CDF=PR*buy;
  USD=PR;
  
  }else{
     
  USD=PR/sale;
  CDF=PR;
  } 
         pst = con.prepareStatement(queries);
    pst.setString(1, comptemere2.getText());
        pst.setString(2,compte2.getText());
         pst.setString(3,code_m2.getText());
         pst.setString(4,code2.getText());
         pst.setString(5,classe2.getText());
         pst.setString(6,substr2.getText());
         pst.setDouble(7,USD);
         pst.setString(8,"0");
         pst.setString(9,rolls);
         
       SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(jDateChooser1.getDate());
         pst.setString(10, addDate);
         pst.setString(11,buss.getText());
         pst.setString(12,libele.getText());
         pst.setString(13,code1.getText());
          pst.setString(14,code1.getText());
           pst.setString(16,"0");
            pst.setDouble(15,CDF);
            pst.setString(17,DEVICEEss);
            pst.setString(18,budget.getText());
          pst.setString(19,rolls_batch); 
          pst.setString(20,ORGANIZATION);
          
         
          pst.executeUpdate();
    } catch (SQLException ex) {
         JOptionPane.showMessageDialog(null, ex);}
   //  JOptionPane.showMessageDialog(null,"Check 3");   
//    here
    if(check_select_Table.getText().equals("Yes")){
      
     int row= home_Table.jTable2.getSelectedRow();
        String   Table_click = (home_Table.jTable2.getModel().getValueAt(row,0). toString());
     //   JOptionPane.showMessageDialog(null,"Check 4");    
            try{
        String sql = "DELETE FROM budget_trans WHERE NUM=? and projet='"+buss.getText()+"'";
        
         pst = con.prepareStatement(sql);
         pst.setString(1,Table_click);
         pst.executeUpdate();

     }catch(SQLException | HeadlessException ex ){
      // JOptionPane.showMessageDialog(null, ex.getMessage());
     }
      //      JOptionPane.showMessageDialog(null,"Check 5"); 
    try{
         PreparedStatement pst = con.prepareStatement("UPDATE `etat_de_besoin` SET `ECRITURE`='' WHERE NUM_TRANS='"+Table_click+"'");
      pst.executeUpdate();

     }catch(SQLException | HeadlessException ex ){
         JOptionPane.showMessageDialog(null,ex); }
    }
   // JOptionPane.showMessageDialog(null,"Check 6"); 
    JOptionPane.showMessageDialog(null,"Transaction Done");}
       }
     minirefrsh();
 }
       }
        
 public void save(){
      try{
         
         String sql="SELECT * FROM  ID_TOKEN  where id=1";
          pst = con.prepareStatement(sql);
          rs=pst.executeQuery();
          if(rs.next()){
         ORGANIZATION = rs.getString("ORGANIZATION"); }
            }catch(SQLException ex ){JOptionPane.showMessageDialog(null,ex);}
      Rolls();
   RollBATCH();
     SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(jDateChooser1.getDate());//jDateChooser1
        
        
         SimpleDateFormat dateFormat1 = new SimpleDateFormat("MMMM");
         SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy");
         String mois = dateFormat1.format(jDateChooser1.getDate());
         String annee = dateFormat2.format(jDateChooser1.getDate());
    
           double USD = 0,sale = 0,buy = 0,BUY,CDF = 0,SALE,USDC,CDFC;
            String DEVICEEssC = null,DEVICEEss = null;
       int rowS= etat_de_besoin.jTable1.getSelectedRow();
        
        int indexs[]=etat_de_besoin.jTable1.getSelectedRows();
        if(code1.getText().isEmpty() ||code2.getText().isEmpty()||budget.getText().isEmpty()){
         JOptionPane.showMessageDialog(null,"Wrong Data"+"\n"+"Wrong Data ","Error",JOptionPane.ERROR_MESSAGE);
        
         }else if(etat_de_besoin.ecri.getText().equals("Yes")){
         JOptionPane.showMessageDialog(null,"Process already Done","Information",JOptionPane.WARNING_MESSAGE);//null,"Wrong Data"+"\n"+"Select one Caisse","Error",JOptionPane.ERROR_MESSAGE
        
         }else if(etat_de_besoin.appr.getText().equals("No")){
         JOptionPane.showMessageDialog(null,"EB Not Yet Approuved","Information",JOptionPane.WARNING_MESSAGE);//null,"Wrong Data"+"\n"+"Select one Caisse","Error",JOptionPane.ERROR_MESSAGE
        
         }else if(jDateChooser1.getText().equals("")){
         JOptionPane.showMessageDialog(null,"Select Date ","Error",JOptionPane.ERROR_MESSAGE);//null,"Wrong Data"+"\n"+"Select one Caisse","Error",JOptionPane.ERROR_MESSAGE
        
         }else{
             
             if(libele.getText().equals("Direct wording from Etat de besoin/Req")){
                for(int i=0; i < indexs.length;i++){
            
          String detsA = (etat_de_besoin.jTable1.getModel().getValueAt(indexs[i],1). toString());
          String dets = detsA.replace("'", "");
          String pts = (etat_de_besoin.jTable1.getModel().getValueAt(indexs[i],4). toString());
          String IDS = (etat_de_besoin.jTable1.getModel().getValueAt(indexs[i],0). toString());
          double PR = Double.parseDouble(pts);
          //String  addDate= (jTable1.getModel().getValueAt(indexs[i],5). toString());
                  
         Double c = Double.parseDouble(pts);
        
             try{
    String sql="select * from monais WHERE caisse='"+code1.getText()+"'";
	   pst = con.prepareStatement(sql);          rs=pst.executeQuery();
            if(rs.next()){
          DEVICEEss=rs.getString("DEVICE");  
            }
            }
        
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex); }
    
     try{
String sql="SELECT * FROM  currency where APPR='"+DEVICEEss+"'";
pst = con.prepareStatement(sql);          rs=pst.executeQuery();
   while(rs.next()){
            //device=rs.getString("APPR")
             sale=rs.getDouble("SALE");
             buy=rs.getDouble("BUY"); 
               PRINCIPAL=rs.getString("PRINCIPAL"); 
            BUY=buy;//Achete le principal currency
            SALE=sale;//Ventre le principal currency
            }
          
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }

   //  JOptionPane.showMessageDialog(null, PRINCIPAL);  
       if(PRINCIPAL.equals("Yes")){
         try{
            String sql="select * from monais WHERE caisse='"+code2.getText()+"'";
	   pst = con.prepareStatement(sql);          rs=pst.executeQuery();
            if(rs.next()){
          DEVICEEss=rs.getString("DEVICE");  
            }
            }
        
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex); }
    
     try{
String sql="SELECT * FROM  currency where APPR='"+DEVICEEss+"'";
pst = con.prepareStatement(sql);          rs=pst.executeQuery();
   while(rs.next()){
            //device=rs.getString("APPR")
             sale=rs.getDouble("SALE");
             buy=rs.getDouble("BUY"); 
               PRINCIPAL=rs.getString("PRINCIPAL"); 
            BUY=buy;//Achete le principal currency
            SALE=sale;//Ventre le principal currency
            }
          
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }   
           
   CDF=PR*sale;
  USD=PR;
  
  }else{
     
  USD=PR/sale;
  CDF=PR;
  }
     try{
            String sql="select * from monais WHERE caisse='"+code2.getText()+"'";
	   pst = con.prepareStatement(sql);          rs=pst.executeQuery();
            if(rs.next()){
          DEVICEEssC=rs.getString("DEVICE");  
            }
            }
        
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex); }
    
     try{
String sql="SELECT * FROM  currency where APPR='"+DEVICEEssC+"'";
pst = con.prepareStatement(sql);          rs=pst.executeQuery();
   while(rs.next()){
            //device=rs.getString("APPR")
             sale=rs.getDouble("SALE");
             buy=rs.getDouble("BUY"); 
               PRINCIPAL=rs.getString("PRINCIPAL"); 
//            BUYC=buy;
           // BUY=buy;
            }
          
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }

   //  JOptionPane.showMessageDialog(null, PRINCIPAL);  
       if(PRINCIPAL.equals("Yes")){ 
   CDFC=PR*buy;
  USDC=PR;
  
  }else{
     
  USDC=PR/sale;
  CDFC=PR;
  }        
       
     
    try{
          String sqls="SELECT * FROM budget where CODE ='"+budget.getText()+"' and PROJECT='"+buss.getText()+"'";
       pst = con.prepareStatement(sqls);          rs=pst.executeQuery();
            while(rs.next()){
               CAT=rs.getString("CAT");
              ITEM=rs.getString("ITEM");
               LB=rs.getString("LB");
               SUB_CAT=rs.getString("SUB_CAT");
               LBSUB=rs.getString("LBSUB");
           }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
              try{ String sql="SELECT   sum(DEBIT),sum(CREDIT) FROM ohada_trans WHERE code='"+code1.getText()+"' AND BUSS='"+buss.getText()+"'";
   pst = con.prepareStatement(sql);          rs=pst.executeQuery();
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
       
            String sql="SELECT   sum(DEBIT),sum(CREDIT) FROM budget_trans WHERE code='"+budget.getText()+"' AND PROJET='"+buss.getText()+"' AND BATCH='"+batchs.getSelectedItem()+"'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
              SUMDEBIT=rs.getDouble("sum(DEBIT)");
              SUMCREDIT=rs.getDouble("sum(CREDIT)");
            
               try{
            String sqlS="SELECT   NUM FROM PROJET WHERE PROJET_NUM='"+buss.getText()+"'";
           
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
            Double CHECK_BUDGETP=SUMCREDIT+USD;
            CHECK_BUDGET=(CHECK_BUDGETP*100)/SUMDEBIT;
           // jMenu7.setText(""+ECART);
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);
          
          
        }
                       try{
            String sqlS="SELECT * FROM OHADA_TRANS WHERE LIBELLE='"+dets+" Cfr "+etat_de_besoin.recherche.getText()+"' and BUSS='"+buss.getText()+"'";
           
            pst=con.prepareStatement(sqlS);
            rs=pst.executeQuery();
            if(rs.next()){
             EXIST="Yes";
             
            }else{
             EXIST="No";
           
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        } 
   if(CHECK_OHADA <USD){  
   JOptionPane.showMessageDialog(null,"Wrong Data"+"\n"+"No Enough Resources  ","Error",JOptionPane.ERROR_MESSAGE);
  }else if(CHECK_BUDGET >=ECART){ 
   JOptionPane.showMessageDialog(null,"Wrong Data"+"\n"+"No Enough Budget pour:"+"\n"+dets+"\n"+"You are at: "+CHECK_BUDGET+" %","Error",JOptionPane.ERROR_MESSAGE);
  }else if(libele.getText().isEmpty()||batchs.getSelectedItem().equals("Batches")||budget.getText().isEmpty()||code2.getText().isEmpty()||code1.getText().isEmpty()||amount.getText().isEmpty()||!amount.getText().equals("0.00")){ 
   JOptionPane.showMessageDialog(null,"Wrong Data"+"\n"+"Wrong Data ","Error",JOptionPane.ERROR_MESSAGE);
 
  }else if(EXIST.equals("Yes")){ 
  JOptionPane.showMessageDialog(null,"Wrong Data"+"\n"+"Process already Done pour:"+"\n"+dets,"Error",JOptionPane.ERROR_MESSAGE);
 
  }else{   
  try { 
      PreparedStatement pst = con.prepareStatement("INSERT INTO `budget_trans`(`ITEM`, `DEBIT`, `CREDIT`, `SOLD`,`PROJET`, `CODE`, `NUM`, `CAT`, `DATES`, `MOIS`, `ANNEE`,SUB_CAT,CODE_CAT,CODE_SUB_CAT,`ITEMS`,BATCH) "
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
         pst.setString(1, dets+" Cfr "+etat_de_besoin.recherche.getText());
         pst.setString(2,"0");
          pst.setDouble(3, USD);
          pst.setDouble(4, 0.0);
          pst.setString(5, buss.getText());
        pst.setString(6,budget.getText());
         pst.setString(7,rolls);
         pst.setString(8,CAT);
         pst.setString(9, addDate);
         pst.setString(10,mois);
          pst.setString(11,annee);
            pst.setString(12,SUB_CAT);
    
     pst.setString(13,LB);
      pst.setString(14,LBSUB);
      
        pst.setString(15,ITEM);
        pst.setString(16,batchs.getSelectedItem().toString());
        
          
       pst.executeUpdate();
        
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());}
         
     try {
         pst = con.prepareStatement(queries);
        pst.setString(1,comptemere1.getText());
        pst.setString(2, compte1.getText());
         pst.setString(3, code_m1.getText());
         pst.setString(4, code1.getText());
         pst.setString(5,classe1.getText());
         pst.setString(6,substr1.getText());
         pst.setDouble(8,USD);
         pst.setString(7,"0");
         pst.setString(9,rolls);
         pst.setString(10, addDate);
         pst.setString(11,buss.getText());
         pst.setString(12,dets+" Cfr "+etat_de_besoin.recherche.getText());
         pst.setString(13,"[JC]");
          pst.setString(14,code2.getText());
           pst.setString(15,"0");
            pst.setDouble(16,CDF);
             pst.setString(17, DEVICEEss);
              pst.setString(18,budget.getText());
              pst.setString(19,rolls_batch);
              pst.setString(20,ORGANIZATION);
       pst.executeUpdate();
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
           try {
        pst = con.prepareStatement(queries);
        pst.setString(1, comptemere2.getText());
        pst.setString(2, compte2.getText());
         pst.setString(3, code_m2.getText());
         pst.setString(4, code2.getText());
         pst.setString(5,classe2.getText());
         pst.setString(6, substr2.getText());
         pst.setDouble(7,USD);
         pst.setString(8,"0");
         pst.setString(9,rolls);
         pst.setString(10, addDate);
         pst.setString(11,buss.getText());
         pst.setString(12,dets+" Cfr "+etat_de_besoin.recherche.getText());
         pst.setString(13,"[JC]");
          pst.setString(14,code1.getText());
           pst.setDouble(15,CDF);
            pst.setString(16,"0");
              pst.setString(17, DEVICEEss);
               pst.setString(18,budget.getText());
               pst.setString(19,rolls_batch);
               pst.setString(20,ORGANIZATION);
       
          
         
          pst.executeUpdate();
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());}
      
          
            if(etat_de_besoin.jRadioButton1.isSelected()){
             try{
 String sqls = "UPDATE etat_de_besoin_b SET ECRITURE=?,NUM_TRANS=? WHERE NUM =? and ID='"+IDS+"'";
        
         pst = con.prepareStatement(sqls);
         pst.setString(3,etat_de_besoin.roll.getText());
          pst.setString(1,"Yes");
           pst.setString(2,rolls);
                     
         pst.executeUpdate();


}catch(Exception ex){JOptionPane.showMessageDialog(null,ex);}  
            }else{
             try{
 String sqls = "UPDATE etat_de_besoin SET ECRITURE=?,NUM_TRANS=? WHERE NUM =? and ID='"+IDS+"'";
        
         pst = con.prepareStatement(sqls);
         pst.setString(3,etat_de_besoin.roll.getText());
          pst.setString(1,"Yes");
           pst.setString(2,rolls);
                     
         pst.executeUpdate();


}catch(Exception ex){JOptionPane.showMessageDialog(null,ex);}  
            }  
       
     //   JOptionPane.showMessageDialog(null,"Transaction Saved checked 2");
         }
             }  
         JOptionPane.showMessageDialog(null,"Transaction Saved");       
   //--------------------------------------------------------------------------------------------------
   //End of if
             }else{
                 if(batchs.getSelectedItem().equals("Batches")||batchs.getSelectedItem().equals("")||budget.getText().equals("")||libele.getText().equals("")||amount.getText().equals("0.00")||amount.getText().equals("")||compte1.getText().equals("")||classe1.getText().equals("")||compte2.getText().equals("")||!etat_de_besoin.appr.getText().equals("Yes")){
 JOptionPane.showMessageDialog(null,"Wrong Data","Error",JOptionPane.ERROR_MESSAGE);
}else{
                    String Table_click1  = amount.getText();
String replaceString=Table_click1.replace(",", "");
 Double PR= Double.parseDouble(replaceString);
              try{
    String sql="select * from monais WHERE caisse='"+code1.getText()+"'";
	   pst = con.prepareStatement(sql);          rs=pst.executeQuery();
            if(rs.next()){
          DEVICEEss=rs.getString("DEVICE");  
            }
            }
        
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex); }
    
     try{
String sql="SELECT * FROM  currency where APPR='"+DEVICEEss+"'";
pst = con.prepareStatement(sql);          rs=pst.executeQuery();
   while(rs.next()){
            //device=rs.getString("APPR")
             sale=rs.getDouble("SALE");
             buy=rs.getDouble("BUY"); 
               PRINCIPAL=rs.getString("PRINCIPAL"); 
            BUY=buy;//Achete le principal currency
            SALE=sale;//Ventre le principal currency
            }
          
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }

   //  JOptionPane.showMessageDialog(null, PRINCIPAL);  
       if(PRINCIPAL.equals("Yes")){
         try{
            String sql="select * from monais WHERE caisse='"+code2.getText()+"'";
	   pst = con.prepareStatement(sql);          rs=pst.executeQuery();
            if(rs.next()){
          DEVICEEss=rs.getString("DEVICE");  
            }
            }
        
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex); }
    
     try{
String sql="SELECT * FROM  currency where APPR='"+DEVICEEss+"'";
pst = con.prepareStatement(sql);          rs=pst.executeQuery();
   while(rs.next()){
            //device=rs.getString("APPR")
             sale=rs.getDouble("SALE");
             buy=rs.getDouble("BUY"); 
               PRINCIPAL=rs.getString("PRINCIPAL"); 
            BUY=buy;//Achete le principal currency
            SALE=sale;//Ventre le principal currency
            }
          
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }   
           
   CDF=PR*sale;
  USD=PR;
  
  }else{
     
  USD=PR/sale;
  CDF=PR;
  }
     try{
            String sql="select * from monais WHERE caisse='"+code2.getText()+"'";
	   pst = con.prepareStatement(sql);          rs=pst.executeQuery();
            if(rs.next()){
          DEVICEEssC=rs.getString("DEVICE");  
            }
            }
        
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex); }
    
     try{
String sql="SELECT * FROM  currency where APPR='"+DEVICEEssC+"'";
pst = con.prepareStatement(sql);          rs=pst.executeQuery();
   while(rs.next()){
            //device=rs.getString("APPR")
             sale=rs.getDouble("SALE");
             buy=rs.getDouble("BUY"); 
               PRINCIPAL=rs.getString("PRINCIPAL"); 
//            BUYC=buy;
           // BUY=buy;
            }
          
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }

   //  JOptionPane.showMessageDialog(null, PRINCIPAL);  
       if(PRINCIPAL.equals("Yes")){ 
   CDFC=PR*buy;
  USDC=PR;
  
  }else{
     
  USDC=PR/sale;
  CDFC=PR;
  }        
       
     
    try{
          String sqls="SELECT * FROM budget where CODE ='"+budget.getText()+"' and PROJECT='"+buss.getText()+"'";
       pst = con.prepareStatement(sqls);          rs=pst.executeQuery();
            while(rs.next()){
               CAT=rs.getString("CAT");
              ITEM=rs.getString("ITEM");
               LB=rs.getString("LB");
               SUB_CAT=rs.getString("SUB_CAT");
               LBSUB=rs.getString("LBSUB");
           }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
                try{ String sql="SELECT   sum(DEBIT),sum(CREDIT) FROM ohada_trans WHERE code='"+code1.getText()+"' AND BUSS='"+buss.getText()+"'";
   pst = con.prepareStatement(sql);          rs=pst.executeQuery();
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
       
            String sql="SELECT   sum(DEBIT),sum(CREDIT) FROM budget_trans WHERE code='"+budget.getText()+"' AND PROJET='"+buss.getText()+"' AND BATCH='"+batchs.getSelectedItem()+"'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
              SUMDEBIT=rs.getDouble("sum(DEBIT)");
              SUMCREDIT=rs.getDouble("sum(CREDIT)");
            
               try{
            String sqlS="SELECT   NUM FROM PROJET WHERE PROJET_NUM='"+buss.getText()+"'";
           
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
            Double CHECK_BUDGETP=SUMCREDIT+USD;
            CHECK_BUDGET=(CHECK_BUDGETP*100)/SUMDEBIT;
           // jMenu7.setText(""+ECART);
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
   if(CHECK_OHADA <USD){  
   JOptionPane.showMessageDialog(null,"Wrong Data"+"\n"+"No Enough Resources  ","Error",JOptionPane.ERROR_MESSAGE);
  }else if(CHECK_BUDGET >=ECART){ 
   JOptionPane.showMessageDialog(null,"Wrong Data"+"\n"+"No Enough Budget"+"\n"+"You are at: "+CHECK_BUDGET+" %","Error",JOptionPane.ERROR_MESSAGE);
  }else if(libele.getText().isEmpty()||batchs.getSelectedItem().equals("Batches")||budget.getText().isEmpty()||code2.getText().isEmpty()||code1.getText().isEmpty()||amount.getText().isEmpty()||amount.getText().equals("0.00")){ 
   JOptionPane.showMessageDialog(null,"Wrong Data"+"\n"+"Wrong Data ","Error",JOptionPane.ERROR_MESSAGE);
  
  }else{      
  try { 
      PreparedStatement pst = con.prepareStatement("INSERT INTO `budget_trans`(`ITEM`, `DEBIT`, `CREDIT`, `SOLD`,`PROJET`, `CODE`, `NUM`, `CAT`, `DATES`, `MOIS`, `ANNEE`,SUB_CAT,CODE_CAT,CODE_SUB_CAT,`ITEMS`,BATCH) "
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
         pst.setString(1,libele.getText()+" Cfr "+etat_de_besoin.recherche.getText());
         pst.setString(2,"0");
          pst.setDouble(3, USD);
          pst.setDouble(4, 0.0);
          pst.setString(5, buss.getText());
        pst.setString(6,budget.getText());
         pst.setString(7,rolls);
         pst.setString(8,CAT);
         pst.setString(9, addDate);
         pst.setString(10,mois);
          pst.setString(11,annee);
            pst.setString(12,SUB_CAT);
    
     pst.setString(13,LB);
      pst.setString(14,LBSUB);
      
        pst.setString(15,ITEM);
        pst.setString(16,batchs.getSelectedItem().toString());
        
          
       pst.executeUpdate();
        
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());}
         
     try {
         pst = con.prepareStatement(queries);
        pst.setString(1,comptemere1.getText());
        pst.setString(2, compte1.getText());
         pst.setString(3, code_m1.getText());
         pst.setString(4, code1.getText());
         pst.setString(5,classe1.getText());
         pst.setString(6,substr1.getText());
         pst.setDouble(8,USD);
         pst.setString(7,"0");
         pst.setString(9,rolls);
         pst.setString(10, addDate);
         pst.setString(11,buss.getText());
         pst.setString(12,libele.getText()+" Cfr "+etat_de_besoin.recherche.getText());
         pst.setString(13,"[JC]");
          pst.setString(14,code2.getText());
           pst.setString(15,"0");
            pst.setDouble(16,CDF);
             pst.setString(17, DEVICEEss);
              pst.setString(18,budget.getText());
              pst.setString(19,rolls_batch);
              pst.setString(20,ORGANIZATION);
       pst.executeUpdate();
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
           try {
        pst = con.prepareStatement(queries);
        pst.setString(1, comptemere2.getText());
        pst.setString(2, compte2.getText());
         pst.setString(3, code_m2.getText());
         pst.setString(4, code2.getText());
         pst.setString(5,classe2.getText());
         pst.setString(6, substr2.getText());
         pst.setDouble(7,USD);
         pst.setString(8,"0");
         pst.setString(9,rolls);
         pst.setString(10, addDate);
         pst.setString(11,buss.getText());
         pst.setString(12,libele.getText()+" Cfr "+etat_de_besoin.recherche.getText());
         pst.setString(13,"[JC]");
          pst.setString(14,code1.getText());
           pst.setDouble(15,CDF);
            pst.setString(16,"0");
              pst.setString(17, DEVICEEss);
               pst.setString(18,budget.getText());
               pst.setString(19,rolls_batch);
               pst.setString(20,ORGANIZATION);
       
          
         
          pst.executeUpdate();
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());}
      
          
            if(etat_de_besoin.jRadioButton1.isSelected()){
             try{
 String sqls = "UPDATE etat_de_besoin_b SET ECRITURE=?,NUM_TRANS=? WHERE NUM =?";
        
         pst = con.prepareStatement(sqls);
         pst.setString(3,etat_de_besoin.roll.getText());
          pst.setString(1,"Yes");
           pst.setString(2,rolls);
                     
         pst.executeUpdate();


}catch(Exception ex){JOptionPane.showMessageDialog(null,ex);}  
            }else{
             try{
 String sqls = "UPDATE etat_de_besoin SET ECRITURE=?,NUM_TRANS=? WHERE NUM =?";
        
         pst = con.prepareStatement(sqls);
         pst.setString(3,etat_de_besoin.roll.getText());
          pst.setString(1,"Yes");
           pst.setString(2,rolls);
                     
         pst.executeUpdate();


}catch(Exception ex){JOptionPane.showMessageDialog(null,ex);}  
            } 
             JOptionPane.showMessageDialog(null,"Transaction Saved");
                 }
             }
                
             }
             
     
         
         minirefrsh();
       }
 }
 public void minirefrsh(){
 libele.setText("");
amount.setText("0.00");
//jComboBox2.setSelectedItem("....");
ohada.setText("");
budget.setText("-");

//compte1.setText("");
//code1.setText("");
//code_m1.setText("");
//substr1.setText("");
//classe1.setText("");
//comptemere1.setText("");
compte2.setText("");
code2.setText("");
code_m2.setText("");
substr2.setText("");
classe2.setText("");
comptemere2.setText("");

 
 }
 public void refresh(){
 //buss.setText("Select One Business");
//jTextField2.setText("0.00");
//jTextField3.setText("0.00");
//jTextField6.setText("0.00");
jTextField1.setText("");
//jTextField4.setText("0.00");jTextField5.setText("0.00");
//usd.setText("0.00");

//batchs.setSelectedItem("Batches");
//batchs1.setSelectedItem("Day");
libele.setText("");
amount.setText("0.00");
//jComboBox2.setSelectedItem("....");
ohada.setText("");
budget.setText("-");

//compte1.setText("");
//code1.setText("");
//code_m1.setText("");
//substr1.setText("");
//classe1.setText("");
//comptemere1.setText("");
compte2.setText("");
code2.setText("");
code_m2.setText("");
substr2.setText("");
classe2.setText("");
comptemere2.setText("");


//jrn.setText("JRN-");
//journals.setText("");
check_select_Table.setText("No");
eb.setText("No");
// boody.removeAll();
//boody.add(new Table());
//boody.repaint();
//boody.revalidate();
 
 }
 
  public void multilanguagefrench(){
  currency.setText("Monnais");
  save.setText("Enreg.");
  jLabel10.setText("Bon");
  jLabel11.setText("Imprim");
  jLabel13.setText("Avance Salaire");
  jLabel12.setText("Suppr.");
 // jPanel16.
  }
//___________________________________________________  
  //>>>>>>>>>>>import
   public void imports(){
        boody.removeAll();
boody.add(new home_Table());
boody.repaint();
boody.revalidate();
        CAISSE_Model();
       DefaultTableModel excels= (DefaultTableModel)home_Table.jTable2.getModel(); 
        
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
       
       
        
        
        
        home_Table.jTable2.setModel(mode);

  }
  
public void checkdata(){
    String code,lb;
DefaultTableModel excels= (DefaultTableModel)home_Table.jTable2.getModel(); 
for(int i=0; i < excels.getRowCount();i++){
code= excels.getValueAt(i,1). toString();
lb = excels.getValueAt(i,2). toString();

  try{
          String sqls="SELECT * FROM  budget WHERE code='"+lb+"' and PROJECT='"+buss.getText()+"'";
          
            pst=con.prepareStatement(sqls);
            //pst.setString(1, tmp);
            rs=pst.executeQuery();
            if(rs.next()){
         }else{
        JOptionPane.showMessageDialog(null,lb +"  Not Exist","Warning",JOptionPane.WARNING_MESSAGE);
            }
  
      }catch(Exception ex){
        JOptionPane.showMessageDialog(null,ex);  
      }
   try{
             
            String sqls="SELECT * FROM   OHADA where CODE='"+code+"'";
          
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
            if(rs.next()){
              
            }else{
            JOptionPane.showMessageDialog(null,code +"  Not Exist","Warning",JOptionPane.WARNING_MESSAGE);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
}
JOptionPane.showMessageDialog(null, "Ok");
}
       public void Save_CAISSE_usd(){
       String COMPTEMEREEXCEL = null,CODEMEREEXCEL = null,CODEEXCEL,NAMEEXCEL = null, CLASSEXCEL = null,SUBSTRSEXCEL = null,BATCH=null;
   DefaultTableModel excels= (DefaultTableModel)home_Table.jTable2.getModel(); 
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
     
     String Batch_ecri=null;
       SimpleDateFormat dateFormatsS = new SimpleDateFormat("yyyy-MM-dd");
         String addDateS = dateFormatsS.format(jDateChooser1.getDate());
        String sql="SELECT BATCH_ECRITURE FROM ohada_trans where SUBSTR(BATCH_ECRITURE,5,10)='"+addDateS+"' ORDER BY BATCH_ECRITURE DESC LIMIT 1";
       if(jTextField1.getText().equals("")){
        try{
          String sqls="SELECT MAX(BATCH_ECRITURE) FROM ohada_trans where buss ='"+buss.getText()+"'";
        pst = con.prepareStatement(sqls);          rs=pst.executeQuery();
           if(rs.next()){
              Batch_ecri=rs.getString("MAX(BATCH_ECRITURE)");
             // jTextField1.setText(Batch_ecri);
              
           try{
		   pst = con.prepareStatement(sql);          rs=pst.executeQuery();
             if(rs.next()){
                 String rl=Batch_ecri;
                 int ln=rl.length();
                 String stxt=rl.substring(0,15);
                 String snum=rl.substring(15,ln);
                 int n = Integer.parseInt(snum);
                 n++;
                 snum=Integer.toString(n);
                rolls_batch=stxt+snum;
                jTextField1.setText(rolls_batch);
             }else{
            rolls_batch= "No: "+addDateS+"/1001";
                jTextField1.setText(rolls_batch);  
             }
         }catch(NumberFormatException | SQLException e){
             JOptionPane.showMessageDialog(null, e);  
         }  
               
          }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        } 
       }else{
       rolls_batch=jTextField1.getText();
       } 
     
    if(CODEEXCEL.equals("-") && LB.equals("-") && CREDIT_USD.equals("0.0")){
     //`COMPTE_M`, `COMPTE`, `CODE_M`, `CODE`, `CLASSE`, `SUBSTR`, `DEBIT`, `CREDIT`, `NUM`, `DATES`, `BUSS`, `LIBELLE`, `JOURNAL`, `CODE1`,DEBIT_CDF,CREDIT_CDF,DEVICE,LB,BATCH_ECRITURE  
  
              try {
        pst = con.prepareStatement(queries);  
        pst.setString(1, "");
        pst.setString(2,comptemere1.getText());
         pst.setString(3, "");
         pst.setString(4, code1.getText());
         pst.setString(5,classe1.getText());
         pst.setString(6,substr1.getText());
         pst.setString(7,DEBIT_USD);
         pst.setString(8,"0");
         pst.setString(9,rolls);
         
       SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(jDateChooser1.getDate());
         pst.setString(10, addDate);
         pst.setString(11,buss.getText());
         pst.setString(12,LIBELLE);
         pst.setString(13,"[JC]");
          pst.setString(14,"");
           pst.setString(15,amount.getText());
            pst.setDouble(16,0.0);
            pst.setString(17,devise.getText());
            
            pst.setString(18,LB);
       pst.setString(19,rolls_batch);  
       pst.setString(20,ORGANIZATION);
          pst.executeUpdate();
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
 }else if((LB.equals("-") && CREDIT_USD.equals("0.0"))){
          // APPROVISIONEMENT
         try{
             
            String sqls="SELECT * FROM   OHADA where CODE='"+CODEEXCEL+"'";
          
            pst=con.prepareStatement(sqls);
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
             
            String sqls="SELECT * FROM   OHADA where CODE='"+code1.getText()+"'";
          
            pst=con.prepareStatement(sqls);
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
     pst = con.prepareStatement(queries);  
        
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
         pst.setString(11,buss.getText());
         pst.setString(12,LIBELLE);
         pst.setString(13,"[JC]");
          pst.setString(14,CODE);
           pst.setString(16,"0");
            pst.setDouble(15,0.0);
      pst.setString(17,devise.getText());
            
            pst.setString(18,LB);
       pst.setString(19,rolls_batch); 
       pst.setString(20,ORGANIZATION);
          pst.executeUpdate();
        
           //      JOptionPane.showMessageDialog(null,"Caisse saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
        
        try {
         String tmp =jComboBox3.getSelectedItem().toString();
        pst = con.prepareStatement(queries);  
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
         pst.setString(11,buss.getText());
         pst.setString(12,LIBELLE);
         pst.setString(13,"OK");
          pst.setString(14, CODEEXCEL);
          pst.setDouble(16,0.0);
            pst.setString(15,"0");
       
    pst.setString(17,jComboBox2.getSelectedItem().toString());  
    pst.setString(18,LB);
       pst.setString(19,rolls_batch);      
         
          pst.executeUpdate();
        
               //  JOptionPane.showMessageDialog(null,"Caisse saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
     
        
   }else if ((LB.equals("-") && DEBIT_USD.equals("0.0"))){
////   TRANSFERT CAISSE
 try{
             
            String sqls="SELECT * FROM   OHADA where CODE='"+CODEEXCEL+"'";
          
            pst=con.prepareStatement(sqls);
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
             
            String sqls="SELECT * FROM   OHADA where CODE='"+code1.getText()+"'";
          
            pst=con.prepareStatement(sqls);
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
       pst = con.prepareStatement(queries);  
        
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
         pst.setString(11,buss.getText());
         pst.setString(12,LIBELLE);
         pst.setString(13,"[JC]");
          pst.setString(14,CODE);
           pst.setString(16,"0");
            pst.setDouble(15,0.0);
       pst.setString(17, jComboBox2.getSelectedItem().toString());
            pst.setString(18,LB);
       pst.setString(19,rolls_batch); 
       pst.setString(20,ORGANIZATION);
         
          pst.executeUpdate();
        
           //      JOptionPane.showMessageDialog(null,"Caisse saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
        
        try {
         String tmp =jComboBox3.getSelectedItem().toString();
        pst = con.prepareStatement(queries);  
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
         pst.setString(11,buss.getText());
         pst.setString(12,LIBELLE);
         pst.setString(13,"[JC]");
          pst.setString(14, CODEEXCEL);
          pst.setDouble(16,0.0);
            pst.setString(15,"0");
       
    pst.setString(17,jComboBox2.getSelectedItem().toString());      
           pst.setString(18,LB);
       pst.setString(19,rolls_batch); 
       pst.setString(20,ORGANIZATION);
          pst.executeUpdate();
        
               //  JOptionPane.showMessageDialog(null,"Caisse saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
      
   }else if (DEBIT_USD.equals("0.0")){
////// CREDIT
try{
             
            String sqls="SELECT * FROM   OHADA where CODE='"+CODEEXCEL+"'";
          
            pst=con.prepareStatement(sqls);
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
             
            String sqls="SELECT * FROM   OHADA where CODE='"+code1.getText()+"'";
          
            pst=con.prepareStatement(sqls);
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
        pst = con.prepareStatement(queries);  
        
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
         pst.setString(11,buss.getText());
         pst.setString(12,LIBELLE);
         pst.setString(13,"[JC]");
          pst.setString(14,CODE);
           pst.setString(16,"0");
            pst.setDouble(15,0.0);
       pst.setString(17, jComboBox2.getSelectedItem().toString());
        pst.setString(18,LB);
       pst.setString(19,rolls_batch); 
       pst.setString(20,ORGANIZATION);
          
         
          pst.executeUpdate();
        
           //      JOptionPane.showMessageDialog(null,"Caisse saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
        
        try {
       pst = con.prepareStatement(queries);  
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
         pst.setString(11,buss.getText());
         pst.setString(12,LIBELLE);
         pst.setString(13,"[JC]");
          pst.setString(14, CODEEXCEL);
          pst.setDouble(16,0.0);
            pst.setString(15,"0");
       
    pst.setString(17,jComboBox2.getSelectedItem().toString()); 
       pst.setString(18,LB);
       pst.setString(19,rolls_batch);
       pst.setString(20,ORGANIZATION);
          pst.executeUpdate();
        
               //  JOptionPane.showMessageDialog(null,"Caisse saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
     

 //BUDGET CREDIT
      String ITEM = null,CODECAT = null,CODESUBCAT = null,CAT = null,SUBCAT = null;
       try{
          String sqls="SELECT item,code FROM  budget_code WHERE code='"+LB+"' and CAT='"+buss.getText()+"'";
          
            pst=con.prepareStatement(sqls);
            //pst.setString(1, tmp);
            rs=pst.executeQuery();
            if(rs.next()){
                 
                ITEM = rs.getString("item"); 
          }
  
      }catch(Exception ex){
        JOptionPane.showMessageDialog(null,ex);  
      }
        
          try{
          String sqls="SELECT CAT,LB,LBSUB,SUB_CAT FROM  budget WHERE code='"+LB+"' and PROJECT='"+buss.getText()+"'";
          
            pst=con.prepareStatement(sqls);
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
    String sqls="INSERT INTO `budget_trans`(`ITEM`, `DEBIT`, `CREDIT`, `SOLD`, `PROJET`, `CODE`, `NUM`, `CAT`, `DATES`, `MOIS`, `ANNEE`,SUB_CAT,CODE_CAT,CODE_SUB_CAT,ITEMS,BATCH) "+"values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    pst=con.prepareStatement(sqls);
    pst.setString(1,LIBELLE);
    pst.setString(2,"0");
    pst.setString(3,CREDIT_USD);
    pst.setDouble(4, 0);
    pst.setString(5,buss.getText());
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
       
 /// import excel
    public void openFile(String file){
        try{
            File path = new File(file);
            Desktop.getDesktop().open(path);
        }catch(IOException ioe){
            System.out.println(ioe);
        }
    } 
    public void export(){
      try{
           JFileChooser jFileChooser = new JFileChooser();
           jFileChooser.showSaveDialog(this);
           File saveFile = jFileChooser.getSelectedFile();
           
           if(saveFile != null){
               saveFile = new File(saveFile.toString()+".xlsx");
               Workbook wb = new XSSFWorkbook();
               Sheet sheet = wb.createSheet("customer");
               
               Row rowCol = sheet.createRow(0);
               for(int i=0;i<home_Table.jTable2.getColumnCount();i++){
                   Cell cell = rowCol.createCell(i);
                   cell.setCellValue(home_Table.jTable2.getColumnName(i));
               }
               
               for(int j=0;j<home_Table.jTable2.getRowCount();j++){
                   Row row = sheet.createRow(j+1);
                   for(int k=0;k<home_Table.jTable2.getColumnCount();k++){
                       Cell cell = row.createCell(k);
                       if(home_Table.jTable2.getValueAt(j, k)!=null){
                           cell.setCellValue(home_Table.jTable2.getValueAt(j, k).toString());
                       }
                   }
               }
               FileOutputStream out = new FileOutputStream(new File(saveFile.toString()));
               wb.write(out);
            //   wb.close();
               out.close();
               openFile(saveFile.toString());
            //    JOptionPane.showMessageDialog(null,"Tranction Saved");
           }else{
               JOptionPane.showMessageDialog(null,"Error al generar archivo");
           }
       }catch(FileNotFoundException e){
           System.out.println(e);
       }catch(IOException io){
           System.out.println(io);
       }
    
    }
 public void encaissement(){
      double USD = 0,sale = 0,buy = 0,BUY = 0,CDF = 0,SALE = 0,USDC,CDFC;
   String Table_click1  = amount.getText();
String replaceString=Table_click1.replace(",", "");
 Double PR= Double.parseDouble(replaceString);
  
  String DEVICEEssC = null,DEVICEEss = null;
  try{
         
         String sql="SELECT * FROM  ID_TOKEN  where id=1";
          pst = con.prepareStatement(sql);
          rs=pst.executeQuery();
          if(rs.next()){
         ORGANIZATION = rs.getString("ORGANIZATION"); }
            }catch(SQLException ex ){JOptionPane.showMessageDialog(null,ex);}
   Rolls();
   RollBATCH();


 
       if(code2.getText().isEmpty()){
    try{
            String sql="select * from monais WHERE caisse='"+code1.getText()+"'";
	   pst = con.prepareStatement(sql);          rs=pst.executeQuery();
            if(rs.next()){
          DEVICEEss=rs.getString("DEVICE");  
            }else{
                 try{
String sqls="SELECT * FROM  currency where PRINCIPAL='YES'";
pst = con.prepareStatement(sqls);          rs=pst.executeQuery();
   while(rs.next()){
            DEVICEEss=rs.getString("APPR"); 
            }
          
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
            }
            }
        
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex); }
    
     try{
String sql="SELECT * FROM  currency where APPR='"+DEVICEEss+"'";
pst = con.prepareStatement(sql);          rs=pst.executeQuery();
   while(rs.next()){
             sale=rs.getDouble("SALE");
             buy=rs.getDouble("BUY"); 
            PRINCIPAL=rs.getString("PRINCIPAL"); 
            BUY=buy;
            SALE=sale;
            }
          
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
JOptionPane.showMessageDialog(null, BUY+" "+SALE+" "+PRINCIPAL); 
       if(PRINCIPAL.equals("Yes")){ 
   CDFC=PR*buy;
  USDC=PR;
  
  }else{
     
  USDC=PR/sale;
  CDFC=PR;
  } 
        JOptionPane.showMessageDialog(null, USD+" "+CDF); 
        if(jComboBox3.getSelectedItem().equals("Credit")){
            
    if(libele.getText().isEmpty()||batchs.getSelectedItem().equals("Batches")||budget.getText().isEmpty()||code1.getText().isEmpty()||amount.getText().isEmpty()||amount.getText().equals("0.00")){ 
   JOptionPane.showMessageDialog(null,"Wrong Data"+"\n"+"Wrong Data ","Error",JOptionPane.ERROR_MESSAGE);
  
  }else{ 
    try {
         pst = con.prepareStatement(queries);
    pst.setString(1, comptemere1.getText());
        pst.setString(2,compte1.getText());
         pst.setString(3,code_m1.getText());
         pst.setString(4,code1.getText());
         pst.setString(5,classe1.getText());
         pst.setString(6,substr1.getText());
         pst.setDouble(8,USDC);
         pst.setString(7,"0");
         pst.setString(9,rolls);
         
       SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(jDateChooser1.getDate());
         pst.setString(10, addDate);
         pst.setString(11,buss.getText());
         pst.setString(12,libele.getText());
         pst.setString(13,code1.getText());
          pst.setString(14,code1.getText());
           pst.setString(15,"0");
            pst.setDouble(16,CDFC);
            pst.setString(17,DEVICEEss);
            pst.setString(18,budget.getText());
           pst.setString(19,rolls_batch);
           pst.setString(20,ORGANIZATION);
          
         
          pst.executeUpdate();
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }        
 JOptionPane.showMessageDialog(null,"Transaction Done");
       }
       }else{
      
        if(libele.getText().isEmpty()||code1.getText().isEmpty()||amount.getText().isEmpty()||amount.getText().equals("0.00")){ 
   JOptionPane.showMessageDialog(null,"Wrong Data"+"\n"+"Wrong Data ","Error",JOptionPane.ERROR_MESSAGE);
  }else { 
         
     try {
         pst = con.prepareStatement(queries);
    pst.setString(1, comptemere1.getText());
        pst.setString(2,compte1.getText());
         pst.setString(3,code_m1.getText());
         pst.setString(4,code1.getText());
         pst.setString(5,classe1.getText());
         pst.setString(6,substr1.getText());
         pst.setDouble(7,USDC);
         pst.setString(8,"0");
         pst.setString(9,rolls);
         
       SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(jDateChooser1.getDate());
         pst.setString(10, addDate);
         pst.setString(11,buss.getText());
         pst.setString(12,libele.getText());
         pst.setString(13,code1.getText());
          pst.setString(14,code1.getText());
           pst.setString(16,"0");
            pst.setDouble(15,CDFC);
            pst.setString(17,DEVICEEss);
            pst.setString(18,budget.getText());
           pst.setString(19,rolls_batch);
           pst.setString(20,ORGANIZATION);
          
         
          pst.executeUpdate();
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
      
      
        JOptionPane.showMessageDialog(null,"Transaction Done");}
    }       
           
       }else{
            try{
            String sql="select * from monais WHERE caisse='"+code1.getText()+"'";
	   pst = con.prepareStatement(sql);          rs=pst.executeQuery();
            if(rs.next()){
          DEVICEEss=rs.getString("DEVICE");  
            }else{
                 try{
String sqls="SELECT * FROM  currency where PRINCIPAL='YES'";
pst = con.prepareStatement(sqls);          rs=pst.executeQuery();
   while(rs.next()){
            DEVICEEss=rs.getString("APPR"); 
            }
          
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
            }
            }
        
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex); }
    
     try{
String sql="SELECT * FROM  currency where APPR='"+DEVICEEss+"'";
pst = con.prepareStatement(sql);          rs=pst.executeQuery();
   while(rs.next()){
            //device=rs.getString("APPR")
             sale=rs.getDouble("SALE");
             buy=rs.getDouble("BUY"); 
               PRINCIPAL=rs.getString("PRINCIPAL"); 
            BUY=buy;//Achete le principal currency
            SALE=sale;//Ventre le principal currency
            }
          
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }

   //  JOptionPane.showMessageDialog(null, PRINCIPAL);  
       if(PRINCIPAL.equals("Yes")){
         try{
            String sql="select * from monais WHERE caisse='"+code2.getText()+"'";
	   pst = con.prepareStatement(sql);          rs=pst.executeQuery();
            if(rs.next()){
          DEVICEEss=rs.getString("DEVICE");  
            }
            }
        
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex); }
    
     try{
String sql="SELECT * FROM  currency where APPR='"+DEVICEEss+"'";
pst = con.prepareStatement(sql);          rs=pst.executeQuery();
   while(rs.next()){
            //device=rs.getString("APPR")
             sale=rs.getDouble("SALE");
             buy=rs.getDouble("BUY"); 
               PRINCIPAL=rs.getString("PRINCIPAL"); 
            BUY=buy;//Achete le principal currency
            SALE=sale;//Ventre le principal currency
            }
          
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }   
           
   CDF=PR*sale;
  USD=PR;
  
  }else{
     
  USD=PR/sale;
  CDF=PR;
  }
     try{
            String sql="select * from monais WHERE caisse='"+code2.getText()+"'";
	   pst = con.prepareStatement(sql);          rs=pst.executeQuery();
            if(rs.next()){
          DEVICEEssC=rs.getString("DEVICE");  
            }
            }
        
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex); }
    
     try{
String sql="SELECT * FROM  currency where APPR='"+DEVICEEssC+"'";
pst = con.prepareStatement(sql);          rs=pst.executeQuery();
   while(rs.next()){
            //device=rs.getString("APPR")
             sale=rs.getDouble("SALE");
             buy=rs.getDouble("BUY"); 
               PRINCIPAL=rs.getString("PRINCIPAL"); 
//            BUYC=buy;
           // BUY=buy;
            }
          
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }

   //  JOptionPane.showMessageDialog(null, PRINCIPAL);  
       if(PRINCIPAL.equals("Yes")){ 
   CDFC=PR*buy;
  USDC=PR;
  
  }else{
     
  USDC=PR/sale;
  CDFC=PR;
  } 
       if(jComboBox3.getSelectedItem().equals("Credit")){
            
    if(libele.getText().isEmpty()||batchs.getSelectedItem().equals("Batches")||budget.getText().isEmpty()||code2.getText().isEmpty()||code1.getText().isEmpty()||amount.getText().isEmpty()||amount.getText().equals("0.00")){ 
   JOptionPane.showMessageDialog(null,"Wrong Data"+"\n"+"Wrong Data ","Error",JOptionPane.ERROR_MESSAGE);
  
  }else{ 
    try {
         pst = con.prepareStatement(queries);
    pst.setString(1, comptemere1.getText());
        pst.setString(2,compte1.getText());
         pst.setString(3,code_m1.getText());
         pst.setString(4,code1.getText());
         pst.setString(5,classe1.getText());
         pst.setString(6,substr1.getText());
         pst.setDouble(8,USD);
         pst.setString(7,"0");
         pst.setString(9,rolls);
         
       SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(jDateChooser1.getDate());
         pst.setString(10, addDate);
         pst.setString(11,buss.getText());
         pst.setString(12,libele.getText());
         pst.setString(13,code2.getText());
          pst.setString(14,code2.getText());
           pst.setString(15,"0");
            pst.setDouble(16,CDF);
            pst.setString(17,DEVICEEss);
            pst.setString(18,budget.getText());
           pst.setString(19,rolls_batch);
           pst.setString(20,ORGANIZATION);
          
         
          pst.executeUpdate();
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
    try {
         pst = con.prepareStatement(queries);
    pst.setString(1, comptemere2.getText());
        pst.setString(2,compte2.getText());
         pst.setString(3,code_m2.getText());
         pst.setString(4,code2.getText());
         pst.setString(5,classe2.getText());
         pst.setString(6,substr2.getText());
         pst.setDouble(7,USD);
         pst.setString(8,"0");
         pst.setString(9,rolls);
         
       SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(jDateChooser1.getDate());
         pst.setString(10, addDate);
         pst.setString(11,buss.getText());
         pst.setString(12,libele.getText());
         pst.setString(13,code2.getText());
          pst.setString(14,code1.getText());
           pst.setString(16,"0");
            pst.setDouble(15,CDF);
            pst.setString(17,DEVICEEss);
            pst.setString(18,budget.getText());
           pst.setString(19,rolls_batch);
           pst.setString(20,ORGANIZATION);
          
         
          pst.executeUpdate();
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());}
        
       
        
          
 JOptionPane.showMessageDialog(null,"Transaction Done");
       }
       }else{
      
        if(libele.getText().isEmpty()||code2.getText().isEmpty()||code1.getText().isEmpty()||amount.getText().isEmpty()||amount.getText().equals("0.00")){ 
   JOptionPane.showMessageDialog(null,"Wrong Data"+"\n"+"Wrong Data ","Error",JOptionPane.ERROR_MESSAGE);
  }else { 
         
     try {
         pst = con.prepareStatement(queries);
    pst.setString(1, comptemere1.getText());
        pst.setString(2,compte1.getText());
         pst.setString(3,code_m1.getText());
         pst.setString(4,code1.getText());
         pst.setString(5,classe1.getText());
         pst.setString(6,substr1.getText());
         pst.setDouble(7,USD);
         pst.setString(8,"0");
         pst.setString(9,rolls);
         
       SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(jDateChooser1.getDate());
         pst.setString(10, addDate);
         pst.setString(11,buss.getText());
         pst.setString(12,libele.getText());
         pst.setString(13,code2.getText());
          pst.setString(14,code2.getText());
           pst.setString(16,"0");
            pst.setDouble(15,CDF);
            pst.setString(17,DEVICEEss);
            pst.setString(18,budget.getText());
           pst.setString(19,rolls_batch);
           pst.setString(20,ORGANIZATION);
          
         
          pst.executeUpdate();
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
      
        try {
         pst = con.prepareStatement(queries);
    pst.setString(1, comptemere2.getText());
        pst.setString(2,compte2.getText());
         pst.setString(3,code_m2.getText());
         pst.setString(4,code2.getText());
         pst.setString(5,classe2.getText());
         pst.setString(6,substr2.getText());
         pst.setDouble(8,USD);
         pst.setString(7,"0");
         pst.setString(9,rolls);
         
       SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(jDateChooser1.getDate());
         pst.setString(10, addDate);
         pst.setString(11,buss.getText());
         pst.setString(12,libele.getText());
         pst.setString(13,code2.getText());
          pst.setString(14,code1.getText());
           pst.setString(15,"0");
            pst.setDouble(16,CDF);
            pst.setString(17,DEVICEEss);
            pst.setString(18,budget.getText());
           pst.setString(19,rolls_batch);
           pst.setString(20,ORGANIZATION);
          
         
          pst.executeUpdate();
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
      
        JOptionPane.showMessageDialog(null,"Transaction Done");}
    }       
            
       }
      
    
       
     
       minirefrsh();

   
 
 }
 
 

           
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        journals = new javax.swing.JTextField();
        jSeparator8 = new javax.swing.JSeparator();
        jSeparator9 = new javax.swing.JSeparator();
        jSeparator10 = new javax.swing.JSeparator();
        jSeparator12 = new javax.swing.JSeparator();
        jSeparator13 = new javax.swing.JSeparator();
        jSeparator15 = new javax.swing.JSeparator();
        jLabel15 = new javax.swing.JLabel();
        currency = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        save = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jSeparator18 = new javax.swing.JSeparator();
        jSeparator19 = new javax.swing.JSeparator();
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jSeparator11 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jSeparator20 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        encaissement = new javax.swing.JCheckBox();
        jPanel7 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jSeparator6 = new javax.swing.JSeparator();
        jPanel13 = new javax.swing.JPanel();
        usd = new javax.swing.JTextField();
        usd_sold = new javax.swing.JTextField();
        jSeparator7 = new javax.swing.JSeparator();
        jSeparator14 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jSeparator17 = new javax.swing.JSeparator();
        jPanel1 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        amount = new javax.swing.JTextField();
        jPanel18 = new javax.swing.JPanel();
        jPanel20 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel19 = new javax.swing.JPanel();
        textAreaScroll3 = new textarea.TextAreaScroll();

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED, new java.awt.Color(204, 204, 204), new java.awt.Color(102, 102, 102)));

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jSeparator4.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jSeparator5.setOrientation(javax.swing.SwingConstants.VERTICAL);

        journals.setEditable(false);
        journals.setBackground(new java.awt.Color(240, 240, 241));
        journals.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        journals.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        journals.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                journalsActionPerformed(evt);
            }
        });
        journals.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                journalsKeyPressed(evt);
            }
        });

        jDateChooser1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jDateChooser1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jDateChooser1.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                jDateChooser1ComponentAdded(evt);
            }
        });
        jDateChooser1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jDateChooser1MouseClicked(evt);
            }
        });
        jDateChooser1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jDateChooser1ActionPerformed(evt);
            }
        });

        jrn.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jrn.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jrn.setText("JRN-");
        jrn.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jrn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrnActionPerformed(evt);
            }
        });
        jrn.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jrnKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jrnKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jrnKeyTyped(evt);
            }
        });

        jSeparator8.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jSeparator9.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jSeparator10.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jSeparator12.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jSeparator15.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_Refresh_16px.png"))); // NOI18N
        jLabel15.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel15.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel15MouseClicked(evt);
            }
        });

        currency.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        currency.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        currency.setIcon(new javax.swing.ImageIcon(getClass().getResource("/journals/icons8_Price_Comparison_30px.png"))); // NOI18N
        currency.setText("Currency");
        currency.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        currency.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        currency.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                currencyMouseClicked(evt);
            }
        });

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/journals/icons8-sold-30.png"))); // NOI18N
        jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/journals/icons8-search-24.png"))); // NOI18N
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/journals/icons8_Microsoft_Excel_30px.png"))); // NOI18N
        jLabel7.setText("Import");
        jLabel7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel7.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
        });

        save.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        save.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        save.setIcon(new javax.swing.ImageIcon(getClass().getResource("/journals/icons8_Microsoft_Excel_30px.png"))); // NOI18N
        save.setText("Save");
        save.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        save.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        save.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                saveMouseClicked(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/journals/icons8_Microsoft_Excel_30px.png"))); // NOI18N
        jLabel9.setText("Export");
        jLabel9.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel9.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel9MouseClicked(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/journals/icons8-cash-report-30.png"))); // NOI18N
        jLabel10.setText("Receipt");
        jLabel10.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel10.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel10MouseClicked(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/journals/icons8_Print_30px.png"))); // NOI18N
        jLabel11.setText("Print");
        jLabel11.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel11.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel11MouseClicked(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/journals/icons8-profit-report-20.png"))); // NOI18N
        jLabel13.setText("Advance Salary");
        jLabel13.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel13.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        jLabel13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel13MouseClicked(evt);
            }
        });

        jSeparator19.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/journals/icons8_Delete_Table_24px.png"))); // NOI18N
        jLabel12.setText("Delete");
        jLabel12.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel12.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel12MouseClicked(evt);
            }
        });

        check_select_Table.setBackground(new java.awt.Color(255, 255, 255));
        check_select_Table.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        check_select_Table.setForeground(new java.awt.Color(255, 255, 255));
        check_select_Table.setText("No");

        eb.setBackground(new java.awt.Color(255, 255, 255));
        eb.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        eb.setForeground(new java.awt.Color(255, 255, 255));
        eb.setText("No");

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_Bill_16px.png"))); // NOI18N
        jLabel14.setText("Etat de besoin/Req");
        jLabel14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel14MouseClicked(evt);
            }
        });

        principal.setBackground(new java.awt.Color(255, 255, 255));
        principal.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        principal.setForeground(new java.awt.Color(255, 255, 255));
        principal.setText("No");

        jSeparator11.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/journals/icons8-purchase-order-30.png"))); // NOI18N
        jLabel6.setText("Pieces");
        jLabel6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel6.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_Shutdown_16px.png"))); // NOI18N
        jLabel16.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabel16.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel16.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel16MouseClicked(evt);
            }
        });

        jSeparator20.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/journals/icons8-update-24.png"))); // NOI18N
        jLabel1.setText("Update");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        encaissement.setText("Encaissement");
        encaissement.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204), 3));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(currency, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                    .addComponent(jSeparator13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(save, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jSeparator18)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jrn, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(encaissement)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(journals, javax.swing.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(check_select_Table, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(eb, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(principal, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jSeparator2)
            .addComponent(jSeparator3)
            .addComponent(jSeparator4)
            .addComponent(jSeparator8, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jSeparator9, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jSeparator10, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jSeparator15, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(currency, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(save, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jSeparator19)
            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(journals, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(encaissement, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jrn))
                .addGap(11, 11, 11))
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jSeparator20)
            .addComponent(jSeparator5)
            .addComponent(jSeparator12, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jSeparator11)
            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel6Layout.createSequentialGroup()
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(3, 3, 3)
                            .addComponent(jSeparator13, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel6Layout.createSequentialGroup()
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jSeparator18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel13)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(check_select_Table)
                        .addComponent(eb)
                        .addComponent(principal))))
        );

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED, new java.awt.Color(204, 204, 204), new java.awt.Color(204, 204, 204)));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Sold project", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jTextField2.setEditable(false);
        jTextField2.setBackground(new java.awt.Color(0, 0, 255));
        jTextField2.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jTextField2.setForeground(new java.awt.Color(255, 255, 255));
        jTextField2.setText("0.00");
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jTextField3.setEditable(false);
        jTextField3.setBackground(new java.awt.Color(0, 0, 255));
        jTextField3.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jTextField3.setForeground(new java.awt.Color(255, 255, 255));
        jTextField3.setText("0.00");

        jTextField1.setBackground(new java.awt.Color(240, 240, 241));
        jTextField1.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jTextField1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jTextField6.setEditable(false);
        jTextField6.setBackground(new java.awt.Color(0, 153, 51));
        jTextField6.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jTextField6.setForeground(new java.awt.Color(255, 255, 255));
        jTextField6.setText("0.00");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField2)
                    .addComponent(jTextField3))
                .addGap(0, 0, 0)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField1)
                    .addComponent(jTextField6, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));
        jPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Sold", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        usd.setEditable(false);
        usd.setBackground(new java.awt.Color(240, 240, 241));
        usd.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        usd.setText("0.00");
        usd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usdActionPerformed(evt);
            }
        });

        devise.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        devise.setText("USD");

        usd_sold.setEditable(false);
        usd_sold.setBackground(new java.awt.Color(240, 240, 241));
        usd_sold.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        usd_sold.setText("0.00");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addComponent(devise)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(usd, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(usd_sold)
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(usd, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(devise)
                    .addComponent(usd_sold, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6))
        );

        buss.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        buss.setText("Select One Business");
        buss.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bussMouseClicked(evt);
            }
        });

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable3.setRowHeight(30);
        jTable3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable3MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable3);

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Batchs", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        batchs.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        batchs.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Batches" }));
        batchs.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        batchs.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                batchsPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        batchs1.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        batchs1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Day", "Month", "Year" }));
        batchs1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        batchs1.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                batchs1PopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(batchs, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(batchs1, 0, 135, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(batchs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(batchs1))
        );

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Taux Jr.", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jTextField4.setEditable(false);
        jTextField4.setBackground(new java.awt.Color(240, 240, 241));
        jTextField4.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jTextField4.setText("0.00");
        jTextField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField4ActionPerformed(evt);
            }
        });

        jTextField5.setEditable(false);
        jTextField5.setBackground(new java.awt.Color(240, 240, 241));
        jTextField5.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jTextField5.setText("0.00");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("Achat");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setText("Vente");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField5, javax.swing.GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE)
                    .addComponent(jTextField4)))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(0, 0, 0)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator7)
            .addComponent(jSeparator14)
            .addComponent(jSeparator6, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jSeparator17)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buss, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(buss, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED, new java.awt.Color(204, 204, 204), new java.awt.Color(204, 204, 204)));

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Action 2", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11))); // NOI18N

        compte1.setEditable(false);
        compte1.setBackground(new java.awt.Color(240, 240, 241));
        compte1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        compte1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        compte1.setToolTipText("");
        compte1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                compte1ActionPerformed(evt);
            }
        });

        code1.setEditable(false);
        code1.setBackground(new java.awt.Color(240, 240, 241));
        code1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        code1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        code1.setToolTipText("");

        code_m1.setEditable(false);
        code_m1.setBackground(new java.awt.Color(240, 240, 241));
        code_m1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        code_m1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        code_m1.setToolTipText("");

        comptemere1.setEditable(false);
        comptemere1.setBackground(new java.awt.Color(240, 240, 241));
        comptemere1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        comptemere1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        comptemere1.setToolTipText("");

        classe1.setEditable(false);
        classe1.setBackground(new java.awt.Color(240, 240, 241));
        classe1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        classe1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        classe1.setToolTipText("");

        substr1.setEditable(false);
        substr1.setBackground(new java.awt.Color(240, 240, 241));
        substr1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        substr1.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(comptemere1)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(compte1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel9Layout.createSequentialGroup()
                        .addComponent(code1, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(code_m1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, 0)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(classe1)
                    .addComponent(substr1, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(compte1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(substr1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(code1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(code_m1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(classe1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addComponent(comptemere1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Action 2", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11))); // NOI18N

        compte2.setEditable(false);
        compte2.setBackground(new java.awt.Color(240, 240, 241));
        compte2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        compte2.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        code2.setEditable(false);
        code2.setBackground(new java.awt.Color(240, 240, 241));
        code2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        code2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        code2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                code2ActionPerformed(evt);
            }
        });

        code_m2.setEditable(false);
        code_m2.setBackground(new java.awt.Color(240, 240, 241));
        code_m2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        code_m2.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        comptemere2.setEditable(false);
        comptemere2.setBackground(new java.awt.Color(240, 240, 241));
        comptemere2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        comptemere2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        comptemere2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comptemere2ActionPerformed(evt);
            }
        });

        classe2.setEditable(false);
        classe2.setBackground(new java.awt.Color(240, 240, 241));
        classe2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        classe2.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        substr2.setEditable(false);
        substr2.setBackground(new java.awt.Color(240, 240, 241));
        substr2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        substr2.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(comptemere2)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(compte2, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel10Layout.createSequentialGroup()
                        .addComponent(code2, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(code_m2, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, 0)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(classe2)
                    .addComponent(substr2, javax.swing.GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE)))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(compte2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(substr2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(code2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(code_m2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(classe2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addComponent(comptemere2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel17.setBackground(new java.awt.Color(255, 255, 255));
        jPanel17.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)), "Action 1", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11))); // NOI18N

        jComboBox2.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "...." }));
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

        jComboBox3.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Credit", "Debit" }));
        jComboBox3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jComboBox3.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                jComboBox3PopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        amount.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        amount.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        amount.setText("0.00");
        amount.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        amount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                amountKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                amountKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(amount)
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jComboBox2)
                    .addComponent(jComboBox3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(amount))
        );

        jPanel18.setBackground(new java.awt.Color(255, 255, 255));
        jPanel18.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)), "Ohada", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11))); // NOI18N

        ohada.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        ohada.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        ohada.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        ohada.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                ohadaKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ohada, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ohada, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel20.setBackground(new java.awt.Color(255, 255, 255));
        jPanel20.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Action 2", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11))); // NOI18N

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
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel19.setBackground(new java.awt.Color(255, 255, 255));
        jPanel19.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)), "Lignes budgetairs", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11))); // NOI18N

        budget.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        budget.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        budget.setText("-");
        budget.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        budget.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                budgetKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(budget, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(budget, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        textAreaScroll3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        textAreaScroll3.setLabelText("Libelle");

        libele.setColumns(20);
        libele.setRows(5);
        textAreaScroll3.setViewportView(libele);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(textAreaScroll3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel20, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(textAreaScroll3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        boody.setBackground(new java.awt.Color(255, 255, 255));
        boody.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED, new java.awt.Color(204, 204, 204), new java.awt.Color(204, 204, 204)));
        boody.setLayout(new javax.swing.BoxLayout(boody, javax.swing.BoxLayout.LINE_AXIS));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(boody, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(boody, javax.swing.GroupLayout.DEFAULT_SIZE, 284, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void journalsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_journalsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_journalsActionPerformed

    private void journalsKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_journalsKeyPressed
        // TODO add your handling jrn here:
    }//GEN-LAST:event_journalsKeyPressed

    private void jDateChooser1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jDateChooser1ActionPerformed
        // TODO add your handling jrn here:
    }//GEN-LAST:event_jDateChooser1ActionPerformed

    private void jrnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jrnActionPerformed

    private void jrnKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jrnKeyPressed
        // TODO add your handling jrn here:
    }//GEN-LAST:event_jrnKeyPressed

    private void jrnKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jrnKeyReleased
        try{
            con=JavaDbConnect.dbConnect();
            if(jrn.getText().equals("JRN-")||jrn.getText().equals("JRN")||jrn.getText().equals("")){
                jrn.setText("JRN-");
            }else{
                call_journal();
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
    }//GEN-LAST:event_jrnKeyReleased

    private void jrnKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jrnKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jrnKeyTyped

    private void jLabel15MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel15MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel15MouseClicked

    private void currencyMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_currencyMouseClicked

        try{
            con=JavaDbConnect.dbConnect();
            boody.removeAll();
            boody.add(new currency());
            boody.repaint();
            boody.revalidate();

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
        }     // TODO add your handling code here:
    }//GEN-LAST:event_currencyMouseClicked

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked

        try{
            con=JavaDbConnect.dbConnect();
            boody.removeAll();
            boody.add(new sold1());
            boody.repaint();
            boody.revalidate();

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
        } // TODO add your handling code here:
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        JOptionPane.showMessageDialog(null,"Pas disponible ","Avertissement",JOptionPane.WARNING_MESSAGE);
        // try {
            //             Scanner Scanner = new Scanner (System.in);
            //  File file =new File ("button.wav");
            //  AudioInputStream audioStream = null;
            //     try {
                //         audioStream = AudioSystem.getAudioInputStream(file);
                //     } catch (UnsupportedAudioFileException ex) {
                //         Logger.getLogger(journal.class.getName()).log(Level.SEVERE, null, ex);
                //     } catch (IOException ex) {
                //         Logger.getLogger(journal.class.getName()).log(Level.SEVERE, null, ex);
                //     }
            //  Clip clip = AudioSystem.getClip();
            //           clip.open(audioStream);
            //            clip.start();
            //  //String response=scanner.next();
            //       } catch (LineUnavailableException ex) {
            //           Logger.getLogger(journal.class.getName()).log(Level.SEVERE, null, ex);
            //       }
        //search_caisse m = new search_caisse();
        //m.show();
        //m. setDefaultCloseOperation(DISPOSE_ON_CLOSE);         // TODO add your handling code here:
    }//GEN-LAST:event_jLabel3MouseClicked

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
        //JOptionPane.showMessageDialog(null,"Pas disponible ","Avertissement",JOptionPane.WARNING_MESSAGE);
        imports();
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel7MouseClicked

    private void saveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_saveMouseClicked

        try{
            con=JavaDbConnect.dbConnect();
            int res = JOptionPane.showOptionDialog(null, "Que voulez-vous faire?","EMS-L",
         JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
         new Object[] { "Checker", "Enregistrer" }, JOptionPane.YES_OPTION);
      if (res == JOptionPane.YES_OPTION){
        checkdata();
      } else if (res == JOptionPane.NO_OPTION) {
      Save_CAISSE_usd();}
            

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
        }    //JOptionPane.showMessageDialog(null,"Pas disponible ","Avertissement",JOptionPane.WARNING_MESSAGE);         // TODO add your handling code here:
    }//GEN-LAST:event_saveMouseClicked

    private void jLabel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseClicked
export();
     //   JOptionPane.showMessageDialog(null,"Pas disponible ","Avertissement",JOptionPane.WARNING_MESSAGE);         // TODO add your handling code here:
    }//GEN-LAST:event_jLabel9MouseClicked

    private void jLabel10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel10MouseClicked
        if(check_select_Table.getText().equals("Yes")){
            
            try{
                con=JavaDbConnect.dbConnect();
                
                if(buss.getText().equals("Select One Business")){
                    JOptionPane.showMessageDialog(null,"Wrong Data","Error",JOptionPane.ERROR_MESSAGE);
                }else{
                
                    bon_caisse1 m =new  bon_caisse1 ();
                //    m.busss.setText();
 
                    int row= home_Table.jTable2.getSelectedRow();
                    String Table_click = (home_Table.jTable2.getModel().getValueAt(row,4). toString());
                    String entrees = (home_Table.jTable2.getModel().getValueAt(row,5). toString());
                    String sorties = (home_Table.jTable2.getModel().getValueAt(row,6). toString());
                    String entree=entrees.replace(",", "");
                    String sortie=sorties.replace(",", "");
                    
                    if(entree.equals("0.00")){
                        if(substr1.getText().equals("57")){
                    bon_caisse1.designation.setText("BON D ENTREE CAISSE");
                        }else{
                   bon_caisse1.designation.setText("BON D ENTREE BANQUE");
                        }

                        double DoubleValue = Double.parseDouble(sortie);
                        int IntValue = (int) DoubleValue;
                        Long a = Long.parseLong(""+ IntValue);
                       bon_caisse1.displays.setText(""+ FrenchNumberToWords.convert(a));
                        // montant.setText(""+ IntValue);
                     bon_caisse1.montant.setText(""+IntValue);

                        String repalceString= bon_caisse1.designation.getText().replace("'","''");
                        try{

                            String sql="SELECT DESCR as 'Motifs',PT as 'Montant',NAME as 'En faveur de',NUM_FICHE AS Num,Date FROM recu where TRANSACTION='"+repalceString+"' and PROJET='"+buss.getText()+"'";
                            pst = con.prepareStatement(sql);
                            rs= pst.executeQuery();
                           bon_caisse1.jTable1.setModel(DbUtils.resultSetToTableModel(rs));

                            TableColumn col0=bon_caisse1.jTable1.getColumnModel().getColumn(0);
                            TableColumn col1=bon_caisse1.jTable1.getColumnModel().getColumn(1);
                            TableColumn col2=bon_caisse1.jTable1.getColumnModel().getColumn(2);
                            TableColumn col3=bon_caisse1.jTable1.getColumnModel().getColumn(3);
                            TableColumn col4=bon_caisse1.jTable1.getColumnModel().getColumn(4);

                            col0.setPreferredWidth(250);
                            col1.setPreferredWidth(50);
                            col2.setPreferredWidth(150);
                            col3.setPreferredWidth(50);
                            col4.setPreferredWidth(30);

                        }catch(SQLException ex ){
                            JOptionPane.showMessageDialog(null, ex);
                        }

                    }else{
                        if(substr1.getText().equals("57")){
                        bon_caisse1.designation.setText("BON DE SORTIE CAISSE");
                        }else{
                        bon_caisse1.designation.setText("BON DE SORTIE BANQUE");
                        }

                        double DoubleValue = Double.parseDouble(entree);
                        int IntValue = (int) DoubleValue;
                        Long a = Long.parseLong(""+ IntValue);
                        bon_caisse1.displays.setText(""+ FrenchNumberToWords.convert(a));
                        // montant.setText(""+ IntValue);
                        bon_caisse1.montant.setText(""+IntValue);

                        String repalceString= bon_caisse1.designation.getText().replace("'","''");
                        try{

                            String sql="SELECT DESCR as 'Motifs',PT as 'Montant',NAME as 'En faveur de',NUM_FICHE AS Num,Date FROM recu where TRANSACTION='"+repalceString+"' and PROJET='"+buss.getText()+"'";
                            pst = con.prepareStatement(sql);
                            rs= pst.executeQuery();
                        bon_caisse1.jTable1.setModel(DbUtils.resultSetToTableModel(rs));

                            TableColumn col0=bon_caisse1.jTable1.getColumnModel().getColumn(0);
                            TableColumn col1=bon_caisse1.jTable1.getColumnModel().getColumn(1);
                            TableColumn col2=bon_caisse1.jTable1.getColumnModel().getColumn(2);
                            TableColumn col3=bon_caisse1.jTable1.getColumnModel().getColumn(3);
                            TableColumn col4=bon_caisse1.jTable1.getColumnModel().getColumn(4);

                            col0.setPreferredWidth(250);
                            col1.setPreferredWidth(50);
                            col2.setPreferredWidth(150);
                            col3.setPreferredWidth(50);
                            col4.setPreferredWidth(30);

                        }catch(SQLException ex ){
                            JOptionPane.showMessageDialog(null, ex);
                        }
                    }
                  bon_caisse1.motif.setText(Table_click);

                    m.show();
                    m. setDefaultCloseOperation(DISPOSE_ON_CLOSE);
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
        }else{
            JOptionPane.showMessageDialog(null,"Select Jounal Table","Error",JOptionPane.ERROR_MESSAGE);
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel10MouseClicked

    private void jLabel11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseClicked

        try{
            con=JavaDbConnect.dbConnect();
            caisse_dates m= new  caisse_dates();
            m.show();
            m. setDefaultCloseOperation(DISPOSE_ON_CLOSE);

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
        }             // TODO add your handling code here:
    }//GEN-LAST:event_jLabel11MouseClicked

    private void jLabel13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MouseClicked

        try{
            con=JavaDbConnect.dbConnect();
            avance_sur_salaire m = new avance_sur_salaire();
            m.show();
            m. setDefaultCloseOperation(DISPOSE_ON_CLOSE);

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
    }//GEN-LAST:event_jLabel13MouseClicked

    private void jLabel12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel12MouseClicked
        try{
            con=JavaDbConnect.dbConnect();
            try{
                String sql="select ACESS from user_write where NAME='"+Home_page.home.user.getText()+"' and CHAMP='Delete_Wording'";

                pst=con.prepareStatement(sql);
                rs=pst.executeQuery();
                if(rs.next()){
                    delete_transction();
                    boody.removeAll();
                    boody.add(new home_Table());
                    boody.repaint();
                    boody.revalidate();
                    callcaisebuss();

                    try{

                        NumberFormat nf = new DecimalFormat("#,###.##");
                        String sqls="select SUM(DEBIT),SUM(CREDIT),(SUM(DEBIT)-SUM(CREDIT)) from BUDGET_TRANS  WHERE PROJET='"+buss.getText()+"' and BATCH='"+batchs.getSelectedItem()+"'";// AND SUBSTR=57 AND LB='' and journal='OK'";

                        pst=con.prepareStatement(sqls);
                        rs=pst.executeQuery();
                        while(rs.next()){
                            double debit=rs.getDouble("SUM(DEBIT)");
                            double credit=rs.getDouble("SUM(CREDIT)");
                            double solde=rs.getDouble("(SUM(DEBIT)-SUM(CREDIT))");

                            String fn = nf.format(debit);
                            String fnn = nf.format(credit);
                            String fnnn = nf.format(solde);

                            jTextField2.setText(fn);
                            jTextField3.setText(fnn);
                            jTextField6.setText(fnnn);

                        }
                    }
                    catch(Exception ex){
                        JOptionPane.showMessageDialog(null, ex);}

                    //  }
            }else{
                JOptionPane.showMessageDialog(null,"Acces limité","Avertissement",JOptionPane.WARNING_MESSAGE);
            }
        }catch(Exception ex){

            JOptionPane.showMessageDialog(null, ex); }

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
    }//GEN-LAST:event_jLabel12MouseClicked

    private void jLabel14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel14MouseClicked

        try{
            con=JavaDbConnect.dbConnect();
            boody.removeAll();
            boody.add(new etat_de_besoin());
            boody.repaint();
            boody.revalidate();
            journals.setText("Etat de Besoin");

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

        //eb.setText("Yes");// TODO add your handling code here:
    }//GEN-LAST:event_jLabel14MouseClicked

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked

        try{
            con=JavaDbConnect.dbConnect();
            int row= home_Table.jTable2.getSelectedRow();
            String Table_clicks = (home_Table.jTable2.getModel().getValueAt(row,7). toString());
            if(Table_clicks.equals("No")){

            }else{
                pdf_reader    m= new pdf_reader ();
                m.show();
                m. setDefaultCloseOperation(DISPOSE_ON_CLOSE);}

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
        }              // TODO add your handling code here:
    }//GEN-LAST:event_jLabel6MouseClicked

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling jrn here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void usdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usdActionPerformed
        // TODO add your handling jrn here:
    }//GEN-LAST:event_usdActionPerformed

    private void bussMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bussMouseClicked
        try{
            con=JavaDbConnect.dbConnect();
            home_buss_select m= new  home_buss_select  ();
            m.show();
            m. setDefaultCloseOperation(DISPOSE_ON_CLOSE);
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
        }     // TODO add your handling code here:
    }//GEN-LAST:event_bussMouseClicked

    private void jTable3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable3MouseClicked

        try{
            con=JavaDbConnect.dbConnect();
            clic_attCall_IN_LIST();
           
            callcaisebuss();
          

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

        //        select_jTable();        // TODO add your handling code here:
    }//GEN-LAST:event_jTable3MouseClicked

    private void batchsPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_batchsPopupMenuWillBecomeInvisible

        try{
            con=JavaDbConnect.dbConnect();
            try{
                NumberFormat nf = new DecimalFormat("#,###.##");
                String sqls="select SUM(DEBIT),SUM(CREDIT),(SUM(DEBIT)-SUM(CREDIT)) from BUDGET_TRANS  WHERE PROJET='"+buss.getText()+"' and BATCH='"+batchs.getSelectedItem()+"'";// AND SUBSTR=57 AND LB='' and journal='OK'";

                pst=con.prepareStatement(sqls);
                rs=pst.executeQuery();
                while(rs.next()){
                    double debit=rs.getDouble("SUM(DEBIT)");
                    double credit=rs.getDouble("SUM(CREDIT)");
                    double solde=rs.getDouble("(SUM(DEBIT)-SUM(CREDIT))");

                    String fn = nf.format(debit);
                    String fnn = nf.format(credit);
                    String fnnn = nf.format(solde);

                    jTextField2.setText(fn);
                    jTextField3.setText(fnn);
                    jTextField6.setText(fnnn);

                }
            }
            catch(Exception ex){
                JOptionPane.showMessageDialog(null, ex);}

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
        }    // TODO add your handling code here:
    }//GEN-LAST:event_batchsPopupMenuWillBecomeInvisible

    private void batchs1PopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_batchs1PopupMenuWillBecomeInvisible

        try{
            con=JavaDbConnect.dbConnect();
            if(code1.getText().equals("")){

            }else{
                boody.removeAll();
                boody.add(new home_Table());
                boody.repaint();
                boody.revalidate();
                callcaisebuss();
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
        }        // TODO add your handling code here:
    }//GEN-LAST:event_batchs1PopupMenuWillBecomeInvisible

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField4ActionPerformed

    private void compte1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_compte1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_compte1ActionPerformed

    private void code2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_code2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_code2ActionPerformed

    private void comptemere2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comptemere2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comptemere2ActionPerformed

    private void jComboBox2PopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_jComboBox2PopupMenuWillBecomeInvisible

        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox2PopupMenuWillBecomeInvisible

    private void jComboBox3PopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_jComboBox3PopupMenuWillBecomeInvisible
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox3PopupMenuWillBecomeInvisible

    private void ohadaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ohadaKeyReleased
        try{
            con=JavaDbConnect.dbConnect();
            compte();

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

        // TODO add your handling jrn here:
    }//GEN-LAST:event_ohadaKeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try{
            con=JavaDbConnect.dbConnect();
            if(batchs.getSelectedItem().equals("Batches")||batchs.getSelectedItem().equals("")||budget.getText().equals("")){
                JOptionPane.showMessageDialog(null,"Wrong Data","Error",JOptionPane.ERROR_MESSAGE);
            }else{
                if(journals.getText().equals("Etat de Besoin")){
                    save();
                }else{
if(encaissement.isSelected()){
encaissement();
}else{
savecaisse();
}
                    
                    boody.removeAll();
                    boody.add(new home_Table());
                    boody.repaint();
                    boody.revalidate();
                    check_select_Table.setText("No");
                }
                callcaisebuss();

                try{

                    NumberFormat nf = new DecimalFormat("#,###.##");
                    String sqls="select SUM(DEBIT),SUM(CREDIT),(SUM(DEBIT)-SUM(CREDIT)) from BUDGET_TRANS  WHERE PROJET='"+buss.getText()+"' and BATCH='"+batchs.getSelectedItem()+"'";// AND SUBSTR=57 AND LB='' and journal='OK'";

                    pst=con.prepareStatement(sqls);
                    rs=pst.executeQuery();
                    while(rs.next()){
                        double debit=rs.getDouble("SUM(DEBIT)");
                        double credit=rs.getDouble("SUM(CREDIT)");
                        double solde=rs.getDouble("(SUM(DEBIT)-SUM(CREDIT))");

                        String fn = nf.format(debit);
                        String fnn = nf.format(credit);
                        String fnnn = nf.format(solde);

                        jTextField2.setText(fn);
                        jTextField3.setText(fnn);
                        jTextField6.setText(fnnn);

                    }
                }
                catch(Exception ex){
                    JOptionPane.showMessageDialog(null, ex);}
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

        // TODO add your handling jrn here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        check_select_Table.setText("No");
        jTextField1.setText("");
        refresh();// TODO add your handling jrn here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void budgetKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_budgetKeyReleased

        try{
            con=JavaDbConnect.dbConnect();
            home_compte1 m = new home_compte1 ();
            m.show();
            m. setDefaultCloseOperation(DISPOSE_ON_CLOSE);

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
        }  // TODO add your handling code here:
    }//GEN-LAST:event_budgetKeyReleased

    private void jLabel16MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel16MouseClicked
jc.setText("Jounaux Comptable");
        this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel16MouseClicked

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked

        try{
              if(jrn.getText().equals("JRN-57")||journal1.jrn.getText().equals("JRN-52")){
            con=JavaDbConnect.dbConnect();
            try{
                String sql="select ACESS from user_write where NAME='"+Home_page.home.user.getText()+"' and CHAMP='Delete_Wording'";

                pst=con.prepareStatement(sql);
                rs=pst.executeQuery();
                if(rs.next()){
                    update_transction();
                    boody.removeAll();
                    boody.add(new home_Table());
                    boody.repaint();
                    boody.revalidate();
                    callcaisebuss();

                    try{

                        NumberFormat nf = new DecimalFormat("#,###.##");
                        String sqls="select SUM(DEBIT),SUM(CREDIT),(SUM(DEBIT)-SUM(CREDIT)) from BUDGET_TRANS  WHERE PROJET='"+buss.getText()+"' and BATCH='"+batchs.getSelectedItem()+"'";// AND SUBSTR=57 AND LB='' and journal='OK'";

                        pst=con.prepareStatement(sqls);
                        rs=pst.executeQuery();
                        while(rs.next()){
                            double debit=rs.getDouble("SUM(DEBIT)");
                            double credit=rs.getDouble("SUM(CREDIT)");
                            double solde=rs.getDouble("(SUM(DEBIT)-SUM(CREDIT))");

                            String fn = nf.format(debit);
                            String fnn = nf.format(credit);
                            String fnnn = nf.format(solde);

                            jTextField2.setText(fn);
                            jTextField3.setText(fnn);
                            jTextField6.setText(fnnn);

                        }
                    }
                    catch(Exception ex){
                        JOptionPane.showMessageDialog(null, ex);}

                    //  }
            }else{
                JOptionPane.showMessageDialog(null,"Acces limité","Avertissement",JOptionPane.WARNING_MESSAGE);
            }
        }catch(Exception ex){

            JOptionPane.showMessageDialog(null, ex); }
        }else{
        JOptionPane.showMessageDialog(null,"Seulement les Operations de tresorerie","Avertissement",JOptionPane.WARNING_MESSAGE);
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
        }        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel1MouseClicked

    private void amountKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_amountKeyPressed
if (evt.getKeyCode()==KeyEvent.VK_ENTER){
            try{

                NumberFormat nf = new DecimalFormat("#,###.00");
                String Table_click1  = amount.getText();
                String replaceString=Table_click1.replace(",", "");
                Double c= Double.parseDouble(replaceString);
                String fn = nf.format(c);
                //PR=c;
                amount.setText(fn);
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null, ex); }
        }          // TODO add your handling code here:
    }//GEN-LAST:event_amountKeyPressed

    private void amountKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_amountKeyTyped
 char c=evt.getKeyChar();
        if(!(Character.isDigit(c) || (c==KeyEvent.VK_BACK_SPACE)||c==KeyEvent.VK_DELETE ||c==KeyEvent.VK_PERIOD )){
            evt.consume();

        }         // TODO add your handling code here:
    }//GEN-LAST:event_amountKeyTyped

    private void jDateChooser1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jDateChooser1MouseClicked

    
        // TODO add your handling code here:
    }//GEN-LAST:event_jDateChooser1MouseClicked

    private void jDateChooser1ComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_jDateChooser1ComponentAdded



      // TODO add your handling code here:
    }//GEN-LAST:event_jDateChooser1ComponentAdded


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField amount;
    public static final javax.swing.JComboBox<String> batchs = new javax.swing.JComboBox<>();
    public static final javax.swing.JComboBox<String> batchs1 = new javax.swing.JComboBox<>();
    public static final javax.swing.JPanel boody = new javax.swing.JPanel();
    public static final javax.swing.JTextField budget = new javax.swing.JTextField();
    public static final javax.swing.JLabel buss = new javax.swing.JLabel();
    public static final javax.swing.JLabel check_select_Table = new javax.swing.JLabel();
    public static final javax.swing.JTextField classe1 = new javax.swing.JTextField();
    public static final javax.swing.JTextField classe2 = new javax.swing.JTextField();
    public static final javax.swing.JTextField code1 = new javax.swing.JTextField();
    public static final javax.swing.JTextField code2 = new javax.swing.JTextField();
    public static final javax.swing.JTextField code_m1 = new javax.swing.JTextField();
    public static final javax.swing.JTextField code_m2 = new javax.swing.JTextField();
    public static final javax.swing.JTextField compte1 = new javax.swing.JTextField();
    public static final javax.swing.JTextField compte2 = new javax.swing.JTextField();
    public static final javax.swing.JTextField comptemere1 = new javax.swing.JTextField();
    public static final javax.swing.JTextField comptemere2 = new javax.swing.JTextField();
    private javax.swing.JLabel currency;
    public static final javax.swing.JLabel devise = new javax.swing.JLabel();
    public static final javax.swing.JLabel eb = new javax.swing.JLabel();
    private javax.swing.JCheckBox encaissement;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    public static final javax.swing.JComboBox<String> jComboBox2 = new javax.swing.JComboBox<>();
    public static final javax.swing.JComboBox<String> jComboBox3 = new javax.swing.JComboBox<>();
    public static final com.alee.extended.date.WebDateField jDateChooser1 = new com.alee.extended.date.WebDateField();
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator13;
    private javax.swing.JSeparator jSeparator14;
    private javax.swing.JSeparator jSeparator15;
    private javax.swing.JSeparator jSeparator17;
    private javax.swing.JSeparator jSeparator18;
    private javax.swing.JSeparator jSeparator19;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator20;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JTable jTable3;
    public static final javax.swing.JTextField jTextField1 = new javax.swing.JTextField();
    public static final javax.swing.JTextField jTextField2 = new javax.swing.JTextField();
    public static final javax.swing.JTextField jTextField3 = new javax.swing.JTextField();
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    public static final javax.swing.JTextField jTextField6 = new javax.swing.JTextField();
    private javax.swing.JTextField journals;
    public static final javax.swing.JTextField jrn = new javax.swing.JTextField();
    public static final textarea.TextArea libele = new textarea.TextArea();
    public static final javax.swing.JTextField ohada = new javax.swing.JTextField();
    public static final javax.swing.JLabel principal = new javax.swing.JLabel();
    private javax.swing.JLabel save;
    public static final javax.swing.JTextField substr1 = new javax.swing.JTextField();
    public static final javax.swing.JTextField substr2 = new javax.swing.JTextField();
    private textarea.TextAreaScroll textAreaScroll3;
    private javax.swing.JTextField usd;
    private javax.swing.JTextField usd_sold;
    // End of variables declaration//GEN-END:variables
}
