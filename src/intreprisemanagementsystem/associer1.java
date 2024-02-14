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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author DOSHE_PC
 */
public class associer1 extends javax.swing.JInternalFrame {

    DefaultTableModel mode;
Connection con=null;
PreparedStatement pst=null;
ResultSet rs= null;
    public associer1() {
        initComponents();
              con=JavaDbConnect.dbConnect();
        Call_ID_TO_BOMBOBOX2();
         pour.setHorizontalAlignment(pour.CENTER);
         pour1.setHorizontalAlignment(pour1.CENTER);
         nbre.setHorizontalAlignment(nbre.CENTER);
         capital.setHorizontalAlignment(capital.CENTER);
         amount.setHorizontalAlignment(amount.CENTER);
         pu.setHorizontalAlignment(pu.CENTER);
         qty.setHorizontalAlignment(qty.CENTER);
         pt.setHorizontalAlignment(pt.CENTER);
       pour3.setHorizontalAlignment(pour3.CENTER);
       jTextField11.setHorizontalAlignment(jTextField11.CENTER);
       jTextField1.setHorizontalAlignment(jTextField1.CENTER);
       jDateChooser3.setDate(new Date());
       
       Groupe1();
    }
    
    public void save(){
try{
             String sql="INSERT INTO `associer1`(`BUSNESS`, `NBR`, `CAPITAL`, `CURRENCY`, `DATES`)"+
                      "values(?,?,?,?,?)";
           
             pst=con.prepareStatement(sql);
             
    pst.setString(1,busss.getText()+"  B");
    pst.setString(2,nbre.getText());
    pst.setString(3,capital.getText());
    pst.setString(4,jComboBox1.getSelectedItem().toString());
    
    SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
    String addDate = dateFormats.format(new Date());
    pst.setString(5, addDate);
    pst.executeUpdate();
    
    JOptionPane.showMessageDialog(null, "Saved");
}catch(Exception e){JOptionPane.showMessageDialog(null, e); }



}
    
     public void save1(){
try{
             String sql="INSERT INTO `tableassocier`(`NOM`, `NATURE`, `ADRESS`, `TELPHONE`, `QTY`, `PU`, `PT`, `BUSS`,DATES)"+
                      "values(?,?,?,?,?,?,?,?,?)";
           
             pst=con.prepareStatement(sql);
             
    pst.setString(1,nom.getText());
    pst.setString(2,"");
    pst.setString(3,adress.getText());
    pst.setString(4,tel.getText());
    
    
    pst.setString(5,"0");
    pst.setString(6,"0");
    pst.setString(7,amount.getText());
   
    pst.setString(8,busss.getText());
      SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
         String addDate1 = dateFormat1.format(jDateChooser3.getDate());
         pst.setString(9, addDate1);
    pst.executeUpdate();
    
    JOptionPane.showMessageDialog(null, "Saved");
}catch(Exception e){JOptionPane.showMessageDialog(null, e); }


//INSERT INTO INTERMEDIATE

try{
             String sql="INSERT INTO `tableassocier_inter`(`NOM`, `PT`, `BUSS`)"+
                      "values(?,?,?)";
           
             pst=con.prepareStatement(sql);
             
    pst.setString(1,nom.getText());
       pst.setString(2,amount.getText());
    pst.setString(3,busss.getText());
    pst.executeUpdate();
    
    JOptionPane.showMessageDialog(null, "Saved");
}catch(HeadlessException | SQLException e){JOptionPane.showMessageDialog(null, e); }




}
     
