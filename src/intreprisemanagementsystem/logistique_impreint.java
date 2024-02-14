/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intreprisemanagementsystem;


import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import net.proteanit.sql.DbUtils;


/**
 *
 * @author DOSHE
 */
public class logistique_impreint extends javax.swing.JInternalFrame {

 Connection con=null;
PreparedStatement pst=null;
ResultSet rs= null;
//DefaultTableModel mode;
 String rolls;
 String NUM_ID;
 DefaultTableModel mode;   
 
 public logistique_impreint() {
        initComponents();
              con=JavaDbConnect.dbConnect();
        Groupe1();
       // call_table();
       webDateField1.setDate(new Date());
         nums.setHorizontalAlignment( nums.CENTER);
/*        unity.addItem("UNITE");
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
         */
    }
 
 
  public void etroll()
     {
         try{
            String sql="SELECT NUM FROM empreint ORDER BY NUM DESC LIMIT 1";
             pst=con.prepareStatement(sql);
             rs=pst.executeQuery();
             if(rs.next()){
                 String rl=rs.getString("NUM");
                 int ln=rl.length();
                 String stxt=rl.substring(0,7);
                 String snum=rl.substring(7,ln);
                 int n = Integer.parseInt(snum);
                 n++;
                 snum=Integer.toString(n);
                 rolls=stxt+snum;
                 
                 
                 
             }else{
                 //rolls="FICHE/EB/2018/1";
                // rolls="EB1001";
                 rolls="BORROW/1001";
             }
                   }catch(Exception e){
             JOptionPane.showMessageDialog(null, e); 
             
         }
     }
  
   public void numid()
     {
         try{
            String sql="SELECT NUMS FROM empreint ORDER BY NUMS DESC LIMIT 1";
             pst=con.prepareStatement(sql);
             rs=pst.executeQuery();
             if(rs.next()){
                 String rl=rs.getString("NUMS");
                 int ln=rl.length();
                 String stxt=rl.substring(0,3);
                 String snum=rl.substring(3,ln);
                 int n = Integer.parseInt(snum);
                 n++;
                 snum=Integer.toString(n);
                NUM_ID=stxt+snum;
                 
                 
                 
             }else{
                 //rolls="FICHE/EB/2018/1";
                // rolls="EB1001";
                 NUM_ID="ID-1001";
             }
                   }catch(Exception e){
             JOptionPane.showMessageDialog(null, e); 
             
         }
     }
 
 
              public void Groupe1(){
ButtonGroup bg1 = new ButtonGroup();
bg1.add(supply);
bg1.add(clients);

ButtonGroup bg2 = new ButtonGroup();
bg2.add(from);
bg2.add(to);

ButtonGroup bg3 = new ButtonGroup();
bg3.add(materiaux);
bg3.add(materiel);
bg3.add(agin);
     }
              
