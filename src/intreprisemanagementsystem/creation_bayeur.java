/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intreprisemanagementsystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author DOSHE
 */
public class creation_bayeur extends javax.swing.JPanel {

Connection con=null;
PreparedStatement pst=null;
ResultSet rs= null;
String etrolls;
    public creation_bayeur() {
        initComponents();
         con=JavaDbConnect.dbConnect();
         show_photo_from_db();
    }
    //INSERT INTO `projet_bayeur`(`PROJET`, `BAYEUR`, `SUPERVISEUR`)
 public void save(){
      try{
          String sql = "SELECT * FROM  projet_bayeur WHERE PROJET_NUM = '"+new_project.projet.getText()+"'";
          pst = con.prepareStatement(sql);
          rs=pst.executeQuery();
          if(rs.next()){
        try {
           // etroll();
        PreparedStatement pst = con.prepareStatement("UPDATE `projet_bayeur` SET `PROJET_NUM`=?,`BAYEUR`=?,`SUPERVISEUR`=? WHERE PROJET_NUM = '"+new_project.projet.getText()+"'");
        
        pst.setString(1, projet.getText());
         pst.setString(2,sponsor.getText());
        pst.setString(3, superviseur.getText());
      
    
         
          pst.executeUpdate();
        
             //  JOptionPane.showMessageDialog(null,"Saved");
        } catch (Exception ex) { JOptionPane.showMessageDialog(null, ex.getMessage());}
            try {
           // etroll();
        PreparedStatement pst = con.prepareStatement("UPDATE `projet` SET `BANQUE`=?, `CODE`=?,`TVA`=?  WHERE PROJET_NUM = '"+new_project.projet.getText()+"'");
     
        pst.setString(1, banque.getText());
        pst.setString(2, compte.getText());
        
        pst.setString(3, tva.getText());
      
    
         
          pst.executeUpdate();
        
               JOptionPane.showMessageDialog(null,"Saved");
        } catch (Exception ex) { JOptionPane.showMessageDialog(null, ex.getMessage());}
                 }else{
     try {
           // etroll();
        PreparedStatement pst = con.prepareStatement("INSERT INTO `projet_bayeur`(`PROJET_NUM`, `BAYEUR`, `SUPERVISEUR`)"
        +"value(?,?,?)");
        
        pst.setString(1, projet.getText());
        pst.setString(2,sponsor.getText());
        pst.setString(3, superviseur.getText());
      
    
         
          pst.executeUpdate();
        
               JOptionPane.showMessageDialog(null,"Saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }      
          
          }
         
    }catch(Exception ex ){
         JOptionPane.showMessageDialog(null,ex);
     }
     projet.setText("");
    // sponsor.setText("");
     superviseur.setText("");
 }
  public void show_photo_from_db()
               
   {
            try{
          String sql = "SELECT * FROM  projet_bayeur WHERE PROJET_NUM = '"+new_project.projet.getText()+"'";
          pst = con.prepareStatement(sql);
          rs=pst.executeQuery();
          if(rs.next()){
        String Projet_num =rs.getString("PROJET_NUM");
         
          projet.setText(Projet_num);
      
        String Projet_loc =rs.getString("BAYEUR");
             sponsor.setText(Projet_loc);
          
                 
        String Date_in=rs.getString("SUPERVISEUR");
               superviseur.setText(Date_in);
      
                 
       
                 }
         
    }catch(Exception ex ){
         JOptionPane.showMessageDialog(null,ex);
     }
           //`BANQUE`=?, `CODE`=?,`TVA`=?  
           try{
          String sql = "SELECT * FROM  projet WHERE PROJET_NUM = '"+new_project.projet.getText()+"'";
          pst = con.prepareStatement(sql);
          rs=pst.executeQuery();
          if(rs.next()){
        String Projet_num =rs.getString("BANQUE");
         banque.setText(Projet_num);
        String Projet_loc =rs.getString("CODE");
        compte.setText(Projet_loc);         
        String Date_in=rs.getString("TVA");
        tva.setText(Date_in);
      
                 
       
                 }
         
    }catch(Exception ex ){
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

        jButton3 = new javax.swing.JButton();
        superviseur = new Palette.TextField();
        banque = new Palette.TextField();
        tva = new Palette.TextField();
        compte = new Palette.TextField();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButton3.setText("Ok");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        projet.setLabelText("Numéro projet");

        sponsor.setLabelText("Bailleur");

        superviseur.setLabelText("Superviseur");

        banque.setLabelText("Banque");

        tva.setText("0.00");
        tva.setLabelText("_%_Tva");

        compte.setLabelText("Numéro de compte");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(banque, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(sponsor, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 423, Short.MAX_VALUE)
                        .addComponent(superviseur, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(projet, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(compte, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tva, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(projet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(51, 51, 51))
                            .addComponent(sponsor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(48, 48, 48))
                    .addComponent(superviseur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(banque, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(compte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
if(projet.getText().equals("")||sponsor.getText().equals("")||superviseur.getText().equals("")){
JOptionPane.showMessageDialog(null,"Wrong Data Item Exists","Error",JOptionPane.ERROR_MESSAGE);
}else{
        save(); 
}// TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Palette.TextField banque;
    private Palette.TextField compte;
    private javax.swing.JButton jButton3;
    public static final Palette.TextField projet = new Palette.TextField();
    public static final Palette.TextField sponsor = new Palette.TextField();
    private Palette.TextField superviseur;
    private Palette.TextField tva;
    // End of variables declaration//GEN-END:variables
}