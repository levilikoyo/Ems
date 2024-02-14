/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intreprisemanagementsystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author DOSHE
 */
public class ohada_log2 extends javax.swing.JPanel {

    DefaultTableModel mode;
Connection con=null;
PreparedStatement pst=null;
ResultSet rs= null;
String ID=null;
String check=null;
String rolls;
    public ohada_log2() {
        initComponents();
              con=JavaDbConnectUMCO.dbConnect();
       // call_in_table();
        attLIST_from_EMPLOYEView_used();
        Groupe1();
    }
    
      public void Groupe1(){
ButtonGroup bg1 = new ButtonGroup();
bg1.add(produit);
bg1.add(stockin);
bg1.add(stockout);
         }
public void call_in_table(){
     String sql;
	if(produit.isSelected()){
 sql="SELECT NAME FROM ohada_pos where STATUS='0'";
 bus.setEditable(false);
        }else if(stockin.isSelected()){
        
sql="SELECT NAME FROM ohada_pos where STATUS2='0'";
bus.setEditable(false);
        }else{
sql="SELECT NAME FROM ohada_pos where STATUS3='0'";
//bus.setEditable(true);
        }
	
      try{
       
            
                
         
     //  String sql="SELECT ID, `NUM_FACTURE`, `DATE_FACTURE`, `DESCRIPTION`, `DEBIT_QTY`, `DEBIT_PU`, `DEBIT_PT`, `DEBIT_TVA`, `CREDIT_QTY`, `CREDIT_PU`, `CREDIT_PT`, `CREDIT_TVA`, `DATES`, `NUM` FROM facture_fournisseur where compte='"+jComboBox6.getSelectedItem()+"'";
       pst = con.prepareStatement(sql);
      rs= pst.executeQuery();
      
       
       jTable3.setModel(DbUtils.resultSetToTableModel(rs));
       mode=new DefaultTableModel();
       
       
      // jTable1.setModel(mode);
       
     
    }catch(Exception ex){JOptionPane.showMessageDialog(null,ex);}  
      
     try{
       
             String sqls="SELECT intitule_compte AS 'Compte',numero_compte as 'Code' FROM  parametre_default";
                
         
     //  String sql="SELECT ID, `NUM_FACTURE`, `DATE_FACTURE`, `DESCRIPTION`, `DEBIT_QTY`, `DEBIT_PU`, `DEBIT_PT`, `DEBIT_TVA`, `CREDIT_QTY`, `CREDIT_PU`, `CREDIT_PT`, `CREDIT_TVA`, `DATES`, `NUM` FROM facture_fournisseur where compte='"+jComboBox6.getSelectedItem()+"'";
       pst = con.prepareStatement(sqls);
      rs= pst.executeQuery();
      
       
       jTable4.setModel(DbUtils.resultSetToTableModel(rs));
       mode=new DefaultTableModel();
       
       
     TableColumn col0=jTable4.getColumnModel().getColumn(0);
       
        TableColumn col1=jTable4.getColumnModel().getColumn(1);
        
        
       
       
      
       col0.setPreferredWidth(200);
       col1.setPreferredWidth(50);
      
     
    }catch(Exception ex){JOptionPane.showMessageDialog(null,ex);}  
     
     
     }
   public void select_jTableSERVICE2()
   {
       
         try{
          int row= jTable3.getSelectedRow();
          String Table_click = (jTable3.getModel().getValueAt(row,0). toString());
          String sql = "SELECT * FROM   ohada_pos WHERE NAME= '"+Table_click+"'";
          
          pst = con.prepareStatement(sql);
          rs=pst.executeQuery();
          if(rs.next()){
            //  ID=rs.getString("SERVICE");
         String suma =rs.getString("NAME");
         bus.setText(suma);
               
                  
              
          }
          
    }catch(Exception ex ){
         JOptionPane.showMessageDialog(null,ex);
       
     }
}
   