         public void select_combo(){
         
          try{
                        
              
             //mat.removeAllItems();
            String sqls="select * from  materiaux_in where DESCR='"+mat.getSelectedItem()+"'";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
            while(rs.next()){
                String add1 = rs.getString("qty");
              qty_in.setText(add1);
               String add2 = rs.getString("unity");
             unity.setText(add2);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
         
         
         
         }
              
              
          public void call_mat()
          {
              if(materiaux.isSelected()){
                    try{
                         mat.removeAllItems();
            String sqls="select distinct(DESCR) from  materiaux_in";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
            while(rs.next()){
                String add1 = rs.getString("DESCR");
              mat.addItem(add1);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
                  }else if(materiel.isSelected()){
              
              //
              
                           try{
                               mat.removeAllItems();
            String sqls="select distinct(ITEM_ID) from  equipement_in";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
            while(rs.next()){
                String add1 = rs.getString("ITEM_ID");
              mat.addItem(add1);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
              
              } else if(agin.isSelected()){
                  
                 
                           try{
                               mat.removeAllItems();
            String sqls="select distinct(DESCR) from  AGIN";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
            while(rs.next()){
                String add1 = rs.getString("DESCR");
              mat.addItem(add1);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        } 
                  
                  
                  
                  }   
                  
        
          
          
          }  
          
          String MODEL,STATUT,SERIE,DESCRIPTION;
          public void solde_materiaux(){
              if(materiaux.isSelected()){
              Double a,b,c;
          String d;
            try{              //call from journala de banque  
            String sql="select SUM(DEBIT),SUM(CREDIT),UNITY,ITEM_ID,ITEM from  materiaux_transaction where DESCR= '"+mat.getSelectedItem()+"'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                a=rs.getDouble("SUM(DEBIT)");
                b=rs.getDouble("SUM(CREDIT)");
                  
                   c=a-b;
                  d= String.format("%.1f",c);
            qty_in.setText(d);
             String sum = rs.getString("UNITY");
             unity.setText(sum);
             
              String sumE = rs.getString("ITEM");
             desc.setText(sumE);
             
              String sum1 = rs.getString("ITEM_ID");
             mat_id.setText(sum1);
            }
            }catch(Exception ex){ JOptionPane.showMessageDialog(null, ex);} 
              }else if(materiel.isSelected()){
              
              Double a,b,c;
          String d;
            try{              //call from journala de banque  
            String sql="select SUM(DEBIT),SUM(CREDIT),MODEL,SERIAL,DESCR,ITEM_ID,ITEM from  eqipement_trans where ITEM_ID= '"+mat.getSelectedItem()+"'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                a=rs.getDouble("SUM(DEBIT)");
                b=rs.getDouble("SUM(CREDIT)");
                  
                   c=a-b;
                  d= String.format("%.1f",c);
            qty_in.setText(d);
           //  String sum = rs.getString("UNITY");
             //unity.setText(sum);
             
              MODEL = rs.getString("MODEL");
           
             
              SERIE = rs.getString("SERIAL");
              
              DESCRIPTION=rs.getString("DESCR");
              desc.setText(DESCRIPTION);
               String aaa=rs.getString("ITEM");
               mat_id.setText(aaa);
              
          
             
             
            }
            }catch(Exception ex){ JOptionPane.showMessageDialog(null, ex);} 
              }else{
                  
                qty_in.setText("1.0");
              }
              
              
              
          
          
          }
          
          String POST=null;
              
     public void call_combo(){
         client.removeAllItems();
    String tmp="Fournisseur";
     try{
            String sqls="select NOM from  client_fournisseur where TYPE= '"+tmp+"'";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
            while(rs.next()){
                String add1 = rs.getString("NOM");
              client.addItem(add1);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
    POST=null;
    POST=tmp;
    }
    
      public void call_combo_client(){
          client.removeAllItems();
    String tmp="Client";
     try{
            String sqls="select NOM from  client_fournisseur where TYPE= '"+tmp+"'";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
            while(rs.next()){
                String add1 = rs.getString("NOM");
              client.addItem(add1);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
    POST=null;
    POST=tmp;
    }
    
      
      public void calculation(){
       Double a = Double.parseDouble(qty_in.getText());
       Double b = Double.parseDouble(qty.getText());
       Double c;
       String d;
       
       c= a-b;
       qty_in.setText(""+c);
      
      }
      
      public void num(){
     try{
            String sqls="select max(num) from  empreint";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
            while(rs.next()){
                String add1 = rs.getString("max(num)");
              nums.setText(add1);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
    
    }
    
      
      
      public void save(){
      String statut="";
if(from.isSelected()){
statut=from.getText();
}else if(to.isSelected()){
statut=to.getText();
}

if(nums.getText().equals("")){
    etroll();
    numid();
  try{
    String sql="INSERT INTO `empreint`(`ITEM`, `QTY`, `UNITY`, `DATES`, `STATUT`, `CLIENT`, `NUM`, `NUMS`) VALUES (?,?,?,?,?,?,?,?)";
    pst=con.prepareStatement(sql);
    pst.setString(1,mat.getSelectedItem().toString());
    pst.setString(2,qty.getText());
    pst.setString(3,unity.getText());
    pst.setString(5,statut);
    pst.setString(6,client.getSelectedItem().toString());
     pst.setString(7,rolls);
     pst.setString(8,NUM_ID);
   
    SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
         String addDate1 = dateFormat1.format(webDateField1.getDate());
         pst.setString(4, addDate1);
      
    
    pst.executeUpdate();
      JOptionPane.showMessageDialog(null,"dates saved");
    }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}
    
}else{
    numid();
       try{
    String sql="INSERT INTO `empreint`(`ITEM`, `QTY`, `UNITY`, `DATES`, `STATUT`, `CLIENT`, `NUM`, `NUMS`) VALUES (?,?,?,?,?,?,?,?)";
    pst=con.prepareStatement(sql);
    pst.setString(1,mat.getSelectedItem().toString());
    pst.setString(2,qty.getText());
    pst.setString(3,unity.getText());
    pst.setString(5,statut);
    pst.setString(6,client.getSelectedItem().toString());
     pst.setString(7,nums.getText());
     pst.setString(8,NUM_ID);
   
    SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
         String addDate1 = dateFormat1.format(webDateField1.getDate());
         pst.setString(4, addDate1);
      
    
    pst.executeUpdate();
      JOptionPane.showMessageDialog(null,"dates saved");
    }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}
    
} 
      }
      
      
      public void update(){
      
            String statut="";
if(from.isSelected()){
statut=from.getText();
}else if(to.isSelected()){
statut=to.getText();
}
          
      try{
    String sql="UPDATE `empreint` SET `ITEM`=?,`QTY`=?,`UNITY`=?,`DATES`=?,`STATUT`=?,`CLIENT`=? WHERE NUMS='"+id.getText()+"'";
    pst=con.prepareStatement(sql);
    pst.setString(1,mat.getSelectedItem().toString());
    pst.setString(2,qty.getText());
    pst.setString(3,unity.getText());
    pst.setString(5,statut);
    pst.setString(6,client.getSelectedItem().toString());
    // pst.setString(7,rolls);
    // pst.setString(8,NUM_ID);
   
    SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
         String addDate1 = dateFormat1.format(webDateField1.getDate());
         pst.setString(4, addDate1);
      
    
    pst.executeUpdate();
      JOptionPane.showMessageDialog(null,"Datas Updated");
    }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}
    id.setText("");
      }
      
      public void call_table(){
      
           try{
           
             String sql="SELECT `ITEM`, `QTY`, `UNITY`, `DATES` , `STATUT`, `CLIENT`, `NUMS` as 'ID' FROM `empreint` where client='"+client.getSelectedItem()+"' and NUM='"+nums.getText()+"'";
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
       
       
      
       
       col0.setPreferredWidth(110);
       col1.setPreferredWidth(20);
       col2.setPreferredWidth(30);
       col3.setPreferredWidth(60);
       col4.setPreferredWidth(70);
       col5.setPreferredWidth(50);
       col6.setPreferredWidth(50);
     
      
       
       
      // jTable1.setModel(mode);
       
     
    }catch(SQLException ex ){
     JOptionPane.showMessageDialog(null, ex);
}
    
      
      }
      
      public void refresh(){
      nums.setText("");
      client.removeAll();
      mat.setSelectedItem("");
      id.setText("");
      qty_in.setText("");
     
      }
      
      
       public void select_jTable()
   {
      
        try{
          int row= jTable1.getSelectedRow();
          String Table_click = (jTable1.getModel().getValueAt(row,6). toString());
          String sql = "SELECT `ITEM`, `QTY`, `UNITY`, `DATES`, `STATUT`, `CLIENT`, `NUM`, `NUMS` FROM `empreint` WHERE  `NUMS` = '"+Table_click+"'";
          pst = con.prepareStatement(sql);
          rs=pst.executeQuery();
          if(rs.next()){
              String sum0=rs.getString("ITEM");
                mat.setSelectedItem(sum0);
                  String sum1=rs.getString("NUMS");
               id.setText(sum1);
               
               
               
                  
          }
         
    }catch(SQLException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }
   } 
      
 public void delete()
    {
      //  if(jComboBox3.getSelectedItem().equals("SELECTIONNE LE PROJET ICI!!!!*****SELECT PROJECT NAME HERE!!!!")){
       
         try{
        String sql = "DELETE FROM empreint WHERE NUMS=?";
        
         pst = con.prepareStatement(sql);
         pst.setString(1,id.getText());
         pst.executeUpdate();
          
         JOptionPane.showMessageDialog(null,"Data Deleted");     
     }catch(SQLException | HeadlessException ex ){
         JOptionPane.showMessageDialog(null,ex);
     } 
        }
 
 

   
   public void big_save(){
   if(to.isSelected()){
     save();
  

       if(materiaux.isSelected()){
            save();
   save_();
   save_TRANSACTION(); 
    save_INVENTAORY();
       }else if(materiel.isSelected()){
             save();
       save_TRANSsEQUI();
       save_EQUI();
       }
   
   
  
   }else if(from.isSelected()) {
      save();
   if(materiaux.isSelected()){  
      save_MAT_BF();
   save_TRANSACTION_MAT_BF();
   }else if(materiel.isSelected()){
   saves();
       save_TRANS();
               savestatus();
   
    
       
   }
   }
   }
   
   
   
   // SAVE MATERIAUX 
  public void save_(){
 etroll();
 numid();
 try{
    String sql="INSERT INTO `materiaux_out_bon`(`ITEM_ID`, `ITEM`, `QTY`, `UNITY`, `SITE`, `RESP`, `DATES`, `EMP`, `TITLE`, `PURPOSE`, `NUM`, `NUM_ID`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
     
    pst=con.prepareStatement(sql);
    pst.setString(1,mat_id.getText());
    pst.setString(2,mat.getSelectedItem().toString());
    pst.setString(3,qty.getText());
    pst.setString(4,unity.getText());
    pst.setString(5,"Borrow");
    
    pst.setString(6,"Logistique");
    pst.setString(8,client.getSelectedItem().toString());
    pst.setString(9,"Client");
    
    pst.setString(10,"Borrow to "+client.getSelectedItem().toString());
   // pst.setString(11,post.getText());
    
     pst.setString(11,rolls);
     pst.setString(12,NUM_ID);
    
   
    SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
         String addDate1 = dateFormat1.format(webDateField1.getDate());
         pst.setString(7, addDate1);
      
    
    pst.executeUpdate();
     // JOptionPane.showMessageDialog(null,"Datas Saved");
    }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}
    
 
 
 }
     
   public void save_TRANSACTION(){
// etroll();
 //numid();
 try{
    String sql="INSERT INTO `materiaux_transaction`(`ITEM_ID`,`ITEM`, `DESCR`, `DEBIT`, `CREDIT`, `UNITY`, `DATES`, `NUM`, `NUM_ID`) VALUES (?,?,?,?,?,?,?,?,?)";
     
    pst=con.prepareStatement(sql);
    pst.setString(1,mat_id.getText());
    pst.setString(2,"ITEM");
    pst.setString(3,mat.getSelectedItem().toString());
    pst.setString(4,"0");
     pst.setString(5,qty.getText());
    pst.setString(6,unity.getText());
     pst.setString(8,rolls);
     pst.setString(9,NUM_ID);
    
   
    SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
         String addDate1 = dateFormat1.format(webDateField1.getDate());
         pst.setString(7, addDate1);
      
    
    pst.executeUpdate();
     // JOptionPane.showMessageDialog(null,"Datas Saved");
    }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}
 }
   
    public void save_INVENTAORY(){
// etroll();
// numid();
 try{
    String sql="INSERT INTO `inventairemtr`(`ITEM`, `DEBIT`, `CREDIT`,`UNITY`, `DATES`, `SITE`, `PARPUSE`, `EMP`, `NUM_ID`)  VALUES (?,?,?,?,?,?,?,?,?)";
     
    pst=con.prepareStatement(sql);
   
    pst.setString(1,mat.getSelectedItem().toString());
    pst.setString(2,"0");
     pst.setString(3,qty.getText());
    pst.setString(4,unity.getText());
   pst.setString(6,"Borrow");
   pst.setString(7,"Borrowed to "+client.getSelectedItem());
   pst.setString(8,client.getSelectedItem().toString());
  
     pst.setString(9,NUM_ID);
    
   
    SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
         String addDate1 = dateFormat1.format(webDateField1.getDate());
         pst.setString(5, addDate1);
      
    
    pst.executeUpdate();
      JOptionPane.showMessageDialog(null,"Datas Saved");
    }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}
 }
    
     public void save_sup(){
 etroll();
 numid();
 try{
    String sql="INSERT INTO `materiaux_out_bon`(`ITEM_ID`, `ITEM`, `QTY`, `UNITY`, `SITE`, `RESP`, `DATES`, `EMP`, `TITLE`, `PURPOSE`, `NUM`, `NUM_ID`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
     
    pst=con.prepareStatement(sql);
    pst.setString(1,mat_id.getText());
    pst.setString(2,mat.getSelectedItem().toString());
    pst.setString(3,qty.getText());
    pst.setString(4,unity.getText());
    pst.setString(5,"Borrow");
    
    pst.setString(6,"Logistique");
    pst.setString(8,client.getSelectedItem().toString());
    pst.setString(9,"Supply");
    
    pst.setString(10,"Borrow from "+client.getSelectedItem().toString());
   // pst.setString(11,post.getText());
    
     pst.setString(11,rolls);
     pst.setString(12,NUM_ID);
    
   
    SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
         String addDate1 = dateFormat1.format(webDateField1.getDate());
         pst.setString(7, addDate1);
      
    
    pst.executeUpdate();
     // JOptionPane.showMessageDialog(null,"Datas Saved");
    }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}
    
 
 
 }
     
   public void save_TRANSACTION_sup(){
// etroll();
 //numid();
 try{
    String sql="INSERT INTO `materiaux_transaction`(`ITEM_ID`,`ITEM`, `DESCR`, `DEBIT`, `CREDIT`, `UNITY`, `DATES`, `NUM`, `NUM_ID`) VALUES (?,?,?,?,?,?,?,?,?)";
     
    pst=con.prepareStatement(sql);
    pst.setString(1,"item_id.getText()");
    pst.setString(2,"ITEM");
    pst.setString(3,mat.getSelectedItem().toString());
    pst.setString(4,"0");
     pst.setString(5,qty.getText());
    pst.setString(6,unity.getText());
     pst.setString(8,rolls);
     pst.setString(9,NUM_ID);
    
   
    SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
         String addDate1 = dateFormat1.format(webDateField1.getDate());
         pst.setString(7, addDate1);
      
    
    pst.executeUpdate();
     // JOptionPane.showMessageDialog(null,"Datas Saved");
    }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}
 }
   
    public void save_INVENTAORY_sup(){
// etroll();
// numid();
 try{
    String sql="INSERT INTO `inventairemtr`(`ITEM`, `DEBIT`, `CREDIT`,`UNITY`, `DATES`, `SITE`, `PARPUSE`, `EMP`, `NUM_ID`)  VALUES (?,?,?,?,?,?,?,?,?)";
     
    pst=con.prepareStatement(sql);
   
    pst.setString(1,mat.getSelectedItem().toString());
    pst.setString(2,"0");
     pst.setString(3,qty.getText());
    pst.setString(4,unity.getText());
   pst.setString(6,"Borrow");
   pst.setString(7,"Borrowed to "+client.getSelectedItem());
   pst.setString(8,client.getSelectedItem().toString());
  
     pst.setString(9,NUM_ID);
    
   
    SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
         String addDate1 = dateFormat1.format(webDateField1.getDate());
         pst.setString(5, addDate1);
      
    
    pst.executeUpdate();
      JOptionPane.showMessageDialog(null,"Datas Saved");
    }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}
 }
    
 
 //SAVE EQUIPEMENT
    
   //save_TRANSsEQUI() save_EQUI()
      public void save_EQUI(){
 etroll();
 numid();
 if(id.getText().isEmpty()){
 try{
    String sql="INSERT INTO `dotation`(`EMP_NAME`,`POST`, `EMP_CHANTIER`, `MATERIEL`, `SERIE`, `MODEL`, `STATUT`, `DUREE`, `DATES`, `QTY_OUT`, `NUM`, `NUM_ID`, `STATUS`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
     
    pst=con.prepareStatement(sql);
    pst.setString(1,client.getSelectedItem().toString());
    pst.setString(2,POST);
    pst.setString(3,"Borrow");
    pst.setString(4,mat.getSelectedItem().toString());
    pst.setString(5,mat_id.getText());
    
    pst.setString(6,MODEL);
    pst.setString(7,"Borrow");
    
    pst.setString(8,webDateField1.getText());
    
    pst.setString(9,webDateField1.getText());
    pst.setString(10,qty.getText());
      pst.setString(11,rolls);
        pst.setString(12,NUM_ID);
        pst.setString(13,"");
    
   
    
   
  
      
    
    pst.executeUpdate();
     // JOptionPane.showMessageDialog(null,"Datas Saved");
    }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}
 
 }else{
 
      try{
    String sql="INSERT INTO `dotation`(`EMP_NAME`,`POST`, `EMP_CHANTIER`, `MATERIEL`, `SERIE`, `MODEL`, `STATUT`, `DUREE`, `DATES`, `QTY_OUT`, `NUM`, `NUM_ID`, `STATUS`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
     
    pst=con.prepareStatement(sql);
    pst.setString(1,client.getSelectedItem().toString());
    pst.setString(2,POST);
    pst.setString(3,"Borrow");
    pst.setString(4,mat.getSelectedItem().toString());
    pst.setString(5,mat_id.getText());
    
    pst.setString(6,MODEL);
    pst.setString(7,"Borrow");
    pst.setString(8,webDateField1.getText());
    
    pst.setString(9,webDateField1.getText());
    pst.setString(10,qty.getText());
      pst.setString(11,id.getText());
        pst.setString(12,NUM_ID);
         pst.setString(13,"");
    
   
    
   
  
      
    
    pst.executeUpdate();
     // JOptionPane.showMessageDialog(null,"Datas Saved");
    }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}
 
 }
    
 
 
 }
                       
