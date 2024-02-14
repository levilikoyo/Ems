/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intreprisemanagementsystem;

import com.alee.laf.WebLookAndFeel;
import static intreprisemanagementsystem.homme.jDesktopPane1;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import java.util.Date;
import javax.swing.JProgressBar;
import javax.swing.Timer;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.table.TableColumn;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author DOSHE
 */
public class logs extends javax.swing.JInternalFrame {

    Connection con=null;
PreparedStatement pst=null;
ResultSet rs= null;
//DefaultTableModel mode;
 String rolls;
 String NUM_ID;
   Timer timer;
 DefaultTableModel mode; 
    public logs() {
        initComponents();
              con=JavaDbConnect.dbConnect();
         WebLookAndFeel.install(true);
         this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI bui= (BasicInternalFrameUI) this.getUI();
       bui.setNorthPane(null);
       
     //  webDateField1.setDate(new Date());
       //lock_employee();
       lock_finance();
       loading_finance();
        jProgressBar_essence.setValue(50);
        int x=0;
        int y=100;

        jProgressBar_essence= new JProgressBar(JProgressBar.VERTICAL);
       // JProgressBar progress = new JProgressBar(JProgressBar.VERTICAL);
      // jProgressBar_essence
      
    }
     public void lock_finance(){
    
    tablodebor.setEnabled(false);
    stateofnee.setEnabled(false);
    logistique.setEnabled(false);
    
    addmateriel.setEnabled(false);
    add_equipement.setEnabled(false);
    materiel_out.setEnabled(false);
    quipement_out.setEnabled(false);
    invent_mat.setEnabled(false);
   invent_equi.setEnabled(false);
    
    }
     public void loading_finance(){
     
      
            try{
            String sql="select ACESS from user_write where NAME='"+homme.user.getText()+"' and CHAMP='Tableau de Bord'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
          String  caisse= rs.getString("ACESS");
          if(caisse.equals("Yes")){
         tablodebor.setEnabled(true); 
          }else{
         tablodebor.setEnabled(false);
          }
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex); } 
            
