/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intreprisemanagementsystem;

import com.alee.laf.WebLookAndFeel;
import com.thoughtworks.xstream.io.path.Path;
import java.awt.HeadlessException;
import java.awt.Toolkit;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import javax.swing.DefaultListModel;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Dosh
 */
public class Achive_check extends javax.swing.JFrame {

    Connection con=null;
PreparedStatement pst=null;
ResultSet rs= null;
 DefaultTableModel mode;
 String roll,rolls;
  String CLASSS;
 String NAME,CODE,COMPTEMERE,CODEMERE,CLASS,SUBSTRS,SUBSTR,CATB,SUB_CATB,LBSUB;
 Double CHECK_BUDGET,CHECK_OHADA;
 
//Double bbb=Double.parseDouble(creditbudget.getText());
 Double SUMDEBIT,SUMCREDIT;
// String rolls=null;
String formats=null;
 
    public Achive_check() {
         this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        initComponents();
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("icons8_Raspberry_Pi_48px_1.png")));
        con=JavaDbConnect.dbConnect();
        call();
    }
    
public void calls(){
    
      int row= table2.getSelectedRow();
          String Table_clicks = (table2.getModel().getValueAt(row,2). toString());
         
         link.setText(Table_clicks);
     try{
            String sql="select * from archive where ID ='"+Table_clicks+"'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                String sum1=rs.getString("NUMBRE");
                 numero.setText(sum1);
                 
                  String sum2=rs.getString("PAR");
                 par.setText(sum2);
                 
                  String sum3=rs.getString("A");
                 a.setText(sum3);
                 
                  String sum4=rs.getString("DATER");
                 datel.setText(sum4);
                 
                  String sum5=rs.getString("DATEC");
                 datec.setText(sum5);
                 
                  String sum6=rs.getString("PHYSQUE");
                 physique.setText(sum6);
                 
                 String sumS=rs.getString("LINK");
                 link.setText(sumS);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex); } 
     
    Table_clicks=null;
     
      }
 
public void call(){
    
     try{
            String sql="select * from projet";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                String sum=rs.getString("PROJET_NUM");
                 buss.addItem(sum);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex); } 
     
    
     
      }
    
          
    //INSERT INTO `archive`(`ID`, `NAME`, `NUMBRE`, `PAR`, `A`, `DATER`, `DATEC`, `OBJET`, `LINK`, `BATCH`, `PHYSQUE`, `NUM`)



       
           
            public void clic_attCall_IN_LIST7()
    { 
      //  DefaultListModel list = new DefaultListModel();
  int row= Archi_table1.jTable1.getSelectedRow();
          String Table_clicks = (Archi_table1.jTable1.getModel().getValueAt(row,0). toString());
         try{
          String sql="SELECT Name,NUMBRE,ID FROM archive where Batch='"+batch.getSelectedValue()+"' AND REF='"+Table_clicks+"'";
             
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
             table2.setModel(DbUtils.resultSetToTableModel(rs));
         }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
         
         TableColumn col0=table2.getColumnModel().getColumn(0);
        TableColumn col1=table2.getColumnModel().getColumn(1);
        
        TableColumn col2=table2.getColumnModel().getColumn(2);
       
       
       col0.setPreferredWidth(200);
       col1.setPreferredWidth(200);
       col2.setPreferredWidth(10);
      
    
    }
            public void calltables(){
   journal.jTable1.setDefaultRenderer(Object.class,new PINTAR_ARCHIVE()); 
         
         String tmp = null;
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
         }else if(jComboBox1.getSelectedItem().equals("Archive")){
          tmp="Archive"; 
         }
         
   



 try{
            // `COMPTE_M`, `COMPTE`, `CODE_M`, `CODE`, `CLASSE`, `SUBSTR`, `DEBIT`, `CREDIT`, `NUM`, `DATES`, `BUSS`, `LIBELLE`, `JOURNAL`, `CODE1`, `NUM_FACTURE`, `DEBIT_CDF`, `CREDIT_CDF`    
          //   String tmp=Achive_check.buss.getSelectedItem().toString();
    String sqlS="select `NUM`, `CODE1` as 'CODE',`LIBELLE` AS 'DESCRIPTION OF TRANSACTION',CREDIT AS DEBIT,DEBIT AS CREDIT, `DATES`,ARCHIVE from OHADA_TRANS where "+tmp+" LIKE '"+search.getText()+"%' AND BUSS = '"+buss.getSelectedItem()+"' AND CLASSE= 5";//AND CODE='"+jComboBox1.getSelectedItem()+"' AND DEVICE='"+jComboBox2.getSelectedItem()+"' order by DATES,NUM";
    //`LIBELLE`, `ENTRE`, `SORTIE`, `DATE`, `BUSS`, `USED`, `NUM`
    pst = con.prepareStatement(sqlS);
      rs= pst.executeQuery();
     
       Archi_table1.jTable1.setModel(DbUtils.resultSetToTableModel(rs));
    
             }catch(Exception ex){
                 JOptionPane.showMessageDialog(null, ex);
             }
                 DefaultTableCellRenderer centre =new DefaultTableCellRenderer();
        centre.setHorizontalAlignment(JLabel.CENTER);
         TableColumn col0=Archi_table1.jTable1.getColumnModel().getColumn(0);
        TableColumn col1=Archi_table1.jTable1.getColumnModel().getColumn(1);
          Archi_table1.jTable1.getColumnModel().getColumn(1).setCellRenderer(centre);
        TableColumn col2=Archi_table1.jTable1.getColumnModel().getColumn(2);
        TableColumn col3=Archi_table1.jTable1.getColumnModel().getColumn(3);
          //centre.setHorizontalAlignment(JLabel.CENTER);
            Archi_table1.jTable1.getColumnModel().getColumn(3).setCellRenderer(centre);
        TableColumn col4=Archi_table1.jTable1.getColumnModel().getColumn(4);
         // centre.setHorizontalAlignment(JLabel.CENTER);
         
            Archi_table1.jTable1.getColumnModel().getColumn(4).setCellRenderer(centre);
            //jTable1.getColumnModel().getColumn(4).;
           TableColumn col5=Archi_table1.jTable1.getColumnModel().getColumn(5);
            TableColumn col6=Archi_table1.jTable1.getColumnModel().getColumn(6);
      
       
       col0.setPreferredWidth(60);
       col1.setPreferredWidth(20);
       col2.setPreferredWidth(370);
       col3.setPreferredWidth(20);
       col4.setPreferredWidth(20);
       col5.setPreferredWidth(20);
       col6.setPreferredWidth(20);
            }
            
            
