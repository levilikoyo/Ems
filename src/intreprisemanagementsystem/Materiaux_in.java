/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intreprisemanagementsystem;

import canalplus.cell.TableActionCellEditor;
import canalplus.cell.TableActionCellRender;
import canalplus.cell.TableActionEvent;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
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
import raven.glasspanepopup.GlassPanePopup;
import sample.message.add_on_stock;
import sample.message.add_on_stocks;

/**
 *
 * @author DOSHE
 */
public class Materiaux_in extends javax.swing.JInternalFrame {

Connection con=null;
PreparedStatement pst=null;
ResultSet rs= null;
//DefaultTableModel mode;
 String rolls,roll_in;
 String NUM_ID,NUM_IDS;
 DefaultTableModel mode; 
 

    public Materiaux_in() {
        initComponents();
              con=JavaDbConnect.dbConnect();
  
       TableActionEvent event = new TableActionEvent(){
        public void onEdit(int row){
           
        }
            @Override
            public void onnew(int row) {
             int rows= jTable1.getSelectedRow();
String    num = (jTable1.getModel().getValueAt(rows,0). toString());
String    det = (jTable1.getModel().getValueAt(rows,1). toString());
String    qtys = (jTable1.getModel().getValueAt(rows,2). toString());
String    ups = (jTable1.getModel().getValueAt(rows,3). toString());
 String qty=qtys.replace(",", "");
 String up=ups.replace(",", "");
double a = Double.parseDouble(qty);
double b = Double.parseDouble(up);
double c=a*b;
    con=JavaDbConnect.dbConnect(); 
           try{
      String sql = "UPDATE etat_de_besoin set DET=?,QTY=?,PU=?,PT=? WHERE ID =?";
        
         pst = con.prepareStatement(sql);
        
         pst.setString(1,det);
         pst.setString(2,qty);
          pst.setString(3,up);
         pst.setDouble(4,c);
          pst.setString(5,num);
         pst.executeUpdate();

     }catch(SQLException | HeadlessException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }
            }

            public void onsave(int row) {
              
       
            }

            @Override
            public void ondelete(int row) {
int rows= jTable1.getSelectedRow();
String    num = (jTable1.getModel().getValueAt(rows,0). toString());
      con=JavaDbConnect.dbConnect();   
        try{
        String sql = "DELETE FROM  etat_de_besoin WHERE ID=? ";
         
         pst = con.prepareStatement(sql);
         pst.setString(1,num);
        
         pst.executeUpdate();

     }catch(SQLException | HeadlessException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }    
            }
        };
    jTable1.getColumnModel().getColumn(12).setCellRenderer(new TableActionCellRender());
    jTable1.getColumnModel().getColumn(12).setCellEditor(new TableActionCellEditor(event));
        call_table_num();
       call_table(); 
         
       //      setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("icons8_Raspberry_Pi_48px_1.png")));
    }
    
    
     SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
         String dates = dateFormat1.format(new Date());
        
    
 public void etroll()
     {
         try{
            String sql="SELECT NUM FROM  gestion_stock where STATUS = 'Stock In' ORDER BY NUM DESC LIMIT 1";
            

            
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
                 rolls=stxt+snum ;
                 
                 
                 
             }else{
                 rolls="STOCK-IN-1001";
             }
                   }catch(Exception e){
             JOptionPane.showMessageDialog(null, e); 
             
         }

     }
  public void numid()
     {
         try{
            String sql="SELECT NUM_ID FROM inventairemtr where  STATUS = 'Stock_In' ORDER BY NUM_ID DESC LIMIT 1";
             pst=con.prepareStatement(sql);
             rs=pst.executeQuery();
             if(rs.next()){
                 String rl=rs.getString("NUM_ID");
                 int ln=rl.length();
                 String stxt=rl.substring(0,6);
                 String snum=rl.substring(6,ln);
                 int n = Integer.parseInt(snum);
                 n++;
                 snum=Integer.toString(n);
                NUM_ID=stxt+snum;
                 
                 
                 
             }else{
                 //rolls="FICHE/EB/2018/1";
                // rolls="EB1001";
                 NUM_ID="ID-MTR1001";
             }
              }catch(Exception e){
             JOptionPane.showMessageDialog(null, e); 
             
         }
         }
 

 public void call_table_num(){
      
           try{
           
             String sql="SELECT distinct(NUM) FROM `gestion_stock` where STATUS='Stock In'";
     //  String sql="SELECT ID, `NUM_FACTURE`, `DATE_FACTURE`, `DESCRIPTION`, `DEBIT_QTY`, `DEBIT_PU`, `DEBIT_PT`, `DEBIT_TVA`, `CREDIT_QTY`, `CREDIT_PU`, `CREDIT_PT`, `CREDIT_TVA`, `DATES`, `NUM` FROM facture_fournisseur where compte='"+jComboBox6.getSelectedItem()+"'";
       pst = con.prepareStatement(sql);
      rs= pst.executeQuery();
      
       
       jTable2.setModel(DbUtils.resultSetToTableModel(rs));
       mode=new DefaultTableModel();
    }catch(SQLException ex ){
     JOptionPane.showMessageDialog(null, ex);
}
 try{
          String sql="SELECT * FROM add_on_stock where cat='Nom fabricant' order by Nom";
             pst = con.prepareStatement(sql);rs=pst.executeQuery();
            while(rs.next()){
                //String sum=rs.getString("nom");
                String sums=rs.getString("Nom");
             
               Materiaux_in.fabr.addItem(sums);
            }
            }catch(Exception ex){JOptionPane.showMessageDialog(null, ex); }
 
 try{
          String sql="SELECT * FROM add_on_stock where cat='Couleur' order by Nom";
             pst = con.prepareStatement(sql);rs=pst.executeQuery();
            while(rs.next()){
                //String sum=rs.getString("nom");
                String sums=rs.getString("Nom");
             
               Materiaux_in.couleur.addItem(sums);
            }
            }catch(Exception ex){JOptionPane.showMessageDialog(null, ex); }
 
 try{
          String sql="SELECT * FROM add_on_stock where cat='Unité' order by Nom";
             pst = con.prepareStatement(sql);rs=pst.executeQuery();
            while(rs.next()){
                //String sum=rs.getString("nom");
                String sums=rs.getString("Nom");
             
               Materiaux_in.unite.addItem(sums);
            }
            }catch(Exception ex){JOptionPane.showMessageDialog(null, ex); } 
  try{
          String sql="SELECT * FROM ohada where SUBSTR='40' order by Name";
             pst = con.prepareStatement(sql);rs=pst.executeQuery();
            while(rs.next()){
                //String sum=rs.getString("nom");
                String sums=rs.getString("Name");
             
               four.addItem(sums);
            }
            }catch(Exception ex){JOptionPane.showMessageDialog(null, ex); } 
   }
 public void save_INVENTAORY(){
 etroll();
 String Num=null;
  
if(nums.getText().equals("")){
Num=rolls;
}else{
Num=nums.getText();
}
    
 try{
    String sql="INSERT INTO  gestion_stock (NUMS,NOM,FRABRICANT,COULEUR,UNITE,QTY,PU,PT,GARANTI,DATEFABRI,FOURNISSEUR,DATE,PURPUS,RESP,STATUS,NUM,ALERT)  VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     
    pst=con.prepareStatement(sql);
    
    pst.setString(1,item_id.getText());
    pst.setString(2,item.getText());
    pst.setString(3,fabr.getSelectedItem().toString()); 
    pst.setString(4,couleur.getSelectedItem().toString());
    pst.setString(5,unite.getSelectedItem().toString());
    
    
    pst.setString(6,qty.getText());
    pst.setString(7,pu.getText());
    pst.setString(8,pt.getText());
    
    pst.setString(9,garanti.getText());
    pst.setString(10,datefabr.getText());
    pst.setString(11,four.getSelectedItem().toString());
    pst.setString(12,datee.getText());
    
    
   
   pst.setString(13,"Stock in");
   pst.setString(14,"LOG");
   pst.setString(15,"Stock in");
  
     pst.setString(17,alert.getText());
      pst.setString(16,Num);
    
    pst.executeUpdate();
    //  JOptionPane.showMessageDialog(null,"Datas Saved");
    }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}
 

