/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intreprisemanagementsystem;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Administrator
 */
public class FINANCE_REGISTRAT extends javax.swing.JInternalFrame {

   Connection con=null;
PreparedStatement pst=null;
ResultSet rs= null;
String etrolls;
DefaultTableModel mode;
    public FINANCE_REGISTRAT() {
        initComponents();
         con=JavaDbConnect.dbConnect();
       
//       this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
//        BasicInternalFrameUI bui= (BasicInternalFrameUI) this.getUI();
//       bui.setNorthPane(null);
       search2BUSs();
    }
    
       public void search2BUSs()
             {
             //COMPTE_M`, `COMPTE`, `CODE_M`, `CODE`, `CLASSE`, `SUBSTR`, `DEBIT`, `CREDIT`, `NUM`, `DATES`, `BUSS`, `LIBELLE`, `JOURNAL`, `CODE1` 
                 try{
               //   String st=projetid.getText().trim();
       //      String tmp=buss.getSelectedItem().toString();
    String sql="select DATES,NUM,BUSS from ohada_trans GROUP BY NUM";
    //`LIBELLE`, `ENTRE`, `SORTIE`, `DATE`, `BUSS`, `USED`, `NUM`
    pst = con.prepareStatement(sql);
      rs= pst.executeQuery();
     
       jTable1.setModel(DbUtils.resultSetToTableModel(rs));
    
             }catch(Exception ex){
                 JOptionPane.showMessageDialog(null, ex);
             }
    /*             DefaultTableCellRenderer centre =new DefaultTableCellRenderer();
        TableColumn col0=jTable1.getColumnModel().getColumn(0);
        TableColumn col1=jTable1.getColumnModel().getColumn(1);
        TableColumn col2=jTable1.getColumnModel().getColumn(2);
        TableColumn col3=jTable1.getColumnModel().getColumn(3);
        TableColumn col4=jTable1.getColumnModel().getColumn(4);
        TableColumn col5=jTable1.getColumnModel().getColumn(5);
        TableColumn col6=jTable1.getColumnModel().getColumn(6);
        TableColumn col7=jTable1.getColumnModel().getColumn(7);
        TableColumn col8=jTable1.getColumnModel().getColumn(8);
        TableColumn col9=jTable1.getColumnModel().getColumn(9);
        TableColumn col10=jTable1.getColumnModel().getColumn(10);
        TableColumn col11=jTable1.getColumnModel().getColumn(11);
         //TableColumn col12=jTable1.getColumnModel().getColumn(12);
        //TableColumn col12=jTable1.getColumnModel().getColumn(11);
       
      
       
       col0.setPreferredWidth(5);
       col1.setPreferredWidth(100);
       col2.setPreferredWidth(80);
       col3.setPreferredWidth(200);
       col4.setPreferredWidth(50);
       col5.setPreferredWidth(50);
       col6.setPreferredWidth(50);
       col7.setPreferredWidth(50);
       col8.setPreferredWidth(50);
       col9.setPreferredWidth(50);
       col10.setPreferredWidth(50);
       col11.setPreferredWidth(50);
      // col12.setPreferredWidth(100);
  
         */    
             } 
       
       
        public void search2BUSsearch()
             {
             //COMPTE_M`, `COMPTE`, `CODE_M`, `CODE`, `CLASSE`, `SUBSTR`, `DEBIT`, `CREDIT`, `NUM`, `DATES`, `BUSS`, `LIBELLE`, `JOURNAL`, `CODE1` 
                 try{
               //   String st=projetid.getText().trim();
       //      String tmp=buss.getSelectedItem().toString();
    String sql="select DATES,NUM,BUSS from ohada_trans where NUM LIKE '"+num.getText()+"%'GROUP BY NUM";
    //`LIBELLE`, `ENTRE`, `SORTIE`, `DATE`, `BUSS`, `USED`, `NUM`
    pst = con.prepareStatement(sql);
      rs= pst.executeQuery();
     
       jTable1.setModel(DbUtils.resultSetToTableModel(rs));
    
             }catch(Exception ex){
                 JOptionPane.showMessageDialog(null, ex);
             }
    /*             DefaultTableCellRenderer centre =new DefaultTableCellRenderer();
        TableColumn col0=jTable1.getColumnModel().getColumn(0);
        TableColumn col1=jTable1.getColumnModel().getColumn(1);
        TableColumn col2=jTable1.getColumnModel().getColumn(2);
        TableColumn col3=jTable1.getColumnModel().getColumn(3);
        TableColumn col4=jTable1.getColumnModel().getColumn(4);
        TableColumn col5=jTable1.getColumnModel().getColumn(5);
        TableColumn col6=jTable1.getColumnModel().getColumn(6);
        TableColumn col7=jTable1.getColumnModel().getColumn(7);
        TableColumn col8=jTable1.getColumnModel().getColumn(8);
        TableColumn col9=jTable1.getColumnModel().getColumn(9);
        TableColumn col10=jTable1.getColumnModel().getColumn(10);
        TableColumn col11=jTable1.getColumnModel().getColumn(11);
         //TableColumn col12=jTable1.getColumnModel().getColumn(12);
        //TableColumn col12=jTable1.getColumnModel().getColumn(11);
       
      
       
       col0.setPreferredWidth(5);
       col1.setPreferredWidth(100);
       col2.setPreferredWidth(80);
       col3.setPreferredWidth(200);
       col4.setPreferredWidth(50);
       col5.setPreferredWidth(50);
       col6.setPreferredWidth(50);
       col7.setPreferredWidth(50);
       col8.setPreferredWidth(50);
       col9.setPreferredWidth(50);
       col10.setPreferredWidth(50);
       col11.setPreferredWidth(50);
      // col12.setPreferredWidth(100);
  
         */    
             } 
       