              try{
            String sql="select ACESS from user_write where NAME='"+homme.user.getText()+"' and CHAMP='Logistc Docs'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
          String  caisse= rs.getString("ACESS");
          if(caisse.equals("Yes")){
          logistique.setEnabled(true); 
          }else{
        logistique.setEnabled(false);
          }
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex); } 
              
                    try{
            String sql="select ACESS from user_write where NAME='"+homme.user.getText()+"' and CHAMP='State of Need'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
          String  caisse= rs.getString("ACESS");
          if(caisse.equals("Yes")){
          stateofnee.setEnabled(true); 
          }else{
         stateofnee.setEnabled(false);
          }
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex); } 
                    
                    
                    
                    
                     try{
            String sql="select ACESS from user_write where NAME='"+homme.user.getText()+"' and CHAMP='Add Materiel'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
           String  caisse= rs.getString("ACESS");
           admateriel.setText(caisse);
          if(caisse.equals("Yes")){
          addmateriel.setEnabled(true); 
          }else{
         addmateriel.setEnabled(false);
          }
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex); } 
                     
         try{
            String sql="select ACESS from user_write where NAME='"+homme.user.getText()+"' and CHAMP='Add Equipement'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
              adequipement.setText(rs.getString("ACESS"));
            }
             if(adequipement.getText().equals("Yes")){
          add_equipement.setEnabled(true); 
          }else{
         add_equipement.setEnabled(false);
          }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex); } 
         
         
         try{
            String sql="select ACESS from user_write where NAME='"+homme.user.getText()+"' and CHAMP='Materiel Out'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
            materielout.setText(rs.getString("ACESS"));
            }
             if(materielout.getText().equals("Yes")){
          materiel_out.setEnabled(true); 
          }else{
         materiel_out.setEnabled(false);
          }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex); } 
         
         
         
         try{
            String sql="select ACESS from user_write where NAME='"+homme.user.getText()+"' and CHAMP='Invent. Mat'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
             inv_mat.setText(rs.getString("ACESS"));
            }
             if(inv_mat.getText().equals("Yes")){
          invent_mat.setEnabled(true); 
          }else{
         invent_mat.setEnabled(false);
          }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex); } 
         
         
         try{
            String sql="select ACESS from user_write where NAME='"+homme.user.getText()+"' and CHAMP='Invent. Equi'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
              inv_qui.setText(rs.getString("ACESS"));
            }
             if(inv_qui.getText().equals("Yes")){
          invent_equi.setEnabled(true); 
          }else{
         invent_equi.setEnabled(false);
          }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex); } 
       
                   try{
            String sql="select ACESS from user_write where NAME='"+homme.user.getText()+"' and CHAMP='Equipement Out'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
              eqipementout.setText(rs.getString("ACESS"));
            }
             if(eqipementout.getText().equals("Yes")){
          quipement_out.setEnabled(true); 
          }else{
         quipement_out.setEnabled(false);
          }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex); }  
                   
                   
                   
                   
                   
                   
                   try{
            String sql="select ACESS from user_write where NAME='"+homme.user.getText()+"' and CHAMP='Add Equipement 2'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
             adequip2.setText(rs.getString("ACESS"));
            }
             if(adequip2.getText().equals("Yes")){
          equipement2.setEnabled(true); 
          }else{
         equipement2.setEnabled(false);
          }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex); }  
                   
                   
                    try{
            String sql="select ACESS from user_write where NAME='"+homme.user.getText()+"' and CHAMP='Fuel'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
             fuel.setText(rs.getString("ACESS"));
            }
             if(fuel.getText().equals("Yes")){
          Fuel.setEnabled(true); 
          }else{
         Fuel.setEnabled(false);
          }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex); }  
                    
                    
                    try{
            String sql="select ACESS from user_write where NAME='"+homme.user.getText()+"' and CHAMP='Agin Out'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
             agin_out.setText(rs.getString("ACESS"));
            }
             if(agin_out.getText().equals("Yes")){
          Agin.setEnabled(true); 
          }else{
         Agin.setEnabled(false);
          }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex); }  
                    
                    
          try{
            String sql="select ACESS from user_write where NAME='"+homme.user.getText()+"' and CHAMP='Cars'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
             car.setText(rs.getString("ACESS"));
            }
             if(car.getText().equals("Yes")){
          Cars.setEnabled(true); 
          }else{
         Cars.setEnabled(false);
          }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex); }  
          
          
    try{
            String sql="select ACESS from user_write where NAME='"+homme.user.getText()+"' and CHAMP='Docs'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
             doc.setText(rs.getString("ACESS"));
            }
             if(doc.getText().equals("Yes")){
          Docs.setEnabled(true); 
          }else{
         Docs.setEnabled(false);
          }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex); }  
     }
     
              
    //notification
    
     public void combobox2_from_materiel()
    {
        try{
          String sql="select count(validation) from  driver_info where Validation='' ";
          
            pst=con.prepareStatement(sql);
           // pst.setString(1, tmp);
            rs=pst.executeQuery();
            if(rs.next()){
               String add2 = rs.getString("count(validation)");
              bell.setText(add2);
               
             
          }
  
  
  
      }catch(Exception ex){
        JOptionPane.showMessageDialog(null,ex);  
      }
        
        
       
     
        try{
               Date datout=new Date();
 SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd.MM.yyyy");
         String addDate1 = dateFormat1.format(datout);
           String sql="select count(SERIE)  FROM `DOTATION` WHERE STATUS='' and DUREE<= '"+addDate1+"'";
          
            pst=con.prepareStatement(sql);
           // pst.setString(1, tmp);
            rs=pst.executeQuery();
            if(rs.next()){
               String add2 = rs.getString("count(SERIE)");
              bell2.setText(add2);
               
             
          }
  
      }catch(Exception ex){
        JOptionPane.showMessageDialog(null,ex);  
      }
          
        try{
               Date datout=new Date();
 SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd.MM.yyyy");
         String addDate1 = dateFormat1.format(datout);
           String sql="select count(DISTINCT(NUM))  FROM `etat_de_besoin` WHERE PRINT='' and ORIENTATION='LOGISTIQUE'";
          
            pst=con.prepareStatement(sql);
           // pst.setString(1, tmp);
            rs=pst.executeQuery();
            if(rs.next()){
               String add2 = rs.getString("count(DISTINCT(NUM))");
              eb.setText(add2);
               
             
          }
  
      }catch(Exception ex){
        JOptionPane.showMessageDialog(null,ex);  
      }  
    } 
     
     
      public void call_table(){
         try{
             
             
             
             //id = ( SELECT MAX(id)
            String sql="SELECT DESCR as 'Alert Materiaux',QTY as Qty FROM Alert_table WHERE  STATUT='Materiaux'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
         //  doc.setText(rs.getString("ACESS"));
            
           // try{
           
           //  String sql="SELECT `ITEM` FROM `inventairemtr` WHERE (sum(DEBIT)-SUM(CREDIT))=>'"+id.getText()+"'";
     //  String sql="SELECT ID, `NUM_FACTURE`, `DATE_FACTURE`, `DESCRIPTION`, `DEBIT_QTY`, `DEBIT_PU`, `DEBIT_PT`, `DEBIT_TVA`, `CREDIT_QTY`, `CREDIT_PU`, `CREDIT_PT`, `CREDIT_TVA`, `DATES`, `NUM` FROM facture_fournisseur where compte='"+jComboBox6.getSelectedItem()+"'";
       pst = con.prepareStatement(sql);
      rs= pst.executeQuery();
      
       
       alertmateriaux.setModel(DbUtils.resultSetToTableModel(rs));
       mode=new DefaultTableModel();
       
     
      
            }
       
    TableColumn col0=alertmateriaux.getColumnModel().getColumn(0);
        TableColumn col1=alertmateriaux.getColumnModel().getColumn(1);
      
       
       
      
       
       col0.setPreferredWidth(250);
       col1.setPreferredWidth(50);
      
     

            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex); }  
     }
      
          
     
        
         
           public void lock_employee(){
         ActionListener actionListener = new  ActionListener(){
             @Override
             public void actionPerformed(ActionEvent e) {
          combobox2_from_materiel();
          call_table();
             }
         };
         timer = new Timer(1000,actionListener); 
    
timer.start();

//To change body of generated methods, choose Tools | Templates.
             }
          public void lock_buton(){
         ActionListener actionListener = new  ActionListener(){
             @Override
             public void actionPerformed(ActionEvent e) {
            jButton1.setEnabled(false);
            jButton2.setEnabled(false);
             }
         };
         timer = new Timer(9000000,actionListener); 
    
timer.start();

//To change body of generated methods, choose Tools | Templates.
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
        addmateriel = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        bell = new javax.swing.JLabel();
        bell2 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        eb = new javax.swing.JLabel();
        admateriel = new javax.swing.JLabel();
        adequipement = new javax.swing.JLabel();
        materielout = new javax.swing.JLabel();
        eqipementout = new javax.swing.JLabel();
        inv_mat = new javax.swing.JLabel();
        inv_qui = new javax.swing.JLabel();
        adequip2 = new javax.swing.JLabel();
        fuel = new javax.swing.JLabel();
        agin_out = new javax.swing.JLabel();
        car = new javax.swing.JLabel();
        doc = new javax.swing.JLabel();
        inv_qui1 = new javax.swing.JLabel();
        add_equipement = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jTextField4 = new javax.swing.JTextField();
        invent_equi = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        Docs = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        Fuel = new javax.swing.JLabel();
        Agin = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        materiel_out = new javax.swing.JLabel();
        quipement_out = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jTextField8 = new javax.swing.JTextField();
        equipement2 = new javax.swing.JLabel();
        invent_mat = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        jTextField9 = new javax.swing.JTextField();
        jLabel42 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        Cars = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jProgressBar1 = new javax.swing.JProgressBar();
        jProgressBar_essence = new javax.swing.JProgressBar();
        jPanel3 = new javax.swing.JPanel();
        tablodebor = new javax.swing.JButton();
        stateofnee = new javax.swing.JButton();
        logistique = new javax.swing.JButton();
        borrow = new javax.swing.JTextField();
        jButton10 = new javax.swing.JButton();
        returne = new javax.swing.JTextField();
        jButton12 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        logistic_desk = new javax.swing.JDesktopPane();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        etjTable3 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        alertmateriaux = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        etjTable5 = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        etjTable6 = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        addmateriel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_brick_wall_50px.png"))); // NOI18N
        addmateriel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addmaterielMouseClicked(evt);
            }
        });
        jPanel2.add(addmateriel, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 49, -1, 60));

        jTextField1.setEditable(false);
        jPanel2.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 0, -1, 120));

        jTextField2.setEditable(false);
        jPanel2.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(374, 2, -1, 120));

        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton1.setText("Stock Management");
        jButton1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton1.setEnabled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 180, 40));

        jButton2.setBackground(new java.awt.Color(255, 255, 255));
        jButton2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton2.setText("Store Management");
        jButton2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton2.setEnabled(false);
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 70, 180, 40));

        jLabel4.setBackground(new java.awt.Color(0, 102, 51));
        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel4.setText("LOG Management Dashboard");
        jPanel4.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        bell.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        bell.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_bell_20px.png"))); // NOI18N
        bell.setText("0");
        bell.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bellMouseClicked(evt);
            }
        });
        jPanel4.add(bell, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 40, 20));

        bell2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        bell2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_bell_20px.png"))); // NOI18N
        bell2.setText("0");
        bell2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bell2MouseClicked(evt);
            }
        });
        jPanel4.add(bell2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 30, 40, -1));

        jLabel7.setText("Agins");
        jPanel4.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, -1));

        jLabel10.setText("Equipement");
        jPanel4.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 50, -1, -1));

        jLabel11.setText("EB");
        jPanel4.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 50, -1, -1));

        eb.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        eb.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_bell_20px.png"))); // NOI18N
        eb.setText("0");
        eb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ebMouseClicked(evt);
            }
        });
        jPanel4.add(eb, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 30, -1, -1));

        admateriel.setForeground(new java.awt.Color(240, 240, 240));
        admateriel.setText("No");
        jPanel4.add(admateriel, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 30, -1, -1));

        adequipement.setForeground(new java.awt.Color(240, 240, 240));
        adequipement.setText("No");
        jPanel4.add(adequipement, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 30, -1, -1));

        materielout.setForeground(new java.awt.Color(240, 240, 240));
        materielout.setText("No");
        jPanel4.add(materielout, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 30, -1, -1));

        eqipementout.setForeground(new java.awt.Color(240, 240, 240));
        eqipementout.setText("No");
        jPanel4.add(eqipementout, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 30, -1, -1));

        inv_mat.setForeground(new java.awt.Color(240, 240, 240));
        inv_mat.setText("No");
        jPanel4.add(inv_mat, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 30, -1, -1));

        inv_qui.setForeground(new java.awt.Color(240, 240, 240));
        inv_qui.setText("No");
        jPanel4.add(inv_qui, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 30, -1, -1));

        adequip2.setForeground(new java.awt.Color(240, 240, 240));
        adequip2.setText("No");
        jPanel4.add(adequip2, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 50, -1, -1));

        fuel.setForeground(new java.awt.Color(240, 240, 240));
        fuel.setText("No");
        jPanel4.add(fuel, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 50, -1, -1));

        agin_out.setForeground(new java.awt.Color(240, 240, 240));
        agin_out.setText("No");
        jPanel4.add(agin_out, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 50, -1, -1));

        car.setForeground(new java.awt.Color(240, 240, 240));
        car.setText("No");
        jPanel4.add(car, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 50, -1, -1));

        doc.setForeground(new java.awt.Color(240, 240, 240));
        doc.setText("No");
        jPanel4.add(doc, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 50, -1, -1));

        inv_qui1.setForeground(new java.awt.Color(240, 240, 240));
        inv_qui1.setText("No");
        jPanel4.add(inv_qui1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 50, -1, -1));

        jPanel2.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(4, 2, 364, 110));

        add_equipement.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        add_equipement.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_multiple_devices_50px.png"))); // NOI18N
        add_equipement.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                add_equipementMouseClicked(evt);
            }
        });
        jPanel2.add(add_equipement, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 40, 80, 80));

        jTextField3.setEditable(false);
        jPanel2.add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 0, -1, 120));
        jPanel2.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 62, 130, 10));

        jTextField4.setEditable(false);
        jPanel2.add(jTextField4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 0, -1, 120));

        invent_equi.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        invent_equi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_pos_terminal_20px_1.png"))); // NOI18N
        invent_equi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                invent_equiMouseClicked(evt);
            }
        });
        jPanel2.add(invent_equi, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 80, 90, 32));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_sell_20px.png"))); // NOI18N
        jLabel3.setEnabled(false);
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 30, -1, 20));

        Docs.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_card_in_use_50px.png"))); // NOI18N
        Docs.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DocsMouseClicked(evt);
            }
        });
        jPanel2.add(Docs, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 80, 50, 30));

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel15.setText("0");
        jPanel2.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(1130, 50, -1, -1));

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel24.setText("Add");
        jPanel2.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 10, -1, -1));

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel25.setText("Invent. Equi");
        jPanel2.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 70, -1, 10));

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel26.setText("Equipement out");
        jPanel2.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 70, -1, -1));

        jLabel30.setBackground(new java.awt.Color(255, 255, 255));
        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel30.setText("Add");
        jPanel2.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 10, -1, -1));

        jLabel31.setBackground(new java.awt.Color(255, 255, 255));
        jLabel31.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel31.setText("Materiel");
        jPanel2.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 24, 50, 20));

        Fuel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Fuel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_gas_station_20px.png"))); // NOI18N
        Fuel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                FuelMouseClicked(evt);
            }
        });
        jPanel2.add(Fuel, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 90, 60, -1));

        Agin.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Agin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_receive_cash_30px.png"))); // NOI18N
        Agin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AginMouseClicked(evt);
            }
        });
        jPanel2.add(Agin, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 90, 70, 20));

        jLabel37.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel37.setText("Borrow To");
        jPanel2.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 10, -1, -1));

        jLabel38.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel38.setText("Docs");
        jPanel2.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 60, 40, 20));

        jLabel34.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel34.setText("  Equipement");
        jPanel2.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 24, 80, 20));

        jTextField7.setEditable(false);
        jPanel2.add(jTextField7, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 0, -1, 120));

        materiel_out.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        materiel_out.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_outdent_20px.png"))); // NOI18N
        materiel_out.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                materiel_outMouseClicked(evt);
            }
        });
        jPanel2.add(materiel_out, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 30, 30, 24));

        quipement_out.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        quipement_out.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_outdent_20px.png"))); // NOI18N
        quipement_out.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                quipement_outMouseClicked(evt);
            }
        });
        jPanel2.add(quipement_out, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 90, 30, -1));
        jPanel2.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 80, 154, 0));
        jPanel2.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 60, 90, 20));

        jTextField8.setEditable(false);
        jPanel2.add(jTextField8, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 0, -1, 120));

        equipement2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        equipement2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_maintenance_50px.png"))); // NOI18N
        equipement2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                equipement2MouseClicked(evt);
            }
        });
        jPanel2.add(equipement2, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 50, 70, 60));

        invent_mat.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        invent_mat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_pos_terminal_20px.png"))); // NOI18N
        invent_mat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                invent_matMouseClicked(evt);
            }
        });
        jPanel2.add(invent_mat, new org.netbeans.lib.awtextra.AbsoluteConstraints(664, 30, 90, 30));

        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel29.setText("Materiel out");
        jPanel2.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 10, -1, -1));

        jLabel41.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel41.setText("Invent. Mat");
        jPanel2.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 10, -1, -1));
        jPanel2.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 60, 90, 10));

        jTextField9.setEditable(false);
        jPanel2.add(jTextField9, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 0, -1, 120));

        jLabel42.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_donate_20px.png"))); // NOI18N
        jLabel42.setEnabled(false);
        jLabel42.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel42MouseClicked(evt);
            }
        });
        jPanel2.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(884, 30, 50, 20));

        jLabel36.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel36.setText("Add");
        jPanel2.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 10, -1, -1));

        jLabel43.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel43.setText("  Equipement 2");
        jPanel2.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 30, 90, -1));

        jLabel44.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel44.setText("Fuel");
        jPanel2.add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(883, 70, 40, -1));

        jLabel45.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel45.setText("Agin Out");
        jPanel2.add(jLabel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 70, -1, -1));

        jTextField6.setEditable(false);
        jPanel2.add(jTextField6, new org.netbeans.lib.awtextra.AbsoluteConstraints(1014, 0, -1, 120));

        jLabel46.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel46.setText("Return");
        jPanel2.add(jLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 10, -1, -1));

        jLabel47.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_Traffic_Jam_40px.png"))); // NOI18N
        jLabel47.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel47MouseClicked(evt);
            }
        });
        jPanel2.add(jLabel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 10, -1, 40));

        jLabel48.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_user_rights_30px.png"))); // NOI18N
        jLabel48.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel48MouseClicked(evt);
            }
        });
        jPanel2.add(jLabel48, new org.netbeans.lib.awtextra.AbsoluteConstraints(1160, 10, -1, -1));

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel21.setText("0");
        jPanel2.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(1170, 50, -1, -1));

        Cars.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_car_50px.png"))); // NOI18N
        Cars.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CarsMouseClicked(evt);
            }
        });
        jPanel2.add(Cars, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 30, 50, 30));

        jLabel57.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel57.setText("Cars");
        jPanel2.add(jLabel57, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 10, -1, -1));

        jProgressBar1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel2.add(jProgressBar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1260, 10, 40, 100));

        jProgressBar_essence.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel2.add(jProgressBar_essence, new org.netbeans.lib.awtextra.AbsoluteConstraints(1206, 10, 40, 100));

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tablodebor.setText("Tableau de Bord");
        tablodebor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tablodeborActionPerformed(evt);
            }
        });
        jPanel3.add(tablodebor, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 13, -1, 35));

        stateofnee.setText("State Of Need");
        stateofnee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stateofneeActionPerformed(evt);
            }
        });
        jPanel3.add(stateofnee, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 54, 111, 35));

        logistique.setText("Logistic Docs");
        logistique.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logistiqueActionPerformed(evt);
            }
        });
        jPanel3.add(logistique, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 95, 111, 37));

        matin.setEditable(false);
        matin.setBackground(new java.awt.Color(240, 240, 241));
        jPanel3.add(matin, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 360, 111, -1));

        matout.setEditable(false);
        matout.setBackground(new java.awt.Color(240, 240, 241));
        jPanel3.add(matout, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 380, 111, -1));

        equipement_in.setEditable(false);
        equipement_in.setBackground(new java.awt.Color(240, 240, 241));
        jPanel3.add(equipement_in, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 400, 111, -1));

        invent.setEditable(false);
        invent.setBackground(new java.awt.Color(240, 240, 241));
        jPanel3.add(invent, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 420, 111, -1));

        borrow.setEditable(false);
        borrow.setBackground(new java.awt.Color(240, 240, 241));
        jPanel3.add(borrow, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 340, 111, -1));

        jButton10.setBackground(new java.awt.Color(153, 153, 255));
        jButton10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton10.setText("Sell Stock");
        jButton10.setEnabled(false);
        jButton10.setOpaque(false);
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 290, 111, 35));

        returne.setEditable(false);
        returne.setBackground(new java.awt.Color(240, 240, 241));
        jPanel3.add(returne, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 440, 111, -1));

        jButton12.setBackground(new java.awt.Color(153, 153, 255));
        jButton12.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton12.setText("Amortissement");
        jButton12.setOpaque(false);
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 111, 35));

        jButton11.setBackground(new java.awt.Color(153, 153, 255));
        jButton11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton11.setText("Buy Stock");
        jButton11.setEnabled(false);
        jButton11.setOpaque(false);
        jPanel3.add(jButton11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, 111, 35));

        jButton13.setBackground(new java.awt.Color(153, 153, 255));
        jButton13.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton13.setText("Stock");
        jButton13.setEnabled(false);
        jButton13.setOpaque(false);
        jButton13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton13MouseClicked(evt);
            }
        });
        jPanel3.add(jButton13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, 111, 35));

        logistic_desk.setBackground(new java.awt.Color(255, 255, 255));

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/logs.jpg"))); // NOI18N

        logistic_desk.setLayer(jLabel6, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout logistic_deskLayout = new javax.swing.GroupLayout(logistic_desk);
        logistic_desk.setLayout(logistic_deskLayout);
        logistic_deskLayout.setHorizontalGroup(
            logistic_deskLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        logistic_deskLayout.setVerticalGroup(
            logistic_deskLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        etjTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Alert Carburant"
            }
        ));
        etjTable3.setFocusable(false);
        etjTable3.setIntercellSpacing(new java.awt.Dimension(0, 0));
        etjTable3.setRowHeight(25);
        etjTable3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                etjTable3MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(etjTable3);

        alertmateriaux.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Alert Materiaux", "Qty"
            }
        ));
        alertmateriaux.setFocusable(false);
        alertmateriaux.setIntercellSpacing(new java.awt.Dimension(0, 0));
        alertmateriaux.setRowHeight(25);
        alertmateriaux.setShowVerticalLines(false);
        alertmateriaux.setSurrendersFocusOnKeystroke(true);
        alertmateriaux.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                alertmateriauxMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(alertmateriaux);

        etjTable5.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Alert Materiels"
            }
        ));
        etjTable5.setFocusable(false);
        etjTable5.setIntercellSpacing(new java.awt.Dimension(0, 0));
        etjTable5.setRowHeight(25);
        etjTable5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                etjTable5MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(etjTable5);

        etjTable6.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Alert Engin"
            }
        ));
        etjTable6.setFocusable(false);
        etjTable6.setIntercellSpacing(new java.awt.Dimension(0, 0));
        etjTable6.setRowHeight(25);
        etjTable6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                etjTable6MouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(etjTable6);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(575, 575, 575)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(275, 275, 275)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, 0)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(logistic_desk))))
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 1391, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(logistic_desk)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 540, Short.MAX_VALUE))
                .addGap(0, 0, 0))
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
    }// </editor-fold>//GEN-END:initComponents

    private void tablodeborActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tablodeborActionPerformed
