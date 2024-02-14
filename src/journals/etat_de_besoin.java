/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package journals;

import canalplus.cell.TableActionCellEditor;
import canalplus.cell.TableActionCellRender;
import canalplus.cell.TableActionEvent;
import intreprisemanagementsystem.JavaDbConnect;
import intreprisemanagementsystem.JavaDbConnectonline;
import intreprisemanagementsystem.NewJFrame;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.HeadlessException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.showInputDialog;
import static javax.swing.JOptionPane.showMessageDialog;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
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
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 *
 * @author Doshe PC
 */
public class etat_de_besoin extends javax.swing.JPanel {

 Connection con=null;
PreparedStatement pst=null;
Connection cononline=null;
ResultSet rs= null;
DefaultTableModel mode;
 private static HttpURLConnection connection;
String rolls,CLASSE,CLASSE1,SUBSTRS,SUBSTR,CATB,SUB_CATB,APPROV,IDS,ECRI,check=null,repalceString;;

 DefaultTableModel model;
    etat_de_besoin_Service api;
    public etat_de_besoin() {
        initComponents();
         con=JavaDbConnect.dbConnect();
        date3.setDate(new Date());
         date4.setDate(new Date());
         
   
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
    jTable1.getColumnModel().getColumn(7).setCellRenderer(new TableActionCellRender());
    jTable1.getColumnModel().getColumn(7).setCellEditor(new TableActionCellEditor(event));
         // call();
         calldata();
    Groupe1();
    load();
    }
    
    public void calldata(){

    try{
        //  String sql="SELECT `ID` AS NUM,`DET`, `QTY`, `PU`, `PT`, `DATES`, `APPROUVATION` FROM `etat_de_besoin` WHERE  `ORIENTATION`='FINANCE' AND ORIENTATION2='Fin' and buss='"+journal1.buss.getText()+"'";   pst = con.prepareStatement(sql);
           
        PreparedStatement ps = con.prepareStatement("Select * from etat_de_besoin WHERE  `ORIENTATION`='FINANCE' AND ORIENTATION2='Fin' and buss='"+journal1.buss.getText()+"'");
        ResultSet rs=ps.executeQuery();
        DefaultTableModel tm = (DefaultTableModel)jTable1.getModel();
        tm.setRowCount(0);

      while(rs.next()){//`ID` AS NUM,`DET`, `QTY`, `PU`, `PT`, `DATES`, `APPROUVATION`
            Object o[] = {rs.getString("ID"),rs.getString("DET"),rs.getDouble("QTY"),rs.getDouble("PU"),rs.getDouble("PT"),rs.getString("DATES"),rs.getString("APPROUVATION")};
            tm.addRow(o);



        }
      /*
      int count= jTable1.getModel().getRowCount();
      if(count==0){
       Object o[] = {home.date.getText(),"repport solde",0.0,0.0,0.0,0.0,"","",""};
            tm.addRow(o);
      }
     */
          
       


    }
    catch(Exception e){

        JOptionPane.showMessageDialog(null,"Error in Employee Grid View..... "+e);
    }

}
public void call(){
 
        try{

            String sql="SELECT `ID` AS NUM,`DET`, `QTY`, `PU`, `PT`, `DATES`, `APPROUVATION` FROM `etat_de_besoin` WHERE  `ORIENTATION`='FINANCE' AND ORIENTATION2='Fin' and buss='"+journal1.buss.getText()+"'";   pst = con.prepareStatement(sql);
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
            
            col0.setPreferredWidth(0);
            col1.setPreferredWidth(250);
            col2.setPreferredWidth(30);
            col3.setPreferredWidth(50);
            col4.setPreferredWidth(50);
            col5.setPreferredWidth(80);
            col6.setPreferredWidth(100);

        }catch(SQLException ex ){
            JOptionPane.showMessageDialog(null, ex);
        }
        
         try{
       
         String sql="SELECT distinct NUM FROM etat_de_besoin WHERE ORIENTATION='FINANCE' and ORIENTATION2='Fin' and PRINT='' and buss='"+journal1.buss.getText()+"' order by num";
          pst = con.prepareStatement(sql);
        rs= pst.executeQuery();
        jTable22.setModel(DbUtils.resultSetToTableModel(rs));
        
      }catch(SQLException ex ){
      JOptionPane.showMessageDialog(null, ex);
}    
  try{
           
             String sql="SELECT  distinct(NUM),SUP,DATES FROM `etat_de_besoin` where Pay='Pay' and BUSS='"+journal1.buss.getText()+"' order by num";
     //  String sql="SELECT ID, `NUM_FACTURE`, `DATE_FACTURE`, `DESCRIPTION`, `DEBIT_QTY`, `DEBIT_PU`, `DEBIT_PT`, `DEBIT_TVA`, `CREDIT_QTY`, `CREDIT_PU`, `CREDIT_PT`, `CREDIT_TVA`, `DATES`, `NUM` FROM facture_fournisseur where compte='"+jComboBox6.getSelectedItem()+"'";
       pst = con.prepareStatement(sql);
      rs= pst.executeQuery();
      
       
       jTable5.setModel(DbUtils.resultSetToTableModel(rs));
       mode=new DefaultTableModel();
       
       
        TableColumn col0=jTable5.getColumnModel().getColumn(0);
        TableColumn col1=jTable5.getColumnModel().getColumn(1);
        TableColumn col2=jTable5.getColumnModel().getColumn(2);
      //  TableColumn col3=jTable3.getColumnModel().getColumn(3);
       
       col0.setPreferredWidth(100);
       col1.setPreferredWidth(150);
        col2.setPreferredWidth(30);
      
       //col4.setPreferredWidth(100);
       
      
      
       
       
      // jTable1.setModel(mode);
       
     
    }catch(SQLException ex ){
     JOptionPane.showMessageDialog(null, ex);
}
    
}

   public void searchS() {
       if(jRadioButton1.isSelected()){
        try{
   String sql="SELECT `ID` AS NUM,`DET`, `QTY`, `PU`, `PT`, `DATES`, `APPROUVATION` FROM `etat_de_besoin_b` WHERE NUM like '"+recherche.getText()+"%'  AND `ORIENTATION`='FINANCE' and buss='"+journal1.buss.getText()+"'";
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
       col0.setPreferredWidth(5);
       col1.setPreferredWidth(250);
       col2.setPreferredWidth(30);
       col3.setPreferredWidth(50);
       col4.setPreferredWidth(50);
       col5.setPreferredWidth(80);
       col6.setPreferredWidth(100);
     
    }catch(SQLException ex ){
     JOptionPane.showMessageDialog(null, ex);
}
       }else{
           
            try{
        //  String sql="SELECT `ID` AS NUM,`DET`, `QTY`, `PU`, `PT`, `DATES`, `APPROUVATION` FROM `etat_de_besoin` WHERE  `ORIENTATION`='FINANCE' AND ORIENTATION2='Fin' and buss='"+journal1.buss.getText()+"'";   pst = con.prepareStatement(sql);
           
        PreparedStatement ps = con.prepareStatement("Select * from etat_de_besoin WHERE NUM like '"+recherche.getText()+"%'  AND `ORIENTATION`='FINANCE' and buss='"+journal1.buss.getText()+"'");
        ResultSet rs=ps.executeQuery();
        DefaultTableModel tm = (DefaultTableModel)jTable1.getModel();
        tm.setRowCount(0);

      while(rs.next()){//`ID` AS NUM,`DET`, `QTY`, `PU`, `PT`, `DATES`, `APPROUVATION`
            Object o[] = {rs.getString("ID"),rs.getString("DET"),rs.getDouble("QTY"),rs.getDouble("PU"),rs.getDouble("PT"),rs.getString("DATES"),rs.getString("APPROUVATION")};
            tm.addRow(o);



        }
       mode=new DefaultTableModel();
       
       
          TableColumn col0=jTable1.getColumnModel().getColumn(0);
        TableColumn col1=jTable1.getColumnModel().getColumn(1);
        TableColumn col2=jTable1.getColumnModel().getColumn(2);
        TableColumn col3=jTable1.getColumnModel().getColumn(3);
        TableColumn col4=jTable1.getColumnModel().getColumn(4);
        TableColumn col5=jTable1.getColumnModel().getColumn(5);
        TableColumn col6=jTable1.getColumnModel().getColumn(6);  
       col0.setPreferredWidth(5);
       col1.setPreferredWidth(250);
       col2.setPreferredWidth(30);
       col3.setPreferredWidth(50);
       col4.setPreferredWidth(50);
       col5.setPreferredWidth(80);
       col6.setPreferredWidth(100);
          
       


    }
    catch(Exception e){

        JOptionPane.showMessageDialog(null,"Error in Employee Grid View..... "+e);
    }
            /*
        try{
   String sql="SELECT `ID` AS NUM,`DET`, `QTY`, `PU`, `PT`, `DATES`, `APPROUVATION` FROM `etat_de_besoin` WHERE NUM like '"+recherche.getText()+"%'  AND `ORIENTATION`='FINANCE' and buss='"+journal1.buss.getText()+"'";
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
       col0.setPreferredWidth(5);
       col1.setPreferredWidth(250);
       col2.setPreferredWidth(30);
       col3.setPreferredWidth(50);
       col4.setPreferredWidth(50);
       col5.setPreferredWidth(80);
       col6.setPreferredWidth(100);
     
    }catch(SQLException ex ){
     JOptionPane.showMessageDialog(null, ex);
}
            */
       
       }
   
     
      }
   
