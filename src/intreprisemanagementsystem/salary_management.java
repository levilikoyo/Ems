/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intreprisemanagementsystem;

import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author DOSHE
 */
public class salary_management extends javax.swing.JInternalFrame {

   DefaultTableModel modes;
  DefaultTableModel mode;
  DefaultTableModel etmode;
  DefaultTableModel attmode;
 DefaultTableModel modetable3;
Connection con=null;
PreparedStatement pst=null;
ResultSet rs= null;
String rolls;
String etrolls;
String salaryrolls;
    public salary_management() {
        initComponents();
              con=JavaDbConnect.dbConnect();
      
        call_ipr();
      //  Toolkit.getDefaultToolkit().getImage(getClass().getResource("icons8_Raspberry_Pi_48px_1.png"));
    }
    
    
 
    
    
    
   
    
   

    
   
     

      
   
      
      
  
       
      
       
   
       
      
      
   
       
     
       
     
       
     
       
      Float AUSD;
       
    public void call_currency(){
    
     try{
            String sqls="SELECT BUY FROM `currency` WHERE APPR='USD'";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
            while(rs.next()){
              AUSD = rs.getFloat("BUY");
              
              
             
              }
            }
        catch(Exception ex){
         JOptionPane.showMessageDialog(null, ex);}
        
    } 
    public void calculationFromCDFA(){
        call_currency();
    double a,b,c,d,f;
                  a = Float.parseFloat(afromcdf.getText());
                  
                 String e;
                 String ee;
                  c=a/AUSD;
                 // f=c
                  e= String.format("%.1f",c);
                  afromusd.setText(e);
                  b = Float.parseFloat(afromusd.getText());
                  d = Float.parseFloat(atousd.getText());
                  f=d-b;
                  ee= String.format("%.1f",f);
                  diffa.setText(ee);
    }
    
     public void calculationTOCDFA(){
        call_currency();
    double a,b,c,d,f;
                  a = Float.parseFloat(atocdf.getText());
                 String e;
                 String ee;
                  c=a/AUSD;
                  e= String.format("%.1f",c);
                  atousd.setText(e);
                  b = Float.parseFloat(afromusd.getText());
                  d = Float.parseFloat(atousd.getText());
                  f=d-b;
                  ee= String.format("%.1f",f);
                  diffa.setText(ee);
    }
     
     
         public void calculationFromCDFB(){
        call_currency();
    double a,b,c,d,f;
                  a = Float.parseFloat(bfromcdf.getText());
                 String e,ee;
                  c=a/AUSD;
                  e= String.format("%.1f",c);
                  bfromusd.setText(e);
                  
                  b = Float.parseFloat(bfromusd.getText());
                  d = Float.parseFloat(btousd.getText());
                  f=d-b;
                  ee= String.format("%.1f",f);
                  diffb.setText(ee);
                  
                  
    }
    
     public void calculationTOCDFB(){
        call_currency();
      double a,b,c,d,f;
                  a = Float.parseFloat(btocdf.getText());
                 String e,ee;
                  c=a/AUSD;
                  e= String.format("%.1f",c);
                  btousd.setText(e);
                  
                  b = Float.parseFloat(bfromusd.getText());
                  d = Float.parseFloat(btousd.getText());
                  f=d-b;
                  ee= String.format("%.1f",f);
                  diffb.setText(ee);
    }
     
     
      public void calculationFromCDFC(){
        call_currency();
   double a,b,c,d,f;
                  a = Float.parseFloat(cfromcdf.getText());
                 String e,ee;
                  c=a/AUSD;
                  e= String.format("%.1f",c);
                  cfromusd.setText(e);
                  
                   b = Float.parseFloat(cfromusd.getText());
                  d = Float.parseFloat(ctousd.getText());
                  f=d-b;
                  ee= String.format("%.1f",f);
                  diffc.setText(ee);
    }
    
     public void calculationTOCDFC(){
        call_currency();
     double a,b,c,d,f;
                  a = Float.parseFloat(ctocdf.getText());
                 String e,ee;
                  c=a/AUSD;
                  e= String.format("%.1f",c);
                  ctousd.setText(e);
                  
                   b = Float.parseFloat(cfromusd.getText());
                  d = Float.parseFloat(ctousd.getText());
                  f=d-b;
                  ee= String.format("%.1f",f);
                  diffc.setText(ee);
    }
     
      public void calculationFromCDFD(){
        call_currency();
    double a,b,c,d,f;
                  a = Float.parseFloat(dfromcdf.getText());
                 String e,ee;
                  c=a/AUSD;
                  e= String.format("%.1f",c);
                  dfromusd.setText(e);
                  
                   b = Float.parseFloat(dfromusd.getText());
                  d = Float.parseFloat(dtousd.getText());
                  f=d-b;
                  ee= String.format("%.1f",f);
                  diffd.setText(ee);
    }
       
