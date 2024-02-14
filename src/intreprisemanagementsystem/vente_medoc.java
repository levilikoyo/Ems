/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intreprisemanagementsystem;

import static com.alee.extended.time.ClockType.timer;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author DOSHE
 */
public class vente_medoc extends javax.swing.JInternalFrame {

    private Connection con;
private Statement st;
PreparedStatement pst=null;
ResultSet rs= null;
String rolls;
String NUM_ID;
 DefaultTableModel mode;
 String ID=null;
  String IDS=null;
  String Total=null;
  String MAX=null;
   Timer timer;
   String COMPTE_MANA;
    public vente_medoc() {
        initComponents();
         this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI bui= (BasicInternalFrameUI) this.getUI();
       bui.setNorthPane(null);
     
      SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
     //  DateFormat dateFormat = DateFormat.getDateInstance(partener);
String date = simpleDateFormat.format(new Date());

       dates.setText(date);
    lock_employee();
       
    }
    
   

      // dates.setText(date);
     String url="jdbc:sqlite:kavira.db";
    //CONNECTION
     
       public void roll()
     {
         try{
                 Class.forName("org.sqlite.JDBC");
           con= DriverManager.getConnection(url);
            String sql="SELECT NUM FROM  h_p_vente_medoc ORDER BY NUM DESC LIMIT 1";
             pst=con.prepareStatement(sql);
             rs=pst.executeQuery();
             if(rs.next()){
                 String rl=rs.getString("NUM");
                 int ln=rl.length();
                 String stxt=rl.substring(0,9);
                 String snum=rl.substring(9,ln);
                 int n = Integer.parseInt(snum);
                 n++;
                 snum=Integer.toString(n);
                 rolls=stxt+snum;
                 
                 
                  pst.close();
            rs.close();
                con.close();
             }else{
                 //rolls="FICHE/EB/2018/1";
                 rolls="MEDI-OUT/1001";
             }
                 
             
         }catch(Exception e){
             JOptionPane.showMessageDialog(null, e); 
             
         }
     }
      