num();    
 call_table();

 }
 public void num(){
     try{
            String sqls="select max(num) from  gestion_stock where STATUS='Stock In'";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
           if(rs.next()){
             nums.setText(rs.getString("max(num)"));
            }
            }
     
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
    
    }
 
  public void save_alerte(){
               try{
          String sql="select * from  alert where  NAME='"+item_id.getText()+"' AND DESCR='"+item.getText()+"'";
    
            pst=con.prepareStatement(sql);
          //  pst.setString(1, item.getText());
            rs=pst.executeQuery();
          if(rs.next()){
          }else{
 try{
    String sqls="INSERT INTO `alert`(`NAME`, `DESCR`, `ALERT`) VALUES (?,?,?)";
     
    pst=con.prepareStatement(sqls);
    pst.setString(1,item_id.getText());
    pst.setString(2,item.getText());
    pst.setString(3,alert.getText());
   
    pst.executeUpdate();
     // JOptionPane.showMessageDialog(null,"Datas Saved");
    }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}
    
}
          
          
         
    }catch(SQLException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }

 
 }
 
 
   public void save_alerte_table(){
        
        
       Double a,ALERT = null;
      Double b;
      Double c = null;
       
         try{
          String sql="select sum(debit),sum(credit) from  inventairemtr where ITEM=?";
    
            pst=con.prepareStatement(sql);
          //  pst.setString(1, desc.getText());
            rs=pst.executeQuery();
          if(rs.next()){
              
            
               a = rs.getDouble("sum(debit)");
               //qtyin.setText(add5);
               b = rs.getDouble("sum(credit)");
              c= a-b;
              // qtyin.setText(""+c);
    //qtyout.setText(""+b);
              
               
               
             
          }
         
    }catch(SQLException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }
         try{
          String sql="select Alert from  alert   where NAME='"+item_id.getText()+"' AND DESCR='"+item.getText()+"'";
    
            pst=con.prepareStatement(sql);
         //   pst.setString(1,item_id.getText());
            rs=pst.executeQuery();
          if(rs.next()){
                ALERT = rs.getDouble("ALERT");
             
              
              
          }
         
    }catch(SQLException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }
    // JOptionPane.showMessageDialog(null,c);   
    // JOptionPane.showMessageDialog(null,ALERT);
        
if(c <=ALERT){
    
   try{
          String sql="select ITEM_ID from  Alert_table   where ITEM_ID='"+item_id.getText()+"' AND DESCR='"+item.getText()+"'";
    
            pst=con.prepareStatement(sql);
         //   pst.setString(1,item_id.getText());
            rs=pst.executeQuery();
          if(rs.next()){
              
            try{
    String sql1="UPDATE `Alert_table` SET `QTY`=? WHERE  ITEM_ID='"+item_id.getText()+"' AND DESCR='"+item.getText()+"'";
     
    pst=con.prepareStatement(sql1);
   
    pst.setDouble(1,c);
   
    pst.executeUpdate();
     // JOptionPane.showMessageDialog(null,"Datas Saved");
    }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}    
          }else{
          
           try{
    String sql2="INSERT INTO `Alert_table`(`ITEM_ID`, `DESCR`, `STATUT`, `QTY`) VALUES (?,?,?,?)";
     
    pst=con.prepareStatement(sql2);
    pst.setString(1,item_id.getText());
    pst.setString(2,item.getText());
     pst.setString(3,"Materiaux");
    pst.setDouble(4,c);
   
    pst.executeUpdate();
     // JOptionPane.showMessageDialog(null,"Datas Saved");
    }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}
    

          }
         
    }catch(SQLException ex ){
         JOptionPane.showMessageDialog(null,ex);
     } 
    
 
  }else{
try{
        String sql = "DELETE FROM  Alert_table WHERE ITEM_ID=?";
        
         pst = con.prepareStatement(sql);
         pst.setString(1,item_id.getText());
         pst.executeUpdate();
      //   JOptionPane.showMessageDialog(null,"delete AUTRES ACHAT");
              
     }catch(SQLException | HeadlessException ex ){
         JOptionPane.showMessageDialog(null,ex);
     } 
}
 
 } 
 
 public void delete(){
 
     try{
        String sql = "DELETE FROM  inventairemtr WHERE 	NUM_ID=? AND NUM=?";
        
         pst = con.prepareStatement(sql);
         pst.setString(2,nums.getText());
       //  pst.setString(1,id.getText());
         pst.executeUpdate();
      //   JOptionPane.showMessageDialog(null,"delete AUTRES ACHAT");
              
     }catch(SQLException | HeadlessException ex ){
         JOptionPane.showMessageDialog(null,ex);
     } 
 
 }
 
 
 public void Update(){

 try{
    String sql="UPDATE  `materiaux_in`SET `ITEM_ID`=?, `ITEM`=?, `DESCR`=?,  `UNITY`=? where NUM_ID='"+item.getText()+"'";
     
    pst=con.prepareStatement(sql);
    pst.setString(1,item_id.getText());
    pst.setString(2,item.getText());
   // pst.setString(3,desc.getText());

   // pst.setString(4,unity.getSelectedItem().toString());
    
    
  
      
    
    pst.executeUpdate();
     // JOptionPane.showMessageDialog(null,"Datas Saved");
    }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}
    
 // Update_INVENTAORY();
 
 }
 

 
           
              
              public void refresh(){
              
              item_id.setText("");
              item.setText("");
             garanti.setText("");
             
              alert.setText("0.00");
              
            qty.setText("0.00");
             fabr.setSelectedItem("- Nom fabricant * -");
             couleur.setSelectedItem("- Couleur -");
            unite.setSelectedItem("- Unité -");
             four.setSelectedItem("- Fournisseur -");
             garanti.setText("");
             datefabr.setText("");
             qty.setText("0.00");
             pu.setText("0.00");
             pt.setText("0.00");
           //   matrid.setSelectedItem("Select Item ID");
            //  unity.setSelectedItem("Unity");
              
              }

              
               public void call_table(){
      
           try{
        //  String sql="SELECT `ID` AS NUM,`DET`, `QTY`, `PU`, `PT`, `DATES`, `APPROUVATION` FROM `etat_de_besoin` WHERE  `ORIENTATION`='FINANCE' AND ORIENTATION2='Fin' and buss='"+journal1.buss.getText()+"'";   pst = con.prepareStatement(sql);
           
        PreparedStatement ps = con.prepareStatement("Select * from gestion_stock WHERE  `STATUS`='Stock in'");
        ResultSet rs=ps.executeQuery();
        DefaultTableModel tm = (DefaultTableModel)jTable1.getModel();
        tm.setRowCount(0);

      while(rs.next()){//``, ``, ``, `                                                                                    `, ``, ``, ``, ``, ``, `DATEFABRI`, ``, `DATE`
            Object o[] = {rs.getString("NUMS"),rs.getString("NOM"),rs.getString("FRABRICANT"),rs.getString("COULEUR"),rs.getString("UNITE"),rs.getDouble("QTY"),rs.getDouble("PU"),rs.getDouble("PT"),rs.getString("GARANTI"),rs.getString("DATEFABRI"),rs.getString("FOURNISSEUR"),rs.getString("DATE")};
            tm.addRow(o);



        }
   
    }
    catch(Exception e){

        JOptionPane.showMessageDialog(null,"Error in Employee Grid View..... "+e);
    }

    
      
      }
               
               


      public void call_table_num_Search(){
      
           try{
           
             String sql="SELECT distinct(NUM_id) FROM `inventairemtr` where NUM like '"+jTextField1.getText()+"%' or ITEM like '"+jTextField1.getText()+"%' and STATUS='Stock_In'";
     //  String sql="SELECT ID, `NUM_FACTURE`, `DATE_FACTURE`, `DESCRIPTION`, `DEBIT_QTY`, `DEBIT_PU`, `DEBIT_PT`, `DEBIT_TVA`, `CREDIT_QTY`, `CREDIT_PU`, `CREDIT_PT`, `CREDIT_TVA`, `DATES`, `NUM` FROM facture_fournisseur where compte='"+jComboBox6.getSelectedItem()+"'";
       pst = con.prepareStatement(sql);
      rs= pst.executeQuery();
      
       
       jTable2.setModel(DbUtils.resultSetToTableModel(rs));
       mode=new DefaultTableModel();
    }catch(SQLException ex ){
     JOptionPane.showMessageDialog(null, ex);
}
    
                             }
               
               
               
               
               
                 public void call_table_refreh(){
      
           try{
           
             String sql="SELECT `ITEM_ID`, `ITEM`, `DESCR`, `UNITY`, `DATES`,`NUM_ID` FROM `materiaux_in` WHERE NUM='xxxxxxxx'";
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
       
       
      
       
       col0.setPreferredWidth(100);
       col1.setPreferredWidth(100);
       col2.setPreferredWidth(100);
       col3.setPreferredWidth(50);
       col4.setPreferredWidth(50);
       col5.setPreferredWidth(50);
       col6.setPreferredWidth(50);
     
      
       
       
      // jTable1.setModel(mode);
       
     
    }catch(SQLException ex ){
     JOptionPane.showMessageDialog(null, ex);
}
    
      
      }
               
     
              
                public void select_jTable()
   {
       
       
        try{
          int row= jTable1.getSelectedRow();
         // String rows =jTable1.getName()
          String Table_click = (jTable1.getModel().getValueAt(row,3). toString());
          String sql = "SELECT * FROM inventairemtr WHERE NUM_ID= '"+Table_click+"'";
          pst = con.prepareStatement(sql);
          rs=pst.executeQuery();
          if(rs.next()){
             String add14= rs.getString("ITEM");
          //  desc.setText(add14);
            item.setText(add14);
            
             String add16= rs.getString("UNITY");
          //  unity.setSelectedItem(add16);
             String add11= rs.getString("NUM_ID");
           //  id.setText(add11);
         
             String add12= rs.getString("ITEM_ID");
            item_id.setText(add12);
            
             String add13= rs.getString("ITEM");
            item.setText(add13);  
            
           
          }
         
    }catch(SQLException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }
        
        
       
        
        
       }
                
                   public void select_jTable_small()
   {
       
       
        try{
          int row= jTable2.getSelectedRow();
         // String rows =jTable1.getName()
          String Table_click = (jTable2.getModel().getValueAt(row,0). toString());
          String sql = "SELECT * FROM inventairemtr WHERE NUM= '"+Table_click+"'";
          pst = con.prepareStatement(sql);
          rs=pst.executeQuery();
          if(rs.next()){
        //`ITEM_ID`, `ITEM`, `DESCR`, `QTY`, `UNITY`, `DATES`, `NUM`, `NUM_ID`
               String add11= rs.getString("NUM");
            nums.setText(add11);
          }
          
          
         
    }catch(SQLException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }
        call_table();
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
            
            String NameFile=""+NameFiles+"Bon_entre_stock.jrxml";
                 
                 
                 
                JasperDesign jd=JRXmlLoader.load(NameFile);
                 
               //JasperDesign jd=JRXmlLoader.load("C:\\Users\\Dosh\\Documents\\NetBeansProjects\\intreprisemanagementsystem\\src\\intreprisemanagementsystem\\journaldecaise.jrxml");
             // String sql="select * from inventairemtr WHERE NUM_ID='"+num.getText()+"'";
              String sql="select * from  inventairemtr  WHERE NUM='"+nums.getText()+"'";
              //select * from  inventairemtr INNER JOIN materiaux_in ON inventairemtr.NUM=materiaux_in.NUM_ID
              
     HashMap param= new HashMap();
    param.put("nom", "BON D'ENTREE STOCK NO:");
    param.put("four", "Service acheteur");
    
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
     }
