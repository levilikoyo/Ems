/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intreprisemanagementsystem;

import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Dosh
 */
public class fourni_client extends javax.swing.JInternalFrame {
Connection con=null;
PreparedStatement pst=null;
ResultSet rs= null;
 DefaultTableModel mode;
 String rolls,CATB,SUB_CATB,SUBSTR_TIERS;
 Double CHECK_BUDGET,CHECK_OHADA,CHECK_BUDGETP,ECART,SUMDEBIT,  SUMCREDIT,USD;
    public fourni_client() {
        initComponents();
          con=JavaDbConnect.dbConnect();
          total.setHorizontalAlignment( total.CENTER);
//          pt.setHorizontalAlignment( pt.CENTER);
        //  tva.setHorizontalAlignment( tva.CENTER);
          supply.setHorizontalAlignment( supply.CENTER);
          codem.setHorizontalAlignment( codem.CENTER);
          code.setHorizontalAlignment( code.CENTER);
          ptc.setHorizontalAlignment( ptc.CENTER);
        //  tvac.setHorizontalAlignment( tvac.CENTER);
           codec.setHorizontalAlignment( codec.CENTER);
          codemc.setHorizontalAlignment( codemc.CENTER);
          codec1.setHorizontalAlignment(codec1.CENTER);
          codemc1.setHorizontalAlignment(codemc1.CENTER);
          call();
    }
       public void call(){
     try{
            String sql="select * from caisse_dispacting WHERE NAME='"+homme.user.getText()+"' and ACCESS='Yes'";
           
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
    public void Mois(){
    SimpleDateFormat dateFormat1 = new SimpleDateFormat("MMMM");
         String addDate1 = dateFormat1.format(jDateChooser1.getDate());
         
         SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy");
         annee = dateFormat2.format(jDateChooser1.getDate());
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
    
    
 
     
     
    
    
    
    
    
     public void call_combo_compte_client(){
         
    String tmp=jComboBox1.getSelectedItem().toString();
    
     DefaultListModel list = new DefaultListModel();
    if(tmp.equals("Supply")){
    
     try{
           String sqls="select distinct(`NUM_FACTURE`) AS 'No_INVOICE' from   ohada_trans where BUSS='"+buss.getSelectedItem()+"' AND SUBSTR='40' and PAY='No' GROUP BY NUM_FACTURE";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
           // while(rs.next()){
            jTable1.setModel(DbUtils.resultSetToTableModel(rs));
            
            
            
           // }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
      try{
            String sql="SELECT Distinct(code)  FROM budget_code WHERE CAT='"+buss.getSelectedItem()+"' ";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                //String sum=rs.getString("nom");
                String sums=rs.getString("code");
                 list.addElement(sums);
                
                 jList2.setModel(list);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
    }else{
     
     
                
                  try{
        String sqls="select distinct(NUM_FACTURE) AS 'No_INVOICE' from   ohada_trans where BUSS='"+buss.getSelectedItem()+"' AND CLASSE=4 AND SUBSTR <> 40 and PAY='No' GROUP BY NUM_FACTURE";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
          //  while(rs.next()){
            jTable1.setModel(DbUtils.resultSetToTableModel(rs));
            
            
              
          //   TableColumn col0=jTable1.getColumnModel().getColumn(0);
      //  TableColumn col1=jTable1.getColumnModel().getColumn(1);
       
       
      
       
      // col0.setPreferredWidth(150);
      // col1.setPreferredWidth(20);
            
            
          //  }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
     
                
                }
                
                
            }
            

            
       
   
     
     
     
     
     
     
     String Classe,Compte_M,CODE1;
     
        public void select_jTable2()
   {
        String tmp=jComboBox1.getSelectedItem().toString();
    
    
    if(tmp.equals("Supply")){
       
        try{
          int row= jTable1.getSelectedRow();
          String Table_click = (jTable1.getModel().getValueAt(row,0). toString());
          String sql = "SELECT * FROM  ohada_trans WHERE NUM_FACTURE = '"+Table_click+"' AND CLASSE=4 AND SUBSTR<>44";
          pst = con.prepareStatement(sql);
          rs=pst.executeQuery();
          if(rs.next()){
                                     // facture_fournisseur
                 // String sum1=rs.getString("LIBELLE");
               // desc.setText(sum1);
                
                
               
                //  String sum3=rs.getString("CREDIT");
              //  ptc.setText(sum3);
                

               
                
                 String sum6=rs.getString("COMPTE");
                supply.setText(sum6);
                String sum7=rs.getString("CODE_M");
                 codem.setText(sum7);
                  String sum8=rs.getString("CODE");
                code.setText(sum8);
                
                 SUBSTR_TIERS=rs.getString("SUBSTR");
               
                
                 String sum8s=rs.getString("CODE");
                buss.setSelectedItem(sum8s);
                
                
                 String sum11=rs.getString("NUM_FACTURE");
                numf.setText(sum11);
                String sum9=rs.getString("DATES");
                 date.setText(sum9);
                  String sum10=rs.getString("NUM");
                num.setText(sum10);
          Classe=rs.getString("CLASSE");
           Compte_M=rs.getString("COMPTE_M");
           
           CODE1=rs.getString("CODE1");
                
               
                
          }
         
    }catch(SQLException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }
        
        
          Float sum1=null;
          Float sum=null;
          Float sum0=null;
         float c;
         String d;
        try{
          int row= jTable1.getSelectedRow();
          String Table_click = (jTable1.getModel().getValueAt(row,0). toString());
          String sql = "SELECT SUM(DEBIT),SUM(`CREDIT`) FROM  ohada_trans WHERE `NUM_FACTURE` = '"+Table_click+"' and CLASSE=4 AND SUBSTR<>44";
          pst = con.prepareStatement(sql);
          rs=pst.executeQuery();
          if(rs.next()){
              sum0=rs.getFloat("SUM(DEBIT)");
               // debit.setText(sum0);
                sum=rs.getFloat("SUM(`CREDIT`)");
                // credit.setText(sum);
                 //sum1=rs.getFloat("SUM(DEBIT_TVA)");
                // credit.setText(sum1);
                
                
                 c=(sum-sum0);
             d= String.format("%.2f",c);
           // fc3.setText(d);
            total.setText(d);
            // total.setText(java.text.NumberFormat.getCurrencyInstance().format(c));
                  
                  
          }
         
    }catch(SQLException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }     
    }else{
    
         try{
          int row= jTable1.getSelectedRow();
          String Table_click = (jTable1.getModel().getValueAt(row,0). toString());
          String sql = "SELECT * FROM  ohada_trans WHERE NUM_FACTURE = '"+Table_click+"' AND classe=4 and substr<>44";
          pst = con.prepareStatement(sql);
          rs=pst.executeQuery();
          if(rs.next()){
                                     // facture_fournisseur
                 // String sum1=rs.getString("LIBELLE");
                //desc.setText(sum1);
              
                 // String sum3=rs.getString("CREDIT");
             //   ptc.setText(sum3);
                

               
                
                 String sum6=rs.getString("COMPTE");
                supply.setText(sum6);
                String sum7=rs.getString("CODE_M");
                 codem.setText(sum7);
                  String sum8=rs.getString("CODE");
                code.setText(sum8);
                
                Compte_M=rs.getString("COMPTE_M");
             
                 SUBSTR_TIERS=rs.getString("SUBSTR");
                Classe=rs.getString("CLASSE");
               
                
                
                 String sum11=rs.getString("NUM_FACTURE");
                numf.setText(sum11);
                String sum9=rs.getString("DATES");
                 date.setText(sum9);
                  String sum10=rs.getString("NUM");
                num.setText(sum10);
                
               CODE1=rs.getString("CODE"); 
          }
         
    }catch(SQLException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }
         
         
         
         
        
          Float sum1=null;
          Float sum=null;
          Float sum0=null;
         float c;
         String d;
        try{
          int row= jTable1.getSelectedRow();
          String Table_click = (jTable1.getModel().getValueAt(row,0). toString());
          String sql = "SELECT SUM(DEBIT),SUM(`CREDIT`) FROM  ohada_trans WHERE `NUM_FACTURE` = '"+Table_click+"' AND CLASSE='4' and SUBSTR<>44";
          pst = con.prepareStatement(sql);
          rs=pst.executeQuery();
          if(rs.next()){
              sum0=rs.getFloat("SUM(DEBIT)");
               // debit.setText(sum0);
                sum=rs.getFloat("SUM(`CREDIT`)");
                // credit.setText(sum);
                
                
                
                 c=(sum0-sum);
             d= String.format("%.2f",c);
           // fc3.setText(d);
            total.setText(d);
            // total.setText(java.text.NumberFormat.getCurrencyInstance().format(c));
                  
          }
         
    }catch(SQLException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }
        
         
    
    }
    

    
    
   }
        
        
        
        //ohada
        
         public void attCall_IN_LIST()
    {
        DefaultListModel list = new DefaultListModel();
        DefaultListModel list1 = new DefaultListModel();
        
         
        try{
            String sql="select * from ohada where CLASS='5'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
               // String sum=rs.getString("NAME");
                String sums=rs.getString("CODE");
                 list.addElement(sums);
                
                 jList1.setModel(list);
               //  jList2.setModel(list);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
        
         try{
            String sql="SELECT Distinct(code)  FROM   budget_trans ";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                //String sum=rs.getString("nom");
                String sums=rs.getString("code");
                 list1.addElement(sums);
                
                // jList2.setModel(list1);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
         try{
            String sql="select * from projet";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                String sum=rs.getString("projet_NUM");
                  buss.addItem(sum);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex); }   
        
      
    
    }
         
          public void attCall_IN_LISTclient()
    {
        DefaultListModel list = new DefaultListModel();
        DefaultListModel lists = new DefaultListModel();
     
         
        try{
            String sql="select * from ohada where CLASS='5'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
               // String sum=rs.getString("NAME");
                String sums=rs.getString("CODE");
                 list.addElement(sums);
                
                 jList1.setModel(list);
               //  jList2.setModel(list);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
        
          
                
                  jList2.setModel(new DefaultListModel());
          
    try{
            String sql="select * from projet";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                String sum=rs.getString("projet_NUM");
                  buss.addItem(sum);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex); }   
    }
          
         
           public void attCall_LIST()
    {
        DefaultListModel list = new DefaultListModel();
        
         {
        try{
            String sql="select code from ohada where code like '%"+recherche.getText()+"%'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                String sum=rs.getString("code");
               // String sums=rs.getString("LNAME");
                 list.addElement(sum);
                
                 jList1.setModel(list);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
    }
    }
             public void attCall_IN_LIST_serach_LB()
    { 
        DefaultListModel list = new DefaultListModel();
 
        try{
          String sql="SELECT * FROM  budget_code where CODE like '"+recherche1.getText()+"%'  AND CAT='"+buss.getSelectedItem()+"' order by CODE";
             
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                //String sum=rs.getString("nom");
                String sums=rs.getString("code");
                 list.addElement(sums);
                
                 jList2.setModel(list);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
    }
            
            
            //JOURNAL DE CAISSE
            
             public void Call_ID_sum_entre()
    {
        
         
             
             Float sum1=null;
              Float sum2=null;
               Float sum3=null;
        try{
            String sql="select sum(ENTRE) from journal";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
               sum1=rs.getFloat("sum(ENTRE)");
                //  doll3.setText(sum);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        } 
    
       try{
            String sql="select max(USED) from journal";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                sum2=rs.getFloat("max(USED)");
                  //used.setText(sum);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }  
       try{
            String sql="select sum(USD1) from journal";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
               sum3=rs.getFloat("sum(USD1)");
                //  call1.setText(sum);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }  
       
       

  
          
            float c;
           
            c=sum1-sum2;
           
          //calulate(); 
   
    }
       
            
            
            
            
            
            
            
            
            
            
            
            
           String CLASSE_C,CLASSE1,SUBSTR_C;
           public void JLIST_from_COMPTE()
    {
        
    // String tmp =(String)jList1.getSelectedValue();
     //String sql="select name from  stockin";
    
        String tmp =(String)jList1.getSelectedValue();
     String sql="select * from ohada where CODE='"+tmp+"'";
     
        try{
         
          
            pst=con.prepareStatement(sql);
            //pst.setString(1, tmp);
            rs=pst.executeQuery();
            if(rs.next()){
                 
                String ad1 = rs.getString("NAME");
              cnom.setText(ad1);     
               String add1 = rs.getString("CODE");
              codec.setText(add1);
              
               String add3 = rs.getString("CODEMERE");
               codemc.setText(add3);
               
               String add4 = rs.getString("COMPTEMERE");
               compte_mc.setText(add4);
               CLASSE_C = rs.getString("CLASS");
               SUBSTR_C = rs.getString("SUBSTR");
             //  Compte_M = rs.getString("COMPTE_M");
               
               
          
          }
  
      }catch(Exception ex){
        JOptionPane.showMessageDialog(null,ex);  
      }
          
    }   
           
            public void JLIST_from_COMPTE1()
    {
        String tmp =(String)jList2.getSelectedValue();
//          try{
//          String sql="SELECT item,code FROM  budget_code WHERE code='"+tmp+"' and CAT='"+project.getSelectedItem()+"'";
//          
//            pst=con.prepareStatement(sql);
//            //pst.setString(1, tmp);
//            rs=pst.executeQuery();
//            if(rs.next()){
//                 
//                String ad1 = rs.getString("item");
//              cnom1.setText(ad1);     
//               String add1 = rs.getString("code");
//              codec1.setText(add1);
//               //String add = rs.getString("CAT");
//             // code_cat.setText("LB");
//          }
//  
//      }catch(Exception ex){
//        JOptionPane.showMessageDialog(null,ex);  
//      }
//                try{
//          String sql="SELECT CAT,LB,LBSUB,SUB_CAT FROM  budget WHERE code='"+tmp+"' and PROJECT='"+project.getSelectedItem()+"'";
//          
//            pst=con.prepareStatement(sql);
//            //pst.setString(1, tmp);
//            rs=pst.executeQuery();
//            if(rs.next()){
//                 
//                String ad1 = rs.getString("LB");
//             code_cat.setText(ad1);     
//               String add1 = rs.getString("LBSUB");
//             code_subcat.setText(add1);
//             CATB = rs.getString("CAT");
//             SUB_CATB = rs.getString("SUB_CAT");
//              
//          }
//  
//      }catch(Exception ex){
//        JOptionPane.showMessageDialog(null,ex);  
//      }
    
        
    
        try{
         
          String sql="SELECT  CAT,ITEM, CODE FROM  budget WHERE  CODE='"+tmp+"' and PROJECT='"+buss.getSelectedItem()+"'";
      
            pst=con.prepareStatement(sql);
            //pst.setString(1, tmp);
            rs=pst.executeQuery();
            if(rs.next()){
                 
                String ad1 = rs.getString("ITEM");
              cnom1.setText(ad1);     
               String add1 = rs.getString("CODE");
              codec1.setText(add1);
              
               String add3 = rs.getString("CAT");
               codemc1.setText(add3);
               
          
          }
  
      }catch(Exception ex){
        JOptionPane.showMessageDialog(null,ex);  
      }
     try{
          String sql="SELECT LB,LBSUB,CAT,SUB_CAT FROM  budget WHERE code='"+tmp+"' and PROJECT='"+buss.getSelectedItem()+"'";
          
            pst=con.prepareStatement(sql);
            //pst.setString(1, tmp);
            rs=pst.executeQuery();
            if(rs.next()){
                 
                String ad1 = rs.getString("LB");
             code_cat.setText(ad1);     
               String add1 = rs.getString("LBSUB");
             code_subcat.setText(add1);
              CATB = rs.getString("CAT");
             SUB_CATB = rs.getString("SUB_CAT");
              //CATB,SUB_CATB
              
          }
  
      }catch(Exception ex){
        JOptionPane.showMessageDialog(null,ex);  
      }     
    }   
     
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
             Double sumcredit,sumcreditC;  
   Double sumcredits;  
public void call_sum_budget(){
Double a;
Double b;
Double c= Double.parseDouble(ptc.getText());

 try{              //call from journala de banque  
            String sql="select SUM(DEBIT),SUM(CREDIT) from  budget_trans where CODE= '"+codec1.getText()+"' and projet='"+buss.getSelectedItem()+"'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                a=rs.getDouble("SUM(DEBIT)");
                b=rs.getDouble("SUM(CREDIT)");
                  
                   sumcredit=a-b;
                   sumcredits=b+c;
                 
            //jTextField4.setText(d);
            }
            }catch(Exception ex){ JOptionPane.showMessageDialog(null, ex);} 
            


}
      
            
            
        
             public void save(){
                 Double SUMDEBIT = null,SUMCREDIT = null, CHECK_BUDGETP,USDs = null;
                   SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(jDateChooser1.getDate());
              etroll();
       
                if(jComboBox1.getSelectedItem().equals("Supply")){
                           try{
       
            String sql="SELECT   sum(DEBIT),sum(CREDIT) FROM budget_trans WHERE code='"+codec1.getText()+"' AND PROJET='"+buss.getSelectedItem()+"'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
              SUMDEBIT=rs.getDouble("sum(DEBIT)");
              SUMCREDIT=rs.getDouble("sum(CREDIT)");
             
            
               try{
            String sqlS="SELECT NUM FROM PROJET WHERE PROJET_NUM='"+buss.getSelectedItem()+"'";
           
            pst=con.prepareStatement(sqlS);
            rs=pst.executeQuery();
            while(rs.next()){
              ECART=rs.getDouble("NUM");
              
             
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }          
                
            }
            CHECK_BUDGETP=SUMCREDIT+USDs;
            CHECK_BUDGET=(CHECK_BUDGETP*100)/SUMDEBIT;
             jLabel9.setText(""+ SUMDEBIT);
             jLabel7.setText(""+ECART);
            
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }    
                    
                if(CHECK_BUDGET >=ECART){
               
               JOptionPane.showMessageDialog(null,"THIS LINE DOES NOT HAVE ENOUGH BUGDET FOR THIS TRANSACTION!!!","Error",JOptionPane.ERROR_MESSAGE);
 
                }else {
                
                 call_sum_budget();
        Double aaa= Double.parseDouble(ptc.getText());
        Double ccc;
        ccc= sumcredit - aaa;
         // Mois();
          try{
              //                           `ITEM`, `DEBIT`, `CREDIT`, `SOLD`, `PROJET`, `CODE`, `NUM`, `CAT`, `DATES`, `MOIS`, `ANNEE`, `SUB_CAT
    String sql="INSERT INTO `budget_trans`(`ITEM`, `DEBIT`, `CREDIT`, `SOLD`, `projet`, `CODE`, `NUM`, `CAT`, `DATES`, `MOIS`, `ANNEE`,SUB_CAT,CODE_CAT,CODE_SUB_CAT,`ITEMS`) "+"values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    pst=con.prepareStatement(sql);
    pst.setString(1,desc.getText());
    pst.setString(2,"0");
    pst.setString(3,ptc.getText());
    pst.setDouble(4, ccc);
    pst.setString(5,buss.getSelectedItem().toString());
    pst.setString(6,codec1.getText());
    pst.setString(7,rolls);
    
    pst.setString(8,CATB);
    
    pst.setString(9,addDate);
    
    SimpleDateFormat dateFormat1 = new SimpleDateFormat("MMMM");
    SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy");
         String Mois = dateFormat1.format(jDateChooser1.getDate());
         String Annee = dateFormat2.format(jDateChooser1.getDate());
    pst.setString(10,Mois);
    pst.setString (11,Annee);
    pst.setString (12,SUB_CATB);
     pst.setString (13,code_cat.getText());
      pst.setString (14,code_subcat.getText());
       pst.setString (15,cnom1.getText());
   
    
    
    pst.executeUpdate();
    // JOptionPane.showMessageDialog(null,"BUDGET TRANS");
    }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}
          double a,b,c = 0;//call from journala de banque
    try{    
          
        double d=Double.parseDouble(ptc.getText());
            String sql="select SUM(DEBIT),SUM(CREDIT) from  budget_trans where CODE= '"+codec1.getText()+"' and projet='"+buss.getSelectedItem()+"'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                a=rs.getDouble("SUM(DEBIT)");
                b=rs.getDouble("SUM(CREDIT)");
                  
                   sumcreditC=a-b;
                  // c=sumcredit+d;
                   
                 
            //jTextField4.setText(d);
            }
            }catch(Exception ex){ JOptionPane.showMessageDialog(null, ex);} 
            
            try {
        
        PreparedStatement pst = con.prepareStatement("UPDATE `budget_show` SET `CREDIT`='"+sumcredits+"',`SOLD`='"+sumcreditC+"' WHERE code='"+codec1.getText()+"' and projet='"+buss.getSelectedItem()+"'");
        
       
         
          pst.executeUpdate();
        
            //    JOptionPane.showMessageDialog(null,"BUDGET_UP");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());}
          
                  try {//                                                       `COMPTE_M`, `COMPTE`, `CODE_M`, `CODE`, `CLASSE`, `SUBSTR`, `DEBIT`, `CREDIT`, `NUM`, `DATES`, `BUSS`, `LIBELLE`, `JOURNAL`, `CODE1`, `NUM_FACTURE`, `DEBIT_CDF`, `CREDIT_CDF`
        PreparedStatement pst = con.prepareStatement("INSERT INTO `ohada_trans`(`COMPTE_M`, `COMPTE`, `CODE_M`, `CODE`, `CLASSE`, `SUBSTR`, `DEBIT`, `CREDIT`, `NUM`, `DATES`, `BUSS`, `LIBELLE`, `JOURNAL`, `CODE1`,`NUM_FACTURE`, `DEBIT_CDF`, `CREDIT_CDF`,LB,DEVICE)"
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        
        pst.setString(1, compte_mc.getText());
        pst.setString(2, cnom.getText());
         pst.setString(3, codemc.getText());
         pst.setString(4, codec.getText());
         pst.setString(5, CLASSE_C);
         pst.setString(6,SUBSTR_C);
         pst.setString(8,ptc.getText());
         pst.setString(7,"0");
         pst.setString(9,rolls);
         
      
         pst.setString(10, addDate);
         pst.setString(11,buss.getSelectedItem().toString());
         pst.setString(12,desc.getText());
         pst.setString(13,"[JA]");
          pst.setString(14,code.getText());
          pst.setString(15,numf.getText());
          pst.setString(16,"0");
          pst.setString(17,"0");
         pst.setString(18,codec1.getText());
         pst.setString(19,"USD");
         // pst.setString(20,codec1.getText());
       //`NUM_FACTURE`, `DEBIT_CDF`, `CREDIT_CDF`
          
         
          pst.executeUpdate();
        
           //      JOptionPane.showMessageDialog(null,"Caisse saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
                   try {
        PreparedStatement pst = con.prepareStatement("INSERT INTO `ohada_trans`(`COMPTE_M`, `COMPTE`, `CODE_M`, `CODE`, `CLASSE`, `SUBSTR`, `DEBIT`, `CREDIT`, `NUM`, `DATES`, `BUSS`, `LIBELLE`, `JOURNAL`, `CODE1`,`NUM_FACTURE`, `DEBIT_CDF`, `CREDIT_CDF`,LB,DEVICE)"
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        
        pst.setString(1,Compte_M);
        pst.setString(2, supply.getText());
         pst.setString(3, codem.getText());
         pst.setString(4, code.getText());
         pst.setString(5, Classe);
         pst.setString(6, "40");
         pst.setString(7,ptc.getText());
         pst.setString(8,"0");
         pst.setString(9,rolls);
         
      
         pst.setString(10, addDate);
         pst.setString(11,buss.getSelectedItem().toString());
         pst.setString(12,desc.getText());
         pst.setString(13,"[JA]");
          pst.setString(14,codec.getText());
          pst.setString(15,numf.getText());
          pst.setString(16,"0");
          pst.setString(17,"0");
           pst.setString(18,codec1.getText());
            pst.setString(19,"USD");
           // pst.setString(20,codec1.getText());
       
          
         
          pst.executeUpdate();
        
               //  JOptionPane.showMessageDialog(null,"Transaction Saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
           
                   
                  int row= jTable1.getSelectedRow();
          String Table_click = (jTable1.getModel().getValueAt(row,0). toString());
          
         try{
          String sql = "SELECT SUM(DEBIT),SUM(`CREDIT`) FROM  ohada_trans WHERE `NUM_FACTURE` = '"+Table_click+"' AND BUSS='"+buss.getSelectedItem()+"' and SUBSTR=40";
          pst = con.prepareStatement(sql);
          rs=pst.executeQuery();
          if(rs.next()){
              Double cc;
            Double  sum0=rs.getDouble("SUM(DEBIT)");
               // debit.setText(sum0);
              Double  sum1=rs.getDouble("SUM(`CREDIT`)");
                // credit.setText(sum);
                
                
                
                 cc=(sum0-sum1);
                 num_fact2.setText(""+cc);
                 
           if(num_fact2.getText().equals("0.0")){
             try {
        PreparedStatement pst = con.prepareStatement("UPDATE `ohada_trans` SET PAY=? WHERE NUM_FACTURE='"+Table_click+"'");
        pst.setString(1,"Pay");
          pst.executeUpdate();
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
           }else{}
                  
          }
         
    }catch(SQLException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }
        
         
    //CAISSE
    
    if(SUBSTR_C.equals("57")){
        
        // CAISSE
       Double xxx,caissesdebit = null,yyy;
       Double USD= Double.parseDouble(ptc.getText());
       try{  
            



//call from journala de banque  
            String sql="select SUM(DEBIT),SUM(CREDIT) from caisses where buss='"+buss.getSelectedItem()+"'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            if(rs.next()){
                xxx=rs.getDouble("SUM(DEBIT)");
               // entre.setText(""+xxx);
                yyy=rs.getDouble("SUM(CREDIT)");
                  
                   caissesdebit=(xxx-yyy)+USD;
                //   sorti.setText(""+yyy);
            }
            }catch(Exception ex){ JOptionPane.showMessageDialog(null, ex);} 
       try {
        // String tmp =jComboBox3.getSelectedItem().toString();
        PreparedStatement pst = con.prepareStatement("INSERT INTO `caisses`(`CODE`,`CODEMERE`,`LIBELE`,`BUSS`,`DEBIT`,`CREDIT`,`DATE`,`COMPTE`,NUM,`MOIS`, `ANNEE`, `BUDGET`, `SOLDE`, `DEVICE`)"
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        
        pst.setString(1, code.getText());
         pst.setString(2,codec.getText());
         pst.setString(3, desc.getText());
         pst.setString(4, buss.getSelectedItem().toString());
         pst.setString(5, "0");
         pst.setString(6,ptc.getText());
         pst.setString(8,cnom.getText());
         pst.setString(9,rolls);
         
          SimpleDateFormat dateFormatsS = new SimpleDateFormat("MMM");
         String addDateS = dateFormatsS.format(jDateChooser1.getDate());
         pst.setString(10, addDateS);
 
         pst.setInt(11,year.getValue());
         pst.setString(12,codec1.getText());
         pst.setString(13,"OK");
         pst.setString(14,"USD");
       
         
         pst.setString(7, addDate);
         
          pst.executeUpdate();
        
                 JOptionPane.showMessageDialog(null,"Transaction In Caisse Saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
    
    }else{ JOptionPane.showMessageDialog(null,"Transaction In Bank Saved");}
             
                   
                   
                   
                   
                   
                }
                }else{
                
                  try {
   PreparedStatement pst = con.prepareStatement("INSERT INTO `ohada_trans`(`COMPTE_M`, `COMPTE`, `CODE_M`, `CODE`, `CLASSE`, `SUBSTR`, `DEBIT`, `CREDIT`, `NUM`, `DATES`, `BUSS`, `LIBELLE`, `JOURNAL`, `CODE1`,`NUM_FACTURE`, `DEBIT_CDF`, `CREDIT_CDF`,LB,DEVICE)"
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        
        pst.setString(1, Compte_M);
        pst.setString(2, supply.getText());
         pst.setString(3, codem.getText());
         pst.setString(4, code.getText());
         pst.setString(5, Classe);
         pst.setString(7, "0");
         pst.setString(8,ptc.getText());
         pst.setString(6,"41");
         pst.setString(9,rolls);
         
     
         pst.setString(10, addDate);
         pst.setString(11,buss.getSelectedItem().toString());
         pst.setString(12,desc.getText());
         pst.setString(13,"[JA]");
          pst.setString(14,codec.getText());
           pst.setString(15,numf.getText());
          pst.setString(16,"0");
          pst.setString(17,"0");
           pst.setString(18,"");
            pst.setString(19,"USD");
          
       
          
         
          pst.executeUpdate();
        
           //      JOptionPane.showMessageDialog(null,"Caisse saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
                    try {
        PreparedStatement pst = con.prepareStatement("INSERT INTO `ohada_trans`(`COMPTE_M`, `COMPTE`, `CODE_M`, `CODE`, `CLASSE`, `SUBSTR`, `DEBIT`, `CREDIT`, `NUM`, `DATES`, `BUSS`, `LIBELLE`, `JOURNAL`, `CODE1`,`NUM_FACTURE`, `DEBIT_CDF`, `CREDIT_CDF`,LB,DEVICE)"
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        
        pst.setString(1, compte_mc.getText());
        pst.setString(2, cnom.getText());
         pst.setString(3, codemc.getText());
         pst.setString(4, codec.getText());
         pst.setString(5, "5");
         pst.setString(6, SUBSTR_C);
         pst.setString(7,ptc.getText());
         pst.setString(8,"0");
         pst.setString(9,rolls);
         
      
         pst.setString(10, addDate);
         pst.setString(11,buss.getSelectedItem().toString());
         pst.setString(12,desc.getText());
         pst.setString(13,"[JA]");
          pst.setString(14,code.getText());
           pst.setString(15,numf.getText());
          pst.setString(16,"0");
          pst.setString(17,"0");
           pst.setString(18,"");
            pst.setString(19,"USD");
       
          
         
          pst.executeUpdate();
        
           //      JOptionPane.showMessageDialog(null,"Caisse saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
               int row= jTable1.getSelectedRow();
          String Table_click = (jTable1.getModel().getValueAt(row,0). toString());
          
         try{
          String sql = "SELECT SUM(DEBIT),SUM(`CREDIT`) FROM  ohada_trans WHERE `NUM_FACTURE` = '"+Table_click+"' AND BUSS='"+buss.getSelectedItem()+"' and CLASSE=4 AND SUBSTR<> 40";
          pst = con.prepareStatement(sql);
          rs=pst.executeQuery();
          if(rs.next()){
              Double c;
            Double  sum0=rs.getDouble("SUM(DEBIT)");
               // debit.setText(sum0);
              Double  sum1=rs.getDouble("SUM(`CREDIT`)");
                // credit.setText(sum);
                
                
                
                 c=(sum0-sum1);
                 num_fact2.setText(""+c);
                 
           if(num_fact2.getText().equals("0.0")){
             try {
        PreparedStatement pst = con.prepareStatement("UPDATE `ohada_trans` SET PAY=? WHERE NUM_FACTURE='"+Table_click+"'");
        pst.setString(1,"Pay");
          pst.executeUpdate();
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
           }else{}
                  
          }
         
    }catch(SQLException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }
        
         
    //CAISSE
    
    if(SUBSTR_C.equals("57")){
        
        // CAISSE
       Double xxx,caissesdebit = null,yyy;
       Double USD= Double.parseDouble(ptc.getText());
       try{  
            



//call from journala de banque  
            String sql="select SUM(DEBIT),SUM(CREDIT) from caisses where buss='"+buss.getSelectedItem()+"'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                xxx=rs.getDouble("SUM(DEBIT)");
               // entre.setText(""+xxx);
                yyy=rs.getDouble("SUM(CREDIT)");
                  
                   caissesdebit=(xxx-yyy)+USD;
                //   sorti.setText(""+yyy);
            }
            }catch(Exception ex){ JOptionPane.showMessageDialog(null, ex);} 
       try {
        // String tmp =jComboBox3.getSelectedItem().toString();
        PreparedStatement pst = con.prepareStatement("INSERT INTO `caisses`(`CODE`,`CODEMERE`,`LIBELE`,`BUSS`,`DEBIT`,`CREDIT`,`DATE`,`COMPTE`,NUM,`MOIS`, `ANNEE`, `BUDGET`, `SOLDE`,`DEVICE`)"
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        
        pst.setString(1, CODE1);
         pst.setString(2, codec.getText());
         pst.setString(3, desc.getText());
         pst.setString(4, buss.getSelectedItem().toString());
         pst.setString(6, "0");
         pst.setString(5,ptc.getText());
         pst.setString(8,cnom.getText());
         pst.setString(9,rolls);
         
          SimpleDateFormat dateFormatsS = new SimpleDateFormat("MMM");
         String addDateS = dateFormatsS.format(jDateChooser1.getDate());
         pst.setString(10, addDateS);
 
         pst.setInt(11,year.getValue());
         pst.setString(12,"");
         pst.setString(13,"OK");
       pst.setString(14,"USD");
        
         pst.setString(7, addDate);
         
          pst.executeUpdate();
        
                 JOptionPane.showMessageDialog(null,"Transaction In Caisse Saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
    
    }else{ JOptionPane.showMessageDialog(null,"Transaction In Bank Saved");}    
                }
           
             
             }
             
             public void saves(){
              
                   SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(jDateChooser1.getDate());
              etroll();
              
              Double a=Double.parseDouble(ptc.getText());
       
                if(jComboBox1.getSelectedItem().equals("Supply")){
        try{
       
 
            String sql="SELECT   sum(DEBIT),sum(CREDIT) FROM ohada_trans WHERE code='"+codec.getText()+"' AND BUSS='"+buss.getSelectedItem()+"'";
           
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
    //    JOptionPane.showMessageDialog(null,"this is check ohada "+CHECK_OHADA);
 
   try{
       
            String sql="SELECT   sum(DEBIT),sum(CREDIT) FROM budget_trans WHERE code='"+codec1.getText()+"' AND PROJET='"+buss.getSelectedItem()+"'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
              SUMDEBIT=rs.getDouble("sum(DEBIT)");
              SUMCREDIT=rs.getDouble("sum(CREDIT)");
             }
            CHECK_BUDGETP=SUMCREDIT+a;
            CHECK_BUDGET=(CHECK_BUDGETP*100)/SUMDEBIT;
           // jMenu7.setText(""+ECART);
             
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
   //JOptionPane.showMessageDialog(null,"this is check budget "+CHECK_BUDGET);
           try{
            String sqlS="SELECT   NUM FROM PROJET WHERE PROJET_NUM='"+buss.getSelectedItem()+"'";
           
            pst=con.prepareStatement(sqlS);
            rs=pst.executeQuery();
            while(rs.next()){
              ECART=rs.getDouble("NUM");
             
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
  // JOptionPane.showMessageDialog(null,"this is ecart "+ECART);
if(CHECK_OHADA <a){ 
      
   JOptionPane.showMessageDialog(null,"Wrong Data"+"\n"+"No Enough Resources  in the "+" '"+cnom.getText()+"'","Error",JOptionPane.ERROR_MESSAGE);
  }else if(CHECK_BUDGET >=ECART){
       JOptionPane.showMessageDialog(null,"Wrong Data"+"\n"+"No Enough Budget  in line "+" '"+cnom1.getText()+"'","Error",JOptionPane.ERROR_MESSAGE);
  }else if(jDateChooser1.getText().equals("")||cnom.getText().equals("")||cnom1.getText().equals("")||compte_mc.getText().equals("")||jComboBox2.getSelectedItem().equals("Batchs")){
 JOptionPane.showMessageDialog(null,"Wrong Data","Error",JOptionPane.ERROR_MESSAGE);
}else{
       try{
              //                           `ITEM`, `DEBIT`, `CREDIT`, `SOLD`, `PROJET`, `CODE`, `NUM`, `CAT`, `DATES`, `MOIS`, `ANNEE`, `SUB_CAT
    String sql="INSERT INTO `budget_trans`(`ITEM`, `DEBIT`, `CREDIT`, `SOLD`, `projet`, `CODE`, `NUM`, `CAT`, `DATES`, `MOIS`, `ANNEE`,SUB_CAT,CODE_CAT,CODE_SUB_CAT,`ITEMS`,BATCH) "+"values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    pst=con.prepareStatement(sql);
    pst.setString(1,desc.getText());
    pst.setString(2,"0");
    pst.setString(3,ptc.getText());
    pst.setDouble(4,0);
    pst.setString(5,buss.getSelectedItem().toString());
    pst.setString(6,codec1.getText());
    pst.setString(7,rolls);
    
    pst.setString(8,CATB);
    
    pst.setString(9,addDate);
    
    SimpleDateFormat dateFormat1 = new SimpleDateFormat("MMMM");
    SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy");
         String Mois = dateFormat1.format(jDateChooser1.getDate());
         String Annee = dateFormat2.format(jDateChooser1.getDate());
    pst.setString(10,Mois);
    pst.setString (11,Annee);
    pst.setString (12,SUB_CATB);
     pst.setString (13,code_cat.getText());
      pst.setString (14,code_subcat.getText());
       pst.setString (15,cnom1.getText());
          pst.setString (16,jComboBox2.getSelectedItem().toString());
   
    
    
    pst.executeUpdate();
    // JOptionPane.showMessageDialog(null,"BUDGET TRANS");
    }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}
       try {//                                                       `COMPTE_M`, `COMPTE`, `CODE_M`, `CODE`, `CLASSE`, `SUBSTR`, `DEBIT`, `CREDIT`, `NUM`, `DATES`, `BUSS`, `LIBELLE`, `JOURNAL`, `CODE1`, `NUM_FACTURE`, `DEBIT_CDF`, `CREDIT_CDF`
        PreparedStatement pst = con.prepareStatement("INSERT INTO `ohada_trans`(`COMPTE_M`, `COMPTE`, `CODE_M`, `CODE`, `CLASSE`, `SUBSTR`, `DEBIT`, `CREDIT`, `NUM`, `DATES`, `BUSS`, `LIBELLE`, `JOURNAL`, `CODE1`,`NUM_FACTURE`, `DEBIT_CDF`, `CREDIT_CDF`,LB,DEVICE)"
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        
        pst.setString(1, compte_mc.getText());
        pst.setString(2, cnom.getText());
         pst.setString(3, codemc.getText());
         pst.setString(4, codec.getText());
         pst.setString(5, CLASSE_C);
         pst.setString(6,SUBSTR_C);
         pst.setString(8,ptc.getText());
         pst.setString(7,"0");
         pst.setString(9,rolls);
         
      
         pst.setString(10, addDate);
         pst.setString(11,buss.getSelectedItem().toString());
         pst.setString(12,desc.getText());
         pst.setString(13,"[JA]");
          pst.setString(14,code.getText());
          pst.setString(15,numf.getText());
          pst.setString(16,"0");
          pst.setString(17,"0");
         pst.setString(18,codec1.getText());
         pst.setString(19,"USD");
         // pst.setString(20,codec1.getText());
       //`NUM_FACTURE`, `DEBIT_CDF`, `CREDIT_CDF`
          
         
          pst.executeUpdate();
        
           //      JOptionPane.showMessageDialog(null,"Caisse saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
                   try {
        PreparedStatement pst = con.prepareStatement("INSERT INTO `ohada_trans`(`COMPTE_M`, `COMPTE`, `CODE_M`, `CODE`, `CLASSE`, `SUBSTR`, `DEBIT`, `CREDIT`, `NUM`, `DATES`, `BUSS`, `LIBELLE`, `JOURNAL`, `CODE1`,`NUM_FACTURE`, `DEBIT_CDF`, `CREDIT_CDF`,LB,DEVICE)"
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        
        pst.setString(1,Compte_M);
        pst.setString(2, supply.getText());
         pst.setString(3, codem.getText());
         pst.setString(4, code.getText());
         pst.setString(5, Classe);
         pst.setString(6, "40");
         pst.setString(7,ptc.getText());
         pst.setString(8,"0");
         pst.setString(9,rolls);
         
      
         pst.setString(10, addDate);
         pst.setString(11,buss.getSelectedItem().toString());
         pst.setString(12,desc.getText());
         pst.setString(13,"[JA]");
          pst.setString(14,codec.getText());
          pst.setString(15,numf.getText());
          pst.setString(16,"0");
          pst.setString(17,"0");
           pst.setString(18,codec1.getText());
            pst.setString(19,"USD");
           // pst.setString(20,codec1.getText());
       
          
         
          pst.executeUpdate();
        
               //  JOptionPane.showMessageDialog(null,"Transaction Saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
           
                   
                  int row= jTable1.getSelectedRow();
          String Table_click = (jTable1.getModel().getValueAt(row,0). toString());
          
         try{
          String sql = "SELECT SUM(DEBIT),SUM(`CREDIT`) FROM  ohada_trans WHERE `NUM_FACTURE` = '"+Table_click+"' AND BUSS='"+buss.getSelectedItem()+"' and SUBSTR=40";
          pst = con.prepareStatement(sql);
          rs=pst.executeQuery();
          if(rs.next()){
              Double cc;
            Double  sum0=rs.getDouble("SUM(DEBIT)");
               // debit.setText(sum0);
              Double  sum1=rs.getDouble("SUM(`CREDIT`)");
                // credit.setText(sum);
                
                
                
                 cc=(sum0-sum1);
                 num_fact2.setText(""+cc);
                 
           if(num_fact2.getText().equals("0.0")){
             try {
        PreparedStatement pst = con.prepareStatement("UPDATE `ohada_trans` SET PAY=? WHERE NUM_FACTURE='"+Table_click+"'");
        pst.setString(1,"Pay");
          pst.executeUpdate();
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
           }else{}
                  
          }
         
    }catch(SQLException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }
   JOptionPane.showMessageDialog(null,"Transaction Saved In "+cnom.getText());
  }
          
             
        
         
   //CLIENT
  
                }else{
            if(jDateChooser1.getText().equals("")||cnom.getText().equals("")){
 JOptionPane.showMessageDialog(null,"Wrong Data","Error",JOptionPane.ERROR_MESSAGE);
}else{  
            
       try {
   PreparedStatement pst = con.prepareStatement("INSERT INTO `ohada_trans`(`COMPTE_M`, `COMPTE`, `CODE_M`, `CODE`, `CLASSE`, `SUBSTR`, `DEBIT`, `CREDIT`, `NUM`, `DATES`, `BUSS`, `LIBELLE`, `JOURNAL`, `CODE1`,`NUM_FACTURE`, `DEBIT_CDF`, `CREDIT_CDF`,LB,DEVICE)"
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        
        pst.setString(1, Compte_M);
        pst.setString(2, supply.getText());
         pst.setString(3, codem.getText());
         pst.setString(4, code.getText());
         pst.setString(5, Classe);
         pst.setString(7, "0");
         pst.setString(8,ptc.getText());
         pst.setString(6,SUBSTR_TIERS);
         pst.setString(9,rolls);
         
     
         pst.setString(10, addDate);
         pst.setString(11,buss.getSelectedItem().toString());
         pst.setString(12,desc.getText());
         pst.setString(13,"[JA]");
          pst.setString(14,codec.getText());
           pst.setString(15,numf.getText());
          pst.setString(16,"0");
          pst.setString(17,"0");
           pst.setString(18,"");
            pst.setString(19,"USD");
          
       
          
         
          pst.executeUpdate();
        
           //      JOptionPane.showMessageDialog(null,"Caisse saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
                    try {
        PreparedStatement pst = con.prepareStatement("INSERT INTO `ohada_trans`(`COMPTE_M`, `COMPTE`, `CODE_M`, `CODE`, `CLASSE`, `SUBSTR`, `DEBIT`, `CREDIT`, `NUM`, `DATES`, `BUSS`, `LIBELLE`, `JOURNAL`, `CODE1`,`NUM_FACTURE`, `DEBIT_CDF`, `CREDIT_CDF`,LB,DEVICE)"
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        
        pst.setString(1, compte_mc.getText());
        pst.setString(2, cnom.getText());
         pst.setString(3, codemc.getText());
         pst.setString(4, codec.getText());
         pst.setString(5, "5");
         pst.setString(6, SUBSTR_C);
         pst.setString(7,ptc.getText());
         pst.setString(8,"0");
         pst.setString(9,rolls);
         
      
         pst.setString(10, addDate);
         pst.setString(11,buss.getSelectedItem().toString());
         pst.setString(12,desc.getText());
         pst.setString(13,"[JA]");
          pst.setString(14,code.getText());
           pst.setString(15,numf.getText());
          pst.setString(16,"0");
          pst.setString(17,"0");
           pst.setString(18,"");
            pst.setString(19,"USD");
       
          
         
          pst.executeUpdate();
        
           //      JOptionPane.showMessageDialog(null,"Caisse saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
               int row= jTable1.getSelectedRow();
          String Table_click = (jTable1.getModel().getValueAt(row,0). toString());
          
         try{
          String sql = "SELECT SUM(DEBIT),SUM(`CREDIT`) FROM  ohada_trans WHERE `NUM_FACTURE` = '"+Table_click+"' AND BUSS='"+buss.getSelectedItem()+"' and CLASSE=4 AND SUBSTR<> 40";
          pst = con.prepareStatement(sql);
          rs=pst.executeQuery();
          if(rs.next()){
              Double c;
            Double  sum0=rs.getDouble("SUM(DEBIT)");
               // debit.setText(sum0);
              Double  sum1=rs.getDouble("SUM(`CREDIT`)");
                // credit.setText(sum);
                
                
                
                 c=(sum0-sum1);
                 num_fact2.setText(""+c);
                 
           if(num_fact2.getText().equals("0.0")){
             try {
        PreparedStatement pst = con.prepareStatement("UPDATE `ohada_trans` SET PAY=? WHERE NUM_FACTURE='"+Table_click+"'");
        pst.setString(1,"Pay");
          pst.executeUpdate();
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
           }else{}
                  
          }
         
    }catch(SQLException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }   
          //JOptionPane.showMessageDialog(null,"Transaction Saved In "+cnom.getText());
            }  
                  
         JOptionPane.showMessageDialog(null,"Transaction Saved In "+cnom.getText());
         
                }
             
             }
            
            
            
 
 //ici fin else
          
            
              
              
              
              
              
              
              
  public void numberFormat(){
  
  String s = total.getText();
    String forma = java.text.NumberFormat.getCurrencyInstance().format(s);
          total.setText(forma);
          
  
  }            
   public void refresh(){
    desc.setText("");
    ptc.setText("0.00");
    supply.setText("");
    codem.setText("");
    code.setText("");
    numf.setText("");
    date.setText("");
    num.setText("");
    total.setText("");
    cnom.setText("");
    compte_mc.setText("");
    codec.setText("");
    codemc.setText("");
    cnom1.setText("");
    compte_m1.setText("");
    codec1.setText("");
    codemc1.setText("");
    code_cat.setText("");
    code_subcat.setText("");
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
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        desc = new javax.swing.JEditorPane();
        jPanel15 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        ptc = new javax.swing.JTextField();
        date = new javax.swing.JTextField();
        num = new javax.swing.JTextField();
        numf = new javax.swing.JTextField();
        num_fact2 = new javax.swing.JTextField();
        code_cat = new javax.swing.JLabel();
        code_subcat = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jComboBox1 = new javax.swing.JComboBox<>();
        year = new com.toedter.calendar.JYearChooser();
        jDateChooser1 = new com.alee.extended.date.WebDateField();
        buss = new javax.swing.JComboBox<>();
        jComboBox2 = new javax.swing.JComboBox<>();
        jPanel6 = new javax.swing.JPanel();
        total = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        supply = new javax.swing.JTextField();
        codem = new javax.swing.JTextField();
        code = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        recherche = new javax.swing.JTextField();
        jPanel12 = new javax.swing.JPanel();
        recherche1 = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList<>();
        jPanel10 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        cnom = new javax.swing.JEditorPane();
        codec = new javax.swing.JTextField();
        codemc = new javax.swing.JTextField();
        compte_mc = new javax.swing.JTextField();
        jPanel14 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        cnom1 = new javax.swing.JEditorPane();
        codec1 = new javax.swing.JTextField();
        codemc1 = new javax.swing.JTextField();
        compte_m1 = new javax.swing.JTextField();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();

        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_Accounting_16px.png"))); // NOI18N

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "No.Invoice & Receipt", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jTable1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
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
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setText("Description");

        desc.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jScrollPane7.setViewportView(desc);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("T.Price to pay");

        ptc.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        ptc.setText("0.00");
        ptc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ptcMouseClicked(evt);
            }
        });
        ptc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                ptcKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ptc, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ptc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        date.setEditable(false);
        date.setBackground(new java.awt.Color(240, 240, 241));
        date.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        num.setEditable(false);
        num.setBackground(new java.awt.Color(240, 240, 241));
        num.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        numf.setEditable(false);
        numf.setBackground(new java.awt.Color(240, 240, 241));
        numf.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        num_fact2.setEditable(false);
        num_fact2.setBackground(new java.awt.Color(240, 240, 241));
        num_fact2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        code_cat.setForeground(new java.awt.Color(240, 240, 240));

        code_subcat.setForeground(new java.awt.Color(240, 240, 240));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 284, Short.MAX_VALUE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(27, 27, 27)
                                .addComponent(code_cat)
                                .addGap(18, 18, 18)
                                .addComponent(code_subcat)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(date, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                            .addComponent(numf))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(num)
                            .addComponent(num_fact2))))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(code_cat)
                    .addComponent(code_subcat))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 1, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(date, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(num, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(numf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(num_fact2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Commands", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jButton1.setBackground(new java.awt.Color(51, 204, 0));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton1.setText("Pay");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Client/Supply", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jComboBox1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Supply", "Client" }));
        jComboBox1.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                jComboBox1PopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        jDateChooser1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        buss.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        buss.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select One Project" }));
        buss.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                bussPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        jComboBox2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Batchs" }));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(jComboBox2, 0, 118, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(year, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buss, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(buss, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(year, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBox2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Total_Amount", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        total.setEditable(false);
        total.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(total, javax.swing.GroupLayout.DEFAULT_SIZE, 286, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(total, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Supply/Client_Info<.....> Project/Business_Info", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        supply.setEditable(false);
        supply.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        codem.setEditable(false);
        codem.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        code.setEditable(false);
        code.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(supply)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(codem)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(code, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(supply, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(codem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(code, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Opions", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Debit/Credit_Account", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "D/C_Account", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jList1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jList1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jList1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jList1MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jList1);

        recherche.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        recherche.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        recherche.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                rechercheKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addComponent(recherche, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addComponent(recherche, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Budget", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        recherche1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        recherche1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        recherche1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                recherche1KeyReleased(evt);
            }
        });

        jList2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jList2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jList2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jList2MouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(jList2);

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(recherche1, javax.swing.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addComponent(recherche1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Account_Info", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Credit Account", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        cnom.setEditable(false);
        cnom.setBackground(new java.awt.Color(240, 240, 240));
        cnom.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        cnom.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jScrollPane5.setViewportView(cnom);

        codec.setEditable(false);
        codec.setBackground(new java.awt.Color(240, 240, 241));
        codec.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        codemc.setEditable(false);
        codemc.setBackground(new java.awt.Color(240, 240, 241));
        codemc.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        compte_mc.setEditable(false);
        compte_mc.setBackground(new java.awt.Color(240, 240, 241));
        compte_mc.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        compte_mc.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addComponent(codec)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(codemc, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(compte_mc)
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(compte_mc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(codec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(codemc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Budget", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        cnom1.setEditable(false);
        cnom1.setBackground(new java.awt.Color(240, 240, 240));
        cnom1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        cnom1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jScrollPane6.setViewportView(cnom1);

        codec1.setEditable(false);
        codec1.setBackground(new java.awt.Color(240, 240, 241));
        codec1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        codemc1.setEditable(false);
        codemc1.setBackground(new java.awt.Color(240, 240, 241));
        codemc1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        compte_m1.setEditable(false);
        compte_m1.setBackground(new java.awt.Color(240, 240, 241));
        compte_m1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        compte_m1.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addComponent(codec1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(codemc1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(compte_m1)
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(compte_m1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(codec1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(codemc1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(45, 45, 45))
        );

        jMenu1.setText("X");
        jMenu1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jMenu1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu1MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu1);

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

        setBounds(140, 10, 971, 591);
    }// </editor-fold>//GEN-END:initComponents

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        refresh();
        select_jTable2();

        // TODO add your handling code here:
    }//GEN-LAST:event_jTable1MouseClicked

    private void ptcMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ptcMouseClicked
        ptc.setText("");        // TODO add your handling code here:
    }//GEN-LAST:event_ptcMouseClicked

    private void ptcKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ptcKeyTyped
        char c=evt.getKeyChar();
        if(!(Character.isDigit(c) || (c==KeyEvent.VK_BACK_SPACE)||c==KeyEvent.VK_DELETE ||c==KeyEvent.VK_PERIOD )){
            evt.consume();
        }          // TODO add your handling code here:
    }//GEN-LAST:event_ptcKeyTyped

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
saves();
refresh();
call_combo_compte_client();
// TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jComboBox1PopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_jComboBox1PopupMenuWillBecomeInvisible
        refresh();
        call_combo_compte_client();
        if(jComboBox1.getSelectedItem().equals("Supply")){
            attCall_IN_LIST();

        }else{
            attCall_IN_LISTclient();

        }
       // call();
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1PopupMenuWillBecomeInvisible

    private void jList1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList1MouseClicked
        JLIST_from_COMPTE();        // TODO add your handling code here:
    }//GEN-LAST:event_jList1MouseClicked

    private void rechercheKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_rechercheKeyReleased
        attCall_LIST();        // TODO add your handling code here:
    }//GEN-LAST:event_rechercheKeyReleased

    private void recherche1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_recherche1KeyReleased
   attCall_IN_LIST_serach_LB();       // TODO add your handling code here:
    }//GEN-LAST:event_recherche1KeyReleased

    private void jList2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList2MouseClicked
        JLIST_from_COMPTE1();        // TODO add your handling code here:
    }//GEN-LAST:event_jList2MouseClicked

    private void jMenu1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu1MouseClicked
this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu1MouseClicked

    private void bussPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_bussPopupMenuWillBecomeInvisible
  DefaultListModel list = new DefaultListModel();
        try{
            String sql="SELECT Distinct(code)  FROM budget_code WHERE CAT='"+buss.getSelectedItem()+"' ";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                //String sum=rs.getString("nom");
                String sums=rs.getString("code");
                 list.addElement(sums);
                
                 jList2.setModel(list);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }  
         jComboBox2.removeAllItems();
     jComboBox2.addItem("Batchs"); 
     try{
          String sql="SELECT * FROM batchs where projet='"+buss.getSelectedItem()+"'";
             
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                //String sum=rs.getString("nom");
                String sums=rs.getString("BATCH");
                
                
               jComboBox2.addItem(sums);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }// TODO add your handling code here:
    }//GEN-LAST:event_bussPopupMenuWillBecomeInvisible


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> buss;
    private javax.swing.JEditorPane cnom;
    private javax.swing.JEditorPane cnom1;
    private javax.swing.JTextField code;
    private javax.swing.JLabel code_cat;
    private javax.swing.JLabel code_subcat;
    private javax.swing.JTextField codec;
    private javax.swing.JTextField codec1;
    private javax.swing.JTextField codem;
    private javax.swing.JTextField codemc;
    private javax.swing.JTextField codemc1;
    private javax.swing.JTextField compte_m1;
    private javax.swing.JTextField compte_mc;
    private javax.swing.JTextField date;
    private javax.swing.JEditorPane desc;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private com.alee.extended.date.WebDateField jDateChooser1;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList<String> jList1;
    private javax.swing.JList<String> jList2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField num;
    private javax.swing.JTextField num_fact2;
    private javax.swing.JTextField numf;
    private javax.swing.JTextField ptc;
    private javax.swing.JTextField recherche;
    private javax.swing.JTextField recherche1;
    private javax.swing.JTextField supply;
    private javax.swing.JTextField total;
    private com.toedter.calendar.JYearChooser year;
    // End of variables declaration//GEN-END:variables
}
