/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intreprisemanagementsystem;

import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author DOSHE
 */
public class warehouseinstockmain extends javax.swing.JPanel {
   DefaultTableModel mode;
Connection con=null;
PreparedStatement pst=null;
ResultSet rs= null;
String ID=null;
String check=null;
String rolls;
String roll;
String NAME;
String NUM_STOCK;
 String COMPTE,CODE,COMPTEMERE,CODEMERE,CLASSES,COMPTE1,CODE1,COMPTEMERE1,CODEMERE1,CLASSES1,SUBSTR1,SUBSTRS;
    public warehouseinstockmain() {
        initComponents();
              con=JavaDbConnectUMCO.dbConnect();
        call_combo();
        webDateField2.setDate(new Date());
    }
 public void call_combo(){
    String tmp="Fournisseur";
     try{
            String sqls="select NAME from  OHADA where SUBSTR= 40";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
            while(rs.next()){
                String add1 = rs.getString("NAME");
              four.addItem(add1);
            }
             rs.close();
     pst.close();
            }
    
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
     try{
            String sqls="select NAME from  warehouse ";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
            while(rs.next()){
                String add1 = rs.getString("NAME");
              store.addItem(add1);
            }
             rs.close();
     pst.close();
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
    }
 
 public void Search2(){
         
          try{
              DefaultListModel list = new DefaultListModel();
             
          String sql="SELECT * FROM warehouse_stock where NAMES LIKE '"+search_medoc.getText()+"%'";
             
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                //String sum=rs.getString("nom");
                String sums=rs.getString("NAMES");
                 list.addElement(sums);
                
                 jList1.setModel(list);
            }
             pst.close();
            rs.close();
              
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
             
         }
 
  public void JLIST_from_COMPTE2()
    {
      
         String tmp =(String)jList1.getSelectedValue();
    
     
        try{
            
            String sql="SELECT * FROM   warehouse_stock Where NAMES='"+tmp+"'";
            
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                String sums1=rs.getString("CLASS");
                clas.setText(sums1);
                
                String sums2=rs.getString("CAT");
                cat.setText(sums2);
                
                String sums3=rs.getString("PR");
                price.setText(sums3);
                String sums4=rs.getString("CDF");
                price1.setText(sums4);
                
                NAME=rs.getString("NAME");
                NUM_STOCK=rs.getString("NUM");
             
                
                
              
                
               
                
                  
                      
            }
             pst.close();
            rs.close();
               
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
    
        }
  
  ///INSERT INTO `warehouse_stock_in_main`(`ID`, `SUPPLY`, `CLASS`, `CAT`, `NAME`, `EXP_DATE`, `PRICE`, `QTY`, `QTYC`, `BARRE`, `DATE`, `NUM`, `NAMES`, `PT`, `PTC`, `STATUS`)
   DateFormat df = new SimpleDateFormat("yyyy");
String requiredDate = df.format(new Date()).toString();
   public void roll()
     {
         try{
            String sql="SELECT NUM FROM  warehouse_stock_in_main ORDER BY NUM DESC LIMIT 1";
            

            
             pst=con.prepareStatement(sql);
             rs=pst.executeQuery();
             if(rs.next()){
                 String rl=rs.getString("NUM");
                 int ln=rl.length();
                 String stxt=rl.substring(0,14);
                 String snum=rl.substring(14,ln);
                 int n = Integer.parseInt(snum);
                 n++;
                 snum=Integer.toString(n);
                 rolls=stxt+snum ;
                 
                 
                  rs.close();
     pst.close();
             }else{
               rolls="STOCK-IN/"+requiredDate+"/1001";
             }
              rs.close();
     pst.close();
                   }catch(Exception e){
             JOptionPane.showMessageDialog(null, e); 
             
         }
     }
   
   public void select_max(){
   
         try{
            
            String sql="SELECT MAX(NUM) FROM   warehouse_stock_in_main ";
            
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            if(rs.next()){
              
     //   NOM,NOMS,CLASSI,CATEGORI,P_ACHAT,P_VENTE,D_UNITE,P_ACHAT_G,P_VENTE_G,G_VENTE,NUM,MARK,MARK_QTY,CONTAINER,CONTAINER_QTY,QTY_UNITE,VAL_UNITE,PV_$,PVG_$,PA_$,PAG_$        
                String sums1=rs.getString("MAX(NUM)");
                num_fiche.setText(sums1);       
            }
             pst.close();
            rs.close();
               
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
         
          try{
            
            String sql="SELECT SUM(PT) FROM   warehouse_stock_in_main WHERE NUM='"+num_fiche.getText()+"'";
            
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            if(rs.next()){
              
     //   NOM,NOMS,CLASSI,CATEGORI,P_ACHAT,P_VENTE,D_UNITE,P_ACHAT_G,P_VENTE_G,G_VENTE,NUM,MARK,MARK_QTY,CONTAINER,CONTAINER_QTY,QTY_UNITE,VAL_UNITE,PV_$,PVG_$,PA_$,PAG_$        
                String sums1=rs.getString("SUM(PT)");
              total.setText(sums1);       
            }
             pst.close();
            rs.close();
               
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
    
   
   }
   
    public void JLIST_from_COMPTE()
    {
   
      String tmp =four.getSelectedItem().toString();
      String tmp1 =(String)jList1.getSelectedValue();
  
     
        try{
            String sql="select * from OHADA where NAME='"+tmp+"'AND SUBSTR=40";
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            if(rs.next()){
                 
                COMPTE = rs.getString("NAME");    
                CODE = rs.getString("CODE");
                COMPTEMERE = rs.getString("COMPTEMERE");
                //qty.setText(COMPTEMERE);
                CODEMERE= rs.getString("CODEMERE");
                CLASSES = rs.getString("CLASS");
               SUBSTRS = rs.getString("SUBSTR");
               
          
          }
   rs.close();
     pst.close();
      }catch(Exception ex){
        JOptionPane.showMessageDialog(null,ex);  
      }
         try{
            String sql="select * from OHADA where NAME='"+tmp1+"' AND CLASS=3";
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            if(rs.next()){
                 
                COMPTE1 = rs.getString("NAME");    
                CODE1 = rs.getString("CODE");
                COMPTEMERE1 = rs.getString("COMPTEMERE");
                //qty.setText(COMPTEMERE);
                CODEMERE1= rs.getString("CODEMERE");
                CLASSES1 = rs.getString("CLASS");
                SUBSTR1 = rs.getString("SUBSTR");
               // client.setText(COMPTE);
               
          
          }
   rs.close();
     pst.close();
      }catch(Exception ex){
        JOptionPane.showMessageDialog(null,ex);  
      }
    }
    public void etroll()
     {
         SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM");
         String addDate = dateFormats.format(webDateField2.getDate());
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
                 roll=stxt+snum;
                 
                rs.close();
     pst.close();  
                 
             }else{
                roll= "No: "+addDate+"/1001";
                 rs.close();
     pst.close();
             }
              rs.close();
     pst.close();
             
         }catch(Exception e){
             JOptionPane.showMessageDialog(null, e); 
             
         }
     }
    Double USD,CDF;
    String PV;
    
 public void call_currency(){
      Double PR = Double.parseDouble(price.getText());
      Double QTY = Double.parseDouble(qty.getText());
           NumberFormat nf1 = new DecimalFormat("#.##");//##.##
//String fn1 = nf1.format(cdf);


 
  try{
                         String sql="SELECT * FROM  currency";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
             CDF=rs.getDouble("ACDF");
             USD=rs.getDouble("VUSD"); 
            }
          if(price1.getText().equals("CDF")){
         Double pvs=(PR/USD)*QTY;
         PV= nf1.format(pvs);

          }else{
          Double pvs=(PR*CDF)*QTY;
          PV= nf1.format(pvs);
          }
           rs.close();
     pst.close();
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
 }
  public void save(){
      Double PT = null;
      Double a= Double.parseDouble(price.getText());
      Double b= Double.parseDouble(qty.getText());
      PT=a*b;
      JLIST_from_COMPTE();
      call_currency();
      
roll();
etroll();

 
 
if(num_fiche.getText().equals("")){
 try{
        String sql="INSERT INTO `warehouse_stock_in_main`(`SUPPLY`, `CLASS`, `CAT`, `NAME`, `EXP_DATE`, `PRICE`, `QTY`, `QTYC`, `BARRE`, `DATE`, `NUM`, `NAMES`, `PT`, `PTC`, `STATUS`,NUM_STOCK)"+
                "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
        pst=con.prepareStatement(sql);
pst.setString(1,four.getSelectedItem().toString());
pst.setString(2,clas.getText());
pst.setString(3,cat.getText());
pst.setString(4,NAME);
pst.setString(5,exp.getText());
pst.setString(6,price.getText());
pst.setString(7,qty.getText());
pst.setString(8,"0");
pst.setString(9,barcode.getText());
pst.setString(10,webDateField2.getText());
pst.setString(11,rolls);
pst.setString(12,jList1.getSelectedValue());
pst.setDouble(13,PT);
pst.setDouble(14,0.00);
pst.setString(15,"Achat");
pst.setString(16,NUM_STOCK);



    pst.executeUpdate();
 //   JOptionPane.showMessageDialog(null, "DATA SAVED");
         rs.close();
     pst.close();
        }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);} 


}else{
 try{
        String sql="INSERT INTO `warehouse_stock_in_main`(`SUPPLY`, `CLASS`, `CAT`, `NAME`, `EXP_DATE`, `PRICE`, `QTY`, `QTYC`, `BARRE`, `DATE`, `NUM`, `NAMES`, `PT`, `PTC`, `STATUS`,NUM_STOCK)"+
                "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
        pst=con.prepareStatement(sql);
pst.setString(1,four.getSelectedItem().toString());
pst.setString(2,clas.getText());
pst.setString(3,cat.getText());
pst.setString(4,NAME);
pst.setString(5,exp.getText());
pst.setString(6,price.getText());
pst.setString(7,qty.getText());
pst.setString(8,"0");
pst.setString(9,barcode.getText());
pst.setString(10,webDateField2.getText());
pst.setString(11,num_fiche.getText());
pst.setString(12,jList1.getSelectedValue());
pst.setDouble(13,PT);
pst.setDouble(14,0.00);
pst.setString(15,"Achat");
pst.setString(16,NUM_STOCK);



    pst.executeUpdate();
   // JOptionPane.showMessageDialog(null, "DATA SAVED");
        rs.close();
     pst.close(); 
        }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}  
}
    select_max();
    call_table();
    
    
  
       try {
     PreparedStatement pst = con.prepareStatement("INSERT INTO `ohada_trans`(`COMPTE_M`, `COMPTE`, `CODE_M`, `CODE`, `CLASSE`, `SUBSTR`, `DEBIT`, `CREDIT`, `NUM`, `DATES`, `BUSS`, `LIBELLE`, `JOURNAL`, `CODE1`,DEBIT_CDF,CREDIT_CDF,PCD)"
        +"values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)  ");
        
        pst.setString(1, COMPTEMERE);
        pst.setString(2, COMPTE);
         pst.setString(3, CODEMERE);
         pst.setString(4, CODE);
         pst.setString(5, CLASSES);
         pst.setString(6, SUBSTRS);
         pst.setString(8,PV);
         pst.setString(7,"");
         pst.setString(9,roll);
         
       
          SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy/MM/dd");
         String addDate = dateFormats.format(webDateField2.getDate());
         pst.setString(10, addDate);
         pst.setString(11,store.getSelectedItem().toString());
         pst.setString(12,jList1.getSelectedValue());
         pst.setString(13,"[JC]");
         pst.setString(14,CODE1);
         pst.setDouble(16,PT);
         pst.setString(15,"");
         pst.setString(17,qty.getText());
         
       
          
         
          pst.executeUpdate();
         rs.close();
     pst.close();
           //      JOptionPane.showMessageDialog(null,"Caisse saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
         ///=====>>>>CREDIT 40
          try {
        
        PreparedStatement pst = con.prepareStatement("INSERT INTO `ohada_trans`(`COMPTE_M`, `COMPTE`, `CODE_M`, `CODE`, `CLASSE`, `SUBSTR`, `DEBIT`, `CREDIT`, `NUM`, `DATES`, `BUSS`, `LIBELLE`, `JOURNAL`, `CODE1`,DEBIT_CDF,CREDIT_CDF,PCD)"
        +"values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ");
        
          pst.setString(1, COMPTEMERE1);
        pst.setString(2, COMPTE1);
         pst.setString(3, CODEMERE1);
         pst.setString(4, CODE1);
         pst.setString(5, CLASSES1);
         pst.setString(6, SUBSTR1);
         pst.setString(7,PV);
         pst.setString(8,"");
         pst.setString(9,roll);
         
       
        SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy/MM/dd");
         String addDate = dateFormats.format(webDateField2.getDate());
         pst.setString(10, addDate);
         pst.setString(11,store.getSelectedItem().toString());
         pst.setString(12,jList1.getSelectedValue());
         pst.setString(13,"[JC]");
         pst.setString(14,CODE);
         pst.setDouble(15,PT);
         pst.setString(16,"");
           pst.setString(17,qty.getText());
       
          
         
          pst.executeUpdate();
         rs.close();
     pst.close();
         //      JOptionPane.showMessageDialog(null,"Transaction saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
 //INSERT TABLE MOUVEMENT
  /*
 Double solde = null;
 try{  
             Double xxx;
Double yyy;
Double QTY= Double.parseDouble(qty.getText());


//call from journala de banque  
            String sql="select SUM(QTYD),SUM(QTYC) from warehouse_mouvement where buss='"+store.getSelectedItem()+"' and NAME='"+jList1.getSelectedValue()+"'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                xxx=rs.getDouble("SUM(QTYD)");
               // entre.setText(""+xxx);
                yyy=rs.getDouble("SUM(QTYC)");
                  
                   solde=(xxx-yyy)+QTY;
                 
            }
            }catch(Exception ex){ JOptionPane.showMessageDialog(null, ex);}
 

      try{
        String sql="INSERT INTO `warehouse_mouvement`(`NAME`, `QTYD`, `QTYC`, `DEBIT`, `CREDIT`, `DEBIT_CDF`, `CREDIT_CDF`, `DATES`, `NUM_ID`,`BUSS`,`LIBELLE`,`NUM_DEL`)"+
                "values(?,?,?,?,?,?,?,?,?,?,?,?)";
        pst=con.prepareStatement(sql);
pst.setString(1,jList1.getSelectedValue());
pst.setString(2,qty.getText());
pst.setString(3,"");
pst.setString(4,price.getText());
pst.setString(5,"");
pst.setDouble(6,PT);
pst.setString(7,"");
pst.setString(8,webDateField2.getText());
pst.setDouble(9,solde);
pst.setString(10,store.getSelectedItem().toString());
pst.setString(11,"Stock entry  --->"+jList1.getSelectedValue());
pst.setString(12, roll);
    pst.executeUpdate(); 
        }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}  
 */
}    
          
          
    
  public void call_table(){
      
           try{
           
            //SUPPLY`, `CLASS`, `CAT`, `NAME`, `EXP_DATE`, `PRICE`, `QTY`, `BARRE`, `DATE`, `NUM`, `NAMES`
             String sql="SELECT `NAME`, `QTY`, `PRICE` FROM `warehouse_stock_in_main`  where num='"+num_fiche.getText()+"'ORDER BY NAME";
     //  String sql="SELECT ID, `NUM_FACTURE`, `DATE_FACTURE`, `DESCRIPTION`, `DEBIT_QTY`, `DEBIT_PU`, `DEBIT_PT`, `DEBIT_TVA`, `CREDIT_QTY`, `CREDIT_PU`, `CREDIT_PT`, `CREDIT_TVA`, `DATES`, `NUM` FROM facture_fournisseur where compte='"+jComboBox6.getSelectedItem()+"'";
       pst = con.prepareStatement(sql);
      rs= pst.executeQuery();
      
       
       jTable1.setModel(DbUtils.resultSetToTableModel(rs));
       mode=new DefaultTableModel();
       
        TableColumn col0=jTable1.getColumnModel().getColumn(0);
        TableColumn col1=jTable1.getColumnModel().getColumn(1);
        TableColumn col2=jTable1.getColumnModel().getColumn(2);
       
       
       
      
       
       col0.setPreferredWidth(250);
       col1.setPreferredWidth(50);
       col2.setPreferredWidth(50);
       
       
      rs.close();
     pst.close();
    }catch(SQLException ex ){
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

        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        exp = new com.alee.extended.date.WebDateField();
        price = new javax.swing.JTextField();
        qty = new javax.swing.JTextField();
        price1 = new javax.swing.JTextField();
        jPanel10 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        four = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        clas = new javax.swing.JTextField();
        cat = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        search_medoc = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jPanel5 = new javax.swing.JPanel();
        barcode = new javax.swing.JTextField();
        num_fiche = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        webDateField2 = new com.alee.extended.date.WebDateField();
        jLabel5 = new javax.swing.JLabel();
        store = new javax.swing.JComboBox<>();
        jPanel7 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        total = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        jScrollPane10 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(128, 255, 128));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1)
        );

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 1, 910, -1));

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("Exp. Date:");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("Price");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setText("Quantities:");

        exp.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        price.setEditable(false);
        price.setBackground(new java.awt.Color(240, 240, 241));
        price.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        price.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        qty.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        qty.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        qty.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                qtyKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                qtyKeyTyped(evt);
            }
        });

        price1.setEditable(false);
        price1.setBackground(new java.awt.Color(240, 240, 241));
        price1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        price1.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(jLabel7))
                .addGap(9, 9, 9)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(exp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(qty)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel9Layout.createSequentialGroup()
                        .addComponent(price, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(price1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(exp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(price, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)
                            .addComponent(price1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(qty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9)))
                    .addComponent(jLabel7))
                .addGap(6, 6, 6))
        );

        jPanel4.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 300, 215, -1));

        jPanel10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Supply");
        jPanel10.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, -1));

        four.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        four.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "......." }));
        jPanel10.add(four, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 191, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Classification");
        jPanel10.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, -1));

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel16.setText("Category");
        jPanel10.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, -1, -1));

        clas.setEditable(false);
        clas.setBackground(new java.awt.Color(240, 240, 241));
        clas.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jPanel10.add(clas, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 190, -1));

        cat.setEditable(false);
        cat.setBackground(new java.awt.Color(240, 240, 241));
        cat.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jPanel10.add(cat, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 190, -1));

        jPanel4.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 12, 215, 150));

        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        search_medoc.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        search_medoc.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        search_medoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                search_medocActionPerformed(evt);
            }
        });
        search_medoc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                search_medocKeyReleased(evt);
            }
        });
        jPanel6.add(search_medoc, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 190, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("Stock Name");
        jPanel6.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, -1));

        jList1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jList1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jList1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jList1);

        jPanel6.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 190, 80));

        jPanel4.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 215, 130));

        jPanel2.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 236, 420));

        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        barcode.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        barcode.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        barcode.setText("-");

        num_fiche.setEditable(false);
        num_fiche.setBackground(new java.awt.Color(240, 240, 241));
        num_fiche.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        num_fiche.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setText("Barcode:");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setText("Sheet No In");

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel13.setText("Date:");

        webDateField2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("Store:");

        store.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        store.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "......" }));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(25, 25, 25)
                        .addComponent(barcode, javax.swing.GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(store, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(num_fiche)
                    .addComponent(webDateField2, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(num_fiche, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(jLabel5)
                    .addComponent(store, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(barcode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel10))
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel13)
                        .addComponent(webDateField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jPanel2.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 50, 650, -1));

        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel14.setText("Total Amount Paid");

        total.setEditable(false);
        total.setBackground(new java.awt.Color(240, 240, 241));
        total.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        total.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 76, Short.MAX_VALUE)
                .addComponent(total, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(total, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18))
        );

        jPanel2.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 350, 650, 60));

        jPanel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton1.setText("New");

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton2.setText("Add");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton3.setText("Remove");

        jButton4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton4.setText("Save");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel2.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 410, 650, 60));

        jPanel11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jTable1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable1.setSelectionForeground(new java.awt.Color(198, 215, 233));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTable1MousePressed(evt);
            }
        });
        jScrollPane10.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 628, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 130, 650, 210));

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 51, 51));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("X");
        jLabel15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel15MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(851, Short.MAX_VALUE)
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
        );

        jPanel2.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 910, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 908, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(142, 142, 142))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 481, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
            // TODO add your handling code here:
    }//GEN-LAST:event_jLabel1MouseClicked

    private void qtyKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_qtyKeyPressed
        int key = evt.getKeyCode();
        if (key == KeyEvent.VK_ENTER) {
        save(); 
        }        // TODO add your handling code here:
    }//GEN-LAST:event_qtyKeyPressed

    private void search_medocKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_search_medocKeyReleased
