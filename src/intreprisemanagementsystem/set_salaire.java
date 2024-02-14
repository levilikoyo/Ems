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
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
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
 * @author Doshe PC
 */
public class set_salaire extends javax.swing.JInternalFrame {
 DefaultTableModel modes;
  DefaultTableModel mode;
  
    Connection con=null;
PreparedStatement pst=null;
ResultSet rs= null;
String rolls;
String etrolls;
String salaryrolls;
    public set_salaire() {
        initComponents();
         con=JavaDbConnect.dbConnect();
        Groupe1();
       call();
       projetarray.setVisible(false);
       projetarray.show(false);
    }
        public void Groupe1(){
ButtonGroup bg1 = new ButtonGroup();
bg1.add(bulletin);
bg1.add(list);

}
public void call(){
     try{
            String sql="select distinct(ANNEE) from salaire";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                String sum1=rs.getString("ANNEE");
                 annee1.addItem(sum1);
                
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex); }  
     
      try{
            String sql="select PROJET_NUM from PROJET";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                String sum=rs.getString("PROJET_NUM");
                 projet.addItem(sum);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex); }  
      
  try{
           
             String sql="SELECT PROJET_NUM FROM PROJET";// where PROJECT='"+projet.getSelectedItem()+"' and CONTRACT='"+contrat.getSelectedItem()+"' ORDER BY NAME";
     //  String sql="SELECT ID, `NUM_FACTURE`, `DATE_FACTURE`, `DESCRIPTION`, `DEBIT_QTY`, `DEBIT_PU`, `DEBIT_PT`, `DEBIT_TVA`, `CREDIT_QTY`, `CREDIT_PU`, `CREDIT_PT`, `CREDIT_TVA`, `DATES`, `NUM` FROM facture_fournisseur where compte='"+jComboBox6.getSelectedItem()+"'";
       pst = con.prepareStatement(sql);
      rs= pst.executeQuery();
      
       
    tbl_projet.setModel(DbUtils.resultSetToTableModel(rs));
       mode=new DefaultTableModel();
 }catch(SQLException ex ){
     JOptionPane.showMessageDialog(null, ex);
}     
   try{
  String sql="SELECT PROJET_NUM FROM PROJET";// where PROJECT='"+projet.getSelectedItem()+"' and CONTRACT='"+contrat.getSelectedItem()+"' ORDER BY NAME";
     pst = con.prepareStatement(sql);
      rs= pst.executeQuery();
      
       
    tbl_projet.setModel(DbUtils.resultSetToTableModel(rs));
       mode=new DefaultTableModel();
 }catch(SQLException ex ){
     JOptionPane.showMessageDialog(null, ex);}  
   
    
    }



  public void call_in_table_AFTER_DATE_active(){
     
      try{
           
             String sql="SELECT NAME,POSTE,ROLL,ACTIVE,PROJECT2 AS '-%-PROJET',SALAIRE FROM contract where PROJECT='"+projet.getSelectedItem()+"' and CONTRACT='"+contrat.getSelectedItem()+"' ORDER BY NAME";
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
       
       col0.setPreferredWidth(150);
       col1.setPreferredWidth(150);
       col2.setPreferredWidth(130);
       col3.setPreferredWidth(50);
       col4.setPreferredWidth(50);
       
    }catch(SQLException ex ){
     JOptionPane.showMessageDialog(null, ex);
}
      
      
    try{
           
             String sql="SELECT `NAME`,`LNAME`,ROLL,DATE_IN,DATE_OUT,FORMAT(SALAIRE_NET,2) AS 'NET' FROM `salaire` WHERE PROJECT='"+projet.getSelectedItem()+"' and MONTH ='"+jComboBox4.getSelectedItem()+"' AND PRINT='Print' and CONTRACT='"+contrat.getSelectedItem()+"' and annee='"+annee1.getSelectedItem()+"' ORDER BY NAME";
     //  String sql="SELECT ID, `NUM_FACTURE`, `DATE_FACTURE`, `DESCRIPTION`, `DEBIT_QTY`, `DEBIT_PU`, `DEBIT_PT`, `DEBIT_TVA`, `CREDIT_QTY`, `CREDIT_PU`, `CREDIT_PT`, `CREDIT_TVA`, `DATES`, `NUM` FROM facture_fournisseur where compte='"+jComboBox6.getSelectedItem()+"'";
       pst = con.prepareStatement(sql);
      rs= pst.executeQuery();
      
       
       tblebultin.setModel(DbUtils.resultSetToTableModel(rs));
       modes=new DefaultTableModel();
       
       
        TableColumn col0=tblebultin.getColumnModel().getColumn(0);
        TableColumn col1=tblebultin.getColumnModel().getColumn(1);
         TableColumn col2=tblebultin.getColumnModel().getColumn(2);
         TableColumn col3=tblebultin.getColumnModel().getColumn(3);
         TableColumn col4=tblebultin.getColumnModel().getColumn(4);
         TableColumn col5=tblebultin.getColumnModel().getColumn(5);
      
       
       col0.setPreferredWidth(200);
       col1.setPreferredWidth(200);
       col2.setPreferredWidth(100);
       col3.setPreferredWidth(50);
       col4.setPreferredWidth(50);
       col5.setPreferredWidth(50);
 
     
    }catch(SQLException ex ){
     JOptionPane.showMessageDialog(null, ex);
}   
  try{
           
             String sql="SELECT DISTINCT(ROLL) AS MATRICULE,NAME,POSTE FROM contract where PROJECT='"+projet.getSelectedItem()+"' and CONTRACT='"+contrat.getSelectedItem()+"' ORDER BY NAME";
     //  String sql="SELECT ID, `NUM_FACTURE`, `DATE_FACTURE`, `DESCRIPTION`, `DEBIT_QTY`, `DEBIT_PU`, `DEBIT_PT`, `DEBIT_TVA`, `CREDIT_QTY`, `CREDIT_PU`, `CREDIT_PT`, `CREDIT_TVA`, `DATES`, `NUM` FROM facture_fournisseur where compte='"+jComboBox6.getSelectedItem()+"'";
       pst = con.prepareStatement(sql);
      rs= pst.executeQuery();
      
       
       tblemp.setModel(DbUtils.resultSetToTableModel(rs));
       mode=new DefaultTableModel();
       
       
        TableColumn col0=tblemp.getColumnModel().getColumn(0);
        TableColumn col1=tblemp.getColumnModel().getColumn(1);
        TableColumn col2=tblemp.getColumnModel().getColumn(2);
        
       
       col0.setPreferredWidth(250);
       col1.setPreferredWidth(100);
       col2.setPreferredWidth(100);
      
       
    }catch(SQLException ex ){
     JOptionPane.showMessageDialog(null, ex);
}   
  //gegdg
     }
  
      public void reports_all(){
//                 String tmp=jComboBox2.getSelectedItem().toString();
             try{
             
          String sqls="Select path from pathn";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
            if(rs.next()){
             
                String sum=rs.getString("path");
                  
                 String NameFiles=sum;
            
            String NameFile=""+NameFiles+"sal.jrxml";//journal_de_banque
           // String NameFile=""+NameFiles+"journal_de_banque.jrxml";//journal_de_banque
                 
                 
                 
                JasperDesign jd=JRXmlLoader.load(NameFile);
        
        
              // JasperDesign jd=JRXmlLoader.load("C:\\Users\\Dosh\\Documents\\NetBeansProjects\\intreprisemanagementsystem\\src\\intreprisemanagementsystem\\journaldecaise.jrxml");
              String sql="select * from salaire INNER JOIN projet ON salaire.PROJECT=projet.PROJET_NUM where Print='Print' and MONTH='"+jComboBox4.getSelectedItem()+"' and CONTRACT='"+contrat.getSelectedItem()+"' and ANNEE='"+annee1.getSelectedItem()+"'";
             // String sql="select * from salaire INNER JOIN projet ON salaire.PROJECT=projet.PROJET_NUM INNER JOIN contract ON PROJET.PROJET_NUM = CONTRACT.PROJECT  where Print='Print' and salaire.MONTH='"+jComboBox4.getSelectedItem()+"' and salaire.CONTRACT='"+contrat.getSelectedItem()+"' and ANNEE='"+annee1.getSelectedItem()+"'";
             //select * from salaire INNER JOIN projet ON salaire.PROJECT=projet.PROJET_NUM INNER JOIN contract ON PROJET.PROJET_NUM = CONTRACT.PROJECT 
    
                JRDesignQuery nq=new JRDesignQuery();
                nq.setText(sql);
                jd.setQuery(nq);
               
                 JasperReport jr =JasperCompileManager.compileReport(jd);
                 JasperPrint jp=JasperFillManager.fillReport(jr,null,con);
                 JasperViewer.viewReport(jp,false);
                 
                 
                 JasperViewer m= new JasperViewer(jp);
                 m.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            }
             }catch(Exception ex){
                  JOptionPane.showMessageDialog(null, ex);   
             }
             
             }
         public void reports_allfiche(){
//                 String tmp=jComboBox2.getSelectedItem().toString();
             try{
             
          String sqls="Select path from pathn";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
            if(rs.next()){
             
                String sum=rs.getString("path");
                  
                 String NameFiles=sum;
            
            String NameFile=""+NameFiles+"salbon.jrxml";
            JasperDesign jd=JRXmlLoader.load(NameFile);
        
        
              // JasperDesign jd=JRXmlLoader.load("C:\\Users\\Dosh\\Documents\\NetBeansProjects\\intreprisemanagementsystem\\src\\intreprisemanagementsystem\\journaldecaise.jrxml");
              String sql="select * from salaire INNER JOIN projet ON salaire.PROJECT=projet.PROJET_NUM where Print='Print' and MONTH='"+jComboBox4.getSelectedItem()+"' and CONTRACT='"+contrat.getSelectedItem()+"' and ANNEE='"+annee1.getSelectedItem()+"' ORDER BY NAME";
              
    
                JRDesignQuery nq=new JRDesignQuery();
                nq.setText(sql);
                jd.setQuery(nq);
               
                 JasperReport jr =JasperCompileManager.compileReport(jd);
                 JasperPrint jp=JasperFillManager.fillReport(jr,null,con);
                 JasperViewer.viewReport(jp,false);
                 
                 
                 JasperViewer m= new JasperViewer(jp);
                 m.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            }
             }catch(Exception ex){
                  JOptionPane.showMessageDialog(null, ex);   
             }
             
             }
         public void delete()
    {
 int indexs[]=tblebultin.getSelectedRows();
      
        for(int i=0; i < indexs.length;i++){
        String Table_click = (tblebultin.getModel().getValueAt(indexs[i],2). toString());   
          
           try{
        String sql = "DELETE FROM salaire WHERE ROLL='"+Table_click+"' and MONTH='"+jComboBox4.getSelectedItem()+"' and annee='"+annee1.getSelectedItem()+"' and contract='"+contrat.getSelectedItem()+"'";
        
         pst = con.prepareStatement(sql);
         pst.executeUpdate();
              
     }catch(SQLException | HeadlessException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }
          
        }
          // JOptionPane.showMessageDialog(null,Table_click);
        
  call_in_table_AFTER_DATE_active();
    }
             public void save(){
 int indexs[]=jTable3.getSelectedRows();
       
        for(int i=0; i < indexs.length;i++){   

    String roll = (jTable3.getModel().getValueAt(indexs[i],2). toString());
      String sal = (jTable3.getModel().getValueAt(indexs[i],5). toString());
       // String Table_click = 
  try{
            String sqls="SELECT count(*) FROM contract WHERE CONTRACT='"+contrat.getSelectedItem()+"' and ROLL='"+roll+"' ";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
            if(rs.next()){
            int count = rs.getInt(1);
            if(count > 1){
            JOptionPane.showMessageDialog(null,"Cette modification nécessite l’utilisation de multiples projets","Warning",JOptionPane.WARNING_MESSAGE);
            }else{
             try {
         
        PreparedStatement pst = con.prepareStatement("UPDATE contract SET SALAIRE=?,PROJECT2=?,NOTE_PROJECT2=? WHERE ROLL='"+roll+"' and CONTRACT='"+contrat.getSelectedItem()+"'");
 //`NOMS`, `LNAME`, `ROLL`, `ADDRESS`, `TEL`, `MAIL`, `GENDER`, `TITLE`, `DEPARTEMENT`, `LOCK`      
        pst.setString(1, sal);
        pst.setString(2,projet.getSelectedItem().toString());
        pst.setString(3, "Contribution 100% de projet "+projet.getSelectedItem()+" = "+sal);
        //Contribution projet 6100149-AA  = 400 |Contribution projet 6000207-BMZ  = 500 |Contribution projet MEDICA 2020  = 600 
 
          pst.executeUpdate();
        
             //    JOptionPane.showMessageDialog(null,"Data Saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
           
            
            }
            
            }
             
        
             //    JOptionPane.showMessageDialog(null,"Data Saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }  
  
 try {
         
        PreparedStatement pst = con.prepareStatement("UPDATE contract_print SET SALAIRE=? WHERE ROLL='"+roll+"' and CONTRACT='"+contrat.getSelectedItem()+"'");
 //`NOMS`, `LNAME`, `ROLL`, `ADDRESS`, `TEL`, `MAIL`, `GENDER`, `TITLE`, `DEPARTEMENT`, `LOCK`      
        pst.setString(1, sal);
        
 
          pst.executeUpdate();
        
             //    JOptionPane.showMessageDialog(null,"Data Saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }

  }
 
      //  JOptionPane.showMessageDialog(null,"Transaction Saved");
        
       }
             
   public void savemultiple(){
  int indexs[]=tblebultin2.getSelectedRows();
int index[]=tblebultinemp.getSelectedRows();
 String NAME = null,roll=null,LNAME = null,NOTE=null ,ADRESS = null,TITLE = null,NATIONALITY = null,MARIAGE = null,COLLAPSE = null,MONTH = null ,PROJECT = null,BAYEUR = null,AFF = null,CONJOINT = null,INSS = null,DEPARTEMENT =null,TEL=null ;      
        for(int i=0; i < indexs.length;i++){   

      
      
      for(int j=0; j <index.length;j++){
       roll = (tblebultinemp.getModel().getValueAt(index[j],0). toString());
     // String name = (tblebultinemp.getModel().getValueAt(indexs[i],1). toString());
      
    String projets = (tblebultin2.getModel().getValueAt(indexs[i],0). toString());
      String sal = (tblebultin2.getModel().getValueAt(indexs[i],1). toString());
        jEditorPane1.setText(jEditorPane1.getText()+"Contribution projet "+projets+"  = "+sal+" |");
    StringBuffer sb= new StringBuffer(jEditorPane1.getText());
    sb.deleteCharAt(sb.length()-1);
     NOTE=sb.toString();
      try{
            String sqls="SELECT `NAME`, `LNAME`, `ADRESSE`, `ROLLNUM`, `TITRE`, `NATIONALITE`, `ETAT_CIVIL`,DEPARTMENT,TEL FROM `employee` WHERE ROLLNUM='"+roll+"'";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
            while(rs.next()){
              NAME = rs.getString("NAME");
              LNAME = rs.getString("LNAME");
              ADRESS = rs.getString("ADRESSE");
              TITLE = rs.getString("TITRE");
              NATIONALITY = rs.getString("NATIONALITE");
              MARIAGE = rs.getString("ETAT_CIVIL"); 
            DEPARTEMENT = rs.getString("DEPARTMENT"); 
            TEL = rs.getString("TEL"); }
            }
        catch(Exception ex){
         JOptionPane.showMessageDialog(null, ex);}
          
           try{
            String sqls="SELECT COLLAPSE,MONTH,PROJECT,BAYEUR,AFF FROM type_contract WHERE CONTRACT='"+contrat.getSelectedItem()+"'";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
            while(rs.next()){
              COLLAPSE = rs.getString("COLLAPSE");
               MONTH = rs.getString("MONTH");
              PROJECT = rs.getString("PROJECT");
              
              BAYEUR = rs.getString("BAYEUR");
              AFF = rs.getString("AFF");
              }
            }
        catch(Exception ex){
         JOptionPane.showMessageDialog(null, ex);}
           
           
           try{
            //   String conjointt=null;
            String sqls="SELECT `PARTENER` FROM `all_fam_numberchild` WHERE ROLL='"+roll+"'";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
            while(rs.next()){
           CONJOINT   = rs.getString("PARTENER");
           //
          //   CONJOINT="-";
            // }
              }
            }
        catch(Exception ex){
         JOptionPane.showMessageDialog(null, ex);}
          
            try{
            String sqls="SELECT `INSS` FROM `inss` WHERE ROLL='"+roll+"'";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
            while(rs.next()){
              INSS = rs.getString("INSS");
              //
              }
            }
        catch(Exception ex){
         JOptionPane.showMessageDialog(null, ex);}
             
       try{
            String sqls="SELECT * FROM contract WHERE CONTRACT='"+contrat.getSelectedItem()+"' and ROLL='"+roll+"' AND PROJECT2='"+projets+"'";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
            if(rs.next()){
             try {
         
        PreparedStatement pst = con.prepareStatement("UPDATE contract SET SALAIRE=? WHERE ROLL='"+roll+"' and CONTRACT='"+contrat.getSelectedItem()+"' AND PROJECT2='"+projets+"'");
    
        pst.setString(1, sal);
        
 
          pst.executeUpdate();
        
             //    JOptionPane.showMessageDialog(null,"Data Saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
              }else{
            try {
         
        PreparedStatement pst = con.prepareStatement("INSERT INTO `contract`(`CONTRACT`, `NAME`, `LNAME`, `ADDRESS`, `NATIONALITY`, `MARIAGE`, `CONJOINT`, `POSTE`, `INSS`, `ROLL`, `ACTIVE`, `DATE_IN`, `DATE_OUT`, `PROJECT`, `SALAIRE`, `BAYEUR`, `AFF`,`COLAPSE`, `MONTH`,DEPARTEMENT,TEL,PROJECT2,NOTE_PROJECT2)"
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
       // `INSS`, `ROLL`, `ACTIVE`, `DATE_IN`, `DATE_OUT`, `PROJECT`, `SALAIRE`)"
       //,,,,PROJECT,COLLAPSE
        pst.setString(1, contrat.getSelectedItem().toString());
         pst.setString(2, NAME);
         pst.setString(3, LNAME);
          pst.setString(4, ADRESS);
          
          pst.setString(5, NATIONALITY);
         pst.setString(6, MARIAGE);
          pst.setString(7, CONJOINT);
          
          pst.setString(8, TITLE);
         pst.setString(9, INSS);
          pst.setString(10, roll);
          
          pst.setString(11, "Active");
         pst.setString(12, "");
          pst.setString(13, "");
          
          pst.setString(14, PROJECT);
         pst.setString(15, sal);
         
         pst.setString(16, BAYEUR);
         pst.setString(17, AFF);
         
          pst.setString(18, COLLAPSE);
         pst.setString(19, MONTH);
           pst.setString(20, DEPARTEMENT);
         pst.setString(21, TEL);
          pst.setString(22,projets);
          pst.setString(23,NOTE);
        
          pst.executeUpdate();
        
                // JOptionPane.showMessageDialog(null,"Data Saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }  
            }
            }
        catch(Exception ex){
         JOptionPane.showMessageDialog(null, ex);}
      
      
      }
   

  }
        // JOptionPane.showMessageDialog(null, NOTE);
           try {
    
        PreparedStatement pst = con.prepareStatement("UPDATE contract SET NOTE_PROJECT2=? WHERE ROLL='"+roll+"' and CONTRACT='"+contrat.getSelectedItem()+"'");
 //`NOMS`, `LNAME`, `ROLL`, `ADDRESS`, `TEL`, `MAIL`, `GENDER`, `TITLE`, `DEPARTEMENT`, `LOCK`      
        pst.setString(1, NOTE);
        
 
          pst.executeUpdate();
        
             //    JOptionPane.showMessageDialog(null,"Data Saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
 NOTE=null;
 jEditorPane1.setText("");
        JOptionPane.showMessageDialog(null,"Transaction Saved");
                // TODO add your handling code here:
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
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        projetarray = new javax.swing.JEditorPane();
        jScrollPane7 = new javax.swing.JScrollPane();
        emparray = new javax.swing.JEditorPane();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbl_projet = new javax.swing.JTable();
        jTextField1 = new Palette.TextField();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblemp = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblebultin2 = new javax.swing.JTable();
        jScrollPane8 = new javax.swing.JScrollPane();
        tblebultinemp = new javax.swing.JTable();
        jScrollPane9 = new javax.swing.JScrollPane();
        jEditorPane1 = new javax.swing.JEditorPane();
        jLabel5 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblebultin = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        bulletin = new javax.swing.JRadioButton();
        list = new javax.swing.JRadioButton();
        projet = new Palette.ComboBoxSuggestion();
        contrat = new Palette.ComboBoxSuggestion();
        jComboBox4 = new Palette.ComboBoxSuggestion();
        annee1 = new Palette.ComboBoxSuggestion();
        signe = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();

        setTitle("Set Salaire");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_Cash_in_Hand_16px.png"))); // NOI18N

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTabbedPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane1MouseClicked(evt);
            }
        });

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable3.setRowHeight(30);
        jTable3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable3MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable3);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 904, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 321, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Salaire de base", jPanel4);

        jPanel6.setBackground(new java.awt.Color(204, 204, 204));

        projetarray.setBorder(null);
        jScrollPane6.setViewportView(projetarray);

        emparray.setBorder(null);
        jScrollPane7.setViewportView(emparray);

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        tbl_projet.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tbl_projet.setRowHeight(30);
        tbl_projet.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_projetMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tbl_projetMousePressed(evt);
            }
        });
        jScrollPane3.setViewportView(tbl_projet);

        jTextField1.setLabelText("Recherche");
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });

        tblemp.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblemp.setRowHeight(30);
        tblemp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblempMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblempMousePressed(evt);
            }
        });
        jScrollPane5.setViewportView(tblemp);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText(">>");
        jLabel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText(">>");
        jLabel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });

        tblebultin2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Projet", "Montant"
            }
        ));
        tblebultin2.setRowHeight(30);
        tblebultin2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblebultin2MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblebultin2MousePressed(evt);
            }
        });
        jScrollPane4.setViewportView(tblebultin2);
        if (tblebultin2.getColumnModel().getColumnCount() > 0) {
            tblebultin2.getColumnModel().getColumn(1).setHeaderValue("Montant");
        }

        tblebultinemp.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Matricule", "Noms "
            }
        ));
        tblebultinemp.setRowHeight(30);
        tblebultinemp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblebultinempMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblebultinempMousePressed(evt);
            }
        });
        jScrollPane8.setViewportView(tblebultinemp);

        jEditorPane1.setEditable(false);
        jEditorPane1.setBackground(new java.awt.Color(242, 242, 241));
        jEditorPane1.setBorder(null);
        jEditorPane1.setAutoscrolls(false);
        jScrollPane9.setViewportView(jEditorPane1);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("<<");
        jLabel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("<<");
        jLabel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane9)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 267, Short.MAX_VALUE)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(0, 142, Short.MAX_VALUE)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 582, Short.MAX_VALUE)
                            .addComponent(jScrollPane4))))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(55, 55, 55))
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 16, Short.MAX_VALUE)
                .addGap(41, 41, 41)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane1.addTab("multiples projets", jPanel6);

        tblebultin.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblebultin.setRowHeight(30);
        tblebultin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblebultinMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblebultinMousePressed(evt);
            }
        });
        jScrollPane2.setViewportView(tblebultin);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 930, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 347, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Bulletin de paie/Liste de paie", jPanel5);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 380, Short.MAX_VALUE)
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jButton4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton4.setText("Delete");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton2.setText("Print");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton1.setText("Ok/Save");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        bulletin.setBackground(new java.awt.Color(255, 255, 255));
        bulletin.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        bulletin.setText("Imprimer Bulletin de paie");

        list.setBackground(new java.awt.Color(255, 255, 255));
        list.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        list.setText("Imprimer List de paie");

        projet.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Projets" }));
        projet.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                projetPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        contrat.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Contrats" }));
        contrat.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                contratPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        contrat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                contratActionPerformed(evt);
            }
        });

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December", "13-ième mois" }));
        jComboBox4.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                jComboBox4PopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        annee1.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                annee1PopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        signe.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        signe.setText("SB");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(contrat, javax.swing.GroupLayout.DEFAULT_SIZE, 372, Short.MAX_VALUE)
                    .addComponent(projet, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(annee1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(list, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bulletin))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(signe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(14, 14, 14))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(bulletin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(6, 6, 6)
                        .addComponent(list, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(signe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(projet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(contrat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(annee1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
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
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        jMenu1.setText("X");
        jMenu1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
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
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setBounds(80, 20, 956, 565);
    }// </editor-fold>//GEN-END:initComponents

    private void tblebultinMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblebultinMouseClicked
signe.setText("BLT");
        int row= tblebultin.getSelectedRow();
        String Table_click = (tblebultin.getModel().getValueAt(row,3). toString());
      
    }//GEN-LAST:event_tblebultinMouseClicked

    private void tblebultinMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblebultinMousePressed
        //doubleclick();

        // TODO add your handling code here:
    }//GEN-LAST:event_tblebultinMousePressed

    private void jTable3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable3MouseClicked
signe.setText("SB");        //        show_photo_from_db();        // TODO add your handling code here:
    }//GEN-LAST:event_jTable3MouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
delete();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
if(bulletin.isSelected()){
reports_all();
}else if(list.isSelected()){
reports_allfiche();
}          // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jMenu1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu1MouseClicked
this.dispose();           // TODO add your handling code here:
    }//GEN-LAST:event_jMenu1MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
if(signe.getText().equals("SB")){
 save();
call_in_table_AFTER_DATE_active(); 
}else if(signe.getText().equals("MSB")){
savemultiple();
}else{


}
              // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void tbl_projetMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_projetMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tbl_projetMouseClicked

    private void tbl_projetMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_projetMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbl_projetMousePressed

    private void tblebultin2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblebultin2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblebultin2MouseClicked

    private void tblebultin2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblebultin2MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tblebultin2MousePressed

    private void tblempMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblempMouseClicked
signe.setText("MSB");

        int row= tblemp.getSelectedRow();
        String Table_click = (tblemp.getModel().getValueAt(row,0). toString());
        try{
           
             String sql="SELECT PROJECT2 AS Projet, SALAIRE as Montant FROM contract where   CONTRACT='"+contrat.getSelectedItem()+"' and ROLL='"+Table_click+"'";
     //  String sql="SELECT ID, `NUM_FACTURE`, `DATE_FACTURE`, `DESCRIPTION`, `DEBIT_QTY`, `DEBIT_PU`, `DEBIT_PT`, `DEBIT_TVA`, `CREDIT_QTY`, `CREDIT_PU`, `CREDIT_PT`, `CREDIT_TVA`, `DATES`, `NUM` FROM facture_fournisseur where compte='"+jComboBox6.getSelectedItem()+"'";
       pst = con.prepareStatement(sql);
      rs= pst.executeQuery();
      
       
    tblebultin2.setModel(DbUtils.resultSetToTableModel(rs));
       mode=new DefaultTableModel();
        
        TableColumn col0=tblebultin2.getColumnModel().getColumn(0);
        TableColumn col1=tblebultin2.getColumnModel().getColumn(1);
      //  TableColumn col2=jTable6.getColumnModel().getColumn(2);
//        TableColumn col3=jTable3.getColumnModel().getColumn(3);
       // TableColumn col4=jTable1.getColumnModel().getColumn(4);
           
        col0.setPreferredWidth(300);
       col1.setPreferredWidth(100);
 }catch(SQLException ex ){
     JOptionPane.showMessageDialog(null, ex);
}   
    DefaultTableModel dm = (DefaultTableModel)tblebultinemp.getModel();
dm.getDataVector().removeAllElements();
dm.fireTableDataChanged();
        TableModel model1 =tblemp.getModel();
        int indexs[]=tblemp.getSelectedRows();
        Object[] rowS = new Object[4];
        DefaultTableModel model2 = (DefaultTableModel) tblebultinemp.getModel();
       
       
        for(int i=0; i < indexs.length;i++){
       //      String projet = (tbl_projet.getModel().getValueAt(indexs[i],0). toString());
           //  String bb = (tbl_dispacting.getModel().getValueAt(indexs[i],2). toString());
           
            
           
            
        rowS[0]= model1.getValueAt(indexs[i],0);
        rowS[1]= model1.getValueAt(indexs[i],1);
      //  row[1]= "";
      
        model2.addRow(rowS);
        
        TableColumn col0=tblebultinemp.getColumnModel().getColumn(0);
        TableColumn col1=tblebultinemp.getColumnModel().getColumn(1);
           
        col0.setPreferredWidth(100);
       col1.setPreferredWidth(250);
//        
        }  
        tblemp.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);// TODO add your handling code here:
    }//GEN-LAST:event_tblempMouseClicked

    private void tblempMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblempMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tblempMousePressed

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
   TableModel model1 =tbl_projet.getModel();
        int indexs[]=tbl_projet.getSelectedRows();
        Object[] row = new Object[4];
        DefaultTableModel model2 = (DefaultTableModel) tblebultin2.getModel();
       
       
        for(int i=0; i < indexs.length;i++){
       //      String projet = (tbl_projet.getModel().getValueAt(indexs[i],0). toString());
           //  String bb = (tbl_dispacting.getModel().getValueAt(indexs[i],2). toString());
           
            
           
            
        row[0]= model1.getValueAt(indexs[i],0);
        row[1]= "";
      
        model2.addRow(row);
        
        // jTable3.setModel(DbUtils.resultSetToTableModel(rs));
      
       
       
        TableColumn col0=tblebultin2.getColumnModel().getColumn(0);
        TableColumn col1=tblebultin2.getColumnModel().getColumn(1);
      //  TableColumn col2=jTable6.getColumnModel().getColumn(2);
//        TableColumn col3=jTable3.getColumnModel().getColumn(3);
       // TableColumn col4=jTable1.getColumnModel().getColumn(4);
           
        col0.setPreferredWidth(300);
       col1.setPreferredWidth(100);
     //  col2.setPreferredWidth(50);
//       col3.setPreferredWidth(50);
      // col4.setPreferredWidth(50);
//        
        }
       /* TableModel model1 =tbl_projet.getModel();
        int indexs[]=tbl_projet.getSelectedRows(); 
          Object[] row = new Object[1];
            String Sb=null;
        for(int i=0; i < indexs.length;i++){
             String projet = (tbl_projet.getModel().getValueAt(indexs[i],0). toString());
        
        row[0]= model1.getValueAt(indexs[i],0);
     projetarray.setText(projetarray.getText()+"'"+projet+"',");
    StringBuffer sb= new StringBuffer(projetarray.getText());
    sb.deleteCharAt(sb.length()-1);
     Sb=sb.toString();
}  
   String projets=null,emps=null;     
    for(int j=0;j<tbl_projet.getRowCount();j++){
     projets = (tbl_projet.getModel().getValueAt(indexs[j],0). toString());
                   for(int k=0;k<tblemp.getColumnCount();k++){
     emps = (tblemp.getModel().getValueAt(indexs[k],0). toString());
                     
                   }

    }  
    /*
         try{
    String sql="INSERT INTO `salaire`(`NAME`, `LNAME`, `TITLE`, `CONTRACT`, `ROLL`, `DATE_IN`, `CURRENT_DATES`, `DATE_OUT`, `INSS`, `DAYS_IN`, `DAYS_OFF`, `DAYS_SICK`, `RESTING_DAYS`, `SAILAIR_BASE`, `CHILD`, `CURENCY`, `TRANSPORT`, `ACCOMODATION`, `ALL_FAM`, `EXTRA_HOUR`, `MOTIVATION`, `OTHER`, `CNSS`, `IPR`, `INPP`, `ONEM`, `ADV`, `SALAIRE_BRUTE`, `SALAIRE_NET_BASE`, `SALAIRE_NET`,`MONTH`,`NUM`,`PRINT`,`CNSS_13`,`CNSS_TOUS`,`DATES`,`ANNEE`,PROJECT,SALAIRE_BASE_M) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"; 
    pst=con.prepareStatement(sql);
    pst.setString(1,emps);
    pst.setString(2,emps);
    pst.setString(3,emps);
    pst.setString(4,contrat.getSelectedItem().toString());
    pst.setString(5,emps);
    pst.setString(6,Date_in);
    pst.setString(7,Curent);
    pst.setString(8,Date_Out);     
    pst.setString(9,Inss);
    pst.setDouble(10,DAYS_IN);
    pst.setDouble(11,DAYS_OUT);
    pst.setDouble(12,DAYS_M);
    pst.setDouble(13,RESTING_DAY);
    pst.setDouble(14,SALAIRE);
    pst.setString(15,Child);   
    pst.setString(16,"USD");
    pst.setDouble(17,Transport);
    pst.setDouble(18,Accommodation);
    pst.setDouble(19,All_Fam);
    pst.setString(20,"0");
    pst.setDouble(21,Accouragement);
    pst.setDouble(22,Autre);
    pst.setString(23,cnss.getText());
    pst.setString(24,ipr.getText());
    pst.setDouble(25,Inpp);
    pst.setDouble(26,Oneme);
    pst.setDouble(27,Advance);
    pst.setDouble(28,salaire_brut);
    pst.setDouble(29,Salairetot);
    pst.setDouble(30,Sal_Net);
    pst.setString(31,jComboBox2.getSelectedItem().toString());
    pst.setString(32,rolls);
    pst.setString(33,"Print");
    pst.setString(34,cnsstou13.getText());
    pst.setString(35,cnsstous.getText());   
    pst.setString(36,webDateField1.getText());
    SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy");
    String Anne = dateFormat2.format(webDateField1.getDate());
    pst.setString(37,Anne);
    pst.setString(38,projets);
    pst.setDouble(39,Salaire_brut_base_modifie);
    pst.executeUpdate();
    }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}
 */
 
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
signe.setText("MSB");
        TableModel model1 =tblemp.getModel();
        int indexs[]=tblemp.getSelectedRows();
        Object[] row = new Object[4];
        DefaultTableModel model2 = (DefaultTableModel) tblebultinemp.getModel();
       
       
        for(int i=0; i < indexs.length;i++){
       //      String projet = (tbl_projet.getModel().getValueAt(indexs[i],0). toString());
           //  String bb = (tbl_dispacting.getModel().getValueAt(indexs[i],2). toString());
           
            
           
            
        row[0]= model1.getValueAt(indexs[i],0);
        row[1]= model1.getValueAt(indexs[i],1);
      //  row[1]= "";
      
        model2.addRow(row);
        
        TableColumn col0=tblebultinemp.getColumnModel().getColumn(0);
        TableColumn col1=tblebultinemp.getColumnModel().getColumn(1);
           
        col0.setPreferredWidth(100);
       col1.setPreferredWidth(250);
//        
        }        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel4MouseClicked

    private void tblebultinempMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblebultinempMouseClicked
