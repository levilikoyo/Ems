/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intreprisemanagementsystem;


import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author DOSHE
 */
public class contract_on extends javax.swing.JInternalFrame {
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
    public contract_on() {
        initComponents();
              con=JavaDbConnect.dbConnect();
        call_in_table2();
        call_in_table_AFTER_DATE();
        call_in_table_AFTER_DATE_active();
        
    }
    
    public void delete()
    {
       
       
         try{
        String sql = "UPDATE contract SET `DATE_IN`='',`DATE_OUT`='' where roll='"+roll.getText()+"' and CONTRACT='"+contract.getText()+"'";
        
         pst = con.prepareStatement(sql);
       //  pst.setString(1,id.getText());
         pst.executeUpdate();
          
              
     }catch(SQLException | HeadlessException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }
   call_in_table_AFTER_DATE();
   
    
         try{
        String sql = "DELETE FROM `contract_print` where roll='"+roll.getText()+"' and CONTRACT='"+contract.getText()+"'";
        
         pst = con.prepareStatement(sql);
       //  pst.setString(1,id.getText());
         pst.executeUpdate();
          
              
     }catch(SQLException | HeadlessException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }
   refresh();
    }
    
    public void refresh(){
    
    contract.setText("");
    webDateField1.setText("");
    webDateField2.setText("");
    name.setText("");
    lname.setText("");
   roll.setText("");
   
    
    }
    public void call_in_table2(){
     
      try{
           
             String sql="SELECT `CONTRACT`, `NAME`, `POSTE`, `ROLL` FROM `contract` ORDER BY CONTRACT,NAME";
     //  String sql="SELECT ID, `NUM_FACTURE`, `DATE_FACTURE`, `DESCRIPTION`, `DEBIT_QTY`, `DEBIT_PU`, `DEBIT_PT`, `DEBIT_TVA`, `CREDIT_QTY`, `CREDIT_PU`, `CREDIT_PT`, `CREDIT_TVA`, `DATES`, `NUM` FROM facture_fournisseur where compte='"+jComboBox6.getSelectedItem()+"'";
       pst = con.prepareStatement(sql);
      rs= pst.executeQuery();
      
       
       jTable1.setModel(DbUtils.resultSetToTableModel(rs));
       mode=new DefaultTableModel();
       
       
        TableColumn col0=jTable1.getColumnModel().getColumn(0);
        TableColumn col1=jTable1.getColumnModel().getColumn(1);
        TableColumn col2=jTable1.getColumnModel().getColumn(2);
        TableColumn col3=jTable1.getColumnModel().getColumn(3);
       // TableColumn col4=jTable1.getColumnModel().getColumn(4);
        
       
       
      
       
       col0.setPreferredWidth(150);
       col1.setPreferredWidth(130);
       col2.setPreferredWidth(100);
       col3.setPreferredWidth(80);
       //col4.setPreferredWidth(100);
       
      
      
       
       
      // jTable1.setModel(mode);
       
     
    }catch(SQLException ex ){
     JOptionPane.showMessageDialog(null, ex);
}
     
     }
    
    public void call_in_table_AFTER_DATE(){
     
      try{
           
             String sql="SELECT  CONTRACT,NAME,DATE_IN,DATE_OUT FROM contract where DATE_IN is not null and TRIM(DATE_IN)<>''";
     //  String sql="SELECT ID, `NUM_FACTURE`, `DATE_FACTURE`, `DESCRIPTION`, `DEBIT_QTY`, `DEBIT_PU`, `DEBIT_PT`, `DEBIT_TVA`, `CREDIT_QTY`, `CREDIT_PU`, `CREDIT_PT`, `CREDIT_TVA`, `DATES`, `NUM` FROM facture_fournisseur where compte='"+jComboBox6.getSelectedItem()+"'";
       pst = con.prepareStatement(sql);
      rs= pst.executeQuery();
      
       
       jTable2.setModel(DbUtils.resultSetToTableModel(rs));
       mode=new DefaultTableModel();
       
       
        TableColumn col0=jTable2.getColumnModel().getColumn(0);
        TableColumn col1=jTable2.getColumnModel().getColumn(1);
        TableColumn col2=jTable2.getColumnModel().getColumn(2);
        TableColumn col3=jTable2.getColumnModel().getColumn(3);
       // TableColumn col4=jTable1.getColumnModel().getColumn(4);
        
       
       
      
       
       col0.setPreferredWidth(150);
       col1.setPreferredWidth(130);
       col2.setPreferredWidth(100);
       col3.setPreferredWidth(80);
       //col4.setPreferredWidth(100);
       
      
      
       
       
      // jTable1.setModel(mode);
       
     
    }catch(SQLException ex ){
     JOptionPane.showMessageDialog(null, ex);
}
     
     }
    