   public void update_IPR(){
       
        String CHECK = null;
       try{
            String sql="SELECT * FROM  ipr WHERE ID=1";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            if(rs.next()){
                 
              try {
         
        PreparedStatement pst = con.prepareStatement("UPDATE `ipr` SET `RANGE`=?,`FROM_CDF`=?,`TO_CDF`=?,`FROM_USD`=?,`TO_USD`=?,`DIFFENCE`=?,`POUR`=? WHERE ID='1'");
       
        pst.setString(1, AAA.getText());
        pst.setString(2, afromcdf.getText());
         pst.setString(3, atocdf.getText());
         pst.setString(4, afromusd.getText());
         pst.setString(5, atousd.getText());
         pst.setString(6, diffa.getText());
         pst.setString(7, rangea.getText());
       
        
          pst.executeUpdate();
        
               //  JOptionPane.showMessageDialog(null,"Data Saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }      
          try {
         
        PreparedStatement pst = con.prepareStatement("UPDATE `ipr` SET `RANGE`=?,`FROM_CDF`=?,`TO_CDF`=?,`FROM_USD`=?,`TO_USD`=?,`DIFFENCE`=?,`POUR`=? WHERE ID='2'");
       
        pst.setString(1, BBB.getText());
        pst.setString(2, bfromcdf.getText());
         pst.setString(3, btocdf.getText());
         pst.setString(4, bfromusd.getText());
         pst.setString(5, btousd.getText());
         pst.setString(6, diffb.getText());
         pst.setString(7, rangeb.getText());
       
        
          pst.executeUpdate();
        
                // JOptionPane.showMessageDialog(null,"Data Saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
          
           try {
         
        PreparedStatement pst = con.prepareStatement("UPDATE `ipr` SET `RANGE`=?,`FROM_CDF`=?,`TO_CDF`=?,`FROM_USD`=?,`TO_USD`=?,`DIFFENCE`=?,`POUR`=? WHERE  ID='3'");
       
        pst.setString(1, CCC.getText());
        pst.setString(2, cfromcdf.getText());
         pst.setString(3, ctocdf.getText());
         pst.setString(4, cfromusd.getText());
         pst.setString(5, ctousd.getText());
         pst.setString(6, diffc.getText());
         pst.setString(7, rangec.getText());
       
        
          pst.executeUpdate();
        
                // JOptionPane.showMessageDialog(null,"Data Saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }  
           
           
            try {
         
        PreparedStatement pst = con.prepareStatement("UPDATE `ipr` SET `RANGE`=?,`FROM_CDF`=?,`TO_CDF`=?,`FROM_USD`=?,`TO_USD`=?,`DIFFENCE`=?,`POUR`=? WHERE ID='4'");
       
        pst.setString(1, DDD.getText());
        pst.setString(2, dfromcdf.getText());
         pst.setString(3, dtocdf.getText());
         pst.setString(4, dfromusd.getText());
         pst.setString(5, dtousd.getText());
         pst.setString(6, diffd.getText());
         pst.setString(7, ranged.getText());
       
        
          pst.executeUpdate();
        
                 JOptionPane.showMessageDialog(null,"Data Saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }        
                
                
            }else{
            
                //UPDATE
                
                try {
         
        PreparedStatement pst = con.prepareStatement("INSERT INTO `ipr`(`RANGE`, `FROM_CDF`, `TO_CDF`, `FROM_USD`, `TO_USD`, `DIFFENCE`, `POUR`) VALUES (?,?,?,?,?,?,?)");
       
        pst.setString(1, AAA.getText());
        pst.setString(2, afromcdf.getText());
         pst.setString(3, atocdf.getText());
         pst.setString(4, afromusd.getText());
         pst.setString(5, atousd.getText());
         pst.setString(6, diffa.getText());
         pst.setString(7, rangea.getText());
       
        
          pst.executeUpdate();
        
               //  JOptionPane.showMessageDialog(null,"Data Saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }      
          try {
         
         PreparedStatement pst = con.prepareStatement("INSERT INTO `ipr`(`RANGE`, `FROM_CDF`, `TO_CDF`, `FROM_USD`, `TO_USD`, `DIFFENCE`, `POUR`) VALUES (?,?,?,?,?,?,?)");
       
        pst.setString(1, BBB.getText());
        pst.setString(2, bfromcdf.getText());
         pst.setString(3, btocdf.getText());
         pst.setString(4, bfromusd.getText());
         pst.setString(5, btousd.getText());
         pst.setString(6, diffb.getText());
         pst.setString(7, rangeb.getText());
       
        
          pst.executeUpdate();
        
                // JOptionPane.showMessageDialog(null,"Data Saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
          
           try {
         
        PreparedStatement pst = con.prepareStatement("INSERT INTO `ipr`(`RANGE`, `FROM_CDF`, `TO_CDF`, `FROM_USD`, `TO_USD`, `DIFFENCE`, `POUR`) VALUES (?,?,?,?,?,?,?)");
       
        pst.setString(1, CCC.getText());
        pst.setString(2, cfromcdf.getText());
         pst.setString(3, ctocdf.getText());
         pst.setString(4, cfromusd.getText());
         pst.setString(5, ctousd.getText());
         pst.setString(6, diffc.getText());
         pst.setString(7, rangec.getText());
       
        
          pst.executeUpdate();
        
                // JOptionPane.showMessageDialog(null,"Data Saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }  
           
           
            try {
         
         PreparedStatement pst = con.prepareStatement("INSERT INTO `ipr`(`RANGE`, `FROM_CDF`, `TO_CDF`, `FROM_USD`, `TO_USD`, `DIFFENCE`, `POUR`) VALUES (?,?,?,?,?,?,?)");
       
        pst.setString(1, DDD.getText());
        pst.setString(2, dfromcdf.getText());
         pst.setString(3, dtocdf.getText());
         pst.setString(4, dfromusd.getText());
         pst.setString(5, dtousd.getText());
         pst.setString(6, diffd.getText());
         pst.setString(7, ranged.getText());
       
        
          pst.executeUpdate();
        
                 JOptionPane.showMessageDialog(null,"Data Saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }      
                
                
                
            
            }
             }
        catch(Exception ex){
      //    JOptionPane.showMessageDialog(null, ex);    
        }
       
         
}
   
    public void call_ipr(){
    
     try{
            String sqls="SELECT `FROM_CDF`, `TO_CDF`, `FROM_USD`, `TO_USD`, `DIFFENCE`, `POUR` FROM `ipr` WHERE ID='1'";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
            while(rs.next()){
              String sum = rs.getString("FROM_CDF");
              afromcdf.setText(sum);
              
              String sum5 = rs.getString("TO_CDF");
              atocdf.setText(sum5);
              
              String sum1 = rs.getString("FROM_USD");
              afromusd.setText(sum1);
              
              String sum2 = rs.getString("TO_USD");
              atousd.setText(sum2);
              
              String sum3 = rs.getString("DIFFENCE");
              diffa.setText(sum3);
              
              String sum4 = rs.getString("POUR");
              rangea.setText(sum4);
              
              
             
              }
            }
        catch(Exception ex){
         JOptionPane.showMessageDialog(null, ex);}
        
       try{
            String sqls="SELECT `FROM_CDF`, `TO_CDF`, `FROM_USD`, `TO_USD`, `DIFFENCE`, `POUR` FROM `ipr` WHERE ID='2'";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
            while(rs.next()){
              String sum = rs.getString("FROM_CDF");
              bfromcdf.setText(sum);
              
              String sum5 = rs.getString("TO_CDF");
              btocdf.setText(sum5);
              
              String sum1 = rs.getString("FROM_USD");
              bfromusd.setText(sum1);
              
              String sum2 = rs.getString("TO_USD");
              btousd.setText(sum2);
              
              String sum3 = rs.getString("DIFFENCE");
              diffb.setText(sum3);
              
              String sum4 = rs.getString("POUR");
              rangeb.setText(sum4);
              
              
             
              }
            }
        catch(Exception ex){
         JOptionPane.showMessageDialog(null, ex);}
     
       try{
            String sqls="SELECT `FROM_CDF`, `TO_CDF`, `FROM_USD`, `TO_USD`, `DIFFENCE`, `POUR` FROM `ipr` WHERE ID='3'";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
            while(rs.next()){
              String sum = rs.getString("FROM_CDF");
              cfromcdf.setText(sum);
              
              String sum5 = rs.getString("TO_CDF");
              ctocdf.setText(sum5);
              
              String sum1 = rs.getString("FROM_USD");
              cfromusd.setText(sum1);
              
              String sum2 = rs.getString("TO_USD");
              ctousd.setText(sum2);
              
              String sum3 = rs.getString("DIFFENCE");
              diffc.setText(sum3);
              
              String sum4 = rs.getString("POUR");
              rangec.setText(sum4);
              
              
             
              }
            }
        catch(Exception ex){
         JOptionPane.showMessageDialog(null, ex);}
       
         try{
            String sqls="SELECT `FROM_CDF`, `TO_CDF`, `FROM_USD`, `TO_USD`, `DIFFENCE`, `POUR` FROM `ipr` WHERE ID='4'";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
            while(rs.next()){
              String sum = rs.getString("FROM_CDF");
              dfromcdf.setText(sum);
              
              String sum5 = rs.getString("TO_CDF");
              dtocdf.setText(sum5);
              
              String sum1 = rs.getString("FROM_USD");
              dfromusd.setText(sum1);
              
              String sum2 = rs.getString("TO_USD");
              dtousd.setText(sum2);
              
              String sum3 = rs.getString("DIFFENCE");
              diffd.setText(sum3);
              
              String sum4 = rs.getString("POUR");
              ranged.setText(sum4);
              
              
             
              }
            }
        catch(Exception ex){
         JOptionPane.showMessageDialog(null, ex);}
       
         
         try{
            String sqls="SELECT `INSS`,`INPP`,`ONME`,`CNSS`,`SICK` FROM `retenue` WHERE ID='1'";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
            while(rs.next()){
              String sum = rs.getString("INSS");
              retenueinss.setText(sum);
              
              String sum5 = rs.getString("INPP");
              retenueinpp.setText(sum5);
              
              String sum1 = rs.getString("ONME");
              retenueoneme.setText(sum1);
              
              String sum11 = rs.getString("CNSS");
              retenuecnss.setText(sum11);
              String sum1s = rs.getString("SICK");
             sick.setText(sum1s);
            }
            }
        catch(Exception ex){
         JOptionPane.showMessageDialog(null, ex);}
         
          try{
            String sqls="SELECT `TRANSPORT`, `ACCOMMODATION`, `ALL_FAM`, `ACCOURAGEMENT`, `AUTRES` FROM `avantage` WHERE ID='1'";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
            while(rs.next()){
              String sum = rs.getString("TRANSPORT");
              transport.setText(sum);
              
              String sum5 = rs.getString("ACCOMMODATION");
              accommodation.setText(sum5);
              
              String sum1 = rs.getString("ALL_FAM");
              all_fam.setText(sum1);
              
              String sum11 = rs.getString("AUTRES");
              price.setText(sum11);
              
              String sum12 = rs.getString("ACCOURAGEMENT");
             accouragement.setText(sum12);
            }
            }
        catch(Exception ex){
         JOptionPane.showMessageDialog(null, ex);}
    } 
    
    public void saveretenu(){
        
          try{
            String sql="SELECT INSS FROM  retenue WHERE ID=1";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            if(rs.next()){
                 
                try {
         
        PreparedStatement pst = con.prepareStatement("UPDATE `retenue` SET `INSS`=?,`INPP`=?,`ONME`=?,`CNSS`=?,SICK=? WHERE ID='1'");
       
        pst.setString(1, retenueinss.getText());
        pst.setString(2, retenueinpp.getText());
         pst.setString(3, retenueoneme.getText());
          pst.setString(4, retenuecnss.getText());
        pst.setString(5,sick.getText());
        
       
        
          pst.executeUpdate();
        
                 JOptionPane.showMessageDialog(null,"Data Saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }       
                
            }else{
            
             try {
         
        PreparedStatement pst = con.prepareStatement("INSERT INTO `retenue`(`INSS`, `INPP`, `ONME`, `CNSS`,SICK) VALUES (?,?,?,?,?)");
       
        pst.setString(1, retenueinss.getText());
        pst.setString(2, retenueinpp.getText());
         pst.setString(3, retenueoneme.getText());
          pst.setString(4, retenuecnss.getText());
          pst.setString(5,sick.getText());
        
       
        
          pst.executeUpdate();
        
                 JOptionPane.showMessageDialog(null,"Data Saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }      
            
            }
             }
        catch(Exception ex){
      //    JOptionPane.showMessageDialog(null, ex);    
        }
    
        
    
    }
    
     public void saveavantage(){
      try{
            String sql="SELECT TRANSPORT FROM  avantage WHERE ID=1";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            if(rs.next()){
                try {
         
        PreparedStatement pst = con.prepareStatement("UPDATE `avantage` SET `TRANSPORT`=?,`ACCOMMODATION`=?,`ALL_FAM`=?,`ACCOURAGEMENT`=?,`AUTRES`=? WHERE ID='1'");
       
        pst.setString(1, transport.getText());
        pst.setString(2, accommodation.getText());
         pst.setString(3, all_fam.getText());
         pst.setString(4, accouragement.getText());
         pst.setString(5, price.getText());
        
       
        
          pst.executeUpdate();
        
                 JOptionPane.showMessageDialog(null,"Data Saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }      
            }else{
            try {
         
        PreparedStatement pst = con.prepareStatement("INSERT INTO `avantage`(`TRANSPORT`, `ACCOMMODATION`, `ALL_FAM`, `ACCOURAGEMENT`, `AUTRES`) VALUES (?,?,?,?,?)");
       
        pst.setString(1, transport.getText());
        pst.setString(2, accommodation.getText());
         pst.setString(3, all_fam.getText());
         pst.setString(4, accouragement.getText());
         pst.setString(5, price.getText());
        
       
        
          pst.executeUpdate();
        
                 JOptionPane.showMessageDialog(null,"Data Saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }      
            
            }
             }
        catch(Exception ex){
      //    JOptionPane.showMessageDialog(null, ex);    
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

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        AAA = new javax.swing.JLabel();
        BBB = new javax.swing.JLabel();
        CCC = new javax.swing.JLabel();
        DDD = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        atocdf = new javax.swing.JTextField();
        btocdf = new javax.swing.JTextField();
        ctocdf = new javax.swing.JTextField();
        dtocdf = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        afromcdf = new javax.swing.JTextField();
        bfromcdf = new javax.swing.JTextField();
        cfromcdf = new javax.swing.JTextField();
        dfromcdf = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        afromusd = new javax.swing.JTextField();
        bfromusd = new javax.swing.JTextField();
        cfromusd = new javax.swing.JTextField();
        dfromusd = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jPanel19 = new javax.swing.JPanel();
        atousd = new javax.swing.JTextField();
        btousd = new javax.swing.JTextField();
        ctousd = new javax.swing.JTextField();
        dtousd = new javax.swing.JTextField();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jPanel28 = new javax.swing.JPanel();
        rangea = new javax.swing.JTextField();
        rangeb = new javax.swing.JTextField();
        rangec = new javax.swing.JTextField();
        ranged = new javax.swing.JTextField();
        jPanel29 = new javax.swing.JPanel();
        diffa = new javax.swing.JTextField();
        diffb = new javax.swing.JTextField();
        diffc = new javax.swing.JTextField();
        diffd = new javax.swing.JTextField();
        jPanel22 = new javax.swing.JPanel();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        retenueinss = new javax.swing.JTextField();
        sick = new javax.swing.JTextField();
        retenueinpp = new javax.swing.JTextField();
        retenueoneme = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        retenuecnss = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jPanel21 = new javax.swing.JPanel();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jPanel30 = new javax.swing.JPanel();
        jPanel31 = new javax.swing.JPanel();
        jPanel32 = new javax.swing.JPanel();
        jPanel33 = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        jPanel34 = new javax.swing.JPanel();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jPanel35 = new javax.swing.JPanel();
        all_fam = new javax.swing.JTextField();
        transport = new javax.swing.JTextField();
        accommodation = new javax.swing.JTextField();
        accouragement = new javax.swing.JTextField();
        price = new javax.swing.JTextField();
        jLabel59 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();

        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_Related_Companies_16px.png"))); // NOI18N

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));
        jPanel14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));
        jPanel15.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ranges", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N

        AAA.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        AAA.setText("Range A");

        BBB.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        BBB.setText("Range B");

        CCC.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        CCC.setText("Range C");

        DDD.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        DDD.setText("Range D");

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(AAA)
                    .addComponent(BBB)
                    .addComponent(CCC)
                    .addComponent(DDD))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addComponent(AAA)
                .addGap(11, 11, 11)
                .addComponent(BBB)
                .addGap(18, 18, 18)
                .addComponent(CCC)
                .addGap(18, 18, 18)
                .addComponent(DDD)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));
        jPanel16.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "To CDF", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N

        atocdf.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        atocdf.setText("0");
        atocdf.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                atocdfKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                atocdfKeyTyped(evt);
            }
        });

        btocdf.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btocdf.setText("0");
        btocdf.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                btocdfKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                btocdfKeyTyped(evt);
            }
        });

        ctocdf.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        ctocdf.setText("0");
        ctocdf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ctocdfActionPerformed(evt);
            }
        });
        ctocdf.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                ctocdfKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                ctocdfKeyTyped(evt);
            }
        });

        dtocdf.setEditable(false);
        dtocdf.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        dtocdf.setText("...");

        jLabel32.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel32.setText("CDF");

        jLabel33.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel33.setText("CDF");

        jLabel34.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel34.setText("CDF");

        jLabel35.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel35.setText("CDF");

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dtocdf)
                    .addComponent(atocdf, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btocdf)
                    .addComponent(ctocdf))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel32)
                    .addComponent(jLabel33)
                    .addComponent(jLabel34)
                    .addComponent(jLabel35))
                .addGap(5, 5, 5))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(atocdf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel32))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btocdf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel33))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ctocdf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel34))
                .addGap(11, 11, 11)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dtocdf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel35))
                .addGap(11, 11, 11))
        );

        jPanel17.setBackground(new java.awt.Color(255, 255, 255));
        jPanel17.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "From CDF", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N

        afromcdf.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        afromcdf.setText("0");
        afromcdf.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                afromcdfKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                afromcdfKeyTyped(evt);
            }
        });

        bfromcdf.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        bfromcdf.setText("0");
        bfromcdf.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                bfromcdfKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                bfromcdfKeyTyped(evt);
            }
        });

        cfromcdf.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        cfromcdf.setText("0");
        cfromcdf.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cfromcdfKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                cfromcdfKeyTyped(evt);
            }
        });

        dfromcdf.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        dfromcdf.setText("0");
        dfromcdf.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                dfromcdfKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                dfromcdfKeyTyped(evt);
            }
        });

        jLabel36.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel36.setText("CDF");

        jLabel37.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel37.setText("CDF");

        jLabel38.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel38.setText("CDF");

        jLabel39.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel39.setText("CDF");

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dfromcdf)
                    .addComponent(afromcdf, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bfromcdf)
                    .addComponent(cfromcdf))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel36)
                    .addComponent(jLabel37)
                    .addComponent(jLabel38)
                    .addComponent(jLabel39))
                .addGap(5, 5, 5))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(afromcdf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel36))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bfromcdf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel37))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cfromcdf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel38))
                .addGap(11, 11, 11)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dfromcdf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel39))
                .addGap(11, 11, 11))
        );

        jPanel18.setBackground(new java.awt.Color(255, 255, 255));
        jPanel18.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "From USD", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N

        afromusd.setEditable(false);
        afromusd.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        afromusd.setText("0");

        bfromusd.setEditable(false);
        bfromusd.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        bfromusd.setText("0");

        cfromusd.setEditable(false);
        cfromusd.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        cfromusd.setText("0");

        dfromusd.setEditable(false);
        dfromusd.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        dfromusd.setText("0");

        jLabel40.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel40.setText("USD");

        jLabel41.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel41.setText("USD");

        jLabel42.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel42.setText("USD");

        jLabel43.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel43.setText("USD");

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dfromusd)
                    .addComponent(afromusd, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bfromusd)
                    .addComponent(cfromusd))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel40)
                    .addComponent(jLabel41)
                    .addComponent(jLabel42)
                    .addComponent(jLabel43)))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(afromusd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel40))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bfromusd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel41))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cfromusd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel42))
                .addGap(11, 11, 11)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dfromusd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel43))
                .addGap(11, 11, 11))
        );

        jPanel19.setBackground(new java.awt.Color(255, 255, 255));
        jPanel19.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "To USD", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N

        atousd.setEditable(false);
        atousd.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        atousd.setText("0");

        btousd.setEditable(false);
        btousd.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btousd.setText("0");

        ctousd.setEditable(false);
        ctousd.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        ctousd.setText("0");

        dtousd.setEditable(false);
        dtousd.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        dtousd.setText("...");

        jLabel44.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel44.setText("USD");

        jLabel45.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel45.setText("USD");

        jLabel46.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel46.setText("USD");

        jLabel47.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel47.setText("USD");

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dtousd, javax.swing.GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE)
                    .addComponent(btousd)
                    .addComponent(ctousd)
                    .addComponent(atousd))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel44)
                    .addComponent(jLabel45)
                    .addComponent(jLabel46)
                    .addComponent(jLabel47)))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(atousd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel44))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btousd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel45))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ctousd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel46))
                .addGap(11, 11, 11)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dtousd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel47))
                .addGap(11, 11, 11))
        );

        jPanel28.setBackground(new java.awt.Color(255, 255, 255));
        jPanel28.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "%", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        rangea.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        rangea.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                rangeaKeyTyped(evt);
            }
        });

        rangeb.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        rangeb.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                rangebKeyTyped(evt);
            }
        });

        rangec.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        rangec.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                rangecKeyTyped(evt);
            }
        });

        ranged.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        ranged.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                rangedKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
        jPanel28.setLayout(jPanel28Layout);
        jPanel28Layout.setHorizontalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel28Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(ranged, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE)
                    .addComponent(rangec, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rangeb)
                    .addComponent(rangea))
                .addContainerGap())
        );
        jPanel28Layout.setVerticalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addComponent(rangea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(rangeb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rangec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ranged, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11))
        );

        jPanel29.setBackground(new java.awt.Color(255, 255, 255));
        jPanel29.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Difference $", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N

        diffa.setEditable(false);
        diffa.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        diffa.setText("0");

        diffb.setEditable(false);
        diffb.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        diffb.setText("0");

        diffc.setEditable(false);
        diffc.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        diffc.setText("0");

        diffd.setEditable(false);
        diffd.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        diffd.setText("0");

        javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
        jPanel29.setLayout(jPanel29Layout);
        jPanel29Layout.setHorizontalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(diffa, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
                    .addComponent(diffb)
                    .addComponent(diffc)
                    .addComponent(diffd))
                .addContainerGap())
        );
        jPanel29Layout.setVerticalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addComponent(diffa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(diffb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(diffc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(diffd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel15, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel17, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel16, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel18, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel19, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel29, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel22.setBackground(new java.awt.Color(255, 255, 255));
        jPanel22.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ranges", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N

        jButton7.setText("New");

        jButton8.setText("OK");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton8, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel22, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Fourchette IPR", jPanel1);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Retenus", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14))); // NOI18N
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("CNSS");
        jPanel7.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 19, -1, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("SICK");
        jPanel7.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 47, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("INPP");
        jPanel7.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 82, -1, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("ONEM");
        jPanel7.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 117, -1, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Salary Advance");
        jPanel7.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 145, -1, -1));

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel27.setText("CNSS");
        jPanel7.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 180, -1, -1));

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "%", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14))); // NOI18N
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        retenueinss.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        retenueinss.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                retenueinssKeyTyped(evt);
            }
        });
        jPanel8.add(retenueinss, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 19, 54, -1));

        sick.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jPanel8.add(sick, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 51, 54, -1));

        retenueinpp.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        retenueinpp.setText("3");
        retenueinpp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                retenueinppKeyTyped(evt);
            }
        });
        jPanel8.add(retenueinpp, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 83, 54, -1));

        retenueoneme.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        retenueoneme.setText("0.2");
        retenueoneme.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                retenueonemeKeyTyped(evt);
            }
        });
        jPanel8.add(retenueoneme, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 115, 54, -1));

        jTextField5.setEditable(false);
        jTextField5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jPanel8.add(jTextField5, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 147, 54, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("%");
        jPanel8.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(74, 20, -1, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("%");
        jPanel8.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(74, 52, -1, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("%");
        jPanel8.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(74, 84, -1, -1));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("%");
        jPanel8.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(74, 116, -1, -1));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setText("%");
        jPanel8.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(74, 148, -1, -1));

        retenuecnss.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jPanel8.add(retenuecnss, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 179, 54, -1));

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel28.setText("%");
        jPanel8.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(74, 180, -1, -1));

        jPanel21.setBackground(new java.awt.Color(255, 255, 255));
        jPanel21.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Commands", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14))); // NOI18N

        jButton5.setText("New");

        jButton6.setText("OK");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
                    .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 116, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jPanel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
                        .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(147, 147, 147)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(270, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Retenues", jPanel2);

        jPanel31.setBackground(new java.awt.Color(255, 255, 255));

        jPanel32.setBackground(new java.awt.Color(255, 255, 255));

        jPanel33.setBackground(new java.awt.Color(255, 255, 255));
        jPanel33.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Advantage", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14))); // NOI18N
        jPanel33.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel29.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel29.setText("Familly Allocation");
        jPanel33.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 19, -1, -1));

        jLabel30.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel30.setText("Transport");
        jPanel33.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 54, -1, -1));

        jLabel56.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel56.setText("Accomodation");
        jPanel33.add(jLabel56, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 89, -1, -1));

        jLabel57.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel57.setText("Acouragement");
        jPanel33.add(jLabel57, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 124, -1, -1));

        jLabel58.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel58.setText("Price");
        jPanel33.add(jLabel58, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 159, -1, -1));

        jPanel34.setBackground(new java.awt.Color(255, 255, 255));
        jPanel34.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Commands", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14))); // NOI18N

        jButton11.setText("New");

        jButton12.setText("OK");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel34Layout = new javax.swing.GroupLayout(jPanel34);
        jPanel34.setLayout(jPanel34Layout);
        jPanel34Layout.setHorizontalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel34Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton11, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
                    .addComponent(jButton12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel34Layout.setVerticalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel34Layout.createSequentialGroup()
                .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel35.setBackground(new java.awt.Color(255, 255, 255));
        jPanel35.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "% Or Equivalente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14))); // NOI18N
        jPanel35.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        all_fam.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        all_fam.setText("0");
        all_fam.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                all_famKeyTyped(evt);
            }
        });
        jPanel35.add(all_fam, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 19, 67, -1));

        transport.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        transport.setText("0");
        transport.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                transportKeyTyped(evt);
            }
        });
        jPanel35.add(transport, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 53, 67, -1));

        accommodation.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        accommodation.setText("0");
        accommodation.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                accommodationKeyTyped(evt);
            }
        });
        jPanel35.add(accommodation, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 87, 67, -1));

        accouragement.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        accouragement.setText("0");
        accouragement.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                accouragementKeyTyped(evt);
            }
        });
        jPanel35.add(accouragement, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 121, 67, -1));

        price.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        price.setText("0");
        price.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                priceActionPerformed(evt);
            }
        });
        price.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                priceKeyTyped(evt);
            }
        });
        jPanel35.add(price, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 155, 67, -1));

        jLabel59.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel59.setText("CDF");
        jPanel35.add(jLabel59, new org.netbeans.lib.awtextra.AbsoluteConstraints(87, 22, -1, -1));

        jLabel60.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel60.setText("CDF");
        jPanel35.add(jLabel60, new org.netbeans.lib.awtextra.AbsoluteConstraints(87, 56, -1, -1));

        jLabel61.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel61.setText("%");
        jPanel35.add(jLabel61, new org.netbeans.lib.awtextra.AbsoluteConstraints(87, 90, -1, -1));

        jLabel62.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel62.setText("%");
        jPanel35.add(jLabel62, new org.netbeans.lib.awtextra.AbsoluteConstraints(87, 124, -1, -1));

        jLabel63.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel63.setText("%");
        jPanel35.add(jLabel63, new org.netbeans.lib.awtextra.AbsoluteConstraints(87, 158, -1, -1));

        javax.swing.GroupLayout jPanel32Layout = new javax.swing.GroupLayout(jPanel32);
        jPanel32.setLayout(jPanel32Layout);
        jPanel32Layout.setHorizontalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel33, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel35, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel32Layout.setVerticalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel32Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel35, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
                    .addComponent(jPanel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel31Layout = new javax.swing.GroupLayout(jPanel31);
        jPanel31.setLayout(jPanel31Layout);
        jPanel31Layout.setHorizontalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel31Layout.createSequentialGroup()
                .addGap(197, 197, 197)
                .addComponent(jPanel32, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(246, Short.MAX_VALUE))
        );
        jPanel31Layout.setVerticalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel31Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(34, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel30Layout = new javax.swing.GroupLayout(jPanel30);
        jPanel30.setLayout(jPanel30Layout);
        jPanel30Layout.setHorizontalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel30Layout.setVerticalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Avantages", jPanel30);

        jMenu1.setBackground(new java.awt.Color(255, 51, 51));
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
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenu1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu1MouseClicked
this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu1MouseClicked

    private void ctocdfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ctocdfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ctocdfActionPerformed

    private void afromcdfKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_afromcdfKeyReleased
calculationFromCDFA();        // TODO add your handling code here:
    }//GEN-LAST:event_afromcdfKeyReleased

    private void atocdfKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_atocdfKeyReleased
