/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intreprisemanagementsystem;


import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.showInputDialog;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
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
 * @author Dosh
 */
public class NewJInternalFramedocloc extends javax.swing.JInternalFrame {

  DefaultTableModel mode;
Connection con=null;
PreparedStatement pst=null;
ResultSet rs= null;
String rolls;
    public NewJInternalFramedocloc() {
        initComponents();
              con=JavaDbConnect.dbConnect();
       /*  this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI bui= (BasicInternalFrameUI) this.getUI();
       bui.setNorthPane(null);*/
        currency();
        calltable();
       // jDateChooser1.setEnabled(false);
        destinateur.setEnabled(false);
        receveur.setEnabled(false);
        transport.setEnabled(false);
        No.setEnabled(false);
        
        
        
        recherche.setHorizontalAlignment( recherche.CENTER);
        
        qty.setHorizontalAlignment( qty.CENTER);
        pu.setHorizontalAlignment( pu.CENTER);
        pt.setHorizontalAlignment( pt.CENTER);
        recherche.setHorizontalAlignment( recherche.CENTER);
        // jComboBox1.addItem("BON D ENTREE STOCK");
       // jComboBox1.addItem("BON DE SORTIE STOCK");
        jComboBox1.addItem("BON DE LIVRAISON");
       // jComboBox1.addItem("BON DE COMMANDE");
        jComboBox1.addItem("BORDEREAUX D EXPEDITION");
      //   jComboBox1.addItem("BON D ACHAT");
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
        
        callnum();
        call();
    }
    
    public void callnum(){
    
            try{
         
         //  String tmp =(String)jComboBox1.getSelectedItem();
     String sql="select DISTINCT(NUM) from  locdoc ";
            pst=con.prepareStatement(sql);
           // pst.setString(1, tmp);
            rs=pst.executeQuery();
           while(rs.next()){

              String add9 = rs.getString("NUM");
              jComboBox2.addItem(add9);
             

          }
  
      }catch(Exception ex){
        JOptionPane.showMessageDialog(null,ex);  
      }; 
      try{
            String sql="select * from ohada where SUBSTR=40";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                String sum=rs.getString("NAME");
                  destinateur.addItem(sum);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
    
    }
   
     
 // AUTO ROLL NUMBER
     public void roll()
     {
         try{
             String sql="SELECT NUM FROM locdoc ORDER BY NUM DESC LIMIT 1";
             //String sql="SELECT NUM FROM etat_de_besoin ORDER BY NUM DESC LIMIT 1";
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
           
                 rolls="LG1001";
             }
                 
             
         }catch(Exception e){
             JOptionPane.showMessageDialog(null, e); 
             
         }
     }
     public void Refresh(){
     qty.setText(null);
     pu.setText("0");
     pt.setText(null);
     obs.setText(null);
     libelle.setText(null);
     
  
     }
      public void Refreshtous(){
     qty.setText(null);
     pu.setText("0");
     pt.setText(null);
     obs.setText(null);
     libelle.setText(null);
     
     num.setText(null);
//      destinateur.setText(null);
      transport.setText(null);
        receveur.setText(null);
         No.setText(null);
           jDateChooser1.setText(null);
             jComboBox1.setSelectedItem("SELECTIONNEZ UNE TRANSACTION");
     calltable();
     }
     
      //SAVE
     
