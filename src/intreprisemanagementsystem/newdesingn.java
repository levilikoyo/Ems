/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intreprisemanagementsystem;

import static intreprisemanagementsystem.homme.jDesktopPane1;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import javax.swing.plaf.basic.BasicInternalFrameUI;

/**
 *
 * @author Dosh
 */
public class newdesingn extends javax.swing.JInternalFrame {

   private Connection con=null;
PreparedStatement pst=null;
ResultSet rs= null;
String path;
    public newdesingn() {
        initComponents();
         this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI bui= (BasicInternalFrameUI) this.getUI();
       bui.setNorthPane(null);
        con=JavaDbConnect.dbConnect();
    
   
     // callback();
     loading();
    }
    
    
  
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
String imgPath=path; 
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
     
     public void loading(){
     
       try{
            String sql="select ACESS from user_write where NAME='"+homme.user.getText()+"' and CHAMP='Journal Caisse'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
             caisse.setText(rs.getString("ACESS"));
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex); }  
       
        try{
            String sql="select ACESS from user_write where NAME='"+homme.user.getText()+"' and CHAMP='Journal Banque'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
             bank.setText(rs.getString("ACESS"));
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex); }  
        
         try{
            String sql="select ACESS from user_write where NAME='"+homme.user.getText()+"' and CHAMP='Journal Vente'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
             vente.setText(rs.getString("ACESS"));
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex); }  
         
          try{
            String sql="select ACESS from user_write where NAME='"+homme.user.getText()+"' and CHAMP='Journal Achat'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
             achat.setText(rs.getString("ACESS"));
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex); }  
          
           try{
            String sql="select ACESS from user_write where NAME='"+homme.user.getText()+"' and CHAMP='Journal Fournisseur'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
             four.setText(rs.getString("ACESS"));
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex); }  
           
            try{
            String sql="select ACESS from user_write where NAME='"+homme.user.getText()+"' and CHAMP='Journal  Client'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
             client.setText(rs.getString("ACESS"));
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex); }  
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
        caisseaa = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        caisse = new javax.swing.JLabel();
        bank = new javax.swing.JLabel();
        vente = new javax.swing.JLabel();
        achat = new javax.swing.JLabel();
        client = new javax.swing.JLabel();
        four = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jod = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        images = new javax.swing.JLabel();

        setTitle("******************************************************************************************************************************JOURNALS******************************************************************************************************************");

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        caisseaa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                caisseaaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                caisseaaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                caisseaaMouseExited(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Cash/Caisse journal");
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel1MouseExited(evt);
            }
        });

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_Coins_16px.png"))); // NOI18N

        javax.swing.GroupLayout caisseaaLayout = new javax.swing.GroupLayout(caisseaa);
        caisseaa.setLayout(caisseaaLayout);
        caisseaaLayout.setHorizontalGroup(
            caisseaaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(caisseaaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        caisseaaLayout.setVerticalGroup(
            caisseaaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(caisseaaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(caisseaaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, caisseaaLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel5))
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel3.setForeground(new java.awt.Color(204, 255, 204));
        jPanel3.setEnabled(false);
        jPanel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel3MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel3MouseExited(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Bank/Banque Journal");

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_Bank_Building_16px.png"))); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setEnabled(false);
        jPanel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel5MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel5MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel5MouseExited(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Sales/Vente Journal");

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_Lease_16px.png"))); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel3))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jPanel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel6MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel6MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel6MouseExited(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Purchase/Achat Journal");
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_Payment_History_16px.png"))); // NOI18N

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel8)
                    .addComponent(jLabel4))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jPanel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel7MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel7MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel7MouseExited(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setText("Client/ Client journal");
        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel9MouseClicked(evt);
            }
        });

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_Reception_16px.png"))); // NOI18N

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel9)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(jLabel11))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel8MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel8MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel8MouseExited(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setText("Supply/Fourn. Journal");

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_Delivered_16px.png"))); // NOI18N

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel10)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(jLabel12))
                .addGap(11, 11, 11))
        );

        jPanel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel9MouseClicked(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 51, 51));
        jLabel13.setText("Log_Out");

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_Shutdown_16px.png"))); // NOI18N

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel13)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        caisse.setForeground(new java.awt.Color(240, 240, 240));
        caisse.setText("No");
        caisse.setOpaque(true);

        bank.setForeground(new java.awt.Color(240, 240, 240));
        bank.setText("No");
        bank.setOpaque(true);

        vente.setForeground(new java.awt.Color(240, 240, 240));
        vente.setText("No");
        vente.setOpaque(true);

        achat.setForeground(new java.awt.Color(240, 240, 240));
        achat.setText("No");
        achat.setOpaque(true);

        client.setForeground(new java.awt.Color(240, 240, 240));
        client.setText("No");
        client.setOpaque(true);

        four.setForeground(new java.awt.Color(240, 240, 240));
        four.setText("No");
        four.setOpaque(true);

        jPanel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel10MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel10MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel10MouseExited(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel15.setText("JOD");

        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_Delivered_16px.png"))); // NOI18N

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel15)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addComponent(jLabel16))
                .addGap(11, 11, 11))
        );

        jod.setForeground(new java.awt.Color(240, 240, 240));
        jod.setText("No");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(caisseaa, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(caisse)
                    .addComponent(bank)
                    .addComponent(vente)
                    .addComponent(achat)
                    .addComponent(client)
                    .addComponent(four)
                    .addComponent(jod))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(caisseaa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(caisse)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bank)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(vente)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(achat)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(client)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(four)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jod)
                .addContainerGap(41, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        images.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        images.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/pn4YyF.jpg"))); // NOI18N

        jDesktopPane1.setLayer(images, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(images)
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(images, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        title.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        title.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        title.setText("Windows");
        title.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        title.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jDesktopPane1)
                    .addComponent(title, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(title)
                .addGap(0, 0, 0)
                .addComponent(jDesktopPane1))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void caisseaaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_caisseaaMouseClicked
if(caisse.getText().equals("No")){
 JOptionPane.showMessageDialog(null," Acces Limiter","Error",JOptionPane.WARNING_MESSAGE);
}else{
  title.setText("Cash Journal / Journal Caisse");
journal_caisse  m= new  journal_caisse  ();
      jDesktopPane1.add(m);
   m.show();
   newdesingn.caisseaa.setBackground(Color.getHSBColor(500,500,500));
   m. setDefaultCloseOperation(DISPOSE_ON_CLOSE); 
}
    }//GEN-LAST:event_caisseaaMouseClicked

    private void jPanel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel3MouseClicked
if(bank.getText().equals("No")){
 JOptionPane.showMessageDialog(null," Acces Limiter","Error",JOptionPane.WARNING_MESSAGE);
}else{
    title.setText("Bank Journal / Journal Banque");
        NewJInternalFramejournal_de_banque m = new NewJInternalFramejournal_de_banque();
 jDesktopPane1.add(m);
   m.show();
   m. setDefaultCloseOperation(DISPOSE_ON_CLOSE); }          // TODO add your handling code here:
    }//GEN-LAST:event_jPanel3MouseClicked

    private void jPanel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel5MouseClicked
