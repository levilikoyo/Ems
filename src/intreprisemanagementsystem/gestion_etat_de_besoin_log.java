/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intreprisemanagementsystem;

import Home_page.menu_log;
import static intreprisemanagementsystem.journal_caisse.jTable1;
import static java.awt.Event.UP;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.showInputDialog;
import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.table.DefaultTableCellRenderer;
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
 * @author Dosh
 */
public class gestion_etat_de_besoin_log extends javax.swing.JInternalFrame {

Connection con=null;
PreparedStatement pst=null;
ResultSet rs= null;
String rolls;
DefaultTableModel mode;
String backup=null;
    public gestion_etat_de_besoin_log() {
        initComponents();
          con=JavaDbConnect.dbConnect();
          showEBData();
    }
   public void showEBData(){ 
   // SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
    // String add = dateFormats.format (jDateChooser1.setDate(new Date()));
     try{
       
         String sql="SELECT distinct NUM FROM etat_de_besoin WHERE  PRINT='' and ORIENTATION2='Null'";
          pst = con.prepareStatement(sql);
        rs= pst.executeQuery();
        jTable2.setModel(DbUtils.resultSetToTableModel(rs));
//            DefaultTableCellRenderer centre =new DefaultTableCellRenderer();
//        centre.setHorizontalAlignment(JLabel.RIGHT);
//         jTable2.getColumnModel().getColumn(0).setCellRenderer(centre);
        
      }catch(SQLException ex ){
      JOptionPane.showMessageDialog(null, ex);
}    
      try{
       
         String sql="SELECT distinct NUM FROM etat_de_besoin WHERE ORIENTATION='LOGISTIQUE' AND PRINT='Print'";
          pst = con.prepareStatement(sql);
        rs= pst.executeQuery();
        jTable4 .setModel(DbUtils.resultSetToTableModel(rs));
        
      }catch(SQLException ex ){
      JOptionPane.showMessageDialog(null, ex);
}    
      
       try{
       
         String sql="SELECT distinct NUM FROM etat_de_besoin WHERE ORIENTATION2='Fin' order by NUM";
          pst = con.prepareStatement(sql);
        rs= pst.executeQuery();
        jTable5 .setModel(DbUtils.resultSetToTableModel(rs));
        
      }catch(SQLException ex ){
      JOptionPane.showMessageDialog(null, ex);
}   
        try{
       
         String sql="SELECT distinct NUM FROM etat_de_besoin_B WHERE ORIENTATION2='Fin'";
          pst = con.prepareStatement(sql);
        rs= pst.executeQuery();
        jTable6 .setModel(DbUtils.resultSetToTableModel(rs));
        
      }catch(SQLException ex ){
      JOptionPane.showMessageDialog(null, ex);
}    
}
    public void select_jTableNUM()
   {
        int row= jTable2.getSelectedRow();
          String Table_click = (jTable2.getModel().getValueAt(row,0). toString());
//        try{
//         
//          String sql = "SELECT * FROM etat_de_besoin WHERE NUM= '"+Table_click+"'";
//          pst = con.prepareStatement(sql);
//          rs=pst.executeQuery();
//          if(rs.next()){
//              String add1 = rs.getString("NUM");//SUP,CHANT,NUM,DET,QTY,PU,PT,DATES
//              recherche.setText(add1);
//              String add = rs.getString("APPROUVATION");//SUP,CHANT,NUM,DET,QTY,PU,PT,DATES
//              app.setText(add);
//                
//          }
//         
//    }catch(SQLException ex ){
//         JOptionPane.showMessageDialog(null,ex);
//     }
        
         try{
           
             String sql="SELECT ID,DET AS 'LIBELLE',QTY FROM `etat_de_besoin` WHERE NUM= '"+Table_click+"'";
     //  String sql="SELECT ID, `NUM_FACTURE`, `DATE_FACTURE`, `DESCRIPTION`, `DEBIT_QTY`, `DEBIT_PU`, `DEBIT_PT`, `DEBIT_TVA`, `CREDIT_QTY`, `CREDIT_PU`, `CREDIT_PT`, `CREDIT_TVA`, `DATES`, `NUM` FROM facture_fournisseur where compte='"+jComboBox6.getSelectedItem()+"'";
       pst = con.prepareStatement(sql);
      rs= pst.executeQuery();
      
       
       jTable1.setModel(DbUtils.resultSetToTableModel(rs));
       mode=new DefaultTableModel();
       
       
        TableColumn col0=jTable1.getColumnModel().getColumn(0);
        TableColumn col1=jTable1.getColumnModel().getColumn(1);
        TableColumn col2=jTable1.getColumnModel().getColumn(2);
           
        col0.setPreferredWidth(2);
       col1.setPreferredWidth(250);
       col2.setPreferredWidth(50);
    
    }catch(SQLException ex ){
     JOptionPane.showMessageDialog(null, ex);
}
   }
    