//tblemp.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);        // TODO add your handling code here:
    }//GEN-LAST:event_tblebultinempMouseClicked

    private void tblebultinempMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblebultinempMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tblebultinempMousePressed

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
try{
  String sql="SELECT NAME,LNAME,ROLLNUM FROM EMPLOYEE where NAME LIKE '"+jTextField1.getText()+"%' or LNAME  LIKE '"+jTextField1.getText()+"%' or ROLLNUM  LIKE '"+jTextField1.getText()+"%'  and ACTIVE='Active' ORDER BY NAME";
     pst = con.prepareStatement(sql);
      rs= pst.executeQuery();
      
       
tblemp.setModel(DbUtils.resultSetToTableModel(rs));
       mode=new DefaultTableModel();
 }catch(SQLException ex ){
     JOptionPane.showMessageDialog(null, ex);}            // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1KeyReleased

    private void projetPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_projetPopupMenuWillBecomeInvisible
signe.setText("SB");
        contrat.removeAllItems();
        try{
            String sqls="select contract from  type_contract where project='"+projet.getSelectedItem()+"'";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
            while(rs.next()){
              String a = rs.getString("contract");
              
               contrat.addItem(a);
              
            }
            }
        catch(Exception ex){
         JOptionPane.showMessageDialog(null, ex);}
        call_in_table_AFTER_DATE_active();         // TODO add your handling code here:
    }//GEN-LAST:event_projetPopupMenuWillBecomeInvisible

    private void contratActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_contratActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_contratActionPerformed

    private void contratPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_contratPopupMenuWillBecomeInvisible