if(vente.getText().equals("No")){
 JOptionPane.showMessageDialog(null," Acces Limiter","Error",JOptionPane.WARNING_MESSAGE);
}else{
    title.setText("Sales Journal / Journal Vente");
        Journal_de_Vente  m= new Journal_de_Vente ();
     jDesktopPane1.add(m);
   m.show();
   m. setDefaultCloseOperation(DISPOSE_ON_CLOSE); }         // TODO add your handling code here:
    }//GEN-LAST:event_jPanel5MouseClicked

    private void jPanel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel6MouseClicked
 if(achat.getText().equals("No")){
 JOptionPane.showMessageDialog(null," Acces Limiter","Error",JOptionPane.WARNING_MESSAGE);
}else{
     title.setText("Purshase Journal / Journal d'Achat");
        journal_achat m = new journal_achat();
 jDesktopPane1.add(m);
   m.show();
   m. setDefaultCloseOperation(DISPOSE_ON_CLOSE); }        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel6MouseClicked

    private void jLabel1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseEntered
jLabel1.setBackground(Color.green);       // TODO add your handling code here:
    }//GEN-LAST:event_jLabel1MouseEntered

    private void jLabel1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseExited
jLabel1.setBackground(Color.white);            // TODO add your handling code here:
    }//GEN-LAST:event_jLabel1MouseExited

    private void caisseaaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_caisseaaMouseEntered
caisseaa.setBackground(Color.getHSBColor(500,500,500));
//jPanel4.setBackground(null);// TODO add your handling code here:
    }//GEN-LAST:event_caisseaaMouseEntered

    private void caisseaaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_caisseaaMouseExited
