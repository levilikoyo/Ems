/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intreprisemanagementsystem;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Dosh
 */
public class Programme_Plannification extends javax.swing.JPanel {
 private Connection con;
private Statement st;
PreparedStatement pst=null;
ResultSet rs= null;
String ACTIVITES,replaceString,rolls;
   // String B1,B2,B3,B4,B5,B6,B7,B8,B9,B10,B11="",B12="",B13="",B14="",B15="",B16="",B17="",B18="",B19="",B20,B21,B22,B23,B24,B25,B26,B27,B28,B29,B30,B31;
         
         
    public Programme_Plannification() {
        initComponents();
          con=JavaDbConnect.dbConnect();
        //  grouperadio();
          call();
          
    }
 
    public void call(){
   
 try{
            String sql="select * from projet";
           
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
 
  public void show_in_table(){
        
         try{
    String sql="select  Distinct(ACTIVITE) as 'Activities'  from program_objective_strategique WHERE PROJET='"+buss.getSelectedItem()+"'";
    //`LIBELLE`, `ENTRE`, `SORTIE`, `DATE`, `BUSS`, `USED`, `NUM`
    pst = con.prepareStatement(sql);
      rs= pst.executeQuery();
     
       jTable2.setModel(DbUtils.resultSetToTableModel(rs));

    
             }catch(Exception ex){
                 JOptionPane.showMessageDialog(null, ex);
             }
  }
  
  
  
//  public void calls(){
//      int rowS= jTable1.getSelectedRow();
//       
//       if(all.isSelected()){
//       int rows=jTable2.getRowCount();
//
//  for(int row = 0; row<rows; row++)
//  {  
//  String Table_clicks2 = (jTable2.getModel().getValueAt(rowS,0). toString());
//   replaceString=Table_clicks2.replace("'", "''");
//   try{
//            String sql="select ACTIVITE from Program_sub_timing_detail WHERE SUB_ACTIVITE='"+replaceString+"' AND SUB_TIMING='"+timing.getSelectedItem()+"' AND PROJET='"+buss.getSelectedItem()+"'";
//           
//            pst=con.prepareStatement(sql);
//            rs=pst.executeQuery();
//            while(rs.next()){
//                ACTIVITES=rs.getString("ACTIVITE");
//                
//            }
//            }
//        catch(Exception ex){
//          JOptionPane.showMessageDialog(null, ex); } 
//  
//  
//  
//  }
//       }else{
//        int indexs[]=jTable2.getSelectedRows();
//       
//        for(int i=0; i < indexs.length;i++){
//          String Table_click = (jTable2.getModel().getValueAt(indexs[i],0). toString());
//          
//           try{
//            String sql="select ACTIVITE from Program_sub_timing_detail WHERE SUB_ACTIVITE='"+Table_click+"' AND SUB_TIMING='"+timing.getSelectedItem()+"' AND PROJET='"+buss.getSelectedItem()+"'";
//           
//            pst=con.prepareStatement(sql);
//            rs=pst.executeQuery();
//            while(rs.next()){
//                ACTIVITES=rs.getString("ACTIVITE");
//                
//            }
//            }
//        catch(Exception ex){
//          JOptionPane.showMessageDialog(null, ex); } 
//          
//        }
//       }
//  
//  
//  }
  
//   public void selectontable(){
//       calls();
//       
//       int rowS= jTable1.getSelectedRow();
//        String Table_clicks = (jTable1.getModel().getValueAt(rowS,0). toString());
//      ////  String Table_clicks2 = (jTable2.getModel().getValueAt(rowS,0). toString());
//        
//        int indexs[]=jTable2.getSelectedRows();
//       
//        for(int i=0; i < indexs.length;i++){
//          String Table_click = (jTable2.getModel().getValueAt(indexs[i],0). toString());
//          
//                 try{
//    String sql="INSERT INTO Program_daily (`PROJET`, `NAME`, `TIMING`, `DATES`, `INDIC`,`ACTIVITIES`, `B_ACTIVITIES`,`STATUT`,`OBSERVATION`)"+"values(?,?,?,?,?,?,?,?,?)";
//    pst=con.prepareStatement(sql);
//    pst.setString(1,buss.getSelectedItem().toString());
//    pst.setString(2,Table_clicks);
//    pst.setString(3,timing.getSelectedItem().toString());
//    pst.setString(4,jDateChooser1.getText());
//  //  pst.setString(5,indic.getText());
//      pst.setString(6,ACTIVITES);
//    pst.setString(7,Table_click);
//     pst.setString(8,"");
//    pst.setString(9,"");
//    
//  
//    
//    pst.executeUpdate();
//    // JOptionPane.showMessageDialog(null,"BUDGET TRANS");
//    }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}
//  }
//        JOptionPane.showMessageDialog(null,"Tranction Saved");
//   }
  
// public void saveS(){
//     calls();
//      int rowS= jTable1.getSelectedRow();
//        String Table_click = (jTable1.getModel().getValueAt(rowS,0). toString());
//        //   String Table_click2 = (jTable2.getModel().getValueAt(rowS,0). toString());
//  int rows=jTable2.getRowCount();
//
//  for(int row = 0; row<rows; row++)
//  {  
//    String qty = (String)jTable2.getValueAt(rows, 0);
//        try{
//    String sql="INSERT INTO Program_daily (`PROJET`, `NAME`, `TIMING`, `DATES`, `INDIC`,`ACTIVITIES`, `B_ACTIVITIES`,`STATUT`, `OBSERVATION`)"+"values(?,?,?,?,?,?,?,?,?)";
//    pst=con.prepareStatement(sql);
//    pst.setString(1,buss.getSelectedItem().toString());
//    pst.setString(2,Table_click);
//    pst.setString(3,timing.getSelectedItem().toString());
//    pst.setString(4,jDateChooser1.getText());
//   // pst.setString(5,indic.getText());
//      pst.setString(6,ACTIVITES);
//    pst.setString(7,qty);
//    
//    pst.setString(8,"");
//    pst.setString(9,"");
//    
//  
//    
//    pst.executeUpdate();
//    // JOptionPane.showMessageDialog(null,"BUDGET TRANS");
//    }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}
//  }
//  JOptionPane.showMessageDialog(null,"Tranction Saved");
//  }
 
// public void call_tableinds(){
//  int row= jTable1.getSelectedRow();
//        // String rows =jTable1.getName()
//        String Table_click = (jTable1.getModel().getValueAt(row,0). toString());
//       //  String Table_click2 = (jTable2.getModel().getValueAt(rowS,0). toString());
//     try{
//    String sql="select  `NAME`,`B_ACTIVITIES`,`DATES` from Program_daily WHERE PROJET='"+buss.getSelectedItem()+"' AND NAME='"+Table_click+"'";
//    //`LIBELLE`, `ENTRE`, `SORTIE`, `DATE`, `BUSS`, `USED`, `NUM`
//    pst = con.prepareStatement(sql);
//      rs= pst.executeQuery();
//     
//       jTable3.setModel(DbUtils.resultSetToTableModel(rs));
//       TableColumn col0=jTable3.getColumnModel().getColumn(0);
//        TableColumn col1=jTable3.getColumnModel().getColumn(1);
//        TableColumn col2=jTable3.getColumnModel().getColumn(2);
//    
//       
//       
//       
//      
//       
//       col0.setPreferredWidth(100);
//       col1.setPreferredWidth(300);
//       col2.setPreferredWidth(50);
//    
//             }catch(Exception ex){
//                 JOptionPane.showMessageDialog(null, ex);
//             }
// 
// }

     public void rolls(){
          try{
                  SimpleDateFormat dateFormatsS = new SimpleDateFormat("yyyy-MM-dd");
         String addDateS = dateFormatsS.format(jDateChooser1.getDate());
         
         String sql="SELECT NUM FROM program_task where SUBSTR(NUM,5,10)='"+addDateS+"' ORDER BY NUM DESC LIMIT 1";
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
 
 public void calldate(){
      SimpleDateFormat dateFormatss = new SimpleDateFormat("yyyy-MM-dd");
         String dat1 = dateFormatss.format(jDateChooser1.getDate());
         String dat2 = dateFormatss.format(jDateChooser2.getDate());
         String Mois = null;
     
  SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
     // SimpleDateFormat dateFormatss = new SimpleDateFormat("dd-EE");
       String from = dateFormats.format(jDateChooser1.getDate());
         String to = dateFormats.format(jDateChooser2.getDate());
  Date d_from = null,d_to=null;
       try {
           d_from = new SimpleDateFormat("yyyy-MM-dd").parse(from);
           d_to = new SimpleDateFormat("yyyy-MM-dd").parse(to);
           
       } catch (ParseException ex) {
           Logger.getLogger(rh_pointage1.class.getName()).log(Level.SEVERE, null, ex);
       }
        long t1=d_from.getTime();
        long t2=d_to.getTime();
       
        SimpleDateFormat dateS=new SimpleDateFormat("EEEE, MMMM d, yyyy");//EEEE, d MMMM yyyy
        SimpleDateFormat date=new SimpleDateFormat("YYYY-MM-dd");//EEEE, d MMMM yyyy
       SimpleDateFormat jrs=new SimpleDateFormat("dd");
        SimpleDateFormat sem=new SimpleDateFormat("EE");
         SimpleDateFormat moiss=new SimpleDateFormat("MMM");
    //   String a1=null,a2=null,a3=null,a4=null,a5=null,a6=null,a7=null,a8=null,a9=null,a10=null,a11=null,a12=null,a13=null,a14=null,a15=null,a16=null,a17=null,a18=null,a19=null,a20=null,a21=null,a22=null,a23=null,a24=null,a25=null,a26=null,a27=null,a28=null,a29=null,a30=null,a31=null;
        if(t1<t2)
        {
            //1 = 1000
            for(long ii=t1;ii<=t2;ii+=86400000)
            {
                 String A1="",A2="",A3="",A4="",A5="",A6="",A7="",A8="",A9="",A10="",A11="",A12="",A13="",A14="",A15="",A16="",A17="",A18="",A19="",A20="",A21="",A22="",A23="",A24="",A25="",A26="",A27="",A28="",A29="",A30="",A31="";
 
            String dates=date.format(ii);
                String days=jrs.format(ii);
                String weeks=sem.format(ii);
               Mois=moiss.format(ii);
               
               
                 if(days.equals("01")){
                     A1="";
                A1=weeks;
                }else if(days.equals("02")){
                    A2="";
                A2=weeks;
                }else if(days.equals("03")){
                    A3="";
               A3=weeks;
                }else if(days.equals("04")){
                    A4="";
               A4=weeks;
                }else if(days.equals("05")){
                    A5="";
                A5=weeks;
                }else if(days.equals("06")){
                    A6="";
                A6=weeks;
                }else if(days.equals("07")){
                    A7="";
                A7=weeks;
                }else if(days.equals("08")){
                    A8="";
                A8=weeks;
                }else if(days.equals("09")){
                    A9="";
                A9=weeks;
                }else if(days.equals("10")){
                    A10="";
               A10=weeks;
                }else if(days.equals("11")){
                     A11="";
                A11=weeks;
                }else if(days.equals("12")){
                     A12="";
                A12=weeks;
                }else if(days.equals("13")){
                     A13="";
                A13=weeks;
                }else if(days.equals("14")){
                     A14="";
                A14=weeks;
                }else if(days.equals("15")){
                     A15="";
                A15=weeks;
                }else if(days.equals("16")){
                     A16="";
               A16=weeks;
                }else if(days.equals("17")){
                     A17="";
                A17=weeks;
                }else if(days.equals("18")){
                     A18="";
                A18=weeks;
                }else if(days.equals("19")){
                     A19="";
                A19=weeks;
                }else if(days.equals("20")){
                     A20="";
                A20=weeks;
                }else if(days.equals("21")){
                      A21="";
                A21=weeks;
                }else if(days.equals("22")){
                      A22="";
               A22=weeks;
                }else if(days.equals("23")){
                      A23="";
                A23=weeks;
                }else if(days.equals("24")){
                      A24="";
                A24=weeks;
                }else if(days.equals("25")){
                      A25="";
                A25=weeks;
                }else if(days.equals("26")){
                    A26="";
                A26=weeks;
                }else if(days.equals("27")){
                    A27="";
                A27=weeks;
                }else if(days.equals("28")){
                    A28="";
               A28=weeks;
                }else if(days.equals("29")){
                    A29="";
                A29=weeks;
                }else if(days.equals("30")){
                   A30="";
                A30=weeks;
                }else if(days.equals("31")){
                    A31="";
                A31=weeks;
                }
                 
                 
                  String replaceStrings=subacti.getText().replace("'", "''");
                 //yyyy-mm-dd-yyyy-mm-dd-jan
                  try{//dat1=11,dat2=11,mois=3
                  String group=dat1+"-"+dat2+"-"+Mois;
         
         String sql="SELECT NUM FROM program_task where SUBSTR(NUM,5,25)='"+group+"' and Task='"+replaceStrings+"' AND Resource='"+resp.getText()+"'  AND PROJET='"+buss.getSelectedItem()+"' ORDER BY NUM DESC LIMIT 1";
             pst=con.prepareStatement(sql);
             rs=pst.executeQuery();
             if(rs.next()){
                 String rl=rs.getString("NUM");
                 int ln=rl.length();
                 String stxt=rl.substring(0,30);
                 String snum=rl.substring(30,ln);
                 int n = Integer.parseInt(snum);
                 n++;
                 snum=Integer.toString(n);
                 rolls=stxt+snum;
             }else{
               rolls= "No: "+group+"/1001";
                
             }
         }catch(NumberFormatException | SQLException e){
             JOptionPane.showMessageDialog(null, e);  
         }
         //A1="";A2="";A3="";A4="";A5="";A6="";A7="";A8="";A9="";A10="";A11="";A12="";A13="";A14="";A15="";A16="";A17="";A18="";A19="";A20="";A21="";A22="";A23="";A24="";A25="";A26="";A27="";A28="";A29="";A30="";A31="";          
                    
                 
//     
         try{
            String sqls="select NUM from program_task WHERE Task='"+replaceStrings+"' AND Resource='"+resp.getText()+"' AND Start='"+dat1+"' AND Finish='"+dat2+"' AND MOIS='"+Mois+"' AND PROJET='"+buss.getSelectedItem()+"'";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
            if(rs.next()){
String NUM=rs.getString("NUM");
//JOptionPane.showMessageDialog(null, NUM);

  if(days.equals("01")){
                     A1="";
                A1=weeks;
                  try{
    String sql="UPDATE `program_task` SET `DAYS`=(Days+1),`A1`=? WHERE NUM='"+NUM+"'";
    pst=con.prepareStatement(sql);
    pst.setString(1,A1);
   
    pst.executeUpdate();
    // JOptionPane.showMessageDialog(null,"BUDGET TRANS");
    }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);} 
                }else if(days.equals("02")){
                 try{
    String sql="UPDATE `program_task` SET `DAYS`=(Days+1),`A2`=? WHERE NUM='"+NUM+"'";
    pst=con.prepareStatement(sql);
    pst.setString(1,A2);
   
    pst.executeUpdate();
    // JOptionPane.showMessageDialog(null,"BUDGET TRANS");
    }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);} 
                
                }else if(days.equals("03")){
                 try{
    String sql="UPDATE `program_task` SET `DAYS`=(Days+1),`A3`=? WHERE NUM='"+NUM+"'";
    pst=con.prepareStatement(sql);
    pst.setString(1,A3);
   
    pst.executeUpdate();
    // JOptionPane.showMessageDialog(null,"BUDGET TRANS");
    }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);} 
                
                }else if(days.equals("04")){
                 try{
    String sql="UPDATE `program_task` SET `DAYS`=(Days+1), `A4`=? WHERE NUM='"+NUM+"'";
    pst=con.prepareStatement(sql);
    pst.setString(1,A4);
   
    pst.executeUpdate();
    // JOptionPane.showMessageDialog(null,"BUDGET TRANS");
    }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);} 
                
                }else if(days.equals("05")){
                 try{
    String sql="UPDATE `program_task` SET `DAYS`=(Days+1),`A5`=? WHERE NUM='"+NUM+"'";
    pst=con.prepareStatement(sql);
    pst.setString(1,A5);
   
    pst.executeUpdate();
    // JOptionPane.showMessageDialog(null,"BUDGET TRANS");
    }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);} 
                
                }else if(days.equals("06")){
                 try{
    String sql="UPDATE `program_task` SET `DAYS`=(Days+1),`A6`=? WHERE NUM='"+NUM+"'";
    pst=con.prepareStatement(sql);
    pst.setString(1,A6);
   
    pst.executeUpdate();
    // JOptionPane.showMessageDialog(null,"BUDGET TRANS");
    }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);} 
                
                }else if(days.equals("07")){
                 try{
    String sql="UPDATE `program_task` SET `DAYS`=(Days+1),`A7`=? WHERE NUM='"+NUM+"'";
    pst=con.prepareStatement(sql);
    pst.setString(1,A7);
   
    pst.executeUpdate();
    // JOptionPane.showMessageDialog(null,"BUDGET TRANS");
    }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);} 
                
                }else if(days.equals("08")){
                 try{
    String sql="UPDATE `program_task` SET `DAYS`=(Days+1),`A8`=? WHERE NUM='"+NUM+"'";
    pst=con.prepareStatement(sql);
    pst.setString(1,A8);
   
    pst.executeUpdate();
    // JOptionPane.showMessageDialog(null,"BUDGET TRANS");
    }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);} 
                
                }else if(days.equals("09")){
                 try{
    String sql="UPDATE `program_task` SET `DAYS`=(Days+1),`A9`=? WHERE NUM='"+NUM+"'";
    pst=con.prepareStatement(sql);
    pst.setString(1,A9);
   
    pst.executeUpdate();
    // JOptionPane.showMessageDialog(null,"BUDGET TRANS");
    }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);} 
                
                }else if(days.equals("10")){
                 try{
    String sql="UPDATE `program_task` SET `DAYS`=(Days+1),`A10`=? WHERE NUM='"+NUM+"'";
    pst=con.prepareStatement(sql);
    pst.setString(1,A10);
   
    pst.executeUpdate();
    // JOptionPane.showMessageDialog(null,"BUDGET TRANS");
    }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);} 
                
                }else if(days.equals("11")){
                 try{
    String sql="UPDATE `program_task` SET `DAYS`=(Days+1),`A11`=? WHERE NUM='"+NUM+"'";
    pst=con.prepareStatement(sql);
    pst.setString(1,A11);
   
    pst.executeUpdate();
    // JOptionPane.showMessageDialog(null,"BUDGET TRANS");
    }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);} 
                
                }else if(days.equals("12")){
                 try{
    String sql="UPDATE `program_task` SET `DAYS`=(Days+1),`A12`=? WHERE NUM='"+NUM+"'";
    pst=con.prepareStatement(sql);
    pst.setString(1,A12);
   
    pst.executeUpdate();
    // JOptionPane.showMessageDialog(null,"BUDGET TRANS");
    }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);} 
                
                }else if(days.equals("13")){
                 try{
    String sql="UPDATE `program_task` SET `DAYS`=(Days+1),`A13`=? WHERE NUM='"+NUM+"'";
    pst=con.prepareStatement(sql);
    pst.setString(1,A13);
   
    pst.executeUpdate();
    // JOptionPane.showMessageDialog(null,"BUDGET TRANS");
    }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);} 
                
                }else if(days.equals("14")){
                 try{
    String sql="UPDATE `program_task` SET `DAYS`=(Days+1),`A14`=? WHERE NUM='"+NUM+"'";
    pst=con.prepareStatement(sql);
    pst.setString(1,A14);
   
    pst.executeUpdate();
    // JOptionPane.showMessageDialog(null,"BUDGET TRANS");
    }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);} 
                
                }else if(days.equals("15")){
                 try{
    String sql="UPDATE `program_task` SET `DAYS`=(Days+1),`A15`=? WHERE NUM='"+NUM+"'";
    pst=con.prepareStatement(sql);
    pst.setString(1,A15);
   
    pst.executeUpdate();
    // JOptionPane.showMessageDialog(null,"BUDGET TRANS");
    }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);} 
                
                }else if(days.equals("16")){
                 try{
    String sql="UPDATE `program_task` SET `DAYS`=(Days+1),`A16`=? WHERE NUM='"+NUM+"'";
    pst=con.prepareStatement(sql);
    pst.setString(1,A16);
   
    pst.executeUpdate();
    // JOptionPane.showMessageDialog(null,"BUDGET TRANS");
    }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);} 
                
                }else if(days.equals("17")){
                 try{
    String sql="UPDATE `program_task` SET `DAYS`=(Days+1),`A17`=? WHERE NUM='"+NUM+"'";
    pst=con.prepareStatement(sql);
    pst.setString(1,A17);
   
    pst.executeUpdate();
    // JOptionPane.showMessageDialog(null,"BUDGET TRANS");
    }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);} 
                
                }else if(days.equals("18")){
                 try{
    String sql="UPDATE `program_task` SET `DAYS`=(Days+1),`A18`=? WHERE NUM='"+NUM+"'";
    pst=con.prepareStatement(sql);
    pst.setString(1,A18);
   
    pst.executeUpdate();
    // JOptionPane.showMessageDialog(null,"BUDGET TRANS");
    }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);} 
                
                }
  else if(days.equals("19")){
                 try{
    String sql="UPDATE `program_task` SET `DAYS`=(Days+1),`A19`=? WHERE NUM='"+NUM+"'";
    pst=con.prepareStatement(sql);
    pst.setString(1,A19);
   
    pst.executeUpdate();
    // JOptionPane.showMessageDialog(null,"BUDGET TRANS");
    }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);} 
                
                }else if(days.equals("20")){
                 try{
    String sql="UPDATE `program_task` SET `DAYS`=(Days+1),`A20`=? WHERE NUM='"+NUM+"'";
    pst=con.prepareStatement(sql);
    pst.setString(1,A20);
   
    pst.executeUpdate();
    // JOptionPane.showMessageDialog(null,"BUDGET TRANS");
    }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);} 
                
                }else if(days.equals("21")){
                 try{
    String sql="UPDATE `program_task` SET `DAYS`=(Days+1),`A21`=? WHERE NUM='"+NUM+"'";
    pst=con.prepareStatement(sql);
    pst.setString(1,A21);
   
    pst.executeUpdate();
    // JOptionPane.showMessageDialog(null,"BUDGET TRANS");
    }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);} 
                
                }else if(days.equals("22")){
                 try{
    String sql="UPDATE `program_task` SET `DAYS`=(Days+1),`A22`=? WHERE NUM='"+NUM+"'";
    pst=con.prepareStatement(sql);
    pst.setString(1,A22);
   
    pst.executeUpdate();
    // JOptionPane.showMessageDialog(null,"BUDGET TRANS");
    }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);} 
                
                }else if(days.equals("23")){
                 try{
    String sql="UPDATE `program_task` SET `DAYS`=(Days+1),`A23`=? WHERE NUM='"+NUM+"'";
    pst=con.prepareStatement(sql);
    pst.setString(1,A23);
   
    pst.executeUpdate();
    // JOptionPane.showMessageDialog(null,"BUDGET TRANS");
    }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);} 
                
                }else if(days.equals("24")){
                 try{
    String sql="UPDATE `program_task` SET `DAYS`=(Days+1),`A24`=? WHERE NUM='"+NUM+"'";
    pst=con.prepareStatement(sql);
    pst.setString(1,A24);
   
    pst.executeUpdate();
    // JOptionPane.showMessageDialog(null,"BUDGET TRANS");
    }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);} 
                
                }else if(days.equals("25")){
                 try{
    String sql="UPDATE `program_task` SET `DAYS`=(Days+1),`A25`=? WHERE NUM='"+NUM+"'";
    pst=con.prepareStatement(sql);
    pst.setString(1,A25);
   
    pst.executeUpdate();
    // JOptionPane.showMessageDialog(null,"BUDGET TRANS");
    }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);} 
                
                }else if(days.equals("26")){
                 try{
    String sql="UPDATE `program_task` SET `DAYS`=(Days+1),`A26`=? WHERE NUM='"+NUM+"'";
    pst=con.prepareStatement(sql);
    pst.setString(1,A26);
   
    pst.executeUpdate();
    // JOptionPane.showMessageDialog(null,"BUDGET TRANS");
    }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);} 
                
                }
  else if(days.equals("27")){
                 try{
    String sql="UPDATE `program_task` SET `DAYS`=(Days+1),`A27`=? WHERE NUM='"+NUM+"'";
    pst=con.prepareStatement(sql);
    pst.setString(1,A27);
   
    pst.executeUpdate();
    // JOptionPane.showMessageDialog(null,"BUDGET TRANS");
    }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);} 
                
                }else if(days.equals("28")){
                 try{
    String sql="UPDATE `program_task` SET `DAYS`=(Days+1),`A28`=? WHERE NUM='"+NUM+"'";
    pst=con.prepareStatement(sql);
    pst.setString(1,A28);
   
    pst.executeUpdate();
    // JOptionPane.showMessageDialog(null,"BUDGET TRANS");
    }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);} 
                
                }else if(days.equals("29")){
                 try{
    String sql="UPDATE `program_task` SET `DAYS`=(Days+1),`A29`=? WHERE NUM='"+NUM+"'";
    pst=con.prepareStatement(sql);
    pst.setString(1,A29);
   
    pst.executeUpdate();
    // JOptionPane.showMessageDialog(null,"BUDGET TRANS");
    }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);} 
                
                }else if(days.equals("30")){
                 try{
    String sql="UPDATE `program_task` SET `DAYS`=(Days+1),`A30`=? WHERE NUM='"+NUM+"'";
    pst=con.prepareStatement(sql);
    pst.setString(1,A30);
   
    pst.executeUpdate();
    // JOptionPane.showMessageDialog(null,"BUDGET TRANS");
    }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);} 
                
                }
  else if(days.equals("31")){
                 try{
    String sql="UPDATE `program_task` SET `DAYS`=(Days+1),`A31`=? WHERE NUM='"+NUM+"'";
    pst=con.prepareStatement(sql);
    pst.setString(1,A31);
   
    pst.executeUpdate();
    // JOptionPane.showMessageDialog(null,"BUDGET TRANS");
    }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);} 
                
                }
       
            }else{

 try{
    String sql="INSERT INTO `program_task`(`Task`, `Resource`, `Start`, `Finish`, `Days`, `Done`, `_%_`,  `A1`, `A2`, `A3`, `A4`, `A5`, `A6`, `A7`, `A8`, `A9`, `A10`, `A11`, `A12`, `A13`, `A14`, `A15`, `A16`, `A17`, `A18`, `A19`, `A20`, `A21`, `A22`, `A23`, `A24`, `A25`, `A26`, `A27`, `A28`, `A29`, `A30`, `A31`,`Mois`,`Projet`,NUM,Year,Sub_time)"+"values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    pst=con.prepareStatement(sql);
    pst.setString(1,subacti.getText());
    pst.setString(2,resp.getText());
    pst.setString(3,dat1);
    pst.setString(4,dat2);
    pst.setString(5,"1");
      pst.setString(6,"0");
    pst.setString(7,"0");
     pst.setString(8,A1);
    pst.setString(9,A2);
    pst.setString(10,A3);
    pst.setString(11,A4);
    pst.setString(12,A5);
    pst.setString(13,A6);
    pst.setString(14,A7);
    pst.setString(15,A8);
    pst.setString(16,A9);
    pst.setString(17,A10);
    
     pst.setString(18,A11);
    pst.setString(19,A12);
    pst.setString(20,A13);
    pst.setString(21,A14);
    pst.setString(22,A15);
    pst.setString(23,A16);
    pst.setString(24,A17);
    pst.setString(25,A18);
    pst.setString(26,A19);
    pst.setString(27,A20);
    
     pst.setString(28,A21);
    pst.setString(29,A22);
    pst.setString(30,A23);
    pst.setString(31,A24);
    pst.setString(32,A25);
    pst.setString(33,A26);
    pst.setString(34,A27);
    pst.setString(35,A28);
    pst.setString(36,A29);
    pst.setString(37,A30);
    
     pst.setString(38,A31);
    
    pst.setString(39,Mois);
     pst.setString(40,buss.getSelectedItem().toString());
      pst.setString(41,rolls);
      pst.setString(42,year.getText());
      pst.setString(43,time.getText());
   pst.executeUpdate();
   
    }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}   
  
