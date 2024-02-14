/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intreprisemanagementsystem;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
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

/**
 *
 * @author DOSHE
 */
public class equipment_inv extends javax.swing.JInternalFrame {

  Connection con=null;
PreparedStatement pst=null;
ResultSet rs= null;
//DefaultTableModel mode;
 String rolls;
 String NUM_ID;
 DefaultTableModel mode; 
    public equipment_inv() {
        initComponents();
              con=JavaDbConnect.dbConnect();
         Call_ID_TO_Chantier();
       Call_ID_TO_material();
        call_table();
        webDateField2.setDate(new Date());
        jPanel4.setVisible(false);
    }
public void Call_ID_TO_material()
    {
         {
        try{
            String sql="select distinct(ITEM_ID) from equipement_in";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                String sum=rs.getString("ITEM_ID");
                  mat.addItem(sum);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
    }
    }
 public void Call_ID_TO_Chantier()
    {
         {
        try{
            String sql="select DISTINCT(SITE) from eqipement_trans";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                String sum=rs.getString("SITE");
                  site.addItem(sum);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
    }
    }
 


 
 
  public void Amortissement()
    {
    String   ITEM_ID,ITEM,DESCR,SERIAL,MODEL,DATES,NUM_IDs,CAT;
    Double PRICE;
    Date date1;
         {
        try{
            String sql="select * from equipement_in";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
           ITEM_ID=rs.getString("ITEM_ID"); 
           ITEM=rs.getString("ITEM"); 
           DESCR=rs.getString("DESCR"); 
           SERIAL=rs.getString("SERIAL"); 
           MODEL=rs.getString("MODEL"); 
           DATES=rs.getString("DATES"); 
            date1=rs.getDate("DATES");
          NUM_IDs=rs.getString("NUM_ID"); 
          PRICE=rs.getDouble("PRICE"); 
          CAT=rs.getString("CAT"); 
           
      Double val = null,c,cc,ccc;
       float daysBetween = 0;
      
       SimpleDateFormat myFormat = new SimpleDateFormat("dd MM yyyy");
  SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd MM yyyy");
       String dateacusition = dateFormat1.format(date1);
        String dateactual = myFormat.format(webDateField1.getDate());
         
	
	 try {
	       Date dateBefore = myFormat.parse(dateacusition);
	       Date dateAfter = myFormat.parse(dateactual);
	       long difference = dateAfter.getTime() - dateBefore.getTime();
	      daysBetween = (difference / (1000*60*60*24));
              
               /* You can also convert the milliseconds to days using this method
                * float daysBetween = 
                *         TimeUnit.DAYS.convert(difference, TimeUnit.MILLISECONDS)
                */
	      
	 } catch (Exception e) {
	       e.printStackTrace();
	 }
           try{
            String sqlSS="select val from amortissement WHERE CAT='"+CAT+"'";
           
            pst=con.prepareStatement(sqlSS);
            rs=pst.executeQuery();
            while(rs.next()){
                Double sum=rs.getDouble("val");
                  c=sum*30; //Jour Amortisable
                  
                 // base amortissable x taux d'amortissement
                  cc=PRICE/c; //valeur Amortisable
                  ccc=cc*daysBetween;
                  val=PRICE-ccc;
                  
            }
            }
        catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}
          
          try{
    String sqls="INSERT INTO `equipement_amortissement`(`ITEM_ID`, `ITEM`, `DESCR`, `SERIAL`, `MODEL`, `DATES`, `NUM_ID`, `PRICE`, `VAL`, `CAT`) VALUES (?,?,?,?,?,?,?,?,?,?)";
     
    pst=con.prepareStatement(sqls);
    pst.setString(1,ITEM_ID);
    pst.setString(2,ITEM);
    pst.setString(3,DESCR);
    pst.setString(4,SERIAL);
    pst.setString(5,MODEL);
    
    pst.setString(6,DATES);
    pst.setString(7,NUM_IDs);
    
    pst.setDouble(8,PRICE);
    
    pst.setDouble(9,val);
    pst.setString(10,CAT);
     
    
   
    
   
  
      
    
    pst.executeUpdate();
     JOptionPane.showMessageDialog(null,"Datas Saved");
    }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}
                
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
    }
    }
 public void saveS(){
  int rows=jTable1.getRowCount();
 String   ITEM_ID = null,ITEM = null,DESCR= null,SERIAL= null,MODEL= null,NUM_IDs= null,CAT= null,DATES= null,PRICES=null;
 Double PRICE= null;
 Date date1 = null;
  call_tablein();
  for(int row = 0; row<rows; row++)
  {   
  // ;   
      
      
    ITEM_ID = (String)jTable1.getValueAt(row, 0);
    ITEM = (String)jTable1.getValueAt(row, 1);
    DESCR = (String)jTable1.getValueAt(row, 2);
    SERIAL = (String)jTable1.getValueAt(row, 3);
    MODEL = (String)jTable1.getValueAt(row, 4);
    DATES = (String)jTable1.getValueAt(row, 5);
    NUM_IDs = (String)jTable1.getValueAt(row, 6);
    PRICES = (String)jTable1.getValueAt(row, 7);
    CAT = (String)jTable1.getValueAt(row, 8);
   
     try{
            String sql="select * from eqipement_trans WHERE ITEM_ID='"+ITEM_ID +"' and DEBIT=1";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
            date1=rs.getDate("DATES");
          PRICE=rs.getDouble("PRICE"); 
        
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
    
    
    
    Double val = null,c,cc = null,ccc,POUR = null;
    String VAL = null,pour = null,CC = null;
       float daysBetween = 0;
      //long date1= Date.parse(DATES);
       SimpleDateFormat myFormat = new SimpleDateFormat("dd MM yyyy");
  SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd MM yyyy");
       String dateacusition = dateFormat1.format(date1);
        //JOptionPane.showMessageDialog(null, date1+" "+dateacusition);
       
        String dateactual = myFormat.format(webDateField2.getDate());
        
       
	
	 try {
	       Date dateBefore = myFormat.parse(dateacusition);
	       Date dateAfter = myFormat.parse(dateactual);
	       long difference = dateAfter.getTime() - dateBefore.getTime();
	      daysBetween = (difference / (1000*60*60*24));
              
               /* You can also convert the milliseconds to days using this method
                * float daysBetween = 
                *         TimeUnit.DAYS.convert(difference, TimeUnit.MILLISECONDS)
                */
	      
	 } catch (Exception e) {
	       e.printStackTrace();
	 }
           try{
            String sqlSS="select val from amortissement WHERE CAT='"+CAT+"'";
           
            pst=con.prepareStatement(sqlSS);
            rs=pst.executeQuery();
            while(rs.next()){
                 NumberFormat nf = new DecimalFormat("#,###.000");

                Double sum=rs.getDouble("val");
                  c=sum*30; //Jour Amortisable
                  
                 // base amortissable x taux d'amortissement
                  cc=PRICE/c; //valeur Amortisable
                  ccc=cc*daysBetween;//valeur net
                  val=PRICE-ccc;
                  POUR=ccc*100/PRICE;
                  CC = nf.format(cc);
                  VAL = nf.format(val);
                  pour = nf.format(POUR);
                  
                  
            }
            }
        catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}
    
         try{
    String sqls="INSERT INTO `equipement_amortissement`(`ITEM_ID`, `ITEM`, `DESCR`, `SERIAL`, `MODEL`, `DATES`, `NUM_ID`, `PRICE`, `VAL`, `CAT`,JRS,POUR,VALPRJRS) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
     
    pst=con.prepareStatement(sqls);
    pst.setString(1,ITEM_ID);
    pst.setString(2,ITEM);
    pst.setString(3,DESCR);
    pst.setString(4,SERIAL);
    pst.setString(5,MODEL);
    
    pst.setString(6,DATES);
    pst.setString(7,NUM_IDs);
    
    pst.setDouble(8,PRICE);
    
    pst.setDouble(9,val);
    pst.setString(10,CAT);
    
     pst.setDouble(11,daysBetween);
    pst.setString(12,pour);
    pst.setString(13,CC);
     
    pst.executeUpdate();
    
    }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}
  }
  
  //VEHICULE
   int rowss=jTable2.getRowCount();
    for(int row = 0; row<rowss; row++)
  {   
   
      
      
String    ITEM_IDS = (String)jTable2.getValueAt(row, 0);
   String  ITEMS = (String)jTable2.getValueAt(row, 1);
  String   DESCRS = (String)jTable2.getValueAt(row, 2);
   String  SERIALS = (String)jTable2.getValueAt(row, 3);
   String  MODELS = (String)jTable2.getValueAt(row, 4);
   String  DATESS = (String)jTable2.getValueAt(row, 5);
   String  NUM_IDsS = (String)jTable2.getValueAt(row, 6);
     PRICES = (String)jTable2.getValueAt(row, 7);
   String CATS = "Vehicule";
   
     try{
            String sql="select * from agin WHERE NUM_AGIN='"+ITEM_IDS +"'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
            date1=rs.getDate("DATE");
          PRICE=rs.getDouble("PRICE"); 
        
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
    
    
    
    Double val = null,c,cc = null,ccc,POURS = null;
    String VAL = null,pour = null,CC = null;
       float daysBetween = 0;
       SimpleDateFormat myFormat = new SimpleDateFormat("dd MM yyyy");
  SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd MM yyyy");
       String dateacusition = dateFormat1.format(date1);
        String dateactual = myFormat.format(webDateField2.getDate());
        
       
	
	 try {
	       Date dateBefore = myFormat.parse(dateacusition);
	       Date dateAfter = myFormat.parse(dateactual);
	       long difference = dateAfter.getTime() - dateBefore.getTime();
	      daysBetween = (difference / (1000*60*60*24));
              
               /* You can also convert the milliseconds to days using this method
                * float daysBetween = 
                *         TimeUnit.DAYS.convert(difference, TimeUnit.MILLISECONDS)
                */
	      
	 } catch (Exception e) {
	       e.printStackTrace();
	 }
           try{
            String sqlSS="select val from amortissement WHERE CAT='"+CATS+"'";
           
            pst=con.prepareStatement(sqlSS);
            rs=pst.executeQuery();
            while(rs.next()){
                 NumberFormat nf = new DecimalFormat("#,###.000");

                Double sum=rs.getDouble("val");
                  c=sum*30; //Jour Amortisable
                  
                 // base amortissable x taux d'amortissement
                  cc=PRICE/c; //valeur Amortisable
                  ccc=cc*daysBetween;//valeur net
                  val=PRICE-ccc;
                  POURS=ccc*100/PRICE;
                  CC = nf.format(cc);
                  VAL = nf.format(val);
                  pour = nf.format(POURS);
                  
                  
            }
            }
        catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}
    
         try{
    String sqls="INSERT INTO `equipement_amortissement`(`ITEM_ID`, `ITEM`, `DESCR`, `SERIAL`, `MODEL`, `DATES`, `NUM_ID`, `PRICE`, `VAL`, `CAT`,JRS,POUR,VALPRJRS) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
     
    pst=con.prepareStatement(sqls);
    pst.setString(1,ITEM_IDS);
    pst.setString(2,ITEMS);
    pst.setString(3,DESCRS);
    pst.setString(4,SERIALS);
    pst.setString(5,MODELS);
    
    pst.setString(6,DATESS);
    pst.setString(7,NUM_IDsS);
    
    pst.setDouble(8,PRICE);
    
    pst.setDouble(9,val);
    pst.setString(10,CATS);
    
     pst.setDouble(11,daysBetween);
    pst.setString(12,pour);
    pst.setString(13,CC);
     
    
   
    
   
  
      
    
    pst.executeUpdate();
    
    }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}
  }
  
  
  
