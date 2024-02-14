/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intreprisemanagementsystem;

//import com.bradneighbors.builders.DateBuilder;
import static intreprisemanagementsystem.NewJInternalFramejournal_de_caisse.getConnection;
import static intreprisemanagementsystem.tableudebor.huille0;
import static intreprisemanagementsystem.tableudebor.huille1;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Dosh
 */
public class bilan_immobile extends javax.swing.JInternalFrame {

      Connection con=null;
PreparedStatement pst=null;
ResultSet rs= null;
 DefaultTableModel mode;
    public bilan_immobile() {
        initComponents();
              con=JavaDbConnect.dbConnect();
              Call_ID_TO_material();
        Groupe1();
        Call_ID_TO_BOMBOBOX();
        qty.setEditable(false);
        amount.setEditable(false);
        qty.setHorizontalAlignment(qty.CENTER);
        duree_amortissement.setHorizontalAlignment(duree_amortissement.CENTER);
        amount.setHorizontalAlignment(amount.CENTER);
        exact_amount.setHorizontalAlignment(exact_amount.CENTER);
        
         tday.setHorizontalAlignment(tday.CENTER);
         sday.setHorizontalAlignment(sday.CENTER);
         rday.setHorizontalAlignment(rday.CENTER);
        tamount.setHorizontalAlignment(tamount.CENTER);
         ramount.setHorizontalAlignment(ramount.CENTER);
         percentage.setHorizontalAlignment(percentage.CENTER);
        
        //construction, general instalations, fixtures
        //field and works of art
        //
        jComboBox1.addItem("Constructions, General Instalations, Fixtures");
        jComboBox1.addItem("Field and Art");
        jComboBox1.addItem("Industrial materials and Tools");
        jComboBox1.addItem("Transport equipment, Office equipment, Computer equipment");
        jComboBox1.addItem("Furniture");
        jComboBox1.addItem("Certificate and License");
        jComboBox1.addItem("Software");
        jComboBox1.addItem("Website");
        jComboBox1.addItem("Goodwill");
      
    }
    public void Call_ID_TO_material()
    {
         {
        try{
            String sql="select distinct(ITEM_ID) from equipement_in";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                String sum=rs.getString("ITEM_ID");
                  nom.addItem(sum);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
    }
    }
    
    public void combobox2(){
       jComboBox3.removeAllItems();
    if(jComboBox1.getSelectedItem().equals("Constructions, General Instalations, Fixtures")){
    jComboBox3.addItem("Commercial Batment");
    jComboBox3.addItem("Industrial");
    jComboBox3.addItem("Office");
    jComboBox3.addItem("Residential Building");
    jComboBox3.addItem("Warehouse");
    jComboBox3.addItem("Office layout");
    jComboBox3.addItem("Light layout");
     jComboBox3.addItem("Single-Family Home");
    jComboBox3.addItem("Condominium");
     jComboBox3.addItem("Townhouse");
    jComboBox3.addItem("Co-op");
     jComboBox3.addItem("Multi-Family Home");  
     jComboBox3.addItem("Painting");
    jComboBox3.addItem("Flooring");
    jComboBox3.addItem("Magazin furniture");
    jComboBox3.addItem("Big jobs in local");
    jComboBox3.addItem("Masonry");
   
    
    } else  if(jComboBox1.getSelectedItem().equals("Field and Art")){
    
     jComboBox3.addItem("Plot");
    jComboBox3.addItem("Art");
   
    }else  if(jComboBox1.getSelectedItem().equals("Transport equipment, Office equipment, Computer equipment")){
    
     jComboBox3.addItem("Cars");
    jComboBox3.addItem("Printer");
     jComboBox3.addItem("Computer");
    jComboBox3.addItem("Mobile");
     jComboBox3.addItem("IT equipement");
    jComboBox3.addItem("Office Equipement");
    
       jComboBox3.addItem("Moto Bisicle");
     jComboBox3.addItem("Bisicle");
    jComboBox3.addItem("Boart");
    
    jComboBox3.addItem("Train");
     jComboBox3.addItem("Plan");
    jComboBox3.addItem("Lorry");
   
    }else  if(jComboBox1.getSelectedItem().equals("Residential Building")){
    
     jComboBox3.addItem("Single-Family Home");
    jComboBox3.addItem("Condominium");
     jComboBox3.addItem("Townhouse");
    jComboBox3.addItem("Co-op");
     jComboBox3.addItem("Multi-Family Home");   
    }else  if(jComboBox1.getSelectedItem().equals("Furniture")){
    
     jComboBox3.addItem("Single seat");
    jComboBox3.addItem("Multiple seats");
     jComboBox3.addItem("Sleeping or lying");
    jComboBox3.addItem("Entertainment");
     jComboBox3.addItem("Tables");
     
     jComboBox3.addItem("Storage");
    jComboBox3.addItem("Sets");
     jComboBox3.addItem("Other");
   
    }else  if(jComboBox1.getSelectedItem().equals("Certificate and License")){
    
     jComboBox3.addItem("Certificate");
    jComboBox3.addItem("License");

   
    }
    else  if(jComboBox1.getSelectedItem().equals("Software")){
    
     jComboBox3.addItem("Software");
    
    }
    else  if(jComboBox1.getSelectedItem().equals("Website")){
    
     jComboBox3.addItem("Website");//
    
    }else if(jComboBox1.getSelectedItem().equals("Goodwillelse")){
    
     jComboBox3.addItem("Goodwillelse ");
    
    }else if(jComboBox1.getSelectedItem().equals("Industrial materials and Tools")){
    
     jComboBox3.addItem("Industrial materials and Tools");
    }
    }
        
    
    
    
     public void Groupe1(){
ButtonGroup bg1 = new ButtonGroup();
bg1.add(uni);
bg1.add(broad);

ButtonGroup bg2 = new ButtonGroup();
bg2.add(jRadioButton1);
bg2.add(jRadioButton2);
     }
     
     public void buttonSelected(){
     
     if(uni.isSelected()){
     qty.setText("1");
     qty.setEditable(false);
     qty.setBackground(Color.lightGray);
     }
     }
    
     public void buttonSelected2(){
     
     if(broad.isSelected()){
     qty.setText("");
     qty.setEditable(true);
     qty.setBackground(Color.white);
     
     }
     }
     
     
     
     public void calcul_amortissement(){
         
         Float sum= null;
             Float sum1= null;
             Date sum2= null;
             
         String sql="select * from billa_mobilier where NAME='"+jComboBox4.getSelectedItem()+"'";    
        try {
            
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
            
             sum=rs.getFloat("YEARS");
             sum1=rs.getFloat("AMOUNT");
             sum2=rs.getDate("USEDDATE");
            
             
              //float a = Float.parseFloat(jTextField11.getText());
            //float b = Float.parseFloat(sum1);
           // float c;// = Math.floor(a*b *100.0) / 100.0;
          // String d;
           // c=(a/100)*sum1;
          //  d= String.format("%.2f",c);
           // pt.setText(d);
            
         
            
     
    // Float tm = Float.parseFloat(duree_amortissement.getText());
     //Float a= Float.parseFloat(amount.getText());
         Float b=sum*365;//total jour d amorticement
         Float c=b/sum1;//motant par jour
          Float d;
          String dd;
          Float e;
          Float f;
         SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//Date d1=null;
Date d2=null;
//try{
//d1 = format.parse(format.format(jDateChooser2.getDate()));
d2 = format.parse(format.format(new Date()));



long diff= d2.getTime()-sum2.getTime();
long hours = diff/(60*60*1000);
long days = hours/24;// expending Days
d= b-days;//nombre exact de payement d'amortissement
 e=(days*100)/b; // %       
  f=d/c;// total net 
  //jLabel10.setText(""+f);
  ramount.setText(""+f);
  
  //jLabel11.setText(""+d);
   rday.setText(""+d);
 
  dd=  String .format("%.2f",e);
   //jLabel12.setText(dd+" %");
   percentage.setText(dd+" %");
          
         //   jLabel13.setText(""+days);
             sday.setText(""+days);
            
       tday.setText(""+b);     
            }
     }catch(Exception ex){JOptionPane.showMessageDialog(null, ex); }
     }
     