         public void save2(){
try{
             String sql="INSERT INTO `tableassocier`(`NOM`, `NATURE`, `ADRESS`, `TELPHONE`, `QTY`, `PU`, `PT`, `BUSS`,DATES)"+
                      "values(?,?,?,?,?,?,?,?,?)";
           
             pst=con.prepareStatement(sql);
             
    pst.setString(1,noms.getText());
    pst.setString(2,nat.getText());
    pst.setString(3,addresss.getText());
    pst.setString(4,tels.getText());
    
    
    pst.setString(5,qty.getText());
    pst.setString(6,pu.getText());
    pst.setString(7,pt.getText());
   
    pst.setString(8,busss.getText());
    SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
         String addDate1 = dateFormat1.format(jDateChooser3.getDate());
         pst.setString(9, addDate1);
    pst.executeUpdate();
    
    JOptionPane.showMessageDialog(null, "Saved");
}catch(Exception e){JOptionPane.showMessageDialog(null, e); }


//INSERT INTO INTERMEDIATE

try{
             String sql="INSERT INTO `tableassocier_inter`(`NOM`, `PT`, `BUSS`)"+
                      "values(?,?,?)";
           
             pst=con.prepareStatement(sql);
             
    pst.setString(1,noms.getText());
       pst.setString(2,pt.getText());
    pst.setString(3,busss.getText());
    pst.executeUpdate();
    
    JOptionPane.showMessageDialog(null, "Saved");
}catch(HeadlessException | SQLException e){JOptionPane.showMessageDialog(null, e); }





}
         
          public void savePOUR(){
              //Integer a = Integer.parseInt(sup.getText())
              
                double a = Float.parseFloat(capital.getText());
            double b = Float.parseFloat(amount.getText());
            double c;// = Math.floor(a*b *100.0) / 100.0;
           String d;
            c=(b*100)/a;
             d= String.format("%.1f",c);
//           sh.setText(d);
try{
             String sql="INSERT INTO `associer_pourcentage`(`NOM`,`ADRESS`,`TEL`,`POURCENTAGE`,`BUSS`)"+
                      "values(?,?,?,?,?)";
           
             pst=con.prepareStatement(sql);
             
    pst.setString(1,nom.getText());
    pst.setString(2,adress.getText());
    pst.setString(3,tel.getText());
    pst.setString(4,d);
   //pst.setDouble(4,c);
    pst.setString(5,busss.getText());
    pst.executeUpdate();
    
    JOptionPane.showMessageDialog(null, "Saved");
}catch(Exception e){JOptionPane.showMessageDialog(null, e); }
}
         
          
           public void savePOUR1(){
              
                double a = Float.parseFloat(capital.getText());
            double b = Float.parseFloat(pt.getText());
            double c;// = Math.floor(a*b *100.0) / 100.0;
           
            String d;
            c=(b*100)/a;
             d= String.format("%.1f",c);
//           sh.setText(d);
try{
             String sql="INSERT INTO `associer_pourcentage`(`NOM`,`ADRESS`,`TEL`,`POURCENTAGE`,`BUSS`)"+
                      "values(?,?,?,?,?)";
           
             pst=con.prepareStatement(sql);
             
    pst.setString(1,noms.getText());
    pst.setString(2,addresss.getText());
    pst.setString(3,tels.getText());
    pst.setString(4,d);
    pst.setString(5,busss.getText());
    pst.executeUpdate();
    
    JOptionPane.showMessageDialog(null, "Saved");
}catch(Exception e){JOptionPane.showMessageDialog(null, e); }
}
         
           
           
           //RENFORCEMENT DE POURCENTAGE
           
           
           
