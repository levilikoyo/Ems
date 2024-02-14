/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intreprisemanagementsystem;

import Home_page.file_carburant;
import java.awt.BorderLayout;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;
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
public class print_carb extends javax.swing.JInternalFrame {

    DefaultTableModel mode;
Connection con=null;
PreparedStatement pst=null;
ResultSet rs= null;
    public print_carb() {
        initComponents();
         con=JavaDbConnect.dbConnect();
         jDateChooser1.setDate(new Date());
         jDateChooser2.setDate(new Date());
         
    }
   
                   
                   
                   
                   public void reports()
                           
     {
          SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String date1 = dateFormats.format(jDateChooser1.getDate());
          String date2 = dateFormats.format(jDateChooser2.getDate());
         try{           String sqls="Select path from pathn";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
            if(rs.next()){
             
                String sum=rs.getString("path");
                  
                 String NameFiles=sum;
            
            String NameFile=""+NameFiles+"Bon_de_carburant.jrxml";
                 
                 
                 
                JasperDesign jd=JRXmlLoader.load(NameFile);
                 
               //JasperDesign jd=JRXmlLoader.load("C:\\Users\\Dosh\\Documents\\NetBeansProjects\\intreprisemanagementsystem\\src\\intreprisemanagementsystem\\journaldecaise.jrxml");
              String sql="select * from bon_carburant WHERE dates between '"+date1+"' and '"+date2+"'";
              
    
                JRDesignQuery nq=new JRDesignQuery();
                nq.setText(sql);
                jd.setQuery(nq);
                
                
               
                 JasperReport jr =JasperCompileManager.compileReport(jd);
                 JasperPrint jp=JasperFillManager.fillReport(jr,null,con);
                 JRViewer m= new JRViewer(jp);
    jPanel1.setLayout(new BorderLayout());
     jPanel1.add(m);
            }
                 
             }catch(Exception ex){
                  JOptionPane.showMessageDialog(null, ex);   
             }
     }
                   
            public void reportsType()
                           
     {
         int row= file_carburant.jTable1.getSelectedRow();
          String Table_click = (file_carburant.jTable1.getModel().getValueAt(row,0). toString());
          SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String date1 = dateFormats.format(jDateChooser1.getDate());
          String date2 = dateFormats.format(jDateChooser2.getDate());
         try{           String sqls="Select path from pathn";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
            if(rs.next()){
             
                String sum=rs.getString("path");
                  
                 String NameFiles=sum;
            
            String NameFile=""+NameFiles+"Bon_de_carburant.jrxml";
                 
                 
                 
                JasperDesign jd=JRXmlLoader.load(NameFile);
                 
               //JasperDesign jd=JRXmlLoader.load("C:\\Users\\Dosh\\Documents\\NetBeansProjects\\intreprisemanagementsystem\\src\\intreprisemanagementsystem\\journaldecaise.jrxml");
              String sql="select * from bon_carburant WHERE TYPE='"+Table_click+"'and dates between '"+date1+"' and '"+date2+"'";
              
    
                JRDesignQuery nq=new JRDesignQuery();
                nq.setText(sql);
                jd.setQuery(nq);
                
                
               
                 JasperReport jr =JasperCompileManager.compileReport(jd);
                 JasperPrint jp=JasperFillManager.fillReport(jr,null,con);
                       JRViewer m= new JRViewer(jp);
    jPanel1.setLayout(new BorderLayout());
     jPanel1.add(m);
            }
                 
             }catch(Exception ex){
                  JOptionPane.showMessageDialog(null, ex);   
             }
     }          
                   
                    public void reportstation()
                           
