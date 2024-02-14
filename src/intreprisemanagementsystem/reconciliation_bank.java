/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intreprisemanagementsystem;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
 * @author Dosh
 */
public class reconciliation_bank extends javax.swing.JInternalFrame {

       DefaultTableModel mode;
Connection con;
PreparedStatement pst=null;
ResultSet rs= null;
String etrolls;
    public reconciliation_bank() {
        initComponents();
         con=JavaDbConnect.dbConnect();
         callbank();
         date1.setDate(new Date());
         date2.setDate(new Date());
    }
  public void callbank(){
      //  buss.removeAllItems();
   // buss.setSelectedItem("SELECT ONE BANK");
    try{
            String sql="select * from OHADA WHERE CLASS=5 AND SUBSTR=52 OR SUBSTR=57";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                String sum=rs.getString("NAME");
                 bank.addItem(sum);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
    
    try{
            String sql="select * from caisse_dispacting WHERE NAME='"+Home_page.home.user.getText()+"' and ACCESS='Yes'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                String sum=rs.getString("CAT");
                 buss.addItem(sum);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex); }   
    
    }
       
           public void sold1()
    {
        SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
       String dates1= dateFormats.format(date1.getDate());
       String dates22 = dateFormats.format(date2.getDate());   
        
         Double Debit;
         Double Credit;
         Double c;
         String e;
        try{
            String sql="SELECT sum(debit),sum(credit) FROM   rapprochement_banque where bank='"+bank.getSelectedItem()+"' and BUSS='"+buss.getSelectedItem()+"' and DATE1 BETWEEN '"+dates1+"' and '"+dates22+"' and status='A. Mouvements figurant sur le relevé bancaire mais pas en comptabilité'";
          
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
               Debit=rs.getDouble("sum(debit)");
               Credit=rs.getDouble("sum(credit)");
               c=Credit-Debit;
                NumberFormat nf = new DecimalFormat("#,###");
String sld = nf.format(c);
String dbt = nf.format(Debit);
String crt = nf.format(Credit);
           sold_debit1.setText(dbt);
           sold_credit1.setText(crt);
           sold1.setText(sld);
           
            }
            }
        
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
    
    }
           
        public void sold2()
    {
        SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
       String dates1= dateFormats.format(date1.getDate());
       String dates22 = dateFormats.format(date2.getDate());
       
        
         Double Debit;
         Double Credit;
         Double c;
         String e;
        try{
            String sql="SELECT sum(debit),sum(credit) FROM   rapprochement_banque where bank='"+bank.getSelectedItem()+"' and BUSS='"+buss.getSelectedItem()+"' and DATE1 BETWEEN '"+dates1+"' and '"+dates22+"' and status='B. Mouvements figurant en comptabilité mais pas sur le relevé bancaire'";
          
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
               Debit=rs.getDouble("sum(debit)");
               Credit=rs.getDouble("sum(credit)");
               c=Debit-Credit;
                NumberFormat nf = new DecimalFormat("#,###");
String sld = nf.format(c);
String dbt = nf.format(Debit);
String crt = nf.format(Credit);
           sold_debit2.setText(dbt);
           sold_credit2.setText(crt);
           sold2.setText(sld);
           
            }
            }
        
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
    
    }
           
              
