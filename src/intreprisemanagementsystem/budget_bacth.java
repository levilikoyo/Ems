/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intreprisemanagementsystem;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.HeadlessException;
import java.awt.print.PrinterException;
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
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import journals.extouner;
import net.proteanit.sql.DbUtils;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.swing.JRViewer;
import net.sf.jasperreports.view.JasperViewer;
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
 * @author Dosh
 */
public class budget_bacth extends javax.swing.JPanel {

   Connection con=null;
    Connection conof=null;
PreparedStatement pst=null;
ResultSet rs= null;
 DefaultTableModel mode;
    public budget_bacth() {
        initComponents();
         con=JavaDbConnect.dbConnect();;
         call();
         jDateChooser1.setDate(new Date());
    }
    public void call(){
     try{
            String sql="select * from caisse_dispacting WHERE NAME='"+Home_page.home.user.getText()+"' and ACCESS='Yes'";
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
     String sql="select CAT AS '...' from caisse_dispacting WHERE NAME='"+Home_page.home.user.getText()+"' and ACCESS='Yes'";
       pst = con.prepareStatement(sql);
      rs= pst.executeQuery();
     
     jTable1.setModel(DbUtils.resultSetToTableModel(rs));
       
    }catch(SQLException ex ){
     JOptionPane.showMessageDialog(null, ex);}   
    
    }
public void callitem(){
 try{
     String sqls="SELECT  BATCH FROM `batchs` where PROJET='"+buss.getSelectedItem().toString()+"' ";
       pst = con.prepareStatement(sqls);
      rs= pst.executeQuery();
     
     jTable4.setModel(DbUtils.resultSetToTableModel(rs));
       
    }catch(SQLException ex ){
     JOptionPane.showMessageDialog(null, ex);}
        try{  // String Table_click = (jList2.getModel().getSelectedItem(tmps,0). toString());
                     
       String sqls="SELECT  BATCH FROM `batchs` where PROJET='"+buss.getSelectedItem().toString()+"' ";
       pst = con.prepareStatement(sqls);
      rs= pst.executeQuery();
     jTable3.setModel(DbUtils.resultSetToTableModel(rs));
       
    }catch(SQLException ex ){
     JOptionPane.showMessageDialog(null, ex);} 
        
         try{  // String Table_click = (jList2.getModel().getSelectedItem(tmps,0). toString());
                     
       String sqls="SELECT  CODE AS LB FROM `BUDGET` where PROJECT='"+buss.getSelectedItem().toString()+"' ORDER BY  ID,CODE";
       pst = con.prepareStatement(sqls);
      rs= pst.executeQuery();
     tbl_lbgrdlivre.setModel(DbUtils.resultSetToTableModel(rs));
       
    }catch(SQLException ex ){
     JOptionPane.showMessageDialog(null, ex);} 
        
        
         try{ 
       String sqls="SELECT  CODE AS LB,ITEM,sum(PT) as PT FROM budget where PROJECT='"+buss.getSelectedItem().toString()+"' GROUP BY CODE ORDER BY ID";
       pst = con.prepareStatement(sqls);
      rs= pst.executeQuery();
     
     tbl_dispacting.setModel(DbUtils.resultSetToTableModel(rs));
      // DefaultTableCellRenderer centre =new DefaultTableCellRenderer();
       TableColumn col0= tbl_dispacting.getColumnModel().getColumn(0);
        TableColumn col1= tbl_dispacting.getColumnModel().getColumn(1);
        TableColumn col2= tbl_dispacting.getColumnModel().getColumn(2);
       col0.setPreferredWidth(10);
       col1.setPreferredWidth(300);
       col2.setPreferredWidth(50);
     
    }catch(SQLException ex ){
     JOptionPane.showMessageDialog(null, ex);}
                 
        try{ 
       String sqls="SELECT   CODE AS LB,ITEM,SUM(QTY) AS QTY,UNITY,SUM(PU) AS UP,sum(PT) as PT FROM budget where PROJECT='"+buss.getSelectedItem().toString()+"' group by ID,CODE ORDER by ID";
       pst = con.prepareStatement(sqls);
      rs= pst.executeQuery();
     
     tbl_budget.setModel(DbUtils.resultSetToTableModel(rs));
       DefaultTableCellRenderer centre =new DefaultTableCellRenderer();
      centre.setHorizontalAlignment(JLabel.CENTER);
       TableColumn col0= tbl_budget.getColumnModel().getColumn(0);
      tbl_budget.getColumnModel().getColumn(0).setCellRenderer(centre);
        TableColumn col1= tbl_budget.getColumnModel().getColumn(1);
        TableColumn col2= tbl_budget.getColumnModel().getColumn(2);
        tbl_budget.getColumnModel().getColumn(2).setCellRenderer(centre);
        TableColumn col3= tbl_budget.getColumnModel().getColumn(3);
        tbl_budget.getColumnModel().getColumn(3).setCellRenderer(centre);
        TableColumn col4= tbl_budget.getColumnModel().getColumn(4);
        tbl_budget.getColumnModel().getColumn(4).setCellRenderer(centre);
        TableColumn col5= tbl_budget.getColumnModel().getColumn(5);
        tbl_budget.getColumnModel().getColumn(5).setCellRenderer(centre);
       col0.setPreferredWidth(10);
       col1.setPreferredWidth(500);
       col2.setPreferredWidth(50);
       col3.setPreferredWidth(50);
       col4.setPreferredWidth(50);
       col5.setPreferredWidth(50);
     
    }catch(SQLException ex ){
     JOptionPane.showMessageDialog(null, ex);}            
                
            }

public void callitemsub(){
 try{  // String Table_click = (jList2.getModel().getSelectedItem(tmps,0). toString());
                     
       String sqls="SELECT  BATCH FROM `batchs` where PROJET='"+buss.getSelectedItem().toString()+"' ";
       pst = con.prepareStatement(sqls);
      rs= pst.executeQuery();
     
     jTable4.setModel(DbUtils.resultSetToTableModel(rs));
       
    }catch(SQLException ex ){
     JOptionPane.showMessageDialog(null, ex);}
        try{  // String Table_click = (jList2.getModel().getSelectedItem(tmps,0). toString());
                     
       String sqls="SELECT  BATCH FROM `batchs` where PROJET='"+buss.getSelectedItem().toString()+"' ";
       pst = con.prepareStatement(sqls);
      rs= pst.executeQuery();
     jTable3.setModel(DbUtils.resultSetToTableModel(rs));
       
    }catch(SQLException ex ){
     JOptionPane.showMessageDialog(null, ex);} 
        
         try{  // String Table_click = (jList2.getModel().getSelectedItem(tmps,0). toString());
                     
       String sqls="SELECT  LBSUB AS LB FROM `BUDGET` where PROJECT='"+buss.getSelectedItem().toString()+"' GROUP BY LBSUB";
       pst = con.prepareStatement(sqls);
      rs= pst.executeQuery();
     tbl_lbgrdlivre.setModel(DbUtils.resultSetToTableModel(rs));
       
    }catch(SQLException ex ){
     JOptionPane.showMessageDialog(null, ex);} 
        
        
         try{ 
       String sqls="SELECT  LBSUB AS LB,SUB_CAT AS ITEM,sum(PT) as PT FROM budget where PROJECT='"+buss.getSelectedItem().toString()+"' GROUP BY LBSUB";
       pst = con.prepareStatement(sqls);
      rs= pst.executeQuery();
     
     tbl_dispacting.setModel(DbUtils.resultSetToTableModel(rs));
      // DefaultTableCellRenderer centre =new DefaultTableCellRenderer();
       TableColumn col0= tbl_dispacting.getColumnModel().getColumn(0);
        TableColumn col1= tbl_dispacting.getColumnModel().getColumn(1);
        TableColumn col2= tbl_dispacting.getColumnModel().getColumn(2);
       col0.setPreferredWidth(10);
       col1.setPreferredWidth(300);
       col2.setPreferredWidth(50);
     
    }catch(SQLException ex ){
     JOptionPane.showMessageDialog(null, ex);}
                 
        try{ 
       String sqls="SELECT  LBSUB AS LB,SUB_CAT AS ITEM,SUM(QTY) AS QTY,UNITY,SUM(PU) AS UP,sum(PT) as PT FROM budget where PROJECT='"+buss.getSelectedItem().toString()+"' group by LBSUB";
       pst = con.prepareStatement(sqls);
      rs= pst.executeQuery();
     
     tbl_budget.setModel(DbUtils.resultSetToTableModel(rs));
       DefaultTableCellRenderer centre =new DefaultTableCellRenderer();
      centre.setHorizontalAlignment(JLabel.CENTER);
       TableColumn col0= tbl_budget.getColumnModel().getColumn(0);
      tbl_budget.getColumnModel().getColumn(0).setCellRenderer(centre);
        TableColumn col1= tbl_budget.getColumnModel().getColumn(1);
        TableColumn col2= tbl_budget.getColumnModel().getColumn(2);
        tbl_budget.getColumnModel().getColumn(2).setCellRenderer(centre);
        TableColumn col3= tbl_budget.getColumnModel().getColumn(3);
        tbl_budget.getColumnModel().getColumn(3).setCellRenderer(centre);
        TableColumn col4= tbl_budget.getColumnModel().getColumn(4);
        tbl_budget.getColumnModel().getColumn(4).setCellRenderer(centre);
        TableColumn col5= tbl_budget.getColumnModel().getColumn(5);
        tbl_budget.getColumnModel().getColumn(5).setCellRenderer(centre);
       col0.setPreferredWidth(10);
       col1.setPreferredWidth(500);
       col2.setPreferredWidth(50);
       col3.setPreferredWidth(50);
       col4.setPreferredWidth(50);
       col5.setPreferredWidth(50);
     
    }catch(SQLException ex ){
     JOptionPane.showMessageDialog(null, ex);}            
                
            }

public void callitemgrd(){
 try{  // String Table_click = (jList2.getModel().getSelectedItem(tmps,0). toString());
                     
       String sqls="SELECT  BATCH FROM `batchs` where PROJET='"+buss.getSelectedItem().toString()+"' ";
       pst = con.prepareStatement(sqls);
      rs= pst.executeQuery();
     
     jTable4.setModel(DbUtils.resultSetToTableModel(rs));
       
    }catch(SQLException ex ){
     JOptionPane.showMessageDialog(null, ex);}
        try{  // String Table_click = (jList2.getModel().getSelectedItem(tmps,0). toString());
                     
       String sqls="SELECT  BATCH FROM `batchs` where PROJET='"+buss.getSelectedItem().toString()+"' ";
       pst = con.prepareStatement(sqls);
      rs= pst.executeQuery();
     jTable3.setModel(DbUtils.resultSetToTableModel(rs));
       
    }catch(SQLException ex ){
     JOptionPane.showMessageDialog(null, ex);} 
        
         try{  // String Table_click = (jList2.getModel().getSelectedItem(tmps,0). toString());
                     
       String sqls="SELECT  LB FROM `BUDGET` where PROJECT='"+buss.getSelectedItem().toString()+"' GROUP BY LB";
       pst = con.prepareStatement(sqls);
      rs= pst.executeQuery();
     tbl_lbgrdlivre.setModel(DbUtils.resultSetToTableModel(rs));
       
    }catch(SQLException ex ){
     JOptionPane.showMessageDialog(null, ex);} 
        
        
         try{ 
       String sqls="SELECT LB,CAT AS ITEM,sum(PT) as PT FROM budget where PROJECT='"+buss.getSelectedItem().toString()+"' GROUP BY LB";
       pst = con.prepareStatement(sqls);
      rs= pst.executeQuery();
     
     tbl_dispacting.setModel(DbUtils.resultSetToTableModel(rs));
      // DefaultTableCellRenderer centre =new DefaultTableCellRenderer();
       TableColumn col0= tbl_dispacting.getColumnModel().getColumn(0);
        TableColumn col1= tbl_dispacting.getColumnModel().getColumn(1);
        TableColumn col2= tbl_dispacting.getColumnModel().getColumn(2);
       col0.setPreferredWidth(10);
       col1.setPreferredWidth(300);
       col2.setPreferredWidth(50);
     
    }catch(SQLException ex ){
     JOptionPane.showMessageDialog(null, ex);}
                 
        try{ 
       String sqls="SELECT  LB,CAT AS ITEM,SUM(QTY) AS QTY,UNITY,SUM(PU) AS UP,sum(PT) as PT FROM budget where PROJECT='"+buss.getSelectedItem().toString()+"' group by LB";
       pst = con.prepareStatement(sqls);
      rs= pst.executeQuery();
     
     tbl_budget.setModel(DbUtils.resultSetToTableModel(rs));
       DefaultTableCellRenderer centre =new DefaultTableCellRenderer();
      centre.setHorizontalAlignment(JLabel.CENTER);
       TableColumn col0= tbl_budget.getColumnModel().getColumn(0);
      tbl_budget.getColumnModel().getColumn(0).setCellRenderer(centre);
        TableColumn col1= tbl_budget.getColumnModel().getColumn(1);
        TableColumn col2= tbl_budget.getColumnModel().getColumn(2);
        tbl_budget.getColumnModel().getColumn(2).setCellRenderer(centre);
        TableColumn col3= tbl_budget.getColumnModel().getColumn(3);
        tbl_budget.getColumnModel().getColumn(3).setCellRenderer(centre);
        TableColumn col4= tbl_budget.getColumnModel().getColumn(4);
        tbl_budget.getColumnModel().getColumn(4).setCellRenderer(centre);
        TableColumn col5= tbl_budget.getColumnModel().getColumn(5);
        tbl_budget.getColumnModel().getColumn(5).setCellRenderer(centre);
       col0.setPreferredWidth(10);
       col1.setPreferredWidth(500);
       col2.setPreferredWidth(50);
       col3.setPreferredWidth(50);
       col4.setPreferredWidth(50);
       col5.setPreferredWidth(50);
     
    }catch(SQLException ex ){
     JOptionPane.showMessageDialog(null, ex);}            
                
            }
           

