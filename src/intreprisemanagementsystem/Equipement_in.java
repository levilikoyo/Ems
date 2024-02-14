/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intreprisemanagementsystem;


import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.basic.BasicInternalFrameUI;
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
public class Equipement_in extends javax.swing.JInternalFrame {

    Connection con=null;
PreparedStatement pst=null;
ResultSet rs= null;
//DefaultTableModel mode;
 String rolls;
 String NUM_ID;
 DefaultTableModel mode;
 
 int xx=0;
int yy=0;
 
    public Equipement_in() {
        initComponents();
              con=JavaDbConnect.dbConnect();
//        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
//        BasicInternalFrameUI bui= (BasicInternalFrameUI) this.getUI();
//       bui.setNorthPane(null);
       // Call_ID_TO_material();
        jDateChooser1.setDate(new Date());
        
         unity.addItem("pcs");
        unity.addItem("Sacs");
        unity.addItem("kg");
        unity.addItem("Ltrs");
       unity.addItem("rouleaux");
       unity.addItem("tours");
        unity.addItem("cources");
        unity.addItem("Jours");
        unity.addItem("Boite");
        unity.addItem("Longueur");
        unity.addItem("Mois");
           unity.addItem("Sachet");
        unity.addItem("m2");
        unity.addItem("m3");
        unity.addItem("gm");
        unity.addItem("Other");
         num.setHorizontalAlignment( num.CENTER);
       //  select();
       call_table_num();
      Call_I_material();
    }
    public void Call_I_material()
    {
         {
        try{
            String sql="select distinct(cat) from amortissement";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                String sum=rs.getString("cat");
                 cat.addItem(sum);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
        
    }
    }
     /* public ImageIcon ResizeImage(String ImagePath, byte[] pic)
    {
        ImageIcon MyImage = null;
        if(ImagePath !=null)
        {
            MyImage = new ImageIcon(ImagePath);
        }else
        {
            MyImage = new ImageIcon (ImagePath);
        }
        Image img =MyImage.getImage();
        Image newImg= img.getScaledInstance(images.getWidth(),images.getHeight(),Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(newImg);
        return image;
    }*/
                                 public void call_table_num(){
      
           try{
           
             String sql="SELECT distinct(NUM) FROM `eqipement_trans` where emp='LOG'";
     //  String sql="SELECT ID, `NUM_FACTURE`, `DATE_FACTURE`, `DESCRIPTION`, `DEBIT_QTY`, `DEBIT_PU`, `DEBIT_PT`, `DEBIT_TVA`, `CREDIT_QTY`, `CREDIT_PU`, `CREDIT_PT`, `CREDIT_TVA`, `DATES`, `NUM` FROM facture_fournisseur where compte='"+jComboBox6.getSelectedItem()+"'";
       pst = con.prepareStatement(sql);
      rs= pst.executeQuery();
      
       
       jTable2.setModel(DbUtils.resultSetToTableModel(rs));
       mode=new DefaultTableModel();
    }catch(SQLException ex ){
     JOptionPane.showMessageDialog(null, ex);
}
    
   }
    public void select(){
    
     try{
            String sql="select NAME from billa_mobilier";
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                String sum=rs.getString("NAME");
//                model.addItem(sum);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
    
    }
    
    
    //BROUSWER
      String imgPath; 
    
     
     
     public void etroll()
     {
         try{
            String sql="SELECT NUM FROM  eqipement_trans where emp='LOG' ORDER BY NUM DESC LIMIT 1";
            

            
             pst=con.prepareStatement(sql);
             rs=pst.executeQuery();
             if(rs.next()){
                 String rl=rs.getString("NUM");
                 int ln=rl.length();
                 String stxt=rl.substring(0,13);
                 String snum=rl.substring(13,ln);
                 int n = Integer.parseInt(snum);
                 n++;
                 snum=Integer.toString(n);
                 rolls=stxt+snum ;
                 
                 
                 
             }else{
                 rolls="EQP-STOCK-IN/1001";
             }
                   }catch(Exception e){
             JOptionPane.showMessageDialog(null, e); 
             
         }
     }
  public void numid()
     {
         try{
            String sql="SELECT NUM_ID FROM eqipement_trans ORDER BY NUM_ID DESC LIMIT 1";
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
                 NUM_ID="ID-EQP1001";
             }
              }catch(Exception e){
             JOptionPane.showMessageDialog(null, e); 
             
         }
         }
     
     
   
        public void save_TRANS(){
            etroll();
            numid();
           
if(num.getText().equals("")){
try{
    String sql="INSERT INTO eqipement_trans(ITEM_ID,ITEM,DESCR,SERIAL,MODEL,STATUT,DEBIT,CREDIT,NUM_ID,NUM,EMP,PURPOSE,DATES,SITE,CAT,UNITY,PRICE) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     
    pst=con.prepareStatement(sql);
    pst.setString(1,item_id.getText());
    pst.setString(2,item.getText());
    pst.setString(3,desc.getText()+" || "+item_id.getText());
    pst.setString(4,serial.getText());
    pst.setString(5,model.getText());
     pst.setString(6,statut.getSelectedItem().toString());
     pst.setString(7,qty.getText());
     
      pst.setString(8,"0");
       pst.setString(9,NUM_ID);
        pst.setString(10,rolls);
        
          pst.setString(11,"LOG");
        pst.setString(12,"STOCK LOG");
        
         SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy.MM.dd");
         String addDate1 = dateFormat1.format(jDateChooser1.getDate());
         pst.setString(13, addDate1);
       pst.setString(14,"LOG");
       pst.setString(15,cat.getSelectedItem().toString());
         pst.setString(16,unity.getSelectedItem().toString());
         pst.setString(17,price.getText());
        
        
      
    
    pst.executeUpdate();
     // JOptionPane.showMessageDialog(null,"Datas Saved");
    }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}
 JOptionPane.showMessageDialog(null, "here");
num();
}else{

try{
     String sql="INSERT INTO eqipement_trans(ITEM_ID,ITEM,DESCR,SERIAL,MODEL,STATUT,DEBIT,CREDIT,NUM_ID,NUM,EMP,PURPOSE,DATES,SITE,CAT,UNITY,PRICE) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     
    pst=con.prepareStatement(sql);
    pst.setString(1,item_id.getText());
    pst.setString(2,item.getText());
    pst.setString(3,desc.getText()+" || "+item_id.getText());
    pst.setString(4,serial.getText());
    pst.setString(5,model.getText());
     pst.setString(6,statut.getSelectedItem().toString());
     pst.setString(7,qty.getText());
     
      pst.setString(8,"0");
       pst.setString(9,NUM_ID);
        pst.setString(10,num.getText());
        
          pst.setString(11,"LOG");
        pst.setString(12,"STOCK LOG");
        
         SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy.MM.dd");
         String addDate1 = dateFormat1.format(jDateChooser1.getDate());
         pst.setString(13, addDate1);
       pst.setString(14,"LOG");
       pst.setString(15,cat.getSelectedItem().toString());
       pst.setString(16,unity.getSelectedItem().toString());
       pst.setString(17,price.getText());
        
        
      
    
    pst.executeUpdate();
     // JOptionPane.showMessageDialog(null,"Datas Saved");
    }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}
}
 
    
 
 
 }   
     
      public void update(){
 try{//`ITEM_ID`, `ITEM`, `DESCR`, `SERIAL`, `MODEL`, `STATUT`, `DEBIT`, `CREDIT`, `NUM_ID`, `NUM`, `EMP`, `PURPOSE`, `DATES`, `SITE`, `CAT`
    String sql="UPDATE  eqipement_trans SET  ITEM_ID=?,ITEM=?,DESCR=?,SERIAL=?,MODEL=?,STATUT=?,DEBIT=?,PURPOSE=?,DATES=?,SITE=?,CAT=?,UNITY=?,PRICE=? WHERE NUM_ID='"+id.getText()+"'";
     
    pst=con.prepareStatement(sql);
    pst.setString(1,item_id.getText());
    pst.setString(2,item.getText());
    pst.setString(3,desc.getText()+" || "+item_id.getText());
    pst.setString(4,serial.getText());
    pst.setString(5,model.getText());
     pst.setString(6,statut.getSelectedItem().toString());
     pst.setString(7,qty.getText());
      pst.setString(8,"Stock log");
       SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy.MM.dd");
         String addDate1 = dateFormat1.format(jDateChooser1.getDate());
         pst.setString(9, addDate1);
        pst.setString(10,"Stock log");
         pst.setString(11,cat.getSelectedItem().toString());
     
      pst.setString(12,unity.getSelectedItem().toString());
      pst.setString(13,price.getText());
  
       // pst.setString(11,num.getText());
        
//         InputStream img = new FileInputStream(new File(imgPath));
//         pst.setBlob(10, img);
    
   
  
      
    
    pst.executeUpdate();
     // JOptionPane.showMessageDialog(null,"Datas Saved");
    }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}
    