Search2();            // TODO add your handling code here:
    }//GEN-LAST:event_search_medocKeyReleased

    private void jList1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList1MouseClicked
JLIST_from_COMPTE2();              // TODO add your handling code here:
    }//GEN-LAST:event_jList1MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
save();    // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable1MouseClicked

    private void jTable1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable1MousePressed

    private void jLabel15MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel15MouseClicked
this.show(false);        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel15MouseClicked

    private void search_medocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_search_medocActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_search_medocActionPerformed

    private void qtyKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_qtyKeyTyped
 char c=evt.getKeyChar();
        if(!(Character.isDigit(c) || (c==KeyEvent.VK_BACK_SPACE)||c==KeyEvent.VK_DELETE ||c==KeyEvent.VK_PERIOD )){
            evt.consume();

        }             // TODO add your handling code here:
    }//GEN-LAST:event_qtyKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField barcode;
    private javax.swing.JTextField cat;
    private javax.swing.JTextField clas;
    private com.alee.extended.date.WebDateField exp;
    private javax.swing.JComboBox<String> four;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList<String> jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField num_fiche;
    private javax.swing.JTextField price;
    private javax.swing.JTextField price1;
    private javax.swing.JTextField qty;
    private javax.swing.JTextField search_medoc;
    private javax.swing.JComboBox<String> store;
    private javax.swing.JTextField total;
    private com.alee.extended.date.WebDateField webDateField2;
    // End of variables declaration//GEN-END:variables
}