 public void selectontable(){
    
        TableModel model1 =tbl_dispacting.getModel();
        int indexs[]=tbl_dispacting.getSelectedRows();
        Object[] row = new Object[4];
        DefaultTableModel model2 = (DefaultTableModel) jTable6.getModel();
       
       
        for(int i=0; i < indexs.length;i++){
             String lb = (tbl_dispacting.getModel().getValueAt(indexs[i],0). toString());
             String bb = (tbl_dispacting.getModel().getValueAt(indexs[i],2). toString());
           
            
            Double b= Double.parseDouble(bb);
            Double c = null;
           try{
          String sql="SELECT sum(debit) FROM budget_trans WHERE   PROJET='"+buss.getSelectedItem()+"' and CODE='"+lb+"'";
             pst = con.prepareStatement(sql);rs=pst.executeQuery();
            while(rs.next()){
               Double a = rs.getDouble("sum(debit)");
               c=b-a;
             
              
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
            
        row[0]= model1.getValueAt(indexs[i],0);
        row[1]= model1.getValueAt(indexs[i],1);
       row[2]= c.toString();
//        row[3]= model1.getValueAt(indexs[i],3);
        model2.addRow(row);
        
        // jTable3.setModel(DbUtils.resultSetToTableModel(rs));
      
       
       
        TableColumn col0=jTable6.getColumnModel().getColumn(0);
        TableColumn col1=jTable6.getColumnModel().getColumn(1);
        TableColumn col2=jTable6.getColumnModel().getColumn(2);
//        TableColumn col3=jTable3.getColumnModel().getColumn(3);
       // TableColumn col4=jTable1.getColumnModel().getColumn(4);
           
        col0.setPreferredWidth(50);
       col1.setPreferredWidth(300);
       col2.setPreferredWidth(50);
//       col3.setPreferredWidth(50);
      // col4.setPreferredWidth(50);
//        
        }
        
    }

  public void selectontablemoin(){
     
       
        int indexs[]=jTable6.getSelectedRows();
        
        DefaultTableModel model = (DefaultTableModel) jTable6.getModel();
        for(int i=0; i < indexs.length;i++){
          
    int getSelectedRowsForDeletion = jTable6.getSelectedRow();
        model.removeRow(getSelectedRowsForDeletion);
        
        }
        
    }
  
  
     public void saveallDOCSS(){
        
 int rowss=jTable4.getSelectedRow();
          String batch = (jTable4.getModel().getValueAt(rowss,0). toString());
      try {PreparedStatement pst= con.prepareStatement("INSERT INTO `batch_budget`(`PROJET`, `BATCH`, `LB`, `ITEM`, `PT`,`PTC`,num) select '"+buss.getSelectedItem()+"','"+batch+"',LB,ITEM,'0','0','' from BUDGET where buss='"+buss.getSelectedItem().toString()+"'");
          pst.executeUpdate();
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    } 
     
     }
  
  public void selectTable(){
       int rowss= jTable4.getSelectedRow();
        // String rows =jTable1.getName()
        String butch = (jTable4.getModel().getValueAt(rowss,0). toString());
        if(jComboBox1.getSelectedItem().equals("LB")){
         try{
            String sqls="SELECT  `CODE`, `ITEM`, `DEBIT` FROM budget_trans where projet='"+buss.getSelectedItem().toString()+"' and BATCH='"+butch+"' AND CREDIT='0.00' GROUP BY CODE";
            pst = con.prepareStatement(sqls);
            rs= pst.executeQuery();

            jTable6.setModel(DbUtils.resultSetToTableModel(rs));
            DefaultTableCellRenderer centre =new DefaultTableCellRenderer();
            centre.setHorizontalAlignment(JLabel.CENTER);
            TableColumn col0=jTable6.getColumnModel().getColumn(0);
            jTable6.getColumnModel().getColumn(0).setCellRenderer(centre);
            TableColumn col1=jTable6.getColumnModel().getColumn(1);
            TableColumn col2=jTable6.getColumnModel().getColumn(2);
            jTable6.getColumnModel().getColumn(2).setCellRenderer(centre);
            //        TableColumn col3=jTable3.getColumnModel().getColumn(3);
            // TableColumn col4=jTable1.getColumnModel().getColumn(4);

            col0.setPreferredWidth(50);
            col1.setPreferredWidth(400);
            col2.setPreferredWidth(50);
            //       col3.setPreferredWidth(50);
            // col4.setPreferredWidth(50);
            //

        }catch(SQLException ex ){
            JOptionPane.showMessageDialog(null, ex);}   
        }if(jComboBox1.getSelectedItem().equals("SUB GRD LB")){
         try{
            String sqls="SELECT  `CODE_SUB_CAT` AS LB, SUB_CAT AS ITEM, `DEBIT` FROM budget_trans where projet='"+buss.getSelectedItem().toString()+"' and BATCH='"+butch+"' AND CREDIT='0.00' GROUP BY CODE_SUB_CAT";
            pst = con.prepareStatement(sqls);
            rs= pst.executeQuery();

            jTable6.setModel(DbUtils.resultSetToTableModel(rs));
            DefaultTableCellRenderer centre =new DefaultTableCellRenderer();
            centre.setHorizontalAlignment(JLabel.CENTER);
            TableColumn col0=jTable6.getColumnModel().getColumn(0);
            jTable6.getColumnModel().getColumn(0).setCellRenderer(centre);
            TableColumn col1=jTable6.getColumnModel().getColumn(1);
            TableColumn col2=jTable6.getColumnModel().getColumn(2);
            jTable6.getColumnModel().getColumn(2).setCellRenderer(centre);
            //        TableColumn col3=jTable3.getColumnModel().getColumn(3);
            // TableColumn col4=jTable1.getColumnModel().getColumn(4);

            col0.setPreferredWidth(50);
            col1.setPreferredWidth(400);
            col2.setPreferredWidth(50);
            //       col3.setPreferredWidth(50);
            // col4.setPreferredWidth(50);
            //

        }catch(SQLException ex ){
            JOptionPane.showMessageDialog(null, ex);}   
        }else if(jComboBox1.getSelectedItem().equals("GRD LB")){

         try{
            String sqls="SELECT  `CODE_CAT` AS LB, CAT AS ITEM, `DEBIT` FROM budget_trans where projet='"+buss.getSelectedItem().toString()+"' and BATCH='"+butch+"' AND CREDIT='0.00' GROUP BY CODE_CAT";
            pst = con.prepareStatement(sqls);
            rs= pst.executeQuery();

            jTable6.setModel(DbUtils.resultSetToTableModel(rs));
            DefaultTableCellRenderer centre =new DefaultTableCellRenderer();
            centre.setHorizontalAlignment(JLabel.CENTER);
            TableColumn col0=jTable6.getColumnModel().getColumn(0);
            jTable6.getColumnModel().getColumn(0).setCellRenderer(centre);
            TableColumn col1=jTable6.getColumnModel().getColumn(1);
            TableColumn col2=jTable6.getColumnModel().getColumn(2);
            jTable6.getColumnModel().getColumn(2).setCellRenderer(centre);
            //        TableColumn col3=jTable3.getColumnModel().getColumn(3);
            // TableColumn col4=jTable1.getColumnModel().getColumn(4);

            col0.setPreferredWidth(50);
            col1.setPreferredWidth(400);
            col2.setPreferredWidth(50);
            //       col3.setPreferredWidth(50);
            // col4.setPreferredWidth(50);
            //

        }catch(SQLException ex ){
            JOptionPane.showMessageDialog(null, ex);}   
        }
  
  }

public void saveselected(){
    int rowss= jTable4.getSelectedRow();
          String butch = (jTable4.getModel().getValueAt(rowss,0). toString());
     int indexs[]=jTable6.getSelectedRows();
     for(int i=0; i < indexs.length;i++){
         String a="AA - ";
           String lb = (jTable6.getModel().getValueAt(indexs[i],0). toString());
           String item = (jTable6.getModel().getValueAt(indexs[i],1). toString());
           String pt = (jTable6.getModel().getValueAt(indexs[i],2). toString());
  
    
    SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
     SimpleDateFormat dateFormatmois = new SimpleDateFormat("MMM");
     SimpleDateFormat dateFormatannee = new SimpleDateFormat("yyyy");
         String date = dateFormats.format(jDateChooser1.getDate());
         String mois = dateFormatmois.format(jDateChooser1.getDate());
         String years = dateFormatannee.format(jDateChooser1.getDate());
  String CAT = null,LB = null,LBSUB = null,SUB_CAT = null;
          try{
          String sql="SELECT CAT,LB,LBSUB,SUB_CAT FROM  budget WHERE code='"+lb+"' and PROJECT='"+buss.getSelectedItem()+"'";
          
            pst=con.prepareStatement(sql);
            //pst.setString(1, tmp);
            rs=pst.executeQuery();
            if(rs.next()){
                 
             LB = rs.getString("LB");     
            LBSUB = rs.getString("LBSUB");
             CAT = rs.getString("CAT");
             SUB_CAT = rs.getString("SUB_CAT");
              
          }
  
      }catch(Exception ex){
        JOptionPane.showMessageDialog(null,ex);  
      }
              try{
            String sqls="select * from budget_trans WHERE  BATCH='"+butch+"' and PROJET='"+buss.getSelectedItem()+"'and CODE='"+lb+"' and CREDIT='0.00'";
             pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
            if(rs.next()){
             
          //  JOptionPane.showMessageDialog(null,"I will update");      
              try {
            
        pst = con.prepareStatement("UPDATE budget_trans SET ITEM=?,DEBIT=? WHERE  BATCH='"+butch+"' and PROJET='"+buss.getSelectedItem()+"' and CODE='"+lb+"' and CREDIT='0.00'" );

pst.setString(1,item);
          pst.setString(2,pt);
        
              pst.executeUpdate();
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
             
            }else{ 
            // JOptionPane.showMessageDialog(null,"I will save");   
               try {
            
         pst = con.prepareStatement("INSERT INTO `budget_trans`(`ITEM`, `DEBIT`, `CREDIT`, `SOLD`, `PROJET`, `CODE`, `NUM`, `CAT`, `DATES`, `MOIS`, `ANNEE`, `SUB_CAT`, `CODE_CAT`, `CODE_SUB_CAT`, `ITEMS`, `BATCH`)"
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
         
          pst.setString(1,"AA - Prévision: "+butch+" -"+item);
         pst.setString(2,pt);
       pst.setString(3,"0.00");
         pst.setString(4,"0");
         pst.setString(5,buss.getSelectedItem().toString());
         pst.setString(6,lb);
         pst.setString(7,"");
        pst.setString(8,CAT);
         pst.setString(9,date);
       pst.setString(10,mois);
         pst.setString(11,years);
         pst.setString(12,SUB_CAT);
         pst.setString(13,LB);
         pst.setString(14,LBSUB);
         pst.setString(15,item);
         pst.setString(16,butch);
        
         
          pst.executeUpdate();
        
              //   JOptionPane.showMessageDialog(null,"CLERCK_IN  SUCCESSFUL BONNE JOURNEE!!!        ");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }    
            
            }
} catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
    

  }
        JOptionPane.showMessageDialog(null,"Transaction Saved");  
        
        

}   

public void Deleteselected(){
    int rowss= jTable4.getSelectedRow();
          String butch = (jTable4.getModel().getValueAt(rowss,0). toString());
     int indexs[]=jTable6.getSelectedRows();
     for(int i=0; i < indexs.length;i++){
       //  String a="AA - ";
           String lb = (jTable6.getModel().getValueAt(indexs[i],0). toString());
           
   try{
        String sql = "DELETE FROM  budget_trans WHERE BATCH='"+butch+"' and PROJET='"+buss.getSelectedItem()+"' and CODE='"+lb+"' and CREDIT='0.00'";
        
         pst = con.prepareStatement(sql);
         pst.executeUpdate();
        // JOptionPane.showMessageDialog(null,"Data Deleted");
              
     }catch(SQLException | HeadlessException ex ){
         JOptionPane.showMessageDialog(null,ex);
     } 
         
    

  }
        JOptionPane.showMessageDialog(null,"Transaction Deleted");  
        
        

} 

  public void save(){
 int rowss= jTable4.getSelectedRow();
          String butch = (jTable4.getModel().getValueAt(rowss,0). toString());
         // String bus = buss.getSelectedItem().toString();
  int rows=jTable6.getRowCount();

  for(int row = 0; row<rows; row++)
  { 
       String a="AA - ";
    String lb = (String)jTable6.getValueAt(row, 0);
   String item = (String)jTable6.getValueAt(row, 1);
    String pt = (String)jTable6.getValueAt(row, 2);
    
    SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
     SimpleDateFormat dateFormatmois = new SimpleDateFormat("MMM");
     SimpleDateFormat dateFormatannee = new SimpleDateFormat("yyyy");
         String date = dateFormats.format(jDateChooser1.getDate());
         String mois = dateFormatmois.format(jDateChooser1.getDate());
         String years = dateFormatannee.format(jDateChooser1.getDate());
  String CAT = null,LB = null,LBSUB = null,SUB_CAT = null;
          try{
          String sql="SELECT CAT,LB,LBSUB,SUB_CAT FROM  budget WHERE code='"+lb+"' and PROJECT='"+buss.getSelectedItem()+"'";
          
            pst=con.prepareStatement(sql);
            //pst.setString(1, tmp);
            rs=pst.executeQuery();
            if(rs.next()){
                 
             LB = rs.getString("LB");     
            LBSUB = rs.getString("LBSUB");
             CAT = rs.getString("CAT");
             SUB_CAT = rs.getString("SUB_CAT");
              
          }
  
      }catch(Exception ex){
        JOptionPane.showMessageDialog(null,ex);  
      }
//    try{
//            String sqls="select * from batch_budget WHERE  BATCH='"+butch+"' and PROJET='"+buss.getSelectedItem()+"'";
//             pst=con.prepareStatement(sqls);
//            rs=pst.executeQuery();
//            if(rs.next()){
//             
//                
//              try {
//            
//        pst = con.prepareStatement("UPDATE batch_budget SET ITEM=?, PT=? WHERE  BATCH='"+butch+"' and PROJET='"+buss.getSelectedItem()+"' and LB='"+lb+"'" );
//         String stxt=item.substring(0,5);
//         if(stxt.equals("AA - ")){
//          pst.setString(1,item);
//         pst.setString(2,pt);
//         }else{
//          pst.setString(1,a+item);
//         pst.setString(2,pt);
//         }
//        
//              pst.executeUpdate();
//    } catch (Exception ex) {
//         JOptionPane.showMessageDialog(null, ex.getMessage());
//    }
//             
//            }else{   
//     try {
//            
//         pst = con.prepareStatement("INSERT INTO `batch_budget`(`PROJET`, `BATCH`, `LB`, `ITEM`, `PT`,`PTC`,num)"
//        +"value(?,?,?,?,?,?,?)");
//        
//        pst.setString(1,buss.getSelectedItem().toString());
//         pst.setString(2,butch);
//       pst.setString(3,lb);
//         pst.setString(4,"AA - "+item);
//         pst.setString(5,pt);
//         pst.setString(6,"");
//         pst.setString(7,"");
//        
//         
//          pst.executeUpdate();
//        
//              //   JOptionPane.showMessageDialog(null,"CLERCK_IN  SUCCESSFUL BONNE JOURNEE!!!        ");
//    } catch (Exception ex) {
//         JOptionPane.showMessageDialog(null, ex.getMessage());
//    } 
//            }
//            
//            }
//        catch(Exception ex){
//          JOptionPane.showMessageDialog(null, ex);    
//        }
    
    
         try {
            
         pst = con.prepareStatement("INSERT INTO `budget_trans`(`ITEM`, `DEBIT`, `CREDIT`, `SOLD`, `PROJET`, `CODE`, `NUM`, `CAT`, `DATES`, `MOIS`, `ANNEE`, `SUB_CAT`, `CODE_CAT`, `CODE_SUB_CAT`, `ITEMS`, `BATCH`)"
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
         
          pst.setString(1,"AA - "+item);
         pst.setString(2,pt);
       pst.setString(3,"0.0");
         pst.setString(4,"0");
         pst.setString(5,buss.getSelectedItem().toString());
         pst.setString(6,lb);
         pst.setString(7,"");
        pst.setString(8,CAT);
         pst.setString(9,date);
       pst.setString(10,mois);
         pst.setString(11,years);
         pst.setString(12,SUB_CAT);
         pst.setString(13,LB);
         pst.setString(14,LBSUB);
         pst.setString(15,item);
         pst.setString(16,butch);
        
         
          pst.executeUpdate();
        
              //   JOptionPane.showMessageDialog(null,"CLERCK_IN  SUCCESSFUL BONNE JOURNEE!!!        ");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    } 

  }
        JOptionPane.showMessageDialog(null,"Transaction Saved");
        
       }
     
public void importexcel(){

     tbl_budget.setModel(new DefaultTableModel());
  mode=new DefaultTableModel();
        mode.addColumn("CODE CAT");
        mode.addColumn("CAT"); 
        mode.addColumn("CODE SC");
        mode.addColumn("SUB CAT");
        mode.addColumn("LB");
        mode.addColumn("ITEM");
        mode.addColumn("UNITE");
        mode.addColumn("QTY");
        mode.addColumn("UP");
        mode.addColumn("LASTING");
        mode.addColumn("POUR");
        mode.addColumn("PT");
       tbl_budget.setModel(mode);
        
   DefaultTableModel excels= (DefaultTableModel)tbl_budget.getModel(); 
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
      XSSFCell cell10 = excelRow.getCell(10);
      XSSFCell cell11= excelRow.getCell(11);
      XSSFCell cell12= excelRow.getCell(12);
     
     excels.addRow(new Object[]{cell,cell1,cell2,cell3,cell4,cell5,cell6,cell7,cell8,cell9,cell10,cell11,cell12}); 
      }
      
      }catch(FileNotFoundException ex){
      ex.printStackTrace();
      }     catch (IOException ex) {
               ex.printStackTrace();
            }
      jLabel9.setText("File Imported");
      //budget.setText("Load");
      } 

}  

  public void SaveBudget(){
   DefaultTableModel excels= (DefaultTableModel)tbl_budget.getModel(); 
   String CAT= null,ITEM = null,QTY,UNITY,PU,PT = null, CODE_CAT = null,CODE_SUB_CAT = null ,LASTING= null , POUR= null ,LB= null , SUB_CAT= null  ;
    for(int i=0; i < excels.getRowCount();i++){
    CODE_CAT  = excels.getValueAt(i,0). toString();
    CAT  = excels.getValueAt(i,1). toString();
    CODE_SUB_CAT = excels.getValueAt(i,2). toString();
    SUB_CAT = excels.getValueAt(i,3). toString();
    LB = excels.getValueAt(i,4). toString();
    ITEM = excels.getValueAt(i,5). toString();
    UNITY = excels.getValueAt(i,6). toString();
    QTY = excels.getValueAt(i,7). toString();
    PU = excels.getValueAt(i,8). toString();
    LASTING = excels.getValueAt(i,9). toString();
    POUR = excels.getValueAt(i,10). toString();
    PT = excels.getValueAt(i,11). toString();
          
         
          
           try {
     
        PreparedStatement pst = con.prepareStatement("INSERT INTO `budget`(`CAT`, `ITEM`, `QTY`, `UNITY`, `PU`, `PT`, `PROJECT`,`CODE`,`LASTING`,`POUR`,`LB`,`SUB_CAT`,`LBSUB`)"
                +"value(?,?,?,?,?,?,?,?,?,?,?,?,?)");
       //select from budget
       //`, ``, ``, ``, ``, ``, ``,``,``,``,``,``,`` 
        pst.setString(1, CAT);
         pst.setString(2, ITEM);
         pst.setString(3, QTY);
         pst.setString(4, UNITY);
         pst.setString(5, PU);
          pst.setString(6, PT);
         pst.setString(7,buss.getSelectedItem().toString());
         pst.setString(8, LB);
         pst.setString(9,LASTING);
          pst.setString(10,POUR);
          pst.setString(11, CODE_CAT);
          pst.setString(12, SUB_CAT);
          pst.setString(13, CODE_SUB_CAT);
        
         pst.executeUpdate();
        // showTableData();
      
                
              //   JOptionPane.showMessageDialog(null,"data saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
          try {
      
        PreparedStatement pst = con.prepareStatement("INSERT INTO `budget_code`(`CAT`, `ITEM`, `CODE`)"
                +"value(?,?,?)");
       
        
        pst.setString(1,buss.getSelectedItem().toString());
         pst.setString(2, ITEM);
         pst.setString(3,LB);
       
        
         pst.executeUpdate();
        // showTableData();
      
         
               //  JOptionPane.showMessageDialog(null,"data saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }   
        }
 
            
          
    JOptionPane.showMessageDialog(null,"Tranction Saved");
    jLabel9.setText("");
   
  }

 public void Save_CAISSE_usd(){
       String GLB = null,GITEM = null,LB,ITEM = null, QTY = null,UNITY = null,UP = null,PT = null;
   DefaultTableModel excels= (DefaultTableModel)tbl_budget.getModel(); 
    for(int i=0; i < excels.getRowCount();i++){
      GLB = excels.getValueAt(i,0).toString();
      GITEM= excels.getValueAt(i,1). toString();
      LB = excels.getValueAt(i,2). toString();
      ITEM = excels.getValueAt(i,3). toString();
      QTY = excels.getValueAt(i,4). toString();
      UNITY= excels.getValueAt(i,5). toString();
      UP = excels.getValueAt(i,6). toString();
     PT = excels.getValueAt(i,7). toString();
   
     
 
             try {
        PreparedStatement pst = con.prepareStatement("INSERT INTO `budget`( `G_LB`,`LB`,`G_ITEM`,`ITEM`,`QTY`,`UP`,`PT`,`UNITY`,`BUSS`,ONLINE)"
        +"value(?,?,?,?,?,?,?,?,?,?)");
        
        pst.setString(1,GLB);
        pst.setString(2,LB);
         pst.setString(3,GITEM);
         pst.setString(4,ITEM);
         pst.setString(5,QTY);
         pst.setString(6,UP);
         pst.setString(7,PT);
         pst.setString(8,UNITY);
         pst.setString(9,buss.getSelectedItem().toString());
          pst.setString(10,"No");
         pst.executeUpdate();
        
           //      JOptionPane.showMessageDialog(null,"Caisse saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }

   
  
    } //END FOR
     JOptionPane.showMessageDialog(null,"Tranction Saved");
     jLabel9.setText("");
      try{ 
       String sqls="SELECT   LB,ITEM,SUM(QTY) AS QTY,UNITY,SUM(UP) AS UP,sum(PT) as PT FROM budget where BUSS='"+buss.getSelectedItem().toString()+"' group by lb";
       pst = con.prepareStatement(sqls);
      rs= pst.executeQuery();
     
     tbl_budget.setModel(DbUtils.resultSetToTableModel(rs));
       DefaultTableCellRenderer centre =new DefaultTableCellRenderer();
      centre.setHorizontalAlignment(JLabel.CENTER);
       TableColumn col0= tbl_budget.getColumnModel().getColumn(0);
      tbl_budget.getColumnModel().getColumn(0).setCellRenderer(centre);
        TableColumn col1= tbl_budget.getColumnModel().getColumn(1);
        TableColumn col2= tbl_budget.getColumnModel().getColumn(2);
        tbl_budget.getColumnModel().getColumn(2).setCellRenderer(centre);
        TableColumn col3= tbl_budget.getColumnModel().getColumn(3);
        tbl_budget.getColumnModel().getColumn(3).setCellRenderer(centre);
        TableColumn col4= tbl_budget.getColumnModel().getColumn(4);
        tbl_budget.getColumnModel().getColumn(4).setCellRenderer(centre);
        TableColumn col5= tbl_budget.getColumnModel().getColumn(5);
        tbl_budget.getColumnModel().getColumn(5).setCellRenderer(centre);
       col0.setPreferredWidth(10);
       col1.setPreferredWidth(600);
       col2.setPreferredWidth(50);
       col3.setPreferredWidth(50);
       col4.setPreferredWidth(50);
       col5.setPreferredWidth(50);
     
    }catch(SQLException ex ){
     JOptionPane.showMessageDialog(null, ex);}    
    } 
     public void report(){
                 String tmp=buss.getSelectedItem().toString();
             try{
             
          String sqls="Select path from pathn";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
            if(rs.next()){
             
                String sum=rs.getString("path");
                  
                 String NameFiles=sum;
            
            String NameFile=""+NameFiles+"budgetrecord.jrxml";//journal_de_banque
           // String NameFile=""+NameFiles+"journal_de_banque.jrxml";//journal_de_banque
                 
                 //SELECT `CAT` AS budget_CAT, `ITEM` AS budget_ITEM, `QTY` AS budget_QTY, `UNITY` AS budget_UNITY, `PU` AS budget_PU, `PT` AS budget_PT, `PROJECT` AS budget_POJECT, `CODE`  AS budget_CODE, `LASTING` AS budget_LASTING FROM `budget` WHERE 1
                 
                JasperDesign jd=JRXmlLoader.load(NameFile);
        
        
              // JasperDesign jd=JRXmlLoader.load("C:\\Users\\Dosh\\Documents\\NetBeansProjects\\intreprisemanagementsystem\\src\\intreprisemanagementsystem\\journaldecaise.jrxml");
              String sql="SELECT * FROM `budget`  INNER JOIN PROJET ON budget.PROJECT=PROJET.PROJET_NUM  where BUDGET.project='"+tmp+"'  ORDER BY BUDGET.ID,lb,LBSUB,CODE";
              
     jPanel5.removeAll();
    jPanel5.repaint();
     jPanel5.revalidate();
                JRDesignQuery nq=new JRDesignQuery();
                nq.setText(sql);
                jd.setQuery(nq);
               
                 JasperReport jr =JasperCompileManager.compileReport(jd);
                 JasperPrint jp=JasperFillManager.fillReport(jr,null,con);
                 JRViewer m= new JRViewer(jp);
     jPanel5.setLayout(new BorderLayout());
     jPanel5.add(m);
            }
             }catch(Exception ex){
                  JOptionPane.showMessageDialog(null, ex);   
             }
             
             }
       public void reportGrdLivre()
                           
     {
         
       int row= tbl_lbgrdlivre.getSelectedRow();
          String Table_clickS = (tbl_lbgrdlivre.getModel().getValueAt(row,0). toString());
          // this.setAlwaysOnTop (false);
     
             try{
                 
                 
                   String sqls="Select path from pathn";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
            if(rs.next()){
             
                String sum=rs.getString("path");
                  
                 String NameFiles=sum;
            
            String NameFile=""+NameFiles+"grand_livre_lb.jrxml";
                 
                 
                 
                JasperDesign jd=JRXmlLoader.load(NameFile);
                // l
               //JasperDesign jd=JRXmlLoader.load("C:\\Users\\Dosh\\Documents\\NetBeansProjects\\intreprisemanagementsystem\\src\\intreprisemanagementsystem\\journaldecaise.jrxml");
              String sql="select*from budget_trans  INNER JOIN projet ON budget_trans.projet=projet.PROJET_NUm WHERE budget_trans.projet='"+buss.getSelectedItem()+"' AND CODE='"+Table_clickS+"'  ORDER BY DATES";
           
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
     public void reportGrdLivre1()
                           
     {
         
       int row= tbl_lbgrdlivre1.getSelectedRow();
          String Table_clickS = (tbl_lbgrdlivre1.getModel().getValueAt(row,0). toString());
              int rowS= jTable3.getSelectedRow();
          String batch = (jTable3.getModel().getValueAt(rowS,0). toString());
          // this.setAlwaysOnTop (false);
     
             try{
                 
                 
                   String sqls="Select path from pathn";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
            if(rs.next()){
             
                String sum=rs.getString("path");
                  
                 String NameFiles=sum;
            
            String NameFile=""+NameFiles+"grand_livre_lb.jrxml";
                 
                 
                 
                JasperDesign jd=JRXmlLoader.load(NameFile);
                // l
               //JasperDesign jd=JRXmlLoader.load("C:\\Users\\Dosh\\Documents\\NetBeansProjects\\intreprisemanagementsystem\\src\\intreprisemanagementsystem\\journaldecaise.jrxml");
              String sql="select*from budget_trans  INNER JOIN projet ON budget_trans.projet=projet.PROJET_NUm WHERE budget_trans.projet='"+buss.getSelectedItem()+"' AND CODE='"+Table_clickS+"' and BATCH='"+batch+"' ORDER BY DATES";
           
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
       public void Cortable(){
     //  String CLASS="Achat  Ordinateur";
       tbl_control.setDefaultRenderer(Object.class, new DefaultTableCellRenderer(){
       @Override
       public Component getTableCellRendererComponent(JTable table,Object value,boolean isSelected,boolean hasFocus,int row,int column){
       JLabel label=(JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
       //************************************************************
  
       Object prevision=table.getValueAt(row,2);
        Object depense=table.getValueAt(row,3);
         Object solde=table.getValueAt(row,4);
         String sold=solde.toString();
String Solde= sold.replace(",", "");
  
  Double a = Double.parseDouble(Solde);
//if(prevision.equals("0.00") ){
//        label.setBackground(java.awt.Color.BLACK);
//       jTable7.setSelectionForeground(java.awt.Color.GREEN);
//       label.setForeground(java.awt.Color.white);
//       }else 
if(prevision.equals("0.00") && depense.equals("0.00")){
        label.setBackground(java.awt.Color.ORANGE);
       tbl_control.setSelectionForeground(java.awt.Color.RED);
       label.setForeground(java.awt.Color.BLUE);

}else if(prevision.equals("0.00")){
        label.setBackground(java.awt.Color.BLACK);
      label.setForeground(java.awt.Color.WHITE);
       tbl_control.setSelectionBackground(java.awt.Color.YELLOW);
       
 }else if(depense.equals("0.00")){
        label.setBackground(java.awt.Color.GREEN);
       tbl_control.setSelectionForeground(java.awt.Color.YELLOW);
       label.setForeground(java.awt.Color.BLACK);


       }else if(a<=0){
        label.setBackground(java.awt.Color.RED);
       tbl_control.setSelectionForeground(java.awt.Color.GREEN);
       label.setForeground(java.awt.Color.BLACK);


       }else{
               label.setBackground(java.awt.Color.WHITE);
       tbl_control.setSelectionForeground(java.awt.Color.GREEN);
        label.setForeground(java.awt.Color.BLACK);
       }
 

      
          return label;
       
       }
       });
       
       }
           public void Cortable_ccb(){
     //  String CLASS="Achat  Ordinateur";
       ccb.setDefaultRenderer(Object.class, new DefaultTableCellRenderer(){
       @Override
       public Component getTableCellRendererComponent(JTable table,Object value,boolean isSelected,boolean hasFocus,int row,int column){
       JLabel label=(JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
       //************************************************************
  
       Object prevision=table.getValueAt(row,2);
        Object depense=table.getValueAt(row,3);
         Object solde=table.getValueAt(row,4);
         String sold=solde.toString();
String Solde= sold.replace(",", "");
  
  Double a = Double.parseDouble(Solde);
//if(prevision.equals("0.00") ){
//        label.setBackground(java.awt.Color.BLACK);
//       jTable7.setSelectionForeground(java.awt.Color.GREEN);
//       label.setForeground(java.awt.Color.white);
//       }else 
if(prevision.equals("0.00") && depense.equals("0.00")){
        label.setBackground(java.awt.Color.ORANGE);
       ccb.setSelectionForeground(java.awt.Color.WHITE);
       label.setForeground(java.awt.Color.BLACK);

}else if(prevision.equals("0.00")){
        label.setBackground(java.awt.Color.BLACK);
       ccb.setSelectionForeground(java.awt.Color.YELLOW);
       label.setForeground(java.awt.Color.WHITE);
       
 }else if(depense.equals("0.00")){
        label.setBackground(java.awt.Color.GREEN);
       ccb.setSelectionForeground(java.awt.Color.YELLOW);
       label.setForeground(java.awt.Color.BLACK);


       }else if(a<=0){
        label.setBackground(java.awt.Color.RED);
       ccb.setSelectionForeground(java.awt.Color.GREEN);
       label.setForeground(java.awt.Color.BLACK);


       }else{
               label.setBackground(java.awt.Color.WHITE);
       ccb.setSelectionForeground(java.awt.Color.GREEN);
        label.setForeground(java.awt.Color.BLACK);
       }
 

      
          return label;
       
       }
       });
       
       }
       
    public void Cortablegrdlb(){
     //  String CLASS="Achat  Ordinateur";
       tbl_grdlivre.setDefaultRenderer(Object.class, new DefaultTableCellRenderer(){
       @Override
       public Component getTableCellRendererComponent(JTable table,Object value,boolean isSelected,boolean hasFocus,int row,int column){
       JLabel label=(JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
       //************************************************************
  
        Object depense=table.getValueAt(row,3);
if(depense.equals("0.00")){
        label.setBackground(java.awt.Color.GREEN);
       tbl_control.setSelectionForeground(java.awt.Color.BLUE);
       label.setForeground(java.awt.Color.BLACK);

       }else{
               label.setBackground(java.awt.Color.WHITE);
       tbl_control.setSelectionForeground(java.awt.Color.GREEN);
        label.setForeground(java.awt.Color.BLACK);
       }
 

      
          return label;
       
       }
       });
       
       }
    
public void Cortablegrdlb1(){
     //  String CLASS="Achat  Ordinateur";
       tbl_grdlivre1.setDefaultRenderer(Object.class, new DefaultTableCellRenderer(){
       @Override
       public Component getTableCellRendererComponent(JTable table,Object value,boolean isSelected,boolean hasFocus,int row,int column){
       JLabel label=(JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
       //************************************************************
  
        Object depense=table.getValueAt(row,4);
      //  Object prevision=table.getValueAt(row,2);
if(depense.equals("0.00")){
        label.setBackground(java.awt.Color.GREEN);
       tbl_control.setSelectionForeground(java.awt.Color.BLUE);
       label.setForeground(java.awt.Color.BLACK);

       }else{
               label.setBackground(java.awt.Color.WHITE);
       tbl_control.setSelectionForeground(java.awt.Color.BLACK);
        label.setForeground(java.awt.Color.BLACK);
       }
 

      
          return label;
       
       }
       });
       
       }
 public void Cortable_ccbs(){
     //  String CLASS="Achat  Ordinateur";
     jTable5.setDefaultRenderer(Object.class, new DefaultTableCellRenderer(){
       @Override
       public Component getTableCellRendererComponent(JTable table,Object value,boolean isSelected,boolean hasFocus,int row,int column){
       JLabel label=(JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
       //************************************************************
  
       Object prevision=table.getValueAt(row,2);
        Object depense=table.getValueAt(row,3);
         Object solde=table.getValueAt(row,4);
         String sold=solde.toString();
String Solde= sold.replace(",", "");
  
  Double a = Double.parseDouble(Solde);
//if(prevision.equals("0.00") ){
//        label.setBackground(java.awt.Color.BLACK);
//       jTable7.setSelectionForeground(java.awt.Color.GREEN);
//       label.setForeground(java.awt.Color.white);
//       }else 
if(prevision.equals("0.00") && depense.equals("0.00")){
        label.setBackground(java.awt.Color.ORANGE);
       jTable5.setSelectionForeground(java.awt.Color.WHITE);
       label.setForeground(java.awt.Color.BLACK);

}else if(prevision.equals("0.00")){
        label.setBackground(java.awt.Color.BLACK);
      jTable5.setSelectionForeground(java.awt.Color.YELLOW);
       label.setForeground(java.awt.Color.WHITE);
       
 }else if(depense.equals("0.00")){
        label.setBackground(java.awt.Color.GREEN);
       jTable5.setSelectionForeground(java.awt.Color.YELLOW);
       label.setForeground(java.awt.Color.BLACK);


       }else if(a<=0){
        label.setBackground(java.awt.Color.RED);
       jTable5.setSelectionForeground(java.awt.Color.GREEN);
       label.setForeground(java.awt.Color.BLACK);


       }else{
               label.setBackground(java.awt.Color.WHITE);
       jTable5.setSelectionForeground(java.awt.Color.GREEN);
        label.setForeground(java.awt.Color.BLACK);
       }
 

      
          return label;
       
       }
       });
       
       }
 
  public void Cortable_ccb1(){
    ccb1.setDefaultRenderer(Object.class, new DefaultTableCellRenderer(){
       @Override
       public Component getTableCellRendererComponent(JTable table,Object value,boolean isSelected,boolean hasFocus,int row,int column){
       JLabel label=(JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
       //************************************************************
  
       Object prevision=table.getValueAt(row,4);
        Object depense=table.getValueAt(row,5);
         Object solde=table.getValueAt(row,4);
         String sold=solde.toString();
String Solde= sold.replace(",", "");
  
  Double a = Double.parseDouble(Solde);
if(prevision.equals("0.00") && depense.equals("0.00")){
        label.setBackground(java.awt.Color.ORANGE);
       ccb1.setSelectionForeground(java.awt.Color.WHITE);
       label.setForeground(java.awt.Color.BLACK);

}else if(prevision.equals("0.00")){
        label.setBackground(java.awt.Color.WHITE);
     ccb1.setSelectionForeground(java.awt.Color.YELLOW);
       label.setForeground(java.awt.Color.BLACK);
       
 }else if(depense.equals("0.00")){
        label.setBackground(java.awt.Color.GREEN);
     ccb1.setSelectionForeground(java.awt.Color.YELLOW);
       label.setForeground(java.awt.Color.BLACK);


       }else if(a<=0){
        label.setBackground(java.awt.Color.RED);
    ccb1.setSelectionForeground(java.awt.Color.GREEN);
       label.setForeground(java.awt.Color.BLACK);


       }else{
               label.setBackground(java.awt.Color.WHITE);
    ccb1.setSelectionForeground(java.awt.Color.GREEN);
        label.setForeground(java.awt.Color.BLACK);
       }
 

      
          return label;
       
       }
       });
       
       }
  
  
   public void Cortable_ccb2(){
    ccb2.setDefaultRenderer(Object.class, new DefaultTableCellRenderer(){
       @Override
       public Component getTableCellRendererComponent(JTable table,Object value,boolean isSelected,boolean hasFocus,int row,int column){
       JLabel label=(JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
       //************************************************************
  
       Object prevision=table.getValueAt(row,4);
        Object depense=table.getValueAt(row,5);
         Object solde=table.getValueAt(row,4);
         String sold=solde.toString();
String Solde= sold.replace(",", "");
  
  Double a = Double.parseDouble(Solde);
if(prevision.equals("0.00") && depense.equals("0.00")){
        label.setBackground(java.awt.Color.ORANGE);
        ccb2.setSelectionForeground(java.awt.Color.WHITE);
       label.setForeground(java.awt.Color.BLACK);

}else if(prevision.equals("0.00")){
        label.setBackground(java.awt.Color.WHITE);
      ccb2.setSelectionForeground(java.awt.Color.YELLOW);
       label.setForeground(java.awt.Color.BLACK);
       
 }else if(depense.equals("0.00")){
        label.setBackground(java.awt.Color.GREEN);
     ccb2.setSelectionForeground(java.awt.Color.YELLOW);
       label.setForeground(java.awt.Color.BLACK);


       }else if(a<=0){
        label.setBackground(java.awt.Color.RED);
     ccb2.setSelectionForeground(java.awt.Color.GREEN);
       label.setForeground(java.awt.Color.BLACK);


       }else{
               label.setBackground(java.awt.Color.WHITE);
     ccb2.setSelectionForeground(java.awt.Color.GREEN);
        label.setForeground(java.awt.Color.BLACK);
       }
 

      
          return label;
       
       }
       });
       
       }
          public void reportBYSTOCK()
     {
         int rowss= jTable3.getSelectedRow();
        String butch = (jTable3.getModel().getValueAt(rowss,0). toString());  
             try{
                 
                 
                   String sqls="Select path from pathn";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
            if(rs.next()){
             
                String sum=rs.getString("path");
                  
                 String NameFiles=sum;
            String NameFile=""+NameFiles+"rapport_projet_1.jrxml";
           
                JasperDesign jd=JRXmlLoader.load(NameFile);
      String sql="select sum(debit),sum(credit),item,items,code,CODE_CAT,CODE_SUB_CAT,BATCH,budget_trans.PROJET,CAT,SUB_CAT,BAYEUR,projet.PROJET as titre,projet.DATEIN,projet.DATEOUT,tete from budget_trans  INNER JOIN  projet_bayeur on budget_trans.PROJET= projet_bayeur.PROJET_NUM   INNER JOIN projet on budget_trans.PROJET=PROJET.PROJET_NUM where budget_trans.PROJET='"+buss.getSelectedItem()+"'  and BATCH='"+butch+"' group by budget_trans.code ORDER BY budget_trans.ID,budget_trans.code ";
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
          
          public void reportconsolidate()
     {
             try{
                 
                 
                   String sqls="Select path from pathn";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
            if(rs.next()){
             
                String sum=rs.getString("path");
                  
                 String NameFiles=sum;
            String NameFile=""+NameFiles+"rapport_projet_1_1.jrxml";
           // String NameFile=""+NameFiles+"depense_projet.jrxml";
                 
                 
                 
                JasperDesign jd=JRXmlLoader.load(NameFile);
                 
       String sql="select sum(debit),sum(credit),item,items,code,CODE_CAT,CODE_SUB_CAT,BATCH,budget_trans.PROJET,CAT,SUB_CAT,BAYEUR,projet.PROJET as titre,projet.DATEIN,projet.DATEOUT,tete from budget_trans  INNER JOIN  projet_bayeur on budget_trans.PROJET= projet_bayeur.PROJET_NUM   INNER JOIN projet on budget_trans.PROJET=PROJET.PROJET_NUM where budget_trans.PROJET='"+buss.getSelectedItem()+"' group by budget_trans.code";
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
               if(budget_shows.title.getText().equals("Livre de dépense cumulé")){
                 for(int i=0;i<tbl_grdlivre.getColumnCount();i++){
                   Cell cell = rowCol.createCell(i);
                   cell.setCellValue(tbl_grdlivre.getColumnName(i));
               }
               
               for(int j=0;j<tbl_grdlivre.getRowCount();j++){
                   Row row = sheet.createRow(j+1);
                   for(int k=0;k<tbl_grdlivre.getColumnCount();k++){
                       Cell cell = row.createCell(k);
                       if(tbl_grdlivre.getValueAt(j, k)!=null){
                           cell.setCellValue(tbl_grdlivre.getValueAt(j, k).toString());
                       }
                   }
               }
               }else if(budget_shows.title.getText().equals("Livre des depenses Previsionel")){
                for(int i=0;i<tbl_grdlivre1.getColumnCount();i++){
                   Cell cell = rowCol.createCell(i);
                   cell.setCellValue(tbl_grdlivre1.getColumnName(i));
               }
               
               for(int j=0;j<tbl_grdlivre1.getRowCount();j++){
                   Row row = sheet.createRow(j+1);
                   for(int k=0;k<tbl_grdlivre1.getColumnCount();k++){
                       Cell cell = row.createCell(k);
                       if(tbl_grdlivre1.getValueAt(j, k)!=null){
                           cell.setCellValue(tbl_grdlivre1.getValueAt(j, k).toString());
                       }
                   }
               }
               }else if(budget_shows.title.getText().equals("Control Budget Previsionel")){
                for(int i=0;i<tbl_control.getColumnCount();i++){
                   Cell cell = rowCol.createCell(i);
                   cell.setCellValue(tbl_control.getColumnName(i));
               }
               
               for(int j=0;j<tbl_control.getRowCount();j++){
                   Row row = sheet.createRow(j+1);
                   for(int k=0;k<tbl_control.getColumnCount();k++){
                       Cell cell = row.createCell(k);
                       if(tbl_control.getValueAt(j, k)!=null){
                           cell.setCellValue(tbl_control.getValueAt(j, k).toString());
                       }
                   }
               }
               }else if(budget_shows.title.getText().equals("Control Budget cumulé")){
                     for(int i=0;i<ccb.getColumnCount();i++){
                   Cell cell = rowCol.createCell(i);
                   cell.setCellValue(ccb.getColumnName(i));
               }
               
               for(int j=0;j<ccb.getRowCount();j++){
                   Row row = sheet.createRow(j+1);
                   for(int k=0;k<ccb.getColumnCount();k++){
                       Cell cell = row.createCell(k);
                       if(ccb.getValueAt(j, k)!=null){
                           cell.setCellValue(ccb.getValueAt(j, k).toString());
                       }
                   }
               }  
               
               
               }else if(budget_shows.title.getText().equals("Rapport consolidé des projets")){
                     for(int i=0;i<jTable5.getColumnCount();i++){
                   Cell cell = rowCol.createCell(i);
                   cell.setCellValue(jTable5.getColumnName(i));
               }
               
               for(int j=0;j<jTable5.getRowCount();j++){
                   Row row = sheet.createRow(j+1);
                   for(int k=0;k<jTable5.getColumnCount();k++){
                       Cell cell = row.createCell(k);
                       if(jTable5.getValueAt(j, k)!=null){
                           cell.setCellValue(jTable5.getValueAt(j, k).toString());
                       }
                   }
               }  
               
               
               }else if(budget_shows.title.getText().equals("Livre de dépense consolidé de prévision")){
                     for(int i=0;i<ccb1.getColumnCount();i++){
                   Cell cell = rowCol.createCell(i);
                   cell.setCellValue(ccb1.getColumnName(i));
               }
               
               for(int j=0;j<ccb1.getRowCount();j++){
                   Row row = sheet.createRow(j+1);
                   for(int k=0;k<ccb1.getColumnCount();k++){
                       Cell cell = row.createCell(k);
                       if(ccb1.getValueAt(j, k)!=null){
                           cell.setCellValue(ccb1.getValueAt(j, k).toString());
                       }
                   }
               }  
               
               
               }else if(budget_shows.title.getText().equals("Livre de dépense Projet")){
                     for(int i=0;i<ccb2.getColumnCount();i++){
                   Cell cell = rowCol.createCell(i);
                   cell.setCellValue(ccb2.getColumnName(i));
               }
               
               for(int j=0;j<ccb2.getRowCount();j++){
                   Row row = sheet.createRow(j+1);
                   for(int k=0;k<ccb2.getColumnCount();k++){
                       Cell cell = row.createCell(k);
                       if(ccb2.getValueAt(j, k)!=null){
                           cell.setCellValue(ccb2.getValueAt(j, k).toString());
                       }
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
    
          public void delete_transction(){
          int indexs[]=tbl_grdlivre1.getSelectedRows();
        for(int i=0; i < indexs.length;i++){
             String nums = (tbl_grdlivre1.getModel().getValueAt(indexs[i],1). toString());
              String bus = buss.getSelectedItem().toString();
            
        
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
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tbl_budget = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbl_dispacting = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable6 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jScrollPane5 = new javax.swing.JScrollPane();
        tbl_control = new javax.swing.JTable();
        jButton4 = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        total_b = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        credit_b = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        pour = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        credit_b1 = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane10 = new javax.swing.JScrollPane();
        jScrollPane11 = new javax.swing.JScrollPane();
        jLabel19 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jTextField9 = new javax.swing.JTextField();
        jButton7 = new javax.swing.JButton();
        jPanel18 = new javax.swing.JPanel();
        jScrollPane16 = new javax.swing.JScrollPane();
        ccb1 = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tbl_grdlivre = new javax.swing.JTable();
        jScrollPane8 = new javax.swing.JScrollPane();
        jLabel4 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        ccb = new javax.swing.JTable();
        jLabel12 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jPanel19 = new javax.swing.JPanel();
        jScrollPane17 = new javax.swing.JScrollPane();
        ccb2 = new javax.swing.JTable();
        jPanel9 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        jScrollPane12 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        jScrollPane13 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPanel16 = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jScrollPane14 = new javax.swing.JScrollPane();
        jTable5 = new javax.swing.JTable();
        jPanel15 = new javax.swing.JPanel();
        jScrollPane15 = new javax.swing.JScrollPane();
        jEditorPane1 = new javax.swing.JEditorPane();
        jPanel17 = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        jTabbedPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jTabbedPane1.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        jTabbedPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane1MouseClicked(evt);
            }
        });

        tbl_budget.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tbl_budget.setGridColor(new java.awt.Color(204, 204, 204));
        tbl_budget.setRowHeight(30);
        tbl_budget.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_budgetMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tbl_budget);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1178, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 526, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Budget", jPanel5);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable4.setRowHeight(30);
        jTable4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable4MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable4);

        tbl_dispacting.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tbl_dispacting.setGridColor(new java.awt.Color(204, 204, 204));
        tbl_dispacting.setRowHeight(30);
        tbl_dispacting.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_dispactingMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbl_dispacting);

        jTable6.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "LB", "ITEM", "PT"
            }
        ));
        jTable6.setGridColor(new java.awt.Color(204, 204, 204));
        jTable6.setRowHeight(30);
        jTable6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable6MouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(jTable6);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText(">>");
        jLabel2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("<<");
        jLabel3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton1.setText("Save");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton3.setText("Del");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 671, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton3)
                        .addGap(0, 0, 0)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(210, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(263, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton3)))
        );

        jTabbedPane1.addTab("Budget dispatching", jPanel1);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

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

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        tbl_control.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tbl_control.setGridColor(new java.awt.Color(204, 204, 204));
        tbl_control.setRowHeight(30);
        tbl_control.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_controlMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tbl_control);

        jButton4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton4.setText("Print");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel15.setText("Total Budget");

        total_b.setEditable(false);
        total_b.setBackground(new java.awt.Color(240, 240, 241));
        total_b.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        total_b.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        total_b.setText("0.00");

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel16.setText("Budget Credit");

        credit_b.setEditable(false);
        credit_b.setBackground(new java.awt.Color(240, 240, 241));
        credit_b.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        credit_b.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        credit_b.setText("0.00");

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel17.setText("Pourcentage");

        pour.setEditable(false);
        pour.setBackground(new java.awt.Color(240, 240, 241));
        pour.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        pour.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pour.setText("0.00");

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel18.setText("Solde/Ecart");

        credit_b1.setEditable(false);
        credit_b1.setBackground(new java.awt.Color(240, 240, 241));
        credit_b1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        credit_b1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        credit_b1.setText("0.00");

        jButton6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton6.setText("Alert");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 1028, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(total_b, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(credit_b, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(credit_b1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pour, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton6)
                .addGap(0, 0, 0)
                .addComponent(jButton4))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 497, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(total_b, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(credit_b, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(credit_b1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(pour, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton4)
                            .addComponent(jButton6))))
                .addGap(0, 0, 0))
        );

        jTabbedPane1.addTab("Budget Control", jPanel2);

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        tbl_lbgrdlivre1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tbl_lbgrdlivre1.setRowHeight(30);
        tbl_lbgrdlivre1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_lbgrdlivre1MouseClicked(evt);
            }
        });
        jScrollPane10.setViewportView(tbl_lbgrdlivre1);

        tbl_grdlivre1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tbl_grdlivre1.setGridColor(new java.awt.Color(204, 204, 204));
        tbl_grdlivre1.setRowHeight(30);
        tbl_grdlivre1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_grdlivre1MouseClicked(evt);
            }
        });
        jScrollPane11.setViewportView(tbl_grdlivre1);

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel19.setText("Total Budget");

        jTextField7.setEditable(false);
        jTextField7.setBackground(new java.awt.Color(240, 240, 241));
        jTextField7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jTextField7.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField7.setText("0.00");

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel20.setText("Budget Credit");

        jTextField8.setEditable(false);
        jTextField8.setBackground(new java.awt.Color(240, 240, 241));
        jTextField8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jTextField8.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField8.setText("0.00");

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel21.setText("Pourcentage");

        jTextField9.setEditable(false);
        jTextField9.setBackground(new java.awt.Color(240, 240, 241));
        jTextField9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jTextField9.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField9.setText("0.00");

        jButton7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton7.setText("Print");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane11, javax.swing.GroupLayout.DEFAULT_SIZE, 1065, Short.MAX_VALUE))
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton7))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane11, javax.swing.GroupLayout.DEFAULT_SIZE, 499, Short.MAX_VALUE)
                    .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton7)))
                    .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jTabbedPane1.addTab("Livre de dépense prévisionnels", jPanel8);

        ccb1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        ccb1.setGridColor(new java.awt.Color(204, 204, 204));
        ccb1.setRowHeight(30);
        ccb1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ccb1MouseClicked(evt);
            }
        });
        jScrollPane16.setViewportView(ccb1);

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane16, javax.swing.GroupLayout.DEFAULT_SIZE, 1178, Short.MAX_VALUE)
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane16, javax.swing.GroupLayout.DEFAULT_SIZE, 526, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Livre de dépense consolidé de prévision", jPanel18);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        tbl_grdlivre.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tbl_grdlivre.setGridColor(new java.awt.Color(204, 204, 204));
        tbl_grdlivre.setRowHeight(30);
        tbl_grdlivre.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_grdlivreMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(tbl_grdlivre);

        tbl_lbgrdlivre.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tbl_lbgrdlivre.setRowHeight(30);
        tbl_lbgrdlivre.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_lbgrdlivreMouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(tbl_lbgrdlivre);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("Total Budget");

        jTextField1.setEditable(false);
        jTextField1.setBackground(new java.awt.Color(240, 240, 241));
        jTextField1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jTextField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField1.setText("0.00");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setText("Budget Credit");

        jTextField2.setEditable(false);
        jTextField2.setBackground(new java.awt.Color(240, 240, 241));
        jTextField2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jTextField2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField2.setText("0.00");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel8.setText("Pourcentage");

        jTextField3.setEditable(false);
        jTextField3.setBackground(new java.awt.Color(240, 240, 241));
        jTextField3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jTextField3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField3.setText("0.00");

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton2.setText("Print");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 1065, Short.MAX_VALUE))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 499, Short.MAX_VALUE)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2)))
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jTabbedPane1.addTab("Livre de dépense cumulé", jPanel6);

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        ccb.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        ccb.setGridColor(new java.awt.Color(204, 204, 204));
        ccb.setRowHeight(30);
        ccb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ccbMouseClicked(evt);
            }
        });
        jScrollPane9.setViewportView(ccb);

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel12.setText("Total Budget");

        jTextField4.setEditable(false);
        jTextField4.setBackground(new java.awt.Color(240, 240, 241));
        jTextField4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jTextField4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField4.setText("0.00");

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel13.setText("Budget Credit");

        jTextField5.setEditable(false);
        jTextField5.setBackground(new java.awt.Color(240, 240, 241));
        jTextField5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jTextField5.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField5.setText("0.00");

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel14.setText("Pourcentage");

        jTextField6.setEditable(false);
        jTextField6.setBackground(new java.awt.Color(240, 240, 241));
        jTextField6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jTextField6.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField6.setText("0.00");

        jButton5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton5.setText("Print");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 1178, Short.MAX_VALUE)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 497, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton5)))
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jTabbedPane1.addTab("Control cumulate Budget", jPanel7);

        ccb2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        ccb2.setGridColor(new java.awt.Color(204, 204, 204));
        ccb2.setRowHeight(30);
        ccb2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ccb2MouseClicked(evt);
            }
        });
        jScrollPane17.setViewportView(ccb2);

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane17, javax.swing.GroupLayout.DEFAULT_SIZE, 1178, Short.MAX_VALUE)
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane17, javax.swing.GroupLayout.DEFAULT_SIZE, 526, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Livre de dépense Projet", jPanel19);

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel24.setText("Select Projects");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel24)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel24)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Projects"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setRowHeight(30);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane12.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(0).setHeaderValue("Projects");
        }

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 345, Short.MAX_VALUE)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
        );

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setText(">>");
        jLabel25.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel25.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel25MouseClicked(evt);
            }
        });

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel26.setText("<<");
        jLabel26.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel26.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel26MouseClicked(evt);
            }
        });

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));
        jPanel13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel27.setText("Projects");

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel27)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel27)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "..."
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable2.setRowHeight(30);
        jScrollPane13.setViewportView(jTable2);
        if (jTable2.getColumnModel().getColumnCount() > 0) {
            jTable2.getColumnModel().getColumn(0).setResizable(false);
            jTable2.getColumnModel().getColumn(0).setHeaderValue("Projects");
        }

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));
        jPanel16.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jDateChooser2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jDateChooser2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jDateChooser3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jDateChooser3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_Related_Companies_16px.png"))); // NOI18N
        jLabel30.setText("Compilation rapport");
        jLabel30.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel30.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel30MouseClicked(evt);
            }
        });

        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel29.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel29.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel29MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jDateChooser2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jDateChooser3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jDateChooser3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jTable5.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6"
            }
        ));
        jTable5.setRowHeight(30);
        jTable5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable5MouseClicked(evt);
            }
        });
        jScrollPane14.setViewportView(jTable5);

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));
        jPanel15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jEditorPane1.setEditable(false);
        jEditorPane1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jScrollPane15.setViewportView(jEditorPane1);

        jLabel31.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel31.setText("Array[]");

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel31)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel31)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane15, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane15)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jScrollPane14)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(84, 84, 84))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel15, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(jScrollPane14, javax.swing.GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
                .addGap(13, 13, 13))
        );

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Rapport consolidé des projets", jPanel9);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/database.png"))); // NOI18N
        jLabel1.setText("Add Budget  Bacths");
        jLabel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_Microsoft_Excel_16px.png"))); // NOI18N
        jLabel7.setText("Import Excel");
        jLabel7.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
        });

        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel9MouseClicked(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_print_24px.png"))); // NOI18N
        jLabel10.setText("Print Budget");
        jLabel10.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel10MouseClicked(evt);
            }
        });

        jDateChooser1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jDateChooser1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/cancel.png"))); // NOI18N
        jLabel11.setText("Delete Budget");
        jLabel11.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel11MouseClicked(evt);
            }
        });

        jComboBox1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "LB", "SUB GRD LB", "GRD LB" }));
        jComboBox1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_Related_Companies_16px.png"))); // NOI18N
        jLabel6.setText("Extourner");
        jLabel6.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_Microsoft_Excel_16px.png"))); // NOI18N
        jLabel22.setText("Export Excel");
        jLabel22.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel22.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel22MouseClicked(evt);
            }
        });

        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/journals/icons8_Delete_Table_24px.png"))); // NOI18N
        jLabel23.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel23.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel23MouseClicked(evt);
            }
        });

        buss.setLabeText("Select One Project");
        buss.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                bussPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addComponent(buss, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel23)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jComboBox1)
            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(buss, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jTabbedPane1))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
