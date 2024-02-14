/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package archivages;


import pv_compilation_offre.*;
import java.awt.HeadlessException;
import java.io.File;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Dosh
 */
public class newarchivage extends javax.swing.JPanel {

  Connection con=null;
PreparedStatement pst=null;
ResultSet rs= null;
String rolls;
DefaultTableModel mode;
    public newarchivage() {
        initComponents();
        con=JavaDbConnect.dbConnect();
      // CALL_CAT();
    }
    
     public void CALL_CAT()
    {
       
        
         
        try{
             // Class.forName("org.sqlite.JDBC");
            String sql="SELECT * FROM   LETTRE_CAT ";
          //  con = DriverManager.getConnection(url);
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                String sum=rs.getString("NAME");
              cat.addItem(sum);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
    
    }
    
    


   public void rolls(){
        SimpleDateFormat dateFormatsS = new SimpleDateFormat("yyyy-MMM");
         String addDateS = dateFormatsS.format(dates.getDate());
           SimpleDateFormat dateFormatsSS = new SimpleDateFormat("dd");
         String jr = dateFormatsSS.format(dates.getDate());
          try{
         
        // String sql="SELECT NUM FROM lettre where status='Letter In'   ORDER BY NUM DESC LIMIT 1";
          String sql="SELECT NUM FROM lettre  ORDER BY NUM DESC LIMIT 1";
         
             pst=con.prepareStatement(sql);
             rs=pst.executeQuery();
             if(rs.next()){
                 String rl=rs.getString("NUM");
                 int ln=rl.length();
                 String stxt=rl.substring(0,10);
                 String snum=rl.substring(10,ln);
                 int n = Integer.parseInt(snum);
                 n++;
                 snum=Integer.toString(n);
                 rolls=stxt+snum;
             }else{
               rolls= "ARCHI-DOC-001";
               
                
             }
         }catch(NumberFormatException | SQLException e){
             JOptionPane.showMessageDialog(null, e);  
         }
         }
   
      String paths1=null;
       public void CALL_PATHS()
    {
       
        
         
        try{
             // Class.forName("org.sqlite.JDBC");
            String sql="SELECT PATHS FROM   path_docs where ID=2";
          //  con = DriverManager.getConnection(url);
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                //String sum=rs.getString("nom");
               paths1=rs.getString("PATHS");
                //buss.addItem(sums);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
    
    }
        public void upload(){
      rolls();
      CALL_PATHS();
          File sourceFile = new File (paths.getText());
File destinationFile = new File (paths1+rolls+".pdf");
try{
  Files.copy(sourceFile.toPath(),destinationFile.toPath());
 // sourceFile.delete();
}catch(Exception ex ){
         JOptionPane.showMessageDialog(null,ex);
}
      
      }

public void save(){
    rolls();
     // roll();
    CALL_PATHS();
    upload(); 
     try {
            
        PreparedStatement pst = con.prepareStatement("INSERT INTO lettre (`num`, `ref`, `dates`, `dest`, `exp`, `objet`, `cat`, `status`, `num_doc`, `path`) "
        +"value(?,?,?,?,?,?,?,?,?,?)");
        
        pst.setString(1,rolls);
         pst.setString(2,ref.getText());
        pst.setString(3,dates.getText());
         pst.setString(4,dest.getText());
        pst.setString(5,emet.getText());
         pst.setString(6,objet.getText());
        pst.setString(7,cat.getSelectedItem().toString());
         pst.setString(8,cat.getSelectedItem().toString());
         pst.setString(9,"");
         pst.setString(10,paths1+rolls+ext.getSelectedItem().toString());
       
         
          pst.executeUpdate();
        
                 JOptionPane.showMessageDialog(null,"Datas Saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
    
   
 refresh();
 


}

//public void Table()
//    {
//         try{
//           
//             String sql="SELECT exp,Objet FROM lettre where STATUS='Letter In'";
//     //  String sql="SELECT ID, `NUM_FACTURE`, `DATE_FACTURE`, `DESCRIPTION`, `DEBIT_QTY`, `DEBIT_PU`, `DEBIT_PT`, `DEBIT_TVA`, `CREDIT_QTY`, `CREDIT_PU`, `CREDIT_PT`, `CREDIT_TVA`, `DATES`, `NUM` FROM facture_fournisseur where compte='"+jComboBox6.getSelectedItem()+"'";
//       pst = con.prepareStatement(sql);
//      rs= pst.executeQuery();
//      
//       
//      courrier.jTable3.setModel(DbUtils.resultSetToTableModel(rs));
//       mode=new DefaultTableModel();
//       
//       
//        TableColumn col0=courrier.jTable3.getColumnModel().getColumn(0);
//        TableColumn col1=courrier.jTable3.getColumnModel().getColumn(1);
//      
//        col0.setPreferredWidth(50);
//       col1.setPreferredWidth(250);
//       
// }catch(SQLException ex ){
//     JOptionPane.showMessageDialog(null, ex);
//}
//    } 
//public void etdelete()
//    {
//         try{
//        String sql = "DELETE FROM lettre WHERE num='"+num.getText()+"' and status='Letter In'";
//        
//         pst = con.prepareStatement(sql);
//        // pst.setString(1,COMPTES);
//         pst.executeUpdate();
//
//     }catch(SQLException | HeadlessException ex ){
//         JOptionPane.showMessageDialog(null,ex);
//     } 
//         refresh();
//    }

public void refresh(){
ref.setText("");
dates.setText("");
dest.setText("");
emet.setText("");
objet.setText("");
paths.setText("");
cat.setSelectedItem("Select One Cat");
}

String imgPath=null; 
  public void broswer()
     {
        // this.setAlwaysOnTop(false);
          JFileChooser chooser = new JFileChooser();
       chooser.setCurrentDirectory(new File(System.getProperty("user.home")));
       FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Images","jpg","gif","png","*.pdf","pdf");
       chooser.addChoosableFileFilter(filter);
       int result = chooser.showSaveDialog(null);
       if(result == JFileChooser.APPROVE_OPTION ){
           File selectedFile = chooser.getSelectedFile();
           String path = selectedFile.getAbsolutePath();
           //images.setIcon(ResizeImage(path,null));
           imgPath = path;
           paths.setText(imgPath);
       }
       else if(result ==  JFileChooser.CANCEL_OPTION){
           System.out.println("No Fli Selected");
       }       
     } 



/*
public void broswers(){
  int indexs[]=home_Table.jTable2.getSelectedRows();
  for(int i=0; i < indexs.length;i++){
   String piece = (home_Table.jTable2.getModel().getValueAt(indexs[i],7). toString()); 
if(piece.equals("No")){
  JFileChooser chooser = new JFileChooser();
       chooser.setCurrentDirectory(new File(System.getProperty("user.home")));
       FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Images","jpg","gif","png","*.pdf","pdf");
       chooser.addChoosableFileFilter(filter);
       int result = chooser.showSaveDialog(null);
       if(result == JFileChooser.APPROVE_OPTION ){
           File selectedFile = chooser.getSelectedFile();
           String path = selectedFile.getAbsolutePath();
           imgPath = path;
        csave();
       }
       else if(result ==  JFileChooser.CANCEL_OPTION){
           System.out.println("No Fli Selected");
       }     
    
}else if(piece.equals("Yes")){
    
   csave();  
}}    
    
    
  
     }   
   public void roll()
     {
         String NUMS = null;
         int SUBSTRING = 0;
          try{
          String sql="SELECT * FROM  transaction_number WHERE PROJET='"+journal1.buss.getText()+"' and NAME='ARCHIVE'";
          
             
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
           if(rs.next()){
                //String sum=rs.getString("nom");
                NUMS=rs.getString("NUM");
                SUBSTRING=rs.getInt("SUBSTR");
              //  jLabel6.setText(NUMS);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
         
         
         
         try{
            String sql="SELECT NUM FROM archive WHERE PROJET='"+journal1.buss.getText()+"' ORDER BY NUM DESC LIMIT 1";
             pst=con.prepareStatement(sql);
             rs=pst.executeQuery();
             if(rs.next()){
                 String rl=rs.getString("NUM");
                 int ln=rl.length();
                 String stxt=rl.substring(0,SUBSTRING);
                 String snum=rl.substring(SUBSTRING,ln);
                 int n = Integer.parseInt(snum);
                 n++;
                 snum=Integer.toString(n);
                 rolls=stxt+snum;
                 
                 
                
             }else{
                 //rolls="FICHE/EB/2018/1";
                 rolls=NUMS;
             }
                 
             
         }catch(Exception e){
             JOptionPane.showMessageDialog(null, e); 
             
         }
     }
      String paths1=null;
      
       public void CALL_PATHS()
    {
       
        
         
        try{
             // Class.forName("org.sqlite.JDBC");
            String sql="SELECT PATHS FROM   path_docs where ID=1";
          //  con = DriverManager.getConnection(url);
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                //String sum=rs.getString("nom");
               paths1=rs.getString("PATHS");
                //buss.addItem(sums);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
    
    }
       
           public void upload(){
          roll();
      CALL_PATHS();
          File sourceFile = new File ( imgPath);//("C:\\Users\\DOSHE\\Documents\\Congo.docx"); 
        // File file = new File("C:\\Users\\Mayank\\Desktop\\1.txt");
File destinationFile = new File (paths1+rolls+".pdf");
//file.d;
try{
  Files.copy(sourceFile.toPath(),destinationFile.toPath());
  sourceFile.delete();
}catch(Exception ex ){
         JOptionPane.showMessageDialog(null,ex);
}
      
      }
   
public void attache(){
  JFileChooser chooser = new JFileChooser();
       chooser.setCurrentDirectory(new File(System.getProperty("user.home")));
       FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Images","jpg","gif","png","*.pdf","pdf");
       chooser.addChoosableFileFilter(filter);
       int result = chooser.showSaveDialog(null);
       if(result == JFileChooser.APPROVE_OPTION ){
           File selectedFile = chooser.getSelectedFile();
           String path = selectedFile.getAbsolutePath();
           imgPath = path;
       roll();
    CALL_PATHS();
    upload(); 
    int indexs[]=home_Table.jTable2.getSelectedRows();
     for(int i=0; i < indexs.length;i++){
   String REF = (home_Table.jTable2.getModel().getValueAt(indexs[i],0). toString());
   String OBJET = (home_Table.jTable2.getModel().getValueAt(indexs[i],2). toString());
          try {
        PreparedStatement pst = con.prepareStatement("INSERT INTO `archive`(`NAME`, `NUMBRE`, `PAR`, `A`, `DATER`, `DATEC`, `OBJET`, `LINK`, `BATCH`, `PHYSQUE`, `NUM`,REF,PROJET)"
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?)");
        
        pst.setString(1,"-");
        pst.setString(2, "-");
         pst.setString(3,"-");
         pst.setString(4,"-");
         pst.setString(5,"-");
         pst.setString(6,"-");
         pst.setString(7,OBJET);
         pst.setString(9,"-");
         pst.setString(8,paths1+rolls+".pdf");
         pst.setString(10,"-");
         pst.setString(11,rolls);
          pst.setString(12,REF);
          pst.setString(13,journal1.buss.getText());
 pst.executeUpdate();
             
    } catch (Exception ex) {JOptionPane.showMessageDialog(null, ex.getMessage()); }
    
     try {
        PreparedStatement pst = con.prepareStatement("UPDATE `ohada_trans` SET `ARCHIVE`='Yes' where NUM='"+REF+"' and BUSS='"+journal1.buss.getText()+"'");
     pst.executeUpdate();
   } catch (Exception ex) { JOptionPane.showMessageDialog(null, ex.getMessage());  }
     }
       }
       else if(result ==  JFileChooser.CANCEL_OPTION){
           System.out.println("No Fli Selected");
       }     

}   
  /*
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        ext = new javax.swing.JComboBox<>();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel3.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jPanel3MouseDragged(evt);
            }
        });
        jPanel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel3MousePressed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/cancel.png"))); // NOI18N
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jButton1.setText("Save");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_Attach_24px.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("Date d'emission");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("Fournisseur/client");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("No. Reference");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("Categorie ou classement");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("Objet");

        dates.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        dates.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        dates.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                datesActionPerformed(evt);
            }
        });

        emet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emetActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setText("Concepteur");

        cat.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select On Cat", "Dossier de personnel", "DAO", "PRODOC", "DOC Administratifs", "Rapports Annuel", "Lettres entrant", "Lettres sortant", "Contrats fournisseur", "Factures et docs achat", "Autres" }));

        paths.setEditable(false);
        paths.setBackground(new java.awt.Color(240, 240, 241));
        paths.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        paths.setForeground(new java.awt.Color(255, 51, 51));

        jButton3.setText("Del");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        ext.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { ".pfd", ".doc", ".docx.", ".jpeg", ".png", ".rtf", ".html" }));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dates, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel9)
                                        .addGap(246, 246, 246))
                                    .addComponent(dest)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(90, 90, 90)
                                .addComponent(jLabel6)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addComponent(objet)
                    .addComponent(cat, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(paths, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(ext, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel7)
                                .addComponent(jLabel8)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addComponent(emet, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(ref, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGap(0, 0, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dest, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dates, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(emet, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ref, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addGap(2, 2, 2)
                .addComponent(cat, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(objet, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(paths, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ext)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(jButton3))))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
this.setVisible(false); 
//courrier.title.setText("Title");
//refresh();
//DefaultTableModel model = new DefaultTableModel();
//courrier.jTable3.setModel(model);// TODO add your handling code here:
    }//GEN-LAST:event_jLabel3MouseClicked

    private void jPanel3MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel3MouseDragged
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel3MouseDragged

    private void jPanel3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel3MousePressed
     // TODO add your handling code here:
    }//GEN-LAST:event_jPanel3MousePressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
if(dest.getText().equals("")||dates.getText().equals("")||emet.getText().equals("")||ref.getText().equals("")||objet.getText().equals("")||paths.getText().equals("")||cat.getSelectedItem().equals("Select On Cat")){
JOptionPane.showMessageDialog(null,"Wrong Data","Error",JOptionPane.ERROR_MESSAGE);
}else{//roll();
save();}
// achivage m = new achivage();
// m.call();// TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
broswer();// TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void datesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_datesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_datesActionPerformed

    private void emetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emetActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_emetActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
//etdelete(); 
//Table();// TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static final javax.swing.JComboBox<String> cat = new javax.swing.JComboBox<>();
    public static final com.alee.extended.date.WebDateField dates = new com.alee.extended.date.WebDateField();
    public static final javax.swing.JTextField dest = new javax.swing.JTextField();
    public static final javax.swing.JTextField emet = new javax.swing.JTextField();
    private javax.swing.JComboBox<String> ext;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    public static final javax.swing.JTextField objet = new javax.swing.JTextField();
    public static final javax.swing.JTextField paths = new javax.swing.JTextField();
    public static final javax.swing.JTextField ref = new javax.swing.JTextField();
    // End of variables declaration//GEN-END:variables
}