public void calltable(){
     journal.jTable1.setDefaultRenderer(Object.class,new PINTAR_ARCHIVE()); 
    SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String date1 = dateFormats.format(jDateChooser1.getDate());
         String date2 = dateFormats.format(jDateChooser2.getDate());
   
        
       try{
            // `COMPTE_M`, `COMPTE`, `CODE_M`, `CODE`, `CLASSE`, `SUBSTR`, `DEBIT`, `CREDIT`, `NUM`, `DATES`, `BUSS`, `LIBELLE`, `JOURNAL`, `CODE1`, `NUM_FACTURE`, `DEBIT_CDF`, `CREDIT_CDF`    
          //   String tmp=Achive_check.buss.getSelectedItem().toString();
    String sqlS="select `NUM`, `CODE1` as 'CODE',`LIBELLE` AS 'DESCRIPTION OF TRANSACTION',CREDIT AS DEBIT,DEBIT AS CREDIT, `DATES`,ARCHIVE from OHADA_TRANS where DATES BETWEEN '"+date1+"' AND '"+date2+"' AND BUSS = '"+buss.getSelectedItem()+"' AND CLASSE= 5 ORDER BY DATES";//AND CODE='"+jComboBox1.getSelectedItem()+"' AND DEVICE='"+jComboBox2.getSelectedItem()+"' order by DATES,NUM";
    //`LIBELLE`, `ENTRE`, `SORTIE`, `DATE`, `BUSS`, `USED`, `NUM`
    pst = con.prepareStatement(sqlS);
      rs= pst.executeQuery();
     
       Archi_table1.jTable1.setModel(DbUtils.resultSetToTableModel(rs));
    
             }catch(Exception ex){
                 JOptionPane.showMessageDialog(null, ex);
             }
                 DefaultTableCellRenderer centre =new DefaultTableCellRenderer();
        centre.setHorizontalAlignment(JLabel.CENTER);
         TableColumn col0=Archi_table1.jTable1.getColumnModel().getColumn(0);
        TableColumn col1=Archi_table1.jTable1.getColumnModel().getColumn(1);
          Archi_table1.jTable1.getColumnModel().getColumn(1).setCellRenderer(centre);
        TableColumn col2=Archi_table1.jTable1.getColumnModel().getColumn(2);
        TableColumn col3=Archi_table1.jTable1.getColumnModel().getColumn(3);
          //centre.setHorizontalAlignment(JLabel.CENTER);
            Archi_table1.jTable1.getColumnModel().getColumn(3).setCellRenderer(centre);
        TableColumn col4=Archi_table1.jTable1.getColumnModel().getColumn(4);
         // centre.setHorizontalAlignment(JLabel.CENTER);
         
            Archi_table1.jTable1.getColumnModel().getColumn(4).setCellRenderer(centre);
            //jTable1.getColumnModel().getColumn(4).;
           TableColumn col5=Archi_table1.jTable1.getColumnModel().getColumn(5);
            TableColumn col6=Archi_table1.jTable1.getColumnModel().getColumn(6);
      
       
       col0.setPreferredWidth(60);
       col1.setPreferredWidth(20);
       col2.setPreferredWidth(370);
       col3.setPreferredWidth(20);
       col4.setPreferredWidth(20);
       col5.setPreferredWidth(20);
       col6.setPreferredWidth(20);
         }





