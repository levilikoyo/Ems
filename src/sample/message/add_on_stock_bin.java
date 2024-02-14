/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package sample.message;

import canalplus.cell.TableActionCellEditor;
import canalplus.cell.TableActionCellRender;
import canalplus.cell.TableActionEvent;
import intreprisemanagementsystem.JavaDbConnect;
import intreprisemanagementsystem.Materiaux_in;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import journals.store_setting;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Doshe PC
 */
public class add_on_stock_bin extends javax.swing.JFrame {

   Connection con=null;
PreparedStatement pst=null;
ResultSet rs= null;
DefaultTableModel mode; 
    public add_on_stock_bin() {
        initComponents();
        con=JavaDbConnect.dbConnect();
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("icons8_Raspberry_Pi_48px_1.png")));
          TableActionEvent event = new TableActionEvent(){
        public void onEdit(int row){
           
        }
            @Override
            public void onnew(int row) {
             int rows= jTable1.getSelectedRow();
String    nom = (jTable1.getModel().getValueAt(rows,0). toString());


    con=JavaDbConnect.dbConnect(); 
           try{
      String sql = "UPDATE stock_bin_location set Nom=? WHERE Nom =? and Cat='"+jLabel1.getText()+"'";
        
         pst = con.prepareStatement(sql);
        
         pst.setString(1,nom);
       
         pst.executeUpdate();

     }catch(SQLException | HeadlessException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }
            }

            public void onsave(int row) {
              
       
            }

            @Override
            public void ondelete(int row) {
int rows= jTable1.getSelectedRow();
String    num = (jTable1.getModel().getValueAt(rows,0). toString());
      con=JavaDbConnect.dbConnect();   
        try{
        String sql = "DELETE FROM  stock_bin_location WHERE Nom=? and Cat='"+jLabel1.getText()+"' ";
         
         pst = con.prepareStatement(sql);
         pst.setString(1,num);
        
         pst.executeUpdate();

     }catch(SQLException | HeadlessException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }    
            }
        };
    jTable1.getColumnModel().getColumn(1).setCellRenderer(new TableActionCellRender());
    jTable1.getColumnModel().getColumn(1).setCellEditor(new TableActionCellEditor(event));
        //call_table();
    }
 public void save(){
      if(jLabel1.getText().equals("depot")){
     try{
     String sql="INSERT INTO stock_bin_location (NOM,CAT,DEPOT,RANGES,ETAGERE,LIGNE,BIN,PROFONDEUR)"+
             "VALUES(?,?,?,?,?,?,?,?)";
     pst=con.prepareStatement(sql);
      pst.setString(1, textField1.getText());
      pst.setString(2, jLabel1.getText());
      pst.setString(3, "");
      pst.setString(4, "");
      pst.setString(5, "");
      pst.setString(6, "");
      pst.setString(7, "");
      pst.setString(8, "");
       pst.executeUpdate();
}catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}       
          
 }       if(jLabel1.getText().equals("Category")){
     try{
     String sql="INSERT INTO stock_bin_location (NOM,CAT,DEPOT,RANGES,ETAGERE,LIGNE,BIN,PROFONDEUR)"+
             "VALUES(?,?,?,?,?,?,?,?)";
     pst=con.prepareStatement(sql);
      pst.setString(1, textField1.getText());
      pst.setString(2, jLabel1.getText());
      pst.setString(3, "");
      pst.setString(4, "");
      pst.setString(5, "");
      pst.setString(6, "");
      pst.setString(7, "");
      pst.setString(8, "");
       pst.executeUpdate();
}catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}       
          
 }  if(jLabel1.getText().equals("Archive")){
     try{
     String sql="INSERT INTO stock_bin_location (NOM,CAT,DEPOT,RANGES,ETAGERE,LIGNE,BIN,PROFONDEUR)"+
             "VALUES(?,?,?,?,?,?,?,?)";
     pst=con.prepareStatement(sql);
      pst.setString(1, textField1.getText());
      pst.setString(2, jLabel1.getText());
      pst.setString(3, "");
      pst.setString(4, "");
      pst.setString(5, "");
      pst.setString(6, "");
      pst.setString(7, "");
      pst.setString(8, "");
       pst.executeUpdate();
}catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}       
          
 }else if(jLabel1.getText().equals("ranges")){
  try{
     String sql="INSERT INTO stock_bin_location (NOM,CAT,DEPOT,RANGES,ETAGERE,LIGNE,BIN,PROFONDEUR)"+
             "VALUES(?,?,?,?,?,?,?,?)";
     pst=con.prepareStatement(sql);
      pst.setString(1, textField1.getText());
      pst.setString(2, jLabel1.getText());
      pst.setString(3, store_setting.depot.getSelectedItem().toString());
      pst.setString(4, "");
      pst.setString(5, "");
      pst.setString(6, "");
      pst.setString(7, "");
      pst.setString(8, "");
       pst.executeUpdate();
}catch(Exception ex){JOptionPane.showMessageDialog(null, ex);} 
 }else if(jLabel1.getText().equals("etagere")){
 try{
     String sql="INSERT INTO stock_bin_location (NOM,CAT,DEPOT,RANGES,ETAGERE,LIGNE,BIN,PROFONDEUR)"+
             "VALUES(?,?,?,?,?,?,?,?)";
     pst=con.prepareStatement(sql);
      pst.setString(1, textField1.getText());
      pst.setString(2, jLabel1.getText());
      pst.setString(3, store_setting.depot.getSelectedItem().toString());
      pst.setString(4, store_setting.ranges.getSelectedItem().toString());
      pst.setString(5, "");
      pst.setString(6, "");
      pst.setString(7, "");
      pst.setString(8, "");
       pst.executeUpdate();
}catch(Exception ex){JOptionPane.showMessageDialog(null, ex);} 
     
     }else if(jLabel1.getText().equals("ligne")){
try{
     String sql="INSERT INTO stock_bin_location (NOM,CAT,DEPOT,RANGES,ETAGERE,LIGNE,BIN,PROFONDEUR)"+
             "VALUES(?,?,?,?,?,?,?,?)";
     pst=con.prepareStatement(sql);
      pst.setString(1, textField1.getText());
      pst.setString(2, jLabel1.getText());
      pst.setString(3, store_setting.depot.getSelectedItem().toString());
      pst.setString(4, store_setting.ranges.getSelectedItem().toString());
      pst.setString(5, store_setting.etagere.getSelectedItem().toString());
      pst.setString(6, "");
      pst.setString(7, "");
      pst.setString(8, "");
       pst.executeUpdate();
}catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}
     
     }else if(jLabel1.getText().equals("bin")){
    try{
     String sql="INSERT INTO stock_bin_location (NOM,CAT,DEPOT,RANGES,ETAGERE,LIGNE,BIN,PROFONDEUR)"+
             "VALUES(?,?,?,?,?,?,?,?)";
     pst=con.prepareStatement(sql);
      pst.setString(1, textField1.getText());
      pst.setString(2, jLabel1.getText());
      pst.setString(3, store_setting.depot.getSelectedItem().toString());
      pst.setString(4, store_setting.ranges.getSelectedItem().toString());
      pst.setString(5, store_setting.etagere.getSelectedItem().toString());
      pst.setString(6, store_setting.ligne.getSelectedItem().toString());
      pst.setString(7, "");
      pst.setString(8, "");
       pst.executeUpdate();
}catch(Exception ex){JOptionPane.showMessageDialog(null, ex);} 
     }else if(jLabel1.getText().equals("profondeur")){
     try{
     String sql="INSERT INTO stock_bin_location (NOM,CAT,DEPOT,RANGES,ETAGERE,LIGNE,BIN,PROFONDEUR)"+
             "VALUES(?,?,?,?,?,?,?,?)";
     pst=con.prepareStatement(sql);
      pst.setString(1, textField1.getText());
      pst.setString(2, jLabel1.getText());
      pst.setString(3, store_setting.depot.getSelectedItem().toString());
      pst.setString(4, store_setting.ranges.getSelectedItem().toString());
      pst.setString(5, store_setting.etagere.getSelectedItem().toString());
      pst.setString(6, store_setting.ligne.getSelectedItem().toString());
      pst.setString(7, store_setting.bin.getSelectedItem().toString());
      pst.setString(8, "");
       pst.executeUpdate();
}catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}
     
     }
         call();
         call_table();
             }
 public void call(){
     if(jLabel1.getText().equals("depot")){
      store_setting.depot.removeAllItems();
     store_setting.depot.addItem("- Store/dépôt -");
      try{
          String sql="SELECT * FROM stock_bin_location where cat='depot' order by Nom";
             pst = con.prepareStatement(sql);rs=pst.executeQuery();
            while(rs.next()){
                //String sum=rs.getString("nom");
                String sums=rs.getString("Nom");
             
              store_setting.depot.addItem(sums);
            }
            }catch(Exception ex){JOptionPane.showMessageDialog(null, ex); }
     } if(jLabel1.getText().equals("Archive")){
      store_setting.depot.removeAllItems();
     store_setting.depot.addItem("- Store/Archive -");
      try{
          String sql="SELECT * FROM stock_bin_location where cat='Archive' order by Nom";
             pst = con.prepareStatement(sql);rs=pst.executeQuery();
            while(rs.next()){
                //String sum=rs.getString("nom");
                String sums=rs.getString("Nom");
             
              store_setting.depot.addItem(sums);
            }
            }catch(Exception ex){JOptionPane.showMessageDialog(null, ex); }
     }else if(jLabel1.getText().equals("range")){
       store_setting.ranges.removeAllItems();
     store_setting.ranges.addItem("- Rank/Rangé -");
      try{
          String sql="SELECT * FROM stock_bin_location where cat='ranges' order by Nom";
             pst = con.prepareStatement(sql);rs=pst.executeQuery();
            while(rs.next()){
                //String sum=rs.getString("nom");
                String sums=rs.getString("Nom");
             
              store_setting.ranges.addItem(sums);
            }
            }catch(Exception ex){JOptionPane.showMessageDialog(null, ex); }
     }else if(jLabel1.getText().equals("etagere")){
       store_setting.etagere.removeAllItems();
     store_setting.etagere.addItem("- Shelves/ Etagères -");
      try{
          String sql="SELECT * FROM stock_bin_location where cat='etagere' order by Nom";
             pst = con.prepareStatement(sql);rs=pst.executeQuery();
            while(rs.next()){
                //String sum=rs.getString("nom");
                String sums=rs.getString("Nom");
             
               store_setting.etagere.addItem(sums);
            }
            }catch(Exception ex){JOptionPane.showMessageDialog(null, ex); }
     }else if(jLabel1.getText().equals("ligne")){
    store_setting.ligne.removeAllItems();
    store_setting.ligne.addItem("- Line/ Ligne -");
      try{
          String sql="SELECT * FROM stock_bin_location where cat='ligne' order by Nom";
             pst = con.prepareStatement(sql);rs=pst.executeQuery();
            while(rs.next()){
                //String sum=rs.getString("nom");
                String sums=rs.getString("Nom");
             
               store_setting.ligne.addItem(sums);
            }
            }catch(Exception ex){JOptionPane.showMessageDialog(null, ex); }
     }else if(jLabel1.getText().equals("bin")){
    store_setting.bin.removeAllItems();
    store_setting.bin.addItem("- Bin location -");
    try{
          String sql="SELECT * FROM stock_bin_location where cat='bin' order by Nom";
             pst = con.prepareStatement(sql);rs=pst.executeQuery();
            while(rs.next()){
                //String sum=rs.getString("nom");
                String sums=rs.getString("Nom");
             
               store_setting.bin.addItem(sums);
            }
            }catch(Exception ex){JOptionPane.showMessageDialog(null, ex); }
     }else if(jLabel1.getText().equals("profondeur")){
    store_setting.profondeur.removeAllItems();
    store_setting.profondeur.addItem("- Deep/ Profondeur -");
       
    try{
          String sql="SELECT * FROM stock_bin_location where cat='profondeur' order by Nom";
             pst = con.prepareStatement(sql);rs=pst.executeQuery();
            while(rs.next()){
                //String sum=rs.getString("nom");
                String sums=rs.getString("Nom");
             
               store_setting.profondeur.addItem(sums);
            }
            }catch(Exception ex){JOptionPane.showMessageDialog(null, ex); }
     
     }
 }
 
 public void call_table(){
     
/*if(jLabel1.getText().equals("")){

}else if(){

}
*/
           try{
        PreparedStatement ps = con.prepareStatement("Select * FROM stock_bin_location where cat='"+jLabel1.getText()+"' and depot='"+store_setting.depot.getSelectedItem()+"' order by Nom");
        ResultSet rs=ps.executeQuery();
        DefaultTableModel tm = (DefaultTableModel)jTable1.getModel();
        tm.setRowCount(0);

      while(rs.next()){//``, ``, ``, `                                                                                    `, ``, ``, ``, ``, ``, `DATEFABRI`, ``, `DATE`
            Object o[] = {rs.getString("NOM")};
            tm.addRow(o);

      }

       }catch(Exception ex){JOptionPane.showMessageDialog(null, ex); }
    
   }
 

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        textField1 = new Palette.TextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        textField1.setLabelText("Description");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Nom", "Action"
            }
        ));
        jTable1.setRowHeight(32);
        jScrollPane1.setViewportView(jTable1);

        jLabel1.setText("Archive");

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/cancel.png"))); // NOI18N

        jButton1.setText("OK");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_Refresh_16px.png"))); // NOI18N
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(textField1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 215, Short.MAX_VALUE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
save();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
call();
         call_table();        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel3MouseClicked

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
            java.util.logging.Logger.getLogger(add_on_stock_bin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(add_on_stock_bin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(add_on_stock_bin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(add_on_stock_bin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new add_on_stock_bin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    public static final javax.swing.JLabel jLabel1 = new javax.swing.JLabel();
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    public static final javax.swing.JTable jTable1 = new javax.swing.JTable();
    private Palette.TextField textField1;
    // End of variables declaration//GEN-END:variables
}
