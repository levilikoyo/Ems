/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intreprisemanagementsystem;

import com.alee.laf.WebLookAndFeel;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Dosh
 */
public class mouvement_du_personnel extends javax.swing.JFrame {

   Connection con=null;
PreparedStatement pst=null;
ResultSet rs= null;
String rolls;
String etrolls;
String salaryrolls;
DefaultTableModel mode;
 Timer timer;
    public mouvement_du_personnel() {
        initComponents();
        con=JavaDbConnect.dbConnect();
         this.setExtendedState(JFrame.MAXIMIZED_BOTH);
     
            setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("icons8_Raspberry_Pi_48px_1.png")));
        photophatom();
        SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd-MM-yyyy");
       String dateAfterString = dateFormat1.format(new Date());
        datesss.setText(dateAfterString);
             date1.setDate(new Date());
              date2.setDate(new Date());
              times();
              call();
    }
        public void call(){
    // DefaultListModel list = new DefaultListModel();
         try{
            String sql="SELECT * FROM `register`  order by NAME";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                //String sum=rs.getString("nom");
                String sums=rs.getString("NAME");
             nom_auto.addItem(sums);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
    
    }
    
    
    public void times(){
         ActionListener actionListener = new  ActionListener(){
             @Override
             public void actionPerformed(ActionEvent e) {
                 Date date = new Date();
         DateFormat timeFormat = new SimpleDateFormat("H:mm:ss");
         String time = timeFormat.format(date);
         
         DateFormat timeFormat1 = new SimpleDateFormat("H");
         String time1 = timeFormat1.format(date);
        // Double a = Double.parseDouble(time1);
         Integer a = Integer.parseInt(time1);
        
        if(a>=12){
          heure.setText(time +"  PM");
         }else{
          heure.setText(time +"  AM");
         }
        
             }
             
         };
         
         timer = new Timer(1000,actionListener); 
timer.setInitialDelay(0);
//jButton1.setEnabled(false);
timer.start();//To change body of generated methods, choose Tools | Templates.
             }
     public ImageIcon ResizeImage(String ImagePath, byte[] pic)
    {
        ImageIcon MyImage = null;
        if(ImagePath !=null)
        {
            MyImage = new ImageIcon(ImagePath);
        }else
        {
            MyImage = new ImageIcon (ImagePath);
        }
        Image img =MyImage.getImage();
        Image newImg= img.getScaledInstance(images.getWidth(),images.getHeight(),Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(newImg);
        return image;
    }
String imgPath= "D:\\logos\\tetes.JPG"; 
     public void photophatom()     
     {
       images.setIcon(ResizeImage(imgPath,null));
     }
     
     
     public void attCall_roll()
    {
      
         String A =JOptionPane.showInputDialog("Please Entre Your Roll Number!!!"); 
        try{
            String sql="select * from employee where ROLLNUM= ? and ACTIVE='Active'";
            
           
             pst=con.prepareStatement(sql);
            pst.setString(1, A);
            rs=pst.executeQuery();
            if(rs.next()){
             String add1 = rs.getString("NAME");
             String add2 = rs.getString("LNAME");
              nom.setText(add1);
              lnom.setText(add2);
             
             String adde = rs.getString("ROLLNUM");
             num.setText(adde);
              String add11 = rs.getString("TITRE");
           fonction.setText(add11);
              
              
              
              
             
             
              // String add4 = rs.getString("ROLL");
              // etroll1.setText(add4);
              try {
                
            
              Statement st = con.createStatement();
              ResultSet rs = st.executeQuery("select * from employee where ROLLNUM= '"+num.getText()+"'");
              if(rs.next()){
                  byte[] img = rs.getBytes("PHOTOS");
                  ImageIcon image =new ImageIcon(img);
                  Image im = image.getImage();
                  Image myIm = im.getScaledInstance(images.getWidth(),images.getHeight(),Image.SCALE_SMOOTH);
                  ImageIcon newImage= new ImageIcon(myIm);
                  images.setIcon(newImage);
              }
                }
             catch (HeadlessException | SQLException ex) {
                
            } 
            
           
            
            
        
              
              
              
              
             Table(); 
              
              
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
     
     public void Table()
    {
         try{
           
             String sql="SELECT  ACTION,MOTIF,DESTINATION,AUTORISER AS 'AUTORISEE PAR:',DATE1 AS 'DATE OUT',DATE2 AS 'DATE IN',JRS,HEURE, NUM,STATUT FROM  mvm_pers  WHERE  roll='"+num.getText()+"' order by num";
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
       TableColumn col6=etjTable3.getColumnModel().getColumn(6);
       TableColumn col7=etjTable3.getColumnModel().getColumn(7);
       TableColumn col8=etjTable3.getColumnModel().getColumn(8);
      TableColumn col9=etjTable3.getColumnModel().getColumn(9);

       
      
       col0.setPreferredWidth(100);
       col1.setPreferredWidth(250);
       col2.setPreferredWidth(100);
       col3.setPreferredWidth(100);
       col4.setPreferredWidth(50);
       col5.setPreferredWidth(50);
       
       col6.setPreferredWidth(10);
       col7.setPreferredWidth(50);
       col8.setPreferredWidth(70);
       col9.setPreferredWidth(30);
     
      
      
      
       
       
      // jTable1.setModel(mode);
       
     
    }catch(SQLException ex ){
     JOptionPane.showMessageDialog(null, ex);
}
    } 

 public void select_jTable()
   {
        try{
          int row= etjTable3.getSelectedRow();
          String Table_click = (etjTable3.getModel().getValueAt(row,8). toString());
          String sql = "SELECT * FROM mvm_pers WHERE NUM= '"+Table_click+"'";
          pst = con.prepareStatement(sql);
          rs=pst.executeQuery();
          
//  `COMMENTAIRE`, `STATUT` 
          if(rs.next()){
              String add1 = rs.getString("ACTION");
             etjComboBox6.setSelectedItem(add1);
               String add2 = rs.getString("MOTIF");
              motif.setText(add2);
               String add3 = rs.getString("DESTINATION");
               destination.setText(add3);
               String add4 = rs.getString("AUTORISER");
              nom_auto.setSelectedItem(add4);
               String add5 = rs.getString("DATE1");
               date1.setText(add5);
               String add6 = rs.getString("DATE2");
              date2.setText(add6);
               String add7 = rs.getString("JRS");
             jrs.setText(add7);
               String add8 = rs.getString("COMMENTAIRE");
            commentaire.setText(add8);
               String add9 = rs.getString("NUM");
            num.setText(add9);
            
            statut.setText("Entrée");
            
          }
         
    }catch(SQLException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }
   }
 
 
 public void days(){
 Double pour=null,PRICEE=null;
          float daysBetween = 0;
SimpleDateFormat myFormat = new SimpleDateFormat("dd MM yyyy");
  SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd MM yyyy");
       String dateAfterString = dateFormat1.format(date2.getDate());
        String dateBeforeString = myFormat.format(date1.getDate());
         
	
	 try {
	       Date dateBefore = myFormat.parse(dateBeforeString);
	       Date dateAfter = myFormat.parse(dateAfterString);
	       long difference = dateAfter.getTime() - dateBefore.getTime();
	      daysBetween = (difference / (1000*60*60*24));
              
               /* You can also convert the milliseconds to days using this method
                * float daysBetween = 
                *         TimeUnit.DAYS.convert(difference, TimeUnit.MILLISECONDS)
                */
	      
	 } catch (Exception e) {
	       e.printStackTrace();
	 }
          
          String  aaa= ""+daysBetween;
if(aaa.equals("0.0")){
  jrs.setText("1");
}else{
  jrs.setText(aaa);
}
      
}
   public void etroll()
     {
         try{
             String sql="SELECT NUM FROM mvm_pers ORDER BY NUM DESC LIMIT 1";
             pst=con.prepareStatement(sql);
             rs=pst.executeQuery();
             if(rs.next()){
                 String rl=rs.getString("NUM");
                 int ln=rl.length();
                 String stxt=rl.substring(0,7);
                 String snum=rl.substring(7,ln);
                 int n = Integer.parseInt(snum);
                 n++;
                 snum=Integer.toString(n);
                 etrolls=stxt+snum;
                 
                 
             }else{
                 //rolls="FICHE/EB/2018/1";
                 etrolls="MVTPRS-1001";
             }
                 
             
         }catch(Exception e){
             JOptionPane.showMessageDialog(null, e); 
             
         }
     }
 public void save(){
  //  if(num.getText().equals("")){
     try {
         
            etroll();
            
             SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
        
         
        PreparedStatement pst = con.prepareStatement("INSERT INTO mvm_pers(`ACTION`, `NOMS`, `FONCTION`, `ROLL`, `MOTIF`, `DESTINATION`, `AUTORISER`, `DATE1`, `DATE2`, `JRS`, `HEURE`, `COMMENTAIRE`, `STATUT`, `NUM`,`NOM`) "
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        
//`ACTION`, `NOMS`, `FONCTION`, `ROLL`, `MOTIF`, `DESTINATION`, `AUTORISER`, `DATE1`, `DATE2`, `JRS`, `HEURE`, `COMMENTAIRE`, `STATUT` 
        pst.setString(1,etjComboBox6.getSelectedItem().toString());
         pst.setString(2, nom.getText()+" "+lnom.getText());
        pst.setString(3, fonction.getText());
         pst.setString(4, num.getText());
         pst.setString(5, motif.getText());
         pst.setString(6, destination.getText());
          pst.setString(7,"Autorisé(e) Par:");
           String addDate1 = dateFormats.format(date1.getDate());
          pst.setString(8,addDate1);
           String addDate2 = dateFormats.format(date2.getDate());
          pst.setString(9,addDate2);
              pst.setString(10,jrs.getText());
                pst.setString(11,heure.getText());
                  pst.setString(12,commentaire.getText());
                   pst.setString(13,statut.getText());
                   pst.setString(14, etrolls);
                   
                    pst.setString(15,nom_auto.getSelectedItem().toString());
       
         
         
          pst.executeUpdate();
        
              //   JOptionPane.showMessageDialog(null,"CLERCK_IN  SUCCESSFUL BONNE JOURNEE!!!        ");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
   
    
    
} 
 
  public void etdeletee()
    {
         try{
        String sql = "DELETE FROM mvm_pers WHERE NUM=?";
        
         pst = con.prepareStatement(sql);
         pst.setString(1,num.getText());
         pst.executeUpdate();

     }catch(SQLException | HeadlessException ex ){
         JOptionPane.showMessageDialog(null,ex);
     } 
        // Table();
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
        jSeparator1 = new javax.swing.JSeparator();
        images = new javax.swing.JLabel();
        nom = new javax.swing.JLabel();
        lnom = new javax.swing.JLabel();
        fonction = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        etjTable3 = new javax.swing.JTable();
        heure = new javax.swing.JTextField();
        jrs = new javax.swing.JTextField();
        date2 = new com.alee.extended.date.WebDateField();
        jLabel8 = new javax.swing.JLabel();
        date1 = new com.alee.extended.date.WebDateField();
        jLabel9 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        statut = new javax.swing.JTextField();
        num = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        motif = new javax.swing.JEditorPane();
        etjComboBox6 = new javax.swing.JComboBox<>();
        destination = new javax.swing.JTextField();
        commentaire = new javax.swing.JTextField();
        nom_auto = new javax.swing.JComboBox<>();
        jButton4 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        datesss = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Mouvement du personnel");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        images.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        nom.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        nom.setText("Name:");

        lnom.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lnom.setText("Last Name:");

        fonction.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        fonction.setText("Fonction");

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_Safe_Out_16px.png"))); // NOI18N
        jLabel6.setText("Clerck");
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });

        etjTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        etjTable3.setFocusable(false);
        etjTable3.setRowHeight(25);
        etjTable3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                etjTable3MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(etjTable3);

        heure.setEditable(false);
        heure.setBackground(new java.awt.Color(240, 240, 241));
        heure.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        heure.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        heure.setText("H Sortie");

        jrs.setEditable(false);
        jrs.setBackground(new java.awt.Color(240, 240, 241));
        jrs.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jrs.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jrs.setText("Jrs");

        date2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        date2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        date2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                date2MouseClicked(evt);
            }
        });
        date2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                date2ActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setText("Date Entrée");

        date1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        date1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        date1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                date1ActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setText("Date Sortie");

        statut.setEditable(false);
        statut.setBackground(new java.awt.Color(240, 240, 241));
        statut.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        statut.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        statut.setText("Sortie");

        num.setEditable(false);
        num.setBackground(new java.awt.Color(240, 240, 241));
        num.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        num.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        motif.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        motif.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        motif.setText("  Motifs");
        jScrollPane2.setViewportView(motif);

        etjComboBox6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        etjComboBox6.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Visite", "Entretien", "Réunion", "Mission", "Répos/Pause", "Permission ou Autres", " " }));
        etjComboBox6.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        etjComboBox6.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                etjComboBox6PopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        destination.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        destination.setText("Destination");
        destination.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        destination.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                destinationMouseClicked(evt);
            }
        });
        destination.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                destinationKeyTyped(evt);
            }
        });

        commentaire.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        commentaire.setText("Commentaires");
        commentaire.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        commentaire.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                commentaireActionPerformed(evt);
            }
        });

        nom_auto.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        nom_auto.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Autorisé(e) Par:" }));
        nom_auto.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButton4.setText("Ok");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton3.setText("Rmv");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        datesss.setEditable(false);
        datesss.setBackground(new java.awt.Color(240, 240, 241));
        datesss.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        datesss.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(etjComboBox6, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(images, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(nom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lnom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(fonction, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 53, Short.MAX_VALUE)
                        .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 888, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 436, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(commentaire)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(destination, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(nom_auto, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(date1, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(date2, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jrs, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(heure, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statut, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(num)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(datesss)))
                        .addContainerGap())))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(etjComboBox6)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(nom)
                                .addGap(4, 4, 4)
                                .addComponent(lnom)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(fonction))
                            .addComponent(images, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(date1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(date2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9)
                            .addComponent(jLabel8)
                            .addComponent(heure, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jrs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(num, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(datesss, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(destination, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(nom_auto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(6, 6, 6)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(commentaire, javax.swing.GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE)))
                            .addComponent(jScrollPane2)))
                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 496, Short.MAX_VALUE)
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

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void etjTable3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_etjTable3MouseClicked
       select_jTable();       // TODO add your handling code here:
    }//GEN-LAST:event_etjTable3MouseClicked

    private void date2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_date2MouseClicked
        //days();        // TODO add your handling code here:
    }//GEN-LAST:event_date2MouseClicked

    private void date2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_date2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_date2ActionPerformed

    private void date1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_date1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_date1ActionPerformed

    private void etjComboBox6PopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_etjComboBox6PopupMenuWillBecomeInvisible
        if(etjComboBox6.getSelectedItem().equals("CONSULTANT")){//&&etjComboBox6.getSelectedItem().equals("EMPLOYE")){
          //  noms.setEditable(true);
          //  fonction.setEditable(true);
        }else{
          //  noms.setEditable(false);
          //  fonction.setEditable(false);
        }// TODO add your handling code here:
    }//GEN-LAST:event_etjComboBox6PopupMenuWillBecomeInvisible

    private void destinationMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_destinationMouseClicked
        destination.setText("");
        // TODO add your handling code here:
    }//GEN-LAST:event_destinationMouseClicked

    private void destinationKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_destinationKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_destinationKeyTyped

    private void commentaireActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_commentaireActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_commentaireActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        days();
        save();
        Table();   // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        etdeletee();
        Table();
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
 attCall_roll();        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel6MouseClicked

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
            java.util.logging.Logger.getLogger(mouvement_du_personnel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(mouvement_du_personnel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(mouvement_du_personnel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(mouvement_du_personnel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                 WebLookAndFeel.install(true);
                new mouvement_du_personnel().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField commentaire;
    private com.alee.extended.date.WebDateField date1;
    private com.alee.extended.date.WebDateField date2;
    private javax.swing.JTextField datesss;
    private javax.swing.JTextField destination;
    private javax.swing.JComboBox<String> etjComboBox6;
    private javax.swing.JTable etjTable3;
    private javax.swing.JLabel fonction;
    private javax.swing.JTextField heure;
    private javax.swing.JLabel images;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JTextField jrs;
    private javax.swing.JLabel lnom;
    private javax.swing.JEditorPane motif;
    private javax.swing.JLabel nom;
    private javax.swing.JComboBox<String> nom_auto;
    private javax.swing.JTextField num;
    private javax.swing.JTextField statut;
    // End of variables declaration//GEN-END:variables
}