                         public void save_TRANSsEQUI(){
 etroll();
 numid();
 if(id.getText().isEmpty()){
 try{
    String sql="INSERT INTO `eqipement_trans`(`ITEM_ID`, `ITEM`, `DESCR`, `SERIAL`, `MODEL`, `STATUT`, `DEBIT`, `CREDIT`, `NUM_ID`, `NUM` ,`EMP`, `PURPOSE`,`DATES`, `SITE`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     
    pst=con.prepareStatement(sql);
    pst.setString(1,mat_id.getText());
    pst.setString(2,mat.getSelectedItem().toString());
    pst.setString(3,DESCRIPTION);
    pst.setString(4,"");
    pst.setString(5,MODEL);
     pst.setString(6,"Borrow");
     pst.setString(7,"0");
     
      pst.setString(8,qty.getText());
       pst.setString(9,NUM_ID);
        pst.setString(10,rolls);
        
        pst.setString(11,client.getSelectedItem().toString());
        pst.setString(12,"Borrow  to "+client.getSelectedItem().toString());
        
        pst.setString(13,webDateField1.getText());
        pst.setString(14,"Borrow");
        
        
        
      
    
    pst.executeUpdate();
     // JOptionPane.showMessageDialog(null,"Datas Saved");
    }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}
 
 }else{
 
      try{
    String sql="INSERT INTO `eqipement_trans`(`ITEM_ID`, `ITEM`, `DESCR`, `SERIAL`, `MODEL`, `STATUT`, `DEBIT`, `CREDIT`, `NUM_ID`, `NUM`, `EMP`, `PURPOSE`,`DATES`, `SITE`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     
    pst=con.prepareStatement(sql);
    pst.setString(1,mat_id.getText());
    pst.setString(2,mat.getSelectedItem().toString());
    pst.setString(3,DESCRIPTION);
    pst.setString(4,"");
    pst.setString(5,MODEL);
     pst.setString(6,"Borrow");
     pst.setString(7,"0");
     
      pst.setString(8,qty.getText());
       pst.setString(9,NUM_ID);
        pst.setString(10,id.getText());
        
        pst.setString(11,client.getSelectedItem().toString());
        pst.setString(12,"Borrow to "+client.getSelectedItem().toString());
        
        
       pst.setString(13,webDateField1.getText());
        pst.setString(14,"Borrow");
    
    pst.executeUpdate();
     // JOptionPane.showMessageDialog(null,"Datas Saved");
    }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}
 
 }
                         }
                         
                         
                         
   // <-----------------------------------------BORROW FROM----------------------------------->
                         //MATERIAUX
    
                          public void etroll_MAT_BF()
     {
         try{
            String sql="SELECT NUM FROM  materiaux_in ORDER BY NUM DESC LIMIT 1";
            

            
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
                 rolls="MAT-STOCK-IN/1001";
             }
                   }catch(Exception e){
             JOptionPane.showMessageDialog(null, e); 
             
         }
     }
  public void numid_MAT_BF()
     {
         try{
            String sql="SELECT NUM_ID FROM materiaux_in ORDER BY NUM_ID DESC LIMIT 1";
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
                 NUM_ID="ID-MTR1001";
             }
              }catch(Exception e){
             JOptionPane.showMessageDialog(null, e); 
             
         }
         }
                         
