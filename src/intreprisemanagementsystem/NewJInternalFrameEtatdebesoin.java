/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intreprisemanagementsystem;

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
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Dosh
 */
public class NewJInternalFrameEtatdebesoin extends javax.swing.JInternalFrame {

Connection con=null;
PreparedStatement pst=null;
ResultSet rs= null;
String etrolls;
DefaultTableModel mode;
    public NewJInternalFrameEtatdebesoin() {
        initComponents();
             con=JavaDbConnect.dbConnect();
        etjComboBox6.addItem("CONSULTANT");
        etjComboBox6.addItem("EMPLOYEE");
        etEMP_NAME_TO_BOMBOBOX2();
          etsup1.setEditable(false);
    etchat.setEditable(false);
    etdate_now();
    etchat.setText(".");
    currency();
    Tableclear();
    attCall_roll();
    }
    public void etdate_now()
    {
       etjDateChooser4.setDate(new Date());
      
    }
   public void etif_nothing()
    {
        if(etchat.getText().equals("")){
            etchat.setText(".                                              ");
            etsearch();
        }
    }
    
    //CONNECTION
    
     
      public void etEMP_NAME_TO_BOMBOBOX2()
    {
         {
        try{
            String sql="select * from  tabe";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                String sum=rs.getString("Nom");
                  etjComboBox6.addItem(sum);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
    }
    }
       public void etcombobox1_from_materiel()
    {
        if(etjComboBox6.getSelectedItem().equals("CONSULTANT")){//&&etjComboBox6.getSelectedItem().equals("EMPLOYE")){
            etsup1.setEditable(true);
            etchat.setEditable(true);
             }else if(etjComboBox6.getSelectedItem().equals("EMPLOYEE")){
                  etsup1.setEditable(false);
            etchat.setEditable(false);
        }else{
     String tmp =(String)etjComboBox6.getSelectedItem();
     String sql="select chantier,sup from  "+tmp+" ";
     
        try{
         
          
            pst=con.prepareStatement(sql);
           // pst.setString(1, tmp);
            rs=pst.executeQuery();
            if(rs.next()){

              String add9 = rs.getString("SUP");
              etsup1.setText(add9);
              String add2 = rs.getString("CHANTIER");
              etchat.setText(add2);

          }
  
      }catch(Exception ex){
        JOptionPane.showMessageDialog(null,ex);  
      }
        }    
    }     
    
       // AUTO ROLL NUMBER
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
      public void calulationS()
         {
             float a = Float.parseFloat(etpu.getText());
             float b = Float.parseFloat(cdf.getText());
             float c;
        
             String d;
      
             c=a/b;
             d= String.format("%.2f",c);
           // fc3.setText(d);
            etpu.setText(d);
             
         }
       public void calulation()
         {
             float a = Float.parseFloat(etpu.getText());
             float b = Float.parseFloat(etqty.getText());
             float c;
        
             String d;
      
             c=a*b;
             d= String.format("%.2f",c);
           // fc3.setText(d);
            etpt.setText(d);
             
         }
       
       //USH
       public void calulationSUSH()
         {
             float a = Float.parseFloat(etpu.getText());
             float b = Float.parseFloat(ush.getText());
             float c;
        
             String d;
      
             c=a/b;
             d= String.format("%.2f",c);
           // fc3.setText(d);
            etpu.setText(d);
             
         }
      
     
      //SAVE
     
      public void etsavema_attendance()
    {
         if(jComboBox2.getSelectedItem().equals("ETAT DE BESOIN POUR LA LOGISTIQUE")){
            
         
         Integer a =0;
           String rollss = etroll1.getText();
        if(rollss.isEmpty()){
       try {
            etroll();
        PreparedStatement pst = con.prepareStatement("INSERT INTO etat_de_besoin(SUP,CHANT,NUM,DET,QTY,PU,PT,DATES,APPROUVATION,EXCECUTE,PRINT,PAY,ORIENTATION) "
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?)");
        
        pst.setString(1, etsup1.getText());
         pst.setString(2, etchat.getText());
        pst.setString(3, etrolls);
         pst.setString(4, etdet.getText());
         pst.setString(5, etqty.getText());
         pst.setString(6, "0");
         pst.setString(7, "0");
         pst.setString(9, app.getText());
          pst.setString(10,"");
          pst.setString(11,"");
          pst.setString(12,"");
            pst.setString(13,"LOGISTIQUE");
       
          SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(etjDateChooser4.getDate());
         pst.setString(8, addDate);
         
          pst.executeUpdate();
        
              //   JOptionPane.showMessageDialog(null,"CLERCK_IN  SUCCESSFUL BONNE JOURNEE!!!        ");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
    }else{
          try {
            etroll();
        PreparedStatement pst = con.prepareStatement("INSERT INTO etat_de_besoin(SUP,CHANT,NUM,DET,QTY,PU,PT,DATES,APPROUVATION,EXCECUTE,PRINT,PAY,ORIENTATION) "
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?)");
        
        pst.setString(1, etsup1.getText());
         pst.setString(2, etchat.getText());
        pst.setString(3, rollss);
         pst.setString(4, etdet.getText());
         pst.setString(5, etqty.getText());
         pst.setString(6, "0");
         pst.setString(7, "0");
         pst.setString(9, app.getText());
          pst.setString(10,"");
          pst.setString(11,"");
          pst.setString(12,"");
           pst.setString(13,"LOGISTIQUE");
       
          SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(etjDateChooser4.getDate());
         pst.setString(8, addDate);
         
          pst.executeUpdate();
        
              //   JOptionPane.showMessageDialog(null,"CLERCK_IN  SUCCESSFUL BONNE JOURNEE!!!        ");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    } 
        }
         
         }else{
         
         
         
         
         
         
            Float a = Float.parseFloat(etqty.getText());
        Float b = Float.parseFloat(etpu.getText());
        //float e = Float.parseFloat(pt.getText());
        Float c;
        String d;
        c=a*b;
        d= String.format("%.2f",c);
        etpt.setText(d);
         
         
         
        if(jComboBox1.getSelectedItem().equals("CDF")){
            calulationS();  
            calulation();
        String rollss = etroll1.getText();
        if(rollss.isEmpty()){
        try {
            etroll();
        PreparedStatement pst = con.prepareStatement("INSERT INTO etat_de_besoin(SUP,CHANT,NUM,DET,QTY,PU,PT,DATES,APPROUVATION,EXCECUTE,PRINT,PAY,ORIENTATION) "
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?)");
        
        pst.setString(1, etsup1.getText());
         pst.setString(2, etchat.getText());
        pst.setString(3, etrolls);
         pst.setString(4, etdet.getText());
         pst.setString(5, etqty.getText());
         pst.setString(6, etpu.getText());
         pst.setString(7, etpt.getText());
         pst.setString(9, app.getText());
          pst.setString(10,"");
          pst.setString(11,"");
          pst.setString(12,"");
           pst.setString(13,"FINANCE");
       
          SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(etjDateChooser4.getDate());
         pst.setString(8, addDate);
         
          pst.executeUpdate();
        
              //   JOptionPane.showMessageDialog(null,"CLERCK_IN  SUCCESSFUL BONNE JOURNEE!!!        ");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
    }else{
            try {
            etroll();
        PreparedStatement pst = con.prepareStatement("INSERT INTO etat_de_besoin(SUP,CHANT,NUM,DET,QTY,PU,PT,DATES,APPROUVATION,EXCECUTE,PRINT,PAY,ORIENTATION) "
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?)");
        
        pst.setString(1, etsup1.getText());
         pst.setString(2, etchat.getText());
        pst.setString(3, etroll1.getText());
         pst.setString(4, etdet.getText());
         pst.setString(5, etqty.getText());
         pst.setString(6, etpu.getText());
         pst.setString(7, etpt.getText());
         pst.setString(9, app.getText());
         
       pst.setString(10,"");
       pst.setString(11,"");
       pst.setString(12,"");
       pst.setString(13,"FINANCE");
          SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(etjDateChooser4.getDate());
         pst.setString(8, addDate);
         
          pst.executeUpdate();
        
              //   JOptionPane.showMessageDialog(null,"CLERCK_IN  SUCCESSFUL BONNE JOURNEE!!!        ");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
            
        }
        }else if(jComboBox1.getSelectedItem().equals("USH")){
         calulationSUSH();  
            calulation();
        String rollss = etroll1.getText();
        if(rollss.isEmpty()){
        try {
            etroll();
        PreparedStatement pst = con.prepareStatement("INSERT INTO etat_de_besoin(SUP,CHANT,NUM,DET,QTY,PU,PT,DATES,APPROUVATION,EXCECUTE,PRINT,PAY,ORIENTATION) "
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?)");
        
        pst.setString(1, etsup1.getText());
         pst.setString(2, etchat.getText());
        pst.setString(3, etrolls);
         pst.setString(4, etdet.getText());
         pst.setString(5, etqty.getText());
         pst.setString(6, etpu.getText());
         pst.setString(7, etpt.getText());
         pst.setString(9, app.getText());
         
       pst.setString(10,"");
       pst.setString(11,"");
       pst.setString(12,"");
       pst.setString(13,"FINANCE");
          SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(etjDateChooser4.getDate());
         pst.setString(8, addDate);
         
          pst.executeUpdate();
        
              //   JOptionPane.showMessageDialog(null,"CLERCK_IN  SUCCESSFUL BONNE JOURNEE!!!        ");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
    }else{
            try {
            etroll();
        PreparedStatement pst = con.prepareStatement("INSERT INTO etat_de_besoin(SUP,CHANT,NUM,DET,QTY,PU,PT,DATES,APPROUVATION,EXCECUTE,PRINT,PAY,ORIENTATION) "
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?)");
        
        pst.setString(1, etsup1.getText());
         pst.setString(2, etchat.getText());
        pst.setString(3, etroll1.getText());
         pst.setString(4, etdet.getText());
         pst.setString(5, etqty.getText());
         pst.setString(6, etpu.getText());
         pst.setString(7, etpt.getText());
         pst.setString(9, app.getText());
         
       pst.setString(10,"");
       pst.setString(11,"");
       pst.setString(12,"");
        pst.setString(13,"FINANCE");
          SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(etjDateChooser4.getDate());
         pst.setString(8, addDate);
         
          pst.executeUpdate();
        
              //   JOptionPane.showMessageDialog(null,"CLERCK_IN  SUCCESSFUL BONNE JOURNEE!!!        ");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
        }
        }else{
        
         String rollss = etroll1.getText();
        if(rollss.isEmpty()){
        try {
            etroll();
        PreparedStatement pst = con.prepareStatement("INSERT INTO etat_de_besoin(SUP,CHANT,NUM,DET,QTY,PU,PT,DATES,APPROUVATION,EXCECUTE,PRINT,PAY,ORIENTATION) "
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?)");
        
        pst.setString(1, etsup1.getText());
         pst.setString(2, etchat.getText());
        pst.setString(3, etrolls);
         pst.setString(4, etdet.getText());
         pst.setString(5, etqty.getText());
         pst.setString(6, etpu.getText());
         pst.setString(7, etpt.getText());
         pst.setString(9, app.getText());
         
       pst.setString(10,"");
       pst.setString(11,"");
       pst.setString(12,"");
        pst.setString(13,"FINANCE");
          SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(etjDateChooser4.getDate());
         pst.setString(8, addDate);
         
          pst.executeUpdate();
        
              //   JOptionPane.showMessageDialog(null,"CLERCK_IN  SUCCESSFUL BONNE JOURNEE!!!        ");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
    }else{
            try {
            etroll();
        PreparedStatement pst = con.prepareStatement("INSERT INTO etat_de_besoin(SUP,CHANT,NUM,DET,QTY,PU,PT,DATES,APPROUVATION,EXCECUTE,PRINT,PAY,ORIENTATION) "
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?)");
        
        pst.setString(1, etsup1.getText());
         pst.setString(2, etchat.getText());
        pst.setString(3, etroll1.getText());
         pst.setString(4, etdet.getText());
         pst.setString(5, etqty.getText());
         pst.setString(6, etpu.getText());
         pst.setString(7, etpt.getText());
         pst.setString(9, app.getText());
         pst.setString(10,"");
         pst.setString(11,"");
       pst.setString(12,"");
        pst.setString(13,"FINANCE");
          SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(etjDateChooser4.getDate());
         pst.setString(8, addDate);
         
          pst.executeUpdate();
        
              //   JOptionPane.showMessageDialog(null,"CLERCK_IN  SUCCESSFUL BONNE JOURNEE!!!        ");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
        }
        }
        } 
    }
      
      // DELETE
    public void etdelete()
    {
         try{
        String sql = "DELETE FROM etat_de_besoin WHERE ID=?";
        
         pst = con.prepareStatement(sql);
         pst.setString(1,etid.getText());
         pst.executeUpdate();

     }catch(SQLException | HeadlessException ex ){
         JOptionPane.showMessageDialog(null,ex);
     } 
    }
      //UPDATE
     public void etupdate_materiaux()
    {
         try{
                        
        String sql = "UPDATE etat_de_besoin SET DET=?,QTY=?,PU=?,PT=? WHERE ID =?";
        
         pst = con.prepareStatement(sql);
         pst.setString(5,etid.getText());
         pst.setString(1,etdet.getText());
          pst.setString(2,etqty.getText());
        pst.setString(3,etpu.getText());
             pst.setString(4,etpt.getText());
             
                       
         pst.executeUpdate();
         JOptionPane.showMessageDialog(null,"updaded");    
     }catch(SQLException | HeadlessException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }
    }        
    
     
       public void etCall_ID_MAX()
    {
         {
        try{
            String sql="select MAX(NUM) from etat_de_besoin ";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                String sum=rs.getString("MAX(NUM)");
                  etroll1.setText(sum);
                  etrecherches.setText(sum);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
    }
    }
        
        
        //SELECT IN JTABLE
        
         public void etselect_jTable()
   {
        try{
          int row= etjTable3.getSelectedRow();
          String Table_click = (etjTable3.getModel().getValueAt(row,0). toString());
          String sql = "SELECT * FROM etat_de_besoin WHERE ID= '"+Table_click+"'";
          pst = con.prepareStatement(sql);
          rs=pst.executeQuery();
          if(rs.next()){
              String add1 = rs.getString("ID");//SUP,CHANT,NUM,DET,QTY,PU,PT,DATES
              etid.setText(add1);
               // String add2 = rs.getString("SUP");
              //sup.setText(add2);
                //String add3 = rs.getString("CHANT");
              //chat.setText(add3);
                //String add4 = rs.getString("NUM");
              //roll.setText(add4);
                //String add5 = rs.getString("DET");
              //det.setText(add5);
                //String add6 = rs.getString("QTY");
              //qty.setText(add6);
               //String add7 = rs.getString("PU");
              //pu.setText(add7);
               //String add8 = rs.getString("PT");
             // pt.setText(add8);
          }
         
    }catch(SQLException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }
   }
        
     //SEARCH AND ADDREADER
     
      public void etReadData(String sql)
    {
            Statement st;
            try{
                
                st=con.createStatement();
                ResultSet rs;
                rs=st.executeQuery(sql);
                if(rs.first()){
                    String Data[]=new String[9];
                    do{
                        for(int i=0;i<9;i++){
                            Data[i]=rs.getString(i+1);
                        }
                    
                    mode.addRow(Data);
                }while(rs.next());
            }
                rs.close();
            }catch(Exception ex){
                
            }
    }
    
    public void etAddModel(){
        mode=new DefaultTableModel();
        mode.addColumn("ID");    
        mode.addColumn("NOM DE SUPERVISEUR");
        mode.addColumn("CHATIER");
        mode.addColumn("NUMERO EB");
        mode.addColumn("DETAILS");
        mode.addColumn("QTY");
        mode.addColumn("PU");
        mode.addColumn("PT");
        mode.addColumn("DATE");
        etjTable3.setModel(mode);
        
        
        TableColumn col0=etjTable3.getColumnModel().getColumn(0);
        TableColumn col1=etjTable3.getColumnModel().getColumn(1);
        TableColumn col2=etjTable3.getColumnModel().getColumn(2);
        TableColumn col3=etjTable3.getColumnModel().getColumn(3);
        TableColumn col4=etjTable3.getColumnModel().getColumn(4);
        TableColumn col5=etjTable3.getColumnModel().getColumn(5);
        TableColumn col6=etjTable3.getColumnModel().getColumn(6);
        TableColumn col7=etjTable3.getColumnModel().getColumn(7);
        TableColumn col9=etjTable3.getColumnModel().getColumn(8);
       
       col0.setPreferredWidth(2);
       col1.setPreferredWidth(130);
       col2.setPreferredWidth(50);
       col3.setPreferredWidth(50);
       col4.setPreferredWidth(130);
       col5.setPreferredWidth(2);
       col6.setPreferredWidth(2);
       col7.setPreferredWidth(2);
       col9.setPreferredWidth(50);
    }
      
      public void etremove(){
          while(etjTable3.getRowCount()>0){
              mode.removeRow(0);
          }
      }
              
             public void etsearch()
             {
                  String st=etchat.getText().trim();
    etremove();
    etReadData("select * from etat_de_besoin where NUM = '"+st+"'");
   
             }
             
              public void etsearchS()
             {
                  String st=etrecherche.getText().trim();
    etremove();
    etReadData("select * from etat_de_besoin where NUM like '"+st+"%'");
   
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

 public void attCall_roll()
    {
      
         String A =JOptionPane.showInputDialog("Please Entre Your Roll Number!!!"); 
        try{
            String sql="select * from Active_employee where ROLL= ? and Locks='Active'";
            
           
             pst=con.prepareStatement(sql);
            pst.setString(1, A);
            rs=pst.executeQuery();
            if(rs.next()){
             String add1 = rs.getString("NOMS");
             String add2 = rs.getString("LNAME");
              etsup1.setText(add1+" "+add2);
             
             
              
              
              
               try{
            String sqls="select * from employee where ROLLNUM= ?";
            
           
             pst=con.prepareStatement(sqls);
            pst.setString(1, A);
            rs=pst.executeQuery();
            if(rs.next()){
             String add11 = rs.getString("DEPARTMENT");
           etchat.setText(add11);
             
             
              // String add4 = rs.getString("ROLL");
              // etroll1.setText(add4);
            }
            
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
              
              
              
              
              
              
              
              // String add4 = rs.getString("ROLL");
              // etroll1.setText(add4);
            }else{JOptionPane.showMessageDialog(null, "Invalid Roll No. or Does not Have Info");  
            
              attCall_roll();
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
   
    }   

public void Table()
    {
         try{
           
             String sql="SELECT ID,`DET` as 'Description', `QTY` AS 'Quantity', `PU` AS 'Unity By Price', `PT` as 'Total Price', `DATES` as 'Delivery Date' FROM `etat_de_besoin` WHERE `NUM`='"+etroll1.getText()+"'";
     //  String sql="SELECT ID, `NUM_FACTURE`, `DATE_FACTURE`, `DESCRIPTION`, `DEBIT_QTY`, `DEBIT_PU`, `DEBIT_PT`, `DEBIT_TVA`, `CREDIT_QTY`, `CREDIT_PU`, `CREDIT_PT`, `CREDIT_TVA`, `DATES`, `NUM` FROM facture_fournisseur where compte='"+jComboBox6.getSelectedItem()+"'";
       pst = con.prepareStatement(sql);
      rs= pst.executeQuery();
      
       
      etjTable3.setModel(DbUtils.resultSetToTableModel(rs));
       mode=new DefaultTableModel();
       
       
        TableColumn col0=etjTable3.getColumnModel().getColumn(0);
        TableColumn col1=etjTable3.getColumnModel().getColumn(1);
        TableColumn col2=etjTable3.getColumnModel().getColumn(2);
        TableColumn col3=etjTable3.getColumnModel().getColumn(3);
        TableColumn col4=etjTable3.getColumnModel().getColumn(4);
        TableColumn col5=etjTable3.getColumnModel().getColumn(5);
      //  TableColumn col5=etjTable3.getColumnModel().getColumn(5);

       
      
       col0.setPreferredWidth(10);
       col1.setPreferredWidth(350);
       col2.setPreferredWidth(50);
       col3.setPreferredWidth(50);
       col4.setPreferredWidth(50);
       col5.setPreferredWidth(50);
      // col5.setPreferredWidth(50);
      
      
      
       
       
      // jTable1.setModel(mode);
       
     
    }catch(SQLException ex ){
     JOptionPane.showMessageDialog(null, ex);
}
    } 
 public void Tableclear()
    {
         try{
           
             String sql="SELECT `DET` as 'Description', `QTY` AS 'Quantity', `PU` AS 'Unity By Price', `PT` as 'Total Price', `DATES` as 'Delivery Date' FROM `etat_de_besoin` WHERE `NUM`='xxxx'";
     //  String sql="SELECT ID, `NUM_FACTURE`, `DATE_FACTURE`, `DESCRIPTION`, `DEBIT_QTY`, `DEBIT_PU`, `DEBIT_PT`, `DEBIT_TVA`, `CREDIT_QTY`, `CREDIT_PU`, `CREDIT_PT`, `CREDIT_TVA`, `DATES`, `NUM` FROM facture_fournisseur where compte='"+jComboBox6.getSelectedItem()+"'";
       pst = con.prepareStatement(sql);
      rs= pst.executeQuery();
      
       
      etjTable3.setModel(DbUtils.resultSetToTableModel(rs));
       mode=new DefaultTableModel();
       
       
        TableColumn col0=etjTable3.getColumnModel().getColumn(0);
        TableColumn col1=etjTable3.getColumnModel().getColumn(1);
        TableColumn col2=etjTable3.getColumnModel().getColumn(2);
        TableColumn col3=etjTable3.getColumnModel().getColumn(3);
        TableColumn col4=etjTable3.getColumnModel().getColumn(4);
      //  TableColumn col5=etjTable3.getColumnModel().getColumn(5);

       
      
       
       col0.setPreferredWidth(350);
       col1.setPreferredWidth(50);
       col2.setPreferredWidth(50);
       col3.setPreferredWidth(50);
       col4.setPreferredWidth(50);
      // col5.setPreferredWidth(50);
      
      
      
       
       
      // jTable1.setModel(mode);
       
     
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
        etjComboBox6 = new javax.swing.JComboBox<>();
        etsup1 = new javax.swing.JTextField();
        etchat = new javax.swing.JTextField();
        etroll1 = new javax.swing.JTextField();
        etjDateChooser4 = new com.toedter.calendar.JDateChooser();
        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        etjTable3 = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        etdet = new javax.swing.JEditorPane();
        etremove = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        etadd = new javax.swing.JButton();
        etqty = new javax.swing.JTextField();
        etpu = new javax.swing.JTextField();
        etpt = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jTextField1 = new javax.swing.JTextField();
        usd = new javax.swing.JTextField();
        cdf = new javax.swing.JTextField();
        ush = new javax.swing.JTextField();
        app = new javax.swing.JTextField();
        select = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        etrecherche = new javax.swing.JTextField();
        etid = new javax.swing.JTextField();
        etrecherches = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();

        setClosable(true);
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

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        etjComboBox6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        etjComboBox6.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                etjComboBox6PopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        etsup1.setBackground(new java.awt.Color(204, 204, 255));
        etsup1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        etsup1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                etsup1KeyReleased(evt);
            }
        });

        etchat.setBackground(new java.awt.Color(204, 204, 255));
        etchat.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        etchat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                etchatActionPerformed(evt);
            }
        });
        etchat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                etchatKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                etchatKeyTyped(evt);
            }
        });

        etroll1.setEditable(false);
        etroll1.setBackground(new java.awt.Color(204, 204, 255));
        etroll1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        etjDateChooser4.setAutoscrolls(true);
        etjDateChooser4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton1.setText("...");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(etsup1)
                    .addComponent(etchat)
                    .addComponent(etroll1)
                    .addComponent(etjDateChooser4, javax.swing.GroupLayout.DEFAULT_SIZE, 292, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(etjComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(etjComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(1, 1, 1)
                .addComponent(etsup1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(etchat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(etroll1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(etjDateChooser4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        etjTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        etjTable3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                etjTable3MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(etjTable3);

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        etdet.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jScrollPane1.setViewportView(etdet);

        etremove.setBackground(new java.awt.Color(255, 51, 51));
        etremove.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        etremove.setText("REMOVE");
        etremove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                etremoveActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("QTY");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("PT");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("PU");

        etadd.setBackground(new java.awt.Color(51, 255, 51));
        etadd.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        etadd.setText("ADD");
        etadd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                etaddActionPerformed(evt);
            }
        });

        etqty.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        etqty.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                etqtyKeyTyped(evt);
            }
        });

        etpu.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        etpu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                etpuKeyTyped(evt);
            }
        });

        etpt.setEditable(false);
        etpt.setBackground(new java.awt.Color(204, 204, 255));
        etpt.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jComboBox1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "USD", "CDF", "USH" }));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Monnaies");

        jComboBox2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ETAT DE BESOIN POUR LA FINANCE", "ETAT DE BESOIN POUR LA LOGISTIQUE" }));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(etqty, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(etpu, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(etpt, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(etadd, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(etremove, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(jLabel1)
                        .addGap(48, 48, 48)
                        .addComponent(jLabel4)
                        .addGap(50, 50, 50)
                        .addComponent(jLabel3)
                        .addGap(107, 107, 107)
                        .addComponent(jLabel2))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(2, 2, 2))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(etremove, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(etadd, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(etqty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(etpu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(etpt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(jLabel2)
                        .addComponent(jLabel1)
                        .addComponent(jLabel4))))
        );

        jTextField1.setEditable(false);
        jTextField1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jTextField1.setText("                                                                                  ETAT DE BESOIN");

        usd.setEditable(false);
        usd.setForeground(new java.awt.Color(240, 240, 240));
        usd.setBorder(null);

        cdf.setEditable(false);
        cdf.setForeground(new java.awt.Color(240, 240, 240));
        cdf.setBorder(null);

        ush.setEditable(false);
        ush.setForeground(new java.awt.Color(240, 240, 240));
        ush.setBorder(null);

        app.setEditable(false);
        app.setForeground(new java.awt.Color(240, 240, 240));
        app.setBorder(null);

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton2.setText("Send");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        etrecherche.setEditable(false);
        etrecherche.setForeground(new java.awt.Color(240, 240, 240));
        etrecherche.setBorder(null);
        etrecherche.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                etrechercheKeyReleased(evt);
            }
        });

        etid.setEditable(false);
        etid.setForeground(new java.awt.Color(240, 240, 240));
        etid.setBorder(null);

        etrecherches.setEditable(false);
        etrecherches.setForeground(new java.awt.Color(240, 240, 240));
        etrecherches.setBorder(null);
        etrecherches.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                etrecherchesKeyReleased(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(255, 0, 0));
        jButton3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton3.setText("X");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(170, 170, 170)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane2)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(usd)
                    .addComponent(cdf)
                    .addComponent(ush)
                    .addComponent(select, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
                    .addComponent(etrecherches)
                    .addComponent(etrecherche)
                    .addComponent(etid)
                    .addComponent(app))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 1256, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(9, 9, 9)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(etrecherches, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(usd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cdf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ush, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(etrecherche, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(etid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(app, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(select, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2)
                .addGap(0, 44, Short.MAX_VALUE))
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
    }// </editor-fold>//GEN-END:initComponents

    private void etjComboBox6PopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_etjComboBox6PopupMenuWillBecomeInvisible

        etroll1.setText("");
        etcombobox1_from_materiel();
        etsearch();// TODO add your handling code here:
    }//GEN-LAST:event_etjComboBox6PopupMenuWillBecomeInvisible

    private void etsup1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_etsup1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_etsup1KeyReleased

    private void etchatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_etchatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_etchatActionPerformed

    private void etchatKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_etchatKeyReleased
        etsearch();        // TODO add your handling code here:
    }//GEN-LAST:event_etchatKeyReleased

    private void etjTable3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_etjTable3MouseClicked
        etselect_jTable();
        // TODO add your handling code here:
    }//GEN-LAST:event_etjTable3MouseClicked

    private void etremoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_etremoveActionPerformed
        etdelete();
        Table();// TODO add your handling code here:
    }//GEN-LAST:event_etremoveActionPerformed

    private void etaddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_etaddActionPerformed
   etsavema_attendance();

        etCall_ID_MAX();
        
       Table();
        etdet.setText("");
        etqty.setText("");
        etpu.setText("");
        etpt.setText("0");
        // TODO add your handling code here:
    }//GEN-LAST:event_etaddActionPerformed

    private void etqtyKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_etqtyKeyTyped
        char c=evt.getKeyChar();
        if(!(Character.isDigit(c) || (c==KeyEvent.VK_BACK_SPACE)||c==KeyEvent.VK_DELETE ||c==KeyEvent.VK_PERIOD )){
            evt.consume();
        }        // TODO add your handling code here:
    }//GEN-LAST:event_etqtyKeyTyped

    private void etpuKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_etpuKeyTyped
        char c=evt.getKeyChar();
        if(!(Character.isDigit(c) || (c==KeyEvent.VK_BACK_SPACE)||c==KeyEvent.VK_DELETE ||c==KeyEvent.VK_PERIOD )){
            evt.consume();
        }        // TODO add your handling code here:
    }//GEN-LAST:event_etpuKeyTyped

    private void etrechercheKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_etrechercheKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_etrechercheKeyReleased

    private void etrecherchesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_etrecherchesKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_etrecherchesKeyReleased

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
  // etAddModel();