try{
 con=JavaDbConnect.dbConnect(); 
 tableudebor   m= new tableudebor();
        // NewJInternalFramemateriel_out  m= new NewJInternalFramemateriel_out ();
       logistic_desk.add(m);
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
    }//GEN-LAST:event_tablodeborActionPerformed

    private void stateofneeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stateofneeActionPerformed
try{
 con=JavaDbConnect.dbConnect(); 
   gestion_etat_de_besoin_log m =new gestion_etat_de_besoin_log();
        logistic_desk.add(m);
        m.show();
        m.setDefaultCloseOperation(DISPOSE_ON_CLOSE); 

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
                //  gestion_etat_de_besoin_logistique m =new gestion_etat_de_besoin_logistique();
              // TODO add your handling code here:
    }//GEN-LAST:event_stateofneeActionPerformed

    private void CarsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CarsMouseClicked
if(car.getText().equals("No")){
// JOptionPane.showMessageDialog(null," Acces Limiter","Error",JOptionPane.WARNING_MESSAGE);
}else{
        registre_agin m= new registre_agin();
        logistic_desk.add(m);
        m.show();
        m. setDefaultCloseOperation(DISPOSE_ON_CLOSE);  }        // TODO add your handling code here:
    }//GEN-LAST:event_CarsMouseClicked

    private void jLabel48MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel48MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel48MouseClicked

    private void jLabel47MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel47MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel47MouseClicked

    private void jLabel42MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel42MouseClicked