calculationTOCDFA();        // TODO add your handling code here:
    }//GEN-LAST:event_atocdfKeyReleased

    private void bfromcdfKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_bfromcdfKeyReleased
calculationFromCDFB();        // TODO add your handling code here:
    }//GEN-LAST:event_bfromcdfKeyReleased

    private void btocdfKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btocdfKeyReleased
calculationTOCDFB();        // TODO add your handling code here:
    }//GEN-LAST:event_btocdfKeyReleased

    private void cfromcdfKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cfromcdfKeyReleased
calculationFromCDFC();        // TODO add your handling code here:
    }//GEN-LAST:event_cfromcdfKeyReleased

    private void ctocdfKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ctocdfKeyReleased
calculationTOCDFC();        // TODO add your handling code here:
    }//GEN-LAST:event_ctocdfKeyReleased

    private void dfromcdfKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dfromcdfKeyReleased
calculationFromCDFD();        // TODO add your handling code here:
    }//GEN-LAST:event_dfromcdfKeyReleased

    private void afromcdfKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_afromcdfKeyTyped
char c=evt.getKeyChar();
if(!(Character.isDigit(c) || (c==KeyEvent.VK_BACK_SPACE)||c==KeyEvent.VK_DELETE ||c==KeyEvent.VK_PERIOD )){
evt.consume(); 
}        // TODO add your handling code here:
    }//GEN-LAST:event_afromcdfKeyTyped

    private void bfromcdfKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_bfromcdfKeyTyped