       public void rollS()
     {
         try{
                 Class.forName("org.sqlite.JDBC");
           con= DriverManager.getConnection(url);
            String sql="SELECT NUM_ID FROM  h_p_vente_medoc ORDER BY NUM_ID DESC LIMIT 1";
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
                 NUM_ID=stxt+snum;
                 
                 
                  pst.close();
            rs.close();
                con.close();
             }else{
                 //rolls="FICHE/EB/2018/1";
                 NUM_ID="TRANS-OUT/1001";
             }
                 
             
         }catch(Exception e){
             JOptionPane.showMessageDialog(null, e); 
             
         }
     }
     
 public void Search2(){
         
          try{
              DefaultListModel list = new DefaultListModel();
             Class.forName("org.sqlite.JDBC");
          String sql="SELECT * FROM h_p_medicament where NOMS LIKE '"+search_medoc.getText()+"%'";
             con = DriverManager.getConnection(url);
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                //String sum=rs.getString("nom");
                String sums=rs.getString("NOMS");
                 list.addElement(sums);
                
                 jList1.setModel(list);
            }
             pst.close();
            rs.close();
              
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
             
         }
  String CAT,CLASSI;
  public void JLIST_from_COMPTE2()
    {
      
         String tmp =(String)jList1.getSelectedValue();
    
     
        try{
              Class.forName("org.sqlite.JDBC");
            String sql="SELECT * FROM   h_p_medicament Where NOMS='"+tmp+"'";
            con = DriverManager.getConnection(url);
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                String sums1=rs.getString("MARK");
                jComboBox2.removeAllItems();
                  CAT=rs.getString("CATEGORI");
                    CLASSI=rs.getString("CLASSI");           
            }
             pst.close();
            rs.close();
               
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
      try{
              Class.forName("org.sqlite.JDBC");
            String sql="SELECT SUM(QTY_DEBIT),SUM(QTY_CREDIT) FROM   h_p_inventaire_medoc Where MEDICAMENT='"+tmp+"'";
            con = DriverManager.getConnection(url);
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                Double sums1=rs.getDouble("SUM(QTY_DEBIT)");
                Double sums2=rs.getDouble("SUM(QTY_CREDIT)");
                Double c;
                c=sums1-sums2;
                qty_s.setText(""+c);
            
                      
            }
             pst.close();
            rs.close();
               
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
    
        }
  
   public void JLIST_from_COMPTE2S()
    {
      
        // String tmp =(String)jList1.getSelectedValue();
          int row= jTable6.getSelectedRow();
          String Table_clickss = (jTable6.getModel().getValueAt(row,0). toString());
    
     
        try{
              Class.forName("org.sqlite.JDBC");
            String sql="SELECT * FROM   h_p_medicament Where NOMS='"+Table_clickss+"'";
            con = DriverManager.getConnection(url);
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                String sums1=rs.getString("MARK");
                jComboBox2.removeAllItems();
                  CAT=rs.getString("CATEGORI");
                    CLASSI=rs.getString("CLASSI");           
            }
             pst.close();
            rs.close();
               
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
      try{
              Class.forName("org.sqlite.JDBC");
            String sql="SELECT SUM(QTY_DEBIT),SUM(QTY_CREDIT) FROM   h_p_inventaire_medoc Where MEDICAMENT='"+Table_clickss+"'";
            con = DriverManager.getConnection(url);
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                Double sums1=rs.getDouble("SUM(QTY_DEBIT)");
                Double sums2=rs.getDouble("SUM(QTY_CREDIT)");
                Double c;
                c=sums1-sums2;
                qty_s.setText(""+c);
            
                      
            }
             pst.close();
            rs.close();
               
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
    
        }
  
  public void select_type1(){
      pv.setText("");
   
        String tmp =null;
       if(jLabel21.getText().equals("JLIST")){
       tmp =(String)jList1.getSelectedValue();
       }else if(jLabel21.getText().equals("TABLE")){
        int row= jTable6.getSelectedRow();
          tmp = (jTable6.getModel().getValueAt(row,0). toString());
       
       }
  if(jComboBox1.getSelectedItem().equals("Gros")){
     try{
              Class.forName("org.sqlite.JDBC");
            String sql="SELECT * FROM   h_p_medicament Where NOMS='"+tmp+"'";
            con = DriverManager.getConnection(url);
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                String sums11=rs.getString("CONTAINER");
                jComboBox2.removeAllItems();
                jComboBox2.addItem(sums11);
                jComboBox2.setSelectedItem(sums11);
                 
               // String sum = rs.getString("MARK_QTY");
                detai1.setText("1");
                
                String sums1=rs.getString("PVG_$");
                pv.setText(sums1);
                
            }
             pst.close();
            rs.close();
               
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
           try{
              Class.forName("org.sqlite.JDBC");
            String sql="SELECT SUM(QTY_DEBIT),SUM(QTY_CREDIT) FROM   h_p_inventaire_medoc Where MEDICAMENT='"+tmp+"'";
            con = DriverManager.getConnection(url);
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
       
                 Double sums1=rs.getDouble("SUM(QTY_DEBIT)");
                Double sums2=rs.getDouble("SUM(QTY_CREDIT)");
                
           
                 Double c,cc;
                 c=sums1-sums2;
            
                 
               qty_s.setText(""+c); 
               detai1.setText(""+c);
            }
             pst.close();
            rs.close();
               
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
          
          
  }else if(jComboBox1.getSelectedItem().equals("Detail")){
       Double MARK_QTY=null;
       Double CONTAINER_QTY=null;
   try{
              Class.forName("org.sqlite.JDBC");
            String sql="SELECT * FROM   h_p_medicament Where NOMS='"+tmp+"'";
            con = DriverManager.getConnection(url);
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                String sums11=rs.getString("MARK");
                jComboBox2.removeAllItems();
                jComboBox2.addItem(sums11);
                jComboBox2.setSelectedItem(sums11);
              
     //   NOM,NOMS,CLASSI,CATEGORI,P_ACHAT,P_VENTE,D_UNITE,P_ACHAT_G,P_VENTE_G,G_VENTE,NUM,MARK,MARK_QTY,CONTAINER,CONTAINER_QTY,QTY_UNITE,VAL_UNITE,PV_$,PVG_$,PA_$,PAG_$        
                String sums1=rs.getString("PV_$");
                pv.setText(sums1);
                
              MARK_QTY =rs.getDouble("MARK_QTY");
              CONTAINER_QTY=rs.getDouble("CONTAINER_QTY");
               detai1.setText(""+MARK_QTY);
                
            }
             pst.close();
            rs.close();
               
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
  try{
              Class.forName("org.sqlite.JDBC");
            String sql="SELECT SUM(QTY_DEBIT),SUM(QTY_CREDIT) FROM   h_p_inventaire_medoc Where MEDICAMENT='"+tmp+"'";
            con = DriverManager.getConnection(url);
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
              
     //   NOM,NOMS,CLASSI,CATEGORI,P_ACHAT,P_VENTE,D_UNITE,P_ACHAT_G,P_VENTE_G,G_VENTE,NUM,MARK,MARK_QTY,CONTAINER,CONTAINER_QTY,QTY_UNITE,VAL_UNITE,PV_$,PVG_$,PA_$,PAG_$        
                /*String sums11=rs.getString("MARK");
                jComboBox2.removeAllItems();
                jComboBox2.addItem(sums11);
                jComboBox2.setSelectedItem(sums11);
                */
                 Double sums1=rs.getDouble("SUM(QTY_DEBIT)");
                Double sums2=rs.getDouble("SUM(QTY_CREDIT)");
                
               // Double a = Double.parseDouble(qty_s.getText());
                //Double v = Double.parseDouble(pv.getText());
                 Double c,cc,C;
                cc=(sums1-sums2)*CONTAINER_QTY;
                c=cc/MARK_QTY;
                 C=sums1-sums2;
                 
                 //cc=v/sums1;
                detai1.setText(""+C);
               qty_s.setText(""+c); 
            }
             pst.close();
            rs.close();
               
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
  }else{
 pv.setText("");
  }
  
  }
  
  public void type2(){
    String tmp =(String)jList1.getSelectedValue();
  try{
              Class.forName("org.sqlite.JDBC");
            String sql="SELECT distinct(MARK),MARK_QTY,CONTAINER_QTY FROM   h_p_medicament Where NOMS='"+tmp+"' AND ";
            con = DriverManager.getConnection(url);
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
              
     //   NOM,NOMS,CLASSI,CATEGORI,P_ACHAT,P_VENTE,D_UNITE,P_ACHAT_G,P_VENTE_G,G_VENTE,NUM,MARK,MARK_QTY,CONTAINER,CONTAINER_QTY,QTY_UNITE,VAL_UNITE,PV_$,PVG_$,PA_$,PAG_$        
               Double sums1=rs.getDouble("MARK_QTY");
                Double sums2=rs.getDouble("CONTAINER_QTY");
                
                Double a = Double.parseDouble(qty_s.getText());
                Double v = Double.parseDouble(pv.getText());
                 Double c,cc;
                 c=a*sums2;
                 cc=v/sums1;
                 
               qty_s.setText(""+c);  
                 
                
            }
             pst.close();
            rs.close();
               
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
  
  
  }
  
   public void SELECT_MAX(){
   try{
              Class.forName("org.sqlite.JDBC");
            String sql="SELECT MAX(NUM) FROM  h_p_vente_medoc";
            con = DriverManager.getConnection(url);
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
              
     //   NOM,NOMS,CLASSI,CATEGORI,P_ACHAT,P_VENTE,D_UNITE,P_ACHAT_G,P_VENTE_G,G_VENTE,NUM,MARK,MARK_QTY,CONTAINER,CONTAINER_QTY,QTY_UNITE,VAL_UNITE,PV_$,PVG_$,PA_$,PAG_$        
              MAX=rs.getString("MAX(NUM)");
              num_fiche.setText(MAX);
                
                
               
                  
                      
            }
             pst.close();
            rs.close();
               
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
  
  }
  public void SELECT_SUM(){
        Double   MAXs = null,CDF=null;
   try{
              Class.forName("org.sqlite.JDBC");
            String sql="SELECT SUM(CREDIT) FROM   h_p_inventaire_medoc WHERE NUM='"+num_fiche.getText()+"'";
            con = DriverManager.getConnection(url);
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
        //     Double a= Double.parseDouble(total.getText());
     //   NOM,NOMS,CLASSI,CATEGORI,P_ACHAT,P_VENTE,D_UNITE,P_ACHAT_G,P_VENTE_G,G_VENTE,NUM,MARK,MARK_QTY,CONTAINER,CONTAINER_QTY,QTY_UNITE,VAL_UNITE,PV_$,PVG_$,PA_$,PAG_$        
             MAXs=rs.getDouble("SUM(CREDIT)");
           DecimalFormat df = new DecimalFormat("USD #,##0.00");
       
    total.setText(""+df.format(MAXs));   
                      
            }
           
               
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
  
  }
  Double QTY=null;
   public void  calculeqty(){
  try{
      Double CONT,MARK,C;
              Class.forName("org.sqlite.JDBC");
            String sql="SELECT * FROM   h_p_medicament Where NOMS='"+jList1.getSelectedValue()+"'";
            con = DriverManager.getConnection(url);
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                CONT=rs.getDouble("CONTAINER_QTY");
              
                
                 MARK=rs.getDouble("MARK_QTY");
    Double QTY_S = Double.parseDouble(qty_s.getText());
       Double BB = Double.parseDouble(qty.getText());
       Double BBB = Double.parseDouble(detai1.getText());
       C=((QTY_S*MARK)-BB)/CONT;
      QTY=BBB-C; //Total=c.toString();  
                      
            }
             pst.close();
            rs.close();
               
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }}
    
  // CREATE TABLE "h_p_vente_medoc" (,MEDICAMENT,CAT,CLASSI,CLIENT,P_VENTE,QTY,P_TOTAL,DATES,NUM_ID,NUM"	TEXT NOT NULL
   public void save(){
       calculeqty();
       String tmp =null;
       if(jLabel21.getText().equals("JLIST")){
       tmp =(String)jList1.getSelectedValue();
       }else if(jLabel21.getText().equals("TABLE")){
        int row= jTable6.getSelectedRow();
          tmp = (jTable6.getModel().getValueAt(row,0). toString());
       
       }
       try{
              Class.forName("org.sqlite.JDBC");
            String sql="SELECT * FROM   h_p_medicament Where NOMS='"+tmp+"'";
            con = DriverManager.getConnection(url);
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
              
     //   NOM,NOMS,CLASSI,CATEGORI,P_ACHAT,P_VENTE,D_UNITE,P_ACHAT_G,P_VENTE_G,G_VENTE,NUM,MARK,MARK_QTY,CONTAINER,CONTAINER_QTY,QTY_UNITE,VAL_UNITE,PV_$,PVG_$,PA_$,PAG_$        
              
                  CAT=rs.getString("CATEGORI");
                    CLASSI=rs.getString("CLASSI");
             //  jComboBox2.addItem(sums1);
          //     jComboBox2.setSelectedItem(sums1);
                
              /*  String sums2=rs.getString("CONTAINER");
                marc.removeAllItems();
                marc.addItem(sums2);
                marc.setSelectedItem(sums2);
                
                 String sums22=rs.getString("CATEGORI");
                forme.setText(sums22);
                */
                  
                      
            }
             pst.close();
            rs.close();
               
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
         Double aa = Double.parseDouble(qty.getText());
       Double bb = Double.parseDouble(pv.getText());
       Double c;
       c=aa*bb;
       Total=c.toString();
       rollS();
       ohada_save();
       if(num_fiche.getText().equals("")){
        roll();
               try{
          Class.forName("org.sqlite.JDBC");
           con= DriverManager.getConnection(url);
     
         // java.sql.Connection con = getConnection();
     String sql="INSERT INTO h_p_vente_medoc (MEDICAMENT,CAT,CLASSI,CLIENT,P_VENTE,QTY,P_TOTAL,DATES,NUM_ID,NUM,QTY_D)"+
             "VALUES(?,?,?,?,?,?,?,?,?,?,?)";
     pst=con.prepareStatement(sql);
    
         pst.setString(1, tmp);
      pst.setString(2, CAT);
      pst.setString(3, CLASSI);
      pst.setString(4, client.getText());
      pst.setString(5, pv.getText());  
      pst.setString(6, qty.getText());
      pst.setString(7, Total);
      pst.setString(8, dates.getText());
      pst.setString(9, NUM_ID);
      pst.setString(10, rolls);
      pst.setString(11, jComboBox2.getSelectedItem().toString());
    
      pst.executeUpdate();
                con.close();
              
  // JOptionPane.showMessageDialog(null,"Transaction Saved");
      
   //  cat1.setText("");
      
     }catch(Exception ex){JOptionPane.showMessageDialog(null,ex);} 
                 try{
          Class.forName("org.sqlite.JDBC");
           con= DriverManager.getConnection(url);
     
         ////   h_p_inventaire_medoc NUM_ID,CAT,CLASSI,QTY_DEBIT,QTY_CREDIT,DEBIT,CREDIT,NUM
     String sql="INSERT INTO  h_p_inventaire_medoc (MEDICAMENT,CAT,CLASSI,QTY_DEBIT,QTY_CREDIT,DEBIT,CREDIT,NUM_ID,NUM,FOUR,STATUT,QTY_DD,QTY_DC)"+
             "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
     pst=con.prepareStatement(sql);
    
      pst.setString(1, tmp);
      pst.setString(2, CAT);
      pst.setString(3, CLASSI);
     
      pst.setString(4,"");
      
      pst.setString(13,qty.getText());
      pst.setString(7, Total);
      pst.setString(6, "");
      pst.setString(8, NUM_ID);
        pst.setString(9, rolls);
      pst.setString(10, client.getText());
      pst.setString(11,"Vente");
      pst.setDouble(5,QTY);
      pst.setString(12,"");
    
   
      
      pst.executeUpdate();
                con.close();
      
     }catch(Exception ex){JOptionPane.showMessageDialog(null,ex);}  
               SELECT_MAX();
             }else{
             try{
          Class.forName("org.sqlite.JDBC");
           con= DriverManager.getConnection(url);
     
         // java.sql.Connection con = getConnection();
     String sql="INSERT INTO h_p_vente_medoc (MEDICAMENT,CAT,CLASSI,CLIENT,P_VENTE,QTY,P_TOTAL,DATES,NUM_ID,NUM,QTY_D)"+
             "VALUES(?,?,?,?,?,?,?,?,?,?,?)";
     pst=con.prepareStatement(sql);
    
         pst.setString(1, tmp);
      pst.setString(2, CAT);
      pst.setString(3, CLASSI);
      pst.setString(4, client.getText());
      pst.setString(5, pv.getText());  
      pst.setString(6, qty.getText());
      pst.setString(7, Total);
      pst.setString(8, dates.getText());
      pst.setString(9, NUM_ID);
      pst.setString(10, num_fiche.getText());
       pst.setString(11, jComboBox2.getSelectedItem().toString());
    
      pst.executeUpdate();
                con.close();
              
  // JOptionPane.showMessageDialog(null,"Transaction Saved");
      
   //  cat1.setText("");
      
     }catch(Exception ex){JOptionPane.showMessageDialog(null,ex);} 
                 try{
          Class.forName("org.sqlite.JDBC");
           con= DriverManager.getConnection(url);
     
         ////   h_p_inventaire_medoc NUM_ID,CAT,CLASSI,QTY_DEBIT,QTY_CREDIT,DEBIT,CREDIT,NUM
     String sql="INSERT INTO  h_p_inventaire_medoc (MEDICAMENT,CAT,CLASSI,QTY_DEBIT,QTY_CREDIT,DEBIT,CREDIT,NUM_ID,NUM,FOUR,STATUT,QTY_DD,QTY_DC)"+
             "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
     pst=con.prepareStatement(sql);
    
      pst.setString(1, tmp);
      pst.setString(2, CAT);
      pst.setString(3, CLASSI);
     
      pst.setString(4,"");
      
      pst.setString(13,qty.getText());
      pst.setString(7, Total);
      pst.setString(6, "");
      pst.setString(8, NUM_ID);
        pst.setString(9,num_fiche.getText());
      pst.setString(10, client.getText());
      pst.setString(11,"Vente");
        pst.setString(12,"");
          pst.setDouble(5,QTY);
    
   
      
      pst.executeUpdate();
                con.close();
      
     }catch(Exception ex){JOptionPane.showMessageDialog(null,ex);} 
       }SELECT_SUM();
   
   
   }
       String COMPTE=null,SUBSTR=null,CLASSE=null,COMPTE_M=null,CODE_M=null,CODE=null,CODEC=null,SUBSTRC=null,CODE_MC=null,COMPTE_MC=null,CODEB=null,SUBSTRB=null,CODE_MB=null,COMPTE_MB=null,COMPTEB=null;
      public void SELECT_buss()
    {
       
        
         
        try{
              Class.forName("org.sqlite.JDBC");
            String sql="SELECT * FROM   compte_mana where compte='"+jList1.getSelectedValue()+"'";
            con = DriverManager.getConnection(url);
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                CODE_M=rs.getString("CODE_M"); 
                CODE=rs.getString("CODE");
                COMPTE=rs.getString("COMPTE");
                  CLASSE=rs.getString("CLASSE");
                   SUBSTR =rs.getString("SUBSTR");
                   COMPTE_M =rs.getString("COMPTE_M");
                  // barcode.setText(COMPTE_M);
            }
              pst.close();
            rs.close();
                con.close();
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
     try{
              Class.forName("org.sqlite.JDBC");
            String sql="SELECT * FROM   compte_mana where compte='"+departement.getText()+"'";
            con = DriverManager.getConnection(url);
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                CODE_MC=rs.getString("CODE_M"); 
                CODEC=rs.getString("CODE");
                //COMPTEC=rs.getString("COMPTE");
                  //CLASSEC=rs.getString("CLASSE");
                   SUBSTRC =rs.getString("SUBSTR");
                   COMPTE_MC =rs.getString("COMPTE_M");
            }
              pst.close();
            rs.close();
                con.close();
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
    try{
              Class.forName("org.sqlite.JDBC");
            String sql="SELECT * FROM   compte_mana where SUBSTR='57 PH'";
            con = DriverManager.getConnection(url);
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                CODE_MB=rs.getString("CODE_M"); 
                CODEB=rs.getString("CODE");
                COMPTEB=rs.getString("COMPTE");
                  //CLASSEC=rs.getString("CLASSE");
                   SUBSTRB =rs.getString("SUBSTR");
                   COMPTE_MB =rs.getString("COMPTE_M");
            }
              pst.close();
            rs.close();
                con.close();
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
    }
  public void ohada_save(){
                SELECT_buss();
          //---->>> OHADA CREDIT 70
          
               try{
          Class.forName("org.sqlite.JDBC");
           con= DriverManager.getConnection(url);
     String sql="INSERT INTO OHADA (COMPTE_M,COMPTE,CODE_M,CODE,SUBSTR,CLASSE,DEBIT,CREDIT,NUM,DATES,BUSS,LIBELLE,JOURNAL)"+
             "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
     pst=con.prepareStatement(sql);
      pst.setString(1, COMPTE_M);
         pst.setString(2,COMPTE);
          pst.setString(3,CODE_M);
           pst.setString(4,CODE);
           
            pst.setString(5,SUBSTR);
          pst.setString(6,CLASSE);
           pst.setString(7,Total);
            pst.setString(8,"0.00");
            
            
            pst.setString(9,num_fiche.getText());
          pst.setString(10,dates.getText());
           pst.setString(11,"PHARMACIE");
           pst.setString(12,jList1.getSelectedValue());
             pst.setString(13,"[SJ]");
           
           pst.executeUpdate();
                 pst.close();
            rs.close();
                con.close(); 
     }catch(Exception ex){JOptionPane.showMessageDialog(null,ex);}
               
                //---->>> OHADA DEBIT 41 NA
          
               try{
          Class.forName("org.sqlite.JDBC");
           con= DriverManager.getConnection(url);
     String sql="INSERT INTO OHADA (COMPTE_M,COMPTE,CODE_M,CODE,SUBSTR,CLASSE,DEBIT,CREDIT,NUM,DATES,BUSS,LIBELLE,JOURNAL)"+
             "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
     pst=con.prepareStatement(sql);
     //COMPTE_MS,CLASSES,SUBSTRS;
     //String value1=jComboBox1.getSelectedItem().toString();
      pst.setString(1, COMPTE_MC);
         pst.setString(2,departement.getText());
          pst.setString(3,CODE_MC);
           pst.setString(4,CODEC);
           
            pst.setString(5,SUBSTRC);
          pst.setString(6,"4");
           pst.setString(7,Total);
            pst.setString(8,"0.00");
            
            
            pst.setString(9,num_fiche.getText());
          pst.setString(10,dates.getText());
           pst.setString(11,"PHARMACIE");
           pst.setString(12,jList1.getSelectedValue());
             pst.setString(13,"[SJ]");
           
           pst.executeUpdate();
                 pst.close();
            rs.close();
                con.close(); 
     }catch(Exception ex){JOptionPane.showMessageDialog(null,ex);}
               
                //---->>> OHADA CREDIT 41 NA
          
               try{
          Class.forName("org.sqlite.JDBC");
           con= DriverManager.getConnection(url);
     String sql="INSERT INTO OHADA (COMPTE_M,COMPTE,CODE_M,CODE,SUBSTR,CLASSE,DEBIT,CREDIT,NUM,DATES,BUSS,LIBELLE,JOURNAL)"+
             "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
     pst=con.prepareStatement(sql);
     //COMPTE_MS,CLASSES,SUBSTRS;
     //String value1=jComboBox1.getSelectedItem().toString();
      pst.setString(1, COMPTE_MC);
         pst.setString(2,departement.getText());
          pst.setString(3,CODE_MC);
           pst.setString(4,CODEC);
           
            pst.setString(5,SUBSTRC);
          pst.setString(6,"4");
           pst.setString(8,Total);
            pst.setString(7,"0.00");
            
            
            pst.setString(9,num_fiche.getText());
          pst.setString(10,dates.getText());
           pst.setString(11,"PHARMACIE");
           pst.setString(12,jList1.getSelectedValue());
             pst.setString(13,"[SJ]");
           
           pst.executeUpdate();
                 pst.close();
            rs.close();
                con.close(); 
     }catch(Exception ex){JOptionPane.showMessageDialog(null,ex);}
               
               //---->>> OHADA DEBIT 57 PH NA
          
               try{
          Class.forName("org.sqlite.JDBC");
           con= DriverManager.getConnection(url);
     String sql="INSERT INTO OHADA (COMPTE_M,COMPTE,CODE_M,CODE,SUBSTR,CLASSE,DEBIT,CREDIT,NUM,DATES,BUSS,LIBELLE,JOURNAL)"+
             "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
     pst=con.prepareStatement(sql);
     //COMPTE_MS,CLASSES,SUBSTRS;
     //String value1=jComboBox1.getSelectedItem().toString();
      pst.setString(1, COMPTE_MB);
         pst.setString(2,COMPTEB);
          pst.setString(3,CODE_MB);
           pst.setString(4,CODEB);
           
            pst.setString(5,SUBSTRB);
          pst.setString(6,"4");
           pst.setString(7,Total);
            pst.setString(8,"0.00");
            
            
            pst.setString(9,num_fiche.getText());
          pst.setString(10,dates.getText());
           pst.setString(11,"PHARMACIE");
           pst.setString(12,jList1.getSelectedValue());
             pst.setString(13,"[SJ]");
           
           pst.executeUpdate();
                 pst.close();
            rs.close();
                con.close(); 
     }catch(Exception ex){JOptionPane.showMessageDialog(null,ex);}
      
  COMPTE=null;
  SUBSTR=null;
  CLASSE=null;
  COMPTE_M=null;
  CODE_M=null;
  CODE=null;
  CODEC=null;
  SUBSTRC=null;
  CODE_MC=null;
  COMPTE_MC=null;
  
  CODEB=null;
  SUBSTRB=null;
  CODE_MB=null;
  COMPTE_MB=null;
  COMPTEB=null;
  }
 public void call_in_tableS(){
  //h_p_vente_medoc (MEDICAMENT,CAT,CLASSI,CLIENT,P_VENTE,QTY,P_TOTAL,DATES,NUM_ID,NUM)"
	
	
      try{
             Class.forName("org.sqlite.JDBC");
           con= DriverManager.getConnection(url);
             String sql="SELECT MEDICAMENT,QTY,QTY_D AS 'UNITE',P_VENTE as PU,P_TOTAL AS 'Prix TOTAL' FROM h_p_vente_medoc  where NUM='"+num_fiche.getText()+"'";
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
        
       
       
      
       
       col0.setPreferredWidth(250);
       col1.setPreferredWidth(20);
       col2.setPreferredWidth(50);
         col3.setPreferredWidth(20);
          col4.setPreferredWidth(50);
      
        
      
      
     
       
       
      // jTable1.setModel(mode);
       
     
    }catch(Exception ex){JOptionPane.showMessageDialog(null,ex);}  
     
     }