     public void call_in_table_AFTER_DATE_activesearch(){
     
      try{
           
             String sql="SELECT CONTRACT,NAME,POSTE,ROLL,ACTIVE,DATE_IN,DATE_OUT,PROJECT,SALAIRE FROM contract where CONTRACT like '"+unlocksearch.getText()+"%' or NAME like '"+unlocksearch.getText()+"%' or POSTE like '"+unlocksearch.getText()+"%' or ROLL like '"+unlocksearch.getText()+"%' or ACTIVE like '"+unlocksearch.getText()+"%' or DATE_IN like '"+unlocksearch.getText()+"%' or DATE_OUT like '"+unlocksearch.getText()+"%' or PROJECT like '"+unlocksearch.getText()+"%' or SALAIRE like '"+unlocksearch.getText()+"%' and DATE_IN is not null and TRIM(DATE_IN)<>''";
     //  String sql="SELECT ID, `NUM_FACTURE`, `DATE_FACTURE`, `DESCRIPTION`, `DEBIT_QTY`, `DEBIT_PU`, `DEBIT_PT`, `DEBIT_TVA`, `CREDIT_QTY`, `CREDIT_PU`, `CREDIT_PT`, `CREDIT_TVA`, `DATES`, `NUM` FROM facture_fournisseur where compte='"+jComboBox6.getSelectedItem()+"'";
       pst = con.prepareStatement(sql);
      rs= pst.executeQuery();
      
       
       jTable3.setModel(DbUtils.resultSetToTableModel(rs));
       mode=new DefaultTableModel();
       
       
        TableColumn col0=jTable3.getColumnModel().getColumn(0);
        TableColumn col1=jTable3.getColumnModel().getColumn(1);
        TableColumn col2=jTable3.getColumnModel().getColumn(2);
        TableColumn col3=jTable3.getColumnModel().getColumn(3);
        TableColumn col4=jTable3.getColumnModel().getColumn(4);
        
         TableColumn col5=jTable3.getColumnModel().getColumn(5);
        TableColumn col6=jTable3.getColumnModel().getColumn(6);
        TableColumn col7=jTable3.getColumnModel().getColumn(7);
        TableColumn col8=jTable3.getColumnModel().getColumn(8);
       // TableColumn col9=jTable3.getColumnModel().getColumn(9);
        
       
       
      
    //`CONTRACT`, `NAME`, `POSTE`, `ROLL`, `ACTIVE`, `DATE_IN`, `DATE_OUT`, `PROJECT`, `SALAIRE`   
       col0.setPreferredWidth(130);
       col1.setPreferredWidth(130);
       col2.setPreferredWidth(100);
       col3.setPreferredWidth(50);
       col4.setPreferredWidth(50);
       
       col5.setPreferredWidth(50);
       col6.setPreferredWidth(50);
       col7.setPreferredWidth(100);
       col8.setPreferredWidth(50);
     //  col9.setPreferredWidth(80);
       
      
      
       
       
      // jTable1.setModel(mode);
       
     
    }catch(SQLException ex ){
     JOptionPane.showMessageDialog(null, ex);
}
     
     }
    
    
     public void call_in_table_AFTER_DATE_active(){
     
      try{
           
             String sql="SELECT CONTRACT,NAME,POSTE,ROLL,ACTIVE,DATE_IN,DATE_OUT,PROJECT,SALAIRE FROM contract where DATE_IN is not null and TRIM(DATE_IN)<>''";
     //  String sql="SELECT ID, `NUM_FACTURE`, `DATE_FACTURE`, `DESCRIPTION`, `DEBIT_QTY`, `DEBIT_PU`, `DEBIT_PT`, `DEBIT_TVA`, `CREDIT_QTY`, `CREDIT_PU`, `CREDIT_PT`, `CREDIT_TVA`, `DATES`, `NUM` FROM facture_fournisseur where compte='"+jComboBox6.getSelectedItem()+"'";
       pst = con.prepareStatement(sql);
      rs= pst.executeQuery();
      
       
       jTable3.setModel(DbUtils.resultSetToTableModel(rs));
       mode=new DefaultTableModel();
       
       
        TableColumn col0=jTable3.getColumnModel().getColumn(0);
        TableColumn col1=jTable3.getColumnModel().getColumn(1);
        TableColumn col2=jTable3.getColumnModel().getColumn(2);
        TableColumn col3=jTable3.getColumnModel().getColumn(3);
        TableColumn col4=jTable3.getColumnModel().getColumn(4);
        
         TableColumn col5=jTable3.getColumnModel().getColumn(5);
        TableColumn col6=jTable3.getColumnModel().getColumn(6);
        TableColumn col7=jTable3.getColumnModel().getColumn(7);
        TableColumn col8=jTable3.getColumnModel().getColumn(8);
       // TableColumn col9=jTable3.getColumnModel().getColumn(9);
        
       
       
      
    //`CONTRACT`, `NAME`, `POSTE`, `ROLL`, `ACTIVE`, `DATE_IN`, `DATE_OUT`, `PROJECT`, `SALAIRE`   
       col0.setPreferredWidth(130);
       col1.setPreferredWidth(130);
       col2.setPreferredWidth(100);
       col3.setPreferredWidth(50);
       col4.setPreferredWidth(50);
       
       col5.setPreferredWidth(50);
       col6.setPreferredWidth(50);
       col7.setPreferredWidth(100);
       col8.setPreferredWidth(50);
     //  col9.setPreferredWidth(80);
       
      
      
       
       
      // jTable1.setModel(mode);
       
     
    }catch(SQLException ex ){
     JOptionPane.showMessageDialog(null, ex);
}
     
     }
    
    
    public void UPdate(){
        int indexs[]=jTable1.getSelectedRows();
       
        for(int i=0; i < indexs.length;i++){
          //  webDateField1.setText("");
//webDateField2.setText("");
        String Table_clicks = (jTable1.getModel().getValueAt(indexs[i],0). toString());
         String Table_click = (jTable1.getModel().getValueAt(indexs[i],3). toString());
             try{
          String sql = "SELECT * FROM  contract WHERE roll= '"+Table_click+"' and  CONTRACT = '"+Table_clicks+"'";
          pst = con.prepareStatement(sql);
          rs=pst.executeQuery();
          if(rs.next()){
            
          //`ID`, `NOMS`, `LNAME`, `ROLL`, `ADDRESS`, `TEL`, `MAIL`, `GENDER`, `TITLE`, `DEPARTEMENT`, `PHOTOS`, `LOCKS`
             String sum=rs.getString("CONTRACT");
                 contract.setText(sum);
                 
                 String sum1=rs.getString("NAME");
               name.setText(sum1);
                 
                 String sum2=rs.getString("LNAME");
                 lname.setText(sum2);
                 
                  String sum22=rs.getString("ROLL");
                 roll.setText(sum22);
                 
                }
         
    }catch(SQLException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }
            
            
         try {
         
        PreparedStatement pst = con.prepareStatement("UPDATE contract SET `DATE_IN`=?,`DATE_OUT`=? where roll='"+roll.getText()+"' and CONTRACT='"+contract.getText()+"'");
       
        pst.setString(1, webDateField1.getText());
         pst.setString(2, webDateField2.getText());
        
        
          pst.executeUpdate();
        
                
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
         insert();
        // call_in_table();
     //refresh();
     }
         JOptionPane.showMessageDialog(null,"Data Saved");
    }

    public void show_photo_from_db()
               
   {
            try{
          int row= jTable1.getSelectedRow();
          String Table_clicks = (jTable1.getModel().getValueAt(row,0). toString());
          String Table_click = (jTable1.getModel().getValueAt(row,3). toString());
          String sql = "SELECT * FROM  contract WHERE roll= '"+Table_click+"' and  CONTRACT = '"+Table_clicks+"'";
          pst = con.prepareStatement(sql);
          rs=pst.executeQuery();
          if(rs.next()){
            
          //`ID`, `NOMS`, `LNAME`, `ROLL`, `ADDRESS`, `TEL`, `MAIL`, `GENDER`, `TITLE`, `DEPARTEMENT`, `PHOTOS`, `LOCKS`
             String sum=rs.getString("CONTRACT");
                 contract.setText(sum);
                 
                 String sum1=rs.getString("NAME");
               name.setText(sum1);
                 
                 String sum2=rs.getString("LNAME");
                 lname.setText(sum2);
                 
                  String sum22=rs.getString("ROLL");
                 roll.setText(sum22);
                 
                }
         
    }catch(SQLException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }
   }

    
     public void show_photo_from_db2()
               
   {
            try{
          int row= jTable2.getSelectedRow();
          String Table_clicks = (jTable2.getModel().getValueAt(row,0). toString());
          String Table_click = (jTable2.getModel().getValueAt(row,1). toString());
          String sql = "SELECT * FROM  contract WHERE NAME= '"+Table_click+"' and  CONTRACT = '"+Table_clicks+"'";
          pst = con.prepareStatement(sql);
          rs=pst.executeQuery();
          if(rs.next()){
            
          //`ID`, `NOMS`, `LNAME`, `ROLL`, `ADDRESS`, `TEL`, `MAIL`, `GENDER`, `TITLE`, `DEPARTEMENT`, `PHOTOS`, `LOCKS`
             String sum=rs.getString("CONTRACT");
                 contract.setText(sum);
                 
                 String sum1=rs.getString("NAME");
               name.setText(sum1);
                 
                 String sum2=rs.getString("LNAME");
                 lname.setText(sum2);
                 
                  String sum22=rs.getString("ROLL");
                 roll.setText(sum22);
                 
                 String sum2D=rs.getString("DATE_IN");
                 webDateField1.setText(sum2D);
                 
                 String sum2A=rs.getString("DATE_OUT");
                 webDateField2.setText(sum2A);
                 
                }
         
    }catch(SQLException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }
   }
     
     
       public void unlock(){
          try {
         
        PreparedStatement pst = con.prepareStatement("UPDATE contract SET ACTIVE=? WHERE ROLL='"+unlockroll.getText()+"' and CONTRACT='"+unlockcontract.getText()+"'");
 //`NOMS`, `LNAME`, `ROLL`, `ADDRESS`, `TEL`, `MAIL`, `GENDER`, `TITLE`, `DEPARTEMENT`, `LOCK`      
        pst.setString(1, "Active");
        
 
          pst.executeUpdate();
        
             //    JOptionPane.showMessageDialog(null,"Data Saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
    
    }
    public void unlock_all(){
          try {
         
        PreparedStatement pst = con.prepareStatement("UPDATE contract SET ACTIVE=?");
 //`NOMS`, `LNAME`, `ROLL`, `ADDRESS`, `TEL`, `MAIL`, `GENDER`, `TITLE`, `DEPARTEMENT`, `LOCK`      
        pst.setString(1, "Active");
        
 
          pst.executeUpdate();
        
             //    JOptionPane.showMessageDialog(null,"Data Saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
    
    }
    
    
     public void lock(){
          try {
         
        PreparedStatement pst = con.prepareStatement("UPDATE contract SET ACTIVE=? WHERE ROLL='"+unlockroll.getText()+"' and CONTRACT='"+unlockcontract.getText()+"'");
 //`NOMS`, `LNAME`, `ROLL`, `ADDRESS`, `TEL`, `MAIL`, `GENDER`, `TITLE`, `DEPARTEMENT`, `LOCK`      
        pst.setString(1, "");
        
 
          pst.executeUpdate();
        
             //    JOptionPane.showMessageDialog(null,"Data Saved");
    } catch (SQLException ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
    
    }
    public void lock_all(){
          try {
         
        PreparedStatement pst = con.prepareStatement("UPDATE contract SET ACTIVE=?");
 //`NOMS`, `LNAME`, `ROLL`, `ADDRESS`, `TEL`, `MAIL`, `GENDER`, `TITLE`, `DEPARTEMENT`, `LOCK`      
        pst.setString(1, "");
        
 
          pst.executeUpdate();
        
             //    JOptionPane.showMessageDialog(null,"Data Saved");
    } catch (SQLException ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
    
    }
    
    
     public void show_photo_from_db_LOCK()
               
   {
            try{
          int row= jTable3.getSelectedRow();
          String Table_clicks = (jTable3.getModel().getValueAt(row,0). toString());
          String Table_click = (jTable3.getModel().getValueAt(row,3). toString());
          String sql = "SELECT * FROM  contract WHERE roll= '"+Table_click+"' and  CONTRACT = '"+Table_clicks+"'";
          pst = con.prepareStatement(sql);
          rs=pst.executeQuery();
          if(rs.next()){
            
          //`ID`, `NOMS`, `LNAME`, `ROLL`, `ADDRESS`, `TEL`, `MAIL`, `GENDER`, `TITLE`, `DEPARTEMENT`, `PHOTOS`, `LOCKS`
             String sum=rs.getString("CONTRACT");
                 unlockcontract.setText(sum);
                 
                 String sum1=rs.getString("NAME");
               unlockname.setText(sum1);
                 
                 
                 
                  String sum22=rs.getString("ROLL");
                 unlockroll.setText(sum22);
                 
                }
         
    }catch(SQLException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }
   }
     
     String CONTRACT;
     String NAME;
     String LNAME;
     String ADDRESS;
     String MARIAGE;
     String CONJOINT;
     String POSTE;
     String INSS;
     String ROLL;
     String DATE_IN;
     String DATE_OUT;
     String PROJECT;
     String SALAIRE;
     String NAMES;
     String AFF;
     String BAYEUR;
     String COLAPSE,MONTH,NATIONALITY;
     public void call_from_where_ever(){
     
     try{
            String sql="SELECT * FROM `contract` WHERE ROLL='"+roll.getText()+"'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                CONTRACT=rs.getString("CONTRACT");
                NAME=rs.getString("NAME");
                LNAME=rs.getString("LNAME");
                ADDRESS=rs.getString("ADDRESS");
                MARIAGE=rs.getString("MARIAGE");
                CONJOINT=rs.getString("CONJOINT");
                POSTE=rs.getString("POSTE");
                INSS=rs.getString("INSS");
                ROLL=rs.getString("ROLL");
                DATE_IN=rs.getString("DATE_IN");
                DATE_OUT=rs.getString("DATE_OUT");
                PROJECT=rs.getString("PROJECT");
                SALAIRE=rs.getString("SALAIRE");
                name.setText(SALAIRE);
                
                COLAPSE=rs.getString("COLAPSE");
                MONTH=rs.getString("MONTH");
                
                BAYEUR=rs.getString("BAYEUR");
                AFF=rs.getString("AFF");
               NATIONALITY=rs.getString("NATIONALITY");
               
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
     
      try{
            String sql="SELECT count(ID) FROM `all_fam_listchild` WHERE  ROLL='"+roll.getText()+"'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                NAMES=rs.getString("count(ID)");
                //DOB=rs.getString("DOB");
                
               
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
      
      
     
     }
     
     public void insert(){
         call_from_where_ever();
     
          try {
        PreparedStatement pst = con.prepareStatement("INSERT INTO `contract_print`(`CONTRACT`, `NAME`, `LNAME`, `ADDRESS`, `MARIAGE`, `CONJOINT`, `POST`, `INSS`, `ROLL`, `DATE_IN`, `DATE_OUT`, `PROJECT`, `SALAIRE`, `NAMES`, `BAYEUR`, `AFF`,`COLAPSE`, `MONTH`, `NATIONALITY`)"
                +"value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ");
       //,,,,,,,,,,,,,,;
        
        pst.setString(1,CONTRACT);
         pst.setString(2,NAME);
         pst.setString(3,LNAME);
         pst.setString(4,ADDRESS);
         pst.setString(5,MARIAGE);
         pst.setString(6,CONJOINT);
          pst.setString(7,POSTE);
           pst.setString(8,INSS);
         pst.setString(9,ROLL);
         pst.setString(10,DATE_IN);
         pst.setString(11,DATE_OUT);
         pst.setString(12,PROJECT);
         pst.setString(13,SALAIRE);
          pst.setString(14,NAMES);
          
          pst.setString(15,BAYEUR);
          pst.setString(16,AFF);
          
          pst.setString(17,COLAPSE);
          pst.setString(18,MONTH);
           pst.setString(19,NATIONALITY);
        
        
        
        
         pst.executeUpdate();
        // showTableData();
      
         
               //  JOptionPane.showMessageDialog(null,"data saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
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

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        webDateField2 = new com.alee.extended.date.WebDateField();
        jLabel3 = new javax.swing.JLabel();
        webDateField1 = new com.alee.extended.date.WebDateField();
        jLabel2 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        contract = new Palette.TextField();
        name = new Palette.TextField();
        lname = new Palette.TextField();
        roll = new Palette.TextField();
        jPanel4 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        unlocksearch = new Palette.TextField();
        unlockcontract = new Palette.TextField();
        unlockname = new Palette.TextField();
        unlockroll = new Palette.TextField();
        jComboBox1 = new Palette.Combobox();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();

        setTitle("Contract On And  Off");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_Purchase_Order_16px.png"))); // NOI18N

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTabbedPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane1MouseClicked(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

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
        jTable1.setRowHeight(30);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 699, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
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
        jTable2.setRowHeight(30);
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Contract Management", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N

        webDateField2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("Date In:");

        webDateField1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("Date In:");

        jButton2.setText("Save");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton1.setText("Delete");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        contract.setEditable(false);
        contract.setLabelText("Type de contrats");

        name.setEditable(false);
        name.setLabelText("Nom de l’employé");

        lname.setEditable(false);
        lname.setLabelText("Prénom de l’employé");

        roll.setEditable(false);
        roll.setLabelText("Numéro matricule");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(webDateField1, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(webDateField2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(contract, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lname, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(roll, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 61, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(contract, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(webDateField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(webDateField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(roll, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton1)
                        .addComponent(jButton2)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("durée du contrat", jPanel1);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

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
        jTable3.setRowHeight(30);
        jTable3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable3MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTable3);

        jButton3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton3.setText("Lock");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton4.setText("UnLock");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        unlocksearch.setLabelText("Recherche");
        unlocksearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                unlocksearchKeyReleased(evt);
            }
        });

        unlockcontract.setEditable(false);
        unlockcontract.setLabelText("Contrat");

        unlockname.setEditable(false);
        unlockname.setLabelText("Nom de l’employé");

        unlockroll.setEditable(false);
        unlockroll.setLabelText("Matricule");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "One By One", "All" }));
        jComboBox1.setLabeText("Mode de selection");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(unlockcontract, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(unlockname, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(unlockroll, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4)
                        .addGap(0, 56, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(unlocksearch, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(unlocksearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 335, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(unlockcontract, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(unlockname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(unlockroll, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3)
                    .addComponent(jButton4))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Activation du contrat", jPanel4);

        jMenu1.setText("X");
        jMenu1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu1MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1081, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 502, Short.MAX_VALUE)
        );

        setBounds(100, 10, 1093, 561);
    }// </editor-fold>//GEN-END:initComponents

    private void jMenu1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu1MouseClicked
this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu1MouseClicked

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
webDateField1.setText("");
webDateField2.setText("");
        show_photo_from_db();        // TODO add your handling code here:
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
UPdate(); 
//insert();
call_in_table_AFTER_DATE();
refresh();// TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
//call_from_where_ever();
 delete();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
webDateField1.setText("");
webDateField2.setText("");
        show_photo_from_db2();        // TODO add your handling code here:
    }//GEN-LAST:event_jTable2MouseClicked

    private void jTable3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable3MouseClicked
show_photo_from_db_LOCK();        // TODO add your handling code here:
    }//GEN-LAST:event_jTable3MouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
if(jComboBox1.getSelectedItem().equals("One By One")){
unlock(); 
}else{
unlock_all();
}
call_in_table_AFTER_DATE_active();
               // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
if(jComboBox1.getSelectedItem().equals("One By One")){
lock(); 
}else{
lock_all();
}   
call_in_table_AFTER_DATE_active();// TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTabbedPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane1MouseClicked
call_in_table_AFTER_DATE_active();      // TODO add your handling code here:
    }//GEN-LAST:event_jTabbedPane1MouseClicked

    private void unlocksearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_unlocksearchKeyReleased
call_in_table_AFTER_DATE_activesearch();         // TODO add your handling code here:
    }//GEN-LAST:event_unlocksearchKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Palette.TextField contract;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private Palette.Combobox jComboBox1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private Palette.TextField lname;
    private Palette.TextField name;
    private Palette.TextField roll;
    private Palette.TextField unlockcontract;
    private Palette.TextField unlockname;
    private Palette.TextField unlockroll;
    private Palette.TextField unlocksearch;
    private com.alee.extended.date.WebDateField webDateField1;
    private com.alee.extended.date.WebDateField webDateField2;
    // End of variables declaration//GEN-END:variables
}