      public void savemateriaux()
    {
         
    if (jComboBox1.getSelectedItem().equals("SELECTIONNEZ UNE TRANSACTION")){
        JOptionPane.showMessageDialog(null,"AUCUNE TRANSACTION N'A ETE SELECTIONNE");
   
    }
    else if (unity.getSelectedItem().equals("SELECTIONNEZ L'UNITE")){
        JOptionPane.showMessageDialog(null,"AUCUNE UNITE N'A ETE SELECTIONNE");
   
    }
     else if (destinateur.getSelectedItem().equals("")){
         
        JOptionPane.showMessageDialog(null,"COMPLETE LE CHAMP VIDE");
     }else{
          if(jComboBox3.getSelectedItem().equals("CDF")){
            calulationS();  
            calulation();
         
       
        
        String rollss = num.getText();
        if(rollss.isEmpty()){
        
        try {
      roll();
         
         //SET JOURS=?,NET_A_PAYER=JOURS*MONTAT  where ID=?";
        PreparedStatement pst = con.prepareStatement("INSERT INTO locdoc(LIBELLE,UNITE,QTY,PU,PT,NUM,OBS,TRANSACTION,DESTINATEUR,RECEVEUR,TRANSPORTEUR,No_PLAQUE,DATES)"
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?)");
        
       // pst.setString(1, roll);
         pst.setString(1, libelle.getText());
           String values=unity.getSelectedItem().toString();
         pst.setString(2, values);
         pst.setString(3, qty.getText());
         pst.setString(4, pu.getText());
         pst.setString(5, pt.getText());
         pst.setString(6, rolls);
          pst.setString(7, obs.getText());
          String value=jComboBox1.getSelectedItem().toString();
         pst.setString(8, value);
         
         pst.setString(9, destinateur.getSelectedItem().toString());
         pst.setString(10, receveur.getText());
         pst.setString(11, transport.getText());
          pst.setString(12, No.getText());
          
            SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(jDateChooser1.getDate());
         pst.setString(13, addDate);
        
         
         
       
         
         
          pst.executeUpdate();
        
                 
    } catch (HeadlessException | SQLException ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
    }else{
             try {
      //roll();
         
       PreparedStatement pst = con.prepareStatement("INSERT INTO locdoc(LIBELLE,UNITE,QTY,PU,PT,NUM,OBS,TRANSACTION,DESTINATEUR,RECEVEUR,TRANSPORTEUR,No_PLAQUE,DATES)"
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?)");
        
       // pst.setString(1, roll);
        pst.setString(1, libelle.getText());
           String values=unity.getSelectedItem().toString();
         pst.setString(2, values);
         pst.setString(3, qty.getText());
         pst.setString(4, pu.getText());
         pst.setString(5, pt.getText());
         pst.setString(6, rolls);
          pst.setString(7, obs.getText());
          String value=jComboBox1.getSelectedItem().toString();
         pst.setString(8, value);
         
         pst.setString(9, destinateur.getSelectedItem().toString());
         pst.setString(10, receveur.getText());
         pst.setString(11, transport.getText());
          pst.setString(12, No.getText());
          
            SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(jDateChooser1.getDate());
         pst.setString(13, addDate);
       
         
         
          pst.executeUpdate();
        
                // JOptionPane.showMessageDialog(null,"data saved");
    } catch (HeadlessException | SQLException ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    } 
        }
          Call_ID_MAX();
    } else if (jComboBox3.getSelectedItem().equals("USH")){
             calulationSUSH();   
            calulation();
         
       
        
        String rollss = num.getText();
        if(rollss.isEmpty()){
        
        try {
      roll();
         
         //SET JOURS=?,NET_A_PAYER=JOURS*MONTAT  where ID=?";
       PreparedStatement pst = con.prepareStatement("INSERT INTO locdoc(LIBELLE,UNITE,QTY,PU,PT,NUM,OBS,TRANSACTION,DESTINATEUR,RECEVEUR,TRANSPORTEUR,No_PLAQUE,DATES)"
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?)");
        
       // pst.setString(1, roll);
       pst.setString(1, libelle.getText());
           String values=unity.getSelectedItem().toString();
         pst.setString(2, values);
         pst.setString(3, qty.getText());
         pst.setString(4, pu.getText());
         pst.setString(5, pt.getText());
         pst.setString(6, rolls);
          pst.setString(7, obs.getText());
          String value=jComboBox1.getSelectedItem().toString();
         pst.setString(8, value);
         
         pst.setString(9, destinateur.getSelectedItem().toString());
         pst.setString(10, receveur.getText());
         pst.setString(11, transport.getText());
          pst.setString(12, No.getText());
          
            SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(jDateChooser1.getDate());
         pst.setString(13, addDate);
         
         
          pst.executeUpdate();
        
                 
    } catch (HeadlessException | SQLException ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
    }else{
             try {
      //roll();
         
       PreparedStatement pst = con.prepareStatement("INSERT INTO locdoc(LIBELLE,UNITE,QTY,PU,PT,NUM,OBS,TRANSACTION,DESTINATEUR,RECEVEUR,TRANSPORTEUR,No_PLAQUE,DATES)"
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?)");
        
       // pst.setString(1, roll);
           pst.setString(1, libelle.getText());
           String values=unity.getSelectedItem().toString();
         pst.setString(2, values);
         pst.setString(3, qty.getText());
         pst.setString(4, pu.getText());
         pst.setString(5, pt.getText());
         pst.setString(6, rolls);
          pst.setString(7, obs.getText());
          String value=jComboBox1.getSelectedItem().toString();
         pst.setString(8, value);
         
         pst.setString(9, destinateur.getSelectedItem().toString());
         pst.setString(10, receveur.getText());
         pst.setString(11, transport.getText());
          pst.setString(12, No.getText());
          
            SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(jDateChooser1.getDate());
         pst.setString(13, addDate);
       
         
         
          pst.executeUpdate();
        
                // JOptionPane.showMessageDialog(null,"data saved");
    } catch (HeadlessException | SQLException ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    } 
        }
          Call_ID_MAX();
    }else {
         
        float a = Float.parseFloat(qty.getText());
            float b = Float.parseFloat(pu.getText());
            float c;
            String d;
            c=a*b;
            d= String.format("%.2f",c);
            pt.setText(d);
        
        String rollss = num.getText();
        if(rollss.isEmpty()){
        
        try {
      roll();
         
         //SET JOURS=?,NET_A_PAYER=JOURS*MONTAT  where ID=?";
       PreparedStatement pst = con.prepareStatement("INSERT INTO locdoc(LIBELLE,UNITE,QTY,PU,PT,NUM,OBS,TRANSACTION,DESTINATEUR,RECEVEUR,TRANSPORTEUR,No_PLAQUE,DATES,LB)"
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        
       // pst.setString(1, roll);
          pst.setString(1, libelle.getText());
           String values=unity.getSelectedItem().toString();
         pst.setString(2, values);
         pst.setString(3, qty.getText());
         pst.setString(4, pu.getText());
         pst.setString(5, pt.getText());
         pst.setString(6, rolls);
          pst.setString(7, obs.getText());
          String value=jComboBox1.getSelectedItem().toString();
         pst.setString(8, value);
         
         pst.setString(9, destinateur.getSelectedItem().toString());
         pst.setString(10, receveur.getText());
         pst.setString(11, transport.getText());
          pst.setString(12, No.getText());
          
            SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(jDateChooser1.getDate());
         pst.setString(13, addDate);
          pst.setString(14,CODEB);
       
         
         
          pst.executeUpdate();
        
                 
    } catch (HeadlessException | SQLException ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
    }else{
             try {
      //roll();
         
       PreparedStatement pst = con.prepareStatement("INSERT INTO locdoc(LIBELLE,UNITE,QTY,PU,PT,NUM,OBS,TRANSACTION,DESTINATEUR,RECEVEUR,TRANSPORTEUR,No_PLAQUE,DATES,LB)"
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        
       // pst.setString(1, roll);
           pst.setString(1, libelle.getText());
           String values=unity.getSelectedItem().toString();
         pst.setString(2, values);
         pst.setString(3, qty.getText());
         pst.setString(4, pu.getText());
         pst.setString(5, pt.getText());
         pst.setString(6, rolls);
          pst.setString(7, obs.getText());
          String value=jComboBox1.getSelectedItem().toString();
         pst.setString(8, value);
         
         pst.setString(9, destinateur.getSelectedItem().toString());
         pst.setString(10, receveur.getText());
         pst.setString(11, transport.getText());
          pst.setString(12, No.getText());
          
            SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(jDateChooser1.getDate());
         pst.setString(13, addDate);
           pst.setString(14,CODEB);
         
         
          pst.executeUpdate();
        
                // JOptionPane.showMessageDialog(null,"data saved");
    } catch (HeadlessException | SQLException ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    } 
        }
          Call_ID_MAX();
    }
     }
    }
    
   // DELETE
    public void delete()
    {
         try{
        String sql = "DELETE FROM locdoc WHERE ID=?";
        
         pst = con.prepareStatement(sql);
         pst.setString(1,id.getText());
         pst.executeUpdate();
         //JOptionPane.showMessageDialog(null,"delete");
              
     }catch(SQLException | HeadlessException ex ){
         JOptionPane.showMessageDialog(null,ex);
     } 
    }
     
    
    //UPDATE
     public void update_materiaux()
             
    {
         
         try{
             
        String sql = "UPDATE locdoc LIBELLE=?,UNITE=?,QTY=?,PU=?,PT=?,OBS=?,TRANSACTION=? where ID=?";
        
         pst = con.prepareStatement(sql);
         //pst.setString(7,id.getText());
         pst.setString(1,libelle.getText());
         String values=unity.getSelectedItem().toString();
         pst.setString(3, values);
        pst.setString(3,qty.getText());
             pst.setString(4,pu.getText());
              pst.setString(5,pt.getText());
         pst.setString(5,obs.getText());
         String value=jComboBox1.getSelectedItem().toString();
         pst.setString(7, value);
          
                       
         pst.executeUpdate();
         JOptionPane.showMessageDialog(null,"updaded");    
     }catch(SQLException | HeadlessException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }
    } 
     
      public void Call_ID_MAX()
    {
         {
        try{
            String sql="select MAX(NUM) from locdoc ";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                String sum=rs.getString("MAX(NUM)");
                  num.setText(sum);
                 // recherches.setText(sum);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
    }
    }
      public void combobox1_from_materiel()
    {
        
        
     String tmp =(String)jComboBox1.getSelectedItem();
     String sql="select * from  locdoc where TRANSACTION=?";
     
        try{
         
          
            pst=con.prepareStatement(sql);
            pst.setString(1, tmp);
            rs=pst.executeQuery();
            if(rs.next()){

              String add9 = rs.getString("TRANSACTION");
              recherche1.setText(add9);
             

          }
  
      }catch(Exception ex){
        JOptionPane.showMessageDialog(null,ex);  
      }
        }    
        //SELECT IN JTABLE
        
         public void select_jTable()
   {
        try{
          int row= jTable1.getSelectedRow();
          String Table_click = (jTable1.getModel().getValueAt(row,0). toString());
          String sql = "SELECT * FROM locdoc WHERE ID= '"+Table_click+"'";
          pst = con.prepareStatement(sql);
          rs=pst.executeQuery();
          if(rs.next()){
              String add1 = rs.getString("ID");
              id.setText(add1);
              String add = rs.getString("NUM");
              roll.setText(add);
          }
         
    }catch(SQLException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }
   }
         
         public void select_jTable2()
                
   {
       if(jComboBox1.getSelectedItem().equals("BON DE COMMANDE")){
       try{
          int row= jTable2.getSelectedRow();
          String Table_click = (jTable2.getModel().getValueAt(row,0). toString());
          String sql = "SELECT * FROM materiaux_in WHERE NUM_ID= '"+Table_click+"'";
          pst = con.prepareStatement(sql);
          rs=pst.executeQuery();
          if(rs.next()){
             String addss = rs.getString("DESCR");
              libelle.setText(addss);
              String add = rs.getString("QTY");
              qty.setText(add);
              
               String add1 = rs.getString("UNITY");
              unity.setSelectedItem(add1);
              
              
          }
         
    }catch(SQLException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }
       }else{
        try{
          int row= jTable2.getSelectedRow();
          String Table_click = (jTable2.getModel().getValueAt(row,0). toString());
          String sql = "SELECT * FROM locdoc WHERE NUM_ID= '"+Table_click+"'";
          pst = con.prepareStatement(sql);
          rs=pst.executeQuery();
          if(rs.next()){
             
              String add = rs.getString("NUM");
              num.setText(add);
              
              // String add1 = rs.getString("DESTINATEUR");
              //destinateur.setText(add1);
              
               String add2 = rs.getString("TRANSPORTEUR");
              transport.setText(add2);
              
               String add3 = rs.getString("RECEVEUR");
              receveur.setText(add3);
              
               String add4 = rs.getString("No_PLAQUE");
             No.setText(add4);
              
               String add5 = rs.getString("DATES");
              jDateChooser1.setText(add5);
              
               String add6 = rs.getString("TRANSACTION");
              jComboBox1.setSelectedItem(add6);
          }
         
    }catch(SQLException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }
        calltable();
       }
   }
     
      //SEARCH AND ADDREADER
     
    
         
         
         
      public void remove(){
          while(jTable1.getRowCount()>0){
              mode.removeRow(0);
          }
      }
              
            
               public void search1()
             {
                 // String st=recherche1.getText().trim();
                 String st=jComboBox1.getSelectedItem().toString();
  //  remove();
   // ReadData("select * from locdoc where TRANSACTION like '"+st+"%'");
   
   
    try{
            String sql="select * from locdoc where TRANSACTION like '"+st+"%'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
               // String sum=rs.getString("sum(USD1)");
                    NewJInternalFramedocloc.jTable1.setModel(DbUtils.resultSetToTableModel(rs));
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }  
   
   
             }
               
                public void report()
     {
         
          String A =showInputDialog("ENTREZ LE MOT DE PASSE!!!");
          if(A.equals("1234")){
             /// 
             if(jComboBox1.getSelectedItem().equals("BON DE LIVRAISON")){
             
                if(recherche.getText().equals("")){
              String tmp =(String) jComboBox1.getSelectedItem();
     
             try{
                 
                 
                String sqls="Select path from pathn";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
            if(rs.next()){
             
                String sum=rs.getString("path");
                  
                 String NameFiles=sum;
            
            String NameFile=""+NameFiles+"Bon_de_livraison.jrxml";
                 
                 
                 
                JasperDesign jd=JRXmlLoader.load(NameFile);   
                 
                // String report ="C:\\Users\\Doshe\\Documents\\NetBeansProjects\\intreprisemanagementsystem\\src\\report\\newReportmateriau.jrxml";
               // JasperDesign jd=JRXmlLoader.load("C:\\Users\\Dosh\\Documents\\NetBeansProjects\\intreprisemanagementsystem\\src\\intreprisemanagementsystem\\logistic.jrxml");
                String sql="select * from locdoc  where transaction='"+tmp+"' and NUM='"+num.getText()+"'" ;
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
             
             }else     if(jComboBox1.getSelectedItem().equals("BORDEREAUX D EXPEDITION")){
             
                if(recherche.getText().equals("")){
              String tmp =(String) jComboBox1.getSelectedItem();
     
             try{
                 
                 
                String sqls="Select path from pathn";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
            if(rs.next()){
             
                String sum=rs.getString("path");
                  
                 String NameFiles=sum;
            
            String NameFile=""+NameFiles+"bordereaudexpedition.jrxml";
                 
                 
                 
                JasperDesign jd=JRXmlLoader.load(NameFile);   
                 
                // String report ="C:\\Users\\Doshe\\Documents\\NetBeansProjects\\intreprisemanagementsystem\\src\\report\\newReportmateriau.jrxml";
               // JasperDesign jd=JRXmlLoader.load("C:\\Users\\Dosh\\Documents\\NetBeansProjects\\intreprisemanagementsystem\\src\\intreprisemanagementsystem\\logistic.jrxml");
                String sql="select * from locdoc  where transaction='"+tmp+"' and NUM='"+num.getText()+"'" ;
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
          
          if(recherche.getText().equals("")){
              String tmp =(String) jComboBox1.getSelectedItem();
     
             try{
                 
                 
                String sqls="Select path from pathn";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
            if(rs.next()){
             
                String sum=rs.getString("path");
                  
                 String NameFiles=sum;
            
            String NameFile=""+NameFiles+"logistic.jrxml";
                 
                 
                 
                JasperDesign jd=JRXmlLoader.load(NameFile);   
                 
                // String report ="C:\\Users\\Doshe\\Documents\\NetBeansProjects\\intreprisemanagementsystem\\src\\report\\newReportmateriau.jrxml";
               // JasperDesign jd=JRXmlLoader.load("C:\\Users\\Dosh\\Documents\\NetBeansProjects\\intreprisemanagementsystem\\src\\intreprisemanagementsystem\\logistic.jrxml");
                String sql="select * from locdoc  where transaction='"+tmp+"' and NUM='"+num.getText()+"'" ;
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
             }
          }
     }
                public void currency(){
    try{
String sql ="select * from currency";

pst=con.prepareStatement(sql);
 rs=pst.executeQuery();
            while(rs.next()){
                String sum=rs.getString("VUSD");
                  usd.setText(sum);
                  String sum1=rs.getString("ACDF");
                  cdf.setText(sum1);
                  String sum2=rs.getString("AUSH");
                  ush.setText(sum2);
                  
               
            }
    }catch(SQLException ex){
    JOptionPane.showMessageDialog(null,ex);
    }
}
     public void calulationS()
         {
             float a = Float.parseFloat(pu.getText());
             float b = Float.parseFloat(cdf.getText());
             float c;
        
             String d;
      
             c=a/b;
             d= String.format("%.2f",c);
           // fc3.setText(d);
            pu.setText(d);
             
         }
       public void calulation()
         {
             float a = Float.parseFloat(pu.getText());
             float b = Float.parseFloat(qty.getText());
             float c;
        
             String d;
      
             c=a*b;
             d= String.format("%.2f",c);
           // fc3.setText(d);
            pt.setText(d);
             
         }
       
       //USH
       public void calulationSUSH()
         {
             float a = Float.parseFloat(pu.getText());
             float b = Float.parseFloat(ush.getText());
             float c;
        
             String d;
      
             c=a/b;
             d= String.format("%.2f",c);
           // fc3.setText(d);
            pu.setText(d);
             
         }

       public void calltable(){
           
           if(jComboBox1.getSelectedItem().equals("BON DE COMMANDE")){
             try{
               
    String sql="SELECT ID,`LIBELLE`,`QTY`,`UNITE`,`PU`, `PT`, `OBS` FROM `locdoc` WHERE num='"+num.getText()+"'";
    //`LIBELLE`, `ENTRE`, `SORTIE`, `DATE`, `BUSS`, `USED`, `NUM`
    pst = con.prepareStatement(sql);
      rs= pst.executeQuery();
     
       jTable1.setModel(DbUtils.resultSetToTableModel(rs));
    
             }catch(Exception ex){
                 JOptionPane.showMessageDialog(null, ex);
             }
               
         TableColumn col0=jTable1.getColumnModel().getColumn(0);
        TableColumn col1=jTable1.getColumnModel().getColumn(1);
        TableColumn col2=jTable1.getColumnModel().getColumn(2);
        TableColumn col3=jTable1.getColumnModel().getColumn(3);
         TableColumn col4=jTable1.getColumnModel().getColumn(4);
          TableColumn col5=jTable1.getColumnModel().getColumn(5);
           TableColumn col6=jTable1.getColumnModel().getColumn(6);
           
       
       col0.setPreferredWidth(30);
       col1.setPreferredWidth(200);
       col2.setPreferredWidth(50);
       col3.setPreferredWidth(50);
       col4.setPreferredWidth(50);
       col5.setPreferredWidth(50);
       col6.setPreferredWidth(100);
       
     
     
    
           }else{
        try{
               
    String sql="SELECT ID,`LIBELLE`,`QTY`,`UNITE`,`PU`, `PT`, `OBS` FROM `locdoc` WHERE num='"+num.getText()+"'";
    //`LIBELLE`, `ENTRE`, `SORTIE`, `DATE`, `BUSS`, `USED`, `NUM`
    pst = con.prepareStatement(sql);
      rs= pst.executeQuery();
     
       jTable1.setModel(DbUtils.resultSetToTableModel(rs));
    
             }catch(Exception ex){
                 JOptionPane.showMessageDialog(null, ex);
             }
               
         TableColumn col0=jTable1.getColumnModel().getColumn(0);
        TableColumn col1=jTable1.getColumnModel().getColumn(1);
        TableColumn col2=jTable1.getColumnModel().getColumn(2);
        TableColumn col3=jTable1.getColumnModel().getColumn(3);
         TableColumn col4=jTable1.getColumnModel().getColumn(4);
          TableColumn col5=jTable1.getColumnModel().getColumn(5);
           TableColumn col6=jTable1.getColumnModel().getColumn(6);
           
       
       col0.setPreferredWidth(30);
       col1.setPreferredWidth(200);
       col2.setPreferredWidth(50);
       col3.setPreferredWidth(50);
       col4.setPreferredWidth(50);
       col5.setPreferredWidth(50);
       col6.setPreferredWidth(100);
       
      calltable2(); 
           }
    }
       
       
              public void calltable2(){
        try{
               
    String sql="SELECT distinct(NUM),TRANSACTION,DATES,DESTINATEUR FROM `locdoc` where transaction='"+jComboBox1.getSelectedItem()+"'";
    //`LIBELLE`, `ENTRE`, `SORTIE`, `DATE`, `BUSS`, `USED`, `NUM`
    pst = con.prepareStatement(sql);
      rs= pst.executeQuery();
     
       jTable2.setModel(DbUtils.resultSetToTableModel(rs));
    
             }catch(Exception ex){
                 JOptionPane.showMessageDialog(null, ex);
             }
               
         TableColumn col0=jTable2.getColumnModel().getColumn(0);
        TableColumn col1=jTable2.getColumnModel().getColumn(1);
        TableColumn col2=jTable2.getColumnModel().getColumn(2);
        TableColumn col3=jTable2.getColumnModel().getColumn(3);
         
           
       
       col0.setPreferredWidth(50);
       col1.setPreferredWidth(150);
       col2.setPreferredWidth(100);
       col3.setPreferredWidth(100);
       
    }

               public void calltables(){
        try{
               
    String sql="SELECT `NUM_ID`, `ITEM`, `DESCR`, `QTY`, `UNITY` FROM `materiaux_in` WHERE `NUM` like '"+jTextField1.getText()+"%'";
    //`LIBELLE`, `ENTRE`, `SORTIE`, `DATE`, `BUSS`, `USED`, `NUM`
    pst = con.prepareStatement(sql);
      rs= pst.executeQuery();
     
       jTable2.setModel(DbUtils.resultSetToTableModel(rs));
    
             }catch(Exception ex){
                 JOptionPane.showMessageDialog(null, ex);
             }
               
         TableColumn col0=jTable2.getColumnModel().getColumn(0);
        TableColumn col1=jTable2.getColumnModel().getColumn(1);
        TableColumn col2=jTable2.getColumnModel().getColumn(2);
        TableColumn col3=jTable2.getColumnModel().getColumn(3);
         TableColumn col4=jTable2.getColumnModel().getColumn(4);
           
       
       col0.setPreferredWidth(100);
       col1.setPreferredWidth(200);
       col2.setPreferredWidth(200);
       col3.setPreferredWidth(50);
       col4.setPreferredWidth(50);
       
     // calltable2(); 
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
               String ITEM,CODEB,LB,CODE_SUBCAT,CATB,SUB_CATB;
        public void call_budget(){
  String tmp =(String)jList1.getSelectedValue();
        try{
          String sql="SELECT item,code FROM  budget_code WHERE code='"+tmp+"' and CAT='"+buss.getSelectedItem()+"'";
          
            pst=con.prepareStatement(sql);
            //pst.setString(1, tmp);
            rs=pst.executeQuery();
            if(rs.next()){
                 
               ITEM = rs.getString("item");     
              CODEB = rs.getString("code");
          }
  
      }catch(Exception ex){
        JOptionPane.showMessageDialog(null,ex);  
      }
        
          try{
          String sql="SELECT CAT,LB,LBSUB,SUB_CAT FROM  budget WHERE code='"+tmp+"' and PROJECT='"+buss.getSelectedItem()+"'";
          
            pst=con.prepareStatement(sql);
            //pst.setString(1, tmp);
            rs=pst.executeQuery();
            if(rs.next()){
                 
             LB = rs.getString("LB");  
              CODE_SUBCAT = rs.getString("LBSUB");
             CATB = rs.getString("CAT");
             SUB_CATB = rs.getString("SUB_CAT");
              
          }
  
      }catch(Exception ex){
        JOptionPane.showMessageDialog(null,ex);  
      }
 }
  public void budget_LIST1()
    {
        DefaultListModel list = new DefaultListModel();
        
         
        try{
            String sql="SELECT * FROM `budget_code` where CAT='"+buss.getSelectedItem()+"' order by CODE";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                //String sum=rs.getString("nom");
                String sums=rs.getString("code");
                 list.addElement(sums);
                
                 jList1.setModel(list);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
    }
    
           public void attCall_IN_LIST_serach_LB()
    { 
        DefaultListModel list = new DefaultListModel();
 
        try{
          String sql="SELECT * FROM  budget_code where CODE like '"+recherche1.getText()+"%'  AND CAT='"+buss.getSelectedItem()+"' order by CODE";
             
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                //String sum=rs.getString("nom");
                String sums=rs.getString("code");
                 list.addElement(sums);
                
                 jList1.setModel(list);
            }
            }
        catch(Exception ex){
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
        jScrollPane1 = new javax.swing.JScrollPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        libelle = new javax.swing.JEditorPane();
        qty = new javax.swing.JTextField();
        unity = new javax.swing.JComboBox<>();
        jComboBox3 = new javax.swing.JComboBox<>();
        pu = new javax.swing.JTextField();
        pt = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        obs = new com.alee.laf.text.WebTextField();
        jButton2 = new javax.swing.JButton();
        jPanel19 = new javax.swing.JPanel();
        recherche2 = new javax.swing.JTextField();
        jScrollPane5 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jPanel7 = new javax.swing.JPanel();
        transport = new javax.swing.JTextField();
        receveur = new javax.swing.JTextField();
        No = new javax.swing.JTextField();
        jDateChooser1 = new com.alee.extended.date.WebDateField();
        jPanel4 = new javax.swing.JPanel();
        roll = new javax.swing.JTextField();
        num = new javax.swing.JTextField();
        recherche1 = new javax.swing.JTextField();
        id = new javax.swing.JTextField();
        usd = new javax.swing.JTextField();
        cdf = new javax.swing.JTextField();
        ush = new javax.swing.JTextField();
        jTextField1 = new javax.swing.JTextField();
        jComboBox2 = new javax.swing.JComboBox<>();
        destinateur = new javax.swing.JComboBox<>();
        IMPRIMER = new javax.swing.JButton();
        recherche = new javax.swing.JTextField();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();

        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_Bill_16px.png"))); // NOI18N
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
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

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

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
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jTable1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTable1KeyTyped(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

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
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jTable2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTable2KeyTyped(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);

        libelle.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        libelle.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        libelle.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                libelleKeyPressed(evt);
            }
        });
        jScrollPane4.setViewportView(libelle);

        qty.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        qty.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "QTY", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N
        qty.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                qtyActionPerformed(evt);
            }
        });
        qty.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                qtyKeyTyped(evt);
            }
        });

        unity.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        unity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                unityActionPerformed(evt);
            }
        });

        jComboBox3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "USD", "CDF", "USH" }));

        pu.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        pu.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "U.P", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N
        pu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                puKeyTyped(evt);
            }
        });

        pt.setEditable(false);
        pt.setBackground(new java.awt.Color(204, 204, 204));
        pt.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        pt.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "P.T", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jButton1.setBackground(new java.awt.Color(51, 255, 51));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton1.setText("ADD");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(255, 0, 0));
        jButton3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton3.setText("DELETE");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        obs.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Observation", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N
        obs.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton2.setText("New");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jPanel19.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)), "LB Code", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        recherche2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        recherche2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                recherche2KeyReleased(evt);
            }
        });

        jList1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jList1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jList1MouseEntered(evt);
            }
        });
        jScrollPane5.setViewportView(jList1);

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(recherche2, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                .addComponent(recherche2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
        );

        buss.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        buss.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select One Project" }));
        buss.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                bussPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(buss, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 331, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(qty)
                        .addGap(2, 2, 2)
                        .addComponent(unity, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(pu, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pt, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(obs, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(buss, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane4)
                        .addGroup(jPanel9Layout.createSequentialGroup()
                            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(unity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(pu, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(pt, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(obs, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addComponent(qty, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jButton1)
                                .addComponent(jButton3)
                                .addComponent(jButton2)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        transport.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        transport.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Transporteur", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N
        transport.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                transportKeyTyped(evt);
            }
        });

        receveur.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        receveur.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Receveur", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N
        receveur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                receveurActionPerformed(evt);
            }
        });
        receveur.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                receveurKeyTyped(evt);
            }
        });

        No.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        No.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "No. Plaque", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N
        No.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                NoKeyTyped(evt);
            }
        });

        jDateChooser1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Date", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N
        jDateChooser1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jComboBox1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECTIONNEZ UNE TRANSACTION" }));
        jComboBox1.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                jComboBox1PopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        roll.setEditable(false);
        roll.setForeground(new java.awt.Color(240, 240, 240));
        roll.setBorder(null);
        roll.setEnabled(false);

        num.setEditable(false);
        num.setBackground(new java.awt.Color(240, 240, 241));
        num.setForeground(new java.awt.Color(240, 240, 240));
        num.setBorder(null);
        num.setEnabled(false);

        recherche1.setEditable(false);
        recherche1.setBackground(new java.awt.Color(240, 240, 241));
        recherche1.setForeground(new java.awt.Color(240, 240, 240));
        recherche1.setBorder(null);
        recherche1.setEnabled(false);
        recherche1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                recherche1KeyReleased(evt);
            }
        });

        id.setEditable(false);
        id.setBackground(new java.awt.Color(240, 240, 241));
        id.setForeground(new java.awt.Color(240, 240, 240));
        id.setBorder(null);
        id.setEnabled(false);

        usd.setEditable(false);
        usd.setBackground(new java.awt.Color(240, 240, 241));
        usd.setForeground(new java.awt.Color(240, 240, 240));
        usd.setBorder(null);
        usd.setEnabled(false);

        cdf.setEditable(false);
        cdf.setBackground(new java.awt.Color(240, 240, 241));
        cdf.setForeground(new java.awt.Color(240, 240, 240));
        cdf.setBorder(null);
        cdf.setEnabled(false);

        ush.setEditable(false);
        ush.setBackground(new java.awt.Color(240, 240, 241));
        ush.setForeground(new java.awt.Color(240, 240, 240));
        ush.setBorder(null);
        ush.setEnabled(false);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(roll, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(num, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(recherche1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(id, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(69, 69, 69)
                .addComponent(usd, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cdf, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ush, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(197, 197, 197))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(usd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cdf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(roll, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(num, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(recherche1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(ush, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTextField1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });

        jComboBox2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jComboBox2.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                jComboBox2PopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        destinateur.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        destinateur.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Fournisseur", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(destinateur, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(transport, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(receveur, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(No))
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 419, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
                    .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField1))
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(transport, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(receveur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(No, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(destinateur))
                .addGap(36, 36, 36))
        );

        IMPRIMER.setBackground(new java.awt.Color(204, 204, 255));
        IMPRIMER.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        IMPRIMER.setText("IMPRIMER");
        IMPRIMER.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IMPRIMERActionPerformed(evt);
            }
        });

        recherche.setEditable(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(recherche, javax.swing.GroupLayout.PREFERRED_SIZE, 823, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(IMPRIMER, javax.swing.GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE))
                    .addComponent(jPanel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(250, 250, 250)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(72, 72, 72)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(IMPRIMER)
                    .addComponent(recherche, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jMenu1.setText("X");
        jMenu1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jMenu1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu1MouseClicked(evt);
            }
        });
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
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
       // TODO add your handling code here:
    }//GEN-LAST:event_formInternalFrameOpened

    private void jMenu1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu1MouseClicked
this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu1MouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        delete();

       calltable();       // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        savemateriaux();
        Refresh();
       calltable();// TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTable2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable2KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable2KeyTyped

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
select_jTable2();        // TODO add your handling code here:
    }//GEN-LAST:event_jTable2MouseClicked

    private void jTable1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable1KeyTyped

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        select_jTable();        // TODO add your handling code here:
    }//GEN-LAST:event_jTable1MouseClicked

    private void IMPRIMERActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IMPRIMERActionPerformed
        report();
        // TODO add your handling code here:
    }//GEN-LAST:event_IMPRIMERActionPerformed

    private void jComboBox1PopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_jComboBox1PopupMenuWillBecomeInvisible
        combobox1_from_materiel();
        num.setText(null);
        if(jComboBox1.getSelectedItem().equals("BON DE LIVRAISON")){
            jDateChooser1.setEnabled(true);
            jDateChooser1.setDate(new Date());
            destinateur.setEnabled(true);
            destinateur.setBackground(Color.white) ;
            receveur.setEnabled(false);
            receveur.setBackground(Color.LIGHT_GRAY) ;
            transport.setEnabled(false);
            receveur.setBackground(Color.LIGHT_GRAY) ;
            No.setEnabled(false);
        }else if(jComboBox1.getSelectedItem().equals("BON DE COMMANDE")){
           // jDateChooser1.setEnabled(false);
            jDateChooser1.setDate(new Date());
            destinateur.setEnabled(true);
            destinateur.setBackground(Color.white) ;
            receveur.setEnabled(false);
            receveur.setBackground(Color.LIGHT_GRAY) ;
            transport.setEnabled(false);
            transport.setBackground(Color.LIGHT_GRAY) ;
            No.setEnabled(false);
            No.setBackground(Color.LIGHT_GRAY) ;

        }else if(jComboBox1.getSelectedItem().equals("BORDEREAUX D EXPEDITION")){
            jDateChooser1.setEnabled(false);
            jDateChooser1.setDate(new Date());
            destinateur.setEnabled(true);
            destinateur.setBackground(Color.white) ;
            receveur.setEnabled(true);
            receveur.setBackground(Color.white) ;
            transport.setEnabled(true);
            transport.setBackground(Color.white) ;
            No.setEnabled(true);
            No.setBackground(Color.white) ;

        }
        Refresh();
        // jDateChooser1.setEnabled(false);
