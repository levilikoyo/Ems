/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intreprisemanagementsystem;

import com.alee.laf.WebLookAndFeel;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.table.DefaultTableCellRenderer;
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
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author DOSHE
 */
public class warehouse_ohada extends javax.swing.JFrame {

     DefaultTableModel mode;
Connection con=null;
PreparedStatement pst=null;
ResultSet rs= null;
String etrolls,SUBSTR;
    public warehouse_ohada() {
        initComponents();
        con=JavaDbConnectUMCO.dbConnect();
          setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("icons8_Raspberry_Pi_48px_1.png")));
          attLIST_from_EMPLOYEView();
          attLIST_from_EMPLOYEView_used();
    }
  public void attLIST_from_EMPLOYEView()
    {
        try{
         // String Table_click = (jList2.getModel().getSelectedItem(tmps,0). toString());
       String sql="select `numero_compte` AS 'CODE', `intitule_compte` AS 'ACCOUNT DESCRIPTION', `class_compte`AS 'CLASS' from  parametre_default";
       pst = con.prepareStatement(sql);
      rs= pst.executeQuery();
     
       jTable1.setModel(DbUtils.resultSetToTableModel(rs));
       DefaultTableCellRenderer centre =new DefaultTableCellRenderer();
       TableColumn col0=jTable1.getColumnModel().getColumn(0);
       
        TableColumn col1=jTable1.getColumnModel().getColumn(1);
        TableColumn col2=jTable1.getColumnModel().getColumn(2);
        
       
       
      
       col0.setPreferredWidth(100);
       col1.setPreferredWidth(500);
       col2.setPreferredWidth(100);
       
      centre.setHorizontalAlignment(JLabel.CENTER);
        jTable1.getColumnModel().getColumn(0).setCellRenderer(centre); 
        centre.setHorizontalAlignment(JLabel.CENTER);
        jTable1.getColumnModel().getColumn(2).setCellRenderer(centre);
       
      
         
     rs.close();
   pst.close();
    }catch(SQLException ex ){
     JOptionPane.showMessageDialog(null, ex);
}
}
   public void attLIST_from_EMPLOYEView_used()
    {
        try{
       
      
     
         // String Table_click = (jList2.getModel().getSelectedItem(tmps,0). toString());
       String sql="SELECT  CODE,`NAME` AS 'ACCOUNT DESCRIPTION', `CLASS` FROM `ohada` ";
       pst = con.prepareStatement(sql);
      rs= pst.executeQuery();
     
       jTable3.setModel(DbUtils.resultSetToTableModel(rs));
       DefaultTableCellRenderer centre =new DefaultTableCellRenderer();
       TableColumn col0=jTable3.getColumnModel().getColumn(0);
       
        TableColumn col1=jTable3.getColumnModel().getColumn(1);
        TableColumn col2=jTable3.getColumnModel().getColumn(2);
      //  TableColumn col3=jTable3.getColumnModel().getColumn(3);
        
       
       
      
       col0.setPreferredWidth(100);
       col1.setPreferredWidth(500);
       col2.setPreferredWidth(100);
       
      centre.setHorizontalAlignment(JLabel.CENTER);
        jTable3.getColumnModel().getColumn(0).setCellRenderer(centre); 
        centre.setHorizontalAlignment(JLabel.CENTER);
        jTable3.getColumnModel().getColumn(2).setCellRenderer(centre);
       
      
        
     rs.close();
   pst.close();
    }catch(SQLException ex ){
     JOptionPane.showMessageDialog(null, ex);
}
}
   
   public void save(){
       substring();
    
     try{
         String sql="Insert into ohada (NAME,CODE,COMPTEMERE,CODEMERE,CLASS,SUBSTR)"+
                "values(?,?,?,?,?,?)";
    
     pst=con.prepareStatement(sql);
     
     pst.setString(1,bus.getText());
     pst.setString(2,code.getText()); 
     pst.setString(3,compte.getText()); 
     pst.setString(4,codem.getText());
     pst.setString(5,classe.getText());
      pst.setString(6,SUBSTR);
      pst.executeUpdate();
      JOptionPane.showMessageDialog(null,"Transaction Saved"); 
      
     
     rs.close();
   pst.close();
     }catch(Exception ex){JOptionPane.showMessageDialog(null,ex);}
     
    }
     public void tableselected(){
         
         
          try{
          int row= jTable1.getSelectedRow();
         // String rows =jTable1.getName()
          String Table_click = (jTable1.getModel().getValueAt(row,0). toString());
          String sql = "SELECT * FROM parametre_default WHERE numero_compte= '"+Table_click+"'";
          pst = con.prepareStatement(sql);
          rs=pst.executeQuery();
          if(rs.next()){
        
               String add11= rs.getString("intitule_compte");
              compte.setText(add11);
               String add12= rs.getString("numero_compte");
             codem.setText(add12);
               String add13= rs.getString("class_compte");
              classe.setText(add13);
          }
         rs.close();
   pst.close();
    }catch(SQLException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }
         
         substring();
         }
      public void tableselected_ohada(){
         
         
          try{
          int row= jTable3.getSelectedRow();
         // String rows =jTable1.getName()
          String Table_click = (jTable3.getModel().getValueAt(row,0). toString());
          String sql = "SELECT * FROM ohada WHERE CODE= '"+Table_click+"'";
          pst = con.prepareStatement(sql);
          rs=pst.executeQuery();
          if(rs.next()){
        //`NAME`, `CODE`, `COMPTEMERE`, `CODEMERE`, `CLASS`,SUBSTR 
               String add11= rs.getString("NAME");
              bus.setText(add11);
               String add14= rs.getString("COMPTEMERE");
              compte.setText(add14);
               String add12= rs.getString("CODE");
             code.setText(add12);
             String add15= rs.getString("CODEMERE");
             codem.setText(add15);
               String add13= rs.getString("CLASS");
              classe.setText(add13);
          }
         
     rs.close();
   pst.close();
     
    }catch(SQLException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }
         
         substring();
         }
     
       public void substring(){
    
      try{
       
      
       
       String sql="SELECT substr(numero_compte,1,2) as SUBSTR FROM parametre_default WHERE numero_compte ='"+codem.getText()+"'";
       //select substring(col1, 4)from table1
       pst = con.prepareStatement(sql);
      rs= pst.executeQuery();
      if(rs.next()){
      
              SUBSTR=rs.getString("SUBSTR");
          pst.close();
       
      }
     
     rs.close();
   pst.close();
  // con.close();
     
    }catch(SQLException ex ){
     JOptionPane.showMessageDialog(null, ex);} 
    
    
    }
      
        public void attLIST_from_EMPLOYEsearch()
    {
        String tmp=jComboBox1.getSelectedItem().toString();
        if(jComboBox1.getSelectedItem().equals("Code")){
        tmp="numero_compte";
        }else if(jComboBox1.getSelectedItem().equals("Compte")){
         tmp="intitule_compte";
        }else if(jComboBox1.getSelectedItem().equals("Class")){
         tmp="class_compte";
        }
        
        try{
         // String Table_click = (jList2.getModel().getSelectedItem(tmps,0). toString());
       String sql="select `numero_compte` AS 'CODE', `intitule_compte` AS 'ACCOUNT DESCRIPTION', `class_compte`AS 'CLASS' from  parametre_default where "+tmp+" like '"+checks.getText()+"%'";
       pst = con.prepareStatement(sql);
      rs= pst.executeQuery();
     
       jTable1.setModel(DbUtils.resultSetToTableModel(rs));
       DefaultTableCellRenderer centre =new DefaultTableCellRenderer();
       TableColumn col0=jTable1.getColumnModel().getColumn(0);
       
        TableColumn col1=jTable1.getColumnModel().getColumn(1);
        TableColumn col2=jTable1.getColumnModel().getColumn(2);
        
       
       
      
       col0.setPreferredWidth(100);
       col1.setPreferredWidth(500);
       col2.setPreferredWidth(100);
       
      centre.setHorizontalAlignment(JLabel.CENTER);
        jTable1.getColumnModel().getColumn(0).setCellRenderer(centre); 
        centre.setHorizontalAlignment(JLabel.CENTER);
        jTable1.getColumnModel().getColumn(2).setCellRenderer(centre);
       
       
        rs.close();
   pst.close();
    }catch(SQLException ex ){
     JOptionPane.showMessageDialog(null, ex);
    }
         
    
}
  public void report()
     {
          
     
             try{
              //   String tmp=num.getText();
                 
                 
                 String sqls="Select path from pathn";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
            if(rs.next()){
             
                String sum=rs.getString("path");
                  
                 String NameFiles=sum;
            
            String NameFile=""+NameFiles+"ohada.jrxml";
                 
                 
                 
                JasperDesign jd=JRXmlLoader.load(NameFile);
                 
                 
                 
                // String report ="C:\\Users\\Dosh\\Documents\\NetBeansProjects\\intreprisemanagementsystem\\src\\intreprisemanagementsystem\\maretiauxrepport.jrxml";
                // JasperDesign jd=JRXmlLoader.load("C:\\Users\\Dosh\\Documents\\NetBeansProjects\\intreprisemanagementsystem\\src\\intreprisemanagementsystem\\Fiche_Materiaux_out.jrxml");
                String sql="select * from OHADA order BY COMPTEMERE ";
                JRDesignQuery nq=new JRDesignQuery();
                nq.setText(sql);
                jd.setQuery(nq);
               
                 JasperReport jr =JasperCompileManager.compileReport(jd);
                 JasperPrint jp=JasperFillManager.fillReport(jr,null,con);
                 JasperViewer.viewReport(jp,false);
                 
                 
                 JasperViewer m= new JasperViewer(jp);
                 m.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            }
            
       rs.close();
   pst.close();
     
             }catch(Exception ex){
                  JOptionPane.showMessageDialog(null, ex);   
             }
       
//setDefaultCloseOperation(DISPOSE_ON_CLOSE); 
     }   
  public void refresh(){
 bus.setText("");
 code.setText(""); 
 compte.setText(""); 
 codem.setText("");
 classe.setText("");
  
  
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
        jSeparator12 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        checks = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        compte = new javax.swing.JTextField();
        codem = new javax.swing.JTextField();
        code = new javax.swing.JTextField();
        classe = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jComboBox2 = new javax.swing.JComboBox<>();
        jTextField1 = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Create Business Account", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jSeparator12.setBackground(new java.awt.Color(102, 102, 102));
        jPanel3.add(jSeparator12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 440, 10));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Denomination:");
        jPanel3.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Account");
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Code");
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(281, 90, 40, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Sub Code");
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 90, -1, -1));

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setText("OK");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 440, 150, 30));

        checks.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                checksKeyReleased(evt);
            }
        });
        jPanel3.add(checks, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 150, 250, -1));

        jTable1.setBackground(new java.awt.Color(240, 240, 240));
        jTable1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable1);

        jPanel3.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 440, 250));

        bus.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jPanel3.add(bus, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 440, -1));

        compte.setEditable(false);
        compte.setBackground(new java.awt.Color(240, 240, 241));
        compte.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jPanel3.add(compte, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 190, -1));

        codem.setBackground(new java.awt.Color(240, 240, 241));
        codem.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jPanel3.add(codem, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 110, 80, -1));

        code.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jPanel3.add(code, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 110, 100, -1));

        classe.setEditable(false);
        classe.setBackground(new java.awt.Color(240, 240, 241));
        classe.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jPanel3.add(classe, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 110, 70, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("Class");
        jPanel3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 90, -1, -1));

        jComboBox1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Compte", "Code", "Class" }));
        jPanel3.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 190, -1));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datas", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jTable3.setBackground(new java.awt.Color(240, 240, 240));
        jTable3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable3MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTable3);

        jButton2.setText("Print");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jComboBox2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Compte", "Code", "Class" }));

        jTextField1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jButton3.setText("Del");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 481, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