public void refresh(){
  
    detai1.setText("");
    jComboBox1.setSelectedItem("......");
    jComboBox2.setSelectedItem("......");
    detail2.setText("");
      
    qty.setText("");
    Total=null;
     // client.setText("");
       pv.setText("");
      qty_s.setText("");
       qty.setText("");
    
  }
public void call_in_table_ordonance(){
  //h_p_vente_medoc (MEDICAMENT,CAT,CLASSI,CLIENT,P_VENTE,QTY,P_TOTAL,DATES,NUM_ID,NUM)"
	
	
      try{
             Class.forName("org.sqlite.JDBC");
           con= DriverManager.getConnection(url);
             String sql="SELECT DISTINCT(NUM_FICHE) AS 'No FICHE',NUM AS MATRICULE FROM h_p_send_pharma  where STATUS='IN'";
     //  String sql="SELECT ID, `NUM_FACTURE`, `DATE_FACTURE`, `DESCRIPTION`, `DEBIT_QTY`, `DEBIT_PU`, `DEBIT_PT`, `DEBIT_TVA`, `CREDIT_QTY`, `CREDIT_PU`, `CREDIT_PT`, `CREDIT_TVA`, `DATES`, `NUM` FROM facture_fournisseur where compte='"+jComboBox6.getSelectedItem()+"'";
       pst = con.prepareStatement(sql);
      rs= pst.executeQuery();
      
       
       jTable7.setModel(DbUtils.resultSetToTableModel(rs));
       mode=new DefaultTableModel();
       
      
        
       TableColumn col0=jTable7.getColumnModel().getColumn(0);
        TableColumn col1=jTable7.getColumnModel().getColumn(1);
        
       
       
      
       
       col0.setPreferredWidth(50);
       col1.setPreferredWidth(50);
     
        
     
    }catch(Exception ex){JOptionPane.showMessageDialog(null,ex);}  
      try{
              Class.forName("org.sqlite.JDBC");
            String sql="SELECT COUNT(DISTINCT(NUM_FICHE)) FROM  h_p_send_pharma WHERE STATUS='IN'";
            con = DriverManager.getConnection(url);
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
              
     //   NOM,NOMS,CLASSI,CATEGORI,P_ACHAT,P_VENTE,D_UNITE,P_ACHAT_G,P_VENTE_G,G_VENTE,NUM,MARK,MARK_QTY,CONTAINER,CONTAINER_QTY,QTY_UNITE,VAL_UNITE,PV_$,PVG_$,PA_$,PAG_$        
             String MAXs=rs.getString("COUNT(DISTINCT(NUM_FICHE))");
             bell.setText(MAXs);
                
                
               
                  
                      
            }
             pst.close();
            rs.close();
               
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
     }

 
           public void lock_employee(){
         ActionListener actionListener = new  ActionListener(){
             @Override
             public void actionPerformed(ActionEvent e) {
          call_in_table_ordonance();
             }
         };
         timer = new Timer(1000,actionListener); 
    
timer.start();

//To change body of generated methods, choose Tools | Templates.
             }
 String Table_click_NUMFICHE=null;
