/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intreprisemanagementsystem;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Doshe PC
 */
public class home_cnss extends javax.swing.JInternalFrame {

Connection con=null;
PreparedStatement pst=null;
ResultSet rs= null;
  DefaultTableModel mode;
    public home_cnss() {
        initComponents();
          con=JavaDbConnect.dbConnect();
        call();
    }
public void call(){
     
      try{
           
        //     String sql="SELECT  NAME AS 'NAME',LNAME AS 'LAST NAME',ROLLNUM FROM employee where Name like '"+jTextField1.getText()+"%' and ACTIVE ='Active'";
      String sql="SELECT  NAME AS 'NAME',LNAME AS 'LAST NAME',ROLLNUM FROM employee where  ACTIVE ='Active'";
       pst = con.prepareStatement(sql);
      rs= pst.executeQuery();
       jTable2.setModel(DbUtils.resultSetToTableModel(rs));
       mode=new DefaultTableModel();
      
        TableColumn col0=jTable2.getColumnModel().getColumn(0);
        TableColumn col1=jTable2.getColumnModel().getColumn(1);
        TableColumn col2=jTable2.getColumnModel().getColumn(2);
     
       col0.setPreferredWidth(150);
       col1.setPreferredWidth(100);
       col2.setPreferredWidth(50);
    
    }catch(SQLException ex ){
     JOptionPane.showMessageDialog(null, ex);
}
      
     }
 
  public void call_SEARCH(){
     
      try{
           
            String sql="SELECT  NAME AS 'NAME',LNAME AS 'LAST NAME',ROLLNUM FROM employee where NAME like '"+jTextField10.getText()+"%' OR LNAME like '"+jTextField10.getText()+"%' and ACTIVE ='Active'";
   
       pst = con.prepareStatement(sql);
      rs= pst.executeQuery();
       jTable2.setModel(DbUtils.resultSetToTableModel(rs));
       mode=new DefaultTableModel();
      
        TableColumn col0=jTable2.getColumnModel().getColumn(0);
        TableColumn col1=jTable2.getColumnModel().getColumn(1);
        TableColumn col2=jTable2.getColumnModel().getColumn(2);
     
       col0.setPreferredWidth(150);
       col1.setPreferredWidth(100);
       col2.setPreferredWidth(50);
    
    }catch(SQLException ex ){
     JOptionPane.showMessageDialog(null, ex);
}
      
     }
   public void show_photo_from_db()
               