     public void select_jTableNUM(){
         if(jRadioButton1.isSelected()){
              int row= jTable22.getSelectedRow();
          String Table_click = (jTable22.getModel().getValueAt(row,0). toString());
        try{
         
          String sql = "SELECT * FROM etat_de_besoin_b WHERE NUM= '"+Table_click+"' and buss='"+journal1.buss.getText()+"'";
          pst = con.prepareStatement(sql);
          rs=pst.executeQuery();
          if(rs.next()){
              String add1 = rs.getString("NUM");//SUP,CHANT,NUM,DET,QTY,PU,PT,DATES
              recherche.setText(add1);
              APPROV= rs.getString("APPROUVATION");//SUP,CHANT,NUM,DET,QTY,PU,PT,DATES
              
              String add11 = rs.getString("DEVICE");//SUP,CHANT,NUM,DET,QTY,PU,PT,DATES
             devise.setText(add11);
             
             
             IDS = rs.getString("NUM_ID");
                String add2 = rs.getString("SUP");
              sup.setText(add2);
                String add3 = rs.getString("CHANT");
              chat.setText(add3);
                String add4 = rs.getString("NUM");
              roll.setText(add4);
              
                
          }
         
    }catch(SQLException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }
        
         try{
           
             String sql="SELECT `ID` AS NUM,`DET`, `QTY`, `PU`, `PT`, `DATES`, `APPROUVATION` FROM `etat_de_besoin_b` WHERE NUM= '"+Table_click+"'";
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
        
       col0.setPreferredWidth(5);
       col1.setPreferredWidth(250);
       col2.setPreferredWidth(30);
       col3.setPreferredWidth(50);
       col4.setPreferredWidth(50);
       col5.setPreferredWidth(80);
       col6.setPreferredWidth(100);
     
    }catch(SQLException ex ){
     JOptionPane.showMessageDialog(null, ex);
}
         }else{
         int row= jTable22.getSelectedRow();
          String Table_click = (jTable22.getModel().getValueAt(row,0). toString());
        try{
         
          String sql = "SELECT * FROM etat_de_besoin WHERE NUM= '"+Table_click+"' and buss='"+journal1.buss.getText()+"'";
          pst = con.prepareStatement(sql);
          rs=pst.executeQuery();
          if(rs.next()){
              String add1 = rs.getString("NUM");//SUP,CHANT,NUM,DET,QTY,PU,PT,DATES
              recherche.setText(add1);
              APPROV= rs.getString("APPROUVATION");//SUP,CHANT,NUM,DET,QTY,PU,PT,DATES
              
              String add11 = rs.getString("DEVICE");//SUP,CHANT,NUM,DET,QTY,PU,PT,DATES
             devise.setText(add11);
             
             
             IDS = rs.getString("NUM_ID");
                String add2 = rs.getString("SUP");
              sup.setText(add2);
                String add3 = rs.getString("CHANT");
              chat.setText(add3);
                String add4 = rs.getString("NUM");
              roll.setText(add4);
              
                
          }
         
    }catch(SQLException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }
        
         try{
           
             String sql="SELECT `ID` AS NUM,`DET`, `QTY`, `PU`, `PT`, `DATES`, `APPROUVATION` FROM `etat_de_besoin` WHERE NUM= '"+Table_click+"'";
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
        
       col0.setPreferredWidth(5);
       col1.setPreferredWidth(250);
       col2.setPreferredWidth(30);
       col3.setPreferredWidth(50);
       col4.setPreferredWidth(50);
       col5.setPreferredWidth(80);
       col6.setPreferredWidth(100);
     
    }catch(SQLException ex ){
     JOptionPane.showMessageDialog(null, ex);
} 
         }
       
   }
     