//etReadData("select * from etat_de_besoin");
  //      etsearch();        // TODO add your handling code here:
    }//GEN-LAST:event_formInternalFrameOpened

    private void etchatKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_etchatKeyTyped
       // TODO add your handling code here:
    }//GEN-LAST:event_etchatKeyTyped

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
String tmp = jComboBox2.getSelectedItem().toString();    
select.setText(tmp);

if(tmp.equals("ETAT DE BESOIN POUR LA LOGISTIQUE")){
    jComboBox1.setEnabled(false);
etpu.setEnabled(false);
}else{ jComboBox1.setEnabled(true);
etpu.setEnabled(true);}


// TODO add your handling code here:
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
attCall_roll();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        int response = JOptionPane.showConfirmDialog(null, "DO YOU WANT TO SEND THIS FOR APPROUVEMENT?", "Confirm",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (response == JOptionPane.YES_OPTION) {
etsup1.setText("");
etchat.setText("");
etroll1.setText("");
select.setText("");
              Tableclear();
            }else{}
            attCall_roll(); 
            
            // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField app;
    private javax.swing.JTextField cdf;
    private javax.swing.JButton etadd;
    private javax.swing.JTextField etchat;
    private javax.swing.JEditorPane etdet;
    private javax.swing.JTextField etid;
    private javax.swing.JComboBox<String> etjComboBox6;
    private com.toedter.calendar.JDateChooser etjDateChooser4;
    private javax.swing.JTable etjTable3;
    private javax.swing.JTextField etpt;
    private javax.swing.JTextField etpu;
    private javax.swing.JTextField etqty;
    private javax.swing.JTextField etrecherche;
    private javax.swing.JTextField etrecherches;
    private javax.swing.JButton etremove;
    private javax.swing.JTextField etroll1;
    private javax.swing.JTextField etsup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField select;
    private javax.swing.JTextField usd;
    private javax.swing.JTextField ush;
    // End of variables declaration//GEN-END:variables
}
