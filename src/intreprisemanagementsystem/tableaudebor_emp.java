/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intreprisemanagementsystem;

import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author DOSHE
 */
public class tableaudebor_emp extends javax.swing.JInternalFrame {

     DefaultTableModel mode;
Connection con=null;
PreparedStatement pst=null;
ResultSet rs= null;
String Rolls;
    public tableaudebor_emp() {
        initComponents();
              con=JavaDbConnect.dbConnect();
        Call_ID_TO_BOMBOBOX();
      
       jPanel12.scrollRectToVisible(jPanel5.getBounds());
    }
     public void Call_ID_TO_BOMBOBOX()
    {
         
        try{
            String sql="select DESCR   from  agin";
           // String sql="select NOM from  agin";
          
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                String sum=rs.getString("DESCR");
                  agins.addItem(sum);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
    }

     Date b;
     Date days;
     int diff;
     
     public void datete(){
         
          try{
            String sql="select DATES  from   tableau_de_bord where DESCR='"+agins.getSelectedItem()+"'";
           // String sql="select NOM from  agin";
          
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
               b=rs.getDate("DATES");
                 
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
         // SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
          
          Date a= new Date();
         Calendar days1 = Calendar.getInstance();
         days1.setTime(a);
         Calendar days2 = Calendar.getInstance();
         days1.setTime(b);
         
         int from = days1.get(Calendar.DAY_OF_YEAR);
         int to = days2.get(Calendar.DAY_OF_YEAR);
         diff= to-from;
         depart.setText(""+diff);
         
     
     }
     
     String place;
     String type;
       public void select_combo3(){
           datete();
           if(diff<7){
           
           
      
   
           
            String tmp = agins.getSelectedItem().toString();
           
                
                 
                  try{
            String sql="select max(KM) from  driver_info where AGIN='"+tmp+"'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
               
                
                   String sum5=rs.getString("max(KM)");
                 km.setText(sum5);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
                         try{
            String sql="select PLACE,TYPE from  AGIN where DESCR='"+tmp+"'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
               
                
                   place=rs.getString("PLACE");
                    type=rs.getString("TYPE");
              //   motif.setText(type);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
                  
                  
                //    String tmp = jComboBox3.getSelectedItem().toString();
           
                 try{
                     
            String sql="select * from  tableau_de_bord where DESCR='"+tmp+"'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
         
                        if ("oui".equals(rs.getString("HUILLE_MOTEUR"))){
                        huille1.setSelected(true);     
                        }     
              
                        if ("non".equals(rs.getString("HUILLE_MOTEUR"))){
                        huille0.setSelected(true);
                        }
                        String sum=rs.getString("HUILLED");
                   huilled.setText(sum);
                        
                        if ("oui".equals(rs.getString("LIQUIDE_REFROIDISSEMENT"))){
                        refroidisement1.setSelected(true);     
                        }
                        if ("non".equals(rs.getString("LIQUIDE_REFROIDISSEMENT"))){
                        refroidisement0.setSelected(true);
                        
                        }
                        String sum1=rs.getString("LIQUIDE_REFROIDISSEMENTD");
                   refroidisementd.setText(sum1);
                   
                        if ("oui".equals(rs.getString("LIQUIDE_LAVE_GLASE"))){
                        glase1.setSelected(true);     
                        }
                        if ("non".equals(rs.getString("LIQUIDE_LAVE_GLASE"))){
                        glase0.setSelected(true);
                        
                        }
                        String sum2=rs.getString("LIQUIDE_LAVE_GLASED");
                   glased.setText(sum2);
                        
                        if ("oui".equals(rs.getString("LIQUIDE_DE_FREIN"))){
                        liquidefreins1.setSelected(true);     
                        }
                        if ("non".equals(rs.getString("LIQUIDE_DE_FREIN"))){
                        liquidefreins0.setSelected(true);
                        
                        }
                         String sum3=rs.getString("LIQUIDE_DE_FREIND");
                   liquidefreinsd.setText(sum3);
                   
                        if ("oui".equals(rs.getString("LIQUIDE_BATERIE"))){
                        batterie1.setSelected(true);     
                        }
                        if ("non".equals(rs.getString("LIQUIDE_BATERIE"))){
                        batterie0.setSelected(true);
                        
                        }
                        String sum4=rs.getString("LIQUIDE_BATERIED");
                   batteried.setText(sum4);
                        
                        if ("oui".equals(rs.getString("PRESION_AVANT"))){
                       avant1.setSelected(true);     
                        }
                        if ("non".equals(rs.getString("PRESION_AVANT"))){
                        avant0.setSelected(true);
                        
                        }
                          String sum5=rs.getString("PRESION_AVANTD");
                   avantd.setText(sum5);
                   
                        if ("oui".equals(rs.getString("PRESION_ARRIERE"))){
                        arriere1.setSelected(true);     
                        }
                        if ("non".equals(rs.getString("PRESION_ARRIERE"))){
                       arriere0.setSelected(true);
                        
                        }
                         String sum6=rs.getString("PRESION_ARRIERED");
                   arriered.setText(sum6);
                   
                        if ("oui".equals(rs.getString("COLE"))){
                        cole1.setSelected(true);     
                        }
                        if ("non".equals(rs.getString("COLE"))){
                        cole0.setSelected(true);
                        
                        }
                         String sum7=rs.getString("COLED");
                   coled.setText(sum7);
                   
                        if ("oui".equals(rs.getString("VITRE"))){
                        vitre1.setSelected(true);     
                        }
                        if ("non".equals(rs.getString("VITRE"))){
                        vitre0.setSelected(true);
                        
                        }
                        String sum8=rs.getString("VITRED");
                   vitred.setText(sum8);
                   
                        if ("oui".equals(rs.getString("ESSUIS"))){
                        seeuisglase1.setSelected(true);     
                        }
                        if ("non".equals(rs.getString("ESSUIS"))){
                        seeuisglase0.setSelected(true);
                        
                        }
                        String sum9=rs.getString("ESSUISD");
                  seeuisglased.setText(sum9);
                        
                         if ("oui".equals(rs.getString("RETRO"))){
                        retroviseurs1.setSelected(true);     
                        }
                        if ("non".equals(rs.getString("RETRO"))){
                        retroviseurs0.setSelected(true);
                        
                        }
                        
                         String sum10=rs.getString("RETROD");
                  retroviseursd.setText(sum10);
                        
                        
                        
                         if ("oui".equals(rs.getString("IMMATR"))){
                        immatriculation1.setSelected(true);     
                        }
                        if ("non".equals(rs.getString("IMMATR"))){
                        immatriculation0.setSelected(true);
                        
                        }
                        
                         String sum11=rs.getString("IMMATRD");
                  immatriculationd.setText(sum11);
                        
                         if ("oui".equals(rs.getString("GIRO"))){
                        girophare1.setSelected(true);     
                        }
                        if ("non".equals(rs.getString("GIRO"))){
                        girophare0.setSelected(true);
                        
                        }
                        String sum12=rs.getString("GIROD");
                  girophared.setText(sum12);
                  
                         if ("oui".equals(rs.getString("POSITION"))){
                        feuxdepossition1.setSelected(true);     
                        }
                        if ("non".equals(rs.getString("POSITION"))){
                        feuxdepossition0.setSelected(true);
                        
                        }
                         String sum13=rs.getString("POSITIOND");
                  feuxdepossitiond.setText(sum13);
                  
                  
                         if ("oui".equals(rs.getString("STOP"))){
                        feuxstop1.setSelected(true);     
                        }
                        if ("non".equals(rs.getString("STOP"))){
                        feuxstop0.setSelected(true);
                        
                        }
                        
                        String sum14=rs.getString("STOPD");
                  feuxstopd.setText(sum14);
                        
                         if ("oui".equals(rs.getString("CLIGNOTA"))){
                       clignotant1.setSelected(true);     
                        }
                        if ("non".equals(rs.getString("CLIGNOTA"))){
                       clignotant0.setSelected(true);
                        
                        }
                        
                        String sum15=rs.getString("CLIGNOTAD");
                  clignotantd.setText(sum15);
                        
                         if ("oui".equals(rs.getString("PHARE"))){
                        phare1.setSelected(true);     
                        }
                        if ("non".equals(rs.getString("PHARE"))){
                        phare0.setSelected(true);
                        
                        }
                         String sum16=rs.getString("PHARED");
                  phared.setText(sum16);
                         if ("oui".equals(rs.getString("CLAXION"))){
                        claxion1.setSelected(true);     
                        }
                        if ("non".equals(rs.getString("CLAXION"))){
                       claxion0.setSelected(true);
                        
                        }
                        
                        String sum17=rs.getString("CLAXIOND");
                  claxiond.setText(sum17);
                        
                         if ("oui".equals(rs.getString("SEINTURE"))){
                        seinture1.setSelected(true);     
                        }
                        if ("non".equals(rs.getString("SEINTURE"))){
                       seinture0.setSelected(true);
                        
                        }
                        
                        String sum18=rs.getString("SEINTURED");
                  seintured.setText(sum18);
                        
                         if ("oui".equals(rs.getString("PARAPRISE"))){
                        paraprise1.setSelected(true);     
                        }
                        if ("non".equals(rs.getString("PARAPRISE"))){
                        paraprise0.setSelected(true);
                        
                        }
                        
                        String sum19=rs.getString("PARAPRISED");
                  paraprised.setText(sum19);
                        
                          if ("oui".equals(rs.getString("FREIN"))){
                        frien1.setSelected(true);     
                        }
                        if ("non".equals(rs.getString("FREIN"))){
                       frien0.setSelected(true);
                        
                        }
                        String sum20=rs.getString("FREIND");
                  friend.setText(sum20);
                         if ("oui".equals(rs.getString("FRIEN_A_MAIN"))){
                        freinamain1.setSelected(true);     
                        }
                        if ("non".equals(rs.getString("FRIEN_A_MAIN"))){
                        freinamain0.setSelected(true);
                        
                        }
                        
                         String sum21=rs.getString("FRIEN_A_MAIND");
                  freinamaind.setText(sum21);
                        
                          if ("oui".equals(rs.getString("PNEU_RESERVE"))){
                        pneureserve1.setSelected(true);     
                        }
                        if ("non".equals(rs.getString("PNEU_RESERVE"))){
                      pneureserve0.setSelected(true);
                        
                        }
                        
                          String sum22=rs.getString("PNEU_RESERVED");
                  pneureserved.setText(sum22);
                        
                         if ("oui".equals(rs.getString("INTERIERE"))){
                        interiere1.setSelected(true);     
                        }
                        if ("non".equals(rs.getString("INTERIERE"))){
                        interiere0.setSelected(true);
                        
                        }
                        String sum23=rs.getString("INTERIERED");
                  interiered.setText(sum23);
                         if ("oui".equals(rs.getString("EXTERIERE"))){
                        exteriere1.setSelected(true);     
                        }
                        if ("non".equals(rs.getString("EXTERIERE"))){
                        exteriere0.setSelected(true);
                        
                        }
                 
                 String sum24=rs.getString("EXTERIERED");
                  exteriere0.setText(sum24);
                 
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
                  
                  
           }else{JOptionPane.showMessageDialog(null, "No Available Dash Bord Sets");  }         
                  
                 
      }   
       
        public void refresh(){

huille1.setSelected(false);
huille0.setSelected(false);

refroidisement1.setSelected(false);
refroidisement0.setSelected(false);

glase1.setSelected(false);
glase0.setSelected(false);

liquidefreins1.setSelected(false);
liquidefreins0.setSelected(false);


batterie1.setSelected(false);
batterie0.setSelected(false);

avant1.setSelected(false);
avant0.setSelected(false);

arriere0.setSelected(false);
arriere1.setSelected(false);

cole0.setSelected(false);
cole1.setSelected(false);

vitre1.setSelected(false);
vitre0.setSelected(false);

seeuisglase1.setSelected(false);
seeuisglase0.setSelected(false);

retroviseurs1.setSelected(false);
retroviseurs0.setSelected(false);

immatriculation1.setSelected(false);
immatriculation0.setSelected(false);

girophare0.setSelected(false);
girophare1.setSelected(false);

interiere0.setSelected(false);
interiere1.setSelected(false);

exteriere0.setSelected(false);
exteriere1.setSelected(false);

feuxdepossition1.setSelected(false);
feuxdepossition0.setSelected(false);

feuxstop0.setSelected(false);
feuxstop1.setSelected(false);

clignotant0.setSelected(false);
clignotant1.setSelected(false);

phare0.setSelected(false);
phare1.setSelected(false);

claxion0.setSelected(false);
claxion1.setSelected(false);

seinture0.setSelected(false);
seinture1.setSelected(false);

paraprise0.setSelected(false);
paraprise1.setSelected(false);

frien0.setSelected(false);
frien1.setSelected(false);

freinamain1.setSelected(false);
freinamain0.setSelected(false);

pneureserve0.setSelected(false);
pneureserve1.setSelected(false);

interiere1.setSelected(false);
interiere0.setSelected(false);
exteriere1.setSelected(false);
exteriere0.setSelected(false);

      
      
      
      }
        
        public void rolls()
     {
         try{
            String sql="SELECT REFF_NUM FROM driver_info ORDER BY REFF_NUM DESC LIMIT 1";
             pst=con.prepareStatement(sql);
             rs=pst.executeQuery();
             if(rs.next()){
                 String rl=rs.getString("REFF_NUM");
                 int ln=rl.length();
                 String stxt=rl.substring(0,9);
                 String snum=rl.substring(9,ln);
                 int n = Integer.parseInt(snum);
                 n++;
                 snum=Integer.toString(n);
                 Rolls=stxt+snum;
                 
                 
                 
             }else{
                 //rolls="FICHE/EB/2018/1";
                 Rolls="AGIN/OUT-1001";
             }
                 
             
         }catch(Exception e){
             JOptionPane.showMessageDialog(null, e); 
             
         }
     }
        
         public void attCall_roll()
    {
      
         String A =JOptionPane.showInputDialog("Please Entre Your Roll Number!!!"); 
        try{
            String sql="select * from Active_employee where ROLL= ? and Locks='Active'";
            
           
             pst=con.prepareStatement(sql);
            pst.setString(1, A);
            rs=pst.executeQuery();
            if(rs.next()){
             String add1 = rs.getString("NOMS");
             String add2 = rs.getString("LNAME");
              conducteur.setText(add1+" "+add2);
            }else{JOptionPane.showMessageDialog(null, "Invalid Roll No. or Does not Have Info");  
            
              attCall_roll();
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
   
    }   
    
        
                public void validation(){
            
            
            
           rolls();
            
            
   
        try{
        
        String sql="Insert into driver_info (`AGIN`, `NAME_DRIVER`,`NBRE DE PASSAGER`, `DEPART`, `ARRIVE`, `REFIL`, `KM`, `KM_EFF`, `MOTIF`, `DATES`, `VALIDATION`, `REFF_NUM`)"+
                "values(?,?,?,?,?,?,?,?,?,?,?,?)";
        pst=con.prepareStatement(sql);
        String value=agins.getSelectedItem().toString();
        pst.setString(1, value);
         pst.setString(2, conducteur.getText());
pst.setString(3, passager.getText());
pst.setString(4, depart.getText());
pst.setString(5, arrive.getText());
pst.setString(6, refil.getText());
pst.setString(8, km.getText());
pst.setString(7, kmcond.getText());
pst.setString(9, motif.getText());

pst.setString(11,"");
pst.setString(12,Rolls);

SimpleDateFormat dateFormats = new SimpleDateFormat("dd.MM.yyyy");
         String addDate = dateFormats.format(new Date());
         pst.setString(10, addDate);
        
        
    pst.executeUpdate();
   // JOptionPane.showMessageDialog(null, "SAVED");
        
        }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}
        
            //}
        }
                
                 public void call_table(){
      
           try{
           
             String sql="SELECT `REFF_NUM`,`AGIN`,`DATES`,`VALIDATION` AS 'VAL' FROM `driver_info` where NAME_DRIVER='"+conducteur.getText()+"'";
     //  String sql="SELECT ID, `NUM_FACTURE`, `DATE_FACTURE`, `DESCRIPTION`, `DEBIT_QTY`, `DEBIT_PU`, `DEBIT_PT`, `DEBIT_TVA`, `CREDIT_QTY`, `CREDIT_PU`, `CREDIT_PT`, `CREDIT_TVA`, `DATES`, `NUM` FROM facture_fournisseur where compte='"+jComboBox6.getSelectedItem()+"'";
       pst = con.prepareStatement(sql);
      rs= pst.executeQuery();
      
       
       jTable1.setModel(DbUtils.resultSetToTableModel(rs));
       mode=new DefaultTableModel();
       
       
        TableColumn col0=jTable1.getColumnModel().getColumn(0);
        TableColumn col1=jTable1.getColumnModel().getColumn(1);
         TableColumn col2=jTable1.getColumnModel().getColumn(2);
         TableColumn col3=jTable1.getColumnModel().getColumn(3);
        
      
       
       col0.setPreferredWidth(80);
       col1.setPreferredWidth(100);
       col2.setPreferredWidth(50);
       col3.setPreferredWidth(20);
     
     
      
       
       
      // jTable1.setModel(mode);
       
     
    }catch(SQLException ex ){
     JOptionPane.showMessageDialog(null, ex);
}
    
      
      }
                 
                 public void validation_(){
                 
                 
                    Float A = Float.parseFloat(place);
        Float B = Float.parseFloat(passager.getText());
        //  Float c;

        if(B > A){
            JOptionPane.showMessageDialog(null, "PLEASE PASSENGERS OVER LOADED");

        }else{
            if(type.equals("VEHICULE ESSENCE")){

                //

                Double a = Double.parseDouble(kmcond.getText());
                Double b = Double.parseDouble(km.getText());
                Integer e = 10000;
                Double c;
                Double d;
                c=a+b;
                d=(c/e)*100;
                if(c >= e){

                    VALIDATION.setBackground(Color.red) ;
                    VALIDATION.setHorizontalAlignment( VALIDATION.CENTER);
                    VALIDATION.setText("CHECKING GENERAL!!! \n VIDAGES D'HUILLE ET FILTRE A HUILLE");

                }else if(d >=80){
                    VALIDATION.setBackground(Color.yellow) ;
                    VALIDATION.setHorizontalAlignment( VALIDATION.CENTER);
                    VALIDATION.setText("REQUIRE GENERAL CHECKING \n  VIDAGES D'HUILLE!!!");
                    validation();

                }else if(huille0.isSelected() ){
                    VALIDATION.setBackground(Color.red) ;
                    VALIDATION.setHorizontalAlignment( VALIDATION.CENTER);
                    VALIDATION.setText(" CHECK  VERIFICATION DES \n NIVEAUX(Huilles et Liquides)");

                }else if( refroidisement0.isSelected() ){
                    VALIDATION.setBackground(Color.red) ;
                    VALIDATION.setHorizontalAlignment( VALIDATION.CENTER);
                    VALIDATION.setText(" CHECK  VERIFICATION DES \n NIVEAUX(Huilles et Liquides)");

                }else if(glase0.isSelected() ){
                    VALIDATION.setBackground(Color.red) ;
                    VALIDATION.setHorizontalAlignment( VALIDATION.CENTER);
                    VALIDATION.setText(" CHECK  VERIFICATION DES NIVEAUX \n (Huilles et Liquides)");

                }else if( liquidefreins0.isSelected() ){
                    VALIDATION.setBackground(Color.red) ;
                    VALIDATION.setHorizontalAlignment( VALIDATION.CENTER);
                    VALIDATION.setText(" CHECK  VERIFICATION DES NIVEAUX \n (Huilles et Liquides)");

                }else if( batterie0.isSelected() ){
                    VALIDATION.setBackground(Color.red) ;
                    VALIDATION.setHorizontalAlignment( VALIDATION.CENTER);
                    VALIDATION.setText(" CHECK  VERIFICATION DES NIVEAUX \n (Huilles et Liquides)");

                }else if(retroviseurs0.isSelected() ){
                    VALIDATION.setBackground(Color.red) ;
                    VALIDATION.setHorizontalAlignment( VALIDATION.CENTER);
                    VALIDATION.setText(" CHECK  LA  VISIBILITE");

                }else if(girophare0.isSelected()){
                    VALIDATION.setBackground(Color.red) ;
                    VALIDATION.setHorizontalAlignment( VALIDATION.CENTER);
                    VALIDATION.setText(" CHECK  LA  VISIBILITE");

                }else if(vitre0.isSelected() ){
                    VALIDATION.setBackground(Color.red) ;
                    VALIDATION.setHorizontalAlignment( VALIDATION.CENTER);
                    VALIDATION.setText(" CHECK  LA  VISIBILITE");

                }else if(feuxdepossition0.isSelected() ){
                    VALIDATION.setBackground(Color.red) ;
                    VALIDATION.setHorizontalAlignment( VALIDATION.CENTER);
                    VALIDATION.setText(" CHECK  L'ECLAIRAGE");

                }else if(feuxstop0.isSelected()){
                    VALIDATION.setBackground(Color.red) ;
                    VALIDATION.setHorizontalAlignment( VALIDATION.CENTER);
                    VALIDATION.setText(" CHECK  L'ECLAIRAGE");

                }else if(clignotant0.isSelected()){
                    VALIDATION.setBackground(Color.red) ;
                    VALIDATION.setHorizontalAlignment( VALIDATION.CENTER);
                    VALIDATION.setText(" CHECK  L'ECLAIRAGE");

                }else if(phare0.isSelected() ){
                    VALIDATION.setBackground(Color.red) ;
                    VALIDATION.setHorizontalAlignment( VALIDATION.CENTER);
                    VALIDATION.setText(" CHECK  L'ECLAIRAGE");

                }else if(claxion0.isSelected()){
                    VALIDATION.setBackground(Color.red) ;
                    VALIDATION.setHorizontalAlignment( VALIDATION.CENTER);
                    VALIDATION.setText(" CHECK  LE  SYSTEME DE SECURITE");
                }else if(seinture0.isSelected()){
                    VALIDATION.setBackground(Color.red) ;
                    VALIDATION.setHorizontalAlignment( VALIDATION.CENTER);
                    VALIDATION.setText(" CHECK  LE  SYSTEME DE SECURITE");
                }else if( paraprise0.isSelected()){
                    VALIDATION.setBackground(Color.red) ;
                    VALIDATION.setHorizontalAlignment( VALIDATION.CENTER);
                    VALIDATION.setText(" CHECK  LE  SYSTEME DE SECURITE");
                }else if(frien0.isSelected()  ){
                    VALIDATION.setBackground(Color.red) ;
                    VALIDATION.setHorizontalAlignment( VALIDATION.CENTER);
                    VALIDATION.setText(" CHECK  LE  SYSTEME DE SECURITE");
                }else{
                    VALIDATION.setText(null);
                    VALIDATION.setBackground(Color.GREEN) ;
                    validation();
                }

            }else if(type.equals("VEHICULE DIESEL")){

                Double a = Double.parseDouble(kmcond.getText());
                Double b = Double.parseDouble(km.getText());
                Integer e = 7000;
                Double c;
                Double d;
                c=a+b;
                d=(c/e)*100;
                if(c >= e){

                    VALIDATION.setBackground(Color.red) ;
                    VALIDATION.setHorizontalAlignment( VALIDATION.CENTER);
                    VALIDATION.setText("CHECKING GENERAL!!! \n VIDAGES D'HUILLE ET FILTRE A HUILLE");

                }else if(d >=80){
                    VALIDATION.setBackground(Color.yellow) ;
                    VALIDATION.setHorizontalAlignment( VALIDATION.CENTER);
                    VALIDATION.setText("REQUIRE GENERAL CHECKING !!! \n VIDAGES D'HUILLE!!!");
                    validation();

                }else if(huille0.isSelected() ){
                    VALIDATION.setBackground(Color.red) ;
                    VALIDATION.setHorizontalAlignment( VALIDATION.CENTER);
                    VALIDATION.setText(" CHECK  VERIFICATION DES NIVEAUX \n (Huilles et Liquides)");

                }else if( refroidisement0.isSelected() ){
                    VALIDATION.setBackground(Color.red) ;
                    VALIDATION.setHorizontalAlignment( VALIDATION.CENTER);
                    VALIDATION.setText(" CHECK  VERIFICATION DES NIVEAUX \n (Huilles et Liquides)");

                }else if(glase0.isSelected() ){
                    VALIDATION.setBackground(Color.red) ;
                    VALIDATION.setHorizontalAlignment( VALIDATION.CENTER);
                    VALIDATION.setText(" CHECK  VERIFICATION DES NIVEAUX \n (Huilles et Liquides)");

                }else if( liquidefreins0.isSelected() ){
                    VALIDATION.setBackground(Color.red) ;
                    VALIDATION.setHorizontalAlignment( VALIDATION.CENTER);
                    VALIDATION.setText(" CHECK  VERIFICATION DES NIVEAUX \n (Huilles et Liquides)");

                }else if( batterie0.isSelected() ){
                    VALIDATION.setBackground(Color.red) ;
                    VALIDATION.setHorizontalAlignment( VALIDATION.CENTER);
                    VALIDATION.setText(" CHECK  VERIFICATION DES NIVEAUX \n (Huilles et Liquides)");

                }else if(retroviseurs0.isSelected() ){
                    VALIDATION.setBackground(Color.red) ;
                    VALIDATION.setHorizontalAlignment( VALIDATION.CENTER);
                    VALIDATION.setText(" CHECK  LA  VISIBILITE");

                }else if(girophare0.isSelected()){
                    VALIDATION.setBackground(Color.red) ;
                    VALIDATION.setHorizontalAlignment( VALIDATION.CENTER);
                    VALIDATION.setText(" CHECK  LA  VISIBILITE");

                }else if(vitre0.isSelected() ){
                    VALIDATION.setBackground(Color.red) ;
                    VALIDATION.setHorizontalAlignment( VALIDATION.CENTER);
                    VALIDATION.setText(" CHECK  LA  VISIBILITE");

                }else if(feuxdepossition0.isSelected() ){
                    VALIDATION.setBackground(Color.red) ;
                    VALIDATION.setHorizontalAlignment( VALIDATION.CENTER);
                    VALIDATION.setText(" CHECK  L'ECLAIRAGE");

                }else if(feuxstop0.isSelected()){
                    VALIDATION.setBackground(Color.red) ;
                    VALIDATION.setHorizontalAlignment( VALIDATION.CENTER);
                    VALIDATION.setText(" CHECK  L'ECLAIRAGE");

                }else if(clignotant0.isSelected()){
                    VALIDATION.setBackground(Color.red) ;
                    VALIDATION.setHorizontalAlignment( VALIDATION.CENTER);
                    VALIDATION.setText(" CHECK  L'ECLAIRAGE");

                }else if(phare0.isSelected() ){
                    VALIDATION.setBackground(Color.red) ;
                    VALIDATION.setHorizontalAlignment( VALIDATION.CENTER);
                    VALIDATION.setText(" CHECK  L'ECLAIRAGE");

                }else if(claxion0.isSelected()){
                    VALIDATION.setBackground(Color.red) ;
                    VALIDATION.setHorizontalAlignment( VALIDATION.CENTER);
                    VALIDATION.setText(" CHECK  LE  SYSTEME DE SECURITE");
                }else if(seinture0.isSelected()){
                    VALIDATION.setBackground(Color.red) ;
                    VALIDATION.setHorizontalAlignment( VALIDATION.CENTER);
                    VALIDATION.setText(" CHECK  LE  SYSTEME DE SECURITE");
                }else if( paraprise0.isSelected()){
                    VALIDATION.setBackground(Color.red) ;
                    VALIDATION.setHorizontalAlignment( VALIDATION.CENTER);
                    VALIDATION.setText(" CHECK  LE  SYSTEME DE SECURITE");
                }else if(frien0.isSelected()  ){
                    VALIDATION.setBackground(Color.red) ;
                    VALIDATION.setHorizontalAlignment( VALIDATION.CENTER);
                    VALIDATION.setText(" CHECK  LE  SYSTEME DE SECURITE");
                }else{
                    VALIDATION.setText(null);
                    VALIDATION.setBackground(Color.GREEN) ;
                    validation();
                }

            }else if(type.equals("GROUPE ELECTROGENE DIESEL ")){

                Double a = Double.parseDouble(kmcond.getText());
                Double b = Double.parseDouble(km.getText());
                Integer huille_moteur = 150;//HUILLE MOTEUR
                Integer filtre_aire = 100;//FILTRE A AIRE
                Integer filtre_diesel = 250;//FILTRE DIESEL
                Double c;
                Double d;
                Double dd;
                Double ddd;
                c=a+b;
                d=(c/filtre_aire)*100;
                dd=(c/huille_moteur)*100;
                ddd=(c/filtre_diesel)*100;

                if(c >= filtre_aire){

                    VALIDATION.setBackground(Color.red) ;
                    VALIDATION.setHorizontalAlignment( VALIDATION.CENTER);
                    VALIDATION.setText("CHECKING GENERAL!!! \n FILTRE A AIRE");

                }else if(c >= huille_moteur){

                    VALIDATION.setBackground(Color.red) ;
                    VALIDATION.setHorizontalAlignment( VALIDATION.CENTER);
                    VALIDATION.setText("CHECKING GENERAL!!! \n CHANGER  HUILE MOTEUR");

                }else if(c >= filtre_diesel){

                    VALIDATION.setBackground(Color.red) ;
                    VALIDATION.setHorizontalAlignment( VALIDATION.CENTER);
                    VALIDATION.setText("CHECKING GENERAL!!! \n CHANGER FILTRE A DIESEL");

                }else if(d >=80){
                    VALIDATION.setBackground(Color.yellow) ;
                    VALIDATION.setHorizontalAlignment( VALIDATION.CENTER);
                    VALIDATION.setText("REQUIRE GENERAL CHECKING !!! \n CHANGER FILTRE A AIR!!!");
                    validation();

                }else if(dd >=80){
                    VALIDATION.setBackground(Color.yellow) ;
                    VALIDATION.setHorizontalAlignment( VALIDATION.CENTER);
                    VALIDATION.setText("REQUIRE GENERAL CHECKING !!! \n  VIDAGES D'HUILLE!!!");
                    validation();

                }else if(ddd >=80){
                    VALIDATION.setBackground(Color.yellow) ;
                    VALIDATION.setHorizontalAlignment( VALIDATION.CENTER);
                    VALIDATION.setText("REQUIRE GENERAL CHECKING !!! \n CHANGER FILTRE A DIESEL!!!");
                    validation();

                }else if(huille0.isSelected() ){
                    VALIDATION.setBackground(Color.red) ;
                    VALIDATION.setHorizontalAlignment( VALIDATION.CENTER);
                    VALIDATION.setText(" CHECK  VERIFICATION DES NIVEAUX \n (Huilles et Liquides)");

                }else if( refroidisement0.isSelected() ){
                    VALIDATION.setBackground(Color.red) ;
                    VALIDATION.setHorizontalAlignment( VALIDATION.CENTER);
                    VALIDATION.setText(" CHECK  VERIFICATION DES NIVEAUX \n (Huilles et Liquides)");

                }else if( batterie0.isSelected() ){
                    VALIDATION.setBackground(Color.red) ;
                    VALIDATION.setHorizontalAlignment( VALIDATION.CENTER);
                    VALIDATION.setText(" CHECK  VERIFICATION DES NIVEAUX \n (Huilles et Liquides)");

                }else if(feuxstop0.isSelected()){
                    VALIDATION.setBackground(Color.red) ;
                    VALIDATION.setHorizontalAlignment( VALIDATION.CENTER);
                    VALIDATION.setText(" CHECK  L'ECLAIRAGE");

                }else{
                    VALIDATION.setText(null);
                    VALIDATION.setBackground(Color.GREEN) ;
                    validation();
                }

            }else if(type.equals("GROUPE ELECTROGENE ESSENCE")){

                Double a = Double.parseDouble(kmcond.getText());
                Double b = Double.parseDouble(km.getText());
                Integer e = 100;//HUILLE MOTEUR
                Integer ee = 200;//FILTRE A AIRE
                Double c;
                Double cc;
                Double d;
                Double dd;
                c=a+b;
                d=(c/e)*100;
                dd=(c/ee)*100;

                if(c >= e){

                    VALIDATION.setBackground(Color.red) ;
                    VALIDATION.setHorizontalAlignment( VALIDATION.CENTER);
                    VALIDATION.setText("CHECKING GENERAL!!! \n VIDAGES D'HUILLE ET FILTRE A HUILLE");

                }else if(c >= ee){

                    VALIDATION.setBackground(Color.red) ;
                    VALIDATION.setHorizontalAlignment( VALIDATION.CENTER);
                    VALIDATION.setText("CHECKING GENERAL!!! \n CHANGER FILTRE A AIR");

                }else if(d >=80){
                    VALIDATION.setBackground(Color.yellow) ;
                    VALIDATION.setHorizontalAlignment( VALIDATION.CENTER);
                    VALIDATION.setText("REQUIRE GENERAL CHECKING !!! \n  VIDAGES D'HUILLE!!!");
                    validation();

                }else if(dd >=80){
                    VALIDATION.setBackground(Color.yellow) ;
                    VALIDATION.setHorizontalAlignment( VALIDATION.CENTER);
                    VALIDATION.setText("REQUIRE GENERAL CHECKING !!! \n CHANGER FILTRE A AIR!!!");
                    validation();

                }else if(huille0.isSelected() ){
                    VALIDATION.setBackground(Color.red) ;
                    VALIDATION.setHorizontalAlignment( VALIDATION.CENTER);
                    VALIDATION.setText(" CHECK  VERIFICATION DES NIVEAUX \n (Huilles et Liquides)");

                }else if( refroidisement0.isSelected() ){
                    VALIDATION.setBackground(Color.red) ;
                    VALIDATION.setHorizontalAlignment( VALIDATION.CENTER);
                    VALIDATION.setText(" CHECK  VERIFICATION DES NIVEAUX \n (Huilles et Liquides)");

                }else if( batterie0.isSelected() ){
                    VALIDATION.setBackground(Color.red) ;
                    VALIDATION.setHorizontalAlignment( VALIDATION.CENTER);
                    VALIDATION.setText(" CHECK  VERIFICATION DES NIVEAUX \n (Huilles et Liquides)");

                }else if(feuxstop0.isSelected()){
                    VALIDATION.setBackground(Color.red) ;
                    VALIDATION.setHorizontalAlignment( VALIDATION.CENTER);
                    VALIDATION.setText(" CHECK  L'ECLAIRAGE");

                }else{
                    VALIDATION.setText(null);
                    VALIDATION.setBackground(Color.GREEN) ;
                    validation();
                }

            }else if(type.equals("MOTO")){

                // validation();

                Double a = Double.parseDouble(kmcond.getText());
                Double b = Double.parseDouble(km.getText());
                Integer e = 6000;
                Double c;
                Double d;
                c=a+b;
                d=(c/e)*100;
                if(c >= e){

                    VALIDATION.setBackground(Color.red) ;
                    VALIDATION.setHorizontalAlignment( VALIDATION.CENTER);
                    VALIDATION.setText("CHECKING GENERAL!!! \n VIDAGES D'HUILLE");

                }else if(d >=80){
                    VALIDATION.setBackground(Color.yellow) ;
                    VALIDATION.setHorizontalAlignment( VALIDATION.CENTER);
                    VALIDATION.setText("REQUIRE GENERAL CHECKING !!! \n VIDAGES D'HUILLE!!!");
                    validation();

                }else if(huille0.isSelected() ){
                    VALIDATION.setBackground(Color.red) ;
                    VALIDATION.setHorizontalAlignment( VALIDATION.CENTER);
                    VALIDATION.setText(" CHECK  VERIFICATION DES NIVEAUX \n (Huilles et Liquides)");

                }else if( refroidisement0.isSelected() ){
                    VALIDATION.setBackground(Color.red) ;
                    VALIDATION.setHorizontalAlignment( VALIDATION.CENTER);
                    VALIDATION.setText(" CHECK  VERIFICATION DES NIVEAUX \n (Huilles et Liquides)");

                }else if( batterie0.isSelected() ){
                    VALIDATION.setBackground(Color.red) ;
                    VALIDATION.setHorizontalAlignment( VALIDATION.CENTER);
                    VALIDATION.setText(" CHECK  VERIFICATION DES NIVEAUX \n (Huilles et Liquides)");

                }else if(retroviseurs0.isSelected() ){
                    VALIDATION.setBackground(Color.red) ;
                    VALIDATION.setHorizontalAlignment( VALIDATION.CENTER);
                    VALIDATION.setText(" CHECK  LA  VISIBILITE");

                }else if(feuxdepossition0.isSelected() ){
                    VALIDATION.setBackground(Color.red) ;
                    VALIDATION.setHorizontalAlignment( VALIDATION.CENTER);
                    VALIDATION.setText(" CHECK  L'ECLAIRAGE");

                }else if(feuxstop0.isSelected()){
                    VALIDATION.setBackground(Color.red) ;
                    VALIDATION.setHorizontalAlignment( VALIDATION.CENTER);
                    VALIDATION.setText(" CHECK  L'ECLAIRAGE");

                }else if(clignotant0.isSelected()){
                    VALIDATION.setBackground(Color.red) ;
                    VALIDATION.setHorizontalAlignment( VALIDATION.CENTER);
                    VALIDATION.setText(" CHECK  L'ECLAIRAGE");

                }else if(phare0.isSelected() ){
                    VALIDATION.setBackground(Color.red) ;
                    VALIDATION.setHorizontalAlignment( VALIDATION.CENTER);
                    VALIDATION.setText(" CHECK  L'ECLAIRAGE");

                }else if(claxion0.isSelected()){
                    VALIDATION.setBackground(Color.red) ;
                    VALIDATION.setHorizontalAlignment( VALIDATION.CENTER);
                    VALIDATION.setText(" CHECK  LE  SYSTEME DE SECURITE");
                }else if(frien0.isSelected()  ){
                    VALIDATION.setBackground(Color.red) ;
                    VALIDATION.setHorizontalAlignment( VALIDATION.CENTER);
                    VALIDATION.setText(" CHECK  LE  SYSTEME DE SECURITE");
                }else{
                    VALIDATION.setText(null);
                    VALIDATION.setBackground(Color.GREEN) ;
                    validation();
                }
            }else{}

            call_table();

            JOptionPane.showMessageDialog(null, "PLEASE CHECK THE SIGNALISATION LIGHT \n BY WAITING FOR LOGISTIC APPROUVAL \n PRINT THE GO SLEEP");
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

        jPanel2 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        huille1 = new javax.swing.JRadioButton();
        huille0 = new javax.swing.JRadioButton();
        refroidisement1 = new javax.swing.JRadioButton();
        refroidisement0 = new javax.swing.JRadioButton();
        glase1 = new javax.swing.JRadioButton();
        glase0 = new javax.swing.JRadioButton();
        liquidefreins1 = new javax.swing.JRadioButton();
        liquidefreins0 = new javax.swing.JRadioButton();
        batterie1 = new javax.swing.JRadioButton();
        batterie0 = new javax.swing.JRadioButton();
        huilled = new javax.swing.JTextField();
        refroidisementd = new javax.swing.JTextField();
        glased = new javax.swing.JTextField();
        liquidefreinsd = new javax.swing.JTextField();
        batteried = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        avant1 = new javax.swing.JRadioButton();
        avant0 = new javax.swing.JRadioButton();
        avantd = new javax.swing.JTextField();
        arriere1 = new javax.swing.JRadioButton();
        arriere0 = new javax.swing.JRadioButton();
        arriered = new javax.swing.JTextField();
        cole0 = new javax.swing.JRadioButton();
        cole1 = new javax.swing.JRadioButton();
        coled = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        vitre1 = new javax.swing.JRadioButton();
        vitre0 = new javax.swing.JRadioButton();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        vitred = new javax.swing.JTextField();
        seeuisglased = new javax.swing.JTextField();
        seeuisglase0 = new javax.swing.JRadioButton();
        seeuisglase1 = new javax.swing.JRadioButton();
        retroviseursd = new javax.swing.JTextField();
        retroviseurs0 = new javax.swing.JRadioButton();
        retroviseurs1 = new javax.swing.JRadioButton();
        immatriculationd = new javax.swing.JTextField();
        immatriculation0 = new javax.swing.JRadioButton();
        immatriculation1 = new javax.swing.JRadioButton();
        girophare1 = new javax.swing.JRadioButton();
        girophare0 = new javax.swing.JRadioButton();
        girophared = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        feuxdepossition1 = new javax.swing.JRadioButton();
        feuxdepossition0 = new javax.swing.JRadioButton();
        feuxdepossitiond = new javax.swing.JTextField();
        feuxstopd = new javax.swing.JTextField();
        feuxstop0 = new javax.swing.JRadioButton();
        feuxstop1 = new javax.swing.JRadioButton();
        clignotantd = new javax.swing.JTextField();
        clignotant0 = new javax.swing.JRadioButton();
        clignotant1 = new javax.swing.JRadioButton();
        phared = new javax.swing.JTextField();
        phare0 = new javax.swing.JRadioButton();
        phare1 = new javax.swing.JRadioButton();
        jRadioButton1 = new javax.swing.JRadioButton();
        jPanel10 = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        claxion1 = new javax.swing.JRadioButton();
        claxion0 = new javax.swing.JRadioButton();
        claxiond = new javax.swing.JTextField();
        seintured = new javax.swing.JTextField();
        seinture0 = new javax.swing.JRadioButton();
        seinture1 = new javax.swing.JRadioButton();
        paraprised = new javax.swing.JTextField();
        paraprise0 = new javax.swing.JRadioButton();
        paraprise1 = new javax.swing.JRadioButton();
        friend = new javax.swing.JTextField();
        frien0 = new javax.swing.JRadioButton();
        frien1 = new javax.swing.JRadioButton();
        jLabel38 = new javax.swing.JLabel();
        freinamain1 = new javax.swing.JRadioButton();
        freinamain0 = new javax.swing.JRadioButton();
        jLabel39 = new javax.swing.JLabel();
        pneureserve1 = new javax.swing.JRadioButton();
        pneureserve0 = new javax.swing.JRadioButton();
        freinamaind = new javax.swing.JTextField();
        pneureserved = new javax.swing.JTextField();
        jPanel11 = new javax.swing.JPanel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        interiere1 = new javax.swing.JRadioButton();
        interiere0 = new javax.swing.JRadioButton();
        interiered = new javax.swing.JTextField();
        jLabel42 = new javax.swing.JLabel();
        exteriere1 = new javax.swing.JRadioButton();
        exteriere0 = new javax.swing.JRadioButton();
        exteriered = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        agins = new javax.swing.JComboBox<>();
        conducteur = new javax.swing.JTextField();
        passager = new javax.swing.JTextField();
        depart = new javax.swing.JTextField();
        arrive = new javax.swing.JTextField();
        refil = new javax.swing.JTextField();
        kmcond = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        motif = new javax.swing.JEditorPane();
        km = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jPanel14 = new javax.swing.JPanel();
        VALIDATION = new javax.swing.JTextField();
        jPanel12 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu2 = new javax.swing.JMenu();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();

        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_Control_Panel_16px.png"))); // NOI18N

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setText(" VERIFICATION DES NIVEAUX");
        jLabel12.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel6.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 2, 389, -1));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel13.setText("Huille moteur");
        jPanel6.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 27, -1, -1));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel14.setText("Liquede de refroidissement");
        jPanel6.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 45, -1, -1));

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel15.setText("Liquide lave-glase");
        jPanel6.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 65, -1, -1));

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel16.setText("Liquide de freins");
        jPanel6.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 86, -1, -1));

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel17.setText("Liquide de batterie");
        jPanel6.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 107, -1, -1));

        huille1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        huille1.setText("oui");
        huille1.setEnabled(false);
        jPanel6.add(huille1, new org.netbeans.lib.awtextra.AbsoluteConstraints(184, 27, -1, 14));

        huille0.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        huille0.setText("non");
        huille0.setEnabled(false);
        huille0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                huille0ActionPerformed(evt);
            }
        });
        jPanel6.add(huille0, new org.netbeans.lib.awtextra.AbsoluteConstraints(231, 27, -1, 14));

        refroidisement1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        refroidisement1.setText("oui");
        refroidisement1.setEnabled(false);
        jPanel6.add(refroidisement1, new org.netbeans.lib.awtextra.AbsoluteConstraints(184, 46, -1, 14));

        refroidisement0.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        refroidisement0.setText("non");
        refroidisement0.setEnabled(false);
        refroidisement0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refroidisement0ActionPerformed(evt);
            }
        });
        jPanel6.add(refroidisement0, new org.netbeans.lib.awtextra.AbsoluteConstraints(231, 46, -1, 14));

        glase1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        glase1.setText("oui");
        glase1.setEnabled(false);
        jPanel6.add(glase1, new org.netbeans.lib.awtextra.AbsoluteConstraints(184, 67, -1, 13));

        glase0.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        glase0.setText("non");
        glase0.setEnabled(false);
        glase0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                glase0ActionPerformed(evt);
            }
        });
        jPanel6.add(glase0, new org.netbeans.lib.awtextra.AbsoluteConstraints(231, 66, -1, 14));

        liquidefreins1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        liquidefreins1.setText("oui");
        liquidefreins1.setEnabled(false);
        liquidefreins1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                liquidefreins1ActionPerformed(evt);
            }
        });
        jPanel6.add(liquidefreins1, new org.netbeans.lib.awtextra.AbsoluteConstraints(184, 86, -1, 14));

        liquidefreins0.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        liquidefreins0.setText("non");
        liquidefreins0.setEnabled(false);
        liquidefreins0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                liquidefreins0ActionPerformed(evt);
            }
        });
        jPanel6.add(liquidefreins0, new org.netbeans.lib.awtextra.AbsoluteConstraints(231, 86, -1, 14));

        batterie1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        batterie1.setText("oui");
        batterie1.setEnabled(false);
        jPanel6.add(batterie1, new org.netbeans.lib.awtextra.AbsoluteConstraints(184, 106, -1, 14));

        batterie0.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        batterie0.setText("non");
        batterie0.setEnabled(false);
        batterie0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                batterie0ActionPerformed(evt);
            }
        });
        jPanel6.add(batterie0, new org.netbeans.lib.awtextra.AbsoluteConstraints(231, 105, -1, 17));

        huilled.setEditable(false);
        huilled.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        huilled.setBorder(null);
        jPanel6.add(huilled, new org.netbeans.lib.awtextra.AbsoluteConstraints(286, 27, 95, -1));

        refroidisementd.setEditable(false);
        refroidisementd.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        refroidisementd.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel6.add(refroidisementd, new org.netbeans.lib.awtextra.AbsoluteConstraints(286, 45, 95, -1));

        glased.setEditable(false);
        glased.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        glased.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel6.add(glased, new org.netbeans.lib.awtextra.AbsoluteConstraints(286, 65, 95, -1));

        liquidefreinsd.setEditable(false);
        liquidefreinsd.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        liquidefreinsd.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel6.add(liquidefreinsd, new org.netbeans.lib.awtextra.AbsoluteConstraints(286, 85, 95, -1));

        batteried.setEditable(false);
        batteried.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        batteried.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel6.add(batteried, new org.netbeans.lib.awtextra.AbsoluteConstraints(286, 105, 95, -1));

        jPanel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel18.setText(" PNEUMATIQUES");
        jLabel18.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel7.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 2, 389, -1));

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel19.setText("Pression avant");
        jPanel7.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 27, -1, -1));

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel20.setText("Pression arriere");
        jPanel7.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 47, -1, -1));

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel21.setText("Cole,etat");
        jPanel7.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 67, -1, -1));

        avant1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        avant1.setText("oui");
        avant1.setEnabled(false);
        jPanel7.add(avant1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 28, -1, 15));

        avant0.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        avant0.setText("non");
        avant0.setEnabled(false);
        avant0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                avant0ActionPerformed(evt);
            }
        });
        jPanel7.add(avant0, new org.netbeans.lib.awtextra.AbsoluteConstraints(237, 28, -1, 15));

        avantd.setEditable(false);
        avantd.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        avantd.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel7.add(avantd, new org.netbeans.lib.awtextra.AbsoluteConstraints(288, 27, 93, -1));

        arriere1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        arriere1.setText("oui");
        arriere1.setEnabled(false);
        jPanel7.add(arriere1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 51, -1, 15));

        arriere0.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        arriere0.setText("non");
        arriere0.setEnabled(false);
        arriere0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                arriere0ActionPerformed(evt);
            }
        });
        jPanel7.add(arriere0, new org.netbeans.lib.awtextra.AbsoluteConstraints(237, 51, -1, 15));

        arriered.setEditable(false);
        arriered.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        arriered.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel7.add(arriered, new org.netbeans.lib.awtextra.AbsoluteConstraints(288, 50, 93, -1));

        cole0.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        cole0.setText("non");
        cole0.setEnabled(false);
        cole0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cole0ActionPerformed(evt);
            }
        });
        jPanel7.add(cole0, new org.netbeans.lib.awtextra.AbsoluteConstraints(237, 74, -1, 15));

        cole1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        cole1.setText("oui");
        cole1.setEnabled(false);
        jPanel7.add(cole1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 74, -1, 15));

        coled.setEditable(false);
        coled.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        coled.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel7.add(coled, new org.netbeans.lib.awtextra.AbsoluteConstraints(288, 73, 93, -1));

        jPanel8.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel22.setText(" VISIBILITE");
        jLabel22.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel8.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 2, 389, -1));

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel23.setText("Vitres");
        jPanel8.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 27, -1, -1));

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel24.setText("Balais d'essuis-glaces");
        jPanel8.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 47, -1, -1));

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel25.setText("Retroviseurs");
        jPanel8.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 67, -1, -1));

        vitre1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        vitre1.setText("oui");
        vitre1.setEnabled(false);
        jPanel8.add(vitre1, new org.netbeans.lib.awtextra.AbsoluteConstraints(192, 28, -1, 15));

        vitre0.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        vitre0.setText("non");
        vitre0.setEnabled(false);
        vitre0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vitre0ActionPerformed(evt);
            }
        });
        jPanel8.add(vitre0, new org.netbeans.lib.awtextra.AbsoluteConstraints(239, 28, -1, 14));

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel26.setText("Plaque d'immatriculation");
        jPanel8.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 87, -1, -1));

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel27.setText("Girophare et Drapelle");
        jPanel8.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 107, -1, -1));

        vitred.setEditable(false);
        vitred.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        vitred.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel8.add(vitred, new org.netbeans.lib.awtextra.AbsoluteConstraints(288, 27, 93, -1));

        seeuisglased.setEditable(false);
        seeuisglased.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        seeuisglased.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel8.add(seeuisglased, new org.netbeans.lib.awtextra.AbsoluteConstraints(288, 47, 93, -1));

        seeuisglase0.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        seeuisglase0.setText("non");
        seeuisglase0.setEnabled(false);
        seeuisglase0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seeuisglase0ActionPerformed(evt);
            }
        });
        jPanel8.add(seeuisglase0, new org.netbeans.lib.awtextra.AbsoluteConstraints(239, 48, -1, 14));

        seeuisglase1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        seeuisglase1.setText("oui");
        seeuisglase1.setEnabled(false);
        jPanel8.add(seeuisglase1, new org.netbeans.lib.awtextra.AbsoluteConstraints(192, 48, -1, 15));

        retroviseursd.setEditable(false);
        retroviseursd.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        retroviseursd.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel8.add(retroviseursd, new org.netbeans.lib.awtextra.AbsoluteConstraints(288, 67, 93, -1));

        retroviseurs0.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        retroviseurs0.setText("non");
        retroviseurs0.setEnabled(false);
        retroviseurs0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                retroviseurs0ActionPerformed(evt);
            }
        });
        jPanel8.add(retroviseurs0, new org.netbeans.lib.awtextra.AbsoluteConstraints(239, 68, -1, 14));

        retroviseurs1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        retroviseurs1.setText("oui");
        retroviseurs1.setEnabled(false);
        jPanel8.add(retroviseurs1, new org.netbeans.lib.awtextra.AbsoluteConstraints(192, 68, -1, 15));

        immatriculationd.setEditable(false);
        immatriculationd.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        immatriculationd.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel8.add(immatriculationd, new org.netbeans.lib.awtextra.AbsoluteConstraints(288, 87, 93, -1));

        immatriculation0.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        immatriculation0.setText("non");
        immatriculation0.setEnabled(false);
        immatriculation0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                immatriculation0ActionPerformed(evt);
            }
        });
        jPanel8.add(immatriculation0, new org.netbeans.lib.awtextra.AbsoluteConstraints(239, 88, -1, 14));

        immatriculation1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        immatriculation1.setText("oui");
        immatriculation1.setEnabled(false);
        jPanel8.add(immatriculation1, new org.netbeans.lib.awtextra.AbsoluteConstraints(192, 88, -1, 15));

        girophare1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        girophare1.setText("oui");
        girophare1.setEnabled(false);
        jPanel8.add(girophare1, new org.netbeans.lib.awtextra.AbsoluteConstraints(192, 111, -1, 15));

        girophare0.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        girophare0.setText("non");
        girophare0.setEnabled(false);
        girophare0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                girophare0ActionPerformed(evt);
            }
        });
        jPanel8.add(girophare0, new org.netbeans.lib.awtextra.AbsoluteConstraints(239, 111, -1, 14));

        girophared.setEditable(false);
        girophared.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        girophared.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel8.add(girophared, new org.netbeans.lib.awtextra.AbsoluteConstraints(288, 110, 93, -1));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel9.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel28.setText(" ECLAIRAGE");
        jLabel28.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel9.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 2, 366, -1));

        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel29.setText("Feux de position");
        jPanel9.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 28, -1, -1));

        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel30.setText("Feux de stop");
        jPanel9.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 47, -1, -1));

        jLabel31.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel31.setText("Clignotants");
        jPanel9.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 67, -1, -1));

        jLabel32.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel32.setText("Phares");
        jPanel9.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 87, -1, -1));

        feuxdepossition1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        feuxdepossition1.setText("oui");
        feuxdepossition1.setEnabled(false);
        feuxdepossition1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                feuxdepossition1ActionPerformed(evt);
            }
        });
        jPanel9.add(feuxdepossition1, new org.netbeans.lib.awtextra.AbsoluteConstraints(113, 28, -1, 14));

        feuxdepossition0.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        feuxdepossition0.setText("non");
        feuxdepossition0.setEnabled(false);
        jPanel9.add(feuxdepossition0, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 28, -1, 14));

        feuxdepossitiond.setEditable(false);
        feuxdepossitiond.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        feuxdepossitiond.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel9.add(feuxdepossitiond, new org.netbeans.lib.awtextra.AbsoluteConstraints(211, 27, 147, -1));

        feuxstopd.setEditable(false);
        feuxstopd.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        feuxstopd.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel9.add(feuxstopd, new org.netbeans.lib.awtextra.AbsoluteConstraints(211, 47, 147, -1));

        feuxstop0.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        feuxstop0.setText("non");
        feuxstop0.setEnabled(false);
        jPanel9.add(feuxstop0, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 48, -1, 14));

        feuxstop1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        feuxstop1.setText("oui");
        feuxstop1.setEnabled(false);
        feuxstop1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                feuxstop1ActionPerformed(evt);
            }
        });
        jPanel9.add(feuxstop1, new org.netbeans.lib.awtextra.AbsoluteConstraints(113, 48, -1, 14));

        clignotantd.setEditable(false);
        clignotantd.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        clignotantd.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel9.add(clignotantd, new org.netbeans.lib.awtextra.AbsoluteConstraints(211, 67, 147, -1));

        clignotant0.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        clignotant0.setText("non");
        clignotant0.setEnabled(false);
        jPanel9.add(clignotant0, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 68, -1, 14));

        clignotant1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        clignotant1.setText("oui");
        clignotant1.setEnabled(false);
        clignotant1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clignotant1ActionPerformed(evt);
            }
        });
        jPanel9.add(clignotant1, new org.netbeans.lib.awtextra.AbsoluteConstraints(113, 68, -1, 14));

        phared.setEditable(false);
        phared.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        phared.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel9.add(phared, new org.netbeans.lib.awtextra.AbsoluteConstraints(211, 87, 147, -1));

        phare0.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        phare0.setText("non");
        phare0.setEnabled(false);
        jPanel9.add(phare0, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 88, -1, 14));

        phare1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        phare1.setText("oui");
        phare1.setEnabled(false);
        phare1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                phare1ActionPerformed(evt);
            }
        });
        jPanel9.add(phare1, new org.netbeans.lib.awtextra.AbsoluteConstraints(113, 88, -1, 14));
        jPanel9.add(jRadioButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(195, 109, 11, 1));

        jPanel10.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel33.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel33.setText(" SYSTEME DE SECURITE");
        jLabel33.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel10.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 2, 366, -1));

        jLabel34.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel34.setText("CLAXION");
        jPanel10.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 28, -1, -1));

        jLabel35.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel35.setText("SEINTURE D SEC.");
        jPanel10.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 48, -1, -1));

        jLabel36.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel36.setText("PARAPRISE");
        jPanel10.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 68, -1, -1));

        jLabel37.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel37.setText("FREIN");
        jPanel10.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 88, -1, -1));

        claxion1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        claxion1.setText("oui");
        claxion1.setEnabled(false);
        claxion1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                claxion1ActionPerformed(evt);
            }
        });
        jPanel10.add(claxion1, new org.netbeans.lib.awtextra.AbsoluteConstraints(115, 28, -1, 14));

        claxion0.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        claxion0.setText("non");
        claxion0.setEnabled(false);
        jPanel10.add(claxion0, new org.netbeans.lib.awtextra.AbsoluteConstraints(162, 28, -1, 14));

        claxiond.setEditable(false);
        claxiond.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        claxiond.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel10.add(claxiond, new org.netbeans.lib.awtextra.AbsoluteConstraints(213, 27, 145, -1));

        seintured.setEditable(false);
        seintured.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        seintured.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel10.add(seintured, new org.netbeans.lib.awtextra.AbsoluteConstraints(213, 47, 145, -1));

        seinture0.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        seinture0.setText("non");
        seinture0.setEnabled(false);
        jPanel10.add(seinture0, new org.netbeans.lib.awtextra.AbsoluteConstraints(162, 48, -1, 14));

        seinture1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        seinture1.setText("oui");
        seinture1.setEnabled(false);
        seinture1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seinture1ActionPerformed(evt);
            }
        });
        jPanel10.add(seinture1, new org.netbeans.lib.awtextra.AbsoluteConstraints(115, 48, -1, 14));

        paraprised.setEditable(false);
        paraprised.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        paraprised.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel10.add(paraprised, new org.netbeans.lib.awtextra.AbsoluteConstraints(213, 67, 145, -1));

        paraprise0.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        paraprise0.setText("non");
        paraprise0.setEnabled(false);
        jPanel10.add(paraprise0, new org.netbeans.lib.awtextra.AbsoluteConstraints(162, 68, -1, 14));

        paraprise1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        paraprise1.setText("oui");
        paraprise1.setEnabled(false);
        paraprise1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                paraprise1ActionPerformed(evt);
            }
        });
        jPanel10.add(paraprise1, new org.netbeans.lib.awtextra.AbsoluteConstraints(115, 68, -1, 14));

        friend.setEditable(false);
        friend.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        friend.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel10.add(friend, new org.netbeans.lib.awtextra.AbsoluteConstraints(213, 87, 145, -1));

        frien0.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        frien0.setText("non");
        frien0.setEnabled(false);
        jPanel10.add(frien0, new org.netbeans.lib.awtextra.AbsoluteConstraints(162, 88, -1, 14));

        frien1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        frien1.setText("oui");
        frien1.setEnabled(false);
        frien1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                frien1ActionPerformed(evt);
            }
        });
        jPanel10.add(frien1, new org.netbeans.lib.awtextra.AbsoluteConstraints(115, 128, -1, 14));

        jLabel38.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel38.setText("FREIN A MAIN");
        jPanel10.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 108, -1, -1));

        freinamain1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        freinamain1.setText("oui");
        freinamain1.setEnabled(false);
        freinamain1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                freinamain1ActionPerformed(evt);
            }
        });
        jPanel10.add(freinamain1, new org.netbeans.lib.awtextra.AbsoluteConstraints(115, 108, -1, 14));

        freinamain0.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        freinamain0.setText("non");
        freinamain0.setEnabled(false);
        jPanel10.add(freinamain0, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 108, -1, 14));

        jLabel39.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel39.setText("PNEU RESERVE");
        jPanel10.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 128, -1, -1));

        pneureserve1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        pneureserve1.setText("oui");
        pneureserve1.setEnabled(false);
        pneureserve1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pneureserve1ActionPerformed(evt);
            }
        });
        jPanel10.add(pneureserve1, new org.netbeans.lib.awtextra.AbsoluteConstraints(115, 88, -1, 14));

        pneureserve0.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        pneureserve0.setText("non");
        pneureserve0.setEnabled(false);
        jPanel10.add(pneureserve0, new org.netbeans.lib.awtextra.AbsoluteConstraints(162, 128, -1, 14));

        freinamaind.setEditable(false);
        freinamaind.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        freinamaind.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel10.add(freinamaind, new org.netbeans.lib.awtextra.AbsoluteConstraints(213, 107, 145, -1));

        pneureserved.setEditable(false);
        pneureserved.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        pneureserved.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel10.add(pneureserved, new org.netbeans.lib.awtextra.AbsoluteConstraints(213, 127, 145, -1));

        jPanel11.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel40.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel40.setText(" PROPIERETE");
        jLabel40.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel11.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 2, 366, -1));

        jLabel41.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel41.setText("Interiere");
        jPanel11.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 28, -1, -1));

        interiere1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        interiere1.setText("oui");
        interiere1.setEnabled(false);
        interiere1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                interiere1ActionPerformed(evt);
            }
        });
        jPanel11.add(interiere1, new org.netbeans.lib.awtextra.AbsoluteConstraints(116, 24, -1, -1));

        interiere0.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        interiere0.setText("non");
        interiere0.setEnabled(false);
        interiere0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                interiere0ActionPerformed(evt);
            }
        });
        jPanel11.add(interiere0, new org.netbeans.lib.awtextra.AbsoluteConstraints(167, 24, -1, -1));

        interiered.setEditable(false);
        interiered.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        interiered.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel11.add(interiered, new org.netbeans.lib.awtextra.AbsoluteConstraints(222, 27, 136, -1));

        jLabel42.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel42.setText("Exteriere");
        jPanel11.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 51, -1, -1));

        exteriere1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        exteriere1.setText("oui");
        exteriere1.setEnabled(false);
        exteriere1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exteriere1ActionPerformed(evt);
            }
        });
        jPanel11.add(exteriere1, new org.netbeans.lib.awtextra.AbsoluteConstraints(116, 47, -1, -1));

        exteriere0.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        exteriere0.setText("non");
        exteriere0.setEnabled(false);
        exteriere0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exteriere0ActionPerformed(evt);
            }
        });
        jPanel11.add(exteriere0, new org.netbeans.lib.awtextra.AbsoluteConstraints(168, 47, -1, -1));

        exteriered.setEditable(false);
        exteriered.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        exteriered.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel11.add(exteriered, new org.netbeans.lib.awtextra.AbsoluteConstraints(223, 50, 135, -1));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        agins.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        agins.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), "Select One Agin", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        agins.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                aginsPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        agins.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aginsActionPerformed(evt);
            }
        });

        conducteur.setEditable(false);
        conducteur.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        conducteur.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), "Names", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        passager.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        passager.setText("0");
        passager.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), "Nbr. Pers.", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        depart.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        depart.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), "Depart", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        depart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                departActionPerformed(evt);
            }
        });

        arrive.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        arrive.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), "Arrived", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        refil.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        refil.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), "Refilling", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        kmcond.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        kmcond.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), "EstImated KM", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        kmcond.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                kmcondKeyReleased(evt);
            }
        });

        motif.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), "Purpose", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        motif.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jScrollPane1.setViewportView(motif);

        km.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        km.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), "KMS", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        jButton5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton5.setText("Roll Nbr");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(agins, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(depart, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(arrive))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(kmcond)
                            .addComponent(passager, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(refil)
                            .addComponent(km)))
                    .addComponent(conducteur)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(conducteur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(agins, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(depart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(arrive, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(passager, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(refil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(kmcond, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(km, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel14.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        VALIDATION.setEditable(false);
        VALIDATION.setBackground(new java.awt.Color(204, 204, 204));
        VALIDATION.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        VALIDATION.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(VALIDATION, javax.swing.GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(VALIDATION)
                .addContainerGap())
        );

        jPanel12.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jLabel1.setText("Utineraire");

        jLabel2.setText("jLabel2");

        jLabel4.setText("jLabel4");

        jLabel5.setText("jLabel5");

        jLabel6.setText("jLabel6");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addGap(95, 95, 95)
                .addComponent(jLabel4)
                .addGap(74, 74, 74)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel6)
                    .addComponent(jLabel2))
                .addContainerGap(53, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel13.setBorder(javax.swing.BorderFactory.createEtchedBorder());

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
        jScrollPane2.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 391, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        jMenu2.setText("X");
        jMenu2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu2MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu2);

        jMenu1.setText("File");

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem2.setText("Send Request");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setText("Print");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel14, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(1, 1, 1))
        );

        setBounds(50, 0, 1239, 615);
    }// </editor-fold>//GEN-END:initComponents

    private void huille0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_huille0ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_huille0ActionPerformed

    private void refroidisement0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refroidisement0ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_refroidisement0ActionPerformed

    private void glase0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_glase0ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_glase0ActionPerformed

    private void liquidefreins1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_liquidefreins1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_liquidefreins1ActionPerformed

    private void liquidefreins0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_liquidefreins0ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_liquidefreins0ActionPerformed

    private void batterie0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_batterie0ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_batterie0ActionPerformed

    private void avant0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_avant0ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_avant0ActionPerformed

    private void arriere0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_arriere0ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_arriere0ActionPerformed

    private void cole0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cole0ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cole0ActionPerformed

    private void vitre0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vitre0ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_vitre0ActionPerformed

    private void seeuisglase0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seeuisglase0ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_seeuisglase0ActionPerformed

    private void retroviseurs0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_retroviseurs0ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_retroviseurs0ActionPerformed

    private void immatriculation0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_immatriculation0ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_immatriculation0ActionPerformed

    private void girophare0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_girophare0ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_girophare0ActionPerformed

    private void feuxdepossition1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_feuxdepossition1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_feuxdepossition1ActionPerformed

    private void feuxstop1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_feuxstop1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_feuxstop1ActionPerformed

    private void clignotant1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clignotant1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_clignotant1ActionPerformed

    private void phare1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_phare1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_phare1ActionPerformed

    private void claxion1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_claxion1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_claxion1ActionPerformed

    private void seinture1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seinture1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_seinture1ActionPerformed

    private void paraprise1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_paraprise1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_paraprise1ActionPerformed

    private void frien1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_frien1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_frien1ActionPerformed

    private void freinamain1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_freinamain1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_freinamain1ActionPerformed

    private void pneureserve1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pneureserve1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pneureserve1ActionPerformed

    private void interiere1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_interiere1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_interiere1ActionPerformed

    private void interiere0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_interiere0ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_interiere0ActionPerformed

    private void exteriere1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exteriere1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_exteriere1ActionPerformed

    private void exteriere0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exteriere0ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_exteriere0ActionPerformed

    private void aginsPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_aginsPopupMenuWillBecomeInvisible
 refresh();
       select_combo3();      // TODO add your handling code here:
    }//GEN-LAST:event_aginsPopupMenuWillBecomeInvisible

    private void aginsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aginsActionPerformed
      
        // km_gr.setText("0");// TODO add your handling code here:
    }//GEN-LAST:event_aginsActionPerformed

    private void departActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_departActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_departActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
     //   select_jTable();

       Double a = Double.parseDouble(kmcond.getText());
            Double b = Double.parseDouble(km.getText());
            Integer e = 10000;
            Double c;
            Double d;
            c=a+b;
            d=c*e/100;
        if(c >= e){

            VALIDATION.setBackground(Color.red) ;
            VALIDATION.setHorizontalAlignment( VALIDATION.CENTER);
            VALIDATION.setText("CARAGE !!! "+
                " VIDAGES D'HUILLE!!!");

        }else if(d >=80){
            VALIDATION.setBackground(Color.yellow) ;
            VALIDATION.setHorizontalAlignment( VALIDATION.CENTER);
            VALIDATION.setText("REQUIRE GENERAL CHECKING !!! "+
                " && VIDAGES D'HUILLE!!!");
        }else if(huille0.isSelected() ){
            VALIDATION.setBackground(Color.red) ;
            VALIDATION.setHorizontalAlignment( VALIDATION.CENTER);
            VALIDATION.setText(" CHECK  VERIFICATION DES NIVEAUX(Huilles et Liquides)");

        }else if( refroidisement0.isSelected() ){
            VALIDATION.setBackground(Color.red) ;
            VALIDATION.setHorizontalAlignment( VALIDATION.CENTER);
            VALIDATION.setText(" CHECK  VERIFICATION DES NIVEAUX(Huilles et Liquides)");

        }else if(glase0.isSelected() ){
            VALIDATION.setBackground(Color.red) ;
            VALIDATION.setHorizontalAlignment( VALIDATION.CENTER);
            VALIDATION.setText(" CHECK  VERIFICATION DES NIVEAUX(Huilles et Liquides)");

        }else if( liquidefreins0.isSelected() ){
            VALIDATION.setBackground(Color.red) ;
            VALIDATION.setHorizontalAlignment( VALIDATION.CENTER);
            VALIDATION.setText(" CHECK  VERIFICATION DES NIVEAUX(Huilles et Liquides)");

        }else if( batterie0.isSelected() ){
            VALIDATION.setBackground(Color.red) ;
            VALIDATION.setHorizontalAlignment( VALIDATION.CENTER);
            VALIDATION.setText(" CHECK  VERIFICATION DES NIVEAUX(Huilles et Liquides)");

        }else if(retroviseurs0.isSelected() ){
            VALIDATION.setBackground(Color.red) ;
            VALIDATION.setHorizontalAlignment( VALIDATION.CENTER);
            VALIDATION.setText(" CHECK  LA  VISIBILITE");

        }else if(girophare0.isSelected()){
            VALIDATION.setBackground(Color.red) ;
            VALIDATION.setHorizontalAlignment( VALIDATION.CENTER);
            VALIDATION.setText(" CHECK  LA  VISIBILITE");

        }else if(vitre0.isSelected() ){
            VALIDATION.setBackground(Color.red) ;
            VALIDATION.setHorizontalAlignment( VALIDATION.CENTER);
            VALIDATION.setText(" CHECK  LA  VISIBILITE");

        }else if(feuxdepossition0.isSelected() ){
            VALIDATION.setBackground(Color.red) ;
            VALIDATION.setHorizontalAlignment( VALIDATION.CENTER);
            VALIDATION.setText(" CHECK  L'ECLAIRAGE");

        }else if(feuxstop0.isSelected()){
            VALIDATION.setBackground(Color.red) ;
            VALIDATION.setHorizontalAlignment( VALIDATION.CENTER);
            VALIDATION.setText(" CHECK  L'ECLAIRAGE");

        }else if(clignotant0.isSelected()){
            VALIDATION.setBackground(Color.red) ;
            VALIDATION.setHorizontalAlignment( VALIDATION.CENTER);
            VALIDATION.setText(" CHECK  L'ECLAIRAGE");

        }else if(phare0.isSelected() ){
            VALIDATION.setBackground(Color.red) ;
            VALIDATION.setHorizontalAlignment( VALIDATION.CENTER);
            VALIDATION.setText(" CHECK  L'ECLAIRAGE");

        }else if(claxion0.isSelected()){
            VALIDATION.setBackground(Color.red) ;
            VALIDATION.setHorizontalAlignment( VALIDATION.CENTER);
            VALIDATION.setText(" CHECK  LE  SYSTEME DE SECURITE");
        }else if(seinture0.isSelected()){
            VALIDATION.setBackground(Color.red) ;
            VALIDATION.setHorizontalAlignment( VALIDATION.CENTER);
            VALIDATION.setText(" CHECK  LE  SYSTEME DE SECURITE");
        }else if( paraprise0.isSelected()){
            VALIDATION.setBackground(Color.red) ;
            VALIDATION.setHorizontalAlignment( VALIDATION.CENTER);
            VALIDATION.setText(" CHECK  LE  SYSTEME DE SECURITE");
        }else if(frien0.isSelected()  ){
            VALIDATION.setBackground(Color.red) ;
            VALIDATION.setHorizontalAlignment( VALIDATION.CENTER);
            VALIDATION.setText(" CHECK  LE  SYSTEME DE SECURITE");
        }else{
            VALIDATION.setText(null);
            VALIDATION.setBackground(Color.GREEN) ;
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_jTable1MouseClicked

    private void jMenu2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu2MouseClicked
this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu2MouseClicked

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
attCall_roll();
call_table();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
validation_();        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void kmcondKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kmcondKeyReleased
int a= Integer.parseInt(kmcond.getText())  ;   
int b= Integer.parseInt(km.getText())  ;
int c;
c=a+b;
km.setText(""+c);
// TODO add your handling code here:
    }//GEN-LAST:event_kmcondKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField VALIDATION;
    private javax.swing.JComboBox<String> agins;
    public static javax.swing.JRadioButton arriere0;
    public static javax.swing.JRadioButton arriere1;
    public static javax.swing.JTextField arriered;
    private javax.swing.JTextField arrive;
    public static javax.swing.JRadioButton avant0;
    public static javax.swing.JRadioButton avant1;
    public static javax.swing.JTextField avantd;
    public static javax.swing.JRadioButton batterie0;
    public static javax.swing.JRadioButton batterie1;
    public static javax.swing.JTextField batteried;
    public static javax.swing.JRadioButton claxion0;
    public static javax.swing.JRadioButton claxion1;
    public static javax.swing.JTextField claxiond;
    public static javax.swing.JRadioButton clignotant0;
    public static javax.swing.JRadioButton clignotant1;
    public static javax.swing.JTextField clignotantd;
    public static javax.swing.JRadioButton cole0;
    public static javax.swing.JRadioButton cole1;
    public static javax.swing.JTextField coled;
    private javax.swing.JTextField conducteur;
    private javax.swing.JTextField depart;
    public static javax.swing.JRadioButton exteriere0;
    public static javax.swing.JRadioButton exteriere1;
    public static javax.swing.JTextField exteriered;
    public static javax.swing.JRadioButton feuxdepossition0;
    public static javax.swing.JRadioButton feuxdepossition1;
    public static javax.swing.JTextField feuxdepossitiond;
    public static javax.swing.JRadioButton feuxstop0;
    public static javax.swing.JRadioButton feuxstop1;
    public static javax.swing.JTextField feuxstopd;
    public static javax.swing.JRadioButton freinamain0;
    public static javax.swing.JRadioButton freinamain1;
    public static javax.swing.JTextField freinamaind;
    public static javax.swing.JRadioButton frien0;
    public static javax.swing.JRadioButton frien1;
    public static javax.swing.JTextField friend;
    public static javax.swing.JRadioButton girophare0;
    public static javax.swing.JRadioButton girophare1;
    public static javax.swing.JTextField girophared;
    public static javax.swing.JRadioButton glase0;
    public static javax.swing.JRadioButton glase1;
    public static javax.swing.JTextField glased;
    public static javax.swing.JRadioButton huille0;
    public static javax.swing.JRadioButton huille1;
    public static javax.swing.JTextField huilled;
    public static javax.swing.JRadioButton immatriculation0;
    public static javax.swing.JRadioButton immatriculation1;
    public static javax.swing.JTextField immatriculationd;
    public static javax.swing.JRadioButton interiere0;
    public static javax.swing.JRadioButton interiere1;
    public static javax.swing.JTextField interiered;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
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
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField km;
    private javax.swing.JTextField kmcond;
    public static javax.swing.JRadioButton liquidefreins0;
    public static javax.swing.JRadioButton liquidefreins1;
    public static javax.swing.JTextField liquidefreinsd;
    private javax.swing.JEditorPane motif;
    public static javax.swing.JRadioButton paraprise0;
    public static javax.swing.JRadioButton paraprise1;
    public static javax.swing.JTextField paraprised;
    private javax.swing.JTextField passager;
    public static javax.swing.JRadioButton phare0;
    public static javax.swing.JRadioButton phare1;
    public static javax.swing.JTextField phared;
    public static javax.swing.JRadioButton pneureserve0;
    public static javax.swing.JRadioButton pneureserve1;
    public static javax.swing.JTextField pneureserved;
    private javax.swing.JTextField refil;
    public static javax.swing.JRadioButton refroidisement0;
    public static javax.swing.JRadioButton refroidisement1;
    public static javax.swing.JTextField refroidisementd;
    public static javax.swing.JRadioButton retroviseurs0;
    public static javax.swing.JRadioButton retroviseurs1;
    public static javax.swing.JTextField retroviseursd;
    public static javax.swing.JRadioButton seeuisglase0;
    public static javax.swing.JRadioButton seeuisglase1;
    public static javax.swing.JTextField seeuisglased;
    public static javax.swing.JRadioButton seinture0;
    public static javax.swing.JRadioButton seinture1;
    public static javax.swing.JTextField seintured;
    public static javax.swing.JRadioButton vitre0;
    public static javax.swing.JRadioButton vitre1;
    public static javax.swing.JTextField vitred;
    // End of variables declaration//GEN-END:variables
}