char c=evt.getKeyChar();
if(!(Character.isDigit(c) || (c==KeyEvent.VK_BACK_SPACE)||c==KeyEvent.VK_DELETE ||c==KeyEvent.VK_PERIOD )){
evt.consume(); 
}        // TODO add your handling code here:
    }//GEN-LAST:event_bfromcdfKeyTyped

    private void cfromcdfKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cfromcdfKeyTyped
char c=evt.getKeyChar();
if(!(Character.isDigit(c) || (c==KeyEvent.VK_BACK_SPACE)||c==KeyEvent.VK_DELETE ||c==KeyEvent.VK_PERIOD )){
evt.consume(); 
}        // TODO add your handling code here:
    }//GEN-LAST:event_cfromcdfKeyTyped

    private void dfromcdfKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dfromcdfKeyTyped
char c=evt.getKeyChar();
if(!(Character.isDigit(c) || (c==KeyEvent.VK_BACK_SPACE)||c==KeyEvent.VK_DELETE ||c==KeyEvent.VK_PERIOD )){
evt.consume(); 
}        // TODO add your handling code here:
    }//GEN-LAST:event_dfromcdfKeyTyped

    private void atocdfKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_atocdfKeyTyped
char c=evt.getKeyChar();
if(!(Character.isDigit(c) || (c==KeyEvent.VK_BACK_SPACE)||c==KeyEvent.VK_DELETE ||c==KeyEvent.VK_PERIOD )){
evt.consume(); 
}        // TODO add your handling code here:
    }//GEN-LAST:event_atocdfKeyTyped

    private void btocdfKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btocdfKeyTyped
