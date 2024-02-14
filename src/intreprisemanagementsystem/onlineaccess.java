/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intreprisemanagementsystem;

import com.alee.laf.WebLookAndFeel;
import static intreprisemanagementsystem.journal_caisse.jTable1;
import java.awt.Color;
import java.awt.Component;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Iterator;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Dosh
 */
public class onlineaccess extends javax.swing.JFrame {

 Connection con=null;
 Connection cononline=null;
PreparedStatement pst=null;
ResultSet rs= null;
 DefaultTableModel mode;
    public onlineaccess() {
        initComponents();
        cononline=JavaDbConnectonline.dbConnect();
        con=JavaDbConnectonline.dbConnect();

         // cononline=JavaDbConnect.dbConnect();
      // con=JavaDbConnect.dbConnect();
        call();
      //  jTable1.setDefaultRenderer(Object.class,new PINTAR_TABELA());
    }

public void call(){
              try{
            String sql="select * from  projet";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                String sum=rs.getString("PROJET_NUM");
                 buss.addItem(sum);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex); }  
     }


    public void attCall_IN_LIST_serach()
    { 
        DefaultListModel list = new DefaultListModel();
 if(jour.getSelectedItem().equals("Journals")){
 try{
          String sql="SELECT distinct(code),Compte FROM ohada_trans where buss='"+buss.getSelectedItem()+"' and classe =5 order by CODE";
             
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            jTable3.setModel(DbUtils.resultSetToTableModel(rs));
                 TableColumn col0=jTable3.getColumnModel().getColumn(0);
        TableColumn col1=jTable3.getColumnModel().getColumn(1);
       col0.setPreferredWidth(50);
       col1.setPreferredWidth(250);
       
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
 }else{
 try{
          String sql="SELECT CODE_SUB_CAT AS Lb,SUB_CAT AS Items,FORMAT(((SUM(CREDIT)*100)/(SUM(DEBIT))),2) AS '_%_' FROM budget_trans where projet='"+buss.getSelectedItem()+"' GROUP by CODE_SUB_CAT";
             
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            jTable3.setModel(DbUtils.resultSetToTableModel(rs));
           TableColumn col0=jTable3.getColumnModel().getColumn(0);
        TableColumn col1=jTable3.getColumnModel().getColumn(1);
        TableColumn col2=jTable3.getColumnModel().getColumn(2);
       col0.setPreferredWidth(30);
       col1.setPreferredWidth(200);
       col2.setPreferredWidth(50);
       
        
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
 
 }
        
    }
    
        public void clicktable1()
    { 
          
        NumberFormat nf = new DecimalFormat("#,###,###");
          int row= jTable3.getSelectedRow();
          String Table_click = (jTable3.getModel().getValueAt(row,0). toString());
    if(jour.getSelectedItem().equals("Journals")){
       
    try{
            // `COMPTE_M`, `COMPTE`, `CODE_M`, `CODE`, `CLASSE`, `SUBSTR`, `DEBIT`, `CREDIT`, `NUM`, `DATES`, `BUSS`, `LIBELLE`, `JOURNAL`, `CODE1`, `NUM_FACTURE`, `DEBIT_CDF`, `CREDIT_CDF`    
             String tmp=buss.getSelectedItem().toString();
    String sqlS="select `NUM`,`LB`, `CODE1` as 'CODE',`LIBELLE` AS 'DESCRIPTION OF TRANSACTION', `CREDIT` AS DEBIT, `DEBIT` as CREDIT, `DATES` from ohada_trans where BUSS = '"+tmp+"' AND CODE='"+Table_click+"' order by DATES,NUM";
    //`LIBELLE`, `ENTRE`, `SORTIE`, `DATE`, `BUSS`, `USED`, `NUM`
    pst = cononline.prepareStatement(sqlS);
      rs= pst.executeQuery();
     
       jTable1.setModel(DbUtils.resultSetToTableModel(rs));
    
             }catch(Exception ex){
                 JOptionPane.showMessageDialog(null, ex);
             }
                 DefaultTableCellRenderer centre =new DefaultTableCellRenderer();
        centre.setHorizontalAlignment(JLabel.CENTER);
         TableColumn col0=jTable1.getColumnModel().getColumn(0);
        TableColumn col1=jTable1.getColumnModel().getColumn(1);
          jTable1.getColumnModel().getColumn(1).setCellRenderer(centre);
        TableColumn col2=jTable1.getColumnModel().getColumn(2);
        TableColumn col3=jTable1.getColumnModel().getColumn(3);
          //centre.setHorizontalAlignment(JLabel.CENTER);
            jTable1.getColumnModel().getColumn(2).setCellRenderer(centre);
        TableColumn col4=jTable1.getColumnModel().getColumn(4);
         // centre.setHorizontalAlignment(JLabel.CENTER);
         
            jTable1.getColumnModel().getColumn(4).setCellRenderer(centre);
            //jTable1.getColumnModel().getColumn(4).;
           TableColumn col5=jTable1.getColumnModel().getColumn(5);
            TableColumn col6=jTable1.getColumnModel().getColumn(6);
      
       
       col0.setPreferredWidth(60);
       col1.setPreferredWidth(20);
       col2.setPreferredWidth(20);
       col3.setPreferredWidth(370);
       col4.setPreferredWidth(20);
       col5.setPreferredWidth(20);
       col6.setPreferredWidth(20);
     //  jTable1.setDefaultRenderer(Object.class,new PINTAR_TABELA());
       
     
        try{
          String sql="SELECT SUM(DEBIT),SUM(CREDIT),(ROUND(SUM(DEBIT)-SUM(CREDIT)),DECIMAL) FROM ohada_trans where CODE ='"+Table_click+"' AND BUSS='"+buss.getSelectedItem()+"' order by CODE";
             
            pst=cononline.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                //String sum=rs.getString("nom");
                Double sumd=rs.getDouble("SUM(DEBIT)");
                 Double sumc=rs.getDouble("SUM(CREDIT)");
                  Double sums=rs.getDouble("(SUM(DEBIT)-SUM(CREDIT))");
                 
                 String fn = nf.format(sumd);
             jTextField4.setText(fn);
             
             String fnc = nf.format(sumc);
             jTextField5.setText(fnc);
             
             String fns = nf.format(sums);
             jTextField7.setText(fns);
                // jList1.setModel(list);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
    
    }else{
    
        try{
          
           //DATES, COMPTE, LIBELLE,DEBIT,DEBIT,BUSS,NUM,NUM_ID,FACTURE,CLIENT
          String  sql="SELECT SUB_CAT AS ITEMS,FORMAT(sum(DEBIT),2) as 'DEBIT',Format(sum(credit),2) AS 'CREDIT',FORMAT((SUM(DEBIT)-SUM(CREDIT)),2) AS 'SOLD',DATES from budget_trans WHERE CODE_SUB_CAT = '"+Table_click+"' AND PROJET='"+buss.getSelectedItem()+"'   group by CODE_SUB_CAT";
           // String sqls="select distinct(`NUM_FACTURE`) AS 'No_INVOICE', `DATE_FACTURE` AS 'DEL_DATE' from   facture_fournisseur where NOT VALIDE= '0'";
            
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
           // while(rs.next()){
            jTable1.setModel(DbUtils.resultSetToTableModel(rs));
            
            
             TableColumn col0=jTable1.getColumnModel().getColumn(0);
        TableColumn col1=jTable1.getColumnModel().getColumn(1);
        TableColumn col2=jTable1.getColumnModel().getColumn(2);
        TableColumn col3=jTable1.getColumnModel().getColumn(3);
        
         TableColumn col4=jTable1.getColumnModel().getColumn(4);
      //  TableColumn col5=jTable1.getColumnModel().getColumn(5);
         
       
       
      
       
       col0.setPreferredWidth(350);
       col1.setPreferredWidth(50);
        col2.setPreferredWidth(50);
         col3.setPreferredWidth(50);
         col4.setPreferredWidth(50);
       //  col5.setPreferredWidth(50);
        
     
     
            
            
     
 
            }
         
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
   try{
              
            String sql="SELECT SUM(DEBIT),SUM(CREDiT) FROM   budget_trans WHERE CODE_SUB_CAT = '"+Table_click+"' AND PROJET='"+buss.getSelectedItem()+"'";
            
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                //String sum=rs.getString("nom");
               Double debits =rs.getDouble("SUM(DEBIT)");
               Double credits =rs.getDouble("SUM(CREDIT)");
               
               Double c=debits-credits;
            String fn = nf.format(debits);
             jTextField4.setText(fn);
             
             String fnc = nf.format(credits);
             jTextField5.setText(fnc);
             
             String fns = nf.format(c);
             jTextField7.setText(fns);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }  
    }
 
  try{
      DefaultListModel list = new DefaultListModel();
          String sql="SELECT CODE as Lb,Items,FORMAT(((SUM(CREDIT)*100)/(SUM(DEBIT))),2) AS '_%_' FROM budget_trans where CODE_SUB_CAT='"+Table_click+"' and projet='"+buss.getSelectedItem()+"' GROUP by CODE";
             
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
           jTable4.setModel(DbUtils.resultSetToTableModel(rs));
           TableColumn col0=jTable4.getColumnModel().getColumn(0);
        TableColumn col1=jTable4.getColumnModel().getColumn(1);
       TableColumn col2=jTable4.getColumnModel().getColumn(2);
       col0.setPreferredWidth(50);
       col1.setPreferredWidth(250);
       col2.setPreferredWidth(50);
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
  
//   try{
//      DefaultListModel list = new DefaultListModel();
//          String sql="SELECT distinct(CODE) as Lb,Items FROM budget_trans where CODE_SUB_CAT='"+Table_click+"' and projet='"+buss.getSelectedItem()+"' AND (SUM (CREDIT)>SUM(DEBIT)) order by CODE";
//             
//            pst=con.prepareStatement(sql);
//            rs=pst.executeQuery();
//           jTable5.setModel(DbUtils.resultSetToTableModel(rs));
////           TableColumn col0=jTable4.getColumnModel().getColumn(0);
////        TableColumn col1=jTable4.getColumnModel().getColumn(1);
////    //     TableColumn col2=jTable4.getColumnModel().getColumn(2);
////       col0.setPreferredWidth(50);
////       col1.setPreferredWidth(250);
////      // col2.setPreferredWidth(50);
//            }
//        catch(Exception ex){
//          JOptionPane.showMessageDialog(null, ex);    
//        }
//  
//  
    }
        
        
        public void clicktable2(){
        NumberFormat nf = new DecimalFormat("#,###,###");
           int row= jTable4.getSelectedRow();
          String Table_click = (jTable4.getModel().getValueAt(row,0). toString());
          try{
          
           //DATES, COMPTE, LIBELLE,DEBIT,DEBIT,BUSS,NUM,NUM_ID,FACTURE,CLIENT
          String sql="SELECT ITEM,DEBIT,CREDIT,DATES from budget_trans WHERE CODE = '"+Table_click+"' AND PROJET='"+buss.getSelectedItem()+"'ORDER BY DATEs";
           // String sqls="select distinct(`NUM_FACTURE`) AS 'No_INVOICE', `DATE_FACTURE` AS 'DEL_DATE' from   facture_fournisseur where NOT VALIDE= '0'";
            
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
           // while(rs.next()){
            jTable1.setModel(DbUtils.resultSetToTableModel(rs));
            
            
             TableColumn col0=jTable1.getColumnModel().getColumn(0);
        TableColumn col1=jTable1.getColumnModel().getColumn(1);
        TableColumn col2=jTable1.getColumnModel().getColumn(2);
        TableColumn col3=jTable1.getColumnModel().getColumn(3);
      //   jTable1.getColumnModel().getColumn(3)..setCellRenderer(centre);
         
       
       
      
       
       col0.setPreferredWidth(580);
       col1.setPreferredWidth(50);
        col2.setPreferredWidth(50);
         col3.setPreferredWidth(20);
        
     
     
            
       // jTable1 = new JTable(tableModel);
jTable1.setDefaultRenderer(Double.class,new MyRenderer());  
     
 
            }
         
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
   try{
              
            String sql="SELECT SUM(DEBIT),SUM(CREDiT) FROM   budget_trans WHERE CODE = '"+Table_click+"' AND PROJET='"+buss.getSelectedItem()+"'";
            
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                //String sum=rs.getString("nom");
               Double debits =rs.getDouble("SUM(DEBIT)");
               Double credits =rs.getDouble("SUM(CREDIT)");
               
               Double c=debits-credits;
            String fn = nf.format(debits);
             jTextField4.setText(fn);
             
             String fnc = nf.format(credits);
             jTextField5.setText(fnc);
             
             String fns = nf.format(c);
             jTextField7.setText(fns);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }  
        }
        
        public void call_jtree(){
           // String [] al = null;
         //   int listSize=al.size();
            DefaultListModel list = new DefaultListModel();
              // create jtree root
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Budget Lines");
//     try{
//          String sql="SELECT LBSUB FROM budget where project='"+buss.getSelectedItem()+"' group by LBSUB";
//             
//            pst=con.prepareStatement(sql);
//            rs=pst.executeQuery();
//          while(rs.next()){
//                //String sum=rs.getString("nom");
//                String sums=rs.getString("LBSUB");
//              for(int i=0,i<rs.rowDeleted())
//                 list.addElement(sums);
//                  jList1.setModel(list);
//                  
//                 
//                
//                   DefaultMutableTreeNode row = new DefaultMutableTreeNode(sums);
//                     root.add(row);
//                     
//                     String code = null; 
//                     
//                      try{
//              
//            String sqls="SELECT code FROM budget where LBSUB='"+sums+"' and project='"+buss.getSelectedItem()+"'";
//            
//            pst=con.prepareStatement(sqls);
//            rs=pst.executeQuery();
//            while(rs.next()){
//                 code=rs.getString("code");
//             DefaultMutableTreeNode node = new DefaultMutableTreeNode(code);
//                     
//                       row.add(node);
//            }
//            }
//        catch(Exception ex){
//          JOptionPane.showMessageDialog(null, ex);    
//        }  
//                     
//                     
//                     
//                   
//                       
//                      
//            }
//           DefaultTreeModel model = new DefaultTreeModel(root);
//        jTree1.setModel(model); 
//            }
//        catch(Exception ex){
//          JOptionPane.showMessageDialog(null, ex);    
//        }   
//         
      // create jtree root
       // DefaultMutableTreeNode root = new DefaultMutableTreeNode("Budget Lines");
        
        // rows
//        String [] a={"1.1","1.2","1.3","1.4"};
//        for(int i = 0; i < a.length; i++){
//            
//            int rowIndex = i+1;
//            
//            DefaultMutableTreeNode row = new DefaultMutableTreeNode(a);
//            
//           // columns 
//            for(int c = 1; c < jTable1.getColumnCount(); c++)
//            {
//                DefaultMutableTreeNode node = new DefaultMutableTreeNode(jTable1.getValueAt(i, c));
//                // add data to the row
//                row.add(node);
//            }
//            // add the row to the root
//            root.add(row);
//        }
//        DefaultTreeModel model = new DefaultTreeModel(root);
//        jTree1.setModel(model); 
        
        }
        public class MyRenderer extends JLabel implements TableCellRenderer {
   public Component getTableCellRendererComponent(JTable table,
      Object value, boolean isSelected, boolean hasFocus, int row,
      int col) {
      DefaultTableCellRenderer renderer =
         new DefaultTableCellRenderer();
      Component c = renderer.getTableCellRendererComponent(table,
         value, isSelected, hasFocus, row, col);
      String s = "";
      if (col == 3) {
         DecimalFormat dFormat = new DecimalFormat("#0.0");
         Double d = (Double) value;
         s = dFormat.format(d);
         c = renderer.getTableCellRendererComponent(table, s,
            isSelected, hasFocus, row, col);
         ((JLabel) c).setHorizontalAlignment(SwingConstants.RIGHT);
      }
      return c;
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

        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jPanel2 = new javax.swing.JPanel();
        buss = new javax.swing.JComboBox<>();
        jour = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jPanel6 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        jScrollPane5 = new javax.swing.JScrollPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8-organization-40.png"))); // NOI18N
        jLabel4.setText("Association des Femmes pour la Promotion et le Developpement Endogene (AFPDE asbl)");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_Microsoft_Excel_30px.png"))); // NOI18N
        jLabel5.setText("Import Excel");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_Microsoft_Excel_30px.png"))); // NOI18N
        jLabel6.setText("Export Excel");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_Print_30px.png"))); // NOI18N
        jLabel7.setText("Print");

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jSeparator4.setOrientation(javax.swing.SwingConstants.VERTICAL);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 660, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(186, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jSeparator1)
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jSeparator2)
            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jSeparator3)
            .addComponent(jSeparator4)
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        buss.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        buss.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select One Project" }));

        jour.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jour.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Journals", "BL Controls", "EB-Approuve" }));
        jour.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                jourPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        jour.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jourMouseClicked(evt);
            }
        });

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Task"
            }
        ));
        jTable3.setRowHeight(24);
        jTable3.setShowHorizontalLines(false);
        jTable3.setShowVerticalLines(false);
        jTable3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable3MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable3);

        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Task"
            }
        ));
        jTable4.setRowHeight(24);
        jTable4.setShowHorizontalLines(false);
        jTable4.setShowVerticalLines(false);
        jTable4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable4MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTable4);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buss, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jour, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(buss, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jour, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 307, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Task"
            }
        ));
        jTable1.setRowHeight(24);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Debit :");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Credit :");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Sold :");

        jTextField4.setEditable(false);
        jTextField4.setBackground(new java.awt.Color(240, 240, 241));
        jTextField4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTextField4.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField4.setText("0.00");

        jTextField5.setEditable(false);
        jTextField5.setBackground(new java.awt.Color(240, 240, 241));
        jTextField5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTextField5.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField5.setText("0.00");

        jTextField7.setEditable(false);
        jTextField7.setBackground(new java.awt.Color(240, 240, 241));
        jTextField7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTextField7.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField7.setText("0.00");

        jTable5.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Task"
            }
        ));
        jTable5.setRowHeight(24);
        jTable5.setShowHorizontalLines(false);
        jTable5.setShowVerticalLines(false);
        jTable5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable5MouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(jTable5);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 656, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(28, 28, 28))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jourMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jourMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jourMouseClicked

    private void jourPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_jourPopupMenuWillBecomeInvisible
attCall_IN_LIST_serach();        // TODO add your handling code here:
    }//GEN-LAST:event_jourPopupMenuWillBecomeInvisible

    private void jTable3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable3MouseClicked
clicktable1();   //   Select_TableCODE();       // TODO add your handling code here:
    }//GEN-LAST:event_jTable3MouseClicked

    private void jTable4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable4MouseClicked
clicktable2();        // TODO add your handling code here:
    }//GEN-LAST:event_jTable4MouseClicked

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable1MouseClicked

    private void jTable5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable5MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable5MouseClicked

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
            java.util.logging.Logger.getLogger(onlineaccess.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(onlineaccess.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(onlineaccess.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(onlineaccess.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                       WebLookAndFeel.install(true);
                new onlineaccess().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> buss;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    public static final javax.swing.JTable jTable1 = new javax.swing.JTable();
    public static final javax.swing.JTable jTable3 = new javax.swing.JTable();
    public static final javax.swing.JTable jTable4 = new javax.swing.JTable();
    public static final javax.swing.JTable jTable5 = new javax.swing.JTable();
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JComboBox<String> jour;
    // End of variables declaration//GEN-END:variables
}
