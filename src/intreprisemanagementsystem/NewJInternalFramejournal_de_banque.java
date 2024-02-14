/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intreprisemanagementsystem;

import java.awt.Color;
import java.awt.Component;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.sql.Connection;

import java.sql.SQLException;

import java.text.DecimalFormat;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JScrollPane;


import javax.swing.JTable;

import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import net.proteanit.sql.DbUtils;
//import net.proteanit.sql.DbUtils;
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
public class NewJInternalFramejournal_de_banque extends javax.swing.JInternalFrame {
    DefaultTableModel mode;
Connection con;
PreparedStatement pst=null;
ResultSet rs= null;
String etrolls,rolls;

 
 private JTable table;
    public NewJInternalFramejournal_de_banque() {
        initComponents();
    
              con=JavaDbConnect.dbConnect();
       /*  this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI bui= (BasicInternalFrameUI) this.getUI();
       bui.setNorthPane(null);*/
        callbank();
        //calltabe();
      //table();
        // search2ALL();
     //callcaiseAll();
      
        
    }

    
    public void refresh(){
    
     
        buss.setSelectedItem("SELECT ONE BANK");
       // jComboBox1.setSelectedItem("SELECT ONE PROJECT");
        //jComboBox1.select("dddddd");
    }
    
     public void callcaisebuss()
    {
       
        
         Double Debit;
         Double Credit;
         Double c;
         String e;
        try{
            String sql="SELECT sum(debit),sum(credit) FROM   ohada_trans where compte='"+buss.getSelectedItem()+"'and SOLDE='No'";
          
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
               Debit=rs.getDouble("sum(debit)");
               Credit=rs.getDouble("sum(credit)");
               c=Debit-Credit;
                NumberFormat nf = new DecimalFormat("#,###,###");
String fn = nf.format(c);
String fn1 = nf.format(Debit);
String fn2 = nf.format(Credit);
           solde_in.setText(fn1);
           solde_out.setText(fn2);
           solde_available.setText(fn);
           
               
               
               
            }
         //   con.close();
            }
        
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
    
    }
     
      public void callcaisebuss_buss()
    {
       
        
         Double Debit;
         Double Credit;
         Double c;
         String e;
        try{
            String sql="SELECT sum(debit),sum(credit) FROM   ohada_trans where compte='"+buss.getSelectedItem()+"' and BUSS='"+BUSS.getSelectedItem()+"' and SOLDE='No'";
          
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
               Debit=rs.getDouble("sum(debit)");
               Credit=rs.getDouble("sum(credit)");
               c=Debit-Credit;
                NumberFormat nf = new DecimalFormat("#,###,###");
String fn = nf.format(c);
String fn1 = nf.format(Debit);
String fn2 = nf.format(Credit);
           Solde_in.setText(fn1);
           Solde_out.setText(fn2);
           Solde_available.setText(fn);
           
               
               
               
            }
         //   con.close();
            }
        
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
    
    }
       public void callcaisebuss_bussdate()
    {
   SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String date1 = dateFormats.format(webDateField1.getDate()); 
         String date2 = dateFormats.format(webDateField2.getDate());
        
         Double Debit;
         Double Credit;
         Double c;
         String e;
        try{
            String sql="SELECT sum(debit),sum(credit) FROM   ohada_trans where compte='"+buss.getSelectedItem()+"' and BUSS='"+BUSS.getSelectedItem()+"' AND dates between '"+date1+"' and '"+date2+"' and SOLDE='No'";
          
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
               Debit=rs.getDouble("sum(debit)");
               Credit=rs.getDouble("sum(credit)");
               c=Debit-Credit;
                NumberFormat nf = new DecimalFormat("#,###,###");
String fn = nf.format(c);
String fn1 = nf.format(Debit);
String fn2 = nf.format(Credit);
           Solde_in.setText(fn1);
           Solde_out.setText(fn2);
           Solde_available.setText(fn);
           
               
               
               
            }
         //   con.close();
            }
        
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
    
    }
    
      public void callcaiseDate()
    {
       
        
         Double Debit;
         Double Credit;
         Double c;
         String e;
        try{
            String sql="SELECT sum(debit),sum(credit) FROM   ohada_trans where compte='"+buss.getSelectedItem()+"' and DATES BETWEEN '"+webDateField1.getText()+"' and '"+webDateField2.getText()+"'";
          
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
               Debit=rs.getDouble("sum(debit)");
               Credit=rs.getDouble("sum(credit)");
               c=Debit-Credit;
                NumberFormat nf = new DecimalFormat("#,###,###");
String fn = nf.format(c);
String fn1 = nf.format(Debit);
String fn2 = nf.format(Credit);
           solde_in.setText(fn1);
           solde_out.setText(fn2);
           solde_available.setText(fn);
           
               
               
               
            }
         //   con.close();
            }
        
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
    
    }
    
       public void callcaiseAll()
    {
       
        
         Double Debit;
         Double Credit;
         Double c;
         String e;
        try{
            String sql="SELECT sum(debit),sum(credit) FROM   ohada_trans where CODE_M='52'";
          
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
               Debit=rs.getDouble("sum(debit)");
               Credit=rs.getDouble("sum(credit)");
               c=Debit-Credit;
                NumberFormat nf = new DecimalFormat("#,###,###");
String fn = nf.format(c);
String fn1 = nf.format(Debit);
String fn2 = nf.format(Credit);
           solde_in.setText(fn1);
           solde_out.setText(fn2);
           solde_available.setText(fn);
           
               
               
               
            }
         //   con.close();
            }
        
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
    
    }
    
     
     
     public void select1(){
        String sql="select sum(MOTANT_IN),sum(MOTANT_OUT) from journal_de_bank where BANQUE='"+buss.getSelectedItem()+"'";
         Double sum=null;
         Double sum1=null;
         try{
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                sum=rs.getDouble("sum(MOTANT_IN)");
                 sum1=rs.getDouble("sum(MOTANT_OUT)");
               
            }}catch(Exception ex){JOptionPane.showMessageDialog(null, ex); }
    if( sum == null){ JOptionPane.showMessageDialog(null, "NO ENOUGH MONEY"); 
    }else if(sum1 == null){JOptionPane.showMessageDialog(null, "NO ENOUGH MONEY");}else{
    try{
           // String sql="select sum(MOTANT_IN),sum(MOTANT_OUT) from journal_de_bank where BANQUE='"+jComboBox2.getSelectedItem()+"'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            if(rs.next()){
            
             Double c;
        
         
      
             c=sum-sum1;
            
            
             DecimalFormat x = new DecimalFormat("$ #,##0.00");
             solde_in.setText(x.format(sum));
             solde_out.setText(x.format(sum1));
                  
                   solde_available.setText(x.format(c));
              
                 
            }
            }
    
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
     }}
    public void callbank(){
        buss.removeAllItems();
    buss.setSelectedItem("SELECT ONE BANK");
    try{
            String sql="select * from bank";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                String sum=rs.getString("REF");
                  buss.addItem(sum);
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
                BUSS.addItem(sum);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex); } 
    
    }
    
    
    
     
      
            
     
             
             public void report(){
                 String tmp=buss.getSelectedItem().toString();
                  SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String date1 = dateFormats.format(webDateField1.getDate()); 
         String date2 = dateFormats.format(webDateField2.getDate());
             try{
             
          String sqls="Select path from pathn";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
            if(rs.next()){
             
                String sum=rs.getString("path");
                  
                 String NameFiles=sum;
            
            String NameFile=""+NameFiles+"banque.jrxml";//journal_de_banque
           // String NameFile=""+NameFiles+"journal_de_banque.jrxml";//journal_de_banque
                 
                 
                 
                JasperDesign jd=JRXmlLoader.load(NameFile);
        
        
              // JasperDesign jd=JRXmlLoader.load("C:\\Users\\Dosh\\Documents\\NetBeansProjects\\intreprisemanagementsystem\\src\\intreprisemanagementsystem\\journaldecaise.jrxml");
              String sql="select * from  ohada_trans INNER JOIN projet ON ohada_trans.BUSS=projet.PROJET_NUM where COMPTE='"+tmp+"' AND DATES BETWEEN '"+date1+"' AND '"+date2+"' order by DATEs,ohada_trans.NUM ";
               HashMap param= new HashMap();
    param.put("date1", webDateField1.getText());
     param.put("date2", webDateField2.getText());
    
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
             
              public void report_buss(){
                 String tmp=buss.getSelectedItem().toString();
                 String tmps=BUSS.getSelectedItem().toString();
                    SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String date1 = dateFormats.format(webDateField1.getDate()); 
         String date2 = dateFormats.format(webDateField2.getDate());
             try{
             
          String sqls="Select path from pathn";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
            if(rs.next()){
             
                String sum=rs.getString("path");
                  
                 String NameFiles=sum;
            
            String NameFile=""+NameFiles+"banque.jrxml";//journal_de_banque
           // String NameFile=""+NameFiles+"journal_de_banque.jrxml";//journal_de_banque
                 
                 
                 
                JasperDesign jd=JRXmlLoader.load(NameFile);
        
        
              // JasperDesign jd=JRXmlLoader.load("C:\\Users\\Dosh\\Documents\\NetBeansProjects\\intreprisemanagementsystem\\src\\intreprisemanagementsystem\\journaldecaise.jrxml");
              String sql="select * from  ohada_trans INNER JOIN projet ON ohada_trans.BUSS=projet.PROJET_NUM  where COMPTE='"+tmp+"' AND DATES BETWEEN '"+date1+"' AND '"+date2+"' and buss='"+tmps+"' order by DATEs,ohada_trans.NUM ";
               HashMap param= new HashMap();
    param.put("date1", webDateField1.getText());
     param.put("date2", webDateField2.getText());
    
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
             
              public void reports(){
                 String tmp=buss.getSelectedItem().toString();
             try{
             
          String sqls="Select path from pathn";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
            if(rs.next()){
             
                String sum=rs.getString("path");
                  
                 String NameFiles=sum;
            
            String NameFile=""+NameFiles+"journal_de_banque.jrxml";//journal_de_banque
           // String NameFile=""+NameFiles+"journal_de_banque.jrxml";//journal_de_banque
                 
                 
                 
                JasperDesign jd=JRXmlLoader.load(NameFile);
        
        
              // JasperDesign jd=JRXmlLoader.load("C:\\Users\\Dosh\\Documents\\NetBeansProjects\\intreprisemanagementsystem\\src\\intreprisemanagementsystem\\journaldecaise.jrxml");
              String sql="SELECT * FROM  journal_de_bank";
              
    
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
              
              
             
              
              
               public void table(){
      try{
           
       String sql="SELECT `DATES`,`CODE`,`MOTIF`,`MOTANT_IN` AS 'DEBIT',`MOTANT_OUT` AS 'CREDIT',`PROJET`,`NUM` FROM `journal_de_bank`";
     //  String sql="SELECT ID, `NUM_FACTURE`, `DATE_FACTURE`, `DESCRIPTION`, `DEBIT_QTY`, `DEBIT_PU`, `DEBIT_PT`, `DEBIT_TVA`, `CREDIT_QTY`, `CREDIT_PU`, `CREDIT_PT`, `CREDIT_TVA`, `DATES`, `NUM` FROM facture_client where compte='"+jComboBox6.getSelectedItem()+"'";
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
        
       
      
       
       col0.setPreferredWidth(30);
       col1.setPreferredWidth(30);
       col2.setPreferredWidth(250);
       col3.setPreferredWidth(50);
       col4.setPreferredWidth(50);
       col5.setPreferredWidth(180);
        col6.setPreferredWidth(30);
       
     
    }catch(SQLException ex ){
     JOptionPane.showMessageDialog(null, ex);
}

               }
public void colorr(){
   
      Object[] columnNames = { "Type", "Company", "Name", "Salery", "Designation", "Desidgnation" , "Designadtion"  };
        Object[][] data = null; 
         
     try{
           
       String sql="SELECT `DATES`,`CODE`,`MOTIF`,`MOTANT_IN` AS 'DEBIT',`MOTANT_OUT` AS 'CREDIT',`PROJET`,`NUM` FROM `journal_de_bank`";
     //  String sql="SELECT ID, `NUM_FACTURE`, `DATE_FACTURE`, `DESCRIPTION`, `DEBIT_QTY`, `DEBIT_PU`, `DEBIT_PT`, `DEBIT_TVA`, `CREDIT_QTY`, `CREDIT_PU`, `CREDIT_PT`, `CREDIT_TVA`, `DATES`, `NUM` FROM facture_client where compte='"+jComboBox6.getSelectedItem()+"'";
       pst = con.prepareStatement(sql);
       sql=data.toString();
      rs= pst.executeQuery();
      
       
       jTable1.setModel(DbUtils.resultSetToTableModel(rs));
       mode=new DefaultTableModel();
       
       
       /* TableColumn col0=table.getColumnModel().getColumn(0);
        TableColumn col1=table.getColumnModel().getColumn(1);
        TableColumn col2=table.getColumnModel().getColumn(2);
        TableColumn col3=table.getColumnModel().getColumn(3);
        TableColumn col4=table.getColumnModel().getColumn(4);
        TableColumn col5=table.getColumnModel().getColumn(5);
         TableColumn col6=table.getColumnModel().getColumn(6);
        
       
      
       
       col0.setPreferredWidth(30);
       col1.setPreferredWidth(30);
       col2.setPreferredWidth(250);
       col3.setPreferredWidth(50);
       col4.setPreferredWidth(50);
       col5.setPreferredWidth(180);
        col6.setPreferredWidth(30);
       */
     
    }catch(SQLException ex ){
     JOptionPane.showMessageDialog(null, ex);
    
        
        DefaultTableModel model = new DefaultTableModel(data, columnNames) {


            @Override
            public Class getColumnClass(int column) {
                return getValueAt(0, column).getClass();
            }
        };
  table = new JTable(model) {
    
 //    @Override
                public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                    Component c = super.prepareRenderer(renderer, row, column);

                      if (!isRowSelected(row)) {
                        if (jTable1.getColumnCount() >= 0) {
                            String type = (String)getModel().getValueAt(row, 0);
                             String types = (String)getModel().getValueAt(row, 3);
                            
                            if (types.equalsIgnoreCase("0")) {
                                c.setBackground(Color.BLUE);

                            }
                            if (type.equalsIgnoreCase("2019-05-30")) {
                                c.setBackground(new Color(14, 255, 190));

                            }
                            if (type.equalsIgnoreCase("Contract")) {
                                c.setBackground(Color.green);

                            }

                        }
                    }
                    if (isRowSelected(row) && isColumnSelected(column)) {
                        ((JComponent)c).setBorder(new LineBorder(Color.red));
                    }
                    return c;
                }
  };

        jTable1.setPreferredScrollableViewportSize(jTable1.getPreferredSize());
        JScrollPane scrollPane = new JScrollPane(jTable1);
        getContentPane().add(scrollPane);

    }
 
}
               
                public void tables(){
                  
      try{
           
       String sql="SELECT `DATES`,`CODE`,`MOTIF`,`MOTANT_IN` as 'DEBIT',`MOTANT_OUT` AS 'CREDIT',`PROJET`,`NUM` FROM `journal_de_bank` where `BANQUE`='"+buss.getSelectedItem()+"'";
     //  String sql="SELECT ID, `NUM_FACTURE`, `DATE_FACTURE`, `DESCRIPTION`, `DEBIT_QTY`, `DEBIT_PU`, `DEBIT_PT`, `DEBIT_TVA`, `CREDIT_QTY`, `CREDIT_PU`, `CREDIT_PT`, `CREDIT_TVA`, `DATES`, `NUM` FROM facture_client where compte='"+jComboBox6.getSelectedItem()+"'";
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
        
       
      
       
       col0.setPreferredWidth(30);
       col1.setPreferredWidth(30);
       col2.setPreferredWidth(250);
       col3.setPreferredWidth(50);
       col4.setPreferredWidth(50);
       col5.setPreferredWidth(180);
        col6.setPreferredWidth(30);
       
  
      
       
       
      // jTable1.setModel(mode);
       
     
    }catch(SQLException ex ){
     JOptionPane.showMessageDialog(null, ex);
}
               }
                
                
                 public void tabless(){
      try{
           
       String sql="SELECT `DATES`,`CODE`,`MOTIF`,`MOTANT_IN` as 'DEBIT',`MOTANT_OUT` AS 'CREDIT',`PROJET`,`NUM` FROM `journal_de_bank` where `BANQUE`='"+buss.getSelectedItem()+"' and DATES BETWEEN '"+webDateField1.getText()+"' AND '"+webDateField2.getText()+"'";
     //  String sql="SELECT ID, `NUM_FACTURE`, `DATE_FACTURE`, `DESCRIPTION`, `DEBIT_QTY`, `DEBIT_PU`, `DEBIT_PT`, `DEBIT_TVA`, `CREDIT_QTY`, `CREDIT_PU`, `CREDIT_PT`, `CREDIT_TVA`, `DATES`, `NUM` FROM facture_client where compte='"+jComboBox6.getSelectedItem()+"'";
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
        
       
      
       
       col0.setPreferredWidth(30);
       col1.setPreferredWidth(30);
       col2.setPreferredWidth(250);
       col3.setPreferredWidth(50);
       col4.setPreferredWidth(50);
       col5.setPreferredWidth(180);
        col6.setPreferredWidth(30);
       
  
      
       
       
      // jTable1.setModel(mode);
       
     
    }catch(SQLException ex ){
     JOptionPane.showMessageDialog(null, ex);
}
               }
                 
                 
                  public void search2BUS()
             {
             //COMPTE_M`, `COMPTE`, `CODE_M`, `CODE`, `CLASSE`, `SUBSTR`, `DEBIT`, `CREDIT`, `NUM`, `DATES`, `BUSS`, `LIBELLE`, `JOURNAL`, `CODE1` 
                 try{
               //   String st=projetid.getText().trim();
             String tmp=buss.getSelectedItem().toString();
    String sql="select `NUM`, `CODE1` AS 'CODE',`LIBELLE`,FORMAT(CREDIT,2) AS DEBIT,FORMAT(DEBIT,2) AS CREDIT, `DATES` from ohada_trans where COMPTE = '"+tmp+"' order by DATES";
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
        jTable1.getColumnModel().getColumn(1).setCellRenderer(centre);
        TableColumn col2=jTable1.getColumnModel().getColumn(2);
        TableColumn col3=jTable1.getColumnModel().getColumn(3);
            jTable1.getColumnModel().getColumn(3).setCellRenderer(centre);
        TableColumn col4=jTable1.getColumnModel().getColumn(4);
        jTable1.getColumnModel().getColumn(4).setCellRenderer(centre);
           TableColumn col5=jTable1.getColumnModel().getColumn(5);
           // TableColumn col6=jTable1.getColumnModel().getColumn(6);
      
       
       col0.setPreferredWidth(50);
       col1.setPreferredWidth(20);
       col2.setPreferredWidth(370);
       col3.setPreferredWidth(20);
       col4.setPreferredWidth(20);
       col5.setPreferredWidth(20);
    //   col6.setPreferredWidth(200);
             
             }
                  
                   public void search2BUSS()
             {
             //COMPTE_M`, `COMPTE`, `CODE_M`, `CODE`, `CLASSE`, `SUBSTR`, `DEBIT`, `CREDIT`, `NUM`, `DATES`, `BUSS`, `LIBELLE`, `JOURNAL`, `CODE1` 
                 try{
               //   String st=projetid.getText().trim();
             String tmp=buss.getSelectedItem().toString();
    String sql="select `NUM`, `CODE1` AS 'CODE',`LIBELLE`,FORMAT(CREDIT,2) AS DEBIT,FORMAT(DEBIT,2) AS CREDIT, `DATES` from ohada_trans where COMPTE = '"+tmp+"' AND BUSS='"+BUSS.getSelectedItem()+"' order by DATES,num";
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
        jTable1.getColumnModel().getColumn(1).setCellRenderer(centre);
        TableColumn col2=jTable1.getColumnModel().getColumn(2);
        TableColumn col3=jTable1.getColumnModel().getColumn(3);
            jTable1.getColumnModel().getColumn(3).setCellRenderer(centre);
        TableColumn col4=jTable1.getColumnModel().getColumn(4);
        jTable1.getColumnModel().getColumn(4).setCellRenderer(centre);
           TableColumn col5=jTable1.getColumnModel().getColumn(5);
           // TableColumn col6=jTable1.getColumnModel().getColumn(6);
      
       
       col0.setPreferredWidth(50);
       col1.setPreferredWidth(20);
       col2.setPreferredWidth(370);
       col3.setPreferredWidth(20);
       col4.setPreferredWidth(20);
       col5.setPreferredWidth(20);
    //   col6.setPreferredWidth(200);
             
             }
                  
          public void search2DATE(){
            SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String date1 = dateFormats.format(webDateField1.getDate());
         String date2 = dateFormats.format(webDateField2.getDate());
                     
             //COMPTE_M`, `COMPTE`, `CODE_M`, `CODE`, `CLASSE`, `SUBSTR`, `DEBIT`, `CREDIT`, `NUM`, `DATES`, `BUSS`, `LIBELLE`, `JOURNAL`, `CODE1` 
                 try{
               //   String st=projetid.getText().trim();
             String tmp=buss.getSelectedItem().toString();
    String sql="select `NUM`, `CODE1` AS 'CODE',`LIBELLE`,FORMAT(CREDIT,2) AS DEBIT,FORMAT(DEBIT,2) AS CREDIT, `DATES`  from ohada_trans where COMPTE = '"+tmp+"' AND DATES BETWEEN '"+date1+"' AND '"+date2+"' order by DATES,NUM";
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
           // TableColumn col6=jTable1.getColumnModel().getColumn(6);
      
       
       col0.setPreferredWidth(20);
       col1.setPreferredWidth(20);
       col2.setPreferredWidth(370);
       col3.setPreferredWidth(20);
       col4.setPreferredWidth(20);
       col5.setPreferredWidth(50);
    //   col6.setPreferredWidth(200);
             
             }
          public void search2ALL()
             {
             //COMPTE_M`, `COMPTE`, `CODE_M`, `CODE`, `CLASSE`, `SUBSTR`, `DEBIT`, `CREDIT`, `NUM`, `DATES`, `BUSS`, `LIBELLE`, `JOURNAL`, `CODE1` 
                 try{
               //   String st=projetid.getText().trim();
             String tmp=buss.getSelectedItem().toString();
    String sql="select `NUM`, `CODE1` AS 'CODE',`LIBELLE`,FORMAT(CREDIT,2) AS DEBIT,FORMAT(DEBIT,2) AS CREDIT, `DATES`  from ohada_trans where CODE_M = '52' order by NUM";
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
           // TableColumn col6=jTable1.getColumnModel().getColumn(6);
      
       
       col0.setPreferredWidth(20);
       col1.setPreferredWidth(20);
       col2.setPreferredWidth(370);
       col3.setPreferredWidth(20);
       col4.setPreferredWidth(20);
       col5.setPreferredWidth(50);
    //   col6.setPreferredWidth(200);
             
             }
      
       String NAME,CODE,COMPTEMERE,CODEMERE,CLASS,SUBSTRS ;
          public void SELECT_buss()
    {
       
        
         //`NAME`, `CODE`, `COMPTEMERE`, `CODEMERE`, `CLASS`
        try{
             
            String sql="SELECT * FROM   OHADA where NAME='"+buss.getSelectedItem()+"' and CLASS=5";
          
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                //String sum=rs.getString("nom");
               COMPTEMERE=rs.getString("COMPTEMERE");
               
                
                CODEMERE=rs.getString("CODEMERE");
              
                
                CODE=rs.getString("CODE");
              
                
                 NAME=rs.getString("NAME");
                 
                  CLASS=rs.getString("CLASS");
                  SUBSTRS =rs.getString("SUBSTR");
                  //COMPTEMERE,CODEMERE,CODE,NAME, CLASS,SUBSTRS
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
    
    }
          
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
        mode.addColumn("LIBELLE                 ");
        mode.addColumn("DEBIT USD");
        mode.addColumn("CREDIT USD");
        mode.addColumn("DEBIT CDF");
        mode.addColumn("CREDIT CDF");   
        mode.addColumn("BATCHS");
       
       
        
        
        
        jTable1.setModel(mode);

  }
     public void Save_CAISSE_usd(){
       String COMPTEMEREEXCEL = null,CODEMEREEXCEL = null,CODEEXCEL,NAMEEXCEL = null, CLASSEXCEL = null,SUBSTRSEXCEL = null;
   DefaultTableModel excels= (DefaultTableModel)jTable1.getModel(); 
   String DATES= null,LB,LIBELLE = null,BATCHS = null, DEBIT_USD = null,DEBIT_CDF= null ,CREDIT_USD= null ,CREDIT_CDF= null,CODE2= null,PROJET= null,COMPTEMERE = null,CODEMERE = null,OHADACODE = null,NAME = null,CLASS = null,SUBSTRS  = null,CCOMPTEMERE = null,CCODEMERE = null,COHADACODE,CNAME = null,CCLASS = null,CSUBSTRS  = null,CCODE = null;
   
    for(int i=0; i < excels.getRowCount();i++){
      DATES = excels.getValueAt(i,0).toString();
      CODEEXCEL= excels.getValueAt(i,1). toString();
      LB = excels.getValueAt(i,2). toString();
      LIBELLE = excels.getValueAt(i,3). toString();
      DEBIT_USD = excels.getValueAt(i,4). toString();
      CREDIT_USD= excels.getValueAt(i,5). toString();
      DEBIT_CDF = excels.getValueAt(i,6). toString();
     CREDIT_CDF = excels.getValueAt(i,7). toString();
     BATCHS = excels.getValueAt(i,8). toString();
  
     
   
  
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
        PreparedStatement pst = con.prepareStatement("INSERT INTO `ohada_trans`(`COMPTE_M`, `COMPTE`, `CODE_M`, `CODE`, `CLASSE`, `SUBSTR`, `DEBIT`, `CREDIT`, `NUM`, `DATES`, `BUSS`, `LIBELLE`, `JOURNAL`, `CODE1`,DEBIT_CDF,CREDIT_CDF,DEVICE,LB)"
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        
        pst.setString(1, COMPTEMERE);
        pst.setString(2, NAME);
         pst.setString(3, CODEMERE);
         pst.setString(4, CODE);
         pst.setString(5,"5");
         pst.setString(6, "57");
         pst.setString(7,DEBIT_USD);
         pst.setString(8,"0");
         pst.setString(9,rolls);
        
         pst.setString(10,DATES);
         pst.setString(11,buss.getSelectedItem().toString());
         pst.setString(12,LIBELLE);
         pst.setString(13,"[JC]");
          pst.setString(14,"");
           pst.setString(15,"");
            pst.setDouble(16,0.0);
            pst.setString(17,"USD");
            pst.setString(18,"");
       
          
         
          pst.executeUpdate();
        
           //      JOptionPane.showMessageDialog(null,"Caisse saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }

        
        
      }else if((LB.equals("") && CREDIT_USD.equals("0.0"))){
          // APPROVISIONEMENT BANQUE
     
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
          JOptionPane.showMessageDialog(null,"Code "+CODEEXCEL+" does not exist");    
        }
 try{
             
            String sql="SELECT * FROM   OHADA where NAME='"+buss.getSelectedItem()+"'";
          
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
         pst.setString(8,CREDIT_USD);
         pst.setString(7,"0");
         pst.setString(9,rolls);
         
       
         pst.setString(10,DATES);
         pst.setString(11,BUSS.getSelectedItem().toString());
         pst.setString(12,LIBELLE);
         pst.setString(13,"[JC]");
          pst.setString(14,CODE);
           pst.setString(16,"0");
            pst.setDouble(15,0);
       pst.setString(17, "USD");
       pst.setString(18,LB);
          
         
          pst.executeUpdate();
        
           //      JOptionPane.showMessageDialog(null,"Caisse saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
        
        try {
        // String tmp =jComboBox3.getSelectedItem().toString();
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
         
      
         pst.setString(10,DATES);
         pst.setString(11,BUSS.getSelectedItem().toString());
         pst.setString(12,LIBELLE);
         pst.setString(13,"[JC]");
          pst.setString(14, CODEEXCEL);
          pst.setDouble(16,0);
            pst.setString(15,"0");
       
    pst.setString(17,"USD");  
pst.setString(18,LB);    
         
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
          JOptionPane.showMessageDialog(null,"Code "+CODEEXCEL+" does not exist");    
        }
 try{
             
            String sql="SELECT * FROM   OHADA where NAME='"+buss.getSelectedItem()+"'";
          
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
         
   
         pst.setString(10,DATES);
         pst.setString(11,buss.getSelectedItem().toString());
         pst.setString(12,LIBELLE);
         pst.setString(13,"[JC]");
          pst.setString(14,CODE);
           pst.setString(16,"0");
            pst.setDouble(15,0);
       pst.setString(17, "USD");
       pst.setString(18, LB);
       
       
          
         
          pst.executeUpdate();
        
           //      JOptionPane.showMessageDialog(null,"Caisse saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
        
        try {
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
         
 
       
         pst.setString(10, DATES);
         pst.setString(11,buss.getSelectedItem().toString());
         pst.setString(12,LIBELLE);
         pst.setString(13,"[JC]");
          pst.setString(14, CODEEXCEL);
          pst.setDouble(16,0);
            pst.setString(15,"0");
       
    pst.setString(17,"USD");    
    pst.setString(18,LB); 
         
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
       JOptionPane.showMessageDialog(null,"Code "+CODEEXCEL+" does not exist");    
        }
 try{
             
            String sql="SELECT * FROM   OHADA where NAME='"+buss.getSelectedItem()+"'";
          
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
         pst.setString(10, DATES);
         pst.setString(11,BUSS.getSelectedItem().toString());
         pst.setString(12,LIBELLE);
         pst.setString(13,"[JC]");
          pst.setString(14,CODE);
           pst.setString(16,"0");
            pst.setDouble(15,0);
       pst.setString(17, "USD");
          pst.setString(18,LB);
         
          pst.executeUpdate();
        
           //      JOptionPane.showMessageDialog(null,"Caisse saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
        
        try {
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
         
      
         pst.setString(10,DATES);
         pst.setString(11,BUSS.getSelectedItem().toString());
         pst.setString(12,LIBELLE);
         pst.setString(13,"[JC]");
          pst.setString(14, CODEEXCEL);
          pst.setDouble(16,0);
            pst.setString(15,"0");
       
    pst.setString(17,"USD");   
    pst.setString(18,LB);
         
          pst.executeUpdate();
        
               //  JOptionPane.showMessageDialog(null,"Caisse saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
     

 //BUDGET CREDIT
      String ITEM = null,CODECAT = null,CODESUBCAT = null,CAT = null,SUBCAT = null;
       try{
          String sql="SELECT item,code FROM  budget_code WHERE code='"+LB+"' and CAT='"+BUSS.getSelectedItem()+"'";
          
            pst=con.prepareStatement(sql);
            //pst.setString(1, tmp);
            rs=pst.executeQuery();
            if(rs.next()){
                 
                ITEM = rs.getString("item"); 
          }
  
      }catch(Exception ex){
        JOptionPane.showMessageDialog(null,"LB "+LB+" does not exist");  
      }
        
          try{
          String sql="SELECT CAT,LB,LBSUB,SUB_CAT FROM  budget WHERE code='"+LB+"' and PROJECT='"+BUSS.getSelectedItem()+"'";
          
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
         //  JOptionPane.showMessageDialog(null,LB);  
          try{
    String sql="INSERT INTO `budget_trans`(`ITEM`, `DEBIT`, `CREDIT`, `SOLD`, `PROJET`, `CODE`, `NUM`, `CAT`, `DATES`, `MOIS`, `ANNEE`,SUB_CAT,CODE_CAT,CODE_SUB_CAT,`ITEMS`,BATCH) "+"values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    pst=con.prepareStatement(sql);
    pst.setString(1,LIBELLE);
    pst.setString(2,"0");
    pst.setString(3,CREDIT_USD);
    pst.setDouble(4, 0);
    pst.setString(5,BUSS.getSelectedItem().toString());
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
     pst.setString(16,BATCHS);
    
    
    pst.executeUpdate();
    // JOptionPane.showMessageDialog(null,"BUDGET TRANS");
    }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}


   }// END OF BIG ELSE
     
      
     
   
  
    } //END FOR
     JOptionPane.showMessageDialog(null,"Tranction Saved");
    } 
           
    //jComboBox2

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
        jPanel6 = new javax.swing.JPanel();
        webDateField1 = new com.alee.extended.date.WebDateField();
        jLabel8 = new javax.swing.JLabel();
        webDateField2 = new com.alee.extended.date.WebDateField();
        jPanel7 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        solde_available = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        solde_out = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        solde_in = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        Solde_in = new javax.swing.JTextField();
        Solde_out = new javax.swing.JTextField();
        Solde_available = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTable table = new JTable(mode) {
            public Component prepareRenderer(TableCellRenderer renderer, int Index_row, int Index_col) {
                // get the current row
                Component comp = super.prepareRenderer(renderer, Index_row, Index_col);
                // even index, not selected
                if (Index_row % 2 == 0 && !isCellSelected(Index_row, Index_col)) {
                    comp.setBackground(Color.lightGray);
                } else {
                    comp.setBackground(Color.white);
                }
                return comp;
            }
        };
        jTable1 = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu4 = new javax.swing.JMenu();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenu5 = new javax.swing.JMenu();

        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_Bank_Building_16px.png"))); // NOI18N
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
                formInternalFrameOpened(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        buss.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        buss.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECT ONE BANK" }));
        buss.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                bussPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        buss.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bussActionPerformed(evt);
            }
        });
        jPanel6.add(buss, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 12, 240, -1));

        webDateField1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        webDateField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                webDateField1ActionPerformed(evt);
            }
        });
        jPanel6.add(webDateField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 41, 111, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setText(">");
        jPanel6.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(126, 46, -1, -1));

        webDateField2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jPanel6.add(webDateField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 40, 110, -1));

        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jPanel4.setBackground(new java.awt.Color(102, 255, 102));
        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("SOLD");
        jLabel11.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        solde_available.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        solde_available.setForeground(new java.awt.Color(255, 255, 255));
        solde_available.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        solde_available.setText("0");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(solde_available, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(solde_available)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(255, 102, 102));
        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("          CREDIT");
        jLabel10.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        solde_out.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        solde_out.setForeground(new java.awt.Color(255, 255, 255));
        solde_out.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        solde_out.setText("0");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(solde_out, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(solde_out)
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(102, 102, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        solde_in.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        solde_in.setForeground(new java.awt.Color(255, 255, 255));
        solde_in.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        solde_in.setText("0");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("             DEBIT");
        jLabel9.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(solde_in, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(solde_in)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        BUSS.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        BUSS.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECT ONE PROJECT" }));
        BUSS.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                BUSSPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        BUSS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BUSSActionPerformed(evt);
            }
        });

        Solde_in.setEditable(false);
        Solde_in.setBackground(new java.awt.Color(0, 0, 255));
        Solde_in.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Solde_in.setForeground(new java.awt.Color(255, 255, 255));
        Solde_in.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        Solde_out.setEditable(false);
        Solde_out.setBackground(new java.awt.Color(255, 51, 51));
        Solde_out.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Solde_out.setForeground(new java.awt.Color(255, 255, 255));
        Solde_out.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        Solde_available.setEditable(false);
        Solde_available.setBackground(new java.awt.Color(51, 255, 51));
        Solde_available.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Solde_available.setForeground(new java.awt.Color(255, 255, 255));
        Solde_available.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BUSS, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(Solde_in, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Solde_out, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Solde_available, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(BUSS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Solde_in, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Solde_out)
                    .addComponent(Solde_available, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTable1.setBackground(new java.awt.Color(240, 240, 240));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE)
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

        jMenuItem1.setText("New_Bank/ Nouveau Compte");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem5.setText("Manuel_Transaction");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem5);

        jMenu2.setText("Print");

        jMenuItem6.setText("By_Bank");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem6);

        jMenuItem7.setText("General");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem7);

        jMenuItem3.setText("Receipt Out");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem3);

        jMenu1.add(jMenu2);

        jMenuItem2.setText("Sold");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenu6.setText("Import");

        jMenuItem4.setText("Excel");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem4);

        jMenuItem8.setText("Save Excel");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem8);

        jMenu1.add(jMenu6);

        jMenuBar1.add(jMenu1);

        jMenu3.setEnabled(false);
        jMenuBar1.add(jMenu3);

        jMenu5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_sync_16px.png"))); // NOI18N
        jMenu5.setText("Refresh");
        jMenu5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu5MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu5);

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

        setBounds(30, 15, 1083, 588);
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
Add_New_Account m = new Add_New_Account();
m.show();
m. setDefaultCloseOperation(DISPOSE_ON_CLOSE);// TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
  
       // calltabe();
      //table();
        // search2ALL();
     //callcaiseAll();
  // TODO add your handling code here:
    }//GEN-LAST:event_formInternalFrameOpened

    private void bussPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_bussPopupMenuWillBecomeInvisible
webDateField1.setText("");
webDateField2.setText("");
        select1();
        if(buss.getSelectedItem().equals("SELECT ONE BANK")){
     search2ALL();
     callcaiseAll();
          webDateField1.setText("");
          webDateField2.setText("");
        }else if(webDateField1.getText().equals("")){  
       search2BUS();
callcaisebuss();
        } else{
            search2DATE();
            callcaiseDate();
        }
    




    // TODO add your handling code here:
    }//GEN-LAST:event_bussPopupMenuWillBecomeInvisible

    private void bussActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bussActionPerformed
         // TODO add your handling code here:
    }//GEN-LAST:event_bussActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