try{
            String sql="select ACESS from user_write where NAME='"+Home_page.home.user.getText()+"' and CHAMP='Save Budget'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
          String  caisse= rs.getString("ACESS");
          if(caisse.equals("Yes")){
         bacth_budget m = new bacth_budget();
m.show();
  m. setDefaultCloseOperation(DISPOSE_ON_CLOSE); 
          }else{
       JOptionPane.showMessageDialog(null,"Seul le financier(e) est autorisé(e)","Avertissement",JOptionPane.WARNING_MESSAGE);
          }
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex); }
       
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
try{
            String sql="select ACESS from user_write where NAME='"+Home_page.home.user.getText()+"' and CHAMP='Save Budget'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
          String  caisse= rs.getString("ACESS");
          if(caisse.equals("Yes")){
      if(jLabel9.getText().equals("")){
importexcel();
}else{
SaveBudget();
}
          }else{
       JOptionPane.showMessageDialog(null,"Seul le financier(e) est autorisé(e)","Avertissement",JOptionPane.WARNING_MESSAGE);
          }
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex); }



    }//GEN-LAST:event_jLabel7MouseClicked

    private void tbl_lbgrdlivreMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_lbgrdlivreMouseClicked
        //jTable10.setDefaultRenderer(Object.class,new PINTAR_control());
        int rowss= tbl_lbgrdlivre.getSelectedRow();
        // String rows =jTable1.getName()
        String butch = (tbl_lbgrdlivre.getModel().getValueAt(rowss,0). toString());
        
         if(jComboBox1.getSelectedItem().equals("LB")){
         try{
           // String sqls="SELECT   `DATES`,`ITEM`, format(sum(DEBIT),2) AS PREVISIONS,format(sum(Credit),2) AS DEPENSES,(SELECT sum(DEBIT)FROM budget_trans where projet='"+buss.getSelectedItem().toString()+"' and code='"+butch+"')-sum(CREDIT) AS SOLD  FROM budget_trans where projet='"+buss.getSelectedItem().toString()+"' and CODE='"+butch+"' group by dates,item ";
             String sqls="SELECT   `DATES`,`ITEM`, format(sum(DEBIT),2) AS PREVISIONS,format(sum(Credit),2) AS DEPENSES  FROM budget_trans where projet='"+buss.getSelectedItem().toString()+"' and CODE='"+butch+"' group by dates,item ";
            pst = con.prepareStatement(sqls);
            rs= pst.executeQuery();

            tbl_grdlivre.setModel(DbUtils.resultSetToTableModel(rs));
            DefaultTableCellRenderer centre =new DefaultTableCellRenderer();
            centre.setHorizontalAlignment(JLabel.CENTER);
            TableColumn col0=tbl_grdlivre.getColumnModel().getColumn(0);
            //jTable9.getColumnModel().getColumn(0).setCellRenderer(centre);
            TableColumn col1=tbl_grdlivre.getColumnModel().getColumn(1);
            //jTable9.getColumnModel().getColumn(1).setCellRenderer(centre);
            TableColumn col2=tbl_grdlivre.getColumnModel().getColumn(2);
          //  jTable9.getColumnModel().getColumn(2).setCellRenderer(centre);
            TableColumn col3=tbl_grdlivre.getColumnModel().getColumn(3);
           // TableColumn col4=tbl_grdlivre.getColumnModel().getColumn(4);
//              TableColumn col5=jTable9.getColumnModel().getColumn(5);
           // jTable9.getColumnModel().getColumn(3).setCellRenderer(centre);
         
            col0.setPreferredWidth(30);
            col1.setPreferredWidth(950);
            col2.setPreferredWidth(50);
            col3.setPreferredWidth(50);
           // col4.setPreferredWidth(50);
//            col5.setPreferredWidth(50);
           

        }catch(SQLException ex ){
            JOptionPane.showMessageDialog(null, ex);}
        NumberFormat nf = new DecimalFormat("#,###.##");
        try{
            String sql="SELECT sum(DEBIT),sum(CREDIT) FROM budget_trans where projet='"+buss.getSelectedItem().toString()+"' and code='"+butch+"'";
            pst = con.prepareStatement(sql);rs=pst.executeQuery();
            while(rs.next()){
                Double PT=rs.getDouble("sum(DEBIT)");
                Double PTC=rs.getDouble("sum(CREDIT)");
                Double POUR= (PTC*100)/PT;
                String fn = nf.format(PT);
                String fn1 = nf.format(PTC);
                String fn2 = nf.format(POUR);

                jTextField1.setText(fn);
                jTextField2.setText(fn1);
                jTextField3.setText(fn2);
            }
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex);
        }  
        }if(jComboBox1.getSelectedItem().equals("SUB GRD LB")){
         try{
            String sqls="SELECT   `DATES`,`ITEM`, format(sum(DEBIT),2) AS PREVISIONS,format(sum(Credit),2) AS DEPENSES   FROM budget_trans where projet='"+buss.getSelectedItem().toString()+"' and CODE_SUB_CAT='"+butch+"' group by dates,item ";
            pst = con.prepareStatement(sqls);
            rs= pst.executeQuery();

            tbl_grdlivre.setModel(DbUtils.resultSetToTableModel(rs));
            DefaultTableCellRenderer centre =new DefaultTableCellRenderer();
            centre.setHorizontalAlignment(JLabel.CENTER);
            TableColumn col0=tbl_grdlivre.getColumnModel().getColumn(0);
            //jTable9.getColumnModel().getColumn(0).setCellRenderer(centre);
            TableColumn col1=tbl_grdlivre.getColumnModel().getColumn(1);
            //jTable9.getColumnModel().getColumn(1).setCellRenderer(centre);
            TableColumn col2=tbl_grdlivre.getColumnModel().getColumn(2);
          //  jTable9.getColumnModel().getColumn(2).setCellRenderer(centre);
            TableColumn col3=tbl_grdlivre.getColumnModel().getColumn(3);
//             TableColumn col4=jTable9.getColumnModel().getColumn(4);
//              TableColumn col5=jTable9.getColumnModel().getColumn(5);
           // jTable9.getColumnModel().getColumn(3).setCellRenderer(centre);
         
            col0.setPreferredWidth(30);
            col1.setPreferredWidth(950);
            col2.setPreferredWidth(50);
            col3.setPreferredWidth(50);
//              col4.setPreferredWidth(50);
//            col5.setPreferredWidth(50);
           

        }catch(SQLException ex ){
            JOptionPane.showMessageDialog(null, ex);}
        NumberFormat nf = new DecimalFormat("#,###.##");
        try{
            String sql="SELECT sum(DEBIT),sum(CREDIT) FROM budget_trans where projet='"+buss.getSelectedItem().toString()+"' and CODE_SUB_CAT='"+butch+"'";
            pst = con.prepareStatement(sql);rs=pst.executeQuery();
            while(rs.next()){
                Double PT=rs.getDouble("sum(DEBIT)");
                Double PTC=rs.getDouble("sum(CREDIT)");
                Double POUR= (PTC*100)/PT;
                String fn = nf.format(PT);
                String fn1 = nf.format(PTC);
                String fn2 = nf.format(POUR);

                jTextField1.setText(fn);
                jTextField2.setText(fn1);
                jTextField3.setText(fn2);
            }
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex);
        }  
        }else if(jComboBox1.getSelectedItem().equals("GRD LB")){
         try{
            String sqls="SELECT   `DATES`,`ITEM`, format(sum(DEBIT),2) AS PREVISIONS,format(sum(Credit),2) AS DEPENSES   FROM budget_trans where projet='"+buss.getSelectedItem().toString()+"' and CODE_CAT='"+butch+"' group by dates,item ";
            pst = con.prepareStatement(sqls);
            rs= pst.executeQuery();

            tbl_grdlivre.setModel(DbUtils.resultSetToTableModel(rs));
            DefaultTableCellRenderer centre =new DefaultTableCellRenderer();
            centre.setHorizontalAlignment(JLabel.CENTER);
            TableColumn col0=tbl_grdlivre.getColumnModel().getColumn(0);
            TableColumn col1=tbl_grdlivre.getColumnModel().getColumn(1);
            TableColumn col2=tbl_grdlivre.getColumnModel().getColumn(2);
            TableColumn col3=tbl_grdlivre.getColumnModel().getColumn(3);
         
            col0.setPreferredWidth(30);
            col1.setPreferredWidth(950);
            col2.setPreferredWidth(50);
            col3.setPreferredWidth(50);
           

        }catch(SQLException ex ){
            JOptionPane.showMessageDialog(null, ex);}
        NumberFormat nf = new DecimalFormat("#,###.##");
        try{
            String sql="SELECT sum(DEBIT),sum(CREDIT) FROM budget_trans where projet='"+buss.getSelectedItem().toString()+"' and CODE_CAT='"+butch+"'";
            pst = con.prepareStatement(sql);rs=pst.executeQuery();
            while(rs.next()){
                Double PT=rs.getDouble("sum(DEBIT)");
                Double PTC=rs.getDouble("sum(CREDIT)");
                Double POUR= (PTC*100)/PT;
                String fn = nf.format(PT);
                String fn1 = nf.format(PTC);
                String fn2 = nf.format(POUR);

                jTextField1.setText(fn);
                jTextField2.setText(fn1);
                jTextField3.setText(fn2);
            }
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex);
        }  
        }
       
        Cortablegrdlb();
 budget_shows.title.setText("Livre de dépense cumulé");// TODO add your handling code here:
    }//GEN-LAST:event_tbl_lbgrdlivreMouseClicked

    private void tbl_grdlivreMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_grdlivreMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tbl_grdlivreMouseClicked

    private void tbl_controlMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_controlMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tbl_controlMouseClicked

    private void jTable3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable3MouseClicked
     int rowss= jTable3.getSelectedRow();
        String butch = (jTable3.getModel().getValueAt(rowss,0). toString());
      //  JButton btn = new JButton ();
         if(jComboBox1.getSelectedItem().equals("LB")){
         try{
            String sqls="SELECT  CODE, ITEMS as 'DEPENSES ESTIMEES', format(sum(`DEBIT`),2) AS PREVISION,format(sum(CREDIT),2) AS DEPENSE,format((sum(DEBIT)-sum(CREDIT)),2) AS SOLDE,format(((sum(CREDIT))*100)/sum(DEBIT),2) AS '_%_' FROM budget_trans where projet='"+buss.getSelectedItem().toString()+"' and BATCH='"+butch+"' group by code ORDER BY code,ID";
            pst = con.prepareStatement(sqls);
            rs= pst.executeQuery();

            tbl_control.setModel(DbUtils.resultSetToTableModel(rs));
            DefaultTableCellRenderer centre =new DefaultTableCellRenderer();
            centre.setHorizontalAlignment(JLabel.CENTER);
            TableColumn col0=tbl_control.getColumnModel().getColumn(0);
            TableColumn col1=tbl_control.getColumnModel().getColumn(1);
            TableColumn col2=tbl_control.getColumnModel().getColumn(2);
            TableColumn col3=tbl_control.getColumnModel().getColumn(3);
            TableColumn col4=tbl_control.getColumnModel().getColumn(4);
            TableColumn col5=tbl_control.getColumnModel().getColumn(5);

            col0.setPreferredWidth(30);
            col1.setPreferredWidth(700);
            col2.setPreferredWidth(30);
            col3.setPreferredWidth(30);
            col4.setPreferredWidth(30);
            col5.setPreferredWidth(30);
            //

        }catch(SQLException ex ){
            JOptionPane.showMessageDialog(null, ex);}  
    //   String sqls="SELECT   DATES,NUM,ITEM,format(sum(DEBIT),2) AS PREVISIONS,format(sum(Credit),2) AS DEPENSES  FROM budget_trans where projet='"+buss.getSelectedItem().toString()+"' and CODE='"+butch+"' and BATCH='"+batch+"' group by dates,item ";
          try{
            String sqls="SELECT  DATES,NUM,CODE, ITEM, format(sum(`DEBIT`),2) AS PREVISION,format(sum(CREDIT),2) AS DEPENSE FROM budget_trans where projet='"+buss.getSelectedItem().toString()+"' and BATCH='"+butch+"' group by dates,item ORDER BY code,ID";
            pst = con.prepareStatement(sqls);
            rs= pst.executeQuery();

            ccb1.setModel(DbUtils.resultSetToTableModel(rs));
            DefaultTableCellRenderer centre =new DefaultTableCellRenderer();
            centre.setHorizontalAlignment(JLabel.CENTER);
            TableColumn col0=ccb1.getColumnModel().getColumn(0);
            TableColumn col1=ccb1.getColumnModel().getColumn(1);
            TableColumn col2=ccb1.getColumnModel().getColumn(2);
            TableColumn col3=ccb1.getColumnModel().getColumn(3);
            TableColumn col4=ccb1.getColumnModel().getColumn(4);
            TableColumn col5=ccb1.getColumnModel().getColumn(5);

            col0.setPreferredWidth(30);
            col1.setPreferredWidth(30);
            col2.setPreferredWidth(30);
            col3.setPreferredWidth(700);
            col4.setPreferredWidth(30);
            col5.setPreferredWidth(30);
            //

        }catch(SQLException ex ){
            JOptionPane.showMessageDialog(null, ex);}  
        }if(jComboBox1.getSelectedItem().equals("SUB GRD LB")){
         try{
            String sqls="SELECT  CODE_SUB_CAT AS LB, SUB_CAT as 'DEPENSES ESTIMEES', format(sum(`DEBIT`),2) AS PREVISION,format(sum(CREDIT),2) AS DEPENSE,format((sum(DEBIT)-sum(CREDIT)),2) AS SOLDE,format(((sum(CREDIT))*100)/sum(DEBIT),2) AS '_%_' FROM budget_trans where projet='"+buss.getSelectedItem().toString()+"' and BATCH='"+butch+"' group by CODE_SUB_CAT ORDER BY ID";
            pst = con.prepareStatement(sqls);
            rs= pst.executeQuery();

            tbl_control.setModel(DbUtils.resultSetToTableModel(rs));
            DefaultTableCellRenderer centre =new DefaultTableCellRenderer();
            centre.setHorizontalAlignment(JLabel.CENTER);
            TableColumn col0=tbl_control.getColumnModel().getColumn(0);
            TableColumn col1=tbl_control.getColumnModel().getColumn(1);
            TableColumn col2=tbl_control.getColumnModel().getColumn(2);
            TableColumn col3=tbl_control.getColumnModel().getColumn(3);
            TableColumn col4=tbl_control.getColumnModel().getColumn(4);
            TableColumn col5=tbl_control.getColumnModel().getColumn(5);

            col0.setPreferredWidth(30);
            col1.setPreferredWidth(700);
            col2.setPreferredWidth(30);
            col3.setPreferredWidth(30);
            col4.setPreferredWidth(30);
            col5.setPreferredWidth(30);
            //

        }catch(SQLException ex ){
            JOptionPane.showMessageDialog(null, ex);}  
        }else if(jComboBox1.getSelectedItem().equals("GRD LB")){
         try{
            String sqls="SELECT  CODE_CAT AS LB,CAT  as 'DEPENSES ESTIMEES', format(sum(`DEBIT`),2) AS PREVISION,format(sum(CREDIT),2) AS DEPENSE,format((sum(DEBIT)-sum(CREDIT)),2) AS SOLDE,format(((sum(CREDIT))*100)/sum(DEBIT),2) AS '_%_' FROM budget_trans where projet='"+buss.getSelectedItem().toString()+"' and BATCH='"+butch+"' group by CODE_CAT ORDER BY ID";
            pst = con.prepareStatement(sqls);
            rs= pst.executeQuery();

            tbl_control.setModel(DbUtils.resultSetToTableModel(rs));
            DefaultTableCellRenderer centre =new DefaultTableCellRenderer();
            centre.setHorizontalAlignment(JLabel.CENTER);
            TableColumn col0=tbl_control.getColumnModel().getColumn(0);
            TableColumn col1=tbl_control.getColumnModel().getColumn(1);
            TableColumn col2=tbl_control.getColumnModel().getColumn(2);
            TableColumn col3=tbl_control.getColumnModel().getColumn(3);
            TableColumn col4=tbl_control.getColumnModel().getColumn(4);
            TableColumn col5=tbl_control.getColumnModel().getColumn(5);

            col0.setPreferredWidth(30);
            col1.setPreferredWidth(700);
            col2.setPreferredWidth(30);
            col3.setPreferredWidth(30);
            col4.setPreferredWidth(30);
            col5.setPreferredWidth(30);
            //

        }catch(SQLException ex ){
            JOptionPane.showMessageDialog(null, ex);}  
        }
         
 Cortable(); 
 Cortable_ccb1();
  NumberFormat nf = new DecimalFormat("#,###.##");
        try{
            String sql="SELECT sum(DEBIT),sum(CREDIT) FROM budget_trans where projet='"+buss.getSelectedItem().toString()+"' and BATCH='"+butch+"'";
            pst = con.prepareStatement(sql);rs=pst.executeQuery();
            while(rs.next()){
                Double PT=rs.getDouble("sum(DEBIT)");
                Double PTC=rs.getDouble("sum(CREDIT)");
                Double SLD=PT-PTC;
                Double POUR= (PTC*100)/PT;
                String fn = nf.format(PT);
                String fn1 = nf.format(PTC);
                String fn2 = nf.format(POUR);
                String fn3 = nf.format(SLD);

                total_b.setText(fn);
                credit_b.setText(fn1);
                pour.setText(fn2);
                credit_b1.setText(fn3);
            }
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex);
        } 
 try{
            String sqls="SELECT  CODE as LB FROM budget_trans where projet='"+buss.getSelectedItem().toString()+"' and BATCH='"+butch+"' group by code ORDER BY code,ID";
            pst = con.prepareStatement(sqls);
            rs= pst.executeQuery();

           tbl_lbgrdlivre1.setModel(DbUtils.resultSetToTableModel(rs));
            DefaultTableCellRenderer centre =new DefaultTableCellRenderer();
            centre.setHorizontalAlignment(JLabel.CENTER);
//            TableColumn col0=tbl_control.getColumnModel().getColumn(0);
//            TableColumn col1=tbl_control.getColumnModel().getColumn(1);
//            TableColumn col2=tbl_control.getColumnModel().getColumn(2);
//            TableColumn col3=tbl_control.getColumnModel().getColumn(3);
//            TableColumn col4=tbl_control.getColumnModel().getColumn(4);
//            TableColumn col5=tbl_control.getColumnModel().getColumn(5);
//
//            col0.setPreferredWidth(30);
//            col1.setPreferredWidth(700);
//            col2.setPreferredWidth(30);
//            col3.setPreferredWidth(30);
//            col4.setPreferredWidth(30);
//            col5.setPreferredWidth(30);
//            //

        }catch(SQLException ex ){
            JOptionPane.showMessageDialog(null, ex);}  
 budget_shows.title.setText("Control Budget Previsionel");