public void call_detail_ordonance(){

     int row= jTable7.getSelectedRow();
        //Table_click = (jTable2.getModel().getValueAt(row,2). toString());
       Table_click_NUMFICHE = (jTable7.getModel().getValueAt(row,0). toString());
	
	
      try{
             Class.forName("org.sqlite.JDBC");
           con= DriverManager.getConnection(url);
             String sql="SELECT MEDICAMENT,QTY,DOSE FROM h_p_send_pharma WHERE   NUM_FICHE='"+Table_click_NUMFICHE+"'";
     //  String sql="SELECT ID, `NUM_FACTURE`, `DATE_FACTURE`, `DESCRIPTION`, `DEBIT_QTY`, `DEBIT_PU`, `DEBIT_PT`, `DEBIT_TVA`, `CREDIT_QTY`, `CREDIT_PU`, `CREDIT_PT`, `CREDIT_TVA`, `DATES`, `NUM` FROM facture_fournisseur where compte='"+jComboBox6.getSelectedItem()+"'";
       pst = con.prepareStatement(sql);
      rs= pst.executeQuery();
      
       
       jTable6.setModel(DbUtils.resultSetToTableModel(rs));
       mode=new DefaultTableModel();
       
       
        
       TableColumn col0=jTable6.getColumnModel().getColumn(0);
        TableColumn col1=jTable6.getColumnModel().getColumn(1);
         TableColumn col2=jTable6.getColumnModel().getColumn(2);
       
          
        
       
        
       
       
      
       
       col0.setPreferredWidth(100);
       col1.setPreferredWidth(20);
       col2.setPreferredWidth(20);
      
       
       
    }catch(Exception ex){JOptionPane.showMessageDialog(null,ex);}  
}