/*        if(borrow.getText().equals("")){
            logistique_impreint m= new logistique_impreint();
            logistic_desk.add(m);
            m.show();
            borrow.setText("yes");
            bell2.setText("Borrow / Materials & Equipement");

            m. setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        }else{}   */     // TODO add your handling code here:
    }//GEN-LAST:event_jLabel42MouseClicked

    private void invent_matMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_invent_matMouseClicked
if(inv_mat.getText().equals("No")){
 JOptionPane.showMessageDialog(null," Acces Limiter","Error",JOptionPane.WARNING_MESSAGE);
}else{
  //invent_mat.setEnabled(true);
        if(invent.getText().equals("")){
            inventairemtr m= new inventairemtr();
            logistic_desk.add(m);
            m.show();
           
            bell2.setText("Materials  Inventory");
            m. setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        }else{}
}        // TODO add your handling code here:
    }//GEN-LAST:event_invent_matMouseClicked

    private void equipement2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_equipement2MouseClicked
if(adequip2.getText().equals("No")){
// JOptionPane.showMessageDialog(null," Acces Limiter","Error",JOptionPane.WARNING_MESSAGE);
}else{
        equipement_return  m= new equipement_return();
        logistic_desk.add(m);
        m.show();

        m. setDefaultCloseOperation(DISPOSE_ON_CLOSE);}        // TODO add your handling code here:
    }//GEN-LAST:event_equipement2MouseClicked

    private void quipement_outMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_quipement_outMouseClicked