public void save_A(){
    if(jComboBox1.getSelectedItem().equals("Debit")){
    try{
    String sql="INSERT INTO `rapprochement_banque`(`LIBELLE`, `DEBIT`, `CREDIT`, `DATE1`, `EXPLICATIONS`, `STATUS`, `BANK`, `BUSS`) VALUES (?,?,?,?,?,?,?,?)";
     
    pst=con.prepareStatement(sql);
   
    pst.setString(1,libelles3.getText());
    pst.setString(2,debits3.getText());
    pst.setString(3,"0.00");
     SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
       String dates1= dateFormats.format(dates3.getDate());
    pst.setString(4,dates1);
    pst.setString(5,"");
    pst.setString(6,"A. Mouvements figurant sur le relevé bancaire mais pas en comptabilité");
  pst.setString(7,bank.getSelectedItem().toString());
    pst.setString(8,buss.getSelectedItem().toString());
    
    pst.executeUpdate();
      JOptionPane.showMessageDialog(null,"Datas Saved");
    }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}
    }else{
    try{
    String sql="INSERT INTO `rapprochement_banque`(`LIBELLE`, `DEBIT`, `CREDIT`, `DATE1`, `EXPLICATIONS`, `STATUS`, `BANK`, `BUSS`) VALUES (?,?,?,?,?,?,?,?)";
     
    pst=con.prepareStatement(sql);
   
    pst.setString(1,libelles3.getText());
    pst.setString(3,debits3.getText());
    pst.setString(2,"0.00");
     SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
       String dates1= dateFormats.format(dates3.getDate());
    pst.setString(4,dates1);
    pst.setString(5,"");
    pst.setString(6,"A. Mouvements figurant sur le relevé bancaire mais pas en comptabilité");
  pst.setString(7,bank.getSelectedItem().toString());
    pst.setString(8,buss.getSelectedItem().toString());
    
    pst.executeUpdate();
      JOptionPane.showMessageDialog(null,"Datas Saved");
    }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}
    }
 
 
 
 }

