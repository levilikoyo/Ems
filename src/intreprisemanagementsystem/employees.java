/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intreprisemanagementsystem;
import java.awt.Image;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Doshe
 */
public class employees extends javax.swing.JFrame {
private Connection con=null;
PreparedStatement pst=null;
ResultSet rs= null;
String path;
    public employees() {
        initComponents();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        con=JavaDbConnect.dbConnect();
        //broswer();
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("icons8_Raspberry_Pi_48px_1.png")));
        callback();
    }
    
     ///IMAGE
      public void callback(){
      
            try {
                
              //Connection  con= DriverManager.getConnection("jdbc:mysql://localhost/entreprise_management_system","root","");
              Statement st = con.createStatement();
              //  String sql="UPDATE `back_ground` SET `img`=? WHERE ID=1";
              ResultSet rs = st.executeQuery("select * from back_ground where ID= 1");
              if(rs.next()){
                  byte[] img = rs.getBytes("img");
                  ImageIcon image =new ImageIcon(img);
                  Image im = image.getImage();
                  Image myIm = im.getScaledInstance(images.getWidth(),images.getHeight(),Image.SCALE_SMOOTH);
                  ImageIcon newImage= new ImageIcon(myIm);
                  images.setIcon(newImage);
              }
                }
             catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex);
            } 
      
      }
    public ImageIcon ResizeImage(String ImagePath, byte[] pic)
    {
        ImageIcon MyImage = null;
        if(ImagePath !=null)
        {
            MyImage = new ImageIcon(ImagePath);
        }else
        {
            MyImage = new ImageIcon (ImagePath);
        }
        Image img =MyImage.getImage();
        Image newImg= img.getScaledInstance(images.getWidth(),images.getHeight(),Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(newImg);
        return image;
    }
      String imgPath= "C:\\Users\\Dosh\\Documents\\NetBeansProjects\\intreprisemanagementsystem\\CaptureSSS.JPG";
      public void broswer()
              
     {
       String imgPath="C:\\Users\\Dosh\\Documents\\NetBeansProjects\\intreprisemanagementsystem\\CaptureSSS.JPG";
       FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Images","jpg","gif","png");
       images.setIcon(ResizeImage(imgPath,null));       
     }
      
      
      
     public void enable(){

tableudebor.huille1.setEnabled(false);
tableudebor.huille0.setEnabled(false);
tableudebor.refroidisement1.setEnabled(false);
tableudebor.refroidisement0.setEnabled(false);




tableudebor.glase1.setEnabled(false);
tableudebor.glase0.setEnabled(false);
tableudebor.liquidefreins1.setEnabled(false);
tableudebor.liquidefreins0.setEnabled(false);


tableudebor.batterie1.setEnabled(false);
tableudebor.batterie0.setEnabled(false);
tableudebor.avant1.setEnabled(false);
tableudebor.avant0.setEnabled(false);


tableudebor.huilled.setEnabled(false);
tableudebor.refroidisementd.setEnabled(false);
tableudebor.glased.setEnabled(false);
tableudebor.liquidefreinsd.setEnabled(false);
tableudebor.batteried.setEnabled(false);
tableudebor.avantd.setEnabled(false);




tableudebor.vitre1.setEnabled(false);
tableudebor.vitre0.setEnabled(false);
tableudebor.seeuisglase1.setEnabled(false);
tableudebor.seeuisglase0.setEnabled(false);

tableudebor.arriere1.setEnabled(false);
tableudebor.arriere0.setEnabled(false);
tableudebor.cole1.setEnabled(false);
tableudebor.cole0.setEnabled(false);


tableudebor.retroviseurs1.setEnabled(false);
tableudebor.retroviseurs0.setEnabled(false);
tableudebor.immatriculation1.setEnabled(false);
tableudebor.immatriculation0.setEnabled(false);


tableudebor.girophare1.setEnabled(false);
tableudebor.girophare0.setEnabled(false);
tableudebor.feuxdepossition1.setEnabled(false);
tableudebor.feuxdepossition0.setEnabled(false);

tableudebor.feuxstop1.setEnabled(false);
tableudebor.feuxstop0.setEnabled(false);
tableudebor.clignotant1.setEnabled(false);
tableudebor.clignotant0.setEnabled(false);


tableudebor.phare1.setEnabled(false);
tableudebor.phare0.setEnabled(false);
tableudebor.claxion1.setEnabled(false);
tableudebor.claxion0.setEnabled(false);



tableudebor.girophared.setEnabled(false);
tableudebor.feuxdepossitiond.setEnabled(false);
tableudebor.clignotantd.setEnabled(false);
tableudebor.phared.setEnabled(false);
tableudebor.claxiond.setEnabled(false);










tableudebor.vitred.setEnabled(false);
tableudebor.seeuisglased.setEnabled(false);
tableudebor.arriered.setEnabled(false);
tableudebor.coled.setEnabled(false);
tableudebor.retroviseursd.setEnabled(false);
tableudebor.immatriculationd.setEnabled(false);











tableudebor.phared.setEnabled(false);
tableudebor.claxiond.setEnabled(false);
tableudebor.seintured.setEnabled(false);
tableudebor.paraprised.setEnabled(false);


tableudebor.seinture1.setEnabled(false);
tableudebor.seinture0.setEnabled(false);
tableudebor.paraprise1.setEnabled(false);
tableudebor.paraprise0.setEnabled(false);




tableudebor.frien1.setEnabled(false);
tableudebor.frien0.setEnabled(false);
tableudebor.freinamain1.setEnabled(false);
tableudebor.freinamain0.setEnabled(false);



tableudebor.pneureserve1.setEnabled(false);
tableudebor.pneureserve0.setEnabled(false);
tableudebor.interiere1.setEnabled(false);
tableudebor.interiere0.setEnabled(false);



tableudebor.exteriere1.setEnabled(false);
tableudebor.exteriere0.setEnabled(false);

tableudebor.friend.setEnabled(false);
tableudebor.freinamaind.setEnabled(false);
tableudebor.pneureserved.setEnabled(false);
tableudebor.interiered.setEnabled(false);
tableudebor.exteriered.setEnabled(false);



tableudebor.jComboBox1.setEnabled(false);
//tableudebor.km.setEnabled(false);
//tableudebor.jComboBox6.setEnabled(false);
//tableudebor.jDateChooser2.setEnabled(false);


     }
      
      
      
      

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDesktopPane1 = new javax.swing.JDesktopPane();
        images = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu2 = new javax.swing.JMenu();
        jMenu6 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenu7 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jMenu9 = new javax.swing.JMenu();
        jMenu5 = new javax.swing.JMenu();
        jMenu8 = new javax.swing.JMenu();
        jMenu1 = new javax.swing.JMenu();
        jMenu10 = new javax.swing.JMenu();
        jMenu11 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        images.setBackground(new java.awt.Color(255, 255, 255));
        images.setForeground(new java.awt.Color(255, 255, 255));
        images.setOpaque(true);

        jDesktopPane1.setLayer(images, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(images, javax.swing.GroupLayout.DEFAULT_SIZE, 1332, Short.MAX_VALUE)
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(images, javax.swing.GroupLayout.DEFAULT_SIZE, 641, Short.MAX_VALUE)
        );

        jMenuBar1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        jMenu2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jMenu2.setText("   Attendance");
        jMenu2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jMenu2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu2MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu2);

        jMenu6.setText("  ");
        jMenu6.setEnabled(false);
        jMenuBar1.add(jMenu6);

        jMenu3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jMenu3.setText("     Program");
        jMenu3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jMenu3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu3MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu3);

        jMenu7.setText("   ");
        jMenu7.setEnabled(false);
        jMenuBar1.add(jMenu7);

        jMenu4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jMenu4.setText("      Devis");
        jMenu4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jMenu4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu4MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu4);

        jMenu9.setText("   ");
        jMenu9.setEnabled(false);
        jMenuBar1.add(jMenu9);

        jMenu5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jMenu5.setText("      Etat de Besoin");
        jMenu5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jMenu5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu5MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu5);

        jMenu8.setText("   ");
        jMenu8.setEnabled(false);
        jMenuBar1.add(jMenu8);

        jMenu1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jMenu1.setText("     Tableau de Bord");
        jMenu1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jMenu1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu1MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu1);

        jMenu10.setText("    Visitors");
        jMenu10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jMenu10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu10MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu10);

        jMenu11.setText("Agin Back");
        jMenuBar1.add(jMenu11);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jMenu3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu3MouseClicked