if(bus.getText().equals("")||code.getText().equals("")){
JOptionPane.showMessageDialog(null,"Wrong Data Item Exists","Error",JOptionPane.ERROR_MESSAGE);
}else{
    try{
        
        save();        
attLIST_from_EMPLOYEView_used();
   //con.close();
    rs.close();
   pst.close();

    }catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex); }
        
   
}
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked

          try{
        
      refresh();
        tableselected();
    rs.close();
   pst.close();

    }catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex); }
              // TODO add your handling code here:
    }//GEN-LAST:event_jTable1MouseClicked

    private void checksKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_checksKeyReleased
  try{
        
       attLIST_from_EMPLOYEsearch();
    rs.close();
   pst.close();

    }catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex); }
            // TODO add your handling code here:
    }//GEN-LAST:event_checksKeyReleased

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
  try{
        
      report();
    rs.close();
   pst.close();

    }catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex); }
              // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTable3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable3MouseClicked
 try{
        
     refresh();
        tableselected_ohada();
    rs.close();
   pst.close();

    }catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex); }
               // TODO add your handling code here:
    }//GEN-LAST:event_jTable3MouseClicked

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
            java.util.logging.Logger.getLogger(warehouse_ohada.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(warehouse_ohada.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(warehouse_ohada.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(warehouse_ohada.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                 WebLookAndFeel.install(true);
                new warehouse_ohada().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static final javax.swing.JTextField bus = new javax.swing.JTextField();
    private javax.swing.JTextField checks;
    private javax.swing.JTextField classe;
    private javax.swing.JTextField code;
    private javax.swing.JTextField codem;
    private javax.swing.JTextField compte;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable3;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