public void delete1(){

String fileName=link.getText();

try{
Files.delete(Paths.get(fileName));
}catch(IOException e){
e.printStackTrace();
}

}



public void delete(){

  int rows= table2.getSelectedRow();
          String Table_clicks = (table2.getModel().getValueAt(rows,2). toString());


 try {
        PreparedStatement pst = con.prepareStatement("UPDATE `ARCHIVE` SET `SUPRIME`='Yes' where ID='"+Table_clicks+"'");
        
      
         
          pst.executeUpdate();
        
                
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex);
         
    }
           try{
  int rowss= Archi_table1.jTable1.getSelectedRow();
          String Table_clickss = (Archi_table1.jTable1.getModel().getValueAt(rowss,0). toString());
            String sql="select * FROM archive where REF ='"+Table_clickss+"' AND SUPRIME='No'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            if(rs.next()){
               
                
            }else{
              try {
        PreparedStatement pst = con.prepareStatement("UPDATE `ohada_trans` SET `ARCHIVE`='No' where NUM='"+Table_clickss+"' and BUSS='"+buss.getSelectedItem()+"'");
        
      
         
          pst.executeUpdate();
        
                
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
         
    }
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex); } 
 


          
     
        JOptionPane.showMessageDialog(null, "Deletion successful then use the third part"); 
 }
 

