/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intreprisemanagementsystem;

import java.awt.HeadlessException;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
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
 * @author Dosh
 */
public class Pharma_madicament extends javax.swing.JPanel {

  private Connection con;
private Statement st;
PreparedStatement pst=null;
ResultSet rs= null;
String rolls;
 DefaultTableModel mode;
 String ID=null;
  String IDS=null;
    public Pharma_madicament() {
        initComponents();
        con=JavaDbConnect.dbConnect();
        buss();
       call_in_tableS();
    }
 public void roll()
     {
         try{
                 
           
            String sql="SELECT NUM FROM h_p_medicament ORDER BY NUM DESC LIMIT 1";
             pst=con.prepareStatement(sql);
             rs=pst.executeQuery();
             if(rs.next()){
                 String rl=rs.getString("NUM");
                 int ln=rl.length();
                 String stxt=rl.substring(0,9);
                 String snum=rl.substring(9,ln);
                 int n = Integer.parseInt(snum);
                 n++;
                 snum=Integer.toString(n);
                 rolls=stxt+snum;
                 
                 
                  
            
               
             }else{
                 //rolls="FICHE/EB/2018/1";
                 rolls="MEDI-ENG/1001";
             }
                 
             
         }catch(Exception e){
             JOptionPane.showMessageDialog(null, e); 
             
         }
     }
  public void buss(){
    
        
          try{
              
            String sql="SELECT distinct(SERVICE) FROM   h_p_planification";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                //String sum=rs.getString("nom");
                String sums=rs.getString("SERVICE");
                classes.addItem(sums);
            }
             
            
           
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
     try{
              
            String sql="SELECT distinct(SERVICE) FROM   h_p_formepharma";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                //String sum=rs.getString("nom");
                String sums=rs.getString("SERVICE");
                fpharma.addItem(sums);
            }
             
            
           
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
     try{
              
            String sql="SELECT distinct(SERVICE) FROM  h_p_manier";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                //String sum=rs.getString("nom");
                String sums=rs.getString("SERVICE");
                mrc.addItem(sums);
            }
             
            
           
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
     try{
              
            String sql="SELECT distinct(SERVICE) FROM  h_p_emballage";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                //String sum=rs.getString("nom");
                String sums= rs.getString("SERVICE");
                ctn.addItem(sums);
            }
             
            
           
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
    
    
    }
  

    public void save(){
        roll();
               try{
          
           
     
         // java.sql.Connection con = getConnection();
     String sql="INSERT INTO h_p_medicament (NOM,NOMS,CLASSI,CATEGORI,P_ACHAT,P_VENTE,D_UNITE,P_ACHAT_G,P_VENTE_G,G_VENTE,NUM,MARK,MARK_QTY,CONTAINER,CONTAINER_QTY,QTY_UNITE,VAL_UNITE)"+
             "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     pst=con.prepareStatement(sql);
    
         pst.setString(1, nom.getText());
      pst.setString(2, nom.getText()+" "+grame.getText()+" "+unity.getSelectedItem());
      pst.setString(3, classes.getSelectedItem().toString());
      pst.setString(4, fpharma.getSelectedItem().toString());
      pst.setString(5, pad.getText());
      
      pst.setString(6, pvd.getText());
      pst.setString(7, prmoney.getSelectedItem().toString());
      pst.setString(8, pag.getText());
      pst.setString(9, pvg.getText());
      pst.setString(10,pvmomey.getSelectedItem().toString());
      pst.setString(11, rolls);
    
      pst.setString(12, mrc.getSelectedItem().toString());
      pst.setString(13, mark_qty.getText());
      pst.setString(14, ctn.getSelectedItem().toString());
      pst.setString(15, ctn_qty.getText());
      pst.setString(16, grame.getText());
      
      pst.setString(17, unity.getSelectedItem().toString());
 
       //pst.setString(21, cat1.getText());
      
      pst.executeUpdate();
               
              
   JOptionPane.showMessageDialog(null,"Transaction Saved");
      
   //  cat1.setText("");
      
     }catch(Exception ex){JOptionPane.showMessageDialog(null,ex);}  
             }
    
