/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intreprisemanagementsystem;

import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Dosh
 */
public class compte extends javax.swing.JFrame {

    Connection con=null;
 Connection cononline=null;
PreparedStatement pst=null;
ResultSet rs= null;
 DefaultTableModel mode;
    public compte() {
        initComponents();
         con=JavaDbConnect.dbConnect();
           setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("icons8_Raspberry_Pi_48px_1.png")));
      //   attLIST_from_EMPLOYEView_used();
    }
   public void attLIST_from_EMPLOYEView_used()
    {
         try{
            String sql="select * from  ohada WHERE SUBSTR='"+journal.code1.getText()+"'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
           if(rs.next()){
                String sum=rs.getString("SUBSTR");
                 try{  // String Table_click = (jList2.getModel().getSelectedItem(tmps,0). toString());
                     
       String sqls="SELECT  CODE as 'Numéro',`NAME` AS 'Intitulé du compte' FROM `ohada` where SUBSTR='"+sum+"' ";
       pst = con.prepareStatement(sqls);
      rs= pst.executeQuery();
     
      etjTable3.setModel(DbUtils.resultSetToTableModel(rs));
       DefaultTableCellRenderer centre =new DefaultTableCellRenderer();
       TableColumn col0=etjTable3.getColumnModel().getColumn(0);
        TableColumn col1=etjTable3.getColumnModel().getColumn(1);
       col0.setPreferredWidth(50);
       col1.setPreferredWidth(300);
      centre.setHorizontalAlignment(JLabel.CENTER);
        etjTable3.getColumnModel().getColumn(0).setCellRenderer(centre); 
    }catch(SQLException ex ){
     JOptionPane.showMessageDialog(null, ex);}
                 
                 compte m = new compte ();
  
        m.show();
        m. setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex); }  
        
       
}
   
    public void search_code()
    {
                 try{  // String Table_click = (jList2.getModel().getSelectedItem(tmps,0). toString());
                     
       String sqls="SELECT  CODE as 'Numéro',`NAME` AS 'Intitulé du compte' FROM `ohada` where code like'"+code.getText()+"%' and SUBSTR='"+journal.code1.getText()+"' ";
       pst = con.prepareStatement(sqls);
      rs= pst.executeQuery();
     
      etjTable3.setModel(DbUtils.resultSetToTableModel(rs));
       DefaultTableCellRenderer centre =new DefaultTableCellRenderer();
       TableColumn col0=etjTable3.getColumnModel().getColumn(0);
        TableColumn col1=etjTable3.getColumnModel().getColumn(1);
       col0.setPreferredWidth(50);
       col1.setPreferredWidth(300);
      centre.setHorizontalAlignment(JLabel.CENTER);
        etjTable3.getColumnModel().getColumn(0).setCellRenderer(centre); 
    }catch(SQLException ex ){
     JOptionPane.showMessageDialog(null, ex);}
                 
               
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex); }  
        
       
}
  // public static 
 public void tableselectedcompte(){
            int row= compte.etjTable3.getSelectedRow();
         // String rows =jTable1.getName()
          String Table_click = (compte.etjTable3.getModel().getValueAt(row,0). toString());   
        
          try{
    
          String sql = "SELECT * FROM ohada WHERE code= '"+Table_click+"'";
          pst = con.prepareStatement(sql);
          rs=pst.executeQuery();
          if(rs.next()){
        //`NAME`, `CODE`, `COMPTEMERE`, `CODEMERE`, `CLASS`, `SUBSTR`
            String sm= rs.getString("code");
             journal.code1.setText(sm);
            
            String sm1=rs.getString("NAME");
            journal.nom.setText(sm1);
            String sm2=rs.getString("COMPTEMERE");
            journal.comptem.setText(sm2);
            String sm3=rs.getString("CODEMERE");
            journal.codem.setText(sm3);
            String sm4=rs.getString("CLASS");
            journal.classe.setText(sm4);
            String sm5=rs.getString("SUBSTR");
            journal.substr.setText(sm5);
          }
         
    }catch(SQLException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }
        if(journal.jComboBox1.getSelectedItem().equals("JRN-CAISSE") ||journal.jComboBox1.getSelectedItem().equals("JRN-BANQUE")){
        
        
           
          try{
String sql="select * from monais WHERE caisse='"+Table_click+"'";      
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            if(rs.next()){
           String DEVICE=rs.getString("DEVICE");  
            journal.jComboBox3.setSelectedItem(DEVICE);
            }
        //  con.close();
            }
        
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex); }
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAlwaysOnTop(true);
        setUndecorated(true);
        setResizable(false);
        setType(java.awt.Window.Type.UTILITY);

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));

        jPanel2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        etjTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        etjTable3.setFocusable(false);
        etjTable3.setRowHeight(25);
        etjTable3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                etjTable3MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(etjTable3);

        code.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        code.setText("Numéro");
        code.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        code.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                codeMouseClicked(evt);
            }
        });
        code.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                codeActionPerformed(evt);
            }
        });
        code.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                codeKeyReleased(evt);
            }
        });

        jTextField2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTextField2.setText("Intitulé du compte");
        jTextField2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 491, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(code, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jTextField2))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(code, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Sélection d’un compte générale");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("X");
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void etjTable3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_etjTable3MouseClicked
tableselectedcompte();
this.dispose();//   select_jTable();       // TODO add your handling code here:
    }//GEN-LAST:event_etjTable3MouseClicked

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel2MouseClicked

    private void codeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_codeMouseClicked
if(code.getText().equals("Numéro")){
code.setText("");
}
               // TODO add your handling code here:
    }//GEN-LAST:event_codeMouseClicked

    private void codeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_codeKeyReleased

        search_code();        // TODO add your handling code here:
    }//GEN-LAST:event_codeKeyReleased

    private void codeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_codeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_codeActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(compte.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(compte.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(compte.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(compte.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new compte().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static final javax.swing.JTextField code = new javax.swing.JTextField();
    public static final javax.swing.JTable etjTable3 = new javax.swing.JTable();
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    public static final javax.swing.JTextField jTextField2 = new javax.swing.JTextField();
    // End of variables declaration//GEN-END:variables
}
