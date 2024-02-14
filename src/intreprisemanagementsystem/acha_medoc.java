/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intreprisemanagementsystem;

import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.Date;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author DOSHE
 */
public class acha_medoc extends javax.swing.JInternalFrame {

   private Connection con;
private Statement st;
PreparedStatement pst=null;
ResultSet rs= null;
String rolls;
String NUM_ID;
 DefaultTableModel mode;
 String ID=null;
  String IDS=null;
  String Total=null;
  String MAX=null;
  
    public acha_medoc() {
        initComponents();
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI bui= (BasicInternalFrameUI) this.getUI();
       bui.setNorthPane(null);
       buss();
       webDateField2.setDate(new Date());
       exp.setDate(new Date());
    }
     String url="jdbc:sqlite:kavira.db";
    //CONNECTION
     
      public void roll()
     {
         try{
                 Class.forName("org.sqlite.JDBC");
           con= DriverManager.getConnection(url);
            String sql="SELECT NUM FROM  h_p_achat_medoc ORDER BY NUM DESC LIMIT 1";
             pst=con.prepareStatement(sql);
             rs=pst.executeQuery();
             if(rs.next()){
                 String rl=rs.getString("NUM");
                 int ln=rl.length();
                 String stxt=rl.substring(0,8);
                 String snum=rl.substring(8,ln);
                 int n = Integer.parseInt(snum);
                 n++;
                 snum=Integer.toString(n);
                 rolls=stxt+snum;
                 
                 
                  pst.close();
            rs.close();
                con.close();
             }else{
                 //rolls="FICHE/EB/2018/1";
                 rolls="MEDI-IN/1001";
             }
                 
             
         }catch(Exception e){
             JOptionPane.showMessageDialog(null, e); 
             
         }
     }
      
       public void rollS()
     {
         try{
                 Class.forName("org.sqlite.JDBC");
           con= DriverManager.getConnection(url);
            String sql="SELECT NUM_ID FROM  h_p_achat_medoc ORDER BY NUM_ID DESC LIMIT 1";
             pst=con.prepareStatement(sql);
             rs=pst.executeQuery();
             if(rs.next()){
                 String rl=rs.getString("NUM_ID");
                 int ln=rl.length();
                 String stxt=rl.substring(0,9);
                 String snum=rl.substring(9,ln);
                 int n = Integer.parseInt(snum);
                 n++;
                 snum=Integer.toString(n);
                 NUM_ID=stxt+snum;
                 
                 
                  pst.close();
            rs.close();
                con.close();
             }else{
                 //rolls="FICHE/EB/2018/1";
                 NUM_ID="TRANS-IN/1001";
             }
                 
             
         }catch(Exception e){
             JOptionPane.showMessageDialog(null, e); 
             
         }
     }
public void buss(){
    
        
          try{
              Class.forName("org.sqlite.JDBC");
            String sql="SELECT distinct(CLASSI) FROM   h_p_medicament";
            con = DriverManager.getConnection(url);
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                //String sum=rs.getString("nom");
                String sums=rs.getString("CLASSI");
                classes.addItem(sums);
            }
             pst.close();
            rs.close();
            con.close();
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
     try{
              Class.forName("org.sqlite.JDBC");
            String sql="SELECT distinct(NOM) FROM   client_supply Where CLIENT='Supply'";
            con = DriverManager.getConnection(url);
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                //String sum=rs.getString("nom");
                String sums=rs.getString("NOM");
                four.addItem(sums);
            }
             pst.close();
            rs.close();
            con.close();
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
      try{
              Class.forName("org.sqlite.JDBC");
            String sql="SELECT SERVICE FROM   h_p_emballage";
            con = DriverManager.getConnection(url);
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                //String sum=rs.getString("nom");
                String sums=rs.getString("SERVICE");
             //   marc.addItem(sums);
            }
             pst.close();
            rs.close();
            con.close();
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
   
    }
  public void Search2(){
         
          try{
              DefaultListModel list = new DefaultListModel();
             Class.forName("org.sqlite.JDBC");
          String sql="SELECT * FROM h_p_medicament where NOMS LIKE '"+search_medoc.getText()+"%' AND CLASSI='"+classes.getSelectedItem()+"'";
             con = DriverManager.getConnection(url);
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                //String sum=rs.getString("nom");
                String sums=rs.getString("NOMS");
                 list.addElement(sums);
                
                 jList1.setModel(list);
            }
             pst.close();
            rs.close();
              
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
             
         }
  