try{
    String sql="UPDATE  `etat` SET `ITEM_ID`=?,`ITEM`=?,`DESCR`=?,`STATUS`=? Where NUM_ID='"+id.getText()+"'";
     
    pst=con.prepareStatement(sql);
    pst.setString(1,item_id.getText());
    pst.setString(2,item.getText());
    pst.setString(3,desc.getText());
    pst.setString(4,statut.getSelectedItem().toString());
  
    
    pst.executeUpdate();
     // JOptionPane.showMessageDialog(null,"Datas Saved");
    }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}
 
 }
 
 
 
     
     
     public void savestatus(){

 try{
    String sql="INSERT INTO `etat`(`ITEM_ID`,`ITEM`,`DESCR`,`STATUS`,`NUM_ID`) VALUES (?,?,?,?,?)";
     
    pst=con.prepareStatement(sql);
    pst.setString(1,item_id.getText());
    pst.setString(2,item.getText());
    pst.setString(3,desc.getText());
    pst.setString(4,statut.getSelectedItem().toString());
    pst.setString(5,NUM_ID);
  
    
    pst.executeUpdate();
     // JOptionPane.showMessageDialog(null,"Datas Saved");
    }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}
    
 
 
 }
     
      public void UDATEtatus(){

 try{
    String sql="UPDATE  `etat` SET `ITEM_ID`=?,`ITEM`=?,`DESCR`=?,`STATUS`=? Where NUM_ID='"+id.getText()+"'";
     
    pst=con.prepareStatement(sql);
    pst.setString(1,item_id.getText());
    pst.setString(2,item.getText());
    pst.setString(3,desc.getText());
    pst.setString(4,statut.getSelectedItem().toString());
  
    
    pst.executeUpdate();
     // JOptionPane.showMessageDialog(null,"Datas Saved");
    }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}
    
 
 
 }
     

      
      
      
     
     
      public void save_TRANSs(){
 etroll();
 numid();
 try{
    String sql="INSERT INTO `eqipement_trans`(`ITEM_ID`, `ITEM`, `DESCR`, `SERIAL`, `MODEL`, `STATUT`, `DEBIT`, `CREDIT`, `NUM_ID`, `NUM`, `EMP`, `PURPOSE`, `DATES`, `SITE`, `CAT`,`UNITY`)  VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     
    pst=con.prepareStatement(sql);
    pst.setString(1,item_id.getText());
    pst.setString(2,item.getText());
    pst.setString(3,desc.getText());
    pst.setString(4,serial.getText());
    pst.setString(5,model.getText());
     pst.setString(6,statut.getSelectedItem().toString());
     pst.setString(7,qty.getText());
     
      pst.setString(8,"0");
       pst.setString(9,NUM_ID);
        pst.setString(10,num.getText());
        
        pst.setString(11,"LOG");
        pst.setString(12,"STOCK LOG");
        
          SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy.MM.dd");
         String addDate1 = dateFormat1.format(jDateChooser1.getDate());
         pst.setString(13, addDate1);
       pst.setString(14,"LOG");
        pst.setString(15,cat.getSelectedItem().toString());
         pst.setString(16,unity.getSelectedItem().toString());
      
    
    pst.executeUpdate();
     // JOptionPane.showMessageDialog(null,"Datas Saved");
    }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}
    
 
 
 }
      
          

     
     
     
      
 
