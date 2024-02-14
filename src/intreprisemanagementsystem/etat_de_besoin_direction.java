/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intreprisemanagementsystem;

import Home_page.home;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author DOSHE
 */
public class etat_de_besoin_direction extends javax.swing.JInternalFrame {

    DefaultTableModel modes;
  DefaultTableModel mode;
  DefaultTableModel etmode;
  DefaultTableModel attmode;
 DefaultTableModel modetable3;
Connection con=null;
PreparedStatement pst=null;
ResultSet rs= null;
String rolls;
String etrolls;
String salaryrolls;
    public etat_de_besoin_direction() {
        initComponents();
              con=JavaDbConnect.dbConnect();
        call_in_table2();
    }
    
    public void call_in_table2(){
     
      try{
           
             String sql="SELECT distinct(`NUM`)as'NUMERO',`SUP` AS 'EMMETEUR' FROM `etat_de_besoin` where EXCECUTE is not null and TRIM(EXCECUTE)<>'' and APPROUVATION =''";
     //  String sql="SELECT ID, `NUM_FACTURE`, `DATE_FACTURE`, `DESCRIPTION`, `DEBIT_QTY`, `DEBIT_PU`, `DEBIT_PT`, `DEBIT_TVA`, `CREDIT_QTY`, `CREDIT_PU`, `CREDIT_PT`, `CREDIT_TVA`, `DATES`, `NUM` FROM facture_fournisseur where compte='"+jComboBox6.getSelectedItem()+"'";
       pst = con.prepareStatement(sql);
      rs= pst.executeQuery();
      
       
       jTable2.setModel(DbUtils.resultSetToTableModel(rs));
       mode=new DefaultTableModel();
       
       
        TableColumn col0=jTable2.getColumnModel().getColumn(0);
        TableColumn col1=jTable2.getColumnModel().getColumn(1);
       
       // TableColumn col4=jTable1.getColumnModel().getColumn(4);
        
       
       
      
       
       col0.setPreferredWidth(60);
       col1.setPreferredWidth(200);
      
       //col4.setPreferredWidth(100);
       
      
      
       
       
      // jTable1.setModel(mode);
       
     
    }catch(SQLException ex ){
     JOptionPane.showMessageDialog(null, ex);
}
     
     }
       public void call_in_table2_b(){
     
      try{
           
             String sql="SELECT distinct(`NUM`)as'NUMERO',`SUP` AS 'EMMETEUR' FROM `etat_de_besoin_b` where EXCECUTE is not null and TRIM(EXCECUTE)<>'' and APPROUVATION =''";
     //  String sql="SELECT ID, `NUM_FACTURE`, `DATE_FACTURE`, `DESCRIPTION`, `DEBIT_QTY`, `DEBIT_PU`, `DEBIT_PT`, `DEBIT_TVA`, `CREDIT_QTY`, `CREDIT_PU`, `CREDIT_PT`, `CREDIT_TVA`, `DATES`, `NUM` FROM facture_fournisseur where compte='"+jComboBox6.getSelectedItem()+"'";
       pst = con.prepareStatement(sql);
      rs= pst.executeQuery();
      
       
       jTable2.setModel(DbUtils.resultSetToTableModel(rs));
       mode=new DefaultTableModel();
       
       
        TableColumn col0=jTable2.getColumnModel().getColumn(0);
        TableColumn col1=jTable2.getColumnModel().getColumn(1);
       
       // TableColumn col4=jTable1.getColumnModel().getColumn(4);
        
       
       
      
       
       col0.setPreferredWidth(60);
       col1.setPreferredWidth(200);
      
       //col4.setPreferredWidth(100);
       
      
      
       
       
      // jTable1.setModel(mode);
       
     
    }catch(SQLException ex ){
     JOptionPane.showMessageDialog(null, ex);
}
     
     }
     public void show_photo_from_db()
               
