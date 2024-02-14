/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Home_page;

import com.alee.laf.WebLookAndFeel;
import intreprisemanagementsystem.JavaDbConnect;
import intreprisemanagementsystem.LOGINS_homme_update;
import static intreprisemanagementsystem.homme.user;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;
import javax.swing.plaf.metal.MetalLookAndFeel;
import org.jb2011.lnf.beautyeye.BeautyEyeLookAndFeelWin;

/**
 *
 * @author DOSHE
 */
public class LOGINS_homme extends javax.swing.JFrame {

  DefaultTableModel mode;
private Connection con=null;
PreparedStatement pst=null;
ResultSet rs= null;
    public LOGINS_homme() {
        initComponents();
           con=JavaDbConnect.dbConnect();
           setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("icons8_Raspberry_Pi_48px_1.png")));
         // BeautyEyeLookAndFeelWin.initLookAndFeelDecorated(); 
       //本属性仅对windows平台有效？！ -> Jack Jiang最终证实没效果！！！！！！！！！！！
		UIManager.put("Application.useSystemFontSettings", Boolean.TRUE);
		//取消Metal LNF中默认的粗体字
		UIManager.put("swing.boldMetal", Boolean.TRUE);
		//此项如是true，则将会为TabbedPane的内容面板填充天蓝色背景
		UIManager.put("TabbedPane.contentOpaque", Boolean.TRUE);
		//此项如是true，则将会为TabbedPane的标签填充天蓝色背景
		UIManager.put("TabbedPane.tabsOpaque", Boolean.TRUE);
	//	BeautyEyeLNFHelper.implLNF();
		
		//自定义JFileChooser的L&F实现（为了解决JFileChooser中的文件查看列表的行高问题）
		org.jb2011.lnf.beautyeye.ch20_filechooser.__UI__.uiImpl_cross();
		
		JFrame.setDefaultLookAndFeelDecorated(true);
		JDialog.setDefaultLookAndFeelDecorated(true); 
           Close.setVisible(false);
          Open.setVisible(true);
           
    }
    