        public void save_Contract(){
          int indexs[]=jTable1.getSelectedRows();
        String LB = null,PRO = null,num_fact=null;
        Double CRE = null,DE;
        for(int i=0; i < indexs.length;i++){
             String nums = (jTable1.getModel().getValueAt(indexs[i],1). toString());
              String buss = (jTable1.getModel().getValueAt(indexs[i],2). toString());
            
         try{
            String sqls="select CODE,DEBIT,CREDIT,PROJET from budget_trans where NUM='"+nums+"' and projet='"+buss+"'";
            
           
             pst=con.prepareStatement(sqls);
          //  pst.setString(1, A);
            rs=pst.executeQuery();
            if(rs.next()){
             LB = rs.getString("CODE");
             CRE = rs.getDouble("CREDIT");
             DE = rs.getDouble("DEBIT");
             PRO = rs.getString("PROJET");
            }
            
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
         
          try{
            String sqls="select NUM_FACTURE from ohada_trans where NUM='"+nums+"' and buss='"+buss+"'";
            
           
             pst=con.prepareStatement(sqls);
          //  pst.setString(1, A);
            rs=pst.executeQuery();
            if(rs.next()){
            num_fact = rs.getString("NUM_FACTURE");
            
            }
            
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
        
        
        
         try{
        String sql = "DELETE FROM ohada_trans WHERE NUM=? and buss='"+buss+"'";
        
         pst = con.prepareStatement(sql);
         pst.setString(1,nums);
         pst.executeUpdate();

     }catch(SQLException | HeadlessException ex ){
         JOptionPane.showMessageDialog(null,ex);
     } 
          try{
        String sql = "DELETE FROM caisses WHERE NUM=? and buss='"+buss+"'";
        
         pst = con.prepareStatement(sql);
         pst.setString(1,nums);
         pst.executeUpdate();

     }catch(SQLException | HeadlessException ex ){
         JOptionPane.showMessageDialog(null,ex);
     } 
            try{
        String sql = "DELETE FROM budget_trans WHERE NUM=? and projet='"+buss+"'";
        
         pst = con.prepareStatement(sql);
         pst.setString(1,nums);
         pst.executeUpdate();

     }catch(SQLException | HeadlessException ex ){
         JOptionPane.showMessageDialog(null,ex);
     } 
            
            
              try {
                Double CREDIT = null,SOLD = null;
         try{              //call from journala de banque  
            String sql="select SUM(DEBIT),SUM(CREDIT) from  budget_show where CODE= '"+LB+"' and projet='"+PRO+"'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
               Double a=rs.getDouble("SUM(DEBIT)");
               Double b=rs.getDouble("SUM(CREDIT)");
               // Double c=Double.parseDouble(amount.getText());
                   CREDIT=b-CRE;
                 SOLD=a-CREDIT;
            //jTextField4.setText(d);
            }
            }catch(Exception ex){ JOptionPane.showMessageDialog(null, ex);} 
        PreparedStatement pst = con.prepareStatement("UPDATE `etat_de_besoin` SET `ECRITURE`='' WHERE NUM_TRANS='"+nums+"' and buss='"+buss+"'");
      pst.executeUpdate();
      
      
      
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());}
              
                  try{
         PreparedStatement pst = con.prepareStatement("UPDATE `ohada_trans` SET `Pay`='No' WHERE NUM_FACTURE='"+num_fact+"' and buss='"+buss+"'");
      pst.executeUpdate();

     }catch(SQLException | HeadlessException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }
           
            
        
        }
        JOptionPane.showMessageDialog(null,"Data Saved");
         search2BUSs();
          LB = null;
          PRO = null;
        CRE = null;
        DE=null;
     }
       
