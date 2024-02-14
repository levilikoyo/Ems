/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intreprisemanagementsystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;

/**
 *
 * @author DOSHE
 */
public class project extends javax.swing.JPanel {
Connection con=null;
PreparedStatement pst=null;
ResultSet rs= null;
String etrolls;
    public project() {
        initComponents();
           con=JavaDbConnect.dbConnect();
           show_photo_from_db();
    }
//INSERT INTO `projet`(`PROJET`, `PROJET_NUM`, `PROJET_LOC`, `DATEIN`, `DATEOUT`, `CHANTIER`, `COUTS`, `NUM`)
    
    public void save(){
        
         try{
          String sql = "SELECT * FROM  projet WHERE PROJET_NUM = '"+new_project.projet.getText()+"'";
          pst = con.prepareStatement(sql);
          rs=pst.executeQuery();
          if(rs.next()){
          try {
           // etroll();
        PreparedStatement pst = con.prepareStatement("UPDATE `projet` SET `PROJET`=?,`PROJET_NUM`=?,`PROJET_LOC`=?,`DATEIN`=?,`DATEOUT`=?,`COUTS`=?,`NUM`=?,`SUP`=?,`OURS`=?,`D_W`=?,`D_M`=? where PROJET_NUM = '"+new_project.projet.getText()+"'");
        
        pst.setString(1, projet.getText());
         pst.setString(2,numero.getText());
        pst.setString(3, site.getText());
         pst.setString(4,datein.getText());
          pst.setString(5,dateout.getText());
         pst.setString(6, budjet.getText());
         pst.setString(7, "");
          pst.setString(8,sup.getText());
          pst.setString(9,our.getText());
          pst.setString(10,d_w.getText());
          pst.setString(11,d_m.getText());
    
         
          pst.executeUpdate();
        
              //   JOptionPane.showMessageDialog(null,"CLERCK_IN  SUCCESSFUL BONNE JOURNEE!!!        ");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
      
                 }else{
          
           try {
           // etroll();
        PreparedStatement pst = con.prepareStatement("INSERT INTO `projet`(`PROJET`, `PROJET_NUM`, `PROJET_LOC`, `DATEIN`, `DATEOUT`, `COUTS`, `NUM`, `SUP`,`OURS`,D_W,D_M)"
        +"value(?,?,?,?,?,?,?,?,?,?,?)");
        
        pst.setString(1, projet.getText());
         pst.setString(2,numero.getText());
        pst.setString(3, site.getText());
         pst.setString(4,datein.getText());
          pst.setString(5,dateout.getText());
         pst.setString(6, budjet.getText());
         pst.setString(7, "");
          pst.setString(8,sup.getText());
          pst.setString(9,our.getText());
          pst.setString(10,d_w.getText());
          pst.setString(11,d_m.getText());
    
         
          pst.executeUpdate();
        
              //   JOptionPane.showMessageDialog(null,"CLERCK_IN  SUCCESSFUL BONNE JOURNEE!!!        ");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
          }
         
    }catch(SQLException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }
        
     
      new_project.projet.setText(numero.getText());
    projet.setText("");
    numero.setText("");
    site.setText("");
    datein.setText("");
    dateout.setText("");
    budjet.setText("");
    our.setText("");
    d_w.setText("");
    d_m.setText("");
    
    }
     public void show_photo_from_db()
               
   {
            try{
          String sql = "SELECT * FROM  projet WHERE PROJET_NUM = '"+new_project.projet.getText()+"'";
          pst = con.prepareStatement(sql);
          rs=pst.executeQuery();
          if(rs.next()){
         
        String Projet=rs.getString("PROJET");
                 projet.setText(Projet);
    
        String Projet_num =rs.getString("PROJET_NUM");
         
          numero.setText(Projet_num);
      
        String Projet_loc =rs.getString("PROJET_LOC");
             site.setText(Projet_loc);
          
                 
        String Date_in=rs.getString("DATEIN");
               datein.setText(Date_in);
      
                 
        String Date_out=rs.getString("DATEOUT");
             dateout.setText(Date_out);
       
                 
        String Gouts =rs.getString("COUTS");
             budjet.setText(Gouts);
      
                 
//        String Num=rs.getString("NUM");
//           numero.setText(Num);
        String Sup =rs.getString("SUP");
               sup.setText(Sup);
        String Ours=rs.getString("OURS");
         our.setText(Ours);
    
               
                 
        String Dw=rs.getString("D_W");
         d_w.setText(Dw);
      
        String Dm=rs.getString("D_M");
         d_m.setText(Dm);     
                 }
         
    }catch(SQLException ex ){
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

        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        datein = new com.alee.extended.date.WebDateField();
        dateout = new com.alee.extended.date.WebDateField();
        jButton3 = new javax.swing.JButton();
        textAreaScroll3 = new textarea.TextAreaScroll();
        numero = new Palette.TextField();
        sup = new Palette.TextField();
        our = new Palette.TextField();
        d_w = new Palette.TextField();
        d_m = new Palette.TextField();
        site = new Palette.TextField();
        budjet = new Palette.TextField();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("Date In");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("Date Out");

        datein.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        dateout.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jButton3.setText("Ok");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        textAreaScroll3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        textAreaScroll3.setLabelText("Titre du projet");

        projet.setColumns(20);
        projet.setRows(5);
        textAreaScroll3.setViewportView(projet);

        numero.setLabelText("Numéro du projet");

        sup.setLabelText("Chef de projet");

        our.setLabelText("Heures de service");

        d_w.setLabelText("Nbre. jrs/sem");

        d_m.setLabelText("Nbr. Jrs/Mois");

        site.setLabelText("Site d'intervation");

        budjet.setLabelText("Budget total du projet");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(numero, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(datein, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(dateout, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(6, 6, 6)
                                .addComponent(sup, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(our, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(2, 2, 2)
                                .addComponent(d_w, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(d_m, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(site, javax.swing.GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addComponent(textAreaScroll3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(budjet, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(textAreaScroll3, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(numero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(datein, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dateout, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(sup, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(our, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(d_w, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(d_m, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(site, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(budjet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
if(projet.getText().equals("")||numero.getText().equals("")||datein.getText().equals("")||dateout.getText().equals("")||sup.getText().equals("")||our.getText().equals("")||d_w.getText().equals("")||d_m.getText().equals("")||site.getText().equals("")||budjet.getText().equals("")){
JOptionPane.showMessageDialog(null,"Wrong Data Item Exists","Error",JOptionPane.ERROR_MESSAGE);
}else{
        save();}        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Palette.TextField budjet;
    private Palette.TextField d_m;
    private Palette.TextField d_w;
    private com.alee.extended.date.WebDateField datein;
    private com.alee.extended.date.WebDateField dateout;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private Palette.TextField numero;
    private Palette.TextField our;
    public static final textarea.TextArea projet = new textarea.TextArea();
    private Palette.TextField site;
    private Palette.TextField sup;
    private textarea.TextAreaScroll textAreaScroll3;
    // End of variables declaration//GEN-END:variables
}
