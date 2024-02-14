/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intreprisemanagementsystem;

import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.event.PopupMenuEvent;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Dosh
 */
public class tableudebor extends javax.swing.JInternalFrame {

    DefaultTableModel mode;
Connection con=null;
PreparedStatement pst=null;
ResultSet rs= null;
String Rolls;
    public tableudebor() {
        initComponents();
              con=JavaDbConnect.dbConnect();
         this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI bui= (BasicInternalFrameUI) this.getUI();
       bui.setNorthPane(null);
          Call_ID_TO_BOMBOBOX();
        Groupe1();
     
        jDateChooser3.setDate(new Date());
       
       // 
      
    }
    
    
    public void refiling_and_km(){
    
     try{
            String sql="select max(KM),SUM(refil) from  driver_info where  AGIN='"+jComboBox1.getSelectedItem()+"'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
               
                
                   String sum5=rs.getString("max(KM)");
               kms.setText(sum5);
                 
               //  String sum6=rs.getString("SUM(REFIL)");
               //  carb.setText(sum6);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
    if(kms.getText().equals("")){
     try{
            String sql="select max(KM) from   agin where  DESCR='"+jComboBox1.getSelectedItem()+"'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
               
                
                   String sum5=rs.getString("max(KM)");
               kms.setText(sum5);
                 
               //  String sum6=rs.getString("SUM(REFIL)");
               //  carb.setText(sum6);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
    }
    
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
      
      

    public void Groupe1(){
ButtonGroup bg1 = new ButtonGroup();
bg1.add(huille1);
bg1.add(huille0);
ButtonGroup bg2 = new ButtonGroup();
bg2.add(refroidisement1);
bg2.add(refroidisement0);

ButtonGroup bg3 = new ButtonGroup();
bg3.add(glase1);
bg3.add(glase0);

ButtonGroup bg4 = new ButtonGroup();
bg4.add(liquidefreins1);
bg4.add(liquidefreins0);//batterie0


ButtonGroup bg5 = new ButtonGroup();
bg5.add(batterie1);
bg5.add(batterie0);//batterie0

ButtonGroup bg6 = new ButtonGroup();
bg6.add(avant1);
bg6.add(avant0);//batterie0

ButtonGroup bg7 = new ButtonGroup();
bg7.add(arriere0);
bg7.add(arriere1);//batterie0

ButtonGroup bg8 = new ButtonGroup();
bg8.add(cole0);
bg8.add(cole1);//batterie0

ButtonGroup bg9 = new ButtonGroup();
bg9.add(vitre1);
bg9.add(vitre0);//batterie0

ButtonGroup bg10 = new ButtonGroup();
bg10.add(seeuisglase1);
bg10.add(seeuisglase0);

ButtonGroup bg11 = new ButtonGroup();
bg11.add(retroviseurs1);
bg11.add(retroviseurs0);

ButtonGroup bg12 = new ButtonGroup();
bg12.add(immatriculation1);
bg12.add(immatriculation0);

ButtonGroup bg13 = new ButtonGroup();
bg13.add(girophare0);
bg13.add(girophare1);

ButtonGroup bg14 = new ButtonGroup();
bg14.add(interiere0);
bg14.add(interiere1);

ButtonGroup bg15 = new ButtonGroup();
bg15.add(exteriere0);
bg15.add(exteriere1);

ButtonGroup bg16 = new ButtonGroup();
bg16.add(feuxdepossition1);
bg16.add(feuxdepossition0);

ButtonGroup bg17 = new ButtonGroup();
bg17.add(feuxstop0);
bg17.add(feuxstop1);

ButtonGroup bg18 = new ButtonGroup();
bg18.add(clignotant0);
bg18.add(clignotant1);

ButtonGroup bg19 = new ButtonGroup();
bg19.add(phare0);
bg19.add(phare1);

ButtonGroup bg20 = new ButtonGroup();
bg20.add(claxion0);
bg20.add(claxion1);

ButtonGroup bg21 = new ButtonGroup();
bg21.add(seinture0);
bg21.add(seinture1);

ButtonGroup bg22 = new ButtonGroup();
bg22.add(paraprise0);
bg22.add(paraprise1);

ButtonGroup bg23 = new ButtonGroup();
bg23.add(frien0);
bg23.add(frien1);

ButtonGroup bg24 = new ButtonGroup();
bg24.add(freinamain1);
bg24.add(freinamain0);

ButtonGroup bg25 = new ButtonGroup();
bg25.add(pneureserve0);
bg25.add(pneureserve1);
}
     public void Groupe2(){
      ButtonGroup bg1 = new ButtonGroup();
      jRadioButton1.setSelected(true);
      bg1.add(jRadioButton1);
bg1.add(huille1);
bg1.add(huille0);

//ButtonGroup bg2 = new ButtonGroup();
bg1.add(refroidisement1);
bg1.add(refroidisement0);

ButtonGroup bg3 = new ButtonGroup();
bg1.add(glase1);
bg1.add(glase0);

ButtonGroup bg4 = new ButtonGroup();
bg1.add(liquidefreins1);
bg1.add(liquidefreins0);//batterie0


ButtonGroup bg5 = new ButtonGroup();
bg1.add(batterie1);
bg1.add(batterie0);//batterie0

ButtonGroup bg6 = new ButtonGroup();
bg1.add(avant1);
bg1.add(avant0);//batterie0

ButtonGroup bg7 = new ButtonGroup();
bg1.add(arriere0);
bg1.add(arriere1);//batterie0

ButtonGroup bg8 = new ButtonGroup();
bg1.add(cole0);
bg1.add(cole1);//batterie0

ButtonGroup bg9 = new ButtonGroup();
bg1.add(vitre1);
bg1.add(vitre0);//batterie0

ButtonGroup bg10 = new ButtonGroup();
bg1.add(seeuisglase1);
bg1.add(seeuisglase0);

ButtonGroup bg11 = new ButtonGroup();
bg1.add(retroviseurs1);
bg1.add(retroviseurs0);

ButtonGroup bg12 = new ButtonGroup();
bg1.add(immatriculation1);
bg1.add(immatriculation0);

ButtonGroup bg13 = new ButtonGroup();
bg1.add(girophare0);
bg1.add(girophare1);

ButtonGroup bg14 = new ButtonGroup();
bg1.add(interiere0);
bg1.add(interiere1);

ButtonGroup bg15 = new ButtonGroup();
bg1.add(exteriere0);
bg1.add(exteriere1);

ButtonGroup bg16 = new ButtonGroup();
bg1.add(feuxdepossition1);
bg1.add(feuxdepossition0);

ButtonGroup bg17 = new ButtonGroup();
bg1.add(feuxstop0);
bg1.add(feuxstop1);

ButtonGroup bg18 = new ButtonGroup();
bg1.add(clignotant0);
bg1.add(clignotant1);

ButtonGroup bg19 = new ButtonGroup();
bg1.add(phare0);
bg1.add(phare1);

ButtonGroup bg20 = new ButtonGroup();
bg1.add(claxion0);
bg1.add(claxion1);

ButtonGroup bg21 = new ButtonGroup();
bg1.add(seinture0);
bg1.add(seinture1);

ButtonGroup bg22 = new ButtonGroup();
bg1.add(paraprise0);
bg1.add(paraprise1);

ButtonGroup bg23 = new ButtonGroup();
bg1.add(frien0);
bg1.add(frien1);

ButtonGroup bg24 = new ButtonGroup();
bg1.add(freinamain1);
bg1.add(freinamain0);

ButtonGroup bg25 = new ButtonGroup();
bg1.add(pneureserve0);
bg1.add(pneureserve1);
jRadioButton1.setSelected(true);
}
    
           public void Call_ID_TO_BOMBOBOX()
    {
         
        try{
            //String sql="select distinct DESC   from  agin";
            String sql="select 	DESCR from  agin";
          
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                String sum=rs.getString("DESCR");
                  jComboBox1.addItem(sum);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
        try{
            //String sql="select distinct DESC   from  agin";
            String sql="select NUM_AGIN	  from   AGIN";
          
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                String sum=rs.getString("NUM_AGIN");
               //   jComboBox3.addItem(sum);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
        
        
         try{
            //String sql="select distinct DESC   from  agin";
            String sql="select NAME  from   EMPLOYEE";
          
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                String sum=rs.getString("NAME");
               //   jComboBox4.addItem(sum);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
        
        
    }
           
           
           
              
           public void select_combobox(){
           
           String tmp = jComboBox1.getSelectedItem().toString();
           //`NUM_AGIN`, `NOM`, `DESC`, `MODEL`, `TYPE`, `ANNE`, `MOTEUR`, `CHASSI`, `IMMAT`, `COULEUR`, `PLACE`, `PROV`, `KM`
                 try{
            String sql="select * from  agin where DESCR='"+tmp+"'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                
                   String sum1=rs.getString("NOM");
                 nom.setText(sum1);
                   String sum2=rs.getString("DESCR");
                  descr.setText(sum2);
                      String sum3=rs.getString("TYPE");
                 type.setText(sum3);
                  // String sum4=rs.getString("KM");
               // km.setText(sum4);
                   String sum5=rs.getString("IMMAT");
                  immat.setText(sum5);
                       String sum6=rs.getString("PLACE");
               //   place.setText(sum6);
                   String sum7=rs.getString("PROV");
                //  respo.setText(sum7);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
                   try{
            String sql="select sum(KM) from  driver_info where AGIN='"+tmp+"'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            if(rs.next()){
               
                
                   String sum5=rs.getString("sum(KM)");
                 kms.setText(sum5);
            }else{
                   try{
            String sqle="select KM from  agin where DESCR='"+tmp+"'";
           
            pst=con.prepareStatement(sqle);
            rs=pst.executeQuery();
            if(rs.next()){
               
                
                   String sum5=rs.getString("KM");
                 kms.setText(sum5);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
            
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
           }
        
         public void calulationS()
         {//AGIN LOURD, AGIN LEGER, MACHINE, MOTO, CAMION, VOITURE, MOTEUR ELECTROGENE
            if(type.getText().equals("AGIN LOURD")){
             float a = Float.parseFloat(km.getText());
             float b = Float.parseFloat("15000");
             float c;
             String d;
             c=a+b;
             d= String.format("%.2f",c);
         //  km_gr.setText(d);
           
            }else if(type.getText().equals("AGIN LEGER")){
                  float a = Float.parseFloat(km.getText());
             float b = Float.parseFloat("15000");
             float c;
             String d;
             c=a+b;
             d= String.format("%.2f",c);
          //   km_gr.setText(d);
             
             
            }else if(type.getText().equals("MACHINE")){
                  float a = Float.parseFloat(km.getText());
             float b = Float.parseFloat("15000");
             float c;
             String d;
             c=a+b;
             d= String.format("%.2f",c);
           //  km_gr.setText(d);
             
             
            }else if(type.getText().equals("MOTO")){
                  float a = Float.parseFloat(km.getText());
             float b = Float.parseFloat("1500");
             float c;
             String d;
             c=a+b;
             d= String.format("%.2f",c);
           //  km_gr.setText(d);
             
             
            }else if(type.getText().equals("VOITURE")){
                  float a = Float.parseFloat(km.getText());
             float b = Float.parseFloat("15000");
             float c;
             String d;
             c=a+b;
             d= String.format("%.2f",c);
           //  km_gr.setText(d);
             
             
            }else if(type.getText().equals("MOTEUR ELECTROGENE")){
                  float a = Float.parseFloat(km.getText());
             float b = Float.parseFloat("50");
             float c;
             String d;
             c=a+b;
             d= String.format("%.2f",c);
           //  km_gr.setText(d);
            }
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
         
         
     
         
         public void SAVE_TRANS(){
             // VERIFICATION DES NIVEAUX
String huile="";
if(huille1.isSelected()){
huile=huille1.getText();
}
if(huille0.isSelected()){
huile=huille0.getText();
}
String refroidisement="";
if(refroidisement1.isSelected()){
refroidisement=refroidisement1.getText();
}
if(refroidisement0.isSelected()){
refroidisement=refroidisement0.getText();
}
String glase="";
if(glase1.isSelected()){
glase=glase1.getText();
}
if(glase0.isSelected()){
glase=glase0.getText();
}
String liquidefreins="";
if(liquidefreins1.isSelected()){
liquidefreins=liquidefreins1.getText();
}
if(liquidefreins0.isSelected()){
liquidefreins=liquidefreins0.getText();
}
String batterie="";
if(batterie1.isSelected()){
batterie=batterie1.getText();
}
if(batterie0.isSelected()){
batterie=batterie0.getText();
}

// PNEUMATIQUES

String avant="";
if(avant1.isSelected()){
avant=avant1.getText();
}
if(avant0.isSelected()){
avant=avant0.getText();
}
String arriere="";
if(arriere1.isSelected()){
arriere=arriere1.getText();
}
if(arriere0.isSelected()){
arriere=arriere0.getText();
}
String cole="";
if(cole1.isSelected()){
cole=cole1.getText();
}
if(cole0.isSelected()){
cole=cole0.getText();
}

// VISIBILITE

String vitre="";
if(vitre1.isSelected()){
vitre=vitre1.getText();
}
if(vitre0.isSelected()){
vitre=vitre0.getText();
}
String seeuisglase="";
if(seeuisglase1.isSelected()){
seeuisglase=seeuisglase1.getText();
}
if(seeuisglase0.isSelected()){
seeuisglase=seeuisglase0.getText();
}
String retroviseurs="";
if(retroviseurs1.isSelected()){
retroviseurs=retroviseurs1.getText();
}
if(retroviseurs0.isSelected()){
retroviseurs=retroviseurs0.getText();
}
String immatriculation="";
if(immatriculation1.isSelected()){
immatriculation=immatriculation1.getText();
}
if(immatriculation0.isSelected()){
immatriculation=immatriculation0.getText();
}
String girophare="";
if(girophare1.isSelected()){
girophare=girophare1.getText();
}
if(girophare0.isSelected()){
girophare=girophare0.getText();
}



// ECLAIRAGE
String feuxdepossition="";
if(feuxdepossition1.isSelected()){
feuxdepossition=feuxdepossition1.getText();
}
if(feuxdepossition0.isSelected()){
feuxdepossition=feuxdepossition0.getText();
}
String feuxstop="";
if(feuxstop1.isSelected()){
feuxstop=feuxstop1.getText();
}
if(feuxstop0.isSelected()){
feuxstop=feuxstop0.getText();
}
String clignotant="";
if(clignotant1.isSelected()){
clignotant=clignotant1.getText();
}
if(clignotant0.isSelected()){
clignotant=clignotant0.getText();
}
String phare="";
if(phare1.isSelected()){
phare=phare1.getText();
}
if(phare0.isSelected()){
phare=phare0.getText();
}

// SYSTEME DE SECURITE

String claxion="";
if(claxion1.isSelected()){
claxion=claxion1.getText();
}
if(claxion0.isSelected()){
claxion=claxion0.getText();
}
String seinture="";
if(seinture1.isSelected()){
seinture=seinture1.getText();
}
if(seinture0.isSelected()){
seinture=seinture0.getText();
}
String paraprise="";
if(paraprise1.isSelected()){
paraprise=paraprise1.getText();
}
if(paraprise0.isSelected()){
paraprise=paraprise0.getText();
}
String frien="";
if(frien1.isSelected()){
frien=frien1.getText();
}
if(frien0.isSelected()){
frien=frien0.getText();
}
String freinamain="";
if(freinamain1.isSelected()){
freinamain=freinamain1.getText();
}
if(freinamain0.isSelected()){
freinamain=freinamain0.getText();
}
String pneureserve="";
if(pneureserve1.isSelected()){
pneureserve=pneureserve1.getText();
}
if(pneureserve0.isSelected()){
pneureserve=pneureserve0.getText();
}
String interiere="";
if(interiere1.isSelected()){
interiere=interiere1.getText();
}
if(interiere0.isSelected()){
interiere=interiere0.getText();
}
String exteriere="";
if(exteriere1.isSelected()){
exteriere=exteriere1.getText();
}
if(exteriere0.isSelected()){
exteriere=exteriere0.getText();
}
             try{
             String sql="Insert into `tableau_de_bord_trans`( `IMM`, `NOM`, `DESCR`, `KM`, `DATE_D_ENTRETIENT`, `HUILLE_MOTEUR`, `HUILLED`, `LIQUIDE_REFROIDISSEMENT`, `LIQUIDE_REFROIDISSEMENTD`, `LIQUIDE_LAVE_GLASE`, `LIQUIDE_LAVE_GLASED`, `LIQUIDE_DE_FREIN`, `LIQUIDE_DE_FREIND`, `LIQUIDE_BATERIE`, `LIQUIDE_BATERIED`, `PRESION_AVANT`, `PRESION_AVANTD`, `PRESION_ARRIERE`, `PRESION_ARRIERED`, `COLE`, `COLED`, `VITRE`, `VITRED`, `ESSUIS`, `ESSUISD`, `RETRO`, `RETROD`, `IMMATR`, `IMMATRD`, `GIRO`, `GIROD`, `POSITION`, `POSITIOND`, `STOP`, `STOPD`, `CLIGNOTA`, `CLIGNOTAD`, `PHARE`, `PHARED`, `CLAXION`, `CLAXIOND`, `SEINTURE`, `SEINTURED`, `PARAPRISE`, `PARAPRISED`, `FREIN`, `FREIND`, `FRIEN_A_MAIN`, `FRIEN_A_MAIND`, `PNEU_RESERVE`, `PNEU_RESERVED`, `INTERIERE`, `INTERIERED`, `EXTERIERE`, `EXTERIERED`, `TYPE`,DATES)"+
                      "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
           
             pst=con.prepareStatement(sql);
             
    pst.setString(1,immat.getText());
    pst.setString(2,nom.getText());
    pst.setString(3,descr.getText());
    pst.setString(4,km.getText());
    SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
    String addDate = dateFormats.format(jDateChooser2.getDate());
    pst.setString(5, addDate);
    
    
     pst.setString(6,huile);
     pst.setString(7,huilled.getText());
    pst.setString(8,refroidisement);
    pst.setString(9,refroidisementd.getText());
    pst.setString(10,glase);
    pst.setString(11,glased.getText());
    pst.setString(12,liquidefreins);
    pst.setString(13,liquidefreinsd.getText());
    pst.setString(14,batterie);
    pst.setString(15,batteried.getText());
    pst.setString(16,avant);
    pst.setString(17,avantd.getText());
     pst.setString(18,arriere);
     pst.setString(19,arriered.getText());
    pst.setString(20,cole);
    pst.setString(21,coled.getText());
    pst.setString(22,vitre);
    pst.setString(23,vitred.getText());
    pst.setString(24,seeuisglase);
    pst.setString(25,seeuisglased.getText());
    pst.setString(26,retroviseurs);
    pst.setString(27,retroviseursd.getText());
    pst.setString(28,immatriculation);
    pst.setString(29,immatriculationd.getText());
     pst.setString(30,girophare);
     pst.setString(31,girophared.getText());
    pst.setString(32,feuxdepossition);
    pst.setString(33,feuxdepossitiond.getText());
    pst.setString(34,feuxstop);
    pst.setString(35,feuxstopd.getText());
    pst.setString(36,clignotant);
    pst.setString(37,clignotantd.getText());
    pst.setString(38,phare);
    pst.setString(39,phared.getText());
    pst.setString(40,claxion);
    pst.setString(41,claxiond.getText());
     pst.setString(42,seinture);
     pst.setString(43,seintured.getText());
    pst.setString(44,paraprise);
    pst.setString(45,paraprised.getText());
    pst.setString(46,frien);
    pst.setString(47,friend.getText());
    pst.setString(48,freinamain);
    pst.setString(49,freinamaind.getText());
    pst.setString(50,pneureserve);
    pst.setString(51,pneureserved.getText());
    pst.setString(52,interiere);
    pst.setString(53,interiered.getText());
     pst.setString(54,exteriere);
    pst.setString(55,exteriered.getText());
    pst.setString(56,type.getText());
    SimpleDateFormat dateFormats1 = new SimpleDateFormat("yyyy-MM-dd");
    String addDate1 = dateFormats1.format(jDateChooser3.getDate());
    pst.setString(57, addDate1);
    
//    String value=jComboBox5.getSelectedItem().toString();
     //   pst.setString(56, value);

    
    
    
    pst.executeUpdate();
    JOptionPane.showMessageDialog(null, "Transaction Saved");
         
             
             
             
             }catch(SQLException ex){JOptionPane.showMessageDialog(null, ex);}
         }
         
         
         
          public void SAVE(){
             // VERIFICATION DES NIVEAUX
String huile="";
if(huille1.isSelected()){
huile=huille1.getText();
}
if(huille0.isSelected()){
huile=huille0.getText();
}
String refroidisement="";
if(refroidisement1.isSelected()){
refroidisement=refroidisement1.getText();
}
if(refroidisement0.isSelected()){
refroidisement=refroidisement0.getText();
}
String glase="";
if(glase1.isSelected()){
glase=glase1.getText();
}
if(glase0.isSelected()){
glase=glase0.getText();
}
String liquidefreins="";
if(liquidefreins1.isSelected()){
liquidefreins=liquidefreins1.getText();
}
if(liquidefreins0.isSelected()){
liquidefreins=liquidefreins0.getText();
}
String batterie="";
if(batterie1.isSelected()){
batterie=batterie1.getText();
}
if(batterie0.isSelected()){
batterie=batterie0.getText();
}

// PNEUMATIQUES

String avant="";
if(avant1.isSelected()){
avant=avant1.getText();
}
if(avant0.isSelected()){
avant=avant0.getText();
}
String arriere="";
if(arriere1.isSelected()){
arriere=arriere1.getText();
}
if(arriere0.isSelected()){
arriere=arriere0.getText();
}
String cole="";
if(cole1.isSelected()){
cole=cole1.getText();
}
if(cole0.isSelected()){
cole=cole0.getText();
}

// VISIBILITE

String vitre="";
if(vitre1.isSelected()){
vitre=vitre1.getText();
}
if(vitre0.isSelected()){
vitre=vitre0.getText();
}
String seeuisglase="";
if(seeuisglase1.isSelected()){
seeuisglase=seeuisglase1.getText();
}
if(seeuisglase0.isSelected()){
seeuisglase=seeuisglase0.getText();
}
String retroviseurs="";
if(retroviseurs1.isSelected()){
retroviseurs=retroviseurs1.getText();
}
if(retroviseurs0.isSelected()){
retroviseurs=retroviseurs0.getText();
}
String immatriculation="";
if(immatriculation1.isSelected()){
immatriculation=immatriculation1.getText();
}
if(immatriculation0.isSelected()){
immatriculation=immatriculation0.getText();
}
String girophare="";
if(girophare1.isSelected()){
girophare=girophare1.getText();
}
if(girophare0.isSelected()){
girophare=girophare0.getText();
}



// ECLAIRAGE
String feuxdepossition="";
if(feuxdepossition1.isSelected()){
feuxdepossition=feuxdepossition1.getText();
}
if(feuxdepossition0.isSelected()){
feuxdepossition=feuxdepossition0.getText();
}
String feuxstop="";
if(feuxstop1.isSelected()){
feuxstop=feuxstop1.getText();
}
if(feuxstop0.isSelected()){
feuxstop=feuxstop0.getText();
}
String clignotant="";
if(clignotant1.isSelected()){
clignotant=clignotant1.getText();
}
if(clignotant0.isSelected()){
clignotant=clignotant0.getText();
}
String phare="";
if(phare1.isSelected()){
phare=phare1.getText();
}
if(phare0.isSelected()){
phare=phare0.getText();
}

// SYSTEME DE SECURITE

String claxion="";
if(claxion1.isSelected()){
claxion=claxion1.getText();
}
if(claxion0.isSelected()){
claxion=claxion0.getText();
}
String seinture="";
if(seinture1.isSelected()){
seinture=seinture1.getText();
}
if(seinture0.isSelected()){
seinture=seinture0.getText();
}
String paraprise="";
if(paraprise1.isSelected()){
paraprise=paraprise1.getText();
}
if(paraprise0.isSelected()){
paraprise=paraprise0.getText();
}
String frien="";
if(frien1.isSelected()){
frien=frien1.getText();
}
if(frien0.isSelected()){
frien=frien0.getText();
}
String freinamain="";
if(freinamain1.isSelected()){
freinamain=freinamain1.getText();
}
if(freinamain0.isSelected()){
freinamain=freinamain0.getText();
}
String pneureserve="";
if(pneureserve1.isSelected()){
pneureserve=pneureserve1.getText();
}
if(pneureserve0.isSelected()){
pneureserve=pneureserve0.getText();
}
String interiere="";
if(interiere1.isSelected()){
interiere=interiere1.getText();
}
if(interiere0.isSelected()){
interiere=interiere0.getText();
}
String exteriere="";
if(exteriere1.isSelected()){
exteriere=exteriere1.getText();
}
if(exteriere0.isSelected()){
exteriere=exteriere0.getText();
}
             try{
             String sql="Insert into `tableau_de_bord`( `IMM`, `NOM`, `DESCR`, `KM`, `DATE_D_ENTRETIENT`, `HUILLE_MOTEUR`, `HUILLED`, `LIQUIDE_REFROIDISSEMENT`, `LIQUIDE_REFROIDISSEMENTD`, `LIQUIDE_LAVE_GLASE`, `LIQUIDE_LAVE_GLASED`, `LIQUIDE_DE_FREIN`, `LIQUIDE_DE_FREIND`, `LIQUIDE_BATERIE`, `LIQUIDE_BATERIED`, `PRESION_AVANT`, `PRESION_AVANTD`, `PRESION_ARRIERE`, `PRESION_ARRIERED`, `COLE`, `COLED`, `VITRE`, `VITRED`, `ESSUIS`, `ESSUISD`, `RETRO`, `RETROD`, `IMMATR`, `IMMATRD`, `GIRO`, `GIROD`, `POSITION`, `POSITIOND`, `STOP`, `STOPD`, `CLIGNOTA`, `CLIGNOTAD`, `PHARE`, `PHARED`, `CLAXION`, `CLAXIOND`, `SEINTURE`, `SEINTURED`, `PARAPRISE`, `PARAPRISED`, `FREIN`, `FREIND`, `FRIEN_A_MAIN`, `FRIEN_A_MAIND`, `PNEU_RESERVE`, `PNEU_RESERVED`, `INTERIERE`, `INTERIERED`, `EXTERIERE`, `EXTERIERED`, `TYPE`,DATES)"+
                      "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
           
             pst=con.prepareStatement(sql);
             
    pst.setString(1,immat.getText());
    pst.setString(2,nom.getText());
    pst.setString(3,descr.getText());
    pst.setString(4,km.getText());
    SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
    String addDate = dateFormats.format(jDateChooser2.getDate());
    pst.setString(5, addDate);
    
    
     pst.setString(6,huile);
     pst.setString(7,huilled.getText());
    pst.setString(8,refroidisement);
    pst.setString(9,refroidisementd.getText());
    pst.setString(10,glase);
    pst.setString(11,glased.getText());
    pst.setString(12,liquidefreins);
    pst.setString(13,liquidefreinsd.getText());
    pst.setString(14,batterie);
    pst.setString(15,batteried.getText());
    pst.setString(16,avant);
    pst.setString(17,avantd.getText());
     pst.setString(18,arriere);
     pst.setString(19,arriered.getText());
    pst.setString(20,cole);
    pst.setString(21,coled.getText());
    pst.setString(22,vitre);
    pst.setString(23,vitred.getText());
    pst.setString(24,seeuisglase);
    pst.setString(25,seeuisglased.getText());
    pst.setString(26,retroviseurs);
    pst.setString(27,retroviseursd.getText());
    pst.setString(28,immatriculation);
    pst.setString(29,immatriculationd.getText());
     pst.setString(30,girophare);
     pst.setString(31,girophared.getText());
    pst.setString(32,feuxdepossition);
    pst.setString(33,feuxdepossitiond.getText());
    pst.setString(34,feuxstop);
    pst.setString(35,feuxstopd.getText());
    pst.setString(36,clignotant);
    pst.setString(37,clignotantd.getText());
    pst.setString(38,phare);
    pst.setString(39,phared.getText());
    pst.setString(40,claxion);
    pst.setString(41,claxiond.getText());
     pst.setString(42,seinture);
     pst.setString(43,seintured.getText());
    pst.setString(44,paraprise);
    pst.setString(45,paraprised.getText());
    pst.setString(46,frien);
    pst.setString(47,friend.getText());
    pst.setString(48,freinamain);
    pst.setString(49,freinamaind.getText());
    pst.setString(50,pneureserve);
    pst.setString(51,pneureserved.getText());
    pst.setString(52,interiere);
    pst.setString(53,interiered.getText());
     pst.setString(54,exteriere);
    pst.setString(55,exteriered.getText());
    pst.setString(56,type.getText());
  SimpleDateFormat dateFormats1 = new SimpleDateFormat("yyyy-MM-dd");
    String addDate1 = dateFormats1.format(jDateChooser3.getDate());
    pst.setString(57, addDate1);
    
//    String value=jComboBox5.getSelectedItem().toString();
     //   pst.setString(56, value);

    
    
    
    pst.executeUpdate();
   // JOptionPane.showMessageDialog(null, "SAVED");
         
             
             
             
             }catch(SQLException ex){JOptionPane.showMessageDialog(null, ex);}
             
              try{
        rolls();
        String sql="Insert into driver_info (`AGIN`, `NAME_DRIVER`,`NBRE DE PASSAGER`, `DEPART`, `ARRIVE`, `REFIL`, `KM`, `KM_EFF`, `MOTIF`, `DATES`, `VALIDATION`, `REFF_NUM`)"+
                "values(?,?,?,?,?,?,?,?,?,?,?,?)";
        pst=con.prepareStatement(sql);
      
  pst.setString(1,descr.getText());
         pst.setString(2,"LOG");
pst.setString(3,"LOG");
pst.setString(4, "LOG");
pst.setString(5, "LOG");
pst.setString(6, "0");
pst.setString(7, km.getText());
pst.setString(8, "0");
pst.setString(9, "LOG");

pst.setString(11,"LOG");
pst.setString(12,Rolls);

SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(new Date());
         pst.setString(10, addDate);
        
        
    pst.executeUpdate();
   // JOptionPane.showMessageDialog(null, "SAVED");
        
        }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}  
         }
         
          public void update(){
             // VERIFICATION DES NIVEAUX
String huile="";
if(huille1.isSelected()){
huile=huille1.getText();
}
if(huille0.isSelected()){
huile=huille0.getText();
}
String refroidisement="";
if(refroidisement1.isSelected()){
refroidisement=refroidisement1.getText();
}
if(refroidisement0.isSelected()){
refroidisement=refroidisement0.getText();
}
String glase="";
if(glase1.isSelected()){
glase=glase1.getText();
}
if(glase0.isSelected()){
glase=glase0.getText();
}
String liquidefreins="";
if(liquidefreins1.isSelected()){
liquidefreins=liquidefreins1.getText();
}
if(liquidefreins0.isSelected()){
liquidefreins=liquidefreins0.getText();
}
String batterie="";
if(batterie1.isSelected()){
batterie=batterie1.getText();
}
if(batterie0.isSelected()){
batterie=batterie0.getText();
}

// PNEUMATIQUES

String avant="";
if(avant1.isSelected()){
avant=avant1.getText();
}
if(avant0.isSelected()){
avant=avant0.getText();
}
String arriere="";
if(arriere1.isSelected()){
arriere=arriere1.getText();
}
if(arriere0.isSelected()){
arriere=arriere0.getText();
}
String cole="";
if(cole1.isSelected()){
cole=cole1.getText();
}
if(cole0.isSelected()){
cole=cole0.getText();
}

// VISIBILITE

String vitre="";
if(vitre1.isSelected()){
vitre=vitre1.getText();
}
if(vitre0.isSelected()){
vitre=vitre0.getText();
}
String seeuisglase="";
if(seeuisglase1.isSelected()){
seeuisglase=seeuisglase1.getText();
}
if(seeuisglase0.isSelected()){
seeuisglase=seeuisglase0.getText();
}
String retroviseurs="";
if(retroviseurs1.isSelected()){
retroviseurs=retroviseurs1.getText();
}
if(retroviseurs0.isSelected()){
retroviseurs=retroviseurs0.getText();
}
String immatriculation="";
if(immatriculation1.isSelected()){
immatriculation=immatriculation1.getText();
}
if(immatriculation0.isSelected()){
immatriculation=immatriculation0.getText();
}
String girophare="";
if(girophare1.isSelected()){
girophare=girophare1.getText();
}
if(girophare0.isSelected()){
girophare=girophare0.getText();
}



// ECLAIRAGE
String feuxdepossition="";
if(feuxdepossition1.isSelected()){
feuxdepossition=feuxdepossition1.getText();
}
if(feuxdepossition0.isSelected()){
feuxdepossition=feuxdepossition0.getText();
}
String feuxstop="";
if(feuxstop1.isSelected()){
feuxstop=feuxstop1.getText();
}
if(feuxstop0.isSelected()){
feuxstop=feuxstop0.getText();
}
String clignotant="";
if(clignotant1.isSelected()){
clignotant=clignotant1.getText();
}
if(clignotant0.isSelected()){
clignotant=clignotant0.getText();
}
String phare="";
if(phare1.isSelected()){
phare=phare1.getText();
}
if(phare0.isSelected()){
phare=phare0.getText();
}

// SYSTEME DE SECURITE

String claxion="";
if(claxion1.isSelected()){
claxion=claxion1.getText();
}
if(claxion0.isSelected()){
claxion=claxion0.getText();
}
String seinture="";
if(seinture1.isSelected()){
seinture=seinture1.getText();
}
if(seinture0.isSelected()){
seinture=seinture0.getText();
}
String paraprise="";
if(paraprise1.isSelected()){
paraprise=paraprise1.getText();
}
if(paraprise0.isSelected()){
paraprise=paraprise0.getText();
}
String frien="";
if(frien1.isSelected()){
frien=frien1.getText();
}
if(frien0.isSelected()){
frien=frien0.getText();
}
String freinamain="";
if(freinamain1.isSelected()){
freinamain=freinamain1.getText();
}
if(freinamain0.isSelected()){
freinamain=freinamain0.getText();
}
String pneureserve="";
if(pneureserve1.isSelected()){
pneureserve=pneureserve1.getText();
}
if(pneureserve0.isSelected()){
pneureserve=pneureserve0.getText();
}
String interiere="";
if(interiere1.isSelected()){
interiere=interiere1.getText();
}
if(interiere0.isSelected()){
interiere=interiere0.getText();
}
String exteriere="";
if(exteriere1.isSelected()){
exteriere=exteriere1.getText();
}
if(exteriere0.isSelected()){
exteriere=exteriere0.getText();
}
             try{
             String sql="UPDATE `tableau_de_bord` SET `IMM`=?,`NOM`=?,`DESCR`=?,`KM`=?,`DATE_D_ENTRETIENT`=?,`HUILLE_MOTEUR`=?,`HUILLED`=?,`LIQUIDE_REFROIDISSEMENT`=?,`LIQUIDE_REFROIDISSEMENTD`=?,`LIQUIDE_LAVE_GLASE`=?,`LIQUIDE_LAVE_GLASED`=?,`LIQUIDE_DE_FREIN`=?,`LIQUIDE_DE_FREIND`=?,`LIQUIDE_BATERIE`=?,`LIQUIDE_BATERIED`=?,`PRESION_AVANT`=?,`PRESION_AVANTD`=?,`PRESION_ARRIERE`=?,`PRESION_ARRIERED`=?,`COLE`=?,`COLED`=?,`VITRE`=?,`VITRED`=?,`ESSUIS`=?,`ESSUISD`=?,`RETRO`=?,`RETROD`=?,`IMMATR`=?,`IMMATRD`=?,`GIRO`=?,`GIROD`=?,`POSITION`=?,`POSITIOND`=?,`STOP`=?,`STOPD`=?,`CLIGNOTA`=?,`CLIGNOTAD`=?,`PHARE`=?,`PHARED`=?,`CLAXION`=?,`CLAXIOND`=?,`SEINTURE`=?,`SEINTURED`=?,`PARAPRISE`=?,`PARAPRISED`=?,`FREIN`=?,`FREIND`=?,`FRIEN_A_MAIN`=?,`FRIEN_A_MAIND`=?,`PNEU_RESERVE`=?,`PNEU_RESERVED`=?,`INTERIERE`=?,`INTERIERED`=?,`EXTERIERE`=?,`EXTERIERED`=?,`TYPE`=?,`DATES`=? where DESCR='"+jComboBox1.getSelectedItem()+"'";
           
             pst=con.prepareStatement(sql);
             
    pst.setString(1,immat.getText());
    pst.setString(2,nom.getText());
    pst.setString(3,descr.getText());
    pst.setString(4,km.getText());
    SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
    String addDate = dateFormats.format(jDateChooser2.getDate());
    pst.setString(5, addDate);
    
    
     pst.setString(6,huile);
     pst.setString(7,huilled.getText());
    pst.setString(8,refroidisement);
    pst.setString(9,refroidisementd.getText());
    pst.setString(10,glase);
    pst.setString(11,glased.getText());
    pst.setString(12,liquidefreins);
    pst.setString(13,liquidefreinsd.getText());
    pst.setString(14,batterie);
    pst.setString(15,batteried.getText());
    pst.setString(16,avant);
    pst.setString(17,avantd.getText());
     pst.setString(18,arriere);
     pst.setString(19,arriered.getText());
    pst.setString(20,cole);
    pst.setString(21,coled.getText());
    pst.setString(22,vitre);
    pst.setString(23,vitred.getText());
    pst.setString(24,seeuisglase);
    pst.setString(25,seeuisglased.getText());
    pst.setString(26,retroviseurs);
    pst.setString(27,retroviseursd.getText());
    pst.setString(28,immatriculation);
    pst.setString(29,immatriculationd.getText());
     pst.setString(30,girophare);
     pst.setString(31,girophared.getText());
    pst.setString(32,feuxdepossition);
    pst.setString(33,feuxdepossitiond.getText());
    pst.setString(34,feuxstop);
    pst.setString(35,feuxstopd.getText());
    pst.setString(36,clignotant);
    pst.setString(37,clignotantd.getText());
    pst.setString(38,phare);
    pst.setString(39,phared.getText());
    pst.setString(40,claxion);
    pst.setString(41,claxiond.getText());
     pst.setString(42,seinture);
     pst.setString(43,seintured.getText());
    pst.setString(44,paraprise);
    pst.setString(45,paraprised.getText());
    pst.setString(46,frien);
    pst.setString(47,friend.getText());
    pst.setString(48,freinamain);
    pst.setString(49,freinamaind.getText());
    pst.setString(50,pneureserve);
    pst.setString(51,pneureserved.getText());
    pst.setString(52,interiere);
    pst.setString(53,interiered.getText());
     pst.setString(54,exteriere);
    pst.setString(55,exteriered.getText());
    pst.setString(56,type.getText());
  SimpleDateFormat dateFormats1 = new SimpleDateFormat("yyyy-MM-dd");
    String addDate1 = dateFormats1.format(jDateChooser3.getDate());
    pst.setString(57, addDate1);
    
//    String value=jComboBox5.getSelectedItem().toString();
     //   pst.setString(56, value);

    
    
    
    pst.executeUpdate();
    //JOptionPane.showMessageDialog(null, "SAVED");
         
             
             
             
             }catch(SQLException ex){JOptionPane.showMessageDialog(null, ex);}
             
             
           
          
         }
         
          
          public void saveinbord(){
              
              String CHK = null;
              try{
            String sql="select DESCR from  tableau_de_bord where  DESCR='"+jComboBox1.getSelectedItem()+"'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
               
                
                   CHK=rs.getString("DESCR");
                
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
              
              if(CHK==null){
                 SAVE();
              }else{
                  update();
              }
          SAVE_TRANS();
          CHK=null;
          refresh();
          
          }
         
         
         
         
         
         //VALIDATION
     
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        webDateField1 = new com.alee.extended.date.WebDateField();
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
        jPanel4 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        immat = new javax.swing.JTextField();
        nom = new javax.swing.JTextField();
        descr = new javax.swing.JTextField();
        type = new javax.swing.JTextField();
        jPanel14 = new javax.swing.JPanel();
        jComboBox1 = new javax.swing.JComboBox<>();
        km = new javax.swing.JTextField();
        jDateChooser3 = new com.alee.extended.date.WebDateField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jDateChooser2 = new com.alee.extended.date.WebDateField();
        kms = new javax.swing.JTextField();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu2 = new javax.swing.JMenu();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        webDateField1.setText("webDateField1");

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setText(" VERIFICATION DES NIVEAUX");
        jLabel12.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel6.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 2, 389, -1));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel13.setText("Huile moteur");
        jPanel6.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 27, -1, -1));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel14.setText("Liquide de refroidissement");
        jPanel6.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 45, -1, -1));

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel15.setText("Liquide lave-glace");
        jPanel6.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 65, -1, -1));

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel16.setText("Liquide de freins");
        jPanel6.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 86, -1, -1));

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel17.setText("Liquide de batterie");
        jPanel6.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 107, -1, -1));

        huille1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        huille1.setText("oui");
        jPanel6.add(huille1, new org.netbeans.lib.awtextra.AbsoluteConstraints(184, 27, -1, 14));

        huille0.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        huille0.setText("non");
        huille0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                huille0ActionPerformed(evt);
            }
        });
        jPanel6.add(huille0, new org.netbeans.lib.awtextra.AbsoluteConstraints(231, 27, -1, 14));

        refroidisement1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        refroidisement1.setText("oui");
        jPanel6.add(refroidisement1, new org.netbeans.lib.awtextra.AbsoluteConstraints(184, 46, -1, 14));

        refroidisement0.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        refroidisement0.setText("non");
        refroidisement0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refroidisement0ActionPerformed(evt);
            }
        });
        jPanel6.add(refroidisement0, new org.netbeans.lib.awtextra.AbsoluteConstraints(231, 46, -1, 14));

        glase1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        glase1.setText("oui");
        jPanel6.add(glase1, new org.netbeans.lib.awtextra.AbsoluteConstraints(184, 67, -1, 13));

        glase0.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        glase0.setText("non");
        glase0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                glase0ActionPerformed(evt);
            }
        });
        jPanel6.add(glase0, new org.netbeans.lib.awtextra.AbsoluteConstraints(231, 66, -1, 14));

        liquidefreins1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        liquidefreins1.setText("oui");
        liquidefreins1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                liquidefreins1ActionPerformed(evt);
            }
        });
        jPanel6.add(liquidefreins1, new org.netbeans.lib.awtextra.AbsoluteConstraints(184, 86, -1, 14));

        liquidefreins0.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        liquidefreins0.setText("non");
        liquidefreins0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                liquidefreins0ActionPerformed(evt);
            }
        });
        jPanel6.add(liquidefreins0, new org.netbeans.lib.awtextra.AbsoluteConstraints(231, 86, -1, 14));

        batterie1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        batterie1.setText("oui");
        jPanel6.add(batterie1, new org.netbeans.lib.awtextra.AbsoluteConstraints(184, 106, -1, 14));

        batterie0.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        batterie0.setText("non");
        batterie0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                batterie0ActionPerformed(evt);
            }
        });
        jPanel6.add(batterie0, new org.netbeans.lib.awtextra.AbsoluteConstraints(231, 105, -1, 17));

        huilled.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        huilled.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel6.add(huilled, new org.netbeans.lib.awtextra.AbsoluteConstraints(286, 27, 95, -1));

        refroidisementd.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        refroidisementd.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel6.add(refroidisementd, new org.netbeans.lib.awtextra.AbsoluteConstraints(286, 45, 95, -1));

        glased.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        glased.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel6.add(glased, new org.netbeans.lib.awtextra.AbsoluteConstraints(286, 65, 95, -1));

        liquidefreinsd.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        liquidefreinsd.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel6.add(liquidefreinsd, new org.netbeans.lib.awtextra.AbsoluteConstraints(286, 85, 95, -1));

        batteried.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        batteried.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
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
        jPanel7.add(avant1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 28, -1, 15));

        avant0.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        avant0.setText("non");
        avant0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                avant0ActionPerformed(evt);
            }
        });
        jPanel7.add(avant0, new org.netbeans.lib.awtextra.AbsoluteConstraints(237, 28, -1, 15));

        avantd.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        avantd.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel7.add(avantd, new org.netbeans.lib.awtextra.AbsoluteConstraints(288, 27, 93, -1));

        arriere1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        arriere1.setText("oui");
        jPanel7.add(arriere1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 51, -1, 15));

        arriere0.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        arriere0.setText("non");
        arriere0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                arriere0ActionPerformed(evt);
            }
        });
        jPanel7.add(arriere0, new org.netbeans.lib.awtextra.AbsoluteConstraints(237, 51, -1, 15));

        arriered.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        arriered.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel7.add(arriered, new org.netbeans.lib.awtextra.AbsoluteConstraints(288, 50, 93, -1));

        cole0.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        cole0.setText("non");
        cole0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cole0ActionPerformed(evt);
            }
        });
        jPanel7.add(cole0, new org.netbeans.lib.awtextra.AbsoluteConstraints(237, 74, -1, 15));

        cole1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        cole1.setText("oui");
        jPanel7.add(cole1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 74, -1, 15));

        coled.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        coled.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
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
        jLabel24.setText("Balais d'essuie-glaces");
        jPanel8.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 47, -1, -1));

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel25.setText("Rétroviseurs");
        jPanel8.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 67, -1, -1));

        vitre1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        vitre1.setText("oui");
        jPanel8.add(vitre1, new org.netbeans.lib.awtextra.AbsoluteConstraints(192, 28, -1, 15));

        vitre0.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        vitre0.setText("non");
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
        jLabel27.setText("Giro phare et Drapelet");
        jPanel8.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 107, -1, -1));

        vitred.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        vitred.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        jPanel8.add(vitred, new org.netbeans.lib.awtextra.AbsoluteConstraints(288, 27, 93, -1));

        seeuisglased.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        seeuisglased.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        jPanel8.add(seeuisglased, new org.netbeans.lib.awtextra.AbsoluteConstraints(288, 47, 93, -1));

        seeuisglase0.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        seeuisglase0.setText("non");
        seeuisglase0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seeuisglase0ActionPerformed(evt);
            }
        });
        jPanel8.add(seeuisglase0, new org.netbeans.lib.awtextra.AbsoluteConstraints(239, 48, -1, 14));

        seeuisglase1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        seeuisglase1.setText("oui");
        jPanel8.add(seeuisglase1, new org.netbeans.lib.awtextra.AbsoluteConstraints(192, 48, -1, 15));

        retroviseursd.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        retroviseursd.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        jPanel8.add(retroviseursd, new org.netbeans.lib.awtextra.AbsoluteConstraints(288, 67, 93, -1));

        retroviseurs0.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        retroviseurs0.setText("non");
        retroviseurs0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                retroviseurs0ActionPerformed(evt);
            }
        });
        jPanel8.add(retroviseurs0, new org.netbeans.lib.awtextra.AbsoluteConstraints(239, 68, -1, 14));

        retroviseurs1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        retroviseurs1.setText("oui");
        jPanel8.add(retroviseurs1, new org.netbeans.lib.awtextra.AbsoluteConstraints(192, 68, -1, 15));

        immatriculationd.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        immatriculationd.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        jPanel8.add(immatriculationd, new org.netbeans.lib.awtextra.AbsoluteConstraints(288, 87, 93, -1));

        immatriculation0.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        immatriculation0.setText("non");
        immatriculation0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                immatriculation0ActionPerformed(evt);
            }
        });
        jPanel8.add(immatriculation0, new org.netbeans.lib.awtextra.AbsoluteConstraints(239, 88, -1, 14));

        immatriculation1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        immatriculation1.setText("oui");
        jPanel8.add(immatriculation1, new org.netbeans.lib.awtextra.AbsoluteConstraints(192, 88, -1, 15));

        girophare1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        girophare1.setText("oui");
        jPanel8.add(girophare1, new org.netbeans.lib.awtextra.AbsoluteConstraints(192, 111, -1, 15));

        girophare0.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        girophare0.setText("non");
        girophare0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                girophare0ActionPerformed(evt);
            }
        });
        jPanel8.add(girophare0, new org.netbeans.lib.awtextra.AbsoluteConstraints(239, 111, -1, 14));

        girophared.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        girophared.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
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
        feuxdepossition1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                feuxdepossition1ActionPerformed(evt);
            }
        });
        jPanel9.add(feuxdepossition1, new org.netbeans.lib.awtextra.AbsoluteConstraints(113, 28, -1, 14));

        feuxdepossition0.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        feuxdepossition0.setText("non");
        jPanel9.add(feuxdepossition0, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 28, -1, 14));

        feuxdepossitiond.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        feuxdepossitiond.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        jPanel9.add(feuxdepossitiond, new org.netbeans.lib.awtextra.AbsoluteConstraints(211, 27, 100, -1));

        feuxstopd.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        feuxstopd.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        jPanel9.add(feuxstopd, new org.netbeans.lib.awtextra.AbsoluteConstraints(211, 47, 100, -1));

        feuxstop0.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        feuxstop0.setText("non");
        jPanel9.add(feuxstop0, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 48, -1, 14));

        feuxstop1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        feuxstop1.setText("oui");
        feuxstop1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                feuxstop1ActionPerformed(evt);
            }
        });
        jPanel9.add(feuxstop1, new org.netbeans.lib.awtextra.AbsoluteConstraints(113, 48, -1, 14));

        clignotantd.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        clignotantd.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        jPanel9.add(clignotantd, new org.netbeans.lib.awtextra.AbsoluteConstraints(211, 67, 100, -1));

        clignotant0.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        clignotant0.setText("non");
        jPanel9.add(clignotant0, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 68, -1, 14));

        clignotant1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        clignotant1.setText("oui");
        clignotant1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clignotant1ActionPerformed(evt);
            }
        });
        jPanel9.add(clignotant1, new org.netbeans.lib.awtextra.AbsoluteConstraints(113, 68, -1, 14));

        phared.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        phared.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        jPanel9.add(phared, new org.netbeans.lib.awtextra.AbsoluteConstraints(211, 87, 100, -1));

        phare0.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        phare0.setText("non");
        jPanel9.add(phare0, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 88, -1, 14));

        phare1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        phare1.setText("oui");
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
        jLabel35.setText("Ceinture de sec.");
        jPanel10.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 48, -1, -1));

        jLabel36.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel36.setText("Para prise");
        jPanel10.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 68, -1, -1));

        jLabel37.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel37.setText("Frein");
        jPanel10.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 88, -1, -1));

        claxion1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        claxion1.setText("oui");
        claxion1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                claxion1ActionPerformed(evt);
            }
        });
        jPanel10.add(claxion1, new org.netbeans.lib.awtextra.AbsoluteConstraints(115, 28, -1, 14));

        claxion0.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        claxion0.setText("non");
        jPanel10.add(claxion0, new org.netbeans.lib.awtextra.AbsoluteConstraints(162, 28, -1, 14));

        claxiond.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        claxiond.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        jPanel10.add(claxiond, new org.netbeans.lib.awtextra.AbsoluteConstraints(213, 27, 100, -1));

        seintured.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        seintured.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        jPanel10.add(seintured, new org.netbeans.lib.awtextra.AbsoluteConstraints(213, 47, 100, -1));

        seinture0.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        seinture0.setText("non");
        jPanel10.add(seinture0, new org.netbeans.lib.awtextra.AbsoluteConstraints(162, 48, -1, 14));

        seinture1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        seinture1.setText("oui");
        seinture1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seinture1ActionPerformed(evt);
            }
        });
        jPanel10.add(seinture1, new org.netbeans.lib.awtextra.AbsoluteConstraints(115, 48, -1, 14));

        paraprised.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        paraprised.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        jPanel10.add(paraprised, new org.netbeans.lib.awtextra.AbsoluteConstraints(213, 67, 100, -1));

        paraprise0.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        paraprise0.setText("non");
        jPanel10.add(paraprise0, new org.netbeans.lib.awtextra.AbsoluteConstraints(162, 68, -1, 14));

        paraprise1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        paraprise1.setText("oui");
        paraprise1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                paraprise1ActionPerformed(evt);
            }
        });
        jPanel10.add(paraprise1, new org.netbeans.lib.awtextra.AbsoluteConstraints(115, 68, -1, 14));

        friend.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        friend.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        jPanel10.add(friend, new org.netbeans.lib.awtextra.AbsoluteConstraints(213, 87, 100, -1));

        frien0.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        frien0.setText("non");
        jPanel10.add(frien0, new org.netbeans.lib.awtextra.AbsoluteConstraints(162, 88, -1, 14));

        frien1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        frien1.setText("oui");
        frien1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                frien1ActionPerformed(evt);
            }
        });
        jPanel10.add(frien1, new org.netbeans.lib.awtextra.AbsoluteConstraints(115, 128, -1, 14));

        jLabel38.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel38.setText("Frein à main");
        jPanel10.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 108, -1, -1));

        freinamain1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        freinamain1.setText("oui");
        freinamain1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                freinamain1ActionPerformed(evt);
            }
        });
        jPanel10.add(freinamain1, new org.netbeans.lib.awtextra.AbsoluteConstraints(115, 108, -1, 14));

        freinamain0.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        freinamain0.setText("non");
        jPanel10.add(freinamain0, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 108, -1, 14));

        jLabel39.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel39.setText("Pneu réserve");
        jPanel10.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 128, -1, -1));

        pneureserve1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        pneureserve1.setText("oui");
        pneureserve1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pneureserve1ActionPerformed(evt);
            }
        });
        jPanel10.add(pneureserve1, new org.netbeans.lib.awtextra.AbsoluteConstraints(115, 88, -1, 14));

        pneureserve0.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        pneureserve0.setText("non");
        jPanel10.add(pneureserve0, new org.netbeans.lib.awtextra.AbsoluteConstraints(162, 128, -1, 14));

        freinamaind.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        freinamaind.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        jPanel10.add(freinamaind, new org.netbeans.lib.awtextra.AbsoluteConstraints(213, 107, 100, -1));

        pneureserved.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        pneureserved.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        jPanel10.add(pneureserved, new org.netbeans.lib.awtextra.AbsoluteConstraints(213, 127, 100, -1));

        jPanel11.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel40.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel40.setText("PROPRETE");
        jLabel40.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel11.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 2, 366, -1));

        jLabel41.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel41.setText("Intérieure");
        jPanel11.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 28, -1, -1));

        interiere1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        interiere1.setText("oui");
        interiere1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                interiere1ActionPerformed(evt);
            }
        });
        jPanel11.add(interiere1, new org.netbeans.lib.awtextra.AbsoluteConstraints(116, 24, -1, -1));

        interiere0.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        interiere0.setText("non");
        interiere0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                interiere0ActionPerformed(evt);
            }
        });
        jPanel11.add(interiere0, new org.netbeans.lib.awtextra.AbsoluteConstraints(167, 24, -1, -1));

        interiered.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        interiered.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        jPanel11.add(interiered, new org.netbeans.lib.awtextra.AbsoluteConstraints(222, 27, 90, -1));

        jLabel42.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel42.setText("Extérieure");
        jPanel11.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 51, -1, -1));

        exteriere1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        exteriere1.setText("oui");
        exteriere1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exteriere1ActionPerformed(evt);
            }
        });
        jPanel11.add(exteriere1, new org.netbeans.lib.awtextra.AbsoluteConstraints(116, 47, -1, -1));

        exteriere0.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        exteriere0.setText("non");
        exteriere0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exteriere0ActionPerformed(evt);
            }
        });
        jPanel11.add(exteriere0, new org.netbeans.lib.awtextra.AbsoluteConstraints(168, 47, -1, -1));

        exteriered.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        exteriered.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        jPanel11.add(exteriered, new org.netbeans.lib.awtextra.AbsoluteConstraints(223, 50, 90, -1));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        immat.setEditable(false);
        immat.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        immat.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), "Nbr. Plate", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        nom.setEditable(false);
        nom.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        nom.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), "Nom", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        descr.setEditable(false);
        descr.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        descr.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), "Descr.", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        type.setEditable(false);
        type.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        type.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), "Type", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(immat)
                    .addComponent(nom)
                    .addComponent(descr)
                    .addComponent(type))
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(immat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(nom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(descr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(type, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jComboBox1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jComboBox1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Select One Engin", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        jComboBox1.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                jComboBox1PopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        km.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        km.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), "KM", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        jDateChooser3.setEditable(false);
        jDateChooser3.setEnabled(false);
        jDateChooser3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("Last Check In Date");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("Date");

        jDateChooser2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        kms.setEditable(false);
        kms.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        kms.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), "KMS", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(km)
                            .addComponent(jLabel1)
                            .addComponent(jDateChooser2, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel2)
                                    .addComponent(jDateChooser3, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(kms))))
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jComboBox1)
                .addGap(18, 18, 18)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(km, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(kms, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jDateChooser3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setText("Save");
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
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
saveinbord();        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void exteriere0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exteriere0ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_exteriere0ActionPerformed

    private void exteriere1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exteriere1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_exteriere1ActionPerformed

    private void interiere0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_interiere0ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_interiere0ActionPerformed

    private void interiere1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_interiere1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_interiere1ActionPerformed

    private void pneureserve1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pneureserve1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pneureserve1ActionPerformed

    private void freinamain1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_freinamain1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_freinamain1ActionPerformed

    private void frien1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_frien1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_frien1ActionPerformed

    private void paraprise1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_paraprise1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_paraprise1ActionPerformed

    private void seinture1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seinture1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_seinture1ActionPerformed

    private void claxion1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_claxion1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_claxion1ActionPerformed

    private void phare1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_phare1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_phare1ActionPerformed

    private void clignotant1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clignotant1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_clignotant1ActionPerformed

    private void feuxstop1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_feuxstop1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_feuxstop1ActionPerformed

    private void feuxdepossition1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_feuxdepossition1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_feuxdepossition1ActionPerformed

    private void girophare0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_girophare0ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_girophare0ActionPerformed

    private void immatriculation0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_immatriculation0ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_immatriculation0ActionPerformed

    private void retroviseurs0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_retroviseurs0ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_retroviseurs0ActionPerformed

    private void seeuisglase0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seeuisglase0ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_seeuisglase0ActionPerformed

    private void vitre0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vitre0ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_vitre0ActionPerformed

    private void cole0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cole0ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cole0ActionPerformed

    private void arriere0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_arriere0ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_arriere0ActionPerformed

    private void avant0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_avant0ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_avant0ActionPerformed

    private void batterie0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_batterie0ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_batterie0ActionPerformed

    private void liquidefreins0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_liquidefreins0ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_liquidefreins0ActionPerformed

    private void liquidefreins1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_liquidefreins1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_liquidefreins1ActionPerformed

    private void glase0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_glase0ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_glase0ActionPerformed

    private void refroidisement0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refroidisement0ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_refroidisement0ActionPerformed

    private void huille0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_huille0ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_huille0ActionPerformed

    private void jComboBox1PopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_jComboBox1PopupMenuWillBecomeInvisible
select_combobox();  
refiling_and_km();// TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1PopupMenuWillBecomeInvisible

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jMenu2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu2MouseClicked
this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu2MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JRadioButton arriere0;
    public static javax.swing.JRadioButton arriere1;
    public static javax.swing.JTextField arriered;
    public static javax.swing.JRadioButton avant0;
    public static javax.swing.JRadioButton avant1;
    public static javax.swing.JTextField avantd;
    public static javax.swing.JRadioButton batterie0;
    public static javax.swing.JRadioButton batterie1;
    public static javax.swing.JTextField batteried;
    private javax.swing.ButtonGroup buttonGroup1;
    public static javax.swing.JRadioButton claxion0;
    public static javax.swing.JRadioButton claxion1;
    public static javax.swing.JTextField claxiond;
    public static javax.swing.JRadioButton clignotant0;
    public static javax.swing.JRadioButton clignotant1;
    public static javax.swing.JTextField clignotantd;
    public static javax.swing.JRadioButton cole0;
    public static javax.swing.JRadioButton cole1;
    public static javax.swing.JTextField coled;
    private javax.swing.JTextField descr;
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
    public static final javax.swing.JTextField huilled = new javax.swing.JTextField();
    private javax.swing.JTextField immat;
    public static javax.swing.JRadioButton immatriculation0;
    public static javax.swing.JRadioButton immatriculation1;
    public static javax.swing.JTextField immatriculationd;
    public static javax.swing.JRadioButton interiere0;
    public static javax.swing.JRadioButton interiere1;
    public static javax.swing.JTextField interiered;
    public static javax.swing.JComboBox<String> jComboBox1;
    private com.alee.extended.date.WebDateField jDateChooser2;
    private com.alee.extended.date.WebDateField jDateChooser3;
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
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JTextField km;
    private javax.swing.JTextField kms;
    public static javax.swing.JRadioButton liquidefreins0;
    public static javax.swing.JRadioButton liquidefreins1;
    public static javax.swing.JTextField liquidefreinsd;
    private javax.swing.JTextField nom;
    public static javax.swing.JRadioButton paraprise0;
    public static javax.swing.JRadioButton paraprise1;
    public static javax.swing.JTextField paraprised;
    public static javax.swing.JRadioButton phare0;
    public static javax.swing.JRadioButton phare1;
    public static javax.swing.JTextField phared;
    public static javax.swing.JRadioButton pneureserve0;
    public static javax.swing.JRadioButton pneureserve1;
    public static javax.swing.JTextField pneureserved;
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
    private javax.swing.JTextField type;
    public static javax.swing.JRadioButton vitre0;
    public static javax.swing.JRadioButton vitre1;
    public static javax.swing.JTextField vitred;
    private com.alee.extended.date.WebDateField webDateField1;
    // End of variables declaration//GEN-END:variables
}
