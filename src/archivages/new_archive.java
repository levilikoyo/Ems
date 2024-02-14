/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package archivages;

import java.io.File;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import pv_compilation_offre.JavaDbConnect;

/**
 *
 * @author Doshe PC
 */
public class new_archive extends javax.swing.JPanel {
Connection con=null;
PreparedStatement pst=null;
ResultSet rs= null;
String rolls,roll;
    public new_archive() {
        initComponents();
           con=JavaDbConnect.dbConnect();
    }
public void rolls(){
        SimpleDateFormat dateFormatsS = new SimpleDateFormat("yyyy-MMM");
         String addDateS = dateFormatsS.format(dates.getDate());
           SimpleDateFormat dateFormatsSS = new SimpleDateFormat("dd");
         String jr = dateFormatsSS.format(dates.getDate());
          try{
         
        // String sql="SELECT NUM FROM lettre where status='Letter In'   ORDER BY NUM DESC LIMIT 1";
          String sql="SELECT NUM FROM lettre   ORDER BY NUM DESC LIMIT 1";
         //ARCHI-DOC-2023-Nov-30001
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
               //ARCHI-DOC-2023-Nov-23001
                
             }
         }catch(NumberFormatException | SQLException e){
             JOptionPane.showMessageDialog(null, e);  
         }
         }
    public void roll()
     {
         String NUMS = null;
         int SUBSTRING = 0,UPSTRING = 0,SUPUPSTRING = 0;
          try{
          String sql="SELECT * FROM  transaction_number WHERE PROJET='LETTER' and NAME='LETTER-IN'";
          
             
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
           if(rs.next()){
                //String sum=rs.getString("nom");
                NUMS=rs.getString("NUM");
                SUBSTRING=rs.getInt("SUBSTR");
                UPSTRING=rs.getInt("UPSTRG");
                SUPUPSTRING=rs.getInt("SUBUPSTR");
              //  jLabel6.setText(NUMS);
            }//N/Réf :151/AFPDE/SK-UV/2022
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
         
         
         
         try{
                // Class.forName("org.sqlite.JDBC");
          // con= DriverManager.getConnection();
            String sql="SELECT NUM_DOC FROM LETTRE ORDER BY NUM_DOC DESC LIMIT 1";
             pst=con.prepareStatement(sql);
             rs=pst.executeQuery();
             if(rs.next()){
                 String rl=rs.getString("NUM_DOC");
                 
                 String p1=rl.substring(0,UPSTRING);//--> N/Réf :1001
                 String p2=rl.substring(UPSTRING,SUBSTRING);//--> /AFPDE/SK-UV/2022
                 
                 
                 int lns=p1.length();//--> 11
                 String stxt=p1.substring(0,SUPUPSTRING);
                 String snum=p1.substring(SUPUPSTRING,lns);
                 int n = Integer.parseInt(snum);
                 n++;
                 snum=Integer.toString(n);
                 roll=stxt+snum+p2;
  
                
             }else{
                 roll=NUMS;
             }
                 
             
         }catch(Exception e){
             JOptionPane.showMessageDialog(null, e); 
             
         }
     }
      String pathsd=null;
       public void CALL_PATHS()
    {
       
        
         
        try{
            String sql="SELECT PATHS FROM   path_docs where ID=2";
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
               pathsd=rs.getString("PATHS");
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
    
    }
        public void upload(){
         // roll();
      CALL_PATHS();
          File sourceFile = new File (paths.getText());
          JOptionPane.showMessageDialog(null,pathsd);
File destinationFile = new File (pathsd+rolls+ext.getSelectedItem());
try{
  Files.copy(sourceFile.toPath(),destinationFile.toPath());
  //sourceFile.delete();
}catch(Exception ex ){
         JOptionPane.showMessageDialog(null,ex);
}
      
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
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        emet = new Palette.MyTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        nom1 = new Palette.MyTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        objet = new Palette.MyTextField();
        paths = new Palette.MyTextField();
        jLabel1 = new javax.swing.JLabel();
        ext = new javax.swing.JComboBox<>();
        jLabel20 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        ref = new Palette.MyTextField();
        jLabel21 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jLabel18.setText("Store/ Archive *");

        depot.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "- Store/Archive -" }));
        depot.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        depot.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                depotPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        depot.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                depotActionPerformed(evt);
            }
        });

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8-plus-24.png"))); // NOI18N
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel8MouseClicked(evt);
            }
        });

        jLabel13.setText("Rank/Rangé *");

        ranges.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "- Rank/Rangé -" }));
        ranges.setToolTipText("");
        ranges.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        ranges.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                rangesPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        ranges.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rangesActionPerformed(evt);
            }
        });

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8-plus-24.png"))); // NOI18N
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });

        jLabel14.setText("Shelves/ Etagères *");

        etagere.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "- Shelves/ Etagères -" }));
        etagere.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        etagere.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                etagerePopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8-plus-24.png"))); // NOI18N
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });

        jLabel15.setText("Line/ Ligne *");

        ligne.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "- Line/ Ligne -" }));
        ligne.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        ligne.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                lignePopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8-plus-24.png"))); // NOI18N
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });

        jLabel16.setText("Bin location *");

        bin.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "- Bin location -" }));
        bin.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        bin.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                binPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8-plus-24.png"))); // NOI18N
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });

        jLabel17.setText("Folder/ Classeur *");

        profondeur.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "- Folder/ Classeur -" }));
        profondeur.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8-plus-24.png"))); // NOI18N
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
        });
        jLabel7.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jLabel7KeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(profondeur, 0, 185, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(ligne, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(depot, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(ranges, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(etagere, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(bin, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(depot)
                    .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ranges, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(etagere, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ligne)
                    .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bin, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(profondeur, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        emet.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel10.setText("Concepteur *");

        jLabel11.setText("Conception Date *");

        nom1.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel12.setText("Destinateur *");

        jLabel19.setText("Object/Objet *");

        objet.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        paths.setEditable(false);
        paths.setBackground(new java.awt.Color(242, 242, 241));
        paths.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        paths.setFont(new java.awt.Font("Segoe UI", 0, 1)); // NOI18N

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_Attach_24px.png"))); // NOI18N
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        ext.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { ".pfd", ".doc", ".docx.", ".jpeg", ".png", ".rtf", ".html" }));
        ext.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3));

        jLabel20.setText("Attachet/Attache *");

        jButton1.setText("Ok");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        ref.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel21.setText("Reference No *");

        dates.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 204), 3));
        dates.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        dates.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        dates.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                datesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(emet, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(objet, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jButton1))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(paths, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ext, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ref, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nom1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dates, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ref, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(emet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(dates, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nom1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(objet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ext)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(paths, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap(7, Short.MAX_VALUE))
        );

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_cancel_24px_1.png"))); // NOI18N
        jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void rangesPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_rangesPopupMenuWillBecomeInvisible
        try{
            String sql="SELECT * FROM stock_bin_location where  cat='etagere' and depot='"+depot.getSelectedItem()+"' and ranges='"+ranges.getSelectedItem()+"' order by Nom";
            pst = con.prepareStatement(sql);rs=pst.executeQuery();
            etagere.removeAllItems();
            etagere.addItem("- Shelves/ Etagères -");
            while(rs.next()){
                String sums=rs.getString("Nom");

                etagere.addItem(sums);
            }
        }catch(Exception ex){JOptionPane.showMessageDialog(null, ex); }        // TODO add your handling code here:
    }//GEN-LAST:event_rangesPopupMenuWillBecomeInvisible

    private void rangesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rangesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rangesActionPerformed

    private void etagerePopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_etagerePopupMenuWillBecomeInvisible
        try{
            String sql="SELECT * FROM stock_bin_location where cat='ligne' and depot='"+depot.getSelectedItem()+"' and ranges='"+ranges.getSelectedItem()+"' and etagere='"+etagere.getSelectedItem()+"' order by Nom";
            pst = con.prepareStatement(sql);rs=pst.executeQuery();
            ligne.removeAllItems();
            ligne.addItem("- Line/ Ligne -");
            while(rs.next()){
                String sums=rs.getString("Nom");

                ligne.addItem(sums);
            }
        }catch(Exception ex){JOptionPane.showMessageDialog(null, ex); }
        // TODO add your handling code here:
    }//GEN-LAST:event_etagerePopupMenuWillBecomeInvisible

    private void lignePopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_lignePopupMenuWillBecomeInvisible
        try{
            String sql="SELECT * FROM stock_bin_location where cat='bin' and depot='"+depot.getSelectedItem()+"' and ranges='"+ranges.getSelectedItem()+"' and etagere='"+etagere.getSelectedItem()+"' and ligne='"+ligne.getSelectedItem()+"' order by Nom";
            pst = con.prepareStatement(sql);rs=pst.executeQuery();
            bin.removeAllItems();
            bin.addItem("- Bin location -");
            while(rs.next()){
                String sums=rs.getString("Nom");

                bin.addItem(sums);
            }
        }catch(Exception ex){JOptionPane.showMessageDialog(null, ex); }        // TODO add your handling code here:
    }//GEN-LAST:event_lignePopupMenuWillBecomeInvisible

    private void binPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_binPopupMenuWillBecomeInvisible
        try{
            String sql="SELECT * FROM stock_bin_location where cat='profondeur' and depot='"+depot.getSelectedItem()+"' and ranges='"+ranges.getSelectedItem()+"' and etagere='"+etagere.getSelectedItem()+"' and ligne='"+ligne.getSelectedItem()+"' and bin='"+bin.getSelectedItem()+"' order by Nom";
            pst = con.prepareStatement(sql);rs=pst.executeQuery();
            profondeur.removeAllItems();
            profondeur.addItem("- Deep/ Profondeur -");
            while(rs.next()){
                String sums=rs.getString("Nom");

                profondeur.addItem(sums);
            }
        }catch(Exception ex){JOptionPane.showMessageDialog(null, ex); }        // TODO add your handling code here:
    }//GEN-LAST:event_binPopupMenuWillBecomeInvisible

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        add_on_stock_bins m = new add_on_stock_bins();
        add_on_stock_bins.jLabel1.setText("ranges");
        try{
            PreparedStatement ps = con.prepareStatement("Select * FROM stock_bin_location where cat='ranges' and depot='"+depot.getSelectedItem()+"'order by Nom");
            ResultSet rs=ps.executeQuery();
            DefaultTableModel tm = (DefaultTableModel)add_on_stock_bins.jTable1.getModel();
            tm.setRowCount(0);

            while(rs.next()){//``, ``, ``, `                                                                                    `, ``, ``, ``, ``, ``, `DATEFABRI`, ``, `DATE`
                Object o[] = {rs.getString("NOM")};
                tm.addRow(o);

            }

        }catch(Exception ex){JOptionPane.showMessageDialog(null, ex); }
        m.show();
        m. setDefaultCloseOperation(DISPOSE_ON_CLOSE);        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel3MouseClicked

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        add_on_stock_bins m = new add_on_stock_bins();
        add_on_stock_bins.jLabel1.setText("etagere");
        try{
            PreparedStatement ps = con.prepareStatement("Select * FROM stock_bin_location where cat='etagere' and depot='"+depot.getSelectedItem()+"' and ranges='"+ranges.getSelectedItem()+"' order by Nom");
            //PreparedStatement ps = con.prepareStatement("Select * FROM stock_bin_location where  ranges='"+ranges.getSelectedItem()+"' order by Nom");
            ResultSet rs=ps.executeQuery();
            DefaultTableModel tm = (DefaultTableModel)add_on_stock_bins.jTable1.getModel();
            tm.setRowCount(0);

            while(rs.next()){//``, ``, ``, `                                                                                    `, ``, ``, ``, ``, ``, `DATEFABRI`, ``, `DATE`
                Object o[] = {rs.getString("NOM")};
                tm.addRow(o);

            }

        }catch(Exception ex){JOptionPane.showMessageDialog(null, ex); }
        m.show();
        m. setDefaultCloseOperation(DISPOSE_ON_CLOSE);        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel4MouseClicked

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        add_on_stock_bins m = new add_on_stock_bins();
        add_on_stock_bins.jLabel1.setText("ligne");
        try{
            PreparedStatement ps = con.prepareStatement("Select * FROM stock_bin_location where cat='ligne' and depot='"+depot.getSelectedItem()+"' and ranges='"+ranges.getSelectedItem()+"' and etagere='"+etagere.getSelectedItem()+"' order by Nom");
            ResultSet rs=ps.executeQuery();
            DefaultTableModel tm = (DefaultTableModel)add_on_stock_bins.jTable1.getModel();
            tm.setRowCount(0);

            while(rs.next()){//``, ``, ``, `                                                                                    `, ``, ``, ``, ``, ``, `DATEFABRI`, ``, `DATE`
                Object o[] = {rs.getString("NOM")};
                tm.addRow(o);

            }

        }catch(Exception ex){JOptionPane.showMessageDialog(null, ex); }
        m.show();
        m. setDefaultCloseOperation(DISPOSE_ON_CLOSE);         // TODO add your handling code here:
    }//GEN-LAST:event_jLabel5MouseClicked

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        add_on_stock_bins m = new add_on_stock_bins();
        add_on_stock_bins.jLabel1.setText("bin");
        try{
            PreparedStatement ps = con.prepareStatement("Select * FROM stock_bin_location where cat='bin' and depot='"+depot.getSelectedItem()+"' and ranges='"+ranges.getSelectedItem()+"' and etagere='"+etagere.getSelectedItem()+"' and ligne='"+ligne.getSelectedItem()+"' order by Nom");
            ResultSet rs=ps.executeQuery();
            DefaultTableModel tm = (DefaultTableModel)add_on_stock_bins.jTable1.getModel();
            tm.setRowCount(0);

            while(rs.next()){//``, ``, ``, `                                                                                    `, ``, ``, ``, ``, ``, `DATEFABRI`, ``, `DATE`
                Object o[] = {rs.getString("NOM")};
                tm.addRow(o);

            }

        }catch(Exception ex){JOptionPane.showMessageDialog(null, ex); }
        m.show();
        m. setDefaultCloseOperation(DISPOSE_ON_CLOSE);         // TODO add your handling code here:
    }//GEN-LAST:event_jLabel6MouseClicked

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
        add_on_stock_bins m = new add_on_stock_bins();
        add_on_stock_bins.jLabel1.setText("Classeur");
        try{
            PreparedStatement ps = con.prepareStatement("Select * FROM stock_bin_location where cat='Classeur' and depot='"+depot.getSelectedItem()+"' and ranges='"+ranges.getSelectedItem()+"' and etagere='"+etagere.getSelectedItem()+"' and ligne='"+ligne.getSelectedItem()+"' and bin='"+bin.getSelectedItem()+"' order by Nom");
            ResultSet rs=ps.executeQuery();
            DefaultTableModel tm = (DefaultTableModel)add_on_stock_bins.jTable1.getModel();
            tm.setRowCount(0);

            while(rs.next()){//``, ``, ``, `                                                                                    `, ``, ``, ``, ``, ``, `DATEFABRI`, ``, `DATE`
                Object o[] = {rs.getString("NOM")};
                tm.addRow(o);

            }

        }catch(Exception ex){JOptionPane.showMessageDialog(null, ex); }
        m.show();
        m. setDefaultCloseOperation(DISPOSE_ON_CLOSE);         // TODO add your handling code here:
    }//GEN-LAST:event_jLabel7MouseClicked

    private void jLabel7KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jLabel7KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel7KeyPressed

    private void depotPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_depotPopupMenuWillBecomeInvisible
        try{
            String sql="SELECT * FROM stock_bin_location where  cat='ranges' and depot='"+depot.getSelectedItem()+"' order by Nom";
            pst = con.prepareStatement(sql);rs=pst.executeQuery();
            ranges.removeAllItems();
            ranges.addItem("- Rank/Rangé -");
            while(rs.next()){
                String sums=rs.getString("NOM");

                ranges.addItem(sums);
            }
        }catch(Exception ex){JOptionPane.showMessageDialog(null, ex); }
        // TODO add your handling code here:
    }//GEN-LAST:event_depotPopupMenuWillBecomeInvisible

    private void depotActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_depotActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_depotActionPerformed

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked
        add_on_stock_bins m = new add_on_stock_bins();
        add_on_stock_bins.jLabel1.setText("Store/ Archive");
        try{
            PreparedStatement ps = con.prepareStatement("Select * FROM stock_bin_location where cat='Store/Archive' order by Nom");
            ResultSet rs=ps.executeQuery();
            DefaultTableModel tm = (DefaultTableModel)add_on_stock_bins.jTable1.getModel();
            tm.setRowCount(0);

            while(rs.next()){//``, ``, ``, `                                                                                    `, ``, ``, ``, ``, ``, `DATEFABRI`, ``, `DATE`
                Object o[] = {rs.getString("NOM")};
                tm.addRow(o);

            }

        }catch(Exception ex){JOptionPane.showMessageDialog(null, ex); }
        m.show();
        m. setDefaultCloseOperation(DISPOSE_ON_CLOSE);         // TODO add your handling code here:
    }//GEN-LAST:event_jLabel8MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

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
         pst.setString(4,dates.getText());
        pst.setString(5,emet.getText());
         pst.setString(6,objet.getText());
       pst.setString(7,jLabel2.getText());
         pst.setString(8,"");
         pst.setString(9,"");
         pst.setString(10,pathsd+rolls+ext.getSelectedItem().toString());
       
         
          pst.executeUpdate();
        
                 JOptionPane.showMessageDialog(null,"Datas Saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
    
   
 //refresh();
 


        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
broswer();        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel1MouseClicked

    private void datesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_datesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_datesActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static final javax.swing.JComboBox<String> bin = new javax.swing.JComboBox<>();
    public static final com.alee.extended.date.WebDateField dates = new com.alee.extended.date.WebDateField();
    public static final javax.swing.JComboBox<String> depot = new javax.swing.JComboBox<>();
    private Palette.MyTextField emet;
    public static final javax.swing.JComboBox<String> etagere = new javax.swing.JComboBox<>();
    private javax.swing.JComboBox<String> ext;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    public static final javax.swing.JLabel jLabel2 = new javax.swing.JLabel();
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    public static final javax.swing.JComboBox<String> ligne = new javax.swing.JComboBox<>();
    private Palette.MyTextField nom1;
    private Palette.MyTextField objet;
    private Palette.MyTextField paths;
    public static final javax.swing.JComboBox<String> profondeur = new javax.swing.JComboBox<>();
    public static final javax.swing.JComboBox<String> ranges = new javax.swing.JComboBox<>();
    private Palette.MyTextField ref;
    // End of variables declaration//GEN-END:variables
}
