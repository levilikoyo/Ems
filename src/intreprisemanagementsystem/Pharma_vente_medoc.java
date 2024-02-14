/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intreprisemanagementsystem;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
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
public class Pharma_vente_medoc extends javax.swing.JPanel {

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
    public Pharma_vente_medoc() {
        initComponents();
        con=JavaDbConnect.dbConnect();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
     //  DateFormat dateFormat = DateFormat.getDateInstance(partener);
String date = simpleDateFormat.format(new Date());

       dates.setText(date);
       call_in_tableSS();
    }
    
     public void call_in_tableSS(){
   try{
          String sql="SELECT distinct(NUM) AS 'Bon de Commande' FROM   h_p_bon_commande where STATUT2='Yes'";
     pst = con.prepareStatement(sql);
      rs= pst.executeQuery();
      
       
      jTable7.setModel(DbUtils.resultSetToTableModel(rs));
       mode=new DefaultTableModel();
  
    }catch(Exception ex){JOptionPane.showMessageDialog(null,ex);}  
     
     }
 public void roll()
     {
         try{
                 
           
            String sql="SELECT NUM FROM  h_p_inventaire_medoc WHERE STATUT='Vente' ORDER BY NUM DESC LIMIT 1";
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
                 
           
            String sql="SELECT NUM_ID FROM  h_p_inventaire_medoc where STATUT='Vente' ORDER BY NUM_ID DESC LIMIT 1";
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
             
          String sql="SELECT * FROM h_p_medicament where NOMS LIKE '"+search_medoc.getText()+"%'";
             
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                //String sum=rs.getString("nom");
                String sums=rs.getString("NOMS");
                 list.addElement(sums);
                
                 jList1.setModel(list);
            }
             
            
              
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
              
            String sql="SELECT * FROM   h_p_medicament Where NOMS='"+tmp+"'";
            
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                String sums1=rs.getString("MARK");
                jComboBox2.removeAllItems();
                  CAT=rs.getString("CATEGORI");
                    CLASSI=rs.getString("CLASSI"); 
                    String sums23=rs.getString("VAL_UNITE");
                unite.setText(sums23);
            }
             
            
               
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
      try{
              
            String sql="SELECT SUM(QTY_DEBIT),SUM(QTY_CREDIT) FROM   h_p_inventaire_medoc Where MEDICAMENT='"+tmp+"'";
            
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                Double sums1=rs.getDouble("SUM(QTY_DEBIT)");
                Double sums2=rs.getDouble("SUM(QTY_CREDIT)");
                Double c;
                c=sums1-sums2;
                qty_s.setText(""+c);
            
                      
            }
             
            
               
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
              