//setDefaultCloseOperation(DISPOSE_ON_CLOSE); 
     
       
 
 
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
      excels.addRow(new Object[]{cell,cell1,cell2,cell3,cell4,cell5,cell6,cell7,cell8,cell9,cell0}); 
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
        mode.addColumn("ITEM_ID");    
        mode.addColumn("ITEM");
        mode.addColumn("DESCR");
        mode.addColumn("ALERT");    
        mode.addColumn("DEBIT");
        mode.addColumn("CREDIT");
        mode.addColumn("UNITY");    
        mode.addColumn("DATE");
        mode.addColumn("SITE");
        mode.addColumn("RESPONSABLE");    
        mode.addColumn("PURPOSE");
       
        jTable1.setModel(mode);

  }
      public void etrollmatout()
     {
         try{
            String sql="SELECT NUM_id FROM  inventairemtr where STATUS = 'Stock_Out' ORDER BY NUM_id DESC LIMIT 1";
            

            
             pst=con.prepareStatement(sql);
             rs=pst.executeQuery();
             if(rs.next()){
                 String rl=rs.getString("NUM_id");
                 int ln=rl.length();
                 String stxt=rl.substring(0,14);
                 String snum=rl.substring(14,ln);
                 int n = Integer.parseInt(snum);
                 n++;
                 snum=Integer.toString(n);
                 rolls=stxt+snum ;
                 
                 
                 
             }else{
                 rolls="MAT-STOCK-OUT/1001";
             }
                   }catch(Exception e){
             JOptionPane.showMessageDialog(null, e); 
             
         }
     }
       public void etrollmatIN()
     {
         try{
            String sql="SELECT NUM_id FROM  inventairemtr where STATUS = 'Stock_In' ORDER BY NUM_id DESC LIMIT 1";
            

            
             pst=con.prepareStatement(sql);
             rs=pst.executeQuery();
             if(rs.next()){
                 String rl=rs.getString("NUM_id");
                 int ln=rl.length();
                 String stxt=rl.substring(0,13);
                 String snum=rl.substring(13,ln);
                 int n = Integer.parseInt(snum);
                 n++;
                 snum=Integer.toString(n);
                 roll_in=stxt+snum ;
                 
                 
                 
             }else{
                 roll_in="MAT-STOCK-IN/1001";
             }
                   }catch(Exception e){
             JOptionPane.showMessageDialog(null, e); 
             
         }
     }
    public void SaveBudget(){
        etroll();
        etrollmatIN();
   DefaultTableModel excels= (DefaultTableModel)jTable1.getModel(); 
   String ITEM_ID= null,ITEM = null,DESCR= null,ALERT= null,DEBIT= null,CREDIT= null,UNITY= null,DATE= null,SITE= null,RESPONSABLE= null,PURPOSE= null;
    for(int i=0; i < excels.getRowCount();i++){
    ITEM_ID  = excels.getValueAt(i,0). toString();
    ITEM  = excels.getValueAt(i,1). toString();
    DESCR  = excels.getValueAt(i,2). toString();
   // ITEM  = excels.getValueAt(i,3). toString();
    ALERT  = excels.getValueAt(i,3). toString();
    DEBIT  = excels.getValueAt(i,4). toString();
    CREDIT  = excels.getValueAt(i,5). toString();
    UNITY  = excels.getValueAt(i,6). toString();
    DATE  = excels.getValueAt(i,7). toString();
    SITE  = excels.getValueAt(i,8). toString();
    RESPONSABLE  = excels.getValueAt(i,9). toString();
    PURPOSE  = excels.getValueAt(i,10). toString();
    
 if(CREDIT.equals("0.0")){
 numid();
 
           try{
          String sql="select * from  materiaux_in where  ITEM_ID ='"+ITEM_ID+"' AND DESCR='"+DESCR+"'";
    
            pst=con.prepareStatement(sql);
          //  pst.setString(1, item.getText());
            rs=pst.executeQuery();
          if(rs.next()){
          }else{
 try{
    String sqls="INSERT INTO `materiaux_in`(`ITEM_ID`, `ITEM`, `DESCR`,`UNITY`,`NUM_ID`) VALUES (?,?,?,?,?)";
     
    pst=con.prepareStatement(sqls);
    pst.setString(1,ITEM_ID);
    pst.setString(2,ITEM);
    pst.setString(3,DESCR);
    pst.setString(4,UNITY);
     pst.setString(5,NUM_ID);
    pst.executeUpdate();
     // JOptionPane.showMessageDialog(null,"Datas Saved");
    }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}
    
}
          
    }catch(SQLException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }
         try{
            String sql="select * from materiaux_in  where DESCR='"+DESCR+"'";
   
            pst=con.prepareStatement(sql);
           
            rs=pst.executeQuery();
          if(rs.next()){
              NUM_IDS = rs.getString("NUM_ID");
               
      
          }
         
    }catch(SQLException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }     
            try{
    String sql="INSERT INTO `inventairemtr`(`ITEM`, `DEBIT`, `CREDIT`,`UNITY`, `DATES`, `SITE`, `PARPUSE`, `EMP`, `NUM_ID`, `NUM`)  VALUES (?,?,?,?,?,?,?,?,?,?)";
     
    pst=con.prepareStatement(sql);
   
    pst.setString(1,DESCR);
    pst.setString(2,DEBIT);
     pst.setString(3,"0");
    pst.setString(4,UNITY);
   pst.setString(6,"LOG");
   pst.setString(7,"LOG");
   pst.setString(8,"LOG");
  
     pst.setString(9,roll_in);
      pst.setString(10,NUM_IDS);
    
         pst.setString(5, DATE);
      
    
    pst.executeUpdate();
    //  JOptionPane.showMessageDialog(null,"Datas Saved");
    }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}
     
 }else{
     etrollmatout();
     try{
            String sql="select * from materiaux_in  where DESCR='"+DESCR+"'";
   
            pst=con.prepareStatement(sql);
           
            rs=pst.executeQuery();
          if(rs.next()){
              NUM_IDS = rs.getString("NUM_ID");
               
      
          }
         
    }catch(SQLException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }
    try{
    String sql="INSERT INTO `inventairemtr`(`ITEM`, `DEBIT`, `CREDIT`,`UNITY`, `DATES`, `SITE`, `PARPUSE`, `EMP`, `NUM_ID`, `NUM`,STATUS)  VALUES (?,?,?,?,?,?,?,?,?,?,?)";
     
    pst=con.prepareStatement(sql);
   
    pst.setString(1,DESCR);
    pst.setString(3,CREDIT);
     pst.setString(2,"0");
    pst.setString(4,UNITY);
   pst.setString(6,SITE);
   pst.setString(7,PURPOSE);
   pst.setString(8,RESPONSABLE);
  
     pst.setString(9,rolls);
      pst.setString(10,NUM_IDS);
      pst.setString(11,"Stock_Out");
         pst.setString(5, DATE);
      
    
    pst.executeUpdate();
    //  JOptionPane.showMessageDialog(null,"Datas Saved");
    }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}

    
    }         