     public void calcul_amortissementLIST(){
         
         Float sum= null;
             Float sum1= null;
             Date sum2= null;
             
         String sql="select * from billa_mobilier where NAME='"+jList1.getSelectedValue()+"'";    
        try {
            
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
            
             sum=rs.getFloat("YEARS");
             sum1=rs.getFloat("AMOUNT");
             sum2=rs.getDate("USEDDATE");
            
             
              //float a = Float.parseFloat(jTextField11.getText());
            //float b = Float.parseFloat(sum1);
           // float c;// = Math.floor(a*b *100.0) / 100.0;
          // String d;
           // c=(a/100)*sum1;
          //  d= String.format("%.2f",c);
           // pt.setText(d);
            
         
            
     
    // Float tm = Float.parseFloat(duree_amortissement.getText());
     //Float a= Float.parseFloat(amount.getText());
         Float b=sum*365;//total jour d amorticement
         Float c=b/sum1;//motant par jour
          Float d;
          String dd;
          Float e;
          Float f;
         SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//Date d1=null;
Date d2=null;
//try{
//d1 = format.parse(format.format(jDateChooser2.getDate()));
d2 = format.parse(format.format(new Date()));



long diff= d2.getTime()-sum2.getTime();
long hours = diff/(60*60*1000);
long days = hours/24;// expending Days
d= b-days;//nombre exact de payement d'amortissement
 e=(days*100)/b; // %       
  f=d/c;// total net 
  //jLabel10.setText(""+f);
  ramount.setText(""+f);
  
  //jLabel11.setText(""+d);
   rday.setText(""+d);
 
  dd=  String .format("%.2f",e);
   //jLabel12.setText(dd+" %");
   percentage.setText(dd+" %");
          
         //   jLabel13.setText(""+days);
             sday.setText(""+days);
            
       tday.setText(""+b);     
            }
     }catch(Exception ex){JOptionPane.showMessageDialog(null, ex); }
     }
     
     
     
     public void calcule_amortisement(){
         if(jComboBox3.getSelectedItem().equals("Commercial Batment")){    
duree_amortissement.setText("50");
         }else if(jComboBox3.getSelectedItem().equals("IT equipement")){
duree_amortissement.setText("3");
         
         }else if(jComboBox3.getSelectedItem().equals("Printer")){
         
        duree_amortissement.setText("3");
         
         }else if(jComboBox3.getSelectedItem().equals("Computer")){
         duree_amortissement.setText("3");
         
         }else if(jComboBox3.getSelectedItem().equals("Mobile")){
         duree_amortissement.setText("3");
         
         }else if(jComboBox3.getSelectedItem().equals("Office Equipement")){
        duree_amortissement.setText("5");
         
         }else if(jComboBox3.getSelectedItem().equals("Cars")){
        duree_amortissement.setText("10");
        
         }else if(jComboBox3.getSelectedItem().equals("Moto Bisicle")){
        duree_amortissement.setText("10");
        
         }else if(jComboBox3.getSelectedItem().equals("Bisicle")){
        duree_amortissement.setText("10");
        
         }else if(jComboBox3.getSelectedItem().equals("Boart")){
        duree_amortissement.setText("10");
        
         }else if(jComboBox3.getSelectedItem().equals("Train")){
        duree_amortissement.setText("10");
        
         }else if(jComboBox3.getSelectedItem().equals("Plan")){
        duree_amortissement.setText("10");
        
         }else if(jComboBox3.getSelectedItem().equals("Lorry")){
        duree_amortissement.setText("10");
        
         } else if(jComboBox3.getSelectedItem().equals("Industrial")){
         duree_amortissement.setText("20");
         
         }else if(jComboBox3.getSelectedItem().equals("Office")){
        duree_amortissement.setText("10");
         
         }else if(jComboBox3.getSelectedItem().equals("Residential Building")){
        duree_amortissement.setText("100");
         
         }else if(jComboBox3.getSelectedItem().equals("Warehouse")){
        duree_amortissement.setText("20");
         
         }else if(jComboBox3.getSelectedItem().equals("Office layout")){
        duree_amortissement.setText("10");
         
         }else if(jComboBox3.getSelectedItem().equals("Light layout")){
        duree_amortissement.setText("6.5");
         
         }else if(jComboBox3.getSelectedItem().equals("Single-Family Home")){
   duree_amortissement.setText("100");
         }else if(jComboBox3.getSelectedItem().equals("Condominium")){
         duree_amortissement.setText("10");
         
         }else if(jComboBox3.getSelectedItem().equals("Townhouse")){
         duree_amortissement.setText("100");
         
         }else if(jComboBox3.getSelectedItem().equals("Co-op")){
         duree_amortissement.setText("10");
         
         }else if(jComboBox3.getSelectedItem().equals("Multi-Family Home")){
         duree_amortissement.setText("100");
         
         }else if(jComboBox3.getSelectedItem().equals("Painting")){
         duree_amortissement.setText("5");
         
         }else if(jComboBox3.getSelectedItem().equals("Flooring")){
         duree_amortissement.setText("5");
         
         }else if(jComboBox3.getSelectedItem().equals("Magazin furniture")){
         duree_amortissement.setText("10");
         
         }else if(jComboBox3.getSelectedItem().equals("Big jobs in local")){
         duree_amortissement.setText("10");
         
         }else if(jComboBox3.getSelectedItem().equals("Masonry")){
         duree_amortissement.setText("10");
         
         }else if(jComboBox3.getSelectedItem().equals("Single seat")){
         duree_amortissement.setText("10");
         
         }else if(jComboBox3.getSelectedItem().equals("Multiple seats")){
         duree_amortissement.setText("10");
         
         }else if(jComboBox3.getSelectedItem().equals("Sleeping or lying")){
         duree_amortissement.setText("10");
         
         }else if(jComboBox3.getSelectedItem().equals("Entertainment")){
         duree_amortissement.setText("10");
         
         }else if(jComboBox3.getSelectedItem().equals("Tables")){
         duree_amortissement.setText("10");
         
         }else if(jComboBox3.getSelectedItem().equals("Storage")){
         duree_amortissement.setText("10");
         
         }else if(jComboBox3.getSelectedItem().equals("Sets")){
         duree_amortissement.setText("10");
         
         }else if(jComboBox3.getSelectedItem().equals("Certificate")){
         duree_amortissement.setText("3");
         
         }else if(jComboBox3.getSelectedItem().equals("License")){
         duree_amortissement.setText("3");
         
         }else if(jComboBox3.getSelectedItem().equals("Software")){
         duree_amortissement.setText("3");
         
         }else if(jComboBox3.getSelectedItem().equals("Website")){
         duree_amortissement.setText("3");
         
         }
          
     }
     
     
     public void save(){
         
         String status="";
if(jRadioButton1.isSelected()){
status="Brand New";
}
if(jRadioButton2.isSelected()){
status="Second Hand";
}
      
     String sql="INSERT INTO `billa_mobilier`(`NAME`, `GATECORIE`, `SOLDEDATE`, `USEDDATE`, `STATUS`, `TYPE`, `YEARS`, `AMOUNT`, `QTY`) VALUES(?,?,?,?,?,?,?,?,?)";
     try{
     pst=con.prepareStatement(sql);
     pst.setString(1,nom.getSelectedItem().toString());
     pst.setString(2,jComboBox1.getSelectedItem().toString());
      
       SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(jDateChooser1.getDate());
    pst.setString(3, addDate);
         SimpleDateFormat dateFormats1 = new SimpleDateFormat("yyyy-MM-dd");
         String addDate1 = dateFormats1.format(jDateChooser2.getDate());
    pst.setString(4, addDate1);
    pst.setString(5,status);
    pst.setString(6,jComboBox3.getSelectedItem().toString());
    pst.setString(7,duree_amortissement.getText());
    pst.setString(8,exact_amount.getText());
    pst.setString(9,qty.getText());
            // pst.setString(1,nom.getText());
     
     pst.executeUpdate();
     JOptionPane.showMessageDialog(null, "Saved");
     
     }catch(Exception ex){JOptionPane.showMessageDialog(null, ex); }
     }
     
     
     
      public void Call_ID_TO_BOMBOBOX()
    {
         
        try{
            //String sql="select distinct DESC   from  agin";
            String sql="select NAME  from  billa_mobilier";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                String sum=rs.getString("NAME");
                  jComboBox4.addItem(sum);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
    }
      public void showTableDatas()
    {
    try{
         
       String sql="SELECT `NAME`, `GATECORIE`, `SOLDEDATE`, `USEDDATE` FROM `billa_mobilier` WHERE NAME='"+jComboBox4.getSelectedItem()+"'";
       pst = con.prepareStatement(sql);
      rs= pst.executeQuery(sql);
       jTable1.setModel(DbUtils.resultSetToTableModel(rs));
        
        TableColumn col0=jTable1.getColumnModel().getColumn(0);
        TableColumn col1=jTable1.getColumnModel().getColumn(1);
        TableColumn col2=jTable1.getColumnModel().getColumn(2);
        TableColumn col3=jTable1.getColumnModel().getColumn(3);
        //TableColumn col4=jTable1.getColumnModel().getColumn(4);
        //TableColumn col5=jTable1.getColumnModel().getColumn(5);
        //TableColumn col6=jTable1.getColumnModel().getColumn(6);
       
       
       col0.setPreferredWidth(150);
       col1.setPreferredWidth(200);
       col2.setPreferredWidth(50);
       col3.setPreferredWidth(50);
       //col4.setPreferredWidth(50);
       //col5.setPreferredWidth(50);
       //col6.setPreferredWidth(50);
       
    }catch(SQLException ex ){
     JOptionPane.showMessageDialog(null, ex);}
     try{
         
       String sql="SELECT `STATUS`,`TYPE`, `YEARS`, `AMOUNT`, `QTY` FROM `billa_mobilier` WHERE NAME='"+jComboBox4.getSelectedItem()+"'";
       pst = con.prepareStatement(sql);
      rs= pst.executeQuery(sql);
       jTable2.setModel(DbUtils.resultSetToTableModel(rs));
        
        TableColumn col0=jTable2.getColumnModel().getColumn(0);
        TableColumn col1=jTable2.getColumnModel().getColumn(1);
        TableColumn col2=jTable2.getColumnModel().getColumn(2);
        TableColumn col3=jTable2.getColumnModel().getColumn(3);
        TableColumn col4=jTable2.getColumnModel().getColumn(4);
        //TableColumn col5=jTable1.getColumnModel().getColumn(5);
        //TableColumn col6=jTable1.getColumnModel().getColumn(6);
       
       
       col0.setPreferredWidth(100);
       col1.setPreferredWidth(150);
       col2.setPreferredWidth(30);
       col3.setPreferredWidth(50);
       col4.setPreferredWidth(30);
       //col5.setPreferredWidth(50);
       //col6.setPreferredWidth(50);
       
    }catch(SQLException ex ){
     JOptionPane.showMessageDialog(null, ex);}
}
       public void showTableDatasLIST()
    {
    try{
         
       String sql="SELECT `NAME`, `GATECORIE`, `SOLDEDATE`, `USEDDATE` FROM `billa_mobilier` WHERE NAME='"+jList1.getSelectedValue()+"'";
       pst = con.prepareStatement(sql);
      rs= pst.executeQuery(sql);
       jTable1.setModel(DbUtils.resultSetToTableModel(rs));
        
        TableColumn col0=jTable1.getColumnModel().getColumn(0);
        TableColumn col1=jTable1.getColumnModel().getColumn(1);
        TableColumn col2=jTable1.getColumnModel().getColumn(2);
        TableColumn col3=jTable1.getColumnModel().getColumn(3);
        //TableColumn col4=jTable1.getColumnModel().getColumn(4);
        //TableColumn col5=jTable1.getColumnModel().getColumn(5);
        //TableColumn col6=jTable1.getColumnModel().getColumn(6);
       
       
       col0.setPreferredWidth(150);
       col1.setPreferredWidth(200);
       col2.setPreferredWidth(50);
       col3.setPreferredWidth(50);
       //col4.setPreferredWidth(50);
       //col5.setPreferredWidth(50);
       //col6.setPreferredWidth(50);
       
    }catch(SQLException ex ){
     JOptionPane.showMessageDialog(null, ex);}
     try{
         
       String sql="SELECT `STATUS`,`TYPE`, `YEARS`, `AMOUNT`, `QTY` FROM `billa_mobilier` WHERE NAME='"+jList1.getSelectedValue()+"'";
       pst = con.prepareStatement(sql);
      rs= pst.executeQuery(sql);
       jTable2.setModel(DbUtils.resultSetToTableModel(rs));
        
        TableColumn col0=jTable2.getColumnModel().getColumn(0);
        TableColumn col1=jTable2.getColumnModel().getColumn(1);
        TableColumn col2=jTable2.getColumnModel().getColumn(2);
        TableColumn col3=jTable2.getColumnModel().getColumn(3);
        TableColumn col4=jTable2.getColumnModel().getColumn(4);
        //TableColumn col5=jTable1.getColumnModel().getColumn(5);
        //TableColumn col6=jTable1.getColumnModel().getColumn(6);
       
       
       col0.setPreferredWidth(100);
       col1.setPreferredWidth(150);
       col2.setPreferredWidth(30);
       col3.setPreferredWidth(50);
       col4.setPreferredWidth(30);
       //col5.setPreferredWidth(50);
       //col6.setPreferredWidth(50);
       
    }catch(SQLException ex ){
     JOptionPane.showMessageDialog(null, ex);}
}
      
             public void attCall_LIST()
    {
        DefaultListModel list = new DefaultListModel();
        
         
        try{
            String sql="select NAME from billa_mobilier where NAME like '%"+jTextField1.getText()+"%'";
             
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                String sum=rs.getString("NAME");
               // String sums=rs.getString("LNAME");
                 list.addElement(sum);
                
                 jList1.setModel(list);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
    }
             
             public void Refresh(){
             nom.setSelectedItem("Select One Here");
             amount.setText(null);
             duree_amortissement.setText(null);
           //  jTextField2.setText(null);
             qty.setText(null);
             exact_amount.setText(null);
             jComboBox1.setSelectedItem("Choose One Gategory");
             jComboBox3.setSelectedItem("Choose One Type");
           //  jComboBox2.setSelectedItem("USD");
             jDateChooser1.setDate(null);
             jDateChooser2.setDate(null);
             
             
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
        jPanel3 = new javax.swing.JPanel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jDateChooser1 = new com.alee.extended.date.WebDateField();
        jDateChooser2 = new com.alee.extended.date.WebDateField();
        nom = new javax.swing.JComboBox<>();
        jPanel4 = new javax.swing.JPanel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        amount = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox<>();
        duree_amortissement = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        uni = new javax.swing.JRadioButton();
        broad = new javax.swing.JRadioButton();
        qty = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        exact_amount = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        tday = new javax.swing.JTextField();
        rday = new javax.swing.JTextField();
        ramount = new javax.swing.JTextField();
        sday = new javax.swing.JTextField();
        tamount = new javax.swing.JTextField();
        percentage = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPanel9 = new javax.swing.JPanel();
        jComboBox4 = new javax.swing.JComboBox<>();
        jTextField1 = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jPanel10 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu2 = new javax.swing.JMenu();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_Check_16px.png"))); // NOI18N

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jComboBox1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Choose One Gategory" }));
        jComboBox1.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                jComboBox1PopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("Denomination");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Gategory");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Sold Date");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("Date First Used");

        jDateChooser1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jDateChooser2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        nom.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        nom.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select One Here" }));
        nom.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                nomPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(nom, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(5, 5, 5)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jRadioButton1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jRadioButton1.setText("Brand New?");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        jRadioButton2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jRadioButton2.setText("Second Hand?");
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });

        amount.setEditable(false);
        amount.setBackground(new java.awt.Color(240, 240, 241));
        amount.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        amount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                amountKeyTyped(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("Estimated Amount/ Estimated Values");

        jComboBox3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Choose One Type" }));
        jComboBox3.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                jComboBox3PopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        duree_amortissement.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        duree_amortissement.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                duree_amortissementKeyTyped(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel14.setText("Years");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel15.setText("Type");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jRadioButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jRadioButton2))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(amount, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox3, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(28, 28, 28)
                                .addComponent(jLabel15)
                                .addGap(0, 57, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addComponent(duree_amortissement, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton1)
                    .addComponent(jRadioButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel14)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(amount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(duree_amortissement, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        uni.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        uni.setText("Uni Cast");
        uni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uniActionPerformed(evt);
            }
        });

        broad.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        broad.setText("Broad Cast");
        broad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                broadActionPerformed(evt);
            }
        });

        qty.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        qty.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                qtyKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                qtyKeyTyped(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("Qty");

        exact_amount.setEditable(false);
        exact_amount.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("Total Price");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(uni, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7)
                    .addComponent(qty))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(broad))
                    .addComponent(exact_amount)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(uni)
                    .addComponent(broad))
                .addGap(3, 3, 3)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addGap(1, 1, 1)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(exact_amount)
                    .addComponent(qty))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel8.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel11.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setText("Total Days");

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel16.setText("Remaining Days");

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel17.setText("Remaining Amount");

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel18.setText("Spending Days");

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel19.setText("Amount");

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel20.setText("Percentage");

        tday.setEditable(false);
        tday.setBackground(new java.awt.Color(240, 240, 241));
        tday.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        rday.setEditable(false);
        rday.setBackground(new java.awt.Color(240, 240, 241));
        rday.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        ramount.setEditable(false);
        ramount.setBackground(new java.awt.Color(240, 240, 241));
        ramount.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        sday.setEditable(false);
        sday.setBackground(new java.awt.Color(240, 240, 241));
        sday.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        tamount.setEditable(false);
        tamount.setBackground(new java.awt.Color(240, 240, 241));
        tamount.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        percentage.setEditable(false);
        percentage.setBackground(new java.awt.Color(240, 240, 241));
        percentage.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel9)
                    .addComponent(tday, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                    .addComponent(jLabel18)
                    .addComponent(sday))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel16)
                            .addComponent(rday, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                            .addComponent(tamount))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel20)
                            .addComponent(percentage, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17)
                            .addComponent(ramount, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel9)
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel16)
                        .addComponent(jLabel17)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tday, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rday, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ramount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sday, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel19)
                            .addComponent(jLabel20))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tamount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(percentage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTable1.setBackground(new java.awt.Color(0, 0, 0));
        jTable1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jTable1.setForeground(new java.awt.Color(255, 255, 255));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jTable2.setBackground(new java.awt.Color(0, 0, 0));
        jTable2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jTable2.setForeground(new java.awt.Color(255, 255, 255));
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane3.setViewportView(jTable2);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 484, Short.MAX_VALUE)
                    .addComponent(jScrollPane3)
                    .addComponent(jPanel11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel9.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jComboBox4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select One Gategory" }));
        jComboBox4.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                jComboBox4PopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        jTextField1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jTextField1.setText("Search By Gategory...");
        jTextField1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField1MouseClicked(evt);
            }
        });
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });

        jList1.setBackground(new java.awt.Color(240, 240, 240));
        jList1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jList1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jList1MouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(jList1);

        jPanel10.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButton2.setText("View");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("print");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBox4, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4)
                    .addComponent(jTextField1))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jMenu2.setText("X");
        jMenu2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jMenu2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu2MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu2);

        jMenu1.setText("File");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.CTRL_MASK));
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
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed
amount.setEditable(true);        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton2ActionPerformed

    private void uniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uniActionPerformed
