/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intreprisemanagementsystem;

import static intreprisemanagementsystem.journal_caisse.buss;
import static intreprisemanagementsystem.journal_caisse.creditbudget;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Dosh
 */
public class search_caisse extends javax.swing.JFrame {
Connection con=null;
 Connection cononline=null;
PreparedStatement pst=null;
ResultSet rs= null;
 DefaultTableModel mode;
  int x,y;
    public search_caisse() {
        initComponents();
          con=JavaDbConnect.dbConnect();
           setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("icons8_Raspberry_Pi_48px_1.png")));
        // attLIST_from_EMPLOYEView_used();
       
    }
public void calltable(){
 
                String DEVICEs = null; 
                SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String date1 = dateFormats.format(jDateChooser1.getDate()); 
         String date2 = dateFormats.format(jDateChooser2.getDate());
         
          NumberFormat nf = new DecimalFormat("#,###.##");
                Double debit = null,credit = null,sold;

                  try{
            String sqls="select sum(COUTS) from projet WHERE PROJET_NUM='"+buss.getSelectedItem()+"'";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
            while(rs.next()){
                Double add1 = rs.getDouble("sum(COUTS)");
                String fn = nf.format(add1 );

              journal_caisse.budget.setText(fn);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
      try{
            String sqls="select SUM(DEBIT) from OHADA_TRANS  WHERE BUSS='"+buss.getSelectedItem()+"' AND SUBSTR=57 AND LB='' and journal='OK' and DATES BETWEEN '"+date1+"' and '"+date2+"'";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
            while(rs.next()){
                 debit=rs.getDouble("SUM(DEBIT)");
                 
                  String fnnn = nf.format(debit);
journal_caisse.debitbudget.setText(fnnn);


            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
         try{
            String sqls="select SUM(CREDIT)  from OHADA_TRANS WHERE BUSS='"+buss.getSelectedItem()+"' AND LB<> '' AND SUBSTR=57 and DATES BETWEEN '"+date1+"' and '"+date2+"'";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
            while(rs.next()){
               credit =rs.getDouble("SUM(CREDIT)");
                
                String fnn = nf.format(credit);
                 
journal_caisse.reste.setText(fnn);

            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
         sold=debit-credit;
          String fnn = nf.format(sold);
          creditbudget.setText(fnn);
         
                 try{
            String sql="select * from monais WHERE caisse='"+journal_caisse.jComboBox1.getSelectedItem()+"'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            if(rs.next()){
            DEVICEs=rs.getString("DEVICE");      
            }
           
       if(DEVICEs.equals("USD")){
            
                try{
        String tmp=journal_caisse.buss.getSelectedItem().toString();
    String sqlS="select `NUM`, `CODE1` as 'CODE',`LIBELLE` AS 'DESCRIPTION OF TRANSACTION',CREDIT AS DEBIT,DEBIT AS CREDIT, `DATES` from OHADA_TRANS where BUSS = '"+tmp+"' AND CODE='"+journal_caisse.jComboBox1.getSelectedItem()+"' AND DEVICE='"+journal_caisse.jComboBox2.getSelectedItem()+"' and DATES between '"+date1+"' and '"+date2+"' order by DATES,NUM";
    //`LIBELLE`, `ENTRE`, `SORTIE`, `DATE`, `BUSS`, `USED`, `NUM`
    pst = con.prepareStatement(sqlS);
      rs= pst.executeQuery();
     
       journal_caisse.jTable1.setModel(DbUtils.resultSetToTableModel(rs));
    
             }catch(Exception ex){
                 JOptionPane.showMessageDialog(null, ex);
             }
                 DefaultTableCellRenderer centre =new DefaultTableCellRenderer();
        centre.setHorizontalAlignment(JLabel.CENTER);
         TableColumn col0=journal_caisse.jTable1.getColumnModel().getColumn(0);
        TableColumn col1=journal_caisse.jTable1.getColumnModel().getColumn(1);
          journal_caisse.jTable1.getColumnModel().getColumn(1).setCellRenderer(centre);
        TableColumn col2=journal_caisse.jTable1.getColumnModel().getColumn(2);
        TableColumn col3=journal_caisse.jTable1.getColumnModel().getColumn(3);
          //centre.setHorizontalAlignment(JLabel.CENTER);
            journal_caisse.jTable1.getColumnModel().getColumn(3).setCellRenderer(centre);
        TableColumn col4=journal_caisse.jTable1.getColumnModel().getColumn(4);
         // centre.setHorizontalAlignment(JLabel.CENTER);
         
            journal_caisse.jTable1.getColumnModel().getColumn(4).setCellRenderer(centre);
            //jTable1.getColumnModel().getColumn(4).;
           TableColumn col5=journal_caisse.jTable1.getColumnModel().getColumn(5);
           // TableColumn col6=jTable1.getColumnModel().getColumn(6);
      
       
       col0.setPreferredWidth(60);
       col1.setPreferredWidth(20);
       col2.setPreferredWidth(370);
       col3.setPreferredWidth(20);
       col4.setPreferredWidth(20);
       col5.setPreferredWidth(20);
            
            }else{
           
                 try{
            // `COMPTE_M`, `COMPTE`, `CODE_M`, `CODE`, `CLASSE`, `SUBSTR`, `DEBIT`, `CREDIT`, `NUM`, `DATES`, `BUSS`, `LIBELLE`, `JOURNAL`, `CODE1`, `NUM_FACTURE`, `DEBIT_CDF`, `CREDIT_CDF`    
             String tmp=journal_caisse.buss.getSelectedItem().toString();
    String sqls="select `NUM`, `CODE1` as 'CODE',`LIBELLE` AS 'DESCRIPTION OF TRANSACTION',CREDIT_CDF AS DEBIT,DEBIT_CDF AS CREDIT, `DATES` from OHADA_TRANS where BUSS = '"+tmp+"' AND CODE='"+journal_caisse.jComboBox1.getSelectedItem()+"' AND DEVICE='"+journal_caisse.jComboBox2.getSelectedItem()+"' and DATES between '"+date1+"' and '"+date2+"' order by DATES,NUM";
    //`LIBELLE`, `ENTRE`, `SORTIE`, `DATE`, `BUSS`, `USED`, `NUM`
    pst = con.prepareStatement(sqls);
      rs= pst.executeQuery();
     
       journal_caisse.jTable1.setModel(DbUtils.resultSetToTableModel(rs));
    
             }catch(Exception ex){
                 JOptionPane.showMessageDialog(null, ex);
             }
                 DefaultTableCellRenderer centre =new DefaultTableCellRenderer();
        centre.setHorizontalAlignment(JLabel.CENTER);
         TableColumn col0=journal_caisse.jTable1.getColumnModel().getColumn(0);
        TableColumn col1=journal_caisse.jTable1.getColumnModel().getColumn(1);
          journal_caisse.jTable1.getColumnModel().getColumn(1).setCellRenderer(centre);
        TableColumn col2=journal_caisse.jTable1.getColumnModel().getColumn(2);
        TableColumn col3=journal_caisse.jTable1.getColumnModel().getColumn(3);
          //centre.setHorizontalAlignment(JLabel.CENTER);
            journal_caisse.jTable1.getColumnModel().getColumn(3).setCellRenderer(centre);
        TableColumn col4=journal_caisse.jTable1.getColumnModel().getColumn(4);
         // centre.setHorizontalAlignment(JLabel.CENTER);
            journal_caisse.jTable1.getColumnModel().getColumn(4).setCellRenderer(centre);
           TableColumn col5=journal_caisse.jTable1.getColumnModel().getColumn(5);
           // TableColumn col6=jTable1.getColumnModel().getColumn(6);
      
       
       col0.setPreferredWidth(60);
       col1.setPreferredWidth(20);
       col2.setPreferredWidth(370);
       col3.setPreferredWidth(20);
       col4.setPreferredWidth(20);
       col5.setPreferredWidth(20);    
            }
            
            
            }catch(Exception ex){
                 JOptionPane.showMessageDialog(null, ex);

            }
  }
public void calltables(){
  // journal.jTable1.setDefaultRenderer(Object.class,new PINTAR_TABELA_journal()); 
         
         String tmp = null;
          String DEVICE = null; 
         if(jComboBox1.getSelectedItem().equals("Libelle")){
         tmp="LIBELLE";
         }else if(jComboBox1.getSelectedItem().equals("LB")){
          tmp="LB";   
         }else if(jComboBox1.getSelectedItem().equals("CODE")){
          tmp="CODE1";   
         }else if(jComboBox1.getSelectedItem().equals("NUM")){
          tmp="NUM";   
         }else if(jComboBox1.getSelectedItem().equals("Debit")){
          tmp="Credit";   
         }else if(jComboBox1.getSelectedItem().equals("Credit")){
          tmp="Debit";   
         }
         
   
 try{
            String sql="select * from monais WHERE caisse='"+journal_caisse.jComboBox1.getSelectedItem()+"'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            if(rs.next()){
            DEVICE=rs.getString("DEVICE");      
            }
            
       if(DEVICE.equals("USD")){
                try{
            // `COMPTE_M`, `COMPTE`, `CODE_M`, `CODE`, `CLASSE`, `SUBSTR`, `DEBIT`, `CREDIT`, `NUM`, `DATES`, `BUSS`, `LIBELLE`, `JOURNAL`, `CODE1`, `NUM_FACTURE`, `DEBIT_CDF`, `CREDIT_CDF`    
             String tmps=journal_caisse.buss.getSelectedItem().toString();
    String sqlS="select `NUM`, `CODE1` as 'CODE',`LIBELLE` AS 'DESCRIPTION OF TRANSACTION',CREDIT AS DEBIT,DEBIT AS CREDIT, `DATES` from OHADA_TRANS where BUSS = '"+tmps+"' AND "+tmp+" like'"+rech.getText()+"%' AND DEVICE='"+journal_caisse.jComboBox2.getSelectedItem()+"' and SUBSTR=57 order by DATES,NUM";
    //`LIBELLE`, `ENTRE`, `SORTIE`, `DATE`, `BUSS`, `USED`, `NUM`
    pst = con.prepareStatement(sqlS);
      rs= pst.executeQuery();
     
       journal_caisse.jTable1.setModel(DbUtils.resultSetToTableModel(rs));
    
             }catch(Exception ex){
                 JOptionPane.showMessageDialog(null, ex);
             }
                 DefaultTableCellRenderer centre =new DefaultTableCellRenderer();
        centre.setHorizontalAlignment(JLabel.CENTER);
         TableColumn col0=journal_caisse.jTable1.getColumnModel().getColumn(0);
        TableColumn col1=journal_caisse.jTable1.getColumnModel().getColumn(1);
          journal_caisse.jTable1.getColumnModel().getColumn(1).setCellRenderer(centre);
        TableColumn col2=journal_caisse.jTable1.getColumnModel().getColumn(2);
        TableColumn col3=journal_caisse.jTable1.getColumnModel().getColumn(3);
          //centre.setHorizontalAlignment(JLabel.CENTER);
            journal_caisse.jTable1.getColumnModel().getColumn(3).setCellRenderer(centre);
        TableColumn col4=journal_caisse.jTable1.getColumnModel().getColumn(4);
         // centre.setHorizontalAlignment(JLabel.CENTER);
         
            journal_caisse.jTable1.getColumnModel().getColumn(4).setCellRenderer(centre);
            //jTable1.getColumnModel().getColumn(4).;
           TableColumn col5=journal_caisse.jTable1.getColumnModel().getColumn(5);
           // TableColumn col6=jTable1.getColumnModel().getColumn(6);
      
       
       col0.setPreferredWidth(60);
       col1.setPreferredWidth(20);
       col2.setPreferredWidth(370);
       col3.setPreferredWidth(20);
       col4.setPreferredWidth(20);
       col5.setPreferredWidth(20);
            
            }else{
                 try{
            // `COMPTE_M`, `COMPTE`, `CODE_M`, `CODE`, `CLASSE`, `SUBSTR`, `DEBIT`, `CREDIT`, `NUM`, `DATES`, `BUSS`, `LIBELLE`, `JOURNAL`, `CODE1`, `NUM_FACTURE`, `DEBIT_CDF`, `CREDIT_CDF`    
             String tmps=journal_caisse.buss.getSelectedItem().toString();
   String sqlS="select `NUM`, `CODE1` as 'CODE',`LIBELLE` AS 'DESCRIPTION OF TRANSACTION',CREDIT AS DEBIT,DEBIT AS CREDIT, `DATES` from OHADA_TRANS where BUSS = '"+tmps+"' AND "+tmp+" like'"+rech.getText()+"%' AND DEVICE='"+journal_caisse.jComboBox2.getSelectedItem()+"'  and SUBSTR=57 order by DATES,NUM";
    //`LIBELLE`, `ENTRE`, `SORTIE`, `DATE`, `BUSS`, `USED`, `NUM`
    pst = con.prepareStatement(sqlS);
      rs= pst.executeQuery();
     
       journal_caisse.jTable1.setModel(DbUtils.resultSetToTableModel(rs));
    
             }catch(Exception ex){
                 JOptionPane.showMessageDialog(null, ex);
             }
                 DefaultTableCellRenderer centre =new DefaultTableCellRenderer();
        centre.setHorizontalAlignment(JLabel.CENTER);
         TableColumn col0=journal_caisse.jTable1.getColumnModel().getColumn(0);
        TableColumn col1=journal_caisse.jTable1.getColumnModel().getColumn(1);
          journal_caisse.jTable1.getColumnModel().getColumn(1).setCellRenderer(centre);
        TableColumn col2=journal_caisse.jTable1.getColumnModel().getColumn(2);
        TableColumn col3=journal_caisse.jTable1.getColumnModel().getColumn(3);
          //centre.setHorizontalAlignment(JLabel.CENTER);
            journal_caisse.jTable1.getColumnModel().getColumn(3).setCellRenderer(centre);
        TableColumn col4=journal_caisse.jTable1.getColumnModel().getColumn(4);
         // centre.setHorizontalAlignment(JLabel.CENTER);
            journal_caisse.jTable1.getColumnModel().getColumn(4).setCellRenderer(centre);
           TableColumn col5=journal_caisse.jTable1.getColumnModel().getColumn(5);
           // TableColumn col6=jTable1.getColumnModel().getColumn(6);
      
       
       col0.setPreferredWidth(60);
       col1.setPreferredWidth(20);
       col2.setPreferredWidth(370);
       col3.setPreferredWidth(20);
       col4.setPreferredWidth(20);
       col5.setPreferredWidth(20);    
            }
            
            
            }catch(Exception ex){
                 JOptionPane.showMessageDialog(null, ex);

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

        jPanel1 = new javax.swing.JPanel();
        jComboBox1 = new javax.swing.JComboBox<>();
        rech = new javax.swing.JTextField();
        jDateChooser1 = new com.alee.extended.date.WebDateField();
        jDateChooser2 = new com.alee.extended.date.WebDateField();
        jButton1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAlwaysOnTop(true);
        setUndecorated(true);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jComboBox1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Entre Deux Date", "Libelle", "LB", "CODE", "NUM", "Debit", "Credit" }));

        rech.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        rech.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                rechKeyReleased(evt);
            }
        });

        jDateChooser1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jDateChooser1.setText("Date1");
        jDateChooser1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jDateChooser1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jDateChooser1ActionPerformed(evt);
            }
        });

        jDateChooser2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jDateChooser2.setText("Date2");
        jDateChooser2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jDateChooser2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jDateChooser2ActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton1.setText("OK");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(102, 102, 102));
        jPanel2.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jPanel2MouseDragged(evt);
            }
        });
        jPanel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel2MousePressed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Search");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("X");
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rech)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rech, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jDateChooser1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jDateChooser1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jDateChooser1ActionPerformed

    private void jDateChooser2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jDateChooser2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jDateChooser2ActionPerformed

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
calltable();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jPanel2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MousePressed
   x= evt.getX();
        y= evt.getY();//         // TODO add your handling code here:
    }//GEN-LAST:event_jPanel2MousePressed

    private void jPanel2MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseDragged
int xx= evt.getXOnScreen();
        int yy= evt.getYOnScreen();
        this.setLocation(xx-x,yy-y);        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel2MouseDragged

    private void rechKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_rechKeyReleased
calltables();        // TODO add your handling code here:
    }//GEN-LAST:event_rechKeyReleased

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
            java.util.logging.Logger.getLogger(search_caisse.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(search_caisse.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(search_caisse.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(search_caisse.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new search_caisse().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox1;
    private com.alee.extended.date.WebDateField jDateChooser1;
    private com.alee.extended.date.WebDateField jDateChooser2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField rech;
    // End of variables declaration//GEN-END:variables
}