// TODO add your handling code here:
    }//GEN-LAST:event_jTable3MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
 try{
            String sql="select ACESS from user_write where NAME='"+Home_page.home.user.getText()+"' and CHAMP='Save Budget'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
         if(rs.next()){
          String  caisse= rs.getString("ACESS");
          if(caisse.equals("Yes")){
       saveselected();
       
          }else{
       JOptionPane.showMessageDialog(null,"Seul le financier(e) est autorisé(e)","Avertissement",JOptionPane.WARNING_MESSAGE);
          }
            }
            }catch(Exception ex){
          
          JOptionPane.showMessageDialog(null, ex); }//save();
 
selectTable();

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
try{
            String sql="select ACESS from user_write where NAME='"+Home_page.home.user.getText()+"' and CHAMP='Save Budget'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
          String  caisse= rs.getString("ACESS");
          if(caisse.equals("Yes")){
        selectontablemoin(); 
          }else{
       JOptionPane.showMessageDialog(null,"Seul le financier(e) est autorisé(e)","Avertissement",JOptionPane.WARNING_MESSAGE);
          }
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex); }
       
              // TODO add your handling code here:
    }//GEN-LAST:event_jLabel3MouseClicked

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
try{
            String sql="select ACESS from user_write where NAME='"+Home_page.home.user.getText()+"' and CHAMP='Save Budget'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
          String  caisse= rs.getString("ACESS");
          if(caisse.equals("Yes")){
       selectontable(); 
          }else{
       JOptionPane.showMessageDialog(null,"Seul le financier(e) est autorisé(e)","Avertissement",JOptionPane.WARNING_MESSAGE);
          }
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex); }
        
               // TODO add your handling code here:
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jTable6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable6MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable6MouseClicked

    private void tbl_dispactingMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_dispactingMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tbl_dispactingMouseClicked

    private void jTable4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable4MouseClicked
        int rowss= jTable4.getSelectedRow();
        // String rows =jTable1.getName()
        String butch = (jTable4.getModel().getValueAt(rowss,0). toString());
        if(jComboBox1.getSelectedItem().equals("LB")){
         try{
            String sqls="SELECT  `CODE`, `ITEM`, `DEBIT` FROM budget_trans where projet='"+buss.getSelectedItem().toString()+"' and BATCH='"+butch+"' AND CREDIT='0.00' GROUP BY CODE ORDER BY ID";
            pst = con.prepareStatement(sqls);
            rs= pst.executeQuery();

            jTable6.setModel(DbUtils.resultSetToTableModel(rs));
            DefaultTableCellRenderer centre =new DefaultTableCellRenderer();
            centre.setHorizontalAlignment(JLabel.CENTER);
            TableColumn col0=jTable6.getColumnModel().getColumn(0);
            jTable6.getColumnModel().getColumn(0).setCellRenderer(centre);
            TableColumn col1=jTable6.getColumnModel().getColumn(1);
            TableColumn col2=jTable6.getColumnModel().getColumn(2);
            jTable6.getColumnModel().getColumn(2).setCellRenderer(centre);
            //        TableColumn col3=jTable3.getColumnModel().getColumn(3);
            // TableColumn col4=jTable1.getColumnModel().getColumn(4);

            col0.setPreferredWidth(50);
            col1.setPreferredWidth(400);
            col2.setPreferredWidth(50);
            //       col3.setPreferredWidth(50);
            // col4.setPreferredWidth(50);
            //

        }catch(SQLException ex ){
            JOptionPane.showMessageDialog(null, ex);}   
        }if(jComboBox1.getSelectedItem().equals("SUB GRD LB")){
         try{
            String sqls="SELECT  `CODE_SUB_CAT` AS LB, SUB_CAT AS ITEM, `DEBIT` FROM budget_trans where projet='"+buss.getSelectedItem().toString()+"' and BATCH='"+butch+"' AND CREDIT='0.00' GROUP BY CODE_SUB_CAT ODER BY ID";
            pst = con.prepareStatement(sqls);
            rs= pst.executeQuery();

            jTable6.setModel(DbUtils.resultSetToTableModel(rs));
            DefaultTableCellRenderer centre =new DefaultTableCellRenderer();
            centre.setHorizontalAlignment(JLabel.CENTER);
            TableColumn col0=jTable6.getColumnModel().getColumn(0);
            jTable6.getColumnModel().getColumn(0).setCellRenderer(centre);
            TableColumn col1=jTable6.getColumnModel().getColumn(1);
            TableColumn col2=jTable6.getColumnModel().getColumn(2);
            jTable6.getColumnModel().getColumn(2).setCellRenderer(centre);
            //        TableColumn col3=jTable3.getColumnModel().getColumn(3);
            // TableColumn col4=jTable1.getColumnModel().getColumn(4);

            col0.setPreferredWidth(50);
            col1.setPreferredWidth(400);
            col2.setPreferredWidth(50);
            //       col3.setPreferredWidth(50);
            // col4.setPreferredWidth(50);
            //

        }catch(SQLException ex ){
            JOptionPane.showMessageDialog(null, ex);}   
        }else if(jComboBox1.getSelectedItem().equals("GRD LB")){

         try{
            String sqls="SELECT  `CODE_CAT` AS LB, CAT AS ITEM, `DEBIT` FROM budget_trans where projet='"+buss.getSelectedItem().toString()+"' and BATCH='"+butch+"' AND CREDIT='0.00' GROUP BY CODE_CAT ORDER BY ID";
            pst = con.prepareStatement(sqls);
            rs= pst.executeQuery();

            jTable6.setModel(DbUtils.resultSetToTableModel(rs));
            DefaultTableCellRenderer centre =new DefaultTableCellRenderer();
            centre.setHorizontalAlignment(JLabel.CENTER);
            TableColumn col0=jTable6.getColumnModel().getColumn(0);
            jTable6.getColumnModel().getColumn(0).setCellRenderer(centre);
            TableColumn col1=jTable6.getColumnModel().getColumn(1);
            TableColumn col2=jTable6.getColumnModel().getColumn(2);
            jTable6.getColumnModel().getColumn(2).setCellRenderer(centre);
            //        TableColumn col3=jTable3.getColumnModel().getColumn(3);
            // TableColumn col4=jTable1.getColumnModel().getColumn(4);

            col0.setPreferredWidth(50);
            col1.setPreferredWidth(400);
            col2.setPreferredWidth(50);
            //       col3.setPreferredWidth(50);
            // col4.setPreferredWidth(50);
            //

        }catch(SQLException ex ){
            JOptionPane.showMessageDialog(null, ex);}   
        }
            // TODO add your handling code here:
    }//GEN-LAST:event_jTable4MouseClicked

    private void tbl_budgetMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_budgetMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tbl_budgetMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