   //JOptionPane.showMessageDialog(null,"Datas Saved");
  }
     public void truncate()
    {
         try{
        String sql = "truncate  equipement_amortissement";
        
         pst = con.prepareStatement(sql);
        // pst.setString(1,num.getText());
         pst.executeUpdate();
        // JOptionPane.showMessageDialog(null,"Table Truncated");
              
     }catch(SQLException | HeadlessException ex ){
         JOptionPane.showMessageDialog(null,ex);
     } 
    }
   public void call_table(){
        try{
           
             String sql="SELECT `ITEM_ID`, `DEBIT`, `CREDIT`,`DATES`, `SITE`, `PURPOSE`, `EMP`, `NUM_ID` FROM `eqipement_trans` ";
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
        
         TableColumn col7=jTable1.getColumnModel().getColumn(7);
     //   TableColumn col8=jTable1.getColumnModel().getColumn(8);
       
       
       
      
       
       col0.setPreferredWidth(150);
       col1.setPreferredWidth(20);
       col2.setPreferredWidth(20);
       col3.setPreferredWidth(20);
       col4.setPreferredWidth(50);
       col5.setPreferredWidth(50);
       col6.setPreferredWidth(150);
     col7.setPreferredWidth(80);
    //   col8.setPreferredWidth(50);
      
       
       
      // jTable1.setModel(mode);
       
     
    }catch(SQLException ex ){
     JOptionPane.showMessageDialog(null, ex);
}
    
    
      
      }
   
    public void call_tablein(){
      
           try{
           
             String sql="SELECT ITEM_ID,ITEM,DESCR,SERIAL,MODEL,DATES,NUM_ID,PRICE,CAT FROM `eqipement_trans` ";
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
        
         TableColumn col7=jTable1.getColumnModel().getColumn(7);
        TableColumn col8=jTable1.getColumnModel().getColumn(8);
       
       
       
      
       
       col0.setPreferredWidth(150);
       col1.setPreferredWidth(20);
       col2.setPreferredWidth(20);
       col3.setPreferredWidth(20);
       col4.setPreferredWidth(50);
       col5.setPreferredWidth(50);
       col6.setPreferredWidth(150);
     col7.setPreferredWidth(80);
      col8.setPreferredWidth(50);
      
       
       
      // jTable1.setModel(mode);
       
     
    }catch(SQLException ex ){
     JOptionPane.showMessageDialog(null, ex);
}
     
           try{
          //SELECT `ID`, ``, `NOM`, `DESCR`, `MODEL`, `TYPE`, `ANNE`, `MOTEUR`, `CHASSI`, `IMMAT`, `COULEUR`, `PLACE`, `PROV`, `KM`, `DATE`, `PRICE` FROM `agin` WHERE 1 
             String sql="SELECT NUM_AGIN,NOM,DESCR,MODEL,TYPE,DATE,MOTEUR,PRICE FROM `agin` WHERE PRICE <> 0 ";
     //  String sql="SELECT ID, `NUM_FACTURE`, `DATE_FACTURE`, `DESCRIPTION`, `DEBIT_QTY`, `DEBIT_PU`, `DEBIT_PT`, `DEBIT_TVA`, `CREDIT_QTY`, `CREDIT_PU`, `CREDIT_PT`, `CREDIT_TVA`, `DATES`, `NUM` FROM facture_fournisseur where compte='"+jComboBox6.getSelectedItem()+"'";
       pst = con.prepareStatement(sql);
      rs= pst.executeQuery();
      
       
       jTable2.setModel(DbUtils.resultSetToTableModel(rs));
       mode=new DefaultTableModel();
       
       
        TableColumn col0=jTable2.getColumnModel().getColumn(0);
        TableColumn col1=jTable2.getColumnModel().getColumn(1);
        TableColumn col2=jTable2.getColumnModel().getColumn(2);
        TableColumn col3=jTable2.getColumnModel().getColumn(3);
        TableColumn col4=jTable2.getColumnModel().getColumn(4);
        TableColumn col5=jTable2.getColumnModel().getColumn(5);
        TableColumn col6=jTable2.getColumnModel().getColumn(6);
        
         TableColumn col7=jTable2.getColumnModel().getColumn(7);
       // TableColumn col8=jTable1.getColumnModel().getColumn(8);
       
       
       
      
       
       col0.setPreferredWidth(150);
       col1.setPreferredWidth(20);
       col2.setPreferredWidth(20);
       col3.setPreferredWidth(20);
       col4.setPreferredWidth(50);
       col5.setPreferredWidth(50);
       col6.setPreferredWidth(150);
     col7.setPreferredWidth(80);
    //  col8.setPreferredWidth(50);
      
       
       
      // jTable1.setModel(mode);
       
     
    }catch(SQLException ex ){
     JOptionPane.showMessageDialog(null, ex);
}
      
      }
 
   
    public void call_table_ITEM(){
      
           try{
           
             String sql="SELECT `ITEM_ID`, `DEBIT`, `CREDIT`,`DATES`, `SITE`, `PURPOSE`, `EMP`, `NUM_ID` FROM `eqipement_trans` WHERE ITEM_ID='"+mat.getSelectedItem()+"' ";
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
        
         TableColumn col7=jTable1.getColumnModel().getColumn(7);
     //   TableColumn col8=jTable1.getColumnModel().getColumn(8);
       
       
       
      
       
       col0.setPreferredWidth(150);
       col1.setPreferredWidth(20);
       col2.setPreferredWidth(20);
       col3.setPreferredWidth(20);
       col4.setPreferredWidth(50);
       col5.setPreferredWidth(50);
       col6.setPreferredWidth(150);
     col7.setPreferredWidth(80);
    //   col8.setPreferredWidth(50);
      
       
       
      // jTable1.setModel(mode);
       
     
    }catch(SQLException ex ){
     JOptionPane.showMessageDialog(null, ex);
}
    
      
      }
     public void call_table_ITEMSITE(){
      
           try{
           
             String sql="SELECT `ITEM_ID`,`CREDIT`, `DATES`, `SITE`, `PURPOSE`, `EMP`, `NUM_ID` FROM `eqipement_trans` WHERE SITE='"+site.getSelectedItem()+"'";
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
        
         //TableColumn col7=jTable1.getColumnModel().getColumn(7);
      //  TableColumn col8=jTable1.getColumnModel().getColumn(8);
       
       
       
      
       
       col0.setPreferredWidth(150);
       col1.setPreferredWidth(20);
       col2.setPreferredWidth(20);
       col3.setPreferredWidth(20);
       col4.setPreferredWidth(50);
       col5.setPreferredWidth(50);
       col6.setPreferredWidth(150);
    // col7.setPreferredWidth(80);
     //  col8.setPreferredWidth(50);
      
       
       
      // jTable1.setModel(mode);
       
     
    }catch(SQLException ex ){
     JOptionPane.showMessageDialog(null, ex);
}
    
      
      }
    
     public void call_table_ITEM_site(){
      
           try{
           
             String sql="SELECT `ITEM_ID`,`CREDIT`,`DATES`, `SITE`, `PURPOSE`, `EMP`, `NUM_ID` FROM `eqipement_trans` WHERE site='"+site.getSelectedItem()+"'";
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
        
         //TableColumn col7=jTable1.getColumnModel().getColumn(7);
     //   TableColumn col8=jTable1.getColumnModel().getColumn(8);
       
       
       
      
       
       col0.setPreferredWidth(150);
       col1.setPreferredWidth(20);
       col2.setPreferredWidth(20);
       col3.setPreferredWidth(20);
       col4.setPreferredWidth(50);
       col5.setPreferredWidth(50);
       col6.setPreferredWidth(150);
    // col7.setPreferredWidth(80);
      // col8.setPreferredWidth(50);
      
       
       
      // jTable1.setModel(mode);
       
     
    }catch(SQLException ex ){
     JOptionPane.showMessageDialog(null, ex);
}
    
      
      }
     
     
      public void call_table_ITEM_sitesS(){
      
           try{
           
             String sql="SELECT `ITEM_ID`, `DEBIT`, `CREDIT`,`DATES`, `SITE`, `PURPOSE`, `EMP`, `NUM_ID` FROM `eqipement_trans` WHERE ITEM_ID='"+mat.getSelectedItem()+"' and '"+site.getSelectedItem()+"'";
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
        
         TableColumn col7=jTable1.getColumnModel().getColumn(7);
      //  TableColumn col8=jTable1.getColumnModel().getColumn(8);
       
       
       
      
       
       col0.setPreferredWidth(150);
       col1.setPreferredWidth(20);
       col2.setPreferredWidth(20);
       col3.setPreferredWidth(20);
       col4.setPreferredWidth(50);
       col5.setPreferredWidth(50);
       col6.setPreferredWidth(150);
     col7.setPreferredWidth(80);
       //col8.setPreferredWidth(50);
      
       
       
      // jTable1.setModel(mode);
       
     
    }catch(SQLException ex ){
     JOptionPane.showMessageDialog(null, ex);
}
    
      
      }
    
      
      public void report(){
      
       try{
                 
                 
                   String sqls="Select path from pathn";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
            if(rs.next()){
             
                String sum=rs.getString("path");
                  
                 String NameFiles=sum;
            
            String NameFile=""+NameFiles+"Equipement_inv.jrxml";
                 
                 
                 
                JasperDesign jd=JRXmlLoader.load(NameFile);
                 
                String sql="SELECT * FROM  eqipement_trans where ITEM_ID ='"+mat.getSelectedItem ()+"'";
              
    
                JRDesignQuery nq=new JRDesignQuery();
                nq.setText(sql);
                jd.setQuery(nq);
               
                 JasperReport jr =JasperCompileManager.compileReport(jd);
                 JasperPrint jp=JasperFillManager.fillReport(jr,null,con);
                 JasperViewer.viewReport(jp,false);
                 
                 
                 JasperViewer ms= new JasperViewer(jp);
                 ms.setAlwaysOnTop (true);
                 ms.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
             }
             }catch(Exception ex){
                  JOptionPane.showMessageDialog(null, ex);   
             }
        
      
      }
       public void reportsite(){
      
       try{
                 
                 
                   String sqls="Select path from pathn";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
            if(rs.next()){
             
                String sum=rs.getString("path");
                  
                 String NameFiles=sum;
            
            String NameFile=""+NameFiles+"Equipement_inv_site.jrxml";
                 
                 
                 
                JasperDesign jd=JRXmlLoader.load(NameFile);
                 
                String sql="SELECT * FROM  eqipement_trans where site ='"+site.getSelectedItem ()+"'";
              
    
                JRDesignQuery nq=new JRDesignQuery();
                nq.setText(sql);
                jd.setQuery(nq);
               
                 JasperReport jr =JasperCompileManager.compileReport(jd);
                 JasperPrint jp=JasperFillManager.fillReport(jr,null,con);
                 JasperViewer.viewReport(jp,false);
                 
                 
                 JasperViewer ms= new JasperViewer(jp);
                 ms.setAlwaysOnTop (true);
                 ms.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
             }
             }catch(Exception ex){
                  JOptionPane.showMessageDialog(null, ex);   
             }
        
      
      }
      
       public void report_amortissement(){
      
       try{
                 
                 
                   String sqls="Select path from pathn";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
            if(rs.next()){
             
                String sum=rs.getString("path");
                  
                 String NameFiles=sum;
            
            String NameFile=""+NameFiles+"inv_equip.jrxml";
                 
                 
                 
                JasperDesign jd=JRXmlLoader.load(NameFile);
                 
                String sql="SELECT * FROM  equipement_amortissement order by CAT";
              
    HashMap param= new HashMap();
    param.put("date1", webDateField2.getText());
                JRDesignQuery nq=new JRDesignQuery();
                nq.setText(sql);
                jd.setQuery(nq);
               
                 JasperReport jr =JasperCompileManager.compileReport(jd);
                 JasperPrint jp=JasperFillManager.fillReport(jr,param,con);
                 JasperViewer.viewReport(jp,false);
                 
                 
                 JasperViewer ms= new JasperViewer(jp);
                 ms.setAlwaysOnTop (true);
                 ms.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
             }
             }catch(Exception ex){
                  JOptionPane.showMessageDialog(null, ex);   
             }
        
      
      }
      
