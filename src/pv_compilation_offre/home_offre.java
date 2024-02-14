/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pv_compilation_offre;

import java.awt.BorderLayout;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
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
 * @author Doshe PC
 */
public class home_offre extends javax.swing.JInternalFrame {

Connection con=null;
PreparedStatement pst=null;
ResultSet rs= null;
String etrolls,roll_id,etrolls_log;
DefaultTableModel mode;
    public home_offre() {
        initComponents();
              con=JavaDbConnect.dbConnect();
         this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI bui= (BasicInternalFrameUI) this.getUI();
       bui.setNorthPane(null);
       call();
    }
 public void call(){
     DefaultListModel list = new DefaultListModel();
        
         
        try{
            String sql="SELECT NUM FROM postes";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                //String sum=rs.getString("nom");
                String sums=rs.getString("NUM");
                 list.addElement(sums);
                
                 batch.setModel(list);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
     
    
    }
     public void Table()
    {
         try{
           
             String sql="SELECT NUM as 'Numero offre',NAME AS Offre FROM POSTES WHERE NUM='"+batch.getSelectedValue()+"'";
     //  String sql="SELECT ID, `NUM_FACTURE`, `DATE_FACTURE`, `DESCRIPTION`, `DEBIT_QTY`, `DEBIT_PU`, `DEBIT_PT`, `DEBIT_TVA`, `CREDIT_QTY`, `CREDIT_PU`, `CREDIT_PT`, `CREDIT_TVA`, `DATES`, `NUM` FROM facture_fournisseur where compte='"+jComboBox6.getSelectedItem()+"'";
       pst = con.prepareStatement(sql);
      rs= pst.executeQuery();
      
       
      pv.offres.setModel(DbUtils.resultSetToTableModel(rs));
       mode=new DefaultTableModel();
       
       
        TableColumn col0=pv.offres.getColumnModel().getColumn(0);
        TableColumn col1=pv.offres.getColumnModel().getColumn(1);
      

       col0.setPreferredWidth(100);
       col1.setPreferredWidth(250);
       
     
    }catch(SQLException ex ){
     JOptionPane.showMessageDialog(null, ex);
}
//               
         
    } 
     public void calltable(){
   int row= jTable1.getSelectedRow();
          String Table_clicks = (jTable1.getModel().getValueAt(row,0). toString());
         
        try{
            String sql="SELECT * FROM PVS where numero='"+Table_clicks+"'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
               //  `NOM`, `PNOM`, `PRNOM`, `TEL`, `ADDR`, `OFFRE`, `DOC`, `EXIST`, `POINT`, `POSTE`, `NUMERO` FROM `pv` WHERE 1
                //String sum=rs.getString("nom");
                String sum1=rs.getString("NOM");
                 String sum2=rs.getString("PNOM");
                  String sum3=rs.getString("PRNOM");
                   String sum4=rs.getString("TEL");
                    String sum5=rs.getString("ADDR");
                    
                    pv.nom.setText(sum1);
                     pv.pnom.setText(sum2);
                      pv.prnom.setText(sum3);
                       pv.tel.setText(sum4);
                        pv.address.setText(sum5);
                         
                
                
               
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
     
    
    }
    
  public void clic_attCall_IN_LIST7()
    { 
        
          String val = null;
         try{
            String sql="SELECT sum(point) FROM  docss where offre='"+home_offre.batch.getSelectedValue()+"' and status='offre'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            if(rs.next()){
               //  `NOM`, `PNOM`, `PRNOM`, `TEL`, `ADDR`, `OFFRE`, `DOC`, `EXIST`, `POINT`, `POSTE`, `NUMERO` FROM `pv` WHERE 1
               val=rs.getString("sum(point)");
                }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
      //  DefaultListModel list = new DefaultListModel();
 
         try{
          String sql="SELECT NUMERO,FORMAT((SUM(POINT)*100/"+val+"),2) AS '-%-' FROM PVS where OFFRE='"+batch.getSelectedValue()+"' GROUP BY NUMERO";
             
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
             jTable1.setModel(DbUtils.resultSetToTableModel(rs));
         }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
         
         TableColumn col0=jTable1.getColumnModel().getColumn(0);
        TableColumn col1=jTable1.getColumnModel().getColumn(1);
        
       // TableColumn col2=jTable1.getColumnModel().getColumn(2);
       
       
       col0.setPreferredWidth(200);
       col1.setPreferredWidth(50);
      // col2.setPreferredWidth(20);
      
    
    }
  
    public void calltables(){
   int row= jTable1.getSelectedRow();
          String Table_clicks = (jTable1.getModel().getValueAt(row,0). toString());
         
        try{
            String sql="SELECT * FROM PVS where numero='"+Table_clicks+"' and offre='"+batch.getSelectedValue()+"'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
               //  `NOM`, `PNOM`, `PRNOM`, `TEL`, `ADDR`, `OFFRE`, `DOC`, `EXIST`, `POINT`, `POSTE`, `NUMERO` FROM `pv` WHERE 1
                //String sum=rs.getString("nom");
                String sum1=rs.getString("NOM");
                 String sum2=rs.getString("PNOM");
                  String sum3=rs.getString("PRNOM");
                   String sum4=rs.getString("TEL");
                    String sum5=rs.getString("ADDR");
                     String sum55=rs.getString("NUMERO");
                    pv.nom.setText(sum1);
                     pv.pnom.setText(sum2);
                      pv.prnom.setText(sum3);
                       pv.tel.setText(sum4);
                        pv.address.setText(sum5);
                         pv.address.setText(sum5);
                         pv.jLabel5.setText(sum55);
                         
                
                
               
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
       try{
          String sql="SELECT DOC,POINT FROM PVS where NUMERO='"+Table_clicks+"' and offre='"+batch.getSelectedValue()+"' and status='offre' ";
             
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
             pv.jTable4.setModel(DbUtils.resultSetToTableModel(rs));
         }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
         
         TableColumn col0=pv.jTable4.getColumnModel().getColumn(0);
        TableColumn col1=pv.jTable4.getColumnModel().getColumn(1);
           
        col0.setPreferredWidth(500);
       col1.setPreferredWidth(50);
      // col2.setPreferredWidth(20);
    
    }
    
   public void call_table(){
       
        try{
           
             String sql="SELECT DOC,point AS Documents FROM DOCSS where offre= '"+home_offre.batch.getSelectedValue()+"' and status='Offre'";
     //  String sql="SELECT ID, `NUM_FACTURE`, `DATE_FACTURE`, `DESCRIPTION`, `DEBIT_QTY`, `DEBIT_PU`, `DEBIT_PT`, `DEBIT_TVA`, `CREDIT_QTY`, `CREDIT_PU`, `CREDIT_PT`, `CREDIT_TVA`, `DATES`, `NUM` FROM facture_fournisseur where compte='"+jComboBox6.getSelectedItem()+"'";
       pst = con.prepareStatement(sql);
      rs= pst.executeQuery();
      
       
      pv.doc.setModel(DbUtils.resultSetToTableModel(rs));
       mode=new DefaultTableModel();
       
       
        TableColumn col0=pv.doc.getColumnModel().getColumn(0);
        TableColumn col1=pv.doc.getColumnModel().getColumn(1);
      

       col0.setPreferredWidth(1000);
       col1.setPreferredWidth(10);
//       
     
    }catch(SQLException ex ){
     JOptionPane.showMessageDialog(null, ex);
}
       
       
       
       
       //-------> call question pv test
    try{
          String sql="SELECT DOC AS Question ,Point FROM `docss` where OFFRE='"+batch.getSelectedValue()+"' and status='teste' order by id";
             
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
             pv_test.doc.setModel(DbUtils.resultSetToTableModel(rs));
         }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
         
        TableColumn col0=pv_test.doc.getColumnModel().getColumn(0);
        TableColumn col1=pv_test.doc.getColumnModel().getColumn(1);
        
       // TableColumn col2=jTable1.getColumnModel().getColumn(2);
       
       
       col0.setPreferredWidth(500);
       col1.setPreferredWidth(50);
      // col2.setPreferredWidth(20);
      
       try{
          String sql="SELECT DOC AS Question ,Point FROM `docss` where OFFRE='"+batch.getSelectedValue()+"' and status='Interview' order by id";
             
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
             pv_interview.doc.setModel(DbUtils.resultSetToTableModel(rs));
         }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
         
        TableColumn col0i= pv_interview.doc.getColumnModel().getColumn(0);
        TableColumn col1i=pv_interview.doc.getColumnModel().getColumn(1);
        
       // TableColumn col2=jTable1.getColumnModel().getColumn(2);
       
       
       col0i.setPreferredWidth(500);
       col1i.setPreferredWidth(50);
      // col2.setPreferredWidth(20);
      
      //-------> call resultat
      String val = null;
         try{
            String sql="SELECT sum(point) FROM  docss where offre='"+batch.getSelectedValue()+"' and status='teste'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            if(rs.next()){
               //  `NOM`, `PNOM`, `PRNOM`, `TEL`, `ADDR`, `OFFRE`, `DOC`, `EXIST`, `POINT`, `POSTE`, `NUMERO` FROM `pv` WHERE 1
               val=rs.getString("sum(point)");
                }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
      
         try{
              String sql="select Num,sum(point) as Point,FORMAT(sum(point)*100/"+val+",2) as '_%_' from pv_test  WHERE offre ='"+batch.getSelectedValue()+"' group by num";// group by nom having (sum(point)*100)/45 >= '"+jTextField1.getText()+"' order by nom";// ";
  //FORMAT(CREDIT,2)
         // String sql="SELECT Num,sum(point), FROM `pv_test` where OFFRE='"+batch.getSelectedValue()+"' order by id";
             
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
             pv_test.jTable5.setModel(DbUtils.resultSetToTableModel(rs));
         }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
         
        TableColumn col00=pv_test.jTable5.getColumnModel().getColumn(0);
        TableColumn col11=pv_test.jTable5.getColumnModel().getColumn(1);
        
        TableColumn col22=pv_test.jTable5.getColumnModel().getColumn(2);
       
       
       col00.setPreferredWidth(500);
       col11.setPreferredWidth(50);
       col22.setPreferredWidth(50);
       
       
       
       
       String vals = null;
         try{
            String sql="SELECT sum(point) FROM  docss where offre='"+batch.getSelectedValue()+"' and status='Interview'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            if(rs.next()){
               //  `NOM`, `PNOM`, `PRNOM`, `TEL`, `ADDR`, `OFFRE`, `DOC`, `EXIST`, `POINT`, `POSTE`, `NUMERO` FROM `pv` WHERE 1
               vals=rs.getString("sum(point)");
                }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
try{
              String sql="select Num,sum(point) as Point,FORMAT(sum(point)*100/"+vals+",2) as '_%_' from pv_test  WHERE offre ='"+batch.getSelectedValue()+"' and status='Interview' group by num";// group by nom having (sum(point)*100)/45 >= '"+jTextField1.getText()+"' order by nom";// ";
  //FORMAT(CREDIT,2)
         // String sql="SELECT Num,sum(point), FROM `pv_test` where OFFRE='"+batch.getSelectedValue()+"' order by id";
             
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
             pv_interview.jTable5.setModel(DbUtils.resultSetToTableModel(rs));
         }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
         
        TableColumn col0ii=pv_interview.jTable5.getColumnModel().getColumn(0);
        TableColumn col1ii=pv_interview.jTable5.getColumnModel().getColumn(1);
        
        TableColumn col2ii=pv_interview.jTable5.getColumnModel().getColumn(2);
       
       
       col0ii.setPreferredWidth(500);
       col1ii.setPreferredWidth(50);
       col2ii.setPreferredWidth(50);
   
   }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jScrollPane4 = new javax.swing.JScrollPane();

        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/pv_compilation_offre/icons.png"))); // NOI18N
        setPreferredSize(new java.awt.Dimension(1062, 571));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED, new java.awt.Color(204, 204, 204), new java.awt.Color(102, 102, 102)));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Offre", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pv_compilation_offre/icons8_Shop_30px.png"))); // NOI18N
        jLabel1.setText("Offres");
        jLabel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pv_compilation_offre/icons8_Literature_30px.png"))); // NOI18N
        jLabel2.setText("Docs");
        jLabel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pv_compilation_offre/icons8_Customs_Officer_30px.png"))); // NOI18N
        jLabel3.setText("PV ouverture");
        jLabel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pv_compilation_offre/icons8_Literature_30px.png"))); // NOI18N
        jLabel12.setText("List 1");
        jLabel12.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel12.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel12.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel12MouseClicked(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pv_compilation_offre/icons8_Literature_30px.png"))); // NOI18N
        jLabel13.setText("List 2");
        jLabel13.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel13.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel13.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel13MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Teste", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pv_compilation_offre/icons8_Search_24px.png"))); // NOI18N
        jLabel4.setText("Teste");
        jLabel4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pv_compilation_offre/icons8_Customs_Officer_30px.png"))); // NOI18N
        jLabel8.setText("Pv de correction");
        jLabel8.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel8.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel8MouseClicked(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pv_compilation_offre/icons8_Literature_30px.png"))); // NOI18N
        jLabel10.setText("List");
        jLabel10.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel10.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel10.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel10MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Interview", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pv_compilation_offre/icons8_Shop_30px.png"))); // NOI18N
        jLabel5.setText("Interview");
        jLabel5.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pv_compilation_offre/icons8_Customs_Officer_30px.png"))); // NOI18N
        jLabel9.setText("Pv de correction");
        jLabel9.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel9.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel9.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel9MouseClicked(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pv_compilation_offre/icons8_Literature_30px.png"))); // NOI18N
        jLabel11.setText("List");
        jLabel11.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel11.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel11.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel11MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/cancel.png"))); // NOI18N
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 133, Short.MAX_VALUE)
                .addComponent(jLabel6))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED, new java.awt.Color(204, 204, 204), new java.awt.Color(204, 204, 204)));

        batch.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        batch.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        batch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                batchMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(batch);

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
        jScrollPane4.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        boody.setBackground(new java.awt.Color(255, 255, 255));
        boody.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED, new java.awt.Color(204, 204, 204), new java.awt.Color(204, 204, 204)));
        boody.setLayout(new javax.swing.BoxLayout(boody, javax.swing.BoxLayout.LINE_AXIS));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(boody, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(boody, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        try{
            con=JavaDbConnect.dbConnect();
            boody.removeAll();
            boody.add(new post());
            boody.repaint();
            boody.revalidate();
        }catch(Exception ex){JOptionPane.showMessageDialog(null,ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) { /* Ignored */}
            }
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException ex) { /* Ignored */}
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) { /* Ignored */}
            }
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        try{
            con=JavaDbConnect.dbConnect();
            boody.removeAll();
            boody.add(new docs());
            boody.repaint();
            boody.revalidate();
        }catch(Exception ex){JOptionPane.showMessageDialog(null,ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) { /* Ignored */}
            }
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException ex) { /* Ignored */}
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) { /* Ignored */}
            }
        }
        // call();        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        try{
            con=JavaDbConnect.dbConnect();
            boody.removeAll();
            boody.add(new pv());
            boody.repaint();
            boody.revalidate();
        }catch(Exception ex){JOptionPane.showMessageDialog(null,ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) { /* Ignored */}
            }
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException ex) { /* Ignored */}
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) { /* Ignored */}
            }
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel3MouseClicked

    private void jLabel12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel12MouseClicked
        try{
            con=JavaDbConnect.dbConnect();
            Double vals = null;
            Double point = null;
            Double val = null;
            try{
                String sql="SELECT sum(point) FROM  docss where offre='"+batch.getSelectedValue()+"' and status='offre'";

                pst=con.prepareStatement(sql);
                rs=pst.executeQuery();
                if(rs.next()){
                    //  `NOM`, `PNOM`, `PRNOM`, `TEL`, `ADDR`, `OFFRE`, `DOC`, `EXIST`, `POINT`, `POSTE`, `NUMERO` FROM `pv` WHERE 1
                    vals=rs.getDouble("sum(point)");
                }
            }
            catch(Exception ex){
                JOptionPane.showMessageDialog(null, ex);}

            try{
                String sql="SELECT sum(point) FROM  PVS where offre='"+batch.getSelectedValue()+"' and  status='offre'";

                pst=con.prepareStatement(sql);
                rs=pst.executeQuery();
                if(rs.next()){
                    //  `NOM`, `PNOM`, `PRNOM`, `TEL`, `ADDR`, `OFFRE`, `DOC`, `EXIST`, `POINT`, `POSTE`, `NUMERO` FROM `pv` WHERE 1
                    point=rs.getDouble("sum(point)");
                }
            }
            catch(Exception ex){
                JOptionPane.showMessageDialog(null, ex);}
            val=(point*100)/vals;
            try{
                String sqls="Select path from pathn";

                pst=con.prepareStatement(sqls);
                rs=pst.executeQuery();
                if(rs.next()){

                    String sum=rs.getString("path");

                    String NameFiles=sum;

                    String NameFile=""+NameFiles+"pvs.jrxml";
                    JasperDesign jd=JRXmlLoader.load(NameFile);
                    String sql="select * from pvs  WHERE OFFRE='"+home_offre.batch.getSelectedValue()+"' and Status='Offre'";
                    //  HashMap param= new HashMap();
                    //
                    //     param.put("maxpoint",val);
                    boody.removeAll();
                    boody.repaint();
                    boody.revalidate();
                    JRDesignQuery nq=new JRDesignQuery();
                    nq.setText(sql);
                    jd.setQuery(nq);
                    JasperReport jr =JasperCompileManager.compileReport(jd);
                    JasperPrint jp=JasperFillManager.fillReport(jr,null,con);
                    // JasperViewer.viewReport(jp,false);
                    // JasperViewer m= new JasperViewer(jp);
                    JRViewer m= new JRViewer(jp);
                    boody.setLayout(new BorderLayout());
                    boody.add(m);

                }
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null, ex);
            }
        }catch(Exception ex){JOptionPane.showMessageDialog(null,ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) { /* Ignored */}
            }
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException ex) { /* Ignored */}
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) { /* Ignored */}
            }
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel12MouseClicked

    private void jLabel13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MouseClicked
        try{
            con=JavaDbConnect.dbConnect();
            pourcentage m = new pourcentage();
            m.show();
            m. setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        }catch(Exception ex){JOptionPane.showMessageDialog(null,ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) { /* Ignored */}
            }
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException ex) { /* Ignored */}
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) { /* Ignored */}
            }
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel13MouseClicked

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        try{
            con=JavaDbConnect.dbConnect();
            boody.removeAll();
            boody.add(new test());
            boody.repaint();
            boody.revalidate();
        }catch(Exception ex){JOptionPane.showMessageDialog(null,ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) { /* Ignored */}
            }
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException ex) { /* Ignored */}
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) { /* Ignored */}
            }
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel4MouseClicked

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked
        try{
            con=JavaDbConnect.dbConnect();
            boody.removeAll();
            boody.add(new pv_test());
            boody.repaint();
            boody.revalidate();
        }catch(Exception ex){JOptionPane.showMessageDialog(null,ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) { /* Ignored */}
            }
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException ex) { /* Ignored */}
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) { /* Ignored */}
            }
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel8MouseClicked

    private void jLabel10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel10MouseClicked
        try{
            con=JavaDbConnect.dbConnect();
            pourcentage1 m = new pourcentage1();
            m.show();
            m. setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        }catch(Exception ex){JOptionPane.showMessageDialog(null,ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) { /* Ignored */}
            }
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException ex) { /* Ignored */}
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) { /* Ignored */}
            }
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel10MouseClicked

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        try{
            con=JavaDbConnect.dbConnect();
            boody.removeAll();
            boody.add(new interview());
            boody.repaint();
            boody.revalidate();
        }catch(Exception ex){JOptionPane.showMessageDialog(null,ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) { /* Ignored */}
            }
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException ex) { /* Ignored */}
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) { /* Ignored */}
            }
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel5MouseClicked

    private void jLabel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseClicked
        try{
            con=JavaDbConnect.dbConnect();
            boody.removeAll();
            boody.add(new pv_interview());
            boody.repaint();
            boody.revalidate();
        }catch(Exception ex){JOptionPane.showMessageDialog(null,ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) { /* Ignored */}
            }
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException ex) { /* Ignored */}
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) { /* Ignored */}
            }
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel9MouseClicked

    private void jLabel11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseClicked
        try{
            con=JavaDbConnect.dbConnect();
            pourcentage11 m = new pourcentage11();
            m.show();
            m. setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        }catch(Exception ex){JOptionPane.showMessageDialog(null,ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) { /* Ignored */}
            }
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException ex) { /* Ignored */}
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) { /* Ignored */}
            }
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel11MouseClicked

    private void batchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_batchMouseClicked

        try{
            con=JavaDbConnect.dbConnect();
            clic_attCall_IN_LIST7();
            Table();

            test m = new test();
            m.Table();
            call_table();
        }catch(Exception ex){JOptionPane.showMessageDialog(null,ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) { /* Ignored */}
            }
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException ex) { /* Ignored */}
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) { /* Ignored */}
            }
        }

    }//GEN-LAST:event_batchMouseClicked

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        try{
            con=JavaDbConnect.dbConnect();
            calltables();
        }catch(Exception ex){JOptionPane.showMessageDialog(null,ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) { /* Ignored */}
            }
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException ex) { /* Ignored */}
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) { /* Ignored */}
            }
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_jTable1MouseClicked

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel6MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static final javax.swing.JList<String> batch = new javax.swing.JList<>();
    public static final javax.swing.JPanel boody = new javax.swing.JPanel();
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane4;
    public static final javax.swing.JTable jTable1 = new javax.swing.JTable();
    // End of variables declaration//GEN-END:variables
}