public void save_G(){
  if(jComboBox2.getSelectedItem().equals("Debit")){
  try{
    String sql="INSERT INTO `rapprochement_banque`(`LIBELLE`, `DEBIT`, `CREDIT`, `DATE1`, `EXPLICATIONS`, `STATUS`, `BANK`, `BUSS`) VALUES (?,?,?,?,?,?,?,?)";
     
    pst=con.prepareStatement(sql);
   
    pst.setString(1,libelles2.getText());
    pst.setString(3,"0.00");
    pst.setString(2,debits2.getText());
   SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
       String dates1= dateFormats.format(dates2.getDate());
    pst.setString(4,dates1);
    pst.setString(5,"");
    pst.setString(6,"B. Mouvements figurant en comptabilité mais pas sur le relevé bancaire");
  pst.setString(7,bank.getSelectedItem().toString());
    pst.setString(8,buss.getSelectedItem().toString());
    
    pst.executeUpdate();
      JOptionPane.showMessageDialog(null,"Datas Saved");
    }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}
  }else{
  try{
    String sql="INSERT INTO `rapprochement_banque`(`LIBELLE`, `DEBIT`, `CREDIT`, `DATE1`, `EXPLICATIONS`, `STATUS`, `BANK`, `BUSS`) VALUES (?,?,?,?,?,?,?,?)";
     
    pst=con.prepareStatement(sql);
   
    pst.setString(1,libelles2.getText());
    pst.setString(2,"0.00");
    pst.setString(3,debits2.getText());
   SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
       String dates1= dateFormats.format(dates2.getDate());
    pst.setString(4,dates1);
    pst.setString(5,"");
    pst.setString(6,"B. Mouvements figurant en comptabilité mais pas sur le relevé bancaire");
  pst.setString(7,bank.getSelectedItem().toString());
    pst.setString(8,buss.getSelectedItem().toString());
    
    pst.executeUpdate();
      JOptionPane.showMessageDialog(null,"Datas Saved");
    }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}
  
  }
 
 
 
 }
                   public void search2()
             {
                 SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
       String dates1= dateFormats.format(date1.getDate());
       String dates22 = dateFormats.format(date2.getDate());
                  String tmp=bank.getSelectedItem().toString();
              try{
               //   String st=projetid.getText().trim();
            
    String sql="select DATE1 AS Date,`LIBELLE` as Libelle, `DEBIT` as 'Debit(D)', CREDIT AS 'Credit(E)' from rapprochement_banque where BANK = '"+tmp+"' and buss='"+buss.getSelectedItem()+"' and Status='B. Mouvements figurant en comptabilité mais pas sur le relevé bancaire' and DATE1 BETWEEN '"+dates1+"' and '"+dates22+"'";
    //`LIBELLE`, `ENTRE`, `SORTIE`, `DATE`, `BUSS`, `USED`, `NUM`
    pst = con.prepareStatement(sql);
      rs= pst.executeQuery();
     
       jTable2.setModel(DbUtils.resultSetToTableModel(rs));
    
             }catch(Exception ex){
                 JOptionPane.showMessageDialog(null, ex);
             }
                 DefaultTableCellRenderer centre =new DefaultTableCellRenderer();
        centre.setHorizontalAlignment(JLabel.CENTER);
         TableColumn col0=jTable2.getColumnModel().getColumn(0);
        TableColumn col1=jTable2.getColumnModel().getColumn(1);
        TableColumn col2=jTable2.getColumnModel().getColumn(2);
        TableColumn col3=jTable2.getColumnModel().getColumn(3);
           
            jTable2.getColumnModel().getColumn(2).setCellRenderer(centre);
             jTable2.getColumnModel().getColumn(3).setCellRenderer(centre);
      
       
       col0.setPreferredWidth(50);
       col1.setPreferredWidth(250);
       col2.setPreferredWidth(50);
       col3.setPreferredWidth(50);
     
    
              try{
               //   String st=projetid.getText().trim();
            
    String sql="select DATE1 AS Date,`LIBELLE` as Libelle, `DEBIT` as 'Debit(B)', CREDIT AS 'Credit(C)' from rapprochement_banque where BANK = '"+tmp+"' and buss='"+buss.getSelectedItem()+"' and status='A. Mouvements figurant sur le relevé bancaire mais pas en comptabilité' and DATE1 BETWEEN '"+dates1+"' and '"+dates22+"'";
    //`LIBELLE`, `ENTRE`, `SORTIE`, `DATE`, `BUSS`, `USED`, `NUM`
    pst = con.prepareStatement(sql);
      rs= pst.executeQuery();
     
       jTable3.setModel(DbUtils.resultSetToTableModel(rs));
    
             }catch(Exception ex){
                 JOptionPane.showMessageDialog(null, ex);
             }
                
        //centre.setHorizontalAlignment(JLabel.CENTER);
         TableColumn col00=jTable3.getColumnModel().getColumn(0);
        TableColumn col11=jTable3.getColumnModel().getColumn(1);
        TableColumn col22=jTable3.getColumnModel().getColumn(2);
        TableColumn col33=jTable3.getColumnModel().getColumn(3);
           
            jTable3.getColumnModel().getColumn(2).setCellRenderer(centre);
             jTable3.getColumnModel().getColumn(3).setCellRenderer(centre);
      
       
       col00.setPreferredWidth(50);
       col11.setPreferredWidth(250);
       col22.setPreferredWidth(50);
       col33.setPreferredWidth(50);
     
             
             }
             
                   public void report()
                           
     {
         
         
             
         SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
       String dates1= dateFormats.format(date1.getDate());
       String dates22 = dateFormats.format(date2.getDate());
             
           String tmp =(String)bank.getSelectedItem();
          // this.setAlwaysOnTop (false);
     
             try{
                 
                 
                   String sqls="Select path from pathn";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
            if(rs.next()){
             
                String sum=rs.getString("path");
                  
                 String NameFiles=sum;
            
            String NameFile=""+NameFiles+"raprochement.jrxml";
                 
                 
                 
                JasperDesign jd=JRXmlLoader.load(NameFile);
                 
              String sql="select * from rapprochement_banque  where bank='"+tmp+"' and DATE1 BETWEEN '"+dates1+"' AND '"+dates22+"' AND BUSS='"+buss.getSelectedItem()+"' order by DATE1, status";
             // String sql="Select * from OHADA_TRANS INNER JOIN projet ON OHADA_TRANS.BUSS=projet.PROJET_NUM where buss='"+tmp+" and code_m=57'";
      HashMap param= new HashMap();
    
     param.put("dbt1", sold_debit1.getText());
     param.put("crt1",sold_credit1.getText());
     param.put("sld1", sold1.getText());
     param.put("db2",sold_debit2.getText());
     param.put("crt2", sold_credit2.getText());
     param.put("sld2",sold2.getText());
     param.put("date1", date1.getText());
     param.put("date2",date2.getText());
                JRDesignQuery nq=new JRDesignQuery();
                nq.setText(sql);
                jd.setQuery(nq);
                
                
               
                 JasperReport jr =JasperCompileManager.compileReport(jd);
                 JasperPrint jp=JasperFillManager.fillReport(jr,param,con);
                 JasperViewer.viewReport(jp,false);
                 
                 
                 
                 JasperViewer m= new JasperViewer(jp);
                 
                
                 m.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
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
        jPanel3 = new javax.swing.JPanel();
        date1 = new com.alee.extended.date.WebDateField();
        date2 = new com.alee.extended.date.WebDateField();
        jPanel4 = new javax.swing.JPanel();
        bank = new javax.swing.JComboBox<>();
        buss = new javax.swing.JComboBox<>();
        signe = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        sold_debit1 = new javax.swing.JTextField();
        sold1 = new javax.swing.JTextField();
        sold_credit1 = new javax.swing.JTextField();
        jPanel13 = new javax.swing.JPanel();
        sold_debit2 = new javax.swing.JTextField();
        sold2 = new javax.swing.JTextField();
        sold_credit2 = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        libelles3 = new javax.swing.JTextField();
        debits3 = new javax.swing.JTextField();
        dates3 = new com.alee.extended.date.WebDateField();
        jButton1 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jPanel12 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        libelles2 = new javax.swing.JTextField();
        debits2 = new javax.swing.JTextField();
        dates2 = new com.alee.extended.date.WebDateField();
        jButton2 = new javax.swing.JButton();
        jComboBox2 = new javax.swing.JComboBox<>();
        jPanel10 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu2 = new javax.swing.JMenu();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();

        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_Bill_16px.png"))); // NOI18N

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Action 1", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N

        date1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        date1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                date1ActionPerformed(evt);
            }
        });

        date2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        date2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                date2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(date1, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(date2, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(date1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(date2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Action 2", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N

        bank.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        bank.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select One Bank" }));
        bank.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        bank.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                bankPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        buss.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        buss.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select One Project" }));
        buss.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        signe.setFont(new java.awt.Font("Tahoma", 0, 3)); // NOI18N
        signe.setForeground(new java.awt.Color(255, 255, 255));
        signe.setText("jLabel1");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(buss, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(bank, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(signe, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(buss)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(bank, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(signe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Banque", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N

        sold_debit1.setEditable(false);
        sold_debit1.setBackground(new java.awt.Color(240, 240, 241));
        sold_debit1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        sold_debit1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        sold_debit1.setEnabled(false);

        sold1.setEditable(false);
        sold1.setBackground(new java.awt.Color(240, 240, 241));
        sold1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        sold1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        sold1.setEnabled(false);

        sold_credit1.setEditable(false);
        sold_credit1.setBackground(new java.awt.Color(240, 240, 241));
        sold_credit1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        sold_credit1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        sold_credit1.setEnabled(false);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(sold_debit1, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(sold_credit1, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(sold1)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sold_debit1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sold_credit1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sold1)
                .addContainerGap())
        );

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));
        jPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ma Comptabilite", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N

        sold_debit2.setEditable(false);
        sold_debit2.setBackground(new java.awt.Color(240, 240, 241));
        sold_debit2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        sold_debit2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        sold_debit2.setEnabled(false);

        sold2.setEditable(false);
        sold2.setBackground(new java.awt.Color(240, 240, 241));
        sold2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        sold2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        sold2.setEnabled(false);

        sold_credit2.setEditable(false);
        sold_credit2.setBackground(new java.awt.Color(240, 240, 241));
        sold_credit2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        sold_credit2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        sold_credit2.setEnabled(false);

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addComponent(sold_debit2, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(sold_credit2, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(sold2)
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sold_debit2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sold_credit2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sold2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Mouvements figurant sur le relevé bancaire mais pas en comptabilité", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        libelles3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        libelles3.setText("Ecriture de Rapprochement");
        libelles3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        libelles3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                libelles3MouseClicked(evt);
            }
        });
        libelles3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                libelles3ActionPerformed(evt);
            }
        });

        debits3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        debits3.setText("0.00");
        debits3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        dates3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton1.setText("Save");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jComboBox1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Debit", "Credit" }));
        jComboBox1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(libelles3)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(dates3, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(debits3, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)))
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(libelles3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(dates3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton1)
                        .addComponent(debits3)))
                .addContainerGap())
        );

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));
        jPanel12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jTable3.setBackground(new java.awt.Color(240, 240, 240));
        jTable3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable3.setRowHeight(22);
        jTable3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable3MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTable3);

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 487, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Mouvements figurant en comptabilité mais pas sur le relevé bancaire", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        libelles2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        libelles2.setText("Ecriture de Rapprochement");
        libelles2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        libelles2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                libelles2MouseClicked(evt);
            }
        });
        libelles2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                libelles2ActionPerformed(evt);
            }
        });

        debits2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        debits2.setText("0.00");
        debits2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        dates2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton2.setText("Save");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jComboBox2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Debit", "Credit" }));
        jComboBox2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(libelles2)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(dates2, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(debits2, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(libelles2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(debits2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dates2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2)
                    .addComponent(jComboBox2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jTable2.setBackground(new java.awt.Color(240, 240, 240));
        jTable2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable2.setRowHeight(22);
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(20, Short.MAX_VALUE))
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

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem3.setText("Delete");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem2.setText("Print");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

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

    private void date1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_date1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_date1ActionPerformed

    private void date2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_date2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_date2ActionPerformed

    private void jMenu2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu2MouseClicked
this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu2MouseClicked

    private void bankPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_bankPopupMenuWillBecomeInvisible
sold1(); 
sold2();
search2();
// TODO add your handling code here:
    }//GEN-LAST:event_bankPopupMenuWillBecomeInvisible

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
report();        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void libelles2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_libelles2MouseClicked
if(libelles2.getText().equals("Ecriture de Rapprochement")){
libelles2.setText("");
}else{

}       // TODO add your handling code here:
    }//GEN-LAST:event_libelles2MouseClicked

    private void libelles2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_libelles2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_libelles2ActionPerformed

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
signe.setText("");
        signe.setText("comptabilite"); 
       // TODO add your handling code here:
    }//GEN-LAST:event_jTable2MouseClicked

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
if(signe.getText().equals("Releve")){
 int row= jTable3.getSelectedRow();
          String date = (jTable3.getModel().getValueAt(row,0). toString());
          String libelles = (jTable3.getModel().getValueAt(row,1). toString());
          String libelle=libelles.replace("'", "");
          String debit = (jTable3.getModel().getValueAt(row,2). toString());
          String credit = (jTable3.getModel().getValueAt(row,3). toString());
//          String ID = null;
//      
//    
//    try{
//           String sql = "SELECT ID FROM   rapprochement_banque WHERE LIBELLE='"+libelle+"' and DEBIT='"+debit+"' and CREDIT='"+credit+"' and DATE1='"+date+"' and BANK='"+bank.getSelectedItem()+"' and BUSS='"+buss.getSelectedItem()+"'";
//             pst = con.prepareStatement(sql);rs=pst.executeQuery();
//          if(rs.next()){
//               ID=rs.getString("ID");
//             
//            }
//            }
//        catch(Exception ex){
//          JOptionPane.showMessageDialog(null, ex);    
//        }
//    JOptionPane.showMessageDialog(null,ID); 
  try{
        String sql = "DELETE FROM  rapprochement_banque WHERE 	LIBELLE='"+libelle+"' and DEBIT='"+debit+"' and CREDIT='"+credit+"' and DATE1='"+date+"' and BANK='"+bank.getSelectedItem()+"' and BUSS='"+buss.getSelectedItem()+"'";
         pst = con.prepareStatement(sql);
         pst.executeUpdate();
       //  JOptionPane.showMessageDialog(null,"delete AUTRES ACHAT");
              
     }catch(SQLException | HeadlessException ex ){
         JOptionPane.showMessageDialog(null,ex);
     } 
}else if(signe.getText().equals("comptabilite")){
int rows= jTable2.getSelectedRow();
          String date = (jTable2.getModel().getValueAt(rows,0). toString());
          String libelles = (jTable2.getModel().getValueAt(rows,1). toString());
          String libelle=libelles.replace("'", "");
          String debit = (jTable2.getModel().getValueAt(rows,2). toString());
          String credit = (jTable2.getModel().getValueAt(rows,3). toString());
     JOptionPane.showMessageDialog(null,libelle+" "+debit+" "+credit+" "+date);      
  try{
        String sql = "DELETE FROM  rapprochement_banque WHERE 	LIBELLE='"+libelle+"' and DEBIT='"+debit+"' and CREDIT='"+credit+"' and DATE1='"+date+"' and BANK='"+bank.getSelectedItem()+"' and BUSS='"+buss.getSelectedItem()+"'";
         pst = con.prepareStatement(sql);
         pst.executeUpdate();
      
        // JOptionPane.showMessageDialog(null,"delete AUTRES ACHAT");
              
     }catch(SQLException | HeadlessException ex ){
         JOptionPane.showMessageDialog(null,ex);
     } 
}
       
        //delete();  