   {
        int row= jTable2.getSelectedRow();
          String Table_clicks = (jTable2.getModel().getValueAt(row,0). toString());
         // Table_clicks = roll.getText();
            try{
          
         // String Table_click = (jTable1.getModel().getValueAt(row,3). toString());
          String sql = "SELECT  NUM_ID AS No,`DET`, `QTY`, `PU`, `PT` FROM  etat_de_besoin WHERE num= '"+Table_clicks+"'";
          pst = con.prepareStatement(sql);
          rs=pst.executeQuery();
          jTable3.setModel(DbUtils.resultSetToTableModel(rs));
       mode=new DefaultTableModel();
       
       
        TableColumn col0=jTable3.getColumnModel().getColumn(0);
        TableColumn col1=jTable3.getColumnModel().getColumn(1);
         TableColumn col2=jTable3.getColumnModel().getColumn(2);
        TableColumn col3=jTable3.getColumnModel().getColumn(3);
       
        TableColumn col4=jTable3.getColumnModel().getColumn(4);
        
       
       
      
       
       col0.setPreferredWidth(10);
       col1.setPreferredWidth(500);
        col2.setPreferredWidth(30);
       col3.setPreferredWidth(30);
      
       col4.setPreferredWidth(50);
         
    }catch(SQLException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }
            
            try{
            String sql="select sum(pt),DEVICE from etat_de_besoin  WHERE num= '"+Table_clicks+"'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
              Double sums=rs.getDouble("sum(pt)");
              String device =rs.getString("DEVICE");
               
                  DecimalFormat x = new DecimalFormat(device+"  #,##0.00");
                  DecimalFormat xx = new DecimalFormat("###");
                  sum.setText(x.format(sums));
                   sumss.setText(xx.format(sums));
                 // recherches.setText(sum);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
             try{
            String sql="select * from etat_de_besoin  WHERE num= '"+Table_clicks+"'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                String sums=rs.getString("SUP");
                  non.setText(sums);
                  
                    String sums1=rs.getString("NUM");
                  roll.setText(sums1);
                  
                 // recherches.setText(sum);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
   }
     
      public void show_photo_from_db_b()
               
   {
        int row= jTable2.getSelectedRow();
          String Table_clicks = (jTable2.getModel().getValueAt(row,0). toString());
         // Table_clicks = roll.getText();
            try{
          
         // String Table_click = (jTable1.getModel().getValueAt(row,3). toString());
          String sql = "SELECT  NUM_ID AS No,`DET`, `QTY`, `PU`, `PT` FROM  etat_de_besoin_b WHERE num= '"+Table_clicks+"'";
          pst = con.prepareStatement(sql);
          rs=pst.executeQuery();
          jTable3.setModel(DbUtils.resultSetToTableModel(rs));
       mode=new DefaultTableModel();
       
       
        TableColumn col0=jTable3.getColumnModel().getColumn(0);
        TableColumn col1=jTable3.getColumnModel().getColumn(1);
         TableColumn col2=jTable3.getColumnModel().getColumn(2);
        TableColumn col3=jTable3.getColumnModel().getColumn(3);
       
        TableColumn col4=jTable3.getColumnModel().getColumn(4);
        
       
       
      
       
       col0.setPreferredWidth(10);
       col1.setPreferredWidth(500);
        col2.setPreferredWidth(30);
       col3.setPreferredWidth(30);
      
       col4.setPreferredWidth(50);
         
    }catch(SQLException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }
            
            try{
            String sql="select sum(pt),DEVICE from etat_de_besoin_b  WHERE num= '"+Table_clicks+"'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
              Double sums=rs.getDouble("sum(pt)");
              String device =rs.getString("DEVICE");
               
                  DecimalFormat x = new DecimalFormat(device+"  #,##0.00");
                  DecimalFormat xx = new DecimalFormat("###");
                  sum.setText(x.format(sums));
                   sumss.setText(xx.format(sums));
                 // recherches.setText(sum);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
             try{
            String sql="select * from etat_de_besoin_b  WHERE num= '"+Table_clicks+"'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                String sums=rs.getString("SUP");
                  non.setText(sums);
                  
                    String sums1=rs.getString("NUM");
                  roll.setText(sums1);
                  
                 // recherches.setText(sum);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
   }
    
     public void call_in_table3g(){
     
      try{
           
             String sql="SELECT  `DET`, `QTY`, `PU`, `PT` FROM `etat_de_besoin`";
     //  String sql="SELECT ID, `NUM_FACTURE`, `DATE_FACTURE`, `DESCRIPTION`, `DEBIT_QTY`, `DEBIT_PU`, `DEBIT_PT`, `DEBIT_TVA`, `CREDIT_QTY`, `CREDIT_PU`, `CREDIT_PT`, `CREDIT_TVA`, `DATES`, `NUM` FROM facture_fournisseur where compte='"+jComboBox6.getSelectedItem()+"'";
       pst = con.prepareStatement(sql);
      rs= pst.executeQuery();
      
       
       jTable3.setModel(DbUtils.resultSetToTableModel(rs));
       mode=new DefaultTableModel();
       
       
        TableColumn col0=jTable3.getColumnModel().getColumn(0);
        TableColumn col1=jTable3.getColumnModel().getColumn(1);
       
       // TableColumn col4=jTable1.getColumnModel().getColumn(4);
        
       
       
      
       
       col0.setPreferredWidth(50);
       col1.setPreferredWidth(200);
      
       //col4.setPreferredWidth(100);
       
      
      
       
       
      // jTable1.setModel(mode);
       
     
    }catch(SQLException ex ){
     JOptionPane.showMessageDialog(null, ex);
}
     
     }
   //  DefaultTableModel excels= (DefaultTableModel)jTable1.getModel(); 
//    for(int i=0; i < excels.getRowCount();i++){
//      DATES = excels.getValueAt(i,0).toString();   
public void save_Contract(){
          int indexs[]=jTable3.getSelectedRows();
          DefaultTableModel excels= (DefaultTableModel)jTable3.getModel(); 
        String LB = null,PRO = null,num_fact=null;
        Double CRE = null,DE;
         int row= jTable2.getSelectedRow();
         int rows= jTable3.getSelectedRow();
          String Table_clicks = (jTable3.getModel().getValueAt(row,0). toString());
          
       for(int i=0; i < excels.getRowCount();i++){
           String numid = (jTable3.getModel().getValueAt(rows,0). toString());
           String det = (jTable3.getModel().getValueAt(rows,1). toString());
           String pu = (jTable3.getModel().getValueAt(rows,2). toString());
           String qty = (jTable3.getModel().getValueAt(rows,3). toString());
           String pt = (jTable3.getModel().getValueAt(rows,4). toString());
           
//             String numid = (jTable3.getModel().getValueAt(indexs[i],0). toString());
//             String det = (jTable3.getModel().getValueAt(indexs[i],1). toString());
//             String qty = (jTable3.getModel().getValueAt(indexs[i],2). toString());
//             String pu = (jTable3.getModel().getValueAt(indexs[i],3). toString());
//             String pt = (jTable3.getModel().getValueAt(indexs[i],4). toString());
     
        try{
 String sql = "UPDATE etat_de_besoin SET DET='"+det+"',QTY='"+qty+"',PU='"+pu+"',PT='"+pt+"' WHERE NUM ='"+Table_clicks+"' and num_id='"+numid+"'";
        
         pst = con.prepareStatement(sql);
       pst.executeUpdate();


}catch(Exception ex){JOptionPane.showMessageDialog(null,ex);}  
        }
      JOptionPane.showMessageDialog(null,"Transaction Saved");
       
     
     } 

 public void saveS(){
   
          String Table_clicks = roll.getText();
  int rows=jTable3.getRowCount();
 
 String numid = null,det = null,qty=null,pu=null,pt;
  for(int row = 0; row<rows; row++)
  { numid = (String)jTable3.getValueAt(row, 0);  
    det = (String)jTable3.getValueAt(row, 1);
    qty = (String)jTable3.getValueAt(row, 2);
    pu = (String)jTable3.getValueAt(row, 3);
    pt = (String)jTable3.getValueAt(row, 4);
    Double Qty= Double.parseDouble(qty);
    Double Pt= Double.parseDouble(pu);
     String  ReplaceString = det.replace("'", "''");
   
          try{
 String sql = "UPDATE etat_de_besoin SET DET='"+ReplaceString+"',QTY='"+qty+"',PU='"+pu+"',PT='"+Qty*Pt+"' WHERE NUM ='"+Table_clicks+"' and num_id='"+numid+"'";
        
         pst = con.prepareStatement(sql);
       pst.executeUpdate();


}catch(Exception ex){JOptionPane.showMessageDialog(null,ex);}  
        }
  JOptionPane.showMessageDialog(null,"Tranction Saved");
  } 
 public void delete(){
          int indexs[]=jTable3.getSelectedRows();
        String Table_clicks = roll.getText();
        for(int i=0; i < indexs.length;i++){
             String nums = (jTable3.getModel().getValueAt(indexs[i],0). toString());
        try{
        String sql = "DELETE FROM etat_de_besoin where NUM='"+Table_clicks+"' and num_id='"+nums+"'";
        
         pst = con.prepareStatement(sql);
         pst.executeUpdate();

     }catch(SQLException | HeadlessException ex ){
     
     } 
        } 
        JOptionPane.showMessageDialog(null,"Tranction Deleted");
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
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jPanel4 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        sumss = new javax.swing.JTextField();
        non = new javax.swing.JTextField();
        roll = new javax.swing.JTextField();
        jTextField1 = new javax.swing.JTextField();
        jRadioButton1 = new javax.swing.JRadioButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        setBackground(new java.awt.Color(255, 255, 255));
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_Invoice_16px.png"))); // NOI18N

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jTable2.setBackground(new java.awt.Color(0, 0, 0));
        jTable2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTable2.setForeground(new java.awt.Color(255, 255, 255));
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 298, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 483, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jTable3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable3.setRowHeight(26);
        jTable3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable3MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTable3);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton1.setText("Approuver");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        sum.setEditable(false);
        sum.setBackground(new java.awt.Color(0, 0, 0));
        sum.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        sum.setForeground(new java.awt.Color(255, 255, 255));
        sum.setText("0.00");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/saves.jpg"))); // NOI18N
        jLabel1.setText("Save");
        jLabel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/cancel.png"))); // NOI18N
        jLabel2.setText("Delete");
        jLabel2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });

        sumss.setEditable(false);
        sumss.setEnabled(false);

        non.setEditable(false);
        non.setEnabled(false);

        roll.setEditable(false);
        roll.setEnabled(false);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(roll, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 473, Short.MAX_VALUE)
                    .addComponent(non, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(sumss)))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(sumss, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(non, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(roll, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sum, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(sum)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, 0)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );

        jTextField1.setEditable(false);
        jTextField1.setBackground(new java.awt.Color(0, 0, 0));
        jTextField1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTextField1.setForeground(new java.awt.Color(255, 255, 255));
        jTextField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField1.setText("APPROBATION DES ETAT DE BESOINS");

        jRadioButton1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jRadioButton1.setText("Back UP");
        jRadioButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRadioButton1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jRadioButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jRadioButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jMenu1.setText("X");
        jMenu1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jMenu1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu1MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu1);

        jMenu2.setText("..........");
        jMenu2.setToolTipText("");
        jMenu2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu2MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setBounds(100, 10, 1168, 619);
    }// </editor-fold>//GEN-END:initComponents

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
if(jRadioButton1.isSelected()){
show_photo_from_db_b(); 
}else{
show_photo_from_db(); 
}
                  // TODO add your handling code here:
    }//GEN-LAST:event_jTable2MouseClicked

    private void jTable3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable3MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable3MouseClicked

    private void jMenu1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu1MouseClicked