     public void select_jTable()
   {
        try{
          int row= jTable2.getSelectedRow();
          String Table_click = (jTable2.getModel().getValueAt(row,0). toString());
          String sql = "SELECT * FROM etat_de_besoin WHERE NUM= '"+Table_click+"'";
          pst = con.prepareStatement(sql);
          rs=pst.executeQuery();
          if(rs.next()){
                String add2 = rs.getString("SUP");
              sup.setText(add2);
                String add3 = rs.getString("ORIENTATION");
              orient.setText(add3);
                String add4 = rs.getString("NUM");
              roll.setText(add4);
              String add4s = rs.getString("BUSS");
              chat.setText(add4s);
                }
        }catch(SQLException ex ){JOptionPane.showMessageDialog(null,ex);}
   }
     
       public void selectontable(){
    
        TableModel model1 =jTable1.getModel();
        int indexs[]=jTable1.getSelectedRows();
            int row_lib[]=jTable1.getSelectedRows();
        
        Object[] row = new Object[5];
        
        DefaultTableModel model2 = (DefaultTableModel) jTable3.getModel();
        for(int i=0; i < indexs.length;i++){
        String UPS = null,PTS = null,UTES=null;    
     try{
          int row_num= jTable2.getSelectedRow();
          String num = (jTable2.getModel().getValueAt(row_num,0). toString());
        
       
          
           
          String libeles = (jTable1.getModel().getValueAt(row_lib[i],0). toString());
         //  String libele=libeles.replace("'", "");
          String qty = (jTable1.getModel().getValueAt(row_lib[i],1). toString());
         // String ute = (jTable1.getModel().getValueAt(row_lib[i],2). toString());
          // = (jTable1.getModel().getValueAt(row_lib,2). toString());
          String sql = "SELECT * FROM etat_de_besoin WHERE NUM= '"+num+"' and ID='"+libeles+"' ";
          pst = con.prepareStatement(sql);
          rs=pst.executeQuery();
          while(rs.next()){
                UPS= rs.getString("PU");
                PTS= rs.getString("PT");
                UTES= rs.getString("UNITE");
             
                }
        }catch(SQLException ex ){JOptionPane.showMessageDialog(null,ex);}        
       // JOptionPane.showMessageDialog(null,UPS+PTS+UTES) ;   
            
        row[0]= model1.getValueAt(indexs[i],1);
        row[1]= model1.getValueAt(indexs[i],2);
        row[2]= UTES;
       row[3]= UPS;
       row[4]= PTS;
        model2.addRow(row);
        
        // jTable3.setModel(DbUtils.resultSetToTableModel(rs));
      
       
       
        TableColumn col0=jTable3.getColumnModel().getColumn(0);
        TableColumn col1=jTable3.getColumnModel().getColumn(1);
        TableColumn col2=jTable3.getColumnModel().getColumn(2);
        TableColumn col3=jTable3.getColumnModel().getColumn(3);
         TableColumn col4=jTable3.getColumnModel().getColumn(4);
        
        col0.setPreferredWidth(500);
       col1.setPreferredWidth(50);
       col2.setPreferredWidth(50);
       col3.setPreferredWidth(50);
       col4.setPreferredWidth(50);
        }
        
    }
      public void selectontablemoin(){
     
       
        int indexs[]=jTable3.getSelectedRows();
        
        DefaultTableModel model = (DefaultTableModel) jTable3.getModel();
        for(int i=0; i < indexs.length;i++){
          
    int getSelectedRowsForDeletion = jTable3.getSelectedRow();
        model.removeRow(getSelectedRowsForDeletion);
        
        }
        
    }
       public void save(){
     try{
 int rowss= jTable2.getSelectedRow();
         // String rows =jTable1.getName()
          String num = (jTable2.getModel().getValueAt(rowss,0). toString());
  int rows=jTable3.getRowCount();

String ReplaceString;

  for(int row = 0; row<rows; row++)
  {   
  
    String libelle = (String)jTable3.getValueAt(row, 0);
    ReplaceString = libelle.replace("'", "''");
    String qty = (String)jTable3.getValueAt(row, 1);
    String up = (String)jTable3.getValueAt(row, 3);
    String pt = (String)jTable3.getValueAt(row, 4);
    
  //  String queryco = "Insert into invoice(qty,unitprice,description,total) values ('"+qty+"','"+unitprice+"','"+description+"','"+total+"')";
 String queryco="UPDATE etat_de_besoin SET QTY='"+qty+"',PU='"+up+"',PT='"+pt+"',ORIENTATION2='Fin' WHERE DET='"+ReplaceString+"' and NUM='"+num+"' and BUSS='"+chat.getText()+"'";
    pst = con.prepareStatement(queryco);
    pst.execute();     
  }
  JOptionPane.showMessageDialog(null, "Transaction Saved Save");
}
catch(Exception ex){
JOptionPane.showMessageDialog(null,ex);
}
  }
       