buttonSelected();  
      
      Float tm = Float.parseFloat(qty.getText());
     Float a= Float.parseFloat(amount.getText());
     Float c= a*tm;
exact_amount.setText(""+c);// TODO add your handling code here:
    }//GEN-LAST:event_uniActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
if(jComboBox4.getSelectedItem().equals("Select One Gategory")){calcul_amortissementLIST();}else{
        
        calcul_amortissement();}

// TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jComboBox1PopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_jComboBox1PopupMenuWillBecomeInvisible
combobox2();        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1PopupMenuWillBecomeInvisible

    private void broadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_broadActionPerformed
buttonSelected2();        // TODO add your handling code here:
    }//GEN-LAST:event_broadActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
 SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
Date d1=null;
Date d2=null;


try{
d1 = format.parse(format.format(jDateChooser2.getDate()));
d2 = format.parse(format.format(new Date()));


}catch(Exception ex){ex.printStackTrace();}

long diff= d2.getTime()-d1.getTime();

long hours = diff/(60*60*1000);
long days = hours/24;// expending Days
//jLabel10.setText(Long.toString(days));// TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jComboBox3PopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_jComboBox3PopupMenuWillBecomeInvisible
calcule_amortisement();        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox3PopupMenuWillBecomeInvisible

    private void amountKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_amountKeyTyped
 char c=evt.getKeyChar();
        if(!(Character.isDigit(c) || (c==KeyEvent.VK_BACK_SPACE)||c==KeyEvent.VK_DELETE ||c==KeyEvent.VK_PERIOD )){
            evt.consume();
        }        // TODO add your handling code here:
    }//GEN-LAST:event_amountKeyTyped

    private void duree_amortissementKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_duree_amortissementKeyTyped
 char c=evt.getKeyChar();
        if(!(Character.isDigit(c) || (c==KeyEvent.VK_BACK_SPACE)||c==KeyEvent.VK_DELETE ||c==KeyEvent.VK_PERIOD )){
            evt.consume();
        }        // TODO add your handling code here:
    }//GEN-LAST:event_duree_amortissementKeyTyped

    private void qtyKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_qtyKeyTyped
 char c=evt.getKeyChar();
        if(!(Character.isDigit(c) || (c==KeyEvent.VK_BACK_SPACE)||c==KeyEvent.VK_DELETE ||c==KeyEvent.VK_PERIOD )){
            evt.consume();
        }        // TODO add your handling code here:
    }//GEN-LAST:event_qtyKeyTyped

    private void qtyKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_qtyKeyReleased
  Float tm = Float.parseFloat(qty.getText());
     Float a= Float.parseFloat(amount.getText());
     Float c= a*tm;