            String sql="SELECT * FROM   h_p_medicament Where NOMS='"+Table_clickss+"'";
            
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                String sums1=rs.getString("MARK");
                jComboBox2.removeAllItems();
                  CAT=rs.getString("CATEGORI");
                    CLASSI=rs.getString("CLASSI");           
            }
             
            
               
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
      try{
              
            String sql="SELECT SUM(QTY_DEBIT),SUM(QTY_CREDIT) FROM   h_p_inventaire_medoc Where MEDICAMENT='"+Table_clickss+"'";
            
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                Double sums1=rs.getDouble("SUM(QTY_DEBIT)");
                Double sums2=rs.getDouble("SUM(QTY_CREDIT)");
                Double c;
                c=sums1-sums2;
                qty_s.setText(""+c);
            
                      
            }
             
            
               
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
              
            String sql="SELECT * FROM   h_p_medicament Where NOMS='"+tmp+"'";
            
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                String sums11=rs.getString("CONTAINER");
                jComboBox2.removeAllItems();
                jComboBox2.addItem(sums11);
                jComboBox2.setSelectedItem(sums11);
                 
               // String sum = rs.getString("MARK_QTY");
                detai1.setText("1");
                
                String sums1=rs.getString("P_VENTE_G");
                pv.setText(sums1);
                
            }
             
            
               
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
           try{
              
            String sql="SELECT SUM(QTY_DEBIT),SUM(QTY_CREDIT),SUM(QTY_DD),SUM(QTY_DC) FROM   h_p_inventaire_medoc Where MEDICAMENT='"+tmp+"'";
            
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
       
                 Double sums1=rs.getDouble("SUM(QTY_DEBIT)");
                Double sums2=rs.getDouble("SUM(QTY_CREDIT)");
                
                Double sums11=rs.getDouble("SUM(QTY_DD)");
                Double sums22=rs.getDouble("SUM(QTY_DC)");
           
                 Double c,cc;
                 c=sums1-sums2;
                 cc=sums11-sums22;
            
                 
               qty_s.setText(""+c); 
               detai1.setText(""+c);
               
              detail2.setText(""+cc);
            }
             
            
               
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
          
          
  }else if(jComboBox1.getSelectedItem().equals("Detail")){
       Double MARK_QTY=null;
       Double CONTAINER_QTY=null;
   try{
              
            String sql="SELECT * FROM   h_p_medicament Where NOMS='"+tmp+"'";
            
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                String sums11=rs.getString("MARK");
                jComboBox2.removeAllItems();
                jComboBox2.addItem(sums11);
                jComboBox2.setSelectedItem(sums11);
              
     //   NOM,NOMS,CLASSI,CATEGORI,P_ACHAT,P_VENTE,D_UNITE,P_ACHAT_G,P_VENTE_G,G_VENTE,NUM,MARK,MARK_QTY,CONTAINER,CONTAINER_QTY,QTY_UNITE,VAL_UNITE,PV_$,PVG_$,PA_$,PAG_$        
                String sums1=rs.getString("P_VENTE");
                pv.setText(sums1);
                
              MARK_QTY =rs.getDouble("MARK_QTY");
              CONTAINER_QTY=rs.getDouble("CONTAINER_QTY");
               detai1.setText(""+MARK_QTY);
                
            }
             
            
               
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
  try{
              
            String sql="SELECT SUM(QTY_DEBIT),SUM(QTY_CREDIT) FROM   h_p_inventaire_medoc Where MEDICAMENT='"+tmp+"'";
            
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
               detail2.setText(""+c); 
            }
             
            
               
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
              
            String sql="SELECT distinct(MARK),MARK_QTY,CONTAINER_QTY FROM   h_p_medicament Where NOMS='"+tmp+"' AND ";
            
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
             
            
               
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
  
  
  }
  
   public void SELECT_MAX(){
   try{
              
            String sql="SELECT MAX(NUM) FROM  h_p_inventaire_medoc where statut='Vente'";
            
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
              
     //   NOM,NOMS,CLASSI,CATEGORI,P_ACHAT,P_VENTE,D_UNITE,P_ACHAT_G,P_VENTE_G,G_VENTE,NUM,MARK,MARK_QTY,CONTAINER,CONTAINER_QTY,QTY_UNITE,VAL_UNITE,PV_$,PVG_$,PA_$,PAG_$        
              MAX=rs.getString("MAX(NUM)");
              num.setText(MAX);
                
                
               
                  
                      
            }
             
            
               
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
  
  }
  public void SELECT_SUM(){
        Double   MAXs = null,CDF=null;
   try{
              
            String sql="SELECT SUM(CREDIT) FROM   h_p_inventaire_medoc WHERE NUM='"+num.getText()+"'";
            
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
  Double QTY=null,CONT=null;
   public void  calculeqty(){
  try{
      Double MARK,C;
              
            String sql="SELECT * FROM   h_p_medicament Where NOMS='"+med.getText()+"'";
            
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                CONT=rs.getDouble("CONTAINER_QTY");
              
                
                 MARK=rs.getDouble("MARK_QTY");
    Double QTY_S = Double.parseDouble(qty_s.getText());
       Double QTYS = Double.parseDouble(qty.getText());
       Double BBB = Double.parseDouble(detai1.getText());
       C=((QTY_S*MARK)-QTYS)/CONT;
      QTY=BBB-C; //Total=c.toString();  
                      
            }
             
            
               
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
              
            String sql="SELECT * FROM   h_p_medicament Where NOMS='"+med.getText()+"'";
            
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
//       ohada_save();
       if(num.getText().equals("")){
        roll();
              
                 try{
     String sql="INSERT INTO  h_p_inventaire_medoc (MEDICAMENT,CAT,CLASSI,QTY_DEBIT,QTY_CREDIT,DEBIT,CREDIT,NUM_ID,NUM,FOUR,STATUT,QTY_DD,QTY_DC,up,DATE_EXP,DATES,MARK,LOT,UNITE)"+
             "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     pst=con.prepareStatement(sql);
    
      pst.setString(1, med.getText());
      pst.setString(2, CAT);
      pst.setString(3, CLASSI);
     
      pst.setString(4,"");
      if(jComboBox1.getSelectedItem().equals("Gros")){
       pst.setDouble(5,aa);
       pst.setDouble(13,aa*CONT);
      }else{
      pst.setDouble(5,aa/CONT);
       pst.setDouble(13,aa);
      }
     
      pst.setString(7, Total);
      pst.setString(6, "");
      pst.setString(8, NUM_ID);
        pst.setString(9, rolls);
      pst.setString(10, client.getText());
      pst.setString(11,"Vente");
      
      pst.setString(12,"");
      pst.setString(14,qty_s.getText());
      
       pst.setString(15,"");
        pst.setString(16,dates.getText());
        pst.setString(17,"");
        pst.setString(18,"");
         pst.setString(19,unite.getText());
    
   
      
      pst.executeUpdate();
                
      
     }catch(Exception ex){JOptionPane.showMessageDialog(null,ex);}  
               SELECT_MAX();
             }else{
            
                 try{
          
           
     
         ////   h_p_inventaire_medoc NUM_ID,CAT,CLASSI,QTY_DEBIT,QTY_CREDIT,DEBIT,CREDIT,NUM
     String sql="INSERT INTO  h_p_inventaire_medoc (MEDICAMENT,CAT,CLASSI,QTY_DEBIT,QTY_CREDIT,DEBIT,CREDIT,NUM_ID,NUM,FOUR,STATUT,QTY_DD,QTY_DC,up,DATE_EXP,DATES,MARK,LOT,UNITE)"+
             "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     pst=con.prepareStatement(sql);
    
      pst.setString(1, med.getText());
      pst.setString(2, CAT);
      pst.setString(3, CLASSI);
     
      pst.setString(4,"");
      
      if(jComboBox1.getSelectedItem().equals("Gros")){
       pst.setDouble(5,aa);
       pst.setDouble(13,aa*CONT);
      }else{
      pst.setDouble(5,aa/CONT);
       pst.setDouble(13,aa);
      }
      pst.setString(7, Total);
      pst.setString(6, "");
      pst.setString(8, NUM_ID);
        pst.setString(9,num.getText());
      pst.setString(10, client.getText());
      pst.setString(11,"Vente");
        pst.setString(12,"");
       
          pst.setString(14,qty_s.getText());
          
           pst.setString(15,"");
        pst.setString(16,dates.getText());
        pst.setString(17,"");
        pst.setString(18,"");
         pst.setString(19,unite.getText());
    
   
      
      pst.executeUpdate();
                
      
     }catch(Exception ex){JOptionPane.showMessageDialog(null,ex);} 
       }SELECT_SUM();
        try{
            int row= jTable7.getSelectedRow();
        String Table_clicks = (jTable7.getModel().getValueAt(row,0). toString());
    String sql="UPDATE `h_p_bon_commande` SET `QTY_LL`=?,`DATE_L`='"+dates.getText()+"',`STATUT2`='Print' WHERE NAME='"+med.getText()+"' AND NUM='"+Table_clicks+"'";
    pst=con.prepareStatement(sql);
    pst.setString(1,qty.getText());
   
    pst.executeUpdate();
    // JOptionPane.showMessageDialog(null,"BUDGET TRANS");
    }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}
     
   }

 public void call_in_tableS(){
  //   MEDICAMENT,CAT,CLASSE,FOUR,P_ACHAT,QTY,P_TOTAL,BARCODE,EXPR,DATES,NUM
	
	
      try{
            // HERE
           ///MEDICAMENT,CAT,CLASSI,QTY_DEBIT,QTY_CREDIT,DEBIT,CREDIT,NUM_ID,NUM,FOUR,STATUT,QTY_DD,QTY_DC
             String sql="SELECT MEDICAMENT,QTY_CREDIT AS QTY,UP,CREDIT AS PT,NUM_ID FROM  h_p_inventaire_medoc where NUM='"+num.getText()+"'";
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
       
        
       
       
      
       
       col0.setPreferredWidth(300);
       col1.setPreferredWidth(20);
       col2.setPreferredWidth(20);
         col3.setPreferredWidth(50);
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
 int row= jTable6.getSelectedRow();
String Table_click = (jTable6.getModel().getValueAt(row,0). toString());

int rows= jTable7.getSelectedRow();
String Table_clicks = (jTable7.getModel().getValueAt(rows,0). toString());
String Table_clickss = (jTable6.getModel().getValueAt(row,1). toString());
qty.setText(Table_clickss);
    
     
        try{
              
            String sql="SELECT * FROM   h_p_medicament Where NOMS='"+Table_click+"'";
            
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                String sums1=rs.getString("MARK");
                jComboBox2.removeAllItems();
                  CAT=rs.getString("CATEGORI");
                    CLASSI=rs.getString("CLASSI");   
                    String sums23=rs.getString("VAL_UNITE");
                unite.setText(sums23);
            }
             
            
               
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
           try{
              
            String sql="SELECT * FROM   h_p_bon_commande Where NUM='"+Table_clicks+"'";
            
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                String sums1=rs.getString("Client");
                client.setText(sums1);
                          
            }
             
            
               
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
      try{
              
            String sql="SELECT SUM(QTY_DEBIT),SUM(QTY_CREDIT) FROM   h_p_inventaire_medoc Where MEDICAMENT='"+Table_click+"'";
            
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                Double sums1=rs.getDouble("SUM(QTY_DEBIT)");
                Double sums2=rs.getDouble("SUM(QTY_CREDIT)");
                Double c;
                c=sums1-sums2;
                qty_s.setText(""+c);
            
                      
            }
             
            
               
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
    
}

public void send(){

 try{
          
           
     
         // java.sql.Connection con = getConnection();
     String sql="UPDATE h_p_send_pharma SET STATUS=? WHERE NUM_FICHE ='"+Table_click_NUMFICHE +"'";
     pst=con.prepareStatement(sql);
    
         pst.setString(1, "OUT");
     
    
      pst.executeUpdate();
                
              
  // JOptionPane.showMessageDialog(null,"Transaction Saved");
      
   //  cat1.setText("");
      
     }catch(Exception ex){JOptionPane.showMessageDialog(null,ex);} 

}

 public void deleteS()
    {
        int rows= jTable1.getSelectedRow();
     String Table_click = (jTable1.getModel().getValueAt(rows,4). toString()); 
       for(int row = 0; row<rows; row++)
  {
         try{
        String sql = "DELETE FROM h_p_inventaire_medoc  WHERE NUM_ID=?";
         
         pst = con.prepareStatement(sql);
         pst.setString(1,Table_click);
         pst.executeUpdate();

     }catch(SQLException | HeadlessException ex ){
         JOptionPane.showMessageDialog(null,ex);
     } 
  }
       Table_click=null;
    }
 
 public void call_in_tableSSS(){
                       int row= jTable7.getSelectedRow();
        String Table_clicks = (jTable7.getModel().getValueAt(row,0). toString());
    try{
          String sql="SELECT `NAME` AS Medicament,`QTY_L` as 'Qte' FROM   h_p_bon_commande where  NUM='"+Table_clicks+"' and statut2='Yes'";
      pst = con.prepareStatement(sql);
      rs= pst.executeQuery();
      
       
       jTable6.setModel(DbUtils.resultSetToTableModel(rs));
       mode=new DefaultTableModel();
       
       DefaultTableCellRenderer centre =new DefaultTableCellRenderer();
        centre.setHorizontalAlignment(JLabel.CENTER);
        
       TableColumn col0=jTable6.getColumnModel().getColumn(0);
        TableColumn col1=jTable6.getColumnModel().getColumn(1);
       
       jTable6.getColumnModel().getColumn(1).setCellRenderer(centre);


//       
//        
//       
//       
//      
//       
       col0.setPreferredWidth(250);
       col1.setPreferredWidth(50);
       
//      
//        
      
      
     
       
       
      // jTable1.setModel(mode);
       
     
    }catch(Exception ex){JOptionPane.showMessageDialog(null,ex);}  
      try{
                
            String sql="SELECT PROJET,CLIENT FROM   h_p_bon_commande where NUM='"+Table_clicks+"'";
            
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                String sum1=rs.getString("PROJET");
           departement.setText(sum1);
           String sum=rs.getString("CLIENT");
           client.setText(sum);
            }
             
            
            
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
     }
 
  public void report()
     {
          
     
             try{
                 String tmp="BON-CMD/1003";
                 
                 
                 String sqls="Select path from pathn";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
            if(rs.next()){
             
                String sum=rs.getString("path");
                  
                 String NameFiles=sum;
            
            String NameFile=""+NameFiles+"h_p_bon_de_commande.jrxml";
                 
                 
                 
                JasperDesign jd=JRXmlLoader.load(NameFile);
                 
                 
                 
                // String report ="C:\\Users\\Dosh\\Documents\\NetBeansProjects\\intreprisemanagementsystem\\src\\intreprisemanagementsystem\\maretiauxrepport.jrxml";
                // JasperDesign jd=JRXmlLoader.load("C:\\Users\\Dosh\\Documents\\NetBeansProjects\\intreprisemanagementsystem\\src\\intreprisemanagementsystem\\Fiche_Materiaux_out.jrxml");
                String sql="select * from h_p_bon_commande  INNER JOIN projet ON h_p_bon_commande.PROJET=projet.PROJET_NUM where h_p_bon_commande.NUM='"+tmp+"'";
                
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
         
//setDefaultCloseOperation(DISPOSE_ON_CLOSE); 
     }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        med = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane10 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        total = new javax.swing.JTextField();
        totalfc = new javax.swing.JTextField();
        jPanel11 = new javax.swing.JPanel();
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
        unite = new javax.swing.JTextField();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel10 = new javax.swing.JPanel();
        search_medoc = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jPanel12 = new javax.swing.JPanel();
        jScrollPane12 = new javax.swing.JScrollPane();
        jTable7 = new javax.swing.JTable();
        jScrollPane11 = new javax.swing.JScrollPane();
        jTable6 = new javax.swing.JTable();
        jPanel14 = new javax.swing.JPanel();
        departement = new javax.swing.JTextField();
        barcode = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        client = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        dates = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        num = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();

        jPanel2.setBackground(new java.awt.Color(192, 255, 192));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jPanel3.setBackground(new java.awt.Color(128, 255, 128));

        jLabel21.setBackground(new java.awt.Color(128, 255, 128));
        jLabel21.setForeground(new java.awt.Color(128, 255, 128));
        jLabel21.setText("NULL");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Vente/Distribution");

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("X");
        jLabel20.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel20MouseClicked(evt);
            }
        });

        med.setBackground(new java.awt.Color(128, 255, 128));
        med.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        med.setForeground(new java.awt.Color(128, 255, 128));
        med.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(128, 255, 128)));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(176, 176, 176)
                        .addComponent(jLabel21))
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(med, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel21))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(med, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

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
                    .addComponent(jScrollPane10)
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
                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(total, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(totalfc, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );

        jPanel11.setBackground(new java.awt.Color(192, 255, 192));
        jPanel11.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 255)));

        jPanel7.setBackground(new java.awt.Color(192, 255, 192));
        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        detai1.setEditable(false);
        detai1.setBackground(new java.awt.Color(240, 240, 241));
        detai1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        detai1.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        detail2.setEditable(false);
        detail2.setBackground(new java.awt.Color(240, 240, 241));
        detail2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        detail2.setHorizontalAlignment(javax.swing.JTextField.CENTER);

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

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setText("Type1");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setText("Type2");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel11.setText("Qty_Detail 1");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel12.setText("Qty_Detail 2");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(detai1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(detail2))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGap(26, 26, 26)
                                .addComponent(jLabel12))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(61, 61, 61)
                                .addComponent(jLabel10)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12))
                .addGap(6, 6, 6)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(detai1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(detail2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10))
                .addGap(6, 6, 6)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel9.setBackground(new java.awt.Color(192, 255, 192));
        jPanel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pv.setEditable(false);
        pv.setBackground(new java.awt.Color(240, 240, 241));
        pv.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        pv.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel9.add(pv, new org.netbeans.lib.awtextra.AbsoluteConstraints(87, 7, 140, -1));

        qty_s.setEditable(false);
        qty_s.setBackground(new java.awt.Color(240, 240, 241));
        qty_s.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        qty_s.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel9.add(qty_s, new org.netbeans.lib.awtextra.AbsoluteConstraints(87, 34, 140, -1));

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
        jPanel9.add(qty, new org.netbeans.lib.awtextra.AbsoluteConstraints(137, 61, 90, -1));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel13.setText("Prix Vente");
        jPanel9.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 11, -1, -1));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel14.setText("Qty Stock");
        jPanel9.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 38, -1, -1));

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel15.setText("Quantities");
        jPanel9.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 65, -1, -1));

        unite.setEditable(false);
        unite.setBackground(new java.awt.Color(240, 240, 241));
        unite.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        unite.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        unite.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                uniteKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                uniteKeyReleased(evt);
            }
        });
        jPanel9.add(unite, new org.netbeans.lib.awtextra.AbsoluteConstraints(87, 61, 50, 20));

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
            .addComponent(search_medoc)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addComponent(search_medoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE))
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
            .addComponent(jScrollPane12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE)
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Ordonance", jPanel12);

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

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane11, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel14.setBackground(new java.awt.Color(192, 255, 192));
        jPanel14.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 255)));

        departement.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        departement.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                departementActionPerformed(evt);
            }
        });

        barcode.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("Departement:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Barcode:");

        client.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        client.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clientActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText(" Client:");

        dates.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        dates.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("Date:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("Num:");

        num.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(barcode, javax.swing.GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE)
                    .addComponent(client))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dates, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(departement))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(num)))
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(departement, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(barcode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(dates, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(client, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4))
                    .addComponent(num, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

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
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("New");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("Add");

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
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
        jPanel16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel16MouseClicked(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("Delete");

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
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
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("Print");

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
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
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable1MouseClicked

    private void jTable1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable1MousePressed

    private void jTable6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable6MouseClicked
select_jTable2();
int rows= jTable6.getSelectedRow();
     String Table_click = (jTable6.getModel().getValueAt(rows,0). toString());
     med.setText(Table_click);
jLabel21.setText("TABLE");// TODO add your handling code here:
    }//GEN-LAST:event_jTable6MouseClicked

    private void jTable6MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable6MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable6MousePressed

    private void search_medocKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_search_medocKeyReleased
Search2();         // TODO add your handling code here:
    }//GEN-LAST:event_search_medocKeyReleased

    private void jList1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList1MouseClicked
JLIST_from_COMPTE2();
jLabel21.setText("JLIST");
med.setText(jList1.getSelectedValue());
//COMPTE_MANA=(jList1.getSelectedValue()); // TODO add your handling code here:
    }//GEN-LAST:event_jList1MouseClicked

    private void jTable7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable7MouseClicked
call_in_tableSSS();         // TODO add your handling code here:
    }//GEN-LAST:event_jTable7MouseClicked

    private void jTable7MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable7MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable7MousePressed

    private void jComboBox1PopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_jComboBox1PopupMenuWillBecomeInvisible