public void select_jTable2(){

  try{
          int row= jTable6.getSelectedRow();
          String Table_clickss = (jTable6.getModel().getValueAt(row,0). toString());
          String sql = "SELECT * FROM  h_p_send_pharma WHERE MEDICAMENT = '"+Table_clickss+"' and NUM_FICHE='"+Table_click_NUMFICHE+"'";
          con = DriverManager.getConnection(url);
          pst = con.prepareStatement(sql);
          rs=pst.executeQuery();
          if(rs.next()){
           String sum1 =rs.getString("NAMES");
         String sum2 =rs.getString("DEPARTEMENT");
         String sum3 =rs.getString("NOTE");
        
        
 client.setText(sum1);
departement.setText(sum2);
note.setText(sum3);
     
          }
          pst.close();
            rs.close();
            con.close();
    }catch(Exception ex ){
         JOptionPane.showMessageDialog(null,ex);
       
     }

}

public void send(){

 try{
          Class.forName("org.sqlite.JDBC");
           con= DriverManager.getConnection(url);
     
         // java.sql.Connection con = getConnection();
     String sql="UPDATE h_p_send_pharma SET STATUS=? WHERE NUM_FICHE ='"+Table_click_NUMFICHE +"'";
     pst=con.prepareStatement(sql);
    
         pst.setString(1, "OUT");
     
    
      pst.executeUpdate();
                con.close();
              
  // JOptionPane.showMessageDialog(null,"Transaction Saved");
      
   //  cat1.setText("");
      
     }catch(Exception ex){JOptionPane.showMessageDialog(null,ex);} 

}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        webButtonStyle1 = new com.alee.laf.button.WebButtonStyle();
        webButtonUI1 = new com.alee.laf.button.WebButtonUI();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        bell = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane10 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        total = new javax.swing.JTextField();
        totalfc = new javax.swing.JTextField();
        jPanel11 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane11 = new javax.swing.JScrollPane();
        jTable6 = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel10 = new javax.swing.JPanel();
        search_medoc = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jPanel12 = new javax.swing.JPanel();
        jScrollPane12 = new javax.swing.JScrollPane();
        jTable7 = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        detai1 = new javax.swing.JTextField();
        detail2 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        pv = new javax.swing.JTextField();
        qty_s = new javax.swing.JTextField();
        qty = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        note = new javax.swing.JTextField();
        barcode = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        dates = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        departement = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        num_fiche = new javax.swing.JTextField();
        client = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();

        jPanel1.setBackground(new java.awt.Color(192, 255, 192));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(192, 255, 192));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(128, 255, 128));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hopital/icons8_close_window_30px_1.png"))); // NOI18N
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });
        jPanel3.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 0, 30, 36));

        bell.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        bell.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hopital/icons8_bell_16px.png"))); // NOI18N
        bell.setText("0");
        jPanel3.add(bell, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        jLabel21.setBackground(new java.awt.Color(128, 255, 128));
        jLabel21.setForeground(new java.awt.Color(128, 255, 128));
        jLabel21.setText("NULL");
        jPanel3.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 10, -1, -1));

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 1, 900, -1));

        jPanel5.setBackground(new java.awt.Color(192, 255, 192));
        jPanel5.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 255)));

        jTable1.setBackground(new java.awt.Color(192, 255, 192));
        jTable1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable1.setSelectionForeground(new java.awt.Color(198, 215, 233));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTable1MousePressed(evt);
            }
        });
        jScrollPane10.setViewportView(jTable1);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("Total montant à payer");

        total.setEditable(false);
        total.setBackground(new java.awt.Color(51, 255, 102));
        total.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        total.setForeground(new java.awt.Color(255, 255, 255));
        total.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        totalfc.setEditable(false);
        totalfc.setBackground(new java.awt.Color(51, 255, 102));
        totalfc.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        totalfc.setForeground(new java.awt.Color(255, 255, 255));
        totalfc.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 618, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(total, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(totalfc)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(total, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(totalfc, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );

        jPanel2.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(248, 141, 640, 240));

        jPanel11.setBackground(new java.awt.Color(192, 255, 192));
        jPanel11.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 255)));
        jPanel11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(192, 255, 192));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        jTable6.setBackground(new java.awt.Color(192, 255, 192));
        jTable6.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jTable6.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable6.setSelectionForeground(new java.awt.Color(198, 215, 233));
        jTable6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable6MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTable6MousePressed(evt);
            }
        });
        jScrollPane11.setViewportView(jTable6);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 208, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane11, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane11, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE))
        );

        jPanel11.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 210, -1));

        jPanel6.setBackground(new java.awt.Color(192, 255, 192));
        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        search_medoc.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        search_medoc.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        search_medoc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                search_medocKeyReleased(evt);
            }
        });

        jList1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jList1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jList1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jList1);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 203, Short.MAX_VALUE)
            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel10Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(search_medoc, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 72, Short.MAX_VALUE)
            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel10Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(search_medoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel10Layout.createSequentialGroup()
                            .addGap(20, 20, 20)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("List Medicament", jPanel10);

        jTable7.setBackground(new java.awt.Color(192, 255, 192));
        jTable7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTable7.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable7.setSelectionForeground(new java.awt.Color(198, 215, 233));
        jTable7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable7MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTable7MousePressed(evt);
            }
        });
        jScrollPane12.setViewportView(jTable7);

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane12, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Ordonance", jPanel12);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel11.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 210, 100));

        jPanel7.setBackground(new java.awt.Color(192, 255, 192));
        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        detai1.setEditable(false);
        detai1.setBackground(new java.awt.Color(240, 240, 241));
        detai1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        detai1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel7.add(detai1, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 32, 90, -1));

        detail2.setEditable(false);
        detail2.setBackground(new java.awt.Color(240, 240, 241));
        detail2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        detail2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel7.add(detail2, new org.netbeans.lib.awtextra.AbsoluteConstraints(107, 32, 92, -1));

        jComboBox1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "......", "Detail", "Gros" }));
        jComboBox1.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                jComboBox1PopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        jPanel7.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 79, 90, -1));

        jComboBox2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "......" }));
        jComboBox2.setEnabled(false);
        jComboBox2.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                jComboBox2PopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        jPanel7.add(jComboBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(107, 79, 89, -1));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setText("Type1");
        jPanel7.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 59, -1, -1));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setText("Type2");
        jPanel7.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(107, 59, -1, -1));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel11.setText("Qty_Detail 1");
        jPanel7.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 12, -1, -1));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel12.setText("Qty_Detail 2");
        jPanel7.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(107, 12, -1, -1));

        jPanel11.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, 210, 110));

        jPanel9.setBackground(new java.awt.Color(192, 255, 192));
        jPanel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pv.setEditable(false);
        pv.setBackground(new java.awt.Color(240, 240, 241));
        pv.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        pv.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel9.add(pv, new org.netbeans.lib.awtextra.AbsoluteConstraints(107, 7, 92, -1));

        qty_s.setEditable(false);
        qty_s.setBackground(new java.awt.Color(240, 240, 241));
        qty_s.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        qty_s.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel9.add(qty_s, new org.netbeans.lib.awtextra.AbsoluteConstraints(107, 34, 92, -1));

        qty.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        qty.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        qty.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                qtyKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                qtyKeyReleased(evt);
            }
        });
        jPanel9.add(qty, new org.netbeans.lib.awtextra.AbsoluteConstraints(107, 61, 92, -1));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel13.setText("Prix Vente");
        jPanel9.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 11, -1, -1));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel14.setText("Qty Stock");
        jPanel9.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 38, -1, -1));

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel15.setText("Quantities");
        jPanel9.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 65, -1, -1));

        jPanel11.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 320, 210, 90));

        jPanel2.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 43, 230, 420));

        jPanel14.setBackground(new java.awt.Color(192, 255, 192));
        jPanel14.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 255)));
        jPanel14.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        note.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        note.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noteActionPerformed(evt);
            }
        });
        jPanel14.add(note, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 30, 190, -1));

        barcode.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jPanel14.add(barcode, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 210, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("Departement");
        jPanel14.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 50, -1, 20));

        dates.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jPanel14.add(dates, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 70, 220, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("No. Facture");
        jPanel14.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 10, -1, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Barcode");
        jPanel14.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        departement.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jPanel14.add(departement, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 70, 190, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("Date");
        jPanel14.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 50, -1, 20));

        num_fiche.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        num_fiche.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel14.add(num_fiche, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 30, 220, -1));

        client.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jPanel14.add(client, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 210, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("Note Medecin");
        jPanel14.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 10, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Nom du Client");
        jPanel14.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, 20));

        jPanel2.add(jPanel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(248, 43, 640, 100));

        jPanel8.setBackground(new java.awt.Color(192, 255, 192));
        jPanel8.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 255)));

        jPanel13.setBackground(new java.awt.Color(192, 255, 192));
        jPanel13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel13MouseClicked(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel16.setText("New");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addComponent(jLabel16)
                .addContainerGap(70, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel15.setBackground(new java.awt.Color(192, 255, 192));
        jPanel15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel15MouseClicked(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel17.setText("Add");

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(85, 85, 85)
                .addComponent(jLabel17)
                .addContainerGap(102, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel16.setBackground(new java.awt.Color(192, 255, 192));
        jPanel16.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel18.setText("Delete");

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(jLabel18)
                .addContainerGap(50, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel17.setBackground(new java.awt.Color(192, 255, 192));
        jPanel17.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel17MouseClicked(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel19.setText("Save");

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel19)
                .addContainerGap(37, Short.MAX_VALUE))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel2.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(248, 381, 640, 80));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 13, 900, 474));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 928, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void noteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_noteActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable1MouseClicked

    private void jTable1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable1MousePressed

    private void jTable6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable6MouseClicked
select_jTable2();
JLIST_from_COMPTE2S();
jLabel21.setText("TABLE");// TODO add your handling code here:
    }//GEN-LAST:event_jTable6MouseClicked

    private void jTable6MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable6MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable6MousePressed

    private void jTable7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable7MouseClicked
