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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.DefaultListModel;
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
public class guerite extends javax.swing.JFrame {

    Connection con=null;
PreparedStatement pst=null;
ResultSet rs= null;
 DefaultTableModel mode;
 Timer timer;
 String rolls,NUM_ID;
    public guerite() {
        initComponents();
           this.setExtendedState(JFrame.MAXIMIZED_BOTH);
            setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("icons8_Raspberry_Pi_48px_1.png")));
        con=JavaDbConnect.dbConnect();
        etjTable3.getTableHeader().setFont(new Font("Segoe UI",Font.BOLD,14));
        etjTable3.getTableHeader().setOpaque(false);
        etjTable3.getTableHeader().setBackground(new Color(32,136,203));
        etjTable3.getTableHeader().setForeground(new Color(255,255,255));
        etjTable3.setRowHeight(25);
        call_combo();
        times();
        jDateChooser1.setDate(new Date());
        Tableshow_all();
        Tablegate();
    }
 public void call_combo(){
      DefaultListModel list = new DefaultListModel();
    
    try{
         String sqls="select NAME from  register ";
             
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
            while(rs.next()){
                //String sum=rs.getString("nom");
                String sums=rs.getString("NAME");
                 list.addElement(sums);
                
                 jList1.setModel(list);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
    }
 
  public void search(){
      DefaultListModel list = new DefaultListModel();
    
    try{
         String sqls="select NAME from  register where NAME LIKE '"+search.getText()+"%' ";
             
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
            while(rs.next()){
                //String sum=rs.getString("nom");
                String sums=rs.getString("NAME");
                 list.addElement(sums);
                
                 jList1.setModel(list);
                 
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
          garite_heure.setText(time +"  PM");
         }else{
          heure.setText(time +"  AM");
           garite_heure.setText(time +"  PM");
         }
        
             }
             
         };
         
         timer = new Timer(1000,actionListener); 
timer.setInitialDelay(0);
//jButton1.setEnabled(false);
timer.start();//To change body of generated methods, choose Tools | Templates.
             }
 public void save(){
     
    try{
         String sqls="select * from visiteur where TELEPHONE = '"+tel1.getText()+"' ";
             
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
            if(rs.next()){
             
                 
            }else{
            
                try {
        PreparedStatement pst = con.prepareStatement("INSERT INTO `visiteur`(`NOMS`, `ADRESSE`, `ORGANISATION`, `TELEPHONE`, `TELEPHONE1`, `MAIL`, `FONCTION`)"
        +"value(?,?,?,?,?,?,?)");
        
//`ACTION`, `NOMS`, `FONCTION`, `ROLL`, `MOTIF`, `DESTINATION`, `AUTORISER`, `DATE1`, `DATE2`, `JRS`, `HEURE`, `COMMENTAIRE`, `STATUT` 
        pst.setString(1,noms.getText());
         pst.setString(2,adresse.getText());
        pst.setString(3, org.getText());
         pst.setString(4,tel1.getText());
         pst.setString(5,tel2.getText());
        pst.setString(6, mail.getText());
         pst.setString(7,fonction.getText());
        
       
         
         
          pst.executeUpdate();
        
          //  JOptionPane.showMessageDialog(null,"Data Saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
            
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
     
     
 

}
  public void save_DETAILS(){
 try {
        PreparedStatement pst = con.prepareStatement("INSERT INTO `visiteur_details`(`TEL`, `MOTIFS`, `BADGE_V`, `BADGE`, `DECLARER`, `PRS_A_VISITE`, `HEURE`, `DATES`, `STATUT`)"
        +"value(?,?,?,?,?,?,?,?,?)");
        
//`ACTION`, `NOMS`, `FONCTION`, `ROLL`, `MOTIF`, `DESTINATION`, `AUTORISER`, `DATE1`, `DATE2`, `JRS`, `HEURE`, `COMMENTAIRE`, `STATUT` 
        pst.setString(1,tel1.getText());
         pst.setString(2,motif.getText());
        pst.setString(3, badge_v.getText());
         pst.setString(4,badge.getText());
         pst.setString(5,declaration.getText());
        pst.setString(6,person_a_visite.getText());
         pst.setString(7,heure.getText());
         pst.setString(8, jDateChooser1.getText());
         pst.setString(9,statut.getText());
        
       
         
         
          pst.executeUpdate();
        
            JOptionPane.showMessageDialog(null,"Data Saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }

}
  
  
  
  public void Table()
    {
         try{
           
             String sql="SELECT  TEL,MOTIFS,BADGE_V,BADGE,DECLARER,PRS_A_VISITE,HEURE,DATES,STATUT FROM visiteur_details  WHERE  TEL like '"+tel1.getText()+"%'";
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
    //  TableColumn col9=etjTable3.getColumnModel().getColumn(9);

       
      
       col0.setPreferredWidth(80);
       col1.setPreferredWidth(250);
       col2.setPreferredWidth(100);
       col3.setPreferredWidth(100);
       col4.setPreferredWidth(250);
       col5.setPreferredWidth(100);
       
       col6.setPreferredWidth(50);
       col7.setPreferredWidth(50);
       col8.setPreferredWidth(50);
      // col9.setPreferredWidth(30);
     
      
      
      
       
       
      // jTable1.setModel(mode);
       
     
    }catch(SQLException ex ){
     JOptionPane.showMessageDialog(null, ex);
}
    } 
  
   public void Tableshow_all()
    {
         try{
           
             String sql="SELECT  TEL,MOTIFS,BADGE_V,BADGE,DECLARER,PRS_A_VISITE,HEURE,DATES,STATUT FROM visiteur_details ";
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
    //  TableColumn col9=etjTable3.getColumnModel().getColumn(9);

       
      
       col0.setPreferredWidth(80);
       col1.setPreferredWidth(250);
       col2.setPreferredWidth(100);
       col3.setPreferredWidth(100);
       col4.setPreferredWidth(250);
       col5.setPreferredWidth(100);
       
       col6.setPreferredWidth(50);
       col7.setPreferredWidth(50);
       col8.setPreferredWidth(50);
      // col9.setPreferredWidth(30);
     
      
      
      
       
       
      // jTable1.setModel(mode);
       
     
    }catch(SQLException ex ){
     JOptionPane.showMessageDialog(null, ex);
}
    } 
   
   
    public void Tableshow_resources_humaine()
    {
         try{
           
             String sql="SELECT  `NUM`, `NOMS`, `FONCTION`, `MOTIF`, `DESTINATION`, `AUTORISER`, `DATE1`, `DATE2`, `HEURE` FROM mvm_pers where AUTORISER<> 'Autorisé(e) Par:' and QUERITE_SATATU='NULL'";
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


       
      
       col0.setPreferredWidth(80);
       col1.setPreferredWidth(150);
       col2.setPreferredWidth(100);
       col3.setPreferredWidth(250);
       col4.setPreferredWidth(100);
       col5.setPreferredWidth(150);
       
       col6.setPreferredWidth(50);
       col7.setPreferredWidth(50);
       col8.setPreferredWidth(50);
      // col9.setPreferredWidth(30);
     
      
      
      
       
       
      // jTable1.setModel(mode);
       
     
    }catch(SQLException ex ){
     JOptionPane.showMessageDialog(null, ex);
}
    } 
    
    public void savein_mvt(){
        int row= etjTable3.getSelectedRow();
          String Table_click = (etjTable3.getModel().getValueAt(row,0). toString());
          String ACTION = null, NOMS = null,FONCTION = null,ROLL = null,MOTIF= null,DESTINATION= null,AUTORISER= null,DATE1= null,DATE2= null,JRS= null,TRANS= null,ACCOMP= null,NUM= null,NOM= null;
     try{
        
          String sql = "SELECT * FROM mvm_pers WHERE NUM = '"+Table_click+"'";
          pst = con.prepareStatement(sql);
          rs=pst.executeQuery();
          if(rs.next()){
              ACTION = rs.getString("ACTION");
               NOMS = rs.getString("NOMS");
               FONCTION = rs.getString("FONCTION");
             ROLL = rs.getString("ROLL");
             MOTIF = rs.getString("MOTIF");
            DESTINATION = rs.getString("DESTINATION");
            AUTORISER = rs.getString("AUTORISER");
            DATE1= rs.getString("DATE1");
            DATE2= rs.getString("DATE2");
            JRS= rs.getString("JRS");
           //HEURE = rs.getString("HEURE");
           // COMMENTAIRE= rs.getString("COMMENTAIRE");
          //  STATUT= rs.getString("STATUT");
            NUM= rs.getString("NUM");
           NOM = rs.getString("NOM");
            
             
          }
         
    }catch(SQLException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }
     try {
             SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
        
         
        PreparedStatement pst = con.prepareStatement("INSERT INTO mvm_pers(`ACTION`, `NOMS`, `FONCTION`, `ROLL`, `MOTIF`, `DESTINATION`, `AUTORISER`, `DATE1`, `DATE2`, `JRS`, `HEURE`, `COMMENTAIRE`, `STATUT`, `NUM`,`NOM`,`GARITE_COMM`, `GARITE_HEURE`, `QUERITE_SATATU`,TRANS,ACCOMP) "
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        
//``, ``, ``, ``, ``, ``, ``, ``, ``, ``, ``, ``, `STATUT` 
        pst.setString(1,ACTION);
         pst.setString(2, NOMS);
        pst.setString(3, FONCTION);
         pst.setString(4, ROLL);
         pst.setString(5, MOTIF);
         pst.setString(6, DESTINATION);
          pst.setString(7,AUTORISER);
          pst.setString(8,DATE1);
          pst.setString(9,DATE2);
          pst.setString(10,JRS);
          pst.setString(11,"");
          pst.setString(12,"");
          pst.setString(13,"");
          pst.setString(14, NUM);
         // `GARITE_COMM`, `GARITE_HEURE`, `QUERITE_SATATU`,TRANS,ACCOMP
          pst.setString(15, NOM);
          
           pst.setString(16, garite_commentaire.getText());
           
           
           pst.setString(17, garite_heure.getText());
           pst.setString(18, "YES");
          
           
          pst.setString(19,TRANS);
          pst.setString(20,ACCOMP);
       
         
         
          pst.executeUpdate();
        
              //   JOptionPane.showMessageDialog(null,"CLERCK_IN  SUCCESSFUL BONNE JOURNEE!!!        ");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
     try {
        PreparedStatement pst = con.prepareStatement("UPDATE `mvm_pers` SET QUERITE_SATATU=? WHERE NUM='"+Table_click+"'");
        
  pst.setString(1,"YES");
        
       
         
         
          pst.executeUpdate();
        
              //   JOptionPane.showMessageDialog(null,"CLERCK_IN  SUCCESSFUL BONNE JOURNEE!!!        ");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
    
    }
    
    
    
   public void select_jTabless()
   {
        try{
          int row= etjTable3.getSelectedRow();
          String Table_click = (etjTable3.getModel().getValueAt(row,0). toString());
          String dates = (etjTable3.getModel().getValueAt(row,7). toString());
           String heures = (etjTable3.getModel().getValueAt(row,6). toString());
         
          String sql = "SELECT * FROM visiteur_details WHERE TEL= '"+Table_click+"' AND DATES='"+dates+"' AND HEURE='"+heures+"'";
          pst = con.prepareStatement(sql);
          rs=pst.executeQuery();
          
// ,,,,HEURE,DATES,STATUT 
          if(rs.next()){
              String add1 = rs.getString("MOTIFS");
             motif.setText(add1);
               String add2 = rs.getString("BADGE_V");
             badge_v.setText(add2);
               String add3 = rs.getString("BADGE");
              badge.setText(add3);
               String add4 = rs.getString("DECLARER");
              declaration.setText(add4);
String add5 = rs.getString("PRS_A_VISITE");
          person_a_visite.setText(add5);
          }
         
    }catch(SQLException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }
         try{
          int row= etjTable3.getSelectedRow();
          String Table_click = (etjTable3.getModel().getValueAt(row,0). toString());
         
          String sql = "SELECT * FROM visiteur WHERE TELEPHONE= '"+Table_click+"'";// AND DATES='"+Table_click3+"' AND HEURE='"+Table_click2+"'";
          pst = con.prepareStatement(sql);
          rs=pst.executeQuery();
          
// ,,,,HEURE,DATES,STATUT 
          if(rs.next()){
              String add1 = rs.getString("NOMS");
            noms.setText(add1);
               String add2 = rs.getString("ADRESSE");
             adresse.setText(add2);
               String add3 = rs.getString("ORGANISATION");
              org.setText(add3);
               String add4 = rs.getString("TELEPHONE");
             tel1.setText(add4);
              String add5 = rs.getString("TELEPHONE1");
              tel2.setText(add5);
              String add6 = rs.getString("MAIL");
              mail.setText(add6);
              String add7 = rs.getString("FONCTION");
              fonction.setText(add7);
                  
          }
         
    }catch(SQLException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }
        
              statut.setText("Sortie");
         
   }
   public void select_jTable()
   {
        
             noms.setText("");
             adresse.setText("");
             org.setText("");
             tel2.setText("");
             mail.setText("");
             fonction.setText("");
             person_a_visite.setText("");
       
        try{
        
          String sql = "SELECT * FROM visiteur WHERE TELEPHONE = '"+tel1.getText()+"'";
          pst = con.prepareStatement(sql);
          rs=pst.executeQuery();
          if(rs.next()){
              String add1 = rs.getString("NOMS");
             noms.setText(add1);
               String add2 = rs.getString("ADRESSE");
              adresse.setText(add2);
               String add3 = rs.getString("ORGANISATION");
               org.setText(add3);
              // String add4 = rs.getString("TELEPHONE");
             // tel1.setText(add4);
               String add5 = rs.getString("TELEPHONE1");
               tel2.setText(add5);
               String add6 = rs.getString("MAIL");
              mail.setText(add6);
               String add7 = rs.getString("FONCTION");
             fonction.setText(add7);
             
          }
         
    }catch(SQLException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }
   }
   
   public void garitepass(){
  int row= etjTable3.getSelectedRow();
          String Table_click = (etjTable3.getModel().getValueAt(row,0). toString());
 try {
        PreparedStatement pst = con.prepareStatement("UPDATE `mvm_pers` SET GARITE_COMM=?,GARITE_HEURE=? WHERE NUM='"+Table_click+"'");
        
//`ACTION`, `NOMS`, `FONCTION`, `ROLL`, `MOTIF`, `DESTINATION`, `AUTORISER`, `DATE1`, `DATE2`, `JRS`, `HEURE`, `COMMENTAIRE`, `STATUT` 
       
         pst.setString(1,garite_commentaire.getText());
          pst.setString(2,garite_heure.getText());
         //  pst.setString(3,garite_statut.getText());
        
       
         
         
          pst.executeUpdate();
        
              //   JOptionPane.showMessageDialog(null,"CLERCK_IN  SUCCESSFUL BONNE JOURNEE!!!        ");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }

}
   
   public void savemsg(){
 try {
        PreparedStatement pst = con.prepareStatement("INSERT INTO `message`(`SENDER`, `RECEIVER`, `MESSAGE`,`STATUT`,`LUS`)"
        +"value(?,?,?,?,?)");
        
//`ACTION`, `NOMS`, `FONCTION`, `ROLL`, `MOTIF`, `DESTINATION`, `AUTORISER`, `DATE1`, `DATE2`, `JRS`, `HEURE`, `COMMENTAIRE`, `STATUT` 
        pst.setString(1,homme.user.getText());
         pst.setString(2, person_a_visite.getText());
        pst.setString(3, noms.getText() +" de "+org.getText() +" est Ici a la Guérite"+" pour "+motif.getText());
         pst.setString(4, "NO");
          pst.setString(5, "NO");
        
       
         
         
          pst.executeUpdate();
        
              //   JOptionPane.showMessageDialog(null,"CLERCK_IN  SUCCESSFUL BONNE JOURNEE!!!        ");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }

}
   public void etroll()
     {
         try{
            String sql="SELECT NUM FROM  materiel_gate  ORDER BY NUM DESC LIMIT 1";
            

            
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
                 rolls=stxt+snum ;
                 
                 
                 
             }else{
                 rolls="GT-STOCK-IN/1001";
             }
                   }catch(Exception e){
             JOptionPane.showMessageDialog(null, e); 
             
         }
     }
   public void NUMID()
     {
         try{
            String sql="SELECT NUM_ID FROM  materiel_gate  ORDER BY NUM DESC LIMIT 1";
            

            
             pst=con.prepareStatement(sql);
             rs=pst.executeQuery();
             if(rs.next()){
                 String rl=rs.getString("NUM_ID");
                 int ln=rl.length();
                 String stxt=rl.substring(0,10);
                 String snum=rl.substring(10,ln);
                 int n = Integer.parseInt(snum);
                 n++;
                 snum=Integer.toString(n);
                NUM_ID=stxt+snum ;
                 
                 
                 
             }else{
                 NUM_ID="TRANS No:/1001";
             }
                   }catch(Exception e){
             JOptionPane.showMessageDialog(null, e); 
             
         }
     }
   public void save_stock(){
       etroll();
       NUMID();
       if(num.getText().equals("") && jComboBox3.getSelectedItem().equals("Entré")){
       try {
        PreparedStatement pst = con.prepareStatement("INSERT INTO `materiel_gate`(`ITEM_ID`, `ITEM`, `DESCR`, `DEBIT`, `CREDIT`, `DATES`, `HEURE`, `UNITE`, `RESP`, `NUM`, `NUM_ID`)"
        +"value(?,?,?,?,?,?,?,?,?,?,?)");
        
//`ACTION`, `NOMS`, `FONCTION`, `ROLL`, `MOTIF`, `DESTINATION`, `AUTORISER`, `DATE1`, `DATE2`, `JRS`, `HEURE`, `COMMENTAIRE`, `STATUT` 
        pst.setString(1,item_id.getText());
         pst.setString(2,item.getText());
        pst.setString(3, desc.getText());
         pst.setString(4,qty.getText());
         pst.setString(5,"");
        pst.setString(6,date.getText());
         pst.setString(7,heure.getText());
         pst.setString(8,unite.getSelectedItem().toString());
         pst.setString(9,resp.getText());
         pst.setString(10,rolls);
         pst.setString(11,NUM_ID);
        
       
         
         
          pst.executeUpdate();
        
            JOptionPane.showMessageDialog(null,"Data Saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
       
       }else if(!num.getText().equals("") && jComboBox3.getSelectedItem().equals("Entré")){
       try {
        PreparedStatement pst = con.prepareStatement("INSERT INTO `materiel_gate`(`ITEM_ID`, `ITEM`, `DESCR`, `DEBIT`, `CREDIT`, `DATES`, `HEURE`, `UNITE`, `RESP`, `NUM`, `NUM_ID`)"
        +"value(?,?,?,?,?,?,?,?,?,?,?)");
        
//`ACTION`, `NOMS`, `FONCTION`, `ROLL`, `MOTIF`, `DESTINATION`, `AUTORISER`, `DATE1`, `DATE2`, `JRS`, `HEURE`, `COMMENTAIRE`, `STATUT` 
        pst.setString(1,item_id.getText());
         pst.setString(2,item.getText());
        pst.setString(3, desc.getText());
         pst.setString(4,qty.getText());
         pst.setString(5,"");
        pst.setString(6,date.getText());
         pst.setString(7,heure.getText());
         pst.setString(8,unite.getSelectedItem().toString());
         pst.setString(9,resp.getText());
         pst.setString(10,num.getText());
         pst.setString(11,NUM_ID);
        
       
         
         
          pst.executeUpdate();
        
            JOptionPane.showMessageDialog(null,"Data Saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
       
       }else if(jComboBox3.getSelectedItem().equals("Sortie")){
        try {
        PreparedStatement pst = con.prepareStatement("INSERT INTO `materiel_gate`(`ITEM_ID`, `ITEM`, `DESCR`, `DEBIT`, `CREDIT`, `DATES`, `HEURE`, `UNITE`, `RESP`, `NUM`, `NUM_ID`)"
        +"value(?,?,?,?,?,?,?,?,?,?,?)");
        
//`ACTION`, `NOMS`, `FONCTION`, `ROLL`, `MOTIF`, `DESTINATION`, `AUTORISER`, `DATE1`, `DATE2`, `JRS`, `HEURE`, `COMMENTAIRE`, `STATUT` 
        pst.setString(1,item_id.getText());
         pst.setString(2,item.getText());
        pst.setString(3, desc.getText());
         pst.setString(5,qty.getText());
         pst.setString(4,"");
        pst.setString(6,date.getText());
         pst.setString(7,heure.getText());
         pst.setString(8,unite.getSelectedItem().toString());
         pst.setString(9,resp.getText());
         pst.setString(10,num.getText());
          pst.setString(11,NUM_ID);
        
       
         
         
          pst.executeUpdate();
        
            JOptionPane.showMessageDialog(null,"Data Saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
       
       }
 
 Tablegate();
    item_id.setText("");
     item.setText("");
    desc.setText("");
    qty.setText("");
    unite.setSelectedItem(".....");
    resp.setText("");
    num.setText("");
}
    public void Tablegate()
    {
         try{
           //`ITEM_ID`, `ITEM`, `DESCR`, `DEBIT`, `CREDIT`, `DATES`, `HEURE`, `UNITE`, `RESP`, `NUM`
             String sql="SELECT  ITEM_ID,ITEM,DESCR,DEBIT,CREDIT,UNITE,RESP,NUM_ID,HEURE,DATES FROM materiel_gate ORDER BY NUM,NUM_ID";
     //  String sql="SELECT ID, `NUM_FACTURE`, `DATE_FACTURE`, `DESCRIPTION`, `DEBIT_QTY`, `DEBIT_PU`, `DEBIT_PT`, `DEBIT_TVA`, `CREDIT_QTY`, `CREDIT_PU`, `CREDIT_PT`, `CREDIT_TVA`, `DATES`, `NUM` FROM facture_fournisseur where compte='"+jComboBox6.getSelectedItem()+"'";
       pst = con.prepareStatement(sql);
      rs= pst.executeQuery();
      
       
      jTable1.setModel(DbUtils.resultSetToTableModel(rs));
       mode=new DefaultTableModel();
       
       
        TableColumn col0=jTable1.getColumnModel().getColumn(0);
        TableColumn col1=jTable1.getColumnModel().getColumn(1);
        TableColumn col2=jTable1.getColumnModel().getColumn(2);
        TableColumn col3=jTable1.getColumnModel().getColumn(3);
        TableColumn col4=jTable1.getColumnModel().getColumn(4);
        TableColumn col5=jTable1.getColumnModel().getColumn(5);
       TableColumn col6=jTable1.getColumnModel().getColumn(6);
       TableColumn col7=jTable1.getColumnModel().getColumn(7);
       TableColumn col8=jTable1.getColumnModel().getColumn(8);
      TableColumn col9=jTable1.getColumnModel().getColumn(9);

       
      
       col0.setPreferredWidth(80);
       col1.setPreferredWidth(150);
       col2.setPreferredWidth(150);
       col3.setPreferredWidth(50);
       col4.setPreferredWidth(50);
       col5.setPreferredWidth(20);
       
       col6.setPreferredWidth(100);
       col7.setPreferredWidth(50);
       col8.setPreferredWidth(50);
       col9.setPreferredWidth(80);
     
      
      
      
       
       
      // jTable1.setModel(mode);
       
     
    }catch(SQLException ex ){
     JOptionPane.showMessageDialog(null, ex);
}
    } 
       public void select_jTablesgate()
   {
        try{
          int row= jTable1.getSelectedRow();
          String Table_click = (jTable1.getModel().getValueAt(row,7). toString());
          
         
          String sql = "SELECT * FROM materiel_gate WHERE num_ID= '"+Table_click+"'";
          pst = con.prepareStatement(sql);
          rs=pst.executeQuery();
          
//  ITEM_ID,ITEM,DESCR,DEBIT,CREDIT,UNITE,RESP,NUM,HEURE,DATES FROM materiel_gate
          if(rs.next()){
              String add1 = rs.getString("ITEM_ID");
             item_id.setText(add1);
               String add2 = rs.getString("ITEM");
            item.setText(add2);
               String add3 = rs.getString("DESCR");
              desc.setText(add3);
               String add4 = rs.getString("DEBIT");
              qty.setText(add4);
              
              String add4s = rs.getString("CREDIT");
              qty_out.setText(add4s);
String add5 = rs.getString("UNITE");
          unite.setSelectedItem(add5);
          
          String add3a = rs.getString("DATES");
              date.setText(add3a);
              String add3aa = rs.getString("NUM_ID");
              num.setText(add3aa);
            
          }
         
    }catch(SQLException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }
        
         
   }
       
       public void delete()
    {
     
         try{
        String sql = "DELETE FROM materiel_gate WHERE NUM_ID=?";
        
         pst = con.prepareStatement(sql);
         pst.setString(1,num.getText());
         pst.executeUpdate();
        // JOptionPane.showMessageDialog(null,"delete");
              
     }catch(SQLException | HeadlessException ex ){
         JOptionPane.showMessageDialog(null,ex);
     } 
    }
           public void search_in_list(){
    //deborah 654
     DefaultListModel list = new DefaultListModel();
        String tmp=rech.getText().trim();
         {
        try{
            String sql="select distinct(DESCR) from  materiel_gate  where "+jComboBox1.getSelectedItem()+" like '"+tmp+"%'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                String sum=rs.getString("DESCR");
               // String sums=rs.getString("LNAME");
                 list.addElement(sum);
                
                 jList2.setModel(list);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
    }
    
    }
             public void select_Jlist()
   {
      
       
        String tmp =(String)jList2.getSelectedValue();
   //  String sql="select * from  materiaux where MATR_DESCR=?";
    //  String sql="select ITEM_ID,ITEM,DESCR,sum(debit),sum(credit),UNITY from  materiaux_transaction where DESCR=?";
        try{
           //`ITEM_ID`, `ITEM`, `DESCR`, `DEBIT`, `CREDIT`, `DATES`, `HEURE`, `UNITE`, `RESP`, `NUM`
             String sql="SELECT  ITEM_ID,ITEM,DESCR,DEBIT,CREDIT,UNITE,RESP,NUM,HEURE,DATES FROM materiel_gate where DESCR='"+tmp+"'  ORDER BY NUM,NUM_ID ";
     //  String sql="SELECT ID, `NUM_FACTURE`, `DATE_FACTURE`, `DESCRIPTION`, `DEBIT_QTY`, `DEBIT_PU`, `DEBIT_PT`, `DEBIT_TVA`, `CREDIT_QTY`, `CREDIT_PU`, `CREDIT_PT`, `CREDIT_TVA`, `DATES`, `NUM` FROM facture_fournisseur where compte='"+jComboBox6.getSelectedItem()+"'";
       pst = con.prepareStatement(sql);
      rs= pst.executeQuery();
      
       
      jTable1.setModel(DbUtils.resultSetToTableModel(rs));
       mode=new DefaultTableModel();
       
       
        TableColumn col0=jTable1.getColumnModel().getColumn(0);
        TableColumn col1=jTable1.getColumnModel().getColumn(1);
        TableColumn col2=jTable1.getColumnModel().getColumn(2);
        TableColumn col3=jTable1.getColumnModel().getColumn(3);
        TableColumn col4=jTable1.getColumnModel().getColumn(4);
        TableColumn col5=jTable1.getColumnModel().getColumn(5);
       TableColumn col6=jTable1.getColumnModel().getColumn(6);
       TableColumn col7=jTable1.getColumnModel().getColumn(7);
       TableColumn col8=jTable1.getColumnModel().getColumn(8);
      TableColumn col9=jTable1.getColumnModel().getColumn(9);

       
      
       col0.setPreferredWidth(80);
       col1.setPreferredWidth(150);
       col2.setPreferredWidth(150);
       col3.setPreferredWidth(50);
       col4.setPreferredWidth(50);
       col5.setPreferredWidth(20);
       
       col6.setPreferredWidth(100);
       col7.setPreferredWidth(50);
       col8.setPreferredWidth(50);
       col9.setPreferredWidth(80);
     
      
      
      
       
       
      // jTable1.setModel(mode);
       
     
    }catch(SQLException ex ){
     JOptionPane.showMessageDialog(null, ex);
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
        jScrollPane3 = new javax.swing.JScrollPane();
        jPanel3 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        tel1 = new javax.swing.JTextField();
        noms = new javax.swing.JTextField();
        adresse = new javax.swing.JTextField();
        tel2 = new javax.swing.JTextField();
        mail = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        org = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        fonction = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        search = new javax.swing.JTextField();
        person_a_visite = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        badge_v = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        badge = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        declaration = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        motif = new javax.swing.JTextField();
        jPanel9 = new javax.swing.JPanel();
        jDateChooser1 = new com.alee.extended.date.WebDateField();
        heure = new javax.swing.JTextField();
        statut = new javax.swing.JTextField();
        jButton7 = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        garite_commentaire = new javax.swing.JEditorPane();
        jLabel13 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        garite_heure = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jButton8 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        garite_statut1 = new javax.swing.JTextField();
        garite_statut = new javax.swing.JComboBox<>();
        jPanel6 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        item_id = new javax.swing.JTextField();
        item = new javax.swing.JTextField();
        desc = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        date = new com.alee.extended.date.WebDateField();
        jLabel17 = new javax.swing.JLabel();
        qty = new javax.swing.JTextField();
        unite = new javax.swing.JComboBox<>();
        jSeparator2 = new javax.swing.JSeparator();
        jScrollPane5 = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList<>();
        rech = new javax.swing.JTextField();
        qty_out = new javax.swing.JTextField();
        num = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        resp = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jButton3 = new javax.swing.JButton();
        jComboBox3 = new javax.swing.JComboBox<>();
        jPanel13 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();
        user = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        etjTable3.setBackground(new java.awt.Color(240, 240, 240));
        etjTable3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
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
            public void mousePressed(java.awt.event.MouseEvent evt) {
                etjTable3MousePressed(evt);
            }
        });
        jScrollPane3.setViewportView(etjTable3);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jTabbedPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane1MouseClicked(evt);
            }
        });

        jPanel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel4MouseClicked(evt);
            }
        });

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Enregistrement", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        tel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tel1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tel1KeyReleased(evt);
            }
        });

        noms.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        noms.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        noms.setMinimumSize(new java.awt.Dimension(10, 21));

        adresse.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        adresse.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        adresse.setMinimumSize(new java.awt.Dimension(10, 21));

        tel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tel2.setMinimumSize(new java.awt.Dimension(10, 21));

        mail.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        mail.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        mail.setMinimumSize(new java.awt.Dimension(10, 21));

        jLabel1.setText("Noms");

        jLabel2.setText("Adresse");

        jLabel3.setText("Telephone 2");

        jLabel4.setText("Mail");

        jLabel5.setText("Telephone");

        jLabel7.setText("Org.");

        org.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        org.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        org.setMinimumSize(new java.awt.Dimension(10, 21));

        jLabel8.setText("Fonct.");

        fonction.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        fonction.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        fonction.setMinimumSize(new java.awt.Dimension(10, 21));

        jList1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jList1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jList1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jList1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jList1);

        search.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        search.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        search.setMinimumSize(new java.awt.Dimension(10, 21));
        search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchKeyReleased(evt);
            }
        });

        person_a_visite.setEditable(false);
        person_a_visite.setBackground(new java.awt.Color(240, 240, 241));
        person_a_visite.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        person_a_visite.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        person_a_visite.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        person_a_visite.setMinimumSize(new java.awt.Dimension(10, 21));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                                .addComponent(mail, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(fonction, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE))
                            .addComponent(tel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                                .addComponent(adresse, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(org, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(noms, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tel1, javax.swing.GroupLayout.Alignment.LEADING)))
                    .addComponent(jSeparator1)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(person_a_visite, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tel1, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(noms, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(adresse, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7)
                        .addComponent(org, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tel2, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(mail, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel8)
                        .addComponent(fonction, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(person_a_visite, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "xxxxx", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        badge_v.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        badge_v.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        badge_v.setMinimumSize(new java.awt.Dimension(10, 21));

        jLabel9.setText("No. Badge Visiteur");

        jLabel10.setText("Declaration:");

        jLabel11.setText("No. Badge");

        badge.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        badge.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        badge.setMinimumSize(new java.awt.Dimension(10, 21));

        jButton5.setText("Ok");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        declaration.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        declaration.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        declaration.setMinimumSize(new java.awt.Dimension(10, 21));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setText("Motifs");

        motif.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        motif.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        motif.setMinimumSize(new java.awt.Dimension(10, 21));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(declaration, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(badge, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(badge_v, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(motif, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(216, 216, 216)))))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(motif, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addGap(0, 0, 0)
                .addComponent(badge_v, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11)
                .addGap(0, 0, 0)
                .addComponent(badge, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addGap(0, 0, 0)
                .addComponent(declaration, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jButton5)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Date/Heure", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jDateChooser1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jDateChooser1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jDateChooser1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jDateChooser1ActionPerformed(evt);
            }
        });

        heure.setEditable(false);
        heure.setBackground(new java.awt.Color(240, 240, 241));
        heure.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        heure.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        heure.setText("H Sortie");
        heure.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        statut.setEditable(false);
        statut.setBackground(new java.awt.Color(240, 240, 241));
        statut.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        statut.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        statut.setText("Entrée");
        statut.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        statut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                statutActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(heure, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
            .addComponent(statut)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(statut, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(heure, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2))
        );

        jButton7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton7.setText("Chat");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jPanel11.setBackground(new java.awt.Color(51, 102, 255));

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 28, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(40, Short.MAX_VALUE))
            .addComponent(jPanel11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton7))
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Visiteurs", jPanel4);

        jPanel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel5MouseClicked(evt);
            }
        });

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Adction", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jComboBox2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select...", "Resources Humaines", "Materiaux", "Materiel", "Engin" }));
        jComboBox2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jComboBox2.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                jComboBox2PopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        garite_commentaire.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        garite_commentaire.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jScrollPane1.setViewportView(garite_commentaire);

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel13.setText("Commentaire");

        jButton6.setText("Ok");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        garite_heure.setEditable(false);
        garite_heure.setBackground(new java.awt.Color(240, 240, 241));
        garite_heure.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        garite_heure.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        garite_heure.setText("00:00:00 AM");

        jTextField2.setEditable(false);
        jTextField2.setBackground(new java.awt.Color(240, 240, 241));
        jTextField2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTextField2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField2.setText("10.10.2020");

        jButton8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton8.setText("Chat");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jTextField1.setText("jTextField1");

        garite_statut1.setEditable(false);
        garite_statut1.setBackground(new java.awt.Color(240, 240, 241));
        garite_statut1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        garite_statut1.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        garite_statut.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        garite_statut.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Sortie", "Entrée" }));
        garite_statut.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel10Layout.createSequentialGroup()
                                    .addComponent(garite_heure)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(garite_statut, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 112, Short.MAX_VALUE))
                            .addComponent(garite_statut1))))
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(garite_heure, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(garite_statut1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(garite_statut, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton6))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(451, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Check IN", jPanel5);

        jPanel12.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel12.setText("Num_id");

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel14.setText("Item");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel15.setText("Desc.");

        item_id.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        item_id.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        item_id.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                item_idKeyReleased(evt);
            }
        });

        item.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        item.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        item.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                itemKeyReleased(evt);
            }
        });

        desc.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        desc.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel16.setText("Date");

        date.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel17.setText("Qty.");

        qty.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        qty.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        unite.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        unite.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "....." }));
        unite.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jList2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jList2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jList2MouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(jList2);

        rech.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        rech.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        rech.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        rech.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rechActionPerformed(evt);
            }
        });
        rech.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                rechKeyReleased(evt);
            }
        });

        qty_out.setEditable(false);
        qty_out.setBackground(new java.awt.Color(240, 240, 241));
        qty_out.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        qty_out.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        qty_out.setText("Qty Out");

        num.setEditable(false);
        num.setBackground(new java.awt.Color(240, 240, 241));
        num.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        num.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton1.setText("Save");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton2.setText("Delete");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        resp.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        resp.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel18.setText("Resp.");

        jComboBox1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item_id", "Item", "Descr", "Dates" }));

        jButton3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton3.setText("New");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jComboBox3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Entré", "Sortie" }));
        jComboBox3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(num))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)
                            .addComponent(qty_out)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rech))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addGap(126, 126, 126)
                                .addComponent(jLabel17)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(qty, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(unite, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addGap(13, 13, 13)
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(resp, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(desc)
                                    .addComponent(item)
                                    .addGroup(jPanel12Layout.createSequentialGroup()
                                        .addComponent(date, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(jPanel12Layout.createSequentialGroup()
                                        .addComponent(item_id)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(item_id, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(item, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(desc, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16)
                    .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(date, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel17)
                        .addComponent(qty, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(unite, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18)
                    .addComponent(resp, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 7, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rech, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(qty_out, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(num, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addContainerGap())
        );

        jPanel13.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTable1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
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
        jScrollPane4.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 636, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Materiels/Materiaux IN/OUT", jPanel6);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        user.setText("File");
        jMenuBar1.add(user);

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
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tel1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tel1KeyReleased
garite_statut1.setText("");
        select_jTable();  
Table();// TODO add your handling code here:
    }//GEN-LAST:event_tel1KeyReleased

    private void jDateChooser1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jDateChooser1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jDateChooser1ActionPerformed

    private void etjTable3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_etjTable3MousePressed
//select_jTabless();        //doubleclick();        // TODO add your handling code here:
    }//GEN-LAST:event_etjTable3MousePressed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
save();
        save_DETAILS();  
savemsg();
//Table();
Tableshow_all();
 noms.setText("");
             tel1.setText("");
             org.setText("");
             tel2.setText("");
             mail.setText("");
             fonction.setText("");
             person_a_visite.setText("");
             
motif.setText("");
            badge_v.setText("");
             badge.setText("");
            declaration.setText("");
             statut.setText("Entrée");
             adresse.setText("");
             // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void statutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_statutActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_statutActionPerformed

    private void jComboBox2PopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_jComboBox2PopupMenuWillBecomeInvisible
Tableshow_resources_humaine();
garite_statut1.setText("Active");// TODO add your handling code here:
    }//GEN-LAST:event_jComboBox2PopupMenuWillBecomeInvisible

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
if(garite_statut.getSelectedItem().equals("Sortie")){
 garitepass();
}else{  
savein_mvt();
}

Tableshow_resources_humaine(); // TODO add your handling code here:
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jTabbedPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane1MouseClicked
garite_statut1.setText("");       // TODO add your handling code here:
    }//GEN-LAST:event_jTabbedPane1MouseClicked

    private void jPanel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel5MouseClicked
Tableshow_resources_humaine();         // TODO add your handling code here:
    }//GEN-LAST:event_jPanel5MouseClicked

    private void jPanel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel4MouseClicked
Tableshow_all();         // TODO add your handling code here:
    }//GEN-LAST:event_jPanel4MouseClicked

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
chat m = new chat();
        m.show();
        m. setDefaultCloseOperation(DISPOSE_ON_CLOSE);         // TODO add your handling code here:
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
  chat m = new chat();
        m.show();
        m. setDefaultCloseOperation(DISPOSE_ON_CLOSE);       // TODO add your handling code here:
    }//GEN-LAST:event_jButton8ActionPerformed

    private void searchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchKeyReleased
search();        // TODO add your handling code here:
    }//GEN-LAST:event_searchKeyReleased

    private void etjTable3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_etjTable3MouseClicked
select_jTabless();        // TODO add your handling code here:
    }//GEN-LAST:event_etjTable3MouseClicked

    private void jList1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList1MouseClicked
person_a_visite.setText(jList1.getSelectedValue());        // TODO add your handling code here:
    }//GEN-LAST:event_jList1MouseClicked

    private void item_idKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_item_idKeyReleased
