/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intreprisemanagementsystem;

import com.alee.laf.WebLookAndFeel;
//import static intreprisemanagementsystem.journal_caisse.jTable1;
import java.awt.Toolkit;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
//import net.proteanit.sql.DbUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Dosh
 */
public class excel extends javax.swing.JFrame {
 Connection con=null;
PreparedStatement pst=null;
ResultSet rs= null;
String rolls,etrolls;
    DefaultTableModel mode;
    public excel() {
        initComponents();
           con=JavaDbConnect.dbConnect();
         setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("icons8_Raspberry_Pi_48px_1.png")));
         call();
    }
    public void call(){
 try{
            String sql="select * from projet";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                String sum=rs.getString("PROJET_NUM");
                 buss.addItem(sum);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex); }  
    }
    
    public void inport(){
    JFileChooser fc = new JFileChooser();
    int option= fc.showSaveDialog(null);
    if(option==JFileChooser.APPROVE_OPTION){
    String filename=fc.getSelectedFile().getName();
    String path= fc.getSelectedFile().getParentFile().getPath();
    int len = filename.length();
    String ext="";
    String file="";
    if(len>4){
    ext=filename.substring(len-4,len);
    }
    if(ext.equals(".xls")){
    file = path+"\\"+filename+"";
    
    }else{
   file = path+"\\"+filename+".xls";
    }
    toExcel(jTable1,new File(file));
    }
   
    
    
    }