signe.setText("SB");
        call_in_table_AFTER_DATE_active();          // TODO add your handling code here:
    }//GEN-LAST:event_contratPopupMenuWillBecomeInvisible

    private void jComboBox4PopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_jComboBox4PopupMenuWillBecomeInvisible
call_in_table_AFTER_DATE_active();        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox4PopupMenuWillBecomeInvisible

    private void annee1PopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_annee1PopupMenuWillBecomeInvisible
call_in_table_AFTER_DATE_active();        // TODO add your handling code here:
    }//GEN-LAST:event_annee1PopupMenuWillBecomeInvisible

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
 int indexs[]=tblebultin2.getSelectedRows();
        
        DefaultTableModel model = (DefaultTableModel) tblebultin2.getModel();
        for(int i=0; i < indexs.length;i++){
          
    int getSelectedRowsForDeletion = tblebultin2.getSelectedRow();
        model.removeRow(getSelectedRowsForDeletion);
        
        }        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel5MouseClicked

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
int indexs[]=tblebultinemp.getSelectedRows();
        
        DefaultTableModel model = (DefaultTableModel) tblebultinemp.getModel();
        for(int i=0; i < indexs.length;i++){
          
    int getSelectedRowsForDeletion = tblebultinemp.getSelectedRow();
        model.removeRow(getSelectedRowsForDeletion);
        
        }         // TODO add your handling code here:
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jTabbedPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTabbedPane1MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Palette.ComboBoxSuggestion annee1;
    private javax.swing.JRadioButton bulletin;
    private Palette.ComboBoxSuggestion contrat;
    private javax.swing.JEditorPane emparray;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private Palette.ComboBoxSuggestion jComboBox4;
    private javax.swing.JEditorPane jEditorPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable3;
    private Palette.TextField jTextField1;
    private javax.swing.JRadioButton list;
    private Palette.ComboBoxSuggestion projet;
    private javax.swing.JEditorPane projetarray;
    private javax.swing.JLabel signe;
    private javax.swing.JTable tbl_projet;
    private javax.swing.JTable tblebultin;
    private javax.swing.JTable tblebultin2;
    private javax.swing.JTable tblebultinemp;
    private javax.swing.JTable tblemp;
    // End of variables declaration//GEN-END:variables
}
