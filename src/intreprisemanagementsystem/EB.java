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
import javax.swing.table.DefaultTableModel;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.TableColumn;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Administrator
 */
public class EB extends javax.swing.JInternalFrame {

   Connection con=null;
PreparedStatement pst=null;
ResultSet rs= null;
String etrolls;
DefaultTableModel mode;
    public EB() {
        initComponents();
         con=JavaDbConnect.dbConnect();
        etjComboBox6.addItem("CONSULTANT");
        etjComboBox6.addItem("EMPLOYEE");
         etsup1.setEditable(false);
            etchat.setEditable(false);
      
    }
    
     // AUTO ROLL NUMBER
     public void etroll()
     {
         try{
             String sql="SELECT NUM FROM etat_de_besoin ORDER BY NUM DESC LIMIT 1";
             pst=con.prepareStatement(sql);
             rs=pst.executeQuery();
             if(rs.next()){
                 String rl=rs.getString("NUM");
                 int ln=rl.length();
                 String stxt=rl.substring(0,2);
                 String snum=rl.substring(2,ln);
                 int n = Integer.parseInt(snum);
                 n++;
                 snum=Integer.toString(n);
                 etrolls=stxt+snum;
                 
                 
             }else{
                 //rolls="FICHE/EB/2018/1";
                 etrolls="EB1001";
             }
                 
             
         }catch(Exception e){
             JOptionPane.showMessageDialog(null, e); 
             
         }
     }
  public void attCall_roll()
    {
      
         String A =JOptionPane.showInputDialog("Please Entre Your Roll Number!!!"); 
        try{
            String sql="select * from Active_employee where ROLL= ? and Locks='Active'";
            
           
             pst=con.prepareStatement(sql);
            pst.setString(1, A);
            rs=pst.executeQuery();
            if(rs.next()){
             String add1 = rs.getString("NOMS");
             String add2 = rs.getString("LNAME");
              etsup1.setText(add1+" "+add2);
             
             String adde = rs.getString("roll");
             etroll1.setText(adde);
              
              
              
               try{
            String sqls="select * from employee where ROLLNUM= ?";
            
           
             pst=con.prepareStatement(sqls);
            pst.setString(1, A);
            rs=pst.executeQuery();
            if(rs.next()){
             String add11 = rs.getString("DEPARTMENT");
           etchat.setText(add11);
             
             
              // String add4 = rs.getString("ROLL");
              // etroll1.setText(add4);
            }
            
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
              
              
              
              
              
              
              
              // String add4 = rs.getString("ROLL");
              // etroll1.setText(add4);
            }else{JOptionPane.showMessageDialog(null, "Invalid Roll No. or Does not Have Info");  
            
              attCall_roll();
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
   
    } 

public void save(){
    Double qty = Double.parseDouble(etqty.getText());
    Double up = Double.parseDouble(etpu.getText());
    Double pt= qty*up;
    String tmp=jComboBox2.getSelectedItem().toString();
    String orientation;
    if(tmp.equals("Log")){
    orientation="LOGISTIQUE";
    }else{
    orientation="FINANCE";
    }
    etroll();
if(num.getText().equals("")){
       try {
            etroll();
        PreparedStatement pst = con.prepareStatement("INSERT INTO etat_de_besoin(SUP,CHANT,NUM,DET,QTY,PU,PT,DATES,APPROUVATION,EXCECUTE,PRINT,PAY,ORIENTATION) "
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?)");
        
        pst.setString(1, etsup1.getText());
         pst.setString(2, etchat.getText());
        pst.setString(3, etrolls);
         pst.setString(4, etdet.getText());
         pst.setString(5, etqty.getText());
         pst.setString(6, etpu.getText());
         pst.setDouble(7, pt);
         pst.setString(9, "");
          pst.setString(10,"");
          pst.setString(11,"");
          pst.setString(12,"");
            pst.setString(13,orientation);
       
          SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(new Date());
         pst.setString(8, addDate);
         
          pst.executeUpdate();
        
              //   JOptionPane.showMessageDialog(null,"CLERCK_IN  SUCCESSFUL BONNE JOURNEE!!!        ");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
    }else{
          try {
            etroll();
        PreparedStatement pst = con.prepareStatement("INSERT INTO etat_de_besoin(SUP,CHANT,NUM,DET,QTY,PU,PT,DATES,APPROUVATION,EXCECUTE,PRINT,PAY,ORIENTATION) "
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?)");
        
        pst.setString(1, etsup1.getText());
         pst.setString(2, etchat.getText());
        pst.setString(3, num.getText());
         pst.setString(4, etdet.getText());
         pst.setString(5, etqty.getText());
         pst.setString(6, etpu.getText());
         pst.setDouble(7, pt);
         pst.setString(9, "");
          pst.setString(10,"");
          pst.setString(11,"");
          pst.setString(12,"");
           pst.setString(13,orientation);
       
          SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(new Date());
         pst.setString(8, addDate);
         
          pst.executeUpdate();
        
              //   JOptionPane.showMessageDialog(null,"CLERCK_IN  SUCCESSFUL BONNE JOURNEE!!!        ");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    } 
        }
  try{
            String sqls="select MAX(NUM) from etat_de_besoin ";
            
           
             pst=con.prepareStatement(sqls);
          //  pst.setString(1, A);
            rs=pst.executeQuery();
            if(rs.next()){
             String add11 = rs.getString("MAX(NUM)");
           num.setText(add11);
             
             
              // String add4 = rs.getString("ROLL");
              // etroll1.setText(add4);
            }
            
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
  Table();
  refreshe();
} 
  // DELETE
    public void etdelete()
    {
        int row= etjTable3.getSelectedRow();
         // String rows =jTable1.getName()
        String  COMPTES = (etjTable3.getModel().getValueAt(row,0). toString());
         try{
        String sql = "DELETE FROM etat_de_besoin WHERE ID=?";
        
         pst = con.prepareStatement(sql);
         pst.setString(1,COMPTES);
         pst.executeUpdate();

     }catch(SQLException | HeadlessException ex ){
         JOptionPane.showMessageDialog(null,ex);
     } 
         Table();
    }
    
    public void etdeletee()
    {
         try{
        String sql = "DELETE FROM etat_de_besoin WHERE NUM=?";
        
         pst = con.prepareStatement(sql);
         pst.setString(1,num.getText());
         pst.executeUpdate();

     }catch(SQLException | HeadlessException ex ){
         JOptionPane.showMessageDialog(null,ex);
     } 
         Table();
    }
    
    
public void Table()
    {
         try{
           
             String sql="SELECT ID,`DET` as 'Description', `QTY` AS 'Quantity', `PU` AS 'Unity By Price', `PT` as 'Total Price', `DATES` as 'Delivery Date' FROM `etat_de_besoin` WHERE `NUM`='"+num.getText()+"'";
     //  String sql="SELECT ID, `NUM_FACTURE`, `DATE_FACTURE`, `DESCRIPTION`, `DEBIT_QTY`, `DEBIT_PU`, `DEBIT_PT`, `DEBIT_TVA`, `CREDIT_QTY`, `CREDIT_PU`, `CREDIT_PT`, `CREDIT_TVA`, `DATES`, `NUM` FROM facture_fournisseur where compte='"+jComboBox6.getSelectedItem()+"'";
       pst = con.prepareStatement(sql);
      rs= pst.executeQuery();
      
       
      etjTable3.setModel(DbUtils.resultSetToTableModel(rs));
       mode=new DefaultTableModel();
       
       
        TableColumn col0=etjTable3.getColumnModel().getColumn(0);
        TableColumn col1=etjTable3.getColumnModel().getColumn(1);
        TableColumn col2=etjTable3.getColumnModel().getColumn(2);
        TableColumn col3=etjTable3.getColumnModel().getColumn(3);
        TableColumn col4=etjTable3.getColumnModel().getColumn(4);
        TableColumn col5=etjTable3.getColumnModel().getColumn(5);
      //  TableColumn col5=etjTable3.getColumnModel().getColumn(5);

       
      
       col0.setPreferredWidth(10);
       col1.setPreferredWidth(350);
       col2.setPreferredWidth(50);
       col3.setPreferredWidth(50);
       col4.setPreferredWidth(50);
       col5.setPreferredWidth(50);
      // col5.setPreferredWidth(50);
      
      
      
       
       
      // jTable1.setModel(mode);
       
     
    }catch(SQLException ex ){
     JOptionPane.showMessageDialog(null, ex);
}
    } 
public void refreshe(){
etqty.setText("Qty");
etpu.setText("up");
etdet.setText("Wording");
//etdet.setText("Wording");

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
        jLabel1 = new javax.swing.JLabel();
        num = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        etjComboBox6 = new javax.swing.JComboBox<>();
        etsup1 = new javax.swing.JTextField();
        etchat = new javax.swing.JTextField();
        etroll1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        etpu = new javax.swing.JTextField();
        jComboBox3 = new javax.swing.JComboBox<>();
        etqty = new javax.swing.JTextField();
        etdet = new javax.swing.JTextField();
        jComboBox2 = new javax.swing.JComboBox<>();
        jButton4 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        etjTable3 = new javax.swing.JTable();

        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_Accounting_16px.png"))); // NOI18N

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));

        jLabel1.setBackground(new java.awt.Color(255, 0, 0));
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("X");
        jLabel1.setOpaque(true);
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        num.setEditable(false);
        num.setBackground(new java.awt.Color(240, 240, 241));
        num.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        num.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(num, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(num, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        etjComboBox6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        etjComboBox6.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                etjComboBox6PopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        etsup1.setBackground(new java.awt.Color(240, 240, 241));
        etsup1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        etsup1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                etsup1KeyReleased(evt);
            }
        });

        etchat.setBackground(new java.awt.Color(240, 240, 241));
        etchat.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        etchat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                etchatActionPerformed(evt);
            }
        });
        etchat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                etchatKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                etchatKeyTyped(evt);
            }
        });

        etroll1.setEditable(false);
        etroll1.setBackground(new java.awt.Color(240, 240, 241));
        etroll1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton1.setText("...");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton3.setText("Rmv");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton2.setText("Add");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jComboBox1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "USD", "CDF", "USH" }));

        etpu.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        etpu.setText("Up");
        etpu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                etpuMouseClicked(evt);
            }
        });
        etpu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                etpuKeyTyped(evt);
            }
        });

        jComboBox3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pcs", "Kg", "M", "Sac", "Ltr", "g" }));

        etqty.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        etqty.setText("Qty");
        etqty.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                etqtyMouseClicked(evt);
            }
        });
        etqty.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                etqtyKeyTyped(evt);
            }
        });

        etdet.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        etdet.setText("Wording");
        etdet.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                etdetMouseClicked(evt);
            }
        });
        etdet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                etdetActionPerformed(evt);
            }
        });

        jComboBox2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Fin", "Log" }));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        jButton4.setText("Send");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(etjComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(etsup1, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(etdet, javax.swing.GroupLayout.PREFERRED_SIZE, 397, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(6, 6, 6)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(etqty, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(etpu, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(etchat))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(etroll1, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(etjComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(etsup1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(etchat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(etroll1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(0, 0, 0)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(etdet, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(etqty)
                    .addComponent(jComboBox3)
                    .addComponent(etpu)
                    .addComponent(jComboBox1)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton4))
                .addContainerGap())
        );

        etjTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(etjTable3);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane1)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 297, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

        setBounds(60, 30, 1059, 495);
    }// </editor-fold>//GEN-END:initComponents

    private void etjComboBox6PopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_etjComboBox6PopupMenuWillBecomeInvisible