//            
            }
            }catch(SQLException ex){JOptionPane.showMessageDialog(null, ex); } 
         
          int yreas= jTable2.getSelectedRow();
    String Yreas = (jTable2.getModel().getValueAt(yreas,0). toString());
  
   try{
    String sql="INSERT INTO program_daily(PROJET,NAME,TIMING,DATES,INDIC,ACTIVITIES,B_ACTIVITIES,STATUT,OBSERVATION,DEBIT,CREDIT,Year,Sub_time,Mois)"+"values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    pst=con.prepareStatement(sql);
    pst.setString(1,buss.getSelectedItem().toString());
    pst.setString(2,resp.getText());
     pst.setString(3,dates);
     pst.setString(4,dates);
    pst.setString(5,rolls);
    pst.setString(6,Yreas);
    pst.setString(7,subacti.getText());
    pst.setString(8,"");
    pst.setString(9,"");
    pst.setString(10,"1");
    pst.setString(11,"0");
    
     pst.setString(12,year.getText());
    pst.setString(13,time.getText());
    pst.setString(14,sub_time.getText());
  
    
    pst.executeUpdate();
    // JOptionPane.showMessageDialog(null,"BUDGET TRANS");
    }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}
 
       
          
         
            }
        

        }
    //   A1="";A2="";A3="";A4="";A5="";A6="";A7="";A8="";A9="";A10="";A11="";A12="";A13="";A14="";A15="";A16="";A17="";A18="";A19="";A20="";A21="";A22="";A23="";A24="";A25="";A26="";A27="";A28="";A29="";A30="";A31="";
      
      
    JOptionPane.showMessageDialog(null,"Transaction Saved");    
                
                
 }
 
 public void savesave(){
     String day;
      SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MMM-dd");
      SimpleDateFormat dateFormatss = new SimpleDateFormat("dd-EE");
         String dat1 = dateFormats.format(jDateChooser1.getDate());
         String dat2 = dateFormats.format(jDateChooser2.getDate());
         
          String mois = dateFormatss.format(jDateChooser2.getDate());
         day=mois;
       try{
            String sqls="select * from program_task WHERE Task='"+subacti.getText()+"' AND Resource='"+resp.getText()+"' AND Start='"+jDateChooser1.getText()+"' AND Finish='"+jDateChooser2.getText()+"'  AND PROJET='"+buss.getSelectedItem()+"'";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
            if(rs.next()){
             try{
    String sql="INSERT INTO `program_task`(`Task`, `Resource`, `Start`, `Finish`, `Days`, `Done`, `_%_`, `Mon`, `Tue`, `Wed`, `Thu`, `Fri`, `Sat`, `Sun`, `Projet`)"+"values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    pst=con.prepareStatement(sql);
    pst.setString(1,subacti.getText());
    pst.setString(2,resp.getText());
    pst.setString(3,dat1);
    pst.setString(4,dat2);
    pst.setString(5,"0");
      pst.setString(6,"0");
    pst.setString(7,"_%_");
     pst.setString(8,day);
    pst.setString(9,day);
    pst.setString(10,day);
    pst.setString(11,day);
    pst.setString(12,day);
    pst.setString(13,day);
    pst.setString(14,day);
    pst.setString(15,day);
    
    
  
    
    pst.executeUpdate();
    // JOptionPane.showMessageDialog(null,"BUDGET TRANS");
    }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}   
                
            }
            try{
    String sql="UPDATE `program_task` SET Mon=?,Tue=?,Wed=?,Thu=?,Fri=?,Sat=?,Sun=? WHERE Task='"+subacti.getText()+"' AND Resource='"+resp.getText()+"' AND Start='"+jDateChooser1.getText()+"' AND Finish='"+jDateChooser2.getText()+"'  AND PROJET='"+buss.getSelectedItem()+"'";
    pst=con.prepareStatement(sql);
   
     pst.setString(1,day);
    pst.setString(2,day);
    pst.setString(3,day);
    pst.setString(4,day);
    pst.setString(5,day);
    pst.setString(6,day);
    pst.setString(7,day);
    
    
    
  
    
    pst.executeUpdate();
    // JOptionPane.showMessageDialog(null,"BUDGET TRANS");
    }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}
 
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex); }   
 
  
 }
 
  public void call_tableindsS(){
     try{
    String sql="SELECT `Task`, `Resource`, `Start`, `Finish`, `Days`, `NUM` FROM `program_task` WHERE `Projet`='"+buss.getSelectedItem()+"'";
    //`LIBELLE`, `ENTRE`, `SORTIE`, `DATE`, `BUSS`, `USED`, `NUM`
    pst = con.prepareStatement(sql);
      rs= pst.executeQuery();
     
       jTable3.setModel(DbUtils.resultSetToTableModel(rs));
       TableColumn col0=jTable3.getColumnModel().getColumn(0);
        TableColumn col1=jTable3.getColumnModel().getColumn(1);
        TableColumn col2=jTable3.getColumnModel().getColumn(2);
        TableColumn col3=jTable3.getColumnModel().getColumn(3);
        TableColumn col4=jTable3.getColumnModel().getColumn(4);
        TableColumn col5=jTable3.getColumnModel().getColumn(5);
    
       
       
       
      
       
       col0.setPreferredWidth(250);
       col1.setPreferredWidth(100);
       col2.setPreferredWidth(50);
       col3.setPreferredWidth(50);
       col4.setPreferredWidth(20);
       col5.setPreferredWidth(100);
    
             }catch(Exception ex){
                 JOptionPane.showMessageDialog(null, ex);
             }
 
 }
   public void delete()
    {
      int yreas= jTable3.getSelectedRow();
    String Yreas = (jTable3.getModel().getValueAt(yreas,5). toString());
         try{
        String sql = "DELETE FROM program_task WHERE NUM='"+Yreas+"' and PROJET='"+buss.getSelectedItem()+"'";
        
         pst = con.prepareStatement(sql);
        // pst.setString(1,IDS);
         pst.executeUpdate();

     }catch(Exception ex ){
         JOptionPane.showMessageDialog(null,ex);
     } 
              try{
        String sql = "DELETE FROM program_daily WHERE INDIC='"+Yreas+"' and PROJET='"+buss.getSelectedItem()+"'";
        
         pst = con.prepareStatement(sql);
        // pst.setString(1,IDS);
         pst.executeUpdate();

     }catch(Exception ex ){
         JOptionPane.showMessageDialog(null,ex);
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
        jLabel1 = new javax.swing.JLabel();
        buss = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane11 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        resp = new javax.swing.JTextField();
        jDateChooser1 = new com.alee.extended.date.WebDateField();
        jDateChooser2 = new com.alee.extended.date.WebDateField();
        subacti = new javax.swing.JTextField();
        year = new javax.swing.JTextField();
        time = new javax.swing.JTextField();
        sub_time = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane10 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane12 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();

        setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("X");
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        buss.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(buss, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
                    .addComponent(buss))
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jTable3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable3.setRowHeight(24);
        jTable3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable3MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTable3MousePressed(evt);
            }
        });
        jScrollPane11.setViewportView(jTable3);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane11, javax.swing.GroupLayout.DEFAULT_SIZE, 614, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        jButton3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton3.setText("Ok");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton4.setText("Del");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        resp.setEditable(false);
        resp.setBackground(new java.awt.Color(240, 240, 241));
        resp.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jDateChooser1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jDateChooser1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jDateChooser1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jDateChooser1ActionPerformed(evt);
            }
        });

        jDateChooser2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jDateChooser2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jDateChooser2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jDateChooser2ActionPerformed(evt);
            }
        });

        subacti.setEditable(false);
        subacti.setBackground(new java.awt.Color(240, 240, 241));

        year.setEditable(false);
        year.setBackground(new java.awt.Color(240, 240, 241));
        year.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        year.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        time.setEditable(false);
        time.setBackground(new java.awt.Color(240, 240, 241));
        time.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        time.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        sub_time.setEditable(false);
        sub_time.setBackground(new java.awt.Color(240, 240, 241));
        sub_time.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        sub_time.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(subacti)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(resp)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(year, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(time, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sub_time, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(year, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                    .addComponent(time)
                    .addComponent(sub_time))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(subacti, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton3)
                        .addComponent(jButton4)
                        .addComponent(resp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Les Activités", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jTable2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTable2MousePressed(evt);
            }
        });
        jScrollPane10.setViewportView(jTable2);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 267, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Les Sous Activités", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jTable4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable4MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTable4MousePressed(evt);
            }
        });
        jScrollPane12.setViewportView(jTable4);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane12, javax.swing.GroupLayout.DEFAULT_SIZE, 267, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jScrollPane12, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
  this.show(false);        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel1MouseClicked

    private void bussPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_bussPopupMenuWillBecomeInvisible
        show_in_table();
        call_tableindsS();
