package sample.notification;

import intreprisemanagementsystem.JavaDbConnect;
import java.awt.Color;
import java.awt.Dimension;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JScrollBar;
import net.miginfocom.swing.MigLayout;
import sample.swing.ModernScrollBarUI;

/**
 *
 * @author RAVEN
 */
public class newshop extends javax.swing.JPanel {
  private Connection con;
private Statement st;
PreparedStatement pst=null;
ResultSet rs= null;
    public newshop(String Nom,String Id) {
        initComponents();
        con=JavaDbConnect.dbConnect();
    
        nom.setText(Nom);
         idd.setText(Id);
        
        
    }

      public void call_fiche(){
 sample.message.add_on_stock.jPanel2.removeAll();
 sample.message.add_on_stock.jPanel2.repaint();
 sample.message.add_on_stock.jPanel2.revalidate();
     //   setOpaque(false);
        JScrollBar sb = sample.message.add_on_stock.scroll.getVerticalScrollBar();
        sb.setOpaque(false);
        sb.setForeground(new Color(33, 140, 206));
        sb.setPreferredSize(new Dimension(8, 8));
        sb.setUI(new ModernScrollBarUI());
        sample.message.add_on_stock.scroll.getViewport().setOpaque(false);
        sample.message.add_on_stock.scroll.setViewportBorder(null);
        sample.message.add_on_stock.jPanel2.setLayout(new MigLayout("inset 0, fillx, wrap", "[fill]"));

        try{

            String sql="SELECT  * FROM  shop ";
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                 String Nom = rs.getString("Nom");
                 String Id = rs.getString("ID");
                

                // C:\Users\Doshe PC\Documents\NetBeansProjects\hand_book\src\sample\notification\fiche_medicale.jpg
              sample.message.add_on_stock.jPanel2.add(new sample.notification.newshop(Nom,Id));
            }
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

        nom = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        idd = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setOpaque(false);
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });

        nom.setBackground(java.awt.SystemColor.control);
        nom.setFont(new java.awt.Font("sansserif", 1, 13)); // NOI18N
        nom.setForeground(new java.awt.Color(106, 106, 106));
        nom.setIcon(new javax.swing.ImageIcon(getClass().getResource("/poisonneries/icons8_Shop_30px.png"))); // NOI18N
        nom.setText("Rapport Vente");
        nom.setOpaque(true);
        nom.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nomMouseClicked(evt);
            }
        });

        jLabel1.setBackground(java.awt.SystemColor.inactiveCaption);
        jLabel1.setOpaque(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );

        idd.setBackground(java.awt.SystemColor.control);
        idd.setFont(new java.awt.Font("Segoe UI", 0, 6)); // NOI18N
        idd.setForeground(java.awt.SystemColor.control);
        idd.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        idd.setIcon(new javax.swing.ImageIcon("C:\\Users\\Doshe PC\\Documents\\NetBeansProjects\\hand_book\\src\\hand_book\\cancel.png")); // NOI18N
        idd.setToolTipText("");
        idd.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        idd.setOpaque(true);
        idd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                iddMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(nom, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(idd, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(nom, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                    .addComponent(idd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void nomMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nomMouseClicked

//home.shop.setText(nom.getText());       // TODO add your handling code here:
    }//GEN-LAST:event_nomMouseClicked

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
       // TODO add your handling code here:
    }//GEN-LAST:event_formMouseClicked

    private void iddMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_iddMouseClicked
        
        try{
        String sql = "DELETE FROM  add_on_stock WHERE ID=? ";
         
         pst = con.prepareStatement(sql);
         pst.setString(1,idd.getText());
        
         pst.executeUpdate();
//JOptionPane.showMessageDialog(null,"done");
     }catch(Exception ex ){
         JOptionPane.showMessageDialog(null,ex);
     }  
       //  JOptionPane.showMessageDialog(null,idd.getText());
call_fiche();// TODO add your handling code here:
    }//GEN-LAST:event_iddMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel idd;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel nom;
    // End of variables declaration//GEN-END:variables
}