   public void etdeletee()
    {
        String LB = null,PRO = null,num_fact=null;
        Double CRE = null,DE;
         try{
            String sqls="select CODE,DEBIT,CREDIT,PROJET from budget_trans where NUM='"+num.getText()+"'";
            
           
             pst=con.prepareStatement(sqls);
          //  pst.setString(1, A);
            rs=pst.executeQuery();
            if(rs.next()){
             LB = rs.getString("CODE");
             CRE = rs.getDouble("CREDIT");
             DE = rs.getDouble("DEBIT");
             PRO = rs.getString("PROJET");
            }
            
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
         
          try{
            String sqls="select NUM_FACTURE from ohada_trans where NUM='"+num.getText()+"'";
            
           
             pst=con.prepareStatement(sqls);
          //  pst.setString(1, A);
            rs=pst.executeQuery();
            if(rs.next()){
            num_fact = rs.getString("NUM_FACTURE");
            
            }
            
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
        
        
        
         try{
        String sql = "DELETE FROM ohada_trans WHERE NUM=?";
        
         pst = con.prepareStatement(sql);
         pst.setString(1,num.getText());
         pst.executeUpdate();

     }catch(SQLException | HeadlessException ex ){
         JOptionPane.showMessageDialog(null,ex);
     } 
          try{
        String sql = "DELETE FROM caisses WHERE NUM=?";
        
         pst = con.prepareStatement(sql);
         pst.setString(1,num.getText());
         pst.executeUpdate();

     }catch(SQLException | HeadlessException ex ){
         JOptionPane.showMessageDialog(null,ex);
     } 
            try{
        String sql = "DELETE FROM budget_trans WHERE NUM=?";
        
         pst = con.prepareStatement(sql);
         pst.setString(1,num.getText());
         pst.executeUpdate();

     }catch(SQLException | HeadlessException ex ){
         JOptionPane.showMessageDialog(null,ex);
     } 
            
            
              try {
                Double CREDIT = null,SOLD = null;
         try{              //call from journala de banque  
            String sql="select SUM(DEBIT),SUM(CREDIT) from  budget_show where CODE= '"+LB+"' and projet='"+PRO+"'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
               Double a=rs.getDouble("SUM(DEBIT)");
               Double b=rs.getDouble("SUM(CREDIT)");
               // Double c=Double.parseDouble(amount.getText());
                   CREDIT=b-CRE;
                 SOLD=a-CREDIT;
            //jTextField4.setText(d);
            }
            }catch(Exception ex){ JOptionPane.showMessageDialog(null, ex);} 
        PreparedStatement pst = con.prepareStatement("UPDATE `etat_de_besoin` SET `ECRITURE`='' WHERE NUM_TRANS='"+num.getText()+"'");
      pst.executeUpdate();
      
      
      
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());}
              
                  try{
         PreparedStatement pst = con.prepareStatement("UPDATE `ohada_trans` SET `Pay`='No' WHERE NUM_FACTURE='"+num_fact+"'");
      pst.executeUpdate();

     }catch(SQLException | HeadlessException ex ){
         JOptionPane.showMessageDialog(null,ex);
//         JOptionPane optionPane = new JOptionPane();
//JDialog dialog = optionPane.createDialog("Title");
//dialog.setAlwaysOnTop(alwaysOnTop);
//dialog.setVisible(true);
     }
           
            
          search2BUSs();
          LB = null;
          PRO = null;
        CRE = null;
        DE=null;
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
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        num = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();

        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_Accounting_16px.png"))); // NOI18N

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        num.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        num.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                numKeyReleased(evt);
            }
        });

        jButton1.setText("Delete");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 396, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(num)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(num, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 322, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(0, 153, 0));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 27, Short.MAX_VALUE)
        );

        jLabel1.setBackground(new java.awt.Color(255, 0, 0));
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("X");
        jLabel1.setOpaque(true);
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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

        setBounds(60, 30, 458, 496);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
//etdeletee();
save_Contract();
num.setText("");
     //JOptionPane.showMessageDialog(null,"OK");
// TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void numKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_numKeyReleased
search2BUSsearch();        // TODO add your handling code here:
    }//GEN-LAST:event_numKeyReleased

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
 int row= jTable1.getSelectedRow();
         // String rows =jTable1.getName()
        String  COMPTES = (jTable1.getModel().getValueAt(row,1). toString()); 
        num.setText(COMPTES);// TODO add your handling code here:
    }//GEN-LAST:event_jTable1MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField num;
    // End of variables declaration//GEN-END:variables
}