public void refresh(){

item_id.setText("");
item.setText("");
desc.setText("");
serial.setText("");
model.setText("");
//statut.setText("");
qty.setText("1");
//pathphoto.setText("");
price.setText("0.00");
     
unity.setSelectedItem("Unity");
cat.setSelectedItem("......");
id.setText("");
      
}



public void num(){
     try{
            String sqls="select max(num) from  eqipement_trans WHERE EMP='LOG'";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
            while(rs.next()){
                String add1 = rs.getString("max(num)");
              num.setText(add1);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
    
    }
              
               public void call_table(){
      
           try{
           
             String sql="SELECT `ITEM_ID`, `ITEM`, `DESCR`, `SERIAL`, `MODEL`, `DATES`,`NUM_ID` FROM `eqipement_trans` WHERE NUM='"+num.getText()+"'";
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
       col3.setPreferredWidth(20);
       col4.setPreferredWidth(20);
       col5.setPreferredWidth(50);
       col6.setPreferredWidth(50);
     
      
       
       
      // jTable1.setModel(mode);
       
     
    }catch(SQLException ex ){
     JOptionPane.showMessageDialog(null, ex);
}
    
      
      }
               
                 public void call_tableref(){
      
           try{
           
             String sql="SELECT `ITEM_ID`, `ITEM`, `DESCR`, `QTY`, `UNITY`, `DATES`,`NUM_ID` FROM `equipement_in` WHERE NUM='xxxxxx'";
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
       col3.setPreferredWidth(20);
       col4.setPreferredWidth(20);
       col5.setPreferredWidth(50);
       col6.setPreferredWidth(50);
     
      
       
       
      // jTable1.setModel(mode);
       
     
    }catch(SQLException ex ){
     JOptionPane.showMessageDialog(null, ex);
}
    
      
      }
                 
                 
             
                  
                  
               
 
      
       
    
                  
                  
               // String id; 
                 
                   public void select_jTable()
   {
        try{
          int row= jTable1.getSelectedRow();
          String Table_click = (jTable1.getModel().getValueAt(row,6). toString());
          String sql = "SELECT * FROM eqipement_trans WHERE NUM_ID= '"+Table_click+"'";
          pst = con.prepareStatement(sql);
          rs=pst.executeQuery();
          if(rs.next()){
          //`ID`, ``, ``, ``, ``, ``, ``, ``, ``, ``, ``, ``, ``
              
            
              String add1 = rs.getString("ITEM_ID");
             item_id.setText(add1);
               String add2 = rs.getString("ITEM");
              item.setText(add2);
               String add3 = rs.getString("DESCR");
               desc.setText(add3);
               String add4 = rs.getString("SERIAL");
              serial.setText(add4);
               String add5 = rs.getString("MODEL");
               model.setText(add5);
               String add6 = rs.getString("STATUT");
              statut.setSelectedItem(add6);
               String add8 = rs.getString("UNITY");
             unity.setSelectedItem(add8);
             String add99  = rs.getString("NUM_ID");
            id.setText(add99);
            
            String add9S  = rs.getString("PRICE");
            price.setText(add9S);
            
            String add9SS  = rs.getString("CAT");
            cat.setSelectedItem(add9SS);
          }
         
    }catch(SQLException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }
        
   }

 public void delete()
    {
     
         try{
        String sql = "DELETE FROM equipement_in WHERE NUM_ID=?";
        
         pst = con.prepareStatement(sql);
         pst.setString(1,id.getText());
         pst.executeUpdate();
        // JOptionPane.showMessageDialog(null,"delete");
              
     }catch(SQLException | HeadlessException ex ){
         JOptionPane.showMessageDialog(null,ex);
     } 
         
          try{
        String sql = "DELETE FROM  eqipement_trans WHERE NUM_ID=?";
        
         pst = con.prepareStatement(sql);
         pst.setString(1,id.getText());
         pst.executeUpdate();
         JOptionPane.showMessageDialog(null,"Data Deleted");
              
     }catch(SQLException | HeadlessException ex ){
         JOptionPane.showMessageDialog(null,ex);
     } 
         
    }
 
 
  public void attCall_roll()
    {
      
         String A =JOptionPane.showInternalInputDialog(rootPane, closable);
        try{       
             
     String sql="select distinct(NUM) from equipement_in ";
            
           
             pst=con.prepareStatement(sql);
          //  pst.setString(1, A);
            rs=pst.executeQuery();
            while(rs.next()){
             String add1 = rs.getString("distinct(NUM)");
              A=(add1);
              
              
            }
             
            
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
        
    }
 
 





 public void report()
     {
          
     
             try{
                 String tmp=num.getText();
                 
                 
                 String sqls="Select path from pathn";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
            if(rs.next()){
             
                String sum=rs.getString("path");
                  
                 String NameFiles=sum;
            
            String NameFile=""+NameFiles+"Reportmaterielin.jrxml";
                 
                 
                 
                JasperDesign jd=JRXmlLoader.load(NameFile);
                 
                 
                 
                // String report ="C:\\Users\\Dosh\\Documents\\NetBeansProjects\\intreprisemanagementsystem\\src\\intreprisemanagementsystem\\maretiauxrepport.jrxml";
                // JasperDesign jd=JRXmlLoader.load("C:\\Users\\Dosh\\Documents\\NetBeansProjects\\intreprisemanagementsystem\\src\\intreprisemanagementsystem\\Fiche_Materiaux_out.jrxml");
                String sql="select * from eqipement_trans INNER JOIN equipement_in ON eqipement_trans.NUM_ID=equipement_in.NUM_ID where NUM='"+num.getText()+" '";
                //Select * from ohada_trans INNER JOIN projet ON ohada_trans.BUSS=projet.PROJET_NUM where buss='"+tmp+" ' and DATES BETWEEN '"+date1+"' AND '"+date2+"'AND CODE ='"+journal_caisse.jComboBox1.getSelectedItem()+"'  order by DATES,ohada_trans.NUM";
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
         
//setDefaultCloseOperation(DISPOSE_ON_CLOSE); 
     }
 
 public void report_all()
     {
          
     
             try{
                 String tmp=num.getText();
                 
                 
                 String sqls="Select path from pathn";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
            if(rs.next()){
             
                String sum=rs.getString("path");
                  
                 String NameFiles=sum;
            
            String NameFile=""+NameFiles+"Equipement_in_all.jrxml";
                 
                 
                 
                JasperDesign jd=JRXmlLoader.load(NameFile);
                 
                 
                 
                // String report ="C:\\Users\\Dosh\\Documents\\NetBeansProjects\\intreprisemanagementsystem\\src\\intreprisemanagementsystem\\maretiauxrepport.jrxml";
                // JasperDesign jd=JRXmlLoader.load("C:\\Users\\Dosh\\Documents\\NetBeansProjects\\intreprisemanagementsystem\\src\\intreprisemanagementsystem\\Fiche_Materiaux_out.jrxml");
                String sql="select * from equipement_in ";
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
         
//setDefaultCloseOperation(DISPOSE_ON_CLOSE); 
     }
   String Check;
  public void Check()
    {
      
         {
        try{
            String sql="Select ITEM_ID from equipement_in where ITEM_ID='"+item_id.getText()+"'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
               Check=rs.getString("ITEM_ID");
                 
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
    }
    }
  
                     public void select_jTable_small()
   {
       
       
        try{
          int row= jTable2.getSelectedRow();
         // String rows =jTable1.getName()
          String Table_click = (jTable2.getModel().getValueAt(row,0). toString());
          String sql = "SELECT * FROM eqipement_trans WHERE NUM= '"+Table_click+"'";
          pst = con.prepareStatement(sql);
          rs=pst.executeQuery();
          if(rs.next()){
        //`ITEM_ID`, `ITEM`, `DESCR`, `QTY`, `UNITY`, `DATES`, `NUM`, `NUM_ID`
               String add11= rs.getString("NUM");
            num.setText(add11);
          }
          
          
         
    }catch(SQLException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }
       
          call_table();
       }
public void call_table_num_Search(){
      
           try{
           
             String sql="SELECT distinct(NUM) FROM `equipement_TRANS` where num like '"+jTextField1.getText()+"%'";
     //  String sql="SELECT ID, `NUM_FACTURE`, `DATE_FACTURE`, `DESCRIPTION`, `DEBIT_QTY`, `DEBIT_PU`, `DEBIT_PT`, `DEBIT_TVA`, `CREDIT_QTY`, `CREDIT_PU`, `CREDIT_PT`, `CREDIT_TVA`, `DATES`, `NUM` FROM facture_fournisseur where compte='"+jComboBox6.getSelectedItem()+"'";
       pst = con.prepareStatement(sql);
      rs= pst.executeQuery();
      
       
       jTable2.setModel(DbUtils.resultSetToTableModel(rs));
       mode=new DefaultTableModel();
    }catch(SQLException ex ){
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
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        item_id = new javax.swing.JTextField();
        item = new javax.swing.JTextField();
        desc = new javax.swing.JTextField();
        serial = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        qty = new javax.swing.JTextField();
        unity = new javax.swing.JComboBox<>();
        num = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        price = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jDateChooser1 = new com.alee.extended.date.WebDateField();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        statut = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        model = new javax.swing.JTextField();
        cat = new javax.swing.JComboBox<>();
        id = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jTextField1 = new javax.swing.JTextField();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu5 = new javax.swing.JMenu();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();

        setBorder(null);
        setTitle("Equipement In");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_Maintenance_16px.png"))); // NOI18N
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                formMouseDragged(evt);
            }
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                formMouseMoved(evt);
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

            }
        ));
        jTable1.setRowHeight(30);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("Item ID");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("Item");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("Desc.");

        item_id.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        item_id.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        item_id.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                item_idKeyReleased(evt);
            }
        });

        item.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        item.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        item.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                itemKeyReleased(evt);
            }
        });

        desc.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        desc.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        serial.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        serial.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        serial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                serialActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("S.Nber");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addGap(13, 13, 13)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(serial)
                    .addComponent(desc, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
                    .addComponent(item_id)
                    .addComponent(item))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(item_id, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(item, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(desc, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(serial, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel7.setText("Date");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel9.setText("Qty");

        qty.setEditable(false);
        qty.setBackground(new java.awt.Color(242, 242, 241));
        qty.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        qty.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        qty.setText("1");
        qty.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        qty.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                qtyKeyTyped(evt);
            }
        });

        unity.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        unity.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Unity" }));
        unity.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        num.setEditable(false);
        num.setBackground(new java.awt.Color(240, 240, 241));
        num.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        num.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTextField2.setEditable(false);
        jTextField2.setBackground(new java.awt.Color(240, 240, 241));
        jTextField2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTextField2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField2.setText("USD");
        jTextField2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        price.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        price.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        price.setText("0.00");
        price.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        price.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                priceKeyTyped(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel8.setText("T.Pr.");

        jDateChooser1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(num, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel9)
                            .addComponent(jLabel8))
                        .addGap(14, 14, 14)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(price)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(qty, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jDateChooser1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(unity, 0, 153, Short.MAX_VALUE))))))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(num, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(qty, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(unity, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(price, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setText("Model");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setText("Statut");

        statut.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        statut.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "New", "Used" }));
        statut.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel12.setText("Cat.");

        model.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        model.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        cat.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        cat.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "......" }));
        cat.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        id.setEditable(false);
        id.setBackground(new java.awt.Color(240, 240, 241));
        id.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(id)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel12))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statut, 0, 297, Short.MAX_VALUE)
                            .addComponent(model)
                            .addComponent(cat, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(model, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(statut, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(cat, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(id, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

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

        jTextField1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTextField1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jTextField1))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jMenu5.setText("X");
        jMenu5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jMenu5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu5MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu5);

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

        jMenu6.setText("Print");

        jMenuItem8.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem8.setText("Print");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem8);

        jMenuItem9.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.ALT_DOWN_MASK | java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem9.setText("Print All Sheet");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem9);

        jMenu1.add(jMenu6);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");

        jMenu3.setText("Select");

        jMenuItem5.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem5.setText("All");
        jMenu3.add(jMenuItem5);

        jMenuItem6.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.ALT_DOWN_MASK));
        jMenuItem6.setText("Sheet");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem6);

        jMenu2.add(jMenu3);

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

        setBounds(50, 10, 957, 400);
    }// </editor-fold>//GEN-END:initComponents

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
select_jTable();
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable1MouseClicked

    private void item_idKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_item_idKeyReleased