reportGrdLivre();      // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jLabel10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel10MouseClicked
report();        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel10MouseClicked

    private void jLabel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseClicked
if(jLabel9.getText().equals("")){

}else{
jLabel9.setText("");
}     // TODO add your handling code here:
    }//GEN-LAST:event_jLabel9MouseClicked

    private void jLabel11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseClicked
try{
            String sql="select ACESS from user_write where NAME='"+Home_page.home.user.getText()+"' and CHAMP='Save Budget'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
          String  caisse= rs.getString("ACESS");
          if(caisse.equals("Yes")){
        try{
        String sqls = "DELETE FROM budget_code WHERE CAT=?";
        
         pst = con.prepareStatement(sqls);
         pst.setString(1,buss.getSelectedItem().toString());
         pst.executeUpdate();
        // JOptionPane.showMessageDialog(null,"delete");
              
     }catch(SQLException | HeadlessException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }   
  try{
        String sqlss = "DELETE FROM budget WHERE PROJECT=?";
        
         pst = con.prepareStatement(sqlss);
         pst.setString(1,buss.getSelectedItem().toString());
         pst.executeUpdate();
         JOptionPane.showMessageDialog(null,"Budget deleted");
              
     }catch(SQLException | HeadlessException ex ){
         JOptionPane.showMessageDialog(null,ex);
     } 
          }else{
       JOptionPane.showMessageDialog(null,"Seul le financier(e) est autorisé(e)","Avertissement",JOptionPane.WARNING_MESSAGE);
          }
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex); }
          // TODO add your handling code here:        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel11MouseClicked

    private void ccbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ccbMouseClicked
 budget_shows.title.setText("Control Budget cumulé");        // TODO add your handling code here:
    }//GEN-LAST:event_ccbMouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