       public void call_sum(){
           if(jRadioButton1.isSelected()){
             NumberFormat nf = new DecimalFormat("#,###.##");
         
          try{
            String sql="select sum(pt) from etat_de_besoin_b  WHERE num= '"+recherche.getText()+"' and buss='"+journal1.buss.getText()+"'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                
                Double sums=rs.getDouble("sum(pt)");
                //sum.setText(""+sums);
            String fn1 = nf.format(sums);
           sum.setText(fn1);
         //  compte.setText(fn1);
          
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
           }else{
         NumberFormat nf = new DecimalFormat("#,###.##");
         
          try{
            String sql="select sum(pt) from etat_de_besoin  WHERE num= '"+recherche.getText()+"' and buss='"+journal1.buss.getText()+"'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                
                Double sums=rs.getDouble("sum(pt)");
                //sum.setText(""+sums);
            String fn1 = nf.format(sums);
           sum.setText(fn1);
         //  compte.setText(fn1);
          
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
       }
           
         
         }
       
                public void select_jTable()
   {//121-506  +243 970 159 942
       if(jRadioButton1.isSelected()){
         try{
          int row= jTable1.getSelectedRow();
          String Table_click = (jTable1.getModel().getValueAt(row,0). toString());
           String libelle = (jTable1.getModel().getValueAt(row,1). toString());
            String dates = (jTable1.getModel().getValueAt(row,5). toString());
            String replaceString=libelle.replace("'", "''");
           //  String Table_click = (jTable1.getModel().getValueAt(row,0). toString());
          String sql = "SELECT * FROM etat_de_besoin_b WHERE ID= '"+Table_click+"' AND DET='"+replaceString+"' and DATES='"+dates+"'";
           //  String sql = "SELECT * FROM etat_de_besoin WHERE NUM_ID= '"+Table_click+"' AND NUM='"+recherche.getText()+"'";
          
          pst = con.prepareStatement(sql);
          rs=pst.executeQuery();
          if(rs.next()){
              IDS = rs.getString("NUM_ID");
                String add2 = rs.getString("SUP");
              sup.setText(add2);
                String add3 = rs.getString("CHANT");
              chat.setText(add3);
                String add4 = rs.getString("NUM");
              roll.setText(add4);
               
              
               String add9 = rs.getString("NUM");
              recherche.setText(add9);
              
                  Date add92 = rs.getDate("DATES");
             // jDateChooser1.setText(add92)
                    //  ;
            //  jDateChooser1.setDate(add92);
              
               String  APPROV = rs.getString("APPROUVATION");
if (APPROV.equals("")){
appr.setText("No");
}else{
appr.setText("Yes");
}//SUP,CHANT,NUM,DET,QTY,PU,PT,DATES
             
              
           String   ECRI = rs.getString("ECRITURE");//SUP,CHANT,NUM,DET,QTY,PU,PT,DATES
if (ECRI.equals("")){
ecri.setText("No");
}else{
ecri.setText("Yes");
}             
              
              String addsss = rs.getString("DEVICE");//SUP,CHANT,NUM,DET,QTY,PU,PT,DATES
              devise.setText(addsss);
          }
         
    }catch(SQLException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }
       }else{
         try{
          int row= jTable1.getSelectedRow();
          String Table_click = (jTable1.getModel().getValueAt(row,0). toString());
           String libelle = (jTable1.getModel().getValueAt(row,1). toString());
            String dates = (jTable1.getModel().getValueAt(row,5). toString());
            String replaceString=libelle.replace("'", "''");
           //  String Table_click = (jTable1.getModel().getValueAt(row,0). toString());
          String sql = "SELECT * FROM etat_de_besoin WHERE ID= '"+Table_click+"' AND DET='"+replaceString+"' and DATES='"+dates+"'";
           //  String sql = "SELECT * FROM etat_de_besoin WHERE NUM_ID= '"+Table_click+"' AND NUM='"+recherche.getText()+"'";
          
          pst = con.prepareStatement(sql);
          rs=pst.executeQuery();
          if(rs.next()){
              IDS = rs.getString("NUM_ID");
                String add2 = rs.getString("SUP");
              sup.setText(add2);
                String add3 = rs.getString("CHANT");
              chat.setText(add3);
                String add4 = rs.getString("NUM");
              roll.setText(add4);
               
              
               String add9 = rs.getString("NUM");
              recherche.setText(add9);
              
                  Date add92 = rs.getDate("DATES");
             // jDateChooser1.setText(add92)
                      ;
//              jDateChooser1.setDate(add92);
              
                 APPROV = rs.getString("APPROUVATION");//SUP,CHANT,NUM,DET,QTY,PU,PT,DATES
             
              
              ECRI = rs.getString("ECRITURE");//SUP,CHANT,NUM,DET,QTY,PU,PT,DATES
             
            if (APPROV.equals("")){
appr.setText("No");
}else{
appr.setText("Yes");
}
            if (ECRI.equals("")){
ecri.setText("No");
}else if(ECRI.equals("Yes/Annuler")){
ecri.setText("Yes/Annuler");
}else{
ecri.setText("Yes");
}   
              
              String addsss = rs.getString("DEVICE");//SUP,CHANT,NUM,DET,QTY,PU,PT,DATES
              devise.setText(addsss);
          }
         
    }catch(SQLException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }
       }

        
        
   }
                public void select_jTableb()
   {
     //  if(recherche.getText().isEmpty()){
       try{
          int row= jTable1.getSelectedRow();
          String Table_click = (jTable1.getModel().getValueAt(row,0). toString());
           String libelle = (jTable1.getModel().getValueAt(row,1). toString());
            String dates = (jTable1.getModel().getValueAt(row,5). toString());
            String replaceString=libelle.replace("'", "''");
           //  String Table_click = (jTable1.getModel().getValueAt(row,0). toString());
          String sql = "SELECT * FROM etat_de_besoin_b WHERE NUM_ID= '"+Table_click+"' AND DET='"+replaceString+"' and DATES='"+dates+"'";
           //  String sql = "SELECT * FROM etat_de_besoin WHERE NUM_ID= '"+Table_click+"' AND NUM='"+recherche.getText()+"'";
          
          pst = con.prepareStatement(sql);
          rs=pst.executeQuery();
          if(rs.next()){
              IDS = rs.getString("NUM_ID");
                String add2 = rs.getString("SUP");
              sup.setText(add2);
                String add3 = rs.getString("CHANT");
              chat.setText(add3);
                String add4 = rs.getString("NUM");
              roll.setText(add4);
               
              
               String add9 = rs.getString("NUM");
              recherche.setText(add9);
              
                 APPROV = rs.getString("APPROUVATION");//SUP,CHANT,NUM,DET,QTY,PU,PT,DATES
             
              
              ECRI = rs.getString("ECRITURE");//SUP,CHANT,NUM,DET,QTY,PU,PT,DATES
             
          
              
              String addsss = rs.getString("DEVICE");//SUP,CHANT,NUM,DET,QTY,PU,PT,DATES
              devise.setText(addsss);
          }
         
    }catch(SQLException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }
        
        
   }
                 public void call_sumb(){
             NumberFormat nf = new DecimalFormat("#,###.##");
         
          try{
            String sql="select sum(pt) from etat_de_besoin_b  WHERE num= '"+recherche.getText()+"' and buss='"+journal1.buss.getText()+"'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                
                Double sums=rs.getDouble("sum(pt)");
                //sum.setText(""+sums);
            String fn1 = nf.format(sums);
           sum.setText(fn1);
         //  compte.setText(fn1);
          
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
         
         }
              public void showEBData(){ 
   // SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
    // String add = dateFormats.format (jDateChooser1.setDate(new Date()));
     try{
       
         String sql="SELECT distinct NUM FROM etat_de_besoin WHERE ORIENTATION='FINANCE' and ORIENTATION2='Fin' and PRINT='' and buss='"+journal1.buss.getText()+"'";
          pst = con.prepareStatement(sql);
        rs= pst.executeQuery();
        jTable22.setModel(DbUtils.resultSetToTableModel(rs));
        
      }catch(SQLException ex ){
      JOptionPane.showMessageDialog(null, ex);
}    
}   
              
public void report_EB()
     {
         String A =showInputDialog("ENTREZ LE ROLL_No!!!");
          if(A.equals("1234")){
              
          if(recherche.getText().equals("")){
              String tmp =(String) recherche.getText();
     
             try{
                 
                   String sqls="Select path from pathn";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
            if(rs.next()){
             
                String sum=rs.getString("path");
                  
                 String NameFiles=sum;
            
            String NameFile=""+NameFiles+"etatdebesoin.jrxml";
                 
                 
                 
                JasperDesign jd=JRXmlLoader.load(NameFile);
                 
                // String report ="C:\\Users\\Doshe\\Documents\\NetBeansProjects\\intreprisemanagementsystem\\src\\report\\newReportmateriau.jrxml";
           //     JasperDesign jd=JRXmlLoader.load("C:\\Users\\Dosh\\Documents\\NetBeansProjects\\intreprisemanagementsystem\\src\\intreprisemanagementsystem\\etatdebesoin.jrxml");
                String sql="select * from etat_de_besoin INNER JOIN projet ON etat_de_besoin.BUSS=projet.PROJET_NUM  ";// where NUM='"+tmp+"' and BUSS='"+buss.getSelectedItem()+"'";
                 HashMap param= new HashMap();
                 String User=intreprisemanagementsystem.homme.user.getText();
    param.put("nom",User);
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
          }else{
              String tmp =(String) recherche.getText();
               try{
                   
                     String sqls="Select path from pathn";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
            if(rs.next()){
             
                String sum=rs.getString("path");
                  
                 String NameFiles=sum;
            
            String NameFile=""+NameFiles+"etatdebesoin.jrxml";
                 
                 
                 
                JasperDesign jd=JRXmlLoader.load(NameFile);
                // String report ="C:\\Users\\Doshe\\Documents\\NetBeansProjects\\intreprisemanagementsystem\\src\\report\\newReportmateriau.jrxml";
               // JasperDesign jd=JRXmlLoader.load("C:\\Users\\Dosh\\Documents\\NetBeansProjects\\intreprisemanagementsystem\\src\\intreprisemanagementsystem\\etatdebesoin.jrxml");
                String sql="select * from etat_de_besoin INNER JOIN projet ON etat_de_besoin.BUSS=projet.PROJET_NUM  where etat_de_besoin.NUM='"+tmp+"' and buss='"+journal1.buss.getText()+"'";
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
          }else{
              showMessageDialog(null,"VOUS N'ETES PAS AUTORISE A IMPRIMER CE DOCUMENT");
          }
     }
   public void report()
     {
         
 int row= jTable5.getSelectedRow();
          String Table_click = (jTable5.getModel().getValueAt(row,0). toString());
     
             try{
                 
                 String sqls="Select path from pathn";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
            if(rs.next()){
             
                String sumS=rs.getString("path");
                  
                 String NameFiles=sumS;
            
            String NameFile=""+NameFiles+"demande_de_payement.jrxml";
                 
                 
                 
                JasperDesign jd=JRXmlLoader.load(NameFile);
                 
                 
                 String sql="select*from recU  INNER JOIN projet ON recU.PROJET=projet.PROJET_NUm WHERE NAME_TO='"+Table_click+"'";
                  
 display.removeAll();
     display.repaint();
    display.revalidate();
                JRDesignQuery nq=new JRDesignQuery();
                nq.setText(sql);
                jd.setQuery(nq);
               
                 JasperReport jr =JasperCompileManager.compileReport(jd);
                 JasperPrint jp=JasperFillManager.fillReport(jr,null,con);
                 //JasperViewer.viewReport(jp,false);
                      JRViewer m= new JRViewer(jp);
     display.setLayout(new BorderLayout());
     display.add(m);
            }
             }catch(Exception ex){
                  JOptionPane.showMessageDialog(null, ex);   
             }
    
     }
      public void reportDMD(){
         String tmp =(String) recherche.getText();
       try{
                   
                     String sqls="Select path from pathn";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
            if(rs.next()){
             
                String sum=rs.getString("path");
                  
                 String NameFiles=sum;
            
            String NameFile=""+NameFiles+"afpde.jrxml";
                 
                 
                 
                JasperDesign jd=JRXmlLoader.load(NameFile);
                // String report ="C:\\Users\\Doshe\\Documents\\NetBeansProjects\\intreprisemanagementsystem\\src\\report\\newReportmateriau.jrxml";
               // JasperDesign jd=JRXmlLoader.load("C:\\Users\\Dosh\\Documents\\NetBeansProjects\\intreprisemanagementsystem\\src\\intreprisemanagementsystem\\etatdebesoin.jrxml");
               String sql="select*from etat_de_besoiN  INNER JOIN projet ON etat_de_besoiN.BUSS=projet.PROJET_NUm INNER JOIN  employee ON etat_de_besoiN.SUP= CONCAT(employee.NAME,' ',employee.LNAME) where etat_de_besoin.NUM='"+tmp+"' and buss='"+journal1.buss.getText()+"'";
             //   String sql="select * from etat_de_besoin INNER JOIN projet ON etat_de_besoin.BUSS=projet.PROJET_NUM  where etat_de_besoin.NUM='"+tmp+"' and buss='"+buss.getSelectedItem()+"'";
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
        
           public void reportDMD_B(){
         String tmp =(String) recherche.getText();
       try{
                   
                     String sqls="Select path from pathn";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
            if(rs.next()){
             
                String sum=rs.getString("path");
                  
                 String NameFiles=sum;
            
            String NameFile=""+NameFiles+"afpde_1.jrxml";
                 
                 
                 
                JasperDesign jd=JRXmlLoader.load(NameFile);
                // String report ="C:\\Users\\Doshe\\Documents\\NetBeansProjects\\intreprisemanagementsystem\\src\\report\\newReportmateriau.jrxml";
               // JasperDesign jd=JRXmlLoader.load("C:\\Users\\Dosh\\Documents\\NetBeansProjects\\intreprisemanagementsystem\\src\\intreprisemanagementsystem\\etatdebesoin.jrxml");
               String sql="select * from etat_de_besoin_b INNER JOIN projet ON etat_de_besoin_b.BUSS=projet.PROJET_NUM  where etat_de_besoin_b.NUM='"+tmp+"' and buss='"+journal1.buss.getText()+"'";
               // String sql="select*from etat_de_besoin_b  INNER JOIN projet ON etat_de_besoin_b.BUSS=projet.PROJET_NUm INNER JOIN  employee ON etat_de_besoin_b.SUP= employee.NAME  where etat_de_besoin_B.NUM='"+tmp+"' and buss='"+buss.getSelectedItem()+"'";
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

            public void delete()
    {
         try{
        String sql = "DELETE FROM etat_de_besoin WHERE NUM=?";
        
         pst = con.prepareStatement(sql);
         pst.setString(1,recherche.getText());
         pst.executeUpdate();

     }catch(SQLException | HeadlessException ex ){
         JOptionPane.showMessageDialog(null,ex);
     } 
    }
                     public void deletea()
    {
         try{
      String sql = "UPDATE etat_de_besoin SET ECRITURE='Yes/Annuler' WHERE NUM =?";
        
         pst = con.prepareStatement(sql);
         pst.setString(1,recherche.getText());
         pst.executeUpdate();

     }catch(SQLException | HeadlessException ex ){
         JOptionPane.showMessageDialog(null,ex);
     } 
      
    }
                     
public void tryconfirm(){
int res = JOptionPane.showOptionDialog(null, "Que voulez-vous faire?","EMS-L",
         JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
         new Object[] { "Remove", "Annuler" }, JOptionPane.YES_OPTION);
      if (res == JOptionPane.YES_OPTION){
         delete();
      } else if (res == JOptionPane.NO_OPTION) {
        deletea();}
}

public void tryconfirms(){
int res = JOptionPane.showOptionDialog(null, "Que voulez-vous faire?","EMS-L",
         JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
         new Object[] { "Remove", "Ecriture Yes" }, JOptionPane.YES_OPTION);
      if (res == JOptionPane.YES_OPTION){
         delete();
      } else if (res == JOptionPane.NO_OPTION) {
        try{
      String sql = "UPDATE etat_de_besoin SET ECRITURE='Yes' WHERE NUM =?";
        
         pst = con.prepareStatement(sql);
         pst.setString(1,recherche.getText());
         pst.executeUpdate();

     }catch(SQLException | HeadlessException ex ){
         JOptionPane.showMessageDialog(null,ex);
     } }
}
//    public void confir(){
//              int response = JOptionPane.showConfirmDialog(null, "Yes si ce pour Remove et No si ce pour Annuler", "Confirm",
//                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
//              new Object[] { "Yes", "No" }, JOptionPane.YES_OPTION);
//            if (response == JOptionPane.YES_OPTION) {
//      delete();
//            } else if (response == JOptionPane.NO_OPTION) {
//               deletea();
//            }
//    
//    }
            public void call_in_table3(){
    if(jRadioButton1.isSelected()){
     try{
           
             String sql="SELECT  `DET`, `QTY`, `PU`, `PT` FROM `etat_de_besoin_b` where NUM='"+num.getText()+"' AND print='Print'";
     //  String sql="SELECT ID, `NUM_FACTURE`, `DATE_FACTURE`, `DESCRIPTION`, `DEBIT_QTY`, `DEBIT_PU`, `DEBIT_PT`, `DEBIT_TVA`, `CREDIT_QTY`, `CREDIT_PU`, `CREDIT_PT`, `CREDIT_TVA`, `DATES`, `NUM` FROM facture_fournisseur where compte='"+jComboBox6.getSelectedItem()+"'";
       pst = con.prepareStatement(sql);
      rs= pst.executeQuery();
      
       
       jTable4.setModel(DbUtils.resultSetToTableModel(rs));
       mode=new DefaultTableModel();
       
       
        TableColumn col0=jTable4.getColumnModel().getColumn(0);
        TableColumn col1=jTable4.getColumnModel().getColumn(1);
        TableColumn col2=jTable4.getColumnModel().getColumn(2);
        TableColumn col3=jTable4.getColumnModel().getColumn(3);
       
       col0.setPreferredWidth(600);
       col1.setPreferredWidth(30);
        col2.setPreferredWidth(60);
       col3.setPreferredWidth(60);
      
       //col4.setPreferredWidth(100);
       
      
      
       
       
      // jTable1.setModel(mode);
       
     
    }catch(SQLException ex ){
     JOptionPane.showMessageDialog(null, ex);
}
      try{
            String sql="select sum(pt),SUP,CHANT,NUM,DATES,PAY,BUSS,DEVICE from etat_de_besoin_b  WHERE num='"+num.getText()+"' and Print='Print'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                Double sums=rs.getDouble("sum(pt)");
                 int sumss=rs.getInt("sum(pt)");
                 pts.setText(""+sumss);
                  DecimalFormat x = new DecimalFormat("#,##0.00");
             sum1.setText(x.format(sums));
                  
                   String sum1=rs.getString("SUP");
                  sup1.setText(sum1);
                  
                   String sum2=rs.getString("BUSS");
                  chantier.setText(sum2);
                  
                   String sum3=rs.getString("NUM");
                  num_eb.setText(sum3);
                  
                   String sum4=rs.getString("DATES");
                  jDateChooser2.setText(sum4);
                  
                   String sum5=rs.getString("PAY");
                  pay.setText(sum5);
                  
               String sum53=rs.getString("device");
                  usd.setText(sum53);
             
            // solde_out.setText(x.format(sum1));
                  
                  // solde_available.setText(x.format(c));
              
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
       
    
    }else{
    
     try{
           
             String sql="SELECT  `DET`, `QTY`, `PU`, `PT` FROM `etat_de_besoin` where NUM='"+num.getText()+"' AND print='Print'";
     //  String sql="SELECT ID, `NUM_FACTURE`, `DATE_FACTURE`, `DESCRIPTION`, `DEBIT_QTY`, `DEBIT_PU`, `DEBIT_PT`, `DEBIT_TVA`, `CREDIT_QTY`, `CREDIT_PU`, `CREDIT_PT`, `CREDIT_TVA`, `DATES`, `NUM` FROM facture_fournisseur where compte='"+jComboBox6.getSelectedItem()+"'";
       pst = con.prepareStatement(sql);
      rs= pst.executeQuery();
      
       
       jTable4.setModel(DbUtils.resultSetToTableModel(rs));
       mode=new DefaultTableModel();
       
       
        TableColumn col0=jTable4.getColumnModel().getColumn(0);
        TableColumn col1=jTable4.getColumnModel().getColumn(1);
        TableColumn col2=jTable4.getColumnModel().getColumn(2);
        TableColumn col3=jTable4.getColumnModel().getColumn(3);
       
       col0.setPreferredWidth(600);
       col1.setPreferredWidth(30);
        col2.setPreferredWidth(60);
       col3.setPreferredWidth(60);
      
       //col4.setPreferredWidth(100);
       
      
      
       
       
      // jTable1.setModel(mode);
       
     
    }catch(SQLException ex ){
     JOptionPane.showMessageDialog(null, ex);
}
      try{
            String sql="select sum(pt),SUP,CHANT,NUM,DATES,PAY,BUSS,DEVICE from etat_de_besoin  WHERE num='"+num.getText()+"' and Print='Print'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                Double sums=rs.getDouble("sum(pt)");
                 int sumss=rs.getInt("sum(pt)");
                 pts.setText(""+sumss);
                  DecimalFormat x = new DecimalFormat("#,##0.00");
             sum1.setText(x.format(sums));
                  
                   String sum1=rs.getString("SUP");
                  sup1.setText(sum1);
                  
                   String sum2=rs.getString("BUSS");
                  chantier.setText(sum2);
                  
                   String sum3=rs.getString("NUM");
                  num_eb.setText(sum3);
                  
                   String sum4=rs.getString("DATES");
                  jDateChooser2.setText(sum4);
                  
                   String sum5=rs.getString("PAY");
                  pay.setText(sum5);
                  
               String sum53=rs.getString("device");
                  usd.setText(sum53);
             
            // solde_out.setText(x.format(sum1));
                  
                  // solde_available.setText(x.format(c));
              
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
       
    } 
     
     }
            public void etroll()
     {
        // repalceString=designation.getText().replace("'","''");
         try{
            String sql="SELECT NUM FROM recu where TRANSACTION='DEMANDE DE PAIEMENT'  ORDER BY NUM DESC LIMIT 1";
           
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
                 rolls=stxt+snum;
                 
                 
                 
             }else{
                 //rolls="FICHE/EB/2018/1";
                 rolls="R-1001";
             }
             }catch(Exception e){
             JOptionPane.showMessageDialog(null, e); 
             
         }
                 
     
     }
            public void save(){
  String   monais = null;
      try{
            String sql="select * from currency  WHERE APPR='"+usd.getText()+"'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                   monais=rs.getString("NAME");
               
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
    
     etroll();
      Long a = Long.parseLong(""+pts.getText());
     displays.setText(""+FrenchNumberToWords.convert(a));
  int indexs[]=jTable4.getSelectedRows();
       
        for(int i=0; i < indexs.length;i++){
          String motif = (jTable4.getModel().getValueAt(indexs[i],0). toString());
            String pt = (jTable4.getModel().getValueAt(indexs[i],3). toString());
 
     try{
    String sql="INSERT INTO `recu`(`DESCR`, `QTY`, `PU`, `PT`, `NUM`, `NAME`, `LETTRE`, `DATE`, `TRANSACTION`, `NAME_TO`, `MONAIS`,`PROJET`,`NUM_FICHE`)  "+"values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
    pst=con.prepareStatement(sql);
    pst.setString(1,jEditorPane1.getText());
    pst.setString(3,"0.0");
    pst.setString(2,"0.0");
    pst.setString(4, pts.getText());
    pst.setString(5,rolls);
    pst.setString(6,sup1.getText());
    pst.setString(7,displays.getText());
         pst.setString(8,jDateChooser2.getText());
 
         pst.setString(9,"DEMANDE DE PAIEMENT");
    pst.setString (10,num_eb.getText());
    
     pst.setString(11,monais);
      pst.setString(12,chantier.getText());
       pst.setString(13,num_eb.getText());
      
   
    
    
    pst.executeUpdate();
     
    }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}
     // JOptionPane.showMessageDialog(null,"BUDGET TRANSIII");
 }
        JOptionPane.showMessageDialog(null,"Data Saved");
   // }
 }
               public void pay(){
    if(jRadioButton1.isSelected()) {
    try{
           
             String sql="SELECT  distinct(NUM),SUP,DATES FROM `etat_de_besoin_b` where Pay='Pay' and BUSS='"+journal1.buss.getText()+"'";
     //  String sql="SELECT ID, `NUM_FACTURE`, `DATE_FACTURE`, `DESCRIPTION`, `DEBIT_QTY`, `DEBIT_PU`, `DEBIT_PT`, `DEBIT_TVA`, `CREDIT_QTY`, `CREDIT_PU`, `CREDIT_PT`, `CREDIT_TVA`, `DATES`, `NUM` FROM facture_fournisseur where compte='"+jComboBox6.getSelectedItem()+"'";
       pst = con.prepareStatement(sql);
      rs= pst.executeQuery();
      
       
       jTable5.setModel(DbUtils.resultSetToTableModel(rs));
       mode=new DefaultTableModel();
       
       
        TableColumn col0=jTable5.getColumnModel().getColumn(0);
        TableColumn col1=jTable5.getColumnModel().getColumn(1);
        TableColumn col2=jTable5.getColumnModel().getColumn(2);
      //  TableColumn col3=jTable3.getColumnModel().getColumn(3);
       
       col0.setPreferredWidth(30);
       col1.setPreferredWidth(100);
        col2.setPreferredWidth(30);
      
       //col4.setPreferredWidth(100);
       
      
      
       
       
      // jTable1.setModel(mode);
       
     
    }catch(SQLException ex ){
     JOptionPane.showMessageDialog(null, ex);
}
    
    }else{
    
    try{
           
             String sql="SELECT  distinct(NUM),SUP,DATES FROM `etat_de_besoin` where Pay='Pay' and BUSS='"+journal1.buss.getText()+"'";
     //  String sql="SELECT ID, `NUM_FACTURE`, `DATE_FACTURE`, `DESCRIPTION`, `DEBIT_QTY`, `DEBIT_PU`, `DEBIT_PT`, `DEBIT_TVA`, `CREDIT_QTY`, `CREDIT_PU`, `CREDIT_PT`, `CREDIT_TVA`, `DATES`, `NUM` FROM facture_fournisseur where compte='"+jComboBox6.getSelectedItem()+"'";
       pst = con.prepareStatement(sql);
      rs= pst.executeQuery();
      
       
       jTable5.setModel(DbUtils.resultSetToTableModel(rs));
       mode=new DefaultTableModel();
       
       
        TableColumn col0=jTable5.getColumnModel().getColumn(0);
        TableColumn col1=jTable5.getColumnModel().getColumn(1);
        TableColumn col2=jTable5.getColumnModel().getColumn(2);
      //  TableColumn col3=jTable3.getColumnModel().getColumn(3);
       
       col0.setPreferredWidth(30);
       col1.setPreferredWidth(100);
        col2.setPreferredWidth(30);
      
       //col4.setPreferredWidth(100);
       
      
      
       
       
      // jTable1.setModel(mode);
       
     
    }catch(SQLException ex ){
     JOptionPane.showMessageDialog(null, ex);
}
    }
           
     
     }
               
  public void refresh_pay(){
     
      try{
           
             String sql="SELECT  `DET`, `QTY`, `PU`, `PT` FROM `etat_de_besoin` where NUM='xxxxx' AND print='Print'";
     //  String sql="SELECT ID, `NUM_FACTURE`, `DATE_FACTURE`, `DESCRIPTION`, `DEBIT_QTY`, `DEBIT_PU`, `DEBIT_PT`, `DEBIT_TVA`, `CREDIT_QTY`, `CREDIT_PU`, `CREDIT_PT`, `CREDIT_TVA`, `DATES`, `NUM` FROM facture_fournisseur where compte='"+jComboBox6.getSelectedItem()+"'";
       pst = con.prepareStatement(sql);
      rs= pst.executeQuery();
      
       
       jTable4.setModel(DbUtils.resultSetToTableModel(rs));
       mode=new DefaultTableModel();
       
       
        TableColumn col0=jTable4.getColumnModel().getColumn(0);
        TableColumn col1=jTable4.getColumnModel().getColumn(1);
        TableColumn col2=jTable4.getColumnModel().getColumn(2);
        TableColumn col3=jTable4.getColumnModel().getColumn(3);
       
       col0.setPreferredWidth(200);
       col1.setPreferredWidth(30);
        col2.setPreferredWidth(30);
       col3.setPreferredWidth(60);
      
       //col4.setPreferredWidth(100);
       
      
      
       
       
      // jTable1.setModel(mode);
       
     
    }catch(SQLException ex ){
     JOptionPane.showMessageDialog(null, ex);
}
    
                  sup.setText("");
                  
                
                  chantier.setText("");
                  
                 
                  numsss.setText("");
                  
                
//                  jDateChooser1.setText("");
                  
                num.setText("");
             
             sum.setText("");
                  
                  // solde_available.setText(x.format(c));
              
            
            
        
     }
  
   public void selectontable(){
        int indexs[]=jTable1.getSelectedRows();
       
        for(int i=0; i < indexs.length;i++){
          String Table_click = (jTable1.getModel().getValueAt(indexs[i],0). toString());
           try{
    String appr ="LOGISTIQUE";
 String sql = "UPDATE etat_de_besoin SET ORIENTATION=? WHERE ID ='"+Table_click+"'";
        
         pst = con.prepareStatement(sql);
        //  pst.setString(2,recherche.getText());
       //  pst.setString(2,homme.user.getText());
          pst.setString(1,appr);
                     
         pst.executeUpdate();


}catch(Exception ex){JOptionPane.showMessageDialog(null,ex);}
         
   }
                }
   
   
   
   ///>>>>>>>>>>>EB CONNECTOR
   
     public void Groupe1(){
ButtonGroup bg1 = new ButtonGroup();
bg1.add(upload);
bg1.add(dowload);
     }
     public void load(){ 
   // SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
    // String add = dateFormats.format (jDateChooser1.setDate(new Date()));
     try{
       
          String sql="SELECT  NUM as 'Numero' FROM `etat_de_besoin` WHERE BUSS='"+journal1.buss.getText()+"' and ORIENTATION='Finance' and print='' and EXCECUTE is not null and TRIM(EXCECUTE)<>'' and APPROUVATION =''and upload='No'  group by num";
          pst = con.prepareStatement(sql);
        rs= pst.executeQuery();
        jTable_ebcon1.setModel(DbUtils.resultSetToTableModel(rs));
        
       
        
      }catch(SQLException ex ){
      JOptionPane.showMessageDialog(null, ex);
} 
      try{
            String sql="select * from  id_token";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            if(rs.next()){
              orgs.setText(rs.getString("ORGANIZATION"));
           
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
}
     public void loadonline(){ 
          cononline=JavaDbConnectonline.dbConnect();
   // SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
    // String add = dateFormats.format (jDateChooser1.setDate(new Date()));
     try{
       
          String sql="SELECT  NUM as 'Numero' FROM `etat_de_besoin` WHERE  APPROUVATION <>'' and dowload='No' and organisation='"+orgs.getText()+"' group by num";
          pst = cononline.prepareStatement(sql);
        rs= pst.executeQuery();
         jTable_ebcon1.setModel(DbUtils.resultSetToTableModel(rs));
        
       
        
      }catch(SQLException ex ){
      JOptionPane.showMessageDialog(null, ex);
}    
}
public void saveebcon(){
           cononline=JavaDbConnectonline.dbConnect();
        int row= jTable_ebcon1.getSelectedRow();
          String Table_clicks = (jTable_ebcon1.getModel().getValueAt(row,0). toString());
        
        int indexs[]=jTableebcon2.getSelectedRows();
       
        for(int i=0; i < indexs.length;i++){
      String NUM_ID = (jTableebcon2.getModel().getValueAt(indexs[i],0). toString());
      String SUP = (jTableebcon2.getModel().getValueAt(indexs[i],1). toString());
      String DET = (jTableebcon2.getModel().getValueAt(indexs[i],2). toString());
      String QTY = (jTableebcon2.getModel().getValueAt(indexs[i],3). toString());
      String PU = (jTableebcon2.getModel().getValueAt(indexs[i],5). toString());
      String PT = (jTableebcon2.getModel().getValueAt(indexs[i],6). toString());
     // String DATES = (jTable1.getModel().getValueAt(indexs[i],0). toString());
      String DEVICE = (jTableebcon2.getModel().getValueAt(indexs[i],7). toString());
      String UNITE = (jTableebcon2.getModel().getValueAt(indexs[i],4). toString());
     try {
           // etroll();
        PreparedStatement pst = cononline.prepareStatement("INSERT INTO etat_de_besoin(`SUP`, `NUM`, `DET`, `QTY`, `PU`, `PT`, `DATES`, `APPROUVATION`, `DEVICE`, `UNITE`, `NUM_ID`,ORGANISATION) "
        +"value(?,?,?,?,?,?,?,?,?,?,?,?)");
        
        pst.setString(1, SUP);
         pst.setString(2, Table_clicks);
        pst.setString(3, DET);
         pst.setString(4, QTY);
         pst.setString(5, PU);
         pst.setString(6, PT);
         pst.setString(7, jLabel1.getText());
         pst.setString(8, "");
         // pst.setString(9,BUSS);
          pst.setString(9,DEVICE);
          pst.setString(10,UNITE);
           pst.setString(11,NUM_ID);
            pst.setString(12,orgs.getText());
          
          pst.executeUpdate();
        
              //   JOptionPane.showMessageDialog(null,"CLERCK_IN  SUCCESSFUL BONNE JOURNEE!!!        ");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    } 
         
         
        
   
    
         }
        
        JOptionPane.showMessageDialog(null,"Transaction Saved");
          try{
 String sql = "UPDATE etat_de_besoin SET upload='Yes' WHERE NUM ='"+Table_clicks+"'";
        
         pst = con.prepareStatement(sql);
       pst.executeUpdate();


}catch(Exception ex){JOptionPane.showMessageDialog(null,ex);}  
        jTableebcon2.setModel(new DefaultTableModel());
        notification();
       }
public void savedowload(){
     cononline=JavaDbConnectonline.dbConnect();
  int row= jTable_ebcon1.getSelectedRow();
          String Table_clicks = (jTable_ebcon1.getModel().getValueAt(row,0). toString());
        
        int indexs[]=jTableebcon2.getSelectedRows();
       
        for(int i=0; i < indexs.length;i++){
      String NUM_ID = (jTableebcon2.getModel().getValueAt(indexs[i],0). toString());
//      String SUP = (jTable1.getModel().getValueAt(indexs[i],1). toString());
     String DET = (jTableebcon2.getModel().getValueAt(indexs[i],2). toString());
      String QTY = (jTableebcon2.getModel().getValueAt(indexs[i],3). toString());
      String PU = (jTableebcon2.getModel().getValueAt(indexs[i],5). toString());
      String PT = (jTableebcon2.getModel().getValueAt(indexs[i],6). toString());

      String  ReplaceString = DET.replace("'", "''");
         try{
 String sql = "UPDATE etat_de_besoin SET DET='"+ReplaceString+"',QTY='"+QTY+"',PU='"+PU+"',PT='"+PT+"',APPROUVATION='Approuved' WHERE  NUM_ID='"+NUM_ID+"' AND NUM ='"+Table_clicks+"'";
        
         pst = con.prepareStatement(sql);
       pst.executeUpdate();


}catch(Exception ex){JOptionPane.showMessageDialog(null,ex);} 
     
    
         }
         try{
 String sql = "UPDATE etat_de_besoin SET dowload='Yes' WHERE NUM ='"+Table_clicks+"'";
        
         pst = cononline.prepareStatement(sql);
       pst.executeUpdate();


}catch(Exception ex){JOptionPane.showMessageDialog(null,ex);}  
        
        JOptionPane.showMessageDialog(null,"Transaction Saved"); 
        loadonline();
         jTableebcon2.setModel(new DefaultTableModel());
    
    
 
}
public void showEBDataON(){ 
   cononline=JavaDbConnectonline.dbConnect();
    int row= jTable_ebcon1.getSelectedRow();
          String Table_clicks = (jTable_ebcon1.getModel().getValueAt(row,0). toString());
     try{
       
          String sql="SELECT  NUM_ID as 'No.',SUP AS 'Demmandeur',DET as 'Indices',QTY,UNITE,PU,PT,DEVICE FROM `etat_de_besoin` WHERE NUM='"+Table_clicks+"' AND  APPROUVATION <>''  and organisation='"+orgs.getText()+"'";//group by num";
          pst = cononline.prepareStatement(sql);
        rs= pst.executeQuery();
        jTableebcon2.setModel(DbUtils.resultSetToTableModel(rs));
        
          TableColumn col0=jTableebcon2.getColumnModel().getColumn(0);
        TableColumn col1=jTableebcon2.getColumnModel().getColumn(1);
        TableColumn col2=jTableebcon2.getColumnModel().getColumn(2);
        TableColumn col3=jTableebcon2.getColumnModel().getColumn(3);
        TableColumn col4=jTableebcon2.getColumnModel().getColumn(4);
         TableColumn col5=jTableebcon2.getColumnModel().getColumn(5);
         TableColumn col6=jTableebcon2.getColumnModel().getColumn(6);
         TableColumn col7=jTableebcon2.getColumnModel().getColumn(7);
       col0.setPreferredWidth(1);
       col1.setPreferredWidth(80);
       col2.setPreferredWidth(300);
       col3.setPreferredWidth(50);
       col4.setPreferredWidth(50);
        col5.setPreferredWidth(50);
         col6.setPreferredWidth(50);
          col7.setPreferredWidth(50);
       
        
      }catch(SQLException ex ){
      JOptionPane.showMessageDialog(null, ex);
} 
      try{
       
          String sql="SELECT  * FROM `etat_de_besoin` WHERE NUM='"+Table_clicks +"'";
          pst=cononline.prepareStatement(sql);
            rs=pst.executeQuery();
            if(rs.next()){
            String   SUP =rs.getString("DATES");
            jLabel1.setText(SUP);
            }
       
        
      }catch(SQLException ex ){
      JOptionPane.showMessageDialog(null, ex);
}    
}
public void showEBDataOFF(){ 
    int row= jTable_ebcon1.getSelectedRow();
          String Table_clicks = (jTable_ebcon1.getModel().getValueAt(row,0). toString());
     try{
       
          String sql="SELECT  NUM_ID as 'No.',SUP AS 'Demmandeur',DET as 'Indices',QTY,UNITE,PU,PT,DEVICE FROM `etat_de_besoin` WHERE NUM='"+Table_clicks+"' AND  ORIENTATION='Finance' and print='' and APPROUVATION =''  ";//group by num";
          pst = con.prepareStatement(sql);
        rs= pst.executeQuery();
        jTableebcon2.setModel(DbUtils.resultSetToTableModel(rs));
        
          TableColumn col0=jTableebcon2.getColumnModel().getColumn(0);
        TableColumn col1=jTableebcon2.getColumnModel().getColumn(1);
        TableColumn col2=jTableebcon2.getColumnModel().getColumn(2);
        TableColumn col3=jTableebcon2.getColumnModel().getColumn(3);
        TableColumn col4=jTableebcon2.getColumnModel().getColumn(4);
         TableColumn col5=jTableebcon2.getColumnModel().getColumn(5);
         TableColumn col6=jTableebcon2.getColumnModel().getColumn(6);
         TableColumn col7=jTableebcon2.getColumnModel().getColumn(7);
       col0.setPreferredWidth(1);
       col1.setPreferredWidth(80);
       col2.setPreferredWidth(300);
       col3.setPreferredWidth(50);
       col4.setPreferredWidth(50);
        col5.setPreferredWidth(50);
         col6.setPreferredWidth(50);
          col7.setPreferredWidth(50);
       
        
      }catch(SQLException ex ){
      JOptionPane.showMessageDialog(null, ex);
} 
      try{
       
          String sql="SELECT  * FROM `etat_de_besoin` WHERE NUM='"+Table_clicks +"'";
          pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            if(rs.next()){
            String   SUP =rs.getString("DATES");
            jLabel1.setText(SUP);
            }
       
        
      }catch(SQLException ex ){
      JOptionPane.showMessageDialog(null, ex);
}    
}
public void notification(){
      
BufferedReader reader;
String line;
StringBuffer responseContent = new StringBuffer(); 
        try{
//URL url = new URL("https://jsonplaceholder.typicode.com/albums");//200
URL url = new URL("https://fcm.googleapis.com/fcm/send");//405
    connection =(HttpURLConnection) url.openConnection();
     int row= jTable_ebcon1.getSelectedRow();
          String Table_clicks = (jTable_ebcon1.getModel().getValueAt(row,0). toString());
          String IDTAKEN = null,TOKEN=null;
           try{
            String sql="select ID_USER from ID_TOKEN WHERE ID=1";
	   pst = con.prepareStatement(sql);          rs=pst.executeQuery();
            if(rs.next()){
          IDTAKEN=rs.getString("ID_USER");  
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex); }
           try{
            String sql="select  token from  token WHERE ID='"+IDTAKEN+"'";
	   pst = cononline.prepareStatement(sql);          rs=pst.executeQuery();
            if(rs.next()){
         TOKEN=rs.getString("token");  
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex); }
String tokens=TOKEN;
//String tokens="ecfLcSTNQQyCHHRt94Lr3A:APA91bF72Vl-LKShx4yXFJ4qreR6vmKCc8jnP665BmbH_dURwCMadvlyI8ppvdoQz_5EqiHl5EmsNw8GfpWKuq-8rFl7voE68c96A5Zn1i6o7YXlboH5x6Yn9zgeD4l1z7AKsmBWOwN_";

String contt=Table_clicks;
//String titttle="Nouvel état de besoin";
    try {
        String jsonData = "{\r\n    \"to\": \""+tokens+"\",\r\n    \"data\" : {\r\n        \"title\" : \"EMS-Likoyo\",\r\n        \"content\" : \""+contt+"\"\r\n    }\r\n\r\n}";
        //request stup
        connection.setRequestMethod("POST");
        connection.setConnectTimeout(5000);
        connection.setDoOutput(true);
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Authorization", "key=AAAATZnF8kA:APA91bGvYNdaxqaG4F6FoqvzicEWNpu8njTh3lRw6DSTq1BgXwfUr4KpL4h5NQs6qjTv36PKa3WjMCF_6B510kU4wUXLwfd1SrmWxwtpEpKkQtW7Y9it1W7UaGGOB2iN9KQ3QeklNcSb");
        
        byte[] out = jsonData.getBytes(StandardCharsets.UTF_8);

        OutputStream stream = connection.getOutputStream();
        stream.write(out);
        
connection.setReadTimeout(5000); 
    } catch (ProtocolException ex) {
        Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, ex);
    }


int status=connection.getResponseCode();
System.out.println(status);
if(status >299){
reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
while((line = reader.readLine()) !=null){
responseContent.append(line);
}
reader.close();
}else{
reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
while((line = reader.readLine()) !=null){
responseContent.append(line);
}
reader.close();
}
System.out.println(responseContent.toString());
}catch(MalformedURLException e){
 e.printStackTrace();
}catch (IOException ex) {
            Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
connection.disconnect();
}    

}

public void uploadeb(){
//String no="",dem="",descr="",qty="",unite="",up="",pt="",device="";
       int indexs[]=jTableebcon2.getSelectedRows();
            for(int i=0; i < indexs.length;i++){
     String  no = (jTableebcon2.getModel().getValueAt(indexs[i],0). toString());
      String dem = (jTableebcon2.getModel().getValueAt(indexs[i],1). toString());
      String descr = (jTableebcon2.getModel().getValueAt(indexs[i],2). toString());
      String qty = (jTableebcon2.getModel().getValueAt(indexs[i],3). toString());
      String unite = (jTableebcon2.getModel().getValueAt(indexs[i],4). toString());
      String up = (jTableebcon2.getModel().getValueAt(indexs[i],5). toString());
     String  pt = (jTableebcon2.getModel().getValueAt(indexs[i],6). toString());
    String   device = (jTableebcon2.getModel().getValueAt(indexs[i],7). toString());
       

      
      api.ajouterEngin(no,dem,descr,qty,unite,up,pt,device).enqueue(new Callback<etat_de_besoin_engin>(){
       
     
          @Override
            public void onResponse(Call<etat_de_besoin_engin> call, Response<etat_de_besoin_engin> response) {
                etat_de_besoin_engin engin = response.body();
                model.insertRow(
                            model.getRowCount(), 
                            new Object[]{engin.getNo(), engin.getDemandeur(), engin.getDescription(),engin.getQty(),engin.getUnite(),engin.getUp(),engin.getPt(),engin.getDevice()}
                    );
                
            }

            @Override
            public void onFailure(Call<etat_de_besoin_engin> call, Throwable t) {
                System.out.println("Erreur : " + t);
            }
        });
      }
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

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        sum = new javax.swing.JTextField();
        devise = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        recherche = new javax.swing.JTextField();
        date3 = new com.alee.extended.date.WebDateField();
        date4 = new com.alee.extended.date.WebDateField();
        jPanel7 = new javax.swing.JPanel();
        sup = new javax.swing.JTextField();
        chat = new javax.swing.JTextField();
        jPanel16 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        jComboBox2 = new javax.swing.JComboBox<>();
        recherches1 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jButton9 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable22 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        num = new javax.swing.JTextField();
        sum1 = new javax.swing.JTextField();
        pay = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jRadioButton2 = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        sup1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        chantier = new javax.swing.JLabel();
        numsss = new javax.swing.JLabel();
        jDateChooser2 = new com.alee.extended.date.WebDateField();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        num_eb = new javax.swing.JLabel();
        num_bon = new javax.swing.JLabel();
        pts = new javax.swing.JLabel();
        usd = new javax.swing.JLabel();
        displays = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jEditorPane1 = new javax.swing.JEditorPane();
        jPanel12 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTable22 = new javax.swing.JTable()

        {
            @Override

            public Component prepareRenderer (TableCellRenderer renderer, int rowIndex, int columnIndex){
                Component componenet = super.prepareRenderer(renderer, rowIndex, columnIndex);

                Object value = getModel().getValueAt(rowIndex,columnIndex);

                if(columnIndex ==1){

                    if(value.equals("EB1001"))
                    {

                        componenet.setBackground(Color.GREEN);
                        componenet.setForeground(Color.red);

                    }

                }else {

                    componenet.setBackground(Color.WHITE);
                    componenet.setForeground(Color.BLACK);
                }

                return componenet;}
        }
        ;
        jTable5 = new javax.swing.JTable();
        display = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTable_ebcon1 = new javax.swing.JTable();
        jPanel15 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTableebcon2 = new javax.swing.JTable();
        jPanel17 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        orgs = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        online = new javax.swing.JRadioButton();
        jSeparator4 = new javax.swing.JSeparator();
        upload = new javax.swing.JRadioButton();
        jSeparator5 = new javax.swing.JSeparator();
        dowload = new javax.swing.JRadioButton();
        jSeparator6 = new javax.swing.JSeparator();
        jLabel11 = new javax.swing.JLabel();

        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jTable1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Num", "Description", "Quantite", "Prix Unitaire", "Prix Total", "Dates", "Approbation", "Action"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Float.class, java.lang.Float.class, java.lang.Float.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                true, true, true, true, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

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
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 303, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel3.setForeground(new java.awt.Color(153, 255, 153));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Montant/Search", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        sum.setEditable(false);
        sum.setBackground(new java.awt.Color(0, 0, 0));
        sum.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        sum.setForeground(new java.awt.Color(255, 255, 255));
        sum.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        devise.setEditable(false);
        devise.setBackground(new java.awt.Color(0, 0, 0));
        devise.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        devise.setForeground(new java.awt.Color(255, 255, 255));
        devise.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Search", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        recherche.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        recherche.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        recherche.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                rechercheKeyReleased(evt);
            }
        });

        date3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        date4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(date3, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(date4, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE))
            .addComponent(recherche)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(recherche, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(date3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(date4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(sum)
                        .addGap(0, 0, 0)
                        .addComponent(devise, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(sum)
                    .addComponent(devise, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "StateOf Need Infos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        sup.setBackground(new java.awt.Color(204, 204, 255));
        sup.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        roll.setEditable(false);
        roll.setBackground(new java.awt.Color(204, 204, 255));
        roll.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        roll.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                rollKeyTyped(evt);
            }
        });

        chat.setBackground(new java.awt.Color(204, 204, 255));
        chat.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        jRadioButton1.setBackground(new java.awt.Color(255, 255, 255));
        jRadioButton1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jRadioButton1.setText("Back Up EB");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        jRadioButton3.setBackground(new java.awt.Color(255, 255, 255));
        jRadioButton3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jRadioButton3.setText("Online Checking");
        jRadioButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sup, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
            .addComponent(chat)
            .addComponent(roll)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jRadioButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jRadioButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton1)
                    .addComponent(jRadioButton3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sup, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(roll, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));
        jPanel16.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Command", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jButton3.setBackground(new java.awt.Color(204, 204, 255));
        jButton3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton3.setText("Print(Ctrl+P)");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(51, 255, 51));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton1.setText("Print DMD ACHAT");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(255, 51, 51));
        jButton2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton2.setText("Remove");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton4.setText("Yes");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Command", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jComboBox2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Send For Approuval", "Send To Log" }));
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

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jComboBox2, javax.swing.GroupLayout.Alignment.TRAILING, 0, 0, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        recherches1.setEditable(false);
        recherches1.setBackground(new java.awt.Color(204, 204, 255));
        recherches1.setForeground(new java.awt.Color(240, 240, 240));
        recherches1.setBorder(null);
        recherches1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                recherches1ActionPerformed(evt);
            }
        });
        recherches1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                recherches1KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                recherches1KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                recherches1KeyTyped(evt);
            }
        });

        jLabel6.setText("Approuve:");

        jLabel8.setText("Ecriture:");

        appr.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        appr.setText("...........");

        ecri.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ecri.setText("...........");

        jButton9.setText("Approbation");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(recherches1, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(appr)
                                    .addComponent(ecri))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(54, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(recherches1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(appr))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(ecri))
                        .addContainerGap())
                    .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jTable22.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jTable22.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable22.setRowHeight(30);
        jTable22.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable22MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable22);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 311, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Gestion des Etat de Besoins", jPanel5);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jTable4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable4.setRowHeight(22);
        jTable4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable4MouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(jTable4);

        jButton5.setText("Pay");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("Delete");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        num.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        num.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                numKeyReleased(evt);
            }
        });

