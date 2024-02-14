/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intreprisemanagementsystem;

import static intreprisemanagementsystem.excel.jTable1;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import net.proteanit.sql.DbUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author DOSHE
 */
public class codebudget extends javax.swing.JFrame {

   DefaultTableModel mode;
    Connection con=null;
    PreparedStatement pst=null;
    ResultSet rs= null;
    String path;
    String replaceString[];
    public codebudget() {
        initComponents();
              con=JavaDbConnect.dbConnect();
          setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("icons8_Raspberry_Pi_48px_1.png")));
     
      
        search.addItem("Search By:");
        search.addItem("Categorie");
        search.addItem("Items");
        search.addItem("Code");
        Call_ID_TO_BOMBOBOXr();
    }
    
    
    public void imports(){
        EB_Model();
       DefaultTableModel excels= (DefaultTableModel)jTable1.getModel(); 
        
        FileInputStream excelFILS=null;
        BufferedInputStream excelBIS=null;
        XSSFWorkbook excelImportWorkbook;
        
        
        String curentDirectiryPath="C:\\Users\\Dosh\\Desktop";
        JFileChooser excelFileChooserImport = new JFileChooser(curentDirectiryPath);
     // excelFileChooserImport.showOpenDialog(null);
      
      int excelchooser=excelFileChooserImport.showOpenDialog(null);
      
      if(excelchooser== JFileChooser.APPROVE_OPTION){
      try{
      File exceleFile =excelFileChooserImport.getSelectedFile();
      excelFILS=new FileInputStream(exceleFile);
      excelBIS= new BufferedInputStream(excelFILS);
       excelImportWorkbook = new XSSFWorkbook(excelBIS);
      XSSFSheet excelSheet = excelImportWorkbook.getSheetAt(0);
      
     
      for(int i=1;i<excelSheet.getLastRowNum(); i++){
      
      XSSFRow excelRow = excelSheet.getRow(i);
      XSSFCell cell = excelRow.getCell(0);
      XSSFCell cell1 = excelRow.getCell(1);
     
     excels.addRow(new Object[]{cell,cell1}); 
      }
      
      }catch(FileNotFoundException ex){
      ex.printStackTrace();
      }     catch (IOException ex) {
               ex.printStackTrace();
            }
      
      }
SaveBudget();
Table(); 
}
     public void EB_Model(){
        mode=new DefaultTableModel();
        mode.addColumn("CODE");    
        mode.addColumn("ITEM");
        mode.addColumn("CAT");
       
        jTable1.setModel(mode);

  }
    public void SaveBudget(){
   DefaultTableModel excels= (DefaultTableModel)jTable1.getModel(); 
   String CODE= null,ITEM = null;
    for(int i=0; i < excels.getRowCount();i++){
    CODE  = excels.getValueAt(i,0). toString();
    ITEM  = excels.getValueAt(i,1). toString();
    
          
         
          
         try {
      
        PreparedStatement pst = con.prepareStatement("INSERT INTO `budget_code`(`CAT`, `ITEM`, `CODE`)"
                +"value(?,?,?)");
       
        
        pst.setString(1, cat.getSelectedItem().toString());
         pst.setString(2, ITEM);
         pst.setString(3, CODE);
       
        
         pst.executeUpdate();
        // showTableData();
      
         
               //  JOptionPane.showMessageDialog(null,"data saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
            
        }
   
          
    JOptionPane.showMessageDialog(null,"Tranction Saved");
   
  }
    
    
    public void Call_ID_TO_BOMBOBOXr()
    {
         
        try{
           
            String sql="select * from projet";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                String sum=rs.getString("PROJET_NUM");
                  cat.addItem(sum);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
         }
    
    
    
    
    
    
    
    
    
     public void savemateriel()
    {
          
        try {
      
        PreparedStatement pst = con.prepareStatement("INSERT INTO `budget_code`(`CAT`, `ITEM`, `CODE`)"
                +"value(?,?,?)");
       
        
        pst.setString(1, cat.getSelectedItem().toString());
         pst.setString(2, item.getText());
         pst.setString(3, code.getText());
       
        
         pst.executeUpdate();
        // showTableData();
      
         
               //  JOptionPane.showMessageDialog(null,"data saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
        item.setText("");
        code.setText("");
    }
      public void updatemateriel()
    {
          
        try {
      
        PreparedStatement pst = con.prepareStatement("update `budget_code` set `CAT`=?, `ITEM`=?, `CODE`=? where CODE='"+id+"' AND cat='"+cat.getSelectedItem()+"'");
       
        
        pst.setString(1, cat.getSelectedItem().toString());
         pst.setString(2, item.getText());
         pst.setString(3, code.getText());
       
        
         pst.executeUpdate();
        // showTableData();
      
         
               //  JOptionPane.showMessageDialog(null,"data saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
        item.setText("");
        code.setText("");
        id="";
    }
     public void delete()
    {
     
         try{
        String sql = "DELETE FROM budget_code  where CODE='"+id+"' AND cat='"+cat.getSelectedItem()+"'";
        
         pst = con.prepareStatement(sql);
      //   pst.setString(1,id.getText());
         pst.executeUpdate();
        // JOptionPane.showMessageDialog(null,"delete");
              
     }catch(SQLException | HeadlessException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }
         item.setText("");
        code.setText("");
        id="";
    }
     
     public void Table()
    {
         try{
           
             String sql="SELECT `CAT`, `ITEM`, `CODE` FROM `budget_code` where cat='"+cat.getSelectedItem()+"' order by code";
     //  String sql="SELECT ID, `NUM_FACTURE`, `DATE_FACTURE`, `DESCRIPTION`, `DEBIT_QTY`, `DEBIT_PU`, `DEBIT_PT`, `DEBIT_TVA`, `CREDIT_QTY`, `CREDIT_PU`, `CREDIT_PT`, `CREDIT_TVA`, `DATES`, `NUM` FROM facture_fournisseur where compte='"+jComboBox6.getSelectedItem()+"'";
       pst = con.prepareStatement(sql);
      rs= pst.executeQuery();
      
       
       jTable1.setModel(DbUtils.resultSetToTableModel(rs));
       mode=new DefaultTableModel();
       
       
        TableColumn col0=jTable1.getColumnModel().getColumn(0);
        TableColumn col1=jTable1.getColumnModel().getColumn(1);
        TableColumn col2=jTable1.getColumnModel().getColumn(2);

       
      
       
       col0.setPreferredWidth(100);
       col1.setPreferredWidth(150);
       col2.setPreferredWidth(80);
      
      
      
       
       
      // jTable1.setModel(mode);
       
     
    }catch(SQLException ex ){
     JOptionPane.showMessageDialog(null, ex);
}
    }
     
     
      public void Tables()
    {
         try{
           
             String sql="SELECT `CAT`, `ITEM`, `CODE` FROM `budget_code` where code like '"+jTextField1.getText()+"%' and cat='"+cat.getSelectedItem()+"' order by code";
     //  String sql="SELECT ID, `NUM_FACTURE`, `DATE_FACTURE`, `DESCRIPTION`, `DEBIT_QTY`, `DEBIT_PU`, `DEBIT_PT`, `DEBIT_TVA`, `CREDIT_QTY`, `CREDIT_PU`, `CREDIT_PT`, `CREDIT_TVA`, `DATES`, `NUM` FROM facture_fournisseur where compte='"+jComboBox6.getSelectedItem()+"'";
       pst = con.prepareStatement(sql);
      rs= pst.executeQuery();
      
       
       jTable1.setModel(DbUtils.resultSetToTableModel(rs));
       mode=new DefaultTableModel();
       
       
        TableColumn col0=jTable1.getColumnModel().getColumn(0);
        TableColumn col1=jTable1.getColumnModel().getColumn(1);
        TableColumn col2=jTable1.getColumnModel().getColumn(2);

       
      
       
       col0.setPreferredWidth(100);
       col1.setPreferredWidth(150);
       col2.setPreferredWidth(80);
      
      
      
       
       
      // jTable1.setModel(mode);
       
     
    }catch(SQLException ex ){
     JOptionPane.showMessageDialog(null, ex);
}
    }
     
     
     String id="";
         public void select_jTable()
   {
        try{
          int row= jTable1.getSelectedRow();
          String Table_click = (jTable1.getModel().getValueAt(row,2). toString());
          String sql = "SELECT * FROM budget_code WHERE CODE= '"+Table_click+"' AND cat='"+cat.getSelectedItem()+"'";
          pst = con.prepareStatement(sql);
          rs=pst.executeQuery();
          if(rs.next()){
          //`ID`, ``, ``, ``, ``, ``, ``, ``, ``, ``, ``, ``, ``
              
            
              String add1 = rs.getString("ITEM");
              item.setText(add1);
               String add2 = rs.getString("CODE");
              code.setText(add2);
              id=add2;
              
          }
         
    }catch(SQLException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }
   }

                     
             public void save_uploading_file(){
             int row= jTable1.getSelectedRow();
         // String rows =jTable1.getName()
  
      try{  
       BufferedReader br=new BufferedReader(new FileReader(path));
       String line;
       while((line=br.readLine())!=null){
       String[]value=line.split(",");
     String replaceStrings=value[1].replace("é", "e");
        String sql="INSERT INTO `budget_code`(`CAT`, `ITEM`, `CODE`) VALUES ('"+value[0]+"','"+replaceStrings+"','"+value[2]+"')";
 pst=con.prepareStatement(sql);
  pst.executeUpdate();
        }
  br.close();
 // JOptionPane.showMessageDialog(null,"Datas Saved");
      } catch(Exception e){
             JOptionPane.showMessageDialog(null, e);  }
 
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
        label1 = new java.awt.Label();
        label2 = new java.awt.Label();
        label3 = new java.awt.Label();
        cat = new javax.swing.JComboBox<>();
        item = new javax.swing.JTextField();
        code = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        search = new javax.swing.JComboBox<>();
        jTextField1 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        label1.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        label1.setText("Project");
        jPanel2.add(label1, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 12, -1, -1));

        label2.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        label2.setText("Code");
        jPanel2.add(label2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, -1, -1));

        label3.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        label3.setText("Item");
        jPanel2.add(label3, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 42, -1, -1));

        cat.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        cat.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                catPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        jPanel2.add(cat, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 10, 390, -1));

        item.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jPanel2.add(item, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 40, 390, -1));

        code.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jPanel2.add(code, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 70, 170, -1));

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton1.setText("OK");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 70, 90, -1));

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton2.setText("DEL");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 70, 60, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_Microsoft_Excel_16px.png"))); // NOI18N
        jLabel1.setText("Excel");
        jLabel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 70, 60, 30));

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

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

        search.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        search.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "CAT", "ITEM", "CODE" }));

        jTextField1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 446, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 470, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
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

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
       // TODO add your handling code here:
    }//GEN-LAST:event_formWindowOpened

    private void catPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_catPopupMenuWillBecomeInvisible
Table();        // TODO add your handling code here:
    }//GEN-LAST:event_catPopupMenuWillBecomeInvisible

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
if(id==""){
    savemateriel(); 
Table();  
}else{
updatemateriel(); 
Table();  
}
              // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
select_jTable();        // TODO add your handling code here:
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
delete(); 
Table(); // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
imports();        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
Tables();        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1KeyReleased

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
            java.util.logging.Logger.getLogger(codebudget.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(codebudget.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(codebudget.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(codebudget.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new codebudget().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cat;
    private javax.swing.JTextField code;
    private javax.swing.JTextField item;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private java.awt.Label label1;
    private java.awt.Label label2;
    private java.awt.Label label3;
    private javax.swing.JComboBox<String> search;
    // End of variables declaration//GEN-END:variables
}
