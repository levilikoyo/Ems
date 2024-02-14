/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pv_compilation_offre;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import net.proteanit.sql.DbUtils;
/**
 *
 * @author Dosh
 */
public class pv extends javax.swing.JPanel {
  Connection con=null;
PreparedStatement pst=null;
ResultSet rs= null;
String etrolls,roll_id,etrolls_log;
DefaultTableModel mode;
    public pv() {
        initComponents();
         con=JavaDbConnect.dbConnect();
        Table();
    }
    public void Table()
    {
         try{
           
             String sql="SELECT NUM as 'Numero offre',NAME AS Offre FROM POSTES";
     //  String sql="SELECT ID, `NUM_FACTURE`, `DATE_FACTURE`, `DESCRIPTION`, `DEBIT_QTY`, `DEBIT_PU`, `DEBIT_PT`, `DEBIT_TVA`, `CREDIT_QTY`, `CREDIT_PU`, `CREDIT_PT`, `CREDIT_TVA`, `DATES`, `NUM` FROM facture_fournisseur where compte='"+jComboBox6.getSelectedItem()+"'";
       pst = con.prepareStatement(sql);
      rs= pst.executeQuery();
      
       
      offres.setModel(DbUtils.resultSetToTableModel(rs));
       mode=new DefaultTableModel();
       
       
        TableColumn col0=offres.getColumnModel().getColumn(0);
        TableColumn col1=offres.getColumnModel().getColumn(1);
      

       col0.setPreferredWidth(100);
       col1.setPreferredWidth(250);
       
     
    }catch(SQLException ex ){
     JOptionPane.showMessageDialog(null, ex);
}
//               
         
    } 

public void etdelete()
    {
        int row= offres.getSelectedRow();
         // String rows =jTable1.getName()
        String  num = (offres.getModel().getValueAt(row,0). toString());
         try{
        String sql = "DELETE FROM POSTES WHERE NUM='"+num+"'";
        
         pst = con.prepareStatement(sql);
        // pst.setString(1,COMPTES);
         pst.executeUpdate();

     }catch(SQLException | HeadlessException ex ){
         JOptionPane.showMessageDialog(null,ex);
     } 
    }
   public void selectontable(){
    
        TableModel model1 =doc.getModel();
        int indexs[]=doc.getSelectedRows();
        Object[] row = new Object[4];
        DefaultTableModel model2 = (DefaultTableModel) jTable4.getModel();
        for(int i=0; i < indexs.length;i++){
        row[0]= model1.getValueAt(indexs[i],0);
        row[1]= model1.getValueAt(indexs[i],1);
        model2.addRow(row);
        
        TableColumn col0=jTable4.getColumnModel().getColumn(0);
        TableColumn col1=jTable4.getColumnModel().getColumn(1);
           
        col0.setPreferredWidth(500);
       col1.setPreferredWidth(50);
        }
        
    }
   public void selectontable_vide(){
    
        TableModel model1 =doc.getModel();
        int indexs[]=doc.getSelectedRows();
        Object[] row = new Object[4];
        DefaultTableModel model2 = (DefaultTableModel) jTable4.getModel();
        for(int i=0; i < indexs.length;i++){
        row[0]= "";
        row[1]= "0";
        model2.addRow(row);
        
        TableColumn col0=jTable4.getColumnModel().getColumn(0);
        TableColumn col1=jTable4.getColumnModel().getColumn(1);
        col0.setPreferredWidth(500);
       col1.setPreferredWidth(50);
        }
        
    }
   
