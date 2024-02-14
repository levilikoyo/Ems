/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intreprisemanagementsystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Administrator
 */
public class salary_recaputilation1 extends javax.swing.JInternalFrame {

  DefaultTableModel modes;
  DefaultTableModel mode;
  DefaultTableModel etmode;
  DefaultTableModel attmode;
 DefaultTableModel modetable3;
Connection con=null;
PreparedStatement pst=null;
ResultSet rs= null;
String rolls;
String etrolls;
String salaryrolls;
Double CHECK_BUDGET;
String CCHECK,CLASSE,CLASSE1,CLASSE2,SUB_CAT,CATB;
    public salary_recaputilation1() {
         con=JavaDbConnect.dbConnect();
        initComponents();
        attCall_IN_LIST();
         jDateChooser1.setDate(new Date());
    }
  public void call_table(){
      SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy");
         String addDate = dateFormats.format(jDateChooser1.getDate());
           try{
           
             String sql="SELECT `NAME`,`LNAME`,`CONTRACT`,ROLL FROM `salaire` WHERE MONTH ='"+jComboBox2.getSelectedItem()+"' AND PRINT='Print' AND REC_FICHE='Null' and ANNEE='"+addDate+"'";
     //  String sql="SELECT ID, `NUM_FACTURE`, `DATE_FACTURE`, `DESCRIPTION`, `DEBIT_QTY`, `DEBIT_PU`, `DEBIT_PT`, `DEBIT_TVA`, `CREDIT_QTY`, `CREDIT_PU`, `CREDIT_PT`, `CREDIT_TVA`, `DATES`, `NUM` FROM facture_fournisseur where compte='"+jComboBox6.getSelectedItem()+"'";
       pst = con.prepareStatement(sql);
      rs= pst.executeQuery();
      
       
       jTable1.setModel(DbUtils.resultSetToTableModel(rs));
       mode=new DefaultTableModel();
       
       
        TableColumn col0=jTable1.getColumnModel().getColumn(0);
        TableColumn col1=jTable1.getColumnModel().getColumn(1);
         TableColumn col2=jTable1.getColumnModel().getColumn(2);
         TableColumn col3=jTable1.getColumnModel().getColumn(3);
      
       
       col0.setPreferredWidth(100);
       col1.setPreferredWidth(100);
       col2.setPreferredWidth(100);
       col3.setPreferredWidth(100);
       
     
      
       
       
      // jTable1.setModel(mode);
       
     
    }catch(SQLException ex ){
     JOptionPane.showMessageDialog(null, ex);
}
    
      
      }
     
     
     public void call_table_ohada(){
      SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy");
         String addDate = dateFormats.format(jDateChooser1.getDate());
           try{
           
             String sql="SELECT SELECT `CODE1`,`JOURNAL`, `DEBIT`,`CREDIT` FROM `ohada_trans` WHERE  ='"+jComboBox2.getSelectedItem()+"' AND PRINT='Print' AND REC_FICHE='Null' and ANNEE='"+addDate+"'";
     //  String sql="SELECT ID, `NUM_FACTURE`, `DATE_FACTURE`, `DESCRIPTION`, `DEBIT_QTY`, `DEBIT_PU`, `DEBIT_PT`, `DEBIT_TVA`, `CREDIT_QTY`, `CREDIT_PU`, `CREDIT_PT`, `CREDIT_TVA`, `DATES`, `NUM` FROM facture_fournisseur where compte='"+jComboBox6.getSelectedItem()+"'";
       pst = con.prepareStatement(sql);
      rs= pst.executeQuery();
      
       
       jTable1.setModel(DbUtils.resultSetToTableModel(rs));
       mode=new DefaultTableModel();
       
       
        TableColumn col0=jTable1.getColumnModel().getColumn(0);
        TableColumn col1=jTable1.getColumnModel().getColumn(1);
         TableColumn col2=jTable1.getColumnModel().getColumn(2);
         TableColumn col3=jTable1.getColumnModel().getColumn(3);
      
       
       col0.setPreferredWidth(100);
       col1.setPreferredWidth(100);
       col2.setPreferredWidth(100);
       col3.setPreferredWidth(100);
       
     
      
       
       
      // jTable1.setModel(mode);
       
     
    }catch(SQLException ex ){
     JOptionPane.showMessageDialog(null, ex);
}
    
      
      }
     
 public void show_photo_from_db()
               
