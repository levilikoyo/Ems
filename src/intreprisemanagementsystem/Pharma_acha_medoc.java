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
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.Date;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Dosh
 */
public class Pharma_acha_medoc extends javax.swing.JPanel {
   private Connection con;
private Statement st;
PreparedStatement pst=null;
ResultSet rs= null;
String rolls;
String NUM_ID,Table_click=null;
 DefaultTableModel mode;
 String ID=null;
  String IDS=null;
  String Total=null;
  String MAX=null;
    public Pharma_acha_medoc() {
        initComponents();
        con=JavaDbConnect.dbConnect();
        buss();
       dates.setDate(new Date());
       exp.setDate(new Date());
    }
  public void roll()
     {
         try{
                 
           
            String sql="SELECT NUM FROM   h_p_inventaire_medoc where STATUT='Achat' ORDER BY NUM DESC LIMIT 1";
             pst=con.prepareStatement(sql);
             rs=pst.executeQuery();
             if(rs.next()){
                 String rl=rs.getString("NUM");
                 int ln=rl.length();
                 String stxt=rl.substring(0,8);
                 String snum=rl.substring(8,ln);
                 int n = Integer.parseInt(snum);
                 n++;
                 snum=Integer.toString(n);
                 rolls=stxt+snum;
                 
                 
                  
            
                
             }else{
                 //rolls="FICHE/EB/2018/1";
                 rolls="MEDI-IN/1001";
             }
                 
             
         }catch(Exception e){
             JOptionPane.showMessageDialog(null, e); 
             
         }
     }
      
       public void rollS()
     {
         try{
                 
           
            String sql="SELECT NUM_ID FROM   h_p_inventaire_medoc where STATUT='Achat' ORDER BY NUM_ID DESC LIMIT 1";
             pst=con.prepareStatement(sql);
             rs=pst.executeQuery();
             if(rs.next()){
                 String rl=rs.getString("NUM_ID");
                 int ln=rl.length();
                 String stxt=rl.substring(0,9);
                 String snum=rl.substring(9,ln);
                 int n = Integer.parseInt(snum);
                 n++;
                 snum=Integer.toString(n);
                 NUM_ID=stxt+snum;
                 
                 
                  
            
                
             }else{
                 //rolls="FICHE/EB/2018/1";
                 NUM_ID="TRANS-IN/1001";
             }
                 
             
         }catch(Exception e){
             JOptionPane.showMessageDialog(null, e); 
             
         }
     }
public void buss(){
    
        
//          try{
//              
//            String sql="SELECT distinct(CLASSI) FROM   h_p_medicament";
//            
//            pst=con.prepareStatement(sql);
//            rs=pst.executeQuery();
//            while(rs.next()){
//                //String sum=rs.getString("nom");
//                String sums=rs.getString("CLASSI");
//                classes.addItem(sums);
//            }
//             
//            
//            
//            }
//        catch(Exception ex){
//          JOptionPane.showMessageDialog(null, ex);    
//        }
     try{
              
            String sql="SELECT * FROM   ohada Where SUBSTR=40";
            
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                //String sum=rs.getString("nom");
                String sums=rs.getString("NAME");
                four.addItem(sums);
            }
             
            
            
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
      try{
              
            String sql="SELECT SERVICE FROM   h_p_emballage";
            
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                //String sum=rs.getString("nom");
                String sums=rs.getString("SERVICE");
             //   marc.addItem(sums);
            }
             
            
            
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
   
    }
  public void Search2(){
         
          try{
              DefaultListModel list = new DefaultListModel();
             
          String sql="SELECT * FROM h_p_medicament where NOMS LIKE '"+search_medoc.getText()+"%'";
             
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                //String sum=rs.getString("nom");
                String sums=rs.getString("NOMS");
                 list.addElement(sums);
                
                 jList1.setModel(list);
            }
             
            
              
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
             
         }
  