if(eqipementout.getText().equals("No")){
// JOptionPane.showMessageDialog(null," Acces Limiter","Error",JOptionPane.WARNING_MESSAGE);
}else{
 //  quipement_out.setEnabled(true);
        sorti_equipement m= new sorti_equipement();
        logistic_desk.add(m);
        m.show();

        m. setDefaultCloseOperation(DISPOSE_ON_CLOSE);  }        // TODO add your handling code here:
    }//GEN-LAST:event_quipement_outMouseClicked

    private void materiel_outMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_materiel_outMouseClicked
if(materielout.getText().equals("No")){
// JOptionPane.showMessageDialog(null," Acces Limiter","Error",JOptionPane.WARNING_MESSAGE);
}else{
 // materiel_out.setEnabled(true);
        if(matout.getText().equals("")){
            materieux_out1 m= new materieux_out1();
            logistic_desk.add(m);
            m.show();
            matout.setText("yes");
            bell2.setText("Materials Out");
            m. setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        }else{}}        // TODO add your handling code here:
    }//GEN-LAST:event_materiel_outMouseClicked

    private void AginMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AginMouseClicked
if(agin_out.getText().equals("No")){
// JOptionPane.showMessageDialog(null," Acces Limiter","Error",JOptionPane.WARNING_MESSAGE);
}else{
        autorisation_sortie_agin m= new autorisation_sortie_agin();
            logistic_desk.add(m);
            m.show();
            matin.setText("yes");
            bell2.setText("Materials In");
            m. setDefaultCloseOperation(DISPOSE_ON_CLOSE); }  // TODO add your handling code here:
    }//GEN-LAST:event_AginMouseClicked

    private void FuelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_FuelMouseClicked
