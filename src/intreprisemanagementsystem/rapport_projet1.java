/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intreprisemanagementsystem;

import java.awt.BorderLayout;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.swing.JRViewer;

/**
 *
 * @author Dosh
 */
public class rapport_projet1 extends javax.swing.JInternalFrame {

  Connection con=null;
PreparedStatement pst=null;
ResultSet rs= null;
 DefaultTableModel mode;
 
    public rapport_projet1() {
        initComponents();
        con=JavaDbConnect.dbConnect();
        buss();
    }
    
public void buss(){
     
           try{
              
            String sql="SELECT distinct(PROJET_NUM) FROM   projet";
            
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                //String sum=rs.getString("nom");
                String sums=rs.getString("PROJET_NUM");
                buss.addItem(sums);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
     }



                  public void reportBYSTOCK()
     {
           
     SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(date1.getDate());
         String addDate2 = dateFormats.format(date2.getDate());
             try{
                 
                 
                   String sqls="Select path from pathn";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
            if(rs.next()){
             
                String sum=rs.getString("path");
                  
                 String NameFiles=sum;
            String NameFile=""+NameFiles+"rapport_projet.jrxml";
           // String NameFile=""+NameFiles+"depense_projet.jrxml";
                 
                 
                 
                JasperDesign jd=JRXmlLoader.load(NameFile);
                 
               //JasperDesign jd=JRXmlLoader.load("C:\\Users\\Dosh\\Documents\\NetBeansProjects\\intreprisemanagementsystem\\src\\intreprisemanagementsystem\\journaldecaise.jrxml");
            //  String sql="select budget_trans.ITEM,sum(budget_trans.DEBIT) as DEBIT,sum(budget_trans.CREDIT) AS CREDIT,budget_trans.SOLD,budget_trans.PROJET,budget_trans.CODE,budget_trans.CAT,budget_trans.SUB_CAT,budget.LB,budget.LBSUB,PROJET.DATEIN,PROJET.DATEOUT,PROJET.PROJET_NUM,projet_bayeur.BAYEUR from budget_trans INNER JOIN projet on budget_trans.PROJET=PROJET.PROJET_NUM INNER JOIN projet_bayeur on budget_trans.PROJET=projet_bayeur.PROJET_NUM INNER JOIN budget on budget_trans.PROJET=budget.PROJECT where budget_trans.PROJET='"+buss.getSelectedItem()+"'";
         //    String sql="select * from budget_trans INNER JOIN projet on budget_trans.PROJET=PROJET.PROJET_NUM  where budget_trans.PROJET='"+buss.getSelectedItem()+"' group by budget_trans.code";
          String sql="select sum(debit),sum(credit),item,items,code,CODE_CAT,CODE_SUB_CAT,budget_trans.PROJET,CAT,SUB_CAT,BAYEUR,projet.PROJET as titre,projet.DATEIN,projet.DATEOUT,tete from budget_trans  INNER JOIN  projet_bayeur on budget_trans.PROJET= projet_bayeur.PROJET_NUM   INNER JOIN projet on budget_trans.PROJET=PROJET.PROJET_NUM where budget_trans.PROJET='"+buss.getSelectedItem()+"' and budget_trans.DATES BETWEEN '"+addDate+"' AND '"+addDate2+"' group by budget_trans.code";
         
       //
              
    
                jPanel3.removeAll();
     jPanel3.repaint();
     jPanel3.revalidate();
                JRDesignQuery nq=new JRDesignQuery();
                nq.setText(sql);
                jd.setQuery(nq);
      JasperReport jr =JasperCompileManager.compileReport(jd);
      JasperPrint jp=JasperFillManager.fillReport(jr,null,con);
     // JasperViewer.viewReport(jp,false);
    // JasperViewer m= new JasperViewer(jp);
    JRViewer m= new JRViewer(jp);
     jPanel3.setLayout(new BorderLayout());
     jPanel3.add(m);
            }
                 
             }catch(Exception ex){
                  JOptionPane.showMessageDialog(null, ex);   
             }
     }
    public void report_details()
     {
           
     SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(date1.getDate());
         String addDate2 = dateFormats.format(date2.getDate());
             try{
                 
                 
                   String sqls="Select path from pathn";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
            if(rs.next()){
             
                String sum=rs.getString("path");
                  
                 String NameFiles=sum;
            String NameFile=""+NameFiles+"rapport_detail.jrxml";
           // String NameFile=""+NameFiles+"depense_projet.jrxml";
                 
                 
                 
                JasperDesign jd=JRXmlLoader.load(NameFile);
                 
               //JasperDesign jd=JRXmlLoader.load("C:\\Users\\Dosh\\Documents\\NetBeansProjects\\intreprisemanagementsystem\\src\\intreprisemanagementsystem\\journaldecaise.jrxml");
            //  String sql="select budget_trans.ITEM,sum(budget_trans.DEBIT) as DEBIT,sum(budget_trans.CREDIT) AS CREDIT,budget_trans.SOLD,budget_trans.PROJET,budget_trans.CODE,budget_trans.CAT,budget_trans.SUB_CAT,budget.LB,budget.LBSUB,PROJET.DATEIN,PROJET.DATEOUT,PROJET.PROJET_NUM,projet_bayeur.BAYEUR from budget_trans INNER JOIN projet on budget_trans.PROJET=PROJET.PROJET_NUM INNER JOIN projet_bayeur on budget_trans.PROJET=projet_bayeur.PROJET_NUM INNER JOIN budget on budget_trans.PROJET=budget.PROJECT where budget_trans.PROJET='"+buss.getSelectedItem()+"'";
         //    String sql="select * from budget_trans INNER JOIN projet on budget_trans.PROJET=PROJET.PROJET_NUM  where budget_trans.PROJET='"+buss.getSelectedItem()+"' group by budget_trans.code";
          String sql="select * from budget_trans  INNER JOIN projet ON budget_tranS.projet=projet.PROJET_NUM  where budget_trans.PROJET='"+buss.getSelectedItem()+"' and DATES BETWEEN '"+addDate+"' AND '"+addDate2+"' order by CODE_CAT,CODE_SUB_CAT,CODE,DATES";
         
       //
               HashMap param= new HashMap();
    param.put("date1", date1.getText());
     param.put("date2",date2.getText());
    
               jPanel3.removeAll();
     jPanel3.repaint();
     jPanel3.revalidate();
                JRDesignQuery nq=new JRDesignQuery();
                nq.setText(sql);
                jd.setQuery(nq);
      JasperReport jr =JasperCompileManager.compileReport(jd);
      JasperPrint jp=JasperFillManager.fillReport(jr,null,con);
     // JasperViewer.viewReport(jp,false);
    // JasperViewer m= new JasperViewer(jp);
    JRViewer m= new JRViewer(jp);
     jPanel3.setLayout(new BorderLayout());
     jPanel3.add(m);
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
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        date1 = new com.alee.extended.date.WebDateField();
        date2 = new com.alee.extended.date.WebDateField();
        buss = new javax.swing.JComboBox<>();
        jComboBox1 = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu2 = new javax.swing.JMenu();
        jMenu1 = new javax.swing.JMenu();

        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_Accounting_16px.png"))); // NOI18N

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Action 1", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        date1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        date2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        buss.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        buss.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select One Project", "Select All Project" }));
        buss.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                bussPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        buss.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bussMouseClicked(evt);
            }
        });

        jComboBox1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Consolidated report", "Detailed report" }));

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton1.setText("Print");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(date1, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(date2, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(buss, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addGap(309, 309, 309))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(date1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(date2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buss, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1)))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(410, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1044, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 383, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jMenu2.setText("X");
        jMenu2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jMenu2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu2MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu2);

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

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
    }// </editor-fold>//GEN-END:initComponents

    private void jMenu2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu2MouseClicked
this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu2MouseClicked

    private void bussMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bussMouseClicked
//call_table();        // TODO add your handling code here:
    }//GEN-LAST:event_bussMouseClicked

    private void bussPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_bussPopupMenuWillBecomeInvisible
//call_table();        // TODO add your handling code here:
    }//GEN-LAST:event_bussPopupMenuWillBecomeInvisible

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
if(jComboBox1.getSelectedItem().equals("Consolidated report")){
    reportBYSTOCK();
}else{

 report_details();
}
               // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> buss;
    private com.alee.extended.date.WebDateField date1;
    private com.alee.extended.date.WebDateField date2;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    // End of variables declaration//GEN-END:variables
}