//    public void importt(){
//    
//    JFileChooser fc = new JFileChooser();
//        int option = fc.showOpenDialog(null);
//        if(option == JFileChooser.APPROVE_OPTION){
//           String filename = fc.getSelectedFile().getName();
//           String path = fc.getSelectedFile().getParentFile().getPath();
//           File files = new File(path+filename);  
//           String line;
//            try {
//                BufferedReader reader = new BufferedReader(new FileReader(files));
//                while((line = reader.readLine()) != null ){
//                    data = new Vector();
//                    StringTokenizer st = new StringTokenizer(line,"\t");
//                    while(st.hasMoreTokens()){
//                        String nexttoken = st.nextToken();
//                        data.add(nexttoken);
//                    }
//                    model.addRow(data);
//                }
//               reader.close();
//            } catch (Exception ex) {
//              //Logger.getLogger(ExportImport.class.getName()).log(Level.SEVERE, null, ex);
//            } 
//        }
//
//    
//    }
    
    public void toExcel(JTable table, File file){
   // File file = null;
   //  DefaultTableModel table= (DefaultTableModel)jTable1.getModel(); 
    try{
    TableModel model=table.getModel();
    FileWriter excel = new FileWriter(file);
    for(int i=0;i<model.getColumnCount();i++){
    excel.write(model.getColumnName(i)+"\t");
    }
    excel.write("\n");
    for(int i=0; i < model.getColumnCount();i++){
     for(int j=0; j < model.getColumnCount();j++){
   excel.write(model.getValueAt(i,j).toString()+"\t");
    }
     excel.write("\n");
    }
    excel.close();
        
        
    }catch(IOException e){
    JOptionPane.showMessageDialog(null, e);
    }
    
    }
    public void imports(){
        
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
      XSSFCell cell10 = excelRow.getCell(10);
      XSSFCell cell11 = excelRow.getCell(11);
      XSSFCell cell12 = excelRow.getCell(12);
     // XSSFCell cell = excelRow.getCell(0);
     excels.addRow(new Object[]{cell.toString(),cell1,cell2,cell3,cell4,cell5,cell6,cell7,cell8,cell9,cell10,cell11,cell12}); 
      }
      
      }catch(FileNotFoundException ex){
      ex.printStackTrace();
      }     catch (IOException ex) {
               ex.printStackTrace();
            }
      
      }

}
public void Budget_Model(){
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
        jTable1.setModel(mode);
        
      
    }


  public void SaveBudget(){
   DefaultTableModel excels= (DefaultTableModel)jTable1.getModel(); 
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
     
        PreparedStatement pst = con.prepareStatement("INSERT INTO `budget_trans`(`ITEM`, `DEBIT`, `CREDIT`, `SOLD`, `PROJET`, `CODE`,`NUM`, `CAT`,`DATES`,`MOIS`, `ANNEE`,`SUB_CAT`,CODE_CAT,CODE_SUB_CAT,`ITEMS`)"
                +"value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ");
       
        
        pst.setString(1, ITEM);
         pst.setString(2, PT);
         pst.setString(3, "0");
         pst.setString(4, "0");
         pst.setString(5, buss.getSelectedItem().toString());
         pst.setString(6, LB);
          pst.setString(7, "");
          
            pst.setString(8, CAT);
             SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(jDateChooser1.getDate());
              pst.setString(9,addDate);
               pst.setString(10, "");
                pst.setString(11, "");
                pst.setString(12,SUB_CAT);
                pst.setString(13, CODE_CAT);
                pst.setString(14, CODE_SUB_CAT);
                pst.setString(15,ITEM);
        
        
        
         pst.executeUpdate();
        // showTableData();
      
         
               //  JOptionPane.showMessageDialog(null,"data saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
        }
   
          
    JOptionPane.showMessageDialog(null,"Tranction Saved");
   
  }
  
  public void EB_Model(){
        mode=new DefaultTableModel();
        mode.addColumn("EMETTEUR");    
        mode.addColumn("PROJET");
        mode.addColumn("SITE/CHANTIER");
        mode.addColumn("LIBELE");
        mode.addColumn("QTY");
        mode.addColumn("UP");
        mode.addColumn("TP");
        mode.addColumn("ORIENTATION");
        jTable1.setModel(mode);

  }
   public void etroll()
     {
         try{
             String sql="SELECT NUM FROM etat_de_besoin ORDER BY NUM DESC LIMIT 1";
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
                 etrolls=stxt+snum;
                 
                 
             }else{
                 //rolls="FICHE/EB/2018/1";
                 etrolls="EB1001";
             }
                 
             
         }catch(Exception e){
             JOptionPane.showMessageDialog(null, e); 
             
         }
     }
    public void Save_EB(){
        etroll();
   DefaultTableModel excels= (DefaultTableModel)jTable1.getModel(); 
   String EBEMET= null, EBQTY,EBPU,EBPT = null, EBPROJECT = null, EBLIBELLE= null ,EBORIENT= null , EBSITE= null  ;
    for(int i=0; i < excels.getRowCount();i++){
      EBEMET  = excels.getValueAt(i,0). toString();
      EBPROJECT= excels.getValueAt(i,1). toString();
      EBSITE = excels.getValueAt(i,2). toString();
      EBLIBELLE = excels.getValueAt(i,3). toString();
      EBQTY = excels.getValueAt(i,4). toString();
      EBPU= excels.getValueAt(i,5). toString();
      EBPT = excels.getValueAt(i,6). toString();
     EBORIENT = excels.getValueAt(i,7). toString();
     
       try {
          //  etroll();
        PreparedStatement pst = con.prepareStatement("INSERT INTO etat_de_besoin(SUP,CHANT,NUM,DET,QTY,PU,PT,DATES,APPROUVATION,EXCECUTE,PRINT,PAY,ORIENTATION,ECRITURE,NUM_TRANS,ETUDE,BUSS) "
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        
        pst.setString(1,EBEMET);
         pst.setString(2,  EBSITE);
        pst.setString(3, etrolls);
         pst.setString(4, EBLIBELLE);
         pst.setString(5,  EBQTY);
         pst.setString(6,  EBPU);
         pst.setString(7, EBPT);
         pst.setString(9, "");
          pst.setString(10,"");
          pst.setString(11,"");
          pst.setString(12,"");
            pst.setString(13,EBORIENT);
              pst.setString(14,"");
                pst.setString(15,"");
                  pst.setString(16,"");
                  pst.setString(17,EBPROJECT);
       
          SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(jDateChooser1.getDate());
         pst.setString(8, addDate);
         
          pst.executeUpdate();
        
              //   JOptionPane.showMessageDialog(null,"CLERCK_IN  SUCCESSFUL BONNE JOURNEE!!!        ");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
   
          
   
   
  }
     JOptionPane.showMessageDialog(null,"Tranction Saved");
    }
    
      public void CAISSE_Model(){
        mode=new DefaultTableModel();
        mode.addColumn("DATES");    
        mode.addColumn("CODE");
        mode.addColumn("LB");
        mode.addColumn("LIBELLE                 ");
        mode.addColumn("DEBIT USD");
        mode.addColumn("CREDIT USD");
        mode.addColumn("DEBIT CDF");
        mode.addColumn("CREDIT CDF");   
        mode.addColumn("CODE 2");
      //  mode.addColumn("PROJET");
       
       
        
        
        
        jTable1.setModel(mode);

  }
      
      public void dates() {
    String ad= null;
    
               DefaultTableModel excels= (DefaultTableModel)jTable1.getModel(); 
       for(int i=0; i < excels.getRowCount();i++){
      
     //   Date DATES= new Date parse = new SimpleDateFormat("yyyy-MM-dd").parse(ad);
      //SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         
      ad = excels.getValueAt(i,0).toString();
                      // SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
                      
                      // String addDate = dateFormats.format(ad);
                      // Date Dates;
                      //SUBSTR(NUM,5,10)
                       
                  JOptionPane.showMessageDialog(null,ad.substring(5,7));
//        try {
//            Dates = dateFormatsS.parse(ad);
//             JOptionPane.showMessageDialog(null,Dates);
//        } catch (ParseException ex) {
//            Logger.getLogger(excel.class.getName()).log(Level.SEVERE, null, ex);
//        }
       //  String addDateS = dateFormatsS.format(ad);
     
      
       }
      
      
      }
     
       public void Save_CAISSE(){
       
   DefaultTableModel excels= (DefaultTableModel)jTable1.getModel(); 
   String DATES= null,CODE,LB,LIBELLE = null, DEBIT_USD = null,DEBIT_CDF= null ,CREDIT_USD= null ,CREDIT_CDF= null,CODE2= null,PROJET= null,COMPTEMERE = null,CODEMERE = null,OHADACODE = null,NAME = null,CLASS = null,SUBSTRS  = null,CCOMPTEMERE = null,CCODEMERE = null,COHADACODE,CNAME = null,CCLASS = null,CSUBSTRS  = null,CCODE = null;
   
    for(int i=0; i < excels.getRowCount();i++){
      DATES = excels.getValueAt(i,0).toString();
      CODE= excels.getValueAt(i,1). toString();
      LB = excels.getValueAt(i,2). toString();
      LIBELLE = excels.getValueAt(i,3). toString();
      DEBIT_USD = excels.getValueAt(i,4). toString();
      CREDIT_USD= excels.getValueAt(i,5). toString();
      DEBIT_CDF = excels.getValueAt(i,6). toString();
     CREDIT_CDF = excels.getValueAt(i,7). toString();
     CODE2 = excels.getValueAt(i,8). toString();
  
     
   
  
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
     
     
     
    if(CREDIT_USD.equals("0.0")){
        
 try{
             
            String sql="SELECT * FROM   OHADA where CODE='"+CODE+"'";
          
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                //COMPTEMERE,CODEMERE,OHADACODE,NAME,CLASS,SUBSTRS
               CCOMPTEMERE=rs.getString("COMPTEMERE");
               
                
                CCODEMERE=rs.getString("CODEMERE");
              
                
                CCODE=rs.getString("CODE");
              
                
                 CNAME=rs.getString("NAME");
                  CCLASS=rs.getString("CLASS");
                  CSUBSTRS =rs.getString("SUBSTR");
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
      if(CODE2.equals("")){
      try{
             
            String sql="SELECT * FROM   OHADA where CODE='"+CODE+"'";
          
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                //COMPTEMERE,CODEMERE,OHADACODE,NAME,CLASS,SUBSTRS
               CCOMPTEMERE=rs.getString("COMPTEMERE");
               
                
                CCODEMERE=rs.getString("CODEMERE");
              
                
                CCODE=rs.getString("CODE");
              
                
                 CNAME=rs.getString("NAME");
                  CCLASS=rs.getString("CLASS");
                  CSUBSTRS =rs.getString("SUBSTR");
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
        try {
        // String tmp =jComboBox3.getSelectedItem().toString();
        PreparedStatement pst = con.prepareStatement("INSERT INTO `ohada_trans`(`COMPTE_M`, `COMPTE`, `CODE_M`, `CODE`, `CLASSE`, `SUBSTR`, `DEBIT`, `CREDIT`, `NUM`, `DATES`, `BUSS`, `LIBELLE`, `JOURNAL`, `CODE1`,DEBIT_CDF,CREDIT_CDF)"
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        
      pst.setString(1, "");
        pst.setString(2, CNAME);
         pst.setString(3, "");
         pst.setString(4,CCODE);
         pst.setString(5,CCLASS);
         pst.setString(6, CSUBSTRS);
         pst.setString(8,"0.0");
         pst.setString(7,DEBIT_USD);
         pst.setString(9,rolls);
         
//       SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
//         String addDate = dateFormats.format(DATES);
         pst.setString(10, DATES);
         pst.setString(11,buss.getSelectedItem().toString());
         pst.setString(12,LIBELLE);
         pst.setString(13,"[JC]");
          pst.setString(14,"");
          pst.setString(15,DEBIT_CDF);
            pst.setString(16,"0.0");
       
          
         
          pst.executeUpdate();
        
               //  JOptionPane.showMessageDialog(null,"Caisse saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
        
        
     try {
   
        PreparedStatement pst = con.prepareStatement("INSERT INTO `caisses`(`CODE`,`CODEMERE`,`LIBELE`,`BUSS`,`DEBIT`,`CREDIT`,`DATE`,`COMPTE`,NUM,`MOIS`, `ANNEE`, `BUDGET`, `SOLDE`, `DEVICE`)"
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        
        pst.setString(1, "");
         pst.setString(2,CCODE);
         pst.setString(3, LIBELLE);
         pst.setString(4,buss.getSelectedItem().toString());
         pst.setString(5, DEBIT_USD);
         pst.setDouble(6,0.0);
         pst.setString(8,"");
         pst.setString(9,rolls);
         
          SimpleDateFormat dateFormatsS = new SimpleDateFormat("MMM");
         String addDateS = dateFormatsS.format(jDateChooser1.getDate());
         pst.setString(10, addDateS);
 
         pst.setString(11,"");
         pst.setString(12,"");
         pst.setString(13,"");
         pst.setString(14,device.getSelectedItem().toString());
       
       
         pst.setString(7, DATES);
         
          pst.executeUpdate();
        
                // JOptionPane.showMessageDialog(null,"Caisse saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
     
        
        
        
      }else{
        try{
             
            String sql="SELECT * FROM   OHADA where CODE='"+CODE2+"'";
          
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                //COMPTEMERE,CODEMERE,OHADACODE,NAME,CLASS,SUBSTRS
               CCOMPTEMERE=rs.getString("COMPTEMERE");
               
                
                CCODEMERE=rs.getString("CODEMERE");
              
                
                CCODE=rs.getString("CODE");
              
                
                 CNAME=rs.getString("NAME");
                  CCLASS=rs.getString("CLASS");
                  CSUBSTRS =rs.getString("SUBSTR");
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
//    
//           //========>>>>CREDIT
//        ///INSERT INTO `ohada_trans`(`ID`, `COMPTE_M`, `COMPTE`, `CODE_M`, `CODE`, `CLASSE`, `SUBSTR`, `DEBIT`, `CREDIT`, `NUM`, `DATES`, `BUSS`, `LIBELLE`, `JOURNAL`)
         try {
        // String tmp =jComboBox3.getSelectedItem().toString();
        PreparedStatement pst = con.prepareStatement("INSERT INTO `ohada_trans`(`COMPTE_M`, `COMPTE`, `CODE_M`, `CODE`, `CLASSE`, `SUBSTR`, `DEBIT`, `CREDIT`, `NUM`, `DATES`, `BUSS`, `LIBELLE`, `JOURNAL`, `CODE1`,DEBIT_CDF,CREDIT_CDF)"
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        
      pst.setString(1, CCOMPTEMERE);
        pst.setString(2, CNAME);
         pst.setString(3, CCODEMERE);
         pst.setString(4,CCODE);
         pst.setString(5,CCLASS);
         pst.setString(6, CSUBSTRS);
         pst.setString(8,"0.0");
         pst.setString(7,DEBIT_USD);
         pst.setString(9,rolls);
         
//       SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
//         String addDate = dateFormats.format(DATES);
         pst.setString(10, DATES);
         pst.setString(11,buss.getSelectedItem().toString());
         pst.setString(12,LIBELLE);
         pst.setString(13,"[JC]");
          pst.setString(14,CODE);
          pst.setString(15,DEBIT_CDF);
            pst.setString(16,"0.0");
       
          
         
          pst.executeUpdate();
        
               //  JOptionPane.showMessageDialog(null,"Caisse saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
   //  }else{
        try{
             
            String sql="SELECT * FROM   OHADA where CODE='"+CODE2+"'";
          
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                //COMPTEMERE,CODEMERE,OHADACODE,NAME,CLASS,SUBSTRS
               CCOMPTEMERE=rs.getString("COMPTEMERE");
               
                
                CCODEMERE=rs.getString("CODEMERE");
              
                
                CCODE=rs.getString("CODE");
              
                
                 CNAME=rs.getString("NAME");
                  CCLASS=rs.getString("CLASS");
                  CSUBSTRS =rs.getString("SUBSTR");
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        } 
       try {
        // String tmp =jComboBox3.getSelectedItem().toString();
        PreparedStatement pst = con.prepareStatement("INSERT INTO `caisses`(`CODE`,`CODEMERE`,`LIBELE`,`BUSS`,`DEBIT`,`CREDIT`,`DATE`,`COMPTE`,NUM,`MOIS`, `ANNEE`, `BUDGET`, `SOLDE`,DEVICE)"
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        
        pst.setString(1, CODE);
         pst.setString(2, CCODE);
         pst.setString(3, LIBELLE);
         pst.setString(4, buss.getSelectedItem().toString());
         pst.setString(6, "0");
         pst.setString(5,DEBIT_USD);
         pst.setString(8,CNAME);
         pst.setString(9,rolls);
         
          SimpleDateFormat dateFormatsS = new SimpleDateFormat("MMM");
         String addDateS = dateFormatsS.format(jDateChooser1.getDate());
         pst.setString(10, addDateS);
 
         pst.setString(11,"");
         pst.setString(12,"");
         pst.setString(13,"");
         pst.setString(14,device.getSelectedItem().toString());
       
         
         pst.setString(7, DATES);
         
          pst.executeUpdate();
        
            //     JOptionPane.showMessageDialog(null,"Caisse saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
          
        
        
      }
      
      
       //Consider as CREDIT
      }else{
         try{
             
            String sql="SELECT * FROM   OHADA where CODE='"+CODE+"'";
          
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
        PreparedStatement pst = con.prepareStatement("INSERT INTO `ohada_trans`(`COMPTE_M`, `COMPTE`, `CODE_M`, `CODE`, `CLASSE`, `SUBSTR`, `DEBIT`, `CREDIT`, `NUM`, `DATES`, `BUSS`, `LIBELLE`, `JOURNAL`, `CODE1`,DEBIT_CDF,CREDIT_CDF)"
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        
      pst.setString(1, COMPTEMERE);
        pst.setString(2, NAME);
         pst.setString(3, CODEMERE);
         pst.setString(4,CODE);
         pst.setString(5,CLASS);
         pst.setString(6, SUBSTRS);
         pst.setString(7,"0.0");
         pst.setString(8,CREDIT_USD);
         pst.setString(9,rolls);
         
//       SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
//         String addDate = dateFormats.format(DATES);
         pst.setString(10, DATES);
         pst.setString(11,buss.getSelectedItem().toString());
         pst.setString(12,LIBELLE);
         pst.setString(13,"[JC]");
          pst.setString(14,CODE2);
          pst.setString(16,DEBIT_CDF);
            pst.setString(15,"0.0");
       
          
         
          pst.executeUpdate();
        
               //  JOptionPane.showMessageDialog(null,"Caisse saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
             
         try{
             
            String sql="SELECT * FROM   OHADA where CODE='"+CODE2+"'";
          
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                //COMPTEMERE,CODEMERE,OHADACODE,NAME,CLASS,SUBSTRS
               CCOMPTEMERE=rs.getString("COMPTEMERE");
               
                
                CCODEMERE=rs.getString("CODEMERE");
              
                
                CCODE=rs.getString("CODE");
              
                
                 CNAME=rs.getString("NAME");
                  CCLASS=rs.getString("CLASS");
                  CSUBSTRS =rs.getString("SUBSTR");
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
        try {
        PreparedStatement pst = con.prepareStatement("INSERT INTO `ohada_trans`(`COMPTE_M`, `COMPTE`, `CODE_M`, `CODE`, `CLASSE`, `SUBSTR`, `DEBIT`, `CREDIT`, `NUM`, `DATES`, `BUSS`, `LIBELLE`, `JOURNAL`, `CODE1`,DEBIT_CDF,CREDIT_CDF)"
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        
      pst.setString(1, CCOMPTEMERE);
        pst.setString(2, CNAME);
         pst.setString(3, CCODEMERE);
         pst.setString(4,CCODE);
         pst.setString(5,CCLASS);
         pst.setString(6, CSUBSTRS);
         pst.setString(7,"0.0");
         pst.setString(8,CREDIT_USD);
         pst.setString(9,rolls);
         
//       SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
//         String addDate = dateFormats.format(DATES);
         pst.setString(10, DATES);
         pst.setString(11,buss.getSelectedItem().toString());
         pst.setString(12,LIBELLE);
         pst.setString(13,"[JC]");
          pst.setString(14,CODE2);
          pst.setString(16,DEBIT_CDF);
            pst.setString(15,"0.0");
       
          
         
          pst.executeUpdate();
        
               //  JOptionPane.showMessageDialog(null,"Caisse saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
       try {
        // String tmp =jComboBox3.getSelectedItem().toString();
        PreparedStatement pst = con.prepareStatement("INSERT INTO `caisses`(`CODE`,`CODEMERE`,`LIBELE`,`BUSS`,`DEBIT`,`CREDIT`,`DATE`,`COMPTE`,NUM,`MOIS`, `ANNEE`, `BUDGET`, `SOLDE`,DEVICE)"
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        
        pst.setString(1, CODE2);
         pst.setString(2, CODE);
         pst.setString(3, LIBELLE);
         pst.setString(4, buss.getSelectedItem().toString());
         pst.setString(5, "0");
         pst.setString(6,CREDIT_USD);
         pst.setString(8,NAME);
         pst.setString(9,rolls);
         
          SimpleDateFormat dateFormatsS = new SimpleDateFormat("MMM");
         String addDateS = dateFormatsS.format(jDateChooser1.getDate());
         pst.setString(10, addDateS);
 
         pst.setString(11,"");
         pst.setString(12,LB);
         pst.setString(13,"");
         pst.setString(14,device.getSelectedItem().toString());
       
         
         pst.setString(7, DATES);
         
          pst.executeUpdate();
        
            //     JOptionPane.showMessageDialog(null,"Caisse saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
         String ITEM = null,CAT = null,SUB_CAT = null,CODE_CAT = null,CODE_SUB_CAT,ITEMS,LBSUB = null;  
          try{
          String sql="SELECT ITEM,LBSUB,CAT,SUB_CAT,LB FROM  budget WHERE code='"+LB+"' and PROJECT='"+buss.getSelectedItem()+"'";
          
            pst=con.prepareStatement(sql);
            //pst.setString(1, tmp);
            rs=pst.executeQuery();
            if(rs.next()){
                 
              ITEM = rs.getString("ITEM");     
             LBSUB = rs.getString("LBSUB");
             CAT = rs.getString("CAT");
             SUB_CAT = rs.getString("SUB_CAT");
             CODE_CAT= rs.getString("LB");
              
          }
  
      }catch(Exception ex){
        JOptionPane.showMessageDialog(null,ex);  
      }   
      if(LB.equals("")){
     
      
      }else{
      
      try{
    String sql="INSERT INTO `budget_trans`(`ITEM`, `DEBIT`, `CREDIT`, `SOLD`, `PROJET`, `CODE`, `NUM`, `CAT`, `DATES`, `MOIS`, `ANNEE`,SUB_CAT,CODE_CAT,CODE_SUB_CAT,`ITEMS`) "+"values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    pst=con.prepareStatement(sql);
    pst.setString(1,LIBELLE);
    pst.setString(2,"0");
    pst.setString(3,CREDIT_USD);
    pst.setString(4,"0.0");
    pst.setString(5,buss.getSelectedItem().toString());
    pst.setString(6,LB);
    pst.setString(7,rolls);
    
    pst.setString(8,CAT);
   
    pst.setString(9,DATES);
    SimpleDateFormat dateFormatsS = new SimpleDateFormat("MMM");
         String addDateS = dateFormatsS.format(jDateChooser1.getDate());
         pst.setString(10, addDateS);
 
         pst.setString(11,"");
    pst.setString (12,SUB_CAT);
    
     pst.setString(13,CODE_CAT);
      pst.setString(14,LBSUB);
      
      pst.setString(15,ITEM);
   
    
    
    pst.executeUpdate();
    // JOptionPane.showMessageDialog(null,"BUDGET TRANS");
    }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}  
      }
       
        
        
        
   }// END OF BIG ELSE
     
      
     
   
  
    } //END FOR
     JOptionPane.showMessageDialog(null,"Tranction Saved");
    }
       
       
       
       public void save_caisse(){
//       try {
//     Double xxx,caissesdebit = null,yyy,USD = null;
//       try{  
//            String sql="select SUM(DEBIT),SUM(CREDIT) from caisses where buss='"+journal_caisse.buss.getSelectedItem()+"'";
//           
//            pst=con.prepareStatement(sql);
//            rs=pst.executeQuery();
//            while(rs.next()){
//                xxx=rs.getDouble("SUM(DEBIT)");
//               // entre.setText(""+xxx);
//                yyy=rs.getDouble("SUM(CREDIT)");
//                  
//                   caissesdebit=(xxx-yyy)-USD;
//                //   sorti.setText(""+yyy);
//            }
//            }catch(Exception ex){ JOptionPane.showMessageDialog(null, ex);} 
//        PreparedStatement pst = con.prepareStatement("INSERT INTO `caisses`(`CODE`,`CODEMERE`,`LIBELE`,`BUSS`,`DEBIT`,`CREDIT`,`DATE`,`COMPTE`,NUM,`MOIS`, `ANNEE`, `BUDGET`, `SOLDE`)"
//        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?)");
//        
//        pst.setString(1, "");
//         pst.setString(2, journal_caisse.jComboBox1.getSelectedItem().toString());
//         pst.setString(3, jTextField1.getText());
//         pst.setString(4, journal_caisse.buss.getSelectedItem().toString());
//         pst.setString(5, amount.getText());
//         pst.setDouble(6,0.0);
//         pst.setString(8,"");
//         pst.setString(9,rolls);
//         
//          SimpleDateFormat dateFormatsS = new SimpleDateFormat("MMM");
//         String addDateS = dateFormatsS.format(jDateChooser1.getDate());
//         pst.setString(10, addDateS);
// 
//         pst.setInt(11,journal_caisse.jYearChooser1.getValue());
//         pst.setString(12,"");
//         pst.setDouble(13,caissesdebit);
//       
//          SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
//         String addDate = dateFormats.format(jDateChooser1.getDate());
//         pst.setString(7, addDate);
//         
//          pst.executeUpdate();
//        
//                 JOptionPane.showMessageDialog(null,"Caisse saved");
//    } catch (Exception ex) {
//         JOptionPane.showMessageDialog(null, ex.getMessage());
//    }
// 
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
        jScrollPane3 = new javax.swing.JScrollPane();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        buss = new javax.swing.JComboBox<>();
        jDateChooser1 = new com.alee.extended.date.WebDateField();
        device = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jTable1.setBackground(new java.awt.Color(240, 240, 240));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTable1MousePressed(evt);
            }
        });
        jScrollPane3.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 1270, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 507, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_Microsoft_Excel_50px.png"))); // NOI18N
        jLabel1.setText("Import Excel");
        jLabel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        jComboBox1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select One Item", "Code Budget", "Budget", "Etat de Besoins", "Journal Caisse", "Journal Banque" }));
        jComboBox1.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                jComboBox1PopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton1.setText("Save Table");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1))
                    .addComponent(jLabel1))
                .addContainerGap())
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        buss.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        buss.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select One Project" }));

        device.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        device.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "USD", "CDF" }));

        jButton2.setText("jButton2");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(buss, 0, 355, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(device, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(buss, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(device, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
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

    private void jTable1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MousePressed
        //doubleclick();        // TODO add your handling code here:
    }//GEN-LAST:event_jTable1MousePressed

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
imports();        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jComboBox1PopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_jComboBox1PopupMenuWillBecomeInvisible
if(jComboBox1.getSelectedItem().equals("Budget")){
 Budget_Model();
}else if (jComboBox1.getSelectedItem().equals("Etat de Besoins")){
EB_Model();
}else if(jComboBox1.getSelectedItem().equals("Journal Caisse")){
CAISSE_Model();
}else{

}
             // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1PopupMenuWillBecomeInvisible

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
if(jComboBox1.getSelectedItem().equals("Budget")){
SaveBudget();
}else if (jComboBox1.getSelectedItem().equals("Etat de Besoins")){
Save_EB();
}else if(jComboBox1.getSelectedItem().equals("Journal Caisse")){
   
        //Save_CAISSE();
        dates();
   
}else{

}
        
            // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
dates();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(excel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(excel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(excel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(excel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                 WebLookAndFeel.install(true);
                new excel().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> buss;
    private javax.swing.JComboBox<String> device;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboBox1;
    private com.alee.extended.date.WebDateField jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    public static final javax.swing.JTable jTable1 = new javax.swing.JTable();
    // End of variables declaration//GEN-END:variables
}