//      public void amortissement(){
//        int row= jTable1.getSelectedRow();
//          String Table_click = (jTable1.getModel().getValueAt(row,0). toString());
//          String Modele = null;
//         {
//        try{
//            String sql="select * from eqipement_in where ITEM_ID='"+Table_click+"' ";
//           
//            pst=con.prepareStatement(sql);
//            rs=pst.executeQuery();
//            while(rs.next()){
//                Double sum=rs.getDouble("price");
//                  price.setText(sum.toString());    
//                  Modele=rs.getString("MODEL");
//                  Double date=rs.getDouble("DATES");
//            }
//            }
//        catch(Exception ex){
//          JOptionPane.showMessageDialog(null, ex);    
//        }
//        
//         try{
//            String sql="select * from billa_mobilier where NAME='"+Modele+"' ";
//           
//            pst=con.prepareStatement(sql);
//            rs=pst.executeQuery();
//            while(rs.next()){
//                Double pour=rs.getDouble("TYPE");
//                Double mois=rs.getDouble("GATECORIE");
//                
//                Double jour=mois*30;
//             // Date  date = 
//              SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy.MM.dd");
//         String addDate1 = dateFormat1.format(new Date());
//         
//                 
//            }
//            }
//        catch(Exception ex){
//          JOptionPane.showMessageDialog(null, ex);    
//        }
//        
//        
//    }
//      
//      }
      
      public void calcul_amorti(){
              String CATT = null;
          Double pour=null,PRICEE=null;
          float daysBetween = 0;
          Date date2 = null;
          
          
          Calendar calendar1 = Calendar.getInstance();
    Calendar calendar2 = Calendar.getInstance();
  
          
          
          
          try{
            String sql="select * from eqipement_trans where ITEM_ID ='"+mat.getSelectedItem()+"' ";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                CATT=rs.getString("CAT");}
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
           try{
            String sql="select * from equipement_in where ITEM_ID='"+mat.getSelectedItem()+"' ";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
               PRICEE=rs.getDouble("PRICE");
               
            
 date2=rs.getDate("DATES");
        // dateBeforeString = dateFormat1.format(date2);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
          
           try{
            String sql="select * from amortissement where CAT='"+CATT+"' ";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
               pour=rs.getDouble("VAL");
               }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }

          
           
 SimpleDateFormat myFormat = new SimpleDateFormat("dd MM yyyy");
  SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd MM yyyy");
       String dateAfterString = dateFormat1.format(webDateField2.getDate());
        String dateBeforeString = myFormat.format(date2);
         
	
	 try {
	       Date dateBefore = myFormat.parse(dateBeforeString);
	       Date dateAfter = myFormat.parse(dateAfterString);
	       long difference = dateAfter.getTime() - dateBefore.getTime();
	      daysBetween = (difference / (1000*60*60*24));
              
               /* You can also convert the milliseconds to days using this method
                * float daysBetween = 
                *         TimeUnit.DAYS.convert(difference, TimeUnit.MILLISECONDS)
                */
	      
	 } catch (Exception e) {
	       e.printStackTrace();
	 }
          
          String  aaa= ""+daysBetween;
         Double b=pour*30;//total jour d'amorticement
         Double c=PRICEE/b;//motant par jour
         Double d=Double.parseDouble(aaa); // Nbre de date entre deux date
         
        Double A=b-d; // JOUR RESTANTS
       Double B= A*c; //AMORTISEMENT ACTUEL
      String C= String.format("%.2f",B);
//        sold3.setText(C);
         
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
        jPanel7 = new javax.swing.JPanel();
        mat = new javax.swing.JComboBox<>();
        jPanel8 = new javax.swing.JPanel();
        site = new javax.swing.JComboBox<>();
        webDateField1 = new com.alee.extended.date.WebDateField();
        webDateField2 = new com.alee.extended.date.WebDateField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu2 = new javax.swing.JMenu();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();

        setBackground(new java.awt.Color(255, 255, 255));
        setTitle("Equipement Inv.");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_Edit_Property_16px.png"))); // NOI18N

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Materials", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        mat.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        mat.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Materials" }));
        mat.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        mat.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                matPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mat, 0, 419, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(mat, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Site", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        site.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        site.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Site" }));
        site.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        site.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                sitePopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(site, 0, 222, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(site, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        webDateField1.setEditable(false);
        webDateField1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        webDateField2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("Date1");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("Date2");

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(jTable2);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(webDateField1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(47, 47, 47)
                                .addComponent(webDateField2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(webDateField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(webDateField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

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
        jTable1.setRowHeight(30);
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 337, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jMenu2.setText("X");
        jMenu2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jMenu2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu2MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu2);

        jMenu1.setText("File");

        jMenuItem1.setText("Print");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem3.setText("Inventory Sheet");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        setBounds(15, 10, 1067, 498);
    }// </editor-fold>//GEN-END:initComponents

    private void jMenu2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu2MouseClicked
        this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu2MouseClicked

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
       if(site.getSelectedItem().equals("Select Site")){
       report(); 
       }else{
        reportsite();
       }
        
       // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void matPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_matPopupMenuWillBecomeInvisible
     call_table_ITEM();
        // TODO add your handling code here:
    }//GEN-LAST:event_matPopupMenuWillBecomeInvisible

    private void sitePopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_sitePopupMenuWillBecomeInvisible
    call_table_ITEM_site();// TODO add your handling code here:
    }//GEN-LAST:event_sitePopupMenuWillBecomeInvisible

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
truncate();
       call_tablein();
saveS();
report_amortissement();
//Amortissement();        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JComboBox<String> mat;
    private javax.swing.JComboBox<String> site;
    private com.alee.extended.date.WebDateField webDateField1;
    private com.alee.extended.date.WebDateField webDateField2;
    // End of variables declaration//GEN-END:variables
}