  public void JLIST_from_COMPTE2()
    {
      
         String tmp =(String)jList1.getSelectedValue();
    
     
        try{
              Class.forName("org.sqlite.JDBC");
            String sql="SELECT * FROM   h_p_medicament Where NOMS='"+tmp+"' AND CLASSI='"+classes.getSelectedItem()+"'";
            con = DriverManager.getConnection(url);
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
              
     //   NOM,NOMS,CLASSI,CATEGORI,P_ACHAT,P_VENTE,D_UNITE,P_ACHAT_G,P_VENTE_G,G_VENTE,NUM,MARK,MARK_QTY,CONTAINER,CONTAINER_QTY,QTY_UNITE,VAL_UNITE,PV_$,PVG_$,PA_$,PAG_$        
                String sums1=rs.getString("PA_$");
                qty_r.setText(sums1);
                
                String sums2=rs.getString("CONTAINER");
                marc.removeAllItems();
                marc.addItem(sums2);
                marc.setSelectedItem(sums2);
                
                 String sums22=rs.getString("CATEGORI");
                forme.setText(sums22);
                
                  
                      
            }
             pst.close();
            rs.close();
               
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
    
        }
  public void combo_from_COMPTE2()
    {
      
         String tmp =marc.getSelectedItem().toString();
    
     
        try{
              Class.forName("org.sqlite.JDBC");
            String sql="SELECT * FROM   h_p_medicament Where CONTAINER='"+tmp+"' AND CLASSI='"+classes.getSelectedItem()+"' AND NOMS='"+jList1.getSelectedValue()+"'";
            con = DriverManager.getConnection(url);
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
              
     //   NOM,NOMS,CLASSI,CATEGORI,P_ACHAT,P_VENTE,D_UNITE,P_ACHAT_G,P_VENTE_G,G_VENTE,NUM,MARK,MARK_QTY,CONTAINER,CONTAINER_QTY,QTY_UNITE,VAL_UNITE,PV_$,PVG_$,PA_$,PAG_$        
                String sums1=rs.getString("PAG_$");
                qty_r.setText(sums1);
                
               
                  
                      
            }
             pst.close();
            rs.close();
               
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
    
        }
 // CREATE TABLE "h_p_achat_medoc" MEDICAMENT,CAT,CLASSE,FOUR,P_ACHAT,QTY,P_TOTAL,BARCODE,EXPR,DATES,NUM
  public void SELECT_MAX(){
   try{
              Class.forName("org.sqlite.JDBC");
            String sql="SELECT MAX(NUM) FROM   h_p_achat_medoc";
            con = DriverManager.getConnection(url);
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
              
     //   NOM,NOMS,CLASSI,CATEGORI,P_ACHAT,P_VENTE,D_UNITE,P_ACHAT_G,P_VENTE_G,G_VENTE,NUM,MARK,MARK_QTY,CONTAINER,CONTAINER_QTY,QTY_UNITE,VAL_UNITE,PV_$,PVG_$,PA_$,PAG_$        
              MAX=rs.getString("MAX(NUM)");
              num_fiche.setText(MAX);
                
                
               
                  
                      
            }
             pst.close();
            rs.close();
               
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
  
  }
  public void SELECT_SUM(){
   try{
              Class.forName("org.sqlite.JDBC");
            String sql="SELECT SUM(P_TOTAL) FROM   h_p_achat_medoc WHERE NUM='"+num_fiche.getText()+"'";
            con = DriverManager.getConnection(url);
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
        //     Double a= Double.parseDouble(total.getText());
     //   NOM,NOMS,CLASSI,CATEGORI,P_ACHAT,P_VENTE,D_UNITE,P_ACHAT_G,P_VENTE_G,G_VENTE,NUM,MARK,MARK_QTY,CONTAINER,CONTAINER_QTY,QTY_UNITE,VAL_UNITE,PV_$,PVG_$,PA_$,PAG_$        
           Double   MAXs=rs.getDouble("SUM(P_TOTAL)");
           DecimalFormat df = new DecimalFormat("#,##0.00");
       
    total.setText(""+df.format(MAXs));
            
                
                
               
                  
                      
            }
             pst.close();
            rs.close();
               
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
 
  }
  Double QTY=null;
   public void  calculeqty(){
  try{
      Double CONT,MARK;
              Class.forName("org.sqlite.JDBC");
            String sql="SELECT * FROM   h_p_medicament Where NOMS='"+jList1.getSelectedValue()+"' AND CLASSI='"+classes.getSelectedItem()+"'";
            con = DriverManager.getConnection(url);
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                CONT=rs.getDouble("CONTAINER_QTY");
              
                
                 MARK=rs.getDouble("MARK_QTY");
   
       Double BB = Double.parseDouble(qty.getText());
       QTY=(BB/MARK)*CONT;
       //Total=c.toString();  
                      
            }
             pst.close();
            rs.close();
               
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
    
  }
   public void save(){
       calculeqty();
       Double aa = Double.parseDouble(qty_r.getText());
       Double bb = Double.parseDouble(qty.getText());
       Double c;
       c=aa*bb;
       Total=c.toString();
       
       rollS();
       if(num_fiche.getText().equals("")){
        roll();
               try{
          Class.forName("org.sqlite.JDBC");
           con= DriverManager.getConnection(url);
     
         // java.sql.Connection con = getConnection();
     String sql="INSERT INTO h_p_achat_medoc (MEDICAMENT,CAT,CLASSE,FOUR,P_ACHAT,QTY,P_TOTAL,BARCODE,EXPR,DATES,NUM,NUM_ID,QTY_D)"+
             "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
     pst=con.prepareStatement(sql);
    
      pst.setString(1, jList1.getSelectedValue());
      pst.setString(2, forme.getText());
      pst.setString(3, classes.getSelectedItem().toString());
      pst.setString(4, four.getSelectedItem().toString());
      pst.setString(5, qty_r.getText());
      
      pst.setDouble(13,QTY);
      pst.setString(7, Total);
      pst.setString(8, barcode.getText());
      pst.setString(9, exp.getText());
      pst.setString(10,webDateField2.getText());
      pst.setString(11, rolls);
       pst.setString(12, NUM_ID);
       pst.setString(6, qty.getText());
    
   
      
      pst.executeUpdate();
                con.close();
              
  // JOptionPane.showMessageDialog(null,"Transaction Saved");
      
   //  cat1.setText("");
      
     }catch(Exception ex){JOptionPane.showMessageDialog(null,ex);} 
               
                 try{
          Class.forName("org.sqlite.JDBC");
           con= DriverManager.getConnection(url);
     
         ////   h_p_inventaire_medoc NUM_ID,CAT,CLASSI,QTY_DEBIT,QTY_CREDIT,DEBIT,CREDIT,NUM
     String sql="INSERT INTO  h_p_inventaire_medoc (MEDICAMENT,CAT,CLASSI,QTY_DEBIT,QTY_CREDIT,DEBIT,CREDIT,NUM_ID,NUM,FOUR,STATUT,QTY_DD,QTY_DC)"+
             "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
     pst=con.prepareStatement(sql);
    
      pst.setString(1, jList1.getSelectedValue());
      pst.setString(2, forme.getText());
      pst.setString(3, classes.getSelectedItem().toString());
     
      pst.setString(5,"");
      
      pst.setDouble(12,QTY);
      pst.setString(6, Total);
      pst.setString(7, "");
      pst.setString(8, NUM_ID);
        pst.setString(9, rolls);
      pst.setString(10, four.getSelectedItem().toString());
      pst.setString(11,"Achat");
       pst.setString(4,qty.getText());
        pst.setString(13,"");
    
   
      
      pst.executeUpdate();
                con.close();
      
     }catch(Exception ex){JOptionPane.showMessageDialog(null,ex);}  
               SELECT_MAX();
       }else{
               try{
          Class.forName("org.sqlite.JDBC");
           con= DriverManager.getConnection(url);
     
         // java.sql.Connection con = getConnection();
     String sql="INSERT INTO h_p_achat_medoc (MEDICAMENT,CAT,CLASSE,FOUR,P_ACHAT,QTY,P_TOTAL,BARCODE,EXPR,DATES,NUM,NUM_ID,QTY_D)"+
             "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
     pst=con.prepareStatement(sql);
    
      pst.setString(1, jList1.getSelectedValue());
      pst.setString(2, forme.getText());
      pst.setString(3, classes.getSelectedItem().toString());
      pst.setString(4, four.getSelectedItem().toString());
      pst.setString(5, qty_r.getText());
      
      pst.setDouble(13,QTY);
      pst.setString(7, Total);
      pst.setString(8, barcode.getText());
      pst.setString(9, exp.getText());
      pst.setString(10,webDateField2.getText());
      pst.setString(11, num_fiche.getText());
       pst.setString(12, NUM_ID);
       pst.setString(6, qty.getText());
    
   
      
      pst.executeUpdate();
                con.close();
              
   //JOptionPane.showMessageDialog(null,"Transaction Saved");
      
   //  cat1.setText("");
      
     }catch(Exception ex){JOptionPane.showMessageDialog(null,ex);}  
              
    
         
           try{
          Class.forName("org.sqlite.JDBC");
           con= DriverManager.getConnection(url);
     
         ////   h_p_inventaire_medoc NUM_ID,CAT,CLASSI,QTY_DEBIT,QTY_CREDIT,DEBIT,CREDIT,NUM
     String sql="INSERT INTO  h_p_inventaire_medoc (MEDICAMENT,CAT,CLASSI,QTY_DEBIT,QTY_CREDIT,DEBIT,CREDIT,NUM_ID,NUM,FOUR,STATUT,QTY_DD,QTY_DC)"+
             "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
     pst=con.prepareStatement(sql);
    
      pst.setString(1, jList1.getSelectedValue());
      pst.setString(2, forme.getText());
      pst.setString(3, classes.getSelectedItem().toString());
     
      pst.setString(5,"");
      
      pst.setDouble(12,QTY);
      pst.setString(6, Total);
      pst.setString(7, "");
      pst.setString(8, NUM_ID);
        pst.setString(9, num_fiche.getText());
      pst.setString(10, four.getSelectedItem().toString());
      pst.setString(11,"Achat");
        pst.setString(4,qty.getText());
        pst.setString(13,"");
    
   
      
      pst.executeUpdate();
                con.close();
      
     }catch(Exception ex){JOptionPane.showMessageDialog(null,ex);}  
              
   }
       SELECT_SUM();
             }