exact_amount.setText(""+c);        // TODO add your handling code here:
    }//GEN-LAST:event_qtyKeyReleased

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
amount.setEditable(true);        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void jComboBox4PopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_jComboBox4PopupMenuWillBecomeInvisible
showTableDatas();        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox4PopupMenuWillBecomeInvisible

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
attCall_LIST();        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1KeyReleased

    private void jTextField1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField1MouseClicked
jTextField1.setText(null);        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1MouseClicked

    private void jList1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList1MouseClicked
showTableDatasLIST();  
jTextField1.setText("Search By Gategory..."); // TODO add your handling code here:
    }//GEN-LAST:event_jList1MouseClicked

    private void nomPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_nomPopupMenuWillBecomeInvisible
 try{
            String sql="select * from equipement_in WHERE ITEM_ID='"+nom.getSelectedItem()+"'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                String sum=rs.getString("PRICE");
                  amount.setText(sum);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }        // TODO add your handling code here:
    }//GEN-LAST:event_nomPopupMenuWillBecomeInvisible

    private void jMenu2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu2MouseClicked
this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu2MouseClicked

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
Date date1=jDateChooser1.getDate();
Date date2=jDateChooser2.getDate();



if(nom.getSelectedItem().equals("Select One Here")){JOptionPane.showMessageDialog(null,"Please Denomination Field can not be Empty","Error",JOptionPane.ERROR_MESSAGE);
}else if(jComboBox1.getSelectedItem().equals("Choose One Gategory")){JOptionPane.showMessageDialog(null,"Please Provide One Gategory","Error",JOptionPane.ERROR_MESSAGE);
}else if(amount.getText().isEmpty()){JOptionPane.showMessageDialog(null,"Please Amount Field can not be Empty","Error",JOptionPane.ERROR_MESSAGE);
}else if(jComboBox3.getSelectedItem().equals("Choose One Type")){JOptionPane.showMessageDialog(null,"Please Provide One Type","Error",JOptionPane.ERROR_MESSAGE);
}else if(duree_amortissement.getText().isEmpty()){JOptionPane.showMessageDialog(null,"Please Year Field can not be Empty","Error",JOptionPane.ERROR_MESSAGE);
}else if(qty.getText().isEmpty()){JOptionPane.showMessageDialog(null,"Please Qty Field can not be Empty","Error",JOptionPane.ERROR_MESSAGE);
}else if(date1==null){JOptionPane.showMessageDialog(null,"Please Provide Sold Date","Error",JOptionPane.ERROR_MESSAGE);
}else if(date2==null){JOptionPane.showMessageDialog(null,"Please Provide First Used Date","Error",JOptionPane.ERROR_MESSAGE);
//}else if(bg2==null){JOptionPane.showMessageDialog(null,"Please Provide Status","Error",JOptionPane.ERROR_MESSAGE);
}else{

save();
amount.setEditable(false);
Refresh();
}        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField amount;
    private javax.swing.JRadioButton broad;
    private javax.swing.JTextField duree_amortissement;
    private javax.swing.JTextField exact_amount;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private com.alee.extended.date.WebDateField jDateChooser1;
    private com.alee.extended.date.WebDateField jDateChooser2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList<String> jList1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JComboBox<String> nom;
    private javax.swing.JTextField percentage;
    private javax.swing.JTextField qty;
    private javax.swing.JTextField ramount;
    private javax.swing.JTextField rday;
    private javax.swing.JTextField sday;
    private javax.swing.JTextField tamount;
    private javax.swing.JTextField tday;
    private javax.swing.JRadioButton uni;
    // End of variables declaration//GEN-END:variables
}