     public void save(){
    
     try{
     String sql="INSERT INTO `ohada`(`NAME`,`CODE`,`COMPTEMERE`,`CODEMERE`,`CLASS`,`SUBSTR`)"+"VALUES(?,?,?,?,?,?)";
     pst=con.prepareStatement(sql);
     
     pst.setString(1,bus.getText());
     pst.setString(2,code.getText()); 
     pst.setString(3,compte.getText()); 
     pst.setString(4,codem.getText());
     pst.setString(5,classe.getText());
     pst.setString(6,"STOCK");
      pst.executeUpdate();
   //   JOptionPane.showMessageDialog(null,"Saved"); 
     }catch(Exception ex){JOptionPane.showMessageDialog(null,ex);}
     
    }
    public void attLIST_from_EMPLOYEView_used()
    {
        try{
       
       String sql;
	if(produit.isSelected()){
 sql="SELECT  CODE AS 'ACCOUNT',`NAME` AS 'ACCOUNT DESCRIPTION', `CLASS` FROM `ohada` WHERE CLASS=7 AND SUBSTR='STOCK'";
        }else if(stockin.isSelected()){
        
sql="SELECT  CODE AS 'ACCOUNT',`NAME` AS 'ACCOUNT DESCRIPTION', `CLASS` FROM `ohada` WHERE CLASS=3 AND SUBSTR='STOCK'";
        }else{
sql="SELECT  CODE AS 'ACCOUNT',`NAME` AS 'ACCOUNT DESCRIPTION', `CLASS` FROM `ohada` WHERE CLASS=6 AND SUBSTR='STOCK'";
        }
     
      pst = con.prepareStatement(sql);
      rs= pst.executeQuery();
     
       jTable2.setModel(DbUtils.resultSetToTableModel(rs));
       DefaultTableCellRenderer centre =new DefaultTableCellRenderer();
       TableColumn col0=jTable2.getColumnModel().getColumn(0);
       
        TableColumn col1=jTable2.getColumnModel().getColumn(1);
        TableColumn col2=jTable2.getColumnModel().getColumn(2);
        
       
       
      
       col0.setPreferredWidth(50);
       col1.setPreferredWidth(200);
       col2.setPreferredWidth(50);
       
        centre.setHorizontalAlignment(JLabel.CENTER);
        jTable2.getColumnModel().getColumn(0).setCellRenderer(centre);
         centre.setHorizontalAlignment(JLabel.CENTER);
        jTable2.getColumnModel().getColumn(2).setCellRenderer(centre);
       
      
        
    }catch(SQLException ex ){
     JOptionPane.showMessageDialog(null, ex);
}
}   
    