if(fuel.getText().equals("No")){
}else{
        NewJInternalFrame_carburant  m= new NewJInternalFrame_carburant ();
        logistic_desk.add(m);
        m.show();
        m. setDefaultCloseOperation(DISPOSE_ON_CLOSE);}      
    }//GEN-LAST:event_FuelMouseClicked

    private void DocsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DocsMouseClicked
if(doc.getText().equals("No")){
// JOptionPane.showMessageDialog(null," Acces Limiter","Error",JOptionPane.WARNING_MESSAGE);
}else{
        doc_de_bord1 m= new doc_de_bord1();
        logistic_desk.add(m);
        m.show();
        m. setDefaultCloseOperation(DISPOSE_ON_CLOSE); }      // TODO add your handling code here:
    }//GEN-LAST:event_DocsMouseClicked

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
/*        if(returne.getText().equals("")){
            logistic_refound m= new logistic_refound();
            logistic_desk.add(m);
            m.show();
            returne.setText("yes");
            bell2.setText("Return / Materials & Equipement");
            m. setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        }else{}
*/        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel3MouseClicked

    
    private void invent_equiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_invent_equiMouseClicked
if(inv_qui.getText().equals("No")){
// JOptionPane.showMessageDialog(null," Acces Limiter","Error",JOptionPane.WARNING_MESSAGE);
}else{
 // invent_equi.setEnabled(true);
        equipment_inv  m = new equipment_inv();
        logistic_desk.add(m);
        m.show();
        m. setDefaultCloseOperation(DISPOSE_ON_CLOSE);
}//*/        // TODO add your handling code here:
    }//GEN-LAST:event_invent_equiMouseClicked

    private void add_equipementMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_add_equipementMouseClicked