public void save_MAT_BF(){
  
        
 etroll_MAT_BF();
 numid_MAT_BF();
 try{
    String sql="INSERT INTO `materiaux_in`(`ITEM_ID`, `ITEM`, `DESCR`, `QTY`, `UNITY`, `DATES`, `NUM`, `NUM_ID`) VALUES (?,?,?,?,?,?,?,?)";
     
    pst=con.prepareStatement(sql);
    pst.setString(1,mat_id.getText());
    pst.setString(2,desc.getText());
    pst.setString(3,mat.getSelectedItem().toString());
    pst.setString(4,qty.getText());
    pst.setString(5,unity.getText());
     pst.setString(7,rolls);
     pst.setString(8,NUM_ID);
    
   
         pst.setString(6, webDateField1.getText());
      
    
    pst.executeUpdate();
     // JOptionPane.showMessageDialog(null,"Datas Saved");
    }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}
    
 
 
 }
 
 public void save_TRANSACTION_MAT_BF(){
 //etroll();
// numid();
 try{
    String sql="INSERT INTO `materiaux_transaction`(`ITEM_ID`,`ITEM`, `DESCR`, `DEBIT`, `CREDIT`, `UNITY`, `DATES`, `NUM`, `NUM_ID`) VALUES (?,?,?,?,?,?,?,?,?)";
     
    pst=con.prepareStatement(sql);
    pst.setString(1,mat_id.getText());
    pst.setString(2,desc.getText());
    pst.setString(3,mat.getSelectedItem().toString());
    pst.setString(4,qty.getText());
     pst.setString(5,"0");
    pst.setString(6,unity.getText());
     pst.setString(8,rolls);
     pst.setString(9,NUM_ID);
    
   
         pst.setString(7,  webDateField1.getText());
      
    
    pst.executeUpdate();
     // JOptionPane.showMessageDialog(null,"Datas Saved");
    }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}
    
 
 
 }
 
 //EQUIPEMENT
   public void etrolls()
     {
         try{
            String sql="SELECT NUM FROM  equipement_in ORDER BY NUM DESC LIMIT 1";
            

            
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
  public void numids()
     {
         try{
            String sql="SELECT NUM_ID FROM equipement_in ORDER BY NUM_ID DESC LIMIT 1";
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
   public void saves(){
       
 etrolls();
 numids();
 try{
    String sql="INSERT INTO `equipement_in`(`ITEM_ID`, `ITEM`, `DESCR`, `SERIAL`, `MODEL`, `STATUT`, `QTY`, `UNITY`, `DATES`,`NUM_ID`,`NUM`,`PHOTOS`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
     
    pst=con.prepareStatement(sql);
    pst.setString(1,mat.getSelectedItem().toString());
    pst.setString(2,mat_id.getText());
    pst.setString(3,desc.getText());
    //MODEL,STATUT,SERIE,DESCRIPTION;
    pst.setString(4,"Borrow From");
    pst.setString(5,"Borrow From");
     pst.setString(6,"Borrow From");
     pst.setString(7,qty.getText());
     
      pst.setString(8,unity.getText());
       pst.setString(10,NUM_ID);
        pst.setString(11,rolls);
        
      
         pst.setString(12,"");
    
   
         pst.setString(9,webDateField1.getText());
      
    
    pst.executeUpdate();
     // JOptionPane.showMessageDialog(null,"Datas Saved");
    }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}
    
     }
     public void savestatus(){

 try{
    String sql="INSERT INTO `etat`(`ITEM_ID`,`ITEM`,`DESCR`,`STATUS`,`NUM_ID`) VALUES (?,?,?,?,?)";
     
    pst=con.prepareStatement(sql);
   pst.setString(1,mat.getSelectedItem().toString());
    pst.setString(2,mat_id.getText());
    pst.setString(3,desc.getText());
    pst.setString(4,"Borrow From");
    pst.setString(5,NUM_ID);
  
    
    pst.executeUpdate();
     // JOptionPane.showMessageDialog(null,"Datas Saved");
    }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}
    
 
 
 }
     
      public void UDATEtatus(){

 try{
    String sql="UPDATE  `etat` SET `ITEM_ID`=?,`ITEM`=?,`DESCR`=?,`STATUS`=? Where NUM_ID='"+id.getText()+"'";
     
    pst=con.prepareStatement(sql);
   pst.setString(1,mat.getSelectedItem().toString());
    pst.setString(2,mat_id.getText());
    pst.setString(3,desc.getText());
    pst.setString(4,"Borrow From");
  
    
    pst.executeUpdate();
     // JOptionPane.showMessageDialog(null,"Datas Saved");
    }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}
    
 
 
 }
     
      public void save_TRANS(){
 //etroll();
 //numid();
 try{
    String sql="INSERT INTO `eqipement_trans`(`ITEM_ID`, `ITEM`, `DESCR`, `SERIAL`, `MODEL`, `STATUT`, `DEBIT`, `CREDIT`, `NUM_ID`, `NUM`, `EMP`, `PURPOSE`,`DATES`, `SITE`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     
    pst=con.prepareStatement(sql);
   pst.setString(1,mat.getSelectedItem().toString());
    pst.setString(2,mat_id.getText());
    pst.setString(3,desc.getText());
    pst.setString(4,"Borrow From");
    pst.setString(5,"Borrow From");
     pst.setString(6,"Borrow From");
     pst.setString(7,qty.getText());
     
      pst.setString(8,"0");
       pst.setString(9,NUM_ID);
        pst.setString(10,rolls);
        
          pst.setString(11,"LOG");
        pst.setString(12,"STOCK LOG");
         pst.setString(13,webDateField1.getText());
       pst.setString(14,"LOG");
        
        
      
    
    pst.executeUpdate();
     // JOptionPane.showMessageDialog(null,"Datas Saved");
    }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}
    
 
 
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
        supply = new javax.swing.JRadioButton();
        clients = new javax.swing.JRadioButton();
        jPanel3 = new javax.swing.JPanel();
        client = new javax.swing.JComboBox<>();
        qty_in = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        webDateField1 = new com.alee.extended.date.WebDateField();
        mat = new javax.swing.JComboBox<>();
        unity = new javax.swing.JTextField();
        qty = new javax.swing.JTextField();
        mat_id = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        desc = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        from = new javax.swing.JRadioButton();
        to = new javax.swing.JRadioButton();
        jPanel6 = new javax.swing.JPanel();
        materiaux = new javax.swing.JRadioButton();
        materiel = new javax.swing.JRadioButton();
        agin = new javax.swing.JRadioButton();
        jPanel7 = new javax.swing.JPanel();
        nums = new javax.swing.JTextField();
        id = new javax.swing.JTextField();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu2 = new javax.swing.JMenu();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();

        setBorder(null);
        setToolTipText("");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setFrameIcon(null);
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
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Select One Opion", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        supply.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        supply.setText("Supply");
        supply.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                supplyActionPerformed(evt);
            }
        });

        clients.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        clients.setText("Client");
        clients.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clientsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(supply)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(clients)
                .addGap(30, 30, 30))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(supply)
                    .addComponent(clients, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 2, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        client.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        client.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                clientPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        qty_in.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        qty_in.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                qty_inKeyTyped(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("Unity");

        webDateField1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        mat.setEditable(true);
        mat.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        mat.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                matPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        unity.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        qty.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        qty.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                qtyMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                qtyMouseReleased(evt);
            }
        });
        qty.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                qtyKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                qtyKeyTyped(evt);
            }
        });

        mat_id.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Mat Id");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Clients");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Items");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("Qtys");

        desc.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addGap(25, 25, 25)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(client, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(webDateField1, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(mat, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                .addComponent(mat_id)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel5)
                                .addGap(8, 8, 8)
                                .addComponent(qty_in, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(qty, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(unity, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(desc))))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(client, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(webDateField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(3, 3, 3)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(mat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(desc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(qty_in, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(qty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)
                            .addComponent(unity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(mat_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel5))
                        .addContainerGap())))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

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

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Statut", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N
        jPanel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        from.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        from.setText("Borrow From");
        from.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fromActionPerformed(evt);
            }
        });

        to.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        to.setText("Borrow To");
        to.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                toActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(from)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(to)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(from)
                    .addComponent(to)))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Select One Option", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        materiaux.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        materiaux.setText("Meterials");
        materiaux.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                materiauxActionPerformed(evt);
            }
        });

        materiel.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        materiel.setText("Equipement");
        materiel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                materielActionPerformed(evt);
            }
        });

        agin.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        agin.setText("Agins");
        agin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aginActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(materiaux)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(materiel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(agin)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(materiaux)
                    .addComponent(materiel)
                    .addComponent(agin))
                .addGap(0, 9, Short.MAX_VALUE))
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "No. Sheet", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        nums.setEditable(false);
        nums.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        id.setEditable(false);
        id.setForeground(new java.awt.Color(240, 240, 240));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(nums, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(id, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 15, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nums, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(92, 92, 92)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(101, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jMenuBar1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        jMenu2.setText("X");
        jMenu2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jMenu2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu2MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu2);

        jMenu1.setText("File");

        jMenuItem4.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem4.setText("New");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem4);

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setText("Save");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem2.setText("Delete");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem3.setText("Print");
        jMenu1.add(jMenuItem3);

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
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setBounds(100, 0, 696, 383);
    }// </editor-fold>//GEN-END:initComponents

    private void clientsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clientsActionPerformed