  public void JLIST_from_COMPTE2()
    {
      
         String tmp =(String)jList1.getSelectedValue();
    
     
        try{
              
            String sql="SELECT * FROM   h_p_medicament Where NOMS='"+tmp+"' ";
            
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
              
     //   NOM,NOMS,CLASSI,CATEGORI,P_ACHAT,P_VENTE,D_UNITE,P_ACHAT_G,P_VENTE_G,G_VENTE,NUM,MARK,MARK_QTY,CONTAINER,CONTAINER_QTY,QTY_UNITE,VAL_UNITE,PV_$,PVG_$,PA_$,PAG_$        
                String sums1=rs.getString("P_ACHAT");
                qty_r.setText(sums1);
                
                String sums2=rs.getString("CONTAINER");
                marc.removeAllItems();
                marc.addItem(sums2);
                marc.setSelectedItem(sums2);
                
                 String sums22=rs.getString("CATEGORI");
                forme.setText(sums22);
                
                String sums23=rs.getString("VAL_UNITE");
                unite.setText(sums23);
                
                  
                      
            }
             
            
               
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
    
        }
  public void combo_from_COMPTE2()
    {
      
         String tmp =marc.getSelectedItem().toString();
    
     
        try{
              
            String sql="SELECT * FROM   h_p_medicament Where CONTAINER='"+tmp+"'  AND NOMS='"+jList1.getSelectedValue()+"'";
            
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
              
     //   NOM,NOMS,CLASSI,CATEGORI,P_ACHAT,P_VENTE,D_UNITE,P_ACHAT_G,P_VENTE_G,G_VENTE,NUM,MARK,MARK_QTY,CONTAINER,CONTAINER_QTY,QTY_UNITE,VAL_UNITE,PV_$,PVG_$,PA_$,PAG_$        
                String sums1=rs.getString("P_ACHAT_G");
                qty_r.setText(sums1);
                
               
                  
                      
            }
             
            
               
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
    
        }
 // CREATE TABLE "h_p_achat_medoc" MEDICAMENT,CAT,CLASSE,FOUR,P_ACHAT,QTY,P_TOTAL,BARCODE,EXPR,DATES,NUM
  public void SELECT_MAX(){
   try{
              
            String sql="SELECT MAX(NUM) FROM   h_p_inventaire_medoc where STATUT='Achat'";
            
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
              
     //   NOM,NOMS,CLASSI,CATEGORI,P_ACHAT,P_VENTE,D_UNITE,P_ACHAT_G,P_VENTE_G,G_VENTE,NUM,MARK,MARK_QTY,CONTAINER,CONTAINER_QTY,QTY_UNITE,VAL_UNITE,PV_$,PVG_$,PA_$,PAG_$        
              MAX=rs.getString("MAX(NUM)");
              num_fiche.setText(MAX);
                
                
               
                  
                      
            }
             
            
               
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
  
  }
  public void SELECT_SUM(){
   try{
              
            String sql="SELECT SUM(DEBIT) FROM    h_p_inventaire_medoc WHERE NUM='"+num_fiche.getText()+"'";
            
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
        //     Double a= Double.parseDouble(total.getText());
     //   NOM,NOMS,CLASSI,CATEGORI,P_ACHAT,P_VENTE,D_UNITE,P_ACHAT_G,P_VENTE_G,G_VENTE,NUM,MARK,MARK_QTY,CONTAINER,CONTAINER_QTY,QTY_UNITE,VAL_UNITE,PV_$,PVG_$,PA_$,PAG_$        
           Double   MAXs=rs.getDouble("SUM(DEBIT)");
           DecimalFormat df = new DecimalFormat("#,##0.00");
       
    total.setText(""+df.format(MAXs));
            
                
                
               
                  
                      
            }
             
            
               
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
 
  }
  Double QTY=null;
  String CLASSE;
   public void  calculeqty(){
  try{
      Double CONT,MARK;
              
            String sql="SELECT * FROM   h_p_medicament Where NOMS='"+jList1.getSelectedValue()+"' ";
            
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                CONT=rs.getDouble("CONTAINER_QTY");
              
                
                 MARK=rs.getDouble("MARK_QTY");
                   CLASSE=rs.getString("CLASSI");
   
       Double BB = Double.parseDouble(qty.getText());
       QTY=(BB/MARK)*CONT;
       //Total=c.toString();  
                      
            }
             
            
               
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
    
  }
   public void save(){
       calculeqty();
       Double aa = Double.parseDouble(qty_r.getText());
       Double bb = Double.parseDouble(qty.getText());
       Double c;
       c=aa*bb;
       Total=c.toString();
       
       rollS();
       if(num_fiche.getText().equals("")){
        roll();
//               try{
//          
//           
//     
//         // java.sql.Connection con = getConnection();
//     String sql="INSERT INTO h_p_achat_medoc (MEDICAMENT,CAT,CLASSE,FOUR,P_ACHAT,QTY,P_TOTAL,BARCODE,EXPR,DATES,NUM,NUM_ID,QTY_D)"+
//             "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
//     pst=con.prepareStatement(sql);
//    
//      pst.setString(1, jList1.getSelectedValue());
//      pst.setString(2, forme.getText());
//      pst.setString(3, classes.getSelectedItem().toString());
//      pst.setString(4, four.getSelectedItem().toString());
//      pst.setString(5, qty_r.getText());
//      
//      pst.setDouble(13,QTY);
//      pst.setString(7, Total);
//      pst.setString(8, barcode.getText());
//      pst.setString(9, exp.getText());
//      pst.setString(10,webDateField2.getText());
//      pst.setString(11, rolls);
//       pst.setString(12, NUM_ID);
//       pst.setString(6, qty.getText());
//    
//   
//      
//      pst.executeUpdate();
//                
//              
//  // JOptionPane.showMessageDialog(null,"Transaction Saved");
//      
//   //  cat1.setText("");
//      
//     }catch(Exception ex){JOptionPane.showMessageDialog(null,ex);} 
//               
                 try{
          
           
     
         ////                                       MEDICAMENT,CAT,CLASSE,FOUR,P_ACHAT,QTY,P_TOTAL,BARCODE,EXPR,DATES,NUM,NUM_ID,QTY_D
     String sql="INSERT INTO  h_p_inventaire_medoc (MEDICAMENT,CAT,CLASSI,QTY_DEBIT,QTY_CREDIT,DEBIT,CREDIT,NUM_ID,NUM,FOUR,STATUT,QTY_DD,QTY_DC,UP,DATE_EXP,DATES,MARK,LOT,UNITE)"+
             "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     pst=con.prepareStatement(sql);
    
      pst.setString(1, jList1.getSelectedValue());
      pst.setString(2, forme.getText());
      pst.setString(3, CLASSE);
     
      pst.setString(5,"");
      
      pst.setDouble(12,QTY);
      pst.setString(6, Total);
      pst.setString(7, "");
      pst.setString(8, NUM_ID);
        pst.setString(9, rolls);
      pst.setString(10, four.getSelectedItem().toString());
      pst.setString(11,"Achat");
       pst.setString(4,qty.getText());
        pst.setString(13,"");
        pst.setString(14,qty_r.getText());
        
        pst.setString(15,exp.getText());
        pst.setString(16,dates.getText());
         pst.setString(17,marc.getSelectedItem().toString());
         pst.setString(18,lot.getText());
         pst.setString(19,unite.getText());
    
   
      
      pst.executeUpdate();
                
      
     }catch(Exception ex){JOptionPane.showMessageDialog(null,ex);}  
               SELECT_MAX();
       }else{
//               try{
//          
//           
//     
//         // java.sql.Connection con = getConnection();
//     String sql="INSERT INTO h_p_achat_medoc (MEDICAMENT,CAT,CLASSE,FOUR,P_ACHAT,QTY,P_TOTAL,BARCODE,EXPR,DATES,NUM,NUM_ID,QTY_D)"+
//             "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
//     pst=con.prepareStatement(sql);
//    
//      pst.setString(1, jList1.getSelectedValue());
//      pst.setString(2, forme.getText());
//      pst.setString(3, classes.getSelectedItem().toString());
//      pst.setString(4, four.getSelectedItem().toString());
//      pst.setString(5, qty_r.getText());
//      
//      pst.setDouble(13,QTY);
//      pst.setString(7, Total);
//      pst.setString(8, barcode.getText());
//      pst.setString(9, exp.getText());
//      pst.setString(10,webDateField2.getText());
//      pst.setString(11, num_fiche.getText());
//       pst.setString(12, NUM_ID);
//       pst.setString(6, qty.getText());
//    
//   
//      
//      pst.executeUpdate();
//                
//              
//   //JOptionPane.showMessageDialog(null,"Transaction Saved");
//      
//   //  cat1.setText("");
//      
//     }catch(Exception ex){JOptionPane.showMessageDialog(null,ex);}  
              
    
         
           try{
          
           
     
         ////   h_p_inventaire_medoc NUM_ID,CAT,CLASSI,QTY_DEBIT,QTY_CREDIT,DEBIT,CREDIT,NUM
     String sql="INSERT INTO  h_p_inventaire_medoc (MEDICAMENT,CAT,CLASSI,QTY_DEBIT,QTY_CREDIT,DEBIT,CREDIT,NUM_ID,NUM,FOUR,STATUT,QTY_DD,QTY_DC,UP,DATE_EXP,DATES,MARK,LOT,UNITE)"+
             "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     pst=con.prepareStatement(sql);
    
      pst.setString(1, jList1.getSelectedValue());
      pst.setString(2, forme.getText());
      pst.setString(3, CLASSE);
     
      pst.setString(5,"");
      
      pst.setDouble(12,QTY);
      pst.setString(6, Total);
      pst.setString(7, "");
      pst.setString(8, NUM_ID);
        pst.setString(9, num_fiche.getText());
      pst.setString(10, four.getSelectedItem().toString());
      pst.setString(11,"Achat");
        pst.setString(4,qty.getText());
        pst.setString(13,"");
        pst.setString(14,qty_r.getText());
         pst.setString(15,exp.getText());
        pst.setString(16,dates.getText());
        pst.setString(17,marc.getSelectedItem().toString());
        pst.setString(18,lot.getText());
        pst.setString(19,unite.getText());
    
   
      
      pst.executeUpdate();
                
      
     }catch(Exception ex){JOptionPane.showMessageDialog(null,ex);}  
              
   }
       SELECT_SUM();
             }