   {
            try{
          int row= jTable2.getSelectedRow();
          String Table_click = (jTable2.getModel().getValueAt(row,2). toString());
          String sql = "SELECT * FROM  employee WHERE  rollnum = '"+Table_click+"'";
          pst = con.prepareStatement(sql);
          rs=pst.executeQuery();
          if(rs.next()){
            
          //`ID`, `NOMS`, `LNAME`, `ROLL`, `ADDRESS`, `TEL`, `MAIL`, `GENDER`, `TITLE`, `DEPARTEMENT`, `PHOTOS`, `LOCKS`
             String sum=rs.getString("ETAT_CIVIL");
                mariage.setText(sum);
                
                String sum1=rs.getString("ROLLNUM");
                inssroll.setText(sum1);
                }
         
    }catch(SQLException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }
            
             try{
          int row= jTable2.getSelectedRow();
          String Table_click = (jTable2.getModel().getValueAt(row,2). toString());
          String sql = "SELECT `PARTENER` FROM `all_fam_numberchild` WHERE  roll = '"+Table_click+"'";
          pst = con.prepareStatement(sql);
          rs=pst.executeQuery();
          if(rs.next()){
            
          //`ID`, `NOMS`, `LNAME`, `ROLL`, `ADDRESS`, `TEL`, `MAIL`, `GENDER`, `TITLE`, `DEPARTEMENT`, `PHOTOS`, `LOCKS`
             String sum=rs.getString("PARTENER");
                insspartener.setText(sum);
                }
         
    }catch(SQLException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }
             
             
             try{
          int row= jTable2.getSelectedRow();
          String Table_click = (jTable2.getModel().getValueAt(row,2). toString());
          String sql = "SELECT count(ID) FROM all_fam_listchild WHERE  roll = '"+Table_click+"'";
          pst = con.prepareStatement(sql);
          rs=pst.executeQuery();
          if(rs.next()){
            
          //`ID`, `NOMS`, `LNAME`, `ROLL`, `ADDRESS`, `TEL`, `MAIL`, `GENDER`, `TITLE`, `DEPARTEMENT`, `PHOTOS`, `LOCKS`
             String sum=rs.getString("count(ID)");
                nbr_child.setText(sum);
                }
         
    }catch(SQLException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }
             
              try{
          int row= jTable2.getSelectedRow();
          String Table_click = (jTable2.getModel().getValueAt(row,2). toString());
          String sql = "SELECT `INSS` FROM `inss` WHERE  roll = '"+Table_click+"'";
          pst = con.prepareStatement(sql);
          rs=pst.executeQuery();
          if(rs.next()){
            
          //`ID`, `NOMS`, `LNAME`, `ROLL`, `ADDRESS`, `TEL`, `MAIL`, `GENDER`, `TITLE`, `DEPARTEMENT`, `PHOTOS`, `LOCKS`
             String sum=rs.getString("INSS");
                inss.setText(sum);
                
                String sum1=rs.getString("INSS");
                check.setText(sum1);
                }
         
    }catch(SQLException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }
            
            
   }
     public void save_Inss(){
       
         try {
         
        PreparedStatement pst = con.prepareStatement("INSERT INTO `inss`(`INSS`,`CHILD`, `MARIAGE`, `PARTENER`, `ROLL`)"
        +"value(?,?,?,?,?)");
       
       pst.setString(1, inss.getText());
         pst.setString(2, nbr_child.getText());
         pst.setString(3, mariage.getText());
        
         pst.setString(4, insspartener.getText());
         
         pst.setString(5, inssroll.getText());
        
          pst.executeUpdate();
        
                 JOptionPane.showMessageDialog(null,"Data Saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
        
     call_inss_table();
     }
       
       public void update_Inss(){
       
         try {
         
        PreparedStatement pst = con.prepareStatement("UPDATE  inss SET INSS=? where roll='"+inssroll.getText()+"'");
       pst.setString(1, inss.getText());
       
       
        
          pst.executeUpdate();
        
                 JOptionPane.showMessageDialog(null,"Data Saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
        
     call_inss_table();
     }
       
       public void Delete(){
       
            try{
        String sql = "DELETE FROM inss WHERE ROLL=?";
        
         pst = con.prepareStatement(sql);
         pst.setString(1,inssroll.getText());
         pst.executeUpdate();
          
              
     }catch(SQLException | HeadlessException ex ){
         JOptionPane.showMessageDialog(null,ex);
     } 
        call_inss_table();
       }
       
       public void call_inss_table(){
       
        try{
           
             String sql="SELECT `INSS`,`CHILD`, `MARIAGE`, `PARTENER`, `ROLL` FROM `inss` where ROLL='"+inssroll.getText()+"'";
     //  String sql="SELECT ID, `NUM_FACTURE`, `DATE_FACTURE`, `DESCRIPTION`, `DEBIT_QTY`, `DEBIT_PU`, `DEBIT_PT`, `DEBIT_TVA`, `CREDIT_QTY`, `CREDIT_PU`, `CREDIT_PT`, `CREDIT_TVA`, `DATES`, `NUM` FROM facture_fournisseur where compte='"+jComboBox6.getSelectedItem()+"'";
       pst = con.prepareStatement(sql);
      rs= pst.executeQuery();
      
       
       jTable3.setModel(DbUtils.resultSetToTableModel(rs));
       mode=new DefaultTableModel();
       
       
        TableColumn col0=jTable3.getColumnModel().getColumn(0);
        TableColumn col1=jTable3.getColumnModel().getColumn(1);
        TableColumn col2=jTable3.getColumnModel().getColumn(2);
        TableColumn col3=jTable3.getColumnModel().getColumn(3);
        TableColumn col4=jTable3.getColumnModel().getColumn(4);
        
       
       
      //`INSS`,`CHILD`, `MARIAGE`, `PARTENER`, `ROLL`
       
       col0.setPreferredWidth(100);
       col1.setPreferredWidth(50);
       col2.setPreferredWidth(50);
       col3.setPreferredWidth(150);
       col4.setPreferredWidth(100);
       
      
      
       
       
      // jTable1.setModel(mode);
       
     
    }catch(SQLException ex ){
     JOptionPane.showMessageDialog(null, ex);
}
       
       }
       
         public void show_photo_from_db_INSS()
               
   {
            try{
          int row= jTable3.getSelectedRow();
          String Table_click = (jTable3.getModel().getValueAt(row,4). toString());
          String sql = "SELECT `INSS`,`CHILD`, `MARIAGE`, `PARTENER`, `ROLL` FROM `inss` WHERE  roll = '"+Table_click+"'";
          pst = con.prepareStatement(sql);
          rs=pst.executeQuery();
          if(rs.next()){
            
          //`ID`, `NOMS`, `LNAME`, `ROLL`, `ADDRESS`, `TEL`, `MAIL`, `GENDER`, `TITLE`, `DEPARTEMENT`, `PHOTOS`, `LOCKS`
             String sum=rs.getString("CHILD");
                nbr_child.setText(sum);
                
                String sum2=rs.getString("MARIAGE");
                mariage.setText(sum2);
                
                String sum3=rs.getString("PARTENER");
                insspartener.setText(sum3);
                
                String sum1=rs.getString("ROLL");
                inssroll.setText(sum1);
                
                 String sum4=rs.getString("INSS");
                inss.setText(sum4);
                
                String sum5=rs.getString("INSS");
               check.setText(sum5);
                }
         
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

        jPanel24 = new javax.swing.JPanel();
        jPanel25 = new javax.swing.JPanel();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        inss = new Palette.TextField();
        nbr_child = new Palette.TextField();
        mariage = new Palette.TextField();
        insspartener = new Palette.TextField();
        check = new Palette.TextField();
        inssroll = new Palette.TextField();
        jPanel26 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jTextField10 = new Palette.TextField();
        jPanel27 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();

        setBackground(new java.awt.Color(255, 255, 255));
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_Check_16px.png"))); // NOI18N

        jPanel24.setBackground(new java.awt.Color(255, 255, 255));
        jPanel24.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jPanel25.setBackground(new java.awt.Color(255, 255, 255));
        jPanel25.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Personel Info", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N

        jButton9.setText("Save");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton10.setText("Delete");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        inss.setLabelText("Numéro d’affiliation social");

        nbr_child.setEditable(false);
        nbr_child.setLabelText("Nombre d'enfant");

        mariage.setEditable(false);
        mariage.setLabelText("Etat civile");

        insspartener.setEditable(false);
        insspartener.setLabelText("Nom du partenaire");

        check.setEditable(false);
        check.setLabelText("...");

        inssroll.setEditable(false);
        inssroll.setLabelText("Matricule");

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(inss, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addComponent(nbr_child, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(mariage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(insspartener, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addComponent(check, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(inssroll, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton9)
                        .addGap(0, 1, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addComponent(inss, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nbr_child, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mariage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(insspartener, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(check, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(inssroll, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton9)
                        .addComponent(jButton10)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel26.setBackground(new java.awt.Color(255, 255, 255));
        jPanel26.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Employees List", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N

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
        jTable2.setRowHeight(30);
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);

        jTextField10.setLabelText("Recherche");
        jTextField10.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField10KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 432, Short.MAX_VALUE)
                    .addGroup(jPanel26Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel27.setBackground(new java.awt.Color(255, 255, 255));
        jPanel27.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "CNSS List", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable3.setRowHeight(30);
        jTable3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable3MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTable3);

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel27Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jMenu1.setText("X");
        jMenu1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
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
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        setBounds(50, 50, 913, 480);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        if (inss.getText().isEmpty()){
            JOptionPane.showMessageDialog(null,"Wrong Input ","Error",JOptionPane.ERROR_MESSAGE);
        }else if(check.getText().isEmpty()){
            save_Inss();
        }else{
            update_Inss();
        }
        nbr_child.setText("");
        mariage.setText("");
        insspartener.setText("-");
        inss.setText("");
        inssroll.setText("");
        check.setText("");// TODO add your handling code here:
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        Delete();
        nbr_child.setText("");
        mariage.setText("");
        insspartener.setText("-");
        inss.setText("");
        inssroll.setText("");
        check.setText("");// TODO add your handling code here:
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        nbr_child.setText("");
        mariage.setText("");
        insspartener.setText("-");
        inss.setText("");
        check.setText("");
        show_photo_from_db(); 
        call_inss_table();// TODO add your handling code here:
    }//GEN-LAST:event_jTable2MouseClicked

    private void jTable3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable3MouseClicked
        show_photo_from_db_INSS();        // TODO add your handling code here:
    }//GEN-LAST:event_jTable3MouseClicked

    private void jMenu1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu1MouseClicked
this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu1MouseClicked

    private void jTextField10KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField10KeyReleased
call_SEARCH();         // TODO add your handling code here:
    }//GEN-LAST:event_jTextField10KeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Palette.TextField check;
    private Palette.TextField inss;
    private Palette.TextField insspartener;
    private Palette.TextField inssroll;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private Palette.TextField jTextField10;
    private Palette.TextField mariage;
    private Palette.TextField nbr_child;
    // End of variables declaration//GEN-END:variables
}