// TODO add your handling code here:
    }//GEN-LAST:event_bussPopupMenuWillBecomeInvisible

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
  int row= jTable2.getSelectedRow();
        String Table_click = (jTable2.getModel().getValueAt(row,0). toString());
     try{
    String sql="select  SUB_ACTIVITIES as 'Sub Activities' from program_objective_strategique WHERE ACTIVITE='"+Table_click+"' AND PROJET='"+buss.getSelectedItem()+"' ";
    //`LIBELLE`, `ENTRE`, `SORTIE`, `DATE`, `BUSS`, `USED`, `NUM`
    pst = con.prepareStatement(sql);
      rs= pst.executeQuery();
     
       jTable4.setModel(DbUtils.resultSetToTableModel(rs));
       
             }catch(Exception ex){
                 JOptionPane.showMessageDialog(null, ex);
             }
        // TODO add your handling code shere:
    }//GEN-LAST:event_jTable2MouseClicked

    private void jTable2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable2MousePressed

    private void jTable3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable3MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable3MouseClicked

    private void jTable3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable3MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable3MousePressed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
calldate();
call_tableindsS();
    //  savesave();// TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
delete(); 
call_tableindsS();// TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jDateChooser1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jDateChooser1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jDateChooser1ActionPerformed

    private void jDateChooser2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jDateChooser2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jDateChooser2ActionPerformed

    private void jTable4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable4MouseClicked
 int row= jTable4.getSelectedRow();
        String Table_click = (jTable4.getModel().getValueAt(row,0). toString()); 
       String  replaceStrings=Table_click.replace("'", "''");
     try{
            String sql="select * from program_objective_strategique WHERE SUB_ACTIVITIES='"+replaceStrings+"' AND  PROJET='"+buss.getSelectedItem()+"'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            if(rs.next()){
            //  String  sub_act=rs.getString("SUB_ACTIVITE");
               String respo=rs.getString("RESPONSABLE");
                resp.setText(respo);
                subacti.setText(Table_click);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex); }
 try{
            String sql="select * from program_objective_strategique WHERE SUB_ACTIVITIES='"+replaceStrings+"' AND  PROJET='"+buss.getSelectedItem()+"'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            if(rs.next()){
            //  String  sub_act=rs.getString("SUB_ACTIVITE");
               String respo=rs.getString("CAT_TIMING");
                year.setText(respo);
                String respos=rs.getString("TIMING");
               time.setText(respos);
                String resposs=rs.getString("SUB_TIMING");
               sub_time.setText(resposs);
               // subacti.setText(Table_click);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex); }
     
// TODO add your handling code here:
    }//GEN-LAST:event_jTable4MouseClicked

    private void jTable4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable4MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable4MousePressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> buss;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private com.alee.extended.date.WebDateField jDateChooser1;
    private com.alee.extended.date.WebDateField jDateChooser2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    private javax.swing.JTextField resp;
    private javax.swing.JTextField sub_time;
    private javax.swing.JTextField subacti;
    private javax.swing.JTextField time;
    private javax.swing.JTextField year;
    // End of variables declaration//GEN-END:variables
}