        //REPORT
               public void report()
     {
       
         
      /// 
          
    
     String A =showInputDialog("ENTREZ LE ROLL_No!!!");
          if(A.equals("1234")){
              
        
     
             try{
                 
                   String sqls="Select path from pathn";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
            if(rs.next()){
             
                String sum=rs.getString("path");
                  
                 String NameFiles=sum;
            
            String NameFile=""+NameFiles+"etatdebesoin_log.jrxml";
                 
                 
                 
                JasperDesign jd=JRXmlLoader.load(NameFile);
                 
                // String report ="C:\\Users\\Doshe\\Documents\\NetBeansProjects\\intreprisemanagementsystem\\src\\report\\newReportmateriau.jrxml";
           //     JasperDesign jd=JRXmlLoader.load("C:\\Users\\Dosh\\Documents\\NetBeansProjects\\intreprisemanagementsystem\\src\\intreprisemanagementsystem\\etatdebesoin.jrxml");
             String sql="select * from etat_de_besoin  where NUM='"+jMenu2.getText()+"'";
             
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
          }else{
              showMessageDialog(null,"VOUS N'ETES PAS AUTORISE A IMPRIMER CE DOCUMENT");
          }
     }
               
 //REPORT
               public void reportrec()
     {
       
         
      /// 
          
    
     String A =showInputDialog("ENTREZ LE ROLL_No!!!");
          if(A.equals("1234")){
              
        
     
             try{
                 
                   String sqls="Select path from pathn";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
            if(rs.next()){
             
                String sum=rs.getString("path");
                  
                 String NameFiles=sum;
            
            String NameFile=""+NameFiles+"requisition.jrxml";
                 
                 
                 
                JasperDesign jd=JRXmlLoader.load(NameFile);
                 
                // String report ="C:\\Users\\Doshe\\Documents\\NetBeansProjects\\intreprisemanagementsystem\\src\\report\\newReportmateriau.jrxml";
           //     JasperDesign jd=JRXmlLoader.load("C:\\Users\\Dosh\\Documents\\NetBeansProjects\\intreprisemanagementsystem\\src\\intreprisemanagementsystem\\etatdebesoin.jrxml");
           //  String sql="select*from etat_de_besoiN INNER JOIN projet ON etat_de_besoiN.BUSS=projet.PROJET_NUm INNER JOIN employee ON etat_de_besoiN.SUP=CONCAT(employee.NAME," ",employee.LNAME)";
             String sql="select*from etat_de_besoiN  INNER JOIN projet ON etat_de_besoiN.BUSS=projet.PROJET_NUm INNER JOIN  employee ON etat_de_besoiN.SUP= CONCAT(employee.NAME,' ',employee.LNAME) WHERE etat_de_besoin.NUM='"+jMenu2.getText()+"'";
            
                
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
          }else{
              showMessageDialog(null,"VOUS N'ETES PAS AUTORISE A IMPRIMER CE DOCUMENT");
          }
     }
                
               
                            public void reportrecb()
     {
       
         
      /// 
          
    
     String A =showInputDialog("ENTREZ LE ROLL_No!!!");
          if(A.equals("1234")){
              
        
     
             try{
                 
                   String sqls="Select path from pathn";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
            if(rs.next()){
             
                String sum=rs.getString("path");
                  
                 String NameFiles=sum;
            
            String NameFile=""+NameFiles+"requisition.jrxml";
                 
                 
                 
                JasperDesign jd=JRXmlLoader.load(NameFile);
                 
                // String report ="C:\\Users\\Doshe\\Documents\\NetBeansProjects\\intreprisemanagementsystem\\src\\report\\newReportmateriau.jrxml";
           //     JasperDesign jd=JRXmlLoader.load("C:\\Users\\Dosh\\Documents\\NetBeansProjects\\intreprisemanagementsystem\\src\\intreprisemanagementsystem\\etatdebesoin.jrxml");
            String sql="select*from etat_de_besoin_b  INNER JOIN projet ON etat_de_besoin_b.BUSS=projet.PROJET_NUm INNER JOIN  employee ON etat_de_besoin_b.SUP= CONCAT(employee.NAME,' ',employee.LNAME) WHERE etat_de_besoin_b.NUM='"+jMenu2.getText()+"'";
                 
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
          }else{
              showMessageDialog(null,"VOUS N'ETES PAS AUTORISE A IMPRIMER CE DOCUMENT");
          }
     }
               
               
public void delete()
    {
         try{
        String sql = "DELETE FROM etat_de_besoin WHERE NUM=?";
        
         pst = con.prepareStatement(sql);
         pst.setString(1,jMenu2.getText());
         pst.executeUpdate();

     }catch(SQLException | HeadlessException ex ){
         JOptionPane.showMessageDialog(null,ex);
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
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        sup = new javax.swing.JTextField();
        chat = new javax.swing.JTextField();
        roll = new javax.swing.JTextField();
        orient = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jButton2 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jPanel6 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable5 = new javax.swing.JTable();
        jPanel10 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTable6 = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_Accounting_16px.png"))); // NOI18N

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jTable2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1"
            }
        ));
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jTable2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTable2KeyPressed(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        sup.setBackground(new java.awt.Color(204, 204, 255));
        sup.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        sup.setText("Emmeteur");

        chat.setBackground(new java.awt.Color(204, 204, 255));
        chat.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        chat.setText("Projet");

        roll.setEditable(false);
        roll.setBackground(new java.awt.Color(204, 204, 255));
        roll.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        roll.setText("Num");
        roll.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                rollKeyTyped(evt);
            }
        });

        orient.setEditable(false);
        orient.setBackground(new java.awt.Color(240, 240, 241));
        orient.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton2.setText("Envois demande d’achat");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_Print_30px.png"))); // NOI18N
        jLabel3.setText("Print EB");
        jLabel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLabel3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel3MouseEntered(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_Paper_Plane_24px.png"))); // NOI18N
        jLabel4.setText("Approv.");
        jLabel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLabel4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        jTable4.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1"
            }
        ));
        jTable4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable4MouseClicked(evt);
            }
        });
        jTable4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTable4KeyPressed(evt);
            }
        });
        jScrollPane4.setViewportView(jTable4);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("EB LOG", jPanel7);

        jTable5.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTable5.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1"
            }
        ));
        jTable5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable5MouseClicked(evt);
            }
        });
        jTable5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTable5KeyPressed(evt);
            }
        });
        jScrollPane5.setViewportView(jTable5);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Fiche de Requisition", jPanel8);

        jTable6.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTable6.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1"
            }
        ));
        jTable6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable6MouseClicked(evt);
            }
        });
        jTable6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTable6KeyPressed(evt);
            }
        });
        jScrollPane6.setViewportView(jTable6);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Requisition Backup", jPanel10);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/cancel.png"))); // NOI18N
        jLabel5.setText("Cancel");
        jLabel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLabel5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(roll)
                    .addComponent(chat)
                    .addComponent(orient, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(sup, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(jSeparator2)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sup, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(roll, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(orient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "La réquisition ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jTable1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable1.setRowHeight(26);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 332, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Demande d’achat ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jTable3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Libelle", "Qty", "Utes", "Up", "Qty"
            }
        ));
        jTable3.setRowHeight(26);
        jTable3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable3MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTable3);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 575, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText(">");
        jLabel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("<");
        jLabel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jMenu1.setText("X");
        jMenu1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jMenu1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu1MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu1);

        jMenu3.setText("Search");
        jMenu3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jMenu3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu3MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu3);

        jMenu2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jMenu2.setText("Num EB");
        jMenu2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jMenu2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jMenu2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
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
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setBounds(30, 10, 1324, 558);
    }// </editor-fold>//GEN-END:initComponents

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
try{
 con=JavaDbConnect.dbConnect(); 
  select_jTableNUM();
       select_jTable();
       int rowss= jTable2.getSelectedRow();
          String num = (jTable2.getModel().getValueAt(rowss,0). toString());
          jMenu2.setText(num);
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
        
        
     //   searchS();
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable2MouseClicked

    private void jTable2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable2KeyPressed

    private void rollKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_rollKeyTyped
        //search();         // TODO add your handling code here:
    }//GEN-LAST:event_rollKeyTyped

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
      //  select_jTable();
    }//GEN-LAST:event_jTable1MouseClicked

    private void jTable3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable3MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable3MouseClicked

    private void jMenu1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu1MouseClicked
