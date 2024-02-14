/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intreprisemanagementsystem;


import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
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
import net.sf.jasperreports.view.JasperViewer;
//import net.sf.jasperreports.engine.JasperCompileManager;
//import net.sf.jasperreports.engine.JasperFillManager;
//import net.sf.jasperreports.engine.JasperPrint;
//import net.sf.jasperreports.engine.JasperReport;
//import net.sf.jasperreports.engine.design.JRDesignQuery;
//import net.sf.jasperreports.engine.design.JasperDesign;
//import net.sf.jasperreports.engine.xml.JRXmlLoader;
//import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author DOSHE
 */
public class worker extends javax.swing.JInternalFrame {

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
    public worker() {
        initComponents();
              con=JavaDbConnect.dbConnect();
           this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI bui= (BasicInternalFrameUI) this.getUI();
       bui.setNorthPane(null);
       
          Call_PROJECT_NAME();
        Call_IN_LISTs();
      
     //   jDateChooser1.setDate(new Date("yyyy-MM-dd"));
       SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
String addDate = dateFormats.format(new Date());
date.setText(addDate);
    }
 public void Call_PROJECT_NAME()
    {
               
         {
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
          JOptionPane.showMessageDialog(null, ex);    
        }
    }
    }
     
  
          
          //SAVE
     
      public void savemateriauxs()
    {
        try {
      
        PreparedStatement pst = con.prepareStatement("INSERT INTO personelnonactive(NOM,PNOM,PRNOM,ADRESSE,FONCTION,MONTAT,JOURS,NET_A_PAYER,CHANTIER,DATES) "
        +"value(?,?,?,?,?,?,?,?,?,?)");
        
        pst.setString(1, nom.getText());
        pst.setString(2, pnom.getText());
        pst.setString(3, prnom.getText());
        pst.setString(4, adresse.getText());
        pst.setString(5, fonction.getText()); 
        pst.setString(6, montat.getText());
        pst.setString(7, nbrejrs.getText());
        pst.setString(8, netat.getText());
       
        pst.setString(9, buss.getSelectedItem().toString());
         pst.setString(10,date.getText());
        pst.executeUpdate();
        
               //  JOptionPane.showMessageDialog(null,"data saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
    
    }
      
      public void empnonactivdeletes()
            
            
            
    {

            
         try{
        String sql = "DELETE FROM  personelnonactive  WHERE ID=? and CHANTIER=?";
        
         pst = con.prepareStatement(sql);
         pst.setString(1,jTextField1.getText());
         
        pst.setString(2, buss.getSelectedItem().toString());
         pst.executeUpdate();
         //JOptionPane.showMessageDialog(null,"delete");
              
     }catch(SQLException | HeadlessException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }  
      
    } 
    
   // DELETE
    public void deletes()
            
            
            
    {
            
         try{
        String sql = "DELETE FROM  personelnonactive  WHERE ID=?";
        
         pst = con.prepareStatement(sql);
         pst.setString(1,jTextField1.getText());
         pst.executeUpdate();
         //JOptionPane.showMessageDialog(null,"delete");
              
     }catch(SQLException | HeadlessException ex ){
         JOptionPane.showMessageDialog(null,ex);
     } 
    }
     
    
    
  public void uptade(){
    try{
             
        String sql = "UPDATE `personelnonactive` SET `NOM`=?,`PNOM`=?,`PRNOM`=?,`ADRESSE`=?,`FONCTION`=?,`MONTAT`=?,`JOURS`=?,NET_A_PAYER=JOURS*MONTAT,`CHANTIER`=? `DATE`=?  WHERE ID=? ";
        
         pst = con.prepareStatement(sql);
          pst.setString(9, jTextField1.getText());
          pst.setString(1, nom.getText());
        pst.setString(2, pnom.getText());
        pst.setString(3, prnom.getText());
        pst.setString(4, adresse.getText());
        pst.setString(5, fonction.getText()); 
        pst.setString(6, montat.getText());
        pst.setString(7, nbrejrs.getText());
       pst.setString(10, date.getText());
       
        pst.setString(8, buss.getSelectedItem().toString());
        pst.executeUpdate();
        
        
     }catch(SQLException | HeadlessException ex ){
         JOptionPane.showMessageDialog(null,ex);
     
    
} 
    }
    
    
    
    String check;
     public void select_num_comte(){
     
      try{
            String sql="SELECT DATES FROM  personelnonactive WHERE `CHANTIER`='"+buss.getSelectedItem().toString()+"'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
               check=(rs.getString("DATES"));
               
                 
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
    
    }
    
    
    
    //UPDATE
     
     
     
     /// CLERCK
     
     public void clercks(){
               SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
String addDate = dateFormats.format(new Date());
        String tmp=buss.getSelectedItem().toString();
     if(jComboBox8.getSelectedItem().equals("Present")){
      try{        
        String sql = "UPDATE  personelnonactive SET JOURS=JOURS+1,NET_A_PAYER=JOURS*MONTAT,DATES='"+addDate +"' where CHANTIER='"+tmp+"' and ID='"+jTextField1.getText()+"'";
         pst = con.prepareStatement(sql);
         pst.executeUpdate();
     }catch(SQLException | HeadlessException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }
     
     }else if(jComboBox8.getSelectedItem().equals("Absent")){
     
      try{
      
        // nbrejrs.setText(null);
             
        String sql = "UPDATE  personelnonactive SET JOURS=JOURS-1,NET_A_PAYER=JOURS*MONTAT,DATES='"+addDate +"' where CHANTIER='"+tmp+"' and ID='"+jTextField1.getText()+"'";
        
         pst = con.prepareStatement(sql);
         
         pst.executeUpdate();
       
     }catch(SQLException | HeadlessException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }
     }else if(jComboBox8.getSelectedItem().equals("Present All")){
     try{        
        String sql = "UPDATE  personelnonactive SET JOURS=JOURS+1,NET_A_PAYER=JOURS*MONTAT,DATES='"+addDate +"' where CHANTIER='"+tmp+"'";
         pst = con.prepareStatement(sql);
         pst.executeUpdate();
     }catch(SQLException | HeadlessException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }
     
     
     }else if(jComboBox8.getSelectedItem().equals("Absent All")){
     
      try{
      
        // nbrejrs.setText(null);
             
        String sql = "UPDATE  personelnonactive SET JOURS=JOURS-1,NET_A_PAYER=JOURS*MONTAT,DATES='"+addDate +"' where CHANTIER='"+tmp+"'";
        
         pst = con.prepareStatement(sql);
         
         pst.executeUpdate();
       
     }catch(SQLException | HeadlessException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }
     }
     
     }
     public void update_materiauxs()
             
    {
        SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
String addDate = dateFormats.format(new Date());
        String tmp=buss.getSelectedItem().toString();
    
        if( nbrejrs.getText().isEmpty()){
            select_num_comte();
            if(check.equals(date.getText())){
            
             JOptionPane.showMessageDialog(null,"YOU CAN NOT CLERK TWINCE");
            }else{
            
        
     if (jComboBox8.getSelectedItem().equals("Present")){
        
         try{
            
         
             
        String sql = "UPDATE  personelnonactive SET JOURS=JOURS+1,NET_A_PAYER=JOURS*MONTAT,DATES='"+addDate +"' where CHANTIER='"+tmp+"'";
        
         pst = con.prepareStatement(sql);
         
         pst.executeUpdate();
       
     }catch(SQLException | HeadlessException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }
    }else if(jComboBox8.getSelectedItem().equals("Absent")) {   
      try{
      
        // nbrejrs.setText(null);
             
        String sql = "UPDATE  personelnonactive SET JOURS=JOURS-1,NET_A_PAYER=JOURS*MONTAT,DATES='"+addDate +"' where CHANTIER='"+tmp+"'";
        
         pst = con.prepareStatement(sql);
         
         pst.executeUpdate();
       
     }catch(SQLException | HeadlessException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }
    }
            }
        }else{
             
        
        
        if (jComboBox8.getSelectedItem().equals("PRESENT")){
        String s = nbrejrs.getText();
        int billNumber = Integer.valueOf(s);
        s = Integer.toString(++billNumber);
        nbrejrs.setText(s);
         try{
         
             
        String sql = "UPDATE  personelnonactive SET JOURS=?,NET_A_PAYER=JOURS*MONTAT ,DATES='"+addDate +"' where ID=?";
        
         pst = con.prepareStatement(sql);
         pst.setString(2,jTextField1.getText());
          pst.setString(1,nbrejrs.getText());
         pst.executeUpdate();
         nom.setText(null);
       pnom.setText(null);
       prnom.setText(null);
       adresse.setText(null);
       fonction.setText(null);
       montat.setText(null);
       nbrejrs.setText(null);
       jTextField1.setText(null);
       
     }catch(SQLException | HeadlessException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }
    }else if(jComboBox8.getSelectedItem().equals("ABSENT")) {   
      try{
         String s = nbrejrs.getText();
        int billNumber = Integer.valueOf(s);
        s = Integer.toString(--billNumber);
        nbrejrs.setText(s);
             
        String sql = "UPDATE  personelnonactive SET JOURS=?,NET_A_PAYER=JOURS*MONTAT ,DATES='"+addDate +"'  where ID=?";
        
         pst = con.prepareStatement(sql);
         pst.setString(2,jTextField1.getText());
          pst.setString(1,nbrejrs.getText());
         
         pst.executeUpdate();
       nom.setText(null);
       pnom.setText(null);
       prnom.setText(null);
       adresse.setText(null);
       fonction.setText(null);
       montat.setText(null);
       nbrejrs.setText(null);
         jTextField1.setText(null);
         
     }catch(SQLException | HeadlessException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }
    }
        
        
        
        }
    

    }
    

     //CALL IN LIST
        public void Call_IN_LISTs()
    {
        DefaultListModel list = new DefaultListModel();

        try{
            
            
            String tmp =buss.getSelectedItem().toString();
            
            String sql="select NOM,JOURS from personelnonactive where  CHANTIER= '"+tmp+"'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                String sum=rs.getString("NOM");
                String sumS=rs.getString("JOURS");
                 list.addElement(sumS+"                 "+sum);
             
            }
            }
        catch(Exception ex){
          //JOptionPane.showMessageDialog(null, ex);    
      // // }  
    }
    }
         public void select_jTables()
   {
        try{
          int row= jTable1.getSelectedRow();
          String Table_click = (jTable1.getModel().getValueAt(row,1). toString());
          String sql = "SELECT * FROM personelnonactive WHERE nom= '"+Table_click+"' and chantier='"+buss.getSelectedItem().toString()+"'";
          pst = con.prepareStatement(sql);
          rs=pst.executeQuery();
          if(rs.next()){
         String add1 = rs.getString("ID");
              jTextField1.setText(add1);
              String add8 = rs.getString("NOM");
              nom.setText(add8);
               String add2 = rs.getString("PNOM");
              pnom.setText(add2);
               String add3 = rs.getString("PRNOM");
               prnom.setText(add3);
               String add4 = rs.getString("ADRESSE");
              adresse.setText(add4);
               String add5 = rs.getString("FONCTION");
              fonction.setText(add5);
               
               String add6 = rs.getString("MONTAT");
               montat.setText(add6);
               String add7 = rs.getString("JOURS");
             nbrejrs.setText(add7);
              
            
          }
         
    }catch(SQLException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }
   }
         
          public void update_emps()
    {
      
         try{
             
        String sql = "UPDATE personelnonactive SET NOM=?,PNOM=?,PRNOM=?,ADRESSE=?,FONCTION=?,MONTAT=?,CHANTIER=? WHERE ID=?";
        
         pst = con.prepareStatement(sql);
         pst.setString(8,jTextField1.getText());
         pst.setString(1,nom.getText());
          pst.setString(2,pnom.getText());
        pst.setString(3,prnom.getText());
            pst.setString(4,adresse.getText());
             pst.setString(5,fonction.getText());
              pst.setString(6,montat.getText());
              
         pst.setString(7, buss.getSelectedItem().toString()); 
                    
         pst.executeUpdate();
         //JOptionPane.showMessageDialog(null,"updaded");    
     }catch(SQLException | HeadlessException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }
          
       
    }        
     
      //SEARCH AND ADDREADER
     
    
     public void tables(){
                  
      try{
           
       String sql="SELECT `DATES`,`NOM`, `PRNOM`, `FONCTION`, `MONTAT`, `JOURS`, `NET_A_PAYER` FROM `personelnonactive` WHERE `CHANTIER`='"+buss.getSelectedItem().toString()+"'";
     //                           `NOM`, `PNOM`, `PRNOM`, `ADRESSE`, `FONCTION`, `MONTAT`, `JOURS`, `NET_A_PAYER`, `CHANTIER`, `DATES`
       pst = con.prepareStatement(sql);
      rs= pst.executeQuery();
      
       
       jTable1.setModel(DbUtils.resultSetToTableModel(rs));
       mode=new DefaultTableModel();
       
       
        TableColumn col0=jTable1.getColumnModel().getColumn(0);
        TableColumn col1=jTable1.getColumnModel().getColumn(1);
        TableColumn col2=jTable1.getColumnModel().getColumn(2);
        TableColumn col3=jTable1.getColumnModel().getColumn(3);
        TableColumn col4=jTable1.getColumnModel().getColumn(4);
        TableColumn col5=jTable1.getColumnModel().getColumn(5);
         TableColumn col6=jTable1.getColumnModel().getColumn(6);
        
       
      
       
       col0.setPreferredWidth(30);
       col1.setPreferredWidth(100);
       col2.setPreferredWidth(100);
       col3.setPreferredWidth(30);
       col4.setPreferredWidth(30);
       col5.setPreferredWidth(30);
        col6.setPreferredWidth(30);
       
  
      
       
       
      // jTable1.setModel(mode);
       
     
    }catch(SQLException ex ){
     JOptionPane.showMessageDialog(null, ex);
}
               }
      
    
              public void reports()
     {
          String tmps =buss.getSelectedItem().toString();
     
             try{
                 
                   String sqls="Select path from pathn";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
            if(rs.next()){
             
                String sum=rs.getString("path");
                  
                 String NameFiles=sum;
            
            String NameFile=""+NameFiles+"fichedepresencesurchantier.jrxml";
                 
                 
                 
                JasperDesign jd=JRXmlLoader.load(NameFile);
                //JasperDesign jd=JRXmlLoader.load("C:\\Users\\Dosh\\Documents\\NetBeansProjects\\intreprisemanagementsystem\\src\\intreprisemanagementsystem\\fichedepaiesurchantier.jrxml");
                String sql="select * from personelnonactive where CHANTIER ='"+tmps+"'";
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
               public void report1s()      // JasperDesign jd=JRXmlLoader.load("C:\\Users\\Dosh\\Documents\\NetBeansProjects\\intreprisemanagementsystem\\src\\intreprisemanagementsystem\\fichedepresencesurchantier.jrxml");
     {
          String tmps =buss.getSelectedItem().toString();
     
             try{
                 
                   String sqls="Select path from pathn";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
            if(rs.next()){
             
                String sum=rs.getString("path");
                  
                 String NameFiles=sum;
            
            String NameFile=""+NameFiles+"fichedepaiesurchantier.jrxml";
                 
                 
                 
                JasperDesign jd=JRXmlLoader.load(NameFile);
                //JasperDesign jd=JRXmlLoader.load("C:\\Users\\Dosh\\Documents\\NetBeansProjects\\intreprisemanagementsystem\\src\\intreprisemanagementsystem\\fichedepaiesurchantier.jrxml");
                String sql="select * from personelnonactive where chantier ='"+tmps+"'";
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
        jPanel5 = new javax.swing.JPanel();
        jComboBox8 = new javax.swing.JComboBox<>();
        date = new com.alee.extended.date.WebDateField();
        jPanel4 = new javax.swing.JPanel();
        nom = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        pnom = new javax.swing.JTextField();
        prnom = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        adresse = new javax.swing.JTextField();
        fonction = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        montat = new javax.swing.JTextField();
        nbrejrs = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        recherche1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        netat = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        buss = new javax.swing.JComboBox<>();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jComboBox8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jComboBox8.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Clerck", "Present", "Absent", "Present All", "Absent All" }));
        jComboBox8.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                jComboBox8PopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        jComboBox8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox8ActionPerformed(evt);
            }
        });

        date.setText("01.12.2019");
        date.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jComboBox8, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(date, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(date, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        nom.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jLabel33.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel33.setText("Name:");
        jLabel33.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jLabel34.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel34.setText("L.Name:");
        jLabel34.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        pnom.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        prnom.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jLabel35.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel35.setText("P.Name:");
        jLabel35.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jLabel36.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel36.setText("Address:");
        jLabel36.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        adresse.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        fonction.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jLabel37.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel37.setText("Function:");
        jLabel37.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jLabel38.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel38.setText("Amount/Days:");
        jLabel38.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        montat.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        montat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                montatKeyTyped(evt);
            }
        });

        nbrejrs.setEditable(false);
        nbrejrs.setBackground(new java.awt.Color(240, 240, 241));
        nbrejrs.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jLabel32.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        recherche1.setForeground(new java.awt.Color(240, 240, 240));

        jTextField1.setEditable(false);
        jTextField1.setBackground(new java.awt.Color(240, 240, 241));

        netat.setEditable(false);
        netat.setBackground(new java.awt.Color(240, 240, 241));
        netat.setText("0");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(recherche1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(netat, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel38, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel33, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel34, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel35, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel36, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel37, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pnom)
                            .addComponent(prnom)
                            .addComponent(adresse)
                            .addComponent(fonction)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(montat, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(nbrejrs, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE))
                            .addComponent(nom))))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel33))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pnom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel34))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(prnom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel35))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel36)
                    .addComponent(adresse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fonction, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel37))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(montat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nbrejrs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel38))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(netat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(recherche1))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jLabel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable1.setRowHeight(24);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 779, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 410, Short.MAX_VALUE)
                .addContainerGap())
        );

        buss.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(buss, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(buss, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jMenu3.setText("X");
        jMenu3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jMenu3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu3MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu3);

        jMenu1.setText("File");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setText("Save");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem2.setText("Delete");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Print");

        jMenuItem3.setText("Attendance Sheet");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem3);

        jMenuItem4.setText("Pay Slip");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem4);

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
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenu3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu3MouseClicked
this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu3MouseClicked

    private void jComboBox8PopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_jComboBox8PopupMenuWillBecomeInvisible