      public void selectontablemoin(){
     
       
        int indexs[]=jTable4.getSelectedRows();
        
        DefaultTableModel model = (DefaultTableModel) jTable4.getModel();
        for(int i=0; i < indexs.length;i++){
          
    int getSelectedRowsForDeletion = jTable4.getSelectedRow();
        model.removeRow(getSelectedRowsForDeletion);
        
        }
        
    }
           public void etroll()
     {
        String NUMS = null;
        int SUSTRING = 0;
         int rowss= offres.getSelectedRow();
         // String rows =jTable1.getName()
          String Offre = (offres.getModel().getValueAt(rowss,0). toString());
           try{
          String sql="SELECT * FROM  POSTES WHERE NUM='"+Offre+"'";
          
             
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
           if(rs.next()){
                //String sum=rs.getString("nom");
                NUMS=rs.getString("NUM_DOS");
                SUSTRING=rs.getInt("SUBSTR");
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
         try{
             String sql="SELECT NUMERO FROM PVS  WHERE OFFRE ='"+Offre+"' ORDER BY NUMERO DESC LIMIT 1";
             pst=con.prepareStatement(sql);
             rs=pst.executeQuery();
             if(rs.next()){
                 String rl=rs.getString("NUMERO");
                 int ln=rl.length();
                 String stxt=rl.substring(0,SUSTRING);
                 String snum=rl.substring(SUSTRING,ln);
                 //sum=001
                 int n = Integer.parseInt(snum);
                
                 n++;
                 snum=Integer.toString(n);
                 if(n < 10){
                 etrolls=stxt+"00"+snum;//001 ou 009
                 }else if(n < 100){
                 etrolls=stxt+"0"+snum;//010 ou 099
                 }else if(n > 100){
                 etrolls=stxt+snum;// 100 ou 199
                 }  else if(n > 1000){
                 etrolls=stxt+snum;// 1000 ou 1999
                 }     
                     
             }else{
                
                 etrolls=NUMS;
             }
                 
             
         }catch(Exception e){
             JOptionPane.showMessageDialog(null, e); 
             
         }
            
     }
     public void saveallDOCSS(){
      etroll();
 int rowss= offres.getSelectedRow();
         // String rows =jTable1.getName()
          String Offre = (offres.getModel().getValueAt(rowss,0). toString());
          String Poste = (offres.getModel().getValueAt(rowss,1). toString());
      try {
           // etroll();
        PreparedStatement pst = con.prepareStatement("INSERT INTO `PVS`(NOM,PNOM,PRNOM,TEL,ADDR,OFFRE,DOC,EXIST,POINT,POSTE,NUMERO,STATUS) select '"+nom.getText()+"','"+pnom.getText()+"','"+prnom.getText()+"','"+tel.getText()+"','"+address.getText()+"','"+Offre+"',DOC,'No','0','"+Poste+"','"+etrolls+"','Offre' from DOCSS where offre='"+home_offre.batch.getSelectedValue()+"' and status='Offre'");
        
       
          pst.executeUpdate();
        
              //   JOptionPane.showMessageDialog(null,"CLERCK_IN  SUCCESSFUL BONNE JOURNEE!!!        ");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    } 
      try{
            String sqls="select MAX(NUMERO) from PVS WHERE OFFRE='"+Offre+"' and status='Offre'";
            
           
             pst=con.prepareStatement(sqls);
          //  pst.setString(1, A);
            rs=pst.executeQuery();
            if(rs.next()){
             String add11 = rs.getString("MAX(NUMERO)");
          jLabel5.setText(add11);
             
            }
            
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
     }
       public void save(){
     etroll();
 int rowss= offres.getSelectedRow();
         // String rows =jTable1.getName()
          String Offre = (offres.getModel().getValueAt(rowss,0). toString());
          String Poste = (offres.getModel().getValueAt(rowss,1). toString());
  int rows=jTable4.getRowCount();

  

  for(int row = 0; row<rows; row++)
  {   
  
    String Doc,Pts;
  //  ReplaceString = libelle.replace("'", "''");
    String Docc = (String)jTable4.getValueAt(row, 0);
    String Ptss = (String)jTable4.getValueAt(row, 1);
   Doc = Docc.replace("'", "''");
   Pts = Ptss.replace("'", "''");
    try{
            String sqls="select * from PVS WHERE  DOC='"+Doc+"' and NUMERO='"+jLabel5.getText()+"'";
            
           
             pst=con.prepareStatement(sqls);
          //  pst.setString(1, A);
            rs=pst.executeQuery();
            if(rs.next()){
              try {
            
        PreparedStatement pst = con.prepareStatement(" UPDATE PVS SET NOM=?,PNOM=?,PRNOM=?,TEL=?,ADDR=?,EXIST=?,POINT=? WHERE DOC='"+Doc+"' and NUMERO='"+jLabel5.getText()+"'");
        pst.setString(1,nom.getText());
        pst.setString(2,pnom.getText());
        pst.setString(3,prnom.getText());
        pst.setString(4,tel.getText());
        pst.setString(5,address.getText());
        pst.setString(7,Pts);
        pst.setString(6,"Yes");
        pst.executeUpdate();     
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());}
             
            }else{
            
            try {
            
        PreparedStatement pst = con.prepareStatement("INSERT INTO `PVS`(`NOM`, `PNOM`, `PRNOM`, `TEL`, `ADDR`, `OFFRE`, `DOC`, `EXIST`, `POINT`, `POSTE`, `NUMERO`)"
        +"value(?,?,?,?,?,?,?,?,?,?,?)");
        
        pst.setString(1,nom.getText());
         pst.setString(2,pnom.getText());
       pst.setString(3,prnom.getText());
         pst.setString(4,tel.getText());
         pst.setString(5,address.getText());
         pst.setString(6,Offre);
         pst.setString(7,Doc);
         pst.setString(8,"Yes");
         pst.setString(9,Pts);
         pst.setString(10,Poste);
         pst.setString(11,jLabel5.getText());
         
          pst.executeUpdate();
        
              //   JOptionPane.showMessageDialog(null,"CLERCK_IN  SUCCESSFUL BONNE JOURNEE!!!        ");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    } 
            }
            
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }

  }
//   try {
//           // etroll();
//        PreparedStatement pst = con.prepareStatement("INSERT INTO `pv`(NOM,PNOM,PRNOM,TEL,ADDR,OFFRE,DOC,EXIST,POINT,POSTES,NUMERO) select DOC from DOCS where pv.DOC<>");
//        
//       
//          pst.executeUpdate();
//        
//              //   JOptionPane.showMessageDialog(null,"CLERCK_IN  SUCCESSFUL BONNE JOURNEE!!!        ");
//    } catch (Exception ex) {
//         JOptionPane.showMessageDialog(null, ex.getMessage());
//    } 
        JOptionPane.showMessageDialog(null,"Transaction Saved");
        
       }
       
       public void save_UPDATE(){
           int roW= home_offre.jTable1.getSelectedRow();
          String numero = (home_offre.jTable1.getModel().getValueAt(roW,0). toString());
    // etroll();
 int rowss= offres.getSelectedRow();
         // String rows =jTable1.getName()
          String Offre = (offres.getModel().getValueAt(rowss,0). toString());
          String Poste = (offres.getModel().getValueAt(rowss,1). toString());
  int rows=jTable4.getRowCount();

  

  for(int row = 0; row<rows; row++)
  {   
  
    String Doc,Pts;
  //  ReplaceString = libelle.replace("'", "''");
    String Docc = (String)jTable4.getValueAt(row, 0);
    String Ptss = (String)jTable4.getValueAt(row, 1);
   Doc = Docc.replace("'", "''");
   Pts = Ptss.replace("'", "''");
    try{
            String sqls="select * from PVS WHERE  DOC='"+Doc+"' and NUMERO='"+numero+"'";
            
           
             pst=con.prepareStatement(sqls);
          //  pst.setString(1, A);
            rs=pst.executeQuery();
            if(rs.next()){
              try {
            
        PreparedStatement pst = con.prepareStatement(" UPDATE PVS SET EXIST=?,POINT=? WHERE DOC='"+Doc+"' and NUMERO='"+numero+"'");
         pst.setString(2,Pts);
          pst.setString(1,"Yes");
          pst.executeUpdate();     
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());}
             
            }else{
            
            try {
            
        PreparedStatement pst = con.prepareStatement("INSERT INTO `PVS`(`NOM`, `PNOM`, `PRNOM`, `TEL`, `ADDR`, `OFFRE`, `DOC`, `EXIST`, `POINT`, `POSTE`, `NUMERO`)"
        +"value(?,?,?,?,?,?,?,?,?,?,?)");
        
        pst.setString(1,nom.getText());
         pst.setString(2,pnom.getText());
       pst.setString(3,prnom.getText());
         pst.setString(4,tel.getText());
         pst.setString(5,address.getText());
         pst.setString(6,Offre);
         pst.setString(7,Doc);
         pst.setString(8,"Yes");
         pst.setString(9,Pts);
         pst.setString(10,Poste);
         pst.setString(11,numero);
         
          pst.executeUpdate();
        
              //   JOptionPane.showMessageDialog(null,"CLERCK_IN  SUCCESSFUL BONNE JOURNEE!!!        ");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    } 
            }
            
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }

  }
//   try {
//           // etroll();
//        PreparedStatement pst = con.prepareStatement("INSERT INTO `pv`(NOM,PNOM,PRNOM,TEL,ADDR,OFFRE,DOC,EXIST,POINT,POSTES,NUMERO) select DOC from DOCS where pv.DOC<>");
//        
//       
//          pst.executeUpdate();
//        
//              //   JOptionPane.showMessageDialog(null,"CLERCK_IN  SUCCESSFUL BONNE JOURNEE!!!        ");
//    } catch (Exception ex) {
//         JOptionPane.showMessageDialog(null, ex.getMessage());
//    } 
        JOptionPane.showMessageDialog(null,"Transaction Saved");
        
       }
        public void call(){ 
        
          String val = null;
         try{
            String sql="SELECT sum(point) FROM  docss where offre='"+home_offre.batch.getSelectedValue()+"' and status='offre'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            if(rs.next()){
               //  `NOM`, `PNOM`, `PRNOM`, `TEL`, `ADDR`, `OFFRE`, `DOC`, `EXIST`, `POINT`, `POSTE`, `NUMERO` FROM `pv` WHERE 1
               val=rs.getString("sum(point)");
                }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
      //  DefaultListModel list = new DefaultListModel();
 
         try{
          String sql="SELECT NUMERO,FORMAT((SUM(POINT)*100/"+val+"),2) AS '-%-' FROM PVS where OFFRE='"+home_offre.batch.getSelectedValue()+"' GROUP BY NUMERO";
             
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
             home_offre.jTable1.setModel(DbUtils.resultSetToTableModel(rs));
         }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
         
         TableColumn col0=home_offre.jTable1.getColumnModel().getColumn(0);
        TableColumn col1=home_offre.jTable1.getColumnModel().getColumn(1);
        
       // TableColumn col2=jTable1.getColumnModel().getColumn(2);
       
       
       col0.setPreferredWidth(200);
       col1.setPreferredWidth(50);
      // col2.setPreferredWidth(20);
      
    
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
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jScrollPane4 = new javax.swing.JScrollPane();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(245, 245, 245));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Candidat Infos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        nom.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        nom.setText("Nom:");
        nom.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        nom.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nomMouseClicked(evt);
            }
        });
        nom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nomActionPerformed(evt);
            }
        });

        pnom.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        pnom.setText("Post nom:");
        pnom.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        pnom.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnomMouseClicked(evt);
            }
        });
        pnom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pnomActionPerformed(evt);
            }
        });

        prnom.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        prnom.setText("Pres nom:");
        prnom.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        prnom.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                prnomMouseClicked(evt);
            }
        });
        prnom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prnomActionPerformed(evt);
            }
        });

        tel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tel.setText("Telephone:");
        tel.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        tel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                telMouseClicked(evt);
            }
        });
        tel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                telActionPerformed(evt);
            }
        });

        address.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        address.setText("Addresse:");
        address.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        address.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addressMouseClicked(evt);
            }
        });
        address.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addressActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nom)
                    .addComponent(pnom, javax.swing.GroupLayout.DEFAULT_SIZE, 359, Short.MAX_VALUE)
                    .addComponent(prnom, javax.swing.GroupLayout.DEFAULT_SIZE, 359, Short.MAX_VALUE)
                    .addComponent(tel, javax.swing.GroupLayout.DEFAULT_SIZE, 359, Short.MAX_VALUE)
                    .addComponent(address, javax.swing.GroupLayout.DEFAULT_SIZE, 359, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(nom, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnom, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(prnom, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(address, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Offres", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        offres.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        offres.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        offres.setRowHeight(26);
        offres.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                offresMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(offres);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "TDR", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        doc.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        doc.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        doc.setRowHeight(26);
        doc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                docMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(doc);

        jTable4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Document", "Pts"
            }
        ));
        jTable4.setRowHeight(26);
        jTable4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable4MouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(jTable4);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText(">>");
        jLabel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("<<");
        jLabel2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText(">>>");
        jLabel3.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 371, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 501, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton1.setText("Save");
        jButton1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Numéro plis:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton2.setText("New");
        jButton2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void offresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_offresMouseClicked
        // select_jTable();        // TODO add your handling code here:
    }//GEN-LAST:event_offresMouseClicked

    private void docMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_docMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_docMouseClicked

    private void jTable4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable4MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable4MouseClicked

    private void nomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nomActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nomActionPerformed

    private void pnomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pnomActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pnomActionPerformed

    private void prnomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prnomActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_prnomActionPerformed

    private void telActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_telActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_telActionPerformed

    private void addressActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addressActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addressActionPerformed

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
try{
 con=JavaDbConnect.dbConnect(); 
    selectontable();  
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
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
try{
 con=JavaDbConnect.dbConnect(); 
   selectontable_vide(); 
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
    }//GEN-LAST:event_jLabel3MouseClicked

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
try{
 con=JavaDbConnect.dbConnect(); 
 selectontablemoin();   
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
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
 try{
 con=JavaDbConnect.dbConnect(); 
    if(nom.getText().equals("")||pnom.getText().equals("")||prnom.getText().equals("")||tel.getText().equals("")||address.getText().equals("")||nom.getText().equals("Nom:")||pnom.getText().equals("Post nom:")||prnom.getText().equals("Pres nom:")||tel.getText().equals("Telephone:")||address.getText().equals("Addresse:")){
        JOptionPane.showMessageDialog(null,"Wrong Data","Error",JOptionPane.ERROR_MESSAGE);
 }else{
     if(jLabel5.getText().equals("")){
     saveallDOCSS();
      save();
     }else{
       save();
     }
   
      
    
     
      
       call();
         mode=new DefaultTableModel();
        mode.addColumn("Document");    
        mode.addColumn("Pts");
        jTable4.setModel(mode);
        nom.setText("Nom:");
        pnom.setText("Post nom:");
        prnom.setText("Pres nom:");
        tel.setText("Telephone:");
        address.setText("Addresse:");
      jLabel5.setText("");
 } 
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
    }//GEN-LAST:event_jButton1ActionPerformed

    private void nomMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nomMouseClicked
if(nom.getText().equals("Nom:")){
nom.setText("");
}else{

}        // TODO add your handling code here:
    }//GEN-LAST:event_nomMouseClicked

    private void pnomMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnomMouseClicked
if(pnom.getText().equals("Post nom:")){
pnom.setText("");
}        // TODO add your handling code here:
    }//GEN-LAST:event_pnomMouseClicked

    private void prnomMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_prnomMouseClicked
