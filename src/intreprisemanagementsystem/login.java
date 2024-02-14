/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intreprisemanagementsystem;

import com.alee.laf.WebLookAndFeel;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.Date;


public class login extends javax.swing.JFrame {
DefaultTableModel mode;
private Connection con=null;
PreparedStatement pst=null;
ResultSet rs= null;
    public login() {
        initComponents();
        con=JavaDbConnect.dbConnect();
EMP_NAME_TO_BOMBOBOX2();
jLabel5.setEnabled(false);
        a.setVisible(false);
        b.setVisible(false);
        c.setVisible(false);
        d.setVisible(false);
        e.setVisible(false);
        f.setVisible(false);
        g.setVisible(false);
       // broswer();
        icon();
        date_now();
    }
     public void date_now()
    {
        Date date=null;
       // Date datess=null;
         SimpleDateFormat dates = new SimpleDateFormat("yyyy-MM-dd");
         
         try{
         String sql="select dates from datess";
         pst=con.prepareStatement(sql);
         rs=pst.executeQuery();
            while(rs.next()){
                String sum=rs.getString("dates");
                 // sums(sum);}
       //   String date1="2018-03-01";
                 
        date=dates.parse(sum);
            }
       // datess=dates.parse(date2);
   
       jDateChooser1.setDate(date);
     //  jDateChooser2.setDate(datess);
     jDateChooser2.setDate(new Date());
                 
                 
                 
         }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}
  
    }
    
    private void icon(){
    setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("icons8_Raspberry_Pi_48px_1.png")));
    
    }