select_type1();          // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1PopupMenuWillBecomeInvisible

    private void jComboBox2PopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_jComboBox2PopupMenuWillBecomeInvisible
           // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox2PopupMenuWillBecomeInvisible

    private void qtyKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_qtyKeyPressed
             // TODO add your handling code here:
    }//GEN-LAST:event_qtyKeyPressed

    private void qtyKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_qtyKeyReleased
 
        // TODO add your handling code here:
    }//GEN-LAST:event_qtyKeyReleased

    private void departementActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_departementActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_departementActionPerformed

    private void jPanel13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel13MouseClicked
   refresh();
// num_fiche.setText("");
 client.setText("");
 departement.setText("");
// note.setText("");
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
 //client.setText("");
        }         // TODO add your handling code here:
    }//GEN-LAST:event_jPanel15MouseClicked

    private void jPanel17MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel17MouseClicked
report();
//            num.setText("");
//            client.setText("");
////            departement.setText("");
//            departement.setText("");
//            total.setText("");
//            totalfc.setText("");
//            jComboBox2.setSelectedItem("......");

            // TODO add your handling code here:
    }//GEN-LAST:event_jPanel17MouseClicked

    private void jLabel20MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel20MouseClicked
this.show(false);        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel20MouseClicked

    private void jPanel16MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel16MouseClicked
deleteS(); 
call_in_tableS();
SELECT_SUM();        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel16MouseClicked

    private void clientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clientActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_clientActionPerformed

    private void uniteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_uniteKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_uniteKeyPressed

    private void uniteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_uniteKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_uniteKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField barcode;
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
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList<String> jList1;
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
    private javax.swing.JPanel jPanel5;
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
    private javax.swing.JTextField med;
    private javax.swing.JTextField num;
    private javax.swing.JTextField pv;
    private javax.swing.JTextField qty;
    private javax.swing.JTextField qty_s;
    private javax.swing.JTextField search_medoc;
    private javax.swing.JTextField total;
    private javax.swing.JTextField totalfc;
    private javax.swing.JTextField unite;
    // End of variables declaration//GEN-END:variables
}
