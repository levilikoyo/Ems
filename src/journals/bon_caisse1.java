/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package journals;

import intreprisemanagementsystem.*;
import com.alee.laf.WebLookAndFeel;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
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
 *LEVI
 * @author Dosh
 */
public class bon_caisse1 extends javax.swing.JFrame {

   Connection con=null;
PreparedStatement pst=null;
ResultSet rs= null;
 DefaultTableModel mode;
 String rolls,repalceString,num_fiche;
    public bon_caisse1() {
        initComponents();
          con=JavaDbConnect.dbConnect();
           setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("icons8_Raspberry_Pi_48px_1.png")));
      jDateChooser1.setDate(new Date());
      calll();
    
    }

  public void call(){
       Long a = Long.parseLong(montant.getText());
    displays.setText(""+ FrenchNumberToWords.convert(a));
// String aa = montant.getText();
     // JOptionPane.showMessageDialog(null,"*** " + FrenchNumberToWords.convert(aa));
  }
  
  public void calll(){
  
  
   try{
          String sql="SELECT * FROM currency";
             
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                //String sum=rs.getString("nom");
                String sums=rs.getString("APPR");
                
                
               jComboBox1.addItem(sums);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
  }
  
   public void etroll()
     {
         repalceString=designation.getText().replace("'","''");
           String  repalceStrings=designation.getText().replace("'","''");
         try{
            String sql="SELECT NUM FROM recu where TRANSACTION='"+ repalceString+"'  ORDER BY NUM DESC LIMIT 1";
           
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
                 rolls="R-1001";
             }
             }catch(Exception e){
             JOptionPane.showMessageDialog(null, e); 
             
         }
        // JOptionPane.showMessageDialog(null, rolls);
          String NUMS = null;
        int SUSTRING = 0;
        String sqls = null,sqlS = null;
      
           try{
               if(designation.getText().equals("BON D ENTREE CAISSE")){
                   if(journals.journal1.devise.getText().equals("USD")){
                sqls="SELECT * FROM  transaction_number WHERE PROJET='"+journals.journal1.buss.getText()+"' and NAME='BEC-USD'";
                 }else{
                 sqls="SELECT * FROM  transaction_number WHERE PROJET='"+journals.journal1.buss.getText()+"' and NAME='BEC-CDF'";
               }
              }else if(designation.getText().equals("BON DE SORTIE CAISSE")){
                  if(journals.journal1.devise.getText().equals("USD")){
                sqls="SELECT * FROM  transaction_number WHERE PROJET='"+journals.journal1.buss.getText()+"' and NAME='BSC-USD'";
                 }else{
                 sqls="SELECT * FROM  transaction_number WHERE PROJET='"+journals.journal1.buss.getText()+"' and NAME='BSC-CDF'";
               }
              // sqls="SELECT * FROM  transaction_number WHERE PROJET='"+journals.journal1.buss.getText()+"' and NAME='BSC-CDF'";
               }else if(designation.getText().equals("BON D ENTREE BANQUE")){
               sqls="SELECT * FROM  transaction_number WHERE PROJET='"+journals.journal1.buss.getText()+"' and NAME='BEB-USD'";
               }else if(designation.getText().equals("BON DE SORTIE BANQUE")){
               sqls="SELECT * FROM  transaction_number WHERE PROJET='"+journals.journal1.buss.getText()+"' and NAME='BSB-USD'";
               }
        //  String sql="SELECT * FROM  transaction_number WHERE PROJET='"+journals.journal1.buss.getText()+"' and NAME='BSC-USD'";
          
             
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
           if(rs.next()){
                //String sum=rs.getString("nom");
                NUMS=rs.getString("NUM");
                SUSTRING=rs.getInt("SUBSTR");
              //  jLabel6.setText(NUMS);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);  
         
        }
          // JOptionPane.showMessageDialog(null, NUMS+" "+SUSTRING)    ;  
          try{
         if(designation.getText().equals("BON D ENTREE CAISSE")){
                 sqlS="SELECT NUM_FICHE FROM recu where projet='"+journals.journal1.buss.getText()+"' and TRANSACTION='"+ repalceStrings+"' AND MONAIS='"+journals.journal1.devise.getText()+"' AND NUM_FICHE='"+NUMS+"' ORDER BY NUM_FICHE DESC LIMIT 1";
                }else if(designation.getText().equals("BON DE SORTIE CAISSE")){
                  sqlS="SELECT NUM_FICHE FROM recu where projet='"+journals.journal1.buss.getText()+"' and TRANSACTION='"+ repalceStrings+"' AND MONAIS='"+journals.journal1.devise.getText()+"' AND NUM_FICHE='"+NUMS+"' ORDER BY NUM_FICHE DESC LIMIT 1";
                }else if(designation.getText().equals("BON D ENTREE BANQUE")){
                  sqlS="SELECT NUM_FICHE FROM recu where projet='"+journals.journal1.buss.getText()+"' and TRANSACTION='"+ repalceStrings+"' AND MONAIS='"+journals.journal1.devise.getText()+"' AND NUM_FICHE='"+NUMS+"' ORDER BY NUM_FICHE DESC LIMIT 1";
                }else if(designation.getText().equals("BON DE SORTIE BANQUE")){
                  sqlS="SELECT NUM_FICHE FROM recu where projet='"+journals.journal1.buss.getText()+"' and TRANSACTION='"+ repalceStrings+"' AND MONAIS='"+journals.journal1.devise.getText()+"' AND NUM_FICHE='"+NUMS+"' ORDER BY NUM_FICHE DESC LIMIT 1";
                }
             
            pst=con.prepareStatement(sqlS);
            rs=pst.executeQuery();
            if(rs.next()){
                try{
              String sql = null;  
                if(designation.getText().equals("BON D ENTREE CAISSE")){
                 sql="SELECT NUM_FICHE FROM recu where ID=(SELECT MAX(ID) FROM RECU WHERE projet='"+journals.journal1.buss.getText()+"' and TRANSACTION='"+ repalceStrings+"' AND MONAIS='"+journals.journal1.devise.getText()+"')  ORDER BY NUM_FICHE DESC LIMIT 1";
                }else if(designation.getText().equals("BON DE SORTIE CAISSE")){
                 sql="SELECT NUM_FICHE FROM recu where ID=(SELECT MAX(ID) FROM RECU WHERE projet='"+journals.journal1.buss.getText()+"' and TRANSACTION='"+ repalceStrings+"' AND MONAIS='"+journals.journal1.devise.getText()+"')ORDER BY NUM_FICHE DESC LIMIT 1";
                }else if(designation.getText().equals("BON D ENTREE BANQUE")){
                 sql="SELECT NUM_FICHE FROM recu where ID=(SELECT MAX(ID) FROM RECU WHERE projet='"+journals.journal1.buss.getText()+"' and TRANSACTION='"+ repalceStrings+"' AND MONAIS='"+journals.journal1.devise.getText()+"')ORDER BY NUM_FICHE DESC LIMIT 1";
                }else if(designation.getText().equals("BON DE SORTIE BANQUE")){
                  sql="SELECT NUM_FICHE FROM recu where ID=(SELECT MAX(ID) FROM RECU WHERE projet='"+journals.journal1.buss.getText()+"' and TRANSACTION='"+ repalceStrings+"' AND MONAIS='"+journals.journal1.devise.getText()+"')ORDER BY NUM_FICHE DESC LIMIT 1";
                }
          
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            if(rs.next()){
                 String rl=rs.getString("NUM_FICHE");
                 int ln=rl.length();
                 String stxt=rl.substring(0,SUSTRING);
                 String snum=rl.substring(SUSTRING,ln);
                 int n = Integer.parseInt(snum);
                 n++;
                 snum=Integer.toString(n);
                 //num_fiche=stxt+snum;
                 
              if(n < 10){
                 num_fiche=stxt+"00"+snum;//001 ou 009
                 }else if(n < 100){
                 num_fiche=stxt+"0"+snum;//010 ou 099
                 }else if(n > 100){
                 num_fiche=stxt+snum;// 100 ou 199
                 }  else if(n > 1000){
                 num_fiche=stxt+snum;// 1000 ou 1999
                 }         
                 
             }else{
                 //rolls="FICHE/EB/2018/1";
                 num_fiche=NUMS;
             }
             }catch(Exception e){
             JOptionPane.showMessageDialog(null, e); }
            }else{
            num_fiche=NUMS;
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
          
   //  JOptionPane.showMessageDialog(null, NUMS)    ;      
     
     }
   
   public void rollb(){
 repalceString=designation.getText().replace("'","''");
           String  repalceStrings=designation.getText().replace("'","''");
         try{
            String sql="SELECT NUM FROM recu where TRANSACTION='"+ repalceString+"'  ORDER BY NUM DESC LIMIT 1";
           
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
                 rolls="R-1001";
             }
             }catch(Exception e){
             JOptionPane.showMessageDialog(null, e); 
             
         }
        // JOptionPane.showMessageDialog(null, rolls);
          String NUMS = null;
        int SUSTRING = 0;
        String sqls = null,sqlS = null;
        
        
   }
  
     
 public void save(){
 etroll();
 String NAME = null;
  try{
          String sql="SELECT * FROM currency where APPR='"+jComboBox1.getSelectedItem()+"'";
             
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                //String sum=rs.getString("nom");
               NAME =rs.getString("NAME");
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
     try{
    String sql="INSERT INTO `recu`(`DESCR`, `QTY`, `PU`, `PT`, `NUM`, `NAME`, `LETTRE`, `DATE`, `TRANSACTION`, `NAME_TO`, `MONAIS`,`PROJET`, `NUM_FICHE`)  "+"values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
  //   `ID`, `DESCR`, `QTY`, `PU`, `PT`, `NUM`, `NAME`, `LETTRE`, `DATE`, `TRANSACTION`, `NAME_TO`, `MONAIS`, `PROJET`, `NUM_FICHE` FROM `recu`
    pst=con.prepareStatement(sql);
    pst.setString(1,motif.getText());
    pst.setString(3,"0.0");
    pst.setString(2,"0.0");
    pst.setString(4, montant.getText());
    pst.setString(5,rolls);
    pst.setString(6,name.getText());
    pst.setString(7,displays.getText()+" "+NAME);
   
    SimpleDateFormat dateFormatsS = new SimpleDateFormat("yyyy-MM-dd");
         String addDateS = dateFormatsS.format(jDateChooser1.getDate());
         pst.setString(8, addDateS);
 
         pst.setString(9,designation.getText().replace("'",""));
    pst.setString (10,"");
    
     pst.setString(11,jComboBox1.getSelectedItem().toString());
     
     pst.setString(12,journals.journal1.buss.getText());
     pst.setString(13,num_fiche);
   
    
    
    pst.executeUpdate();
    // JOptionPane.showMessageDialog(null,"BUDGET TRANS");
    }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}
     Call_for_num_receipt();
     showTableDatas();
 }
  public void Call_for_num_receipt()
    {
         repalceString=designation.getText().replace("'","''");
        try{
            String sql="select * from recu where ID= (SELECT MAX(ID) FROM RECU WHERE  TRANSACTION='"+repalceString+"' AND PROJET='"+journals.journal1.buss.getText()+"' AND MONAIS='"+journals.journal1.devise.getText()+"') ";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            if(rs.next()){
             String   MAX_RECEIPT_NO=rs.getString("NUM_FICHE");
                   num.setText( MAX_RECEIPT_NO);
                  
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
         }
  
   public void showTableDatas()
    {
         repalceString=designation.getText().replace("'","''");
    try{
      
       String sql="SELECT DESCR as 'Motifs',PT as 'Montant',NAME as 'En faveur de',Num_FICHE,Date FROM recu where TRANSACTION='"+repalceString+"' and projet='"+journals.journal1.buss.getText()+"' AND MONAIS='"+journals.journal1.devise.getText()+"'";
       pst = con.prepareStatement(sql);
      rs= pst.executeQuery();
       jTable1.setModel(DbUtils.resultSetToTableModel(rs));
        
        TableColumn col0=jTable1.getColumnModel().getColumn(0);
        TableColumn col1=jTable1.getColumnModel().getColumn(1);
        TableColumn col2=jTable1.getColumnModel().getColumn(2);
        TableColumn col3=jTable1.getColumnModel().getColumn(3);
        TableColumn col4=jTable1.getColumnModel().getColumn(4);
        
       
       
       
       col0.setPreferredWidth(100);
       col1.setPreferredWidth(50);
       col2.setPreferredWidth(100);
       col3.setPreferredWidth(50);
       col4.setPreferredWidth(50);
      
       
       
    }catch(SQLException ex ){
     JOptionPane.showMessageDialog(null, ex);
}
    }
  
   
   
   
    public void select_jTable()
   {
     
     repalceString=designation.getText().replace("'","''");   
        try{
          int row= jTable1.getSelectedRow();
         // String rows =jTable1.getName()
          String Table_click = (jTable1.getModel().getValueAt(row,3). toString());
          String sql = "SELECT * FROM recu WHERE num_FICHE= '"+Table_click+"' and TRANSACTION='"+repalceString+"'";
          pst = con.prepareStatement(sql);
          rs=pst.executeQuery();
          if(rs.next()){
    //    `DESCR`, `PT`, `NUM`, `NAME`, `LETTRE`, `DATE`, `MONAIS`
             
     
             String  IDS= rs.getString("DESCR");
             motif.setText(IDS);
             String  IDS1= rs.getString("PT");
             montant.setText(IDS1);
             String  IDS2= rs.getString("NUM_FICHE");
              num.setText(IDS2);
             String  IDS3= rs.getString("NAME");
            name.setText(IDS3);
             String  IDS4= rs.getString("LETTRE");
           displays.setText(IDS4);
             String  IDSs= rs.getString("MONAIS");
              jComboBox1.setSelectedItem(IDSs);
             
            // roll.setText(IDSs);
             
          }
         
    }catch(SQLException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }
       }
   
  public void report()
     {
         this.setAlwaysOnTop(false);
     
             try{
                 
                 String sqls="Select path from pathn";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
            if(rs.next()){
             
                String sum=rs.getString("path");
                  
                 String NameFiles=sum;
            
            String NameFile=""+NameFiles+"bon_de_sortie_banque.jrxml";
                 
                 
                 
                JasperDesign jd=JRXmlLoader.load(NameFile);
                 
                 
                 String sql="select * from recu  INNER JOIN projet ON recu.projet=projet.PROJET_NUM  where recu.NUM_FICHE='"+num.getText()+"' and TRANSACTION='"+designation.getText().replace("'","")+"'";
                  
   
                JRDesignQuery nq=new JRDesignQuery();
                nq.setText(sql);
                jd.setQuery(nq);
               
                 JasperReport jr =JasperCompileManager.compileReport(jd);
                 JasperPrint jp=JasperFillManager.fillReport(jr,null,con);
       
                 
                 
                 JasperViewer jv = null;
jv = new JasperViewer(jp);
 jv.setTitle("rcus");
 JasperViewer.viewReport(jp,false);
  JasperViewer m= new JasperViewer(jp);
 m.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            }
             }catch(Exception ex){
                  JOptionPane.showMessageDialog(null, ex);   
             }
     }
     
  public void reportrecus()
     {
         this.setAlwaysOnTop(false);
     
             try{
                 
                 String sqls="Select path from pathn";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
            if(rs.next()){
             
                String sum=rs.getString("path");
                  
                 String NameFiles=sum;
            
            String NameFile=""+NameFiles+"recus_caisse.jrxml";
                 
                 
                 
                JasperDesign jd=JRXmlLoader.load(NameFile);
                 
                 
                 String sql="select * from recu  where NUM_FICHE='"+num.getText()+"'";
                  
   HashMap param= new HashMap();
   if(designation.getText().equals("BON D ENTREE BANQUE")){
     param.put("Name", "RECUS DE PAIEMENT");
   }else{
   
    param.put("Name", "RECUS CAISSE");
   }
                JRDesignQuery nq=new JRDesignQuery();
                nq.setText(sql);
                jd.setQuery(nq);
               
                 JasperReport jr =JasperCompileManager.compileReport(jd);
                 JasperPrint jp=JasperFillManager.fillReport(jr,param,con);
                 //JasperViewer.viewReport(jp,false);
                 
                 
                 JasperViewer jv = null;
jv = new JasperViewer(jp);
 jv.setTitle("rcus");
 JasperViewer.viewReport(jp,false);
//jp.setFrameIcon(new javax.swing.ImageIcon(<<your image>>));
                 
                 
                 
                 JasperViewer m= new JasperViewer(jp);
                
               //  m.setFrameIcon(new javax.swing.ImageIcon("icons8_Raspberry_Pi_48px_1.png"));
                 m.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            }
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
        jPanel2 = new javax.swing.JPanel();
        jDateChooser1 = new com.alee.extended.date.WebDateField();
        num = new Palette.MyTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jButton1 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        name = new Palette.MyTextField();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextField1 = new Palette.MyTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jDateChooser1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jDateChooser1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jDateChooser1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jDateChooser1ActionPerformed(evt);
            }
        });

        designation.setEditable(false);
        designation.setBackground(new java.awt.Color(242, 242, 241));

        num.setEditable(false);
        num.setBackground(new java.awt.Color(242, 242, 241));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(designation, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(num, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(designation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(num, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("En faveur de:");
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("Motifs");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("Montant");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("Letttre");

        motif.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        motif.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jScrollPane2.setViewportView(motif);

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton1.setText("OK");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jComboBox1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "......" }));
        jComboBox1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setText("Curr.");

        jButton2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton2.setText("Recus de Caisse");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        montant.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                montantKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(displays, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(name, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(montant, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(montant, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(displays, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

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
        jTable1.setRowHeight(30);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 629, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 431, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

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
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jDateChooser1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jDateChooser1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jDateChooser1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
if(jComboBox1.getSelectedItem().equals("......")){
JOptionPane.showMessageDialog(null,"Wrong Data","Error",JOptionPane.ERROR_MESSAGE);
}else{
 if(num.getText().isEmpty()){
 save();
report();
}else{
report();
}
}
       
       
//cals();// TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        select_jTable();        // TODO add your handling code here:
    }//GEN-LAST:event_jTable1MouseClicked

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
 // showTableDatas();        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowOpened

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

reportrecus();

              // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
Call_for_num_receipt();       // TODO add your handling code here:
    }//GEN-LAST:event_jLabel1MouseClicked

    private void montantKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_montantKeyReleased
call();        // TODO add your handling code here:
    }//GEN-LAST:event_montantKeyReleased

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
            java.util.logging.Logger.getLogger(bon_caisse1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(bon_caisse1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(bon_caisse1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(bon_caisse1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                    WebLookAndFeel.install(true);
                new bon_caisse1().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static final Palette.MyTextField designation = new Palette.MyTextField();
    public static final Palette.MyTextField displays = new Palette.MyTextField();
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboBox1;
    private com.alee.extended.date.WebDateField jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    public static final javax.swing.JTable jTable1 = new javax.swing.JTable();
    private Palette.MyTextField jTextField1;
    public static final Palette.MyTextField montant = new Palette.MyTextField();
    public static final javax.swing.JEditorPane motif = new javax.swing.JEditorPane();
    private Palette.MyTextField name;
    private Palette.MyTextField num;
    // End of variables declaration//GEN-END:variables
}