      public void caluculate1(){
    
     Double a= Double.parseDouble(mark_qty.getText());
     Double b= Double.parseDouble(ctn_qty.getText());
     Double aa= Double.parseDouble(pag.getText());
     Double c,d;
     
     c=b/a;
     d=aa/c;
     
     pad.setText(""+d);
    
    }
    
//    public void currency() {
//            String usd="";
//            String cdf="";
//             String sell = "";
//                String buy = "";
//            
//           
//              try{
//             
//            String sql="select  USD,CDF from monais_principale where ID='1'";
//            
//            pst=con.prepareStatement(sql);
//            rs=pst.executeQuery();
//            while(rs.next()){
//                 usd =rs.getString("USD");
//                 cdf =rs.getString("CDF");
//                 
//            }
//             
//            
//           
//            }
//        catch(Exception ex){
//          JOptionPane.showMessageDialog(null, ex);    
//        }
//
//
//            if (usd.equals("Yes"))
//            {
//               
//                if (prmoney.getSelectedItem().equals("USD"))
//                {
//           
//                    prg_$.setText(pag.getText());
//                     prd_$.setText(pad.getText());
//
//
//                }else if (prmoney.getSelectedItem().equals("CDF"))
//                {
//                   try{
//             
//            String sql="select  BUY,SELL from currency where NOM='CDF'";
//            
//            pst=con.prepareStatement(sql);
//            rs=pst.executeQuery();
//            while(rs.next()){
//                 buy =rs.getString("BUY");
//                 sell =rs.getString("SELL");
//                 
//            }
//            }
//        catch(Exception ex){
//          JOptionPane.showMessageDialog(null, ex);    
//        }
//Double a = Double.parseDouble(pag.getText());
//Double b = Double.parseDouble(pad.getText());
//Double d= Double.parseDouble(sell);
//Double c,cc;
//cc=b/d;
//c=a/d;
//  prg_$.setText(""+c);
//  prd_$.setText(""+cc);
//
//               
//                }
//               
//                }else  if (cdf.equals("Yes"))
//                {
//                if (prmoney.getSelectedItem().equals("CDF"))
//                {
//           
//                    prg_$.setText(pag.getText());
//                     prd_$.setText(pad.getText());
//
//
//                }else if (prmoney.getSelectedItem().equals("USD"))
//                {
//                   try{
//             
//            String sql="select  BUY,SELL from currency where NOM='CDF'";
//            
//            pst=con.prepareStatement(sql);
//            rs=pst.executeQuery();
//            while(rs.next()){
//                 buy =rs.getString("BUY");
//                 sell =rs.getString("SELL");
//                 
//            }
//            }
//        catch(Exception ex){
//          JOptionPane.showMessageDialog(null, ex);    
//        }
//Double a = Double.parseDouble(pag.getText());
//Double b = Double.parseDouble(pad.getText());
//Double d= Double.parseDouble(buy);
//Double c,cc;
//cc=b*d;
//c=a*d;
//  prg_$.setText(""+c);
//  prd_$.setText(""+cc);
//
//               
//                }
//                
//                }
//
////GROS VENTE
//
//
//  try{
//             
//            String sql="select  USD,CDF from monais_principale where ID='1'";
//            
//            pst=con.prepareStatement(sql);
//            rs=pst.executeQuery();
//            while(rs.next()){
//                 usd =rs.getString("USD");
//                 cdf =rs.getString("CDF");
//                 
//            }
//             
//            
//           
//            }
//        catch(Exception ex){
//          JOptionPane.showMessageDialog(null, ex);    
//        }
//
//
//            if (usd.equals("Yes"))
//            {
//               
//                if (prmoney.getSelectedItem().equals("USD"))
//                {
//           
//                    pvg_$.setText(pvg.getText());
//                     pvd_$.setText(pvd.getText());
//
//
//                }else if (pvmomey.getSelectedItem().equals("CDF"))
//                {
//                   try{
//             
//            String sql="select  BUY,SELL from currency where NOM='CDF'";
//            
//            pst=con.prepareStatement(sql);
//            rs=pst.executeQuery();
//            while(rs.next()){
//                 buy =rs.getString("BUY");
//                 sell =rs.getString("SELL");
//                 
//            }
//            }
//        catch(Exception ex){
//          JOptionPane.showMessageDialog(null, ex);    
//        }
//Double a = Double.parseDouble(pvg.getText());
//Double b = Double.parseDouble(pvd.getText());
//Double d= Double.parseDouble(sell);
//Double c,cc;
//cc=b/d;
//c=a/d;
//  pvg_$.setText(""+c);
//  pvd_$.setText(""+cc);
//
//               
//                }
//               
//                }else  if (cdf.equals("Yes"))
//                {
//                if (pvmomey.getSelectedItem().equals("CDF"))
//                {
//           
//                    pvg_$.setText(pvg.getText());
//                     pvd_$.setText(pvd.getText());
//
//
//                }else if (pvmomey.getSelectedItem().equals("USD"))
//                {
//                   try{
//             
//            String sql="select  BUY,SELL from currency where NOM='CDF'";
//            
//            pst=con.prepareStatement(sql);
//            rs=pst.executeQuery();
//            while(rs.next()){
//                 buy =rs.getString("BUY");
//                 sell =rs.getString("SELL");
//                 
//            }
//            }
//        catch(Exception ex){
//          JOptionPane.showMessageDialog(null, ex);    
//        }
//Double a = Double.parseDouble(pvg.getText());
//Double b = Double.parseDouble(pvd.getText());
//Double d= Double.parseDouble(buy);
//Double c,cc;
//cc=b*d;
//c=a*d;
//  pvg_$.setText(""+c);
//  pvd_$.setText(""+cc);
//
//               
//                }
//                
//                }
//
//
//     
//        }
   public void call_in_table_search(){
     // `NOM`, `CLASSIFICATION`, `CATEGORIE`, `P_ACHAT`, `P_VENTE`, `NUM`
	
	
      try{
            
           
             String sql="SELECT NOMS,CLASSI AS 'CLASSIFICATION',CATEGORI as 'FORME',P_ACHAT,P_VENTE,D_UNITE AS 'UNITY',NUM  FROM `h_p_medicament` where "+jComboBox8.getSelectedItem()+" like '"+jTextField10.getText()+"%' ORDER BY NOMS";
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
       
        
       
       
      
       
       col0.setPreferredWidth(200);
       col1.setPreferredWidth(100);
       col2.setPreferredWidth(100);
         col3.setPreferredWidth(50);
       col4.setPreferredWidth(50);
       col5.setPreferredWidth(20);
       col6.setPreferredWidth(80);
        
      
      
     
       
       
      // jTable1.setModel(mode);
       
     
    }catch(Exception ex){JOptionPane.showMessageDialog(null,ex);}  
     
     } 
      public void call_in_tableS(){
     // `NOM`, `CLASSIFICATION`, `CATEGORIE`, `P_ACHAT`, `P_VENTE`, `NUM`
	
	
      try{
            
           
             String sql="SELECT NOMS,CLASSI AS 'CLASSIFICATION',CATEGORI as 'FORME',P_ACHAT,P_VENTE,D_UNITE AS 'UNITY',NUM FROM h_p_medicament ORDER BY NOMS";
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
       
        
       
       
      
       
       col0.setPreferredWidth(200);
       col1.setPreferredWidth(100);
       col2.setPreferredWidth(100);
         col3.setPreferredWidth(50);
       col4.setPreferredWidth(50);
       col5.setPreferredWidth(20);
       col6.setPreferredWidth(80);
        
      
      
     
       
       
      // jTable1.setModel(mode);
       
     
    }catch(Exception ex){JOptionPane.showMessageDialog(null,ex);}  
     
     }
        public void select_jTable2S()
   {
       
         try{
          int row= jTable1.getSelectedRow();
          String Table_click = (jTable1.getModel().getValueAt(row,6). toString());
          String sql = "SELECT * FROM h_p_medicament WHERE NUM= '"+Table_click+"'";
          
          pst = con.prepareStatement(sql);
          rs=pst.executeQuery();
          if(rs.next()){
        
         String sum1 =rs.getString("NOM");
         String sum2 =rs.getString("CLASSI");
         String sum3 =rs.getString("CATEGORI");
         String sum4 =rs.getString("P_ACHAT");
         String sum5 =rs.getString("P_VENTE");
         
              nom.setText(sum1);
    classes.setSelectedItem(sum2);
    fpharma.setSelectedItem(sum3);
    pad.setText(sum4);
    pag.setText(sum5);
    
         
         
          String sum6 =rs.getString("D_UNITE");
         String sum7 =rs.getString("P_ACHAT_G");
         String sum8 =rs.getString("P_VENTE_G");
         String sum9 =rs.getString("G_VENTE");
         String sum10 =rs.getString("NUM");
         
          prmoney.setSelectedItem(sum6);
    pvg.setText(sum7);
    pvd.setText(sum8);
    pvmomey.setSelectedItem(sum9);
     num.setText(sum10);
     
    
         
          String sum11 =rs.getString("MARK");
         String sum12 =rs.getString("MARK_QTY");
         String sum13 =rs.getString("CONTAINER");
         String sum14 =rs.getString("CONTAINER_QTY");
         String sum15 =rs.getString("QTY_UNITE");
         
         mrc.setSelectedItem(sum11);
         
          String sum16 =rs.getString("VAL_UNITE");
        
         
         
     
   
    
    mark_qty.setText(sum12);
    ctn.setSelectedItem(sum13);
    ctn_qty.setText(sum14);
    grame.setText(sum15);
    unity.setSelectedItem(sum16);
       
                  
              
          }
          
            
           
    }catch(Exception ex ){
         JOptionPane.showMessageDialog(null,ex);
       
     }
}
         public void updateS(){
               try{
         
           
     
         // java.sql.Connection con = getConnection();
     String sql="update h_p_medicament set NOM=?,NOMS=?,CLASSI=?,CATEGORI=?,P_ACHAT=?,P_VENTE=?,D_UNITE=?,P_ACHAT_G=?,P_VENTE_G=?,G_VENTE=?,NUM=?,MARK=?,MARK_QTY=?,CONTAINER=?,CONTAINER_QTY=?,QTY_UNITE=?,VAL_UNITE=? where NUM='"+num.getText()+"'";
     pst=con.prepareStatement(sql);
     
     //String value1=jComboBox1.getSelectedItem().toString();
       pst.setString(1, nom.getText());
      pst.setString(2, nom.getText()+" "+grame.getText()+" "+unity.getSelectedItem());
      pst.setString(3, classes.getSelectedItem().toString());
      pst.setString(4, fpharma.getSelectedItem().toString());
           pst.setString(5, pad.getText());
      
      pst.setString(6, pvd.getText());
      pst.setString(7, prmoney.getSelectedItem().toString());
      pst.setString(8, pag.getText());
      pst.setString(9, pvg.getText());
      pst.setString(10,pvmomey.getSelectedItem().toString());
      pst.setString(11, num.getText());
    
      pst.setString(12, mrc.getSelectedItem().toString());
      pst.setString(13, mark_qty.getText());
      pst.setString(14, ctn.getSelectedItem().toString());
      pst.setString(15, ctn_qty.getText());
      pst.setString(16, grame.getText());
      
      pst.setString(17, unity.getSelectedItem().toString());
   
            pst.executeUpdate();
               
              
   JOptionPane.showMessageDialog(null,"Transaction Saved");
      
    // cat2.setText("");
      
     }catch(Exception ex){JOptionPane.showMessageDialog(null,ex);}  
             }
       