     {
         int row= file_carburant.jTable1.getSelectedRow();
          String Table_click = (file_carburant.jTable1.getModel().getValueAt(row,0). toString());
          SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String date1 = dateFormats.format(jDateChooser1.getDate());
          String date2 = dateFormats.format(jDateChooser2.getDate());
       
         
         try{           String sqls="Select path from pathn";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
            if(rs.next()){
             
                String sum=rs.getString("path");
                  
                 String NameFiles=sum;
            
            String NameFile=""+NameFiles+"bon_carburant_station.jrxml";
                 
                 
                 
                JasperDesign jd=JRXmlLoader.load(NameFile);
                 
               //JasperDesign jd=JRXmlLoader.load("C:\\Users\\Dosh\\Documents\\NetBeansProjects\\intreprisemanagementsystem\\src\\intreprisemanagementsystem\\journaldecaise.jrxml");
              String sql="select * from bon_carburant WHERE STATION ='"+Table_click+"' and dates between '"+date1+"' and '"+date2+"'";
              
    
                JRDesignQuery nq=new JRDesignQuery();
                nq.setText(sql);
                jd.setQuery(nq);
                
                
               
                 JasperReport jr =JasperCompileManager.compileReport(jd);
                 JasperPrint jp=JasperFillManager.fillReport(jr,null,con);
                       JRViewer m= new JRViewer(jp);
    jPanel1.setLayout(new BorderLayout());
     jPanel1.add(m);
            }
                 
             }catch(Exception ex){
                  JOptionPane.showMessageDialog(null, ex);   
             }
     }
              
                    public void reportss()
                           
     {
         int row= file_carburant.jTable1.getSelectedRow();
          String Table_click = (file_carburant.jTable1.getModel().getValueAt(row,0). toString());
          SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String date1 = dateFormats.format(jDateChooser1.getDate());
          String date2 = dateFormats.format(jDateChooser2.getDate());
         try{           String sqls="Select path from pathn";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
            if(rs.next()){
             
                String sum=rs.getString("path");
                  
                 String NameFiles=sum;
            
            String NameFile=""+NameFiles+"Bon_de_carburant.jrxml";
                 
                 
                 
                JasperDesign jd=JRXmlLoader.load(NameFile);
                 
               //JasperDesign jd=JRXmlLoader.load("C:\\Users\\Dosh\\Documents\\NetBeansProjects\\intreprisemanagementsystem\\src\\intreprisemanagementsystem\\journaldecaise.jrxml");
              String sql="select * from bon_carburant WHERE MODEL ='"+Table_click+"' and  dates between '"+date1+"' and '"+date2+"'";
              
    
                JRDesignQuery nq=new JRDesignQuery();
                nq.setText(sql);
                jd.setQuery(nq);
                
                
               
                 JasperReport jr =JasperCompileManager.compileReport(jd);
                 JasperPrint jp=JasperFillManager.fillReport(jr,null,con);
                        JRViewer m= new JRViewer(jp);
    jPanel1.setLayout(new BorderLayout());
     jPanel1.add(m);
            }
                 
             }catch(Exception ex){
                  JOptionPane.showMessageDialog(null, ex);   
             }
     }
              
                  public void reportproject()
                           