if(adequipement.getText().equals("No")){
 JOptionPane.showMessageDialog(null," Acces Limiter","Error",JOptionPane.WARNING_MESSAGE);
}else{
 //  add_equipement.setEnabled(true);
        if(equipement_in.getText().equals("")){
            Equipement_in m= new Equipement_in();
            logistic_desk.add(m);
            m.show();
            equipement_in.setText("Yes");
            bell2.setText("Equipement In");
            m. setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        }else{} 

}      // TODO add your handling code here:
    }//GEN-LAST:event_add_equipementMouseClicked

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        jButton2.setBackground(Color.getHSBColor(500,500,500));         // TODO add your handling code here:
    }//GEN-LAST:event_jButton2MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
this.dispose();
        logs m = new logs();
        jDesktopPane1.add(m);
        jButton1.setBackground(Color.red);
        m.show();
        m.setDefaultCloseOperation(DISPOSE_ON_CLOSE);          // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void addmaterielMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addmaterielMouseClicked
if(admateriel.getText().equals("No")){
// JOptionPane.showMessageDialog(null," Acces Limiter","Error",JOptionPane.WARNING_MESSAGE);
}else{
   //
   //addmateriel.setEnabled(true);
        if(matin.getText().equals("")){
            Materiaux_in m= new Materiaux_in();
            logistic_desk.add(m);
            m.show();
            matin.setText("yes");
            bell2.setText("Materials In");
            m. setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        }else{

        }} // TODO add your handling code here:
    }//GEN-LAST:event_addmaterielMouseClicked

    private void bellMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bellMouseClicked
 JOptionPane.showMessageDialog(null,"you have  "+bell.getText()+"  Notification");          // TODO add your handling code here:
    }//GEN-LAST:event_bellMouseClicked

    private void bell2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bell2MouseClicked
 JOptionPane.showMessageDialog(null,"you have  "+bell2.getText()+"  Notification");          // TODO add your handling code here:
    }//GEN-LAST:event_bell2MouseClicked

    private void ebMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ebMouseClicked