try{
          String sql="select * from  alert where  NAME='"+ITEM_ID+"' AND DESCR='"+DESCR+"'";
    
            pst=con.prepareStatement(sql);
          //  pst.setString(1, item.getText());
            rs=pst.executeQuery();
          if(rs.next()){
          }else{
 try{
    String sqls="INSERT INTO `alert`(`NAME`, `DESCR`, `ALERT`) VALUES (?,?,?)";
     
    pst=con.prepareStatement(sqls);
    pst.setString(1,ITEM_ID);
    pst.setString(2,DESCR);
    pst.setString(3,ALERT);
   
    pst.executeUpdate();
     // JOptionPane.showMessageDialog(null,"Datas Saved");
    }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}
    
}
          
          
         
    }catch(SQLException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }


            
        }
   
          
    JOptionPane.showMessageDialog(null,"Tranction Saved");
   
  }
  
  public void calculate(){
 double a = Double.parseDouble(qty.getText());
 double b = Double.parseDouble(pu.getText());
 double c= a*b;
 pt.setText(""+c);
 
 
 }              
               
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dateChooser1 = new com.raven.datechooser.DateChooser();
        dateChooser2 = new com.raven.datechooser.DateChooser();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jTextField2 = new Palette.TextField();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        item_id = new Palette.MyTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        item = new Palette.MyTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        nums = new Palette.MyTextField();
        jLabel24 = new javax.swing.JLabel();
        alert = new Palette.MyTextField();
        jPanel4 = new javax.swing.JPanel();
        garanti = new Palette.MyTextField();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        datefabr = new Palette.MyTextField();
        four = new javax.swing.JComboBox<>();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        datee = new Palette.MyTextField();
        jButton1 = new javax.swing.JButton();
        pt = new Palette.MyTextField();
        jLabel19 = new javax.swing.JLabel();
        pu = new Palette.MyTextField();
        jLabel18 = new javax.swing.JLabel();
        qty = new Palette.MyTextField();
        jLabel17 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jTextField1 = new Palette.TextField();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();

        dateChooser1.setDateFormat("yyyy-MM-dd");
        dateChooser1.setTextRefernce(datefabr);

        dateChooser2.setDateFormat("yyyy-MM-dd");
        dateChooser2.setTextRefernce(datee);

        setBorder(null);
        setTitle("Material In");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_Brick_Wall_16px.png"))); // NOI18N
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                formMouseDragged(evt);
            }
        });
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameActivated(evt);
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameClosed(evt);
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
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jPanel1MouseDragged(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTable1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Numéro produit", "Nom", "Nom fabricant", "Couleur", "Unité", "Quantité", "Prix Uté", "Prix total", "Garantie", "Date de fabrication", "Fournisseur", "Dates", "Action"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Float.class, java.lang.Float.class, java.lang.Float.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                true, true, true, true, true, false, false, false, true, true, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setGridColor(new java.awt.Color(153, 204, 255));
        jTable1.setRowHeight(32);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable1);

        jTextField2.setLabelText("Recherche");
        jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField2KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 283, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        item_id.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel6.setText("Numéro produit *");

        jLabel10.setText("Nom *");

        jLabel11.setText("Nom fabricant *");

        jLabel12.setText("Couleur");

        jLabel13.setText("Unité *");

        fabr.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "- Nom fabricant * -" }));
        fabr.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 204, 255), 2));

        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8-plus-24.png"))); // NOI18N
        jLabel14.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel14MouseClicked(evt);
            }
        });

        couleur.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "- Couleur -" }));
        couleur.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 204, 255), 2));

        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8-plus-24.png"))); // NOI18N
        jLabel15.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel15MouseClicked(evt);
            }
        });

        unite.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "- Unité -" }));
        unite.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 204, 255), 2));

        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8-plus-24.png"))); // NOI18N
        jLabel16.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel16MouseClicked(evt);
            }
        });

        nums.setEditable(false);
        nums.setBackground(new java.awt.Color(242, 242, 241));
        nums.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        nums.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel24.setText("Alerte Stock *");

        alert.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        alert.setText("0.00");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(unite, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(fabr, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                                .addComponent(couleur, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addComponent(nums, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(item_id, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(alert, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(item, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(nums, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(item_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(alert, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(item, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(fabr)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(couleur)
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(unite)
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jLabel20.setText("Garantie");

        jLabel21.setText("Date de fabrication");

        datefabr.setEditable(true);

        four.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "- Fournisseur -" }));
        four.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 204, 255), 2));

        jLabel22.setText("Fournisseur *");

        jLabel23.setText("Date *");

        datee.setEditable(true);

        jButton1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton1.setText("OK");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        pt.setEditable(false);
        pt.setBackground(new java.awt.Color(242, 242, 241));
        pt.setText("0.00");

        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel19.setText("Prix total *");

        pu.setText("0.00");
        pu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                puKeyReleased(evt);
            }
        });

        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel18.setText("Prix Uté *");

        qty.setText("0.00");
        qty.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                qtyKeyReleased(evt);
            }
        });

        jLabel17.setText("Quantité *");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(garanti, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(datefabr, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(four, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(datee, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(8, 8, 8))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(qty, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pu, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pt, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(garanti, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(datefabr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(four, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(datee, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(qty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTable2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable2.setRowHeight(30);
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTable2);

        jTextField1.setLabelText("Recherche");
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 119, Short.MAX_VALUE)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 25, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jMenu3.setText("X");
        jMenu3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jMenu3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu3MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu3);

        jMenu1.setText("File");

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem2.setText("New");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem1.setText("Save");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem3.setText("Delete");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenu4.setText("Print");

        jMenuItem4.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem4.setText("Print Stock Sheet");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem4);

        jMenu1.add(jMenu4);

        jMenu2.setText("Import");

        jMenuItem8.setText("Excel");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem8);

        jMenuItem9.setText("Save Excel");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem9);

        jMenu1.add(jMenu2);

        jMenuBar1.add(jMenu1);

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

        setSize(new java.awt.Dimension(1308, 656));
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
if(item_id.getText().equals("")||alert.getText().equals("")){
JOptionPane.showMessageDialog(null,"Wrong Data","Error",JOptionPane.ERROR_MESSAGE);
}else if(item.getText().equals("")){
JOptionPane.showMessageDialog(null,"Wrong Data","Error",JOptionPane.ERROR_MESSAGE);
}else{
// save(); 
save_INVENTAORY();
call_table();  
call_table_num();
refresh(); 
}

// TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
      select_jTable();
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable1MouseClicked

    private void formInternalFrameClosed(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosed
// TODO add your handling code here:
    }//GEN-LAST:event_formInternalFrameClosed

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
      // TODO add your handling code here:
    }//GEN-LAST:event_formMouseClicked

    private void formInternalFrameActivated(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameActivated
         // TODO add your handling code here:
    }//GEN-LAST:event_formInternalFrameActivated

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
         // TODO add your handling code here:
    }//GEN-LAST:event_formMousePressed

    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
                                     
   
       // TODO add your handling code here:
    }//GEN-LAST:event_formMouseDragged

    private void jMenu3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu3MouseClicked