call_detail_ordonance();        // TODO add your handling code here:
    }//GEN-LAST:event_jTable7MouseClicked

    private void jTable7MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable7MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable7MousePressed

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel1MouseClicked

    private void search_medocKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_search_medocKeyReleased
Search2();        // TODO add your handling code here:
    }//GEN-LAST:event_search_medocKeyReleased

    private void jList1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList1MouseClicked
JLIST_from_COMPTE2();
jLabel21.setText("JLIST");
COMPTE_MANA=(jList1.getSelectedValue());
// TODO add your handling code here:
    }//GEN-LAST:event_jList1MouseClicked

    private void jComboBox1PopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_jComboBox1PopupMenuWillBecomeInvisible
select_type1();        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1PopupMenuWillBecomeInvisible

    private void jComboBox2PopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_jComboBox2PopupMenuWillBecomeInvisible
type2();        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox2PopupMenuWillBecomeInvisible

    private void qtyKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_qtyKeyReleased
if(qty.getText().equals("")){
  
}else if(jComboBox1.getSelectedItem().equals("Gros")){

}else if(jComboBox1.getSelectedItem().equals("Detail")){
 try{
     String tmp =null;
       if(jLabel21.getText().equals("JLIST")){
       tmp =(String)jList1.getSelectedValue();
       }else if(jLabel21.getText().equals("TABLE")){
        int row= jTable6.getSelectedRow();
          tmp = (jTable6.getModel().getValueAt(row,0). toString());
       
       }
     Double MARK_QTY=null;
     Double a= Double.parseDouble(qty.getText());
     Double c;
              Class.forName("org.sqlite.JDBC");
            String sql="SELECT * FROM   h_p_medicament Where NOMS='"+tmp+"'";
            con = DriverManager.getConnection(url);
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
              
                
              MARK_QTY =rs.getDouble("MARK_QTY");
              
         c=a*MARK_QTY;     
               detail2.setText(""+c);
                
            }
             pst.close();
            rs.close();
               
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }  
}else{}
             // TODO add your handling code here:
    }//GEN-LAST:event_qtyKeyReleased

    private void qtyKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_qtyKeyPressed