//        Check();
//        if(Check.isEmpty())
//        {}else{
//            JOptionPane.showMessageDialog(null,"Wrong Data Item Exists","Error",JOptionPane.ERROR_MESSAGE);
//            item_id.setText("");
//            Check="";
//        }// TODO add your handling code here:
    }//GEN-LAST:event_item_idKeyReleased

    private void itemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_itemKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_itemKeyReleased

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
      select_jTablesgate();
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable1MouseClicked

    private void jList2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList2MouseClicked
select_Jlist();        // TODO add your handling code here:
    }//GEN-LAST:event_jList2MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
save_stock();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void rechActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rechActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rechActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
delete();
Tablegate();// TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void rechKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_rechKeyReleased
search_in_list();        // TODO add your handling code here:
    }//GEN-LAST:event_rechKeyReleased

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
    item_id.setText("");
     item.setText("");
    desc.setText("");
    qty.setText("");
    qty_out.setText("");
    unite.setSelectedItem(".....");
    resp.setText("");
    num.setText("");        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

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
            java.util.logging.Logger.getLogger(guerite.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(guerite.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(guerite.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(guerite.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                 WebLookAndFeel.install(true);
                new guerite().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField adresse;
    private javax.swing.JTextField badge;
    private javax.swing.JTextField badge_v;
    private com.alee.extended.date.WebDateField date;
    private javax.swing.JTextField declaration;
    private javax.swing.JTextField desc;
    public static final javax.swing.JTable etjTable3 = new javax.swing.JTable();
    private javax.swing.JTextField fonction;
    private javax.swing.JEditorPane garite_commentaire;
    private javax.swing.JTextField garite_heure;
    private javax.swing.JComboBox<String> garite_statut;
    private javax.swing.JTextField garite_statut1;
    private javax.swing.JTextField heure;
    private javax.swing.JTextField item;
    private javax.swing.JTextField item_id;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private com.alee.extended.date.WebDateField jDateChooser1;
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
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList<String> jList1;
    private javax.swing.JList<String> jList2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField mail;
    private javax.swing.JTextField motif;
    private javax.swing.JTextField noms;
    private javax.swing.JTextField num;
    private javax.swing.JTextField org;
    private javax.swing.JTextField person_a_visite;
    private javax.swing.JTextField qty;
    private javax.swing.JTextField qty_out;
    private javax.swing.JTextField rech;
    private javax.swing.JTextField resp;
    private javax.swing.JTextField search;
    private javax.swing.JTextField statut;
    private javax.swing.JTextField tel1;
    private javax.swing.JTextField tel2;
    private javax.swing.JComboBox<String> unite;
    private javax.swing.JMenu user;
    // End of variables declaration//GEN-END:variables
}