             public void saveQ(){

                 
            try{
             String sql="INSERT INTO `tableassocier`(`NOM`, `NATURE`, `ADRESS`, `TELPHONE`, `QTY`, `PU`, `PT`, `BUSS`,DATES)"+
                      "values(?,?,?,?,?,?,?,?,?)";
           
             pst=con.prepareStatement(sql);
             
    pst.setString(1,nom.getText());
    pst.setString(2,"");
    pst.setString(3,adress.getText());
    pst.setString(4,tel.getText());
    
    
    pst.setString(5,"0");
    pst.setString(6,"0");
    pst.setString(7,amount.getText());
   
    pst.setString(8,busss.getText());
      SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
         String addDate1 = dateFormat1.format(jDateChooser3.getDate());
         pst.setString(9, addDate1);
    pst.executeUpdate();
    
    JOptionPane.showMessageDialog(null, "Saved");
}catch(Exception e){JOptionPane.showMessageDialog(null, e); }     
 
            //UPTADE TABLE ASSOCIER_INTER
            
            
            
                 try{
       
                 Float add1=null;  
                 Float Amount = Float.parseFloat(amount.getText());
                     try{
    String tmp =(String)jComboBox4.getSelectedItem();
     String sql="select PT from tableassocier_inter where NOM='"+nom.getText()+"'and BUSS='"+tmp+"'";
          
            pst=con.prepareStatement(sql);
           // pst.setString(1, tmp);
            rs=pst.executeQuery();
            if(rs.next()){
              
               add1 = rs.getFloat("PT");
                 
          }  
      }catch(Exception ex){
        JOptionPane.showMessageDialog(null,ex);}
                     
                     
                     
  // String sql =          UPDATE employee SET NAME=?,LNAME=?,ADRESSE=?,DOB=?,ROLLNUM=?,TITRE=?,TEL=?,EMAIL=?,BANKACC=?,DEGREE=?,NATIONALITE=?,DATEIN=?,SUP=?,ETAT_CIVIL=?,PROFFESSION=?,NOMS=?,ADRESSE1=?,RELATION=?,TEL1=?,TEL2=?,EMAIL1=?,SALARY=?,STATUT=?,DEPARTEMENT=?,DATE_OUT=? WHERE ID=?
             String sqlUP = "UPDATE tableassocier_inter SET PT =? WHERE NOM='"+nom.getText()+"'";
        
         pst = con.prepareStatement(sqlUP);
         
         pst.setFloat(1,Amount+add1);
         
      
                     
         pst.executeUpdate();
         JOptionPane.showMessageDialog(null,"updaded");    
     }catch(Exception  ex ){
         JOptionPane.showMessageDialog(null,ex);}
            

//SELECT SOMME OF CAPITAL
try{
    String tmp =(String)jComboBox4.getSelectedItem();
     String sql="select SUM(PT) from tableassocier where BUSS='"+tmp+"'";
          
            pst=con.prepareStatement(sql);
           // pst.setString(1, tmp);
            rs=pst.executeQuery();
            if(rs.next()){
              
              String add1 = rs.getString("SUM(PT)");
              capital.setText(add1);       
          }  
      }catch(Exception ex){
        JOptionPane.showMessageDialog(null,ex);}


//POURCENTAGE REEL


try{
    String tmp =(String)jComboBox4.getSelectedItem();
     String sql="select * from tableassocier_inter where BUSS='"+tmp+"'";
          
            pst=con.prepareStatement(sql);
           // pst.setString(1, tmp);
            rs=pst.executeQuery();
            while(rs.next()){
              
              String add1 = rs.getString("NOM");
               String add2 = rs.getString("PT");
                String add3 = rs.getString("BUSS");
                
                
                 float a = Float.parseFloat(capital.getText());
                  float b = Float.parseFloat(add2);
            float c;
            c=(b*100)/a;
          try{
             
  // String sql =          UPDATE employee SET NAME=?,LNAME=?,ADRESSE=?,DOB=?,ROLLNUM=?,TITRE=?,TEL=?,EMAIL=?,BANKACC=?,DEGREE=?,NATIONALITE=?,DATEIN=?,SUP=?,ETAT_CIVIL=?,PROFFESSION=?,NOMS=?,ADRESSE1=?,RELATION=?,TEL1=?,TEL2=?,EMAIL1=?,SALARY=?,STATUT=?,DEPARTEMENT=?,DATE_OUT=? WHERE ID=?
             String sqlUP = "UPDATE associer_pourcentage SET POURCENTAGE =? WHERE NOM='"+add1+"'";
        
         pst = con.prepareStatement(sqlUP);
         
         pst.setFloat(1,c);
         
      
                     
         pst.executeUpdate();
         JOptionPane.showMessageDialog(null,"updaded");    
     }catch(Exception  ex ){
         JOptionPane.showMessageDialog(null,ex);
     }
                
                    
          }  
      }catch(Exception ex){
        JOptionPane.showMessageDialog(null,ex);}


//INSERT INTO BUZNESS TRANSACTION

try{
             String sql="INSERT INTO `business_transaction`(`LIBELLE`, `CAPITAL`, `SORTIE`, `DATE`, `BUSS`, `USED`, `NUM`, `ENTRE`)"+
                      "values(?,?,?,?,?,?,?,?)";
           
             pst=con.prepareStatement(sql);
             
    pst.setString(1,"Capital Add");
       pst.setString(2,amount.getText());
    pst.setString(3,"0");
     pst.setString(5,busss.getText());
       pst.setString(6,"0");
    pst.setString(7,"");
    pst.setString(8,amount.getText());
     SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
         String addDate1 = dateFormat1.format(jDateChooser3.getDate());
         pst.setString(4, addDate1);
    pst.executeUpdate();
    
    JOptionPane.showMessageDialog(null, "Saved");
}catch(HeadlessException | SQLException e){JOptionPane.showMessageDialog(null, e); }



//SELECT TABLE



         try{
        
        // String sqls="SELECT `NOM` as 'NAME',`MONT` AS 'AMOUNT' FROM `associer_pourcentage` WHERE BUS ='"+busss.getText()+"'";
          String sqls="SELECT DATES as'DATE', PT as'TM_GIVEN' FROM  tableassocier WHERE  BUSS='"+jComboBox4.getSelectedItem()+"' and NOM='"+nom.getText()+"'";
          pst = con.prepareStatement(sqls);
        rs= pst.executeQuery();
        jTable3.setModel(DbUtils.resultSetToTableModel(rs));
        
        
        
        //  TableColumn col0=jTable3.getColumnModel().getColumn(0);
        //TableColumn col1=jTable3.getColumnModel().getColumn(1);
       
        // col0.setPreferredWidth();
       //col1.setPreferredWidth(50);  
      }catch(Exception ex ){
      JOptionPane.showMessageDialog(null, ex);}   
}
         
    
      public void Call_ID_TO_BOMBOBOX2()
    {  
        try{
            String sql="select * from associer1";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                String sum=rs.getString("BUSNESS");
//                  jComboBox3.addItem(sum);
                 jComboBox4.addItem(sum);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
    }
      
       public void combobox_from_materiel()
    {
        
     String tmp =(String)jComboBox4.getSelectedItem();
     String sql="select * from associer1 where BUSNESS=?";
     
        try{
          
            pst=con.prepareStatement(sql);
            pst.setString(1, tmp);
            rs=pst.executeQuery();
            if(rs.next()){
              
              String add1 = rs.getString("BUSNESS");
              busss.setText(add1);
               String add2 = rs.getString("NBR");
              nbre.setText(add2);
               //String add4 = rs.getString("CAPITAL");
              //capital.setText(add4);
               String add7 = rs.getString("CURRENCY");
             jComboBox1.setSelectedItem(add7);
               
          }   
            
      }catch(Exception ex){
        JOptionPane.showMessageDialog(null,ex); }
        
      
       
         
     String sqls="select sum(PT) from tableassocier_inter where BUSS=?";
      String add4=null;
        try{
          
            pst=con.prepareStatement(sqls);
            pst.setString(1, tmp);
            rs=pst.executeQuery();
            if(rs.next()){
              add4 = rs.getString("SUM(PT)");
              //capital.setText(add4);
               }   
            
      }catch(Exception ex){
        JOptionPane.showMessageDialog(null,ex); }
        if(add4==null){
        
        try{
          
            pst=con.prepareStatement(sql);
            pst.setString(1, tmp);
            rs=pst.executeQuery();
            if(rs.next()){
               String add44 = rs.getString("CAPITAL");
              capital.setText(add44);
               }   
            
      }catch(Exception ex){
        JOptionPane.showMessageDialog(null,ex); }
        
        }else{capital.setText(add4);} 
         
          
    }     
       
       
      public void calulation2()
         {
             float a = Float.parseFloat(pu.getText());
            float b = Float.parseFloat(qty.getText());
            float c;// = Math.floor(a*b *100.0) / 100.0;
           String d;
            c=a*b;
            d= String.format("%.2f",c);
            pt.setText(d);
         }  
       
       
       public void show_in_table(){

  try{
        
         String sql="SELECT NOM as'MANE',POURCENTAGE as '%' FROM associer_pourcentage where BUSS ='"+busss.getText()+"' order by POURCENTAGE";
          pst = con.prepareStatement(sql);
        rs= pst.executeQuery();
        jTable1.setModel(DbUtils.resultSetToTableModel(rs));
        
        
        
          TableColumn col0=jTable1.getColumnModel().getColumn(0);
        TableColumn col1=jTable1.getColumnModel().getColumn(1);
        
          
        
         col0.setPreferredWidth(200);
       col1.setPreferredWidth(50);
      
        
        
        
        
      }catch(Exception ex ){
      JOptionPane.showMessageDialog(null, ex);
}


}
       
       public void show_pour(){
       
       float a = Float.parseFloat(capital.getText());
           // float b = Float.parseFloat(qty.getText());
            float c;
            float cc;
            float add1;
             String d;
              String dd;
        String sql="select sum(POURCENTAGE) from associer_pourcentage where BUSS='"+busss.getText()+"'";
     
        try{
          
            pst=con.prepareStatement(sql);
          //  pst.setString(1, tmp);
            rs=pst.executeQuery();
            if(rs.next()){
              
               add1 = rs.getFloat("sum(POURCENTAGE)");
              pour.setText(add1+"  %");
             // pour3.setText(add1);
             
             // = Math.floor(a*b *100.0) / 100.0;
          
            c=100-add1;
            d= String.format("%.2f",c);
            pour3.setText(d +" %");
            
            cc=a*c/100;
            dd=String.format("%.0f",cc);
            pour1.setText(dd+"  $");
            
          }
            
      }catch(Exception ex){
        JOptionPane.showMessageDialog(null,ex);  
      }
        
       }
       
       //Pay associer
       
       public void select_from_pour(){
       
        String sql="select NOM,POURCENTAGE  from associer_pourcentage where BUSS='"+busss.getText()+"'";
     
        try{
          
            pst=con.prepareStatement(sql);
          //  pst.setString(1, tmp);
            rs=pst.executeQuery();
            if(rs.next()){
              
               String add1 = rs.getString("NOM");
              pour.setText(add1);
              String add2 = rs.getString("POURCENTAGE");
              pour.setText(add2);
           
            
          }
            
      }catch(Exception ex){
        JOptionPane.showMessageDialog(null,ex);  
      }
       
       
       }
       
       public void whilee(){
           
       String sql="select * from associer_pourcentage where BUSS='"+busss.getText()+"'";    
        try {
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
            
             String sum=rs.getString("NOM");
             Float sum1=rs.getFloat("POURCENTAGE");
             String sum2=rs.getString("BUSS");
            
              float a = Float.parseFloat(jTextField11.getText());
            //float b = Float.parseFloat(sum1);
            float c;// = Math.floor(a*b *100.0) / 100.0;
          // String d;
            c=(a/100)*sum1;
          //  d= String.format("%.2f",c);
           // pt.setText(d);
             
             
             

             String sqls="INSERT INTO `associer_pourcentage_paye`(`NOM`, `POUR`, `MONT`, `BUS`)"+
                      "values(?,?,?,?)";
           
             pst=con.prepareStatement(sqls);
             
    pst.setString(1,sum);
    pst.setFloat(2,sum1);
    pst.setFloat(3,c);
    pst.setString(4,sum2);
    pst.executeUpdate();
            
            
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(associer1.class.getName()).log(Level.SEVERE, null, ex);
        }
          //  pst.setString(1, tmp);
         try{
        
         String sqls="SELECT `NOM` as 'NAME',`MONT` AS 'AMOUNT' FROM `associer_pourcentage_paye` WHERE BUS ='"+busss.getText()+"'";
          pst = con.prepareStatement(sqls);
        rs= pst.executeQuery();
        jTable2.setModel(DbUtils.resultSetToTableModel(rs));
        
        
        
          TableColumn col0=jTable2.getColumnModel().getColumn(0);
        TableColumn col1=jTable2.getColumnModel().getColumn(1);
        
          
        
         col0.setPreferredWidth(200);
       col1.setPreferredWidth(50);
      
        
        
        
        
      }catch(Exception ex ){
      JOptionPane.showMessageDialog(null, ex);
}   
       
       }
       
       public void payecalcule(){
       
        float a = Float.parseFloat(jTextField11.getText());
            float b = Float.parseFloat(jTextField1.getText());
            float c;// = Math.floor(a*b *100.0) / 100.0;
           String d;
            c=a-b;
            d= String.format("%.2f",c);
            jTextField1.setText(d);
       
       }
       
       public void select_table(){
       
        try{
          int row= jTable1.getSelectedRow();
          String Table_click = (jTable1.getModel().getValueAt(row,0). toString());
          String sql = "SELECT * FROM   tableassocier WHERE NOM = '"+Table_click+"' And BUSS='"+jComboBox4.getSelectedItem()+"'";
           //String sql1 = "SELECT * FROM   tableassocier WHERE NOM = '"+Table_click+"'";
          pst = con.prepareStatement(sql);
          rs=pst.executeQuery();
          if(rs.next()){
                                     // facture_fournisseur
                  String sum1=rs.getString("NOM");
                nom.setText(sum1);
                String sum2=rs.getString("NOM");
                 noms.setText(sum2);
                 
                 String sum3=rs.getString("ADRESS");
                adress.setText(sum3);
                String sum4=rs.getString("ADRESS");
                 addresss.setText(sum4);
                 String sum5=rs.getString("TELPHONE");
                tel.setText(sum5);
                String sum6=rs.getString("TELPHONE");
                 tels.setText(sum6);
     
          }
         
    }catch(SQLException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }
        //TOTAL MOTANT DONNEE
        
         try{
          int row= jTable1.getSelectedRow();
          String Table_click = (jTable1.getModel().getValueAt(row,0). toString());
         // String sql = "SELECT * FROM   tableassocier WHERE NOM = '"+Table_click+"'";
           String sql = "SELECT sum(pt) FROM   tableassocier WHERE NOM = '"+Table_click+"' and BUSS='"+jComboBox4.getSelectedItem()+"'";
          pst = con.prepareStatement(sql);
          rs=pst.executeQuery();
          if(rs.next()){
                                     // facture_fournisseur
                  String sum1=rs.getString("sum(pt)");
               jFormattedTextField1.setText(sum1);
                
     
          }
         
    }catch(SQLException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }
         
          try{
              int row= jTable1.getSelectedRow();
          String Table_click = (jTable1.getModel().getValueAt(row,0). toString());
        
         String sql="SELECT DATES as'DATE', PT as'TM_GIVEN' FROM  tableassocier WHERE NOM = '"+Table_click+"' and BUSS='"+jComboBox4.getSelectedItem()+"'";
          pst = con.prepareStatement(sql);
        rs= pst.executeQuery();
        jTable3.setModel(DbUtils.resultSetToTableModel(rs));
        
        
        
        //  TableColumn col0=jTable3.getColumnModel().getColumn(0);
        //TableColumn col1=jTable1.getColumnModel().getColumn(1);
        
          
        
        // col0.setPreferredWidth(200);
       //col1.setPreferredWidth(50);
      
        
        
        
        
      }catch(Exception ex ){
      JOptionPane.showMessageDialog(null, ex);
}
         
         
       }
       
       
        public void Groupe1(){
ButtonGroup bg1 = new ButtonGroup();
bg1.add(cash);
bg1.add(noncash);
     }
        
         public void buttonSelected(){
    //jComboBox3.removeAllItems();
     if(cash.isSelected()){
         noms.setEnabled(false);
         nat.setEnabled(false);
         qty.setEnabled(false);
         pu.setEnabled(false);
         pt.setEnabled(false);
         addresss.setEnabled(false);
         tels.setEnabled(false);
         jButton3.setEnabled(false);
         
         nom.setEnabled(true);
         amount.setEnabled(true);
        // qty.setEnabled(false);
        // pu.setEnabled(false);
        // pt.setEnabled(false);
         adress.setEnabled(true);
         tel.setEnabled(true);
         jButton4.setEnabled(true);
         
         
     }else{noms.setEnabled(true);
         nat.setEnabled(true);
         qty.setEnabled(true);
         pu.setEnabled(true);
         pt.setEnabled(true);
         addresss.setEnabled(true);
         tels.setEnabled(true);
         jButton3.setEnabled(true);
         
         nom.setEnabled(false);
         amount.setEnabled(false);
        // qty.setEnabled(false);
        // pu.setEnabled(false);
        // pt.setEnabled(false);
         adress.setEnabled(false);
         tel.setEnabled(false);
         jButton4.setEnabled(false);}}
     
       
       
       

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
        jPanel3 = new javax.swing.JPanel();
        jComboBox1 = new javax.swing.JComboBox<>();
        busss = new javax.swing.JTextField();
        capital = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        nbre = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jComboBox4 = new javax.swing.JComboBox<>();
        jPanel4 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        nom = new javax.swing.JTextField();
        amount = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jButton4 = new javax.swing.JButton();
        adress = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        tel = new javax.swing.JTextField();
        cash = new javax.swing.JRadioButton();
        noncash = new javax.swing.JRadioButton();
        jPanel7 = new javax.swing.JPanel();
        noms = new javax.swing.JTextField();
        nat = new javax.swing.JTextField();
        qty = new javax.swing.JTextField();
        pu = new javax.swing.JTextField();
        pt = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        tels = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        addresss = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jDateChooser3 = new com.toedter.calendar.JDateChooser();
        jPanel10 = new javax.swing.JPanel();
        pour = new javax.swing.JTextField();
        pour1 = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        pour3 = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jFormattedTextField1 = new javax.swing.JFormattedTextField();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jTextField11 = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();

        setClosable(true);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jComboBox1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "USD", "USH", "CDF" }));

        busss.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        capital.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Capital");

        nbre.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        nbre.setText("1");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Nb. SH");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("Business");

        jButton5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton5.setText("Add");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jComboBox4.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                jComboBox4PopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        jComboBox4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jComboBox4, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(busss)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(nbre, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                                .addComponent(jLabel3)
                                .addGap(26, 26, 26)
                                .addComponent(capital, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(busss, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nbre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(capital, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton5)
                    .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Names of Ass", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        nom.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        amount.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("Name");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("Amount");

        jComboBox2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "USD", "USH", "CDF" }));

        jButton4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton4.setText("Add");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        adress.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel11.setText("Adress");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel12.setText("Tel");

        tel.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(adress, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tel))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(nom))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addComponent(jLabel4)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addComponent(amount, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(73, 73, 73))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(amount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4)
                    .addComponent(adress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12)
                    .addComponent(tel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21))
        );

        cash.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        cash.setText("Cash");
        cash.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cashMouseClicked(evt);
            }
        });
        cash.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cashActionPerformed(evt);
            }
        });

        noncash.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        noncash.setText("Non Cash");
        noncash.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                noncashMouseClicked(evt);
            }
        });
        noncash.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noncashActionPerformed(evt);
            }
        });

        jPanel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        noms.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        noms.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nomsActionPerformed(evt);
            }
        });

        nat.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        qty.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        qty.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                qtyKeyReleased(evt);
            }
        });

        pu.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        pu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                puKeyReleased(evt);
            }
        });

        pt.setEditable(false);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("Name");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("Non Cash");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("Qty");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setText("U.P");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setText("T.P");

        jButton3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton3.setText("Add");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        tels.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        tels.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                telsActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel13.setText("Tel");

        addresss.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel14.setText("Adress");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(qty, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(pu, javax.swing.GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE)
                                .addGap(13, 13, 13)
                                .addComponent(jLabel10)
                                .addGap(18, 18, 18)
                                .addComponent(pt, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(nat)))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(noms))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(addresss, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tels)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(noms, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(qty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton3)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(addresss, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel14)
                        .addComponent(jLabel13)
                        .addComponent(tels, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jDateChooser3.setEnabled(false);
        jDateChooser3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(cash)
                        .addGap(45, 45, 45)
                        .addComponent(jDateChooser3, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(noncash))
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap(19, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(noncash)
                            .addComponent(cash)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jDateChooser3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
        );

        jPanel10.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        pour.setEditable(false);
        pour.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        pour.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pourActionPerformed(evt);
            }
        });

        pour1.setEditable(false);
        pour1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel15.setText("Percentage");

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel16.setText("Remaining");

        pour3.setEditable(false);
        pour3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(pour1, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pour3, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(pour))
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pour, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pour1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16)
                    .addComponent(pour3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "ADD %", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane3.setViewportView(jTable3);

        jFormattedTextField1.setEditable(false);
        jFormattedTextField1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("¤¤#,##0"))));
        jFormattedTextField1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addComponent(jFormattedTextField1)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel8.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(jTable2);

        jTextField11.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jTextField11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jTextField11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField11ActionPerformed(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel19.setText("Profit");

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel20.setText("Payed");

        jTextField1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jTextField1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton2.setText("Pay");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton1.setText("Print");

        jButton6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton6.setText("Seach");

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel17.setText("From");

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel18.setText("To");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton6))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jLabel17)
                        .addGap(110, 110, 110)
                        .addComponent(jLabel18))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField11)
                            .addComponent(jLabel19)
                            .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jDateChooser2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel20)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jTextField1))))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(jLabel18))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(jLabel20))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField11)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

    private void cashActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cashActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cashActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
