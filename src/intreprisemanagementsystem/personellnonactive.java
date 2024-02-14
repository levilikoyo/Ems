/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intreprisemanagementsystem;


import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
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
 * @author Doshe
 */
public class personellnonactive extends javax.swing.JFrame {
 DefaultTableModel mode;

    Connection con=null;
    PreparedStatement pst=null;
    ResultSet rs= null;
    public personellnonactive() {
        initComponents();
              con=JavaDbConnect.dbConnect();
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("icons8_Raspberry_Pi_48px_1.png")));
        Call_ID_TO_BOMBOBOX1();
       // nbrejrs.setText("0");
        Call_IN_LIST();
    }
     //CONNECTION
     public static Connection getConnection()
    {
        Connection con = null;
        try{
             con= DriverManager.getConnection("jdbc:mysql://localhost/entreprise_management_system","root","");
              // JOptionPane.showMessageDialog(null,"connected");
             return con;
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,"not connected");
           
        }
         return null;
    }
    
    
          public void Call_ID_TO_BOMBOBOX1()
    {
         {
        try{
            String sql="select * from tabe";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                String sum=rs.getString("nom");
                  jComboBox7.addItem(sum);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
    }
    }
          
          //SAVE
     
      public void savemateriaux()
    {
        if(jComboBox7.getSelectedItem().equals("CHANTIER")){
              JOptionPane.showMessageDialog(null,"LE CHANTIER N'A PAS ETE SELECTIONE");
                   
            }else{
        try {
         Connection con = getConnection();
        PreparedStatement pst = con.prepareStatement("INSERT INTO personelnonactive(NOM,PNOM,PRNOM,ADRESSE,FONCTION,MONTAT,JOURS,NET_A_PAYER,CHANTIER) "
        +"value(?,?,?,?,?,?,?,?,?)");
        
        pst.setString(1, nom.getText());
        pst.setString(2, pnom.getText());
        pst.setString(3, prnom.getText());
        pst.setString(4, adresse.getText());
        pst.setString(5, fonction.getText()); 
        pst.setString(6, montat.getText());
        pst.setString(7, nbrejrs.getText());
        pst.setString(8, netat.getText());
        String value=jComboBox7.getSelectedItem().toString();
        pst.setString(9, value);
        pst.executeUpdate();
        
               //  JOptionPane.showMessageDialog(null,"data saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
    }
    }
    
   // DELETE
    public void delete()
            
            
            
    {
        if(jComboBox7.getSelectedItem().equals("CHANTIER")){
            
         try{
        String sql = "DELETE FROM  personelnonactive  WHERE ID=?";
        
         pst = con.prepareStatement(sql);
         pst.setString(1,jTextField1.getText());
         pst.executeUpdate();
         //JOptionPane.showMessageDialog(null,"delete");
              
     }catch(SQLException | HeadlessException ex ){
         JOptionPane.showMessageDialog(null,ex);
     } 
    }else{
             try{
        String sql = "DELETE FROM  personelnonactive  WHERE CHANTIER=?";
        
         pst = con.prepareStatement(sql);
         String value=jComboBox7.getSelectedItem().toString();
        pst.setString(1, value);
         pst.executeUpdate();
        // JOptionPane.showMessageDialog(null,"delete");
              
     }catch(SQLException | HeadlessException ex ){
         JOptionPane.showMessageDialog(null,ex);
     } 
        }
    }
     
    
    //UPDATE
     public void update_materiaux()
             
    {
        if(jComboBox7.getSelectedItem().equals("CHANTIER")){
              JOptionPane.showMessageDialog(null,"LE CHANTIER N'A PAS ETE SELECTIONE");
                   
            }else{
        if( nbrejrs.getText().isEmpty()){
        
     if (jComboBox6.getSelectedItem().equals("PRESENT")){
       // nbrejrs.setText(null);
         try{
             String tmp=jComboBox7.getSelectedItem().toString();
         
             
        String sql = "UPDATE  personelnonactive SET JOURS=JOURS+1,NET_A_PAYER=JOURS*MONTAT where CHANTIER='"+tmp+"'";
        
         pst = con.prepareStatement(sql);
         
         pst.executeUpdate();
       
     }catch(SQLException | HeadlessException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }
    }else if(jComboBox6.getSelectedItem().equals("ABSENT")) {   
      try{
             String tmp=jComboBox7.getSelectedItem().toString();
        // nbrejrs.setText(null);
             
        String sql = "UPDATE  personelnonactive SET JOURS=JOURS-1,NET_A_PAYER=JOURS*MONTAT where CHANTIER='"+tmp+"'";
        
         pst = con.prepareStatement(sql);
         
         pst.executeUpdate();
       
     }catch(SQLException | HeadlessException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }
    }
        }else{
             
        
        
        if (jComboBox6.getSelectedItem().equals("PRESENT")){
        String s = nbrejrs.getText();
        int billNumber = Integer.valueOf(s);
        s = Integer.toString(++billNumber);
        nbrejrs.setText(s);
         try{
             String tmp=jComboBox7.getSelectedItem().toString();
         
             
        String sql = "UPDATE  personelnonactive SET JOURS=?,NET_A_PAYER=JOURS*MONTAT  where ID=?";
        
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
       
     }catch(SQLException | HeadlessException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }
    }else if(jComboBox6.getSelectedItem().equals("ABSENT")) {   
      try{
             String tmp=jComboBox7.getSelectedItem().toString();
         String s = nbrejrs.getText();
        int billNumber = Integer.valueOf(s);
        s = Integer.toString(--billNumber);
        nbrejrs.setText(s);
             
        String sql = "UPDATE  personelnonactive SET JOURS=?,NET_A_PAYER=JOURS*MONTAT  where ID=?";
        
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
         
     }catch(SQLException | HeadlessException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }
    }
        
        
        
        }
    
    }
    }
    

     //CALL IN LIST
        public void Call_IN_LIST()
    {
        DefaultListModel list = new DefaultListModel();

        try{
            
            
            String tmp =(String)jComboBox7.getSelectedItem();
            
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
         public void select_jTable()
   {
        try{
          int row= jTable2.getSelectedRow();
          String Table_click = (jTable2.getModel().getValueAt(row,0). toString());
          String sql = "SELECT * FROM personelnonactive WHERE ID= '"+Table_click+"'";
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
         
          public void update_emp()
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
              
                   String value=jComboBox7.getSelectedItem().toString();
         pst.setString(7, value); 
                    
         pst.executeUpdate();
         //JOptionPane.showMessageDialog(null,"updaded");    
     }catch(SQLException | HeadlessException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }
          
       
    }        
     
      //SEARCH AND ADDREADER
     
      public void ReadData(String sql)
    {
            Statement st;
            try{
                
                st=con.createStatement();
                ResultSet rs;
                rs=st.executeQuery(sql);
                if(rs.first()){
                    String Data[]=new String[10];
                    do{
                        for(int i=0;i<10;i++){
                            Data[i]=rs.getString(i+1);
                        }
                    
                    mode.addRow(Data);
                }while(rs.next());
            }
                rs.close();
            }catch(Exception ex){
                
            }
    }
    
    public void AddModel(){
        mode=new DefaultTableModel();
        mode.addColumn("ID");
        mode.addColumn("NOM");
        mode.addColumn("POST_NOM");
        mode.addColumn("PRES_NOM");    
        mode.addColumn("ADRESSE");
        mode.addColumn("FONCTION");
        mode.addColumn("MONTAT");
        mode.addColumn("JOURS");
        mode.addColumn("NET_A_PAYER");
        mode.addColumn("CHANTIER");
       
        jTable2.setModel(mode);
        
        
        TableColumn col0=jTable2.getColumnModel().getColumn(0);
        TableColumn col1=jTable2.getColumnModel().getColumn(1);
        TableColumn col2=jTable2.getColumnModel().getColumn(2);
        TableColumn col3=jTable2.getColumnModel().getColumn(3);
        TableColumn col4=jTable2.getColumnModel().getColumn(4);
        TableColumn col5=jTable2.getColumnModel().getColumn(5);
        TableColumn col6=jTable2.getColumnModel().getColumn(6);
        TableColumn col7=jTable2.getColumnModel().getColumn(7);
        TableColumn col8=jTable2.getColumnModel().getColumn(8);
        TableColumn col9=jTable2.getColumnModel().getColumn(9);
        
       
       col0.setPreferredWidth(5);
       col1.setPreferredWidth(50);
       col2.setPreferredWidth(50);
       col3.setPreferredWidth(50);
       col4.setPreferredWidth(50);
       col5.setPreferredWidth(20);
       col6.setPreferredWidth(10);
       col7.setPreferredWidth(10);
       col8.setPreferredWidth(10);
       col9.setPreferredWidth(50);
    }
    
      
      public void remove(){
          while(jTable2.getRowCount()>0){
              mode.removeRow(0);
          }
      }
              
                public void search()
             {
                  String st=recherche1.getText().trim();
    remove();
    ReadData("select * from personelnonactive where CHANTIER like '%"+st+"%'");
             }
                
                       public void search1()
        {
                  String st=recherches.getText().trim();
    remove();
    ReadData("select * from personelnonactive where NOM like '%"+st+"%'");
    ReadData("select * from personelnonactive where PNOM like '%"+st+"%'");
    ReadData("select * from personelnonactive where PRNOM like '%"+st+"%'");
    ReadData("select * from personelnonactive where CHANTIER like '%"+st+"%'");
        }
                
              public void report()
     {
          String tmps =(String)recherche1.getText();
     
             try{
                JasperDesign jd=JRXmlLoader.load("C:\\Users\\Dosh\\Documents\\NetBeansProjects\\intreprisemanagementsystem\\src\\intreprisemanagementsystem\\fichedepresencesurchantier.jrxml");
                String sql="select * from personelnonactive where CHANTIER ='"+tmps+"'";
                JRDesignQuery nq=new JRDesignQuery();
                nq.setText(sql);
                jd.setQuery(nq);
               
                 JasperReport jr =JasperCompileManager.compileReport(jd);
                 JasperPrint jp=JasperFillManager.fillReport(jr,null,con);
                 JasperViewer.viewReport(jp,false);
                 
                 
                 JasperViewer m= new JasperViewer(jp);
                 m.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
             }catch(Exception ex){
                  JOptionPane.showMessageDialog(null, ex);   
             }

     }
               public void report1()
     {
          String tmps =(String)recherche1.getText();
     
             try{
                JasperDesign jd=JRXmlLoader.load("C:\\Users\\Dosh\\Documents\\NetBeansProjects\\intreprisemanagementsystem\\src\\intreprisemanagementsystem\\fichedepaiesurchantier.jrxml");
                String sql="select * from personelnonactive where CHANTIER ='"+tmps+"'";
                JRDesignQuery nq=new JRDesignQuery();
                nq.setText(sql);
                jd.setQuery(nq);
               
                 JasperReport jr =JasperCompileManager.compileReport(jd);
                 JasperPrint jp=JasperFillManager.fillReport(jr,null,con);
                 JasperViewer.viewReport(jp,false);
                 
                 
                 JasperViewer m= new JasperViewer(jp);
                 m.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
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
        nom = new javax.swing.JTextField();
        pnom = new javax.swing.JTextField();
        prnom = new javax.swing.JTextField();
        adresse = new javax.swing.JTextField();
        fonction = new javax.swing.JTextField();
        montat = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jComboBox7 = new javax.swing.JComboBox<>();
        nbrejrs = new javax.swing.JTextField();
        jTextField1 = new javax.swing.JTextField();
        jComboBox6 = new javax.swing.JComboBox<>();
        recherches = new javax.swing.JTextField();
        recherche1 = new javax.swing.JTextField();
        netat = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jTextField8 = new javax.swing.JTextField();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(204, 255, 204));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        nom.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        pnom.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        prnom.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        adresse.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        adresse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adresseActionPerformed(evt);
            }
        });

        fonction.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        montat.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        montat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                montatKeyTyped(evt);
            }
        });

        jLabel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("NOM");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("POST-NOM");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("PRESNOM");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("ADRESSE");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("FONCTION");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("MOTANT /JOUR");

        jComboBox7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jComboBox7.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "CHANTIER" }));
        jComboBox7.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                jComboBox7PopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        jComboBox7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox7ActionPerformed(evt);
            }
        });

        nbrejrs.setEditable(false);
        nbrejrs.setBackground(new java.awt.Color(204, 204, 255));
        nbrejrs.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jTextField1.setEditable(false);
        jTextField1.setBackground(new java.awt.Color(204, 255, 204));
        jTextField1.setForeground(new java.awt.Color(204, 255, 204));
        jTextField1.setBorder(null);

        jComboBox6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jComboBox6.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "PRESENCE", "PRESENT", "ABSENT" }));
        jComboBox6.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                jComboBox6PopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        jComboBox6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox6ActionPerformed(evt);
            }
        });

        recherches.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                recherchesKeyReleased(evt);
            }
        });

        recherche1.setEditable(false);
        recherche1.setBackground(new java.awt.Color(204, 255, 204));
        recherche1.setForeground(new java.awt.Color(204, 255, 204));
        recherche1.setBorder(null);
        recherche1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                recherche1KeyReleased(evt);
            }
        });

        netat.setEditable(false);
        netat.setBackground(new java.awt.Color(204, 255, 204));
        netat.setForeground(new java.awt.Color(204, 255, 204));
        netat.setText("0");
        netat.setBorder(null);

        jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField2KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(recherches)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nom))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(prnom)
                            .addComponent(pnom)
                            .addComponent(adresse)
                            .addComponent(fonction)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jComboBox6, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox7, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(recherche1)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextField1)))
                                .addGap(40, 40, 40))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(netat, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(montat, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(nbrejrs))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addGap(6, 6, 6))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pnom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(prnom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(adresse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fonction, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(montat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(nbrejrs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(netat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(recherche1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(recherches, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
        );

        jPanel2.setBackground(new java.awt.Color(204, 255, 204));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

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
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addGap(2, 2, 2))
        );

        jTextField8.setEditable(false);
        jTextField8.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jTextField8.setText("                                          EMPLOYEE NON ACTIF FOLLOWING UP FORM");
        jTextField8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField8ActionPerformed(evt);
            }
        });

        jMenu1.setText("File");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setText("Save");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_U, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem2.setText("Update");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem5.setText("Delete");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem5);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        jMenu3.setText("Rapports");

        jMenuItem3.setText("Fiche_De_De_Presence");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem3);

        jMenuItem4.setText("Fiche_De_Paies");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem4);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jTextField8, javax.swing.GroupLayout.DEFAULT_SIZE, 1103, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(6, 6, 6))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField8ActionPerformed

    private void adresseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adresseActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_adresseActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
