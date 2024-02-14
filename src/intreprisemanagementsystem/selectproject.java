/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intreprisemanagementsystem;


import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.showInputDialog;
import static javax.swing.JOptionPane.showMessageDialog;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
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
public class selectproject extends javax.swing.JFrame {
 Connection con=null;
    PreparedStatement pst=null;
    ResultSet rs= null;
    String rolls;
    public selectproject() {
        initComponents();
              con=JavaDbConnect.dbConnect();
         setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("icons8_Raspberry_Pi_48px_1.png")));
        PROJET_NAME_TO_BOMBOBOX3();
    }
public void PROJET_NAME_TO_BOMBOBOX3()
    {
         
        try{
            String sql="select * from tabe";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                String sum=rs.getString("NOM");
                  jComboBox1.addItem(sum);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
     try{
String sql=" select MAX(USED) from journal";
pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                String sum=rs.getString("MAX(USED)");
                  usdjournal.setText(sum);                
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
      try{
String sql=" select USD1 from journal WHERE ID=(SELECT MAX(ID) FROM JOURNAL)";
pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                String sum=rs.getString("USD1");
                  usd1journal.setText(sum);                
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
    }  
    

public void combobox1_from_materiel()
    {
         this.setAlwaysOnTop(false);
     String tmp =(String)jComboBox1.getSelectedItem();
     if(jComboBox1.getSelectedItem().equals("SELECTIONNE UN PROJET")){
       JOptionPane.showMessageDialog(null,"LE PREOJET N'EST PAS SELECTIONNE");  
     }else{
        try{
         
     String sql="select * from  "+tmp+"";
          
            pst=con.prepareStatement(sql);
           // pst.setString(1, tmp);
            rs=pst.executeQuery();
            if(rs.next()){
           
                String add4 = rs.getString("TITRE");
               titre.setText(add4);
                       String add5 = rs.getString("NUM");
              projectid.setText(add5);
               
                 
            }
          }catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);  
                  }
     }
      try{
String sql=" select COUT_OUT from "+tmp+"";
pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                String sum=rs.getString("COUT_OUT");
                  out.setText(sum);
           
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
       try{
String sql=" select COUT_IN from "+tmp+"";
pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                String sum=rs.getString("COUT_IN");
                  in.setText(sum);
           
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
    } 
        
    


              //SAVE

public void select(){
    String tmp = NewJInternalFrameGestionEtat_de_Besoin.recherche.getText();
  
    try{
String sql=" select SUM(PT) from etat_de_besoin WHERE  NUM='"+tmp+"'";
pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                String sum=rs.getString("SUM(PT)");
                  pt.setText(sum);                
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
    
    Float a= Float.parseFloat(pt.getText());
     Float b= Float.parseFloat(usdjournal.getText());
     Float d= Float.parseFloat(usd1journal.getText());
     
     Float B= Float.parseFloat(in.getText());
     Float D= Float.parseFloat(out.getText());
     
     Float c;
     Float e;
     
     Float C;
     Float E;
     
     c=a+b;
     e=d-a;
     
     C=B-a;
     E=D+a;
     
     
     journal.setText(""+c);
     journalusd.setText(""+e);
     
     in.setText(""+C);
     out.setText(""+E);
     
    
    }



 //rolls
         
         
           public void etroll()
     {
         try{
            String sql="SELECT NUM FROM journal ORDER BY NUM DESC LIMIT 1";
             pst=con.prepareStatement(sql);
             rs=pst.executeQuery();
             if(rs.next()){
                 String rl=rs.getString("NUM");
                 int ln=rl.length();
                 String stxt=rl.substring(0,2);
                 String snum=rl.substring(2,ln);
                 int n = Integer.parseInt(snum);
                 n++;
                 snum=Integer.toString(n);
                 rolls=stxt+snum;
                 
                 
                 
             }else{
                 //rolls="FICHE/EB/2018/1";
                 rolls="EB1001";
             }
                 
             
         }catch(Exception e){
             JOptionPane.showMessageDialog(null, e); 
             
         }
     }


     
      public void savemateriaux(){
              
    
         etroll(); 
          
          
          
          
String tmp = jComboBox1.getSelectedItem().toString();
Date date=new Date();
        try {
        PreparedStatement pst = con.prepareStatement("INSERT INTO journal(LIBELLE,ENTRE,SORTIE,USD,FC,SH,USED,DATES,PROJET,POJECT_ID,USD1,NUM) "
        +"value(?,?,?,?,?,?,?,?,?,?,?,?)");
        
        pst.setString(1,NewJInternalFrameGestionEtat_de_Besoin.recherche.getText());
        pst.setInt(2, 0);
         pst.setString(3, pt.getText());
         pst.setString(4, pt.getText());
         pst.setInt(5, 0);
          pst.setInt(6, 0);
        pst.setString(7, journal.getText());
         pst.setString(11, journalusd.getText());
         pst.setString(10,projectid.getText());
         pst.setString(9,titre.getText());
         pst.setString(12,rolls);
         
        
          SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(date);
         pst.setString(8, addDate);
         
          pst.executeUpdate();
        
            //    JOptionPane.showMessageDialog(null," journal data saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
        //SAVE PLANRING TABLE
        // String TABLES =jComboBox1.getSelectedItem().toString();
    int a=0;
    char b=0;
        try {
        PreparedStatement pst = con.prepareStatement("INSERT INTO "+tmp+"(NUM,TITRE,DESCRIPTION,TYPE,CHANTIER,LOCALISATION,DEBUT,FIN,COUT,COUT_OUT,COUT_IN,SUP,POST,TEL,BNAME,BADRESSE,BDESCRIPTION,DETAIL,ENTRE,SORTIE,DDATE,CONTEXTE,OBJECTIF,RESATT,RESOBT,PREVISION,REALISATION,RAPPORT,NUMS)"
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        
       
         pst.setString(1, empty.getText());
         pst.setString(2, empty.getText());
         pst.setString(3, empty.getText());
         pst.setString(4, empty.getText());
         pst.setString(5, empty.getText());
         pst.setString(6, empty.getText());
         pst.setString(7, empty.getText());
         pst.setString(8, empty.getText());
          pst.setInt(9,a);
          pst.setString(10,out.getText());
          pst.setString(11,in.getText());
          
         pst.setString(12, empty.getText());
         pst.setString(13, empty.getText());
         pst.setString(14, empty.getText());
         pst.setString(15, empty.getText());
         pst.setString(16, empty.getText());
         pst.setString(17, empty.getText()); 
          
        pst.setString(18, NewJInternalFrameGestionEtat_de_Besoin.recherche.getText());
       pst.setInt(19, 0);
        pst.setString(20,pt.getText());
           SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(date);
         pst.setString(21, addDate);
        
        
        
         
       // pst.setInt(21,0);
         pst.setString(22, empty.getText());
         pst.setString(23, empty.getText());
         pst.setString(24, empty.getText());
         pst.setString(25, empty.getText());
         pst.setString(26, empty.getText());
         pst.setString(27, empty.getText()); 
         pst.setString(28, empty.getText()); 
        
       pst.setString(29,rolls);
         
          pst.executeUpdate();
        
     //JOptionPane.showMessageDialog(null," project data saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
        
         try {
        PreparedStatement pst = con.prepareStatement("INSERT INTO backupprojet (NOM,NUM,TITRE,DESCRIPTION,TYPE,CHANTIER,LOCALISATION,DEBUT,FIN,COUT,COUT_OUT,COUT_IN,SUP,POST,TEL,BNAME,BADRESSE,BDESCRIPTION,DETAIL,ENTRE,SORTIE,DDATE,CONTEXTE,OBJECTIF,RESATT,RESOBT,PREVISION,REALISATION,RAPPORT,NUMS)"
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        
         pst.setString(1, tmp);
         pst.setString(2, projectid.getText());
         pst.setString(3, titre.getText());
         pst.setString(4, empty.getText());
         pst.setString(5, empty.getText());
         pst.setString(6, empty.getText());
         pst.setString(7, empty.getText());
         pst.setString(8, empty.getText());
         pst.setString(9, empty.getText());
          pst.setInt(10,a);
          pst.setString(11,out.getText());
         pst.setString(12,in.getText());
          
         pst.setString(13, empty.getText());
         pst.setString(14, empty.getText());
         pst.setString(15, empty.getText());
         pst.setString(16, empty.getText());
         pst.setString(17, empty.getText());
         pst.setString(18, empty.getText()); 
          
        pst.setString(19,NewJInternalFrameGestionEtat_de_Besoin.recherche.getText());
        pst.setInt(20,a);
        pst.setString(21,pt.getText());
        SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(date);
         pst.setString(22, addDate);
        
        
        
         
       // pst.setInt(21,0);
         pst.setString(23, empty.getText());
         pst.setString(24, empty.getText());
         pst.setString(25, empty.getText());
         pst.setString(26, empty.getText());
         pst.setString(27, empty.getText());
         pst.setString(28, empty.getText()); 
         pst.setString(29, empty.getText()); 
        
       
       
         pst.setString(30,rolls);
          pst.executeUpdate();
        
     //JOptionPane.showMessageDialog(null," backup data saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
        
         
         
         try{
    String appr ="Par La Comptabilite";
 String sql = "UPDATE etat_de_besoin SET EXCECUTE=? WHERE NUM =?";
        
         pst = con.prepareStatement(sql);
         pst.setString(2,NewJInternalFrameGestionEtat_de_Besoin.recherche.getText());
          pst.setString(1,appr);
          
         pst.executeUpdate();
}catch(Exception ex){JOptionPane.showMessageDialog(null,ex);}
         
         
      
            }
      
  
     //REPORT
               public void report()
     {
        this.setAlwaysOnTop (false);
         
      /// 
          
    
     String A =showInputDialog("ENTREZ LE ROLL_No!!!");
          if(A.equals("1234")){
              
          if(NewJInternalFrameGestionEtat_de_Besoin.recherche.getText().equals("")){
              String tmp =(String) NewJInternalFrameGestionEtat_de_Besoin.recherches.getText();
     
             try{
                 
                  String sqls="Select path from pathn";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
            if(rs.next()){
             
                String sum=rs.getString("path");
                  
                 String NameFiles=sum;
            
            String NameFile=""+NameFiles+"etatdebesoin.jrxml";
                 
                 
                 
                JasperDesign jd=JRXmlLoader.load(NameFile);
                 
                // String report ="C:\\Users\\Doshe\\Documents\\NetBeansProjects\\intreprisemanagementsystem\\src\\report\\newReportmateriau.jrxml";
              //  JasperDesign jd=JRXmlLoader.load("C:\\Users\\Dosh\\Documents\\NetBeansProjects\\intreprisemanagementsystem\\src\\intreprisemanagementsystem\\etatdebesoin.jrxml");
                String sql="select * from etat_de_besoin  where NUM='"+tmp+"'";
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
              String tmp =(String) NewJInternalFrameGestionEtat_de_Besoin.recherche.getText();
               try{
                   
                   
                   String sqls="Select path from pathn";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
            if(rs.next()){
             
                String sum=rs.getString("path");
                  
                 String NameFiles=sum;
            
            String NameFile=""+NameFiles+"etatdebesoin.jrxml";
                 
                 
                 
                JasperDesign jd=JRXmlLoader.load(NameFile);
                   
                   
                   
                // String report ="C:\\Users\\Doshe\\Documents\\NetBeansProjects\\intreprisemanagementsystem\\src\\report\\newReportmateriau.jrxml";
                //JasperDesign jd=JRXmlLoader.load("C:\\Users\\Dosh\\Documents\\NetBeansProjects\\intreprisemanagementsystem\\src\\intreprisemanagementsystem\\etatdebesoin.jrxml");
                String sql="select * from etat_de_besoin  where NUM='"+tmp+"'";
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
          }else{
              showMessageDialog(null,"VOUS N'ETES PAS AUTORISE A IMPRIMER CE DOCUMENT");
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
        jComboBox1 = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        empty = new javax.swing.JTextField();
        pt = new javax.swing.JTextField();
        etat = new javax.swing.JTextField();
        usdjournal = new javax.swing.JTextField();
        journal = new javax.swing.JTextField();
        usd1journal = new javax.swing.JTextField();
        journalusd = new javax.swing.JTextField();
        titre = new javax.swing.JTextField();
        projectid = new javax.swing.JTextField();
        out = new javax.swing.JTextField();
        in = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAlwaysOnTop(true);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(204, 255, 204));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setForeground(new java.awt.Color(204, 255, 204));

        jComboBox1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECTIONNE UN PROJET" }));
        jComboBox1.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                jComboBox1PopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton1.setText("OK/IMPRIMER");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        empty.setEditable(false);
        empty.setBackground(new java.awt.Color(204, 255, 204));
        empty.setFont(new java.awt.Font("Tahoma", 0, 3)); // NOI18N
        empty.setForeground(new java.awt.Color(204, 255, 204));
        empty.setBorder(null);

        pt.setEditable(false);
        pt.setBackground(new java.awt.Color(204, 255, 204));
        pt.setFont(new java.awt.Font("Tahoma", 0, 3)); // NOI18N
        pt.setForeground(new java.awt.Color(204, 255, 204));
        pt.setBorder(null);

        etat.setEditable(false);
        etat.setBackground(new java.awt.Color(204, 255, 204));
        etat.setFont(new java.awt.Font("Tahoma", 0, 3)); // NOI18N
        etat.setForeground(new java.awt.Color(204, 255, 204));
        etat.setBorder(null);

        usdjournal.setEditable(false);
        usdjournal.setBackground(new java.awt.Color(204, 255, 204));
        usdjournal.setFont(new java.awt.Font("Tahoma", 0, 3)); // NOI18N
        usdjournal.setForeground(new java.awt.Color(204, 255, 204));
        usdjournal.setBorder(null);

        journal.setEditable(false);
        journal.setBackground(new java.awt.Color(204, 255, 204));
        journal.setFont(new java.awt.Font("Tahoma", 0, 3)); // NOI18N
        journal.setForeground(new java.awt.Color(204, 255, 204));
        journal.setBorder(null);

        usd1journal.setEditable(false);
        usd1journal.setBackground(new java.awt.Color(204, 255, 204));
        usd1journal.setFont(new java.awt.Font("Tahoma", 0, 3)); // NOI18N
        usd1journal.setForeground(new java.awt.Color(204, 255, 204));
        usd1journal.setBorder(null);

        journalusd.setEditable(false);
        journalusd.setBackground(new java.awt.Color(204, 255, 204));
        journalusd.setFont(new java.awt.Font("Tahoma", 0, 3)); // NOI18N
        journalusd.setForeground(new java.awt.Color(204, 255, 204));
        journalusd.setBorder(null);

        titre.setEditable(false);
        titre.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        titre.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        projectid.setEditable(false);
        projectid.setBackground(new java.awt.Color(204, 255, 204));
        projectid.setFont(new java.awt.Font("Tahoma", 0, 3)); // NOI18N
        projectid.setForeground(new java.awt.Color(204, 255, 204));
        projectid.setBorder(null);

        out.setEditable(false);
        out.setBackground(new java.awt.Color(204, 255, 204));
        out.setFont(new java.awt.Font("Tahoma", 0, 3)); // NOI18N
        out.setForeground(new java.awt.Color(204, 255, 204));
        out.setBorder(null);

        in.setEditable(false);
        in.setBackground(new java.awt.Color(204, 255, 204));
        in.setFont(new java.awt.Font("Tahoma", 0, 3)); // NOI18N
        in.setForeground(new java.awt.Color(204, 255, 204));
        in.setBorder(null);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(in, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21)
                        .addComponent(journalusd, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(usd1journal, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(journal, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(out, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(projectid, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(empty, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(1, 1, 1)
                                        .addComponent(pt, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(usdjournal, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addComponent(etat, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jComboBox1, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(titre, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(78, 78, 78)
                        .addComponent(pt, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21)
                        .addComponent(usdjournal, javax.swing.GroupLayout.PREFERRED_SIZE, 7, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(94, 94, 94)
                        .addComponent(projectid, javax.swing.GroupLayout.PREFERRED_SIZE, 6, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(69, 69, 69)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(etat, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(78, 78, 78)
                        .addComponent(empty, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(titre, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(in, javax.swing.GroupLayout.PREFERRED_SIZE, 7, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(journalusd, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(usd1journal, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(journal, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(out, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
this.setAlwaysOnTop(false);


  String tmp = jComboBox1.getSelectedItem().toString();        
     String l = NewJInternalFrameGestionEtat_de_Besoin.recherche.getText();
    float f = 0;
     float g = 0;
     float h=0;
      float i=0;  
      float j=0;
      float k=0;
      float n=0;

 try{
              String sql="select sum(ENTRE) from journal";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
               f=rs.getFloat("sum(ENTRE)");
                 
                 }
             String sql1="select sum(USD1) from journal";
           
            pst=con.prepareStatement(sql1);
            rs=pst.executeQuery();
            while(rs.next()){
                g=rs.getFloat("sum(USD1)");}
            
            
             String sql2="select max(COUT) from "+tmp+"";
           
            pst=con.prepareStatement(sql2);
            rs=pst.executeQuery();
            while(rs.next()){
               h = rs.getFloat("max(COUT)");
             
            }
            
            
            String sqls="select max(COUT_OUT) from "+tmp+"";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
            while(rs.next()){
                i =rs.getFloat("max(COUT_OUT)");
                 
            }
            
            
            
            /// sum(PT)etat de besoin
            
              String sql3="select sum(PT) from etat_de_besoin where NUM='"+l+"'";
           
            pst=con.prepareStatement(sql3);
            rs=pst.executeQuery();
            while(rs.next()){
                n =rs.getFloat("sum(PT)");
                 
            }
            
            //journal
           // j=f-g;
            //projet
            k=h-i;
            
             }catch(Exception ex){JOptionPane.showMessageDialog(null,ex);}
 
 
 
        if(jComboBox1.getSelectedItem().equals("SELECTIONNE UN PROJET")){
JOptionPane.showMessageDialog(null,"LE PREOJET N'EST PAS SELECTIONNE"); 
}else if ( f<= n ){
         JOptionPane.showMessageDialog(null,"LA MONNAIS N'EST PAS SOUFFISANTE POUR CETTE TRANSACTION!!!"); 
        }else if( k<= n ){
         JOptionPane.showMessageDialog(null,"LE PROJET  NE CONTIENT  PAS ASSER DE BUDGET SOUFFISANT POUR CETTE TRANSACTION!!!");    
        }else{ 
            
            
            
            
           
            
            
            
            
        select();
savemateriaux();


 
}
try{
       
         String sql="SELECT distinct NUM FROM etat_de_besoin WHERE DATE(DATES) = CURDATE() and EXCECUTE='"+""+"'";
          pst = con.prepareStatement(sql);
        rs= pst.executeQuery();
        NewJInternalFrameGestionEtat_de_Besoin.jTable2.setModel(DbUtils.resultSetToTableModel(rs));
        
      }catch(SQLException ex ){
      JOptionPane.showMessageDialog(null, ex);
}    

report();
this.dispose();
// TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jComboBox1PopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_jComboBox1PopupMenuWillBecomeInvisible
combobox1_from_materiel();        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1PopupMenuWillBecomeInvisible

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(selectproject.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(selectproject.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(selectproject.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(selectproject.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new selectproject().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField empty;
    public static javax.swing.JTextField etat;
    private javax.swing.JTextField in;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField journal;
    private javax.swing.JTextField journalusd;
    private javax.swing.JTextField out;
    private javax.swing.JTextField projectid;
    private javax.swing.JTextField pt;
    private javax.swing.JTextField titre;
    private javax.swing.JTextField usd1journal;
    private javax.swing.JTextField usdjournal;
    // End of variables declaration//GEN-END:variables
}