char c=evt.getKeyChar();
if(!(Character.isDigit(c) || (c==KeyEvent.VK_BACK_SPACE)||c==KeyEvent.VK_DELETE ||c==KeyEvent.VK_PERIOD )){
evt.consume(); 
}        // TODO add your handling code here:
    }//GEN-LAST:event_btocdfKeyTyped

    private void ctocdfKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ctocdfKeyTyped
char c=evt.getKeyChar();
if(!(Character.isDigit(c) || (c==KeyEvent.VK_BACK_SPACE)||c==KeyEvent.VK_DELETE ||c==KeyEvent.VK_PERIOD )){
evt.consume(); 
}        // TODO add your handling code here:
    }//GEN-LAST:event_ctocdfKeyTyped

    private void rangeaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_rangeaKeyTyped
char c=evt.getKeyChar();
if(!(Character.isDigit(c) || (c==KeyEvent.VK_BACK_SPACE)||c==KeyEvent.VK_DELETE ||c==KeyEvent.VK_PERIOD )){
evt.consume(); 
}        // TODO add your handling code here:
    }//GEN-LAST:event_rangeaKeyTyped

    private void rangebKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_rangebKeyTyped
char c=evt.getKeyChar();
if(!(Character.isDigit(c) || (c==KeyEvent.VK_BACK_SPACE)||c==KeyEvent.VK_DELETE ||c==KeyEvent.VK_PERIOD )){
evt.consume(); 
}        // TODO add your handling code here:
    }//GEN-LAST:event_rangebKeyTyped

    private void rangecKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_rangecKeyTyped