  public void refresh(){
  
    forme.setText("");
    classes.setSelectedItem("......");
    four.setSelectedItem("......");
    qty_r.setText("");
      
    qty.setText("");
    Total=null;
      barcode.setText("");
       exp.setText("");
      webDateField2.setText("");
    
  }
  
      public void call_in_tableS(){
  //   MEDICAMENT,CAT,CLASSE,FOUR,P_ACHAT,QTY,P_TOTAL,BARCODE,EXPR,DATES,NUM
	
	
      try{
             Class.forName("org.sqlite.JDBC");
           con= DriverManager.getConnection(url);
             String sql="SELECT MEDICAMENT,P_ACHAT as PU,QTY,P_TOTAL AS 'Prix TOTAL' FROM h_p_achat_medoc where NUM='"+num_fiche.getText()+"'";
     //  String sql="SELECT ID, `NUM_FACTURE`, `DATE_FACTURE`, `DESCRIPTION`, `DEBIT_QTY`, `DEBIT_PU`, `DEBIT_PT`, `DEBIT_TVA`, `CREDIT_QTY`, `CREDIT_PU`, `CREDIT_PT`, `CREDIT_TVA`, `DATES`, `NUM` FROM facture_fournisseur where compte='"+jComboBox6.getSelectedItem()+"'";
       pst = con.prepareStatement(sql);
      rs= pst.executeQuery();
      
       
       jTable1.setModel(DbUtils.resultSetToTableModel(rs));
       mode=new DefaultTableModel();
       
      
        
       TableColumn col0=jTable1.getColumnModel().getColumn(0);
        TableColumn col1=jTable1.getColumnModel().getColumn(1);
        TableColumn col2=jTable1.getColumnModel().getColumn(2);
        TableColumn col3=jTable1.getColumnModel().getColumn(3);
       
        
       
       
      
       
       col0.setPreferredWidth(250);
       col1.setPreferredWidth(20);
       col2.setPreferredWidth(20);
         col3.setPreferredWidth(50);
      
        
      
      
     
       
       
      // jTable1.setModel(mode);
       
     
    }catch(Exception ex){JOptionPane.showMessageDialog(null,ex);}  
     
     }
//   h_p_inventaire_medoc NUM_ID,CAT,CLASSI,QTY_DEBIT,QTY_CREDIT,DEBIT,CREDIT,NUM

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
        jLabel1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        exp = new com.alee.extended.date.WebDateField();
        qty_r = new javax.swing.JTextField();
        qty = new javax.swing.JTextField();
        jPanel10 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        four = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        classes = new javax.swing.JComboBox<>();
        jPanel6 = new javax.swing.JPanel();
        search_medoc = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jPanel13 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        forme = new javax.swing.JTextField();
        marc = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        barcode = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        num_fiche = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        webComboBox1 = new com.alee.laf.combobox.WebComboBox();
        webDateField2 = new com.alee.extended.date.WebDateField();
        jPanel7 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        total = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        jScrollPane10 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(192, 255, 192));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(192, 255, 192));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(128, 255, 128));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hopital/icons8_close_window_30px_1.png"))); // NOI18N
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1)
        );

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 1, 910, -1));

        jPanel4.setBackground(new java.awt.Color(192, 255, 192));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel9.setBackground(new java.awt.Color(192, 255, 192));
        jPanel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("Exp. Date:");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("Pix de Rev.:");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setText("Quantites:");

        exp.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        qty_r.setEditable(false);
        qty_r.setBackground(new java.awt.Color(240, 240, 241));
        qty_r.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        qty_r.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        qty.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        qty.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        qty.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                qtyKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(jLabel7))
                .addGap(9, 9, 9)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(qty_r)
                    .addComponent(exp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(qty))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(exp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(qty_r, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(qty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9)))
                    .addComponent(jLabel7))
                .addGap(6, 6, 6))
        );

        jPanel4.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 300, 215, -1));

        jPanel10.setBackground(new java.awt.Color(192, 255, 192));
        jPanel10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Fournisseur");
        jPanel10.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, -1));

        four.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        four.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "......." }));
        jPanel10.add(four, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 191, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Classification");
        jPanel10.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, -1));

        classes.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        classes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "......." }));
        jPanel10.add(classes, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 191, -1));

        jPanel4.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 12, 215, 102));

        jPanel6.setBackground(new java.awt.Color(192, 255, 192));
        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        search_medoc.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        search_medoc.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        search_medoc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                search_medocKeyReleased(evt);
            }
        });
        jPanel6.add(search_medoc, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 190, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("Medicament");
        jPanel6.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, -1));

        jList1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jList1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jList1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jList1);

        jPanel6.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 190, 42));

        jPanel4.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 120, 215, 92));

        jPanel13.setBackground(new java.awt.Color(192, 255, 192));
        jPanel13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel13.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("F. Pharma.");
        jPanel13.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        forme.setEditable(false);
        forme.setBackground(new java.awt.Color(240, 240, 241));
        forme.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        forme.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel13.add(forme, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 92, -1));

        marc.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        marc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "......." }));
        marc.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                marcPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        jPanel13.add(marc, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 30, 95, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("Marque");
        jPanel13.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 10, -1, -1));

        jPanel4.add(jPanel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 220, 215, 70));

        jPanel2.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 39, 236, 420));

        jPanel5.setBackground(new java.awt.Color(192, 255, 192));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        barcode.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        barcode.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jTextField6.setEditable(false);
        jTextField6.setBackground(new java.awt.Color(240, 240, 241));
        jTextField6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        num_fiche.setEditable(false);
        num_fiche.setBackground(new java.awt.Color(240, 240, 241));
        num_fiche.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        num_fiche.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setText("Barcode:");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setText("Recherche:");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setText("Numéro Fiche Entre:");

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel13.setText("Date:");

        webComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "......." }));
        webComboBox1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        webDateField2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11))
                .addGap(25, 25, 25)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(barcode)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel12)
                        .addGap(18, 18, 18))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(webComboBox1, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel13)
                        .addGap(26, 26, 26)))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(num_fiche)
                    .addComponent(webDateField2, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(barcode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(num_fiche, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel11)
                        .addComponent(jLabel13)
                        .addComponent(webDateField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(webComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel2.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(253, 39, 650, -1));

        jPanel7.setBackground(new java.awt.Color(192, 255, 192));
        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel14.setText("Total General Achat");

        total.setEditable(false);
        total.setBackground(new java.awt.Color(240, 240, 241));
        total.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        total.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 76, Short.MAX_VALUE)
                .addComponent(total, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(total, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18))
        );

        jPanel2.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(253, 336, 650, 60));

        jPanel8.setBackground(new java.awt.Color(192, 255, 192));
        jPanel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton1.setText("Nouveau");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton2.setText("Add");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton3.setText("Remove");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton4.setText("Save");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel2.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(253, 396, 650, 60));

        jPanel11.setBackground(new java.awt.Color(192, 255, 192));
        jPanel11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jTable1.setBackground(new java.awt.Color(192, 255, 192));
        jTable1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable1.setSelectionForeground(new java.awt.Color(198, 215, 233));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTable1MousePressed(evt);
            }
        });
        jScrollPane10.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 628, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(253, 120, 650, 210));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, 908, 470));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 928, 510));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable1MouseClicked

    private void jTable1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable1MousePressed

    private void search_medocKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_search_medocKeyReleased