     public void save_ohada_pos(){
      String sql;
	if(produit.isSelected()){
 sql="UPDATE  `ohada_pos` SET `STATUS`=? WHERE NAME='"+bus.getText()+"'";
  try{
        
        pst=con.prepareStatement(sql);
        pst.setString(1,"1");
       // pst.setString(2,"1");
      //  pst.setString(3,"1");


    pst.executeUpdate();
   // JOptionPane.showMessageDialog(null, "DATA SAVED");
        
        }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}  
        }else if(stockin.isSelected()){
        
sql="UPDATE  `ohada_pos` SET `STATUS2`=?  WHERE NAME='"+bus.getText()+"'";
 try{
        
        pst=con.prepareStatement(sql);
        pst.setString(1,"1");
      //  pst.setString(2,"1");
      //  pst.setString(3,"1");


    pst.executeUpdate();
   // JOptionPane.showMessageDialog(null, "DATA SAVED");
        
        }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}  
        }else{
sql="UPDATE  `ohada_pos` SET `STATUS3`=? WHERE NAME='"+bus.getText()+"'";
 try{
        
        pst=con.prepareStatement(sql);
        pst.setString(1,"1");
       // pst.setString(2,"1");
       // pst.setString(3,"1");


    pst.executeUpdate();
   // JOptionPane.showMessageDialog(null, "DATA SAVED");
        
        }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}  
        }
  
      
  
  }
     
     public void callll(){
          int row= jTable4.getSelectedRow();
          String Table_click = (jTable4.getModel().getValueAt(row,1). toString());
              try{
             //  con.close();
          String sql = "SELECT * FROM  parametre_default WHERE numero_compte ='"+Table_click+"' ";
          pst = con.prepareStatement(sql);
          rs=pst.executeQuery();
        while(rs.next()){
 
            String sum1=rs.getString("intitule_compte");
             String sum2=rs.getString("numero_compte");
             String sum3=rs.getString("class_compte");
            
             compte.setText(sum1);
             codem.setText(sum2);
             classe.setText(sum3);
                
                 
          }
         // pst.close();
           //     rs.close();
 
                
    }catch(SQLException ex ){
         JOptionPane.showMessageDialog(null,ex);
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

        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator12 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jSeparator9 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        jSeparator10 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        buss = new javax.swing.JComboBox<>();
        checks = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel13 = new javax.swing.JLabel();
        compte = new javax.swing.JTextField();
        codem = new javax.swing.JTextField();
        code = new javax.swing.JTextField();
        classe = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        produit = new javax.swing.JRadioButton();
        stockin = new javax.swing.JRadioButton();
        stockout = new javax.swing.JRadioButton();

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Create Business Account", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jSeparator3.setBackground(new java.awt.Color(102, 102, 102));
        jPanel3.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 80, 220, 10));

        jSeparator12.setBackground(new java.awt.Color(102, 102, 102));
        jPanel3.add(jSeparator12, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 50, 620, 10));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Denomination:");
        jPanel3.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, -1, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Compte");
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, -1, -1));

        jSeparator9.setBackground(new java.awt.Color(102, 102, 102));
        jPanel3.add(jSeparator9, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 80, 60, 10));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Code-M");
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 65, 60, -1));

        jSeparator10.setBackground(new java.awt.Color(102, 102, 102));
        jPanel3.add(jSeparator10, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 80, 100, 10));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Code");
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 65, 40, -1));

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setText("OK");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 270, 150, 30));

        buss.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        buss.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select One Business" }));
        buss.setBorder(null);
        buss.setEnabled(false);
        buss.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                bussPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        jPanel3.add(buss, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 271, 480, 30));

        checks.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                checksKeyReleased(evt);
            }
        });
        jPanel3.add(checks, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 90, 290, -1));

        jTable2.setBackground(new java.awt.Color(240, 240, 240));
        jTable2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(jTable2);

        jPanel3.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 430, 140));

        bus.setEditable(false);
        bus.setBackground(new java.awt.Color(240, 240, 241));
        bus.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jPanel3.add(bus, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 30, 620, -1));
        jPanel3.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 170, 160));

        compte.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jPanel3.add(compte, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 60, 220, -1));

        codem.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jPanel3.add(codem, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 60, 80, -1));

        code.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jPanel3.add(code, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 60, 100, -1));

        classe.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jPanel3.add(classe, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 60, 60, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("Class");
        jPanel3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 65, 40, -1));

        jTable3.setBackground(new java.awt.Color(240, 240, 240));
        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTable3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable3MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTable3);

        jPanel3.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 30, 190, 270));

        jTable4.setBackground(new java.awt.Color(240, 240, 240));
        jTable4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTable4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable4MouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(jTable4);

        jPanel3.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 120, 290, 140));

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 950, 310));

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 0, 0));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("X");
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });

        produit.setBackground(new java.awt.Color(204, 204, 204));
        produit.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        produit.setText("Compte Product");
        produit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                produitMouseClicked(evt);
            }
        });
        produit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                produitActionPerformed(evt);
            }
        });

        stockin.setBackground(new java.awt.Color(204, 204, 204));
        stockin.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        stockin.setText("Compte Stock In");
        stockin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                stockinMouseClicked(evt);
            }
        });

        stockout.setBackground(new java.awt.Color(204, 204, 204));
        stockout.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        stockout.setText("Compte Stock Out");
        stockout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                stockoutMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(produit)
                .addGap(18, 18, 18)
                .addComponent(stockin)
                .addGap(10, 10, 10)
                .addComponent(stockout)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 499, Short.MAX_VALUE)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(produit)
                            .addComponent(stockin)
                            .addComponent(stockout)))
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 33, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel2.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 970, 40));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 971, Short.MAX_VALUE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 402, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
save();