     {
         int row= file_carburant.jTable1.getSelectedRow();
          String Table_click = (file_carburant.jTable1.getModel().getValueAt(row,0). toString());
          SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String date1 = dateFormats.format(jDateChooser1.getDate());
          String date2 = dateFormats.format(jDateChooser2.getDate());
         try{           String sqls="Select path from pathn";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
            if(rs.next()){
             
                String sum=rs.getString("path");
                  
                 String NameFiles=sum;
            
            String NameFile=""+NameFiles+"Bon_de_carburant.jrxml";
                 
                 
                 
                JasperDesign jd=JRXmlLoader.load(NameFile);
                 
               //JasperDesign jd=JRXmlLoader.load("C:\\Users\\Dosh\\Documents\\NetBeansProjects\\intreprisemanagementsystem\\src\\intreprisemanagementsystem\\journaldecaise.jrxml");
              String sql="select * from bon_carburant WHERE PROJET ='"+Table_click+"' and  dates between '"+date1+"' and '"+date2+"'";
              
    
                JRDesignQuery nq=new JRDesignQuery();
                nq.setText(sql);
                jd.setQuery(nq);
                
                
               
                 JasperReport jr =JasperCompileManager.compileReport(jd);
                 JasperPrint jp=JasperFillManager.fillReport(jr,null,con);
                       JRViewer m= new JRViewer(jp);
    jPanel1.setLayout(new BorderLayout());
     jPanel1.add(m);
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
        selecte = new javax.swing.JComboBox<>();
        jDateChooser1 = new com.alee.extended.date.WebDateField();
        jDateChooser2 = new com.alee.extended.date.WebDateField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_Gas_Station_16px.png"))); // NOI18N

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 402, Short.MAX_VALUE)
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        selecte.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select One Items", "Print All Transaction", "Print By Vehicle", "Print By Station", "Print By Project", "Print By Fuel Type " }));
        selecte.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        selecte.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                selectePopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        jDateChooser1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jDateChooser2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_Print_30px.png"))); // NOI18N
        jLabel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/cancel.png"))); // NOI18N
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
                .addComponent(selecte, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 368, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(selecte)
                    .addComponent(jDateChooser2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new javax.swing.BoxLayout(jPanel3, javax.swing.BoxLayout.LINE_AXIS));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel2MouseClicked

    private void selectePopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_selectePopupMenuWillBecomeInvisible
if(selecte.getSelectedItem().equals("Print By Project")){
 
   jPanel3.removeAll();
jPanel3.add(new file_carburant());
try{
            String sql="select CAT AS Projet from caisse_dispacting WHERE NAME='"+Home_page.home.user.getText()+"' and ACCESS='Yes'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            file_carburant.jTable1.setModel(DbUtils.resultSetToTableModel(rs));
 }catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex); }

jPanel3.repaint();
jPanel3.revalidate();

}else if(selecte.getSelectedItem().equals("Print By Vehicle")){
 
   jPanel3.removeAll();
jPanel3.add(new file_carburant());
try{
            String sql="SELECT Distinct(MODEL) as Vehicule FROM  bon_carburant ORDER BY MODEL";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            file_carburant.jTable1.setModel(DbUtils.resultSetToTableModel(rs));
 }catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex); }

jPanel3.repaint();
jPanel3.revalidate();

}else if(selecte.getSelectedItem().equals("Print By Station")){
 
   jPanel3.removeAll();
jPanel3.add(new file_carburant());
try{
            String sql="SELECT Distinct(STATION) as 'Petro Station' FROM  bon_carburant";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            file_carburant.jTable1.setModel(DbUtils.resultSetToTableModel(rs));
 }catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex); }

jPanel3.repaint();
jPanel3.revalidate();

}else if(selecte.getSelectedItem().equals("Print By Fuel Type ")){
 
   jPanel3.removeAll();
jPanel3.add(new file_carburant());
try{
            String sql="SELECT Distinct(TYPE) as 'Type of Fuel' FROM  bon_carburant";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            file_carburant.jTable1.setModel(DbUtils.resultSetToTableModel(rs));
 }catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex); }

jPanel3.repaint();
jPanel3.revalidate();

}else{
jPanel3.removeAll();
jPanel3.repaint();
jPanel3.revalidate();
}
//Print By Vehicle, Print By Station, Print By Project, Print By Type        
        //Select One Items, Print All Transaction, Print By Vehicle, Print By Station, Print By Project, Print By Fuel Type      // TODO add your handling code here:
    }//GEN-LAST:event_selectePopupMenuWillBecomeInvisible

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked

        if(selecte.getSelectedItem().equals("Print All Transaction")){
            jPanel1.removeAll();
jPanel1.repaint();
jPanel1.revalidate();
reports(); 
}else if(selecte.getSelectedItem().equals("Print By Vehicle")){
     jPanel1.removeAll();
jPanel1.repaint();
jPanel1.revalidate();
reportss(); 
}  else if(selecte.getSelectedItem().equals("Print By Station")){
     jPanel1.removeAll();
jPanel1.repaint();
jPanel1.revalidate();
reportstation();
}  else if(selecte.getSelectedItem().equals("Print By Project")){
     jPanel1.removeAll();
jPanel1.repaint();
jPanel1.revalidate();
reportproject();  
}  else if(selecte.getSelectedItem().equals("Print By Fuel Type ")){
     jPanel1.removeAll();
jPanel1.repaint();
jPanel1.revalidate();
reportsType(); 
}        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel1MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.alee.extended.date.WebDateField jDateChooser1;
    private com.alee.extended.date.WebDateField jDateChooser2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    public static final javax.swing.JPanel jPanel3 = new javax.swing.JPanel();
    private javax.swing.JComboBox<String> selecte;
    // End of variables declaration//GEN-END:variables
}