          public void deleteS()
    {
         try{
        String sql = "DELETE FROM h_p_medicament WHERE NUM=?";
         
         pst = con.prepareStatement(sql);
         pst.setString(1,num.getText());
         pst.executeUpdate();

     }catch(SQLException | HeadlessException ex ){
         JOptionPane.showMessageDialog(null,ex);
     } 
        // cat2.setText("");
    }
          

        public void refresh(){
            num.setText("");
    nom.setText("");
    classes.setSelectedItem("......");
    fpharma.setSelectedItem("......");
    pad.setText("");
    pag.setText("");
    prmoney.setSelectedItem("......");
    pvd.setText("");
    pvg.setText("");
    pvmomey.setSelectedItem("......");
    mrc.setSelectedItem("......");
    mark_qty.setText("");
    ctn.setSelectedItem("......");
    ctn_qty.setText("");
    grame.setText("");
    unity.setSelectedItem("......");
  
        
        }
        
        
        
          public void imports(){
        EB_Model();
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
      XSSFCell cell9 = excelRow.getCell(9);
       XSSFCell cell0 = excelRow.getCell(10);
       XSSFCell cell11 = excelRow.getCell(11);
      excels.addRow(new Object[]{cell,cell1,cell2,cell3,cell4,cell5,cell6,cell7,cell8,cell9,cell0,cell11}); 
      }
      
      }catch(FileNotFoundException ex){
      ex.printStackTrace();
      }     catch (IOException ex) {
               ex.printStackTrace();
            }
      
      }