  public void refresh(){
  
    forme.setText("");
//    classes.setSelectedItem("......");
    four.setSelectedItem("......");
    qty_r.setText("");
      
    qty.setText("");
    Total=null;
      barcode.setText("");
       exp.setText("");
      dates.setText("");
    
  }

   public void deleteS()
    {
        int rows= jTable1.getSelectedRow();
      Table_click = (jTable1.getModel().getValueAt(rows,4). toString()); 
       for(int row = 0; row<rows; row++)
  {
         try{
        String sql = "DELETE FROM h_p_inventaire_medoc  WHERE NUM_ID=?";
         
         pst = con.prepareStatement(sql);
         pst.setString(1,Table_click);
         pst.executeUpdate();

     }catch(SQLException | HeadlessException ex ){
         JOptionPane.showMessageDialog(null,ex);
     } 
  }
       Table_click=null;
    }
      public void call_in_tableS(){
  //   MEDICAMENT,CAT,CLASSE,FOUR,P_ACHAT,QTY,P_TOTAL,BARCODE,EXPR,DATES,NUM
	
	
      try{
            // HERE
           ///MEDICAMENT,CAT,CLASSI,QTY_DEBIT,QTY_CREDIT,DEBIT,CREDIT,NUM_ID,NUM,FOUR,STATUT,QTY_DD,QTY_DC
             String sql="SELECT MEDICAMENT,QTY_DEBIT AS QTY,UP,DEBIT AS PT,NUM_ID FROM  h_p_inventaire_medoc where NUM='"+num_fiche.getText()+"'";
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
       
        
       
       
      
       
       col0.setPreferredWidth(300);
       col1.setPreferredWidth(20);
       col2.setPreferredWidth(20);
         col3.setPreferredWidth(50);
         col4.setPreferredWidth(50);
      
        
      
      
     
       
       
      // jTable1.setModel(mode);
       
     
    }catch(Exception ex){JOptionPane.showMessageDialog(null,ex);}  
     
     }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        exp = new com.alee.extended.date.WebDateField();
        qty_r = new javax.swing.JTextField();
        qty = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        lot = new javax.swing.JTextField();
        unite = new javax.swing.JTextField();
        jPanel10 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        four = new javax.swing.JComboBox<>();
        jPanel6 = new javax.swing.JPanel();
        search_medoc = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jPanel13 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        forme = new javax.swing.JTextField();
        marc = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        barcode = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        num_fiche = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        webComboBox1 = new com.alee.laf.combobox.WebComboBox();
        dates = new com.alee.extended.date.WebDateField();
        jPanel7 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        total = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        jScrollPane10 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setBackground(new java.awt.Color(192, 255, 192));