if(etjComboBox6.getSelectedItem().equals("CONSULTANT")){//&&etjComboBox6.getSelectedItem().equals("EMPLOYE")){
            etsup1.setEditable(true);
            etchat.setEditable(true);
             }else{
                  etsup1.setEditable(false);
            etchat.setEditable(false);
        }// TODO add your handling code here:
    }//GEN-LAST:event_etjComboBox6PopupMenuWillBecomeInvisible

    private void etsup1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_etsup1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_etsup1KeyReleased

    private void etchatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_etchatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_etchatActionPerformed

    private void etchatKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_etchatKeyReleased
            // TODO add your handling code here:
    }//GEN-LAST:event_etchatKeyReleased

    private void etchatKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_etchatKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_etchatKeyTyped

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
etsup1.setText("");
etchat.setText("");
etroll1.setText("");
num.setText("");
etjTable3.setModel(new DefaultTableModel());
refreshe();
        attCall_roll();              // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void etqtyKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_etqtyKeyTyped
        char c=evt.getKeyChar();
        if(!(Character.isDigit(c) || (c==KeyEvent.VK_BACK_SPACE)||c==KeyEvent.VK_DELETE ||c==KeyEvent.VK_PERIOD )){
            evt.consume();
        }        // TODO add your handling code here:
    }//GEN-LAST:event_etqtyKeyTyped

    private void etpuKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_etpuKeyTyped
        char c=evt.getKeyChar();
        if(!(Character.isDigit(c) || (c==KeyEvent.VK_BACK_SPACE)||c==KeyEvent.VK_DELETE ||c==KeyEvent.VK_PERIOD )){
            evt.consume();
        }        // TODO add your handling code here:
    }//GEN-LAST:event_etpuKeyTyped

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
      

        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void etdetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_etdetActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_etdetActionPerformed

    private void etdetMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_etdetMouseClicked
