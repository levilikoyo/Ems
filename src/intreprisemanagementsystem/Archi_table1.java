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
import javax.swing.DefaultListModel;
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
public class Archi_table1 extends javax.swing.JPanel {
    Connection con=null;
PreparedStatement pst=null;
ResultSet rs= null;
 DefaultTableModel mode;
 String roll,rolls;
    public Archi_table1() {
        initComponents();
         con=JavaDbConnect.dbConnect();
         calltable();
         jTable1.setDefaultRenderer(Object.class,new PINTAR_ARCHIVE()); 
    }
public void calltable(){
       try{
            // `COMPTE_M`, `COMPTE`, `CODE_M`, `CODE`, `CLASSE`, `SUBSTR`, `DEBIT`, `CREDIT`, `NUM`, `DATES`, `BUSS`, `LIBELLE`, `JOURNAL`, `CODE1`, `NUM_FACTURE`, `DEBIT_CDF`, `CREDIT_CDF`    
             String tmp=Achive_check.buss.getSelectedItem().toString();
    String sqlS="select `NUM`, `CODE1` as 'CODE',`LIBELLE` AS 'DESCRIPTION OF TRANSACTION',CREDIT AS DEBIT,DEBIT AS CREDIT, `DATES`,ARCHIVE from OHADA_TRANS where BUSS = '"+tmp+"' AND CLASSE= 5 ORDER BY DATES";//AND CODE='"+jComboBox1.getSelectedItem()+"' AND DEVICE='"+jComboBox2.getSelectedItem()+"' order by DATES,NUM";
    //`LIBELLE`, `ENTRE`, `SORTIE`, `DATE`, `BUSS`, `USED`, `NUM`
    pst = con.prepareStatement(sqlS);
      rs= pst.executeQuery();
     
       jTable1.setModel(DbUtils.resultSetToTableModel(rs));
    
             }catch(Exception ex){
                 JOptionPane.showMessageDialog(null, ex);
             }
                 DefaultTableCellRenderer centre =new DefaultTableCellRenderer();
        centre.setHorizontalAlignment(JLabel.CENTER);
         TableColumn col0=jTable1.getColumnModel().getColumn(0);
        TableColumn col1=jTable1.getColumnModel().getColumn(1);
          jTable1.getColumnModel().getColumn(1).setCellRenderer(centre);
        TableColumn col2=jTable1.getColumnModel().getColumn(2);
        TableColumn col3=jTable1.getColumnModel().getColumn(3);
          //centre.setHorizontalAlignment(JLabel.CENTER);
            jTable1.getColumnModel().getColumn(3).setCellRenderer(centre);
        TableColumn col4=jTable1.getColumnModel().getColumn(4);
         // centre.setHorizontalAlignment(JLabel.CENTER);
         
            jTable1.getColumnModel().getColumn(4).setCellRenderer(centre);
            //jTable1.getColumnModel().getColumn(4).;
           TableColumn col5=jTable1.getColumnModel().getColumn(5);
            TableColumn col6=jTable1.getColumnModel().getColumn(6);
      
       
       col0.setPreferredWidth(60);
       col1.setPreferredWidth(20);
       col2.setPreferredWidth(370);
       col3.setPreferredWidth(20);
       col4.setPreferredWidth(20);
       col5.setPreferredWidth(20);
       col6.setPreferredWidth(20);
    
    }

public void selecttable(){

     
      DefaultListModel list = new DefaultListModel();
     
      list.clear();
      Achive_check.batch.setModel(list);
          int row= jTable1.getSelectedRow();
          String Table_clicks = (jTable1.getModel().getValueAt(row,0). toString());
                    
         
        try{
            String sql="SELECT * FROM archive WHERE REF='"+Table_clicks+"'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                //String sum=rs.getString("nom");
                String sums=rs.getString("BATCH");
                 list.addElement(sums);
                
                 Achive_check.batch.setModel(list);
            }
            }
        catch(Exception ex){
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

        jScrollPane2 = new javax.swing.JScrollPane();

        jTable1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable1.setRowHeight(22);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 650, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
selecttable();      // TODO add your handling jrn here:
    }//GEN-LAST:event_jTable1MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane2;
    public static final javax.swing.JTable jTable1 = new javax.swing.JTable();
    // End of variables declaration//GEN-END:variables
}