public void EMP_NAME_TO_BOMBOBOX2()
    {
         {
        try{
            String sql="select * from  password";
            
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                String sum=rs.getString("Cat");
                  jComboBox1.addItem(sum);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
    }
    }
    public void clear(){
        username.setText(null);
        password.setText(null);
        a.setText(null);
        b.setText(null);
        c.setText(null);
    }
        ///IMAGE
     
    
    
    public void login(){
        
        if(jComboBox1.getSelectedItem().equals("CATEGORIE")){
             
             if(username.getText().equals("ADMIN") &&password.getText().equals("ADMIN")){
             homme m = new homme();
             homme.user.setEnabled(true);
                     m.show();
                     this.dispose();
             }
             
             }
        
        
        this.setAlwaysOnTop(false);
        String user = username.getText();
        String passs = password.getText();
        String tmp =(String) jComboBox1.getSelectedItem();
        
        
        
            SimpleDateFormat dates = new SimpleDateFormat("yyyy-MM-dd");
             if(jDateChooser1.getDate().compareTo(jDateChooser2.getDate())>0){
        JOptionPane.showMessageDialog(null, "DESOLE VEILLEZ CONTACTE LE PROVIDER SERVICES POUR LA LICENSE DU PRODUIT");    
       username.setText(null);
        password.setText(null);
      
             }else{
          JOptionPane.showMessageDialog(null, "VOUS UTILISEZ LA PERIODE D'ESSAIS (TRIAL) POUR 30 JOURS");
        
         if(jComboBox1.getSelectedItem().equals("COMPTABILITE")){
         try{
             String sql ="select * from password where Cat='"+tmp+"' and user=? and pass= ?";
          
            pst=con.prepareStatement(sql);
            pst.setString(1,user);
            pst.setString(2,passs);
            rs=pst.executeQuery();
           if(rs.next()){
               clear();
              
      //   homme m = new homme();
               homme.comptabilite.setEnabled(true);
              //  m.show();
                    this.dispose();
              //  m. setDefaultCloseOperation(DISPOSE_ON_CLOSE); 
              
                //this.show();
                
            }else{
                 JOptionPane.showMessageDialog(null, "MOT DE PASSE INCORECT");
                 clear();
            }
         }catch(Exception ex){
             JOptionPane.showMessageDialog(null, ex);
         }
         }
         
          if(jComboBox1.getSelectedItem().equals("LOGISTIQUE")){
         try{
             String sql ="select * from password where Cat='"+tmp+"' and user=? and pass= ?";
            
            pst=con.prepareStatement(sql);
            pst.setString(1,user);
            pst.setString(2,passs);
            rs=pst.executeQuery();
           if(rs.next()){
               clear();
               // logistic m = new logistic();
             //  homme m = new homme();
               homme.logistique.setEnabled(true);
               // m.show();
                    this.dispose();
               // m. setDefaultCloseOperation(DISPOSE_ON_CLOSE); 
            }else{
                 JOptionPane.showMessageDialog(null, "MOT DE PASSE INCORECT");
                 clear();
            }
         }catch(Exception ex){
             JOptionPane.showMessageDialog(null, ex);
         }
         }
          
           if(jComboBox1.getSelectedItem().equals("RESOURCES HUMAINES")){
         try{
     String sql ="select * from password where Cat='"+tmp+"' and user=? and pass= ?";
             
            pst=con.prepareStatement(sql);
            pst.setString(1,user);
            pst.setString(2,passs);
            rs=pst.executeQuery();
           if(rs.next()){
               clear();
              //  homme m = new homme();
                homme.rh.setEnabled(true);
                //attendance.jList1.setEnabled(true);
              //  m.show();
                    this.dispose();
              //  m. setDefaultCloseOperation(DISPOSE_ON_CLOSE); 
            }else{
                 JOptionPane.showMessageDialog(null, "MOT DE PASSE INCORECT");
                 clear();
            }
         }catch(Exception ex){
             JOptionPane.showMessageDialog(null, ex);
         }
         }
           
            if(jComboBox1.getSelectedItem().equals("ADMINISTRATION")){
         try{
String sql ="select * from password where Cat='"+tmp+"' and user=? and pass= ?";
          
            pst=con.prepareStatement(sql);
            pst.setString(1,user);
            pst.setString(2,passs);
            rs=pst.executeQuery();
           if(rs.next()){
               clear();
               // homme m = new homme();
                homme.admin.setEnabled(true);
              //  m.show();
                    this.dispose();
              //  m. setDefaultCloseOperation(DISPOSE_ON_CLOSE); 
            }else{
                 JOptionPane.showMessageDialog(null, "MOT DE PASSE INCORECT");
                 clear();
            }
         }catch(Exception ex){
             JOptionPane.showMessageDialog(null, ex);
         }
         }
            
             if(jComboBox1.getSelectedItem().equals("DIRECTION")){
         try{
String sql ="select * from password where Cat='"+tmp+"' and user=? and pass= ?";
             
            pst=con.prepareStatement(sql);
            pst.setString(1,user);
            pst.setString(2,passs);
            rs=pst.executeQuery();
           if(rs.next()){
               clear();
                
                homme.direction.setEnabled(true);
               // m.show();
                    this.dispose();
              //  m. setDefaultCloseOperation(DISPOSE_ON_CLOSE); 
            }else{
                 JOptionPane.showMessageDialog(null, "MOT DE PASSE INCORECT");
                 clear();
            }
         }catch(Exception ex){
             JOptionPane.showMessageDialog(null, ex);
         }
         }
             
              if(jComboBox1.getSelectedItem().equals("EMPLOYEE")){
         try{
String sql ="select * from password where Cat='"+tmp+"' and user=? and pass= ?";
             
            pst=con.prepareStatement(sql);
            pst.setString(1,user);
            pst.setString(2,passs);
            rs=pst.executeQuery();
           if(rs.next()){
               clear();
               // homme m = new homme();
                homme.employee.setEnabled(true);
               // attendance.jList1.setEnabled(false);
               // attendance.jMenu3.setEnabled(false);
             //   m.show();
                
                
             //   m. setDefaultCloseOperation(DISPOSE_ON_CLOSE); 
                
            }else{
                 JOptionPane.showMessageDialog(null, "MOT DE PASSE INCORECT");
                 clear();
            }
         }catch(Exception ex){
             JOptionPane.showMessageDialog(null, ex);
         }
              }
              
              
                    if(jComboBox1.getSelectedItem().equals("CAISSE")){
         try{
String sql ="select * from password where Cat='"+tmp+"' and user=? and pass= ?";
             
            pst=con.prepareStatement(sql);
            pst.setString(1,user);
            pst.setString(2,passs);
            rs=pst.executeQuery();
           if(rs.next()){
               clear();
               // homme m = new homme();
                homme.comptabilite.setEnabled(true);
               newdesingn.caisseaa.setEnabled(true);
               // attendance.jMenu3.setEnabled(false);
             //   m.show();
                this.dispose();
                
             //   m. setDefaultCloseOperation(DISPOSE_ON_CLOSE); 
                
            }else{
                 JOptionPane.showMessageDialog(null, "MOT DE PASSE INCORECT");
                 clear();
            }
         }catch(Exception ex){
             JOptionPane.showMessageDialog(null, ex);
         }
              }
                    
                          if(jComboBox1.getSelectedItem().equals("FIN_ASS")){
         try{
String sql ="select * from password where Cat='"+tmp+"' and user=? and pass= ?";
             
            pst=con.prepareStatement(sql);
            pst.setString(1,user);
            pst.setString(2,passs);
            rs=pst.executeQuery();
           if(rs.next()){
               clear();
               // homme m = new homme();
                homme.comptabilite.setEnabled(true);
                 homme.comptabilite.setEnabled(true);
                  homme.comptabilite.setEnabled(true);
                   homme.comptabilite.setEnabled(true);
                    homme.comptabilite.setEnabled(true);
                     homme.comptabilite.setEnabled(true);
               // attendance.jList1.setEnabled(false);
               // attendance.jMenu3.setEnabled(false);
             //   m.show();
                
                
             //   m. setDefaultCloseOperation(DISPOSE_ON_CLOSE); 
                
            }else{
                 JOptionPane.showMessageDialog(null, "MOT DE PASSE INCORECT");
                 clear();
            }
         }catch(Exception ex){
             JOptionPane.showMessageDialog(null, ex);
         }
              }
         }
             
            
    }
    public void call(){
        String tmp =(String) jComboBox1.getSelectedItem();
         String tmps =a1.getText();;
        try{
            String sql="select pass from password where Cat='"+tmp+"'";
            
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            if(rs.next()){
               // String sums = rs.getString("EMP_ROLL");
             //chatier.setText(sums);
                String sum=rs.getString("pass");
                  a1.setText(sum);
                  
            }
       
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
    }
         //UPDATE
     public void update_materiaux()
             
    {
        this.setAlwaysOnTop(false);
        String tmp =(String) jComboBox1.getSelectedItem();
       
        if((a1.getText()).equals(b.getText()) ){
         
         try{
                        
String sql ="update password set  user=?, pass= ? where Cat='"+tmp+"'";
         
         pst = con.prepareStatement(sql);
         pst.setString(1,a.getText());
         // pst.setString(2,b.getText());
        pst.setString(2,c.getText());
             
             
                       
         pst.executeUpdate();
         
        JOptionPane.showMessageDialog(null,"MOT DE PASSE MODIFIE" ); 
        
                
        clear();
     }catch(SQLException | HeadlessException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }
    }else{
             JOptionPane.showMessageDialog(null,"INCORECT MOT DE PASSE" ); 
            
        
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
        username = new javax.swing.JTextField();
        password = new javax.swing.JPasswordField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        c = new javax.swing.JPasswordField();
        d = new javax.swing.JButton();
        f = new javax.swing.JLabel();
        g = new javax.swing.JLabel();
        a = new javax.swing.JTextField();
        e = new javax.swing.JLabel();
        b = new javax.swing.JPasswordField();
        a1 = new javax.swing.JTextField();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new java.awt.Button();
        jLabel4 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAlwaysOnTop(true);
        setFocusCycleRoot(false);
        setFocusTraversalPolicyProvider(true);
        setLocationByPlatform(true);
        setUndecorated(true);
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        username.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        password.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        password.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                passwordKeyPressed(evt);
            }
        });

        jComboBox1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "CATEGORIE" }));
        jComboBox1.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                jComboBox1PopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton1.setText("Exit");

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton2.setText("Reset");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton4.setText("LogIn");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Nom_d'Utilisateur");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Mot de passe");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2)
                            .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE))
                            .addComponent(password)
                            .addComponent(username))))
                .addContainerGap(11, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(username, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton4))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        c.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jPanel2.add(c, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 77, 170, -1));

        d.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        d.setText("Changer le Mot de Pass");
        d.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dActionPerformed(evt);
            }
        });
        jPanel2.add(d, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 104, 340, -1));

        f.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        f.setText("Ancien mot de passe");
        jPanel2.add(f, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 48, -1, -1));

        g.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        g.setText("Nouveau mot de passe");
        jPanel2.add(g, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 80, -1, -1));

        a.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jPanel2.add(a, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 13, 170, -1));

        e.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        e.setText("Nom_d'Utilisateur");
        jPanel2.add(e, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 16, -1, -1));

        b.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jPanel2.add(b, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 45, 170, -1));

        a1.setEditable(false);
        a1.setFont(new java.awt.Font("Tahoma", 0, 3)); // NOI18N
        a1.setForeground(new java.awt.Color(240, 240, 240));
        a1.setBorder(null);
        jPanel2.add(a1, new org.netbeans.lib.awtextra.AbsoluteConstraints(179, 35, 170, 9));

        jDateChooser1.setForeground(new java.awt.Color(240, 240, 240));
        jDateChooser1.setEnabled(false);
        jDateChooser1.setFont(new java.awt.Font("Tahoma", 1, 5)); // NOI18N
        jDateChooser1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jDateChooser1MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jDateChooser1MousePressed(evt);
            }
        });

        jDateChooser2.setForeground(new java.awt.Color(240, 240, 240));
        jDateChooser2.setEnabled(false);
        jDateChooser2.setFont(new java.awt.Font("Tahoma", 1, 5)); // NOI18N
        jDateChooser2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jDateChooser2MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jDateChooser2MousePressed(evt);
            }
        });

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/login.jpg"))); // NOI18N
        jLabel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(51, 51, 255));
        jLabel5.setLabel("Changer_Mot_de_Passe");
        jLabel5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jLabel5ActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel4.setText("X");
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(51, 51, 51));
        jPanel3.setForeground(new java.awt.Color(255, 255, 255));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Log in ");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(254, 254, 254)
                .addComponent(jLabel6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(11, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
login(); 
homme.user.setText(username.getText());// TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void dActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dActionPerformed
update_materiaux(); 
a.setText("");
b.setText("");
c.setText("");
d.setText("");
e.setText("");
f.setText("");
g.setText("");



// TODO add your handling code here:
    }//GEN-LAST:event_dActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
clear();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jComboBox1PopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_jComboBox1PopupMenuWillBecomeInvisible
call();        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1PopupMenuWillBecomeInvisible

    private void passwordKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_passwordKeyPressed
 if (evt.getKeyCode()==KeyEvent.VK_ENTER){
      login();       
}           // TODO add your handling code here:
    }//GEN-LAST:event_passwordKeyPressed

    private void jDateChooser1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jDateChooser1MouseClicked

        // TODO add your handling code here:
    }//GEN-LAST:event_jDateChooser1MouseClicked

    private void jDateChooser1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jDateChooser1MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jDateChooser1MousePressed

    private void jDateChooser2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jDateChooser2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jDateChooser2MouseClicked

    private void jDateChooser2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jDateChooser2MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jDateChooser2MousePressed

    private void jLabel5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jLabel5ActionPerformed
a.setVisible(true);
        b.setVisible(true);
        c.setVisible(true);
        d.setVisible(true);
        e.setVisible(true);
        f.setVisible(true);
        g.setVisible(true);        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel5ActionPerformed

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel4MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
      
       new login().setVisible(true);
        WebLookAndFeel.install(true);
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("JTatoo".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
         java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
               // new login().setVisible(true);
                  // WebLookAndFeel.install(true);
            }
        });
       
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField a;
    private javax.swing.JTextField a1;
    private javax.swing.JPasswordField b;
    private javax.swing.JPasswordField c;
    private javax.swing.JButton d;
    private javax.swing.JLabel e;
    private javax.swing.JLabel f;
    private javax.swing.JLabel g;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    public static javax.swing.JComboBox<String> jComboBox1;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    public static java.awt.Button jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPasswordField password;
    private javax.swing.JTextField username;
    // End of variables declaration//GEN-END:variables
}