call_combo_client();        // TODO add your handling code here:
    }//GEN-LAST:event_clientsActionPerformed

    private void supplyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_supplyActionPerformed
call_combo();
// TODO add your handling code here:
    }//GEN-LAST:event_supplyActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
delete(); 
id.setText("");
refresh(); 
call_table();// TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
 if(mat.getSelectedItem().equals("")){
 JOptionPane.showMessageDialog(null,"Wrong Data","Error",JOptionPane.ERROR_MESSAGE); 
 } if(qty_in.getText().equals("")){
 JOptionPane.showMessageDialog(null,"Wrong Data","Error",JOptionPane.ERROR_MESSAGE); 
 }else{
        if(id.getText().equals("")){
       big_save();
}else{
update();
}
mat.setSelectedItem("");
qty_in.setText("");
num();
call_table();}// TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
refresh();  
client.removeAllItems();
mat.removeAllItems();// TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
select_jTable();        // TODO add your handling code here:
    }//GEN-LAST:event_jTable1MouseClicked

    private void clientPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_clientPopupMenuWillBecomeInvisible
call_table();         // TODO add your handling code here:
    }//GEN-LAST:event_clientPopupMenuWillBecomeInvisible

    private void qty_inKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_qty_inKeyTyped
 char c=evt.getKeyChar();
        if(!(Character.isDigit(c) || (c==KeyEvent.VK_BACK_SPACE)||c==KeyEvent.VK_DELETE ||c==KeyEvent.VK_PERIOD )){
            evt.consume();

        }        // TODO add your handling code here:
    }//GEN-LAST:event_qty_inKeyTyped

    private void formInternalFrameClosed(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosed
// TODO add your handling code here:
    }//GEN-LAST:event_formInternalFrameClosed

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
      // TODO add your handling code here:
    }//GEN-LAST:event_formMouseClicked

    private void formInternalFrameActivated(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameActivated
         // TODO add your handling code here:
    }//GEN-LAST:event_formInternalFrameActivated

    private void jMenu2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu2MouseClicked