try{
            String sql="select ACESS from user_write where NAME='"+Home_page.home.user.getText()+"' and CHAMP='Save Budget'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
          String  caisse= rs.getString("ACESS");
          if(caisse.equals("Yes")){
             Deleteselected();   
selectTable();   
          }else{
       JOptionPane.showMessageDialog(null,"Seul le financier(e) est autorisé(e)","Avertissement",JOptionPane.WARNING_MESSAGE);
          }
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex); }
      // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
reportBYSTOCK();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
reportconsolidate();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
 int rowss= jTable3.getSelectedRow();
        String butch = (jTable3.getModel().getValueAt(rowss,0). toString());
        MessageFormat header = new MessageFormat("Projet: "+buss.getSelectedItem()+"  "+butch);   
 MessageFormat footer = new MessageFormat("Page{0,number,integer}"); 
 try{
tbl_control.print(JTable.PrintMode.FIT_WIDTH,header,footer);
 }catch(PrinterException ex){
          JOptionPane.showMessageDialog(null, ex);    
        }        // TODO add your handling code here:
    }//GEN-LAST:event_jButton6ActionPerformed

    private void tbl_lbgrdlivre1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_lbgrdlivre1MouseClicked
    //jTable10.setDefaultRenderer(Object.class,new PINTAR_control());
        int rowss= tbl_lbgrdlivre1.getSelectedRow();
        int rowsss= jTable3.getSelectedRow();
        // String rows =jTable1.getName()
        String butch = (tbl_lbgrdlivre1.getModel().getValueAt(rowss,0). toString());
         String batch = (jTable3.getModel().getValueAt(rowsss,0). toString());
        
         if(jComboBox1.getSelectedItem().equals("LB")){
         try{
           // String sqls="SELECT   `DATES`,`ITEM`, format(sum(DEBIT),2) AS PREVISIONS,format(sum(Credit),2) AS DEPENSES,(SELECT sum(DEBIT)FROM budget_trans where projet='"+buss.getSelectedItem().toString()+"' and code='"+butch+"')-sum(CREDIT) AS SOLD  FROM budget_trans where projet='"+buss.getSelectedItem().toString()+"' and CODE='"+butch+"' group by dates,item ";
             String sqls="SELECT   DATES,NUM,ITEM,format(sum(DEBIT),2) AS PREVISIONS,format(sum(Credit),2) AS DEPENSES  FROM budget_trans where projet='"+buss.getSelectedItem().toString()+"' and CODE='"+butch+"' and BATCH='"+batch+"' group by dates,item ";
            pst = con.prepareStatement(sqls);
            rs= pst.executeQuery();

            tbl_grdlivre1.setModel(DbUtils.resultSetToTableModel(rs));
            DefaultTableCellRenderer centre =new DefaultTableCellRenderer();
            centre.setHorizontalAlignment(JLabel.CENTER);
            TableColumn col0=tbl_grdlivre1.getColumnModel().getColumn(0);
            TableColumn col1=tbl_grdlivre1.getColumnModel().getColumn(1);//tbl_grdlivre1
           tbl_grdlivre1.getColumnModel().getColumn(1).setCellRenderer(centre);
            TableColumn col2=tbl_grdlivre1.getColumnModel().getColumn(2);
            TableColumn col3=tbl_grdlivre1.getColumnModel().getColumn(3);
            TableColumn col4=tbl_grdlivre1.getColumnModel().getColumn(4);
//         
            col0.setPreferredWidth(30);
            col1.setPreferredWidth(50);
            col2.setPreferredWidth(550);
            col3.setPreferredWidth(50);
           col4.setPreferredWidth(50);
           

        }catch(SQLException ex ){
            JOptionPane.showMessageDialog(null, ex);}
        NumberFormat nf = new DecimalFormat("#,###.##");
        try{
            String sql="SELECT sum(DEBIT),sum(CREDIT) FROM budget_trans where projet='"+buss.getSelectedItem().toString()+"' and code='"+butch+"' and BATCH='"+batch+"'";
            pst = con.prepareStatement(sql);rs=pst.executeQuery();
            while(rs.next()){
                Double PT=rs.getDouble("sum(DEBIT)");
                Double PTC=rs.getDouble("sum(CREDIT)");
                Double POUR= (PTC*100)/PT;
                String fn = nf.format(PT);
                String fn1 = nf.format(PTC);
                String fn2 = nf.format(POUR);

                jTextField7.setText(fn);
                jTextField8.setText(fn1);
                jTextField9.setText(fn2);
            }
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex);
        }  
        }if(jComboBox1.getSelectedItem().equals("SUB GRD LB")){
         try{
            String sqls="SELECT   `DATES`,`ITEM`, format(sum(DEBIT),2) AS PREVISIONS,format(sum(Credit),2) AS DEPENSES   FROM budget_trans where projet='"+buss.getSelectedItem().toString()+"' and CODE_SUB_CAT='"+butch+"' and BATCH='"+batch+"' group by dates,item ";
            pst = con.prepareStatement(sqls);
            rs= pst.executeQuery();

            tbl_grdlivre1.setModel(DbUtils.resultSetToTableModel(rs));
            DefaultTableCellRenderer centre =new DefaultTableCellRenderer();
            centre.setHorizontalAlignment(JLabel.CENTER);
            TableColumn col0=tbl_grdlivre1.getColumnModel().getColumn(0);
            //jTable9.getColumnModel().getColumn(0).setCellRenderer(centre);
            TableColumn col1=tbl_grdlivre1.getColumnModel().getColumn(1);
            //jTable9.getColumnModel().getColumn(1).setCellRenderer(centre);
            TableColumn col2=tbl_grdlivre1.getColumnModel().getColumn(2);
          //  jTable9.getColumnModel().getColumn(2).setCellRenderer(centre);
            TableColumn col3=tbl_grdlivre1.getColumnModel().getColumn(3);
//             TableColumn col4=jTable9.getColumnModel().getColumn(4);
//              TableColumn col5=jTable9.getColumnModel().getColumn(5);
           // jTable9.getColumnModel().getColumn(3).setCellRenderer(centre);
         
            col0.setPreferredWidth(30);
            col1.setPreferredWidth(950);
            col2.setPreferredWidth(50);
            col3.setPreferredWidth(50);
//              col4.setPreferredWidth(50);
//            col5.setPreferredWidth(50);
           

        }catch(SQLException ex ){
            JOptionPane.showMessageDialog(null, ex);}
        NumberFormat nf = new DecimalFormat("#,###.##");
        try{
            String sql="SELECT sum(DEBIT),sum(CREDIT) FROM budget_trans where projet='"+buss.getSelectedItem().toString()+"' and CODE_SUB_CAT='"+butch+"' and BATCH='"+batch+"'";
            pst = con.prepareStatement(sql);rs=pst.executeQuery();
            while(rs.next()){
                Double PT=rs.getDouble("sum(DEBIT)");
                Double PTC=rs.getDouble("sum(CREDIT)");
                Double POUR= (PTC*100)/PT;
                String fn = nf.format(PT);
                String fn1 = nf.format(PTC);
                String fn2 = nf.format(POUR);

                jTextField7.setText(fn);
                jTextField8.setText(fn1);
                jTextField9.setText(fn2);
            }
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex);
        }  
        }else if(jComboBox1.getSelectedItem().equals("GRD LB")){
         try{
            String sqls="SELECT   `DATES`,`ITEM`, format(sum(DEBIT),2) AS PREVISIONS,format(sum(Credit),2) AS DEPENSES   FROM budget_trans where projet='"+buss.getSelectedItem().toString()+"' and CODE_CAT='"+butch+"' and BATCH='"+batch+"' group by dates,item ";
            pst = con.prepareStatement(sqls);
            rs= pst.executeQuery();

            tbl_grdlivre1.setModel(DbUtils.resultSetToTableModel(rs));
            DefaultTableCellRenderer centre =new DefaultTableCellRenderer();
            centre.setHorizontalAlignment(JLabel.CENTER);
            TableColumn col0=tbl_grdlivre1.getColumnModel().getColumn(0);
            TableColumn col1=tbl_grdlivre1.getColumnModel().getColumn(1);
            TableColumn col2=tbl_grdlivre1.getColumnModel().getColumn(2);
            TableColumn col3=tbl_grdlivre1.getColumnModel().getColumn(3);
         
            col0.setPreferredWidth(30);
            col1.setPreferredWidth(950);
            col2.setPreferredWidth(50);
            col3.setPreferredWidth(50);
           

        }catch(SQLException ex ){
            JOptionPane.showMessageDialog(null, ex);}
        NumberFormat nf = new DecimalFormat("#,###.##");
        try{
            String sql="SELECT sum(DEBIT),sum(CREDIT) FROM budget_trans where projet='"+buss.getSelectedItem().toString()+"' and CODE_CAT='"+butch+"' and BATCH='"+batch+"'";
            pst = con.prepareStatement(sql);rs=pst.executeQuery();
            while(rs.next()){
                Double PT=rs.getDouble("sum(DEBIT)");
                Double PTC=rs.getDouble("sum(CREDIT)");
                Double POUR= (PTC*100)/PT;
                String fn = nf.format(PT);
                String fn1 = nf.format(PTC);
                String fn2 = nf.format(POUR);

                jTextField7.setText(fn);
                jTextField8.setText(fn1);
                jTextField9.setText(fn2);
            }
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex);
        }  
        }
       
       Cortablegrdlb1(); 
 budget_shows.title.setText("Livre des depenses Previsionel");       // TODO add your handling code here:
    }//GEN-LAST:event_tbl_lbgrdlivre1MouseClicked

    private void tbl_grdlivre1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_grdlivre1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tbl_grdlivre1MouseClicked

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
reportGrdLivre1();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
try{
            String sql="select ACESS from user_write where NAME='"+Home_page.home.user.getText()+"' and CHAMP='Save Budget'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
          String  caisse= rs.getString("ACESS");
          if(caisse.equals("Yes")){
         journals.extouner m = new journals.extouner();
//         selectontableextourne();
m.show();
  m. setDefaultCloseOperation(DISPOSE_ON_CLOSE); 
          }else{
       JOptionPane.showMessageDialog(null,"Seul le financier(e) est autorisé(e)","Avertissement",JOptionPane.WARNING_MESSAGE);
          }
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex); }        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel6MouseClicked

    private void jLabel22MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel22MouseClicked