//SaveBudget();
//Table(); 
}
     public void EB_Model(){
        mode=new DefaultTableModel();
       // ,,,, , ,,,,,,
        mode.addColumn("Nom");    
        mode.addColumn("Class");
        mode.addColumn("Forme");
        mode.addColumn("P Achat");    
        mode.addColumn("P vente");
        mode.addColumn("Pachat G");
        mode.addColumn("P Vente G");    
        mode.addColumn("Marque");
        mode.addColumn("Containance");
        mode.addColumn("Containance QTY");    
        mode.addColumn("Dosage");
         mode.addColumn("Unite");
       
        jTable1.setModel(mode);

  }
      public void SaveBudget(){
   DefaultTableModel excels= (DefaultTableModel)jTable1.getModel();
   String NOM= null,CLASSI = null,CATEGORI= null,P_ACHAT= null,P_VENTE= null,D_UNITE= null,P_ACHAT_G= null,P_VENTE_G= null,G_VENTE= null,MARK= null,MARK_QTY= null,CONTAINER= null,CONTAINER_QTY= null,QTY_UNITE= null,VAL_UNITE= null;
    for(int i=0; i < excels.getRowCount();i++){
   NOM  = excels.getValueAt(i,0). toString();
    CLASSI  = excels.getValueAt(i,1). toString();
    CATEGORI  = excels.getValueAt(i,2). toString();
   // ITEM  = excels.getValueAt(i,3). toString();
    P_ACHAT  = excels.getValueAt(i,3). toString();
    P_VENTE  = excels.getValueAt(i,4). toString();
  
    P_ACHAT_G  = excels.getValueAt(i,5). toString();
    P_VENTE_G  = excels.getValueAt(i,6). toString();
   
    MARK  = excels.getValueAt(i,7). toString();
    MARK_QTY  = "1";
    
     CONTAINER  = excels.getValueAt(i,8). toString();
    CONTAINER_QTY  = excels.getValueAt(i,9). toString();
   QTY_UNITE  = excels.getValueAt(i,10). toString();
    VAL_UNITE  = excels.getValueAt(i,11). toString();
    

    
     roll();
               try{
          
           
     
         // java.sql.Connection con = getConnection();
     String sql="INSERT INTO h_p_medicament (NOM,NOMS,CLASSI,CATEGORI,P_ACHAT,P_VENTE,D_UNITE,P_ACHAT_G,P_VENTE_G,G_VENTE,NUM,MARK,MARK_QTY,CONTAINER,CONTAINER_QTY,QTY_UNITE,VAL_UNITE)"+
             "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     pst=con.prepareStatement(sql);
    
         pst.setString(1,NOM);
      pst.setString(2, NOM);
   //= null,= null,= null,= null;
      pst.setString(3, CLASSI);
      pst.setString(4, CATEGORI);
      pst.setString(5, P_ACHAT);
      
      pst.setString(6, P_VENTE);
      pst.setString(7, "USD");
      pst.setString(8, P_ACHAT_G);
      pst.setString(9, P_VENTE_G);
      pst.setString(10,"USD");
      pst.setString(11, rolls);
    
      pst.setString(12,MARK);
      pst.setString(13,MARK_QTY);
      pst.setString(14,CONTAINER);
      pst.setString(15,CONTAINER_QTY);
      pst.setString(16,QTY_UNITE);
      
      pst.setString(17,VAL_UNITE);
 
       //pst.setString(21, cat1.getText());
      
      pst.executeUpdate();
               
              
 
     }catch(Exception ex){JOptionPane.showMessageDialog(null,ex);}         


            
        }
   
          
    JOptionPane.showMessageDialog(null,"Tranction Saved");
   
  } 
      
        public void reportBYSTOCK()
     {
          
     
             try{
                 
                 
                   String sqls="Select path from pathn";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
            if(rs.next()){
             
                String sum=rs.getString("path");
                  
                 String NameFiles=sum;
            
            String NameFile=""+NameFiles+"h_p_medoc.jrxml";
                 
                 
                 
                JasperDesign jd=JRXmlLoader.load(NameFile);
                 
               //JasperDesign jd=JRXmlLoader.load("C:\\Users\\Dosh\\Documents\\NetBeansProjects\\intreprisemanagementsystem\\src\\intreprisemanagementsystem\\journaldecaise.jrxml");
             // String sql="select * from inventairemtr WHERE NUM_ID='"+num.getText()+"'";
              String sql="SELECT * FROM `h_p_medicament` order by CLASSI,NOMS ";
              //select * from  inventairemtr INNER JOIN materiaCLASSIux_in ON inventairemtr.NUM=materiaux_in.NUM_ID
              
   
    
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
        
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        num = new javax.swing.JTextField();
        nom = new javax.swing.JTextField();
        grame = new javax.swing.JTextField();
        unity = new javax.swing.JComboBox<>();
        classes = new javax.swing.JComboBox<>();
        fpharma = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        mrc = new javax.swing.JComboBox<>();
        mark_qty = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        ctn = new javax.swing.JComboBox<>();
        ctn_qty = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        pad = new javax.swing.JTextField();
        pvd = new javax.swing.JTextField();
        jPanel10 = new javax.swing.JPanel();
        pag = new javax.swing.JTextField();
        pvg = new javax.swing.JTextField();
        jPanel11 = new javax.swing.JPanel();
        prmoney = new javax.swing.JComboBox<>();
        pvmomey = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jPanel19 = new javax.swing.JPanel();
        jComboBox8 = new javax.swing.JComboBox<>();
        jTextField10 = new javax.swing.JTextField();
        jPanel20 = new javax.swing.JPanel();
        jPanel21 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jPanel22 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jPanel23 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        jScrollPane10 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setBackground(new java.awt.Color(192, 255, 192));

        jPanel3.setBackground(new java.awt.Color(192, 255, 192));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jPanel2.setBackground(new java.awt.Color(128, 255, 128));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Médicaments");

        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("X");
        jLabel13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel13MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 456, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 470, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
            .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel4.setBackground(new java.awt.Color(192, 255, 192));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jPanel12.setBackground(new java.awt.Color(192, 255, 192));
        jPanel12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jPanel15.setBackground(new java.awt.Color(192, 255, 192));
        jPanel15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        jPanel15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel15MouseClicked(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("New");

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jPanel16.setBackground(new java.awt.Color(0, 255, 0));
        jPanel16.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        jPanel16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel16MouseClicked(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Save");

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel10)
                .addContainerGap())
        );

        jPanel17.setBackground(new java.awt.Color(255, 51, 51));
        jPanel17.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        jPanel17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel17MouseClicked(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Delete");

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel13.setBackground(new java.awt.Color(192, 255, 192));
        jPanel13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        num.setEditable(false);
        num.setBackground(new java.awt.Color(240, 240, 241));
        num.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        nom.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        grame.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        grame.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        unity.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        unity.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "......", "g", "mg", "l", "ml" }));

        classes.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        classes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "......" }));

        fpharma.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        fpharma.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "......" }));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("No.");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Nom Médicaments");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("Classification");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("Forme Pharmaceutique");

        jTextField1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setText("Signalisation Stock");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addGap(24, 24, 24)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(classes, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(fpharma, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addComponent(nom, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(11, 11, 11))
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addComponent(num, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField1)
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addComponent(grame, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(unity, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(num, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(grame, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(unity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(classes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fpharma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(6, 6, 6))
        );

        jPanel14.setBackground(new java.awt.Color(192, 255, 192));
        jPanel14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel14.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel5.setBackground(new java.awt.Color(192, 255, 192));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Type d'Achat/Vente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel6.setBackground(new java.awt.Color(192, 255, 192));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Mrc/Qty_In", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        mrc.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        mrc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "......" }));
        jPanel6.add(mrc, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 16, 78, -1));

        mark_qty.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        mark_qty.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel6.add(mark_qty, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 43, 78, -1));

        jPanel5.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 16, 90, 80));

        jPanel7.setBackground(new java.awt.Color(192, 255, 192));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ctn/Nbr_In", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ctn.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        ctn.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "......" }));
        jPanel7.add(ctn, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 16, 80, -1));

        ctn_qty.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        ctn_qty.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel7.add(ctn_qty, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 43, 80, -1));

        jPanel5.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(98, 16, 90, 80));

        jPanel14.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 200, 100));

        jPanel8.setBackground(new java.awt.Color(192, 255, 192));
        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Prix", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel9.setBackground(new java.awt.Color(192, 255, 192));
        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Détails", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pad.setEditable(false);
        pad.setBackground(new java.awt.Color(240, 240, 241));
        pad.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        pad.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel9.add(pad, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 16, 80, -1));

        pvd.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        pvd.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel9.add(pvd, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 43, 80, -1));

        jPanel8.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, 110, 80));

        jPanel10.setBackground(new java.awt.Color(192, 255, 192));
        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Gros", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N
        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pag.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        pag.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pag.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                pagKeyReleased(evt);
            }
        });
        jPanel10.add(pag, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 16, 80, -1));

        pvg.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        pvg.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel10.add(pvg, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 42, 80, -1));

        jPanel8.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 10, 100, 80));

        jPanel11.setBackground(new java.awt.Color(192, 255, 192));
        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Monnaies", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N
        jPanel11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        prmoney.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        prmoney.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "USD", "CDF" }));
        jPanel11.add(prmoney, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 16, -1, -1));

        pvmomey.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        pvmomey.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "USD", "CDF" }));
        jPanel11.add(pvmomey, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 43, -1, -1));

        jPanel8.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 10, 80, 80));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("Pr. Reviens");
        jPanel8.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 34, -1, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("Pr. Vente");
        jPanel8.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 66, -1, -1));

        jPanel14.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 10, 390, 100));

        jPanel19.setBackground(new java.awt.Color(192, 255, 192));
        jPanel19.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jComboBox8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jComboBox8.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "NOMS", "CLASSI" }));

        jTextField10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTextField10.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField10KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBox8, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField10))
                .addContainerGap())
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jComboBox8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel20.setBackground(new java.awt.Color(192, 255, 192));
        jPanel20.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jPanel21.setBackground(new java.awt.Color(192, 255, 192));
        jPanel21.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        jPanel21.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel21MouseClicked(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Import Excel");

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jPanel22.setBackground(new java.awt.Color(0, 255, 0));
        jPanel22.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        jPanel22.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel22MouseClicked(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("Save Excel");

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel22Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel15)
                .addContainerGap())
        );

        jPanel23.setBackground(new java.awt.Color(192, 255, 192));
        jPanel23.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        jPanel23.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel23MouseClicked(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("Print List");

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel16)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jPanel18.setBackground(new java.awt.Color(192, 255, 192));
        jPanel18.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jTable1.setBackground(new java.awt.Color(192, 255, 192));
        jTable1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable1.setRowHeight(24);
        jTable1.setSelectionForeground(new java.awt.Color(198, 215, 233));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTable1MousePressed(evt);
            }
        });
        jScrollPane10.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane10)
                .addContainerGap())
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
           // TODO add your handling code here:
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jPanel15MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel15MouseClicked
refresh();// TODO add your handling code here:
    }//GEN-LAST:event_jPanel15MouseClicked

    private void jPanel16MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel16MouseClicked