int key = evt.getKeyCode();
     if (key == KeyEvent.VK_ENTER) {
        Toolkit.getDefaultToolkit().beep(); 
        if(detai1.getText().equals("")||detail2.getText().equals("")||pv.getText().equals("")||qty_s.getText().equals("")||qty.getText().equals("")||client.getText().equals("")||jComboBox1.getSelectedItem().equals("......")||jComboBox2.getSelectedItem().equals("......")){
        JOptionPane.showMessageDialog(null,"Wrong Data","Error",JOptionPane.ERROR_MESSAGE);
        }else{
        save(); 
call_in_tableS();
 refresh();
        }
        }              // TODO add your handling code here:
    }//GEN-LAST:event_qtyKeyPressed

    private void jPanel13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel13MouseClicked
 refresh();
 num_fiche.setText("");
 client.setText("");
 departement.setText("");
 note.setText("");
 total.setText("");
 totalfc.setText("");
 jComboBox2.setSelectedItem("......");
jTable1.setModel(new DefaultTableModel());

// TODO add your handling code here:
    }//GEN-LAST:event_jPanel13MouseClicked

    private void jPanel15MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel15MouseClicked
    if(detai1.getText().equals("")||detail2.getText().equals("")||pv.getText().equals("")||qty_s.getText().equals("")||qty.getText().equals("")||client.getText().equals("")||jComboBox1.getSelectedItem().equals("......")||jComboBox2.getSelectedItem().equals("......")){
        JOptionPane.showMessageDialog(null,"Wrong Data","Error",JOptionPane.ERROR_MESSAGE);
        }else{
        save(); 
call_in_tableS();
 refresh();
 client.setText("");
        }        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel15MouseClicked

    private void jPanel17MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel17MouseClicked