/*Check();
if(Check.isEmpty())
{}else{
JOptionPane.showMessageDialog(null,"Wrong Data Item Exists","Error",JOptionPane.ERROR_MESSAGE);
item_id.setText("");
Check="";
}
*/
// TODO add your handling code here:
    }//GEN-LAST:event_item_idKeyReleased

    private void itemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_itemKeyReleased
          // TODO add your handling code here:
    }//GEN-LAST:event_itemKeyReleased

    private void qtyKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_qtyKeyTyped
        char c=evt.getKeyChar();
        if(!(Character.isDigit(c) || (c==KeyEvent.VK_BACK_SPACE)||c==KeyEvent.VK_DELETE ||c==KeyEvent.VK_PERIOD )){
            evt.consume();

        }          // TODO add your handling code here:
    }//GEN-LAST:event_qtyKeyTyped

    private void serialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_serialActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_serialActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
if(id.getText().equals("")){
save_TRANS();
}else{
update();
}
     

 call_table_num();
 call_table();
refresh();// TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
refresh();
num.setText("");
call_tableref();
// TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
delete();
refresh();
call_table();// TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
attCall_roll();        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void formInternalFrameActivated(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameActivated
    // TODO add your handling code here:
    }//GEN-LAST:event_formInternalFrameActivated

    private void formInternalFrameClosed(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosed
       // TODO add your handling code here:
    }//GEN-LAST:event_formInternalFrameClosed

    private void formMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_formMouseMoved

    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
         // TODO add your handling code here:
    }//GEN-LAST:event_formMouseDragged

    private void jMenu5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu5MouseClicked