this.dispose(); 
    logs.matin.setText("");
// TODO add your handling code here:
    }//GEN-LAST:event_jMenu3MouseClicked

    private void jPanel1MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseDragged
      // TODO add your handling code here:
    }//GEN-LAST:event_jPanel1MouseDragged

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
refresh();
nums.setText("");
jTable1.setModel(new DefaultTableModel());
call_table_refreh();// TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
delete();   
call_table();
call_table_num();
refresh();// TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
reportBYSTOCK();        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
select_jTable_small();        // TODO add your handling code here:
    }//GEN-LAST:event_jTable2MouseClicked

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
imports();        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
SaveBudget();        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
call_table_num_Search();         // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1KeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
save_INVENTAORY(); 
refresh();// TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jLabel14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel14MouseClicked
add_on_stocks m = new add_on_stocks();
add_on_stocks.jLabel1.setText("Nom fabricant");
            m.show();
            m. setDefaultCloseOperation(DISPOSE_ON_CLOSE); 
            
// TODO add your handling code here:
    }//GEN-LAST:event_jLabel14MouseClicked

    private void jLabel15MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel15MouseClicked
add_on_stocks m = new add_on_stocks();
add_on_stocks.jLabel1.setText("Couleur");
            m.show();
            m. setDefaultCloseOperation(DISPOSE_ON_CLOSE);         // TODO add your handling code here:
    }//GEN-LAST:event_jLabel15MouseClicked

    private void jLabel16MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel16MouseClicked