this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu2MouseClicked

    private void materiauxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_materiauxActionPerformed
call_mat();        // TODO add your handling code here:
    }//GEN-LAST:event_materiauxActionPerformed

    private void materielActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_materielActionPerformed
call_mat();        // TODO add your handling code here:
    }//GEN-LAST:event_materielActionPerformed

    private void fromActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fromActionPerformed
//mat.setEditable(true); 
//materiaux.setEnabled(false);
//materiel.setEnabled(false);// TODO add your handling code here:
    }//GEN-LAST:event_fromActionPerformed

    private void toActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_toActionPerformed
mat.setEditable(false); 
materiaux.setEnabled(true);
materiel.setEnabled(true);// TODO add your handling code here:
    }//GEN-LAST:event_toActionPerformed

    private void matPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_matPopupMenuWillBecomeInvisible
solde_materiaux();      // TODO add your handling code here:
    }//GEN-LAST:event_matPopupMenuWillBecomeInvisible

    private void qtyMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_qtyMouseClicked

// TODO add your handling code here:
    }//GEN-LAST:event_qtyMouseClicked

    private void qtyMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_qtyMouseReleased
      // TODO add your handling code here:
    }//GEN-LAST:event_qtyMouseReleased

    private void qtyKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_qtyKeyReleased
