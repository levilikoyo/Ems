/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intreprisemanagementsystem;

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
import java.util.concurrent.TimeUnit;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author DOSHE
 */
public class equipement_return extends javax.swing.JInternalFrame {
    Connection con=null;
PreparedStatement pst=null;
ResultSet rs= null;
//DefaultTableModel mode;
 String rolls;
 String NUM_ID;
 DefaultTableModel mode;
  String CHECK=null;
    public equipement_return() {
        initComponents();
              con=JavaDbConnect.dbConnect();
        call_table();
        webDateField1.setDate(new Date());
        call_tablerequest();  
    }
    
     public void call_table(){
      
           try{
           
             String sql="SELECT distinct(`NUM`),`EMP_NAME` FROM `DOTATION` WHERE STATUS=''";
     //  String sql="SELECT ID, `NUM_FACTURE`, `DATE_FACTURE`, `DESCRIPTION`, `DEBIT_QTY`, `DEBIT_PU`, `DEBIT_PT`, `DEBIT_TVA`, `CREDIT_QTY`, `CREDIT_PU`, `CREDIT_PT`, `CREDIT_TVA`, `DATES`, `NUM` FROM facture_fournisseur where compte='"+jComboBox6.getSelectedItem()+"'";
       pst = con.prepareStatement(sql);
      rs= pst.executeQuery();
      
       
       jTable1.setModel(DbUtils.resultSetToTableModel(rs));
       mode=new DefaultTableModel();
       
       
        TableColumn col0=jTable1.getColumnModel().getColumn(0);
        TableColumn col1=jTable1.getColumnModel().getColumn(1);
      
       
       col0.setPreferredWidth(50);
       col1.setPreferredWidth(100);
       
     
      
       
       
      // jTable1.setModel(mode);
       
     
    }catch(SQLException ex ){
     JOptionPane.showMessageDialog(null, ex);
}
    
      
      }
 Date a;
     String datein;
     Date days;
    long diff;
   