add_on_stocks m = new add_on_stocks();
add_on_stocks.jLabel1.setText("Unité");
            m.show();
            m. setDefaultCloseOperation(DISPOSE_ON_CLOSE);         // TODO add your handling code here:
    }//GEN-LAST:event_jLabel16MouseClicked

    private void qtyKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_qtyKeyReleased
calculate();        // TODO add your handling code here:
    }//GEN-LAST:event_qtyKeyReleased

    private void puKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_puKeyReleased
calculate();        // TODO add your handling code here:
    }//GEN-LAST:event_puKeyReleased

    private void jTextField2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyReleased
 try{
        //  String sql="SELECT `ID` AS NUM,`DET`, `QTY`, `PU`, `PT`, `DATES`, `APPROUVATION` FROM `etat_de_besoin` WHERE  `ORIENTATION`='FINANCE' AND ORIENTATION2='Fin' and buss='"+journal1.buss.getText()+"'";   pst = con.prepareStatement(sql);
           
        PreparedStatement ps = con.prepareStatement("Select * from gestion_stock WHERE NUMS like '"+jTextField2.getText()+"%' or NOM like '"+jTextField2.getText()+"%' or FRABRICANT like '"+jTextField2.getText()+"%' or COULEUR like '"+jTextField2.getText()+"%' or GARANTI like '"+jTextField2.getText()+"%' or FOURNISSEUR  and `STATUS`='Stock in'");
        ResultSet rs=ps.executeQuery();
        DefaultTableModel tm = (DefaultTableModel)jTable1.getModel();
        tm.setRowCount(0);

      while(rs.next()){//``, ``, ``, `                                                                                    `, ``, ``, ``, ``, ``, `DATEFABRI`, ``, `DATE`
            Object o[] = {rs.getString("NUMS"),rs.getString("NOM"),rs.getString("FRABRICANT"),rs.getString("COULEUR"),rs.getString("UNITE"),rs.getDouble("QTY"),rs.getDouble("PU"),rs.getDouble("PT"),rs.getString("GARANTI"),rs.getString("DATEFABRI"),rs.getString("FOURNISSEUR"),rs.getString("DATE")};
            tm.addRow(o);



        }
   
    }
    catch(Exception e){

        JOptionPane.showMessageDialog(null,"Error in Employee Grid View..... "+e);
    }
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2KeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Palette.MyTextField alert;
    public static final javax.swing.JComboBox<String> couleur = new javax.swing.JComboBox<>();
    private com.raven.datechooser.DateChooser dateChooser1;
    private com.raven.datechooser.DateChooser dateChooser2;
    private Palette.MyTextField datee;
    private Palette.MyTextField datefabr;
    public static final javax.swing.JComboBox<String> fabr = new javax.swing.JComboBox<>();
    private javax.swing.JComboBox<String> four;
    private Palette.MyTextField garanti;
    private Palette.MyTextField item;
    private Palette.MyTextField item_id;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private Palette.TextField jTextField1;
    private Palette.TextField jTextField2;
    private Palette.MyTextField nums;
    private Palette.MyTextField pt;
    private Palette.MyTextField pu;
    private Palette.MyTextField qty;
    public static final javax.swing.JComboBox<String> unite = new javax.swing.JComboBox<>();
    // End of variables declaration//GEN-END:variables
}