char c=evt.getKeyChar();
if(!(Character.isDigit(c) || (c==KeyEvent.VK_BACK_SPACE)||c==KeyEvent.VK_DELETE ||c==KeyEvent.VK_PERIOD )){
evt.consume(); 
}        // TODO add your handling code here:
    }//GEN-LAST:event_rangecKeyTyped

    private void rangedKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_rangedKeyTyped
char c=evt.getKeyChar();
if(!(Character.isDigit(c) || (c==KeyEvent.VK_BACK_SPACE)||c==KeyEvent.VK_DELETE ||c==KeyEvent.VK_PERIOD )){
evt.consume(); 
}        // TODO add your handling code here:
    }//GEN-LAST:event_rangedKeyTyped

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
update_IPR();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
saveretenu();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton6ActionPerformed

    private void retenueinssKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_retenueinssKeyTyped
char c=evt.getKeyChar();
if(!(Character.isDigit(c) || (c==KeyEvent.VK_BACK_SPACE)||c==KeyEvent.VK_DELETE ||c==KeyEvent.VK_PERIOD )){
evt.consume(); 
}           // TODO add your handling code here:
    }//GEN-LAST:event_retenueinssKeyTyped

    private void retenueinppKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_retenueinppKeyTyped
char c=evt.getKeyChar();
if(!(Character.isDigit(c) || (c==KeyEvent.VK_BACK_SPACE)||c==KeyEvent.VK_DELETE ||c==KeyEvent.VK_PERIOD )){
evt.consume(); 
}           // TODO add your handling code here:
    }//GEN-LAST:event_retenueinppKeyTyped

    private void retenueonemeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_retenueonemeKeyTyped