// update_materiauxs();
clercks();
  tables(); 
  jTextField1.setText("");
            // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox8PopupMenuWillBecomeInvisible

    private void jComboBox8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox8ActionPerformed

     //   update_materiauxs();
        //AddModels();
        // ReadDatas("select * from personelnonactive"); // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox8ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        select_jTables();
        //  jComboBox7.setSelectedItem("PROJET_LOC");
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable1MouseClicked

    private void bussPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_bussPopupMenuWillBecomeInvisible
//Call_ID_TO_BOMBOBOX1s(); 
tables();// TODO add your handling code here:
    }//GEN-LAST:event_bussPopupMenuWillBecomeInvisible

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
nbrejrs.setText("0");
if(jTextField1.getText().isEmpty()){
        savemateriauxs();
}else{
        update_emps();
}
        nom.setText(null);
pnom.setText(null);
prnom.setText(null);
adresse.setText(null);
fonction.setText(null);
montat.setText(null);
nbrejrs.setText(null);
jTextField1.setText(null);
jTextField1.setText(null);
  tables(); // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
deletes(); 
    nom.setText(null);
pnom.setText(null);
prnom.setText(null);
adresse.setText(null);
fonction.setText(null);
montat.setText(null);
nbrejrs.setText(null);
jTextField1.setText(null);
  tables(); // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
reports();         // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
report1s();         // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void montatKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_montatKeyTyped
   char c=evt.getKeyChar();
        if(!(Character.isDigit(c) || (c==KeyEvent.VK_BACK_SPACE)||c==KeyEvent.VK_DELETE ||c==KeyEvent.VK_PERIOD )){
            evt.consume();
        }        // TODO add your handling code here:
    }//GEN-LAST:event_montatKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField adresse;
    private javax.swing.JComboBox<String> buss;
    private com.alee.extended.date.WebDateField date;
    private javax.swing.JTextField fonction;
    private javax.swing.JComboBox<String> jComboBox8;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField montat;
    private javax.swing.JTextField nbrejrs;
    private javax.swing.JTextField netat;
    private javax.swing.JTextField nom;
    private javax.swing.JTextField pnom;
    private javax.swing.JTextField prnom;
    private javax.swing.JLabel recherche1;
    // End of variables declaration//GEN-END:variables
}