        jPanel2.setBackground(new java.awt.Color(192, 255, 192));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jPanel3.setBackground(new java.awt.Color(128, 255, 128));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel15.setText("Achat Medicament");

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("X");
        jLabel16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel16MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 479, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
            .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel4.setBackground(new java.awt.Color(192, 255, 192));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jPanel9.setBackground(new java.awt.Color(192, 255, 192));
        jPanel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("Exp. Date:");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("Pix de Rev.:");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setText("Quantites:");

        exp.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        qty_r.setEditable(false);
        qty_r.setBackground(new java.awt.Color(240, 240, 241));
        qty_r.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        qty_r.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        qty.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        qty.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        qty.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                qtyKeyPressed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Num. Lot:");

        lot.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lot.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        unite.setEditable(false);
        unite.setBackground(new java.awt.Color(240, 240, 241));
        unite.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(9, 9, 9)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(qty_r, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
                    .addComponent(exp, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lot)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel9Layout.createSequentialGroup()
                        .addComponent(qty)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(unite, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(lot, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(exp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(qty_r, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(unite, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(qty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel9))))
                    .addComponent(jLabel7))
                .addContainerGap())
        );

        jPanel10.setBackground(new java.awt.Color(192, 255, 192));
        jPanel10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Fournisseur");
        jPanel10.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, -1));

        four.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        four.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "......." }));
        jPanel10.add(four, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 210, -1));

        jPanel6.setBackground(new java.awt.Color(192, 255, 192));
        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        search_medoc.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        search_medoc.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        search_medoc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                search_medocKeyReleased(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("Medicament");

        jList1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jList1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jList1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jList1);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(search_medoc))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(search_medoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel13.setBackground(new java.awt.Color(192, 255, 192));
        jPanel13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel13.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("F. Pharma.");
        jPanel13.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        forme.setEditable(false);
        forme.setBackground(new java.awt.Color(240, 240, 241));
        forme.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        forme.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel13.add(forme, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 92, -1));

        marc.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        marc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "......." }));
        marc.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                marcPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        jPanel13.add(marc, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 30, 95, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("Marque");
        jPanel13.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 10, -1, -1));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel5.setBackground(new java.awt.Color(192, 255, 192));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        barcode.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        barcode.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jTextField6.setEditable(false);
        jTextField6.setBackground(new java.awt.Color(240, 240, 241));
        jTextField6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        num_fiche.setEditable(false);
        num_fiche.setBackground(new java.awt.Color(240, 240, 241));
        num_fiche.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        num_fiche.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setText("Barcode:");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setText("Recherche:");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setText("Numéro Fiche Entre:");

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel13.setText("Date:");

        webComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "......." }));
        webComboBox1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        dates.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11))
                .addGap(25, 25, 25)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(barcode)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel12)
                        .addGap(18, 18, 18))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(webComboBox1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel13)
                        .addGap(26, 26, 26)))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(num_fiche)
                    .addComponent(dates, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(barcode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(num_fiche, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel11)
                        .addComponent(jLabel13)
                        .addComponent(dates, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(webComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel7.setBackground(new java.awt.Color(192, 255, 192));
        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel14.setText("Total General Achat");

        total.setEditable(false);
        total.setBackground(new java.awt.Color(240, 240, 241));
        total.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        total.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 166, Short.MAX_VALUE)
                .addComponent(total, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(total, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18))
        );

        jPanel8.setBackground(new java.awt.Color(192, 255, 192));
        jPanel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton1.setText("Nouveau");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton2.setText("Add");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton3.setText("Remove");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton4.setText("Save");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel11.setBackground(new java.awt.Color(192, 255, 192));
        jPanel11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jTable1.setBackground(new java.awt.Color(192, 255, 192));
        jTable1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable1.setSelectionForeground(new java.awt.Color(198, 215, 233));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTable1MousePressed(evt);
            }
        });
        jScrollPane10.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane10)
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
              // TODO add your handling code here:
    }//GEN-LAST:event_jLabel1MouseClicked

    private void qtyKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_qtyKeyPressed
 int key = evt.getKeyCode();
     if (key == KeyEvent.VK_ENTER) {
        Toolkit.getDefaultToolkit().beep(); 
        save(); 
call_in_tableS();  
     }// TODO add your handling code here:
    }//GEN-LAST:event_qtyKeyPressed

    private void search_medocKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_search_medocKeyReleased
