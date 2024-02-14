/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intreprisemanagementsystem;


import Home_page.home;
import Home_page.menu_finance;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import net.proteanit.sql.DbUtils;
import java.sql.SQLException;
import javax.swing.DefaultListModel;
import journals.journal1;

/**
 *
 * @author Dosh
 */
public class avance_sur_salaire1 extends javax.swing.JFrame {

 Connection con=null;
PreparedStatement pst=null;
ResultSet rs= null;
String etrolls;
String rolls;
DefaultTableModel mode;
    public avance_sur_salaire1() {
        initComponents();
              con=JavaDbConnect.dbConnect();
          setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("icons8_Raspberry_Pi_48px_1.png")));
         
         call_tablee();
          SimpleDateFormat dateFormats = new SimpleDateFormat("YYYY");
      String Dates = dateFormats.format(journal1.jDateChooser1.getDate());
         
         jLabel2.setText(Dates);
       motant_programme.setEnabled(false);  
    }
    public void call_tablee(){
    
            try{
       
      
       String sql="SELECT distinct(`NUM`) FROM  avance_sur_sal Where buss='"+buss.getSelectedItem()+"'";
       //String sql="SELECT SUM(`CREDIT_PT`) from `facture_fournisseur` WHERE `COMPTE`='LEVI LIKOYO'";
       pst = con.prepareStatement(sql);
      rs= pst.executeQuery();
    //  if(rs.next()){
       jTable2.setModel(DbUtils.resultSetToTableModel(rs));
     // }
    }catch(SQLException ex ){
     JOptionPane.showMessageDialog(null, ex);
}
      try{
             String sql="select CAT  from caisse_dispacting WHERE NAME='"+home.user.getText()+"' and ACCESS='Yes'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                String sum=rs.getString("CAT");
                  buss.addItem(sum);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex); }   
 
    }
     String mois;
    String annee;
     public void mois(){
    SimpleDateFormat dateFormat1 = new SimpleDateFormat("MMMM");
         String addDate1 = dateFormat1.format(new Date());
         
         SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy");
         annee = dateFormat2.format(new Date());
         if(addDate1.equals("January")){
         mois=("Janvier");
         // total.setText(mois);
         
         }else if(addDate1.equals("February")){
         mois=("Fevrier");
         }else if(addDate1.equals("March")){
          mois=("Mars");
         }else if(addDate1.equals("April")){
          mois=("Avril");
         }else if(addDate1.equals("May")){
          mois=("Mais");
         }else if(addDate1.equals("June")){
          mois=("Juin");
         }else if(addDate1.equals("July")){
          mois=("Juillet");
         }else if(addDate1.equals("August")){
          mois=("Aout");
         }else if(addDate1.equals("September")){
          mois=("Septembre");
         }else if(addDate1.equals("October")){
          mois=("Octobre");
         }else if(addDate1.equals("November")){
          mois=("Novembre");
         }else if(addDate1.equals("December")){
          mois=("Decembre");
         }
        
    
    }
    
    
      public void rolls()
     {
         try{
            String sql="SELECT NUM FROM CAISSES ORDER BY NUM DESC LIMIT 1";
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
                 rolls=stxt+snum;
                 
                 
                 
             }else{
                 //rolls="FICHE/EB/2018/1";
                 rolls="EB1001";
             }
                 
             
         }catch(Exception e){
             JOptionPane.showMessageDialog(null, e); 
             
         }
     }
      public void etroll()
     {
      Date dates= new Date();
        // con=JavaDbConnect.dbConnect();
         SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM");
         String addDate = dateFormats.format(dates);
         try{
            String sql="SELECT NUM FROM ohada_trans ORDER BY NUM DESC LIMIT 1";
             pst=con.prepareStatement(sql);
             rs=pst.executeQuery();
             if(rs.next()){
                 String rl=rs.getString("NUM");
                 int ln=rl.length();
                 String stxt=rl.substring(0,12);
                 String snum=rl.substring(12,ln);
                 int n = Integer.parseInt(snum);
                 n++;
                 snum=Integer.toString(n);
                 rolls=stxt+snum;
                 
                 
                 
             }else{
                rolls= "No: "+addDate+"/1";
                
             }
              // compte.setText(rolls);
             
         }catch(NumberFormatException | SQLException e){
             JOptionPane.showMessageDialog(null, e); 
             
         }
         
     }
    
    
      public void call_table(){
      
           try{
           
             String sql="SELECT `MOTANT`, `MONTANT_PR`, `DATES` FROM `avance_sur_sal` WHERE num ='"+roll.getText()+"' and BUSS='"+buss.getSelectedItem()+"'";
     //  String sql="SELECT ID, `NUM_FACTURE`, `DATE_FACTURE`, `DESCRIPTION`, `DEBIT_QTY`, `DEBIT_PU`, `DEBIT_PT`, `DEBIT_TVA`, `CREDIT_QTY`, `CREDIT_PU`, `CREDIT_PT`, `CREDIT_TVA`, `DATES`, `NUM` FROM facture_fournisseur where compte='"+jComboBox6.getSelectedItem()+"'";
       pst = con.prepareStatement(sql);
      rs= pst.executeQuery();
      
       
       jTable1.setModel(DbUtils.resultSetToTableModel(rs));
       mode=new DefaultTableModel();
       
       
        TableColumn col0=jTable1.getColumnModel().getColumn(0);
        TableColumn col1=jTable1.getColumnModel().getColumn(1);
         TableColumn col2=jTable1.getColumnModel().getColumn(2);
      //   TableColumn col3=jTable1.getColumnModel().getColumn(3);
      
       
       col0.setPreferredWidth(100);
       col1.setPreferredWidth(100);
       col2.setPreferredWidth(100);
       //col3.setPreferredWidth(100);
       
     
      
       
       
      // jTable1.setModel(mode);
       
     
    }catch(SQLException ex ){
     JOptionPane.showMessageDialog(null, ex);
}
    
      
      }
     
     
            public void Call_ID_sum_entre_refresh()
    {
         String tmp =(String)NewJInternalFramejournal_de_caisse.jComboBox3.getSelectedItem();
         
        
           if(Float.parseFloat(motant.getText()) <= Float.parseFloat(NewJInternalFramejournal_de_caisse.doll3.getText())) {  
               
            float a = Float.parseFloat(NewJInternalFramejournal_de_caisse.doll3.getText());
            float b = Float.parseFloat(motant.getText());
            float B = Float.parseFloat(NewJInternalFramejournal_de_caisse.used.getText());
            
            float c;
             float C;
           
            String d;
            String D;
           
            c=a-b;
            C=B+b;
          
            d= String.format("%.2f",c);
             
            NewJInternalFramejournal_de_caisse.doll3.setText(d);
            
             D= String.format("%.2f",C);
            NewJInternalFramejournal_de_caisse.used.setText(D);
                 
                 
           }else{ JOptionPane.showMessageDialog(null, "LA MONNAIS N'EST PAS SOUFFISANTE POUR CETTE TRANSACTION!!!");} 
            
       
    
      
       try{
            String sql="select sum(USD1) from journal";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                String sum=rs.getString("sum(USD1)");
               //  NewJInternalFramejournal_de_caisse. call1.setText(sum);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        } 
       
       
                   try{
            String sqls="select max(COUT) from "+tmp+"";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
            while(rs.next()){
                String add1 = rs.getString("max(COUT)");
              NewJInternalFramejournal_de_caisse.coutin.setText(add1);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
      try{
            String sqls="select max(COUT_OUT) from "+tmp+"";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
            while(rs.next()){
                String sum=rs.getString("max(COUT_OUT)");
                  NewJInternalFramejournal_de_caisse.coutout.setText(sum);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
        Float a = Float.parseFloat( NewJInternalFramejournal_de_caisse.coutin.getText());
Float b = Float.parseFloat( NewJInternalFramejournal_de_caisse.coutout.getText());


 Float aa = Float.parseFloat( NewJInternalFramejournal_de_caisse.coutin.getText());
Float bb = Float.parseFloat( NewJInternalFramejournal_de_caisse.coutout.getText());
//Float e = Float.parseFloat(coutin1.getText());

Float c;
Float cc;

String d;
String dd;

c=a-b;
cc=aa-bb;

d= String.format("%.2f",c);
NewJInternalFramejournal_de_caisse.coutin1.setText(d);

dd= String.format("%.2f",cc);
//coutin1.setText(dd);
    }
     
     
     

     
        
        
        
         public void call_in_table(){
        
         try{
       
      
       String sql="SELECT MOTANT,DATES FROM  avance_sur_sal  Where num='"+roll.getText()+"' AND MOTANT <> 0 and BUSS='"+buss.getSelectedItem()+"'";
       //String sql="SELECT SUM(`CREDIT_PT`) from `facture_fournisseur` WHERE `COMPTE`='LEVI LIKOYO'";
       pst = con.prepareStatement(sql);
      rs= pst.executeQuery();
    //  if(rs.next()){
       jTable1.setModel(DbUtils.resultSetToTableModel(rs));
     // }
    }catch(SQLException ex ){
     JOptionPane.showMessageDialog(null, ex);
}
      try{
       
      
       String sql="SELECT `MOTANT`,`MOTANT_PR` as 'M. PR',`MATANT_R` AS 'SOLD', `MOIS` FROM `programation_avance_sur_sal` WHERE `NUM`='"+roll.getText()+"' and BUSS='"+buss.getSelectedItem()+"'";
       //String sql="SELECT SUM(`CREDIT_PT`) from `facture_fournisseur` WHERE `COMPTE`='LEVI LIKOYO'";
       pst = con.prepareStatement(sql);
      rs= pst.executeQuery();
    //  if(rs.next()){
       jTable3.setModel(DbUtils.resultSetToTableModel(rs));
     // }
    }catch(SQLException ex ){
     JOptionPane.showMessageDialog(null, ex);
} 
         }
         public void call_in_tableS(){
        
         
           try{
       
      
       String sql="SELECT distinct(NUM) FROM  avance_sur_sal Where NUM LIKE '"+recherche.getText()+"%'";
       //String sql="SELECT SUM(`CREDIT_PT`) from `facture_fournisseur` WHERE `COMPTE`='LEVI LIKOYO'";
       pst = con.prepareStatement(sql);
      rs= pst.executeQuery();
    //  if(rs.next()){
      // jTable1.setModel(DbUtils.resultSetToTableModel(rs));
       jTable2.setModel(DbUtils.resultSetToTableModel(rs));
     // }
    }catch(SQLException ex ){
     JOptionPane.showMessageDialog(null, ex);
}
         }
        
        
         public void programme(){        
     try{
     
      String sql1="Insert into programation_avance_sur_sal( NUM,`MOTANT`, `MATANT_R`, `MOTANT_PR`,`MOIS`,buss,annee)"+"values(?,?,?,?,?,?,?)";
      pst=con.prepareStatement(sql1);
      
      pst.setString(1,roll.getText());
      pst.setString(2,motant.getText());
         pst.setString(3,montat_reste.getText());
         pst.setString(4,motant_programme.getText());
         pst.setString(5, jList1.getSelectedItem().toString());
         pst.setString(6, buss.getSelectedItem().toString());
         pst.setString(7,jLabel2.getText());
          pst.executeUpdate();
          JOptionPane.showMessageDialog(null,"" +motant_programme.getText()+"$   "+"PROGRAMMED TO "+name.getText()+"  FOR "+jList1.getSelectedItem());
     }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}
     
     
      try{
     
     String sql2="Insert into avance_sur_sal(`NUM`,`NAME`, `LNAME`, `TITLE`, `ADRESSE`, `MOTANT`, `MONTANT_PR`, `DATES`,NUMS,BUSS)"+"value(?,?,?,?,?,?,?,?,?,?)";
      pst=con.prepareStatement(sql2);
      //pst.setString(1, roll.getText());
      pst.setString(1, roll.getText());
      pst.setString(2, name.getText());
      pst.setString(3, "");
      pst.setString(4,"");
      pst.setString(5, "");
      pst.setInt(6,0);
      pst.setString(7,motant_programme.getText());
       pst.setString(9,"");
       pst.setString(10, buss.getSelectedItem().toString());
     SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(new Date());
         pst.setString(8, addDate);
         pst.executeUpdate();
         
       //  JOptionPane.showMessageDialog(null,"AVANCE SUR SALAIRE PAYE A "+" "+name.getText());
     }catch(Exception ex){JOptionPane.showMessageDialog(null,ex);}
     
         try{
          float c;
       String d;
         String sql3="Select SUM(MOTANT),SUM(MONTANT_PR) from  avance_sur_sal WHERE NUM = '"+roll.getText()+"' and BUSS='"+buss.getSelectedItem()+"'";
         
           pst = con.prepareStatement(sql3);
          rs=pst.executeQuery();
          if(rs.next()){
              float sum4=rs.getFloat("SUM(MOTANT)");
                // motant.setText(sum4);
                 
                 float sum44=rs.getFloat("SUM(MONTANT_PR)");
                // motant.setText(sum44);
                
                 c=sum4-sum44;
                 
                 d= String.format("%.2f",c);
                  motant.setText(d);
               }
         
         }catch(Exception e){JOptionPane.showMessageDialog(null,e);}

              }
            
               
         
      //   }catch(Exception e){JOptionPane.showMessageDialog(null,e);}
         
      //   }
         
          public void select_jTable2()
   {
       Double c;
       String d;
        try{
          int row= jTable1.getSelectedRow();
          String Table_click = (jTable1.getModel().getValueAt(row,0). toString());
          String sql = "SELECT NUM,`NAME`,`TITLE`,SUM(MOTANT),SUM(MONTANT_PR)  FROM  avance_sur_sal WHERE NUM = '"+Table_click+"' and BUSS='"+buss.getSelectedItem()+"'";
          pst = con.prepareStatement(sql);
          rs=pst.executeQuery();
          if(rs.next()){
             String sum0=rs.getString("NUM");
                roll.setText(sum0);
                //Date sum=rs.getDate("DATES");
                 //jDateChooser1.setDate(sum);
                
                String sum2=rs.getString("TITLE");
                 title.setText(sum2);
                  
              Double sum4=rs.getDouble("SUM(MOTANT)");
                // motant.setText(sum4);
                 
                Double  sum44=rs.getDouble("SUM(MONTANT_PR)");
                // motant.setText(sum44);
                
                 c=sum4-sum44;
                 
                 d= String.format("%.2f",c);
                  motant.setText(d);
                 
                  String sum5=rs.getString("NAME");
                name.setText(sum5);
               
             
          }
         
    }catch(SQLException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }
   }
            public void select_jTable2s()
   {
     Double c,sum1=null,sum2=null;
       String d;
        int row= jTable2.getSelectedRow();
          String Table_click = (jTable2.getModel().getValueAt(row,0). toString());
        try{
         
          String sql = "SELECT NUM,`NAME`,`TITLE`,SUM(MOTANT),SUM(MONTANT_PR)  FROM  avance_sur_sal WHERE NUM = '"+Table_click+"' and BUSS='"+buss.getSelectedItem()+"'";
          pst = con.prepareStatement(sql);
          rs=pst.executeQuery();
          if(rs.next()){
              String sum0=rs.getString("NUM");
               roll.setText(sum0);
                //Date sum=rs.getDate("DATES");
                 //jDateChooser1.setDate(sum);
                
               
                   String sum5=rs.getString("NAME");
                name.setText(sum5);
              sum1=rs.getDouble("SUM(MOTANT)");  }
         
    }catch(SQLException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }
        
         try{
         
          String sql = "SELECT SUM(MOTANT_PR)  FROM programation_avance_sur_sal WHERE NUM = '"+Table_click+"' and BUSS='"+buss.getSelectedItem()+"'";
          pst = con.prepareStatement(sql);
          rs=pst.executeQuery();
          if(rs.next()){
             
                  
              sum2=rs.getDouble("SUM(MOTANT_PR)"); }
         
    }catch(SQLException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }
   
          
                 c=sum1-sum2;
                 
                 d= String.format("%.2f",c);
                  motant.setText(d);
                 
                 
        
        
   }
         
    public void calulation2()
            
            
         {
             
             float a = Float.parseFloat(motant.getText());
            float b = Float.parseFloat(motant_programme.getText());
            float c;// = Math.floor(a*b *100.0) / 100.0;
            
            String d;
            
            if(b>a){JOptionPane.showMessageDialog(null,"NO CREDIT RECORDED");
            motant_programme.setText("0.00");
            }
            else{
            c=a-b;
            d= String.format("%.1f",c);
           // sh3.setText(d);
            montat_reste.setText(d);
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

        webDateField1 = new com.alee.extended.date.WebDateField();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        roll = new Palette.TextField1();
        name = new Palette.TextField1();
        title = new Palette.TextField1();
        motant = new Palette.TextField1();
        motant_programme = new Palette.TextField1();
        montat_reste = new Palette.TextField1();
        recherche = new Palette.TextField1();
        jList1 = new Palette.Combobox();
        buss = new Palette.Combobox();

        webDateField1.setText("webDateField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setType(java.awt.Window.Type.UTILITY);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Salary Advance");
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable1.setRowHeight(30);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton2.setText("Programme");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable2.setRowHeight(30);
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTable2);

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable3.setRowHeight(30);
        jTable3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable3MouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(jTable3);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("ANNEE");

        roll.setEditable(false);
        roll.setBackground(new java.awt.Color(240, 240, 241));

        name.setEditable(false);
        name.setBackground(new java.awt.Color(240, 240, 241));

        title.setEditable(false);
        title.setBackground(new java.awt.Color(240, 240, 241));

        motant.setEditable(false);
        motant.setBackground(new java.awt.Color(240, 240, 241));

        motant_programme.setText("0.00");
        motant_programme.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                motant_programmeKeyReleased(evt);
            }
        });

        montat_reste.setEditable(false);
        montat_reste.setBackground(new java.awt.Color(240, 240, 241));
        montat_reste.setText("0.00");

        recherche.setText("Recherche");
        recherche.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                rechercheKeyReleased(evt);
            }
        });

        jList1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "", "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" }));
        jList1.setLabeText("Mois");
        jList1.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                jList1PopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        buss.setLabeText("Liste de projets");
        buss.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                bussPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
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
                        .addComponent(motant_programme, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(montat_reste, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jList1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(recherche, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(buss, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(roll, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(motant, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(buss, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(8, 8, 8)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(roll, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(motant, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(motant_programme, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(montat_reste, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(recherche, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jList1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
   
          // TODO add your handling code here:
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
programme(); 
call_in_table();
motant_programme.setText("0.00");// TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
//select_jTable2();        // TODO add your handling code here:
    }//GEN-LAST:event_jTable1MouseClicked

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
select_jTable2s();
 call_in_table();// TODO add your handling code here:
    }//GEN-LAST:event_jTable2MouseClicked

    private void jTable3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable3MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable3MouseClicked

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
menu_finance.avc.setText("Avance sur Salaire");        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowClosing

    private void rechercheKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_rechercheKeyReleased
call_in_tableS();         // TODO add your handling code here:
    }//GEN-LAST:event_rechercheKeyReleased

    private void motant_programmeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_motant_programmeKeyReleased
calulation2();         // TODO add your handling code here:
    }//GEN-LAST:event_motant_programmeKeyReleased

    private void jList1PopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_jList1PopupMenuWillBecomeInvisible
motant_programme.setEnabled(true);         // TODO add your handling code here:
    }//GEN-LAST:event_jList1PopupMenuWillBecomeInvisible

    private void bussPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_bussPopupMenuWillBecomeInvisible
call_tablee();         // TODO add your handling code here:
    }//GEN-LAST:event_bussPopupMenuWillBecomeInvisible

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
            java.util.logging.Logger.getLogger(avance_sur_salaire1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(avance_sur_salaire1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(avance_sur_salaire1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(avance_sur_salaire1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new avance_sur_salaire1().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Palette.Combobox buss;
    public static javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private Palette.Combobox jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    public static javax.swing.JTable jTable1;
    public static javax.swing.JTable jTable2;
    public static javax.swing.JTable jTable3;
    private Palette.TextField1 montat_reste;
    private Palette.TextField1 motant;
    private Palette.TextField1 motant_programme;
    private Palette.TextField1 name;
    private Palette.TextField1 recherche;
    private Palette.TextField1 roll;
    private Palette.TextField1 title;
    private com.alee.extended.date.WebDateField webDateField1;
    // End of variables declaration//GEN-END:variables
}