nbrejrs.setText("0");
        savemateriaux();
        nom.setText(null);
pnom.setText(null);
prnom.setText(null);
adresse.setText(null);
fonction.setText(null);
montat.setText(null);
nbrejrs.setText(null);
        
search(); // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
 AddModel();
 ReadData("select * from personelnonactive");        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowOpened

    private void jComboBox7PopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_jComboBox7PopupMenuWillBecomeInvisible
Call_IN_LIST();
String tmp = jComboBox7.getSelectedItem().toString(); 
recherche1.setText(tmp);
search();
nom.setText(null);
pnom.setText(null);
prnom.setText(null);
adresse.setText(null);
fonction.setText(null);
montat.setText(null);
nbrejrs.setText(null);// TODO add your handling code here:
    }//GEN-LAST:event_jComboBox7PopupMenuWillBecomeInvisible

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
update_emp();
nom.setText(null);
pnom.setText(null);
prnom.setText(null);
adresse.setText(null);
fonction.setText(null);
montat.setText(null);
nbrejrs.setText(null);
search();       // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jComboBox6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox6ActionPerformed
  
        update_materiaux();
     // AddModel();
 //ReadData("select * from personelnonactive"); // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox6ActionPerformed

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
select_jTable();
//jComboBox7.setSelectedItem("CHANTIER");
// TODO add your handling code here:
    }//GEN-LAST:event_jTable2MouseClicked

    private void jComboBox6PopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_jComboBox6PopupMenuWillBecomeInvisible

        search();     // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox6PopupMenuWillBecomeInvisible

    private void recherche1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_recherche1KeyReleased
