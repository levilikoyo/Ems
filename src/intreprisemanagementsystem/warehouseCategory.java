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
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author DOSHE
 */
public class warehouseCategory extends javax.swing.JPanel {

 DefaultTableModel mode;
Connection con=null;
PreparedStatement pst=null;
ResultSet rs= null;
String ID=null;
String check=null;
    public warehouseCategory() {
        initComponents();
              con=JavaDbConnectUMCO.dbConnect();
        call_table();
    }
   // String url="jdbc:sqlite:umco.db";
    
 ///>>>>ETAGERE
     public void check(){
  
       try{
          String sql = "SELECT NAME FROM ClassCat WHERE NAME= '"+name.getText()+"'";
          //***;
          pst = con.prepareStatement(sql);
          rs=pst.executeQuery();
          if(rs.next()){
             check = rs.getString("NAME");
            
               }
         
    }catch(SQLException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }
  
  }  
    
 public void save(){
     if(check==null){
      try{
        String sql="Insert into ClassCat (`NAME`,STATUT)"+
                "values(?,?)";
        //***;
        pst=con.prepareStatement(sql);
pst.setString(1,name.getText());
pst.setString(2,"Cat");


    pst.executeUpdate();
    JOptionPane.showMessageDialog(null, "DATA SAVED");
        
        }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}  
     }else{
         JOptionPane.showMessageDialog(null,"Data Exist","Error",JOptionPane.ERROR_MESSAGE);
    
     }
      
          
    
    }
 public void Update(){
     try{
        String sql="UPDATE `ClassCat` SET `NAME`=? WHERE NAME='"+ID+"'";
        pst=con.prepareStatement(sql);
pst.setString(1,name.getText());


    pst.executeUpdate();
    JOptionPane.showMessageDialog(null, "DATA UPDATED");
        
        }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}   
          
    
    }
 
   public void call_table(){
      
           try{
          
             String sql="SELECT `NAME` FROM `ClassCat` where STATUT='Cat'";
             ////***;
     //  String sql="SELECT ID, `NUM_FACTURE`, `DATE_FACTURE`, `DESCRIPTION`, `DEBIT_QTY`, `DEBIT_PU`, `DEBIT_PT`, `DEBIT_TVA`, `CREDIT_QTY`, `CREDIT_PU`, `CREDIT_PT`, `CREDIT_TVA`, `DATES`, `NUM` FROM facture_fournisseur where compte='"+jComboBox6.getSelectedItem()+"'";
       pst = con.prepareStatement(sql);
      rs= pst.executeQuery();
      
       
       jTable1.setModel(DbUtils.resultSetToTableModel(rs));
       mode=new DefaultTableModel();
       
       
      
       
      // jTable1.setModel(mode);
       
     
    }catch(Exception ex ){
     JOptionPane.showMessageDialog(null, ex);
}
    
      
      }
                public void select_jTable()
   {
        try{
          int row= jTable1.getSelectedRow();
          String Table_click = (jTable1.getModel().getValueAt(row,0). toString());
          String sql = "SELECT * FROM ClassCat WHERE NAME= '"+Table_click+"' and STATUT='Cat'";
          //***;
          pst = con.prepareStatement(sql);
          rs=pst.executeQuery();
          if(rs.next()){
          //`ID`, ``, ``, ``, ``, ``, ``, ``, ``, ``, ``, ``, ``
              
            ID = rs.getString("NAME");
            
              String add1 = rs.getString("NAME");
              name.setText(add1);
              
              
          }
         
    }catch(SQLException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }
   }

 public void delete()
    {
     
         try{
        String sql = "DELETE FROM ClassCat WHERE NAME=? and STATUT='Cat'";
        
         pst = con.prepareStatement(sql);
         pst.setString(1,ID);
         pst.executeUpdate();
        // JOptionPane.showMessageDialog(null,"delete");
              
     }catch(SQLException  ex ){
         JOptionPane.showMessageDialog(null,ex);
     } 
    }
    public void refresh(){
    name.setText("");
    ID=null;
    
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        name = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jPanel2.setBackground(new java.awt.Color(153, 153, 153));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 0, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("X");
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("Categories");

        name.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jButton1.setText("OK");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Del");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(name)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(399, 399, 399)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(175, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 224, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        this.show(false);        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
select_jTable();         // TODO add your handling code here:
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
if(ID==null){
save();
}else{
Update();
}
call_table();
refresh();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
delete(); 
call_table();
refresh();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField name;
    // End of variables declaration//GEN-END:variables
}