        sum1.setEditable(false);
        sum1.setBackground(new java.awt.Color(0, 0, 0));
        sum1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        sum1.setForeground(new java.awt.Color(255, 255, 255));

        pay.setEditable(false);
        pay.setBackground(new java.awt.Color(204, 204, 204));
        pay.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jRadioButton2.setBackground(new java.awt.Color(255, 255, 255));
        jRadioButton2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jRadioButton2.setText("Back UP");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("Personne à payer :");

        sup1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        sup1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("Allocation:");

        chantier.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        chantier.setText("...........");

        numsss.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        numsss.setText("Date de transaction:");

        jDateChooser2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jDateChooser2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jDateChooser2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jDateChooser2ActionPerformed(evt);
            }
        });

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel3.setText("Numéro d’etat de besoin :");

        jLabel4.setText("Numéro de transaction :");

        num_eb.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        num_eb.setText("...........");

        num_bon.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        num_bon.setText("..........");

        pts.setForeground(new java.awt.Color(255, 255, 255));
        pts.setText("Devise:");

        usd.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        usd.setText("..........");

        displays.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        displays.setText("..........");

        jLabel5.setText("Devise:");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jRadioButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(chantier, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sup1, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(numsss)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(usd)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(pts))
                            .addComponent(num_bon, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
                            .addComponent(num_eb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(displays))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jRadioButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sup1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(chantier, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(numsss, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
                            .addComponent(num_eb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                            .addComponent(num_bon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(pts, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(usd))
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(displays)))
                .addContainerGap())
        );

        jScrollPane3.setViewportView(jEditorPane1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(pay, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(sum1, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(num, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 124, Short.MAX_VALUE))
                    .addComponent(jScrollPane4))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(num, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5)
                    .addComponent(sum1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6)))
        );

        jTabbedPane1.addTab("Demande de Payement", jPanel1);

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));
        jPanel13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jTable5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTable5.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable5.setRowHeight(22);
        jTable5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable5MouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(jTable5);

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 505, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        display.setBackground(new java.awt.Color(255, 255, 255));
        display.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        display.setLayout(new javax.swing.BoxLayout(display, javax.swing.BoxLayout.LINE_AXIS));

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/journals/icons8_Print_30px.png"))); // NOI18N
        jLabel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jLabel7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(114, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(display, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(display, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Demande de payement 2", jPanel12);

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));
        jPanel14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jTable_ebcon1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jTable_ebcon1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable_ebcon1.setRowHeight(26);
        jTable_ebcon1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_ebcon1MouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(jTable_ebcon1);

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 382, Short.MAX_VALUE)
        );

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));
        jPanel15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jTableebcon2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jTableebcon2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTableebcon2.setRowHeight(26);
        jTableebcon2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableebcon2MouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(jTableebcon2);

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5)
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        jPanel17.setBackground(new java.awt.Color(255, 255, 255));
        jPanel17.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("Message");

        orgs.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        orgs.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        orgs.setText("Organization");

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);

        online.setBackground(new java.awt.Color(255, 255, 255));
        online.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        online.setText("Online Datas");
        online.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                onlineMouseClicked(evt);
            }
        });

        jSeparator4.setOrientation(javax.swing.SwingConstants.VERTICAL);

        upload.setBackground(new java.awt.Color(255, 255, 255));
        upload.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        upload.setText("Upload Datas");

        jSeparator5.setOrientation(javax.swing.SwingConstants.VERTICAL);

        dowload.setBackground(new java.awt.Color(255, 255, 255));
        dowload.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        dowload.setText("Download Datas");

        jSeparator6.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/journals/icons8_Save_as_24px.png"))); // NOI18N
        jLabel11.setText("Save Action");
        jLabel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel11MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(orgs, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(online, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(upload, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dowload)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(online, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
            .addComponent(orgs, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jSeparator4, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(upload, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jSeparator5)
            .addComponent(dowload, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jSeparator6)
            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("EB. Connector", jPanel11);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void rollKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_rollKeyTyped
        //search();         // TODO add your handling code here:
    }//GEN-LAST:event_rollKeyTyped

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
try{
 con=JavaDbConnect.dbConnect(); 
    
        if(jRadioButton1.isSelected()){
    try{

            String sql="SELECT `ID` AS NUM,`DET`, `QTY`, `PU`, `PT`, `DATES`, `APPROUVATION` FROM `etat_de_besoin_b` WHERE  `ORIENTATION`='FINANCE' AND ORIENTATION2='Fin' and buss='"+journal1.buss.getText()+"'";   pst = con.prepareStatement(sql);
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
            
            col0.setPreferredWidth(0);
            col1.setPreferredWidth(250);
            col2.setPreferredWidth(30);
            col3.setPreferredWidth(50);
            col4.setPreferredWidth(50);
            col5.setPreferredWidth(80);
            col6.setPreferredWidth(100);

        }catch(SQLException ex ){
            JOptionPane.showMessageDialog(null, ex);
        }
        
         try{
       
         String sql="SELECT distinct NUM FROM etat_de_besoin_b WHERE ORIENTATION='FINANCE' and ORIENTATION2='Fin' and PRINT='' and buss='"+journal1.buss.getText()+"' order by num";
          pst = con.prepareStatement(sql);
        rs= pst.executeQuery();
        jTable22.setModel(DbUtils.resultSetToTableModel(rs));
        
      }catch(SQLException ex ){
      JOptionPane.showMessageDialog(null, ex);
}    
  try{
           
             String sql="SELECT  distinct(NUM),SUP,DATES FROM `etat_de_besoin_b` where Pay='Pay' and BUSS='"+journal1.buss.getText()+"' order by num";
     //  String sql="SELECT ID, `NUM_FACTURE`, `DATE_FACTURE`, `DESCRIPTION`, `DEBIT_QTY`, `DEBIT_PU`, `DEBIT_PT`, `DEBIT_TVA`, `CREDIT_QTY`, `CREDIT_PU`, `CREDIT_PT`, `CREDIT_TVA`, `DATES`, `NUM` FROM facture_fournisseur where compte='"+jComboBox6.getSelectedItem()+"'";
       pst = con.prepareStatement(sql);
      rs= pst.executeQuery();
      
       
       jTable5.setModel(DbUtils.resultSetToTableModel(rs));
       mode=new DefaultTableModel();
       
       
        TableColumn col0=jTable5.getColumnModel().getColumn(0);
        TableColumn col1=jTable5.getColumnModel().getColumn(1);
        TableColumn col2=jTable5.getColumnModel().getColumn(2);
      //  TableColumn col3=jTable3.getColumnModel().getColumn(3);
       
       col0.setPreferredWidth(100);
       col1.setPreferredWidth(150);
        col2.setPreferredWidth(30);
      
       //col4.setPreferredWidth(100);
       
      
      
       
       
      // jTable1.setModel(mode);
       
     
    }catch(SQLException ex ){
     JOptionPane.showMessageDialog(null, ex);
}
}else{
    try{

            String sql="SELECT `ID` AS NUM,`DET`, `QTY`, `PU`, `PT`, `DATES`, `APPROUVATION` FROM `etat_de_besoin` WHERE  `ORIENTATION`='FINANCE' AND ORIENTATION2='Fin' and buss='"+journal1.buss.getText()+"'";   pst = con.prepareStatement(sql);
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
            
            col0.setPreferredWidth(0);
            col1.setPreferredWidth(250);
            col2.setPreferredWidth(30);
            col3.setPreferredWidth(50);
            col4.setPreferredWidth(50);
            col5.setPreferredWidth(80);
            col6.setPreferredWidth(100);

        }catch(SQLException ex ){
            JOptionPane.showMessageDialog(null, ex);
        }
        
         try{
       
         String sql="SELECT distinct NUM FROM etat_de_besoin WHERE ORIENTATION='FINANCE' and ORIENTATION2='Fin' and PRINT='' and buss='"+journal1.buss.getText()+"' order by num";
          pst = con.prepareStatement(sql);
        rs= pst.executeQuery();
        jTable22.setModel(DbUtils.resultSetToTableModel(rs));
        
      }catch(SQLException ex ){
      JOptionPane.showMessageDialog(null, ex);
}    
  try{
           
             String sql="SELECT  distinct(NUM),SUP,DATES FROM `etat_de_besoin` where Pay='Pay' and BUSS='"+journal1.buss.getText()+"' order by num";
     //  String sql="SELECT ID, `NUM_FACTURE`, `DATE_FACTURE`, `DESCRIPTION`, `DEBIT_QTY`, `DEBIT_PU`, `DEBIT_PT`, `DEBIT_TVA`, `CREDIT_QTY`, `CREDIT_PU`, `CREDIT_PT`, `CREDIT_TVA`, `DATES`, `NUM` FROM facture_fournisseur where compte='"+jComboBox6.getSelectedItem()+"'";
       pst = con.prepareStatement(sql);
      rs= pst.executeQuery();
      
       
       jTable5.setModel(DbUtils.resultSetToTableModel(rs));
       mode=new DefaultTableModel();
       
       
        TableColumn col0=jTable5.getColumnModel().getColumn(0);
        TableColumn col1=jTable5.getColumnModel().getColumn(1);
        TableColumn col2=jTable5.getColumnModel().getColumn(2);
      //  TableColumn col3=jTable3.getColumnModel().getColumn(3);
       
       col0.setPreferredWidth(100);
       col1.setPreferredWidth(150);
        col2.setPreferredWidth(30);
      
       //col4.setPreferredWidth(100);
       
      
      
       
       
      // jTable1.setModel(mode);
       
     
    }catch(SQLException ex ){
     JOptionPane.showMessageDialog(null, ex);
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
}        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
if(ecri.getText().equals("...........") ||ecri.getText().equals("No") || appr.getText().equals("...........")|| appr.getText().equals("No")){
 JOptionPane.showMessageDialog(null,"EB Non approuvé ou l'écriture comptable n'est pas passée");
}else{
    try{
 con=JavaDbConnect.dbConnect(); 
     if(APPROV.isEmpty()){
            JOptionPane.showMessageDialog(null,"YOU ARE NOT AUTHORISED TO PRINT A EB WHICH IS NOT YET APPOUVED !!!");
        }
        else if(recherche.getText().isEmpty()){
            JOptionPane.showMessageDialog(null,"PLAISE SELECT ONE DOCUMENT FOR PRINTING");
        }
            else{
            if(jRadioButton1.isSelected()){
                 try{
                    String appr ="Print";
                    String sql = "UPDATE etat_de_besoin_b SET PRINT=? WHERE NUM =?";

                    pst = con.prepareStatement(sql);
                    pst.setString(2,recherche.getText());
                    pst.setString(1,appr);

                    pst.executeUpdate();

                }catch(Exception ex){JOptionPane.showMessageDialog(null,ex);}
                showEBData();
                report_EB();
            
            }else{
                 try{
                    String appr ="Print";
                    String sql = "UPDATE etat_de_besoin SET PRINT=? WHERE NUM =?";

                    pst = con.prepareStatement(sql);
                    pst.setString(2,recherche.getText());
                    pst.setString(1,appr);

                    pst.executeUpdate();

                }catch(Exception ex){JOptionPane.showMessageDialog(null,ex);}
                showEBData();
                report_EB();
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
    }

   

        // m. setDefaultCloseOperation(DISPOSE_ON_CLOSE);//          // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
if(ecri.getText().equals("...........") || ecri.getText().equals("No") ||appr.getText().equals("...........")||appr.getText().equals("No")){
 JOptionPane.showMessageDialog(null,"EB Non approuvé ou l'écriture comptable n'est pas passée");
}else{
 try{
 con=JavaDbConnect.dbConnect(); 
if(jRadioButton1.isSelected()){
 reportDMD_B();
}else{
 reportDMD();
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
}    
    //roll.setText(null);// TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
 try{
            String sql="select ACESS from user_write where NAME='"+Home_page.home.user.getText()+"' and CHAMP='Save Budget'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
         if(rs.next()){
          String  caisse= rs.getString("ACESS");
          if(caisse.equals("Yes")){
       try{
 con=JavaDbConnect.dbConnect(); 
 tryconfirm();
        searchS();
        call_sum();
          
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
       JOptionPane.showMessageDialog(null,"Seul le financier(e) est autorisé(e)","Avertissement",JOptionPane.WARNING_MESSAGE);
          }
            }
            }catch(Exception ex){
          
          JOptionPane.showMessageDialog(null, ex); }//save();
 


// TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
  
  
try{
 con=JavaDbConnect.dbConnect(); 
   if(jRadioButton1.isSelected()){
        select_jTable(); //after
       call_sumb();
       }else{
       select_jTable();
        call_sum();
       }  
  // int row= jTable1.getSelectedRow();
          //String Table_click = (jTable1.getModel().getValueAt(row,0). toString());
          journal1.libele.setText("Direct wording from Etat de besoin/Req");
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
    }//GEN-LAST:event_jTable1MouseClicked

    private void rechercheKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_rechercheKeyReleased
    
  try{
 con=JavaDbConnect.dbConnect(); 
  searchS();
   call_sum();
          
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
    }//GEN-LAST:event_rechercheKeyReleased

    private void recherches1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_recherches1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_recherches1ActionPerformed

    private void recherches1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_recherches1KeyPressed
        //search();         // TODO add your handling code here:
    }//GEN-LAST:event_recherches1KeyPressed

    private void recherches1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_recherches1KeyReleased
        // search();     // TODO add your handling code here:
    }//GEN-LAST:event_recherches1KeyReleased

    private void recherches1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_recherches1KeyTyped
        //        search();         // TODO add your handling code here:
    }//GEN-LAST:event_recherches1KeyTyped

    private void jDateChooser2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jDateChooser2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jDateChooser2ActionPerformed

    private void jTable4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable4MouseClicked
check="ok";              // TODO add your handling code here:
    }//GEN-LAST:event_jTable4MouseClicked

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed


try{
 con=JavaDbConnect.dbConnect(); 
if(check==null||jEditorPane1.getText().equals("") || jEditorPane1.getText().equals("Motif")){
    JOptionPane.showMessageDialog(null,"Wrong Data","Select Table or Motif",JOptionPane.ERROR_MESSAGE);
}else{
    if(jRadioButton1.isSelected()){
    try{

    String appr ="Pay";
 String sql = "UPDATE etat_de_besoin_b SET Pay=? WHERE NUM =?";
        
         pst = con.prepareStatement(sql);
         pst.setString(2,num_eb.getText());
          pst.setString(1,appr);
                     
         pst.executeUpdate();


}catch(Exception ex){JOptionPane.showMessageDialog(null,ex);} 
    }else{
try{

    String appr ="Pay";
 String sql = "UPDATE etat_de_besoin SET Pay=? WHERE NUM =?";
        
         pst = con.prepareStatement(sql);
         pst.setString(2,num_eb.getText());
          pst.setString(1,appr);
                     
         pst.executeUpdate();


}catch(Exception ex){JOptionPane.showMessageDialog(null,ex);} 
}
    
 save();
 pay();
refresh_pay();
}
check=null; 
jEditorPane1.setText("");
sum1.setText("");
displays.setText("..........");
num_eb.setText("..........");
chantier.setText("..........");
sup1.setText("");
pay.setText("");

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
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
 
   
   
 try{
 con=JavaDbConnect.dbConnect(); 
   try{
        String sql = "DELETE FROM recu WHERE NUM_fiche=?";
        
         pst = con.prepareStatement(sql);
         pst.setString(1,num_eb.getText());
         pst.executeUpdate();

     }catch(SQLException | HeadlessException ex ){
         JOptionPane.showMessageDialog(null,ex);
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
   
    }//GEN-LAST:event_jButton6ActionPerformed

    private void numKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_numKeyReleased
 
try{
 con=JavaDbConnect.dbConnect(); 
  call_in_table3();
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
    }//GEN-LAST:event_numKeyReleased

    private void jTable5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable5MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable5MouseClicked

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
 

 try{
 con=JavaDbConnect.dbConnect(); 
report(); 

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
}   // TODO add your handling code here:
    }//GEN-LAST:event_jLabel7MouseClicked

    private void jTableebcon2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableebcon2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTableebcon2MouseClicked

    private void jTable_ebcon1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_ebcon1MouseClicked
if(online.isSelected()){
//showEBData();
showEBDataON();
}else{
showEBDataOFF();
}     // TODO add your handling code here:
    }//GEN-LAST:event_jTable_ebcon1MouseClicked

    private void onlineMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_onlineMouseClicked
if(online.isSelected()){
loadonline();
}else{
 load();
}          // TODO add your handling code here:
    }//GEN-LAST:event_onlineMouseClicked

    private void jLabel11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseClicked
if(upload.isSelected()){
uploadeb();
}else if(dowload.isSelected()){
savedowload();
loadonline();
}        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel11MouseClicked

    private void jRadioButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton3ActionPerformed

    private void jTable22MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable22MouseClicked
 try{
 con=JavaDbConnect.dbConnect(); 
 select_jTableNUM();
        searchS();
        call_sum();  
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
    }//GEN-LAST:event_jTable22MouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
 try{
      con=JavaDbConnect.dbConnect();
            String sql="select ACESS from user_write where NAME='"+Home_page.home.user.getText()+"' and CHAMP='Save Budget'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
         if(rs.next()){
          String  caisse= rs.getString("ACESS");
          if(caisse.equals("Yes")){
       try{
 con=JavaDbConnect.dbConnect(); 
 tryconfirms(); 
        searchS();
        call_sum();
          
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
       JOptionPane.showMessageDialog(null,"Seul le financier(e) est autorisé(e)","Avertissement",JOptionPane.WARNING_MESSAGE);
          }
            }
            }catch(Exception ex){
          
          JOptionPane.showMessageDialog(null, ex); }//save();
 
              // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jComboBox2PopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_jComboBox2PopupMenuWillBecomeInvisible

        try{
            con=JavaDbConnect.dbConnect();
            try{
                String sql="select ACESS from user_write where NAME='"+Home_page.home.user.getText()+"' and CHAMP='Send EB for Approval'";

                pst=con.prepareStatement(sql);
                rs=pst.executeQuery();
                if(rs.next()){
                    String  caisse= rs.getString("ACESS");
                    if(caisse.equals("Yes")){
                        if(jComboBox2.getSelectedItem().equals("Send For Approuval")){

                            int response = JOptionPane.showConfirmDialog(null, "DO YOU WANT TO SEND THIS FOR APPROUVAL?", "Confirm",
                                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                            if (response == JOptionPane.YES_OPTION) {
                                if(jRadioButton1.isSelected()){
                                    try{
                                        String appr =Home_page.home.user.getText();
                                        String sqls = "UPDATE etat_de_besoin_b SET EXCECUTE=?,ETUDE=? WHERE NUM =?";

                                        pst = con.prepareStatement(sqls);
                                        pst.setString(3,recherche.getText());
                                        pst.setString(1,appr);
                                        pst.setString(2,appr);

                                        pst.executeUpdate();

                                    }catch(Exception ex){JOptionPane.showMessageDialog(null,ex);}
                                }else{
                                    JOptionPane.showMessageDialog(null,"Here");
                                    try{
                                        String appr =Home_page.home.user.getText();
                                        String sqls = "UPDATE etat_de_besoin SET EXCECUTE=?,ETUDE=? WHERE NUM =?";

                                        pst = con.prepareStatement(sqls);
                                        pst.setString(3,recherche.getText());
                                        pst.setString(1,appr);
                                        pst.setString(2,appr);

                                        pst.executeUpdate();

                                    }catch(Exception ex){JOptionPane.showMessageDialog(null,ex);}
                                }

                                //searchS();
                            } else if (response == JOptionPane.NO_OPTION) {
                                searchS();
                            }
                        }else{
                            int response = JOptionPane.showConfirmDialog(null, "DO YOU WANT TO SEND THIS TO LOGISTIQUE?", "Confirm",
                                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                            if (response == JOptionPane.YES_OPTION) {
                                selectontable();
                                searchS();
                            }else if (response == JOptionPane.NO_OPTION) {
                                searchS();
                            }
                        }
                    }else{
                        JOptionPane.showMessageDialog(null,"Seul le financier(e) est autorisé(e)","Avertissement",JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
            catch(Exception ex){
                JOptionPane.showMessageDialog(null, ex); }
            load();
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
    }//GEN-LAST:event_jComboBox2PopupMenuWillBecomeInvisible

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed

        try{
            con=JavaDbConnect.dbConnect();
            try{
                String sql="select ACESS from user_write where NAME='"+Home_page.home.user.getText()+"' and CHAMP='Send EB for Approval'";

                pst=con.prepareStatement(sql);
                rs=pst.executeQuery();
                if(rs.next()){
                    String  caisse= rs.getString("ACESS");
                    if(caisse.equals("Yes")){
                    
                                if(jRadioButton1.isSelected()){
                                    try{
                                        String appr =Home_page.home.user.getText();
                                        String sqls = "UPDATE etat_de_besoin_b SET EXCECUTE=?,ETUDE=? WHERE NUM =?";

                                        pst = con.prepareStatement(sqls);
                                        pst.setString(3,recherche.getText());
                                        pst.setString(1,appr);
                                        pst.setString(2,appr);

                                        pst.executeUpdate();

                                    }catch(Exception ex){JOptionPane.showMessageDialog(null,ex);}
                                }else{
                                    JOptionPane.showMessageDialog(null,"Transaction done");
  /*                                
int rows=jTable1.getRowCount();

String ReplaceString;

  for(int row = 0; row<rows; row++)
  {   
  
    String libelle = (String)jTable1.getValueAt(row, 0);
    ReplaceString = libelle.replace("'", "''");
    String qty = (String)jTable1.getValueAt(row, 1);
  }*/
                                    try{
                                        String appr =Home_page.home.user.getText();
                                        String sqls = "UPDATE etat_de_besoin SET EXCECUTE=?,ETUDE=? WHERE NUM =?";

                                        pst = con.prepareStatement(sqls);
                                        pst.setString(3,recherche.getText());
                                        pst.setString(1,appr);
                                        pst.setString(2,appr);

                                        pst.executeUpdate();

                                    }catch(Exception ex){JOptionPane.showMessageDialog(null,ex);}
                                }

                                //searchS();
                         
                    
                    }else{
                        JOptionPane.showMessageDialog(null,"Seul le financier(e) est autorisé(e)","Avertissement",JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
            catch(Exception ex){
                JOptionPane.showMessageDialog(null, ex); }
            load();
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
    }//GEN-LAST:event_jButton9ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static final javax.swing.JLabel appr = new javax.swing.JLabel();
    private javax.swing.JLabel chantier;
    private javax.swing.JTextField chat;
    private com.alee.extended.date.WebDateField date3;
    private com.alee.extended.date.WebDateField date4;
    private javax.swing.JTextField devise;
    private javax.swing.JPanel display;
    private javax.swing.JLabel displays;
    private javax.swing.JRadioButton dowload;
    public static final javax.swing.JLabel ecri = new javax.swing.JLabel();
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    public static javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jComboBox2;
    private com.alee.extended.date.WebDateField jDateChooser2;
    private javax.swing.JEditorPane jEditorPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
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
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    public static final javax.swing.JRadioButton jRadioButton1 = new javax.swing.JRadioButton();
    private javax.swing.JRadioButton jRadioButton2;
    public static final javax.swing.JRadioButton jRadioButton3 = new javax.swing.JRadioButton();
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JTabbedPane jTabbedPane1;
    public static final javax.swing.JTable jTable1 = new javax.swing.JTable();
    public static javax.swing.JTable jTable22;
    private javax.swing.JTable jTable4;
    public static javax.swing.JTable jTable5;
    private javax.swing.JTable jTable_ebcon1;
    private javax.swing.JTable jTableebcon2;
    private javax.swing.JTextField num;
    private javax.swing.JLabel num_bon;
    private javax.swing.JLabel num_eb;
    private javax.swing.JLabel numsss;
    private javax.swing.JRadioButton online;
    private javax.swing.JLabel orgs;
    private javax.swing.JTextField pay;
    private javax.swing.JLabel pts;
    public static javax.swing.JTextField recherche;
    public static javax.swing.JTextField recherches1;
    public static final javax.swing.JTextField roll = new javax.swing.JTextField();
    private javax.swing.JTextField sum;
    private javax.swing.JTextField sum1;
    private javax.swing.JTextField sup;
    private javax.swing.JTextField sup1;
    private javax.swing.JRadioButton upload;
    private javax.swing.JLabel usd;
    // End of variables declaration//GEN-END:variables
}