search2();// TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void libelles3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_libelles3MouseClicked
if(libelles3.getText().equals("Ecriture de Rapprochement")){
libelles3.setText("");
}else{

}        // TODO add your handling code here:
    }//GEN-LAST:event_libelles3MouseClicked

    private void libelles3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_libelles3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_libelles3ActionPerformed

    private void jTable3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable3MouseClicked
signe.setText("");
        signe.setText("Releve");        // TODO add your handling code here:
    }//GEN-LAST:event_jTable3MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
save_A();
search2();
sold1();
libelles2.setText("Ecriture de Rapprochement");
debits2.setText("0.00");
libelles3.setText("Ecriture de Rapprochement");
debits3.setText("0.00");// TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
save_G();
search2();
sold2();
libelles2.setText("Ecriture de Rapprochement");
debits2.setText("0.00");
libelles3.setText("Ecriture de Rapprochement");
debits3.setText("0.00");// TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> bank;
    private javax.swing.JComboBox<String> buss;
    private com.alee.extended.date.WebDateField date1;
    private com.alee.extended.date.WebDateField date2;
    private com.alee.extended.date.WebDateField dates2;
    private com.alee.extended.date.WebDateField dates3;
    private javax.swing.JTextField debits2;
    private javax.swing.JTextField debits3;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTextField libelles2;
    private javax.swing.JTextField libelles3;
    private javax.swing.JLabel signe;
    private javax.swing.JTextField sold1;
    private javax.swing.JTextField sold2;
    private javax.swing.JTextField sold_credit1;
    private javax.swing.JTextField sold_credit2;
    private javax.swing.JTextField sold_debit1;
    private javax.swing.JTextField sold_debit2;
    // End of variables declaration//GEN-END:variables
}
