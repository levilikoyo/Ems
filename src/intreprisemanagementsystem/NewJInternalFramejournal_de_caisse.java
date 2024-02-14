/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intreprisemanagementsystem;


import static intreprisemanagementsystem.Numero_Trans.rolls;
import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
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
public class NewJInternalFramejournal_de_caisse extends javax.swing.JInternalFrame {

   Connection con=null;
PreparedStatement pst=null;
ResultSet rs= null;
 DefaultTableModel mode;
  public static String rolls;
 Double CHECK_BUDGET;
  String mois;
   Timer timer;
    public NewJInternalFramejournal_de_caisse() {
        initComponents();
              con=JavaDbConnect.dbConnect();
              
         this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI bui= (BasicInternalFrameUI) this.getUI();
       bui.setNorthPane(null);
                if(doll3.getText().isEmpty()){doll3.setText("0");}
         if(used.getText().isEmpty()){used.setText("0");}
         
         
        // jTable1.setEnabled(false);
   //  setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("icons8_Raspberry_Pi_48px_1.png")));
attCall_IN_LIST();   

       // showTableData();
   
       
       currency();
       Groupe1();
        exercice();
        
        date_now();
        fc1.setEnabled(false);
        sh1.setEnabled(false);
        doll1.setEnabled(false);
           fc2.setEnabled(false);
        sh2.setEnabled(false);
        doll2.setEnabled(false);
        jButton1.setEnabled(false);
         jComboBox5.setEnabled(false);//jComboBox4
          jComboBox4.setEnabled(false);//jComboBox4
          
          business.setEnabled(false);
          
         
          
      
        // if(doll3.getText().isEmpty()){doll3.setText("0");}
    
        
        
        
     //   titre.setHorizontalAlignment( titre.CENTER);
  
        
        jComboBox1.setSelectedItem("CURRENCY");
        jComboBox1.addItem("CURRENCY");
        jComboBox1.addItem("USD");
        jComboBox1.addItem("USH");
        jComboBox1.addItem("FC");
        
        //jComboBox2.setSelectedItem("TRANSA.");
       // jComboBox2.addItem("TRANSA.");
        jComboBox2.addItem("DEBIT");
        jComboBox2.addItem("CREDIT");//ENTREE
        jComboBox3.addItem("Select One Project");
        
        entre.setText("0");
        sorti.setText("0");
         
        entre.setEnabled(false);
        sorti.setEnabled(false);
        
        
       // sorti.setBorder(/*border*/);
        //iflabel();
       //projet_NAME_TO_BOMBOBOX3();
       callcaisebuss();
                
    }
      public void callcaisebuss()
    {
       
        
         Double Debit;
         Double Credit;
         Double c;
         String e;
        try{
            String sql="SELECT sum(debit),sum(credit) FROM   ohada_trans WHERE CODE_M='57'";
          
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
               Debit=rs.getDouble("sum(debit)");
               Credit=rs.getDouble("sum(credit)");
               c=Debit-Credit;
                NumberFormat nf = new DecimalFormat("#,###,###");
String fn = nf.format(c);
String fn1 = nf.format(Debit);
String fn2 = nf.format(Credit);
           //doll3.setText(fn1);
          /// used.setText(fn2);
         //  doll3.setText(fn);
         
          doll3.setText(""+c);
           used.setText(""+Debit);
         //  doll3.setText(fn);
           
               
               
               
            }
         //   con.close();
            }
        
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
    
    }
      
       public void callcaisebussS()
    {
       
        
         Double Debit;
         Double Credit;
         Double c;
         String e;
        try{
            String sql="SELECT sum(debit),sum(credit) FROM   ohada_trans WHERE CODE='"+caissecode.getText()+"'";
          
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
               Debit=rs.getDouble("sum(debit)");
               Credit=rs.getDouble("sum(credit)");
               c=Debit-Credit;
                NumberFormat nf = new DecimalFormat("#,###,###");
String fn = nf.format(c);
String fn1 = nf.format(Debit);
String fn2 = nf.format(Credit);
           doll3.setText(fn1);
           used.setText(fn2);
           doll3.setText(fn);
           
               
               
               
            }
         //   con.close();
            }
        
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
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
    
  
    
    public void exercice(){
   //  String aa="";
        String a="01" ;
        String b="02" ;
        String c="03" ;
        String d="04" ;
        String e="05" ;
        String f="06" ;
        String g="07" ;
        String h="08" ;
        String i="09" ;
        String j="10" ;
        String k="11";
        String l="12";
        
SimpleDateFormat dateFormats = new SimpleDateFormat("MM");
String addDate = dateFormats.format(new Date());
         
      
        
       
        if(addDate.equals(a)){
        january.setBackground(Color.green) ;
        mois="Janvier";
        }else if(addDate.equals(b)){
        february.setBackground(Color.green) ;
        mois="Fevrier";
        }else if(addDate.equals(c)){
        march.setBackground(Color.green) ;
        mois="Mars";
        }else if(addDate.equals(d)){
        april.setBackground(Color.green) ;
        mois="Avril";
        }else if(addDate.equals(e)){
        may.setBackground(Color.green) ;
        mois="Mais";
        }else if(addDate.equals(f)){
        june.setBackground(Color.green) ;
        mois="Juin";
        }else if(addDate.equals(g)){
        july.setBackground(Color.green) ;
        mois="Juillet";
        }else if(addDate.equals(h)){
        august.setBackground(Color.green) ;
        mois="Aout";
        }else if(addDate.equals(i)){
        september.setBackground(Color.green) ;
        mois="Septembre";
        }else if(addDate.equals(j)){
        october.setBackground(Color.green) ;
        mois="Octombre";
        }else if(addDate.equals(k)){
        november.setBackground(Color.green) ;
        mois="Novembre";
        }else if(addDate.equals(l)){
        december.setBackground(Color.green) ;
        mois="Decembre";
    }
    
    }
    
    public void date_now()
    {
       jDateChooser1.setDate(new Date());
      
    }
    
    
     
         public void if_combo()
         {
             if(jComboBox2.getSelectedItem().equals("CREDIT")){
           sorti.setEnabled(true);
             entre.setEnabled(false);
             entre.setText("0");
             sorti.setText("0");
        
         }else if(jComboBox2.getSelectedItem().equals("DEBIT")) {
              entre.setEnabled(true);
        sorti.setEnabled(false);  
        sorti.setText("0");
        entre.setText("0");
         jComboBox5.setEnabled(true); 
             }else{
             entre.setEnabled(false);
        sorti.setEnabled(false);
        jComboBox5.setEnabled(false);
         sorti.setText("0");
        entre.setText("0");
         }
     }
         public void iflabel()
         {
          if(doll3.getText().equals("")){ 
            doll3.setText("0");  
          } else if(used.getText().equals("")){
             used.setText("0"); 
          } 
         }
         
          //CONNECTION
     public static java.sql.Connection getConnection()
    {
        Connection con = null;
        try{
             con= DriverManager.getConnection("jdbc:mysql://localhost/entreprise_management_system","root","");
              // JOptionPane.showMessageDialog(null,"connected");
             return con;
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,"not connected");
           
        }
         return null;
    }
         //SORTIE
         public void calulation()
         {
             float a = Float.parseFloat(sorti.getText());
             float b = Float.parseFloat(fc1.getText());
             float c;
        
             String d;
      
             c=a*b;
             d= String.format("%.2f",c);
           // fc3.setText(d);
            fc.setText(d);
             
         }
        public void calulation1()
         {
             float b = Float.parseFloat(fc1.getText());
            float d = Float.parseFloat(fc.getText());
             float e;
             
            String a;
       
            
           e=d/b;
           a= String.format("%.2f",e);
          // doll3.setText(a); 
           doll.setText(a); 
         }
          public void calulation2()
         {
             float a = Float.parseFloat(sh1.getText());
            float b = Float.parseFloat(doll.getText());
            float c;// = Math.floor(a*b *100.0) / 100.0;
            
            String d;
            c=a*b;
            d= String.format("%.2f",c);
           // sh3.setText(d);
            sh.setText(d);
         }
          
          
           public void calulation3()
         {
             float a = Float.parseFloat(sorti.getText());
            float b = Float.parseFloat(doll1.getText());
            float c;
            String d;
            c=a/b;
            d= String.format("%.2f",c);
            //doll3.setText(d);
            doll.setText(d);
         }
               public void calulation4()
         {
             float a = Float.parseFloat(doll.getText());
            float b = Float.parseFloat(fc1.getText());
            float c;
            String d;
            c=a*b;
            d= String.format("%.2f",c);
            //fc3.setText(d);
            fc.setText(d);
         }
                 public void calulation5()
         {
             float a = Float.parseFloat(sh1.getText());
            float b = Float.parseFloat(doll.getText());
            float c;// = Math.floor(a*b *100.0) / 100.0;
            
            String d;
            c=a*b;
            d= String.format("%.2f",c);
            //sh3.setText(d);
            sh.setText(d);
         }
                 
                 
           public void calulation6()
         {
             float a = Float.parseFloat(sorti.getText());
            float b = Float.parseFloat(sh1.getText());
            float c;
            String d;
            c=a/b;
            d= String.format("%.2f",c);
            //doll3.setText(d);
            doll.setText(d);
         }
               public void calulation7()
         {
             float a = Float.parseFloat(doll.getText());
            float b = Float.parseFloat(fc1.getText());
            float c;
            String d;
            c=a*b;
            d= String.format("%.2f",c);
           // fc3.setText(d);
            fc.setText(d);
         }
                 public void calulation8()
         {
             float a = Float.parseFloat(doll.getText());
            float b = Float.parseFloat(sh1.getText());
            double c;  // = Math.floor(a*b *100.0) / 100.0;
            
            String d;
            c=a*b;
            d= String.format("%.2f",c);
           // sh3.setText(d);
            sh.setText(d);
         }
                 
                 //ENTREE
        
         public void calulationS()
         {
             float a = Float.parseFloat(entre.getText());
             float b = Float.parseFloat(fc1.getText());
             float c;
        
             String d;
      
             c=a*b;
             d= String.format("%.2f",c);
           // fc3.setText(d);
            fc.setText(d);
             
         }
        public void calulationS1()
         {
             float b = Float.parseFloat(fc1.getText());
            float d = Float.parseFloat(fc.getText());
             float e;
             
            String a;
       
            
           e=d/b;
           a= String.format("%.2f",e);
           //doll3.setText(a); 
           doll.setText(a); 
         }
          public void calulationS2()
         {
             float a = Float.parseFloat(sh1.getText());
            float b = Float.parseFloat(doll.getText());
            float c;// = Math.floor(a*b *100.0) / 100.0;
            
            String d;
            c=a*b;
            d= String.format("%.2f",c);
            //sh3.setText(d);
            sh.setText(d);
         }
          
          
           public void calulationS3()
         {
             float a = Float.parseFloat(entre.getText());
            float b = Float.parseFloat(doll1.getText());
            float c;
            String d;
            c=a/b;
            d= String.format("%.2f",c);
            //doll3.setText(d);
            doll.setText(d);
         }
               public void calulationS4()
         {
             float a = Float.parseFloat(doll.getText());
            float b = Float.parseFloat(fc1.getText());
            float c;
            String d;
            c=a*b;
            d= String.format("%.2f",c);
            //fc3.setText(d);
            fc.setText(d);
         }
                 public void calulationS5()
         {
             float a = Float.parseFloat(sh1.getText());
            float b = Float.parseFloat(doll.getText());
            float c;// = Math.floor(a*b *100.0) / 100.0;
            
            String d;
            c=a*b;
            d= String.format("%.2f",c);
            //sh3.setText(d);
            sh.setText(d);
         }
                 
                 
           public void calulationS6()
         {
             float a = Float.parseFloat(entre.getText());
            float b = Float.parseFloat(sh1.getText());
            float c;
            String d;
            c=a/b;
            d= String.format("%.2f",c);
           // doll3.setText(d);
            doll.setText(d);
         }
               public void calulationS7()
         {
             float a = Float.parseFloat(doll.getText());
            float b = Float.parseFloat(fc1.getText());
            float c;
            String d;
            c=a*b;
            d= String.format("%.2f",c);
           // fc3.setText(d);
            fc.setText(d);
         }
                 public void calulationS8()
         {
             float a = Float.parseFloat(doll.getText());
            float b = Float.parseFloat(sh1.getText());
            double c;  // = Math.floor(a*b *100.0) / 100.0;
            
            String d;
            c=a*b;
            d= String.format("%.2f",c);
           // sh3.setText(d);
            sh.setText(d);
         }
       public void comboboxsortie()
 {
if(jComboBox1.getSelectedItem().equals("USD")){
  calulation();
calulation1();
calulation2();
}else if(jComboBox1.getSelectedItem().equals("FC")){
    calulation3();
    calulation4();
calulation5();
}else if(jComboBox1.getSelectedItem().equals("USH")){
  calulation6();
    calulation7();
calulation8();  
}
               }
              public void comboboxentre()
 {
if(jComboBox1.getSelectedItem().equals("USD")){
  calulationS();
calulationS1();
calulationS2();
}else if(jComboBox1.getSelectedItem().equals("FC")){
    calulationS3();
    calulationS4();
calulationS5();
}else{
  calulationS6();
    calulationS7();
calulationS8();  
}
               }
              
  Double sumcredit;  
   Double sumcredits;  
   
    Double caissesdebit; 
    // Double soldecaisses; 