save(); 
jComboBox4.removeAllItems();


busss.setText(null);
nbre.setText(null);
capital.setText(null);
//busss.setText(null);
Call_ID_TO_BOMBOBOX2();// TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jComboBox4PopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_jComboBox4PopupMenuWillBecomeInvisible
combobox_from_materiel(); 
show_in_table();
show_pour();// TODO add your handling code here:
    }//GEN-LAST:event_jComboBox4PopupMenuWillBecomeInvisible

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
 if(amount.getText().isEmpty()){JOptionPane.showMessageDialog(null,"UNKNOWN AMOUNT","Error",JOptionPane.ERROR_MESSAGE);}
 else{ 
     if(jFormattedTextField1.getText().isEmpty()){
     save1();  
savePOUR();
nom.setText(null);
adress.setText(null);
tel.setText(null);
amount.setText(null);
jComboBox2.setSelectedItem("USD");
show_in_table();
show_pour();
     }else{
      //save1();
      saveQ();
      show_in_table();
     // show_pour(); 
     }
 }// TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
 if(pt.getText().isEmpty()){JOptionPane.showMessageDialog(null,"UNKNOWN AMOUNT","Error",JOptionPane.ERROR_MESSAGE);}
 else{
      if(jFormattedTextField1.getText().isEmpty()){
     save2(); 
savePOUR1();

noms.setText(null);
addresss.setText(null);
tels.setText(null);
qty.setText(null);
pu.setText(null);
pt.setText(null);
nat.setText(null);
//jComboBox2.setSelectedItem("USD");

show_in_table();
show_pour();
      }else{
      save2();
      saveQ();
     }
 }// TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void puKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_puKeyReleased