JOptionPane.showMessageDialog(null,"you have  "+eb.getText()+"  Notification09");        // TODO add your handling code here:
    }//GEN-LAST:event_ebMouseClicked

    private void logistiqueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logistiqueActionPerformed
NewJInternalFramedocloc m= new  NewJInternalFramedocloc();
       logistic_desk.add(m);
       
        m.show();
        m. setDefaultCloseOperation(DISPOSE_ON_CLOSE);      // TODO add your handling code here:
    }//GEN-LAST:event_logistiqueActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
 this.dispose();
        logs2 m = new logs2();
        jDesktopPane1.add(m);
        jButton2.setBackground(Color.red);
        m.show();
        m.setDefaultCloseOperation(DISPOSE_ON_CLOSE);        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
amourtisementloc m = new amourtisementloc();
         logistic_desk.add(m);
    //    jButton1.setBackground(Color.red);
        m.show();
        m.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }//GEN-LAST:event_jButton12ActionPerformed

    private void etjTable3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_etjTable3MouseClicked
//        select_jTable();       // TODO add your handling code here:
    }//GEN-LAST:event_etjTable3MouseClicked

    private void alertmateriauxMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_alertmateriauxMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_alertmateriauxMouseClicked

    private void etjTable5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_etjTable5MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_etjTable5MouseClicked

    private void etjTable6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_etjTable6MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_etjTable6MouseClicked

    private void jButton13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton13MouseClicked
call_table();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton13MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Agin;
    private javax.swing.JLabel Cars;
    private javax.swing.JLabel Docs;
    private javax.swing.JLabel Fuel;
    private javax.swing.JLabel add_equipement;
    private javax.swing.JLabel addmateriel;
    private javax.swing.JLabel adequip2;
    private javax.swing.JLabel adequipement;
    private javax.swing.JLabel admateriel;
    private javax.swing.JLabel agin_out;
    private javax.swing.JTable alertmateriaux;
    private javax.swing.JLabel bell;
    private javax.swing.JLabel bell2;
    private javax.swing.JTextField borrow;
    private javax.swing.JLabel car;
    private javax.swing.JLabel doc;
    private javax.swing.JLabel eb;
    private javax.swing.JLabel eqipementout;
    private javax.swing.JLabel equipement2;
    public static final javax.swing.JTextField equipement_in = new javax.swing.JTextField();
    private javax.swing.JTable etjTable3;
    private javax.swing.JTable etjTable5;
    private javax.swing.JTable etjTable6;
    private javax.swing.JLabel fuel;
    private javax.swing.JLabel inv_mat;
    private javax.swing.JLabel inv_qui;
    private javax.swing.JLabel inv_qui1;
    public static final javax.swing.JTextField invent = new javax.swing.JTextField();
    private javax.swing.JLabel invent_equi;
    private javax.swing.JLabel invent_mat;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    public static javax.swing.JLabel jLabel15;
    public static javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JProgressBar jProgressBar_essence;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    public static javax.swing.JDesktopPane logistic_desk;
    private javax.swing.JButton logistique;
    private javax.swing.JLabel materiel_out;
    private javax.swing.JLabel materielout;
    public static final javax.swing.JTextField matin = new javax.swing.JTextField();
    public static final javax.swing.JTextField matout = new javax.swing.JTextField();
    private javax.swing.JLabel quipement_out;
    private javax.swing.JTextField returne;
    private javax.swing.JButton stateofnee;
    private javax.swing.JButton tablodebor;
    // End of variables declaration//GEN-END:variables
}
