/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intreprisemanagementsystem;

import com.alee.laf.WebLookAndFeel;

import java.awt.*;
import java.io.File;

import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.swing.*;

import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

import net.proteanit.sql.DbUtils;
public class hommes extends javax.swing.JFrame {
private Connection con=null;
PreparedStatement pst=null;
ResultSet rs= null;
    public hommes() {
       initComponents();
       con=JavaDbConnectUMCO.dbConnect();
     setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("icons8_Raspberry_Pi_48px_1.png")));
    // save();
    }
    
 SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
 String mois= dateFormats.format(new Date());
   // String url="jdbc:sqlite:pos.db";
 public void save(){
     String Check=null,Check1=null;
      try{
          String sql = "SELECT Date_in,max(Date_out) FROM serial WHERE Date_in= '"+mois+"'";
          pst = con.prepareStatement(sql);
          rs=pst.executeQuery();
          if(rs.next()){
            
           Check = rs.getString("Date_in");
           Check1 = rs.getString("max(Date_out)");
            
           
          }
         
    }catch(SQLException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }
     if(Check==null){
  try {
           // etroll();
       PreparedStatement pst = con.prepareStatement("INSERT INTO `serial`(`Date_in`, `Date_out`, `Status`)"
        +"value(?,?,?)");
        
           pst.setString(1,mois);
       pst.setString(2,"1");
        pst.setString(3,"");
    
         
          pst.executeUpdate();
        
               JOptionPane.showMessageDialog(null,"Saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
 }else if(Check1.equals("1")){
   //  hommes m = new hommes();
    //   m.show(false);
       JOptionPane.showMessageDialog(null,"Votre License est  Expirée veillez contacter  le fournisseur");
       
      
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

        webMenuBar1 = new com.alee.laf.menu.WebMenuBar();
        jMenu15 = new javax.swing.JMenu();
        jMenu16 = new javax.swing.JMenu();
        jTextField1 = new javax.swing.JTextField();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        jLabel1 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenuItem81 = new javax.swing.JMenuItem();
        jMenuItem51 = new javax.swing.JMenuItem();
        jMenuItem39 = new javax.swing.JMenuItem();

        jMenu15.setText("File");
        webMenuBar1.add(jMenu15);

        jMenu16.setText("Edit");
        webMenuBar1.add(jMenu16);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBounds(new java.awt.Rectangle(0, 0, 0, 0));
        setLocationByPlatform(true);
        setResizable(false);
        setType(java.awt.Window.Type.POPUP);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jTextField1.setEditable(false);
        jTextField1.setBackground(new java.awt.Color(204, 255, 255));
        jTextField1.setFont(new java.awt.Font("Georgia", 1, 10)); // NOI18N
        jTextField1.setText(" www.togethewecanbusnessmanagementsystem.ug.com// toghetherwecanmana@system.ug.com// levilikoyolevi@gmail.com/(+256)757776103 //(+243)816464945. Project relised by Together We can Busness Management System(TWBMS)");

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/Capture.JPG"))); // NOI18N
        jLabel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jDesktopPane1.setLayer(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1328, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 629, Short.MAX_VALUE)
        );

        jMenuBar1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jMenuBar1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        logistique.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        logistique.setText("Home");
        logistique.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        logistique.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                logistiqueMouseClicked(evt);
            }
        });
        logistique.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logistiqueActionPerformed(evt);
            }
        });

        jMenuItem81.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_Table_16px.png"))); // NOI18N
        jMenuItem81.setText("Dash Board");
        jMenuItem81.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem81ActionPerformed(evt);
            }
        });
        logistique.add(jMenuItem81);

        jMenuItem51.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_Documents_16px.png"))); // NOI18N
        jMenuItem51.setText("Ohada");
        jMenuItem51.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem51ActionPerformed(evt);
            }
        });
        logistique.add(jMenuItem51);

        jMenuItem39.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_Password_Reset_16px.png"))); // NOI18N
        jMenuItem39.setText("Backup/Restore");
        jMenuItem39.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem39ActionPerformed(evt);
            }
        });
        logistique.add(jMenuItem39);

        jMenuBar1.add(logistique);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTextField1)
            .addComponent(jDesktopPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jDesktopPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void logistiqueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logistiqueActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_logistiqueActionPerformed

    private void logistiqueMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logistiqueMouseClicked

        // TODO add your handling code here:
    }//GEN-LAST:event_logistiqueMouseClicked

    private void jMenuItem39ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem39ActionPerformed
            // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem39ActionPerformed

    private void jMenuItem51ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem51ActionPerformed
        warehouse_ohada m = new warehouse_ohada();
        m.show();
        m. setDefaultCloseOperation(DISPOSE_ON_CLOSE);      // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem51ActionPerformed

    private void jMenuItem81ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem81ActionPerformed
logs2 m = new logs2();
        jDesktopPane1.add(m);
       m.show();
    m.setDefaultCloseOperation(DISPOSE_ON_CLOSE);       // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem81ActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
     // TODO add your handling code here:
    }//GEN-LAST:event_formWindowClosing
 
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        new hommes().setVisible(true);
   //  save();
        
         
         
       //  new attendance().setVisible(true);
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("JTatoo".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(homme.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(homme.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(homme.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(homme.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                 WebLookAndFeel.install(true);
               //WebLookAndFeel.setDecorateAllWindows(true);
              // new homme().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu15;
    private javax.swing.JMenu jMenu16;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem39;
    private javax.swing.JMenuItem jMenuItem51;
    private javax.swing.JMenuItem jMenuItem81;
    private javax.swing.JTextField jTextField1;
    public static final javax.swing.JMenu logistique = new javax.swing.JMenu();
    private com.alee.laf.menu.WebMenuBar webMenuBar1;
    // End of variables declaration//GEN-END:variables
}