//C:\\Users\\Dosh\\Desktop\\HERE\\xxxxxxxxxxxxxxxxxxx-1001pdf
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this jrn. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        search = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jDateChooser1 = new com.alee.extended.date.WebDateField();
        jDateChooser2 = new com.alee.extended.date.WebDateField();
        jButton1 = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JSeparator();
        jPanel7 = new javax.swing.JPanel();
        jSeparator6 = new javax.swing.JSeparator();
        jPanel14 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jScrollPane4 = new javax.swing.JScrollPane();
        boody = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        physique = new javax.swing.JTextField();
        par = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        a = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        numero = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        datel = new javax.swing.JTextField();
        datec = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED, new java.awt.Color(204, 204, 204), new java.awt.Color(102, 102, 102)));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/cancel.png"))); // NOI18N
        jLabel1.setText("Delete");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jSeparator5.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_Refresh_30px.png"))); // NOI18N
        jLabel2.setText("Refresh");
        jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Archivage électronique de pièces justificatives");
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });

        search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchKeyReleased(evt);
            }
        });

        jComboBox1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Libelle", "LB", "CODE", "NUM", "Debit", "Credit", "Archive" }));

        jDateChooser1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jDateChooser1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jDateChooser1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jDateChooser1ActionPerformed(evt);
            }
        });

        jDateChooser2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jDateChooser2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jDateChooser2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jDateChooser2ActionPerformed(evt);
            }
        });

        jButton1.setText("OK");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 6, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jSeparator5)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jSeparator2)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(search)
                    .addComponent(jComboBox1)
                    .addComponent(jDateChooser2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED, new java.awt.Color(204, 204, 204), new java.awt.Color(204, 204, 204)));

        batch.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        batch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                batchMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(batch);

        table2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        table2.setRowHeight(22);
        table2.setShowHorizontalLines(false);
        table2.setShowVerticalLines(false);
        table2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table2MouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(table2);

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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 321, Short.MAX_VALUE))
        );

        buss.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        buss.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select One Project" }));
        buss.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                bussPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator6, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buss, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(buss, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        boody.setBackground(new java.awt.Color(255, 255, 255));
        boody.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED, new java.awt.Color(204, 204, 204), new java.awt.Color(204, 204, 204)));
        boody.setLayout(new javax.swing.BoxLayout(boody, javax.swing.BoxLayout.LINE_AXIS));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("Emplacement:");

        physique.setEditable(false);
        physique.setBackground(new java.awt.Color(240, 240, 241));
        physique.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        physique.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                physiqueActionPerformed(evt);
            }
        });

        par.setEditable(false);
        par.setBackground(new java.awt.Color(240, 240, 241));
        par.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setText("Livre par:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setText("Livrer à ");

        a.setEditable(false);
        a.setBackground(new java.awt.Color(240, 240, 241));
        a.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("Numéro:");

        numero.setEditable(false);
        numero.setBackground(new java.awt.Color(240, 240, 241));
        numero.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("Date de Livraison:");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("Date de Classement:");

        datel.setEditable(false);
        datel.setBackground(new java.awt.Color(240, 240, 241));
        datel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        datec.setEditable(false);
        datec.setBackground(new java.awt.Color(240, 240, 241));
        datec.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        link.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        link.setForeground(new java.awt.Color(255, 255, 255));
        link.setText("Link");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(par, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                            .addComponent(a))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(physique, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(numero)
                    .addComponent(datel)
                    .addComponent(datec, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(link)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(numero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(link))
                        .addGap(0, 0, 0)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(datel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, 0)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(datec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(physique, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, 0)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(par, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(0, 0, 0)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5)
                            .addComponent(a, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

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
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(boody, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(boody, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
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
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void batchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_batchMouseClicked

clic_attCall_IN_LIST7();
// TODO add your handling code here:
    }//GEN-LAST:event_batchMouseClicked

    private void bussPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_bussPopupMenuWillBecomeInvisible
//calltable();
boody.removeAll();
boody.add(new Archi_table1());
boody.repaint();
boody.revalidate(); // TODO add your handling code here:
    }//GEN-LAST:event_bussPopupMenuWillBecomeInvisible

    private void table2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table2MouseClicked
calls();
        boody.removeAll();
boody.add(new Archi_pdf1());
boody.repaint();
boody.revalidate();                // TODO add your handling code here:
    }//GEN-LAST:event_table2MouseClicked

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
delete();
//delete();//batchs m= new  batchs();
//        m.show();
//        m. setDefaultCloseOperation(DISPOSE_ON_CLOSE);        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
// call();        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jDateChooser1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jDateChooser1ActionPerformed
        // TODO add your handling jrn here:
    }//GEN-LAST:event_jDateChooser1ActionPerformed

    private void jDateChooser2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jDateChooser2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jDateChooser2ActionPerformed

    private void searchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchKeyReleased
calltables();        // TODO add your handling code here:
    }//GEN-LAST:event_searchKeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
calltable();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void physiqueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_physiqueActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_physiqueActionPerformed

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
  int rows= table2.getSelectedRow();
          String Table_clicks = (table2.getModel().getValueAt(rows,2). toString());
        JOptionPane.showMessageDialog(null, Table_clicks);
  //  }       // TODO add your handling code here:
    }//GEN-LAST:event_jLabel3MouseClicked

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
       // TODO add your handling code here:
    }//GEN-LAST:event_formWindowClosed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
         // TODO add your handling code here:
    }//GEN-LAST:event_formWindowClosing

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting jrn (optional) ">
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
            java.util.logging.Logger.getLogger(Achive_check.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Achive_check.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Achive_check.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Achive_check.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                    WebLookAndFeel.install(true);
                new Achive_check().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField a;
    public static final javax.swing.JList<String> batch = new javax.swing.JList<>();
    private javax.swing.JPanel boody;
    public static final javax.swing.JComboBox<String> buss = new javax.swing.JComboBox<>();
    private javax.swing.JTextField datec;
    private javax.swing.JTextField datel;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox1;
    private com.alee.extended.date.WebDateField jDateChooser1;
    private com.alee.extended.date.WebDateField jDateChooser2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    public static final javax.swing.JLabel link = new javax.swing.JLabel();
    private javax.swing.JTextField numero;
    private javax.swing.JTextField par;
    private javax.swing.JTextField physique;
    private javax.swing.JTextField search;
    public static final javax.swing.JTable table2 = new javax.swing.JTable();
    // End of variables declaration//GEN-END:variables
}