this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu1MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
 int response = JOptionPane.showConfirmDialog(null, "VOULEZ VOUS APPORTEZ VOTRE APPROBATION A CET EB? ET  IMPRIME LA DEMANDE DE PAIEMENT", "Confirm",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (response == JOptionPane.YES_OPTION) {
if(jRadioButton1.isSelected()){
 try{
    
     int row= jTable2.getSelectedRow();
          String Table_clicks = (jTable2.getModel().getValueAt(row,0). toString());
    String appr =home.user.getText();
 String sql = "UPDATE etat_de_besoin_b SET APPROUVATION=? WHERE NUM ='"+Table_clicks+"'";
        
         pst = con.prepareStatement(sql);
        // pst.setString(2,recherche.getText());
          pst.setString(1,appr);
         
        
             
                       
         pst.executeUpdate();


}catch(Exception ex){JOptionPane.showMessageDialog(null,ex);}  
call_in_table2_b();
sum.setText("0.00");
try{
          
         // String Table_click = (jTable1.getModel().getValueAt(row,3). toString());
          String sql = "SELECT  `DET`, `QTY`, `PU`, `PT` FROM  etat_de_besoin_b WHERE num= 'xxxxxxx'";
          pst = con.prepareStatement(sql);
          rs=pst.executeQuery();
          jTable3.setModel(DbUtils.resultSetToTableModel(rs));
       mode=new DefaultTableModel();
       
       
        TableColumn col0=jTable3.getColumnModel().getColumn(0);
        TableColumn col1=jTable3.getColumnModel().getColumn(1);
         TableColumn col2=jTable3.getColumnModel().getColumn(2);
        TableColumn col3=jTable3.getColumnModel().getColumn(3);
       
       // TableColumn col4=jTable1.getColumnModel().getColumn(4);
        
       
       
      
       
       col0.setPreferredWidth(200);
       col1.setPreferredWidth(30);
        col2.setPreferredWidth(30);
       col3.setPreferredWidth(60);
      
       //col4.setPreferredWidth(100);
         
    }catch(SQLException ex ){
         JOptionPane.showMessageDialog(null,ex);}
}else{
 try{
    
     int row= jTable2.getSelectedRow();
          String Table_clicks = (jTable2.getModel().getValueAt(row,0). toString());
    String appr =home.user.getText();
 String sql = "UPDATE etat_de_besoin SET APPROUVATION=? WHERE NUM ='"+Table_clicks+"'";
        
         pst = con.prepareStatement(sql);
        // pst.setString(2,recherche.getText());
          pst.setString(1,appr);
         
        
             
                       
         pst.executeUpdate();


}catch(Exception ex){JOptionPane.showMessageDialog(null,ex);}  
call_in_table2();
sum.setText("0.00");
try{
          
         // String Table_click = (jTable1.getModel().getValueAt(row,3). toString());
          String sql = "SELECT  `DET`, `QTY`, `PU`, `PT` FROM  etat_de_besoin WHERE num= 'xxxxxxx'";
          pst = con.prepareStatement(sql);
          rs=pst.executeQuery();
          jTable3.setModel(DbUtils.resultSetToTableModel(rs));
       mode=new DefaultTableModel();
       
       
        TableColumn col0=jTable3.getColumnModel().getColumn(0);
        TableColumn col1=jTable3.getColumnModel().getColumn(1);
         TableColumn col2=jTable3.getColumnModel().getColumn(2);
        TableColumn col3=jTable3.getColumnModel().getColumn(3);
       
       // TableColumn col4=jTable1.getColumnModel().getColumn(4);
        
       
       
      
       
       col0.setPreferredWidth(200);
       col1.setPreferredWidth(30);
        col2.setPreferredWidth(30);
       col3.setPreferredWidth(60);
      
       //col4.setPreferredWidth(100);
         
    }catch(SQLException ex ){
         JOptionPane.showMessageDialog(null,ex);}
}
               
               
            } else if (response == JOptionPane.NO_OPTION) {
                
            } 
          
            
           


        
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
saveS();
show_photo_from_db(); // TODO add your handling code here:
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
delete(); 
show_photo_from_db(); // TODO add your handling code here:
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jMenu2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu2MouseClicked
 CALL_EB m = new CALL_EB();
        m.show();
        m. setDefaultCloseOperation(DISPOSE_ON_CLOSE);          // TODO add your handling code here:
    }//GEN-LAST:event_jMenu2MouseClicked

    private void jRadioButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRadioButton1MouseClicked
call_in_table2_b();        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton1MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable2;
    public static final javax.swing.JTable jTable3 = new javax.swing.JTable();
    private javax.swing.JTextField jTextField1;
    public static javax.swing.JTextField non;
    public static javax.swing.JTextField roll;
    public static final javax.swing.JTextField sum = new javax.swing.JTextField();
    public static javax.swing.JTextField sumss;
    // End of variables declaration//GEN-END:variables
}