save_ohada_pos();
call_in_table();
attLIST_from_EMPLOYEView_used();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void bussPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_bussPopupMenuWillBecomeInvisible
           // TODO add your handling code here:
    }//GEN-LAST:event_bussPopupMenuWillBecomeInvisible

    private void jTable3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable3MouseClicked
select_jTableSERVICE2();                   // TODO add your handling code here:
    }//GEN-LAST:event_jTable3MouseClicked

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
this.show(false);        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel6MouseClicked

    private void checksKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_checksKeyReleased
try{
     
       
             String sql="SELECT intitule_compte AS 'Compte',numero_compte as 'Code' FROM  parametre_default where intitule_compte like '"+checks.getText()+"%' ";
                
         
     //  String sql="SELECT ID, `NUM_FACTURE`, `DATE_FACTURE`, `DESCRIPTION`, `DEBIT_QTY`, `DEBIT_PU`, `DEBIT_PT`, `DEBIT_TVA`, `CREDIT_QTY`, `CREDIT_PU`, `CREDIT_PT`, `CREDIT_TVA`, `DATES`, `NUM` FROM facture_fournisseur where compte='"+jComboBox6.getSelectedItem()+"'";
       pst = con.prepareStatement(sql);
      rs= pst.executeQuery();
      
       
       jTable4.setModel(DbUtils.resultSetToTableModel(rs));
       mode=new DefaultTableModel();
       
       
       TableColumn col0=jTable4.getColumnModel().getColumn(0);
       
        TableColumn col1=jTable4.getColumnModel().getColumn(1);
        
        
       
       
      
       col0.setPreferredWidth(200);
       col1.setPreferredWidth(50);
   
   rs.close();
   pst.close();
    }catch(Exception ex){
        JOptionPane.showMessageDialog(null,ex);
    }  
             // TODO add your handling code here:
    }//GEN-LAST:event_checksKeyReleased

    private void jTable4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable4MouseClicked
callll();        // TODO add your handling code here:
    }//GEN-LAST:event_jTable4MouseClicked

    private void produitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_produitActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_produitActionPerformed

    private void produitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_produitMouseClicked
call_in_table(); 
attLIST_from_EMPLOYEView_used();// TODO add your handling code here:
    }//GEN-LAST:event_produitMouseClicked

    private void stockinMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_stockinMouseClicked
call_in_table(); 
attLIST_from_EMPLOYEView_used();// TODO add your handling code here:
    }//GEN-LAST:event_stockinMouseClicked

    private void stockoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_stockoutMouseClicked
call_in_table(); 
attLIST_from_EMPLOYEView_used();// TODO add your handling code here:
    }//GEN-LAST:event_stockoutMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static final javax.swing.JTextField bus = new javax.swing.JTextField();
    private javax.swing.JComboBox<String> buss;
    private javax.swing.JTextField checks;
    private javax.swing.JTextField classe;
    private javax.swing.JTextField code;
    private javax.swing.JTextField codem;
    private javax.swing.JTextField compte;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    private javax.swing.JRadioButton produit;
    private javax.swing.JRadioButton stockin;
    private javax.swing.JRadioButton stockout;
    // End of variables declaration//GEN-END:variables
}
