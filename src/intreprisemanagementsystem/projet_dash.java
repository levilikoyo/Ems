/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intreprisemanagementsystem;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
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
import net.sf.jasperreports.swing.JRViewer;
import net.sf.jasperreports.view.JasperViewer;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import static org.icepdf.ri.util.BareBonesBrowserLaunch.openFile;

/**
 *
 * @author Dosh
 */
public class projet_dash extends javax.swing.JInternalFrame {

    Connection con=null;
PreparedStatement pst=null;
ResultSet rs= null;
    public projet_dash() {
        initComponents();
        con=JavaDbConnect.dbConnect();
        sahowtable();
    }
 public void sahowtable(){
    
     try{
           
    String sqls="SELECT `PROJET_NUM`, `DATEIN`, `DATEOUT`, `STATUS`,QPP AS 'TVA(%)',`NUM` as 'ECART', `TETE` AS 'EN TETE' FROM `projet`";
    //`LIBELLE`, `ENTRE`, `SORTIE`, `DATE`, `BUSS`, `USED`, `NUM`
    pst = con.prepareStatement(sqls);
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
         jTable1.getColumnModel().getColumn(2).setCellRenderer(centre);
         TableColumn col3=jTable1.getColumnModel().getColumn(3);
         jTable1.getColumnModel().getColumn(3).setCellRenderer(centre);
         TableColumn col4=jTable1.getColumnModel().getColumn(4);
         jTable1.getColumnModel().getColumn(4).setCellRenderer(centre);
         TableColumn col5=jTable1.getColumnModel().getColumn(5);
         jTable1.getColumnModel().getColumn(5).setCellRenderer(centre);
         TableColumn col6=jTable1.getColumnModel().getColumn(6);
        
      
       
       col0.setPreferredWidth(200);
       col1.setPreferredWidth(50);
       col2.setPreferredWidth(50);
        col3.setPreferredWidth(50);
       col4.setPreferredWidth(20);
       col5.setPreferredWidth(20);
       col6.setPreferredWidth(200);
           }  
 
 
       public void saveS(){
  int rows=jTable1.getRowCount();
 String ecart = null,tete = null,num=null,qpp=null;
  for(int row = 0; row<rows; row++)
  { num = (String)jTable1.getValueAt(row, 0);  
    ecart = (String)jTable1.getValueAt(row, 5);
    tete = (String)jTable1.getValueAt(row, 6);
    qpp = (String)jTable1.getValueAt(row, 4);
   
          try{
    // SELECT `PROJET_NUM`, `DATEIN`, `DATEOUT`, `STATUS`,`NUM` as 'ECART', `TETE` AS 'EN TETE' FROM `projet`
         // java.sql.Connection con = getConnection();
     String sql=" UPDATE projet SET `NUM`=?,`TETE`=?,QPP=? where  PROJET_NUM='"+num+"'";
     pst=con.prepareStatement(sql);
    
      pst.setString(1,ecart);
       pst.setString(2,tete);
        pst.setString(3,qpp);
   
            pst.executeUpdate();
               
              
  //JOptionPane.showMessageDialog(null,"Transaction Saved");
        
  
     }catch(Exception ex){JOptionPane.showMessageDialog(null,ex);}
  }
  JOptionPane.showMessageDialog(null,"Tranction Saved");
  
  
  }
  public void report_EB()
     {
               try{
                   
                     String sqls="Select path from pathn";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
            if(rs.next()){
             
                String sum=rs.getString("path");
                  
                 String NameFiles=sum;
            
            String NameFile=""+NameFiles+"presentation.jrxml";
                 
                 
                 
                JasperDesign jd=JRXmlLoader.load(NameFile);
                
                
                 TableModel model1 =jTable1.getModel();
        int indexs[]=jTable1.getSelectedRows();
        Object[] row = new Object[1];
       
       String Sb=null;
        for(int i=0; i < indexs.length;i++){
             String lb = (jTable1.getModel().getValueAt(indexs[i],0). toString());
        
        row[0]= model1.getValueAt(indexs[i],0);
        //jEditorPane1.setText("");
       jEditorPane1.setText(jEditorPane1.getText()+"'"+lb+"',");
     // String arrays="'"+lb+"',";
      
    StringBuffer sb= new StringBuffer(jEditorPane1.getText());
    sb.deleteCharAt(sb.length()-1);
     Sb=sb.toString();
        
      
        } 
                
                
                
           String sql="Select PROJET.PROJET_NUM,PROJET,projet_bayeur.BAYEUR,DATEIN,DATEOUT,COUTS from PROJET INNER JOIN projet_bayeur ON projet.PROJET_NUM=projet_bayeur.PROJET_NUM WHERE projet.PROJET_NUM IN ("+Sb+")";
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
       public void reportss(){
       
          try{
                 
                 
                   String sqls="Select path from pathn";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
            if(rs.next()){
             
                String sum=rs.getString("path");
                  
                 String NameFiles=sum;
            
            String NameFile=""+NameFiles+"presentation.jrxml";
                 
                JasperDesign jd=JRXmlLoader.load(NameFile);
                
               
                 JasperReport jr =JasperCompileManager.compileReport(jd);
                 JasperPrint jp=JasperFillManager.fillReport(jr,null,con);
                 JasperViewer.viewReport(jp,false);
 
               // JRViewer m= new JRViewer(jp);
//     jPanel3.setLayout(new BorderLayout());
//     jPanel3.add(m);
            }
             }catch(Exception ex){
                  JOptionPane.showMessageDialog(null, ex);   
             }
       }
       
public void export(){
      try{
           JFileChooser jFileChooser = new JFileChooser();
           jFileChooser.showSaveDialog(this);
           File saveFile = jFileChooser.getSelectedFile();
           
           if(saveFile != null){
               saveFile = new File(saveFile.toString()+".xlsx");
               Workbook wb = new XSSFWorkbook();
               Sheet sheet = wb.createSheet("customer");
               
               Row rowCol = sheet.createRow(0);
               for(int i=0;i<jTable1.getColumnCount();i++){
                   Cell cell = rowCol.createCell(i);
                   cell.setCellValue(jTable1.getColumnName(i));
               }
               
               for(int j=0;j<jTable1.getRowCount();j++){
                   Row row = sheet.createRow(j+1);
                   for(int k=0;k<jTable1.getColumnCount();k++){
                       Cell cell = row.createCell(k);
                       if(jTable1.getValueAt(j, k)!=null){
                           cell.setCellValue(jTable1.getValueAt(j, k).toString());
                       }
                   }
               }
               FileOutputStream out = new FileOutputStream(new File(saveFile.toString()));
               wb.write(out);
            //   wb.close();
               out.close();
               openFile(saveFile.toString());
             //   JOptionPane.showMessageDialog(null,"Tranction Saved");
           }else{
               JOptionPane.showMessageDialog(null,"Error al generar archivo");
           }
       }catch(FileNotFoundException e){
           System.out.println(e);
       }catch(IOException io){
           System.out.println(io);
       }
 }       
       
          /*  public void reports()
     {
      if(buss.getSelectedItem().equals("Select All Project")){
      
             try{
                 
                 
                   String sqls="Select path from pathn";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
            if(rs.next()){
             
                String sum=rs.getString("path");
                  
                 String NameFiles=sum;
            
            String NameFile=""+NameFiles+"presentation.jrxml";
                 
                   jPanel3.removeAll();
     jPanel3.repaint();
     jPanel3.revalidate();
                 
                JasperDesign jd=JRXmlLoader.load(NameFile);
                
               
                 JasperReport jr =JasperCompileManager.compileReport(jd);
                 JasperPrint jp=JasperFillManager.fillReport(jr,null,con);
                // JasperViewer.viewReport(jp,false);
 
                JRViewer m= new JRViewer(jp);
     jPanel3.setLayout(new BorderLayout());
     jPanel3.add(m);
            }
             }catch(Exception ex){
                  JOptionPane.showMessageDialog(null, ex);   
             }

      }else if(buss.getSelectedItem().equals("Select One Project")){
      
      
      }else{
      
      
             try{
                 
                 
                   String sqls="Select path from pathn";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
            if(rs.next()){
             
                String sum=rs.getString("path");
                  
                 String NameFiles=sum;
            
            String NameFile=""+NameFiles+"presentation.jrxml";
                 
                 
                 
                JasperDesign jd=JRXmlLoader.load(NameFile);
                 
               //JasperDesign jd=JRXmlLoader.load("C:\\Users\\Dosh\\Documents\\NetBeansProjects\\intreprisemanagementsystem\\src\\intreprisemanagementsystem\\journaldecaise.jrxml");
              String sql="Select PROJET.PROJET_NUM,	PROJET,projet_bayeur.BAYEUR,DATEIN,DATEOUT,COUTS from PROJET INNER JOIN projet_bayeur ON projet.PROJET_NUM=projet_bayeur.PROJET_NUM WHERE PROJET.PROJET_NUM ='"+buss.getSelectedItem()+"'";
             // String sql="Select * from OHADA_TRANS INNER JOIN projet ON OHADA_TRANS.BUSS=projet.PROJET_NUM where buss='"+tmp+" and code_m=57'";
    
                jPanel3.removeAll();
     jPanel3.repaint();
     jPanel3.revalidate();
                JRDesignQuery nq=new JRDesignQuery();
                nq.setText(sql);
                jd.setQuery(nq);
      JasperReport jr =JasperCompileManager.compileReport(jd);
      JasperPrint jp=JasperFillManager.fillReport(jr,null,con);
     // JasperViewer.viewReport(jp,false);
    // JasperViewer m= new JasperViewer(jp);
    JRViewer m= new JRViewer(jp);
     jPanel3.setLayout(new BorderLayout());
     jPanel3.add(m);
            }
                 
             }catch(Exception ex){
                  JOptionPane.showMessageDialog(null, ex);   
             }
     
           
      }
          
     
     } 
       */
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        jEditorPane1 = new javax.swing.JEditorPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();

        setBackground(new java.awt.Color(255, 255, 255));
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_Bulleted_List_16px.png"))); // NOI18N

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTable1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Currency", "Sale", "Buy"
            }
        ));
        jTable1.setFocusable(false);
        jTable1.setRowHeight(30);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jTable1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTable1KeyReleased(evt);
            }
        });
        jScrollPane4.setViewportView(jTable1);

        jEditorPane1.setEditable(false);
        jEditorPane1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jScrollPane1.setViewportView(jEditorPane1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 1019, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 379, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE))
        );

        jMenu1.setText("X");
        jMenu1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jMenu1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu1MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu1);

        jMenu2.setText("File");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem1.setText("Save");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem1);

        jMenuItem2.setText("Print");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem2);

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

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenu1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu1MouseClicked
this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu1MouseClicked

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable1MouseClicked

    private void jTable1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyReleased
        //update();
        //sahowtable();// TODO add your handling code here:
    }//GEN-LAST:event_jTable1KeyReleased

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
saveS();        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
//reportss();
report_EB();
jEditorPane1.setText("");// TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JEditorPane jEditorPane1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