Search2();            // TODO add your handling code here:
    }//GEN-LAST:event_search_medocKeyReleased

    private void jList1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList1MouseClicked
   JLIST_from_COMPTE2();    // TODO add your handling code here:
    }//GEN-LAST:event_jList1MouseClicked

    private void marcPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_marcPopupMenuWillBecomeInvisible
 combo_from_COMPTE2(); // TODO add your handling code here:
    }//GEN-LAST:event_marcPopupMenuWillBecomeInvisible

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
save(); 
call_in_tableS();// TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
int row= jTable1.getSelectedRow();
          String Table_click = (jTable1.getModel().getValueAt(row,3). toString());       // TODO add your handling code here:
    }//GEN-LAST:event_jTable1MouseClicked

    private void jTable1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable1MousePressed

    private void jLabel16MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel16MouseClicked
this.show(false);        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel16MouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
deleteS(); 
call_in_tableS();
SELECT_SUM();// TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField barcode;
    private com.alee.extended.date.WebDateField dates;
    private com.alee.extended.date.WebDateField exp;
    private javax.swing.JTextField forme;
    private javax.swing.JComboBox<String> four;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList<String> jList1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField lot;
    private javax.swing.JComboBox<String> marc;
    private javax.swing.JTextField num_fiche;
    private javax.swing.JTextField qty;
    private javax.swing.JTextField qty_r;
    private javax.swing.JTextField search_medoc;
    private javax.swing.JTextField total;
    private javax.swing.JTextField unite;
    private com.alee.laf.combobox.WebComboBox webComboBox1;
    // End of variables declaration//GEN-END:variables
}