NewJInternalFrameEmp_Info m= new NewJInternalFrameEmp_Info()  ; 


jDesktopPane1.add(m);
m.show();
m.setDefaultCloseOperation(DISPOSE_ON_CLOSE);        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu3MouseClicked

    private void jMenu2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu2MouseClicked
      // TODO add your handling code here:
    }//GEN-LAST:event_jMenu2MouseClicked

    private void jMenu4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu4MouseClicked
NewJInternalFrameDevis    m= new NewJInternalFrameDevis   ();
jDesktopPane1.add(m);
m.show();
m.setDefaultCloseOperation(DISPOSE_ON_CLOSE);        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu4MouseClicked

    private void jMenu5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu5MouseClicked
NewJInternalFrameEtatdebesoin  m= new NewJInternalFrameEtatdebesoin ();
jDesktopPane1.add(m);
            m.show();
           // m.setAlwaysOnTop(true);
   m. setDefaultCloseOperation(DISPOSE_ON_CLOSE);         // TODO add your handling code here:
    }//GEN-LAST:event_jMenu5MouseClicked

    private void jMenu1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu1MouseClicked
 tableaudebor_emp m= new tableaudebor_emp();
        jDesktopPane1.add(m);
        m.show();
        m. setDefaultCloseOperation(DISPOSE_ON_CLOSE);            // TODO add your handling code here:
    }//GEN-LAST:event_jMenu1MouseClicked

    private void jMenu10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu10MouseClicked
vistors_reg   m= new vistors_reg();

      // NewJInternalFramemateriel_out  m= new NewJInternalFramemateriel_out ();
  jDesktopPane1.add(m);
   m.show();
   m. setDefaultCloseOperation(DISPOSE_ON_CLOSE);      // TODO add your handling code here:
    }//GEN-LAST:event_jMenu10MouseClicked

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
                if ("Web LaF".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(employees.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(employees.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(employees.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(employees.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new employees().setVisible(true);
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel images;
    public static javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu10;
    private javax.swing.JMenu jMenu11;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenu jMenu8;
    private javax.swing.JMenu jMenu9;
    private javax.swing.JMenuBar jMenuBar1;
    // End of variables declaration//GEN-END:variables
}