calulation2();        // TODO add your handling code here:
    }//GEN-LAST:event_puKeyReleased

    private void qtyKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_qtyKeyReleased
calulation2();        // TODO add your handling code here:
    }//GEN-LAST:event_qtyKeyReleased

    private void pourActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pourActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pourActionPerformed

    private void jComboBox4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox4ActionPerformed

    private void jTextField11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField11ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField11ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
whilee();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
payecalcule();        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1KeyReleased

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
select_table();        // TODO add your handling code here:
    }//GEN-LAST:event_jTable1MouseClicked

    private void telsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_telsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_telsActionPerformed

    private void nomsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nomsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nomsActionPerformed

    private void noncashActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noncashActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_noncashActionPerformed

    private void cashMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cashMouseClicked
buttonSelected();        // TODO add your handling code here:
    }//GEN-LAST:event_cashMouseClicked

    private void noncashMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_noncashMouseClicked
 buttonSelected();        // TODO add your handling code here:
    }//GEN-LAST:event_noncashMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField addresss;
    private javax.swing.JTextField adress;
    private javax.swing.JTextField amount;
    private javax.swing.JTextField busss;
    private javax.swing.JTextField capital;
    private javax.swing.JRadioButton cash;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox4;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private com.toedter.calendar.JDateChooser jDateChooser3;
    private javax.swing.JFormattedTextField jFormattedTextField1;
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField nat;
    private javax.swing.JTextField nbre;
    private javax.swing.JTextField nom;
    private javax.swing.JTextField noms;
    private javax.swing.JRadioButton noncash;
    private javax.swing.JTextField pour;
    private javax.swing.JTextField pour1;
    private javax.swing.JTextField pour3;
    private javax.swing.JTextField pt;
    private javax.swing.JTextField pu;
    private javax.swing.JTextField qty;
    private javax.swing.JTextField tel;
    private javax.swing.JTextField tels;
    // End of variables declaration//GEN-END:variables
}