search();        // TODO add your handling code here:
    }//GEN-LAST:event_recherche1KeyReleased

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
report();        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
report1();        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void recherchesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_recherchesKeyReleased
search1();        // TODO add your handling code here:
    }//GEN-LAST:event_recherchesKeyReleased

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
delete(); 
AddModel();
ReadData("select * from personelnonactive");// TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void montatKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_montatKeyTyped
char c=evt.getKeyChar();
if(!(Character.isDigit(c) || (c==KeyEvent.VK_BACK_SPACE)||c==KeyEvent.VK_DELETE ||c==KeyEvent.VK_PERIOD )){
evt.consume(); 
}        // TODO add your handling code here:
    }//GEN-LAST:event_montatKeyTyped

    private void jTextField2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyReleased
search1();         // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2KeyReleased

    private void jComboBox7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox7ActionPerformed

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
            java.util.logging.Logger.getLogger(personellnonactive.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(personellnonactive.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(personellnonactive.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(personellnonactive.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new personellnonactive().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField adresse;
    private javax.swing.JTextField fonction;
    private javax.swing.JComboBox<String> jComboBox6;
    private javax.swing.JComboBox<String> jComboBox7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField montat;
    private javax.swing.JTextField nbrejrs;
    private javax.swing.JTextField netat;
    private javax.swing.JTextField nom;
    private javax.swing.JTextField pnom;
    private javax.swing.JTextField prnom;
    private javax.swing.JTextField recherche1;
    private javax.swing.JTextField recherches;
    // End of variables declaration//GEN-END:variables
}