char c=evt.getKeyChar();
if(!(Character.isDigit(c) || (c==KeyEvent.VK_BACK_SPACE)||c==KeyEvent.VK_DELETE ||c==KeyEvent.VK_PERIOD )){
evt.consume(); 
}           // TODO add your handling code here:
    }//GEN-LAST:event_retenueonemeKeyTyped

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
saveavantage();          // TODO add your handling code here:
    }//GEN-LAST:event_jButton12ActionPerformed

    private void all_famKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_all_famKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_all_famKeyTyped

    private void transportKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_transportKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_transportKeyTyped

    private void accommodationKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_accommodationKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_accommodationKeyTyped

    private void accouragementKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_accouragementKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_accouragementKeyTyped

    private void priceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_priceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_priceActionPerformed

    private void priceKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_priceKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_priceKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel AAA;
    private javax.swing.JLabel BBB;
    private javax.swing.JLabel CCC;
    private javax.swing.JLabel DDD;
    private javax.swing.JTextField accommodation;
    private javax.swing.JTextField accouragement;
    private javax.swing.JTextField afromcdf;
    private javax.swing.JTextField afromusd;
    private javax.swing.JTextField all_fam;
    private javax.swing.JTextField atocdf;
    private javax.swing.JTextField atousd;
    private javax.swing.JTextField bfromcdf;
    private javax.swing.JTextField bfromusd;
    private javax.swing.JTextField btocdf;
    private javax.swing.JTextField btousd;
    private javax.swing.JTextField cfromcdf;
    private javax.swing.JTextField cfromusd;
    private javax.swing.JTextField ctocdf;
    private javax.swing.JTextField ctousd;
    private javax.swing.JTextField dfromcdf;
    private javax.swing.JTextField dfromusd;
    private javax.swing.JTextField diffa;
    private javax.swing.JTextField diffb;
    private javax.swing.JTextField diffc;
    private javax.swing.JTextField diffd;
    private javax.swing.JTextField dtocdf;
    private javax.swing.JTextField dtousd;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JPanel jPanel34;
    private javax.swing.JPanel jPanel35;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField price;
    private javax.swing.JTextField rangea;
    private javax.swing.JTextField rangeb;
    private javax.swing.JTextField rangec;
    private javax.swing.JTextField ranged;
    private javax.swing.JTextField retenuecnss;
    private javax.swing.JTextField retenueinpp;
    private javax.swing.JTextField retenueinss;
    private javax.swing.JTextField retenueoneme;
    private javax.swing.JTextField sick;
    private javax.swing.JTextField transport;
    // End of variables declaration//GEN-END:variables
}