menu_log.eb.setText("Etats des besoins");
        this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu1MouseClicked

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
} // TODO add your handling code here:
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
selectontablemoin();        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jTable4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable4MouseClicked
int rowss= jTable4.getSelectedRow();
          String num = (jTable4.getModel().getValueAt(rowss,0). toString());
          jMenu2.setText(num);  
          orient.setText("LOGISTIQUE");// TODO add your handling code here:
    }//GEN-LAST:event_jTable4MouseClicked

    private void jTable4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable4KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable4KeyPressed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
try{
 con=JavaDbConnect.dbConnect(); 
      save(); 
showEBData();
 mode=new DefaultTableModel();
        mode.addColumn("Libelle");    
        mode.addColumn("Qty");
        mode.addColumn("Utes");
        mode.addColumn("Up");
        mode.addColumn("Pt");
        jTable3.setModel(mode);
        
       DefaultTableModel  modes=new DefaultTableModel();

        jTable1.setModel(modes);
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
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
try{
 con=JavaDbConnect.dbConnect(); 
      if(orient.getText().equals("LOGISTIQUE")){
        report();
showEBData();
        try{
    String appr ="Print";
 String sql = "UPDATE etat_de_besoin SET PRINT=? WHERE NUM =?";
        
         pst = con.prepareStatement(sql);
         pst.setString(2,jMenu2.getText());
          pst.setString(1,appr);
                     
         pst.executeUpdate();


}catch(Exception ex){JOptionPane.showMessageDialog(null,ex);} 
}else{
    if (backup.equals("No")){
     reportrec(); 
    }else{
    reportrecb(); 
    }
 

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
    }//GEN-LAST:event_jLabel3MouseClicked

    private void jTable5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable5MouseClicked
int rowss= jTable5.getSelectedRow();
          String num = (jTable5.getModel().getValueAt(rowss,0). toString());
          jMenu2.setText(num); 
          backup="No";
          orient.setText("FINANCE");// TODO add your handling code here:
    }//GEN-LAST:event_jTable5MouseClicked

    private void jTable5KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable5KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable5KeyPressed

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
try{
 con=JavaDbConnect.dbConnect(); 
      delete();  
showEBData();
 mode=new DefaultTableModel();
        mode.addColumn("Libelle");    
        mode.addColumn("Qty");
        mode.addColumn("Up");
        mode.addColumn("Pt");
        jTable3.setModel(mode);
        
       DefaultTableModel  modes=new DefaultTableModel();
//        modes.addColumn("Libelle");    
//        modes.addColumn("Qty");
//        modes.addColumn("Up");
//        modes.addColumn("Pt");
        jTable1.setModel(modes); 
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
    }//GEN-LAST:event_jLabel5MouseClicked

    private void jTable6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable6MouseClicked
int rowss= jTable6.getSelectedRow();
          String num = (jTable6.getModel().getValueAt(rowss,0). toString());
          jMenu2.setText(num); 
          backup="Yes";
          orient.setText("FINANCE");        // TODO add your handling code here:
    }//GEN-LAST:event_jTable6MouseClicked

    private void jTable6KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable6KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable6KeyPressed

    private void jLabel3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel3MouseEntered

    private void jMenu3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu3MouseClicked
try{
 con=JavaDbConnect.dbConnect(); 
     sercheb m = new  sercheb();
       
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
    }//GEN-LAST:event_jMenu3MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField chat;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
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
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    public static javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    public static javax.swing.JTable jTable4;
    public static javax.swing.JTable jTable5;
    public static javax.swing.JTable jTable6;
    private javax.swing.JTextField orient;
    private javax.swing.JTextField roll;
    private javax.swing.JTextField sup;
    // End of variables declaration//GEN-END:variables
}