public void login(){
  // this.dispose();
     
     
        try{
             String sql ="select name from password where  user=? and pass= ? ";
          
            pst=con.prepareStatement(sql);
            pst.setString(1,user.getText());
            pst.setString(2,pass.getText());
            rs=pst.executeQuery();
           if(rs.next()){
               
               String lo= rs.getString("name");
               
              if(lo.equals("Pharmacien")){
               this.dispose();
            intreprisemanagementsystem.Pharmacies m = new intreprisemanagementsystem.Pharmacies();
                     // Home_page.home.user.setText(lo);
// 
        m.show();
        m.pack();
         m.setExtendedState(JFrame.MAXIMIZED_BOTH);
        m. setDefaultCloseOperation(DISPOSE_ON_CLOSE);
              }else{
            this.dispose();
                     Home_page.home m = new Home_page.home();
                      Home_page.home.user.setText(lo);
// 
        m.show();
        m.pack();
         m.setExtendedState(JFrame.MAXIMIZED_BOTH);
        m. setDefaultCloseOperation(DISPOSE_ON_CLOSE);
           }
            
                   
       // WebLookAndFeel.install(true); 
       
            }else{
               msg.setText("NOM D'UTILISATEUR OU MOT DE PASSE INCORECT");
                user.setText("");
           pass.setText("");
            }
         }catch(Exception ex){
            msg.setText(ex.toString());
         }
     
     
   
} 
         
            
            public void callback(){
//                  ImageIcon myimage = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("banckgrounds.jpg")));
//        Image img1 = myimage.getImage();
//        Image img2=img1.getScaledInstance(homme.images.getWidth(),homme.images.getHeight(),Image.SCALE_SMOOTH);
//        ImageIcon i = new ImageIcon(img2);
//        homme.images.setIcon(i);
      
//            try {
//                
//              //Connection  con= DriverManager.getConnection("jdbc:mysql://localhost/entreprise_management_system","root","");
//              Statement st = con.createStatement();
//              //  String sql="UPDATE `back_ground` SET `img`=? WHERE ID=1";
//              ResultSet rs = st.executeQuery("select * from back_ground where  USER='"+homme.user.getText()+"'");
//              if(rs.next()){
//                  byte[] img = rs.getBytes("img");
//                  ImageIcon image =new ImageIcon(img);
//                  Image im = image.getImage();
//                  Image myIm = im.getScaledInstance(homme.images.getWidth(),homme.images.getHeight(),Image.SCALE_SMOOTH);
//                  ImageIcon newImage= new ImageIcon(myIm);
//                  homme.images.setIcon(newImage);
//              }
//                }
//             catch (Exception ex) {
//                JOptionPane.showMessageDialog(null, ex);
//            } 
//      
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
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        pass = new javax.swing.JPasswordField();
        user = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        Open = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        Close = new javax.swing.JLabel();
        msg = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("LOGIN");

        jLabel6.setBackground(new java.awt.Color(0, 0, 0));
        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("X");
        jLabel6.setOpaque(true);
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8-reset-password-30.png"))); // NOI18N
        jLabel5.setText("Reset Password");
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pass.setBackground(new java.awt.Color(240, 240, 241));
        pass.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        pass.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        pass.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                passKeyPressed(evt);
            }
        });
        jPanel2.add(pass, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 95, 260, 20));

        user.setBackground(new java.awt.Color(240, 240, 241));
        user.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        user.setBorder(null);
        jPanel2.add(user, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 46, 290, 20));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("UserName");
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Password");
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 75, -1, 20));

        jTextField1.setEditable(false);
        jTextField1.setBackground(new java.awt.Color(0, 0, 0));
        jTextField1.setFont(new java.awt.Font("Tahoma", 0, 3)); // NOI18N
        jTextField1.setText("jTextField1");
        jPanel2.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 110, 260, 10));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8-user-rights-30.png"))); // NOI18N
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 34, -1, 40));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8-hotel-room-key-30.png"))); // NOI18N
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 93, -1, 30));

        Open.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Open.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8-eye-20.png"))); // NOI18N
        Open.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Open.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        Open.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                OpenMouseClicked(evt);
            }
        });
        jPanel2.add(Open, new org.netbeans.lib.awtextra.AbsoluteConstraints(306, 90, 40, 40));

        jTextField2.setEditable(false);
        jTextField2.setBackground(new java.awt.Color(0, 0, 0));
        jTextField2.setFont(new java.awt.Font("Tahoma", 0, 3)); // NOI18N
        jTextField2.setText("jTextField1");
        jPanel2.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 60, 290, 10));

        Close.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Close.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8-closed-eye-20.png"))); // NOI18N
        Close.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Close.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        Close.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CloseMouseClicked(evt);
            }
        });
        jPanel2.add(Close, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 90, 50, 40));

        msg.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        msg.setForeground(new java.awt.Color(255, 0, 0));
        msg.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel2.add(msg, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 350, 20));

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/BTN1.png"))); // NOI18N
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel8MouseClicked(evt);
            }
        });

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/ems.png"))); // NOI18N
        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel9MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel8))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(3, 3, 3)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5))
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
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

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel6MouseClicked

    private void passKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_passKeyPressed
 if (evt.getKeyCode()==KeyEvent.VK_ENTER){
      try{
         // WebLookAndFeel.install(true);
  login();
  callback();
   //con.close();
    rs.close();
   pst.close();

    }catch(Exception ex){}
       //   JOptionPane.showMessageDialog(null, ex); }       
}        // TODO add your handling code here:
    }//GEN-LAST:event_passKeyPressed

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked

        LOGINS_homme_update m = new LOGINS_homme_update(); 
  m.show();
        
        m. setDefaultCloseOperation(DISPOSE_ON_CLOSE);
     this.dispose();   
              // TODO add your handling code here:
    }//GEN-LAST:event_jLabel5MouseClicked

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
pass.setEchoChar((char) 0);        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel3MouseClicked

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
      // TODO add your handling code here:
    }//GEN-LAST:event_jLabel2MouseClicked

    private void CloseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CloseMouseClicked
pass.setEchoChar('.'); 
Close.setVisible(false);
Open.setVisible(true);// TODO add your handling code here:
    }//GEN-LAST:event_CloseMouseClicked

    private void OpenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_OpenMouseClicked
pass.setEchoChar((char) 0);   
Open.setVisible(false);
Close.setVisible(true);// TODO add your handling code here:
    }//GEN-LAST:event_OpenMouseClicked

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked
 try{
   //  WebLookAndFeel.install(true);
  login();
  callback();
   //con.close();
    rs.close();
   pst.close();

    }catch(Exception ex){}
          //JOptionPane.showMessageDialog(null, ex); }        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel8MouseClicked

    private void jLabel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseClicked
try{
   //  WebLookAndFeel.install(true);
  login();
  callback();
   //con.close();
    rs.close();
   pst.close();

    }catch(Exception ex){}
         // msg.setText(ex); }         // TODO add your handling code here:
    }//GEN-LAST:event_jLabel9MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        /*    
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    */    
        try
    {
        WebLookAndFeel.install(true); 
        org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
        
    }
    catch(Exception e)
    {
        //TODO exception
    }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
          //  WebLookAndFeel.install(true);        
               
                new LOGINS_homme().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Close;
    private javax.swing.JLabel Open;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JLabel msg;
    private javax.swing.JPasswordField pass;
    private javax.swing.JTextField user;
    // End of variables declaration//GEN-END:variables
}
