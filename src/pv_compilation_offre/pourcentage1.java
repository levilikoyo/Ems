/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pv_compilation_offre;

import java.awt.BorderLayout;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.HashMap;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.swing.JRViewer;

/**
 *
 * @author Dosh
 */
public class pourcentage1 extends javax.swing.JFrame {

        private Connection con;
private Statement st;
PreparedStatement pst=null;
ResultSet rs= null;
    public pourcentage1() {
        initComponents();
        con=JavaDbConnect.dbConnect();
        jDateChooser1.setDate(new Date());
    }
public void reports()
     {
          String val = null;
         try{
            String sql="SELECT sum(point) FROM  docss where offre='"+home_offre.batch.getSelectedValue()+"' and status='Teste'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            if(rs.next()){
               //  `NOM`, `PNOM`, `PRNOM`, `TEL`, `ADDR`, `OFFRE`, `DOC`, `EXIST`, `POINT`, `POSTE`, `NUMERO` FROM `pv` WHERE 1
               val=rs.getString("sum(point)");
                }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
     
             try{
                 String sqls="Select path from pathn";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
            if(rs.next()){
             
                String sum=rs.getString("path");
                  
                 String NameFiles=sum;
            
            String NameFile=""+NameFiles+"newReport_1.jrxml";
                JasperDesign jd=JRXmlLoader.load(NameFile);
  String sql="SELECT * FROM pv_test   WHERE OFFRE='"+home_offre.batch.getSelectedValue()+"' and status='teste' group by NUM having (sum(point)*100)/"+val+" >= '"+jTextField1.getText()+"' order by nom";
//Select * from ohada_trans INNER JOIN projet ON ohada_trans.BUSS=projet.PROJET_NUM
// ";
   HashMap param= new HashMap();
    param.put("date", jDateChooser1.getText());
     home_offre.boody.removeAll();
     home_offre.boody.repaint();
    home_offre.boody.revalidate();
                JRDesignQuery nq=new JRDesignQuery();
                nq.setText(sql);
                jd.setQuery(nq);
      JasperReport jr =JasperCompileManager.compileReport(jd);
      JasperPrint jp=JasperFillManager.fillReport(jr,param,con);
     // JasperViewer.viewReport(jp,false);
    // JasperViewer m= new JasperViewer(jp);
    JRViewer m= new JRViewer(jp);
     home_offre.boody.setLayout(new BorderLayout());
     home_offre.boody.add(m);
     
                 
            }
             }catch(Exception ex){
                  JOptionPane.showMessageDialog(null, ex);   
             }
         
//setDefaultCloseOperation(DISPOSE_ON_CLOSE); 
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
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jDateChooser1 = new com.alee.extended.date.WebDateField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jTextField1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTextField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton1.setText("OK");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jDateChooser1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jDateChooser1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jDateChooser1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jDateChooser1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField1)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
 try{
 con=JavaDbConnect.dbConnect(); 
 reports(); 
this.dispose();
}catch(Exception ex){JOptionPane.showMessageDialog(null,ex);
} finally {
    if (rs != null) {
        try {
            rs.close();
        } catch (SQLException ex) { /* Ignored */}
    }
    if (pst != null) {
        try {
            pst.close();
        } catch (SQLException ex) { /* Ignored */}
    }
    if (con != null) {
        try {
            con.close();
        } catch (SQLException ex) { /* Ignored */}
    }
} 
       //// TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jDateChooser1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jDateChooser1ActionPerformed
        // TODO add your handling jrn here:
    }//GEN-LAST:event_jDateChooser1ActionPerformed

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
            java.util.logging.Logger.getLogger(pourcentage1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(pourcentage1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(pourcentage1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(pourcentage1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new pourcentage1().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private com.alee.extended.date.WebDateField jDateChooser1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