     public void datete(){
         
          try{
                //Class.forName("org.sqlite.JDBC");
          
            String sql="select DUREE  from  dodation where status=''";
          
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
          while(rs.next()){
               datein =rs.getString("DUREE");
 //             jTextField2.setText(datein);
                 
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
         
         
         
     
   
     }
     
       public void call_tablerequest(){
            Date datout=new Date();
 SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd.MM.yyyy");
         String addDate1 = dateFormat1.format(datout);
         /*  Integer aaa ;  
        SimpleDateFormat myFormat = new SimpleDateFormat("dd MM yyyy");
         
         emp.setText(""+addDate1);
        
         try{
                //Class.forName("org.sqlite.JDBC");
          
            String sql="select DUREE  from   dotation where status=''";
          
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
          while(rs.next()){
               datein =rs.getString("DUREE");
              num.setText(""+datein);
                 
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
//String inputString1 = datein;//datein
//String inputString2 = addDate1;//nowdate
Integer aa = Integer.parseInt(datein);
Integer aa1 = Integer.parseInt(addDate1);

aaa = aa - aa1;
Eqi.setText(""+aaa);
      */
           try{
           
             String sql="SELECT distinct(`SERIE`),`EMP_NAME` FROM `DOTATION` WHERE STATUS='' and DUREE<= '"+addDate1+"'";
     //  String sql="SELECT ID, `NUM_FACTURE`, `DATE_FACTURE`, `DESCRIPTION`, `DEBIT_QTY`, `DEBIT_PU`, `DEBIT_PT`, `DEBIT_TVA`, `CREDIT_QTY`, `CREDIT_PU`, `CREDIT_PT`, `CREDIT_TVA`, `DATES`, `NUM` FROM facture_fournisseur where compte='"+jComboBox6.getSelectedItem()+"'";
       pst = con.prepareStatement(sql);
      rs= pst.executeQuery();
      
       
       jTable2.setModel(DbUtils.resultSetToTableModel(rs));
       mode=new DefaultTableModel();
       
       
        TableColumn col0=jTable2.getColumnModel().getColumn(0);
        TableColumn col1=jTable2.getColumnModel().getColumn(1);
      
       
       col0.setPreferredWidth(100);
       col1.setPreferredWidth(50);
       
     
      
       
       
      // jTable1.setModel(mode);
       
     
    }catch(SQLException ex ){
     JOptionPane.showMessageDialog(null, ex);
}
    
      
      }
     
      public void show_photo_from_db()
               
   {
       int row= jTable1.getSelectedRow();
          String Table_clicks = (jTable1.getModel().getValueAt(row,0). toString());
            try{
          
         // String Table_click = (jTable1.getModel().getValueAt(row,3). toString());
          String sql = "SELECT  `serie` AS 'ID', `MATERIEL`,`DATES`, `DUREE` AS 'DATE OFF', `QTY_OUT` as 'QTY' FROM  DOTATION WHERE num= '"+Table_clicks+"' and STATUS =''";
          pst = con.prepareStatement(sql);
          rs=pst.executeQuery();
          jTable3.setModel(DbUtils.resultSetToTableModel(rs));
       mode=new DefaultTableModel();
       
       
        TableColumn col0=jTable3.getColumnModel().getColumn(0);
        TableColumn col1=jTable3.getColumnModel().getColumn(1);
         TableColumn col2=jTable3.getColumnModel().getColumn(2);
        TableColumn col3=jTable3.getColumnModel().getColumn(3);
         TableColumn col4=jTable3.getColumnModel().getColumn(4);
       
       // TableColumn col4=jTable1.getColumnModel().getColumn(4);
        
       
       //`EMP_CHANTIER` as 'Site', `MATERIEL`,`DATES`, `DUREE`, `QTY_OUT`
      
       
       col0.setPreferredWidth(150);
       col1.setPreferredWidth(80);
        col2.setPreferredWidth(30);
       col3.setPreferredWidth(30);
       col4.setPreferredWidth(20);
      
       //col4.setPreferredWidth(100);
         
    }catch(SQLException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }
   }
      
      
        public void show_photo_()
               
   {
       int row= jTable3.getSelectedRow();
          String Table_clicks = (jTable3.getModel().getValueAt(row,0). toString());
            try{
          
         // String Table_click = (jTable1.getModel().getValueAt(row,3). toString());
          String sql = "SELECT  * FROM  DOTATION WHERE serie= '"+Table_clicks+"'";
          pst = con.prepareStatement(sql);
          rs=pst.executeQuery();
          
          if(rs.next()){
         
       String sums=rs.getString("EMP_NAME");
        emp.setText(sums);
                  
                  String sums11=rs.getString("EMP_NAME");
                  emp.setText(sums11);
                  //`EMP_NAME`, `POST`, `EMP_CHANTIER`, `MATERIEL`, `DUREE`, `DATES`, `QTY_OUT`
                  String sums12=rs.getString("POST");
                  post.setText(sums12);
                  
                  String sums13=rs.getString("MATERIEL");
                  Eqi.setText(sums13);
                  
                  String sums14=rs.getString("serie");
                  num.setText(sums14);
                  
                    String sums15=rs.getString("qty_out");
                  qty.setText(sums15);
                  
                    CHECK="T3";
                  
                 //  String sums14=rs.getString("EMP_NAME");
                 // webDateField1.setText(sums14);
          }
         
    }catch(SQLException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }
   }
           public void show_photo_S()
               
   {
       int row= jTable2.getSelectedRow();
          String Table_clicks = (jTable2.getModel().getValueAt(row,0). toString());
            try{
          
         // String Table_click = (jTable1.getModel().getValueAt(row,3). toString());
          String sql = "SELECT  * FROM  DOTATION WHERE serie= '"+Table_clicks+"'";
          pst = con.prepareStatement(sql);
          rs=pst.executeQuery();
          
          if(rs.next()){
         
       String sums=rs.getString("EMP_NAME");
        emp.setText(sums);
                  
                  String sums11=rs.getString("EMP_NAME");
                  emp.setText(sums11);
                  //`EMP_NAME`, `POST`, `EMP_CHANTIER`, `MATERIEL`, `DUREE`, `DATES`, `QTY_OUT`
                  String sums12=rs.getString("POST");
                  post.setText(sums12);
                  
                  String sums13=rs.getString("MATERIEL");
                  Eqi.setText(sums13);
                  
                  String sums14=rs.getString("serie");
                  num.setText(sums14);
                  
                    String sums15=rs.getString("qty_out");
                  qty.setText(sums15);
                   CHECK="T2";
                  
                 //  String sums14=rs.getString("EMP_NAME");
                 // webDateField1.setText(sums14);
          }
         
    }catch(SQLException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }
   }
        
        
        
        //RETURN
        
         public void etroll()
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
  public void numid()
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
        
        String ITEM_IDS,ITEMS,DESCRS,SERIALS,MODELS,STATUTS,QTYS,UNITYS,DATESS,NUM_IDS;
        
        
        public void call_equipementT3(){
         int row= jTable3.getSelectedRow();
          String Table_click = (jTable3.getModel().getValueAt(row,0). toString());
         try{
            String sql="SELECT ITEM,DESCR,SERIAL,MODEL,STATUT,QTY,UNITY,DATES FROM  equipement_in WHERE ITEM_ID='"+Table_click +"'";
            

            
             pst=con.prepareStatement(sql);
             rs=pst.executeQuery();
             if(rs.next()){
                 
             
                // ITEM_IDS =rs.getString("EMP_NAME");
               ITEMS =rs.getString("ITEM");
               DESCRS =rs.getString("DESCR");
               SERIALS =rs.getString("SERIAL");
               MODELS =rs.getString("MODEL");
               STATUTS =rs.getString("STATUT");
               QTYS =rs.getString("QTY");
               UNITYS =rs.getString("UNITY");
               DATESS =rs.getString("DATES");
                  
                 
                 
             }
                   }catch(Exception e){
             JOptionPane.showMessageDialog(null, e); 
             
         }
        
         try{
            String sql="SELECT NUM_ID FROM dotation WHERE SERIE='"+Table_click +"' and status=''";
            

            
             pst=con.prepareStatement(sql);
             rs=pst.executeQuery();
            while(rs.next()){
                 
             
                // ITEM_IDS =rs.getString("EMP_NAME");
               NUM_IDS =rs.getString("NUM_ID");                 
             }
                   }catch(Exception e){
             JOptionPane.showMessageDialog(null, e); 
             
         }
        
        }
        
        public void call_equipementT2(){
         int row= jTable2.getSelectedRow();
          String Table_click = (jTable2.getModel().getValueAt(row,0). toString());
         try{
            String sql="SELECT ITEM,DESCR,SERIAL,MODEL,STATUT,QTY,UNITY,DATES FROM  equipement_in WHERE ITEM_ID='"+Table_click +"'";
            

            
             pst=con.prepareStatement(sql);
             rs=pst.executeQuery();
             if(rs.next()){
                 
             
                // ITEM_IDS =rs.getString("EMP_NAME");
               ITEMS =rs.getString("ITEM");
               DESCRS =rs.getString("DESCR");
               SERIALS =rs.getString("SERIAL");
               MODELS =rs.getString("MODEL");
               STATUTS =rs.getString("STATUT");
               QTYS =rs.getString("QTY");
               UNITYS =rs.getString("UNITY");
               DATESS =rs.getString("DATES");
                  
                 
                 
             }
                   }catch(Exception e){
             JOptionPane.showMessageDialog(null, e); 
             
         }
         
          try{
            String sql="SELECT NUM_ID FROM dotation WHERE SERIE='"+Table_click +"' and status=''";
            

            
             pst=con.prepareStatement(sql);
             rs=pst.executeQuery();
            while(rs.next()){
                 
             
                // ITEM_IDS =rs.getString("EMP_NAME");
               NUM_IDS =rs.getString("NUM_ID");                 
             }
                   }catch(Exception e){
             JOptionPane.showMessageDialog(null, e); 
             
         }
        
        
        
        }
        
        
        
          public void saves(){
              if(CHECK.equals("T3")){
               call_equipementT3();
              }else{
                call_equipementT2();
              }

 etroll();
 numid();
 try{
    String sql="INSERT INTO `equipement_in`(`ITEM_ID`, `ITEM`, `DESCR`,`SERIAL`, `MODEL`, `STATUT`, `QTY`, `UNITY`, `DATES`, `NUM_ID`, `NUM`, `PHOTOS`,`PRICE`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
     
    pst=con.prepareStatement(sql);
    pst.setString(1,num.getText());
    pst.setString(2,ITEMS);
    pst.setString(3,DESCRS);
    pst.setString(4,SERIALS);
    pst.setString(5,MODELS);
    pst.setString(6,jComboBox1.getSelectedItem().toString());
     pst.setString(7,qty.getText());
     pst.setString(8,UNITYS);
     
      pst.setString(9,webDateField1.getText());
       pst.setString(10,NUM_ID);
        pst.setString(11,rolls);
        pst.setString(12,"");
        pst.setString(13,"");
    
    pst.executeUpdate();
     // JOptionPane.showMessageDialog(null,"Datas Saved");
    }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}
 
 try{//,,,,,QTYS,UNITYS,DATESS;
    String sql="INSERT INTO `eqipement_trans`(`ITEM_ID`, `ITEM`, `DESCR`, `SERIAL`, `MODEL`, `STATUT`, `DEBIT`, `CREDIT`, `NUM_ID`, `NUM`, `EMP`, `PURPOSE`,`DATES`, `SITE`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     
    pst=con.prepareStatement(sql);
    pst.setString(1,num.getText());
    pst.setString(2,ITEMS);
    pst.setString(3,DESCRS);
    pst.setString(4,SERIALS);
    pst.setString(5,MODELS);
     pst.setString(6,jComboBox1.getSelectedItem().toString());
     pst.setString(7,qty.getText());
     
      pst.setString(8,"0");
       pst.setString(9,NUM_ID);
        pst.setString(10,num.getText());
        
       pst.setString(11,emp.getText());
       pst.setString(12,"Returned");
       
       pst.setString(13, webDateField1.getText());
       pst.setString(14,"LOG");
        
        
      
    
    pst.executeUpdate();
      JOptionPane.showMessageDialog(null,"Equipement Restituer avec status   "+jComboBox1.getSelectedItem().toString()+"");
    }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}
    
              
 
 }
          