//        destinateur.setText(null);
        receveur.setText(null);
        transport.setText(null);
        No.setText(null);

       calltable();      // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1PopupMenuWillBecomeInvisible

    private void NoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_NoKeyTyped

    private void transportKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_transportKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_transportKeyTyped

    private void receveurKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_receveurKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_receveurKeyTyped

    private void libelleKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_libelleKeyPressed
        if (evt.getKeyCode()==KeyEvent.VK_ENTER){
            qty.requestFocusInWindow();
        }// TODO add your handling code here:
    }//GEN-LAST:event_libelleKeyPressed

    private void recherche1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_recherche1KeyReleased
        search1();        // TODO add your handling code here:
    }//GEN-LAST:event_recherche1KeyReleased

    private void receveurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_receveurActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_receveurActionPerformed

    private void unityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_unityActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_unityActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
Refreshtous();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void qtyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_qtyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_qtyActionPerformed

    private void qtyKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_qtyKeyTyped
char c=evt.getKeyChar();
if(!(Character.isDigit(c) || (c==KeyEvent.VK_BACK_SPACE)||c==KeyEvent.VK_DELETE ||c==KeyEvent.VK_PERIOD )){
evt.consume(); 
}         // TODO add your handling code here:
    }//GEN-LAST:event_qtyKeyTyped

    private void puKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_puKeyTyped