   {
          
          int row= jTable1.getSelectedRow();
          String Table_click = (jTable1.getModel().getValueAt(row,3). toString());
        
          Double CNSS = null,IPR = null,INPP = null,SALAIRE_NET = null;
            
               try{
       
       String sql="SELECT `CNSS_Tous`, `IPR`, `INPP`, `ADV`, `SALAIRE_NET` FROM `salaire` WHERE ROLL='"+Table_click+"' and MONTH='"+jComboBox2.getSelectedItem()+"'";
       pst = con.prepareStatement(sql);
      rs= pst.executeQuery();
      jTable3.setModel(DbUtils.resultSetToTableModel(rs));     
    }catch(SQLException ex ){
     JOptionPane.showMessageDialog(null, ex);
}
               String e;
               Double dd;
                try{
             String sql="SELECT CNSS_Tous,IPR,INPP,SALAIRE_NET FROM `salaire` WHERE ROLL='"+Table_click+"' and MONTH='"+jComboBox2.getSelectedItem()+"'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                CNSS=rs.getDouble("CNSS_tous");
                    IPR=rs.getDouble("IPR");
                        INPP=rs.getDouble("INPP");
                        SALAIRE_NET=rs.getDouble("SALAIRE_NET");
                        
                        dd=CNSS+IPR+INPP+SALAIRE_NET;
                         e= String.format("%.2f",dd);
       //    onem.setText(e);
              jTextField4.setText(e);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex); }  
   }
 
 
 
 public void attCall_IN_LIST()
    {DefaultListModel list = new DefaultListModel();
       
        DefaultListModel listS = new DefaultListModel();
         
        
         
        try{
            String sql="SELECT * FROM  ohada WHERE CLASS BETWEEN 6 AND 7";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
               // String sum=rs.getString("intitule_compte");
                String sums=rs.getString("code");
                 list.addElement(sums);
                
                 jList5.setModel(list);
                  jList6.setModel(list);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
        
         try{
            String sql="SELECT * FROM  ohada  WHERE  class='5'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
               // String sum=rs.getString("intitule_compte");
                String sums=rs.getString("CODE");
               listS.addElement(sums);
                 jList3.setModel(listS);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
         
        
         try{
            String sql="select * from PROJET";
           
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
 
 
 
  public void etroll()
     {
        try{
                  SimpleDateFormat dateFormatsS = new SimpleDateFormat("yyyy-MM-dd");
         String addDateS = dateFormatsS.format(jDateChooser1.getDate());
         
         String sql="SELECT NUM FROM ohada_trans where SUBSTR(NUM,5,10)='"+addDateS+"' ORDER BY NUM DESC LIMIT 1";
             pst=con.prepareStatement(sql);
             rs=pst.executeQuery();
             if(rs.next()){
                 String rl=rs.getString("NUM");
                 int ln=rl.length();
                 String stxt=rl.substring(0,15);
                 String snum=rl.substring(15,ln);
                 int n = Integer.parseInt(snum);
                 n++;
                 snum=Integer.toString(n);
                 rolls=stxt+snum;
             }else{
               rolls= "No: "+addDateS+"/1001";
                
             }
         }catch(NumberFormatException | SQLException e){
             JOptionPane.showMessageDialog(null, e);  
         }
     }
    
  
   public void budgetcheckS(){
  Double SUMBUDGETDEBIT;
 Double SUMBUDGETCREDIT;
    try{
            String sql="SELECT   sum(DEBIT),sum(CREDIT) FROM budget_trans WHERE code='"+jList4.getSelectedValue()+"' AND PROJET='"+buss.getSelectedItem()+"'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
              SUMBUDGETDEBIT=rs.getDouble("sum(DEBIT)");
              SUMBUDGETCREDIT=rs.getDouble("sum(CREDIT)");
             
               CHECK_BUDGET=SUMBUDGETDEBIT-SUMBUDGETCREDIT;
                
                
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
 
 
 }
   
   
      
       String mois;
    String annee;
    public void mois(){
    SimpleDateFormat dateFormat1 = new SimpleDateFormat("MMMM");
        // String addDate1 = dateFormat1.format(jDateChooser1.getDate());
         String addDate1 = jComboBox2.getSelectedItem().toString();
         
         SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy");
         annee = dateFormat2.format(jDateChooser1.getDate());
         if(addDate1.equals("January")){
         mois=("Janvier");
         // total.setText(mois);
         
         }else if(addDate1.equals("February")){
         mois=("Février");
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
          mois=("Octombre");
         }else if(addDate1.equals("November")){
          mois=("Novembre");
         }else if(addDate1.equals("December")){
          mois=("Décembre");
         }
        
    
    }
      
    
       
                    Double sumcredit;  
   Double sumcredits;  

Double a;
Double b;
//Double c= Double.parseDouble(ptc.getText());

 
            



 
  public void savemateriaux()
              
    {
        
        int indexs[]=jTable1.getSelectedRows();
       
        for(int i=0; i < indexs.length;i++){
          String Table_click = (jTable1.getModel().getValueAt(indexs[i],3). toString());
          String NAME = (jTable1.getModel().getValueAt(indexs[i],0). toString());
          
          // int row= jTable1.getSelectedRow();
         // String Table_click = (jTable1.getModel().getValueAt(row,3). toString());
        
          Double CNSSs = null,IPRs = null,INPPs = null,SALAIRE_NETs = null;
          String IPR= null, INPP= null,ADV= null ,SAL = null,CNSS= null;
               try{
       
       String sql="SELECT `CNSS_Tous`, `IPR`, `INPP`, `ADV`, `SALAIRE_NET` FROM `salaire` WHERE ROLL='"+Table_click+"' and MONTH='"+jComboBox2.getSelectedItem()+"'";
       pst = con.prepareStatement(sql);
      rs= pst.executeQuery();
       while(rs.next()){
            CNSS = rs.getString("CNSS_Tous");
          IPR = rs.getString("IPR");
         INPP = rs.getString("INPP");
           ADV = rs.getString("ADV");
          SAL = rs.getString("SALAIRE_NET");
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex); } 

               String e;
               Double dd;
                try{
             String sql="SELECT CNSS_Tous,IPR,INPP,SALAIRE_NET FROM `salaire` WHERE ROLL='"+Table_click+"' and MONTH='"+jComboBox2.getSelectedItem()+"'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                CNSSs=rs.getDouble("CNSS_tous");
                    IPRs=rs.getDouble("IPR");
                        INPPs=rs.getDouble("INPP");
                        SALAIRE_NETs=rs.getDouble("SALAIRE_NET");
                        
                        dd=CNSSs+IPRs+INPPs+SALAIRE_NETs;
                         e= String.format("%.2f",dd);
       //    onem.setText(e);
              jTextField4.setText(e);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex); } 
          
          
          
            
        
           Double c_SAL = Double.parseDouble(SAL);
           Double c_CNSS = Double.parseDouble(CNSS);
           Double c_INPP = Double.parseDouble(INPP);
         Double c_IPR = Double.parseDouble(IPR);
         Double xxx,caissesdebit = null,yyy;
       
         SimpleDateFormat dateFormatSs = new SimpleDateFormat("yyyy-MM-dd");
         String adddates = dateFormatSs.format(jDateChooser1.getDate());
         //adddates ;
         
         
      call_budget();  
   etroll();
   budgetcheckS();
   mois();
   
   
   
     
        //SALAIRE
        
         try{ 
             //con.close();
           //call from journala de banque  
            String sql="select SUM(DEBIT),SUM(CREDIT) from  budget_trans where CODE= '"+jList4.getSelectedValue()+"' and projet='"+buss.getSelectedItem()+"'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                a=rs.getDouble("SUM(DEBIT)");
                b=rs.getDouble("SUM(CREDIT)");
                  
                   sumcredit=(a-b)-c_SAL;
                   sumcredits=(b+a)+c_SAL;
                 
            
            }
            }catch(Exception ex){ JOptionPane.showMessageDialog(null, ex);} 
        
         try {
       //   String sql="INSERT INTO `budget_trans`(`ITEM`, `DEBIT`, `CREDIT`, `SOLD`, `PROJET`, `CODE`, `NUM`, `CAT`, `DATES`, `MOIS`, `ANNEE`,SUB_CAT,CODE_CAT,CODE_SUB_CAT) "+"values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";   
        
        PreparedStatement pst = con.prepareStatement("INSERT INTO `budget_trans`(`ITEM`, `DEBIT`, `CREDIT`, `SOLD`, `PROJET`, `CODE`, `NUM`, `CAT`, `DATES`, `MOIS`, `ANNEE`,`SUB_CAT`, `CODE_CAT`, `CODE_SUB_CAT`) "
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        
        //budget,codeb.getText(),"",ohada_code,ohada_account,
        
        pst.setString(6, codeb.getText());
         pst.setString(5, buss.getSelectedItem().toString());
         pst.setDouble(4, sumcredit);
         pst.setString(1, "Salaire "+NAME+" mois de "+mois);
         pst.setString(3, SAL);
         pst.setString(2,"0");
         pst.setString(7,rolls);
         
         pst.setString(8,CATB);
         
        pst.setString(9,adddates );
        
         pst.setString(10,mois);
          pst.setString(11,annee);
          
           pst.setString(12,SUB_CAT);
           pst.setString(13,code_cat.getText());
           pst.setString(14,code_subcat.getText());
      
       
         
          pst.executeUpdate();
         
        
             //    JOptionPane.showMessageDialog(null,"data saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());}
         
         
          try {
         Double CREDIT = null,SOLD = null;
          Double c = Double.parseDouble(jTextField4.getText());
         try{              //call from journala de banque  
            String sql="select SUM(DEBIT),SUM(CREDIT) from  budget_show where CODE= '"+codeb.getText()+"' and projet='"+buss.getSelectedItem()+"'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
               Double a=rs.getDouble("SUM(DEBIT)");
               Double b=rs.getDouble("SUM(CREDIT)");
               
                   CREDIT=b+c;
                 SOLD=a-CREDIT;
            //jTextField4.setText(d);
            }
            }catch(Exception ex){ JOptionPane.showMessageDialog(null, ex);} 
        PreparedStatement pst = con.prepareStatement("UPDATE `budget_show` SET `CREDIT`='"+CREDIT+"',`SOLD`='"+SOLD+"' WHERE code='"+codeb.getText()+"' and projet='"+buss.getSelectedItem()+"'");
      pst.executeUpdate();
      
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());}
          
          // CAISSE
      // Double xxx,caissesdebit = null,yyy;
            try{  
            
            String sql="select SUM(DEBIT),SUM(CREDIT) from caisses where buss='"+buss.getSelectedItem()+"'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                xxx=rs.getDouble("SUM(DEBIT)");
               // entre.setText(""+xxx);
                yyy=rs.getDouble("SUM(CREDIT)");
                  
                   caissesdebit=(xxx-yyy)-c_SAL;
                //   sorti.setText(""+yyy);
            }
            }catch(Exception ex){ JOptionPane.showMessageDialog(null, ex);} 
       try {
      //   String tmp =jComboBox3.getSelectedItem().toString();
        PreparedStatement pst = con.prepareStatement("INSERT INTO `caisses`(`CODE`,`CODEMERE`,`LIBELE`,`BUSS`,`DEBIT`,`CREDIT`,`DATE`,`COMPTE`,NUM,`MOIS`, `ANNEE`, `BUDGET`, `SOLDE`)"
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?)");
        
        pst.setString(1, code1.getText());
         pst.setString(2,code.getText());
         pst.setString(3, "Salaire "+NAME+"  Mois de "+mois);
         pst.setString(4, buss.getSelectedItem().toString());
         pst.setString(5, "0");
         pst.setDouble(6,c_SAL);
         pst.setString(8,compte.getText());
         pst.setString(9,rolls);
         
          SimpleDateFormat dateFormatsS = new SimpleDateFormat("MMM");
         String addDateS = dateFormatsS.format(jDateChooser1.getDate());
         pst.setString(10, addDateS);
 
         SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy");
         String addDates = dateFormats.format(jDateChooser1.getDate());
         pst.setString(11, addDates);;
         pst.setString(12,codeb.getText());
         pst.setDouble(13,caissesdebit);
       
         
         pst.setString(7,adddates );
         
          pst.executeUpdate();
        
                // JOptionPane.showMessageDialog(null,"Caisse saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
          
 //INSS
 
  try{ 
    // 
      

            String sql="select SUM(DEBIT),SUM(CREDIT) from  budget_trans where CODE= '"+jList4.getSelectedValue()+"' and projet='"+buss.getSelectedItem()+"'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                a=rs.getDouble("SUM(DEBIT)");
                b=rs.getDouble("SUM(CREDIT)");
                  
                   sumcredit=(a-b)-c_CNSS;
                   sumcredits=(b+a)+c_CNSS;
                 
              
            }
            }catch(Exception ex){ JOptionPane.showMessageDialog(null, ex);} 
 
  try {
        
        PreparedStatement pst = con.prepareStatement("INSERT INTO `budget_trans`(`ITEM`, `DEBIT`, `CREDIT`, `SOLD`, `PROJET`, `CODE`, `NUM`, `CAT`, `DATES`, `MOIS`, `ANNEE`,`SUB_CAT`, `CODE_CAT`, `CODE_SUB_CAT`) "
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        
        //budget,codeb.getText(),"",ohada_code,ohada_account,
        
        pst.setString(6, codeb.getText());
         pst.setString(5, buss.getSelectedItem().toString());
         pst.setDouble(4, sumcredit);
         pst.setString(1, "CNSS Pour "+NAME+" mois de "+mois);
         pst.setString(3, CNSS);
         pst.setString(2,"0");
         pst.setString(7,rolls);
         
         pst.setString(8,CATB);
         
        pst.setString(9,adddates );
        
         pst.setString(10,mois);
          pst.setString(11,annee);
      
        pst.setString(12,SUB_CAT);
           pst.setString(13,code_cat.getText());
           pst.setString(14,code_subcat.getText());
         
          pst.executeUpdate();
        
              
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());}
         
         
          try {
        
        
        PreparedStatement pst = con.prepareStatement("UPDATE `budget_show` SET `CREDIT`='"+sumcredits+"',`SOLD`='"+c_CNSS+"' WHERE code='"+jList4.getSelectedValue()+"' and projet='"+buss.getSelectedItem()+"'");
        
       
         
          pst.executeUpdate();
        
               
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());}
          
          // CAISSE
      
            try{  
            
            String sql="select SUM(DEBIT),SUM(CREDIT) from caisses where buss='"+buss.getSelectedItem()+"'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                xxx=rs.getDouble("SUM(DEBIT)");
               // entre.setText(""+xxx);
                yyy=rs.getDouble("SUM(CREDIT)");
                  
                   caissesdebit=(xxx-yyy)-c_CNSS;
                //   sorti.setText(""+yyy);
            }
            }catch(Exception ex){ JOptionPane.showMessageDialog(null, ex);} 
       try {
      //   String tmp =jComboBox3.getSelectedItem().toString();
        PreparedStatement pst = con.prepareStatement("INSERT INTO `caisses`(`CODE`,`CODEMERE`,`LIBELE`,`BUSS`,`DEBIT`,`CREDIT`,`DATE`,`COMPTE`,NUM,`MOIS`, `ANNEE`, `BUDGET`, `SOLDE`)"
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?)");
        
        pst.setString(1, code1.getText());
         pst.setString(2, code.getText());
         pst.setString(3, "CNSS "+NAME+"  Mois de "+mois);
         pst.setString(4, buss.getSelectedItem().toString());
         pst.setString(5, "0");
         pst.setDouble(6,c_CNSS);
         pst.setString(8,compte.getText());
         pst.setString(9,rolls);
         
          SimpleDateFormat dateFormatsS = new SimpleDateFormat("MMM");
         String addDateS = dateFormatsS.format(jDateChooser1.getDate());
         pst.setString(10, addDateS);
 
         SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy");
         String addDates = dateFormats.format(jDateChooser1.getDate());
         pst.setString(11, addDates);;
         pst.setString(12,codeb.getText());
         pst.setDouble(13,caissesdebit);
       
         
         pst.setString(7,adddates );
         
          pst.executeUpdate();
        
                // JOptionPane.showMessageDialog(null,"Caisse saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
          
          //IPR
          
          
           try{ 
              
            String sql="select SUM(DEBIT),SUM(CREDIT) from  budget_trans where CODE= '"+jList4.getSelectedValue()+"' and projet='"+buss.getSelectedItem()+"'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                a=rs.getDouble("SUM(DEBIT)");
                b=rs.getDouble("SUM(CREDIT)");
                  
                   sumcredit=(a-b)-c_IPR;
                   sumcredits=(b+a)+c_IPR;
                 
              
            }
            }catch(Exception ex){ JOptionPane.showMessageDialog(null, ex);} 
          
         
 
  try {
        
        PreparedStatement pst = con.prepareStatement("INSERT INTO `budget_trans`(`ITEM`, `DEBIT`, `CREDIT`, `SOLD`, `PROJET`, `CODE`, `NUM`, `CAT`, `DATES`, `MOIS`, `ANNEE`,`SUB_CAT`, `CODE_CAT`, `CODE_SUB_CAT`) "
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        
        //budget,codeb.getText(),"",ohada_code,ohada_account,
        
        pst.setString(6, codeb.getText());
         pst.setString(5, buss.getSelectedItem().toString());
         pst.setDouble(4, sumcredit);
         pst.setString(1, "IPR Pour "+NAME+" mois de "+mois);
         pst.setString(3, IPR);
         pst.setString(2,"0");
         pst.setString(7,rolls);
         
         pst.setString(8,CATB);
         
        pst.setString(9,adddates );
        
         pst.setString(10,mois);
          pst.setString(11,annee);
      
        pst.setString(12,SUB_CAT);
           pst.setString(13,code_cat.getText());
           pst.setString(14,code_subcat.getText());
         
          pst.executeUpdate();
        
            
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());}
         
         
          try {
             //
             
        
        PreparedStatement pst = con.prepareStatement("UPDATE `budget_show` SET `CREDIT`='"+sumcredits+"',`SOLD`='"+c_IPR+"' WHERE code='"+jList4.getSelectedValue()+"' and projet='"+buss.getSelectedItem()+"'");
        
       
         
          pst.executeUpdate();
        
             
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());}
          // CAISSE
//       Double xxx,caissesdebit = null,yyy;
            try{  
            
            String sql="select SUM(DEBIT),SUM(CREDIT) from caisses where buss='"+buss.getSelectedItem()+"'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                xxx=rs.getDouble("SUM(DEBIT)");
               // entre.setText(""+xxx);
                yyy=rs.getDouble("SUM(CREDIT)");
                  
                   caissesdebit=(xxx-yyy)-c_IPR;
                //   sorti.setText(""+yyy);
            }
            }catch(Exception ex){ JOptionPane.showMessageDialog(null, ex);} 
       try {
      //   String tmp =jComboBox3.getSelectedItem().toString();
        PreparedStatement pst = con.prepareStatement("INSERT INTO `caisses`(`CODE`,`CODEMERE`,`LIBELE`,`BUSS`,`DEBIT`,`CREDIT`,`DATE`,`COMPTE`,NUM,`MOIS`, `ANNEE`, `BUDGET`, `SOLDE`)"
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?)");
        
        pst.setString(1, code.getText());
         pst.setString(2, code.getText());
         pst.setString(3, "IPR Pour "+NAME+"  Mois de "+mois);
         pst.setString(4, buss.getSelectedItem().toString());
         pst.setString(5, "0");
         pst.setDouble(6,c_IPR);
         pst.setString(8,compte.getText());
         pst.setString(9,rolls);
         
          SimpleDateFormat dateFormatsS = new SimpleDateFormat("MMM");
         String addDateS = dateFormatsS.format(jDateChooser1.getDate());
         pst.setString(10, addDateS);
 
         SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy");
         String addDates = dateFormats.format(jDateChooser1.getDate());
         pst.setString(11, addDates);;
         pst.setString(12,codeb.getText());
         pst.setDouble(13,caissesdebit);
       
         
         pst.setString(7,adddates );
         
          pst.executeUpdate();
        
                // JOptionPane.showMessageDialog(null,"Caisse saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
          
           //INPP
          
          
           try{ 
              
            String sql="select SUM(DEBIT),SUM(CREDIT) from  budget_trans where CODE= '"+jList4.getSelectedValue()+"' and projet='"+buss.getSelectedItem()+"'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                a=rs.getDouble("SUM(DEBIT)");
                b=rs.getDouble("SUM(CREDIT)");
                  
                   sumcredit=(a-b)-c_INPP;
                   sumcredits=(b+a)+c_INPP;
                 
              
            }
            }catch(Exception ex){ JOptionPane.showMessageDialog(null, ex);} 
          
         
 
  try {
        
        PreparedStatement pst = con.prepareStatement("INSERT INTO `budget_trans`(`ITEM`, `DEBIT`, `CREDIT`, `SOLD`, `PROJET`, `CODE`, `NUM`, `CAT`, `DATES`, `MOIS`, `ANNEE`,`SUB_CAT`, `CODE_CAT`, `CODE_SUB_CAT`) "
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        
        //budget,codeb.getText(),"",ohada_code,ohada_account,
        
        pst.setString(6, codeb.getText());
         pst.setString(5, buss.getSelectedItem().toString());
         pst.setDouble(4, sumcredit);
         pst.setString(1, "INPP Pour "+NAME+" mois de "+mois);
         pst.setString(3, INPP);
         pst.setString(2,"0");
         pst.setString(7,rolls);
         
         pst.setString(8,CATB);
         
        pst.setString(9,adddates );
        
         pst.setString(10,mois);
          pst.setString(11,annee);
      
        pst.setString(12,SUB_CAT);
           pst.setString(13,code_cat.getText());
           pst.setString(14,code_subcat.getText());
         
          pst.executeUpdate();
        
            
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());}
         
         
          try {
            // 
            
        
        PreparedStatement pst = con.prepareStatement("UPDATE `budget_show` SET `CREDIT`='"+sumcredits+"',`SOLD`='"+c_INPP+"' WHERE code='"+jList4.getSelectedValue()+"' and projet='"+buss.getSelectedItem()+"'");
        
       
         
          pst.executeUpdate();
        
             
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());}
          // CAISSE
      // Double xxx,caissesdebit = null,yyy;
            try{  
            
            String sql="select SUM(DEBIT),SUM(CREDIT) from caisses where buss='"+buss.getSelectedItem()+"'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                xxx=rs.getDouble("SUM(DEBIT)");
               // entre.setText(""+xxx);
                yyy=rs.getDouble("SUM(CREDIT)");
                  
                   caissesdebit=(xxx-yyy)-c_INPP;
                //   sorti.setText(""+yyy);
            }
            }catch(Exception ex){ JOptionPane.showMessageDialog(null, ex);} 
       try {
      //   String tmp =jComboBox3.getSelectedItem().toString();
        PreparedStatement pst = con.prepareStatement("INSERT INTO `caisses`(`CODE`,`CODEMERE`,`LIBELE`,`BUSS`,`DEBIT`,`CREDIT`,`DATE`,`COMPTE`,NUM,`MOIS`, `ANNEE`, `BUDGET`, `SOLDE`)"
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?)");
        
        pst.setString(1, code.getText());
         pst.setString(2, code.getText());
         pst.setString(3, "Salaire "+NAME+"  Mois de "+mois);
         pst.setString(4, buss.getSelectedItem().toString());
         pst.setString(5, "0");
         pst.setDouble(6,c_INPP);
         pst.setString(8,compte.getText());
         pst.setString(9,rolls);
         
          SimpleDateFormat dateFormatsS = new SimpleDateFormat("MMM");
         String addDateS = dateFormatsS.format(jDateChooser1.getDate());
         pst.setString(10, addDateS);
 
         SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy");
         String addDates = dateFormats.format(jDateChooser1.getDate());
         pst.setString(11, addDates);;
         pst.setString(12,codeb.getText());
         pst.setDouble(13,caissesdebit);
       
         
         pst.setString(7,adddates );
         
          pst.executeUpdate();
        
                // JOptionPane.showMessageDialog(null,"Caisse saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
          
         /* if(ADV.equals("0")){
          }else{
          
               //IPR
          
          
           try{ 
               Double c = Double.parseDouble(ADV);
            String sql="select SUM(DEBIT),SUM(CREDIT) from  budget_trans where CODE= '"+jList1.getSelectedValue()+"' and projet='"+projet.getSelectedItem()+"'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                a=rs.getDouble("SUM(DEBIT)");
                b=rs.getDouble("SUM(CREDIT)");
                  
                   sumcredit=(a-b)-c;
                   sumcredits=(b+a)+c;
                 
               
            }
            }catch(Exception ex){ JOptionPane.showMessageDialog(null, ex);} 
          
         
 
  try {
        
        PreparedStatement pst = con.prepareStatement("INSERT INTO `budget_trans`(`ITEM`, `DEBIT`, `CREDIT`, `SOLD`, `PROJET`, `CODE`, `NUM`, `CAT`, `DATES`, `MOIS`, `ANNEE`) "
        +"value(?,?,?,?,?,?,?,?,?,?,?)");
        
        //budget,codeb.getText(),"",ohada_code,ohada_account,
        
        pst.setString(6, codeb.getText());
         pst.setString(5, projet.getSelectedItem().toString());
         pst.setDouble(4, sumcredit);
         pst.setString(1, "Remboursement Avance sur Salaire  "+NAME+" mois de "+mois);
         pst.setString(3, ADV);
         pst.setString(2,"0");
         pst.setString(7,rolls);
         
         pst.setString(8,CATB);
         
        pst.setString(9,adddates );
        
         pst.setString(10,mois);
          pst.setString(11,annee);
      
       
         
          pst.executeUpdate();
        
               
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());}
         
         
          try {
              Double c = Double.parseDouble(ADV);
        
        PreparedStatement pst = con.prepareStatement("UPDATE `budget_show` SET `CREDIT`='"+sumcredits+"',`SOLD`='"+c+"' WHERE code='"+jList1.getSelectedValue()+"' and projet='"+projet.getSelectedItem()+"'");
        
       
         
          pst.executeUpdate();
        
                 
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());}
          
          }*/

 
          ////////////////////////////////////////////////////////////////////////////////////////////
          ///////////////////////////////////////////////////////////////////////////////////////////
     

        //SALAIRE
        
 try {
       
        PreparedStatement pst = con.prepareStatement("INSERT INTO `ohada_trans`(`COMPTE_M`, `COMPTE`, `CODE_M`, `CODE`, `CLASSE`, `SUBSTR`, `DEBIT`, `CREDIT`, `NUM`, `DATES`, `BUSS`, `LIBELLE`, `JOURNAL`, `CODE1`,DEBIT_CDF,CREDIT_CDF)"
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        
        pst.setString(1, compte_m.getText());
        pst.setString(2, compte.getText());
         pst.setString(3, code_m.getText());
         pst.setString(4, code.getText());
         pst.setString(5,CLASSE);
         pst.setString(6, "");
         pst.setString(8,SAL);
         pst.setString(7,"0");
         pst.setString(9,rolls);
         
       SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(jDateChooser1.getDate());
         pst.setString(10, addDate);
         pst.setString(11,buss.getSelectedItem().toString());
         pst.setString(12,"Salaire "+NAME+"  Mois de "+mois);
         pst.setString(13,"[JC]");
          pst.setString(14,code2.getText());
           pst.setString(15,"0");
            pst.setString(16,"0");
       
          
         
          pst.executeUpdate();
        
           //      JOptionPane.showMessageDialog(null,"Caisse saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
      
           try {
        // String tmp =jComboBox3.getSelectedItem().toString();
        PreparedStatement pst = con.prepareStatement("INSERT INTO `ohada_trans`(`COMPTE_M`, `COMPTE`, `CODE_M`, `CODE`, `CLASSE`, `SUBSTR`, `DEBIT`, `CREDIT`, `NUM`, `DATES`, `BUSS`, `LIBELLE`, `JOURNAL`, `CODE1`,DEBIT_CDF,CREDIT_CDF)"
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        
        pst.setString(1, compte_m2.getText());
        pst.setString(2, compte2.getText());
         pst.setString(3, code_m2.getText());
         pst.setString(4, code2.getText());
         pst.setString(5,CLASSE2);
         pst.setString(6, "");
         pst.setString(7,SAL);
         pst.setString(8,"0");
         pst.setString(9,rolls);
         
       SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(jDateChooser1.getDate());
         pst.setString(10, addDate);
         pst.setString(11,buss.getSelectedItem().toString());
         pst.setString(12,"Salaire "+NAME+"  Mois de "+mois);
         pst.setString(13,"[JC]");
          pst.setString(14,code.getText());
           pst.setString(15,"0");
            pst.setString(16,"0");
       
          
         
          pst.executeUpdate();
        
           //      JOptionPane.showMessageDialog(null,"Caisse saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());}
      
      
         
          //CNSS
        
          try {
       
        PreparedStatement pst = con.prepareStatement("INSERT INTO `ohada_trans`(`COMPTE_M`, `COMPTE`, `CODE_M`, `CODE`, `CLASSE`, `SUBSTR`, `DEBIT`, `CREDIT`, `NUM`, `DATES`, `BUSS`, `LIBELLE`, `JOURNAL`, `CODE1`,DEBIT_CDF,CREDIT_CDF)"
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        
        pst.setString(1, compte_m.getText());
        pst.setString(2, compte.getText());
         pst.setString(3, code_m.getText());
         pst.setString(4, code.getText());
         pst.setString(5,CLASSE);
         pst.setString(6, "");
         pst.setString(8,CNSS);
         pst.setString(7,"0");
         pst.setString(9,rolls);
         
       SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(jDateChooser1.getDate());
         pst.setString(10, addDate);
         pst.setString(11,buss.getSelectedItem().toString());
         pst.setString(12,"CNSS "+NAME+"  Mois de "+mois);
         pst.setString(13,"[JC]");
          pst.setString(14,code1.getText());
           pst.setString(15,"0");
            pst.setString(16,"0");
       
          
         
          pst.executeUpdate();
        
           //      JOptionPane.showMessageDialog(null,"Caisse saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
      
           try {
        // String tmp =jComboBox3.getSelectedItem().toString();
        PreparedStatement pst = con.prepareStatement("INSERT INTO `ohada_trans`(`COMPTE_M`, `COMPTE`, `CODE_M`, `CODE`, `CLASSE`, `SUBSTR`, `DEBIT`, `CREDIT`, `NUM`, `DATES`, `BUSS`, `LIBELLE`, `JOURNAL`, `CODE1`,DEBIT_CDF,CREDIT_CDF)"
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        
        pst.setString(1, compte_m1.getText());
        pst.setString(2, compte1.getText());
         pst.setString(3, code_m1.getText());
         pst.setString(4, code1.getText());
         pst.setString(5,CLASSE1);
         pst.setString(6, "");
         pst.setString(7,CNSS);
         pst.setString(8,"0");
         pst.setString(9,rolls);
         
       SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(jDateChooser1.getDate());
         pst.setString(10, addDate);
         pst.setString(11,buss.getSelectedItem().toString());
         pst.setString(12,"CNSS "+NAME+"  Mois de "+mois);
         pst.setString(13,"[JC]");
          pst.setString(14,code.getText());
           pst.setString(15,"0");
            pst.setString(16,"0");
       
          
         
          pst.executeUpdate();
        
           //      JOptionPane.showMessageDialog(null,"Caisse saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());}
          //IPR
        
    try {
       
        PreparedStatement pst = con.prepareStatement("INSERT INTO `ohada_trans`(`COMPTE_M`, `COMPTE`, `CODE_M`, `CODE`, `CLASSE`, `SUBSTR`, `DEBIT`, `CREDIT`, `NUM`, `DATES`, `BUSS`, `LIBELLE`, `JOURNAL`, `CODE1`,DEBIT_CDF,CREDIT_CDF)"
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        
        pst.setString(1, compte_m.getText());
        pst.setString(2, compte.getText());
         pst.setString(3, code_m.getText());
         pst.setString(4, code.getText());
         pst.setString(5,CLASSE);
         pst.setString(6, "");
         pst.setString(8,IPR);
         pst.setString(7,"0");
         pst.setString(9,rolls);
         
       SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(jDateChooser1.getDate());
         pst.setString(10, addDate);
         pst.setString(11,buss.getSelectedItem().toString());
         pst.setString(12,"IPR "+NAME+"  Mois de "+mois);
         pst.setString(13,"[JC]");
          pst.setString(14,code1.getText());
           pst.setString(15,"0");
            pst.setString(16,"0");
       
          
         
          pst.executeUpdate();
        
           //      JOptionPane.showMessageDialog(null,"Caisse saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
      
           try {
        // String tmp =jComboBox3.getSelectedItem().toString();
        PreparedStatement pst = con.prepareStatement("INSERT INTO `ohada_trans`(`COMPTE_M`, `COMPTE`, `CODE_M`, `CODE`, `CLASSE`, `SUBSTR`, `DEBIT`, `CREDIT`, `NUM`, `DATES`, `BUSS`, `LIBELLE`, `JOURNAL`, `CODE1`,DEBIT_CDF,CREDIT_CDF)"
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        
        pst.setString(1, compte_m1.getText());
        pst.setString(2, compte1.getText());
         pst.setString(3, code_m1.getText());
         pst.setString(4, code1.getText());
         pst.setString(5,CLASSE1);
         pst.setString(6, "");
         pst.setString(7,IPR);
         pst.setString(8,"0");
         pst.setString(9,rolls);
         
       SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(jDateChooser1.getDate());
         pst.setString(10, addDate);
         pst.setString(11,buss.getSelectedItem().toString());
         pst.setString(12,"IPR "+NAME+"  Mois de "+mois);
         pst.setString(13,"[JC]");
          pst.setString(14,code.getText());
           pst.setString(15,"0");
            pst.setString(16,"0");
       
          
         
          pst.executeUpdate();
        
           //      JOptionPane.showMessageDialog(null,"Caisse saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());}
         
         
          //INPP
        
 //ohada_code,ohada_account,ohada_account_mere, ohada_class,ohada_code1,ohada_account1,ohada_account_mere1, ohada_class1;       
            try {
       
        PreparedStatement pst = con.prepareStatement("INSERT INTO `ohada_trans`(`COMPTE_M`, `COMPTE`, `CODE_M`, `CODE`, `CLASSE`, `SUBSTR`, `DEBIT`, `CREDIT`, `NUM`, `DATES`, `BUSS`, `LIBELLE`, `JOURNAL`, `CODE1`,DEBIT_CDF,CREDIT_CDF)"
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        
        pst.setString(1, compte_m.getText());
        pst.setString(2, compte.getText());
         pst.setString(3, code_m.getText());
         pst.setString(4, code.getText());
         pst.setString(5,CLASSE);
         pst.setString(6, "");
         pst.setString(8,INPP);
         pst.setString(7,"0");
         pst.setString(9,rolls);
         
       SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(jDateChooser1.getDate());
         pst.setString(10, addDate);
         pst.setString(11,buss.getSelectedItem().toString());
         pst.setString(12,"INPP "+NAME+"  Mois de "+mois);
         pst.setString(13,"[JC]");
          pst.setString(14,code1.getText());
           pst.setString(15,"0");
            pst.setString(16,"0");
       
          
         
          pst.executeUpdate();
        
           //      JOptionPane.showMessageDialog(null,"Caisse saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
      
           try {
        // String tmp =jComboBox3.getSelectedItem().toString();
        PreparedStatement pst = con.prepareStatement("INSERT INTO `ohada_trans`(`COMPTE_M`, `COMPTE`, `CODE_M`, `CODE`, `CLASSE`, `SUBSTR`, `DEBIT`, `CREDIT`, `NUM`, `DATES`, `BUSS`, `LIBELLE`, `JOURNAL`, `CODE1`,DEBIT_CDF,CREDIT_CDF)"
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        
        pst.setString(1, compte_m1.getText());
        pst.setString(2, compte1.getText());
         pst.setString(3, code_m1.getText());
         pst.setString(4, code1.getText());
         pst.setString(5,CLASSE1);
         pst.setString(6, "");
         pst.setString(7,INPP);
         pst.setString(8,"0");
         pst.setString(9,rolls);
         
       SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(jDateChooser1.getDate());
         pst.setString(10, addDate);
         pst.setString(11,buss.getSelectedItem().toString());
         pst.setString(12,"INPP "+NAME+"  Mois de "+mois);
         pst.setString(13,"[JC]");
          pst.setString(14,code.getText());
           pst.setString(15,"0");
            pst.setString(16,"0");
       
          
         
          pst.executeUpdate();
        
           //      JOptionPane.showMessageDialog(null,"Caisse saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());}
         /*
         if(ADV.equals("0")){
         }else{
         
                //IPR
        
 //ohada_code,ohada_account,ohada_account_mere, ohada_class,ohada_code1,ohada_account1,ohada_account_mere1, ohada_class1;       
        try {
        
        PreparedStatement pst = con.prepareStatement("INSERT INTO autres_achats(`CODE`, `CODEMERE`, `LIBELE`, `BUSS`, `DEBIT`, `CREDIT`, `DATE`, `COMPTE`, `NUM`) "
        +"value(?,?,?,?,?,?,?,?,?)");
        
        pst.setString(1, jList2.getSelectedValue());
         pst.setString(2, ohada_account_mere);
         pst.setString(3, "Remboursement Avance sur Salaire "+NAME+"  Mois de "+mois );
         pst.setString(4, projet.getSelectedItem().toString());
         pst.setString(5, ADV);
         pst.setString(6,"0");
         pst.setString(8,"0");
         pst.setString(9,rolls);
       
          SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(jDateChooser1.getDate());
         pst.setString(7, addDate);
         
          pst.executeUpdate();
        
              //  JOptionPane.showMessageDialog(null,"data saved1");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
         
      //ohada_code,ohada_account,ohada_account_mere, ohada_class,ohada_code1,ohada_account1,ohada_account_mere1, ohada_class1;       
   
         try {
        
        PreparedStatement pst = con.prepareStatement("INSERT INTO autres_achats(`CODE`, `CODEMERE`, `LIBELE`, `BUSS`, `DEBIT`, `CREDIT`, `DATE`, `COMPTE`, `NUM`) "
        +"value(?,?,?,?,?,?,?,?,?)");
        
        pst.setString(1, jList3.getSelectedValue());
         pst.setString(2, ohada_account_mere1);
         pst.setString(3, "Remboursement Avance sur Salaire "+NAME+"  Mois de "+mois );
         pst.setString(4, projet.getSelectedItem().toString());
         pst.setString(6, ADV);
         pst.setString(5,"0");
         pst.setString(8,"0");
         pst.setString(9,rolls);
       
          SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(jDateChooser1.getDate());
         pst.setString(7, addDate);
         
          pst.executeUpdate();
        
                JOptionPane.showMessageDialog(null,"data saved1");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
         
         }
         */
          SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy");
         String addDate = dateFormats.format(jDateChooser1.getDate());
   //int row= jTable1.getSelectedRow();
         // String Table_click = (jTable1.getModel().getValueAt(row,3). toString());
      
      try{
    String sql="update salaire set PRINT=?,REC_FICHE=? where roll='"+Table_click+"' and MONTH='"+jComboBox2.getSelectedItem()+"' AND REC_FICHE='Null' and ANNEE='"+addDate+"'";
     
    pst=con.prepareStatement(sql);
   
         pst.setString(1,"Print");
         pst.setString(2,"Pay");
    
    pst.executeUpdate();

    }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}
        }
     
     JOptionPane.showMessageDialog(null,"Transaction Saved");
    }
  
  
  public void update(){
       SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy");
         String addDate = dateFormats.format(jDateChooser1.getDate());
   int row= jTable1.getSelectedRow();
          String Table_click = (jTable1.getModel().getValueAt(row,3). toString());
      
      try{
    String sql="update salaire set PRINT=?,REC_FICHE=? where roll='"+Table_click+"' and MONTH='"+jComboBox2.getSelectedItem()+"' AND REC_FICHE='Null' and ANNEE='"+addDate+"'";
     
    pst=con.prepareStatement(sql);
   
         pst.setString(1,"Print");
         pst.setString(2,"Pay");
    
    pst.executeUpdate();

    }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}

  }
  
  
  
  
  
     public void call_budget(){
  String tmp =(String)jList4.getSelectedValue();
    
     
        try{
          String sql="SELECT item,code FROM  budget_code WHERE code='"+tmp+"' and CAT='"+buss.getSelectedItem()+"'";
          
            pst=con.prepareStatement(sql);
            //pst.setString(1, tmp);
            rs=pst.executeQuery();
            if(rs.next()){
                 
                String ad1 = rs.getString("item");
              lb.setText(ad1);     
               String add1 = rs.getString("code");
              codeb.setText(add1);
          
          }
  
      }catch(Exception ex){
        JOptionPane.showMessageDialog(null,ex);  
      }
        
         try{
          String sql="SELECT LB,LBSUB,SUB_CAT,CAT FROM  budget WHERE code='"+tmp+"' and PROJECT='"+buss.getSelectedItem()+"'";
          
            pst=con.prepareStatement(sql);
            //pst.setString(1, tmp);
            rs=pst.executeQuery();
            if(rs.next()){
                 
                String ad1 = rs.getString("LB");
             code_cat.setText(ad1);     
               String add1 = rs.getString("LBSUB");
             code_subcat.setText(add1);
               SUB_CAT = rs.getString("SUB_CAT");
            CATB = rs.getString("CAT");
          }
  
      }catch(Exception ex){
        JOptionPane.showMessageDialog(null,ex);  
      }
        
 }
        public void clic_attCall_IN_LIST7()
    { 
        DefaultListModel list = new DefaultListModel();
 
        try{
          String sql="SELECT * FROM ohada where CODE ='"+jList3.getSelectedValue()+"'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                //String sum=rs.getString("nom");
                //String sum=rs.getString("nom");
                String sums=rs.getString("NAME");
                compte.setText(sums);
                
                String sums1=rs.getString("CODEMERE");
                code_m.setText(sums1);
                
                String sums2=rs.getString("CODE");
                code.setText(sums2);
                
                String  COMPTEMERES=rs.getString("COMPTEMERE");
                compte_m.setText(COMPTEMERES);
                 
                  CLASSE=rs.getString("CLASS");
                
                 
                
                
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
    }
     public void clic_attCall_IN_LIST8()
    { 
        DefaultListModel list = new DefaultListModel();
 
        try{
          String sql="SELECT * FROM ohada where CODE ='"+jList5.getSelectedValue()+"'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                //String sum=rs.getString("nom");
                //String sum=rs.getString("nom");
                String sums=rs.getString("NAME");
                compte1.setText(sums);
                
                String sums1=rs.getString("CODEMERE");
                code_m1.setText(sums1);
                
                String sums2=rs.getString("CODE");
                code1.setText(sums2);
                
                String  COMPTEMERES=rs.getString("COMPTEMERE");
                compte_m1.setText(COMPTEMERES);
                 
                  CLASSE1=rs.getString("CLASS");
                
                 
                
                
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
    }
         public void clic_attCall_IN_LIST9()
    { 
        DefaultListModel list = new DefaultListModel();
 
        try{
          String sql="SELECT * FROM ohada where CODE ='"+jList6.getSelectedValue()+"'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                //String sum=rs.getString("nom");
                //String sum=rs.getString("nom");
                String sums=rs.getString("NAME");
                compte2.setText(sums);
                
                String sums1=rs.getString("CODEMERE");
                code_m2.setText(sums1);
                
                String sums2=rs.getString("CODE");
                code2.setText(sums2);
                
                String  COMPTEMERES=rs.getString("COMPTEMERE");
                compte_m2.setText(COMPTEMERES);
                 
                  CLASSE2=rs.getString("CLASS");
                
                 
                
                
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
    }
         
         
          public void refreh(){
     lb.setText("LB");
     codeb.setText("");
     jTextField4.setText("");
     
     compte.setText("");
     code.setText("");
     code_m.setText("");
     compte_m.setText("");
     
    
   
     compte1.setText("");
     code1.setText("");
     code_m1.setText("");
     compte_m1.setText("");
     
     compte2.setText("");
     code2.setText("");
     code_m2.setText("");
     compte_m2.setText("");
     
     
     
     
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
        jPanel3 = new javax.swing.JPanel();
        compte_m1 = new javax.swing.JTextField();
        code_m1 = new javax.swing.JTextField();
        code1 = new javax.swing.JTextField();
        compte1 = new javax.swing.JTextField();
        compte = new javax.swing.JTextField();
        code = new javax.swing.JTextField();
        code_m = new javax.swing.JTextField();
        compte_m = new javax.swing.JTextField();
        lb = new javax.swing.JTextField();
        codeb = new javax.swing.JTextField();
        compte2 = new javax.swing.JTextField();
        code2 = new javax.swing.JTextField();
        compte_m2 = new javax.swing.JTextField();
        code_m2 = new javax.swing.JTextField();
        code_cat = new javax.swing.JTextField();
        code_subcat = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jComboBox2 = new javax.swing.JComboBox<>();
        jDateChooser1 = new com.alee.extended.date.WebDateField();
        jTextField4 = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        buss = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList4 = new javax.swing.JList<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList3 = new javax.swing.JList<>();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jScrollPane5 = new javax.swing.JScrollPane();
        jList5 = new javax.swing.JList<>();
        jScrollPane7 = new javax.swing.JScrollPane();
        jList6 = new javax.swing.JList<>();
        jTextField5 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_Course_16px.png"))); // NOI18N

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        compte_m1.setEditable(false);
        compte_m1.setBackground(new java.awt.Color(240, 240, 241));
        compte_m1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        compte_m1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        compte_m1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                compte_m1ActionPerformed(evt);
            }
        });

        code_m1.setEditable(false);
        code_m1.setBackground(new java.awt.Color(240, 240, 241));
        code_m1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        code_m1.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        code1.setEditable(false);
        code1.setBackground(new java.awt.Color(240, 240, 241));
        code1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        code1.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        compte1.setEditable(false);
        compte1.setBackground(new java.awt.Color(240, 240, 241));
        compte1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        compte1.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        compte.setEditable(false);
        compte.setBackground(new java.awt.Color(240, 240, 241));
        compte.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        compte.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        code.setEditable(false);
        code.setBackground(new java.awt.Color(240, 240, 241));
        code.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        code.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        code_m.setEditable(false);
        code_m.setBackground(new java.awt.Color(240, 240, 241));
        code_m.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        code_m.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        compte_m.setEditable(false);
        compte_m.setBackground(new java.awt.Color(240, 240, 241));
        compte_m.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        compte_m.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        compte_m.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                compte_mActionPerformed(evt);
            }
        });

        lb.setEditable(false);
        lb.setBackground(new java.awt.Color(240, 240, 241));
        lb.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lb.setText("LB");

        codeb.setEditable(false);
        codeb.setBackground(new java.awt.Color(240, 240, 241));
        codeb.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        codeb.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        compte2.setEditable(false);
        compte2.setBackground(new java.awt.Color(240, 240, 241));
        compte2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        compte2.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        code2.setEditable(false);
        code2.setBackground(new java.awt.Color(240, 240, 241));
        code2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        code2.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        compte_m2.setEditable(false);
        compte_m2.setBackground(new java.awt.Color(240, 240, 241));
        compte_m2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        compte_m2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        compte_m2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                compte_m2ActionPerformed(evt);
            }
        });

        code_m2.setEditable(false);
        code_m2.setBackground(new java.awt.Color(240, 240, 241));
        code_m2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        code_m2.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        code_cat.setEditable(false);
        code_cat.setBackground(new java.awt.Color(240, 240, 241));
        code_cat.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        code_subcat.setEditable(false);
        code_subcat.setBackground(new java.awt.Color(240, 240, 241));
        code_subcat.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(compte, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(code_m, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(code, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE))
                            .addComponent(compte_m))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(compte_m1, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(compte1, javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addComponent(code_m1, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(code1, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(code_m2, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(code2))
                            .addComponent(compte_m2)
                            .addComponent(compte2, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(lb, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(codeb, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(code_cat, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(code_subcat)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(codeb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(code_cat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(code_subcat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(compte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(code_m, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(code, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(compte_m, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(compte1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(code_m1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(code1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(compte_m1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(compte2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(code_m2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(code2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(compte_m2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel9.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable3MouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(jTable3);

        jComboBox2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" }));
        jComboBox2.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                jComboBox2PopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        jTextField4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField4, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField4, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        buss.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                bussPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("         LB                 TR.                  TAXE               SAL");

        jTextField1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jList4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jList4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jList4MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jList4);

        jList3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jList3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jList3MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jList3);

        jTextField2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jTextField3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jList5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jList5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jList5MouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(jList5);

        jList6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jList6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jList6MouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(jList6);

        jTextField5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton1.setText("Wording");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buss, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addGap(8, 8, 8)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jTextField2, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(buss, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(41, Short.MAX_VALUE))
        );

        jMenu1.setText("X");
        jMenu1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jMenu1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu1MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu1);
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

        setBounds(100, 50, 829, 488);
    }// </editor-fold>//GEN-END:initComponents

    private void compte_m1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_compte_m1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_compte_m1ActionPerformed

    private void compte_mActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_compte_mActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_compte_mActionPerformed

    private void compte_m2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_compte_m2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_compte_m2ActionPerformed

    private void jTable3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable3MouseClicked
//        CCHECK="YES";        // TODO add your handling code here:
    }//GEN-LAST:event_jTable3MouseClicked

    private void jComboBox2PopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_jComboBox2PopupMenuWillBecomeInvisible
        call_table();        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox2PopupMenuWillBecomeInvisible

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        show_photo_from_db();        // TODO add your handling code here:
    }//GEN-LAST:event_jTable1MouseClicked

    private void bussPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_bussPopupMenuWillBecomeInvisible
        try{
            DefaultListModel listSS = new DefaultListModel();
            String sql="SELECT * FROM  budget_code  WHERE  CAT='"+buss.getSelectedItem()+"'";

            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                // String sum=rs.getString("intitule_compte");
                String sums=rs.getString("CODE");
                listSS.addElement(sums);
                jList4.setModel(listSS);
            }
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex); }
        // TODO add your handling code here:
    }//GEN-LAST:event_bussPopupMenuWillBecomeInvisible

    private void jList4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList4MouseClicked
        call_budget();        // TODO add your handling code here:
    }//GEN-LAST:event_jList4MouseClicked

    private void jList3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList3MouseClicked
        clic_attCall_IN_LIST7();        // TODO add your handling code here:
    }//GEN-LAST:event_jList3MouseClicked

    private void jList5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList5MouseClicked
        clic_attCall_IN_LIST8();        // TODO add your handling code here:
    }//GEN-LAST:event_jList5MouseClicked

    private void jList6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList6MouseClicked
        clic_attCall_IN_LIST9();        // TODO add your handling code here:
    }//GEN-LAST:event_jList6MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
            Double SUMDEBIT,SUMCREDIT,CHECK_OHADA = null,CHECK_BUDGET = null;
Double USD= Double.parseDouble(jTextField4.getText());
  try{
       
 
            String sql="SELECT   sum(DEBIT),sum(CREDIT) FROM ohada_trans WHERE code='"+code.getText()+"' AND BUSS='"+buss.getSelectedItem()+"'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
              SUMDEBIT=rs.getDouble("sum(DEBIT)");
              SUMCREDIT=rs.getDouble("sum(CREDIT)");
             
               CHECK_OHADA=SUMDEBIT-SUMCREDIT;
                
                
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
 
   try{
       
            String sql="SELECT   sum(DEBIT),sum(CREDIT) FROM budget_trans WHERE code='"+codeb.getText()+"' AND PROJET='"+buss.getSelectedItem()+"'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
              SUMDEBIT=rs.getDouble("sum(DEBIT)");
              SUMCREDIT=rs.getDouble("sum(CREDIT)");
             
               CHECK_BUDGET=SUMDEBIT-SUMCREDIT;
                
                
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
        
         if(CHECK_OHADA <USD){ 
   JOptionPane.showMessageDialog(null,"Wrong Data"+"\n"+"No Enough Resources  ","Error",JOptionPane.ERROR_MESSAGE);
  }else if(CHECK_BUDGET <USD){
      JOptionPane.showMessageDialog(null,"Wrong Data"+"\n"+"No Budget  ","Error",JOptionPane.ERROR_MESSAGE); 
  }else{

            savemateriaux();
            
            CCHECK=null;
            call_table();
             jTable3.setModel(new DefaultTableModel());
             refreh();
          //  JOptionPane.showMessageDialog(null, "Transaction Saved");
        }

        //mois();// TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jMenu1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu1MouseClicked
this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu1MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> buss;
    private javax.swing.JTextField code;
    private javax.swing.JTextField code1;
    private javax.swing.JTextField code2;
    private javax.swing.JTextField code_cat;
    private javax.swing.JTextField code_m;
    private javax.swing.JTextField code_m1;
    private javax.swing.JTextField code_m2;
    private javax.swing.JTextField code_subcat;
    private javax.swing.JTextField codeb;
    private javax.swing.JTextField compte;
    private javax.swing.JTextField compte1;
    private javax.swing.JTextField compte2;
    private javax.swing.JTextField compte_m;
    private javax.swing.JTextField compte_m1;
    private javax.swing.JTextField compte_m2;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox2;
    private com.alee.extended.date.WebDateField jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JList<String> jList3;
    private javax.swing.JList<String> jList4;
    private javax.swing.JList<String> jList5;
    private javax.swing.JList<String> jList6;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField lb;
    // End of variables declaration//GEN-END:variables
}