caisseaa.setBackground(null);        // TODO add your handling code here:
    }//GEN-LAST:event_caisseaaMouseExited

    private void jPanel3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel3MouseEntered
jPanel3.setBackground(Color.getHSBColor(500,500,500));        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel3MouseEntered

    private void jPanel3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel3MouseExited
jPanel3.setBackground(null);        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel3MouseExited

    private void jPanel5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel5MouseEntered
jPanel5.setBackground(Color.getHSBColor(500,500,500));         // TODO add your handling code here:
    }//GEN-LAST:event_jPanel5MouseEntered

    private void jPanel6MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel6MouseEntered
jPanel6.setBackground(Color.getHSBColor(500,500,500));         // TODO add your handling code here:
    }//GEN-LAST:event_jPanel6MouseEntered

    private void jPanel7MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel7MouseEntered
jPanel7.setBackground(Color.getHSBColor(500,500,500));         // TODO add your handling code here:
    }//GEN-LAST:event_jPanel7MouseEntered

    private void jPanel8MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel8MouseEntered
jPanel8.setBackground(Color.getHSBColor(500,500,500));         // TODO add your handling code here:
    }//GEN-LAST:event_jPanel8MouseEntered

    private void jPanel5MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel5MouseExited
jPanel5.setBackground(null);         // TODO add your handling code here:
    }//GEN-LAST:event_jPanel5MouseExited

    private void jPanel6MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel6MouseExited
jPanel6.setBackground(null);         // TODO add your handling code here:
    }//GEN-LAST:event_jPanel6MouseExited

    private void jPanel7MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel7MouseExited
jPanel7.setBackground(null);         // TODO add your handling code here:
    }//GEN-LAST:event_jPanel7MouseExited

    private void jPanel8MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel8MouseExited
jPanel8.setBackground(null);         // TODO add your handling code here:
    }//GEN-LAST:event_jPanel8MouseExited

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked

                 // TODO add your handling code here:
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
         // TODO add your handling code here:
    }//GEN-LAST:event_jLabel4MouseClicked

    private void jLabel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel9MouseClicked

    private void jPanel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel7MouseClicked
if(client.getText().equals("No")){
 JOptionPane.showMessageDialog(null," Acces Limiter","Error",JOptionPane.WARNING_MESSAGE);
}else{
    title.setText("Clients Journal / Journal Client");
        journal_client m = new journal_client();
 jDesktopPane1.add(m);
   m.show();
   m. setDefaultCloseOperation(DISPOSE_ON_CLOSE);  }       // TODO add your handling code here:
    }//GEN-LAST:event_jPanel7MouseClicked

    private void jPanel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel8MouseClicked
 if(four.getText().equals("No")){
 JOptionPane.showMessageDialog(null," Acces Limiter","Error",JOptionPane.WARNING_MESSAGE);
}else{
     title.setText("Suppliers Journal / Journal Fournisseur");
        journal_fournisseur  m= new journal_fournisseur ();
     jDesktopPane1.add(m);
   m.show();
   m. setDefaultCloseOperation(DISPOSE_ON_CLOSE);}          // TODO add your handling code here:
    }//GEN-LAST:event_jPanel8MouseClicked

    private void jPanel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel9MouseClicked
this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel9MouseClicked

    private void jPanel10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel10MouseClicked
//if(jod.getText().equals("No")){
// JOptionPane.showMessageDialog(null," Acces Limiter","Error",JOptionPane.WARNING_MESSAGE);
//}else{
      caisse_bruoillons  m= new caisse_bruoillons ();
     jDesktopPane1.add(m);
   m.show();
   m. setDefaultCloseOperation(DISPOSE_ON_CLOSE);//} 
// TODO add your handling code here:
    }//GEN-LAST:event_jPanel10MouseClicked

    private void jPanel10MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel10MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel10MouseEntered

    private void jPanel10MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel10MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel10MouseExited


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel achat;
    private javax.swing.JLabel bank;
    private javax.swing.JLabel caisse;
    public static javax.swing.JPanel caisseaa;
    private javax.swing.JLabel client;
    private javax.swing.JLabel four;
    private javax.swing.JLabel images;
    public static javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JLabel jod;
    public static final javax.swing.JLabel title = new javax.swing.JLabel();
    private javax.swing.JLabel vente;
    // End of variables declaration//GEN-END:variables
}