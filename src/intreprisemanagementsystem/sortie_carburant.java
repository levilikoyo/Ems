/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intreprisemanagementsystem;

import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
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
 * @author Doshe PC
 */
public class sortie_carburant extends javax.swing.JInternalFrame {
 DefaultTableModel mode;
Connection con=null;
PreparedStatement pst=null;
ResultSet rs= null;
 String rolls;
 // Double PRIX;
    public sortie_carburant() {
        initComponents();
         con=JavaDbConnect.dbConnect();
        combos(); 
       jDateChooser1.setDate(new Date());
    }
  // AUTO ROLL NUMBER
     public void roll()
     {
         try{
             String sql="SELECT NUMS FROM  bon_carburant ORDER BY NUMS DESC LIMIT 1";
             pst=con.prepareStatement(sql);
             rs=pst.executeQuery();
             if(rs.next()){
                 String rl=rs.getString("NUMS");
                 int ln=rl.length();
                 String stxt=rl.substring(0,14);
                 String snum=rl.substring(14,ln);
                 int n = Integer.parseInt(snum);
                 n++;
                 snum=Integer.toString(n);
                 rolls=stxt+snum;
                 
                 
             }else{
               
                 rolls="BON_CARB/TWBM/1001";
             }
                 
             
         }catch(Exception e){
             JOptionPane.showMessageDialog(null, e); 
             
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
                    String Data[]=new String[6];
                    do{
                        for(int i=0;i<6;i++){
                            Data[i]=rs.getString(i+1);
                        }
                    
                    mode.addRow(Data);
                }while(rs.next());
            }
                rs.close();
            }catch(Exception ex){
                
            }
    }
    
    
    
   
    
   
    
    public void combos(){
       try{
           station.removeAllItems();
            String sql="SELECT Distinct(NOMS) FROM NEW_STATION";// where DESC='"+tmp+"'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                   String sum5=rs.getString("noms");
                 station.addItem(sum5);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
        try{
            DESCs.removeAllItems();
            String sql="SELECT Distinct(descr) FROM  station_stock";// where DESC='"+tmp+"'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                   String sum5=rs.getString("descr");
                 DESCs.addItem(sum5);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        } 
        
      try{
            String sql="select NOM,DESCR AS DESCRIPTION,NUM_AGIN AS NUMERO  from agin ";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
          jTable3.setModel(DbUtils.resultSetToTableModel(rs));
 }catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex); }
      try{
            String sql="select CAT AS Projet from caisse_dispacting WHERE NAME='"+Home_page.home.user.getText()+"' and ACCESS='Yes'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            jTable4.setModel(DbUtils.resultSetToTableModel(rs));
 }catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex); }
    }

    
    
     public void call_in_table1(){
       int row= jTable3.getSelectedRow();
          String Table_click = (jTable3.getModel().getValueAt(row,1). toString());   
         try{
  
       String sql= "SELECT ID,`DESC` AS 'FUEL DESCRIPTION', `NAME` AS 'EMPLOYEE NAME', `STATION` AS 'PETRO STATION', `ADRESSE` AS 'PETRO STATION ADRESS', `QTY` AS 'QTY/Ltrs', `DATES` AS 'DATE', `NUMS` AS 'NUM OF SHEET' FROM `bon_carburant`  WHERE `MODEL` ='"+Table_click+"'";
       pst = con.prepareStatement(sql);
      rs= pst.executeQuery();

      
       jTable1.setModel(DbUtils.resultSetToTableModel(rs));
       TableColumn col0=jTable1.getColumnModel().getColumn(0);
        TableColumn col1=jTable1.getColumnModel().getColumn(1);
       TableColumn col2=jTable1.getColumnModel().getColumn(2);
       TableColumn col3=jTable1.getColumnModel().getColumn(3);
       TableColumn col4=jTable1.getColumnModel().getColumn(4);
       TableColumn col5=jTable1.getColumnModel().getColumn(5);
       TableColumn col6=jTable1.getColumnModel().getColumn(6);
       TableColumn col7=jTable1.getColumnModel().getColumn(7);
    
      
       
       col0.setPreferredWidth(5);
       col1.setPreferredWidth(150);
       col2.setPreferredWidth(150);
       col3.setPreferredWidth(150);
       col4.setPreferredWidth(100);
       col5.setPreferredWidth(50);
       col6.setPreferredWidth(50);
       col7.setPreferredWidth(100);
       
    
      
       
    }catch(SQLException ex ){
     JOptionPane.showMessageDialog(null, ex);
}
         
     }  
     
     
      
     
    
      public void SAVE(){
          
          
          int response = JOptionPane.showConfirmDialog(null, "Do you Want to Save This Transaction?", "Confirm Save Action",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (response == JOptionPane.YES_OPTION) {
          
          roll();
  
     int row= jTable3.getSelectedRow();
          String num = (jTable3.getModel().getValueAt(row,2). toString());    
          String nom = (jTable3.getModel().getValueAt(row,0). toString());  
          String types = (jTable3.getModel().getValueAt(row,1). toString()); 
          int rows= jTable4.getSelectedRow();
          String buss = (jTable4.getModel().getValueAt(rows,0). toString());
      try{
             String sql="INSERT INTO bon_carburant (`NUM`, `NOM`, `MODEL`, `NAME`, `STATION`, `ADRESSE`, `QTY`, `PU`, `PT`, `DATES`,`NUMS`,`DESC`,`PROJET`,`UPS`,`PTS`,TYPE)"+"Values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";// where DESC='"+tmp+"'";
           
            pst=con.prepareStatement(sql);
           
            pst.setString(1,num);
            pst.setString(2,nom);
            pst.setString(3,types);
            pst.setString(4,names.getText());
            pst.setString(5,station.getSelectedItem().toString());
            pst.setString(6,adress.getText());
            pst.setString(7,qty.getText());
            pst.setString(8,pu.getText());
            pst.setString(9,qty1.getText());
            
             SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(jDateChooser1.getDate());
         pst.setString(10, addDate);
        // rolls
                 pst.setString(11,rolls);
                 pst.setString(12,desc.getText());
                 pst.setString(13,buss);
                pst.setString(14,pu.getText());
            pst.setString(15,qty1.getText());
                pst.setString(16,DESCs.getSelectedItem().toString());
          pst.executeUpdate();
          
          
          
           
          
          //JOptionPane.showMessageDialog(null, "Data Saved");
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);   
      
      
      
      }    
      
      
      
      
      
         try{
             String sql="Select max(NUMS) from  bon_carburant";
         
          
            pst=con.prepareStatement(sql);
            //pst.setString(1, tmp);
            rs=pst.executeQuery();
            if(rs.next()){
                  
                  String add = rs.getString("MAX(NUMS)");
              nums.setText(add);
               
              
             
          }
  
      }catch(Exception ex){
        JOptionPane.showMessageDialog(null,ex);  
      }
        
      
        
    } else if (response == JOptionPane.NO_OPTION) {
               // report();
            }
      }
      
      public void qstion(){
      
      int response = JOptionPane.showConfirmDialog(null, "Do you want to Save This Transaction?", "Confirm Save Action",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (response == JOptionPane.YES_OPTION) {

               
            } else if (response == JOptionPane.NO_OPTION) {
              //  report();
            }

      
      }
      
      public void delete(){
      
       try{
        String sql = "DELETE FROM  bon_carburant WHERE ID=?";
        
         pst = con.prepareStatement(sql);
         pst.setString(1,id.getText());
         pst.executeUpdate();
      //   JOptionPane.showMessageDialog(null,"delete AUTRES ACHAT");
              
     }catch(SQLException | HeadlessException ex ){
         JOptionPane.showMessageDialog(null,ex);
     } 
       try{
        String sql = "DELETE FROM   station_stock WHERE num=?";
        
         pst = con.prepareStatement(sql);
         pst.setString(1,nums.getText());
         pst.executeUpdate();
      //   JOptionPane.showMessageDialog(null,"delete AUTRES ACHAT");
              
     }catch(SQLException | HeadlessException ex ){
         JOptionPane.showMessageDialog(null,ex);
     } 
      
      }
      
      
      public void select_table(){
      
       try{
          int row= jTable1.getSelectedRow();
         // String rows =jTable1.getName()
          String Table_click = (jTable1.getModel().getValueAt(row,0). toString());
          String sql = "SELECT * FROM bon_carburant WHERE ID= '"+Table_click+"'";
          pst = con.prepareStatement(sql);
          rs=pst.executeQuery();
          if(rs.next()){
        
               String add11= rs.getString("NUMS");
              nums.setText(add11);
              String add1= rs.getString("ID");
              id.setText(add1);
          }
         
    }catch(SQLException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }
      
       try{
          int row= jTable1.getSelectedRow();
          String Table_click = (jTable1.getModel().getValueAt(row,0). toString());
          String sql = "SELECT * FROM  station_stock WHERE num= '"+Table_click+"'";
          pst = con.prepareStatement(sql);
          rs=pst.executeQuery();
          if(rs.next()){
        
               String add11= rs.getString("NUMS");
              nums.setText(add11);
             
          }
         
    }catch(SQLException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }
      }
      
      
      
      
                   public void report()
                           
     {
         try{           String sqls="Select path from pathn";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
            if(rs.next()){
             
                String sum=rs.getString("path");
                  
                 String NameFiles=sum;
            
            String NameFile=""+NameFiles+"newReportbon_carburant.jrxml";
                 
                 
                 
                JasperDesign jd=JRXmlLoader.load(NameFile);
                 
               //JasperDesign jd=JRXmlLoader.load("C:\\Users\\Dosh\\Documents\\NetBeansProjects\\intreprisemanagementsystem\\src\\intreprisemanagementsystem\\journaldecaise.jrxml");
              String sql="select * from bon_carburant INNER JOIN projet ON bon_carburant.projet=projet.PROJET_NUm WHERE NUMS='"+nums.getText()+"'";
            //  SELECT * FROM bon_carburant INNER JOIN projet ON bon_carburant.projet=projet.PROJET_NUm
              
    
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
     }
                   
                   
                   
                   
                   public void reports()
                           
     {
         try{           String sqls="Select path from pathn";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
            if(rs.next()){
             
                String sum=rs.getString("path");
                  
                 String NameFiles=sum;
            
            String NameFile=""+NameFiles+"Bon_de_carburant.jrxml";
                 
                 
                 
                JasperDesign jd=JRXmlLoader.load(NameFile);
                 
               //JasperDesign jd=JRXmlLoader.load("C:\\Users\\Dosh\\Documents\\NetBeansProjects\\intreprisemanagementsystem\\src\\intreprisemanagementsystem\\journaldecaise.jrxml");
              String sql="select * from bon_carburant";// WHERE NUMS='"+nums.getText()+"'";
              
    
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
     }
                   
                   
                   
                    public void reportstation()
                           
     {
         try{           String sqls="Select path from pathn";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
            if(rs.next()){
             
                String sum=rs.getString("path");
                  
                 String NameFiles=sum;
            
            String NameFile=""+NameFiles+"bon_carburant_station.jrxml";
                 
                 
                 
                JasperDesign jd=JRXmlLoader.load(NameFile);
                 
               //JasperDesign jd=JRXmlLoader.load("C:\\Users\\Dosh\\Documents\\NetBeansProjects\\intreprisemanagementsystem\\src\\intreprisemanagementsystem\\journaldecaise.jrxml");
              String sql="select * from bon_carburant WHERE STATION ='"+station.getSelectedItem()+"'";
              
    
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
     }
              
                    public void reportss(){
                         int row= jTable3.getSelectedRow();
          String model = (jTable3.getModel().getValueAt(row,1). toString());  
         try{           String sqls="Select path from pathn";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
            if(rs.next()){
             
                String sum=rs.getString("path");
                  
                 String NameFiles=sum;
            
            String NameFile=""+NameFiles+"Bon_de_carburant.jrxml";
                 
                 
                 
                JasperDesign jd=JRXmlLoader.load(NameFile);
                 
               //JasperDesign jd=JRXmlLoader.load("C:\\Users\\Dosh\\Documents\\NetBeansProjects\\intreprisemanagementsystem\\src\\intreprisemanagementsystem\\journaldecaise.jrxml");
              String sql="select * from bon_carburant WHERE MODEL ='"+model+"'";
              
    
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
     }
              
                  public void reportproject()
                           
     {
           int rows= jTable4.getSelectedRow();
          String buss = (jTable4.getModel().getValueAt(rows,0). toString());
         try{           String sqls="Select path from pathn";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
            if(rs.next()){
             
                String sum=rs.getString("path");
                  
                 String NameFiles=sum;
            
            String NameFile=""+NameFiles+"Bon_de_carburant.jrxml";
                 
                 
                 
                JasperDesign jd=JRXmlLoader.load(NameFile);
                 
               //JasperDesign jd=JRXmlLoader.load("C:\\Users\\Dosh\\Documents\\NetBeansProjects\\intreprisemanagementsystem\\src\\intreprisemanagementsystem\\journaldecaise.jrxml");
              String sql="select * from bon_carburant WHERE PROJET ='"+buss+"'";
              
    
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
     }
                  
                    
                    
                    //SAVE INTO CARBURANT IN
                    
                    
                      public void etroll()
     {
         try{
            String sql="SELECT NUM FROM  station_stock ORDER BY NUM DESC LIMIT 1";
            

            
             pst=con.prepareStatement(sql);
             rs=pst.executeQuery();
             if(rs.next()){
                 String rl=rs.getString("NUM");
                 int ln=rl.length();
                 String stxt=rl.substring(0,16);
                 String snum=rl.substring(16,ln);
                 int n = Integer.parseInt(snum);
                 n++;
                 snum=Integer.toString(n);
                 rolls=stxt+snum ;
                 
                 
                 
             }else{
                 rolls="MAT-STOCK-IN-ST/1001";
             }
                   }catch(Exception e){
             JOptionPane.showMessageDialog(null, e); 
             
         }
     }
                      
                      
                      String ITEM_ID,ITEM,QTY;
                      Double DEBIT,CREDIT,C;
      
        public void save(){
        //roll();
        select_Jlistws();
     
          try{
    String sql="INSERT INTO `station_stock`(`ITEM_ID`, `ITEM`, `DESCR`, `QTY`, `UNITY`, `DATES`, `STATION`,`NUM`, `CREDIT`)"+
            "values(?,?,?,?,?,?,?,?,?)";
    pst=con.prepareStatement(sql);
    pst.setString(1,ITEM_ID);
    pst.setString(2,ITEM);
      pst.setString(3,DESCs.getSelectedItem().toString());
    pst.setString(4,"0.0");
    pst.setString(5,"Ltr");
     pst.setString(6,jDateChooser1.getText());
      pst.setString(7,station.getSelectedItem().toString());
       pst.setString(8,nums.getText());
         pst.setString(9,qty1.getText());
     pst.executeUpdate();
    
    
    JOptionPane.showMessageDialog(null, "Transaction Saved");
    
    }catch(Exception ex){ JOptionPane.showMessageDialog(null, ex); }
    
    
          }
                   
            // this.show();
          public void select_Jlistws()
   {
      String sql="select ITEM_ID,ITEM,DESCR,sum(QTY),sum(credit),UNITY from  station_stock where DESCR='"+DESCs.getSelectedItem()+"' and station='"+station.getSelectedItem()+"'";
        try{
         
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
          if(rs.next()){
                ITEM_ID = rs.getString("ITEM_ID");
                ITEM = rs.getString("ITEM");
                 String desecr = rs.getString("DESCR");
               desc.setText(desecr);
               
               DEBIT =rs.getDouble("SUM(QTY)");
               CREDIT= rs.getDouble("SUM(CREDIT)");
            
               
              C= DEBIT-CREDIT;
               stock.setText(""+C);
adress1.setText(""+C);
              
               
               
               
               
             
          }
         
    }catch(SQLException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }
   }
          
           public void select_JlistwsSTATION()
   {
   
      
       
   //    String tmp =matrid.getSelectedItem().toString();
   //  String sql="select * from  materiaux where MATR_DESCR=?";
      String sql="select * from  NEW_STATION where  NOMS='"+station.getSelectedItem()+"'";
        try{
         
            pst=con.prepareStatement(sql);
         //   pst.setString(1, tmp);
            rs=pst.executeQuery();
          if(rs.next()){
            
//    qtyin.setText(""+b);
String add = rs.getString("ADDRESS");
adress.setText(add);

double PRIX= rs.getDouble("PRIX");
pu.setText(""+PRIX);

              
               
               
               
               
             
          }
         
    }catch(SQLException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }
   }
           
           public void refresh(){
      names.setText("");
     desc.setText("");
     stock.setText("0.0");
     
        nums.setText("");
        adress.setText("");
        
        
           id.setText("");
           
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
        jPanel7 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jPanel9 = new javax.swing.JPanel();
        jTextField2 = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        desc = new javax.swing.JEditorPane();
        jLabel3 = new javax.swing.JLabel();
        names = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        adress = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        qty = new javax.swing.JTextField();
        qty1 = new javax.swing.JTextField();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        pu = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jDateChooser1 = new com.alee.extended.date.WebDateField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        adress1 = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        nums = new javax.swing.JTextField();
        id = new javax.swing.JTextField();
        stock = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();

        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_Gas_Station_16px.png"))); // NOI18N

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Vehicule"));

        jTextField1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTextField1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
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
        jTable3.setRowHeight(30);
        jTable3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable3MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTable3);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTextField1)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE))
        );

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder("Projets"));

        jTextField2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTextField2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField2KeyReleased(evt);
            }
        });

        jTable4.setModel(new javax.swing.table.DefaultTableModel(
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
        jTable4.setRowHeight(30);
        jScrollPane4.setViewportView(jTable4);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTextField2)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable1.setRowHeight(30);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("Fuel");

        DESCs.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        DESCs.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        DESCs.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                DESCsPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("Libelle");

        desc.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jScrollPane2.setViewportView(desc);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("Nom");

        names.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        names.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        names.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                namesActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("Station");

        station.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        station.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        station.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                stationPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setText("Adresse");

        adress.setEditable(false);
        adress.setBackground(new java.awt.Color(240, 240, 241));
        adress.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        adress.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setText("Qte. Ltrs");

        qty.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        qty.setText("0.0");
        qty.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        qty.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                qtyKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                qtyKeyTyped(evt);
            }
        });

        qty1.setEditable(false);
        qty1.setBackground(new java.awt.Color(0, 204, 0));
        qty1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        qty1.setForeground(new java.awt.Color(255, 255, 255));
        qty1.setText("0.0");
        qty1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        qty1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                qty1KeyTyped(evt);
            }
        });

        jComboBox2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "USD" }));
        jComboBox2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel7.setText("Prix Ltrs");

        pu.setEditable(false);
        pu.setBackground(new java.awt.Color(240, 240, 241));
        pu.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        pu.setText("0.0");
        pu.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                puActionPerformed(evt);
            }
        });
        pu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                puKeyTyped(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel16.setText("Date");

        jDateChooser1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/saves.jpg"))); // NOI18N
        jLabel8.setText("Save");
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel8MouseClicked(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel9.setText("Qty in Stock");

        adress1.setEditable(false);
        adress1.setBackground(new java.awt.Color(240, 240, 241));
        adress1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        adress1.setText("0.00");
        adress1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addComponent(names)
                    .addComponent(adress)
                    .addComponent(adress1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(pu)
                            .addComponent(qty))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(qty1)
                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(DESCs, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(station, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel9)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel16))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(DESCs, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addGap(4, 4, 4)
                .addComponent(names, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addGap(7, 7, 7)
                .addComponent(station, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(adress, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(adress1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(qty, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(qty1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pu, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        nums.setEditable(false);
        nums.setBackground(new java.awt.Color(240, 240, 241));

        id.setEditable(false);
        id.setBackground(new java.awt.Color(240, 240, 241));

        stock.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        stock.setForeground(new java.awt.Color(255, 255, 255));
        stock.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        stock.setText("0.0");
        stock.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(stock, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(nums, javax.swing.GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)
                    .addComponent(id))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(nums, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(stock, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 623, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1)))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jMenu3.setText("X");
        jMenu3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jMenu3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu3MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu3);

        jMenu1.setText("File");

        jMenuItem8.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem8.setText("New");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem8);

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem1.setText("Save");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem5.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem5.setText("Delete");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem5);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Report");
        jMenu2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jMenu2KeyPressed(evt);
            }
        });

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem2.setText("Print");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem2);

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

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenu3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu3MouseClicked
        this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu3MouseClicked

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        refresh();        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        Double a = Double.parseDouble(qty.getText());
        Double c= Double.parseDouble(stock.getText());
        if(desc.getText().isEmpty() || names.getText().isEmpty() || adress.getText().isEmpty() || stock.getText().equals("0.0") ){
            JOptionPane.showMessageDialog(null,"Wrong Data","Error",JOptionPane.ERROR_MESSAGE);
        }else if(a>c){
            JOptionPane.showMessageDialog(null,"No Fuel","Error",JOptionPane.ERROR_MESSAGE);
            qty.setText("0.0");
        }else{
            SAVE();
            save();
            call_in_table1();
            refresh();
        }// TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        int response = JOptionPane.showConfirmDialog(null, "Do you want to Delete This Transaction?", "Confirm Delete Action",
            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (response == JOptionPane.YES_OPTION) {

            delete();
            id.setText("");
            call_in_table1();
            refresh();

        } else if (response == JOptionPane.NO_OPTION) {
            //  report();
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        report();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenu2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jMenu2KeyPressed

        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu2KeyPressed

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
        try{
            String sql="select NOM,DESCR AS DESCRIPTION,NUM_AGIN AS NUMERO  from agin  WHERE NOM LIKE '"+jTextField1.getText()+"%' or DESCR LIKE '"+jTextField1.getText()+"%' or NUM_AGIN LIKE '"+jTextField1.getText()+"%'";

            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            jTable3.setModel(DbUtils.resultSetToTableModel(rs));
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex); }        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1KeyReleased

    private void jTable3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable3MouseClicked
        call_in_table1();        // TODO add your handling code here:
    }//GEN-LAST:event_jTable3MouseClicked

    private void jTextField2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2KeyReleased

    private void stationPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_stationPopupMenuWillBecomeInvisible
        select_Jlistws();
        select_JlistwsSTATION();// TODO add your handling code here:
    }//GEN-LAST:event_stationPopupMenuWillBecomeInvisible

    private void namesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_namesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_namesActionPerformed

    private void DESCsPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_DESCsPopupMenuWillBecomeInvisible
        select_Jlistws();        // TODO add your handling code here:
    }//GEN-LAST:event_DESCsPopupMenuWillBecomeInvisible

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        select_table();        // TODO add your handling code here:
    }//GEN-LAST:event_jTable1MouseClicked

    private void qty1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_qty1KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_qty1KeyTyped

    private void puKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_puKeyTyped
        char c=evt.getKeyChar();
        if(!(Character.isDigit(c) || (c==KeyEvent.VK_BACK_SPACE)||c==KeyEvent.VK_DELETE ||c==KeyEvent.VK_PERIOD )){
            evt.consume();
        }        // TODO add your handling code here:
    }//GEN-LAST:event_puKeyTyped

    private void puActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_puActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_puActionPerformed

    private void qtyKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_qtyKeyTyped
        char c=evt.getKeyChar();
        if(!(Character.isDigit(c) || (c==KeyEvent.VK_BACK_SPACE)||c==KeyEvent.VK_DELETE ||c==KeyEvent.VK_PERIOD )){
            evt.consume();
        }           // TODO add your handling code here:
    }//GEN-LAST:event_qtyKeyTyped

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked
          Double a = Double.parseDouble(qty.getText());
        Double c= Double.parseDouble(stock.getText());
        if(desc.getText().isEmpty() || names.getText().isEmpty() || adress.getText().isEmpty() || stock.getText().equals("0.0") ){
            JOptionPane.showMessageDialog(null,"Wrong Data","Error",JOptionPane.ERROR_MESSAGE);
        }else if(a>c){
            JOptionPane.showMessageDialog(null,"No Fuel","Error",JOptionPane.ERROR_MESSAGE);
            qty.setText("0.0");
        }else{
            SAVE();
            save();
            call_in_table1();
            refresh();
        }     // TODO add your handling code here:
    }//GEN-LAST:event_jLabel8MouseClicked

    private void qtyKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_qtyKeyReleased
double a = Double.parseDouble(qty.getText()); 
double b = Double.parseDouble(pu.getText());
double c= a*b;
qty1.setText(""+c);
// TODO add your handling code here:
    }//GEN-LAST:event_qtyKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static final javax.swing.JComboBox<String> DESCs = new javax.swing.JComboBox<>();
    private javax.swing.JTextField adress;
    private javax.swing.JTextField adress1;
    private javax.swing.JEditorPane desc;
    private javax.swing.JTextField id;
    private javax.swing.JComboBox<String> jComboBox2;
    private com.alee.extended.date.WebDateField jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField names;
    private javax.swing.JTextField nums;
    private javax.swing.JTextField pu;
    private javax.swing.JTextField qty;
    private javax.swing.JTextField qty1;
    public static final javax.swing.JComboBox<String> station = new javax.swing.JComboBox<>();
    private javax.swing.JLabel stock;
    // End of variables declaration//GEN-END:variables
}
