package sample.notification;

import intreprisemanagementsystem.JavaDbConnect;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Path2D;
import java.awt.geom.RoundRectangle2D;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JScrollBar;
import net.miginfocom.swing.MigLayout;
import sample.swing.ModernScrollBarUI;

/**
 *
 * @author RAVEN
 */
public class Notifications extends javax.swing.JPanel {

    private Connection con;
private Statement st;
PreparedStatement pst=null;
ResultSet rs= null;
    public Notifications() {
        initComponents();
         con=JavaDbConnect.dbConnect();
        setOpaque(false);
        JScrollBar sb = scroll.getVerticalScrollBar();
        sb.setOpaque(false);
        sb.setForeground(new Color(33, 140, 206));
        sb.setPreferredSize(new Dimension(8, 8));
        sb.setUI(new ModernScrollBarUI());
        scroll.getViewport().setOpaque(false);
        scroll.setViewportBorder(null);
        panel.setLayout(new MigLayout("inset 0, fillx, wrap", "[fill]"));
        loadNoti();
    }

    private void loadNoti() {
         try{
             
          String sql="SELECT  * FROM  h_consultation";
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
         while(rs.next()){
             String Matri = rs.getString("MATRICULE");
             String Num = rs.getString("NUM");
             String Nom = rs.getString("NOMS");
             String Age = rs.getString("AGE");
             //C:\Users\Doshe PC\Documents\NetBeansProjects\hand_book\src\sample\notification\fiche_medicale.jpg
       //   panel.add(new Item(new ImageIcon(getClass().getResource("/notification/fiche_medicale.jpg")),Matri, Num, Nom, Age));       
           }
            }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}


    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        int header = 10;
        AffineTransform tran = new AffineTransform();
        tran.translate(getWidth() - 25, 5);
        tran.rotate(Math.toRadians(45));
        Path2D p = new Path2D.Double(new RoundRectangle2D.Double(0, 0, 20, 20, 5, 5), tran);
        Area area = new Area(p);
        area.add(new Area(new RoundRectangle2D.Double(0, header, getWidth(), getHeight() - header, 10, 10)));
        g2.fill(area);
        g2.dispose();
        super.paintComponent(grphcs);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        scroll = new javax.swing.JScrollPane();
        panel = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createEmptyBorder(15, 10, 10, 10));

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 15)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(117, 117, 117));
        jLabel1.setText("Notifications");

        scroll.setBorder(null);
        scroll.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        panel.setOpaque(false);

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 262, Short.MAX_VALUE)
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 334, Short.MAX_VALUE)
        );

        scroll.setViewportView(panel);

        jButton1.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(33, 140, 206));
        jButton1.setText("Show All");
        jButton1.setContentAreaFilled(false);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(scroll, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scroll, javax.swing.GroupLayout.DEFAULT_SIZE, 334, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel panel;
    private javax.swing.JScrollPane scroll;
    // End of variables declaration//GEN-END:variables
}