           public void save_TRANSs(){
 //etroll();
 //numid();
 try{//,,,,,QTYS,UNITYS,DATESS;
    String sql="INSERT INTO `eqipement_trans`(`ITEM_ID`, `ITEM`, `DESCR`, `SERIAL`, `MODEL`, `STATUT`, `DEBIT`, `CREDIT`, `NUM_ID`, `NUM`, `EMP`, `PURPOSE`,`DATES`, `SITE`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     
    pst=con.prepareStatement(sql);
    pst.setString(1,num.getText());
    pst.setString(2,ITEMS);
    pst.setString(3,DESCRS);
    pst.setString(4,SERIALS);
    pst.setString(5,MODELS);
     pst.setString(6,STATUTS);
     pst.setString(7,qty.getText());
     
      pst.setString(8,"0");
       pst.setString(9,NUM_ID);
        pst.setString(10,num.getText());
        
        pst.setString(11,emp.getText());
        pst.setString(12,"Returned");
        
        pst.setString(13, webDateField1.getText());
       pst.setString(14,"LOG");
        
        
        
        
      
    
    pst.executeUpdate();
     // JOptionPane.showMessageDialog(null,"Datas Saved");
    }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}
    
 
 
 }
  public void UDATEtatus(){

 try{
    String sql="UPDATE  `etat` SET `STATUS`=? Where ITEM_ID='"+num.getText()+"'";
     
    pst=con.prepareStatement(sql);
   
    pst.setString(1,jComboBox1.getSelectedItem().toString());
  
    
    pst.executeUpdate();
     // JOptionPane.showMessageDialog(null,"Datas Saved");
    }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}
    
 UDATEtatusRETURNE();
 
 }
  
  public void UDATEtatusRETURNE(){
    /* if(CHECK.equals("T2")){
 int row= jTable2.getSelectedRow();
          String Table_click = (jTable2.getModel().getValueAt(row,0). toString());
         
         try{
            String sql="SELECT NUM_ID FROM dotation WHERE SERIE='"+Table_click +"' and status=''";
            

            
             pst=con.prepareStatement(sql);
             rs=pst.executeQuery();
            while(rs.next()){
                 
             
                // ITEM_IDS =rs.getString("EMP_NAME");
               ITEMS =rs.getString("NUM_ID");                 
             }
                   }catch(Exception e){
             JOptionPane.showMessageDialog(null, e); 
             
         }
     }else if(CHECK.equals("T3")){
          int row= jTable3.getSelectedRow();
          String Table_click = (jTable3.getModel().getValueAt(row,0). toString());
         
         try{
            String sql="SELECT NUM_ID FROM dotation WHERE SERIE='"+Table_click +"' and status=''";
            

            
             pst=con.prepareStatement(sql);
             rs=pst.executeQuery();
             while(rs.next()){
                 
             
                // ITEM_IDS =rs.getString("EMP_NAME");
               ITEMS =rs.getString("NUM_ID");                 
             }
                   }catch(Exception e){
             JOptionPane.showMessageDialog(null, e); 
             
         }       
                 
                 
                 } */ 
 try{
    String sql="UPDATE  `dotation` SET `STATUS`=? Where NUM_ID='"+NUM_IDS+"'";
     
    pst=con.prepareStatement(sql);
   
    pst.setString(1,"Returned");
  
    
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
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        num = new javax.swing.JTextField();
        Eqi = new javax.swing.JTextField();
        post = new javax.swing.JTextField();
        emp = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        webDateField1 = new com.alee.extended.date.WebDateField();
        qty = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_Maintenance_16px.png"))); // NOI18N

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Liste des Matériel & Equipement Doté", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N

        jTable1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
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
        jScrollPane2.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 369, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Liste des Matériel & Equipement A retourner", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N

        jTable2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTable2);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N

        jTable3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable3MouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(jTable3);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 547, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Commandes", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N

        num.setEditable(false);
        num.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        num.setEnabled(false);

        Eqi.setEditable(false);
        Eqi.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Eqi.setEnabled(false);

        post.setEditable(false);
        post.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        post.setEnabled(false);

        emp.setEditable(false);
        emp.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        emp.setEnabled(false);

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton1.setText("Requette");
        jButton1.setEnabled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton2.setText("Rapport");
        jButton2.setEnabled(false);

        jButton3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton3.setText("Retourner");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        webDateField1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        qty.setEditable(false);
        qty.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        qty.setEnabled(false);

        jComboBox1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "New", "Used", "Opsolete" }));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(num)
                    .addComponent(emp)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Eqi, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(post)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(webDateField1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel5Layout.createSequentialGroup()
                            .addComponent(qty, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(num, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Eqi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(webDateField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(emp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(post, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(qty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jMenu1.setText("X");
        jMenu1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jMenu1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu1MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu1);

        jMenu2.setText("File");
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

        setBounds(80, 10, 1026, 426);
    }// </editor-fold>//GEN-END:initComponents

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
show_photo_from_db();      // select_jTable();
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable1MouseClicked

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
//show_photo_S();        // TODO add your handling code here:
    }//GEN-LAST:event_jTable2MouseClicked

    private void jTable3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable3MouseClicked
show_photo_();        // TODO add your handling code here:
    }//GEN-LAST:event_jTable3MouseClicked

    private void jMenu1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu1MouseClicked
this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu1MouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
saves();  
UDATEtatus();
show_photo_from_db();
call_table();
call_tablerequest();
num.setText("");
Eqi.setText("");
emp.setText("");
post.setText("");
qty.setText("");
CHECK=null;
// TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
      // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Eqi;
    private javax.swing.JTextField emp;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTextField num;
    private javax.swing.JTextField post;
    private javax.swing.JTextField qty;
    private com.alee.extended.date.WebDateField webDateField1;
    // End of variables declaration//GEN-END:variables
}