this.dispose(); 
logs.equipement_in.setText("");// TODO add your handling code here:
    }//GEN-LAST:event_jMenu5MouseClicked

    private void jPanel1MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseDragged
int x=evt.getXOnScreen();
       int y=evt.getYOnScreen();
       this.setLocation(x-xx, y-yy);        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel1MouseDragged

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
report();          // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
report_all();        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        select_jTable_small();        // TODO add your handling code here:
    }//GEN-LAST:event_jTable2MouseClicked

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
        call_table_num_Search();        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1KeyReleased

    private void priceKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_priceKeyTyped
 char c=evt.getKeyChar();
        if(!(Character.isDigit(c) || (c==KeyEvent.VK_BACK_SPACE)||c==KeyEvent.VK_DELETE ||c==KeyEvent.VK_PERIOD )){
            evt.consume();

        }          // TODO add your handling code here:
    }//GEN-LAST:event_priceKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cat;
    private javax.swing.JTextField desc;
    private javax.swing.JTextField id;
    private javax.swing.JTextField item;
    private javax.swing.JTextField item_id;
    private com.alee.extended.date.WebDateField jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField model;
    private javax.swing.JTextField num;
    private javax.swing.JTextField price;
    private javax.swing.JTextField qty;
    private javax.swing.JTextField serial;
    private javax.swing.JComboBox<String> statut;
    private javax.swing.JComboBox<String> unity;
    // End of variables declaration//GEN-END:variables
}