Search2();        // TODO add your handling code here:
    }//GEN-LAST:event_search_medocKeyReleased

    private void jList1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList1MouseClicked
JLIST_from_COMPTE2();        // TODO add your handling code here:
    }//GEN-LAST:event_jList1MouseClicked

    private void marcPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_marcPopupMenuWillBecomeInvisible
combo_from_COMPTE2();        // TODO add your handling code here:
    }//GEN-LAST:event_marcPopupMenuWillBecomeInvisible

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
save(); 
call_in_tableS();// TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void qtyKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_qtyKeyPressed
int key = evt.getKeyCode();
     if (key == KeyEvent.VK_ENTER) {
        Toolkit.getDefaultToolkit().beep(); 
        save(); 
call_in_tableS();
        }        // TODO add your handling code here:
    }//GEN-LAST:event_qtyKeyPressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
         // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField barcode;
    private javax.swing.JComboBox<String> classes;
    private com.alee.extended.date.WebDateField exp;
    private javax.swing.JTextField forme;
    private javax.swing.JComboBox<String> four;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList<String> jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JComboBox<String> marc;
    private javax.swing.JTextField num_fiche;
    private javax.swing.JTextField qty;
    private javax.swing.JTextField qty_r;
    private javax.swing.JTextField search_medoc;
    private javax.swing.JTextField total;
    private com.alee.laf.combobox.WebComboBox webComboBox1;
    private com.alee.extended.date.WebDateField webDateField2;
    // End of variables declaration//GEN-END:variables
}