export();        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel22MouseClicked

    private void jLabel23MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel23MouseClicked
delete_transction();        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel23MouseClicked

    private void jLabel25MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel25MouseClicked
budget_shows.title.setText("Rapport consolidé des projets");  
        TableModel model1 =jTable1.getModel();
        int indexs[]=jTable1.getSelectedRows();
        Object[] row = new Object[1];
        DefaultTableModel model2 = (DefaultTableModel) jTable2.getModel();
       
       
        for(int i=0; i < indexs.length;i++){
             String lb = (jTable1.getModel().getValueAt(indexs[i],0). toString());
        
        row[0]= model1.getValueAt(indexs[i],0);
        //row[1]= model1.getValueAt(indexs[i],1);
      
        model2.addRow(row);
        
      
        }  
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel25MouseClicked

    private void jLabel26MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel26MouseClicked
       int indexs[]=jTable2.getSelectedRows();
        
        DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
        for(int i=0; i < indexs.length;i++){
          
    int getSelectedRowsForDeletion = jTable2.getSelectedRow();
        model.removeRow(getSelectedRowsForDeletion);
        
        }  
        jEditorPane1.setText("");// TODO add your handling code here:
    }//GEN-LAST:event_jLabel26MouseClicked

    private void jLabel29MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel29MouseClicked