if(nom.getText().equals("") ||classes.getSelectedItem().equals("....")||fpharma.getSelectedItem().equals("....") ||mark_qty.getText().equals("") || ctn_qty.getText().equals("") || pag.getText().equals("") || pad.getText().equals("") || pvg.getText().equals("") || pvd.getText().equals("") ){
JOptionPane.showMessageDialog(null,"Wrong Data","Error",JOptionPane.ERROR_MESSAGE);
}else{
        if(num.getText().equals("")){
            
save();
}else{
            
updateS();
}  
call_in_tableS();
refresh();     
}                  // TODO add your handling code here:
    }//GEN-LAST:event_jPanel16MouseClicked

    private void jPanel17MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel17MouseClicked

if(num.getText().equals("")){
JOptionPane.showMessageDialog(null,"Wrong Data","Error",JOptionPane.ERROR_MESSAGE);
}else{
        deleteS();
call_in_tableS();
refresh();
}                 // TODO add your handling code here:
    }//GEN-LAST:event_jPanel17MouseClicked

    private void pagKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pagKeyReleased
        caluculate1();        // TODO add your handling code here:
    }//GEN-LAST:event_pagKeyReleased

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        select_jTable2S();        // TODO add your handling code here:
    }//GEN-LAST:event_jTable1MouseClicked

    private void jTable1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable1MousePressed

    private void jLabel13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MouseClicked
this.show(false);        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel13MouseClicked

    private void jPanel21MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel21MouseClicked
imports();        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel21MouseClicked

    private void jPanel22MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel22MouseClicked
SaveBudget();
        call_in_tableS();        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel22MouseClicked

    private void jPanel23MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel23MouseClicked
reportBYSTOCK();        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel23MouseClicked

    private void jTextField10KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField10KeyReleased
call_in_table_search();        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField10KeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> classes;
    private javax.swing.JComboBox<String> ctn;
    private javax.swing.JTextField ctn_qty;
    private javax.swing.JComboBox<String> fpharma;
    private javax.swing.JTextField grame;
    private javax.swing.JComboBox<String> jComboBox8;
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
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField mark_qty;
    private javax.swing.JComboBox<String> mrc;
    private javax.swing.JTextField nom;
    private javax.swing.JTextField num;
    private javax.swing.JTextField pad;
    private javax.swing.JTextField pag;
    private javax.swing.JComboBox<String> prmoney;
    private javax.swing.JTextField pvd;
    private javax.swing.JTextField pvg;
    private javax.swing.JComboBox<String> pvmomey;
    private javax.swing.JComboBox<String> unity;
    // End of variables declaration//GEN-END:variables
}
