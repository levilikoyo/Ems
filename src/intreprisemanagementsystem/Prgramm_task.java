/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intreprisemanagementsystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Dosh
 */
public class Prgramm_task extends javax.swing.JPanel {

    private Connection con;
private Statement st;
PreparedStatement pst=null;
ResultSet rs= null;
    public Prgramm_task() {
        initComponents();
         con=JavaDbConnect.dbConnect();
       // show_in_tables();
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
      
       public void load_combo(){
   
 try{
     month.removeAllItems();
            String sql="select distinct(Mois) from program_task where projet='"+buss.getSelectedItem()+"'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                
                 
                   String sum2=rs.getString("Mois");
                 month.addItem(sum2);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex); } 
  try{
     years.removeAllItems();
            String sql="select distinct(year) from program_task where projet='"+buss.getSelectedItem()+"'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                String sum=rs.getString("year");
                 years.addItem(sum);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex); } 
   try{
       terms.removeAllItems();
            String sql="select distinct(Sub_time) from program_task where projet='"+buss.getSelectedItem()+"'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                 
                   String sum1=rs.getString("Sub_Time");
                 terms.addItem(sum1);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex); } 
    
   
    
    }
      
      
    public void callculate(){
        try{
         
          String sql="select distinct(INDIC) from  program_daily where  PROJET='"+buss.getSelectedItem()+"'";
            pst=con.prepareStatement(sql);;
            rs=pst.executeQuery();
           for(;rs.next();){
             String num=rs.getString("INDIC");
//            JOptionPane.showMessageDialog(null, num); 
//            
//            UPDATE T1 SET T1.extrasPrice = (SELECT SUM(T2.Price) FROM BookingPitchExtras T2 WHERE T2.pitchID = T1.ID)
//FROM BookingPitches T1;

try{
    String sqlss="UPDATE `program_task` SET `_%_`=(SELECT (((SUM(CREDIT))*100)/SUM(DEBIT)) FROM program_daily  where  INDIC='"+num+"' and PROJET='"+buss.getSelectedItem()+"') WHERE  NUM='"+num+"' AND Projet='"+buss.getSelectedItem()+"'";
  // String sqlss="UPDATE `program_task` SET `_%_`=90  WHERE  NUM='"+num+"' AND Projet='"+buss.getSelectedItem()+"'";
    pst=con.prepareStatement(sqlss);
  //  pst.setString(1,buss.getSelectedItem().toString());
    pst.executeUpdate();
    }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}   


             
        }
  
      }catch(Exception ex){
        JOptionPane.showMessageDialog(null,ex);  
      }       
  
 // }
     
    
    }
    
    
 public void show_in_tables(){
 try{
    String sql="select `Task` as 'Task Name', `Resource`, `Start`, `Finish`, `Days`, `_%_`,Mois as 'Month', `A1` as '1', `A2` as '2', `A3`as '3', `A4`as '4', `A5`as '5', `A6`as '6', `A7` as '7', `A8` as '8', `A9` as '9', `A10` as '10', `A11` as '11', `A12` as '12', `A13` as '13', `A14` as '14', `A15` as '15', `A16` as '16', `A17` as '17', `A18` as '18', `A19` as '19', `A20` as '20', `A21` as '21', `A22` as '22', `A23` as '23', `A24` as '24', `A25` as '25', `A26`as '26' , `A27` as '27', `A28` as '28', `A29` as '29', `A30` as '30', `A31` as '31' from program_task WHERE PROJET='"+buss.getSelectedItem()+"' order by Start,Finish";// WHERE PROJECT='"+buss.getSelectedItem()+"' and INDICATEUR='"+Table_click+"'";
    //`LIBELLE`, `ENTRE`, `SORTIE`, `DATE`, `BUSS`, `USED`, `NUM`
    pst = con.prepareStatement(sql);
      rs= pst.executeQuery();
     
      jTable3.setModel(DbUtils.resultSetToTableModel(rs));
        DefaultTableCellRenderer centre =new DefaultTableCellRenderer();
        centre.setHorizontalAlignment(JLabel.CENTER);
     TableColumn col0=jTable3.getColumnModel().getColumn(0);
        TableColumn col1=jTable3.getColumnModel().getColumn(1);
         
        TableColumn col2=jTable3.getColumnModel().getColumn(2);
        jTable3.getColumnModel().getColumn(2).setCellRenderer(centre);
         TableColumn col3=jTable3.getColumnModel().getColumn(3);
         jTable3.getColumnModel().getColumn(3).setCellRenderer(centre);
          TableColumn col4=jTable3.getColumnModel().getColumn(4);
          jTable3.getColumnModel().getColumn(4).setCellRenderer(centre);
           TableColumn col5=jTable3.getColumnModel().getColumn(5);
           jTable3.getColumnModel().getColumn(5).setCellRenderer(centre);
          // jTable3.getColumnSelectionAllowed();
            TableColumn col6=jTable3.getColumnModel().getColumn(6);
            jTable3.getColumnModel().getColumn(6).setCellRenderer(centre);
            
            
             TableColumn col7=jTable3.getColumnModel().getColumn(7);
             jTable3.getColumnModel().getColumn(7).setCellRenderer(centre);
              TableColumn col8=jTable3.getColumnModel().getColumn(8);
              jTable3.getColumnModel().getColumn(8).setCellRenderer(centre);
               TableColumn col9=jTable3.getColumnModel().getColumn(9);
               jTable3.getColumnModel().getColumn(9).setCellRenderer(centre);
                TableColumn col10=jTable3.getColumnModel().getColumn(10);
                jTable3.getColumnModel().getColumn(10).setCellRenderer(centre);
                 TableColumn col11=jTable3.getColumnModel().getColumn(11);
                 jTable3.getColumnModel().getColumn(11).setCellRenderer(centre);
                  TableColumn col12=jTable3.getColumnModel().getColumn(12);
                  jTable3.getColumnModel().getColumn(12).setCellRenderer(centre);
                   TableColumn col13=jTable3.getColumnModel().getColumn(13);
                   jTable3.getColumnModel().getColumn(13).setCellRenderer(centre);
     TableColumn col14=jTable3.getColumnModel().getColumn(14);
                 jTable3.getColumnModel().getColumn(14).setCellRenderer(centre);
                  TableColumn col15=jTable3.getColumnModel().getColumn(15);
                  jTable3.getColumnModel().getColumn(15).setCellRenderer(centre);
                   TableColumn col16=jTable3.getColumnModel().getColumn(16);
                   jTable3.getColumnModel().getColumn(16).setCellRenderer(centre);
                   
                    TableColumn col17=jTable3.getColumnModel().getColumn(17);
             jTable3.getColumnModel().getColumn(17).setCellRenderer(centre);
              TableColumn col18=jTable3.getColumnModel().getColumn(18);
              jTable3.getColumnModel().getColumn(18).setCellRenderer(centre);
               TableColumn col19=jTable3.getColumnModel().getColumn(19);
               jTable3.getColumnModel().getColumn(19).setCellRenderer(centre);
                TableColumn col20=jTable3.getColumnModel().getColumn(20);
                jTable3.getColumnModel().getColumn(20).setCellRenderer(centre);
                 TableColumn col21=jTable3.getColumnModel().getColumn(21);
                 jTable3.getColumnModel().getColumn(21).setCellRenderer(centre);
                  TableColumn col22=jTable3.getColumnModel().getColumn(22);
                  jTable3.getColumnModel().getColumn(22).setCellRenderer(centre);
                   TableColumn col23=jTable3.getColumnModel().getColumn(23);
                   jTable3.getColumnModel().getColumn(23).setCellRenderer(centre);
     TableColumn col24=jTable3.getColumnModel().getColumn(24);
                 jTable3.getColumnModel().getColumn(24).setCellRenderer(centre);
                  TableColumn col25=jTable3.getColumnModel().getColumn(25);
                  jTable3.getColumnModel().getColumn(25).setCellRenderer(centre);
                   TableColumn col26=jTable3.getColumnModel().getColumn(26);
                   jTable3.getColumnModel().getColumn(26).setCellRenderer(centre);
                   
                    TableColumn col27=jTable3.getColumnModel().getColumn(27);
             jTable3.getColumnModel().getColumn(27).setCellRenderer(centre);
              TableColumn col28=jTable3.getColumnModel().getColumn(28);
              jTable3.getColumnModel().getColumn(28).setCellRenderer(centre);
               TableColumn col29=jTable3.getColumnModel().getColumn(29);
               jTable3.getColumnModel().getColumn(29).setCellRenderer(centre);
                TableColumn col30=jTable3.getColumnModel().getColumn(30);
                jTable3.getColumnModel().getColumn(30).setCellRenderer(centre);
                 TableColumn col31=jTable3.getColumnModel().getColumn(31);
                 jTable3.getColumnModel().getColumn(31).setCellRenderer(centre);
                 
                     TableColumn col32=jTable3.getColumnModel().getColumn(32);
                 jTable3.getColumnModel().getColumn(32).setCellRenderer(centre);
                     TableColumn col33=jTable3.getColumnModel().getColumn(33);
                 jTable3.getColumnModel().getColumn(33).setCellRenderer(centre);
                     TableColumn col34=jTable3.getColumnModel().getColumn(34);
                 jTable3.getColumnModel().getColumn(34).setCellRenderer(centre);
                     TableColumn col35=jTable3.getColumnModel().getColumn(35);
                 jTable3.getColumnModel().getColumn(35).setCellRenderer(centre);
                 
                     TableColumn col36=jTable3.getColumnModel().getColumn(36);
                 jTable3.getColumnModel().getColumn(36).setCellRenderer(centre);
                     TableColumn col37=jTable3.getColumnModel().getColumn(37);
                 jTable3.getColumnModel().getColumn(37).setCellRenderer(centre);
                 
       
       
       
      
       
       col0.setPreferredWidth(230);
       col1.setPreferredWidth(130);
       col2.setPreferredWidth(90);
       col3.setPreferredWidth(90);
       col4.setPreferredWidth(60);
       col5.setPreferredWidth(60);
       col6.setPreferredWidth(60);
       col7.setPreferredWidth(50);
       col8.setPreferredWidth(50);
       col9.setPreferredWidth(50);
       col10.setPreferredWidth(50);
       col11.setPreferredWidth(50);
       col12.setPreferredWidth(50);
       col13.setPreferredWidth(50);
       col14.setPreferredWidth(50);
       col15.setPreferredWidth(50);
       col16.setPreferredWidth(50);
       
        col17.setPreferredWidth(50);
       col18.setPreferredWidth(50);
       col19.setPreferredWidth(50);
       col20.setPreferredWidth(50);
       col21.setPreferredWidth(50);
       col22.setPreferredWidth(50);
       col23.setPreferredWidth(50);
       col24.setPreferredWidth(50);
       col25.setPreferredWidth(50);
       col26.setPreferredWidth(50);
       
        col27.setPreferredWidth(50);
       col28.setPreferredWidth(50);
       col29.setPreferredWidth(50);
       col30.setPreferredWidth(50);
       col31.setPreferredWidth(50);
       
         col32.setPreferredWidth(50);
       
        col33.setPreferredWidth(50);
       col34.setPreferredWidth(50);
       col35.setPreferredWidth(50);
       col36.setPreferredWidth(50);
       col37.setPreferredWidth(50);
       
       
             }catch(Exception ex){
                 JOptionPane.showMessageDialog(null, ex);
             }
      
  }
 
 public void pour_projet(){
 
 try{
            String sql="select (Sum(credit)*100/sum(debit)) from program_daily where projet='"+buss.getSelectedItem()+"'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                 
                   Double sum1=rs.getDouble("(Sum(credit)*100/sum(debit))");
                
                NumberFormat nf = new DecimalFormat("#,###.000");
                String fn = nf.format(sum1);
                jTextField1.setText(fn+" %");
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex); } 
 
 }
 
  public void pour_year(){
 
 try{
            String sql="select (Sum(credit)*100/sum(debit)) from program_daily where year='"+years.getSelectedItem()+"' and projet='"+buss.getSelectedItem()+"'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                 
                   Double sum1=rs.getDouble("(Sum(credit)*100/sum(debit))");
                
                NumberFormat nf = new DecimalFormat("#,###.000");
                String fn = nf.format(sum1);
               jTextField2.setText(fn+" %");
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex); } 
 
 try{
    String sql="select `Task` as 'Task Name', `Resource`, `Start`, `Finish`, `Days`,`_%_`,Mois as 'Month', `A1` as '1', `A2` as '2', `A3`as '3', `A4`as '4', `A5`as '5', `A6`as '6', `A7` as '7', `A8` as '8', `A9` as '9', `A10` as '10', `A11` as '11', `A12` as '12', `A13` as '13', `A14` as '14', `A15` as '15', `A16` as '16', `A17` as '17', `A18` as '18', `A19` as '19', `A20` as '20', `A21` as '21', `A22` as '22', `A23` as '23', `A24` as '24', `A25` as '25', `A26`as '26' , `A27` as '27', `A28` as '28', `A29` as '29', `A30` as '30', `A31` as '31' from program_task where year='"+years.getSelectedItem()+"' and projet='"+buss.getSelectedItem()+"'";
    //`LIBELLE`, `ENTRE`, `SORTIE`, `DATE`, `BUSS`, `USED`, `NUM`
    pst = con.prepareStatement(sql);
      rs= pst.executeQuery();
     
      jTable3.setModel(DbUtils.resultSetToTableModel(rs));
        DefaultTableCellRenderer centre =new DefaultTableCellRenderer();
        centre.setHorizontalAlignment(JLabel.CENTER);
     TableColumn col0=jTable3.getColumnModel().getColumn(0);
        TableColumn col1=jTable3.getColumnModel().getColumn(1);
         
        TableColumn col2=jTable3.getColumnModel().getColumn(2);
        jTable3.getColumnModel().getColumn(2).setCellRenderer(centre);
         TableColumn col3=jTable3.getColumnModel().getColumn(3);
         jTable3.getColumnModel().getColumn(3).setCellRenderer(centre);
          TableColumn col4=jTable3.getColumnModel().getColumn(4);
          jTable3.getColumnModel().getColumn(4).setCellRenderer(centre);
           TableColumn col5=jTable3.getColumnModel().getColumn(5);
           jTable3.getColumnModel().getColumn(5).setCellRenderer(centre);
          // jTable3.getColumnSelectionAllowed();
            TableColumn col6=jTable3.getColumnModel().getColumn(6);
            jTable3.getColumnModel().getColumn(6).setCellRenderer(centre);
            
            
             TableColumn col7=jTable3.getColumnModel().getColumn(7);
             jTable3.getColumnModel().getColumn(7).setCellRenderer(centre);
              TableColumn col8=jTable3.getColumnModel().getColumn(8);
              jTable3.getColumnModel().getColumn(8).setCellRenderer(centre);
               TableColumn col9=jTable3.getColumnModel().getColumn(9);
               jTable3.getColumnModel().getColumn(9).setCellRenderer(centre);
                TableColumn col10=jTable3.getColumnModel().getColumn(10);
                jTable3.getColumnModel().getColumn(10).setCellRenderer(centre);
                 TableColumn col11=jTable3.getColumnModel().getColumn(11);
                 jTable3.getColumnModel().getColumn(11).setCellRenderer(centre);
                  TableColumn col12=jTable3.getColumnModel().getColumn(12);
                  jTable3.getColumnModel().getColumn(12).setCellRenderer(centre);
                   TableColumn col13=jTable3.getColumnModel().getColumn(13);
                   jTable3.getColumnModel().getColumn(13).setCellRenderer(centre);
     TableColumn col14=jTable3.getColumnModel().getColumn(14);
                 jTable3.getColumnModel().getColumn(14).setCellRenderer(centre);
                  TableColumn col15=jTable3.getColumnModel().getColumn(15);
                  jTable3.getColumnModel().getColumn(15).setCellRenderer(centre);
                   TableColumn col16=jTable3.getColumnModel().getColumn(16);
                   jTable3.getColumnModel().getColumn(16).setCellRenderer(centre);
                   
                    TableColumn col17=jTable3.getColumnModel().getColumn(17);
             jTable3.getColumnModel().getColumn(17).setCellRenderer(centre);
              TableColumn col18=jTable3.getColumnModel().getColumn(18);
              jTable3.getColumnModel().getColumn(18).setCellRenderer(centre);
               TableColumn col19=jTable3.getColumnModel().getColumn(19);
               jTable3.getColumnModel().getColumn(19).setCellRenderer(centre);
                TableColumn col20=jTable3.getColumnModel().getColumn(20);
                jTable3.getColumnModel().getColumn(20).setCellRenderer(centre);
                 TableColumn col21=jTable3.getColumnModel().getColumn(21);
                 jTable3.getColumnModel().getColumn(21).setCellRenderer(centre);
                  TableColumn col22=jTable3.getColumnModel().getColumn(22);
                  jTable3.getColumnModel().getColumn(22).setCellRenderer(centre);
                   TableColumn col23=jTable3.getColumnModel().getColumn(23);
                   jTable3.getColumnModel().getColumn(23).setCellRenderer(centre);
     TableColumn col24=jTable3.getColumnModel().getColumn(24);
                 jTable3.getColumnModel().getColumn(24).setCellRenderer(centre);
                  TableColumn col25=jTable3.getColumnModel().getColumn(25);
                  jTable3.getColumnModel().getColumn(25).setCellRenderer(centre);
                   TableColumn col26=jTable3.getColumnModel().getColumn(26);
                   jTable3.getColumnModel().getColumn(26).setCellRenderer(centre);
                   
                    TableColumn col27=jTable3.getColumnModel().getColumn(27);
             jTable3.getColumnModel().getColumn(27).setCellRenderer(centre);
              TableColumn col28=jTable3.getColumnModel().getColumn(28);
              jTable3.getColumnModel().getColumn(28).setCellRenderer(centre);
               TableColumn col29=jTable3.getColumnModel().getColumn(29);
               jTable3.getColumnModel().getColumn(29).setCellRenderer(centre);
                TableColumn col30=jTable3.getColumnModel().getColumn(30);
                jTable3.getColumnModel().getColumn(30).setCellRenderer(centre);
                 TableColumn col31=jTable3.getColumnModel().getColumn(31);
                 jTable3.getColumnModel().getColumn(31).setCellRenderer(centre);
                 
                     TableColumn col32=jTable3.getColumnModel().getColumn(32);
                 jTable3.getColumnModel().getColumn(32).setCellRenderer(centre);
                     TableColumn col33=jTable3.getColumnModel().getColumn(33);
                 jTable3.getColumnModel().getColumn(33).setCellRenderer(centre);
                     TableColumn col34=jTable3.getColumnModel().getColumn(34);
                 jTable3.getColumnModel().getColumn(34).setCellRenderer(centre);
                     TableColumn col35=jTable3.getColumnModel().getColumn(35);
                 jTable3.getColumnModel().getColumn(35).setCellRenderer(centre);
                 
                     TableColumn col36=jTable3.getColumnModel().getColumn(36);
                 jTable3.getColumnModel().getColumn(36).setCellRenderer(centre);
                     TableColumn col37=jTable3.getColumnModel().getColumn(37);
                 jTable3.getColumnModel().getColumn(37).setCellRenderer(centre);
                 
       
       
       
      
       
       col0.setPreferredWidth(230);
       col1.setPreferredWidth(130);
       col2.setPreferredWidth(90);
       col3.setPreferredWidth(90);
       col4.setPreferredWidth(60);
       col5.setPreferredWidth(60);
       col6.setPreferredWidth(60);
       col7.setPreferredWidth(50);
       col8.setPreferredWidth(50);
       col9.setPreferredWidth(50);
       col10.setPreferredWidth(50);
       col11.setPreferredWidth(50);
       col12.setPreferredWidth(50);
       col13.setPreferredWidth(50);
       col14.setPreferredWidth(50);
       col15.setPreferredWidth(50);
       col16.setPreferredWidth(50);
       
        col17.setPreferredWidth(50);
       col18.setPreferredWidth(50);
       col19.setPreferredWidth(50);
       col20.setPreferredWidth(50);
       col21.setPreferredWidth(50);
       col22.setPreferredWidth(50);
       col23.setPreferredWidth(50);
       col24.setPreferredWidth(50);
       col25.setPreferredWidth(50);
       col26.setPreferredWidth(50);
       
        col27.setPreferredWidth(50);
       col28.setPreferredWidth(50);
       col29.setPreferredWidth(50);
       col30.setPreferredWidth(50);
       col31.setPreferredWidth(50);
       
         col32.setPreferredWidth(50);
       
        col33.setPreferredWidth(50);
       col34.setPreferredWidth(50);
       col35.setPreferredWidth(50);
       col36.setPreferredWidth(50);
       col37.setPreferredWidth(50);
      
       
       
             }catch(Exception ex){
                 JOptionPane.showMessageDialog(null, ex);
             }
      
 
 }
  
   public void pour_term(){
 
 try{
            String sql="select (Sum(credit)*100/sum(debit)) from program_daily where  year='"+years.getSelectedItem()+"' and sub_time='"+terms.getSelectedItem()+"'and  projet='"+buss.getSelectedItem()+"'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                 
                   Double sum1=rs.getDouble("(Sum(credit)*100/sum(debit))");
                
                NumberFormat nf = new DecimalFormat("#,###.000");
                String fn = nf.format(sum1);
               jTextField3.setText(fn+" %");
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex); } 
 
 try{
    String sql="select `Task` as 'Task Name', `Resource`, `Start`, `Finish`, `Days`, `_%_`,Mois as 'Month', `A1` as '1', `A2` as '2', `A3`as '3', `A4`as '4', `A5`as '5', `A6`as '6', `A7` as '7', `A8` as '8', `A9` as '9', `A10` as '10', `A11` as '11', `A12` as '12', `A13` as '13', `A14` as '14', `A15` as '15', `A16` as '16', `A17` as '17', `A18` as '18', `A19` as '19', `A20` as '20', `A21` as '21', `A22` as '22', `A23` as '23', `A24` as '24', `A25` as '25', `A26`as '26' , `A27` as '27', `A28` as '28', `A29` as '29', `A30` as '30', `A31` as '31' from program_task where year='"+years.getSelectedItem()+"' and sub_time='"+terms.getSelectedItem()+"' and projet='"+buss.getSelectedItem()+"'";
    //`LIBELLE`, `ENTRE`, `SORTIE`, `DATE`, `BUSS`, `USED`, `NUM`
    pst = con.prepareStatement(sql);
      rs= pst.executeQuery();
     
      jTable3.setModel(DbUtils.resultSetToTableModel(rs));
        DefaultTableCellRenderer centre =new DefaultTableCellRenderer();
        centre.setHorizontalAlignment(JLabel.CENTER);
     TableColumn col0=jTable3.getColumnModel().getColumn(0);
        TableColumn col1=jTable3.getColumnModel().getColumn(1);
         
        TableColumn col2=jTable3.getColumnModel().getColumn(2);
        jTable3.getColumnModel().getColumn(2).setCellRenderer(centre);
         TableColumn col3=jTable3.getColumnModel().getColumn(3);
         jTable3.getColumnModel().getColumn(3).setCellRenderer(centre);
          TableColumn col4=jTable3.getColumnModel().getColumn(4);
          jTable3.getColumnModel().getColumn(4).setCellRenderer(centre);
           TableColumn col5=jTable3.getColumnModel().getColumn(5);
           jTable3.getColumnModel().getColumn(5).setCellRenderer(centre);
          // jTable3.getColumnSelectionAllowed();
            TableColumn col6=jTable3.getColumnModel().getColumn(6);
            jTable3.getColumnModel().getColumn(6).setCellRenderer(centre);
            
            
             TableColumn col7=jTable3.getColumnModel().getColumn(7);
             jTable3.getColumnModel().getColumn(7).setCellRenderer(centre);
              TableColumn col8=jTable3.getColumnModel().getColumn(8);
              jTable3.getColumnModel().getColumn(8).setCellRenderer(centre);
               TableColumn col9=jTable3.getColumnModel().getColumn(9);
               jTable3.getColumnModel().getColumn(9).setCellRenderer(centre);
                TableColumn col10=jTable3.getColumnModel().getColumn(10);
                jTable3.getColumnModel().getColumn(10).setCellRenderer(centre);
                 TableColumn col11=jTable3.getColumnModel().getColumn(11);
                 jTable3.getColumnModel().getColumn(11).setCellRenderer(centre);
                  TableColumn col12=jTable3.getColumnModel().getColumn(12);
                  jTable3.getColumnModel().getColumn(12).setCellRenderer(centre);
                   TableColumn col13=jTable3.getColumnModel().getColumn(13);
                   jTable3.getColumnModel().getColumn(13).setCellRenderer(centre);
     TableColumn col14=jTable3.getColumnModel().getColumn(14);
                 jTable3.getColumnModel().getColumn(14).setCellRenderer(centre);
                  TableColumn col15=jTable3.getColumnModel().getColumn(15);
                  jTable3.getColumnModel().getColumn(15).setCellRenderer(centre);
                   TableColumn col16=jTable3.getColumnModel().getColumn(16);
                   jTable3.getColumnModel().getColumn(16).setCellRenderer(centre);
                   
                    TableColumn col17=jTable3.getColumnModel().getColumn(17);
             jTable3.getColumnModel().getColumn(17).setCellRenderer(centre);
              TableColumn col18=jTable3.getColumnModel().getColumn(18);
              jTable3.getColumnModel().getColumn(18).setCellRenderer(centre);
               TableColumn col19=jTable3.getColumnModel().getColumn(19);
               jTable3.getColumnModel().getColumn(19).setCellRenderer(centre);
                TableColumn col20=jTable3.getColumnModel().getColumn(20);
                jTable3.getColumnModel().getColumn(20).setCellRenderer(centre);
                 TableColumn col21=jTable3.getColumnModel().getColumn(21);
                 jTable3.getColumnModel().getColumn(21).setCellRenderer(centre);
                  TableColumn col22=jTable3.getColumnModel().getColumn(22);
                  jTable3.getColumnModel().getColumn(22).setCellRenderer(centre);
                   TableColumn col23=jTable3.getColumnModel().getColumn(23);
                   jTable3.getColumnModel().getColumn(23).setCellRenderer(centre);
     TableColumn col24=jTable3.getColumnModel().getColumn(24);
                 jTable3.getColumnModel().getColumn(24).setCellRenderer(centre);
                  TableColumn col25=jTable3.getColumnModel().getColumn(25);
                  jTable3.getColumnModel().getColumn(25).setCellRenderer(centre);
                   TableColumn col26=jTable3.getColumnModel().getColumn(26);
                   jTable3.getColumnModel().getColumn(26).setCellRenderer(centre);
                   
                    TableColumn col27=jTable3.getColumnModel().getColumn(27);
             jTable3.getColumnModel().getColumn(27).setCellRenderer(centre);
              TableColumn col28=jTable3.getColumnModel().getColumn(28);
              jTable3.getColumnModel().getColumn(28).setCellRenderer(centre);
               TableColumn col29=jTable3.getColumnModel().getColumn(29);
               jTable3.getColumnModel().getColumn(29).setCellRenderer(centre);
                TableColumn col30=jTable3.getColumnModel().getColumn(30);
                jTable3.getColumnModel().getColumn(30).setCellRenderer(centre);
                 TableColumn col31=jTable3.getColumnModel().getColumn(31);
                 jTable3.getColumnModel().getColumn(31).setCellRenderer(centre);
                 
                     TableColumn col32=jTable3.getColumnModel().getColumn(32);
                 jTable3.getColumnModel().getColumn(32).setCellRenderer(centre);
                     TableColumn col33=jTable3.getColumnModel().getColumn(33);
                 jTable3.getColumnModel().getColumn(33).setCellRenderer(centre);
                     TableColumn col34=jTable3.getColumnModel().getColumn(34);
                 jTable3.getColumnModel().getColumn(34).setCellRenderer(centre);
                     TableColumn col35=jTable3.getColumnModel().getColumn(35);
                 jTable3.getColumnModel().getColumn(35).setCellRenderer(centre);
                 
                     TableColumn col36=jTable3.getColumnModel().getColumn(36);
                 jTable3.getColumnModel().getColumn(36).setCellRenderer(centre);
                     TableColumn col37=jTable3.getColumnModel().getColumn(37);
                 jTable3.getColumnModel().getColumn(37).setCellRenderer(centre);
                 
       
       
      
       
       col0.setPreferredWidth(230);
       col1.setPreferredWidth(130);
       col2.setPreferredWidth(90);
       col3.setPreferredWidth(90);
       col4.setPreferredWidth(60);
       col5.setPreferredWidth(60);
       col6.setPreferredWidth(60);
       col7.setPreferredWidth(50);
       col8.setPreferredWidth(50);
       col9.setPreferredWidth(50);
       col10.setPreferredWidth(50);
       col11.setPreferredWidth(50);
       col12.setPreferredWidth(50);
       col13.setPreferredWidth(50);
       col14.setPreferredWidth(50);
       col15.setPreferredWidth(50);
       col16.setPreferredWidth(50);
       
        col17.setPreferredWidth(50);
       col18.setPreferredWidth(50);
       col19.setPreferredWidth(50);
       col20.setPreferredWidth(50);
       col21.setPreferredWidth(50);
       col22.setPreferredWidth(50);
       col23.setPreferredWidth(50);
       col24.setPreferredWidth(50);
       col25.setPreferredWidth(50);
       col26.setPreferredWidth(50);
       
        col27.setPreferredWidth(50);
       col28.setPreferredWidth(50);
       col29.setPreferredWidth(50);
       col30.setPreferredWidth(50);
       col31.setPreferredWidth(50);
       
         col32.setPreferredWidth(50);
       
        col33.setPreferredWidth(50);
       col34.setPreferredWidth(50);
       col35.setPreferredWidth(50);
       col36.setPreferredWidth(50);
       col37.setPreferredWidth(50);
     
       
             }catch(Exception ex){
                 JOptionPane.showMessageDialog(null, ex);
             }
      
 
 }
   
      public void pour_mois(){
 
 try{
            String sql="select (Sum(credit)*100/sum(debit)) from program_daily where  year='"+years.getSelectedItem()+"' and sub_time='"+terms.getSelectedItem()+"' and Mois='"+month.getSelectedItem()+"' and  projet='"+buss.getSelectedItem()+"'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                 
                   Double sum1=rs.getDouble("(Sum(credit)*100/sum(debit))");
                
                NumberFormat nf = new DecimalFormat("#,###.000");
                String fn = nf.format(sum1);
               jTextField4.setText(fn+" %");
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex); } 
 
 try{
    String sql="select `Task` as 'Task Name', `Resource`, `Start`, `Finish`, `Days`, `_%_`,Mois as 'Month', `A1` as '1', `A2` as '2', `A3`as '3', `A4`as '4', `A5`as '5', `A6`as '6', `A7` as '7', `A8` as '8', `A9` as '9', `A10` as '10', `A11` as '11', `A12` as '12', `A13` as '13', `A14` as '14', `A15` as '15', `A16` as '16', `A17` as '17', `A18` as '18', `A19` as '19', `A20` as '20', `A21` as '21', `A22` as '22', `A23` as '23', `A24` as '24', `A25` as '25', `A26`as '26' , `A27` as '27', `A28` as '28', `A29` as '29', `A30` as '30', `A31` as '31' from program_task where year='"+years.getSelectedItem()+"' and sub_time='"+terms.getSelectedItem()+"' and Mois='"+month.getSelectedItem()+"' and projet='"+buss.getSelectedItem()+"'";
    //`LIBELLE`, `ENTRE`, `SORTIE`, `DATE`, `BUSS`, `USED`, `NUM`
    pst = con.prepareStatement(sql);
      rs= pst.executeQuery();
     
      jTable3.setModel(DbUtils.resultSetToTableModel(rs));
        DefaultTableCellRenderer centre =new DefaultTableCellRenderer();
        centre.setHorizontalAlignment(JLabel.CENTER);
     TableColumn col0=jTable3.getColumnModel().getColumn(0);
        TableColumn col1=jTable3.getColumnModel().getColumn(1);
         
        TableColumn col2=jTable3.getColumnModel().getColumn(2);
        jTable3.getColumnModel().getColumn(2).setCellRenderer(centre);
         TableColumn col3=jTable3.getColumnModel().getColumn(3);
         jTable3.getColumnModel().getColumn(3).setCellRenderer(centre);
          TableColumn col4=jTable3.getColumnModel().getColumn(4);
          jTable3.getColumnModel().getColumn(4).setCellRenderer(centre);
           TableColumn col5=jTable3.getColumnModel().getColumn(5);
           jTable3.getColumnModel().getColumn(5).setCellRenderer(centre);
          // jTable3.getColumnSelectionAllowed();
            TableColumn col6=jTable3.getColumnModel().getColumn(6);
            jTable3.getColumnModel().getColumn(6).setCellRenderer(centre);
            
            
             TableColumn col7=jTable3.getColumnModel().getColumn(7);
             jTable3.getColumnModel().getColumn(7).setCellRenderer(centre);
              TableColumn col8=jTable3.getColumnModel().getColumn(8);
              jTable3.getColumnModel().getColumn(8).setCellRenderer(centre);
               TableColumn col9=jTable3.getColumnModel().getColumn(9);
               jTable3.getColumnModel().getColumn(9).setCellRenderer(centre);
                TableColumn col10=jTable3.getColumnModel().getColumn(10);
                jTable3.getColumnModel().getColumn(10).setCellRenderer(centre);
                 TableColumn col11=jTable3.getColumnModel().getColumn(11);
                 jTable3.getColumnModel().getColumn(11).setCellRenderer(centre);
                  TableColumn col12=jTable3.getColumnModel().getColumn(12);
                  jTable3.getColumnModel().getColumn(12).setCellRenderer(centre);
                   TableColumn col13=jTable3.getColumnModel().getColumn(13);
                   jTable3.getColumnModel().getColumn(13).setCellRenderer(centre);
     TableColumn col14=jTable3.getColumnModel().getColumn(14);
                 jTable3.getColumnModel().getColumn(14).setCellRenderer(centre);
                  TableColumn col15=jTable3.getColumnModel().getColumn(15);
                  jTable3.getColumnModel().getColumn(15).setCellRenderer(centre);
                   TableColumn col16=jTable3.getColumnModel().getColumn(16);
                   jTable3.getColumnModel().getColumn(16).setCellRenderer(centre);
                   
                    TableColumn col17=jTable3.getColumnModel().getColumn(17);
             jTable3.getColumnModel().getColumn(17).setCellRenderer(centre);
              TableColumn col18=jTable3.getColumnModel().getColumn(18);
              jTable3.getColumnModel().getColumn(18).setCellRenderer(centre);
               TableColumn col19=jTable3.getColumnModel().getColumn(19);
               jTable3.getColumnModel().getColumn(19).setCellRenderer(centre);
                TableColumn col20=jTable3.getColumnModel().getColumn(20);
                jTable3.getColumnModel().getColumn(20).setCellRenderer(centre);
                 TableColumn col21=jTable3.getColumnModel().getColumn(21);
                 jTable3.getColumnModel().getColumn(21).setCellRenderer(centre);
                  TableColumn col22=jTable3.getColumnModel().getColumn(22);
                  jTable3.getColumnModel().getColumn(22).setCellRenderer(centre);
                   TableColumn col23=jTable3.getColumnModel().getColumn(23);
                   jTable3.getColumnModel().getColumn(23).setCellRenderer(centre);
     TableColumn col24=jTable3.getColumnModel().getColumn(24);
                 jTable3.getColumnModel().getColumn(24).setCellRenderer(centre);
                  TableColumn col25=jTable3.getColumnModel().getColumn(25);
                  jTable3.getColumnModel().getColumn(25).setCellRenderer(centre);
                   TableColumn col26=jTable3.getColumnModel().getColumn(26);
                   jTable3.getColumnModel().getColumn(26).setCellRenderer(centre);
                   
                    TableColumn col27=jTable3.getColumnModel().getColumn(27);
             jTable3.getColumnModel().getColumn(27).setCellRenderer(centre);
              TableColumn col28=jTable3.getColumnModel().getColumn(28);
              jTable3.getColumnModel().getColumn(28).setCellRenderer(centre);
               TableColumn col29=jTable3.getColumnModel().getColumn(29);
               jTable3.getColumnModel().getColumn(29).setCellRenderer(centre);
                TableColumn col30=jTable3.getColumnModel().getColumn(30);
                jTable3.getColumnModel().getColumn(30).setCellRenderer(centre);
                 TableColumn col31=jTable3.getColumnModel().getColumn(31);
                 jTable3.getColumnModel().getColumn(31).setCellRenderer(centre);
                 
                     TableColumn col32=jTable3.getColumnModel().getColumn(32);
                 jTable3.getColumnModel().getColumn(32).setCellRenderer(centre);
                     TableColumn col33=jTable3.getColumnModel().getColumn(33);
                 jTable3.getColumnModel().getColumn(33).setCellRenderer(centre);
                     TableColumn col34=jTable3.getColumnModel().getColumn(34);
                 jTable3.getColumnModel().getColumn(34).setCellRenderer(centre);
                     TableColumn col35=jTable3.getColumnModel().getColumn(35);
                 jTable3.getColumnModel().getColumn(35).setCellRenderer(centre);
                 
                     TableColumn col36=jTable3.getColumnModel().getColumn(36);
                 jTable3.getColumnModel().getColumn(36).setCellRenderer(centre);
                     TableColumn col37=jTable3.getColumnModel().getColumn(37);
                 jTable3.getColumnModel().getColumn(37).setCellRenderer(centre);
                  
       
       
      
       
       col0.setPreferredWidth(230);
       col1.setPreferredWidth(130);
       col2.setPreferredWidth(90);
       col3.setPreferredWidth(90);
       col4.setPreferredWidth(60);
       col5.setPreferredWidth(60);
       col6.setPreferredWidth(60);
       col7.setPreferredWidth(50);
       col8.setPreferredWidth(50);
       col9.setPreferredWidth(50);
       col10.setPreferredWidth(50);
       col11.setPreferredWidth(50);
       col12.setPreferredWidth(50);
       col13.setPreferredWidth(50);
       col14.setPreferredWidth(50);
       col15.setPreferredWidth(50);
       col16.setPreferredWidth(50);
       
        col17.setPreferredWidth(50);
       col18.setPreferredWidth(50);
       col19.setPreferredWidth(50);
       col20.setPreferredWidth(50);
       col21.setPreferredWidth(50);
       col22.setPreferredWidth(50);
       col23.setPreferredWidth(50);
       col24.setPreferredWidth(50);
       col25.setPreferredWidth(50);
       col26.setPreferredWidth(50);
       
        col27.setPreferredWidth(50);
       col28.setPreferredWidth(50);
       col29.setPreferredWidth(50);
       col30.setPreferredWidth(50);
       col31.setPreferredWidth(50);
       
         col32.setPreferredWidth(50);
       
        col33.setPreferredWidth(50);
       col34.setPreferredWidth(50);
       col35.setPreferredWidth(50);
       col36.setPreferredWidth(50);
       col37.setPreferredWidth(50);
      
       
       
             }catch(Exception ex){
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
        jLabel1 = new javax.swing.JLabel();
        buss = new javax.swing.JComboBox<>();
        jTextField1 = new javax.swing.JTextField();
        years = new javax.swing.JComboBox<>();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        terms = new javax.swing.JComboBox<>();
        month = new javax.swing.JComboBox<>();
        jTextField4 = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();

        setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("X");
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        buss.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        buss.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select One Project" }));
        buss.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                bussPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
                bussPopupMenuWillBecomeVisible(evt);
            }
        });

        jTextField1.setEditable(false);
        jTextField1.setBackground(new java.awt.Color(240, 240, 241));
        jTextField1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jTextField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField1.setText("%");

        years.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        years.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Years" }));
        years.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                yearsPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        jTextField2.setEditable(false);
        jTextField2.setBackground(new java.awt.Color(240, 240, 241));
        jTextField2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jTextField2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField2.setText("%");

        jTextField3.setEditable(false);
        jTextField3.setBackground(new java.awt.Color(240, 240, 241));
        jTextField3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jTextField3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField3.setText("%");

        terms.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        terms.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Term & Semester" }));
        terms.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                termsPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        terms.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                termsActionPerformed(evt);
            }
        });

        month.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        month.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Month" }));
        month.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                monthPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        month.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                monthActionPerformed(evt);
            }
        });

        jTextField4.setEditable(false);
        jTextField4.setBackground(new java.awt.Color(240, 240, 241));
        jTextField4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jTextField4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField4.setText("%");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(buss, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(years, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(terms, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(month, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buss)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
                    .addComponent(jTextField1)
                    .addComponent(years)
                    .addComponent(jTextField2)
                    .addComponent(terms)
                    .addComponent(jTextField3)
                    .addComponent(month)
                    .addComponent(jTextField4))
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Task Name", "Resource", "Start", "Finish", "Days", "Done", "_%_", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable3.setRowHeight(24);
        jScrollPane1.setViewportView(jTable3);
        if (jTable3.getColumnModel().getColumnCount() > 0) {
            jTable3.getColumnModel().getColumn(0).setPreferredWidth(300);
            jTable3.getColumnModel().getColumn(1).setPreferredWidth(200);
            jTable3.getColumnModel().getColumn(2).setPreferredWidth(100);
            jTable3.getColumnModel().getColumn(3).setPreferredWidth(100);
            jTable3.getColumnModel().getColumn(4).setPreferredWidth(50);
            jTable3.getColumnModel().getColumn(6).setPreferredWidth(50);
            jTable3.getColumnModel().getColumn(7).setPreferredWidth(50);
            jTable3.getColumnModel().getColumn(8).setPreferredWidth(50);
            jTable3.getColumnModel().getColumn(9).setPreferredWidth(50);
            jTable3.getColumnModel().getColumn(10).setPreferredWidth(50);
            jTable3.getColumnModel().getColumn(11).setPreferredWidth(50);
            jTable3.getColumnModel().getColumn(12).setPreferredWidth(50);
            jTable3.getColumnModel().getColumn(13).setPreferredWidth(50);
            jTable3.getColumnModel().getColumn(14).setPreferredWidth(50);
            jTable3.getColumnModel().getColumn(15).setPreferredWidth(50);
            jTable3.getColumnModel().getColumn(16).setPreferredWidth(50);
            jTable3.getColumnModel().getColumn(17).setPreferredWidth(50);
            jTable3.getColumnModel().getColumn(18).setPreferredWidth(50);
            jTable3.getColumnModel().getColumn(19).setPreferredWidth(50);
            jTable3.getColumnModel().getColumn(20).setPreferredWidth(50);
            jTable3.getColumnModel().getColumn(21).setPreferredWidth(50);
            jTable3.getColumnModel().getColumn(22).setPreferredWidth(50);
            jTable3.getColumnModel().getColumn(23).setPreferredWidth(50);
            jTable3.getColumnModel().getColumn(24).setPreferredWidth(50);
            jTable3.getColumnModel().getColumn(25).setPreferredWidth(50);
            jTable3.getColumnModel().getColumn(26).setPreferredWidth(50);
            jTable3.getColumnModel().getColumn(27).setPreferredWidth(50);
            jTable3.getColumnModel().getColumn(28).setPreferredWidth(50);
            jTable3.getColumnModel().getColumn(29).setPreferredWidth(50);
            jTable3.getColumnModel().getColumn(30).setPreferredWidth(50);
            jTable3.getColumnModel().getColumn(31).setPreferredWidth(50);
            jTable3.getColumnModel().getColumn(32).setPreferredWidth(50);
            jTable3.getColumnModel().getColumn(33).setPreferredWidth(50);
            jTable3.getColumnModel().getColumn(34).setPreferredWidth(50);
            jTable3.getColumnModel().getColumn(35).setPreferredWidth(50);
            jTable3.getColumnModel().getColumn(36).setPreferredWidth(50);
            jTable3.getColumnModel().getColumn(37).setPreferredWidth(50);
        }

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        this.show(false);        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel1MouseClicked

    private void bussPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_bussPopupMenuWillBecomeInvisible
callculate(); 
        show_in_tables();
        load_combo();
pour_projet();//    show_in_tabl();
        //  call_tableinds();
     //   show_in_table();// TODO add your handling code here:
    }//GEN-LAST:event_bussPopupMenuWillBecomeInvisible

    private void bussPopupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_bussPopupMenuWillBecomeVisible
        // TODO add your handling code here:
    }//GEN-LAST:event_bussPopupMenuWillBecomeVisible

    private void termsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_termsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_termsActionPerformed

    private void monthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_monthActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_monthActionPerformed

    private void yearsPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_yearsPopupMenuWillBecomeInvisible
pour_year();        // TODO add your handling code here:
    }//GEN-LAST:event_yearsPopupMenuWillBecomeInvisible

    private void termsPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_termsPopupMenuWillBecomeInvisible
pour_term();        // TODO add your handling code here:
    }//GEN-LAST:event_termsPopupMenuWillBecomeInvisible

    private void monthPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_monthPopupMenuWillBecomeInvisible
pour_mois();        // TODO add your handling code here:
    }//GEN-LAST:event_monthPopupMenuWillBecomeInvisible


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> buss;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JComboBox<String> month;
    private javax.swing.JComboBox<String> terms;
    private javax.swing.JComboBox<String> years;
    // End of variables declaration//GEN-END:variables
}
