/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intreprisemanagementsystem;


import com.alee.laf.WebLookAndFeel;
import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import net.proteanit.sql.DbUtils;

/**
 *
 * @author Administrator
 */
public class mouvement_du_personel extends javax.swing.JFrame {

   Connection con=null;
PreparedStatement pst=null;
ResultSet rs= null;
String etrolls;
DefaultTableModel mode;
int xx=0;
int yy=0;
 Timer timer;
    public mouvement_du_personel() {
        initComponents();
         setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("icons8_Raspberry_Pi_48px_1.png")));
         con=JavaDbConnect.dbConnect();
          etjTable3.getTableHeader().setFont(new Font("Segoe UI",Font.BOLD,14));
        etjTable3.getTableHeader().setOpaque(false);
        etjTable3.getTableHeader().setBackground(new Color(32,136,203));
        etjTable3.getTableHeader().setForeground(new Color(255,255,255));
        etjTable3.setRowHeight(25);
         noms.setEditable(false);
            fonction.setEditable(false);
            jDateChooser1.setDate(new Date());
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
 // AUTO ROLL NUMBER
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
  public void attCall_roll()
    {
      
         String A =JOptionPane.showInputDialog("Please Entre Your Roll Number!!!"); 
        try{
            String sql="select * from employee where ROLLNUM= ? and ACTIVE='Active'";
            
           
             pst=con.prepareStatement(sql);
            pst.setString(1, A);
            rs=pst.executeQuery();
            if(rs.next()){
             String add1 = rs.getString("NOMS");
             String add2 = rs.getString("LNAME");
              noms.setText(add1+" "+add2);
             
             String adde = rs.getString("roll");
             roll.setText(adde);
              
              
              
               try{
            String sqls="select * from employee where ROLLNUM= ?";
            
           
             pst=con.prepareStatement(sqls);
            pst.setString(1, A);
            rs=pst.executeQuery();
            if(rs.next()){
             String add11 = rs.getString("DEPARTMENT");
           fonction.setText(add11);
             
             
              // String add4 = rs.getString("ROLL");
              // etroll1.setText(add4);
            }
            
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
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

public void save(){
  //  if(num.getText().equals("")){
     try {
         
            etroll();
            
             SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
        
         
        PreparedStatement pst = con.prepareStatement("INSERT INTO mvm_pers(`ACTION`, `NOMS`, `FONCTION`, `ROLL`, `MOTIF`, `DESTINATION`, `AUTORISER`, `DATE1`, `DATE2`, `JRS`, `HEURE`, `COMMENTAIRE`, `STATUT`, `NUM`,`NOM`) "
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        
//`ACTION`, `NOMS`, `FONCTION`, `ROLL`, `MOTIF`, `DESTINATION`, `AUTORISER`, `DATE1`, `DATE2`, `JRS`, `HEURE`, `COMMENTAIRE`, `STATUT` 
        pst.setString(1,etjComboBox6.getSelectedItem().toString());
         pst.setString(2, noms.getText());
        pst.setString(3, fonction.getText());
         pst.setString(4, roll.getText());
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
public void update(){
    int row= etjTable3.getSelectedRow();
          String Table_click = (etjTable3.getModel().getValueAt(row,8). toString());

    try {
        PreparedStatement pst = con.prepareStatement("UPDATE `mvm_pers` SET HEURE=?,COMMENTAIRE=?,STATUT=? WHERE NUM='"+Table_click+"'");
        
  pst.setString(1,heure.getText());
  pst.setString(2,commentaire.getText());
  pst.setString(3,"Entrée");
        
       
         
         
          pst.executeUpdate();
        
              //   JOptionPane.showMessageDialog(null,"CLERCK_IN  SUCCESSFUL BONNE JOURNEE!!!        ");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
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
  // DELETE
  
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
    
    
public void Table()
    {
         try{
           
             String sql="SELECT  ACTION,MOTIF,DESTINATION,AUTORISER AS 'AUTORISEE PAR:',DATE1 AS 'DATE OUT',DATE2 AS 'DATE IN',JRS,HEURE, NUM,STATUT FROM  mvm_pers  WHERE  roll='"+roll.getText()+"'";
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
//         Double b=pour*30;//total jour d'amorticement
//         Double c=PRICEE/b;//motant par jour
//         Double d=Double.parseDouble(aaa); // Nbre de date entre deux date
//         
//        Double A=b-d; // JOUR RESTANTS
//       Double B= A*c; //AMORTISEMENT ACTUEL
//      String C= String.format("%.2f",B);
        jrs.setText(aaa);
}
public void refreshe(){
destination.setText("Destination");
motif.setText("Motifs");
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
        jLabel2 = new javax.swing.JLabel();
        jDateChooser1 = new com.alee.extended.date.WebDateField();
        statut = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        etjComboBox6 = new javax.swing.JComboBox<>();
        noms = new javax.swing.JTextField();
        fonction = new javax.swing.JTextField();
        roll = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        destination = new javax.swing.JTextField();
        motif = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        date1 = new com.alee.extended.date.WebDateField();
        date2 = new com.alee.extended.date.WebDateField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        heure = new javax.swing.JTextField();
        commentaire = new javax.swing.JTextField();
        jrs = new javax.swing.JTextField();
        nom_auto = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        etjTable3 = new javax.swing.JTable();
        buss = new javax.swing.JComboBox<>();
        jPanel5 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel2.setBackground(new java.awt.Color(0, 204, 204));
        jPanel2.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jPanel2MouseDragged(evt);
            }
        });

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

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel2.setText("Mouvement de Personnels");

        jDateChooser1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jDateChooser1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jDateChooser1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jDateChooser1ActionPerformed(evt);
            }
        });

        statut.setEditable(false);
        statut.setBackground(new java.awt.Color(240, 240, 241));
        statut.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        statut.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        statut.setText("Sortie");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(statut, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(num, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(num, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(statut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        etjComboBox6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        etjComboBox6.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Visite", "Entretien", "Réunion", "Mission", "Répos/Pause", " " }));
        etjComboBox6.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                etjComboBox6PopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        noms.setBackground(new java.awt.Color(240, 240, 241));
        noms.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        noms.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                nomsKeyReleased(evt);
            }
        });

        fonction.setBackground(new java.awt.Color(240, 240, 241));
        fonction.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        fonction.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fonctionActionPerformed(evt);
            }
        });
        fonction.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                fonctionKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                fonctionKeyTyped(evt);
            }
        });

        roll.setEditable(false);
        roll.setBackground(new java.awt.Color(240, 240, 241));
        roll.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        roll.setHorizontalAlignment(javax.swing.JTextField.CENTER);

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

        destination.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        destination.setText("Destination");
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

        motif.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        motif.setText("Motifs");
        motif.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                motifMouseClicked(evt);
            }
        });
        motif.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                motifActionPerformed(evt);
            }
        });

        jButton4.setText("Ok");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        date1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        date1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        date1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                date1ActionPerformed(evt);
            }
        });

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

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Date Sortie");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Date Entrée");

        heure.setEditable(false);
        heure.setBackground(new java.awt.Color(240, 240, 241));
        heure.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        heure.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        heure.setText("H Sortie");

        commentaire.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        commentaire.setText("Commentaires");
        commentaire.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                commentaireActionPerformed(evt);
            }
        });

        jrs.setEditable(false);
        jrs.setBackground(new java.awt.Color(240, 240, 241));
        jrs.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jrs.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jrs.setText("Jrs");

        nom_auto.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        nom_auto.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Autorisé(e) Par:" }));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addComponent(etjComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(noms, javax.swing.GroupLayout.PREFERRED_SIZE, 419, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(motif))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(date1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(date2, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jrs, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(heure, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(fonction, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(destination, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 333, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(roll, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1))
                            .addComponent(nom_auto, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(commentaire)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(etjComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(noms, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fonction, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(roll, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(0, 0, 0)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                    .addComponent(motif, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(destination)
                    .addComponent(nom_auto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(date1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(date2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton4)
                    .addComponent(heure, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(commentaire, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jrs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        etjTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        etjTable3.setFocusable(false);
        etjTable3.setIntercellSpacing(new java.awt.Dimension(0, 0));
        etjTable3.setRowHeight(25);
        etjTable3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                etjTable3MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(etjTable3);

        buss.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        buss.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select One Project" }));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(buss, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(851, Short.MAX_VALUE))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(buss, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 458, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(51, 51, 255));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 21, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel1MouseClicked

    private void etjComboBox6PopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_etjComboBox6PopupMenuWillBecomeInvisible
        if(etjComboBox6.getSelectedItem().equals("CONSULTANT")){//&&etjComboBox6.getSelectedItem().equals("EMPLOYE")){
            noms.setEditable(true);
            fonction.setEditable(true);
        }else{
            noms.setEditable(false);
            fonction.setEditable(false);
        }// TODO add your handling code here:
    }//GEN-LAST:event_etjComboBox6PopupMenuWillBecomeInvisible

    private void nomsKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nomsKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_nomsKeyReleased

    private void fonctionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fonctionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fonctionActionPerformed

    private void fonctionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fonctionKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_fonctionKeyReleased

    private void fonctionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fonctionKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_fonctionKeyTyped

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        noms.setText("");
        fonction.setText("");
        roll.setText("");
        num.setText("");
        etjTable3.setModel(new DefaultTableModel());
        refreshe();
        attCall_roll();              // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
     etdeletee();
     Table();
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void destinationMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_destinationMouseClicked
        destination.setText("");
      // TODO add your handling code here:
    }//GEN-LAST:event_destinationMouseClicked

    private void destinationKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_destinationKeyTyped
           // TODO add your handling code here:
    }//GEN-LAST:event_destinationKeyTyped

    private void motifMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_motifMouseClicked
if(motif.getText().equals("Wording")){
motif.setText(""); 
} else{

}              // TODO add your handling code here:
    }//GEN-LAST:event_motifMouseClicked

    private void motifActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_motifActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_motifActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
days(); 
save();
Table();   // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jPanel2MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseDragged
int x=evt.getXOnScreen();
       int y=evt.getYOnScreen();
       this.setLocation(x-xx, y-yy);        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel2MouseDragged

    private void jDateChooser1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jDateChooser1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jDateChooser1ActionPerformed

    private void date1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_date1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_date1ActionPerformed

    private void date2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_date2ActionPerformed
       // TODO add your handling code here:
    }//GEN-LAST:event_date2ActionPerformed

    private void commentaireActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_commentaireActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_commentaireActionPerformed

    private void etjTable3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_etjTable3MouseClicked
select_jTable();       // TODO add your handling code here:
    }//GEN-LAST:event_etjTable3MouseClicked

    private void date2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_date2MouseClicked
//days();        // TODO add your handling code here:
    }//GEN-LAST:event_date2MouseClicked

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
            java.util.logging.Logger.getLogger(mouvement_du_personel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(mouvement_du_personel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(mouvement_du_personel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(mouvement_du_personel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                 WebLookAndFeel.install(true);
                new mouvement_du_personel().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> buss;
    private javax.swing.JTextField commentaire;
    private com.alee.extended.date.WebDateField date1;
    private com.alee.extended.date.WebDateField date2;
    private javax.swing.JTextField destination;
    private javax.swing.JComboBox<String> etjComboBox6;
    private javax.swing.JTable etjTable3;
    private javax.swing.JTextField fonction;
    private javax.swing.JTextField heure;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private com.alee.extended.date.WebDateField jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jrs;
    private javax.swing.JTextField motif;
    private javax.swing.JComboBox<String> nom_auto;
    private javax.swing.JTextField noms;
    private javax.swing.JTextField num;
    private javax.swing.JTextField roll;
    private javax.swing.JTextField statut;
    // End of variables declaration//GEN-END:variables
}
