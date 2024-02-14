/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package journals;

import intreprisemanagementsystem.JavaDbConnect;
import static intreprisemanagementsystem.budget_bacth.jTable3;
import static intreprisemanagementsystem.budget_bacth.tbl_grdlivre1;
import static intreprisemanagementsystem.budget_bacth.tbl_lbgrdlivre1;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

/**
 *
 * @author Doshe PC
 */
public class extouner extends javax.swing.JFrame {

Connection con=null;
Connection conof=null;
PreparedStatement pst=null;
ResultSet rs= null;
 DefaultTableModel mode;
    public extouner() {
        initComponents();
          setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("icons8_Raspberry_Pi_48px_1.png")));
           con=JavaDbConnect.dbConnect();
        selectontableextourne();
        
    }
 public void selectontableextourne(){
    int rowss= tbl_lbgrdlivre1.getSelectedRow();
        String lb = (tbl_lbgrdlivre1.getModel().getValueAt(rowss,0). toString()); 
        
        int rowb= jTable3.getSelectedRow();
        String batch = (jTable3.getModel().getValueAt(rowb,0). toString());
        jMenu2.setText(batch);
        TableModel model1 =tbl_grdlivre1.getModel();
        int indexs[]=tbl_grdlivre1.getSelectedRows();
        Object[] row = new Object[5];
        DefaultTableModel model2 = (DefaultTableModel) jTable1.getModel();
        for(int i=0; i < indexs.length;i++){
        row[0]= model1.getValueAt(indexs[i],0);
        row[1]= model1.getValueAt(indexs[i],1);
        row[2]= lb;
        row[3]= model1.getValueAt(indexs[i],2);
        row[4]= model1.getValueAt(indexs[i],4);
        model2.addRow(row);
        
        TableColumn col0=jTable1.getColumnModel().getColumn(0);
        TableColumn col1=jTable1.getColumnModel().getColumn(1);
        TableColumn col2=jTable1.getColumnModel().getColumn(2);
        TableColumn col3=jTable1.getColumnModel().getColumn(3);
          TableColumn col4=jTable1.getColumnModel().getColumn(4);
           
        col0.setPreferredWidth(50);
       col1.setPreferredWidth(50);
        col2.setPreferredWidth(30);
       col3.setPreferredWidth(500);
       col4.setPreferredWidth(50);
        }
        
    }
 public void update(){
  int indexs[]=jTable1.getSelectedRows();
        for(int i=0; i < indexs.length;i++){
             String num = (jTable1.getModel().getValueAt(indexs[i],1). toString());
               String lb = (jTable1.getModel().getValueAt(indexs[i],2). toString());
              try{
         PreparedStatement pst = con.prepareStatement("UPDATE `BUDGET_TRANS` SET `CODE`='"+lb+"',BATCH='"+jMenu2.getText()+"' WHERE NUM='"+num+"'");
      pst.executeUpdate();

     }catch(SQLException | HeadlessException ex ){
         JOptionPane.showMessageDialog(null,ex); }   
               try{
         PreparedStatement pst = con.prepareStatement("UPDATE `OHADA_TRANS` SET `LB`='"+lb+"' WHERE NUM='"+num+"'");
      pst.executeUpdate();

     }catch(SQLException | HeadlessException ex ){
         JOptionPane.showMessageDialog(null,ex); }
        }
       JOptionPane.showMessageDialog(null,"Transactrion Done"); 
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
        jScrollPane1 = new javax.swing.JScrollPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Date", "Num", "Lb", "Libelle", "Montant"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setRowHeight(26);
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(1).setResizable(false);
            jTable1.getColumnModel().getColumn(4).setResizable(false);
        }

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 781, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 368, Short.MAX_VALUE)
        );

        jMenu1.setText("File");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setText("Save");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Batch");
        jMenu2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jMenu2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu2MouseClicked(evt);
            }
        });
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

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
update();
this.dispose();// TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenu2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu2MouseClicked
extouners_sel m = new extouners_sel();
m.dispose();
m.show();
  m. setDefaultCloseOperation(DISPOSE_ON_CLOSE);         // TODO add your handling code here:
    }//GEN-LAST:event_jMenu2MouseClicked

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
            java.util.logging.Logger.getLogger(extouner.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(extouner.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(extouner.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(extouner.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new extouner().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    public static final javax.swing.JMenu jMenu2 = new javax.swing.JMenu();
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    public static final javax.swing.JTable jTable1 = new javax.swing.JTable();
    // End of variables declaration//GEN-END:variables
}