etdet.setText("");        // TODO add your handling code here:
    }//GEN-LAST:event_etdetMouseClicked

    private void etqtyMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_etqtyMouseClicked
etqty.setText("");
etpu.setText("0.0");// TODO add your handling code here:
    }//GEN-LAST:event_etqtyMouseClicked

    private void etpuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_etpuMouseClicked

etpu.setText("");        // TODO add your handling code here:
    }//GEN-LAST:event_etpuMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
save();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
 etdelete();
                // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
  int response = JOptionPane.showConfirmDialog(null, "DO YOU WANT TO SEND THIS FOR APPROUVEMENT?", "Confirm",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (response == JOptionPane.YES_OPTION) {
etsup1.setText("");
etchat.setText("");
etroll1.setText("");
num.setText("");
etjTable3.setModel(new DefaultTableModel());
refreshe();
            }else{
            etdeletee();
            etsup1.setText("");
etchat.setText("");
etroll1.setText("");
num.setText("");
etjTable3.setModel(new DefaultTableModel());
refreshe();
            }        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel1MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField etchat;
    private javax.swing.JTextField etdet;
    private javax.swing.JComboBox<String> etjComboBox6;
    private javax.swing.JTable etjTable3;
    private javax.swing.JTextField etpu;
    private javax.swing.JTextField etqty;
    private javax.swing.JTextField etroll1;
    private javax.swing.JTextField etsup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField num;
    // End of variables declaration//GEN-END:variables
}