if(BUSS.getSelectedItem().equals("SELECT ONE PROJECT")){
report(); 
}else{
report_buss();
}
        

// TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
reports();         // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
transaction_manuel_banque m = new transaction_manuel_banque();

m.show();
m. setDefaultCloseOperation(DISPOSE_ON_CLOSE);     // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenu4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu4MouseClicked
this.dispose();  
newdesingn.title.setText("Windows");// TODO add your handling code here:
    }//GEN-LAST:event_jMenu4MouseClicked

    private void webDateField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_webDateField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_webDateField1ActionPerformed

    private void formInternalFrameActivated(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameActivated
newdesingn.title.setText("Bank Journal / Journal Banque");//callbank();        // TODO add your handling code here:
    }//GEN-LAST:event_formInternalFrameActivated

    private void jMenu5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu5MouseClicked
buss.removeAllItems();
        callbank();        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu5MouseClicked

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
if(buss.getSelectedItem().equals("SELECT ONE BANK")){
JOptionPane.showMessageDialog(null,"Wrong Data","Error",JOptionPane.ERROR_MESSAGE);
}else{
        sold_banque m= new sold_banque();
        newdesingn.jDesktopPane1.add(m);
        m.show();
        m. setDefaultCloseOperation(DISPOSE_ON_CLOSE); 
}        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
if(buss.getSelectedItem().equals("SELECT ONE BANK")){
JOptionPane.showMessageDialog(null,"Wrong Data","Error",JOptionPane.ERROR_MESSAGE);
}else{
        bon_banque m =new bon_banque ();
    
       int row= jTable1.getSelectedRow();
          String Table_click = (jTable1.getModel().getValueAt(row,2). toString());
           String Table_clicks = (jTable1.getModel().getValueAt(row,3). toString());
            String Table_clickss = (jTable1.getModel().getValueAt(row,4). toString());
            
           
 String Sortie=Table_clicks.replace(",", "");//replaces all occurrences of a to e  
  String entree=Table_clickss.replace(",", "");
         // objective.setText(Sortie);
            
            
           if(Table_clicks.equals("0.00")){
             bon_banque.designation.setText("BON D'ENTREE BANQUE");
              // double DoubleValue = Double.parseDouble(Table_clickss);
              double DoubleValue = Double.parseDouble(entree);
        int IntValue = (int) DoubleValue;
       Long a = Long.parseLong(""+ IntValue);
      bon_banque.displays.setText(""+ FrenchNumberToWords.convert(a));
       // montant.setText(""+ IntValue);
            bon_banque.montant.setText(""+IntValue);
            
            
            
            String repalceString= bon_banque.designation.getText().replace("'","''");
    try{
      
       String sql="SELECT DESCR as 'Motifs',PT as 'Montant',NAME as 'En faveur de',Num,Date FROM recu where TRANSACTION='"+repalceString+"'";
       pst = con.prepareStatement(sql);
      rs= pst.executeQuery();
       bon_banque.jTable1.setModel(DbUtils.resultSetToTableModel(rs));
        
        TableColumn col0=bon_banque.jTable1.getColumnModel().getColumn(0);
        TableColumn col1=bon_banque.jTable1.getColumnModel().getColumn(1);
        TableColumn col2=bon_banque.jTable1.getColumnModel().getColumn(2);
        TableColumn col3=bon_banque.jTable1.getColumnModel().getColumn(3);
        TableColumn col4=bon_banque.jTable1.getColumnModel().getColumn(4);
        
       
       
       
       col0.setPreferredWidth(100);
       col1.setPreferredWidth(50);
       col2.setPreferredWidth(100);
       col3.setPreferredWidth(50);
       col4.setPreferredWidth(50);
      
       
       
    }catch(SQLException ex ){
     JOptionPane.showMessageDialog(null, ex);
}
            
           }else{
             bon_banque.designation.setText("BON DE SORTIE BANQUE");
             
             //double DoubleValue = Double.parseDouble(Table_clicks);
            double DoubleValue = Double.parseDouble(Sortie);
        int IntValue = (int) DoubleValue;
         Long a = Long.parseLong(""+ IntValue);
      bon_banque.displays.setText(""+ FrenchNumberToWords.convert(a));
       // montant.setText(""+ IntValue);
            bon_banque.montant.setText(""+IntValue);
            
            
            
            
            
             String repalceString= bon_banque.designation.getText().replace("'","''");
    try{
      
       String sql="SELECT DESCR as 'Motifs',PT as 'Montant',NAME as 'En faveur de',Num,Date FROM recu where TRANSACTION='"+repalceString+"'";
       pst = con.prepareStatement(sql);
      rs= pst.executeQuery();
       bon_banque.jTable1.setModel(DbUtils.resultSetToTableModel(rs));
        
        TableColumn col0=bon_banque.jTable1.getColumnModel().getColumn(0);
        TableColumn col1=bon_banque.jTable1.getColumnModel().getColumn(1);
        TableColumn col2=bon_banque.jTable1.getColumnModel().getColumn(2);
        TableColumn col3=bon_banque.jTable1.getColumnModel().getColumn(3);
        TableColumn col4=bon_banque.jTable1.getColumnModel().getColumn(4);
        
       
       
       
       col0.setPreferredWidth(100);
       col1.setPreferredWidth(50);
       col2.setPreferredWidth(100);
       col3.setPreferredWidth(50);
       col4.setPreferredWidth(50);
      
       
       
    }catch(SQLException ex ){
     JOptionPane.showMessageDialog(null, ex);
}
           }
          bon_banque.motif.setText(Table_click);
         
        m.show();
        m. setDefaultCloseOperation(DISPOSE_ON_CLOSE);
}        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void BUSSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BUSSActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BUSSActionPerformed

    private void BUSSPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_BUSSPopupMenuWillBecomeInvisible
if(webDateField1.getText().equals("")){
callcaisebuss_buss();
search2BUSS();
}else{
    callcaisebuss_bussdate();
  search2DATE();  
    }
        
// TODO add your handling code here:
    }//GEN-LAST:event_BUSSPopupMenuWillBecomeInvisible

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
CAISSE_Model();  
imports();// TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
Save_CAISSE_usd();        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem8ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static final javax.swing.JComboBox<String> BUSS = new javax.swing.JComboBox<>();
    private javax.swing.JTextField Solde_available;
    private javax.swing.JTextField Solde_in;
    private javax.swing.JTextField Solde_out;
    public static final javax.swing.JComboBox<String> buss = new javax.swing.JComboBox<>();
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTable jTable1;
    private javax.swing.JLabel solde_available;
    private javax.swing.JLabel solde_in;
    private javax.swing.JLabel solde_out;
    private com.alee.extended.date.WebDateField webDateField1;
    private com.alee.extended.date.WebDateField webDateField2;
    // End of variables declaration//GEN-END:variables
}