public void call_sum_budget(){
Double a;
Double b;
Double aa;
Double ba;
Double c= Double.parseDouble(doll.getText());

 try{              //call from journala de banque  
            String sql="select SUM(DEBIT),SUM(CREDIT) from  budget_trans where CODE= '"+code.getText()+"' and projet='"+titre.getText()+"'";
           
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

//String mois;
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
              
           public void save_budget(){
             
 call_sum_budget();
        Double aaa= Double.parseDouble(doll.getText());
        Double ccc;
        ccc= sumcredit - aaa;
          Mois();
          try{
    String sql="INSERT INTO `budget_trans`(`ITEM`, `DEBIT`, `CREDIT`, `SOLD`, `PROJET`, `CODE`, `NUM`, `CAT`, `DATES`, `MOIS`, `ANNEE`,SUB_CAT) "+"values(?,?,?,?,?,?,?,?,?,?,?,?)";
    pst=con.prepareStatement(sql);
    pst.setString(1,libele.getText());
    pst.setString(2,"0");
    pst.setString(3,doll.getText());
    pst.setDouble(4, ccc);
    pst.setString(5,titre.getText());
    pst.setString(6,jList1.getSelectedValue());
    pst.setString(7,rolls);
    
    pst.setString(8,code.getText());
    
    pst.setString(9,jDateChooser1.getText());
    pst.setString(10,mois);
    pst.setString (11,annee);
    pst.setString (12,comptemere.getText());
   
    
    
    pst.executeUpdate();
    // JOptionPane.showMessageDialog(null,"BUDGET TRANS");
    }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}
    
            try {
        
        PreparedStatement pst = con.prepareStatement("UPDATE `budget_show` SET `CREDIT`='"+sumcredits+"',`SOLD`='"+ccc+"' WHERE code='"+code.getText()+"' and projet='"+titre.getText()+"'");
        
       
         
          pst.executeUpdate();
        
            //    JOptionPane.showMessageDialog(null,"BUDGET_UP");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());}
           
           }
              //SAVE
     
      public void savemateriaux()
              
    {
    
   etroll();
   budgetcheckS();
     Integer tmps1 = jYearChooser1.getValue();
        if(project.isSelected()){
        
        if(jComboBox2.getSelectedItem().equals("CREDIT")){
            String titres = titre.getText();
             String tit = cnom.getText();
            if(titres.isEmpty()){
                JOptionPane.showMessageDialog(null,"THE PROJECT IS NOT SELECTED PLEASE SELECT ONE");   
            } else if(tit.isEmpty()){
              JOptionPane.showMessageDialog(null,"THE ACCOUNT IS NOT SELECTED PLEASE SELECT ONE!!!!");
               
                
            }else if(caissecode.getText().isEmpty()){
              JOptionPane.showMessageDialog(null,"THE ACCOUNT IS NOT SELECTED PLEASE SELECT ONE (CAISSE)!!!!");
              }else if(libele.getText().isEmpty()){
              JOptionPane.showMessageDialog(null,"UNKNOWN LIBELLE!!!!");
                
                
            }else if(jComboBox1.getSelectedItem().equals("CURRENCY")){
              JOptionPane.showMessageDialog(null,"THE CURRENCY IS NOT SELECTED PLEASE SELECT ONE");
               
                
            }else if(Float.parseFloat(doll.getText()) >= Float.parseFloat(doll3.getText()) ){
                JOptionPane.showMessageDialog(null,"NO ENOUGH MONEY FOR THIS TRANSACTION!!!"); 
                doll.setText("0");
            }else if(Float.parseFloat(doll.getText()) >= Float.parseFloat(coutin1.getText()) ){
               
                JOptionPane.showMessageDialog(null,"THE PROJECT DOES NOT HAVE ENOUGH BUGDET FOR THIS TRANSACTION!!!");    
            }else if(Float.parseFloat(doll.getText()) > Float.parseFloat(CHECK_BUDGET.toString()) ){
               
                JOptionPane.showMessageDialog(null,"THIS LINE DOES NOT HAVE ENOUGH BUGDET FOR THIS TRANSACTION!!!");    
            }else {
                
               coutin_out();
               calulateEX();
              
    
        // OHADA ACCOUNT
        
        //========>>>>DEBIT
        ///INSERT INTO `ohada_trans`(`ID`, `COMPTE_M`, `COMPTE`, `CODE_M`, `CODE`, `CLASSE`, `SUBSTR`, `DEBIT`, `CREDIT`, `NUM`, `DATES`, `BUSS`, `LIBELLE`, `JOURNAL`)
         try {
         String tmp =jComboBox3.getSelectedItem().toString();
         Connection con = getConnection();
        PreparedStatement pst = con.prepareStatement("INSERT INTO `ohada_trans`(`COMPTE_M`, `COMPTE`, `CODE_M`, `CODE`, `CLASSE`, `SUBSTR`, `DEBIT`, `CREDIT`, `NUM`, `DATES`, `BUSS`, `LIBELLE`, `JOURNAL`, `CODE1`)"
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        
        pst.setString(1, comptemere1.getText());
        pst.setString(2, cnom1.getText());
         pst.setString(3, codemere1.getText());
         pst.setString(4, code1.getText());
         pst.setString(5, classe1.getText());
         pst.setString(6, "");
         pst.setString(7,doll.getText());
         pst.setString(8,"");
         pst.setString(9,rolls);
         
       SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(jDateChooser1.getDate());
         pst.setString(10, addDate);
         pst.setString(11,titre.getText());
         pst.setString(12,libele.getText());
         pst.setString(13,"[JC]");
          pst.setString(14,cnom.getText());
       
          
         
          pst.executeUpdate();
        
           //      JOptionPane.showMessageDialog(null,"Caisse saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
      
           //========>>>>CREDIT
        ///INSERT INTO `ohada_trans`(`ID`, `COMPTE_M`, `COMPTE`, `CODE_M`, `CODE`, `CLASSE`, `SUBSTR`, `DEBIT`, `CREDIT`, `NUM`, `DATES`, `BUSS`, `LIBELLE`, `JOURNAL`)
         try {
         String tmp =jComboBox3.getSelectedItem().toString();
         Connection con = getConnection();
        PreparedStatement pst = con.prepareStatement("INSERT INTO `ohada_trans`(`COMPTE_M`, `COMPTE`, `CODE_M`, `CODE`, `CLASSE`, `SUBSTR`, `DEBIT`, `CREDIT`, `NUM`, `DATES`, `BUSS`, `LIBELLE`, `JOURNAL`, `CODE1`)"
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        
        pst.setString(1, pcomptem.getText());
        pst.setString(2, pcnom.getText());
         pst.setString(3, pcodem.getText());
         pst.setString(4, caissecode.getText());
         pst.setString(5, pclasse.getText());
         pst.setString(6, "");
         pst.setString(7,"");
         pst.setString(8,doll.getText());
         pst.setString(9,rolls);
         
       SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(jDateChooser1.getDate());
         pst.setString(10, addDate);
         pst.setString(11,titre.getText());
         pst.setString(12,libele.getText());
         pst.setString(13,"[JC]");
          pst.setString(14,cnom1.getText());
       
          
         
          pst.executeUpdate();
        
               //  JOptionPane.showMessageDialog(null,"Caisse saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
        
        call_sum_budget();
        Double aaa= Double.parseDouble(doll.getText());
        Double ccc;
        ccc= sumcredit - aaa;
       
         // BUSNESS ACOUNT
         
         try{  
             Double xxx;
Double yyy;


//call from journala de banque  
            String sql="select SUM(DEBIT),SUM(CREDIT) from caisses where buss='"+titre.getText()+"'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                xxx=rs.getDouble("SUM(DEBIT)");
               // entre.setText(""+xxx);
                yyy=rs.getDouble("SUM(CREDIT)");
                  
                   caissesdebit=(xxx-yyy)-aaa;
                //   sorti.setText(""+yyy);
            }
            }catch(Exception ex){ JOptionPane.showMessageDialog(null, ex);} 

         
         
         
         
         
         try {
         String tmp =jComboBox3.getSelectedItem().toString();
         Connection con = getConnection();
        PreparedStatement pst = con.prepareStatement("INSERT INTO `caisses`(`CODE`,`CODEMERE`,`LIBELE`,`BUSS`,`DEBIT`,`CREDIT`,`DATE`,`COMPTE`,NUM,`MOIS`, `ANNEE`, `BUDGET`, `SOLDE`)"
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?)");
        
        pst.setString(1, code1.getText());
         pst.setString(2, codemere1.getText());
         pst.setString(3, libele.getText());
         pst.setString(4, titre.getText());
         pst.setString(5, "0");
         pst.setString(6,doll.getText());
         pst.setString(8,pcnom.getText());
         pst.setString(9,rolls);
         
         pst.setString(10,mois);
         pst.setInt(11,jYearChooser1.getValue());
         pst.setString(12,code.getText());
         pst.setDouble(13,caissesdebit);
       
          SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(jDateChooser1.getDate());
         pst.setString(7, addDate);
         
          pst.executeUpdate();
        
                 JOptionPane.showMessageDialog(null,"Caisse saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
         ////>>>>>>BUDGE
         save_budget(); 
            }
           
 
    }else{
            //
                     Double aaa=Double.parseDouble(doll.getText());
             //  Double caissesdebit = null;
            try{  
             Double xxx;
Double yyy;


//call from journala de banque  
            String sql="select SUM(DEBIT),SUM(CREDIT) from ohada_trans where code='"+code1.getText()+"'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                xxx=rs.getDouble("SUM(DEBIT)");
               // entre.setText(""+xxx);
                yyy=rs.getDouble("SUM(CREDIT)");
                  
                   caissesdebit=(xxx-yyy);
                 
            }
            }catch(Exception ex){ JOptionPane.showMessageDialog(null, ex);}
            
            if(caissesdebit < aaa){
            JOptionPane.showMessageDialog(null,"Wrong Data"+"\n"+"No Enough Resources","Error",JOptionPane.ERROR_MESSAGE);
            }else{  
            
            String tit = caissecode.getText();
           
           // String titres = titre.getText();
            if(jComboBox1.getSelectedItem().equals("CURRENCY")){
              JOptionPane.showMessageDialog(null,"THE CURRENCY IS NOT SELECTED PLEASE SELECT ONE");     
            }else if(tit.isEmpty()){
              JOptionPane.showMessageDialog(null,"THE ACCOUNT IS NOT SELECTED PLEASE SELECT ONE!!!!");
               
            } else if(libele.getText().isEmpty()){
              JOptionPane.showMessageDialog(null,"UNKNOWN LIBELLE!!!!");
                
            }else{
             calulateEX();
          Double xxx;
                   Double yyy;
                 //  Double aaa= Double.parseDouble(doll.getText());
            try{
               //call from journala de banque  
            String sql="select SUM(DEBIT),SUM(CREDIT) from  caisses where BUSS='"+titre.getText()+"'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                xxx=rs.getDouble("SUM(DEBIT)");
                yyy=rs.getDouble("SUM(CREDIT)");
                  
                   caissesdebit=(xxx-yyy)+aaa;
            }
            }catch(Exception ex){ JOptionPane.showMessageDialog(null, ex);} 


        
        
        try {
         Connection con = getConnection();
        PreparedStatement pst = con.prepareStatement("INSERT INTO `caisses`(`CODE`,`CODEMERE`,`LIBELE`,`BUSS`,`DEBIT`,`CREDIT`,`DATE`,`COMPTE`,`NUM`,`MOIS`, `ANNEE`, `BUDGET`,`SOLDE`)"
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?)");
        
        pst.setString(1, code1.getText());
         pst.setString(2, codemere1.getText());
         pst.setString(3, libele.getText());
         pst.setString(4, titre.getText());
         pst.setString(5, doll.getText());
         pst.setString(6,"0");
         pst.setString(8,"0");
         pst.setString(9,rolls);
         
         pst.setString(10,mois);
         pst.setInt(11,jYearChooser1.getValue());
         pst.setString(12,code.getText());
         pst.setDouble(13,caissesdebit);
       
          SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(jDateChooser1.getDate());
         pst.setString(7, addDate);
         
          pst.executeUpdate();
        
               JOptionPane.showMessageDialog(null,"data saved1");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
        
        
        
     
             
                  
          // OHADA ACCOUNT
        
        //========>>>>CREDIT
        ///INSERT INTO `ohada_trans`(`ID`, `COMPTE_M`, `COMPTE`, `CODE_M`, `CODE`, `CLASSE`, `SUBSTR`, `DEBIT`, `CREDIT`, `NUM`, `DATES`, `BUSS`, `LIBELLE`, `JOURNAL`)
         try {
         String tmp =jComboBox3.getSelectedItem().toString();
         Connection con = getConnection();
        PreparedStatement pst = con.prepareStatement("INSERT INTO `ohada_trans`(`COMPTE_M`, `COMPTE`, `CODE_M`, `CODE`, `CLASSE`, `SUBSTR`, `DEBIT`, `CREDIT`, `NUM`, `DATES`, `BUSS`, `LIBELLE`, `JOURNAL`, `CODE1`)"
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        
        pst.setString(1, comptemere1.getText());
        pst.setString(2, cnom1.getText());
         pst.setString(3, codemere1.getText());
         pst.setString(4, code1.getText());
         pst.setString(5, classe1.getText());
         pst.setString(6, "");
         pst.setString(7,"");
         pst.setString(8,doll.getText());
         pst.setString(9,rolls);
         
       SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(jDateChooser1.getDate());
         pst.setString(10, addDate);
         pst.setString(11,titre.getText());
         pst.setString(12,libele.getText());
         pst.setString(13,"[JC]");
          pst.setString(14,cnom.getText());
       
          
         
          pst.executeUpdate();
        
                JOptionPane.showMessageDialog(null,"Caisse saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
      
           //========>>>>DEBIT
        ///INSERT INTO `ohada_trans`(`ID`, `COMPTE_M`, `COMPTE`, `CODE_M`, `CODE`, `CLASSE`, `SUBSTR`, `DEBIT`, `CREDIT`, `NUM`, `DATES`, `BUSS`, `LIBELLE`, `JOURNAL`)
         try {
         String tmp =jComboBox3.getSelectedItem().toString();
         Connection con = getConnection();
        PreparedStatement pst = con.prepareStatement("INSERT INTO `ohada_trans`(`COMPTE_M`, `COMPTE`, `CODE_M`, `CODE`, `CLASSE`, `SUBSTR`, `DEBIT`, `CREDIT`, `NUM`, `DATES`, `BUSS`, `LIBELLE`, `JOURNAL`, `CODE1`)"
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        
        pst.setString(1, pcomptem.getText());
        pst.setString(2, pcnom.getText());
         pst.setString(3, pcodem.getText());
         pst.setString(4, caissecode.getText());
         pst.setString(5, pclasse.getText());
         pst.setString(6, "");
         pst.setString(7,doll.getText());
         pst.setString(8,"");
         pst.setString(9,rolls);
         
       SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(jDateChooser1.getDate());
         pst.setString(10, addDate);
         pst.setString(11,titre.getText());
         pst.setString(12,libele.getText());
         pst.setString(13,"[JC]");
          pst.setString(14,cnom1.getText());
       
          
         
          pst.executeUpdate();
        
                 JOptionPane.showMessageDialog(null,"Transaction  saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
           combobox1_from_materiel();             
            }
            
            }  
        
        }
        
       
    }else{
        
        
           if(jComboBox2.getSelectedItem().equals("CREDIT")){
            String titres = titre.getText();
             String tit = cnom.getText();
            if(titres.isEmpty()){
                JOptionPane.showMessageDialog(null,"THE BUSINESS IS NOT SELECTED PLEASE SELECT ONE");   
            } else if(tit.isEmpty()){
              JOptionPane.showMessageDialog(null,"THE ACCOUNT IS NOT SELECTED PLEASE SELECT ONE!!!!");
               
                
            }else if(jComboBox1.getSelectedItem().equals("CURRENCY")){
              JOptionPane.showMessageDialog(null,"THE CURRENCY IS NOT SELECTED PLEASE SELECT ONE");
               
                
            }else if(Float.parseFloat(doll.getText()) >= Float.parseFloat(doll3.getText()) ){
                JOptionPane.showMessageDialog(null,"NO ENOUGH MONEY FOR THIS TRANSACTION!!!"); 
                doll.setText("0");
            }else if(Float.parseFloat(doll.getText()) >= Float.parseFloat(coutin1.getText()) ){
               
                JOptionPane.showMessageDialog(null,"THE BUSINESS DOES NOT HAVE ENOUGH BUGDET FOR THIS TRANSACTION!!!");    
            }else {
                
               coutin_out();
               calulateEX();
              
     
        
          // BUSNESS ACOUNT
         try {
         String tmp =jComboBox3.getSelectedItem().toString();
         Connection con = getConnection();
        PreparedStatement pst = con.prepareStatement("INSERT INTO `caisses`(`CODE`,`CODEMERE`,`LIBELE`,`BUSS`,`DEBIT`,`CREDIT`,`DATE`,`COMPTE`,NUM)"
        +"value(?,?,?,?,?,?,?,?,?)");
        
        pst.setString(1, code1.getText());
         pst.setString(2, codemere1.getText());
         pst.setString(3, libele.getText());
         pst.setString(4, titre.getText());
         pst.setString(5, "0");
         pst.setString(6,doll.getText());
         pst.setString(8,pcnom.getText());
         pst.setString(9,rolls);
       
          SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(jDateChooser1.getDate());
         pst.setString(7, addDate);
         
          pst.executeUpdate();
        
                 JOptionPane.showMessageDialog(null,"Caisse saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
            }
           }else{  //
            String aa = jComboBox5.getSelectedItem().toString(); 
            
            float a ;
            float b ;
            String d;
            
            
            float c=0 ;
            
                 try{              //call from journala de banque  
            String sql="select SUM(MOTANT_IN),SUM(MOTANT_OUT) from journal_de_bank where BANQUE= '"+aa+"'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                a=rs.getFloat("SUM(MOTANT_IN)");
                b=rs.getFloat("SUM(MOTANT_OUT)");
                  
                   c=a-b;
                  d= String.format("%.2f",c);
            //jTextField4.setText(d);
            }
            }catch(Exception ex){ JOptionPane.showMessageDialog(null, ex);} 
              
            
            String tit = cnom.getText();
           
            String titres = titre.getText();
            if(jComboBox1.getSelectedItem().equals("CURRENCY")){
              JOptionPane.showMessageDialog(null,"THE CURRENCY IS NOT SELECTED PLEASE SELECT ONE");     
            }else if(c <=Float.parseFloat(entre.getText()) ){
              JOptionPane.showMessageDialog(null,"THE SELECTED BANK DOES NOT HAVE ENOUGH PROVISSION");     
            }else if(tit.isEmpty()){
              JOptionPane.showMessageDialog(null,"THE ACCOUNT IS NOT SELECTED PLEASE SELECT ONE!!!!");
            }else if(titres.isEmpty()){
            JOptionPane.showMessageDialog(null,"NO BUSINESS IS NOT SELECTED PLEASE SELECT ONE!!!!");
               
                
            }else{
             calulateEX();
            try {
         Connection con = getConnection();
        PreparedStatement pst = con.prepareStatement("INSERT INTO `caisses`(`CODE`,`CODEMERE`,`LIBELE`,`BUSS`,`DEBIT`,`CREDIT`,`DATE`,`COMPTE`,`NUM`,`MOIS`, `ANNEE`, `BUDGET`, `SOLDE`)"
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?)");
        
        pst.setString(1, code1.getText());
         pst.setString(2, codemere1.getText());
         pst.setString(3, libele.getText());
         pst.setString(4, titre.getText());
         pst.setString(5, doll.getText());
         pst.setString(6,"0");
         pst.setString(8,"");
         pst.setString(9,rolls);
         
           pst.setString(10,mois);
             pst.setInt(11,jYearChooser1.getValue());
               pst.setString(12,"");
                 pst.setString(13,rolls);
       
          SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(jDateChooser1.getDate());
         pst.setString(7, addDate);
         
          pst.executeUpdate();
        
             //   JOptionPane.showMessageDialog(null,"data saved1");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
        try {
                            Double e =0.0;
                        //     etroll();
         Connection con = getConnection();//         INSERT INTO `journal_de_bank`(`TRANS_NUM`, `PROVENANCE`, `MOTIF`, `projet`, `MOTANT_IN`, `MOTANT_OUT`, `DATES`, `BANQUE`, `NUM`, `CODE`)
        PreparedStatement pst = con.prepareStatement("Insert into journal_de_bank (`TRANS_NUM`, `PROVENANCE`, `MOTIF`, `projet`, `MOTANT_IN`, `MOTANT_OUT`, `DATES`, `BANQUE`, `NUM`, `CODE`)"+"VALUE(?,?,?,?,?,?,?,?,?,?)");
        
        
        pst.setString(1, "");
         pst.setString(2, "To "+titre.getText());
         pst.setString(3, libele.getText());
       // String values=jComboBox3.getSelectedItem().toString();
         pst.setString(4,titre.getText());
         pst.setDouble(5, e);
         pst.setString(6, doll.getText());
         String value=jComboBox5.getSelectedItem().toString();
         pst.setString(8, value);
        
         pst.setString(9,rolls);
         pst.setString(10,"");
       
          SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(jDateChooser1.getDate());
         pst.setString(7, addDate);
         
          pst.executeUpdate();
        
                JOptionPane.showMessageDialog(null,"TRANSACTION SAVED");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    } 
             
            }   
           }
        
        }//radio button
    
    }
    
      
     
      
      public void attCall_IN_LISTsearch()
    {DefaultListModel list = new DefaultListModel();
       
       // DefaultListModel list = new DefaultListModel();
        
         
        try{
            String sql="SELECT * FROM  ohada  where CODE LIKE '"+jTextField1.getText()+"%'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
               // String sum=rs.getString("intitule_compte");
                String sums=rs.getString("code");
                 list.addElement(sums);
                
                 jList3.setModel(list);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
        
      
      }
      
      
      
      ///COMPTE OHADA
      
       public void attCall_IN_LIST()
    {DefaultListModel list = new DefaultListModel();
       
       // DefaultListModel list = new DefaultListModel();
        
         
        try{
            String sql="SELECT * FROM  ohada ";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
               // String sum=rs.getString("intitule_compte");
                String sums=rs.getString("code");
                 list.addElement(sums);
                
                 jList3.setModel(list);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
        
         try{
            String sql="SELECT * FROM  ohada  WHERE  CODEMERE='57'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
               // String sum=rs.getString("intitule_compte");
                String sums=rs.getString("NAME");
               
                jComboBox6.addItem(sums);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
         
    }
       
       //ONLY CAISSE
          public void attCall_IN_LIST1()
    {
       
        
        
        DefaultListModel list = new DefaultListModel();
        
         
        try{
            String sql="SELECT * FROM `OHADA` WHERE LEFT(CODE,2)='57'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                //String sum=rs.getString("nom");
                String sums=rs.getString("code");
                 list.addElement(sums);
                
                 jList1.setModel(list);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
    
    
    }
          
          
             public void budget_LIST1()
    {
        DefaultListModel list = new DefaultListModel();
        
         
        try{
            String sql="SELECT * FROM `budget_code` where CAT='"+titre.getText()+"'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                //String sum=rs.getString("nom");
                String sums=rs.getString("code");
                 list.addElement(sums);
                
                 jList1.setModel(list);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
    
    }
          
              public void budget_LIST1_search()
    {
        DefaultListModel list = new DefaultListModel();
        
         
        try{
            String sql="SELECT * FROM `budget_code` where  CODE like'"+jTextField4.getText()+"%' and CAT='"+titre.getText()+"'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                //String sum=rs.getString("nom");
                String sums=rs.getString("code");
                 list.addElement(sums);
                
                 jList1.setModel(list);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
    
    }
          
          
       
       
       
   
       
        public void lock_buton(){
         ActionListener actionListener = new  ActionListener(){
             @Override
             public void actionPerformed(ActionEvent e) {
           
             }
         };
         timer = new Timer(1000,actionListener); 
    
timer.start();

//To change body of generated methods, choose Tools | Templates.
             }

       
       
       
        //JTABLE
              public void select_jTable()
   {
       if(jComboBox3.getSelectedItem().equals("SELECTIONNE LE projet ICI!!!!*****SELECT PROJECT NAME HERE!!!!")){
       
        try{
          int row= jTable1.getSelectedRow();
         // String rows =jTable1.getName()
          String Table_click = (jTable1.getModel().getValueAt(row,0). toString());
          String sql = "SELECT * FROM JOURNAL WHERE id= '"+Table_click+"'";
          pst = con.prepareStatement(sql);
          rs=pst.executeQuery();
          if(rs.next()){
        
               String add11= rs.getString("NUM");
              journal_id.setText(add11);
          }
         
    }catch(SQLException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }
    //JOptionPane.showMessageDialog(null,"LE projet N'A PAS ETE SELECTIONNE");   
       
       }else{
       String tmp = jComboBox3.getSelectedItem().toString();
        try{
          int row= jTable1.getSelectedRow();
         // String rows =jTable1.getName()
          String Table_click = (jTable1.getModel().getValueAt(row,0). toString());
          String sql = "SELECT * FROM "+tmp+" WHERE id= '"+Table_click+"'";
          pst = con.prepareStatement(sql);
          rs=pst.executeQuery();
          if(rs.next()){
        
               String add11= rs.getString("NUMS");
              journal_id.setText(add11);
          }
         
    }catch(SQLException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }
       }
   }
       
       
       
       
       
 
    
   // DELETE
    public void delete()
    {
        if(jComboBox3.getSelectedItem().equals("SELECTIONNE LE projet ICI!!!!*****SELECT PROJECT NAME HERE!!!!")){
       
         try{
        String sql = "DELETE FROM journal WHERE NUM=?";
        
         pst = con.prepareStatement(sql);
         pst.setString(1,journal_id.getText());
         pst.executeUpdate();
          
              
     }catch(SQLException | HeadlessException ex ){
         JOptionPane.showMessageDialog(null,ex);
     } 
         try{
        String sql = "DELETE FROM  journal_de_bank WHERE NUM=?";
        
         pst = con.prepareStatement(sql);
         pst.setString(1,journal_id.getText());
         pst.executeUpdate();
     JOptionPane.showMessageDialog(null,"TRANSACTION DELETED");    //JOptionPane.showMessageDialog(null,"delete JOURNAL");
              
     }catch(SQLException | HeadlessException ex ){
         JOptionPane.showMessageDialog(null,ex);
     } 
        }else{
             String tmp = jComboBox3.getSelectedItem().toString();
              try{
        String sql = "DELETE FROM journal WHERE NUM=?";
        
         pst = con.prepareStatement(sql);
         pst.setString(1,journal_id.getText());
       //  JOptionPane.showMessageDialog(null,"delete JOURNAL");  
         pst.executeUpdate();

              
     }catch(SQLException | HeadlessException ex ){
         JOptionPane.showMessageDialog(null,ex);
     } 
         try{
        String sql = "DELETE FROM  autres_achats WHERE NUM=?";
        
         pst = con.prepareStatement(sql);
         pst.setString(1,journal_id.getText());
         pst.executeUpdate();
      //   JOptionPane.showMessageDialog(null,"delete AUTRES ACHAT");
              
     }catch(SQLException | HeadlessException ex ){
         JOptionPane.showMessageDialog(null,ex);
     } 
         
         try{
        String sql = "DELETE FROM  backupprojet WHERE NUMS=?";
        
         pst = con.prepareStatement(sql);
        pst.setString(1,journal_id.getText());
         pst.executeUpdate();
      //  JOptionPane.showMessageDialog(null,"delete BACKUP PROJECT");
              
     }catch(SQLException | HeadlessException ex ){
         JOptionPane.showMessageDialog(null,ex);
     } 
         
         try{
        String sql = "DELETE FROM "+tmp+" WHERE NUMS=?";
        
         pst = con.prepareStatement(sql);
         pst.setString(1,journal_id.getText());
         pst.executeUpdate();
         JOptionPane.showMessageDialog(null,"TRANSACTION DELETED");
              
     }catch(SQLException | HeadlessException ex ){
         JOptionPane.showMessageDialog(null,ex);
     } 
        }  
    }
    
    public void recycle_bin(){
    
    
      try {
          String libelle="";
          Float entre = null;
          Float doll= null;
          Float dolls= null;
          Float fc= null;
          Float sh= null;
          Float used= null;
          String dates ="";
         String titre="";
          String projetid="";
          Float doll3= null;
          String rolls="";
         
         try{
        String sql = "select * FROM journal WHERE NUM='"+journal_id.getText()+"'";
        
        pst = con.prepareStatement(sql);
          rs=pst.executeQuery();
       
         if(rs.next()){
        
                libelle= rs.getString("LIBELLE");
                entre= rs.getFloat("ENTRE");
                doll= rs.getFloat("SORTIE");
                dolls= rs.getFloat("USD");
                fc= rs.getFloat("FC");
                sh= rs.getFloat("SH");
                used= rs.getFloat("USED");
                dates= rs.getString("DATES");
                 titre= rs.getString("projet");
                  projetid= rs.getString("POJECT_ID");
                doll3= rs.getFloat("USD1");
                 rolls= rs.getString("NUM");
          }
              
     }catch(SQLException | HeadlessException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }   
          
          
          
Connection con = getConnection();
PreparedStatement pst = con.prepareStatement("INSERT INTO recycle_bin (LIBELLE,ENTRE,SORTIE,USD,FC,SH,USED,DATES,projet,PROJECT_ID,USD1,NUM) "
+"value(?,?,?,?,?,?,?,?,?,?,?,?)");

        pst.setString(1, libelle);
         pst.setFloat(2, entre);
         pst.setFloat(3, doll);
         pst.setFloat(4, dolls);
         pst.setFloat(5, fc);
          pst.setFloat(6, sh);
         pst.setFloat(7, used);
         pst.setString(8, dates);
         pst.setString(9, titre);
         pst.setString(10, projetid);//
         pst.setFloat(11, doll3);
         pst.setString(12,rolls);
       
         
         // pst.executeUpdate();
          pst.executeUpdate();
        
               //  JOptionPane.showMessageDialog(null,"RECYCLE BIN CHARGED");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }   
    
    }
     
    
    //UPDATE
     public void update_materiaux()
    {
       // this.setAlwaysOnTop(false);
         try{
             
        String sql = "UPDATE journal SET LIBELLE=?,ENTRE=?,SORTIE=?,USD=?,FC=?,SH=?,USED=?,DATES`=? WHERE ID =?";
        
         pst = con.prepareStatement(sql);
        // pst.setString(9,matrid.getText());
         pst.setString(1,libele.getText());
          pst.setString(2,entre.getText());
        pst.setString(3,sorti.getText());
             pst.setString(4,doll3.getText());
              pst.setString(5,fc3.getText());
              pst.setString(6,sh3.getText());
             pst.setString(7,doll3.getText());
              pst.setString(8,used.getText());
                SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
         String addDate1 = dateFormat1.format(jDateChooser1.getDate());
         pst.setString(6, addDate1); 
                       
         pst.executeUpdate();
         JOptionPane.showMessageDialog(null,"UPDATE");    
     }catch(SQLException | HeadlessException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }
    }        
     
//CALCULE
     
             public void calulate()
         {
             float a = Float.parseFloat(doll3.getText());
            float b = Float.parseFloat(fc1.getText());
            float e = Float.parseFloat(sh1.getText());
            float c;
            float g;
            String d;
             String f;
            c=a*b;
            g=a*e;
            d= String.format("%.2f",c);
            f= String.format("%.2f",g);
            fc3.setText(d);
            sh3.setText(f);
                
         }
                    public void calulate1()
         {
             float a = Float.parseFloat(used.getText());
            float b = Float.parseFloat(fc1.getText());
            float e = Float.parseFloat(sh1.getText());
          
            float c;
            float g;
            String d;
             String f;
            c=a*b;
            g=a*e;
            d= String.format("%.2f",c);
            f= String.format("%.2f",g);
            fcusd.setText(d);
            shusd.setText(f);
                
         }
                    
                    
                    
                    //CALCULE EXTERIEUR
                    
                    
      
                  public void calulateEX()
         {
             if(jComboBox2.getSelectedItem().equals("CREDIT")){
             float a = Float.parseFloat(doll.getText());
            float b = Float.parseFloat(used.getText());
            float e = Float.parseFloat(doll3.getText());
            
            float c;
            float f;
            String d;
            String g;
            c=a+b;
            f=e-a;
            
            d= String.format("%.2f",c);
            g= String.format("%.2f",f);
            used.setText(d);
            doll3.setText(g);
             }else{
            float a = Float.parseFloat(doll.getText());
            //float b = Float.parseFloat(entre.getText());
            float e = Float.parseFloat(doll3.getText());
            
            float c;
           // float f;
            String d;
            //String g;
            c=a+e;
            //f=e-a;
            
            d= String.format("%.2f",c);
            //g= String.format("%.2f",f);
            doll3.setText(d);
           // doll3.setText(g); 
             }
                
         }
                   public void ReadData(String sql)
    {
            Statement st;
            try{
                
                st=con.createStatement();
                ResultSet rs;
                rs=st.executeQuery(sql);
                if(rs.first()){
                    String Data[]=new String[10];
                    do{
                        for(int i=0;i<10;i++){
                            Data[i]=rs.getString(i+1);
                        }
                    
                    mode.addRow(Data);
                }while(rs.next());
            }
                rs.close();
            }catch(Exception ex){
                
            }
    }
                   
public void projet_NAME_TO_BOMBOBOX3()
    {
         {
        try{
            String sql="select * from tabe";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                String sum=rs.getString("NOM");
                  jComboBox3.addItem(sum);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
        
        
        try{
            String sql="select * from busnesse";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                String sum=rs.getString("NOM");
                  jComboBox3.addItem(sum);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
    }
    }

               public void combobox1_from_materiel()
    {
        
        
     if(jComboBox3.getSelectedItem().equals("Select One Project")){
       JOptionPane.showMessageDialog(null,"NO PROJECT SELECTED PLEASE SELECT ONE");  
     
     }else if (project.isSelected()){
       
       
    // }else{
        try{
         String tmp =(String)jComboBox3.getSelectedItem();
     String sql="select * from  projet WHERE PROJET_NUM='"+tmp+"' ";
          
            pst=con.prepareStatement(sql);
           // pst.setString(1, tmp);
            rs=pst.executeQuery();
            if(rs.next()){
                
                String add4 = rs.getString("PROJET_NUM");
               titre.setText(add4);
                // String add5 = rs.getString("projet_ID");
               //projetid.setText(add5);
          }
                  try{
            String sqls="select sum(COUTS) from projet WHERE PROJET_NUM='"+titre.getText()+"'";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
            while(rs.next()){
                String add1 = rs.getString("sum(COUTS)");
              coutin.setText(add1);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
      try{
            String sqls="select SUM(CREDIT) from caisses WHERE 	BUSS='"+titre.getText()+"'";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
            while(rs.next()){
                String sum=rs.getString("SUM(CREDIT)");
                  coutout.setText(sum);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
      
         try{
            String sqls="select SUM(DEBIT) from caisses WHERE 	BUSS='"+titre.getText()+"'";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
            while(rs.next()){
                String sum=rs.getString("SUM(DEBIT)");
                  //coutin1.setText(sum);
                 // Float a = Float.parseFloat(coutin.getText());
Float b = Float.parseFloat(coutout.getText());
Float e = Float.parseFloat(sum);

Float c;
String d;
c=e-b;
d= String.format("%.2f",c);
coutin1.setText(d); 

                  
               
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
      
      
      }catch(Exception ex){
        JOptionPane.showMessageDialog(null,ex);  
      }
        //Float a = Float.parseFloat(coutin.getText());
//Float b = Float.parseFloat(coutout.getText());
//Float e = Float.parseFloat(coutin1.getText());

//Float c;
//String d;
//c=a-b;
//d= String.format("%.2f",c);
//coutin1.setText(d); 


// from_COMPTE_PROJECT();
        //Call_cout_sum_entre();
//       search22();
      search2BUS();

     }else{
     
         
          String tmp =(String)jComboBox3.getSelectedItem();
          String add8=null;
          Float add1=null;
          Float add2=null;
          Float add3=null;
          Float add4=null;
          
          try{
      //SELECT `ID`, `CODE`, `CODEMERE`, `LIBELE`, `BUSS`, `DEBIT`, `CREDIT`, `DATE`, `COMPTE` FROM `caisses` WHERE 1  
     String sql="select BUSS,ID, SUM(DEBIT),SUM(CREDIT) from  caisses WHERE BUSS='"+tmp+"'";
          
            pst=con.prepareStatement(sql);
           // pst.setString(1, tmp);
            rs=pst.executeQuery();
            if(rs.next()){
                
                String add5 = rs.getString("BUSS");
               titre.setText(add5);
                 String add6 = rs.getString("ID");
               projetid.setText(add6);
                 //String add5 = rs.getString("SUM(CAPITAL)");
               //coutin.setText(add5);
               
                 add2 = rs.getFloat("SUM(DEBIT)");
               //coutin1.setText(add2);
               
                 add3 = rs.getFloat("SUM(CREDIT)");
               coutout.setText(add3.toString());
               
               // add8 = rs.getString("SUM(ENTRE)");
             //  AA.setText(add8);
          }
          } catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);  
     
    
          
         
          
          
     }
Float c;
String d;
c=add2-add3;
d= String.format("%.2f",c);
coutin1.setText(d);
search2BUS();
    }
    // 
    } 
               
   
                 public void Call_cout_sum_entre()
    {
         {
        try{
            PreparedStatement pst = con.prepareStatement("select COUT from projet where projet_NAME= ?");
            pst.setString(1,titre.getText());
           
            rs=pst.executeQuery();
          
            while(rs.next()){
                String sum=rs.getString("COUT");
                  coutin.setText(sum);
                  
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
    }
       try{
            PreparedStatement pst = con.prepareStatement("select COUTOUT from projet where projet_NAME= ?");
            pst.setString(1,titre.getText());
           
            rs=pst.executeQuery();
            while(rs.next()){
                String sum=rs.getString("COUTOUT");
                  coutout.setText(sum);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }  
        try{
            PreparedStatement pst = con.prepareStatement("select COUTIN from projet where projet_NAME= ?");
            pst.setString(1,titre.getText());
           
            rs=pst.executeQuery();
            while(rs.next()){
                String sum=rs.getString("COUTIN");
                  coutin1.setText(sum);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        } 
    }

 public void AddModel(){
        mode=new DefaultTableModel();
        mode.addColumn("ID");
        mode.addColumn("WORDING");    
        mode.addColumn("DEBIT");
        mode.addColumn("CREDIT");
        mode.addColumn("USD");
        mode.addColumn("CDF");
        mode.addColumn("USH");
        mode.addColumn("USED");
        mode.addColumn("DATE");
        mode.addColumn("PROJECT");
      //  mode.addColumn("projet ID");
        
        
        jTable1.setModel(mode);
        
        
       
       
       
        
        TableColumn col0=jTable1.getColumnModel().getColumn(0);
        TableColumn col1=jTable1.getColumnModel().getColumn(1);
        TableColumn col2=jTable1.getColumnModel().getColumn(2);
        TableColumn col3=jTable1.getColumnModel().getColumn(3);
        TableColumn col4=jTable1.getColumnModel().getColumn(4);
        TableColumn col5=jTable1.getColumnModel().getColumn(5);
        TableColumn col6=jTable1.getColumnModel().getColumn(6);
        TableColumn col7=jTable1.getColumnModel().getColumn(7);
        TableColumn col9=jTable1.getColumnModel().getColumn(9);
       
       col0.setPreferredWidth(2);
       col1.setPreferredWidth(200);
       col2.setPreferredWidth(20);
       col3.setPreferredWidth(20);
       col4.setPreferredWidth(20);
       col5.setPreferredWidth(20);
       col6.setPreferredWidth(20);
       col7.setPreferredWidth(20);
       col9.setPreferredWidth(250);
       JTableHeader h=jTable1.getTableHeader();
       
    }
 
      
       public void remove(){
          while(jTable1.getRowCount()>0){
              mode.removeRow(0);
          }
      }
       
       
       //REPORT
     
                   public void report()
                           
     {
         
         
             
             if (projetid.getText().isEmpty()){
                // this.setAlwaysOnTop (false);
 try{
     
      
                   
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String tmpss = dateFormat.format(jDateChooser2.getDate());
         
       SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
        String tmps = dateFormats.format(jDateChooser3.getDate()); 
        
        
        
          String sqls="Select path from pathn";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
            if(rs.next()){
             
                String sum=rs.getString("path");
                  
                 String NameFiles=sum;
            
            String NameFile=""+NameFiles+"journaldecaise.jrxml";
                 
                 
                 
                JasperDesign jd=JRXmlLoader.load(NameFile);
        
        
              // JasperDesign jd=JRXmlLoader.load("C:\\Users\\Dosh\\Documents\\NetBeansProjects\\intreprisemanagementsystem\\src\\intreprisemanagementsystem\\journaldecaise.jrxml");
              String sql="SELECT * FROM  caisses WHERE DATE BETWEEN '"+tmpss+"' and '"+tmps+"' and BUSS='"+titre.getText()+"' ";
              
    
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
     }else{
             
           String tmp =(String)titre.getText();
          // this.setAlwaysOnTop (false);
     
             try{
                 
                 
                   String sqls="Select path from pathn";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
            if(rs.next()){
             
                String sum=rs.getString("path");
                  
                 String NameFiles=sum;
            
            String NameFile=""+NameFiles+"journaldecaise.jrxml";
                 
                 
                 
                JasperDesign jd=JRXmlLoader.load(NameFile);
                 
               //JasperDesign jd=JRXmlLoader.load("C:\\Users\\Dosh\\Documents\\NetBeansProjects\\intreprisemanagementsystem\\src\\intreprisemanagementsystem\\journaldecaise.jrxml");
              String sql="select * from caisses WHERE buss='"+tmp+"'";
              
    
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
     }
           
     }
             
             
             
             
             
             
             
             
             
                   //("SELECT * FROM  journal WHERE DATES BETWEEN '"+tmp+"' and '"+tmps+"' ");
                   
       // SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
       // String tmp = dateFormat.format(jDateChooser2.getDate());
         
       //SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
       // String tmps = dateFormats.format(jDateChooser3.getDate()); 
                                   public void reports()
     {
        // this.setAlwaysOnTop(false);
          
     
             try{
                 
                 
                   String sqls="Select path from pathn";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
            if(rs.next()){
             
                String sum=rs.getString("path");
                  
                 String NameFiles=sum;
            
            String NameFile=""+NameFiles+"journaldecaise.jrxml";
                 
                 
                 
                JasperDesign jd=JRXmlLoader.load(NameFile);
                 
                 
                 
               //String report ="C:\\Users\\Dosh\\Documents\\NetBeansProjects\\intreprisemanagementsystem\\src\\intreprisemanagementsystem\\journaldecaise.jrxml";
               // String report ="C:\\Users\\Doshe\\Documents\\NetBeansProjects\\intreprisemanagementsystem\\src\\report\\newReportmateriau.jrxml";
                
               
                 JasperReport jr =JasperCompileManager.compileReport(jd);
                 JasperPrint jp=JasperFillManager.fillReport(jr,null,con);
                 JasperViewer.viewReport(jp,false);
 
                 JasperViewer m= new JasperViewer(jp);
                 m.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            }
             }catch(Exception ex){
                  JOptionPane.showMessageDialog(null, ex);   
             }

     }
       
        //IMPRESSION COMPTE
                                   
                                   
                                   
                                     public void report_compte()
     {
          String A =JOptionPane.showInputDialog("PRINTING CODE!!!");
        // this.setAlwaysOnTop(false);
          String tmps =(String)jList2.getSelectedValue();
     if(A.equals("1234")){
             try{
            String sqls="Select path from pathn";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
            if(rs.next()){
             
                String sum=rs.getString("path");
                  
                 String NameFiles=sum;
            
            String NameFile=""+NameFiles+"compte_ohada.jrxml";
                 
                 
                 
                JasperDesign jd=JRXmlLoader.load(NameFile);
                 
               //JasperDesign jd=JRXmlLoader.load("C:\\Users\\Dosh\\Documents\\NetBeansProjects\\intreprisemanagementsystem\\src\\intreprisemanagementsystem\\journaldecaise.jrxml");
              String sql="select * from autres_achats WHERE CODE='"+tmps+"'";
              
    
                JRDesignQuery nq=new JRDesignQuery();
                nq.setText(sql);
                jd.setQuery(nq);
                
                
               
                 JasperReport jr =JasperCompileManager.compileReport(jd);
                 JasperPrint jp=JasperFillManager.fillReport(jr,null,con);
                 JasperViewer.viewReport(jp,false);
                 
                 
                 
                 JasperViewer m= new JasperViewer(jp);
                 
                 //this.setAlwaysOnTop (false);
                // m.setAlwaysOnTop (true);
                 // this.hide();
                 m.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            }
                 
             }catch(Exception ex){
                  JOptionPane.showMessageDialog(null, ex);   
             }
     }else{JOptionPane.showMessageDialog(null, "YOU ARE NOT ALLOWED TO PRINT THIS DOCUMENT!!!!");}

     }
                                   
                                   
                                   
                                   
                                   
                 
                
                                public void search2BUS()
             {
                  if(jComboBox3.getSelectedItem().equals("SELECTIONNE LE projet ICI!!!!*****SELECT PROJECT NAME HERE!!!!")){
     //  JOptionPane.showMessageDialog(null,"LE PREOJET N'EST PAS SELECTIONNE");  
     }else{
                 try{
                  String st=projetid.getText().trim();
             String tmp=jComboBox3.getSelectedItem().toString();
    String sql="select `NUM`, `CODE`,`LIBELE`, `DEBIT`, `CREDIT`, `DATE` from caisses where BUSS like '"+tmp+"'";
    //`LIBELLE`, `ENTRE`, `SORTIE`, `DATE`, `BUSS`, `USED`, `NUM`
    pst = con.prepareStatement(sql);
      rs= pst.executeQuery();
     
       jTable1.setModel(DbUtils.resultSetToTableModel(rs));
    
             }catch(Exception ex){
                 JOptionPane.showMessageDialog(null, ex);
             }
                 DefaultTableCellRenderer centre =new DefaultTableCellRenderer();
        centre.setHorizontalAlignment(JLabel.CENTER);
         TableColumn col0=jTable1.getColumnModel().getColumn(0);
        TableColumn col1=jTable1.getColumnModel().getColumn(1);
        TableColumn col2=jTable1.getColumnModel().getColumn(2);
        TableColumn col3=jTable1.getColumnModel().getColumn(3);
          centre.setHorizontalAlignment(JLabel.CENTER);
            jTable1.getColumnModel().getColumn(3).setCellRenderer(centre);
        TableColumn col4=jTable1.getColumnModel().getColumn(4);
          centre.setHorizontalAlignment(JLabel.CENTER);
            jTable1.getColumnModel().getColumn(4).setCellRenderer(centre);
           TableColumn col5=jTable1.getColumnModel().getColumn(5);
           // TableColumn col6=jTable1.getColumnModel().getColumn(6);
      
       
       col0.setPreferredWidth(20);
       col1.setPreferredWidth(20);
       col2.setPreferredWidth(370);
       col3.setPreferredWidth(20);
       col4.setPreferredWidth(20);
       col5.setPreferredWidth(20);
    //   col6.setPreferredWidth(200);
             }
             }
       // titre.setText(stS);
             //recherche.setText(stS);    
    
             
             
             public void showTableData(){
     try{
        
         String sql="SELECT * FROM journal";
          pst = con.prepareStatement(sql);
        rs= pst.executeQuery();
        jTable1.setModel(DbUtils.resultSetToTableModel(rs));
        
      }catch(SQLException ex ){
      JOptionPane.showMessageDialog(null, ex);
}
}
             //CALCULE COUTIN AND COUTOUT
             public void coutin_out()
             {
            float a = Float.parseFloat(coutin1.getText());
            float b = Float.parseFloat(doll.getText());
            float e = Float.parseFloat(coutout.getText());
            float c;
            float g;
            String d;
             String f;
            c=a-b;
            g=b+e;
            d= String.format("%.2f",c);
            f= String.format("%.2f",g);
            coutin1.setText(d);
            coutout.setText(f);   
             }
public void currency(){
    try{
String sql ="select* from currency";
pst=con.prepareStatement(sql);
 rs=pst.executeQuery();
            while(rs.next()){
                String sum=rs.getString("VUSD");
                  fc1.setText(sum);
                  String sum1=rs.getString("ACDF");
                  doll1.setText(sum1);
                  String sum2=rs.getString("AUSH");
                  sh1.setText(sum2);
                  String sum3=rs.getString("VUSD");
                  fc2.setText(sum3);
                  String sum4=rs.getString("ACDF");
                  doll2.setText(sum4);
                  String sum5=rs.getString("VUSH");
                  sh2.setText(sum5);
            }
    }catch(Exception ex){
    JOptionPane.showMessageDialog(null,ex);
    }
}

public void show_in_table(){


 try{
                  String st=projetid.getText().trim();
             String tmp=jComboBox3.getSelectedItem().toString();
    String sql="select `ID`, `CODE`,`LIBELE`, `DEBIT`, `CREDIT`, `DATE`,BUSS from caisses";
    //`LIBELLE`, `ENTRE`, `SORTIE`, `DATE`, `BUSS`, `USED`, `NUM`
    pst = con.prepareStatement(sql);
      rs= pst.executeQuery();
     
       jTable1.setModel(DbUtils.resultSetToTableModel(rs));
    
             }catch(Exception ex){
                 JOptionPane.showMessageDialog(null, ex);
             }
                 DefaultTableCellRenderer centre =new DefaultTableCellRenderer();
        centre.setHorizontalAlignment(JLabel.CENTER);
         TableColumn col0=jTable1.getColumnModel().getColumn(0);
        TableColumn col1=jTable1.getColumnModel().getColumn(1);
        TableColumn col2=jTable1.getColumnModel().getColumn(2);
        TableColumn col3=jTable1.getColumnModel().getColumn(3);
          centre.setHorizontalAlignment(JLabel.CENTER);
            jTable1.getColumnModel().getColumn(3).setCellRenderer(centre);
        TableColumn col4=jTable1.getColumnModel().getColumn(4);
          centre.setHorizontalAlignment(JLabel.CENTER);
            jTable1.getColumnModel().getColumn(4).setCellRenderer(centre);
           TableColumn col5=jTable1.getColumnModel().getColumn(5);
            TableColumn col6=jTable1.getColumnModel().getColumn(6);
      
       
       col0.setPreferredWidth(0);
       col1.setPreferredWidth(20);
       col2.setPreferredWidth(370);
       col3.setPreferredWidth(50);
       col4.setPreferredWidth(50);
       col5.setPreferredWidth(50);
       col6.setPreferredWidth(200);
             }
             







  public void JLIST_from_COMPTE()
    {
        if(project.isSelected()){
         String tmp =(String)jList1.getSelectedValue();
    
     
        try{
          String sql="SELECT item,code FROM  budget_code WHERE code='"+tmp+"' and CAT='"+titre.getText()+"'";
          
            pst=con.prepareStatement(sql);
            //pst.setString(1, tmp);
            rs=pst.executeQuery();
            if(rs.next()){
                 
                String ad1 = rs.getString("item");
              cnom.setText(ad1);     
               String add1 = rs.getString("code");
              code.setText(add1);
               //String add = rs.getString("CAT");
              //comptemere.setText(add);
               
               
          
          }
  
      }catch(Exception ex){
        JOptionPane.showMessageDialog(null,ex);  
      }
        
        try{
          String sql="SELECT CAT FROM  budget WHERE code='"+tmp+"' and project='"+titre.getText()+"'";
          
            pst=con.prepareStatement(sql);
            //pst.setString(1, tmp);
            rs=pst.executeQuery();
            if(rs.next()){
                 
               
               String add = rs.getString("CAT");
              comptemere.setText(add);
               
               
          
          }
  
      }catch(Exception ex){
        JOptionPane.showMessageDialog(null,ex);  
      }

        
        }else{
     String tmp =(String)jList1.getSelectedValue();
     String sql="SELECT `numero_compte`, `intitule_compte`,`class_compte` FROM `parametre_default` WHERE numero_compte='"+tmp+"'";
     
        try{
         
          
            pst=con.prepareStatement(sql);
            //pst.setString(1, tmp);
            rs=pst.executeQuery();
            if(rs.next()){
                 
                String ad1 = rs.getString("intitule_compte");
              cnom.setText(ad1);     
               String add1 = rs.getString("numero_compte");
              code.setText(add1);
               String add2 = rs.getString("class_compte");
               comptemere.setText(add2);
               String add3 = rs.getString("class_compte");
              codemere.setText(add3);
               
          
          }
  
      }catch(Exception ex){
        JOptionPane.showMessageDialog(null,ex);  
      }
    }   
    } 
  
  
  //SELECT CAISSE ONLY
  
  public void JLIST_from_COMPTECAISSE()
    {
     String tmp =(String)jList1.getSelectedValue();
     String tmps =(String)jList3.getSelectedValue();
          
        try{
         String sql="SELECT * FROM ohada WHERE CODE='"+tmp+"'";

          
            pst=con.prepareStatement(sql);
            //pst.setString(1, tmp);
            rs=pst.executeQuery();
            if(rs.next()){
                 
                String ad1 = rs.getString("NAME");
              cnom.setText(ad1);     
               String add1 = rs.getString("CODE");
              code.setText(add1);
               String add2 = rs.getString("CLASS");
               comptemere.setText(add2);
               String add3 = rs.getString("CODEMERE");
              codemere.setText(add3);
               
          
          }
  
      }catch(Exception ex){
        JOptionPane.showMessageDialog(null,ex);  
      }
        
         try{
         String sql="SELECT * FROM ohada WHERE CODE='"+tmps+"'";

          
            pst=con.prepareStatement(sql);
            //pst.setString(1, tmp);
            rs=pst.executeQuery();
            if(rs.next()){
                 
                String ad1 = rs.getString("NAME");
              cnom1.setText(ad1);     
               String add1 = rs.getString("CODE");
              code1.setText(add1);
               String add2 = rs.getString("CLASS");
               classe1.setText(add2);
               String add3 = rs.getString("CODEMERE");
              codemere1.setText(add3);
                String add4 = rs.getString("COMPTEMERE");
              comptemere1.setText(add4);
               
          
          }
  
      }catch(Exception ex){
        JOptionPane.showMessageDialog(null,ex);  
      }
          
    } 
  
  public void callcodecaisse(){
  
   try{
         String sql="SELECT * FROM ohada WHERE NAME='"+jComboBox6.getSelectedItem()+"'";
          
            pst=con.prepareStatement(sql);
            //pst.setString(1, tmp);
            rs=pst.executeQuery();
            if(rs.next()){
                 
                 
               String add1 = rs.getString("CODE");
              caissecode.setText(add1);
                 String ad1 = rs.getString("NAME");
              pcnom.setText(ad1);     
              
               String add2 = rs.getString("CLASS");
               pclasse.setText(add2);
               String add3 = rs.getString("CODEMERE");
              pcodem.setText(add3);
                String add4 = rs.getString("COMPTEMERE");
              pcomptem.setText(add4);
               
          
          }
  
      }catch(Exception ex){
        JOptionPane.showMessageDialog(null,ex);  
      }
  
  }
  

 //SUBSTRING FUNCTION
    
    public void substring(){
    
      try{
       
      
        Integer tmps = 523;
         // String Table_click = (jList2.getModel().getSelectedItem(tmps,0). toString());
       String sql="SELECT LEFT(numero_compte,2)  FROM parametre_default WHERE numero_compte ='"+code.getText()+"'";
       //select substring(col1, 4)from table1
       pst = con.prepareStatement(sql);
      rs= pst.executeQuery();
      if(rs.next()){
       
                
                String sum2=rs.getString("LEFT(numero_compte,2)");
                comptemere.setText(sum2);
                 
              
               
      }
    }catch(SQLException ex ){
     JOptionPane.showMessageDialog(null, ex);} 
    
    
    }
       
  
  
   public void attCall_LIST()
    {
        DefaultListModel list = new DefaultListModel();
        
           if(project.isSelected()){
         
        
         
        try{
            String sql="SELECT * FROM  budget_code WHERE code like '"+jTextField4.getText()+"%'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                //String sum=rs.getString("nom");
                String sums=rs.getString("code");
                 list.addElement(sums);
                
                 jList1.setModel(list);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
        
        }else if(business.isSelected()){
        try{
            String sql="select numero_compte  from  parametre_default where numero_compte like '"+jTextField4.getText()+"%' and class_compte between 6 and 7";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                String sum=rs.getString("numero_compte");
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
   
      public void attCall_LIST1()
    {
        DefaultListModel list = new DefaultListModel();
        
         
        try{
            String sql="select CODE  from  OHADA where CODE like '"+jTextField4.getText()+"%' and LEFT (CODE,2)='57'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                String sum=rs.getString("CODE");
               // String sums=rs.getString("LNAME");
                 list.addElement(sum);
                
                 jList1.setModel(list);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
    }
     

 public void from_COMPTE_PROJECT()
    {
        
    // String tmp =(String)jList1.getSelectedValue();
     //String sql="select name from  stockin";
    
        String tmp =(String)jComboBox3.getSelectedItem();
     String sql="select CODE,COMPTE_MERE,CODE_MERE from ohada where NAME='"+tmp+"'";
     
        try{
         
          
            pst=con.prepareStatement(sql);
            //pst.setString(1, tmp);
            rs=pst.executeQuery();
            if(rs.next()){
                 
               // String ad1 = rs.getString("NAME");
              //cnom.setText(ad1);     
               String add1 = rs.getString("CODE");
              pcnom.setText(add1);
               String add2 = rs.getString("COMPTE_MERE");
               pcomptem.setText(add2);
               String add3 = rs.getString("CODE_MERE");
              pcodem.setText(add3);
               
          
          }
  
      }catch(Exception ex){
        JOptionPane.showMessageDialog(null,ex);  
      }
          
    }
 
 
 public void from_COMPTE_PROJECTS()
    {
        
    // String tmp =(String)jList1.getSelectedValue();
     //String sql="select name from  stockin";
    
        String tmp =(String)jComboBox5.getSelectedItem();
        //`ID`, `NAME`, `CODE`, `COMPTEMERE`, `CODEMERE`, `CLASS`
     String sql="select * from ohada where NAME='"+tmp+"'";
     
        try{
         
          
            pst=con.prepareStatement(sql);
            //pst.setString(1, tmp);
            rs=pst.executeQuery();
            if(rs.next()){
                 
               // String ad1 = rs.getString("NAME");
              //cnom.setText(ad1);     
               String ad1 = rs.getString("NAME");
              cnom1.setText(ad1);     
               String add1 = rs.getString("CODE");
              code1.setText(add1);
               String add2 = rs.getString("CLASS");
               classe1.setText(add2);
               String add3 = rs.getString("CODEMERE");
              codemere1.setText(add3);
                String add4 = rs.getString("COMPTEMERE");
              comptemere1.setText(add4);
               
          
          }
  
      }catch(Exception ex){
        JOptionPane.showMessageDialog(null,ex);  
      }
          
    }

 
 
 
  public void Groupe1(){
ButtonGroup bg1 = new ButtonGroup();
bg1.add(project);
bg1.add(business);
     }
     
     public void buttonSelected(){
    jComboBox3.removeAllItems();
     if(business.isSelected()){
    try{
            String sql="select * from busnesse";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                String sum=rs.getString("NOM");
                  jComboBox3.addItem(sum);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
    }
     }
     
    
     public void buttonSelected2(){
     jComboBox3.removeAllItems();
     if(project.isSelected()){
    try{
            String sql="select * from projet";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                String sum=rs.getString("PROJET_NUM");
                  jComboBox3.addItem(sum);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex); }   
        
     
     }
     }
 Integer Code_Budget_check;
 public void budgetcheck(){
 
    try{
            String sql="SELECT  count(CODE) FROM budget_trans WHERE code='"+jList1.getSelectedValue()+"' AND projet='"+titre.getText()+"'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
               String sum=rs.getString("count(CODE)");
               jTextField5.setText(sum);
               
                
                
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
 
 
 }
 

 public void budgetcheckS(){
  Double SUMBUDGETDEBIT;
 Double SUMBUDGETCREDIT;
    try{
            String sql="SELECT   sum(DEBIT),sum(CREDIT) FROM budget_trans WHERE code='"+jList1.getSelectedValue()+"' AND projet='"+titre.getText()+"'";
           
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
 
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        fc1 = new javax.swing.JTextField();
        sh1 = new javax.swing.JTextField();
        doll1 = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        sh3 = new javax.swing.JLabel();
        fc3 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        doll3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        doll = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        fc = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        sh = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        used = new javax.swing.JLabel();
        fcusd = new javax.swing.JLabel();
        shusd = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        sh2 = new javax.swing.JTextField();
        doll2 = new javax.swing.JTextField();
        fc2 = new javax.swing.JTextField();
        jDateChooser1 = new com.alee.extended.date.WebDateField();
        jComboBox6 = new javax.swing.JComboBox<>();
        caissecode = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        coutin = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        coutin1 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        titre = new javax.swing.JEditorPane();
        jPanel19 = new javax.swing.JPanel();
        coutout = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        entre = new javax.swing.JTextField();
        sorti = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jComboBox5 = new javax.swing.JComboBox<>();
        jTextField5 = new javax.swing.JTextField();
        jPanel12 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jTextField4 = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jList3 = new javax.swing.JList<>();
        jTextField1 = new javax.swing.JTextField();
        jPanel15 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jDateChooser2 = new com.alee.extended.date.WebDateField();
        jDateChooser3 = new com.alee.extended.date.WebDateField();
        jComboBox4 = new javax.swing.JComboBox<>();
        jPanel20 = new javax.swing.JPanel();
        jComboBox3 = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        libele = new javax.swing.JEditorPane();
        jLabel18 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        recherche = new javax.swing.JTextField();
        coutin2 = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        project = new javax.swing.JRadioButton();
        business = new javax.swing.JRadioButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jTextField2 = new javax.swing.JTextField();
        january = new javax.swing.JTextField();
        february = new javax.swing.JTextField();
        march = new javax.swing.JTextField();
        april = new javax.swing.JTextField();
        may = new javax.swing.JTextField();
        june = new javax.swing.JTextField();
        july = new javax.swing.JTextField();
        august = new javax.swing.JTextField();
        september = new javax.swing.JTextField();
        october = new javax.swing.JTextField();
        november = new javax.swing.JTextField();
        jYearChooser1 = new com.toedter.calendar.JYearChooser();
        december = new javax.swing.JTextField();
        jPanel13 = new javax.swing.JPanel();
        journal_id = new javax.swing.JLabel();
        pcnom = new javax.swing.JTextField();
        pcomptem = new javax.swing.JTextField();
        pcodem = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        cnom = new javax.swing.JTextField();
        code = new javax.swing.JTextField();
        comptemere = new javax.swing.JTextField();
        codemere = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList<>();
        projetid = new javax.swing.JTextField();
        cnom1 = new javax.swing.JTextField();
        code1 = new javax.swing.JTextField();
        comptemere1 = new javax.swing.JTextField();
        codemere1 = new javax.swing.JTextField();
        classe1 = new javax.swing.JTextField();
        pclasse = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu6 = new javax.swing.JMenu();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();

        setClosable(true);
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameOpened(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        fc1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        fc1.setText("1350");
        fc1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        fc1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fc1ActionPerformed(evt);
            }
        });
        jPanel1.add(fc1, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 40, 130, -1));

        sh1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        sh1.setText("3620");
        sh1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.add(sh1, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 40, 140, -1));

        doll1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        doll1.setText("1500");
        doll1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.add(doll1, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 40, 120, -1));

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        sh3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        sh3.setForeground(new java.awt.Color(102, 102, 255));
        sh3.setText("0");

        fc3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        fc3.setForeground(new java.awt.Color(0, 51, 255));
        fc3.setText("0");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 51, 255));
        jLabel7.setText("FC");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 204));
        jLabel9.setText("USD");

        doll3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        doll3.setForeground(new java.awt.Color(0, 0, 204));
        doll3.setText("0");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 102, 255));
        jLabel5.setText("SH");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(doll3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel9))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(sh3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(fc3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel5))))
                .addContainerGap(120, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sh3)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(2, 2, 2)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fc3)
                    .addComponent(jLabel7))
                .addGap(1, 1, 1)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(doll3))
                .addGap(12, 12, 12))
        );

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 170, 70));

        jPanel6.setBackground(new java.awt.Color(204, 204, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        doll.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        doll.setText("0");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("USD");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(doll)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6)
                .addGap(0, 77, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(doll)
                    .addComponent(jLabel6)))
        );

        jPanel1.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 10, 130, 30));

        jPanel7.setBackground(new java.awt.Color(204, 204, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        fc.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        fc.setText("0");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setText("CDF");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(fc)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addContainerGap(71, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fc)
                    .addComponent(jLabel10)))
        );

        jPanel1.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 10, 120, 30));

        jPanel8.setBackground(new java.awt.Color(204, 204, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        sh.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        sh.setText("0");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setText("USH");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sh)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel12)
                .addContainerGap(89, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sh)
                    .addComponent(jLabel12)))
        );

        jPanel1.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 10, 140, 30));

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton1.setText("RATE");
        jButton1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 10, 90, 30));

        jPanel9.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel9.setForeground(new java.awt.Color(255, 0, 0));

        used.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        used.setForeground(new java.awt.Color(255, 0, 0));
        used.setText("0");

        fcusd.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        fcusd.setForeground(new java.awt.Color(255, 102, 102));
        fcusd.setText("0");

        shusd.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        shusd.setForeground(new java.awt.Color(255, 153, 153));
        shusd.setText("0");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 153, 153));
        jLabel11.setText("SH");

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 102, 102));
        jLabel13.setText("FC");

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 51, 51));
        jLabel14.setText("USD");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(used)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel14))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(fcusd)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel13))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(shusd)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel11)))
                .addGap(0, 120, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(shusd)
                    .addComponent(jLabel11))
                .addGap(1, 1, 1)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fcusd)
                    .addComponent(jLabel13))
                .addGap(1, 1, 1)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(used)
                    .addComponent(jLabel14))
                .addContainerGap())
        );

        jPanel1.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 10, 170, 70));

        sh2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        sh2.setText("3620");
        sh2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.add(sh2, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 60, 140, -1));

        doll2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        doll2.setText("1500");
        doll2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.add(doll2, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 60, 120, -1));

        fc2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        fc2.setText("1350");
        fc2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        fc2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fc2ActionPerformed(evt);
            }
        });
        jPanel1.add(fc2, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 60, 130, -1));

        jDateChooser1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jPanel1.add(jDateChooser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 10, 130, 30));

        jComboBox6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jComboBox6.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                jComboBox6PopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        jPanel1.add(jComboBox6, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 50, 130, -1));

        caissecode.setEditable(false);
        caissecode.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jPanel1.add(caissecode, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 50, 90, -1));

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel17.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel17.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel11.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        coutin.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        coutin.setText("0");
        jPanel11.add(coutin, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 13, -1, -1));

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel15.setText("$");
        jPanel11.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 10, 10, -1));

        jPanel17.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 182, 42));

        jPanel18.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel18.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        coutin1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        coutin1.setText("0");
        jPanel18.add(coutin1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel16.setText("$");
        jPanel18.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 10, -1, -1));

        jPanel17.add(jPanel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 10, 175, 42));

        titre.setEditable(false);
        titre.setBackground(new java.awt.Color(240, 240, 240));
        titre.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        titre.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jScrollPane5.setViewportView(titre);

        jPanel17.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 10, 419, 42));

        jPanel19.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel19.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        coutout.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        coutout.setText("0");
        jPanel19.add(coutout, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel17.setText("$");
        jPanel19.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 10, 10, -1));

        jPanel17.add(jPanel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 10, 177, 42));

        jPanel10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel5.setForeground(new java.awt.Color(240, 240, 240));

        entre.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        entre.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                entreMouseClicked(evt);
            }
        });
        entre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                entreKeyTyped(evt);
            }
        });

        sorti.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        sorti.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sortiMouseClicked(evt);
            }
        });
        sorti.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                sortiKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                sortiKeyTyped(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Georgia", 1, 12)); // NOI18N
        jLabel1.setText("Enter");

        jLabel2.setFont(new java.awt.Font("Georgia", 1, 12)); // NOI18N
        jLabel2.setText("Out");

        jComboBox1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jComboBox2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jComboBox2.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                jComboBox2PopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setText("Bank");

        jComboBox5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECT ONE BANK" }));
        jComboBox5.setEnabled(false);
        jComboBox5.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                jComboBox5PopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        jTextField5.setEditable(false);
        jTextField5.setForeground(new java.awt.Color(240, 240, 240));
        jTextField5.setBorder(null);
        jTextField5.setEnabled(false);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel8))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(sorti)
                            .addComponent(entre)
                            .addComponent(jComboBox5, 0, 139, Short.MAX_VALUE))))
                .addContainerGap())
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(entre, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sorti, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 4, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel12.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel14.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTextField4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jTextField4.setBorder(null);
        jTextField4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField4KeyReleased(evt);
            }
        });

        jList1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jList1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jList1MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jList1);

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addComponent(jTextField4, javax.swing.GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jList3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jList3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jList3MouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(jList3);

        jTextField1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addComponent(jTextField1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel15.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButton3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton3.setText("Search BTW Two Dates");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jDateChooser2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jDateChooser3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jComboBox4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "OTHER TRANSACTION", "PAYDAY ADVANCE" }));
        jComboBox4.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                jComboBox4PopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jDateChooser2, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
                    .addComponent(jDateChooser3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jComboBox4, 0, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jDateChooser3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel20.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel20.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jComboBox3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jComboBox3.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                jComboBox3PopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        jComboBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox3ActionPerformed(evt);
            }
        });
        jPanel20.add(jComboBox3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 340, -1));

        libele.setBorder(null);
        libele.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jScrollPane2.setViewportView(libele);

        jPanel20.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 60, 260, 42));

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel18.setText("Wording");
        jPanel20.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 70, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Search by Word.");
        jPanel20.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, -1, 21));

        recherche.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        recherche.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        recherche.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                rechercheKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                rechercheKeyTyped(evt);
            }
        });
        jPanel20.add(recherche, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 110, 230, 20));

        coutin2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        coutin2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/search.png"))); // NOI18N
        jPanel20.add(coutin2, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 110, -1, -1));

        project.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        project.setText("Project");
        project.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                projectActionPerformed(evt);
            }
        });

        business.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        business.setText("Business");
        business.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                businessActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addComponent(business)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 182, Short.MAX_VALUE)
                .addComponent(project)
                .addContainerGap())
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(project, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(business, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
        );

        jPanel20.add(jPanel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 330, -1));

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addComponent(jPanel20, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTable1.setAutoCreateRowSorter(true);
        jTable1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jTable1.setFont(new java.awt.Font("Tahoma", 0, 14));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jTextField2.setEditable(false);
        jTextField2.setBackground(new java.awt.Color(0, 0, 0));
        jTextField2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jTextField2.setForeground(new java.awt.Color(255, 255, 255));
        jTextField2.setText("                                                             Cash_Journal");
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        january.setEditable(false);
        january.setBackground(new java.awt.Color(204, 204, 204));
        january.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        january.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        january.setText("JANUARY");
        january.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        february.setEditable(false);
        february.setBackground(new java.awt.Color(204, 204, 204));
        february.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        february.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        february.setText("FEBRUARY");
        february.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        march.setEditable(false);
        march.setBackground(new java.awt.Color(204, 204, 204));
        march.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        march.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        march.setText("MARCH");
        march.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        april.setEditable(false);
        april.setBackground(new java.awt.Color(204, 204, 204));
        april.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        april.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        april.setText("APRIL");
        april.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        may.setEditable(false);
        may.setBackground(new java.awt.Color(204, 204, 204));
        may.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        may.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        may.setText("MAY");
        may.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        june.setEditable(false);
        june.setBackground(new java.awt.Color(204, 204, 204));
        june.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        june.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        june.setText("JUNE");
        june.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        july.setEditable(false);
        july.setBackground(new java.awt.Color(204, 204, 204));
        july.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        july.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        july.setText("JULY");
        july.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        august.setEditable(false);
        august.setBackground(new java.awt.Color(204, 204, 204));
        august.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        august.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        august.setText("AUGUST");
        august.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        september.setEditable(false);
        september.setBackground(new java.awt.Color(204, 204, 204));
        september.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        september.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        september.setText("SEPTEMBER");
        september.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        october.setEditable(false);
        october.setBackground(new java.awt.Color(204, 204, 204));
        october.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        october.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        october.setText("OCTOBER");
        october.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        november.setEditable(false);
        november.setBackground(new java.awt.Color(204, 204, 204));
        november.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        november.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        november.setText("NOVEMBER");
        november.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        november.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                novemberActionPerformed(evt);
            }
        });

        jYearChooser1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        december.setEditable(false);
        december.setBackground(new java.awt.Color(204, 204, 204));
        december.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        december.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        december.setText("DECEMBER");
        december.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel13.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        journal_id.setForeground(new java.awt.Color(240, 240, 240));
        journal_id.setText("0");

        pcnom.setEditable(false);
        pcnom.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        pcnom.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        pcomptem.setEditable(false);
        pcomptem.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        pcomptem.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        pcodem.setEditable(false);
        pcodem.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        pcodem.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jTextField3.setEditable(false);
        jTextField3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        cnom.setEditable(false);
        cnom.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        cnom.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        code.setEditable(false);
        code.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        code.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        comptemere.setEditable(false);
        comptemere.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        comptemere.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        codemere.setEditable(false);
        codemere.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        codemere.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jList2.setBackground(new java.awt.Color(240, 240, 240));
        jList2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jList2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jList2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jList2MouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(jList2);

        projetid.setEditable(false);
        projetid.setFont(new java.awt.Font("Tahoma", 0, 3)); // NOI18N
        projetid.setBorder(null);
        projetid.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                projetidKeyReleased(evt);
            }
        });

        cnom1.setEditable(false);
        cnom1.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        cnom1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        code1.setEditable(false);
        code1.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        code1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        comptemere1.setEditable(false);
        comptemere1.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        comptemere1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        codemere1.setEditable(false);
        codemere1.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        codemere1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        classe1.setEditable(false);
        classe1.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        classe1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        pclasse.setEditable(false);
        pclasse.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        pclasse.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pcnom)
            .addComponent(pcomptem)
            .addComponent(pcodem)
            .addComponent(jTextField3)
            .addComponent(cnom)
            .addComponent(code)
            .addComponent(comptemere)
            .addComponent(codemere)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addComponent(cnom1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(code1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(comptemere1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(codemere1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(classe1)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addComponent(journal_id, javax.swing.GroupLayout.PREFERRED_SIZE, 6, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(projetid, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(pclasse)
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addComponent(pcnom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pcomptem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pcodem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pclasse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cnom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(code, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(comptemere, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(codemere, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cnom1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(code1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(comptemere1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(codemere1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(classe1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(journal_id, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(projetid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_Refresh_30px.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jMenu6.setText("X");
        jMenu6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jMenu6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu6MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu6);

        jMenu1.setText("File");

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_Save_16px_1.png"))); // NOI18N
        jMenuItem2.setText("Save");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem11.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_Delete_16px_2.png"))); // NOI18N
        jMenuItem11.setText("Delete");
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem11);

        jMenu4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/search.png"))); // NOI18N
        jMenu4.setText("Search");

        jMenuItem4.setText("Seach by Project Description");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem4);

        jMenuItem5.setText("Search by Project Title");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem5);

        jMenu1.add(jMenu4);

        jMenuItem8.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem8.setText("Refresh");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem8);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");

        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_Coins_16px.png"))); // NOI18N
        jMenuItem1.setText("Curency");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem1);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Tool");

        jMenuItem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_Calculator_16px.png"))); // NOI18N
        jMenuItem3.setText("Curency_Calculator");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem3);

        jMenuBar1.add(jMenu3);

        jMenu5.setText("Reports");

        jMenuItem9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_Bill_16px.png"))); // NOI18N
        jMenuItem9.setText("Cash out Receipt");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem9);

        jMenuItem6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_Report_Card_16px.png"))); // NOI18N
        jMenuItem6.setText("Cash Book");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem6);

        jMenuItem7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_News_16px.png"))); // NOI18N
        jMenuItem7.setText("General Cash Book Report");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem7);

        jMenuBar1.add(jMenu5);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(january, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(february, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(march, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(april, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(may, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(june, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(july, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(august, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(september, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(october, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(november, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(december, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jYearChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE))
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(january, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(february, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(march, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(april, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(may, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(june, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(july, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(august, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(september, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(october, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(november, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(december, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jYearChooser1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, 477, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
       if(jComboBox2.getSelectedItem().equals("CREDIT")){
            comboboxsortie();

        }else{
            comboboxentre();
        }
        fc1.enable(false);
        sh1.enable(false);
        doll1.enable(false);
if(project.isSelected()){
        savemateriaux();
search2BUS();
}else if(business.isSelected()){
savemateriaux();
search2BUS();
}

        calulate1();

        //float a = Float.parseFloat(doll3.getText());
        // float b = Float.parseFloat(used.getText());
        // float c;
        // String d;
        // c=a-b;
        // d= String.format("%.2f",c);
        // doll3.setText(d);
        calulate();
        //Call_cout_sum_entre();
        jComboBox1.setSelectedItem("CURRENCY");
       // jComboBox2.setSelectedItem("TRANSA.");
        sorti.setText("0");
        entre.setText("0");
        sorti.setEnabled(false);
        entre.setEnabled(false);
        //Call_cout_sum_entre();
        libele.setText(null);
        //titre.setText(null);
       // coutin.setText(null);
       // coutin1.setText(null);
       // coutout.setText(null);
        
         cnom.setText(null);
        code.setText(null);
        comptemere.setText(null);
        codemere.setText(null);
     //   project_call_afeter_delete();
        

      // search22();
//search2BUS();// TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
//        search();        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
//        search2();        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
      currency m =new currency();

        m.show();
        m.setAlwaysOnTop(true);
        //m.setType(Type.UTILITY);
        //calculater f = new calculater(" frame not displayable in the task bar ");
        m. setDefaultCloseOperation(DISPOSE_ON_CLOSE);      // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
     //   calculater m =new calculater();

      //  m.show();
      //  m.setAlwaysOnTop(true);
        //m.setType(Type.UTILITY);
        //calculater f = new calculater(" frame not displayable in the task bar ");
      //  m. setDefaultCloseOperation(DISPOSE_ON_CLOSE);// TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        report();
        //setAlwaysOnTop (true);// TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        reports();        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void fc1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fc1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fc1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
      try{
      String sql ="update  currency set AUSD=?,ACDF=?,AUSH=?,VUSD=?,VCDF=?,VUSH=? WHERE ID='1'";
      pst=con.prepareStatement(sql);
      
      pst.setString(1, doll1.getText());
      pst.setString(2, doll1.getText());
      pst.setString(3, sh1.getText());
      pst.setString(4, fc2.getText());
      pst.setString(5, fc2.getText());
      pst.setString(6, sh2.getText());
      
         pst.executeUpdate();
      
      }catch(Exception ex){
      JOptionPane.showMessageDialog(null,ex);
      }
       fc1.setEnabled(false);
        sh1.setEnabled(false);
        doll1.setEnabled(false);
           fc2.setEnabled(false);
        sh2.setEnabled(false);
        doll2.setEnabled(false);

        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void rechercheKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_rechercheKeyReleased
//        search();
        //search2();// TODO add your handling code here:
    }//GEN-LAST:event_rechercheKeyReleased

    private void rechercheKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_rechercheKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_rechercheKeyTyped

    private void jComboBox3PopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_jComboBox3PopupMenuWillBecomeInvisible
 jComboBox4.setEnabled(true);
coutin.setText("0");
coutin1.setText("0");
coutout.setText("0");//jComboBox4
jList1.removeAll();
        combobox1_from_materiel();
        budget_LIST1();
      
        //jComboBox3.setSelectedItem("SELECTIONNE LE projet ICI!!!!*****SELECT PROJECT NAME HERE!!!!");// TODO add your handling code here:
    }//GEN-LAST:event_jComboBox3PopupMenuWillBecomeInvisible

    private void jComboBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox3ActionPerformed
 //jComboBox4        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox3ActionPerformed

    private void entreMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_entreMouseClicked
        entre.setText("");         // TODO add your handling code here:
    }//GEN-LAST:event_entreMouseClicked

    private void entreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_entreKeyTyped
        char c=evt.getKeyChar();
        if(!(Character.isDigit(c) || (c==KeyEvent.VK_BACK_SPACE)||c==KeyEvent.VK_DELETE ||c==KeyEvent.VK_PERIOD )){
            evt.consume();
        }
sorti.setText("0");// TODO add your handling code here:
    }//GEN-LAST:event_entreKeyTyped

    private void sortiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sortiMouseClicked
        sorti.setText("");        // TODO add your handling code here:
    }//GEN-LAST:event_sortiMouseClicked

    private void sortiKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_sortiKeyTyped
        char c=evt.getKeyChar();
        if(!(Character.isDigit(c) || (c==KeyEvent.VK_BACK_SPACE)||c==KeyEvent.VK_DELETE ||c==KeyEvent.VK_PERIOD )){
            evt.consume();
 //sorti.setText(string.Format("{0:n0}", double.Parse( sorti.setText)));
        }
entre.setText("0");// TODO add your handling code here:
    }//GEN-LAST:event_sortiKeyTyped

    private void jComboBox2PopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_jComboBox2PopupMenuWillBecomeInvisible
// if_combo();
       
        if(jComboBox2.getSelectedItem().equals("CREDIT")){
         //attCall_IN_LIST();
        //  budget_LIST1();
         sorti.setEnabled(true);
         entre.setEnabled(false);
         //jComboBox5.setSelectedItem("SELECT ONE BANK");
         //jComboBox5.setEnabled(false);
        }else{
        //attCall_IN_LIST1();
     //   jList3.setModel(new DefaultListModel() );
 ////jList1.setModel(new DefaultListModel() );
        sorti.setEnabled(false);
        entre.setEnabled(true);
       // jComboBox5.setEnabled(true);
        }// TODO add your handling code here:
    }//GEN-LAST:event_jComboBox2PopupMenuWillBecomeInvisible

    private void projetidKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_projetidKeyReleased
//        search22();        // TODO add your handling code here:
    }//GEN-LAST:event_projetidKeyReleased

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String tmp = dateFormat.format(jDateChooser2.getDate());

        SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
        String tmps = dateFormats.format(jDateChooser3.getDate());
projetid.setText("");
       //
       try{
               //   String st=projetid.getText().trim();
            // String tmp=jComboBox3.getSelectedItem().toString();
    String sql="select `NUM`, `CODE`,`LIBELE`, `DEBIT`, `CREDIT`, `DATE` FROM  caisses WHERE DATE BETWEEN '"+tmp+"' and '"+tmps+"' and BUSS='"+titre.getText()+"' ";
    //`LIBELLE`, `ENTRE`, `SORTIE`, `DATE`, `BUSS`, `USED`, `NUM`
    pst = con.prepareStatement(sql);
      rs= pst.executeQuery();
     
       jTable1.setModel(DbUtils.resultSetToTableModel(rs));
    
             }catch(Exception ex){
                 JOptionPane.showMessageDialog(null, ex);
             }
                 DefaultTableCellRenderer centre =new DefaultTableCellRenderer();
        centre.setHorizontalAlignment(JLabel.CENTER);
         TableColumn col0=jTable1.getColumnModel().getColumn(0);
        TableColumn col1=jTable1.getColumnModel().getColumn(1);
        TableColumn col2=jTable1.getColumnModel().getColumn(2);
        TableColumn col3=jTable1.getColumnModel().getColumn(3);
          centre.setHorizontalAlignment(JLabel.CENTER);
            jTable1.getColumnModel().getColumn(3).setCellRenderer(centre);
        TableColumn col4=jTable1.getColumnModel().getColumn(4);
          centre.setHorizontalAlignment(JLabel.CENTER);
            jTable1.getColumnModel().getColumn(4).setCellRenderer(centre);
           TableColumn col5=jTable1.getColumnModel().getColumn(5);
           // TableColumn col6=jTable1.getColumnModel().getColumn(6);
      
       
       col0.setPreferredWidth(20);
       col1.setPreferredWidth(20);
       col2.setPreferredWidth(370);
       col3.setPreferredWidth(20);
       col4.setPreferredWidth(20);
       col5.setPreferredWidth(20);
    //   col6.setPreferredWidth(200);
             
             
        //
        
        
   //     Rea dData("SELECT * FROM  caisses WHERE DATE BETWEEN '"+tmp+"' and '"+tmps+"' ");

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
calulate1();

  
            /* float a = Float.parseFloat(doll3.getText());
            float b = Float.parseFloat(used.getText());
            float c;
            String d;
            c=a-b;
            d= String.format("%.2f",c);
            doll3.setText(d);*/
          calulate(); 
                
                
         
 show_in_table();       // TODO add your handling code here:
    }//GEN-LAST:event_formInternalFrameOpened

    private void fc2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fc2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fc2ActionPerformed

    private void jComboBox4PopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_jComboBox4PopupMenuWillBecomeInvisible

// TODO add your handling code here:
    }//GEN-LAST:event_jComboBox4PopupMenuWillBecomeInvisible

    private void jList1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList1MouseClicked
if(jComboBox5.getSelectedItem().equals("SELECT ONE BANK")){
    jTextField5.setText("");
    budgetcheck();
     if(jTextField5.getText().equals("0")){
         JOptionPane.showMessageDialog(null, "UnKnown Budget Line");
    }else{
 JLIST_from_COMPTE(); 
substring();
     }
}else{
   
    
    JLIST_from_COMPTECAISSE();
}
// TODOLIST_from_COMPTE( add your handling code here:
    }//GEN-LAST:event_jList1MouseClicked

    private void jList2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList2MouseClicked
report_compte();        // TODO add your handling code here:
    }//GEN-LAST:event_jList2MouseClicked

    private void novemberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_novemberActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_novemberActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
select_jTable();        // TODO add your handling code here:
    }//GEN-LAST:event_jTable1MouseClicked

    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
recycle_bin();
delete();




// search22();
//this.dispose();// TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem11ActionPerformed

    private void jComboBox5PopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_jComboBox5PopupMenuWillBecomeInvisible
from_COMPTE_PROJECTS(); 
 jList3.setModel(new DefaultListModel() );
 jList1.setModel(new DefaultListModel() );// TODO add your handling code here:
    }//GEN-LAST:event_jComboBox5PopupMenuWillBecomeInvisible

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
this.dispose();
NewJInternalFramejournal_de_caisse  m= new NewJInternalFramejournal_de_caisse ();
    newdesingn.jDesktopPane1.add(m);
   m.show();
   m. setDefaultCloseOperation(DISPOSE_ON_CLOSE);         // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
/*this.dispose();
NewJInternalFramejournal_de_caisse  m= new NewJInternalFramejournal_de_caisse ();
     newdesingn.jDesktopPane1.add(m);
   m.show();
   m. setDefaultCloseOperation(DISPOSE_ON_CLOSE);*/
try{  
             Double xxx;
Double yyy;//call from journala de banque  
            String sql="select SUM(DEBIT),SUM(CREDIT) from caisses";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                xxx=rs.getDouble("SUM(DEBIT)");
               // entre.setText(""+xxx);
                yyy=rs.getDouble("SUM(CREDIT)");
                  
                   caissesdebit=xxx-yyy;
                 //  sorti.setText(""+yyy);
                   jTextField4.setText(""+caissesdebit);
            }
            }catch(Exception ex){ JOptionPane.showMessageDialog(null, ex);} 

// TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void businessActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_businessActionPerformed
//buttonSelected(); 
 //attCall_IN_LIST();// TODO add your handling code here:
    }//GEN-LAST:event_businessActionPerformed

    private void projectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_projectActionPerformed
buttonSelected2(); 
budget_LIST1();// TODO add your handling code here:
    }//GEN-LAST:event_projectActionPerformed

    private void sortiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_sortiKeyPressed
 if (evt.getKeyCode()==KeyEvent.VK_ENTER){
            if(jComboBox2.getSelectedItem().equals("CREDIT")){
            comboboxsortie();

        }else{
            comboboxentre();
        }
        fc1.enable(false);
        sh1.enable(false);
        doll1.enable(false);

        savemateriaux();

        calulate1();

        //float a = Float.parseFloat(doll3.getText());
        // float b = Float.parseFloat(used.getText());
        // float c;
        // String d;
        // c=a-b;
        // d= String.format("%.2f",c);
        // doll3.setText(d);
        calulate();
        //Call_cout_sum_entre();
        jComboBox1.setSelectedItem("CURRENCY");
     //   jComboBox2.setSelectedItem("TRANSA.");
        sorti.setText("0");
        entre.setText("0");
        sorti.setEnabled(false);
        entre.setEnabled(false);
        //Call_cout_sum_entre();
        libele.setText(null);
        //titre.setText(null);
       // coutin.setText(null);
       // coutin1.setText(null);
       // coutout.setText(null);
        
         cnom.setText(null);
        code.setText(null);
        comptemere.setText(null);
        codemere.setText(null);
     //   project_call_afeter_delete();

 }          // TODO add your handling code here:
    }//GEN-LAST:event_sortiKeyPressed

    private void jTextField4KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField4KeyReleased
budget_LIST1_search();
        
               // TODO add your handling code here:
    }//GEN-LAST:event_jTextField4KeyReleased

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jList3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList3MouseClicked
JLIST_from_COMPTECAISSE();        // TODO add your handling code here:
    }//GEN-LAST:event_jList3MouseClicked

    private void codemere2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_codemere2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_codemere2ActionPerformed

    private void jComboBox6PopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_jComboBox6PopupMenuWillBecomeInvisible
callcodecaisse(); 
callcaisebussS();// TODO add your handling code here:
    }//GEN-LAST:event_jComboBox6PopupMenuWillBecomeInvisible

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
attCall_IN_LISTsearch();        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1KeyReleased

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
       // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void jMenu6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu6MouseClicked
this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu6MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField april;
    private javax.swing.JTextField august;
    private javax.swing.JRadioButton business;
    private javax.swing.JTextField caissecode;
    public static javax.swing.JTextField classe1;
    public static javax.swing.JTextField cnom;
    public static javax.swing.JTextField cnom1;
    public static javax.swing.JTextField code;
    public static javax.swing.JTextField code1;
    public static javax.swing.JTextField codemere;
    public static javax.swing.JTextField codemere1;
    public static javax.swing.JTextField comptemere;
    public static javax.swing.JTextField comptemere1;
    public static javax.swing.JLabel coutin;
    public static javax.swing.JLabel coutin1;
    public static javax.swing.JLabel coutin2;
    public static javax.swing.JLabel coutout;
    private javax.swing.JTextField december;
    private javax.swing.JLabel doll;
    private javax.swing.JTextField doll1;
    private javax.swing.JTextField doll2;
    public static javax.swing.JLabel doll3;
    private javax.swing.JTextField entre;
    private javax.swing.JLabel fc;
    private javax.swing.JTextField fc1;
    private javax.swing.JTextField fc2;
    private javax.swing.JLabel fc3;
    private javax.swing.JLabel fcusd;
    private javax.swing.JTextField february;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox<String> jComboBox1;
    public static javax.swing.JComboBox<String> jComboBox2;
    public static javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JComboBox<String> jComboBox5;
    private javax.swing.JComboBox<String> jComboBox6;
    private com.alee.extended.date.WebDateField jDateChooser1;
    private com.alee.extended.date.WebDateField jDateChooser2;
    private com.alee.extended.date.WebDateField jDateChooser3;
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
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList<String> jList1;
    private javax.swing.JList<String> jList2;
    private javax.swing.JList<String> jList3;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
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
    private javax.swing.JScrollPane jScrollPane6;
    public static javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    public static javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private com.toedter.calendar.JYearChooser jYearChooser1;
    private javax.swing.JTextField january;
    private javax.swing.JLabel journal_id;
    private javax.swing.JTextField july;
    private javax.swing.JTextField june;
    private javax.swing.JEditorPane libele;
    private javax.swing.JTextField march;
    private javax.swing.JTextField may;
    private javax.swing.JTextField november;
    private javax.swing.JTextField october;
    private javax.swing.JTextField pclasse;
    private javax.swing.JTextField pcnom;
    private javax.swing.JTextField pcodem;
    private javax.swing.JTextField pcomptem;
    private javax.swing.JRadioButton project;
    public static javax.swing.JTextField projetid;
    private javax.swing.JTextField recherche;
    private javax.swing.JTextField september;
    private javax.swing.JLabel sh;
    private javax.swing.JTextField sh1;
    private javax.swing.JTextField sh2;
    private javax.swing.JLabel sh3;
    private javax.swing.JLabel shusd;
    private javax.swing.JTextField sorti;
    public static javax.swing.JEditorPane titre;
    public static javax.swing.JLabel used;
    // End of variables declaration//GEN-END:variables
}