if(prnom.getText().equals("Pres nom:")){
prnom.setText("");
}        // TODO add your handling code here:
    }//GEN-LAST:event_prnomMouseClicked

    private void telMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_telMouseClicked
if(tel.getText().equals("Telephone:")){
tel.setText("");
}        // TODO add your handling code here:
    }//GEN-LAST:event_telMouseClicked

    private void addressMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addressMouseClicked
if(address.getText().equals("Addresse:")){
address.setText("");
}        // TODO add your handling code here:
    }//GEN-LAST:event_addressMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
 mode=new DefaultTableModel();
        mode.addColumn("Document");    
        mode.addColumn("Pts");
        jTable4.setModel(mode);
        nom.setText("Nom:");
        pnom.setText("Post nom:");
        prnom.setText("Pres nom:");
        tel.setText("Telephone:");
        address.setText("Addresse:");
jLabel5.setText("");        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static final javax.swing.JTextField address = new javax.swing.JTextField();
    public static final javax.swing.JTable doc = new javax.swing.JTable();
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    public static final javax.swing.JLabel jLabel5 = new javax.swing.JLabel();
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    public static final javax.swing.JTable jTable4 = new javax.swing.JTable();
    public static final javax.swing.JTextField nom = new javax.swing.JTextField();
    public static final javax.swing.JTable offres = new javax.swing.JTable();
    public static final javax.swing.JTextField pnom = new javax.swing.JTextField();
    public static final javax.swing.JTextField prnom = new javax.swing.JTextField();
    public static final javax.swing.JTextField tel = new javax.swing.JTextField();
    // End of variables declaration//GEN-END:variables
}