Double a =Double.parseDouble(qty_in.getText());
Double b = Double.parseDouble(qty.getText());
if(b>a){
    JOptionPane.showMessageDialog(null,"Not Enough Stock","Error",JOptionPane.ERROR_MESSAGE);
    qty.setText("");
}else{
calculation();
}          // TODO add your handling code here:
    }//GEN-LAST:event_qtyKeyReleased

    private void qtyKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_qtyKeyTyped
  char c=evt.getKeyChar();
        if(!(Character.isDigit(c) || (c==KeyEvent.VK_BACK_SPACE)||c==KeyEvent.VK_DELETE ||c==KeyEvent.VK_PERIOD )){
            evt.consume();
        }        // TODO add your handling code here:
    }//GEN-LAST:event_qtyKeyTyped

    private void aginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aginActionPerformed
call_mat();        // TODO add your handling code here:
    }//GEN-LAST:event_aginActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton agin;
    private javax.swing.JComboBox<String> client;
    private javax.swing.JRadioButton clients;
    private javax.swing.JTextField desc;
    private javax.swing.JRadioButton from;
    private javax.swing.JTextField id;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JComboBox<String> mat;
    private javax.swing.JTextField mat_id;
    private javax.swing.JRadioButton materiaux;
    private javax.swing.JRadioButton materiel;
    private javax.swing.JTextField nums;
    private javax.swing.JTextField qty;
    private javax.swing.JTextField qty_in;
    private javax.swing.JRadioButton supply;
    private javax.swing.JRadioButton to;
    private javax.swing.JTextField unity;
    private com.alee.extended.date.WebDateField webDateField1;
    // End of variables declaration//GEN-END:variables
}