int response = JOptionPane.showConfirmDialog(null, "VOULEZ-VOUS ENREGISTRE CETTE TRANSACTION?", "Confirm",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (response == JOptionPane.YES_OPTION) {
 send();  
jTable6.setModel(new DefaultTableModel());
jTable1.setModel(new DefaultTableModel());
 refresh();
 num_fiche.setText("");
 client.setText("");
 departement.setText("");
 note.setText("");
 total.setText("");
 totalfc.setText("");
 jComboBox2.setSelectedItem("......");
               
            } else if (response == JOptionPane.NO_OPTION) {
              //delete(); 
           
            }        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel17MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField barcode;
    private javax.swing.JLabel bell;
    private javax.swing.JTextField client;
    private javax.swing.JTextField dates;
    private javax.swing.JTextField departement;
    private javax.swing.JTextField detai1;
    private javax.swing.JTextField detail2;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
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
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList<String> jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable6;
    private javax.swing.JTable jTable7;
    private javax.swing.JTextField note;
    private javax.swing.JTextField num_fiche;
    private javax.swing.JTextField pv;
    private javax.swing.JTextField qty;
    private javax.swing.JTextField qty_s;
    private javax.swing.JTextField search_medoc;
    private javax.swing.JTextField total;
    private javax.swing.JTextField totalfc;
    private com.alee.laf.button.WebButtonStyle webButtonStyle1;
    private com.alee.laf.button.WebButtonUI webButtonUI1;
    // End of variables declaration//GEN-END:variables
}