/* 
        
        try{
                 
                 
                   String sqls="Select path from pathn";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
            if(rs.next()){
             
                String sum=rs.getString("path");
                  
                 String NameFiles=sum;
            String NameFile=""+NameFiles+"report8.jrxml";
           // String NameFile=""+NameFiles+"depense_projet.jrxml";
                 
                 
            // here    
                JasperDesign jd=JRXmlLoader.load(NameFile);
                
      
         StringBuffer sb= new StringBuffer(jEditorPane1.getText());
    sb.deleteCharAt(sb.length()-1);
    String Sb=sb.toString();          
       String sql="select sum(debit),sum(credit),item,items,code,CODE_CAT,CODE_SUB_CAT,BATCH,budget_trans.PROJET,CAT,SUB_CAT,BAYEUR,projet.PROJET as titre,projet.DATEIN,projet.DATEOUT,tete from budget_trans  INNER JOIN  projet_bayeur on budget_trans.PROJET= projet_bayeur.PROJET_NUM   INNER JOIN projet on budget_trans.PROJET=PROJET.PROJET_NUM where budget_trans.PROJET in ("+Sb+") group by budget_trans.projet";
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
             }        // TODO add your handling code here:
        */
    }//GEN-LAST:event_jLabel29MouseClicked

    private void jLabel30MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel30MouseClicked
 TableModel model1 =jTable2.getModel();
        int indexs[]=jTable2.getSelectedRows();
        Object[] row = new Object[1];
     SimpleDateFormat dateFormatsS = new SimpleDateFormat("yyyy-MM-dd");
         String date1 = dateFormatsS.format(jDateChooser2.getDate());
          String date2 = dateFormatsS.format(jDateChooser3.getDate());
       
       String Sb=null;
        for(int i=0; i < indexs.length;i++){
             String lb = (jTable2.getModel().getValueAt(indexs[i],0). toString());
        
        row[0]= model1.getValueAt(indexs[i],0);
        //row[1]= model1.getValueAt(indexs[i],1);
     //   String aa="'6100149-AA','6000207-BMZ'";
     jEditorPane1.setText(jEditorPane1.getText()+"'"+lb+"',");
    StringBuffer sb= new StringBuffer(jEditorPane1.getText());
    sb.deleteCharAt(sb.length()-1);
     Sb=sb.toString();
       
      
        }  
  
  String sqls = null;
        try{
            if(jComboBox1.getSelectedItem().equals("LB")){
             sqls="SELECT  CODE, ITEMS as 'DEPENSES ESTIMEES', format(sum(`DEBIT`),2) AS PREVISION,format(sum(CREDIT),2) AS DEPENSE,format((sum(DEBIT)-sum(CREDIT)),2) AS SOLDE,format(((sum(CREDIT))*100)/sum(DEBIT),2) AS '_%_',PROJET FROM budget_trans where projet IN ("+Sb+") and DATES BETWEEN '"+date1+"' AND '"+date2+"'  group by code ORDER BY projet,CODE,ID";
            
            }else if(jComboBox1.getSelectedItem().equals("SUB GRD LB")){
            sqls="SELECT  CODE_SUB_CAT, SUB_CAT as 'DEPENSES ESTIMEES', format(sum(`DEBIT`),2) AS PREVISION,format(sum(CREDIT),2) AS DEPENSE,format((sum(DEBIT)-sum(CREDIT)),2) AS SOLDE,format(((sum(CREDIT))*100)/sum(DEBIT),2) AS '_%_',PROJET FROM budget_trans where projet IN ("+Sb+") and DATES BETWEEN '"+date1+"' AND '"+date2+"'  group by CODE_SUB_CAT ORDER BY projet,CODE_SUB_CAT,ID";
            
            }else if(jComboBox1.getSelectedItem().equals("GRD LB")){
            sqls="SELECT  CODE_CAT, CAT as 'DEPENSES ESTIMEES', format(sum(`DEBIT`),2) AS PREVISION,format(sum(CREDIT),2) AS DEPENSE,format((sum(DEBIT)-sum(CREDIT)),2) AS SOLDE,format(((sum(CREDIT))*100)/sum(DEBIT),2) AS '_%_',PROJET FROM budget_trans where projet IN ("+Sb+") and DATES BETWEEN '"+date1+"' AND '"+date2+"'  group by CODE_CAT ORDER BY projet,CODE_CAT,ID";
             
            }
           pst = con.prepareStatement(sqls);
            rs= pst.executeQuery();

            jTable5.setModel(DbUtils.resultSetToTableModel(rs));
            DefaultTableCellRenderer centre =new DefaultTableCellRenderer();
            centre.setHorizontalAlignment(JLabel.CENTER);
            TableColumn col0=jTable5.getColumnModel().getColumn(0);
            TableColumn col1=jTable5.getColumnModel().getColumn(1);
            TableColumn col2=jTable5.getColumnModel().getColumn(2);
            TableColumn col3=jTable5.getColumnModel().getColumn(3);
            TableColumn col4=jTable5.getColumnModel().getColumn(4);
            TableColumn col5=jTable5.getColumnModel().getColumn(5);
            TableColumn col6=jTable5.getColumnModel().getColumn(6);

            col0.setPreferredWidth(30);
            col1.setPreferredWidth(700);
            col2.setPreferredWidth(30);
            col3.setPreferredWidth(30);
            col4.setPreferredWidth(30);
            col5.setPreferredWidth(30);
            col6.setPreferredWidth(100);

            //

        }catch(SQLException ex ){
            JOptionPane.showMessageDialog(null, ex);}  
   
  Cortable_ccbs();      
        
        /*DefaultTableModel excels= (DefaultTableModel)jTable2.getModel(); 
int indexs[]=jTable2.getSelectedRows();
int[] array =jTable2.getSelectedRows();
  SimpleDateFormat dateFormatsS = new SimpleDateFormat("yyyy-MM-dd");
         String date1 = dateFormatsS.format(jDateChooser2.getDate());
          String date2 = dateFormatsS.format(jDateChooser3.getDate());
for(int i=0; i < excels.getRowCount();i++){
  //  for(int i=0; i < array.length;i++){
    String consomation= null,prevision = null;
    String lb = null,item= null;
     String projet = (jTable2.getModel().getValueAt(indexs[i],0). toString());
        try{
 String sqls="SELECT  CODE, ITEMS , DEBIT,CREDIT FROM budget_trans where projet in ('"+projet+"') and DATES BETWEEN '"+date1+"' AND '"+date2+"'  ";//group by code ";//ORDER BY ID,projet";
pst = con.prepareStatement(sqls);          rs=pst.executeQuery();
while(rs.next()){
consomation=rs.getString("CREDIT");
prevision=rs.getString("DEBIT");
lb=rs.getString("CODE");
item=rs.getString("ITEMS");
 
 }    
 } catch (Exception ex) {JOptionPane.showMessageDialog(null, ex.getMessage());} 
  /*
    try{
            String sqls="SELECT  CODE, ITEMS as 'DEPENSES ESTIMEES', format(sum(`DEBIT`),2) AS PREVISION,format(sum(CREDIT),2) AS DEPENSE,format((sum(DEBIT)-sum(CREDIT)),2) AS SOLDE,format(((sum(CREDIT))*100)/sum(DEBIT),2) AS '_%_',PROJET FROM budget_trans where projet='"+projet+"' and DATES BETWEEN '"+date1+"' AND '"+date2+"'  group by code ORDER BY ID,projet";
            pst = con.prepareStatement(sqls);
            rs= pst.executeQuery();

            jTable5.setModel(DbUtils.resultSetToTableModel(rs));
            DefaultTableCellRenderer centre =new DefaultTableCellRenderer();
            centre.setHorizontalAlignment(JLabel.CENTER);
            TableColumn col0=jTable5.getColumnModel().getColumn(0);
            TableColumn col1=jTable5.getColumnModel().getColumn(1);
            TableColumn col2=jTable5.getColumnModel().getColumn(2);
            TableColumn col3=jTable5.getColumnModel().getColumn(3);
            TableColumn col4=jTable5.getColumnModel().getColumn(4);
            TableColumn col5=jTable5.getColumnModel().getColumn(5);
            TableColumn col6=jTable5.getColumnModel().getColumn(6);

            col0.setPreferredWidth(30);
            col1.setPreferredWidth(700);
            col2.setPreferredWidth(30);
            col3.setPreferredWidth(30);
            col4.setPreferredWidth(30);
            col5.setPreferredWidth(30);
            col6.setPreferredWidth(100);

            //

        }catch(SQLException ex ){
            JOptionPane.showMessageDialog(null, ex);}  
   
 
   
  // HERE SAVE
             try {
String queries= "INSERT INTO `report_all`( `LB`, `ITEM`, `PREVISION`, `COSOMATION`, `BUSS`) "
        +"value(?,?,?,?,?)";
         pst = con.prepareStatement(queries);
    pst.setString(1, lb);
        pst.setString(2,item);
         pst.setString(3,prevision);
         pst.setString(4,consomation);
         pst.setString(5,"");
        
         
          pst.executeUpdate();
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }

}
 */               // TODO add your handling code here:
    }//GEN-LAST:event_jLabel30MouseClicked

    private void jTable5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable5MouseClicked
budget_shows.title.setText("Rapport consolidé des projets");         // TODO add your handling code here:
    }//GEN-LAST:event_jTable5MouseClicked

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
budget_shows.title.setText("Rapport consolidé des projets");          // TODO add your handling code here:
    }//GEN-LAST:event_jTable1MouseClicked

    private void ccb1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ccb1MouseClicked
 budget_shows.title.setText("Livre de dépense consolidé de prévision");          // TODO add your handling code here:
    }//GEN-LAST:event_ccb1MouseClicked

    private void ccb2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ccb2MouseClicked
 budget_shows.title.setText("Livre de dépense Projet");       // TODO add your handling code here:
    }//GEN-LAST:event_ccb2MouseClicked

    private void jTabbedPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTabbedPane1MouseClicked

    private void bussPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_bussPopupMenuWillBecomeInvisible
if(jComboBox1.getSelectedItem().equals("LB")){
callitem(); 
try{
            String sqls="SELECT  CODE, ITEMS as 'DEPENSES ESTIMEES', format(sum(`DEBIT`),2) AS PREVISION,format(sum(CREDIT),2) AS DEPENSE,format((sum(DEBIT)-sum(CREDIT)),2) AS SOLDE,format(((sum(CREDIT))*100)/sum(DEBIT),2) AS '_%_' FROM budget_trans where projet='"+buss.getSelectedItem().toString()+"'  group by code ORDER BY ID";
            pst = con.prepareStatement(sqls);
            rs= pst.executeQuery();

            ccb.setModel(DbUtils.resultSetToTableModel(rs));
            DefaultTableCellRenderer centre =new DefaultTableCellRenderer();
            centre.setHorizontalAlignment(JLabel.CENTER);
            TableColumn col0=ccb.getColumnModel().getColumn(0);
            TableColumn col1=ccb.getColumnModel().getColumn(1);
            TableColumn col2=ccb.getColumnModel().getColumn(2);
            TableColumn col3=ccb.getColumnModel().getColumn(3);
            TableColumn col4=ccb.getColumnModel().getColumn(4);
            TableColumn col5=ccb.getColumnModel().getColumn(5);

            col0.setPreferredWidth(30);
            col1.setPreferredWidth(700);
            col2.setPreferredWidth(30);
            col3.setPreferredWidth(30);
            col4.setPreferredWidth(30);
            col5.setPreferredWidth(30);
            //

        }catch(SQLException ex ){
            JOptionPane.showMessageDialog(null, ex);}    
  try{
            String sqls="SELECT  DATES,NUM,CODE, ITEM, format(sum(`DEBIT`),2) AS PREVISION,format(sum(CREDIT),2) AS DEPENSE FROM budget_trans where projet='"+buss.getSelectedItem().toString()+"' group by dates,item ORDER BY code,ID";
            pst = con.prepareStatement(sqls);
            rs= pst.executeQuery();

            ccb2.setModel(DbUtils.resultSetToTableModel(rs));
            DefaultTableCellRenderer centre =new DefaultTableCellRenderer();
            centre.setHorizontalAlignment(JLabel.CENTER);
            TableColumn col0=ccb2.getColumnModel().getColumn(0);
            TableColumn col1=ccb2.getColumnModel().getColumn(1);
            TableColumn col2=ccb2.getColumnModel().getColumn(2);
            TableColumn col3=ccb2.getColumnModel().getColumn(3);
            TableColumn col4=ccb2.getColumnModel().getColumn(4);
            TableColumn col5=ccb2.getColumnModel().getColumn(5);

            col0.setPreferredWidth(30);
            col1.setPreferredWidth(30);
            col2.setPreferredWidth(30);
            col3.setPreferredWidth(700);
            col4.setPreferredWidth(30);
            col5.setPreferredWidth(30);
            //

        }catch(SQLException ex ){
            JOptionPane.showMessageDialog(null, ex);}  
Cortable_ccb2();
}else if(jComboBox1.getSelectedItem().equals("SUB GRD LB")){
callitemsub();
try{
            String sqls="SELECT CODE_SUB_CAT AS LB, SUB_CAT as 'DEPENSES ESTIMEES', format(sum(`DEBIT`),2) AS PREVISION,format(sum(CREDIT),2) AS DEPENSE,format((sum(DEBIT)-sum(CREDIT)),2) AS SOLDE,format(((sum(CREDIT))*100)/sum(DEBIT),2) AS '_%_' FROM budget_trans where projet='"+buss.getSelectedItem().toString()+"'  group by CODE_SUB_CAT ORDER BY ID";
            pst = con.prepareStatement(sqls);
            rs= pst.executeQuery();

            ccb.setModel(DbUtils.resultSetToTableModel(rs));
            DefaultTableCellRenderer centre =new DefaultTableCellRenderer();
            centre.setHorizontalAlignment(JLabel.CENTER);
            TableColumn col0=ccb.getColumnModel().getColumn(0);
            TableColumn col1=ccb.getColumnModel().getColumn(1);
            TableColumn col2=ccb.getColumnModel().getColumn(2);
            TableColumn col3=ccb.getColumnModel().getColumn(3);
            TableColumn col4=ccb.getColumnModel().getColumn(4);
            TableColumn col5=ccb.getColumnModel().getColumn(5);

            col0.setPreferredWidth(30);
            col1.setPreferredWidth(700);
            col2.setPreferredWidth(30);
            col3.setPreferredWidth(30);
            col4.setPreferredWidth(30);
            col5.setPreferredWidth(30);
            //

        }catch(SQLException ex ){
            JOptionPane.showMessageDialog(null, ex);}    
}else{
callitemgrd();
try{
            String sqls="SELECT CODE_CAT AS LB, CAT  as 'DEPENSES ESTIMEES', format(sum(`DEBIT`),2) AS PREVISION,format(sum(CREDIT),2) AS DEPENSE,format((sum(DEBIT)-sum(CREDIT)),2) AS SOLDE,format(((sum(CREDIT))*100)/sum(DEBIT),2) AS '_%_' FROM budget_trans where projet='"+buss.getSelectedItem().toString()+"'  group by CODE_CAT ORDER BY ID";
            pst = con.prepareStatement(sqls);
            rs= pst.executeQuery();

            ccb.setModel(DbUtils.resultSetToTableModel(rs));
            DefaultTableCellRenderer centre =new DefaultTableCellRenderer();
            centre.setHorizontalAlignment(JLabel.CENTER);
            TableColumn col0=ccb.getColumnModel().getColumn(0);
            TableColumn col1=ccb.getColumnModel().getColumn(1);
            TableColumn col2=ccb.getColumnModel().getColumn(2);
            TableColumn col3=ccb.getColumnModel().getColumn(3);
            TableColumn col4=ccb.getColumnModel().getColumn(4);
            TableColumn col5=ccb.getColumnModel().getColumn(5);

            col0.setPreferredWidth(30);
            col1.setPreferredWidth(700);
            col2.setPreferredWidth(30);
            col3.setPreferredWidth(30);
            col4.setPreferredWidth(30);
            col5.setPreferredWidth(30);
            //

        }catch(SQLException ex ){
            JOptionPane.showMessageDialog(null, ex);}    
} 
 
Cortable_ccb();  

NumberFormat nf = new DecimalFormat("#,###.##");
        try{
            String sql="SELECT sum(DEBIT),sum(CREDIT) FROM budget_trans where projet='"+buss.getSelectedItem().toString()+"'";
            pst = con.prepareStatement(sql);rs=pst.executeQuery();
            while(rs.next()){
                Double PT=rs.getDouble("sum(DEBIT)");
                Double PTC=rs.getDouble("sum(CREDIT)");
                Double POUR= (PTC*100)/PT;
                String fn = nf.format(PT);
                String fn1 = nf.format(PTC);
                String fn2 = nf.format(POUR);

                jTextField4.setText(fn);
                jTextField5.setText(fn1);
                jTextField6.setText(fn2);
            }
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex);
        } 
// TODO add your handling code here:
    }                                                 

    private void jLabel7MouseClicked() {                                     
try{
            String sql="select ACESS from user_write where NAME='"+Home_page.home.user.getText()+"' and CHAMP='Save Budget'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
          String  caisse= rs.getString("ACESS");
          if(caisse.equals("Yes")){
      if(jLabel9.getText().equals("")){
importexcel();
}else{
SaveBudget();
}
          }else{
       JOptionPane.showMessageDialog(null,"Seul le financier(e) est autorisé(e)","Avertissement",JOptionPane.WARNING_MESSAGE);
          }
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex); }
        // TODO add your handling code here:
    }//GEN-LAST:event_bussPopupMenuWillBecomeInvisible


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static final Palette.Combobox buss = new Palette.Combobox();
    private javax.swing.JTable ccb;
    private javax.swing.JTable ccb1;
    private javax.swing.JTable ccb2;
    private javax.swing.JTextField credit_b;
    private javax.swing.JTextField credit_b1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JComboBox<String> jComboBox1;
    public static final com.alee.extended.date.WebDateField jDateChooser1 = new com.alee.extended.date.WebDateField();
    public static final com.alee.extended.date.WebDateField jDateChooser2 = new com.alee.extended.date.WebDateField();
    public static final com.alee.extended.date.WebDateField jDateChooser3 = new com.alee.extended.date.WebDateField();
    private javax.swing.JEditorPane jEditorPane1;
    private javax.swing.JLabel jLabel1;
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
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
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
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane17;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    public static final javax.swing.JTable jTable3 = new javax.swing.JTable();
    public static final javax.swing.JTable jTable4 = new javax.swing.JTable();
    private javax.swing.JTable jTable5;
    private javax.swing.JTable jTable6;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JTextField pour;
    private javax.swing.JTable tbl_budget;
    private javax.swing.JTable tbl_control;
    private javax.swing.JTable tbl_dispacting;
    private javax.swing.JTable tbl_grdlivre;
    public static final javax.swing.JTable tbl_grdlivre1 = new javax.swing.JTable();
    public static final javax.swing.JTable tbl_lbgrdlivre = new javax.swing.JTable();
    public static final javax.swing.JTable tbl_lbgrdlivre1 = new javax.swing.JTable();
    private javax.swing.JTextField total_b;
    // End of variables declaration//GEN-END:variables
}