char c=evt.getKeyChar();
if(!(Character.isDigit(c) || (c==KeyEvent.VK_BACK_SPACE)||c==KeyEvent.VK_DELETE ||c==KeyEvent.VK_PERIOD )){
evt.consume(); 
}         // TODO add your handling code here:
    }//GEN-LAST:event_puKeyTyped

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
calltables();        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1KeyReleased

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jComboBox2PopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_jComboBox2PopupMenuWillBecomeInvisible
num.setText(jComboBox2.getSelectedItem().toString());        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox2PopupMenuWillBecomeInvisible

    private void recherche2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_recherche2KeyReleased
     attCall_IN_LIST_serach_LB();    // TODO add your handling code here:
    }//GEN-LAST:event_recherche2KeyReleased

    private void jList1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList1MouseClicked
        call_budget();              // TODO add your handling code here:
    }//GEN-LAST:event_jList1MouseClicked

    private void jList1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList1MouseEntered
        //JOptionPane.showMessageDialog(null, jList1.getSelectedValue());     // TODO add your handling code here:
    }//GEN-LAST:event_jList1MouseEntered

    private void bussPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_bussPopupMenuWillBecomeInvisible
        if(jComboBox1.getSelectedItem().equals("Select one Caisse")){
            JOptionPane.showMessageDialog(null,"Wrong Data","Error",JOptionPane.ERROR_MESSAGE);
        }else{
            budget_LIST1();}// TODO add your handling code here:
    }//GEN-LAST:event_bussPopupMenuWillBecomeInvisible


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton IMPRIMER;
    public static javax.swing.JTextField No;
    public static final javax.swing.JComboBox<String> buss = new javax.swing.JComboBox<>();
    private javax.swing.JTextField cdf;
    private javax.swing.JComboBox<String> destinateur;
    private javax.swing.JTextField id;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    public static final javax.swing.JComboBox<String> jComboBox1 = new javax.swing.JComboBox<>();
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    public static com.alee.extended.date.WebDateField jDateChooser1;
    private javax.swing.JList<String> jList1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private static final javax.swing.JTable jTable1 = new javax.swing.JTable();
    public static javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JEditorPane libelle;
    private javax.swing.JTextField num;
    public static com.alee.laf.text.WebTextField obs;
    private javax.swing.JTextField pt;
    private javax.swing.JTextField pu;
    private javax.swing.JTextField qty;
    public static javax.swing.JTextField receveur;
    private javax.swing.JTextField recherche;
    private javax.swing.JTextField recherche1;
    private javax.swing.JTextField recherche2;
    private javax.swing.JTextField roll;
    public static javax.swing.JTextField transport;
    private javax.swing.JComboBox<String> unity;
    private javax.swing.JTextField usd;
    private javax.swing.JTextField ush;
    // End of variables declaration//GEN-END:variables
}
